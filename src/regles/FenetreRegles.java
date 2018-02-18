package regles;

import javax.swing.JFrame;

public class FenetreRegles extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PanneauRegles panneauRegles;
	public FenetreRegles(){
		super();
		this.setTitle("Règles Awale");
		this.setSize(1200, 600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.panneauRegles = new PanneauRegles();
	    this.add(panneauRegles);
	    

	}
	
}
