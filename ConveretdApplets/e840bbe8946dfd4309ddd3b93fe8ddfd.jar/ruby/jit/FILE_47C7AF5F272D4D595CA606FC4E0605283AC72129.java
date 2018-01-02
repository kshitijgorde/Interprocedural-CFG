// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129 extends AbstractScript
{
    public FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129() {
        this.filename = "./lib//win32/errors.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("[]\uffffN\uffffinvert\uffffN\uffff[]\uffffN\uffff\u0002\u0006\u0005\u0000\u0002\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129 file_47C7AF5F272D4D595CA606FC4E0605283AC72129, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return module__0$RUBY$Win32(file_47C7AF5F272D4D595CA606FC4E0605283AC72129, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Win32(final FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Win32"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129.module__1$RUBY$Errors:(Lruby/jit/FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: aload_1        
        //    40: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: goto            51
        //    46: aload_1        
        //    47: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    50: athrow         
        //    51: aload_1        
        //    52: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    55: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     43     46     51     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException: -1
        //     at java.util.ArrayList.elementData(ArrayList.java:422)
        //     at java.util.ArrayList.remove(ArrayList.java:499)
        //     at com.strobel.assembler.ir.StackMappingVisitor.pop(StackMappingVisitor.java:267)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:595)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
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
    
    public static IRubyObject module__1$RUBY$Errors(final FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Errors"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_1        
        //    28: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    31: aload_0        
        //    32: aload_1        
        //    33: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    36: ldc             "SUCCESS"
        //    38: invokevirtual   ruby/jit/FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    41: aload_1        
        //    42: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    45: invokestatic    org/jruby/RubyFixnum.zero:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    48: aload_0        
        //    49: aload_1        
        //    50: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    53: ldc             "NOT_SUPPORTED"
        //    55: invokevirtual   ruby/jit/FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    58: aload_0        
        //    59: aload_1        
        //    60: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    63: bipush          50
        //    65: invokevirtual   ruby/jit/FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129.getFixnum0:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //    68: aload_0        
        //    69: aload_1        
        //    70: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    73: ldc             "INVALID_PARAMETERS"
        //    75: invokevirtual   ruby/jit/FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    78: aload_0        
        //    79: aload_1        
        //    80: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    83: bipush          87
        //    85: invokevirtual   ruby/jit/FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129.getFixnum1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //    88: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructHash:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyHash;
        //    91: dup            
        //    92: aload_1        
        //    93: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    96: aload_0        
        //    97: aload_1        
        //    98: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   101: ldc             "INVALID_STATE"
        //   103: invokevirtual   ruby/jit/FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   106: aload_0        
        //   107: aload_1        
        //   108: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   111: sipush          5023
        //   114: invokevirtual   ruby/jit/FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129.getFixnum2:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   117: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   120: dup            
        //   121: aload_1        
        //   122: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   125: aload_0        
        //   126: aload_1        
        //   127: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   130: ldc             "INVALID_HANDLE"
        //   132: invokevirtual   ruby/jit/FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   135: aload_0        
        //   136: aload_1        
        //   137: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   140: bipush          6
        //   142: invokevirtual   ruby/jit/FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129.getFixnum3:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   145: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   148: dup            
        //   149: aload_1        
        //   150: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   153: aload_0        
        //   154: aload_1        
        //   155: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   158: ldc             "ACCESS_DENIED"
        //   160: invokevirtual   ruby/jit/FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   163: aload_0        
        //   164: aload_1        
        //   165: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   168: bipush          65
        //   170: invokevirtual   ruby/jit/FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129.getFixnum4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   173: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   176: aload_1        
        //   177: ldc             "MAP"
        //   179: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   182: pop            
        //   183: aload_1        
        //   184: aload_2        
        //   185: aload_2        
        //   186: aload_0        
        //   187: ldc             "get_code_for_int"
        //   189: ldc             "method__2$RUBY$get_code_for_int"
        //   191: ldc             "i,1,0,-1"
        //   193: iconst_1       
        //   194: ldc             "./lib//win32/errors.rb"
        //   196: ldc             12
        //   198: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   201: ldc             "qi"
        //   203: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   206: pop            
        //   207: aload_1        
        //   208: aload_2        
        //   209: aload_2        
        //   210: aload_0        
        //   211: ldc             "get_int_for_code"
        //   213: ldc             "method__3$RUBY$get_int_for_code"
        //   215: ldc             "sym,1,0,-1"
        //   217: iconst_1       
        //   218: ldc             "./lib//win32/errors.rb"
        //   220: ldc             16
        //   222: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   225: ldc             "qsym"
        //   227: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   230: aload_1        
        //   231: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   234: goto            242
        //   237: aload_1        
        //   238: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   241: athrow         
        //   242: aload_1        
        //   243: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   246: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     234    237    242    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException: -1
        //     at java.util.ArrayList.elementData(ArrayList.java:422)
        //     at java.util.ArrayList.remove(ArrayList.java:499)
        //     at com.strobel.assembler.ir.StackMappingVisitor.pop(StackMappingVisitor.java:267)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:595)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
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
    
    @JRubyMethod(name = "get_code_for_int", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__2$RUBY$get_code_for_int(final FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload_3        
        //     7: aload           5
        //     9: swap           
        //    10: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    13: pop            
        //    14: aload_0        
        //    15: invokevirtual   ruby/jit/FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129.getCallSite0:()Lorg/jruby/runtime/CallSite;
        //    18: aload_1        
        //    19: aload_2        
        //    20: aload_0        
        //    21: invokevirtual   ruby/jit/FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129.getCallSite1:()Lorg/jruby/runtime/CallSite;
        //    24: aload_1        
        //    25: aload_2        
        //    26: aload_0        
        //    27: aload_1        
        //    28: ldc             "MAP"
        //    30: invokevirtual   ruby/jit/FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    36: aload           locals
        //    38: aload_1        
        //    39: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    42: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    48: dup            
        //    49: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    54: ifne            67
        //    57: pop            
        //    58: aload           locals
        //    60: aload_1        
        //    61: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    64: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     54      5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
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
    
    @JRubyMethod(name = "get_int_for_code", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$get_int_for_code(final FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload_3        
        //     7: aload           5
        //     9: swap           
        //    10: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    13: pop            
        //    14: aload_0        
        //    15: invokevirtual   ruby/jit/FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129.getCallSite2:()Lorg/jruby/runtime/CallSite;
        //    18: aload_1        
        //    19: aload_2        
        //    20: aload_0        
        //    21: aload_1        
        //    22: ldc             "MAP"
        //    24: invokevirtual   ruby/jit/FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129.getConstant1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    27: aload           locals
        //    29: aload_1        
        //    30: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    36: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: dup            
        //    40: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    45: ifne            58
        //    48: pop            
        //    49: aload           locals
        //    51: aload_1        
        //    52: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     45      5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
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
    
    public static IRubyObject module__1$RUBY$Errors(final FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129 file_47C7AF5F272D4D595CA606FC4E0605283AC72129, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Errors(file_47C7AF5F272D4D595CA606FC4E0605283AC72129, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Win32(final FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129 file_47C7AF5F272D4D595CA606FC4E0605283AC72129, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Win32(file_47C7AF5F272D4D595CA606FC4E0605283AC72129, threadContext, rubyObject, block);
    }
    
    @Override
    public IRubyObject load(final ThreadContext context, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        try {
            RuntimeHelpers.preLoad(context, ",0,0,-2");
            final IRubyObject _file__ = __file__(this, context, rubyObject, array, block);
            RuntimeHelpers.postLoad(context);
            return _file__;
        }
        finally {
            RuntimeHelpers.postLoad(context);
        }
    }
    
    public static void main(final String[] argv) {
        final FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129 file_47C7AF5F272D4D595CA606FC4E0605283AC72129 = new FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129();
        final String string = FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129.class.getClassLoader().getResource("ruby/jit/FILE_47C7AF5F272D4D595CA606FC4E0605283AC72129.class").toString();
        file_47C7AF5F272D4D595CA606FC4E0605283AC72129.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_47C7AF5F272D4D595CA606FC4E0605283AC72129.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
