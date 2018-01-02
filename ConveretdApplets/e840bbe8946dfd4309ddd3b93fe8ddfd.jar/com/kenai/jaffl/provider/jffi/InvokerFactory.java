// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

import com.kenai.jaffl.provider.Invoker;
import com.kenai.jaffl.LibraryOption;
import java.util.Map;
import com.kenai.jaffl.provider.Library;
import java.lang.reflect.Method;

public interface InvokerFactory
{
    Invoker createInvoker(final Method p0, final Library p1, final Map<LibraryOption, ?> p2);
    
    boolean isMethodSupported(final Method p0);
}
