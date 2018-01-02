import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Color;
import java.awt.Event;
import java.awt.MediaTracker;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Orbiter extends Applet implements Runnable
{
    tdViewpoint m_viewpoint;
    tdFont m_font;
    tdModel m_modelComposite;
    tdObject m_object;
    int m_nWindowWidth;
    int m_nWindowHeight;
    Thread m_threadAnim;
    long m_lStartTime;
    long m_lPrevTime;
    double m_dRadiansPerSecond;
    boolean m_bInitialized;
    MediaTracker m_mediaTracker;
    String m_stringRollover;
    String m_stringURL;
    String m_stringTarget;
    
    public void start() {
        if (this.m_threadAnim == null) {
            (this.m_threadAnim = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.m_threadAnim != null) {
            this.m_threadAnim.stop();
            this.m_threadAnim = null;
        }
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.m_stringRollover != null) {
            this.showStatus(" ");
        }
        return true;
    }
    
    private void initialization() {
        final Dimension size = this.size();
        this.m_nWindowWidth = size.width;
        this.m_nWindowHeight = size.height;
        Color yellow = Color.yellow;
        final String parameter = this.getParameter("text_color_front");
        if (parameter != null) {
            yellow = new Color(Integer.parseInt(parameter, 16));
        }
        Color red = Color.red;
        final String parameter2 = this.getParameter("text_color_back");
        if (parameter2 != null) {
            if (parameter2.equalsIgnoreCase("transparent")) {
                red = null;
            }
            else {
                red = new Color(Integer.parseInt(parameter2, 16));
            }
        }
        this.m_font = new tdFont(yellow, red);
        String parameter3 = this.getParameter("string");
        if (parameter3 == null) {
            parameter3 = new String("No string parameter");
        }
        final char[] charArray = parameter3.toCharArray();
        final int length = charArray.length;
        double n = 0.0;
        final double n2 = 1.5;
        final double n3 = 0.2;
        double n4 = 48.0;
        int n5 = 0;
        for (int i = 0; i < length; ++i) {
            if (this.m_font.m_model[charArray[i]] == null) {
                n += n2;
            }
            else {
                n += this.m_font.m_dWidth[charArray[i]] + n3;
                ++n5;
            }
        }
        if (n > n4 * 0.75) {
            n4 = n / 0.75;
        }
        final double n6 = n4 / 6.283185307179586;
        double n7 = 0.0;
        int n8 = 0;
        final tdObject[] array = new tdObject[n5];
        for (int j = 0; j < length; ++j) {
            if (this.m_font.m_model[charArray[j]] == null) {
                n7 += n2;
            }
            else {
                final double n9 = n7 + (this.m_font.m_dWidth[charArray[j]] + n3) / 2.0;
                array[n8] = new tdObject(this.m_font.m_model[charArray[j]]);
                final double n10 = n9 / n4 * 2.0 * 3.141592653589793;
                array[n8].m_vector.m_dZ = n6 * Math.cos(n10);
                array[n8].m_vector.m_dX = n6 * Math.sin(n10);
                array[n8].m_matrix.rotateWorld(0.0, n10, 0.0);
                n7 = n9 + (this.m_font.m_dWidth[charArray[j]] + n3) / 2.0;
                ++n8;
            }
        }
        this.m_modelComposite = new tdModel(array, n8);
        this.m_object = new tdObject(this.m_modelComposite);
        final String parameter4 = this.getParameter("dip");
        if (parameter4 != null) {
            Double n11;
            try {
                n11 = new Double(parameter4);
            }
            catch (NumberFormatException ex) {
                n11 = new Double(0.0);
            }
            this.m_object.m_matrix.rotateWorld(n11 * 3.141592653589793 / 180.0, 0.0, 0.0);
        }
        else {
            this.m_object.m_matrix.rotateWorld(0.2, 0.0, 0.0);
        }
        final String parameter5 = this.getParameter("tilt");
        if (parameter5 != null) {
            Double n12;
            try {
                n12 = new Double(parameter5);
            }
            catch (NumberFormatException ex2) {
                n12 = new Double(0.0);
            }
            this.m_object.m_matrix.rotateWorld(0.0, 0.0, n12 * 3.141592653589793 / 180.0);
        }
        else {
            this.m_object.m_matrix.rotateWorld(0.0, 0.0, 0.2);
        }
        this.m_object.transform();
        final String parameter6 = this.getParameter("rpm");
        if (parameter6 != null) {
            Double n13;
            try {
                n13 = new Double(parameter6);
            }
            catch (NumberFormatException ex3) {
                n13 = new Double(10.0);
            }
            this.m_dRadiansPerSecond = n13 / 60.0 * 2.0 * 3.141592653589793;
        }
        this.m_viewpoint = new tdViewpoint(0, 0, this.m_nWindowWidth, this.m_nWindowHeight, this);
        this.m_viewpoint.m_vector.m_dZ = 2.414 * n6;
        this.m_viewpoint.m_matrix.rotateWorld(0.0, 3.141592653589793, 0.0);
        final String parameter7 = this.getParameter("bgcolor");
        if (parameter7 != null) {
            this.m_viewpoint.m_colorBackgrnd = new Color(Integer.parseInt(parameter7, 16));
        }
        final String parameter8 = this.getParameter("planet");
        if (parameter8 != null) {
            this.m_viewpoint.m_imagePlanet = this.getImage(this.getDocumentBase(), parameter8);
            this.m_mediaTracker.addImage(this.m_viewpoint.m_imagePlanet, 0);
            while (!this.m_mediaTracker.checkID(0, true)) {
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex4) {}
            }
            this.m_viewpoint.m_nPlanetX = (this.m_nWindowWidth - this.m_viewpoint.m_imagePlanet.getWidth(null)) / 2;
            this.m_viewpoint.m_nPlanetY = (this.m_nWindowHeight - this.m_viewpoint.m_imagePlanet.getHeight(null)) / 2;
        }
        final String parameter9 = this.getParameter("background");
        if (parameter9 != null) {
            final Image image = this.getImage(this.getDocumentBase(), parameter9);
            this.m_mediaTracker.addImage(image, 1);
            while (!this.m_mediaTracker.checkID(1, true)) {
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex5) {}
            }
            final int width = image.getWidth(null);
            final int height = image.getHeight(null);
            this.m_viewpoint.m_imageBackgrnd = this.createImage(this.m_nWindowWidth, this.m_nWindowHeight);
            final Graphics graphics = this.m_viewpoint.m_imageBackgrnd.getGraphics();
            for (int k = 0; k < this.m_nWindowWidth; k += width) {
                for (int l = 0; l < this.m_nWindowHeight; l += height) {
                    graphics.drawImage(image, k, l, null);
                }
            }
            graphics.dispose();
        }
        this.m_stringRollover = this.getParameter("rollover");
        this.m_stringURL = this.getParameter("URL");
        this.m_stringTarget = this.getParameter("target");
        this.m_lStartTime = System.currentTimeMillis();
        this.m_lPrevTime = this.m_lStartTime;
        this.m_bInitialized = true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.m_stringURL != null) {
            URL url;
            try {
                url = new URL(this.m_stringURL);
            }
            catch (Exception ex) {
                this.showStatus("Bad URL");
                return true;
            }
            if (this.m_stringTarget == null) {
                this.getAppletContext().showDocument(url);
            }
            else {
                this.getAppletContext().showDocument(url, this.m_stringTarget);
            }
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.m_stringRollover != null) {
            this.showStatus(this.m_stringRollover);
        }
        return true;
    }
    
    public Orbiter() {
        this.m_dRadiansPerSecond = 1.0;
        this.m_mediaTracker = new MediaTracker(this);
    }
    
    public void run() {
        if (!this.m_bInitialized) {
            this.initialization();
        }
        final tdObject[] array = { this.m_object };
        while (true) {
            final long currentTimeMillis = System.currentTimeMillis();
            final double n = (currentTimeMillis - this.m_lPrevTime) / 1000.0;
            this.m_lPrevTime = currentTimeMillis;
            array[0].m_matrix.rotateSelf(0.0, this.m_dRadiansPerSecond * n, 0.0);
            array[0].transform();
            this.m_viewpoint.render(array, 1, this);
            System.gc();
            try {
                Thread.sleep(0L);
            }
            catch (InterruptedException ex) {
                this.stop();
            }
        }
    }
    
    public void init() {
        this.start();
    }
}
