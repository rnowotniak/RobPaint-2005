package paint;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.event.*;

public class aboutWindow extends javax.swing.JDialog {

	public aboutWindow(Frame owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		jLabel4 = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		jButton1 = new javax.swing.JButton();

		getContentPane().setLayout(new java.awt.GridBagLayout());

		setTitle("Informacja o programie");
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exitForm(evt);
			}
		});

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(16, 14, 5, 14);
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.4;
		getContentPane().add(jLabel4, gridBagConstraints);

		jLabel1.setFont(new java.awt.Font("Monospaced", 1, 18));
		jLabel1.setForeground(new java.awt.Color(255, 102, 102));
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("Robert's Paint 0.1\n");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 2;
		gridBagConstraints.ipady = 20;
		gridBagConstraints.insets = new java.awt.Insets(5, 21, 5, 24);
		gridBagConstraints.weighty = 0.2;
		getContentPane().add(jLabel1, gridBagConstraints);

		jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel2.setText("by Robert Nowotniak");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 2;
		gridBagConstraints.ipady = 20;
		gridBagConstraints.insets = new java.awt.Insets(5, 21, 5, 24);
		gridBagConstraints.weighty = 0.2;
		getContentPane().add(jLabel2, gridBagConstraints);

		jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel3.setText("\n6 maja 2005, 14:19:31 CEST");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 2;
		gridBagConstraints.ipady = 20;
		gridBagConstraints.insets = new java.awt.Insets(5, 21, 5, 24);
		gridBagConstraints.weighty = 0.2;
		getContentPane().add(jLabel3, gridBagConstraints);

		jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

		jButton1.setText("Zamknij");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jPanel1.add(jButton1);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.insets = new java.awt.Insets(5, 12, 14, 15);
		gridBagConstraints.weightx = 0.1;
		gridBagConstraints.weighty = 0.4;
		getContentPane().add(jPanel1, gridBagConstraints);

		pack();
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		dispose();
	}

	private void exitForm(java.awt.event.WindowEvent evt) {
		System.exit(0);
	}


	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JPanel jPanel1;

}
