package com.example.application;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import com.example.event.TradeEvent;

@SuppressWarnings("deprecation")
public class LegacyObserverApp {

	public static void main(String[] args) {
		TradeObserver observer = new TradeObserver();
		
		Observer slowObserver = (o, trade) -> {
			try {TimeUnit.SECONDS.sleep(4);}catch(Exception e) {}
			System.err.println("Slow Observer - " + trade);
		} ;
		Observer fastObserver = (o, trade) -> {
			System.err.println("Fast Observer - " + trade);
		} ;
		observer.addObserver(slowObserver);
		observer.addObserver(fastObserver);
		List<TradeEvent> trades = List.of(new TradeEvent("ORCL", 100, 100), new TradeEvent("MSFT", 101, 200),
				new TradeEvent("IBM", 102, 300), new TradeEvent("ORCL", 103, 400), new TradeEvent("MSFT", 104, 500),
				new TradeEvent("IBM", 105, 600));
		trades.forEach(observer::notifyObservers);
		System.err.println("Done.");
	}

}

@SuppressWarnings("deprecation")
class TradeObserver extends Observable {

	@Override
	public void notifyObservers(Object event) {
		setChanged();
		super.notifyObservers(event);
	}



}