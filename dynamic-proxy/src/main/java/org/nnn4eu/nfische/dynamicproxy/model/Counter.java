package org.nnn4eu.nfische.dynamicproxy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Counter {

    public Integer getSize(){
        return (end - start);
    }
    private Integer start;
    private Integer end;
    private Integer current;
    private Integer step;
    private Long threadId;
    private String name;
    private Boolean doStop;

    public String isRunning(){
        for (Thread t : Thread.getAllStackTraces().keySet()){
            if(t.getId()==threadId) return t.isAlive()+"";
        }
        return "thread-not-found";
    }

    private boolean keepRunning() {
        if(!doStop) doStop=(current+step)>end;
        return (doStop == false);
    }


    @Override
    public String toString(){
        return "name: "+name+", doStop: "+doStop+", threadId: "+threadId+", size: "+getSize()+", start: "+start+", end: "+end+", current: "+current+", step: "+step;
    }

    public void count() {
        doStop = false;
        while(keepRunning()){
            print();
        }
        doStop = true;
    }

    private void print() {
        current=current+step;
        threadId=Thread.currentThread().getId();
        System.out.println(name+" - "+threadId+", "+current);
    }

    public void pause() {
        doStop = true;
    }
    public void stop() {
        current=start-1;
        doStop = true;
    }
}
