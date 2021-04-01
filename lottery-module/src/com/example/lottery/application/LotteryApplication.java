package com.example.lottery.application;

import java.util.ServiceLoader;
import java.util.function.Predicate;

import com.example.lottery.service.impl.StandardLotteryService;
import com.example.random.service.Quality;
import com.example.random.service.QualityLevel;
import com.example.random.service.RandomNumberService;

public class LotteryApplication {

	public static void main(String[] args) {
		StandardLotteryService lotteryService =
				new StandardLotteryService();
		ServiceLoader<RandomNumberService> services =
				ServiceLoader.load(RandomNumberService.class);
		services.forEach(srv -> System.err.println(srv.getClass().getName()));
		Predicate<ServiceLoader.Provider<RandomNumberService>> fastSelector =
				srv ->{
					var clazz = srv.get().getClass();
					
					if (clazz.isAnnotationPresent(Quality.class)) {
						var quality = clazz.getAnnotation(Quality.class);
						if (quality.value() == QualityLevel.FAST)
							return true;
					}
					return false;
				};
		RandomNumberService randomNumberService =
				services.stream().filter(fastSelector)
				                 .findFirst()
				                 .get()
				                 .get();
		
		lotteryService.setRandomNumberService(randomNumberService);
		lotteryService.draw(60, 6, 10)
		              .forEach(System.err::println);

	}

}
