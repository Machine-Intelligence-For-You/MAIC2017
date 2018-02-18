package graphique;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanneauScore extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int scoreJ1, scoreJ2;
	String nomJ1, nomJ2;
	private Font police;
	
	public PanneauScore(Dimension dim, String nomJ2){
		this.setPreferredSize(dim);
		this.scoreJ1 = 0;
		this.scoreJ2 = 0;
		this.nomJ1 = "Ordi";
		this.nomJ2 = nomJ2;
		police = new Font("Helvetica", Font.BOLD, 14);
	}
	
	public PanneauScore(Dimension dim, String nomJ1, String nomJ2){
		this.setPreferredSize(dim);
		this.scoreJ1 = 0;
		this.scoreJ2 = 0;
		this.nomJ1 = nomJ1;
		this.nomJ2 = nomJ2;
		police = new Font("Helvetica", Font.BOLD, 14);
	}
	
	public PanneauScore(Dimension dim){
		this.setPreferredSize(dim);
		this.scoreJ1 = 0;
		this.scoreJ2 = 0;
		this.nomJ1 = "Ordi1";
		this.nomJ2 = "Ordi2";
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setFont(police);
		g.drawString("SCORES: " + nomJ1 + ": " + scoreJ1 +", " + nomJ2 + ": " + scoreJ2, 10, 25);
		
	}
	
	public void changeScoreJ1(int score){
		this.scoreJ1 = score;
	}
	
	public void changeScoreJ2(int score){
		this.scoreJ2 = score;
	}
	
}
