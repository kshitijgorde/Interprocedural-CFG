// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.c;

import wordle.core.K;

public final class f
{
    public final String a;
    public final e b;
    public final wordle.core.b.a.e c;
    public final K d;
    
    public f(final String a, final e b, final wordle.core.b.a.e c, final K d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    public final String toString() {
        final StringBuilder sb;
        (sb = new StringBuilder()).append("State ( ").append(super.toString()).append("\n    ").append("actionTaken = ").append(this.a).append("\n    ").append("layoutSettings = ").append(this.b).append("\n    ").append("colorStrategy = ").append(this.c).append("\n    ").append("drawables = ").append(this.d).append("\n    ").append(" )");
        return sb.toString();
    }
}
