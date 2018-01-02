// 
// Decompiled by Procyon v0.5.30
// 

package menu;

import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

public class ImageEnchaine extends Applet
{
    boolean isStandalone;
    int delai;
    int nbimage;
    Diaporama diaporama1;
    private Thread _$1007;
    Image offscreen;
    String[] tfichier;
    String[] http;
    String fichier1;
    String fichier2;
    
    public String getParameter(final String key, final String def) {
        return this.isStandalone ? System.getProperty(key, def) : ((this.getParameter(key) != null) ? this.getParameter(key) : def);
    }
    
    public ImageEnchaine() {
        this.isStandalone = false;
        this.diaporama1 = new Diaporama();
        this.tfichier = new String[50];
        this.http = new String[50];
    }
    
    public void init() {
        try {
            this._$1054();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void _$1054() throws Exception {
        final Graphics g = this.getParent().getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
        g.setColor(new Color(120, 210, 255));
        g.drawString("Chargement...", 0, 50);
        super.paint(g);
        this.setLayout(null);
        this.delai = Integer.parseInt(this.getParameter("delai", "0"));
        this.fichier1 = this.getParameter("images", "0");
        this.fichier2 = this.getParameter("liens", "0");
        this.lecfichier(this.fichier1, this.tfichier);
        this.diaporama1.setNbImage(this.nbimage);
        this.lecfichier(this.fichier2, this.http);
        this.diaporama1.setBounds(new Rectangle(0, 0, 640, 400));
        this.diaporama1.setFichier(this.tfichier);
        this.diaporama1.setApplet(this);
        this.diaporama1.setHttp(this.http);
        this.diaporama1.setSync(20);
        this.diaporama1.setDelai(this.delai);
        this.add(this.diaporama1, null);
    }
    
    public String getAppletInfo() {
        return "Information applet";
    }
    
    public String[][] getParameterInfo() {
        return null;
    }
    
    public void stop() {
        this.diaporama1.stop();
    }
    
    public void start() {
        final String s = this.getDocumentBase().toString();
        if (s.indexOf("dmitours") >= 0 | s.indexOf("tran-emmanuel") >= 0 | s.indexOf("peinture") >= 0 | s.indexOf("southwest") >= 0) {
            this.diaporama1.demarre();
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void paint(final Graphics g) {
        super.paint(g);
    }
    
    public void lecfichier(final String nomfichier, final String[] f) {
        if (nomfichier != null) {
            this.nbimage = 0;
            String s = this.getDocumentBase().toString();
            if (s.indexOf("jar:") >= 0) {
                s = s.substring(4, s.lastIndexOf("/") + 1);
            }
            else {
                s = s.substring(0, s.lastIndexOf("/") + 1);
            }
            s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(nomfichier)));
            try {
                final URL url = new URL(s);
                final InputStream is = url.openStream();
                final BufferedReader in = new BufferedReader(new InputStreamReader(is));
                in.mark(1000);
                String line;
                while ((line = in.readLine()) != null) {
                    if (line.length() > 1) {
                        this.http[this.nbimage] = " ";
                        f[this.nbimage] = line;
                    }
                    ++this.nbimage;
                }
            }
            catch (StringIndexOutOfBoundsException ex) {}
            catch (MalformedURLException ex2) {}
            catch (IOException ex3) {}
            catch (NullPointerException ex4) {}
        }
    }
    
    void this_mouseMoved(final MouseEvent e) {
    }
}
