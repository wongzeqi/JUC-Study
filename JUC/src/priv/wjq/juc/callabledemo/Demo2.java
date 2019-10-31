package priv.wjq.juc.callabledemo;



import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//demo2 实现工程成本核算
public class Demo2 {

    //组合式异步编程
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //主线程计算总和
        CallableImpl callable1 = new CallableImpl();
        callable1.setProductName("人工");
        FutureTask<Double> doubleFutureTask1 = new FutureTask<>(callable1);
        new Thread(doubleFutureTask1).start();

        CallableImpl callable2 = new CallableImpl();
        callable2.setProductName("材料");
        FutureTask<Double> doubleFutureTask2 = new FutureTask<>(callable2);
        new Thread(doubleFutureTask2).start();

        Double d1 = doubleFutureTask1.get();

        Double d2 = doubleFutureTask2.get();

        System.out.println("人工成本为:"+d1);

        System.out.println("材料成本为:"+d2);

        System.out.println("总成本为:"+(d2+d1));

    }

}

//计算人工成本
class CallableImpl implements Callable<Double>{
    public String productName;
    @Override
    public Double call() throws Exception {
        if(productName.equals("人工")){
            //Thread.sleep(2000);
            System.out.println("人工成本计算完成....");
            return 12.0d;
        }else{
            //Thread.sleep(3000);
            System.out.println("材料成本计算完成....");
            return 120.0d;
        }

    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}





