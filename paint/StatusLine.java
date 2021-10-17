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

public class StatusLine extends JPanel {
	public StatusLine() {
		initComponents();
	}

	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		jLabel1 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		jTextField2 = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jTextField3 = new javax.swing.JTextField();
		jLabel6 = new javax.swing.JLabel();
		jTextField4 = new javax.swing.JTextField();
		jTextField5 = new javax.swing.JTextField();

		setLayout(new java.awt.GridBagLayout());

		setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0)));
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		jLabel1.setText("Kolor1:");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		add(jLabel1, gridBagConstraints);

		jTextField1.setEditable(false);
		jTextField1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
		jTextField1.setText("#FFFFFF");
		jTextField1.setAutoscrolls(false);
		jTextField1.setColumns(5);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		add(jTextField1, gridBagConstraints);

		jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		jLabel2.setText("Kolor2:");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 10, 4, 4);
		add(jLabel2, gridBagConstraints);

		jTextField2.setEditable(false);
		jTextField2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
		jTextField2.setText("#FFFFFF");
		jTextField2.setColumns(5);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		add(jTextField2, gridBagConstraints);

		jLabel3.setText("(x,y):");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 10, 4, 4);
		add(jLabel3, gridBagConstraints);

		jTextField3.setEditable(false);
		jTextField3.setHorizontalAlignment(javax.swing.JTextField.LEFT);
		jTextField3.setText("(0,0)");
		jTextField3.setColumns(5);
		jTextField3.setHorizontalAlignment(JTextField.CENTER);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		add(jTextField3, gridBagConstraints);

		jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		jLabel6.setText("Narz\u0119dzie:");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 10, 4, 4);
		add(jLabel6, gridBagConstraints);

		jTextField4.setEditable(false);
		jTextField4.setHorizontalAlignment(javax.swing.JTextField.LEFT);
		jTextField4.setText("Narz\u0119dzie1");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		add(jTextField4, gridBagConstraints);

		jTextField5.setColumns(1);
		jTextField5.setEditable(false);
		jTextField5.setHorizontalAlignment(javax.swing.JTextField.LEFT);
		jTextField5.setText("Program uruchomiony");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(4, 10, 4, 4);
		gridBagConstraints.weightx = 0.1;
		add(jTextField5, gridBagConstraints);

	}

	public void setXY(int x, int y) {
		String location = 
			"(" + x + "," + y + ")";
		jTextField3.setText(location);
	}

	public void setMessage(String msg) {
		jTextField5.setText(msg);
		jTextField5.setToolTipText(msg);
	}

	public void setToolName(String msg) {
		jTextField4.setText(msg);
		jTextField4.setToolTipText(msg);
	}

	private String stringFromColor(Color c) {
		StringBuffer result = new StringBuffer();
		String redString, greenString, blueString;
		result.append("#");
		redString = Integer.toHexString(c.getRed());
		greenString = Integer.toHexString(c.getGreen());
		blueString = Integer.toHexString(c.getBlue());

		if (redString.length() == 1) {
			result.append("0");
		}
		result.append(redString);

		if (greenString.length() == 1) {
			result.append("0");
		}
		result.append(greenString);

		if (blueString.length() == 1) {
			result.append("0");
		}
		result.append(blueString);
		return result.toString();
	}

	public void updateColorLabels(Color color1, Color color2) {
		jTextField1.setText(stringFromColor(color1));
		jTextField2.setText(stringFromColor(color2));
	}

	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTextField jTextField4;
	private javax.swing.JTextField jTextField5;
}
