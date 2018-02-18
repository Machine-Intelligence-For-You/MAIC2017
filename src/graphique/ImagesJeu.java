package graphique;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ImagesJeu {


	public final static int VIDE = 0;
	public final static int UNE_BILLE = 1;
	public final static int DEUX_BILLES = 2;
	public final static int TROIS_BILLES = 3;
	public final static int QUATRE_BILLES = 4;
	public final static int CINQ_BILLES = 5;
	public final static int SIX_BILLES = 6;
	public final static int SEPT_BILLES = 7;
	public final static int PLEIN = 8;
	public final static int ARRIERE_PLAN = 9;
	public final static int PLATEAU = 10;
	public final static int ROND = 11;
	public final static int ROND_RED = 12;
	public final static int MAIN1 = 13;
	public final static int MAIN2 = 14;
	/*public final static int SELECTIONNEUR_1 = 12;
	public final static int SELECTIONNEUR_2 = 13;
	public final static int SELECTIONNEUR_3 = 14;
	public final static int SELECTIONNEUR_4 = 15;*/
	private ArrayList<Image> images;
	private Parametres parametres;

	public ImagesJeu(Parametres parametres){
		this.parametres = parametres;
		images = new ArrayList<Image>();
		try {
			Image fond = ImageIO.read(new File("images/arriereplan/"+parametres.theme()));
			Image plateau = ImageIO.read(new File("images/plateau/plateau.jpg"));
			plateau = Toolkit.getDefaultToolkit().createImage (new FilteredImageSource (plateau.getSource (), new ImageTransparente()));
			Image caseVide = ImageIO.read(new File("images/plateau/Vide.jpg"));
			caseVide = Toolkit.getDefaultToolkit().createImage (new FilteredImageSource (caseVide.getSource (), new ImageTransparente()));
			Image caseUneBille = ImageIO.read(new File("images/plateau/UneBille.jpg"));
			caseUneBille = Toolkit.getDefaultToolkit().createImage (new FilteredImageSource (caseUneBille.getSource (), new ImageTransparente()));
			Image caseDeuxBilles = ImageIO.read(new File("images/plateau/DeuxBilles.jpg"));
			caseDeuxBilles = Toolkit.getDefaultToolkit().createImage (new FilteredImageSource (caseDeuxBilles.getSource (), new ImageTransparente()));
			Image caseTroisBilles = ImageIO.read(new File("images/plateau/TroisBilles.jpg"));
			caseTroisBilles = Toolkit.getDefaultToolkit().createImage (new FilteredImageSource (caseTroisBilles.getSource (), new ImageTransparente()));
			Image caseQuatreBilles = ImageIO.read(new File("images/plateau/QuatreBilles.jpg"));
			caseQuatreBilles = Toolkit.getDefaultToolkit().createImage (new FilteredImageSource (caseQuatreBilles.getSource (), new ImageTransparente()));
			Image caseCinqBilles = ImageIO.read(new File("images/plateau/CinqBilles.jpg"));
			caseCinqBilles = Toolkit.getDefaultToolkit().createImage (new FilteredImageSource (caseCinqBilles.getSource (), new ImageTransparente()));
			Image caseSixBilles = ImageIO.read(new File("images/plateau/SixBilles.jpg"));
			caseSixBilles = Toolkit.getDefaultToolkit().createImage (new FilteredImageSource (caseSixBilles.getSource (), new ImageTransparente()));
			Image caseSeptBilles = ImageIO.read(new File("images/plateau/SeptBilles.jpg"));
			caseSeptBilles = Toolkit.getDefaultToolkit().createImage (new FilteredImageSource (caseSeptBilles.getSource (), new ImageTransparente()));
			Image casePlein = ImageIO.read(new File("images/plateau/Plein.jpg"));
			casePlein = Toolkit.getDefaultToolkit().createImage (new FilteredImageSource (casePlein.getSource (), new ImageTransparente()));
			Image rond = ImageIO.read(new File("images/selectionneur/rond.png"));
			rond = Toolkit.getDefaultToolkit().createImage (new FilteredImageSource (rond.getSource (), new ImageTransparente()));
			Image rond_red = ImageIO.read(new File("images/selectionneur/rond_red.png"));
			rond_red = Toolkit.getDefaultToolkit().createImage (new FilteredImageSource (rond_red.getSource (), new ImageTransparente()));

			Image main1 = ImageIO.read(new File("images/selectionneur/main2.png"));
			main1 = Toolkit.getDefaultToolkit().createImage (main1.getSource ());
			Image main2 = ImageIO.read(new File("images/selectionneur/main1.png"));
			main2 = Toolkit.getDefaultToolkit().createImage (main2.getSource ());


			images.add(caseVide);
			images.add(caseUneBille);
			images.add(caseDeuxBilles);
			images.add(caseTroisBilles);
			images.add(caseQuatreBilles);
			images.add(caseCinqBilles);
			images.add(caseSixBilles);
			images.add(caseSeptBilles);
			images.add(casePlein);
			images.add(fond);
			images.add(plateau);
			images.add(rond);
			images.add(rond_red);
			images.add(main1);
			images.add(main2);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Image imageN(int n){
		return images.get(n);
	}

	public void changeArrierePlan(){

		try {
			images.remove(ARRIERE_PLAN);
			Image fond = ImageIO.read(new File("images/arriereplan/"+parametres.theme()));
			images.add(ARRIERE_PLAN, fond);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}