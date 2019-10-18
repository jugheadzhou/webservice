package com.jugheadzhou.webservice;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 将spring+cxf发布的服务接口复制到客户端来。
 *
 * 还有一种方式是将spring+cxf发布的服务接口打包导入到客户端来
 */
@WebService
public interface HelloWord {

    String say(@WebParam(name = "user") String user, @WebParam(name = "content") String content);

    int add(int a, int b);
}
