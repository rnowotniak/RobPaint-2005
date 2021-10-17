package paint;

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

public class ConfigurationPanel extends JPanel
	implements ChangeListener, ActionListener {

	MainWindow mainWindow;
	Point2D gradientStartPoint;
	Point2D gradientEndPoint;
	Rectangle2D textureAnchor;

	public ConfigurationPanel(MainWindow main) {
		mainWindow = main;
		gradientStartPoint = new Point(0, 0);
		gradientEndPoint = new Point(100, 100);
		textureAnchor = new Rectangle(0, 0, 100, 100);
		initComponents();
		setPreferredSize(
				new Dimension(212, 459));
	}

	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		paintGroup = new javax.swing.ButtonGroup();
		jFarbaPanel = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jKolorButton = new javax.swing.JRadioButton();
		jGradientButton = new javax.swing.JRadioButton();
		jPanel2 = new javax.swing.JPanel();
		jTeksturaButton = new javax.swing.JRadioButton();
		jGBegButton = new javax.swing.JButton();
		jGEndButton = new javax.swing.JButton();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jKolorButton1 = new javax.swing.JButton();
		jKolorButton2 = new javax.swing.JButton();
		jCyclicCheckBox = new javax.swing.JCheckBox();
		jLabel4 = new javax.swing.JLabel();
		jAnchorButton = new javax.swing.JButton();
		jLabel5 = new javax.swing.JLabel();
		jImageButton = new javax.swing.JButton();
		jTexturePreview = new TexturePreview();
		jPedzelPanel = new javax.swing.JPanel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jSzerokoscSpinner = new javax.swing.JSpinner();
		jLabel8 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		jZakonczeniaCombo = new javax.swing.JComboBox(
				new String [] {"Okragle", "Kwadratowe", "Sciete"});
		jPolaczeniaCombo = new javax.swing.JComboBox(
				new String [] {"Okragle", "Ostre", "Sciete"});
		jPrzenikaniePanel = new javax.swing.JPanel();
		jLabel10 = new javax.swing.JLabel();
		jLabel11 = new javax.swing.JLabel();
		jRegulaCombo = new javax.swing.JComboBox(
				new String []  {
					"brak",
					"SRC",
					"DST",
					"SRC_OVER",
					"DST_OVER",
				}
				);
		jLabel12 = new javax.swing.JLabel();
		jAlfaSlider = new javax.swing.JSlider();

		setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

		setFont(new java.awt.Font("Dialog", 0, 10));
		jFarbaPanel.setLayout(null);

		jFarbaPanel.setBorder(new javax.swing.border.EtchedBorder());
		jFarbaPanel.setFont(new java.awt.Font("Dialog", 0, 12));
		jFarbaPanel.setPreferredSize(new java.awt.Dimension(212, 294));
		jLabel1.setText("Farba:");
		jFarbaPanel.add(jLabel1);
		jLabel1.setBounds(4, 6, 170, 15);

		jKolorButton.setFont(new java.awt.Font("Dialog", 0, 10));
		jKolorButton.setSelected(true);
		jKolorButton.setText("Kolor");
		paintGroup.add(jKolorButton);
		jFarbaPanel.add(jKolorButton);
		jKolorButton.setBounds(18, 22, 80, 21);
		jKolorButton.addActionListener(this);

		jGradientButton.setFont(new java.awt.Font("Dialog", 0, 10));
		jGradientButton.setText("Gradient");
		paintGroup.add(jGradientButton);
		jFarbaPanel.add(jGradientButton);
		jGradientButton.setBounds(18, 42, 80, 21);
		jGradientButton.addActionListener(this);

		jTeksturaButton.setFont(new java.awt.Font("Dialog", 0, 10));
		jTeksturaButton.setText("Tekstura");
		paintGroup.add(jTeksturaButton);
		jFarbaPanel.add(jTeksturaButton);
		jTeksturaButton.setBounds(18, 132, 90, 21);
		jTeksturaButton.addActionListener(this);

		jGBegButton.setFont(new java.awt.Font("Dialog", 0, 10));
		jGBegButton.setText("(0,0)");
		jFarbaPanel.add(jGBegButton);
		jGBegButton.setBounds(102, 62, 79, 23);
		jGBegButton.addActionListener(this);

		jGEndButton.setFont(new java.awt.Font("Dialog", 0, 10));
		jGEndButton.setText("(50,50)");
		jFarbaPanel.add(jGEndButton);
		jGEndButton.setBounds(102, 86, 79, 23);
		jGEndButton.addActionListener(this);

		jLabel2.setFont(new java.awt.Font("Dialog", 0, 10));
		jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel2.setText("Pocz\u0105tek:");
		jFarbaPanel.add(jLabel2);
		jLabel2.setBounds(38, 68, 64, 13);

		jLabel3.setFont(new java.awt.Font("Dialog", 0, 10));
		jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel3.setText("Koniec:");
		jFarbaPanel.add(jLabel3);
		jLabel3.setBounds(52, 90, 48, 13);

		jKolorButton1.setBackground(new java.awt.Color(153, 244, 51));
		jKolorButton1.setFont(new java.awt.Font("Dialog", 0, 10));
		jFarbaPanel.add(jKolorButton1);
		jKolorButton1.setBounds(182, 62, 28, 24);

		jKolorButton2.setBackground(new java.awt.Color(51, 102, 255));
		jKolorButton2.setFont(new java.awt.Font("Dialog", 0, 10));
		jFarbaPanel.add(jKolorButton2);
		jKolorButton2.setBounds(182, 86, 28, 24);

		jCyclicCheckBox.setFont(new java.awt.Font("Dialog", 0, 10));
		jCyclicCheckBox.setText("Kolory cykliczne");
		jCyclicCheckBox.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		jFarbaPanel.add(jCyclicCheckBox);
		jCyclicCheckBox.setBounds(54, 110, 168, 21);

		jLabel4.setFont(new java.awt.Font("Dialog", 0, 10));
		jLabel4.setText("Zakotwiczenie:");
		jFarbaPanel.add(jLabel4);
		jLabel4.setBounds(40, 156, 74, 13);

		jAnchorButton.setFont(new java.awt.Font("Dialog", 0, 10));
		jAnchorButton.setText("10,10,50,50");
		jFarbaPanel.add(jAnchorButton);
		jAnchorButton.setBounds(60, 174, 136, 23);
		jAnchorButton.addActionListener(this);

		jLabel5.setFont(new java.awt.Font("Dialog", 0, 10));
		jLabel5.setText("Obraz:");
		jFarbaPanel.add(jLabel5);
		jLabel5.setBounds(40, 200, 54, 13);

		jImageButton.setFont(new java.awt.Font("Dialog", 0, 10));
		jImageButton.setText("Wybierz");
		jFarbaPanel.add(jImageButton);
		jImageButton.setBounds(60, 216, 136, 20);
		jImageButton.addActionListener(this);

		jTexturePreview.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
		jFarbaPanel.add(jTexturePreview);
		jTexturePreview.setBounds(98, 238, 62, 50);

		add(jFarbaPanel);

		jPedzelPanel.setLayout(null);

		jPedzelPanel.setBorder(new javax.swing.border.EtchedBorder());
		jPedzelPanel.setFont(new java.awt.Font("Dialog", 0, 10));
		jPedzelPanel.setPreferredSize(new java.awt.Dimension(212, 90));
		jLabel6.setText("P\u0119dzel:");
		jPedzelPanel.add(jLabel6);
		jLabel6.setBounds(4, 4, 62, 16);

		jLabel7.setFont(new java.awt.Font("Dialog", 0, 10));
		jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel7.setText("Szeroko\u015b\u0107:");
		jPedzelPanel.add(jLabel7);
		jLabel7.setBounds(28, 26, 52, 13);

		jSzerokoscSpinner.setFont(new java.awt.Font("Dialog", 0, 10));
		jPedzelPanel.add(jSzerokoscSpinner);
		jSzerokoscSpinner.setBounds(84, 22, 40, 20);
		jSzerokoscSpinner.addChangeListener(this);
		jSzerokoscSpinner.setModel(new SpinnerNumberModel(
					1, 1, 40, 5));

		jLabel8.setFont(new java.awt.Font("Dialog", 0, 10));
		jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel8.setText("Zako\u0144czenia:");
		jPedzelPanel.add(jLabel8);
		jLabel8.setBounds(6, 50, 74, 13);

		jLabel9.setFont(new java.awt.Font("Dialog", 0, 10));
		jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel9.setText("Po\u0142\u0105czenia:");
		jPedzelPanel.add(jLabel9);
		jLabel9.setBounds(12, 72, 68, 13);

		jZakonczeniaCombo.setFont(new java.awt.Font("Dialog", 0, 10));
		jPedzelPanel.add(jZakonczeniaCombo);
		jZakonczeniaCombo.setBounds(84, 46, 96, 20);
		jZakonczeniaCombo.addActionListener(this);

		jPolaczeniaCombo.setFont(new java.awt.Font("Dialog", 0, 10));
		jPedzelPanel.add(jPolaczeniaCombo);
		jPolaczeniaCombo.setBounds(84, 68, 96, 20);
		jPolaczeniaCombo.addActionListener(this);

		add(jPedzelPanel);

		jPrzenikaniePanel.setLayout(null);
		jPrzenikaniePanel.setBorder(new javax.swing.border.EtchedBorder());

		jPrzenikaniePanel.setPreferredSize(new java.awt.Dimension(212, 75));
		jLabel10.setText("Przenikanie:");
		jPrzenikaniePanel.add(jLabel10);
		jLabel10.setBounds(6, 2, 75, 15);

		jLabel11.setFont(new java.awt.Font("Dialog", 0, 10));
		jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel11.setText("Regu\u0142a:");
		jPrzenikaniePanel.add(jLabel11);
		jLabel11.setBounds(28, 26, 45, 15);

		jRegulaCombo.setFont(new java.awt.Font("Dialog", 0, 10));
		jPrzenikaniePanel.add(jRegulaCombo);
		jRegulaCombo.setBounds(82, 26, 126, 20);
		jRegulaCombo.addActionListener(this);

		jLabel12.setFont(new java.awt.Font("Dialog", 0, 10));
		jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		jLabel12.setText("Alfa:");
		jPrzenikaniePanel.add(jLabel12);
		jLabel12.setBounds(20, 56, 53, 13);

		jAlfaSlider.setFont(new java.awt.Font("Dialog", 0, 10));
		jAlfaSlider.setValue(70);
		jPrzenikaniePanel.add(jAlfaSlider);
		jAlfaSlider.setBounds(78, 56, 134, 16);
		jAlfaSlider.addChangeListener(this);

		add(jPrzenikaniePanel);

	}

	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == jSzerokoscSpinner) {
			JSpinner jSpiner1 = (JSpinner) e.getSource();
			float newWidth = ((Integer) jSpiner1.getValue()).intValue();
			BasicStroke prev = (BasicStroke) mainWindow.currentStroke;
			mainWindow.currentStroke =
				new BasicStroke(
						newWidth,
						prev.getEndCap(),
						prev.getLineJoin(),
						prev.getMiterLimit()
						);
			mainWindow.statusLine.setMessage(
					"Nowa szerokosc: " + newWidth);
		}
		else if (e.getSource() == jAlfaSlider) {
			actionPerformed(
					new ActionEvent(jRegulaCombo,
						ActionEvent.ACTION_PERFORMED, ""));
			if (true)
				return;
			float newValue =
				(float) (1.0 * jAlfaSlider.getValue() / 100);

			JSpinner jSpiner1 = (JSpinner) e.getSource();
			float newWidth = ((Integer) jSpiner1.getValue()).intValue();
			BasicStroke prev = (BasicStroke) mainWindow.currentStroke;
			mainWindow.currentStroke =
				new BasicStroke(
						newWidth,
						prev.getEndCap(),
						prev.getLineJoin(),
						prev.getMiterLimit()
						);
			mainWindow.statusLine.setMessage(
					"Nowa szerokosc: " + newWidth);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jKolorButton) {
			mainWindow.currentPaint = mainWindow.color1;
		} else if (e.getSource() == jTeksturaButton) {
			BufferedImage i = jTexturePreview.getImage();
			if (i != null) {
				TexturePaint tPaint = new TexturePaint(i, textureAnchor);
				mainWindow.currentPaint = tPaint;
				mainWindow.statusLine.setMessage(
						"Ustawiono rysowanie tekstura");
			}
		} else if (e.getSource() == jAnchorButton) {
			String result;
			result = (String) JOptionPane.showInputDialog(this,
					"Podaj zakotwiczenie tekstury: x,y,w,h",
					"Zakotwiczenie",
					JOptionPane.QUESTION_MESSAGE,
					null, null,
					jAnchorButton.getText());
			if (result != null && result.matches("^\\d+,\\d+,\\d+,\\d+")) {
				String parts[] = result.split(",");
				int x = Integer.parseInt(parts[0]);
				int y = Integer.parseInt(parts[1]);
				int w = Integer.parseInt(parts[2]);
				int h = Integer.parseInt(parts[3]);
				textureAnchor = new Rectangle(x, y, w, h);
				jAnchorButton.setText(
						"" + x + "," + y + "," + w + "," + h);
				actionPerformed(new ActionEvent(jTeksturaButton,
							ActionEvent.ACTION_PERFORMED, ""));
			}
		} else if (e.getSource() == jImageButton) {
			JFileChooser chooser = new JFileChooser(".");
			chooser.setFileFilter(new ImageFilter());
			int retVal = chooser.showOpenDialog(this);
			if (retVal == JFileChooser.APPROVE_OPTION) {
				jTexturePreview.setImage(
						chooser.getSelectedFile());
				actionPerformed(new ActionEvent(jTeksturaButton,
							ActionEvent.ACTION_PERFORMED, ""));
			}
		} else if (e.getSource() == jGBegButton) {
			String result;
			result = (String) JOptionPane.showInputDialog(this,
					"Podaj punkt poczatku gradientu:",
					"Wybierz punkt",
					JOptionPane.QUESTION_MESSAGE,
					null, null,
					"" + (int) gradientStartPoint.getX() +
					"," + (int) gradientStartPoint.getY()
					);
			if (result != null) {
				if (result.matches("\\d+,\\d+")) {
					String s = result.replaceFirst(",.*$", "");
					int x = Integer.parseInt(s);
					s = result.replaceFirst(".*,", "");
					int y = Integer.parseInt(s);
					gradientStartPoint = new Point(x, y);
					jGBegButton.setText("(" + x + "," + y + ")");
					actionPerformed(
							new ActionEvent(jGradientButton,
								ActionEvent.ACTION_PERFORMED, ""));
				}
			}
		} else if (e.getSource() == jGEndButton) {
			String result;
			result = (String) JOptionPane.showInputDialog(this,
					"Podaj punkt konca gradientu:",
					"Wybierz punkt",
					JOptionPane.QUESTION_MESSAGE,
					null, null,
					"" + (int) gradientEndPoint.getX() +
					"," + (int) gradientEndPoint.getY()
					);
			if (result != null) {
				if (result.matches("\\d+,\\d+")) {
					String s = result.replaceFirst(",.*$", "");
					int x = Integer.parseInt(s);
					s = result.replaceFirst(".*,", "");
					int y = Integer.parseInt(s);
					gradientEndPoint = new Point(x, y);
					jGEndButton.setText("(" + x + "," + y + ")");
					actionPerformed(
							new ActionEvent(jGradientButton,
								ActionEvent.ACTION_PERFORMED, ""));
				}
			}
		} else if (e.getSource() == jCyclicCheckBox) {
			actionPerformed(new ActionEvent(jGradientButton,
						ActionEvent.ACTION_PERFORMED, ""));
		} else if (e.getSource() == jGradientButton) {
			GradientPaint gPaint = new GradientPaint(
					gradientStartPoint,
					mainWindow.color1,
					gradientEndPoint,
					mainWindow.color2,
					jCyclicCheckBox.isSelected()
					);
			mainWindow.currentPaint = gPaint;
			mainWindow.statusLine.setMessage(
					"Wybrano malowanie gradientem");
		} else if (e.getSource() == jZakonczeniaCombo) {
			String cap = (String) jZakonczeniaCombo.getSelectedItem();
			int newCap = 0;
			if (cap == "Okragle") {
				newCap = BasicStroke.CAP_ROUND;
			} else if (cap == "Kwadratowe") {
				newCap = BasicStroke.CAP_SQUARE;
			} else if (cap == "Sciete") {
				newCap = BasicStroke.CAP_BUTT;
			}
			BasicStroke prev = (BasicStroke) mainWindow.currentStroke;
			mainWindow.currentStroke =
				new BasicStroke(
						prev.getLineWidth(),
						newCap,
						prev.getLineJoin(),
						prev.getMiterLimit()
						);
			mainWindow.statusLine.setMessage(
					"Nowa zakonczenia: " + newCap);
		} else if (e.getSource() == jRegulaCombo) {
			String rule = (String) jRegulaCombo.getSelectedItem();
			int newRule = 0;
			if (rule == "brak") {
				mainWindow.currentComposite = AlphaComposite.SrcOver;
				return;
			} else if (rule == "SRC") {
				newRule = AlphaComposite.SRC;
			} else if (rule == "DST") {
				newRule = AlphaComposite.DST;
			} else if (rule == "SRC_OVER") {
				newRule = AlphaComposite.SRC_OVER;
			} else if (rule == "DST_OVER") {
				newRule = AlphaComposite.DST_OVER;
			}
			mainWindow.currentComposite =
				AlphaComposite.getInstance(
						newRule,
						(float) (1.0 * jAlfaSlider.getValue() / 100.0)
						);
			mainWindow.statusLine.setMessage(
					"Ustawiono nowe przenikanie");
		} else if (e.getSource() == jPolaczeniaCombo) {
			String join = (String) jPolaczeniaCombo.getSelectedItem();
			int newJoin = 0;
			if (join == "Okragle") {
				newJoin = BasicStroke.JOIN_ROUND;
			} else if (join == "Ostre") {
				newJoin = BasicStroke.JOIN_MITER;
			} else if (join == "Sciete") {
				newJoin = BasicStroke.JOIN_BEVEL;
			}
			BasicStroke prev = (BasicStroke) mainWindow.currentStroke;
			mainWindow.currentStroke =
				new BasicStroke(
						prev.getLineWidth(),
						prev.getEndCap(),
						newJoin,
						10.0f
						);
			mainWindow.statusLine.setMessage(
					"Nowa zakonczenia: " + newJoin);
		}
	}

	private javax.swing.JSlider jAlfaSlider;
	private javax.swing.JButton jGBegButton;
	private javax.swing.JButton jGEndButton;
	javax.swing.JButton jKolorButton1;
	javax.swing.JButton jKolorButton2;
	private javax.swing.JButton jAnchorButton;
	private javax.swing.JButton jImageButton;
	private javax.swing.JCheckBox jCyclicCheckBox;
	private javax.swing.JPanel jFarbaPanel;
	private javax.swing.JRadioButton jGradientButton;
	javax.swing.JRadioButton jKolorButton;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private TexturePreview jTexturePreview;
		class TexturePreview extends JPanel {
			public BufferedImage image = null;
			public void paintComponent(Graphics g) {
				if (image != null) {
					g.drawImage(image, 0, 0,
							getWidth(), getHeight(), this);
				}
			}
			void setImage(File f) {
				Image i = new ImageIcon(f.getAbsolutePath()).getImage();
				image = new BufferedImage(
						i.getWidth(null), i.getHeight(null), BufferedImage.TYPE_INT_RGB);
				image.getGraphics().drawImage(i, 0, 0, this);
			}
			BufferedImage getImage() {
				return image;
			}
		}
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPedzelPanel;
	private javax.swing.JComboBox jPolaczeniaCombo;
	private javax.swing.JPanel jPrzenikaniePanel;
	private javax.swing.JComboBox jRegulaCombo;
	private javax.swing.JSpinner jSzerokoscSpinner;
	private javax.swing.JRadioButton jTeksturaButton;
	private javax.swing.JComboBox jZakonczeniaCombo;
	private javax.swing.ButtonGroup paintGroup;

}
