// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler.util;

import org.apache.xalan.xsltc.compiler.Stylesheet;

public final class NodeSortRecordFactGenerator extends ClassGenerator
{
    public NodeSortRecordFactGenerator(final String className, final String superClassName, final String fileName, final int accessFlags, final String[] interfaces, final Stylesheet stylesheet) {
        super(className, superClassName, fileName, accessFlags, interfaces, stylesheet);
    }
    
    public boolean isExternal() {
        return true;
    }
}
