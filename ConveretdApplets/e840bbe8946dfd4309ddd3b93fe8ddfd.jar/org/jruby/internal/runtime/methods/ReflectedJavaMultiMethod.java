// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.anno.JRubyMethod;
import java.lang.reflect.Method;
import java.util.List;
import org.jruby.RubyModule;

public class ReflectedJavaMultiMethod extends JavaMethod
{
    private ReflectedJavaMethod method0;
    private ReflectedJavaMethod method1;
    private ReflectedJavaMethod method2;
    private ReflectedJavaMethod method3;
    private ReflectedJavaMethod methodN;
    
    public ReflectedJavaMultiMethod(final RubyModule implementationClass, final List<Method> methods, final List<JRubyMethod> annotations) {
        super(implementationClass, annotations.get(1).visibility());
        ReflectedJavaMethod nMethod = null;
        boolean foundActualNMethod = false;
        for (int i = 0; i < methods.size(); ++i) {
            final Method method = methods.get(i);
            final JRubyMethod annotation = annotations.get(i);
            final ReflectedJavaMethod dynMethod = new ReflectedJavaMethod(implementationClass, method, annotation);
            switch (dynMethod.arityValue) {
                case 0: {
                    this.method0 = dynMethod;
                    if (nMethod == null && !foundActualNMethod) {
                        nMethod = dynMethod;
                        break;
                    }
                    break;
                }
                case 1: {
                    this.method1 = dynMethod;
                    if (nMethod == null && !foundActualNMethod) {
                        nMethod = dynMethod;
                        break;
                    }
                    break;
                }
                case 2: {
                    this.method2 = dynMethod;
                    if (nMethod == null && !foundActualNMethod) {
                        nMethod = dynMethod;
                        break;
                    }
                    break;
                }
                case 3: {
                    this.method3 = dynMethod;
                    if (nMethod == null && !foundActualNMethod) {
                        nMethod = dynMethod;
                        break;
                    }
                    break;
                }
                default: {
                    this.methodN = dynMethod;
                    nMethod = dynMethod;
                    foundActualNMethod = true;
                    break;
                }
            }
        }
        if (this.methodN == null) {
            this.methodN = nMethod;
        }
        if (this.method0 == null) {
            this.method0 = this.methodN;
        }
        if (this.method1 == null) {
            this.method1 = this.methodN;
        }
        if (this.method2 == null) {
            this.method2 = this.methodN;
        }
        if (this.method3 == null) {
            this.method3 = this.methodN;
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
        switch (args.length) {
            case 0: {
                return this.method0.call(context, self, clazz, name, block);
            }
            case 1: {
                return this.method1.call(context, self, clazz, name, args[0], block);
            }
            case 2: {
                return this.method2.call(context, self, clazz, name, args[0], args[1], block);
            }
            case 3: {
                return this.method3.call(context, self, clazz, name, args[0], args[1], args[2], block);
            }
            default: {
                return this.methodN.call(context, self, clazz, name, args, block);
            }
        }
    }
}
