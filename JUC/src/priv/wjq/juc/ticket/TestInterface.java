package priv.wjq.juc.ticket;

public interface TestInterface {

    public void a();

    public void b();

    public void c();



    //这个也可以写多个静态的方法

    public static void d(){

    }

    public static void e(){

    }


    //默认实现的方法可以写多个

    default public void f(){

    }

    default public void g(){

    }


}
