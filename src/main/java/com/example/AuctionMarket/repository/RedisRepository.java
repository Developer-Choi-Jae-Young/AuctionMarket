package com.example.AuctionMarket.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RedisRepository {
    private final RedisTemplate<String, Object> redisTemplate;

    public Object getData(String key)
    {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }
    public List<Object> getListData(String key)
    {
        ListOperations<String, Object> listOperations = redisTemplate.opsForList();
        return listOperations.range(key, 0, -1);
    }
    public void setData(String key, String value)
    {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    public boolean isExistData(String key)
    {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public void setDateExpire(String key, String value, long duration)
    {
        log.info("key, value" + key + ", " + value);
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        Duration exprieDuration = Duration.ofSeconds(duration);
        valueOperations.set(key, value, exprieDuration);
    }

    public void setListExpireData(String key, String value, long duration)
    {
        log.info("key, value" + key + ", " + value);
        ListOperations<String, Object> listOperations = redisTemplate.opsForList();
        listOperations.leftPush(key, value);
        redisTemplate.expire(key, duration, TimeUnit.SECONDS);
    }

    public void deleteData(String key)
    {
        redisTemplate.delete(key);
    }
}
