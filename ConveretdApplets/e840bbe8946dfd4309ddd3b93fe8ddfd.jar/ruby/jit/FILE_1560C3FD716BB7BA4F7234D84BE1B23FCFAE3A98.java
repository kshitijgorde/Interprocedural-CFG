// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.RubyBoolean;
import org.jruby.runtime.CallSite;
import org.jruby.RubyFixnum;
import org.jruby.runtime.Arity;
import org.jruby.RubyHash;
import org.jruby.runtime.DynamicScope;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 extends AbstractScript
{
    public FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98() {
        this.filename = "./lib//lister/utils/net_config.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffextend\uffffF\uffffeach\uffffN\ufffflines\uffffN\uffffspawn\uffffF\uffffget_iface_macaddresses_cmd\uffffV\uffff===\uffffN\uffffsplit\uffffN\uffffchomp\uffffN\ufffftr\uffffN\uffffshift\uffffN\uffffloop\uffffF\uffff==\uffffN\uffffshift\uffffN\uffffempty?\uffffN\uffffshift\uffffN\uffff<<\uffffN\uffff===\uffffN\ufffflast\uffffN\uffffsplit\uffffN\uffffchomp\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffff===\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\ufffflast\uffffN\uffffsplit\uffffN\uffffchomp\uffffN\uffff===\uffffN\uffffsplit\uffffN\uffffchomp\uffffN\ufffffirst\uffffN\uffffsplit\uffffN\uffff[]\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffff===\uffffN\uffffsplit\uffffN\uffffchomp\uffffN\uffff[]\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffeach\uffffN\ufffflines\uffffN\uffffspawn\uffffF\uffffget_sysctl_cmd\uffffV\uffffsplit\uffffN\uffffsub\uffffN\uffffsub\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffto_i\uffffN\uffffservers\uffffN\uffffinject\uffffN\uffffmerge\uffffN\uffffsend\uffffN\uffffeach\uffffN\ufffflines\uffffN\uffffspawn\uffffF\uffffget_iface_macaddresses_cmd\uffffV\uffff===\uffffN\uffffsplit\uffffN\uffffchomp\uffffN\uffffshift\uffffN\uffffloop\uffffF\uffffshift\uffffN\uffffnil?\uffffN\uffff==\uffffN\uffff==\uffffN\uffffsize\uffffN\uffffsplit\uffffN\uffffsplit\uffffN\uffff==\uffffN\uffffshift\uffffN\uffff<<\uffffN\uffff===\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffff[]\uffffN\uffffsplit\uffffN\uffff===\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\ufffflast\uffffN\uffffsplit\uffffN\uffff[]\uffffN\uffffsplit\uffffN\uffff===\uffffN\ufffffind\uffffN\uffffsplit\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\ufffflast\uffffN\uffffsplit\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffff[]\uffffN\uffffsplit\uffffN\uffffeach\uffffN\ufffflines\uffffN\uffffspawn\uffffF\uffffget_sysctl_cmd\uffffV\uffffsplit\uffffN\ufffffirst\uffffN\ufffflast\uffffN\uffffsub\uffffN\uffffsub\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffto_i\uffffN\uffffservers\uffffN\uffffinject\uffffN\uffffmerge\uffffN\uffffsend\uffffN\uffffinclude\uffffF\uffffeach\uffffN\uffffall\uffffN\ufffftranscode\uffffF\uffffdescription\uffffN\ufffftype_str\uffffN\uffffmac_addr\uffffN\uffffstatus\uffffN\uffffmtu\uffffN\uffffeach\uffffN\uffffip_addresses\uffffV\uffff==\uffffN\uffff[]\uffffN\uffffindex\uffffN\uffff[]\uffffN\ufffffirst\uffffN\uffffunpack\uffffN\uffffpack\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffto_s\uffffN\uffffnew\uffffN\uffff<<\uffffN\uffffnew\uffffN\uffffwrite_ulong\uffffN\uffffGetIpAddrTable\uffffN\uffffnew\uffffN\uffffread_ulong\uffffN\uffffwrite_ulong\uffffN\uffffsize\uffffN\uffffGetIpAddrTable\uffffN\uffffnew\uffffN\uffffto_ptr\uffffN\uffff[]\uffffN\uffffmap\uffffN\ufffftimes\uffffN\uffff[]\uffffN\uffff+\uffffN\uffffaddress\uffffN\uffff*\uffffN\uffffsize\uffffN\uffffnew\uffffN\uffffnew\uffffN\uffffnew\uffffN\uffffGetTcpStatistics\uffffN\uffffnew\uffffN\uffffalgo\uffffN\uffff[]\uffffN\uffff[]\uffffN\uffff[]\uffffN\uffffnew\uffffN\uffffwrite_ulong\uffffN\uffffGetNetworkParams\uffffN\ufffflogger\uffffV\uffffdebug\uffffN\ufffflogger\uffffV\uffffread_ulong\uffffN\uffff==\uffffN\uffffread_ulong\uffffN\uffffnew\uffffN\uffffnew\uffffN\uffffnew\uffffN\uffffread_ulong\uffffN\uffffGetNetworkParams\uffffN\uffffnew\uffffN\uffffdefault\uffffN\uffffmtu\uffffN\uffffnew\uffffN\uffffGetIpStatistics\uffffN\uffffnew\uffffN\uffffnode_type\uffffN\uffffrouting_enabled?\uffffN\uffffproxy_enabled?\uffffN\uffffdns_server_enabled?\uffffN\uffff[]\uffffN\ufffffree\uffffN\ufffffree\uffffN\ufffffree\uffffN\uffffservers\uffffN\uffffinject\uffffN\uffffmerge\uffffN\uffffsend\uffffN\uffffon_windows?\uffffN\uffffextend\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffon_linux?\uffffN\uffffextend\uffffF\uffffon_mac?\uffffN\uffffextend\uffffF\uffff\u0006\u001e\u0000\u0000D\u000b\u0000\u0000\u0000\u000e\u0000\u0000$\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(30, " (", this.getEncoding0());
        this.setByteList(9, "mtu", this.getEncoding0());
        this.setByteList(8, "", this.getEncoding0());
        this.setByteList(18, "^\\S+", this.getEncoding0());
        this.setByteList(2, "lister/utils/dns", this.getEncoding0());
        this.setByteList(32, "non-available", this.getEncoding0());
        this.setByteList(1, "lister/utils/platform", this.getEncoding0());
        this.setByteList(25, "net.ipv4.", this.getEncoding0());
        this.setByteList(3, "lister/utils/transcode", this.getEncoding0());
        this.setByteList(29, "win32 GetNetworkParams needs ", this.getEncoding0());
        this.setByteList(35, "win32/mib/ipstats", this.getEncoding0());
        this.setByteList(16, "sysctl -a net.inet", this.getEncoding0());
        this.setByteList(28, "N*", this.getEncoding0());
        this.setByteList(11, "^\\s+ether", this.getEncoding0());
        this.setByteList(23, "sysctl -A 2> /dev/null", this.getEncoding0());
        this.setByteList(12, "^\\s+status", this.getEncoding0());
        this.setByteList(20, "encap", this.getEncoding0());
        this.setByteList(13, "^\\s+inet6", this.getEncoding0());
        this.setByteList(14, "%", this.getEncoding0());
        this.setByteList(15, "^\\s+inet\\s", this.getEncoding0());
        this.setByteList(27, "l", this.getEncoding0());
        this.setByteList(17, "net.inet.", this.getEncoding0());
        this.setByteList(21, "^\\s+inet", this.getEncoding0());
        this.setByteList(31, ")", this.getEncoding0());
        this.setByteList(26, "unknown", this.getEncoding0());
        this.setByteList(10, "n/a", this.getEncoding0());
        this.setByteList(4, "ip", this.getEncoding0());
        this.setByteList(22, "MTU", this.getEncoding0());
        this.setByteList(33, "win32/interface", this.getEncoding0());
        this.setByteList(0, "lister/util", this.getEncoding0());
        this.setByteList(34, "win32/iphelper", this.getEncoding0());
        this.setByteList(19, "HWaddr", this.getEncoding0());
        this.setByteList(7, ":", this.getEncoding0());
        this.setByteList(6, "^\\S+:", this.getEncoding0());
        this.setByteList(24, "^net.ipv4", this.getEncoding0());
        this.setByteList(5, "ifconfig", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite0().call(threadContext, rubyObject, rubyObject, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString0(threadContext.runtime, 32));
        file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite1().call(threadContext, rubyObject, rubyObject, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString1(threadContext.runtime, 32));
        file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite2().call(threadContext, rubyObject, rubyObject, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString2(threadContext.runtime, 32));
        file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite3().call(threadContext, rubyObject, rubyObject, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString3(threadContext.runtime, 32));
        file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite4().call(threadContext, rubyObject, rubyObject, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString4(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.module__1$RUBY$Utils:(Lruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Utils(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Utils"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.module__2$RUBY$NetConfig:(Lruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__2$RUBY$NetConfig(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "NetConfig"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite5:()Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_2        
        //    34: aload_0        
        //    35: aload_1        
        //    36: ldc             "Util"
        //    38: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    44: pop            
        //    45: aload_0        
        //    46: aload_1        
        //    47: aload_2        
        //    48: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    54: invokestatic    ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.module__3$RUBY$MacOs:(Lruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: pop            
        //    58: aload_0        
        //    59: aload_1        
        //    60: aload_2        
        //    61: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    64: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    67: invokestatic    ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.module__10$RUBY$Linux:(Lruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: pop            
        //    71: aload_0        
        //    72: aload_1        
        //    73: aload_2        
        //    74: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    80: invokestatic    ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.module__17$RUBY$Windows:(Lruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: pop            
        //    84: aload_0        
        //    85: sipush          201
        //    88: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    91: aload_1        
        //    92: aload_2        
        //    93: aload_0        
        //    94: aload_1        
        //    95: ldc_w           "Platform"
        //    98: bipush          62
        //   100: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   103: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   106: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   111: ifeq            218
        //   114: aload_0        
        //   115: sipush          202
        //   118: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   121: aload_1        
        //   122: aload_2        
        //   123: aload_2        
        //   124: aload_0        
        //   125: aload_1        
        //   126: ldc_w           "Windows"
        //   129: bipush          63
        //   131: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   134: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   137: pop            
        //   138: aload_0        
        //   139: sipush          203
        //   142: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   145: aload_1        
        //   146: aload_2        
        //   147: aload_2        
        //   148: aload_0        
        //   149: aload_1        
        //   150: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   153: bipush          33
        //   155: bipush          32
        //   157: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   160: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   163: pop            
        //   164: aload_0        
        //   165: sipush          204
        //   168: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   171: aload_1        
        //   172: aload_2        
        //   173: aload_2        
        //   174: aload_0        
        //   175: aload_1        
        //   176: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   179: bipush          34
        //   181: bipush          32
        //   183: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   186: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   189: pop            
        //   190: aload_0        
        //   191: sipush          205
        //   194: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   197: aload_1        
        //   198: aload_2        
        //   199: aload_2        
        //   200: aload_0        
        //   201: aload_1        
        //   202: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   205: bipush          35
        //   207: bipush          32
        //   209: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   212: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   215: goto            333
        //   218: aload_0        
        //   219: sipush          206
        //   222: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   225: aload_1        
        //   226: aload_2        
        //   227: aload_0        
        //   228: aload_1        
        //   229: ldc_w           "Platform"
        //   232: bipush          64
        //   234: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   237: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   240: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   245: ifeq            274
        //   248: aload_0        
        //   249: sipush          207
        //   252: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   255: aload_1        
        //   256: aload_2        
        //   257: aload_2        
        //   258: aload_0        
        //   259: aload_1        
        //   260: ldc_w           "Linux"
        //   263: bipush          65
        //   265: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   268: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   271: goto            333
        //   274: aload_0        
        //   275: sipush          208
        //   278: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   281: aload_1        
        //   282: aload_2        
        //   283: aload_0        
        //   284: aload_1        
        //   285: ldc_w           "Platform"
        //   288: bipush          66
        //   290: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   293: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   296: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   301: ifeq            329
        //   304: aload_0        
        //   305: sipush          209
        //   308: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   311: aload_1        
        //   312: aload_2        
        //   313: aload_2        
        //   314: aload_0        
        //   315: aload_1        
        //   316: ldc             "MacOs"
        //   318: bipush          67
        //   320: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   323: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   326: goto            333
        //   329: aload_1        
        //   330: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   333: aload_1        
        //   334: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   337: goto            345
        //   340: aload_1        
        //   341: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   344: athrow         
        //   345: aload_1        
        //   346: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   349: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     337    340    345    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject module__3$RUBY$MacOs(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "MacOs"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getScope3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_1        
        //    28: aload_2        
        //    29: aload_0        
        //    30: ldc             "get_iface_macaddresses_cmd"
        //    32: ldc             "method__4$RUBY$get_iface_macaddresses_cmd"
        //    34: ldc             ",0,0,-1"
        //    36: iconst_0       
        //    37: ldc             "./lib//lister/utils/net_config.rb"
        //    39: ldc             13
        //    41: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    44: ldc             "NONE"
        //    46: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: pop            
        //    50: aload_1        
        //    51: aload_2        
        //    52: aload_0        
        //    53: ldc_w           "get_iface_macaddresses"
        //    56: ldc_w           "method__5$RUBY$get_iface_macaddresses"
        //    59: ldc_w           "list;iface,0,0,-1"
        //    62: iconst_0       
        //    63: ldc             "./lib//lister/utils/net_config.rb"
        //    65: ldc_w           17
        //    68: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    71: ldc             "NONE"
        //    73: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: pop            
        //    77: aload_1        
        //    78: aload_2        
        //    79: aload_0        
        //    80: ldc_w           "get_sysctl_cmd"
        //    83: ldc_w           "method__6$RUBY$get_sysctl_cmd"
        //    86: ldc             ",0,0,-1"
        //    88: iconst_0       
        //    89: ldc             "./lib//lister/utils/net_config.rb"
        //    91: ldc_w           55
        //    94: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    97: ldc             "NONE"
        //    99: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   102: pop            
        //   103: aload_1        
        //   104: aload_2        
        //   105: aload_0        
        //   106: ldc_w           "get_sysctl"
        //   109: ldc_w           "method__7$RUBY$get_sysctl"
        //   112: ldc_w           "hash,0,0,-1"
        //   115: iconst_0       
        //   116: ldc             "./lib//lister/utils/net_config.rb"
        //   118: ldc_w           59
        //   121: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   124: ldc             "NONE"
        //   126: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   129: pop            
        //   130: aload_1        
        //   131: aload_2        
        //   132: aload_0        
        //   133: ldc_w           "get_dns"
        //   136: ldc_w           "method__8$RUBY$get_dns"
        //   139: ldc             ",0,0,-1"
        //   141: iconst_0       
        //   142: ldc             "./lib//lister/utils/net_config.rb"
        //   144: ldc_w           69
        //   147: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   150: ldc             "NONE"
        //   152: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   155: pop            
        //   156: aload_1        
        //   157: aload_2        
        //   158: aload_0        
        //   159: ldc_w           "get"
        //   162: ldc_w           "method__9$RUBY$get"
        //   165: ldc             ",0,0,-1"
        //   167: iconst_0       
        //   168: ldc             "./lib//lister/utils/net_config.rb"
        //   170: ldc_w           73
        //   173: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   176: ldc             "NONE"
        //   178: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   181: aload_1        
        //   182: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   185: goto            193
        //   188: aload_1        
        //   189: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   192: athrow         
        //   193: aload_1        
        //   194: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   197: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     185    188    193    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "get_iface_macaddresses_cmd", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$get_iface_macaddresses_cmd(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString5(threadContext.runtime, 32);
    }
    
    @JRubyMethod(name = "get_iface_macaddresses", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$get_iface_macaddresses(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(threadContext.runtime.newArray());
        locals.setValueOneDepthZero(threadContext.nil);
        file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite6().callIter(threadContext, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite7().call(threadContext, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite8().call(threadContext, self, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite9().call(threadContext, self, self))), RuntimeHelpers.createBlock(threadContext, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getBlockBody1(threadContext, "block_0$RUBY$get_iface_macaddresses,1,l;ary;name;mtu;mac;ip,false,2,./lib//lister/utils/net_config.rb,20,false")));
        return locals.getValueZeroDepthZeroOrNil(threadContext.nil);
    }
    
    public static IRubyObject block_0$RUBY$get_iface_macaddresses(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    71: aload_1        
        //    72: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    78: aload_1        
        //    79: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //    82: astore          9
        //    84: aload_0        
        //    85: bipush          10
        //    87: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    90: aload_1        
        //    91: aload_2        
        //    92: aload           9
        //    94: aload_0        
        //    95: aload_1        
        //    96: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    99: aload_0        
        //   100: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getByteList6:()Lorg/jruby/util/ByteList;
        //   103: ldc             512
        //   105: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getRegexp0:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   108: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   111: ifeq            523
        //   114: aload           locals
        //   116: aload_0        
        //   117: bipush          11
        //   119: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   122: aload_1        
        //   123: aload_2        
        //   124: aload_0        
        //   125: bipush          12
        //   127: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   130: aload_1        
        //   131: aload_2        
        //   132: aload           locals
        //   134: aload_1        
        //   135: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   138: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   141: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   144: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   147: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   150: pop            
        //   151: aload           locals
        //   153: aload_0        
        //   154: bipush          13
        //   156: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   159: aload_1        
        //   160: aload_2        
        //   161: aload_0        
        //   162: bipush          14
        //   164: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   167: aload_1        
        //   168: aload_2        
        //   169: aload           locals
        //   171: aload_1        
        //   172: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   175: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   178: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   181: aload_0        
        //   182: aload_1        
        //   183: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   186: bipush          32
        //   188: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   191: aload_0        
        //   192: aload_1        
        //   193: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   196: bipush          32
        //   198: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   201: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   204: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   207: pop            
        //   208: aload_0        
        //   209: bipush          15
        //   211: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   214: aload_1        
        //   215: aload_2        
        //   216: aload_2        
        //   217: aload_1        
        //   218: aload_2        
        //   219: aload_0        
        //   220: aload_1        
        //   221: ldc_w           "block_1$RUBY$get_iface_macaddresses,-1,,false,0,./lib//lister/utils/net_config.rb,25,true"
        //   224: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   227: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   230: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   233: pop            
        //   234: aload           locals
        //   236: aload_0        
        //   237: bipush          19
        //   239: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   242: aload_1        
        //   243: aload_2        
        //   244: aload           locals
        //   246: aload_1        
        //   247: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   250: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   253: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   256: dup            
        //   257: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   262: ifne            278
        //   265: pop            
        //   266: aload_0        
        //   267: aload_1        
        //   268: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   271: bipush          10
        //   273: bipush          32
        //   275: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   278: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   281: pop            
        //   282: aload           locals
        //   284: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   287: aload_1        
        //   288: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   291: aload_0        
        //   292: aload_1        
        //   293: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   296: ldc_w           "name"
        //   299: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   302: aload           locals
        //   304: aload_1        
        //   305: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   308: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   311: aload_0        
        //   312: aload_1        
        //   313: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   316: ldc_w           "type"
        //   319: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   322: aload_0        
        //   323: aload_1        
        //   324: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   327: bipush          10
        //   329: bipush          32
        //   331: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   334: aload_0        
        //   335: aload_1        
        //   336: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   339: ldc_w           "ip"
        //   342: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   345: aload_0        
        //   346: aload_1        
        //   347: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   350: bipush          10
        //   352: bipush          32
        //   354: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   357: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructHash:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyHash;
        //   360: dup            
        //   361: aload_1        
        //   362: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   365: aload_0        
        //   366: aload_1        
        //   367: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   370: ldc_w           "ipv6"
        //   373: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   376: aload_0        
        //   377: aload_1        
        //   378: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   381: bipush          10
        //   383: bipush          32
        //   385: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   388: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   391: dup            
        //   392: aload_1        
        //   393: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   396: aload_0        
        //   397: aload_1        
        //   398: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   401: ldc_w           "ether"
        //   404: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   407: aload_0        
        //   408: aload_1        
        //   409: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   412: bipush          10
        //   414: bipush          32
        //   416: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   419: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   422: dup            
        //   423: aload_1        
        //   424: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   427: aload_0        
        //   428: aload_1        
        //   429: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   432: ldc_w           "status"
        //   435: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   438: aload_0        
        //   439: aload_1        
        //   440: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   443: bipush          10
        //   445: bipush          32
        //   447: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   450: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   453: dup            
        //   454: aload_1        
        //   455: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   458: aload_0        
        //   459: aload_1        
        //   460: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   463: ldc_w           "mtu"
        //   466: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol6:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   469: aload           locals
        //   471: aload_1        
        //   472: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   475: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   478: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   481: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   484: pop            
        //   485: aload_0        
        //   486: bipush          20
        //   488: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   491: aload_1        
        //   492: aload_2        
        //   493: aload           locals
        //   495: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   498: aload_1        
        //   499: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   502: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   505: aload           locals
        //   507: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   510: aload_1        
        //   511: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   514: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   517: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   520: goto            1117
        //   523: aload_0        
        //   524: bipush          21
        //   526: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   529: aload_1        
        //   530: aload_2        
        //   531: aload           9
        //   533: aload_0        
        //   534: aload_1        
        //   535: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   538: aload_0        
        //   539: bipush          11
        //   541: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getByteList:(I)Lorg/jruby/util/ByteList;
        //   544: ldc             512
        //   546: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getRegexp1:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   549: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   552: ifeq            653
        //   555: aload           6
        //   557: iconst_4       
        //   558: aload_0        
        //   559: bipush          22
        //   561: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   564: aload_1        
        //   565: aload_2        
        //   566: aload_0        
        //   567: bipush          23
        //   569: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   572: aload_1        
        //   573: aload_2        
        //   574: aload_0        
        //   575: bipush          24
        //   577: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   580: aload_1        
        //   581: aload_2        
        //   582: aload           locals
        //   584: aload_1        
        //   585: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   588: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   591: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   594: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   597: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   600: aastore        
        //   601: aload           locals
        //   603: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   606: aload_1        
        //   607: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   610: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   613: dup            
        //   614: aload_2        
        //   615: aload_0        
        //   616: bipush          25
        //   618: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   621: aload_0        
        //   622: bipush          26
        //   624: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   627: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   630: aload_0        
        //   631: aload_1        
        //   632: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   635: ldc_w           "ether"
        //   638: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   641: aload           6
        //   643: iconst_4       
        //   644: aaload         
        //   645: aload_1        
        //   646: aload_2        
        //   647: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   650: goto            1117
        //   653: aload_0        
        //   654: bipush          27
        //   656: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   659: aload_1        
        //   660: aload_2        
        //   661: aload           9
        //   663: aload_0        
        //   664: aload_1        
        //   665: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   668: aload_0        
        //   669: bipush          12
        //   671: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getByteList:(I)Lorg/jruby/util/ByteList;
        //   674: ldc             512
        //   676: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getRegexp2:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   679: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   682: ifeq            775
        //   685: aload           locals
        //   687: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   690: aload_1        
        //   691: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   694: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   697: dup            
        //   698: aload_2        
        //   699: aload_0        
        //   700: bipush          28
        //   702: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   705: aload_0        
        //   706: bipush          29
        //   708: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   711: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   714: aload_0        
        //   715: aload_1        
        //   716: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   719: ldc_w           "status"
        //   722: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   725: aload_0        
        //   726: bipush          30
        //   728: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   731: aload_1        
        //   732: aload_2        
        //   733: aload_0        
        //   734: bipush          31
        //   736: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   739: aload_1        
        //   740: aload_2        
        //   741: aload_0        
        //   742: bipush          32
        //   744: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   747: aload_1        
        //   748: aload_2        
        //   749: aload           locals
        //   751: aload_1        
        //   752: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   755: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   758: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   761: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   764: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   767: aload_1        
        //   768: aload_2        
        //   769: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   772: goto            1117
        //   775: aload_0        
        //   776: bipush          33
        //   778: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   781: aload_1        
        //   782: aload_2        
        //   783: aload           9
        //   785: aload_0        
        //   786: aload_1        
        //   787: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   790: aload_0        
        //   791: bipush          13
        //   793: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getByteList:(I)Lorg/jruby/util/ByteList;
        //   796: ldc             512
        //   798: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getRegexp3:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   801: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   804: ifeq            961
        //   807: aload           locals
        //   809: aload_0        
        //   810: bipush          34
        //   812: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   815: aload_1        
        //   816: aload_2        
        //   817: aload_0        
        //   818: bipush          35
        //   820: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   823: aload_1        
        //   824: aload_2        
        //   825: aload           locals
        //   827: aload_1        
        //   828: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   831: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   834: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   837: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   840: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   843: pop            
        //   844: aload           6
        //   846: iconst_5       
        //   847: aload_0        
        //   848: bipush          36
        //   850: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   853: aload_1        
        //   854: aload_2        
        //   855: aload_0        
        //   856: bipush          37
        //   858: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   861: aload_1        
        //   862: aload_2        
        //   863: aload_0        
        //   864: bipush          38
        //   866: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   869: aload_1        
        //   870: aload_2        
        //   871: aload           locals
        //   873: aload_1        
        //   874: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   877: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   880: aload_1        
        //   881: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   884: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   887: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   890: aload_0        
        //   891: aload_1        
        //   892: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   895: bipush          14
        //   897: bipush          32
        //   899: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   902: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   905: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   908: aastore        
        //   909: aload           locals
        //   911: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   914: aload_1        
        //   915: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   918: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   921: dup            
        //   922: aload_2        
        //   923: aload_0        
        //   924: bipush          39
        //   926: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   929: aload_0        
        //   930: bipush          40
        //   932: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   935: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   938: aload_0        
        //   939: aload_1        
        //   940: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   943: ldc_w           "ipv6"
        //   946: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   949: aload           6
        //   951: iconst_5       
        //   952: aaload         
        //   953: aload_1        
        //   954: aload_2        
        //   955: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   958: goto            1117
        //   961: aload_0        
        //   962: bipush          41
        //   964: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   967: aload_1        
        //   968: aload_2        
        //   969: aload           9
        //   971: aload_0        
        //   972: aload_1        
        //   973: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   976: aload_0        
        //   977: bipush          15
        //   979: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getByteList:(I)Lorg/jruby/util/ByteList;
        //   982: ldc             512
        //   984: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getRegexp4:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   987: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   990: ifeq            1113
        //   993: aload           locals
        //   995: aload_0        
        //   996: bipush          42
        //   998: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1001: aload_1        
        //  1002: aload_2        
        //  1003: aload_0        
        //  1004: bipush          43
        //  1006: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1009: aload_1        
        //  1010: aload_2        
        //  1011: aload           locals
        //  1013: aload_1        
        //  1014: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //  1017: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1020: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1023: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1026: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1029: pop            
        //  1030: aload           6
        //  1032: iconst_5       
        //  1033: aload_0        
        //  1034: bipush          44
        //  1036: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1039: aload_1        
        //  1040: aload_2        
        //  1041: aload           locals
        //  1043: aload_1        
        //  1044: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //  1047: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1050: aload_1        
        //  1051: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1054: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //  1057: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1060: aastore        
        //  1061: aload           locals
        //  1063: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //  1066: aload_1        
        //  1067: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //  1070: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1073: dup            
        //  1074: aload_2        
        //  1075: aload_0        
        //  1076: bipush          45
        //  1078: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1081: aload_0        
        //  1082: bipush          46
        //  1084: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1087: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //  1090: aload_0        
        //  1091: aload_1        
        //  1092: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1095: ldc_w           "ip"
        //  1098: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //  1101: aload           6
        //  1103: iconst_5       
        //  1104: aaload         
        //  1105: aload_1        
        //  1106: aload_2        
        //  1107: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1110: goto            1117
        //  1113: aload_1        
        //  1114: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //  1117: areturn        
        //  1118: pop            
        //  1119: goto            69
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  69     1049    5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  69     1118   1118   1122   Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_1$RUBY$get_iface_macaddresses(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final DynamicScope currentScope = context.getCurrentScope();
        RuntimeHelpers.processBlockArgument(context.runtime, block);
        IRubyObject rubyObject3;
        if (!(rubyObject3 = file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(16).call(context, rubyObject, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(17).call(context, rubyObject, currentScope.getNextCapturedScope().getValueOneDepthZeroOrNil(context.nil)), file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString9(context.runtime, 32))).isTrue()) {
            rubyObject3 = file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(18).call(context, rubyObject, currentScope.getNextCapturedScope().getValueOneDepthZeroOrNil(context.nil));
        }
        return rubyObject3.isTrue() ? RuntimeHelpers.breakJump(context, context.nil) : context.nil;
    }
    
    @JRubyMethod(name = "get_sysctl_cmd", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$get_sysctl_cmd(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString(threadContext.runtime, 16, 32);
    }
    
    @JRubyMethod(name = "get_sysctl", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$get_sysctl(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(RubyHash.newHash(threadContext.runtime));
        file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(47).callIter(threadContext, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(48).call(threadContext, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(49).call(threadContext, self, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(50).call(threadContext, self, self))), RuntimeHelpers.createBlock(threadContext, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getBlockBody2(threadContext, "block_2$RUBY$get_sysctl,1,l;key;val;str,false,2,./lib//lister/utils/net_config.rb,61,false")));
        return locals.getValueZeroDepthZeroOrNil(threadContext.nil);
    }
    
    public static IRubyObject block_2$RUBY$get_sysctl(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    28: aload           5
        //    30: swap           
        //    31: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    34: pop            
        //    35: aload_1        
        //    36: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    39: aload           4
        //    41: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    44: aload_3        
        //    45: aload           5
        //    47: swap           
        //    48: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: pop            
        //    52: pop            
        //    53: aload_0        
        //    54: bipush          51
        //    56: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    59: aload_1        
        //    60: aload_2        
        //    61: aload           locals
        //    63: aload_1        
        //    64: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.splatValue:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //    76: aload_1        
        //    77: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    80: iconst_1       
        //    81: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.ensureMultipleAssignableRubyArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;Z)Lorg/jruby/RubyArray;
        //    84: astore          9
        //    86: aload           9
        //    88: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilZero:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    91: aload           locals
        //    93: swap           
        //    94: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    97: pop            
        //    98: aload           9
        //   100: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilOne:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   103: aload           locals
        //   105: swap           
        //   106: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   109: pop            
        //   110: aload           9
        //   112: pop            
        //   113: aload           locals
        //   115: aload_0        
        //   116: bipush          52
        //   118: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   121: aload_1        
        //   122: aload_2        
        //   123: aload_0        
        //   124: bipush          53
        //   126: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   129: aload_1        
        //   130: aload_2        
        //   131: aload           locals
        //   133: aload_1        
        //   134: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   137: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   140: aload_0        
        //   141: aload_1        
        //   142: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   145: bipush          17
        //   147: bipush          32
        //   149: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   152: aload_0        
        //   153: aload_1        
        //   154: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   157: bipush          32
        //   159: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   162: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   165: aload_0        
        //   166: aload_1        
        //   167: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   170: bipush          32
        //   172: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   175: aload_0        
        //   176: aload_1        
        //   177: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   180: bipush          32
        //   182: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   185: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   188: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   191: pop            
        //   192: aload           locals
        //   194: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   197: aload_1        
        //   198: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   201: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   204: dup            
        //   205: aload_2        
        //   206: aload_0        
        //   207: bipush          54
        //   209: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   212: aload_0        
        //   213: bipush          55
        //   215: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   218: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   221: aload           locals
        //   223: aload_1        
        //   224: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   227: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   230: aload_0        
        //   231: bipush          56
        //   233: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   236: aload_1        
        //   237: aload_2        
        //   238: aload           locals
        //   240: aload_1        
        //   241: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   244: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   247: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   250: aload_1        
        //   251: aload_2        
        //   252: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   255: areturn        
        //   256: pop            
        //   257: goto            53
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  53     203     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  53     256    256    260    Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "get_dns", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$get_dns(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        return RuntimeHelpers.constructHash(context.runtime, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol7(context.runtime, "dns_servers"), file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(57).call(context, rubyObject, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant1(context, "DNS")));
    }
    
    @JRubyMethod(name = "get", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$get(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(58).callIter(threadContext, self, RuntimeHelpers.constructRubyArray(threadContext.runtime, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol8(threadContext.runtime, "get_dns"), file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol9(threadContext.runtime, "get_sysctl")), RubyHash.newHash(threadContext.runtime), RuntimeHelpers.createBlock(threadContext, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getBlockBody3(threadContext, "block_3$RUBY$get,2,hash;sym,true,1,./lib//lister/utils/net_config.rb,74,true")));
    }
    
    public static IRubyObject block_3$RUBY$get(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    57: bipush          59
        //    59: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    62: aload_1        
        //    63: aload_2        
        //    64: aload           hash
        //    66: aload_0        
        //    67: bipush          60
        //    69: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    72: aload_1        
        //    73: aload_2        
        //    74: aload_2        
        //    75: aload           sym
        //    77: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  56     28      9     hash  Lorg/jruby/runtime/builtin/IRubyObject;
        //  56     28      10    sym   Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject module__3$RUBY$MacOs(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__3$RUBY$MacOs(file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__10$RUBY$Linux(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc_w           "Linux"
        //    11: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    14: dup            
        //    15: astore_2       
        //    16: aload_1        
        //    17: swap           
        //    18: aload_0        
        //    19: aload_1        
        //    20: ldc             ",0,0,-1"
        //    22: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getScope4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    25: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    28: aload_1        
        //    29: aload_2        
        //    30: aload_0        
        //    31: ldc             "get_iface_macaddresses_cmd"
        //    33: ldc_w           "method__11$RUBY$get_iface_macaddresses_cmd"
        //    36: ldc             ",0,0,-1"
        //    38: iconst_0       
        //    39: ldc             "./lib//lister/utils/net_config.rb"
        //    41: ldc_w           81
        //    44: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    47: ldc             "NONE"
        //    49: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: pop            
        //    53: aload_1        
        //    54: aload_2        
        //    55: aload_0        
        //    56: ldc_w           "get_iface_macaddresses"
        //    59: ldc_w           "method__12$RUBY$get_iface_macaddresses"
        //    62: ldc_w           "list;iface,0,0,-1"
        //    65: iconst_0       
        //    66: ldc             "./lib//lister/utils/net_config.rb"
        //    68: ldc_w           85
        //    71: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    74: ldc             "NONE"
        //    76: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: pop            
        //    80: aload_1        
        //    81: aload_2        
        //    82: aload_0        
        //    83: ldc_w           "get_sysctl_cmd"
        //    86: ldc_w           "method__13$RUBY$get_sysctl_cmd"
        //    89: ldc             ",0,0,-1"
        //    91: iconst_0       
        //    92: ldc             "./lib//lister/utils/net_config.rb"
        //    94: ldc_w           121
        //    97: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   100: ldc             "NONE"
        //   102: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: pop            
        //   106: aload_1        
        //   107: aload_2        
        //   108: aload_0        
        //   109: ldc_w           "get_sysctl"
        //   112: ldc_w           "method__14$RUBY$get_sysctl"
        //   115: ldc_w           "hash,0,0,-1"
        //   118: iconst_0       
        //   119: ldc             "./lib//lister/utils/net_config.rb"
        //   121: ldc_w           125
        //   124: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   127: ldc             "NONE"
        //   129: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   132: pop            
        //   133: aload_1        
        //   134: aload_2        
        //   135: aload_0        
        //   136: ldc_w           "get_dns"
        //   139: ldc_w           "method__15$RUBY$get_dns"
        //   142: ldc             ",0,0,-1"
        //   144: iconst_0       
        //   145: ldc             "./lib//lister/utils/net_config.rb"
        //   147: ldc_w           139
        //   150: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   153: ldc             "NONE"
        //   155: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   158: pop            
        //   159: aload_1        
        //   160: aload_2        
        //   161: aload_0        
        //   162: ldc_w           "get"
        //   165: ldc_w           "method__16$RUBY$get"
        //   168: ldc             ",0,0,-1"
        //   170: iconst_0       
        //   171: ldc             "./lib//lister/utils/net_config.rb"
        //   173: ldc_w           143
        //   176: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   179: ldc             "NONE"
        //   181: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   184: aload_1        
        //   185: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   188: goto            196
        //   191: aload_1        
        //   192: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   195: athrow         
        //   196: aload_1        
        //   197: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   200: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  28     188    191    196    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "get_iface_macaddresses_cmd", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__11$RUBY$get_iface_macaddresses_cmd(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString5(threadContext.runtime, 32);
    }
    
    @JRubyMethod(name = "get_iface_macaddresses", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__12$RUBY$get_iface_macaddresses(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(threadContext.runtime.newArray());
        locals.setValueOneDepthZero(threadContext.nil);
        file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(61).callIter(threadContext, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(62).call(threadContext, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(63).call(threadContext, self, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(64).call(threadContext, self, self))), RuntimeHelpers.createBlock(threadContext, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getBlockBody6(threadContext, "block_4$RUBY$get_iface_macaddresses,1,l;ary;name;type;mac;mtu,false,2,./lib//lister/utils/net_config.rb,88,false")));
        return locals.getValueZeroDepthZeroOrNil(threadContext.nil);
    }
    
    public static IRubyObject block_4$RUBY$get_iface_macaddresses(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    71: aload_1        
        //    72: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    78: aload_1        
        //    79: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //    82: astore          9
        //    84: aload_0        
        //    85: bipush          65
        //    87: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    90: aload_1        
        //    91: aload_2        
        //    92: aload           9
        //    94: aload_0        
        //    95: aload_1        
        //    96: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    99: aload_0        
        //   100: bipush          18
        //   102: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getByteList:(I)Lorg/jruby/util/ByteList;
        //   105: ldc             512
        //   107: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getRegexp5:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   110: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   113: ifeq            378
        //   116: aload           locals
        //   118: aload_0        
        //   119: bipush          66
        //   121: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   124: aload_1        
        //   125: aload_2        
        //   126: aload_0        
        //   127: bipush          67
        //   129: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   132: aload_1        
        //   133: aload_2        
        //   134: aload           locals
        //   136: aload_1        
        //   137: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   140: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   143: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   146: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   149: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   152: pop            
        //   153: aload           locals
        //   155: aload_0        
        //   156: bipush          68
        //   158: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   161: aload_1        
        //   162: aload_2        
        //   163: aload           locals
        //   165: aload_1        
        //   166: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   169: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   172: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   175: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   178: pop            
        //   179: aload           locals
        //   181: aload_0        
        //   182: aload_1        
        //   183: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   186: bipush          10
        //   188: bipush          32
        //   190: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   193: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   196: pop            
        //   197: aload_0        
        //   198: bipush          69
        //   200: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   203: aload_1        
        //   204: aload_2        
        //   205: aload_2        
        //   206: aload_1        
        //   207: aload_2        
        //   208: aload_0        
        //   209: aload_1        
        //   210: ldc_w           "block_5$RUBY$get_iface_macaddresses,-1,word;k;v,false,0,./lib//lister/utils/net_config.rb,94,false"
        //   213: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getBlockBody4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   216: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   219: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   222: pop            
        //   223: aload           6
        //   225: iconst_4       
        //   226: aload_0        
        //   227: bipush          78
        //   229: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   232: aload_1        
        //   233: aload_2        
        //   234: aload           locals
        //   236: aload_1        
        //   237: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   240: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   243: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   246: dup            
        //   247: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   252: ifne            268
        //   255: pop            
        //   256: aload_0        
        //   257: aload_1        
        //   258: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   261: bipush          10
        //   263: bipush          32
        //   265: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   268: aastore        
        //   269: aload           locals
        //   271: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   274: aload_1        
        //   275: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   278: aload_0        
        //   279: aload_1        
        //   280: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   283: ldc_w           "name"
        //   286: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   289: aload           locals
        //   291: aload_1        
        //   292: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   295: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   298: aload_0        
        //   299: aload_1        
        //   300: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   303: ldc_w           "type"
        //   306: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   309: aload           locals
        //   311: aload_1        
        //   312: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   315: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   318: aload_0        
        //   319: aload_1        
        //   320: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   323: ldc_w           "ether"
        //   326: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   329: aload           6
        //   331: iconst_4       
        //   332: aaload         
        //   333: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructHash:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyHash;
        //   336: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   339: pop            
        //   340: aload_0        
        //   341: bipush          79
        //   343: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   346: aload_1        
        //   347: aload_2        
        //   348: aload           locals
        //   350: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   353: aload_1        
        //   354: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   357: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   360: aload           locals
        //   362: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   365: aload_1        
        //   366: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   369: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   372: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   375: goto            913
        //   378: aload_0        
        //   379: bipush          80
        //   381: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   384: aload_1        
        //   385: aload_2        
        //   386: aload           9
        //   388: aload_0        
        //   389: aload_1        
        //   390: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   393: aload_0        
        //   394: bipush          13
        //   396: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getByteList:(I)Lorg/jruby/util/ByteList;
        //   399: ldc             512
        //   401: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getRegexp6:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   404: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   407: ifeq            496
        //   410: aload           locals
        //   412: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   415: aload_1        
        //   416: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   419: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   422: dup            
        //   423: aload_2        
        //   424: aload_0        
        //   425: bipush          81
        //   427: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   430: aload_0        
        //   431: bipush          82
        //   433: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   436: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   439: aload_0        
        //   440: aload_1        
        //   441: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   444: ldc_w           "ipv6"
        //   447: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   450: aload_0        
        //   451: bipush          83
        //   453: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   456: aload_1        
        //   457: aload_2        
        //   458: aload_0        
        //   459: bipush          84
        //   461: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   464: aload_1        
        //   465: aload_2        
        //   466: aload           locals
        //   468: aload_1        
        //   469: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   472: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   475: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   478: aload_1        
        //   479: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   482: invokestatic    org/jruby/RubyFixnum.two:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   485: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   488: aload_1        
        //   489: aload_2        
        //   490: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   493: goto            913
        //   496: aload_0        
        //   497: bipush          85
        //   499: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   502: aload_1        
        //   503: aload_2        
        //   504: aload           9
        //   506: aload_0        
        //   507: aload_1        
        //   508: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   511: aload_0        
        //   512: bipush          21
        //   514: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getByteList:(I)Lorg/jruby/util/ByteList;
        //   517: ldc             512
        //   519: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getRegexp7:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   522: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   525: ifeq            646
        //   528: aload           locals
        //   530: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   533: aload_1        
        //   534: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   537: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   540: dup            
        //   541: aload_2        
        //   542: aload_0        
        //   543: bipush          86
        //   545: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   548: aload_0        
        //   549: bipush          87
        //   551: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   554: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   557: aload_0        
        //   558: aload_1        
        //   559: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   562: ldc_w           "ip"
        //   565: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   568: aload_0        
        //   569: bipush          88
        //   571: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   574: aload_1        
        //   575: aload_2        
        //   576: aload_0        
        //   577: bipush          89
        //   579: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   582: aload_1        
        //   583: aload_2        
        //   584: aload_0        
        //   585: bipush          90
        //   587: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   590: aload_1        
        //   591: aload_2        
        //   592: aload_0        
        //   593: bipush          91
        //   595: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   598: aload_1        
        //   599: aload_2        
        //   600: aload           locals
        //   602: aload_1        
        //   603: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   606: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   609: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   612: aload_1        
        //   613: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   616: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   619: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   622: aload_0        
        //   623: aload_1        
        //   624: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   627: bipush          32
        //   629: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   632: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   635: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   638: aload_1        
        //   639: aload_2        
        //   640: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   643: goto            913
        //   646: aload_0        
        //   647: bipush          92
        //   649: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   652: aload_1        
        //   653: aload_2        
        //   654: aload           9
        //   656: aload_0        
        //   657: aload_1        
        //   658: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   661: aload_0        
        //   662: bipush          22
        //   664: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getByteList:(I)Lorg/jruby/util/ByteList;
        //   667: ldc             512
        //   669: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getRegexp8:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   672: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   675: ifeq            909
        //   678: aload           6
        //   680: iconst_5       
        //   681: aload_0        
        //   682: bipush          93
        //   684: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   687: aload_1        
        //   688: aload_2        
        //   689: aload_0        
        //   690: bipush          94
        //   692: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   695: aload_1        
        //   696: aload_2        
        //   697: aload           locals
        //   699: aload_1        
        //   700: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   703: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   706: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   709: aload_1        
        //   710: aload_2        
        //   711: aload_0        
        //   712: aload_1        
        //   713: ldc_w           "block_6$RUBY$get_iface_macaddresses,1,a,false,2,./lib//lister/utils/net_config.rb,113,false"
        //   716: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getBlockBody5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   719: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   722: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   725: aastore        
        //   726: aload           6
        //   728: iconst_5       
        //   729: aaload         
        //   730: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   735: ifeq            823
        //   738: aload           locals
        //   740: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   743: aload_1        
        //   744: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   747: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   750: dup            
        //   751: aload_2        
        //   752: aload_0        
        //   753: bipush          95
        //   755: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   758: aload_0        
        //   759: bipush          96
        //   761: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   764: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   767: aload_0        
        //   768: aload_1        
        //   769: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   772: ldc_w           "mtu"
        //   775: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol6:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   778: aload_0        
        //   779: bipush          97
        //   781: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   784: aload_1        
        //   785: aload_2        
        //   786: aload_0        
        //   787: bipush          98
        //   789: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   792: aload_1        
        //   793: aload_2        
        //   794: aload           6
        //   796: iconst_5       
        //   797: aaload         
        //   798: aload_0        
        //   799: aload_1        
        //   800: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   803: bipush          32
        //   805: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   808: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   811: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   814: aload_1        
        //   815: aload_2        
        //   816: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   819: pop            
        //   820: goto            823
        //   823: aload           locals
        //   825: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   828: aload_1        
        //   829: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   832: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   835: dup            
        //   836: aload_2        
        //   837: aload_0        
        //   838: bipush          99
        //   840: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   843: aload_0        
        //   844: bipush          100
        //   846: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   849: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   852: aload_0        
        //   853: aload_1        
        //   854: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   857: ldc_w           "status"
        //   860: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   863: aload_0        
        //   864: bipush          101
        //   866: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   869: aload_1        
        //   870: aload_2        
        //   871: aload_0        
        //   872: bipush          102
        //   874: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   877: aload_1        
        //   878: aload_2        
        //   879: aload           locals
        //   881: aload_1        
        //   882: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   885: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   888: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   891: aload_1        
        //   892: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   895: invokestatic    org/jruby/RubyFixnum.two:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   898: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   901: aload_1        
        //   902: aload_2        
        //   903: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   906: goto            913
        //   909: aload_1        
        //   910: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   913: areturn        
        //   914: pop            
        //   915: goto            69
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  69     845     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  69     914    914    918    Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_5$RUBY$get_iface_macaddresses(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    39: pop            
        //    40: pop            
        //    41: aload           locals
        //    43: aload_0        
        //    44: bipush          70
        //    46: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload           locals
        //    53: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    56: aload_1        
        //    57: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    69: pop            
        //    70: aload_0        
        //    71: bipush          71
        //    73: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    76: aload_1        
        //    77: aload_2        
        //    78: aload           locals
        //    80: aload_1        
        //    81: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    84: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    87: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    90: dup            
        //    91: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    96: ifne            132
        //    99: pop            
        //   100: aload_0        
        //   101: bipush          72
        //   103: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   106: aload_1        
        //   107: aload_2        
        //   108: aload           locals
        //   110: aload_1        
        //   111: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   114: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   117: aload_0        
        //   118: aload_1        
        //   119: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   122: bipush          19
        //   124: bipush          32
        //   126: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   129: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   132: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   137: ifeq            152
        //   140: aload_1        
        //   141: aload_1        
        //   142: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   145: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.breakJump:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   148: pop            
        //   149: goto            152
        //   152: aload_0        
        //   153: bipush          73
        //   155: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   158: aload_1        
        //   159: aload_2        
        //   160: aload_0        
        //   161: bipush          74
        //   163: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   166: aload_1        
        //   167: aload_2        
        //   168: aload_0        
        //   169: bipush          75
        //   171: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   174: aload_1        
        //   175: aload_2        
        //   176: aload           locals
        //   178: aload_1        
        //   179: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   182: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   185: aload_0        
        //   186: aload_1        
        //   187: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   190: bipush          32
        //   192: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   195: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   198: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   201: ldc2_w          2
        //   204: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;J)Lorg/jruby/runtime/builtin/IRubyObject;
        //   207: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   212: ifeq            352
        //   215: aload_0        
        //   216: bipush          76
        //   218: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   221: aload_1        
        //   222: aload_2        
        //   223: aload           locals
        //   225: aload_1        
        //   226: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   229: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   232: aload_0        
        //   233: aload_1        
        //   234: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   237: bipush          32
        //   239: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   242: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   245: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.aryToAry:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   248: aload_1        
        //   249: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   252: iconst_1       
        //   253: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.ensureMultipleAssignableRubyArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;Z)Lorg/jruby/RubyArray;
        //   256: astore          9
        //   258: aload           9
        //   260: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilZero:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   263: aload           locals
        //   265: swap           
        //   266: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   269: pop            
        //   270: aload           9
        //   272: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilOne:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   275: aload           locals
        //   277: swap           
        //   278: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   281: pop            
        //   282: aload           9
        //   284: pop            
        //   285: aload_0        
        //   286: bipush          77
        //   288: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   291: aload_1        
        //   292: aload_2        
        //   293: aload           locals
        //   295: aload_1        
        //   296: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   299: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   302: aload_0        
        //   303: aload_1        
        //   304: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   307: bipush          20
        //   309: bipush          32
        //   311: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   314: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   317: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   322: ifeq            345
        //   325: aload           locals
        //   327: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   330: aload           locals
        //   332: aload_1        
        //   333: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   336: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   339: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   342: goto            349
        //   345: aload_1        
        //   346: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   349: goto            356
        //   352: aload_1        
        //   353: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   356: areturn        
        //   357: pop            
        //   358: goto            41
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  41     316     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  41     357    357    361    Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_6$RUBY$get_iface_macaddresses(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    40: aload_0        
        //    41: bipush          22
        //    43: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getByteList:(I)Lorg/jruby/util/ByteList;
        //    46: ldc             512
        //    48: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getRegexp9:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //    51: aload           locals
        //    53: aload_1        
        //    54: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: aload_1        
        //    61: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.match3:(Lorg/jruby/RubyRegexp;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    64: areturn        
        //    65: pop            
        //    66: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     30      5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     65     65     69     Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "get_sysctl_cmd", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__13$RUBY$get_sysctl_cmd(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString(threadContext.runtime, 23, 32);
    }
    
    @JRubyMethod(name = "get_sysctl", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__14$RUBY$get_sysctl(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(RubyHash.newHash(threadContext.runtime));
        file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(103).callIter(threadContext, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(104).call(threadContext, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(105).call(threadContext, self, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(106).call(threadContext, self, self))), RuntimeHelpers.createBlock(threadContext, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getBlockBody7(threadContext, "block_7$RUBY$get_sysctl,1,l;ary;key;val;str,false,2,./lib//lister/utils/net_config.rb,127,false")));
        return locals.getValueZeroDepthZeroOrNil(threadContext.nil);
    }
    
    public static IRubyObject block_7$RUBY$get_sysctl(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    72: bipush          107
        //    74: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    77: aload_1        
        //    78: aload_2        
        //    79: aload           locals
        //    81: aload_1        
        //    82: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    85: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    91: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    94: pop            
        //    95: aload           locals
        //    97: aload_0        
        //    98: bipush          108
        //   100: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   103: aload_1        
        //   104: aload_2        
        //   105: aload           locals
        //   107: aload_1        
        //   108: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   111: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   114: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   117: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   120: pop            
        //   121: aload           locals
        //   123: aload_0        
        //   124: bipush          109
        //   126: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   129: aload_1        
        //   130: aload_2        
        //   131: aload           locals
        //   133: aload_1        
        //   134: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   137: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   140: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   143: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   146: pop            
        //   147: aload_0        
        //   148: aload_1        
        //   149: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   152: bipush          10
        //   154: aload_0        
        //   155: bipush          24
        //   157: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getByteList:(I)Lorg/jruby/util/ByteList;
        //   160: ldc             512
        //   162: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getRegexp:(Lorg/jruby/Ruby;ILorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   165: aload           locals
        //   167: aload_1        
        //   168: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   171: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   174: aload_1        
        //   175: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.match3:(Lorg/jruby/RubyRegexp;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   178: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   183: ifeq            324
        //   186: aload           6
        //   188: iconst_4       
        //   189: aload_0        
        //   190: bipush          110
        //   192: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   195: aload_1        
        //   196: aload_2        
        //   197: aload_0        
        //   198: bipush          111
        //   200: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   203: aload_1        
        //   204: aload_2        
        //   205: aload           locals
        //   207: aload_1        
        //   208: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   211: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   214: aload_0        
        //   215: aload_1        
        //   216: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   219: bipush          25
        //   221: bipush          32
        //   223: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   226: aload_0        
        //   227: aload_1        
        //   228: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   231: bipush          32
        //   233: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   236: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   239: aload_0        
        //   240: aload_1        
        //   241: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   244: bipush          32
        //   246: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   249: aload_0        
        //   250: aload_1        
        //   251: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   254: bipush          32
        //   256: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   259: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   262: aastore        
        //   263: aload           locals
        //   265: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   268: aload_1        
        //   269: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   272: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   275: dup            
        //   276: aload_2        
        //   277: aload_0        
        //   278: bipush          112
        //   280: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   283: aload_0        
        //   284: bipush          113
        //   286: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   289: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   292: aload           6
        //   294: iconst_4       
        //   295: aaload         
        //   296: aload_0        
        //   297: bipush          114
        //   299: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   302: aload_1        
        //   303: aload_2        
        //   304: aload           locals
        //   306: aload_1        
        //   307: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   310: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   313: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   316: aload_1        
        //   317: aload_2        
        //   318: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   321: goto            328
        //   324: aload_1        
        //   325: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   328: areturn        
        //   329: pop            
        //   330: goto            69
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  69     260     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  69     329    329    333    Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "get_dns", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__15$RUBY$get_dns(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        return RuntimeHelpers.constructHash(context.runtime, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol7(context.runtime, "dns_servers"), file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(115).call(context, rubyObject, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant2(context, "DNS")));
    }
    
    @JRubyMethod(name = "get", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__16$RUBY$get(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(116).callIter(threadContext, self, RuntimeHelpers.constructRubyArray(threadContext.runtime, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol8(threadContext.runtime, "get_dns"), file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol9(threadContext.runtime, "get_sysctl")), RubyHash.newHash(threadContext.runtime), RuntimeHelpers.createBlock(threadContext, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getBlockBody8(threadContext, "block_8$RUBY$get,2,hash;sym,true,1,./lib//lister/utils/net_config.rb,144,true")));
    }
    
    public static IRubyObject block_8$RUBY$get(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    57: bipush          117
        //    59: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    62: aload_1        
        //    63: aload_2        
        //    64: aload           hash
        //    66: aload_0        
        //    67: bipush          118
        //    69: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    72: aload_1        
        //    73: aload_2        
        //    74: aload_2        
        //    75: aload           sym
        //    77: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  56     28      9     hash  Lorg/jruby/runtime/builtin/IRubyObject;
        //  56     28      10    sym   Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject module__10$RUBY$Linux(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__10$RUBY$Linux(file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__17$RUBY$Windows(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc_w           "Windows"
        //    11: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    14: dup            
        //    15: astore_2       
        //    16: aload_1        
        //    17: swap           
        //    18: aload_0        
        //    19: aload_1        
        //    20: ldc             ",0,0,-1"
        //    22: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getScope5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    25: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    28: aload_0        
        //    29: bipush          119
        //    31: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    34: aload_1        
        //    35: aload_2        
        //    36: aload_2        
        //    37: aload_0        
        //    38: aload_1        
        //    39: ldc_w           "Transcode"
        //    42: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    48: pop            
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload_0        
        //    52: ldc_w           "get_iface_macaddresses"
        //    55: ldc_w           "method__18$RUBY$get_iface_macaddresses"
        //    58: ldc_w           "list,0,0,-1"
        //    61: iconst_0       
        //    62: ldc             "./lib//lister/utils/net_config.rb"
        //    64: ldc_w           152
        //    67: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    70: ldc             "NONE"
        //    72: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: pop            
        //    76: aload_1        
        //    77: aload_2        
        //    78: aload_0        
        //    79: ldc_w           "ip_addresses"
        //    82: ldc_w           "method__19$RUBY$ip_addresses"
        //    85: ldc_w           "p_len;ptr;table;p_ips,0,0,-1"
        //    88: iconst_0       
        //    89: ldc             "./lib//lister/utils/net_config.rb"
        //    91: ldc_w           174
        //    94: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    97: ldc             "NONE"
        //    99: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   102: pop            
        //   103: aload_1        
        //   104: aload_2        
        //   105: aload_0        
        //   106: ldc_w           "get_tcp_config"
        //   109: ldc_w           "method__20$RUBY$get_tcp_config"
        //   112: ldc_w           "p_stats;stats,0,0,-1"
        //   115: iconst_0       
        //   116: ldc             "./lib//lister/utils/net_config.rb"
        //   118: ldc_w           191
        //   121: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   124: ldc             "NONE"
        //   126: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   129: pop            
        //   130: aload_1        
        //   131: aload_2        
        //   132: aload_0        
        //   133: ldc_w           "get_other_config"
        //   136: ldc_w           "method__21$RUBY$get_other_config"
        //   139: ldc_w           "p_long;err_code;info;p_info;iface;mtu;p_stats;stats;ret,0,0,-1"
        //   142: iconst_0       
        //   143: ldc             "./lib//lister/utils/net_config.rb"
        //   145: ldc_w           201
        //   148: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   151: ldc             "NONE"
        //   153: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   156: pop            
        //   157: aload_1        
        //   158: aload_2        
        //   159: aload_0        
        //   160: ldc_w           "get_dns"
        //   163: ldc_w           "method__23$RUBY$get_dns"
        //   166: ldc             ",0,0,-1"
        //   168: iconst_0       
        //   169: ldc             "./lib//lister/utils/net_config.rb"
        //   171: ldc_w           240
        //   174: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   177: ldc             "NONE"
        //   179: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   182: pop            
        //   183: aload_1        
        //   184: aload_2        
        //   185: aload_0        
        //   186: ldc_w           "get"
        //   189: ldc_w           "method__24$RUBY$get"
        //   192: ldc             ",0,0,-1"
        //   194: iconst_0       
        //   195: ldc             "./lib//lister/utils/net_config.rb"
        //   197: ldc_w           244
        //   200: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   203: ldc             "NONE"
        //   205: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   208: aload_1        
        //   209: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   212: goto            220
        //   215: aload_1        
        //   216: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   219: athrow         
        //   220: aload_1        
        //   221: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   224: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  28     212    215    220    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "get_iface_macaddresses", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__18$RUBY$get_iface_macaddresses(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(threadContext.runtime.newArray());
        file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(120).callIter(threadContext, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(121).call(threadContext, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom5(RuntimeHelpers.checkIsModule(file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant4(threadContext, "Win32")), threadContext, "Interface")), RuntimeHelpers.createBlock(threadContext, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getBlockBody(threadContext, 10, "block_9$RUBY$get_iface_macaddresses,1,iface;h,false,2,./lib//lister/utils/net_config.rb,154,false")));
        return locals.getValueZeroDepthZeroOrNil(threadContext.nil);
    }
    
    public static IRubyObject block_9$RUBY$get_iface_macaddresses(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    47: aload_0        
        //    48: aload_1        
        //    49: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    52: ldc_w           "name"
        //    55: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    58: aload_0        
        //    59: bipush          122
        //    61: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    64: aload_1        
        //    65: aload_2        
        //    66: aload_2        
        //    67: aload_0        
        //    68: bipush          123
        //    70: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    73: aload_1        
        //    74: aload_2        
        //    75: aload           locals
        //    77: aload_1        
        //    78: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    81: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    84: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    87: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    90: aload_0        
        //    91: aload_1        
        //    92: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    95: ldc_w           "type"
        //    98: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   101: aload_0        
        //   102: bipush          124
        //   104: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   107: aload_1        
        //   108: aload_2        
        //   109: aload           locals
        //   111: aload_1        
        //   112: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   115: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   118: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   121: aload_0        
        //   122: aload_1        
        //   123: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   126: ldc_w           "ip"
        //   129: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   132: aload_0        
        //   133: aload_1        
        //   134: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   137: bipush          10
        //   139: bipush          32
        //   141: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   144: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructHash:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyHash;
        //   147: dup            
        //   148: aload_1        
        //   149: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   152: aload_0        
        //   153: aload_1        
        //   154: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   157: ldc_w           "ipv6"
        //   160: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   163: aload_0        
        //   164: aload_1        
        //   165: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   168: bipush          26
        //   170: bipush          32
        //   172: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   175: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   178: dup            
        //   179: aload_1        
        //   180: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   183: aload_0        
        //   184: aload_1        
        //   185: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   188: ldc_w           "ether"
        //   191: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   194: aload_0        
        //   195: bipush          125
        //   197: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   200: aload_1        
        //   201: aload_2        
        //   202: aload           locals
        //   204: aload_1        
        //   205: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   208: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   211: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   214: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   217: dup            
        //   218: aload_1        
        //   219: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   222: aload_0        
        //   223: aload_1        
        //   224: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   227: ldc_w           "status"
        //   230: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   233: aload_0        
        //   234: bipush          126
        //   236: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   239: aload_1        
        //   240: aload_2        
        //   241: aload           locals
        //   243: aload_1        
        //   244: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   247: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   250: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   253: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   256: dup            
        //   257: aload_1        
        //   258: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   261: aload_0        
        //   262: aload_1        
        //   263: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   266: ldc_w           "mtu"
        //   269: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol6:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   272: aload_0        
        //   273: bipush          127
        //   275: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   278: aload_1        
        //   279: aload_2        
        //   280: aload           locals
        //   282: aload_1        
        //   283: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   286: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   289: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   292: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   295: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   298: pop            
        //   299: aload_0        
        //   300: sipush          128
        //   303: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   306: aload_1        
        //   307: aload_2        
        //   308: aload_0        
        //   309: sipush          129
        //   312: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   315: aload_1        
        //   316: aload_2        
        //   317: aload_2        
        //   318: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   321: aload_1        
        //   322: aload_2        
        //   323: aload_0        
        //   324: aload_1        
        //   325: ldc_w           "block_10$RUBY$get_iface_macaddresses,1,ip;host_int;net_int,false,2,./lib//lister/utils/net_config.rb,162,false"
        //   328: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getBlockBody9:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   331: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   334: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   337: pop            
        //   338: aload_0        
        //   339: sipush          141
        //   342: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   345: aload_1        
        //   346: aload_2        
        //   347: aload           locals
        //   349: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   352: aload_1        
        //   353: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   356: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   359: aload           locals
        //   361: aload_1        
        //   362: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   365: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   368: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   371: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  41     331     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_10$RUBY$get_iface_macaddresses(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    47: aload_0        
        //    48: sipush          130
        //    51: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    54: aload_1        
        //    55: aload_2        
        //    56: aload_0        
        //    57: sipush          131
        //    60: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    63: aload_1        
        //    64: aload_2        
        //    65: aload           locals
        //    67: aload_1        
        //    68: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    71: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: aload_0        
        //    75: aload_1        
        //    76: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    79: ldc_w           10
        //    82: ldc_w           "dwIndex"
        //    85: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    88: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    91: aload_0        
        //    92: sipush          132
        //    95: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    98: aload_1        
        //    99: aload_2        
        //   100: aload           locals
        //   102: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   105: aload_1        
        //   106: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   109: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   112: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   115: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   118: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   123: ifeq            352
        //   126: aload           locals
        //   128: aload_0        
        //   129: sipush          133
        //   132: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   135: aload_1        
        //   136: aload_2        
        //   137: aload           locals
        //   139: aload_1        
        //   140: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   143: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   146: aload_0        
        //   147: aload_1        
        //   148: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   151: ldc_w           11
        //   154: ldc_w           "dwAddr"
        //   157: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   160: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   163: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   166: pop            
        //   167: aload           locals
        //   169: aload_0        
        //   170: sipush          134
        //   173: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   176: aload_1        
        //   177: aload_2        
        //   178: aload_0        
        //   179: sipush          135
        //   182: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   185: aload_1        
        //   186: aload_2        
        //   187: aload_0        
        //   188: sipush          136
        //   191: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   194: aload_1        
        //   195: aload_2        
        //   196: aload_1        
        //   197: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   200: aload           locals
        //   202: aload_1        
        //   203: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   206: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   209: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   212: aload_0        
        //   213: aload_1        
        //   214: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   217: bipush          27
        //   219: bipush          32
        //   221: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   224: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   227: aload_0        
        //   228: aload_1        
        //   229: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   232: bipush          28
        //   234: bipush          32
        //   236: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   239: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   242: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   245: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   248: pop            
        //   249: aload           locals
        //   251: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   254: aload_1        
        //   255: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   258: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   261: dup            
        //   262: aload_2        
        //   263: aload_0        
        //   264: sipush          137
        //   267: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   270: aload_0        
        //   271: sipush          138
        //   274: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   277: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   280: aload_0        
        //   281: aload_1        
        //   282: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   285: ldc_w           "ip"
        //   288: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   291: aload_0        
        //   292: sipush          139
        //   295: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   298: aload_1        
        //   299: aload_2        
        //   300: aload_0        
        //   301: sipush          140
        //   304: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   307: aload_1        
        //   308: aload_2        
        //   309: aload_0        
        //   310: aload_1        
        //   311: ldc_w           "IP"
        //   314: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant6:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   317: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   320: aload_0        
        //   321: swap           
        //   322: aload_1        
        //   323: ldc_w           "V4"
        //   326: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom7:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   329: aload           locals
        //   331: aload_1        
        //   332: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   335: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   338: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   341: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   344: aload_1        
        //   345: aload_2        
        //   346: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   349: goto            356
        //   352: aload_1        
        //   353: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   356: areturn        
        //   357: pop            
        //   358: goto            47
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  47     310     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  47     357    357    361    Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "ip_addresses", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__19$RUBY$ip_addresses(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, final ThreadContext context, final IRubyObject self, final Block block) {
        final DynamicScope locals = context.getCurrentScope();
        locals.setValueZeroDepthZero(file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(142).call(context, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom9(RuntimeHelpers.checkIsModule(file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant8(context, "FFI")), context, "MemoryPointer"), file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol(context.runtime, 12, "ulong")));
        file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(143).call(context, self, locals.getValueZeroDepthZeroOrNil(context.nil), RubyFixnum.zero(context.runtime));
        final CallSite callSite = file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(144);
        final IRubyObject constant = file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom(RuntimeHelpers.checkIsModule(file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant(context, "Win32", 10)), context, "IPHelper", 11);
        final IRubyObject nil = context.nil;
        final IRubyObject valueZeroDepthZeroOrNil = locals.getValueZeroDepthZeroOrNil(context.nil);
        final RubyBoolean false = context.runtime.getFalse();
        context.pollThreadEvents();
        callSite.call(context, self, constant, nil, valueZeroDepthZeroOrNil, false);
        locals.setValueOneDepthZero(file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(145).call(context, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom(RuntimeHelpers.checkIsModule(file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant(context, "FFI", 12)), context, "MemoryPointer", 13), file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(146).call(context, self, locals.getValueZeroDepthZeroOrNil(context.nil))));
        file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(147).call(context, self, locals.getValueZeroDepthZeroOrNil(context.nil), file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(148).call(context, self, locals.getValueOneDepthZeroOrNil(context.nil)));
        file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(149).call(context, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom(RuntimeHelpers.checkIsModule(file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant(context, "Win32", 14)), context, "IPHelper", 15), locals.getValueOneDepthZeroOrNil(context.nil), locals.getValueZeroDepthZeroOrNil(context.nil), RubyFixnum.zero(context.runtime));
        locals.setValueTwoDepthZero(file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(150).call(context, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom(RuntimeHelpers.checkIsModule(file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom(RuntimeHelpers.checkIsModule(file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant(context, "Win32", 16)), context, "MIB", 17)), context, "IpAddrTable", 18), locals.getValueOneDepthZeroOrNil(context.nil)));
        locals.setValueThreeDepthZero(file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(151).call(context, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(152).call(context, self, locals.getValueTwoDepthZeroOrNil(context.nil), file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol(context.runtime, 13, "table"))));
        return file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(153).callIter(context, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(154).call(context, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(155).call(context, self, locals.getValueTwoDepthZeroOrNil(context.nil), file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol(context.runtime, 14, "dwNumEntries"))), RuntimeHelpers.createBlock(context, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getBlockBody(context, 11, "block_11$RUBY$ip_addresses,1,i;addr,false,2,./lib//lister/utils/net_config.rb,184,true")));
    }
    
    public static IRubyObject block_11$RUBY$ip_addresses(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    31: aload_0        
        //    32: sipush          156
        //    35: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    38: aload_1        
        //    39: aload_2        
        //    40: aload_0        
        //    41: sipush          157
        //    44: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    47: aload_1        
        //    48: aload_2        
        //    49: aload           5
        //    51: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    54: aload_1        
        //    55: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    61: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    64: aload_0        
        //    65: sipush          158
        //    68: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    71: aload_1        
        //    72: aload_2        
        //    73: aload           i
        //    75: aload_0        
        //    76: sipush          159
        //    79: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    82: aload_1        
        //    83: aload_2        
        //    84: aload_0        
        //    85: aload_1        
        //    86: ldc_w           "Win32"
        //    89: bipush          19
        //    91: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    94: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    97: aload_0        
        //    98: swap           
        //    99: aload_1        
        //   100: ldc_w           "MIB"
        //   103: bipush          20
        //   105: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   108: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   111: aload_0        
        //   112: swap           
        //   113: aload_1        
        //   114: ldc_w           "IpAddrTable"
        //   117: bipush          21
        //   119: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   122: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   125: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   128: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   131: astore          addr
        //   133: aload_0        
        //   134: sipush          160
        //   137: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   140: aload_1        
        //   141: aload_2        
        //   142: aload_0        
        //   143: aload_1        
        //   144: ldc_w           "Win32"
        //   147: bipush          22
        //   149: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   152: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   155: aload_0        
        //   156: swap           
        //   157: aload_1        
        //   158: ldc_w           "MIB"
        //   161: bipush          23
        //   163: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   166: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   169: aload_0        
        //   170: swap           
        //   171: aload_1        
        //   172: ldc_w           "IpAddrRow"
        //   175: bipush          24
        //   177: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   180: aload_0        
        //   181: sipush          161
        //   184: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   187: aload_1        
        //   188: aload_2        
        //   189: aload_0        
        //   190: aload_1        
        //   191: ldc_w           "FFI"
        //   194: bipush          25
        //   196: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   199: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   202: aload_0        
        //   203: swap           
        //   204: aload_1        
        //   205: ldc_w           "Pointer"
        //   208: bipush          26
        //   210: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   213: aload           addr
        //   215: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   218: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   221: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  31     191     9     i     Lorg/jruby/runtime/builtin/IRubyObject;
        //  31     191     10    addr  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "get_tcp_config", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__20$RUBY$get_tcp_config(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(162).call(threadContext, rubyObject, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom(RuntimeHelpers.checkIsModule(file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant(threadContext, "FFI", 27)), threadContext, "MemoryPointer", 28), file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom(RuntimeHelpers.checkIsModule(file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom(RuntimeHelpers.checkIsModule(file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant(threadContext, "Win32", 29)), threadContext, "MIB", 30)), threadContext, "TCPStats", 31)));
        file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(163).call(threadContext, rubyObject, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom(RuntimeHelpers.checkIsModule(file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant(threadContext, "Win32", 32)), threadContext, "IPHelper", 33), locals.getValueZeroDepthZeroOrNil(threadContext.nil));
        locals.setValueOneDepthZero(file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(164).call(threadContext, rubyObject, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom(RuntimeHelpers.checkIsModule(file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom(RuntimeHelpers.checkIsModule(file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant(threadContext, "Win32", 34)), threadContext, "MIB", 35)), threadContext, "TCPStats", 36), locals.getValueZeroDepthZeroOrNil(threadContext.nil)));
        final RubyHash constructHash = RuntimeHelpers.constructHash(threadContext.runtime, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol(threadContext.runtime, 15, "tcp_algorithm"), file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(165).call(threadContext, rubyObject, locals.getValueOneDepthZeroOrNil(threadContext.nil)), file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol(threadContext.runtime, 16, "tcp_rto_min"), file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(166).call(threadContext, rubyObject, locals.getValueOneDepthZeroOrNil(threadContext.nil), file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol(threadContext.runtime, 17, "dwRtoMin")), file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol(threadContext.runtime, 18, "tcp_rto_max"), file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(167).call(threadContext, rubyObject, locals.getValueOneDepthZeroOrNil(threadContext.nil), file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol(threadContext.runtime, 19, "dwRtoMax")));
        constructHash.fastASetCheckString(threadContext.runtime, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol(threadContext.runtime, 20, "tcp_max_connexions"), file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(168).call(threadContext, rubyObject, locals.getValueOneDepthZeroOrNil(threadContext.nil), file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol(threadContext.runtime, 21, "dwMaxConn")));
        return constructHash;
    }
    
    @JRubyMethod(name = "get_other_config", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__21$RUBY$get_other_config(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          4
        //     6: aload           4
        //     8: invokevirtual   org/jruby/runtime/DynamicScope.getValues:()[Lorg/jruby/runtime/builtin/IRubyObject;
        //    11: astore          5
        //    13: aload           5
        //    15: aload_1        
        //    16: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    19: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.fillNil:([Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;)V
        //    22: aload           locals
        //    24: aload_0        
        //    25: sipush          169
        //    28: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_0        
        //    34: aload_1        
        //    35: ldc_w           "FFI"
        //    38: bipush          37
        //    40: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    46: aload_0        
        //    47: swap           
        //    48: aload_1        
        //    49: ldc_w           "MemoryPointer"
        //    52: bipush          38
        //    54: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: aload_0        
        //    58: aload_1        
        //    59: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    62: ldc_w           12
        //    65: ldc_w           "ulong"
        //    68: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    71: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: pop            
        //    78: aload_0        
        //    79: sipush          170
        //    82: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    85: aload_1        
        //    86: aload_2        
        //    87: aload           locals
        //    89: aload_1        
        //    90: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    93: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    96: aload_1        
        //    97: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   100: invokestatic    org/jruby/RubyFixnum.zero:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   103: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   106: pop            
        //   107: aload           locals
        //   109: aload_0        
        //   110: sipush          171
        //   113: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   116: aload_1        
        //   117: aload_2        
        //   118: aload_0        
        //   119: aload_1        
        //   120: ldc_w           "Win32"
        //   123: bipush          39
        //   125: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   128: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   131: aload_0        
        //   132: swap           
        //   133: aload_1        
        //   134: ldc_w           "IPHelper"
        //   137: bipush          40
        //   139: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   142: aload_1        
        //   143: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   146: aload           locals
        //   148: aload_1        
        //   149: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   152: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   155: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   158: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   161: pop            
        //   162: aload_0        
        //   163: sipush          172
        //   166: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   169: aload_1        
        //   170: aload_2        
        //   171: aload_2        
        //   172: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   175: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   180: ifeq            313
        //   183: aload_0        
        //   184: sipush          173
        //   187: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   190: aload_1        
        //   191: aload_2        
        //   192: aload_0        
        //   193: sipush          174
        //   196: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   199: aload_1        
        //   200: aload_2        
        //   201: aload_2        
        //   202: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   205: aload_1        
        //   206: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   209: ldc_w           20
        //   212: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   215: aload_0        
        //   216: aload_1        
        //   217: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   220: bipush          29
        //   222: bipush          32
        //   224: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   227: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   230: aload_0        
        //   231: sipush          175
        //   234: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   237: aload_1        
        //   238: aload_2        
        //   239: aload           locals
        //   241: aload_1        
        //   242: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   245: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   248: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   251: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   256: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   259: aload_0        
        //   260: aload_1        
        //   261: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   264: bipush          30
        //   266: bipush          32
        //   268: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   271: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   274: aload           locals
        //   276: aload_1        
        //   277: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   280: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   283: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   288: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   291: aload_0        
        //   292: aload_1        
        //   293: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   296: bipush          31
        //   298: bipush          32
        //   300: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   303: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   306: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   309: pop            
        //   310: goto            313
        //   313: aload           locals
        //   315: aload_0        
        //   316: sipush          176
        //   319: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   322: aload_1        
        //   323: aload_2        
        //   324: aload_0        
        //   325: sipush          177
        //   328: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   331: aload_1        
        //   332: aload_2        
        //   333: aload           locals
        //   335: aload_1        
        //   336: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   339: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   342: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   345: ldc2_w          0
        //   348: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;J)Lorg/jruby/runtime/builtin/IRubyObject;
        //   351: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   356: ifeq            411
        //   359: aload_0        
        //   360: sipush          178
        //   363: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   366: aload_1        
        //   367: aload_2        
        //   368: aload_0        
        //   369: sipush          179
        //   372: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   375: aload_1        
        //   376: aload_2        
        //   377: aload_0        
        //   378: aload_1        
        //   379: ldc_w           "Class"
        //   382: bipush          41
        //   384: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   387: aload_1        
        //   388: aload_2        
        //   389: aload_0        
        //   390: aload_1        
        //   391: bipush          12
        //   393: ldc_w           "block_12$RUBY$get_other_config,-1,,false,0,./lib//lister/utils/net_config.rb,207,true"
        //   396: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getBlockBody:(Lorg/jruby/runtime/ThreadContext;ILjava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   399: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   402: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   405: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   408: goto            588
        //   411: aload           locals
        //   413: aload_0        
        //   414: sipush          180
        //   417: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   420: aload_1        
        //   421: aload_2        
        //   422: aload_0        
        //   423: aload_1        
        //   424: ldc_w           "FFI"
        //   427: bipush          42
        //   429: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   432: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   435: aload_0        
        //   436: swap           
        //   437: aload_1        
        //   438: ldc_w           "MemoryPointer"
        //   441: bipush          43
        //   443: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   446: aload_0        
        //   447: sipush          181
        //   450: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   453: aload_1        
        //   454: aload_2        
        //   455: aload           locals
        //   457: aload_1        
        //   458: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   461: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   464: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   467: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   470: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   473: pop            
        //   474: aload_0        
        //   475: sipush          182
        //   478: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   481: aload_1        
        //   482: aload_2        
        //   483: aload_0        
        //   484: aload_1        
        //   485: ldc_w           "Win32"
        //   488: bipush          44
        //   490: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   493: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   496: aload_0        
        //   497: swap           
        //   498: aload_1        
        //   499: ldc_w           "IPHelper"
        //   502: bipush          45
        //   504: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   507: aload           locals
        //   509: aload_1        
        //   510: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   513: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   516: aload           locals
        //   518: aload_1        
        //   519: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   522: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   525: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   528: pop            
        //   529: aload_0        
        //   530: sipush          183
        //   533: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   536: aload_1        
        //   537: aload_2        
        //   538: aload_0        
        //   539: aload_1        
        //   540: ldc_w           "Win32"
        //   543: bipush          46
        //   545: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   548: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   551: aload_0        
        //   552: swap           
        //   553: aload_1        
        //   554: ldc_w           "MIB"
        //   557: bipush          47
        //   559: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   562: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   565: aload_0        
        //   566: swap           
        //   567: aload_1        
        //   568: ldc_w           "FixedInfo"
        //   571: bipush          48
        //   573: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   576: aload           locals
        //   578: aload_1        
        //   579: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   582: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   585: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   588: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   591: pop            
        //   592: aload           5
        //   594: iconst_4       
        //   595: aload_0        
        //   596: sipush          184
        //   599: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   602: aload_1        
        //   603: aload_2        
        //   604: aload_0        
        //   605: aload_1        
        //   606: ldc_w           "Win32"
        //   609: bipush          49
        //   611: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   614: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   617: aload_0        
        //   618: swap           
        //   619: aload_1        
        //   620: ldc_w           "Interface"
        //   623: bipush          50
        //   625: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   628: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   631: aastore        
        //   632: aload           5
        //   634: iconst_5       
        //   635: aload           5
        //   637: iconst_4       
        //   638: aaload         
        //   639: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   644: ifeq            666
        //   647: aload_0        
        //   648: sipush          185
        //   651: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   654: aload_1        
        //   655: aload_2        
        //   656: aload           5
        //   658: iconst_4       
        //   659: aaload         
        //   660: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   663: goto            678
        //   666: aload_0        
        //   667: aload_1        
        //   668: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   671: bipush          32
        //   673: bipush          32
        //   675: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   678: aastore        
        //   679: aload           5
        //   681: bipush          6
        //   683: aload_0        
        //   684: sipush          186
        //   687: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   690: aload_1        
        //   691: aload_2        
        //   692: aload_0        
        //   693: aload_1        
        //   694: ldc_w           "FFI"
        //   697: bipush          51
        //   699: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   702: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   705: aload_0        
        //   706: swap           
        //   707: aload_1        
        //   708: ldc_w           "MemoryPointer"
        //   711: bipush          52
        //   713: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   716: aload_0        
        //   717: aload_1        
        //   718: ldc_w           "Win32"
        //   721: bipush          53
        //   723: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   726: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   729: aload_0        
        //   730: swap           
        //   731: aload_1        
        //   732: ldc_w           "MIB"
        //   735: bipush          54
        //   737: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   740: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   743: aload_0        
        //   744: swap           
        //   745: aload_1        
        //   746: ldc_w           "IPStats"
        //   749: bipush          55
        //   751: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   754: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   757: aastore        
        //   758: aload_0        
        //   759: sipush          187
        //   762: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   765: aload_1        
        //   766: aload_2        
        //   767: aload_0        
        //   768: aload_1        
        //   769: ldc_w           "Win32"
        //   772: bipush          56
        //   774: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   777: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   780: aload_0        
        //   781: swap           
        //   782: aload_1        
        //   783: ldc_w           "IPHelper"
        //   786: bipush          57
        //   788: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   791: aload           5
        //   793: bipush          6
        //   795: aaload         
        //   796: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   799: pop            
        //   800: aload           5
        //   802: bipush          7
        //   804: aload_0        
        //   805: sipush          188
        //   808: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   811: aload_1        
        //   812: aload_2        
        //   813: aload_0        
        //   814: aload_1        
        //   815: ldc_w           "Win32"
        //   818: bipush          58
        //   820: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   823: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   826: aload_0        
        //   827: swap           
        //   828: aload_1        
        //   829: ldc_w           "MIB"
        //   832: bipush          59
        //   834: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   837: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   840: aload_0        
        //   841: swap           
        //   842: aload_1        
        //   843: ldc_w           "IPStats"
        //   846: bipush          60
        //   848: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   851: aload           5
        //   853: bipush          6
        //   855: aaload         
        //   856: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   859: aastore        
        //   860: aload           5
        //   862: bipush          8
        //   864: aload_1        
        //   865: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   868: aload_0        
        //   869: aload_1        
        //   870: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   873: ldc_w           22
        //   876: ldc_w           "machine_type"
        //   879: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   882: aload_0        
        //   883: sipush          189
        //   886: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   889: aload_1        
        //   890: aload_2        
        //   891: aload           locals
        //   893: aload_1        
        //   894: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   897: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   900: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   903: aload_0        
        //   904: aload_1        
        //   905: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   908: ldc_w           23
        //   911: ldc_w           "machine_is_a_router?"
        //   914: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   917: aload_0        
        //   918: sipush          190
        //   921: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   924: aload_1        
        //   925: aload_2        
        //   926: aload           locals
        //   928: aload_1        
        //   929: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   932: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   935: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   938: aload_0        
        //   939: aload_1        
        //   940: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   943: ldc_w           24
        //   946: ldc_w           "machine_is_a_proxy?"
        //   949: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   952: aload_0        
        //   953: sipush          191
        //   956: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   959: aload_1        
        //   960: aload_2        
        //   961: aload           locals
        //   963: aload_1        
        //   964: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   967: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   970: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   973: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructHash:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyHash;
        //   976: dup            
        //   977: aload_1        
        //   978: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   981: aload_0        
        //   982: aload_1        
        //   983: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   986: ldc_w           25
        //   989: ldc_w           "machine_is_a_dns_server?"
        //   992: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   995: aload_0        
        //   996: sipush          192
        //   999: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1002: aload_1        
        //  1003: aload_2        
        //  1004: aload           locals
        //  1006: aload_1        
        //  1007: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //  1010: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1013: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1016: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //  1019: dup            
        //  1020: aload_1        
        //  1021: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1024: aload_0        
        //  1025: aload_1        
        //  1026: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1029: ldc_w           "mtu"
        //  1032: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol6:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //  1035: aload           5
        //  1037: iconst_5       
        //  1038: aaload         
        //  1039: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //  1042: dup            
        //  1043: aload_1        
        //  1044: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1047: aload_0        
        //  1048: aload_1        
        //  1049: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1052: ldc_w           26
        //  1055: ldc_w           "ttl"
        //  1058: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1061: aload_0        
        //  1062: sipush          193
        //  1065: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1068: aload_1        
        //  1069: aload_2        
        //  1070: aload           5
        //  1072: bipush          7
        //  1074: aaload         
        //  1075: aload_0        
        //  1076: aload_1        
        //  1077: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1080: ldc_w           27
        //  1083: ldc_w           "dwDefaultTTL"
        //  1086: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1089: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1092: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //  1095: aastore        
        //  1096: aload_0        
        //  1097: sipush          194
        //  1100: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1103: aload_1        
        //  1104: aload_2        
        //  1105: aload           locals
        //  1107: aload_1        
        //  1108: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //  1111: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1114: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1117: pop            
        //  1118: aload_0        
        //  1119: sipush          195
        //  1122: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1125: aload_1        
        //  1126: aload_2        
        //  1127: aload           locals
        //  1129: aload_1        
        //  1130: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //  1133: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1136: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1139: pop            
        //  1140: aload_0        
        //  1141: sipush          196
        //  1144: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1147: aload_1        
        //  1148: aload_2        
        //  1149: aload           5
        //  1151: bipush          6
        //  1153: aaload         
        //  1154: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1157: pop            
        //  1158: aload           5
        //  1160: bipush          8
        //  1162: aaload         
        //  1163: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  22     1142    4     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_12$RUBY$get_other_config(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 scriptObject, final ThreadContext context, final IRubyObject self, final IRubyObject rubyObject, final Block block) {
        context.getCurrentScope();
        RuntimeHelpers.processBlockArgument(context.runtime, block);
        return RuntimeHelpers.def(context, self, scriptObject, "method_missing", "method__22$RUBY$method_missing", "meth,1,0,-1", 1, "./lib//lister/utils/net_config.rb", 208, CallConfiguration.FrameNoneScopeNone, "qmeth");
    }
    
    @JRubyMethod(name = "method_missing", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__22$RUBY$method_missing(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getString(threadContext.runtime, 10, 32);
    }
    
    @JRubyMethod(name = "get_dns", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__23$RUBY$get_dns(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        return RuntimeHelpers.constructHash(context.runtime, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol7(context.runtime, "dns_servers"), file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(197).call(context, rubyObject, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getConstant(context, "DNS", 61)));
    }
    
    @JRubyMethod(name = "get", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__24$RUBY$get(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite(198).callIter(threadContext, self, RuntimeHelpers.constructRubyArray(threadContext.runtime, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol(threadContext.runtime, 28, "get_tcp_config"), file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol(threadContext.runtime, 29, "get_other_config"), file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getSymbol8(threadContext.runtime, "get_dns")), RubyHash.newHash(threadContext.runtime), RuntimeHelpers.createBlock(threadContext, self, file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getBlockBody(threadContext, 13, "block_13$RUBY$get,2,hash;sym,true,1,./lib//lister/utils/net_config.rb,245,true")));
    }
    
    public static IRubyObject block_13$RUBY$get(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    57: sipush          199
        //    60: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    63: aload_1        
        //    64: aload_2        
        //    65: aload           hash
        //    67: aload_0        
        //    68: sipush          200
        //    71: invokevirtual   ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    74: aload_1        
        //    75: aload_2        
        //    76: aload_2        
        //    77: aload           sym
        //    79: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    85: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  56     30      9     hash  Lorg/jruby/runtime/builtin/IRubyObject;
        //  56     30      10    sym   Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject module__17$RUBY$Windows(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__17$RUBY$Windows(file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__2$RUBY$NetConfig(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__2$RUBY$NetConfig(file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Utils(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Utils(file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98, threadContext, rubyObject, block);
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
        final FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98 = new FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98();
        final String string = FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.class.getClassLoader().getResource("ruby/jit/FILE_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.class").toString();
        file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_1560C3FD716BB7BA4F7234D84BE1B23FCFAE3A98.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
