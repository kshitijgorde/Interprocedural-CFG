// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.lang.reflect.Array;
import org.jruby.runtime.builtin.IRubyObject;

public final class ArraysUtil
{
    public static IRubyObject[] copyOf(final IRubyObject[] source, final int length) {
        final IRubyObject[] destination = (IRubyObject[])Array.newInstance(IRubyObject.class, length);
        System.arraycopy(source, 0, destination, 0, Math.min(source.length, length));
        return destination;
    }
}
