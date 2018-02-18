package jeu;

import java.util.Scanner;

public abstract class Humain extends Joueur {

	//Constructeur
	Humain(String nom){
		super(nom);
	}
	
	/*
	 * méthode choix
	 * résultat: retourne le choix du joueur quand à la case qu'il a choisi de jouer.
	 */
	
	public int choix() {
		int choix;
		Scanner clavier = new Scanner(System.in);
		choix = clavier.nextInt();
		while (super.numJoueur() == 1 && (choix < 0 || choix > 5)){
			System.out.println("Veuillez taper un nombre entre 0 et 5");
			choix = clavier.nextInt();
		}
		while (super.numJoueur() == 2 && (choix < 6 || choix > 11)){
			System.out.println("Veuillez taper un nombre entre 6 et 11");
			choix = clavier.nextInt();
		}
		
		return choix;
	}
	


}
