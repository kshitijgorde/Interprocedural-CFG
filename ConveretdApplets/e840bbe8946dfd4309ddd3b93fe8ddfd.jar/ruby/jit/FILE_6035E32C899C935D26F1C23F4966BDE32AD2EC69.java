// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.exceptions.JumpException;
import org.jruby.runtime.DynamicScope;
import org.jruby.RubyHash;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 extends AbstractScript
{
    public FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69() {
        this.filename = "./lib//lister/runner/measurements/lan_services.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffinclude_class\uffffF\uffffinclude\uffffF\uffffgather_zeroconf_services!\uffffF\uffffprogress\uffffF\uffffgather_upnp_services!\uffffF\uffffgather_zeroconf_services\uffffV\uffffgather_upnp_services\uffffV\uffffeach\uffffN\uffffupnp_devices_decent\uffffF\uffffchild_devices\uffffN\uffffhexdigest\uffffN\ufffflog\uffffF\uffffdiscover\uffffN\uffffprogress\uffffF\uffffmap\uffffN\uffff[]\uffffF\uffffudn\uffffN\uffffsha1\uffffF\ufffffriendly_name\uffffN\uffffdevice_type\uffffN\uffffhide_ip\uffffF\uffffhost\uffffN\uffffdevice_def_loc\uffffN\uffff[]=\uffffF\uffff[]\uffffN\uffffudn\uffffN\uffffupnp_devices_decent\uffffF\uffffchild_devices\uffffN\uffffeach\uffffN\uffffservices\uffffN\uffffservice_type\uffffN\uffffinclude?\uffffN\uffff[]\uffffN\uffff<<\uffffN\uffff[]\uffffN\uffffvalues\uffffN\ufffflog\uffffF\uffffon_linux?\uffffN\uffffrunner\uffffV\uffffempty?\uffffN\uffff`\uffffF\uffffgather_zeroconf_services_linux\uffffV\uffffon_windows?\uffffN\uffffrunner\uffffV\uffffon_mac?\uffffN\uffffrunner\uffffV\uffffrequire\uffffF\uffffgather_zeroconf_services_apple_soft\uffffV\uffffnew\uffffN\uffffrun\uffffN\uffffeach_pair\uffffN\uffffgroup_by\uffffN\uffffservices\uffffN\uffffeach_pair\uffffN\uffffgroup_by\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffhide_ip\uffffF\uffff[]=\uffffN\uffff[]=\uffffV\uffffuniq\uffffN\uffffmap\uffffN\uffff<<\uffffN\uffffnew\uffffN\uffffeach_line\uffffN\uffff`\uffffF\uffffsplit\uffffN\uffff<<\uffffN\uffffnew\uffffN\uffffeach_with_index\uffffN\uffffgroup_by\uffffN\uffffeach_pair\uffffN\uffffgroup_by\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffhide_ip\uffffF\uffff[]=\uffffN\uffff[]=\uffffV\uffffuniq\uffffN\uffffmap\uffffN\uffff<<\uffffN\uffff\u0003\u0007\u0004\u0000\n\u0000\u0000\u0000\u0002\n\u0000\u0000\u0010\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(13, "services", this.getEncoding0());
        this.setByteList(4, "Discovering UPnP services", this.getEncoding0());
        this.setByteList(10, "macOS/zeroconf", this.getEncoding0());
        this.setByteList(7, "Discovering Zeroconf services", this.getEncoding0());
        this.setByteList(6, "n/a", this.getEncoding0());
        this.setByteList(12, "ip", this.getEncoding0());
        this.setByteList(1, "lister/utils/hide_ip", this.getEncoding0());
        this.setByteList(8, "which avahi-browse", this.getEncoding0());
        this.setByteList(0, "lister/measurements/lan_services", this.getEncoding0());
        this.setByteList(14, "avahi-browse -ratp", this.getEncoding0());
        this.setByteList(2, "digest/sha1", this.getEncoding0());
        this.setByteList(15, ";", this.getEncoding0());
        this.setByteList(5, "ssdp:all", this.getEncoding0());
        this.setByteList(3, "net.sbbi.upnp.Discovery", this.getEncoding0());
        this.setByteList(11, "not-running", this.getEncoding0());
        this.setByteList(9, "not-available", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 file_6035E32C899C935D26F1C23F4966BDE32AD2EC69, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite0().call(threadContext, rubyObject, rubyObject, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getString0(threadContext.runtime, 32));
        file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite1().call(threadContext, rubyObject, rubyObject, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getString1(threadContext.runtime, 32));
        file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite2().call(threadContext, rubyObject, rubyObject, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getString2(threadContext.runtime, 32));
        file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite3().call(threadContext, rubyObject, rubyObject, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getString3(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_6035E32C899C935D26F1C23F4966BDE32AD2EC69, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.module__1$RUBY$Measurements:(Lruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc_w           "Measurement"
        //    34: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getConstant9:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    37: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    40: invokestatic    ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.class_2$RUBY$NetworkServices:(Lruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject class_2$RUBY$NetworkServices(final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc             "NetworkServices"
        //    19: swap           
        //    20: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    23: dup            
        //    24: astore_2       
        //    25: aload_1        
        //    26: swap           
        //    27: aload_0        
        //    28: aload_1        
        //    29: ldc             ",0,0,-1"
        //    31: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    34: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    37: aload_0        
        //    38: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite4:()Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_2        
        //    44: aload_0        
        //    45: aload_1        
        //    46: ldc             "HideIP"
        //    48: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: pop            
        //    55: aload_1        
        //    56: aload_2        
        //    57: aload_0        
        //    58: ldc             "execute"
        //    60: ldc             "method__3$RUBY$execute"
        //    62: ldc             ",0,0,-1"
        //    64: iconst_0       
        //    65: ldc             "./lib//lister/runner/measurements/lan_services.rb"
        //    67: ldc             10
        //    69: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    72: ldc             "NONE"
        //    74: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: pop            
        //    78: aload_1        
        //    79: aload_2        
        //    80: aload_0        
        //    81: ldc             "gather_zeroconf_services!"
        //    83: ldc             "method__4$RUBY$gather_zeroconf_services_b_"
        //    85: ldc             ",0,0,-1"
        //    87: iconst_0       
        //    88: ldc             "./lib//lister/runner/measurements/lan_services.rb"
        //    90: ldc             16
        //    92: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    95: ldc             "NONE"
        //    97: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   100: pop            
        //   101: aload_1        
        //   102: aload_2        
        //   103: aload_0        
        //   104: ldc             "gather_upnp_services!"
        //   106: ldc             "method__5$RUBY$gather_upnp_services_b_"
        //   108: ldc             ",0,0,-1"
        //   110: iconst_0       
        //   111: ldc             "./lib//lister/runner/measurements/lan_services.rb"
        //   113: ldc             20
        //   115: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   118: ldc             "NONE"
        //   120: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   123: pop            
        //   124: aload_1        
        //   125: aload_2        
        //   126: aload_0        
        //   127: ldc_w           "upnp_devices_decent"
        //   130: ldc_w           "method__6$RUBY$upnp_devices_decent"
        //   133: ldc_w           "list,1,0,-1"
        //   136: iconst_1       
        //   137: ldc             "./lib//lister/runner/measurements/lan_services.rb"
        //   139: ldc_w           24
        //   142: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   145: ldc_w           "qlist"
        //   148: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   151: pop            
        //   152: aload_1        
        //   153: aload_2        
        //   154: aload_0        
        //   155: ldc_w           "sha1"
        //   158: ldc_w           "method__7$RUBY$sha1"
        //   161: ldc_w           "str,1,0,-1"
        //   164: iconst_1       
        //   165: ldc             "./lib//lister/runner/measurements/lan_services.rb"
        //   167: ldc_w           33
        //   170: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   173: ldc_w           "qstr"
        //   176: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   179: pop            
        //   180: aload_1        
        //   181: aload_2        
        //   182: aload_0        
        //   183: ldc_w           "gather_upnp_services"
        //   186: ldc_w           "method__8$RUBY$gather_upnp_services"
        //   189: ldc_w           "devices;ret,0,0,-1"
        //   192: iconst_0       
        //   193: ldc             "./lib//lister/runner/measurements/lan_services.rb"
        //   195: ldc_w           37
        //   198: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   201: ldc             "NONE"
        //   203: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   206: pop            
        //   207: aload_1        
        //   208: aload_2        
        //   209: aload_0        
        //   210: ldc_w           "gather_zeroconf_services"
        //   213: ldc_w           "method__9$RUBY$gather_zeroconf_services"
        //   216: ldc             ",0,0,-1"
        //   218: iconst_0       
        //   219: ldc             "./lib//lister/runner/measurements/lan_services.rb"
        //   221: ldc_w           63
        //   224: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   227: ldc             "NONE"
        //   229: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   232: pop            
        //   233: aload_1        
        //   234: aload_2        
        //   235: aload_0        
        //   236: ldc_w           "gather_zeroconf_services_apple_soft"
        //   239: ldc_w           "method__11$RUBY$gather_zeroconf_services_apple_soft"
        //   242: ldc_w           "counter;ret,0,0,-1"
        //   245: iconst_0       
        //   246: ldc             "./lib//lister/runner/measurements/lan_services.rb"
        //   248: ldc_w           83
        //   251: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   254: ldc             "NONE"
        //   256: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   259: pop            
        //   260: aload_1        
        //   261: aload_2        
        //   262: aload_0        
        //   263: ldc_w           "gather_zeroconf_services_linux"
        //   266: ldc_w           "method__12$RUBY$gather_zeroconf_services_linux"
        //   269: ldc_w           "service;services;ret,0,0,-1"
        //   272: iconst_0       
        //   273: ldc             "./lib//lister/runner/measurements/lan_services.rb"
        //   275: ldc_w           101
        //   278: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   281: ldc             "NONE"
        //   283: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   286: aload_1        
        //   287: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   290: goto            298
        //   293: aload_1        
        //   294: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   297: athrow         
        //   298: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  37     286    293    298    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "execute", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$execute(final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 file_6035E32C899C935D26F1C23F4966BDE32AD2EC69, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite5().call(threadContext, rubyObject, rubyObject);
        file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite6().call(threadContext, rubyObject, rubyObject, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getFixnum0(threadContext.runtime, 50));
        return file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite7().call(threadContext, rubyObject, rubyObject);
    }
    
    @JRubyMethod(name = "gather_zeroconf_services!", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$gather_zeroconf_services_b_(final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 file_6035E32C899C935D26F1C23F4966BDE32AD2EC69, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        return file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.setVariable0(threadContext.runtime, "@zeroconf", object, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite8().call(threadContext, object, object));
    }
    
    @JRubyMethod(name = "gather_upnp_services!", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$gather_upnp_services_b_(final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 file_6035E32C899C935D26F1C23F4966BDE32AD2EC69, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        return file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.setVariable1(threadContext.runtime, "@upnp", object, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite9().call(threadContext, object, object));
    }
    
    @JRubyMethod(name = "upnp_devices_decent", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$upnp_devices_decent(final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    17: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    20: aload_1        
        //    21: aload_2        
        //    22: aload           locals
        //    24: aload_1        
        //    25: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    28: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_0        
        //    34: aload_1        
        //    35: ldc_w           "block_0$RUBY$upnp_devices_decent,1,dev,false,2,./lib//lister/runner/measurements/lan_services.rb,25,false"
        //    38: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getBlockBody1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    41: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    44: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     34      5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_0$RUBY$upnp_devices_decent(final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    35: aload_1        
        //    36: invokevirtual   org/jruby/runtime/ThreadContext.getFrameBlock:()Lorg/jruby/runtime/Block;
        //    39: aload_1        
        //    40: aload           locals
        //    42: aload_1        
        //    43: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    46: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: pop            
        //    53: aload_0        
        //    54: bipush          11
        //    56: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    59: aload_1        
        //    60: aload_2        
        //    61: aload_2        
        //    62: aload_0        
        //    63: bipush          12
        //    65: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    68: aload_1        
        //    69: aload_2        
        //    70: aload           locals
        //    72: aload_1        
        //    73: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: dup            
        //    83: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    88: ifne            99
        //    91: pop            
        //    92: aload_1        
        //    93: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    96: invokevirtual   org/jruby/Ruby.newArray:()Lorg/jruby/RubyArray;
        //    99: aload_1        
        //   100: aload_2        
        //   101: aload_0        
        //   102: aload_1        
        //   103: ldc             "block_1$RUBY$upnp_devices_decent,1,subdev,false,2,./lib//lister/runner/measurements/lan_services.rb,27,true"
        //   105: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   108: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   111: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   114: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     80      5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_1$RUBY$upnp_devices_decent(final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    25: aload_1        
        //    26: invokevirtual   org/jruby/runtime/ThreadContext.getFrameBlock:()Lorg/jruby/runtime/Block;
        //    29: aload_1        
        //    30: aload           subdev
        //    32: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    35: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  ---------------------------------------
        //  25     11      9     subdev  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "sha1", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$sha1(final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 file_6035E32C899C935D26F1C23F4966BDE32AD2EC69, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(13).call(threadContext, rubyObject, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getConstantFrom2(RuntimeHelpers.checkIsModule(file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getConstant1(threadContext, "Digest")), threadContext, "SHA1"), rubyObject2);
    }
    
    @JRubyMethod(name = "gather_upnp_services", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$gather_upnp_services(final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 file_6035E32C899C935D26F1C23F4966BDE32AD2EC69, final ThreadContext context, final IRubyObject self, final Block block) {
        final DynamicScope locals = context.getCurrentScope();
        file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(14).call(context, self, self, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getString4(context.runtime, 32));
        final DynamicScope dynamicScope = locals;
        IRubyObject valueZeroDepthZero;
        if (!(valueZeroDepthZero = file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(15).call(context, self, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getConstant3(context, "Discovery"), file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getFixnum1(context.runtime, 10000), file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getString5(context.runtime, 32))).isTrue()) {
            valueZeroDepthZero = context.runtime.newArray();
        }
        dynamicScope.setValueZeroDepthZero(valueZeroDepthZero);
        file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(16).call(context, self, self, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getFixnum2(context.runtime, 80));
        locals.setValueOneDepthZero(RubyHash.newHash(context.runtime));
        file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(17).callIter(context, self, locals.getValueZeroDepthZeroOrNil(context.nil), RuntimeHelpers.createBlock(context, self, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getBlockBody4(context, "block_2$RUBY$gather_upnp_services,1,root;h,false,2,./lib//lister/runner/measurements/lan_services.rb,45,false")));
        return file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(38).call(context, self, locals.getValueOneDepthZeroOrNil(context.nil));
    }
    
    public static IRubyObject block_2$RUBY$gather_upnp_services(final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    42: bipush          18
        //    44: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    47: aload_1        
        //    48: aload_2        
        //    49: aload           locals
        //    51: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    54: aload_1        
        //    55: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    61: aload_0        
        //    62: bipush          19
        //    64: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    67: aload_1        
        //    68: aload_2        
        //    69: aload           locals
        //    71: aload_1        
        //    72: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    78: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    81: dup2           
        //    82: astore          9
        //    84: astore          10
        //    86: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: dup            
        //    90: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    95: ifne            295
        //    98: pop            
        //    99: aload_1        
        //   100: aload_2        
        //   101: aload           10
        //   103: aload           9
        //   105: aload_1        
        //   106: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   109: aload_0        
        //   110: aload_1        
        //   111: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   114: ldc_w           "name_sha1"
        //   117: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   120: aload_0        
        //   121: bipush          20
        //   123: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   126: aload_1        
        //   127: aload_2        
        //   128: aload_2        
        //   129: aload_0        
        //   130: bipush          21
        //   132: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   135: aload_1        
        //   136: aload_2        
        //   137: aload           locals
        //   139: aload_1        
        //   140: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   143: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   146: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   149: dup            
        //   150: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   155: ifne            169
        //   158: pop            
        //   159: aload_0        
        //   160: aload_1        
        //   161: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   164: bipush          32
        //   166: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getString6:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   169: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   172: aload_0        
        //   173: aload_1        
        //   174: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   177: ldc_w           "type"
        //   180: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   183: aload_0        
        //   184: bipush          22
        //   186: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   189: aload_1        
        //   190: aload_2        
        //   191: aload           locals
        //   193: aload_1        
        //   194: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   197: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   200: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   203: aload_0        
        //   204: aload_1        
        //   205: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   208: ldc_w           "services"
        //   211: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   214: aload_1        
        //   215: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   218: invokevirtual   org/jruby/Ruby.newArray:()Lorg/jruby/RubyArray;
        //   221: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructHash:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyHash;
        //   224: dup            
        //   225: aload_1        
        //   226: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   229: aload_0        
        //   230: aload_1        
        //   231: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   234: ldc_w           "ip"
        //   237: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   240: aload_0        
        //   241: bipush          23
        //   243: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   246: aload_1        
        //   247: aload_2        
        //   248: aload_2        
        //   249: aload_0        
        //   250: bipush          24
        //   252: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   255: aload_1        
        //   256: aload_2        
        //   257: aload_0        
        //   258: bipush          25
        //   260: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   263: aload_1        
        //   264: aload_2        
        //   265: aload           locals
        //   267: aload_1        
        //   268: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   271: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   274: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   277: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   280: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   283: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   286: aload_0        
        //   287: bipush          26
        //   289: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   292: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.opElementAsgnWithOrPartTwoOneArg:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   295: pop            
        //   296: aload           locals
        //   298: aload_0        
        //   299: bipush          27
        //   301: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   304: aload_1        
        //   305: aload_2        
        //   306: aload           locals
        //   308: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   311: aload_1        
        //   312: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   315: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   318: aload_0        
        //   319: bipush          28
        //   321: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   324: aload_1        
        //   325: aload_2        
        //   326: aload           locals
        //   328: aload_1        
        //   329: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   332: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   335: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   338: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   341: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   344: pop            
        //   345: aload_0        
        //   346: bipush          29
        //   348: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   351: aload_1        
        //   352: aload_2        
        //   353: aload_2        
        //   354: aload_0        
        //   355: bipush          30
        //   357: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   360: aload_1        
        //   361: aload_2        
        //   362: aload           locals
        //   364: aload_1        
        //   365: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   368: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   371: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   374: dup            
        //   375: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   380: ifne            391
        //   383: pop            
        //   384: aload_1        
        //   385: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   388: invokevirtual   org/jruby/Ruby.newArray:()Lorg/jruby/RubyArray;
        //   391: aload_1        
        //   392: aload_2        
        //   393: aload_0        
        //   394: aload_1        
        //   395: ldc_w           "block_3$RUBY$gather_upnp_services,1,dev,false,2,./lib//lister/runner/measurements/lan_services.rb,53,false"
        //   398: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getBlockBody3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   401: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   404: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   407: areturn        
        //   408: pop            
        //   409: goto            41
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  41     367     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  41     408    408    412    Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_3$RUBY$gather_upnp_services(final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: bipush          31
        //    38: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_0        
        //    44: bipush          32
        //    46: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload           locals
        //    53: aload_1        
        //    54: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: aload_1        
        //    64: aload_2        
        //    65: aload_0        
        //    66: aload_1        
        //    67: ldc_w           "block_4$RUBY$gather_upnp_services,1,s;type,false,2,./lib//lister/runner/measurements/lan_services.rb,54,false"
        //    70: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getBlockBody2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    73: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    76: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     45      5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_4$RUBY$gather_upnp_services(final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    44: bipush          33
        //    46: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload           locals
        //    53: aload_1        
        //    54: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: pop            
        //    67: aload_0        
        //    68: bipush          34
        //    70: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    73: aload_1        
        //    74: aload_2        
        //    75: aload_0        
        //    76: bipush          35
        //    78: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    81: aload_1        
        //    82: aload_2        
        //    83: aload           locals
        //    85: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    88: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    91: aload_1        
        //    92: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    95: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    98: aload_0        
        //    99: aload_1        
        //   100: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   103: ldc_w           "services"
        //   106: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   109: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   112: aload           locals
        //   114: aload_1        
        //   115: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   118: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   121: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   124: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   129: ifeq            139
        //   132: aload_1        
        //   133: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   136: goto            196
        //   139: aload_0        
        //   140: bipush          36
        //   142: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   145: aload_1        
        //   146: aload_2        
        //   147: aload_0        
        //   148: bipush          37
        //   150: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   153: aload_1        
        //   154: aload_2        
        //   155: aload           locals
        //   157: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   160: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   163: aload_1        
        //   164: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   167: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   170: aload_0        
        //   171: aload_1        
        //   172: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   175: ldc_w           "services"
        //   178: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   181: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   184: aload           locals
        //   186: aload_1        
        //   187: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   190: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   193: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   196: areturn        
        //   197: pop            
        //   198: goto            41
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  41     156     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  41     197    197    201    Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "gather_zeroconf_services", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$gather_zeroconf_services(final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 file_6035E32C899C935D26F1C23F4966BDE32AD2EC69, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(39).call(threadContext, rubyObject, rubyObject, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getString7(threadContext.runtime, 32));
        IRubyObject rubyObject2;
        if (file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(40).call(threadContext, rubyObject, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(41).call(threadContext, rubyObject, rubyObject)).isTrue()) {
            rubyObject2 = (file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(42).call(threadContext, rubyObject, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(43).call(threadContext, rubyObject, rubyObject, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getString8(threadContext.runtime, 0))).isTrue() ? file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getString9(threadContext.runtime, 32) : file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(44).call(threadContext, rubyObject, rubyObject));
        }
        else {
            IRubyObject rubyObject3;
            if (!(rubyObject3 = file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(45).call(threadContext, rubyObject, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(46).call(threadContext, rubyObject, rubyObject))).isTrue()) {
                rubyObject3 = file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(47).call(threadContext, rubyObject, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(48).call(threadContext, rubyObject, rubyObject));
            }
            rubyObject2 = (rubyObject3.isTrue() ? chained_10_rescue_1$RUBY$SYNTHETICgather_zeroconf_services(file_6035E32C899C935D26F1C23F4966BDE32AD2EC69, threadContext, rubyObject, block) : file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getString9(threadContext.runtime, 32));
        }
        return rubyObject2;
    }
    
    public static IRubyObject chained_10_rescue_1$RUBY$SYNTHETICgather_zeroconf_services(final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 file_6035E32C899C935D26F1C23F4966BDE32AD2EC69, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        threadContext.getCurrentScope();
        final IRubyObject errorInfo = threadContext.getErrorInfo();
        IRubyObject rubyObject2 = null;
        Label_0116: {
            try {
                try {
                    file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(49).call(threadContext, rubyObject, rubyObject, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getString(threadContext.runtime, 10, 32));
                    rubyObject2 = file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(50).call(threadContext, rubyObject, rubyObject);
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable currentThrowable) {
                    if (RuntimeHelpers.isJavaExceptionHandled(currentThrowable, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getConstant4(threadContext, "NameError"), file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getConstant5(threadContext, "LoadError"), threadContext).isTrue()) {
                        rubyObject2 = file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getString9(threadContext.runtime, 32);
                        RuntimeHelpers.clearErrorInfo(threadContext);
                        break Label_0116;
                    }
                    throw currentThrowable;
                }
            }
            catch (JumpException.FlowControlException ex2) {
                threadContext.setErrorInfo(errorInfo);
                throw ex2;
            }
        }
        threadContext.setErrorInfo(errorInfo);
        return rubyObject2;
    }
    
    @JRubyMethod(name = "gather_zeroconf_services_apple_soft", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__11$RUBY$gather_zeroconf_services_apple_soft(final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 file_6035E32C899C935D26F1C23F4966BDE32AD2EC69, final ThreadContext threadContext, final IRubyObject self, final Block currentBlock) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(51).call(threadContext, self, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getConstantFrom7(RuntimeHelpers.checkIsModule(file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getConstant6(threadContext, "Darwin")), threadContext, "ZeroconfCounter")));
        if (file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(52).call(threadContext, self, locals.getValueZeroDepthZeroOrNil(threadContext.nil), file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getFixnum3(threadContext.runtime, 10)).isTrue()) {
            locals.setValueOneDepthZero(threadContext.runtime.newArray());
            file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(53).callIter(threadContext, self, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(54).call(threadContext, self, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(55).call(threadContext, self, locals.getValueZeroDepthZeroOrNil(threadContext.nil)), RuntimeHelpers.getBlockFromBlockPassBody(file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getSymbol4(threadContext.runtime, "host"), currentBlock)), RuntimeHelpers.createBlock(threadContext, self, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getBlockBody6(threadContext, "block_5$RUBY$gather_zeroconf_services_apple_soft,2,host;services;host_info,true,1,./lib//lister/runner/measurements/lan_services.rb,89,false")));
            return locals.getValueOneDepthZeroOrNil(threadContext.nil);
        }
        return file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getString(threadContext.runtime, 11, 32);
    }
    
    public static IRubyObject block_5$RUBY$gather_zeroconf_services_apple_soft(final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    39: aload_1        
        //    40: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    43: iconst_1       
        //    44: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.ensureMultipleAssignableRubyArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;Z)Lorg/jruby/RubyArray;
        //    47: astore          9
        //    49: aload           9
        //    51: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilZero:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: aload           5
        //    56: swap           
        //    57: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: pop            
        //    61: aload           9
        //    63: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilOne:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: aload           5
        //    68: swap           
        //    69: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    72: pop            
        //    73: aload           9
        //    75: pop            
        //    76: pop            
        //    77: aload           locals
        //    79: aload_1        
        //    80: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    83: invokestatic    org/jruby/RubyHash.newHash:(Lorg/jruby/Ruby;)Lorg/jruby/RubyHash;
        //    86: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: pop            
        //    90: aload_0        
        //    91: bipush          56
        //    93: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    96: aload_1        
        //    97: aload_2        
        //    98: aload_0        
        //    99: bipush          57
        //   101: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   104: aload_1        
        //   105: aload_2        
        //   106: aload           locals
        //   108: aload_1        
        //   109: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   112: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   115: aload_0        
        //   116: aload_1        
        //   117: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   120: ldc_w           "ip"
        //   123: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   126: aload_1        
        //   127: invokevirtual   org/jruby/runtime/ThreadContext.getFrameBlock:()Lorg/jruby/runtime/Block;
        //   130: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.getBlockFromBlockPassBody:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/Block;
        //   133: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   136: aload_1        
        //   137: aload_2        
        //   138: aload_0        
        //   139: aload_1        
        //   140: ldc_w           "block_6$RUBY$gather_zeroconf_services_apple_soft,2,ip;group,true,1,./lib//lister/runner/measurements/lan_services.rb,92,true"
        //   143: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getBlockBody5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   146: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   149: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   152: pop            
        //   153: aload_0        
        //   154: bipush          65
        //   156: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   159: aload_1        
        //   160: aload_2        
        //   161: aload           locals
        //   163: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   166: aload_1        
        //   167: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   170: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   173: aload           locals
        //   175: aload_1        
        //   176: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   179: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   182: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   185: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  77     109     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_6$RUBY$gather_zeroconf_services_apple_soft(final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    56: aload           5
        //    58: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    61: aload_1        
        //    62: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    68: dup            
        //    69: aload_2        
        //    70: aload_0        
        //    71: bipush          58
        //    73: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    76: aload_0        
        //    77: bipush          59
        //    79: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    82: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //    85: aload_0        
        //    86: aload_1        
        //    87: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    90: bipush          12
        //    92: bipush          32
        //    94: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    97: aload_0        
        //    98: bipush          60
        //   100: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   103: aload_1        
        //   104: aload_2        
        //   105: aload_2        
        //   106: aload           ip
        //   108: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   111: aload_1        
        //   112: aload_2        
        //   113: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   116: pop            
        //   117: aload           5
        //   119: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   122: aload_1        
        //   123: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   126: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   129: dup            
        //   130: aload_2        
        //   131: aload_0        
        //   132: bipush          61
        //   134: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   137: aload_0        
        //   138: bipush          62
        //   140: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   143: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   146: aload_0        
        //   147: aload_1        
        //   148: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   151: bipush          13
        //   153: bipush          32
        //   155: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   158: aload_0        
        //   159: bipush          63
        //   161: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   164: aload_1        
        //   165: aload_2        
        //   166: aload_0        
        //   167: bipush          64
        //   169: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   172: aload_1        
        //   173: aload_2        
        //   174: aload           group
        //   176: aload_0        
        //   177: aload_1        
        //   178: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   181: ldc_w           "reg_type"
        //   184: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   187: aload_1        
        //   188: invokevirtual   org/jruby/runtime/ThreadContext.getFrameBlock:()Lorg/jruby/runtime/Block;
        //   191: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.getBlockFromBlockPassBody:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/Block;
        //   194: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   197: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   200: aload_1        
        //   201: aload_2        
        //   202: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   205: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name   Signature
        //  -----  ------  ----  -----  ---------------------------------------
        //  56     150     9     ip     Lorg/jruby/runtime/builtin/IRubyObject;
        //  56     150     10    group  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "gather_zeroconf_services_linux", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__12$RUBY$gather_zeroconf_services_linux(final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 file_6035E32C899C935D26F1C23F4966BDE32AD2EC69, final ThreadContext context, final IRubyObject rubyObject, final Block currentBlock) {
        final DynamicScope locals = context.getCurrentScope();
        locals.setValueZeroDepthZero(file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(66).call(context, rubyObject, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getConstant8(context, "Struct"), file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getSymbol1(context.runtime, "type"), file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getSymbol6(context.runtime, "hostname"), file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getSymbol3(context.runtime, "ip")));
        locals.setValueOneDepthZero(context.runtime.newArray());
        file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(67).callIter(context, rubyObject, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(68).call(context, rubyObject, rubyObject, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getString(context.runtime, 14, 0)), RuntimeHelpers.createBlock(context, rubyObject, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getBlockBody7(context, "block_7$RUBY$gather_zeroconf_services_linux,1,line;ary;mode;iface;ip_ver;service_name;reg_type;domain;hostname;ip;port;txt,false,2,./lib//lister/runner/measurements/lan_services.rb,104,false")));
        locals.setValueTwoDepthZero(context.runtime.newArray());
        file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(72).callIter(context, rubyObject, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite(73).call(context, rubyObject, locals.getValueOneDepthZeroOrNil(context.nil), RuntimeHelpers.getBlockFromBlockPassBody(file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getSymbol6(context.runtime, "hostname"), currentBlock)), RuntimeHelpers.createBlock(context, rubyObject, file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getBlockBody9(context, "block_8$RUBY$gather_zeroconf_services_linux,2,pair;idx;host_info,true,1,./lib//lister/runner/measurements/lan_services.rb,112,false")));
        return locals.getValueTwoDepthZeroOrNil(context.nil);
    }
    
    public static IRubyObject block_7$RUBY$gather_zeroconf_services_linux(final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    16: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    19: aload           5
        //    21: swap           
        //    22: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    25: aload           5
        //    27: swap           
        //    28: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    31: aload           5
        //    33: swap           
        //    34: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    37: aload           5
        //    39: swap           
        //    40: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: pop            
        //    44: aload_1        
        //    45: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    48: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.fillNil:([Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;)V
        //    51: aload_1        
        //    52: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    55: aload           4
        //    57: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: aload_3        
        //    61: aload           5
        //    63: swap           
        //    64: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: pop            
        //    68: pop            
        //    69: aload           locals
        //    71: aload_0        
        //    72: bipush          69
        //    74: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    77: aload_1        
        //    78: aload_2        
        //    79: aload           locals
        //    81: aload_1        
        //    82: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    85: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: aload_0        
        //    89: aload_1        
        //    90: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    93: bipush          15
        //    95: bipush          32
        //    97: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   100: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   103: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   106: pop            
        //   107: aload           locals
        //   109: aload_1        
        //   110: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   113: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   116: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.splatValue:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   119: aload_1        
        //   120: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   123: iconst_1       
        //   124: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.ensureMultipleAssignableRubyArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;Z)Lorg/jruby/RubyArray;
        //   127: astore          9
        //   129: aload           9
        //   131: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilZero:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   134: aload           locals
        //   136: swap           
        //   137: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   140: pop            
        //   141: aload           9
        //   143: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilOne:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   146: aload           locals
        //   148: swap           
        //   149: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   152: pop            
        //   153: aload           9
        //   155: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilTwo:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   158: aload           6
        //   160: swap           
        //   161: iconst_4       
        //   162: swap           
        //   163: aastore        
        //   164: aload           9
        //   166: iconst_3       
        //   167: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNil:(Lorg/jruby/RubyArray;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   170: aload           6
        //   172: swap           
        //   173: iconst_5       
        //   174: swap           
        //   175: aastore        
        //   176: aload           9
        //   178: iconst_4       
        //   179: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNil:(Lorg/jruby/RubyArray;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   182: aload           6
        //   184: swap           
        //   185: bipush          6
        //   187: swap           
        //   188: aastore        
        //   189: aload           9
        //   191: iconst_5       
        //   192: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNil:(Lorg/jruby/RubyArray;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   195: aload           6
        //   197: swap           
        //   198: bipush          7
        //   200: swap           
        //   201: aastore        
        //   202: aload           9
        //   204: bipush          6
        //   206: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNil:(Lorg/jruby/RubyArray;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   209: aload           6
        //   211: swap           
        //   212: bipush          8
        //   214: swap           
        //   215: aastore        
        //   216: aload           9
        //   218: bipush          7
        //   220: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNil:(Lorg/jruby/RubyArray;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   223: aload           6
        //   225: swap           
        //   226: bipush          9
        //   228: swap           
        //   229: aastore        
        //   230: aload           9
        //   232: bipush          8
        //   234: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNil:(Lorg/jruby/RubyArray;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   237: aload           6
        //   239: swap           
        //   240: bipush          10
        //   242: swap           
        //   243: aastore        
        //   244: aload           9
        //   246: bipush          9
        //   248: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNil:(Lorg/jruby/RubyArray;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   251: aload           6
        //   253: swap           
        //   254: bipush          11
        //   256: swap           
        //   257: aastore        
        //   258: aload           9
        //   260: pop            
        //   261: aload           6
        //   263: bipush          8
        //   265: aaload         
        //   266: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   271: ifeq            338
        //   274: aload_0        
        //   275: bipush          70
        //   277: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   280: aload_1        
        //   281: aload_2        
        //   282: aload           locals
        //   284: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   287: aload_1        
        //   288: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   291: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   294: aload_0        
        //   295: bipush          71
        //   297: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   300: aload_1        
        //   301: aload_2        
        //   302: aload           locals
        //   304: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   307: aload_1        
        //   308: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   311: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   314: aload           6
        //   316: bipush          6
        //   318: aaload         
        //   319: aload           6
        //   321: bipush          8
        //   323: aaload         
        //   324: aload           6
        //   326: bipush          9
        //   328: aaload         
        //   329: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   332: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   335: goto            342
        //   338: aload_1        
        //   339: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   342: areturn        
        //   343: pop            
        //   344: goto            69
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  69     274     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  69     343    343    347    Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_8$RUBY$gather_zeroconf_services_linux(final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    39: aload_1        
        //    40: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    43: iconst_1       
        //    44: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.ensureMultipleAssignableRubyArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;Z)Lorg/jruby/RubyArray;
        //    47: astore          9
        //    49: aload           9
        //    51: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilZero:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: aload           5
        //    56: swap           
        //    57: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: pop            
        //    61: aload           9
        //    63: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilOne:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: aload           5
        //    68: swap           
        //    69: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    72: pop            
        //    73: aload           9
        //    75: pop            
        //    76: pop            
        //    77: aload           locals
        //    79: aload_1        
        //    80: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    83: invokestatic    org/jruby/RubyHash.newHash:(Lorg/jruby/Ruby;)Lorg/jruby/RubyHash;
        //    86: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: pop            
        //    90: aload_0        
        //    91: bipush          74
        //    93: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    96: aload_1        
        //    97: aload_2        
        //    98: aload_0        
        //    99: bipush          75
        //   101: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   104: aload_1        
        //   105: aload_2        
        //   106: aload           locals
        //   108: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   111: aload_1        
        //   112: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   115: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   118: aload_0        
        //   119: aload_1        
        //   120: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   123: ldc_w           "ip"
        //   126: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   129: aload_1        
        //   130: invokevirtual   org/jruby/runtime/ThreadContext.getFrameBlock:()Lorg/jruby/runtime/Block;
        //   133: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.getBlockFromBlockPassBody:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/Block;
        //   136: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   139: aload_1        
        //   140: aload_2        
        //   141: aload_0        
        //   142: aload_1        
        //   143: ldc_w           "block_9$RUBY$gather_zeroconf_services_linux,2,ip;group,true,1,./lib//lister/runner/measurements/lan_services.rb,114,true"
        //   146: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getBlockBody8:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   149: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   152: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   155: pop            
        //   156: aload_0        
        //   157: bipush          83
        //   159: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   162: aload_1        
        //   163: aload_2        
        //   164: aload           locals
        //   166: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   169: aload_1        
        //   170: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   173: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   176: aload           locals
        //   178: aload_1        
        //   179: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   182: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   185: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   188: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  77     112     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_9$RUBY$gather_zeroconf_services_linux(final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    56: aload           5
        //    58: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    61: aload_1        
        //    62: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    68: dup            
        //    69: aload_2        
        //    70: aload_0        
        //    71: bipush          76
        //    73: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    76: aload_0        
        //    77: bipush          77
        //    79: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    82: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //    85: aload_0        
        //    86: aload_1        
        //    87: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    90: bipush          12
        //    92: bipush          32
        //    94: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    97: aload_0        
        //    98: bipush          78
        //   100: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   103: aload_1        
        //   104: aload_2        
        //   105: aload_2        
        //   106: aload           ip
        //   108: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   111: aload_1        
        //   112: aload_2        
        //   113: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   116: pop            
        //   117: aload           5
        //   119: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   122: aload_1        
        //   123: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   126: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   129: dup            
        //   130: aload_2        
        //   131: aload_0        
        //   132: bipush          79
        //   134: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   137: aload_0        
        //   138: bipush          80
        //   140: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   143: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   146: aload_0        
        //   147: aload_1        
        //   148: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   151: bipush          13
        //   153: bipush          32
        //   155: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   158: aload_0        
        //   159: bipush          81
        //   161: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   164: aload_1        
        //   165: aload_2        
        //   166: aload_0        
        //   167: bipush          82
        //   169: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   172: aload_1        
        //   173: aload_2        
        //   174: aload           group
        //   176: aload_0        
        //   177: aload_1        
        //   178: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   181: ldc_w           "type"
        //   184: invokevirtual   ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   187: aload_1        
        //   188: invokevirtual   org/jruby/runtime/ThreadContext.getFrameBlock:()Lorg/jruby/runtime/Block;
        //   191: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.getBlockFromBlockPassBody:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/Block;
        //   194: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   197: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   200: aload_1        
        //   201: aload_2        
        //   202: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   205: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name   Signature
        //  -----  ------  ----  -----  ---------------------------------------
        //  56     150     9     ip     Lorg/jruby/runtime/builtin/IRubyObject;
        //  56     150     10    group  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_2$RUBY$NetworkServices(final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 file_6035E32C899C935D26F1C23F4966BDE32AD2EC69, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_2$RUBY$NetworkServices(file_6035E32C899C935D26F1C23F4966BDE32AD2EC69, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 file_6035E32C899C935D26F1C23F4966BDE32AD2EC69, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Measurements(file_6035E32C899C935D26F1C23F4966BDE32AD2EC69, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 file_6035E32C899C935D26F1C23F4966BDE32AD2EC69, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_6035E32C899C935D26F1C23F4966BDE32AD2EC69, threadContext, rubyObject, block);
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
        final FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69 file_6035E32C899C935D26F1C23F4966BDE32AD2EC69 = new FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69();
        final String string = FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.class.getClassLoader().getResource("ruby/jit/FILE_6035E32C899C935D26F1C23F4966BDE32AD2EC69.class").toString();
        file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_6035E32C899C935D26F1C23F4966BDE32AD2EC69.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
