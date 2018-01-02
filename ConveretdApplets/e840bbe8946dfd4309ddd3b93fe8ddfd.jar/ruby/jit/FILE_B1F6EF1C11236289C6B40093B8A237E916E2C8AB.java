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

public class FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB extends AbstractScript
{
    public FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB() {
        this.filename = "./proprietary//undersimple/core/loose_type.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffinclude\uffffF\uffffinclude\uffffF\uffffattr_accessor\uffffF\uffff[]\uffffN\uffffall\uffffV\uffffall\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffff[]\uffffF\uffffraise\uffffF\uffffnew\uffffN\uffffinspect\uffffN\uffff[]\uffffF\uffffnew\uffffN\uffff[]=\uffffF\uffff[]=\uffffN\uffff[]=\uffffV\uffffinstance_eval\uffffN\uffff\u0002\u0001\u0000\u0000\u0004\u0000\u0000\u0000\u0001\u0000\u0000\u0000\u0003\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(0, "undersimple/core/describable", this.getEncoding0());
        this.setByteList(2, "No such type: ", this.getEncoding0());
        this.setByteList(1, "undersimple/core/verifiable", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB file_B1F6EF1C11236289C6B40093B8A237E916E2C8AB, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getCallSite0().call(threadContext, rubyObject, rubyObject, file_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getString0(threadContext.runtime, 32));
        file_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getCallSite1().call(threadContext, rubyObject, rubyObject, file_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getString1(threadContext.runtime, 32));
        return module__0$RUBY$UnderSimple(file_B1F6EF1C11236289C6B40093B8A237E916E2C8AB, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$UnderSimple(final FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    33: invokestatic    ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.class_1$RUBY$LooseType:(Lruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject class_1$RUBY$LooseType(final FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    10: ldc             "LooseType"
        //    12: swap           
        //    13: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    16: dup            
        //    17: astore_2       
        //    18: aload_1        
        //    19: swap           
        //    20: aload_0        
        //    21: aload_1        
        //    22: ldc             ",0,0,-1"
        //    24: invokevirtual   ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    27: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    30: aload_0        
        //    31: invokevirtual   ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getCallSite2:()Lorg/jruby/runtime/CallSite;
        //    34: aload_1        
        //    35: aload_2        
        //    36: aload_2        
        //    37: aload_0        
        //    38: aload_1        
        //    39: ldc             "Describable"
        //    41: invokevirtual   ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    44: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: pop            
        //    48: aload_0        
        //    49: invokevirtual   ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getCallSite3:()Lorg/jruby/runtime/CallSite;
        //    52: aload_1        
        //    53: aload_2        
        //    54: aload_2        
        //    55: aload_0        
        //    56: aload_1        
        //    57: ldc             "Verifiable"
        //    59: invokevirtual   ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getConstant1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    62: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: pop            
        //    66: aload_0        
        //    67: invokevirtual   ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getCallSite4:()Lorg/jruby/runtime/CallSite;
        //    70: aload_1        
        //    71: aload_2        
        //    72: aload_2        
        //    73: aload_0        
        //    74: aload_1        
        //    75: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    78: ldc             "name"
        //    80: invokevirtual   ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    83: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    86: pop            
        //    87: aload_1        
        //    88: aload_1        
        //    89: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    92: aload_2        
        //    93: ldc             "@@all"
        //    95: aload_1        
        //    96: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    99: invokestatic    org/jruby/RubyHash.newHash:(Lorg/jruby/Ruby;)Lorg/jruby/RubyHash;
        //   102: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.fastDeclareClassVariable:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: pop            
        //   106: aload_1        
        //   107: aload_2        
        //   108: aload_2        
        //   109: aload_0        
        //   110: ldc             "all"
        //   112: ldc             "method__2$RUBY$all"
        //   114: ldc             ",0,0,-1"
        //   116: iconst_0       
        //   117: ldc             "./proprietary//undersimple/core/loose_type.rb"
        //   119: ldc             13
        //   121: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   124: ldc             "NONE"
        //   126: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   129: pop            
        //   130: aload_1        
        //   131: aload_2        
        //   132: aload_2        
        //   133: aload_0        
        //   134: ldc             "[]"
        //   136: ldc             "method__3$RUBY$_aref_"
        //   138: ldc             "key,1,0,-1"
        //   140: iconst_1       
        //   141: ldc             "./proprietary//undersimple/core/loose_type.rb"
        //   143: ldc             17
        //   145: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   148: ldc             "qkey"
        //   150: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   153: pop            
        //   154: aload_1        
        //   155: aload_2        
        //   156: aload_2        
        //   157: aload_0        
        //   158: ldc             "[]="
        //   160: ldc             "method__4$RUBY$_aref_equal_"
        //   162: ldc             "key;val,2,0,-1"
        //   164: iconst_2       
        //   165: ldc             "./proprietary//undersimple/core/loose_type.rb"
        //   167: ldc             21
        //   169: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   172: ldc             "qkey;qval"
        //   174: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   177: pop            
        //   178: aload_1        
        //   179: aload_2        
        //   180: aload_2        
        //   181: aload_0        
        //   182: ldc_w           "for"
        //   185: ldc_w           "method__5$RUBY$for"
        //   188: ldc_w           "val;instance,1,0,-1"
        //   191: iconst_1       
        //   192: ldc             "./proprietary//undersimple/core/loose_type.rb"
        //   194: ldc_w           25
        //   197: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   200: ldc_w           "qval"
        //   203: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   206: pop            
        //   207: aload_1        
        //   208: aload_2        
        //   209: aload_2        
        //   210: aload_0        
        //   211: ldc_w           "new_or_existing_for_name"
        //   214: ldc_w           "method__6$RUBY$new_or_existing_for_name"
        //   217: ldc_w           "name;blk,1,0,-1"
        //   220: iconst_1       
        //   221: ldc             "./proprietary//undersimple/core/loose_type.rb"
        //   223: ldc_w           31
        //   226: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   229: ldc_w           "qname;bblk"
        //   232: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   235: pop            
        //   236: aload_1        
        //   237: aload_2        
        //   238: aload_0        
        //   239: ldc_w           "initialize"
        //   242: ldc_w           "method__7$RUBY$initialize"
        //   245: ldc_w           "name;blk,1,0,-1"
        //   248: iconst_1       
        //   249: ldc             "./proprietary//undersimple/core/loose_type.rb"
        //   251: ldc_w           35
        //   254: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   257: ldc_w           "qname;bblk"
        //   260: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   263: aload_1        
        //   264: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   267: goto            275
        //   270: aload_1        
        //   271: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   274: athrow         
        //   275: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  30     263    270    275    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "all", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__2$RUBY$all(final FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB file_B1F6EF1C11236289C6B40093B8A237E916E2C8AB, final ThreadContext context, final IRubyObject self, final Block block) {
        return RuntimeHelpers.fastFetchClassVariable(context, context.runtime, self, "@@all");
    }
    
    @JRubyMethod(name = "[]", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$_aref_(final FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    15: invokevirtual   ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getCallSite5:()Lorg/jruby/runtime/CallSite;
        //    18: aload_1        
        //    19: aload_2        
        //    20: aload_0        
        //    21: invokevirtual   ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getCallSite6:()Lorg/jruby/runtime/CallSite;
        //    24: aload_1        
        //    25: aload_2        
        //    26: aload_2        
        //    27: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    30: aload           locals
        //    32: aload_1        
        //    33: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    36: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    42: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     29      5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "[]=", frame = true, required = 2, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$_aref_equal_(final FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final Block p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_3        
        //     1: astore          10
        //     3: aload           4
        //     5: astore          val
        //     7: aload_0        
        //     8: invokevirtual   ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getCallSite7:()Lorg/jruby/runtime/CallSite;
        //    11: aload_1        
        //    12: aload_2        
        //    13: aload_2        
        //    14: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    17: dup            
        //    18: aload_2        
        //    19: aload_0        
        //    20: invokevirtual   ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getCallSite8:()Lorg/jruby/runtime/CallSite;
        //    23: aload_0        
        //    24: invokevirtual   ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getCallSite9:()Lorg/jruby/runtime/CallSite;
        //    27: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //    30: aload           key
        //    32: aload           val
        //    34: aload_1        
        //    35: aload_2        
        //    36: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  7      33      10    key   Lorg/jruby/runtime/builtin/IRubyObject;
        //  7      33      11    val   Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "for", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$for(final FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    17: bipush          10
        //    19: invokevirtual   ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    22: aload_1        
        //    23: aload_2        
        //    24: aload_2        
        //    25: aload           locals
        //    27: aload_1        
        //    28: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    31: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    34: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    37: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    40: pop            
        //    41: aload           locals
        //    43: aload_1        
        //    44: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    55: ifeq            61
        //    58: goto            144
        //    61: aload_0        
        //    62: bipush          11
        //    64: invokevirtual   ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    67: aload_1        
        //    68: aload_2        
        //    69: aload_2        
        //    70: aload_0        
        //    71: bipush          12
        //    73: invokevirtual   ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    76: aload_1        
        //    77: aload_2        
        //    78: aload_0        
        //    79: aload_1        
        //    80: ldc_w           "ArgumentError"
        //    83: invokevirtual   ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getConstant2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    86: aload_1        
        //    87: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    90: ldc_w           20
        //    93: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    96: aload_0        
        //    97: aload_1        
        //    98: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   101: bipush          32
        //   103: invokevirtual   ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getString2:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   106: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   109: aload_0        
        //   110: bipush          13
        //   112: invokevirtual   ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   115: aload_1        
        //   116: aload_2        
        //   117: aload           locals
        //   119: aload_1        
        //   120: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   123: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   126: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   129: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   134: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   137: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   140: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   143: pop            
        //   144: aload           locals
        //   146: aload_1        
        //   147: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   150: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   153: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     140     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "new_or_existing_for_name", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$new_or_existing_for_name(final FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    21: bipush          14
        //    23: invokevirtual   ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    26: aload_1        
        //    27: aload_2        
        //    28: aload_2        
        //    29: aload           name
        //    31: dup2           
        //    32: astore          11
        //    34: astore          12
        //    36: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: dup            
        //    40: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    45: ifne            85
        //    48: pop            
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload           12
        //    53: aload           11
        //    55: aload_0        
        //    56: bipush          15
        //    58: invokevirtual   ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    61: aload_1        
        //    62: aload_2        
        //    63: aload_2        
        //    64: aload           name
        //    66: aload           blk
        //    68: aload           4
        //    70: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.getBlockFromBlockPassBody:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/Block;
        //    73: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: aload_0        
        //    77: bipush          16
        //    79: invokevirtual   ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    82: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.opElementAsgnWithOrPartTwoOneArg:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    85: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  20     66      9     name  Lorg/jruby/runtime/builtin/IRubyObject;
        //  20     66      10    blk   Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "initialize", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$initialize(final FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    14: aload_1        
        //    15: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    18: aload           4
        //    20: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    23: aload           5
        //    25: swap           
        //    26: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    29: pop            
        //    30: aload_0        
        //    31: aload_1        
        //    32: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    35: ldc_w           "@name"
        //    38: aload_2        
        //    39: aload           locals
        //    41: aload_1        
        //    42: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    48: invokevirtual   ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.setVariable0:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: pop            
        //    52: aload_0        
        //    53: aload_1        
        //    54: ldc             "LooseType"
        //    56: invokevirtual   ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getConstant3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    59: dup            
        //    60: aload_2        
        //    61: aload_0        
        //    62: bipush          17
        //    64: invokevirtual   ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    67: aload_0        
        //    68: bipush          18
        //    70: invokevirtual   ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    73: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //    76: aload           locals
        //    78: aload_1        
        //    79: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    85: aload_2        
        //    86: aload_1        
        //    87: aload_2        
        //    88: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    91: pop            
        //    92: aload           locals
        //    94: aload_1        
        //    95: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    98: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   101: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   106: ifeq            138
        //   109: aload_0        
        //   110: bipush          19
        //   112: invokevirtual   ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   115: aload_1        
        //   116: aload_2        
        //   117: aload_2        
        //   118: aload           locals
        //   120: aload_1        
        //   121: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   124: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   127: aload           4
        //   129: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.getBlockFromBlockPassBody:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/Block;
        //   132: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   135: goto            142
        //   138: aload_1        
        //   139: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   142: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  30     113     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_1$RUBY$LooseType(final FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB file_B1F6EF1C11236289C6B40093B8A237E916E2C8AB, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_1$RUBY$LooseType(file_B1F6EF1C11236289C6B40093B8A237E916E2C8AB, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$UnderSimple(final FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB file_B1F6EF1C11236289C6B40093B8A237E916E2C8AB, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$UnderSimple(file_B1F6EF1C11236289C6B40093B8A237E916E2C8AB, threadContext, rubyObject, block);
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
        final FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB file_B1F6EF1C11236289C6B40093B8A237E916E2C8AB = new FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB();
        final String string = FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.class.getClassLoader().getResource("ruby/jit/FILE_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.class").toString();
        file_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_B1F6EF1C11236289C6B40093B8A237E916E2C8AB.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
