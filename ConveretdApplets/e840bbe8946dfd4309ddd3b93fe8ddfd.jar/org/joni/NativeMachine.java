// 
// Decompiled by Procyon v0.5.30
// 

package org.joni;

public abstract class NativeMachine extends Matcher
{
    protected NativeMachine(final Regex regex, final byte[] bytes, final int p, final int end) {
        super(regex, bytes, p, end);
    }
}
