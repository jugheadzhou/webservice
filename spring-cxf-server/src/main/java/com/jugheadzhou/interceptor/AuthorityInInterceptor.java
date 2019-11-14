package com.jugheadzhou.interceptor;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;
import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: AuthorityInInterceptor
 * @Description: 权限拦截器
 * @Author: zhaoRong
 * @Create: 2019/11/14 16:02
 **/
public class AuthorityInInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

    private Map<String,String> userInfo = new HashMap<>(16);

    //在准备协议阶段拦截请求
    private static String phase = Phase.PRE_PROTOCOL;

    public AuthorityInInterceptor() {
        super(phase);
        this.initUserInfo();
    }

    @Override
    public void handleMessage(SoapMessage soapMessage) throws Fault {

        Header header = soapMessage.getHeader(new QName("authHeader"));
        if (null == header){
            System.out.println("null == header");
            return;
        }
        Element element = (Element) header.getObject();

        String clientName = element.getElementsByTagName("name").item(0).getTextContent();
        String clientPassword = element.getElementsByTagName("password").item(0).getTextContent();

        if (this.userInfo.containsKey(clientName) && this.userInfo.get(clientName).equals(clientPassword)){
            System.out.println("spring + cxf 入拦截认证通过！");
            return;
        }else {
            System.out.println("spring + cxf 入拦截认证失败原因：用户名或密码错误");
            throw new RuntimeException("spring + cxf 入拦截认证失败,用户名或密码错误");
        }

    }

    /**
     * 模拟数据库中的数据
     */
    private void initUserInfo(){
        if (null == userInfo || userInfo.isEmpty()){
            userInfo.put("jugheadzhou","123456");
            userInfo.put("aishu","000000");
        }
    }
}

class User{
    private Long id;
    private String name;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}