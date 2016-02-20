package com.darylmathison.ai.cache;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by Daryl on 2/20/2016.
 */
@Aspect
public class SystemArch {

    @Pointcut("@annotation(com.darylmathison.ai.annotation.CacheMe)")
    public void cacheMeCut() {

    }

    @Pointcut("@annotation(com.darylmathison.ai.annotation.CacheMeNow)")
    public void cacheMeNowCut() {

    }
}
