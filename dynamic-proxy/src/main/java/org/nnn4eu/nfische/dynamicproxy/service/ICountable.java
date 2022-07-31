package org.nnn4eu.nfische.dynamicproxy.service;


import org.nnn4eu.nfische.dynamicproxy.model.Counter;

public interface ICountable {

    String countStart(Counter counter);

    String countStop(Counter counter);
    String countPause(Counter counter);


    String countResetStep(Counter counter, int step);
    String countResetFinish(Counter counter, int finish);
}
