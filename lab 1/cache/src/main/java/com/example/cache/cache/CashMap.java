package com.example.cache.cache;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CashMap {
    public static Map<Session, RequestEntity> cache = new ConcurrentHashMap<>();
}