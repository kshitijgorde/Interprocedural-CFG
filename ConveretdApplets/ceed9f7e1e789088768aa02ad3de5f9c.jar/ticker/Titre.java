// 
// Decompiled by Procyon v0.5.30
// 

package ticker;

public class Titre
{
    String nom;
    float valeur;
    String valeurstr;
    float exValeur;
    
    public Titre(final String nom, final float valeur, final float exValeur) {
        this.nom = nom;
        this.valeur = valeur;
        this.exValeur = exValeur;
    }
    
    public String getNom() {
        return this.nom;
    }
    
    public String getID() {
        return this.getNom();
    }
    
    public float getValeur() {
        return this.valeur;
    }
    
    public String getValeurStr() {
        return this.valeurstr;
    }
    
    public float getExValeur() {
        return this.exValeur;
    }
    
    public void setValeur(final float valeur) {
        this.valeur = valeur;
    }
    
    public void setValeurStr(final String valeurstr) {
        this.valeurstr = valeurstr;
    }
    
    public void setExValeur(final float exValeur) {
        this.exValeur = exValeur;
    }
    
    public char getEtat() {
        if (this.getValeur() > this.getExValeur()) {
            return '+';
        }
        if (this.getValeur() < this.getExValeur()) {
            return '-';
        }
        return '=';
    }
    
    public String[] getPourcentage() {
        final String[] array = new String[3];
        if (this.getValeur() > this.getExValeur()) {
            array[0] = "+";
        }
        else {
            if (this.getValeur() >= this.getExValeur()) {
                array[0] = "unch";
                return array;
            }
            array[0] = "-";
        }
        final int abs = Math.abs((int)(100000.0f * ((this.getValeur() - this.getExValeur()) / this.getExValeur())));
        int n;
        if (abs % 10 > 5) {
            n = abs / 10 + 1;
        }
        else {
            n = abs / 10;
        }
        array[1] = n / 100 + "";
        if (n % 100 < 10) {
            array[2] = "0" + n % 100;
        }
        else {
            array[2] = "" + n % 100;
        }
        return array;
    }
}
