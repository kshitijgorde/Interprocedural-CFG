import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class TurtlePixels
{
    private static final int MAX_TURTLE_HEIGHT = 100;
    private static final int MAX_TURTLE_WIDTH = 100;
    private static final int MIN_TURTLE_HEIGHT = 5;
    private static final int MIN_TURTLE_WIDTH = 5;
    private static final int BLACK_OPAQUE_PIXEL = -16777216;
    private static final int PIXEL_OPACITY_BITS = -16777216;
    private static final int WHITE_OPAQUE_PIXEL = -1;
    private boolean[] turtleFillMask;
    private Color turtleColor;
    private float turtleHeading;
    private int pixRectSideSize;
    private int turtleHeight;
    private int turtleWidth;
    private int[] baseTurtlePixels;
    private int[] turtlePixRect;
    
    public TurtlePixels(int turtleWidth, int turtleHeight, final Color turtleColor, final float turtleHeading) {
        if (turtleHeight > 100) {
            turtleHeight = 100;
        }
        if (turtleHeight < 5) {
            turtleHeight = 5;
        }
        if (turtleWidth > 100) {
            turtleWidth = 100;
        }
        if (turtleWidth < 5) {
            turtleWidth = 5;
        }
        if ((this.turtleHeight = turtleHeight) >= (this.turtleWidth = turtleWidth)) {
            this.pixRectSideSize = turtleHeight + turtleHeight / 4;
        }
        else {
            this.pixRectSideSize = turtleWidth + turtleWidth / 4;
        }
        if (this.pixRectSideSize % 2 != 0) {
            ++this.pixRectSideSize;
        }
        this.baseTurtlePixels = new int[this.pixRectSideSize * this.pixRectSideSize];
        for (int i = 0; i < this.baseTurtlePixels.length; ++i) {
            this.baseTurtlePixels[i] = -1;
        }
        this.initTurtlePixels(this.baseTurtlePixels, this.pixRectSideSize);
        this.clearToTurtleEdge();
        this.buildTurtleFillMask();
        this.turtleColor = turtleColor;
        final int n = this.turtleColor.getRGB() | 0xFF000000;
        for (int j = 0; j < this.baseTurtlePixels.length; ++j) {
            if (this.turtleFillMask[j]) {
                this.baseTurtlePixels[j] = n;
            }
        }
        this.updateTurtlePixels(this.turtleHeading = turtleHeading);
    }
    
    private void buildTurtleFillMask() {
        final int n2;
        final int n = n2 = Math.round(this.pixRectSideSize / 2.0f) - 1;
        final int n3 = this.baseTurtlePixels[n * this.pixRectSideSize + n2];
        this.turtleFillMask = new boolean[this.pixRectSideSize * this.pixRectSideSize];
        this.floodFill(n, n2, n3);
    }
    
    private void circlePixels(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.fillPixel(n + n3, n2 + n4, n5);
        this.fillPixel(n + n4, n2 + n3, n5);
        this.fillPixel(n + n4, n2 - n3, n5);
        this.fillPixel(n + n3, n2 - n4, n5);
        this.fillPixel(n - n3, n2 - n4, n5);
        this.fillPixel(n - n4, n2 - n3, n5);
        this.fillPixel(n - n4, n2 + n3, n5);
        this.fillPixel(n - n3, n2 + n4, n5);
    }
    
    private void clearToTurtleEdge() {
        for (int i = 0; i < this.pixRectSideSize; ++i) {
            for (int j = 0; j < this.pixRectSideSize; ++j) {
                final int n = i * this.pixRectSideSize + j;
                if ((this.baseTurtlePixels[n] & 0xFFFFFF) != 0xFFFFFF) {
                    break;
                }
                this.baseTurtlePixels[n] = 0;
            }
        }
        for (int k = 0; k < this.pixRectSideSize; ++k) {
            for (int l = this.pixRectSideSize - 1; l >= 0; --l) {
                final int n2 = k * this.pixRectSideSize + l;
                if ((this.baseTurtlePixels[n2] & 0xFFFFFF) != 0xFFFFFF) {
                    break;
                }
                this.baseTurtlePixels[n2] = 0;
            }
        }
        for (int n3 = 0; n3 < this.pixRectSideSize; ++n3) {
            for (int n4 = 0; n4 < this.pixRectSideSize; ++n4) {
                final int n5 = n4 * this.pixRectSideSize + n3;
                if (this.baseTurtlePixels[n5] != 0) {
                    if ((this.baseTurtlePixels[n5] & 0xFFFFFF) != 0xFFFFFF) {
                        break;
                    }
                    this.baseTurtlePixels[n5] = 0;
                }
            }
        }
        for (int n6 = 0; n6 < this.pixRectSideSize; ++n6) {
            for (int n7 = this.pixRectSideSize - 1; n7 >= 0; --n7) {
                final int n8 = n7 * this.pixRectSideSize + n6;
                if (this.baseTurtlePixels[n8] != 0) {
                    if ((this.baseTurtlePixels[n8] & 0xFFFFFF) != 0xFFFFFF) {
                        break;
                    }
                    this.baseTurtlePixels[n8] = 0;
                }
            }
        }
    }
    
    private void fillHorizLine(final int n, final int n2, final int n3, final int n4) {
        for (int i = n; i <= n2; ++i) {
            this.fillPixel(i, n3, n4);
        }
    }
    
    private void fillVertLine(final int n, final int n2, final int n3, final int n4) {
        for (int i = n2; i <= n3; ++i) {
            this.fillPixel(n, i, n4);
        }
    }
    
    private void fillXUnitLine(int i, final int n, final int n2, final int n3, final int n4) {
        final int n5 = n2 - i;
        boolean b = false;
        int n6 = n3 - n;
        int n7 = n;
        if (n6 < 0) {
            b = true;
            n6 = -n6;
        }
        int n8 = 2 * n6 - n5;
        final int n9 = 2 * n6;
        final int n10 = 2 * (n6 - n5);
        this.fillPixel(i, n, n4);
        while (i < n2) {
            ++i;
            if (n8 <= 0) {
                n8 += n9;
            }
            else {
                n8 += n10;
                ++n7;
            }
            if (b) {
                this.fillPixel(i, n - (n7 - n), n4);
            }
            else {
                this.fillPixel(i, n7, n4);
            }
        }
    }
    
    private void fillYUnitLine(final int n, int i, final int n2, final int n3, final int n4) {
        boolean b = false;
        int n5 = n2 - n;
        int n6 = n;
        if (n5 < 0) {
            b = true;
            n5 = -n5;
        }
        final int n7 = n3 - i;
        int n8 = 2 * n5 - n7;
        final int n9 = 2 * n5;
        final int n10 = 2 * (n5 - n7);
        this.fillPixel(n, i, n4);
        while (i < n3) {
            ++i;
            if (n8 <= 0) {
                n8 += n9;
            }
            else {
                n8 += n10;
                ++n6;
            }
            if (b) {
                this.fillPixel(n - (n6 - n), i, n4);
            }
            else {
                this.fillPixel(n6, i, n4);
            }
        }
    }
    
    private void floodFill(final int n, final int n2, final int n3) {
        final int n4 = n * this.pixRectSideSize + n2;
        if (this.baseTurtlePixels[n4] == n3 && !this.turtleFillMask[n4]) {
            this.turtleFillMask[n4] = true;
            if (n - 1 >= 0) {
                this.floodFill(n - 1, n2, n3);
            }
            if (n + 1 < this.pixRectSideSize) {
                this.floodFill(n + 1, n2, n3);
            }
            if (n2 - 1 >= 0) {
                this.floodFill(n, n2 - 1, n3);
            }
            if (n2 + 1 < this.pixRectSideSize) {
                this.floodFill(n, n2 + 1, n3);
            }
        }
    }
    
    private float slope(final int n, final int n2, final int n3, final int n4) {
        return (n4 - n2) / (n3 - n);
    }
    
    private void updateTurtlePixels(final float n) {
        if (this.turtlePixRect == null) {
            this.turtlePixRect = new int[this.pixRectSideSize * this.pixRectSideSize];
        }
        if (n == 0.0f) {
            for (int i = 0; i < this.pixRectSideSize * this.pixRectSideSize; ++i) {
                this.turtlePixRect[i] = this.baseTurtlePixels[i];
            }
        }
        else {
            final int n2 = this.pixRectSideSize / 2;
            final double n3 = n;
            final double cos = Math.cos(n3);
            final double sin = Math.sin(n3);
            for (int j = 0; j < this.pixRectSideSize; ++j) {
                for (int k = 0; k < this.pixRectSideSize; ++k) {
                    this.turtlePixRect[j * this.pixRectSideSize + k] = 0;
                }
            }
            for (int l = 0; l < this.pixRectSideSize; ++l) {
                final int n4 = l * this.pixRectSideSize;
                final int n5 = 2 * (l - n2) + 1;
                for (int n6 = 0; n6 < this.pixRectSideSize; ++n6) {
                    final int n7 = 2 * (n6 - n2) + 1;
                    final int n8 = ((int)Math.rint(n7 * cos - n5 * sin) - 1) / 2 + n2;
                    final int n9 = ((int)Math.rint(n7 * sin + n5 * cos) - 1) / 2 + n2;
                    if (n8 >= 0) {
                        if (n8 < this.pixRectSideSize) {
                            if (n9 >= 0) {
                                if (n9 < this.pixRectSideSize) {
                                    this.turtlePixRect[n4 + n6] = this.baseTurtlePixels[n9 * this.pixRectSideSize + n8];
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void fillCirclePixels(final int n, final int n2, final int n3, final int n4) {
        int n5 = 0;
        int i = n3;
        float n6 = 1.25f - n3;
        this.circlePixels(n, n2, n5, i, n4);
        while (i > n5) {
            if (n6 < 0.0f) {
                n6 += 2.0f * n5 + 3.0f;
                ++n5;
            }
            else {
                n6 += 2.0f * (n5 - i) + 5.0f;
                ++n5;
                --i;
            }
            this.circlePixels(n, n2, n5, i, n4);
        }
    }
    
    public void fillLinePixels(final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n == n3) {
            if (n2 <= n4) {
                this.fillVertLine(n, n2, n4, n5);
            }
            else {
                this.fillVertLine(n, n4, n2, n5);
            }
        }
        else if (n2 == n4) {
            if (n <= n3) {
                this.fillHorizLine(n, n3, n2, n5);
            }
            else {
                this.fillHorizLine(n3, n, n2, n5);
            }
        }
        else if (Math.abs(this.slope(n, n2, n3, n4)) > 1.0f) {
            if (n2 < n4) {
                this.fillYUnitLine(n, n2, n3, n4, n5);
            }
            else {
                this.fillYUnitLine(n3, n4, n, n2, n5);
            }
        }
        else if (n < n3) {
            this.fillXUnitLine(n, n2, n3, n4, n5);
        }
        else {
            this.fillXUnitLine(n3, n4, n, n2, n5);
        }
    }
    
    public void fillPixel(final int n, final int n2, final int n3) {
        final String s = "TurtlePixels.fillPixel: ";
        final String s2 = " out of bounds";
        if (n < 0 || n >= this.pixRectSideSize) {
            System.err.println(s + "x=" + n + s2);
        }
        else if (n2 < 0 || n2 >= this.pixRectSideSize) {
            System.err.println(s + "y=" + n2 + s2);
        }
        else {
            this.baseTurtlePixels[n + n2 * this.pixRectSideSize] = n3;
        }
    }
    
    public int[] getPixels() {
        return this.turtlePixRect;
    }
    
    public int getSideSize() {
        return this.pixRectSideSize;
    }
    
    public Color getTurtleColor() {
        return this.turtleColor;
    }
    
    public float getTurtleHeading() {
        return this.turtleHeading;
    }
    
    public int getTurtleHeight() {
        return this.turtleHeight;
    }
    
    public int getTurtleWidth() {
        return this.turtleWidth;
    }
    
    public abstract void initTurtlePixels(final int[] p0, final int p1);
    
    public boolean setTurtleColor(final Color turtleColor) {
        if (turtleColor != this.turtleColor) {
            final int rgb = turtleColor.getRGB();
            for (int i = 0; i < this.baseTurtlePixels.length; ++i) {
                if (this.turtleFillMask[i]) {
                    this.baseTurtlePixels[i] = (this.baseTurtlePixels[i] & 0xFF000000) + rgb;
                }
            }
            this.updateTurtlePixels(this.turtleHeading);
            this.turtleColor = turtleColor;
            return true;
        }
        return false;
    }
    
    public boolean setTurtleHeading(final float turtleHeading) {
        if (this instanceof BallTurtle) {
            return false;
        }
        if (Math.abs(this.turtleHeading - turtleHeading) > 0.001) {
            this.updateTurtlePixels(this.turtleHeading = turtleHeading);
            return true;
        }
        return false;
    }
}
