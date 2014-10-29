package ij.plugin;
import ij.*;
import ij.process.*;
import ij.gui.*;
import ij.util.Tools;
import ij.io.Opener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** This plugin implements the File/Batch/Convert command, 
	which converts the images in a folder to a specified format. */
	public class BatchConverter implements PlugIn, ActionListener {
		private static final String[] formats = {"TIFF", "8-bit TIFF", "JPEG", "GIF", "PNG", "PGM", "BMP", "FITS", "Text Image", "ZIP", "Raw"};
		private static String format = formats[0];
		//private static int height;
		private static double scale = 1.0;
		private static boolean useBioFormats;
		private static int interpolationMethod = ImageProcessor.BILINEAR;
		private String[] methods = ImageProcessor.getInterpolationMethods();
		private JButton input, output;
		private JTextField inputDir, outputDir;
		private GenericDialog gd;

	public void run(String arg) {
		if (!showDialog())
			return;
		String inputPath = inputDir.getText();
		if (inputPath.equals("")) {
			IJ.error("Batch Converter", "Please choose an input folder");
			return;
		}
		String outputPath = outputDir.getText();
		if (outputPath.equals("")) {
			IJ.error("Batch Converter", "Please choose an output folder");
			return;
		}
		File f1 = new File(inputPath);
		if (!f1.exists() || !f1.isDirectory()) {
			IJ.error("Batch Converter", "Input does not exist or is not a folder\n \n"+inputPath);
			return;
		}
		File f2 = new File(outputPath);
		if (!outputPath.equals("") && (!f2.exists() || !f2.isDirectory())) {
			IJ.error("Batch Converter", "Output does not exist or is not a folder\n \n"+outputPath);
			return;
		}
		String[] list = (new File(inputPath)).list();
		ImageJ ij = IJ.getInstance();
		if (ij!=null) ij.getProgressBar().setBatchMode(true);
		IJ.resetEscape();
		for (int i=0; i<list.length; i++) {
			if (IJ.escapePressed())
				break;
			if (IJ.debugMode) IJ.log(i+"  "+list[i]);
			String path = inputPath + list[i];
			if ((new File(path)).isDirectory())
				continue;
			if (list[i].startsWith(".")||list[i].endsWith(".avi")||list[i].endsWith(".AVI"))
				continue;
			IJ.showProgress(i+1, list.length);
			ImagePlus imp = null;
			IJ.redirectErrorMessages(true);
			if (useBioFormats)
				imp = Opener.openUsingBioFormats(path);
			else
				imp = IJ.openImage(path);
			IJ.redirectErrorMessages(false);
			if (imp==null) {
				String reader = useBioFormats?"Bio-Formats not found or":"IJ.openImage()";
				IJ.log(reader+" returned null: "+path);
				continue;
			}
			if (scale!=1.0) {
				int width = (int)(scale*imp.getWidth());
				int height = (int)(scale*imp.getHeight());
				ImageProcessor ip = imp.getProcessor();
				ip.setInterpolationMethod(interpolationMethod);
				imp.setProcessor(null, ip.resize(width,height,true));
			}
			if (format.equals("8-bit TIFF") || format.equals("GIF")) {
				if (imp.getBitDepth()==24)
					IJ.run(imp, "8-bit Color", "number=256");
				else
					IJ.run(imp, "8-bit", "");
			}
			IJ.saveAs(imp, format, outputPath+list[i]);
			imp.close();
		}
		IJ.showProgress(1,1);
		Prefs.set("batch.input", inputDir.getText());
		Prefs.set("batch.output", outputDir.getText());
	}
			
	private boolean showDialog() {
		gd = new GenericDialog("Batch Convert");
		addPanels(gd);
		gd.setInsets(15, 0, 5);
		gd.addChoice("Output format:", formats, format);
		gd.addChoice("Interpolation:", methods, methods[interpolationMethod]);
		//gd.addStringField("Height (pixels): ", height==0?"\u2014":""+height, 6);
		gd.addNumericField("Scale factor:", scale, 2);
		gd.addCheckbox("Read images using Bio-Formats", useBioFormats);
		gd.setOKLabel("Convert");
		gd.showDialog();
		if (gd.wasCanceled())
			return false;
		format = gd.getNextChoice();
		interpolationMethod = gd.getNextChoiceIndex();
		//height = (int)Tools.parseDouble(gd.getNextString(), 0.0);
		scale = gd.getNextNumber();
		useBioFormats = gd.getNextBoolean();
		return true;
	}

	void addPanels(GenericDialog gd) {
		JPanel p = new JPanel();
    	p.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
		input = new JButton("Input...");
		input.addActionListener(this);
		p.add(input);
		inputDir = new JTextField(Prefs.get("batch.input", ""), 45);
		p.add(inputDir);
		gd.addPanel(p);
		p = new JPanel();
    	p.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
		output = new JButton("Output...");
		output.addActionListener(this);
		p.add(output);
		outputDir = new JTextField(Prefs.get("batch.output", ""), 45);
		p.add(outputDir);
		gd.addPanel(p);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String s = source==input?"Input":"Output";
		String path = IJ.getDirectory(s+" Folder");
		if (path==null) return;
		if (source==input)
			inputDir.setText(path);
		else
			outputDir.setText(path);
		if (IJ.isMacOSX())
			{gd.setVisible(false); gd.setVisible(true);}
	}

}
