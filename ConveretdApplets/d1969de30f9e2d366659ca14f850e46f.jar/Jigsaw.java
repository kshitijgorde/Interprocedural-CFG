import java.awt.event.WindowEvent;
import java.awt.TextArea;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Frame;
import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Cursor;
import java.net.MalformedURLException;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Button;
import java.awt.Label;
import java.util.Random;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.applet.AppletContext;
import java.awt.Color;
import java.awt.event.WindowListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Jigsaw extends Applet implements Runnable, ActionListener, MouseListener, MouseMotionListener, WindowListener
{
    int wSize;
    int hSize;
    int rows;
    int cols;
    int iWidth;
    int iHeight;
    int jPosX;
    int jPosY;
    int appW;
    int appH;
    int hlpImgFade;
    boolean hlpImgGray;
    boolean avoidCenter;
    boolean canRotate;
    boolean keepClear;
    int autoSnap;
    int cutting;
    int ts;
    int overlap;
    int totalPieces;
    int[] points;
    int w;
    int h;
    int vAlign;
    Color bgColor;
    Color txColor;
    Color ofColor;
    Color ifColor;
    Color pkColor;
    Color bdColor;
    boolean showSolve;
    Thread runner;
    AppletContext apc;
    boolean bDoneSolve;
    boolean isComplete;
    boolean bImageLoaded;
    MediaTracker tracker;
    int selID;
    boolean bCanReorder;
    boolean bAutoShow;
    Graphics offScrGr;
    Image offScrImg;
    Image img1;
    Image tImg;
    Image aImg;
    String imgName;
    String sRunURL;
    String sRunTarget;
    String[] t;
    int[] iOrder;
    JigsawPiece[] jp;
    JigsawPiece selPP;
    Random rNum;
    int xmOff;
    int ymOff;
    Label l1;
    Label l2;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    int iNumPieces;
    public int STEP_SIZE;
    
    public Jigsaw() {
        this.hlpImgFade = -1;
        this.hlpImgGray = false;
        this.avoidCenter = false;
        this.canRotate = false;
        this.keepClear = false;
        this.autoSnap = 3;
        this.cutting = 0;
        this.ts = 0;
        this.overlap = 16;
        this.w = 0;
        this.h = 0;
        this.vAlign = 1;
        this.bgColor = Color.lightGray;
        this.txColor = Color.black;
        this.ofColor = Color.black;
        this.ifColor = Color.black;
        this.pkColor = Color.red;
        this.bdColor = new Color(16760767);
        this.showSolve = true;
        this.runner = null;
        this.bDoneSolve = false;
        this.isComplete = false;
        this.bImageLoaded = false;
        this.tracker = new MediaTracker(this);
        this.selID = 0;
        this.bCanReorder = true;
        this.bAutoShow = true;
        this.img1 = null;
        this.tImg = null;
        this.aImg = null;
        this.selPP = null;
        this.rNum = new Random();
        this.xmOff = 0;
        this.ymOff = 0;
        this.STEP_SIZE = 20;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals(this.t[2])) {
            for (int i = 0; i < this.rows; ++i) {
                for (int j = 0; j < this.cols; ++j) {
                    final JigsawPiece jigsawPiece = this.jp[i * this.cols + j];
                    jigsawPiece.setPosition(jigsawPiece.getBaseX(), jigsawPiece.getBaseY());
                    jigsawPiece.setOrientation(0);
                }
            }
        }
        if (actionCommand.equals(this.t[0])) {
            this.breakupPuzzle(false);
        }
        if (actionCommand.equals(this.t[1])) {
            this.breakupPuzzle(true);
        }
        if (actionCommand.equals("?")) {
            this.showAbout();
        }
        this.repaint();
    }
    
    private void breakupPuzzle(final boolean b) {
        for (int i = 0; i < this.rows; ++i) {
            if (this.bCanReorder) {
                this.reorderList((int)(this.rNum.nextDouble() * this.iNumPieces));
            }
            for (int j = 0; j < this.cols; ++j) {
                int n = 1;
                final JigsawPiece jigsawPiece = this.jp[i * this.cols + j];
                if (b) {
                    final int x = jigsawPiece.getX();
                    final int y = jigsawPiece.getY();
                    if (x >= this.jPosX && x <= this.jPosX + this.iWidth - this.w && y >= this.jPosY && y <= this.jPosY + this.iHeight - this.h) {
                        n = 0;
                    }
                }
                if (n == 1) {
                    int n2;
                    int n3;
                    do {
                        if (!b && this.canRotate) {
                            jigsawPiece.setOrientation((int)(this.rNum.nextDouble() * 3.0));
                        }
                        n2 = (int)(this.rNum.nextDouble() * (this.appW - this.w - 20 - this.overlap)) + 10;
                        n3 = (int)(this.rNum.nextDouble() * (this.appH - this.h - 20 - this.overlap)) + 10;
                    } while ((this.keepClear || b) && n2 > this.jPosX - this.w - this.overlap && n3 >= this.jPosY - this.h - this.overlap && n3 <= this.jPosY + this.iHeight);
                    jigsawPiece.setPosition(n2, n3);
                }
            }
        }
    }
    
    public void cutupPicture(final Image image) {
        int n = 0;
        int n2 = 0;
        final int[] array = new int[4];
        final int n3 = this.w / 2;
        final int n4 = this.h / 2;
        this.overlap = this.STEP_SIZE - 2;
        for (int i = 0; i < this.rows; ++i) {
            int n5 = 0;
            for (int j = 0; j < this.cols; ++j) {
                int n6;
                int n7;
                do {
                    n6 = (int)(this.rNum.nextDouble() * (this.appW - this.w - 10)) + 5;
                    n7 = (int)(this.rNum.nextDouble() * (this.appH - this.h - 10)) + 5;
                } while (n6 < this.jPosX + this.iWidth + 5 && n6 > this.jPosX - this.w - 5 && n7 < this.jPosY + this.iHeight + 5 && n7 > this.jPosY - this.h - 5 && this.avoidCenter);
                if (i == 0) {
                    array[0] = 0;
                }
                else {
                    array[0] = -this.jp[(i - 1) * this.cols + j].getFace(2);
                }
                if (j == this.cols - 1) {
                    array[1] = 0;
                }
                else {
                    array[1] = this.h / 2;
                    if (this.rNum.nextDouble() * 2.0 - 1.0 < 0.0) {
                        array[1] = -array[1];
                    }
                    if (array[0] > 0) {
                        if (array[1] > 0) {
                            final int[] array2 = array;
                            final int n8 = 1;
                            array2[n8] += this.overlap - 3;
                        }
                        else {
                            final int[] array3 = array;
                            final int n9 = 1;
                            array3[n9] -= this.overlap - 3;
                        }
                    }
                }
                if (i == this.rows - 1) {
                    array[2] = 0;
                }
                else {
                    array[2] = this.w / 2;
                    if (this.rNum.nextDouble() * 2.0 - 1.0 < 0.0) {
                        array[2] = -array[2];
                    }
                }
                if (j == 0) {
                    array[3] = 0;
                }
                else {
                    array[3] = -this.jp[i * this.cols + (j - 1)].getFace(1);
                }
                final int[] array4 = new int[4];
                for (int k = 0; k < 4; ++k) {
                    array4[k] = ((array[k] > 0) ? (this.overlap - 3) : 0);
                }
                this.jp[n] = new JigsawPiece(image, this.overlap, n5 - array4[3], n2 - array4[0], this.w + array4[3] + array4[1], this.h + array4[0] + array4[2], this.jPosX, this.jPosY, n6, n7, array[0], array[1], array[2], array[3], this.points, this.canRotate ? ((int)(this.rNum.nextDouble() * 3.0)) : 0);
                this.iOrder[n] = n;
                this.tracker.addImage(this.jp[n].getPiece(), n + 1);
                ++n;
                n5 += this.w;
            }
            n2 += this.h;
        }
        this.cutting = 0;
    }
    
    public void destroy() {
        if (this.aImg != null) {
            this.aImg.flush();
        }
        if (this.b1 != null) {
            this.b1.removeActionListener(this);
        }
        if (this.b2 != null) {
            this.b2.removeActionListener(this);
        }
        if (this.b3 != null) {
            this.b3.removeActionListener(this);
        }
        if (this.b4 != null) {
            this.b4.removeActionListener(this);
        }
        this.removeMouseListener(this);
        this.removeMouseMotionListener(this);
    }
    
    public void dloadImage(final String s) {
        try {
            synchronized (this) {
                final Image scaledInstance = this.getImage(new URL(this.getDocumentBase(), s)).getScaledInstance(this.iWidth, this.iHeight, 2);
                this.aImg = scaledInstance;
                this.tImg = scaledInstance;
                this.tracker.addImage(this.tImg, 0);
                if (this.hlpImgFade > -1) {
                    this.img1 = this.createImage(new FilteredImageSource(this.tImg.getSource(), new JigsawCutter(this.hlpImgFade, this.hlpImgGray, 0, 0, 0, 0, 0, 0, 0, this.points)));
                }
                this.repaint();
            }
        }
        catch (MalformedURLException ex) {
            System.out.println("MalformedURLException: " + ex);
        }
    }
    
    private JigsawPiece findPiece(final int n, final int n2, final boolean b) {
        for (int i = this.rows - 1; i >= 0; --i) {
            for (int j = this.cols - 1; j >= 0; --j) {
                final JigsawPiece jigsawPiece = this.jp[i * this.cols + j];
                if (jigsawPiece.isOver(n, n2)) {
                    this.selID = i * this.cols + j;
                    return jigsawPiece;
                }
            }
        }
        return null;
    }
    
    public void init() {
        this.sRunURL = null;
        this.sRunTarget = null;
        if (this.aImg != null) {
            this.aImg.flush();
        }
        this.removeMouseListener(this);
        this.removeMouseMotionListener(this);
        final Cursor cursor = new Cursor(12);
        this.appW = this.getBounds().width;
        this.appH = this.getBounds().height;
        this.offScrImg = this.createImage(this.appW, this.appH);
        this.offScrGr = this.offScrImg.getGraphics();
        this.setLayout(new FlowLayout(0, 3, 3));
        (this.t = new String[4])[0] = new String("Breakup");
        this.t[1] = new String("Tidy Pieces");
        this.t[2] = new String("Solve");
        this.t[3] = new String(" Jigsaw solved ! ");
        this.readParams();
        if (this.STEP_SIZE == 0) {
            this.STEP_SIZE = this.makeStepSize(this.iWidth, this.iHeight, this.rows, this.cols);
        }
        this.add(this.b4 = new Button("?"));
        this.add(this.b1 = new Button(this.t[0]));
        this.add(this.l2 = new Label(""));
        this.add(this.b2 = new Button(this.t[1]));
        if (this.showSolve) {
            this.add(this.b3 = new Button(this.t[2]));
            this.b3.setCursor(cursor);
            this.b3.addActionListener(this);
        }
        this.add(this.l1 = new Label(this.t[3]));
        this.b1.setCursor(cursor);
        this.b2.setCursor(cursor);
        this.b4.setCursor(cursor);
        this.b4.setFont(new Font("helvetica", 1, 13));
        this.b4.setForeground(Color.white);
        this.b4.setBackground(Color.darkGray);
        this.b1.addActionListener(this);
        this.b2.addActionListener(this);
        this.b4.addActionListener(this);
        this.setBackground(this.bgColor);
        this.l1.setBackground(this.bgColor);
        this.l2.setBackground(this.bgColor);
        this.l1.setForeground(this.txColor);
        this.validateKeepClear();
        this.points = new int[this.STEP_SIZE + 1];
        int n = 0;
        for (double n2 = 0.0; n2 < 3.141592653589793; n2 += 3.141592653589793 / this.STEP_SIZE, ++n) {
            this.points[n] = (int)((this.STEP_SIZE - 2) / 2 * Math.sin(n2));
        }
        this.totalPieces = this.rows * this.cols;
        this.jPosX = this.appW - this.iWidth - 7;
        switch (this.vAlign) {
            case 0: {
                this.jPosY = 5;
                break;
            }
            case 4: {
                this.jPosX = this.appW / 2 - this.iWidth / 2;
            }
            case 1: {
                this.jPosY = this.appH / 2 - this.iHeight / 2;
                break;
            }
            case 2: {
                this.jPosY = this.appH - this.iHeight - 6;
                break;
            }
            case 3: {
                this.jPosY = 34;
                break;
            }
        }
        this.iNumPieces = this.rows * this.cols;
        this.jp = new JigsawPiece[this.iNumPieces];
        this.iOrder = new int[this.iNumPieces];
        this.repaint();
    }
    
    private int makeStepSize(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n / n4;
        final int n6 = n2 / n3;
        final int n7 = (n5 > n6) ? n6 : n5;
        int n8;
        if (n7 < 28) {
            n8 = 10;
        }
        else if (n7 < 35) {
            n8 = 12;
        }
        else if (n7 < 43) {
            n8 = 14;
        }
        else if (n7 < 50) {
            n8 = 16;
        }
        else if (n7 < 60) {
            n8 = 18;
        }
        else if (n7 < 70) {
            n8 = 20;
        }
        else if (n7 < 80) {
            n8 = 22;
        }
        else {
            n8 = 24;
        }
        return n8;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.selPP != null) {
            int n = mouseEvent.getX() - this.xmOff;
            int n2 = mouseEvent.getY() - this.ymOff;
            if (n < 5) {
                n = 5;
            }
            if (n > this.appW - this.w - 3) {
                n = this.appW - this.w - 3;
            }
            if (n2 < 8) {
                n2 = 8;
            }
            if (n2 > this.appH - this.h - 3) {
                n2 = this.appH - this.h - 3;
            }
            this.selPP.setPosition(n, n2);
            this.repaint();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final JigsawPiece selPP = this.selPP;
        this.selPP = this.findPiece(mouseEvent.getX(), mouseEvent.getY(), false);
        if (selPP != this.selPP) {
            this.repaint();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) > 0 || !this.canRotate) {
            if (this.selPP != null) {
                this.xmOff = mouseEvent.getX() - this.selPP.getX();
                this.ymOff = mouseEvent.getY() - this.selPP.getY();
                if (this.bCanReorder) {
                    this.reorderList(this.selID);
                }
            }
            this.repaint();
        }
        else if ((mouseEvent.getModifiers() & 0x4) > 0 && this.canRotate) {
            this.selPP = this.findPiece(mouseEvent.getX(), mouseEvent.getY(), true);
            if (this.selPP != null) {
                this.selPP.setOrientation(1);
                this.repaint();
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.selPP != null) {
            final int x = this.selPP.getX();
            final int y = this.selPP.getY();
            final int baseX = this.selPP.getBaseX();
            final int baseY = this.selPP.getBaseY();
            if (x > baseX - this.autoSnap && x < baseX + this.autoSnap && y > baseY - this.autoSnap && y < baseY + this.autoSnap) {
                this.selPP.setPosition(baseX, baseY);
            }
        }
        this.selPP = null;
    }
    
    public void paint(final Graphics graphics) {
        int n = 0;
        this.isComplete = true;
        this.offScrGr.setColor(this.bgColor);
        this.offScrGr.fillRect(0, 0, this.appW, this.appH);
        this.offScrGr.setColor(this.ofColor);
        this.offScrGr.drawRect(0, 0, this.appW - 2, this.appH - 2);
        if (this.img1 != null) {
            this.offScrGr.drawImage(this.img1, this.jPosX, this.jPosY, this);
        }
        else {
            this.offScrGr.setColor(this.bdColor);
            this.offScrGr.fillRect(this.jPosX, this.jPosY, this.iWidth, this.iHeight);
        }
        this.offScrGr.setColor(this.ifColor);
        this.offScrGr.drawRect(this.jPosX - 1, this.jPosY - 1, this.iWidth + 2, this.iHeight + 2);
        for (int i = 0; i < this.iNumPieces; ++i) {
            final JigsawPiece jigsawPiece = this.jp[this.iOrder[i]];
            if (jigsawPiece != null) {
                final int x = jigsawPiece.getX();
                final int y = jigsawPiece.getY();
                if (this.tracker.statusID(this.iOrder[i] + 1, false) == 8) {
                    final Image piece = jigsawPiece.getPiece();
                    ++n;
                    final int width = piece.getWidth(this);
                    final int height = piece.getHeight(this);
                    switch (jigsawPiece.getOrientation()) {
                        case 0: {
                            this.offScrGr.drawImage(piece, x, y, this);
                            break;
                        }
                        case 1: {
                            this.offScrGr.drawImage(piece, x, y, x + width, y + height, width, 0, 0, height, this);
                            break;
                        }
                        case 2: {
                            this.offScrGr.drawImage(piece, x, y, x + width, y + height, width, height, 0, 0, this);
                            break;
                        }
                        case 3: {
                            this.offScrGr.drawImage(piece, x, y, x + width, y + height, 0, height, width, 0, this);
                            break;
                        }
                    }
                }
                if (this.isComplete && (x != jigsawPiece.getBaseX() || y != jigsawPiece.getBaseY() || jigsawPiece.getOrientation() != 0)) {
                    this.isComplete = false;
                }
            }
        }
        if (this.isComplete && n != this.iNumPieces) {
            this.isComplete = false;
        }
        if (this.selPP != null) {
            final int x2 = this.selPP.getX();
            final int y2 = this.selPP.getY();
            if (!this.bCanReorder || this.bAutoShow) {
                final Image piece2 = this.selPP.getPiece();
                final int width2 = piece2.getWidth(this);
                final int height2 = piece2.getHeight(this);
                switch (this.selPP.getOrientation()) {
                    case 0: {
                        this.offScrGr.drawImage(piece2, x2, y2, this);
                        break;
                    }
                    case 1: {
                        this.offScrGr.drawImage(piece2, x2, y2, x2 + width2, y2 + height2, width2, 0, 0, height2, this);
                        break;
                    }
                    case 2: {
                        this.offScrGr.drawImage(piece2, x2, y2, x2 + width2, y2 + height2, width2, height2, 0, 0, this);
                        break;
                    }
                    case 3: {
                        this.offScrGr.drawImage(piece2, x2, y2, x2 + width2, y2 + height2, 0, height2, width2, 0, this);
                        break;
                    }
                }
            }
            this.offScrGr.setColor(this.pkColor);
            this.offScrGr.drawRect(this.selPP.getX(), this.selPP.getY(), this.selPP.getW(), this.selPP.getH());
        }
        if (this.isComplete) {
            this.l1.setText(this.t[3]);
            this.l1.setVisible(true);
            if (this.sRunURL != null && !this.bDoneSolve) {
                URL url = null;
                this.bDoneSolve = true;
                try {
                    url = new URL(this.sRunURL);
                }
                catch (MalformedURLException ex) {
                    try {
                        url = new URL(this.getDocumentBase(), this.sRunURL);
                    }
                    catch (MalformedURLException ex2) {
                        System.out.println("Malformed URL: " + this.sRunURL);
                    }
                }
                if (url != null) {
                    if (this.sRunTarget != null) {
                        this.getAppletContext().showDocument(url, this.sRunTarget);
                    }
                    else {
                        this.getAppletContext().showDocument(url);
                    }
                }
            }
        }
        else {
            this.bDoneSolve = false;
            this.l1.setText("");
            this.l1.setVisible(false);
        }
        if (this.tracker.statusAll(false) != 8) {
            final int n2 = (int)(this.appW - 140.0) / 2;
            final int n3 = (int)(this.appH - 35.0) / 2;
            this.offScrGr.setFont(new Font("helvetica", 1, 15));
            if (this.tracker.statusID(0, false) != 8) {
                this.offScrGr.setColor(new Color(16768991));
                this.offScrGr.fillRect(n2, n3, 140, 70);
                this.offScrGr.setColor(Color.red);
                this.offScrGr.drawString("Loading image", n2 + 15, n3 + 25);
            }
            else {
                this.offScrGr.setColor(new Color(14671871));
                this.offScrGr.fillRect(n2, n3, 140, 70);
                this.offScrGr.setColor(Color.blue);
                this.offScrGr.drawString("Cutting pieces", n2 + 15, n3 + 25);
            }
            this.offScrGr.drawString("Please wait...", n2 + 22, n3 + 55);
            this.offScrGr.setColor(Color.black);
            this.offScrGr.drawRect(n2 + 1, n3 + 1, 140, 70);
        }
        graphics.drawImage(this.offScrImg, 0, 0, this);
        super.paint(graphics);
    }
    
    public void randomizePosition(final JigsawPiece jigsawPiece, final boolean b) {
        final int n = this.iWidth / this.cols;
        final int n2 = this.iHeight / this.rows;
        int n3;
        int n4;
        do {
            n3 = (int)(this.rNum.nextDouble() * (this.appW - n - 20 - this.overlap)) + 10;
            n4 = (int)(this.rNum.nextDouble() * (this.appH - n2 - 20 - this.overlap)) + 10;
        } while (n3 < this.jPosX + this.iWidth + 5 + this.overlap && n3 > this.jPosX - n - 5 && n4 < this.jPosY + this.iHeight + 5 + this.overlap && n4 > this.jPosY - n2 - 5 && b);
        jigsawPiece.setPosition(n3, n4);
    }
    
    private void readParams() {
        this.imgName = this.getParameter("Image");
        final String parameter;
        if ((parameter = this.getParameter("ImgWidth")) != null) {
            this.iWidth = new Integer(parameter);
        }
        final String parameter2;
        if ((parameter2 = this.getParameter("ImgHeight")) != null) {
            this.iHeight = new Integer(parameter2);
        }
        final String parameter3;
        if ((parameter3 = this.getParameter("Rows")) != null) {
            this.rows = new Integer(parameter3);
        }
        final String parameter4;
        if ((parameter4 = this.getParameter("Cols")) != null) {
            this.cols = new Integer(parameter4);
        }
        final String parameter5;
        if ((parameter5 = this.getParameter("DimHelpImage")) != null) {
            this.hlpImgFade = new Integer(parameter5);
        }
        final String parameter6;
        if ((parameter6 = this.getParameter("HelpImageGrayed")) != null && parameter6.equals("true")) {
            this.hlpImgGray = true;
        }
        final String parameter7;
        if ((parameter7 = this.getParameter("AutoSnap")) != null) {
            this.autoSnap = new Integer(parameter7);
        }
        final String parameter8;
        if ((parameter8 = this.getParameter("CanRotate")) != null && parameter8.equals("true")) {
            this.canRotate = true;
        }
        final String parameter9;
        if ((parameter9 = this.getParameter("KeepBoardClear")) != null && parameter9.equals("true")) {
            this.keepClear = true;
        }
        final String parameter10;
        if ((parameter10 = this.getParameter("BgColor")) != null) {
            this.bgColor = Color.decode(parameter10);
        }
        final String parameter11;
        if ((parameter11 = this.getParameter("TextColor")) != null) {
            this.txColor = Color.decode(parameter11);
        }
        final String parameter12;
        if ((parameter12 = this.getParameter("OuterFrameColor")) != null) {
            this.ofColor = Color.decode(parameter12);
        }
        final String parameter13;
        if ((parameter13 = this.getParameter("InnerFrameColor")) != null) {
            this.ifColor = Color.decode(parameter13);
        }
        final String parameter14;
        if ((parameter14 = this.getParameter("BoardColor")) != null) {
            this.bdColor = Color.decode(parameter14);
        }
        final String parameter15;
        if ((parameter15 = this.getParameter("SelectColor")) != null) {
            this.pkColor = Color.decode(parameter15);
        }
        final String parameter16;
        if ((parameter16 = this.getParameter("BreakupText")) != null) {
            this.t[0] = new String(parameter16);
        }
        final String parameter17;
        if ((parameter17 = this.getParameter("TidyText")) != null) {
            this.t[1] = new String(parameter17);
        }
        final String parameter18;
        if ((parameter18 = this.getParameter("SolveText")) != null) {
            this.t[2] = new String(parameter18);
        }
        final String parameter19;
        if ((parameter19 = this.getParameter("MessageText")) != null) {
            this.t[3] = new String(parameter19);
        }
        final String parameter20;
        if ((parameter20 = this.getParameter("RunURL")) != null) {
            this.sRunURL = new String(parameter20);
        }
        final String parameter21;
        if ((parameter21 = this.getParameter("RunTarget")) != null) {
            this.sRunTarget = new String(parameter21);
        }
        final String parameter22;
        if ((parameter22 = this.getParameter("AllowSolve")) != null && parameter22.equals("false")) {
            this.showSolve = false;
        }
        final String parameter23;
        if ((parameter23 = this.getParameter("LosePieces")) != null && parameter23.equals("true")) {
            this.bCanReorder = false;
        }
        final String parameter24;
        if ((parameter24 = this.getParameter("AutoShowPieces")) != null && parameter24.equals("false")) {
            this.bAutoShow = false;
        }
        final String parameter25;
        if ((parameter25 = this.getParameter("PictureAlign")) != null) {
            if (parameter25.equals("top")) {
                this.vAlign = 0;
            }
            if (parameter25.equals("spaced")) {
                this.vAlign = 3;
            }
            if (parameter25.equals("bottom")) {
                this.vAlign = 2;
            }
            if (parameter25.equals("center")) {
                this.vAlign = 4;
            }
        }
        final String parameter26;
        if ((parameter26 = this.getParameter("Connector")) != null) {
            switch (new Integer(parameter26)) {
                case -1: {
                    this.STEP_SIZE = 0;
                    break;
                }
                case 0: {
                    this.STEP_SIZE = 16;
                    break;
                }
                case 1: {
                    this.STEP_SIZE = 18;
                    break;
                }
                case 2: {
                    this.STEP_SIZE = 20;
                    break;
                }
                case 3: {
                    this.STEP_SIZE = 22;
                    break;
                }
                case 4: {
                    this.STEP_SIZE = 24;
                    break;
                }
            }
        }
        if (this.autoSnap < 0) {
            this.autoSnap = 0;
        }
        if (this.autoSnap > 15) {
            this.autoSnap = 15;
        }
        if (this.hlpImgFade > 100) {
            this.hlpImgFade = 100;
        }
    }
    
    private void reorderList(int iNumPieces) {
        int n = 0;
        if (iNumPieces > this.iNumPieces) {
            iNumPieces = this.iNumPieces;
        }
        if (this.iOrder[this.iNumPieces - 1] != iNumPieces) {
            for (int i = 0; i < this.iNumPieces - 1; ++i) {
                if (n == 0 && this.iOrder[i] == iNumPieces) {
                    n = 1;
                }
                if (n == 1) {
                    this.iOrder[i] = this.iOrder[i + 1];
                }
            }
            if (n != 0) {
                this.iOrder[this.iNumPieces - 1] = iNumPieces;
            }
        }
    }
    
    public void run() {
        Thread.currentThread();
        this.repaint();
        this.dloadImage(this.imgName);
        this.repaint();
        try {
            this.tracker.waitForID(0);
        }
        catch (InterruptedException ex) {
            return;
        }
        this.cutupPicture(this.tImg);
        this.repaint();
        try {
            this.tracker.waitForAll();
        }
        catch (InterruptedException ex2) {
            return;
        }
        this.breakupPuzzle(false);
        this.repaint();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public void showAbout() {
        final Label[] array = new Label[6];
        final Frame frame = new Frame("About");
        frame.setBackground(Color.lightGray);
        frame.setLayout(new FlowLayout(0, 2, 2));
        final Panel panel = new Panel();
        frame.add(panel);
        panel.setLayout(new BorderLayout(1, 1));
        final Label label;
        panel.add("North", label = new Label(" Jigsaw "));
        label.setFont(new Font("helvetica", 1, 32));
        label.setForeground(Color.yellow);
        label.setBackground(Color.black);
        final Panel panel2 = new Panel();
        panel.setBackground(Color.lightGray);
        panel.add("Center", panel2);
        panel2.setLayout(new GridLayout(3, 2, 0, 0));
        panel2.setBackground(Color.lightGray);
        panel2.add(array[0] = new Label("Version"));
        panel2.add(array[3] = new Label("1.4d"));
        panel2.add(array[1] = new Label("Author"));
        panel2.add(array[4] = new Label("Steve White"));
        panel2.add(array[2] = new Label("Date"));
        panel2.add(array[5] = new Label("01/10/2000"));
        final TextArea textArea;
        panel.add("South", textArea = new TextArea("", 6, 37, 1));
        textArea.setBackground(Color.white);
        textArea.setFont(new Font("helvetica", 0, 11));
        textArea.setEditable(false);
        textArea.append("This applet has been written by Steve White and it is FREE for both commercial and non-commercial use.");
        textArea.append("\n          --------------------\nCommercial users MUST ");
        textArea.append("ask for and receive permission BEFORE using this applet.");
        textArea.append("\n          --------------------\nALL users implementing this applet on their own web pages should read and agree to the conditions of use in the readme file supplied with the applet.");
        textArea.append("\n\nThe homepage for this applet is http://svwhite.com/games\n\n");
        textArea.append("You can contact me via games@svwhite.com.\n\n");
        for (int i = 0; i < 6; ++i) {
            array[i].setFont(new Font("helvetica", 1, 16));
            array[i].setBackground(Color.lightGray);
            if (i < 3) {
                array[i].setForeground(new Color(0, 64, 0));
            }
        }
        frame.setSize(256, 295);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocation(150, 100);
        frame.addWindowListener(this);
    }
    
    public void start() {
        (this.runner = new Thread(this)).start();
    }
    
    public synchronized void stop() {
        this.runner = null;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void validateKeepClear() {
        this.w = this.iWidth / this.cols;
        this.h = this.iHeight / this.rows;
        final int n = this.appW - this.iWidth - 20 - this.overlap;
        final int n2 = this.appH - this.iHeight - 20 - this.overlap;
        final int n3 = this.iWidth / this.cols;
        final int n4 = this.iHeight / this.rows;
        final int n5 = (n3 > n4) ? n3 : n4;
        if (n5 + 5 > n && n5 + 5 > n2) {
            this.keepClear = false;
            this.b2.setVisible(false);
        }
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        windowEvent.getWindow().dispose();
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
}
