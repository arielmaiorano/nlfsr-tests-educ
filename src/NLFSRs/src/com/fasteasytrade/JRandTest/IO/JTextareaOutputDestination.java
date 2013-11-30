
package com.fasteasytrade.JRandTest.IO;

import java.awt.*;

import javax.swing.JTextArea;

/**

 */
public class JTextareaOutputDestination implements OutputDestination {

	JTextArea ta = null;

	public JTextareaOutputDestination(JTextArea ta) {
		this.ta = ta;
	}

	/**
	 * @see com.fasteasytrade.JRandTest.IO.OutputDestination#printf(java.lang.String)
	 */
	public void printf(String s) {
		ta.append(s);
	}

	/**
	 * @see com.fasteasytrade.JRandTest.IO.OutputDestination#puts(java.lang.String)
	 */
	public void puts(String s) {
		ta.append(s);
	}

}