package com.example.application;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

import com.example.event.TradeEvent;

public class ReactiveApp {

	public static void main(String[] args) {
		try (SubmissionPublisher<TradeEvent> publisher = new SubmissionPublisher<>()) {
			Flow.Subscriber<TradeEvent> fastSubscriber = new SignalProcessor();
			Flow.Subscriber<TradeEvent> slowSubscriber = new AlgoTrader();
			publisher.subscribe(slowSubscriber);
			publisher.subscribe(fastSubscriber);
			List<TradeEvent> trades = List.of(new TradeEvent("ORCL", 100, 100), new TradeEvent("MSFT", 101, 200),
					new TradeEvent("IBM", 102, 300), new TradeEvent("ORCL", 103, 400), new TradeEvent("MSFT", 104, 500),
					new TradeEvent("IBM", 105, 600));
			trades.forEach(publisher::submit);
			try {
				TimeUnit.SECONDS.sleep(60);
			} catch (Exception e) {
			}
		}
		System.err.println("App is done.");
	}

}

class AlgoTrader implements Flow.Subscriber<TradeEvent> {

	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		subscription.request(1);
	}

	@Override
	public void onNext(TradeEvent event) {
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (Exception e) {
		}
		System.err.println("AlgoTrader - " + event);
		this.subscription.request(1);
	}

	@Override
	public void onError(Throwable throwable) {
		System.err.println("AlgoTrader - Error : " + throwable.getMessage());
	}

	@Override
	public void onComplete() {
		System.err.println("AlgoTrader - Done.");
	}

}

class SignalProcessor implements Flow.Subscriber<TradeEvent> {

	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		subscription.request(1);
	}

	@Override
	public void onNext(TradeEvent event) {
		System.err.println("SignalProcessor - " + event);
		subscription.request(1);
	}

	@Override
	public void onError(Throwable throwable) {
		System.err.println("SignalProcessor - Error : " + throwable.getMessage());
	}

	@Override
	public void onComplete() {
		System.err.println("SignalProcessor - Done.");
	}

}