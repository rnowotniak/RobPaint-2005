package tools;

import paint.*;
import java.lang.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class LSystemDrawer extends AbstractTool {

	static public AbstractTool tool = null;

	protected Point prevPoint;

	public LSystemDrawer(MainWindow main) {
		super(main);
		toolName = "L-System";
	}

	static public AbstractTool getTool(MainWindow main) {
		if (tool == null)
			tool = new LSystemDrawer(main);
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

		g2d.setXORMode(colorXOR);
		g2d.drawLine(
				(int) firstPoint.getX(),
				(int) firstPoint.getY(),
				(int) firstPoint.getX(),
				(int) firstPoint.getY());
		g2d.drawLine(
				(int) firstPoint.getX(),
				(int) firstPoint.getY(),
				(int) e.getX(),
				(int) e.getY()
				);
		frameContent.repaint();


		if (mainWindow.lsysDialog1 == null) {
			LSystemDialog lsysDial = new LSystemDialog(mainWindow, true);

			mainWindow.lsysDialog1 = lsysDial;

			DefaultListModel listModel = new DefaultListModel();
			lsysDial.jExamplesList.setModel(listModel);
			String demos[] = LSystem.getDemoNames();
			for (int i = 0; i < demos.length; i++) {
				listModel.addElement(demos[i]);
			}

		}
		mainWindow.lsysDialog1.jLenSpinner.setValue(
				new Integer((int) LSystem.distanceFromPoint(
						firstPoint, new Point(e.getX(), e.getY()))));
		mainWindow.lsysDialog1.jStartText.setText(
				"" + firstPoint.x + "," + firstPoint.y);
		double direction = Math.atan(
				1.0 *
				(e.getX() - firstPoint.x) / (firstPoint.y - e.getY()));
		direction = Math.toDegrees(direction);
		if (e.getY() > firstPoint.y) {
			direction += 180;
		}
		mainWindow.lsysDialog1.jDirectionText.setText(
				new Integer((int) direction).toString() + " stopni");
		mainWindow.lsysDialog1.show();
	}
}
