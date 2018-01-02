// 
// Decompiled by Procyon v0.5.30
// 

package ticker;

import java.awt.Event;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Font;
import java.util.Hashtable;
import java.awt.Image;
import java.awt.Canvas;

public class Defile extends Canvas implements Runnable
{
    public int[] TAILLE_CASES_MAX;
    public int ESPACE_INTER;
    Image iBuffer;
    Hashtable hCasesTitres;
    TitresDataBase database;
    int posX;
    int indice;
    int vitesse;
    int periode;
    boolean on_tourne;
    Thread thread;
    Font[] fonte;
    
    public Defile(final TitresDataBase database) {
        this.TAILLE_CASES_MAX = new int[3];
        this.hCasesTitres = new Hashtable();
        this.on_tourne = true;
        this.fonte = new Font[] { new Font("Helvetica", 1, 11), new Font("Helvetica", 1, 11), new Font("Helvetica", 1, 11), new Font("Helvetica", 1, 11) };
        this.vitesse = Parametres.getParamInt("vitesse");
        this.periode = Parametres.getParamInt("periode");
        this.ESPACE_INTER = Parametres.getParamInt("espace");
        this.TAILLE_CASES_MAX[0] = Parametres.getParamInt("TAILLE_CASE1");
        this.TAILLE_CASES_MAX[1] = Parametres.getParamInt("TAILLE_CASE2");
        this.TAILLE_CASES_MAX[2] = Parametres.getParamInt("TAILLE_CASE3");
        this.database = database;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.iBuffer != null) {
            graphics.drawImage(this.iBuffer, 0, 0, this);
        }
        else {
            this.init();
        }
    }
    
    public void suspend() {
        if (this.thread != null) {
            this.thread.suspend();
        }
    }
    
    public void resume() {
        if (this.thread != null) {
            this.thread.resume();
        }
    }
    
    void init() {
        this.iBuffer = this.creerImage(this.size().width, this.size().height);
        this.posX = 0;
        this.indice = 0;
        this.updateImage();
    }
    
    public void start() {
        this.on_tourne = true;
        if (this.thread != null) {
            this.thread.stop();
        }
        (this.thread = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.thread != null) {
            this.on_tourne = false;
            this.thread.stop();
            this.thread = null;
        }
    }
    
    public void run() {
        int n = 0;
        try {
            this.updateImage();
            if (this.database.getTitres() != null && this.indice < this.database.getTitres().size()) {
                final ImageCases imageCases = this.hCasesTitres.get(this.database.getTitres().elementAt(this.indice));
                if (imageCases == null) {
                    n = 0;
                }
                else {
                    n = imageCases.image.getWidth(this);
                }
            }
        }
        catch (Exception ex) {
            System.err.println("Defile.run(), Exception : " + ex);
            ex.printStackTrace(System.err);
        }
        while (this.on_tourne) {
            try {
                this.updateImage();
                if (this.posX + n <= 0) {
                    this.posX += n + this.ESPACE_INTER;
                    if (this.database != null && this.database.getTitres() != null) {
                        final int size = this.database.getTitres().size();
                        if (size != 0) {
                            this.indice = (this.indice + 1) % size;
                            if (this.indice < size) {
                                final ImageCases imageCases2 = this.hCasesTitres.get(this.database.getTitres().elementAt(this.indice));
                                if (imageCases2 == null) {
                                    n = 0;
                                }
                                else {
                                    n = imageCases2.image.getWidth(this);
                                }
                            }
                        }
                    }
                }
                this.posX -= this.vitesse;
            }
            catch (Exception ex2) {
                System.err.println("Defile.run(), Exception 2 : " + ex2);
                ex2.printStackTrace(System.err);
            }
            pause(this.periode);
        }
    }
    
    Image creerImage(final int n, final int n2) {
        return this.createImage(n, n2);
    }
    
    public void redimensionne(final int n) {
        if (this.iBuffer != null) {
            this.iBuffer = this.creerImage(n, this.size().height);
            this.resize(n, this.size().height);
            this.updateImage();
        }
    }
    
    void updateImage() {
        if (this.iBuffer == null) {
            return;
        }
        final Graphics graphics = this.iBuffer.getGraphics();
        if (graphics == null) {
            return;
        }
        graphics.setColor(Parametres.getParamCol("COL_FOND"));
        graphics.fillRect(0, 0, this.iBuffer.getWidth(this), this.iBuffer.getHeight(this));
        final int n = this.iBuffer.getHeight(this) - 3;
        int indice = this.indice;
        int size = 0;
        if (this.database != null) {
            size = this.database.getTitres().size();
        }
        final TextLine[] array = new TextLine[3];
        if (size > 0) {
            ImageCases imageCases;
            for (int i = this.posX; i < this.iBuffer.getWidth(this); i = i + imageCases.image.getWidth(this) + this.ESPACE_INTER) {
                final Titre titre = this.database.getTitres().elementAt(indice);
                indice = (indice + 1) % size;
                array[0] = new TextLine(titre.getNom(), this.fonte[0], Parametres.getParamCol("COL_CASE1_TEXT"), 1);
                array[1] = new TextLine(titre.getValeurStr(), this.fonte[1], Parametres.getParamCol("COL_CASE2_TEXT"), 0);
                final String[] pourcentage = titre.getPourcentage();
                String s;
                Color color;
                if (titre.getValeur() < titre.getExValeur()) {
                    s = pourcentage[0] + pourcentage[1] + "." + pourcentage[2] + "%";
                    color = Parametres.getParamCol("COL_CASE3_TEXT_MINUS");
                }
                else if (titre.getValeur() > titre.getExValeur()) {
                    s = pourcentage[0] + pourcentage[1] + "." + pourcentage[2] + "%";
                    color = Parametres.getParamCol("COL_CASE3_TEXT_PLUS");
                }
                else {
                    s = pourcentage[0];
                    color = Parametres.getParamCol("COL_CASE3_TEXT_UNCH");
                }
                array[2] = new TextLine((titre.getValeur() > 0.0f) ? s : "-----", this.fonte[2], color, 1);
                imageCases = this.hCasesTitres.get(titre);
                if (imageCases == null) {
                    this.hCasesTitres.put(titre, this.creerCases(graphics, array));
                    imageCases = this.hCasesTitres.get(titre);
                }
                graphics.drawImage(imageCases.image, i, 0, this);
                array[0].draw(graphics, i + 5, 0, imageCases.cases[0] - 2, n - 2);
                array[1].draw(graphics, i + imageCases.cases[0] + imageCases.cases[1] / 2, 0, imageCases.cases[2] - 2, n - 2);
                array[2].draw(graphics, i + imageCases.cases[0] + imageCases.cases[1] + 5, 0, imageCases.cases[2] - 2, n - 2);
            }
        }
        else {
            graphics.drawString("Connexion en cours ...", this.posX, n);
        }
        this.repaint();
    }
    
    public static void pause(final long n) {
        try {
            if (n > 0L) {
                Thread.sleep(n);
            }
        }
        catch (Exception ex) {}
    }
    
    public int largeurVue() {
        Component parent = this;
        while (!(parent instanceof Frame)) {
            parent = parent.getParent();
            if (parent == null) {
                return -1;
            }
        }
        return ((Frame)parent).size().width;
    }
    
    void accelere() {
        this.vitesse = Parametres.getParamInt("vitesse_acceleree");
        this.periode = Parametres.getParamInt("periode_acceleree");
    }
    
    void decelere() {
        this.vitesse = Parametres.getParamInt("vitesse");
        this.periode = Parametres.getParamInt("periode");
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        return true;
    }
    
    public ImageCases creerCases(final Graphics graphics, final TextLine[] array) {
        final int[] cases = { array[0].getWidth(graphics) + 10, array[1].getWidth(graphics) + 10, array[2].getWidth(graphics) + 10 };
        final ImageCases imageCases = new ImageCases();
        final int n = cases[0] + cases[1];
        imageCases.image = this.creerImage(n, this.size().height);
        imageCases.cases = cases;
        final Graphics graphics2 = imageCases.image.getGraphics();
        graphics2.setColor(Parametres.getParamCol("COL_CASES_CADRE"));
        graphics2.drawRect(0, 0, n - 1, this.size().height - 1);
        graphics2.setColor(Parametres.getParamCol("COL_CASE1_FOND"));
        graphics2.fillRect(1, 1, cases[0], this.size().height - 2);
        graphics2.setColor(Parametres.getParamCol("COL_CASE2_FOND"));
        graphics2.fillRect(1 + cases[0], 1, cases[1], this.size().height - 2);
        return imageCases;
    }
    
    class ImageCases
    {
        public Image image;
        public int[] cases;
    }
}
