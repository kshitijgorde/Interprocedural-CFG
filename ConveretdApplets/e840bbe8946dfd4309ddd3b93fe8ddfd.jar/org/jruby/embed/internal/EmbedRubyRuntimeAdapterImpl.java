// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.internal;

import org.jruby.parser.EvalStaticScope;
import org.jruby.RubyModule;
import org.jruby.parser.StaticScope;
import org.jruby.parser.LocalStaticScope;
import org.jruby.ast.executable.Script;
import org.jruby.ast.Node;
import org.jruby.runtime.scope.ManyVarsDynamicScope;
import org.jruby.runtime.IAccessor;
import org.jruby.Ruby;
import org.jruby.exceptions.RaiseException;
import org.jruby.compiler.ASTInspector;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.DynamicScope;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.internal.runtime.ValueAccessor;
import org.jruby.RubyString;
import java.io.Writer;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.jruby.embed.ParseFailedException;
import java.io.PrintWriter;
import java.io.File;
import org.jruby.embed.util.SystemPropertyCatcher;
import java.io.FileInputStream;
import org.jruby.embed.PathType;
import java.io.InputStream;
import java.io.Reader;
import org.jruby.embed.io.ReaderInputStream;
import java.io.StringReader;
import org.jruby.embed.AttributeName;
import org.jruby.embed.EmbedEvalUnit;
import org.jruby.javasupport.JavaEmbedUtils;
import org.jruby.embed.ScriptingContainer;
import org.jruby.RubyRuntimeAdapter;
import org.jruby.embed.EmbedRubyRuntimeAdapter;

public class EmbedRubyRuntimeAdapterImpl implements EmbedRubyRuntimeAdapter
{
    private RubyRuntimeAdapter adapter;
    private ScriptingContainer container;
    
    public EmbedRubyRuntimeAdapterImpl(final ScriptingContainer container) {
        this.adapter = JavaEmbedUtils.newRuntimeAdapter();
        this.container = container;
    }
    
    public EmbedEvalUnit parse(final String script, final int... lines) {
        if (script == null) {
            return null;
        }
        boolean unicode_escape = false;
        final Object obj = this.container.getAttribute(AttributeName.UNICODE_ESCAPE);
        if (obj != null && obj instanceof Boolean) {
            unicode_escape = (boolean)obj;
        }
        if (unicode_escape) {
            final InputStream istream = new ReaderInputStream(new StringReader(script));
            return this.runParser(istream, null, lines);
        }
        return this.runParser(script, null, lines);
    }
    
    public EmbedEvalUnit parse(final Reader reader, final String filename, final int... lines) {
        if (reader != null) {
            final InputStream istream = new ReaderInputStream(reader);
            return this.runParser(istream, filename, lines);
        }
        return null;
    }
    
    public EmbedEvalUnit parse(PathType type, final String filename, final int... lines) {
        if (filename == null) {
            return null;
        }
        if (type == null) {
            type = PathType.ABSOLUTE;
        }
        InputStream istream = null;
        try {
            switch (EmbedRubyRuntimeAdapterImpl$1.$SwitchMap$org$jruby$embed$PathType[type.ordinal()]) {
                case 0: {
                    istream = new FileInputStream(filename);
                    break;
                }
                case 1: {
                    String basedir = (String)this.container.getAttribute(AttributeName.BASE_DIR);
                    if (basedir == null) {
                        basedir = SystemPropertyCatcher.getBaseDir();
                    }
                    final String absolutePath = basedir + File.separator + filename;
                    istream = new FileInputStream(absolutePath);
                    break;
                }
                case 2: {
                    istream = this.container.getProvider().getRuntime().getJRubyClassLoader().getResourceAsStream(filename);
                    break;
                }
            }
            return this.parse(istream, filename, lines);
        }
        catch (FileNotFoundException e) {
            final Writer w = this.container.getErrorWriter();
            if (w instanceof PrintWriter) {
                e.printStackTrace((PrintWriter)w);
            }
            else {
                try {
                    w.write(e.getMessage());
                }
                catch (IOException ex) {
                    throw new ParseFailedException(ex);
                }
            }
            throw new ParseFailedException(e);
        }
        finally {
            if (istream != null) {
                try {
                    istream.close();
                }
                catch (IOException ex2) {}
            }
        }
    }
    
    public EmbedEvalUnit parse(final InputStream istream, final String filename, final int... lines) {
        if (istream != null) {
            return this.runParser(istream, filename, lines);
        }
        return null;
    }
    
    private EmbedEvalUnit runParser(final Object input, String filename, final int... lines) {
        if (input == null) {
            return null;
        }
        if (filename == null || filename.length() == 0) {
            filename = this.container.getScriptFilename();
        }
        final Ruby runtime = this.container.getProvider().getRuntime();
        final IAccessor d = new ValueAccessor(RubyString.newString(runtime, filename));
        runtime.getGlobalVariables().define("$PROGRAM_NAME", d);
        runtime.getGlobalVariables().define("$0", d);
        int line = 0;
        Label_0095: {
            if (lines == null || lines.length <= 0) {
                break Label_0095;
            }
            line = lines[0];
            try {
                ManyVarsDynamicScope scope = null;
                boolean sharing_variables = true;
                final Object obj = this.container.getAttribute(AttributeName.SHARING_VARIABLES);
                if (obj != null && obj instanceof Boolean && !(boolean)obj) {
                    sharing_variables = false;
                }
                if (sharing_variables) {
                    scope = getManyVarsDynamicScope(this.container, 0);
                }
                Node node = null;
                if (input instanceof String) {
                    node = runtime.parseEval((String)input, filename, scope, line);
                }
                else {
                    node = runtime.parseFile((InputStream)input, filename, scope, line);
                }
                final RubyInstanceConfig.CompileMode compileMode = runtime.getInstanceConfig().getCompileMode();
                if (compileMode != RubyInstanceConfig.CompileMode.FORCE) {
                    return new EmbedEvalUnitImpl(this.container, node, scope);
                }
                final ASTInspector inspector = new ASTInspector();
                inspector.setFlag(32768);
                final Script script = runtime.tryCompile(node, inspector);
                if (script != null) {
                    return new EmbedEvalUnitImpl(this.container, node, scope, script);
                }
                return new EmbedEvalUnitImpl(this.container, node, scope);
            }
            catch (RaiseException e) {
                runtime.printError(e.getException());
                throw new ParseFailedException(e.getMessage(), e);
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
                        throw new ParseFailedException(ex);
                    }
                }
                throw new ParseFailedException(e2);
            }
            finally {
                try {
                    if (input instanceof InputStream) {
                        ((InputStream)input).close();
                    }
                }
                catch (IOException ex2) {
                    throw new ParseFailedException(ex2);
                }
            }
        }
    }
    
    static ManyVarsDynamicScope getManyVarsDynamicScope(final ScriptingContainer container, final int depth) {
        final StaticScope topStaticScope = new LocalStaticScope(null);
        topStaticScope.setModule(container.getProvider().getRuntime().getObject());
        final DynamicScope currentScope = new ManyVarsDynamicScope(topStaticScope, null);
        final String[] names4Injection = container.getVarMap().getLocalVarNames();
        ManyVarsDynamicScope scope;
        if (names4Injection == null || names4Injection.length == 0) {
            scope = new ManyVarsDynamicScope(new EvalStaticScope(currentScope.getStaticScope()), currentScope);
        }
        else {
            scope = new ManyVarsDynamicScope(new EvalStaticScope(currentScope.getStaticScope(), names4Injection), currentScope);
        }
        scope.getStaticScope().determineModule();
        return scope;
    }
    
    public IRubyObject eval(final Ruby runtime, final String script) {
        return this.adapter.eval(runtime, script);
    }
    
    public JavaEmbedUtils.EvalUnit parse(final Ruby runtime, final String script, final String filename, final int lineNumber) {
        return this.adapter.parse(runtime, script, filename, lineNumber);
    }
    
    public JavaEmbedUtils.EvalUnit parse(final Ruby runtime, final InputStream istream, final String filename, final int lineNumber) {
        return this.adapter.parse(runtime, istream, filename, lineNumber);
    }
}
