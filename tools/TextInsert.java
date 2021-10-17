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
public class TextInsert extends AbstractTool {

	static public AbstractTool tool = null;
	static String text;
	static Font font;

	static String dialogTitle = "Pisanie tekstem";

	public TextInsert(MainWindow main) {
		super(main);
		toolName = "Tekst";
	}

	static public AbstractTool getTool(MainWindow main) {
		if (tool == null)
			tool = new TextInsert(main);
		text = null;
		font = new Font("Default", Font.PLAIN, 30);
		return tool;
	}

	public void mouseClicked(MouseEvent e) {
		MainWindow.ImageFrame.Content frameContent =
			(MainWindow.ImageFrame.Content) e.getSource();
		Image image = frameContent.image;
		Graphics2D g2d = (Graphics2D) image.getGraphics();


		boolean newText = false;
		if (text == null) {
			newText = true;
			text = inputText();
		}
		if (text != null) {
			if (newText) {
				String fonts[] = {
					"Default", "Monospaced", "Sans Serif",
					"Serif", "Dialog", "DialogInput"
				};

				String name = (String) JOptionPane.showInputDialog(
						mainWindow,
						"Nazwa czcionki:",
						dialogTitle,
						JOptionPane.QUESTION_MESSAGE,
						null, fonts, "Monospaced");
				if (name != null) {
					Integer size = (Integer) JOptionPane.showInputDialog(
							mainWindow,
							"Rozmiar czcionki",
							dialogTitle,
							JOptionPane.QUESTION_MESSAGE,
							null, new Integer[] {
								new Integer(10), new Integer(20),
							  new Integer(30), new Integer(40),
							  new Integer(50), new Integer(55)
							}, new Integer(30));

					if (size != null) {
						font = new Font(name, Font.PLAIN, size.intValue());
					}
				}
			}

			g2d.setPaint(mainWindow.currentPaint);
			g2d.setStroke(mainWindow.currentStroke);
			g2d.setComposite(mainWindow.currentComposite);

			g2d.setFont(font);
			g2d.drawString(text, e.getX(), e.getY());

			frameContent.repaint();
		}
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

	private String inputText() {
		return (String) JOptionPane.showInputDialog(mainWindow,
				"Wprowadz napis tekstowy:",
				toolName,
				JOptionPane.QUESTION_MESSAGE,
				null, null,
				"aA bB cC dD");
	}
}

