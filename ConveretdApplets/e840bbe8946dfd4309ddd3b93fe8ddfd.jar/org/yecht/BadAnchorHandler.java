// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht;

public interface BadAnchorHandler
{
    Node handle(final Parser p0, final String p1);
    
    public static class Default implements BadAnchorHandler
    {
        public Node handle(final Parser p, final String anchor) {
            System.err.println("Bad anchor at [Line " + p.linect + ", Col " + (p.cursor - p.lineptr) + "]: " + anchor);
            return null;
        }
    }
}
