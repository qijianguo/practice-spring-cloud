package com.qijianguo.springcloud.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Hystrix通过HystrixCommand对调用进行隔离
 * @author qijianguo
 */
public class MyHystrixCommandFallback extends HystrixCommand<String> {

    private final String name;

    protected MyHystrixCommandFallback(String name) {
        super(HystrixCommandGroupKey.Factory.asKey(name));
        this.name = name;
    }

    /**
     * 执行业务逻辑
     * @return
     * @throws Exception
     */
    @Override
    protected String run() throws Exception {
        Thread.sleep(1000 * 10);
        return this.name + ": " + Thread.currentThread().getName();
    }

    /**
     * 失败回调
     * @return
     */
    @Override
    protected String getFallback() {
        return "请求失败...";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 同步调用
        MyHystrixCommandFallback command = new MyHystrixCommandFallback("aaa");
        String execute = command.execute();
        System.out.println(execute);

        Future<String> bbb = new MyHystrixCommandFallback("bbb").queue();
        String s = bbb.get();
        System.out.println(s);
    }
}
