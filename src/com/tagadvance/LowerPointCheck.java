package com.tagadvance;

import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class LowerPointCheck extends PointCheck {

	protected static LowerPointCheck[][] table;
	public static final CheckListener CF = new CheckListener();

	public LowerPointCheck(int x, int y) {
		super(x, y);
		table[x][y] = this;
		this.addMouseListener(CF);
	}

	public void cube() {
		int XC = getXC();
		int YC = getYC();
		for (int x = XC - 1; x < (XC + 2); x++) {
			for (int y = YC - 1; y < (YC + 2); y++) {
				try {
					table[x][y].toggle();
				} catch (Exception ex) {
				}
			}
		}
	}

	public static JCheckBox getCheckAt(int x, int y) {
		return table[x][y];
	}

	public static void clear(int x, int y) {
		table = new LowerPointCheck[x][y];
	}

	public static boolean[][] getSet() {
		int l1 = table.length;
		int l2 = table[0].length;
		boolean[][] bTable = new boolean[l1][l2];
		for (int x = 0; x < l1; x++) {
			for (int y = 0; y < l2; y++) {
				bTable[x][y] = table[x][y].isSelected();
			}
		}
		return bTable;
	}

	public static void reset(boolean[][] bTable) {
		try {
			for (int x = 0; x < table.length; x++) {
				for (int y = 0; y < table[x].length; y++) {
					table[x][y].setSelected(bTable[x][y]);
				}
			}
		} catch (Exception ex) {
		}
	}

}
