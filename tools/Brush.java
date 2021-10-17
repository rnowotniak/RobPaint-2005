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

/* Maluje odrecznie danym pedzlem */
public class Brush extends AbstractTool {

	static public AbstractTool tool = null;

	public Brush(MainWindow main) {
		super(main);
		toolName = "Pedzel";
	}

	static public AbstractTool getTool(MainWindow main) {
		if (tool == null)
			tool = new Brush(main);
		return tool;
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

