// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport;

import org.jruby.ast.Node;
import java.io.InputStream;
import org.jruby.runtime.DynamicScope;
import org.jruby.RubyRuntimeAdapter;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Block;
import org.jruby.RubyString;
import org.jruby.RubyInteger;
import org.jruby.RubyArray;
import org.jruby.RubyModule;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyObjectAdapter;
import org.jruby.util.ClassCache;
import org.jruby.RubyInstanceConfig;
import org.jruby.Ruby;
import java.util.List;

public class JavaEmbedUtils
{
    public static Ruby initialize(final List loadPaths) {
        return initialize(loadPaths, new RubyInstanceConfig());
    }
    
    public static Ruby initialize(final List loadPaths, final ClassCache classCache) {
        final RubyInstanceConfig config = new RubyInstanceConfig();
        if (classCache != null) {
            config.setClassCache(classCache);
        }
        return initialize(loadPaths, config);
    }
    
    public static Ruby initialize(final List loadPaths, final RubyInstanceConfig config) {
        final Ruby runtime = Ruby.newInstance(config);
        runtime.getLoadService().init(loadPaths);
        runtime.getLoadService().require("java");
        return runtime;
    }
    
    public static ClassCache createClassCache(final ClassLoader loader) {
        return new ClassCache(loader, new RubyInstanceConfig().getJitMax());
    }
    
    public static RubyObjectAdapter newObjectAdapter() {
        return new RubyObjectAdapter() {
            public boolean isKindOf(final IRubyObject value, final RubyModule rubyModule) {
                return rubyModule.isInstance(value);
            }
            
            public IRubyObject setInstanceVariable(final IRubyObject obj, final String variableName, final IRubyObject value) {
                return obj.getInstanceVariables().setInstanceVariable(variableName, value);
            }
            
            public IRubyObject[] convertToJavaArray(final IRubyObject array) {
                return ((RubyArray)array).toJavaArray();
            }
            
            public RubyInteger convertToRubyInteger(final IRubyObject obj) {
                return obj.convertToInteger();
            }
            
            public IRubyObject getInstanceVariable(final IRubyObject obj, final String variableName) {
                return obj.getInstanceVariables().getInstanceVariable(variableName);
            }
            
            public RubyString convertToRubyString(final IRubyObject obj) {
                return obj.convertToString();
            }
            
            public IRubyObject callMethod(final IRubyObject receiver, final String methodName) {
                return receiver.callMethod(receiver.getRuntime().getCurrentContext(), methodName);
            }
            
            public IRubyObject callMethod(final IRubyObject receiver, final String methodName, final IRubyObject singleArg) {
                return receiver.callMethod(receiver.getRuntime().getCurrentContext(), methodName, singleArg);
            }
            
            public IRubyObject callMethod(final IRubyObject receiver, final String methodName, final IRubyObject[] args) {
                return receiver.callMethod(receiver.getRuntime().getCurrentContext(), methodName, args);
            }
            
            public IRubyObject callMethod(final IRubyObject receiver, final String methodName, final IRubyObject[] args, final Block block) {
                return receiver.callMethod(receiver.getRuntime().getCurrentContext(), methodName, args, block);
            }
            
            public IRubyObject callSuper(final IRubyObject receiver, final IRubyObject[] args) {
                return RuntimeHelpers.invokeSuper(receiver.getRuntime().getCurrentContext(), receiver, args, Block.NULL_BLOCK);
            }
            
            public IRubyObject callSuper(final IRubyObject receiver, final IRubyObject[] args, final Block block) {
                return RuntimeHelpers.invokeSuper(receiver.getRuntime().getCurrentContext(), receiver, args, block);
            }
        };
    }
    
    public static RubyRuntimeAdapter newRuntimeAdapter() {
        return new RubyRuntimeAdapter() {
            public IRubyObject eval(final Ruby runtime, final String script) {
                return runtime.evalScriptlet(script);
            }
            
            public EvalUnit parse(final Ruby runtime, final String script, final String filename, final int lineNumber) {
                return new InterpretedEvalUnit(runtime, runtime.parseEval(script, filename, null, lineNumber));
            }
            
            public EvalUnit parse(final Ruby runtime, final InputStream in, final String filename, final int lineNumber) {
                return new InterpretedEvalUnit(runtime, runtime.parseFile(in, filename, null, lineNumber));
            }
        };
    }
    
    public static void terminate(final Ruby runtime) {
        runtime.tearDown();
    }
    
    public static Object invokeMethod(final Ruby runtime, final Object receiver, final String method, final Object[] args, final Class returnType) {
        final IRubyObject rubyReceiver = (receiver != null) ? JavaUtil.convertJavaToRuby(runtime, receiver) : runtime.getTopSelf();
        final IRubyObject[] rubyArgs = JavaUtil.convertJavaArrayToRuby(runtime, args);
        for (int i = 0; i < rubyArgs.length; ++i) {
            final IRubyObject obj = rubyArgs[i];
            if (obj instanceof JavaObject) {
                rubyArgs[i] = Java.wrap(runtime, obj);
            }
        }
        final IRubyObject result = rubyReceiver.callMethod(runtime.getCurrentContext(), method, rubyArgs);
        return rubyToJava(runtime, result, returnType);
    }
    
    public static Object rubyToJava(final Ruby runtime, final IRubyObject value, final Class type) {
        return value.toJava(type);
    }
    
    public static Object rubyToJava(final IRubyObject value) {
        return value.toJava(Object.class);
    }
    
    public static IRubyObject javaToRuby(final Ruby runtime, final Object value) {
        if (value instanceof IRubyObject) {
            return (IRubyObject)value;
        }
        final IRubyObject result = JavaUtil.convertJavaToUsableRubyObject(runtime, value);
        return (result instanceof JavaObject) ? Java.wrap(runtime, result) : result;
    }
    
    public static IRubyObject javaToRuby(final Ruby runtime, final boolean value) {
        return javaToRuby(runtime, value ? Boolean.TRUE : Boolean.FALSE);
    }
    
    public static IRubyObject javaToRuby(final Ruby runtime, final byte value) {
        return javaToRuby(runtime, (Object)value);
    }
    
    public static IRubyObject javaToRuby(final Ruby runtime, final char value) {
        return javaToRuby(runtime, (Object)value);
    }
    
    public static IRubyObject javaToRuby(final Ruby runtime, final double value) {
        return javaToRuby(runtime, new Double(value));
    }
    
    public static IRubyObject javaToRuby(final Ruby runtime, final float value) {
        return javaToRuby(runtime, new Float(value));
    }
    
    public static IRubyObject javaToRuby(final Ruby runtime, final int value) {
        return javaToRuby(runtime, (Object)value);
    }
    
    public static IRubyObject javaToRuby(final Ruby runtime, final long value) {
        return javaToRuby(runtime, (Object)value);
    }
    
    public static IRubyObject javaToRuby(final Ruby runtime, final short value) {
        return javaToRuby(runtime, (Object)value);
    }
    
    public static class InterpretedEvalUnit implements EvalUnit
    {
        private Ruby runtime;
        private Node node;
        
        protected InterpretedEvalUnit(final Ruby runtime, final Node node) {
            this.runtime = runtime;
            this.node = node;
        }
        
        public IRubyObject run() {
            return this.runtime.runInterpreter(this.node);
        }
    }
    
    public interface EvalUnit
    {
        IRubyObject run();
    }
}
