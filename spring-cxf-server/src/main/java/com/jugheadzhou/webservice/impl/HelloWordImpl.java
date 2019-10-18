package com.jugheadzhou.webservice.impl;

import com.jugheadzhou.webservice.HelloWord;
import org.springframework.stereotype.Service;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @description: 发布的服务实现接口
 * @author: aishu
 * @create: 2019-10-16 09:13
 */
@Service("helloWorld")
@WebService(endpointInterface = "com.jugheadzhou.webservice.HelloWord")
public class HelloWordImpl implements HelloWord {

    @Override
    public String say(@WebParam(name = "user") String user,@WebParam(name = "content") String content) {

        System.out.println("spring-cxf-server-HelloWordImpl:");

        System.out.println("客户端"+user+"说："+content);

        return "欢迎你: "+user;
    }

    @Override
    public int add( int a, int b) {
        return a + b;
    }
}