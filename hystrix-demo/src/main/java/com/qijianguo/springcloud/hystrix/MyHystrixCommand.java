package com.qijianguo.springcloud.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Hystrix通过HystrixCommand对调用进行隔离
 * @author qijianguo
 */
public class MyHystrixCommand extends HystrixCommand<String> {

    private final String name;

    protected MyHystrixCommand(String name) {
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
        return this.name + ": " + Thread.currentThread().getName();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 同步调用
        MyHystrixCommand command = new MyHystrixCommand("aaa");
        String execute = command.execute();
        System.out.println(execute);

        Future<String> bbb = new MyHystrixCommand("bbb").queue();
        String s = bbb.get();
        System.out.println(s);
    }
}
