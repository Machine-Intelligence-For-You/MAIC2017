package graphique;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import jeu.Joueur;
import jeu.Partie;


public class PanneauJeu extends JPanel {

	private static final long serialVersionUID = 1L;
	private Partie partie;
	private String typePartie, difficulte, difficulte2, nomJoueur1, nomJoueur2;
	private String[] boutons = {"1 Joueur (Hum vs Ordi)", "2 Joueurs (Hum vs Hum)", "Spectateur (Ordi vs Ordi)"};
	private String[] boutons2 = {"Débutant", "Faible", "Moyen", "Difficile"};
	private ExecutorService es;
	private PanneauScore panneauScore;
	private PanneauTour panneauTour;
	private Dimension dimJeu, dimPanneauScore, dimPanneauTour;

	private Joueur IA_1;
	private Joueur IA_2;

	public PanneauJeu(Dimension dim, Parametres parametres){
		
		dimPanneauScore = new Dimension(dim.width, 30);
		dimJeu = new Dimension(dim.width, 470);
		dimPanneauTour = new Dimension(dim.width, 30);
		this.setLayout(new BorderLayout());
//		IA_1 = new Ordinateur("IA_1");
//		IA_2 = new Ordinateur2("IA_2");
        panneauScore = new PanneauScore(dimPanneauScore, "IA_1", "IA_2");
        panneauTour = new PanneauTour(dimPanneauTour, "IA_1", "IA_2");

        partie = new Partie(IA_1, IA_2, dimJeu,  panneauScore, panneauTour, parametres);
	    es = Executors.newFixedThreadPool(1);
	    this.add(panneauTour, BorderLayout.NORTH);
	    this.add(partie, BorderLayout.CENTER);
	    this.add(panneauScore, BorderLayout.SOUTH);
	    es.execute(partie);


	}



}
