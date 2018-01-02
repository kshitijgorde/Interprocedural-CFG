import java.awt.Color;
import java.awt.Event;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class ConsultCanvas extends Canvas
{
    ConsultTreeTrial consultTree;
    Vector folderVector;
    ConsultFolder folder;
    ConsultFolder childFolder;
    Image offscreenImg;
    ConsultFolder thisFolder;
    Graphics og;
    Font f;
    int downx;
    int downy;
    int starty;
    int startx;
    int x;
    int y;
    boolean border;
    boolean first;
    boolean mousedrag;
    boolean showCross;
    long crossTime;
    boolean folderClicked;
    boolean jump;
    boolean scrollFirst;
    
    ConsultCanvas(final ConsultTreeTrial consultTree, final Vector folderVector) {
        this.starty = 200;
        this.startx = 200;
        this.x = -(this.startx - 20);
        this.y = -(this.starty - 6);
        this.border = true;
        this.first = true;
        this.mousedrag = false;
        this.showCross = false;
        this.folderClicked = false;
        this.jump = false;
        this.scrollFirst = true;
        this.consultTree = consultTree;
        this.folderVector = folderVector;
        this.setBackground(consultTree.backgroundColor);
        this.thisFolder = consultTree.rootFolder;
        this.border = consultTree.border;
    }
    
    public void update(final Graphics g) {
        this.paint(g);
        if (this.consultTree.scrollBar & this.scrollFirst & ConsultFolder.y - this.starty + 23 <= this.size().height) {
            this.consultTree.Mypanel.vs.hide();
            this.scrollFirst = false;
            this.paint(g);
        }
    }
    
    public void paint(final Graphics g) {
        if (this.border) {
            g.setColor(this.consultTree.borderColor);
            for (int j = 0; j < 3; ++j) {
                g.draw3DRect(j, j, this.consultTree.size().width - j * 2 - 1, this.consultTree.size().height - j * 2 - 1, this.consultTree.borderRaised);
            }
            g.clipRect(4, 4, this.size().width - 8, this.size().height - 8);
        }
        if (this.offscreenImg != null) {
            g.drawImage(this.offscreenImg, this.x, this.y, this);
        }
    }
    
    void drawTree() {
        ConsultFolder folder = this.consultTree.rootFolder;
        if (this.offscreenImg == null) {
            this.offscreenImg = this.createImage(this.startx + this.consultTree.canvasWidth, this.starty + this.consultTree.canvasHeight);
            (this.og = this.offscreenImg.getGraphics()).setColor(this.consultTree.backgroundColor);
            this.og.setFont(folder.f);
        }
        this.og.setColor(this.consultTree.backgroundColor);
        this.og.fillRect(0, 0, this.offscreenImg.getWidth(this), this.offscreenImg.getHeight(this));
        this.og.setColor(this.consultTree.dotColor);
        Enumeration e = this.folderVector.elements();
        while (e.hasMoreElements()) {
            folder = e.nextElement();
            if (folder.active) {
                this.childFolder = this.findLastChild(folder);
                if (this.childFolder == null || !this.childFolder.show) {
                    continue;
                }
                this.drawVertDot(folder.xpos + folder.width / 2, folder.ypos + folder.width / 2, folder.xpos + folder.width / 2, this.childFolder.ypos + this.childFolder.width / 2 - 1, this.og);
            }
        }
        e = this.folderVector.elements();
        while (e.hasMoreElements()) {
            folder = e.nextElement();
            if (folder.show) {
                this.og.drawImage(folder.folderImage[folder.state], folder.xpos, folder.ypos, this);
                this.og.setColor(folder.color);
                this.og.fillRect(folder.xpos + folder.width + 3, folder.ypos + folder.height / 2 - 8, folder.discSize, 12);
                this.og.setColor(this.consultTree.descriptionBorder);
                this.og.drawRect(folder.xpos + folder.width + 3, folder.ypos + folder.height / 2 - 8, folder.discSize, 12);
                this.og.setColor(this.consultTree.textColor);
                this.og.drawString(folder.thisFolderName, folder.xpos + folder.width + 5, folder.ypos + folder.height / 2 + 2);
                this.og.setColor(this.consultTree.dotColor);
                this.drawHorDot(folder.xpos - folder.width / 2, folder.ypos + folder.width / 2 - 2, folder.xpos, folder.ypos + folder.width / 2 - 2, this.og);
                if (!folder.haveChild) {
                    continue;
                }
                this.og.setColor(this.consultTree.backgroundColor);
                this.og.fillRect(folder.xpos - folder.width / 2 - 4, folder.ypos + folder.width / 2 - 6, 8, 8);
                this.og.setColor(this.consultTree.boxColor);
                this.og.drawRect(folder.xpos - folder.width / 2 - 4, folder.ypos + folder.width / 2 - 6, 8, 8);
                this.og.setColor(this.consultTree.plusColor);
                if (folder.active) {
                    this.og.drawLine(folder.xpos - folder.width / 2 - 3, folder.ypos + folder.height / 2 - 3, folder.xpos - folder.width / 2 + 3, folder.ypos + folder.height / 2 - 3);
                }
                else {
                    this.og.drawLine(folder.xpos - folder.width / 2 - 3, folder.ypos + folder.height / 2 - 3, folder.xpos - folder.width / 2 + 3, folder.ypos + folder.height / 2 - 3);
                    this.og.drawLine(folder.xpos - folder.width / 2, folder.ypos + folder.height / 2 - 6, folder.xpos - folder.width / 2, folder.ypos + folder.height / 2);
                }
            }
        }
        if (!this.scrollFirst) {
            this.hideBar();
        }
    }
    
    void hideBar() {
        if (!this.consultTree.scrollBar) {
            return;
        }
        if (ConsultFolder.y - this.starty + 23 > this.size().height) {
            this.consultTree.Mypanel.vs.show();
            return;
        }
        if (this.y >= -194) {
            this.consultTree.Mypanel.vs.hide();
        }
    }
    
    ConsultFolder findLastChild(final ConsultFolder parent) {
        ConsultFolder childFolder = null;
        final Enumeration e = parent.childVector.elements();
        while (e.hasMoreElements()) {
            childFolder = e.nextElement();
        }
        return childFolder;
    }
    
    void pause(final int time) {
        try {
            Thread.sleep(time);
        }
        catch (InterruptedException ex) {}
    }
    
    void drawVertDot(final int topX, final int topY, final int botX, final int botY, final Graphics g) {
        for (int i = topY; i <= botY; i += 2) {
            g.drawLine(topX, i, botX, i);
        }
    }
    
    void drawHorDot(final int leftX, final int leftY, final int rightX, final int rightY, final Graphics g) {
        for (int i = leftX + 1; i <= rightX; i += 2) {
            g.drawLine(i, leftY, i, rightY);
        }
    }
    
    void boxClicked(final int x, final int y) {
        final Enumeration e = this.folderVector.elements();
        while (e.hasMoreElements()) {
            this.folder = e.nextElement();
            if (x > this.folder.xpos - this.folder.width / 2 - 4 + this.x && x < this.folder.xpos - this.folder.width / 2 + 4 + this.x && y > this.folder.ypos + this.folder.height / 2 - 7 + this.y && y < this.folder.ypos + this.folder.height / 2 + 1 + this.y && this.folder.show) {
                if (this.folder.active) {
                    this.folder.active = false;
                }
                else {
                    this.folder.active = true;
                    this.folder.setUpchild();
                }
                ConsultFolder.y = -this.consultTree.rootFolder.height + this.starty;
                this.consultTree.rootFolder.setUpPos(this.startx);
                this.drawTree();
                this.repaint();
            }
        }
    }
    
    void folderClicked(final int x, final int y) {
        final Enumeration e = this.folderVector.elements();
        while (e.hasMoreElements()) {
            this.folder = e.nextElement();
            if (x > this.folder.xpos + this.x && x < this.folder.xpos + this.x + this.folder.width + this.folder.discSize && y > this.folder.ypos + this.y && y < this.folder.ypos + this.y + this.folder.height && this.folder.show) {
                this.folderClicked = true;
                this.folder.handleChild();
                this.closeAll(this.folder);
                ConsultFolder.y = -this.consultTree.rootFolder.height + this.starty;
                this.consultTree.rootFolder.setUpPos(this.startx);
                this.drawTree();
                this.repaint();
                if (this.folder.state == 1 && !this.first) {
                    this.jumpToUrl(this.folder.html, this.folder.target);
                }
                this.first = false;
            }
        }
    }
    
    void jumpToUrl(String theUrl, String linkFrame) {
        try {
            if (theUrl.equals(" ")) {
                return;
            }
            this.jump = true;
            if (!theUrl.startsWith("http") && !theUrl.startsWith("ftp")) {
                theUrl = this.consultTree.path + theUrl;
            }
            if (linkFrame == null) {
                linkFrame = this.consultTree.linkFrame;
            }
            final URL urlToGoto = new URL(theUrl);
            this.consultTree.getAppletContext().showDocument(urlToGoto, linkFrame);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean mouseExit(final Event e, final int x, final int y) {
        this.changeDescColor(this.thisFolder, this.consultTree.descriptionColor);
        this.thisFolder = null;
        this.consultTree.showStatus("");
        final Graphics g = this.getGraphics();
        g.clipRect(4, this.size().height - 31, 26, 26);
        g.drawImage(this.offscreenImg, this.x, this.y, this);
        this.showCross = false;
        return true;
    }
    
    public boolean handleEvent(final Event evt) {
        if (this.mousedrag) {
            return super.handleEvent(evt);
        }
        final Enumeration e = this.folderVector.elements();
        while (e.hasMoreElements()) {
            this.folder = e.nextElement();
            if (evt.x > this.folder.xpos + this.x && evt.x < this.folder.xpos + this.x + this.folder.width + this.folder.discSize && evt.y > this.folder.ypos + this.y && evt.y < this.folder.ypos + this.y + this.folder.height && this.folder.show) {
                if (this.thisFolder != null && !this.thisFolder.equals(this.folder)) {
                    this.changeDescColor(this.thisFolder, this.consultTree.descriptionColor);
                    this.consultTree.showStatus("");
                }
                this.changeDescColor(this.folder, this.consultTree.highliteColor);
                this.thisFolder = this.folder;
                if (!this.first) {
                    this.consultTree.showStatus(this.folder.html);
                }
                return super.handleEvent(evt);
            }
        }
        if (this.thisFolder != null) {
            this.changeDescColor(this.thisFolder, this.consultTree.descriptionColor);
            this.consultTree.showStatus("");
            this.thisFolder = null;
        }
        return super.handleEvent(evt);
    }
    
    void closeAll(final ConsultFolder thisfolder) {
        ConsultFolder folder = null;
        final Enumeration e = this.folderVector.elements();
        while (e.hasMoreElements()) {
            folder = e.nextElement();
            if (!this.thisFolder.equals(folder)) {
                folder.state = 0;
            }
        }
    }
    
    void changeDescColor(final ConsultFolder folder, final Color color) {
        if (this.offscreenImg != null && folder != null) {
            folder.color = color;
            this.og.setColor(color);
            this.og.fillRect(folder.xpos + folder.width + 3, folder.ypos + folder.height / 2 - 8, folder.discSize, 12);
            this.og.setColor(this.consultTree.descriptionBorder);
            this.og.drawRect(folder.xpos + folder.width + 3, folder.ypos + folder.height / 2 - 8, folder.discSize, 12);
            this.og.setColor(this.consultTree.textColor);
            this.og.drawString(folder.thisFolderName, folder.xpos + folder.width + 5, folder.ypos + folder.height / 2 + 2);
            final Graphics g = this.getGraphics();
            if (this.border) {
                int w = this.consultTree.size().width - 4 - (this.x + folder.xpos + folder.width + 3);
                int h = this.consultTree.size().height - 4 - (this.y + folder.ypos + folder.height / 2 - 8);
                int x = this.x + folder.xpos + folder.width + 3;
                int y = this.y + folder.ypos + folder.height / 2 - 8;
                if (w > this.consultTree.size().width - 8) {
                    w = this.consultTree.size().width - 8;
                }
                if (h > 12) {
                    h = 12;
                }
                if (x < 4) {
                    x = 4;
                }
                if (y < 4) {
                    y = 4;
                }
                g.clipRect(x, y, w, h);
            }
            else {
                g.clipRect(this.x + folder.xpos + folder.width + 3, this.y + folder.ypos + folder.height / 2 - 8, folder.discSize, 12);
            }
            g.drawImage(this.offscreenImg, this.x, this.y, this);
        }
    }
    
    public boolean mouseDrag(final Event e, final int x, final int y) {
        if (!this.jump) {
            this.scrollDown(x, y);
        }
        return this.mousedrag = true;
    }
    
    void scrollDown(final int mousex, final int mousey) {
        int diffx = 0;
        int diffy = 0;
        if (!this.consultTree.scrollHor && !this.consultTree.scrollVert) {
            return;
        }
        if (this.consultTree.scrollVert) {
            diffy = this.downy - mousey;
            this.y -= diffy;
            this.downy = mousey;
        }
        if (this.consultTree.scrollHor) {
            diffx = this.downx - mousex;
            this.x -= diffx;
            this.downx = mousex;
        }
        final Graphics g = this.getGraphics();
        if (this.border) {
            g.setColor(this.consultTree.borderColor);
            for (int j = 0; j < 3; ++j) {
                g.draw3DRect(j, j, this.consultTree.size().width - j * 2 - 1, this.consultTree.size().height - j * 2 - 1, this.consultTree.borderRaised);
            }
            g.clipRect(4, 4, this.size().width - 8, this.size().height - 8);
        }
        if (diffx != 0 || diffy != 0) {
            this.og.setColor(Color.red);
            this.og.drawRect(this.startx - 16, this.starty - 2, this.consultTree.size().width - 9, this.consultTree.size().height - 9);
        }
        g.drawImage(this.offscreenImg, this.x, this.y, this);
    }
    
    public boolean mouseDown(final Event evt, final int x, final int y) {
        this.folderClicked = false;
        this.folderClicked(x, y);
        if (!this.folderClicked) {
            this.boxClicked(x, y);
        }
        this.downx = x;
        this.downy = y;
        return true;
    }
    
    public boolean mouseUp(final Event evt, final int x, final int y) {
        this.drawTree();
        this.repaint();
        this.mousedrag = false;
        this.jump = false;
        return true;
    }
    
    public boolean mouseEnter(final Event e, final int x, final int y) {
        if (this.consultTree.scrollHor || this.consultTree.scrollVert) {
            final Graphics g = this.getGraphics();
            g.setColor(Color.red);
            final Font f = new Font("Helvetica", 0, 9);
            g.setFont(f);
            g.drawImage(this.consultTree.cross, 8, this.size().height - 27, this);
            g.drawString("scroll", 5, this.size().height - 6);
            this.crossTime = System.currentTimeMillis();
            this.showCross = true;
        }
        return true;
    }
}
