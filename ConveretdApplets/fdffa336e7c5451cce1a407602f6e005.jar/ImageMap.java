import java.awt.Event;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.Vector;
import java.awt.image.ImageProducer;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.image.ImageFilter;
import java.awt.Rectangle;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ImageMap extends Applet
{
    Image baseImage;
    ImageMapArea[] areas;
    static final int BRIGHTER = 0;
    static final int DARKER = 1;
    int hlmode;
    int hlpercent;
    private boolean fullrepaint;
    private Rectangle repaintrect;
    private long lastupdate;
    private static final long UPDATERATE = 100L;
    int pressX;
    int pressY;
    
    Image getHighlight(final int n, final int n2, final int n3, final int n4) {
        return this.getHighlight(n, n2, n3, n4, this.hlmode, this.hlpercent);
    }
    
    Image getHighlight(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        return this.getHighlight(n, n2, n3, n4, new HighlightFilter(n5 == 0, n6));
    }
    
    Image getHighlight(final int n, final int n2, final int n3, final int n4, final ImageFilter imageFilter) {
        return this.makeImage(this.makeImage(this.baseImage, new CropImageFilter(n, n2, n3, n4)), imageFilter);
    }
    
    Image makeImage(final Image image, final ImageFilter imageFilter) {
        return this.createImage(new FilteredImageSource(image.getSource(), imageFilter));
    }
    
    void parseHighlight(final String s) {
        if (s == null) {
            return;
        }
        if (s.startsWith("brighter")) {
            this.hlmode = 0;
            if (s.length() > "brighter".length()) {
                this.hlpercent = Integer.parseInt(s.substring("brighter".length()));
            }
        }
        else if (s.startsWith("darker")) {
            this.hlmode = 1;
            if (s.length() > "darker".length()) {
                this.hlpercent = Integer.parseInt(s.substring("darker".length()));
            }
        }
    }
    
    public void init() {
        this.parseHighlight(this.getParameter("highlight"));
        this.baseImage = this.getImage(this.getDocumentBase(), this.getParameter("img"));
        final Vector vector = new Vector<ImageMapArea>();
        int n = 1;
        while (true) {
            final String parameter = this.getParameter("area" + n);
            String s;
            ImageMapArea imageMapArea;
            if (parameter == null) {
                s = this.getParameter("rect" + n);
                if (s == null) {
                    break;
                }
                final String parameter2 = this.getParameter("href" + n);
                if (parameter2 != null) {
                    s = s + "," + parameter2;
                }
                imageMapArea = new HrefArea();
            }
            else {
                final int index = parameter.indexOf(",");
                try {
                    imageMapArea = (ImageMapArea)Class.forName(parameter.substring(0, index)).newInstance();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    break;
                }
                s = parameter.substring(index + 1);
            }
            imageMapArea.init(this, s);
            vector.addElement(imageMapArea);
            ++n;
        }
        vector.copyInto(this.areas = new ImageMapArea[vector.size()]);
        this.checkSize();
    }
    
    synchronized void checkSize() {
        final int width = this.baseImage.getWidth(this);
        final int height = this.baseImage.getHeight(this);
        if (width > 0 && height > 0) {
            this.resize(width, height);
            final Rectangle repaintrect = this.repaintrect;
            final Rectangle repaintrect2 = this.repaintrect;
            final boolean b = false;
            repaintrect2.y = (b ? 1 : 0);
            repaintrect.x = (b ? 1 : 0);
            this.repaintrect.width = width;
            this.repaintrect.height = height;
            this.fullrepaint = true;
            this.repaint();
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x3) != 0x0) {
            this.checkSize();
        }
        if ((n & 0x38) != 0x0) {
            this.repaint(((n & 0x30) != 0x0) ? 0L : 100L, n2, n3, n4, n5);
        }
        return (n & 0x60) == 0x0;
    }
    
    public void paint(Graphics create) {
        synchronized (this) {
            if (this.fullrepaint) {
                create = create.create();
                create.clipRect(this.repaintrect.x, this.repaintrect.y, this.repaintrect.width, this.repaintrect.height);
                this.fullrepaint = false;
            }
        }
        if (this.baseImage == null) {
            return;
        }
        create.drawImage(this.baseImage, 0, 0, this);
        if (this.areas != null) {
            int length = this.areas.length;
            while (--length >= 0) {
                if (this.areas[length].active || this.areas[length].entered) {
                    this.areas[length].setState(create, this.areas[length].entered);
                }
            }
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.fullrepaint) {
            this.paint(graphics);
            return;
        }
        if (this.baseImage == null) {
            return;
        }
        graphics.drawImage(this.baseImage, 0, 0, this);
        if (this.areas == null) {
            return;
        }
        int length = this.areas.length;
        while (--length >= 0) {
            if (this.areas[length].active && !this.areas[length].entered) {
                this.areas[length].setState(graphics, false);
            }
        }
        int length2 = this.areas.length;
        while (--length2 >= 0) {
            if (this.areas[length2].entered) {
                this.areas[length2].setState(graphics, true);
            }
        }
    }
    
    public void mouseExit() {
        boolean b = false;
        for (int i = 0; i < this.areas.length; ++i) {
            if (this.areas[i].active) {
                this.areas[i].entered = false;
                b = true;
            }
        }
        if (b) {
            this.repaint();
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        boolean b = false;
        boolean entered = true;
        for (int i = 0; i < this.areas.length; ++i) {
            if (this.areas[i].inside(n, n2)) {
                this.areas[i].entered = entered;
                if (this.areas[i].terminal) {
                    entered = false;
                }
            }
            else {
                this.areas[i].entered = false;
            }
            if (this.areas[i].active != this.areas[i].entered) {
                b = true;
            }
        }
        if (b) {
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int pressX, final int pressY) {
        this.pressX = pressX;
        this.pressY = pressY;
        for (int i = 0; i < this.areas.length; ++i) {
            if (this.areas[i].inside(pressX, pressY)) {
                this.areas[i].press(pressX, pressY);
                if (this.areas[i].terminal) {
                    break;
                }
            }
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        for (int i = 0; i < this.areas.length; ++i) {
            if (this.areas[i].inside(this.pressX, this.pressY)) {
                this.areas[i].lift(n, n2);
                if (this.areas[i].terminal) {
                    break;
                }
            }
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        this.mouseMove(event, n, n2);
        for (int i = 0; i < this.areas.length; ++i) {
            if (this.areas[i].inside(this.pressX, this.pressY)) {
                this.areas[i].drag(n, n2);
                if (this.areas[i].terminal) {
                    break;
                }
            }
        }
        return true;
    }
    
    public ImageMap() {
        this.hlpercent = 50;
        this.fullrepaint = false;
        this.repaintrect = new Rectangle();
    }
}
