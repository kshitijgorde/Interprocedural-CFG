// 
// Decompiled by Procyon v0.5.30
// 

package cue.lang;

import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class d extends b
{
    private static final Pattern a;
    private final Matcher b;
    private boolean c;
    
    static {
        a = Pattern.compile("[@+\\p{javaLetterOrDigit}]+([-.:/'\u2019\\p{M}\\u2032\\u00A0\\u200C\\u200D~]+[@+\\p{javaLetterOrDigit}]+)*");
    }
    
    public d(final String s) {
        this.b = d.a.matcher((s == null) ? "" : s);
        this.c = this.b.find();
    }
    
    public final void remove() {
        throw new UnsupportedOperationException();
    }
    
    public final boolean hasNext() {
        return this.c;
    }
}
