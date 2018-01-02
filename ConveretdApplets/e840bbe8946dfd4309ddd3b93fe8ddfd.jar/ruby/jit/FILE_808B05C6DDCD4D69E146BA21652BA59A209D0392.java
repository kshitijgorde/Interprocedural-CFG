// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.RubyString;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392 extends AbstractScript
{
    public FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392() {
        this.filename = "./lib//lister/util.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("autoload\uffffF\uffffattr_accessor\uffffF\ufffflogger\uffffV\uffffdebug\uffffN\ufffflogger\uffffV\uffff`\uffffF\ufffflogger\uffffV\uffffdebug\uffffN\ufffflogger\uffffV\uffffpopen3\uffffN\uffffread\uffffN\uffff\u0002\u0002\u0000\u0000\u0001\u0000\u0000\u0000\u0000\u0001\u0000\u0000\u0004\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(3, "", this.getEncoding0());
        this.setByteList(2, "'", this.getEncoding0());
        this.setByteList(1, "starting `", this.getEncoding0());
        this.setByteList(0, "open3", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392 file_808B05C6DDCD4D69E146BA21652BA59A209D0392, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_808B05C6DDCD4D69E146BA21652BA59A209D0392.getCallSite0().call(threadContext, rubyObject, rubyObject, file_808B05C6DDCD4D69E146BA21652BA59A209D0392.getSymbol0(threadContext.runtime, "Open3"), file_808B05C6DDCD4D69E146BA21652BA59A209D0392.getString0(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_808B05C6DDCD4D69E146BA21652BA59A209D0392, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Lister"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392.module__1$RUBY$Util:(Lruby/jit/FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject module__1$RUBY$Util(final FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Util"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: invokevirtual   ruby/jit/FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392.getCallSite1:()Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_2        
        //    34: aload_0        
        //    35: aload_1        
        //    36: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    39: ldc             "logger"
        //    41: invokevirtual   ruby/jit/FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    44: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: pop            
        //    48: aload_1        
        //    49: aload_2        
        //    50: aload_0        
        //    51: ldc             "spawn"
        //    53: ldc             "method__2$RUBY$spawn"
        //    55: ldc             "cmd,1,0,-1"
        //    57: iconst_1       
        //    58: ldc             "./lib//lister/util.rb"
        //    60: ldc             6
        //    62: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    65: ldc             "qcmd"
        //    67: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: pop            
        //    71: aload_1        
        //    72: aload_2        
        //    73: aload_0        
        //    74: ldc_w           "spawn_err"
        //    77: ldc_w           "method__3$RUBY$spawn_err"
        //    80: ldc_w           "cmd;ret,1,0,-1"
        //    83: iconst_1       
        //    84: ldc             "./lib//lister/util.rb"
        //    86: ldc_w           11
        //    89: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    92: ldc             "qcmd"
        //    94: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    97: aload_1        
        //    98: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   101: goto            109
        //   104: aload_1        
        //   105: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   108: athrow         
        //   109: aload_1        
        //   110: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   113: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     101    104    109    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "spawn", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__2$RUBY$spawn(final FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392 file_808B05C6DDCD4D69E146BA21652BA59A209D0392, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        if (file_808B05C6DDCD4D69E146BA21652BA59A209D0392.getCallSite2().call(threadContext, rubyObject, rubyObject).isTrue()) {
            file_808B05C6DDCD4D69E146BA21652BA59A209D0392.getCallSite3().call(threadContext, rubyObject, file_808B05C6DDCD4D69E146BA21652BA59A209D0392.getCallSite4().call(threadContext, rubyObject, rubyObject), RubyString.newStringLight(threadContext.runtime, 20).append(file_808B05C6DDCD4D69E146BA21652BA59A209D0392.getString1(threadContext.runtime, 32)).append(rubyObject2.asString()).append(file_808B05C6DDCD4D69E146BA21652BA59A209D0392.getString2(threadContext.runtime, 32)));
        }
        return file_808B05C6DDCD4D69E146BA21652BA59A209D0392.getCallSite5().call(threadContext, rubyObject, rubyObject, RubyString.newStringLight(threadContext.runtime, 20).append(rubyObject2.asString()));
    }
    
    @JRubyMethod(name = "spawn_err", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$spawn_err(final FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    15: invokevirtual   ruby/jit/FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392.getCallSite6:()Lorg/jruby/runtime/CallSite;
        //    18: aload_1        
        //    19: aload_2        
        //    20: aload_2        
        //    21: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    24: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    29: ifeq            107
        //    32: aload_0        
        //    33: invokevirtual   ruby/jit/FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392.getCallSite7:()Lorg/jruby/runtime/CallSite;
        //    36: aload_1        
        //    37: aload_2        
        //    38: aload_0        
        //    39: invokevirtual   ruby/jit/FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392.getCallSite8:()Lorg/jruby/runtime/CallSite;
        //    42: aload_1        
        //    43: aload_2        
        //    44: aload_2        
        //    45: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    48: aload_1        
        //    49: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    52: ldc             20
        //    54: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    57: aload_0        
        //    58: aload_1        
        //    59: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    62: bipush          32
        //    64: invokevirtual   ruby/jit/FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392.getString1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    67: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    70: aload           locals
        //    72: aload_1        
        //    73: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //    84: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    87: aload_0        
        //    88: aload_1        
        //    89: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    92: bipush          32
        //    94: invokevirtual   ruby/jit/FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392.getString2:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    97: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   100: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   103: pop            
        //   104: goto            107
        //   107: aload           locals
        //   109: aload_0        
        //   110: aload_1        
        //   111: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   114: bipush          32
        //   116: invokevirtual   ruby/jit/FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392.getString3:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   119: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   122: pop            
        //   123: aload_0        
        //   124: invokevirtual   ruby/jit/FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392.getCallSite9:()Lorg/jruby/runtime/CallSite;
        //   127: aload_1        
        //   128: aload_2        
        //   129: aload_0        
        //   130: aload_1        
        //   131: ldc             "Open3"
        //   133: invokevirtual   ruby/jit/FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   136: aload           locals
        //   138: aload_1        
        //   139: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   142: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   145: aload_1        
        //   146: aload_2        
        //   147: aload_0        
        //   148: aload_1        
        //   149: ldc             "block_0$RUBY$spawn_err,3,i;o;e,true,1,./lib//lister/util.rb,14,true"
        //   151: invokevirtual   ruby/jit/FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   154: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   157: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   160: pop            
        //   161: aload           locals
        //   163: aload_1        
        //   164: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   167: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   170: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     157     5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_0$RUBY$spawn_err(final FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    10: astore          9
        //    12: aload_1        
        //    13: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    16: astore          10
        //    18: aload_1        
        //    19: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    22: astore          11
        //    24: aload_1        
        //    25: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    28: aload           4
        //    30: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: aload_3        
        //    34: aload_1        
        //    35: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    38: iconst_1       
        //    39: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.ensureMultipleAssignableRubyArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;Z)Lorg/jruby/RubyArray;
        //    42: astore          12
        //    44: aload           12
        //    46: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilZero:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: astore          9
        //    51: aload           12
        //    53: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilOne:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    56: astore          10
        //    58: aload           12
        //    60: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilTwo:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: astore          11
        //    65: aload           12
        //    67: pop            
        //    68: pop            
        //    69: aload           5
        //    71: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    74: aload_0        
        //    75: bipush          10
        //    77: invokevirtual   ruby/jit/FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    80: aload_1        
        //    81: aload_2        
        //    82: aload           e
        //    84: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    87: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    90: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  69     22      9     i     Lorg/jruby/runtime/builtin/IRubyObject;
        //  69     22      10    o     Lorg/jruby/runtime/builtin/IRubyObject;
        //  69     22      11    e     Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Util(final FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392 file_808B05C6DDCD4D69E146BA21652BA59A209D0392, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Util(file_808B05C6DDCD4D69E146BA21652BA59A209D0392, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392 file_808B05C6DDCD4D69E146BA21652BA59A209D0392, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_808B05C6DDCD4D69E146BA21652BA59A209D0392, threadContext, rubyObject, block);
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
        final FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392 file_808B05C6DDCD4D69E146BA21652BA59A209D0392 = new FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392();
        final String string = FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392.class.getClassLoader().getResource("ruby/jit/FILE_808B05C6DDCD4D69E146BA21652BA59A209D0392.class").toString();
        file_808B05C6DDCD4D69E146BA21652BA59A209D0392.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_808B05C6DDCD4D69E146BA21652BA59A209D0392.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
