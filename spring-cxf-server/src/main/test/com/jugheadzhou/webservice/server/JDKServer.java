package com.jugheadzhou.webservice.server;

import com.jugheadzhou.webservice.HelloWord;
import com.jugheadzhou.webservice.impl.HelloWordImpl;

import javax.xml.ws.Endpoint;

/**
 * @description: 使用jdk实现的webservice服务端
 * @author: aishu
 * @create: 2019-10-18 11:13
 */
public class JDKServer {
    public static void main(String[] args) {
        System.out.println("web server start");
        HelloWord helloWord = new HelloWordImpl();
        String address = "http://localhost:9999/hello";
        Endpoint endpoint = Endpoint.publish(address,helloWord);
        System.out.println("web server started");

        System.out.println(endpoint.getProperties());

    }
}