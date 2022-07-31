package org.nnn4eu.nfische.dynamicproxy.service;

import org.nnn4eu.nfische.dynamicproxy.framework.TransactionalService;
import org.nnn4eu.nfische.dynamicproxy.model.Counter;

@TransactionalService
public class CounterServiceImpl implements ICountable,IJumpable{

    @Override
    public String countStart(Counter counter) {
        counter.count();
        return counter.toString();
    }
    @Override
    public String countStop(Counter counter) {
        counter.stop();
        return counter.toString();
    }

    @Override
    public String countPause(Counter counter) {
        counter.pause();
        return counter.toString();
    }

    @Override
    public String countJump(Counter counter, int current) {
        counter.setCurrent(current);
        if(counter.getDoStop())
            counter.count();
        return counter.toString();
    }

    @Override
    public String countResetStep(Counter counter, int step) {
        counter.setStep(step);
        if(counter.getDoStop())
            counter.count();
        return counter.toString();
    }

    @Override
    public String countResetFinish(Counter counter, int finish) {
        counter.setEnd(finish);
        if(counter.getDoStop())
            counter.count();
        return counter.toString();
    }


}
