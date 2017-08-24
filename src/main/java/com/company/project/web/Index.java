package com.company.project.web;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.company.project.Sender;
import com.company.project.SpringUtils;
import com.company.project.util.DESUtil;

@Controller
public class Index {

	@RequestMapping("/index")
	public String home(Map<String,Object> map){
		System.out.println("index");
		
	   //map.put("hello","Hello World!");  
	   return "index";  
	}
	
	@RequestMapping("/upload") 
	  public String file() { 
	    return "upload"; 
	  } 
	
	@RequestMapping(value="/uploadFile", method=RequestMethod.POST)
    public String uploadFile(HttpServletRequest request) {
		//用来检测程序运行时间
        long  startTime=System.currentTimeMillis();
        
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (!isMultipart) {
               return "upload";
            }

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload();

            // Parse the request
            FileItemIterator iter = upload.getItemIterator(request);
            
            //DESUtil des = new DESUtil("test");
            System.out.println(iter.hasNext());
            while (iter.hasNext()) {
            	System.out.println(iter.hasNext());
            	FileItemStream item = iter.next();
                String name = item.getFieldName();
                InputStream stream = item.openStream();
                System.out.println("item.isFormField()"+item.isFormField());
                if (!item.isFormField()) {
                	System.out.println("File field " + name + " with file name "+ item.getName() + " detected.");
                	BufferedInputStream bin= new BufferedInputStream(stream);
                	 byte buf[] = new byte[2048];
                	 while ((bin.read(buf)) != -1)
                	 {
                		 System.out.println(buf);
                		/*
                		 //加密
                		try {
							System.out.println(des.encrypt(buf));
						} catch (InvalidKeyException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalBlockSizeException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (BadPaddingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NoSuchAlgorithmException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NoSuchPaddingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                		 */
                		 
                		 //Sender sender = SpringUtils.getApplicationContext().getBean(Sender.class);
                         //sender.sendMessage(new String(buf));
                	 }
                	bin.close();
                	
                	//String message = Streams.asString(stream);
                    //Sender sender = SpringUtils.getApplicationContext().getBean(Sender.class);
                    //sender.sendMessage(message);
                    stream.close();
                
                }

                /*
                System.out.println("item.isFormField()"+item.isFormField());
                if (!item.isFormField()) {
                	System.out.println("---file----");
                    // Process the input stream
                    String message = Streams.asString(stream);
                    System.out.println(message);
                    //Sender sender = SpringUtils.getApplicationContext().getBean(Sender.class);
                    //sender.sendMessage(message);
                    stream.close();
                }
                */
            }
        } catch (FileUploadException e) {
            return "upload";
        } catch (IOException e) {
            return "upload";
        }
        long  endTime=System.currentTimeMillis();
        System.out.println("运行时间："+String.valueOf(endTime-startTime)+"ms");
        return "hello";
    }
	

	@RequestMapping("/doUpload")
	public String upload(@RequestParam("file") MultipartFile file) throws IOException{
		//用来检测程序运行时间
        long  startTime=System.currentTimeMillis();
        System.out.println("fileName："+file.getOriginalFilename());
         
        try {
            //获取输出流
            //OutputStream os=new FileOutputStream("E:/"+new Date().getTime()+file.getOriginalFilename());
            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
            InputStream is=file.getInputStream();
            int temp;
            //一个一个字节的读取并写入
            while((temp=is.read())!=(-1))
            {
                //os.write(temp);
            	System.out.println(temp);
            }
           //os.flush();
           //os.close();
           is.close();
         
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long  endTime=System.currentTimeMillis();
        System.out.println("运行时间："+String.valueOf(endTime-startTime)+"ms");
        return "hello"; 
	}
}
