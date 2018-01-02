// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.jsr223;

import javax.script.ScriptEngine;
import java.io.Writer;
import java.io.IOException;
import java.io.PrintWriter;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.JavaEmbedUtils;
import org.jruby.RubyNil;
import javax.script.ScriptException;
import javax.script.ScriptContext;
import javax.script.Bindings;
import java.io.Reader;
import org.jruby.embed.EmbedEvalUnit;
import org.jruby.embed.ScriptingContainer;
import javax.script.CompiledScript;

public class JRubyCompiledScript extends CompiledScript
{
    private ScriptingContainer container;
    private JRubyEngine engine;
    private final EmbedEvalUnit unit;
    
    JRubyCompiledScript(final ScriptingContainer container, final JRubyEngine engine, final String script) {
        this.container = container;
        this.engine = engine;
        Utils.preEval(container, (JRubyContext)engine.getContext());
        this.unit = container.parse(script, new int[0]);
    }
    
    JRubyCompiledScript(final ScriptingContainer container, final JRubyEngine engine, final Reader reader) {
        this.container = container;
        this.engine = engine;
        final String filename = System.getProperty("javax.script.filename");
        Utils.preEval(container, (JRubyContext)engine.getContext());
        this.unit = container.parse(reader, filename, Utils.getLineNumber(engine.getContext()));
    }
    
    public Object eval(final Bindings bindings) throws ScriptException {
        if (bindings == null) {
            throw new NullPointerException("bindings is null");
        }
        final ScriptContext context = this.engine.getScriptContext(bindings);
        return this.eval(context);
    }
    
    public Object eval(final ScriptContext context) throws ScriptException {
        final JRubyContext tmpContext = JRubyContext.convert(this.container, context);
        try {
            Utils.preEval(this.container, tmpContext);
            final IRubyObject ret = this.unit.run();
            if (!(ret instanceof RubyNil)) {
                return JavaEmbedUtils.rubyToJava(ret);
            }
            return null;
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
    
    private ScriptException wrapException(final Exception e) throws ScriptException {
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
        if (e.getCause() instanceof Exception) {
            return new ScriptException((Exception)e.getCause());
        }
        return new ScriptException(e);
    }
    
    public ScriptEngine getEngine() {
        return this.engine;
    }
}
