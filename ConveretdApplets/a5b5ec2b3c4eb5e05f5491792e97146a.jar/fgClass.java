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
    
    fgClass(final Applet applet, final int screenWidth, final int screenHeight) {
        this.mt = new MediaTracker(applet);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.fgdivide = 0;
        final String parameter = applet.getParameter("fgdivide");
        if (parameter != null) {
            this.fgdivide = Integer.parseInt(parameter, 10);
        }
        int int1;
        final int n = int1 = 0;
        this.numLoaded = n;
        this.numImg = n;
        final String parameter2 = applet.getParameter("fgmax");
        if (parameter2 != null) {
            int1 = Integer.parseInt(parameter2, 10);
            if (int1 > 0) {
                for (int i = 1; i <= int1; ++i) {
                    if (applet.getParameter("fgimage" + i) != null) {
                        ++this.numImg;
                    }
                }
                if (this.numImg > 0) {
                    this.data = new fgImage[this.numImg];
                }
            }
        }
        int n2 = 0;
        for (int j = 1; j <= int1; ++j) {
            if (applet.getParameter("fgimage" + j) != null) {
                this.data[n2] = new fgImage();
                final fgImage fgImage = this.data[n2];
                fgImage.img = applet.getImage(applet.getCodeBase(), applet.getParameter("fgimage" + j));
                this.mt.addImage(fgImage.img, n2);
                final String parameter3 = applet.getParameter("fgxpos" + j);
                if (parameter3 != null) {
                    fgImage.xpos = Integer.parseInt(parameter3, 10);
                }
                final String parameter4 = applet.getParameter("fgypos" + j);
                if (parameter4 != null) {
                    fgImage.ypos = Integer.parseInt(parameter4, 10);
                }
                final String parameter5 = applet.getParameter("fgxmove" + j);
                if (parameter5 != null) {
                    fgImage.xmove = Integer.parseInt(parameter5, 10);
                }
                final String parameter6 = applet.getParameter("fgymove" + j);
                if (parameter6 != null) {
                    fgImage.ymove = Integer.parseInt(parameter6, 10);
                }
                if (fgImage.xmove != 0 || fgImage.ymove != 0) {
                    fgImage.isMove = true;
                }
                ++n2;
            }
        }
    }
    
    int allLoaded() {
        if (this.numLoaded < this.numImg) {
            final int statusID = this.mt.statusID(this.numLoaded, true);
            if (statusID != 1) {
                if (statusID == 4) {
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
    
    void draw(final Graphics graphics) {
        if (this.allLoaded() > 0) {
            for (int i = 0; i < this.numLoaded; ++i) {
                this.data[i].draw(graphics);
            }
        }
    }
    
    void drawOne(final Graphics graphics) {
        if (this.allLoaded() > 0) {
            int n = this.fgdivide;
            if (n > this.numLoaded) {
                n = this.numLoaded;
            }
            for (int i = 0; i < n; ++i) {
                this.data[i].draw(graphics);
            }
        }
    }
    
    void drawTwo(final Graphics graphics) {
        if (this.allLoaded() > 0 && this.fgdivide < this.numLoaded) {
            for (int i = this.fgdivide; i < this.numLoaded; ++i) {
                this.data[i].draw(graphics);
            }
        }
    }
    
    boolean isMoving() {
        boolean b = false;
        for (int n = 0; n < this.numImg && !b; b |= this.data[n].isMove, ++n) {}
        return b;
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
        
        public void draw(final Graphics graphics) {
            if (this.exist) {
                graphics.drawImage(this.img, this.xpos, this.ypos, null);
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
        
        public void move(final int n, final int n2) {
            this.xpos += n;
            this.ypos += n2;
        }
        
        public void setExist() {
            this.imgxsize = this.img.getWidth(null);
            this.imgysize = this.img.getHeight(null);
            this.exist = true;
        }
        
        public void setxy(final int xpos, final int ypos) {
            this.xpos = xpos;
            this.ypos = ypos;
        }
    }
}
