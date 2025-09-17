package com.Rate_limiter.github;

public interface TokenStore {
    StoreEntry get(Key key);
    
    StoreEntry create(Key key, int timeToLiveInSecs);
}
