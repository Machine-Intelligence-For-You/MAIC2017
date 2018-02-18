package Finale;

import jeu.Grille;
import jeu.Joueur;

public class Obf extends Joueur
{
	private static int profondeur=6;
	private Grille maGrille[]=new Grille[profondeur+1];
	private int minChezMoi=0,minChezLadversaire=6;
	
	public Obf(String name)
	{
        super(name);
    }
	
	public int minMax(int profondeurAParcourir, int numeroJoueur, int scoreAppelant, int scoreAdversaire, Grille grille)
	{
		if(numeroJoueur==1)
		{
			minChezMoi=6;
			minChezLadversaire=0;
		}
		
		int indiceiDonnantMaximum=-12;
    	int plus_grand_des_minimums=-48;
    	int mon_nombre_de_cases_non_jouables=0;
    	
    	for(int i=0;i<6;i++)
    	{
    		if(grille.caseJouable(minChezMoi+i))
    		{
    			maGrille[0]=grille.etatGrille();
        		int minimum=49;
    			
        		int nombre_de_graines_capturees_par_moi=nombre_de_graines_capturees(i+minChezMoi,minChezMoi,0);
        		int nombre_de_casesj_non_jouables=0;
        		
        		for(int j=0;j<6;j++)
        		{
        			if(maGrille[0].caseJouable(minChezLadversaire+j))
        			{
            			maGrille[1]=maGrille[0].etatGrille();
            			int nombre_de_graines_capturees_par_ladversaire=nombre_de_graines_capturees(j+minChezLadversaire,minChezLadversaire,1);
        				int nombre_de_graines_que_je_gagne_en_differentiel=nombre_de_graines_capturees_par_moi-nombre_de_graines_capturees_par_ladversaire+differentiel(2,minimum-(nombre_de_graines_capturees_par_moi-nombre_de_graines_capturees_par_ladversaire));
            			if(nombre_de_graines_que_je_gagne_en_differentiel<minimum)
        				{
        					minimum=nombre_de_graines_que_je_gagne_en_differentiel;
        				}
        			}
        			else
        			{
        				nombre_de_casesj_non_jouables++;
        			}
        			if(minimum<=plus_grand_des_minimums)
        			{
        				break;
        			}
        		}
        		if(nombre_de_casesj_non_jouables!=6 && plus_grand_des_minimums<minimum)
    			{
    				plus_grand_des_minimums=minimum;
    				indiceiDonnantMaximum=i;	
    			}		
    		}
    		else
    		{
    			mon_nombre_de_cases_non_jouables++;
    		}
    	}
    	
    	int retour=minChezMoi+indiceiDonnantMaximum;
    	if(!(mon_nombre_de_cases_non_jouables<5 && retour>=minChezMoi && retour<minChezMoi+6 && grille.getNombreBilleParCase(retour)!=0))
    	{
    		retour=grille.premiereCaseJouableOrdi(numeroJoueur+1);	 
    	}
    	return retour;
	}
	
	private int nombre_de_graines_capturees(int indice_de_la_case_choisie, int min, int grille)
	{
		int [] valeurs=new int [12];
		
		int sonMin=0;
		if(min==0)
		{
			sonMin=6;
		}
		
		for(int i=0;i<12;i++)
		{
			valeurs[i]=maGrille[grille].getNombreBilleParCase(i);
		}
		
		int nombre_de_deplacements=valeurs[indice_de_la_case_choisie];
		valeurs[indice_de_la_case_choisie]=0;
		int indice=1+indice_de_la_case_choisie;
		for(int a=1;a<=nombre_de_deplacements;a++,indice++)
		{
			if(indice==indice_de_la_case_choisie)
			{
				indice++;
			}
			if(indice==12)
			{
				indice=0;
			}
			valeurs[indice]++;	
		}
		
		if(indice==0)
		{
			indice=11;
		}
		else
		{
			indice=indice-1; 
		}
		
		int nombre_graines_capturees=0;
		while(indice>=0 && (valeurs[indice]==2 || valeurs[indice]==3) &&(indice>=sonMin) && (indice<sonMin+6))
		{
			nombre_graines_capturees+=valeurs[indice];
			valeurs[indice]=0;
			indice--;
		}
		
		for(int i=0;i<12;i++)
		{
			maGrille[grille].modifieValeurXCaseN(i, valeurs[i]);
		}
		
		return nombre_graines_capturees;
	}
	
	private int differentiel(int indiceDeMaGrille, int m)
	{
		int mon_nombre_de_cases_non_jouables=0;
		int plus_grand_des_minimums=-48;
    	for(int i=0;i<6;i++)
    	{
    		if(maGrille[-1+indiceDeMaGrille].caseJouable(minChezMoi+i))
    		{
    			maGrille[indiceDeMaGrille]=maGrille[-1+indiceDeMaGrille].etatGrille();
        		
        		int nombre_de_graines_capturees_par_moi=nombre_de_graines_capturees(i+minChezMoi,minChezMoi,indiceDeMaGrille);
        		
        		if(indiceDeMaGrille==profondeur)
        		{
        			if(plus_grand_des_minimums<nombre_de_graines_capturees_par_moi)
        			{
        				plus_grand_des_minimums=nombre_de_graines_capturees_par_moi;	
        			}
        			
        		}
        		else
        		{
        			int nombre_de_casesj_non_jouables=0;
            		int minimum=49;
            		
            		for(int j=0;j<6;j++)
            		{
            			if(maGrille[indiceDeMaGrille].caseJouable(minChezLadversaire+j))
            			{
            				maGrille[1+indiceDeMaGrille]=maGrille[indiceDeMaGrille].etatGrille();
                			int nombre_de_graines_capturees_par_ladversaire=nombre_de_graines_capturees(j+minChezLadversaire,minChezLadversaire,1+indiceDeMaGrille);
                			int nombre_de_graines_que_je_gagne_en_differentiel=nombre_de_graines_capturees_par_moi-nombre_de_graines_capturees_par_ladversaire+differentiel(indiceDeMaGrille+2,minimum-(nombre_de_graines_capturees_par_moi-nombre_de_graines_capturees_par_ladversaire));
                			if(nombre_de_graines_que_je_gagne_en_differentiel<minimum)
            				{
            					minimum=nombre_de_graines_que_je_gagne_en_differentiel;
            				}
            			}
            			else
            			{
            				nombre_de_casesj_non_jouables++;
            			}
            			if(minimum<=plus_grand_des_minimums)
            			{
            				break;
            			}
            		}
            		if(nombre_de_casesj_non_jouables!=6 && plus_grand_des_minimums<minimum)
        			{
        				plus_grand_des_minimums=minimum;	
        			}
        		}		
    		}
    		else
    		{
    			mon_nombre_de_cases_non_jouables++;
    		}
    		if(plus_grand_des_minimums>=m)
    		{
    			break;
    		}
    	}
    	if(mon_nombre_de_cases_non_jouables==6)
    	{
    		return 0;
    	}
    	else
    	{
    		return plus_grand_des_minimums;
    	}
	}
}