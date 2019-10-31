package priv.wjq.juc.ticket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyTicket {
    public static int ticketNumber = 10;


    //方式1
    //访问公共资源的方法
    public void sale(){
        ticketNumber--;
        System.out.println(Thread.currentThread().getName()+"卖出一张票 剩余"+ticketNumber+"张");
    }

    //方式2
    //将访问公共资源的代码上锁
    public synchronized void sale2(){//自动锁
        ticketNumber--;
        System.out.println(Thread.currentThread().getName()+"卖出一张票 剩余"+ticketNumber+"张");
    }


    //方式3
    Lock lock = new ReentrantLock();

    //使用juc可重入锁
    public void sale3(){
        lock.lock();
        try {
            ticketNumber--;
            System.out.println(Thread.currentThread().getName()+"卖出一张票 剩余"+ticketNumber+"张");
        }finally {
            lock.unlock();
        }
    }



    public static int getTicketNumber() {
        return ticketNumber;
    }

    public static void setTicketNumber(int ticketNumber) {
        MyTicket.ticketNumber = ticketNumber;
    }

}
