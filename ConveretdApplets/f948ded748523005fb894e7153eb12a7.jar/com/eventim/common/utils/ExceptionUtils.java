// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.utils;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

public class ExceptionUtils
{
    public static String getExceptionStackTrace(final Exception e) {
        try {
            final ByteArrayOutputStream os = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(os));
            return os.toString();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return "Fehler in getExceptionStackTrace(): Konnte [" + e.toString() + "] nicht bearbeiten, Meldung: " + ex.toString();
        }
    }
}
