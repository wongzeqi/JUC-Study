package priv.wjq.juc.callabledemo;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Demo5 {

    public static void main(String[] args) {
        Cooker cooker = new Cooker();
        new Thread(()->{
            for (int j = 0; j < 5; j++) {
                cooker.wash();
            }
        },"王师傅").start();
        new Thread(()->{
            for (int j = 0; j < 5; j++) {
                cooker.cooking();
            }
        },"李大妈").start();
        new Thread(()->{
            for (int j = 0; j <5 ; j++) {
                cooker.take();
                System.out.println("第"+(j+1)+"道菜完成...");
            }

        },"张师傅").start();
    }
}

class Cooker{//资源类

    //public int i = 10;
    public String status = "wash";

    Lock lock = new ReentrantLock();
    Condition condition_wash = lock.newCondition();
    Condition condition_cooking = lock.newCondition();
    Condition condition_take = lock.newCondition();


    public void wash(){
        lock.lock();
        try {
            while(!status.equals("wash")) {
                try {
                    condition_wash.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"菜已经洗完，请炒菜师傅炒菜...");
            status = "cooking";
            condition_cooking.signal();
        }finally {
            lock.unlock();
        }
    }

    public void cooking(){

        lock.lock();
        try {
            while(!status.equals("cooking")) {
                try {
                    condition_cooking.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"菜已经炒完，请端菜师傅端菜...");
            status = "take";
            condition_take.signal();
        }finally {
            lock.unlock();
        }

    }

    public void take(){
        lock.lock();
        try {
            while(!status.equals("take")) {
                try {
                    condition_take.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"菜已经端完，请洗菜师傅洗菜...");
            status = "wash";
            condition_wash.signal();
        }finally {
            lock.unlock();
        }
    }
}
