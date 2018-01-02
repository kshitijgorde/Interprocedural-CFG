// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.display;

import java.util.logging.Logger;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Clip
{
    private static final byte EMPTY = 0;
    private static final byte INUSE = 1;
    private static final byte INVALID = 2;
    private double[] clip;
    private byte status;
    
    public Clip() {
        this.clip = new double[8];
        this.status = 2;
    }
    
    public void reset() {
        this.status = 0;
    }
    
    public void invalidate() {
        this.status = 2;
    }
    
    public void setClip(final Clip clip) {
        this.status = 1;
        System.arraycopy(clip.clip, 0, this.clip, 0, this.clip.length);
    }
    
    public void setClip(final Rectangle2D rectangle2D) {
        this.setClip(rectangle2D.getX(), rectangle2D.getY(), rectangle2D.getWidth(), rectangle2D.getHeight());
    }
    
    public void setClip(final double n, final double n2, final double n3, final double n4) {
        this.status = 1;
        this.clip[0] = n;
        this.clip[1] = n2;
        this.clip[6] = n + n3;
        this.clip[7] = n2 + n4;
    }
    
    public void transform(final AffineTransform affineTransform) {
        this.clip[2] = this.clip[0];
        this.clip[3] = this.clip[7];
        this.clip[4] = this.clip[6];
        this.clip[5] = this.clip[1];
        affineTransform.transform(this.clip, 0, this.clip, 0, 4);
        double n = this.clip[0];
        double n2 = this.clip[1];
        double n3 = this.clip[6];
        double n4 = this.clip[7];
        for (int i = 0; i < 7; i += 2) {
            if (this.clip[i] < n) {
                n = this.clip[i];
            }
            if (this.clip[i] > n3) {
                n3 = this.clip[i];
            }
            if (this.clip[i + 1] < n2) {
                n2 = this.clip[i + 1];
            }
            if (this.clip[i + 1] > n4) {
                n4 = this.clip[i + 1];
            }
        }
        this.clip[0] = n;
        this.clip[1] = n2;
        this.clip[6] = n3;
        this.clip[7] = n4;
    }
    
    public void limit(final double n, final double n2, final double n3, final double n4) {
        this.clip[0] = Math.max(this.clip[0], n);
        this.clip[1] = Math.max(this.clip[1], n2);
        this.clip[6] = Math.min(this.clip[6], n3);
        this.clip[7] = Math.min(this.clip[7], n4);
    }
    
    public boolean intersects(final Rectangle2D rectangle2D, final double n) {
        final double n2 = this.clip[6] - this.clip[0];
        final double n3 = this.clip[7] - this.clip[1];
        final double width = rectangle2D.getWidth();
        final double height = rectangle2D.getHeight();
        if (width < 0.0 || height < 0.0 || n2 < 0.0 || n3 < 0.0) {
            return false;
        }
        final double n4 = this.clip[0];
        final double n5 = this.clip[1];
        final double n6 = rectangle2D.getX() - n;
        final double n7 = rectangle2D.getY() - n;
        final double n8 = width + (n6 + 2.0 * n);
        final double n9 = height + (n7 + 2.0 * n);
        final double n10 = n2 + n4;
        final double n11 = n3 + n5;
        return (n8 < n6 || n8 > n4) && (n9 < n7 || n9 > n5) && (n10 < n4 || n10 > n6) && (n11 < n5 || n11 > n7);
    }
    
    public void union(final Clip clip) {
        if (this.status == 2) {
            return;
        }
        if (this.status == 0) {
            this.setClip(clip);
            this.status = 1;
            return;
        }
        this.clip[0] = Math.min(this.clip[0], clip.clip[0]);
        this.clip[1] = Math.min(this.clip[1], clip.clip[1]);
        this.clip[6] = Math.max(this.clip[6], clip.clip[6]);
        this.clip[7] = Math.max(this.clip[7], clip.clip[7]);
    }
    
    public void union(final Rectangle2D clip) {
        if (this.status == 2) {
            return;
        }
        final double minX = clip.getMinX();
        final double minY = clip.getMinY();
        final double maxX = clip.getMaxX();
        final double maxY = clip.getMaxY();
        if (Double.isNaN(minX) || Double.isNaN(minY) || Double.isNaN(maxX) || Double.isNaN(maxY)) {
            Logger.getLogger(this.getClass().getName()).warning("Union with invalid clip region: " + clip);
            return;
        }
        if (this.status == 0) {
            this.setClip(clip);
            this.status = 1;
            return;
        }
        this.clip[0] = Math.min(this.clip[0], minX);
        this.clip[1] = Math.min(this.clip[1], minY);
        this.clip[6] = Math.max(this.clip[6], maxX);
        this.clip[7] = Math.max(this.clip[7], maxY);
    }
    
    public void union(final double n, final double n2, final double n3, final double n4) {
        if (this.status == 2) {
            return;
        }
        if (this.status == 0) {
            this.setClip(n, n2, n3, n4);
            this.status = 1;
            return;
        }
        this.clip[0] = Math.min(this.clip[0], n);
        this.clip[1] = Math.min(this.clip[1], n2);
        this.clip[6] = Math.max(this.clip[6], n + n3);
        this.clip[7] = Math.max(this.clip[7], n2 + n4);
    }
    
    public void intersection(final Clip clip) {
        if (this.status == 2) {
            return;
        }
        if (this.status == 0) {
            this.setClip(clip);
            this.status = 1;
            return;
        }
        this.clip[0] = Math.max(this.clip[0], clip.clip[0]);
        this.clip[1] = Math.max(this.clip[1], clip.clip[1]);
        this.clip[6] = Math.min(this.clip[6], clip.clip[6]);
        this.clip[7] = Math.min(this.clip[7], clip.clip[7]);
    }
    
    public void intersection(final Rectangle2D clip) {
        if (this.status == 2) {
            return;
        }
        if (this.status == 0) {
            this.setClip(clip);
            this.status = 1;
            return;
        }
        this.clip[0] = Math.max(this.clip[0], clip.getMinX());
        this.clip[1] = Math.max(this.clip[1], clip.getMinY());
        this.clip[6] = Math.min(this.clip[6], clip.getMaxX());
        this.clip[7] = Math.min(this.clip[7], clip.getMaxY());
    }
    
    public void intersection(final double n, final double n2, final double n3, final double n4) {
        if (this.status == 2) {
            return;
        }
        if (this.status == 0) {
            this.setClip(n, n2, n3, n4);
            this.status = 1;
            return;
        }
        this.clip[0] = Math.max(this.clip[0], n);
        this.clip[1] = Math.max(this.clip[1], n2);
        this.clip[6] = Math.min(this.clip[6], n + n3);
        this.clip[7] = Math.min(this.clip[7], n2 + n4);
    }
    
    public void expandToIntegerLimits() {
        this.clip[0] = Math.floor(this.clip[0]);
        this.clip[1] = Math.floor(this.clip[1]);
        this.clip[6] = Math.ceil(this.clip[6]);
        this.clip[7] = Math.ceil(this.clip[7]);
    }
    
    public void expand(final double n) {
        final double[] clip = this.clip;
        final int n2 = 0;
        clip[n2] -= n;
        final double[] clip2 = this.clip;
        final int n3 = 1;
        clip2[n3] -= n;
        final double[] clip3 = this.clip;
        final int n4 = 6;
        clip3[n4] += n;
        final double[] clip4 = this.clip;
        final int n5 = 7;
        clip4[n5] += n;
    }
    
    public void grow(final double n) {
        final double[] clip = this.clip;
        final int n2 = 6;
        clip[n2] += n;
        final double[] clip2 = this.clip;
        final int n3 = 7;
        clip2[n3] += n;
    }
    
    public double getMinX() {
        return this.clip[0];
    }
    
    public double getMinY() {
        return this.clip[1];
    }
    
    public double getMaxX() {
        return this.clip[6];
    }
    
    public double getMaxY() {
        return this.clip[7];
    }
    
    public double getWidth() {
        return this.clip[6] - this.clip[0];
    }
    
    public double getHeight() {
        return this.clip[7] - this.clip[1];
    }
    
    public boolean isEmpty() {
        return this.status == 0;
    }
    
    public boolean isInvalid() {
        return this.status == 2;
    }
    
    public boolean equals(final Object o) {
        if (o instanceof Rectangle2D) {
            final Rectangle2D rectangle2D = (Rectangle2D)o;
            return rectangle2D.getMinX() == this.clip[0] && rectangle2D.getMinY() == this.clip[1] && rectangle2D.getMaxX() == this.clip[6] && rectangle2D.getMaxY() == this.clip[7];
        }
        if (o instanceof Clip) {
            final Clip clip = (Clip)o;
            return clip.status == this.status && (this.status != 1 || (clip.clip[0] == this.clip[0] && clip.clip[1] == this.clip[1] && clip.clip[6] == this.clip[6] && clip.clip[7] == this.clip[7]));
        }
        return false;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(20);
        sb.append("Clip[");
        switch (this.status) {
            case 2: {
                sb.append("invalid");
                break;
            }
            case 0: {
                sb.append("empty");
                break;
            }
            default: {
                sb.append(this.clip[0]).append(",");
                sb.append(this.clip[1]).append(",");
                sb.append(this.clip[6]).append(",");
                sb.append(this.clip[7]);
                break;
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
