import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Component;
import java.applet.Applet;
import java.awt.MediaTracker;

// 
// Decompiled by Procyon v0.5.30
// 

class fgClass
{
    fgImage[] data;
    int screenWidth;
    int screenHeight;
    int numImg;
    int numLoaded;
    int fgdivide;
    MediaTracker mt;
    
    fgClass(final Applet applet, final int i, final int j) {
        this.mt = new MediaTracker(applet);
        this.screenWidth = i;
        this.screenHeight = j;
        this.fgdivide = 0;
        String s = applet.getParameter("fgdivide");
        if (s != null) {
            this.fgdivide = Integer.parseInt(s, 10);
        }
        int k;
        final int n = k = 0;
        this.numLoaded = n;
        this.numImg = n;
        s = applet.getParameter("fgmax");
        if (s != null) {
            k = Integer.parseInt(s, 10);
            if (k > 0) {
                for (int i2 = 1; i2 <= k; ++i2) {
                    if (applet.getParameter("fgimage" + i2) != null) {
                        ++this.numImg;
                    }
                }
                if (this.numImg > 0) {
                    this.data = new fgImage[this.numImg];
                }
            }
        }
        int l = 0;
        for (int j2 = 1; j2 <= k; ++j2) {
            if (applet.getParameter("fgimage" + j2) != null) {
                this.data[l] = new fgImage();
                final fgImage fgimage = this.data[l];
                fgimage.img = applet.getImage(applet.getCodeBase(), applet.getParameter("fgimage" + j2));
                this.mt.addImage(fgimage.img, l);
                String s2 = applet.getParameter("fgxpos" + j2);
                if (s2 != null) {
                    fgimage.xpos = Integer.parseInt(s2, 10);
                }
                s2 = applet.getParameter("fgypos" + j2);
                if (s2 != null) {
                    fgimage.ypos = Integer.parseInt(s2, 10);
                }
                s2 = applet.getParameter("fgxmove" + j2);
                if (s2 != null) {
                    fgimage.xmove = Integer.parseInt(s2, 10);
                }
                s2 = applet.getParameter("fgymove" + j2);
                if (s2 != null) {
                    fgimage.ymove = Integer.parseInt(s2, 10);
                }
                if (fgimage.xmove != 0 || fgimage.ymove != 0) {
                    fgimage.isMove = true;
                }
                ++l;
            }
        }
    }
    
    int allLoaded() {
        if (this.numLoaded < this.numImg) {
            final int i = this.mt.statusID(this.numLoaded, true);
            if (i != 1) {
                if (i == 4) {
                    this.data[this.numLoaded].img = null;
                    System.err.println("ForeImage" + this.numLoaded + " has an Error.");
                }
                else {
                    this.data[this.numLoaded].setExist();
                    System.err.println("ForeImage" + this.numLoaded + " has been Loaded." + this.data[this.numLoaded].imgxsize + ":" + this.data[this.numLoaded].imgysize);
                }
                if (++this.numLoaded >= this.numImg) {
                    this.mt = null;
                    System.gc();
                }
            }
        }
        return this.numLoaded;
    }
    
    void draw(final Graphics g) {
        if (this.allLoaded() > 0) {
            for (int i = 0; i < this.numLoaded; ++i) {
                this.data[i].draw(g);
            }
        }
    }
    
    void drawOne(final Graphics g) {
        if (this.allLoaded() > 0) {
            int i = this.fgdivide;
            if (i > this.numLoaded) {
                i = this.numLoaded;
            }
            for (int j = 0; j < i; ++j) {
                this.data[j].draw(g);
            }
        }
    }
    
    void drawTwo(final Graphics g) {
        if (this.allLoaded() > 0 && this.fgdivide < this.numLoaded) {
            for (int i = this.fgdivide; i < this.numLoaded; ++i) {
                this.data[i].draw(g);
            }
        }
    }
    
    boolean isMoving() {
        boolean flag = false;
        for (int i = 0; i < this.numImg && !flag; flag |= this.data[i].isMove, ++i) {}
        return flag;
    }
    
    class fgImage
    {
        Image img;
        int imgxsize;
        int imgysize;
        int xpos;
        int ypos;
        int xmove;
        int ymove;
        boolean exist;
        boolean isMove;
        
        public void draw(final Graphics g) {
            if (this.exist) {
                g.drawImage(this.img, this.xpos, this.ypos, null);
            }
            this.move();
        }
        
        private void move() {
            if (this.isMove) {
                this.xpos += this.xmove;
                this.ypos += this.ymove;
                if (this.xpos > fgClass.this.screenWidth) {
                    this.xpos = -this.imgxsize;
                }
                else if (this.xpos < -this.imgxsize) {
                    this.xpos = fgClass.this.screenWidth;
                }
                if (this.ypos > fgClass.this.screenHeight) {
                    this.ypos = -this.imgysize;
                }
                else if (this.ypos < -this.imgysize) {
                    this.ypos = fgClass.this.screenHeight;
                }
            }
        }
        
        public void move(final int i, final int j) {
            this.xpos += i;
            this.ypos += j;
        }
        
        public void setExist() {
            this.imgxsize = this.img.getWidth(null);
            this.imgysize = this.img.getHeight(null);
            this.exist = true;
        }
        
        public void setxy(final int i, final int j) {
            this.xpos = i;
            this.ypos = j;
        }
        
        fgImage() {
            final boolean b = false;
            this.isMove = b;
            this.exist = b;
            final boolean b2 = false;
            this.imgysize = (b2 ? 1 : 0);
            this.imgxsize = (b2 ? 1 : 0);
            this.ymove = (b2 ? 1 : 0);
            this.xmove = (b2 ? 1 : 0);
            this.ypos = (b2 ? 1 : 0);
            this.xpos = (b2 ? 1 : 0);
        }
    }
}
