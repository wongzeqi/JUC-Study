package priv.wjq.juc.callabledemo;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo4 {



    @Test
    public void test2() {
        TestPrint3 tp = new TestPrint3();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                tp.print0();
            }
        },"打印0的线程1").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                tp.print1();
            }
        },"打印1的线程1").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                tp.print0();
            }
        },"打印0的线程2").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                tp.print1();
            }
        },"打印1的线程2").start();
    }
}


class TestPrint4{

    public  static  int num = 0;
    Lock lock = new ReentrantLock();
    public void print0() {
        //if (num==0){
        lock.lock();
        while (num == 0) {
            try {
                this.wait();
                num--;
                System.out.println("线程" + Thread.currentThread().getName() + "打印" + num);
                this.notifyAll();
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }

        }
    }

    public  void print1(){
        Lock lock = new ReentrantLock();
        lock.lock();
        while (num == 1) {
            try {
                this.wait();
                num++;
                System.out.println("线程" + Thread.currentThread().getName() + "打印" + num);
                this.notifyAll();
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }

        }
    }
}


class TestPrint3{

    public  static  int num = 0;
    Lock lock = new ReentrantLock();
    final Condition condition0 = lock.newCondition();
    final Condition condition1 = lock.newCondition();
    public void print0() {
        lock.lock();
        while (num == 0) {
            try {
                condition0.await();
                num--;
                System.out.println("线程" + Thread.currentThread().getName() + "打印" + num);
                condition1.signal();
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }

        }
    }

    public  void print1(){

        lock.lock();
        while (num == 1) {
            try {
                condition1.await();
                num++;
                System.out.println("线程" + Thread.currentThread().getName() + "打印" + num);
                condition0.signal();
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }

        }
    }
}