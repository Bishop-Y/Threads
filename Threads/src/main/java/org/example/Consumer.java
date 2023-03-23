package org.example;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable{
    private final Queue<Double> queue;
    public Consumer(Queue<Double> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Consumed: " + consume());
            } catch (InterruptedException ex) {
                System.out.println("Exception");
            }
        }
    }

    private Double consume() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        synchronized (queue) {
            if (queue.isEmpty()) {
                queue.wait();
            }

            queue.notifyAll();
            return queue.poll();
        }
    }
}
