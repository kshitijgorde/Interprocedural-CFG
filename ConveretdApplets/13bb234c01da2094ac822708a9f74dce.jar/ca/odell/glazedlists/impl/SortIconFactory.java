// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl;

import java.util.HashMap;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.Icon;
import java.util.Map;

public final class SortIconFactory
{
    private static final Map a;
    private static Icon[] b;
    private static String[] c;
    
    private SortIconFactory() {
        throw new UnsupportedOperationException();
    }
    
    public static Icon[] a() {
        if (SortIconFactory.b != null) {
            return SortIconFactory.b;
        }
        String s = UIManager.getLookAndFeel().getName();
        if (s.equals("Metal")) {
            s = PLAFDetector.a();
        }
        else if (s.equals("Windows")) {
            s = PLAFDetector.b();
        }
        String s2 = SortIconFactory.a.get(s);
        if (s2 == null) {
            s2 = "aqua";
        }
        return SortIconFactory.b = a("resources/" + s2);
    }
    
    public static Icon[] a(final String s) {
        final ClassLoader classLoader = SortIconFactory.class.getClassLoader();
        final Icon[] array = new Icon[SortIconFactory.c.length];
        for (int i = 1; i < array.length; ++i) {
            final URL resource = classLoader.getResource(s + "/" + SortIconFactory.c[i]);
            if (resource != null) {
                array[i] = new ImageIcon(resource);
            }
        }
        return array;
    }
    
    static {
        (a = new HashMap()).put("Mac OS X Aqua", "aqua");
        SortIconFactory.a.put("Metal/Steel", "metal");
        SortIconFactory.a.put("Metal/Ocean", "ocean");
        SortIconFactory.a.put("Classic Windows", "windows");
        SortIconFactory.a.put("Windows XP", "windowsxp");
        SortIconFactory.a.put("Windows Vista", "windowsxp");
        SortIconFactory.a.put("WinLAF", "windowsxp");
        SortIconFactory.b = null;
        SortIconFactory.c = new String[] { "unsorted.png", "primary_sorted.png", "primary_sorted_reverse.png", "primary_sorted_alternate.png", "primary_sorted_alternate_reverse.png", "secondary_sorted.png", "secondary_sorted_reverse.png", "secondary_sorted_alternate.png", "secondary_sorted_alternate_reverse.png" };
    }
}
