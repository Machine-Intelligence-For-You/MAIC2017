package Finale;

import jeu.Grille;
import jeu.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Codestorm extends Joueur {

	// Constructeur
	public Codestorm(String name) {
		super("CodeStorm.ai");
	}
	
	public static class State {

		private Grille grille;
		private int score;
		private int scoreAdversaire;
		private int caseJouer;
		private int numeroJoueur;

		/**
		 * @return the numeroJoueur
		 */
		public int getNumeroJoueur() {
			return this.numeroJoueur;
		}

		/**
		 * @param numeroJoueur
		 *            the numeroJoueur to set
		 */
		public void setNumeroJoueur(int numeroJoueur) {
			this.numeroJoueur = numeroJoueur;
		}

		/**
		 * @return the grille
		 */
		public Grille getGrille() {
			return this.grille;
		}

		/**
		 * @return the score
		 */
		public int getScore() {
			return this.score;
		}

		/**
		 * @param score
		 *            the score to set
		 */
		public void setScore(int score) {
			this.score = score;
		}

		/**
		 * @return the caseJouer
		 */
		public int getCaseJouer() {
			return this.caseJouer;
		}

		/**
		 * @param caseJouer
		 *            the caseJouer to set
		 */
		public void setCaseJouer(int caseJouer) {
			this.caseJouer = caseJouer;
		}

		/**
		 * @param grille
		 *            the grille to set
		 */
		public void setGrille(Grille grille) {
			this.grille = grille;
		}

		/**
		 * @return the scoreAdversaire
		 */
		public int getScoreAdversaire() {
			return this.scoreAdversaire;
		}

		/**
		 * @param scoreAdversaire
		 *            the scoreAdversaire to set
		 */
		public void setScoreAdversaire(int scoreAdversaire) {
			this.scoreAdversaire = scoreAdversaire;
		}
	}
	
	public class AlphaBeta {
		private double alpha;
		private double beta;
		private int indexOfBestMove;
		private int score;


		/**
		 * @return the score
		 */
		public int getScore() {
			return this.score;
		}

		/**
		 * @param score
		 *            the score to set
		 */
		public void setScore(int score) {
			this.score = score;
		}

		/**
		 * @return the alpha
		 */
		public double getAlpha() {
			return this.alpha;
		}

		/**
		 * @param alpha
		 *            the alpha to set
		 */
		public void setAlpha(double alpha) {
			this.alpha = alpha;
		}

		/**
		 * @return the beta
		 */
		public double getBeta() {
			return this.beta;
		}

		/**
		 * @param beta
		 *            the beta to set
		 */
		public void setBeta(double beta) {
			this.beta = beta;
		}

		/**
		 * @param beta
		 *            the beta to set
		 */
		public void setBeta(int beta) {
			this.beta = beta;
		}

		/**
		 * @return the indexOfBestMove
		 */
		public int getIndexOfBestMove() {
			return this.indexOfBestMove;
		}

		/**
		 * @param indexOfBestMove
		 *            the indexOfBestMove to set
		 */
		public void setIndexOfBestMove(int indexOfBestMove) {
			this.indexOfBestMove = indexOfBestMove;
		}
	}

	private static class Problem {

		public static int exploredNoeud = 0;
		
		public static boolean isEndOfGame(State node) {
			if ((node.getScore() > 24) || (node.getScoreAdversaire() > 24)
					|| (node.getGrille().nombreBillesDansGrille() <= 6)) {
				return true;
			}
			return false;
		}

		private static List<State> successor(State node, boolean maximizingPlayer) {
			List<State> list = new ArrayList<>();
			for (int i = 0 + (6 * node.getNumeroJoueur()); i < (6 + (6 * node.getNumeroJoueur())); i++) {
				State childNode = new State();
				childNode.setGrille(node.getGrille().etatGrille());
				childNode.setCaseJouer(i);
				childNode.setNumeroJoueur((node.getNumeroJoueur() + 1) % 2);
				if (childNode.getGrille().caseJouable(i)) {
					int score = childNode.getGrille().nombreBillesDansGrille();
					childNode.getGrille().deplaceBille(i, node.getNumeroJoueur() + 1);
					score -= childNode.getGrille().nombreBillesDansGrille();
					if (maximizingPlayer) {
						childNode.setScore(node.getScore() + score);
						childNode.setScoreAdversaire(node.getScoreAdversaire());
					} else {
						childNode.setScoreAdversaire(node.getScoreAdversaire() + score);
						childNode.setScore(node.getScore());
					}
					list.add(childNode);
				}
			}

			return list;
		}

		public static int estimate(State node) {
			Problem.exploredNoeud++;
			if (Problem.isEndOfGame(node)) {
				
				if (node.getScore() > node.getScoreAdversaire()) { // gagné
	                return Integer.MAX_VALUE;
	            } else if (node.getScore() == node.getScoreAdversaire()) { // match nul
	                return 0;
	            } else { // perdu
	                return -Integer.MAX_VALUE;
	            }
			}
			// estimate score
			return node.getScore() - node.getScoreAdversaire();
		}
	}

	public AlphaBeta alphabeta(State node, int depth, double alpha, double beta, boolean maximizingPlayer) {
		AlphaBeta retour = new AlphaBeta();
		retour.setIndexOfBestMove(-1);
		retour.setAlpha(alpha);
		retour.setBeta(beta);
		
		if ((depth == 0) || (Problem.isEndOfGame(node))) {
			retour.score = Problem.estimate(node);
			return retour;
		}
		
		for (State state : Problem.successor(node, maximizingPlayer)) {
			if (maximizingPlayer) {
				retour.score = Math.max((int) retour.alpha,
						this.alphabeta(state, depth - 1, alpha, beta, false).getScore());
				if (retour.score > retour.alpha) {
					retour.alpha = retour.score;
					retour.indexOfBestMove = state.getCaseJouer();
					// System.out.println(retour.getIndexOfBestMove());

				}

				if (retour.alpha > retour.beta) {
					break;
				}
			}
			else {
				if (retour.score <= retour.beta) {
					retour.beta = retour.score;
					retour.indexOfBestMove = state.getCaseJouer();
				}

				if (retour.alpha > retour.beta) {
					break;
				}
			}
		}
		
		return retour;

	}

	@Override
	public int minMax(int profondeurAParcourir, int numeroJoueur, int scoreAppelant, int scoreAdversaire,
			Grille grille) {
		State node = new State();
		node.setGrille(grille);
		node.setCaseJouer(0);
		node.setScore(scoreAppelant);
		node.setScoreAdversaire(scoreAdversaire);
		node.setNumeroJoueur(numeroJoueur % 2);
		Problem.exploredNoeud = 0;

		AlphaBeta score = this.alphabeta(node, 7, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY,
				true);

		return score.getIndexOfBestMove();
	}


}
