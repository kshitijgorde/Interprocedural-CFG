// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.view.impl.image;

import java.awt.Point;
import java.awt.Cursor;
import java.awt.BasicStroke;

public class RectangleRubberBand
{
    public static final int STATUS_READY = 0;
    public static final int STATUS_RESIZING = 1;
    public static final int STATUS_MOVING = 2;
    public static final int ANCHOR_COUNT = 8;
    public static final int ANCHOR_NONE = -1;
    public static final int ANCHOR_LEFT_TOP = 0;
    public static final int ANCHOR_CENTER_TOP = 1;
    public static final int ANCHOR_RIGHT_TOP = 2;
    public static final int ANCHOR_RIGHT_MIDDLE = 3;
    public static final int ANCHOR_RIGHT_BOTTOM = 4;
    public static final int ANCHOR_CENTER_BOTTOM = 5;
    public static final int ANCHOR_LEFT_BOTTOM = 6;
    public static final int ANCHOR_LEFT_MIDDLE = 7;
    public static final double[] ANCHOR_X_FACTOR;
    public static final double[] ANCHOR_Y_FACTOR;
    public static final int ANCHOR_SIDE_HALF_SIZE = 3;
    static final BasicStroke I;
    static final float[] L;
    static final BasicStroke O;
    static final Cursor H;
    static final Cursor G;
    static final Cursor D;
    static final Cursor[] F;
    protected int M;
    protected int C;
    protected int K;
    protected int A;
    protected int J;
    protected int B;
    protected Double N;
    protected Integer E;
    
    public RectangleRubberBand() {
        this.J = 0;
        this.B = -1;
        this.N = null;
        this.E = null;
    }
    
    public Integer getPerimeterMin() {
        return this.E;
    }
    
    public void setPerimeterMin(final Integer e) {
        this.E = e;
    }
    
    public Double getProportions() {
        return this.N;
    }
    
    public void setProportions(final Double n) {
        this.N = n;
    }
    
    public int getLeft() {
        return (this.M < this.K) ? this.M : this.K;
    }
    
    public int getTop() {
        return (this.C < this.A) ? this.C : this.A;
    }
    
    public Point getLeftTop() {
        return new Point(this.getLeft(), this.getTop());
    }
    
    public int getRight() {
        return (this.M < this.K) ? this.K : this.M;
    }
    
    public int getBottom() {
        return (this.C < this.A) ? this.A : this.C;
    }
    
    public Point getRightBottom() {
        return new Point(this.getRight(), this.getBottom());
    }
    
    public Point getPoint0() {
        return new Point(this.M, this.C);
    }
    
    public Point getPoint1() {
        return new Point(this.K, this.A);
    }
    
    public void setPoint0(final Point point) {
        this.setX0((int)point.getX());
        this.setY0((int)point.getY());
    }
    
    public void setPoint1(final Point point) {
        this.setX1((int)point.getX());
        this.setY1((int)point.getY());
    }
    
    public int getHeight() {
        return Math.abs(this.C - this.A);
    }
    
    public int getWidth() {
        return Math.abs(this.M - this.K);
    }
    
    public int getX0() {
        return this.M;
    }
    
    public void setX0(final int m) {
        this.M = m;
        this.A(false);
    }
    
    public int getX1() {
        return this.K;
    }
    
    public void setX1(final int k) {
        this.K = k;
        this.A(false);
    }
    
    public int getY0() {
        return this.C;
    }
    
    public void setY0(final int c) {
        this.C = c;
        this.A(true);
    }
    
    public int getY1() {
        return this.A;
    }
    
    public void setY1(final int a) {
        this.A = a;
        this.A(true);
    }
    
    public int getHotAnchorId() {
        return this.B;
    }
    
    public void setHotAnchorId(final int b) {
        this.B = b;
        int m = 0;
        int c = 0;
        int k = 0;
        int a = 0;
        switch (b) {
            case 0:
            case 1: {
                m = this.getRight();
                c = this.getBottom();
                k = this.getLeft();
                a = this.getTop();
                break;
            }
            case 2:
            case 3: {
                m = this.getLeft();
                c = this.getBottom();
                k = this.getRight();
                a = this.getTop();
                break;
            }
            case 4:
            case 5: {
                m = this.getLeft();
                c = this.getTop();
                k = this.getRight();
                a = this.getBottom();
                break;
            }
            case 6:
            case 7: {
                m = this.getRight();
                c = this.getTop();
                k = this.getLeft();
                a = this.getBottom();
                break;
            }
        }
        this.M = m;
        this.K = k;
        this.C = c;
        this.A = a;
    }
    
    private boolean A(final boolean b) {
        boolean b2 = true;
        for (int n = 0; b2 && n < 2; b2 = this.B(b), ++n) {
            this.C(b);
        }
        return b2;
    }
    
    private boolean C(final boolean b) {
        boolean b2 = false;
        final int width = this.getWidth();
        final int height = this.getHeight();
        final boolean b3 = this.A >= this.C;
        final boolean b4 = this.K >= this.M;
        int n = width;
        int n2 = height;
        if (this.E != null && 2 * (width + height) < this.E) {
            final int n3 = this.E / 2;
            final double doubleValue = this.N;
            if (this.N != null) {
                n = (int)(doubleValue * n3 / (1.0 + doubleValue));
                n2 = n3 - n;
            }
        }
        if (n != width) {
            b2 = true;
            if (b4) {
                this.K = this.M + n;
            }
            else {
                this.K = this.M - n;
            }
        }
        if (n2 != height) {
            b2 = true;
            if (b3) {
                this.A = this.C + n2;
            }
            else {
                this.A = this.C - n2;
            }
        }
        return b2;
    }
    
    private boolean B(final boolean b) {
        boolean b2 = false;
        final int width = this.getWidth();
        final int height = this.getHeight();
        final boolean b3 = this.A >= this.C;
        final boolean b4 = this.K >= this.M;
        int n = width;
        int n2 = height;
        if (width > 0 && height > 0) {
            if (this.N != null) {
                if (b) {
                    n = (int)(height * this.N);
                }
                else {
                    n2 = (int)(width / this.N);
                }
            }
            if (n != width) {
                b2 = true;
                if (b4) {
                    this.K = this.M + n;
                }
                else {
                    this.K = this.M - n;
                }
            }
            if (n2 != height) {
                b2 = true;
                if (b3) {
                    this.A = this.C + n2;
                }
                else {
                    this.A = this.C - n2;
                }
            }
        }
        return b2;
    }
    
    public int getStatus() {
        return this.J;
    }
    
    public void setStatus(final int j) {
        this.J = j;
    }
    
    public void setCoords(final int x0, final int y0, final int x2, final int y2) {
        this.setX0(x0);
        this.setY0(y0);
        this.setX1(x2);
        this.setY1(y2);
    }
    
    static {
        ANCHOR_X_FACTOR = new double[] { 0.0, 0.5, 1.0, 1.0, 1.0, 0.5, 0.0, 0.0 };
        ANCHOR_Y_FACTOR = new double[] { 0.0, 0.0, 0.0, 0.5, 1.0, 1.0, 1.0, 0.5 };
        I = new BasicStroke(1.0f);
        L = new float[] { 3.0f };
        O = new BasicStroke(1.0f, 0, 0, 10.0f, RectangleRubberBand.L, 0.0f);
        H = new Cursor(0);
        G = new Cursor(13);
        D = new Cursor(12);
        F = new Cursor[8];
        for (int i = 0; i < 8; ++i) {
            Cursor cursor = null;
            switch (i) {
                case 0: {
                    cursor = new Cursor(6);
                    break;
                }
                case 1: {
                    cursor = new Cursor(8);
                    break;
                }
                case 2: {
                    cursor = new Cursor(7);
                    break;
                }
                case 3: {
                    cursor = new Cursor(11);
                    break;
                }
                case 4: {
                    cursor = new Cursor(5);
                    break;
                }
                case 5: {
                    cursor = new Cursor(9);
                    break;
                }
                case 6: {
                    cursor = new Cursor(4);
                    break;
                }
                case 7: {
                    cursor = new Cursor(10);
                    break;
                }
            }
            RectangleRubberBand.F[i] = cursor;
        }
    }
}
