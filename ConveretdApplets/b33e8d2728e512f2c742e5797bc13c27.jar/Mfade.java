import java.awt.FontMetrics;
import java.awt.image.PixelGrabber;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Color;
import java.util.StringTokenizer;
import java.awt.Component;
import java.net.MalformedURLException;
import java.awt.Cursor;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.net.URL;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Mfade extends Applet implements Runnable
{
    Image _i006;
    Image[] _i008;
    Graphics _i007;
    Thread t;
    MediaTracker mt;
    int _np001;
    int mpx001;
    int w;
    int h;
    int _n001;
    int[][] _ipx001;
    int[] _ci001;
    int _fd001;
    int _bc001;
    int _p001;
    int[][] _r001;
    int[][] _v001;
    int[][] _b001;
    int _i001;
    int _i002;
    int alpha;
    double _i003;
    double _i004;
    double _i005;
    private URL m_href;
    private String m_frame;
    private String m_testo;
    private final String param_href = "href";
    private final String param_target = "target";
    private final String param_testo = "testo";
    boolean isStandalone;
    String aut_001;
    
    public Mfade() {
        this._np001 = 40;
        this._i005 = 0.0;
        this.isStandalone = false;
        this.m_frame = "_self";
        this._i002 = 1;
        this.alpha = -16777216;
        this._i003 = 1.0;
    }
    
    final void _cm001() {
        if (this._i004 >= 1.0) {
            this._p001 = 0;
            this._i004 = 0.0;
            this._i003 = 1.0;
            this._sw001();
        }
    }
    
    final Image _mf001(final double d) {
        this._cm001();
        for (int i = 0; i < this.mpx001; ++i) {
            final int _jb1 = (int)(this._r001[this._i001][i] * this._i003 + this._r001[this._i002][i] * this._i004);
            final int _kb1 = (int)(this._v001[this._i001][i] * this._i003 + this._v001[this._i002][i] * this._i004);
            final int _lb1;
            this._ci001[i] = (this.alpha | _jb1 << 16 | _kb1 << 8 | (_lb1 = (int)(this._b001[this._i001][i] * this._i003 + this._b001[this._i002][i] * this._i004)));
        }
        this._i004 += d;
        this._i003 -= d;
        return this.createImage(new MemoryImageSource(this.w, this.h, this._ci001, 0, this.w));
    }
    
    final void _sw001() {
        ++this._i001;
        ++this._i002;
        if (this._i002 == this._n001) {
            this._i002 = 0;
        }
        if (this._i001 == this._n001) {
            this._i001 = 0;
        }
    }
    
    public String getAppletInfo() {
        String s = "";
        s = String.valueOf(s) + "Titolo: Mfade\r\n";
        s = String.valueOf(s) + "Autore: Massimo Giari\r\n";
        s = String.valueOf(s) + "E-mail: motore@iol.it\r\n";
        s = String.valueOf(s) + "Copyright Massimo Giari 09/07/2002";
        return s;
    }
    
    public String getParameter(final String key, final String def) {
        return this.isStandalone ? System.getProperty(key, def) : ((this.getParameter(key) == null) ? def : this.getParameter(key));
    }
    
    public String[][] getParameterInfo() {
        final String[][] info = { { "href", "URL", "URL link da inserire" }, { "target", "String", "Target frame destinazione" }, { "testo", "String", "Testo scritto prima del caricamento immagini" } };
        return info;
    }
    
    public void init() {
        try {
            this.aut_001 = this.getParameter("autore", "");
        }
        catch (Exception ex) {}
        if (!this.aut_001.equalsIgnoreCase("Massimo Giari")) {
            System.exit(0);
        }
        String param = this.getParameter("href");
        if (param != null) {
            try {
                this.setCursor(new Cursor(12));
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
        param = this.getParameter("testo");
        if (param != null) {
            this.m_testo = param;
        }
        param = this.getParameter("testo");
        if (param == null) {
            this.m_testo = "Caricamento Immagini";
        }
        final String at = this.getParameter("priority");
        this._np001 = ((at != null) ? Integer.valueOf(at) : 1);
        this.w = this.size().width;
        this.h = this.size().height;
        this.mpx001 = this.w * this.h;
        this.mt = new MediaTracker(this);
        StringTokenizer stringtokenizer = new StringTokenizer(this.getParameter("speed"));
        this._i005 = Integer.parseInt(stringtokenizer.nextToken()) * 0.001;
        stringtokenizer = new StringTokenizer(this.getParameter("numero_immagini"));
        this._n001 = Integer.parseInt(stringtokenizer.nextToken());
        stringtokenizer = new StringTokenizer(this.getParameter("background"));
        this._bc001 = new Color(Integer.parseInt(stringtokenizer.nextToken(), 16)).getRGB();
        this._i008 = new Image[this._n001];
        this._ci001 = new int[this.mpx001];
        this._ipx001 = new int[this._n001][this.mpx001];
        this._r001 = new int[this._n001][this.mpx001];
        this._v001 = new int[this._n001][this.mpx001];
        this._b001 = new int[this._n001][this.mpx001];
        for (int i = 0; i < this._n001; ++i) {
            this._i008[i] = this.getImage(this.getCodeBase(), this.getParameter("image" + (i + 1)));
        }
        for (int j = 0; j < this._n001; ++j) {
            this.mt.addImage(this._i008[j], 1);
        }
    }
    
    public boolean mouseDown(final Event event, final int i, final int j) {
        this.getAppletContext().showDocument(this.m_href, this.m_frame);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int i, final int j) {
        return true;
    }
    
    public boolean mouseMove(final Event event, final int i, final int j) {
        this.showStatus("Applet Mfade by ( Massimo Giari )");
        return true;
    }
    
    public void paint(final Graphics g) {
        if (this._i006 != null) {
            this._i007.drawImage(this._mf001(this._i005), 0, 0, null);
            g.drawImage(this._i006, 0, 0, null);
            return;
        }
        StringTokenizer stringtokenizer = new StringTokenizer(this.getParameter("color_load"));
        g.setColor(new Color(Integer.parseInt(stringtokenizer.nextToken(), 16)));
        g.fillRect(0, 0, this.w, this.h);
        stringtokenizer = new StringTokenizer(this.getParameter("text_color"));
        g.setColor(new Color(Integer.parseInt(stringtokenizer.nextToken(), 16)));
        final Font font = new Font("Helvetica", 0, 12);
        final FontMetrics fm = g.getFontMetrics(font);
        final String str = new String(this.m_testo);
        g.setFont(font);
        g.drawString(str, (this.size().width - fm.stringWidth(str)) / 2, (this.size().height - fm.getHeight()) / 2 + fm.getAscent());
        try {
            this.mt.waitForAll();
        }
        catch (InterruptedException ex) {
            this.stop();
        }
        this._i006 = this.createImage(this.size().width, this.size().height);
        this._i007 = this._i006.getGraphics();
        for (int j = 0; j < this._n001; ++j) {
            final int[] _ai001 = new int[this._i008[j].getHeight(this) * this._i008[j].getWidth(this)];
            final PixelGrabber pixelgrabber = new PixelGrabber(this._i008[j], 0, 0, this._i008[j].getWidth(this), this._i008[j].getHeight(this), _ai001, 0, this._i008[j].getWidth(this));
            try {
                pixelgrabber.grabPixels();
            }
            catch (InterruptedException ex2) {}
            for (int kb = 0; kb < this.h; ++kb) {
                for (int l_i = 0; l_i < this.w; ++l_i) {
                    final int i = kb * this.w + l_i;
                    if (kb < this._i008[j].getHeight(this) && l_i < this._i008[j].getWidth(this)) {
                        this._ipx001[j][i] = _ai001[kb * this._i008[j].getWidth(this) + l_i];
                    }
                    else {
                        this._ipx001[j][i] = this._bc001;
                    }
                }
            }
            for (int i2 = 0; i2 < this.mpx001; ++i2) {
                this._r001[j][i2] = (this._ipx001[j][i2] & 0xFF0000) >> 16;
                this._v001[j][i2] = (this._ipx001[j][i2] & 0xFF00) >> 8;
                this._b001[j][i2] = (this._ipx001[j][i2] & 0xFF);
            }
        }
    }
    
    public void paintFrame(final Graphics g) {
    }
    
    public void run() {
        while (this.t != null) {
            try {
                Thread.sleep(this._fd001);
            }
            catch (InterruptedException ex) {}
            this.repaint();
        }
    }
    
    public void start() {
        if (this.t == null) {
            this.t = new Thread(this);
            Thread.currentThread().setPriority(this._np001);
            this.t.start();
        }
    }
    
    public void stop() {
        this.t = null;
    }
    
    final int toHex(final int i, final int j, final int k, final int l) {
        return this.alpha | j << 16 | k << 8 | l;
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
}
