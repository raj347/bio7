/*******************************************************************************
 * Copyright (c) 2007-2013 M. Austenfeld
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     M. Austenfeld
 *******************************************************************************/

package com.eco.bio7.rbridge;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import org.apache.commons.io.comparator.NameFileComparator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RSession;
import org.rosuda.REngine.Rserve.RserveException;

import com.eco.bio7.Bio7Plugin;
import com.eco.bio7.batch.Bio7Dialog;
import com.eco.bio7.batch.FileRoot;
import com.eco.bio7.collection.Work;
import com.eco.bio7.console.Console;
import com.eco.bio7.console.ConsolePageParticipant;
import com.eco.bio7.preferences.PreferenceConstants;
import com.eco.bio7.rcp.ApplicationWorkbenchWindowAdvisor;
import com.eco.bio7.rcp.StartBio7Utils;

import ij.ImagePlus;
import ij.ImageStack;

/**
 * This class provides methods for the communication with the Rserve
 * application.
 * 
 * @author Bio7
 * 
 */
public class RServe {

	private static String rout; // The output to the console

	private static boolean Rrunning = false;

	private static RConnection connection = null;

	private static boolean isconnecting = false;

	private static String[] selections = null;

	private static RSession rSession;

	/**
	 * Evaluates an R expression without running in a job. This method can be
	 * used to evaluate R scripts from e.g. Groovy (running already in a job). R
	 * plots a possible, too.
	 * 
	 * @param expression
	 *            a R expression.
	 */
	public static void evaluateExt(String expression) {
		if (RServe.isAlive()) {
			if (RState.isBusy() == false) {
				RState.setBusy(true);
				try {
					RServe.getConnection().eval(expression);

					int countDev = RServe.getDisplayNumber();
					if (countDev > 0) {
						RServe.closeAndDisplay();
					}

				} catch (RserveException e) {
					RState.setBusy(false);
					System.out.println(e.getRequestErrorDescription());

				}
				RState.setBusy(false);

			} else {
				System.out.println("Rserve is busy!");
			}
		} else {
			System.out.println("No connection to Rserve available!");
		}
	}

	/**
	 * Evaluates and prints an expression to the Bio7 console executed in a job.
	 * 
	 * @param expression
	 *            a R expression as a string.
	 */
	public static void printJob(String expression) {// helper class to print
		if (RState.isBusy() == false) {
			RState.setBusy(true);
			REvaluateJob job = new REvaluateJob(expression);
			job.addJobChangeListener(new JobChangeAdapter() {
				public void done(IJobChangeEvent event) {
					if (event.getResult().isOK()) {

						int countDev = getDisplayNumber();
						RState.setBusy(false);
						if (countDev > 0) {
							RServe.closeAndDisplay();
						}
						System.out.flush();
					} else {
						RState.setBusy(false);

						System.out.flush();
					}
				}
			});

			// job.setSystem(true);
			job.schedule();
		} else {
			Process p;
			//IPreferenceStore store = Bio7Plugin.getDefault().getPreferenceStore();
			//if (store.getBoolean("RSERVE_NATIVE_START")) {
				ConsolePageParticipant consol = ConsolePageParticipant.getConsolePageParticipantInstance();
				p = consol.getRProcess();
			/*} else {

				p = RConnectionJob.getProc();
			}*/

			// Write to the output!
			if (p != null) {
				final OutputStream os = p.getOutputStream();
				final OutputStreamWriter osw = new OutputStreamWriter(os);
				final BufferedWriter bw = new BufferedWriter(osw, 100);

				try {
					bw.write(expression);

					bw.newLine();

					os.flush();
					bw.flush();
					// bw.close();
					System.out.flush();
				} catch (IOException e) {
					System.err.println("");
				}
			}

			// Bio7Dialog.message("Rserve is busy!");

		}

	}

	/**
	 * Evaluates and prints an array of expressions to the Bio7 console executed
	 * in a job.
	 * 
	 * @param expressions
	 *            a R expression as a string.
	 */
	public static void printJobs(String[] expressions) {// helper class to print
		if (RState.isBusy() == false) {
			RState.setBusy(true);
			REvaluateExpressionsJob job = new REvaluateExpressionsJob(expressions);
			job.addJobChangeListener(new JobChangeAdapter() {
				public void done(IJobChangeEvent event) {
					if (event.getResult().isOK()) {

						int countDev = getDisplayNumber();

						RState.setBusy(false);
						if (countDev > 0) {
							RServe.closeAndDisplay();
						}
					} else {
						RState.setBusy(false);
					}
				}
			});

			// job.setSystem(true);
			job.schedule();
		} else {

			Bio7Dialog.message("Rserve is busy!");

		}

	}

	/**
	 * Evaluates and prints an expression to the Bio7 console.
	 * 
	 * @param expression
	 *            a R expression as a string.
	 */
	public static void print(String expression) {

		try {
			RServe.rout = RServe.connection.eval("try(paste(capture.output(print(" + expression + ")),collapse=\"\\n\"))").asString();
		} catch (REXPMismatchException e) {

			e.printStackTrace();
		} catch (RserveException e) {

			e.printStackTrace();
		}

		// StartBio7Utils.getConsoleInstance().cons.println(RServe.rout);

		if (RServe.rout != null && RServe.rout.equals("NULL") == false) {
			StartBio7Utils.getConsoleInstance().cons.println(RServe.rout);
			/* Send also the output to the R console view! */
			if (RShellView.isConsoleExpanded()) {

				RShellView.setTextConsole(RServe.rout);

			}
		}

	}

	/**
	 * Returns a Rserve connection.
	 * 
	 * @return a R connection.
	 */
	public static RConnection getConnection() {
		return connection;
	}

	/**
	 * Returns if the connection to Rserve is alive visible by means of a
	 * dialog.
	 * 
	 * @return a boolean value.
	 */
	public static boolean isAliveDialog() {
		if (RServe.connection == null) {
			Display display = PlatformUI.getWorkbench().getDisplay();
			display.syncExec(new Runnable() {
				public void run() {

					MessageBox messageBox = new MessageBox(new Shell(),

					SWT.ICON_WARNING);
					messageBox.setMessage("Rserve connection failed\nServer is not running!");
					messageBox.open();
				}
			});

		}
		return Rrunning;

	}

	/**
	 * Returns if the connection to Rserve is alive.
	 * 
	 * @return a boolean value.
	 */
	public static boolean isAlive() {
		return Rrunning;

	}

	/**
	 * Sets a Rserve connection.
	 * 
	 * @param connection
	 *            a R connection.
	 */
	public static void setConnection(RConnection connection) {
		RServe.connection = connection;
	}

	/**
	 * Returns if the plotting device is the pdf device.
	 * 
	 * @return a boolean value.
	 */
	public static boolean isPdf() {
		return PlotJob.isPdf();
	}

	/**
	 * Set the plotting device to pdf.
	 * 
	 * @param pdf
	 *            a boolean value.
	 */
	public static void setPdf(boolean pdf) {
		PlotJob.setPdf(pdf);
	}

	/**
	 * Internal method of Bio7.
	 * 
	 * 
	 * @return a boolean value.
	 */
	public static boolean isIsconnecting() {
		return isconnecting;
	}

	/**
	 * Returns if the Rserve application is running.
	 * 
	 * @return a boolean value.
	 */
	public static boolean isRrunning() {
		return Rrunning;
	}

	/**
	 * Set the Rserve running value.
	 * 
	 * @param running
	 *            a boolean value.
	 */
	public static void setRrunning(boolean running) {
		Rrunning = running;
	}

	/**
	 * Internal method of Bio7.
	 * 
	 * @param rout
	 *            a string value.
	 */
	public static void setRout(String rout) {
		RServe.rout = rout;
	}

	/**
	 * Internal method of Bio7.
	 * 
	 * @return a string value.
	 */
	public static String getRout() {
		return rout;
	}

	/**
	 * Returns if the Bio7 application is connected to the Rserve application.
	 * 
	 * @return a boolean value.
	 */
	public static boolean isConnected() {
		boolean connected;
		if (RServe.getConnection() != null) {
			connected = true;
		} else {
			connected = false;
		}
		return connected;
	}

	/**
	 * Set the plot size in inch for a pdf plot.
	 * 
	 * @param inchx
	 *            the width of the plot in inch units.
	 * @param inchy
	 *            the height of the plot in inch units.
	 */
	public static void setPlotInch(double inchx, double inchy) {
		PlotJob.setPlotInch(inchx, inchy);
	}

	/**
	 * Set the plot size in pixel for a png plot.
	 * 
	 * @param imageWidth
	 *            the width of the plot in pixel units.
	 * @param imageHeight
	 *            the height of the plot in pixel units.
	 */
	public static void setPlotPixel(int imageWidth, int imageHeight) {
		PlotJob.setPlotPixel(imageWidth, imageHeight);

	}

	/**
	 * A method to call a RScript.
	 * 
	 * @param path
	 *            the relative path from the Bio7 Workspace to the script.
	 */
	public static void callRScript(String path) {
		/* Get the path and convert it for R (Windows) */
		String f = FileRoot.getFileRoot() + path;

		if (Bio7Dialog.getOS().equals("Windows")) {
			f = f.replace("/", "\\");
		}
		try {
			/* Transfer path to R ! */
			RServe.getConnection().assign("fileroot", f);

			/* Call the custom Rscript ! */
			String rout = null;
			try {
				rout = RServe.getConnection().eval("" + "try(paste(capture.output(source(fileroot,echo=T)),collapse=\"\\n\"))").asString();
			} catch (REXPMismatchException e) {

				e.printStackTrace();
			}
			Console cons = StartBio7Utils.getConsoleInstance().cons;
			if (rout != null && rout.equals("NULL") == false) {
				cons.println(rout);
				/* Send also the output to the R console view! */
				if (RShellView.isConsoleExpanded()) {

					RShellView.setTextConsole(rout);

				}
			}

		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * A method to call a RScript without to capture (print) the output.
	 * 
	 * @param path
	 *            the relative path from the Bio7 Workspace to the script.
	 * 
	 */
	public static void callRScriptSilent(String path) {
		/* Get the path and convert it for R (Windows) */
		String f = FileRoot.getFileRoot() + path;

		if (Bio7Dialog.getOS().equals("Windows")) {
			f = f.replace("/", "\\");
		}
		try {
			/* Transfer path to R ! */
			RServe.getConnection().assign("fileroot", f);

			/* Call the custom Rscript ! */

			RServe.getConnection().eval("try(source(fileroot,echo=F))");

		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * A method to get the selected variable names in the left Objects tab in
	 * the R-Shell view (variables in the R workspace!).
	 * 
	 * @return the selected workspace variables.
	 */
	public static String[] getShellSelections() {

		Display display = PlatformUI.getWorkbench().getDisplay();
		display.syncExec(new Runnable() {
			public void run() {

				RShellView shell = RShellView.getInstance();
				if (shell != null) {
					selections = shell.getListShell().getSelection();
				}
			}
		});

		return selections;
	}

	public static void closeAndDisplay() {
		Job job = new Job("Add To ImageStack") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Add Plots To ImageStack ...", IProgressMonitor.UNKNOWN);

				finalCloseAndDisplay();

				monitor.done();
				return Status.OK_STATUS;
			}

		};
		job.addJobChangeListener(new JobChangeAdapter() {
			public void done(IJobChangeEvent event) {
				if (event.getResult().isOK()) {

					RState.setBusy(false);
				} else {

					RState.setBusy(false);
				}
			}
		});
		// job.setSystem(true);
		job.schedule();
	}

	private static void finalCloseAndDisplay() {
		IPreferenceStore store = Bio7Plugin.getDefault().getPreferenceStore();
		boolean customDevice = store.getBoolean("USE_CUSTOM_DEVICE");
		if (customDevice) {

			String plotPathR = store.getString(PreferenceConstants.P_TEMP_R);
			String fileName = store.getString("DEVICE_FILENAME");

			if (fileName.endsWith("pdf") || fileName.endsWith("eps") || fileName.endsWith("xfig") || fileName.endsWith("bitmap") || fileName.endsWith("pictex")) {
				if (ApplicationWorkbenchWindowAdvisor.getOS().equals("Windows") || ApplicationWorkbenchWindowAdvisor.getOS().equals("Mac")) {
					Program.launch(plotPathR + fileName);
				} else {
					plotLinux(plotPathR + fileName);
				}

			} else if (fileName.endsWith("svg")) {
				if (ApplicationWorkbenchWindowAdvisor.getOS().equals("Windows") || ApplicationWorkbenchWindowAdvisor.getOS().equals("Mac")) {
					Program.launch(plotPathR + fileName);
				} else {
					plotLinuxSVG(plotPathR + fileName);
				}

			}

			else {
				Work.openView("com.eco.bio7.imagej");

				// System.out.println(plotPathR);

				File[] files = ListFilesDirectory(new File(plotPathR), new String[] { ".tiff", ".tif", ".jpg", ".jpeg", ".png", ".bmp" });
				if (files.length > 0) {
					ImagePlus plu = new ImagePlus(files[0].toString());
					ImageStack stack = new ImageStack(plu.getWidth(), plu.getHeight());
					// System.out.println(files.length);
					for (int i = 0; i < files.length; i++) {
						// System.out.println(files[i].toString());
						ImagePlus plus = new ImagePlus(files[i].toString());
						stack.addSlice(plus.getProcessor());
						files[i].delete();
					}

					new ImagePlus("Plot", stack).show();
				}

			}

		}
	}

	public static int getDisplayNumber() {
		IPreferenceStore store = Bio7Plugin.getDefault().getPreferenceStore();
		boolean customDevice = store.getBoolean("USE_CUSTOM_DEVICE");

		int countDev = 0;
		if (customDevice) {
			REXP devList = null;
			try {
				devList = RServe.getConnection().eval("length(dev.list())");
			} catch (RserveException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				countDev = devList.asInteger();
				// System.out.println(countDev);
			} catch (REXPMismatchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				RServe.getConnection().eval("if(length(dev.list())>0) dev.off()");
			} catch (RserveException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return countDev;
	}

	public static File[] ListFilesDirectory(File filedirectory, final String[] extensions) {
		File dir = filedirectory;

		String[] children = dir.list();
		if (children == null) {

		} else {
			for (int i = 0; i < children.length; i++) {
				// Get filename of the file or directory inside Bio7.
				String filename = children[i];
			}
		}

		// Filter the extension of the file.
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return (name.endsWith(extensions[0]) || name.endsWith(extensions[1]) || name.endsWith(extensions[2]) || name.endsWith(extensions[3]) || name.endsWith(extensions[4]) || name.endsWith(extensions[5]));
			}
		};

		File[] files = dir.listFiles(filter);
		/* Sort the filenames! */
		Arrays.sort(files, NameFileComparator.NAME_INSENSITIVE_COMPARATOR);
		return files;
	}

	private static void plotLinuxSVG(String finalpath) {
		try {

			Runtime.getRuntime().exec("inkscape " + finalpath);
		} catch (IOException e) {
			Bio7Dialog.message("Can't start Inkscape!");
		}
	}

	public static void plotLinux(String finalpath) {
		IPreferenceStore store = Bio7Plugin.getDefault().getPreferenceStore();
		String pdfReader = store.getString("PDF_READER");

		if (pdfReader.equals("ACROBAT")) {
			try {

				Runtime.getRuntime().exec("acroread " + finalpath);
			} catch (IOException e) {
				Bio7Dialog.message("Can't start Acrobat Reader!");
			}
		} else if (pdfReader.equals("KPDF")) {
			try {
				Runtime.getRuntime().exec("kpdf " + finalpath);
			} catch (IOException e2) {

				Bio7Dialog.message("Can't start Kpdf!");
			}
		} else if (pdfReader.equals("XPDF")) {
			try {
				Runtime.getRuntime().exec("xpdf " + finalpath);
			} catch (IOException e2) {

				Bio7Dialog.message("Can't start Xpdf!");
			}
		} else if (pdfReader.equals("EVINCE")) {
			try {
				Runtime.getRuntime().exec("evince " + finalpath);
			} catch (IOException e2) {

				Bio7Dialog.message("Can't starte Evince!");
			}

		}
	}

	/**
	 * A method to store the current RSession.
	 * 
	 * @param rSess
	 *            the RSession
	 */
	public static void setRsession(RSession rSess) {
		rSession = rSess;

	}

	/**
	 * A method to get the current stored RSession.
	 * 
	 * @return the RSession
	 */
	public static RSession getRsession() {

		return rSession;
	}

}
