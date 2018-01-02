// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.image.RGBImageFilter;

public class b extends RGBImageFilter
{
    int a;
    int b;
    int c;
    int d;
    
    public int filterRGB(final int n, final int n2, final int n3) {
        final int n4 = n3 >> 16 & 0xFF;
        final int n5 = n3 >> 8 & 0xFF;
        final int n6 = n3 & 0xFF;
        int n7 = (255 - this.d) * n4 + this.d * this.a >> 8;
        int n8 = (255 - this.d) * n5 + this.d * this.b >> 8;
        int n9 = (255 - this.d) * n6 + this.d * this.c >> 8;
        if (n7 > 255) {
            n7 = 255;
        }
        if (n8 > 255) {
            n8 = 255;
        }
        if (n9 > 255) {
            n9 = 255;
        }
        return (n3 & 0xFF000000) | n7 << 16 | n8 << 8 | n9;
    }
    
    public b(final int d, final int a, final int b, final int c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
}
