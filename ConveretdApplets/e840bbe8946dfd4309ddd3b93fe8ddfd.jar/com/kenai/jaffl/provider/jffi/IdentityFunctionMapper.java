// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

import com.kenai.jaffl.mapper.FunctionMapper;

public class IdentityFunctionMapper implements FunctionMapper
{
    public static FunctionMapper getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    public String mapFunctionName(final String functionName, final Context context) {
        return functionName;
    }
    
    private static final class SingletonHolder
    {
        public static final FunctionMapper INSTANCE;
        
        static {
            INSTANCE = new IdentityFunctionMapper();
        }
    }
}
