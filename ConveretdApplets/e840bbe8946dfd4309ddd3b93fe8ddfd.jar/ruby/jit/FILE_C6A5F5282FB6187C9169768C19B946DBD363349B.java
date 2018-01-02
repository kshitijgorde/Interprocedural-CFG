// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.RubyBoolean;
import org.jruby.Ruby;
import org.jruby.RubyString;
import org.jruby.RubyArray;
import org.jruby.RubyFixnum;
import org.jruby.RubyHash;
import org.jruby.exceptions.JumpException;
import org.jruby.runtime.DynamicScope;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_C6A5F5282FB6187C9169768C19B946DBD363349B extends AbstractScript
{
    public FILE_C6A5F5282FB6187C9169768C19B946DBD363349B() {
        this.filename = "./lib//lister/runner/measurements/upnp.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffattr_accessor\uffffF\ufffffind\uffffN\uffffservices\uffffN\uffff==\uffffN\uffffservice_type\uffffN\ufffflog\uffffF\uffffdiscover\uffffN\ufffffind\uffffN\uffffdevice_is_gw?\uffffF\uffffeach\uffffN\ufffffind\uffffN\uffffget_top_level_child_devices\uffffN\uffffdevice_is_gw?\uffffF\uffffSocketException\uffffN\uffffnet\uffffN\uffffjava\uffffV\ufffflog\uffffF\ufffflog\uffffF\uffffjoin\uffffN\uffffget_service\uffffN\uffffgateway\uffffV\uffffget_new_instance\uffffN\uffffget_message\uffffN\uffffservice\uffffN\uffffmap\uffffN\uffffget_out_action_argument_value\uffffN\uffffmap\uffffN\uffffeach\uffffN\ufffflog\uffffF\uffffget_remote_stats\uffffF\ufffflog\uffffF\uffffinspect\uffffN\ufffffirst\uffffN\uffff==\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffto_i\uffffN\ufffflog\uffffF\ufffflog\uffffF\uffffget_remote_stats\uffffF\uffff+\uffffN\uffff+\uffffN\ufffffirst\uffffN\ufffflog\uffffF\uffffget_remote_stats\uffffF\ufffffirst\uffffN\uffffsync_rates\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffff[]\uffffN\uffffsync_rates\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffff[]\uffffN\ufffflog\uffffF\ufffflog\uffffF\uffffeach\uffffN\uffffsub\uffffN\uffffgateway_model\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffsend\uffffN\uffffgateway\uffffV\ufffflog\uffffF\ufffftimes\uffffN\ufffflog\uffffF\uffffgetaddress\uffffN\uffffnew\uffffN\ufffflog\uffffF\ufffflog\uffffF\uffff[]=\uffffN\uffff[]=\uffffV\uffffget_upnp_stats\uffffV\ufffflog\uffffF\uffff[]=\uffffN\uffff[]=\uffffV\uffffnow\uffffN\uffffprogress\uffffF\ufffflog\uffffF\uffffinject_some_traffic\uffffN\uffffprogress\uffffF\ufffflog\uffffF\uffff[]=\uffffN\uffff[]=\uffffV\uffffnow\uffffN\ufffflog\uffffF\uffff[]=\uffffN\uffff[]=\uffffV\uffffget_upnp_stats\uffffV\uffffeach\uffffN\uffffeach\uffffN\uffffeach\uffffN\uffffto_sym\uffffN\uffffboth_available_traffic_value?\uffffF\uffff[]\uffffN\uffff[]\uffffN\uffff[]\uffffN\uffff[]\uffffN\uffffcounter_accuracy\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffff-\uffffN\uffff[]\uffffN\uffff[]\uffffN\uffff[]\uffffN\uffff[]\uffffN\uffffcounter_accuracy\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffff==\uffffN\uffff==\uffffN\uffffdiscover_gw\uffffV\uffffprogress\uffffF\uffffgateway\uffffV\uffffget_model\uffffV\uffffget_link_infos\uffffV\uffffget_isp\uffffV\uffffprogress\uffffF\ufffftest_upnp_counters\uffffV\ufffflog\uffffF\uffff\u0003\u000f\u0005\u0000\u0011\u0001\u0000\u0005\f\f\u0000\u0000D\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(53, "", this.getEncoding0());
        this.setByteList(23, "NewTotalPacketsReceived", this.getEncoding0());
        this.setByteList(58, "could not resolv '", this.getEncoding0());
        this.setByteList(46, "get_model_description", this.getEncoding0());
        this.setByteList(51, "get_serial_number", this.getEncoding0());
        this.setByteList(56, "cmon.lip6.fr", this.getEncoding0());
        this.setByteList(64, "T1: Getting UPnP stats", this.getEncoding0());
        this.setByteList(13, ", reading ", this.getEncoding0());
        this.setByteList(37, "NewWANAccessType", this.getEncoding0());
        this.setByteList(24, "Getting: ", this.getEncoding0());
        this.setByteList(17, "NewTotalBytesSent", this.getEncoding0());
        this.setByteList(45, "get_manufacturer_url", this.getEncoding0());
        this.setByteList(33, "unavailable", this.getEncoding0());
        this.setByteList(14, " ", this.getEncoding0());
        this.setByteList(35, "Getting link information", this.getEncoding0());
        this.setByteList(41, "Gettting gateway and connection type informations", this.getEncoding0());
        this.setByteList(9, "urn:schemas-upnp-org:service:WANCommonInterfaceConfig:1", this.getEncoding0());
        this.setByteList(2, "resolv", this.getEncoding0());
        this.setByteList(50, "get_presentation_url", this.getEncoding0());
        this.setByteList(61, "T0: Getting local stats", this.getEncoding0());
        this.setByteList(49, "get_model_url", this.getEncoding0());
        this.setByteList(67, "no gateway found", this.getEncoding0());
        this.setByteList(11, "no Socket to run UPnP test", this.getEncoding0());
        this.setByteList(36, "GetCommonLinkProperties", this.getEncoding0());
        this.setByteList(28, "didn't got stats", this.getEncoding0());
        this.setByteList(27, "got ", this.getEncoding0());
        this.setByteList(43, "get_friendly_name", this.getEncoding0());
        this.setByteList(38, "NewLayer1UpstreamMaxBitRate", this.getEncoding0());
        this.setByteList(8, "net.sbbi.upnp.messages.UPNPResponseException", this.getEncoding0());
        this.setByteList(16, "GetTotalBytesSent", this.getEncoding0());
        this.setByteList(22, "GetTotalPacketsReceived", this.getEncoding0());
        this.setByteList(30, "WANAccessProvider", this.getEncoding0());
        this.setByteList(65, "delta_", this.getEncoding0());
        this.setByteList(59, "', retrying", this.getEncoding0());
        this.setByteList(39, "NewLayer1DownstreamMaxBitRate", this.getEncoding0());
        this.setByteList(63, "T1: Getting local stats", this.getEncoding0());
        this.setByteList(25, " / ", this.getEncoding0());
        this.setByteList(66, "_", this.getEncoding0());
        this.setByteList(26, " for ", this.getEncoding0());
        this.setByteList(0, "lister/measurements/upnp", this.getEncoding0());
        this.setByteList(54, "Testing UPnP counters", this.getEncoding0());
        this.setByteList(18, "GetTotalPacketsSent", this.getEncoding0());
        this.setByteList(47, "get_model_name", this.getEncoding0());
        this.setByteList(7, "net.sbbi.upnp.messages.UPNPMessageFactory", this.getEncoding0());
        this.setByteList(48, "get_model_number", this.getEncoding0());
        this.setByteList(55, "132.227.126.1", this.getEncoding0());
        this.setByteList(42, "get_device_type", this.getEncoding0());
        this.setByteList(20, "GetTotalBytesReceived", this.getEncoding0());
        this.setByteList(57, "resolving ", this.getEncoding0());
        this.setByteList(52, "^get_", this.getEncoding0());
        this.setByteList(19, "NewTotalPacketsSent", this.getEncoding0());
        this.setByteList(4, "net.sbbi.upnp.devices.UPNPDevice", this.getEncoding0());
        this.setByteList(32, "New", this.getEncoding0());
        this.setByteList(44, "get_manufacturer", this.getEncoding0());
        this.setByteList(60, "T0: Getting UPnP stats", this.getEncoding0());
        this.setByteList(15, "n/a", this.getEncoding0());
        this.setByteList(5, "net.sbbi.upnp.messages.ActionMessage", this.getEncoding0());
        this.setByteList(1, "lister/utils/traffic", this.getEncoding0());
        this.setByteList(12, "Getting remote stats: ", this.getEncoding0());
        this.setByteList(40, "did not get an answer", this.getEncoding0());
        this.setByteList(10, "Looking for a UPnP gateway", this.getEncoding0());
        this.setByteList(3, "net.sbbi.upnp.Discovery", this.getEncoding0());
        this.setByteList(62, "Injecting some traffic with ping to ", this.getEncoding0());
        this.setByteList(31, "Get", this.getEncoding0());
        this.setByteList(21, "NewTotalBytesReceived", this.getEncoding0());
        this.setByteList(6, "net.sbbi.upnp.messages.ActionResponse", this.getEncoding0());
        this.setByteList(29, "Getting ISP information if there", this.getEncoding0());
        this.setByteList(34, "instance-variable", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B file_C6A5F5282FB6187C9169768C19B946DBD363349B, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite0().call(threadContext, rubyObject, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString0(threadContext.runtime, 32));
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite1().call(threadContext, rubyObject, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString1(threadContext.runtime, 32));
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite2().call(threadContext, rubyObject, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString2(threadContext.runtime, 32));
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite3().call(threadContext, rubyObject, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString3(threadContext.runtime, 32));
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite4().call(threadContext, rubyObject, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString4(threadContext.runtime, 32));
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite5().call(threadContext, rubyObject, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString5(threadContext.runtime, 32));
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite6().call(threadContext, rubyObject, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString6(threadContext.runtime, 32));
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite7().call(threadContext, rubyObject, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString7(threadContext.runtime, 32));
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite8().call(threadContext, rubyObject, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString8(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_C6A5F5282FB6187C9169768C19B946DBD363349B, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.module__1$RUBY$Measurements:(Lruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc_w           "Measurement"
        //    34: bipush          16
        //    36: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    42: invokestatic    ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.class_2$RUBY$UPnP:(Lruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject class_2$RUBY$UPnP(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc             "UPnP"
        //    19: swap           
        //    20: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    23: dup            
        //    24: astore_2       
        //    25: aload_1        
        //    26: swap           
        //    27: aload_0        
        //    28: aload_1        
        //    29: ldc             ",0,0,-1"
        //    31: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    34: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    37: aload_0        
        //    38: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite9:()Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_2        
        //    44: aload_0        
        //    45: aload_1        
        //    46: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    49: ldc             "gateway"
        //    51: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    54: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: pop            
        //    58: aload_0        
        //    59: aload_1        
        //    60: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    63: bipush          32
        //    65: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getString9:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    68: aload_1        
        //    69: ldc             "WANIC1"
        //    71: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: pop            
        //    75: aload_1        
        //    76: aload_2        
        //    77: aload_0        
        //    78: ldc             "device_is_gw?"
        //    80: ldc             "method__3$RUBY$device_is_gw_p_"
        //    82: ldc             "device,1,0,-1"
        //    84: iconst_1       
        //    85: ldc             "./lib//lister/runner/measurements/upnp.rb"
        //    87: ldc             23
        //    89: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    92: ldc             "qdevice"
        //    94: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    97: pop            
        //    98: aload_1        
        //    99: aload_2        
        //   100: aload_0        
        //   101: ldc_w           "discover_gw"
        //   104: ldc_w           "method__4$RUBY$discover_gw"
        //   107: ldc_w           "devices,0,0,-1"
        //   110: iconst_0       
        //   111: ldc             "./lib//lister/runner/measurements/upnp.rb"
        //   113: ldc_w           27
        //   116: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   119: ldc_w           "NONE"
        //   122: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   125: pop            
        //   126: aload_1        
        //   127: aload_2        
        //   128: aload_0        
        //   129: ldc_w           "get_remote_stats"
        //   132: ldc_w           "method__7$RUBY$get_remote_stats"
        //   135: ldc_w           "req;rsps;ret;wanic1;factory;action;resp,1,0,1"
        //   138: bipush          -2
        //   140: ldc             "./lib//lister/runner/measurements/upnp.rb"
        //   142: ldc_w           48
        //   145: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   148: ldc_w           "qreq;rrsps"
        //   151: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   154: pop            
        //   155: aload_1        
        //   156: aload_2        
        //   157: aload_0        
        //   158: ldc_w           "get_upnp_stats"
        //   161: ldc_w           "method__10$RUBY$get_upnp_stats"
        //   164: ldc_w           "h;reqs,0,0,-1"
        //   167: iconst_0       
        //   168: ldc             "./lib//lister/runner/measurements/upnp.rb"
        //   170: ldc_w           67
        //   173: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   176: ldc_w           "NONE"
        //   179: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   182: pop            
        //   183: aload_1        
        //   184: aload_2        
        //   185: aload_0        
        //   186: ldc_w           "get_isp"
        //   189: ldc_w           "method__11$RUBY$get_isp"
        //   192: ldc_w           "at;query_ret,0,0,-1"
        //   195: iconst_0       
        //   196: ldc             "./lib//lister/runner/measurements/upnp.rb"
        //   198: ldc_w           91
        //   201: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   204: ldc_w           "NONE"
        //   207: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   210: pop            
        //   211: aload_1        
        //   212: aload_2        
        //   213: aload_0        
        //   214: ldc_w           "isp"
        //   217: ldc_w           "method__12$RUBY$isp"
        //   220: ldc             ",0,0,-1"
        //   222: iconst_0       
        //   223: ldc             "./lib//lister/runner/measurements/upnp.rb"
        //   225: ldc_w           98
        //   228: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   231: ldc_w           "NONE"
        //   234: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   237: pop            
        //   238: aload_1        
        //   239: aload_2        
        //   240: aload_0        
        //   241: ldc_w           "sync_rates"
        //   244: ldc_w           "method__13$RUBY$sync_rates"
        //   247: ldc             ",0,0,-1"
        //   249: iconst_0       
        //   250: ldc             "./lib//lister/runner/measurements/upnp.rb"
        //   252: ldc_w           102
        //   255: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   258: ldc_w           "NONE"
        //   261: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   264: pop            
        //   265: aload_1        
        //   266: aload_2        
        //   267: aload_0        
        //   268: ldc_w           "get_link_infos"
        //   271: ldc_w           "method__14$RUBY$get_link_infos"
        //   274: ldc_w           "rep,0,0,-1"
        //   277: iconst_0       
        //   278: ldc             "./lib//lister/runner/measurements/upnp.rb"
        //   280: ldc_w           106
        //   283: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   286: ldc_w           "NONE"
        //   289: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   292: pop            
        //   293: aload_1        
        //   294: aload_2        
        //   295: aload_0        
        //   296: ldc_w           "gateway_model"
        //   299: ldc_w           "method__15$RUBY$gateway_model"
        //   302: ldc             ",0,0,-1"
        //   304: iconst_0       
        //   305: ldc             "./lib//lister/runner/measurements/upnp.rb"
        //   307: ldc_w           121
        //   310: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   313: ldc_w           "NONE"
        //   316: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   319: pop            
        //   320: aload_1        
        //   321: aload_2        
        //   322: aload_0        
        //   323: ldc_w           "get_model"
        //   326: ldc_w           "method__16$RUBY$get_model"
        //   329: ldc_w           "strs,0,0,-1"
        //   332: iconst_0       
        //   333: ldc             "./lib//lister/runner/measurements/upnp.rb"
        //   335: ldc_w           125
        //   338: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   341: ldc_w           "NONE"
        //   344: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   347: pop            
        //   348: aload_1        
        //   349: aload_2        
        //   350: aload_0        
        //   351: ldc_w           "counter_accuracy"
        //   354: ldc_w           "method__17$RUBY$counter_accuracy"
        //   357: ldc             ",0,0,-1"
        //   359: iconst_0       
        //   360: ldc             "./lib//lister/runner/measurements/upnp.rb"
        //   362: ldc_w           167
        //   365: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   368: ldc_w           "NONE"
        //   371: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   374: pop            
        //   375: aload_1        
        //   376: aload_2        
        //   377: aload_0        
        //   378: ldc_w           "test_upnp_counters"
        //   381: ldc_w           "method__18$RUBY$test_upnp_counters"
        //   384: ldc_w           "ip;t0;t1,0,0,-1"
        //   387: iconst_0       
        //   388: ldc             "./lib//lister/runner/measurements/upnp.rb"
        //   390: ldc_w           171
        //   393: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   396: ldc_w           "NONE"
        //   399: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   402: pop            
        //   403: aload_1        
        //   404: aload_2        
        //   405: aload_0        
        //   406: ldc_w           "both_available_traffic_value?"
        //   409: ldc_w           "method__21$RUBY$both_available_traffic_value_p_"
        //   412: ldc_w           "t1;t0,2,0,-1"
        //   415: iconst_2       
        //   416: ldc             "./lib//lister/runner/measurements/upnp.rb"
        //   418: ldc_w           220
        //   421: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   424: ldc_w           "qt1;qt0"
        //   427: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   430: pop            
        //   431: aload_1        
        //   432: aload_2        
        //   433: aload_0        
        //   434: ldc_w           "execute"
        //   437: ldc_w           "method__22$RUBY$execute"
        //   440: ldc             ",0,0,-1"
        //   442: iconst_0       
        //   443: ldc             "./lib//lister/runner/measurements/upnp.rb"
        //   445: ldc_w           224
        //   448: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   451: ldc_w           "NONE"
        //   454: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   457: aload_1        
        //   458: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   461: goto            469
        //   464: aload_1        
        //   465: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   468: athrow         
        //   469: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  37     457    464    469    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "device_is_gw?", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$device_is_gw_p_(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    15: bipush          10
        //    17: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    20: aload_1        
        //    21: aload_2        
        //    22: aload_0        
        //    23: bipush          11
        //    25: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
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
        //    46: ldc             "block_0$RUBY$device_is_gw?,1,s,false,2,./lib//lister/runner/measurements/upnp.rb,24,true"
        //    48: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    51: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    54: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     44      5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_0$RUBY$device_is_gw?(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    28: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_0        
        //    34: aload_1        
        //    35: ldc             "WANIC1"
        //    37: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    40: aload_0        
        //    41: bipush          13
        //    43: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    46: aload_1        
        //    47: aload_2        
        //    48: aload           s
        //    50: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    53: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    56: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     32      9     s     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "discover_gw", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$discover_gw(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B file_C6A5F5282FB6187C9169768C19B946DBD363349B, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(14).call(threadContext, rubyObject, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 10, 32));
        return chained_5_rescue_1$RUBY$SYNTHETICdiscover_gw(file_C6A5F5282FB6187C9169768C19B946DBD363349B, threadContext, rubyObject, block);
    }
    
    public static IRubyObject chained_5_rescue_1$RUBY$SYNTHETICdiscover_gw(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B file_C6A5F5282FB6187C9169768C19B946DBD363349B, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        final DynamicScope currentScope = context.getCurrentScope();
        final IRubyObject errorInfo = context.getErrorInfo();
        IRubyObject rubyObject2 = null;
        Label_0273: {
            try {
                try {
                    currentScope.setValueZeroDepthZero(file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(15).call(context, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getConstant1(context, "Discovery"), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getFixnum0(context.runtime, 10000), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getConstant2(context, "WANIC1")));
                    if (currentScope.getValueZeroDepthZeroOrNil(context.nil).isTrue()) {
                        file_C6A5F5282FB6187C9169768C19B946DBD363349B.setVariable0(context.runtime, "@gateway", rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(16).callIter(context, rubyObject, currentScope.getValueZeroDepthZeroOrNil(context.nil), RuntimeHelpers.createBlock(context, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getBlockBody1(context, "block_1$RUBY$discover_gw,1,dev,false,2,./lib//lister/runner/measurements/upnp.rb,32,true"))));
                        rubyObject2 = (file_C6A5F5282FB6187C9169768C19B946DBD363349B.getVariable0(context.runtime, "@gateway", rubyObject).isTrue() ? context.nil : file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(18).callIter(context, rubyObject, currentScope.getValueZeroDepthZeroOrNil(context.nil), RuntimeHelpers.createBlock(context, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getBlockBody3(context, "block_2$RUBY$discover_gw,1,dev,false,2,./lib//lister/runner/measurements/upnp.rb,36,false"))));
                    }
                    else {
                        rubyObject2 = context.nil;
                    }
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable t) {
                    if (RuntimeHelpers.isJavaExceptionHandled(t, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(22).call(context, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(23).call(context, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(24).call(context, rubyObject, rubyObject))), context).isTrue()) {
                        RuntimeHelpers.storeExceptionInErrorInfo(t, context);
                        rubyObject2 = chained_6_rescue_line_43(file_C6A5F5282FB6187C9169768C19B946DBD363349B, context, rubyObject, block);
                        RuntimeHelpers.clearErrorInfo(context);
                        break Label_0273;
                    }
                    throw t;
                }
            }
            catch (JumpException.FlowControlException ex2) {
                context.setErrorInfo(errorInfo);
                throw ex2;
            }
        }
        context.setErrorInfo(errorInfo);
        return rubyObject2;
    }
    
    public static IRubyObject block_1$RUBY$discover_gw(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    28: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_2        
        //    34: aload           dev
        //    36: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     15      9     dev   Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_2$RUBY$discover_gw(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: aload_1        
        //    37: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    40: ldc_w           "@gateway"
        //    43: aload_2        
        //    44: aload_0        
        //    45: bipush          19
        //    47: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    50: aload_1        
        //    51: aload_2        
        //    52: aload_0        
        //    53: bipush          20
        //    55: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    58: aload_1        
        //    59: aload_2        
        //    60: aload           locals
        //    62: aload_1        
        //    63: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    69: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    72: aload_1        
        //    73: aload_2        
        //    74: aload_0        
        //    75: aload_1        
        //    76: ldc_w           "block_3$RUBY$discover_gw,1,child,false,2,./lib//lister/runner/measurements/upnp.rb,37,true"
        //    79: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getBlockBody2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    82: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    85: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.setVariable1:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    91: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     57      5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_3$RUBY$discover_gw(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    28: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_2        
        //    34: aload           child
        //    36: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name   Signature
        //  -----  ------  ----  -----  ---------------------------------------
        //  25     15      9     child  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject chained_6_rescue_line_43(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B file_C6A5F5282FB6187C9169768C19B946DBD363349B, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        threadContext.getCurrentScope();
        return file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(25).call(threadContext, rubyObject, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 11, 32), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getSymbol1(threadContext.runtime, "error"));
    }
    
    @JRubyMethod(name = "get_remote_stats", frame = true, required = 1, optional = 0, rest = 1)
    public static IRubyObject method__7$RUBY$get_remote_stats(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload           5
        //     8: invokevirtual   org/jruby/runtime/DynamicScope.getValues:()[Lorg/jruby/runtime/builtin/IRubyObject;
        //    11: astore          6
        //    13: aload           6
        //    15: aload_1        
        //    16: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    19: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.fillNil:([Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;)V
        //    22: aload_1        
        //    23: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    26: aload_3        
        //    27: iconst_1       
        //    28: iconst_m1      
        //    29: invokestatic    org/jruby/runtime/Arity.raiseArgumentError:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;II)V
        //    32: aload_3        
        //    33: iconst_0       
        //    34: aaload         
        //    35: aload           5
        //    37: swap           
        //    38: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: pop            
        //    42: aload_3        
        //    43: aload_1        
        //    44: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    47: iconst_1       
        //    48: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createSubarray:([Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;I)Lorg/jruby/RubyArray;
        //    51: aload           5
        //    53: swap           
        //    54: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: pop            
        //    58: aload_0        
        //    59: bipush          26
        //    61: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    64: aload_1        
        //    65: aload_2        
        //    66: aload_2        
        //    67: aload_1        
        //    68: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    71: ldc_w           20
        //    74: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    77: aload_0        
        //    78: aload_1        
        //    79: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    82: bipush          12
        //    84: bipush          32
        //    86: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    89: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    92: aload           locals
        //    94: aload_1        
        //    95: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    98: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   101: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   106: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   109: aload_0        
        //   110: aload_1        
        //   111: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   114: bipush          13
        //   116: bipush          32
        //   118: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   121: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   124: aload_0        
        //   125: bipush          27
        //   127: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   130: aload_1        
        //   131: aload_2        
        //   132: aload           locals
        //   134: aload_1        
        //   135: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   138: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   141: aload_0        
        //   142: aload_1        
        //   143: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   146: bipush          14
        //   148: bipush          32
        //   150: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   153: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   156: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   161: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   164: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   167: pop            
        //   168: aload           locals
        //   170: aload_1        
        //   171: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   174: aload_0        
        //   175: aload_1        
        //   176: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   179: bipush          15
        //   181: bipush          32
        //   183: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   186: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   189: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   192: pop            
        //   193: aload_0        
        //   194: aload_1        
        //   195: aload_2        
        //   196: aload_3        
        //   197: aload           4
        //   199: invokestatic    ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.chained_8_rescue_2$RUBY$SYNTHETICget_remote_stats:(Lruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   202: pop            
        //   203: aload           locals
        //   205: aload_1        
        //   206: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   209: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   212: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  58     155     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject chained_8_rescue_2$RUBY$SYNTHETICget_remote_stats(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B file_C6A5F5282FB6187C9169768C19B946DBD363349B, final ThreadContext context, final IRubyObject self, final IRubyObject[] array, final Block block) {
        final DynamicScope currentScope = context.getCurrentScope();
        final IRubyObject[] values = currentScope.getValues();
        final IRubyObject errorInfo = context.getErrorInfo();
        IRubyObject rubyObject = null;
        Label_0255: {
            try {
                try {
                    currentScope.setValueThreeDepthZero(file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(28).call(context, self, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(29).call(context, self, self), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getConstant3(context, "WANIC1")));
                    values[4] = file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(30).call(context, self, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getConstant4(context, "UPNPMessageFactory"), currentScope.getValueThreeDepthZeroOrNil(context.nil));
                    values[5] = file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(31).call(context, self, values[4], currentScope.getValueZeroDepthZeroOrNil(context.nil));
                    if (values[5].isTrue()) {
                        values[6] = file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(32).call(context, self, values[5]);
                        rubyObject = currentScope.setValueTwoDepthZero(file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(33).callIter(context, self, currentScope.getValueOneDepthZeroOrNil(context.nil), RuntimeHelpers.createBlock(context, self, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getBlockBody4(context, "block_4$RUBY$get_remote_stats,1,rsp,false,2,./lib//lister/runner/measurements/upnp.rb,57,true"))));
                    }
                    else {
                        rubyObject = context.nil;
                    }
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable t) {
                    if (RuntimeHelpers.isJavaExceptionHandled(t, context.runtime.getStandardError(), context).isTrue()) {
                        RuntimeHelpers.storeExceptionInErrorInfo(t, context);
                        rubyObject = chained_9_rescue_line_61(file_C6A5F5282FB6187C9169768C19B946DBD363349B, context, self, array, block);
                        RuntimeHelpers.clearErrorInfo(context);
                        break Label_0255;
                    }
                    throw t;
                }
            }
            catch (JumpException.FlowControlException ex2) {
                context.setErrorInfo(errorInfo);
                throw ex2;
            }
        }
        context.setErrorInfo(errorInfo);
        return rubyObject;
    }
    
    public static IRubyObject block_4$RUBY$get_remote_stats(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    28: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           5
        //    35: bipush          6
        //    37: iconst_1       
        //    38: aload_1        
        //    39: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    42: invokevirtual   org/jruby/runtime/DynamicScope.getValueOrNil:(IILorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: aload           rsp
        //    47: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     26      9     rsp   Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject chained_9_rescue_line_61(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B file_C6A5F5282FB6187C9169768C19B946DBD363349B, final ThreadContext threadContext, final IRubyObject self, final IRubyObject[] array, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        currentScope.getValues();
        return currentScope.setValueTwoDepthZero(file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(35).callIter(threadContext, self, currentScope.getValueOneDepthZeroOrNil(threadContext.nil), RuntimeHelpers.createBlock(threadContext, self, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getBlockBody5(threadContext, "block_5$RUBY$get_remote_stats,1,rsp,false,2,./lib//lister/runner/measurements/upnp.rb,62,true"))));
    }
    
    public static IRubyObject block_5$RUBY$get_remote_stats(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B file_C6A5F5282FB6187C9169768C19B946DBD363349B, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        threadContext.getCurrentScope();
        final IRubyObject nil = threadContext.nil;
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        return file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 15, 32);
    }
    
    @JRubyMethod(name = "get_upnp_stats", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__10$RUBY$get_upnp_stats(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B file_C6A5F5282FB6187C9169768C19B946DBD363349B, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(RubyHash.newHash(threadContext.runtime));
        locals.setValueOneDepthZero(RuntimeHelpers.constructRubyArray(threadContext.runtime, RuntimeHelpers.constructRubyArray(threadContext.runtime, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 16, 32), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 17, 32), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getSymbol2(threadContext.runtime, "bytes_sent")), RuntimeHelpers.constructRubyArray(threadContext.runtime, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 18, 32), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 19, 32), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getSymbol3(threadContext.runtime, "packets_sent")), RuntimeHelpers.constructRubyArray(threadContext.runtime, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 20, 32), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 21, 32), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getSymbol4(threadContext.runtime, "bytes_received")), RuntimeHelpers.constructRubyArray(threadContext.runtime, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 22, 32), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 23, 32), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getSymbol5(threadContext.runtime, "packets_received"))));
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(36).callIter(threadContext, self, locals.getValueOneDepthZeroOrNil(threadContext.nil), RuntimeHelpers.createBlock(threadContext, self, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getBlockBody6(threadContext, "block_6$RUBY$get_upnp_stats,3,g;s;sym;stats;val,true,1,./lib//lister/runner/measurements/upnp.rb,73,true")));
        return locals.getValueZeroDepthZeroOrNil(threadContext.nil);
    }
    
    public static IRubyObject block_6$RUBY$get_upnp_stats(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    25: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    28: astore          12
        //    30: aload_1        
        //    31: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    34: astore          13
        //    36: aload_1        
        //    37: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    40: aload           4
        //    42: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: aload_3        
        //    46: aload_1        
        //    47: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    50: iconst_1       
        //    51: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.ensureMultipleAssignableRubyArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;Z)Lorg/jruby/RubyArray;
        //    54: astore          14
        //    56: aload           14
        //    58: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilZero:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    61: astore          9
        //    63: aload           14
        //    65: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilOne:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    68: astore          10
        //    70: aload           14
        //    72: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilTwo:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: astore          11
        //    77: aload           14
        //    79: pop            
        //    80: pop            
        //    81: aload_0        
        //    82: bipush          37
        //    84: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
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
        //   105: bipush          24
        //   107: bipush          32
        //   109: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   112: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   115: aload           g
        //   117: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   122: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   125: aload_0        
        //   126: aload_1        
        //   127: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   130: bipush          25
        //   132: bipush          32
        //   134: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   137: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   140: aload           s
        //   142: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   147: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   150: aload_0        
        //   151: aload_1        
        //   152: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   155: bipush          26
        //   157: bipush          32
        //   159: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   162: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   165: aload           sym
        //   167: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   172: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   175: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   178: pop            
        //   179: aload_0        
        //   180: bipush          38
        //   182: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   185: aload_1        
        //   186: aload_2        
        //   187: aload_2        
        //   188: aload           g
        //   190: aload           s
        //   192: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   195: astore          stats
        //   197: aload           stats
        //   199: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   204: ifeq            407
        //   207: aload_0        
        //   208: bipush          39
        //   210: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   213: aload_1        
        //   214: aload_2        
        //   215: aload_2        
        //   216: aload_1        
        //   217: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   220: ldc_w           20
        //   223: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   226: aload_0        
        //   227: aload_1        
        //   228: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   231: bipush          27
        //   233: bipush          32
        //   235: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   238: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   241: aload_0        
        //   242: bipush          40
        //   244: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   247: aload_1        
        //   248: aload_2        
        //   249: aload           stats
        //   251: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   254: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   259: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   262: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   265: pop            
        //   266: aload_0        
        //   267: bipush          41
        //   269: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   272: aload_1        
        //   273: aload_2        
        //   274: aload           stats
        //   276: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   279: astore          val
        //   281: aload_0        
        //   282: bipush          42
        //   284: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   287: aload_1        
        //   288: aload_2        
        //   289: aload           val
        //   291: aload_0        
        //   292: aload_1        
        //   293: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   296: bipush          15
        //   298: bipush          32
        //   300: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   303: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   306: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   311: ifeq            355
        //   314: aload           5
        //   316: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   319: aload_1        
        //   320: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   323: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   326: dup            
        //   327: aload_2        
        //   328: aload_0        
        //   329: bipush          43
        //   331: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   334: aload_0        
        //   335: bipush          44
        //   337: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   340: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   343: aload           sym
        //   345: aload           val
        //   347: aload_1        
        //   348: aload_2        
        //   349: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   352: goto            404
        //   355: aload           5
        //   357: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   360: aload_1        
        //   361: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   364: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   367: dup            
        //   368: aload_2        
        //   369: aload_0        
        //   370: bipush          45
        //   372: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   375: aload_0        
        //   376: bipush          46
        //   378: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   381: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   384: aload           sym
        //   386: aload_0        
        //   387: bipush          47
        //   389: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   392: aload_1        
        //   393: aload_2        
        //   394: aload           val
        //   396: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   399: aload_1        
        //   400: aload_2        
        //   401: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   404: goto            431
        //   407: aload_0        
        //   408: bipush          48
        //   410: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   413: aload_1        
        //   414: aload_2        
        //   415: aload_2        
        //   416: aload_0        
        //   417: aload_1        
        //   418: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   421: bipush          28
        //   423: bipush          32
        //   425: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   428: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   431: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name   Signature
        //  -----  ------  ----  -----  ---------------------------------------
        //  81     351     9     g      Lorg/jruby/runtime/builtin/IRubyObject;
        //  81     351     10    s      Lorg/jruby/runtime/builtin/IRubyObject;
        //  81     351     11    sym    Lorg/jruby/runtime/builtin/IRubyObject;
        //  81     351     12    stats  Lorg/jruby/runtime/builtin/IRubyObject;
        //  81     351     13    val    Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "get_isp", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__11$RUBY$get_isp(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B file_C6A5F5282FB6187C9169768C19B946DBD363349B, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject query_ret = threadContext.nil;
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(49).call(threadContext, object, object, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 29, 32));
        final IRubyObject at = file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 30, 32);
        query_ret = file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(50).call(threadContext, object, object, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(51).call(threadContext, object, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 31, 32), at), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(52).call(threadContext, object, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 32, 32), at));
        return query_ret.isTrue() ? file_C6A5F5282FB6187C9169768C19B946DBD363349B.setVariable2(threadContext.runtime, "@isp", object, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(53).call(threadContext, object, query_ret)) : threadContext.nil;
    }
    
    @JRubyMethod(name = "isp", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__12$RUBY$isp(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B file_C6A5F5282FB6187C9169768C19B946DBD363349B, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if (!(rubyObject = file_C6A5F5282FB6187C9169768C19B946DBD363349B.getVariable1(threadContext.runtime, "@isp", object)).isTrue()) {
            rubyObject = file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 33, 32);
        }
        return rubyObject;
    }
    
    @JRubyMethod(name = "sync_rates", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__13$RUBY$sync_rates(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B file_C6A5F5282FB6187C9169768C19B946DBD363349B, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@sync_rates") ? file_C6A5F5282FB6187C9169768C19B946DBD363349B.getByteList(34) : null) == null) {
            rubyObject = file_C6A5F5282FB6187C9169768C19B946DBD363349B.setVariable3(threadContext.runtime, "@sync_rates", object, RubyHash.newHash(threadContext.runtime));
        }
        else if (!(rubyObject = file_C6A5F5282FB6187C9169768C19B946DBD363349B.getVariable2(threadContext.runtime, "@sync_rates", object)).isTrue()) {
            rubyObject = file_C6A5F5282FB6187C9169768C19B946DBD363349B.setVariable4(threadContext.runtime, "@sync_rates", object, RubyHash.newHash(threadContext.runtime));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "get_link_infos", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__14$RUBY$get_link_infos(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B file_C6A5F5282FB6187C9169768C19B946DBD363349B, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(54).call(threadContext, self, self, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 35, 32));
        locals.setValueZeroDepthZero(file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(55).call(threadContext, self, self, RuntimeHelpers.constructObjectArray(file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 36, 32), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 37, 32), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 38, 32), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 39, 32))));
        IRubyObject rubyObject;
        if (locals.getValueZeroDepthZeroOrNil(threadContext.nil).isTrue()) {
            file_C6A5F5282FB6187C9169768C19B946DBD363349B.setVariable5(threadContext.runtime, "@line_type", self, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(56).call(threadContext, self, locals.getValueZeroDepthZeroOrNil(threadContext.nil)));
            final IRubyObject call = file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(57).call(threadContext, self, self);
            RuntimeHelpers.doAttrAsgn(call, RuntimeHelpers.selectAttrAsgnCallSite(call, self, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(58), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(59)), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getSymbol6(threadContext.runtime, "up"), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(60).call(threadContext, self, locals.getValueZeroDepthZeroOrNil(threadContext.nil), RubyFixnum.one(threadContext.runtime)), threadContext, self);
            final IRubyObject call2 = file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(61).call(threadContext, self, self);
            rubyObject = RuntimeHelpers.doAttrAsgn(call2, RuntimeHelpers.selectAttrAsgnCallSite(call2, self, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(62), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(63)), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getSymbol7(threadContext.runtime, "down"), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(64).call(threadContext, self, locals.getValueZeroDepthZeroOrNil(threadContext.nil), RubyFixnum.two(threadContext.runtime)), threadContext, self);
        }
        else {
            rubyObject = file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(65).call(threadContext, self, self, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 40, 32));
        }
        return rubyObject;
    }
    
    @JRubyMethod(name = "gateway_model", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__15$RUBY$gateway_model(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B file_C6A5F5282FB6187C9169768C19B946DBD363349B, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@gateway_model") ? file_C6A5F5282FB6187C9169768C19B946DBD363349B.getByteList(34) : null) == null) {
            rubyObject = file_C6A5F5282FB6187C9169768C19B946DBD363349B.setVariable6(threadContext.runtime, "@gateway_model", object, RubyHash.newHash(threadContext.runtime));
        }
        else if (!(rubyObject = file_C6A5F5282FB6187C9169768C19B946DBD363349B.getVariable3(threadContext.runtime, "@gateway_model", object)).isTrue()) {
            rubyObject = file_C6A5F5282FB6187C9169768C19B946DBD363349B.setVariable7(threadContext.runtime, "@gateway_model", object, RubyHash.newHash(threadContext.runtime));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "get_model", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__16$RUBY$get_model(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B file_C6A5F5282FB6187C9169768C19B946DBD363349B, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(66).call(threadContext, self, self, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 41, 32));
        locals.setValueZeroDepthZero(RubyArray.newArrayNoCopy(threadContext.runtime, new IRubyObject[] { file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 42, 32), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 43, 32), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 44, 32), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 45, 32), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 46, 32), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 47, 32), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 48, 32), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 49, 32), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 50, 32), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 51, 32) }));
        return file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(67).callIter(threadContext, self, locals.getValueZeroDepthZeroOrNil(threadContext.nil), RuntimeHelpers.createBlock(threadContext, self, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getBlockBody7(threadContext, "block_7$RUBY$get_model,1,str;key,false,2,./lib//lister/runner/measurements/upnp.rb,161,false")));
    }
    
    public static IRubyObject block_7$RUBY$get_model(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    43: aload_0        
        //    44: bipush          68
        //    46: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload           locals
        //    53: aload_1        
        //    54: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: aload_0        
        //    61: aload_1        
        //    62: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    65: aload_0        
        //    66: bipush          52
        //    68: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getByteList:(I)Lorg/jruby/util/ByteList;
        //    71: ldc_w           512
        //    74: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getRegexp0:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //    77: aload_0        
        //    78: aload_1        
        //    79: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    82: bipush          53
        //    84: bipush          32
        //    86: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    89: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    92: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    95: pop            
        //    96: aload_0        
        //    97: bipush          69
        //    99: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   102: aload_1        
        //   103: aload_2        
        //   104: aload_2        
        //   105: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   108: dup            
        //   109: aload_2        
        //   110: aload_0        
        //   111: bipush          70
        //   113: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   116: aload_0        
        //   117: bipush          71
        //   119: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   122: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   125: aload           locals
        //   127: aload_1        
        //   128: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   131: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   134: aload_0        
        //   135: bipush          72
        //   137: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   140: aload_1        
        //   141: aload_2        
        //   142: aload_0        
        //   143: bipush          73
        //   145: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   148: aload_1        
        //   149: aload_2        
        //   150: aload_2        
        //   151: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   154: aload           locals
        //   156: aload_1        
        //   157: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   160: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   163: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   166: aload_1        
        //   167: aload_2        
        //   168: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   171: areturn        
        //   172: pop            
        //   173: goto            41
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  41     131     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  41     172    172    176    Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "counter_accuracy", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__17$RUBY$counter_accuracy(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B file_C6A5F5282FB6187C9169768C19B946DBD363349B, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@counter_accuracy") ? file_C6A5F5282FB6187C9169768C19B946DBD363349B.getByteList(34) : null) == null) {
            rubyObject = file_C6A5F5282FB6187C9169768C19B946DBD363349B.setVariable8(threadContext.runtime, "@counter_accuracy", object, RubyHash.newHash(threadContext.runtime));
        }
        else if (!(rubyObject = file_C6A5F5282FB6187C9169768C19B946DBD363349B.getVariable4(threadContext.runtime, "@counter_accuracy", object)).isTrue()) {
            rubyObject = file_C6A5F5282FB6187C9169768C19B946DBD363349B.setVariable9(threadContext.runtime, "@counter_accuracy", object, RubyHash.newHash(threadContext.runtime));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "test_upnp_counters", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__18$RUBY$test_upnp_counters(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B file_C6A5F5282FB6187C9169768C19B946DBD363349B, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(74).call(threadContext, rubyObject, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 54, 32));
        locals.setValueZeroDepthZero(file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 55, 32));
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(75).callIter(threadContext, rubyObject, RubyFixnum.three(threadContext.runtime), RuntimeHelpers.createBlock(threadContext, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getBlockBody8(threadContext, "block_8$RUBY$test_upnp_counters,1,t;host,false,2,./lib//lister/runner/measurements/upnp.rb,174,false")));
        locals.setValueOneDepthZero(RubyHash.newHash(threadContext.runtime));
        locals.setValueTwoDepthZero(RubyHash.newHash(threadContext.runtime));
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(80).call(threadContext, rubyObject, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 60, 32));
        final IRubyObject valueOneDepthZeroOrNil = locals.getValueOneDepthZeroOrNil(threadContext.nil);
        RuntimeHelpers.doAttrAsgn(valueOneDepthZeroOrNil, RuntimeHelpers.selectAttrAsgnCallSite(valueOneDepthZeroOrNil, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(81), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(82)), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getSymbol8(threadContext.runtime, "remote"), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(83).call(threadContext, rubyObject, rubyObject), threadContext, rubyObject);
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(84).call(threadContext, rubyObject, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 61, 32));
        final IRubyObject valueOneDepthZeroOrNil2 = locals.getValueOneDepthZeroOrNil(threadContext.nil);
        RuntimeHelpers.doAttrAsgn(valueOneDepthZeroOrNil2, RuntimeHelpers.selectAttrAsgnCallSite(valueOneDepthZeroOrNil2, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(85), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(86)), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getSymbol9(threadContext.runtime, "local"), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(87).call(threadContext, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getConstantFrom(RuntimeHelpers.checkIsModule(file_C6A5F5282FB6187C9169768C19B946DBD363349B.getConstant(threadContext, "Utils", 10)), threadContext, "Traffic", 11)), threadContext, rubyObject);
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(88).call(threadContext, rubyObject, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getFixnum1(threadContext.runtime, 40));
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(89).call(threadContext, rubyObject, rubyObject, RubyString.newStringLight(threadContext.runtime, 20).append(file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 62, 32)).append(locals.getValueZeroDepthZeroOrNil(threadContext.nil).asString()));
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(90).call(threadContext, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getConstantFrom(RuntimeHelpers.checkIsModule(file_C6A5F5282FB6187C9169768C19B946DBD363349B.getConstant(threadContext, "Utils", 12)), threadContext, "Traffic", 13), RuntimeHelpers.constructHash(threadContext.runtime, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getSymbol(threadContext.runtime, 10, "ip"), locals.getValueZeroDepthZeroOrNil(threadContext.nil)));
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(91).call(threadContext, rubyObject, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getFixnum2(threadContext.runtime, 80));
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(92).call(threadContext, rubyObject, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 63, 32));
        final IRubyObject valueTwoDepthZeroOrNil = locals.getValueTwoDepthZeroOrNil(threadContext.nil);
        RuntimeHelpers.doAttrAsgn(valueTwoDepthZeroOrNil, RuntimeHelpers.selectAttrAsgnCallSite(valueTwoDepthZeroOrNil, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(93), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(94)), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getSymbol9(threadContext.runtime, "local"), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(95).call(threadContext, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getConstantFrom(RuntimeHelpers.checkIsModule(file_C6A5F5282FB6187C9169768C19B946DBD363349B.getConstant(threadContext, "Utils", 14)), threadContext, "Traffic", 15)), threadContext, rubyObject);
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(96).call(threadContext, rubyObject, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 64, 32));
        final IRubyObject valueTwoDepthZeroOrNil2 = locals.getValueTwoDepthZeroOrNil(threadContext.nil);
        RuntimeHelpers.doAttrAsgn(valueTwoDepthZeroOrNil2, RuntimeHelpers.selectAttrAsgnCallSite(valueTwoDepthZeroOrNil2, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(97), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(98)), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getSymbol8(threadContext.runtime, "remote"), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(99).call(threadContext, rubyObject, rubyObject), threadContext, rubyObject);
        return file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(100).callIter(threadContext, rubyObject, RuntimeHelpers.constructRubyArray(threadContext.runtime, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getSymbol(threadContext.runtime, 11, "bytes"), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getSymbol(threadContext.runtime, 12, "packets")), RuntimeHelpers.createBlock(threadContext, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getBlockBody(threadContext, 11, "block_9$RUBY$test_upnp_counters,1,what,false,2,./lib//lister/runner/measurements/upnp.rb,205,false")));
    }
    
    public static IRubyObject block_8$RUBY$test_upnp_counters(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B file_C6A5F5282FB6187C9169768C19B946DBD363349B, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject valueZeroDepthZero, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        currentScope.setValueOneDepthZero(currentScope.setValueZeroDepthZero(threadContext.nil));
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        currentScope.setValueZeroDepthZero(valueZeroDepthZero);
        try {
            return chained_19_rescue_3$RUBY$SYNTHETICtest_upnp_counters(file_C6A5F5282FB6187C9169768C19B946DBD363349B, threadContext, rubyObject, valueZeroDepthZero, block);
        }
        catch (JumpException.RedoJump redoJump) {
            return chained_19_rescue_3$RUBY$SYNTHETICtest_upnp_counters(file_C6A5F5282FB6187C9169768C19B946DBD363349B, threadContext, rubyObject, valueZeroDepthZero, block);
        }
    }
    
    public static IRubyObject chained_19_rescue_3$RUBY$SYNTHETICtest_upnp_counters(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B file_C6A5F5282FB6187C9169768C19B946DBD363349B, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final DynamicScope currentScope = context.getCurrentScope();
        final IRubyObject errorInfo = context.getErrorInfo();
        IRubyObject rubyObject3 = null;
        Label_0233: {
            try {
                try {
                    currentScope.setValueOneDepthZero(file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(context.runtime, 56, 32));
                    file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(76).call(context, rubyObject, rubyObject, RubyString.newStringLight(context.runtime, 20).append(file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(context.runtime, 57, 32)).append(currentScope.getValueOneDepthZeroOrNil(context.nil).asString()));
                    currentScope.getNextCapturedScope().setValueZeroDepthZero(file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(77).call(context, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(78).call(context, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getConstant5(context, "Resolv")), currentScope.getValueOneDepthZeroOrNil(context.nil)));
                    rubyObject3 = RuntimeHelpers.breakJump(context, context.nil);
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable t) {
                    if (RuntimeHelpers.isJavaExceptionHandled(t, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getConstantFrom7(RuntimeHelpers.checkIsModule(file_C6A5F5282FB6187C9169768C19B946DBD363349B.getConstant6(context, "Resolv")), context, "ResolvError"), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getConstant8(context, "TypeError"), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getConstant9(context, "SocketError"), context).isTrue()) {
                        RuntimeHelpers.storeExceptionInErrorInfo(t, context);
                        rubyObject3 = chained_20_rescue_line_182(file_C6A5F5282FB6187C9169768C19B946DBD363349B, context, rubyObject, rubyObject2, block);
                        RuntimeHelpers.clearErrorInfo(context);
                        break Label_0233;
                    }
                    throw t;
                }
            }
            catch (JumpException.FlowControlException ex2) {
                context.setErrorInfo(errorInfo);
                throw ex2;
            }
        }
        context.setErrorInfo(errorInfo);
        return rubyObject3;
    }
    
    public static IRubyObject chained_20_rescue_line_182(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B file_C6A5F5282FB6187C9169768C19B946DBD363349B, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(79).call(threadContext, rubyObject, rubyObject, RubyString.newStringLight(threadContext.runtime, 20).append(file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 58, 32)).append(threadContext.getCurrentScope().getValueOneDepthZeroOrNil(threadContext.nil).asString()).append(file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 59, 32)));
    }
    
    public static IRubyObject block_9$RUBY$test_upnp_counters(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B file_C6A5F5282FB6187C9169768C19B946DBD363349B, final ThreadContext threadContext, final IRubyObject self, final IRubyObject valueZeroDepthZero, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        currentScope.setValueZeroDepthZero(threadContext.nil);
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        currentScope.setValueZeroDepthZero(valueZeroDepthZero);
        return file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(101).callIter(threadContext, self, RuntimeHelpers.constructRubyArray(threadContext.runtime, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getSymbol(threadContext.runtime, 13, "sent"), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getSymbol(threadContext.runtime, 14, "received")), RuntimeHelpers.createBlock(threadContext, self, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getBlockBody(threadContext, 10, "block_10$RUBY$test_upnp_counters,1,which_direction,false,2,./lib//lister/runner/measurements/upnp.rb,206,false")));
    }
    
    public static IRubyObject block_10$RUBY$test_upnp_counters(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B file_C6A5F5282FB6187C9169768C19B946DBD363349B, final ThreadContext threadContext, final IRubyObject self, final IRubyObject valueZeroDepthZero, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        currentScope.setValueZeroDepthZero(threadContext.nil);
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        currentScope.setValueZeroDepthZero(valueZeroDepthZero);
        return file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(102).callIter(threadContext, self, RuntimeHelpers.constructRubyArray(threadContext.runtime, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getSymbol8(threadContext.runtime, "remote"), file_C6A5F5282FB6187C9169768C19B946DBD363349B.getSymbol9(threadContext.runtime, "local")), RuntimeHelpers.createBlock(threadContext, self, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getBlockBody9(threadContext, "block_11$RUBY$test_upnp_counters,1,where;delta_str;sym,false,2,./lib//lister/runner/measurements/upnp.rb,207,false")));
    }
    
    public static IRubyObject block_11$RUBY$test_upnp_counters(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    22: aload           5
        //    24: swap           
        //    25: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    28: pop            
        //    29: aload_1        
        //    30: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    33: aload           4
        //    35: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    38: aload_3        
        //    39: aload           5
        //    41: swap           
        //    42: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: pop            
        //    46: pop            
        //    47: aload           locals
        //    49: aload_1        
        //    50: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    53: ldc_w           20
        //    56: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    59: aload_0        
        //    60: aload_1        
        //    61: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    64: bipush          65
        //    66: bipush          32
        //    68: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    71: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    74: aload           locals
        //    76: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    79: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    82: aload_1        
        //    83: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    86: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //    94: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    97: aload_0        
        //    98: aload_1        
        //    99: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   102: bipush          66
        //   104: bipush          32
        //   106: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   109: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   112: aload           locals
        //   114: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   117: aload_1        
        //   118: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   121: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   124: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   129: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   132: aload_0        
        //   133: aload_1        
        //   134: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   137: bipush          66
        //   139: bipush          32
        //   141: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   144: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   147: aload           locals
        //   149: aload_1        
        //   150: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   153: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   156: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   161: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   164: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   167: pop            
        //   168: aload           locals
        //   170: aload_0        
        //   171: bipush          103
        //   173: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   176: aload_1        
        //   177: aload_2        
        //   178: aload_1        
        //   179: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   182: ldc_w           20
        //   185: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   188: aload           locals
        //   190: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   193: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   196: aload_1        
        //   197: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   200: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   203: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   208: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   211: aload_0        
        //   212: aload_1        
        //   213: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   216: bipush          66
        //   218: bipush          32
        //   220: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   223: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   226: aload           locals
        //   228: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   231: aload_1        
        //   232: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   235: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   238: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   243: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   246: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   249: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   252: pop            
        //   253: aload_0        
        //   254: bipush          104
        //   256: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   259: aload_1        
        //   260: aload_2        
        //   261: aload_2        
        //   262: aload_0        
        //   263: bipush          105
        //   265: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   268: aload_1        
        //   269: aload_2        
        //   270: aload_0        
        //   271: bipush          106
        //   273: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   276: aload_1        
        //   277: aload_2        
        //   278: aload           locals
        //   280: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   283: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   286: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   289: aload_1        
        //   290: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   293: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   296: aload           locals
        //   298: aload_1        
        //   299: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   302: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   305: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   308: aload           locals
        //   310: aload_1        
        //   311: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   314: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   317: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   320: aload_0        
        //   321: bipush          107
        //   323: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   326: aload_1        
        //   327: aload_2        
        //   328: aload_0        
        //   329: bipush          108
        //   331: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   334: aload_1        
        //   335: aload_2        
        //   336: aload           locals
        //   338: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   341: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   344: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   347: aload_1        
        //   348: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   351: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   354: aload           locals
        //   356: aload_1        
        //   357: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   360: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   363: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   366: aload           locals
        //   368: aload_1        
        //   369: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   372: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   375: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   378: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   381: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   386: ifeq            562
        //   389: aload_0        
        //   390: bipush          109
        //   392: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   395: aload_1        
        //   396: aload_2        
        //   397: aload_2        
        //   398: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   401: dup            
        //   402: aload_2        
        //   403: aload_0        
        //   404: bipush          110
        //   406: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   409: aload_0        
        //   410: bipush          111
        //   412: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   415: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   418: aload           locals
        //   420: aload_1        
        //   421: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   424: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   427: aload_0        
        //   428: bipush          112
        //   430: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   433: aload_1        
        //   434: aload_2        
        //   435: aload_0        
        //   436: bipush          113
        //   438: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   441: aload_1        
        //   442: aload_2        
        //   443: aload_0        
        //   444: bipush          114
        //   446: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   449: aload_1        
        //   450: aload_2        
        //   451: aload           locals
        //   453: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   456: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   459: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   462: aload_1        
        //   463: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   466: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   469: aload           locals
        //   471: aload_1        
        //   472: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   475: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   478: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   481: aload           locals
        //   483: aload_1        
        //   484: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   487: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   490: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   493: aload_0        
        //   494: bipush          115
        //   496: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   499: aload_1        
        //   500: aload_2        
        //   501: aload_0        
        //   502: bipush          116
        //   504: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   507: aload_1        
        //   508: aload_2        
        //   509: aload           locals
        //   511: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   514: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   517: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   520: aload_1        
        //   521: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   524: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   527: aload           locals
        //   529: aload_1        
        //   530: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   533: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   536: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   539: aload           locals
        //   541: aload_1        
        //   542: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   545: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   548: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   551: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   554: aload_1        
        //   555: aload_2        
        //   556: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   559: goto            617
        //   562: aload_0        
        //   563: bipush          117
        //   565: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   568: aload_1        
        //   569: aload_2        
        //   570: aload_2        
        //   571: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   574: dup            
        //   575: aload_2        
        //   576: aload_0        
        //   577: bipush          118
        //   579: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   582: aload_0        
        //   583: bipush          119
        //   585: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   588: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   591: aload           locals
        //   593: aload_1        
        //   594: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   597: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   600: aload_0        
        //   601: aload_1        
        //   602: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   605: bipush          33
        //   607: bipush          32
        //   609: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   612: aload_1        
        //   613: aload_2        
        //   614: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   617: areturn        
        //   618: pop            
        //   619: goto            47
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  47     571     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  47     618    618    622    Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "both_available_traffic_value?", frame = true, required = 2, optional = 0, rest = -1)
    public static IRubyObject method__21$RUBY$both_available_traffic_value_p_(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final Block p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_3        
        //     1: astore          10
        //     3: aload           4
        //     5: astore          t0
        //     7: aload           t1
        //     9: dup            
        //    10: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    15: ifeq            21
        //    18: pop            
        //    19: aload           t0
        //    21: dup            
        //    22: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    27: ifeq            63
        //    30: pop            
        //    31: aload_0        
        //    32: bipush          120
        //    34: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    37: aload_1        
        //    38: aload_2        
        //    39: aload           t1
        //    41: aload_0        
        //    42: aload_1        
        //    43: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    46: bipush          15
        //    48: bipush          32
        //    50: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    53: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    56: aload_1        
        //    57: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    60: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.negate:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: dup            
        //    64: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    69: ifeq            105
        //    72: pop            
        //    73: aload_0        
        //    74: bipush          121
        //    76: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    79: aload_1        
        //    80: aload_2        
        //    81: aload           t0
        //    83: aload_0        
        //    84: aload_1        
        //    85: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    88: bipush          15
        //    90: bipush          32
        //    92: invokevirtual   ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    95: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    98: aload_1        
        //    99: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   102: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.negate:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  7      99      10    t1    Lorg/jruby/runtime/builtin/IRubyObject;
        //  7      99      11    t0    Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "execute", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__22$RUBY$execute(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B file_C6A5F5282FB6187C9169768C19B946DBD363349B, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(122).call(threadContext, rubyObject, rubyObject);
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(123).call(threadContext, rubyObject, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getFixnum3(threadContext.runtime, 20));
        IRubyObject rubyObject2;
        if (file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(124).call(threadContext, rubyObject, rubyObject).isTrue()) {
            final Ruby runtime = threadContext.runtime;
            final int i = 10;
            final String name = "@has_gateway";
            final RubyBoolean true = threadContext.runtime.getTrue();
            threadContext.pollThreadEvents();
            file_C6A5F5282FB6187C9169768C19B946DBD363349B.setVariable(runtime, i, name, rubyObject, true);
            file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(125).call(threadContext, rubyObject, rubyObject);
            file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(126).call(threadContext, rubyObject, rubyObject);
            file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(127).call(threadContext, rubyObject, rubyObject);
            file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(128).call(threadContext, rubyObject, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getFixnum4(threadContext.runtime, 30));
            rubyObject2 = file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(129).call(threadContext, rubyObject, rubyObject);
        }
        else {
            file_C6A5F5282FB6187C9169768C19B946DBD363349B.getCallSite(130).call(threadContext, rubyObject, rubyObject, file_C6A5F5282FB6187C9169768C19B946DBD363349B.getString(threadContext.runtime, 67, 32));
            final Ruby runtime2 = threadContext.runtime;
            final int j = 11;
            final String name2 = "@has_gateway";
            final RubyBoolean false = threadContext.runtime.getFalse();
            threadContext.pollThreadEvents();
            rubyObject2 = file_C6A5F5282FB6187C9169768C19B946DBD363349B.setVariable(runtime2, j, name2, rubyObject, false);
        }
        return rubyObject2;
    }
    
    public static IRubyObject class_2$RUBY$UPnP(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B file_C6A5F5282FB6187C9169768C19B946DBD363349B, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_2$RUBY$UPnP(file_C6A5F5282FB6187C9169768C19B946DBD363349B, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B file_C6A5F5282FB6187C9169768C19B946DBD363349B, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Measurements(file_C6A5F5282FB6187C9169768C19B946DBD363349B, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B file_C6A5F5282FB6187C9169768C19B946DBD363349B, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_C6A5F5282FB6187C9169768C19B946DBD363349B, threadContext, rubyObject, block);
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
        final FILE_C6A5F5282FB6187C9169768C19B946DBD363349B file_C6A5F5282FB6187C9169768C19B946DBD363349B = new FILE_C6A5F5282FB6187C9169768C19B946DBD363349B();
        final String string = FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.class.getClassLoader().getResource("ruby/jit/FILE_C6A5F5282FB6187C9169768C19B946DBD363349B.class").toString();
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_C6A5F5282FB6187C9169768C19B946DBD363349B.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
