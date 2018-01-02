// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.c;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import cue.lang.stop.StopWords;
import wordle.core.f;
import wordle.core.z;
import wordle.core.fitness.OverallShape;
import java.awt.Font;
import java.util.Set;

public final class e
{
    private static Set j;
    public final Font a;
    public final Class b;
    public final OverallShape c;
    public final z d;
    public final int e;
    public final f f;
    public final boolean g;
    public final StopWords h;
    public final Set i;
    
    static {
        e.j = Collections.unmodifiableSet((Set<?>)new HashSet<Object>());
    }
    
    public e(final Font font, final z z, final Class clazz, final OverallShape overallShape, final StopWords stopWords, final int n) {
        this(font, z, clazz, overallShape, 150, stopWords, wordle.core.c.e.j, null, true);
    }
    
    private e(final Font a, final z d, final Class b, final OverallShape c, final int e, final StopWords h, final Set set, final f f, final boolean g) {
        this.a = a;
        this.d = d;
        this.c = c;
        this.b = b;
        this.e = e;
        if (set == null) {
            this.i = Collections.emptySet();
        }
        else {
            this.i = Collections.unmodifiableSet((Set<?>)set);
        }
        this.f = f;
        this.h = h;
        this.g = g;
    }
    
    public final e a(final Font font) {
        return new e(font, this.d, this.b, this.c, this.e, this.h, this.i, this.f, this.g);
    }
    
    public final e a(final z z) {
        return new e(this.a, z, this.b, this.c, this.e, this.h, this.i, this.f, this.g);
    }
    
    public final e a(final int n) {
        return new e(this.a, this.d, this.b, this.c, n, this.h, this.i, this.f, this.g);
    }
    
    public final e a(final Class clazz) {
        return new e(this.a, this.d, clazz, this.c, this.e, this.h, this.i, this.f, this.g);
    }
    
    public final e a(final String s) {
        final HashSet set;
        (set = new HashSet(this.i)).add(s);
        return new e(this.a, this.d, this.b, this.c, this.e, this.h, set, this.f, this.g);
    }
    
    public final e a(final f f) {
        return new e(this.a, this.d, this.b, this.c, this.e, this.h, this.i, f, this.g);
    }
    
    public final e a(final StopWords stopWords) {
        return new e(this.a, this.d, this.b, this.c, this.e, stopWords, this.i, this.f, this.g);
    }
    
    public final e a(final OverallShape overallShape) {
        return new e(this.a, this.d, this.b, overallShape, this.e, this.h, this.i, this.f, this.g);
    }
    
    public final e a(final boolean b) {
        return new e(this.a, this.d, this.b, this.c, this.e, this.h, this.i, this.f, b);
    }
    
    public final String toString() {
        final StringBuilder sb;
        (sb = new StringBuilder()).append("LayoutSettings ( ").append(super.toString()).append("\n\t").append("font = ").append(this.a).append("\n\t").append("fitnessClass = ").append(this.b).append("\n\t").append("angleStrategy = ").append(this.d).append("\n\t").append("maxObjects = ").append(this.e).append("\n\t").append("bannedWords = ").append(this.i).append("\n\t").append("\n\t").append("stripDigits = ").append(this.g).append(" )");
        return sb.toString();
    }
}
