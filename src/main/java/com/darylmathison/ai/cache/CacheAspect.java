package com.darylmathison.ai.cache;

import com.darylmathison.ai.annotation.CacheMeNow;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by Daryl on 2/20/2016.
 */
@Aspect
public class CacheAspect {

    @Autowired
    private Map<String, Object> cache;

    @Around("com.darylmathison.ai.cache.SystemArch.cacheMeCut()")
    public Object simpleCache(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("before");
        StringBuffer keyBuffer = new StringBuffer();
        for(Object o: proceedingJoinPoint.getArgs()) {
            keyBuffer.append(o.hashCode());
        }
        String key = keyBuffer.toString();
        Object ret = cache.get(key);
        if(ret == null) {
            ret = proceedingJoinPoint.proceed();
            cache.put(key, ret);
        }
        System.out.println("After");
        return ret;
    }

    @Around("com.darylmathison.ai.cache.SystemArch.cacheMeNowCut() && @annotation(cacheMeNow)")
    public Object simpleCacheWithParam(ProceedingJoinPoint proceedingJoinPoint, CacheMeNow cacheMeNow) throws Throwable {
        System.out.println("before");
        Object ret = cache.get(cacheMeNow.key());
        if(ret == null) {
            ret = proceedingJoinPoint.proceed();
            cache.put(cacheMeNow.key(), ret);
        }
        System.out.println("After");
        return ret;
    }
}
