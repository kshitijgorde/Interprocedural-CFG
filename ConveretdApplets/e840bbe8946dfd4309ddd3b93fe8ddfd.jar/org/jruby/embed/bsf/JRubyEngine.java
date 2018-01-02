// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.bsf;

import org.jruby.javasupport.Java;
import org.jruby.javasupport.JavaObject;
import org.jruby.javasupport.JavaUtil;
import org.jruby.exceptions.JumpException;
import org.jruby.exceptions.RaiseException;
import java.util.Arrays;
import java.io.File;
import java.util.List;
import org.jruby.embed.variable.BiVariable;
import org.jruby.embed.variable.VariableInterceptor;
import org.jruby.RubyObject;
import org.jruby.Ruby;
import org.jruby.runtime.IAccessor;
import org.apache.bsf.BSFEngine;
import org.apache.bsf.util.BSFFunctions;
import org.apache.bsf.BSFDeclaredBean;
import org.jruby.CompatVersion;
import org.jruby.embed.LocalVariableBehavior;
import org.jruby.embed.util.SystemPropertyCatcher;
import org.jruby.embed.LocalContextScope;
import org.apache.bsf.BSFManager;
import org.jruby.embed.EmbedRubyObjectAdapter;
import org.apache.bsf.BSFException;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.JavaEmbedUtils;
import org.jruby.embed.PathType;
import java.io.InputStream;
import java.io.Reader;
import org.jruby.runtime.ThreadContext;
import java.util.Vector;
import org.jruby.embed.ScriptingContainer;
import org.apache.bsf.util.BSFEngineImpl;

public class JRubyEngine extends BSFEngineImpl
{
    private ScriptingContainer container;
    
    public Object apply(final String file, final int line, final int col, final Object funcBody, final Vector paramNames, final Vector args) {
        final ThreadContext context = this.container.getProvider().getRuntime().getCurrentContext();
        try {
            if (paramNames != null && args != null) {
                for (int i = 0; i < paramNames.size(); ++i) {
                    final Object o = paramNames.get(i);
                    if (o instanceof String) {
                        final String name = (String)o;
                        this.container.put(name, args.get(i));
                    }
                }
            }
            return this.run(file, line, funcBody);
        }
        catch (StackOverflowError soe) {
            throw context.getRuntime().newSystemStackError("stack level too deep", soe);
        }
    }
    
    private Object run(final String fileinfo, final int line, final Object scriptUnit) {
        JavaEmbedUtils.EvalUnit unit = null;
        if (scriptUnit instanceof String) {
            unit = this.container.parse(scriptUnit.toString(), line);
        }
        else if (scriptUnit instanceof Reader) {
            unit = this.container.parse((Reader)scriptUnit, fileinfo, line);
        }
        else if (scriptUnit instanceof InputStream) {
            unit = this.container.parse((InputStream)scriptUnit, fileinfo, line);
        }
        else if (scriptUnit instanceof PathType) {
            unit = this.container.parse((PathType)scriptUnit, fileinfo, line);
        }
        if (unit == null) {
            return null;
        }
        final IRubyObject ret = unit.run();
        return JavaEmbedUtils.rubyToJava(ret);
    }
    
    public Object eval(final String file, final int line, final int col, final Object expr) throws BSFException {
        try {
            return this.run(file, line, expr);
        }
        catch (Exception excptn) {
            throw new BSFException(100, "Exception", (Throwable)excptn);
        }
    }
    
    public void exec(final String file, final int line, final int col, final Object expr) throws BSFException {
        try {
            this.run(file, line, expr);
        }
        catch (Exception excptn) {
            throw new BSFException(100, "Exception", (Throwable)excptn);
        }
    }
    
    public Object call(final Object recv, final String method, final Object[] args) throws BSFException {
        try {
            final EmbedRubyObjectAdapter adapter = this.container.newObjectAdapter();
            if (args == null) {
                return adapter.callMethod(recv, method, Object.class);
            }
            return adapter.callMethod(recv, method, args, Object.class);
        }
        catch (Exception excptn) {
            throw new BSFException(100, excptn.getMessage(), (Throwable)excptn);
        }
    }
    
    public void initialize(final BSFManager manager, final String language, final Vector someDeclaredBeans) throws BSFException {
        super.initialize(manager, language, someDeclaredBeans);
        final LocalContextScope scope = SystemPropertyCatcher.getScope(LocalContextScope.SINGLETON);
        final LocalVariableBehavior behavior = LocalVariableBehavior.BSF;
        SystemPropertyCatcher.setConfiguration(this.container = new ScriptingContainer(scope, behavior));
        if (SystemPropertyCatcher.isRuby19(language)) {
            this.container.getProvider().getRubyInstanceConfig().setCompatVersion(CompatVersion.RUBY1_9);
        }
        final Ruby runtime = this.container.getProvider().getRuntime();
        if (someDeclaredBeans != null && someDeclaredBeans.size() > 0) {
            for (int i = 0; i < someDeclaredBeans.size(); ++i) {
                final BSFDeclaredBean bean = someDeclaredBeans.get(i);
                this.setVariable(bean);
            }
        }
        runtime.getGlobalVariables().defineReadonly("$bsf", new FunctionsGlobalVariable(runtime, new BSFFunctions(manager, (BSFEngine)this)));
    }
    
    private void setVariable(final BSFDeclaredBean bean) {
        String name = bean.name;
        if ("$bsf".equals(name)) {
            return;
        }
        if (!name.startsWith("$")) {
            name = "$".concat(name);
        }
        final RubyObject receiver = (RubyObject)this.container.getProvider().getRuntime().getTopSelf();
        final BiVariable v = VariableInterceptor.getVariableInstance(LocalVariableBehavior.BSF, receiver, name, bean.bean, bean.type);
        this.container.getVarMap().setVariable(receiver, v);
    }
    
    private List<String> getClassPath(final BSFManager manager) {
        String classpath = manager.getClassPath();
        final String s = System.getProperty("org.jruby.embed.class.path");
        if (s != null) {
            classpath = classpath + File.pathSeparator + s;
        }
        return Arrays.asList(classpath.split(System.getProperty("path.separator")));
    }
    
    public void declareBean(final BSFDeclaredBean bean) throws BSFException {
        assert bean != null;
        this.setVariable(bean);
    }
    
    public void undeclareBean(final BSFDeclaredBean bean) throws BSFException {
        assert bean != null;
        String name = bean.name;
        if (!name.startsWith("$")) {
            name = "$".concat(name);
        }
        this.container.getVarMap().remove(name);
    }
    
    public void handleException(final BSFException bsfExcptn) {
        printException(this.container.getProvider().getRuntime(), (Exception)bsfExcptn.getTargetException());
    }
    
    private static void printException(final Ruby runtime, final Exception exception) {
        assert exception != null;
        if (exception instanceof RaiseException) {
            final JumpException je = (JumpException)exception;
            if (je instanceof RaiseException) {
                runtime.printError(((RaiseException)je).getException());
            }
            else if (je instanceof JumpException.BreakJump) {
                runtime.getErrorStream().println("break without block.");
            }
            else if (je instanceof JumpException.ReturnJump) {
                runtime.getErrorStream().println("return without block.");
            }
        }
    }
    
    public void terminate() {
        this.container.getVarMap().clear();
        this.container.terminate();
        super.terminate();
    }
    
    private static class FunctionsGlobalVariable implements IAccessor
    {
        private Ruby runtime;
        private BSFFunctions functions;
        
        public FunctionsGlobalVariable(final Ruby runtime, final BSFFunctions functions) {
            this.runtime = runtime;
            this.functions = functions;
        }
        
        public IRubyObject getValue() {
            final IRubyObject result = JavaUtil.convertJavaToRuby(this.runtime, this.functions, BSFFunctions.class);
            return (result instanceof JavaObject) ? Java.wrap(this.runtime, result) : result;
        }
        
        public IRubyObject setValue(final IRubyObject value) {
            return value;
        }
    }
}
