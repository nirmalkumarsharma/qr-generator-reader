package com.nks.nit.reader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.nks.nit.reader.filepath.input.InputFileSelector;

public class QRCodeReader {

	public static void main(String[] args) throws IOException, NotFoundException
	{
		InputFileSelector selector=new InputFileSelector();
		String qrFilePath=selector.getFilePath();
		System.out.println("InputFile : "+qrFilePath);
		File qrFile=new File(qrFilePath);
		String decodeText=decodeText(qrFile);
		System.out.println("Decoded Text : "+decodeText);
	}

	private static String decodeText(File qrFile) throws IOException, NotFoundException
	{
		BufferedImage image=ImageIO.read(qrFile);
		LuminanceSource source=new BufferedImageLuminanceSource(image);
		BinaryBitmap binaryBitmap=new BinaryBitmap(new HybridBinarizer(source));
		Result result=new MultiFormatReader().decode(binaryBitmap);
		return result.getText();
	}
}
