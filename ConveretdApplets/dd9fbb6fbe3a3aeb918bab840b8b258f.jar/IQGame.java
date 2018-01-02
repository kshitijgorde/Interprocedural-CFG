import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.applet.AudioClip;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class IQGame extends Applet
{
    static final int OX = 20;
    static final int OY = 20;
    static final int FILL = 1;
    static final int HOLE = 0;
    static final int BALL_NUM = 15;
    String[] comment;
    MyNode[] mynodes;
    BitmapNode[] nodes;
    BitmapNode undo;
    BitmapNode redo;
    BitmapNode newGame;
    DragBitmap movingNode;
    RecordSprite[] recorder;
    int recorderItem;
    Image ball;
    AudioClip grab;
    AudioClip err;
    AudioClip click;
    Font f;
    int appWidth;
    int appHeight;
    int step;
    int ballLeft;
    int firstNode;
    int secondNode;
    boolean gotNode;
    int oldx;
    int oldy;
    int startOption;
    Graphics offScreen;
    Image image;
    
    public void paint(final Graphics g) {
        this.ballLeft = 0;
        for (int ii = 0; ii < this.nodes.length; ++ii) {
            if (this.nodes[ii].getStatus() == 1) {
                ++this.ballLeft;
            }
        }
        this.offScreen.setColor(new Color(0, 135, 64));
        this.offScreen.fillRect(0, 0, this.appWidth, this.appHeight);
        this.offScreen.draw3DRect(310, 70, this.appWidth - 310 - 20, this.appHeight - 70 - 20, false);
        this.offScreen.setFont(new Font("TimeRoman", 1, 15));
        this.offScreen.setColor(Color.yellow);
        this.offScreen.drawString("Step: " + this.step, 325, 30);
        this.offScreen.drawString("Ball(s) Left: " + this.ballLeft, 325, 50);
        this.offScreen.setFont(new Font("TimeRoman", 0, 12));
        this.offScreen.setColor(Color.green);
        this.offScreen.drawString("GRADE: How many ball(s) left?", 325, 90);
        this.offScreen.setColor(Color.white);
        int i = 0;
        do {
            this.offScreen.drawImage(this.ball, 320 + i * 13, 100, this);
        } while (++i < 8);
        this.offScreen.drawString(this.comment[0], 325, 125);
        i = 0;
        do {
            this.offScreen.drawImage(this.ball, 320 + i * 13, 135, this);
        } while (++i < 1);
        this.offScreen.drawString(this.comment[1], 325, 160);
        i = 0;
        do {
            this.offScreen.drawImage(this.ball, 320 + i * 13, 170, this);
        } while (++i < 2);
        this.offScreen.drawString(this.comment[2], 325, 195);
        i = 0;
        do {
            this.offScreen.drawImage(this.ball, 320 + i * 13, 205, this);
        } while (++i < 3);
        this.offScreen.drawString(this.comment[3], 325, 230);
        this.offScreen.drawString("The others......", 325, 255);
        this.offScreen.drawString(this.comment[4], 325, 270);
        this.offScreen.drawString("Undo", 65, 308);
        this.offScreen.drawString("Redo", 200, 308);
        this.offScreen.drawString("-- By Jiashiang Wang, 1998 --", 330, 345);
        this.undo.paint(this.offScreen);
        this.redo.paint(this.offScreen);
        this.newGame.paint(this.offScreen);
        for (i = 0; i < this.nodes.length; ++i) {
            this.nodes[i].paint(this.offScreen);
        }
        this.movingNode.paint(this.offScreen);
        if (!this.gotNode && this.isFinish()) {
            this.f = new Font("TimeRoman", 3, 30);
            this.offScreen.setFont(this.f);
            this.offScreen.setColor(Color.yellow);
            final FontMetrics m = this.offScreen.getFontMetrics();
            String tempStr = new String();
            if (this.ballLeft >= 8) {
                tempStr = this.comment[0];
            }
            else if (this.ballLeft == 1) {
                tempStr = this.comment[1];
            }
            else if (this.ballLeft == 2) {
                tempStr = this.comment[2];
            }
            else if (this.ballLeft == 3) {
                tempStr = this.comment[3];
            }
            else if (this.ballLeft >= 4 && this.ballLeft < 8) {
                tempStr = this.comment[4];
            }
            final int strWidth = m.stringWidth(tempStr);
            this.offScreen.drawString(tempStr, (this.appWidth - strWidth) / 2, this.appHeight / 2);
        }
        g.drawImage(this.image, 0, 0, this);
    }
    
    public void initEmptyNode() {
        for (int i = 0; i < this.nodes.length; ++i) {
            this.nodes[i].setStatus(1);
            this.mynodes[i].setStatus(1);
        }
        final int r = (int)Math.floor(Math.random() * 15.0);
        if (this.startOption == -1) {
            this.nodes[r].setStatus(0);
            this.mynodes[r].setStatus(0);
        }
        else {
            this.nodes[this.startOption].setStatus(0);
            this.mynodes[this.startOption].setStatus(0);
        }
    }
    
    public void modifyNode(final int step) {
        if (this.mynodes[this.recorder[step].first].getStatus() == 1) {
            this.mynodes[this.recorder[step].first].setStatus(0);
            this.nodes[this.recorder[step].first].setStatus(0);
        }
        else {
            this.mynodes[this.recorder[step].first].setStatus(1);
            this.nodes[this.recorder[step].first].setStatus(1);
        }
        if (this.mynodes[this.recorder[step].middle].getStatus() == 1) {
            this.mynodes[this.recorder[step].middle].setStatus(0);
            this.nodes[this.recorder[step].middle].setStatus(0);
        }
        else {
            this.mynodes[this.recorder[step].middle].setStatus(1);
            this.nodes[this.recorder[step].middle].setStatus(1);
        }
        if (this.mynodes[this.recorder[step].second].getStatus() == 1) {
            this.mynodes[this.recorder[step].second].setStatus(0);
            this.nodes[this.recorder[step].second].setStatus(0);
        }
        else {
            this.mynodes[this.recorder[step].second].setStatus(1);
            this.nodes[this.recorder[step].second].setStatus(1);
        }
    }
    
    public boolean mouseUp(final Event e, final int x, final int y) {
        if (this.newGame.getStatus() == 0) {
            this.newGame.setStatus(1);
        }
        if (this.undo.getStatus() == 1) {
            this.undo.setStatus(2);
        }
        if (this.redo.getStatus() == 1) {
            this.redo.setStatus(2);
        }
        this.repaint();
        if (!this.gotNode && this.newGame.inside(x, y)) {
            this.newGameInit();
        }
        else if (!this.gotNode && !this.isFinish()) {
            if (this.undo.inside(x, y) && this.undo.getStatus() != 0) {
                if (this.step >= 1) {
                    this.modifyNode(this.step);
                    --this.step;
                    if (this.step == 0) {
                        this.undo.setStatus(0);
                    }
                    if (this.step < this.recorderItem) {
                        this.redo.setStatus(2);
                    }
                }
                else {
                    this.err.play();
                }
            }
            else if (this.redo.inside(x, y) && this.redo.getStatus() != 0) {
                if (this.step < this.recorderItem) {
                    this.modifyNode(++this.step);
                    if (this.step > 0) {
                        this.undo.setStatus(2);
                    }
                    if (this.step == this.recorderItem) {
                        this.redo.setStatus(0);
                    }
                }
                else {
                    this.err.play();
                }
            }
        }
        else if (this.gotNode || !this.isFinish()) {
            int i = 0;
            while (!this.nodes[i].inside(x, y) && ++i < this.nodes.length) {}
            this.secondNode = i;
            if (i < this.nodes.length && this.nodes[i].getStatus() == 0) {
                if (this.isRightPosition()) {
                    this.grab.play();
                    this.nodes[i].setStatus(1);
                    this.mynodes[i].setStatus(1);
                }
                else {
                    this.err.play();
                    this.nodes[this.firstNode].setStatus(1);
                    this.mynodes[this.firstNode].setStatus(1);
                }
            }
            else {
                this.err.play();
                this.nodes[this.firstNode].setStatus(1);
                this.mynodes[this.firstNode].setStatus(1);
            }
            this.gotNode = false;
            this.movingNode.setDraggable(false);
            this.movingNode.suspend();
        }
        this.repaint();
        return true;
    }
    
    public void initNodes() {
        for (int i = 0; i < this.mynodes.length; ++i) {
            this.mynodes[i] = new MyNode();
        }
        for (int i = 0; i < this.nodes.length; ++i) {
            this.nodes[i].setSize(50, 50);
        }
        for (int i = 0; i < this.nodes.length; ++i) {
            this.nodes[i].setPosition(i);
            this.mynodes[i].setPosition(i);
        }
        this.movingNode.setSize(50, 50);
        this.movingNode.suspend();
        this.mynodes[0].LD = this.mynodes[1];
        this.mynodes[0].RD = this.mynodes[2];
        this.mynodes[1].RU = this.mynodes[0];
        this.mynodes[1].R = this.mynodes[2];
        this.mynodes[1].LD = this.mynodes[3];
        this.mynodes[1].RD = this.mynodes[4];
        this.mynodes[3].RU = this.mynodes[1];
        this.mynodes[3].R = this.mynodes[4];
        this.mynodes[3].LD = this.mynodes[6];
        this.mynodes[3].RD = this.mynodes[7];
        this.mynodes[6].RU = this.mynodes[3];
        this.mynodes[6].R = this.mynodes[7];
        this.mynodes[6].LD = this.mynodes[10];
        this.mynodes[6].RD = this.mynodes[11];
        this.mynodes[10].RU = this.mynodes[6];
        this.mynodes[10].R = this.mynodes[11];
        this.mynodes[2].LU = this.mynodes[0];
        this.mynodes[2].L = this.mynodes[1];
        this.mynodes[2].LD = this.mynodes[4];
        this.mynodes[2].RD = this.mynodes[5];
        this.mynodes[5].LU = this.mynodes[2];
        this.mynodes[5].L = this.mynodes[4];
        this.mynodes[5].LD = this.mynodes[8];
        this.mynodes[5].RD = this.mynodes[9];
        this.mynodes[9].LU = this.mynodes[5];
        this.mynodes[9].L = this.mynodes[8];
        this.mynodes[9].LD = this.mynodes[13];
        this.mynodes[9].RD = this.mynodes[14];
        this.mynodes[14].LU = this.mynodes[9];
        this.mynodes[14].L = this.mynodes[13];
        this.mynodes[4].LU = this.mynodes[1];
        this.mynodes[4].RU = this.mynodes[2];
        this.mynodes[4].L = this.mynodes[3];
        this.mynodes[4].R = this.mynodes[5];
        this.mynodes[4].LD = this.mynodes[7];
        this.mynodes[4].RD = this.mynodes[8];
        this.mynodes[7].LU = this.mynodes[3];
        this.mynodes[7].RU = this.mynodes[4];
        this.mynodes[7].L = this.mynodes[6];
        this.mynodes[7].R = this.mynodes[8];
        this.mynodes[7].LD = this.mynodes[11];
        this.mynodes[7].RD = this.mynodes[12];
        this.mynodes[8].LU = this.mynodes[4];
        this.mynodes[8].RU = this.mynodes[5];
        this.mynodes[8].L = this.mynodes[7];
        this.mynodes[8].R = this.mynodes[9];
        this.mynodes[8].LD = this.mynodes[12];
        this.mynodes[8].RD = this.mynodes[13];
        this.mynodes[11].LU = this.mynodes[6];
        this.mynodes[11].RU = this.mynodes[7];
        this.mynodes[11].L = this.mynodes[10];
        this.mynodes[11].R = this.mynodes[12];
        this.mynodes[12].LU = this.mynodes[7];
        this.mynodes[12].RU = this.mynodes[8];
        this.mynodes[12].L = this.mynodes[11];
        this.mynodes[12].R = this.mynodes[13];
        this.mynodes[13].LU = this.mynodes[8];
        this.mynodes[13].RU = this.mynodes[9];
        this.mynodes[13].L = this.mynodes[12];
        this.mynodes[13].R = this.mynodes[14];
        this.initEmptyNode();
    }
    
    public void initMemberData() {
        this.startOption = -1;
        final int n = -1;
        this.secondNode = n;
        this.firstNode = n;
        this.recorderItem = 0;
        this.step = 0;
        this.ballLeft = 14;
        this.gotNode = false;
    }
    
    public IQGame() {
        this.comment = new String[] { "You are a GENIUS !", "You are above all !", "You are good !", "You are average.", "Well, we are all human. *_*" };
    }
    
    public boolean isFinish() {
        for (int i = 0; i < this.mynodes.length; ++i) {
            if (this.mynodes[i].getStatus() != 0) {
                if (this.mynodes[i].LU != null && this.mynodes[i].LU.LU != null && this.mynodes[i].LU.getStatus() == 1 && this.mynodes[i].LU.LU.getStatus() == 0) {
                    return false;
                }
                if (this.mynodes[i].RU != null && this.mynodes[i].RU.RU != null && this.mynodes[i].RU.getStatus() == 1 && this.mynodes[i].RU.RU.getStatus() == 0) {
                    return false;
                }
                if (this.mynodes[i].L != null && this.mynodes[i].L.L != null && this.mynodes[i].L.getStatus() == 1 && this.mynodes[i].L.L.getStatus() == 0) {
                    return false;
                }
                if (this.mynodes[i].R != null && this.mynodes[i].R.R != null && this.mynodes[i].R.getStatus() == 1 && this.mynodes[i].R.R.getStatus() == 0) {
                    return false;
                }
                if (this.mynodes[i].LD != null && this.mynodes[i].LD.LD != null && this.mynodes[i].LD.getStatus() == 1 && this.mynodes[i].LD.LD.getStatus() == 0) {
                    return false;
                }
                if (this.mynodes[i].RD != null && this.mynodes[i].RD.RD != null && this.mynodes[i].RD.getStatus() == 1 && this.mynodes[i].RD.RD.getStatus() == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void loadImage() {
        final Image[] baseImage = new Image[3];
        final Image[] larrow = new Image[3];
        final Image[] rarrow = new Image[3];
        final Image[] nGame = new Image[2];
        final MediaTracker t = new MediaTracker(this);
        this.showStatus("Loading Images......");
        t.addImage(baseImage[0] = this.getImage(this.getCodeBase(), "hole.gif"), 0);
        t.addImage(baseImage[1] = this.getImage(this.getCodeBase(), "fill.gif"), 0);
        t.addImage(baseImage[2] = this.getImage(this.getCodeBase(), "shadow.gif"), 0);
        t.addImage(this.ball = this.getImage(this.getCodeBase(), "smallball.gif"), 0);
        t.addImage(larrow[0] = this.getImage(this.getCodeBase(), "left0.gif"), 0);
        t.addImage(larrow[1] = this.getImage(this.getCodeBase(), "left1.gif"), 0);
        t.addImage(larrow[2] = this.getImage(this.getCodeBase(), "left2.gif"), 0);
        t.addImage(rarrow[0] = this.getImage(this.getCodeBase(), "right0.gif"), 0);
        t.addImage(rarrow[1] = this.getImage(this.getCodeBase(), "right1.gif"), 0);
        t.addImage(rarrow[2] = this.getImage(this.getCodeBase(), "right2.gif"), 0);
        t.addImage(nGame[0] = this.getImage(this.getCodeBase(), "new0.gif"), 0);
        t.addImage(nGame[1] = this.getImage(this.getCodeBase(), "new1.gif"), 0);
        try {
            t.waitForAll();
        }
        catch (InterruptedException e) {
            return;
        }
        this.showStatus("Have fun !!");
        this.nodes[0] = new BitmapNode(120, 20, baseImage, this);
        this.nodes[1] = new BitmapNode(95, 70, baseImage, this);
        this.nodes[2] = new BitmapNode(145, 70, baseImage, this);
        this.nodes[3] = new BitmapNode(70, 120, baseImage, this);
        this.nodes[4] = new BitmapNode(120, 120, baseImage, this);
        this.nodes[5] = new BitmapNode(170, 120, baseImage, this);
        this.nodes[6] = new BitmapNode(45, 170, baseImage, this);
        this.nodes[7] = new BitmapNode(95, 170, baseImage, this);
        this.nodes[8] = new BitmapNode(145, 170, baseImage, this);
        this.nodes[9] = new BitmapNode(195, 170, baseImage, this);
        this.nodes[10] = new BitmapNode(20, 220, baseImage, this);
        this.nodes[11] = new BitmapNode(70, 220, baseImage, this);
        this.nodes[12] = new BitmapNode(120, 220, baseImage, this);
        this.nodes[13] = new BitmapNode(170, 220, baseImage, this);
        this.nodes[14] = new BitmapNode(220, 220, baseImage, this);
        this.movingNode = new DragBitmap(0, 0, baseImage[2], this);
        (this.undo = new BitmapNode(95, 280, larrow, this)).setStatus(0);
        this.undo.setSize(50, 50);
        (this.redo = new BitmapNode(145, 280, rarrow, this)).setStatus(0);
        this.redo.setSize(50, 50);
        (this.newGame = new BitmapNode(390, 290, nGame, this)).setStatus(1);
        this.newGame.setSize(58, 33);
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public boolean mouseDown(final Event e, final int x, final int y) {
        if (!this.gotNode && this.newGame.inside(x, y)) {
            this.newGame.setStatus(0);
            this.click.play();
            this.repaint();
        }
        else if (!this.gotNode && !this.isFinish()) {
            if (this.undo.inside(x, y) && this.undo.getStatus() != 0) {
                this.undo.setStatus(1);
                this.grab.play();
            }
            else if (this.redo.inside(x, y) && this.redo.getStatus() != 0) {
                this.redo.setStatus(1);
                this.grab.play();
            }
            else {
                int i = 0;
                while (!this.nodes[i].inside(x, y) && ++i < this.nodes.length) {}
                this.firstNode = i;
                if (i < this.nodes.length && this.nodes[i].getStatus() == 1) {
                    this.grab.play();
                    this.nodes[i].setStatus(0);
                    this.mynodes[i].setStatus(0);
                    this.gotNode = true;
                    this.movingNode.setDraggable(true);
                    this.oldx = x;
                    this.oldy = y;
                    this.movingNode.restore();
                    this.movingNode.setCenter(x, y);
                }
                else {
                    this.err.play();
                }
            }
        }
        this.repaint();
        return true;
    }
    
    public void init() {
        this.setBackground(new Color(0, 135, 64));
        this.appWidth = this.bounds().width;
        this.appHeight = this.bounds().height;
        this.initMemberData();
        this.recorder = new RecordSprite[15];
        this.nodes = new BitmapNode[15];
        this.mynodes = new MyNode[15];
        this.loadImage();
        this.initNodes();
        this.err = this.getAudioClip(this.getCodeBase(), "boing.au");
        this.grab = this.getAudioClip(this.getCodeBase(), "treat.au");
        this.click = this.getAudioClip(this.getCodeBase(), "click.au");
        this.image = this.createImage(this.appWidth, this.appHeight);
        this.offScreen = this.image.getGraphics();
    }
    
    public void newGameInit() {
        this.initMemberData();
        this.initEmptyNode();
        this.undo.setStatus(0);
        this.redo.setStatus(0);
    }
    
    public boolean mouseDrag(final Event e, final int x, final int y) {
        if (this.movingNode.isDraggable()) {
            this.movingNode.translate(x - this.oldx, y - this.oldy);
            this.oldx = x;
            this.oldy = y;
            this.repaint();
        }
        return true;
    }
    
    public boolean isRightPosition() {
        int i = -1;
        if (this.firstNode == this.secondNode) {
            return true;
        }
        if (this.mynodes[this.firstNode].LU != null && this.mynodes[this.firstNode].LU.LU == this.mynodes[this.secondNode]) {
            i = this.mynodes[this.firstNode].LU.getPosition();
        }
        if (this.mynodes[this.firstNode].RU != null && this.mynodes[this.firstNode].RU.RU == this.mynodes[this.secondNode]) {
            i = this.mynodes[this.firstNode].RU.getPosition();
        }
        if (this.mynodes[this.firstNode].L != null && this.mynodes[this.firstNode].L.L == this.mynodes[this.secondNode]) {
            i = this.mynodes[this.firstNode].L.getPosition();
        }
        if (this.mynodes[this.firstNode].R != null && this.mynodes[this.firstNode].R.R == this.mynodes[this.secondNode]) {
            i = this.mynodes[this.firstNode].R.getPosition();
        }
        if (this.mynodes[this.firstNode].LD != null && this.mynodes[this.firstNode].LD.LD == this.mynodes[this.secondNode]) {
            i = this.mynodes[this.firstNode].LD.getPosition();
        }
        if (this.mynodes[this.firstNode].RD != null && this.mynodes[this.firstNode].RD.RD == this.mynodes[this.secondNode]) {
            i = this.mynodes[this.firstNode].RD.getPosition();
        }
        if (i == -1) {
            return false;
        }
        if (this.mynodes[i].getStatus() == 0) {
            return false;
        }
        this.mynodes[i].setStatus(0);
        this.nodes[i].setStatus(0);
        this.recorderItem = this.step;
        ++this.step;
        ++this.recorderItem;
        this.recorder[this.recorderItem] = new RecordSprite(this.firstNode, i, this.secondNode);
        if (this.step > 0) {
            this.undo.setStatus(2);
        }
        if (this.step == this.recorderItem) {
            this.redo.setStatus(0);
        }
        return true;
    }
}
