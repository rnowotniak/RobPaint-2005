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

/*
 * Przechowuje informacje o modyfikowalnym ksztalcie
 */
public class ModifiableShape {
	public Shape shape;
	public Paint paint;
	public Stroke stroke;
	public Composite composite;
	public AffineTransform transform;
	public boolean isFilled;

	public static Paint selectedShapePaint = Color.YELLOW;
	static Stroke selectedShapeStroke =
		new BasicStroke(3);

	public ModifiableShape(Shape shape) {
		/* ustawienia default */
		this(shape, Color.RED, new BasicStroke(),
				AlphaComposite.SrcOver, new AffineTransform());
		isFilled = false;
	}

	public ModifiableShape(Shape shape,
			Paint paint, Stroke stroke,
			Composite composite, AffineTransform transform) {
		this.shape = shape;
		this.paint = paint;
		this.stroke = stroke;
		this.composite = composite;
		this.transform = transform;
	}
}


