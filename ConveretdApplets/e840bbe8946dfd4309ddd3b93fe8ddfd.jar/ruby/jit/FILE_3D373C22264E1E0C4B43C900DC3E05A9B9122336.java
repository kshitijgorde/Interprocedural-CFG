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

public class FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336 extends AbstractScript
{
    public FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336() {
        this.filename = "./proprietary//undersimple/core/describable.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("attr_accessor\uffffF\uffffdefault_description_tag\uffffV\uffffdescriptions\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffdefault_description_tag\uffffV\uffff[]\uffffN\uffffdescriptions\uffffV\uffff[]\uffffN\uffffdescriptions\uffffV\uffffdefault_description_tag\uffffV\uffffnew\uffffN\uffffnew\uffffN\uffff\u0002\u0002\u0000\u0000\u0002\u0000\u0000\u0001\u0002\u0000\u0000\u0000\u0001\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(0, "instance-variable", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336 file_3D373C22264E1E0C4B43C900DC3E05A9B9122336, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return module__0$RUBY$UnderSimple(file_3D373C22264E1E0C4B43C900DC3E05A9B9122336, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$UnderSimple(final FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336.module__1$RUBY$Describable:(Lruby/jit/FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Describable(final FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Describable"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: invokevirtual   ruby/jit/FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336.getCallSite0:()Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_2        
        //    34: aload_0        
        //    35: aload_1        
        //    36: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    39: ldc             "description"
        //    41: invokevirtual   ruby/jit/FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    44: aload_0        
        //    45: aload_1        
        //    46: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    49: ldc             "default_description_tag"
        //    51: invokevirtual   ruby/jit/FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    54: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: pop            
        //    58: aload_1        
        //    59: aload_2        
        //    60: aload_0        
        //    61: ldc             "describe"
        //    63: ldc             "method__2$RUBY$describe"
        //    65: ldc             "str;tag,1,1,-1"
        //    67: bipush          -2
        //    69: ldc             "./proprietary//undersimple/core/describable.rb"
        //    71: ldc             5
        //    73: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    76: ldc             "qstr;otag"
        //    78: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    81: pop            
        //    82: aload_1        
        //    83: aload_2        
        //    84: aload_0        
        //    85: ldc             "description"
        //    87: ldc             "method__3$RUBY$description"
        //    89: ldc             "tag,0,1,-1"
        //    91: iconst_m1      
        //    92: ldc             "./proprietary//undersimple/core/describable.rb"
        //    94: ldc             9
        //    96: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    99: ldc             "otag"
        //   101: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   104: pop            
        //   105: aload_1        
        //   106: aload_2        
        //   107: aload_0        
        //   108: ldc_w           "descriptions"
        //   111: ldc_w           "method__4$RUBY$descriptions"
        //   114: ldc             ",0,0,-1"
        //   116: iconst_0       
        //   117: ldc             "./proprietary//undersimple/core/describable.rb"
        //   119: ldc_w           13
        //   122: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   125: ldc_w           "NONE"
        //   128: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   131: aload_1        
        //   132: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   135: goto            143
        //   138: aload_1        
        //   139: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   142: athrow         
        //   143: aload_1        
        //   144: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   147: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     135    138    143    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "describe", frame = true, required = 1, optional = 1, rest = -1)
    public static IRubyObject method__2$RUBY$describe(final FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     4: astore          10
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    10: aload_3        
        //    11: iconst_1       
        //    12: iconst_2       
        //    13: invokestatic    org/jruby/runtime/Arity.raiseArgumentError:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;II)V
        //    16: aload_3        
        //    17: iconst_0       
        //    18: aaload         
        //    19: astore          9
        //    21: aload_3        
        //    22: iconst_1       
        //    23: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.elementOrNull:([Lorg/jruby/runtime/builtin/IRubyObject;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    26: dup            
        //    27: ifnull          35
        //    30: astore          10
        //    32: goto            48
        //    35: aload_0        
        //    36: invokevirtual   ruby/jit/FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336.getCallSite1:()Lorg/jruby/runtime/CallSite;
        //    39: aload_1        
        //    40: aload_2        
        //    41: aload_2        
        //    42: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: astore          10
        //    47: pop            
        //    48: aload_0        
        //    49: invokevirtual   ruby/jit/FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336.getCallSite2:()Lorg/jruby/runtime/CallSite;
        //    52: aload_1        
        //    53: aload_2        
        //    54: aload_2        
        //    55: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: dup            
        //    59: aload_2        
        //    60: aload_0        
        //    61: invokevirtual   ruby/jit/FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336.getCallSite3:()Lorg/jruby/runtime/CallSite;
        //    64: aload_0        
        //    65: invokevirtual   ruby/jit/FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336.getCallSite4:()Lorg/jruby/runtime/CallSite;
        //    68: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //    71: aload           tag
        //    73: aload           str
        //    75: aload_1        
        //    76: aload_2        
        //    77: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  48     33      9     str   Lorg/jruby/runtime/builtin/IRubyObject;
        //  48     33      10    tag   Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "description", frame = true, required = 0, optional = 1, rest = -1)
    public static IRubyObject method__3$RUBY$description(final FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
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
        //    22: ifnull          35
        //    25: aload           5
        //    27: swap           
        //    28: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    31: pop            
        //    32: goto            52
        //    35: aload           5
        //    37: aload_0        
        //    38: invokevirtual   ruby/jit/FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336.getCallSite5:()Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_2        
        //    44: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: pop            
        //    51: pop            
        //    52: aload_0        
        //    53: invokevirtual   ruby/jit/FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336.getCallSite6:()Lorg/jruby/runtime/CallSite;
        //    56: aload_1        
        //    57: aload_2        
        //    58: aload_0        
        //    59: invokevirtual   ruby/jit/FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336.getCallSite7:()Lorg/jruby/runtime/CallSite;
        //    62: aload_1        
        //    63: aload_2        
        //    64: aload_2        
        //    65: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    68: aload           locals
        //    70: aload_1        
        //    71: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: dup            
        //    81: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    86: ifne            121
        //    89: pop            
        //    90: aload_0        
        //    91: invokevirtual   ruby/jit/FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336.getCallSite8:()Lorg/jruby/runtime/CallSite;
        //    94: aload_1        
        //    95: aload_2        
        //    96: aload_0        
        //    97: invokevirtual   ruby/jit/FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336.getCallSite9:()Lorg/jruby/runtime/CallSite;
        //   100: aload_1        
        //   101: aload_2        
        //   102: aload_2        
        //   103: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   106: aload_0        
        //   107: bipush          10
        //   109: invokevirtual   ruby/jit/FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   112: aload_1        
        //   113: aload_2        
        //   114: aload_2        
        //   115: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   118: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   121: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  52     70      5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "descriptions", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$descriptions(final FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336 file_3D373C22264E1E0C4B43C900DC3E05A9B9122336, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@descriptions") ? file_3D373C22264E1E0C4B43C900DC3E05A9B9122336.getByteList0() : null) == null) {
            rubyObject = file_3D373C22264E1E0C4B43C900DC3E05A9B9122336.setVariable0(threadContext.runtime, "@descriptions", object, file_3D373C22264E1E0C4B43C900DC3E05A9B9122336.getCallSite(11).call(threadContext, object, file_3D373C22264E1E0C4B43C900DC3E05A9B9122336.getConstant0(threadContext, "Hash")));
        }
        else if (!(rubyObject = file_3D373C22264E1E0C4B43C900DC3E05A9B9122336.getVariable0(threadContext.runtime, "@descriptions", object)).isTrue()) {
            rubyObject = file_3D373C22264E1E0C4B43C900DC3E05A9B9122336.setVariable1(threadContext.runtime, "@descriptions", object, file_3D373C22264E1E0C4B43C900DC3E05A9B9122336.getCallSite(12).call(threadContext, object, file_3D373C22264E1E0C4B43C900DC3E05A9B9122336.getConstant1(threadContext, "Hash")));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    public static IRubyObject module__1$RUBY$Describable(final FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336 file_3D373C22264E1E0C4B43C900DC3E05A9B9122336, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Describable(file_3D373C22264E1E0C4B43C900DC3E05A9B9122336, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$UnderSimple(final FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336 file_3D373C22264E1E0C4B43C900DC3E05A9B9122336, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$UnderSimple(file_3D373C22264E1E0C4B43C900DC3E05A9B9122336, threadContext, rubyObject, block);
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
        final FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336 file_3D373C22264E1E0C4B43C900DC3E05A9B9122336 = new FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336();
        final String string = FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336.class.getClassLoader().getResource("ruby/jit/FILE_3D373C22264E1E0C4B43C900DC3E05A9B9122336.class").toString();
        file_3D373C22264E1E0C4B43C900DC3E05A9B9122336.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_3D373C22264E1E0C4B43C900DC3E05A9B9122336.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
