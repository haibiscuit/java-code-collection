package Collection;
/**
 * 介绍了四种List的遍历方法
 * 
 * 各遍历方式的适用于什么场合？
1、传统的for循环遍历，基于计数器的：
        顺序存储：读取性能比较高。适用于遍历顺序存储集合。
        链式存储：时间复杂度太大，不适用于遍历链式存储的集合。
2、迭代器遍历，Iterator：
        顺序存储：如果不是太在意时间，推荐选择此方式，毕竟代码更加简洁，也防止了Off-By-One的问题。
        链式存储：意义就重大了，平均时间复杂度降为O(n)，还是挺诱人的，所以推荐此种遍历方式。
3、foreach循环遍历：
        foreach只是让代码更加简洁了，但是他有一些缺点，就是遍历过程中不能操作数据集合（删除等），
                    所以有些场合不使用。而且它本身就是基于Iterator实现的，但是由于类型转换的问题，所以会比直接使用Iterator慢一点，
                   但是还好，时间复杂度都是一样的。所以怎么选择，参考上面两种方式，做一个折中的选择。
 */
import java.util.ArrayList;
import java.util.Iterator;

public class ListForeach_demo1 {
	public static void main(String args[]){
		ArrayList<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		//List遍历方式：
		//第一种：
		Iterator<String> iterator = list.iterator();
		for(;iterator.hasNext();){                    
		    String i = (String)iterator.next();                   
		    System.out.println(i);               
		}
		//第二种：
		Iterator<String> iterator1 = list.iterator();
		while(iterator1.hasNext()){
		    String i = (String) iterator1.next();
		    System.out.println(i);
		}
		//第三种：
		for (Object object : list) { 
		    System.out.println(object); 
		}
		//第四种：
		for(int i = 0 ;i<list.size();i++) {  
		    String j=  list.get(i);
		    System.out.println(j);  
		}
	}
}
