import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class menu
{
    public Vector vecteur;
    public int y_item_du_haut;
    public int numero_menu;
    public int occurence;
    boolean menu_existant;
    public int item_selection;
    
    menu(final int numero_menu, final int occurence, final int n, final String s, final String s2, final String s3, final String s4) {
        this.vecteur = new Vector();
        this.menu_existant = true;
        this.item_selection = -1;
        this.numero_menu = numero_menu;
        this.occurence = occurence;
        this.vecteur.addElement(new item(n, s, s2));
        this.vecteur.addElement(new item(3 * n + quickmenu.hauteur_item, s3, s4));
    }
    
    menu(final int n, final int n2, final int n3, final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        this(n, n2, n3, s, s2, s3, s4);
        this.vecteur.addElement(new item(5 * n3 + 2 * quickmenu.hauteur_item, s5, s6));
    }
    
    menu(final int n, final int n2, final int n3, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8) {
        this(n, n2, n3, s, s2, s3, s4, s5, s6);
        this.vecteur.addElement(new item(7 * n3 + 3 * quickmenu.hauteur_item, s7, s8));
    }
    
    menu(final int n, final int n2, final int n3, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10) {
        this(n, n2, n3, s, s2, s3, s4, s5, s6, s7, s8);
        this.vecteur.addElement(new item(9 * n3 + 4 * quickmenu.hauteur_item, s9, s10));
    }
    
    menu(final int n, final int n2, final int n3, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10, final String s11, final String s12) {
        this(n, n2, n3, s, s2, s3, s4, s5, s6, s7, s8, s9, s10);
        this.vecteur.addElement(new item(11 * n3 + 5 * quickmenu.hauteur_item, s11, s12));
    }
    
    menu(final int n, final int n2, final int n3, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10, final String s11, final String s12, final String s13, final String s14) {
        this(n, n2, n3, s, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12);
        this.vecteur.addElement(new item(13 * n3 + 6 * quickmenu.hauteur_item, s13, s14));
    }
    
    menu(final int n, final int n2, final int n3, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10, final String s11, final String s12, final String s13, final String s14, final String s15, final String s16) {
        this(n, n2, n3, s, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14);
        this.vecteur.addElement(new item(15 * n3 + 7 * quickmenu.hauteur_item, s15, s16));
    }
    
    menu(final int n, final int n2, final int n3, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10, final String s11, final String s12, final String s13, final String s14, final String s15, final String s16, final String s17, final String s18) {
        this(n, n2, n3, s, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14, s15, s16);
        this.vecteur.addElement(new item(17 * n3 + 8 * quickmenu.hauteur_item, s17, s18));
    }
    
    menu(final int numero_menu, final int occurence, final boolean b) {
        this.vecteur = new Vector();
        this.menu_existant = true;
        this.item_selection = -1;
        this.numero_menu = numero_menu;
        this.occurence = occurence;
        this.menu_existant = false;
    }
}
