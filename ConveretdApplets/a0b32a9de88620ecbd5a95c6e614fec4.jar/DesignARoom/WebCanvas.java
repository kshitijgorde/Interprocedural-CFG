// 
// Decompiled by Procyon v0.5.30
// 

package DesignARoom;

import java.awt.Dimension;
import java.io.StringBufferInputStream;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Enumeration;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Vector;
import java.awt.Canvas;

public class WebCanvas extends Canvas implements Runnable, Cloneable
{
    String name;
    public String oW;
    public String oH;
    public char wU;
    public char hU;
    public Vector furnVector;
    Vector furnVectorTemp;
    public Vector fNmVector;
    public Vector oWVector;
    public Vector oHVector;
    public Vector wUVector;
    public Vector hUVector;
    public Vector mFVector;
    public Vector furnVVector;
    int vIndex;
    public WebApplet wA;
    public boolean createNew;
    public boolean roomSized;
    public boolean spinning;
    Webfurn furnSpin;
    public int roomWidth;
    public int roomHeight;
    int height;
    int furnLeft;
    public int furnTop;
    int i;
    int k;
    public float multFactor;
    public Rectangle boundaryRect;
    int[] overLapTested;
    String Angle;
    public Thread t;
    public Thread tT;
    String animation;
    boolean rManimatRun;
    boolean fNanimatRun;
    Color animColor;
    String animText;
    int animYpos;
    int animSpeed;
    int[] charWidths;
    boolean exited;
    private int last_x;
    private int last_y;
    private int down_x;
    private int down_y;
    Rectangle prevRect;
    Rectangle newRect;
    String mode;
    public int degrees;
    Color offWhite;
    Color ltBlue;
    int fnLind;
    boolean gridOn;
    Color StorBordColor;
    public int apHeight;
    public int apWidth;
    public int apRmHeight;
    public int apDivHeight;
    public int apStTop;
    
    public WebCanvas(final WebApplet wa, final int apWidth, final int apHeight) {
        this.furnVector = new Vector();
        this.furnVectorTemp = new Vector();
        this.fNmVector = new Vector();
        this.oWVector = new Vector();
        this.oHVector = new Vector();
        this.wUVector = new Vector();
        this.hUVector = new Vector();
        this.mFVector = new Vector();
        this.furnVVector = new Vector();
        this.createNew = false;
        this.roomSized = false;
        this.height = 60;
        this.furnLeft = 2;
        this.k = 0;
        this.multFactor = 0.0f;
        this.overLapTested = new int[25];
        this.rManimatRun = false;
        this.fNanimatRun = false;
        this.exited = false;
        this.last_x = 0;
        this.last_y = 0;
        this.down_x = -1;
        this.down_y = -1;
        this.prevRect = new Rectangle();
        this.newRect = new Rectangle();
        this.mode = "new";
        this.degrees = 0;
        this.offWhite = new Color(225, 225, 225);
        this.ltBlue = new Color(12, 199, 191);
        this.fnLind = 0;
        this.gridOn = true;
        this.StorBordColor = Color.blue.darker();
        this.wA = wa;
        this.apHeight = apHeight;
        this.apWidth = apWidth;
        this.apRmHeight = this.apWidth;
        this.apDivHeight = 30;
        this.apStTop = this.apRmHeight + this.apDivHeight;
        this.boundaryRect = new Rectangle(0, this.apStTop, this.apWidth - 1, this.apHeight - this.apStTop - 1);
        Webfurn.receiveConstants(this.apHeight, this.apWidth, this.apRmHeight, this.apDivHeight, this.apStTop, this.wA);
        this.furnTop = this.apStTop + 2;
    }
    
    public WebCanvas() {
        this.furnVector = new Vector();
        this.furnVectorTemp = new Vector();
        this.fNmVector = new Vector();
        this.oWVector = new Vector();
        this.oHVector = new Vector();
        this.wUVector = new Vector();
        this.hUVector = new Vector();
        this.mFVector = new Vector();
        this.furnVVector = new Vector();
        this.createNew = false;
        this.roomSized = false;
        this.height = 60;
        this.furnLeft = 2;
        this.k = 0;
        this.multFactor = 0.0f;
        this.overLapTested = new int[25];
        this.rManimatRun = false;
        this.fNanimatRun = false;
        this.exited = false;
        this.last_x = 0;
        this.last_y = 0;
        this.down_x = -1;
        this.down_y = -1;
        this.prevRect = new Rectangle();
        this.newRect = new Rectangle();
        this.mode = "new";
        this.degrees = 0;
        this.offWhite = new Color(225, 225, 225);
        this.ltBlue = new Color(12, 199, 191);
        this.fnLind = 0;
        this.gridOn = true;
        this.StorBordColor = Color.blue.darker();
    }
    
    public void decideSave() {
    }
    
    public void updateOrigVectors(final int n) {
    }
    
    public void defineRoomSize(final String ow, final String oh, final char wu, final char hu, final String name) {
        if (!this.createNew) {
            this.roomSized = true;
            if (this.tT.isAlive()) {
                this.tT.stop();
            }
        }
        this.name = name;
        this.oW = ow;
        this.oH = oh;
        this.wU = wu;
        this.hU = hu;
        final float floatValue = Float.valueOf(ow);
        final float floatValue2 = Float.valueOf(oh);
        this.multFactor = this.apWidth / Math.max(floatValue, floatValue2);
        this.roomWidth = (int)(this.multFactor * floatValue);
        this.roomHeight = (int)(this.multFactor * floatValue2);
        this.repaint();
        this.fNmVector.addElement("");
        this.oWVector.addElement(this.oW);
        this.oHVector.addElement(this.oH);
        this.wUVector.addElement(new Character(this.wU));
        this.hUVector.addElement(new Character(this.hU));
        this.mFVector.addElement(Float.toString(this.multFactor));
        this.furnVVector.addElement(new Vector<Vector>());
        this.retrieveRoom(this.name, this.furnVVector.size() - 1);
    }
    
    public void retrieveRoom(final String name, final int vIndex) {
        if (!this.createNew) {
            this.roomSized = true;
        }
        if (this.roomSized && this.tT.isAlive()) {
            this.tT.stop();
        }
        this.vIndex = vIndex;
        this.name = name;
        this.wU = this.wUVector.elementAt(vIndex);
        this.hU = this.hUVector.elementAt(vIndex);
        this.oW = this.oWVector.elementAt(vIndex);
        this.oH = this.oHVector.elementAt(vIndex);
        this.multFactor = Float.valueOf(this.mFVector.elementAt(vIndex));
        this.furnVectorTemp = this.furnVVector.elementAt(vIndex);
        int i = 1;
        while (i != 0) {
            i = 0;
            final Enumeration<Webfurn> elements = (Enumeration<Webfurn>)this.furnVector.elements();
            while (elements.hasMoreElements()) {
                final Webfurn webfurn = elements.nextElement();
                webfurn.grabbed = false;
                webfurn.doubleClicked = false;
                if (webfurn.getTop() >= this.apStTop) {
                    i = 1;
                    this.furnVector.removeElement(webfurn);
                    this.furnVectorTemp.addElement(webfurn);
                }
            }
        }
        this.furnVector = this.furnVectorTemp;
        this.updateRoomSize(this.oW, this.oH, this.wU, this.hU);
    }
    
    public void updateRoomSize(final String ow, final String oh, final char wu, final char hu) {
        float n;
        if (!this.createNew) {
            final float floatValue = Float.valueOf(this.oW);
            final float floatValue2 = Float.valueOf(this.oH);
            this.roomWidth = (int)(this.multFactor * floatValue);
            this.roomHeight = (int)(this.multFactor * floatValue2);
            n = (this.apWidth + 2) / Math.max(this.roomWidth, this.roomHeight);
        }
        else {
            n = 1.0f;
        }
        this.oW = ow;
        this.oH = oh;
        this.wU = wu;
        this.hU = hu;
        final float floatValue3 = Float.valueOf(ow);
        final float floatValue4 = Float.valueOf(oh);
        this.multFactor = this.apWidth / Math.max(floatValue3, floatValue4);
        this.roomWidth = (int)(this.multFactor * floatValue3);
        this.roomHeight = (int)(this.multFactor * floatValue4);
        final Enumeration<Webfurn> elements = this.furnVector.elements();
        while (elements.hasMoreElements()) {
            final Webfurn webfurn = elements.nextElement();
            webfurn.setActualWidth((int)(this.multFactor * Float.valueOf(webfurn.oW)));
            webfurn.setActualHeight((int)(this.multFactor * Float.valueOf(webfurn.oH)));
            webfurn.figureWandH();
            webfurn.setLeft((int)(n * webfurn.getLeft()));
            webfurn.setTop((int)(n * webfurn.getTop()));
            webfurn.keepInBounds(this.roomWidth, this.roomHeight);
            webfurn.figurePolyPoints();
            webfurn.setFonts(this.getGraphics());
        }
        this.repaint();
        this.oWVector.setElementAt(this.oW, this.vIndex);
        this.oHVector.setElementAt(this.oH, this.vIndex);
        this.wUVector.setElementAt(new Character(this.wU), this.vIndex);
        this.hUVector.setElementAt(new Character(this.hU), this.vIndex);
        this.mFVector.setElementAt(Float.toString(this.multFactor), this.vIndex);
    }
    
    public char getWunit() {
        return this.wU;
    }
    
    public char getHunit() {
        return this.hU;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getWidth() {
        return this.oW;
    }
    
    public String getHeight() {
        return this.oH;
    }
    
    public Webfurn addFurniture(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final int furnLeft, final int furnTop) {
        final int furnLeft2 = this.furnLeft;
        final int furnTop2 = this.furnTop;
        this.furnLeft = furnLeft;
        this.furnTop = furnTop;
        final Webfurn addFurniture = this.addFurniture(s, s2, s3, s4, s5, s6, s7, s8);
        this.furnLeft = furnLeft2;
        this.furnTop = furnTop2;
        return addFurniture;
    }
    
    public Webfurn addFurniture(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8) {
        if (this.tT.isAlive()) {
            this.tT.stop();
        }
        final Graphics graphics = this.getGraphics();
        if (this.furnVector.isEmpty()) {
            this.repaint();
        }
        final int n = (int)(this.multFactor * Float.valueOf(s));
        final int n2 = (int)(this.multFactor * Float.valueOf(s2));
        if (this.furnLeft + n + 2 > this.apWidth) {
            this.furnLeft = 2;
            this.furnTop += (this.apHeight - this.apStTop - 5) / 3 + 2;
            if (this.furnTop >= this.apHeight - 10) {
                this.furnTop = this.apStTop + 7;
            }
        }
        final char char1 = this.toChar(s3);
        final char char2 = this.toChar(s4);
        final Color convertColor = this.convertColor(s6);
        final double n3 = Integer.valueOf(s8) * 2 * 3.141592653589793 / 360.0;
        Webfurn webfurn;
        if (s7.equals("Oval")) {
            webfurn = new WebOvalFurn(this.furnLeft, this.furnTop, n, n2, char1, char2, s5, convertColor, s7, n3, s, s2, s3, s4, s5, s6, s7, s8);
        }
        else if (s7.equals("Open Door")) {
            webfurn = new WebDoorFurn(this.furnLeft, this.furnTop, n, n2, char1, char2, s5, convertColor, s7, n3, s, s2, s3, s4, s5, s6, s7, s8);
        }
        else if (s7.equals("Window")) {
            webfurn = new WebWindowFurn(this.furnLeft, this.furnTop, n, n2, char1, char2, s5, convertColor, s7, n3, s, s2, s3, s4, s5, s6, s7, s8);
        }
        else {
            webfurn = new WebRectFurn(this.furnLeft, this.furnTop, n, n2, char1, char2, s5, convertColor, s7, n3, s, s2, s3, s4, s5, s6, s7, s8);
        }
        this.furnVector.addElement(webfurn);
        if (webfurn.keepInBounds(this.roomWidth, this.roomHeight)) {
            webfurn.figurePolyPoints();
        }
        webfurn.setFonts(graphics);
        webfurn.paint(graphics);
        if (this.furnOverlaps(webfurn, this.boundaryRect)) {
            graphics.setColor(this.StorBordColor);
            graphics.drawRect(0, this.apStTop, this.apWidth - 1, this.apHeight - this.apStTop - 1);
        }
        this.furnLeft += n + 2;
        graphics.dispose();
        return webfurn;
    }
    
    public boolean anyGrabbed() {
        final Enumeration<Webfurn> elements = this.furnVector.elements();
        while (elements.hasMoreElements()) {
            if (elements.nextElement().grabbed) {
                return true;
            }
        }
        return false;
    }
    
    public boolean anyDoubleClicked() {
        final Enumeration<Webfurn> elements = this.furnVector.elements();
        while (elements.hasMoreElements()) {
            if (elements.nextElement().doubleClicked) {
                return true;
            }
        }
        return false;
    }
    
    public Webfurn getGrabbedOne() {
        final Enumeration<Webfurn> elements = this.furnVector.elements();
        while (elements.hasMoreElements()) {
            final Webfurn webfurn = elements.nextElement();
            if (webfurn.grabbed) {
                return webfurn;
            }
        }
        return null;
    }
    
    public Webfurn getDoubleClickedOne() {
        final Enumeration<Webfurn> elements = this.furnVector.elements();
        while (elements.hasMoreElements()) {
            final Webfurn webfurn = elements.nextElement();
            if (webfurn.doubleClicked) {
                return webfurn;
            }
        }
        return null;
    }
    
    public void setWHNCS(final Webfurn webfurn, final String ow, final String oh, final String oWu, final String oHu, final String s, final String oc, final String s2) {
        Webfurn webfurn2 = webfurn;
        this.repaintTrail(webfurn2);
        webfurn2.setActualWidth((int)(this.multFactor * Float.valueOf(ow)));
        webfurn2.setActualHeight((int)(this.multFactor * Float.valueOf(oh)));
        webfurn2.setWunit(this.toChar(oWu));
        webfurn2.setHunit(this.toChar(oHu));
        webfurn2.setName(s);
        webfurn2.setColor(this.convertColor(oc));
        if (!s2.equals(webfurn2.getShape())) {
            webfurn2.setShape(s2);
            this.furnVector.removeElement(webfurn2);
            Webfurn webfurn3;
            if (s2.equals("Oval")) {
                webfurn3 = new WebOvalFurn(webfurn2.getActualLeft(), webfurn2.getActualTop(), webfurn2.getActualWidth(), webfurn2.getActualHeight(), webfurn2.getWunit(), webfurn2.getHunit(), webfurn2.getName(), webfurn2.getColor(), webfurn2.getShape(), 0.0, ow, oh, oWu, oHu, s, oc, s2, "0");
            }
            else if (s2.equals("Open Door")) {
                webfurn3 = new WebDoorFurn(webfurn2.getActualLeft(), webfurn2.getActualTop(), webfurn2.getActualWidth(), webfurn2.getActualHeight(), webfurn2.getWunit(), webfurn2.getHunit(), webfurn2.getName(), webfurn2.getColor(), webfurn2.getShape(), 0.0, ow, oh, oWu, oHu, s, oc, s2, "0");
            }
            else if (s2.equals("Window")) {
                webfurn3 = new WebWindowFurn(webfurn2.getActualLeft(), webfurn2.getActualTop(), webfurn2.getActualWidth(), webfurn2.getActualHeight(), webfurn2.getWunit(), webfurn2.getHunit(), webfurn2.getName(), webfurn2.getColor(), webfurn2.getShape(), 0.0, ow, oh, oWu, oHu, s, oc, s2, "0");
            }
            else {
                webfurn3 = new WebRectFurn(webfurn2.getActualLeft(), webfurn2.getActualTop(), webfurn2.getActualWidth(), webfurn2.getActualHeight(), webfurn2.getWunit(), webfurn2.getHunit(), webfurn2.getName(), webfurn2.getColor(), webfurn2.getShape(), webfurn2.getAngle(), ow, oh, oWu, oHu, s, oc, s2, webfurn2.oA);
            }
            webfurn2 = webfurn3;
            this.furnVector.addElement(webfurn2);
            webfurn2.grabbed = true;
        }
        webfurn2.figurePolyEnclosing();
        webfurn2.figurePolyPoints();
        webfurn2.setFonts(this.getGraphics());
        this.repaintNewPosition(webfurn2);
        webfurn2.oW = ow;
        webfurn2.oH = oh;
        webfurn2.oWu = oWu;
        webfurn2.oHu = oHu;
        webfurn2.oN = s;
        webfurn2.oC = oc;
        webfurn2.oS = s2;
    }
    
    public void setAngle(final Webfurn furnSpin, String s) {
        if (!s.equals("ContinuousCounter") && !s.equals("ContinuousClock") && !s.equals("Stop")) {
            this.repaintTrail(furnSpin);
            if (Integer.valueOf(s) > 89 || Integer.valueOf(s) < 0) {
                if (Integer.valueOf(s) > 89) {
                    s = Integer.toString(Integer.valueOf(s) - 90);
                    if (furnSpin instanceof WebDoorFurn) {
                        ((WebDoorFurn)furnSpin).shiftOpening("clock");
                    }
                }
                else {
                    s = Integer.toString(Integer.valueOf(s) + 90);
                    if (furnSpin instanceof WebDoorFurn) {
                        ((WebDoorFurn)furnSpin).shiftOpening("counter");
                    }
                }
                final int actualW = furnSpin.actualW;
                furnSpin.actualW = furnSpin.actualH;
                furnSpin.actualH = actualW;
                final String ow = furnSpin.oW;
                furnSpin.oW = furnSpin.oH;
                furnSpin.oH = ow;
                final String text = this.wA.fnWidth.getText();
                this.wA.fnWidth.setText(this.wA.fnLength.getText());
                this.wA.fnLength.setText(text);
                final String selectedItem = this.wA.fnWunit.getSelectedItem();
                this.wA.fnWunit.select(this.wA.fnLunit.getSelectedItem());
                this.wA.fnLunit.select(selectedItem);
            }
            furnSpin.setAngle(Integer.valueOf(s) * 2 * 3.141592653589793 / 360.0);
            furnSpin.figurePolyEnclosing();
            if (!furnSpin.spinning) {
                furnSpin.figurePolyPoints();
                furnSpin.setFonts(this.getGraphics());
            }
            this.repaintNewPosition(furnSpin);
            furnSpin.oA = s;
        }
        else {
            this.Angle = s;
            furnSpin.spinning = true;
            this.spinning = true;
            this.furnSpin = furnSpin;
            this.animation = "spin";
            (this.t = new Thread(this)).start();
        }
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        if (this.animation.equals("spin")) {
            this.spinFurniture();
        }
        if (this.animation.equals("type")) {
            this.typeText(this.animText, this.animColor, this.animYpos, this.animSpeed);
        }
        if (this.animation.equals("fade")) {
            this.fadeText(this.animText, this.animYpos, this.animSpeed);
        }
    }
    
    public void storeEverything() {
        this.furnLeft = 2;
        this.furnTop = this.apStTop + 2;
        final Enumeration<Webfurn> elements = this.furnVector.elements();
        while (elements.hasMoreElements()) {
            final Webfurn webfurn = elements.nextElement();
            final int width = webfurn.getWidth();
            if (this.furnLeft + width + 2 > this.apWidth) {
                this.furnLeft = 2;
                this.furnTop += (this.apHeight - this.apStTop - 5) / 3 + 2;
                if (this.furnTop >= this.apHeight - 10) {
                    this.furnTop = this.apStTop + 7;
                }
            }
            webfurn.setLeft(this.furnLeft);
            webfurn.setTop(this.furnTop);
            webfurn.figurePolyPoints();
            this.furnLeft += width + 2;
        }
        this.repaint();
    }
    
    public void clearStorage() {
        final Graphics graphics = this.getGraphics();
        boolean b;
        do {
            b = false;
            final Enumeration<Webfurn> elements = this.furnVector.elements();
            while (elements.hasMoreElements()) {
                final Webfurn webfurn = elements.nextElement();
                if (webfurn.getTop() >= this.apStTop) {
                    b = true;
                    this.furnVector.removeElement(webfurn);
                }
            }
        } while (b);
        graphics.setColor(Color.darkGray);
        graphics.fillRect(0, this.apStTop, this.apWidth, this.apHeight - this.apStTop);
        graphics.setColor(this.StorBordColor);
        graphics.drawRect(0, this.apStTop, this.apWidth - 1, this.apHeight - this.apStTop - 1);
        this.furnLeft = 2;
        this.furnTop = this.apStTop + 2;
        graphics.dispose();
    }
    
    public void deleteFurniture(final Webfurn webfurn) {
        final Graphics graphics = this.getGraphics();
        this.repaintTrail(webfurn);
        this.overLapTested[0] = -1;
        final Enumeration<Webfurn> elements = this.furnVector.elements();
        while (elements.hasMoreElements()) {
            final Webfurn webfurn2 = elements.nextElement();
            if (this.IntInArray(this.overLapTested, this.furnVector.indexOf(webfurn2))) {
                webfurn2.paint(graphics);
            }
        }
        this.furnVector.removeElement(webfurn);
        graphics.dispose();
    }
    
    void drawGrid(final Graphics graphics) {
        if (this.gridOn) {
            graphics.setColor(Color.lightGray);
            double n = 0.0;
            switch (this.wU) {
                case 'f': {
                    n = this.multFactor;
                    break;
                }
                case 'i': {
                    n = this.multFactor / 12.0;
                    break;
                }
                case 'm': {
                    n = this.multFactor * 39.37 / 12.0;
                    break;
                }
            }
            for (int n2 = 1; n2 <= (this.roomWidth - 1) / n; ++n2) {
                graphics.drawLine((int)(n * n2), 0, (int)(n * n2), this.roomHeight - 1);
            }
            switch (this.hU) {
                case 'f': {
                    n = this.multFactor;
                    break;
                }
                case 'i': {
                    n = this.multFactor / 12.0;
                    break;
                }
                case 'm': {
                    n = this.multFactor * 39.37 / 12.0;
                    break;
                }
            }
            for (int n3 = 1; n3 <= (this.roomHeight - 1) / n; ++n3) {
                graphics.drawLine(0, (int)(n * n3), this.roomWidth - 1, (int)(n * n3));
            }
        }
    }
    
    void drawGridp(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (this.gridOn && n2 < this.roomHeight) {
            graphics.setColor(Color.lightGray);
            double n5 = 0.0;
            switch (this.wU) {
                case 'f': {
                    n5 = this.multFactor;
                    break;
                }
                case 'i': {
                    n5 = this.multFactor / 12.0;
                    break;
                }
                case 'm': {
                    n5 = this.multFactor * 39.37 / 12.0;
                    break;
                }
            }
            for (int n6 = 1; n6 <= (this.roomWidth - 1) / n5; ++n6) {
                final int n7 = (int)(n5 * n6);
                if (n7 >= n & n7 <= n + n3) {
                    graphics.drawLine(n7, n2, n7, Math.min(n2 + n4, this.roomHeight - 1));
                }
            }
            switch (this.hU) {
                case 'f': {
                    n5 = this.multFactor;
                    break;
                }
                case 'i': {
                    n5 = this.multFactor / 12.0;
                    break;
                }
                case 'm': {
                    n5 = this.multFactor * 39.37 / 12.0;
                    break;
                }
            }
            for (int n8 = 1; n8 <= (this.roomHeight - 1) / n5; ++n8) {
                final int n9 = (int)(n5 * n8);
                if (n9 >= n2 & n9 <= n2 + n4) {
                    graphics.drawLine(n, n9, Math.min(n + n3, this.roomWidth - 1), n9);
                }
            }
        }
    }
    
    void setGridOn(final boolean gridOn) {
        this.gridOn = gridOn;
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this.apWidth, this.apRmHeight);
        if (!this.createNew) {
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, this.roomWidth, this.roomHeight);
            this.drawGrid(graphics);
        }
        graphics.setColor(Color.black);
        graphics.fillRect(0, this.apRmHeight, this.apWidth, this.apDivHeight);
        final Font font = graphics.getFont();
        graphics.setFont(new Font("Dialog", 0, 19));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.setColor(Color.gray.brighter().brighter());
        graphics.drawString("Furniture Storage", this.apWidth / 2 - fontMetrics.stringWidth("Furniture Storage") / 2, this.apStTop - 5);
        graphics.setFont(font);
        graphics.setColor(this.StorBordColor);
        graphics.drawRect(0, this.apStTop - 23, this.apWidth - 1, 22);
        graphics.setColor(Color.darkGray);
        graphics.fillRect(0, this.apStTop, this.apWidth, this.apHeight - this.apStTop);
        final Enumeration<Webfurn> elements = (Enumeration<Webfurn>)this.furnVector.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().paint(graphics);
        }
        graphics.setColor(this.StorBordColor);
        graphics.drawRect(0, this.apStTop, this.apWidth - 1, this.apHeight - this.apStTop - 1);
        if (!this.roomSized) {
            if (!this.rManimatRun) {
                this.rManimatRun = true;
                this.animation = "fade";
                this.animText = "Welcome to\nDesign-A-Room!!";
                this.animYpos = 20;
                this.animSpeed = 50;
                (this.tT = new Thread(this)).start();
            }
            else {
                this.animation = "";
                this.animText = "Welcome to\nDesign-A-Room!!";
                this.animColor = new Color(255, 255, 0);
                this.animYpos = 20;
                this.animYpos = this.plainText(this.animText, this.animColor, this.animYpos, 30);
                this.animText = "\nStart by creating a room\nwith the top left panel.";
                this.animColor = Color.lightGray.brighter();
                this.plainText(this.animText, this.animColor, this.animYpos, 24);
            }
        }
        else if (this.createNew) {
            this.animText = "Create your next room\nwith the top left panel.";
            this.animColor = Color.red;
            this.animYpos = 20;
            this.animYpos = this.plainText(this.animText, this.animColor, this.animYpos, 24);
        }
        else if (this.furnVector.isEmpty()) {
            if (!this.fNanimatRun) {
                this.fNanimatRun = true;
                this.animation = "type";
                this.animColor = Color.lightGray.brighter();
                this.animText = "Now create furniture\nwith the middle panel.";
                this.animYpos = this.apStTop + (this.apHeight - this.apStTop) / 2 - 10;
                this.animSpeed = 50;
                (this.tT = new Thread(this)).start();
            }
            else {
                this.animText = "Now create furniture\nwith the middle panel.";
                this.animColor = Color.lightGray.brighter();
                this.animYpos = this.apStTop + (this.apHeight - this.apStTop) / 2 - 10;
                this.plainText(this.animText, this.animColor, this.animYpos, 24);
            }
        }
    }
    
    public boolean mouseDown(final Event event, final int down_x, final int down_y) {
        Webfurn webfurn = new WebRectFurn();
        Webfurn webfurn2 = new WebRectFurn();
        this.exited = false;
        int n = 0;
        if (this.spinning) {
            this.wA.stopSpinning();
        }
        final Enumeration<Webfurn> elements = (Enumeration<Webfurn>)this.furnVector.elements();
        while (elements.hasMoreElements()) {
            final Webfurn webfurn3 = elements.nextElement();
            webfurn3.mouseDown(event, down_x, down_y);
            if (webfurn3.wasGrabbed) {
                webfurn2 = webfurn3;
            }
            if (webfurn3.grabbed) {
                if (n != 0) {
                    webfurn.grabbed = false;
                    webfurn.doubleClicked = false;
                }
                n = 1;
                webfurn = webfurn3;
            }
        }
        if ((webfurn.grabbed || webfurn2.wasGrabbed) && (!webfurn.wasGrabbed || (webfurn.doubleClicked ^ webfurn.wasDoubleClicked))) {
            final Graphics graphics = this.getGraphics();
            if (webfurn != webfurn2) {
                if (webfurn2.wasGrabbed) {
                    this.repaintTrail(webfurn2);
                    this.repaintNewPosition(webfurn2);
                }
                if (webfurn.grabbed) {
                    this.furnVector.removeElement(webfurn);
                    this.furnVector.addElement(webfurn);
                }
            }
            if (webfurn.grabbed) {
                webfurn.paint(graphics);
            }
            if (this.furnOverlaps(webfurn, this.boundaryRect) || this.furnOverlaps(webfurn2, this.boundaryRect)) {
                graphics.setColor(this.StorBordColor);
                graphics.drawRect(0, this.apStTop, this.apWidth - 1, this.apHeight - this.apStTop - 1);
            }
            graphics.dispose();
        }
        if (webfurn.grabbed) {
            this.mode = "update";
            final char wunit = webfurn.getWunit();
            final char hunit = webfurn.getHunit();
            final String fromFeet = this.wA.fromFeet(webfurn.oW, wunit);
            final String fromFeet2 = this.wA.fromFeet(webfurn.oH, hunit);
            if (this.wA.fnLength.getText() != fromFeet2) {
                this.wA.fnLength.setText(fromFeet2);
            }
            if (this.wA.fnWidth.getText() != fromFeet) {
                this.wA.fnWidth.setText(fromFeet);
            }
            if (this.wA.fnWunit.getSelectedItem() != webfurn.oWu) {
                this.wA.fnWunit.select(webfurn.oWu);
            }
            if (this.wA.fnLunit.getSelectedItem() != webfurn.oHu) {
                this.wA.fnLunit.select(webfurn.oHu);
            }
            if (this.wA.fnLabel.getText() != webfurn.oN) {
                this.wA.fnLabel.setText(webfurn.oN);
            }
            if (this.wA.fnColor.getSelectedItem() != webfurn.oC) {
                this.wA.fnColor.select(webfurn.oC);
            }
            if (this.wA.fnShape.getSelectedItem() != webfurn.oS) {
                this.wA.fnShape.select(webfurn.oS);
            }
            this.wA.fnButton.disable();
            this.wA.clearButton.enable();
            this.wA.copyButton.enable();
            this.wA.deleteButton.enable();
        }
        else {
            this.wA.setNewMode();
        }
        if (n == 0) {
            this.last_x = Math.min(this.roomWidth, down_x);
            this.last_y = Math.min(this.roomHeight - 1, down_y);
            if (down_y < this.roomHeight - 1 || down_y > this.apStTop) {
                this.down_y = down_y;
                this.down_x = down_x;
            }
            else {
                final int n2 = -1;
                this.down_x = n2;
                this.down_y = n2;
            }
            this.prevRect = new Rectangle();
            this.wA.rDir.disable();
            this.wA.rAmt.disable();
            this.wA.rButton.disable();
            this.wA.rDir.select(0);
            this.wA.rAmt.select(2);
        }
        else {
            if (webfurn instanceof WebOvalFurn || webfurn instanceof WebWindowFurn) {
                this.wA.rDir.disable();
            }
            else {
                this.wA.rDir.enable();
            }
            if (webfurn instanceof WebRectFurn) {
                this.wA.rAmt.enable();
            }
            else {
                this.wA.rAmt.select(2);
                this.wA.rAmt.disable();
            }
            this.wA.rButton.enable();
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.mouseUp(event, n, n2);
        return this.exited = true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        final Graphics graphics = this.getGraphics();
        if (this.prevRect.width != 0 || this.prevRect.height != 0) {
            if (this.prevRect.y < this.roomHeight) {
                graphics.setColor(Color.white);
            }
            else {
                graphics.setColor(Color.darkGray);
            }
            graphics.drawRect(this.prevRect.x, this.prevRect.y, this.prevRect.width, this.prevRect.height);
            this.drawGridp(graphics, this.prevRect.x, this.prevRect.y, this.prevRect.width, this.prevRect.height);
        }
        if (this.newRect.width > 3 && this.newRect.height > 3) {
            this.repaintNewPosition(this.newRect);
            if (this.wA.fnShape.getSelectedItem().equals("Open Door") || this.wA.fnShape.getSelectedItem().equals("Window")) {
                this.wA.fnShape.select(0);
            }
            final String text = this.wA.fnLabel.getText();
            if ((text.length() == 0 || text.length() >= 4) && (text.length() == 0 || text.substring(0, 4).equals("Furn"))) {
                if (++this.fnLind == 1) {
                    this.wA.fnLabel.setText("Furn");
                }
                else {
                    this.wA.fnLabel.setText(String.valueOf(String.valueOf("Furn (").concat(String.valueOf(this.fnLind))).concat(String.valueOf(")")));
                }
            }
            this.repaintNewPosition(this.addFurniture(this.wA.toFeet(this.wA.fnWidth.getText(), this.wA.fnWunit.getSelectedItem()), this.wA.toFeet(this.wA.fnLength.getText(), this.wA.fnLunit.getSelectedItem()), this.wA.fnWunit.getSelectedItem(), this.wA.fnLunit.getSelectedItem(), this.wA.fnLabel.getText(), this.wA.fnColor.getSelectedItem(), this.wA.fnShape.getSelectedItem(), "0", this.newRect.x, this.newRect.y));
            this.mouseDown(event, this.newRect.x + 1, this.newRect.y + 1);
            this.mode = "update";
            this.wA.fnButton.disable();
            this.wA.clearButton.enable();
            this.wA.copyButton.enable();
            this.wA.deleteButton.enable();
            this.wA.fnLabel.selectAll();
            this.wA.fnLabel.requestFocus();
            this.decideSave();
        }
        else if (this.newRect.width > 0 || this.newRect.height > 0) {
            this.wA.clearFurnValues();
        }
        this.prevRect = new Rectangle();
        this.newRect = new Rectangle();
        graphics.dispose();
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        boolean b = false;
        if (!this.exited && !this.createNew) {
            final Enumeration<Webfurn> elements = (Enumeration<Webfurn>)this.furnVector.elements();
            while (elements.hasMoreElements()) {
                final Webfurn webfurn = elements.nextElement();
                if (webfurn.grabbed) {
                    b = true;
                    this.repaintTrail(webfurn);
                    webfurn.mouseDrag(event, n, n2);
                    this.repaintNewPosition(webfurn);
                    this.decideSave();
                    break;
                }
            }
            if (!b) {
                if (event.shiftDown()) {
                    if (n2 < this.roomHeight && n < this.roomWidth) {
                        final Graphics graphics = this.getGraphics();
                        if (!this.furnVector.isEmpty()) {
                            graphics.setColor(this.furnVector.lastElement().getColor());
                        }
                        else {
                            graphics.setColor(Color.black);
                        }
                        graphics.drawLine(this.last_x, this.last_y, n, n2);
                        graphics.dispose();
                    }
                    this.last_x = Math.min(this.roomWidth, n);
                    this.last_y = Math.min(this.roomHeight, n2);
                }
                else if (this.down_x != -1 && n > 0 && n2 > 0 && ((n2 < this.roomHeight - 1 && n < this.roomWidth - 1) || (n2 > this.apStTop && n < this.apWidth - 1))) {
                    this.wA.fnWunit.select(0);
                    this.wA.fnLunit.select(0);
                    final Graphics graphics2 = this.getGraphics();
                    this.repaintTrail(this.prevRect);
                    if (n2 < this.roomHeight) {
                        graphics2.setColor(Color.white);
                    }
                    else {
                        graphics2.setColor(Color.darkGray);
                    }
                    graphics2.drawRect(this.prevRect.x, this.prevRect.y, this.prevRect.width, this.prevRect.height);
                    this.drawGridp(graphics2, this.prevRect.x, this.prevRect.y, this.prevRect.width, this.prevRect.height);
                    graphics2.setColor(Color.black);
                    if (n > this.down_x) {
                        if (n2 > this.down_y) {
                            this.newRect = new Rectangle(this.down_x, this.down_y, n - this.down_x, n2 - this.down_y);
                        }
                        else {
                            this.newRect = new Rectangle(this.down_x, n2, n - this.down_x, this.down_y - n2);
                        }
                    }
                    else if (n2 > this.down_y) {
                        this.newRect = new Rectangle(n, this.down_y, this.down_x - n, n2 - this.down_y);
                    }
                    else {
                        this.newRect = new Rectangle(n, n2, this.down_x - n, this.down_y - n2);
                    }
                    graphics2.drawRect(this.newRect.x, this.newRect.y, this.newRect.width, this.newRect.height);
                    this.repaintNewPosition(this.newRect);
                    final String string = Float.toString(this.newRect.width / this.multFactor);
                    final String string2 = Float.toString(this.newRect.height / this.multFactor);
                    this.wA.fnWidth.setText(string.substring(0, Math.min(4, string.length())));
                    this.wA.fnLength.setText(string2.substring(0, Math.min(4, string2.length())));
                    graphics2.dispose();
                    this.prevRect = this.newRect;
                }
                else {
                    final int n3 = -1;
                    this.down_y = n3;
                    this.down_x = n3;
                    this.mouseUp(event, n, n2);
                }
            }
        }
        return true;
    }
    
    public static boolean altDown(final Event event) {
        return (event.modifiers & 0x8) != 0x0;
    }
    
    public boolean keyDown(final Event event, final int n) {
        final char c = (char)n;
        if (this.anyGrabbed()) {
            final Webfurn grabbedOne = this.getGrabbedOne();
            if (altDown(event)) {
                switch (c) {
                    case 120: {
                        this.deleteFurniture(grabbedOne);
                        return true;
                    }
                    case 88: {
                        this.deleteFurniture(grabbedOne);
                        return true;
                    }
                    case 100: {
                        this.addFurniture(grabbedOne.oW, grabbedOne.oH, grabbedOne.oWu, grabbedOne.oHu, grabbedOne.oN, grabbedOne.oC, grabbedOne.oS, grabbedOne.oA);
                        return true;
                    }
                    case 68: {
                        this.addFurniture(grabbedOne.oW, grabbedOne.oH, grabbedOne.oWu, grabbedOne.oHu, grabbedOne.oN, grabbedOne.oC, grabbedOne.oS, grabbedOne.oA);
                        return true;
                    }
                    case 114: {
                        this.setAngle(grabbedOne, "90");
                        return true;
                    }
                    case 82: {
                        this.setAngle(grabbedOne, "90");
                        return true;
                    }
                }
            }
            switch (n) {
                case 1006: {
                    this.moveFurn(grabbedOne, -1, 0);
                    return true;
                }
                case 1007: {
                    this.moveFurn(grabbedOne, 1, 0);
                    return true;
                }
                case 1004: {
                    this.moveFurn(grabbedOne, 0, -1);
                    return true;
                }
                case 1005: {
                    this.moveFurn(grabbedOne, 0, 1);
                    return true;
                }
            }
        }
        return false;
    }
    
    void moveFurn(final Webfurn webfurn, final int n, final int n2) {
        this.repaintTrail(webfurn);
        webfurn.setLeft(webfurn.getLeft() + n);
        webfurn.setTop(webfurn.getTop() + n2);
        this.repaintNewPosition(webfurn);
        this.decideSave();
    }
    
    private void repaintTrail(final Rectangle rectangle) {
        final Graphics graphics = this.getGraphics();
        for (int i = 0; i < this.overLapTested.length; ++i) {
            this.overLapTested[i] = -1;
        }
        this.k = 0;
        this.overLapTest(rectangle, graphics);
        graphics.dispose();
    }
    
    public void repaintNewPosition(final Rectangle rectangle) {
        final Graphics graphics = this.getGraphics();
        final Enumeration<Webfurn> elements = this.furnVector.elements();
        while (elements.hasMoreElements()) {
            final Webfurn webfurn = elements.nextElement();
            if (this.IntInArray(this.overLapTested, this.furnVector.indexOf(webfurn))) {
                webfurn.paint(graphics);
            }
        }
        graphics.dispose();
    }
    
    private void overLapTest(final Rectangle rectangle, final Graphics graphics) {
        final Enumeration<Webfurn> elements = this.furnVector.elements();
        while (elements.hasMoreElements()) {
            final Webfurn webfurn = elements.nextElement();
            if (this.furnOverlaps(webfurn, rectangle)) {
                this.overLapTest(webfurn, graphics);
            }
        }
    }
    
    private void repaintTrail(final Webfurn webfurn) {
        final Graphics graphics = this.getGraphics();
        webfurn.unPaint(graphics);
        this.drawGridp(graphics, webfurn.getLeft(), webfurn.getTop(), webfurn.getWidth(), webfurn.getHeight());
        if (this.furnOverlaps(webfurn, this.boundaryRect)) {
            graphics.setColor(this.StorBordColor);
            graphics.drawRect(0, this.apStTop, this.apWidth - 1, this.apHeight - this.apStTop - 1);
        }
        for (int i = 0; i < this.overLapTested.length; ++i) {
            this.overLapTested[i] = -1;
        }
        this.k = 0;
        this.overLapTest(webfurn, graphics);
        graphics.dispose();
    }
    
    public void repaintNewPosition(final Webfurn webfurn) {
        final Graphics graphics = this.getGraphics();
        webfurn.keepInBounds(this.roomWidth, this.roomHeight);
        webfurn.figurePolyPoints();
        final Enumeration<Webfurn> elements = this.furnVector.elements();
        while (elements.hasMoreElements()) {
            final Webfurn webfurn2 = elements.nextElement();
            if (this.IntInArray(this.overLapTested, this.furnVector.indexOf(webfurn2))) {
                webfurn2.paint(graphics);
            }
        }
        if (this.furnOverlaps(webfurn, this.boundaryRect)) {
            graphics.setColor(this.StorBordColor);
            graphics.drawRect(0, this.apStTop, this.apWidth - 1, this.apHeight - this.apStTop - 1);
        }
        graphics.dispose();
    }
    
    private void overLapTest(final Webfurn webfurn, final Graphics graphics) {
        this.overLapTested[this.k++] = this.furnVector.indexOf(webfurn);
        final Enumeration<Webfurn> elements = this.furnVector.elements();
        while (elements.hasMoreElements()) {
            final Webfurn webfurn2 = elements.nextElement();
            if (!this.IntInArray(this.overLapTested, this.furnVector.indexOf(webfurn2)) && this.furnOverlaps(webfurn, webfurn2)) {
                this.overLapTest(webfurn2, graphics);
            }
        }
    }
    
    private boolean furnOverlaps(final Webfurn webfurn, final Webfurn webfurn2) {
        return this.furnOverlaps(webfurn, new Rectangle(webfurn2.getLeft(), webfurn2.getTop(), webfurn2.getWidth(), webfurn2.getHeight()));
    }
    
    private boolean furnOverlaps(final Webfurn webfurn, final Rectangle rectangle) {
        final int left = webfurn.getLeft();
        final int top = webfurn.getTop();
        final int width = webfurn.getWidth();
        final int height = webfurn.getHeight();
        final int x = rectangle.x;
        final int y = rectangle.y;
        final int width2 = rectangle.width;
        final int height2 = rectangle.height;
        return (left < x + width2 & (left > x || left + width > x)) && (top < y + height2 & (top > y || top + height > y));
    }
    
    private Color convertColor(final String s) {
        Color color = Color.black;
        if (s.equals("Black")) {
            color = Color.black;
        }
        else if (s.equals("Brown")) {
            color = new Color(103, 62, 15);
        }
        else if (s.equals("Tan")) {
            color = new Color(187, 131, 23);
        }
        else if (s.equals("Red")) {
            color = new Color(202, 9, 20);
        }
        else if (s.equals("Orange")) {
            color = new Color(240, 118, 16);
        }
        else if (s.equals("Yellow")) {
            color = new Color(246, 254, 12);
        }
        else if (s.equals("Green")) {
            color = new Color(32, 198, 12);
        }
        else if (s.equals("Blue")) {
            color = new Color(10, 17, 200);
        }
        else if (s.equals("Light Blue")) {
            color = this.ltBlue;
        }
        else if (s.equals("Purple")) {
            color = new Color(147, 8, 203);
        }
        return color;
    }
    
    void doRotate() {
        final Webfurn grabbedOne = this.getGrabbedOne();
        if (this.spinning) {
            this.setAngle(grabbedOne, "Stop");
        }
        else {
            if (this.wA.rAmt.getSelectedIndex() == 0) {
                this.degrees = 1;
            }
            if (this.wA.rAmt.getSelectedIndex() == 1) {
                this.degrees = 45;
            }
            if (this.wA.rAmt.getSelectedIndex() == 2) {
                this.degrees = 90;
            }
            if (this.wA.rAmt.getSelectedIndex() == 3) {
                this.degrees = 0;
            }
            if (this.wA.rAmt.getSelectedIndex() == 3) {
                this.spinning = true;
                if (this.wA.rDir.getSelectedItem().equals("Clockwise")) {
                    this.setAngle(grabbedOne, "ContinuousClock");
                }
                else {
                    this.setAngle(grabbedOne, "ContinuousCounter");
                }
            }
            else if (this.wA.rAmt.getSelectedIndex() == 4) {
                this.setAngle(grabbedOne, "0");
            }
            else {
                if (this.wA.rDir.getSelectedItem().equals("Counter")) {
                    this.degrees *= -1;
                }
                this.setAngle(grabbedOne, Integer.toString(Integer.valueOf(grabbedOne.oA) + this.degrees));
            }
        }
    }
    
    void spinFurniture() {
        while (this.spinning) {
            if (this.Angle.equals("ContinuousClock")) {
                final int n = Integer.valueOf(this.furnSpin.oA) + 1;
                this.i = n;
                while (this.i <= 90 && this.spinning) {
                    this.setAngle(this.furnSpin, Integer.toString(this.i));
                    try {
                        Thread.currentThread();
                        Thread.sleep(85L);
                    }
                    catch (Exception ex) {}
                    ++this.i;
                }
                this.i = 1;
                while (this.i < n && this.spinning) {
                    this.setAngle(this.furnSpin, Integer.toString(this.i));
                    try {
                        Thread.currentThread();
                        Thread.sleep(85L);
                    }
                    catch (Exception ex2) {}
                    ++this.i;
                }
                this.i = n;
                while (this.i <= 90 && this.spinning) {
                    this.setAngle(this.furnSpin, Integer.toString(this.i));
                    try {
                        Thread.currentThread();
                        Thread.sleep(85L);
                    }
                    catch (Exception ex3) {}
                    ++this.i;
                }
                this.i = 1;
                while (this.i < n - 1 && this.spinning) {
                    this.setAngle(this.furnSpin, Integer.toString(this.i));
                    try {
                        Thread.currentThread();
                        Thread.sleep(85L);
                    }
                    catch (Exception ex4) {}
                    ++this.i;
                }
            }
            else {
                final int n2 = Integer.valueOf(this.furnSpin.oA) - 1;
                this.i = n2;
                while (this.i >= -1 && this.spinning) {
                    this.setAngle(this.furnSpin, Integer.toString(this.i));
                    try {
                        Thread.currentThread();
                        Thread.sleep(85L);
                    }
                    catch (Exception ex5) {}
                    --this.i;
                }
                this.i = 89;
                while (this.i > n2 && this.spinning) {
                    this.setAngle(this.furnSpin, Integer.toString(this.i));
                    try {
                        Thread.currentThread();
                        Thread.sleep(85L);
                    }
                    catch (Exception ex6) {}
                    --this.i;
                }
                this.i = n2;
                while (this.i >= -1 && this.spinning) {
                    this.setAngle(this.furnSpin, Integer.toString(this.i));
                    try {
                        Thread.currentThread();
                        Thread.sleep(85L);
                    }
                    catch (Exception ex7) {}
                    --this.i;
                }
                this.i = 89;
                while (this.i > n2 + 1 && this.spinning) {
                    this.setAngle(this.furnSpin, Integer.toString(this.i));
                    try {
                        Thread.currentThread();
                        Thread.sleep(85L);
                    }
                    catch (Exception ex8) {}
                    --this.i;
                }
            }
        }
        this.furnSpin.spinning = false;
        this.furnSpin.setFonts(this.getGraphics());
        if (this.furnVector.contains(this.furnSpin)) {
            this.repaintNewPosition(this.furnSpin);
        }
        this.decideSave();
    }
    
    void fadeText(final String s, final int n, final int n2) {
        final Graphics graphics = this.getGraphics();
        graphics.setFont(new Font("TimesRoman", 0, 1));
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int n3 = 0;
        for (int i = 1; i < 31; ++i) {
            graphics.setColor(Color.black);
            int n4 = n;
            int j = 0;
            int n5 = 0;
            while (j != -1) {
                j = s.indexOf("\n", n5);
                final String s2 = (j == -1) ? s.substring(n5) : s.substring(n5, j);
                n5 = j + 1;
                graphics.drawString(s2, this.apWidth / 2 - fontMetrics.stringWidth(s2) / 2, n4);
                n4 += fontMetrics.getHeight();
            }
            graphics.setFont(new Font("TimesRoman", 0, i));
            fontMetrics = graphics.getFontMetrics();
            graphics.setColor(new Color(8 * i + 15, 8 * i + 15, 0));
            n3 = n;
            int k = 0;
            int n6 = 0;
            while (k != -1) {
                k = s.indexOf("\n", n6);
                final String s3 = (k == -1) ? s.substring(n6) : s.substring(n6, k);
                n6 = k + 1;
                graphics.drawString(s3, this.apWidth / 2 - fontMetrics.stringWidth(s3) / 2, n3);
                n3 += fontMetrics.getHeight();
            }
            try {
                Thread.currentThread();
                Thread.sleep(n2);
            }
            catch (InterruptedException ex) {}
        }
        this.typeText("\nStart by creating a room\nwith the top left panel.", Color.lightGray.brighter(), n3, 50);
        graphics.dispose();
    }
    
    void typeText(final String s, final Color color, final int n, final int n2) {
        final Graphics graphics = this.getGraphics();
        graphics.setFont(new Font("TimesRoman", 0, 24));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.setColor(color);
        final StringBufferInputStream stringBufferInputStream = new StringBufferInputStream(s);
        final char[] array = { '\0' };
        stringBufferInputStream.reset();
        if (this.charWidths == null) {
            this.charWidths = fontMetrics.getWidths();
        }
        final int index = s.indexOf("\n", 0);
        final String s2 = (index == -1) ? s : s.substring(0, index);
        int n3 = index + 1;
        int n4 = this.apWidth / 2 - fontMetrics.stringWidth(s2) / 2;
        int n5 = n;
        int read;
        while ((read = stringBufferInputStream.read()) != -1) {
            if (read == 10) {
                final int index2 = s.indexOf("\n", n3);
                final String s3 = (index2 == -1) ? s.substring(n3) : s.substring(n3, index2);
                n3 = index2 + 1;
                n4 = this.apWidth / 2 - fontMetrics.stringWidth(s3) / 2;
                n5 += fontMetrics.getHeight();
            }
            else {
                array[0] = (char)read;
                graphics.drawChars(array, 0, 1, n4, n5);
                n4 += this.charWidths[read];
                try {
                    Thread.currentThread();
                    Thread.sleep(n2);
                }
                catch (InterruptedException ex) {}
            }
        }
        graphics.dispose();
    }
    
    int plainText(final String s, final Color color, final int n, final int n2) {
        final Graphics graphics = this.getGraphics();
        graphics.setFont(new Font("TimesRoman", 0, n2));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.setColor(color);
        int n3 = n;
        int i = 0;
        int n4 = 0;
        while (i != -1) {
            i = s.indexOf("\n", n4);
            final String s2 = (i == -1) ? s.substring(n4) : s.substring(n4, i);
            n4 = i + 1;
            graphics.drawString(s2, this.apWidth / 2 - fontMetrics.stringWidth(s2) / 2, n3);
            n3 += fontMetrics.getHeight();
        }
        graphics.dispose();
        return n3;
    }
    
    char toChar(final String s) {
        char c = ' ';
        if (s.equals("Feet")) {
            c = 'f';
        }
        if (s.equals("Inches")) {
            c = 'i';
        }
        if (s.equals("Meters")) {
            c = 'm';
        }
        return c;
    }
    
    boolean IntInArray(final int[] array, final int n) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == n) {
                return true;
            }
        }
        return false;
    }
    
    public synchronized Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public Dimension minimumSize() {
        return new Dimension(this.apWidth, this.apHeight);
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
}
