// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j;

import java.util.Enumeration;
import org.apache.log4j.helpers.LogLog;
import java.util.Vector;
import java.util.Stack;
import java.util.Hashtable;

public class NDC
{
    static Hashtable ht;
    static int pushCounter;
    static final int REAP_THRESHOLD = 5;
    
    public static void clear() {
        final Stack stack = NDC.ht.get(Thread.currentThread());
        if (stack != null) {
            stack.setSize(0);
        }
    }
    
    public static Stack cloneStack() {
        final Stack value = NDC.ht.get(Thread.currentThread());
        if (value == null) {
            return null;
        }
        return (Stack)value.clone();
    }
    
    public static void inherit(final Stack stack) {
        if (stack != null) {
            NDC.ht.put(Thread.currentThread(), stack);
        }
    }
    
    public static String get() {
        final Stack<DiagnosticContext> stack = NDC.ht.get(Thread.currentThread());
        if (stack != null && !stack.isEmpty()) {
            return stack.peek().fullMessage;
        }
        return null;
    }
    
    public static int getDepth() {
        final Stack stack = NDC.ht.get(Thread.currentThread());
        if (stack == null) {
            return 0;
        }
        return stack.size();
    }
    
    private static void lazyRemove() {
        final Vector vector;
        synchronized (NDC.ht) {
            if (++NDC.pushCounter <= 5) {
                return;
            }
            NDC.pushCounter = 0;
            int n = 0;
            vector = new Vector<Thread>();
            final Enumeration keys = NDC.ht.keys();
            while (keys.hasMoreElements() && n <= 4) {
                final Thread thread = keys.nextElement();
                if (thread.isAlive()) {
                    ++n;
                }
                else {
                    n = 0;
                    vector.addElement(thread);
                }
            }
        }
        for (int size = vector.size(), i = 0; i < size; ++i) {
            final Thread thread2 = vector.elementAt(i);
            LogLog.debug("Lazy NDC removal for thread [" + thread2.getName() + "] (" + NDC.ht.size() + ").");
            NDC.ht.remove(thread2);
        }
    }
    
    public static String pop() {
        final Stack<DiagnosticContext> stack = NDC.ht.get(Thread.currentThread());
        if (stack != null && !stack.isEmpty()) {
            return stack.pop().message;
        }
        return "";
    }
    
    public static String peek() {
        final Stack<DiagnosticContext> stack = NDC.ht.get(Thread.currentThread());
        if (stack != null && !stack.isEmpty()) {
            return stack.peek().message;
        }
        return "";
    }
    
    public static void push(final String s) {
        final Thread currentThread = Thread.currentThread();
        final Stack<DiagnosticContext> stack = NDC.ht.get(currentThread);
        if (stack == null) {
            final DiagnosticContext diagnosticContext = new DiagnosticContext(s, null);
            final Stack<DiagnosticContext> stack2 = new Stack<DiagnosticContext>();
            NDC.ht.put(currentThread, stack2);
            stack2.push(diagnosticContext);
        }
        else if (stack.isEmpty()) {
            stack.push(new DiagnosticContext(s, null));
        }
        else {
            stack.push(new DiagnosticContext(s, stack.peek()));
        }
    }
    
    public static void remove() {
        NDC.ht.remove(Thread.currentThread());
        lazyRemove();
    }
    
    public static void setMaxDepth(final int size) {
        final Stack stack = NDC.ht.get(Thread.currentThread());
        if (stack != null && size < stack.size()) {
            stack.setSize(size);
        }
    }
    
    static {
        NDC.ht = new Hashtable();
        NDC.pushCounter = 0;
    }
    
    private static class DiagnosticContext
    {
        String fullMessage;
        String message;
        
        DiagnosticContext(final String s, final DiagnosticContext diagnosticContext) {
            this.message = s;
            if (diagnosticContext != null) {
                this.fullMessage = diagnosticContext.fullMessage + ' ' + s;
            }
            else {
                this.fullMessage = s;
            }
        }
    }
}
