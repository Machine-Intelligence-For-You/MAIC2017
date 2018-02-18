package Finale;

import jeu.Grille;
import jeu.Joueur;

public class Mlvelocity extends Joueur {
	private int depth,count;
	public Mlvelocity(String name) {
		super(name);
		depth=5;
		count=0;
	}
	public int[] alphabeta(int profondeurAParcourir, int numeroJoueur, int scoreAppelant, int scoreAdversaire, Grille grille, int alpha, int beta) {
		int choix=-1;
               if(profondeurAParcourir==0){
                        int casesVul=0;
                        for (int i = numeroJoueur  != 0 ? 6 : 0, n = i; i < n + 6; i++) {
                            if (grille.getNombreBilleParCase(i) < 3 && grille.getNombreBilleParCase(i) >= 0) ++casesVul;
                        }

            if(scoreAdversaire>=25)return new int[]{9999999,choix};
            return new int[]{7*(scoreAdversaire-scoreAppelant)+4*(25-scoreAppelant)+5*(casesVul-6),choix};
		}
		else {
			int score;
			for (int i = numeroJoueur != 0 ? 6 : 0, n = i; i < n + 6; ++i) {
				if (grille.caseJouable(i)) {
					Grille g = grille.etatGrille();
					g.deplaceBille(i, numeroJoueur + 1);
					  score = alphabeta(profondeurAParcourir - 1, numeroJoueur != 0 ? 0 : 1, scoreAdversaire, scoreAppelant + grille.nombreBillesDansGrille()-g.nombreBillesDansGrille(), g, alpha, beta)[0];

					if (numeroJoueur == this.numJoueur() - 1) {
						if (score > alpha) {
							alpha = score;
							choix = i;
						}
					} else {
						if (score < beta) {
							beta = score;
							choix = i;
						}

					}
                                    
					if (alpha >= beta) break;
				}
                                
			}
		}
		return new int[] {(numeroJoueur == this.numJoueur() - 1) ? alpha : beta,choix};
	}

	public int minMax(int profondeurAParcourir, int numeroJoueur, int scoreAppelant, int scoreAdversaire, Grille grille) {
            if(++count==1) return this.numJoueur()-1 == 0 ? 0 : 6;
	   return alphabeta(depth, numeroJoueur,  scoreAppelant,  scoreAdversaire,  grille,-100000,100000)[1];
	}
}
