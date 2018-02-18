package graphique;

import java.awt.image.ColorModel;
import java.awt.image.RGBImageFilter;

public class ImageTransparente extends RGBImageFilter {
    
	public ImageTransparente () {
            //Pour dire que notre filtre est indépendant de la position des pixels de l'image.
            canFilterIndexColorModel = true;
    }
    
	public int filterRGB(int x, int y, int rgb) {
        ColorModel cm = ColorModel.getRGBdefault();
            //On récupére le modèle de couleur RGBA (4 octets pour coder une couleur.)
        int alpha = cm.getAlpha(rgb); //Récupération des valeurs rgba des pixels de l'image.
        int rouge = cm.getRed(rgb);
        int vert = cm.getGreen(rgb);
        int bleu = cm.getBlue(rgb);
        //Si la couleur du pixel est blanc, on le rend transparent, sinon, on renvoie les valeurs rgba tel qu'elles sont.
        if (rouge <= 255 && rouge >=220 && vert <= 255 && vert >=220 && bleu <= 255 && bleu >= 220) {
             alpha = 0 & 0xFF;
                     //0 = transparent 255 = opaque.
                     //On renvoie les valeurs RGBA dans un int.
             return  alpha | rouge | vert | bleu;
        } else
                 return rgb;
    }
}

