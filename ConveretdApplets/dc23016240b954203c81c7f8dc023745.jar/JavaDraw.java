import java.awt.Window;
import java.util.StringTokenizer;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.awt.FileDialog;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.MediaTracker;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Polygon;
import java.awt.Point;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class JavaDraw extends Applet
{
    PaletteOutil palette;
    FenetreCode fenetreCode;
    Image offscreenImg;
    Graphics offscreenG;
    int width;
    int height;
    Color coulFond;
    Color coulContour;
    Color coulRemplissage;
    Color coulSelection;
    Font infoFont;
    boolean contourTransp;
    boolean remplissageTransp;
    Point pointInitial;
    Point pointCourant;
    Point pointFinal;
    boolean nouveauPoly;
    Polygon polygoneCourant;
    Point pointInitialPoly;
    int positionInitialeX;
    int positionInitialeY;
    Plan[] plan;
    int planSelectionne;
    Object frame;
    boolean isApplet;
    String nomFichier;
    Image arrow;
    Image line;
    Image rect;
    Image rect3D1;
    Image rect3D0;
    Image oval;
    Image point;
    Image polygon;
    Image delete;
    Image text;
    
    public static void main(final String[] array) {
        final Frame frame = new Frame(" JavaDraw");
        final JavaDraw javaDraw = new JavaDraw();
        frame.add("Center", javaDraw);
        frame.resize(400, 380);
        frame.move(220, 45);
        frame.toFront();
        frame.show();
        frame.move(220, 45);
        javaDraw.isApplet = false;
        javaDraw.init();
        javaDraw.start();
    }
    
    public void isApplication() {
        this.isApplet = false;
    }
    
    public void init() {
        this.frame = this.getParent();
        while (!(this.frame instanceof Frame)) {
            this.frame = ((Component)this.frame).getParent();
        }
        ((Frame)this.frame).setCursor(3);
        this.loadImages();
        try {
            this.width = new Integer(this.getParameter("width"));
        }
        catch (Exception ex) {
            this.width = 640;
        }
        try {
            this.height = new Integer(this.getParameter("height"));
        }
        catch (Exception ex2) {
            this.height = 480;
        }
        ((Component)(this.palette = new PaletteOutil(" Tools", this))).move(7, 45);
        ((Window)this.palette).toFront();
        ((Window)this.palette).show();
        this.offscreenImg = this.createImage(this.width, this.height);
        (this.offscreenG = this.offscreenImg.getGraphics()).setColor(this.coulFond);
        this.offscreenG.fillRect(0, 0, this.width, this.height);
        this.dessinAcceuil();
        this.plan[0] = new Plan("rien");
        ((Frame)this.frame).setCursor(0);
    }
    
    public void start() {
        if (this.isApplet) {
            this.showStatus("Bienvenue sur JavaDraw !");
            if (!((Component)this.palette).isShowing()) {
                ((Window)this.palette).show();
                this.dessinAcceuil();
            }
        }
    }
    
    public void stop() {
        if (this.isApplet) {
            ((Component)this.palette).hide();
            this.palette.paletteCoulContour.ouverte = false;
            ((Component)this.palette.paletteCoulContour).hide();
            this.palette.paletteCoulRemplissage.ouverte = false;
            ((Component)this.palette.paletteCoulRemplissage).hide();
            this.palette.paletteCoulFond.ouverte = false;
            ((Component)this.palette.paletteCoulFond).hide();
            this.palette.paletteText.ouverte = false;
            ((Component)this.palette.paletteText).hide();
            ((Component)this.palette.fenetreAide).hide();
            this.afficheInfo("A bientot ! :-)");
        }
    }
    
    public void loadImages() {
        if (this.isApplet) {
            this.showStatus("Loading images...");
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(this.arrow = this.getImage(this.getCodeBase(), "tool_arrow.gif"), 0);
            mediaTracker.addImage(this.rect = this.getImage(this.getCodeBase(), "tool_rect.gif"), 0);
            mediaTracker.addImage(this.rect3D1 = this.getImage(this.getCodeBase(), "tool_3Drect1.gif"), 0);
            mediaTracker.addImage(this.rect3D0 = this.getImage(this.getCodeBase(), "tool_3Drect0.gif"), 0);
            mediaTracker.addImage(this.oval = this.getImage(this.getCodeBase(), "tool_oval.gif"), 0);
            mediaTracker.addImage(this.point = this.getImage(this.getCodeBase(), "tool_point.gif"), 0);
            mediaTracker.addImage(this.line = this.getImage(this.getCodeBase(), "tool_line.gif"), 0);
            mediaTracker.addImage(this.polygon = this.getImage(this.getCodeBase(), "tool_polygon.gif"), 0);
            mediaTracker.addImage(this.delete = this.getImage(this.getCodeBase(), "tool_delete.gif"), 0);
            mediaTracker.addImage(this.text = this.getImage(this.getCodeBase(), "tool_text.gif"), 0);
            try {
                mediaTracker.waitForAll();
                return;
            }
            catch (InterruptedException ex) {
                this.showStatus("Error waiting for images...");
                return;
            }
        }
        final MediaTracker mediaTracker2 = new MediaTracker(this);
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        mediaTracker2.addImage(this.arrow = defaultToolkit.getImage("tool_arrow.gif"), 0);
        mediaTracker2.addImage(this.rect = defaultToolkit.getImage("tool_rect.gif"), 0);
        mediaTracker2.addImage(this.rect3D1 = defaultToolkit.getImage("tool_3Drect1.gif"), 0);
        mediaTracker2.addImage(this.rect3D0 = defaultToolkit.getImage("tool_3Drect0.gif"), 0);
        mediaTracker2.addImage(this.oval = defaultToolkit.getImage("tool_oval.gif"), 0);
        mediaTracker2.addImage(this.point = defaultToolkit.getImage("tool_point.gif"), 0);
        mediaTracker2.addImage(this.line = defaultToolkit.getImage("tool_line.gif"), 0);
        mediaTracker2.addImage(this.polygon = defaultToolkit.getImage("tool_polygon.gif"), 0);
        mediaTracker2.addImage(this.delete = defaultToolkit.getImage("tool_delete.gif"), 0);
        mediaTracker2.addImage(this.text = defaultToolkit.getImage("tool_text.gif"), 0);
        try {
            mediaTracker2.waitForAll();
        }
        catch (InterruptedException ex2) {
            System.err.println("Error waiting for images...");
        }
    }
    
    public void dessinAcceuil() {
        this.repaint();
        int n;
        int n2;
        if (!this.isApplet) {
            n = ((Frame)this.frame).size().width;
            n2 = ((Frame)this.frame).size().height;
        }
        else {
            n = this.size().width;
            n2 = this.size().height;
        }
        final Color[] array = new Color[50];
        float n3 = 0.0f;
        for (int i = 0; i < array.length; ++i) {
            array[i] = Color.getHSBColor(n3, 1.0f, 1.0f);
            n3 += 0.02;
        }
        int n4;
        if (n > n2) {
            n4 = n;
        }
        else {
            n4 = n2;
        }
        final int n5 = n4 / 50;
        for (int j = 0; j < 50; ++j) {
            this.offscreenG.setColor(array[j]);
            final int n6 = n4 - j * n5;
            this.offscreenG.fillRect(0, 0, n6, n6);
        }
        final Font font = new Font("Helvetica", 1, 50);
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        this.offscreenG.setFont(font);
        this.offscreenG.setColor(Color.blue);
        this.offscreenG.drawString("JavaDraw !", (n - fontMetrics.stringWidth("JavaDraw !")) / 2, n2 - 80);
        final Font font2 = new Font("Helvetica", 0, 12);
        final FontMetrics fontMetrics2 = this.getFontMetrics(font2);
        this.offscreenG.setFont(font2);
        this.offscreenG.drawString("Version 1.0.5, march 1998", (n - fontMetrics2.stringWidth("Version 1.0.5, march 1998")) / 2, n2 - 55);
        this.offscreenG.drawString("by Vincent Zimmermann, NCTech, France", (n - fontMetrics2.stringWidth("by Vincent Zimmermann, NCTech, France")) / 2, n2 - 40);
        this.repaint();
    }
    
    public final Polygon translatePoly2(final Polygon polygon, final int n, final int n2) {
        final int npoints = polygon.npoints;
        final int[] array = new int[npoints];
        final int[] array2 = new int[npoints];
        for (int i = 0; i < npoints; ++i) {
            array[i] = polygon.xpoints[i] + n;
            array2[i] = polygon.ypoints[i] + n2;
        }
        return new Polygon(array, array2, npoints);
    }
    
    public final Polygon translatePoly(final Polygon polygon, final int n, final int n2) {
        final int npoints = polygon.npoints;
        final int[] array = new int[npoints];
        final int[] array2 = new int[npoints];
        final Rectangle boundingBox = polygon.getBoundingBox();
        final int x = boundingBox.x;
        final int y = boundingBox.y;
        for (int i = 0; i < npoints; ++i) {
            array[i] = n - x + polygon.xpoints[i];
            array2[i] = n2 - y + polygon.ypoints[i];
        }
        return new Polygon(array, array2, npoints);
    }
    
    public final void miseAJourCouleur() {
        if (this.palette.couleurContour.getSelectedItem().equals("Outline RGB...")) {
            this.coulContour = this.palette.paletteCoulContour.couleur;
        }
        else {
            this.coulContour = this.palette.coulContour;
        }
        if (this.palette.couleurRemplissage.getSelectedItem().equals("Fill RGB...")) {
            this.coulRemplissage = this.palette.paletteCoulRemplissage.couleur;
        }
        else {
            this.coulRemplissage = this.palette.coulRemplissage;
        }
        if (this.palette.couleurFond.getSelectedItem().equals("Background RGB...")) {
            this.coulFond = this.palette.paletteCoulFond.couleur;
        }
        else {
            this.coulFond = this.palette.coulFond;
        }
        this.contourTransp = false;
        this.remplissageTransp = false;
        if (this.palette.couleurContour.getSelectedItem().equals("Outline Transparent")) {
            this.contourTransp = true;
        }
        if (this.palette.couleurRemplissage.getSelectedItem().equals("Fill Transparent")) {
            this.remplissageTransp = true;
        }
        if (this.coulFond == Color.blue) {
            this.coulSelection = Color.white;
            return;
        }
        this.coulSelection = Color.blue;
    }
    
    public final void miseAJourTexte() {
        final FontMetrics fontMetrics = this.getFontMetrics(new Font(this.plan[this.planSelectionne].police, this.plan[this.planSelectionne].style, this.plan[this.planSelectionne].taille));
        final int stringWidth = fontMetrics.stringWidth(this.plan[this.planSelectionne].texte);
        final int ascent = fontMetrics.getAscent();
        this.plan[this.planSelectionne].largeur = stringWidth;
        this.plan[this.planSelectionne].hauteur = ascent;
    }
    
    public final void changeCouleur() {
        if (this.planSelectionne != 0) {
            this.miseAJourCouleur();
            if (this.plan[this.planSelectionne].type.equals("ligne") || this.plan[this.planSelectionne].type.equals("rectangle") || this.plan[this.planSelectionne].type.equals("rectangle3D") || this.plan[this.planSelectionne].type.equals("ovale") || this.plan[this.planSelectionne].type.equals("polygone")) {
                this.plan[this.planSelectionne].couleurContour = this.coulContour;
                this.plan[this.planSelectionne].contourTransp = this.contourTransp;
            }
            if (this.plan[this.planSelectionne].type.equals("rectangle") || this.plan[this.planSelectionne].type.equals("ovale") || this.plan[this.planSelectionne].type.equals("polygone") || this.plan[this.planSelectionne].type.equals("texte") || this.plan[this.planSelectionne].type.equals("point")) {
                this.plan[this.planSelectionne].couleurRemplissage = this.coulRemplissage;
                this.plan[this.planSelectionne].remplissageTransp = this.remplissageTransp;
            }
        }
    }
    
    public final void changeEpaisseur() {
        if (this.planSelectionne != 0 && (this.plan[this.planSelectionne].type.equals("ligne") || this.plan[this.planSelectionne].type.equals("rectangle") || this.plan[this.planSelectionne].type.equals("ovale") || this.plan[this.planSelectionne].type.equals("point"))) {
            this.plan[this.planSelectionne].epaisseur = this.palette.epaisseur;
        }
    }
    
    public final void premierPlan() {
        if (this.planSelectionne != 0) {
            if (this.planSelectionne == Plan.nombrePlan) {
                this.afficheInfo("This object is already at first layer !");
                return;
            }
            ++Plan.nombrePlan;
            if (Plan.nombrePlan == 799) {
                --Plan.nombrePlan;
                this.afficheInfo("There is too much layers");
            }
            this.plan[Plan.nombrePlan] = this.plan[this.planSelectionne];
            this.plan[this.planSelectionne] = new Plan("rien");
            this.dessinePlans();
            this.planSelectionne = Plan.nombrePlan;
            this.selectObjet();
            this.repaint();
        }
    }
    
    public final void enAvant() {
        if (this.planSelectionne != 0) {
            if (this.planSelectionne == Plan.nombrePlan) {
                this.afficheInfo("This object is already at first layer !");
                return;
            }
            final Plan plan = (Plan)this.plan[this.planSelectionne].clone();
            this.plan[this.planSelectionne] = new Plan("rien");
            this.plan[this.planSelectionne] = (Plan)this.plan[this.planSelectionne + 1].clone();
            this.plan[this.planSelectionne + 1] = plan;
            this.dessinePlans();
            ++this.planSelectionne;
            this.selectObjet();
            this.repaint();
        }
    }
    
    public final void enArriere() {
        if (this.planSelectionne != 0) {
            if (this.planSelectionne == 1) {
                this.afficheInfo("This object is already at last layer !");
                return;
            }
            final Plan plan = (Plan)this.plan[this.planSelectionne].clone();
            this.plan[this.planSelectionne] = new Plan("rien");
            this.plan[this.planSelectionne] = (Plan)this.plan[this.planSelectionne - 1].clone();
            this.plan[this.planSelectionne - 1] = plan;
            this.dessinePlans();
            --this.planSelectionne;
            this.selectObjet();
            this.repaint();
        }
    }
    
    public final void dupliqueObjet() {
        if (this.planSelectionne != 0) {
            ++Plan.nombrePlan;
            if (Plan.nombrePlan == 799) {
                --Plan.nombrePlan;
                this.afficheInfo("There is too much layers");
            }
            this.plan[Plan.nombrePlan] = (Plan)this.plan[this.planSelectionne].clone();
            this.dessinePlans();
            this.selectObjet();
            this.repaint();
        }
    }
    
    public final void selectObjet() {
        this.palette.mText.disable();
        if (this.plan[this.planSelectionne].type.equals("rectangle")) {
            this.selectRectangle(this.planSelectionne);
            return;
        }
        if (this.plan[this.planSelectionne].type.equals("ovale")) {
            this.selectOvale(this.planSelectionne);
            return;
        }
        if (this.plan[this.planSelectionne].type.equals("point")) {
            this.selectPoint(this.planSelectionne);
            return;
        }
        if (this.plan[this.planSelectionne].type.equals("ligne")) {
            this.selectLigne(this.planSelectionne);
            return;
        }
        if (this.plan[this.planSelectionne].type.equals("polygone")) {
            this.selectPolygone(this.planSelectionne);
            return;
        }
        if (this.plan[this.planSelectionne].type.equals("texte")) {
            this.selectTexte(this.planSelectionne);
            return;
        }
        if (this.plan[this.planSelectionne].type.equals("rectangle3D")) {
            this.selectRectangle(this.planSelectionne);
        }
    }
    
    public final void selectRectangle(final int planSelectionne) {
        this.planSelectionne = planSelectionne;
        this.offscreenG.setColor(this.coulSelection);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 - 2, this.plan[planSelectionne].y1 - 2, 5, 5);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 - 2, this.plan[planSelectionne].y1 + this.plan[planSelectionne].hauteur - 2, 5, 5);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 + this.plan[planSelectionne].largeur - 2, this.plan[planSelectionne].y1 - 2, 5, 5);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 + this.plan[planSelectionne].largeur - 2, this.plan[planSelectionne].y1 + this.plan[planSelectionne].hauteur - 2, 5, 5);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 + this.plan[planSelectionne].largeur / 2 - 2, this.plan[planSelectionne].y1 + this.plan[planSelectionne].hauteur / 2 - 2, 5, 5);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 + this.plan[planSelectionne].largeur / 2 - 1, this.plan[planSelectionne].y1 - 1, 3, 3);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 + this.plan[planSelectionne].largeur / 2 - 1, this.plan[planSelectionne].y1 + this.plan[planSelectionne].hauteur - 1, 3, 3);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 - 1, this.plan[planSelectionne].y1 + this.plan[planSelectionne].hauteur / 2 - 1, 3, 3);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 + this.plan[planSelectionne].largeur - 1, this.plan[planSelectionne].y1 + this.plan[planSelectionne].hauteur / 2 - 1, 3, 3);
        this.offscreenG.setFont(this.infoFont);
        this.offscreenG.drawString("x = " + this.plan[planSelectionne].x1, 5, 10);
        this.offscreenG.drawString("y = " + this.plan[planSelectionne].y1, 5, 20);
        this.offscreenG.drawString("width = " + this.plan[planSelectionne].largeur, 5, 35);
        this.offscreenG.drawString("height = " + this.plan[planSelectionne].hauteur, 5, 45);
        if (!this.plan[this.planSelectionne].type.equals("rectangle3D")) {
            this.offscreenG.drawString("thickness = " + this.plan[planSelectionne].epaisseur, 5, 60);
        }
        this.repaint();
    }
    
    public final void selectOvale(final int planSelectionne) {
        this.planSelectionne = planSelectionne;
        this.offscreenG.setColor(this.coulSelection);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 - 2, this.plan[planSelectionne].y1 - 2, 5, 5);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 - 2, this.plan[planSelectionne].y1 + this.plan[planSelectionne].hauteur - 2, 5, 5);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 + this.plan[planSelectionne].largeur - 2, this.plan[planSelectionne].y1 - 2, 5, 5);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 + this.plan[planSelectionne].largeur - 2, this.plan[planSelectionne].y1 + this.plan[planSelectionne].hauteur - 2, 5, 5);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 + this.plan[planSelectionne].largeur / 2 - 2, this.plan[planSelectionne].y1 + this.plan[planSelectionne].hauteur / 2 - 2, 5, 5);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 + this.plan[planSelectionne].largeur / 2 - 1, this.plan[planSelectionne].y1 - 1, 3, 3);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 + this.plan[planSelectionne].largeur / 2 - 1, this.plan[planSelectionne].y1 + this.plan[planSelectionne].hauteur - 1, 3, 3);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 - 1, this.plan[planSelectionne].y1 + this.plan[planSelectionne].hauteur / 2 - 1, 3, 3);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 + this.plan[planSelectionne].largeur - 1, this.plan[planSelectionne].y1 + this.plan[planSelectionne].hauteur / 2 - 1, 3, 3);
        this.offscreenG.setFont(this.infoFont);
        this.offscreenG.drawString("x = " + this.plan[planSelectionne].x1, 5, 10);
        this.offscreenG.drawString("y = " + this.plan[planSelectionne].y1, 5, 20);
        this.offscreenG.drawString("width = " + this.plan[planSelectionne].largeur, 5, 35);
        this.offscreenG.drawString("height = " + this.plan[planSelectionne].hauteur, 5, 45);
        this.offscreenG.drawString("thickness = " + this.plan[planSelectionne].epaisseur, 5, 60);
        this.repaint();
    }
    
    public final void selectPoint(final int planSelectionne) {
        this.planSelectionne = planSelectionne;
        this.offscreenG.setColor(this.coulSelection);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 - 2, this.plan[planSelectionne].y1 - 2, 5, 5);
        this.offscreenG.setFont(this.infoFont);
        this.offscreenG.drawString("x = " + this.plan[planSelectionne].x1, 5, 10);
        this.offscreenG.drawString("y = " + this.plan[planSelectionne].y1, 5, 20);
        this.offscreenG.drawString("thickness = " + this.plan[planSelectionne].epaisseur, 5, 35);
        this.repaint();
    }
    
    public final void selectTexte(final int planSelectionne) {
        this.planSelectionne = planSelectionne;
        this.offscreenG.setColor(this.coulSelection);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 - 2, this.plan[planSelectionne].y1 - 2, 5, 5);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 - 2, this.plan[planSelectionne].y1 + this.plan[planSelectionne].hauteur - 2, 5, 5);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 + this.plan[planSelectionne].largeur - 2, this.plan[planSelectionne].y1 - 2, 5, 5);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 + this.plan[planSelectionne].largeur - 2, this.plan[planSelectionne].y1 + this.plan[planSelectionne].hauteur - 2, 5, 5);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 + this.plan[planSelectionne].largeur / 2 - 2, this.plan[planSelectionne].y1 + this.plan[planSelectionne].hauteur / 2 - 2, 5, 5);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 + this.plan[planSelectionne].largeur / 2 - 1, this.plan[planSelectionne].y1 - 1, 3, 3);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 + this.plan[planSelectionne].largeur / 2 - 1, this.plan[planSelectionne].y1 + this.plan[planSelectionne].hauteur - 1, 3, 3);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 - 1, this.plan[planSelectionne].y1 + this.plan[planSelectionne].hauteur / 2 - 1, 3, 3);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 + this.plan[planSelectionne].largeur - 1, this.plan[planSelectionne].y1 + this.plan[planSelectionne].hauteur / 2 - 1, 3, 3);
        this.offscreenG.setFont(this.infoFont);
        this.offscreenG.drawString("x = " + this.plan[planSelectionne].x1, 5, 10);
        this.offscreenG.drawString("y = " + this.plan[planSelectionne].y1, 5, 20);
        this.offscreenG.drawString("width = " + this.plan[planSelectionne].largeur, 5, 35);
        this.offscreenG.drawString("height = " + this.plan[planSelectionne].hauteur, 5, 45);
        this.offscreenG.drawString("size = " + this.plan[planSelectionne].taille, 5, 60);
        this.palette.mText.enable();
        this.repaint();
    }
    
    public final void selectLigne(final int planSelectionne) {
        this.planSelectionne = planSelectionne;
        this.offscreenG.setColor(this.coulSelection);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 - 2, this.plan[planSelectionne].y1 - 2, 5, 5);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 - 2, this.plan[planSelectionne].y1 + this.plan[planSelectionne].hauteur - 2, 5, 5);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 + this.plan[planSelectionne].largeur - 2, this.plan[planSelectionne].y1 - 2, 5, 5);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 + this.plan[planSelectionne].largeur - 2, this.plan[planSelectionne].y1 + this.plan[planSelectionne].hauteur - 2, 5, 5);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 + this.plan[planSelectionne].largeur / 2 - 2, this.plan[planSelectionne].y1 + this.plan[planSelectionne].hauteur / 2 - 2, 5, 5);
        this.offscreenG.setFont(this.infoFont);
        this.offscreenG.drawString("x = " + this.plan[planSelectionne].x1, 5, 10);
        this.offscreenG.drawString("y = " + this.plan[planSelectionne].y1, 5, 20);
        this.offscreenG.drawString("width = " + this.plan[planSelectionne].largeur, 5, 35);
        this.offscreenG.drawString("height = " + this.plan[planSelectionne].hauteur, 5, 45);
        this.offscreenG.drawString("thickness = " + this.plan[planSelectionne].epaisseur, 5, 60);
        this.repaint();
    }
    
    public final void selectPolygone(final int planSelectionne) {
        this.planSelectionne = planSelectionne;
        this.offscreenG.setColor(this.coulSelection);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 - 2, this.plan[planSelectionne].y1 - 2, 5, 5);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 - 2, this.plan[planSelectionne].y1 + this.plan[planSelectionne].hauteur - 2, 5, 5);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 + this.plan[planSelectionne].largeur - 2, this.plan[planSelectionne].y1 - 2, 5, 5);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 + this.plan[planSelectionne].largeur - 2, this.plan[planSelectionne].y1 + this.plan[planSelectionne].hauteur - 2, 5, 5);
        this.offscreenG.fillRect(this.plan[planSelectionne].x1 + this.plan[planSelectionne].largeur / 2 - 2, this.plan[planSelectionne].y1 + this.plan[planSelectionne].hauteur / 2 - 2, 5, 5);
        for (int npoints = this.plan[planSelectionne].polygone.npoints, i = 0; i < npoints; ++i) {
            this.offscreenG.fillRect(this.plan[planSelectionne].polygone.xpoints[i] - 1, this.plan[planSelectionne].polygone.ypoints[i] - 1, 3, 3);
        }
        this.offscreenG.setFont(this.infoFont);
        this.offscreenG.drawString("x = " + this.plan[planSelectionne].x1, 5, 10);
        this.offscreenG.drawString("y = " + this.plan[planSelectionne].y1, 5, 20);
        this.offscreenG.drawString("width = " + this.plan[planSelectionne].largeur, 5, 35);
        this.offscreenG.drawString("height = " + this.plan[planSelectionne].hauteur, 5, 45);
        this.offscreenG.drawString(this.plan[planSelectionne].polygone.npoints - 1 + " points", 5, 60);
        this.repaint();
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        this.dessinePlans();
        this.pointInitial = new Point(n, n2);
        this.pointCourant = new Point(n, n2);
        this.planSelectionne = 0;
        if (this.palette.bouttonSelectionne.equals("fleche") || this.palette.bouttonSelectionne.equals("effacer")) {
            ((Frame)this.frame).setCursor(12);
            for (int i = Plan.nombrePlan; i >= 0; --i) {
                if (this.plan[i].type.equals("rectangle")) {
                    if (this.plan[i].x1 <= n && n <= this.plan[i].largeur + this.plan[i].x1 && this.plan[i].y1 <= n2 && n2 <= this.plan[i].hauteur + this.plan[i].y1) {
                        this.selectRectangle(i);
                        this.positionInitialeX = this.plan[i].x1;
                        this.positionInitialeY = this.plan[i].y1;
                        break;
                    }
                }
                else if (this.plan[i].type.equals("ovale")) {
                    if (this.plan[i].x1 <= n && n <= this.plan[i].largeur + this.plan[i].x1 && this.plan[i].y1 <= n2 && n2 <= this.plan[i].hauteur + this.plan[i].y1) {
                        this.selectOvale(i);
                        this.positionInitialeX = this.plan[i].x1;
                        this.positionInitialeY = this.plan[i].y1;
                        break;
                    }
                }
                else if (this.plan[i].type.equals("point")) {
                    final int n3 = this.plan[i].epaisseur / 2;
                    if (this.plan[i].x1 - n3 - 1 <= n && n <= this.plan[i].epaisseur + this.plan[i].x1 - n3 + 1 && this.plan[i].y1 - n3 - 1 <= n2 && n2 <= this.plan[i].epaisseur + this.plan[i].y1 - n3 + 1) {
                        this.selectPoint(i);
                        this.positionInitialeX = this.plan[i].x1;
                        this.positionInitialeY = this.plan[i].y1;
                        break;
                    }
                }
                else if (this.plan[i].type.equals("texte")) {
                    if (this.plan[i].x1 <= n && n <= this.plan[i].largeur + this.plan[i].x1 && this.plan[i].y1 <= n2 && n2 <= this.plan[i].hauteur + this.plan[i].y1) {
                        this.selectTexte(i);
                        this.positionInitialeX = this.plan[i].x1;
                        this.positionInitialeY = this.plan[i].y1;
                        break;
                    }
                }
                else if (this.plan[i].type.equals("polygone")) {
                    if (this.plan[i].polygone.inside(n, n2)) {
                        this.selectPolygone(i);
                        this.positionInitialeX = this.plan[i].x1;
                        this.positionInitialeY = this.plan[i].y1;
                        break;
                    }
                }
                else if (this.plan[i].type.equals("rectangle3D")) {
                    if (this.plan[i].x1 <= n && n <= this.plan[i].largeur + this.plan[i].x1 && this.plan[i].y1 <= n2 && n2 <= this.plan[i].hauteur + this.plan[i].y1) {
                        this.selectOvale(i);
                        this.positionInitialeX = this.plan[i].x1;
                        this.positionInitialeY = this.plan[i].y1;
                        break;
                    }
                }
                else if (this.plan[i].type.equals("ligne")) {
                    int x1 = this.plan[i].x1;
                    int y1 = this.plan[i].y1;
                    int largeur = this.plan[i].largeur;
                    int hauteur = this.plan[i].hauteur;
                    if (this.plan[i].largeur < 0) {
                        x1 = this.plan[i].x1 + this.plan[i].largeur;
                        largeur = -this.plan[i].largeur;
                    }
                    if (this.plan[i].hauteur < 0) {
                        y1 = this.plan[i].y1 + this.plan[i].hauteur;
                        hauteur = -this.plan[i].hauteur;
                    }
                    if (x1 - 1 <= n && n <= largeur + x1 + 1 && y1 - 1 <= n2 && n2 <= hauteur + y1 + 1) {
                        float n4 = 9.9999998E10f;
                        if (this.plan[i].largeur != 0) {
                            n4 = this.plan[i].hauteur / this.plan[i].largeur;
                        }
                        float n5 = this.plan[i].y1 - n4 * (this.plan[i].x1 + 4);
                        float n6 = this.plan[i].y1 - n4 * (this.plan[i].x1 - 4);
                        if (n5 < n6) {
                            final float n7 = n5;
                            n5 = n6;
                            n6 = n7;
                        }
                        if (n2 < n4 * n + n5 + 2.0f && n2 > n4 * n + n6 - 2.0f) {
                            this.selectLigne(i);
                            this.positionInitialeX = this.plan[i].x1;
                            this.positionInitialeY = this.plan[i].y1;
                            break;
                        }
                    }
                }
            }
        }
        if (this.palette.bouttonSelectionne.equals("effacer")) {
            this.plan[this.planSelectionne] = new Plan("rien");
        }
        if (this.palette.bouttonSelectionne.equals("point")) {
            this.enregistrePoint();
            this.dessinePlans();
        }
        if (this.palette.bouttonSelectionne.equals("polygone")) {
            if (this.nouveauPoly) {
                this.nouveauPoly = false;
                (this.polygoneCourant = new Polygon()).addPoint(n, n2);
                this.pointInitialPoly = new Point(n, n2);
            }
            else if (n < this.pointInitialPoly.x + 5 && this.pointInitialPoly.x - 5 < n && n2 < this.pointInitialPoly.y + 5 && this.pointInitialPoly.y - 5 < n2) {
                this.polygoneCourant.addPoint(this.pointInitialPoly.x, this.pointInitialPoly.y);
                this.enregistrePolygone();
                this.dessinePlans();
                this.nouveauPoly = true;
            }
            else {
                this.polygoneCourant.addPoint(n, n2);
                this.dessineDroitesPoly();
            }
        }
        this.repaint();
        return true;
    }
    
    public final boolean mouseDrag(final Event event, final int n, final int n2) {
        this.pointCourant = new Point(n, n2);
        ((Frame)this.frame).setCursor(13);
        if (event.shiftDown()) {
            this.pointCourant.y = this.pointCourant.x - this.pointInitial.x + this.pointInitial.y;
        }
        if (this.planSelectionne != 0) {
            this.plan[this.planSelectionne].x1 = this.positionInitialeX - (this.pointInitial.x - n);
            this.plan[this.planSelectionne].y1 = this.positionInitialeY - (this.pointInitial.y - n2);
            if (this.plan[this.planSelectionne].type.equals("polygone")) {
                this.plan[this.planSelectionne].polygone = this.translatePoly(this.plan[this.planSelectionne].polygone, this.positionInitialeX - (this.pointInitial.x - n), this.positionInitialeY - (this.pointInitial.y - n2));
            }
        }
        this.dessinePlans();
        if (this.planSelectionne != 0) {
            this.offscreenG.setColor(this.coulSelection);
            this.offscreenG.setFont(this.infoFont);
            this.offscreenG.drawString("x moving = " + (this.pointCourant.x - this.pointInitial.x), 5, 35);
            this.offscreenG.drawString("y moving = " + (this.pointCourant.y - this.pointInitial.y), 5, 45);
        }
        this.dessineRectDrag();
        this.repaint();
        return true;
    }
    
    public final boolean mouseUp(final Event event, final int n, final int n2) {
        this.pointFinal = new Point(n, n2);
        ((Frame)this.frame).setCursor(0);
        if (event.shiftDown()) {
            this.pointFinal.y = this.pointFinal.x - this.pointInitial.x + this.pointInitial.y;
        }
        if (this.palette.bouttonSelectionne.equals("rectangle")) {
            this.enregistreRectOval("rectangle");
        }
        else if (this.palette.bouttonSelectionne.equals("ovale")) {
            this.enregistreRectOval("ovale");
        }
        else if (this.palette.bouttonSelectionne.equals("ligne")) {
            this.enregistreLigne();
        }
        else if (this.palette.bouttonSelectionne.equals("texte")) {
            this.enregistreTexte();
        }
        else if (this.palette.bouttonSelectionne.equals("rectangle3D1") || this.palette.bouttonSelectionne.equals("rectangle3D0")) {
            this.enregistreRect3D();
        }
        this.dessinePlans();
        this.selectObjet();
        if (!this.nouveauPoly) {
            this.dessineDroitesPoly();
        }
        this.pointInitial = new Point(0, 0);
        this.pointCourant = new Point(0, 0);
        this.repaint();
        return true;
    }
    
    public final boolean keyDown(final Event event, final int n) {
        switch (n) {
            case 1003: {
                this.plan[this.planSelectionne] = new Plan("rien");
                this.dessinePlans();
                this.repaint();
                break;
            }
            case 1002: {
                this.afficheCode();
                break;
            }
            case 1006: {
                final Plan plan = this.plan[this.planSelectionne];
                --plan.x1;
                if (this.plan[this.planSelectionne].type.equals("polygone")) {
                    this.plan[this.planSelectionne].polygone = this.translatePoly2(this.plan[this.planSelectionne].polygone, -1, 0);
                }
                this.dessinePlans();
                this.selectObjet();
                this.repaint();
                break;
            }
            case 1007: {
                final Plan plan2 = this.plan[this.planSelectionne];
                ++plan2.x1;
                if (this.plan[this.planSelectionne].type.equals("polygone")) {
                    this.plan[this.planSelectionne].polygone = this.translatePoly2(this.plan[this.planSelectionne].polygone, 1, 0);
                }
                this.dessinePlans();
                this.selectObjet();
                this.repaint();
                break;
            }
            case 1004: {
                final Plan plan3 = this.plan[this.planSelectionne];
                --plan3.y1;
                if (this.plan[this.planSelectionne].type.equals("polygone")) {
                    this.plan[this.planSelectionne].polygone = this.translatePoly2(this.plan[this.planSelectionne].polygone, 0, -1);
                }
                this.dessinePlans();
                this.selectObjet();
                this.repaint();
                break;
            }
            case 1005: {
                final Plan plan4 = this.plan[this.planSelectionne];
                ++plan4.y1;
                if (this.plan[this.planSelectionne].type.equals("polygone")) {
                    this.plan[this.planSelectionne].polygone = this.translatePoly2(this.plan[this.planSelectionne].polygone, 0, 1);
                }
                this.dessinePlans();
                this.selectObjet();
                this.repaint();
                break;
            }
        }
        return true;
    }
    
    public final void enregistreRectOval(final String s) {
        int n;
        int n2;
        int n3;
        int n4;
        if (this.pointCourant.x - this.pointInitial.x > 0 && this.pointCourant.y - this.pointInitial.y > 0) {
            n = this.pointInitial.x;
            n2 = this.pointInitial.y;
            n3 = this.pointCourant.x - this.pointInitial.x;
            n4 = this.pointCourant.y - this.pointInitial.y;
        }
        else if (this.pointCourant.x - this.pointInitial.x < 0 && this.pointCourant.y - this.pointInitial.y > 0) {
            n = this.pointCourant.x;
            n2 = this.pointInitial.y;
            n3 = this.pointInitial.x - this.pointCourant.x;
            n4 = this.pointCourant.y - this.pointInitial.y;
        }
        else if (this.pointCourant.x - this.pointInitial.x > 0 && this.pointCourant.y - this.pointInitial.y < 0) {
            n = this.pointInitial.x;
            n2 = this.pointCourant.y;
            n3 = this.pointCourant.x - this.pointInitial.x;
            n4 = this.pointInitial.y - this.pointCourant.y;
        }
        else {
            n = this.pointCourant.x;
            n2 = this.pointCourant.y;
            n3 = this.pointInitial.x - this.pointCourant.x;
            n4 = this.pointInitial.y - this.pointCourant.y;
        }
        ++Plan.nombrePlan;
        if (Plan.nombrePlan == 799) {
            --Plan.nombrePlan;
            this.afficheInfo("There is too much layers");
        }
        this.plan[Plan.nombrePlan] = new Plan(s, n, n2, n3, n4, this.palette.epaisseur, this.coulContour, this.coulRemplissage, this.contourTransp, this.remplissageTransp);
    }
    
    public final void enregistreLigne() {
        final int x = this.pointInitial.x;
        final int y = this.pointInitial.y;
        final int n = this.pointCourant.x - this.pointInitial.x;
        final int n2 = this.pointCourant.y - this.pointInitial.y;
        ++Plan.nombrePlan;
        if (Plan.nombrePlan == 799) {
            --Plan.nombrePlan;
            this.afficheInfo("There is too much layers");
        }
        this.plan[Plan.nombrePlan] = new Plan("ligne", x, y, n, n2, this.palette.epaisseur, this.coulContour, this.contourTransp);
    }
    
    public final void enregistrePoint() {
        ++Plan.nombrePlan;
        if (Plan.nombrePlan == 799) {
            --Plan.nombrePlan;
            this.afficheInfo("There is too much layers");
        }
        this.plan[Plan.nombrePlan] = new Plan("point", this.pointInitial.x, this.pointInitial.y, this.palette.epaisseur, this.coulRemplissage, this.remplissageTransp);
    }
    
    public final void enregistreTexte() {
        ++Plan.nombrePlan;
        if (Plan.nombrePlan == 799) {
            --Plan.nombrePlan;
            this.afficheInfo("There is too much layers");
        }
        final String text = this.palette.paletteText.texte.getText();
        final String selectedItem = this.palette.paletteText.styleText.getSelectedItem();
        int n = 0;
        if (selectedItem.equals("Bold")) {
            n = 1;
        }
        else if (selectedItem.equals("Italic")) {
            n = 2;
        }
        else if (selectedItem.equals("Bold-italic")) {
            n = 3;
        }
        final int taille = this.palette.paletteText.taille;
        final FontMetrics fontMetrics = this.getFontMetrics(new Font(this.palette.paletteText.policeText.getSelectedItem(), n, taille));
        this.plan[Plan.nombrePlan] = new Plan("texte", this.pointInitial.x, this.pointInitial.y, fontMetrics.stringWidth(text), fontMetrics.getAscent(), text, this.palette.paletteText.policeText.getSelectedItem(), n, taille, this.coulRemplissage, this.remplissageTransp);
    }
    
    public final void enregistrePolygone() {
        ++Plan.nombrePlan;
        if (Plan.nombrePlan == 799) {
            --Plan.nombrePlan;
            this.afficheInfo("There is too much layers");
        }
        final Rectangle boundingBox = this.polygoneCourant.getBoundingBox();
        this.plan[Plan.nombrePlan] = new Plan("polygone", boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height, this.polygoneCourant, this.coulContour, this.coulRemplissage, this.contourTransp, this.remplissageTransp);
    }
    
    public final void enregistreRect3D() {
        int n;
        int n2;
        int n3;
        int n4;
        if (this.pointCourant.x - this.pointInitial.x > 0 && this.pointCourant.y - this.pointInitial.y > 0) {
            n = this.pointInitial.x;
            n2 = this.pointInitial.y;
            n3 = this.pointCourant.x - this.pointInitial.x;
            n4 = this.pointCourant.y - this.pointInitial.y;
        }
        else if (this.pointCourant.x - this.pointInitial.x < 0 && this.pointCourant.y - this.pointInitial.y > 0) {
            n = this.pointCourant.x;
            n2 = this.pointInitial.y;
            n3 = this.pointInitial.x - this.pointCourant.x;
            n4 = this.pointCourant.y - this.pointInitial.y;
        }
        else if (this.pointCourant.x - this.pointInitial.x > 0 && this.pointCourant.y - this.pointInitial.y < 0) {
            n = this.pointInitial.x;
            n2 = this.pointCourant.y;
            n3 = this.pointCourant.x - this.pointInitial.x;
            n4 = this.pointInitial.y - this.pointCourant.y;
        }
        else {
            n = this.pointCourant.x;
            n2 = this.pointCourant.y;
            n3 = this.pointInitial.x - this.pointCourant.x;
            n4 = this.pointInitial.y - this.pointCourant.y;
        }
        ++Plan.nombrePlan;
        if (Plan.nombrePlan == 799) {
            --Plan.nombrePlan;
            this.afficheInfo("There is too much layers");
        }
        boolean b;
        if (this.palette.bouttonSelectionne.equals("rectangle3D0")) {
            b = false;
        }
        else {
            b = true;
        }
        this.plan[Plan.nombrePlan] = new Plan("rectangle3D", n, n2, n3, n4, (int)(b ? 1 : 0), this.coulContour, this.contourTransp);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void dessineRectDrag() {
        this.offscreenG.setColor(this.coulSelection);
        if (this.palette.bouttonSelectionne.equals("fleche")) {
            this.offscreenG.drawLine(this.pointInitial.x, this.pointInitial.y, this.pointCourant.x, this.pointCourant.y);
            this.offscreenG.setFont(this.infoFont);
            this.offscreenG.drawString("x = " + this.pointCourant.x, 5, 10);
            this.offscreenG.drawString("y = " + this.pointCourant.y, 5, 20);
            return;
        }
        if (this.pointCourant.x - this.pointInitial.x > 0 && this.pointCourant.y - this.pointInitial.y > 0) {
            this.offscreenG.drawRect(this.pointInitial.x, this.pointInitial.y, this.pointCourant.x - this.pointInitial.x, this.pointCourant.y - this.pointInitial.y);
            this.offscreenG.drawOval(this.pointInitial.x, this.pointInitial.y, this.pointCourant.x - this.pointInitial.x, this.pointCourant.y - this.pointInitial.y);
        }
        else if (this.pointCourant.x - this.pointInitial.x < 0 && this.pointCourant.y - this.pointInitial.y > 0) {
            this.offscreenG.drawRect(this.pointCourant.x, this.pointInitial.y, this.pointInitial.x - this.pointCourant.x, this.pointCourant.y - this.pointInitial.y);
            this.offscreenG.drawOval(this.pointCourant.x, this.pointInitial.y, this.pointInitial.x - this.pointCourant.x, this.pointCourant.y - this.pointInitial.y);
        }
        else if (this.pointCourant.x - this.pointInitial.x > 0 && this.pointCourant.y - this.pointInitial.y < 0) {
            this.offscreenG.drawRect(this.pointInitial.x, this.pointCourant.y, this.pointCourant.x - this.pointInitial.x, this.pointInitial.y - this.pointCourant.y);
            this.offscreenG.drawOval(this.pointInitial.x, this.pointCourant.y, this.pointCourant.x - this.pointInitial.x, this.pointInitial.y - this.pointCourant.y);
        }
        else {
            this.offscreenG.drawRect(this.pointCourant.x, this.pointCourant.y, this.pointInitial.x - this.pointCourant.x, this.pointInitial.y - this.pointCourant.y);
            this.offscreenG.drawOval(this.pointCourant.x, this.pointCourant.y, this.pointInitial.x - this.pointCourant.x, this.pointInitial.y - this.pointCourant.y);
        }
        this.offscreenG.drawLine(this.pointInitial.x, this.pointInitial.y, this.pointCourant.x, this.pointCourant.y);
        this.offscreenG.drawLine(this.pointInitial.x, this.pointCourant.y, this.pointCourant.x, this.pointInitial.y);
        this.offscreenG.setFont(this.infoFont);
        this.offscreenG.drawString("width = " + (this.pointCourant.x - this.pointInitial.x), 5, 10);
        this.offscreenG.drawString("height = " + (this.pointCourant.y - this.pointInitial.y), 5, 20);
    }
    
    public final void dessineDroitesPoly() {
        this.offscreenG.setColor(this.coulSelection);
        this.offscreenG.fillRect(this.pointInitialPoly.x - 2, this.pointInitialPoly.y - 2, 5, 5);
        this.offscreenG.drawPolygon(this.polygoneCourant);
        this.offscreenG.setFont(this.infoFont);
        this.offscreenG.drawString("x = " + this.pointCourant.x, 5, 10);
        this.offscreenG.drawString("y = " + this.pointCourant.y, 5, 20);
    }
    
    public final void dessinePlans() {
        this.offscreenG.setColor(this.coulFond);
        this.offscreenG.fillRect(0, 0, this.width, this.height);
        for (int i = 0; i <= Plan.nombrePlan; ++i) {
            if (!this.plan[i].type.equals("rien")) {
                if (this.plan[i].type.equals("rectangle")) {
                    if (!this.plan[i].remplissageTransp) {
                        this.offscreenG.setColor(this.plan[i].couleurRemplissage);
                        this.offscreenG.fillRect(this.plan[i].x1, this.plan[i].y1, this.plan[i].largeur, this.plan[i].hauteur);
                    }
                    if (!this.plan[i].contourTransp) {
                        this.offscreenG.setColor(this.plan[i].couleurContour);
                        final int n = this.plan[i].epaisseur / 2;
                        for (int j = 0; j < this.plan[i].epaisseur; ++j) {
                            this.offscreenG.drawRect(this.plan[i].x1 + n - j, this.plan[i].y1 + n - j, this.plan[i].largeur - 2 * n + 2 * j, this.plan[i].hauteur - 2 * n + 2 * j);
                        }
                    }
                }
                else if (this.plan[i].type.equals("ovale")) {
                    if (!this.plan[i].remplissageTransp) {
                        this.offscreenG.setColor(this.plan[i].couleurRemplissage);
                        this.offscreenG.fillOval(this.plan[i].x1, this.plan[i].y1, this.plan[i].largeur, this.plan[i].hauteur);
                    }
                    if (!this.plan[i].contourTransp) {
                        this.offscreenG.setColor(this.plan[i].couleurContour);
                        final int n2 = this.plan[i].epaisseur / 2;
                        for (int k = 0; k < this.plan[i].epaisseur; ++k) {
                            this.offscreenG.drawOval(this.plan[i].x1 + n2 - k, this.plan[i].y1 + n2 - k, this.plan[i].largeur - 2 * n2 + 2 * k, this.plan[i].hauteur - 2 * n2 + 2 * k);
                        }
                    }
                }
                else if (this.plan[i].type.equals("ligne")) {
                    if (!this.plan[i].contourTransp) {
                        this.offscreenG.setColor(this.plan[i].couleurContour);
                        this.offscreenG.drawLine(this.plan[i].x1, this.plan[i].y1, this.plan[i].x1 + this.plan[i].largeur, this.plan[i].y1 + this.plan[i].hauteur);
                        if (this.plan[i].epaisseur > 1) {
                            final int n3 = this.plan[i].epaisseur / 2;
                            float n4 = 999.0f;
                            if (this.plan[i].largeur != 0) {
                                n4 = this.plan[i].hauteur / this.plan[i].largeur;
                            }
                            if (Math.abs(n4) >= 1.0f) {
                                for (int l = 0; l < this.plan[i].epaisseur; ++l) {
                                    this.offscreenG.drawLine(this.plan[i].x1 - n3 + l, this.plan[i].y1, this.plan[i].x1 + this.plan[i].largeur - n3 + l, this.plan[i].y1 + this.plan[i].hauteur);
                                }
                            }
                            else {
                                for (int n5 = 0; n5 < this.plan[i].epaisseur; ++n5) {
                                    this.offscreenG.drawLine(this.plan[i].x1, this.plan[i].y1 - n3 + n5, this.plan[i].x1 + this.plan[i].largeur, this.plan[i].y1 + this.plan[i].hauteur - n3 + n5);
                                }
                            }
                        }
                    }
                }
                else if (this.plan[i].type.equals("point")) {
                    if (!this.plan[i].remplissageTransp) {
                        final int n6 = this.plan[i].epaisseur / 2;
                        this.offscreenG.setColor(this.plan[i].couleurRemplissage);
                        this.offscreenG.fillOval(this.plan[i].x1 - n6, this.plan[i].y1 - n6, this.plan[i].epaisseur, this.plan[i].epaisseur);
                    }
                }
                else if (this.plan[i].type.equals("texte")) {
                    if (!this.plan[i].remplissageTransp) {
                        this.offscreenG.setFont(new Font(this.plan[i].police, this.plan[i].style, this.plan[i].taille));
                        this.offscreenG.setColor(this.plan[i].couleurRemplissage);
                        this.offscreenG.drawString(this.plan[i].texte, this.plan[i].x1, this.plan[i].y1 + this.plan[i].hauteur);
                    }
                }
                else if (this.plan[i].type.equals("polygone")) {
                    if (!this.plan[i].remplissageTransp) {
                        this.offscreenG.setColor(this.plan[i].couleurRemplissage);
                        this.offscreenG.fillPolygon(this.plan[i].polygone);
                    }
                    if (!this.plan[i].contourTransp) {
                        this.offscreenG.setColor(this.plan[i].couleurContour);
                        this.offscreenG.drawPolygon(this.plan[i].polygone);
                    }
                }
                else if (this.plan[i].type.equals("rectangle3D") && !this.plan[i].contourTransp) {
                    final boolean b = this.plan[i].epaisseur != 0;
                    this.offscreenG.setColor(this.plan[i].couleurContour);
                    this.offscreenG.draw3DRect(this.plan[i].x1, this.plan[i].y1, this.plan[i].largeur, this.plan[i].hauteur, b);
                }
            }
        }
    }
    
    public final void paint(final Graphics graphics) {
        if (this.offscreenImg == null) {
            final Dimension size = this.size();
            this.offscreenImg = this.createImage(size.width, size.height);
            this.offscreenG = this.offscreenImg.getGraphics();
        }
        graphics.drawImage(this.offscreenImg, 0, 0, this);
    }
    
    public void invalidate() {
        super.invalidate();
        this.offscreenImg = null;
    }
    
    public void destroy() {
        ((Component)this.palette).hide();
        ((Component)this.palette.paletteCoulContour).hide();
        ((Component)this.palette.paletteCoulRemplissage).hide();
        ((Component)this.palette.paletteCoulFond).hide();
        ((Component)this.palette.paletteText).hide();
    }
    
    public final String genereCode() {
        int n = 0;
        String s = "\n\n" + "import java.awt.*;\n\n" + "// Save this text under the name : MyDrawing.java\n" + "// then compile it with : javac MyDrawing.java\n" + "// and create an HTML file that show the applet\n\n" + "public class MyDrawing extends java.applet.Applet\n" + "{\n" + "\t// The text police\n" + "\tFont f;\n\n" + "\tpublic void init()\n" + "\t{\n" + "\t\tsetBackground(new Color(" + this.coulFond.getRed() + ", " + this.coulFond.getGreen() + ", " + this.coulFond.getBlue() + "));\n" + "\t}\n\n" + "\tpublic void paint(Graphics g)\n" + "\t{\n";
        for (int i = 0; i <= Plan.nombrePlan; ++i) {
            if (!this.plan[i].type.equals("rien")) {
                if (this.plan[i].type.equals("rectangle")) {
                    if (!this.plan[i].remplissageTransp) {
                        s = s + "\t\tg.setColor(new Color(" + this.plan[i].couleurRemplissage.getRed() + ", " + this.plan[i].couleurRemplissage.getGreen() + ", " + this.plan[i].couleurRemplissage.getBlue() + "));\n" + "\t\tg.fillRect(" + this.plan[i].x1 + ", " + this.plan[i].y1 + ", " + this.plan[i].largeur + ", " + this.plan[i].hauteur + ");\n";
                    }
                    if (!this.plan[i].contourTransp) {
                        s = s + "\t\tg.setColor(new Color(" + this.plan[i].couleurContour.getRed() + ", " + this.plan[i].couleurContour.getGreen() + ", " + this.plan[i].couleurContour.getBlue() + "));\n";
                        final int n2 = this.plan[i].epaisseur / 2;
                        for (int j = 0; j < this.plan[i].epaisseur; ++j) {
                            s = s + "\t\tg.drawRect(" + (this.plan[i].x1 + n2 - j) + ", " + (this.plan[i].y1 + n2 - j) + ", " + (this.plan[i].largeur - 2 * n2 + 2 * j) + ", " + (this.plan[i].hauteur - 2 * n2 + 2 * j) + ");\n";
                        }
                    }
                }
                else if (this.plan[i].type.equals("ovale")) {
                    if (!this.plan[i].remplissageTransp) {
                        s = s + "\t\tg.setColor(new Color(" + this.plan[i].couleurRemplissage.getRed() + ", " + this.plan[i].couleurRemplissage.getGreen() + ", " + this.plan[i].couleurRemplissage.getBlue() + "));\n" + "\t\tg.fillOval(" + this.plan[i].x1 + ", " + this.plan[i].y1 + ", " + this.plan[i].largeur + ", " + this.plan[i].hauteur + ");\n";
                    }
                    if (!this.plan[i].contourTransp) {
                        s = s + "\t\tg.setColor(new Color(" + this.plan[i].couleurContour.getRed() + ", " + this.plan[i].couleurContour.getGreen() + ", " + this.plan[i].couleurContour.getBlue() + "));\n";
                        final int n3 = this.plan[i].epaisseur / 2;
                        for (int k = 0; k < this.plan[i].epaisseur; ++k) {
                            s = s + "\t\tg.drawOval(" + (this.plan[i].x1 + n3 - k) + ", " + (this.plan[i].y1 + n3 - k) + ", " + (this.plan[i].largeur - 2 * n3 + 2 * k) + ", " + (this.plan[i].hauteur - 2 * n3 + 2 * k) + ");\n";
                        }
                    }
                }
                else if (this.plan[i].type.equals("ligne")) {
                    if (!this.plan[i].contourTransp) {
                        s = s + "\t\tg.setColor(new Color(" + this.plan[i].couleurContour.getRed() + ", " + this.plan[i].couleurContour.getGreen() + ", " + this.plan[i].couleurContour.getBlue() + "));\n" + "\t\tg.drawLine(" + this.plan[i].x1 + ", " + this.plan[i].y1 + ", " + (this.plan[i].x1 + this.plan[i].largeur) + ", " + (this.plan[i].y1 + this.plan[i].hauteur) + ");\n";
                        if (this.plan[i].epaisseur > 1) {
                            final int n4 = this.plan[i].epaisseur / 2;
                            float n5 = 999.0f;
                            if (this.plan[i].largeur != 0) {
                                n5 = this.plan[i].hauteur / this.plan[i].largeur;
                            }
                            if (Math.abs(n5) >= 1.0f) {
                                for (int l = 0; l < this.plan[i].epaisseur; ++l) {
                                    s = s + "\t\tg.drawLine(" + (this.plan[i].x1 - n4 + l) + ", " + this.plan[i].y1 + ", " + (this.plan[i].x1 + this.plan[i].largeur - n4 + l) + ", " + (this.plan[i].y1 + this.plan[i].hauteur) + ");\n";
                                }
                            }
                            else {
                                for (int n6 = 0; n6 < this.plan[i].epaisseur; ++n6) {
                                    s = s + "\t\tg.drawLine(" + this.plan[i].x1 + ", " + (this.plan[i].y1 - n4 + n6) + ", " + (this.plan[i].x1 + this.plan[i].largeur) + ", " + (this.plan[i].y1 + this.plan[i].hauteur - n4 + n6) + ");\n";
                                }
                            }
                        }
                    }
                }
                else if (this.plan[i].type.equals("point")) {
                    if (!this.plan[i].remplissageTransp) {
                        final int n7 = this.plan[i].epaisseur / 2;
                        s = s + "\t\tg.setColor(new Color(" + this.plan[i].couleurRemplissage.getRed() + ", " + this.plan[i].couleurRemplissage.getGreen() + ", " + this.plan[i].couleurRemplissage.getBlue() + "));\n" + "\t\tg.fillOval(" + (this.plan[i].x1 - n7) + ", " + (this.plan[i].y1 - n7) + ", " + this.plan[i].epaisseur + ", " + this.plan[i].epaisseur + ");\n";
                    }
                }
                else if (this.plan[i].type.equals("polygone")) {
                    final int npoints = this.plan[i].polygone.npoints;
                    ++n;
                    String s2 = s + "\t\tint xPoints" + n + "[] = { ";
                    for (int n8 = 0; n8 < npoints - 1; ++n8) {
                        s2 = s2 + this.plan[i].polygone.xpoints[n8] + ", ";
                    }
                    String s3 = s2 + this.plan[i].polygone.xpoints[npoints - 1] + " };\n" + "\t\tint yPoints" + n + "[] = { ";
                    for (int n9 = 0; n9 < npoints - 1; ++n9) {
                        s3 = s3 + this.plan[i].polygone.ypoints[n9] + ", ";
                    }
                    s = s3 + this.plan[i].polygone.ypoints[npoints - 1] + " };\n";
                    if (!this.plan[i].remplissageTransp) {
                        s = s + "\t\tg.setColor(new Color(" + this.plan[i].couleurRemplissage.getRed() + ", " + this.plan[i].couleurRemplissage.getGreen() + ", " + this.plan[i].couleurRemplissage.getBlue() + "));\n" + "\t\tg.fillPolygon(xPoints" + n + ", yPoints" + n + ", " + npoints + ");\n";
                    }
                    if (!this.plan[i].contourTransp) {
                        s = s + "\t\tg.setColor(new Color(" + this.plan[i].couleurContour.getRed() + ", " + this.plan[i].couleurContour.getGreen() + ", " + this.plan[i].couleurContour.getBlue() + "));\n" + "\t\tg.drawPolygon(xPoints" + n + ", yPoints" + n + ", " + npoints + ");\n";
                    }
                }
                else if (this.plan[i].type.equals("texte")) {
                    if (!this.plan[i].remplissageTransp) {
                        s = s + "\t\tf = new Font(\"" + this.plan[i].police + "\", " + this.plan[i].style + ", " + this.plan[i].taille + ");\n" + "\t\tg.setFont(f);\n" + "\t\tg.setColor(new Color(" + this.plan[i].couleurRemplissage.getRed() + ", " + this.plan[i].couleurRemplissage.getGreen() + ", " + this.plan[i].couleurRemplissage.getBlue() + "));\n" + "\t\tg.drawString(\"" + this.plan[i].texte + "\", " + this.plan[i].x1 + ", " + (this.plan[i].y1 + this.plan[i].hauteur) + ");\n";
                    }
                }
                else if (this.plan[i].type.equals("rectangle3D") && !this.plan[i].contourTransp) {
                    s = s + "\t\tg.setColor(new Color(" + this.plan[i].couleurContour.getRed() + ", " + this.plan[i].couleurContour.getGreen() + ", " + this.plan[i].couleurContour.getBlue() + "));\n" + "\t\tg.draw3DRect(" + this.plan[i].x1 + ", " + this.plan[i].y1 + ", " + this.plan[i].largeur + ", " + this.plan[i].hauteur + ", " + (this.plan[i].epaisseur != 0) + ");\n";
                }
            }
        }
        return s + "\t}\n" + "}\n\n" + "// That's all !  ;-)\n" + "// Generated by JavaDraw, version 1.0\n" + "// http://www.nctech.fr/Zimmermann\n\n\n";
    }
    
    public final void afficheCode() {
        final String genereCode = this.genereCode();
        this.fenetreCode = new FenetreCode(" Java code !");
        this.fenetreCode.leCode.insertText(genereCode, 0);
        this.fenetreCode.move(45, 45);
        this.fenetreCode.toFront();
        this.fenetreCode.show();
        this.fenetreCode.ouverte = true;
    }
    
    public final void enregistreCode() {
        if (!this.isApplet) {
            final FileDialog fileDialog = new FileDialog((Frame)this.frame, "Enregistrer code...", 1);
            fileDialog.show();
            this.afficheInfo("Save the code...");
            final String string = fileDialog.getDirectory() + "/" + fileDialog.getFile();
            final String genereCode = this.genereCode();
            final File file = new File(string);
            try {
                new DataOutputStream(new FileOutputStream(file)).writeBytes(genereCode);
            }
            catch (IOException ex) {
                if (!ex.getMessage().equals("null/null")) {
                    this.afficheInfo("Error during file saving : " + ex.getMessage());
                    return;
                }
                this.afficheInfo("Save cancel.");
            }
            catch (Exception ex2) {
                this.afficheInfo("Error !!! : " + ex2.getMessage());
            }
        }
    }
    
    public final void enregistre() {
        if (!this.isApplet) {
            final FileDialog fileDialog = new FileDialog((Frame)this.frame, "Save as...", 1);
            fileDialog.show();
            this.afficheInfo("Save the JavaDraw drawing...");
            this.nomFichier = fileDialog.getDirectory();
            this.nomFichier = this.nomFichier + "/" + fileDialog.getFile();
            final File file = new File(this.nomFichier);
            try {
                final DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
                dataOutputStream.writeBytes("JavaDraw 1.0.5 file, march 1998 |" + this.coulFond.getRed() + "," + this.coulFond.getGreen() + "," + this.coulFond.getBlue() + "\n");
                for (int i = 0; i <= Plan.nombrePlan; ++i) {
                    if (!this.plan[i].type.equals("rien")) {
                        dataOutputStream.writeBytes(this.plan[i].toString());
                    }
                }
                ((Frame)this.frame).setTitle(" JavaDraw : " + fileDialog.getFile());
            }
            catch (IOException ex) {
                if (!ex.getMessage().equals("null/null")) {
                    this.afficheInfo("Error during file saving : " + ex.getMessage());
                    return;
                }
                this.afficheInfo("Save cancel.");
            }
            catch (Exception ex2) {
                this.afficheInfo("Error !!! : " + ex2.getMessage());
            }
        }
    }
    
    public final void enregistre2() {
        if (!this.isApplet && !this.nomFichier.equals("")) {
            this.afficheInfo("Save the JavaDraw drawing...");
            final File file = new File(this.nomFichier);
            try {
                final DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
                dataOutputStream.writeBytes("JavaDraw 1.0.5 file, march 1998 |" + this.coulFond.getRed() + "," + this.coulFond.getGreen() + "," + this.coulFond.getBlue() + "\n");
                for (int i = 0; i <= Plan.nombrePlan; ++i) {
                    if (!this.plan[i].type.equals("rien")) {
                        dataOutputStream.writeBytes(this.plan[i].toString());
                    }
                }
            }
            catch (IOException ex) {
                if (!ex.getMessage().equals("null/null")) {
                    this.afficheInfo("Erreur pendant l'enregistrement du fichier : " + ex.getMessage());
                    return;
                }
                this.afficheInfo("Enregistrement annule.");
            }
            catch (Exception ex2) {
                this.afficheInfo("Erreur !!! : " + ex2.getMessage());
            }
        }
    }
    
    public final void ouvre() {
        if (!this.isApplet) {
            final FileDialog fileDialog = new FileDialog((Frame)this.frame, "Open...", 0);
            fileDialog.show();
            this.afficheInfo("Open JavaDraw drawing...");
            this.nomFichier = fileDialog.getDirectory();
            this.nomFichier = this.nomFichier + "/" + fileDialog.getFile();
            final String nomFichier = this.nomFichier;
            final File file = new File(this.nomFichier);
            try {
                final DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
                this.effacePlans();
                this.recupCouleurFond(dataInputStream.readLine());
                String line;
                while ((line = dataInputStream.readLine()) != null) {
                    this.ajoutePlanFichier(line);
                }
                ((Frame)this.frame).setTitle(" JavaDraw : " + fileDialog.getFile());
                this.nomFichier = nomFichier;
                this.miseAJourCouleur();
                this.dessinePlans();
                this.repaint();
            }
            catch (IOException ex) {
                if (!ex.getMessage().equals("null/null")) {
                    this.afficheInfo("Error during file loading : " + ex.getMessage());
                    return;
                }
                this.afficheInfo("Open cancel.");
            }
            catch (Exception ex2) {
                this.afficheInfo("Error !!! : " + ex2.getMessage());
            }
        }
    }
    
    public final void afficheInfo(final String s) {
        this.dessinePlans();
        this.selectObjet();
        int n;
        int n2;
        if (!this.isApplet) {
            n = ((Frame)this.frame).size().width;
            n2 = ((Frame)this.frame).size().height;
        }
        else {
            n = this.size().width;
            n2 = this.size().height;
        }
        final FontMetrics fontMetrics = this.getFontMetrics(this.infoFont);
        final int n3 = (n - fontMetrics.stringWidth(s)) / 2;
        final int n4 = (n2 - fontMetrics.getHeight()) / 2;
        this.offscreenG.setColor(Color.black);
        this.offscreenG.fillRect(n3 - 5 + 3, n4 - 5 - fontMetrics.getHeight() + 3, fontMetrics.stringWidth(s) + 10, fontMetrics.getHeight() + 10);
        this.offscreenG.setColor(Color.pink);
        this.offscreenG.fillRect(n3 - 5, n4 - 5 - fontMetrics.getHeight(), fontMetrics.stringWidth(s) + 10, fontMetrics.getHeight() + 10);
        this.offscreenG.setColor(Color.black);
        this.offscreenG.drawRect(n3 - 5, n4 - 5 - fontMetrics.getHeight(), fontMetrics.stringWidth(s) + 10, fontMetrics.getHeight() + 10);
        this.offscreenG.setFont(this.infoFont);
        this.offscreenG.setColor(Color.white);
        this.offscreenG.drawString(s, n3 + 1, n4 - 1);
        this.offscreenG.setColor(Color.red);
        this.offscreenG.drawString(s, n3, n4 - 2);
        this.repaint();
    }
    
    public final void effacePlans() {
        for (int i = 0; i <= Plan.nombrePlan; ++i) {
            this.plan[i] = new Plan("rien");
        }
        this.planSelectionne = 0;
        Plan.nombrePlan = 0;
        if (!this.isApplet) {
            ((Frame)this.frame).setTitle(" JavaDraw");
        }
        this.nomFichier = "";
    }
    
    public final void recupCouleurFond(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "|");
        try {
            stringTokenizer.nextToken();
            final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), ",");
            final Integer n = new Integer(stringTokenizer2.nextToken());
            final Integer n2 = new Integer(stringTokenizer2.nextToken());
            final Integer n3 = new Integer(stringTokenizer2.nextToken());
            this.coulFond = new Color(n, n2, n3);
            this.palette.paletteCoulFond.couleur = this.coulFond;
            this.palette.coulFond = this.coulFond;
            ((Component)this.palette.paletteCoulFond).hide();
            this.palette.paletteCoulFond.ouverte = false;
            if (this.coulFond.equals(Color.white)) {
                this.palette.couleurFond.select("Background White");
                return;
            }
            if (this.coulFond.equals(Color.black)) {
                this.palette.couleurFond.select("Background Black");
                return;
            }
            if (this.coulFond.equals(Color.lightGray)) {
                this.palette.couleurFond.select("Background Light gray");
                return;
            }
            if (this.coulFond.equals(Color.gray)) {
                this.palette.couleurFond.select("Background Gray");
                return;
            }
            if (this.coulFond.equals(Color.darkGray)) {
                this.palette.couleurFond.select("Background Dark gray");
                return;
            }
            if (this.coulFond.equals(Color.red)) {
                this.palette.couleurFond.select("Background Red");
                return;
            }
            if (this.coulFond.equals(Color.green)) {
                this.palette.couleurFond.select("Background Green");
                return;
            }
            if (this.coulFond.equals(Color.blue)) {
                this.palette.couleurFond.select("Background Blue");
                return;
            }
            if (this.coulFond.equals(Color.yellow)) {
                this.palette.couleurFond.select("Background Yellow");
                return;
            }
            if (this.coulFond.equals(Color.magenta)) {
                this.palette.couleurFond.select("Background Magenta");
                return;
            }
            if (this.coulFond.equals(Color.cyan)) {
                this.palette.couleurFond.select("Background Cyan");
                return;
            }
            if (this.coulFond.equals(Color.pink)) {
                this.palette.couleurFond.select("Background Pink");
                return;
            }
            if (this.coulFond.equals(Color.orange)) {
                this.palette.couleurFond.select("Background Orange");
                return;
            }
            this.palette.couleurFond.select("Background RGB...");
            this.palette.paletteCoulFond.Rouge.setText(n.toString());
            this.palette.paletteCoulFond.Vert.setText(n2.toString());
            this.palette.paletteCoulFond.Bleu.setText(n3.toString());
            final PaletteRVB paletteCoulFond = this.palette.paletteCoulFond;
            paletteCoulFond.verifChamps();
            paletteCoulFond.panel.setBackground(paletteCoulFond.couleur);
            paletteCoulFond.panel.repaint();
            if (this.palette.premiereOuvertureCoulFond) {
                ((Component)this.palette.paletteCoulFond).move(200, 45);
                this.palette.premiereOuvertureCoulFond = false;
            }
            ((Window)this.palette.paletteCoulFond).toFront();
            ((Window)this.palette.paletteCoulFond).show();
            this.palette.paletteCoulFond.ouverte = true;
        }
        catch (Exception ex) {}
    }
    
    public final void ajoutePlanFichier(final String s) {
        try {
            ++Plan.nombrePlan;
            if (Plan.nombrePlan == 799) {
                --Plan.nombrePlan;
                this.afficheInfo("There is too much layers");
            }
            this.plan[Plan.nombrePlan] = new Plan("rien");
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "|");
            if (stringTokenizer.countTokens() != 15) {
                return;
            }
            String nextToken;
            try {
                nextToken = stringTokenizer.nextToken();
            }
            catch (Exception ex) {
                nextToken = "rien";
            }
            Integer n;
            try {
                n = new Integer(stringTokenizer.nextToken());
            }
            catch (Exception ex2) {
                n = new Integer(0);
            }
            Integer n2;
            try {
                n2 = new Integer(stringTokenizer.nextToken());
            }
            catch (Exception ex3) {
                n2 = new Integer(0);
            }
            Integer n3;
            try {
                n3 = new Integer(stringTokenizer.nextToken());
            }
            catch (Exception ex4) {
                n3 = new Integer(0);
            }
            Integer n4;
            try {
                n4 = new Integer(stringTokenizer.nextToken());
            }
            catch (Exception ex5) {
                n4 = new Integer(0);
            }
            Integer n5;
            try {
                n5 = new Integer(stringTokenizer.nextToken());
            }
            catch (Exception ex6) {
                n5 = new Integer(0);
            }
            Color black;
            try {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), ",");
                black = new Color(new Integer(stringTokenizer2.nextToken()), new Integer(stringTokenizer2.nextToken()), new Integer(stringTokenizer2.nextToken()));
            }
            catch (Exception ex7) {
                black = Color.black;
            }
            Color white;
            try {
                final StringTokenizer stringTokenizer3 = new StringTokenizer(stringTokenizer.nextToken(), ",");
                white = new Color(new Integer(stringTokenizer3.nextToken()), new Integer(stringTokenizer3.nextToken()), new Integer(stringTokenizer3.nextToken()));
            }
            catch (Exception ex8) {
                white = Color.white;
            }
            boolean contourTransp;
            try {
                final String nextToken2 = stringTokenizer.nextToken();
                contourTransp = true;
                if (nextToken2.equals("false")) {
                    contourTransp = false;
                }
            }
            catch (Exception ex9) {
                contourTransp = false;
            }
            boolean remplissageTransp;
            try {
                final String nextToken3 = stringTokenizer.nextToken();
                remplissageTransp = true;
                if (nextToken3.equals("false")) {
                    remplissageTransp = false;
                }
            }
            catch (Exception ex10) {
                remplissageTransp = false;
            }
            String nextToken4;
            try {
                nextToken4 = stringTokenizer.nextToken();
            }
            catch (Exception ex11) {
                nextToken4 = "";
            }
            String nextToken5;
            try {
                nextToken5 = stringTokenizer.nextToken();
            }
            catch (Exception ex12) {
                nextToken5 = "Courier";
            }
            Integer n6;
            try {
                n6 = new Integer(stringTokenizer.nextToken());
            }
            catch (Exception ex13) {
                n6 = new Integer(0);
            }
            Integer n7;
            try {
                n7 = new Integer(stringTokenizer.nextToken());
            }
            catch (Exception ex14) {
                n7 = new Integer(10);
            }
            Polygon polygone = null;
            try {
                final String nextToken6 = stringTokenizer.nextToken();
                if (!nextToken6.equals("null")) {
                    final StringTokenizer stringTokenizer4 = new StringTokenizer(nextToken6, "-");
                    final String nextToken7 = stringTokenizer4.nextToken();
                    final String nextToken8 = stringTokenizer4.nextToken();
                    final StringTokenizer stringTokenizer5 = new StringTokenizer(nextToken7, ",");
                    final StringTokenizer stringTokenizer6 = new StringTokenizer(nextToken8, ",");
                    polygone = new Polygon();
                    final Integer n8 = new Integer(0);
                    final Integer n9 = new Integer(0);
                    while (stringTokenizer5.hasMoreTokens()) {
                        if (!stringTokenizer6.hasMoreTokens()) {
                            break;
                        }
                        polygone.addPoint(new Integer(stringTokenizer5.nextToken()), new Integer(stringTokenizer6.nextToken()));
                    }
                }
            }
            catch (Exception ex15) {
                polygone = null;
                nextToken = "rien";
            }
            this.plan[Plan.nombrePlan].type = nextToken;
            this.plan[Plan.nombrePlan].x1 = n;
            this.plan[Plan.nombrePlan].y1 = n2;
            this.plan[Plan.nombrePlan].largeur = n3;
            this.plan[Plan.nombrePlan].hauteur = n4;
            this.plan[Plan.nombrePlan].epaisseur = n5;
            this.plan[Plan.nombrePlan].couleurContour = black;
            this.plan[Plan.nombrePlan].couleurRemplissage = white;
            this.plan[Plan.nombrePlan].contourTransp = contourTransp;
            this.plan[Plan.nombrePlan].remplissageTransp = remplissageTransp;
            this.plan[Plan.nombrePlan].texte = nextToken4;
            this.plan[Plan.nombrePlan].police = nextToken5;
            this.plan[Plan.nombrePlan].style = n6;
            this.plan[Plan.nombrePlan].taille = n7;
            this.plan[Plan.nombrePlan].polygone = polygone;
        }
        catch (Exception ex16) {}
    }
    
    public String getAppletInfo() {
        return "JavaDraw, version 1.0.5, march 1998. By Vincent Zimmermann at NCTech, France";
    }
    
    public JavaDraw() {
        this.coulFond = Color.white;
        this.coulContour = Color.black;
        this.coulRemplissage = Color.white;
        this.coulSelection = Color.blue;
        this.infoFont = new Font("Dialog", 0, 10);
        this.contourTransp = false;
        this.remplissageTransp = false;
        this.pointInitial = new Point(0, 0);
        this.pointCourant = new Point(0, 0);
        this.pointFinal = new Point(0, 0);
        this.nouveauPoly = true;
        this.plan = new Plan[800];
        this.isApplet = true;
        this.nomFichier = "";
    }
}
