import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.util.Hashtable;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class ImgButton extends Applet
{
    public Image up;
    public Image down;
    private boolean isUp;
    private int x;
    private int y;
    private boolean clickable;
    private boolean changed;
    private int xSize;
    private int ySize;
    private int border;
    public static Hashtable images;
    
    static {
        ImgButton.images = new Hashtable();
    }
    
    ImgButton(final ImgButtonImage imgButtonImage, final int x, final int y, final boolean clickable, final Applet applet) {
        this.up = imgButtonImage.getUp();
        this.down = imgButtonImage.getDown();
        this.changed = true;
        this.clickable = clickable;
        this.x = x;
        this.y = y;
        this.xSize = imgButtonImage.getXsize();
        this.ySize = imgButtonImage.getYsize();
        this.border = imgButtonImage.getBorder();
        this.isUp = true;
    }
    
    ImgButton(final String s, final int xSize, final int ySize, final int border, final int x, final int y, final boolean clickable, final Applet applet) {
        Integer n = new Integer(-1);
        for (int i = 0; i < ImgButton.images.size(); ++i) {
            final Integer n2 = new Integer(i);
            if (((ImgButtonImage)ImgButton.images.get(n2)).IsEqual(s, xSize, ySize, border)) {
                n = n2;
            }
        }
        ImgButtonImage imgButtonImage;
        if (n == -1) {
            imgButtonImage = new ImgButtonImage(s, xSize, ySize, border, applet);
            ImgButton.images.put(new Integer(ImgButton.images.size()), imgButtonImage);
        }
        else {
            imgButtonImage = ImgButton.images.get(n);
        }
        this.up = imgButtonImage.getUp();
        this.down = imgButtonImage.getDown();
        this.changed = true;
        this.clickable = clickable;
        this.x = x;
        this.y = y;
        this.xSize = xSize;
        this.ySize = ySize;
        this.border = border;
        this.isUp = true;
    }
    
    public void ChangeImage(final ImgButtonImage imgButtonImage, final Applet applet) {
        this.up = imgButtonImage.getUp();
        this.down = imgButtonImage.getDown();
    }
    
    public void ChangeImage(final String s, final Applet applet) {
        Integer n = new Integer(-1);
        for (int i = 0; i < ImgButton.images.size(); ++i) {
            final Integer n2 = new Integer(i);
            if (((ImgButtonImage)ImgButton.images.get(n2)).IsEqual(s, this.xSize, this.ySize, this.border)) {
                n = n2;
            }
        }
        ImgButtonImage imgButtonImage;
        if (n == -1) {
            imgButtonImage = new ImgButtonImage(s, this.xSize, this.ySize, this.border, applet);
            ImgButton.images.put(new Integer(ImgButton.images.size()), imgButtonImage);
        }
        else {
            imgButtonImage = ImgButton.images.get(n);
        }
        this.up = imgButtonImage.getUp();
        this.down = imgButtonImage.getDown();
    }
    
    public int getBorder() {
        return this.border;
    }
    
    public boolean getChanged() {
        return this.changed;
    }
    
    public boolean getClickable() {
        return this.clickable;
    }
    
    public Image getDown() {
        return this.down;
    }
    
    public boolean getIsUp() {
        return this.isUp;
    }
    
    public Image getUp() {
        return this.up;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getXsize() {
        return this.xSize;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getYsize() {
        return this.ySize;
    }
    
    public void init() {
        ImgButton.images.clear();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2, final Applet applet) {
        if (this.clickable) {
            this.isUp = false;
            this.changed = true;
            applet.repaint();
            return true;
        }
        return false;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2, final Applet applet) {
        if (!this.isUp) {
            this.isUp = true;
            this.changed = true;
            applet.repaint();
            return true;
        }
        return false;
    }
    
    public void paint(final Graphics graphics) {
        if (this.isUp) {
            graphics.drawImage(this.up, this.x, this.y, this);
        }
        else {
            graphics.drawImage(this.down, this.x, this.y, this);
        }
        this.changed = false;
    }
    
    public void setChanged() {
        this.changed = false;
    }
    
    public void setClickable(final boolean clickable) {
        this.clickable = clickable;
    }
    
    public void setX(final int x) {
        this.x = x;
    }
    
    public void setY(final int y) {
        this.y = y;
    }
}
