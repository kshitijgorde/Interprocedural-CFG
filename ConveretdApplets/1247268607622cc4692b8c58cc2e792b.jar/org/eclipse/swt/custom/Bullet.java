// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.SWT;

public class Bullet
{
    public int type;
    public StyleRange style;
    public String text;
    int[] linesIndices;
    int count;
    
    public Bullet(final StyleRange styleRange) {
        this(1, styleRange);
    }
    
    public Bullet(final int type, final StyleRange style) {
        if (style == null) {
            SWT.error(4);
        }
        if (style.metrics == null) {
            SWT.error(4);
        }
        this.type = type;
        this.style = style;
    }
    
    void addIndices(final int n, final int count) {
        if (this.linesIndices == null) {
            this.linesIndices = new int[count];
            this.count = count;
            for (int i = 0; i < count; ++i) {
                this.linesIndices[i] = n + i;
            }
        }
        else {
            int n2;
            for (n2 = 0; n2 < this.count && n > this.linesIndices[n2]; ++n2) {}
            int n3;
            for (n3 = n2; n3 < this.count && n + count > this.linesIndices[n3]; ++n3) {}
            final int count2 = n2 + count + this.count - n3;
            if (count2 > this.linesIndices.length) {
                final int[] linesIndices = new int[count2];
                System.arraycopy(this.linesIndices, 0, linesIndices, 0, this.count);
                this.linesIndices = linesIndices;
            }
            System.arraycopy(this.linesIndices, n3, this.linesIndices, n2 + count, this.count - n3);
            for (int j = 0; j < count; ++j) {
                this.linesIndices[n2 + j] = n + j;
            }
            this.count = count2;
        }
    }
    
    int indexOf(final int n) {
        for (int i = 0; i < this.count; ++i) {
            if (this.linesIndices[i] == n) {
                return i;
            }
        }
        return -1;
    }
    
    public int hashCode() {
        return this.style.hashCode() ^ this.type;
    }
    
    int[] removeIndices(final int n, final int n2, final int n3, final boolean b) {
        if (this.count == 0) {
            return null;
        }
        if (n > this.linesIndices[this.count - 1]) {
            return null;
        }
        final int n4 = n + n2;
        final int n5 = n3 - n2;
        for (int i = 0; i < this.count; ++i) {
            if (n <= this.linesIndices[i]) {
                int n6;
                for (n6 = i; n6 < this.count && this.linesIndices[n6] < n4; ++n6) {}
                if (b) {
                    for (int j = n6; j < this.count; ++j) {
                        final int[] linesIndices = this.linesIndices;
                        final int n7 = j;
                        linesIndices[n7] += n5;
                    }
                }
                final int[] array = new int[this.count - n6];
                System.arraycopy(this.linesIndices, n6, array, 0, this.count - n6);
                System.arraycopy(this.linesIndices, n6, this.linesIndices, i, this.count - n6);
                this.count -= n6 - i;
                return array;
            }
        }
        for (int k = 0; k < this.count; ++k) {
            final int[] linesIndices2 = this.linesIndices;
            final int n8 = k;
            linesIndices2[n8] += n5;
        }
        return null;
    }
    
    int size() {
        return this.count;
    }
}
