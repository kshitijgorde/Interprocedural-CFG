// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.runtime.DynamicScope;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.RubyHash;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC extends AbstractScript
{
    public FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC() {
        this.filename = "./lib//lister/runner/measurements/network_scan.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffautoload\uffffF\uffffinclude\uffffF\uffffinclude?\uffffN\uffffstart_with?\uffffN\uffffget_current_computer_infos\uffffV\uffffget_lan_infos\uffffV\uffffcount_neighbours_zeroconf\uffffN\uffffprogress\uffffF\uffffarp_scan\uffffN\uffffarp_neighbours\uffffN\uffffprogress\uffffF\uffffmap\uffffN\uffffmac\uffffN\uffffeach\uffffN\uffff<<\uffffN\uffffmac_vendors\uffffV\uffffjoin\uffffN\uffff[]\uffffN\uffffsplit\uffffN\uffffeach\uffffN\uffff<<\uffffN\uffffhashed_macs\uffffV\uffffhexdigest\uffffN\uffffcounts\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffcounts\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffsize\uffffN\uffffeach\uffffN\uffffnon_device_mac?\uffffF\uffffmac\uffffN\ufffflog\uffffF\uffffmac\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffhexdigest\uffffN\uffffmac\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffhide_ip\uffffF\uffffto_s\uffffN\uffffip\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffjoin\uffffN\uffff[]\uffffN\uffffsplit\uffffN\uffffmac\uffffN\uffff<<\uffffN\ufffflan_nic_info\uffffV\uffff==\uffffN\uffffhide_ip\uffffF\uffffeach\uffffN\uffffget_iface_macaddresses\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffff[]\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffff[]\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffff[]\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffto_i\uffffN\uffff[]\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffmaybe_hide_ip\uffffF\uffff[]\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffmaybe_hide_ip\uffffF\uffff[]\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffjoin\uffffN\uffff[]\uffffN\uffffsplit\uffffN\uffff[]\uffffN\uffff<<\uffffN\uffffcomputer_nic_info\uffffV\uffff\u0003\f\u0002\u0000\u000e\u0000\u0000\u0005\n\u0005\u0000\u0000\u000f\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(7, "00:00:00:00:00:00", this.getEncoding0());
        this.setByteList(3, "lister/utils/net_config", this.getEncoding0());
        this.setByteList(0, "lister/measurement", this.getEncoding0());
        this.setByteList(1, "lister/measurements/network_scan", this.getEncoding0());
        this.setByteList(14, "n/a", this.getEncoding0());
        this.setByteList(11, "-", this.getEncoding0());
        this.setByteList(9, "01:00:5e", this.getEncoding0());
        this.setByteList(4, "lister/utils/hide_ip", this.getEncoding0());
        this.setByteList(2, "lister/utils/scan", this.getEncoding0());
        this.setByteList(12, "skipping mac addr: ", this.getEncoding0());
        this.setByteList(5, "digest/sha1", this.getEncoding0());
        this.setByteList(13, " which is not a real NIC", this.getEncoding0());
        this.setByteList(10, ":", this.getEncoding0());
        this.setByteList(8, "ff:ff:ff:ff:ff:ff", this.getEncoding0());
        this.setByteList(6, "instance-variable", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite0().call(threadContext, rubyObject, rubyObject, file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getString0(threadContext.runtime, 32));
        file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite1().call(threadContext, rubyObject, rubyObject, file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getString1(threadContext.runtime, 32));
        file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite2().call(threadContext, rubyObject, rubyObject, file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getString2(threadContext.runtime, 32));
        file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite3().call(threadContext, rubyObject, rubyObject, file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getString3(threadContext.runtime, 32));
        file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite4().call(threadContext, rubyObject, rubyObject, file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getString4(threadContext.runtime, 32));
        file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite5().call(threadContext, rubyObject, rubyObject, file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getSymbol0(threadContext.runtime, "Digest"), file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getString5(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.module__1$RUBY$Measurements:(Lruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc_w           "Measurement"
        //    34: bipush          13
        //    36: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    42: invokestatic    ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.class_2$RUBY$NetworkScan:(Lruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject class_2$RUBY$NetworkScan(final FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc             "NetworkScan"
        //    19: swap           
        //    20: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    23: dup            
        //    24: astore_2       
        //    25: aload_1        
        //    26: swap           
        //    27: aload_0        
        //    28: aload_1        
        //    29: ldc             ",0,0,-1"
        //    31: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    34: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    37: aload_0        
        //    38: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite6:()Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_2        
        //    44: aload_0        
        //    45: aload_1        
        //    46: ldc             "HideIP"
        //    48: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: pop            
        //    55: aload_1        
        //    56: aload_2        
        //    57: aload_0        
        //    58: ldc             "counts"
        //    60: ldc             "method__3$RUBY$counts"
        //    62: ldc             ",0,0,-1"
        //    64: iconst_0       
        //    65: ldc             "./lib//lister/runner/measurements/network_scan.rb"
        //    67: ldc             12
        //    69: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    72: ldc             "NONE"
        //    74: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: pop            
        //    78: aload_1        
        //    79: aload_2        
        //    80: aload_0        
        //    81: ldc             "mac_vendors"
        //    83: ldc             "method__4$RUBY$mac_vendors"
        //    85: ldc             ",0,0,-1"
        //    87: iconst_0       
        //    88: ldc             "./lib//lister/runner/measurements/network_scan.rb"
        //    90: ldc             16
        //    92: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    95: ldc             "NONE"
        //    97: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   100: pop            
        //   101: aload_1        
        //   102: aload_2        
        //   103: aload_0        
        //   104: ldc_w           "hashed_macs"
        //   107: ldc_w           "method__5$RUBY$hashed_macs"
        //   110: ldc             ",0,0,-1"
        //   112: iconst_0       
        //   113: ldc             "./lib//lister/runner/measurements/network_scan.rb"
        //   115: ldc_w           20
        //   118: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   121: ldc             "NONE"
        //   123: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   126: pop            
        //   127: aload_1        
        //   128: aload_2        
        //   129: aload_0        
        //   130: ldc_w           "lan_nic_info"
        //   133: ldc_w           "method__6$RUBY$lan_nic_info"
        //   136: ldc             ",0,0,-1"
        //   138: iconst_0       
        //   139: ldc             "./lib//lister/runner/measurements/network_scan.rb"
        //   141: ldc_w           24
        //   144: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   147: ldc             "NONE"
        //   149: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   152: pop            
        //   153: aload_1        
        //   154: aload_2        
        //   155: aload_0        
        //   156: ldc_w           "computer_nic_info"
        //   159: ldc_w           "method__7$RUBY$computer_nic_info"
        //   162: ldc             ",0,0,-1"
        //   164: iconst_0       
        //   165: ldc             "./lib//lister/runner/measurements/network_scan.rb"
        //   167: ldc_w           28
        //   170: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   173: ldc             "NONE"
        //   175: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   178: pop            
        //   179: aload_1        
        //   180: aload_2        
        //   181: aload_0        
        //   182: ldc_w           "non_device_mac?"
        //   185: ldc_w           "method__8$RUBY$non_device_mac_p_"
        //   188: ldc_w           "mac,1,0,-1"
        //   191: iconst_1       
        //   192: ldc             "./lib//lister/runner/measurements/network_scan.rb"
        //   194: ldc_w           32
        //   197: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   200: ldc_w           "qmac"
        //   203: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   206: pop            
        //   207: aload_1        
        //   208: aload_2        
        //   209: aload_0        
        //   210: ldc_w           "execute"
        //   213: ldc_w           "method__9$RUBY$execute"
        //   216: ldc             ",0,0,-1"
        //   218: iconst_0       
        //   219: ldc             "./lib//lister/runner/measurements/network_scan.rb"
        //   221: ldc_w           37
        //   224: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   227: ldc             "NONE"
        //   229: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   232: pop            
        //   233: aload_1        
        //   234: aload_2        
        //   235: aload_0        
        //   236: ldc_w           "get_lan_infos"
        //   239: ldc_w           "method__10$RUBY$get_lan_infos"
        //   242: ldc_w           "zconf;entries;mac_addrs,0,0,-1"
        //   245: iconst_0       
        //   246: ldc             "./lib//lister/runner/measurements/network_scan.rb"
        //   248: ldc_w           42
        //   251: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   254: ldc             "NONE"
        //   256: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   259: pop            
        //   260: aload_1        
        //   261: aload_2        
        //   262: aload_0        
        //   263: ldc_w           "maybe_hide_ip"
        //   266: ldc_w           "method__11$RUBY$maybe_hide_ip"
        //   269: ldc_w           "ip,1,0,-1"
        //   272: iconst_1       
        //   273: ldc             "./lib//lister/runner/measurements/network_scan.rb"
        //   275: ldc_w           76
        //   278: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   281: ldc_w           "qip"
        //   284: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   287: pop            
        //   288: aload_1        
        //   289: aload_2        
        //   290: aload_0        
        //   291: ldc_w           "get_current_computer_infos"
        //   294: ldc_w           "method__12$RUBY$get_current_computer_infos"
        //   297: ldc             ",0,0,-1"
        //   299: iconst_0       
        //   300: ldc             "./lib//lister/runner/measurements/network_scan.rb"
        //   302: ldc_w           84
        //   305: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   308: ldc             "NONE"
        //   310: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   313: aload_1        
        //   314: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   317: goto            325
        //   320: aload_1        
        //   321: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   324: athrow         
        //   325: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  37     313    320    325    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "counts", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$counts(final FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@counts") ? file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getByteList6() : null) == null) {
            rubyObject = file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.setVariable0(threadContext.runtime, "@counts", object, RubyHash.newHash(threadContext.runtime));
        }
        else if (!(rubyObject = file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getVariable0(threadContext.runtime, "@counts", object)).isTrue()) {
            rubyObject = file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.setVariable1(threadContext.runtime, "@counts", object, RubyHash.newHash(threadContext.runtime));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "mac_vendors", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$mac_vendors(final FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@mac_vendors") ? file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getByteList6() : null) == null) {
            rubyObject = file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.setVariable2(threadContext.runtime, "@mac_vendors", object, threadContext.runtime.newArray());
        }
        else if (!(rubyObject = file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getVariable1(threadContext.runtime, "@mac_vendors", object)).isTrue()) {
            rubyObject = file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.setVariable3(threadContext.runtime, "@mac_vendors", object, threadContext.runtime.newArray());
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "hashed_macs", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$hashed_macs(final FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@hashed_macs") ? file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getByteList6() : null) == null) {
            rubyObject = file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.setVariable4(threadContext.runtime, "@hashed_macs", object, threadContext.runtime.newArray());
        }
        else if (!(rubyObject = file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getVariable2(threadContext.runtime, "@hashed_macs", object)).isTrue()) {
            rubyObject = file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.setVariable5(threadContext.runtime, "@hashed_macs", object, threadContext.runtime.newArray());
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "lan_nic_info", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$lan_nic_info(final FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@lan_nic_info") ? file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getByteList6() : null) == null) {
            rubyObject = file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.setVariable6(threadContext.runtime, "@lan_nic_info", object, threadContext.runtime.newArray());
        }
        else if (!(rubyObject = file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getVariable3(threadContext.runtime, "@lan_nic_info", object)).isTrue()) {
            rubyObject = file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.setVariable7(threadContext.runtime, "@lan_nic_info", object, threadContext.runtime.newArray());
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "computer_nic_info", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$computer_nic_info(final FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@computer_nic_info") ? file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getByteList6() : null) == null) {
            rubyObject = file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.setVariable8(threadContext.runtime, "@computer_nic_info", object, threadContext.runtime.newArray());
        }
        else if (!(rubyObject = file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getVariable4(threadContext.runtime, "@computer_nic_info", object)).isTrue()) {
            rubyObject = file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.setVariable9(threadContext.runtime, "@computer_nic_info", object, threadContext.runtime.newArray());
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "non_device_mac?", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$non_device_mac_p_(final FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        IRubyObject rubyObject3;
        if (!(rubyObject3 = file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite7().call(threadContext, rubyObject, RuntimeHelpers.constructRubyArray(threadContext.runtime, file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getString7(threadContext.runtime, 32), file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getString8(threadContext.runtime, 32)), rubyObject2)).isTrue()) {
            rubyObject3 = file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite8().call(threadContext, rubyObject, rubyObject2, file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getString9(threadContext.runtime, 32));
        }
        return rubyObject3;
    }
    
    @JRubyMethod(name = "execute", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$execute(final FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite9().call(threadContext, rubyObject, rubyObject);
        return file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite(10).call(threadContext, rubyObject, rubyObject);
    }
    
    @JRubyMethod(name = "get_lan_infos", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__10$RUBY$get_lan_infos(final FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite(11).call(threadContext, rubyObject, file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getConstantFrom2(RuntimeHelpers.checkIsModule(file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getConstant1(threadContext, "Utils")), threadContext, "Scan")));
        file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite(12).call(threadContext, rubyObject, rubyObject, file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getFixnum0(threadContext.runtime, 50));
        file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite(13).call(threadContext, rubyObject, file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getConstantFrom4(RuntimeHelpers.checkIsModule(file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getConstant3(threadContext, "Utils")), threadContext, "Scan"));
        locals.setValueOneDepthZero(file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite(14).call(threadContext, rubyObject, file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getConstantFrom6(RuntimeHelpers.checkIsModule(file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getConstant5(threadContext, "Utils")), threadContext, "Scan")));
        file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite(15).call(threadContext, rubyObject, rubyObject, file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getFixnum1(threadContext.runtime, 75));
        locals.setValueTwoDepthZero(file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite(16).callIter(threadContext, rubyObject, locals.getValueOneDepthZeroOrNil(threadContext.nil), RuntimeHelpers.createBlock(threadContext, rubyObject, file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getBlockBody0(threadContext, "block_0$RUBY$get_lan_infos,1,entry,false,2,./lib//lister/runner/measurements/network_scan.rb,50,true"))));
        file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite(18).callIter(threadContext, rubyObject, locals.getValueTwoDepthZeroOrNil(threadContext.nil), RuntimeHelpers.createBlock(threadContext, rubyObject, file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getBlockBody1(threadContext, "block_1$RUBY$get_lan_infos,1,mac,false,2,./lib//lister/runner/measurements/network_scan.rb,51,false")));
        file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite(24).callIter(threadContext, rubyObject, locals.getValueTwoDepthZeroOrNil(threadContext.nil), RuntimeHelpers.createBlock(threadContext, rubyObject, file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getBlockBody2(threadContext, "block_2$RUBY$get_lan_infos,1,mac,false,2,./lib//lister/runner/measurements/network_scan.rb,55,true")));
        final IRubyObject call = file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite(28).call(threadContext, rubyObject, rubyObject);
        RuntimeHelpers.doAttrAsgn(call, RuntimeHelpers.selectAttrAsgnCallSite(call, rubyObject, file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite(29), file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite(30)), file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getSymbol1(threadContext.runtime, "zeroconf"), locals.getValueZeroDepthZeroOrNil(threadContext.nil), threadContext, rubyObject);
        final IRubyObject call2 = file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite(31).call(threadContext, rubyObject, rubyObject);
        RuntimeHelpers.doAttrAsgn(call2, RuntimeHelpers.selectAttrAsgnCallSite(call2, rubyObject, file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite(32), file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite(33)), file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getSymbol2(threadContext.runtime, "arp"), file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite(34).call(threadContext, rubyObject, locals.getValueTwoDepthZeroOrNil(threadContext.nil)), threadContext, rubyObject);
        return file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite(35).callIter(threadContext, rubyObject, locals.getValueOneDepthZeroOrNil(threadContext.nil), RuntimeHelpers.createBlock(threadContext, rubyObject, file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getBlockBody3(threadContext, "block_3$RUBY$get_lan_infos,1,entry;h,false,2,./lib//lister/runner/measurements/network_scan.rb,63,false")));
    }
    
    public static IRubyObject block_0$RUBY$get_lan_infos(final FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    28: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           entry
        //    35: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    38: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name   Signature
        //  -----  ------  ----  -----  ---------------------------------------
        //  25     14      9     entry  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_1$RUBY$get_lan_infos(final FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: bipush          19
        //    38: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_0        
        //    44: bipush          20
        //    46: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload_2        
        //    52: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: aload_0        
        //    56: bipush          21
        //    58: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    61: aload_1        
        //    62: aload_2        
        //    63: aload_0        
        //    64: bipush          22
        //    66: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    69: aload_1        
        //    70: aload_2        
        //    71: aload_0        
        //    72: bipush          23
        //    74: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    77: aload_1        
        //    78: aload_2        
        //    79: aload           locals
        //    81: aload_1        
        //    82: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    85: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: aload_0        
        //    89: aload_1        
        //    90: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    93: bipush          10
        //    95: bipush          32
        //    97: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   100: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   103: aload_1        
        //   104: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   107: aload_1        
        //   108: aload_1        
        //   109: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   112: invokestatic    org/jruby/RubyFixnum.zero:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   115: aload_1        
        //   116: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   119: invokestatic    org/jruby/RubyFixnum.two:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   122: invokestatic    org/jruby/RubyRange.newInclusiveRange:(Lorg/jruby/Ruby;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyRange;
        //   125: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   128: aload_0        
        //   129: aload_1        
        //   130: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   133: bipush          11
        //   135: bipush          32
        //   137: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   140: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   143: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   146: areturn        
        //   147: pop            
        //   148: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     112     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     147    147    151    Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_2$RUBY$get_lan_infos(final FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          25
        //    28: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_0        
        //    34: bipush          26
        //    36: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    39: aload_1        
        //    40: aload_2        
        //    41: aload_2        
        //    42: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: aload_0        
        //    46: bipush          27
        //    48: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    51: aload_1        
        //    52: aload_2        
        //    53: aload_0        
        //    54: aload_1        
        //    55: ldc             "Digest"
        //    57: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getConstant7:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    63: aload_0        
        //    64: swap           
        //    65: aload_1        
        //    66: ldc_w           "SHA1"
        //    69: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getConstantFrom8:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    72: aload           mac
        //    74: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     56      9     mac   Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_3$RUBY$get_lan_infos(final FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    41: aload_0        
        //    42: bipush          36
        //    44: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    47: aload_1        
        //    48: aload_2        
        //    49: aload_2        
        //    50: aload_0        
        //    51: bipush          37
        //    53: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    56: aload_1        
        //    57: aload_2        
        //    58: aload           locals
        //    60: aload_1        
        //    61: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    64: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    78: ifeq            164
        //    81: aload_0        
        //    82: bipush          38
        //    84: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    87: aload_1        
        //    88: aload_2        
        //    89: aload_2        
        //    90: aload_1        
        //    91: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    94: ldc_w           20
        //    97: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   100: aload_0        
        //   101: aload_1        
        //   102: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   105: bipush          12
        //   107: bipush          32
        //   109: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   112: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   115: aload_0        
        //   116: bipush          39
        //   118: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   121: aload_1        
        //   122: aload_2        
        //   123: aload           locals
        //   125: aload_1        
        //   126: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   129: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   132: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   135: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   140: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   143: aload_0        
        //   144: aload_1        
        //   145: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   148: bipush          13
        //   150: bipush          32
        //   152: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   155: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   158: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   161: goto            532
        //   164: aload           locals
        //   166: aload_1        
        //   167: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   170: invokestatic    org/jruby/RubyHash.newHash:(Lorg/jruby/Ruby;)Lorg/jruby/RubyHash;
        //   173: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   176: pop            
        //   177: aload           locals
        //   179: aload_1        
        //   180: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   183: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   186: dup            
        //   187: aload_2        
        //   188: aload_0        
        //   189: bipush          40
        //   191: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   194: aload_0        
        //   195: bipush          41
        //   197: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   200: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   203: aload_0        
        //   204: aload_1        
        //   205: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   208: ldc_w           "hashed_mac"
        //   211: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   214: aload_0        
        //   215: bipush          42
        //   217: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   220: aload_1        
        //   221: aload_2        
        //   222: aload_0        
        //   223: aload_1        
        //   224: ldc             "Digest"
        //   226: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getConstant9:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   229: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   232: aload_0        
        //   233: swap           
        //   234: aload_1        
        //   235: ldc_w           "SHA1"
        //   238: bipush          10
        //   240: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   243: aload_0        
        //   244: bipush          43
        //   246: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   249: aload_1        
        //   250: aload_2        
        //   251: aload           locals
        //   253: aload_1        
        //   254: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   257: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   260: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   263: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   266: aload_1        
        //   267: aload_2        
        //   268: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   271: pop            
        //   272: aload           locals
        //   274: aload_1        
        //   275: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   278: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   281: dup            
        //   282: aload_2        
        //   283: aload_0        
        //   284: bipush          44
        //   286: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   289: aload_0        
        //   290: bipush          45
        //   292: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   295: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   298: aload_0        
        //   299: aload_1        
        //   300: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   303: ldc_w           "ip"
        //   306: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   309: aload_0        
        //   310: bipush          46
        //   312: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   315: aload_1        
        //   316: aload_2        
        //   317: aload_2        
        //   318: aload_0        
        //   319: bipush          47
        //   321: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   324: aload_1        
        //   325: aload_2        
        //   326: aload_0        
        //   327: bipush          48
        //   329: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   332: aload_1        
        //   333: aload_2        
        //   334: aload           locals
        //   336: aload_1        
        //   337: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   340: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   343: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   346: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   349: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   352: aload_1        
        //   353: aload_2        
        //   354: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   357: pop            
        //   358: aload           locals
        //   360: aload_1        
        //   361: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   364: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   367: dup            
        //   368: aload_2        
        //   369: aload_0        
        //   370: bipush          49
        //   372: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   375: aload_0        
        //   376: bipush          50
        //   378: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   381: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   384: aload_0        
        //   385: aload_1        
        //   386: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   389: ldc_w           "oui"
        //   392: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   395: aload_0        
        //   396: bipush          51
        //   398: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   401: aload_1        
        //   402: aload_2        
        //   403: aload_0        
        //   404: bipush          52
        //   406: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   409: aload_1        
        //   410: aload_2        
        //   411: aload_0        
        //   412: bipush          53
        //   414: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   417: aload_1        
        //   418: aload_2        
        //   419: aload_0        
        //   420: bipush          54
        //   422: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   425: aload_1        
        //   426: aload_2        
        //   427: aload           locals
        //   429: aload_1        
        //   430: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   433: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   436: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   439: aload_0        
        //   440: aload_1        
        //   441: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   444: bipush          10
        //   446: bipush          32
        //   448: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   451: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   454: aload_1        
        //   455: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   458: aload_1        
        //   459: aload_1        
        //   460: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   463: invokestatic    org/jruby/RubyFixnum.zero:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   466: aload_1        
        //   467: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   470: invokestatic    org/jruby/RubyFixnum.two:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   473: invokestatic    org/jruby/RubyRange.newInclusiveRange:(Lorg/jruby/Ruby;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyRange;
        //   476: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   479: aload_0        
        //   480: aload_1        
        //   481: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   484: bipush          11
        //   486: bipush          32
        //   488: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   491: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   494: aload_1        
        //   495: aload_2        
        //   496: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   499: pop            
        //   500: aload_0        
        //   501: bipush          55
        //   503: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   506: aload_1        
        //   507: aload_2        
        //   508: aload_0        
        //   509: bipush          56
        //   511: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   514: aload_1        
        //   515: aload_2        
        //   516: aload_2        
        //   517: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   520: aload           locals
        //   522: aload_1        
        //   523: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   526: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   529: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   532: areturn        
        //   533: pop            
        //   534: goto            41
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  41     492     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  41     533    533    537    Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "maybe_hide_ip", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__11$RUBY$maybe_hide_ip(final FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite(57).call(threadContext, rubyObject, rubyObject2, file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getString(threadContext.runtime, 14, 32)).isTrue() ? file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getString(threadContext.runtime, 14, 32) : file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite(58).call(threadContext, rubyObject, rubyObject, rubyObject2);
    }
    
    @JRubyMethod(name = "get_current_computer_infos", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__12$RUBY$get_current_computer_infos(final FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite(59).callIter(threadContext, self, file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite(60).call(threadContext, self, file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getConstantFrom(RuntimeHelpers.checkIsModule(file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getConstant(threadContext, "Utils", 11)), threadContext, "NetConfig", 12)), RuntimeHelpers.createBlock(threadContext, self, file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getBlockBody4(threadContext, "block_4$RUBY$get_current_computer_infos,1,hash;details,false,2,./lib//lister/runner/measurements/network_scan.rb,85,false")));
    }
    
    public static IRubyObject block_4$RUBY$get_current_computer_infos(final FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    66: bipush          61
        //    68: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    71: aload_0        
        //    72: bipush          62
        //    74: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    77: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //    80: aload_0        
        //    81: aload_1        
        //    82: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    85: ldc_w           "name"
        //    88: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getSymbol6:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    91: aload_0        
        //    92: bipush          63
        //    94: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
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
        //   116: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getSymbol6:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
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
        //   140: bipush          64
        //   142: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   145: aload_0        
        //   146: bipush          65
        //   148: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   151: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   154: aload_0        
        //   155: aload_1        
        //   156: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   159: ldc_w           "status"
        //   162: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getSymbol7:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   165: aload_0        
        //   166: bipush          66
        //   168: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
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
        //   190: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getSymbol7:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
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
        //   214: bipush          67
        //   216: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   219: aload_0        
        //   220: bipush          68
        //   222: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   225: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   228: aload_0        
        //   229: aload_1        
        //   230: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   233: ldc_w           "type"
        //   236: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getSymbol8:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   239: aload_0        
        //   240: bipush          69
        //   242: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
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
        //   264: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getSymbol8:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
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
        //   288: bipush          70
        //   290: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   293: aload_0        
        //   294: bipush          71
        //   296: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   299: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   302: aload_0        
        //   303: aload_1        
        //   304: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   307: ldc_w           "mtu"
        //   310: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getSymbol9:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   313: aload_0        
        //   314: bipush          72
        //   316: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   319: aload_1        
        //   320: aload_2        
        //   321: aload_0        
        //   322: bipush          73
        //   324: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
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
        //   346: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getSymbol9:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
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
        //   373: bipush          74
        //   375: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   378: aload_0        
        //   379: bipush          75
        //   381: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   384: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   387: aload_0        
        //   388: aload_1        
        //   389: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   392: ldc_w           "ip"
        //   395: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   398: aload_0        
        //   399: bipush          76
        //   401: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   404: aload_1        
        //   405: aload_2        
        //   406: aload_2        
        //   407: aload_0        
        //   408: bipush          77
        //   410: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
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
        //   432: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
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
        //   459: bipush          78
        //   461: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   464: aload_0        
        //   465: bipush          79
        //   467: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   470: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   473: aload_0        
        //   474: aload_1        
        //   475: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   478: ldc_w           10
        //   481: ldc_w           "ipv6"
        //   484: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   487: aload_0        
        //   488: bipush          80
        //   490: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   493: aload_1        
        //   494: aload_2        
        //   495: aload_2        
        //   496: aload_0        
        //   497: bipush          81
        //   499: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   502: aload_1        
        //   503: aload_2        
        //   504: aload           locals
        //   506: aload_1        
        //   507: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   510: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   513: aload_0        
        //   514: aload_1        
        //   515: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   518: ldc_w           10
        //   521: ldc_w           "ipv6"
        //   524: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   527: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   530: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   533: aload_1        
        //   534: aload_2        
        //   535: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   538: pop            
        //   539: aload           locals
        //   541: aload_1        
        //   542: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   545: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   548: dup            
        //   549: aload_2        
        //   550: aload_0        
        //   551: bipush          82
        //   553: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   556: aload_0        
        //   557: bipush          83
        //   559: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   562: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   565: aload_0        
        //   566: aload_1        
        //   567: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   570: ldc_w           "oui"
        //   573: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   576: aload_0        
        //   577: bipush          84
        //   579: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   582: aload_1        
        //   583: aload_2        
        //   584: aload_0        
        //   585: bipush          85
        //   587: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   590: aload_1        
        //   591: aload_2        
        //   592: aload_0        
        //   593: bipush          86
        //   595: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   598: aload_1        
        //   599: aload_2        
        //   600: aload_0        
        //   601: bipush          87
        //   603: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   606: aload_1        
        //   607: aload_2        
        //   608: aload           locals
        //   610: aload_1        
        //   611: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   614: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   617: aload_0        
        //   618: aload_1        
        //   619: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   622: ldc_w           11
        //   625: ldc_w           "ether"
        //   628: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   631: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   634: aload_0        
        //   635: aload_1        
        //   636: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   639: bipush          10
        //   641: bipush          32
        //   643: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   646: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   649: aload_1        
        //   650: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   653: aload_1        
        //   654: aload_1        
        //   655: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   658: invokestatic    org/jruby/RubyFixnum.zero:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   661: aload_1        
        //   662: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   665: invokestatic    org/jruby/RubyFixnum.two:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   668: invokestatic    org/jruby/RubyRange.newInclusiveRange:(Lorg/jruby/Ruby;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyRange;
        //   671: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   674: aload_0        
        //   675: aload_1        
        //   676: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   679: bipush          11
        //   681: bipush          32
        //   683: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   686: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   689: aload_1        
        //   690: aload_2        
        //   691: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   694: pop            
        //   695: aload_0        
        //   696: bipush          88
        //   698: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   701: aload_1        
        //   702: aload_2        
        //   703: aload_0        
        //   704: bipush          89
        //   706: invokevirtual   ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   709: aload_1        
        //   710: aload_2        
        //   711: aload_2        
        //   712: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   715: aload           locals
        //   717: aload_1        
        //   718: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   721: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   724: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   727: areturn        
        //   728: pop            
        //   729: goto            41
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  41     687     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  41     728    728    732    Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_2$RUBY$NetworkScan(final FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_2$RUBY$NetworkScan(file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Measurements(file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC, threadContext, rubyObject, block);
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
        final FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC = new FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC();
        final String string = FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.class.getClassLoader().getResource("ruby/jit/FILE_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.class").toString();
        file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_95B968D89BA6CAA7A3FFBE4587264F5ACAC343AC.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
