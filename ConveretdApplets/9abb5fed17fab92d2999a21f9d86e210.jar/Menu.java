import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Rectangle;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Menu extends Applet
{
    Rectangle[] menu;
    Rectangle[] smenu;
    String[] text_smenu;
    String[] URLs_menu;
    String[] Targets_menu;
    String[] URLs;
    String[] Targets;
    String[] Aides;
    int largeur;
    int hauteur;
    int nb_menus;
    int nb_smenus;
    int menu_cour;
    int smenu_cour;
    Font police;
    FontMetrics fpolice;
    boolean eff;
    int couleur_police_R;
    int couleur_police_V;
    int couleur_police_B;
    int couleur_police_link_R;
    int couleur_police_link_V;
    int couleur_police_link_B;
    int couleur_menu_R;
    int couleur_menu_V;
    int couleur_menu_B;
    int couleur_menu_bordGH_R;
    int couleur_menu_bordGH_V;
    int couleur_menu_bordGH_B;
    int couleur_menu_bordBD_R;
    int couleur_menu_bordBD_V;
    int couleur_menu_bordBD_B;
    int couleur_menu_tour_sm_R;
    int couleur_menu_tour_sm_V;
    int couleur_menu_tour_sm_B;
    int marge_sous_menus;
    String pos_text;
    int marge_menus;
    String pos_text_m;
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.getAppletContext().showStatus("");
        this.menu_cour = -1;
        this.smenu_cour = -1;
        int y = 0;
        for (int i = 0; i < this.nb_menus; ++i) {
            this.menu[i].y = y;
            y += this.hauteur;
        }
        this.smenu = new Rectangle[0];
        this.eff = true;
        this.repaint();
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.eff) {
            graphics.clearRect(0, 0, this.getSize().width, this.getSize().height);
            this.eff = false;
        }
        for (int i = 0; i < this.nb_menus; ++i) {
            graphics.setColor(new Color(this.couleur_menu_R, this.couleur_menu_V, this.couleur_menu_B));
            graphics.fillRect(this.menu[i].x, this.menu[i].y, this.largeur, this.hauteur);
            graphics.setColor(new Color(this.couleur_menu_bordGH_R, this.couleur_menu_bordGH_V, this.couleur_menu_bordGH_B));
            graphics.drawLine(this.menu[i].x, this.menu[i].y, this.menu[i].x + this.largeur, this.menu[i].y);
            graphics.drawLine(this.menu[i].x, this.menu[i].y, this.menu[i].x, this.menu[i].y + this.hauteur - 1);
            graphics.setColor(new Color(this.couleur_menu_bordBD_R, this.couleur_menu_bordBD_V, this.couleur_menu_bordBD_B));
            graphics.drawLine(this.menu[i].x + 1, this.menu[i].y + this.hauteur - 1, this.menu[i].x + this.largeur + 1, this.menu[i].y + this.hauteur - 1);
            graphics.drawLine(this.menu[i].x + this.largeur - 1, this.menu[i].y, this.menu[i].x + this.largeur - 1, this.menu[i].y + this.hauteur - 1);
            graphics.setColor(new Color(this.couleur_police_R, this.couleur_police_V, this.couleur_police_B));
            graphics.setFont(this.police);
            if (this.pos_text_m.compareTo("M") == 0) {
                this.marge_menus = (this.largeur - this.fpolice.stringWidth(this.getParameter("menu" + (i + 1)))) / 2;
            }
            if (this.pos_text_m.compareTo("R") == 0) {
                this.marge_menus = this.largeur - this.fpolice.stringWidth(this.getParameter("menu" + (i + 1))) - 2;
            }
            graphics.drawString(this.getParameter("menu" + (i + 1)), this.marge_menus, this.menu[i].y + this.fpolice.getHeight());
            if (i == this.menu_cour) {
                graphics.setColor(new Color(this.couleur_police_link_R, this.couleur_police_link_V, this.couleur_police_link_B));
                graphics.setFont(this.police);
                if (this.pos_text_m.compareTo("M") == 0) {
                    this.marge_menus = (this.largeur - this.fpolice.stringWidth(this.getParameter("menu" + (i + 1)))) / 2;
                }
                if (this.pos_text_m.compareTo("R") == 0) {
                    this.marge_menus = this.largeur - this.fpolice.stringWidth(this.getParameter("menu" + (i + 1))) - 2;
                }
                graphics.drawString(this.getParameter("menu" + (i + 1)), this.marge_menus, this.menu[i].y + this.fpolice.getHeight());
                graphics.setColor(new Color(this.couleur_menu_bordBD_R, this.couleur_menu_bordBD_V, this.couleur_menu_bordBD_B));
                graphics.drawLine(this.menu[i].x, this.menu[i].y, this.menu[i].x + this.largeur, this.menu[i].y);
                graphics.drawLine(this.menu[i].x, this.menu[i].y, this.menu[i].x, this.menu[i].y + this.hauteur - 2);
                graphics.setColor(new Color(this.couleur_menu_bordGH_R, this.couleur_menu_bordGH_V, this.couleur_menu_bordGH_B));
                graphics.drawLine(this.menu[i].x + 1, this.menu[i].y + this.hauteur - 2, this.menu[i].x + this.largeur + 1, this.menu[i].y + this.hauteur - 2);
                graphics.drawLine(this.menu[i].x + this.largeur - 1, this.menu[i].y, this.menu[i].x + this.largeur - 1, this.menu[i].y + this.hauteur - 2);
                int n;
                for (n = 1; this.getParameter("smenu" + (i + 1) + "_" + n) != null; ++n) {}
                this.smenu = new Rectangle[n - 1];
                this.text_smenu = new String[n - 1];
                this.URLs = new String[n - 1];
                this.Targets = new String[n - 1];
                this.Aides = new String[n - 1];
                this.nb_smenus = n - 1;
                int hauteur = this.hauteur;
                for (int j = 0; j < n - 1; ++j) {
                    this.smenu[j] = new Rectangle(this.menu[i].x, this.menu[i].y + hauteur, this.largeur, this.hauteur);
                    this.text_smenu[j] = new String(this.getParameter("smenu" + (i + 1) + "_" + (j + 1)));
                    graphics.setColor(new Color(this.couleur_menu_tour_sm_R, this.couleur_menu_tour_sm_V, this.couleur_menu_tour_sm_B));
                    this.URLs[j] = new String(this.getParameter("smenu" + (i + 1) + "_" + (j + 1) + "_URL"));
                    this.Targets[j] = new String(this.getParameter("smenu" + (i + 1) + "_" + (j + 1) + "_TARGET"));
                    this.Aides[j] = new String(this.getParameter("smenu" + (i + 1) + "_" + (j + 1) + "_AIDE"));
                    graphics.drawRect(this.smenu[j].x, this.smenu[j].y - 1, this.largeur - 1, this.hauteur);
                    graphics.setColor(new Color(this.couleur_police_R, this.couleur_police_V, this.couleur_police_B));
                    graphics.setFont(this.police);
                    if (this.pos_text.compareTo("M") == 0) {
                        this.marge_sous_menus = (this.largeur - this.fpolice.stringWidth(this.text_smenu[j])) / 2;
                    }
                    if (this.pos_text.compareTo("R") == 0) {
                        this.marge_sous_menus = this.largeur - this.fpolice.stringWidth(this.text_smenu[j]) - 2;
                    }
                    graphics.drawString(this.text_smenu[j], this.marge_sous_menus, this.smenu[j].y + this.fpolice.getHeight());
                    if (j == this.smenu_cour) {
                        graphics.setColor(new Color(this.couleur_police_link_R, this.couleur_police_link_V, this.couleur_police_link_B));
                        graphics.setFont(this.police);
                        if (this.pos_text.compareTo("M") == 0) {
                            this.marge_sous_menus = (this.largeur - this.fpolice.stringWidth(this.text_smenu[j])) / 2;
                        }
                        if (this.pos_text.compareTo("R") == 0) {
                            this.marge_sous_menus = this.largeur - this.fpolice.stringWidth(this.text_smenu[j]) - 2;
                        }
                        graphics.drawString(this.text_smenu[j], this.marge_sous_menus, this.smenu[j].y + this.fpolice.getHeight());
                        this.getAppletContext().showStatus(this.Aides[j]);
                    }
                    hauteur += this.hauteur;
                }
                int y = this.menu[i].y + this.hauteur + this.hauteur * (n - 1);
                for (int k = i + 1; k < this.nb_menus; ++k) {
                    this.menu[k].y = y;
                    y += this.hauteur;
                }
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public String getAppletInfo() {
        return "Programming\tby Samuel Pignon for EDI-TECHNOLOGIES, mail-me at samos@informaticien.net";
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        try {
            if (this.smenu_cour != -1) {
                this.getAppletContext().showDocument(new URL(this.URLs[this.smenu_cour]), this.Targets[this.smenu_cour]);
            }
            else if (this.menu_cour != -1) {
                this.getAppletContext().showDocument(new URL(this.URLs_menu[this.menu_cour]), this.Targets_menu[this.menu_cour]);
            }
        }
        catch (Exception ex) {}
        return true;
    }
    
    public void init() {
        final String parameter = this.getParameter("Type_police");
        if (parameter.compareTo("BOLD") == 0) {
            this.police = new Font(this.getParameter("Police"), 1, Integer.parseInt(this.getParameter("Taille")));
        }
        if (parameter.compareTo("PLAIN") == 0) {
            this.police = new Font(this.getParameter("Police"), 0, Integer.parseInt(this.getParameter("Taille")));
        }
        this.fpolice = this.getFontMetrics(this.police);
        this.pos_text_m = "";
        if (this.getParameter("marge_menus").compareTo("LEFT") == 0) {
            this.marge_menus = 2;
        }
        else if (this.getParameter("marge_menus").compareTo("MIDDLE") == 0) {
            this.pos_text_m = "M";
        }
        else if (this.getParameter("marge_menus").compareTo("RIGHT") == 0) {
            this.pos_text_m = "R";
        }
        else {
            this.marge_menus = Integer.parseInt(this.getParameter("marge_menus"));
        }
        this.pos_text = "";
        if (this.getParameter("marge_sous_menus").compareTo("LEFT") == 0) {
            this.marge_sous_menus = 2;
        }
        else if (this.getParameter("marge_sous_menus").compareTo("MIDDLE") == 0) {
            this.pos_text = "M";
        }
        else if (this.getParameter("marge_sous_menus").compareTo("RIGHT") == 0) {
            this.pos_text = "R";
        }
        else {
            this.marge_sous_menus = Integer.parseInt(this.getParameter("marge_sous_menus"));
        }
        this.couleur_police_R = Integer.parseInt(this.getParameter("couleur_police_R"));
        this.couleur_police_V = Integer.parseInt(this.getParameter("couleur_police_V"));
        this.couleur_police_B = Integer.parseInt(this.getParameter("couleur_police_B"));
        this.couleur_police_link_R = Integer.parseInt(this.getParameter("couleur_police_link_R"));
        this.couleur_police_link_V = Integer.parseInt(this.getParameter("couleur_police_link_V"));
        this.couleur_police_link_B = Integer.parseInt(this.getParameter("couleur_police_link_B"));
        this.couleur_menu_R = Integer.parseInt(this.getParameter("couleur_menu_R"));
        this.couleur_menu_V = Integer.parseInt(this.getParameter("couleur_menu_V"));
        this.couleur_menu_B = Integer.parseInt(this.getParameter("couleur_menu_B"));
        this.couleur_menu_bordGH_R = Integer.parseInt(this.getParameter("couleur_menu_bordGH_R"));
        this.couleur_menu_bordGH_V = Integer.parseInt(this.getParameter("couleur_menu_bordGH_V"));
        this.couleur_menu_bordGH_B = Integer.parseInt(this.getParameter("couleur_menu_bordGH_B"));
        this.couleur_menu_bordBD_R = Integer.parseInt(this.getParameter("couleur_menu_bordBD_R"));
        this.couleur_menu_bordBD_V = Integer.parseInt(this.getParameter("couleur_menu_bordBD_V"));
        this.couleur_menu_bordBD_B = Integer.parseInt(this.getParameter("couleur_menu_bordBD_B"));
        this.couleur_menu_tour_sm_R = Integer.parseInt(this.getParameter("couleur_menu_tour_sm_R"));
        this.couleur_menu_tour_sm_V = Integer.parseInt(this.getParameter("couleur_menu_tour_sm_V"));
        this.couleur_menu_tour_sm_B = Integer.parseInt(this.getParameter("couleur_menu_tour_sm_B"));
        this.nb_menus = Integer.parseInt(this.getParameter("nb_menus"));
        this.menu = new Rectangle[this.nb_menus];
        this.hauteur = Integer.parseInt(this.getParameter("hauteur"));
        this.largeur = Integer.parseInt(this.getParameter("largeur"));
        this.Targets_menu = new String[this.nb_menus];
        this.URLs_menu = new String[this.nb_menus];
        int n = 0;
        for (int i = 0; i < this.nb_menus; ++i) {
            this.menu[i] = new Rectangle(0, n, this.largeur, this.hauteur);
            this.URLs_menu[i] = new String(this.getParameter("menu" + (i + 1) + "_URL"));
            this.Targets_menu[i] = new String(this.getParameter("menu" + (i + 1) + "_TARGET"));
            n += this.hauteur;
        }
        this.menu_cour = -1;
        this.smenu_cour = -1;
        this.eff = true;
        this.setBackground(new Color(Integer.parseInt(this.getParameter("couleur_fond_R")), Integer.parseInt(this.getParameter("couleur_fond_V")), Integer.parseInt(this.getParameter("couleur_fond_B"))));
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        int i = 0;
        while (i < this.nb_menus) {
            if (n2 >= this.menu[i].y & n2 <= this.menu[i].y + this.hauteur) {
                if (this.smenu_cour != -1) {
                    this.smenu_cour = -1;
                }
                if (i != this.menu_cour) {
                    this.menu_cour = i;
                    int y = 0;
                    for (int j = 0; j < this.nb_menus; ++j) {
                        this.menu[j].y = y;
                        y += this.hauteur;
                    }
                    this.eff = true;
                    this.repaint();
                    break;
                }
                break;
            }
            else {
                ++i;
            }
        }
        int k = 0;
        while (k < this.nb_smenus) {
            if (n2 >= this.smenu[k].y & n2 <= this.smenu[k].y + this.hauteur) {
                if (k != this.smenu_cour) {
                    this.smenu_cour = k;
                    int y2 = 0;
                    for (int l = 0; l < this.nb_smenus; ++l) {
                        this.smenu[l].y = y2;
                        y2 += this.hauteur;
                    }
                    this.repaint();
                    break;
                }
                break;
            }
            else {
                ++k;
            }
        }
        return true;
    }
}
