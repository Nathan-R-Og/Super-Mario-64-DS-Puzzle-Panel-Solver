package com.tagadvance;

import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class PointCheck extends JCheckBox {

	private int x, y;

	public PointCheck(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public void toggle() {
		setSelected(!isSelected());
	}

	public void toggleEnabled() {
		setEnabled(!isEnabled());
	}

	public int getXC() {
		return this.x;
	}

	public int getYC() {
		return this.y;
	}

	public static boolean compare(UpperPointCheck[][] upc, PointCheck[][] lpc) {
		for (int x = 0; x < upc.length; x++) {
			for (int y = 0; y < upc[x].length; y++) {
				if (!(upc[x][y].isSelected() == lpc[x][y].isSelected())) {
					return false;
				}
			}
		}
		return true;
	}

}
