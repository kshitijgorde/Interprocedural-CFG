// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.impl;

import org.jruby.runtime.Block;
import org.jruby.util.CodegenUtils;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.compiler.BodyCompiler;
import org.jruby.runtime.CallType;
import org.jruby.compiler.ArgumentsCallback;
import org.jruby.compiler.CompilerCallback;

public class InvokeDynamicInvocationCompiler extends StandardInvocationCompiler
{
    public InvokeDynamicInvocationCompiler(final BaseBodyCompiler methodCompiler, final SkinnyMethodAdapter method) {
        super(methodCompiler, method);
        methodCompiler.getScriptCompiler().getClassInitMethod();
    }
    
    public void invokeDynamic(final String name, final CompilerCallback receiverCallback, final ArgumentsCallback argsCallback, final CallType callType, final CompilerCallback closureArg, final boolean iterator) {
        if (callType == CallType.SUPER) {
            super.invokeDynamic(name, receiverCallback, argsCallback, callType, closureArg, iterator);
            return;
        }
        this.methodCompiler.loadThreadContext();
        this.methodCompiler.loadSelf();
        if (receiverCallback != null) {
            receiverCallback.call(this.methodCompiler);
        }
        else {
            this.methodCompiler.loadSelf();
        }
        this.methodCompiler.method.ldc(name);
        final String invokeName = (callType == CallType.NORMAL) ? "call" : "fcall";
        String signature = null;
        if (argsCallback == null) {
            if (closureArg == null) {
                signature = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class));
            }
            else {
                closureArg.call(this.methodCompiler);
                signature = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class, Block.class));
            }
        }
        else {
            argsCallback.call(this.methodCompiler);
            if (closureArg == null) {
                switch (argsCallback.getArity()) {
                    case 1: {
                        signature = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class, IRubyObject.class));
                        break;
                    }
                    case 2: {
                        signature = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class, IRubyObject.class, IRubyObject.class));
                        break;
                    }
                    case 3: {
                        signature = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class, IRubyObject.class, IRubyObject.class, IRubyObject.class));
                        break;
                    }
                    default: {
                        signature = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class, IRubyObject[].class));
                        break;
                    }
                }
            }
            else {
                closureArg.call(this.methodCompiler);
                switch (argsCallback.getArity()) {
                    case 1: {
                        signature = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class, IRubyObject.class, Block.class));
                        break;
                    }
                    case 2: {
                        signature = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class, IRubyObject.class, IRubyObject.class, Block.class));
                        break;
                    }
                    case 3: {
                        signature = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class, IRubyObject.class, IRubyObject.class, IRubyObject.class, Block.class));
                        break;
                    }
                    default: {
                        signature = CodegenUtils.sig(IRubyObject.class, CodegenUtils.params(ThreadContext.class, IRubyObject.class, IRubyObject.class, String.class, IRubyObject[].class, Block.class));
                        break;
                    }
                }
            }
        }
        this.method.invokedynamic(CodegenUtils.p(Object.class), invokeName, signature);
    }
}
