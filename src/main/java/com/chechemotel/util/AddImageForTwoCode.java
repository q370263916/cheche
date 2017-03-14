package com.chechemotel.util;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
/**
 * 给二维码添加logo
 * @author zhouzhiyuan
 *
 */
public final class AddImageForTwoCode {
	private static final Logger logger = Logger.getLogger(AddImageForTwoCode.class);
	 public static void addLogo_QRCode(File qrPic, File logoPic, LogoConfig logoConfig)
	    {
	        try
	        {
	            if (!qrPic.isFile() || !logoPic.isFile())
	            {
	            	logger.debug("file not find !");
	               
	            }
	 
	            /**
	             * 读取二维码图片，并构建绘图对象
	             */
	            BufferedImage image = ImageIO.read(qrPic);
	            Graphics2D g = image.createGraphics();
	 
	            /**
	             * 读取Logo图片
	             */
	            BufferedImage logo = ImageIO.read(logoPic);
	             
	            int widthLogo = logo.getWidth(), heightLogo = logo.getHeight();
	             int widthimage=image.getWidth(),heightimage=image.getHeight();
	            // 计算图片放置位置
	            int x = (image.getWidth() - widthLogo) / 2;
	            int y = (image.getHeight() - logo.getHeight()) / 2;
	 
	            //开始绘制图片
	            g.drawImage(logo, x, y, widthLogo, heightLogo, null);
	            g.drawRoundRect(x, y, widthLogo, heightLogo, 15, 15);
	            g.setStroke(new BasicStroke(logoConfig.getBorder()));
	            g.setColor(logoConfig.getBorderColor());
	            g.drawRect(x, y, widthLogo, heightLogo);
	             
	            g.dispose();
	             Date date=new Date();
	             Long imgSrc=date.getTime();
	             String str=String.valueOf(imgSrc);
	            ImageIO.write(image, "jpeg", new File("F:/picture/twoCode"+str+".jpg"));
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }
	 public static void main(String args[]){
		 LogoConfig logoConfig=new LogoConfig();
		 
		 File qrPic=new File("F:/picture/showqrcode.jpg");
		 File logoPic=new File("F:/picture/comment.png");
		 addLogo_QRCode(qrPic,logoPic,logoConfig);
	 }
}
