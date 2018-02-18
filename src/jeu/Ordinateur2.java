package jeu;

public class Ordinateur2 extends Joueur{

    Ordinateur2(String nom) {
        super(nom);
        // TODO Auto-generated constructor stub
    }
    public int meilleurjeux=-1;
    public int  meilleurCoup=-1;

    @Override
    public int minMax(int profondeurAParcourir, int numeroJoueur, int scoreAppelant, int scoreAdversaire,
                      Grille grille) {

        int premierchoix=0;

        int score=BestScore(profondeurAParcourir+6, scoreAdversaire,grille,premierchoix);

        return meilleurjeux;
    }

    public int gainJoueur(Grille grille, int choix, int numeroJoueur) {
        int gain=0, min=0, max=0;
        if(numeroJoueur==0) {
            min=0;
            max=5;
        }
        else{
            min=6;
           max=11;
        }
//        if(numeroJoueur==0) {
//            min=0;
//            max=5;
//        }
        int dernierPion=(choix+grille.nombreBillesDansCaseN(choix))%12;
        if(dernierPion>=min && dernierPion<=max) {
            if((grille.nombreBillesDansCaseN(dernierPion)+1)==2) {
                gain+=2;
                dernierPion-=1;
                while(dernierPion>=min && dernierPion<=max) {
                    if((grille.nombreBillesDansCaseN(dernierPion)+1)==2) {
                        gain+=2;
                    }
                    if((grille.nombreBillesDansCaseN(dernierPion)+1)==3) {
                        gain+=3;
                    }
                    if((grille.nombreBillesDansCaseN(dernierPion)+1)!=2 && (grille.nombreBillesDansCaseN(dernierPion)+1)!=3) {
                        break;
                    }
                    dernierPion--;
                }
            }
            dernierPion=(choix+grille.nombreBillesDansCaseN(choix))%12;
            if((grille.nombreBillesDansCaseN(dernierPion)+1)==3) {
                gain+=3;
                dernierPion-=1;
                while(dernierPion>=min && dernierPion<=max) {
                    if((grille.nombreBillesDansCaseN(dernierPion)+1)==2) {
                        gain+=2;
                    }
                    if((grille.nombreBillesDansCaseN(dernierPion)+1)==3) {
                        gain+=3;
                    }
                    if((grille.nombreBillesDansCaseN(dernierPion)+1)!=2 && (grille.nombreBillesDansCaseN(dernierPion)+1)!=3) {
                        break;
                    }
                    dernierPion--;
                }
            }
        }
        return gain;
    }


    public int minvalue(int profondeurAParcourir, int numeroJoueur, Grille grille,int dernierchoix,int alpha,int beta)
    {
        if (profondeurAParcourir<=0) return gainJoueur(grille.etatGrille(), dernierchoix, numeroJoueur);
        int meilleurScore=0;
        meilleurScore=999;
        for(int i=6;i<12;i++)
        {
            if(grille.caseJouable(i))
            {
                grille.deplaceBille(i, numeroJoueur);
                int score=maxvalue(profondeurAParcourir-1, numeroJoueur,grille.etatGrille(),i,alpha,beta);
                if(score<meilleurScore)
                {
                    meilleurScore=score;
                    meilleurCoup=i;
                }
                if(meilleurScore<=alpha)
                {
                    return meilleurScore;
                }
                beta=min(beta,meilleurScore);
            }

        }
        return meilleurScore;
    }

    public int maxvalue(int profondeurAParcourir, int numeroJoueur, Grille grille,int dernierchoix,int alpha,int beta)
    {
        if (profondeurAParcourir<=0) return gainJoueur(grille.etatGrille(), dernierchoix, numeroJoueur);

        int meilleurScore;


        meilleurScore=-999;
        for(int i=0;i<=6;i++)
        {
            if(grille.caseJouable(i))
            {
                grille.deplaceBille(i, numeroJoueur);
                int score=minvalue(profondeurAParcourir-1, (numeroJoueur+1)%2,grille.etatGrille(),i,alpha,beta);
                if(score>meilleurScore)
                {
                    meilleurScore=score;
                    meilleurCoup=i;
                }
                if(meilleurScore>=beta)
                {
                    return meilleurScore;
                }
                alpha=max(alpha,meilleurScore);
            }

        }
        return meilleurScore;

    }

    public int min(int a, int b) {
        if (a>b)
            return b;
        if(a<b) return a;

        else return a;
    }

    public int max(int a, int b) {
        if (a>b)
            return a;
        if(a<b) return b;

        else return a;
    }

    public int BestScore(int profondeurAParcourir, int numeroJoueur, Grille grille,int dernierchoix)
    {


        int meilleurScore=-1;

        //if(meilleurCoup==-1)
        //	meilleurScore=gainJoueur(grille.etatGrille(), dernierchoix, numeroJoueur);

        meilleurScore=maxvalue(profondeurAParcourir,numeroJoueur, grille,dernierchoix,-100,100);

        meilleurjeux=meilleurCoup;
        return meilleurScore;

    }




}
