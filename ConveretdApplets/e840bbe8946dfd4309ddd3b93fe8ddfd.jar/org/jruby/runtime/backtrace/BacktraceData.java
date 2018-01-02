// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.backtrace;

import java.util.List;
import org.jruby.util.JavaNameMangler;
import java.util.ArrayList;
import java.util.Map;
import org.jruby.Ruby;

public class BacktraceData
{
    private RubyStackTraceElement[] backtraceElements;
    private final StackTraceElement[] javaTrace;
    private final BacktraceElement[] rubyTrace;
    private final boolean fullTrace;
    private final boolean maskNative;
    private final TraceType.Gather gather;
    public static final BacktraceData EMPTY;
    
    public BacktraceData(final StackTraceElement[] javaTrace, final BacktraceElement[] rubyTrace, final boolean fullTrace, final boolean maskNative, final TraceType.Gather gather) {
        this.javaTrace = javaTrace;
        this.rubyTrace = rubyTrace;
        this.fullTrace = fullTrace;
        this.maskNative = maskNative;
        this.gather = gather;
    }
    
    public RubyStackTraceElement[] getBacktrace(final Ruby runtime) {
        if (this.backtraceElements == null) {
            this.backtraceElements = this.transformBacktrace(runtime.getBoundMethods());
        }
        return this.backtraceElements;
    }
    
    private RubyStackTraceElement[] transformBacktrace(final Map<String, String> boundMethods) {
        final List<RubyStackTraceElement> trace = new ArrayList<RubyStackTraceElement>(this.javaTrace.length);
        boolean dupFrame = false;
        String dupFrameName = null;
        int rubyFrameIndex = (this.rubyTrace == null) ? -1 : (this.rubyTrace.length - 1);
        if (this.javaTrace == null) {
            return null;
        }
        for (int i = 0; i < this.javaTrace.length; ++i) {
            StackTraceElement element = this.javaTrace[i];
            if (element.getFileName() != null && (element.getFileName().endsWith(".rb") || element.getFileName().equals("-e") || element.getClassName().startsWith("rubyjit.") || element.getMethodName().contains("$RUBY$") || element.getMethodName().contains("__file__"))) {
                if (element.getLineNumber() == -1) {
                    continue;
                }
                String methodName = element.getMethodName();
                final String className = element.getClassName();
                if (className.startsWith("rubyjit")) {
                    methodName = className.substring("rubyjit".length() + 1, className.lastIndexOf("_"));
                    methodName = JavaNameMangler.demangleMethodName(methodName);
                    final RubyStackTraceElement rubyElement = new RubyStackTraceElement(className, methodName, element.getFileName(), element.getLineNumber(), false);
                    if (this.maskNative && dupFrame) {
                        dupFrame = false;
                        trace.add(new RubyStackTraceElement(rubyElement.getClassName(), dupFrameName, rubyElement.getFileName(), rubyElement.getLineNumber(), rubyElement.isBinding()));
                    }
                    trace.add(rubyElement);
                    if (element.getMethodName().contains("$RUBY$SYNTHETIC")) {
                        while (element.getMethodName().indexOf("$RUBY$SYNTHETIC") != -1 && ++i < this.javaTrace.length) {
                            element = this.javaTrace[i];
                        }
                    }
                    continue;
                }
                else {
                    final int RUBYindex = methodName.indexOf("$RUBY$");
                    if (RUBYindex >= 0) {
                        methodName = methodName.substring(RUBYindex);
                        if (methodName.startsWith("$RUBY$SYNTHETIC")) {
                            methodName = methodName.substring("$RUBY$SYNTHETIC".length());
                            methodName = JavaNameMangler.demangleMethodName(methodName);
                            if (methodName.equals("__file__")) {
                                methodName = "(root)";
                            }
                            final RubyStackTraceElement rubyElement2 = new RubyStackTraceElement(className, methodName, element.getFileName(), element.getLineNumber(), false);
                            if (this.maskNative && dupFrame) {
                                dupFrame = false;
                                trace.add(new RubyStackTraceElement(rubyElement2.getClassName(), dupFrameName, rubyElement2.getFileName(), rubyElement2.getLineNumber(), rubyElement2.isBinding()));
                            }
                            trace.add(rubyElement2);
                            while (element.getMethodName().indexOf("$RUBY$SYNTHETIC") != -1 && ++i < this.javaTrace.length) {
                                element = this.javaTrace[i];
                            }
                            continue;
                        }
                        methodName = methodName.substring("$RUBY$".length());
                        methodName = JavaNameMangler.demangleMethodName(methodName);
                        final RubyStackTraceElement rubyElement2 = new RubyStackTraceElement(className, methodName, element.getFileName(), element.getLineNumber(), false);
                        if (this.maskNative && dupFrame) {
                            dupFrame = false;
                            trace.add(new RubyStackTraceElement(rubyElement2.getClassName(), dupFrameName, rubyElement2.getFileName(), rubyElement2.getLineNumber(), rubyElement2.isBinding()));
                        }
                        trace.add(rubyElement2);
                        continue;
                    }
                    else if (methodName.equals("__file__") && !element.getFileName().endsWith("AbstractScript.java")) {
                        methodName = "(root)";
                        final RubyStackTraceElement rubyElement2 = new RubyStackTraceElement(className, methodName, element.getFileName(), element.getLineNumber(), false);
                        if (this.maskNative && dupFrame) {
                            dupFrame = false;
                            trace.add(new RubyStackTraceElement(rubyElement2.getClassName(), dupFrameName, rubyElement2.getFileName(), rubyElement2.getLineNumber(), rubyElement2.isBinding()));
                        }
                        trace.add(rubyElement2);
                        continue;
                    }
                }
            }
            final String dotClassMethod = element.getClassName() + "." + element.getMethodName();
            String rubyName = null;
            if (this.fullTrace || (rubyName = boundMethods.get(dotClassMethod)) != null) {
                String filename = element.getFileName();
                if (filename == null) {
                    filename = element.getClassName().replaceAll("\\.", "/");
                }
                else {
                    final int lastDot = element.getClassName().lastIndexOf(46);
                    if (lastDot != -1) {
                        filename = element.getClassName().substring(0, lastDot + 1).replaceAll("\\.", "/") + filename;
                    }
                }
                if (this.maskNative) {
                    dupFrame = true;
                    dupFrameName = rubyName;
                    continue;
                }
                if (rubyName == null) {
                    rubyName = element.getMethodName();
                }
                trace.add(new RubyStackTraceElement(element.getClassName(), rubyName, filename, element.getLineNumber(), false));
                if (!this.fullTrace) {
                    continue;
                }
            }
            final String classMethod = element.getClassName() + "." + element.getMethodName();
            final FrameType frameType = FrameType.INTERPRETED_FRAMES.get(classMethod);
            if (frameType != null && rubyFrameIndex >= 0) {
                final BacktraceElement rubyFrame = this.rubyTrace[rubyFrameIndex];
                final RubyStackTraceElement rubyElement3 = new RubyStackTraceElement(rubyFrame.klass, rubyFrame.method, rubyFrame.filename, rubyFrame.line + 1, false);
                if (this.maskNative && dupFrame) {
                    dupFrame = false;
                    trace.add(new RubyStackTraceElement(rubyElement3.getClassName(), dupFrameName, rubyElement3.getFileName(), rubyElement3.getLineNumber(), rubyElement3.isBinding()));
                }
                trace.add(rubyElement3);
                --rubyFrameIndex;
            }
        }
        final RubyStackTraceElement[] rubyStackTrace = new RubyStackTraceElement[trace.size()];
        return trace.toArray(rubyStackTrace);
    }
    
    static {
        EMPTY = new BacktraceData(new StackTraceElement[0], new BacktraceElement[0], false, false, TraceType.Gather.NORMAL);
    }
}
