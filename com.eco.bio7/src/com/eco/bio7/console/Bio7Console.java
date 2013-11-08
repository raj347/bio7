/*******************************************************************************
 * Copyright (c) 2007-2012 M. Austenfeld
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     M. Austenfeld
 *******************************************************************************/

package com.eco.bio7.console;

import org.eclipse.jface.preference.IPreferenceStore;

import com.eco.bio7.Bio7Plugin;

/**
 * @author Bio7 A class to write commands to the Bio7 console!
 */
public class Bio7Console {

	/**
	 * A method to write to the console.
	 * 
	 * @param command
	 *            a command for the console
	 */
	public static void write(String command) {

		ConsolePageParticipant.pipeInputToConsole(command);

	}

	/**
	 * A method to set the encoding.
	 * 
	 * @param encoding
	 *            the encoding which will be stored in the preferences!
	 */
	public static void setEncoding(String encoding) {
		IPreferenceStore store = Bio7Plugin.getDefault().getPreferenceStore();
		store.setValue("Console_Encoding", encoding);

	}

	/**
	 * A method which returns the selected Console.
	 * 
	 * @return a String representation of the selected console.
	 */
	public static String getConsoleSelection() {
		String selectionConsole = ConsolePageParticipant.getInterpreterSelection();
		return selectionConsole;
	}

}
