// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import rene.util.xml.XmlWriter;
import java.io.PrintWriter;
import rene.util.xml.XmlReaderException;
import rene.util.xml.XmlReader;
import java.io.IOException;
import java.awt.Frame;
import java.util.Date;
import rene.util.list.ListElement;
import rene.util.list.Tree;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Font;
import java.util.Vector;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Canvas;

public class Board extends Canvas implements MouseListener, MouseMotionListener, KeyListener
{
    int O;
    int W;
    int D;
    int S;
    int OT;
    int OTU;
    int OP;
    int lasti;
    int lastj;
    boolean showlast;
    Image Empty;
    Image EmptyShadow;
    Image ActiveImage;
    SGFTree T;
    Vector Trees;
    int CurrentTree;
    Position P;
    int number;
    TreeNode Pos;
    int State;
    Font font;
    FontMetrics fontmetrics;
    BoardInterface GF;
    boolean Active;
    int MainColor;
    public int MyColor;
    int sendi;
    int sendj;
    Dimension Dim;
    int SpecialMarker;
    String TextMarker;
    public int Pw;
    public int Pb;
    BufferedReader LaterLoad;
    Image BlackStone;
    Image WhiteStone;
    int Range;
    boolean KeepRange;
    String NodeName;
    String LText;
    boolean DisplayNodeName;
    public boolean Removing;
    boolean Activated;
    public boolean Teaching;
    public static WoodPaint woodpaint;
    EmptyPaint EPThread;
    final double pixel = 0.8;
    final double shadow = 0.7;
    boolean MouseDown;
    protected int iTarget;
    protected int jTarget;
    
    public Board(final int s, final BoardInterface gf) {
        this.lasti = -1;
        this.MainColor = 1;
        this.sendi = -1;
        this.SpecialMarker = 2;
        this.TextMarker = "A";
        this.Range = -1;
        this.KeepRange = false;
        this.NodeName = "";
        this.LText = "";
        this.DisplayNodeName = false;
        this.Removing = false;
        this.Activated = false;
        this.Teaching = false;
        this.MouseDown = false;
        this.iTarget = -1;
        this.jTarget = -1;
        this.S = s;
        this.D = 16;
        this.W = this.S * this.D;
        this.Empty = null;
        this.EmptyShadow = null;
        this.showlast = true;
        this.GF = gf;
        this.State = 1;
        this.P = new Position(this.S);
        this.number = 1;
        this.T = new SGFTree(new Node(this.number));
        (this.Trees = new Vector()).addElement(this.T);
        this.CurrentTree = 0;
        this.Pos = this.T.top();
        this.Active = true;
        this.Dim = new Dimension();
        this.Dim.width = 0;
        this.Dim.height = 0;
        final boolean b = false;
        this.Pb = (b ? 1 : 0);
        this.Pw = (b ? 1 : 0);
        this.setfonts();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
    }
    
    void setfonts() {
        this.font = this.GF.boardFont();
        this.fontmetrics = this.getFontMetrics(this.font);
    }
    
    public Dimension getMinimumSize() {
        final Dimension size = this.getSize();
        if (size.width == 0) {
            return this.Dim;
        }
        size.width = size.height + 5;
        return size;
    }
    
    public Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    public synchronized void makeimages() {
        this.Dim = this.getSize();
        final boolean parameter = this.GF.getParameter("coordinates", true);
        final int parameter2 = this.GF.getParameter("upperleftcoordinates", true) ? 1 : 0;
        final int parameter3 = this.GF.getParameter("lowerrightcoordinates", true) ? 1 : 0;
        this.D = this.Dim.height / (this.S + 1 + (parameter ? (parameter2 + parameter3) : 0));
        this.OP = this.D / 4;
        this.O = this.D / 2 + this.OP;
        this.W = this.S * this.D + 2 * this.O;
        if (parameter) {
            if (parameter3 != 0) {
                this.OT = this.D;
            }
            else {
                this.OT = 0;
            }
            if (parameter2 != 0) {
                this.OTU = this.D;
            }
            else {
                this.OTU = 0;
            }
        }
        else {
            final boolean b = false;
            this.OTU = (b ? 1 : 0);
            this.OT = (b ? 1 : 0);
        }
        this.W += this.OTU + this.OT;
        if (!this.GF.boardShowing()) {
            return;
        }
        synchronized (this) {
            this.Empty = this.createImage(this.W, this.W);
            this.EmptyShadow = this.createImage(this.W, this.W);
        }
        this.emptypaint();
        this.ActiveImage = this.createImage(this.W, this.W);
        this.updateall();
        this.repaint();
    }
    
    public synchronized void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (this.Dim.width != size.width || this.Dim.height != size.height) {
            this.Dim = size;
            this.makeimages();
            this.repaint();
            return;
        }
        if (this.ActiveImage != null) {
            graphics.drawImage(this.ActiveImage, 0, 0, this);
        }
        if (!this.Activated && this.GF.boardShowing()) {
            this.Activated = true;
            this.GF.activate();
        }
        graphics.setColor(this.GF.backgroundColor());
        if (size.width > this.W) {
            graphics.fillRect(this.W, 0, size.width - this.W, this.W);
        }
        if (size.height > this.W) {
            graphics.fillRect(0, this.W, size.width, size.height - this.W);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized boolean trywood(final Graphics graphics, final Graphics graphics2, final int n) {
        if (EmptyPaint.haveImage(n, n, this.GF.getColor("boardcolor", 170, 120, 70), this.OP + this.OP / 2, this.OP - this.OP / 2, this.D)) {
            graphics.drawImage(EmptyPaint.StaticImage, this.O + this.OTU - this.OP, this.O + this.OTU - this.OP, this);
            if (EmptyPaint.StaticShadowImage != null && graphics2 != null) {
                graphics2.drawImage(EmptyPaint.StaticShadowImage, this.O + this.OTU - this.OP, this.O + this.OTU - this.OP, this);
            }
            return true;
        }
        if (this.EPThread != null && this.EPThread.isAlive()) {
            this.EPThread.stopit();
        }
        this.EPThread = new EmptyPaint(this, n, n, this.GF.getColor("boardcolor", 170, 120, 70), true, this.OP + this.OP / 2, this.OP - this.OP / 2, this.D);
        return false;
    }
    
    public void stonespaint() {
        final int rgb = this.GF.boardColor().getRGB();
        final int n = rgb & 0xFF;
        final int n2 = (rgb & 0xFF00) >> 8;
        final int n3 = (rgb & 0xFF0000) >> 16;
        final boolean parameter = this.GF.getParameter("alias", true);
        if (this.BlackStone == null || this.BlackStone.getWidth(this) != this.D + 2) {
            final int n4 = this.D + 2;
            final int[] array = new int[n4 * n4];
            final int[] array2 = new int[n4 * n4];
            final double n5 = n4 / 2.0 - 0.5;
            double n6 = n5 - 0.2;
            final double sqrt = Math.sqrt(3.0);
            int n7 = 0;
            if (this.GF.getParameter("smallerstones", false)) {
                --n6;
            }
            for (int i = 0; i < n4; ++i) {
                for (int j = 0; j < n4; ++j) {
                    final double n8 = i - n5;
                    final double n9 = j - n5;
                    final double n10 = n6 - Math.sqrt(n8 * n8 + n9 * n9);
                    if (n10 >= 0.0) {
                        final double n11 = n6 * n6 - n8 * n8 - n9 * n9;
                        double n12;
                        if (n11 > 0.0) {
                            n12 = Math.sqrt(n11) * sqrt;
                        }
                        else {
                            n12 = 0.0;
                        }
                        final double n13 = n8;
                        final double n14 = n9;
                        final double n15 = (2.0 * n12 - n13 + n14) / Math.sqrt(6.0 * (n13 * n13 + n14 * n14 + n12 * n12));
                        double n16;
                        if (n15 > 0.9) {
                            n16 = (n15 - 0.9) * 10.0;
                        }
                        else {
                            n16 = 0.0;
                        }
                        if (n10 > 0.8 || !parameter) {
                            final int n17 = (int)(10.0 + 10.0 * n15 + n16 * 140.0);
                            array[n7] = (0xFF000000 | n17 << 16 | n17 << 8 | n17);
                            final int n18 = (int)(200.0 + 10.0 * n15 + n16 * 45.0);
                            array2[n7] = (0xFF000000 | n18 << 16 | n18 << 8 | n18);
                        }
                        else {
                            final double n19 = (0.8 - n10) / 0.8;
                            final int n20 = (int)(10.0 + 10.0 * n15 + n16 * 140.0);
                            double n21;
                            if (n8 - n9 < n6 / 3.0) {
                                n21 = 1.0;
                            }
                            else {
                                n21 = 0.7;
                            }
                            array[n7] = (0xFF000000 | (int)((1.0 - n19) * n20 + n19 * n21 * n3) << 16 | (int)((1.0 - n19) * n20 + n19 * n21 * n2) << 8 | (int)((1.0 - n19) * n20 + n19 * n21 * n));
                            final int n22 = (int)(200.0 + 10.0 * n15 + n16 * 45.0);
                            array2[n7] = (0xFF000000 | (int)((1.0 - n19) * n22 + n19 * n21 * n3) << 16 | (int)((1.0 - n19) * n22 + n19 * n21 * n2) << 8 | (int)((1.0 - n19) * n22 + n19 * n21 * n));
                        }
                    }
                    else {
                        array[n7] = (array2[n7] = 0);
                    }
                    ++n7;
                }
            }
            this.BlackStone = this.createImage(new MemoryImageSource(n4, n4, ColorModel.getRGBdefault(), array, 0, n4));
            this.WhiteStone = this.createImage(new MemoryImageSource(n4, n4, ColorModel.getRGBdefault(), array2, 0, n4));
        }
    }
    
    public synchronized void emptypaint() {
        if (Board.woodpaint != null && Board.woodpaint.isAlive()) {
            Board.woodpaint.stopit();
        }
        synchronized (this) {
            if (this.Empty == null || this.EmptyShadow == null) {
                // monitorexit(this)
                return;
            }
            final Graphics graphics = this.Empty.getGraphics();
            final Graphics graphics2 = this.EmptyShadow.getGraphics();
            graphics.setColor(this.GF.backgroundColor());
            graphics.fillRect(0, 0, this.S * this.D + 2 * this.OP + 100, this.S * this.D + 2 * this.OP + 100);
            if (!this.GF.getParameter("beauty", true) || !this.trywood(graphics, graphics2, this.S * this.D + 2 * this.OP)) {
                graphics.setColor(this.GF.boardColor());
                graphics.fillRect(this.O + this.OTU - this.OP, this.O + this.OTU - this.OP, this.S * this.D + 2 * this.OP, this.S * this.D + 2 * this.OP);
                graphics2.setColor(this.GF.boardColor());
                graphics2.fillRect(this.O + this.OTU - this.OP, this.O + this.OTU - this.OP, this.S * this.D + 2 * this.OP, this.S * this.D + 2 * this.OP);
            }
            if (this.GF.getParameter("beautystones", true)) {
                this.stonespaint();
            }
            graphics.setColor(Color.black);
            graphics2.setColor(Color.black);
            int n = this.O + this.OTU + this.D / 2;
            final int n2 = this.O + this.OTU + this.D / 2;
            final int n3 = this.O + this.D / 2 + this.OTU + (this.S - 1) * this.D;
            for (int i = 0; i < this.S; ++i) {
                graphics.drawLine(n, n2, n, n3);
                graphics.drawLine(n2, n, n3, n);
                graphics2.drawLine(n, n2, n, n3);
                graphics2.drawLine(n2, n, n3, n);
                n += this.D;
            }
            if (this.S == 19) {
                for (int j = 0; j < 3; ++j) {
                    for (int k = 0; k < 3; ++k) {
                        this.hand(graphics, 3 + j * 6, 3 + k * 6);
                        this.hand(graphics2, 3 + j * 6, 3 + k * 6);
                    }
                }
            }
            else if (this.S >= 11) {
                if (this.S >= 15 && this.S % 2 == 1) {
                    final int n4 = this.S / 2 - 3;
                    for (int l = 0; l < 3; ++l) {
                        for (int n5 = 0; n5 < 3; ++n5) {
                            this.hand(graphics, 3 + l * n4, 3 + n5 * n4);
                            this.hand(graphics2, 3 + l * n4, 3 + n5 * n4);
                        }
                    }
                }
                else {
                    this.hand(graphics, 3, 3);
                    this.hand(graphics, this.S - 4, 3);
                    this.hand(graphics, 3, this.S - 4);
                    this.hand(graphics, this.S - 4, this.S - 4);
                    this.hand(graphics2, 3, 3);
                    this.hand(graphics2, this.S - 4, 3);
                    this.hand(graphics2, 3, this.S - 4);
                    this.hand(graphics2, this.S - 4, this.S - 4);
                }
            }
            if (this.OT > 0) {
                graphics.setFont(this.font);
                int n6 = this.O + this.OTU;
                final int n7 = this.fontmetrics.getAscent() / 2 - 1;
                for (int n8 = 0; n8 < this.S; ++n8) {
                    final String value = String.valueOf(this.S - n8);
                    graphics.drawString(value, this.O + this.OTU + this.S * this.D + this.D / 2 + this.OP - this.fontmetrics.stringWidth(value) / 2, n6 + this.D / 2 + n7);
                    n6 += this.D;
                }
                int n9 = this.O + this.OTU;
                final char[] array = { '\0' };
                for (int n10 = 0; n10 < this.S; ++n10) {
                    int n11 = n10;
                    if (n11 > 7) {
                        ++n11;
                    }
                    array[0] = (char)(65 + n11);
                    final String s = new String(array);
                    graphics.drawString(s, n9 + this.D / 2 - this.fontmetrics.stringWidth(s) / 2, this.O + this.OTU + this.S * this.D + this.D / 2 + this.OP + n7);
                    n9 += this.D;
                }
            }
            if (this.OTU > 0) {
                graphics.setFont(this.font);
                int n12 = this.O + this.OTU;
                final int n13 = this.fontmetrics.getAscent() / 2 - 1;
                for (int n14 = 0; n14 < this.S; ++n14) {
                    final String value2 = String.valueOf(this.S - n14);
                    graphics.drawString(value2, this.O + this.D / 2 - this.OP - this.fontmetrics.stringWidth(value2) / 2, n12 + this.D / 2 + n13);
                    n12 += this.D;
                }
                int n15 = this.O + this.OTU;
                final char[] array2 = { '\0' };
                for (int n16 = 0; n16 < this.S; ++n16) {
                    int n17 = n16;
                    if (n17 > 7) {
                        ++n17;
                    }
                    array2[0] = (char)(65 + n17);
                    final String s2 = new String(array2);
                    graphics.drawString(s2, n15 + this.D / 2 - this.fontmetrics.stringWidth(s2) / 2, this.O + this.D / 2 - this.OP + n13);
                    n15 += this.D;
                }
            }
        }
    }
    
    public void hand(final Graphics graphics, final int n, final int n2) {
        graphics.setColor(Color.black);
        int n3 = this.D / 10;
        if (n3 < 2) {
            n3 = 2;
        }
        graphics.fillRect(this.O + this.OTU + this.D / 2 + n * this.D - n3, this.O + this.OTU + this.D / 2 + n2 * this.D - n3, 2 * n3 + 1, 2 * n3 + 1);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.MouseDown = true;
        this.requestFocus();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (!this.MouseDown) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.MouseDown = false;
        if (this.ActiveImage == null) {
            return;
        }
        if (!this.Active) {
            return;
        }
        if (this.GF.blocked() && this.Pos.isLastMain()) {
            return;
        }
        this.getinformation();
        final int n = x - (this.O + this.OTU);
        final int n2 = y - (this.O + this.OTU);
        final int n3 = n / this.D;
        final int n4 = n2 / this.D;
        if (n < 0 || n2 < 0 || n3 < 0 || n4 < 0 || n3 >= this.S || n4 >= this.S) {
            return;
        }
        switch (this.State) {
            case 3: {
                if (mouseEvent.isShiftDown() && mouseEvent.isControlDown()) {
                    this.setmousec(n3, n4, 1);
                    break;
                }
                this.setmouse(n3, n4, 1);
                break;
            }
            case 4: {
                if (mouseEvent.isShiftDown() && mouseEvent.isControlDown()) {
                    this.setmousec(n3, n4, -1);
                    break;
                }
                this.setmouse(n3, n4, -1);
                break;
            }
            case 5: {
                this.mark(n3, n4);
                break;
            }
            case 6: {
                this.letter(n3, n4);
                break;
            }
            case 7: {
                if (mouseEvent.isShiftDown() && mouseEvent.isControlDown()) {
                    this.deletemousec(n3, n4);
                    break;
                }
                this.deletemouse(n3, n4);
                break;
            }
            case 8: {
                this.removemouse(n3, n4);
                break;
            }
            case 9: {
                this.specialmark(n3, n4);
                break;
            }
            case 10: {
                this.textmark(n3, n4);
                break;
            }
            case 1:
            case 2: {
                if (mouseEvent.isShiftDown()) {
                    if (mouseEvent.isControlDown()) {
                        this.changemove(n3, n4);
                        break;
                    }
                    this.variation(n3, n4);
                    break;
                }
                else if (mouseEvent.isControlDown()) {
                    if (this.P.tree(n3, n4) != null) {
                        this.gotovariation(n3, n4);
                        break;
                    }
                    break;
                }
                else {
                    if (!mouseEvent.isMetaDown()) {
                        this.movemouse(n3, n4);
                        break;
                    }
                    if (this.P.tree(n3, n4) != null) {
                        this.gotovariation(n3, n4);
                        break;
                    }
                    this.variation(n3, n4);
                    break;
                }
                break;
            }
        }
        this.showinformation();
        this.copy();
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (!this.Active) {
            return;
        }
        if (this.DisplayNodeName) {
            this.GF.setLabelM(this.LText);
            this.DisplayNodeName = false;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final int n = x - (this.O + this.OTU);
        final int n2 = y - (this.O + this.OTU);
        final int iTarget = n / this.D;
        final int jTarget = n2 / this.D;
        if (iTarget < 0 || jTarget < 0 || iTarget >= this.S || jTarget >= this.S) {
            if (this.GF.showTarget()) {
                final int n3 = -1;
                this.jTarget = n3;
                this.iTarget = n3;
                this.copy();
            }
            return;
        }
        if (this.GF.showTarget() && (this.iTarget != iTarget || this.jTarget != jTarget)) {
            this.drawTarget(iTarget, jTarget);
            this.iTarget = iTarget;
            this.jTarget = jTarget;
        }
        this.GF.setLabelM(Field.coordinate(iTarget, jTarget, this.S));
    }
    
    public void drawTarget(int n, int n2) {
        this.copy();
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return;
        }
        n = this.O + this.OTU + n * this.D + this.D / 2;
        n2 = this.O + this.OTU + n2 * this.D + this.D / 2;
        if (this.GF.bwColor()) {
            graphics.setColor(Color.white);
        }
        else {
            graphics.setColor(Color.gray.brighter());
        }
        graphics.drawRect(n - this.D / 4, n2 - this.D / 4, this.D / 2, this.D / 2);
        graphics.dispose();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (!this.Active) {
            return;
        }
        if (this.DisplayNodeName) {
            this.GF.setLabel(this.LText);
            this.DisplayNodeName = false;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final int n = x - (this.O + this.OTU);
        final int n2 = y - (this.O + this.OTU);
        final int iTarget = n / this.D;
        final int jTarget = n2 / this.D;
        if (iTarget < 0 || jTarget < 0 || iTarget >= this.S || jTarget >= this.S) {
            return;
        }
        if (this.GF.showTarget()) {
            this.drawTarget(iTarget, jTarget);
            this.iTarget = iTarget;
            this.jTarget = jTarget;
        }
        this.GF.setLabelM(Field.coordinate(iTarget, jTarget, this.S));
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (!this.Active) {
            return;
        }
        this.GF.setLabelM("--");
        if (!this.NodeName.equals("")) {
            this.GF.setLabel(this.NodeName);
            this.DisplayNodeName = true;
        }
        if (this.GF.showTarget()) {
            final int n = -1;
            this.jTarget = n;
            this.iTarget = n;
            this.copy();
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.isActionKey()) {
            switch (keyEvent.getKeyCode()) {
                case 40: {
                    this.forward();
                }
                case 38: {
                    this.back();
                }
                case 37: {
                    this.varleft();
                }
                case 39: {
                    this.varright();
                }
                case 34: {
                    this.fastforward();
                }
                case 33: {
                    this.fastback();
                }
                case 8:
                case 127: {
                    this.undo();
                }
                case 36: {
                    this.varmain();
                }
                case 35: {
                    this.varmaindown();
                }
            }
        }
        else {
            switch (keyEvent.getKeyChar()) {
                case '*': {
                    this.varmain();
                }
                case '/': {
                    this.varmaindown();
                }
                case 'V':
                case 'v': {
                    this.varup();
                }
                case 'M':
                case 'm': {
                    this.mark();
                }
                case 'P':
                case 'p': {
                    this.resume();
                }
                case 'C':
                case 'c': {
                    this.specialmark(4);
                }
                case 'S':
                case 's': {
                    this.specialmark(2);
                }
                case 'T':
                case 't': {
                    this.specialmark(3);
                }
                case 'L':
                case 'l': {
                    this.letter();
                }
                case 'R':
                case 'r': {
                    this.specialmark(1);
                }
                case 'w': {
                    this.setwhite();
                }
                case 'b': {
                    this.setblack();
                }
                case 'W': {
                    this.white();
                }
                case 'B': {
                    this.black();
                }
                case '+': {
                    this.gotonext();
                }
                case '-': {
                    this.gotoprevious();
                }
                case '\b':
                case '\u007f': {
                    this.undo();
                }
            }
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    void gotovariation(final int n, final int n2) {
        final TreeNode tree = this.P.tree(n, n2);
        if (tree.parentPos() == this.Pos.parentPos()) {
            this.goback();
            this.Pos = tree;
            this.setnode();
            this.showinformation();
            this.copy();
        }
    }
    
    public void set(final int n, final int n2) {
        synchronized (this.Pos) {
            if (this.P.color(n, n2) == 0) {
                if (this.Pos.node().actions() != null || this.Pos.parentPos() == null) {
                    final Node node = new Node(++this.number);
                    Action action;
                    if (this.P.color() > 0) {
                        action = new Action("B", Field.string(n, n2));
                    }
                    else {
                        action = new Action("W", Field.string(n, n2));
                    }
                    node.addaction(action);
                    this.setaction(node, action, this.P.color());
                    final TreeNode pos = new TreeNode(node);
                    this.Pos.addchild(pos);
                    node.main(this.Pos);
                    this.Pos = pos;
                }
                else {
                    final Node node2 = this.Pos.node();
                    Action action2;
                    if (this.P.color() > 0) {
                        action2 = new Action("B", Field.string(n, n2));
                    }
                    else {
                        action2 = new Action("W", Field.string(n, n2));
                    }
                    node2.addaction(action2);
                    this.setaction(node2, action2, this.P.color());
                }
            }
        }
        // monitorexit(this.Pos)
    }
    
    public void delete(final int n, final int n2) {
        if (this.P.color(n, n2) == 0) {
            return;
        }
        synchronized (this.Pos) {
            Node node = this.Pos.node();
            if (this.GF.getParameter("puresgf", true) && (node.contains("B") || node.contains("W"))) {
                node = this.newnode();
            }
            node.expandaction(new Action("AE", Field.string(n, n2)));
            node.addchange(new Change(n, n2, this.P.color(n, n2)));
            this.P.color(n, n2, 0);
            this.update(n, n2);
            this.showinformation();
            this.copy();
        }
        // monitorexit(this.Pos)
    }
    
    public void changemove(final int n, final int n2) {
        if (this.P.color(n, n2) != 0) {
            return;
        }
        synchronized (this.Pos) {
            final ListElement actions = this.Pos.node().actions();
            while (actions != null) {
                final Action action = (Action)actions.content();
                if (action.type().equals("B") || action.type().equals("W")) {
                    this.undonode();
                    action.arguments().content(Field.string(n, n2));
                    this.setnode();
                    break;
                }
            }
        }
        // monitorexit(this.Pos)
    }
    
    public void removegroup(final int n, final int n2) {
        if (this.Pos.haschildren()) {
            return;
        }
        if (this.P.color(n, n2) == 0) {
            return;
        }
        this.P.markgroup(n, n2);
        this.P.color(n, n2);
        Node node = this.Pos.node();
        if (node.contains("B") || node.contains("W")) {
            node = this.newnode();
        }
        for (int i = 0; i < this.S; ++i) {
            for (int j = 0; j < this.S; ++j) {
                if (this.P.marked(i, j)) {
                    final Action action = new Action("AE", Field.string(i, j));
                    node.addchange(new Change(i, j, this.P.color(i, j)));
                    node.expandaction(action);
                    if (this.P.color(i, j) > 0) {
                        final Node node2 = node;
                        ++node2.Pb;
                        ++this.Pb;
                    }
                    else {
                        final Node node3 = node;
                        ++node3.Pw;
                        ++this.Pw;
                    }
                    this.P.color(i, j, 0);
                    this.update(i, j);
                }
            }
        }
        this.copy();
    }
    
    public void mark(final int n, final int n2) {
        this.Pos.node().toggleaction(new MarkAction(Field.string(n, n2), this.GF));
        this.update(n, n2);
    }
    
    public void specialmark(final int n, final int n2) {
        final Node node = this.Pos.node();
        String s = null;
        switch (this.SpecialMarker) {
            case 2: {
                s = "SQ";
                break;
            }
            case 4: {
                s = "CR";
                break;
            }
            case 3: {
                s = "TR";
                break;
            }
            default: {
                s = "MA";
                break;
            }
        }
        node.toggleaction(new Action(s, Field.string(n, n2)));
        this.update(n, n2);
    }
    
    public void markterritory(final int n, final int n2, final int n3) {
        Action action;
        if (n3 > 0) {
            action = new Action("TB", Field.string(n, n2));
        }
        else {
            action = new Action("TW", Field.string(n, n2));
        }
        this.Pos.node().expandaction(action);
        this.update(n, n2);
    }
    
    public void textmark(final int n, final int n2) {
        this.Pos.node().expandaction(new Action("LB", String.valueOf(Field.string(n, n2)) + ":" + this.TextMarker));
        this.update(n, n2);
        this.GF.advanceTextmark();
    }
    
    public void letter(final int n, final int n2) {
        this.Pos.node().toggleaction(new LabelAction(Field.string(n, n2), this.GF));
        this.update(n, n2);
    }
    
    public Node newnode() {
        final Node node = new Node(++this.number);
        final TreeNode pos = new TreeNode(node);
        this.Pos.addchild(pos);
        node.main(this.Pos);
        this.Pos = pos;
        this.setlast();
        return node;
    }
    
    public void set(final int n, final int n2, final int n3) {
        if (this.Pos.haschildren()) {
            return;
        }
        this.setc(n, n2, n3);
    }
    
    public void setc(final int n, final int n2, final int n3) {
        synchronized (this.Pos) {
            if (this.P.color(n, n2) == 0) {
                Node node = this.Pos.node();
                if (this.GF.getParameter("puresgf", true) && (node.contains("B") || node.contains("W"))) {
                    node = this.newnode();
                }
                node.addchange(new Change(n, n2, 0));
                Action action;
                if (n3 > 0) {
                    action = new Action("AB", Field.string(n, n2));
                }
                else {
                    action = new Action("AW", Field.string(n, n2));
                }
                node.expandaction(action);
                this.P.color(n, n2, n3);
                this.update(n, n2);
            }
        }
        // monitorexit(this.Pos)
    }
    
    public void capture(final int n, final int n2, final Node node) {
        final int n3 = -this.P.color(n, n2);
        if (n > 0) {
            this.capturegroup(n - 1, n2, n3, node);
        }
        if (n2 > 0) {
            this.capturegroup(n, n2 - 1, n3, node);
        }
        if (n < this.S - 1) {
            this.capturegroup(n + 1, n2, n3, node);
        }
        if (n2 < this.S - 1) {
            this.capturegroup(n, n2 + 1, n3, node);
        }
        if (this.P.color(n, n2) == -n3) {
            this.capturegroup(n, n2, -n3, node);
        }
    }
    
    public void capturegroup(final int n, final int n2, final int n3, final Node node) {
        if (this.P.color(n, n2) != n3) {
            return;
        }
        if (!this.P.markgrouptest(n, n2, 0)) {
            for (int i = 0; i < this.S; ++i) {
                for (int j = 0; j < this.S; ++j) {
                    if (this.P.marked(i, j)) {
                        node.addchange(new Change(i, j, this.P.color(i, j)));
                        if (this.P.color(i, j) > 0) {
                            ++this.Pb;
                            ++node.Pb;
                        }
                        else {
                            ++this.Pw;
                            ++node.Pw;
                        }
                        this.P.color(i, j, 0);
                        this.update(i, j);
                    }
                }
            }
        }
    }
    
    public void variation(final int n, final int n2) {
        if (this.Pos.parentPos() == null) {
            return;
        }
        if (this.P.color(n, n2) == 0) {
            final int color = this.P.color();
            this.goback();
            this.P.color(-color);
            this.set(n, n2);
            this.number = 2;
            this.Pos.node().number(2);
        }
    }
    
    public String formtime(int n) {
        final int n2 = n / 3600;
        if (n2 >= 1) {
            n -= 3600 * n2;
            final int n3 = n / 60;
            return n2 + ":" + this.twodigits(n3) + ":" + this.twodigits(n - 60 * n3);
        }
        final int n4 = n / 60;
        return n4 + ":" + this.twodigits(n - 60 * n4);
    }
    
    public String twodigits(final int n) {
        if (n < 10) {
            return "0" + n;
        }
        return String.valueOf(n);
    }
    
    public String lookuptime(final String s) {
        if (this.Pos.parentPos() != null) {
            final String getaction = this.Pos.parentPos().node().getaction(s);
            if (!getaction.equals("")) {
                try {
                    return this.formtime(Integer.parseInt(getaction));
                }
                catch (Exception ex) {
                    return "";
                }
            }
            return "";
        }
        return "";
    }
    
    public void showinformation() {
        final Node node = this.Pos.node();
        this.number = node.number();
        this.NodeName = node.getaction("N");
        String s = "";
        if (node.main()) {
            if (!this.Pos.haschildren()) {
                s = "** ";
            }
            else {
                s = "* ";
            }
        }
        switch (this.State) {
            case 3: {
                this.LText = String.valueOf(s) + this.GF.resourceString("Set_black_stones");
                break;
            }
            case 4: {
                this.LText = String.valueOf(s) + this.GF.resourceString("Set_white_stones");
                break;
            }
            case 5: {
                this.LText = String.valueOf(s) + this.GF.resourceString("Mark_fields");
                break;
            }
            case 6: {
                this.LText = String.valueOf(s) + this.GF.resourceString("Place_letters");
                break;
            }
            case 7: {
                this.LText = String.valueOf(s) + this.GF.resourceString("Delete_stones");
                break;
            }
            case 8: {
                this.LText = String.valueOf(s) + this.GF.resourceString("Remove_prisoners");
                break;
            }
            case 9: {
                this.LText = String.valueOf(s) + this.GF.resourceString("Set_special_marker");
                break;
            }
            case 10: {
                this.LText = String.valueOf(s) + this.GF.resourceString("Text__") + this.TextMarker;
                break;
            }
            default: {
                if (this.P.color() > 0) {
                    final String lookuptime = this.lookuptime("BL");
                    if (!lookuptime.equals("")) {
                        this.LText = String.valueOf(s) + this.GF.resourceString("Next_move__Black_") + this.number + " (" + lookuptime + ")";
                        break;
                    }
                    this.LText = String.valueOf(s) + this.GF.resourceString("Next_move__Black_") + this.number;
                    break;
                }
                else {
                    final String lookuptime2 = this.lookuptime("WL");
                    if (!lookuptime2.equals("")) {
                        this.LText = String.valueOf(s) + this.GF.resourceString("Next_move__White_") + this.number + " (" + lookuptime2 + ")";
                        break;
                    }
                    this.LText = String.valueOf(s) + this.GF.resourceString("Next_move__White_") + this.number;
                    break;
                }
                break;
            }
        }
        this.LText = String.valueOf(this.LText) + " (" + this.siblings() + " " + this.GF.resourceString("Siblings") + ", " + this.children() + " " + this.GF.resourceString("Children") + ")";
        if (this.NodeName.equals("")) {
            this.GF.setLabel(this.LText);
            this.DisplayNodeName = false;
        }
        else {
            this.GF.setLabel(this.NodeName);
            this.DisplayNodeName = true;
        }
        this.GF.setState(3, !node.main());
        this.GF.setState(4, !node.main());
        this.GF.setState(7, !node.main() || this.Pos.haschildren());
        if (this.State == 1 || this.State == 2) {
            if (this.P.color() == 1) {
                this.State = 1;
            }
            else {
                this.State = 2;
            }
        }
        this.GF.setState(1, this.Pos.parentPos() != null && this.Pos.parentPos().firstChild() != this.Pos);
        this.GF.setState(2, this.Pos.parentPos() != null && this.Pos.parentPos().lastChild() != this.Pos);
        this.GF.setState(5, this.Pos.haschildren());
        this.GF.setState(6, this.Pos.parentPos() != null);
        if (this.State != 9) {
            this.GF.setState(this.State);
        }
        else {
            this.GF.setMarkState(this.SpecialMarker);
        }
        for (int i = 0; i < this.S; ++i) {
            for (int j = 0; j < this.S; ++j) {
                if (this.P.tree(i, j) != null) {
                    this.P.tree(i, j, null);
                    this.update(i, j);
                }
                if (this.P.marker(i, j) != 0) {
                    this.P.marker(i, j, 0);
                    this.update(i, j);
                }
                if (this.P.letter(i, j) != 0) {
                    this.P.letter(i, j, 0);
                    this.update(i, j);
                }
                if (this.P.haslabel(i, j)) {
                    this.P.clearlabel(i, j);
                    this.update(i, j);
                }
            }
        }
        final TreeNode parentPos = this.Pos.parentPos();
        if (parentPos != null && parentPos.parentPos() != null) {
            for (ListElement listElement = parentPos.firstChild().listelement(); listElement != null; listElement = listElement.next()) {
                final TreeNode treeNode = (TreeNode)listElement.content();
                if (treeNode != this.Pos) {
                    ListElement listElement2 = treeNode.node().actions();
                    while (listElement2 != null) {
                        final Action action = (Action)listElement2.content();
                        if (action.type().equals("W") || action.type().equals("B")) {
                            final String s2 = (String)action.arguments().content();
                            final int k = Field.i(s2);
                            final int l = Field.j(s2);
                            if (this.valid(k, l)) {
                                this.P.tree(k, l, treeNode);
                                this.update(k, l);
                                break;
                            }
                            break;
                        }
                        else {
                            listElement2 = listElement2.next();
                        }
                    }
                }
            }
        }
        ListElement listElement3 = this.Pos.node().actions();
        String comment = "";
        int n = 1;
        while (listElement3 != null) {
            final Action action2 = (Action)listElement3.content();
            if (action2.type().equals("C")) {
                comment = (String)action2.arguments().content();
            }
            else if (action2.type().equals("SQ") || action2.type().equals("SL")) {
                for (ListElement listElement4 = action2.arguments(); listElement4 != null; listElement4 = listElement4.next()) {
                    final String s3 = (String)listElement4.content();
                    final int m = Field.i(s3);
                    final int j2 = Field.j(s3);
                    if (this.valid(m, j2)) {
                        this.P.marker(m, j2, 2);
                        this.update(m, j2);
                    }
                }
            }
            else if (action2.type().equals("MA") || action2.type().equals("M") || action2.type().equals("TW") || action2.type().equals("TB")) {
                for (ListElement listElement5 = action2.arguments(); listElement5 != null; listElement5 = listElement5.next()) {
                    final String s4 = (String)listElement5.content();
                    final int i2 = Field.i(s4);
                    final int j3 = Field.j(s4);
                    if (this.valid(i2, j3)) {
                        this.P.marker(i2, j3, 1);
                        this.update(i2, j3);
                    }
                }
            }
            else if (action2.type().equals("TR")) {
                for (ListElement listElement6 = action2.arguments(); listElement6 != null; listElement6 = listElement6.next()) {
                    final String s5 = (String)listElement6.content();
                    final int i3 = Field.i(s5);
                    final int j4 = Field.j(s5);
                    if (this.valid(i3, j4)) {
                        this.P.marker(i3, j4, 3);
                        this.update(i3, j4);
                    }
                }
            }
            else if (action2.type().equals("CR")) {
                for (ListElement listElement7 = action2.arguments(); listElement7 != null; listElement7 = listElement7.next()) {
                    final String s6 = (String)listElement7.content();
                    final int i4 = Field.i(s6);
                    final int j5 = Field.j(s6);
                    if (this.valid(i4, j5)) {
                        this.P.marker(i4, j5, 4);
                        this.update(i4, j5);
                    }
                }
            }
            else if (action2.type().equals("L")) {
                for (ListElement listElement8 = action2.arguments(); listElement8 != null; listElement8 = listElement8.next()) {
                    final String s7 = (String)listElement8.content();
                    final int i5 = Field.i(s7);
                    final int j6 = Field.j(s7);
                    if (this.valid(i5, j6)) {
                        this.P.letter(i5, j6, n);
                        ++n;
                        this.update(i5, j6);
                    }
                }
            }
            else if (action2.type().equals("LB")) {
                for (ListElement listElement9 = action2.arguments(); listElement9 != null; listElement9 = listElement9.next()) {
                    final String s8 = (String)listElement9.content();
                    final int i6 = Field.i(s8);
                    final int j7 = Field.j(s8);
                    if (this.valid(i6, j7) && s8.length() >= 4 && s8.charAt(2) == ':') {
                        this.P.setlabel(i6, j7, s8.substring(3));
                        this.update(i6, j7);
                    }
                }
            }
            listElement3 = listElement3.next();
        }
        if (!this.GF.getComment().equals(comment)) {
            this.GF.setComment(comment);
        }
        if (this.Range >= 0 && !this.KeepRange) {
            this.clearrange();
        }
    }
    
    public int siblings() {
        ListElement listElement = this.Pos.listelement();
        if (listElement == null) {
            return 0;
        }
        while (listElement.previous() != null) {
            listElement = listElement.previous();
        }
        int n;
        for (n = 0; listElement.next() != null; listElement = listElement.next(), ++n) {}
        return n;
    }
    
    public int children() {
        if (!this.Pos.haschildren()) {
            return 0;
        }
        final TreeNode firstChild = this.Pos.firstChild();
        if (firstChild == null) {
            return 0;
        }
        ListElement listElement = firstChild.listelement();
        if (listElement == null) {
            return 0;
        }
        while (listElement.previous() != null) {
            listElement = listElement.previous();
        }
        int n;
        for (n = 1; listElement.next() != null; listElement = listElement.next(), ++n) {}
        return n;
    }
    
    public void clearsend() {
        if (this.sendi >= 0) {
            final int sendi = this.sendi;
            this.sendi = -1;
            this.update(sendi, this.sendj);
        }
    }
    
    public void getinformation() {
        ListElement listElement = this.Pos.node().actions();
        this.clearsend();
        while (listElement != null) {
            final Action action = (Action)listElement.content();
            if (action.type().equals("C")) {
                if (this.GF.getComment().equals("")) {
                    this.Pos.node().removeaction(listElement);
                    return;
                }
                action.arguments().content(this.GF.getComment());
                return;
            }
            else {
                listElement = listElement.next();
            }
        }
        final String comment = this.GF.getComment();
        if (!comment.equals("")) {
            this.Pos.addaction(new Action("C", comment));
        }
    }
    
    public void update(final int n, final int n2) {
        if (this.ActiveImage == null) {
            return;
        }
        if (n < 0 || n2 < 0) {
            return;
        }
        final Graphics graphics = this.ActiveImage.getGraphics();
        final int n3 = this.O + this.OTU + n * this.D;
        final int n4 = this.O + this.OTU + n2 * this.D;
        synchronized (this) {
            graphics.drawImage(this.Empty, n3, n4, n3 + this.D, n4 + this.D, n3, n4, n3 + this.D, n4 + this.D, this);
            if (this.GF.getParameter("shadows", false) && this.GF.getParameter("beauty", true) && this.GF.getParameter("beautystones", true)) {
                if (this.P.color(n, n2) != 0) {
                    graphics.drawImage(this.EmptyShadow, n3 - this.OP / 2, n4 + this.OP / 2, n3 + this.D - this.OP / 2, n4 + this.D + this.OP / 2, n3 - this.OP / 2, n4 + this.OP / 2, n3 + this.D - this.OP / 2, n4 + this.D + this.OP / 2, this);
                }
                else {
                    graphics.drawImage(this.Empty, n3 - this.OP / 2, n4 + this.OP / 2, n3 + this.D - this.OP / 2, n4 + this.D + this.OP / 2, n3 - this.OP / 2, n4 + this.OP / 2, n3 + this.D - this.OP / 2, n4 + this.D + this.OP / 2, this);
                }
                graphics.setClip(n3 - this.OP / 2, n4 + this.OP / 2, this.D, this.D);
                this.update1(graphics, n - 1, n2);
                this.update1(graphics, n, n2 + 1);
                this.update1(graphics, n - 1, n2 + 1);
                graphics.setClip(n3, n4, this.D, this.D);
                if (n < this.S - 1 && this.P.color(n + 1, n2) != 0) {
                    graphics.drawImage(this.EmptyShadow, n3 + this.D - this.OP / 2, n4 + this.OP / 2, n3 + this.D, n4 + this.D, n3 + this.D - this.OP / 2, n4 + this.OP / 2, n3 + this.D, n4 + this.D, this);
                }
                if (n2 > 0 && this.P.color(n, n2 - 1) != 0) {
                    graphics.drawImage(this.EmptyShadow, n3, n4, n3 + this.D - this.OP / 2, n4 + this.OP / 2, n3, n4, n3 + this.D - this.OP / 2, n4 + this.OP / 2, this);
                }
            }
        }
        graphics.setClip(n3, n4, this.D, this.D);
        this.update1(graphics, n, n2);
        graphics.dispose();
    }
    
    void update1(final Graphics graphics, final int n, final int n2) {
        if (n < 0 || n >= this.S || n2 < 0 || n2 >= this.S) {
            return;
        }
        final char[] array = { '\0' };
        final int n3 = this.O + this.OTU + n * this.D;
        final int n4 = this.O + this.OTU + n2 * this.D;
        if (this.P.color(n, n2) > 0 || (this.P.color(n, n2) < 0 && this.GF.blackOnly())) {
            if (this.BlackStone != null) {
                graphics.drawImage(this.BlackStone, n3 - 1, n4 - 1, this);
            }
            else {
                graphics.setColor(this.GF.blackColor());
                graphics.fillOval(n3 + 1, n4 + 1, this.D - 2, this.D - 2);
                graphics.setColor(this.GF.blackSparkleColor());
                graphics.drawArc(n3 + this.D / 2, n4 + this.D / 4, this.D / 4, this.D / 4, 40, 50);
            }
        }
        else if (this.P.color(n, n2) < 0) {
            if (this.WhiteStone != null) {
                graphics.drawImage(this.WhiteStone, n3 - 1, n4 - 1, this);
            }
            else {
                graphics.setColor(this.GF.whiteColor());
                graphics.fillOval(n3 + 1, n4 + 1, this.D - 2, this.D - 2);
                graphics.setColor(this.GF.whiteSparkleColor());
                graphics.drawArc(n3 + this.D / 2, n4 + this.D / 4, this.D / 4, this.D / 4, 40, 50);
            }
        }
        if (this.P.tree(n, n2) != null) {
            if (this.GF.bwColor()) {
                graphics.setColor(Color.white);
            }
            else {
                graphics.setColor(Color.green);
            }
            graphics.drawLine(n3 + this.D / 2 - this.D / 6, n4 + this.D / 2, n3 + this.D / 2 + this.D / 6, n4 + this.D / 2);
            graphics.drawLine(n3 + this.D / 2, n4 + this.D / 2 - this.D / 6, n3 + this.D / 2, n4 + this.D / 2 + this.D / 6);
        }
        if (this.P.marker(n, n2) != 0) {
            if (this.GF.bwColor()) {
                if (this.P.color(n, n2) >= 0) {
                    graphics.setColor(Color.white);
                }
                else {
                    graphics.setColor(Color.black);
                }
            }
            else {
                graphics.setColor(this.GF.markerColor());
            }
            final int n5 = this.D / 4;
            switch (this.P.marker(n, n2)) {
                case 4: {
                    graphics.drawOval(n3 + this.D / 2 - n5, n4 + this.D / 2 - n5, 2 * n5, 2 * n5);
                    break;
                }
                case 1: {
                    graphics.drawLine(n3 + this.D / 2 - n5, n4 + this.D / 2 - n5, n3 + this.D / 2 + n5, n4 + this.D / 2 + n5);
                    graphics.drawLine(n3 + this.D / 2 + n5, n4 + this.D / 2 - n5, n3 + this.D / 2 - n5, n4 + this.D / 2 + n5);
                    break;
                }
                case 3: {
                    graphics.drawLine(n3 + this.D / 2, n4 + this.D / 2 - n5, n3 + this.D / 2 - n5, n4 + this.D / 2 + n5);
                    graphics.drawLine(n3 + this.D / 2, n4 + this.D / 2 - n5, n3 + this.D / 2 + n5, n4 + this.D / 2 + n5);
                    graphics.drawLine(n3 + this.D / 2 - n5, n4 + this.D / 2 + n5, n3 + this.D / 2 + n5, n4 + this.D / 2 + n5);
                    break;
                }
                default: {
                    graphics.drawRect(n3 + this.D / 2 - n5, n4 + this.D / 2 - n5, 2 * n5, 2 * n5);
                    break;
                }
            }
        }
        if (this.P.letter(n, n2) != 0) {
            if (this.GF.bwColor()) {
                if (this.P.color(n, n2) >= 0) {
                    graphics.setColor(Color.white);
                }
                else {
                    graphics.setColor(Color.black);
                }
            }
            else {
                graphics.setColor(this.GF.labelColor());
            }
            array[0] = (char)(97 + this.P.letter(n, n2) - 1);
            final String s = new String(array);
            final int n6 = this.fontmetrics.stringWidth(s) / 2;
            final int n7 = this.fontmetrics.getAscent() / 2 - 1;
            graphics.setFont(this.font);
            graphics.drawString(s, n3 + this.D / 2 - n6, n4 + this.D / 2 + n7);
        }
        if (this.P.haslabel(n, n2)) {
            if (this.GF.bwColor()) {
                if (this.P.color(n, n2) >= 0) {
                    graphics.setColor(Color.white);
                }
                else {
                    graphics.setColor(Color.black);
                }
            }
            else {
                graphics.setColor(this.GF.labelColor());
            }
            final String label = this.P.label(n, n2);
            final int n8 = this.fontmetrics.stringWidth(label) / 2;
            final int n9 = this.fontmetrics.getAscent() / 2 - 1;
            graphics.setFont(this.font);
            graphics.drawString(label, n3 + this.D / 2 - n8, n4 + this.D / 2 + n9);
        }
        if (this.sendi == n && this.sendj == n2) {
            if (this.GF.bwColor()) {
                if (this.P.color(n, n2) > 0) {
                    graphics.setColor(Color.white);
                }
                else {
                    graphics.setColor(Color.black);
                }
            }
            else {
                graphics.setColor(Color.gray);
            }
            graphics.drawLine(n3 + this.D / 2 - 1, n4 + this.D / 2, n3 + this.D / 2 + 1, n4 + this.D / 2);
            graphics.drawLine(n3 + this.D / 2, n4 + this.D / 2 - 1, n3 + this.D / 2, n4 + this.D / 2 + 1);
        }
        if (this.lasti != n || this.lastj != n2 || !this.showlast) {
            if (this.P.color(n, n2) != 0 && this.Range >= 0 && this.P.number(n, n2) > this.Range) {
                if (this.P.color(n, n2) > 0) {
                    graphics.setColor(Color.white);
                }
                else {
                    graphics.setColor(Color.black);
                }
                final String value = String.valueOf(this.P.number(n, n2) % 100);
                final int n10 = this.fontmetrics.stringWidth(value) / 2;
                final int n11 = this.fontmetrics.getAscent() / 2 - 1;
                graphics.setFont(this.font);
                graphics.drawString(value, n3 + this.D / 2 - n10, n4 + this.D / 2 + n11);
            }
            return;
        }
        if (this.GF.lastNumber() || (this.Range >= 0 && this.P.number(n, n2) > this.Range)) {
            if (this.P.color(n, n2) > 0) {
                graphics.setColor(Color.white);
            }
            else {
                graphics.setColor(Color.black);
            }
            final String value2 = String.valueOf(this.P.number(n, n2) % 100);
            final int n12 = this.fontmetrics.stringWidth(value2) / 2;
            final int n13 = this.fontmetrics.getAscent() / 2 - 1;
            graphics.setFont(this.font);
            graphics.drawString(value2, n3 + this.D / 2 - n12, n4 + this.D / 2 + n13);
            return;
        }
        if (this.GF.bwColor()) {
            if (this.P.color(n, n2) > 0) {
                graphics.setColor(Color.white);
            }
            else {
                graphics.setColor(Color.black);
            }
        }
        else {
            graphics.setColor(Color.red);
        }
        graphics.drawLine(n3 + this.D / 2 - this.D / 6, n4 + this.D / 2, n3 + this.D / 2 + this.D / 6, n4 + this.D / 2);
        graphics.drawLine(n3 + this.D / 2, n4 + this.D / 2 - this.D / 6, n3 + this.D / 2, n4 + this.D / 2 + this.D / 6);
    }
    
    public void copy() {
        if (this.ActiveImage == null) {
            return;
        }
        try {
            this.getGraphics().drawImage(this.ActiveImage, 0, 0, this);
        }
        catch (Exception ex) {}
    }
    
    public void undonode() {
        final Node node = this.Pos.node();
        this.clearrange();
        for (ListElement listElement = node.lastchange(); listElement != null; listElement = listElement.previous()) {
            final Change change = (Change)listElement.content();
            this.P.color(change.I, change.J, change.C);
            this.update(change.I, change.J);
        }
        node.clearchanges();
        this.Pw -= node.Pw;
        this.Pb -= node.Pb;
    }
    
    public void setaction(final Node node, final Action action, final int n) {
        final String s = (String)action.arguments().content();
        final int i = Field.i(s);
        final int j = Field.j(s);
        if (!this.valid(i, j)) {
            return;
        }
        node.addchange(new Change(i, j, this.P.color(i, j)));
        this.P.color(i, j, n);
        this.P.number(i, j, node.number() - 1);
        this.showlast = false;
        this.update(this.lasti, this.lastj);
        this.showlast = true;
        this.update(this.lasti = i, this.lastj = j);
        this.P.color(-n);
        this.capture(i, j, node);
    }
    
    public void placeaction(final Node node, final Action action, final int n) {
        for (ListElement listElement = action.arguments(); listElement != null; listElement = listElement.next()) {
            final String s = (String)listElement.content();
            final int i = Field.i(s);
            final int j = Field.j(s);
            if (this.valid(i, j)) {
                node.addchange(new Change(i, j, this.P.color(i, j)));
                this.P.color(i, j, n);
                this.update(i, j);
            }
        }
    }
    
    public void emptyaction(final Node node, final Action action) {
        for (ListElement listElement = action.arguments(); listElement != null; listElement = listElement.next()) {
            final String s = (String)listElement.content();
            final int i = Field.i(s);
            final int j = Field.j(s);
            if (this.valid(i, j)) {
                node.addchange(new Change(i, j, this.P.color(i, j)));
                if (this.P.color(i, j) < 0) {
                    ++node.Pw;
                    ++this.Pw;
                }
                else if (this.P.color(i, j) > 0) {
                    ++node.Pb;
                    ++this.Pb;
                }
                this.P.color(i, j, 0);
                this.update(i, j);
            }
        }
    }
    
    public void setnode() {
        final Node node = this.Pos.node();
        ListElement listElement = node.actions();
        if (listElement == null) {
            return;
        }
        while (listElement != null) {
            final Action action = (Action)listElement.content();
            if (action.type().equals("SZ") && this.Pos.parentPos() == null) {
                try {
                    final int int1 = Integer.parseInt(action.argument().trim());
                    if (int1 != this.S) {
                        this.S = int1;
                        this.P = new Position(this.S);
                        this.makeimages();
                        this.updateall();
                        this.copy();
                    }
                }
                catch (NumberFormatException ex) {}
            }
            listElement = listElement.next();
        }
        node.clearchanges();
        final Node node2 = node;
        final Node node3 = node;
        final boolean b = false;
        node3.Pb = (b ? 1 : 0);
        node2.Pw = (b ? 1 : 0);
        for (ListElement listElement2 = node.actions(); listElement2 != null; listElement2 = listElement2.next()) {
            final Action action2 = (Action)listElement2.content();
            if (action2.type().equals("B")) {
                this.setaction(node, action2, 1);
            }
            else if (action2.type().equals("W")) {
                this.setaction(node, action2, -1);
            }
            if (action2.type().equals("AB")) {
                this.placeaction(node, action2, 1);
            }
            if (action2.type().equals("AW")) {
                this.placeaction(node, action2, -1);
            }
            else if (action2.type().equals("AE")) {
                this.emptyaction(node, action2);
            }
        }
    }
    
    public void setlast() {
        final Node node = this.Pos.node();
        ListElement listElement = node.actions();
        final int lasti = this.lasti;
        final int lastj = this.lastj;
        this.lasti = -1;
        this.lastj = -1;
        this.update(lasti, lastj);
        while (listElement != null) {
            final Action action = (Action)listElement.content();
            if (action.type().equals("B") || action.type().equals("W")) {
                final String s = (String)action.arguments().content();
                final int i = Field.i(s);
                final int j = Field.j(s);
                if (this.valid(i, j)) {
                    this.lasti = i;
                    this.lastj = j;
                    this.update(this.lasti, this.lastj);
                    this.P.color(-this.P.color(i, j));
                }
            }
            listElement = listElement.next();
        }
        this.number = node.number();
    }
    
    public void undo() {
        if (this.Pos.haschildren() || (this.Pos.parent() != null && this.Pos.parent().lastchild() != this.Pos.parent().firstchild() && this.Pos == this.Pos.parent().firstchild())) {
            if (this.GF.askUndo()) {
                this.doundo(this.Pos);
            }
        }
        else {
            this.doundo(this.Pos);
        }
    }
    
    public void doundo(final TreeNode treeNode) {
        if (treeNode != this.Pos) {
            return;
        }
        if (this.Pos.parentPos() == null) {
            this.undonode();
            this.Pos.removeall();
            this.Pos.node().removeactions();
            this.showinformation();
            this.copy();
            return;
        }
        final TreeNode pos = this.Pos;
        this.goback();
        if (pos == this.Pos.firstchild()) {
            this.Pos.removeall();
        }
        else {
            this.Pos.remove(pos);
        }
        this.goforward();
        this.showinformation();
        this.copy();
    }
    
    public void goback() {
        this.State = 1;
        if (this.Pos.parentPos() == null) {
            return;
        }
        this.undonode();
        this.Pos = this.Pos.parentPos();
        this.setlast();
    }
    
    public void goforward() {
        if (!this.Pos.haschildren()) {
            return;
        }
        this.Pos = this.Pos.firstChild();
        this.setnode();
        this.setlast();
    }
    
    public void tovarleft() {
        final ListElement listelement = this.Pos.listelement();
        if (listelement == null) {
            return;
        }
        if (listelement.previous() == null) {
            return;
        }
        final TreeNode pos = (TreeNode)listelement.previous().content();
        this.goback();
        this.Pos = pos;
        this.setnode();
    }
    
    public void tovarright() {
        final ListElement listelement = this.Pos.listelement();
        if (listelement == null) {
            return;
        }
        if (listelement.next() == null) {
            return;
        }
        final TreeNode pos = (TreeNode)listelement.next().content();
        this.goback();
        this.Pos = pos;
        this.setnode();
    }
    
    public boolean hasvariation() {
        final ListElement listelement = this.Pos.listelement();
        return listelement != null && listelement.next() != null;
    }
    
    public void territory(final int n, final int n2) {
        this.mark(n, n2);
        this.copy();
    }
    
    public void setpass() {
        TreeNode treeNode;
        for (treeNode = this.T.top(); treeNode.haschildren(); treeNode = treeNode.firstChild()) {}
        final Node node = new Node(this.number);
        treeNode.addchild(new TreeNode(node));
        node.main(treeNode);
        this.GF.yourMove(this.Pos != treeNode);
        if (this.Pos == treeNode) {
            this.getinformation();
            final int color = this.P.color();
            this.goforward();
            this.P.color(-color);
            this.showinformation();
            this.GF.addComment(this.GF.resourceString("Pass"));
        }
        this.MainColor = -this.MainColor;
    }
    
    public void resume() {
        this.getinformation();
        this.State = 1;
        this.showinformation();
    }
    
    Node newtree() {
        this.number = 1;
        final boolean b = false;
        this.Pb = (b ? 1 : 0);
        this.Pw = (b ? 1 : 0);
        final Node node = new Node(this.number);
        this.T = new SGFTree(node);
        this.Trees.setElementAt(this.T, this.CurrentTree);
        this.resettree();
        return node;
    }
    
    void resettree() {
        this.Pos = this.T.top();
        this.P = new Position(this.S);
        final int n = -1;
        this.lastj = n;
        this.lasti = n;
        final boolean b = false;
        this.Pw = (b ? 1 : 0);
        this.Pb = (b ? 1 : 0);
        this.updateall();
        this.copy();
    }
    
    public boolean deltree() {
        this.newtree();
        return true;
    }
    
    public void active(final boolean active) {
        this.Active = active;
    }
    
    public int getboardsize() {
        return this.S;
    }
    
    public boolean canfinish() {
        return this.Pos.isLastMain();
    }
    
    public int maincolor() {
        return this.MainColor;
    }
    
    public boolean ismain() {
        return this.Pos.isLastMain();
    }
    
    Node firstnode() {
        return this.T.top().node();
    }
    
    boolean valid(final int n, final int n2) {
        return n >= 0 && n < this.S && n2 >= 0 && n2 < this.S;
    }
    
    public void clearrange() {
        if (this.Range == -1) {
            return;
        }
        this.Range = -1;
        this.updateall();
        this.copy();
    }
    
    public void back() {
        this.State = 1;
        this.getinformation();
        this.goback();
        this.showinformation();
        this.copy();
    }
    
    public void forward() {
        this.State = 1;
        this.getinformation();
        this.goforward();
        this.showinformation();
        this.copy();
    }
    
    public void fastback() {
        this.getinformation();
        for (int i = 0; i < 10; ++i) {
            this.goback();
        }
        this.showinformation();
        this.copy();
    }
    
    public void fastforward() {
        this.getinformation();
        for (int i = 0; i < 10; ++i) {
            this.goforward();
        }
        this.showinformation();
        this.copy();
    }
    
    public void allback() {
        this.getinformation();
        while (this.Pos.parentPos() != null) {
            this.goback();
        }
        this.showinformation();
        this.copy();
    }
    
    public void allforward() {
        this.getinformation();
        while (this.Pos.haschildren()) {
            this.goforward();
        }
        this.showinformation();
        this.copy();
    }
    
    public void varleft() {
        this.State = 1;
        this.getinformation();
        final ListElement listelement = this.Pos.listelement();
        if (listelement == null) {
            return;
        }
        if (listelement.previous() == null) {
            return;
        }
        final TreeNode pos = (TreeNode)listelement.previous().content();
        this.goback();
        this.Pos = pos;
        this.setnode();
        this.showinformation();
        this.copy();
    }
    
    public void varright() {
        this.State = 1;
        this.getinformation();
        final ListElement listelement = this.Pos.listelement();
        if (listelement == null) {
            return;
        }
        if (listelement.next() == null) {
            return;
        }
        final TreeNode pos = (TreeNode)listelement.next().content();
        this.goback();
        this.Pos = pos;
        this.setnode();
        this.showinformation();
        this.copy();
    }
    
    public void varmain() {
        this.State = 1;
        this.getinformation();
        while (this.Pos.parentPos() != null && !this.Pos.node().main()) {
            this.goback();
        }
        if (this.Pos.haschildren()) {
            this.goforward();
        }
        this.showinformation();
        this.copy();
    }
    
    public void varmaindown() {
        this.State = 1;
        this.getinformation();
        while (this.Pos.parentPos() != null) {
            if (this.Pos.node().main()) {
                break;
            }
            this.goback();
        }
        while (this.Pos.haschildren()) {
            this.goforward();
        }
        this.showinformation();
        this.copy();
    }
    
    public void varup() {
        this.State = 1;
        this.getinformation();
        if (this.Pos.parentPos() != null) {
            this.goback();
        }
        while (this.Pos.parentPos() != null && this.Pos.parentPos().firstChild() == this.Pos.parentPos().lastChild() && !this.Pos.node().main()) {
            this.goback();
        }
        this.showinformation();
        this.copy();
    }
    
    void gotonext() {
        this.State = 1;
        this.getinformation();
        this.goforward();
        while (this.Pos.node().getaction("N").equals("") && this.Pos.haschildren()) {
            this.goforward();
        }
        this.showinformation();
        this.copy();
    }
    
    void gotoprevious() {
        this.State = 1;
        this.getinformation();
        this.goback();
        while (this.Pos.node().getaction("N").equals("") && this.Pos.parentPos() != null) {
            this.goback();
        }
        this.showinformation();
        this.copy();
    }
    
    void gotonextmain() {
        if (this.CurrentTree + 1 >= this.Trees.size()) {
            return;
        }
        this.State = 1;
        this.getinformation();
        this.T.top().setaction("AP", "Jago:" + this.GF.version(), true);
        this.T.top().setaction("SZ", String.valueOf(this.S), true);
        this.T.top().setaction("GM", "1", true);
        this.T.top().setaction("FF", this.GF.getParameter("puresgf", false) ? "4" : "1", true);
        ++this.CurrentTree;
        this.T = this.Trees.elementAt(this.CurrentTree);
        this.resettree();
        this.setnode();
        this.showinformation();
        this.copy();
    }
    
    void gotopreviousmain() {
        if (this.CurrentTree == 0) {
            return;
        }
        this.State = 1;
        this.getinformation();
        this.T.top().setaction("AP", "Jago:" + this.GF.version(), true);
        this.T.top().setaction("SZ", String.valueOf(this.S), true);
        this.T.top().setaction("GM", "1", true);
        this.T.top().setaction("FF", this.GF.getParameter("puresgf", false) ? "4" : "1", true);
        --this.CurrentTree;
        this.T = this.Trees.elementAt(this.CurrentTree);
        this.resettree();
        this.setnode();
        this.showinformation();
        this.copy();
    }
    
    void addnewgame() {
        this.State = 1;
        this.getinformation();
        this.T.top().setaction("AP", "Jago:" + this.GF.version(), true);
        this.T.top().setaction("SZ", String.valueOf(this.S), true);
        this.T.top().setaction("GM", "1", true);
        this.T.top().setaction("FF", this.GF.getParameter("puresgf", false) ? "4" : "1", true);
        this.T = new SGFTree(new Node(this.number));
        ++this.CurrentTree;
        if (this.CurrentTree >= this.Trees.size()) {
            this.Trees.addElement(this.T);
        }
        else {
            this.Trees.insertElementAt(this.T, this.CurrentTree);
        }
        this.resettree();
        this.setnode();
        this.showinformation();
        this.copy();
    }
    
    void removegame() {
        if (this.Trees.size() == 0) {
            return;
        }
        this.Trees.removeElementAt(this.CurrentTree);
        if (this.CurrentTree >= this.Trees.size()) {
            --this.CurrentTree;
        }
        this.T = this.Trees.elementAt(this.CurrentTree);
        this.resettree();
        this.setnode();
        this.showinformation();
        this.copy();
    }
    
    public void black(final int n, final int n2) {
        if (n < 0 || n2 < 0 || n >= this.S || n2 >= this.S) {
            return;
        }
        TreeNode treeNode;
        for (treeNode = this.T.top(); treeNode.haschildren(); treeNode = treeNode.firstChild()) {}
        final Action action = new Action("B", Field.string(n, n2));
        final Node node = new Node(treeNode.node().number() + 1);
        node.addaction(action);
        treeNode.addchild(new TreeNode(node));
        node.main(treeNode);
        this.GF.yourMove(this.Pos != treeNode);
        if (this.Pos == treeNode) {
            this.forward();
        }
        this.MainColor = -1;
    }
    
    public void white(final int n, final int n2) {
        if (n < 0 || n2 < 0 || n >= this.S || n2 >= this.S) {
            return;
        }
        TreeNode treeNode;
        for (treeNode = this.T.top(); treeNode.haschildren(); treeNode = treeNode.firstChild()) {}
        final Action action = new Action("W", Field.string(n, n2));
        final Node node = new Node(treeNode.node().number() + 1);
        node.addaction(action);
        treeNode.addchild(new TreeNode(node));
        node.main(treeNode);
        this.GF.yourMove(this.Pos != treeNode);
        if (this.Pos == treeNode) {
            this.forward();
        }
        this.MainColor = 1;
    }
    
    public void setblack(final int n, final int n2) {
        if (n < 0 || n2 < 0 || n >= this.S || n2 >= this.S) {
            return;
        }
        TreeNode treeNode;
        for (treeNode = this.T.top(); treeNode.haschildren(); treeNode = treeNode.firstChild()) {}
        final Action action = new Action("AB", Field.string(n, n2));
        if (treeNode == this.T.top()) {
            final TreeNode pos;
            treeNode.addchild(pos = new TreeNode(1));
            if (this.Pos == treeNode) {
                this.Pos = pos;
            }
            treeNode = pos;
            treeNode.main(true);
        }
        final Node node = treeNode.node();
        node.expandaction(action);
        if (this.Pos == treeNode) {
            node.addchange(new Change(n, n2, this.P.color(n, n2)));
            this.P.color(n, n2, 1);
            this.update(n, n2);
            this.copy();
        }
        this.MainColor = -1;
    }
    
    public void setwhite(final int n, final int n2) {
        if (n < 0 || n2 < 0 || n >= this.S || n2 >= this.S) {
            return;
        }
        TreeNode treeNode;
        for (treeNode = this.T.top(); treeNode.haschildren(); treeNode = treeNode.firstChild()) {}
        final Action action = new Action("AW", Field.string(n, n2));
        if (treeNode == this.T.top()) {
            final TreeNode pos;
            treeNode.addchild(pos = new TreeNode(1));
            if (this.Pos == treeNode) {
                this.Pos = pos;
            }
            treeNode = pos;
            treeNode.main(true);
        }
        final Node node = treeNode.node();
        node.expandaction(action);
        if (this.Pos == treeNode) {
            node.addchange(new Change(n, n2, this.P.color(n, n2)));
            this.P.color(n, n2, -1);
            this.update(n, n2);
            this.copy();
        }
        this.MainColor = 1;
    }
    
    public void pass() {
        if (this.Pos.haschildren()) {
            return;
        }
        if (this.GF.blocked() && this.Pos.node().main()) {
            return;
        }
        this.getinformation();
        this.P.color(-this.P.color());
        final Node node = new Node(this.number);
        this.Pos.addchild(new TreeNode(node));
        node.main(this.Pos);
        this.goforward();
        this.setlast();
        this.showinformation();
        this.copy();
        this.GF.addComment(this.GF.resourceString("Pass"));
    }
    
    public void remove(final int n, final int n2) {
        final int state = this.State;
        this.varmaindown();
        this.State = state;
        if (this.P.color(n, n2) == 0) {
            return;
        }
        this.P.markgroup(n, n2);
        this.P.color(n, n2);
        Node node = this.Pos.node();
        if (this.GF.getParameter("puresgf", true) && (node.contains("B") || node.contains("W"))) {
            node = this.newnode();
        }
        for (int i = 0; i < this.S; ++i) {
            for (int j = 0; j < this.S; ++j) {
                if (this.P.marked(i, j)) {
                    final Action action = new Action("AE", Field.string(i, j));
                    node.addchange(new Change(i, j, this.P.color(i, j)));
                    node.expandaction(action);
                    if (this.P.color(i, j) > 0) {
                        final Node node2 = node;
                        ++node2.Pb;
                        ++this.Pb;
                    }
                    else {
                        final Node node3 = node;
                        ++node3.Pw;
                        ++this.Pw;
                    }
                    this.P.color(i, j, 0);
                    this.update(i, j);
                }
            }
        }
        this.copy();
    }
    
    public void clearmarks() {
        this.getinformation();
        this.undonode();
        ListElement next;
        for (ListElement actions = this.Pos.node().actions(); actions != null; actions = next) {
            final Action action = (Action)actions.content();
            next = actions.next();
            if (action.type().equals("M") || action.type().equals("L") || action.type().equals("MA") || action.type().equals("SQ") || action.type().equals("SL") || action.type().equals("CR") || action.type().equals("TR") || action.type().equals("LB")) {
                this.Pos.node().removeaction(actions);
            }
        }
        this.setnode();
        this.showinformation();
        this.copy();
    }
    
    public void clearremovals() {
        if (this.Pos.haschildren()) {
            return;
        }
        this.getinformation();
        this.undonode();
        ListElement next;
        for (ListElement actions = this.Pos.node().actions(); actions != null; actions = next) {
            final Action action = (Action)actions.content();
            next = actions.next();
            if (action.type().equals("AB") || action.type().equals("AW") || action.type().equals("AE")) {
                this.Pos.node().removeaction(actions);
            }
        }
        this.setnode();
        this.showinformation();
        this.copy();
    }
    
    public void insertnode() {
        if (this.Pos.haschildren() && !this.GF.askInsert()) {
            return;
        }
        final Node node = new Node(this.Pos.node().number());
        this.Pos.insertchild(new TreeNode(node));
        node.main(this.Pos);
        this.getinformation();
        this.Pos = this.Pos.lastChild();
        this.setlast();
        this.showinformation();
        this.copy();
    }
    
    public void insertvariation() {
        if (this.Pos.parentPos() == null) {
            return;
        }
        this.getinformation();
        final int color = this.P.color();
        this.back();
        final Node node = new Node(2);
        this.Pos.addchild(new TreeNode(node));
        node.main(this.Pos);
        this.Pos = this.Pos.lastChild();
        this.setlast();
        this.P.color(-color);
        this.showinformation();
        this.copy();
    }
    
    public void undo(final int n) {
        this.varmaindown();
        for (int i = 0; i < n; ++i) {
            this.goback();
            this.Pos.removeall();
            this.showinformation();
            this.copy();
        }
        this.GF.addComment(this.GF.resourceString("Undo"));
    }
    
    public void setblack() {
        this.getinformation();
        this.State = 3;
        this.showinformation();
    }
    
    public void setwhite() {
        this.getinformation();
        this.State = 4;
        this.showinformation();
    }
    
    public void black() {
        this.getinformation();
        this.State = 1;
        this.P.color(1);
        this.showinformation();
    }
    
    public void white() {
        this.getinformation();
        this.State = 2;
        this.P.color(-1);
        this.showinformation();
    }
    
    public void mark() {
        this.getinformation();
        this.State = 5;
        this.showinformation();
    }
    
    public void specialmark(final int specialMarker) {
        this.getinformation();
        this.State = 9;
        this.SpecialMarker = specialMarker;
        this.showinformation();
    }
    
    public void textmark(final String textMarker) {
        this.getinformation();
        this.State = 10;
        this.TextMarker = textMarker;
        this.showinformation();
    }
    
    public void letter() {
        this.getinformation();
        this.State = 6;
        this.showinformation();
    }
    
    public void deletestones() {
        this.getinformation();
        this.State = 7;
        this.showinformation();
    }
    
    public boolean score() {
        if (this.Pos.haschildren()) {
            return false;
        }
        this.getinformation();
        this.State = 8;
        this.Removing = true;
        this.showinformation();
        return this.Pos.node().main();
    }
    
    public synchronized void setsize(final int s) {
        if (s < 5 || s > 29) {
            return;
        }
        this.S = s;
        this.P = new Position(this.S);
        this.number = 1;
        final Node node = new Node(this.number);
        node.main(true);
        final int n = -1;
        this.lastj = n;
        this.lasti = n;
        this.T = new SGFTree(node);
        this.Trees.setElementAt(this.T, this.CurrentTree);
        this.Pos = this.T.top();
        this.makeimages();
        this.showinformation();
        this.copy();
    }
    
    void setname(final String s) {
        this.Pos.setaction("N", s, true);
        this.showinformation();
    }
    
    public void setinformation(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        this.T.top().setaction("PB", s, true);
        this.T.top().setaction("BR", s2, true);
        this.T.top().setaction("PW", s3, true);
        this.T.top().setaction("WR", s4, true);
        this.T.top().setaction("KM", s5, true);
        this.T.top().setaction("HA", s6, true);
        this.T.top().setaction("GN", String.valueOf(s3) + "-" + s, true);
        this.T.top().setaction("DT", new Date().toString());
    }
    
    String getname() {
        return this.T.top().getaction("N");
    }
    
    public String getKomi() {
        return this.T.top().getaction("KM");
    }
    
    public String extraInformation() {
        final StringBuffer sb = new StringBuffer(this.GF.resourceString("_("));
        final Node node = this.T.top().node();
        if (node.contains("HA")) {
            sb.append(this.GF.resourceString("Ha_"));
            sb.append(node.getaction("HA"));
        }
        if (node.contains("KM")) {
            sb.append(this.GF.resourceString("__Ko"));
            sb.append(node.getaction("KM"));
        }
        sb.append(this.GF.resourceString("__B"));
        sb.append(String.valueOf(this.Pw));
        sb.append(this.GF.resourceString("__W"));
        sb.append(String.valueOf(this.Pb));
        sb.append(this.GF.resourceString("_)"));
        return sb.toString();
    }
    
    public void print(final Frame frame) {
        new PrintBoard(new Position(this.P), this.Range, frame);
    }
    
    public void lastrange(final int n) {
        this.Range = (this.Pos.node().number() - 2) / n * n;
        if (this.Range < 0) {
            this.Range = 0;
        }
        this.KeepRange = true;
        this.updateall();
        this.copy();
        this.KeepRange = false;
    }
    
    public void addcomment(final String s) {
        TreeNode treeNode;
        for (treeNode = this.T.top(); treeNode.haschildren(); treeNode = treeNode.firstChild()) {}
        if (this.Pos == treeNode) {
            this.getinformation();
        }
        ListElement listElement = treeNode.node().actions();
        String string = "";
        while (true) {
            while (listElement != null) {
                final Action action = (Action)listElement.content();
                if (action.type().equals("C")) {
                    final ListElement arguments = action.arguments();
                    if (((String)arguments.content()).equals("")) {
                        arguments.content(s);
                        string = s;
                    }
                    else {
                        arguments.content(String.valueOf(arguments.content()) + "\n" + s);
                        string = "\n" + s;
                    }
                    if (this.Pos == treeNode) {
                        this.GF.appendComment(string);
                        this.showinformation();
                    }
                    return;
                }
                listElement = listElement.next();
            }
            treeNode.addaction(new Action("C", s));
            continue;
        }
    }
    
    public String done() {
        if (this.Pos.haschildren()) {
            return null;
        }
        this.clearmarks();
        this.getinformation();
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        this.P.getterritory();
        for (int i = 0; i < this.S; ++i) {
            for (int j = 0; j < this.S; ++j) {
                if (this.P.territory(i, j) == 1 || this.P.territory(i, j) == -1) {
                    this.markterritory(i, j, this.P.territory(i, j));
                    if (this.P.territory(i, j) > 0) {
                        ++n;
                    }
                    else {
                        ++n2;
                    }
                }
                else if (this.P.color(i, j) > 0) {
                    ++n3;
                }
                else if (this.P.color(i, j) < 0) {
                    ++n4;
                }
            }
        }
        final String string = String.valueOf(this.GF.resourceString("Chinese_count_")) + "\n" + this.GF.resourceString("Black__") + (n3 + n) + this.GF.resourceString("__White__") + (n4 + n2) + "\n" + this.GF.resourceString("Japanese_count_") + "\n" + this.GF.resourceString("Black__") + (this.Pw + n) + this.GF.resourceString("__White__") + (this.Pb + n2);
        this.showinformation();
        this.copy();
        if (this.Pos.node().main()) {
            this.GF.result(n, n2);
        }
        return string;
    }
    
    public String docount() {
        this.clearmarks();
        this.getinformation();
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        this.P.getterritory();
        for (int i = 0; i < this.S; ++i) {
            for (int j = 0; j < this.S; ++j) {
                if (this.P.territory(i, j) == 1 || this.P.territory(i, j) == -1) {
                    this.markterritory(i, j, this.P.territory(i, j));
                    if (this.P.territory(i, j) > 0) {
                        ++n;
                    }
                    else {
                        ++n2;
                    }
                }
                else if (this.P.color(i, j) > 0) {
                    ++n3;
                }
                else if (this.P.color(i, j) < 0) {
                    ++n4;
                }
            }
        }
        this.showinformation();
        this.copy();
        return String.valueOf(this.GF.resourceString("Chinese_count_")) + "\n" + this.GF.resourceString("Black__") + (n3 + n) + this.GF.resourceString("__White__") + (n4 + n2) + "\n" + this.GF.resourceString("Japanese_count_") + "\n" + this.GF.resourceString("Black__") + (this.Pw + n) + this.GF.resourceString("__White__") + (this.Pb + n2);
    }
    
    public void load(final BufferedReader bufferedReader) throws IOException {
        this.showlast = false;
        this.update(this.lasti, this.lastj);
        this.showlast = true;
        final int n = -1;
        this.lastj = n;
        this.lasti = n;
        this.newtree();
        final Vector load = SGFTree.load(bufferedReader, this.GF);
        if (load.size() == 0) {
            return;
        }
        this.Trees = load;
        this.T = load.elementAt(0);
        this.CurrentTree = 0;
        this.resettree();
        this.setnode();
        this.showinformation();
        this.copy();
    }
    
    public void loadXml(final XmlReader xmlReader) throws XmlReaderException {
        this.showlast = false;
        this.update(this.lasti, this.lastj);
        this.showlast = true;
        final int n = -1;
        this.lastj = n;
        this.lasti = n;
        this.newtree();
        final Vector load = SGFTree.load(xmlReader, this.GF);
        if (load.size() == 0) {
            return;
        }
        this.Trees = load;
        this.T = load.elementAt(0);
        this.CurrentTree = 0;
        this.resettree();
        this.setnode();
        this.showinformation();
        this.copy();
    }
    
    public void save(final PrintWriter printWriter) {
        this.getinformation();
        this.T.top().setaction("AP", "Jago:" + this.GF.version(), true);
        this.T.top().setaction("SZ", String.valueOf(this.S), true);
        this.T.top().setaction("GM", "1", true);
        this.T.top().setaction("FF", this.GF.getParameter("puresgf", false) ? "4" : "1", true);
        for (int i = 0; i < this.Trees.size(); ++i) {
            ((SGFTree)this.Trees.elementAt(i)).print(printWriter);
        }
    }
    
    public void saveXML(final PrintWriter printWriter, final String s) {
        this.getinformation();
        this.T.top().setaction("AP", "Jago:" + this.GF.version(), true);
        this.T.top().setaction("SZ", String.valueOf(this.S), true);
        this.T.top().setaction("GM", "1", true);
        this.T.top().setaction("FF", this.GF.getParameter("puresgf", false) ? "4" : "1", true);
        final XmlWriter xmlWriter = new XmlWriter(printWriter);
        xmlWriter.printEncoding(s);
        xmlWriter.printXls("go.xsl");
        xmlWriter.printDoctype("Go", "go.dtd");
        xmlWriter.startTagNewLine("Go");
        for (int i = 0; i < this.Trees.size(); ++i) {
            ((SGFTree)this.Trees.elementAt(i)).printXML(xmlWriter);
        }
        xmlWriter.endTagNewLine("Go");
    }
    
    public void asciisave(final PrintWriter printWriter) {
        printWriter.println(this.T.top().getaction("GN"));
        printWriter.print("      ");
        for (int i = 0; i < this.S; ++i) {
            char c;
            if (i <= 7) {
                c = (char)(65 + i);
            }
            else {
                c = (char)(65 + i + 1);
            }
            printWriter.print(" " + c);
        }
        printWriter.println();
        printWriter.print("      ");
        for (int j = 0; j < this.S; ++j) {
            printWriter.print("--");
        }
        printWriter.println("-");
        for (int k = 0; k < this.S; ++k) {
            printWriter.print("  ");
            if (this.S - k < 10) {
                printWriter.print(" " + (this.S - k));
            }
            else {
                printWriter.print(this.S - k);
            }
            printWriter.print(" |");
            for (int l = 0; l < this.S; ++l) {
                switch (this.P.color(l, k)) {
                    case 1: {
                        printWriter.print(" #");
                        break;
                    }
                    case -1: {
                        printWriter.print(" O");
                        break;
                    }
                    case 0: {
                        if (this.P.haslabel(l, k)) {
                            printWriter.print(" " + this.P.label(l, k));
                            break;
                        }
                        if (this.P.letter(l, k) > 0) {
                            printWriter.print(" " + (char)(this.P.letter(l, k) + 97 - 1));
                            break;
                        }
                        if (this.P.marker(l, k) > 0) {
                            printWriter.print(" X");
                            break;
                        }
                        if (this.ishand(k) && this.ishand(l)) {
                            printWriter.print(" ,");
                            break;
                        }
                        printWriter.print(" .");
                        break;
                    }
                }
            }
            printWriter.print(" | ");
            if (this.S - k < 10) {
                printWriter.print(" " + (this.S - k));
            }
            else {
                printWriter.print(this.S - k);
            }
            printWriter.println();
        }
        printWriter.print("      ");
        for (int n = 0; n < this.S; ++n) {
            printWriter.print("--");
        }
        printWriter.println("-");
        printWriter.print("      ");
        for (int n2 = 0; n2 < this.S; ++n2) {
            char c2;
            if (n2 <= 7) {
                c2 = (char)(65 + n2);
            }
            else {
                c2 = (char)(65 + n2 + 1);
            }
            printWriter.print(" " + c2);
        }
        printWriter.println();
    }
    
    boolean ishand(final int n) {
        if (this.S > 13) {
            return n == 3 || n == this.S - 4 || n == this.S / 2;
        }
        return this.S > 9 && (n == 3 || n == this.S - 4);
    }
    
    public void handicap(final int n) {
        final int n2 = (this.S < 13) ? 3 : 4;
        if (n > 5) {
            this.setblack(n2 - 1, this.S / 2);
            this.setblack(this.S - n2, this.S / 2);
        }
        if (n > 7) {
            this.setblack(this.S / 2, n2 - 1);
            this.setblack(this.S / 2, this.S - n2);
        }
        switch (n) {
            case 5:
            case 7:
            case 9: {
                this.setblack(this.S / 2, this.S / 2);
            }
            case 4:
            case 6:
            case 8: {
                this.setblack(this.S - n2, this.S - n2);
            }
            case 3: {
                this.setblack(n2 - 1, n2 - 1);
            }
            case 2: {
                this.setblack(n2 - 1, this.S - n2);
            }
            case 1: {
                this.setblack(this.S - n2, n2 - 1);
                break;
            }
        }
        this.MainColor = -1;
    }
    
    public void updateall() {
        if (this.ActiveImage == null) {
            return;
        }
        synchronized (this) {
            this.ActiveImage.getGraphics().drawImage(this.Empty, 0, 0, this);
        }
        for (int i = 0; i < this.S; ++i) {
            for (int j = 0; j < this.S; ++j) {
                this.update(i, j);
            }
        }
        this.showinformation();
    }
    
    public void updateboard() {
        final Image image = null;
        this.WhiteStone = image;
        this.BlackStone = image;
        this.EmptyShadow = null;
        this.setfonts();
        this.makeimages();
        this.updateall();
        this.copy();
    }
    
    public boolean search(final String s) {
        this.State = 1;
        this.getinformation();
        final TreeNode pos = this.Pos;
        boolean b = true;
    Label_0096:
        while (this.Pos.node().getaction("C").indexOf(s) < 0 || this.Pos == pos) {
            if (!this.Pos.haschildren()) {
                while (!this.hasvariation()) {
                    if (this.Pos.parent() == null) {
                        b = false;
                        break Label_0096;
                    }
                    this.goback();
                }
                this.tovarright();
            }
            else {
                this.goforward();
            }
        }
        this.showinformation();
        this.copy();
        return b;
    }
    
    Image getBoardImage() {
        return this.ActiveImage;
    }
    
    Dimension getBoardImageSize() {
        return new Dimension(this.ActiveImage.getWidth(this), this.ActiveImage.getHeight(this));
    }
    
    void movemouse(final int n, final int n2) {
        if (this.Pos.haschildren()) {
            return;
        }
        this.set(n, n2);
    }
    
    void setmouse(final int n, final int n2, final int n3) {
        this.set(n, n2, n3);
        this.undonode();
        this.setnode();
        this.showinformation();
    }
    
    void setmousec(final int n, final int n2, final int n3) {
        this.setc(n, n2, n3);
        this.undonode();
        this.setnode();
        this.showinformation();
    }
    
    void deletemouse(final int n, final int n2) {
        if (this.Pos.haschildren()) {
            return;
        }
        this.deletemousec(n, n2);
    }
    
    void deletemousec(final int n, final int n2) {
        this.delete(n, n2);
        this.undonode();
        this.setnode();
        this.showinformation();
    }
    
    void removemouse(final int n, final int n2) {
        if (this.Pos.haschildren()) {
            return;
        }
        this.removegroup(n, n2);
        this.undonode();
        this.setnode();
        this.showinformation();
    }
    
    static {
        Board.woodpaint = null;
    }
}
