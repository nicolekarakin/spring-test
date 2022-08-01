package org.nnn4eu.nfische.beanscopetest.web.model;


public class Greeting {
    private int greetingCount =0;
    private String greeter="default-greeter";
    public Greeting greetMe(String str){
        greeter=str; greetingCount++;
        System.out.println(this.toString2());
        return this;
    }
    public Greeting greetMe(){
        greetingCount++;
        System.out.println(this+", "+this.toString2());
        return this;
    }
    public Greeting(){
        System.out.println("default constructor "+greeter+"! -----------------------"+ greetingCount);
        System.out.println(this.getClass().getTypeName());
    }

    public String toString2() {
        return "hello from "+greeter+"! "+ greetingCount;
    }
}
