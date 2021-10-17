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


/* Rysuje linie proste Line2d */
public class LineDrawer extends AbstractTool {

	static public AbstractTool tool = null;

	protected Point prevPoint;

	public LineDrawer(MainWindow main) {
		super(main);
		toolName = "Proste";
	}

	static public AbstractTool getTool(MainWindow main) {
		if (tool == null)
			tool = new LineDrawer(main);
		return tool;
	}

	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		prevPoint = new Point(e.getX(), e.getY());
	}
	public void mouseDragged(MouseEvent e) {
		if (e.getX() == prevPoint.x && e.getY() == prevPoint.y)
			return;

		MainWindow.ImageFrame.Content frameContent =
			(MainWindow.ImageFrame.Content) e.getSource();
		Image image = frameContent.image;
		Graphics2D g2d = (Graphics2D) image.getGraphics();

		g2d.setXORMode(colorXOR);
		g2d.drawLine(
				firstPoint.x,
				firstPoint.y,
				prevPoint.x,
				prevPoint.y
				);
		g2d.drawLine(
				firstPoint.x,
				firstPoint.y,
				e.getX(),
				e.getY()
				);
		prevPoint = new Point(e.getX(), e.getY());

		frameContent.repaint();
	}
	public void mouseReleased(MouseEvent e) {
		MainWindow.ImageFrame.Content frameContent =
			(MainWindow.ImageFrame.Content) e.getSource();
		Image image = frameContent.image;
		Graphics2D g2d = (Graphics2D) image.getGraphics();

		g2d.setPaintMode();
		g2d.setPaint(mainWindow.currentPaint);
		g2d.setStroke(mainWindow.currentStroke);
		g2d.drawLine(
				(int) firstPoint.getX(),
				(int) firstPoint.getY(),
				(int) e.getX(),
				(int) e.getY()
				);
		frameContent.repaint();
	}
}
