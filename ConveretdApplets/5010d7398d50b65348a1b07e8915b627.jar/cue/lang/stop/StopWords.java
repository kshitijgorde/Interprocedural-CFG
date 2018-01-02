// 
// Decompiled by Procyon v0.5.30
// 

package cue.lang.stop;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Collection;
import java.util.Set;

public enum StopWords
{
    c("Arabic", 0), 
    d("Armenian", 1), 
    e("Catalan", 2, true), 
    f("Croatian", 3), 
    g("Czech", 4), 
    h("Dutch", 5), 
    i("Danish", 6), 
    j("English", 7), 
    k("Esperanto", 8), 
    l("Farsi", 9), 
    m("Finnish", 10), 
    n("French", 11, true), 
    o("German", 12), 
    p("Greek", 13), 
    q("Hindi", 14), 
    r("Hungarian", 15), 
    s("Italian", 16), 
    t("Latin", 17), 
    u("Norwegian", 18), 
    v("Polish", 19), 
    w("Portuguese", 20), 
    x("Romanian", 21), 
    y("Russian", 22), 
    z("Slovenian", 23), 
    A("Slovak", 24), 
    B("Spanish", 25), 
    C("Swedish", 26), 
    D("Hebrew", 27), 
    E("Turkish", 28), 
    a("Custom", 29);
    
    public final boolean b;
    private final Set F;
    
    static {
        G = new StopWords[] { StopWords.c, StopWords.d, StopWords.e, StopWords.f, StopWords.g, StopWords.h, StopWords.i, StopWords.j, StopWords.k, StopWords.l, StopWords.m, StopWords.n, StopWords.o, StopWords.p, StopWords.q, StopWords.r, StopWords.s, StopWords.t, StopWords.u, StopWords.v, StopWords.w, StopWords.x, StopWords.y, StopWords.z, StopWords.A, StopWords.B, StopWords.C, StopWords.D, StopWords.E, StopWords.a };
    }
    
    public static StopWords a(final Collection collection) {
        StopWords stopWords = null;
        int n = 0;
        StopWords[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final StopWords stopWords2 = values[i];
            int n2 = 0;
            final Iterator<String> iterator = collection.iterator();
            while (iterator.hasNext()) {
                if (stopWords2.a(iterator.next())) {
                    ++n2;
                }
            }
            if (n2 > n) {
                stopWords = stopWords2;
                n = n2;
            }
        }
        return stopWords;
    }
    
    private StopWords(final String s, final int n) {
        this(s, n, false);
    }
    
    private StopWords(String lowerCase, final int n, final boolean b) {
        this.F = new HashSet();
        this.b = b;
        if (!(lowerCase = (this = this).name().toLowerCase(Locale.ENGLISH)).equals("custom")) {
            this.a(this.getClass().getResourceAsStream(lowerCase), Charset.forName("UTF-8"));
        }
    }
    
    public final boolean a(final String s) {
        return s.length() == 1 || this.F.contains(s.replace('\u2019', '\'').toLowerCase(Locale.ENGLISH));
    }
    
    private void a(InputStream inputStream, final Charset charset) {
        try {
            inputStream = (InputStream)new BufferedReader(new InputStreamReader(inputStream, charset));
            try {
                String line;
                while ((line = ((BufferedReader)inputStream).readLine()) != null) {
                    final String trim;
                    if ((trim = line.replaceAll("\\|.*", "").trim()).length() != 0) {
                        String[] split;
                        for (int length = (split = trim.split("\\s+")).length, i = 0; i < length; ++i) {
                            this.F.add(split[i].toLowerCase(Locale.ENGLISH));
                        }
                    }
                }
            }
            finally {
                ((BufferedReader)inputStream).close();
            }
            ((BufferedReader)inputStream).close();
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
