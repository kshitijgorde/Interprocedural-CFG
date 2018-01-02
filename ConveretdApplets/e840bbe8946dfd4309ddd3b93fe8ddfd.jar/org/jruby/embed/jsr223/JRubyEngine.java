// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.jsr223;

import javax.script.ScriptEngineFactory;
import javax.script.SimpleBindings;
import javax.script.Bindings;
import java.io.Writer;
import java.io.IOException;
import java.io.PrintWriter;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.embed.EmbedEvalUnit;
import org.jruby.javasupport.JavaEmbedUtils;
import javax.script.ScriptContext;
import java.io.Reader;
import javax.script.ScriptException;
import javax.script.CompiledScript;
import org.jruby.embed.ScriptingContainer;
import javax.script.ScriptEngine;
import javax.script.Invocable;
import javax.script.Compilable;

public class JRubyEngine implements Compilable, Invocable, ScriptEngine
{
    private final ScriptingContainer container;
    private JRubyEngineFactory factory;
    private JRubyContext jrubyContext;
    
    JRubyEngine(final ScriptingContainer container, final JRubyEngineFactory factory) {
        this.container = container;
        this.factory = factory;
        this.jrubyContext = new JRubyContext(container);
    }
    
    public CompiledScript compile(final String script) throws ScriptException {
        if (script == null) {
            throw new NullPointerException("script is null");
        }
        return new JRubyCompiledScript(this.container, this, script);
    }
    
    public CompiledScript compile(final Reader reader) throws ScriptException {
        if (reader == null) {
            throw new NullPointerException("reader is null");
        }
        return new JRubyCompiledScript(this.container, this, reader);
    }
    
    public Object eval(final String script, final ScriptContext context) throws ScriptException {
        if (script == null || context == null) {
            throw new NullPointerException("either script or context is null");
        }
        final JRubyContext tmpContext = JRubyContext.convert(this.container, context);
        this.container.setScriptFilename(Utils.getFilename(tmpContext));
        try {
            Utils.preEval(this.container, tmpContext);
            final EmbedEvalUnit unit = this.container.parse(script, Utils.getLineNumber(tmpContext));
            final IRubyObject ret = unit.run();
            return JavaEmbedUtils.rubyToJava(ret);
        }
        catch (Exception e) {
            throw this.wrapException(e);
        }
        finally {
            Utils.postEval(this.container, tmpContext);
            if (tmpContext != context) {
                JRubyContext.update(tmpContext, context);
            }
            if (Utils.isTerminationOn(tmpContext)) {
                this.container.terminate();
            }
        }
    }
    
    private ScriptException wrapException(final Exception e) {
        if (e.getCause() instanceof Exception) {
            final Writer w = this.container.getErrorWriter();
            if (w instanceof PrintWriter) {
                e.printStackTrace((PrintWriter)w);
            }
            else {
                try {
                    w.write(e.getMessage());
                }
                catch (IOException ex) {
                    return new ScriptException(ex);
                }
            }
            return new ScriptException((Exception)e.getCause());
        }
        return new ScriptException(e);
    }
    
    public Object eval(final Reader reader, final ScriptContext context) throws ScriptException {
        if (reader == null || context == null) {
            throw new NullPointerException("either reader or context is null");
        }
        final JRubyContext tmpContext = JRubyContext.convert(this.container, context);
        final String filename = Utils.getFilename(tmpContext);
        try {
            Utils.preEval(this.container, tmpContext);
            final EmbedEvalUnit unit = this.container.parse(reader, filename, Utils.getLineNumber(tmpContext));
            final IRubyObject ret = unit.run();
            return JavaEmbedUtils.rubyToJava(ret);
        }
        catch (Exception e) {
            throw this.wrapException(e);
        }
        finally {
            Utils.postEval(this.container, tmpContext);
            if (tmpContext != context) {
                JRubyContext.update(tmpContext, context);
            }
            if (Utils.isTerminationOn(tmpContext)) {
                this.container.terminate();
            }
        }
    }
    
    public Object eval(final String script, final Bindings bindings) throws ScriptException {
        final JRubyContext context = this.getScriptContext(bindings);
        return this.eval(script, context);
    }
    
    public Object eval(final Reader reader, final Bindings bindings) throws ScriptException {
        final JRubyContext context = this.getScriptContext(bindings);
        return this.eval(reader, context);
    }
    
    public Object eval(final String script) throws ScriptException {
        return this.eval(script, this.jrubyContext);
    }
    
    public Object eval(final Reader reader) throws ScriptException {
        return this.eval(reader, this.jrubyContext);
    }
    
    protected JRubyContext getScriptContext(final Bindings bindings) {
        if (bindings == null) {
            throw new NullPointerException("null bindings in engine scope");
        }
        final JRubyContext newContext = new JRubyContext(this.container);
        newContext.setEngineScopeBindings(bindings);
        final Bindings global = this.getBindings(200);
        if (global != null) {
            newContext.setGlobalScopeBindings(global);
        }
        newContext.setReader(this.jrubyContext.getReader(), false);
        newContext.setWriter(this.jrubyContext.getWriter());
        newContext.setErrorWriter(this.jrubyContext.getErrorWriter());
        return newContext;
    }
    
    public Object get(final String key) {
        return this.jrubyContext.getAttribute(key, 100);
    }
    
    public void put(final String key, final Object value) {
        this.jrubyContext.getEngineScopeBindings().put(key, value);
    }
    
    public Bindings getBindings(final int scope) {
        return this.jrubyContext.getBindings(scope);
    }
    
    public void setBindings(final Bindings bindings, final int scope) {
        this.jrubyContext.setBindings(bindings, scope);
    }
    
    public Bindings createBindings() {
        return new SimpleBindings();
    }
    
    public ScriptContext getContext() {
        return this.jrubyContext;
    }
    
    public void setContext(final ScriptContext ctx) {
        if (ctx == null) {
            throw new NullPointerException("context is null");
        }
        this.jrubyContext = JRubyContext.convert(this.container, ctx);
    }
    
    public ScriptEngineFactory getFactory() {
        return this.factory;
    }
    
    public Object invokeMethod(final Object receiver, final String method, final Object... args) throws ScriptException, NoSuchMethodException {
        if (method == null) {
            throw new NullPointerException("method is null");
        }
        if (receiver == null) {
            throw new NullPointerException("receiver is null");
        }
        try {
            Utils.preEval(this.container, this.jrubyContext);
            if (args == null || args.length == 0) {
                return this.container.callMethod(receiver, method, Object.class);
            }
            return this.container.callMethod(receiver, method, args, Object.class);
        }
        catch (Exception e) {
            if (e.getCause().getMessage().contains("undefined method")) {
                throw this.wrapMethodException(e);
            }
            throw this.wrapException(e);
        }
        finally {
            Utils.postEval(this.container, this.jrubyContext);
        }
    }
    
    private NoSuchMethodException wrapMethodException(final Exception e) {
        final Writer w = this.container.getErrorWriter();
        if (w instanceof PrintWriter) {
            e.printStackTrace((PrintWriter)w);
        }
        else {
            try {
                w.write(e.getMessage());
            }
            catch (IOException ex) {
                return (NoSuchMethodException)new NoSuchMethodException(ex.getMessage()).initCause(ex);
            }
        }
        return (NoSuchMethodException)new NoSuchMethodException(e.getCause().getMessage()).initCause(e);
    }
    
    public Object invokeFunction(final String method, final Object... args) throws ScriptException, NoSuchMethodException {
        if (method == null) {
            throw new NullPointerException("method is null");
        }
        try {
            Utils.preEval(this.container, this.jrubyContext);
            if (args == null || args.length == 0) {
                return this.container.callMethod(null, method, Object.class);
            }
            return this.container.callMethod(null, method, args, Object.class);
        }
        catch (Exception e) {
            if (e.getCause().getMessage().contains("undefined method")) {
                throw this.wrapMethodException(e);
            }
            throw this.wrapException(e);
        }
        finally {
            Utils.postEval(this.container, this.jrubyContext);
        }
    }
    
    public <T> T getInterface(final Class<T> returnType) {
        return this.getInterface(null, returnType);
    }
    
    public <T> T getInterface(final Object receiver, final Class<T> returnType) {
        return this.container.getInstance(receiver, returnType);
    }
}
