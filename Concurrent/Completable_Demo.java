package Concurrent;


import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 描述:CompletableFuture的使用实例
 *     (无非是对上一个结果的处理)
 * 参考：https://segmentfault.com/a/1190000008462088
 */
public class Completable_Demo {
    public static void main(String []args){
       ExecutorService executorService = Executors.newFixedThreadPool(10); 
       
       
       executorService.submit(new Task());
       executorService.submit(new Task());
       
       //supplyAsync初始池，操作
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("First step:返回 zero");
            return "zero";
        }, executorService);      //这里先完成executorService中的线程，接着执行自己的线程
        
        
        CompletableFuture.supplyAsync(new Supplier<String>() {

           @Override
           public String get() {
              System.out.println("我是默认的方法！");
              return "over";
           }
       }
        );
        
       //thenApply相当于回调方法(既接受上一个返回值，同样当前的线程也有放回值)
        CompletableFuture<Integer> f2 = f1.thenApply(new Function<String, Integer>() {

            @Override
            public Integer apply(String t) {
                System.out.println("step 1结果:"+t);
                System.out.println("step 2:返回2");
                return 2;
            }
        });
        //thenAccept方法只接受上一个返回值，当前方法却没有返回值
        f2.thenAccept(new Consumer<Integer>(){

           @Override
           public void accept(Integer t) {
               System.out.println("接收上一个执行返回的结果"+t);
           }
        
        });
        
        
        CompletableFuture<Double> f3 = f2.thenApply(new Function<Integer, Double>(){

           @Override
           public Double apply(Integer t) {
               System.out.println("Step  3");
               return 3.0;
           }
        
        });
        
        
        //监听f2的完成
        f2.thenRun(new Runnable(){

           @Override
           public void run() {
               System.out.println("Step  2完成");
           }
        
        });

    
    
    }
}
class Task implements Callable<String>{
    private static volatile int i = 1;
    @Override
    public String call() throws Exception {
        System.out.println("我是call方法"+i++);
        return "nice!";
    }

}
