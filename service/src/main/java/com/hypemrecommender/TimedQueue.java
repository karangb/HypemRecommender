package com.hypemrecommender;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: @karangb
 * Date: 01/12/2013
 * Time: 18:36
 */
public class TimedQueue<T> extends LinkedList<T>{
    private final int delay;
    private long lastPollTime;

    public TimedQueue(int delay)
    {
        this.delay = delay;
        lastPollTime = 0;
    }

    @Override
    public T poll() {
        long needToWait = delay - (System.currentTimeMillis() - lastPollTime);
        if(needToWait > 0)
        {
            try {
                Thread.sleep(needToWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lastPollTime = System.currentTimeMillis();
        return super.poll();
    }
}
