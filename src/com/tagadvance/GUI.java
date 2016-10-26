package com.tagadvance;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GUI {

	JFrame frame;
	Container cPane;
	final IntegerTextField x = new IntegerTextField();
	final IntegerTextField y = new IntegerTextField();
	final JPanel upper = new JPanel();
	final JPanel lower = new JPanel();
	
	final JButton lay = new JButton("Layout");
	final IntegerTextField turns = new IntegerTextField();
	final JButton run = new JButton("Run");
	
	public GUI(String title) {
		this.frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.cPane = frame.getContentPane();
		cPane.setLayout(new GridLayout(3,1));
		JPanel co = new JPanel();
		co.setLayout(new FlowLayout());
		Dimension d = new Dimension(20, 20);
		x.setPreferredSize(d);
		x.setToolTipText("X-Coordinate");
		co.add(x);
		y.setPreferredSize(d);
		y.setToolTipText("Y-Coordinate");
		co.add(y);
		lay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				reset(x.getInt(), y.getInt());
			}
		});
		turns.setPreferredSize(d);
		turns.setToolTipText("Turn Count");
		run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				execute();
			}
		});
		co.add(lay);
		co.add(turns);
		co.add(run);
		cPane.add(co);
		upper.setBorder(
				LineBorder.createGrayLineBorder());
		cPane.add(upper);
		lower.setBorder(
				LineBorder.createBlackLineBorder());
		cPane.add(lower);
		frame.setVisible(true);
		refresh();
	}
	Point[] points;
	public void execute() {
		Runnable run = new Runnable() {
			public void run() {
				try {
					int t = turns.getInt();
					points = new Point[t];
					rec(t);
					reverse(points);
					for(int i = 0; i < t; i++) {
						System.out.println("(" + (points[i].getX() + 1) + "," + (points[i].getY()+ 1) + ")");
					}
				} catch(Exception ex) {
					ex.printStackTrace();
					//is thrown when max turn count is not set
				}
			}
		};
		Thread t = new Thread(run);
		t.setPriority(Thread.MAX_PRIORITY);
		t.start();
	}
	
	public static void reverse(Point[] p) {
		int last = p.length - 1;
		for ( int i = 0; i < last; i++) {
			Point temp = p[i]; 
			p[i]  = p[last]; 
			p[last--] = temp;
		}
	}
	
	public boolean rec(int count) {
		count--;
		UpperPointCheck[][] upc = UpperPointCheck.table;
		LowerPointCheck[][] lpc = LowerPointCheck.table;
		for(int x = 0; x < upc.length; x++) {
			for(int y = 0; y < upc[x].length; y++) {
				boolean[][] mark = LowerPointCheck.getSet();
				lpc[x][y].cube();
				points[count] = new Point(x, y);
				//rest(10);
				if(PointCheck.compare(upc, lpc)) {
					return true;
				} else {
					 if(count > 0 && rec(count)) {
						 return true;
					 }
				}
				LowerPointCheck.reset(mark);
			}
		}
		return false;
	}
	
	public void reset(int x, int y) {
		upper.removeAll();
		lower.removeAll();
		UpperPointCheck.clear(x, y);
		LowerPointCheck.clear(x, y);
		upper.setLayout(new GridLayout(y,x));
		lower.setLayout(new GridLayout(y,x));
		for(int b = 0; b < y; b++) {
			for(int a = 0; a < x; a++) {
				upper.add(new UpperPointCheck(a, b));
				lower.add(new LowerPointCheck(a, b));
			}
		}
		refresh();
	}
	
	private void refresh() {
		frame.validate();
		frame.pack();
		frame.repaint();
	}
	
	public static void main(String[] args) {
		GUI gui = new GUI("Mario Hacks");
	}
	
	public static void rest(long time) {
		try {
			Thread.sleep(time);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}