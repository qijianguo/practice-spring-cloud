package com.qijianguo.springcloud.randomport;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Random;

public class StartCommand {

    public StartCommand(String[] args) {
        boolean isServerPort = false;
        String serverPort = null;
        if (args != null) {
            for (String arg : args) {
                if (StringUtils.hasText(arg) && arg.contains("server.port")) {
                    isServerPort = true;
                    serverPort = arg;
                }
            }
        }
        if (isServerPort) {
            System.setProperty("server.port", serverPort.split("=")[1]);
        } else {
            System.setProperty("server.port", String.valueOf(getPort()));
        }

    }

    private int getPort() {
        int max = 65535, min = 1000;
        Random random = new Random();
        int port = random.nextInt(max) % (max - min + 1) + min;
        if (!verify(port)) {
            port = getPort();
        }
        return port;
    }

    /**
     * 检查端口
     * @param port
     * @return
     */
    private boolean verify(int port) {

        return true;
    }

}
