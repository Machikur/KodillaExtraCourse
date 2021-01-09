package com.shop.domain.user;

public enum Auth {
    ADMIN, MODERATOR, USER;

    public String auth() {
        return this.name();
    }
}