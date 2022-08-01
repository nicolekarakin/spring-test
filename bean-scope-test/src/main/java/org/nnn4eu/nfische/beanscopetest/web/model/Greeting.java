package org.nnn4eu.nfische.beanscopetest.web.model;


public class Greeting {
    private int greetingCount =0;
    private String greeter="default-greeter";
    public Greeting greetMe(String str){
        greeter=str; greetingCount++;
        System.out.println(this.toString());
        return this;
    }
    public Greeting greetMe(){
        greetingCount++;
        System.out.println(this.getClass()+", "+this.toString());
        return this;
    }
    public Greeting(){
        System.out.println("default constructor "+greeter+"! -----------------------"+ greetingCount);
        System.out.println(this.getClass().getTypeName());
    }
    @Override
    public String toString() {
        return "hello from "+greeter+"! "+ greetingCount;
    }
}
