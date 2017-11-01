package com.nks.nit.reader.filepath.input;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class InputFileSelector
{
	public String getFilePath()
	{
		JFileChooser fileChooser=new JFileChooser(new File("/media/nirmal/GODOWN/QR"));
		
		FileFilter filter=new FileNameExtensionFilter("Image","png"); //Filters the xlsx file
		fileChooser.setFileFilter(filter);
		if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
		{
			return fileChooser.getSelectedFile().getAbsolutePath();
		}
		return "";
	}
}
