package graphique;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanneauTour extends JPanel{

	
	private static final long serialVersionUID = 1L;
	private boolean tourJ1, init = false;
	private String nomJ1, nomJ2;
	private Font police;
	
	public PanneauTour(Dimension dim, String nomJ2){
		this.setPreferredSize(dim);
		tourJ1 = true;
		this.nomJ1 = "Ordi";
		this.nomJ2 = nomJ2;
		police = new Font("Helvetica", Font.BOLD, 14);
		
	}
	
	public PanneauTour(Dimension dim, String nomJ1, String nomJ2){
		this.setPreferredSize(dim);
		tourJ1 = true;
		this.nomJ1 = nomJ1;
		this.nomJ2 = nomJ2;
		police = new Font("Helvetica", Font.BOLD, 12);
	}
	
	public PanneauTour(Dimension dim){
		this.setPreferredSize(dim);
		tourJ1 = true;
		this.nomJ1 = "Ordi1";
		this.nomJ2 = "Ordi2";
		police = new Font("Helvetica", Font.BOLD, 14);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (init){
			g.setFont(police);
			if(tourJ1)
				g.drawString("Tour de " + nomJ1, 10, 20);
			else
				g.drawString("Tour de " + nomJ2, 10, 20);
		}
	}
	
	public void changeTour(){
		this.tourJ1 = !this.tourJ1;
	}
	
	public void init(){
		init = true;
		
	}
}
