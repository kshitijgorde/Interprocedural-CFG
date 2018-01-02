// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.runtime.DynamicScope;
import org.jruby.RubyRange;
import org.jruby.RubyFixnum;
import org.jruby.anno.JRubyMethod;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_C8511E9F2D80426C130189A76C6533C21207D7AA extends AbstractScript
{
    public FILE_C8511E9F2D80426C130189A76C6533C21207D7AA() {
        this.filename = "./lib//lister/utils/uid.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrespond_to?\uffffN\uffffpack\uffffN\uffffchomp\uffffN\ufffftr\uffffN\uffffstrict_encode64\uffffF\uffffattr_reader\uffffF\uffffpack\uffffN\uffffmap\uffffN\ufffflen\uffffV\uffffrand\uffffF\uffffurlsafe_encode64\uffffN\uffffgenerate!\uffffN\uffffnew\uffffN\uffff\u0003\u0002\u0002\u0000\u0005\u0000\u0000\u0000\u0001\u0001\u0000\u0000\u0005\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(0, "base64", this.getEncoding0());
        this.setByteList(4, "C*", this.getEncoding0());
        this.setByteList(3, "-_", this.getEncoding0());
        this.setByteList(2, "+/", this.getEncoding0());
        this.setByteList(1, "m0", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_C8511E9F2D80426C130189A76C6533C21207D7AA file_C8511E9F2D80426C130189A76C6533C21207D7AA, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_C8511E9F2D80426C130189A76C6533C21207D7AA.getCallSite0().call(context, rubyObject, rubyObject, file_C8511E9F2D80426C130189A76C6533C21207D7AA.getString0(context.runtime, 32));
        if (!file_C8511E9F2D80426C130189A76C6533C21207D7AA.getCallSite1().call(context, rubyObject, file_C8511E9F2D80426C130189A76C6533C21207D7AA.getConstant0(context, "Base64"), file_C8511E9F2D80426C130189A76C6533C21207D7AA.getSymbol0(context.runtime, "urlsafe_encode64")).isTrue()) {
            RuntimeHelpers.defs(context, rubyObject, file_C8511E9F2D80426C130189A76C6533C21207D7AA.getConstant1(context, "Base64"), file_C8511E9F2D80426C130189A76C6533C21207D7AA, "strict_encode64", "method__0$RUBY$strict_encode64", "bin,1,0,-1", 1, "./lib//lister/utils/uid.rb", 3, CallConfiguration.FrameNoneScopeNone, "qbin");
            RuntimeHelpers.defs(context, rubyObject, file_C8511E9F2D80426C130189A76C6533C21207D7AA.getConstant2(context, "Base64"), file_C8511E9F2D80426C130189A76C6533C21207D7AA, "urlsafe_encode64", "method__1$RUBY$urlsafe_encode64", "bin,1,0,-1", 1, "./lib//lister/utils/uid.rb", 6, CallConfiguration.FrameNoneScopeNone, "qbin");
        }
        return module__2$RUBY$Lister(file_C8511E9F2D80426C130189A76C6533C21207D7AA, context, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    @JRubyMethod(name = "strict_encode64", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__0$RUBY$strict_encode64(final FILE_C8511E9F2D80426C130189A76C6533C21207D7AA file_C8511E9F2D80426C130189A76C6533C21207D7AA, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject one, final Block block) {
        return file_C8511E9F2D80426C130189A76C6533C21207D7AA.getCallSite2().call(threadContext, rubyObject, RuntimeHelpers.constructRubyArray(threadContext.runtime, one), file_C8511E9F2D80426C130189A76C6533C21207D7AA.getString1(threadContext.runtime, 32));
    }
    
    @JRubyMethod(name = "urlsafe_encode64", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__1$RUBY$urlsafe_encode64(final FILE_C8511E9F2D80426C130189A76C6533C21207D7AA file_C8511E9F2D80426C130189A76C6533C21207D7AA, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_C8511E9F2D80426C130189A76C6533C21207D7AA.getCallSite3().call(threadContext, rubyObject, file_C8511E9F2D80426C130189A76C6533C21207D7AA.getCallSite4().call(threadContext, rubyObject, file_C8511E9F2D80426C130189A76C6533C21207D7AA.getCallSite5().call(threadContext, rubyObject, rubyObject, rubyObject2), file_C8511E9F2D80426C130189A76C6533C21207D7AA.getString2(threadContext.runtime, 32), file_C8511E9F2D80426C130189A76C6533C21207D7AA.getString3(threadContext.runtime, 32)));
    }
    
    public static IRubyObject module__2$RUBY$Lister(final FILE_C8511E9F2D80426C130189A76C6533C21207D7AA p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_C8511E9F2D80426C130189A76C6533C21207D7AA.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    33: invokestatic    ruby/jit/FILE_C8511E9F2D80426C130189A76C6533C21207D7AA.class_3$RUBY$UID:(Lruby/jit/FILE_C8511E9F2D80426C130189A76C6533C21207D7AA;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    36: aload_1        
        //    37: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    40: goto            48
        //    43: aload_1        
        //    44: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    47: athrow         
        //    48: aload_1        
        //    49: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    52: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     40     43     48     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_3$RUBY$UID(final FILE_C8511E9F2D80426C130189A76C6533C21207D7AA p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: aload_1        
        //     2: aload_1        
        //     3: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     6: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     9: swap           
        //    10: ldc             "UID"
        //    12: swap           
        //    13: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    16: dup            
        //    17: astore_2       
        //    18: aload_1        
        //    19: swap           
        //    20: aload_0        
        //    21: aload_1        
        //    22: ldc             ",0,0,-1"
        //    24: invokevirtual   ruby/jit/FILE_C8511E9F2D80426C130189A76C6533C21207D7AA.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    27: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    30: aload_0        
        //    31: aload_1        
        //    32: aload_2        
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_C8511E9F2D80426C130189A76C6533C21207D7AA.class_4$RUBY$Generator:(Lruby/jit/FILE_C8511E9F2D80426C130189A76C6533C21207D7AA;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: pop            
        //    40: aload_1        
        //    41: aload_2        
        //    42: aload_2        
        //    43: aload_0        
        //    44: ldc_w           "generate"
        //    47: ldc_w           "method__7$RUBY$generate"
        //    50: ldc             "len,0,1,-1"
        //    52: iconst_m1      
        //    53: ldc             "./lib//lister/utils/uid.rb"
        //    55: ldc_w           26
        //    58: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    61: ldc             "olen"
        //    63: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: aload_1        
        //    67: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    70: goto            78
        //    73: aload_1        
        //    74: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    77: athrow         
        //    78: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  30     66     73     78     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_4$RUBY$Generator(final FILE_C8511E9F2D80426C130189A76C6533C21207D7AA p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: aload_1        
        //     2: aload_1        
        //     3: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     6: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     9: swap           
        //    10: ldc             "Generator"
        //    12: swap           
        //    13: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    16: dup            
        //    17: astore_2       
        //    18: aload_1        
        //    19: swap           
        //    20: aload_0        
        //    21: aload_1        
        //    22: ldc             ",0,0,-1"
        //    24: invokevirtual   ruby/jit/FILE_C8511E9F2D80426C130189A76C6533C21207D7AA.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    27: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    30: aload_0        
        //    31: invokevirtual   ruby/jit/FILE_C8511E9F2D80426C130189A76C6533C21207D7AA.getCallSite6:()Lorg/jruby/runtime/CallSite;
        //    34: aload_1        
        //    35: aload_2        
        //    36: aload_2        
        //    37: aload_0        
        //    38: aload_1        
        //    39: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    42: ldc             "len"
        //    44: invokevirtual   ruby/jit/FILE_C8511E9F2D80426C130189A76C6533C21207D7AA.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    47: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: pop            
        //    51: aload_1        
        //    52: aload_2        
        //    53: aload_0        
        //    54: ldc             "initialize"
        //    56: ldc             "method__5$RUBY$initialize"
        //    58: ldc             "len,0,1,-1"
        //    60: iconst_m1      
        //    61: ldc             "./lib//lister/utils/uid.rb"
        //    63: ldc             16
        //    65: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    68: ldc             "olen"
        //    70: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: pop            
        //    74: aload_1        
        //    75: aload_2        
        //    76: aload_0        
        //    77: ldc_w           "generate!"
        //    80: ldc_w           "method__6$RUBY$generate_b_"
        //    83: ldc_w           "str,0,0,-1"
        //    86: iconst_0       
        //    87: ldc             "./lib//lister/utils/uid.rb"
        //    89: ldc_w           20
        //    92: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    95: ldc_w           "NONE"
        //    98: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   101: aload_1        
        //   102: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   105: goto            113
        //   108: aload_1        
        //   109: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   112: athrow         
        //   113: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  30     101    108    113    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "initialize", frame = true, required = 0, optional = 1, rest = -1)
    public static IRubyObject method__5$RUBY$initialize(final FILE_C8511E9F2D80426C130189A76C6533C21207D7AA p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     4: astore          9
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    10: aload_3        
        //    11: iconst_0       
        //    12: iconst_1       
        //    13: invokestatic    org/jruby/runtime/Arity.raiseArgumentError:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;II)V
        //    16: aload_3        
        //    17: iconst_0       
        //    18: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.elementOrNull:([Lorg/jruby/runtime/builtin/IRubyObject;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    21: dup            
        //    22: ifnull          30
        //    25: astore          9
        //    27: goto            43
        //    30: aload_0        
        //    31: aload_1        
        //    32: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    35: bipush          33
        //    37: invokevirtual   ruby/jit/FILE_C8511E9F2D80426C130189A76C6533C21207D7AA.getFixnum0:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //    40: astore          9
        //    42: pop            
        //    43: aload_0        
        //    44: aload_1        
        //    45: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    48: ldc             "@len"
        //    50: aload_2        
        //    51: aload           len
        //    53: invokevirtual   ruby/jit/FILE_C8511E9F2D80426C130189A76C6533C21207D7AA.setVariable0:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    56: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  43     14      9     len   Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
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
    
    @JRubyMethod(name = "generate!", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$generate_b_(final FILE_C8511E9F2D80426C130189A76C6533C21207D7AA file_C8511E9F2D80426C130189A76C6533C21207D7AA, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(file_C8511E9F2D80426C130189A76C6533C21207D7AA.getCallSite7().call(threadContext, self, file_C8511E9F2D80426C130189A76C6533C21207D7AA.getCallSite8().callIter(threadContext, self, RubyRange.newInclusiveRange(threadContext.runtime, threadContext, RubyFixnum.one(threadContext.runtime), file_C8511E9F2D80426C130189A76C6533C21207D7AA.getCallSite9().call(threadContext, self, self)), RuntimeHelpers.createBlock(threadContext, self, file_C8511E9F2D80426C130189A76C6533C21207D7AA.getBlockBody0(threadContext, "block_0$RUBY$generate!,-1,,false,0,./lib//lister/utils/uid.rb,21,true"))), file_C8511E9F2D80426C130189A76C6533C21207D7AA.getString4(threadContext.runtime, 32)));
        return file_C8511E9F2D80426C130189A76C6533C21207D7AA.getCallSite(11).call(threadContext, self, file_C8511E9F2D80426C130189A76C6533C21207D7AA.getConstant3(threadContext, "Base64"), locals.getValueZeroDepthZeroOrNil(threadContext.nil));
    }
    
    public static IRubyObject block_0$RUBY$generate!(final FILE_C8511E9F2D80426C130189A76C6533C21207D7AA file_C8511E9F2D80426C130189A76C6533C21207D7AA, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        threadContext.getCurrentScope();
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        return file_C8511E9F2D80426C130189A76C6533C21207D7AA.getCallSite(10).call(threadContext, rubyObject, rubyObject, file_C8511E9F2D80426C130189A76C6533C21207D7AA.getFixnum1(threadContext.runtime, 256));
    }
    
    public static IRubyObject class_4$RUBY$Generator(final FILE_C8511E9F2D80426C130189A76C6533C21207D7AA file_C8511E9F2D80426C130189A76C6533C21207D7AA, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_4$RUBY$Generator(file_C8511E9F2D80426C130189A76C6533C21207D7AA, threadContext, rubyObject, block);
    }
    
    @JRubyMethod(name = "generate", frame = true, required = 0, optional = 1, rest = -1)
    public static IRubyObject method__7$RUBY$generate(final FILE_C8511E9F2D80426C130189A76C6533C21207D7AA p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     4: astore          9
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    10: aload_3        
        //    11: iconst_0       
        //    12: iconst_1       
        //    13: invokestatic    org/jruby/runtime/Arity.raiseArgumentError:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;II)V
        //    16: aload_3        
        //    17: iconst_0       
        //    18: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.elementOrNull:([Lorg/jruby/runtime/builtin/IRubyObject;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    21: dup            
        //    22: ifnull          30
        //    25: astore          9
        //    27: goto            43
        //    30: aload_0        
        //    31: aload_1        
        //    32: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    35: bipush          33
        //    37: invokevirtual   ruby/jit/FILE_C8511E9F2D80426C130189A76C6533C21207D7AA.getFixnum0:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //    40: astore          9
        //    42: pop            
        //    43: aload_0        
        //    44: bipush          12
        //    46: invokevirtual   ruby/jit/FILE_C8511E9F2D80426C130189A76C6533C21207D7AA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload_0        
        //    52: bipush          13
        //    54: invokevirtual   ruby/jit/FILE_C8511E9F2D80426C130189A76C6533C21207D7AA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    57: aload_1        
        //    58: aload_2        
        //    59: aload_0        
        //    60: aload_1        
        //    61: ldc             "Generator"
        //    63: invokevirtual   ruby/jit/FILE_C8511E9F2D80426C130189A76C6533C21207D7AA.getConstant4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: aload           len
        //    68: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    71: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  43     32      9     len   Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
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
    
    public static IRubyObject class_3$RUBY$UID(final FILE_C8511E9F2D80426C130189A76C6533C21207D7AA file_C8511E9F2D80426C130189A76C6533C21207D7AA, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_3$RUBY$UID(file_C8511E9F2D80426C130189A76C6533C21207D7AA, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__2$RUBY$Lister(final FILE_C8511E9F2D80426C130189A76C6533C21207D7AA file_C8511E9F2D80426C130189A76C6533C21207D7AA, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__2$RUBY$Lister(file_C8511E9F2D80426C130189A76C6533C21207D7AA, threadContext, rubyObject, block);
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
        final FILE_C8511E9F2D80426C130189A76C6533C21207D7AA file_C8511E9F2D80426C130189A76C6533C21207D7AA = new FILE_C8511E9F2D80426C130189A76C6533C21207D7AA();
        final String string = FILE_C8511E9F2D80426C130189A76C6533C21207D7AA.class.getClassLoader().getResource("ruby/jit/FILE_C8511E9F2D80426C130189A76C6533C21207D7AA.class").toString();
        file_C8511E9F2D80426C130189A76C6533C21207D7AA.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_C8511E9F2D80426C130189A76C6533C21207D7AA.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
