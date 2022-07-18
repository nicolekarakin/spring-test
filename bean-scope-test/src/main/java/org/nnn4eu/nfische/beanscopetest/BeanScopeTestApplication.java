package org.nnn4eu.nfische.beanscopetest;

import org.nnn4eu.nfische.beanscopetest.config.DemoCGLIBConfig;
import org.nnn4eu.nfische.beanscopetest.service.IAccountService;
import org.nnn4eu.nfische.beanscopetest.web.model.Account;
import org.nnn4eu.nfische.beanscopetest.web.model.Greeting;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Method;
import java.util.Arrays;

//@EnableCaching(proxyTargetClass = true)
//@EnableTransactionManagement(proxyTargetClass = false)
@SpringBootApplication
public class BeanScopeTestApplication implements ApplicationContextAware {
//or use extends SpringBootServletInitializer
    private static ApplicationContext applicationContext;
    public static void main(String[] args) {
        test0(args);

//        test1();
//        test2();

    }
    public static void test0(String[] args) {
        SpringApplication.run(BeanScopeTestApplication.class, args);
/*
        default DemoCGLIB constructor! 0 -----------------------
                org.nnn4eu.nfische.beanscopetest.config.DemoCGLIBConfig$$EnhancerBySpringCGLIB$$3233731e
        custom constructor AccountService -----------------------
                org.nnn4eu.nfische.beanscopetest.service.AccountService
                jdk.proxy4.$Proxy95
        end of constructor -----------------------
        default constructor default-greeter! -----------------------0
                org.nnn4eu.nfische.beanscopetest.web.model.Greeting
                hello from default-greeter! 1
        default constructor default-greeter! -----------------------0
                org.nnn4eu.nfische.beanscopetest.web.model.Greeting
                hello from Hereto! 1
        custom constructor GreetingService -----------------------
                org.nnn4eu.nfische.beanscopetest.service.GreetingService
                org.nnn4eu.nfische.beanscopetest.web.model.Greeting
                org.nnn4eu.nfische.beanscopetest.web.model.Greeting
        end of constructor -----------------------
                org.nnn4eu.nfische.beanscopetest.service.GreetingService
        end ofPostConstruct setup -----------------------
                custom constructor CalculatorService -----------------------
                org.nnn4eu.nfische.beanscopetest.service.CalculatorService
                org.nnn4eu.nfische.beanscopetest.service.GreetingService
        end of constructor -----------------------
        custom constructor AccountCtrl -----------------------
                org.nnn4eu.nfische.beanscopetest.web.controller.AccountCtrl
                org.nnn4eu.nfische.beanscopetest.service.AccountService
        end of constructor -----------------------
                custom constructor GreetingCtrl -----------------------
                org.nnn4eu.nfische.beanscopetest.web.controller.GreetingCtrl
                org.nnn4eu.nfische.beanscopetest.service.GreetingService
        end of constructor -----------------------
                something() invoked, this.counter: 1
*/
        IAccountService accountService= applicationContext.getBean(IAccountService.class);//IAccountService.class
        System.out.println("accountService class = " + accountService.getClass().getCanonicalName());
//without (interface & transactional) <<< accountService class = org.nnn4eu.nfische.beanscopetest.service.AccountService
//without interface & with transactional <<< accountService class = org.nnn4eu.nfische.beanscopetest.service.AccountService$$EnhancerBySpringCGLIB$$10f54664

        accountService.addAccount(new Account(null,null,"Milka","green"));
//with interface & without transactional <<< accountService class = org.nnn4eu.nfische.beanscopetest.service.AccountService
//with (interface & transactional) <<< accountService class = accountService class = jdk.proxy4.$Proxy100




//        printInterfaces(accountService);
//        printMethods(accountService);

//        printBeans(applicationContext);

    }

    public static void test1() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoCGLIBConfig.class);

//AnnotationConfigApplicationContext - Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@31f924f5
//invoked via DefaultListableBeanFactory >> singleton beans:
//org.springframework.context.annotation.internalConfigurationAnnotationProcessor
//org.springframework.context.event.internalEventListenerProcessor
//org.springframework.context.event.internalEventListenerFactory
//org.springframework.context.annotation.internalAutowiredAnnotationProcessor
//org.springframework.context.annotation.internalCommonAnnotationProcessor
//demoCGLIB << default DemoCGLIB constructor! 0
//org.nnn4eu.nfische.beanscopetest.config.DemoCGLIB$$EnhancerBySpringCGLIB$$47f44106

//something << something() invoked, this.counter: 1
//greetingDefault << default constructor default-greeter! 0  << hello from default-greeter! 1
//greetingHereto << default constructor default-greeter! 0 << hello from Hereto! 1

//Found key 'spring.liveBeansView.mbeanDomain' in PropertySource 'systemProperties' with value of type String

        DemoCGLIBConfig bean = context.getBean(DemoCGLIBConfig.class);
        System.out.println("cglib counter: "+bean.getCounter());
//cglib counter: 1
        bean.something();//this won't print
        System.out.println("this will print: "+bean.something());
//this will print: 1
        System.out.println("cglib counter: "+bean.getCounter());
//cglib counter: 1
        Greeting greeting1=context.getBean("greetingDefault",Greeting.class);
        greeting1.greetMe();
//hello from default-greeter! 2
        greeting1.greetMe("Rony");
//hello from Rony! 3

        Greeting greeting2=context.getBean("greetingHereto",Greeting.class);
        greeting2.greetMe();
//hello from Hereto! 2

    }

    static void test2(){
//        ApplicationContext context = new AnnotationConfigApplicationContext(DemoCGLIBConfig.class, GreetingCtrl.class);

        ApplicationContext context = new AnnotationConfigApplicationContext( BeanScopeTestApplication.class);

//AnnotationConfigApplicationContext - Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@31f924f5
//invoked via DefaultListableBeanFactory >> singleton beans:
//org.springframework.context.annotation.internalConfigurationAnnotationProcessor
//org.springframework.context.event.internalEventListenerProcessor
//org.springframework.context.event.internalEventListenerFactory
//org.springframework.context.annotation.internalAutowiredAnnotationProcessor
//org.springframework.context.annotation.internalCommonAnnotationProcessor
//demoCGLIB << default DemoCGLIB constructor! 0 << org.nnn4eu.nfische.beanscopetest.config.DemoCGLIB$$EnhancerBySpringCGLIB$$47f44106

//something << something() invoked, this.counter: 1
//greetingDefault << default constructor default-greeter! 0 << org.nnn4eu.nfische.beanscopetest.web.model.Greeting << hello from default-greeter! 1

//greetingHereto << default constructor default-greeter! 0 << org.nnn4eu.nfische.beanscopetest.web.model.Greeting << hello from Hereto! 1

//Found key 'spring.liveBeansView.mbeanDomain' in PropertySource 'systemProperties' with value of type String
        DemoCGLIBConfig bean = context.getBean(DemoCGLIBConfig.class);
        System.out.println("cglib counter: "+bean.getCounter());
//cglib counter: 1

//        printBeans(context);
    }

    private static void printBeans(ApplicationContext appContext) {
        System.out.println("getBeanNames-------------------------------");
        System.out.println(Arrays.asList(getBeanNames(appContext)));
        System.out.println("SingletonBeanRegistry-------------------------------");
        System.out.println(Arrays.asList(getSingltonBeanNamess(appContext)));
    }
    private static String[] getBeanNames(ApplicationContext appContext){
        return appContext.getBeanDefinitionNames();
    }
    private static String[] getSingltonBeanNamess(ApplicationContext appContext) {
        AutowireCapableBeanFactory autowireCapableBeanFactory = appContext.getAutowireCapableBeanFactory();
        if (autowireCapableBeanFactory instanceof SingletonBeanRegistry) {
            String[] singletonNames = ((SingletonBeanRegistry) autowireCapableBeanFactory).getSingletonNames();
            for (String singleton : singletonNames) {
                System.out.println(singleton);
            }
            return singletonNames;
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    private static void printInterfaces(Object obj){
        System.out.println("---------printInterfaces for " + obj.getClass());
        for (Class<?> implementedInterface : obj.getClass().getInterfaces()) {
            System.out.println(implementedInterface);
        }
    }
    private static void printMethods(Object obj){
        System.out.println("---------printMethods for " + obj.getClass());
        for (Method method: obj.getClass().getDeclaredMethods()) {
            System.out.println(method.getName());
        }
    }


}
