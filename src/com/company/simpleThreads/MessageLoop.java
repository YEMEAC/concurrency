package com.company.simpleThreads;


import static com.company.simpleThreads.SimpleThreads.threadMessage;

public  class MessageLoop implements Runnable {
    public void run() {
        String importantInfo[] = {"FIRST MESSAGE", "SECOND MESSAGE", "THIRD MESSAGE", "FORTH MESSAGE"};
        try {
            for (int i = 0; i < importantInfo.length; i++) {
                Thread.sleep(1000);
                threadMessage(importantInfo[i]);
            }
        } catch (InterruptedException e) {
            threadMessage("I wasn't done!");
        }
        threadMessage("I finishing");
    }
}