package ij.gui;
import ij.IJ;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

/** A modal dialog box that displays information. Based on the
	InfoDialogclass from "Java in a Nutshell" by David Flanagan. */
public class MessageDialog extends JDialog implements ActionListener, KeyListener, WindowListener {
	protected JButton button;
	protected MultiLineLabel label;
	private boolean escapePressed;
	
	public MessageDialog(Frame parent, String title, String message) {
		super(parent, title, true);
		setLayout(new BorderLayout());
		if (message==null) message = "";
		label = new MultiLineLabel(message);
		if (!IJ.isLinux()) label.setFont(new Font("SansSerif", Font.PLAIN, 14));
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		panel.add(label);
		add("Center", panel);
		button = new JButton("  OK  ");
		button.addActionListener(this);
		button.addKeyListener(this);
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(button);
		add("South", panel);
		if (ij.IJ.isMacintosh())
			setResizable(false);
		pack();
		GUI.center(this);
		addWindowListener(this);
		show();
	}
	
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
	
	public void keyPressed(KeyEvent e) { 
		int keyCode = e.getKeyCode(); 
		IJ.setKeyDown(keyCode);
		escapePressed = keyCode==KeyEvent.VK_ESCAPE;
		if (keyCode==KeyEvent.VK_ENTER || escapePressed)
			dispose();
	} 
	
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode(); 
		IJ.setKeyUp(keyCode); 
	}
	
	public void keyTyped(KeyEvent e) {}

	public void windowClosing(WindowEvent e) {
		dispose();
	}
	
	public boolean escapePressed() {
		return escapePressed;
	}

	public void windowActivated(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}

}
