package org.example;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {
    private final Queue<Double> queue;
    private final int max;

    public Producer(Queue<Double> queue, int max_size) {
        this.queue = queue;
        this.max = max_size;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Created: " + produce());
            } catch (InterruptedException e) {
                System.out.println("Exception");
            }
        }
    }

    private double produce() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        synchronized (queue) {
            if (queue.size() == max) {
                queue.wait();
            }
            double temporary = Math.random();
            queue.add(temporary);
            queue.notifyAll();
            return temporary;
        }
    }
}
