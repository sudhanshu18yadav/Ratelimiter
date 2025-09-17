package com.Rate_limiter.github;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import com.google.common.collect.MapMaker;
import com.google.common.cache.*;
import java.util.concurrent.*;

public class MemoryTokenStore implements TokenStore {

    private final Cache<Key, StoreEntry> cache;

    private final Lock r;

    private final Lock w;

    private static StoreEntry EMPTY_STORE_ENTRY = new StoreEntryImpl(0);

    public MemoryTokenStore() {
        this.cache = CacheBuilder.newBuilder().softValues().build();
        ReadWriteLock lock = new ReentrantReadWriteLock();
        this.r = lock.readLock();
        this.w = lock.writeLock();
    }

    public StoreEntry get(Key key) {

        StoreEntry result = null;
        r.lock();

        try {
            result = this.cache.get(key, () -> { return EMPTY_STORE_ENTRY;});
        } catch(ExecutionException e){

        }
         finally {
            r.unlock();
        }

        if (!(result == null || result.isExpired())) {

            return result;
        }

        w.lock();

        result = checkPopulateThisPeriod(key);

        return result;
    }

    public StoreEntry create(Key key, int timeToLive) {
        try {
            StoreEntryImpl entry = new StoreEntryImpl(timeToLive);
            cache.put(key, entry);
            return entry;
        } finally {
            w.unlock();
        }
    }

    private StoreEntry checkPopulateThisPeriod(Key key) {

        StoreEntry result = null;

        try{
            result = this.cache.get(key, () -> { return EMPTY_STORE_ENTRY;});
        } catch(ExecutionException e){
            
        }

        if (result == null) {

        } else if (result.isExpired()) {

            cache.invalidate(key);
            result = null;
        } else {
            w.unlock();
        }

        return result;
    }

}