import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.applet.AudioClip;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DragonTreeviewV2 extends Applet implements Runnable
{
    Tree myTree;
    ParameterInfo myParameterInfo;
    boolean boImagesLoaded;
    boolean boErrorLoading;
    boolean backgroundLoaded;
    boolean soundLoaded;
    boolean loadingSounds;
    boolean loadingBackground;
    boolean finishedloading;
    Image[] arImages;
    Image[] orgImages;
    int iPercentComplete;
    Thread myThread;
    int markedNode;
    int previousMarkedNode;
    boolean fullRepaintNecessary;
    Image buffer;
    Graphics b;
    int appletX;
    int appletY;
    Rectangle appletSize;
    int treeHeight;
    int treeWidth;
    boolean animating;
    Image bgImage;
    Image transparency;
    Image drawableBuffer;
    Image backgroundImage;
    Image optimizedBuffer;
    Graphics bIb;
    Image duffer;
    Graphics d;
    FontMetrics fm;
    AudioClip closeSound;
    AudioClip openSound;
    AudioClip markSound;
    AudioClip clickSound;
    boolean imgContinue;
    MediaTracker temp;
    int nodeStringWidth;
    
    public DragonTreeviewV2() {
        this.boImagesLoaded = false;
        this.boErrorLoading = false;
        this.backgroundLoaded = false;
        this.soundLoaded = false;
        this.loadingSounds = false;
        this.loadingBackground = false;
        this.finishedloading = false;
        this.arImages = new Image[500];
        this.orgImages = new Image[500];
        this.iPercentComplete = 0;
        this.markedNode = 999;
        this.previousMarkedNode = 999;
        this.fullRepaintNecessary = true;
        this.appletX = 0;
        this.appletY = 0;
        this.treeHeight = 0;
        this.treeWidth = 0;
        this.animating = false;
        this.closeSound = null;
        this.openSound = null;
        this.markSound = null;
        this.clickSound = null;
        this.imgContinue = false;
        this.nodeStringWidth = 0;
        this.boImagesLoaded = false;
        this.boErrorLoading = false;
        this.iPercentComplete = 0;
        this.markedNode = 999;
        this.previousMarkedNode = 999;
        this.fullRepaintNecessary = true;
        this.appletX = 0;
        this.appletY = 0;
        this.treeHeight = 0;
        this.treeWidth = 0;
        this.animating = false;
    }
    
    public void setFontColors() {
        this.setBackground(this.myParameterInfo.bgColor);
        this.setForeground(this.myParameterInfo.foColor);
        this.setFont(this.myParameterInfo.font);
    }
    
    public void start() {
        if (this.myThread == null) {
            (this.myThread = new Thread(this)).start();
        }
        else {
            this.myThread.start();
        }
    }
    
    public void stop() {
    }
    
    public void getParameters() {
        final String s = new String("bgimageposx");
        this.myParameterInfo.proccess(s, this.getParameter(s));
        final String s2 = new String("closeSound");
        this.myParameterInfo.proccess(s2, this.getParameter(s2));
        final String s3 = new String("clickSound");
        this.myParameterInfo.proccess(s3, this.getParameter(s3));
        final String s4 = new String("markSound");
        this.myParameterInfo.proccess(s4, this.getParameter(s4));
        final String s5 = new String("openSound");
        this.myParameterInfo.proccess(s5, this.getParameter(s5));
        final String s6 = new String("bgimageposy");
        this.myParameterInfo.proccess(s6, this.getParameter(s6));
        final String s7 = new String("bgimagestyle");
        this.myParameterInfo.proccess(s7, this.getParameter(s7));
        final String s8 = new String("bgimage");
        this.myParameterInfo.proccess(s8, this.getParameter(s8));
        final String s9 = new String("animate");
        this.myParameterInfo.proccess(s9, this.getParameter(s9));
        final String s10 = new String("animationdelay");
        this.myParameterInfo.proccess(s10, this.getParameter(s10));
        final String s11 = new String("font");
        this.myParameterInfo.proccess(s11, this.getParameter(s11));
        final String s12 = new String("sefont");
        this.myParameterInfo.proccess(s12, this.getParameter(s12));
        final String s13 = new String("bgcolor");
        this.myParameterInfo.proccess(s13, this.getParameter(s13));
        final String s14 = new String("focolor");
        this.myParameterInfo.proccess(s14, this.getParameter(s14));
        final String s15 = new String("secolor");
        this.myParameterInfo.proccess(s15, this.getParameter(s15));
        final String s16 = new String("iconsize");
        this.myParameterInfo.proccess(s16, this.getParameter(s16));
        final String s17 = new String("cocolor");
        this.myParameterInfo.proccess(s17, this.getParameter(s17));
        String parameter = new String("A workaround, only");
        final String s18 = new String("image");
        int n = 0;
        while (parameter != null) {
            final String s19 = new String(String.valueOf(s18).concat(String.valueOf(Integer.toString(n))));
            parameter = this.getParameter(s19);
            if (parameter == null) {
                break;
            }
            this.myParameterInfo.processImageParameter(s19);
            this.myTree.imageIndex[n] = new String(parameter);
            ++n;
        }
        String parameter2 = new String("A workaround, only");
        final String s20 = new String("node");
        int n2 = 0;
        while (parameter2 != null) {
            parameter2 = this.getParameter(new String(String.valueOf(s20).concat(String.valueOf(Integer.toString(n2)))));
            if (parameter2 == null) {
                break;
            }
            this.myTree.nodes[n2] = this.myParameterInfo.processNode(parameter2, this.getDocumentBase());
            ++n2;
        }
    }
    
    public void init() {
        this.finishedloading = false;
        this.myTree = new Tree();
        this.myParameterInfo = new ParameterInfo();
        this.getParameters();
        this.setFontColors();
        this.buffer = this.createImage(1000, 1000);
        this.b = this.buffer.getGraphics();
        this.fm = this.b.getFontMetrics();
        this.appletSize = this.getBounds();
        this.duffer = this.createImage(this.appletSize.width, this.appletSize.height);
        (this.d = this.duffer.getGraphics()).setClip(0, 0, this.appletSize.width, this.appletSize.height);
        this.temp = new MediaTracker(this);
    }
    
    public void run() {
        if (!this.boImagesLoaded) {
            this.showStatus("Loading ...");
            final MediaTracker mediaTracker = new MediaTracker(this);
            int i;
            for (i = 0; i < this.myParameterInfo.imageCount; ++i) {
                mediaTracker.addImage(this.orgImages[i] = this.getImage(this.getDocumentBase(), this.myTree.imageIndex[i]), i + 1);
                try {
                    mediaTracker.waitForID(i + 1);
                }
                catch (InterruptedException ex) {}
                this.iPercentComplete += 100 / this.myParameterInfo.imageCount;
                this.repaint();
            }
            if (this.myParameterInfo.bgImage != null) {
                this.loadingBackground = true;
                this.showStatus("Loading Background...");
                mediaTracker.addImage(this.bgImage = this.getImage(this.getDocumentBase(), this.myParameterInfo.bgImage), i + 2);
                this.repaint();
                try {
                    mediaTracker.waitForID(i + 2);
                }
                catch (InterruptedException ex2) {}
            }
            this.loadingBackground = false;
            if (!mediaTracker.isErrorAny()) {
                if (this.myParameterInfo.bgImage != null) {
                    this.backgroundLoaded = true;
                }
                this.boImagesLoaded = true;
                this.showStatus("Running Applet");
            }
            else {
                this.boErrorLoading = true;
                this.showStatus("ERROR LOADING IMAGES");
            }
        }
        this.loadingSounds = true;
        if (this.myParameterInfo.clickSound != null) {
            this.clickSound = this.getAudioClip(this.getDocumentBase(), this.myParameterInfo.clickSound);
            this.repaint();
        }
        if (this.myParameterInfo.markSound != null) {
            this.markSound = this.getAudioClip(this.getDocumentBase(), this.myParameterInfo.markSound);
            this.repaint();
        }
        if (this.myParameterInfo.openSound != null) {
            this.openSound = this.getAudioClip(this.getDocumentBase(), this.myParameterInfo.openSound);
            this.repaint();
        }
        if (this.myParameterInfo.closeSound != null) {
            this.closeSound = this.getAudioClip(this.getDocumentBase(), this.myParameterInfo.closeSound);
            this.repaint();
        }
        this.loadingSounds = false;
        this.showStatus("Running Dragon Applet II");
        this.backgroundImage = this.createImage(this.appletSize.width, this.appletSize.height);
        this.bIb = this.backgroundImage.getGraphics();
        if (this.bgImage != null) {
            if (this.myParameterInfo.bgImageStyle) {
                for (int n = 0; n * this.bgImage.getHeight(null) < this.appletSize.height; ++n) {
                    for (int n2 = 0; n2 * this.bgImage.getWidth(null) < this.appletSize.width; ++n2) {
                        this.bIb.drawImage(this.bgImage, -this.myParameterInfo.bgImagePosX - this.appletX + n2 * this.bgImage.getWidth(null), -this.myParameterInfo.bgImagePosY - this.appletY + n * this.bgImage.getHeight(null), null);
                    }
                }
            }
            else {
                this.bIb.drawImage(this.bgImage, -this.appletX, -this.appletY, this.appletSize.width, this.appletSize.height, null);
            }
        }
        else {
            this.bIb.setColor(this.myParameterInfo.bgColor);
            this.bIb.fillRect(0, 0, 1000, 1000);
        }
        this.rescale(this.myParameterInfo.imageSize, true);
        if (this.myParameterInfo.startWithAnimation) {
            final int priority = Thread.currentThread().getPriority();
            Thread.currentThread().setPriority(10);
            this.animate(this.myParameterInfo.animationStyle, this.myParameterInfo.animationWait);
            Thread.currentThread().setPriority(priority);
        }
        this.finishedloading = true;
        this.paintTree(false, 0);
        this.repaint();
    }
    
    public void rescale(final int imageSize, final boolean b) {
        for (int i = 0; i < this.myParameterInfo.imageCount; ++i) {
            if (b) {
                this.arImages[i] = this.orgImages[i].getScaledInstance(imageSize, imageSize, 1);
            }
            else {
                this.arImages[i] = this.orgImages[i].getScaledInstance(imageSize, imageSize, 2);
            }
            this.temp.addImage(this.arImages[i], i);
        }
        try {
            this.temp.waitForAll();
        }
        catch (InterruptedException ex) {}
        this.myParameterInfo.imageSize = imageSize;
        this.myParameterInfo.levelIndent = imageSize + this.myParameterInfo.iconXSpacing;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setClip(0, 0, this.appletSize.width, this.appletSize.height);
        if (this.loadingSounds && (this.myParameterInfo.clickSound != null || this.myParameterInfo.closeSound != null || this.myParameterInfo.openSound != null || this.myParameterInfo.markSound != null)) {
            graphics.clearRect(0, 0, 1000, 1000);
            graphics.drawString("Loading Sounds...", 20, 20);
            this.showStatus("Loading Sounds...");
            return;
        }
        if (this.myParameterInfo.bgImage != null && this.loadingBackground && !this.boErrorLoading) {
            graphics.clearRect(0, 0, 1000, 1000);
            graphics.drawString("Loading Background...", 20, 20);
            this.showStatus("Loading Background...");
            return;
        }
        if (!this.boImagesLoaded && !this.boErrorLoading) {
            graphics.clearRect(0, 0, 1000, 1000);
            graphics.drawString(String.valueOf(String.valueOf("Loading Icons...").concat(String.valueOf(Integer.toString(this.iPercentComplete)))).concat(String.valueOf("%")), 20, 20);
            this.showStatus(String.valueOf(String.valueOf("Loading Icons...").concat(String.valueOf(Integer.toString(this.iPercentComplete)))).concat(String.valueOf("%")));
            return;
        }
        if (this.boErrorLoading) {
            graphics.clearRect(0, 0, 1000, 1000);
            graphics.drawString("ERROR LOADING IMAGES", 20, 20);
            this.showStatus("ERROR LOADING IMAGES");
            return;
        }
        if (this.animating && this.bgImage != null) {
            this.d.drawImage(this.backgroundImage, 0, 0, null);
            this.d.drawImage(this.buffer, this.appletX, this.appletY, null);
            graphics.drawImage(this.duffer, 0, 0, null);
            return;
        }
        graphics.drawImage(this.buffer, this.appletX, this.appletY, null);
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.markedNode != 999) {
            this.unmarkNode(this.markedNode);
        }
        this.markedNode = 999;
        this.paintTree(false, 0);
        this.paint(this.getGraphics());
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.markedNode != 999) {
            if (this.myTree.nodes[this.markedNode].isFolder) {
                if (this.myTree.nodes[this.markedNode].isOpened) {
                    this.myTree.Close(this.markedNode);
                    if (this.closeSound != null) {
                        if (this.closeSound != null) {
                            this.closeSound.stop();
                        }
                        if (this.clickSound != null) {
                            this.clickSound.stop();
                        }
                        if (this.markSound != null) {
                            this.markSound.stop();
                        }
                        if (this.openSound != null) {
                            this.openSound.stop();
                        }
                        this.closeSound.play();
                    }
                    this.fullRepaintNecessary = true;
                    this.paintTree(false, 0);
                    this.paint(this.getGraphics());
                    return true;
                }
                this.myTree.Open(this.markedNode);
                if (this.openSound != null) {
                    if (this.closeSound != null) {
                        this.closeSound.stop();
                    }
                    if (this.clickSound != null) {
                        this.clickSound.stop();
                    }
                    if (this.markSound != null) {
                        this.markSound.stop();
                    }
                    if (this.openSound != null) {
                        this.openSound.stop();
                    }
                    this.openSound.play();
                }
                this.fullRepaintNecessary = true;
                this.paintTree(false, 0);
                this.paint(this.getGraphics());
                return true;
            }
            else {
                if (this.clickSound != null) {
                    if (this.closeSound != null) {
                        this.closeSound.stop();
                    }
                    if (this.clickSound != null) {
                        this.clickSound.stop();
                    }
                    if (this.markSound != null) {
                        this.markSound.stop();
                    }
                    if (this.openSound != null) {
                        this.openSound.stop();
                    }
                    this.clickSound.play();
                }
                this.getAppletContext().showDocument(this.myTree.nodes[this.markedNode].url, this.myTree.nodes[this.markedNode].frameName);
            }
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, int n, int y) {
        if (!this.boImagesLoaded || !this.finishedloading) {
            return true;
        }
        if (!this.animating) {
            final Graphics graphics = this.getGraphics();
            if (y < 21) {
                for (int i = 0; i < 10; ++i) {
                    if (this.appletY < 0) {
                        this.appletY += 5;
                        this.paintTree(false, 0);
                        this.paint(graphics);
                    }
                }
            }
            if (y > this.appletSize.height - 20) {
                for (int j = 0; j < 10; ++j) {
                    if (this.appletY > 0 || this.appletSize.height - this.appletY < this.treeHeight + 8) {
                        this.appletY -= 5;
                        this.paintTree(false, 0);
                        this.paint(graphics);
                    }
                }
            }
            if (n < 21) {
                for (int k = 0; k < 10; ++k) {
                    if (this.appletX < 0) {
                        this.appletX += 5;
                        this.paintTree(false, 0);
                        this.paint(graphics);
                    }
                }
            }
            if (n > this.appletSize.width - 20) {
                for (int l = 0; l < 10; ++l) {
                    if (this.appletX > 0 || this.appletSize.width - this.appletX < this.treeWidth) {
                        this.appletX -= 5;
                        this.paintTree(false, 0);
                        this.paint(graphics);
                    }
                }
            }
            y -= this.appletY;
            n -= this.appletX;
            final int activeNode = this.myTree.getActiveNode(y, this.myParameterInfo.imageSize, this.myParameterInfo.initialY, this.myParameterInfo.iconYSpacing);
            if (this.markedNode == activeNode) {
                return true;
            }
            this.unmarkNode(this.markedNode);
            this.paintTree(false, 0);
            this.paint(this.getGraphics());
            if (this.markSound != null) {
                if (this.closeSound != null) {
                    this.closeSound.stop();
                }
                if (this.clickSound != null) {
                    this.clickSound.stop();
                }
                if (this.markSound != null) {
                    this.markSound.stop();
                }
                if (this.openSound != null) {
                    this.openSound.stop();
                }
                this.markSound.play();
            }
            this.markNode(activeNode);
        }
        return true;
    }
    
    public void markNode(final int markedNode) {
        this.previousMarkedNode = this.markedNode;
        this.markedNode = markedNode;
        if (markedNode != 999) {
            this.myTree.nodes[markedNode].isMarked = true;
            this.paintTree(this.fullRepaintNecessary = false, 0);
            this.paint(this.getGraphics());
            if (this.myTree.nodes[markedNode].hasComment) {
                this.showStatus(this.myTree.nodes[markedNode].comment);
            }
            else if (!this.myTree.nodes[markedNode].isFolder) {
                this.showStatus(this.myTree.nodes[markedNode].url.toString());
            }
        }
    }
    
    public void unmarkNode(final int n) {
        if (n != 999) {
            this.myTree.nodes[n].isMarked = false;
            this.paintTree(this.fullRepaintNecessary = false, 0);
            this.paint(this.getGraphics());
            this.showStatus("Applet Running");
        }
    }
    
    public void paintTree(final boolean b, final int n) {
        int treeWidth = 0;
        int index = 0;
        final int initialX = this.myParameterInfo.initialX;
        int initialY = this.myParameterInfo.initialY;
        this.b.setClip(-this.appletX, -this.appletY, this.appletSize.width, this.appletSize.height);
        this.b.drawImage(this.backgroundImage, -this.appletX, -this.appletY, null);
        while (this.myTree.nodes[index].isValid) {
            if (this.myTree.isNodeVisible(index)) {
                final int whatLevel = this.myTree.WhatLevel(index);
                if (this.myTree.nodes[index].isMarked) {
                    this.b.setColor(this.myParameterInfo.seColor);
                    this.b.setFont(this.myParameterInfo.seFont);
                }
                else {
                    this.b.setColor(this.myParameterInfo.foColor);
                    this.b.setFont(this.myParameterInfo.font);
                }
                if (!this.myTree.nodes[index].isMarked) {
                    this.b.drawString(this.myTree.nodes[index].text, initialX + whatLevel * this.myParameterInfo.levelIndent + this.myParameterInfo.levelIndent, initialY + ((this.myParameterInfo.imageSize - this.myParameterInfo.fontSize) / 2 + this.myParameterInfo.fontSize));
                }
                else {
                    this.b.drawString(this.myTree.nodes[index].rollover, initialX + whatLevel * this.myParameterInfo.levelIndent + this.myParameterInfo.levelIndent, initialY + ((this.myParameterInfo.imageSize - this.myParameterInfo.fontSize) / 2 + this.myParameterInfo.fontSize));
                }
                this.fm = this.b.getFontMetrics();
                this.nodeStringWidth = 0;
                if (!this.myTree.nodes[index].isMarked) {
                    this.nodeStringWidth = this.fm.stringWidth(this.myTree.nodes[index].text);
                }
                else {
                    this.nodeStringWidth = this.fm.stringWidth(this.myTree.nodes[index].rollover);
                }
                final int n2 = this.nodeStringWidth + (whatLevel + 1) * this.myParameterInfo.levelIndent + this.myParameterInfo.initialX + 4;
                if (treeWidth < n2) {
                    treeWidth = n2;
                }
                if (!b) {
                    if (this.myTree.nodes[index].isFolder && this.myTree.nodes[index].isOpened) {
                        this.b.drawImage(this.arImages[this.myTree.nodes[index].imageIndexOpened], initialX + whatLevel * this.myParameterInfo.levelIndent, initialY, null);
                    }
                    else {
                        this.b.drawImage(this.arImages[this.myTree.nodes[index].imageIndex], initialX + whatLevel * this.myParameterInfo.levelIndent, initialY, null);
                    }
                }
                else if (this.myTree.nodes[index].isFolder && this.myTree.nodes[index].isOpened) {
                    this.b.drawImage(this.arImages[this.myTree.nodes[index].imageIndexOpened], initialX + whatLevel * this.myParameterInfo.levelIndent, initialY, n, n, null);
                }
                else {
                    this.b.drawImage(this.arImages[this.myTree.nodes[index].imageIndex], initialX + whatLevel * this.myParameterInfo.levelIndent, initialY, n, n, null);
                }
                if (this.myParameterInfo.hasConnectors && whatLevel > 0) {
                    this.b.setColor(this.myParameterInfo.coColor);
                    final int n3 = this.myParameterInfo.imageSize / 2;
                    this.b.drawLine(initialX + whatLevel * this.myParameterInfo.levelIndent - this.myParameterInfo.iconXSpacing, initialY + n3, initialX + whatLevel * this.myParameterInfo.levelIndent - this.myParameterInfo.levelIndent + n3, initialY + n3);
                }
                if (this.myParameterInfo.hasConnectors && this.myTree.nodes[index].isFolder && this.myTree.nodes[index].isOpened) {
                    this.b.setColor(this.myParameterInfo.coColor);
                    this.b.drawLine(initialX + whatLevel * this.myParameterInfo.levelIndent + this.myParameterInfo.imageSize / 2, initialY + this.myParameterInfo.imageSize + this.myParameterInfo.iconYSpacing, initialX + whatLevel * this.myParameterInfo.levelIndent + this.myParameterInfo.imageSize / 2, initialY + this.myParameterInfo.imageSize + this.myParameterInfo.iconYSpacing + (this.myTree.countVisibleChildren(index) * (this.myParameterInfo.imageSize + this.myParameterInfo.iconYSpacing) - this.myParameterInfo.imageSize / 2 - 3));
                }
                initialY += this.myParameterInfo.iconYSpacing + this.myParameterInfo.imageSize;
            }
            ++index;
        }
        this.fullRepaintNecessary = true;
        this.treeHeight = initialY;
        this.treeWidth = treeWidth;
    }
    
    public void setBGCOLOR(final String s) {
        this.setBackground(this.myParameterInfo.bgColor = ParameterInfo.parseColor(s));
        this.fullRepaintNecessary = true;
        this.paintTree(false, 0);
        this.repaint();
    }
    
    public void setFOCOLOR(final String s) {
        this.setForeground(this.myParameterInfo.foColor = ParameterInfo.parseColor(s));
        this.fullRepaintNecessary = true;
        this.paintTree(false, 0);
        this.repaint();
    }
    
    public void setSECOLOR(final String s) {
        this.myParameterInfo.seColor = ParameterInfo.parseColor(s);
        this.fullRepaintNecessary = true;
        this.paintTree(false, 0);
        this.repaint();
    }
    
    public void setCOCOLOR(final String s) {
        this.myParameterInfo.coColor = ParameterInfo.parseColor(s);
        this.fullRepaintNecessary = true;
        this.paintTree(false, 0);
        this.repaint();
    }
    
    public void setNORMALFONT(final String s) {
        this.myParameterInfo.font = ParameterInfo.parseFont(s);
        this.myParameterInfo.fontSize = this.myParameterInfo.font.getSize();
        this.myParameterInfo.fontStyle = this.myParameterInfo.font.getStyle();
        this.myParameterInfo.fontName = this.myParameterInfo.font.getName();
        this.setFont(this.myParameterInfo.font);
        this.fullRepaintNecessary = true;
        this.paintTree(false, 0);
        this.repaint();
    }
    
    public void setSELECTIONFONT(final String s) {
        this.myParameterInfo.seFont = ParameterInfo.parseFont(s);
        this.myParameterInfo.seFontSize = this.myParameterInfo.seFont.getSize();
        this.myParameterInfo.seFontStyle = this.myParameterInfo.seFont.getStyle();
        this.myParameterInfo.seFontName = this.myParameterInfo.seFont.getName();
        this.fullRepaintNecessary = true;
        this.paintTree(false, 0);
        this.repaint();
    }
    
    public void setConnectors(final boolean hasConnectors) {
        this.myParameterInfo.hasConnectors = hasConnectors;
        this.fullRepaintNecessary = true;
        this.paintTree(false, 0);
        this.repaint();
    }
    
    public void setIconSize(final int n) {
        this.rescale(n, true);
        this.paintTree(false, 0);
        this.repaint();
    }
    
    public void openFolder(final int index) {
        if (!this.myTree.nodes[index].isOpened && this.myTree.nodes[index].isFolder) {
            this.myTree.Open(index);
            if (this.openSound != null) {
                if (this.closeSound != null) {
                    this.closeSound.stop();
                }
                if (this.clickSound != null) {
                    this.clickSound.stop();
                }
                if (this.markSound != null) {
                    this.markSound.stop();
                }
                if (this.openSound != null) {
                    this.openSound.stop();
                }
                this.openSound.play();
            }
            this.fullRepaintNecessary = true;
            this.paintTree(false, 0);
            this.repaint();
        }
    }
    
    public void closeFolder(final int index) {
        if (this.myTree.nodes[index].isOpened && this.myTree.nodes[index].isFolder) {
            this.myTree.Close(index);
            if (this.closeSound != null) {
                if (this.closeSound != null) {
                    this.closeSound.stop();
                }
                if (this.clickSound != null) {
                    this.clickSound.stop();
                }
                if (this.markSound != null) {
                    this.markSound.stop();
                }
                if (this.openSound != null) {
                    this.openSound.stop();
                }
                this.closeSound.play();
            }
            this.fullRepaintNecessary = true;
            this.paintTree(false, 0);
            this.repaint();
        }
    }
    
    public String getAppletInfo() {
        return new String("DragonTreeviewV2 by Slawomir Chodnicki   www.talknet.de/~slawo/index.html");
    }
    
    public synchronized void animate(final String s, final int n) {
        final Graphics graphics = this.getGraphics();
        if (s.equalsIgnoreCase("icongrow")) {
            this.animating = true;
            for (int i = this.myParameterInfo.imageSize, n2 = 1; i >= n2; ++n2) {
                this.paintTree(true, n2);
                this.paint(graphics);
                if (n != 0) {
                    try {
                        Thread.sleep(n);
                    }
                    catch (InterruptedException ex) {
                        this.showStatus("THREAD INTERRUPT ERROR");
                    }
                }
            }
            this.animating = false;
        }
        if (s.equalsIgnoreCase("grow")) {
            this.animating = true;
            for (int j = this.myParameterInfo.imageSize, imageSize = 1; j >= imageSize; ++imageSize) {
                this.myParameterInfo.imageSize = imageSize;
                this.myParameterInfo.levelIndent = imageSize + this.myParameterInfo.iconXSpacing;
                this.paintTree(true, imageSize);
                this.paint(graphics);
                if (n != 0) {
                    try {
                        Thread.sleep(n);
                    }
                    catch (InterruptedException ex2) {
                        this.showStatus("THREAD INTERRUPT ERROR");
                    }
                }
            }
            this.animating = false;
        }
        if (s.equalsIgnoreCase("right")) {
            this.animating = true;
            this.appletX = this.appletSize.width;
            while (this.appletX > 0) {
                this.paintTree(false, 0);
                this.paint(graphics);
                this.appletX -= 2;
                if (this.appletX < 0) {
                    this.appletX = 0;
                }
                int n3 = n;
                if (this.appletX < 50 && this.appletX != 0) {
                    n3 += (this.appletSize.width - this.appletX) / Math.abs(this.appletX + this.appletX);
                }
                if (n3 != 0) {
                    try {
                        Thread.currentThread();
                        Thread.sleep(n3);
                    }
                    catch (InterruptedException ex3) {}
                }
            }
            this.animating = false;
            return;
        }
        if (s.equalsIgnoreCase("left")) {
            this.animating = true;
            this.appletX = 0 - this.appletSize.width;
            this.getGraphics().clearRect(0, 0, 1000, 1000);
            while (this.appletX < 0) {
                this.paintTree(false, 0);
                this.paint(graphics);
                this.appletX += 2;
                if (this.appletX > 0) {
                    this.appletX = 0;
                }
                try {
                    int n4 = n;
                    if (this.appletX > -50 && this.appletX != 0) {
                        n4 += (this.appletSize.width - this.appletX) / Math.abs(this.appletX + this.appletX);
                    }
                    if (n4 == 0) {
                        continue;
                    }
                    Thread.sleep(n4);
                }
                catch (InterruptedException ex4) {}
            }
            this.animating = false;
        }
        if (s.equalsIgnoreCase("top")) {
            this.animating = true;
            this.paintTree(false, 0);
            this.appletY = 0 - this.treeHeight;
            while (this.appletY < 0) {
                this.paintTree(false, 0);
                this.paint(graphics);
                this.appletY += 2;
                if (this.appletY > 0) {
                    this.appletY = 0;
                }
                int n5 = n;
                if (this.appletY > -50 && this.appletY != 0) {
                    n5 += (this.appletSize.height - this.appletY) / Math.abs(this.appletY + this.appletY);
                }
                if (n5 != 0) {
                    try {
                        Thread.sleep(n5);
                    }
                    catch (InterruptedException ex5) {}
                }
            }
            this.animating = false;
        }
        if (s.equalsIgnoreCase("bottom")) {
            this.animating = true;
            this.appletY = this.appletSize.height;
            while (this.appletY > 0) {
                this.paintTree(false, 0);
                this.paint(graphics);
                this.appletY -= 2;
                if (this.appletY < 0) {
                    this.appletY = 0;
                }
                int n6 = n;
                if (this.appletY > -50 && this.appletY != 0) {
                    n6 += (this.appletSize.height - this.appletY) / Math.abs(this.appletY + this.appletY);
                }
                if (n6 != 0) {
                    try {
                        Thread.sleep(n6);
                    }
                    catch (InterruptedException ex6) {}
                }
            }
            this.animating = false;
        }
        this.animating = false;
    }
    
    public int addNode(final String s) {
        final Node processNode = this.myParameterInfo.processNode(s, this.getDocumentBase());
        if (processNode == null) {
            return -1;
        }
        int n;
        for (n = 0; this.myTree.nodes[n].isValid; ++n) {}
        if (n == 500) {
            return -1;
        }
        this.myTree.nodes[n] = processNode;
        final ParameterInfo myParameterInfo = this.myParameterInfo;
        ++myParameterInfo.nodeCount;
        this.paintTree(false, 0);
        this.paint(this.getGraphics());
        return n;
    }
    
    public void addNodeAt(final String s, final int n) {
        int n2 = n;
        int i = this.myParameterInfo.nodeCount - 1;
        if (i == 500) {
            return;
        }
        while (i >= n) {
            this.myTree.nodes[i + 1] = this.myTree.nodes[i];
            --i;
        }
        while (this.myTree.nodes[n2].isValid) {
            if (this.myTree.nodes[n2].hasParent && this.myTree.nodes[n2].parentIndex >= n) {
                final Node node = this.myTree.nodes[n2];
                ++node.parentIndex;
            }
            ++n2;
        }
        final ParameterInfo myParameterInfo = this.myParameterInfo;
        ++myParameterInfo.nodeCount;
        this.updateNode(n, s);
    }
    
    public void updateNode(final int n, final String s) {
        final Node processNode = this.myParameterInfo.processNode(s, this.getDocumentBase());
        if (processNode == null) {
            return;
        }
        this.myTree.nodes[n] = processNode;
        this.paintTree(false, 0);
        this.paint(this.getGraphics());
    }
    
    public void killNode(final int n) {
        if (!this.myTree.nodes[n].isValid) {
            return;
        }
        if (n == 499) {
            this.paintTree(this.myTree.nodes[n].isValid = false, 0);
            this.paint(this.getGraphics());
            return;
        }
        int n2 = n;
        if (this.myTree.nodes[n2].isFolder) {
            while (this.myTree.nodes[n2].isValid) {
                if (this.myTree.nodes[n2].parentIndex == n && this.myTree.nodes[n2].hasParent) {
                    this.killNode(n2);
                }
                else {
                    ++n2;
                }
            }
        }
        for (int n3 = n; this.myTree.nodes[n3].isValid; ++n3) {
            this.myTree.nodes[n3] = this.myTree.nodes[n3 + 1];
            if (this.myTree.nodes[n3].parentIndex > n) {
                final Node node = this.myTree.nodes[n3];
                --node.parentIndex;
            }
        }
        this.paintTree(false, 0);
        this.paint(this.getGraphics());
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
