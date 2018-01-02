// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import com.kenai.jaffl.mapper.FunctionMapper;

final class POSIXFunctionMapper implements FunctionMapper
{
    public static final FunctionMapper INSTANCE;
    
    public String mapFunctionName(String name, final Context ctx) {
        if (ctx.getLibrary().getName().equals("msvcrt") && (name.equals("getpid") || name.equals("chmod"))) {
            name = "_" + name;
        }
        return name;
    }
    
    static {
        INSTANCE = new POSIXFunctionMapper();
    }
}
