package com.davena.exercises.Thread;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Test {



    static class Producer extends Thread {
        Queue queue;
        int maxqueuesize=10;

        public Producer (Queue <Integer> queue) {
            this.queue=queue;
        }

        @Override
        public void run() {
            int i=0;
            while(!isInterrupted()) {
                // queue.add(i++); on è un operazione atomica, quindi può creare dei problemi, per questo si usano delle operazioni safe

                synchronized (queue) {
                    if (queue.size()<maxqueuesize) {
                        System.out.println("Producer: " +queue.add(i++));
                        queue.notifyAll();
                    }else {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {

                        }
                    }
                }
            }
        }
    }
    static class Consumer extends Thread{
        Queue queue;
        public Consumer (Queue <Integer> queue) {
            this.queue=queue;
        }

        @Override
        public void run() {
            while(!isInterrupted()) {
                // queue.remove(); on è un operazione atomica, quindi può creare dei problemi, per questo si usano delle operazioni safe
                synchronized (queue){ // previene facendo il lock
                    if(queue.size()>0) {
                        System.out.println("Consumer: head of the queue =" + queue.remove());
                        queue.notifyAll();
                    } else {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //Queue queue=new LinkedList(); // è unsafe

        int poolSize = Runtime.getRuntime().availableProcessors();
        System.out.println(poolSize);
        //per vedere quanti thread ho a disposizione


        Queue queue=new ConcurrentLinkedQueue(); //questa è safe quindi bisogna usare questa
        Thread a=new Producer(queue);
        Thread b=new Consumer(queue);

        a.start();
        b.start();

        Thread.sleep(10);

        a.interrupt();
        b.interrupt();

        a.join();
        b.join();
    }
}
