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

public class FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F extends AbstractScript
{
    public FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F() {
        this.filename = "./lib//lister/runner/questionnaire/html.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("include?\uffffN\uffffjoin\uffffN\uffffmap\uffffN\uffffto_a\uffffN\ufffflines\uffffN\ufffftr\uffffN\uffff\u0003\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0000\u0000\u0004\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(2, "", this.getEncoding0());
        this.setByteList(0, "<HTML>", this.getEncoding0());
        this.setByteList(1, "\n", this.getEncoding0());
        this.setByteList(3, "<br>", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F file_D7F214E717F9AE0FF49091F2564BAF2890EC009F, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return module__0$RUBY$Lister(file_D7F214E717F9AE0FF49091F2564BAF2890EC009F, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F.module__1$RUBY$Questionnaire:(Lruby/jit/FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Questionnaire(final FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Questionnaire"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F.module__2$RUBY$HasHtml:(Lruby/jit/FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__2$RUBY$HasHtml(final FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "HasHtml"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_1        
        //    28: aload_2        
        //    29: aload_0        
        //    30: ldc             "clean_html_message"
        //    32: ldc             "method__3$RUBY$clean_html_message"
        //    34: ldc             "message,1,0,-1"
        //    36: iconst_1       
        //    37: ldc             "./lib//lister/runner/questionnaire/html.rb"
        //    39: ldc             4
        //    41: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    44: ldc             "qmessage"
        //    46: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: aload_1        
        //    50: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    53: goto            61
        //    56: aload_1        
        //    57: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    60: athrow         
        //    61: aload_1        
        //    62: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    65: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     53     56     61     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "clean_html_message", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$clean_html_message(final FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    15: invokevirtual   ruby/jit/FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F.getCallSite0:()Lorg/jruby/runtime/CallSite;
        //    18: aload_1        
        //    19: aload_2        
        //    20: aload           locals
        //    22: aload_1        
        //    23: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    26: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    29: aload_0        
        //    30: aload_1        
        //    31: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    34: bipush          32
        //    36: invokevirtual   ruby/jit/FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F.getString0:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    39: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    42: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    47: ifeq            126
        //    50: aload           locals
        //    52: aload_0        
        //    53: invokevirtual   ruby/jit/FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F.getCallSite1:()Lorg/jruby/runtime/CallSite;
        //    56: aload_1        
        //    57: aload_2        
        //    58: aload_0        
        //    59: invokevirtual   ruby/jit/FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F.getCallSite2:()Lorg/jruby/runtime/CallSite;
        //    62: aload_1        
        //    63: aload_2        
        //    64: aload_0        
        //    65: invokevirtual   ruby/jit/FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F.getCallSite3:()Lorg/jruby/runtime/CallSite;
        //    68: aload_1        
        //    69: aload_2        
        //    70: aload_0        
        //    71: invokevirtual   ruby/jit/FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F.getCallSite4:()Lorg/jruby/runtime/CallSite;
        //    74: aload_1        
        //    75: aload_2        
        //    76: aload           locals
        //    78: aload_1        
        //    79: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    85: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    91: aload_1        
        //    92: aload_2        
        //    93: aload_0        
        //    94: aload_1        
        //    95: ldc             "block_0$RUBY$clean_html_message,1,l,false,2,./lib//lister/runner/questionnaire/html.rb,6,true"
        //    97: invokevirtual   ruby/jit/FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   100: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   103: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   106: aload_0        
        //   107: aload_1        
        //   108: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   111: bipush          32
        //   113: invokevirtual   ruby/jit/FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F.getString3:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   116: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   119: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   122: pop            
        //   123: goto            126
        //   126: aload           locals
        //   128: aload_1        
        //   129: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   132: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   135: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     122     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_0$RUBY$clean_html_message(final FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    13: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    16: aload           4
        //    18: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    21: aload_3        
        //    22: astore          9
        //    24: pop            
        //    25: aload_0        
        //    26: invokevirtual   ruby/jit/FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F.getCallSite5:()Lorg/jruby/runtime/CallSite;
        //    29: aload_1        
        //    30: aload_2        
        //    31: aload           l
        //    33: aload_0        
        //    34: aload_1        
        //    35: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    38: bipush          32
        //    40: invokevirtual   ruby/jit/FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F.getString1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    43: aload_0        
        //    44: aload_1        
        //    45: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    48: bipush          32
        //    50: invokevirtual   ruby/jit/FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F.getString2:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    53: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    56: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     32      9     l     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject module__2$RUBY$HasHtml(final FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F file_D7F214E717F9AE0FF49091F2564BAF2890EC009F, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__2$RUBY$HasHtml(file_D7F214E717F9AE0FF49091F2564BAF2890EC009F, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Questionnaire(final FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F file_D7F214E717F9AE0FF49091F2564BAF2890EC009F, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Questionnaire(file_D7F214E717F9AE0FF49091F2564BAF2890EC009F, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F file_D7F214E717F9AE0FF49091F2564BAF2890EC009F, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_D7F214E717F9AE0FF49091F2564BAF2890EC009F, threadContext, rubyObject, block);
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
        final FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F file_D7F214E717F9AE0FF49091F2564BAF2890EC009F = new FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F();
        final String string = FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F.class.getClassLoader().getResource("ruby/jit/FILE_D7F214E717F9AE0FF49091F2564BAF2890EC009F.class").toString();
        file_D7F214E717F9AE0FF49091F2564BAF2890EC009F.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_D7F214E717F9AE0FF49091F2564BAF2890EC009F.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
