package priv.wjq.juc.callabledemo;

import org.junit.Test;

public class Demo3 {
    public static void main(String[] args) {
        TestPrint tp = new TestPrint();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                tp.print0();
            }
        },"打印0的线程").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                tp.print1();
            }
        },"打印1的线程").start();
    }


    @Test
    public void test2() {
        TestPrint tp = new TestPrint();

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





class TestPrint{

    public  static  int num = 0;

    public synchronized void print0(){
        //if (num==0){
        while (num==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num--;
        System.out.println("线程"+Thread.currentThread().getName()+"打印" +num);
        this.notifyAll();
    }

    public synchronized void print1(){
        //if (num==1){
        while(num==1){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num++;
        System.out.println("线程"+Thread.currentThread().getName()+"打印" +num);
        this.notifyAll();
    }
}