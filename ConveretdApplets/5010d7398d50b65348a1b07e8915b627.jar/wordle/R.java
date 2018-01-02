// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import javax.swing.AbstractButton;
import wordle.core.c.d;
import javax.swing.Action;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import java.net.URL;
import java.io.InputStream;
import wordle.core.w;
import java.util.zip.GZIPInputStream;
import java.net.HttpURLConnection;
import javax.swing.JMenuItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Arrays;
import wordle.core.b.h;
import java.util.HashMap;
import java.util.Collections;
import java.util.HashSet;
import wordle.core.c.a;
import java.util.Map;
import java.util.Set;
import java.awt.Font;

public final class R
{
    private static final Font a;
    private static final Set b;
    private static final Map c;
    private static final Map d;
    private static final Map e;
    private static final Map f;
    private final WordleApplet g;
    private final a h;
    private final ai i;
    private final Map j;
    private Character.UnicodeBlock k;
    private String l;
    private static final Set m;
    
    static {
        a = new Font("Lucida Sans", 0, 1);
        (b = new HashSet()).add("sexsmith");
        R.b.add("owned");
        c = Collections.unmodifiableMap((Map<?, ?>)new Q());
        d = Collections.unmodifiableMap((Map<?, ?>)new N());
        (e = new HashMap()).put(Character.UnicodeBlock.BASIC_LATIN, a("grilledcheese", "owned", "teen", "berylium", "telephoto", "coolvetica", "gnuolane", "steelfish", "kenyan", "king", "goudy", "duality", "sexsmith", "mailrays", "exprswy_free", "powell", "fridge", "tanklite", "melochergbold", "vigo", "leaguegothic", "chunkfive", "enamel", "jblack"));
        R.e.put(Character.UnicodeBlock.ARABIC, a("scheherazade", "IranNastaliq"));
        R.e.put(Character.UnicodeBlock.ARMENIAN, a(new String[] { "damase" }));
        R.e.put(Character.UnicodeBlock.HEBREW, a(new String[] { "sbl-hebrew" }));
        R.e.put(Character.UnicodeBlock.GREEK, a("BPreplayBold", "damase", "gentium"));
        R.e.put(Character.UnicodeBlock.CYRILLIC, a("exprswy_free", "damase", "mailrays", "gentium"));
        R.e.put(Character.UnicodeBlock.DEVANAGARI, a(new String[] { "lucidasans" }));
        R.e.put(Character.UnicodeBlock.LATIN_1_SUPPLEMENT, a("grilledcheese", "berylium", "teen", "gnuolane", "steelfish", "mailrays", "exprswy_free", "owned", "melochergbold", "enamel", "jblack"));
        R.e.put(Character.UnicodeBlock.LATIN_EXTENDED_A, a("mailrays", "berylium", "gentium", "teen", "steelfish", "melochergbold", "owned", "enamel"));
        R.e.put(Character.UnicodeBlock.LATIN_EXTENDED_B, a(new String[] { "gentium" }));
        R.e.put(Character.UnicodeBlock.IPA_EXTENSIONS, a(new String[] { "gentium" }));
        R.e.put(Character.UnicodeBlock.COMBINING_DIACRITICAL_MARKS, a(new String[] { "gentium" }));
        R.e.put(Character.UnicodeBlock.SPACING_MODIFIER_LETTERS, a(new String[] { "gentium" }));
        R.e.put(Character.UnicodeBlock.KATAKANA, a(new String[] { "chrysuni" }));
        R.e.put(Character.UnicodeBlock.HIRAGANA, a(new String[] { "chrysuni" }));
        final HashSet set = new HashSet(R.d.values());
        final Iterator<Map.Entry<K, Set>> iterator = R.e.entrySet().iterator();
        while (iterator.hasNext()) {
            Object o;
            for (final String s : ((Map.Entry<Object, Set>)(o = iterator.next())).getValue()) {
                if (!set.contains(s)) {
                    System.err.println(((Map.Entry<Object, V>)o).getKey() + " contains bogus font " + s);
                }
            }
        }
        f = new HashMap();
        m = new HashSet();
        final h h = new h();
        char c2 = '\ue000';
        for (final String s2 : Arrays.asList("AA", "BB", "CJ", "CS", "CX", "CZ", "DD", "EE", "EJ", "ES", "EX", "EZ", "FF", "Fr", "GG", "KE", "KS", "LE", "LL", "ME", "MI", "MM", "MME", "MMI", "MP", "NN", "OO", "Or", "PP", "RA", "RE", "RR", "RRA", "RRE", "RRS", "RS", "RT", "RZ", "SS", "Ti", "Tr", "TT", "ZZ", "aa", "ar", "arr", "art", "bb", "cc", "cci", "ci", "cit", "citi", "dd", "ee", "fj", "gg", "ggs", "gs", "it", "iti", "itt", "itti", "la", "le", "ll", "mm", "nn", "oo", "oor", "or", "pp", "pr", "ps", "re", "rr", "rt", "ss", "ti", "tit", "titt", "titti", "tt", "tti", "tto", "ww", "ys", "zz", "00", "11", "22", "33", "44", "55", "66", "77", "88", "99", "!!")) {
            final h h2 = h;
            final String s3 = s2;
            final char c3 = c2;
            c2 = (char)(c3 + '\u0001');
            h2.a(s3, String.valueOf(c3));
        }
        h.a("ff", "\ufb00");
        wordle.core.b.h.a("EnamelBrush-Regular", h);
    }
    
    private static Set a(final String... array) {
        return new HashSet(Arrays.asList(array));
    }
    
    public R(final WordleApplet g, final a h, final ai i) {
        this.j = new HashMap();
        this.k = null;
        this.l = null;
        R.m.add(Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS);
        R.m.add(Character.UnicodeBlock.LATIN_EXTENDED_B);
        R.m.add(Character.UnicodeBlock.COMBINING_DIACRITICAL_MARKS);
        R.m.add(Character.UnicodeBlock.SPACING_MODIFIER_LETTERS);
        R.m.add(Character.UnicodeBlock.IPA_EXTENSIONS);
        this.g = g;
        this.h = h;
        this.i = i;
        this.k = Character.UnicodeBlock.BASIC_LATIN;
        this.a();
    }
    
    public final void a() {
        final Set c = this.c();
        this.l = new ArrayList<String>(c).get((int)(Math.random() * c.size()));
    }
    
    public final void a(final Character.UnicodeBlock k) {
        this.k = k;
        final Set c = this.c();
        final Iterator<Map.Entry<K, JMenuItem>> iterator = this.j.entrySet().iterator();
        while (iterator.hasNext()) {
            final Object o;
            ((Map.Entry<K, JMenuItem>)(o = iterator.next())).getValue().setFont(((Map.Entry<K, JMenuItem>)o).getValue().getFont().deriveFont((int)(c.contains(((Map.Entry)o).getKey()) ? 1 : 0)));
        }
        if (!R.m.contains(k)) {
            this.j.get("lucidasans").setFont(this.j.get("lucidasans").getFont().deriveFont(1));
        }
    }
    
    private Set c() {
        Set<String> singleton;
        if (!R.e.containsKey(this.k)) {
            System.err.println("No fonts for " + this.k + "; trusting to " + R.a);
            singleton = Collections.singleton("lucidasans");
        }
        else {
            singleton = R.e.get(this.k);
        }
        return singleton;
    }
    
    public final Font b() {
        return this.d(this.l);
    }
    
    private static String b(final String s) {
        final String s2;
        if ((s2 = R.d.get(s)) == null) {
            throw new RuntimeException("No font in the map with name <<" + s + ">>");
        }
        return s2;
    }
    
    public final Font a(final String s) {
        return this.d(c(s));
    }
    
    private static String c(final String s) {
        final String replaceAll = s.replace(" ", "").toLowerCase().replaceAll("-regular$", "").replaceAll("-", "");
        final String s2;
        if ((s2 = R.c.get(replaceAll)) == null) {
            System.err.println("No such fontname <" + s + "> with canonical rep <" + replaceAll + ">");
        }
        return s2;
    }
    
    private Font d(final String s) {
        synchronized (R.f) {
            if (!R.f.containsKey(s)) {
                R.f.put(s, this.e(s));
            }
            // monitorexit(R.f)
            return R.f.get(s);
        }
    }
    
    private Font e(final String s) {
        if (s == null || s.equals("lucidasans")) {
            return R.a;
        }
        try {
            return this.a(s, true);
        }
        catch (Throwable t) {
            a(s, t);
            System.err.println("Trying without caches.");
            try {
                return this.a(s, false);
            }
            catch (Throwable t2) {
                a(s, t2);
                return R.a;
            }
        }
    }
    
    private static void a(final String s, Throwable cause) {
        System.err.println("Couldn't load " + s);
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        System.err.println(cause);
    }
    
    private Font a(final String s, final boolean b) {
        final URL b2;
        final HttpURLConnection httpURLConnection;
        (httpURLConnection = (HttpURLConnection)(b2 = this.g.b("/fonts/" + s + ".ttf.gz")).openConnection()).setDefaultUseCaches(b);
        httpURLConnection.setUseCaches(b);
        if (!b) {
            System.err.println(b2);
            System.err.println(httpURLConnection.getResponseCode());
            System.err.println(httpURLConnection.getResponseMessage());
        }
        return w.a(new GZIPInputStream(httpURLConnection.getInputStream()));
    }
    
    public final JMenu a(final boolean b) {
        final JMenu menu;
        (menu = new JMenu("Font")).setMnemonic(70);
        final ButtonGroup buttonGroup = new ButtonGroup();
        for (final String s : R.d.keySet()) {
            if (b || !R.b.contains(R.d.get(s))) {
                final JMenu menu2 = menu;
                final ButtonGroup buttonGroup2 = buttonGroup;
                final String s2 = s;
                final ButtonGroup buttonGroup3 = buttonGroup2;
                final JMenu menu3 = menu2;
                final String b2 = b(s2);
                final JRadioButtonMenuItem radioButtonMenuItem = new JRadioButtonMenuItem(this.i.a(s2, new M(this, s2, b2)));
                this.j.put(b(s2), radioButtonMenuItem);
                this.h.a(new O(this, radioButtonMenuItem, b2));
                menu3.add(radioButtonMenuItem);
                buttonGroup3.add(radioButtonMenuItem);
                radioButtonMenuItem.setSelected(R.d.get(s).equals(this.l));
            }
        }
        return menu;
    }
}
