import java.awt.Polygon;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Plan implements Cloneable
{
    String type;
    int x1;
    int y1;
    int largeur;
    int hauteur;
    int epaisseur;
    Color couleurContour;
    Color couleurRemplissage;
    boolean contourTransp;
    boolean remplissageTransp;
    String texte;
    String police;
    int style;
    int taille;
    Polygon polygone;
    public static int nombrePlan;
    
    Plan(final String type) {
        this.type = type;
    }
    
    Plan(final String type, final int x1, final int y1, final int largeur, final int hauteur, final int epaisseur, final Color couleurContour, final Color couleurRemplissage, final boolean contourTransp, final boolean remplissageTransp) {
        this.type = type;
        this.x1 = x1;
        this.y1 = y1;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.epaisseur = epaisseur;
        this.couleurContour = couleurContour;
        this.couleurRemplissage = couleurRemplissage;
        this.contourTransp = contourTransp;
        this.remplissageTransp = remplissageTransp;
    }
    
    Plan(final String type, final int x1, final int y1, final int epaisseur, final Color couleurRemplissage, final boolean remplissageTransp) {
        this.type = type;
        this.x1 = x1;
        this.y1 = y1;
        this.epaisseur = epaisseur;
        this.couleurRemplissage = couleurRemplissage;
        this.remplissageTransp = remplissageTransp;
    }
    
    Plan(final String type, final int x1, final int y1, final int largeur, final int hauteur, final int epaisseur, final Color couleurContour, final boolean contourTransp) {
        this.type = type;
        this.x1 = x1;
        this.y1 = y1;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.epaisseur = epaisseur;
        this.couleurContour = couleurContour;
        this.contourTransp = contourTransp;
    }
    
    Plan(final String type, final int x1, final int y1, final int largeur, final int hauteur, final String texte, final String police, final int style, final int taille, final Color couleurRemplissage, final boolean remplissageTransp) {
        this.type = type;
        this.x1 = x1;
        this.y1 = y1;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.texte = texte;
        this.police = police;
        this.style = style;
        this.taille = taille;
        this.couleurRemplissage = couleurRemplissage;
        this.remplissageTransp = remplissageTransp;
    }
    
    Plan(final String type, final int x1, final int y1, final int largeur, final int hauteur, final Polygon polygone, final Color couleurContour, final Color couleurRemplissage, final boolean contourTransp, final boolean remplissageTransp) {
        this.type = type;
        this.x1 = x1;
        this.y1 = y1;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.polygone = polygone;
        this.couleurContour = couleurContour;
        this.couleurRemplissage = couleurRemplissage;
        this.contourTransp = contourTransp;
        this.remplissageTransp = remplissageTransp;
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError(ex.toString());
        }
    }
    
    public String toString() {
        final String string = "" + this.type + "|" + this.x1 + "|" + this.y1 + "|" + this.largeur + "|" + this.hauteur + "|" + this.epaisseur + "|";
        String s;
        if (this.couleurContour != null) {
            s = string + this.couleurContour.getRed() + "," + this.couleurContour.getGreen() + "," + this.couleurContour.getBlue() + "|";
        }
        else {
            s = string + "null|";
        }
        String s2;
        if (this.couleurRemplissage != null) {
            s2 = s + this.couleurRemplissage.getRed() + "," + this.couleurRemplissage.getGreen() + "," + this.couleurRemplissage.getBlue() + "|";
        }
        else {
            s2 = s + "null|";
        }
        String s3 = s2 + this.contourTransp + "|" + this.remplissageTransp + "|" + this.texte + "|" + this.police + "|" + this.style + "|" + this.taille + "|";
        try {
            if (this.polygone != null) {
                final int npoints = this.polygone.npoints;
                for (int i = 0; i < npoints - 1; ++i) {
                    s3 = s3 + this.polygone.xpoints[i] + ",";
                }
                s3 = s3 + this.polygone.xpoints[npoints - 1] + "-";
                for (int j = 0; j < npoints - 1; ++j) {
                    s3 = s3 + this.polygone.ypoints[j] + ",";
                }
                s3 += this.polygone.ypoints[npoints - 1];
            }
            else {
                s3 += "null";
            }
        }
        catch (Exception ex) {
            s3 += "null";
        }
        return s3 + "\n";
    }
}
