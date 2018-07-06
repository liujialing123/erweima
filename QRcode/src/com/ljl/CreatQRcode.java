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
		//������ά�����
		Qrcode qrcode = new Qrcode();
		qrcode.setQrcodeVersion(version);
		//����һ��ͼƬ�������
	BufferedImage bufferedImage = new	BufferedImage(imgSize,imgSize,BufferedImage.TYPE_INT_BGR);
	//�����滭����
	Graphics2D gs=bufferedImage.createGraphics();
	//���ñ�����ɫ
	gs.setBackground(Color.white);
	//���������ɫ
	gs.setColor(Color.blue);
	//��ջ���
	gs.clearRect(0,0,imgSize,imgSize);
	//��ά������


	//����ƫ����
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
	//ͨ����ά��Ҫ�������ݻ�ȡһ���������͵Ķ�ά����
	boolean[][] calQrcode=qrcode.calQrcode(content1.getBytes("UTF-8"));
	for(int i=0;i<calQrcode.length;i++){//������
	System.out.println("������"+calQrcode.length+"������"+calQrcode[i].length);
	for(int j=0;j<calQrcode.length;j++){//������
		if(calQrcode[i][j]){//true����ά����ɫ  false�����
			
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

	//�رջ滭����
	gs.dispose();
	//�ѻ�����ͼƬ������ڴ浱��
	bufferedImage.flush();
	try{
	//���ڴ浱�е�ͼƬ�����Ӳ�̵���
	ImageIO.write(bufferedImage,"png",new File("D:/qr.png"));
	} catch(IOException e){
	e.printStackTrace();
	}
	System.out.println("�ɹ�");
	}
	
public static void main(String[] args) throws IOException {
		
		String content1="BEGIN:VCARD\r\n" + 
		"FN:����:������\r\n"+
		"ORG:ѧУ:�ӱ��Ƽ�ʦ��ѧԺ	ѧԺ:����\r\n"+
		"TITLE:��У��\r\n" + 
		"TEL;WORK;VOICE:1238\r\n"+
		"TEL;HOME;VOICE:456\r\n"+
		"TEL;CELL;VOICE:15533516959\r\n"+
		"ADR;WORK:�ӱ��Ƽ�ʦ��ѧԺ����ѧԺ�Ƽ�1603\r\n"+
		"ADR;HOME:��Ժ6��¥\r\n"+
		"URL:http://www.�ٶ�.com\r\n"+
		"EMAIL;HOME:244861262@qq.com\r\n" + 
		"END:VCARD";
		String imgPath="D:/qr.png";
		int version=16;
		String startRgb="0,0,255";
		String endRgb="255,0,0";
		creatQRcode(content1,imgPath,version,startRgb,endRgb);
	}

}

