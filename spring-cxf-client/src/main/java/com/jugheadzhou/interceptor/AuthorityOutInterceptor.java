package com.jugheadzhou.interceptor;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import java.util.List;

/**
 * @ClassName: AuthorityOutInterceptor
 * @Description: 客户端自定义权限出拦截器
 * @Author: zhaoRong
 * @Create: 2019/11/14 16:15
 **/

public class AuthorityOutInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

    private String name;
    private String password;
    private static String phase = Phase.PRE_PROTOCOL;

    public AuthorityOutInterceptor(String name, String password) {
        super(phase);
        this.name = name;
        this.password = password;
    }

    @Override
    public void handleMessage(SoapMessage soapMessage) throws Fault {
        /**
         * <Envelope>
         *         <head>
         *             <!--头中可以添加多个内容，本例中只添加一个-->
         *             <authHeader>
         *                 <name>jughead</name>
         *                 <password>123456</password>
         *             </authHeader>
         *             ....
         *         <head>
         *         <Body>
         *             <sayHello>
         *                 <arg0>lzj</arg0>
         *             <sayHello>
         *         </Body>
         *     </Envelope>
         */
        Document document = DOMUtils.createDocument();

        //创建根节点authHeader
        Element authHeader = document.createElement("authHeader");

        //创建name子节点
        Element nameElement = document.createElement("name");
        nameElement.setTextContent(name);
        authHeader.appendChild(nameElement);

        //创建password子节点
        Element passwordElement = document.createElement("password");
        passwordElement.setTextContent(password);
        authHeader.appendChild(passwordElement);

        soapMessage.getHeaders().add(new Header(new QName("authHeader"),authHeader));
    }
}
