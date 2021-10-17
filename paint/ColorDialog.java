package paint;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.event.*;

public class ColorDialog extends JDialog
	implements ActionListener {

	JButton jbut1;
	JButton jbut2;
	JPanel jpan1;
	JColorChooser tcc;

	public ColorDialog(Frame owner, Color c) {
		super(owner, "Wybierz kolor", true);
		getContentPane().setLayout(new BorderLayout());
		tcc = new JColorChooser(c);
		getContentPane().add(tcc, BorderLayout.CENTER);

		jpan1 = new JPanel();
		getContentPane().add(jpan1, BorderLayout.SOUTH);

		jbut1 = new JButton("OK");
		jbut2 = new JButton("Anuluj");
		jbut1.addActionListener(this);
		jbut2.addActionListener(this);

		jpan1.add(jbut1);
		jpan1.add(jbut2);

		setSize(450, 270);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbut1) {
			setVisible(false);
		} else if (e.getSource() == jbut1) {
			setVisible(false);
		}
	}

	public Color getColor() {
		return tcc.getColor();
	}
}

