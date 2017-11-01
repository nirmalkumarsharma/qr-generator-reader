package com.nks.nit.generator;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;


public class QRCodeGenerator
{
	public static void main(String[] args) throws WriterException, IOException
    {
        Scanner inp=new Scanner(System.in);
        String url=inp.nextLine();
        inp.close();
        String urlPattern[]=url.split("\\.");
        String fileName=urlPattern[1];
        String filePath="";
        if(urlPattern[0].equals("https://www")||urlPattern[0].equals("http://www")||urlPattern[0].equals("www"))
            filePath="/media/nirmal/GODOWN/QR/"+fileName+".png";
        else if(urlPattern[0].substring(0,5).equals("https"))
            filePath="/media/nirmal/GODOWN/QR/"+fileName+"_"+urlPattern[0].substring(8)+".png";
        else if(urlPattern[0].substring(0,5).equals("http:"))
            filePath="/media/nirmal/GODOWN/QR/"+fileName+"_"+urlPattern[0].substring(7)+".png";
        else
        	filePath="/media/nirmal/GODOWN/QR/"+fileName+"_"+urlPattern[0]+".png";
        int size=1000;
        String fileType="png";
        File qrFile=new File(filePath);
        createQRImage(qrFile,url,size,fileType);
        System.out.println("QR Code Generated");
        System.out.println("File Path: "+filePath);
    }

    private static void createQRImage(File qrFile, String url, int size, String fileType) throws WriterException, IOException
    {
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap=new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter=new QRCodeWriter();
        BitMatrix bitMatrix=qrCodeWriter.encode(url, BarcodeFormat.QR_CODE,size,size,hintMap);
        int matrixWidth=bitMatrix.getWidth();
        BufferedImage image=new BufferedImage(matrixWidth,matrixWidth,BufferedImage.TYPE_INT_RGB);
        image.createGraphics();
        Graphics2D graphics2D= (Graphics2D) image.getGraphics();
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0,0,matrixWidth,matrixWidth);
        graphics2D.setColor(Color.BLACK);
        for (int i=0;i<matrixWidth;i++)
        {
            for (int j=0;j<matrixWidth;j++)
            {
                if (bitMatrix.get(i,j))
                {
                    graphics2D.fillRect(i,j,1,1);
                }
            }
        }
        ImageIO.write(image,fileType,qrFile);
    }
}
