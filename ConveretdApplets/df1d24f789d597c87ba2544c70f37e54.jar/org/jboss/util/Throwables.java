// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

public final class Throwables
{
    public static String toString(final Throwable t) {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final PrintStream stream = new PrintStream(output);
        t.printStackTrace(stream);
        return output.toString();
    }
}
