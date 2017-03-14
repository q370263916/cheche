package com.chechemotel.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

public class QRCode {
	
	//生成二维码
	public static String encode(String text) throws Exception{   
		String url="";
		String realPath =ConstantClass.imgUrl;
	       if(text!=null&&!text.equals(""))
	       {
	    	    int width = 100;   
	 	        int height = 100;   
	 	        String format = "png";   
	 	        Hashtable hints= new Hashtable();   
	 	        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");   
	 	         BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height,hints);   
	 	        File file =new File(realPath); 
	 	        if(!file.exists()&&!file.isDirectory())
	 	        {
	 	        	file.mkdir();
	 	        }
	 	         File outputFile = new File(realPath+"/"+text+".png");   
	 	         MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);   
	 	         url="/upload/"+text+".png";
	       }
	   
	           return  url;
	    }   
	
	
    // 解码  
    public void decode() {  
        try {  
            Reader reader = new MultiFormatReader();  
            String imgPath = "D:\\hwy.png";  
            File file = new File(imgPath);  
            BufferedImage image;  
            try {  
                image = ImageIO.read(file);  
                if (image == null) {  
                    System.out.println("Could not decode image");  
                }  
                LuminanceSource source = new BufferedImageLuminanceSource(image);  
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(  
                        source));  
                Result result;  
                Hashtable hints = new Hashtable();  
                hints.put(DecodeHintType.CHARACTER_SET, "GBK");  
                result = new MultiFormatReader().decode(bitmap, hints);  
                String resultStr = result.getText();  
                System.out.println(resultStr);  
  
            } catch (IOException ioe) {  
                System.out.println(ioe.toString());  
            } catch (ReaderException re) {  
                System.out.println(re.toString());  
            }  
  
        } catch (Exception ex) {  
  
        }  
    }  
	
}
