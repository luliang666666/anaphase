package com.baizhi.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Component   //交由工厂管理
@Aspect      //声明此类为切面
public class RedisCacheAop {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //环绕通知  添加缓存 + 查询缓存
    @Around("@annotation(com.baizhi.annotation.AddCache)")
    public Object arroud(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        //1. 判断是否有缓存  有直接返回
        HashOperations hashOperations = redisTemplate.opsForHash();
        // key namespace  key方法名+参数 value值
        String nameSpace = proceedingJoinPoint.getTarget().getClass().getName(); //namespace
        String name = proceedingJoinPoint.getSignature().getName();        //方法名
        Object[] args = proceedingJoinPoint.getArgs();                      //获得所有的参数
        //将方法名和参数进行拼接
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        for (Object arg : args) {
            sb.append(arg.toString());
        }
        //判断缓存中key是否存在
        if(hashOperations.hasKey(nameSpace,sb)){ //存在
            System.out.println("获得缓存数据");
            Object o = hashOperations.get(nameSpace, sb);
            return o;
        }
        //查询数据库
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("添加缓存");
        //添加缓存
        hashOperations.put(nameSpace,sb,proceed);
        return proceed;
    }

    @After("@annotation(com.baizhi.annotation.ClearCache)")  //清除缓存
    public void after(JoinPoint joinPoint){
        String name = joinPoint.getTarget().getClass().getName();
        System.out.println("清除缓存");
        stringRedisTemplate.delete(name);
    }
}
