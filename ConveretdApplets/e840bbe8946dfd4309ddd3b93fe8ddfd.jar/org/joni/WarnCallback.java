// 
// Decompiled by Procyon v0.5.30
// 

package org.joni;

public interface WarnCallback
{
    public static final WarnCallback DEFAULT = new WarnCallback() {
        public void warn(final String message) {
            System.err.println(message);
        }
    };
    
    void warn(final String p0);
}
