package com.company.hiveThreads;

import java.util.Random;

import static com.company.hiveThreads.Utils.threadMessage;

public class FragmentRequestSimulation implements Runnable {

    StreamStatus streamStatus;

    FragmentRequestSimulation(StreamStatus streamStatus) {
        this.streamStatus = streamStatus;
    }

    public void run() {
        Random r = new Random();
        int sleepTime = r.nextInt((3 - 3) + 1) + 3;
        try {
            Thread.sleep(sleepTime*1000);
            streamStatus.lastFragmentId = sleepTime;
            streamStatus.increaseFragmentCounter();
        } catch (InterruptedException e) {
            threadMessage("I wasn't done!");
        }
    }
}