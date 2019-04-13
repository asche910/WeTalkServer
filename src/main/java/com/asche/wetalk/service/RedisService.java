package com.asche.wetalk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class RedisService {

    @Autowired
    RedisTemplate redisTemplate;

    public Object get(String key){
        ValueOperations<Serializable, Object> operations =  redisTemplate.opsForValue();
        return operations.get(key);
    }

    public boolean write(String key, Object object){
        boolean flag = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, object);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
