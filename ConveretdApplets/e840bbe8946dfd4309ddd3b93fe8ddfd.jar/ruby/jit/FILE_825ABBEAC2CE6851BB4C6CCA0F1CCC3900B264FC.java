// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.RubyHash;
import org.jruby.runtime.Arity;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC extends AbstractScript
{
    public FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC() {
        this.filename = "./proprietary//undersimple/core/morphable.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("[]\uffffN\uffffmorphers\uffffV\uffff[]\uffffN\uffffmorphers\uffffV\uffff[]\uffffN\uffffmorphers\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffblock_given?\uffffF\uffffraise\uffffF\uffffset_morpher\uffffF\uffffblock_given?\uffffF\uffffraise\uffffF\uffffset_morpher\uffffF\uffffset_from_morpher\uffffF\uffff[]\uffffN\ufffffrom_morphers\uffffV\uffffset_to_morpher\uffffF\uffff[]\uffffN\uffffto_morphers\uffffV\uffff===\uffffN\uffffcall\uffffN\uffff===\uffffN\uffffsend\uffffN\uffffraise\uffffF\uffffdo_morph\uffffF\ufffffrom\uffffF\ufffffind\uffffN\ufffffrom_morphers\uffffV\uffffis_a?\uffffN\uffffis_a?\uffffN\uffffdo_morph\uffffF\uffffdo_morph\uffffF\uffffto\uffffF\uffff\u0003\u0002\u0000\u0000\u0007\u0000\u0000\u0001\u0002\u0001\u0000\u0000\u0002\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(1, "no sym nor block", this.getEncoding0());
        this.setByteList(0, "instance-variable", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return module__0$RUBY$UnderSimple(file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$UnderSimple(final FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc             "StandardError"
        //    33: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    36: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    39: invokestatic    ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.class_1$RUBY$MorphingError:(Lruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    42: pop            
        //    43: aload_0        
        //    44: aload_1        
        //    45: aload_2        
        //    46: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    52: invokestatic    ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.module__2$RUBY$Morphable:(Lruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: aload_1        
        //    56: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    59: goto            67
        //    62: aload_1        
        //    63: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    66: athrow         
        //    67: aload_1        
        //    68: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    71: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     59     62     67     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_1$RUBY$MorphingError(final FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //     4: aload_2        
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareSuperClass:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyClass;
        //     8: aload_1        
        //     9: aload_1        
        //    10: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    13: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    16: swap           
        //    17: ldc             "MorphingError"
        //    19: swap           
        //    20: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    23: dup            
        //    24: astore_2       
        //    25: aload_1        
        //    26: swap           
        //    27: aload_0        
        //    28: aload_1        
        //    29: ldc             ",0,0,-1"
        //    31: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    34: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    37: aload_1        
        //    38: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: aload_1        
        //    42: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    45: goto            53
        //    48: aload_1        
        //    49: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    52: athrow         
        //    53: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  37     41     48     53     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_1$RUBY$MorphingError(final FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_1$RUBY$MorphingError(file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__2$RUBY$Morphable(final FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Morphable"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_1        
        //    28: aload_2        
        //    29: aload_0        
        //    30: ldc             "morphers"
        //    32: ldc             "method__3$RUBY$morphers"
        //    34: ldc             ",0,0,-1"
        //    36: iconst_0       
        //    37: ldc             "./proprietary//undersimple/core/morphable.rb"
        //    39: ldc             5
        //    41: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    44: ldc             "NONE"
        //    46: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: pop            
        //    50: aload_1        
        //    51: aload_2        
        //    52: aload_0        
        //    53: ldc             "to_morphers"
        //    55: ldc             "method__4$RUBY$to_morphers"
        //    57: ldc             ",0,0,-1"
        //    59: iconst_0       
        //    60: ldc             "./proprietary//undersimple/core/morphable.rb"
        //    62: ldc             9
        //    64: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    67: ldc             "NONE"
        //    69: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    72: pop            
        //    73: aload_1        
        //    74: aload_2        
        //    75: aload_0        
        //    76: ldc             "from_morphers"
        //    78: ldc             "method__5$RUBY$from_morphers"
        //    80: ldc             ",0,0,-1"
        //    82: iconst_0       
        //    83: ldc             "./proprietary//undersimple/core/morphable.rb"
        //    85: ldc             13
        //    87: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    90: ldc             "NONE"
        //    92: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    95: pop            
        //    96: aload_1        
        //    97: aload_2        
        //    98: aload_0        
        //    99: ldc_w           "set_morpher"
        //   102: ldc_w           "method__6$RUBY$set_morpher"
        //   105: ldc_w           "to_from;kind;morpher,3,0,-1"
        //   108: iconst_3       
        //   109: ldc             "./proprietary//undersimple/core/morphable.rb"
        //   111: ldc_w           17
        //   114: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   117: ldc_w           "qto_from;qkind;qmorpher"
        //   120: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   123: pop            
        //   124: aload_1        
        //   125: aload_2        
        //   126: aload_0        
        //   127: ldc_w           "set_from_morpher"
        //   130: ldc_w           "method__7$RUBY$set_from_morpher"
        //   133: ldc_w           "kind;sym;blk;morpher,1,1,-1"
        //   136: bipush          -2
        //   138: ldc             "./proprietary//undersimple/core/morphable.rb"
        //   140: ldc_w           21
        //   143: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   146: ldc_w           "qkind;osym;bblk"
        //   149: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   152: pop            
        //   153: aload_1        
        //   154: aload_2        
        //   155: aload_0        
        //   156: ldc_w           "set_to_morpher"
        //   159: ldc_w           "method__8$RUBY$set_to_morpher"
        //   162: ldc_w           "kind;sym;blk;morpher,1,1,-1"
        //   165: bipush          -2
        //   167: ldc             "./proprietary//undersimple/core/morphable.rb"
        //   169: ldc_w           31
        //   172: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   175: ldc_w           "qkind;osym;bblk"
        //   178: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   181: pop            
        //   182: aload_1        
        //   183: aload_2        
        //   184: aload_0        
        //   185: ldc             "from"
        //   187: ldc_w           "method__9$RUBY$from"
        //   190: ldc_w           "kind;sym;blk,1,1,-1"
        //   193: bipush          -2
        //   195: ldc             "./proprietary//undersimple/core/morphable.rb"
        //   197: ldc_w           41
        //   200: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   203: ldc_w           "qkind;osym;bblk"
        //   206: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   209: pop            
        //   210: aload_1        
        //   211: aload_2        
        //   212: aload_0        
        //   213: ldc             "to"
        //   215: ldc_w           "method__10$RUBY$to"
        //   218: ldc_w           "kind;sym;blk,1,1,-1"
        //   221: bipush          -2
        //   223: ldc             "./proprietary//undersimple/core/morphable.rb"
        //   225: ldc_w           49
        //   228: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   231: ldc_w           "qkind;osym;bblk"
        //   234: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   237: pop            
        //   238: aload_1        
        //   239: aload_2        
        //   240: aload_0        
        //   241: ldc_w           "do_morph"
        //   244: ldc_w           "method__11$RUBY$do_morph"
        //   247: ldc_w           "obj;morpher,2,0,-1"
        //   250: iconst_2       
        //   251: ldc             "./proprietary//undersimple/core/morphable.rb"
        //   253: ldc_w           57
        //   256: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   259: ldc_w           "qobj;qmorpher"
        //   262: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   265: pop            
        //   266: aload_1        
        //   267: aload_2        
        //   268: aload_0        
        //   269: ldc_w           "new_from"
        //   272: ldc_w           "method__12$RUBY$new_from"
        //   275: ldc_w           "kind;obj,2,0,-1"
        //   278: iconst_2       
        //   279: ldc             "./proprietary//undersimple/core/morphable.rb"
        //   281: ldc_w           68
        //   284: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   287: ldc_w           "qkind;qobj"
        //   290: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   293: pop            
        //   294: aload_1        
        //   295: aload_2        
        //   296: aload_0        
        //   297: ldc_w           "new_for"
        //   300: ldc_w           "method__13$RUBY$new_for"
        //   303: ldc_w           "obj;kind;morpher,1,0,-1"
        //   306: iconst_1       
        //   307: ldc             "./proprietary//undersimple/core/morphable.rb"
        //   309: ldc_w           72
        //   312: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   315: ldc_w           "qobj"
        //   318: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   321: pop            
        //   322: aload_1        
        //   323: aload_2        
        //   324: aload_0        
        //   325: ldc_w           "morph_into"
        //   328: ldc_w           "method__14$RUBY$morph_into"
        //   331: ldc_w           "kind,1,0,-1"
        //   334: iconst_1       
        //   335: ldc             "./proprietary//undersimple/core/morphable.rb"
        //   337: ldc_w           79
        //   340: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   343: ldc_w           "qkind"
        //   346: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   349: aload_1        
        //   350: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   353: goto            361
        //   356: aload_1        
        //   357: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   360: athrow         
        //   361: aload_1        
        //   362: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   365: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     353    356    361    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "morphers", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$morphers(final FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@morphers") ? file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getByteList0() : null) == null) {
            rubyObject = file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.setVariable0(threadContext.runtime, "@morphers", object, RuntimeHelpers.constructHash(threadContext.runtime, file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getSymbol0(threadContext.runtime, "to"), RubyHash.newHash(threadContext.runtime), file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getSymbol1(threadContext.runtime, "from"), RubyHash.newHash(threadContext.runtime)));
        }
        else if (!(rubyObject = file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getVariable0(threadContext.runtime, "@morphers", object)).isTrue()) {
            rubyObject = file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.setVariable1(threadContext.runtime, "@morphers", object, RuntimeHelpers.constructHash(threadContext.runtime, file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getSymbol0(threadContext.runtime, "to"), RubyHash.newHash(threadContext.runtime), file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getSymbol1(threadContext.runtime, "from"), RubyHash.newHash(threadContext.runtime)));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "to_morphers", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$to_morphers(final FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite0().call(threadContext, rubyObject, file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite1().call(threadContext, rubyObject, rubyObject), file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getSymbol0(threadContext.runtime, "to"));
    }
    
    @JRubyMethod(name = "from_morphers", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$from_morphers(final FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite2().call(threadContext, rubyObject, file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite3().call(threadContext, rubyObject, rubyObject), file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getSymbol1(threadContext.runtime, "from"));
    }
    
    @JRubyMethod(name = "set_morpher", frame = true, required = 3, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$set_morpher(final FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final IRubyObject p5, final Block p6) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          7
        //     6: aload_3        
        //     7: aload           7
        //     9: swap           
        //    10: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    13: pop            
        //    14: aload           4
        //    16: aload           7
        //    18: swap           
        //    19: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    22: pop            
        //    23: aload           5
        //    25: aload           7
        //    27: swap           
        //    28: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    31: pop            
        //    32: aload_0        
        //    33: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite4:()Lorg/jruby/runtime/CallSite;
        //    36: aload_1        
        //    37: aload_2        
        //    38: aload_0        
        //    39: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite5:()Lorg/jruby/runtime/CallSite;
        //    42: aload_1        
        //    43: aload_2        
        //    44: aload_2        
        //    45: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    48: aload           locals
        //    50: aload_1        
        //    51: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: dup            
        //    61: aload_2        
        //    62: aload_0        
        //    63: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite6:()Lorg/jruby/runtime/CallSite;
        //    66: aload_0        
        //    67: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite7:()Lorg/jruby/runtime/CallSite;
        //    70: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //    73: aload           locals
        //    75: aload_1        
        //    76: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: aload           locals
        //    84: aload_1        
        //    85: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    91: aload_1        
        //    92: aload_2        
        //    93: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    96: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  32     65      7     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "set_from_morpher", frame = true, required = 1, optional = 1, rest = -1)
    public static IRubyObject method__7$RUBY$set_from_morpher(final FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     4: dup            
        //     5: astore          10
        //     7: dup            
        //     8: astore          11
        //    10: astore          12
        //    12: aload_1        
        //    13: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    16: aload_3        
        //    17: iconst_1       
        //    18: iconst_2       
        //    19: invokestatic    org/jruby/runtime/Arity.raiseArgumentError:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;II)V
        //    22: aload_3        
        //    23: iconst_0       
        //    24: aaload         
        //    25: astore          9
        //    27: aload_3        
        //    28: iconst_1       
        //    29: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.elementOrNull:([Lorg/jruby/runtime/builtin/IRubyObject;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    32: dup            
        //    33: ifnull          41
        //    36: astore          10
        //    38: goto            48
        //    41: aload_1        
        //    42: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: astore          10
        //    47: pop            
        //    48: aload_1        
        //    49: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    52: aload           4
        //    54: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: astore          blk
        //    59: aload           sym
        //    61: dup            
        //    62: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    67: ifne            81
        //    70: pop            
        //    71: aload_0        
        //    72: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite8:()Lorg/jruby/runtime/CallSite;
        //    75: aload_1        
        //    76: aload_2        
        //    77: aload_2        
        //    78: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    81: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    86: ifeq            92
        //    89: goto            121
        //    92: aload_0        
        //    93: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite9:()Lorg/jruby/runtime/CallSite;
        //    96: aload_1        
        //    97: aload_2        
        //    98: aload_2        
        //    99: aload_0        
        //   100: aload_1        
        //   101: ldc_w           "ArgumentError"
        //   104: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getConstant1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   107: aload_0        
        //   108: aload_1        
        //   109: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   112: bipush          32
        //   114: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getString1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   117: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   120: pop            
        //   121: aload           sym
        //   123: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   128: ifeq            136
        //   131: aload           sym
        //   133: goto            138
        //   136: aload           blk
        //   138: astore          morpher
        //   140: aload_0        
        //   141: bipush          10
        //   143: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   146: aload_1        
        //   147: aload_2        
        //   148: aload_2        
        //   149: aload_0        
        //   150: aload_1        
        //   151: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   154: ldc             "from"
        //   156: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   159: aload           kind
        //   161: aload           morpher
        //   163: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   166: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name     Signature
        //  -----  ------  ----  -------  ---------------------------------------
        //  59     108     9     kind     Lorg/jruby/runtime/builtin/IRubyObject;
        //  59     108     10    sym      Lorg/jruby/runtime/builtin/IRubyObject;
        //  59     108     11    blk      Lorg/jruby/runtime/builtin/IRubyObject;
        //  59     108     12    morpher  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "set_to_morpher", frame = true, required = 1, optional = 1, rest = -1)
    public static IRubyObject method__8$RUBY$set_to_morpher(final FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     4: dup            
        //     5: astore          10
        //     7: dup            
        //     8: astore          11
        //    10: astore          12
        //    12: aload_1        
        //    13: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    16: aload_3        
        //    17: iconst_1       
        //    18: iconst_2       
        //    19: invokestatic    org/jruby/runtime/Arity.raiseArgumentError:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;II)V
        //    22: aload_3        
        //    23: iconst_0       
        //    24: aaload         
        //    25: astore          9
        //    27: aload_3        
        //    28: iconst_1       
        //    29: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.elementOrNull:([Lorg/jruby/runtime/builtin/IRubyObject;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    32: dup            
        //    33: ifnull          41
        //    36: astore          10
        //    38: goto            48
        //    41: aload_1        
        //    42: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: astore          10
        //    47: pop            
        //    48: aload_1        
        //    49: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    52: aload           4
        //    54: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: astore          blk
        //    59: aload           sym
        //    61: dup            
        //    62: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    67: ifne            83
        //    70: pop            
        //    71: aload_0        
        //    72: bipush          11
        //    74: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    77: aload_1        
        //    78: aload_2        
        //    79: aload_2        
        //    80: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    88: ifeq            94
        //    91: goto            125
        //    94: aload_0        
        //    95: bipush          12
        //    97: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   100: aload_1        
        //   101: aload_2        
        //   102: aload_2        
        //   103: aload_0        
        //   104: aload_1        
        //   105: ldc_w           "ArgumentError"
        //   108: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getConstant2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   111: aload_0        
        //   112: aload_1        
        //   113: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   116: bipush          32
        //   118: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getString1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   121: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   124: pop            
        //   125: aload           sym
        //   127: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   132: ifeq            140
        //   135: aload           sym
        //   137: goto            142
        //   140: aload           blk
        //   142: astore          morpher
        //   144: aload_0        
        //   145: bipush          13
        //   147: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   150: aload_1        
        //   151: aload_2        
        //   152: aload_2        
        //   153: aload_0        
        //   154: aload_1        
        //   155: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   158: ldc             "to"
        //   160: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   163: aload           kind
        //   165: aload           morpher
        //   167: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   170: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name     Signature
        //  -----  ------  ----  -------  ---------------------------------------
        //  59     112     9     kind     Lorg/jruby/runtime/builtin/IRubyObject;
        //  59     112     10    sym      Lorg/jruby/runtime/builtin/IRubyObject;
        //  59     112     11    blk      Lorg/jruby/runtime/builtin/IRubyObject;
        //  59     112     12    morpher  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "from", frame = true, required = 1, optional = 1, rest = -1)
    public static IRubyObject method__9$RUBY$from(final FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
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
        //    11: iconst_1       
        //    12: iconst_2       
        //    13: invokestatic    org/jruby/runtime/Arity.raiseArgumentError:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;II)V
        //    16: aload_3        
        //    17: iconst_0       
        //    18: aaload         
        //    19: aload           5
        //    21: swap           
        //    22: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    25: pop            
        //    26: aload_3        
        //    27: iconst_1       
        //    28: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.elementOrNull:([Lorg/jruby/runtime/builtin/IRubyObject;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    31: dup            
        //    32: ifnull          45
        //    35: aload           5
        //    37: swap           
        //    38: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: pop            
        //    42: goto            56
        //    45: aload           5
        //    47: aload_1        
        //    48: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: pop            
        //    55: pop            
        //    56: aload_1        
        //    57: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    60: aload           4
        //    62: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: aload           5
        //    67: swap           
        //    68: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    71: pop            
        //    72: aload           locals
        //    74: aload_1        
        //    75: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    78: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    81: dup            
        //    82: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    87: ifne            100
        //    90: pop            
        //    91: aload           locals
        //    93: aload_1        
        //    94: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    97: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   100: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   105: ifeq            155
        //   108: aload_0        
        //   109: bipush          14
        //   111: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   114: aload_1        
        //   115: aload_2        
        //   116: aload_2        
        //   117: aload           locals
        //   119: aload_1        
        //   120: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   123: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   126: aload           locals
        //   128: aload_1        
        //   129: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   132: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   135: aload           locals
        //   137: aload_1        
        //   138: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   141: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   144: aload           4
        //   146: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.getBlockFromBlockPassBody:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/Block;
        //   149: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   152: goto            187
        //   155: aload_0        
        //   156: bipush          15
        //   158: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   161: aload_1        
        //   162: aload_2        
        //   163: aload_0        
        //   164: bipush          16
        //   166: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   169: aload_1        
        //   170: aload_2        
        //   171: aload_2        
        //   172: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   175: aload           locals
        //   177: aload_1        
        //   178: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   181: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   184: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   187: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  72     116     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "to", frame = true, required = 1, optional = 1, rest = -1)
    public static IRubyObject method__10$RUBY$to(final FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
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
        //    11: iconst_1       
        //    12: iconst_2       
        //    13: invokestatic    org/jruby/runtime/Arity.raiseArgumentError:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;II)V
        //    16: aload_3        
        //    17: iconst_0       
        //    18: aaload         
        //    19: aload           5
        //    21: swap           
        //    22: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    25: pop            
        //    26: aload_3        
        //    27: iconst_1       
        //    28: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.elementOrNull:([Lorg/jruby/runtime/builtin/IRubyObject;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    31: dup            
        //    32: ifnull          45
        //    35: aload           5
        //    37: swap           
        //    38: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: pop            
        //    42: goto            56
        //    45: aload           5
        //    47: aload_1        
        //    48: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: pop            
        //    55: pop            
        //    56: aload_1        
        //    57: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    60: aload           4
        //    62: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: aload           5
        //    67: swap           
        //    68: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    71: pop            
        //    72: aload           locals
        //    74: aload_1        
        //    75: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    78: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    81: dup            
        //    82: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    87: ifne            100
        //    90: pop            
        //    91: aload           locals
        //    93: aload_1        
        //    94: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    97: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   100: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   105: ifeq            155
        //   108: aload_0        
        //   109: bipush          17
        //   111: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   114: aload_1        
        //   115: aload_2        
        //   116: aload_2        
        //   117: aload           locals
        //   119: aload_1        
        //   120: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   123: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   126: aload           locals
        //   128: aload_1        
        //   129: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   132: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   135: aload           locals
        //   137: aload_1        
        //   138: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   141: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   144: aload           4
        //   146: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.getBlockFromBlockPassBody:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/Block;
        //   149: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   152: goto            187
        //   155: aload_0        
        //   156: bipush          18
        //   158: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   161: aload_1        
        //   162: aload_2        
        //   163: aload_0        
        //   164: bipush          19
        //   166: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   169: aload_1        
        //   170: aload_2        
        //   171: aload_2        
        //   172: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   175: aload           locals
        //   177: aload_1        
        //   178: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   181: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   184: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   187: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  72     116     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "do_morph", frame = true, required = 2, optional = 0, rest = -1)
    public static IRubyObject method__11$RUBY$do_morph(final FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final Block p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          6
        //     6: aload_3        
        //     7: aload           6
        //     9: swap           
        //    10: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    13: pop            
        //    14: aload           4
        //    16: aload           6
        //    18: swap           
        //    19: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    22: pop            
        //    23: aload           locals
        //    25: aload_1        
        //    26: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    29: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    32: aload_1        
        //    33: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //    36: astore          10
        //    38: aload_0        
        //    39: bipush          20
        //    41: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    44: aload_1        
        //    45: aload_2        
        //    46: aload           10
        //    48: aload_0        
        //    49: aload_1        
        //    50: ldc_w           "Proc"
        //    53: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getConstant3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    56: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //    59: ifeq            94
        //    62: aload_0        
        //    63: bipush          21
        //    65: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    68: aload_1        
        //    69: aload_2        
        //    70: aload           locals
        //    72: aload_1        
        //    73: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: aload           locals
        //    81: aload_1        
        //    82: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    85: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    91: goto            169
        //    94: aload_0        
        //    95: bipush          22
        //    97: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   100: aload_1        
        //   101: aload_2        
        //   102: aload           10
        //   104: aload_0        
        //   105: aload_1        
        //   106: ldc_w           "Symbol"
        //   109: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getConstant4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   112: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   115: ifeq            150
        //   118: aload_0        
        //   119: bipush          23
        //   121: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   124: aload_1        
        //   125: aload_2        
        //   126: aload           locals
        //   128: aload_1        
        //   129: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   132: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   135: aload           locals
        //   137: aload_1        
        //   138: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   141: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   144: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   147: goto            169
        //   150: aload_0        
        //   151: bipush          24
        //   153: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   156: aload_1        
        //   157: aload_2        
        //   158: aload_2        
        //   159: aload_0        
        //   160: aload_1        
        //   161: ldc             "MorphingError"
        //   163: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getConstant5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   166: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   169: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  23     147     6     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "new_from", frame = true, required = 2, optional = 0, rest = -1)
    public static IRubyObject method__12$RUBY$new_from(final FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final Block p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_3        
        //     1: astore          10
        //     3: aload           4
        //     5: astore          obj
        //     7: aload_0        
        //     8: bipush          25
        //    10: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    13: aload_1        
        //    14: aload_2        
        //    15: aload_2        
        //    16: aload           obj
        //    18: aload_0        
        //    19: bipush          26
        //    21: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    24: aload_1        
        //    25: aload_2        
        //    26: aload_2        
        //    27: aload           kind
        //    29: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    32: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    35: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  7      29      10    kind  Lorg/jruby/runtime/builtin/IRubyObject;
        //  7      29      11    obj   Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "new_for", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__13$RUBY$new_for(final FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    15: bipush          27
        //    17: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    20: aload_1        
        //    21: aload_2        
        //    22: aload_0        
        //    23: bipush          28
        //    25: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    28: aload_1        
        //    29: aload_2        
        //    30: aload_2        
        //    31: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    34: aload_1        
        //    35: aload_2        
        //    36: aload_0        
        //    37: aload_1        
        //    38: ldc_w           "block_0$RUBY$new_for,2,k;m,true,1,./proprietary//undersimple/core/morphable.rb,73,true"
        //    41: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    44: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    47: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.aryToAry:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    53: aload_1        
        //    54: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    57: iconst_1       
        //    58: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.ensureMultipleAssignableRubyArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;Z)Lorg/jruby/RubyArray;
        //    61: astore          9
        //    63: aload           9
        //    65: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilZero:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    68: aload           locals
        //    70: swap           
        //    71: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: pop            
        //    75: aload           9
        //    77: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilOne:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: aload           locals
        //    82: swap           
        //    83: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    86: pop            
        //    87: aload           9
        //    89: pop            
        //    90: aload_0        
        //    91: bipush          31
        //    93: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    96: aload_1        
        //    97: aload_2        
        //    98: aload_2        
        //    99: aload           locals
        //   101: aload_1        
        //   102: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   108: aload           locals
        //   110: aload_1        
        //   111: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   114: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   117: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   120: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     107     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_0$RUBY$new_for(final FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    57: bipush          29
        //    59: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    62: aload_1        
        //    63: aload_2        
        //    64: aload           k
        //    66: aload_0        
        //    67: aload_1        
        //    68: ldc_w           "Module"
        //    71: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getConstant6:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: dup            
        //    78: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    83: ifeq            112
        //    86: pop            
        //    87: aload_0        
        //    88: bipush          30
        //    90: invokevirtual   ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    93: aload_1        
        //    94: aload_2        
        //    95: aload           5
        //    97: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   100: aload_1        
        //   101: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   104: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   107: aload           k
        //   109: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   112: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  56     57      9     k     Lorg/jruby/runtime/builtin/IRubyObject;
        //  56     57      10    m     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "morph_into", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__14$RUBY$morph_into(final FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite(32).call(threadContext, rubyObject, rubyObject, rubyObject, file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.getCallSite(33).call(threadContext, rubyObject, rubyObject, rubyObject2));
    }
    
    public static IRubyObject module__2$RUBY$Morphable(final FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__2$RUBY$Morphable(file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$UnderSimple(final FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$UnderSimple(file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC, threadContext, rubyObject, block);
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
        final FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC = new FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC();
        final String string = FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.class.getClassLoader().getResource("ruby/jit/FILE_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.class").toString();
        file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_825ABBEAC2CE6851BB4C6CCA0F1CCC3900B264FC.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
