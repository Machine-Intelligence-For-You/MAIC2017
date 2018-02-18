package graphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanneauAccueil extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public PanneauAccueil(Dimension dim){
		this.setBackground(Color.WHITE);
		this.setPreferredSize(dim);
		
	}
	
	public void paintComponent(Graphics g){
		try {
			Image accueil = ImageIO.read(new File("images/accueil.png"));
			g.drawImage(accueil, this.getWidth()/2 - accueil.getWidth(this)/2, 
					this.getHeight()/2 - accueil.getHeight(this)/2, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
