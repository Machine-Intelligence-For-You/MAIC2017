package regles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanneauRegles extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image regles;
	
	PanneauRegles(){
		this.setBackground(Color.white);
		try {
			regles = ImageIO.read(new File("images/règles/règles.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(regles, 0, 0, this);
		
	}
}
