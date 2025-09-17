package com.Rate_limiter.github.circuitbreaker;

class HalfOpenState implements CircuitBreakerState {

    public void after(CircuitBreaker circuitBreakerImpl) {
        circuitBreakerImpl.reset();
    }

    public void before(CircuitBreaker circuitBreakerImpl) throws CircuitBreakerException {
        // no-op
    }

    public long getTimeToReset() {
        return 0;
    }

    public void handleFailure(CircuitBreaker circuitBreakerImpl) {
        circuitBreakerImpl.tripBreaker();
    }

    @Override
    public String toString() {
        return "HALF_OPEN";
    }
    
}
