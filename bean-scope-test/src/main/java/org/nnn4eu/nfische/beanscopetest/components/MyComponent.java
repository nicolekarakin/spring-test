package org.nnn4eu.nfische.beanscopetest.components;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyComponent {

    private String name="default-name";

    public MyComponent() {

        System.out.println("MyComponent constructor------------------------------"+name);
        System.out.println(this);
    }

    public void doSome(String a){

        System.out.println("MyComponent doSome, a>>>name: "+a+">>>"+name);
        System.out.println(this.getClass().getClassLoader());
        System.out.println(this);
    }
}
