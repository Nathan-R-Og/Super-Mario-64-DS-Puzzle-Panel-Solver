package com.tagadvance;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class IntegerTextField extends JTextField {

	private int maxlength = -1;

	protected IntegerTextField() {
		this.addKeyListener(new IntegerListener());
	}

	class IntegerListener implements KeyListener {
		public void keyTyped(KeyEvent ae) {
			int kc = ae.getKeyChar(); // readability
			JTextField source = (JTextField) ae.getSource();
			int length = source.getText().length();
			if (kc < 48 || kc > 57 || (maxlength >= 0 && length > maxlength - 1)) {
				ae.consume();
			}
		}

		public void keyPressed(KeyEvent ae) {

		}

		public void keyReleased(KeyEvent ae) {

		}
	}

	public int getInt() {
		return Integer.parseInt(getText());
	}

	public void setMaxLength(int length) {
		this.maxlength = length;
	}

	public int getMaxLength() {
		return this.maxlength;
	}

}
