package com.jugheadzhou.base.service.impl;

import com.jugheadzhou.base.service.ClientService;
import com.jugheadzhou.webservice.HelloWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: webService的客户端调用服务端发布的服务的接口
 * @author: aishu
 * @create: 2019-10-18 09:15
 */
@Service
public class ClientServiceImpl implements ClientService {
    /**
     * webService的服务端发布的接口
     */
    @Resource
    private HelloWord helloWord;

    @Override
    public String say(String user){

        System.out.println("spring-cxf-client-ClientServiceImpl:");
        /**
         * 调用webService的服务端发布的服务的方法
         */
        String response = helloWord.say(user,"我是"+user);

        System.out.println("spring-cxf发布的webService的服务响应结果："+response);

        return response;
    }
}