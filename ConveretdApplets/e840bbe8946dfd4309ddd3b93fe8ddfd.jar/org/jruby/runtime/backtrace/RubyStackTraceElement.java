// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.backtrace;

public class RubyStackTraceElement
{
    public static final RubyStackTraceElement[] EMPTY_ARRAY;
    private final StackTraceElement element;
    private final boolean binding;
    private final FrameType frameType;
    
    public RubyStackTraceElement(final StackTraceElement element) {
        this.element = element;
        this.binding = false;
        this.frameType = FrameType.METHOD;
    }
    
    public RubyStackTraceElement(final String cls, final String method, final String file, final int line, final boolean binding) {
        this(cls, method, file, line, binding, FrameType.METHOD);
    }
    
    public RubyStackTraceElement(final String cls, final String method, final String file, final int line, final boolean binding, final FrameType frameType) {
        this.element = new StackTraceElement(cls, method, file, line);
        this.binding = binding;
        this.frameType = frameType;
    }
    
    public StackTraceElement getElement() {
        return this.element;
    }
    
    public boolean isBinding() {
        return this.binding;
    }
    
    public String getClassName() {
        return this.element.getClassName();
    }
    
    public String getFileName() {
        return this.element.getFileName();
    }
    
    public int getLineNumber() {
        return this.element.getLineNumber();
    }
    
    public String getMethodName() {
        return this.element.getMethodName();
    }
    
    public FrameType getFrameType() {
        return this.frameType;
    }
    
    public String toString() {
        return this.element.toString();
    }
    
    static {
        EMPTY_ARRAY = new RubyStackTraceElement[0];
    }
}
