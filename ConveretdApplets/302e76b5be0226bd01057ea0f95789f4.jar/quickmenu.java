import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Font;
import java.util.Enumeration;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class quickmenu extends Applet
{
    public static String afficher;
    private Vector rang;
    private int hauteur_applet;
    private int largeur_applet;
    public static int hauteur_item;
    public int largeur_item;
    private Color couleur_lib;
    private Color couleur_lib2;
    private Color couleur_fond;
    item item_choisi_menu2;
    item item_choisi_menu1;
    item item_choisi_menu3;
    String frame;
    private menu menu1;
    private menu menu21;
    private menu menu22;
    private menu menu201;
    private menu menu301;
    private Image image;
    private Graphics g;
    private int nb_item_menu;
    public int decalage_menu1;
    public int decalage_menu2;
    public int decalage_menu3;
    private int menu_actif_occurence;
    private int[] indice;
    int j;
    
    static {
        quickmenu.afficher = "";
    }
    
    public quickmenu() {
        this.rang = new Vector();
        this.item_choisi_menu2 = new item();
        this.item_choisi_menu1 = new item();
        this.item_choisi_menu3 = new item();
        this.frame = null;
        this.menu_actif_occurence = 1;
        this.indice = new int[5];
    }
    
    void HTML(final String s) {
        URL url = null;
        try {
            url = new URL("http://www.web-media.fr/applets/quickmenu/demo.html");
            System.out.println(String.valueOf(s));
        }
        catch (MalformedURLException ex) {
            System.out.println("Mauvaise URL");
        }
        this.getAppletContext().showDocument(url, this.frame);
    }
    
    void creer_image() {
        this.g.setColor(this.couleur_fond);
        this.g.fillRect(0, 0, this.largeur_applet, this.hauteur_applet);
        this.g.setColor(this.couleur_lib2);
        int n = 0;
        final Enumeration<item> elements = this.rang.elementAt(this.indice[1]).vecteur.elements();
        while (elements.hasMoreElements()) {
            ++n;
            final item item = elements.nextElement();
            if (this.rang.elementAt(this.indice[1]).item_selection == n) {
                this.g.setColor(this.couleur_lib);
            }
            this.g.drawString(item.lib, this.decalage_menu1, item.y + 15);
            this.g.setColor(this.couleur_lib2);
        }
        int n2 = 0;
        if (this.indice[2] != 0) {
            int n3 = -1;
            int n4 = 0;
            int n5 = 0;
            if (this.rang.elementAt(this.indice[2]).menu_existant) {
                final Enumeration<item> elements2 = this.rang.elementAt(this.indice[2]).vecteur.elements();
                while (elements2.hasMoreElements()) {
                    ++n3;
                    ++n2;
                    final item item2 = elements2.nextElement();
                    if (n3 == 0) {
                        n4 = item2.y + 1;
                    }
                    if (this.rang.elementAt(this.indice[2]).item_selection == n2) {
                        this.g.setColor(this.couleur_lib);
                    }
                    this.g.drawString(String.valueOf(item2.lib), this.decalage_menu2, item2.y + 15);
                    this.g.setColor(this.couleur_lib2);
                    n5 = item2.y + 20;
                }
                if (n3 != -1) {
                    this.g.drawLine(this.decalage_menu2 - 10, n4, this.decalage_menu2 - 10, n5);
                    this.g.drawLine(this.decalage_menu2 - 10, n4, this.decalage_menu2 + 15, n4);
                    this.g.drawLine(this.decalage_menu2 - 10, n5, this.decalage_menu2 + 15, n5);
                }
            }
        }
        int n6 = 0;
        if (this.indice[3] != 0) {
            int n7 = -1;
            int n8 = 0;
            int n9 = 0;
            if (this.rang.elementAt(this.indice[3]).menu_existant) {
                final Enumeration<item> elements3 = this.rang.elementAt(this.indice[3]).vecteur.elements();
                while (elements3.hasMoreElements()) {
                    ++n7;
                    ++n6;
                    final item item3 = elements3.nextElement();
                    if (n7 == 0) {
                        n8 = item3.y + 1;
                    }
                    if (this.rang.elementAt(this.indice[3]).item_selection == n6) {
                        this.g.setColor(this.couleur_lib);
                    }
                    this.g.drawString(String.valueOf(item3.lib), this.decalage_menu3, item3.y + 15);
                    this.g.setColor(this.couleur_lib2);
                    n9 = item3.y + 20;
                }
                if (n7 != -1) {
                    this.g.drawLine(this.decalage_menu3 - 10, n8, this.decalage_menu3 - 10, n9);
                    this.g.drawLine(this.decalage_menu3 - 10, n8, this.decalage_menu3 + 15, n8);
                    this.g.drawLine(this.decalage_menu3 - 10, n9, this.decalage_menu3 + 15, n9);
                }
            }
        }
        this.repaint();
    }
    
    public void init() {
        this.hauteur_applet = this.size().height;
        this.largeur_applet = this.size().width;
        this.couleur_lib = Color.lightGray;
        this.couleur_lib2 = Color.black;
        final String parameter = this.getParameter("bgcolor");
        final String parameter2 = this.getParameter("text_color_on");
        final String parameter3 = this.getParameter("text_color_off");
        String parameter4 = this.getParameter("font");
        final String parameter5 = this.getParameter("font_size");
        int int1 = 10;
        if (this.getParameter("item_height") != null) {
            quickmenu.hauteur_item = Integer.parseInt(this.getParameter("item_height"));
        }
        else {
            quickmenu.hauteur_item = 20;
        }
        if (this.getParameter("item_width") != null) {
            this.largeur_item = Integer.parseInt(this.getParameter("item_width"));
        }
        else {
            this.largeur_item = 100;
        }
        this.frame = this.getParameter("frame");
        if (this.frame == null) {
            this.frame = "_parent";
        }
        if (parameter != null) {
            this.couleur_fond = new Color(Integer.parseInt(parameter, 16));
        }
        else {
            this.couleur_fond = Color.white;
        }
        if (parameter5 != null) {
            int1 = Integer.parseInt(parameter5);
        }
        if (parameter2 != null) {
            this.couleur_lib = new Color(Integer.parseInt(parameter2, 16));
        }
        else {
            this.couleur_lib = Color.white;
        }
        if (parameter3 != null) {
            this.couleur_lib2 = new Color(Integer.parseInt(parameter3, 16));
        }
        else {
            this.couleur_lib2 = Color.lightGray;
        }
        if (parameter4 == null) {
            parameter4 = "Blurlight";
        }
        if (parameter4.equalsIgnoreCase("p367tout")) {
            System.out.println("Copyright C-E B FRANCE");
        }
        this.setFont(new Font(parameter4, 1, int1));
        this.decalage_menu1 = 10;
        this.decalage_menu2 = 20 + this.largeur_item + 20;
        this.decalage_menu3 = 30 + 2 * this.largeur_item + 40;
        int n = 0;
        this.image = this.createImage(this.largeur_applet, this.hauteur_applet);
        (this.g = this.image.getGraphics()).setColor(this.couleur_lib2);
        this.indice[1] = 0;
        this.indice[2] = 0;
        this.indice[3] = 0;
        int n2 = 0;
        int i = -1;
        final int n3 = 0;
        String[] array = new String[25];
        final String parameter6 = this.getParameter("menus");
        String s = parameter6.substring(n3, parameter6.indexOf("|", n3));
        int j = parameter6.indexOf("|", 0);
        while (j != -1) {
            array = new String[25];
            int n4 = 0;
            i = -1;
            int k = s.indexOf(";", n4);
            while (k != -1) {
                ++i;
                array[i] = s.substring(n4, k);
                n4 = k + 1;
                k = s.indexOf(";", k + 1);
                if (k == -1) {
                    ++i;
                    array[i] = s.substring(n4, s.length());
                }
            }
            if (array[1].equalsIgnoreCase("no_menu")) {
                i = 2;
                this.rang.addElement(new menu(2, n2, false));
            }
            this.nb_item_menu = i / 2;
            final int n5 = this.hauteur_applet / this.nb_item_menu / 2 - quickmenu.hauteur_item / 2;
            if (i == 4) {
                this.rang.addElement(new menu(Integer.parseInt(array[0]), n2, n5, array[1], array[2], array[3], array[4]));
            }
            if (i == 6) {
                this.rang.addElement(new menu(Integer.parseInt(array[0]), n2, n5, array[1], array[2], array[3], array[4], array[5], array[6]));
            }
            if (i == 8) {
                this.rang.addElement(new menu(Integer.parseInt(array[0]), n2, n5, array[1], array[2], array[3], array[4], array[5], array[6], array[7], array[8]));
            }
            if (i == 10) {
                this.rang.addElement(new menu(Integer.parseInt(array[0]), n2, n5, array[1], array[2], array[3], array[4], array[5], array[6], array[7], array[8], array[9], array[10]));
            }
            if (i == 12) {
                this.rang.addElement(new menu(Integer.parseInt(array[0]), n2, n5, array[1], array[2], array[3], array[4], array[5], array[6], array[7], array[8], array[9], array[10], array[11], array[12]));
            }
            if (i == 14) {
                this.rang.addElement(new menu(Integer.parseInt(array[0]), n2, n5, array[1], array[2], array[3], array[4], array[5], array[6], array[7], array[8], array[9], array[10], array[11], array[12], array[13], array[14]));
            }
            if (i == 16) {
                this.rang.addElement(new menu(Integer.parseInt(array[0]), n2, n5, array[1], array[2], array[3], array[4], array[5], array[6], array[7], array[8], array[9], array[10], array[11], array[12], array[13], array[14], array[15], array[16]));
            }
            if (i == 16) {
                this.rang.addElement(new menu(Integer.parseInt(array[0]), n2, n5, array[1], array[2], array[3], array[4], array[5], array[6], array[7], array[8], array[9], array[10], array[11], array[12], array[13], array[14], array[15], array[16], array[17], array[18]));
            }
            final int n6 = j + 1;
            j = parameter6.indexOf("|", j + 1);
            if (j != -1) {
                s = parameter6.substring(n6, j);
            }
            s = s.trim();
            ++n;
            if (!array[0].equalsIgnoreCase("no_menu") && n > 1) {
                n2 += i / 2;
            }
        }
        while (i < 50) {
            this.rang.addElement(new menu(3, n2, false));
            ++i;
        }
        if (array[0].equalsIgnoreCase("1")) {
            while (i < 50) {
                this.rang.addElement(new menu(2, n2, false));
                ++i;
            }
        }
        this.creer_image();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (n > this.decalage_menu1 && n < this.decalage_menu1 + this.largeur_item && this.rang.elementAt(this.indice[1]).item_selection != -1) {
            this.HTML(this.item_choisi_menu1.url);
        }
        if (n > this.decalage_menu2 && n < this.decalage_menu2 + this.largeur_item && this.rang.elementAt(this.indice[2]).item_selection != -1) {
            this.HTML(this.item_choisi_menu2.url);
        }
        if (n > this.decalage_menu3 && n < this.decalage_menu3 + this.largeur_item && this.rang.elementAt(this.indice[3]).item_selection != -1) {
            this.HTML(this.item_choisi_menu3.url);
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        int item_selection = 0;
        if (n > this.decalage_menu1 && n < this.decalage_menu1 + this.largeur_item) {
            if (this.rang.elementAt(this.indice[2]).item_selection != -1 && this.rang.elementAt(this.indice[2]).item_selection != -1) {
                this.rang.elementAt(this.indice[2]).item_selection = -1;
                this.creer_image();
            }
            final Enumeration<item> elements = this.rang.elementAt(this.indice[1]).vecteur.elements();
            while (elements.hasMoreElements()) {
                ++item_selection;
                final item item_choisi_menu1 = elements.nextElement();
                if (n > this.decalage_menu1 && n2 > item_choisi_menu1.y && n < this.decalage_menu1 + this.largeur_item && n2 < item_choisi_menu1.y + quickmenu.hauteur_item) {
                    this.item_choisi_menu1 = item_choisi_menu1;
                    if (item_choisi_menu1.dedans) {
                        continue;
                    }
                    this.indice[2] = item_selection;
                    this.indice[3] = 0;
                    this.rang.elementAt(this.indice[1]).item_selection = item_selection;
                    item_choisi_menu1.dedans = true;
                    this.creer_image();
                }
                else {
                    item_choisi_menu1.dedans = false;
                    this.rang.elementAt(this.indice[2]).item_selection = -1;
                }
            }
            if (this.item_choisi_menu2.dedans) {
                this.item_choisi_menu2.dedans = false;
            }
        }
        if (n > this.decalage_menu2 && n < this.decalage_menu2 + this.largeur_item) {
            if (this.rang.elementAt(this.indice[3]).item_selection != -1 && this.indice[3] != 0) {
                this.rang.elementAt(this.indice[3]).item_selection = -1;
                this.creer_image();
            }
            int item_selection2 = 0;
            if (this.rang.elementAt(this.indice[2]).menu_existant) {
                if (this.indice[2] != 0) {
                    final Enumeration<item> elements2 = this.rang.elementAt(this.indice[2]).vecteur.elements();
                    while (elements2.hasMoreElements()) {
                        ++item_selection2;
                        final item item_choisi_menu2 = elements2.nextElement();
                        if (n > this.decalage_menu2 && n2 > item_choisi_menu2.y && n < this.decalage_menu2 + this.largeur_item && n2 < item_choisi_menu2.y + quickmenu.hauteur_item) {
                            this.item_choisi_menu2 = item_choisi_menu2;
                            if (item_choisi_menu2.dedans) {
                                continue;
                            }
                            this.indice[3] = 4 + this.rang.elementAt(this.indice[2]).occurence - 1 + item_selection2;
                            this.rang.elementAt(this.indice[2]).item_selection = item_selection2;
                            item_choisi_menu2.dedans = true;
                            this.rang.elementAt(this.indice[3]).item_selection = -1;
                            this.item_choisi_menu3.dedans = false;
                            this.creer_image();
                        }
                        else {
                            item_choisi_menu2.dedans = false;
                        }
                    }
                }
                if (this.item_choisi_menu1.dedans) {
                    this.item_choisi_menu1.dedans = false;
                }
            }
        }
        if (n > this.decalage_menu3 && n < this.decalage_menu3 + this.largeur_item) {
            int item_selection3 = 0;
            if (this.rang.elementAt(this.indice[3]).menu_existant) {
                if (this.indice[3] != 0) {
                    final Enumeration<item> elements3 = this.rang.elementAt(this.indice[3]).vecteur.elements();
                    while (elements3.hasMoreElements()) {
                        ++item_selection3;
                        final item item_choisi_menu3 = elements3.nextElement();
                        if (n > this.decalage_menu3 && n2 > item_choisi_menu3.y && n < this.decalage_menu3 + this.largeur_item && n2 < item_choisi_menu3.y + quickmenu.hauteur_item) {
                            this.item_choisi_menu3 = item_choisi_menu3;
                            if (item_choisi_menu3.dedans) {
                                continue;
                            }
                            this.rang.elementAt(this.indice[3]).item_selection = item_selection3;
                            item_choisi_menu3.dedans = true;
                            if (this.item_choisi_menu2.dedans) {
                                this.item_choisi_menu2.dedans = false;
                            }
                            this.creer_image();
                        }
                        else {
                            item_choisi_menu3.dedans = false;
                        }
                    }
                }
                if (this.item_choisi_menu2.dedans) {
                    this.item_choisi_menu2.dedans = false;
                }
            }
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.image, 0, 0, this);
    }
    
    public void start() {
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
