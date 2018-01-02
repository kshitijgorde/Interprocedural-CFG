// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.resources;

import java.util.ListResourceBundle;

public class JCommonResources extends ListResourceBundle
{
    private static final Object[][] CONTENTS;
    
    static {
        CONTENTS = new Object[][] { { "project.name", "JCommon" }, { "project.version", "1.0.10" }, { "project.info", "http://www.jfree.org/jcommon/" }, { "project.copyright", "(C)opyright 2000-2007, by Object Refinery Limited and Contributors" } };
    }
    
    public Object[][] getContents() {
        return JCommonResources.CONTENTS;
    }
}
