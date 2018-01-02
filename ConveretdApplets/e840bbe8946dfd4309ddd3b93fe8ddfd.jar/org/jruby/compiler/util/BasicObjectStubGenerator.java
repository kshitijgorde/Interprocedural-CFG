// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.util;

import org.jruby.BasicObjectStub;
import org.jruby.compiler.impl.SkinnyMethodAdapter;
import org.jruby.util.CodegenUtils;
import org.jruby.org.objectweb.asm.ClassVisitor;
import java.lang.reflect.Method;

public class BasicObjectStubGenerator
{
    private static final Method[] BASIC_OBJECT_STUB_METHODS;
    
    public static void addBasicObjectStubsToClass(final ClassVisitor cv) {
        for (final Method stub : BasicObjectStubGenerator.BASIC_OBJECT_STUB_METHODS) {
            if (!stub.getName().equals("getRuntime")) {
                if (!stub.getName().equals("getMetaClass")) {
                    final Class[] signature = new Class[stub.getParameterTypes().length - 1];
                    for (int i = 0; i < signature.length; ++i) {
                        signature[i] = stub.getParameterTypes()[i + 1];
                    }
                    final SkinnyMethodAdapter method = new SkinnyMethodAdapter(cv, 65, stub.getName(), CodegenUtils.sig(stub.getReturnType(), signature), null, null);
                    method.start();
                    method.aload(0);
                    int nextIndex = 1;
                    for (final Class argType : signature) {
                        if (argType.isPrimitive()) {
                            if (argType == Boolean.TYPE || argType == Byte.TYPE || argType == Character.TYPE || argType == Short.TYPE || argType == Integer.TYPE) {
                                method.iload(nextIndex);
                                ++nextIndex;
                            }
                            else if (argType == Long.TYPE) {
                                method.lload(nextIndex);
                                nextIndex += 2;
                            }
                            else if (argType == Float.TYPE) {
                                method.fload(nextIndex);
                                ++nextIndex;
                            }
                            else {
                                if (argType != Double.TYPE) {
                                    throw new RuntimeException("unknown primitive type: " + argType);
                                }
                                method.dload(nextIndex);
                                nextIndex += 2;
                            }
                        }
                        else {
                            method.aload(nextIndex);
                            ++nextIndex;
                        }
                    }
                    method.invokestatic(CodegenUtils.p(BasicObjectStub.class), stub.getName(), CodegenUtils.sig(stub.getReturnType(), (Class[])stub.getParameterTypes()));
                    final Class retType = stub.getReturnType();
                    if (retType == Void.TYPE) {
                        method.voidreturn();
                    }
                    else if (retType.isPrimitive()) {
                        if (retType == Boolean.TYPE || retType == Byte.TYPE || retType == Character.TYPE || retType == Short.TYPE || retType == Integer.TYPE) {
                            method.ireturn();
                        }
                        else if (retType == Long.TYPE) {
                            method.lreturn();
                        }
                        else if (retType == Float.TYPE) {
                            method.freturn();
                        }
                        else {
                            if (retType != Double.TYPE) {
                                throw new RuntimeException("unknown primitive type: " + retType);
                            }
                            method.dreturn();
                        }
                    }
                    else {
                        method.areturn();
                    }
                    method.end();
                }
            }
        }
    }
    
    static {
        BASIC_OBJECT_STUB_METHODS = BasicObjectStub.class.getDeclaredMethods();
    }
}
