package graphique;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import jeu.Partie;

import javax.swing.*;

public class EcouteurPartie implements MouseListener {
	
	Partie partie;
	
	public EcouteurPartie(Partie partie){
		this.partie = partie;
	
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		if(this.partie.type() == 1){
			if(!this.partie.estLeTourDuJoueur1()){
				for(int i = 6; i < 12; i++){
					if( (arg0.getX() >= this.partie.positionXCaseN(i)) && (arg0.getX() <= this.partie.positionXCaseN(i) + 120) 
							&& (arg0.getY() >=  this.partie.positionYCaseN(i)) && (arg0.getY() <= this.partie.positionYCaseN(i) + 121)){
						if(this.partie.etatGrille().caseJouable(i)){
							this.partie.aChoisi(i);
							
						}
                        else
                        {
                            JOptionPane.showMessageDialog( null, "Vous ne pouvez pas jouer ce coup!","Alert", JOptionPane.INFORMATION_MESSAGE);
                        }
					}
				}
			}
		}
		else if(this.partie.type() == 2){
			if(this.partie.estLeTourDuJoueur1()){
				for(int i = 0; i < 6; i++){
					if( (arg0.getX() >= this.partie.positionXCaseN(i)) && (arg0.getX() <= this.partie.positionXCaseN(i) + 120) 
							&& (arg0.getY() >=  this.partie.positionYCaseN(i)) && (arg0.getY() <= this.partie.positionYCaseN(i) + 121)){
						if(this.partie.etatGrille().caseJouable(i)){
						
							this.partie.aChoisi(i);
						
						}
						else
						{
							JOptionPane.showMessageDialog( null, "Vous ne pouvez pas jouer ce coup!","Alert", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		
			else{
				for(int i = 6; i < 12; i++){
					if( (arg0.getX() >= this.partie.positionXCaseN(i)) && (arg0.getX() <= this.partie.positionXCaseN(i) + 120) 
							&& (arg0.getY() >=  this.partie.positionYCaseN(i)) && (arg0.getY() <= this.partie.positionYCaseN(i) + 121)){
						if(this.partie.etatGrille().caseJouable(i)){
							
							this.partie.aChoisi(i);
							
						}
                        else
                        {
                            JOptionPane.showMessageDialog( null, "Vous ne pouvez pas jouer ce coup!","Alert", JOptionPane.INFORMATION_MESSAGE);
                        }
					}
				}
			}
		}
	}



	public void mouseEntered(MouseEvent e) {
		
		/*if(this.partie.type() == 1){
			if(!this.partie.estLeTourDuJoueur1()){
				for(int i = 6; i < 12; i++){
					if( (e.getX() >= this.partie.positionXCaseN(i)) && (e.getX() <= this.partie.positionXCaseN(i) + 120) 
							&& (e.getY() >=  this.partie.positionYCaseN(i)) && (e.getY() <= this.partie.positionYCaseN(i) + 121)){
						if(this.partie.etatGrille().caseJouable(i)){
							this.selectionneur.changeNumero(i);
						}
					}
					else
						this.selectionneur.changeNumero(-1);
						
				}
			}
		}
		else if(this.partie.type() == 2){
			if(this.partie.estLeTourDuJoueur1()){
				for(int i = 0; i < 6; i++){
					if( (e.getX() >= this.partie.positionXCaseN(i)) && (e.getX() <= this.partie.positionXCaseN(i) + 120) 
							&& (e.getY() >=  this.partie.positionYCaseN(i)) && (e.getY() <= this.partie.positionYCaseN(i) + 121)){
						if(this.partie.etatGrille().caseJouable(i)){
						
							this.selectionneur.changeNumero(i);
						
						}
					}
					else
						this.selectionneur.changeNumero(-1);
				}
			}
		
			else{
				for(int i = 6; i < 12; i++){
					if( (e.getX() >= this.partie.positionXCaseN(i)) && (e.getX() <= this.partie.positionXCaseN(i) + 120) 
							&& (e.getY() >=  this.partie.positionYCaseN(i)) && (e.getY() <= this.partie.positionYCaseN(i) + 121)){
						if(this.partie.etatGrille().caseJouable(i)){
							
							this.selectionneur.changeNumero(i);
							
						}
					}
					else
						this.selectionneur.changeNumero(-1);
				}
			}
		}
		this.selectionneur.repaint();
		*/
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
