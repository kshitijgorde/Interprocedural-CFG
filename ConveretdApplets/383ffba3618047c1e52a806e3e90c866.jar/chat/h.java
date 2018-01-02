// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Color;

public final class h implements av
{
    private bl a;
    
    public final void a(final Object o, final Object o2) {
        try {
            this.a.a((ad)o, o2.toString());
        }
        catch (Exception ex) {}
    }
    
    public h(final bl a) {
        this.a = a;
    }
    
    public static Color a(final String s) {
        return new Color(Integer.parseInt(s, 16));
    }
    
    public static int a(float n, float n2, final float n3) {
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        if (n2 == 0.0f) {
            n5 = (n4 = (n6 = (int)(n3 * 255.0f + 0.5f)));
        }
        else {
            final float n7 = n = (n - (float)Math.floor(n)) * 6.0f;
            final float n8 = n7 - (float)Math.floor(n7);
            final float n9 = n3 * (1.0f - n2);
            final float n10 = n3 * (1.0f - n2 * n8);
            n2 = n3 * (1.0f - n2 * (1.0f - n8));
            switch ((int)n) {
                case 0: {
                    n4 = (int)(n3 * 255.0f + 0.5f);
                    n5 = (int)(n2 * 255.0f + 0.5f);
                    n6 = (int)(n9 * 255.0f + 0.5f);
                    break;
                }
                case 1: {
                    n4 = (int)(n10 * 255.0f + 0.5f);
                    n5 = (int)(n3 * 255.0f + 0.5f);
                    n6 = (int)(n9 * 255.0f + 0.5f);
                    break;
                }
                case 2: {
                    n4 = (int)(n9 * 255.0f + 0.5f);
                    n5 = (int)(n3 * 255.0f + 0.5f);
                    n6 = (int)(n2 * 255.0f + 0.5f);
                    break;
                }
                case 3: {
                    n4 = (int)(n9 * 255.0f + 0.5f);
                    n5 = (int)(n10 * 255.0f + 0.5f);
                    n6 = (int)(n3 * 255.0f + 0.5f);
                    break;
                }
                case 4: {
                    n4 = (int)(n2 * 255.0f + 0.5f);
                    n5 = (int)(n9 * 255.0f + 0.5f);
                    n6 = (int)(n3 * 255.0f + 0.5f);
                    break;
                }
                case 5: {
                    n4 = (int)(n3 * 255.0f + 0.5f);
                    n5 = (int)(n9 * 255.0f + 0.5f);
                    n6 = (int)(n10 * 255.0f + 0.5f);
                    break;
                }
            }
        }
        return 0xFF000000 | n4 << 16 | n5 << 8 | n6;
    }
    
    public static int a(final int n) {
        if (n > 255) {
            return 255;
        }
        if (n < 0) {
            return 0;
        }
        return n;
    }
    
    public h() {
    }
}
