package Finale;

import jeu.Grille;
import jeu.Joueur;

public class Slh extends Joueur {
	
	private boolean firstCall,continu;
	//private int candidats[]=new int[6];
	private int scores[][]=new int[2][6];
	private int nombreJeu=0;
	private int unMax[]={-25,-25,-25,-25, -25};
	private int unGain[]={0,0,0,0,0};

	public Slh(String nom) {
		super(nom);
		this.firstCall=true;
		this.continu=false;
		for(int k=0;k<6;k++)
		{
			scores[0][k]=0;
			scores[1][k]=-1;
		}
		// TODO Auto-generated constructor stub
	}

	@Override
	public int minMax(int profondeurAParcourir, int numeroJoueur,
			int scoreAppelant, int scoreAdversaire, Grille grille) {
		//Grille copie = grille.etatGrille();
		int nombre=0;
		boolean dernier=true;
		int monMax=-25,index=0,numAd=1,nbreMax=0;
		if(numeroJoueur==1) numAd=0;
		if(grille.nombreBillesDansGrille()<8) dernier=false;
		if(firstCall)
		{
			nombre=3;
			nombreJeu++;
			//if( nombreJeu<6) nombre=2;
			//else if( nombreJeu<20&&this.nbreCasesJouables(numeroJoueur, grille)<=4&&this.nbreCasesJouables(numAd, grille)<=2 ) nombre=4;
			//else if( nombreJeu<20) nombre=3;
			if( grille.nombreBillesDansGrille()<20&&this.nbreCasesJouables(numeroJoueur, grille)<=4&&this.nbreCasesJouables(numAd, grille)<=4 ) nombre=4;
			//if( nombreJeu<40&& this.nbreCasesJouables(numeroJoueur, grille)<=3&&this.nbreCasesJouables(numAd, grille)<=4 ) nombre=4;
			//if(this.nbreCasesJouables(numeroJoueur, grille)<=4 && this.nbreCasesJouables(numAd, grille)<=4) nombre=5;
			//if(scoreAppelant<scoreAdversaire) nombre=4;
			//if(grille.nombreBillesDansGrille()<10) nombre=5;
			//System.out.print(this.nbreCasesJouables(numeroJoueur, grille)+"     "+nbreCasesJouables(numAd, grille)+"     "+nombre+"    ");
			profondeurAParcourir=nombre;
			firstCall=false;
		}
		profondeurAParcourir--;
		for(int i=0;i<=profondeurAParcourir;i++)
		{
			unMax[i]=-25;
			unGain[i]=0;
		}
		for(int i=numeroJoueur*6;i<numeroJoueur*6+6;i++)
		{
			if(grille.caseJouable(i))
			{
				int monGain=0;
				Grille copie1=grille.etatGrille();
				copie1.deplaceBille(i, numeroJoueur+1);
				monGain=grille.nombreBillesDansGrille()-copie1.nombreBillesDansGrille();
				if(profondeurAParcourir==0&&monGain>=monMax)
				{
					continu=true;
				}
				else if(profondeurAParcourir!=0) continu=true;
				else continu=false;
				if(continu)
				{
					if(profondeurAParcourir==nombre-1&&scoreAdversaire<scoreAppelant+monGain&&copie1.nombreBillesDansGrille()<=5) return i;
					if(profondeurAParcourir==nombre-1) scores[0][i-numeroJoueur*6]=monGain;
					int sonMax=-25;
					for(int j=numAd*6;j<numAd*6+6;j++)
					{
						if(copie1.caseJouable(j))
						{
							int sonGain=0;
							Grille copie2=copie1.etatGrille();
							copie2.deplaceBille(j, numAd+1);
							sonGain=copie1.nombreBillesDansGrille()-copie2.nombreBillesDansGrille();
							unGain[profondeurAParcourir]=sonGain;
							if(profondeurAParcourir!=0) sonGain-=minMax(profondeurAParcourir,numeroJoueur,scoreAppelant,scoreAdversaire,copie2);
							if(sonGain>sonMax)
							{
								sonMax=sonGain;
								unMax[profondeurAParcourir]=sonMax;
								if(monGain-sonMax<monMax)
								{
									break;
									//continuer=false;
								}
							}
						}
					}
					monGain-=sonMax;
					if(dernier&&monGain>=monMax)
					{
						if(monGain==monMax)
						{
							nbreMax++;
						}
						else
						{
							nbreMax=1;
						}
						monMax=monGain;
						index=i;
					}
					else if(!dernier&&monGain>monMax)
					{
						if(monGain==monMax)
						{
							nbreMax++;
						}
						else
						{
							nbreMax=1;
						}
						monMax=monGain;
						index=i;
					}
					if(profondeurAParcourir!=nombre-1&&unGain[profondeurAParcourir+1]-monMax<unMax[profondeurAParcourir+1]) break;
				}
			}
		}
		if(profondeurAParcourir!=nombre-1)
		{
			index=monMax;
		}
		else
		{
			firstCall=true;
			System.out.println(monMax);
		}
		return index;
	}
	
	int nbreCasesJouables(int numJ,Grille g)
	{
		int nbre=0;
		for(int k=numJ*6;k<numJ*6+6;k++) if(g.caseJouable(k)) nbre++;
		return nbre;
	}

}
