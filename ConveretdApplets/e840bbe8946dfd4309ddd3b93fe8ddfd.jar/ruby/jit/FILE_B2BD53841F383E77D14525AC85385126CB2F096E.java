// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.RubyHash;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_B2BD53841F383E77D14525AC85385126CB2F096E extends AbstractScript
{
    public FILE_B2BD53841F383E77D14525AC85385126CB2F096E() {
        this.filename = "./proprietary//undersimple/core/verifiable.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("verifications\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\ufffffind\uffffN\uffffverifications\uffffV\uffffcall\uffffN\uffff\u0002\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0001\u0000\u0000\u0001\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(0, "instance-variable", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_B2BD53841F383E77D14525AC85385126CB2F096E file_B2BD53841F383E77D14525AC85385126CB2F096E, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return module__0$RUBY$UnderSimple(file_B2BD53841F383E77D14525AC85385126CB2F096E, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$UnderSimple(final FILE_B2BD53841F383E77D14525AC85385126CB2F096E p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "UnderSimple"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_B2BD53841F383E77D14525AC85385126CB2F096E.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_B2BD53841F383E77D14525AC85385126CB2F096E.module__1$RUBY$Verifiable:(Lruby/jit/FILE_B2BD53841F383E77D14525AC85385126CB2F096E;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Verifiable(final FILE_B2BD53841F383E77D14525AC85385126CB2F096E p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Verifiable"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_B2BD53841F383E77D14525AC85385126CB2F096E.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_1        
        //    28: aload_2        
        //    29: aload_0        
        //    30: ldc             "verifications"
        //    32: ldc             "method__2$RUBY$verifications"
        //    34: ldc             ",0,0,-1"
        //    36: iconst_0       
        //    37: ldc             "./proprietary//undersimple/core/verifiable.rb"
        //    39: ldc             3
        //    41: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    44: ldc             "NONE"
        //    46: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: pop            
        //    50: aload_1        
        //    51: aload_2        
        //    52: aload_0        
        //    53: ldc             "verify"
        //    55: ldc             "method__3$RUBY$verify"
        //    57: ldc             "name;blk,1,0,-1"
        //    59: iconst_1       
        //    60: ldc             "./proprietary//undersimple/core/verifiable.rb"
        //    62: ldc             7
        //    64: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    67: ldc             "qname;bblk"
        //    69: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    72: pop            
        //    73: aload_1        
        //    74: aload_2        
        //    75: aload_0        
        //    76: ldc_w           "valid_value?"
        //    79: ldc_w           "method__4$RUBY$valid_value_p_"
        //    82: ldc_w           "value;obj,1,0,-1"
        //    85: iconst_1       
        //    86: ldc             "./proprietary//undersimple/core/verifiable.rb"
        //    88: ldc_w           11
        //    91: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    94: ldc_w           "qvalue"
        //    97: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   100: aload_1        
        //   101: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   104: goto            112
        //   107: aload_1        
        //   108: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   111: athrow         
        //   112: aload_1        
        //   113: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   116: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     104    107    112    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "verifications", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__2$RUBY$verifications(final FILE_B2BD53841F383E77D14525AC85385126CB2F096E file_B2BD53841F383E77D14525AC85385126CB2F096E, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@verifications") ? file_B2BD53841F383E77D14525AC85385126CB2F096E.getByteList0() : null) == null) {
            rubyObject = file_B2BD53841F383E77D14525AC85385126CB2F096E.setVariable0(threadContext.runtime, "@verifications", object, RubyHash.newHash(threadContext.runtime));
        }
        else if (!(rubyObject = file_B2BD53841F383E77D14525AC85385126CB2F096E.getVariable0(threadContext.runtime, "@verifications", object)).isTrue()) {
            rubyObject = file_B2BD53841F383E77D14525AC85385126CB2F096E.setVariable1(threadContext.runtime, "@verifications", object, RubyHash.newHash(threadContext.runtime));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "verify", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$verify(final FILE_B2BD53841F383E77D14525AC85385126CB2F096E p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     4: astore          10
        //     6: aload_3        
        //     7: astore          9
        //     9: aload_1        
        //    10: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    13: aload           4
        //    15: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    18: astore          blk
        //    20: aload_0        
        //    21: invokevirtual   ruby/jit/FILE_B2BD53841F383E77D14525AC85385126CB2F096E.getCallSite0:()Lorg/jruby/runtime/CallSite;
        //    24: aload_1        
        //    25: aload_2        
        //    26: aload_2        
        //    27: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    30: dup            
        //    31: aload_2        
        //    32: aload_0        
        //    33: invokevirtual   ruby/jit/FILE_B2BD53841F383E77D14525AC85385126CB2F096E.getCallSite1:()Lorg/jruby/runtime/CallSite;
        //    36: aload_0        
        //    37: invokevirtual   ruby/jit/FILE_B2BD53841F383E77D14525AC85385126CB2F096E.getCallSite2:()Lorg/jruby/runtime/CallSite;
        //    40: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //    43: aload           name
        //    45: aload           blk
        //    47: aload_1        
        //    48: aload_2        
        //    49: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  20     33      9     name  Lorg/jruby/runtime/builtin/IRubyObject;
        //  20     33      10    blk   Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "valid_value?", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$valid_value_p_(final FILE_B2BD53841F383E77D14525AC85385126CB2F096E p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    14: aload           locals
        //    16: aload_0        
        //    17: invokevirtual   ruby/jit/FILE_B2BD53841F383E77D14525AC85385126CB2F096E.getCallSite3:()Lorg/jruby/runtime/CallSite;
        //    20: aload_1        
        //    21: aload_2        
        //    22: aload_0        
        //    23: invokevirtual   ruby/jit/FILE_B2BD53841F383E77D14525AC85385126CB2F096E.getCallSite4:()Lorg/jruby/runtime/CallSite;
        //    26: aload_1        
        //    27: aload_2        
        //    28: aload_2        
        //    29: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    32: aload_1        
        //    33: aload_2        
        //    34: aload_0        
        //    35: aload_1        
        //    36: ldc             "block_0$RUBY$valid_value?,2,n;vrfy,true,1,./proprietary//undersimple/core/verifiable.rb,12,true"
        //    38: invokevirtual   ruby/jit/FILE_B2BD53841F383E77D14525AC85385126CB2F096E.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    41: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    44: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: pop            
        //    51: aload           locals
        //    53: aload_1        
        //    54: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: aload_1        
        //    61: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    64: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.negate:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     54      5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_0$RUBY$valid_value?(final FILE_B2BD53841F383E77D14525AC85385126CB2F096E p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    19: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    22: aload           4
        //    24: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    27: aload_3        
        //    28: aload_1        
        //    29: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    32: iconst_1       
        //    33: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.ensureMultipleAssignableRubyArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;Z)Lorg/jruby/RubyArray;
        //    36: astore          11
        //    38: aload           11
        //    40: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilZero:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: astore          9
        //    45: aload           11
        //    47: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilOne:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: astore          10
        //    52: aload           11
        //    54: pop            
        //    55: pop            
        //    56: aload_0        
        //    57: invokevirtual   ruby/jit/FILE_B2BD53841F383E77D14525AC85385126CB2F096E.getCallSite5:()Lorg/jruby/runtime/CallSite;
        //    60: aload_1        
        //    61: aload_2        
        //    62: aload           vrfy
        //    64: aload           5
        //    66: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    69: aload_1        
        //    70: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: aload_1        
        //    80: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    83: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.negate:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    86: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  56     31      9     n     Lorg/jruby/runtime/builtin/IRubyObject;
        //  56     31      10    vrfy  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject module__1$RUBY$Verifiable(final FILE_B2BD53841F383E77D14525AC85385126CB2F096E file_B2BD53841F383E77D14525AC85385126CB2F096E, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Verifiable(file_B2BD53841F383E77D14525AC85385126CB2F096E, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$UnderSimple(final FILE_B2BD53841F383E77D14525AC85385126CB2F096E file_B2BD53841F383E77D14525AC85385126CB2F096E, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$UnderSimple(file_B2BD53841F383E77D14525AC85385126CB2F096E, threadContext, rubyObject, block);
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
        final FILE_B2BD53841F383E77D14525AC85385126CB2F096E file_B2BD53841F383E77D14525AC85385126CB2F096E = new FILE_B2BD53841F383E77D14525AC85385126CB2F096E();
        final String string = FILE_B2BD53841F383E77D14525AC85385126CB2F096E.class.getClassLoader().getResource("ruby/jit/FILE_B2BD53841F383E77D14525AC85385126CB2F096E.class").toString();
        file_B2BD53841F383E77D14525AC85385126CB2F096E.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_B2BD53841F383E77D14525AC85385126CB2F096E.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
