package com.Rate_limiter.github;

public interface StoreEntry {

    int incrementAndGet();

    boolean isExpired();
}