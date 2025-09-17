package com.Rate_limiter.github.circuitbreaker;

public interface CircuitBreaker {

    public void addListener(CircuitBreakerListener listener);

    public void after();

    public void attemptReset();

    public void before() throws CircuitBreakerException;

    public String getCurrentState();

    public int getThreshold();

    public long getTimeToResetInMillis();

    public long getTripCount();

    public void handleFailure();

    public void reset();

    public void tripBreaker();

}
