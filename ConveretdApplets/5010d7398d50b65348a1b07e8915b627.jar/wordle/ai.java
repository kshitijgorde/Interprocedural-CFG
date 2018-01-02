// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import java.util.concurrent.Executor;

public final class ai
{
    private final Executor a;
    
    public ai(final Executor a) {
        this.a = a;
    }
    
    public final P a(final String s, final Runnable runnable) {
        return new P(s, this.a, runnable);
    }
}
