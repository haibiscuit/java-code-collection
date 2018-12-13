package IO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
/**
 * 流的关闭原则： 先打开的后关闭
 *            先外再内（比如先关闭buffered流，再关闭inputstream流）
 */
/**
 * 
 * @ClassName:  Eficient_IO   
 * @Description:本程序是高效的IO操作   
 * @author: 申梦杰 
 * @date:   2018年10月10日 下午7:03:36
 * @version 1.8.0   
 * @param    
 *
 */
public class Eficient_IO {
    public static void main(String []args){
    	double start = System.currentTimeMillis();      
    	Eficient_IO.I0_Demo1();
    	System.out.println("基于IO缓冲耗时：" + (System.currentTimeMillis() - start) + "ms");
    }
    /**
     * 
     * @Title: I0_Demo1   
     * @Description:基于块内存的缓冲流
     * @param:       
     * @return: void      
     * @throws
     */
    public static void I0_Demo1(){
    	FileInputStream inputStream = null;
    	FileOutputStream outputStream = null;
    	BufferedInputStream bis = null;
    	BufferedOutputStream bos = null;
  		
  		try {
  			   inputStream = new FileInputStream("D://file.txt");
  			   bis = new BufferedInputStream(inputStream);
  			   outputStream = new FileOutputStream("D://filecopy.txt");
  			   bos = new BufferedOutputStream(outputStream);
  			   
  			   
  			   int len;    //读取到的长度
  			   byte[] bs = new byte[1024];
  			   // 开始时间
  			   long begin = System.currentTimeMillis();
  			   while ((len = bis.read(bs)) != -1) {
  				   /**
  				    * 或是使用如下可以解决中文乱码的问题
  				    * Sring str = new String(bs,0,len);
  				    * bos.write(str.getBytes());
  				    * bos.flush();
  				    */
  			       bos.write(bs, 0, len);
  			       bos.flush();
  			   }
  			   // 用时毫秒
  			   System.out.println(System.currentTimeMillis() - begin);// 78毫秒
  			   
  			   //关闭流的操作
  			   try {
  				   bos.close();
  				   bis.close();
  				} catch (Exception e) {
  					e.printStackTrace();
  				}
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
    	
    }
    /**
     * 
     * @Title: IO_Demo2   
     * @Description: 字符流修饰的缓冲流（可用于乱码问题）   
     * @param:       
     * @return: void      
     * @throws
     */
    public static void IO_Demo2(){
    	Reader reader = null;
    	Writer writer = null;
    	BufferedWriter bWriter = null;
    	BufferedReader bReader = null;
    	try {
			reader = new InputStreamReader(new FileInputStream(new File("e:/666.txt")),"UTF-8");
			writer = new OutputStreamWriter(new FileOutputStream(new File("e:/999.txt")),"UTF-8");
			bReader = new BufferedReader(reader);
			bWriter = new BufferedWriter(writer);
			char[] buffer = new char[1024];
			int len = 0;
			while((len=bReader.read(buffer))!= -1){
			System.out.println(new String(buffer,0,len));
			bWriter.write(buffer, 0, len);
			}
			bWriter.flush();
			//遵从先开后关的原则
			bWriter.close();
			bReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
