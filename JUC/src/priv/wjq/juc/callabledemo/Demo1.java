package priv.wjq.juc.callabledemo;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Demo1 {

    public static void main(String[] args) {

//        new Thread(new Callable<>() {
//            @Override
//            public void call() {
//
//            }
//        }).start();

        //这样不行的话


        //通过FutureTask  避免传统的runable 的局限性


        //call 方法  相对 run 方法来说 多一个泛型  以及多一个返回值 这就是其中的扩展

        //String subThreadMessage = null;

        //callable
        //可以和主线程通过用户自定义的操作来实现数据的交互
        //实现call方法  可以获得线程call的执行结果

        new Thread(new FutureTask<String>(()->{
            return "HELLO";
        })).start();




    }

}
