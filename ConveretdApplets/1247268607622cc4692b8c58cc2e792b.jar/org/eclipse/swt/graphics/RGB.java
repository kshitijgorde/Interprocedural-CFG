// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.graphics;

import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.SerializableCompatibility;

public final class RGB implements SerializableCompatibility
{
    public int red;
    public int green;
    public int blue;
    static final long serialVersionUID = 3258415023461249074L;
    
    public RGB(final int red, final int green, final int blue) {
        if (red > 255 || red < 0 || green > 255 || green < 0 || blue > 255 || blue < 0) {
            SWT.error(5);
        }
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    
    public RGB(float n, final float n2, final float n3) {
        if (n < 0.0f || n > 360.0f || n2 < 0.0f || n2 > 1.0f || n3 < 0.0f || n3 > 1.0f) {
            SWT.error(5);
        }
        float n4;
        float n5;
        float n6 = 0.0f;
        if (n2 == 0.0f) {
            n4 = n3;
            n5 = n3;
            n6 = n3;
        }
        else {
            if (n == 360.0f) {
                n = 0.0f;
            }
            n /= 60.0f;
            final int n7 = (int)n;
            final float n8 = n - n7;
            final float n9 = n3 * (1.0f - n2);
            final float n10 = n3 * (1.0f - n2 * n8);
            final float n11 = n3 * (1.0f - n2 * (1.0f - n8));
            switch (n7) {
                case 0: {
                    n6 = n3;
                    n5 = n11;
                    n4 = n9;
                    break;
                }
                case 1: {
                    n6 = n10;
                    n5 = n3;
                    n4 = n9;
                    break;
                }
                case 2: {
                    n6 = n9;
                    n5 = n3;
                    n4 = n11;
                    break;
                }
                case 3: {
                    n6 = n9;
                    n5 = n10;
                    n4 = n3;
                    break;
                }
                case 4: {
                    n6 = n11;
                    n5 = n9;
                    n4 = n3;
                    break;
                }
                default: {
                    n6 = n3;
                    n5 = n9;
                    n4 = n10;
                    break;
                }
            }
        }
        this.red = (int)(n6 * 255.0f + 0.5);
        this.green = (int)(n5 * 255.0f + 0.5);
        this.blue = (int)(n4 * 255.0f + 0.5);
    }
    
    public float[] getHSB() {
        final float n = this.red / 255.0f;
        final float n2 = this.green / 255.0f;
        final float n3 = this.blue / 255.0f;
        final float max = Math.max(Math.max(n, n2), n3);
        final float min = Math.min(Math.min(n, n2), n3);
        final float n4 = max - min;
        float n5 = 0.0f;
        final float n6 = max;
        final float n7 = (max == 0.0f) ? 0.0f : ((max - min) / max);
        if (n4 != 0.0f) {
            float n8;
            if (n == max) {
                n8 = (n2 - n3) / n4;
            }
            else if (n2 == max) {
                n8 = 2.0f + (n3 - n) / n4;
            }
            else {
                n8 = 4.0f + (n - n2) / n4;
            }
            n5 = n8 * 60.0f;
            if (n5 < 0.0f) {
                n5 += 360.0f;
            }
        }
        return new float[] { n5, n7, n6 };
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RGB)) {
            return false;
        }
        final RGB rgb = (RGB)o;
        return rgb.red == this.red && rgb.green == this.green && rgb.blue == this.blue;
    }
    
    public int hashCode() {
        return this.blue << 16 | this.green << 8 | this.red;
    }
    
    public String toString() {
        return "RGB {" + this.red + ", " + this.green + ", " + this.blue + "}";
    }
}
