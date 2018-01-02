// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.profile;

import java.util.Iterator;
import org.jruby.util.collections.IntHashMap;
import java.util.HashMap;
import java.util.Map;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyClass;
import org.jruby.RubyObject;
import org.jruby.MetaClass;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.Ruby;
import java.text.DecimalFormat;
import org.jruby.RubyIO;
import java.io.PrintStream;

public class AbstractProfilePrinter
{
    public void printProfile(final PrintStream out) {
    }
    
    public void printProfile(final RubyIO out) {
        this.printProfile(new PrintStream(out.getOutStream()));
    }
    
    protected void pad(final PrintStream out, final int size, final String body) {
        this.pad(out, size, body, true);
    }
    
    protected void pad(final PrintStream out, final int size, final String body, final boolean front) {
        if (front) {
            for (int i = 0; i < size - body.length(); ++i) {
                out.print(' ');
            }
        }
        out.print(body);
        if (!front) {
            for (int i = 0; i < size - body.length(); ++i) {
                out.print(' ');
            }
        }
    }
    
    protected String nanoString(final long nanoTime) {
        final DecimalFormat formatter = new DecimalFormat("##0.00");
        return formatter.format(nanoTime / 1.0E9);
    }
    
    public boolean isProfilerInvocation(final Invocation inv) {
        return this.isThisProfilerInvocation(inv.getMethodSerialNumber()) || (inv.getParent() != null && this.isProfilerInvocation(inv.getParent()));
    }
    
    public boolean isThisProfilerInvocation(final int serial) {
        final String name = this.methodName(serial);
        return name.length() > 15 && (name.equals("JRuby::Profiler.start") || name.equals("JRuby::Profiler.stop"));
    }
    
    public String methodName(final int serial) {
        return getMethodName(serial);
    }
    
    public static String getMethodName(final int serial) {
        if (serial == 0) {
            return "(top)";
        }
        final Ruby runtime = Ruby.getGlobalRuntime();
        final String[] profiledNames = runtime.profiledNames;
        final DynamicMethod[] profiledMethods = runtime.profiledMethods;
        String displayName;
        if (serial < profiledNames.length) {
            final String name = profiledNames[serial];
            final DynamicMethod method = profiledMethods[serial];
            displayName = moduleHashMethod(method.getImplementationClass(), name);
        }
        else {
            displayName = "<unknown>";
        }
        return displayName;
    }
    
    protected static String moduleHashMethod(RubyModule module, final String name) {
        if (module instanceof MetaClass) {
            final IRubyObject obj = ((MetaClass)module).getAttached();
            if (obj instanceof RubyModule) {
                module = (RubyModule)obj;
                return module.getName() + "." + name;
            }
            if (obj instanceof RubyObject) {
                return ((RubyObject)obj).getType().getName() + "(singleton)#" + name;
            }
            return "unknown#" + name;
        }
        else {
            if (module.isSingleton()) {
                return ((RubyClass)module).getRealClass().getName() + "(singleton)#" + name;
            }
            return module.getName() + "#" + name;
        }
    }
    
    protected Map<Integer, MethodData> methodData(final Invocation top) {
        final Map<Integer, MethodData> methods = new HashMap<Integer, MethodData>();
        final MethodData data = new MethodData(0);
        methods.put(0, data);
        data.invocations.add(top);
        this.methodData1(methods, top);
        return methods;
    }
    
    protected void methodData1(final Map<Integer, MethodData> methods, final Invocation inv) {
        for (final IntHashMap.Entry<Invocation> entry : inv.getChildren().entrySet()) {
            final Invocation child = entry.getValue();
            final int serial = child.getMethodSerialNumber();
            MethodData data = methods.get(serial);
            if (data == null) {
                data = new MethodData(serial);
                methods.put(serial, data);
            }
            data.invocations.add(child);
            this.methodData1(methods, child);
        }
    }
}
