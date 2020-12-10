package edu.ubo.graphicLayer.demos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import edu.ubo.graphicLayer.GImage;
import edu.ubo.graphicLayer.GRect;
import edu.ubo.graphicLayer.GSpace;
import edu.ubo.graphicLayer.GString;

// I_love_Brest_city


public class Example4 {
	
	public Example4() {

		File path = new File("I_love_Brest_city.jpg");
		BufferedImage rawImage = null;
		try {
			rawImage = ImageIO.read(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		GSpace w = new GSpace("Image", new Dimension(rawImage.getWidth() + 10, rawImage.getHeight() + 10));
		
		GRect container = new GRect();
		container.withoutBorder();
		container.setColor(Color.white);
		container.setDimension(new Dimension(rawImage.getWidth() + 10, rawImage.getHeight() + 10));
		w.addElement(container);
		
		GImage image = new GImage (rawImage);
		image.setPosition(new Point(5,5));
		container.addElement(image);
		
		GString s = new GString();
		s.setPosition(new Point(10,10));
		s.setString("I love Brest...");
		s.setFont(new Font("Arial", Font.BOLD, 30));
		s.setColor(Color.red);
		container.addElement(s);
		w.open();
	}
	
	public static void main(String[] args) {
		new Example4();
	}

}
