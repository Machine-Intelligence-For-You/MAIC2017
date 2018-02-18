package jeu;

public abstract class Joueur {

	public static void setNombreJoueur(int nombreJoueur) {
		Joueur.nombreJoueur = nombreJoueur;
	}

	/*
         * int nombreJoueur.
         * variable de classe permettant de savoir combien d'objet joueur ont
         * étés instanciés.
         */
	private static int nombreJoueur = 0;
	private int numJoueur;// numéro du joueur.
	private int score;// score du joueur



	private String nom;// nom du joueur.
	
	//Constructeur
	public Joueur(String nom){
		numJoueur = ++nombreJoueur;
		System.out.println(numJoueur);
		score = 0;
		this.nom = nom;
	}
	
	
	/*
	 * méthode modifieScore(int points)
	 * permet: de modifier le score en ajoutant l'entier "points" au score actuel
	 */
	public void modifieScore(int points){
		score += points;
	}

	public abstract int minMax(int profondeurAParcourir, int numeroJoueur, int scoreAppelant, int scoreAdversaire, Grille grille);
	//ACCESSEURS
	public int numJoueur(){
		return this.numJoueur;
	}
	public int score(){
		return this.score;
	}
	public String nomJoueur(){
		return this.nom.toString();
	}
	

}
