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


/* Gumka scierajaca */
public class Eraser extends Brush {

	static public AbstractTool tool = null;

	public Eraser(MainWindow main) {
		super(main);
		toolName = "Gumka";
	}

	static public AbstractTool getTool(MainWindow main) {
		if (tool == null)
			tool = new Eraser(main);
		return tool;
	}

	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
	}
	public void mouseDragged(MouseEvent e) {
		Paint origPaint = mainWindow.currentPaint;
		Stroke origStroke = mainWindow.currentStroke;
		mainWindow.currentPaint = Color.BLACK;
		mainWindow.currentStroke = new BasicStroke(
				16, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER);
		super.mouseDragged(e);
		mainWindow.currentPaint = origPaint;
		mainWindow.currentStroke = origStroke;
	}
}

