import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ImageList
{
    private Applet applet;
    private ImageItem[] list;
    private int current;
    private URL codeBase;
    private int number;
    private long length;
    private long time;
    private boolean mouseEnter;
    
    public ImageList(final Applet applet) {
        this.current = 0;
        this.number = 0;
        this.length = 0L;
        this.time = 0L;
        this.mouseEnter = false;
        this.applet = applet;
        this.codeBase = applet.getCodeBase();
    }
    
    public URL getURL() {
        return this.list[this.current].url;
    }
    
    public String getURLString() {
        return this.list[this.current].str;
    }
    
    public void loadImages() {
        final String parameter = this.applet.getParameter("number");
        if (parameter != null) {
            this.number = Integer.parseInt(parameter);
        }
        else {
            this.number = 0;
        }
        final String parameter2 = this.applet.getParameter("length");
        if (parameter2 != null) {
            this.length = Integer.parseInt(parameter2);
        }
        else {
            this.length = 3L;
        }
        this.length *= 1000L;
        this.list = new ImageItem[this.number];
        for (int i = 0; i < this.number; ++i) {
            try {
                final String[] strings = this.parseStrings(this.applet.getParameter("item" + i), "|");
                final Image image = this.applet.getImage(this.codeBase, strings[0]);
                this.applet.showStatus("Loading: " + strings[0]);
                this.list[i] = new ImageItem(image, strings[1], new URL(strings[1]));
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void nextImage() {
        if (this.current + 1 >= this.number) {
            this.current = 0;
        }
        else {
            ++this.current;
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.list[this.current].img, 0, 0, this.applet);
    }
    
    public String[] parseStrings(final String s, final String s2) {
        if (s != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
            final String[] array = new String[stringTokenizer.countTokens()];
            for (int i = 0; i < array.length; ++i) {
                array[i] = stringTokenizer.nextToken();
            }
            return array;
        }
        return null;
    }
    
    public void setMouseEnter(final boolean mouseEnter) {
        this.mouseEnter = mouseEnter;
    }
    
    public void update() {
        if (this.time > 0L) {
            if (System.currentTimeMillis() - this.time >= this.length) {
                this.nextImage();
                this.time = System.currentTimeMillis();
                if (this.mouseEnter) {
                    this.applet.showStatus(this.list[this.current].str);
                }
            }
        }
        else {
            this.time = System.currentTimeMillis();
        }
    }
}
