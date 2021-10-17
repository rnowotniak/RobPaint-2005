package tools;

import paint.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.*;

public abstract class AbstractTool
	implements MouseListener, MouseMotionListener {

	protected Point firstPoint;
	Color colorXOR = new Color(50, 15, 245);

	public String toolName;
	protected MainWindow mainWindow;

	protected AbstractTool(MainWindow main) {
		mainWindow = main;
		toolName = "Abstract Tool";
	}

	public void mouseClicked(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mousePressed(MouseEvent e) {
		firstPoint = new Point(e.getX(), e.getY());
	}
	public void mouseReleased(MouseEvent e) { }
	public void mouseDragged(MouseEvent e) { }
	public void mouseMoved(MouseEvent e) { }
}

