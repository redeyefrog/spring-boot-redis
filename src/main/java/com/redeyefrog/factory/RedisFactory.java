package com.redeyefrog.factory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.redeyefrog.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisFactory {

    @Autowired
    private RedisTemplate<String, Object> template;

    /**
     * Check Key Exist or Not
     *
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        return template.hasKey(key);
    }

    /**
     * Check Key And HashKey Exist or Not
     *
     * @param key
     * @param hashKey
     * @return
     */
    public boolean hasKey(String key, String hashKey) {
        return template.opsForHash().hasKey(key, hashKey);
    }

    /**
     * Set Value
     *
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        set(key, value, 0);
    }

    /**
     * Set Value with Expire Time
     *
     * @param key
     * @param value
     * @param milliseconds
     */
    public void set(String key, Object value, long milliseconds) {
        set(key, value, milliseconds, TimeUnit.MILLISECONDS);
    }

    /**
     * Set Value with Expire Time
     *
     * @param key
     * @param value
     * @param timeout
     * @param unit
     */
    public void set(String key, Object value, long timeout, TimeUnit unit) {
        if (timeout > 0 && unit != null) {
            template.opsForValue().set(key, value, timeout, unit);
//            template.boundValueOps(key).set(value, timeout, unit);
        } else {
            template.opsForValue().set(key, value);
//            template.boundValueOps(key).set(value);
        }
    }

    /**
     * Set Hashes
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public void hset(String key, String hashKey, Object value) {
        hset(key, Collections.singletonMap(hashKey, value));
//        template.opsForHash().put(key, hashKey, value);
    }

    /**
     * Set Hashes with Expire Time
     *
     * @param key
     * @param hashKey
     * @param value
     * @param milliseconds
     */
    public void hset(String key, String hashKey, Object value, long milliseconds) {
        hset(key, Collections.singletonMap(hashKey, value), milliseconds);
    }

    /**
     * Set Hashes with Expire Time
     *
     * @param key
     * @param hashKey
     * @param value
     * @param timeout
     * @param unit
     */
    public void hset(String key, String hashKey, Object value, long timeout, TimeUnit unit) {
        hset(key, Collections.singletonMap(hashKey, value), timeout, unit);
    }

    /**
     * Set Hashes
     *
     * @param key
     * @param params
     */
    public void hset(String key, Map<String, Object> params) {
        hset(key, params, 0);
    }

    /**
     * Set Hashes with Expire Time
     *
     * @param key
     * @param params
     * @param milliseconds
     */
    public void hset(String key, Map<String, Object> params, long milliseconds) {
        hset(key, params, milliseconds, TimeUnit.MILLISECONDS);
    }

    /**
     * Set Hashes with Expire Time
     *
     * @param key
     * @param params
     * @param timeout
     * @param unit
     */
    public void hset(String key, Map<String, Object> params, long timeout, TimeUnit unit) {
        template.opsForHash().putAll(key, params);
//        template.boundHashOps(key).putAll(params);

        if (timeout > 0 && unit != null) {
            expire(key, timeout, unit);
        }
    }

    /**
     * Set Expire Time
     *
     * @param key
     * @param timeout
     * @param timeUnit
     */
    public void expire(String key, long timeout, TimeUnit timeUnit) {
        template.expire(key, timeout, timeUnit);
    }

    /**
     * Set Expire DateTime
     *
     * @param key
     * @param dateTime
     * @return
     */
    public Boolean expireAt(String key, LocalDateTime dateTime) {
        return template.expireAt(key, Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant()));
    }

    /**
     * Delete Keys
     *
     * @param keys
     * @return
     */
    public Long del(String... keys) {
        return template.delete(Arrays.asList(keys));
    }

    /**
     * Delete HashKeys
     *
     * @param key
     * @param hashKeys
     * @return
     */
    public Long hdel(String key, String... hashKeys) {
        return template.opsForHash().delete(key, hashKeys);
    }


    /**
     * Get Value
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(String key, Class<T> clazz) {
        return JsonUtils.convert(template.boundValueOps(key).get(), clazz);
    }

    /**
     * Get Value
     *
     * @param key
     * @param typeReference
     * @param <T>
     * @return
     */
    public <T> T get(String key, TypeReference<T> typeReference) {
        return JsonUtils.convert(template.boundValueOps(key).get(), typeReference);
    }

    /**
     * Get Hashes
     *
     * @param key
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V> Map<K, V> hget(String key) {
        return JsonUtils.convert(template.boundHashOps(key).entries(), Map.class);
    }

    /**
     * Get Hashes
     *
     * @param key
     * @param hashKey
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T hget(String key, String hashKey, Class<T> clazz) {
        return JsonUtils.convert(template.boundHashOps(key).entries().get(hashKey), clazz);
    }

    /**
     * Get Hashes
     *
     * @param key
     * @param hashKey
     * @param typeReference
     * @param <T>
     * @return
     */
    public <T> T hget(String key, String hashKey, TypeReference<T> typeReference) {
        return JsonUtils.convert(template.boundHashOps(key).entries().get(hashKey), typeReference);
    }

}
