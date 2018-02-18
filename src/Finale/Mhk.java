package Finale;

// import java.util.Random;

import jeu.Grille;
import jeu.Joueur;

public class Mhk extends Joueur {

//	public void setDifficulte(int difficulte) {
//		this.difficulte = difficulte;
//	}

	/*
         * int difficulte
         * variable qui va déterminer à quelle profondeur maximale
         * l'ordinateur va utiliser l'algorithme minmax.
         */
//	private int difficulte;

    //Constructeur
    public Mhk(String name){
        super(name);
        //this.difficulte = difficulte;
    }



    /*
     * fonction minMax
     * retourne: le dernier retour de cette méthode est un coup jugé intéressant pour l'appelant, cependant
     * lors des appels récursifs la méthode renvoit les valeurs des feuilles et noeuds que l'algorithme aura choisi.
     */
    public int minMax(int profondeurAParcourir, int numeroJoueur, int scoreAppelant, int scoreAdversaire, Grille grille) {

//		Random random = new Random();


        //test 14/11/2017 cteation de KAlliA
        int min=(numeroJoueur==1)?6:0;
        int max=(numeroJoueur==1)?11:5;
        int grand=0;
        boolean debut=true;

        for(int j=min; j<=max;j++){
            if(grille.nombreBillesDansCaseN(j)!=4) debut=false;
        }
        if(debut){
            System.out.println("KAlliA");
            return min+2;
        }

        if(grille.nombreBillesDansCaseN(max)>=16){
            System.out.println("KAlliA");
            return max;
        }else if(grille.nombreBillesDansCaseN(max-1)>=17){
            System.out.println("KAlliA");
            return max-1;
        }

        for(int j=min; j<=max-2;j++){
            if(grand<grille.nombreBillesDansCaseN(j)) grand=j;

        }


        if(grille.caseJouable(grand)){
            System.out.println("KAlliA");
            return grand;
        }else{
            //	if(grand<max-2){
            if(grille.caseJouable(grand+1)){
                System.out.println("KAlliA");
                return grand+1;
            }
            //	}
            else if(grille.caseJouable(max-1)){

                System.out.println("KAlliA");
                return max-1;

            }else{
                if(grille.caseJouable(max)){

                    System.out.println("KAlliA");
                    return max;
                }
            }


        }

        return grille.premiereCaseJouableOrdi(numeroJoueur + 1);

        // Coded by KAMINAT


    }




    //ACCESSEUR
    //public int difficulte(){
//		return this.difficulte;
}


