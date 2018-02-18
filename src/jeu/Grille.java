package jeu;




public class Grille{
	
	/*
	 * private int[] grille
	 * représentation de la grille de jeu
	 * par un tableau de 12 entiers.
	 */
	private int[] grille = new int[12];
	
	
	//Constructeur
	public Grille()
	{
		super();
	}
	
	/* 
	 * méthode: initGrille()
	 * permet: l'initialisation de la grille de jeu,
	 * 		   affecte 4 à chaque case du tableau.
	 */
	public void initGrille(){
		for(int i = 0; i < 12; i++){
			this.grille[i] = 4;
		}
	}

	public int getNombreBilleParCase(int i){
		return  this.grille[i];
	}
	
	/*
	 * méthode: deplaceBille(int choix, int numJ){
	 * pré-requis: choix doit être compris entre 0 et 11, numJ entre 1 et 2
	 * permet: d'effectuer le déplacement des billes selon la case
	 * 		   "choix" choisie, et de supprimer les billes gagnées par le joueur.
	 */
	public void deplaceBille(int choix, int numJ){
		
		int nbDeplacement = grille[choix];
		int caseARemplir = choix + 1, caseAVider;
		
		grille[choix] = 0;
		
		for(int deplacementFaits = 0; deplacementFaits < nbDeplacement; deplacementFaits++){
			if(caseARemplir > 11)
				caseARemplir = 0;
			this.grille[caseARemplir]++;
			caseARemplir++;
			if (caseARemplir == choix)
				caseARemplir++;
		}
		caseAVider = caseARemplir - 1;
		if(numJ == 1){
			if ((this.grille[caseAVider] <= 3 && this.grille[caseAVider] > 1) &&
					(caseAVider > 5 && caseAVider <= 11)){
				this.grille[caseAVider] = 0;
				caseAVider--;
				while(caseAVider > 5 && caseAVider <= 11 && this.grille[caseAVider] <= 3 && this.grille[caseAVider] > 1){
					this.grille[caseAVider] = 0;
					caseAVider--;
				}
			}
		}
		else{
			if ((this.grille[caseAVider] <= 3 && this.grille[caseAVider] > 1) &&
					(caseAVider >= 0 && caseAVider <= 5)){
				this.grille[caseAVider] = 0;
				caseAVider--;
				while(caseAVider >= 0 && caseAVider <= 5 && this.grille[caseAVider] <= 3 && this.grille[caseAVider] > 1){
					this.grille[caseAVider] = 0;
					caseAVider--;
				}
			}
		}
	}
	
	/*
	 * méthode nombreBillesDansGrille()
	 * résultat: retourne le nombre de billes contenues dans la grille de jeu
	 */
	
	public int nombreBillesDansGrille(){
		int somme = 0;
		for(int i = 0; i < 12; i++)
			somme += this.grille[i];
		return somme;
	}
	
	/*
	 * méthode caseJouable(int i)
	 * pré-requis: i doit être compris entre 0 et 11.
	 * résultat: retourne vrai si la case est jouable, c'est à dire si
	 * 			 le nombre de billes contenues dans celle ci est > 0.
	 */
	public boolean caseJouable(int i)
    {

		Grille copie = this.etatGrille();
		int before_sommeJ1 = 0;
		int before_sommeJ2 = 0;
		for(int j = 0; j < 6; j++)
		{
			before_sommeJ1 = before_sommeJ1 + copie.grille[j];
			before_sommeJ2 = before_sommeJ2 + copie.grille[j + 6];
		}
		if(i == -1){
			return  false;
		}
		if(i < 6)
		{
			if(before_sommeJ2 == 0)
			{
                copie.deplaceBille(i,1);
                int after_sommeJ2 = 0;
                for(int j = 6; j < 12; j++)
                {
                    after_sommeJ2 = after_sommeJ2 + copie.grille[j];
                }
                return this.grille[i] > 0 && after_sommeJ2 > 0;


			}
			else
			{
				copie.deplaceBille(i,1);
				int after_sommeJ2 = 0;
				for(int j = 6; j < 12; j++)
				{
					after_sommeJ2 = after_sommeJ2 + copie.grille[j];
				}
				return this.grille[i] > 0 && after_sommeJ2 > 0;
			}
		}
		else
		{

			if(before_sommeJ1 == 0)
			{
                copie.deplaceBille(i,2);
                int after_sommeJ1 = 0;
                for(int j = 0; j < 6; j++)
                {
                    after_sommeJ1 = after_sommeJ1 + copie.grille[j];
                }
                return this.grille[i] > 0 && after_sommeJ1 > 0;


			}
			else
			{
				copie.deplaceBille(i,2);
				int after_sommeJ1 = 0;
				for(int j = 0; j < 6; j++)
				{
					after_sommeJ1 = after_sommeJ1 + copie.grille[j];
				}
				return this.grille[i] > 0 && after_sommeJ1 > 0;
			}
		}
    }
	
	/*
	 * méthode modifieValeurXCaseN(int n, int x)
	 * permet: de modifier une case n de la grille, par la valeur x.
	 */
	public void modifieValeurXCaseN(int n, int x){
		this.grille[n] = x;
	}
	
	/*
	 * méthode nombreBillesDansCaseN(int n)
	 * résultat: retourne le nombre de bille contenues dans la grille
	 */
	public int nombreBillesDansCaseN(int n){
		return this.grille[n];
	}
	
	
	/* 
	 * méthode premiereCaseJouableOrdi()
	 * résultat: retourne la premiere case jouable par l'ordinateur
	 * 			 en partant de la case 0.
	 */
	public int premiereCaseJouableOrdi(int numJoueur){
		if(numJoueur==1)
		{
			int i = 0;
			while (!caseJouable(i) && ( i >= 0 && i < 6))
			{
				i++;
			}
			if(i==6){
				i=5;
			}
			return i;
		}
		else
		{
			int i = 6;
			while (!caseJouable(i)&& ( i >= 6 && i < 12))
			{
				i++;
			}

			if(i==12){
				i=11;
			}
			return i;
		}
	}

	
	/*
	 * méthode etatGrille()
	 * résultat: retourne une copie de l'état actuel de la grille.
	 */
	
	public Grille etatGrille(){
		Grille copie;
		copie = new Grille();
		for(int i = 0; i < this.grille.length; i++)
			copie.grille[i] = this.grille[i];
		return copie;
	}
	
	public String toString(){
		String s = "";
		for (int i = 5; i >= 0; i--)
		{
			s += grille[i] + " ";
		}
		s += "\n-----------\n";
		for (int i = 6; i < 12; i ++)
		{
			s += grille[i] + " ";
		}
		return s;
	}
}
