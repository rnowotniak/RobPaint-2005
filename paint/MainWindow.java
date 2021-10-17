package paint;

import tools.*;
import java.lang.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.*;

public class MainWindow extends JFrame
	implements ActionListener, ItemListener, MouseListener,
				  MouseMotionListener, WindowListener {

	JMenuBar jmenuBar1;
	JMenuItem menuItem_oProgramie;
	JMenuItem menuItem_Wyczysc;
	JMenuItem menuItem_Splaszcz;
	JMenuItem menuItem_UsunWarstwy;
	JMenuItem menuItem_Wytnij;
	JMenuItem menuItem_Wklej;
	JMenuItem menuItem_Odswiez;
	JMenuItem menuItem_Koniec;
	JMenuItem menuItem_Zapisz;
	JMenuItem menuItem_Otworz;
	JMenuItem menuItem_Nowy;
	JMenuItem menuItem_Modyfikator;
	JMenuItem menuItem_Olowek;
	JMenuItem menuItem_Pedzel;
	JMenuItem menuItem_Linie;
	JMenuItem menuItem_Prostokaty;
	JMenuItem menuItem_ProstokatyFill;
	JMenuItem menuItem_Elipsy;
	JMenuItem menuItem_ElipsyFill;
	JMenuItem menuItem_Rounded;
	JMenuItem menuItem_RoundedFill;
	JMenuItem menuItem_Polygon;
	JMenuItem menuItem_PolygonFill;
	JMenuItem menuItem_cPicker;
	JMenuItem menuItem_TextInsert;
	JMenuItem menuItem_Eraser;
	JMenuItem menuItem_Bucker;
	JMenuItem menuItem_LSystemDraw;
	JCheckBoxMenuItem menuItem_upperToolbar;
	JCheckBoxMenuItem menuItem_toolChooserBar;
	JCheckBoxMenuItem menuItem_colorsToolbar;
	JCheckBoxMenuItem menuItem_statusLine;;
	JCheckBoxMenuItem menuItem_configToolbar;;
	public LSystemDialog lsysDialog1;

	JMenu menu_pomoc;
	JMenu menu_edycja;
	JMenu menu_widok;
	JMenu menu_narzedzia;
	JMenu menu_plik;

	JPanel innerPanel; /* okno oprocz status line */
	JDesktopPane jDesktopPane1;
	public StatusLine statusLine;
	public ToolChooserBox toolChooser;
	public UpperToolbar upperToolbar;
	public ColorsToolbar colorsToolbar;
	public ConfigurationPanel configPanel;
	public JToolBar toolConfigToolbar;

	public Color color1;
	public Color color2;
	public Paint currentPaint;
	public Stroke currentStroke;
	public Composite currentComposite;
	public AffineTransform currentTransform;
	AbstractTool activeTool;
	ModifiableShape cutBuffer;
	LSystem lsystem;


	public MainWindow(String title) {
		super(title);
		addWindowListener(this);

		configureMenuBar();
		initComponents();

		color1 = Color.WHITE;
		color2 = Color.BLACK;
		/* pobrac standardowe ustawienia */
		ModifiableShape ms = new ModifiableShape(
				new Rectangle(1, 1));
		currentPaint = ms.paint;
		currentStroke = ms.stroke;
		currentComposite = ms.composite;
		currentTransform = ms.transform;
		cutBuffer = null;
		lsysDialog1 = null;
		selectTool(ShapeModifier.getTool(this));
		statusLine.updateColorLabels(color1, color2);

		setSize(800, 600);
	}

	private void initComponents() {
		jDesktopPane1 = new JDesktopPane();
		jDesktopPane1.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		innerPanel = new JPanel();
		innerPanel.setLayout(new BorderLayout());
		innerPanel.add(jDesktopPane1);

		statusLine = new StatusLine();
		toolChooser = new ToolChooserBox();
		toolChooser.setOrientation(ToolChooserBox.VERTICAL);

		upperToolbar = new UpperToolbar();
		upperToolbar.setOrientation(ToolChooserBox.HORIZONTAL);

		colorsToolbar = new ColorsToolbar();
		colorsToolbar.setOrientation(ColorsToolbar.HORIZONTAL);

		/**/
		toolConfigToolbar = new JToolBar("Konfiguracja");
		toolConfigToolbar.setLayout(new BorderLayout());
		configPanel = new ConfigurationPanel(this);
		toolConfigToolbar.setOrientation(JToolBar.VERTICAL);
		toolConfigToolbar.setFloatable(false);
		toolConfigToolbar.add(configPanel, BorderLayout.NORTH);
		toolConfigToolbar.add(new JPanel(), BorderLayout.SOUTH);
		getContentPane().add(toolConfigToolbar, BorderLayout.EAST);
		/**/

		getContentPane().add(statusLine, BorderLayout.SOUTH);
		innerPanel.add(toolChooser, BorderLayout.WEST);
		innerPanel.add(upperToolbar, BorderLayout.NORTH);
		innerPanel.add(colorsToolbar, BorderLayout.SOUTH);
		getContentPane().add(innerPanel);

		ImageFrame okno1 = new ImageFrame("Obrazek 1");
		okno1.setVisible(true);
		okno1.setBounds(70, 70, okno1.getWidth(), okno1.getHeight());
		jDesktopPane1.add(okno1);
	}

	private void configureMenuBar() {
		jmenuBar1 = new JMenuBar();

		menu_plik = new JMenu("Plik");
		menu_plik.setMnemonic('p');
		jmenuBar1.add(menu_plik);
		menuItem_Nowy = new JMenuItem("Nowy");
		menuItem_Nowy.setMnemonic('n');
		menu_plik.add(menuItem_Nowy);
		menuItem_Nowy.addActionListener(this);
		menuItem_Otworz = new JMenuItem("Otworz...");
		menuItem_Otworz.setMnemonic('o');
		menu_plik.add(menuItem_Otworz);
		menuItem_Otworz.addActionListener(this);
		menuItem_Zapisz = new JMenuItem("Zapisz...");
		menu_plik.add(menuItem_Zapisz);
		menuItem_Zapisz.addActionListener(this);
		menu_plik.addSeparator();
		menuItem_Koniec = new JMenuItem("Koniec");
		menuItem_Koniec.setMnemonic('k');
		menu_plik.add(menuItem_Koniec);
		menuItem_Koniec.addActionListener(this);

		menu_edycja = new JMenu("Edycja");
		jmenuBar1.add(menu_edycja);
		menuItem_Odswiez = new JMenuItem("Odswiez");
		menu_edycja.add(menuItem_Odswiez);
		menuItem_Odswiez.addActionListener(this);
		menuItem_Wyczysc = new JMenuItem("Wyczysc wszystko");
		menu_edycja.add(menuItem_Wyczysc);
		menuItem_Wyczysc.addActionListener(this);
		menu_edycja.addSeparator();
		menuItem_Splaszcz = new JMenuItem("Splaszcz warstwy");
		menu_edycja.add(menuItem_Splaszcz);
		menuItem_Splaszcz.addActionListener(this);
		menuItem_UsunWarstwy = new JMenuItem("Usun warstwy");
		menu_edycja.add(menuItem_UsunWarstwy);
		menuItem_UsunWarstwy.addActionListener(this);
		menuItem_Wytnij = new JMenuItem("Wytnij");
		menu_edycja.add(menuItem_Wytnij);
		menuItem_Wytnij.addActionListener(this);
		menuItem_Wklej = new JMenuItem("Wklej");
		menu_edycja.add(menuItem_Wklej);
		menuItem_Wklej.addActionListener(this);

		menu_widok = new JMenu("Widok");
		jmenuBar1.add(menu_widok);
		menuItem_colorsToolbar = new JCheckBoxMenuItem("Pasek kolorow", true);
		menu_widok.add(menuItem_colorsToolbar);
		menuItem_colorsToolbar.addActionListener(this);
		menuItem_toolChooserBar = new JCheckBoxMenuItem("Przybornik", true);
		menu_widok.add(menuItem_toolChooserBar);
		menuItem_toolChooserBar.addActionListener(this);
		menuItem_upperToolbar = new JCheckBoxMenuItem("Gorny pasek narzedziowy", true);
		menu_widok.add(menuItem_upperToolbar);
		menuItem_upperToolbar.addActionListener(this);
		menuItem_statusLine = new JCheckBoxMenuItem("Linia statusu", true);
		menu_widok.add(menuItem_statusLine);
		menuItem_statusLine.addActionListener(this);
		menuItem_configToolbar = new JCheckBoxMenuItem("Konfiguracja narzedzia", true);
		menu_widok.add(menuItem_configToolbar);
		menuItem_configToolbar.addActionListener(this);

		menu_narzedzia = new JMenu("Narzedzia");
		jmenuBar1.add(menu_narzedzia);
		menuItem_Modyfikator = new JMenuItem("Modyfikator");
		menu_narzedzia.add(menuItem_Modyfikator);
		menuItem_Modyfikator.addActionListener(this);
		menu_narzedzia.addSeparator();
		menuItem_Olowek = new JMenuItem("Olowek");
		menu_narzedzia.add(menuItem_Olowek);
		menuItem_Olowek.addActionListener(this);
		menuItem_Pedzel = new JMenuItem("Pedzel");
		menu_narzedzia.add(menuItem_Pedzel);
		menuItem_Pedzel.addActionListener(this);
		menuItem_Linie = new JMenuItem("Linie");
		menu_narzedzia.add(menuItem_Linie);
		menuItem_Linie.addActionListener(this);
		menu_narzedzia.addSeparator();
		menuItem_Prostokaty = new JMenuItem("Prostokaty puste");
		menu_narzedzia.add(menuItem_Prostokaty);
		menuItem_Prostokaty.addActionListener(this);
		menuItem_ProstokatyFill = new JMenuItem("Prostokaty pelne");
		menu_narzedzia.add(menuItem_ProstokatyFill);
		menuItem_ProstokatyFill.addActionListener(this);
		menuItem_Elipsy = new JMenuItem("Elipsy puste");
		menu_narzedzia.add(menuItem_Elipsy);
		menuItem_Elipsy.addActionListener(this);
		menuItem_ElipsyFill = new JMenuItem("Elipsy pelne");
		menu_narzedzia.add(menuItem_ElipsyFill);
		menuItem_ElipsyFill.addActionListener(this);
		menuItem_Rounded = new JMenuItem("Prostokaty zaokroglane");
		menu_narzedzia.add(menuItem_Rounded);
		menuItem_Rounded.addActionListener(this);
		menuItem_RoundedFill = new JMenuItem("Zaokraglone pelne");
		menu_narzedzia.add(menuItem_RoundedFill);
		menuItem_RoundedFill.addActionListener(this);
		menuItem_Polygon = new JMenuItem("Wielokaty puste");
		menu_narzedzia.add(menuItem_Polygon);
		menuItem_Polygon.addActionListener(this);
		menuItem_PolygonFill = new JMenuItem("Wielokaty pelne");
		menu_narzedzia.add(menuItem_PolygonFill);
		menuItem_PolygonFill.addActionListener(this);
		menu_narzedzia.addSeparator();
		menuItem_cPicker = new JMenuItem("Pobieranie koloru");
		menu_narzedzia.add(menuItem_cPicker);
		menuItem_cPicker.addActionListener(this);
		menuItem_TextInsert = new JMenuItem("Pisanie tekstem");
		menu_narzedzia.add(menuItem_TextInsert);
		menuItem_TextInsert.addActionListener(this);
		menuItem_Eraser = new JMenuItem("Wycieranie");
		menu_narzedzia.add(menuItem_Eraser);
		menuItem_Eraser.addActionListener(this);
		menuItem_Bucker = new JMenuItem("Wypelnianie kolorem");
		menu_narzedzia.add(menuItem_Bucker);
		menuItem_Bucker.addActionListener(this);
		menuItem_LSystemDraw = new JMenuItem("Rysowanie L-Systemow");
		menu_narzedzia.add(menuItem_LSystemDraw);
		menuItem_LSystemDraw.addActionListener(this);


		jmenuBar1.add(Box.createHorizontalGlue());
		menu_pomoc = new JMenu("Pomoc");
		menu_pomoc.setMnemonic('c');
		jmenuBar1.add(menu_pomoc);
		menuItem_oProgramie = new JMenuItem("O programie...");
		menu_pomoc.add(menuItem_oProgramie);
		menuItem_oProgramie.addActionListener(this);

		setJMenuBar(jmenuBar1);
	}

	private void selectTool(AbstractTool tool) {
		activeTool = tool;
		statusLine.setMessage("Wybrano: " + tool.toolName);
		statusLine.setToolName(tool.toolName);
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == menuItem_Koniec) {
			System.exit(0);
		} else if (event.getSource() == menuItem_Nowy
				|| event.getSource() == upperToolbar.newFileButton) {
			ImageFrame newImageFrame = new ImageFrame("Nowy obrazek");
			newImageFrame.setVisible(true);
			newImageFrame.setBounds(10, 10,
					newImageFrame.getWidth(), newImageFrame.getHeight());
			jDesktopPane1.add(newImageFrame);
		} else if (event.getSource() == menuItem_Otworz
				|| event.getSource() == upperToolbar.openFileButton) {
			JFileChooser chooser = new JFileChooser(".");
			chooser.setFileFilter(new ImageFilter());
			int returnVal = chooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				ImageFrame okno1 = new ImageFrame(file);
				okno1.setVisible(true);
				okno1.setLocation(70, 70);
				jDesktopPane1.add(okno1);
			}
		} else if (event.getSource() == menuItem_Zapisz
				|| event.getSource() == upperToolbar.saveFileButton) {
			ImageFrame activeFrame =
				(ImageFrame) jDesktopPane1.getSelectedFrame();
			if (activeFrame != null) {
				JFileChooser chooser = new JFileChooser(".");
				chooser.setFileFilter(new ImageFilter());
				int returnVal = chooser.showSaveDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					try  {
						String ext = file.getName();
						if (ext.matches(".*\\....$")) {
							ext = ext.replaceAll("^.*\\.", "");
						}
						boolean result = ImageIO.write(
								(BufferedImage) activeFrame.getImage(),
								ext, file);
						if (result == false) {
							JOptionPane.showMessageDialog(this,
									"Nieznane rozszerzenie pliku");
						}
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(this,
								"Nie udalo sie zapisac do pliku.");
					}
				}
			}
		} else if (event.getSource() == menuItem_upperToolbar) {
			upperToolbar.setVisible(menuItem_upperToolbar.getState());
		} else if (event.getSource() == menuItem_toolChooserBar) {
			toolChooser.setVisible(menuItem_toolChooserBar.getState());
		} else if (event.getSource() == menuItem_colorsToolbar) {
			colorsToolbar.setVisible(menuItem_colorsToolbar.getState());
		} else if (event.getSource() == menuItem_statusLine) {
			statusLine.setVisible(menuItem_statusLine.getState());
		} else if (event.getSource() == menuItem_configToolbar) {
			toolConfigToolbar.setVisible(menuItem_configToolbar.getState());
		} else if (event.getSource() == menuItem_oProgramie
				|| event.getSource() == upperToolbar.helpButton) {
			JDialog dialog = new aboutWindow(this);
			dialog.setVisible(true);
		} else if (event.getSource() == upperToolbar.filtersCombo) {
			ImageFrame activeFrame = 
				(ImageFrame) jDesktopPane1.getSelectedFrame();
			if (activeFrame != null) {
				int option = upperToolbar.filtersCombo.getSelectedIndex();

				BufferedImage bi = activeFrame.getImage();
				ImageProcessor filter = new ImageProcessor(bi);
				activeFrame.setImage(filter.apply(option));
				activeFrame.content.repaint();
			}
		} else if (event.getSource() == menuItem_Splaszcz
				|| event.getSource() == upperToolbar.flattenButton) {
			ImageFrame activeFrame = 
				(ImageFrame) jDesktopPane1.getSelectedFrame();
			if (activeFrame != null) {
				activeFrame.content.flatten();
			}
		} else if (event.getSource() == menuItem_Wyczysc) {
			ImageFrame activeFrame = 
				(ImageFrame) jDesktopPane1.getSelectedFrame();
			if (activeFrame != null) {
				Image i = activeFrame.content.image;
				Graphics2D g2d = (Graphics2D) i.getGraphics();
				g2d.clearRect(0, 0, i.getWidth(null), i.getHeight(null));
				activeFrame.content.repaint();
			}
		} else if (event.getSource() == menuItem_UsunWarstwy
				|| event.getSource() == upperToolbar.deleteButton) {
			ImageFrame activeFrame = 
				(ImageFrame) jDesktopPane1.getSelectedFrame();
			if (activeFrame != null) {
				activeFrame.content.deleteShapes();
			}
		} else if (event.getSource() == menuItem_Wytnij
				|| event.getSource() == upperToolbar.cutButton) {
			ImageFrame activeFrame = 
				(ImageFrame) jDesktopPane1.getSelectedFrame();
			if (activeFrame != null
					&& activeFrame.content.selectedShape != null) {
				cutBuffer = activeFrame.content.selectedShape;
				activeFrame.content.mShapes.remove(cutBuffer);
				activeFrame.repaint();
				statusLine.setMessage("Warstwa schowana do bufora");
					}
		} else if (event.getSource() == menuItem_Wklej
				|| event.getSource() == upperToolbar.pasteButton) {
			ImageFrame activeFrame = 
				(ImageFrame) jDesktopPane1.getSelectedFrame();
			if (activeFrame != null
					&& cutBuffer != null) {
				activeFrame.content.mShapes.add(cutBuffer);
				activeFrame.repaint();
				statusLine.setMessage("Warstwa zostala wklejona");
					}
		} else if (event.getSource() == menuItem_Modyfikator) {
			toolChooser.actionPerformed(
					new ActionEvent(toolChooser.jButton1,
						ActionEvent.ACTION_PERFORMED, ""));
		} else if (event.getSource() == menuItem_Olowek) {
			toolChooser.actionPerformed(
					new ActionEvent(toolChooser.jButton2,
						ActionEvent.ACTION_PERFORMED, ""));
		} else if (event.getSource() == menuItem_Pedzel) {
			toolChooser.actionPerformed(
					new ActionEvent(toolChooser.jButton3,
						ActionEvent.ACTION_PERFORMED, ""));
		} else if (event.getSource() == menuItem_Linie) {
			toolChooser.actionPerformed(
					new ActionEvent(toolChooser.jButton4,
						ActionEvent.ACTION_PERFORMED, ""));
		} else if (event.getSource() == menuItem_Prostokaty) {
			toolChooser.actionPerformed(
					new ActionEvent(toolChooser.jButton5,
						ActionEvent.ACTION_PERFORMED, ""));
		} else if (event.getSource() == menuItem_ProstokatyFill) {
			toolChooser.actionPerformed(
					new ActionEvent(toolChooser.jButton6,
						ActionEvent.ACTION_PERFORMED, ""));
		} else if (event.getSource() == menuItem_Elipsy) {
			toolChooser.actionPerformed(
					new ActionEvent(toolChooser.jButton7,
						ActionEvent.ACTION_PERFORMED, ""));
		} else if (event.getSource() == menuItem_ElipsyFill) {
			toolChooser.actionPerformed(
					new ActionEvent(toolChooser.jButton8,
						ActionEvent.ACTION_PERFORMED, ""));
		} else if (event.getSource() == menuItem_Rounded) {
			toolChooser.actionPerformed(
					new ActionEvent(toolChooser.jButton9,
						ActionEvent.ACTION_PERFORMED, ""));
		} else if (event.getSource() == menuItem_RoundedFill) {
			toolChooser.actionPerformed(
					new ActionEvent(toolChooser.jButton10,
						ActionEvent.ACTION_PERFORMED, ""));
		} else if (event.getSource() == menuItem_Polygon) {
			toolChooser.actionPerformed(
					new ActionEvent(toolChooser.jButton15,
						ActionEvent.ACTION_PERFORMED, ""));
		} else if (event.getSource() == menuItem_PolygonFill) {
			toolChooser.actionPerformed(
					new ActionEvent(toolChooser.jButton16,
						ActionEvent.ACTION_PERFORMED, ""));
		} else if (event.getSource() == menuItem_cPicker) {
			toolChooser.actionPerformed(
					new ActionEvent(toolChooser.jButton11,
						ActionEvent.ACTION_PERFORMED, ""));
		} else if (event.getSource() == menuItem_TextInsert) {
			toolChooser.actionPerformed(
					new ActionEvent(toolChooser.jButton12,
						ActionEvent.ACTION_PERFORMED, ""));
		} else if (event.getSource() == menuItem_Eraser) {
			toolChooser.actionPerformed(
					new ActionEvent(toolChooser.jButton13,
						ActionEvent.ACTION_PERFORMED, ""));
		} else if (event.getSource() == menuItem_Bucker) {
			toolChooser.actionPerformed(
					new ActionEvent(toolChooser.jButton14,
						ActionEvent.ACTION_PERFORMED, ""));
		} else if (event.getSource() == menuItem_LSystemDraw) {
			toolChooser.actionPerformed(
					new ActionEvent(toolChooser.jButton17,
						ActionEvent.ACTION_PERFORMED, ""));
		}


	}


	public void itemStateChanged(ItemEvent event) {
		if (event.getSource() == null) {

		}
	}

	/*
	 * Zwiazki zaprogramowane.
	 * Odeslanie obslugi zdarzen do aktywnego narzedzia.
	 */
	public void mouseClicked(MouseEvent e) {
		if (activeTool != null
				&& e.getSource() instanceof ImageFrame.Content)
			activeTool.mouseClicked(e);
	}
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mousePressed(MouseEvent e) {
		if (activeTool != null
				&& e.getSource() instanceof ImageFrame.Content)
			activeTool.mousePressed(e);
	}
	public void mouseReleased(MouseEvent e)  {
		if (activeTool != null
				&& e.getSource() instanceof ImageFrame.Content)
			activeTool.mouseReleased(e);
	}
	public void	mouseDragged(MouseEvent e) {
		if (activeTool != null
				&& e.getSource() instanceof ImageFrame.Content)
			activeTool.mouseDragged(e);
	}
	public void	mouseMoved(MouseEvent e) {
		statusLine.setXY(e.getX(), e.getY());
		if (activeTool != null
				&& e.getSource() instanceof ImageFrame.Content)
			activeTool.mouseMoved(e);
	}

	public void windowActivated(WindowEvent e) { }
	public void windowClosed(WindowEvent e) { }
	public void windowDeactivated(WindowEvent e) { }
	public void windowDeiconified(WindowEvent e) { }
	public void windowIconified(WindowEvent e) { }
	public void windowOpened(WindowEvent e) { }
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}




	/* * * * * * * * * * *
	 * Klasy wewnetrzne
	 * * * * * * * * * * */

	class ColorSelectListener implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				color1 = ((Component) (e.getSource())).getBackground();
				statusLine.updateColorLabels(color1, color2);
				colorsToolbar.selectedColor1.setBackground(color1);
				colorsToolbar.redSlider.setValue(color1.getRed());
				colorsToolbar.greenSlider.setValue(color1.getGreen());
				colorsToolbar.blueSlider.setValue(color1.getBlue());
				configPanel.jKolorButton1.setBackground(color1);
				configPanel.jKolorButton.setSelected(true);
				currentPaint = color1;
			}
			if (e.getButton() == MouseEvent.BUTTON2
					|| e.getButton() == MouseEvent.BUTTON3) {
				color2 = ((Component) (e.getSource())).getBackground();
				statusLine.updateColorLabels(color1, color2);
				colorsToolbar.selectedColor2.setBackground(color2);
				colorsToolbar.redSlider.setValue(color2.getRed());
				colorsToolbar.greenSlider.setValue(color2.getGreen());
				colorsToolbar.blueSlider.setValue(color2.getBlue());
				configPanel.jKolorButton2.setBackground(color2);
				configPanel.jKolorButton.setSelected(true);
				currentPaint = color2;
			}
		}
		public void mouseEntered(MouseEvent e) { }
		public void mouseExited(MouseEvent e) { }
		public void mousePressed(MouseEvent e) { }
		public void mouseReleased(MouseEvent e) { }
	}

	/*
	 * Wewnetrzne okienko z obrazkiem
	 */
	public class ImageFrame extends JInternalFrame {
		public class Content extends JPanel {
			public BufferedImage image;
			public LinkedList mShapes;
			public ModifiableShape selectedShape;

			public Content() {
				setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
				mShapes = new LinkedList();
				selectedShape = null;
			}

			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.drawImage(image, 0, 0, this);
				/* narysowanie wektorowych elementow */
				drawShapes(g2d);
			}

			/* splaszczenie warstw */
			public void flatten() {
				Iterator i = mShapes.iterator();
				Graphics2D iGraphics = (Graphics2D) image.getGraphics();
				int size = mShapes.size();
				drawShapes(iGraphics);
				mShapes.clear();
				statusLine.setMessage("" + size + " warstw splaszczonych");
			}

			/* usuniecie warstw */
			public void deleteShapes() {
				int size = mShapes.size();
				mShapes.clear();
				statusLine.setMessage("Usunieto " + size + "warstw");
				repaint();
			}

			/* narysowanie warstw */
			private void drawShapes(Graphics2D g2d) {
				AffineTransform defaultTransform =
					g2d.getTransform();
				Iterator i = mShapes.iterator();
				while (i.hasNext()) {
					ModifiableShape ms = (ModifiableShape) i.next();
					if (ms == selectedShape) {
						g2d.setPaint(ModifiableShape.selectedShapePaint);
					} else {
						g2d.setPaint(ms.paint);
					}
					g2d.setStroke(ms.stroke);
					//g2d.setComposite(AlphaComposite.SrcOver);
					g2d.setComposite(ms.composite);
					// g2d.setTransform(defaultTransform);
					g2d.transform(ms.transform);
					if (ms.isFilled) {
						g2d.fill(ms.shape);
					} else {
						g2d.draw(ms.shape);
					}
				}
			}
		}

		JScrollPane jScrollPane1;
		Content content;

		public ImageFrame(String title) {
			super(title, true, true, true, true);
			content = new Content();
			content.addMouseMotionListener(MainWindow.this);
			content.addMouseListener(MainWindow.this);
			setImage(new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB));
			setSize(500, 500);

			jScrollPane1 = new JScrollPane(content);
			jScrollPane1.setHorizontalScrollBarPolicy(
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			jScrollPane1.setVerticalScrollBarPolicy(
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			getContentPane().add(jScrollPane1);

			content.setPreferredSize(
					new Dimension(getImage().getWidth(null),
						getImage().getHeight(null)));

			Dimension dim1 = new Dimension(500, 500);

			jScrollPane1.setViewportView(content);
			setSize(dim1.width + 15, dim1.height + 35);

			dim1 = new Dimension(500, 500);
			content.setPreferredSize(dim1);

			/* XX dla testu, dodanie shapow */
			if (false) {
				Ellipse2D.Float e = new Ellipse2D.Float(50, 50, 300, 120);
				RoundRectangle2D.Float rr = new RoundRectangle2D.Float(
						150, 80, 100, 200, 20, 40);
				content.mShapes.addFirst(new ModifiableShape(e));
				content.mShapes.addFirst(new ModifiableShape(rr));
			}
		}
		public ImageFrame() {
			this("Obrazek");
		}
		public ImageFrame(File file) {
			this(file.getName());
			Image loaded = new ImageIcon(file.getAbsolutePath()).getImage();
			BufferedImage i = new BufferedImage(
					loaded.getWidth(null), loaded.getHeight(null),
					BufferedImage.TYPE_INT_RGB);
			i.getGraphics().drawImage(loaded, 0, 0, this);
			setImage(i);

			Dimension dim1 = 
					new Dimension(loaded.getWidth(null), loaded.getHeight(null));

			jScrollPane1.setViewportView(content);
			setSize(dim1.width + 15, dim1.height + 35);

			dim1 = new Dimension(loaded.getWidth(null), loaded.getHeight(null));
			content.setPreferredSize(dim1);
		}

		public BufferedImage getImage() {
			return content.image;
		}
		public Image setImage(BufferedImage i) {
			content.image = i;
			return i;
		}
	}

	/* * * * * * * * * * * * * * * *
	 *  Klasy paskow narzedziowych
	 * * * * * * * * * * * * * * * */

	public class ColorsToolbar extends JToolBar {
		public ColorsToolbar() {
			initComponents();
		}

		private void initComponents() {
			jPanel1 = new javax.swing.JPanel();
			redSlider = new javax.swing.JSlider();
			greenSlider = new javax.swing.JSlider();
			blueSlider = new javax.swing.JSlider();
			jPanel2 = new javax.swing.JPanel();
			selectedColor1 = new javax.swing.JButton();
			selectedColor2 = new javax.swing.JButton();
			jPanel3 = new javax.swing.JPanel();
			jButton1 = new javax.swing.JButton();
			jButton2 = new javax.swing.JButton();
			jButton3 = new javax.swing.JButton();
			jButton4 = new javax.swing.JButton();
			jButton5 = new javax.swing.JButton();
			jButton6 = new javax.swing.JButton();
			jButton7 = new javax.swing.JButton();
			jButton8 = new javax.swing.JButton();
			jButton9 = new javax.swing.JButton();
			jButton10 = new javax.swing.JButton();
			jButton11 = new javax.swing.JButton();
			jButton12 = new javax.swing.JButton();
			jButton13 = new javax.swing.JButton();
			jButton14 = new javax.swing.JButton();
			jButton15 = new javax.swing.JButton();
			jButton16 = new javax.swing.JButton();
			jButton17 = new javax.swing.JButton();
			jButton18 = new javax.swing.JButton();
			jButton19 = new javax.swing.JButton();
			jButton20 = new javax.swing.JButton();

			setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

			jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

			redSlider.setMaximum(255);
			redSlider.setToolTipText("R");
			redSlider.setValue(100);
			redSlider.setPreferredSize(new java.awt.Dimension(83, 16));
			jPanel1.add(redSlider);

			greenSlider.setMaximum(255);
			greenSlider.setToolTipText("G");
			greenSlider.setValue(100);
			greenSlider.setPreferredSize(new java.awt.Dimension(83, 16));
			jPanel1.add(greenSlider);

			blueSlider.setMaximum(255);
			blueSlider.setToolTipText("B");
			blueSlider.setValue(100);
			blueSlider.setPreferredSize(new java.awt.Dimension(83, 16));
			jPanel1.add(blueSlider);

			add(jPanel1);

			jPanel2.setLayout(null);

			jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
			jPanel2.setPreferredSize(new java.awt.Dimension(50, 40));
			selectedColor1.setBackground(new java.awt.Color(51, 102, 255));
			selectedColor1.setText("1");
			selectedColor1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
			jPanel2.add(selectedColor1);
			selectedColor1.setBounds(0, 0, 30, 30);

			selectedColor2.setBackground(new java.awt.Color(153, 255, 153));
			selectedColor2.setText("2");
			selectedColor2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
			jPanel2.add(selectedColor2);
			selectedColor2.setBounds(20, 10, 30, 30);

			add(jPanel2);

			jPanel3.setLayout(new java.awt.GridLayout(2, 25));

			jButton1.setBackground(new java.awt.Color(255, 255, 255));
			jButton1.setPreferredSize(new java.awt.Dimension(23, 23));

			jPanel3.add(jButton1);

			jButton2.setBackground(new java.awt.Color(102, 255, 0));
			jButton2.setPreferredSize(new java.awt.Dimension(23, 23));
			jPanel3.add(jButton2);

			jButton3.setBackground(new java.awt.Color(153, 0, 153));
			jButton3.setPreferredSize(new java.awt.Dimension(23, 23));
			jPanel3.add(jButton3);

			jButton4.setBackground(new java.awt.Color(0, 153, 0));
			jButton4.setPreferredSize(new java.awt.Dimension(23, 23));
			jPanel3.add(jButton4);

			jButton5.setBackground(new java.awt.Color(51, 204, 0));
			jButton5.setPreferredSize(new java.awt.Dimension(23, 23));
			jPanel3.add(jButton5);

			jButton6.setBackground(new java.awt.Color(0, 153, 153));
			jButton6.setPreferredSize(new java.awt.Dimension(23, 23));
			jPanel3.add(jButton6);

			jButton7.setBackground(new java.awt.Color(255, 102, 204));
			jButton7.setPreferredSize(new java.awt.Dimension(23, 23));
			jPanel3.add(jButton7);

			jButton8.setBackground(new java.awt.Color(255, 102, 102));
			jButton8.setPreferredSize(new java.awt.Dimension(23, 23));
			jPanel3.add(jButton8);

			jButton9.setBackground(new java.awt.Color(204, 102, 0));
			jButton9.setPreferredSize(new java.awt.Dimension(23, 23));
			jPanel3.add(jButton9);

			jButton10.setBackground(new java.awt.Color(0, 153, 0));
			jButton10.setPreferredSize(new java.awt.Dimension(23, 23));
			jPanel3.add(jButton10);

			jButton11.setBackground(new java.awt.Color(0, 0, 0));
			jButton11.setPreferredSize(new java.awt.Dimension(23, 23));
			jPanel3.add(jButton11);

			jButton12.setBackground(new java.awt.Color(255, 51, 204));
			jButton12.setPreferredSize(new java.awt.Dimension(23, 23));
			jPanel3.add(jButton12);

			jButton13.setBackground(new java.awt.Color(153, 153, 0));
			jButton13.setPreferredSize(new java.awt.Dimension(23, 23));
			jPanel3.add(jButton13);

			jButton14.setBackground(new java.awt.Color(255, 0, 102));
			jButton14.setPreferredSize(new java.awt.Dimension(23, 23));
			jPanel3.add(jButton14);

			jButton15.setBackground(new java.awt.Color(102, 102, 255));
			jButton15.setPreferredSize(new java.awt.Dimension(23, 23));
			jPanel3.add(jButton15);

			jButton16.setBackground(new java.awt.Color(255, 255, 0));
			jButton16.setPreferredSize(new java.awt.Dimension(23, 23));
			jPanel3.add(jButton16);

			jButton17.setBackground(new java.awt.Color(0, 255, 255));
			jButton17.setPreferredSize(new java.awt.Dimension(23, 23));
			jPanel3.add(jButton17);

			jButton18.setBackground(new java.awt.Color(204, 204, 255));
			jButton18.setPreferredSize(new java.awt.Dimension(23, 23));
			jPanel3.add(jButton18);

			jButton19.setBackground(new java.awt.Color(0, 102, 102));
			jButton19.setPreferredSize(new java.awt.Dimension(23, 23));
			jPanel3.add(jButton19);

			jButton20.setBackground(new java.awt.Color(255, 51, 255));
			jButton20.setPreferredSize(new java.awt.Dimension(23, 23));
			jPanel3.add(jButton20);

			ColorSelectListener colorSelect = new ColorSelectListener();
			jButton1.addMouseListener(colorSelect);
			jButton2.addMouseListener(colorSelect);
			jButton3.addMouseListener(colorSelect);
			jButton4.addMouseListener(colorSelect);
			jButton5.addMouseListener(colorSelect);
			jButton6.addMouseListener(colorSelect);
			jButton7.addMouseListener(colorSelect);
			jButton8.addMouseListener(colorSelect);
			jButton9.addMouseListener(colorSelect);
			jButton10.addMouseListener(colorSelect);
			jButton11.addMouseListener(colorSelect);
			jButton12.addMouseListener(colorSelect);
			jButton13.addMouseListener(colorSelect);
			jButton14.addMouseListener(colorSelect);
			jButton15.addMouseListener(colorSelect);
			jButton16.addMouseListener(colorSelect);
			jButton17.addMouseListener(colorSelect);
			jButton18.addMouseListener(colorSelect);
			jButton19.addMouseListener(colorSelect);
			jButton20.addMouseListener(colorSelect);

			ColorSelectListener colorDial = new ColorSelectListener() {
				public void mouseClicked(MouseEvent e) {
					Component comp = ((Component) (e.getSource()));
					ColorDialog dial = 
						new ColorDialog(MainWindow.this, comp.getBackground());
					comp.setBackground(dial.getColor());
					super.mouseClicked(e);
				}
			};
			selectedColor1.addMouseListener(colorDial);
			selectedColor2.addMouseListener(colorDial);

			/* kolejne 8 * 2 przyciskow kolorow */
			for (int i = 0; i < 8 * 2; i++) {
				JButton b = new JButton();
				b.setBackground(new Color(
							(int) (Math.random() * 256),
							(int) (Math.random() * 256),
							(int) (Math.random() * 256)));
				b.setPreferredSize(new Dimension(23, 23));
				b.addMouseListener(colorSelect);
				jPanel3.add(b);
			}

			add(jPanel3);

		}


		private javax.swing.JButton jButton1;
		private javax.swing.JButton jButton10;
		private javax.swing.JButton jButton11;
		private javax.swing.JButton jButton12;
		private javax.swing.JButton jButton13;
		private javax.swing.JButton jButton14;
		private javax.swing.JButton jButton15;
		private javax.swing.JButton jButton16;
		private javax.swing.JButton jButton17;
		private javax.swing.JButton jButton18;
		private javax.swing.JButton jButton19;
		private javax.swing.JButton jButton2;
		private javax.swing.JButton jButton20;
		public javax.swing.JButton selectedColor1;
		public javax.swing.JButton selectedColor2;
		private javax.swing.JButton jButton3;
		private javax.swing.JButton jButton4;
		private javax.swing.JButton jButton5;
		private javax.swing.JButton jButton6;
		private javax.swing.JButton jButton7;
		private javax.swing.JButton jButton8;
		private javax.swing.JButton jButton9;
		private javax.swing.JPanel jPanel1;
		private javax.swing.JPanel jPanel2;
		private javax.swing.JPanel jPanel3;
		private javax.swing.JSlider redSlider;
		private javax.swing.JSlider greenSlider;
		private javax.swing.JSlider blueSlider;
	}

	class UpperToolbar extends JToolBar {
		public UpperToolbar() {
			initComponents();
		}

		private void initComponents() {
			newFileButton = new javax.swing.JButton();
			openFileButton = new javax.swing.JButton();
			saveFileButton = new javax.swing.JButton();
			cutButton = new javax.swing.JButton();
			pasteButton = new javax.swing.JButton();
			helpButton = new javax.swing.JButton();
			flattenButton = new javax.swing.JButton();
			deleteButton = new javax.swing.JButton();
			redrawButton = new javax.swing.JButton();
			filtersCombo = new javax.swing.JComboBox();

			newFileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/newFile.jpg")));
			add(newFileButton);
			newFileButton.addActionListener(MainWindow.this);
			openFileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/openFile.jpg")));
			add(openFileButton);
			openFileButton.addActionListener(MainWindow.this);
			saveFileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/saveFile.jpg")));
			add(saveFileButton);
			saveFileButton.addActionListener(MainWindow.this);

			addSeparator();
			cutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cut.jpg")));
			add(cutButton);
			cutButton.addActionListener(MainWindow.this);
			pasteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/paste.jpg")));
			add(pasteButton);
			pasteButton.addActionListener(MainWindow.this);

			addSeparator();
			redrawButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/redraw.jpg")));
			add(redrawButton);
			redrawButton.addActionListener(MainWindow.this);
			deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/trashcan.jpg")));
			add(deleteButton);
			deleteButton.addActionListener(MainWindow.this);
			flattenButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/flatten.jpg")));
			add(flattenButton);
			flattenButton.addActionListener(MainWindow.this);

			addSeparator();
			helpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/help.jpg")));
			add(helpButton);
			helpButton.addActionListener(MainWindow.this);

			addSeparator();
			filtersCombo.setModel(new DefaultComboBoxModel(
						new String[] {
							"Rozmycie 3 x 3",
							"Wyostrzenie 3 x 3",
							"Wykrywanie krawedzi 3 x 3",
							"Wykrywanie krawedzi 5 x 5" }));
			filtersCombo.addActionListener(MainWindow.this);
			add(filtersCombo);
		}

		private javax.swing.JButton newFileButton;
		private javax.swing.JButton openFileButton;
		private javax.swing.JButton saveFileButton;
		private javax.swing.JButton cutButton;
		private javax.swing.JButton pasteButton;
		private javax.swing.JButton helpButton;
		private javax.swing.JButton flattenButton;
		private javax.swing.JButton deleteButton;
		private javax.swing.JButton redrawButton;
		private javax.swing.JComboBox filtersCombo;
	}


	class ToolChooserBox extends JToolBar
			implements ActionListener {

			public ToolChooserBox() {
				initComponents();
			}

			private void initComponents() {
				java.awt.GridBagConstraints gridBagConstraints;

				jPanel1 = new javax.swing.JPanel();
				jButton1 = new javax.swing.JButton();
				jButton2 = new javax.swing.JButton();
				jButton3 = new javax.swing.JButton();
				jButton4 = new javax.swing.JButton();
				jButton5 = new javax.swing.JButton();
				jButton6 = new javax.swing.JButton();
				jButton7 = new javax.swing.JButton();
				jButton8 = new javax.swing.JButton();
				jButton9 = new javax.swing.JButton();
				jButton10 = new javax.swing.JButton();
				jButton11 = new javax.swing.JButton();
				jButton12 = new javax.swing.JButton();
				jButton13 = new javax.swing.JButton();
				jButton14 = new javax.swing.JButton();
				jButton15 = new javax.swing.JButton();
				jButton16 = new javax.swing.JButton();
				jButton17 = new javax.swing.JButton();
				jLabel1 = new javax.swing.JLabel();

				setLayout(new java.awt.BorderLayout());

				setMinimumSize(null);
				setPreferredSize(new java.awt.Dimension(85, 130));
				setMinimumSize(null);
				setPreferredSize(null);
				jPanel1.setLayout(new java.awt.GridBagLayout());

				jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/arrow.jpg")));
				jButton1.setMinimumSize(new java.awt.Dimension(27, 27));
				jButton1.setPreferredSize(new java.awt.Dimension(27, 27));
				jButton1.addActionListener(this);
				gridBagConstraints = new java.awt.GridBagConstraints();
				gridBagConstraints.gridx = 0;
				gridBagConstraints.gridy = 0;
				gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
				jPanel1.add(jButton1, gridBagConstraints);

				jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/freehand.jpg")));
				jButton2.setMinimumSize(new java.awt.Dimension(27, 27));
				jButton2.setPreferredSize(new java.awt.Dimension(27, 27));
				jButton2.addActionListener(this);
				gridBagConstraints = new java.awt.GridBagConstraints();
				gridBagConstraints.gridx = 1;
				gridBagConstraints.gridy = 0;
				gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
				jPanel1.add(jButton2, gridBagConstraints);

				jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/brush.jpg")));
				jButton3.setMinimumSize(new java.awt.Dimension(27, 27));
				jButton3.setPreferredSize(new java.awt.Dimension(27, 27));
				jButton3.addActionListener(this);
				gridBagConstraints = new java.awt.GridBagConstraints();
				gridBagConstraints.gridx = 0;
				gridBagConstraints.gridy = 1;
				gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
				jPanel1.add(jButton3, gridBagConstraints);

				jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/line.jpg")));
				jButton4.setMinimumSize(new java.awt.Dimension(27, 27));
				jButton4.setPreferredSize(new java.awt.Dimension(27, 27));
				jButton4.addActionListener(this);
				gridBagConstraints = new java.awt.GridBagConstraints();
				gridBagConstraints.gridx = 1;
				gridBagConstraints.gridy = 1;
				gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
				jPanel1.add(jButton4, gridBagConstraints);

				jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/rect.jpg")));
				jButton5.setMinimumSize(new java.awt.Dimension(27, 27));
				jButton5.setPreferredSize(new java.awt.Dimension(27, 27));
				jButton5.addActionListener(this);
				gridBagConstraints = new java.awt.GridBagConstraints();
				gridBagConstraints.gridx = 0;
				gridBagConstraints.gridy = 2;
				gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
				jPanel1.add(jButton5, gridBagConstraints);

				jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/rectFilled.jpg")));
				jButton6.setMinimumSize(new java.awt.Dimension(27, 27));
				jButton6.setPreferredSize(new java.awt.Dimension(27, 27));
				jButton6.addActionListener(this);

				gridBagConstraints = new java.awt.GridBagConstraints();
				gridBagConstraints.gridx = 1;
				gridBagConstraints.gridy = 2;
				gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
				jPanel1.add(jButton6, gridBagConstraints);

				jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/circle.jpg")));
				jButton7.setMinimumSize(new java.awt.Dimension(27, 27));
				jButton7.setPreferredSize(new java.awt.Dimension(27, 27));
				jButton7.addActionListener(this);

				gridBagConstraints = new java.awt.GridBagConstraints();
				gridBagConstraints.gridx = 0;
				gridBagConstraints.gridy = 3;
				gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
				jPanel1.add(jButton7, gridBagConstraints);

				jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/circleFilled.jpg")));
				jButton8.setMinimumSize(new java.awt.Dimension(27, 27));
				jButton8.setPreferredSize(new java.awt.Dimension(27, 27));
				jButton8.addActionListener(this);

				gridBagConstraints = new java.awt.GridBagConstraints();
				gridBagConstraints.gridx = 1;
				gridBagConstraints.gridy = 3;
				gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
				jPanel1.add(jButton8, gridBagConstraints);

				jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/roundRect.jpg")));
				jButton9.setMinimumSize(new java.awt.Dimension(27, 27));
				jButton9.setPreferredSize(new java.awt.Dimension(27, 27));
				jButton9.addActionListener(this);
				gridBagConstraints = new java.awt.GridBagConstraints();
				gridBagConstraints.gridx = 0;
				gridBagConstraints.gridy = 4;
				gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
				jPanel1.add(jButton9, gridBagConstraints);

				jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/roundRectFilled.jpg")));
				jButton10.setMinimumSize(new java.awt.Dimension(27, 27));
				jButton10.setPreferredSize(new java.awt.Dimension(27, 27));
				jButton10.addActionListener(this);
				gridBagConstraints = new java.awt.GridBagConstraints();
				gridBagConstraints.gridx = 1;
				gridBagConstraints.gridy = 4;
				gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
				jPanel1.add(jButton10, gridBagConstraints);


				jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/polygon.jpg")));
				jButton15.setMinimumSize(new java.awt.Dimension(27, 27));
				jButton15.setPreferredSize(new java.awt.Dimension(27, 27));
				jButton15.addActionListener(this);
				gridBagConstraints = new java.awt.GridBagConstraints();
				gridBagConstraints.gridx = 0;
				gridBagConstraints.gridy = 5;
				gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
				jPanel1.add(jButton15, gridBagConstraints);

				jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/polygonFilled.jpg")));
				jButton16.setMinimumSize(new java.awt.Dimension(27, 27));
				jButton16.setPreferredSize(new java.awt.Dimension(27, 27));
				jButton16.addActionListener(this);
				gridBagConstraints = new java.awt.GridBagConstraints();
				gridBagConstraints.gridx = 1;
				gridBagConstraints.gridy = 5;
				gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
				jPanel1.add(jButton16, gridBagConstraints);

				jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pickColor.jpg")));
				jButton11.setMinimumSize(new java.awt.Dimension(27, 27));
				jButton11.setPreferredSize(new java.awt.Dimension(27, 27));
				jButton11.addActionListener(this);
				gridBagConstraints = new java.awt.GridBagConstraints();
				gridBagConstraints.gridx = 0;
				gridBagConstraints.gridy = 6;
				gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
				jPanel1.add(jButton11, gridBagConstraints);

				jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/font.jpg")));
				jButton12.setMinimumSize(new java.awt.Dimension(27, 27));
				jButton12.setPreferredSize(new java.awt.Dimension(27, 27));
				jButton12.addActionListener(this);
				gridBagConstraints = new java.awt.GridBagConstraints();
				gridBagConstraints.gridx = 1;
				gridBagConstraints.gridy = 6;
				gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
				jPanel1.add(jButton12, gridBagConstraints);

				jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eraser.jpg")));
				jButton13.setMinimumSize(new java.awt.Dimension(27, 27));
				jButton13.setPreferredSize(new java.awt.Dimension(27, 27));
				jButton13.addActionListener(this);
				gridBagConstraints = new java.awt.GridBagConstraints();
				gridBagConstraints.gridx = 0;
				gridBagConstraints.gridy = 7;
				gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
				jPanel1.add(jButton13, gridBagConstraints);

				jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/bucket.jpg")));
				jButton14.setMinimumSize(new java.awt.Dimension(27, 27));
				jButton14.setPreferredSize(new java.awt.Dimension(27, 27));
				jButton14.addActionListener(this);
				gridBagConstraints = new java.awt.GridBagConstraints();
				gridBagConstraints.gridx = 1;
				gridBagConstraints.gridy = 7;
				gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
				jPanel1.add(jButton14, gridBagConstraints);


				jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/lsystem.jpg")));
				jButton17.setMinimumSize(new java.awt.Dimension(27, 27));
				jButton17.setPreferredSize(new java.awt.Dimension(42, 46));
				jButton17.addActionListener(this);
				gridBagConstraints = new java.awt.GridBagConstraints();
				gridBagConstraints.gridx = 0;
				gridBagConstraints.gridy = 8;
				gridBagConstraints.gridwidth = 2;
				gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
				gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
				jPanel1.add(jButton17, gridBagConstraints);

				gridBagConstraints = new java.awt.GridBagConstraints();
				gridBagConstraints.gridx = 0;
				gridBagConstraints.gridy = 9;
				gridBagConstraints.gridwidth = 2;
				gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
				gridBagConstraints.weighty = 0.1;
				jPanel1.add(jLabel1, gridBagConstraints);

				add(jPanel1);

			}

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == jButton1) {
					AbstractTool tool = ShapeModifier.getTool(MainWindow.this);
					selectTool(tool);
				} else if (e.getSource() == jButton2) {
					AbstractTool tool = Pencil.getTool(MainWindow.this);
					selectTool(tool);
				} else if (e.getSource() == jButton3) {
					AbstractTool tool = Brush.getTool(MainWindow.this);
					selectTool(tool);
				} else if (e.getSource() == jButton4) {
					AbstractTool tool = LineDrawer.getTool(MainWindow.this);
					selectTool(tool);
				} else if (e.getSource() == jButton5) {
					AbstractTool tool = RectangleDrawer.getTool(MainWindow.this);
					selectTool(tool);
				} else if (e.getSource() == jButton6) {
					AbstractTool tool = FilledRectangleDrawer.getTool(MainWindow.this);
					selectTool(tool);
				} else if (e.getSource() == jButton7) {
					AbstractTool tool = EllipseDrawer.getTool(MainWindow.this);
					selectTool(tool);
				} else if (e.getSource() == jButton8) {
					AbstractTool tool = FilledEllipseDrawer.getTool(MainWindow.this);
					selectTool(tool);
				} else if (e.getSource() == jButton9) {
					AbstractTool tool = RoundRectangleDrawer.getTool(MainWindow.this);
					selectTool(tool);
				} else if (e.getSource() == jButton10) {
					AbstractTool tool = FilledRoundRectangleDrawer.getTool(MainWindow.this);
					selectTool(tool);
				} else if (e.getSource() == jButton11) {
					AbstractTool tool = ColorPicker.getTool(MainWindow.this);
					selectTool(tool);
				} else if (e.getSource() == jButton12) {
					AbstractTool tool = TextInsert.getTool(MainWindow.this);
					selectTool(tool);
				} else if (e.getSource() == jButton13) {
					AbstractTool tool = Eraser.getTool(MainWindow.this);
					selectTool(tool);
				} else if (e.getSource() == jButton14) {
					AbstractTool tool = Bucket.getTool(MainWindow.this);
					selectTool(tool);
				} else if (e.getSource() == jButton15) {
					AbstractTool tool = PolygonDrawer.getTool(MainWindow.this);
					selectTool(tool);
				} else if (e.getSource() == jButton16) {
					AbstractTool tool = FilledPolygonDrawer.getTool(MainWindow.this);
					selectTool(tool);
				} else if (e.getSource() == jButton17) {
					AbstractTool tool = LSystemDrawer.getTool(MainWindow.this);
					selectTool(tool);
				}

			}

			private javax.swing.JButton jButton1;
			private javax.swing.JButton jButton10;
			private javax.swing.JButton jButton11;
			private javax.swing.JButton jButton12;
			private javax.swing.JButton jButton13;
			private javax.swing.JButton jButton14;
			private javax.swing.JButton jButton15;
			private javax.swing.JButton jButton16;
			private javax.swing.JButton jButton17;
			private javax.swing.JButton jButton2;
			private javax.swing.JButton jButton3;
			private javax.swing.JButton jButton4;
			private javax.swing.JButton jButton5;
			private javax.swing.JButton jButton6;
			private javax.swing.JButton jButton7;
			private javax.swing.JButton jButton8;
			private javax.swing.JButton jButton9;
			private javax.swing.JPanel jPanel1;
			private javax.swing.JLabel jLabel1;
	}

}
