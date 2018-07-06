package com.ljl;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import com.swetake.util.Qrcode;
public class CreatQRcode {
	public static void creatQRcode (String content1,String imgPath,int version,String startRgb,String endRgb) throws IOException
	{
		int imgSize=67+(version-1)*12;
		//创建二维码对象
		Qrcode qrcode = new Qrcode();
		qrcode.setQrcodeVersion(version);
		//生成一个图片缓存对象
	BufferedImage bufferedImage = new	BufferedImage(imgSize,imgSize,BufferedImage.TYPE_INT_BGR);
	//创建绘画对象
	Graphics2D gs=bufferedImage.createGraphics();
	//设置背景颜色
	gs.setBackground(Color.white);
	//设置填充颜色
	gs.setColor(Color.blue);
	//清空画板
	gs.clearRect(0,0,imgSize,imgSize);
	//二维码内容


	//声明偏移量
	int pixoff=2;
	
	int startR=0, startG=0, startB=0;
	int endR=0,endG=0, endB=0;

	if(null!=startRgb)
	{
	String[] split=startRgb.split(",");
	startR=Integer.valueOf(split[0]);
	startG=Integer.valueOf(split[1]);
	startB=Integer.valueOf(split[2]);
	}
	if(null!=endRgb)
	{
	String[] split=endRgb.split(",");
	endR=Integer.valueOf(split[0]);
	endG=Integer.valueOf(split[1]);
	endB=Integer.valueOf(split[2]);
	}
	//通过二维码要填充的内容获取一个布尔类型的二维数组
	boolean[][] calQrcode=qrcode.calQrcode(content1.getBytes("UTF-8"));
	for(int i=0;i<calQrcode.length;i++){//遍历行
	System.out.println("行数："+calQrcode.length+"列数："+calQrcode[i].length);
	for(int j=0;j<calQrcode.length;j++){//遍历列
		if(calQrcode[i][j]){//true填充二维码颜色  false则不填充
			
			int r=startR+(endR-startR)/calQrcode.length*(j+1);
			int g=startG+(endG-startR)/calQrcode.length*(j+1);
			int b=startB+(endB-startR)/calQrcode.length*(j+1);
			Color color = new Color(r,g,b);
			gs.setColor(color);
			gs.fillRect(i*3+pixoff, j*3+pixoff, 3, 3);
		}
		
	}
	}
	BufferedImage logo=ImageIO.read(new File("D:/logo.jpg"));
	int logoSize=imgSize/4;
	int o=(imgSize-logoSize)/2;
	gs.drawImage(logo,o,o,logoSize,logoSize,null);

	//关闭绘画对象
	gs.dispose();
	//把缓存区图片输出到内存当中
	bufferedImage.flush();
	try{
	//把内存当中的图片输出到硬盘当中
	ImageIO.write(bufferedImage,"png",new File("D:/qr.png"));
	} catch(IOException e){
	e.printStackTrace();
	}
	System.out.println("成功");
	}
	
public static void main(String[] args) throws IOException {
		
		String content1="BEGIN:VCARD\r\n" + 
		"FN:姓名:刘嘉玲\r\n"+
		"ORG:学校:河北科技师范学院	学院:数信\r\n"+
		"TITLE:在校生\r\n" + 
		"TEL;WORK;VOICE:1238\r\n"+
		"TEL;HOME;VOICE:456\r\n"+
		"TEL;CELL;VOICE:15533516959\r\n"+
		"ADR;WORK:河北科技师范学院数信学院科技1603\r\n"+
		"ADR;HOME:南院6号楼\r\n"+
		"URL:http://www.百度.com\r\n"+
		"EMAIL;HOME:244861262@qq.com\r\n" + 
		"END:VCARD";
		String imgPath="D:/qr.png";
		int version=16;
		String startRgb="0,0,255";
		String endRgb="255,0,0";
		creatQRcode(content1,imgPath,version,startRgb,endRgb);
	}

}

