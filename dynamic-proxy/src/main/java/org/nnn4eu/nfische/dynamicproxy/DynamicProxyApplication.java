package org.nnn4eu.nfische.dynamicproxy;

import org.nnn4eu.nfische.dynamicproxy.framework.ProxyFactory;
import org.nnn4eu.nfische.dynamicproxy.model.Counter;
import org.nnn4eu.nfische.dynamicproxy.service.ICountable;
import org.nnn4eu.nfische.dynamicproxy.service.IJumpable;


//@SpringBootApplication
public class DynamicProxyApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(DynamicProxyApplication.class, args);
//    }
public static void main(String[] args) {
    ProxyFactory proxyFactory = new ProxyFactory(DynamicProxyApplication.class.getPackage());

    Counter foo1 = new Counter(1,1000,0,1,null,"foo1",true);
    ICountable countable = proxyFactory.getBean(ICountable.class);
    System.out.println(countable.countStart(foo1));
    //System.out.println(countable.countPause(foo1));

    IJumpable jumpable = proxyFactory.getBean(IJumpable.class);
    System.out.println(jumpable.countJump(foo1,600));
}
}
