package com.tagadvance;

import javax.swing.JCheckBox;

public class UpperPointCheck extends PointCheck {

	protected static UpperPointCheck[][] table;

	public UpperPointCheck(int x, int y) {
		super(x, y);
		table[x][y] = this;
	}

	public static JCheckBox getCheckAt(int x, int y) {
		return table[x][y];
	}

	public static void clear(int x, int y) {
		table = new UpperPointCheck[x][y];
	}

}
