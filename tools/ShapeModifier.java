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

/* Modyfikuje wektorowe elementy */
public class ShapeModifier extends AbstractTool {

	static public AbstractTool tool = null;

	public ShapeModifier(MainWindow main) {
		super(main);
		toolName = "Modyfikator";
	}

	static public AbstractTool getTool(MainWindow main) {
		if (tool == null)
			tool = new ShapeModifier(main);
		return tool;
	}

	public void mouseClicked(MouseEvent e) {
		super.mousePressed(e);
		MainWindow.ImageFrame.Content frameContent =
			(MainWindow.ImageFrame.Content) e.getSource();
		Image image = frameContent.image;
		Graphics2D g2d = (Graphics2D) image.getGraphics();

		boolean found = false;
		Iterator i = frameContent.mShapes.iterator();
		while (!found && i.hasNext()) {
			ModifiableShape ms = (ModifiableShape) i.next();
			Shape s = ms.shape;
			/* dodac .contans() do ModifiableShape zamiast tego */
			if (s instanceof RectangularShape
					|| s instanceof Polygon) {
				if (s.contains(e.getX(), e.getY())) {
					mainWindow.statusLine.setMessage(s.toString());
					frameContent.selectedShape = ms;
					found = true;
				}
			}
		}
		if (!found) {
			frameContent.selectedShape = null;
			mainWindow.statusLine.setMessage("Nic tu nie ma");
		}
		frameContent.repaint();
	}
	public void mouseDragged(MouseEvent e) {
		MainWindow.ImageFrame.Content frameContent =
			(MainWindow.ImageFrame.Content) e.getSource();
		Image image = frameContent.image;
		Graphics2D g2d = (Graphics2D) image.getGraphics();

		ModifiableShape ms = frameContent.selectedShape;
		if (ms != null) {
			/* przesuniecie figury */
			if (ms.shape instanceof RectangularShape) {
				if (ms.shape.contains(e.getX(), e.getY())) {
					RectangularShape rS = (RectangularShape) ms.shape;
					rS.setFrame(
							rS.getX() + e.getX() - firstPoint.getX(),
							rS.getY() + e.getY() - firstPoint.getY(),
							rS.getWidth(), rS.getHeight());
				}
			}
			else if (ms.shape instanceof Polygon) {
				if (ms.shape.contains(e.getX(), e.getY())) {
					Polygon poly = (Polygon) ms.shape;
					poly.translate(
							(int) (e.getX() - firstPoint.getX()),
							(int) (e.getY() - firstPoint.getY()));
				}
			}
		} else {
			frameContent.selectedShape = null;
		}
		firstPoint = new Point(e.getX(), e.getY());
		frameContent.repaint();
	}
}
