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
    
    fgClass(final Applet app, final int x, final int y) {
        this.mt = new MediaTracker(app);
        this.screenWidth = x;
        this.screenHeight = y;
        this.fgdivide = 0;
        String tempstr = app.getParameter("fgdivide");
        if (tempstr != null) {
            this.fgdivide = Integer.parseInt(tempstr, 10);
        }
        int max;
        final int n = max = 0;
        this.numLoaded = n;
        this.numImg = n;
        tempstr = app.getParameter("fgmax");
        if (tempstr != null) {
            max = Integer.parseInt(tempstr, 10);
            if (max > 0) {
                for (int i = 1; i <= max; ++i) {
                    if (app.getParameter("fgimage" + i) != null) {
                        ++this.numImg;
                    }
                }
                if (this.numImg > 0) {
                    this.data = new fgImage[this.numImg];
                }
            }
        }
        int count = 0;
        for (int i = 1; i <= max; ++i) {
            if (app.getParameter("fgimage" + i) != null) {
                this.data[count] = new fgImage();
                final fgImage tg = this.data[count];
                tg.img = app.getImage(app.getCodeBase(), app.getParameter("fgimage" + i));
                this.mt.addImage(tg.img, count);
                tempstr = app.getParameter("fgxpos" + i);
                if (tempstr != null) {
                    tg.xpos = Integer.parseInt(tempstr, 10);
                }
                tempstr = app.getParameter("fgypos" + i);
                if (tempstr != null) {
                    tg.ypos = Integer.parseInt(tempstr, 10);
                }
                tempstr = app.getParameter("fgxmove" + i);
                if (tempstr != null) {
                    tg.xmove = Integer.parseInt(tempstr, 10);
                }
                tempstr = app.getParameter("fgymove" + i);
                if (tempstr != null) {
                    tg.ymove = Integer.parseInt(tempstr, 10);
                }
                if (tg.xmove != 0 || tg.ymove != 0) {
                    tg.isMove = true;
                }
                ++count;
            }
        }
    }
    
    int allLoaded() {
        if (this.numLoaded < this.numImg) {
            final int status = this.mt.statusID(this.numLoaded, true);
            if (status != 1) {
                if (status == 4) {
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
            int max = this.fgdivide;
            if (max > this.numLoaded) {
                max = this.numLoaded;
            }
            for (int i = 0; i < max; ++i) {
                this.data[i].draw(g);
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
        boolean rt = false;
        for (int i = 0; i < this.numImg && !rt; rt |= this.data[i].isMove, ++i) {}
        return rt;
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
        
        public void move(final int x, final int y) {
            this.xpos += x;
            this.ypos += y;
        }
        
        public void setExist() {
            this.imgxsize = this.img.getWidth(null);
            this.imgysize = this.img.getHeight(null);
            this.exist = true;
        }
        
        public void setxy(final int x, final int y) {
            this.xpos = x;
            this.ypos = y;
        }
    }
}
