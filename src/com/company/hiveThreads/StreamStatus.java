package com.company.hiveThreads;


import static com.company.hiveThreads.Utils.threadMessage;

public class StreamStatus {

    String id;
    int lastFragmentId;
    double counterFragmentsReceived;

    StreamStatus(String id) {
        this.id = id;
    }

    public int getLastFragmentId() {
        return lastFragmentId;
    }

    public double getCounterFragmentsReceived() {
        return counterFragmentsReceived;
    }

    public void setLastFragmentId(int lastFragmentId) {
        this.lastFragmentId = lastFragmentId;
    }

    public void setCounterFragmentsReceived(double counterFragmentsReceived) {
        this.counterFragmentsReceived = counterFragmentsReceived;
    }

    //without syncronized the threads would interfere and have another value
    public synchronized void increaseFragmentCounter() {
        threadMessage("Current value " + counterFragmentsReceived);

        for (int i = 0; i < 10000; i++) { //simulate work
            this.setCounterFragmentsReceived(this.counterFragmentsReceived + 1);
        }
        threadMessage("After value " + counterFragmentsReceived);
    }
}
