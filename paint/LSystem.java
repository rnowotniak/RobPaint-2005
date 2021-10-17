package paint;

import java.lang.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;

public class LSystem {

	Character axiom;
	float totalLength;
	float angle;
	float baseAngle;
	Point start;
	int level;
	HashMap prods;
	String fractalName;

	private Graphics2D g2d;
	private Stack stateStack;
	private float stepLength;
	private double scaleFactor;
	
	static private String[] demoNames = {
		"Roslinka 1", "Roslinka 2", "Roslinka 3",
		"Roslinka 4", "Krzywa Hilberta", "Krzywa Peano"
	};


	private LSystem() {
		stateStack = new Stack();
		stepLength = 100;
	}

	public static void main(String[] args) {
		Okno1 o = new Okno1("Jakas aplikacja");

		LSystem lsys = LSystem.getDemoLSystem(4);
		lsys.drawIt(o.im.createGraphics());

		o.setSize(600, 700);
		o.setVisible(true);
	}

	static public String[] getDemoNames() {
		return demoNames;
	}

	static public LSystem getDemoLSystem(int number) {
		LSystem result = new LSystem();

		if (number == 1) {
			result.axiom = new Character('F');
			result.totalLength = 300;
			result.angle = 22;
			result.baseAngle = 0;
			result.start = new Point(300, 500);
			result.level = 4;
			result.fractalName = demoNames[number - 1];
			result.prods = new HashMap();
			result.prods.put(new Character('F'), "F[+F]F[-F]F");
			result.prods.put(new Character('f'), "fff");
		}
		else if (number == 2) {
			result.axiom = new Character('F');
			result.totalLength = 300;
			result.angle = 22;
			result.baseAngle = 0;
			result.start = new Point(300, 500);
			result.level = 3;
			result.fractalName = demoNames[number - 1];
			result.prods = new HashMap();
			result.prods.put(new Character('F'), "FF+[+F-F-F]-[-F+F+F]");
			result.prods.put(new Character('f'), "fff");
		} else if (number == 3) {
			result.axiom = new Character('X');
			result.totalLength = 200;
			result.angle = 22;
			result.baseAngle = 0;
			result.start = new Point(300, 500);
			result.level = 5;
			result.fractalName = demoNames[number - 1];
			result.prods = new HashMap();
			result.prods.put(new Character('X'), "F-[[X]+X]+F[+FX]-X");
			result.prods.put(new Character('F'), "FF");
		} else if (number == 4) {
			result.axiom = new Character('Y');
			result.angle = 25;
			result.level = 5;
			result.totalLength = 10;
			result.baseAngle = 0;
			result.start = new Point(300, 500);
			result.fractalName = demoNames[number - 1];
			result.prods = new HashMap();
			result.prods.put(new Character('Y'), "YFX[+Y][-Y]");
			result.prods.put(new Character('X'), "X[-FFF][+FFF]FX");
		} else if (number == 5) {
			result.axiom = new Character('X');
			result.angle = 90;
			result.level = 7;
			result.totalLength = 10;
			result.baseAngle = 0;
			result.start = new Point(300, 500);
			result.fractalName = demoNames[number - 1];
			result.prods = new HashMap();
			result.prods.put(new Character('X'), "-YF+XFX+FY-");
			result.prods.put(new Character('Y'), "+XF-YFY-FX+");
		} else if (number == 6) {
			result.axiom = new Character('X');
			result.angle = 90;
			result.level = 4;
			result.totalLength = 10;
			result.baseAngle = 0;
			result.start = new Point(300, 500);
			result.fractalName = demoNames[number - 1];
			result.prods = new HashMap();
			result.prods.put(new Character('X'), "XFYFX+F+YFXFY-F-XFYFX");
			result.prods.put(new Character('Y'), "YFXFY-F-XFYFX+F+YFXFY");
		} else if (number == 9) {
			result.axiom = new Character('F');
			result.totalLength = 400;
			result.angle = 22;
			result.baseAngle = 0;
			result.start = new Point(300, 500);
			result.level = 4;
			result.prods = new HashMap();
			result.prods.put(new Character('F'), "FfF");
			result.prods.put(new Character('f'), "fff");
		}

		return result;
	}

	public void drawIt(Graphics2D g2d) {
		this.g2d = g2d;

		//g2d.setPaint(Color.RED);
		g2d.rotate(Math.toRadians(180));
		g2d.translate(-start.x, -start.y);
		g2d.rotate(Math.toRadians(baseAngle));

		String firstRule = (String) prods.get(axiom);
		String rule = new String(firstRule);

		for (int i = 1; i < level; i++) {
			rule = processRule(rule);
		}

		//System.out.println(rule);

		scaleFactor = computeScale(firstRule);
		//System.out.println(scaleFactor);
		g2d.scale(scaleFactor, scaleFactor);
		draw(rule);
	}

	/* Algorytm szacujacy rozmiar fraktala :/ */
	private double computeScale(String firstRule) {
		double result;

		AffineTransform tx = new AffineTransform();
		Point p = new Point();
		double lastY = 0, maxY = 0;
		for (int i = 0; i < firstRule.length(); i++) {
			char c = firstRule.charAt(i);
			/*
			System.out.println(c);
			System.out.println(p);
			System.out.println(tx);
			System.out.println("-");
			*/
			if (c == 'F' || c == 'f') {
				tx.translate(0, stepLength);
				p = transformPoint(new Point(), tx);
				if (Math.abs(p.y) > maxY) {
					maxY = Math.abs(p.y);
				}
			} else if (c == '+') {
				tx.rotate(Math.toRadians(angle));
			} else if (c == '-') {
				tx.rotate(Math.toRadians(-angle));
			} else if (c == '[') {
				stateStack.push(new AffineTransform(tx));
			} else if (c == ']') {
				tx = (AffineTransform) stateStack.pop();
			} else {
				/* XXX */
			}
			//System.out.println(c);
			//System.out.println(p);
			//System.out.println("=========");
			p = transformPoint(new Point(), tx);
			lastY = Math.abs(p.y);
		}

		//System.out.println(lastY);
		//System.out.println(maxY);

		result = totalLength / maxY;
		result *= Math.pow(stepLength / lastY, level - 1);
		return result;
	}

	static public double distanceFromPoint(Point p1, Point p2) {
		double result;
		double a = p2.x - p1.x;
		double b = p2.y - p1.y;
		result = Math.sqrt(a * a + b * b);
		return result;
	}

	private Point transformPoint(Point p, AffineTransform tx) {
		Point result;
		double coefs[] = new double[6];
		double x2, y2;

		tx.getMatrix(coefs);
		x2 = coefs[0] * p.x + coefs[2] * p.y + coefs[4];
		y2 = coefs[1] * p.x + coefs[3] * p.y + coefs[5];

		result = new Point((int) x2, (int) y2);
		return result;
	}

	private String processRule(String rule) {
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < rule.length(); i++) {
			Character c = new Character(rule.charAt(i));
			String rep = (String) prods.get(c);
			if (rep != null) {
				result.append(rep);
			} else  {
				result.append(c);
			}
		}

		return result.toString();
	}

	private void draw(String rule) {
		for (int i = 0; i < rule.length(); i++) {
			char c = rule.charAt(i);
			if (c == 'F') {
				g2d.drawLine(0, 0, 0, (int) stepLength);
				g2d.translate(0, stepLength);
			} else if (c == 'f') {
				g2d.translate(0, stepLength);
			} else if (c == '+') {
				g2d.rotate(Math.toRadians(angle));
			} else if (c == '-') {
				g2d.rotate(-Math.toRadians(angle));
			} else if (c == '[') {
				stateStack.push(g2d.getTransform());
			} else if (c == ']') {
				AffineTransform tx =
					(AffineTransform) stateStack.pop();
				g2d.setTransform(tx);
			} else {
				/* XXX */
			}
		}
	}

}



class Okno1 extends Frame
	implements ActionListener, ItemListener, MouseListener, WindowListener {

	JPanel pan1;
	BufferedImage im;
	JLabel lab1;

	public Okno1() {
		this("Okno");
	}

	public Okno1(String title) {
		super(title);
		addWindowListener(this);

		pan1 = new JPanel();
		im = new BufferedImage(600, 700, BufferedImage.TYPE_INT_RGB);
		lab1 = new JLabel();
		lab1.setIcon(new ImageIcon(im));
		pan1.add(lab1);
		add(pan1);

		pack();
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == null) {

		}
	}
	public void itemStateChanged(ItemEvent event) {
		if (event.getSource() == null) {

		}
	}

	public void mouseClicked(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mousePressed(MouseEvent e) { }
	public void mouseReleased(MouseEvent e)  { }

	public void windowActivated(WindowEvent e) { }
	public void windowClosed(WindowEvent e) { }
	public void windowDeactivated(WindowEvent e) { }
	public void windowDeiconified(WindowEvent e) { }
	public void windowIconified(WindowEvent e) { }
	public void windowOpened(WindowEvent e) { }
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}
