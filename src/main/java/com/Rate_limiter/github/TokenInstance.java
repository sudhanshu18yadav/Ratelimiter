package com.Rate_limiter.github;

public enum TokenInstance implements Token {
    USABLE() {
        public boolean isUsable() {
            return true;
        }
    },

    UNUSABLE() {
        public boolean isUsable() {
            return false;
        }
    };

}
