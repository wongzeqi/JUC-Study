package priv.wjq.juc.callabledemo;
import java.util.concurrent.CountDownLatch;
public class Demo6 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("负责锁门的同学正在观察...");
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10 ; i++) {
            new Thread(()->{
                System.out.println("同学"+Thread.currentThread().getName()+"离开了教室...");
                countDownLatch.countDown();
            },""+i).start();
        }
        countDownLatch.await();
        System.out.println("负责锁门的同学把门锁上...");
    }
}
