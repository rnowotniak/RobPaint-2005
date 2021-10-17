package tools;

import paint.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;


/* Rysuje niewypelniony wielokat */
public class PolygonDrawer extends LineDrawer {

	static public AbstractTool tool = null;

	protected Point prevPoint;
	static protected ArrayList vertexes;

	public PolygonDrawer(MainWindow main) {
		super(main);
		toolName = "Wielokaty";
	}

	static public AbstractTool getTool(MainWindow main) {
		if (tool == null)
			tool = new PolygonDrawer(main);
		vertexes = new ArrayList();
		return tool;
	}

	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		MainWindow.ImageFrame.Content frameContent =
			(MainWindow.ImageFrame.Content) e.getSource();
		Image image = frameContent.image;
		Graphics2D g2d = (Graphics2D) image.getGraphics();

		if (e.getButton() == MouseEvent.BUTTON1) {
			mouseMoved(e);
			vertexes.add(new Point(e.getX(), e.getY()));
		} else if (e.getButton() == MouseEvent.BUTTON2
				|| e.getButton() == MouseEvent.BUTTON3) {
			if (vertexes.size() == 0) {
				return;
			}

			/* Dodanie wielokata */
			Point currentPoint;
			Point prevPoint = null;
			int x, y;
			boolean first = true;
			Polygon poly = new Polygon();
			g2d.setXORMode(colorXOR);
			for (int i = 0; i < vertexes.size(); i++) {
				currentPoint = (Point) vertexes.get(i);
				x = (int) currentPoint.x;
				y = (int) currentPoint.y;
				if (!first) {
					g2d.drawLine(
							prevPoint.x, prevPoint.y,
							x, y);
					g2d.drawLine(x, y, x, y);
				} else {
					g2d.drawLine(x, y, x, y);
					first = false;
				}
				prevPoint = new Point(x, y);
				poly.addPoint(x, y);
			}
			g2d.drawLine(
					(int) prevPoint.getX(),
					(int) prevPoint.getY(),
					e.getX(), e.getY());
			ModifiableShape ms = new ModifiableShape(poly,
					mainWindow.currentPaint, mainWindow.currentStroke,
					mainWindow.currentComposite, mainWindow.currentTransform);
			frameContent.mShapes.addFirst(ms);
			vertexes = new ArrayList();
			frameContent.repaint();
				}
	}
	public void mouseMoved(MouseEvent e) {
		if (vertexes.size() != 0) {
			super.mouseDragged(e);
		}
	}
	public void mouseReleased(MouseEvent e) { }
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}
}
