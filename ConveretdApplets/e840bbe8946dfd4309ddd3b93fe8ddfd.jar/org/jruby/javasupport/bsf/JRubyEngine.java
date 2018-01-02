// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.javasupport.bsf;

import org.jruby.javasupport.Java;
import org.jruby.javasupport.JavaObject;
import org.jruby.javasupport.JavaUtil;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.exceptions.JumpException;
import org.jruby.exceptions.RaiseException;
import java.util.Arrays;
import java.util.List;
import org.apache.bsf.BSFEngine;
import org.apache.bsf.util.BSFFunctions;
import org.jruby.runtime.IAccessor;
import org.jruby.runtime.GlobalVariable;
import org.apache.bsf.BSFDeclaredBean;
import org.apache.bsf.BSFManager;
import org.apache.bsf.BSFException;
import org.jruby.runtime.DynamicScope;
import org.jruby.runtime.ThreadContext;
import java.util.Vector;
import org.jruby.javasupport.JavaEmbedUtils;
import org.jruby.RubyRuntimeAdapter;
import org.jruby.Ruby;
import org.apache.bsf.util.BSFEngineImpl;

public class JRubyEngine extends BSFEngineImpl
{
    private Ruby runtime;
    private RubyRuntimeAdapter evaler;
    
    public JRubyEngine() {
        this.evaler = JavaEmbedUtils.newRuntimeAdapter();
    }
    
    public Object apply(final String file, final int line, final int col, final Object funcBody, final Vector paramNames, final Vector args) {
        final ThreadContext context = this.runtime.getCurrentContext();
        try {
            final String[] names = new String[paramNames.size()];
            paramNames.toArray(names);
            context.preBsfApply(names);
            final DynamicScope scope = context.getCurrentScope();
            for (int i = 0, size = args.size(); i < size; ++i) {
                scope.setValue(i, JavaEmbedUtils.javaToRuby(this.runtime, args.get(i)), 0);
            }
            return JavaEmbedUtils.rubyToJava(this.evaler.parse(this.runtime, funcBody.toString(), file, line).run());
        }
        catch (StackOverflowError soe) {
            throw context.getRuntime().newSystemStackError("stack level too deep", soe);
        }
        finally {
            context.postBsfApply();
        }
    }
    
    public Object eval(final String file, final int line, final int col, final Object expr) throws BSFException {
        try {
            return JavaEmbedUtils.rubyToJava(this.evaler.parse(this.runtime, expr.toString(), file, line).run());
        }
        catch (Exception excptn) {
            throw new BSFException(100, "Exception", (Throwable)excptn);
        }
    }
    
    public void exec(final String file, final int line, final int col, final Object expr) throws BSFException {
        try {
            this.evaler.parse(this.runtime, expr.toString(), file, line).run();
        }
        catch (Exception excptn) {
            throw new BSFException(100, "Exception", (Throwable)excptn);
        }
    }
    
    public Object call(final Object recv, final String method, final Object[] args) throws BSFException {
        try {
            return JavaEmbedUtils.invokeMethod(this.runtime, recv, method, args, Object.class);
        }
        catch (Exception excptn) {
            throw new BSFException(100, excptn.getMessage(), (Throwable)excptn);
        }
    }
    
    public void initialize(final BSFManager manager, final String language, final Vector someDeclaredBeans) throws BSFException {
        super.initialize(manager, language, someDeclaredBeans);
        this.runtime = JavaEmbedUtils.initialize(this.getClassPath(manager));
        for (int i = 0, size = someDeclaredBeans.size(); i < size; ++i) {
            final BSFDeclaredBean bean = someDeclaredBeans.elementAt(i);
            this.runtime.getGlobalVariables().define(GlobalVariable.variableName(bean.name), new BeanGlobalVariable(this.runtime, bean));
        }
        this.runtime.getGlobalVariables().defineReadonly("$bsf", new FunctionsGlobalVariable(this.runtime, new BSFFunctions(manager, (BSFEngine)this)));
    }
    
    private List getClassPath(final BSFManager manager) {
        return Arrays.asList(manager.getClassPath().split(System.getProperty("path.separator")));
    }
    
    public void declareBean(final BSFDeclaredBean bean) throws BSFException {
        this.runtime.getGlobalVariables().define(GlobalVariable.variableName(bean.name), new BeanGlobalVariable(this.runtime, bean));
    }
    
    public void undeclareBean(final BSFDeclaredBean bean) throws BSFException {
        this.runtime.getGlobalVariables().set(GlobalVariable.variableName(bean.name), this.runtime.getNil());
    }
    
    public void handleException(final BSFException bsfExcptn) {
        printException(this.runtime, (Exception)bsfExcptn.getTargetException());
    }
    
    private static void printException(final Ruby runtime, final Exception exception) {
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
        JavaEmbedUtils.terminate(this.runtime);
        this.runtime = null;
        super.terminate();
    }
    
    private static class BeanGlobalVariable implements IAccessor
    {
        private Ruby runtime;
        private BSFDeclaredBean bean;
        
        public BeanGlobalVariable(final Ruby runtime, final BSFDeclaredBean bean) {
            this.runtime = runtime;
            this.bean = bean;
        }
        
        public IRubyObject getValue() {
            final IRubyObject result = JavaUtil.convertJavaToRuby(this.runtime, this.bean.bean, this.bean.type);
            return (result instanceof JavaObject) ? Java.wrap(this.runtime, result) : result;
        }
        
        public IRubyObject setValue(final IRubyObject value) {
            this.bean.bean = value.toJava(Object.class);
            return value;
        }
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
