package paint;

import javax.swing.*;
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

public class LSystemDialog extends javax.swing.JDialog
	implements ActionListener {

	MainWindow mainWindow;
	LSystem lsys;
    
    public LSystemDialog(MainWindow main, boolean modal) {
        super(main, modal);
		  mainWindow = main;
        initComponents();
    }
    
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jUpperPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jBottomPanel = new javax.swing.JPanel();
        jDrawButton = new javax.swing.JButton();
        jCancelButton = new javax.swing.JButton();
        jMiddlePanel = new javax.swing.JPanel();
        jLeftPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jAxiomCombo = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jDirectionText = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jStartText = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLevelCombo = new javax.swing.JComboBox();
        jLenSpinner = new javax.swing.JSpinner();
        jAngleSpinner = new javax.swing.JSpinner();
        jRightPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jExamplesList = new javax.swing.JList();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jProdsText = new javax.swing.JTextArea();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18));
        jLabel1.setText("Rysowanie L-Sysem\u00f3w");
        jUpperPanel.add(jLabel1);

        getContentPane().add(jUpperPanel, java.awt.BorderLayout.NORTH);

        jDrawButton.setText("Narysuj L-System");
        jBottomPanel.add(jDrawButton);
		  jDrawButton.addActionListener(this);

        jCancelButton.setText("Anuluj");
        jBottomPanel.add(jCancelButton);
		  jCancelButton.addActionListener(this);

        getContentPane().add(jBottomPanel, java.awt.BorderLayout.SOUTH);

        jMiddlePanel.setLayout(new java.awt.GridLayout(1, 2));

        jLeftPanel.setLayout(new java.awt.GridBagLayout());

        jLeftPanel.setBorder(new javax.swing.border.EtchedBorder());
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Aksjomat:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 12, 11);
        jLeftPanel.add(jLabel3, gridBagConstraints);

        jAxiomCombo.setMaximumRowCount(5);
        jAxiomCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAxiomComboActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jLeftPanel.add(jAxiomCombo, gridBagConstraints);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("D\u0142ugo\u015b\u0107:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 12, 11);
        jLeftPanel.add(jLabel5, gridBagConstraints);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("K\u0105t:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 12, 11);
        jLeftPanel.add(jLabel6, gridBagConstraints);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Kierunek:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 12, 11);
        jLeftPanel.add(jLabel7, gridBagConstraints);

        jDirectionText.setEditable(false);
        jDirectionText.setText("15 stopni");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jLeftPanel.add(jDirectionText, gridBagConstraints);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Baza:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 12, 11);
        jLeftPanel.add(jLabel8, gridBagConstraints);

        jStartText.setEditable(false);
        jStartText.setText("(300,12)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jLeftPanel.add(jStartText, gridBagConstraints);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Rekurencja:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 12, 11);
        jLeftPanel.add(jLabel9, gridBagConstraints);

        jLevelCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLevelComboActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jLeftPanel.add(jLevelCombo, gridBagConstraints);
		  for (int i = 1; i <= 7; i++) {
			  jLevelCombo.addItem(new Integer(i));
		  }

        jLenSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jLenSpinnerStateChanged(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jLeftPanel.add(jLenSpinner, gridBagConstraints);
		  jLenSpinner.setModel(new SpinnerNumberModel(
					  1, 1, 1000, 20));

        jAngleSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jAngleSpinnerStateChanged(evt);
            }
        });
		  jAngleSpinner.setModel(new SpinnerNumberModel(
					  0, -180, 180, 2));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jLeftPanel.add(jAngleSpinner, gridBagConstraints);

        jMiddlePanel.add(jLeftPanel);

        jRightPanel.setLayout(new java.awt.GridBagLayout());

        jRightPanel.setBorder(new javax.swing.border.EtchedBorder());
        jLabel2.setText("Przyk\u0142ady L-System\u00f3w:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jRightPanel.add(jLabel2, gridBagConstraints);

        jExamplesList.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        jExamplesList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "a", "b", "c" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jExamplesList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jExamplesList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jExamplesListValueChanged(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jRightPanel.add(jExamplesList, gridBagConstraints);

        jLabel4.setText("Regu\u0142y produkcji L-Systemu:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jRightPanel.add(jLabel4, gridBagConstraints);

        jProdsText.setFont(new java.awt.Font("Monospaced", 0, 14));
        jProdsText.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(jProdsText);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.2;
        jRightPanel.add(jScrollPane1, gridBagConstraints);

        jMiddlePanel.add(jRightPanel);

        getContentPane().add(jMiddlePanel, java.awt.BorderLayout.CENTER);

        pack();
    }

    private void jExamplesListValueChanged(javax.swing.event.ListSelectionEvent evt) {
		 int number = ((JList) evt.getSource()).getSelectedIndex() + 1;
		 loadDemo(number);
    }

    private void jLevelComboActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jAngleSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {
    }

    private void jLenSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {
    }

    private void jAxiomComboActionPerformed(java.awt.event.ActionEvent evt) {
    }
    
    private void closeDialog(java.awt.event.WindowEvent evt) {
        setVisible(false);
    }
    
    public static void main(String args[]) {
    }
    
    public void loadDemo(int number) {
		 lsys = LSystem.getDemoLSystem(number);
		 setValues(lsys);
    }    

	 private String prods2Text(HashMap prods) {
		 StringBuffer result = new StringBuffer();
		 Object keys[] = prods.keySet().toArray();
		 jAxiomCombo.removeAllItems();
		 for (int i = 0; i < keys.length; i++) {
			 Character key = (Character) keys[i];
			 jAxiomCombo.addItem(key);
			 result.append(key);
			 result.append(" : ");
			 result.append((String) prods.get(key));
			 result.append("\n");
		 }
		 return result.toString();
	 }
    
    public void setValues(LSystem lsys) {
		 //jAxiomCombo.addItem(lsys.axiom);
		 //jAxiomCombo.setSelectedItem(lsys.axiom);
		 jLenSpinner.setValue(new Integer((int) lsys.totalLength));
		 jAngleSpinner.setValue(new Integer((int) lsys.angle));
		 jLevelCombo.setSelectedItem(new Integer(lsys.level));
		 jProdsText.setText(prods2Text(lsys.prods));
		 jAxiomCombo.setSelectedItem(lsys.axiom);
		 //jStartText.setText(
		//		"" + lsys.start.x + "," + lsys.start.y);
    }


	 public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == jCancelButton) {
			 setVisible(false);
		 } else if (e.getSource() == jDrawButton
				 && lsys != null) {
			 MainWindow.ImageFrame frame =
				 (MainWindow.ImageFrame)
				 mainWindow.jDesktopPane1.getSelectedFrame();
			 Image i = frame.getImage();
			 Graphics2D g2d = (Graphics2D) i.getGraphics();

			 /* Uaktualnienie ustawien */
			 lsys.level = ((Integer) jLevelCombo.getSelectedItem()).intValue();
			 lsys.axiom = (Character) jAxiomCombo.getSelectedItem();
			 lsys.totalLength = ((Integer) jLenSpinner.getValue()).intValue();
			 lsys.angle = ((Integer) jAngleSpinner.getValue()).intValue();
			 lsys.baseAngle =
				 Integer.parseInt(
						 jDirectionText.getText().replaceAll(" .*$", ""));
			 int x = Integer.parseInt(jStartText.getText().replaceAll(",.*$", ""));
			 int y = Integer.parseInt(jStartText.getText().replaceAll("^.*,", ""));
			 lsys.start = new Point(x, y);


			 g2d.setPaint(mainWindow.currentPaint);
			 g2d.setStroke(mainWindow.currentStroke);

			 lsys.drawIt(g2d);
			 frame.repaint();
			 setVisible(false);
		 }
	 }
    
    public javax.swing.JSpinner jAngleSpinner;
    private javax.swing.JComboBox jAxiomCombo;
    private javax.swing.JPanel jBottomPanel;
    private javax.swing.JButton jCancelButton;
    public javax.swing.JTextField jDirectionText;
    private javax.swing.JButton jDrawButton;
    public javax.swing.JList jExamplesList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jLeftPanel;
    public javax.swing.JSpinner jLenSpinner;
    private javax.swing.JComboBox jLevelCombo;
    private javax.swing.JPanel jMiddlePanel;
    private javax.swing.JTextArea jProdsText;
    private javax.swing.JPanel jRightPanel;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextField jStartText;
    private javax.swing.JPanel jUpperPanel;
    
}
