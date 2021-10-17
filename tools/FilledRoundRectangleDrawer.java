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


/* Rysuje wypelniony prostokat zaokraglony */
public class FilledRoundRectangleDrawer extends RoundRectangleDrawer {

	static public AbstractTool tool = null;

	public FilledRoundRectangleDrawer(MainWindow main) {
		super(main);
		toolName = "Wypelnione Prostokaty Zaokraglone";
	}

	static public AbstractTool getTool(MainWindow main) {
		if (tool == null)
			tool = new FilledRoundRectangleDrawer(main);
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
