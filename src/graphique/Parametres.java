package graphique;

public class Parametres {

	private int vitesse;
	private String theme;
	private boolean changementTheme;
	
	public Parametres(){
		super();
		vitesse = 500;
		theme = "art.jpg";
		changementTheme = false;
	}
	
	public void changeVitesse(int n){
		if(n == 1)
			vitesse = 1000;
		else if(n == 2)
			vitesse = 500;
		else
			vitesse = 200;
	}
	
	public void changeTheme(int n){
		if(n == 1)
			theme = "art.jpg";
		else if(n == 2)
			theme = "tapis.jpg";
		changementTheme = true;
	}
	
	public int vitesse(){
		return this.vitesse;
	}
	
	public String theme(){
		return this.theme;
	}
	
	public boolean themeChange(){
		return changementTheme;
	}
	
	
}
