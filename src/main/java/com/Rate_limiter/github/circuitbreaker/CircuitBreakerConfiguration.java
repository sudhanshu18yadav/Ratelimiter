package com.Rate_limiter.github.circuitbreaker;

public class CircuitBreakerConfiguration {

    private int threshold = 3;

    private int timeout = 10000;

    public CircuitBreaker createCircuitBreaker() {
        return new CircuitBreakerImpl(threshold, timeout);
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public int getTimeoutInMillis() {
        return timeout;
    }

    public void setTimeoutInMillis(int timeout) {
        this.timeout = timeout;
    }
}
