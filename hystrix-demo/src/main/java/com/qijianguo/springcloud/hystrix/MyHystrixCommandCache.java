package com.qijianguo.springcloud.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Hystrix通过HystrixCommand对调用进行隔离
 * @author qijianguo
 */
public class MyHystrixCommandCache extends HystrixCommand<String> {

    private final String name;

    private static final HystrixCommandKey GETTER_KEY = HystrixCommandKey.Factory.asKey("MyKey");

    protected MyHystrixCommandCache(String name) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("MyGroup"))
                .andCommandKey(GETTER_KEY));
        this.name = name;

    }

    /**
     * 执行业务逻辑
     * @return
     * @throws Exception
     */
    @Override
    protected String run() throws Exception {
        System.out.println("执行业务逻辑-----");
        return this.name + ": " + Thread.currentThread().getName();
    }

    /**
     * 结果缓存
     * @return
     */
    @Override
    protected String getCacheKey() {
        return String.valueOf(this.name);
    }

    /**
     * 清除缓存
     * @param key
     */
    public void flushCache(String key) {
        HystrixRequestCache.getInstance(GETTER_KEY, HystrixConcurrencyStrategyDefault.getInstance()
        ).clear(key);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 【必须】初始化 HystrixRequestContext
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        String name = "aaa";
        // 同步调用
        MyHystrixCommandCache command = null;
        // 验证缓存是否生效（即只执行一次业务逻辑run()）
        for (int i = 0; i < 5; i++) {
            command = new MyHystrixCommandCache(name);
            System.out.println(command.execute());
        }
        command.flushCache(name);
        System.out.println("-------------清除缓存后执行-------------");

        for (int i = 0; i < 5; i++) {
            command = new MyHystrixCommandCache(name);
            System.out.println(command.execute());
        }
    }
}
