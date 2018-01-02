import java.awt.Window;
import java.awt.Container;
import java.awt.Event;
import java.awt.Label;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Image;
import java.awt.GridLayout;
import util102.gui.BorderPanel;
import java.awt.MenuItem;
import java.awt.MenuBar;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Menu;
import java.awt.TextField;
import java.awt.Color;
import java.awt.Choice;
import util102.gui.ImageButton;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public final class PaletteOutil extends Frame
{
    ImageButton fleche;
    ImageButton ligne;
    ImageButton rectangle;
    ImageButton rectangle3D1;
    ImageButton rectangle3D0;
    ImageButton point;
    ImageButton texte;
    ImageButton effacer;
    ImageButton ovale;
    ImageButton polygone;
    String bouttonSelectionne;
    String choiceContourPrecedent;
    String choiceRemplissagePrecedent;
    String choiceFondPrecedent;
    Choice couleurContour;
    Choice couleurRemplissage;
    Choice couleurFond;
    Color coulContour;
    Color coulRemplissage;
    Color coulFond;
    TextField epaisseurContour;
    int epaisseur;
    Menu mObj;
    Menu mText;
    Menu mAide;
    PaletteRVB paletteCoulContour;
    PaletteRVB paletteCoulRemplissage;
    PaletteRVB paletteCoulFond;
    PaletteText paletteText;
    FenetreAide fenetreAide;
    boolean premiereOuvertureTexte;
    boolean premiereOuvertureCoulContour;
    boolean premiereOuvertureCoulRempl;
    boolean premiereOuvertureCoulFond;
    JavaDraw parentJavaDraw;
    
    PaletteOutil(final String s, final JavaDraw parentJavaDraw) {
        super(s);
        this.bouttonSelectionne = "fleche";
        this.coulContour = Color.black;
        this.coulRemplissage = Color.white;
        this.coulFond = Color.white;
        this.epaisseur = 1;
        this.premiereOuvertureTexte = true;
        this.premiereOuvertureCoulContour = true;
        this.premiereOuvertureCoulRempl = true;
        this.premiereOuvertureCoulFond = true;
        this.parentJavaDraw = parentJavaDraw;
        this.paletteCoulContour = new PaletteRVB(" Outline", this.parentJavaDraw);
        this.paletteCoulRemplissage = new PaletteRVB(" Fill", this.parentJavaDraw);
        this.paletteCoulFond = new PaletteRVB(" Background", this.parentJavaDraw);
        this.paletteText = new PaletteText(" Text");
        this.fenetreAide = new FenetreAide(" JavaDraw Help");
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout(0, 0));
        final MenuBar menuBar = new MenuBar();
        menuBar.add(this.mObj = new Menu("Objects"));
        if (!this.parentJavaDraw.isApplet) {
            this.mObj.add(new MenuItem("Open..."));
            this.mObj.add(new MenuItem("Save"));
            this.mObj.add(new MenuItem("Save as..."));
            this.mObj.add(new MenuItem("-"));
        }
        this.mObj.add(new MenuItem("To front"));
        this.mObj.add(new MenuItem("Before"));
        this.mObj.add(new MenuItem("After"));
        this.mObj.add(new MenuItem("Duplicate"));
        this.mObj.add(new MenuItem("Delete"));
        this.mObj.add(new MenuItem("Delete all"));
        this.mObj.add(new MenuItem("-"));
        this.mObj.add(new MenuItem("Generate code..."));
        if (!this.parentJavaDraw.isApplet) {
            this.mObj.add(new MenuItem("Save code"));
        }
        this.mObj.add(new MenuItem("-"));
        this.mObj.add(new MenuItem("Quit"));
        menuBar.add(this.mText = new Menu("Text"));
        this.mText.add(new MenuItem("Normal"));
        this.mText.add(new MenuItem("Italic"));
        this.mText.add(new MenuItem("Bold"));
        this.mText.add(new MenuItem("Bold-italic"));
        this.mText.add(new MenuItem("-"));
        this.mText.add(new MenuItem("Courier"));
        this.mText.add(new MenuItem("Helvetica"));
        this.mText.add(new MenuItem("TimesRoman"));
        this.mText.add(new MenuItem("Symbol"));
        this.mText.add(new MenuItem("Dialog"));
        this.mText.add(new MenuItem("-"));
        final Menu menu = new Menu("Size");
        this.mText.add(menu);
        menu.add(new MenuItem("size +1"));
        menu.add(new MenuItem("size -1"));
        menu.add(new MenuItem("-"));
        menu.add(new MenuItem("8"));
        menu.add(new MenuItem("12"));
        menu.add(new MenuItem("18"));
        menu.add(new MenuItem("24"));
        menu.add(new MenuItem("30"));
        menu.add(new MenuItem("40"));
        menu.add(new MenuItem("50"));
        menu.add(new MenuItem("70"));
        menu.add(new MenuItem("100"));
        this.mText.disable();
        menuBar.add(this.mAide = new Menu("Help"));
        this.mAide.add(new MenuItem("A brief description..."));
        this.mAide.add(new MenuItem("-"));
        this.mAide.add(new MenuItem("About..."));
        final BorderPanel borderPanel = new BorderPanel(5, 5, 5, 5, 3);
        ((Container)borderPanel).setLayout(new BorderLayout(5, 5));
        final BorderPanel borderPanel2 = new BorderPanel(5, 5, 5, 5, 7);
        ((Container)borderPanel2).setLayout(new GridLayout(4, 3, 5, 5));
        (this.fleche = new ImageButton(this.parentJavaDraw.arrow, (Image)null, (Image)null)).setSticky(true);
        this.fleche.setLabel("Selection");
        ((Container)borderPanel2).add((Component)this.fleche);
        ((Container)borderPanel2).add(new Canvas());
        ((Container)borderPanel2).add(new Canvas());
        (this.ligne = new ImageButton(this.parentJavaDraw.line, (Image)null, (Image)null)).setSticky(true);
        this.fleche.setLabel("Ligne");
        ((Container)borderPanel2).add((Component)this.ligne);
        (this.rectangle = new ImageButton(this.parentJavaDraw.rect, (Image)null, (Image)null)).setSticky(true);
        this.fleche.setLabel("Rectangle");
        ((Container)borderPanel2).add((Component)this.rectangle);
        (this.ovale = new ImageButton(this.parentJavaDraw.oval, (Image)null, (Image)null)).setSticky(true);
        this.fleche.setLabel("Ovale");
        ((Container)borderPanel2).add((Component)this.ovale);
        (this.rectangle3D1 = new ImageButton(this.parentJavaDraw.rect3D1, (Image)null, (Image)null)).setSticky(true);
        this.fleche.setLabel("Relief");
        ((Container)borderPanel2).add((Component)this.rectangle3D1);
        (this.rectangle3D0 = new ImageButton(this.parentJavaDraw.rect3D0, (Image)null, (Image)null)).setSticky(true);
        this.fleche.setLabel("Creux");
        ((Container)borderPanel2).add((Component)this.rectangle3D0);
        (this.point = new ImageButton(this.parentJavaDraw.point, (Image)null, (Image)null)).setSticky(true);
        this.fleche.setLabel("Point");
        ((Container)borderPanel2).add((Component)this.point);
        (this.polygone = new ImageButton(this.parentJavaDraw.polygon, (Image)null, (Image)null)).setSticky(true);
        this.fleche.setLabel("Polygone");
        ((Container)borderPanel2).add((Component)this.polygone);
        (this.texte = new ImageButton(this.parentJavaDraw.text, (Image)null, (Image)null)).setSticky(true);
        this.fleche.setLabel("Texte");
        ((Container)borderPanel2).add((Component)this.texte);
        (this.effacer = new ImageButton(this.parentJavaDraw.delete, (Image)null, (Image)null)).setSticky(true);
        this.fleche.setLabel("Effacer");
        ((Container)borderPanel2).add((Component)this.effacer);
        ((Container)borderPanel).add("North", (Component)borderPanel2);
        final BorderPanel borderPanel3 = new BorderPanel(5, 5, 5, 5, 7);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        ((Container)borderPanel3).setLayout(layout);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 3, 0);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        (this.couleurContour = new Choice()).addItem("Outline Black");
        this.couleurContour.addItem("Outline Transparent");
        this.couleurContour.addItem("Outline White");
        this.couleurContour.addItem("Outline Light gray");
        this.couleurContour.addItem("Outline Gray");
        this.couleurContour.addItem("Outline Dark gray");
        this.couleurContour.addItem("Outline Red");
        this.couleurContour.addItem("Outline Green");
        this.couleurContour.addItem("Outline Blue");
        this.couleurContour.addItem("Outline Yellow");
        this.couleurContour.addItem("Outline Magenta");
        this.couleurContour.addItem("Outline Cyan");
        this.couleurContour.addItem("Outline Pink");
        this.couleurContour.addItem("Outline Orange");
        this.couleurContour.addItem("Outline RGB...");
        layout.setConstraints(this.couleurContour, gridBagConstraints);
        ((Container)borderPanel3).add(this.couleurContour);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 3, 0);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        (this.couleurRemplissage = new Choice()).addItem("Fill White");
        this.couleurRemplissage.addItem("Fill Transparent");
        this.couleurRemplissage.addItem("Fill Black");
        this.couleurRemplissage.addItem("Fill Light gray");
        this.couleurRemplissage.addItem("Fill Gray");
        this.couleurRemplissage.addItem("Fill Dark gray");
        this.couleurRemplissage.addItem("Fill Red");
        this.couleurRemplissage.addItem("Fill Green");
        this.couleurRemplissage.addItem("Fill Blue");
        this.couleurRemplissage.addItem("Fill Yellow");
        this.couleurRemplissage.addItem("Fill Magenta");
        this.couleurRemplissage.addItem("Fill Cyan");
        this.couleurRemplissage.addItem("Fill Pink");
        this.couleurRemplissage.addItem("Fill Orange");
        this.couleurRemplissage.addItem("Fill RGB...");
        layout.setConstraints(this.couleurRemplissage, gridBagConstraints);
        ((Container)borderPanel3).add(this.couleurRemplissage);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 2);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridwidth = -1;
        final Label label = new Label("Outline thickness :");
        layout.setConstraints(label, gridBagConstraints);
        ((Container)borderPanel3).add(label);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.anchor = 14;
        gridBagConstraints.gridwidth = 0;
        (this.epaisseurContour = new TextField("1", 3)).setBackground(Color.white);
        layout.setConstraints(this.epaisseurContour, gridBagConstraints);
        ((Container)borderPanel3).add(this.epaisseurContour);
        ((Container)borderPanel).add("Center", (Component)borderPanel3);
        final BorderPanel borderPanel4 = new BorderPanel(5, 5, 5, 5, 7);
        ((Container)borderPanel4).setLayout(new GridLayout(1, 1, 0, 0));
        (this.couleurFond = new Choice()).addItem("Background White");
        this.couleurFond.addItem("Background Black");
        this.couleurFond.addItem("Background Light gray");
        this.couleurFond.addItem("Background Gray");
        this.couleurFond.addItem("Background Dark gray");
        this.couleurFond.addItem("Background Red");
        this.couleurFond.addItem("Background Green");
        this.couleurFond.addItem("Background Blue");
        this.couleurFond.addItem("Background Yellow");
        this.couleurFond.addItem("Background Magenta");
        this.couleurFond.addItem("Background Cyan");
        this.couleurFond.addItem("Background Pink");
        this.couleurFond.addItem("Background Orange");
        this.couleurFond.addItem("Background RGB...");
        ((Container)borderPanel4).add(this.couleurFond);
        ((Container)borderPanel).add("South", (Component)borderPanel4);
        this.add("Center", (Component)borderPanel);
        this.validate();
        this.pack();
        this.setMenuBar(menuBar);
        this.validate();
        this.pack();
        this.toFront();
    }
    
    public final boolean action(final Event event, final Object o) {
        if (event.target instanceof ImageButton) {
            if (event.target == this.fleche) {
                this.bouttonSelectionne = "fleche";
                if (this.parentJavaDraw.isApplet) {
                    this.parentJavaDraw.showStatus("- Current tool : Selection");
                }
            }
            else if (event.target == this.ligne) {
                this.bouttonSelectionne = "ligne";
                if (this.parentJavaDraw.isApplet) {
                    this.parentJavaDraw.showStatus("- Current tool : Line");
                }
            }
            else if (event.target == this.rectangle) {
                this.bouttonSelectionne = "rectangle";
                if (this.parentJavaDraw.isApplet) {
                    this.parentJavaDraw.showStatus("- Current tool : Rectangle");
                }
            }
            else if (event.target == this.rectangle3D1) {
                this.bouttonSelectionne = "rectangle3D1";
                if (this.parentJavaDraw.isApplet) {
                    this.parentJavaDraw.showStatus("- Current tool : Rectangle 3D relief");
                }
            }
            else if (event.target == this.rectangle3D0) {
                this.bouttonSelectionne = "rectangle3D0";
                if (this.parentJavaDraw.isApplet) {
                    this.parentJavaDraw.showStatus("- Current tool : Rectangle 3D hollow");
                }
            }
            else if (event.target == this.point) {
                this.bouttonSelectionne = "point";
                if (this.parentJavaDraw.isApplet) {
                    this.parentJavaDraw.showStatus("- Current tool : Point");
                }
            }
            else if (event.target == this.ovale) {
                this.bouttonSelectionne = "ovale";
                if (this.parentJavaDraw.isApplet) {
                    this.parentJavaDraw.showStatus("- Current tool : Oval");
                }
            }
            else if (event.target == this.polygone) {
                this.bouttonSelectionne = "polygone";
                if (this.parentJavaDraw.isApplet) {
                    this.parentJavaDraw.showStatus("- Current tool : Polygon");
                }
            }
            else if (event.target == this.texte) {
                this.bouttonSelectionne = "texte";
                if (!this.paletteText.ouverte) {
                    this.paletteText.ouverte = true;
                    if (this.premiereOuvertureTexte) {
                        ((Component)this.paletteText).move(200, 45);
                        this.premiereOuvertureTexte = false;
                    }
                    ((Window)this.paletteText).toFront();
                    ((Window)this.paletteText).show();
                }
                if (this.parentJavaDraw.isApplet) {
                    this.parentJavaDraw.showStatus("- Current tool : Text");
                }
            }
            else if (event.target == this.effacer) {
                this.bouttonSelectionne = "effacer";
                this.parentJavaDraw.plan[this.parentJavaDraw.planSelectionne] = new Plan("rien");
                this.parentJavaDraw.dessinePlans();
                this.parentJavaDraw.repaint();
                if (this.parentJavaDraw.isApplet) {
                    this.parentJavaDraw.showStatus("- Current tool : Delete");
                }
            }
            return true;
        }
        if (event.target instanceof MenuItem) {
            final String s = (String)o;
            if (s.equals("Open...")) {
                this.parentJavaDraw.ouvre();
            }
            else if (s.equals("Save")) {
                this.parentJavaDraw.enregistre2();
            }
            else if (s.equals("Save as...")) {
                this.parentJavaDraw.enregistre();
            }
            else if (s.equals("Save code")) {
                this.parentJavaDraw.enregistreCode();
            }
            else if (s.equals("Duplicate")) {
                this.parentJavaDraw.dupliqueObjet();
            }
            else if (s.equals("Delete")) {
                this.parentJavaDraw.plan[this.parentJavaDraw.planSelectionne] = new Plan("rien");
                this.parentJavaDraw.dessinePlans();
                this.parentJavaDraw.repaint();
            }
            else if (s.equals("Delete all")) {
                this.parentJavaDraw.effacePlans();
                this.parentJavaDraw.dessinePlans();
                this.parentJavaDraw.repaint();
            }
            else if (s.equals("To front")) {
                this.parentJavaDraw.premierPlan();
            }
            else if (s.equals("Before")) {
                this.parentJavaDraw.enAvant();
            }
            else if (s.equals("After")) {
                this.parentJavaDraw.enArriere();
            }
            else if (s.equals("Generate code...")) {
                this.parentJavaDraw.afficheCode();
            }
            else if (s.equals("Quit")) {
                this.hide();
                this.paletteCoulContour.ouverte = false;
                ((Component)this.paletteCoulContour).hide();
                this.paletteCoulRemplissage.ouverte = false;
                ((Component)this.paletteCoulRemplissage).hide();
                this.paletteCoulFond.ouverte = false;
                ((Component)this.paletteCoulFond).hide();
                this.paletteText.ouverte = false;
                ((Component)this.paletteText).hide();
                ((Component)this.fenetreAide).hide();
                this.parentJavaDraw.afficheInfo("A bientot (see ya') ! :-)");
                if (!this.parentJavaDraw.isApplet) {
                    System.exit(0);
                }
            }
            else if (s.equals("A brief description...")) {
                ((Component)this.fenetreAide).move(45, 45);
                ((Window)this.fenetreAide).toFront();
                ((Window)this.fenetreAide).show();
            }
            else if (s.equals("About...")) {
                this.parentJavaDraw.dessinAcceuil();
                this.parentJavaDraw.repaint();
            }
            else if (this.parentJavaDraw.plan[this.parentJavaDraw.planSelectionne].type.equals("texte")) {
                if (s.equals("Courier")) {
                    this.parentJavaDraw.plan[this.parentJavaDraw.planSelectionne].police = "Courier";
                }
                else if (s.equals("Helvetica")) {
                    this.parentJavaDraw.plan[this.parentJavaDraw.planSelectionne].police = "Helvetica";
                }
                else if (s.equals("TimesRoman")) {
                    this.parentJavaDraw.plan[this.parentJavaDraw.planSelectionne].police = "TimesRoman";
                }
                else if (s.equals("Symbol")) {
                    this.parentJavaDraw.plan[this.parentJavaDraw.planSelectionne].police = "Symbol";
                }
                else if (s.equals("Dialog")) {
                    this.parentJavaDraw.plan[this.parentJavaDraw.planSelectionne].police = "Dialog";
                }
                else if (s.equals("Normal")) {
                    this.parentJavaDraw.plan[this.parentJavaDraw.planSelectionne].style = 0;
                }
                else if (s.equals("Bold")) {
                    this.parentJavaDraw.plan[this.parentJavaDraw.planSelectionne].style = 1;
                }
                else if (s.equals("Italic")) {
                    this.parentJavaDraw.plan[this.parentJavaDraw.planSelectionne].style = 2;
                }
                else if (s.equals("Bold-italic")) {
                    this.parentJavaDraw.plan[this.parentJavaDraw.planSelectionne].style = 3;
                }
                else if (s.equals("8")) {
                    this.parentJavaDraw.plan[this.parentJavaDraw.planSelectionne].taille = 8;
                }
                else if (s.equals("12")) {
                    this.parentJavaDraw.plan[this.parentJavaDraw.planSelectionne].taille = 12;
                }
                else if (s.equals("18")) {
                    this.parentJavaDraw.plan[this.parentJavaDraw.planSelectionne].taille = 18;
                }
                else if (s.equals("24")) {
                    this.parentJavaDraw.plan[this.parentJavaDraw.planSelectionne].taille = 24;
                }
                else if (s.equals("30")) {
                    this.parentJavaDraw.plan[this.parentJavaDraw.planSelectionne].taille = 30;
                }
                else if (s.equals("40")) {
                    this.parentJavaDraw.plan[this.parentJavaDraw.planSelectionne].taille = 40;
                }
                else if (s.equals("50")) {
                    this.parentJavaDraw.plan[this.parentJavaDraw.planSelectionne].taille = 50;
                }
                else if (s.equals("70")) {
                    this.parentJavaDraw.plan[this.parentJavaDraw.planSelectionne].taille = 70;
                }
                else if (s.equals("100")) {
                    this.parentJavaDraw.plan[this.parentJavaDraw.planSelectionne].taille = 100;
                }
                else if (s.equals("taille +1")) {
                    final Plan plan = this.parentJavaDraw.plan[this.parentJavaDraw.planSelectionne];
                    ++plan.taille;
                }
                else if (s.equals("taille -1")) {
                    final Plan plan2 = this.parentJavaDraw.plan[this.parentJavaDraw.planSelectionne];
                    --plan2.taille;
                }
                this.parentJavaDraw.miseAJourTexte();
                this.parentJavaDraw.dessinePlans();
                this.parentJavaDraw.selectObjet();
                this.parentJavaDraw.repaint();
            }
            return true;
        }
        if (event.target instanceof Choice) {
            final String selectedItem = this.couleurContour.getSelectedItem();
            final String selectedItem2 = this.couleurRemplissage.getSelectedItem();
            final String selectedItem3 = this.couleurFond.getSelectedItem();
            if (selectedItem.equals("Outline RGB...") && !this.paletteCoulContour.ouverte) {
                this.paletteCoulContour.ouverte = true;
                if (this.premiereOuvertureCoulContour) {
                    ((Component)this.paletteCoulContour).move(200, 45);
                    this.premiereOuvertureCoulContour = false;
                }
                ((Window)this.paletteCoulContour).toFront();
                ((Window)this.paletteCoulContour).show();
            }
            else if (!selectedItem.equals("Outline RGB...")) {
                this.paletteCoulContour.ouverte = false;
                ((Component)this.paletteCoulContour).hide();
            }
            if (selectedItem.equals("Outline White")) {
                this.coulContour = Color.white;
            }
            else if (selectedItem.equals("Outline Black")) {
                this.coulContour = Color.black;
            }
            else if (selectedItem.equals("Outline Light gray")) {
                this.coulContour = Color.lightGray;
            }
            else if (selectedItem.equals("Outline Gray")) {
                this.coulContour = Color.gray;
            }
            else if (selectedItem.equals("Outline Dark gray")) {
                this.coulContour = Color.darkGray;
            }
            else if (selectedItem.equals("Outline Red")) {
                this.coulContour = Color.red;
            }
            else if (selectedItem.equals("Outline Green")) {
                this.coulContour = Color.green;
            }
            else if (selectedItem.equals("Outline Blue")) {
                this.coulContour = Color.blue;
            }
            else if (selectedItem.equals("Outline Yellow")) {
                this.coulContour = Color.yellow;
            }
            else if (selectedItem.equals("Outline Magenta")) {
                this.coulContour = Color.magenta;
            }
            else if (selectedItem.equals("Outline Cyan")) {
                this.coulContour = Color.cyan;
            }
            else if (selectedItem.equals("Outline Pink")) {
                this.coulContour = Color.pink;
            }
            else if (selectedItem.equals("Outline Orange")) {
                this.coulContour = Color.orange;
            }
            if (selectedItem2.equals("Fill RGB...") && !this.paletteCoulRemplissage.ouverte) {
                this.paletteCoulRemplissage.ouverte = true;
                if (this.premiereOuvertureCoulRempl) {
                    ((Component)this.paletteCoulRemplissage).move(200, 45);
                    this.premiereOuvertureCoulRempl = false;
                }
                ((Window)this.paletteCoulRemplissage).toFront();
                ((Window)this.paletteCoulRemplissage).show();
            }
            else if (!selectedItem2.equals("Fill RGB...")) {
                this.paletteCoulRemplissage.ouverte = false;
                ((Component)this.paletteCoulRemplissage).hide();
            }
            if (selectedItem2.equals("Fill White")) {
                this.coulRemplissage = Color.white;
            }
            else if (selectedItem2.equals("Fill Black")) {
                this.coulRemplissage = Color.black;
            }
            else if (selectedItem2.equals("Fill Light gray")) {
                this.coulRemplissage = Color.lightGray;
            }
            else if (selectedItem2.equals("Fill Gray")) {
                this.coulRemplissage = Color.gray;
            }
            else if (selectedItem2.equals("Fill Dark gray")) {
                this.coulRemplissage = Color.darkGray;
            }
            else if (selectedItem2.equals("Fill Red")) {
                this.coulRemplissage = Color.red;
            }
            else if (selectedItem2.equals("Fill Green")) {
                this.coulRemplissage = Color.green;
            }
            else if (selectedItem2.equals("Fill Blue")) {
                this.coulRemplissage = Color.blue;
            }
            else if (selectedItem2.equals("Fill Yellow")) {
                this.coulRemplissage = Color.yellow;
            }
            else if (selectedItem2.equals("Fill Magenta")) {
                this.coulRemplissage = Color.magenta;
            }
            else if (selectedItem2.equals("Fill Cyan")) {
                this.coulRemplissage = Color.cyan;
            }
            else if (selectedItem2.equals("Fill Pink")) {
                this.coulRemplissage = Color.pink;
            }
            else if (selectedItem2.equals("Fill Orange")) {
                this.coulRemplissage = Color.orange;
            }
            if (selectedItem3.equals("Background RGB...") && !this.paletteCoulFond.ouverte) {
                this.paletteCoulFond.ouverte = true;
                if (this.premiereOuvertureCoulFond) {
                    ((Component)this.paletteCoulFond).move(200, 45);
                    this.premiereOuvertureCoulFond = false;
                }
                ((Window)this.paletteCoulFond).toFront();
                ((Window)this.paletteCoulFond).show();
            }
            else if (!selectedItem3.equals("Background RGB...")) {
                this.paletteCoulFond.ouverte = false;
                ((Component)this.paletteCoulFond).hide();
            }
            if (selectedItem3.equals("Background White")) {
                this.coulFond = Color.white;
            }
            else if (selectedItem3.equals("Background Black")) {
                this.coulFond = Color.black;
            }
            else if (selectedItem3.equals("Background Light gray")) {
                this.coulFond = Color.lightGray;
            }
            else if (selectedItem3.equals("Background Gray")) {
                this.coulFond = Color.gray;
            }
            else if (selectedItem3.equals("Background Dark gray")) {
                this.coulFond = Color.darkGray;
            }
            else if (selectedItem3.equals("Background Red")) {
                this.coulFond = Color.red;
            }
            else if (selectedItem3.equals("Background Green")) {
                this.coulFond = Color.green;
            }
            else if (selectedItem3.equals("Background Blue")) {
                this.coulFond = Color.blue;
            }
            else if (selectedItem3.equals("Background Yellow")) {
                this.coulFond = Color.yellow;
            }
            else if (selectedItem3.equals("Background Magenta")) {
                this.coulFond = Color.magenta;
            }
            else if (selectedItem3.equals("Background Cyan")) {
                this.coulFond = Color.cyan;
            }
            else if (selectedItem3.equals("Background Pink")) {
                this.coulFond = Color.pink;
            }
            else if (selectedItem3.equals("Background Orange")) {
                this.coulFond = Color.orange;
            }
            this.parentJavaDraw.miseAJourCouleur();
            this.parentJavaDraw.changeCouleur();
            this.parentJavaDraw.dessinePlans();
            this.parentJavaDraw.selectObjet();
            this.parentJavaDraw.repaint();
            return true;
        }
        if (event.target instanceof TextField) {
            try {
                this.epaisseur = new Integer(this.epaisseurContour.getText());
            }
            catch (Exception ex) {
                this.epaisseur = 1;
                this.epaisseurContour.setText("1");
            }
            if (this.epaisseur > 50) {
                this.epaisseur = 50;
                this.epaisseurContour.setText("50");
            }
            this.parentJavaDraw.changeEpaisseur();
            this.parentJavaDraw.dessinePlans();
            this.parentJavaDraw.selectObjet();
            this.parentJavaDraw.repaint();
            return true;
        }
        return false;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            System.exit(0);
            return true;
        }
        return super.handleEvent(event);
    }
}
