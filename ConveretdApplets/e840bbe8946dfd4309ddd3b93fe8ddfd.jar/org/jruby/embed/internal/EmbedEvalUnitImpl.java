// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.internal;

import java.io.Writer;
import org.jruby.Ruby;
import java.io.IOException;
import java.io.PrintWriter;
import org.jruby.exceptions.RaiseException;
import org.jruby.embed.EvalFailedException;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.DynamicScope;
import org.jruby.embed.AttributeName;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.Script;
import org.jruby.runtime.scope.ManyVarsDynamicScope;
import org.jruby.ast.Node;
import org.jruby.embed.ScriptingContainer;
import org.jruby.embed.EmbedEvalUnit;

public class EmbedEvalUnitImpl implements EmbedEvalUnit
{
    private ScriptingContainer container;
    private Node node;
    private ManyVarsDynamicScope scope;
    private Script script;
    
    public EmbedEvalUnitImpl(final ScriptingContainer container, final Node node, final ManyVarsDynamicScope scope) {
        this(container, node, scope, null);
    }
    
    public EmbedEvalUnitImpl(final ScriptingContainer container, final Node node, final ManyVarsDynamicScope scope, final Script script) {
        this.container = container;
        this.node = node;
        this.scope = scope;
        this.script = script;
    }
    
    public Node getNode() {
        return this.node;
    }
    
    public ManyVarsDynamicScope getScope() {
        return this.scope;
    }
    
    public IRubyObject run() {
        if (this.node == null && this.script == null) {
            return null;
        }
        final Ruby runtime = this.container.getProvider().getRuntime();
        final BiVariableMap vars = this.container.getVarMap();
        boolean sharing_variables = true;
        final Object obj = this.container.getAttribute(AttributeName.SHARING_VARIABLES);
        Label_0077: {
            if (obj == null || !(obj instanceof Boolean) || (boolean)obj) {
                break Label_0077;
            }
            sharing_variables = false;
            try {
                if (sharing_variables) {
                    vars.inject(this.scope, 0, null);
                    runtime.getCurrentContext().pushScope(this.scope);
                }
                final RubyInstanceConfig.CompileMode mode = runtime.getInstanceConfig().getCompileMode();
                IRubyObject ret;
                if (mode == RubyInstanceConfig.CompileMode.FORCE) {
                    ret = runtime.runScriptBody(this.script);
                }
                else {
                    ret = runtime.runInterpreter(this.node);
                }
                if (sharing_variables) {
                    vars.retrieve(ret);
                }
                return ret;
            }
            catch (RaiseException e) {
                runtime.printError(e.getException());
                throw new EvalFailedException(e.getMessage(), e);
            }
            catch (StackOverflowError soe) {
                throw runtime.newSystemStackError("stack level too deep", soe);
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
                        throw new EvalFailedException(ex);
                    }
                }
                throw new EvalFailedException(e2);
            }
            finally {
                if (sharing_variables) {
                    runtime.getCurrentContext().popScope();
                }
                vars.terminate();
            }
        }
    }
}
