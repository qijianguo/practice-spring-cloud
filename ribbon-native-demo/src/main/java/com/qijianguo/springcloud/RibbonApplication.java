package com.qijianguo.springcloud;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import com.netflix.loadbalancer.reactive.ServerOperation;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import rx.Observable;
import rx.observables.BlockingObservable;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * @author qijianguo
 */

/**
 * @author qijianguo
 */
public class RibbonApplication {

    public static void main(String[] args) throws InterruptedException {
        List<Server> serverList = Arrays.asList(new Server("localhost", 8081), new Server("localhost", 8083));
        BaseLoadBalancer loadBalancer = LoadBalancerBuilder.newBuilder().buildFixedServerListLoadBalancer(serverList);
        for (int i = 0; i < 150; i++) {
            Thread.sleep(1000);
            Observable get = LoadBalancerCommand.builder()
                    .withLoadBalancer(loadBalancer)
                    .build()
                    .submit(new ServerOperation() {
                        @Override
                        public Object call(Object o) {
                            return null;
                        }

                        @Override
                        public Observable call(Server server) {
                            String addr = "http://" + server.getHost() + ":" + server.getPort() + "/user/hello?name=linglingqi";
                            System.out.println("调用地址" + addr);
                            try {
                                URL url = new URL(addr);
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                conn.setRequestMethod("GET");
                                conn.connect();
                                InputStream in = conn.getInputStream();
                                byte[] data = new byte[in.available()];
                                in.read(data);
                                return Observable.just(new String(data));
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                                loadBalancer.markServerDown(server.getId());
                            }
                            return null;
                        }
                    });
            BlockingObservable blockingObservable = get.toBlocking();
            Object result = blockingObservable.first();
            System.out.println("调用结果：" + result);
        }
    }

}
