package tools;

import paint.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.*;

public class ImageProcessor {

	private float data[][] =
	{ {0.1f, 0.1f, 0.1f, // 3x3 rozmycie
		  0.1f, 0.2f, 0.1f,
		  0.1f, 0.1f, 0.1f},
	{-1.0f, -1.0f, -1.0f,           // 3x3 wyostrzenie
		-1.0f, 9.0f, -1.0f,
		-1.0f, -1.0f, -1.0f},
	{ 0.f, -1.f,  0.f,               // 3x3 wykrywanie krawedzi
		-1.f,  5.f, -1.f,
		0.f, -1.f,  0.f},
	{-1.0f, -1.0f, -1.0f, -1.0f, -1.0f,  // 5x5 wykrywanie krawedzi
		-1.0f, -1.0f, -1.0f, -1.0f, -1.0f,
		-1.0f, -1.0f, 24.0f, -1.0f, -1.0f,
		-1.0f, -1.0f, -1.0f, -1.0f, -1.0f,
		-1.0f, -1.0f, -1.0f, -1.0f, -1.0f}};

	private BufferedImage origImage;

	public ImageProcessor(BufferedImage image) {
		origImage = image;
	}

	public BufferedImage apply(int operation) {
		BufferedImage result;

		BufferedImageOp biop;
		Kernel kern = null;

		if (operation == 0) {
			/* 3 x 3 rozmycie */
			kern = new Kernel(3, 3, data[0]);
		} else if (operation == 1) {
			/* 3 x 3 wyostrzenie */
			kern = new Kernel(3, 3, data[1]);
		} else if (operation == 2) {
			/* 3 x 3 krawedzie */
			kern = new Kernel(3, 3, data[2]);
		} else if (operation == 3) {
			/* 5 x 5 krawedzie */
			kern = new Kernel(5, 5, data[3]);
		} else  {
			/* NOT REACHABLE */
		}
		biop = new ConvolveOp(kern);

		result = new BufferedImage(
				origImage.getWidth(), origImage.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		biop.filter(origImage, result);

		return result;
	}

}

