package com.Rate_limiter.github.circuitbreaker;

interface CircuitBreakerState {
    void after(CircuitBreaker circuitBreaker);

    void before(CircuitBreaker circuitBreaker) throws CircuitBreakerException;

    void handleFailure(CircuitBreaker circuitBreaker);
    long getTimeToReset();

}
