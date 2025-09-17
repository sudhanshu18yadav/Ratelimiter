package com.Rate_limiter.github.circuitbreaker;

class OpenState implements CircuitBreakerState {

    private final long tripTime;

    private final int timeout;

    OpenState(int timeout) {
        this.tripTime = System.currentTimeMillis();
        this.timeout = timeout;
    }

    public void after(CircuitBreaker circuitBreakerImpl) {
        // no-op
    }

    public void before(CircuitBreaker circuitBreakerImpl) throws CircuitBreakerException {
        long now = System.currentTimeMillis();
        long elapsed = now - this.tripTime;

        if (elapsed > this.timeout) {
            circuitBreakerImpl.attemptReset();
        } else {
            throw new CircuitBreakerOpenException("Open CircuitBreaker not yet ready for use.");
        }
    }

    public long getTimeToReset() {
        long now = System.currentTimeMillis();
        long elapsed = now - this.tripTime;

        if (elapsed < this.timeout) {
            
            /* There is still some time to go. */
            return this.timeout - elapsed;
        }
        
        /* It will reset on the next client attempt. */
        return 0;
    }

    public void handleFailure(CircuitBreaker circuitBreakerImpl) {
        // no-op
    }

    @Override
    public String toString() {
        return "OPEN";
    }

}
