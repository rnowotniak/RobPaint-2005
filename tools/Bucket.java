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

/* Wypelnianie warstw deseniem */
public class Bucket extends AbstractTool {

	static public AbstractTool tool = null;

	public Bucket(MainWindow main) {
		super(main);
		toolName = "Wiadro";
	}

	static public AbstractTool getTool(MainWindow main) {
		if (tool == null)
			tool = new Bucket(main);
		return tool;
	}


	public void mouseClicked(MouseEvent e) {
		MainWindow.ImageFrame.Content frameContent =
			(MainWindow.ImageFrame.Content) e.getSource();

		boolean found = false;
		Iterator i = frameContent.mShapes.iterator();
		while (!found && i.hasNext()) {
			ModifiableShape ms = (ModifiableShape) i.next();
			Shape s = ms.shape;
			if (s.contains(e.getX(), e.getY())) {
				ms.isFilled = true;
				ms.paint = mainWindow.currentPaint;
				ms.composite = mainWindow.currentComposite;
				frameContent.selectedShape = null;
				mainWindow.statusLine.setMessage("Wypelniono figure");
				found = true;
			}
		}
		if (!found) {
			frameContent.selectedShape = null;
			mainWindow.statusLine.setMessage("Nic tu nie ma do wypelnienia");
		}
		frameContent.repaint();
	}

	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
	}
	public void mouseDragged(MouseEvent e) {
		MainWindow.ImageFrame.Content frameContent =
			(MainWindow.ImageFrame.Content) e.getSource();
		Image image = frameContent.image;
		Graphics2D g2d = (Graphics2D) image.getGraphics();
		g2d.setPaint(mainWindow.currentPaint);
		g2d.setStroke(mainWindow.currentStroke);
		g2d.drawLine(
				(int) firstPoint.getX(),
				(int) firstPoint.getY(),
				(int) e.getX(),
				(int) e.getY()
				);
		firstPoint.move(e.getX(), e.getY());
		frameContent.repaint();
	}
}

