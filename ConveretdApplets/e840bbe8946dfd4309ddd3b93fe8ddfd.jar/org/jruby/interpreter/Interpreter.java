// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.interpreter;

import org.jruby.runtime.RubyEvent;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.compiler.ir.instructions.Instr;
import org.jruby.runtime.Frame;
import org.jruby.exceptions.JumpException;
import org.jruby.RubyLocalJumpError;
import org.jruby.compiler.ir.instructions.BREAK_Instr;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.compiler.ir.instructions.ReturnInstr;
import org.jruby.exceptions.RaiseException;
import org.jruby.compiler.ir.IRClosure;
import org.jruby.compiler.ir.representations.CFG;
import org.jruby.runtime.ThreadContext;
import org.jruby.compiler.ir.IRMethod;
import org.jruby.internal.runtime.methods.InterpretedIRMethod;
import org.jruby.RubyModule;
import org.jruby.compiler.ir.IRScript;
import org.jruby.compiler.ir.IRScope;
import org.jruby.ast.RootNode;
import org.jruby.compiler.ir.IRBuilder;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.Node;
import org.jruby.Ruby;

public class Interpreter
{
    private static boolean debug;
    private static int interpInstrsCount;
    
    public static IRubyObject interpret(final Ruby runtime, final Node rootNode, final IRubyObject self) {
        final IRScope scope = new IRBuilder().buildRoot((RootNode)rootNode);
        scope.prepareForInterpretation();
        return interpretTop(runtime, scope, self);
    }
    
    public static boolean isDebug() {
        return Interpreter.debug;
    }
    
    public static IRubyObject interpretTop(final Ruby runtime, final IRScope scope, final IRubyObject self) {
        assert scope instanceof IRScript : "Must be an IRScript scope at Top!!!";
        final IRScript root = (IRScript)scope;
        if (root.getStaticScope().getModule() == null) {
            root.getStaticScope().setModule(runtime.getObject());
        }
        final IRMethod rootMethod = root.getRootClass().getRootMethod();
        final RubyModule metaclass = self.getMetaClass();
        final InterpretedIRMethod method = new InterpretedIRMethod(rootMethod, metaclass);
        final IRubyObject rv = method.call(runtime.getCurrentContext(), self, metaclass, "", new IRubyObject[0]);
        if (Interpreter.debug) {
            System.out.println("-- Interpreted " + Interpreter.interpInstrsCount + " instructions");
        }
        return rv;
    }
    
    public static IRubyObject interpret(final ThreadContext context, final CFG cfg, final InterpreterContext interp) {
        final boolean inClosure = cfg.getScope() instanceof IRClosure;
        try {
            interp.setMethodExitLabel(cfg.getExitBB().getLabel());
            final IRubyObject self = (IRubyObject)interp.getSelf();
            final Instr[] instrs = cfg.prepareForInterpretation();
            final int n = instrs.length;
            int ipc = 0;
            Instr lastInstr = null;
            while (ipc < n) {
                ++Interpreter.interpInstrsCount;
                lastInstr = instrs[ipc];
                if (Interpreter.debug) {
                    System.out.println("EXEC'ing: " + lastInstr);
                }
                try {
                    final Label jumpTarget = lastInstr.interpret(interp, self);
                    ipc = ((jumpTarget == null) ? (ipc + 1) : jumpTarget.getTargetPC());
                }
                catch (RaiseException re) {
                    ipc = cfg.getRescuerPC(lastInstr);
                    if (ipc == -1) {
                        throw re;
                    }
                    interp.setException(re.getException());
                }
            }
            final IRubyObject rv = (IRubyObject)interp.getReturnValue();
            if (lastInstr instanceof ReturnInstr) {
                if (inClosure) {
                    throw RuntimeHelpers.returnJump(rv, context);
                }
            }
            else if (lastInstr instanceof BREAK_Instr) {
                if (!inClosure) {
                    throw context.getRuntime().newLocalJumpError(RubyLocalJumpError.Reason.BREAK, rv, "unexpected break");
                }
                RuntimeHelpers.breakJump(context, rv);
            }
            return rv;
        }
        catch (JumpException.ReturnJump rj) {
            if (inClosure) {
                throw rj;
            }
            return (IRubyObject)rj.getValue();
        }
        finally {
            if (interp.getFrame() != null) {
                context.popFrame();
                interp.setFrame(null);
            }
            if (interp.hasAllocatedDynamicScope()) {
                context.postMethodScopeOnly();
            }
        }
    }
    
    public static IRubyObject INTERPRET_METHOD(final ThreadContext context, final CFG cfg, final InterpreterContext interp, final String name, final RubyModule implClass, final boolean isTraceable) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2         /* interp */
        //     1: invokeinterface org/jruby/interpreter/InterpreterContext.getRuntime:()Lorg/jruby/Ruby;
        //     6: astore          runtime
        //     8: aload_3         /* name */
        //     9: ifnull          21
        //    12: aload_3         /* name */
        //    13: ldc             ""
        //    15: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    18: ifeq            25
        //    21: iconst_1       
        //    22: goto            26
        //    25: iconst_0       
        //    26: istore          syntheticMethod
        //    28: aload           implClass
        //    30: invokevirtual   org/jruby/RubyModule.getName:()Ljava/lang/String;
        //    33: astore          className
        //    35: iload           syntheticMethod
        //    37: ifne            55
        //    40: aload_0         /* context */
        //    41: aload           className
        //    43: aload_3         /* name */
        //    44: aload_0         /* context */
        //    45: invokevirtual   org/jruby/runtime/ThreadContext.getFile:()Ljava/lang/String;
        //    48: aload_0         /* context */
        //    49: invokevirtual   org/jruby/runtime/ThreadContext.getLine:()I
        //    52: invokestatic    org/jruby/runtime/ThreadContext.pushBacktrace:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
        //    55: iload           isTraceable
        //    57: ifeq            69
        //    60: aload           runtime
        //    62: aload_0         /* context */
        //    63: aload_3         /* name */
        //    64: aload           implClass
        //    66: invokestatic    org/jruby/interpreter/Interpreter.methodPreTrace:(Lorg/jruby/Ruby;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;Lorg/jruby/RubyModule;)V
        //    69: aload_0         /* context */
        //    70: aload_1         /* cfg */
        //    71: aload_2         /* interp */
        //    72: invokestatic    org/jruby/interpreter/Interpreter.interpret:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/compiler/ir/representations/CFG;Lorg/jruby/interpreter/InterpreterContext;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: astore          9
        //    77: jsr             91
        //    80: aload           9
        //    82: areturn        
        //    83: astore          10
        //    85: jsr             91
        //    88: aload           10
        //    90: athrow         
        //    91: astore          11
        //    93: iload           isTraceable
        //    95: ifeq            137
        //    98: aload           runtime
        //   100: aload_0         /* context */
        //   101: aload_3         /* name */
        //   102: aload           implClass
        //   104: invokestatic    org/jruby/interpreter/Interpreter.methodPostTrace:(Lorg/jruby/Ruby;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;Lorg/jruby/RubyModule;)V
        //   107: jsr             121
        //   110: goto            134
        //   113: astore          12
        //   115: jsr             121
        //   118: aload           12
        //   120: athrow         
        //   121: astore          13
        //   123: iload           syntheticMethod
        //   125: ifne            132
        //   128: aload_0         /* context */
        //   129: invokestatic    org/jruby/runtime/ThreadContext.popBacktrace:(Lorg/jruby/runtime/ThreadContext;)V
        //   132: ret             13
        //   134: goto            146
        //   137: iload           syntheticMethod
        //   139: ifne            146
        //   142: aload_0         /* context */
        //   143: invokestatic    org/jruby/runtime/ThreadContext.popBacktrace:(Lorg/jruby/runtime/ThreadContext;)V
        //   146: ret             11
        //    LocalVariableTable:
        //  Start  Length  Slot  Name             Signature
        //  -----  ------  ----  ---------------  -------------------------------------------
        //  35     48      8     className        Ljava/lang/String;
        //  0      148     0     context          Lorg/jruby/runtime/ThreadContext;
        //  0      148     1     cfg              Lorg/jruby/compiler/ir/representations/CFG;
        //  0      148     2     interp           Lorg/jruby/interpreter/InterpreterContext;
        //  0      148     3     name             Ljava/lang/String;
        //  0      148     4     implClass        Lorg/jruby/RubyModule;
        //  0      148     5     isTraceable      Z
        //  8      140     6     runtime          Lorg/jruby/Ruby;
        //  28     120     7     syntheticMethod  Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  28     80     83     91     Any
        //  83     88     83     91     Any
        //  98     110    113    121    Any
        //  113    118    113    121    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0132 (coming from #0120).
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2181)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static void methodPreTrace(final Ruby runtime, final ThreadContext context, final String name, final RubyModule implClass) {
        if (runtime.hasEventHooks()) {
            context.trace(RubyEvent.CALL, name, implClass);
        }
    }
    
    private static void methodPostTrace(final Ruby runtime, final ThreadContext context, final String name, final RubyModule implClass) {
        if (runtime.hasEventHooks()) {
            context.trace(RubyEvent.RETURN, name, implClass);
        }
    }
    
    static {
        Interpreter.debug = Boolean.parseBoolean(System.getProperty("jruby.ir.debug", "false"));
        Interpreter.interpInstrsCount = 0;
    }
}
