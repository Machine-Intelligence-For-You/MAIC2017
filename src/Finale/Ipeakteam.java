package Finale;

import jeu.Grille;
import jeu.Joueur;

import java.util.ArrayList;

/**
 * Created by root on 11/15/17.
 */
public class Ipeakteam extends Joueur {

    private static double MAX_VALUE_FOR_ALPHA = 10000 ;
    private int caseChoisie;
    private int profondeurInitiale;
    private double HERE_IS_NORMALLY_NEVER_REACHED = -1000000000000000000000.0;

    public Ipeakteam(String nom) {
        super(nom);
    }

    @Override
    public int minMax(int profondeurAParcourir, int numeroJoueur, int scoreAppelant, int scoreAdversaire, Grille grille) {
        profondeurAParcourir = 9;
        profondeurInitiale=profondeurAParcourir;
        ArrayList<Integer> casesJouables = new ArrayList<>();
        for (int i = 0+6*numeroJoueur; i < 6+6*numeroJoueur; i++) {
            if (grille.caseJouable(i))casesJouables.add(i);
        }
        if (casesJouables.size()==1)return casesJouables.get(0);
        monNegaBeta(profondeurAParcourir,numeroJoueur,grille,0, -MAX_VALUE_FOR_ALPHA,MAX_VALUE_FOR_ALPHA);
        System.out.println("SoniAliBerK a choisi "+caseChoisie);
        return caseChoisie;
    }

    private double monNegaBeta(int profondeurAParcourir, int numeroJoueur, Grille grille,double gainDeplacementPrecedent, double alpha,double beta)

    {
        ArrayList<Integer> casesJouables = new ArrayList<>();
        for (int i = 0+6*numeroJoueur; i < 6+6*numeroJoueur; i++) {
            if (grille.caseJouable(i))casesJouables.add(i);
        }
        // no more possible moves for the current player
        // He can't play. So the opponent earns all the remaining seeds;
        if (casesJouables.size()==0)
        {
            return grille.nombreBillesDansGrille();
        }
        else
            {
                    if (profondeurAParcourir==0)

                        {
                            return eval(grille,casesJouables,numeroJoueur);
                        }
                        else if (profondeurAParcourir>0)
                        {

                            for (int i = 0; i <casesJouables.size() ; i++)
                            {
                                int caseActuelle = casesJouables.get(i);
                                Grille grilleCopie = grille.etatGrille();
                                grilleCopie.deplaceBille(caseActuelle,numeroJoueur);
                                double gainAvant = grille.nombreBillesDansGrille();
                                double gainApres = grilleCopie.nombreBillesDansGrille();
                                double gainDeplacementActuel = gainAvant-gainApres;
                                double monNegaBetaTemp = gainDeplacementActuel-(1/(profondeurInitiale+1-profondeurAParcourir))*monNegaBeta(profondeurAParcourir-1,
                                        1-numeroJoueur,grilleCopie,gainDeplacementActuel,-beta,-alpha);
                                if (monNegaBetaTemp>alpha)
                                {
                                    alpha = monNegaBetaTemp;
                                    if (profondeurAParcourir==profondeurInitiale)
                                    {
                                        caseChoisie = caseActuelle;
                                    }
                                    if (alpha-gainDeplacementPrecedent>=beta)return alpha;
                                }
                            }
                            return alpha;
                        }

                    }
        return HERE_IS_NORMALLY_NEVER_REACHED;
    }

    private double eval(Grille grille, ArrayList<Integer> casesJouables, int numeroJoueur)
    {
        Grille grilleCopie = grille.etatGrille();
        double valeur = 0;
        for (int i = 0; i <casesJouables.size() ; i++) {
            int caseActuelle = casesJouables.get(i);
            grilleCopie.deplaceBille(caseActuelle,numeroJoueur);
            double nombreDeBillesAvantDeplacement = grille.nombreBillesDansGrille();
            double nombreDeBillesApresDeplacement = grilleCopie.nombreBillesDansGrille();
            double gain = nombreDeBillesAvantDeplacement-nombreDeBillesApresDeplacement;
            valeur = Math.max(valeur,gain);
            // Reinitializing grilleCopieValue;
            grilleCopie = grille.etatGrille();
        }
        return valeur;
    }
}
