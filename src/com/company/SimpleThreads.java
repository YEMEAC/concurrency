package com.company;


public class SimpleThreads {

    static void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.format("%s: %s%n", threadName, message);
    }

    private static class MessageLoop implements Runnable {
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

    public static void main(String args[]) throws InterruptedException {

        long timeout = 3000;

        if (args.length > 0) {
            try {
                timeout = Long.parseLong(args[0]) * 1000;
            } catch (NumberFormatException e) {
                System.err.println("Argument must be an integer.");
                System.exit(1);
            }
        }

        threadMessage("Starting MessageLoop thread");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();

        threadMessage("Waiting for MessageLoop thread to finish");

        //loop until child thread finish our timeout is reached
        while (t.isAlive()) {
            threadMessage("Still waiting...");
            t.join(1000);
            if (((System.currentTimeMillis() - startTime) > timeout) && t.isAlive()) {
                threadMessage("Tired of waiting! Im going to stop you");
                t.interrupt();
                //t.join(); // -- wait indefinitely
            }
        }
        threadMessage("Finally!");
    }
}