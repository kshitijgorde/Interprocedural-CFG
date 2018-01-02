// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.runtime.CallSite;
import org.jruby.runtime.DynamicScope;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2 extends AbstractScript
{
    public FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2() {
        this.filename = "./lib//lister/runner/measurements/configuration.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffinclude_class\uffffF\uffffinclude\uffffF\uffffget\uffffN\uffffos\uffffN\ufffftrue_os\uffffN\uffffto_a\uffffN\uffffnetwork_interfaces\uffffN\uffffeach\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffname\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffvirtual?\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffmtu\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffeach\uffffN\uffffto_a\uffffN\uffffinet_addresses\uffffN\uffff<<\uffffN\uffff[]\uffffN\uffffhide_ip\uffffF\uffffhost_address\uffffN\uffff<<\uffffN\uffffinterfaces\uffffV\uffffpopulate_ifaces\uffffV\uffffprogress\uffffF\uffffpopulate_ifaces_details\uffffV\uffffeach\uffffN\uffffget_iface_macaddresses\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffff[]\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffff[]\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffff[]\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffto_i\uffffN\uffff[]\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffhide_ip\uffffF\uffff[]\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffff==\uffffN\uffff[]\uffffN\uffffhide_ip\uffffF\uffff[]\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffjoin\uffffN\uffff[]\uffffN\uffffsplit\uffffN\uffff[]\uffffN\uffff<<\uffffN\uffffinterfaces_details\uffffV\uffff\u0003\n\u0001\u0000\u000b\u0000\u0000\u0002\u0004\u0003\u0000\u0000\t\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(2, "lister/utils/hide_ip", this.getEncoding0());
        this.setByteList(1, "lister/utils/net_config", this.getEncoding0());
        this.setByteList(3, "lister/measurements/configuration", this.getEncoding0());
        this.setByteList(7, ":", this.getEncoding0());
        this.setByteList(0, "lister/utils/platform", this.getEncoding0());
        this.setByteList(6, "n/a", this.getEncoding0());
        this.setByteList(4, "java.net.NetworkInterface", this.getEncoding0());
        this.setByteList(8, "-", this.getEncoding0());
        this.setByteList(5, "instance-variable", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2 file_3BA8725467C1CAF5B13181A749730EACF72E2AC2, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite0().call(threadContext, rubyObject, rubyObject, file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getString0(threadContext.runtime, 32));
        file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite1().call(threadContext, rubyObject, rubyObject, file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getString1(threadContext.runtime, 32));
        file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite2().call(threadContext, rubyObject, rubyObject, file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getString2(threadContext.runtime, 32));
        file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite3().call(threadContext, rubyObject, rubyObject, file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getString3(threadContext.runtime, 32));
        file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite4().call(threadContext, rubyObject, rubyObject, file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getString4(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_3BA8725467C1CAF5B13181A749730EACF72E2AC2, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.module__1$RUBY$Measurements:(Lruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc_w           "Measurement"
        //    34: bipush          10
        //    36: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    42: invokestatic    ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.class_2$RUBY$Configuration:(Lruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: aload_1        
        //    46: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: goto            57
        //    52: aload_1        
        //    53: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    56: athrow         
        //    57: aload_1        
        //    58: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    61: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     49     52     57     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_2$RUBY$Configuration(final FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc             "Configuration"
        //    19: swap           
        //    20: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    23: dup            
        //    24: astore_2       
        //    25: aload_1        
        //    26: swap           
        //    27: aload_0        
        //    28: aload_1        
        //    29: ldc             ",0,0,-1"
        //    31: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    34: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    37: aload_0        
        //    38: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite5:()Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_2        
        //    44: aload_0        
        //    45: aload_1        
        //    46: ldc             "HideIP"
        //    48: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: pop            
        //    55: aload_1        
        //    56: aload_2        
        //    57: aload_0        
        //    58: ldc             "interfaces"
        //    60: ldc             "method__3$RUBY$interfaces"
        //    62: ldc             ",0,0,-1"
        //    64: iconst_0       
        //    65: ldc             "./lib//lister/runner/measurements/configuration.rb"
        //    67: ldc             13
        //    69: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    72: ldc             "NONE"
        //    74: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: pop            
        //    78: aload_1        
        //    79: aload_2        
        //    80: aload_0        
        //    81: ldc             "interfaces_details"
        //    83: ldc             "method__4$RUBY$interfaces_details"
        //    85: ldc             ",0,0,-1"
        //    87: iconst_0       
        //    88: ldc             "./lib//lister/runner/measurements/configuration.rb"
        //    90: ldc             17
        //    92: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    95: ldc             "NONE"
        //    97: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   100: pop            
        //   101: aload_1        
        //   102: aload_2        
        //   103: aload_0        
        //   104: ldc             "net_config"
        //   106: ldc             "method__5$RUBY$net_config"
        //   108: ldc             ",0,0,-1"
        //   110: iconst_0       
        //   111: ldc             "./lib//lister/runner/measurements/configuration.rb"
        //   113: ldc             21
        //   115: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   118: ldc             "NONE"
        //   120: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   123: pop            
        //   124: aload_1        
        //   125: aload_2        
        //   126: aload_0        
        //   127: ldc_w           "java_os"
        //   130: ldc_w           "method__6$RUBY$java_os"
        //   133: ldc             ",0,0,-1"
        //   135: iconst_0       
        //   136: ldc             "./lib//lister/runner/measurements/configuration.rb"
        //   138: ldc_w           25
        //   141: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   144: ldc             "NONE"
        //   146: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   149: pop            
        //   150: aload_1        
        //   151: aload_2        
        //   152: aload_0        
        //   153: ldc_w           "os"
        //   156: ldc_w           "method__7$RUBY$os"
        //   159: ldc             ",0,0,-1"
        //   161: iconst_0       
        //   162: ldc             "./lib//lister/runner/measurements/configuration.rb"
        //   164: ldc_w           29
        //   167: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   170: ldc             "NONE"
        //   172: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   175: pop            
        //   176: aload_1        
        //   177: aload_2        
        //   178: aload_0        
        //   179: ldc_w           "populate_ifaces"
        //   182: ldc_w           "method__8$RUBY$populate_ifaces"
        //   185: ldc_w           "ifaces,0,0,-1"
        //   188: iconst_0       
        //   189: ldc             "./lib//lister/runner/measurements/configuration.rb"
        //   191: ldc_w           33
        //   194: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   197: ldc             "NONE"
        //   199: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   202: pop            
        //   203: aload_1        
        //   204: aload_2        
        //   205: aload_0        
        //   206: ldc_w           "execute"
        //   209: ldc_w           "method__9$RUBY$execute"
        //   212: ldc             ",0,0,-1"
        //   214: iconst_0       
        //   215: ldc             "./lib//lister/runner/measurements/configuration.rb"
        //   217: ldc_w           49
        //   220: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   223: ldc             "NONE"
        //   225: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   228: pop            
        //   229: aload_1        
        //   230: aload_2        
        //   231: aload_0        
        //   232: ldc_w           "populate_ifaces_details"
        //   235: ldc_w           "method__10$RUBY$populate_ifaces_details"
        //   238: ldc             ",0,0,-1"
        //   240: iconst_0       
        //   241: ldc             "./lib//lister/runner/measurements/configuration.rb"
        //   243: ldc_w           55
        //   246: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   249: ldc             "NONE"
        //   251: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   254: aload_1        
        //   255: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   258: goto            266
        //   261: aload_1        
        //   262: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   265: athrow         
        //   266: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  37     254    261    266    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "interfaces", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$interfaces(final FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2 file_3BA8725467C1CAF5B13181A749730EACF72E2AC2, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@interfaces") ? file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getByteList5() : null) == null) {
            rubyObject = file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.setVariable0(threadContext.runtime, "@interfaces", object, threadContext.runtime.newArray());
        }
        else if (!(rubyObject = file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getVariable0(threadContext.runtime, "@interfaces", object)).isTrue()) {
            rubyObject = file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.setVariable1(threadContext.runtime, "@interfaces", object, threadContext.runtime.newArray());
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "interfaces_details", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$interfaces_details(final FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2 file_3BA8725467C1CAF5B13181A749730EACF72E2AC2, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@interfaces_details") ? file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getByteList5() : null) == null) {
            rubyObject = file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.setVariable2(threadContext.runtime, "@interfaces_details", object, threadContext.runtime.newArray());
        }
        else if (!(rubyObject = file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getVariable1(threadContext.runtime, "@interfaces_details", object)).isTrue()) {
            rubyObject = file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.setVariable3(threadContext.runtime, "@interfaces_details", object, threadContext.runtime.newArray());
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "net_config", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$net_config(final FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2 file_3BA8725467C1CAF5B13181A749730EACF72E2AC2, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite6().call(threadContext, rubyObject, file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getConstantFrom2(RuntimeHelpers.checkIsModule(file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getConstant1(threadContext, "Utils")), threadContext, "NetConfig"));
    }
    
    @JRubyMethod(name = "java_os", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$java_os(final FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2 file_3BA8725467C1CAF5B13181A749730EACF72E2AC2, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite7().call(threadContext, rubyObject, file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getConstantFrom4(RuntimeHelpers.checkIsModule(file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getConstant3(threadContext, "Utils")), threadContext, "Platform"));
    }
    
    @JRubyMethod(name = "os", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$os(final FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2 file_3BA8725467C1CAF5B13181A749730EACF72E2AC2, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite8().call(threadContext, rubyObject, file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getConstantFrom6(RuntimeHelpers.checkIsModule(file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getConstant5(threadContext, "Utils")), threadContext, "Platform"));
    }
    
    @JRubyMethod(name = "populate_ifaces", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$populate_ifaces(final FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2 file_3BA8725467C1CAF5B13181A749730EACF72E2AC2, final ThreadContext context, final IRubyObject self, final Block block) {
        final DynamicScope currentScope;
        final DynamicScope locals = currentScope = context.getCurrentScope();
        final CallSite callSite9 = file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite9();
        IRubyObject rubyObject;
        if (!(rubyObject = file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite(10).call(context, self, file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getConstant7(context, "NetworkInterface"))).isTrue()) {
            rubyObject = context.runtime.newArray();
        }
        currentScope.setValueZeroDepthZero(callSite9.call(context, self, rubyObject));
        return file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite(11).callIter(context, self, locals.getValueZeroDepthZeroOrNil(context.nil), RuntimeHelpers.createBlock(context, self, file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getBlockBody1(context, "block_0$RUBY$populate_ifaces,1,iface;hash,false,2,./lib//lister/runner/measurements/configuration.rb,35,false")));
    }
    
    public static IRubyObject block_0$RUBY$populate_ifaces(final FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    33: aload           5
        //    35: swap           
        //    36: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: pop            
        //    40: pop            
        //    41: aload           locals
        //    43: aload_1        
        //    44: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    47: invokestatic    org/jruby/RubyHash.newHash:(Lorg/jruby/Ruby;)Lorg/jruby/RubyHash;
        //    50: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    53: pop            
        //    54: aload           locals
        //    56: aload_1        
        //    57: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: dup            
        //    64: aload_2        
        //    65: aload_0        
        //    66: bipush          12
        //    68: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    71: aload_0        
        //    72: bipush          13
        //    74: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    77: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //    80: aload_0        
        //    81: aload_1        
        //    82: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    85: ldc_w           "name"
        //    88: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    91: aload_0        
        //    92: bipush          14
        //    94: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    97: aload_1        
        //    98: aload_2        
        //    99: aload           locals
        //   101: aload_1        
        //   102: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   108: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   111: aload_1        
        //   112: aload_2        
        //   113: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   116: pop            
        //   117: aload           locals
        //   119: aload_1        
        //   120: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   123: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   126: dup            
        //   127: aload_2        
        //   128: aload_0        
        //   129: bipush          15
        //   131: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   134: aload_0        
        //   135: bipush          16
        //   137: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   140: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   143: aload_0        
        //   144: aload_1        
        //   145: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   148: ldc_w           "virtual"
        //   151: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   154: aload_0        
        //   155: bipush          17
        //   157: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   160: aload_1        
        //   161: aload_2        
        //   162: aload           locals
        //   164: aload_1        
        //   165: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   168: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   171: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   174: aload_1        
        //   175: aload_2        
        //   176: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   179: pop            
        //   180: aload           locals
        //   182: aload_1        
        //   183: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   186: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   189: dup            
        //   190: aload_2        
        //   191: aload_0        
        //   192: bipush          18
        //   194: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   197: aload_0        
        //   198: bipush          19
        //   200: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   203: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   206: aload_0        
        //   207: aload_1        
        //   208: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   211: ldc_w           "mtu"
        //   214: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   217: aload_0        
        //   218: bipush          20
        //   220: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   223: aload_1        
        //   224: aload_2        
        //   225: aload           locals
        //   227: aload_1        
        //   228: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   231: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   234: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   237: aload_1        
        //   238: aload_2        
        //   239: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   242: pop            
        //   243: aload           locals
        //   245: aload_1        
        //   246: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   249: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   252: dup            
        //   253: aload_2        
        //   254: aload_0        
        //   255: bipush          21
        //   257: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   260: aload_0        
        //   261: bipush          22
        //   263: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   266: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   269: aload_0        
        //   270: aload_1        
        //   271: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   274: ldc_w           "addresses"
        //   277: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   280: aload_1        
        //   281: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   284: invokevirtual   org/jruby/Ruby.newArray:()Lorg/jruby/RubyArray;
        //   287: aload_1        
        //   288: aload_2        
        //   289: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   292: pop            
        //   293: aload_0        
        //   294: bipush          23
        //   296: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   299: aload_1        
        //   300: aload_2        
        //   301: aload_0        
        //   302: bipush          24
        //   304: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   307: aload_1        
        //   308: aload_2        
        //   309: aload_0        
        //   310: bipush          25
        //   312: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   315: aload_1        
        //   316: aload_2        
        //   317: aload           locals
        //   319: aload_1        
        //   320: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   323: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   326: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   329: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   332: aload_1        
        //   333: aload_2        
        //   334: aload_0        
        //   335: aload_1        
        //   336: ldc_w           "block_1$RUBY$populate_ifaces,1,addr,false,2,./lib//lister/runner/measurements/configuration.rb,41,false"
        //   339: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   342: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   345: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   348: pop            
        //   349: aload_0        
        //   350: bipush          30
        //   352: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   355: aload_1        
        //   356: aload_2        
        //   357: aload_0        
        //   358: bipush          31
        //   360: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   363: aload_1        
        //   364: aload_2        
        //   365: aload_2        
        //   366: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   369: aload           locals
        //   371: aload_1        
        //   372: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   375: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   378: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   381: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  41     341     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_1$RUBY$populate_ifaces(final FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    16: pop            
        //    17: aload_1        
        //    18: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    21: aload           4
        //    23: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    26: aload_3        
        //    27: aload           5
        //    29: swap           
        //    30: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: pop            
        //    34: pop            
        //    35: aload_0        
        //    36: bipush          26
        //    38: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_0        
        //    44: bipush          27
        //    46: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload           locals
        //    53: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    56: aload_1        
        //    57: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: aload_0        
        //    64: aload_1        
        //    65: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    68: ldc_w           "addresses"
        //    71: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    74: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: aload_0        
        //    78: bipush          28
        //    80: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    83: aload_1        
        //    84: aload_2        
        //    85: aload_2        
        //    86: aload_0        
        //    87: bipush          29
        //    89: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    92: aload_1        
        //    93: aload_2        
        //    94: aload           locals
        //    96: aload_1        
        //    97: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   100: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   103: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   106: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   109: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   112: areturn        
        //   113: pop            
        //   114: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     78      5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     113    113    117    Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "execute", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$execute(final FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2 file_3BA8725467C1CAF5B13181A749730EACF72E2AC2, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite(32).call(threadContext, rubyObject, rubyObject);
        file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite(33).call(threadContext, rubyObject, rubyObject, file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getFixnum0(threadContext.runtime, 50));
        return file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite(34).call(threadContext, rubyObject, rubyObject);
    }
    
    @JRubyMethod(name = "populate_ifaces_details", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__10$RUBY$populate_ifaces_details(final FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2 file_3BA8725467C1CAF5B13181A749730EACF72E2AC2, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite(35).callIter(threadContext, self, file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite(36).call(threadContext, self, file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getConstantFrom9(RuntimeHelpers.checkIsModule(file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getConstant8(threadContext, "Utils")), threadContext, "NetConfig")), RuntimeHelpers.createBlock(threadContext, self, file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getBlockBody2(threadContext, "block_2$RUBY$populate_ifaces_details,1,hash;details,false,2,./lib//lister/runner/measurements/configuration.rb,56,false")));
    }
    
    public static IRubyObject block_2$RUBY$populate_ifaces_details(final FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    33: aload           5
        //    35: swap           
        //    36: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: pop            
        //    40: pop            
        //    41: aload           locals
        //    43: aload_1        
        //    44: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    47: invokestatic    org/jruby/RubyHash.newHash:(Lorg/jruby/Ruby;)Lorg/jruby/RubyHash;
        //    50: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    53: pop            
        //    54: aload           locals
        //    56: aload_1        
        //    57: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: dup            
        //    64: aload_2        
        //    65: aload_0        
        //    66: bipush          37
        //    68: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    71: aload_0        
        //    72: bipush          38
        //    74: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    77: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //    80: aload_0        
        //    81: aload_1        
        //    82: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    85: ldc_w           "name"
        //    88: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    91: aload_0        
        //    92: bipush          39
        //    94: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    97: aload_1        
        //    98: aload_2        
        //    99: aload           locals
        //   101: aload_1        
        //   102: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   108: aload_0        
        //   109: aload_1        
        //   110: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   113: ldc_w           "name"
        //   116: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   119: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   122: aload_1        
        //   123: aload_2        
        //   124: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   127: pop            
        //   128: aload           locals
        //   130: aload_1        
        //   131: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   134: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   137: dup            
        //   138: aload_2        
        //   139: aload_0        
        //   140: bipush          40
        //   142: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   145: aload_0        
        //   146: bipush          41
        //   148: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   151: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   154: aload_0        
        //   155: aload_1        
        //   156: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   159: ldc_w           "status"
        //   162: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   165: aload_0        
        //   166: bipush          42
        //   168: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   171: aload_1        
        //   172: aload_2        
        //   173: aload           locals
        //   175: aload_1        
        //   176: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   179: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   182: aload_0        
        //   183: aload_1        
        //   184: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   187: ldc_w           "status"
        //   190: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   193: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   196: aload_1        
        //   197: aload_2        
        //   198: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   201: pop            
        //   202: aload           locals
        //   204: aload_1        
        //   205: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   208: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   211: dup            
        //   212: aload_2        
        //   213: aload_0        
        //   214: bipush          43
        //   216: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   219: aload_0        
        //   220: bipush          44
        //   222: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   225: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   228: aload_0        
        //   229: aload_1        
        //   230: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   233: ldc_w           "type"
        //   236: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   239: aload_0        
        //   240: bipush          45
        //   242: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   245: aload_1        
        //   246: aload_2        
        //   247: aload           locals
        //   249: aload_1        
        //   250: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   253: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   256: aload_0        
        //   257: aload_1        
        //   258: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   261: ldc_w           "type"
        //   264: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   267: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   270: aload_1        
        //   271: aload_2        
        //   272: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   275: pop            
        //   276: aload           locals
        //   278: aload_1        
        //   279: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   282: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   285: dup            
        //   286: aload_2        
        //   287: aload_0        
        //   288: bipush          46
        //   290: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   293: aload_0        
        //   294: bipush          47
        //   296: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   299: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   302: aload_0        
        //   303: aload_1        
        //   304: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   307: ldc_w           "mtu"
        //   310: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   313: aload_0        
        //   314: bipush          48
        //   316: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   319: aload_1        
        //   320: aload_2        
        //   321: aload_0        
        //   322: bipush          49
        //   324: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   327: aload_1        
        //   328: aload_2        
        //   329: aload           locals
        //   331: aload_1        
        //   332: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   335: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   338: aload_0        
        //   339: aload_1        
        //   340: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   343: ldc_w           "mtu"
        //   346: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   349: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   352: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   355: aload_1        
        //   356: aload_2        
        //   357: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   360: pop            
        //   361: aload           locals
        //   363: aload_1        
        //   364: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   367: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   370: dup            
        //   371: aload_2        
        //   372: aload_0        
        //   373: bipush          50
        //   375: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   378: aload_0        
        //   379: bipush          51
        //   381: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   384: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   387: aload_0        
        //   388: aload_1        
        //   389: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   392: ldc_w           "ip"
        //   395: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getSymbol6:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   398: aload_0        
        //   399: bipush          52
        //   401: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   404: aload_1        
        //   405: aload_2        
        //   406: aload_2        
        //   407: aload_0        
        //   408: bipush          53
        //   410: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   413: aload_1        
        //   414: aload_2        
        //   415: aload           locals
        //   417: aload_1        
        //   418: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   421: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   424: aload_0        
        //   425: aload_1        
        //   426: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   429: ldc_w           "ip"
        //   432: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getSymbol6:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   435: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   438: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   441: aload_1        
        //   442: aload_2        
        //   443: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   446: pop            
        //   447: aload           locals
        //   449: aload_1        
        //   450: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   453: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   456: dup            
        //   457: aload_2        
        //   458: aload_0        
        //   459: bipush          54
        //   461: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   464: aload_0        
        //   465: bipush          55
        //   467: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   470: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   473: aload_0        
        //   474: aload_1        
        //   475: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   478: ldc_w           "ipv6"
        //   481: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getSymbol7:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   484: aload_0        
        //   485: bipush          56
        //   487: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   490: aload_1        
        //   491: aload_2        
        //   492: aload_0        
        //   493: bipush          57
        //   495: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   498: aload_1        
        //   499: aload_2        
        //   500: aload           locals
        //   502: aload_1        
        //   503: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   506: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   509: aload_0        
        //   510: aload_1        
        //   511: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   514: ldc_w           "ipv6"
        //   517: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getSymbol7:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   520: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   523: aload_0        
        //   524: aload_1        
        //   525: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   528: bipush          32
        //   530: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getString6:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   533: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   536: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   541: ifeq            557
        //   544: aload_0        
        //   545: aload_1        
        //   546: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   549: bipush          32
        //   551: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getString6:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   554: goto            600
        //   557: aload_0        
        //   558: bipush          58
        //   560: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   563: aload_1        
        //   564: aload_2        
        //   565: aload_2        
        //   566: aload_0        
        //   567: bipush          59
        //   569: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   572: aload_1        
        //   573: aload_2        
        //   574: aload           locals
        //   576: aload_1        
        //   577: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   580: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   583: aload_0        
        //   584: aload_1        
        //   585: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   588: ldc_w           "ipv6"
        //   591: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getSymbol7:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   594: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   597: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   600: aload_1        
        //   601: aload_2        
        //   602: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   605: pop            
        //   606: aload           locals
        //   608: aload_1        
        //   609: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   612: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   615: dup            
        //   616: aload_2        
        //   617: aload_0        
        //   618: bipush          60
        //   620: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   623: aload_0        
        //   624: bipush          61
        //   626: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   629: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   632: aload_0        
        //   633: aload_1        
        //   634: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   637: ldc_w           "oui"
        //   640: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getSymbol8:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   643: aload_0        
        //   644: bipush          62
        //   646: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   649: aload_1        
        //   650: aload_2        
        //   651: aload_0        
        //   652: bipush          63
        //   654: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   657: aload_1        
        //   658: aload_2        
        //   659: aload_0        
        //   660: bipush          64
        //   662: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   665: aload_1        
        //   666: aload_2        
        //   667: aload_0        
        //   668: bipush          65
        //   670: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   673: aload_1        
        //   674: aload_2        
        //   675: aload           locals
        //   677: aload_1        
        //   678: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   681: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   684: aload_0        
        //   685: aload_1        
        //   686: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   689: ldc_w           "ether"
        //   692: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getSymbol9:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   695: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   698: aload_0        
        //   699: aload_1        
        //   700: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   703: bipush          32
        //   705: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   708: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   711: aload_1        
        //   712: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   715: aload_1        
        //   716: aload_1        
        //   717: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   720: invokestatic    org/jruby/RubyFixnum.zero:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   723: aload_1        
        //   724: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   727: invokestatic    org/jruby/RubyFixnum.two:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   730: invokestatic    org/jruby/RubyRange.newInclusiveRange:(Lorg/jruby/Ruby;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyRange;
        //   733: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   736: aload_0        
        //   737: aload_1        
        //   738: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   741: bipush          32
        //   743: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getString8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   746: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   749: aload_1        
        //   750: aload_2        
        //   751: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   754: pop            
        //   755: aload_0        
        //   756: bipush          66
        //   758: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   761: aload_1        
        //   762: aload_2        
        //   763: aload_0        
        //   764: bipush          67
        //   766: invokevirtual   ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   769: aload_1        
        //   770: aload_2        
        //   771: aload_2        
        //   772: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   775: aload           locals
        //   777: aload_1        
        //   778: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   781: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   784: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   787: areturn        
        //   788: pop            
        //   789: goto            41
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  41     747     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  41     788    788    792    Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_2$RUBY$Configuration(final FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2 file_3BA8725467C1CAF5B13181A749730EACF72E2AC2, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_2$RUBY$Configuration(file_3BA8725467C1CAF5B13181A749730EACF72E2AC2, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2 file_3BA8725467C1CAF5B13181A749730EACF72E2AC2, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Measurements(file_3BA8725467C1CAF5B13181A749730EACF72E2AC2, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2 file_3BA8725467C1CAF5B13181A749730EACF72E2AC2, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_3BA8725467C1CAF5B13181A749730EACF72E2AC2, threadContext, rubyObject, block);
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
        final FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2 file_3BA8725467C1CAF5B13181A749730EACF72E2AC2 = new FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2();
        final String string = FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.class.getClassLoader().getResource("ruby/jit/FILE_3BA8725467C1CAF5B13181A749730EACF72E2AC2.class").toString();
        file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_3BA8725467C1CAF5B13181A749730EACF72E2AC2.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
