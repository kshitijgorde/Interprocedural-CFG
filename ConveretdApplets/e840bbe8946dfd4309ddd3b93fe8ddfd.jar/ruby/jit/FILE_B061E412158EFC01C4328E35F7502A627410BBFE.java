// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.runtime.DynamicScope;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_B061E412158EFC01C4328E35F7502A627410BBFE extends AbstractScript
{
    public FILE_B061E412158EFC01C4328E35F7502A627410BBFE() {
        this.filename = "./lib//lister/runner/measurements/wlan_scan.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffautoload\uffffF\uffffsize\uffffN\uffffaps\uffffV\uffffcount\uffffN\uffffkeys\uffffN\uffffgroup_by\uffffN\uffffaps\uffffV\uffffbssid\uffffN\uffffcount\uffffN\uffffkeys\uffffN\uffffgroup_by\uffffN\uffffaps\uffffV\uffffessid\uffffN\uffffmap\uffffN\uffffaps\uffffV\uffffrssi\uffffN\uffffmap\uffffN\uffffaps\uffffV\uffffchannel\uffffN\uffffhexdigest\uffffN\uffffmap\uffffN\uffffaps\uffffV\uffffsha1\uffffF\uffffessid\uffffN\uffffrssi\uffffN\uffffchannel\uffffN\uffffis_a?\uffffN\uffffbssid\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffsha1\uffffF\uffffdowncase\uffffN\uffffbssid\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffcurrent_ap\uffffV\uffffdup\uffffN\uffffcurrent_ap\uffffV\uffffeach\uffffN\uffff[]\uffffN\ufffflog\uffffF\uffff[]=\uffffN\uffff[]=\uffffV\uffffsha1\uffffF\uffff[]\uffffN\uffff[]\uffffN\uffffto_sym\uffffN\uffffto_sym\uffffN\ufffflog\uffffF\uffff[]=\uffffN\uffff[]=\uffffV\uffffsha1\uffffF\uffff[]\uffffN\ufffflog\uffffF\uffffeach\uffffN\uffff[]\uffffN\ufffflog\uffffF\uffff[]=\uffffN\uffff[]=\uffffV\uffffsha1\uffffF\uffffdowncase\uffffN\uffff[]\uffffN\uffff[]\uffffN\uffffto_sym\uffffN\uffffto_sym\uffffN\ufffflog\uffffF\uffff[]=\uffffN\uffff[]=\uffffV\uffffsha1\uffffF\uffffdowncase\uffffN\uffff[]\uffffN\ufffflog\uffffF\uffffattr_reader\uffffF\uffffeach_AP_cmd\uffffN\uffffall_APs\uffffN\uffffcurrent_AP\uffffN\uffffget_current_ap!\uffffF\uffffprogress\uffffF\uffffpopulate_aps!\uffffF\uffff\u0003\u0007\u0001\u0000\n\u0000\u0000\u0000\u0003\u0007\u0000\u0000\r\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(0, "lister/measurements/wlan_scan", this.getEncoding0());
        this.setByteList(3, "unavailable", this.getEncoding0());
        this.setByteList(6, "hashing: ", this.getEncoding0());
        this.setByteList(8, " (symbol)", this.getEncoding0());
        this.setByteList(7, " (string)", this.getEncoding0());
        this.setByteList(12, "bssid", this.getEncoding0());
        this.setByteList(1, "lister/utils/wifiscan", this.getEncoding0());
        this.setByteList(4, "SSID", this.getEncoding0());
        this.setByteList(2, "digest/sha1", this.getEncoding0());
        this.setByteList(9, "no ", this.getEncoding0());
        this.setByteList(5, "ssid", this.getEncoding0());
        this.setByteList(10, " present for hashing", this.getEncoding0());
        this.setByteList(11, "BSSID", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_B061E412158EFC01C4328E35F7502A627410BBFE file_B061E412158EFC01C4328E35F7502A627410BBFE, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite0().call(threadContext, rubyObject, rubyObject, file_B061E412158EFC01C4328E35F7502A627410BBFE.getString0(threadContext.runtime, 32));
        file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite1().call(threadContext, rubyObject, rubyObject, file_B061E412158EFC01C4328E35F7502A627410BBFE.getString1(threadContext.runtime, 32));
        file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite2().call(threadContext, rubyObject, rubyObject, file_B061E412158EFC01C4328E35F7502A627410BBFE.getSymbol0(threadContext.runtime, "Digest"), file_B061E412158EFC01C4328E35F7502A627410BBFE.getString2(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_B061E412158EFC01C4328E35F7502A627410BBFE, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_B061E412158EFC01C4328E35F7502A627410BBFE p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.module__1$RUBY$Measurements:(Lruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_B061E412158EFC01C4328E35F7502A627410BBFE p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc_w           "Measurement"
        //    34: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getConstant9:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    37: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    40: invokestatic    ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.class_2$RUBY$WlanScan:(Lruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject class_2$RUBY$WlanScan(final FILE_B061E412158EFC01C4328E35F7502A627410BBFE p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc             "WlanScan"
        //    19: swap           
        //    20: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    23: dup            
        //    24: astore_2       
        //    25: aload_1        
        //    26: swap           
        //    27: aload_0        
        //    28: aload_1        
        //    29: ldc             ",0,0,-1"
        //    31: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    34: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    37: aload_1        
        //    38: aload_2        
        //    39: aload_0        
        //    40: ldc             "count"
        //    42: ldc             "method__3$RUBY$count"
        //    44: ldc             ",0,0,-1"
        //    46: iconst_0       
        //    47: ldc             "./lib//lister/runner/measurements/wlan_scan.rb"
        //    49: ldc             8
        //    51: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    54: ldc             "NONE"
        //    56: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    59: pop            
        //    60: aload_1        
        //    61: aload_2        
        //    62: aload_0        
        //    63: ldc             "count_bssid"
        //    65: ldc             "method__4$RUBY$count_bssid"
        //    67: ldc             ",0,0,-1"
        //    69: iconst_0       
        //    70: ldc             "./lib//lister/runner/measurements/wlan_scan.rb"
        //    72: ldc             12
        //    74: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    77: ldc             "NONE"
        //    79: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: pop            
        //    83: aload_1        
        //    84: aload_2        
        //    85: aload_0        
        //    86: ldc             "count_essid"
        //    88: ldc             "method__5$RUBY$count_essid"
        //    90: ldc             ",0,0,-1"
        //    92: iconst_0       
        //    93: ldc             "./lib//lister/runner/measurements/wlan_scan.rb"
        //    95: ldc             16
        //    97: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   100: ldc             "NONE"
        //   102: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: pop            
        //   106: aload_1        
        //   107: aload_2        
        //   108: aload_0        
        //   109: ldc             "rssis"
        //   111: ldc             "method__6$RUBY$rssis"
        //   113: ldc             ",0,0,-1"
        //   115: iconst_0       
        //   116: ldc             "./lib//lister/runner/measurements/wlan_scan.rb"
        //   118: ldc             20
        //   120: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   123: ldc             "NONE"
        //   125: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   128: pop            
        //   129: aload_1        
        //   130: aload_2        
        //   131: aload_0        
        //   132: ldc             "channels"
        //   134: ldc             "method__7$RUBY$channels"
        //   136: ldc             ",0,0,-1"
        //   138: iconst_0       
        //   139: ldc             "./lib//lister/runner/measurements/wlan_scan.rb"
        //   141: ldc             24
        //   143: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   146: ldc             "NONE"
        //   148: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   151: pop            
        //   152: aload_1        
        //   153: aload_2        
        //   154: aload_0        
        //   155: ldc_w           "sha1"
        //   158: ldc_w           "method__8$RUBY$sha1"
        //   161: ldc_w           "str,1,0,-1"
        //   164: iconst_1       
        //   165: ldc             "./lib//lister/runner/measurements/wlan_scan.rb"
        //   167: ldc_w           28
        //   170: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   173: ldc_w           "qstr"
        //   176: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   179: pop            
        //   180: aload_1        
        //   181: aload_2        
        //   182: aload_0        
        //   183: ldc_w           "networks"
        //   186: ldc_w           "method__9$RUBY$networks"
        //   189: ldc             ",0,0,-1"
        //   191: iconst_0       
        //   192: ldc             "./lib//lister/runner/measurements/wlan_scan.rb"
        //   194: ldc_w           32
        //   197: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   200: ldc             "NONE"
        //   202: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   205: pop            
        //   206: aload_1        
        //   207: aload_2        
        //   208: aload_0        
        //   209: ldc_w           "current"
        //   212: ldc_w           "method__10$RUBY$current"
        //   215: ldc_w           "h,0,0,-1"
        //   218: iconst_0       
        //   219: ldc             "./lib//lister/runner/measurements/wlan_scan.rb"
        //   221: ldc_w           46
        //   224: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   227: ldc             "NONE"
        //   229: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   232: pop            
        //   233: aload_0        
        //   234: bipush          74
        //   236: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   239: aload_1        
        //   240: aload_2        
        //   241: aload_2        
        //   242: aload_0        
        //   243: aload_1        
        //   244: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   247: ldc_w           "aps"
        //   250: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   253: aload_0        
        //   254: aload_1        
        //   255: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   258: ldc_w           "current_ap"
        //   261: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getSymbol6:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   264: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   267: pop            
        //   268: aload_1        
        //   269: aload_2        
        //   270: aload_0        
        //   271: ldc_w           "populate_aps!"
        //   274: ldc_w           "method__11$RUBY$populate_aps_b_"
        //   277: ldc             ",0,0,-1"
        //   279: iconst_0       
        //   280: ldc             "./lib//lister/runner/measurements/wlan_scan.rb"
        //   282: ldc_w           80
        //   285: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   288: ldc             "NONE"
        //   290: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   293: pop            
        //   294: aload_1        
        //   295: aload_2        
        //   296: aload_0        
        //   297: ldc_w           "get_current_ap!"
        //   300: ldc_w           "method__12$RUBY$get_current_ap_b_"
        //   303: ldc             ",0,0,-1"
        //   305: iconst_0       
        //   306: ldc             "./lib//lister/runner/measurements/wlan_scan.rb"
        //   308: ldc_w           85
        //   311: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   314: ldc             "NONE"
        //   316: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   319: pop            
        //   320: aload_1        
        //   321: aload_2        
        //   322: aload_0        
        //   323: ldc_w           "execute"
        //   326: ldc_w           "method__13$RUBY$execute"
        //   329: ldc             ",0,0,-1"
        //   331: iconst_0       
        //   332: ldc             "./lib//lister/runner/measurements/wlan_scan.rb"
        //   334: ldc_w           89
        //   337: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   340: ldc             "NONE"
        //   342: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   345: aload_1        
        //   346: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   349: goto            357
        //   352: aload_1        
        //   353: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   356: athrow         
        //   357: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  37     345    352    357    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "count", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$count(final FILE_B061E412158EFC01C4328E35F7502A627410BBFE file_B061E412158EFC01C4328E35F7502A627410BBFE, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite3().call(threadContext, rubyObject, file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite4().call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "count_bssid", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$count_bssid(final FILE_B061E412158EFC01C4328E35F7502A627410BBFE file_B061E412158EFC01C4328E35F7502A627410BBFE, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite5().call(threadContext, self, file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite6().call(threadContext, self, file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite7().callIter(threadContext, self, file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite8().call(threadContext, self, self), RuntimeHelpers.createBlock(threadContext, self, file_B061E412158EFC01C4328E35F7502A627410BBFE.getBlockBody0(threadContext, "block_0$RUBY$count_bssid,1,a,false,2,./lib//lister/runner/measurements/wlan_scan.rb,13,true")))));
    }
    
    public static IRubyObject block_0$RUBY$count_bssid(final FILE_B061E412158EFC01C4328E35F7502A627410BBFE p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite9:()Lorg/jruby/runtime/CallSite;
        //    29: aload_1        
        //    30: aload_2        
        //    31: aload           a
        //    33: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    36: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     12      9     a     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "count_essid", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$count_essid(final FILE_B061E412158EFC01C4328E35F7502A627410BBFE file_B061E412158EFC01C4328E35F7502A627410BBFE, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite(10).call(threadContext, self, file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite(11).call(threadContext, self, file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite(12).callIter(threadContext, self, file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite(13).call(threadContext, self, self), RuntimeHelpers.createBlock(threadContext, self, file_B061E412158EFC01C4328E35F7502A627410BBFE.getBlockBody1(threadContext, "block_1$RUBY$count_essid,1,a,false,2,./lib//lister/runner/measurements/wlan_scan.rb,17,true")))));
    }
    
    public static IRubyObject block_1$RUBY$count_essid(final FILE_B061E412158EFC01C4328E35F7502A627410BBFE p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    28: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           a
        //    35: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    38: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     14      9     a     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "rssis", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$rssis(final FILE_B061E412158EFC01C4328E35F7502A627410BBFE file_B061E412158EFC01C4328E35F7502A627410BBFE, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite(15).callIter(threadContext, self, file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite(16).call(threadContext, self, self), RuntimeHelpers.createBlock(threadContext, self, file_B061E412158EFC01C4328E35F7502A627410BBFE.getBlockBody2(threadContext, "block_2$RUBY$rssis,1,a,false,2,./lib//lister/runner/measurements/wlan_scan.rb,21,true")));
    }
    
    public static IRubyObject block_2$RUBY$rssis(final FILE_B061E412158EFC01C4328E35F7502A627410BBFE p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          17
        //    28: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           a
        //    35: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    38: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     14      9     a     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "channels", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$channels(final FILE_B061E412158EFC01C4328E35F7502A627410BBFE file_B061E412158EFC01C4328E35F7502A627410BBFE, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite(18).callIter(threadContext, self, file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite(19).call(threadContext, self, self), RuntimeHelpers.createBlock(threadContext, self, file_B061E412158EFC01C4328E35F7502A627410BBFE.getBlockBody3(threadContext, "block_3$RUBY$channels,1,a,false,2,./lib//lister/runner/measurements/wlan_scan.rb,25,true")));
    }
    
    public static IRubyObject block_3$RUBY$channels(final FILE_B061E412158EFC01C4328E35F7502A627410BBFE p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          20
        //    28: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           a
        //    35: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    38: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     14      9     a     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "sha1", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$sha1(final FILE_B061E412158EFC01C4328E35F7502A627410BBFE file_B061E412158EFC01C4328E35F7502A627410BBFE, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite(21).call(threadContext, rubyObject, file_B061E412158EFC01C4328E35F7502A627410BBFE.getConstantFrom1(RuntimeHelpers.checkIsModule(file_B061E412158EFC01C4328E35F7502A627410BBFE.getConstant0(threadContext, "Digest")), threadContext, "SHA1"), rubyObject2);
    }
    
    @JRubyMethod(name = "networks", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$networks(final FILE_B061E412158EFC01C4328E35F7502A627410BBFE file_B061E412158EFC01C4328E35F7502A627410BBFE, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite(22).callIter(threadContext, self, file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite(23).call(threadContext, self, self), RuntimeHelpers.createBlock(threadContext, self, file_B061E412158EFC01C4328E35F7502A627410BBFE.getBlockBody4(threadContext, "block_4$RUBY$networks,1,a;h,false,2,./lib//lister/runner/measurements/wlan_scan.rb,33,true")));
    }
    
    public static IRubyObject block_4$RUBY$networks(final FILE_B061E412158EFC01C4328E35F7502A627410BBFE p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    28: astore          9
        //    30: pop            
        //    31: aload_1        
        //    32: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    35: aload_0        
        //    36: aload_1        
        //    37: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    40: ldc_w           "essid_sha1"
        //    43: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    46: aload_0        
        //    47: bipush          24
        //    49: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    52: aload_1        
        //    53: aload_2        
        //    54: aload_2        
        //    55: aload_0        
        //    56: bipush          25
        //    58: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    61: aload_1        
        //    62: aload_2        
        //    63: aload           a
        //    65: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    68: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    71: aload_0        
        //    72: aload_1        
        //    73: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    76: ldc_w           "rssi"
        //    79: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    82: aload_0        
        //    83: bipush          26
        //    85: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    88: aload_1        
        //    89: aload_2        
        //    90: aload           a
        //    92: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    95: aload_0        
        //    96: aload_1        
        //    97: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   100: ldc_w           "channel"
        //   103: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   106: aload_0        
        //   107: bipush          27
        //   109: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   112: aload_1        
        //   113: aload_2        
        //   114: aload           a
        //   116: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   119: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructHash:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyHash;
        //   122: astore          h
        //   124: aload_0        
        //   125: bipush          28
        //   127: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   130: aload_1        
        //   131: aload_2        
        //   132: aload_0        
        //   133: bipush          29
        //   135: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   138: aload_1        
        //   139: aload_2        
        //   140: aload           a
        //   142: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   145: aload_0        
        //   146: aload_1        
        //   147: ldc_w           "String"
        //   150: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getConstant2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   153: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   156: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   161: ifeq            239
        //   164: aload           h
        //   166: dup            
        //   167: aload_2        
        //   168: aload_0        
        //   169: bipush          30
        //   171: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   174: aload_0        
        //   175: bipush          31
        //   177: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   180: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   183: aload_0        
        //   184: aload_1        
        //   185: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   188: ldc_w           "bssid_sha1"
        //   191: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   194: aload_0        
        //   195: bipush          32
        //   197: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   200: aload_1        
        //   201: aload_2        
        //   202: aload_2        
        //   203: aload_0        
        //   204: bipush          33
        //   206: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   209: aload_1        
        //   210: aload_2        
        //   211: aload_0        
        //   212: bipush          34
        //   214: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   217: aload_1        
        //   218: aload_2        
        //   219: aload           a
        //   221: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   224: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   227: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   230: aload_1        
        //   231: aload_2        
        //   232: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   235: pop            
        //   236: goto            285
        //   239: aload           h
        //   241: dup            
        //   242: aload_2        
        //   243: aload_0        
        //   244: bipush          35
        //   246: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   249: aload_0        
        //   250: bipush          36
        //   252: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   255: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   258: aload_0        
        //   259: aload_1        
        //   260: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   263: ldc_w           "bssid_sha1"
        //   266: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   269: aload_0        
        //   270: aload_1        
        //   271: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   274: bipush          32
        //   276: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getString3:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   279: aload_1        
        //   280: aload_2        
        //   281: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   284: pop            
        //   285: aload           h
        //   287: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  31     257     9     a     Lorg/jruby/runtime/builtin/IRubyObject;
        //  31     257     10    h     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "current", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__10$RUBY$current(final FILE_B061E412158EFC01C4328E35F7502A627410BBFE file_B061E412158EFC01C4328E35F7502A627410BBFE, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(threadContext.nil);
        if (file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite(37).call(threadContext, rubyObject, rubyObject).isTrue()) {
            locals.setValueZeroDepthZero(file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite(38).call(threadContext, rubyObject, file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite(39).call(threadContext, rubyObject, rubyObject)));
            file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite(40).callIter(threadContext, rubyObject, RuntimeHelpers.constructRubyArray(threadContext.runtime, file_B061E412158EFC01C4328E35F7502A627410BBFE.getString4(threadContext.runtime, 32), file_B061E412158EFC01C4328E35F7502A627410BBFE.getString5(threadContext.runtime, 32)), RuntimeHelpers.createBlock(threadContext, rubyObject, file_B061E412158EFC01C4328E35F7502A627410BBFE.getBlockBody5(threadContext, "block_5$RUBY$current,1,key,false,2,./lib//lister/runner/measurements/wlan_scan.rb,50,false")));
            file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite(56).callIter(threadContext, rubyObject, RuntimeHelpers.constructRubyArray(threadContext.runtime, file_B061E412158EFC01C4328E35F7502A627410BBFE.getString(threadContext.runtime, 11, 32), file_B061E412158EFC01C4328E35F7502A627410BBFE.getString(threadContext.runtime, 12, 32)), RuntimeHelpers.createBlock(threadContext, rubyObject, file_B061E412158EFC01C4328E35F7502A627410BBFE.getBlockBody6(threadContext, "block_6$RUBY$current,1,key,false,2,./lib//lister/runner/measurements/wlan_scan.rb,62,false")));
        }
        return locals.getValueZeroDepthZeroOrNil(threadContext.nil);
    }
    
    public static IRubyObject block_5$RUBY$current(final FILE_B061E412158EFC01C4328E35F7502A627410BBFE p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: bipush          41
        //    38: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload           locals
        //    45: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    48: aload_1        
        //    49: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: aload           locals
        //    57: aload_1        
        //    58: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    61: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    64: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    72: ifeq            230
        //    75: aload_0        
        //    76: bipush          42
        //    78: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    81: aload_1        
        //    82: aload_2        
        //    83: aload_2        
        //    84: aload_1        
        //    85: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    88: ldc             20
        //    90: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    93: aload_0        
        //    94: aload_1        
        //    95: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    98: bipush          32
        //   100: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getString6:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   103: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   106: aload           locals
        //   108: aload_1        
        //   109: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   112: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   115: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   120: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   123: aload_0        
        //   124: aload_1        
        //   125: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   128: bipush          32
        //   130: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   133: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   136: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   139: pop            
        //   140: aload           locals
        //   142: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   145: aload_1        
        //   146: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   149: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   152: dup            
        //   153: aload_2        
        //   154: aload_0        
        //   155: bipush          43
        //   157: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   160: aload_0        
        //   161: bipush          44
        //   163: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   166: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   169: aload           locals
        //   171: aload_1        
        //   172: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   175: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   178: aload_0        
        //   179: bipush          45
        //   181: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   184: aload_1        
        //   185: aload_2        
        //   186: aload_2        
        //   187: aload_0        
        //   188: bipush          46
        //   190: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   193: aload_1        
        //   194: aload_2        
        //   195: aload           locals
        //   197: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   200: aload_1        
        //   201: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   204: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   207: aload           locals
        //   209: aload_1        
        //   210: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   213: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   216: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   219: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   222: aload_1        
        //   223: aload_2        
        //   224: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   227: goto            528
        //   230: aload_0        
        //   231: bipush          47
        //   233: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   236: aload_1        
        //   237: aload_2        
        //   238: aload           locals
        //   240: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   243: aload_1        
        //   244: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   247: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   250: aload_0        
        //   251: bipush          48
        //   253: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   256: aload_1        
        //   257: aload_2        
        //   258: aload           locals
        //   260: aload_1        
        //   261: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   264: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   267: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   270: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   273: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   278: ifeq            462
        //   281: aload           locals
        //   283: aload_0        
        //   284: bipush          49
        //   286: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   289: aload_1        
        //   290: aload_2        
        //   291: aload           locals
        //   293: aload_1        
        //   294: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   297: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   300: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   303: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   306: pop            
        //   307: aload_0        
        //   308: bipush          50
        //   310: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   313: aload_1        
        //   314: aload_2        
        //   315: aload_2        
        //   316: aload_1        
        //   317: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   320: ldc             20
        //   322: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   325: aload_0        
        //   326: aload_1        
        //   327: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   330: bipush          32
        //   332: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getString6:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   335: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   338: aload           locals
        //   340: aload_1        
        //   341: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   344: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   347: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   352: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   355: aload_0        
        //   356: aload_1        
        //   357: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   360: bipush          32
        //   362: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getString8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   365: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   368: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   371: pop            
        //   372: aload           locals
        //   374: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   377: aload_1        
        //   378: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   381: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   384: dup            
        //   385: aload_2        
        //   386: aload_0        
        //   387: bipush          51
        //   389: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   392: aload_0        
        //   393: bipush          52
        //   395: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   398: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   401: aload           locals
        //   403: aload_1        
        //   404: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   407: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   410: aload_0        
        //   411: bipush          53
        //   413: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   416: aload_1        
        //   417: aload_2        
        //   418: aload_2        
        //   419: aload_0        
        //   420: bipush          54
        //   422: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   425: aload_1        
        //   426: aload_2        
        //   427: aload           locals
        //   429: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   432: aload_1        
        //   433: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   436: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   439: aload           locals
        //   441: aload_1        
        //   442: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   445: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   448: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   451: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   454: aload_1        
        //   455: aload_2        
        //   456: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   459: goto            528
        //   462: aload_0        
        //   463: bipush          55
        //   465: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   468: aload_1        
        //   469: aload_2        
        //   470: aload_2        
        //   471: aload_1        
        //   472: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   475: ldc             20
        //   477: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   480: aload_0        
        //   481: aload_1        
        //   482: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   485: bipush          32
        //   487: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getString9:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   490: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   493: aload           locals
        //   495: aload_1        
        //   496: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   499: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   502: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   507: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   510: aload_0        
        //   511: aload_1        
        //   512: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   515: bipush          10
        //   517: bipush          32
        //   519: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   522: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   525: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   528: areturn        
        //   529: pop            
        //   530: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     494     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     529    529    533    Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_6$RUBY$current(final FILE_B061E412158EFC01C4328E35F7502A627410BBFE p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: bipush          57
        //    38: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload           locals
        //    45: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    48: aload_1        
        //    49: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: aload           locals
        //    57: aload_1        
        //    58: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    61: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    64: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    72: ifeq            241
        //    75: aload_0        
        //    76: bipush          58
        //    78: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    81: aload_1        
        //    82: aload_2        
        //    83: aload_2        
        //    84: aload_1        
        //    85: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    88: ldc             20
        //    90: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    93: aload_0        
        //    94: aload_1        
        //    95: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    98: bipush          32
        //   100: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getString6:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   103: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   106: aload           locals
        //   108: aload_1        
        //   109: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   112: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   115: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   120: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   123: aload_0        
        //   124: aload_1        
        //   125: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   128: bipush          32
        //   130: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   133: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   136: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   139: pop            
        //   140: aload           locals
        //   142: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   145: aload_1        
        //   146: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   149: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   152: dup            
        //   153: aload_2        
        //   154: aload_0        
        //   155: bipush          59
        //   157: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   160: aload_0        
        //   161: bipush          60
        //   163: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   166: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   169: aload           locals
        //   171: aload_1        
        //   172: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   175: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   178: aload_0        
        //   179: bipush          61
        //   181: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   184: aload_1        
        //   185: aload_2        
        //   186: aload_2        
        //   187: aload_0        
        //   188: bipush          62
        //   190: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   193: aload_1        
        //   194: aload_2        
        //   195: aload_0        
        //   196: bipush          63
        //   198: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   201: aload_1        
        //   202: aload_2        
        //   203: aload           locals
        //   205: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   208: aload_1        
        //   209: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   212: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   215: aload           locals
        //   217: aload_1        
        //   218: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   221: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   224: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   227: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   230: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   233: aload_1        
        //   234: aload_2        
        //   235: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   238: goto            550
        //   241: aload_0        
        //   242: bipush          64
        //   244: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   247: aload_1        
        //   248: aload_2        
        //   249: aload           locals
        //   251: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   254: aload_1        
        //   255: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   258: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   261: aload_0        
        //   262: bipush          65
        //   264: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   267: aload_1        
        //   268: aload_2        
        //   269: aload           locals
        //   271: aload_1        
        //   272: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   275: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   278: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   281: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   284: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   289: ifeq            484
        //   292: aload           locals
        //   294: aload_0        
        //   295: bipush          66
        //   297: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   300: aload_1        
        //   301: aload_2        
        //   302: aload           locals
        //   304: aload_1        
        //   305: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   308: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   311: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   314: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   317: pop            
        //   318: aload_0        
        //   319: bipush          67
        //   321: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   324: aload_1        
        //   325: aload_2        
        //   326: aload_2        
        //   327: aload_1        
        //   328: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   331: ldc             20
        //   333: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   336: aload_0        
        //   337: aload_1        
        //   338: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   341: bipush          32
        //   343: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getString6:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   346: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   349: aload           locals
        //   351: aload_1        
        //   352: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   355: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   358: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   363: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   366: aload_0        
        //   367: aload_1        
        //   368: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   371: bipush          32
        //   373: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getString8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   376: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   379: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   382: pop            
        //   383: aload           locals
        //   385: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   388: aload_1        
        //   389: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   392: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   395: dup            
        //   396: aload_2        
        //   397: aload_0        
        //   398: bipush          68
        //   400: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   403: aload_0        
        //   404: bipush          69
        //   406: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   409: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   412: aload           locals
        //   414: aload_1        
        //   415: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   418: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   421: aload_0        
        //   422: bipush          70
        //   424: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   427: aload_1        
        //   428: aload_2        
        //   429: aload_2        
        //   430: aload_0        
        //   431: bipush          71
        //   433: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   436: aload_1        
        //   437: aload_2        
        //   438: aload_0        
        //   439: bipush          72
        //   441: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   444: aload_1        
        //   445: aload_2        
        //   446: aload           locals
        //   448: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   451: aload_1        
        //   452: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   455: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   458: aload           locals
        //   460: aload_1        
        //   461: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   464: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   467: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   470: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   473: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   476: aload_1        
        //   477: aload_2        
        //   478: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   481: goto            550
        //   484: aload_0        
        //   485: bipush          73
        //   487: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   490: aload_1        
        //   491: aload_2        
        //   492: aload_2        
        //   493: aload_1        
        //   494: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   497: ldc             20
        //   499: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   502: aload_0        
        //   503: aload_1        
        //   504: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   507: bipush          32
        //   509: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getString9:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   512: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   515: aload           locals
        //   517: aload_1        
        //   518: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   521: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   524: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   529: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   532: aload_0        
        //   533: aload_1        
        //   534: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   537: bipush          10
        //   539: bipush          32
        //   541: invokevirtual   ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   544: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   547: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   550: areturn        
        //   551: pop            
        //   552: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     516     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     551    551    555    Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "populate_aps!", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__11$RUBY$populate_aps_b_(final FILE_B061E412158EFC01C4328E35F7502A627410BBFE file_B061E412158EFC01C4328E35F7502A627410BBFE, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_B061E412158EFC01C4328E35F7502A627410BBFE.setVariable0(threadContext.runtime, "@scan_command", rubyObject, file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite(75).call(threadContext, rubyObject, file_B061E412158EFC01C4328E35F7502A627410BBFE.getConstantFrom4(RuntimeHelpers.checkIsModule(file_B061E412158EFC01C4328E35F7502A627410BBFE.getConstant3(threadContext, "Utils")), threadContext, "WifiScan")));
        return file_B061E412158EFC01C4328E35F7502A627410BBFE.setVariable1(threadContext.runtime, "@aps", rubyObject, file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite(76).call(threadContext, rubyObject, file_B061E412158EFC01C4328E35F7502A627410BBFE.getConstantFrom6(RuntimeHelpers.checkIsModule(file_B061E412158EFC01C4328E35F7502A627410BBFE.getConstant5(threadContext, "Utils")), threadContext, "WifiScan")));
    }
    
    @JRubyMethod(name = "get_current_ap!", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__12$RUBY$get_current_ap_b_(final FILE_B061E412158EFC01C4328E35F7502A627410BBFE file_B061E412158EFC01C4328E35F7502A627410BBFE, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        return file_B061E412158EFC01C4328E35F7502A627410BBFE.setVariable2(threadContext.runtime, "@current_ap", object, file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite(77).call(threadContext, object, file_B061E412158EFC01C4328E35F7502A627410BBFE.getConstantFrom8(RuntimeHelpers.checkIsModule(file_B061E412158EFC01C4328E35F7502A627410BBFE.getConstant7(threadContext, "Utils")), threadContext, "WifiScan")));
    }
    
    @JRubyMethod(name = "execute", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__13$RUBY$execute(final FILE_B061E412158EFC01C4328E35F7502A627410BBFE file_B061E412158EFC01C4328E35F7502A627410BBFE, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite(78).call(threadContext, rubyObject, rubyObject);
        file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite(79).call(threadContext, rubyObject, rubyObject, file_B061E412158EFC01C4328E35F7502A627410BBFE.getFixnum0(threadContext.runtime, 50));
        return file_B061E412158EFC01C4328E35F7502A627410BBFE.getCallSite(80).call(threadContext, rubyObject, rubyObject);
    }
    
    public static IRubyObject class_2$RUBY$WlanScan(final FILE_B061E412158EFC01C4328E35F7502A627410BBFE file_B061E412158EFC01C4328E35F7502A627410BBFE, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_2$RUBY$WlanScan(file_B061E412158EFC01C4328E35F7502A627410BBFE, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_B061E412158EFC01C4328E35F7502A627410BBFE file_B061E412158EFC01C4328E35F7502A627410BBFE, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Measurements(file_B061E412158EFC01C4328E35F7502A627410BBFE, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_B061E412158EFC01C4328E35F7502A627410BBFE file_B061E412158EFC01C4328E35F7502A627410BBFE, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_B061E412158EFC01C4328E35F7502A627410BBFE, threadContext, rubyObject, block);
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
        final FILE_B061E412158EFC01C4328E35F7502A627410BBFE file_B061E412158EFC01C4328E35F7502A627410BBFE = new FILE_B061E412158EFC01C4328E35F7502A627410BBFE();
        final String string = FILE_B061E412158EFC01C4328E35F7502A627410BBFE.class.getClassLoader().getResource("ruby/jit/FILE_B061E412158EFC01C4328E35F7502A627410BBFE.class").toString();
        file_B061E412158EFC01C4328E35F7502A627410BBFE.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_B061E412158EFC01C4328E35F7502A627410BBFE.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
