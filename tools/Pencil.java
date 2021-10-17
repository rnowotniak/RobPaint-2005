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
public class Pencil extends AbstractTool {

	static public AbstractTool tool = null;

	public Pencil(MainWindow main) {
		super(main);
		toolName = "Olowek";
	}

	static public AbstractTool getTool(MainWindow main) {
		if (tool == null)
			tool = new Pencil(main);
		return tool;
	}

	public void mouseDragged(MouseEvent e) {
		MainWindow.ImageFrame.Content frameContent =
			(MainWindow.ImageFrame.Content) e.getSource();
		Image image = frameContent.image;
		Graphics2D g2d = (Graphics2D) image.getGraphics();
		g2d.setPaint(mainWindow.color1);
		g2d.setStroke(new BasicStroke(1));
		g2d.drawLine(
				(int) e.getX(),
				(int) e.getY(),
				(int) e.getX(),
				(int) e.getY()
				);
		frameContent.repaint();
	}
}

