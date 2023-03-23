package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] strings) {
        Queue<Double> queue = new LinkedList<>();
        int size = 5;
        Thread producer = new Thread(new Producer(queue, size));
        Thread consumer = new Thread(new Consumer(queue));
        consumer.start();
        producer.start();
    }
}