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

public class FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4 extends AbstractScript
{
    public FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4() {
        this.filename = "./lib//lister/runner/measurements/services.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffget_system_services\uffffV\uffffprogress\uffffF\uffffget_programs\uffffV\uffffprogress\uffffF\uffffget_udp_servers\uffffV\uffffprogress\uffffF\uffffget_tcp_servers\uffffV\ufffflog\uffffF\uffffeach\uffffN\uffffall\uffffN\uffff<<\uffffN\uffffprograms\uffffV\uffffuniq!\uffffN\uffffprograms\uffffV\ufffflog\uffffF\uffffeach\uffffN\uffffall\uffffN\uffff<<\uffffN\uffffsystem\uffffV\uffffuniq!\uffffN\uffffsystem\uffffV\ufffflog\uffffF\uffffeach\uffffN\uffff<<\uffffN\uffffudp\uffffV\uffffport\uffffN\uffffuniq!\uffffN\uffffudp\uffffV\ufffflog\uffffF\uffffeach\uffffN\uffff<<\uffffN\ufffftcp\uffffV\uffffport\uffffN\uffffuniq!\uffffN\ufffftcp\uffffV\uffff\u0003\u0002\u0003\u0000\t\u0000\u0000\u0004\b\u0004\u0000\u0000\t\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(5, "Gathering list of running programs", this.getEncoding0());
        this.setByteList(1, "lister/utils/services", this.getEncoding0());
        this.setByteList(3, "lister/utils/programs", this.getEncoding0());
        this.setByteList(8, "Gathering TCP listening servers list", this.getEncoding0());
        this.setByteList(6, "Gathering list of system services", this.getEncoding0());
        this.setByteList(2, "lister/utils/servers", this.getEncoding0());
        this.setByteList(7, "Gathering UDP listening servers list", this.getEncoding0());
        this.setByteList(0, "lister/measurements/services", this.getEncoding0());
        this.setByteList(4, "instance-variable", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4 file_978F9C4578A02BC05F54056E84B1095E5DF90DF4, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite0().call(threadContext, rubyObject, rubyObject, file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getString0(threadContext.runtime, 32));
        file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite1().call(threadContext, rubyObject, rubyObject, file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getString1(threadContext.runtime, 32));
        file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite2().call(threadContext, rubyObject, rubyObject, file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getString2(threadContext.runtime, 32));
        file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite3().call(threadContext, rubyObject, rubyObject, file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getString3(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_978F9C4578A02BC05F54056E84B1095E5DF90DF4, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4.module__1$RUBY$Measurements:(Lruby/jit/FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Measurements"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc_w           "Measurement"
        //    34: invokevirtual   ruby/jit/FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getConstant8:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    37: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    40: invokestatic    ruby/jit/FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4.class_2$RUBY$Services:(Lruby/jit/FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: aload_1        
        //    44: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: goto            55
        //    50: aload_1        
        //    51: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    54: athrow         
        //    55: aload_1        
        //    56: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    59: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     47     50     55     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_2$RUBY$Services(final FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc             "Services"
        //    19: swap           
        //    20: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    23: dup            
        //    24: astore_2       
        //    25: aload_1        
        //    26: swap           
        //    27: aload_0        
        //    28: aload_1        
        //    29: ldc             ",0,0,-1"
        //    31: invokevirtual   ruby/jit/FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    34: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    37: aload_1        
        //    38: aload_2        
        //    39: aload_0        
        //    40: ldc             "tcp"
        //    42: ldc             "method__3$RUBY$tcp"
        //    44: ldc             ",0,0,-1"
        //    46: iconst_0       
        //    47: ldc             "./lib//lister/runner/measurements/services.rb"
        //    49: ldc             9
        //    51: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    54: ldc             "NONE"
        //    56: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    59: pop            
        //    60: aload_1        
        //    61: aload_2        
        //    62: aload_0        
        //    63: ldc             "udp"
        //    65: ldc             "method__4$RUBY$udp"
        //    67: ldc             ",0,0,-1"
        //    69: iconst_0       
        //    70: ldc             "./lib//lister/runner/measurements/services.rb"
        //    72: ldc             13
        //    74: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    77: ldc             "NONE"
        //    79: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: pop            
        //    83: aload_1        
        //    84: aload_2        
        //    85: aload_0        
        //    86: ldc             "system"
        //    88: ldc             "method__5$RUBY$system"
        //    90: ldc             ",0,0,-1"
        //    92: iconst_0       
        //    93: ldc             "./lib//lister/runner/measurements/services.rb"
        //    95: ldc             17
        //    97: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   100: ldc             "NONE"
        //   102: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: pop            
        //   106: aload_1        
        //   107: aload_2        
        //   108: aload_0        
        //   109: ldc             "programs"
        //   111: ldc             "method__6$RUBY$programs"
        //   113: ldc             ",0,0,-1"
        //   115: iconst_0       
        //   116: ldc             "./lib//lister/runner/measurements/services.rb"
        //   118: ldc             21
        //   120: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   123: ldc             "NONE"
        //   125: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   128: pop            
        //   129: aload_1        
        //   130: aload_2        
        //   131: aload_0        
        //   132: ldc_w           "execute"
        //   135: ldc_w           "method__7$RUBY$execute"
        //   138: ldc             ",0,0,-1"
        //   140: iconst_0       
        //   141: ldc             "./lib//lister/runner/measurements/services.rb"
        //   143: ldc_w           25
        //   146: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   149: ldc             "NONE"
        //   151: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   154: pop            
        //   155: aload_1        
        //   156: aload_2        
        //   157: aload_0        
        //   158: ldc_w           "get_programs"
        //   161: ldc_w           "method__8$RUBY$get_programs"
        //   164: ldc             ",0,0,-1"
        //   166: iconst_0       
        //   167: ldc             "./lib//lister/runner/measurements/services.rb"
        //   169: ldc_w           35
        //   172: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   175: ldc             "NONE"
        //   177: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   180: pop            
        //   181: aload_1        
        //   182: aload_2        
        //   183: aload_0        
        //   184: ldc_w           "get_system_services"
        //   187: ldc_w           "method__9$RUBY$get_system_services"
        //   190: ldc             ",0,0,-1"
        //   192: iconst_0       
        //   193: ldc             "./lib//lister/runner/measurements/services.rb"
        //   195: ldc_w           43
        //   198: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   201: ldc             "NONE"
        //   203: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   206: pop            
        //   207: aload_1        
        //   208: aload_2        
        //   209: aload_0        
        //   210: ldc_w           "get_udp_servers"
        //   213: ldc_w           "method__10$RUBY$get_udp_servers"
        //   216: ldc             ",0,0,-1"
        //   218: iconst_0       
        //   219: ldc             "./lib//lister/runner/measurements/services.rb"
        //   221: ldc_w           51
        //   224: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   227: ldc             "NONE"
        //   229: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   232: pop            
        //   233: aload_1        
        //   234: aload_2        
        //   235: aload_0        
        //   236: ldc_w           "get_tcp_servers"
        //   239: ldc_w           "method__11$RUBY$get_tcp_servers"
        //   242: ldc             ",0,0,-1"
        //   244: iconst_0       
        //   245: ldc             "./lib//lister/runner/measurements/services.rb"
        //   247: ldc_w           59
        //   250: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   253: ldc             "NONE"
        //   255: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   258: aload_1        
        //   259: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   262: goto            270
        //   265: aload_1        
        //   266: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   269: athrow         
        //   270: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  37     258    265    270    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "tcp", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$tcp(final FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4 file_978F9C4578A02BC05F54056E84B1095E5DF90DF4, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@tcp") ? file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getByteList4() : null) == null) {
            rubyObject = file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.setVariable0(threadContext.runtime, "@tcp", object, threadContext.runtime.newArray());
        }
        else if (!(rubyObject = file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getVariable0(threadContext.runtime, "@tcp", object)).isTrue()) {
            rubyObject = file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.setVariable1(threadContext.runtime, "@tcp", object, threadContext.runtime.newArray());
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "udp", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$udp(final FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4 file_978F9C4578A02BC05F54056E84B1095E5DF90DF4, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@udp") ? file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getByteList4() : null) == null) {
            rubyObject = file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.setVariable2(threadContext.runtime, "@udp", object, threadContext.runtime.newArray());
        }
        else if (!(rubyObject = file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getVariable1(threadContext.runtime, "@udp", object)).isTrue()) {
            rubyObject = file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.setVariable3(threadContext.runtime, "@udp", object, threadContext.runtime.newArray());
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "system", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$system(final FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4 file_978F9C4578A02BC05F54056E84B1095E5DF90DF4, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@system") ? file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getByteList4() : null) == null) {
            rubyObject = file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.setVariable4(threadContext.runtime, "@system", object, threadContext.runtime.newArray());
        }
        else if (!(rubyObject = file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getVariable2(threadContext.runtime, "@system", object)).isTrue()) {
            rubyObject = file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.setVariable5(threadContext.runtime, "@system", object, threadContext.runtime.newArray());
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "programs", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$programs(final FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4 file_978F9C4578A02BC05F54056E84B1095E5DF90DF4, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@programs") ? file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getByteList4() : null) == null) {
            rubyObject = file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.setVariable6(threadContext.runtime, "@programs", object, threadContext.runtime.newArray());
        }
        else if (!(rubyObject = file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getVariable3(threadContext.runtime, "@programs", object)).isTrue()) {
            rubyObject = file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.setVariable7(threadContext.runtime, "@programs", object, threadContext.runtime.newArray());
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "execute", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$execute(final FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4 file_978F9C4578A02BC05F54056E84B1095E5DF90DF4, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite4().call(threadContext, rubyObject, rubyObject);
        file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite5().call(threadContext, rubyObject, rubyObject, file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getFixnum0(threadContext.runtime, 25));
        file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite6().call(threadContext, rubyObject, rubyObject);
        file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite7().call(threadContext, rubyObject, rubyObject, file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getFixnum1(threadContext.runtime, 50));
        file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite8().call(threadContext, rubyObject, rubyObject);
        file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite9().call(threadContext, rubyObject, rubyObject, file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getFixnum2(threadContext.runtime, 75));
        return file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite(10).call(threadContext, rubyObject, rubyObject);
    }
    
    @JRubyMethod(name = "get_programs", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$get_programs(final FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4 file_978F9C4578A02BC05F54056E84B1095E5DF90DF4, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite(11).call(threadContext, self, self, file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getString5(threadContext.runtime, 32));
        file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite(12).callIter(threadContext, self, file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite(13).call(threadContext, self, file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getConstantFrom1(RuntimeHelpers.checkIsModule(file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getConstant0(threadContext, "Utils")), threadContext, "Programs")), RuntimeHelpers.createBlock(threadContext, self, file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getBlockBody0(threadContext, "block_0$RUBY$get_programs,1,program,false,2,./lib//lister/runner/measurements/services.rb,37,true")));
        return file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite(16).call(threadContext, self, file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite(17).call(threadContext, self, self));
    }
    
    public static IRubyObject block_0$RUBY$get_programs(final FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          14
        //    28: invokevirtual   ruby/jit/FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_0        
        //    34: bipush          15
        //    36: invokevirtual   ruby/jit/FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    39: aload_1        
        //    40: aload_2        
        //    41: aload_2        
        //    42: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: aload           program
        //    47: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name     Signature
        //  -----  ------  ----  -------  ---------------------------------------
        //  25     26      9     program  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "get_system_services", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$get_system_services(final FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4 file_978F9C4578A02BC05F54056E84B1095E5DF90DF4, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite(18).call(threadContext, self, self, file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getString6(threadContext.runtime, 32));
        file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite(19).callIter(threadContext, self, file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite(20).call(threadContext, self, file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getConstantFrom3(RuntimeHelpers.checkIsModule(file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getConstant2(threadContext, "Utils")), threadContext, "Services")), RuntimeHelpers.createBlock(threadContext, self, file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getBlockBody1(threadContext, "block_1$RUBY$get_system_services,1,service,false,2,./lib//lister/runner/measurements/services.rb,45,true")));
        return file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite(23).call(threadContext, self, file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite(24).call(threadContext, self, self));
    }
    
    public static IRubyObject block_1$RUBY$get_system_services(final FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          21
        //    28: invokevirtual   ruby/jit/FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_0        
        //    34: bipush          22
        //    36: invokevirtual   ruby/jit/FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    39: aload_1        
        //    40: aload_2        
        //    41: aload_2        
        //    42: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: aload           service
        //    47: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name     Signature
        //  -----  ------  ----  -------  ---------------------------------------
        //  25     26      9     service  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "get_udp_servers", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__10$RUBY$get_udp_servers(final FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4 file_978F9C4578A02BC05F54056E84B1095E5DF90DF4, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite(25).call(threadContext, self, self, file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getString7(threadContext.runtime, 32));
        file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite(26).callIter(threadContext, self, file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getConstantFrom5(RuntimeHelpers.checkIsModule(file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getConstant4(threadContext, "Utils")), threadContext, "Servers"), file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getSymbol0(threadContext.runtime, "udp"), RuntimeHelpers.createBlock(threadContext, self, file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getBlockBody2(threadContext, "block_2$RUBY$get_udp_servers,1,server,false,2,./lib//lister/runner/measurements/services.rb,53,true")));
        return file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite(30).call(threadContext, self, file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite(31).call(threadContext, self, self));
    }
    
    public static IRubyObject block_2$RUBY$get_udp_servers(final FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          27
        //    28: invokevirtual   ruby/jit/FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_0        
        //    34: bipush          28
        //    36: invokevirtual   ruby/jit/FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    39: aload_1        
        //    40: aload_2        
        //    41: aload_2        
        //    42: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: aload_0        
        //    46: bipush          29
        //    48: invokevirtual   ruby/jit/FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    51: aload_1        
        //    52: aload_2        
        //    53: aload           server
        //    55: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    61: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  ---------------------------------------
        //  25     37      9     server  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "get_tcp_servers", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__11$RUBY$get_tcp_servers(final FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4 file_978F9C4578A02BC05F54056E84B1095E5DF90DF4, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite(32).call(threadContext, self, self, file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getString8(threadContext.runtime, 32));
        file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite(33).callIter(threadContext, self, file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getConstantFrom7(RuntimeHelpers.checkIsModule(file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getConstant6(threadContext, "Utils")), threadContext, "Servers"), file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getSymbol1(threadContext.runtime, "tcp"), RuntimeHelpers.createBlock(threadContext, self, file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getBlockBody3(threadContext, "block_3$RUBY$get_tcp_servers,1,server,false,2,./lib//lister/runner/measurements/services.rb,61,true")));
        return file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite(37).call(threadContext, self, file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite(38).call(threadContext, self, self));
    }
    
    public static IRubyObject block_3$RUBY$get_tcp_servers(final FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          34
        //    28: invokevirtual   ruby/jit/FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_0        
        //    34: bipush          35
        //    36: invokevirtual   ruby/jit/FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    39: aload_1        
        //    40: aload_2        
        //    41: aload_2        
        //    42: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: aload_0        
        //    46: bipush          36
        //    48: invokevirtual   ruby/jit/FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    51: aload_1        
        //    52: aload_2        
        //    53: aload           server
        //    55: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    61: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  ---------------------------------------
        //  25     37      9     server  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_2$RUBY$Services(final FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4 file_978F9C4578A02BC05F54056E84B1095E5DF90DF4, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_2$RUBY$Services(file_978F9C4578A02BC05F54056E84B1095E5DF90DF4, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4 file_978F9C4578A02BC05F54056E84B1095E5DF90DF4, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Measurements(file_978F9C4578A02BC05F54056E84B1095E5DF90DF4, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4 file_978F9C4578A02BC05F54056E84B1095E5DF90DF4, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_978F9C4578A02BC05F54056E84B1095E5DF90DF4, threadContext, rubyObject, block);
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
        final FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4 file_978F9C4578A02BC05F54056E84B1095E5DF90DF4 = new FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4();
        final String string = FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4.class.getClassLoader().getResource("ruby/jit/FILE_978F9C4578A02BC05F54056E84B1095E5DF90DF4.class").toString();
        file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_978F9C4578A02BC05F54056E84B1095E5DF90DF4.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
