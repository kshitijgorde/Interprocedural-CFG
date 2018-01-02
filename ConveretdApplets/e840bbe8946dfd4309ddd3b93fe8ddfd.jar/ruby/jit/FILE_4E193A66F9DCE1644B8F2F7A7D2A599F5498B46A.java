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

public class FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A extends AbstractScript
{
    public FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A() {
        this.filename = "./proprietary//undersimple/core/parameter.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffinclude\uffffF\uffffinclude\uffffF\uffffinclude\uffffF\uffffinclude\uffffF\uffffattr_accessor\uffffF\uffffempty?\uffffN\uffffverify\uffffF\uffff==\uffffN\uffffsize\uffffN\uffffvalues\uffffV\uffffis_a?\uffffN\uffffvalues\uffffV\uffffinclude?\uffffN\ufffffirst\uffffN\uffffvalues\uffffV\uffffinclude?\uffffN\uffffvalues\uffffV\uffff\u0002\u0002\u0000\u0000\u0005\u0000\u0000\u0001\u0002\u0001\u0000\u0000\u0004\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(3, "undersimple/core/morphable", this.getEncoding0());
        this.setByteList(0, "undersimple/core/describable", this.getEncoding0());
        this.setByteList(2, "undersimple/core/loosely_typable", this.getEncoding0());
        this.setByteList(1, "undersimple/core/verifiable", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A file_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getCallSite0().call(threadContext, rubyObject, rubyObject, file_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getString0(threadContext.runtime, 32));
        file_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getCallSite1().call(threadContext, rubyObject, rubyObject, file_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getString1(threadContext.runtime, 32));
        file_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getCallSite2().call(threadContext, rubyObject, rubyObject, file_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getString2(threadContext.runtime, 32));
        file_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getCallSite3().call(threadContext, rubyObject, rubyObject, file_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getString2(threadContext.runtime, 32));
        file_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getCallSite4().call(threadContext, rubyObject, rubyObject, file_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getString3(threadContext.runtime, 32));
        return module__0$RUBY$UnderSimple(file_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$UnderSimple(final FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    33: invokestatic    ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.class_1$RUBY$ParameterDescription:(Lruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject class_1$RUBY$ParameterDescription(final FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    10: ldc             "ParameterDescription"
        //    12: swap           
        //    13: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    16: dup            
        //    17: astore_2       
        //    18: aload_1        
        //    19: swap           
        //    20: aload_0        
        //    21: aload_1        
        //    22: ldc             ",0,0,-1"
        //    24: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    27: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    30: aload_0        
        //    31: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getCallSite5:()Lorg/jruby/runtime/CallSite;
        //    34: aload_1        
        //    35: aload_2        
        //    36: aload_2        
        //    37: aload_0        
        //    38: aload_1        
        //    39: ldc             "Describable"
        //    41: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    44: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: pop            
        //    48: aload_0        
        //    49: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getCallSite6:()Lorg/jruby/runtime/CallSite;
        //    52: aload_1        
        //    53: aload_2        
        //    54: aload_2        
        //    55: aload_0        
        //    56: aload_1        
        //    57: ldc             "Verifiable"
        //    59: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getConstant1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    62: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: pop            
        //    66: aload_0        
        //    67: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getCallSite7:()Lorg/jruby/runtime/CallSite;
        //    70: aload_1        
        //    71: aload_2        
        //    72: aload_2        
        //    73: aload_0        
        //    74: aload_1        
        //    75: ldc             "LooselyTypable"
        //    77: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getConstant2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: pop            
        //    84: aload_0        
        //    85: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getCallSite8:()Lorg/jruby/runtime/CallSite;
        //    88: aload_1        
        //    89: aload_2        
        //    90: aload_2        
        //    91: aload_0        
        //    92: aload_1        
        //    93: ldc             "Morphable"
        //    95: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getConstant3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    98: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   101: pop            
        //   102: aload_0        
        //   103: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getCallSite9:()Lorg/jruby/runtime/CallSite;
        //   106: aload_1        
        //   107: aload_2        
        //   108: aload_2        
        //   109: aload_0        
        //   110: aload_1        
        //   111: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   114: ldc             "name"
        //   116: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   119: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   122: pop            
        //   123: aload_1        
        //   124: aload_2        
        //   125: aload_0        
        //   126: ldc             "initialize"
        //   128: ldc             "method__2$RUBY$initialize"
        //   130: ldc             "name,1,0,-1"
        //   132: iconst_1       
        //   133: ldc             "./proprietary//undersimple/core/parameter.rb"
        //   135: ldc             18
        //   137: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   140: ldc             "qname"
        //   142: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   145: pop            
        //   146: aload_1        
        //   147: aload_2        
        //   148: aload_0        
        //   149: ldc_w           "values"
        //   152: ldc_w           "method__3$RUBY$values"
        //   155: ldc_w           "args,0,0,0"
        //   158: iconst_m1      
        //   159: ldc             "./proprietary//undersimple/core/parameter.rb"
        //   161: ldc_w           23
        //   164: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   167: ldc_w           "rargs"
        //   170: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   173: pop            
        //   174: aload_1        
        //   175: aload_2        
        //   176: ldc_w           "accept?"
        //   179: ldc_w           "valid_value?"
        //   182: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defineAlias:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/Object;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   185: aload_1        
        //   186: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   189: goto            197
        //   192: aload_1        
        //   193: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   196: athrow         
        //   197: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  30     185    192    197    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "initialize", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__2$RUBY$initialize(final FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A file_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A, final ThreadContext threadContext, final IRubyObject object, final IRubyObject value, final Block block) {
        return file_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.setVariable0(threadContext.runtime, "@name", object, value);
    }
    
    @JRubyMethod(name = "values", frame = true, required = 0, optional = 0, rest = 0)
    public static IRubyObject method__3$RUBY$values(final FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload_3        
        //     7: aload_1        
        //     8: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    11: iconst_0       
        //    12: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createSubarray:([Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;I)Lorg/jruby/RubyArray;
        //    15: aload           5
        //    17: swap           
        //    18: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    21: pop            
        //    22: aload_0        
        //    23: bipush          10
        //    25: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    28: aload_1        
        //    29: aload_2        
        //    30: aload           locals
        //    32: aload_1        
        //    33: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    36: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    42: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    47: ifeq            53
        //    50: goto            109
        //    53: aload_0        
        //    54: aload_1        
        //    55: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    58: ldc             "@values"
        //    60: aload_2        
        //    61: aload           locals
        //    63: aload_1        
        //    64: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.setVariable1:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: pop            
        //    74: aload_0        
        //    75: bipush          11
        //    77: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    80: aload_1        
        //    81: aload_2        
        //    82: aload_2        
        //    83: aload_0        
        //    84: aload_1        
        //    85: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    88: ldc             "allowed_values"
        //    90: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    93: aload_1        
        //    94: aload_2        
        //    95: aload_0        
        //    96: aload_1        
        //    97: ldc             "block_0$RUBY$values,1,x,false,2,./proprietary//undersimple/core/parameter.rb,26,true"
        //    99: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   102: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   105: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   108: pop            
        //   109: aload_0        
        //   110: aload_1        
        //   111: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   114: ldc             "@values"
        //   116: aload_2        
        //   117: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getVariable0:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   120: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  22     99      5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_0$RUBY$values(final FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          12
        //    28: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_0        
        //    34: bipush          13
        //    36: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    39: aload_1        
        //    40: aload_2        
        //    41: aload_0        
        //    42: bipush          14
        //    44: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    47: aload_1        
        //    48: aload_2        
        //    49: aload_2        
        //    50: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    53: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    56: ldc2_w          1
        //    59: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;J)Lorg/jruby/runtime/builtin/IRubyObject;
        //    62: dup            
        //    63: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    68: ifeq            102
        //    71: pop            
        //    72: aload_0        
        //    73: bipush          15
        //    75: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    78: aload_1        
        //    79: aload_2        
        //    80: aload_0        
        //    81: bipush          16
        //    83: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    86: aload_1        
        //    87: aload_2        
        //    88: aload_2        
        //    89: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    92: aload_0        
        //    93: aload_1        
        //    94: ldc             "Enumerable"
        //    96: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getConstant4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    99: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   102: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   107: ifeq            149
        //   110: aload_0        
        //   111: bipush          17
        //   113: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   116: aload_1        
        //   117: aload_2        
        //   118: aload_0        
        //   119: bipush          18
        //   121: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   124: aload_1        
        //   125: aload_2        
        //   126: aload_0        
        //   127: bipush          19
        //   129: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   132: aload_1        
        //   133: aload_2        
        //   134: aload_2        
        //   135: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   138: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   141: aload           x
        //   143: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   146: goto            174
        //   149: aload_0        
        //   150: bipush          20
        //   152: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   155: aload_1        
        //   156: aload_2        
        //   157: aload_0        
        //   158: bipush          21
        //   160: invokevirtual   ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   163: aload_1        
        //   164: aload_2        
        //   165: aload_2        
        //   166: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   169: aload           x
        //   171: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   174: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     150     9     x     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_1$RUBY$ParameterDescription(final FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A file_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_1$RUBY$ParameterDescription(file_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$UnderSimple(final FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A file_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$UnderSimple(file_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A, threadContext, rubyObject, block);
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
        final FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A file_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A = new FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A();
        final String string = FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.class.getClassLoader().getResource("ruby/jit/FILE_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.class").toString();
        file_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_4E193A66F9DCE1644B8F2F7A7D2A599F5498B46A.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
