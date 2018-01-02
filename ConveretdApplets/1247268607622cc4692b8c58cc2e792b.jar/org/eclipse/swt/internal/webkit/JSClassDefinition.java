// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.webkit;

public class JSClassDefinition
{
    public int version;
    public int attributes;
    public int className;
    public int parentClass;
    public int staticValues;
    public int staticFunctions;
    public int initialize;
    public int finalize;
    public int hasProperty;
    public int getProperty;
    public int setProperty;
    public int deleteProperty;
    public int getPropertyNames;
    public int callAsFunction;
    public int callAsConstructor;
    public int hasInstance;
    public int convertToType;
    public static final int sizeof;
    
    static {
        sizeof = WebKit_win32.JSClassDefinition_sizeof();
    }
}
