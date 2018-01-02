// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler.util;

import java.util.Stack;

public final class StringStack extends Stack
{
    public String peekString() {
        return super.peek();
    }
    
    public String popString() {
        return super.pop();
    }
    
    public String pushString(final String val) {
        return super.push(val);
    }
}
