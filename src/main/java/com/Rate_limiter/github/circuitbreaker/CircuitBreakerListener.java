package com.Rate_limiter.github.circuitbreaker;


public interface CircuitBreakerListener {

    void attemptReset();
    void reset();
    void tripped();
}
