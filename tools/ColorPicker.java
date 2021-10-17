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


/* Bierze kolor z danego piksela*/
public class ColorPicker extends AbstractTool {

	static public AbstractTool tool = null;

	public ColorPicker(MainWindow main) {
		super(main);
		toolName = "Wybieracz kolorow";
	}

	static public AbstractTool getTool(MainWindow main) {
		if (tool == null)
			tool = new ColorPicker(main);
		return tool;
	}

	public void mouseClicked(MouseEvent e) {
		MainWindow.ImageFrame.Content frameContent =
			(MainWindow.ImageFrame.Content) e.getSource();
		Image image = frameContent.image;

		int pixels[] = new int[1];
		PixelGrabber pg = new PixelGrabber(
				image, e.getX(), e.getY(), 1, 1,
				pixels, 0, 1);
		try  {
			pg.grabPixels();
		} catch (Exception ex) {
			mainWindow.statusLine.setMessage(
					"Wyjatek podczas wybierania koloru. " + ex);
			return;
		}
		int alpha = (pixels[0] >> 24) & 0xff;
		int red   = (pixels[0] >> 16) & 0xff;
		int green = (pixels[0] >>  8) & 0xff;
		int blue  = (pixels[0]      ) & 0xff;
		mainWindow.statusLine.setMessage(
				"" + red + " " +green+" "+blue+"");
		if (e.getButton() == MouseEvent.BUTTON1) {
			mainWindow.color1 = new Color(red, green, blue);
			mainWindow.colorsToolbar.selectedColor1.setBackground(
					mainWindow.color1);
		}
		else if (e.getButton() == MouseEvent.BUTTON2
				|| e.getButton() == MouseEvent.BUTTON3) {
			mainWindow.color2 = new Color(red, green, blue);
			mainWindow.colorsToolbar.selectedColor2.setBackground(
					mainWindow.color2);
				}
	}
}
