import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.Color;
import java.util.StringTokenizer;
import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.MemoryImageSource;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Mdrain extends Applet implements Runnable
{
    Image offImage;
    Image img;
    Image correnteImage;
    Graphics offGraphics;
    Thread m_animateThread;
    MediaTracker mediaTrack;
    int nPriority;
    int maxPixels;
    int h;
    int w;
    int[] imgPixel;
    int velocity;
    int max;
    int numeroFrames;
    int[] newPix;
    int frameCount;
    int effetto;
    int correnteFrame;
    int sfondoColorRBG;
    int col;
    int cx2;
    int cy2;
    int cx;
    int cy;
    int pixel;
    int pixl;
    int sx;
    int sy;
    boolean su;
    boolean isStandalone;
    double angolo1;
    double angolo2;
    double angolo3;
    double percentuale;
    MemoryImageSource correnteImageSource;
    String[] massimo;
    String autore;
    private URL m_href;
    private String m_frame;
    private String m_s_mouse;
    private final String param_mouse_s = "mouse_enter";
    private final String param_href = "href";
    private final String param_target = "target";
    
    public Mdrain() {
        this.isStandalone = false;
        this.m_frame = "_self";
        this.m_s_mouse = "";
        this.su = true;
    }
    
    public String getAppletInfo() {
        return "Applet Mdrain  by ( Massimo Giari 18/08/2000 )";
    }
    
    public String getParameter(final String key, final String def) {
        return this.isStandalone ? System.getProperty(key, def) : ((this.getParameter(key) != null) ? this.getParameter(key) : def);
    }
    
    public String[][] getParameterInfo() {
        final String[][] info = { { "href", "URL", "URL da inserire" }, { "target", "String", "Target frame destinazione" }, { "mouse_enter", "String", "Testo visualizzato in status bar quando il mouse passa sull'applet" } };
        return info;
    }
    
    public void init() {
        try {
            this.autore = this.getParameter("autore", "");
        }
        catch (Exception ex) {}
        if (!this.autore.equalsIgnoreCase("Massimo Giari")) {
            System.exit(0);
        }
        final String at = this.getParameter("priority");
        this.nPriority = ((at != null) ? Integer.valueOf(at) : 1);
        String param = this.getParameter("mouse_enter");
        if (param != null) {
            this.m_s_mouse = param;
        }
        param = this.getParameter("href");
        if (param != null) {
            try {
                this.m_href = new URL(this.getDocumentBase(), param);
            }
            catch (MalformedURLException ex2) {
                this.getAppletContext().showStatus(String.valueOf(param));
                return;
            }
        }
        param = this.getParameter("target");
        if (param != null) {
            this.m_frame = param;
        }
        this.w = this.size().width;
        this.h = this.size().height;
        this.maxPixels = this.w * this.h;
        this.cx = this.w / 2;
        this.cy = this.h / 2;
        if (this.cx * 2 < this.w) {
            this.cx2 = this.cx + 1;
        }
        else {
            this.cx2 = this.cx;
        }
        if (this.cy * 2 < this.h) {
            this.cy2 = this.cy + 1;
        }
        else {
            this.cy2 = this.cy;
        }
        this.newPix = new int[this.maxPixels];
        this.mediaTrack = new MediaTracker(this);
        StringTokenizer stringtokenizer = new StringTokenizer(this.getParameter("background"));
        this.sfondoColorRBG = new Color(Integer.parseInt(stringtokenizer.nextToken(), 16)).getRGB();
        stringtokenizer = new StringTokenizer(this.getParameter("speed"));
        this.velocity = Integer.parseInt(stringtokenizer.nextToken());
        stringtokenizer = new StringTokenizer(this.getParameter("effect_type"));
        this.effetto = Integer.parseInt(stringtokenizer.nextToken());
        if (this.effetto >= 3) {
            this.effetto = 0;
        }
        stringtokenizer = new StringTokenizer(this.getParameter("num_frames"));
        this.numeroFrames = Integer.parseInt(stringtokenizer.nextToken());
        if (this.numeroFrames <= 60) {
            this.numeroFrames = 100;
        }
        this.imgPixel = new int[this.maxPixels];
        stringtokenizer = new StringTokenizer(this.getParameter("image"));
        this.img = this.getImage(this.getCodeBase(), stringtokenizer.nextToken());
        this.mediaTrack.addImage(this.img, 0);
        for (int i = 0; i < this.maxPixels; ++i) {
            this.newPix[i] = this.sfondoColorRBG;
        }
        this.correnteImageSource = new MemoryImageSource(this.w, this.h, this.newPix, 0, this.w);
        this.correnteImage = this.createImage(this.correnteImageSource);
    }
    
    public boolean mouseDown(final Event event, final int i, final int j) {
        if (this.m_animateThread == null) {
            this.start();
        }
        else {
            this.stop();
        }
        this.getAppletContext().showDocument(this.m_href, this.m_frame);
        return false;
    }
    
    public boolean mouseEnter(final Event evt, final int i, final int j) {
        if (this.m_s_mouse == null) {
            this.showStatus(String.valueOf(this.m_s_mouse));
        }
        this.getAppletContext().showStatus(this.m_s_mouse);
        return true;
    }
    
    public boolean mouseExit(final Event evt, final int i, final int j) {
        this.showStatus("");
        return true;
    }
    
    public void paint(final Graphics g) {
        g.drawString("Caricando Immagine", 5, 20);
        this.update(g);
    }
    
    public void run() {
        while (this.m_animateThread != null) {
            try {
                Thread.currentThread().setPriority(this.nPriority);
                Thread.sleep(this.velocity);
            }
            catch (InterruptedException ex) {}
            if (this.su) {
                ++this.correnteFrame;
                if (this.correnteFrame == this.numeroFrames) {
                    this.su = false;
                    this.correnteFrame -= 2;
                }
            }
            else {
                --this.correnteFrame;
                if (this.correnteFrame == -1) {
                    this.su = true;
                    this.correnteFrame += 2;
                }
            }
            this.repaint();
        }
    }
    
    final void ruota_antiorario(final double d, final double d1) {
        this.pixl = 0;
        this.angolo3 = d * d1;
        this.angolo2 = Math.cos(this.angolo3);
        this.angolo1 = Math.sin(this.angolo3);
        this.percentuale = this.angolo3 / Math.sqrt(this.w * this.w + this.h * this.h);
        for (int i = -this.cy; i < this.cy2; ++i) {
            for (int j = -this.cx; j < this.cx2; ++j) {
                this.angolo3 = Math.sqrt(j * j + i * i) * this.percentuale;
                this.angolo1 = Math.cos(this.angolo3);
                this.angolo2 = 1.01 - this.angolo1;
                this.sx = (int)(j * this.angolo1 - i * this.angolo2) + this.cx;
                this.sy = (int)(i * this.angolo1 + j * this.angolo2) + this.cy;
                if (this.sx >= 0 && this.sx < this.w && this.sy >= 0 && this.sy < this.h) {
                    this.newPix[this.pixl] = this.imgPixel[this.sy * this.w + this.sx];
                }
                else {
                    this.newPix[this.pixl] = this.sfondoColorRBG;
                }
                ++this.pixl;
            }
        }
    }
    
    final void ruota_orario(final double d, final double d1) {
        this.pixl = 0;
        this.angolo3 = d * d1;
        this.angolo2 = Math.cos(this.angolo3);
        this.angolo1 = Math.sin(this.angolo3);
        this.percentuale = this.angolo3 / Math.sqrt(this.w * this.w + this.h * this.h);
        for (int i = -this.cy; i < this.cy2; ++i) {
            for (int j = -this.cx; j < this.cx2; ++j) {
                this.angolo3 = Math.sqrt(j * j + i * i) * this.percentuale;
                this.angolo1 = Math.cos(this.angolo3);
                this.angolo2 = 1.01 - this.angolo1;
                this.sx = (int)(j * this.angolo1 + i * this.angolo2) + this.cx;
                this.sy = (int)(i * this.angolo1 - j * this.angolo2) + this.cy;
                if (this.sx >= 0 && this.sx < this.w && this.sy >= 0 && this.sy < this.h) {
                    this.newPix[this.pixl] = this.imgPixel[this.sy * this.w + this.sx];
                }
                else {
                    this.newPix[this.pixl] = this.sfondoColorRBG;
                }
                ++this.pixl;
            }
        }
    }
    
    final void spirale_rientro(final double d, final double d1) {
        this.pixl = 0;
        this.angolo3 = d * d1;
        this.angolo2 = Math.cos(this.angolo3);
        this.angolo1 = Math.sin(this.angolo3);
        this.percentuale = this.angolo3 / Math.sqrt(this.w * this.w + this.h * this.h);
        for (int i = -this.cy; i < this.cy2; ++i) {
            for (int j = -this.cx; j < this.cx2; ++j) {
                this.angolo3 = Math.sqrt(j * j + i * i) * this.percentuale;
                this.angolo2 = Math.cos(this.angolo3);
                this.angolo1 = 1.01 - this.angolo2;
                this.sx = (int)(j * this.angolo2 - j * this.angolo1) + this.cx;
                this.sy = (int)(i * this.angolo2 - i * this.angolo1) + this.cy;
                if (this.sx >= 0 && this.sx < this.w && this.sy >= 0 && this.sy < this.h) {
                    this.newPix[this.pixl] = this.imgPixel[this.sy * this.w + this.sx];
                }
                else {
                    this.newPix[this.pixl] = this.sfondoColorRBG;
                }
                ++this.pixl;
            }
        }
    }
    
    public void start() {
        if (this.m_animateThread == null) {
            (this.m_animateThread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.m_animateThread = null;
    }
    
    public void update(final Graphics g) {
        if (this.offImage != null) {
            this.offGraphics.drawImage(this.correnteImage, 0, 0, null);
            g.drawImage(this.offImage, 0, 0, null);
            switch (this.effetto) {
                case 0: {
                    this.ruota_antiorario(this.correnteFrame * 0.07, this.correnteFrame * 0.07);
                    break;
                }
                case 1: {
                    this.ruota_orario(this.correnteFrame * 0.07, this.correnteFrame * 0.07);
                    break;
                }
                case 2: {
                    this.spirale_rientro(this.correnteFrame * 0.07, this.correnteFrame * 0.07);
                    break;
                }
            }
            this.correnteImage = this.createImage(this.correnteImageSource);
            return;
        }
        try {
            this.mediaTrack.waitForAll();
        }
        catch (InterruptedException ex) {
            this.stop();
        }
        this.offImage = this.createImage(this.w, this.h);
        this.offGraphics = this.offImage.getGraphics();
        final int[] ai = new int[this.img.getHeight(this) * this.img.getWidth(this)];
        final PixelGrabber pixelgrabber = new PixelGrabber(this.img, 0, 0, this.img.getWidth(this), this.img.getHeight(this), ai, 0, this.img.getWidth(this));
        try {
            pixelgrabber.grabPixels();
        }
        catch (InterruptedException ex2) {}
        for (int j = 0; j < this.h; ++j) {
            for (int k = 0; k < this.w; ++k) {
                final int i = j * this.w + k;
                if (j < this.img.getHeight(this) && k < this.img.getWidth(this)) {
                    this.imgPixel[i] = ai[j * this.img.getWidth(this) + k];
                }
                else {
                    this.imgPixel[i] = this.sfondoColorRBG;
                }
            }
        }
    }
}
