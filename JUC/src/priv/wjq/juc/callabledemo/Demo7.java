package priv.wjq.juc.callabledemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
public class Demo7 {
    private static final int NUMBER = 7;
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER,()->{
            System.out.println("集齐七颗龙珠就可以召唤神龙...");
        });
        for (int i = 1; i <= 7 ; i++) {
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"龙珠收集中..");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
