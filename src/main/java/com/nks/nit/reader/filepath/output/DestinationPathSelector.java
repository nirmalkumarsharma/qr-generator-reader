package com.nks.nit.reader.filepath.output;

import java.io.File;

import javax.swing.JFileChooser;

public class DestinationPathSelector
{
	public String getDestinationPath()
	{
		String destinationPath = null;
		JFileChooser fileChooser=new JFileChooser(new File("/media/nirmal/GODOWN/QR/"));
		if(fileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
		{
			destinationPath=fileChooser.getSelectedFile().getAbsolutePath();
		}
		
		return destinationPath;
	}
}
