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


/* Rysuje wypelniony prostokat */
public class FilledPolygonDrawer extends PolygonDrawer {

	static public AbstractTool tool = null;

	public FilledPolygonDrawer(MainWindow main) {
		super(main);
		toolName = "Wypelnione Wielokaty";
	}

	static public AbstractTool getTool(MainWindow main) {
		if (tool == null)
			tool = new FilledPolygonDrawer(main);
		vertexes = new ArrayList();
		return tool;
	}

	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		if (e.getButton() == MouseEvent.BUTTON2
				|| e.getButton() == MouseEvent.BUTTON3) {
		MainWindow.ImageFrame.Content frameContent =
			(MainWindow.ImageFrame.Content) e.getSource();
		((ModifiableShape) frameContent.mShapes.getFirst()).isFilled = true;
		frameContent.repaint();
				}
	}
}
