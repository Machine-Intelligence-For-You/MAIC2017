package graphique;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;


import jeu.Joueur;
import regles.FenetreRegles;


public class Fenetre extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JMenuBar menu;
	
	private JMenu fichier;
	private JMenuItem nouveau;
	private JMenuItem quitter;
	
	private JMenu aPropos;
	private JMenuItem aPropos2;
	private JMenuItem regles;
	
	private JMenu options;
	private JMenu vitesse;
	private JMenu theme;
	
	private JRadioButton lent;
	private JRadioButton moyen;
	private JRadioButton rapide;
	
	private JRadioButton art;
	private JRadioButton tapis;
	
	private PanneauAccueil panneauMenu;
	private PanneauJeu panneauJeu;
	private JPanel conteneur;
	private Dimension dim;
	
	
	private FenetreRegles fenetreRegles;
	private Parametres parametres;
	public Fenetre(){
		super();
		this.setTitle("Awale");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setLayout(new BorderLayout());
	    this.parametres = new Parametres();
	    
	    this.dim = new Dimension(this.getWidth(), this.getHeight());
	    
	    this.menu = new JMenuBar();
	    this.fichier = new JMenu("Fichier");
	    this.aPropos = new JMenu("A propos");
	    this.options = new JMenu("Options");
	    this.vitesse = new JMenu("Vitesse");
	    this.theme = new JMenu("Thème");
	    
	    this.nouveau = new JMenuItem("Nouveau");
	    this.nouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                InputEvent.CTRL_MASK));
	    
	    nouveau.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				conteneur.removeAll();
				panneauJeu = new PanneauJeu(dim, parametres);
				conteneur.add(panneauJeu);
				Joueur.setNombreJoueur(0);
				conteneur.revalidate();
				
			}
	    });
	    
	    this.quitter = new JMenuItem("Quitter");
	    this.quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
	                                                  KeyEvent.CTRL_MASK));
	    this.quitter.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		System.exit(0);
	    	}
	    });
	    
	    this.lent = new JRadioButton("Lente");
	    lent.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent arg0) {
				parametres.changeVitesse(1);
				moyen.setSelected(false);
				rapide.setSelected(false);
			}	    	
	    });
	    
	    this.moyen = new JRadioButton("Moyenne", true);
	    moyen.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent arg0) {
				parametres.changeVitesse(2);
				lent.setSelected(false);
				rapide.setSelected(false);
			}	    	
	    });
	    
	    this.rapide = new JRadioButton("Rapide");
	    rapide.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent arg0) {
				parametres.changeVitesse(3);
				lent.setSelected(false);
				moyen.setSelected(false);
			}	    	
	    });
	    
	    this.tapis = new JRadioButton("tapis");
	    tapis.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent arg0) {
				parametres.changeTheme(2);
				art.setSelected(false);
			}	    	
	    });
	    
	    this.art = new JRadioButton("art", true);
	    art.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent arg0) {
				parametres.changeTheme(1);
				tapis.setSelected(false);
			}	    	
	    });
	    
	    
	    this.aPropos2 = new JMenuItem("Awale");
	    aPropos2.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		JOptionPane.showMessageDialog(null,
							    		          "Créateur: MP.\nLicense: Freeware.\nSource règles: Wikipédia.\n",
							    		          "Information", JOptionPane.NO_OPTION);
	    	}
	    });
	    
	    this.regles = new JMenuItem("Règles");
	    this.regles.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
                InputEvent.CTRL_MASK));
	    regles.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent arg0) {
				fenetreRegles = new FenetreRegles();
				fenetreRegles.setVisible(true);
			}	    	
	    });
	    
	   
	    this.fichier.add(this.nouveau);
	    this.fichier.addSeparator();
	    this.fichier.add(this.quitter);
	    
	    this.vitesse.add(lent);
	    this.vitesse.add(moyen);
	    this.vitesse.add(rapide);
	   
	    this.theme.add(art);
	    this.theme.add(tapis);
	    
	    this.options.add(vitesse);
	    this.options.add(theme);
	    
	    this.aPropos.add(regles);
	    this.aPropos.addSeparator();
	    this.aPropos.add(aPropos2);
	    
	    this.menu.add(this.fichier);
		this.menu.add(options);
		this.menu.add(aPropos);
	    
		this.conteneur = new JPanel();
		this.setContentPane(conteneur);
		this.panneauMenu = new PanneauAccueil(dim);
		
		this.conteneur.add(panneauMenu);

		this.setJMenuBar(menu);
	}
}