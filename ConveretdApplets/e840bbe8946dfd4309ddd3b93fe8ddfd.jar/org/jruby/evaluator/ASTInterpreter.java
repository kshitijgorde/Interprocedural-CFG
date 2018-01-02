// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.evaluator;

import org.jruby.ast.util.ArgsUtil;
import org.jruby.common.IRubyWarnings;
import org.jruby.runtime.InterpretedBlock;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.ast.BlockPassNode;
import org.jruby.ast.IterNode;
import org.jruby.util.ByteList;
import org.jruby.parser.StaticScope;
import org.jruby.RubyArray;
import org.jruby.ast.ArrayNode;
import org.jruby.ast.MultipleAsgnNode;
import org.jruby.RubyString;
import org.jruby.runtime.Frame;
import org.jruby.runtime.DynamicScope;
import org.jruby.exceptions.JumpException;
import org.jruby.RubyLocalJumpError;
import org.jruby.runtime.Binding;
import org.jruby.runtime.RubyEvent;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.Node;
import org.jruby.RubyModule;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;

public class ASTInterpreter
{
    public static IRubyObject INTERPRET_METHOD(final Ruby runtime, final ThreadContext context, final String file, final int line, final RubyModule implClass, final Node node, final String name, final IRubyObject self, final Block block, final boolean isTraceable) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload           implClass
        //     2: invokevirtual   org/jruby/RubyModule.getName:()Ljava/lang/String;
        //     5: astore          className
        //     7: aload_1         /* context */
        //     8: aload           className
        //    10: aload           name
        //    12: aload_2         /* file */
        //    13: iload_3         /* line */
        //    14: invokestatic    org/jruby/runtime/ThreadContext.pushBacktrace:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V
        //    17: iload           isTraceable
        //    19: ifeq            31
        //    22: aload_0         /* runtime */
        //    23: aload_1         /* context */
        //    24: aload           name
        //    26: aload           implClass
        //    28: invokestatic    org/jruby/evaluator/ASTInterpreter.methodPreTrace:(Lorg/jruby/Ruby;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;Lorg/jruby/RubyModule;)V
        //    31: aload           node
        //    33: aload_0         /* runtime */
        //    34: aload_1         /* context */
        //    35: aload           self
        //    37: aload           block
        //    39: invokevirtual   org/jruby/ast/Node.interpret:(Lorg/jruby/Ruby;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    42: astore          11
        //    44: jsr             58
        //    47: aload           11
        //    49: areturn        
        //    50: astore          12
        //    52: jsr             58
        //    55: aload           12
        //    57: athrow         
        //    58: astore          13
        //    60: iload           isTraceable
        //    62: ifeq            99
        //    65: aload_0         /* runtime */
        //    66: aload_1         /* context */
        //    67: aload           name
        //    69: aload           implClass
        //    71: invokestatic    org/jruby/evaluator/ASTInterpreter.methodPostTrace:(Lorg/jruby/Ruby;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;Lorg/jruby/RubyModule;)V
        //    74: jsr             88
        //    77: goto            96
        //    80: astore          14
        //    82: jsr             88
        //    85: aload           14
        //    87: athrow         
        //    88: astore          15
        //    90: aload_1         /* context */
        //    91: invokestatic    org/jruby/runtime/ThreadContext.popBacktrace:(Lorg/jruby/runtime/ThreadContext;)V
        //    94: ret             15
        //    96: goto            103
        //    99: aload_1         /* context */
        //   100: invokestatic    org/jruby/runtime/ThreadContext.popBacktrace:(Lorg/jruby/runtime/ThreadContext;)V
        //   103: ret             13
        //    LocalVariableTable:
        //  Start  Length  Slot  Name         Signature
        //  -----  ------  ----  -----------  ---------------------------------------
        //  7      43      10    className    Ljava/lang/String;
        //  0      105     0     runtime      Lorg/jruby/Ruby;
        //  0      105     1     context      Lorg/jruby/runtime/ThreadContext;
        //  0      105     2     file         Ljava/lang/String;
        //  0      105     3     line         I
        //  0      105     4     implClass    Lorg/jruby/RubyModule;
        //  0      105     5     node         Lorg/jruby/ast/Node;
        //  0      105     6     name         Ljava/lang/String;
        //  0      105     7     self         Lorg/jruby/runtime/builtin/IRubyObject;
        //  0      105     8     block        Lorg/jruby/runtime/Block;
        //  0      105     9     isTraceable  Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  0      47     50     58     Any
        //  50     55     50     58     Any
        //  65     77     80     88     Any
        //  80     85     80     88     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2162)
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
    
    public static IRubyObject INTERPRET_EVAL(final Ruby runtime, final ThreadContext context, final Node node, final String name, final IRubyObject self, final Block block) {
        try {
            ThreadContext.pushBacktrace(context, self.getMetaClass().getName(), name, node.getPosition());
            return node.interpret(runtime, context, self, block);
        }
        finally {
            ThreadContext.popBacktrace(context);
        }
    }
    
    public static IRubyObject INTERPRET_EVAL(final Ruby runtime, final ThreadContext context, final String file, final int line, final Node node, final String name, final IRubyObject self, final Block block) {
        try {
            ThreadContext.pushBacktrace(context, self.getMetaClass().getName(), name, file, line);
            return node.interpret(runtime, context, self, block);
        }
        finally {
            ThreadContext.popBacktrace(context);
        }
    }
    
    public static IRubyObject INTERPRET_CLASS(final Ruby runtime, final ThreadContext context, final Node node, final String name, final IRubyObject self, final Block block) {
        try {
            ThreadContext.pushBacktrace(context, self.getMetaClass().getName(), name, node.getPosition());
            return node.interpret(runtime, context, self, block);
        }
        finally {
            ThreadContext.popBacktrace(context);
        }
    }
    
    public static IRubyObject INTERPRET_BLOCK(final Ruby runtime, final ThreadContext context, final String file, final int line, final Node node, final String name, final IRubyObject self, final Block block) {
        try {
            ThreadContext.pushBacktrace(context, self.getMetaClass().getName(), name, file, line);
            return node.interpret(runtime, context, self, block);
        }
        finally {
            ThreadContext.popBacktrace(context);
        }
    }
    
    public static IRubyObject INTERPRET_ROOT(final Ruby runtime, final ThreadContext context, final Node node, final IRubyObject self, final Block block) {
        try {
            ThreadContext.pushBacktrace(context, self.getMetaClass().getName(), "(root)", node.getPosition());
            return node.interpret(runtime, context, self, block);
        }
        finally {
            ThreadContext.popBacktrace(context);
        }
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
    
    @Deprecated
    public static IRubyObject evalWithBinding(final ThreadContext context, final IRubyObject src, final Binding binding) {
        return evalWithBinding(context, binding.getSelf(), src, binding);
    }
    
    public static IRubyObject evalWithBinding(final ThreadContext context, final IRubyObject self, final IRubyObject src, final Binding binding) {
        final Ruby runtime = src.getRuntime();
        final DynamicScope evalScope = binding.getDynamicScope().getEvalScope();
        evalScope.getStaticScope().determineModule();
        final Frame lastFrame = context.preEvalWithBinding(binding);
        try {
            final RubyString source = src.convertToString();
            final Node node = runtime.parseEval(source.getByteList(), binding.getFile(), evalScope, binding.getLine());
            return INTERPRET_EVAL(runtime, context, binding.getFile(), binding.getLine(), node, binding.getMethod(), self, binding.getFrame().getBlock());
        }
        catch (JumpException.BreakJump bj) {
            throw runtime.newLocalJumpError(RubyLocalJumpError.Reason.BREAK, (IRubyObject)bj.getValue(), "unexpected break");
        }
        catch (JumpException.RedoJump rj) {
            throw runtime.newLocalJumpError(RubyLocalJumpError.Reason.REDO, (IRubyObject)rj.getValue(), "unexpected redo");
        }
        catch (StackOverflowError soe) {
            throw runtime.newSystemStackError("stack level too deep", soe);
        }
        finally {
            context.postEvalWithBinding(binding, lastFrame);
        }
    }
    
    public static IRubyObject evalSimple(final ThreadContext context, final IRubyObject self, final RubyString src, final String file, final int lineNumber) {
        assert file != null;
        final Ruby runtime = src.getRuntime();
        final String savedFile = context.getFile();
        final int savedLine = context.getLine();
        final RubyString source = src.convertToString();
        final DynamicScope evalScope = context.getCurrentScope().getEvalScope();
        evalScope.getStaticScope().determineModule();
        try {
            final Node node = runtime.parseEval(source.getByteList(), file, evalScope, lineNumber);
            return INTERPRET_EVAL(runtime, context, file, lineNumber, node, "(eval)", self, Block.NULL_BLOCK);
        }
        catch (JumpException.BreakJump bj) {
            throw runtime.newLocalJumpError(RubyLocalJumpError.Reason.BREAK, (IRubyObject)bj.getValue(), "unexpected break");
        }
        catch (StackOverflowError soe) {
            throw runtime.newSystemStackError("stack level too deep", soe);
        }
        finally {
            context.setFile(savedFile);
            context.setLine(savedLine);
        }
    }
    
    public static void callTraceFunction(final Ruby runtime, final ThreadContext context, final RubyEvent event) {
        final String name = context.getFrameName();
        final RubyModule type = context.getFrameKlazz();
        runtime.callEventHooks(context, event, context.getFile(), context.getLine(), name, type);
    }
    
    public static IRubyObject pollAndReturn(final ThreadContext context, final IRubyObject result) {
        context.pollThreadEvents();
        return result;
    }
    
    public static IRubyObject multipleAsgnArrayNode(final Ruby runtime, final ThreadContext context, final MultipleAsgnNode iVisited, final ArrayNode node, final IRubyObject self, final Block aBlock) {
        final IRubyObject[] array = new IRubyObject[node.size()];
        for (int i = 0; i < node.size(); ++i) {
            array[i] = node.get(i).interpret(runtime, context, self, aBlock);
        }
        return AssignmentVisitor.multiAssign(runtime, context, self, iVisited, RubyArray.newArrayNoCopyLight(runtime, array), false);
    }
    
    public static IRubyObject evalClassDefinitionBody(final Ruby runtime, final ThreadContext context, final StaticScope scope, final Node bodyNode, final RubyModule type, final IRubyObject self, final Block block) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1         /* context */
        //     1: aload_2         /* scope */
        //     2: aload           type
        //     4: invokevirtual   org/jruby/runtime/ThreadContext.preClassEval:(Lorg/jruby/parser/StaticScope;Lorg/jruby/RubyModule;)V
        //     7: aload_0         /* runtime */
        //     8: invokevirtual   org/jruby/Ruby.hasEventHooks:()Z
        //    11: ifeq            22
        //    14: aload_0         /* runtime */
        //    15: aload_1         /* context */
        //    16: getstatic       org/jruby/runtime/RubyEvent.CLASS:Lorg/jruby/runtime/RubyEvent;
        //    19: invokestatic    org/jruby/evaluator/ASTInterpreter.callTraceFunction:(Lorg/jruby/Ruby;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/RubyEvent;)V
        //    22: aload_3         /* bodyNode */
        //    23: ifnonnull       38
        //    26: aload_0         /* runtime */
        //    27: invokevirtual   org/jruby/Ruby.getNil:()Lorg/jruby/runtime/builtin/IRubyObject;
        //    30: astore          7
        //    32: jsr             115
        //    35: aload           7
        //    37: areturn        
        //    38: aload           type
        //    40: invokevirtual   org/jruby/RubyModule.getBaseName:()Ljava/lang/String;
        //    43: astore          name
        //    45: aload           name
        //    47: ifnonnull       87
        //    50: aload           type
        //    52: invokevirtual   org/jruby/RubyModule.isSingleton:()Z
        //    55: ifeq            66
        //    58: ldc_w           "__singleton__"
        //    61: astore          name
        //    63: goto            87
        //    66: aload           type
        //    68: invokevirtual   org/jruby/RubyModule.isModule:()Z
        //    71: ifeq            82
        //    74: ldc_w           "<anonymous module>"
        //    77: astore          name
        //    79: goto            87
        //    82: ldc_w           "<anonymous class>"
        //    85: astore          name
        //    87: aload_0         /* runtime */
        //    88: aload_1         /* context */
        //    89: aload_3         /* bodyNode */
        //    90: aload           name
        //    92: aload           type
        //    94: aload           block
        //    96: invokestatic    org/jruby/evaluator/ASTInterpreter.INTERPRET_CLASS:(Lorg/jruby/Ruby;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/ast/Node;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    99: astore          8
        //   101: jsr             115
        //   104: aload           8
        //   106: areturn        
        //   107: astore          9
        //   109: jsr             115
        //   112: aload           9
        //   114: athrow         
        //   115: astore          10
        //   117: aload_0         /* runtime */
        //   118: invokevirtual   org/jruby/Ruby.hasEventHooks:()Z
        //   121: ifeq            132
        //   124: aload_0         /* runtime */
        //   125: aload_1         /* context */
        //   126: getstatic       org/jruby/runtime/RubyEvent.END:Lorg/jruby/runtime/RubyEvent;
        //   129: invokestatic    org/jruby/evaluator/ASTInterpreter.callTraceFunction:(Lorg/jruby/Ruby;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/RubyEvent;)V
        //   132: jsr             146
        //   135: goto            154
        //   138: astore          11
        //   140: jsr             146
        //   143: aload           11
        //   145: athrow         
        //   146: astore          12
        //   148: aload_1         /* context */
        //   149: invokevirtual   org/jruby/runtime/ThreadContext.postClassEval:()V
        //   152: ret             12
        //   154: ret             10
        //    LocalVariableTable:
        //  Start  Length  Slot  Name      Signature
        //  -----  ------  ----  --------  ---------------------------------------
        //  45     62      7     name      Ljava/lang/String;
        //  0      156     0     runtime   Lorg/jruby/Ruby;
        //  0      156     1     context   Lorg/jruby/runtime/ThreadContext;
        //  0      156     2     scope     Lorg/jruby/parser/StaticScope;
        //  0      156     3     bodyNode  Lorg/jruby/ast/Node;
        //  0      156     4     type      Lorg/jruby/RubyModule;
        //  0      156     5     self      Lorg/jruby/runtime/builtin/IRubyObject;
        //  0      156     6     block     Lorg/jruby/runtime/Block;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  7      35     107    115    Any
        //  38     104    107    115    Any
        //  107    112    107    115    Any
        //  117    135    138    146    Any
        //  138    143    138    146    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2162)
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
    
    public static ByteList getArgumentDefinition(final Ruby runtime, final ThreadContext context, final Node node, final ByteList type, final IRubyObject self, final Block block) {
        if (node == null) {
            return type;
        }
        if (node instanceof ArrayNode) {
            final ArrayNode list = (ArrayNode)node;
            for (int size = list.size(), i = 0; i < size; ++i) {
                if (list.get(i).definition(runtime, context, self, block) == null) {
                    return null;
                }
            }
        }
        else if (node.definition(runtime, context, self, block) == null) {
            return null;
        }
        return type;
    }
    
    public static Block getBlock(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block currentBlock, final Node blockNode) {
        if (blockNode == null) {
            return Block.NULL_BLOCK;
        }
        if (blockNode instanceof IterNode) {
            return getIterNodeBlock(blockNode, context, self);
        }
        if (blockNode instanceof BlockPassNode) {
            return getBlockPassBlock(blockNode, runtime, context, self, currentBlock);
        }
        assert false : "Trying to get block from something which cannot deliver";
        return null;
    }
    
    private static Block getBlockPassBlock(final Node blockNode, final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block currentBlock) {
        final Node bodyNode = ((BlockPassNode)blockNode).getBodyNode();
        IRubyObject proc;
        if (bodyNode == null) {
            proc = runtime.getNil();
        }
        else {
            proc = bodyNode.interpret(runtime, context, self, currentBlock);
        }
        return RuntimeHelpers.getBlockFromBlockPassBody(proc, currentBlock);
    }
    
    private static Block getIterNodeBlock(final Node blockNode, final ThreadContext context, final IRubyObject self) {
        final IterNode iterNode = (IterNode)blockNode;
        final StaticScope scope = iterNode.getScope();
        scope.determineModule();
        return InterpretedBlock.newInterpretedClosure(context, iterNode.getBlockBody(), self);
    }
    
    public static RubyModule getClassVariableBase(final ThreadContext context, final Ruby runtime) {
        StaticScope scope = context.getCurrentScope().getStaticScope();
        RubyModule rubyClass = scope.getModule();
        while (rubyClass.isSingleton() || rubyClass == runtime.getDummy()) {
            if (scope == null) {
                return null;
            }
            scope = scope.getPreviousCRefScope();
            rubyClass = scope.getModule();
            if (scope.getPreviousCRefScope() != null) {
                continue;
            }
            runtime.getWarnings().warn(IRubyWarnings.ID.CVAR_FROM_TOPLEVEL_SINGLETON_METHOD, "class variable access from toplevel singleton method", new Object[0]);
        }
        return rubyClass;
    }
    
    public static IRubyObject[] setupArgs(final Ruby runtime, final ThreadContext context, final Node node, final IRubyObject self, final Block aBlock) {
        if (node == null) {
            return IRubyObject.NULL_ARRAY;
        }
        if (node instanceof ArrayNode) {
            final ArrayNode argsArrayNode = (ArrayNode)node;
            final String savedFile = context.getFile();
            final int savedLine = context.getLine();
            final int size = argsArrayNode.size();
            final IRubyObject[] argsArray = new IRubyObject[size];
            for (int i = 0; i < size; ++i) {
                argsArray[i] = argsArrayNode.get(i).interpret(runtime, context, self, aBlock);
            }
            context.setFile(savedFile);
            context.setLine(savedLine);
            return argsArray;
        }
        return ArgsUtil.convertToJavaArray(node.interpret(runtime, context, self, aBlock));
    }
}
