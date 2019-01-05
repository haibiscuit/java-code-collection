package ByteBuffer;

import java.nio.ByteBuffer;
/**
 * 
 * @ClassName:  ByteBufferTest   
 * @Description:ByteBuffer的基本使用   
 * @author: 申梦杰 
 * @date:   2019年1月5日 下午4:25:15
 * @version 1.8.0   
 * @param    
 *
 */
/**
 * 描述：对bytebuffer的测试(
 *         主要方法：在读时flip(),remaining()方法很重要，在写时，clear()，compact()很重要
 *                  1.wrap()
 *                  2.mark()
 *                  3.compact()
 *                  4.flip()
 *                  5.rewind()    :应该是只读方法，因为在向里面写数据时会报错
    *               6.clear()   
 * )
 * 
 */
/**
 * 总结ByteBuffer的方法:
 *                    1. rewind()方法，更干脆，直接清除mark。
                         flip()方法，也清除mark
                         clear()方法，也清除mark
                         compact方法，也清除mark
                         * 
                      (2.3中其实都是可读可写方法，只是养成好的编程习惯)
                      2.将读转换成写的方法
                      * clear():不会将position后没有读完的数据拷贝到bytebuffer的开头,position=0,limit=capacity                
                      * compact():会将没有读完的数据拷贝到ByteBuffer的开头,positon移到数据的最后,limit=capacity
                      * rewind(): 同clear()方法，不同的是limit不变(即在读的时候,limit在数据的最后)
                      
                      3.将写转换成读
                      * flip():  limit = position,position = 0
                      * 
                      * 
                      4.重点，使用get方法时不要指定byte [] = new byte[size];不要指定size的大小，以防止buffer中读取不到size长度的数据抛出异常
                      * (原因是,在使用get方法时，position在向后移动，如果超出limit的大小,则会抛出异常)
 */
public class ByteBufferTest {
	 public static void main(String []args){
	        
	        System.out.println("测试getInt(0)和getInt的区别");
	        ByteBuffer buff1 = ByteBuffer.allocate(1024);
	        buff1.putInt(10);
	        buff1.flip();
	       
	        int a = buff1.getInt(0);    //getInt(0)只会读取一个整数字节(4子节点的长度，position并不会移动)
	        System.out.println("getInt(0)方法  "+"position"+buff1.position()+"        limit"    +buff1.limit()+"        capacity"+buff1.capacity());
	        buff1.getInt();    //getInt();会移动指针
	        System.out.println("getInt()方法  "+"position"+buff1.position()+"        limit"    +buff1.limit()+"        capacity"+buff1.capacity());
	        System.out.println(a);        

	        
	        
	        String str = "helloWorld";  
	        ByteBuffer buff  = ByteBuffer.wrap(str.getBytes());  
	        System.out.println("position:"+buff.position()+"\t limit:"+buff.limit()+"\t remaining...."+buff.remaining());  
	        //读取两个字节  
	        byte[] abytes = new byte[1];
	        buff.get(abytes);  
	        System.out.println("get one byte to string:" + new String(abytes));
	        //Reads the byte at this buffer's current position, and then increments the position.
	        buff.get();  
	        System.out.println("获取两个字节（两次get()方法调用）后");
	        System.out.println("position:"+buff.position()+"\t limit:"+buff.limit()); 
	        //Sets this buffer's mark at its position. like ByteBuffer.this.mark=position
	        buff.mark();  
	        System.out.println("mark()...");
	        System.out.println("position:"+buff.position()+"\t limit:"+buff.limit()+"\t remaining...."+buff.remaining());  
	        
	        buff.compact();
	        System.out.println("compact()...");
	        System.out.println("position:"+buff.position()+"\t limit:"+buff.limit()+"\t remaining...."+buff.remaining());
	        
	        
	        
	        buff.flip();  
	        System.out.println("flip()...");
	        System.out.println("position:"+buff.position()+"\t limit:"+buff.limit());
	        
	        buff.put("flip".getBytes());
	        buff.flip();
	        byte []a4 = new byte[buff.remaining()];   //一般这里不会放置准确的数据
	        buff.get(a4);
	        System.out.println("flip后输出全部数据" + new String(a4));  
	        
	        buff.flip();
	        byte []a1 = new byte[buff.remaining()];
	        buff.get(a1);
	        System.out.println("flip后输出全部数据" + new String(a1));
	        
	        buff.rewind();
	        System.out.println("rewind()...");
	        System.out.println("position:"+buff.position()+"\t limit:"+buff.limit()+"\t remaining...."+buff.remaining());
	        buff.put("12".getBytes());
	        buff.flip();
	        
	        byte []a2 = new byte[buff.remaining()];
	        buff.get(a2);
	        System.out.println("rewind后输出全部数据" + new String(a2));

	       
	     
	        
	        buff.clear();
	        System.out.println("clear()...");
	        System.out.println("position:"+buff.position()+"\t limit:"+buff.limit()+"\t remaining...."+buff.remaining());
	        System.out.println("remaining....."+buff.remaining());
	        
	        buff.put("clear".getBytes());
	        buff.flip();
	        byte []a3 = new byte[buff.remaining()];   //一般这里不会放置准确的数据
	        buff.get(a3);
	        System.out.println("clear后输出全部数据" + new String(a3));   
	    }
}
