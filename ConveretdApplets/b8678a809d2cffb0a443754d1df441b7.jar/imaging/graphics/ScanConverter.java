// 
// Decompiled by Procyon v0.5.30
// 

package imaging.graphics;

import imaging.math3D.MoreMath;
import java.util.Vector;
import imaging.math3D.ViewWindow;

public class ScanConverter
{
    private static final int SCALE_BITS = 16;
    private static final int SCALE = 65536;
    private static final int SCALE_MASK = 65535;
    protected ViewWindow view;
    protected Scan[] scans;
    protected int top;
    protected int bottom;
    
    public ScanConverter(final ViewWindow view) {
        this.view = view;
    }
    
    public int getTopBoundary() {
        return this.top;
    }
    
    public int getBottomBoundary() {
        return this.bottom;
    }
    
    public Scan getScan(final int y) {
        return this.scans[y];
    }
    
    protected void ensureCapacity() {
        final int height = this.view.getTopOffset() + this.view.getHeight();
        if (this.scans == null || this.scans.length != height) {
            this.scans = new Scan[height];
            for (int i = 0; i < height; ++i) {
                this.scans[i] = new Scan();
            }
            this.top = 0;
            this.bottom = height - 1;
        }
    }
    
    private void clearCurrentScan() {
        for (int i = this.top; i <= this.bottom; ++i) {
            this.scans[i].clear();
        }
        this.top = Integer.MAX_VALUE;
        this.bottom = Integer.MIN_VALUE;
    }
    
    public boolean convert(final Vector<float[]> polygPoints) {
        this.ensureCapacity();
        this.clearCurrentScan();
        final int minX = this.view.getLeftOffset();
        final int maxX = this.view.getLeftOffset() + this.view.getWidth() - 1;
        final int minY = this.view.getTopOffset();
        final int maxY = this.view.getTopOffset() + this.view.getHeight() - 1;
        for (int numVertices = polygPoints.size(), i = 0; i < numVertices; ++i) {
            float[] v1 = polygPoints.elementAt(i);
            float[] v2;
            if (i == numVertices - 1) {
                v2 = polygPoints.elementAt(0);
            }
            else {
                v2 = polygPoints.elementAt(i + 1);
            }
            if (v1[1] > v2[1]) {
                final float[] temp = v1;
                v1 = v2;
                v2 = temp;
            }
            final float dy = v2[1] - v1[1];
            if (dy != 0.0f) {
                int startY = Math.max(MoreMath.ceil(v1[1]), minY);
                int endY = Math.min(MoreMath.ceil(v2[1]) - 1, maxY);
                this.top = Math.min(this.top, startY);
                this.bottom = Math.max(this.bottom, endY);
                final float dx = v2[0] - v1[0];
                if (dx == 0.0f) {
                    int x = MoreMath.ceil(v1[0]);
                    x = Math.min(maxX + 1, Math.max(x, minX));
                    for (int y = startY; y <= endY; ++y) {
                        this.scans[y].setBoundary(x);
                    }
                }
                else {
                    final float gradient = dx / dy;
                    final float startX = v1[0] + (startY - v1[1]) * gradient;
                    if (startX < minX) {
                        int yInt;
                        for (yInt = (int)(v1[1] + (minX - v1[0]) / gradient), yInt = Math.min(yInt, endY); startY <= yInt; ++startY) {
                            this.scans[startY].setBoundary(minX);
                        }
                    }
                    else if (startX > maxX) {
                        int yInt;
                        for (yInt = (int)(v1[1] + (maxX - v1[0]) / gradient), yInt = Math.min(yInt, endY); startY <= yInt; ++startY) {
                            this.scans[startY].setBoundary(maxX + 1);
                        }
                    }
                    if (startY <= endY) {
                        final float endX = v1[0] + (endY - v1[1]) * gradient;
                        if (endX < minX) {
                            int yInt2;
                            for (yInt2 = MoreMath.ceil(v1[1] + (minX - v1[0]) / gradient), yInt2 = Math.max(yInt2, startY); endY >= yInt2; --endY) {
                                this.scans[endY].setBoundary(minX);
                            }
                        }
                        else if (endX > maxX) {
                            int yInt2;
                            for (yInt2 = MoreMath.ceil(v1[1] + (maxX - v1[0]) / gradient), yInt2 = Math.max(yInt2, startY); endY >= yInt2; --endY) {
                                this.scans[endY].setBoundary(maxX + 1);
                            }
                        }
                        if (startY <= endY) {
                            int xScaled = (int)(65536.0f * v1[0] + 65536.0f * (startY - v1[1]) * dx / dy) + 65535;
                            final int dxScaled = (int)(dx * 65536.0f / dy);
                            for (int y2 = startY; y2 <= endY; ++y2) {
                                this.scans[y2].setBoundary(xScaled >> 16);
                                xScaled += dxScaled;
                            }
                        }
                    }
                }
            }
        }
        for (int i = this.top; i <= this.bottom; ++i) {
            if (this.scans[i].isValid()) {
                return true;
            }
        }
        return false;
    }
    
    public static class Scan
    {
        public int left;
        public int right;
        
        public void setBoundary(final int x) {
            if (x < this.left) {
                this.left = x;
            }
            if (x - 1 > this.right) {
                this.right = x - 1;
            }
        }
        
        public void clear() {
            this.left = Integer.MAX_VALUE;
            this.right = Integer.MIN_VALUE;
        }
        
        public boolean isValid() {
            return this.left <= this.right;
        }
        
        public void setTo(final int left, final int right) {
            this.left = left;
            this.right = right;
        }
        
        public boolean equals(final int left, final int right) {
            return this.left == left && this.right == right;
        }
    }
}
