import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Rectangle;
import java.util.Vector;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class b extends Canvas implements Runnable
{
    private static final long serialVersionUID = 1L;
    private Point pntMousePoint;
    private Image imgBackGround;
    private Dimension dimImageDimension;
    private Graphics objGrapBack;
    private Color objBackColor;
    private int intRecSize;
    private bse_ticker objBseTicker;
    private int intItemNum;
    private int intMPosition;
    private Dimension objBSEDimension;
    c objCObject;
    c q;
    Vector vecScripData;
    Vector vecScripData1;
    int intIncrementor;
    Rectangle objRectangle;
    int f;
    int d;
    boolean a;
    int i;
    int _fldtry;
    Point pntMouseClickedPoint;
    int intVar;
    int r;
    int _flddo;
    protected int _fldbyte;
    protected int p;
    protected int n;
    public boolean _fldcase;
    public int u;
    
    public b(final bse_ticker objBseTicker) {
        this._fldtry = 1;
        this._fldcase = false;
        this.f = 1;
        this.u = 40;
        this.a = false;
        this.i = 0;
        this.intItemNum = -1;
        this.intMPosition = 0;
        this.d = 0;
        this.objBseTicker = objBseTicker;
        this.objBSEDimension = new Dimension();
        this.vecScripData = new Vector();
        this.vecScripData1 = new Vector();
        this.q = new c();
        this.objBackColor = new Color(100, 151, 161);
        this.pntMousePoint = new Point(0, 0);
        if (this.objBseTicker._mthif()) {
            this.r = this.objBseTicker.c[6]._fldif.width;
            return;
        }
        this.r = 0;
    }
    
    public void _mthfor() {
        this.vecScripData.removeAllElements();
        this.vecScripData = (Vector)this.vecScripData1.clone();
    }
    
    public synchronized void a(final c c) {
        if (this.intMPosition < this.vecScripData1.size()) {
            this.objBSEDimension.width -= this.vecScripData1.elementAt(this.intMPosition)._fldif.width;
            this.objCObject = this.vecScripData1.elementAt(this.intMPosition);
            this.vecScripData1.setElementAt(c, this.intMPosition);
        }
        else {
            this.vecScripData1.addElement(c);
        }
        this.objBSEDimension.width += c._fldif.width;
        ++this.intMPosition;
    }
    
    public void _mthtry() {
        --this.i;
        this.a = false;
    }
    
    public c _mthdo() {
        final int size = this.vecScripData.size();
        if (size > 0) {
            ++this.intItemNum;
            if (this.intItemNum >= size) {
                this.intItemNum = 0;
            }
            return this.vecScripData.elementAt(this.intItemNum);
        }
        return null;
    }
    
    public void _mthif() {
        this.objRectangle = this.bounds();
        this.p = 0;
        this._fldbyte = 0;
    }
    
    public boolean _mthint() {
        return this.i != 0;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (n >= 0 && n <= this.r && this.objBseTicker._mthif()) {
            if (n2 > 0 && n2 < this.objBseTicker.c[6]._fldif.height) {
                this.objBseTicker.d = 0;
            }
            if (n2 > this.objBseTicker.c[6]._fldif.height && n2 < this.objBseTicker.c[6]._fldif.height + this.objBseTicker.c[8]._fldif.height) {
                this.objBseTicker.d = 1;
            }
            if (n2 > this.objBseTicker.c[6]._fldif.height + this.objBseTicker.c[8]._fldif.height && n2 < this.objBseTicker.c[6]._fldif.height + this.objBseTicker.c[8]._fldif.height + this.objBseTicker.c[10]._fldif.height) {
                this.objBseTicker.d = 2;
            }
            if (n2 > this.objBseTicker.c[6]._fldif.height + this.objBseTicker.c[8]._fldif.height + this.objBseTicker.c[10]._fldif.height && n2 < this.objBseTicker.c[6]._fldif.height + this.objBseTicker.c[8]._fldif.height + this.objBseTicker.c[10]._fldif.height + this.objBseTicker.c[12]._fldif.height) {
                this.objBseTicker.d = 3;
            }
            this.a(this.getGraphics());
            this.objBseTicker._mthif(this.objBseTicker.d);
            this.p = 0;
        }
        this.pntMouseClickedPoint = new Point(n, n2);
        this.intVar = this.p;
        if (!this._mthint() && n > this._flddo + this.r) {
            this._mthnew();
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        this._fldcase = true;
        this.p = n - this.pntMouseClickedPoint.x + this.intVar;
        if (this.intVar > this.p) {
            this._fldtry = 1;
            this.objBseTicker.repaint(this.objBseTicker.c[0]._fldif.x, this.objBseTicker.c[0]._fldif.y, this.objBseTicker.c[0]._fldif.width, this.objBseTicker.c[0]._fldif.height);
        }
        else {
            this._fldtry = 2;
            this.objBseTicker.repaint(this.objBseTicker.c[1]._fldif.x, this.objBseTicker.c[1]._fldif.y, this.objBseTicker.c[1]._fldif.width, this.objBseTicker.c[1]._fldif.height);
        }
        this.objBseTicker.repaint(this.objBseTicker.c[2]._fldif.x, this.objBseTicker.c[2]._fldif.y, this.objBseTicker.c[2]._fldif.width, this.objBseTicker.c[2]._fldif.height);
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int x, final int y) {
        if (x > this.q._fldif.width + this.r) {
            if (!this.objBseTicker.getCurrentType().equals("1") && !this.objBseTicker.getCurrentType().equals("2")) {
                this.setCursor(new Cursor(12));
            }
            this.pntMousePoint.x = x;
            this.pntMousePoint.y = y;
        }
        this.objBseTicker.repaint();
        return false;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.setCursor(new Cursor(0));
        return false;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this != null && !this.objBseTicker.getCurrentType().equals("1") && !this.objBseTicker.getCurrentType().equals("2")) {
            if (n > this.r + this.q._fldif.width) {
                this.setCursor(new Cursor(12));
            }
            else if (n <= this.r + this.q._fldif.width) {
                this.setCursor(new Cursor(0));
            }
        }
        return false;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.a) {
            this._mthtry();
        }
        return false;
    }
    
    public void paint(final Graphics graphics) {
        this.reshape(this.objRectangle.x, this.objRectangle.y, this.objRectangle.width, this.objRectangle.height);
        graphics.setColor(this.objBackColor);
        graphics.fillRect(0, 0, this.objRectangle.width, this.objRectangle.height);
        if (!this.vecScripData.isEmpty()) {
            this.objBseTicker.a();
            if (!this._mthint()) {
                switch (this._fldtry) {
                    case 1: {
                        this.n = this.objBSEDimension.width;
                        if (this.p <= -this.n) {
                            this.p = this.p + this.n - this.f;
                            break;
                        }
                        this.p -= this.f;
                        break;
                    }
                    case 2: {
                        this.n = this.objRectangle.width;
                        if (this.p >= this.n) {
                            this.p = this.p + -this.objBSEDimension.width + this.f;
                            break;
                        }
                        this.p += this.f;
                        break;
                    }
                }
            }
            this.intIncrementor = this.p;
            if (this.intIncrementor >= 0) {
                this.intRecSize = this.vecScripData.size();
                while (this.intIncrementor >= 0) {
                    if (this.intRecSize <= 0) {
                        this.intRecSize = this.vecScripData.size();
                    }
                    --this.intRecSize;
                    this.objCObject = this.vecScripData.elementAt(this.intRecSize);
                    this.intIncrementor -= this.objCObject._fldif.width;
                }
                this.intItemNum = this.intRecSize - 1;
            }
            else {
                this.intItemNum = -1;
            }
            while (this.intIncrementor < this.objRectangle.width) {
                this.objCObject = this._mthdo();
                if (this.intIncrementor + this.objCObject._fldif.width >= 0 && this.intIncrementor <= this.objRectangle.width) {
                    this.objCObject._fldtry = this.intIncrementor;
                    if (this.q != null || this.objCObject != null) {
                        graphics.drawImage(this.q._flddo, this.r, 0, this);
                        graphics.drawImage(this.objCObject._flddo, this.intIncrementor + this.q._fldif.width + 8, 0, this);
                        if (this.d == 1) {
                            graphics.drawImage(this.objBseTicker.c[14]._flddo, this.r, 0, this);
                        }
                        if (this.d == 2) {
                            graphics.drawImage(this.objBseTicker.c[15]._flddo, this.r, this.objBseTicker.c[6]._fldif.height, this);
                        }
                        if (this.d == 3) {
                            graphics.drawImage(this.objBseTicker.c[16]._flddo, this.r, this.objBseTicker.c[6]._fldif.height + this.objBseTicker.c[8]._fldif.height, this);
                        }
                        if (this.d == 4) {
                            graphics.drawImage(this.objBseTicker.c[17]._flddo, this.r, this.objBseTicker.c[6]._fldif.height + this.objBseTicker.c[8]._fldif.height + this.objBseTicker.c[10]._fldif.height, this);
                        }
                        this.a(graphics);
                    }
                }
                this.intIncrementor += this.objCObject._fldif.width;
            }
        }
    }
    
    public void _mthnew() {
        ++this.i;
        this.a = true;
    }
    
    public void run() {
        while (this != null) {
            this.repaint();
            try {
                Thread.sleep(this.u);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    void a(final Graphics graphics) {
        final Color color = graphics.getColor();
        graphics.setColor(this.objBseTicker._fldgoto);
        graphics.fillRect(0, 0, this.r, 41);
        graphics.setColor(color);
        if (!this.objBseTicker._mthif()) {
            return;
        }
        if (this.objBseTicker.d == 0) {
            graphics.drawImage(this.objBseTicker.c[6]._flddo, 0, 1, this);
            graphics.drawImage(this.objBseTicker.c[9]._flddo, 0, this.objBseTicker.c[6]._fldif.height, this);
            graphics.drawImage(this.objBseTicker.c[11]._flddo, 0, this.objBseTicker.c[6]._fldif.height + this.objBseTicker.c[8]._fldif.height, this);
            graphics.drawImage(this.objBseTicker.c[13]._flddo, 0, this.objBseTicker.c[6]._fldif.height + this.objBseTicker.c[8]._fldif.height + this.objBseTicker.c[10]._fldif.height, this);
            return;
        }
        if (this.objBseTicker.d == 1) {
            graphics.drawImage(this.objBseTicker.c[7]._flddo, 1, 1, this);
            graphics.drawImage(this.objBseTicker.c[8]._flddo, 1, this.objBseTicker.c[6]._fldif.height, this);
            graphics.drawImage(this.objBseTicker.c[11]._flddo, 1, this.objBseTicker.c[6]._fldif.height + this.objBseTicker.c[8]._fldif.height, this);
            graphics.drawImage(this.objBseTicker.c[13]._flddo, 1, this.objBseTicker.c[6]._fldif.height + this.objBseTicker.c[8]._fldif.height + this.objBseTicker.c[10]._fldif.height, this);
            return;
        }
        if (this.objBseTicker.d == 2) {
            graphics.drawImage(this.objBseTicker.c[7]._flddo, 1, 1, this);
            graphics.drawImage(this.objBseTicker.c[9]._flddo, 1, this.objBseTicker.c[6]._fldif.height, this);
            graphics.drawImage(this.objBseTicker.c[10]._flddo, 1, this.objBseTicker.c[6]._fldif.height + this.objBseTicker.c[8]._fldif.height, this);
            graphics.drawImage(this.objBseTicker.c[13]._flddo, 1, this.objBseTicker.c[6]._fldif.height + this.objBseTicker.c[8]._fldif.height + this.objBseTicker.c[10]._fldif.height, this);
            return;
        }
        graphics.drawImage(this.objBseTicker.c[7]._flddo, 1, 1, this);
        graphics.drawImage(this.objBseTicker.c[9]._flddo, 1, this.objBseTicker.c[6]._fldif.height, this);
        graphics.drawImage(this.objBseTicker.c[11]._flddo, 1, this.objBseTicker.c[6]._fldif.height + this.objBseTicker.c[8]._fldif.height, this);
        graphics.drawImage(this.objBseTicker.c[12]._flddo, 1, this.objBseTicker.c[6]._fldif.height + this.objBseTicker.c[8]._fldif.height + this.objBseTicker.c[10]._fldif.height, this);
    }
    
    public void a() {
        this.intMPosition = 0;
        if (this.vecScripData1.size() > 0) {
            this.vecScripData1.removeAllElements();
        }
    }
    
    public void update(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.imgBackGround == null || size.width != this.dimImageDimension.width || size.height != this.dimImageDimension.height) {
            this.dimImageDimension = size;
            this.imgBackGround = this.createImage(size.width, size.height);
            this.objGrapBack = this.imgBackGround.getGraphics();
        }
        this.paint(this.objGrapBack);
        graphics.drawImage(this.imgBackGround, 0, 0, null);
    }
}
