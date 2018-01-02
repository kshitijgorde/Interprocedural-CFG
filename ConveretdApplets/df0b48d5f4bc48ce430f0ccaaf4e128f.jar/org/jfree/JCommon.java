// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree;

import org.jfree.ui.about.ProjectInfo;

public final class JCommon
{
    public static final ProjectInfo INFO;
    
    static {
        INFO = JCommonInfo.getInstance();
    }
    
    public static void main(final String[] args) {
        System.out.println(JCommon.INFO.toString());
    }
}
