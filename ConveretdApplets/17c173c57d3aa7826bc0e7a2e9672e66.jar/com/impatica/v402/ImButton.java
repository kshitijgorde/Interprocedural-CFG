// 
// Decompiled by Procyon v0.5.30
// 

package com.impatica.v402;

public class ImButton extends ImRect
{
    boolean I;
    ImButton Z;
    boolean C;
    
    static final void I(ImButton i, final ImIrgb imIrgb, final ImRect imRect) {
        i = I(i, 0);
        if (i != null && imIrgb.I(imRect, i)) {
            imIrgb.Z(i.L, i.N, i.M, i.O);
        }
    }
    
    static final void I(ImButton imButton) {
        while (imButton != null) {
            final ImButton z = imButton.Z;
            imButton.I = false;
            imButton = z;
        }
    }
    
    static final ImButton I(final ImButton imButton, final int n) {
        ImButton imButton2 = imButton;
        ImButton imButton3 = null;
        ImButton imButton4 = null;
        while (imButton2 != null) {
            if (imButton2.I) {
                imButton4 = imButton3;
                imButton3 = imButton2;
                if (imButton2.C) {
                    break;
                }
            }
            else {
                imButton2.C = false;
            }
            imButton2 = imButton2.Z;
        }
        if (imButton2 == null) {
            if (imButton3 != null) {
                imButton3.C = true;
            }
            return imButton3;
        }
        if (!imButton2.C) {
            imButton2.C = true;
            return imButton2;
        }
        if (n == 0) {
            return imButton2;
        }
        if (n < 0) {
            imButton3.C = false;
            if (imButton4 == null) {
                while (imButton2 != null) {
                    if (imButton2.I) {
                        imButton4 = imButton2;
                    }
                    imButton2 = imButton2.Z;
                }
                if (imButton4 == null) {
                    imButton4 = imButton3;
                }
            }
            imButton4.C = true;
            return imButton4;
        }
        imButton3.C = false;
        ImButton imButton5 = null;
        for (ImButton imButton6 = imButton2.Z; imButton6 != null; imButton6 = imButton6.Z) {
            if (imButton6.I) {
                imButton5 = imButton6;
                break;
            }
        }
        if (imButton5 == null) {
            for (ImButton z = imButton; z != null; z = z.Z) {
                if (z.I) {
                    imButton5 = z;
                    break;
                }
            }
        }
        imButton5.C = true;
        return imButton5;
    }
}
