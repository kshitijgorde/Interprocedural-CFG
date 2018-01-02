import java.awt.FontMetrics;
import java.awt.Component;
import java.awt.Cursor;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Graphics;
import java.applet.Applet;
import java.util.Random;
import java.awt.MediaTracker;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

class imagehold
{
    final Font MFONT;
    colors col;
    imageset[] theimg;
    int[] order;
    int imgCount;
    int imgLoaded;
    int imgShown;
    int imgDisplay;
    MediaTracker mt;
    int screenx;
    int screeny;
    Random rd;
    
    imagehold(final Applet applet, final Graphics graphics, final int screenx, final int screeny) {
        this.MFONT = new Font("Arial", 1, 11);
        this.rd = new Random();
        this.screenx = screenx;
        this.screeny = screeny;
        this.col = new colors(applet);
        this.setImages(applet, graphics);
    }
    
    int allLoaded() {
        if (this.imgLoaded < this.imgCount) {
            final int statusID = this.mt.statusID(this.imgLoaded, true);
            if (statusID != 1) {
                if (statusID == 4) {
                    System.err.println("Image" + this.imgLoaded + " has an Error.");
                }
                else {
                    this.theimg[this.imgLoaded].setDimensions(this.col.nextSpeed(), this.rd);
                    System.err.println("Image" + this.imgLoaded + " has been Loaded. " + this.theimg[this.imgLoaded].xsize + ":" + this.theimg[this.imgLoaded].ysize);
                }
                if (this.imgDisplay < this.imgShown) {
                    ++this.imgDisplay;
                }
                if (++this.imgLoaded >= this.imgCount) {
                    this.mt = null;
                    System.gc();
                }
            }
        }
        return this.imgLoaded;
    }
    
    void draw(final Graphics graphics) {
        if (this.allLoaded() > 0) {
            for (int i = this.imgDisplay - 1; i >= 0; --i) {
                final int n = this.order[i];
                this.theimg[n].draw(graphics, this.col);
                this.theimg[n].move();
            }
            int j = 0;
            int imgDisplay = this.imgDisplay;
            while (j < imgDisplay) {
                if (!this.theimg[this.order[j]].isVisible()) {
                    this.reorder(j);
                    --imgDisplay;
                }
                else {
                    ++j;
                }
            }
        }
    }
    
    boolean imageDrag(final boolean b, final int n, final int n2, final int n3, final int n4) {
        if (this.theimg[this.order[0]].isOver(n3, n4) && b) {
            this.theimg[this.order[0]].move(n - n3, n2 - n4);
            return true;
        }
        return false;
    }
    
    void imageEnter(final Applet applet, final int n, final int n2, final String s) {
        boolean b = false;
        for (int n3 = 0; n3 < this.imgDisplay && !b; ++n3) {
            if (this.theimg[this.order[n3]].isEnter(n, n2, this.col) && this.theimg[this.order[n3]].link != null) {
                URL url;
                try {
                    url = new URL(this.theimg[this.order[n3]].link);
                }
                catch (MalformedURLException ex) {
                    return;
                }
                applet.getAppletContext().showDocument(url, s);
                b = true;
            }
        }
    }
    
    int imageOver(final Applet applet, final int n, final int n2) {
        boolean b;
        int n3;
        int n4;
        for (b = false, n3 = 0; n3 < this.imgDisplay && !b; ++n3) {
            if (this.theimg[this.order[n3]].isOver(n, n2)) {
                if (this.theimg[this.order[n3]].isEnter(n, n2, this.col)) {
                    applet.setCursor(Cursor.getPredefinedCursor(12));
                    applet.showStatus("Link: " + this.theimg[this.order[n3]].link);
                }
                else {
                    applet.setCursor(Cursor.getPredefinedCursor(0));
                }
                n4 = this.order[n3];
                this.order[n3] = this.order[0];
                this.order[0] = n4;
                b = true;
            }
        }
        if (!b) {
            applet.setCursor(Cursor.getPredefinedCursor(0));
        }
        return n3;
    }
    
    void reorder(final int n) {
        final int n2 = this.order[n];
        int n3 = 0;
        if (this.imgLoaded > 1) {
            if (this.imgDisplay >= this.imgLoaded) {
                n3 = n2;
            }
            else {
                n3 = this.order[this.imgDisplay];
            }
            for (int i = n; i < this.imgLoaded - 1; ++i) {
                this.order[i] = this.order[i + 1];
            }
            this.order[this.imgLoaded - 1] = n2;
        }
        this.theimg[n3].setpos(this.col.nextSpeed(), this.rd);
    }
    
    void reset() {
        for (int i = 0; i < this.imgDisplay; ++i) {
            this.theimg[this.order[i]].over = false;
        }
    }
    
    private void setImages(final Applet applet, final Graphics graphics) {
        int n = 0;
        this.imgCount = 0;
        final String parameter = applet.getParameter("maximages");
        if (parameter != null) {
            this.imgCount = Integer.parseInt(parameter);
        }
        if (this.imgCount > 1000) {
            this.imgCount = 1000;
        }
        else if (this.imgCount <= 0) {
            this.imgCount = 0;
        }
        else {
            for (int i = 1; i <= this.imgCount; ++i) {
                if (applet.getParameter("img" + i) != null) {
                    ++n;
                }
            }
        }
        this.imgShown = n;
        final String parameter2 = applet.getParameter("maxshown");
        if (parameter2 != null) {
            final int int1 = Integer.parseInt(parameter2);
            if (int1 > 0 && int1 < n) {
                this.imgShown = int1;
            }
        }
        if (n > 0) {
            this.theimg = new imageset[n];
            this.order = new int[n];
            for (int j = 0; j < n; ++j) {
                this.order[j] = j;
            }
        }
        this.imgLoaded = 0;
        this.imgDisplay = 0;
        if (n > 0) {
            graphics.setFont(this.MFONT);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            this.mt = new MediaTracker(applet);
            int n2 = 0;
            for (int k = 0; k < this.imgCount; ++k) {
                if (applet.getParameter("img" + (k + 1)) != null) {
                    (this.theimg[n2] = new imageset(applet, k + 1, this.screenx, this.screeny, fontMetrics)).setpos(this.col.nextSpeed(), this.rd);
                    if (this.col.diagonal) {
                        this.theimg[n2].setside(8);
                    }
                    this.mt.addImage(this.theimg[n2].img, n2);
                    ++n2;
                }
            }
        }
        this.imgCount = n;
    }
    
    void setNotOver(int i) {
        while (i < this.imgDisplay) {
            this.theimg[this.order[i]].over = false;
            ++i;
        }
    }
}
