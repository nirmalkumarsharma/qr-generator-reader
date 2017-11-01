package com.nks.nit.generator;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.nks.nit.reader.filepath.output.DestinationPathSelector;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Scanner;


public class QRCodeGeneratorDynamic
{
    public static void main(String[] args) throws WriterException, IOException
    {
        Scanner inp=new Scanner(System.in);
        String url=inp.nextLine();
        inp.close();
        DestinationPathSelector destinationPathSelector=new DestinationPathSelector();
        String baseDirectoryAndFile=destinationPathSelector.getDestinationPath();
        
        String qrFileExt=FilenameUtils.getExtension(baseDirectoryAndFile);
        
        if(qrFileExt.equals("")||qrFileExt.equals(null))
        {
        	baseDirectoryAndFile+=".png";
        }
        
        int size=1000;
        String fileType="png";
        File qrFile=new File(baseDirectoryAndFile);
        createQRImage(qrFile,url,size,fileType);
        System.out.println("QR Code Generated");
        System.out.println("File Path: "+baseDirectoryAndFile);
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
