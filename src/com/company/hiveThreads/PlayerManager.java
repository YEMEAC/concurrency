package com.company.hiveThreads;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.company.hiveThreads.Utils.threadMessage;

public class PlayerManager {

    public static void main(String args[]) throws InterruptedException {

        int numberFragmentRequest = 100;
        List<Thread> fragmentRequests = new ArrayList<>();
        StreamStatus stream = new StreamStatus(UUID.randomUUID().toString());

        for (int i = 0; i < numberFragmentRequest; ++i)
            fragmentRequests.add(new Thread(new FragmentRequestSimulation(stream)));

        for (int i = 0; i < fragmentRequests.size(); ++i) {
            fragmentRequests.get(i).start();
        }

        for (int i = 0; i < fragmentRequests.size(); ++i) {
            fragmentRequests.get(i).join();
        }

        threadMessage("All fragment request delivered!");
        threadMessage("Number fragments received " + stream.getCounterFragmentsReceived());
    }

    private static boolean anyAlive(List<Thread> fragmentRequests) {
        for (int i = 0; i < fragmentRequests.size(); ++i)
            if (fragmentRequests.get(i).isAlive())
                return true;
        return false;
    }
}