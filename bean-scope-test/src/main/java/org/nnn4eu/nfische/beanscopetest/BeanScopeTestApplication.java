package org.nnn4eu.nfische.beanscopetest;

import org.nnn4eu.nfische.beanscopetest.components.MyComponent;
import org.nnn4eu.nfische.beanscopetest.config.DemoCGLIBConfig;
import org.nnn4eu.nfische.beanscopetest.config.DemoCGLIBConfig1;
import org.nnn4eu.nfische.beanscopetest.service.IAccountService;
import org.nnn4eu.nfische.beanscopetest.web.model.Greeting;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//@EnableCaching(proxyTargetClass = true)
//@EnableTransactionManagement(proxyTargetClass = false)
@SpringBootApplication
public class BeanScopeTestApplication implements ApplicationContextAware {
//or use extends SpringBootServletInitializer
    private static ApplicationContext applicationContext;
    public static void main(String[] args) {
//        test0(args);
        test1();
//        test2();
//        test3();

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

//        accountService.addAccount(new Account(null,null,"Milka","green"));
//with interface & without transactional <<< accountService class = org.nnn4eu.nfische.beanscopetest.service.AccountService
//with (interface & transactional) <<< accountService class = accountService class = jdk.proxy4.$Proxy100



//        printInterfaces(accountService);
//        printMethods(accountService);

//        printBeans(applicationContext);
//          printFilteredBeans(applicationContext, "$");
//        printProxiedBeans(applicationContext);
//        printConfigBeans(applicationContext);
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
        System.out.println("retrieved greeting1 default bean: "+System.identityHashCode(greeting1));
        greeting1.greetMe();
//hello from default-greeter! 2
        greeting1.greetMe("Rony");
//hello from Rony! 3

        Greeting greeting12=context.getBean("greetingDefault",Greeting.class);
        System.out.println("retrieved greeting12 default bean: "+System.identityHashCode(greeting12));
        greeting12.greetMe();
//hello from default-greeter! 2
        greeting12.greetMe("Rony");
//hello from Rony! 3

        System.out.println("greeting12.equals(greeting1): "+greeting12.equals(greeting1));//false
//=========================================================
        Greeting greeting2=context.getBean("greetingHereto",Greeting.class);
        System.out.println("retrieved greeting2 default bean: "+System.identityHashCode(greeting2));
        greeting2.greetMe();
//hello from Hereto! 2

        Greeting greeting22=context.getBean("greetingHereto",Greeting.class);
        System.out.println("retrieved greeting22 default bean: "+System.identityHashCode(greeting22));
        greeting22.greetMe();
        greeting22.greetMe("rrrrr");

        System.out.println("greeting2.equals(greeting22): "+greeting2.equals(greeting22));//true
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

    public static void test3() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoCGLIBConfig1.class);
        MyComponent myComponent=context.getBean("myComponent",MyComponent.class);
        System.out.println("MyComponent class typeName-----"+myComponent.getClass().getTypeName());
        System.out.println("MyComponent--------------------"+myComponent);
        myComponent.doSome("myComponent");
//        =============================
        System.out.println("\nmethods declared on proxy of MyComponent----------------------------------");
        for (Method method: myComponent.getClass().getDeclaredMethods()) {
            System.out.println(method.toString().split(".components.")[1]);
        }
        System.out.println("----------------------------------");
//        =============================
        DemoCGLIBConfig1 bean = context.getBean(DemoCGLIBConfig1.class);
//        System.out.println("cglib counter: "+bean.getCounter()); //cglib counter: 1
        String res=bean.something2();//this won't print but returns string <<< DemoCGLIBConfig1.counter is not increased!!!
        System.out.println(res);


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
    private static void printFilteredBeans(ApplicationContext appContext,String filter) {
        String[]arr1= getBeanTypeNames(appContext);
        List<String> list1=Arrays.stream(arr1)
                .filter(a->a.contains(filter))
                .sorted().collect(Collectors.toList());
        System.out.println("getBeanNames-------------------------------");
        System.out.println(Arrays.toString(list1.toArray()).replace(",","\n"));
        System.out.println("end getBeanNames-------------------------------");

        String[]arr2= getSingltonBeanTypeNamess(appContext);
        List<String> list2=Arrays.stream(arr2)
                .filter(a->a.contains(filter))
                .sorted().collect(Collectors.toList());
        System.out.println("getSingltonBeanNamess-------------------------------");
        System.out.println(Arrays.toString(list2.toArray()).replace(",","\n"));
        System.out.println("getSingltonBeanNamess-------------------------------");
    }
    private static void printBeans(ApplicationContext appContext) {
        System.out.println("getBeanNames-------------------------------");
        System.out.println(Arrays.asList(getBeanTypeNames(appContext)));
        System.out.println("SingletonBeanRegistry-------------------------------");
        System.out.println(Arrays.asList(getSingltonBeanTypeNamess(appContext)));
    }
    private static String[] getBeanTypeNames(ApplicationContext appContext){
        String[]arr=appContext.getBeanDefinitionNames();
        String[]res=new String[arr.length];
        for(int i=0;i<arr.length;i++){
            Class cl=appContext.getBean(arr[i]).getClass();
            if(cl==null || cl.getTypeName()==null) System.out.println(appContext.getBean(arr[i])+", class or typename is null");
            else res[i]=cl.getTypeName();
        }
        return res;
    }
    private static String[] getSingltonBeanTypeNamess(ApplicationContext appContext) {
        AutowireCapableBeanFactory autowireCapableBeanFactory = appContext.getAutowireCapableBeanFactory();
        if (autowireCapableBeanFactory instanceof SingletonBeanRegistry) {
            String[] singletonNames = ((SingletonBeanRegistry) autowireCapableBeanFactory).getSingletonNames();
            String[] singletonCannonicalClassNames=new String[singletonNames.length];
            int i=0;
            for (String singleton : singletonNames) {
//                System.out.println(singleton);
                Object ob=((SingletonBeanRegistry) autowireCapableBeanFactory).getSingleton(singleton);

                if(ob==null || ob.getClass().getTypeName()==null) System.out.println(ob+", class or typename is null");
                else singletonCannonicalClassNames[i]=ob.getClass().getTypeName();
                i++;
            }
//            return singletonNames;
            return singletonCannonicalClassNames;
        }
        return null;
    }
    private static void printConfigBeans(ApplicationContext appContext) {

        System.out.println("printConfigBeans-------------------------------\n"+
                Arrays.asList(getConfigBeanDescriptorString(appContext)));
    }
    private static String[] getConfigBeanDescriptorString(ApplicationContext appContext){
        String[]arr=appContext.getBeanNamesForAnnotation(Configuration.class);

        List<String>res=new ArrayList<String>();
        for(int i=0;i<arr.length;i++){
            Class cl=appContext.getBean(arr[i]).getClass();
            if(cl==null || cl.getTypeName()==null) System.out.println(appContext.getBean(arr[i])+", not proxy or class or typename is null");
            else res.add(cl.descriptorString());
        }
        return res.toArray(new String[0]);
    }
    private static void printProxiedBeans(ApplicationContext appContext) {

        System.out.println("getDynamicProxys-------------------------------\n"+
                Arrays.asList(getBeanProxyDescriptorString(appContext,"dynamic")));

        System.out.println("getCGLIBproxys-------------------------------\n"+
                Arrays.asList(getBeanProxyDescriptorString(appContext,"cglib")));
    }
    private static String[] getBeanProxyDescriptorString(ApplicationContext appContext, String proxytype){
        String[]arr=appContext.getBeanDefinitionNames();

        List<String>res=new ArrayList<String>();
        for(int i=0;i<arr.length;i++){
            Class cl=appContext.getBean(arr[i]).getClass();
            boolean isProxy=false;
            if("dynamic".equals(proxytype))isProxy=classDynamicProxy(cl);
            if("cglib".equals(proxytype))isProxy=classCGLIBProxy(cl);
            if(!isProxy)continue;
            if(cl==null || cl.getTypeName()==null) System.out.println(appContext.getBean(arr[i])+", not proxy or class or typename is null");
            else res.add(cl.descriptorString());
        }
        return res.toArray(new String[0]);
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }


    public static boolean classDynamicProxy(Class a) {
        return (java.lang.reflect.Proxy.isProxyClass(a));
    }
    public static boolean classCGLIBProxy(Class a) {
        return (org.springframework.cglib.proxy.Proxy.isProxyClass(a));
    }

    public static boolean classIsProxy(Object o){
        return AopUtils.isAopProxy(o);
//        return (object instanceof SpringProxy && (Proxy.isProxyClass(object.getClass()) ||
//                object.getClass().getName().contains(ClassUtils.CGLIB_CLASS_SEPARATOR)));

    }
}
