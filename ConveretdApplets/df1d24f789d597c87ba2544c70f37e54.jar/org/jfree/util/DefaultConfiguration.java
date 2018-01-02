// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.util;

import java.util.Properties;

public class DefaultConfiguration extends Properties implements Configuration
{
    public String getConfigProperty(final String s) {
        return this.getProperty(s);
    }
    
    public String getConfigProperty(final String s, final String s2) {
        return this.getProperty(s, s2);
    }
}
