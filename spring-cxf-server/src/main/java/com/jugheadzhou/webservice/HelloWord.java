package com.jugheadzhou.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 使用spring+cxf发布的服务接口
 */
@WebService
public interface HelloWord {

    String say(@WebParam(name = "user") String user, @WebParam(name = "content") String content);

    int add(int a, int b);
}
