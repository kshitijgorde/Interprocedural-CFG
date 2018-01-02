// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir;

public class CodeVersion
{
    private static long _nextVersionNumber;
    public final long _version;
    
    public static CodeVersion getClassVersionToken() {
        return new ClassCodeVersion();
    }
    
    public static CodeVersion getMethodVersionToken() {
        return new MethodCodeVersion();
    }
    
    protected CodeVersion(final long v) {
        this._version = v;
    }
    
    static {
        CodeVersion._nextVersionNumber = 0L;
    }
    
    static class ClassCodeVersion extends CodeVersion
    {
        private static long _nextVersionNumber;
        
        ClassCodeVersion() {
            super(ClassCodeVersion._nextVersionNumber + 1L);
            ++ClassCodeVersion._nextVersionNumber;
        }
        
        public String toString() {
            return "C_" + super.toString();
        }
        
        static {
            ClassCodeVersion._nextVersionNumber = 0L;
        }
    }
    
    static class MethodCodeVersion extends CodeVersion
    {
        private static long _nextVersionNumber;
        
        MethodCodeVersion() {
            super(MethodCodeVersion._nextVersionNumber + 1L);
            ++MethodCodeVersion._nextVersionNumber;
        }
        
        public String toString() {
            return "M_" + super.toString();
        }
        
        static {
            MethodCodeVersion._nextVersionNumber = 0L;
        }
    }
}
