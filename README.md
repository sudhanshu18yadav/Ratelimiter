# Ratelimiter

## Overview
Contains the primitives and utilities used to rate-limit / throttle Java applications, and a CircuitBreaker implementation.

### Summary
A robust, scalable rate limiter to control request rates for APIs or services, ensuring system stability and fair usage.

### Features

Token Bucket Algorithm: Limits requests based on a token bucket model with configurable capacity and refill rate.
Sliding Window: Tracks requests within a time window for precise rate limiting.
Distributed Support: Integrates with Redis for distributed rate limiting across multiple instances.
Customizable Rules: Define rate limits per endpoint, user, or IP address.
Graceful Degradation: Handles failures gracefully with fallback to in-memory limiting.

### CircuitBreaker
A **circuit breaker** is a design pattern used in software systems to enhance resilience by preventing cascading failures. It monitors requests to a service and "trips" (blocks further requests) when failures exceed a threshold, protecting the system from overload.

### Core Features
- **Failure Detection**: Tracks errors (e.g., timeouts, exceptions) for a service.
- **States**:
  - **Closed**: Requests are allowed; failures are counted.
  - **Open**: Requests are blocked to prevent overloading a failing service.
  - **Half-Open**: Allows limited requests to test if the service has recovered.
- **Configurable Thresholds**: Set failure limits and timeouts.
- **Automatic Recovery**: Transitions to half-open after a cooldown period.
- **Fallback Mechanism**: Executes alternative logic when the circuit is open.

Used in distributed systems, APIs, and microservices to ensure stability and graceful degradation.
