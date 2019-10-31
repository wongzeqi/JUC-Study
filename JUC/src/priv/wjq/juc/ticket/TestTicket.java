package priv.wjq.juc.ticket;

import org.junit.Test;

public class TestTicket {

    @Test
    public void test1(){
        MyTicket ticket = new MyTicket();
        for (int i = 0; i <10 ; i++) {
            //接口中只声明有一个方法  称之为函数式接口
            new Thread(()->{
                ticket.sale();
            }).start();//线程操作资源类
        }
    }

    @Test
    public void test2(){
        MyTicket ticket = new MyTicket();
        for (int i = 0; i <10 ; i++) {
            //接口中只声明有一个方法  称之为函数式接口
            new Thread(()->{
                ticket.sale2();
            }).start();//线程操作资源类
        }
    }


    @Test
    public void test3(){
        MyTicket ticket = new MyTicket();
        for (int i = 0; i <10 ; i++) {
            //接口中只声明有一个方法  称之为函数式接口
            new Thread(()->{
                ticket.sale3();
            }).start();//线程操作资源类
        }
    }
}
