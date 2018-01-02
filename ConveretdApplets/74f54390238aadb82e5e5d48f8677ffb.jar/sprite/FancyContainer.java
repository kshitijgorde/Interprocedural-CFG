// 
// Decompiled by Procyon v0.5.30
// 

package sprite;

import java.awt.Color;
import java.awt.image.ColorModel;
import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.image.MemoryImageSource;
import java.awt.Component;

public class FancyContainer extends Sprite
{
    boolean m_bCompatible;
    private boolean m_bFillBG;
    boolean m_bMacNSHack;
    boolean m_bNSHack;
    private Component m_c;
    private FancyHelper m_helper;
    private MemoryImageSource m_imageSrc;
    private int m_nClrBG;
    private int m_nHackCount;
    private int[] m_pixels;
    private Rectangle m_rDirty;
    private Vector m_vKids;
    
    public FancyContainer(final int nWidth, final int nHeight) {
        this.m_bFillBG = false;
        this.m_vKids = new Vector();
        this.m_pixels = null;
        this.m_imageSrc = null;
        this.m_helper = new FancyHelper();
        this.m_rDirty = new Rectangle();
        this.m_bCompatible = false;
        this.m_bMacNSHack = false;
        this.m_bNSHack = false;
        this.m_c = null;
        this.m_nHackCount = 0;
        super.m_nWidth = nWidth;
        super.m_nHeight = nHeight;
        this.setupImage();
        this.m_c = null;
    }
    
    public FancyContainer(final int nWidth, final int nHeight, final Component comp) {
        this.m_bFillBG = false;
        this.m_vKids = new Vector();
        this.m_pixels = null;
        this.m_imageSrc = null;
        this.m_helper = new FancyHelper();
        this.m_rDirty = new Rectangle();
        this.m_bCompatible = false;
        this.m_bMacNSHack = false;
        this.m_bNSHack = false;
        this.m_c = null;
        this.m_nHackCount = 0;
        super.m_nWidth = nWidth;
        super.m_nHeight = nHeight;
        this.setupImage();
        this.m_c = comp;
    }
    
    public boolean add(final SpriteBase sb, final SpriteBase after) {
        if (!(sb instanceof FancySprite)) {
            return false;
        }
        if (this.m_vKids.contains(sb)) {
            this.m_vKids.removeElement(sb);
        }
        if (after == null) {
            this.m_vKids.insertElementAt(sb, 0);
        }
        else {
            final int i = this.m_vKids.indexOf(after);
            this.m_vKids.insertElementAt(sb, i + 1);
        }
        return true;
    }
    
    public boolean add(final SpriteBase sb) {
        if (!(sb instanceof FancySprite)) {
            return false;
        }
        if (this.m_vKids.contains(sb)) {
            this.m_vKids.removeElement(sb);
        }
        this.m_vKids.addElement(sb);
        this.addToBounding();
        return true;
    }
    
    public boolean addBefore(final SpriteBase sb, final SpriteBase before) {
        if (!(sb instanceof FancySprite)) {
            return false;
        }
        if (this.m_vKids.contains(sb)) {
            this.m_vKids.removeElement(sb);
        }
        if (before == null) {
            this.m_vKids.insertElementAt(sb, 0);
        }
        else {
            int i = this.m_vKids.indexOf(before);
            if (i < 0) {
                i = 0;
            }
            this.m_vKids.insertElementAt(sb, i);
        }
        return true;
    }
    
    public int count() {
        return this.m_vKids.size();
    }
    
    public void drawSprite(final Graphics g) {
        if (!this.visible()) {
            return;
        }
        if (super.m_image == null && !this.m_bCompatible && !this.m_bNSHack) {
            this.setupImage();
        }
        if (this.m_rDirty.width != 0) {
            if (this.m_bFillBG) {
                this.m_helper.fillRect(this.m_pixels, super.m_nWidth, super.m_nHeight, this.m_nClrBG, this.m_rDirty);
            }
            synchronized (this.m_vKids) {
                for (int l = this.m_vKids.size(), i = 0; i < l; ++i) {
                    final FancySprite sb = this.m_vKids.elementAt(i);
                    if (sb != null && this.m_rDirty.intersects(((SpriteBase)sb).getBounding())) {
                        sb.drawFancy(this.m_pixels, super.m_nWidth, super.m_nHeight, this.m_rDirty);
                    }
                }
            }
            if (this.m_bCompatible || this.m_bNSHack) {
                final ColorModel cm = this.m_helper.getColorModel();
                if (super.m_image != null) {
                    super.m_image.flush();
                }
                this.m_imageSrc = new MemoryImageSource(super.m_nWidth, super.m_nHeight, cm, this.m_pixels, 0, super.m_nWidth);
                super.m_image = Toolkit.getDefaultToolkit().createImage(this.m_imageSrc);
                this.m_nHackCount = 0;
            }
            else {
                ++this.m_nHackCount;
                if (this.m_bMacNSHack) {
                    this.m_imageSrc.newPixels();
                }
                else {
                    this.m_imageSrc.newPixels(this.m_rDirty.x, this.m_rDirty.y, this.m_rDirty.width, this.m_rDirty.height);
                }
            }
            this.m_rDirty.reshape(0, 0, 0, 0);
        }
        super.drawSprite(g);
    }
    
    public SpriteBase elementAt(final int i) {
        synchronized (this.m_vKids) {
            if (i < 0 || i >= this.m_vKids.size()) {
                return null;
            }
            return this.m_vKids.elementAt(i);
        }
    }
    
    public Rectangle getChildBounds() {
        final Rectangle r = new Rectangle();
        synchronized (this.m_vKids) {
            for (int l = this.m_vKids.size(), i = 0; i < l; ++i) {
                final SpriteBase sb = this.m_vKids.elementAt(i);
                if (sb != null) {
                    this.addToRect(r, sb.getBounding());
                }
            }
        }
        return r;
    }
    
    public FancyHelper getFancyHelper() {
        return this.m_helper;
    }
    
    public boolean nextFrame(final int nFrame, final Rectangle rBound) {
        boolean b = false;
        synchronized (this.m_vKids) {
            for (int l = this.m_vKids.size(), i = 0; i < l; ++i) {
                final SpriteBase sb = this.m_vKids.elementAt(i);
                if (sb != null) {
                    b = (sb.nextFrame(nFrame, this.m_rDirty) || b);
                }
            }
        }
        if (b) {
            final Rectangle rDirty = this.m_rDirty;
            rDirty.x += super.m_nX;
            final Rectangle rDirty2 = this.m_rDirty;
            rDirty2.y += super.m_nY;
            this.addToBounding(this.m_rDirty);
            final Rectangle rDirty3 = this.m_rDirty;
            rDirty3.x -= super.m_nX;
            final Rectangle rDirty4 = this.m_rDirty;
            rDirty4.y -= super.m_nY;
        }
        return super.nextFrame(nFrame, rBound);
    }
    
    public void refresh(final Rectangle r) {
        this.addToRect(this.m_rDirty, r);
        final Rectangle rDirty = this.m_rDirty;
        rDirty.x += super.m_nX;
        final Rectangle rDirty2 = this.m_rDirty;
        rDirty2.y += super.m_nY;
        this.addToBounding(this.m_rDirty);
        final Rectangle rDirty3 = this.m_rDirty;
        rDirty3.x -= super.m_nX;
        final Rectangle rDirty4 = this.m_rDirty;
        rDirty4.y -= super.m_nY;
    }
    
    public void refresh() {
        this.m_rDirty.reshape(0, 0, super.m_nWidth, super.m_nHeight);
        this.addToBounding();
    }
    
    public boolean remove(final SpriteBase sb) {
        if (!(sb instanceof FancySprite)) {
            return false;
        }
        if (this.m_vKids.contains(sb)) {
            this.m_vKids.removeElement(sb);
        }
        return true;
    }
    
    public void removeAll() {
        this.addToBounding();
        this.m_vKids.removeAllElements();
        this.addToBounding();
    }
    
    public void setBGColor(final Color clrBG) {
        if (clrBG != null) {
            this.m_nClrBG = this.m_helper.getPixelValue(clrBG.getRed(), clrBG.getGreen(), clrBG.getBlue());
            this.m_bFillBG = true;
        }
        else {
            this.m_bFillBG = false;
        }
        this.addToBounding();
    }
    
    public void setColorBalance(final int nAlpha, final int nRed, final int nGreen, final int nBlue) {
        synchronized (this.m_vKids) {
            for (int l = this.m_vKids.size(), i = 0; i < l; ++i) {
                final FancySprite sb = this.m_vKids.elementAt(i);
                if (sb != null) {
                    sb.setColorBalance(nAlpha, nRed, nGreen, nBlue);
                }
            }
        }
    }
    
    public void setSize(final int nw, final int nh) {
        this.addToBounding();
        super.m_nWidth = nw;
        super.m_nHeight = nh;
        this.setupImage();
        this.addToBounding();
    }
    
    private synchronized void setupImage() {
        final ColorModel cm = this.m_helper.getColorModel();
        this.m_pixels = new int[super.m_nWidth * super.m_nHeight];
        this.m_imageSrc = new MemoryImageSource(super.m_nWidth, super.m_nHeight, cm, this.m_pixels, 0, super.m_nWidth);
        this.m_bCompatible = false;
        try {
            this.m_imageSrc.setAnimated(true);
            super.m_image = Toolkit.getDefaultToolkit().createImage(this.m_imageSrc);
            this.m_nHackCount = 0;
        }
        catch (NoSuchMethodError e2) {
            System.out.println("Running in Java1.0 mode");
            this.m_bCompatible = true;
        }
        catch (RuntimeException e) {
            e.printStackTrace();
        }
        this.m_bNSHack = false;
        this.m_bMacNSHack = false;
        final String strVendor = System.getProperty("java.vendor");
        if (strVendor != null && strVendor.indexOf("Netscape") != -1) {
            this.m_bNSHack = true;
            final String strOs = System.getProperty("os.name");
            if (strOs != null && strOs.startsWith("Mac")) {
                this.m_bMacNSHack = true;
            }
        }
        this.refresh();
    }
}
