package com.example.cache.cache;

import lombok.SneakyThrows;

import javax.websocket.Session;
import java.time.LocalDateTime;
import java.util.Map;

public class DeleteThread extends Thread{
    @SneakyThrows
    @Override
    public void run() {
        for (Map.Entry<Session, RequestEntity> entry : CashMap.cache.entrySet()){
            Session session = entry.getKey();
            RequestEntity request = entry.getValue();
            if(request.getTime().plusHours(3).isAfter(LocalDateTime.now())){
                CashMap.cache.remove(session);
            }
        }
        sleep(60000);
    }
}
