// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht;

public interface ErrorHandler
{
    void handle(final Parser p0, final String p1);
    
    public static class Default implements ErrorHandler
    {
        public void handle(final Parser p, final String msg) {
            System.err.println("Error at [Line " + p.linect + ", Col " + (p.cursor - p.lineptr) + "]: " + msg);
        }
    }
}
