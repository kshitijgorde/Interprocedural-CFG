// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.anno;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import java.lang.reflect.Modifier;
import org.jruby.util.CodegenUtils;
import java.lang.reflect.Method;

public class JavaMethodDescriptor
{
    public final boolean isStatic;
    public final boolean hasContext;
    public final boolean hasBlock;
    public final boolean hasVarArgs;
    public final int actualRequired;
    public final int arity;
    public final int required;
    public final int optional;
    public final boolean rest;
    private final Class[] parameters;
    private final Class returnClass;
    public final JRubyMethod anno;
    public final int modifiers;
    private final Class declaringClass;
    public final String declaringClassName;
    public final String declaringClassPath;
    public final String name;
    public final String signature;
    public final Class[] argumentTypes;
    
    public JavaMethodDescriptor(final Method method) {
        this.anno = method.getAnnotation(JRubyMethod.class);
        this.modifiers = method.getModifiers();
        this.declaringClass = method.getDeclaringClass();
        this.declaringClassName = this.declaringClass.getName();
        this.declaringClassPath = CodegenUtils.p(this.declaringClass);
        this.name = method.getName();
        this.isStatic = Modifier.isStatic(this.modifiers);
        this.parameters = method.getParameterTypes();
        this.returnClass = method.getReturnType();
        if (this.parameters.length > 0) {
            this.hasContext = (this.parameters[0] == ThreadContext.class);
            this.hasBlock = (this.parameters[this.parameters.length - 1] == Block.class);
        }
        else {
            this.hasContext = false;
            this.hasBlock = false;
        }
        if (this.hasContext) {
            if (this.hasBlock) {
                this.hasVarArgs = (this.parameters[this.parameters.length - 2] == IRubyObject[].class);
            }
            else {
                this.hasVarArgs = (this.parameters[this.parameters.length - 1] == IRubyObject[].class);
            }
        }
        else if (this.hasBlock) {
            this.hasVarArgs = (this.parameters.length > 1 && this.parameters[this.parameters.length - 2] == IRubyObject[].class);
        }
        else {
            this.hasVarArgs = (this.parameters.length > 0 && this.parameters[this.parameters.length - 1] == IRubyObject[].class);
        }
        final int start = (this.hasContext + this.isStatic) ? 1 : 0;
        final int end = this.parameters.length - (this.hasBlock ? 1 : 0);
        this.argumentTypes = new Class[end - start];
        System.arraycopy(this.parameters, start, this.argumentTypes, 0, end - start);
        this.optional = this.anno.optional();
        this.rest = this.anno.rest();
        this.required = this.anno.required();
        if (this.optional == 0 && !this.rest) {
            int args = this.parameters.length;
            if (args == 0) {
                this.actualRequired = 0;
            }
            else {
                if (this.isStatic) {
                    --args;
                }
                if (this.hasContext) {
                    --args;
                }
                if (this.hasBlock) {
                    --args;
                }
                this.actualRequired = args;
            }
        }
        else {
            int args = this.parameters.length;
            if (args == 0) {
                this.actualRequired = 0;
            }
            else {
                if (this.isStatic) {
                    --args;
                }
                if (this.hasContext) {
                    --args;
                }
                if (this.hasBlock) {
                    --args;
                }
                --args;
                this.actualRequired = args;
            }
            if (this.actualRequired != 0) {
                throw new RuntimeException("Combining specific args with IRubyObject[] is not yet supported");
            }
        }
        final int arityRequired = Math.max(this.required, this.actualRequired);
        this.arity = ((this.optional > 0 || this.rest) ? (-(arityRequired + 1)) : arityRequired);
        this.signature = CodegenUtils.sig(method.getReturnType(), (Class[])method.getParameterTypes());
    }
    
    public Class getDeclaringClass() {
        return this.declaringClass;
    }
    
    public Class[] getParameterClasses() {
        return this.parameters;
    }
    
    public Class getReturnClass() {
        return this.returnClass;
    }
}
