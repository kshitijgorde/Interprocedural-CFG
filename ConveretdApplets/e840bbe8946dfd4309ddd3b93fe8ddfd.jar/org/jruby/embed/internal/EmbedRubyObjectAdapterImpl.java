// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.internal;

import org.jruby.runtime.ThreadContext;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.javasupport.Java;
import org.jruby.javasupport.JavaObject;
import org.jruby.javasupport.JavaUtil;
import org.jruby.runtime.scope.ManyVarsDynamicScope;
import org.jruby.Ruby;
import org.jruby.exceptions.RaiseException;
import org.jruby.RubyNil;
import org.jruby.runtime.DynamicScope;
import org.jruby.embed.AttributeName;
import java.io.Writer;
import java.io.IOException;
import java.io.PrintWriter;
import org.jruby.embed.InvokeFailedException;
import org.jruby.embed.EmbedEvalUnit;
import org.jruby.runtime.Block;
import org.jruby.embed.variable.BiVariable;
import org.jruby.embed.variable.InstanceVariable;
import org.jruby.RubyObject;
import org.jruby.RubyString;
import org.jruby.RubyInteger;
import org.jruby.RubyModule;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.JavaEmbedUtils;
import org.jruby.embed.ScriptingContainer;
import org.jruby.RubyObjectAdapter;
import org.jruby.embed.EmbedRubyObjectAdapter;

public class EmbedRubyObjectAdapterImpl implements EmbedRubyObjectAdapter
{
    private RubyObjectAdapter adapter;
    private ScriptingContainer container;
    
    public EmbedRubyObjectAdapterImpl(final ScriptingContainer container) {
        this.adapter = JavaEmbedUtils.newObjectAdapter();
        this.container = container;
    }
    
    public boolean isKindOf(final IRubyObject value, final RubyModule rubyModule) {
        return this.adapter.isKindOf(value, rubyModule);
    }
    
    public IRubyObject[] convertToJavaArray(final IRubyObject array) {
        return this.adapter.convertToJavaArray(array);
    }
    
    public RubyInteger convertToRubyInteger(final IRubyObject obj) {
        return this.adapter.convertToRubyInteger(obj);
    }
    
    public RubyString convertToRubyString(final IRubyObject obj) {
        return this.adapter.convertToRubyString(obj);
    }
    
    public IRubyObject setInstanceVariable(final IRubyObject obj, final String variableName, final IRubyObject value) {
        final BiVariableMap map = this.container.getVarMap();
        synchronized (map) {
            if (map.containsKey(variableName)) {
                final BiVariable bv = map.getVariable((RubyObject)this.container.getProvider().getRuntime().getTopSelf(), variableName);
                bv.setRubyObject(value);
            }
            else {
                final InstanceVariable iv = new InstanceVariable(obj, variableName, value);
                map.update(variableName, iv);
            }
        }
        return obj.getInstanceVariables().setInstanceVariable(variableName, value);
    }
    
    public IRubyObject getInstanceVariable(final IRubyObject obj, final String variableName) {
        final BiVariableMap map = this.container.getVarMap();
        synchronized (map) {
            if (map.containsKey(variableName)) {
                final BiVariable bv = map.getVariable((RubyObject)this.container.getProvider().getRuntime().getTopSelf(), variableName);
                return bv.getRubyObject();
            }
        }
        return null;
    }
    
    public IRubyObject callMethod(final IRubyObject receiver, final String methodName) {
        return this.adapter.callMethod(receiver, methodName);
    }
    
    public IRubyObject callMethod(final IRubyObject receiver, final String methodName, final IRubyObject singleArg) {
        return this.adapter.callMethod(receiver, methodName, singleArg);
    }
    
    public IRubyObject callMethod(final IRubyObject receiver, final String methodName, final IRubyObject[] args) {
        return this.adapter.callMethod(receiver, methodName, args);
    }
    
    public IRubyObject callMethod(final IRubyObject receiver, final String methodName, final IRubyObject[] args, final Block block) {
        return this.adapter.callMethod(receiver, methodName, args, block);
    }
    
    public IRubyObject callSuper(final IRubyObject receiver, final IRubyObject[] args) {
        return this.adapter.callSuper(receiver, args);
    }
    
    public IRubyObject callSuper(final IRubyObject receiver, final IRubyObject[] args, final Block block) {
        return this.adapter.callSuper(receiver, args, block);
    }
    
    public <T> T callMethod(final Object receiver, final String methodName, final Class<T> returnType) {
        try {
            return this.call(MethodType.CALLMETHOD_NOARG, returnType, receiver, methodName, null, null, new Object[0]);
        }
        catch (InvokeFailedException e) {
            throw e;
        }
        catch (Throwable e2) {
            final Writer w = this.container.getErrorWriter();
            if (w instanceof PrintWriter) {
                e2.printStackTrace((PrintWriter)w);
            }
            else {
                try {
                    w.write(e2.getMessage());
                }
                catch (IOException ex) {
                    throw new InvokeFailedException(ex);
                }
            }
            throw new InvokeFailedException(e2);
        }
    }
    
    public <T> T callMethod(final Object receiver, final String methodName, final Object singleArg, final Class<T> returnType) {
        try {
            return this.call(MethodType.CALLMETHOD, returnType, receiver, methodName, null, null, singleArg);
        }
        catch (InvokeFailedException e) {
            throw e;
        }
        catch (Throwable e2) {
            final Writer w = this.container.getErrorWriter();
            if (w instanceof PrintWriter) {
                e2.printStackTrace((PrintWriter)w);
            }
            else {
                try {
                    w.write(e2.getMessage());
                }
                catch (IOException ex) {
                    throw new InvokeFailedException(ex);
                }
            }
            throw new InvokeFailedException(e2);
        }
    }
    
    public <T> T callMethod(final Object receiver, final String methodName, final Object[] args, final Class<T> returnType) {
        try {
            return this.call(MethodType.CALLMETHOD, returnType, receiver, methodName, null, null, args);
        }
        catch (InvokeFailedException e) {
            throw e;
        }
        catch (Throwable e2) {
            final Writer w = this.container.getErrorWriter();
            if (w instanceof PrintWriter) {
                e2.printStackTrace((PrintWriter)w);
            }
            else {
                try {
                    w.write(e2.getMessage());
                }
                catch (IOException ex) {
                    throw new InvokeFailedException(ex);
                }
            }
            throw new InvokeFailedException(e2);
        }
    }
    
    public <T> T callMethod(final Object receiver, final String methodName, final Object[] args, final Block block, final Class<T> returnType) {
        try {
            return this.call(MethodType.CALLMETHOD_WITHBLOCK, returnType, receiver, methodName, block, null, args);
        }
        catch (InvokeFailedException e) {
            throw e;
        }
        catch (Throwable e2) {
            final Writer w = this.container.getErrorWriter();
            if (w instanceof PrintWriter) {
                e2.printStackTrace((PrintWriter)w);
            }
            else {
                try {
                    w.write(e2.getMessage());
                }
                catch (IOException ex) {
                    throw new InvokeFailedException(ex);
                }
            }
            throw new InvokeFailedException(e2);
        }
    }
    
    public <T> T callMethod(final Object receiver, final String methodName, final Class<T> returnType, final EmbedEvalUnit unit) {
        try {
            return this.call(MethodType.CALLMETHOD_NOARG, returnType, receiver, methodName, null, unit, new Object[0]);
        }
        catch (InvokeFailedException e) {
            throw e;
        }
        catch (Throwable e2) {
            final Writer w = this.container.getErrorWriter();
            if (w instanceof PrintWriter) {
                e2.printStackTrace((PrintWriter)w);
            }
            else {
                try {
                    w.write(e2.getMessage());
                }
                catch (IOException ex) {
                    throw new InvokeFailedException(ex);
                }
            }
            throw new InvokeFailedException(e2);
        }
    }
    
    public <T> T callMethod(final Object receiver, final String methodName, final Object[] args, final Class<T> returnType, final EmbedEvalUnit unit) {
        try {
            return this.call(MethodType.CALLMETHOD, returnType, receiver, methodName, null, unit, args);
        }
        catch (InvokeFailedException e) {
            throw e;
        }
        catch (Throwable e2) {
            final Writer w = this.container.getErrorWriter();
            if (w instanceof PrintWriter) {
                e2.printStackTrace((PrintWriter)w);
            }
            else {
                try {
                    w.write(e2.getMessage());
                }
                catch (IOException ex) {
                    throw new InvokeFailedException(ex);
                }
            }
            throw new InvokeFailedException(e2);
        }
    }
    
    public <T> T callMethod(final Object receiver, final String methodName, final Object[] args, final Block block, final Class<T> returnType, final EmbedEvalUnit unit) {
        try {
            return this.call(MethodType.CALLMETHOD_WITHBLOCK, returnType, receiver, methodName, block, unit, args);
        }
        catch (InvokeFailedException e) {
            throw e;
        }
        catch (Throwable e2) {
            final Writer w = this.container.getErrorWriter();
            if (w instanceof PrintWriter) {
                e2.printStackTrace((PrintWriter)w);
            }
            else {
                try {
                    w.write(e2.getMessage());
                }
                catch (IOException ex) {
                    throw new InvokeFailedException(ex);
                }
            }
            throw new InvokeFailedException(e2);
        }
    }
    
    public <T> T callSuper(final Object receiver, final Object[] args, final Class<T> returnType) {
        try {
            return this.call(MethodType.CALLSUPER, returnType, receiver, null, null, null, args);
        }
        catch (InvokeFailedException e) {
            throw e;
        }
        catch (Throwable e2) {
            final Writer w = this.container.getErrorWriter();
            if (w instanceof PrintWriter) {
                e2.printStackTrace((PrintWriter)w);
            }
            else {
                try {
                    w.write(e2.getMessage());
                }
                catch (IOException ex) {
                    throw new InvokeFailedException(ex);
                }
            }
            throw new InvokeFailedException(e2);
        }
    }
    
    public <T> T callSuper(final Object receiver, final Object[] args, final Block block, final Class<T> returnType) {
        try {
            return this.call(MethodType.CALLSUPER_WITHBLOCK, returnType, receiver, null, block, null, args);
        }
        catch (InvokeFailedException e) {
            throw e;
        }
        catch (Throwable e2) {
            final Writer w = this.container.getErrorWriter();
            if (w instanceof PrintWriter) {
                e2.printStackTrace((PrintWriter)w);
            }
            else {
                try {
                    w.write(e2.getMessage());
                }
                catch (IOException ex) {
                    throw new InvokeFailedException(ex);
                }
            }
            throw new InvokeFailedException(e2);
        }
    }
    
    public Object callMethod(final Object receiver, final String methodName, final Object... args) {
        try {
            if (args.length == 0) {
                return this.call(MethodType.CALLMETHOD_NOARG, Object.class, receiver, methodName, null, null, new Object[0]);
            }
            return this.call(MethodType.CALLMETHOD, Object.class, receiver, methodName, null, null, args);
        }
        catch (InvokeFailedException e) {
            throw e;
        }
        catch (Throwable e2) {
            final Writer w = this.container.getErrorWriter();
            if (w instanceof PrintWriter) {
                e2.printStackTrace((PrintWriter)w);
            }
            else {
                try {
                    w.write(e2.getMessage());
                }
                catch (IOException ex) {
                    throw new InvokeFailedException(ex);
                }
            }
            throw new InvokeFailedException(e2);
        }
    }
    
    public Object callMethod(final Object receiver, final String methodName, final Block block, final Object... args) {
        try {
            if (args.length == 0) {
                throw new IllegalArgumentException("needs at least one argument in a method");
            }
            return this.call(MethodType.CALLMETHOD_WITHBLOCK, Object.class, receiver, methodName, block, null, args);
        }
        catch (InvokeFailedException e) {
            throw e;
        }
        catch (Throwable e2) {
            final Writer w = this.container.getErrorWriter();
            if (w instanceof PrintWriter) {
                e2.printStackTrace((PrintWriter)w);
            }
            else {
                try {
                    w.write(e2.getMessage());
                }
                catch (IOException ex) {
                    throw new InvokeFailedException(ex);
                }
            }
            throw new InvokeFailedException(e2);
        }
    }
    
    private <T> T call(final MethodType type, final Class<T> returnType, final Object receiver, final String methodName, final Block block, final EmbedEvalUnit unit, final Object... args) {
        if (methodName == null || methodName.length() == 0) {
            return null;
        }
        final Ruby runtime = this.container.getProvider().getRuntime();
        final RubyObject rubyReceiver = this.getReceiverObject(runtime, receiver);
        boolean sharing_variables = true;
        final Object obj = this.container.getAttribute(AttributeName.SHARING_VARIABLES);
        Label_0080: {
            if (obj == null || !(obj instanceof Boolean) || (boolean)obj) {
                break Label_0080;
            }
            sharing_variables = false;
            try {
                if (sharing_variables) {
                    ManyVarsDynamicScope scope;
                    if (unit != null && unit.getScope() != null) {
                        scope = unit.getScope();
                    }
                    else {
                        scope = EmbedRubyRuntimeAdapterImpl.getManyVarsDynamicScope(this.container, 0);
                    }
                    this.container.getVarMap().inject(scope, 0, rubyReceiver);
                    runtime.getCurrentContext().pushScope(scope);
                }
                final IRubyObject result = this.callEachType(type, rubyReceiver, methodName, block, args);
                if (sharing_variables) {
                    this.container.getVarMap().retrieve(rubyReceiver);
                }
                if (!(result instanceof RubyNil) && returnType != null) {
                    final Object ret = JavaEmbedUtils.rubyToJava(runtime, result, returnType);
                    return (ret != null) ? returnType.cast(ret) : null;
                }
                return null;
            }
            catch (RaiseException e) {
                runtime.printError(e.getException());
                throw new InvokeFailedException(e.getMessage(), e);
            }
            catch (Throwable e2) {
                final Writer w = this.container.getErrorWriter();
                if (w instanceof PrintWriter) {
                    e2.printStackTrace((PrintWriter)w);
                }
                else {
                    try {
                        w.write(e2.getMessage());
                    }
                    catch (IOException ex) {
                        throw new InvokeFailedException(ex);
                    }
                }
                throw new InvokeFailedException(e2);
            }
            finally {
                if (sharing_variables) {
                    runtime.getCurrentContext().popScope();
                }
            }
        }
    }
    
    private RubyObject getReceiverObject(final Ruby runtime, final Object receiver) {
        if (receiver == null || !(receiver instanceof IRubyObject) || receiver instanceof RubyNil) {
            return (RubyObject)runtime.getTopSelf();
        }
        if (receiver instanceof RubyObject) {
            return (RubyObject)receiver;
        }
        return (RubyObject)((IRubyObject)receiver).getRuntime().getTopSelf();
    }
    
    private IRubyObject callEachType(final MethodType type, final IRubyObject rubyReceiver, final String methodName, final Block block, final Object... args) {
        final Ruby runtime = rubyReceiver.getRuntime();
        IRubyObject[] rubyArgs = null;
        if (args != null && args.length > 0) {
            rubyArgs = JavaUtil.convertJavaArrayToRuby(runtime, args);
            for (int i = 0; i < rubyArgs.length; ++i) {
                final IRubyObject obj = rubyArgs[i];
                if (obj instanceof JavaObject) {
                    rubyArgs[i] = Java.wrap(runtime, obj);
                }
            }
        }
        final ThreadContext context = runtime.getCurrentContext();
        switch (type) {
            case CALLMETHOD_NOARG: {
                return RuntimeHelpers.invoke(context, rubyReceiver, methodName);
            }
            case CALLMETHOD: {
                return RuntimeHelpers.invoke(context, rubyReceiver, methodName, rubyArgs);
            }
            case CALLMETHOD_WITHBLOCK: {
                return RuntimeHelpers.invoke(context, rubyReceiver, methodName, rubyArgs, block);
            }
            case CALLSUPER: {
                return RuntimeHelpers.invokeSuper(context, rubyReceiver, rubyArgs, Block.NULL_BLOCK);
            }
            case CALLSUPER_WITHBLOCK: {
                return RuntimeHelpers.invokeSuper(context, rubyReceiver, rubyArgs, block);
            }
            default: {
                return null;
            }
        }
    }
    
    public enum MethodType
    {
        CALLMETHOD_NOARG, 
        CALLMETHOD, 
        CALLMETHOD_WITHBLOCK, 
        CALLSUPER, 
        CALLSUPER_WITHBLOCK;
    }
}
