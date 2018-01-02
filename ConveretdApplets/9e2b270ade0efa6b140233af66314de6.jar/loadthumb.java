import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class loadthumb
{
    private Applet mainapp;
    private Image[] img;
    private int imgCount;
    private int imgLoaded;
    private int width;
    private int height;
    private int cur;
    private MediaTracker mt;
    private Color bdcolor;
    private Color hvcolor;
    public Color bgcolor;
    private int thXstart;
    private int thYstart;
    private int thWidth;
    private int thHeight;
    private int columns;
    private int fadeSpeed;
    private int fadeCount;
    private int fadeSize;
    private long nextcycle;
    private long delayInterval;
    private long maxb4show;
    private boolean imgAllLoaded;
    int overPic;
    int overCount;
    boolean overValue;
    boolean overStart;
    int drawXpos;
    int drawYpos;
    int drawWidth;
    int drawHeight;
    int goalXpos;
    int goalYpos;
    int goalWidth;
    int goalHeight;
    int goalPercent;
    int goalSpeed;
    
    loadthumb(final Applet mainapp, final int width, final int height) {
        this.width = width;
        this.height = height;
        this.mainapp = mainapp;
        this.imgAllLoaded = false;
        this.maxb4show = 0L;
        this.bgcolor = Color.black;
        final String parameter = mainapp.getParameter("bgcolor");
        if (parameter != null) {
            this.bgcolor = new Color(Integer.parseInt(parameter, 16));
        }
        this.bdcolor = Color.yellow;
        final String parameter2 = mainapp.getParameter("bordercolor");
        if (parameter2 != null) {
            this.bdcolor = new Color(Integer.parseInt(parameter2, 16));
        }
        this.hvcolor = Color.white;
        final String parameter3 = mainapp.getParameter("hovercolor");
        if (parameter3 != null) {
            this.hvcolor = new Color(Integer.parseInt(parameter3, 16));
        }
        this.delayInterval = 3000L;
        final String parameter4 = mainapp.getParameter("delay");
        if (parameter4 != null) {
            this.delayInterval = Long.parseLong(parameter4);
            if (this.delayInterval < 0L) {
                this.delayInterval = 0L;
            }
        }
        this.nextcycle = System.currentTimeMillis() + 3000L;
        this.imgCount = 0;
        this.imgLoaded = 0;
        this.cur = 0;
        final String parameter5 = mainapp.getParameter("maximages");
        if (parameter5 != null) {
            this.imgCount = Integer.parseInt(parameter5);
            if (this.imgCount > 0) {
                System.err.println("Init ImgCount = " + this.imgCount);
                this.img = new Image[this.imgCount];
                this.mt = new MediaTracker(mainapp);
                this.getNextImg();
            }
        }
        this.columns = 0;
        final String parameter6 = mainapp.getParameter("columns");
        if (parameter6 != null) {
            this.columns = this.convertString(parameter6);
        }
        if (this.columns == 0) {
            int n = this.width / this.imgCount << 1;
            if (n <= 0) {
                n = 1;
            }
            int n2 = this.height / n;
            if (n2 <= 0) {
                n2 = 1;
            }
            this.columns = (this.imgCount + n2 - 1) / n2;
            System.err.println("C=" + this.columns + ",x=" + n + ",y=" + n2);
        }
        this.thWidth = this.width / this.columns;
        this.thHeight = this.height / ((this.imgCount + this.columns - 1) / this.columns);
        final String parameter7 = mainapp.getParameter("shape");
        if (parameter7 != null && parameter7.toLowerCase().equals("square")) {
            if (this.thWidth > this.thHeight) {
                this.thWidth = this.thHeight;
            }
            else {
                this.thHeight = this.thWidth;
            }
        }
        final String parameter8 = mainapp.getParameter("thumbwidth");
        if (parameter8 != null) {
            final int convertString = this.convertString(parameter8);
            if (convertString < this.thWidth && convertString > 5) {
                this.thWidth = convertString;
            }
        }
        final String parameter9 = mainapp.getParameter("thumbheight");
        if (parameter9 != null) {
            final int convertString2 = this.convertString(parameter9);
            if (convertString2 < this.thHeight && convertString2 > 5) {
                this.thHeight = convertString2;
            }
        }
        this.isOverReset();
        if (this.thWidth > this.thHeight) {
            this.fadeSize = this.thHeight >> 1;
        }
        else {
            this.fadeSize = this.thWidth >> 1;
        }
        this.thXstart = this.width - this.thWidth * this.columns >> 1;
        this.thYstart = this.height - this.thHeight * ((this.imgCount + this.columns - 1) / this.columns) >> 1;
        final String parameter10 = mainapp.getParameter("xalign");
        if (parameter10 != null) {
            if (parameter10.toLowerCase().equals("left")) {
                this.thXstart = 0;
            }
            else if (parameter10.toLowerCase().equals("right")) {
                this.thXstart = this.width - this.thWidth * this.columns;
            }
        }
        final String parameter11 = mainapp.getParameter("yalign");
        if (parameter11 != null) {
            if (parameter11.toLowerCase().equals("top")) {
                this.thYstart = 0;
            }
            else if (parameter11.toLowerCase().equals("bottom")) {
                this.thYstart = this.height - this.thHeight * ((this.imgCount + this.columns - 1) / this.columns);
            }
        }
        final String parameter12 = mainapp.getParameter("loadall");
        if (parameter12 != null && parameter12.toLowerCase().equals("yes")) {
            this.maxb4show = this.imgCount - 1;
            System.err.println("Load All");
        }
    }
    
    int allLoaded() {
        if (this.imgLoaded < this.imgCount) {
            final int statusID = this.mt.statusID(this.imgLoaded, true);
            if ((statusID & 0x1) != 0x0) {
                return this.imgLoaded;
            }
            if ((statusID & 0x8) != 0x0) {
                if ((statusID & 0x6) != 0x0) {
                    this.img[this.imgLoaded] = null;
                    System.err.println("Image" + this.imgLoaded + " has an Error");
                }
                else {
                    System.err.println(String.valueOf(this.imgLoaded) + ": Image" + this.imgLoaded + " has been Loaded." + this.img[this.imgLoaded].getWidth(null) + ":" + this.img[this.imgLoaded].getHeight(null) + "\n");
                }
                if (++this.imgLoaded >= this.imgCount) {
                    this.mt = null;
                    System.gc();
                }
                else {
                    while (this.imgLoaded < this.imgCount && !this.getNextImg()) {
                        ++this.imgLoaded;
                    }
                }
                this.imgAllLoaded = true;
            }
            else if ((statusID & 0x1) == 0x0 && (statusID & 0x6) != 0x0) {
                this.img[this.imgLoaded] = null;
                System.err.println(String.valueOf(this.imgLoaded) + ": Image" + this.imgLoaded + " has an Error.\n");
                if (++this.imgLoaded >= this.imgCount) {
                    this.mt = null;
                    System.gc();
                    this.imgAllLoaded = true;
                }
                else {
                    while (this.imgLoaded < this.imgCount && !this.getNextImg()) {
                        ++this.imgLoaded;
                    }
                }
            }
        }
        return this.imgLoaded;
    }
    
    boolean backFade() {
        final int fadeCount = this.fadeCount - this.fadeSpeed;
        this.fadeCount = fadeCount;
        if (fadeCount < 4) {
            this.fadeCount = 4;
            return true;
        }
        if ((this.fadeSpeed -= 2) < 1) {
            this.fadeSpeed = 1;
        }
        return false;
    }
    
    void clearGraphics(final Graphics graphics) {
        graphics.setColor(this.bgcolor);
        graphics.fillRect(0, 0, this.width, this.height);
    }
    
    int convertString(final String s) {
        int int1;
        try {
            int1 = Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            int1 = 0;
        }
        return int1;
    }
    
    void draw(final Graphics graphics) {
        if (this.allLoaded() > this.maxb4show) {
            if (this.overStart) {
                switch (this.overCount) {
                    case 0: {
                        if (this.nextFade()) {
                            ++this.overCount;
                            this.setGoal();
                        }
                        this.clearGraphics(graphics);
                        this.drawThumbnails(graphics, this.fadeCount);
                        break;
                    }
                    case 1: {
                        this.drawPicture(graphics);
                        if (this.drawPictureNext()) {
                            ++this.overCount;
                            break;
                        }
                        break;
                    }
                    case 2: {
                        this.clearGraphics(graphics);
                        ++this.overCount;
                    }
                    case 3: {
                        graphics.drawImage(this.img[this.overPic], this.goalXpos, this.goalYpos, this.goalWidth, this.goalHeight, null);
                        break;
                    }
                    case 4: {
                        this.drawPicture(graphics);
                        if (this.drawPictureBack()) {
                            ++this.overCount;
                            break;
                        }
                        break;
                    }
                    case 5: {
                        this.clearGraphics(graphics);
                        ++this.overCount;
                    }
                    case 6: {
                        if (this.backFade()) {
                            ++this.overCount;
                        }
                        this.drawThumbnails(graphics, this.fadeCount);
                        break;
                    }
                    case 7: {
                        this.overStart = false;
                        break;
                    }
                }
            }
            else if (this.imgAllLoaded) {
                this.imgAllLoaded = false;
                this.nextFade();
                this.clearGraphics(graphics);
                this.drawThumbnails(graphics, 5);
                this.clearGraphics(graphics);
                this.drawThumbnails(graphics);
            }
            else {
                this.drawThumbnails(graphics);
            }
        }
    }
    
    void drawPicture(final Graphics graphics) {
        graphics.setColor(this.bgcolor);
        graphics.fillRect(0, 0, this.width, this.height);
        final double n = 1.0 - this.goalPercent / 60.0;
        graphics.drawImage(this.img[this.overPic], this.goalXpos - (int)((this.goalXpos - this.drawXpos) * n), this.goalYpos - (int)((this.goalYpos - this.drawYpos) * n), this.goalWidth - (int)((this.goalWidth - this.drawWidth) * n), this.goalHeight - (int)((this.goalHeight - this.drawHeight) * n), null);
    }
    
    boolean drawPictureBack() {
        final int goalPercent = this.goalPercent - this.goalSpeed;
        this.goalPercent = goalPercent;
        if (goalPercent < 1) {
            this.goalPercent = 0;
            return true;
        }
        this.goalSpeed -= 3;
        return false;
    }
    
    boolean drawPictureNext() {
        final int goalPercent = this.goalPercent + this.goalSpeed;
        this.goalPercent = goalPercent;
        if (goalPercent > 60) {
            this.goalPercent = 60;
            return true;
        }
        this.goalSpeed += 3;
        return false;
    }
    
    void drawThumbnails(final Graphics graphics) {
        this.drawThumbnails(graphics, 4);
    }
    
    void drawThumbnails(final Graphics graphics, final int n) {
        final int n2 = n + n;
        int n3 = 0;
        int n4 = this.thXstart + n;
        int n5 = this.thYstart + n;
        final int n6 = this.thWidth - n2;
        final int n7 = this.thHeight - n2;
        graphics.setColor(this.bdcolor);
        for (int i = 0; i < this.imgLoaded; ++i) {
            if (this.img[i] != null) {
                if ((this.overStart | this.overValue) && i == this.overPic && !this.imgAllLoaded) {
                    this.drawXpos = n4 - n + 4;
                    this.drawYpos = n5 - n + 4;
                    this.drawWidth = this.thWidth - 8;
                    this.drawHeight = this.thHeight - 8;
                    graphics.drawImage(this.img[i], this.drawXpos, this.drawYpos, this.drawWidth, this.drawHeight, null);
                    graphics.setColor(this.hvcolor);
                    graphics.drawRect(this.drawXpos - 1, this.drawYpos - 1, this.drawWidth + 2, this.drawHeight + 2);
                    graphics.setColor(Color.black);
                    graphics.drawRect(this.drawXpos, this.drawYpos, this.drawWidth, this.drawHeight);
                    graphics.setColor(this.bdcolor);
                }
                else if (n6 > 0 && n7 > 0) {
                    graphics.drawImage(this.img[i], n4, n5, n6, n7, null);
                    graphics.drawRect(n4 - 1, n5 - 1, n6 + 2, n7 + 2);
                }
            }
            n4 += this.thWidth;
            if (++n3 >= this.columns) {
                n3 = 0;
                n4 = this.thXstart + n;
                n5 += this.thHeight;
            }
        }
        for (int j = this.imgLoaded; j < this.imgCount; ++j) {
            if (this.img[j] != null) {
                graphics.drawImage(this.img[j], n4, n5, this.thWidth - n2, this.thHeight - n2, null);
            }
            graphics.drawRect(n4 - 1, n5 - 1, this.thWidth - n2 + 2, this.thHeight - n2 + 2);
            n4 += this.thWidth;
            if (++n3 >= this.columns) {
                n3 = 0;
                n4 = this.thXstart + n;
                n5 += this.thHeight;
            }
        }
    }
    
    boolean getNextImg() {
        final String parameter = this.mainapp.getParameter("image" + (this.imgLoaded + 1));
        if (parameter != null) {
            System.err.println(String.valueOf(this.imgLoaded) + ": " + parameter);
            this.img[this.imgLoaded] = this.mainapp.getImage(this.mainapp.getCodeBase(), parameter);
            this.mt.addImage(this.img[this.imgLoaded], this.imgLoaded);
            return true;
        }
        this.img[this.imgLoaded] = null;
        return false;
    }
    
    boolean isOver(final eventClass eventClass) {
        if (!this.overStart || this.overCount >= 6) {
            final int n = this.thWidth - 8;
            final int n2 = this.thHeight - 8;
            boolean overValue = false;
            int n3 = 0;
            int n4 = this.thXstart + 4;
            int n5 = this.thYstart + 4;
            for (int overPic = 0; overPic < this.imgLoaded && !overValue; ++overPic) {
                if (this.img[overPic] != null && eventClass.xpos > n4 && eventClass.xpos < n4 + n && eventClass.ypos > n5 && eventClass.ypos < n5 + n2) {
                    overValue = true;
                    this.overPic = overPic;
                    if (eventClass.mou_down) {
                        this.startFade();
                    }
                }
                n4 += this.thWidth;
                if (++n3 >= this.columns) {
                    n3 = 0;
                    n4 = this.thXstart + 4;
                    n5 += this.thHeight;
                }
            }
            return this.overValue = overValue;
        }
        if (this.overCount != 3 && this.overCount != 1) {
            return false;
        }
        if (eventClass.xpos > this.goalXpos && eventClass.xpos < this.goalXpos + this.goalWidth && eventClass.ypos > this.goalYpos && eventClass.ypos < this.goalYpos + this.goalHeight) {
            if (eventClass.mou_down) {
                this.overCount = 4;
            }
            return true;
        }
        return false;
    }
    
    void isOverReset() {
        final boolean b = false;
        this.overStart = b;
        this.overValue = b;
        this.overPic = -1;
    }
    
    boolean nextFade() {
        this.fadeSpeed += 2;
        final int fadeCount = this.fadeCount + this.fadeSpeed;
        this.fadeCount = fadeCount;
        if (fadeCount > this.fadeSize) {
            this.fadeCount = this.fadeSize;
            return true;
        }
        return false;
    }
    
    void setGoal() {
        this.goalWidth = this.img[this.overPic].getWidth(null);
        this.goalHeight = this.img[this.overPic].getHeight(null);
        this.goalXpos = this.width - this.goalWidth >> 1;
        this.goalYpos = this.height - this.goalHeight >> 1;
        this.goalPercent = 1;
        this.goalSpeed = 1;
        if (this.goalXpos < 0) {
            final int n = this.goalHeight * (this.goalXpos / this.goalWidth);
            this.goalWidth += this.goalXpos * 2;
            this.goalXpos = 0;
        }
        if (this.goalYpos < 0) {
            final int n2 = this.goalWidth * (this.goalYpos / this.goalHeight);
            this.goalHeight += this.goalYpos * 2;
            this.goalYpos = 0;
        }
    }
    
    void startFade() {
        this.fadeCount = 4;
        this.fadeSpeed = 2;
        this.overStart = true;
        this.overCount = 0;
    }
}
