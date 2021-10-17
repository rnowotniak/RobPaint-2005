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

/* Rysuje niewypelniona elipse */
public class EllipseDrawer extends AbstractTool {

	static public AbstractTool tool = null;

	protected Point prevPoint;

	public EllipseDrawer(MainWindow main) {
		super(main);
		toolName = "Elipsy";
	}

	static public AbstractTool getTool(MainWindow main) {
		if (tool == null)
			tool = new EllipseDrawer(main);
		return tool;
	}

	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		prevPoint = new Point(e.getX(), e.getY());
	}
	public void mouseDragged(MouseEvent e) {
		MainWindow.ImageFrame.Content frameContent =
			(MainWindow.ImageFrame.Content) e.getSource();
		Image image = frameContent.image;
		Graphics2D g2d = (Graphics2D) image.getGraphics();

		Dimension dim1 = new Dimension(
				(int) Math.abs(firstPoint.getX() - prevPoint.getX()),
				(int) Math.abs(firstPoint.getY() - prevPoint.getY())
				);
		Point p1 = new Point(
				(int) Math.min(prevPoint.getX(), firstPoint.getX()),
				(int) Math.min(prevPoint.getY(), firstPoint.getY())
				);
		Dimension dim2 = new Dimension(
				(int) Math.abs(e.getX() - firstPoint.getX()),
				(int) Math.abs(e.getY() - firstPoint.getY())
				);
		Point p2 = new Point(
				(int) Math.min(firstPoint.getX(), e.getX()),
				(int) Math.min(firstPoint.getY(), e.getY())
				);

		g2d.setXORMode(colorXOR);
		g2d.draw(
				new Ellipse2D.Double(
					p1.getX(), p1.getY(),
					dim1.getWidth(), dim1.getHeight()));
		g2d.draw(
				new Ellipse2D.Double(
					p2.getX(), p2.getY(),
					dim2.getWidth(), dim2.getHeight()));
		prevPoint = new Point(e.getX(), e.getY());
		frameContent.repaint();
	}
	public void mouseReleased(MouseEvent e) {
		MainWindow.ImageFrame.Content frameContent =
			(MainWindow.ImageFrame.Content) e.getSource();
		Image image = frameContent.image;
		Graphics2D g2d = (Graphics2D) image.getGraphics();

		Dimension dim1 = new Dimension(
				(int) Math.abs(e.getX() - firstPoint.getX()),
				(int) Math.abs(e.getY() - firstPoint.getY())
				);
		Point p1 = new Point(
				(int) Math.min(firstPoint.getX(), e.getX()),
				(int) Math.min(firstPoint.getY(), e.getY())
				);

		frameContent.mShapes.addFirst(
				new ModifiableShape(
					new Ellipse2D.Double(
						p1.getX(), p1.getY(),
						dim1.getWidth(), dim1.getHeight()),
					mainWindow.currentPaint, mainWindow.currentStroke,
					mainWindow.currentComposite, mainWindow.currentTransform
					));

		g2d.setXORMode(colorXOR);
		g2d.draw(
				new Ellipse2D.Double(
					p1.getX(), p1.getY(),
					dim1.getWidth(), dim1.getHeight()
					));
		g2d.draw(
				new Ellipse2D.Double(
					firstPoint.getX(), firstPoint.getY(),
					0, 0));

		frameContent.repaint();
	}
}
