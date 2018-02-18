package jeu;

import Finale.*;
import graphique.EcouteurPartie;
import graphique.ImagesJeu;
import graphique.PanneauScore;
import graphique.PanneauTour;
import graphique.Parametres;




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Partie extends JPanel implements Runnable{
	
	
	private static final long serialVersionUID = 1L;
	private static final int longueurAttente = 200;
	private Grille grille = new Grille(); //grille de jeu
//	private Ordinateur ordi, ordi2;
	private Joueur ordi;
	private Joueur ordi2;
	private Humain hum1, hum2;
	private boolean tourJ1, partieTerminee = false; //variable qui désigne qui joue, variable qui désigne si la partie est terminée ou non
	private int type, choix; // type de partie, choix du joueur
	private int positionXCases[], positionYCases[]; //les positions en abscisse et ordonnée des images représentants les cases de jeu
	private boolean continuer;
	private EcouteurPartie ecouteur;
	private Dimension dim;
	private PanneauScore panScore;
	private PanneauTour panTour;
	private ImagesJeu imagesJeu;
	private Parametres parametres;
	private long duration = 1000;
	public static int position_main;

	//Constructeur 3, pour une partie ordinateur contre ordinateur
	public Partie(Joueur ordi, Joueur ordi2, Dimension dim,
			PanneauScore panScore, PanneauTour panTour, Parametres parametres){

		this.dim = dim;
		this.setPreferredSize(this.dim);
		this.setLayout(new BorderLayout());
		this.type = 3;
		this.ordi = new Slh("IA_1");
		this.ordi2 = new Obf ("IA_2");
		this.panScore = panScore;
		this.panTour = panTour;
		this.parametres = parametres;
		this.imagesJeu = new ImagesJeu(parametres);
		this.grille.initGrille();
		
		this.positionXCases = new int[12];
		this.positionYCases = new int[12];
		for(int i = 0; i < 12; i ++){
			
			if (i < 6){
				positionXCases[i] = (127 *(5 - (i % 6)) + 20);
				positionYCases[i] = (dim.height/2 - 140);
			}
			else{
				positionXCases[i] = (128 * (i % 6) + 22);
				positionYCases[i] = (dim.height/2 + 10);
			}	
		}
		
		this.ecouteur = new EcouteurPartie(this);
		this.addMouseListener(ecouteur);
		
	}
	
	/*méthode paintComponent(Graphics g)
	 *  permet de dessiner les images du jeu
	 */

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(parametres.themeChange()){
			imagesJeu.changeArrierePlan();
		}
		Font police = new Font("Helvetica", Font.BOLD, 40);
		g.setFont(police);
		g.setColor(Color.WHITE);
		g.drawImage(imagesJeu.imageN(ImagesJeu.ARRIERE_PLAN), 0, 0, this.dim.width, this.dim.height, this);
		g.drawImage(imagesJeu.imageN(ImagesJeu.PLATEAU), 0, this.dim.height/2 - imagesJeu.imageN(ImagesJeu.PLATEAU).getHeight(this)/2, this);

		for(int i = 0; i < 12; i++){



			if(this.grille.nombreBillesDansCaseN(i) == 0)
				g.drawImage(imagesJeu.imageN(ImagesJeu.VIDE), positionXCases[i], positionYCases[i], this);
			else if(this.grille.nombreBillesDansCaseN(i) == 1)
				g.drawImage(imagesJeu.imageN(ImagesJeu.UNE_BILLE),  positionXCases[i], positionYCases[i], this);
			else if(this.grille.nombreBillesDansCaseN(i) == 2)
				g.drawImage(imagesJeu.imageN(ImagesJeu.DEUX_BILLES),  positionXCases[i], positionYCases[i], this);
			else if(this.grille.nombreBillesDansCaseN(i) == 3)
				g.drawImage(imagesJeu.imageN(ImagesJeu.TROIS_BILLES),  positionXCases[i], positionYCases[i], this);
			else if(this.grille.nombreBillesDansCaseN(i) == 4)
				g.drawImage(imagesJeu.imageN(ImagesJeu.QUATRE_BILLES),  positionXCases[i], positionYCases[i], this);
			else if(this.grille.nombreBillesDansCaseN(i) == 5)
				g.drawImage(imagesJeu.imageN(ImagesJeu.CINQ_BILLES),  positionXCases[i], positionYCases[i], this);
			else if(this.grille.nombreBillesDansCaseN(i) == 6)
				g.drawImage(imagesJeu.imageN(ImagesJeu.SIX_BILLES),  positionXCases[i], positionYCases[i], this);
			else if(this.grille.nombreBillesDansCaseN(i) == 7)
				g.drawImage(imagesJeu.imageN(ImagesJeu.SEPT_BILLES),  positionXCases[i], positionYCases[i], this);
			else
				g.drawImage(imagesJeu.imageN(ImagesJeu.PLEIN),  positionXCases[i], positionYCases[i], this);
			if (i == this.choix)
			{
				g.drawImage(imagesJeu.imageN(ImagesJeu.ROND_RED), positionXCases[this.choix], positionYCases[this.choix], this);
			}
			else
			{
				g.drawImage(imagesJeu.imageN(ImagesJeu.ROND), positionXCases[i] - 2, positionYCases[i] - 2, this);
			}
			//g.drawImage(imagesJeu.imageN(ImagesJeu.ROND), positionXCases[i] - 2, positionYCases[i] - 2, this);
			if(i < 6)
				g.drawString(""+this.grille.nombreBillesDansCaseN(i), positionXCases[i] + 50, positionYCases[i] - 10);
			else {
				g.drawString("" + this.grille.nombreBillesDansCaseN(i), positionXCases[i] + 50, positionYCases[i] + 160);
			}
			if(choix<6)
			{
				if (i == position_main) {
					g.drawImage(imagesJeu.imageN(ImagesJeu.MAIN1), positionXCases[i], positionYCases[i], this);
				}
			}
			else if (choix>5)
			{
				if (i == position_main) {
					g.drawImage(imagesJeu.imageN(ImagesJeu.MAIN2), positionXCases[i], positionYCases[i], this);
				}
			}
		}
		//g.drawImage(imagesJeu.imageN(ImagesJeu.ROND_RED), positionXCases[this.choix], positionYCases[this.choix], this);
	}
	
	/*méthode joueur1Gagne()
	 * retourne vrai si le joueur 1 a gagné, faux sinon
	 */
	
	private boolean joueur1Gagne(){
		if(type == 1 || type == 3)
			return ordi.score() > 24;
		else
			return (hum1.score() > 24);
	}
	
	/*méthode joueur2Gagne()
	 * retourne vrai si le joueur 2 a gagné, faux sinon
	 */
	private boolean joueur2Gagne(){
		if(type == 1)
			return hum1.score() > 24;
		else if(type == 2)
			return (hum2.score() > 24);
		else
			return (ordi2.score() > 24);
	}
	
	/*
	 * méthode plusAssezDeBilles()
	 * retourne vrai si le plateau ne contient plus suffisamment de billes
	 * cad qu'il en a moins de 7
	 */
	private boolean plusAssezDeBilles(){
		return (grille.nombreBillesDansGrille() <= 6);
	}
	
	/*
	 * méthode designeJoueurQuiCommence()
	 * permet: d'affecter qui va être le joueur à débuter la partie
	 * il affiche un panneau d'information pour prévenir l'utilisateur
	 */
	public void designeJoueurQuiCommence(){
		tourJ1 = new Accessoires().tirAuSort();
//		if (tourJ1){
//			if (type == 1)
//				JOptionPane.showMessageDialog( null, "L' " + ordi.nomJoueur() + " commence.", "Tirage au sort", JOptionPane.INFORMATION_MESSAGE);
//			else if(type == 2)
//				JOptionPane.showMessageDialog( null, hum1.nomJoueur() + " commence.", "Tirage au sort", JOptionPane.INFORMATION_MESSAGE);
//			else if(type == 3)
//				JOptionPane.showMessageDialog( null, "L' " + ordi.nomJoueur() + "1 commence.", "Tirage au sort", JOptionPane.INFORMATION_MESSAGE);
//		}
//		else{
//			if(type == 1)
//				JOptionPane.showMessageDialog( null, hum1.nomJoueur() + " commence.", "Tirage au sort", JOptionPane.INFORMATION_MESSAGE);
//			else if(type == 2)
//				JOptionPane.showMessageDialog( null, hum2.nomJoueur() + " commence.", "Tirage au sort", JOptionPane.INFORMATION_MESSAGE);
//			else if(type == 3)
//				JOptionPane.showMessageDialog( null, "L' " + ordi2.nomJoueur() + "2 commence.", "Tirage au sort", JOptionPane.INFORMATION_MESSAGE);
//			panTour.changeTour();
//		}
		panTour.init();
		panTour.repaint();
	}
	
	/*
	 * méthode designeJoueurGagnant()
	 * permet: de désigner qui est le gagnant de la partie
	 * et averti l'utilisateur par un panneau d'information.
	 */
	public void designeJoueurGagnant(){
		if(type == 1){
			if (ordi.score() > hum1.score())
				JOptionPane.showMessageDialog( null, "L' " + ordi.nomJoueur() + " a gagné.", "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
			else if(hum2.score() > ordi.score())
				JOptionPane.showMessageDialog( null, hum1.nomJoueur() + " a gagné.", "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog( null, "Egalité.", "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(type == 2){
			if (hum1.score() > hum2.score()){
				JOptionPane.showMessageDialog( null, hum1.nomJoueur() + " a gagné.", "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(hum2.score() > hum1.score())
				JOptionPane.showMessageDialog( null, hum2.nomJoueur() + " a gagné.", "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog( null, "Egalité.", "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
				
		}
		else if(type == 3){
			if (ordi.score() > ordi2.score())
				JOptionPane.showMessageDialog( null, "L' " + ordi.nomJoueur() + " a gagné.", "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
			else if(ordi2.score() > ordi.score())
				JOptionPane.showMessageDialog( null, "L' " + ordi2.nomJoueur() + " a gagné.", "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog( null, "Egalité.", "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/* méthode run()
	 * permet: de jouer une partie d'awalé.
	 */
	public synchronized void run() {
		
		int nombreBillesAvantCoup, nombreBillesApresCoup;
		Grille grilleApresCoup;
		grille.initGrille();// on initialise la grille
		designeJoueurQuiCommence();// on désigne le joueur commençant.
			while(!partieTerminee){
				if(tourJ1){ // si tour du joueur 1
					choix = grille.premiereCaseJouableOrdi(1);
					nombreBillesAvantCoup = grille.nombreBillesDansGrille();
					//----jb-------
					int minChoix = ordi.minMax(9, 0, ordi.score(), ordi2.score(), grille.etatGrille());
					if(this.grille.caseJouable(minChoix))
					{
						choix = minChoix;

					}
					if (choix < 0 || choix > 5){

						choix = grille.premiereCaseJouableOrdi(1);
						System.out.println("Poulouu"+choix);

					}
//					else
//					{
//						choix = grille.premiereCaseJouableOrdi(1);
//					}
					//----fin-jb-----
                   // System.out.println(" J1 Depth: " + ordi.difficulte());

					//jb
//					if(this.grille.etatGrille().nombreBillesDansCaseN(choix)==0)
//					{
//						choix = grille.premiereCaseJouableOrdi(1);
//					}
					//---fin-jb
					grilleApresCoup = this.grille.etatGrille();
					this.afficheEtapes(1);
					grilleApresCoup.deplaceBille(choix, 1);
					nombreBillesApresCoup = grilleApresCoup.nombreBillesDansGrille();
					ordi.modifieScore(nombreBillesAvantCoup - nombreBillesApresCoup);
					this.panScore.changeScoreJ1(ordi.score());
				}
				else{
					choix = grille.premiereCaseJouableOrdi(2);;
					nombreBillesAvantCoup = grille.nombreBillesDansGrille();
//----jb---
					int minChoix = ordi2.minMax(9, 1, ordi2.score(), ordi.score(), grille.etatGrille());
					if(this.grille.caseJouable(minChoix))
					{
						System.out.println("Pichaa" + choix);
						choix = minChoix;
					}
					if (choix < 6 || choix > 11){

						System.out.println("Picha PJouable" + choix);
						choix = grille.premiereCaseJouableOrdi(2);
					}
					//System.out.print(choix +"Merdedededede");
//					else
//					{
//						choix = grille.premiereCaseJouableOrdi(2);
//					}
					//-----fin--jb----
                  //  System.out.println(" J2 Depth: " + ordi2.difficulte());

					//-----jb-----

//					if(this.grille.etatGrille().nombreBillesDansCaseN(choix)==0)
//					{
//						choix = grille.premiereCaseJouableOrdi(2);
//					}
					//---jb----

					grilleApresCoup = this.grille.etatGrille();
					this.afficheEtapes(2);
					grilleApresCoup.deplaceBille(choix, 2);
					nombreBillesApresCoup = grilleApresCoup.nombreBillesDansGrille();
					ordi2.modifieScore(nombreBillesAvantCoup - nombreBillesApresCoup);
					this.panScore.changeScoreJ2(ordi2.score());
				}
				
				this.grille = grilleApresCoup;
				this.panScore.repaint();
				this.repaint();
				
				if (joueur1Gagne() || joueur2Gagne() || plusAssezDeBilles()){
					partieTerminee = true;
					designeJoueurGagnant();
				}
				else{
					tourJ1 = !tourJ1;
					this.panTour.changeTour();
					this.panTour.repaint();
				}
			}
		}
	/*méthode afficheEtapes(int numJoueur)
	 * permet: après un coup choisi par l'utilisateur ou le joueur
	 * d'afficher la distribution des graines, une à une dans chaque case.
	 */
	public void afficheEtapes(int numJoueur){
		int nombreEtapes = this.etatGrille().nombreBillesDansCaseN(choix);

		for(int i = choix; i <= choix + nombreEtapes; i++){
			if(i % 12 != choix){
				position_main=i%12;
				this.grille.modifieValeurXCaseN(i % 12, this.grille.nombreBillesDansCaseN(i % 12) + 1);
				this.repaint();
				try {
					Thread.sleep(parametres.vitesse());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else
				this.grille.modifieValeurXCaseN(i % 12, 0);
		}
		//position_main=1998;
	}
	
	public String toString(){
		String s = "";
		for(int i = 0; i < 10; i++)
		{
			s += "\n";
		}
		s += grille.toString() + "\n\n tour de ";
		if (this.tourJ1){
			if (type == 1)
				s += ordi.nomJoueur() + "(>=0 <=5)" ;
			else
				s += hum1.nomJoueur() + "(>=0 <=5)" ;
		}
		else{
			if(type == 1)
				s += hum1.nomJoueur() + "(>=6 <=11)";
			else
				s += hum2.nomJoueur() + "(>=6 <=11)";
		}
		if (type == 1)
			s += "\nscore J1: " + ordi.score() + " score J2: " + hum1.score();
		else
			s += "\nscore J1: " + hum1.score() + " score J2: " + hum2.score();
		
		return s;
	}
	
	public void aChoisi(int n){
		this.choix = n;
		this.continuer = false;
	}
	
	/*
	 * ACCESSEURS
	 */
	
	public Grille etatGrille(){
		return this.grille.etatGrille();
	}
	public int type(){
		return this.type;
	}
	public boolean estLeTourDuJoueur1(){
		return this.tourJ1;
	}
	public int positionXCaseN(int n){
		return positionXCases[n];
	}
	public int positionYCaseN(int n){
		return positionYCases[n];
	}
	public int scoreJoueur1(){
		if(this.type == 1)
			return this.ordi.score();
		else
			return this.hum1.score();
	}
	public int scoreJoueur2(){
		if(this.type == 1)
			return this.hum1.score();
		else
			return this.hum2.score();
	}
}
