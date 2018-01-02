// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.util;

import java.io.Serializable;

public interface Configuration extends Serializable
{
    String getConfigProperty(final String p0);
    
    String getConfigProperty(final String p0, final String p1);
}
