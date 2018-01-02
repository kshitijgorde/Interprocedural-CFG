// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.RubyBoolean;
import org.jruby.runtime.Arity;
import org.jruby.RubyHash;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_228BF5929741C6B8797082BEAE19A72196A57D6B extends AbstractScript
{
    public FILE_228BF5929741C6B8797082BEAE19A72196A57D6B() {
        this.filename = "./proprietary//undersimple/core/measurement.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffextend\uffffN\uffffextend\uffffN\uffffattr_accessor\uffffF\uffffattr_accessor\uffffF\uffffis_a?\uffffN\uffffattr_accessor\uffffF\uffffnew\uffffN\uffffinstance_eval\uffffN\uffffparameters\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffis_a?\uffffN\uffffattr_accessor\uffffF\uffffnew\uffffN\uffffinstance_eval\uffffN\uffffreports\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffparameters=\uffffN\uffffparameters=\uffffV\uffffdup\uffffN\uffffparameters\uffffV\uffffreports=\uffffN\uffffreports=\uffffV\uffffdup\uffffN\uffffreports\uffffV\uffffreject\uffffN\uffffkeys\uffffN\uffffhas_key?\uffffN\uffffparameters\uffffV\uffffreject\uffffN\uffffkeys\uffffN\uffffparameters\uffffV\uffffhas_key?\uffffN\uffffreject\uffffN\uffffkeys\uffffN\uffffhas_key?\uffffN\uffffreports\uffffV\uffffreject\uffffN\uffffkeys\uffffN\uffffreports\uffffV\uffffhas_key?\uffffN\uffffempty?\uffffN\uffffunknown_parameters\uffffF\uffffempty?\uffffN\uffffmissing_parameters\uffffF\uffffempty?\uffffN\uffffrejected_parameters\uffffF\uffffkeys\uffffN\uffffreject\uffffN\uffffparameters\uffffV\uffffhas_key?\uffffN\uffffvalid_value?\uffffN\uffff[]\uffffN\uffffattr_accessor\uffffF\uffffattr_accessor\uffffF\uffffunknown_parameters\uffffN\uffffclass\uffffN\uffffparameters\uffffV\uffffmissing_parameters\uffffN\uffffclass\uffffN\uffffparameters\uffffV\uffffunknown_reports\uffffN\uffffclass\uffffN\uffffreports\uffffV\uffffmissing_reports\uffffN\uffffclass\uffffN\uffffreports\uffffV\uffffeach\uffffN\uffffreports\uffffN\uffffclass\uffffN\uffffrespond_to?\uffffF\uffffsend\uffffF\uffffvalid_value?\uffffN\uffffreports\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffraise\uffffF\uffffinclude\uffffF\uffff\u0004\u0002\u0000\u0000\b\u0000\u0000\u0003\u0004\u0006\u0000\u0000\u0006\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(2, "undersimple/core/describable", this.getEncoding0());
        this.setByteList(1, "undersimple/core/report", this.getEncoding0());
        this.setByteList(4, "invalid value ", this.getEncoding0());
        this.setByteList(5, " for ", this.getEncoding0());
        this.setByteList(3, "instance-variable", this.getEncoding0());
        this.setByteList(0, "undersimple/core/parameter", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B file_228BF5929741C6B8797082BEAE19A72196A57D6B, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite0().call(threadContext, rubyObject, rubyObject, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getString0(threadContext.runtime, 32));
        file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite1().call(threadContext, rubyObject, rubyObject, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getString1(threadContext.runtime, 32));
        file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite2().call(threadContext, rubyObject, rubyObject, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getString2(threadContext.runtime, 32));
        return module__0$RUBY$UnderSimple(file_228BF5929741C6B8797082BEAE19A72196A57D6B, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$UnderSimple(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.module__1$RUBY$MeasurementDescription:(Lruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$MeasurementDescription(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "MeasurementDescription"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_1        
        //    28: aload_2        
        //    29: aload_2        
        //    30: aload_0        
        //    31: ldc             "included"
        //    33: ldc             "method__2$RUBY$included"
        //    35: ldc             "klass,1,0,-1"
        //    37: iconst_1       
        //    38: ldc             "./proprietary//undersimple/core/measurement.rb"
        //    40: ldc             7
        //    42: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    45: ldc             "qklass"
        //    47: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: pop            
        //    51: aload_0        
        //    52: aload_1        
        //    53: aload_2        
        //    54: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    60: invokestatic    ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.module__3$RUBY$ClassMethods:(Lruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: pop            
        //    64: aload_0        
        //    65: aload_1        
        //    66: aload_2        
        //    67: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    73: invokestatic    ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.module__16$RUBY$InstanceMethods:(Lruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: pop            
        //    77: aload_0        
        //    78: bipush          81
        //    80: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    83: aload_1        
        //    84: aload_2        
        //    85: aload_2        
        //    86: aload_0        
        //    87: aload_1        
        //    88: ldc_w           "InstanceMethods"
        //    91: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getConstant7:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    94: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "included", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__2$RUBY$included(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B file_228BF5929741C6B8797082BEAE19A72196A57D6B, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite3().call(threadContext, rubyObject, rubyObject2, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getConstant0(threadContext, "Describable"));
        return file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite4().call(threadContext, rubyObject, rubyObject2, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getConstant1(threadContext, "ClassMethods"));
    }
    
    public static IRubyObject module__3$RUBY$ClassMethods(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "ClassMethods"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite5:()Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_2        
        //    34: aload_0        
        //    35: aload_1        
        //    36: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    39: ldc             "parameters"
        //    41: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    44: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: pop            
        //    48: aload_0        
        //    49: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite6:()Lorg/jruby/runtime/CallSite;
        //    52: aload_1        
        //    53: aload_2        
        //    54: aload_2        
        //    55: aload_0        
        //    56: aload_1        
        //    57: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    60: ldc             "reports"
        //    62: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    65: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    68: pop            
        //    69: aload_1        
        //    70: aload_2        
        //    71: aload_0        
        //    72: ldc             "parameters"
        //    74: ldc             "method__4$RUBY$parameters"
        //    76: ldc             ",0,0,-1"
        //    78: iconst_0       
        //    79: ldc             "./proprietary//undersimple/core/measurement.rb"
        //    81: ldc             16
        //    83: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    86: ldc             "NONE"
        //    88: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    91: pop            
        //    92: aload_1        
        //    93: aload_2        
        //    94: aload_0        
        //    95: ldc_w           "parameter"
        //    98: ldc_w           "method__5$RUBY$parameter"
        //   101: ldc_w           "name;blk;obj,1,0,-1"
        //   104: iconst_1       
        //   105: ldc             "./proprietary//undersimple/core/measurement.rb"
        //   107: ldc_w           20
        //   110: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   113: ldc_w           "qname;bblk"
        //   116: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   119: pop            
        //   120: aload_1        
        //   121: aload_2        
        //   122: aload_0        
        //   123: ldc             "reports"
        //   125: ldc_w           "method__6$RUBY$reports"
        //   128: ldc             ",0,0,-1"
        //   130: iconst_0       
        //   131: ldc             "./proprietary//undersimple/core/measurement.rb"
        //   133: ldc_w           27
        //   136: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   139: ldc             "NONE"
        //   141: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   144: pop            
        //   145: aload_1        
        //   146: aload_2        
        //   147: aload_0        
        //   148: ldc_w           "report"
        //   151: ldc_w           "method__7$RUBY$report"
        //   154: ldc_w           "name;blk;obj,1,0,-1"
        //   157: iconst_1       
        //   158: ldc             "./proprietary//undersimple/core/measurement.rb"
        //   160: ldc_w           31
        //   163: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   166: ldc_w           "qname;bblk"
        //   169: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   172: pop            
        //   173: aload_1        
        //   174: aload_2        
        //   175: aload_0        
        //   176: ldc_w           "inherited"
        //   179: ldc_w           "method__8$RUBY$inherited"
        //   182: ldc             "klass,1,0,-1"
        //   184: iconst_1       
        //   185: ldc             "./proprietary//undersimple/core/measurement.rb"
        //   187: ldc_w           38
        //   190: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   193: ldc             "qklass"
        //   195: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   198: pop            
        //   199: aload_1        
        //   200: aload_2        
        //   201: aload_0        
        //   202: ldc_w           "timeout"
        //   205: ldc_w           "method__9$RUBY$timeout"
        //   208: ldc_w           "cnt,0,1,-1"
        //   211: iconst_m1      
        //   212: ldc             "./proprietary//undersimple/core/measurement.rb"
        //   214: ldc_w           43
        //   217: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   220: ldc_w           "ocnt"
        //   223: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   226: pop            
        //   227: aload_1        
        //   228: aload_2        
        //   229: aload_0        
        //   230: ldc_w           "unknown_parameters"
        //   233: ldc_w           "method__10$RUBY$unknown_parameters"
        //   236: ldc_w           "params,1,0,-1"
        //   239: iconst_1       
        //   240: ldc             "./proprietary//undersimple/core/measurement.rb"
        //   242: ldc_w           48
        //   245: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   248: ldc_w           "qparams"
        //   251: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   254: pop            
        //   255: aload_1        
        //   256: aload_2        
        //   257: aload_0        
        //   258: ldc_w           "missing_parameters"
        //   261: ldc_w           "method__11$RUBY$missing_parameters"
        //   264: ldc_w           "params,1,0,-1"
        //   267: iconst_1       
        //   268: ldc             "./proprietary//undersimple/core/measurement.rb"
        //   270: ldc_w           54
        //   273: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   276: ldc_w           "qparams"
        //   279: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   282: pop            
        //   283: aload_1        
        //   284: aload_2        
        //   285: aload_0        
        //   286: ldc_w           "unknown_reports"
        //   289: ldc_w           "method__12$RUBY$unknown_reports"
        //   292: ldc_w           "reps,1,0,-1"
        //   295: iconst_1       
        //   296: ldc             "./proprietary//undersimple/core/measurement.rb"
        //   298: ldc_w           60
        //   301: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   304: ldc_w           "qreps"
        //   307: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   310: pop            
        //   311: aload_1        
        //   312: aload_2        
        //   313: aload_0        
        //   314: ldc_w           "missing_reports"
        //   317: ldc_w           "method__13$RUBY$missing_reports"
        //   320: ldc_w           "reps,1,0,-1"
        //   323: iconst_1       
        //   324: ldc             "./proprietary//undersimple/core/measurement.rb"
        //   326: ldc_w           66
        //   329: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   332: ldc_w           "qreps"
        //   335: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   338: pop            
        //   339: aload_1        
        //   340: aload_2        
        //   341: aload_0        
        //   342: ldc_w           "valid_parameters?"
        //   345: ldc_w           "method__14$RUBY$valid_parameters_p_"
        //   348: ldc_w           "params,1,0,-1"
        //   351: iconst_1       
        //   352: ldc             "./proprietary//undersimple/core/measurement.rb"
        //   354: ldc_w           72
        //   357: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   360: ldc_w           "qparams"
        //   363: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   366: pop            
        //   367: aload_1        
        //   368: aload_2        
        //   369: aload_0        
        //   370: ldc_w           "rejected_parameters"
        //   373: ldc_w           "method__15$RUBY$rejected_parameters"
        //   376: ldc_w           "params,1,0,-1"
        //   379: iconst_1       
        //   380: ldc             "./proprietary//undersimple/core/measurement.rb"
        //   382: ldc_w           81
        //   385: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   388: ldc_w           "qparams"
        //   391: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   394: aload_1        
        //   395: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   398: goto            406
        //   401: aload_1        
        //   402: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   405: athrow         
        //   406: aload_1        
        //   407: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   410: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     398    401    406    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "parameters", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$parameters(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B file_228BF5929741C6B8797082BEAE19A72196A57D6B, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@parameters") ? file_228BF5929741C6B8797082BEAE19A72196A57D6B.getByteList3() : null) == null) {
            rubyObject = file_228BF5929741C6B8797082BEAE19A72196A57D6B.setVariable0(threadContext.runtime, "@parameters", object, RubyHash.newHash(threadContext.runtime));
        }
        else if (!(rubyObject = file_228BF5929741C6B8797082BEAE19A72196A57D6B.getVariable0(threadContext.runtime, "@parameters", object)).isTrue()) {
            rubyObject = file_228BF5929741C6B8797082BEAE19A72196A57D6B.setVariable1(threadContext.runtime, "@parameters", object, RubyHash.newHash(threadContext.runtime));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "parameter", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$parameter(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    31: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite7:()Lorg/jruby/runtime/CallSite;
        //    34: aload_1        
        //    35: aload_2        
        //    36: aload           locals
        //    38: aload_1        
        //    39: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    42: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: aload_0        
        //    46: aload_1        
        //    47: ldc             "Symbol"
        //    49: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getConstant2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    60: ifeq            86
        //    63: aload_0        
        //    64: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite8:()Lorg/jruby/runtime/CallSite;
        //    67: aload_1        
        //    68: aload_2        
        //    69: aload_2        
        //    70: aload           locals
        //    72: aload_1        
        //    73: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: pop            
        //    83: goto            86
        //    86: aload           locals
        //    88: aload_0        
        //    89: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite9:()Lorg/jruby/runtime/CallSite;
        //    92: aload_1        
        //    93: aload_2        
        //    94: aload_0        
        //    95: aload_1        
        //    96: ldc_w           "ParameterDescription"
        //    99: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getConstant3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   102: aload           locals
        //   104: aload_1        
        //   105: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   108: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   111: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   114: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   117: pop            
        //   118: aload_0        
        //   119: bipush          10
        //   121: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   124: aload_1        
        //   125: aload_2        
        //   126: aload           locals
        //   128: aload_1        
        //   129: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   132: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   135: aload           locals
        //   137: aload_1        
        //   138: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   141: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   144: aload           4
        //   146: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.getBlockFromBlockPassBody:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/Block;
        //   149: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   152: pop            
        //   153: aload_0        
        //   154: bipush          11
        //   156: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   159: aload_1        
        //   160: aload_2        
        //   161: aload_2        
        //   162: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   165: dup            
        //   166: aload_2        
        //   167: aload_0        
        //   168: bipush          12
        //   170: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   173: aload_0        
        //   174: bipush          13
        //   176: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   179: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   182: aload           locals
        //   184: aload_1        
        //   185: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   188: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   191: aload           locals
        //   193: aload_1        
        //   194: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   197: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   200: aload_1        
        //   201: aload_2        
        //   202: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   205: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  30     176     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "reports", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$reports(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B file_228BF5929741C6B8797082BEAE19A72196A57D6B, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@reports") ? file_228BF5929741C6B8797082BEAE19A72196A57D6B.getByteList3() : null) == null) {
            rubyObject = file_228BF5929741C6B8797082BEAE19A72196A57D6B.setVariable2(threadContext.runtime, "@reports", object, RubyHash.newHash(threadContext.runtime));
        }
        else if (!(rubyObject = file_228BF5929741C6B8797082BEAE19A72196A57D6B.getVariable1(threadContext.runtime, "@reports", object)).isTrue()) {
            rubyObject = file_228BF5929741C6B8797082BEAE19A72196A57D6B.setVariable3(threadContext.runtime, "@reports", object, RubyHash.newHash(threadContext.runtime));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "report", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$report(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    31: bipush          14
        //    33: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    36: aload_1        
        //    37: aload_2        
        //    38: aload           locals
        //    40: aload_1        
        //    41: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    44: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: aload_0        
        //    48: aload_1        
        //    49: ldc             "Symbol"
        //    51: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getConstant4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    62: ifeq            90
        //    65: aload_0        
        //    66: bipush          15
        //    68: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    71: aload_1        
        //    72: aload_2        
        //    73: aload_2        
        //    74: aload           locals
        //    76: aload_1        
        //    77: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    86: pop            
        //    87: goto            90
        //    90: aload           locals
        //    92: aload_0        
        //    93: bipush          16
        //    95: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    98: aload_1        
        //    99: aload_2        
        //   100: aload_0        
        //   101: aload_1        
        //   102: ldc_w           "ReportDescription"
        //   105: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getConstant5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   108: aload           locals
        //   110: aload_1        
        //   111: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   114: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   117: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   120: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   123: pop            
        //   124: aload_0        
        //   125: bipush          17
        //   127: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   130: aload_1        
        //   131: aload_2        
        //   132: aload           locals
        //   134: aload_1        
        //   135: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   138: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   141: aload           locals
        //   143: aload_1        
        //   144: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   147: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   150: aload           4
        //   152: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.getBlockFromBlockPassBody:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/Block;
        //   155: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   158: pop            
        //   159: aload_0        
        //   160: bipush          18
        //   162: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   165: aload_1        
        //   166: aload_2        
        //   167: aload_2        
        //   168: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   171: dup            
        //   172: aload_2        
        //   173: aload_0        
        //   174: bipush          19
        //   176: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   179: aload_0        
        //   180: bipush          20
        //   182: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   185: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   188: aload           locals
        //   190: aload_1        
        //   191: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   194: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   197: aload           locals
        //   199: aload_1        
        //   200: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   203: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   206: aload_1        
        //   207: aload_2        
        //   208: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   211: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  30     182     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "inherited", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$inherited(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B file_228BF5929741C6B8797082BEAE19A72196A57D6B, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        RuntimeHelpers.doAttrAsgn(rubyObject2, RuntimeHelpers.selectAttrAsgnCallSite(rubyObject2, rubyObject, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(21), file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(22)), file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(23).call(threadContext, rubyObject, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(24).call(threadContext, rubyObject, rubyObject)), threadContext, rubyObject);
        return RuntimeHelpers.doAttrAsgn(rubyObject2, RuntimeHelpers.selectAttrAsgnCallSite(rubyObject2, rubyObject, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(25), file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(26)), file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(27).call(threadContext, rubyObject, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(28).call(threadContext, rubyObject, rubyObject)), threadContext, rubyObject);
    }
    
    @JRubyMethod(name = "timeout", frame = true, required = 0, optional = 1, rest = -1)
    public static IRubyObject method__9$RUBY$timeout(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B file_228BF5929741C6B8797082BEAE19A72196A57D6B, final ThreadContext threadContext, final IRubyObject object, final IRubyObject[] array, final Block block) {
        final IRubyObject nil = threadContext.nil;
        Arity.raiseArgumentError(threadContext.runtime, array, 0, 1);
        if (RuntimeHelpers.elementOrNull(array, 0) == null) {
            final IRubyObject nil2 = threadContext.nil;
        }
        return file_228BF5929741C6B8797082BEAE19A72196A57D6B.getVariable2(threadContext.runtime, "@timeout", object);
    }
    
    @JRubyMethod(name = "unknown_parameters", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__10$RUBY$unknown_parameters(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    15: bipush          29
        //    17: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    20: aload_1        
        //    21: aload_2        
        //    22: aload_0        
        //    23: bipush          30
        //    25: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    28: aload_1        
        //    29: aload_2        
        //    30: aload           locals
        //    32: aload_1        
        //    33: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    36: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    42: aload_1        
        //    43: aload_2        
        //    44: aload_0        
        //    45: aload_1        
        //    46: ldc_w           "block_0$RUBY$unknown_parameters,1,k,false,2,./proprietary//undersimple/core/measurement.rb,49,true"
        //    49: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    52: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    55: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     45      5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_0$RUBY$unknown_parameters(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          31
        //    28: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_0        
        //    34: bipush          32
        //    36: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    39: aload_1        
        //    40: aload_2        
        //    41: aload_2        
        //    42: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: aload           k
        //    47: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     26      9     k     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "missing_parameters", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__11$RUBY$missing_parameters(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B file_228BF5929741C6B8797082BEAE19A72196A57D6B, final ThreadContext threadContext, final IRubyObject self, final IRubyObject valueZeroDepthZero, final Block block) {
        threadContext.getCurrentScope().setValueZeroDepthZero(valueZeroDepthZero);
        return file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(33).callIter(threadContext, self, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(34).call(threadContext, self, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(35).call(threadContext, self, self)), RuntimeHelpers.createBlock(threadContext, self, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getBlockBody1(threadContext, "block_1$RUBY$missing_parameters,1,k,false,2,./proprietary//undersimple/core/measurement.rb,55,true")));
    }
    
    public static IRubyObject block_1$RUBY$missing_parameters(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          36
        //    28: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           5
        //    35: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    38: aload_1        
        //    39: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    42: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: aload           k
        //    47: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     26      9     k     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "unknown_reports", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__12$RUBY$unknown_reports(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    15: bipush          37
        //    17: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    20: aload_1        
        //    21: aload_2        
        //    22: aload_0        
        //    23: bipush          38
        //    25: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    28: aload_1        
        //    29: aload_2        
        //    30: aload           locals
        //    32: aload_1        
        //    33: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    36: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    42: aload_1        
        //    43: aload_2        
        //    44: aload_0        
        //    45: aload_1        
        //    46: ldc_w           "block_2$RUBY$unknown_reports,1,k,false,2,./proprietary//undersimple/core/measurement.rb,61,true"
        //    49: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getBlockBody2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    52: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    55: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     45      5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_2$RUBY$unknown_reports(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          39
        //    28: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_0        
        //    34: bipush          40
        //    36: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    39: aload_1        
        //    40: aload_2        
        //    41: aload_2        
        //    42: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: aload           k
        //    47: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     26      9     k     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "missing_reports", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__13$RUBY$missing_reports(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B file_228BF5929741C6B8797082BEAE19A72196A57D6B, final ThreadContext threadContext, final IRubyObject self, final IRubyObject valueZeroDepthZero, final Block block) {
        threadContext.getCurrentScope().setValueZeroDepthZero(valueZeroDepthZero);
        return file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(41).callIter(threadContext, self, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(42).call(threadContext, self, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(43).call(threadContext, self, self)), RuntimeHelpers.createBlock(threadContext, self, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getBlockBody3(threadContext, "block_3$RUBY$missing_reports,1,k,false,2,./proprietary//undersimple/core/measurement.rb,67,true")));
    }
    
    public static IRubyObject block_3$RUBY$missing_reports(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          44
        //    28: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           5
        //    35: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    38: aload_1        
        //    39: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    42: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: aload           k
        //    47: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     26      9     k     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "valid_parameters?", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__14$RUBY$valid_parameters_p_(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B file_228BF5929741C6B8797082BEAE19A72196A57D6B, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        IRubyObject rubyObject3;
        if ((rubyObject3 = file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(45).call(threadContext, rubyObject, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(46).call(threadContext, rubyObject, rubyObject, rubyObject2))).isTrue()) {
            rubyObject3 = file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(47).call(threadContext, rubyObject, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(48).call(threadContext, rubyObject, rubyObject, rubyObject2));
        }
        if (rubyObject3.isTrue()) {
            return file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(49).call(threadContext, rubyObject, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(50).call(threadContext, rubyObject, rubyObject, rubyObject2));
        }
        final RubyBoolean false = threadContext.runtime.getFalse();
        threadContext.pollThreadEvents();
        return false;
    }
    
    @JRubyMethod(name = "rejected_parameters", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__15$RUBY$rejected_parameters(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B file_228BF5929741C6B8797082BEAE19A72196A57D6B, final ThreadContext threadContext, final IRubyObject self, final IRubyObject valueZeroDepthZero, final Block block) {
        threadContext.getCurrentScope().setValueZeroDepthZero(valueZeroDepthZero);
        return file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(51).call(threadContext, self, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(52).callIter(threadContext, self, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(53).call(threadContext, self, self), RuntimeHelpers.createBlock(threadContext, self, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getBlockBody4(threadContext, "block_4$RUBY$rejected_parameters,2,sym;spec,true,1,./proprietary//undersimple/core/measurement.rb,82,false"))));
    }
    
    public static IRubyObject block_4$RUBY$rejected_parameters(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    10: aload           5
        //    12: swap           
        //    13: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    16: aload           5
        //    18: swap           
        //    19: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    22: pop            
        //    23: aload_1        
        //    24: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    27: aload           4
        //    29: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    32: aload_3        
        //    33: aload_1        
        //    34: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    37: iconst_1       
        //    38: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.ensureMultipleAssignableRubyArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;Z)Lorg/jruby/RubyArray;
        //    41: astore          9
        //    43: aload           9
        //    45: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilZero:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    48: aload           5
        //    50: swap           
        //    51: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: pop            
        //    55: aload           9
        //    57: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilOne:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: aload           5
        //    62: swap           
        //    63: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: pop            
        //    67: aload           9
        //    69: pop            
        //    70: pop            
        //    71: aload_0        
        //    72: bipush          54
        //    74: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    77: aload_1        
        //    78: aload_2        
        //    79: aload           locals
        //    81: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    84: aload_1        
        //    85: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    91: aload           locals
        //    93: aload_1        
        //    94: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    97: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   100: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   103: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   108: ifeq            166
        //   111: aload_0        
        //   112: bipush          55
        //   114: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   117: aload_1        
        //   118: aload_2        
        //   119: aload           locals
        //   121: aload_1        
        //   122: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   125: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   128: aload_0        
        //   129: bipush          56
        //   131: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   134: aload_1        
        //   135: aload_2        
        //   136: aload           locals
        //   138: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   141: aload_1        
        //   142: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   145: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   148: aload           locals
        //   150: aload_1        
        //   151: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   154: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   157: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   160: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   163: goto            177
        //   166: aload_1        
        //   167: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   170: invokevirtual   org/jruby/Ruby.getTrue:()Lorg/jruby/RubyBoolean;
        //   173: aload_1        
        //   174: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //   177: areturn        
        //   178: pop            
        //   179: goto            71
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  71     107     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  71     178    178    182    Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject module__3$RUBY$ClassMethods(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B file_228BF5929741C6B8797082BEAE19A72196A57D6B, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__3$RUBY$ClassMethods(file_228BF5929741C6B8797082BEAE19A72196A57D6B, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__16$RUBY$InstanceMethods(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc_w           "InstanceMethods"
        //    11: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    14: dup            
        //    15: astore_2       
        //    16: aload_1        
        //    17: swap           
        //    18: aload_0        
        //    19: aload_1        
        //    20: ldc             ",0,0,-1"
        //    22: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getScope3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    25: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    28: aload_0        
        //    29: bipush          57
        //    31: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    34: aload_1        
        //    35: aload_2        
        //    36: aload_2        
        //    37: aload_0        
        //    38: aload_1        
        //    39: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    42: ldc             "parameters"
        //    44: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    47: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: pop            
        //    51: aload_0        
        //    52: bipush          58
        //    54: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    57: aload_1        
        //    58: aload_2        
        //    59: aload_2        
        //    60: aload_0        
        //    61: aload_1        
        //    62: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    65: ldc             "reports"
        //    67: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    70: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: pop            
        //    74: aload_1        
        //    75: aload_2        
        //    76: aload_0        
        //    77: ldc_w           "unknown_parameters"
        //    80: ldc_w           "method__17$RUBY$unknown_parameters"
        //    83: ldc             ",0,0,-1"
        //    85: iconst_0       
        //    86: ldc             "./proprietary//undersimple/core/measurement.rb"
        //    88: ldc_w           95
        //    91: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    94: ldc             "NONE"
        //    96: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    99: pop            
        //   100: aload_1        
        //   101: aload_2        
        //   102: aload_0        
        //   103: ldc_w           "missing_parameters"
        //   106: ldc_w           "method__18$RUBY$missing_parameters"
        //   109: ldc             ",0,0,-1"
        //   111: iconst_0       
        //   112: ldc             "./proprietary//undersimple/core/measurement.rb"
        //   114: ldc_w           98
        //   117: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   120: ldc             "NONE"
        //   122: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   125: pop            
        //   126: aload_1        
        //   127: aload_2        
        //   128: aload_0        
        //   129: ldc_w           "unknown_reports"
        //   132: ldc_w           "method__19$RUBY$unknown_reports"
        //   135: ldc             ",0,0,-1"
        //   137: iconst_0       
        //   138: ldc             "./proprietary//undersimple/core/measurement.rb"
        //   140: ldc_w           101
        //   143: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   146: ldc             "NONE"
        //   148: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   151: pop            
        //   152: aload_1        
        //   153: aload_2        
        //   154: aload_0        
        //   155: ldc_w           "missing_reports"
        //   158: ldc_w           "method__20$RUBY$missing_reports"
        //   161: ldc             ",0,0,-1"
        //   163: iconst_0       
        //   164: ldc             "./proprietary//undersimple/core/measurement.rb"
        //   166: ldc_w           104
        //   169: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   172: ldc             "NONE"
        //   174: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   177: pop            
        //   178: aload_1        
        //   179: aload_2        
        //   180: aload_0        
        //   181: ldc_w           "auto_report"
        //   184: ldc_w           "method__21$RUBY$auto_report"
        //   187: ldc             ",0,0,-1"
        //   189: iconst_0       
        //   190: ldc             "./proprietary//undersimple/core/measurement.rb"
        //   192: ldc_w           107
        //   195: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   198: ldc             "NONE"
        //   200: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   203: aload_1        
        //   204: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   207: goto            215
        //   210: aload_1        
        //   211: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   214: athrow         
        //   215: aload_1        
        //   216: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   219: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  28     207    210    215    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "unknown_parameters", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__17$RUBY$unknown_parameters(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B file_228BF5929741C6B8797082BEAE19A72196A57D6B, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(59).call(threadContext, rubyObject, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(60).call(threadContext, rubyObject, rubyObject), file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(61).call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "missing_parameters", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__18$RUBY$missing_parameters(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B file_228BF5929741C6B8797082BEAE19A72196A57D6B, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(62).call(threadContext, rubyObject, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(63).call(threadContext, rubyObject, rubyObject), file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(64).call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "unknown_reports", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__19$RUBY$unknown_reports(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B file_228BF5929741C6B8797082BEAE19A72196A57D6B, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(65).call(threadContext, rubyObject, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(66).call(threadContext, rubyObject, rubyObject), file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(67).call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "missing_reports", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__20$RUBY$missing_reports(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B file_228BF5929741C6B8797082BEAE19A72196A57D6B, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(68).call(threadContext, rubyObject, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(69).call(threadContext, rubyObject, rubyObject), file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(70).call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "auto_report", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__21$RUBY$auto_report(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B file_228BF5929741C6B8797082BEAE19A72196A57D6B, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(71).callIter(threadContext, self, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(72).call(threadContext, self, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite(73).call(threadContext, self, self)), RuntimeHelpers.createBlock(threadContext, self, file_228BF5929741C6B8797082BEAE19A72196A57D6B.getBlockBody5(threadContext, "block_5$RUBY$auto_report,2,sym;spec;val,true,1,./proprietary//undersimple/core/measurement.rb,108,true")));
    }
    
    public static IRubyObject block_5$RUBY$auto_report(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    60: pop            
        //    61: pop            
        //    62: aload_0        
        //    63: bipush          74
        //    65: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    68: aload_1        
        //    69: aload_2        
        //    70: aload_2        
        //    71: aload           sym
        //    73: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    81: ifeq            103
        //    84: aload_0        
        //    85: bipush          75
        //    87: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    90: aload_1        
        //    91: aload_2        
        //    92: aload_2        
        //    93: aload           sym
        //    95: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    98: astore          val
        //   100: goto            103
        //   103: aload_0        
        //   104: bipush          76
        //   106: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   109: aload_1        
        //   110: aload_2        
        //   111: aload           spec
        //   113: aload           val
        //   115: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   118: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   123: ifeq            167
        //   126: aload_0        
        //   127: bipush          77
        //   129: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   132: aload_1        
        //   133: aload_2        
        //   134: aload_2        
        //   135: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   138: dup            
        //   139: aload_2        
        //   140: aload_0        
        //   141: bipush          78
        //   143: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   146: aload_0        
        //   147: bipush          79
        //   149: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   152: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   155: aload           sym
        //   157: aload           val
        //   159: aload_1        
        //   160: aload_2        
        //   161: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   164: goto            243
        //   167: aload_0        
        //   168: bipush          80
        //   170: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   173: aload_1        
        //   174: aload_2        
        //   175: aload_2        
        //   176: aload_0        
        //   177: aload_1        
        //   178: ldc_w           "RuntimeError"
        //   181: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getConstant6:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   184: aload_1        
        //   185: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   188: ldc_w           20
        //   191: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   194: aload_0        
        //   195: aload_1        
        //   196: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   199: bipush          32
        //   201: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getString4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   204: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   207: aload           val
        //   209: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   214: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   217: aload_0        
        //   218: aload_1        
        //   219: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   222: bipush          32
        //   224: invokevirtual   ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.getString5:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   227: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   230: aload           sym
        //   232: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   237: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   240: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   243: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  62     182     9     sym   Lorg/jruby/runtime/builtin/IRubyObject;
        //  62     182     10    spec  Lorg/jruby/runtime/builtin/IRubyObject;
        //  62     182     11    val   Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject module__16$RUBY$InstanceMethods(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B file_228BF5929741C6B8797082BEAE19A72196A57D6B, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__16$RUBY$InstanceMethods(file_228BF5929741C6B8797082BEAE19A72196A57D6B, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$MeasurementDescription(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B file_228BF5929741C6B8797082BEAE19A72196A57D6B, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$MeasurementDescription(file_228BF5929741C6B8797082BEAE19A72196A57D6B, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$UnderSimple(final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B file_228BF5929741C6B8797082BEAE19A72196A57D6B, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$UnderSimple(file_228BF5929741C6B8797082BEAE19A72196A57D6B, threadContext, rubyObject, block);
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
        final FILE_228BF5929741C6B8797082BEAE19A72196A57D6B file_228BF5929741C6B8797082BEAE19A72196A57D6B = new FILE_228BF5929741C6B8797082BEAE19A72196A57D6B();
        final String string = FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.class.getClassLoader().getResource("ruby/jit/FILE_228BF5929741C6B8797082BEAE19A72196A57D6B.class").toString();
        file_228BF5929741C6B8797082BEAE19A72196A57D6B.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_228BF5929741C6B8797082BEAE19A72196A57D6B.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
