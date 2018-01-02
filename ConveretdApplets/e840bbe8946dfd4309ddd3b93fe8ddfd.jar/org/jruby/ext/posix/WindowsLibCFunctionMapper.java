// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import java.util.HashMap;
import java.util.Map;
import com.kenai.jaffl.mapper.FunctionMapper;

final class WindowsLibCFunctionMapper implements FunctionMapper
{
    static final FunctionMapper INSTANCE;
    private final Map<String, String> methodNameMap;
    
    WindowsLibCFunctionMapper() {
        (this.methodNameMap = new HashMap<String, String>()).put("getpid", "_getpid");
        this.methodNameMap.put("chmod", "_chmod");
        this.methodNameMap.put("fstat", "_fstat64");
        this.methodNameMap.put("stat", "_stat64");
        this.methodNameMap.put("umask", "_umask");
        this.methodNameMap.put("isatty", "_isatty");
        this.methodNameMap.put("read", "_read");
        this.methodNameMap.put("write", "_write");
        this.methodNameMap.put("close", "_close");
    }
    
    public String mapFunctionName(final String originalName, final Context context) {
        final String name = this.methodNameMap.get(originalName);
        return (name != null) ? name : originalName;
    }
    
    static {
        INSTANCE = new WindowsLibCFunctionMapper();
    }
}
