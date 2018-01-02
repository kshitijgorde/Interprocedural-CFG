// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.mapper;

import com.kenai.jaffl.Library;

public interface FunctionMapper
{
    String mapFunctionName(final String p0, final Context p1);
    
    public interface Context
    {
        Library getLibrary();
    }
}
