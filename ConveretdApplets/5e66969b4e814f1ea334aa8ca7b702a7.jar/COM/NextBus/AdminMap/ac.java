// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import COM.NextBus.a.a;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.Collections;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Color;
import java.util.List;

public final class ac
{
    private volatile List a;
    private final O b;
    private static Color c;
    private static final Color d;
    private static final Font e;
    
    ac(final O b) {
        this.a = null;
        this.b = b;
    }
    
    private synchronized void a(final List a) {
        if (!x.a(this.a, a)) {
            this.a = a;
            this.b.f.b();
        }
    }
    
    final void a(String s) {
        s = s;
        final String[] array = new String[20];
        int n = 0;
        while (s.length() > 0) {
            if (s.length() <= 44) {
                array[n++] = s;
                break;
            }
            final int lastIndex;
            if ((lastIndex = s.substring(0, 44).lastIndexOf(32)) >= 0) {
                array[n++] = s.substring(0, lastIndex);
                s = s.substring(lastIndex + 1);
            }
            else {
                array[n++] = s.substring(0, 44);
                s = s.substring(44);
            }
        }
        final List<String> synchronizedList = Collections.synchronizedList(new ArrayList<String>());
        for (int i = 0; i < n; ++i) {
            synchronizedList.add(array[i]);
        }
        this.a(synchronizedList);
    }
    
    final void a() {
        this.a((List)null);
    }
    
    public final void a(final Graphics2D graphics2D, final Dimension dimension) {
        final List a;
        String[] array;
        if ((a = this.a) == null) {
            array = null;
        }
        else {
            array = new String[a.size()];
            for (int i = 0; i < a.size(); ++i) {
                array[i] = a.get(i);
            }
        }
        if (array != null && array.length > 0) {
            graphics2D.setFont(ac.e);
            final FontMetrics fontMetrics;
            final int maxAscent = (fontMetrics = graphics2D.getFontMetrics()).getMaxAscent();
            final int height = fontMetrics.getHeight();
            final int stringWidth = fontMetrics.stringWidth("x");
            int max = 0;
            int j = 0;
            for (int k = 0; k < array.length; ++k) {
                if (!array[k].startsWith("~")) {
                    max = Math.max(max, fontMetrics.stringWidth(array[k]));
                }
                else {
                    ++j;
                }
            }
            if (max < 1) {
                max = 100;
            }
            while (j > 0) {
                for (int n = j, n2 = 0; n2 < array.length && n == j; ++n2) {
                    if (array[n2].startsWith("~")) {
                        --j;
                        String s = array[n2].substring("~".length()).trim();
                        final List<String> synchronizedList = Collections.synchronizedList(new ArrayList<String>());
                        while (fontMetrics.stringWidth(s) > max) {
                            int n3 = 0;
                            final String s2 = s;
                            for (int n4 = 0; n4 < s.length() && s2 == s; ++n4) {
                                if (s.charAt(n4) == ' ') {
                                    n3 = n4;
                                }
                                if (fontMetrics.stringWidth(s.substring(0, n4 + 1)) > max) {
                                    if (n3 < 1) {
                                        n3 = n4 + 1;
                                    }
                                    synchronizedList.add(s.substring(0, n3));
                                    s = s.substring(n3);
                                }
                            }
                            s = s.trim();
                        }
                        synchronizedList.add(s);
                        if (synchronizedList.size() == 1) {
                            array[n2] = synchronizedList.get(0);
                        }
                        else {
                            final String[] array2 = new String[array.length + synchronizedList.size() - 1];
                            int n5 = 0;
                            for (int l = 0; l < n2; ++l) {
                                array2[n5++] = array[l];
                            }
                            for (int n6 = 0; n6 < synchronizedList.size(); ++n6) {
                                array2[n5++] = synchronizedList.get(n6);
                            }
                            for (int n7 = n2 + 1; n7 < array.length; ++n7) {
                                array2[n5++] = array[n7];
                            }
                            array = array2;
                        }
                    }
                }
            }
            final int n8 = max + (stringWidth << 2);
            final int n9 = height * array.length + height;
            final int n10 = (dimension.width - n8) / 2;
            final int n11 = (dimension.height - n9) / 2;
            final int n12 = height << 1;
            graphics2D.setColor(ac.c);
            graphics2D.fillRoundRect(n10 - 3, n11 - 3, n8 + 7, n9 + 7, n12 + 6, n12 + 6);
            graphics2D.setColor(ac.d);
            graphics2D.drawRoundRect(n10, n11, n8, n9, n12, n12);
            final int n13 = n10 + (stringWidth << 1);
            int n14 = n11 + (height / 2 + maxAscent);
            for (int n15 = 0; n15 < array.length; ++n15) {
                graphics2D.drawString(array[n15], n13, n14);
                n14 += height;
            }
        }
    }
    
    static {
        ac.c = new Color(a.a);
        d = Color.white;
        e = new Font("SansSerif", 0, 12);
    }
}
