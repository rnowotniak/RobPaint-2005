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
public class FilledRectangleDrawer extends RectangleDrawer {

	static public AbstractTool tool = null;

	public FilledRectangleDrawer(MainWindow main) {
		super(main);
		toolName = "Wypelnione Prostokaty";
	}

	static public AbstractTool getTool(MainWindow main) {
		if (tool == null)
			tool = new FilledRectangleDrawer(main);
		return tool;
	}

	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		MainWindow.ImageFrame.Content frameContent =
			(MainWindow.ImageFrame.Content) e.getSource();
		((ModifiableShape) frameContent.mShapes.getFirst()).isFilled = true;
		frameContent.repaint();
	}
}
