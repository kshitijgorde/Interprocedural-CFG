// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.RubyString;
import org.jruby.exceptions.JumpException;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.DynamicScope;
import org.jruby.RubyFixnum;
import org.jruby.runtime.Arity;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 extends AbstractScript
{
    public FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289() {
        this.filename = "./lib//lister/utils/scan.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffautoload\uffffF\uffffautoload\uffffF\uffffrequire\uffffF\uffffattr_writer\uffffF\uffffextend\uffffF\uffffnew\uffffN\ufffflogger\uffffV\uffffdebug\uffffN\ufffflogger\uffffV\ufffftimeout\uffffN\uffffnew\uffffN\uffffeach_neighbouring_address\uffffF\uffffinclude?\uffffN\uffffto_addr\uffffN\uffffnetwork\uffffN\uffffaddress\uffffV\uffffto_addr\uffffN\uffffbroadcast\uffffN\uffffaddress\uffffV\uffffto_addr\uffffN\uffffsend\uffffN\uffffto_addr\uffffN\uffff+\uffffN\uffffdebug\uffffN\ufffflogger\uffffV\uffffdebug\uffffN\ufffflogger\uffffV\uffffaddress\uffffV\uffffeach_with_index\uffffN\uffffto_range\uffffN\uffffnew\uffffN\uffffarp_scan\uffffV\uffffto_a\uffffN\uffffarp_neighbours\uffffV\uffffsize\uffffN\ufffflist_neighbours_arp\uffffV\uffffget_address\uffffV\uffffget_address\uffffV\uffffdefault_iface\uffffN\ufffffind\uffffN\ufffflines\uffffN\uffffspawn\uffffF\uffffaddress_cmd\uffffV\uffffnew\uffffN\uffff[]\uffffN\uffffsplit\uffffN\uffffselect\uffffN\ufffflines\uffffN\uffffspawn\uffffF\uffffarp_neighbours_cmd\uffffV\uffff==\uffffN\uffff[]\uffffN\uffffsplit\uffffN\uffffmap\uffffN\ufffflladdr_arp_neighbours_lines\uffffV\uffff[]\uffffN\uffffsplit\uffffN\ufffftr\uffffN\uffff[]\uffffN\uffffsplit\uffffN\uffffnew\uffffN\uffffuniq\uffffN\uffffmap\uffffN\ufffflines\uffffN\uffffspawn\uffffF\uffffavahi_scan_cmd\uffffV\uffff[]\uffffN\uffffsplit\uffffN\uffffsize\uffffN\uffffavahi_neighbours\uffffV\uffffdefault_iface\uffffN\ufffffind\uffffN\ufffflines\uffffN\uffffspawn\uffffF\uffffaddress_cmd\uffffV\uffffsplit\uffffN\uffffstrip\uffffN\uffff==\uffffN\ufffffirst\uffffN\uffffsplit\uffffN\uffffstrip\uffffN\uffffnew\uffffN\uffff[]\uffffN\uffffnew\uffffN\uffff[]\uffffN\uffffmask=\uffffN\uffffmask=\uffffV\uffffto_i\uffffN\uffff~\uffffN\uffffreject\uffffN\ufffflines\uffffN\uffffspawn\uffffF\uffffarp_neighbours_cmd\uffffV\uffffmap\uffffN\uffffnon_incomplete_arp_neighbours_lines\uffffV\ufffffind\uffffN\uffffsplit\uffffN\uffffjoin\uffffN\uffffmap\uffffN\uffffsplit\uffffN\uffff==\uffffN\uffffsize\uffffN\uffff+\uffffN\ufffffind\uffffN\uffffsplit\uffffN\uffff[]\uffffN\ufffflast_match\uffffN\uffffnew\uffffN\uffffnew\uffffN\uffffrun\uffffN\uffffsize\uffffN\uffffuniq\uffffN\uffffmap\uffffN\uffffservices\uffffN\uffffhost\uffffN\uffffeach\uffffN\ufffflines\uffffN\uffffspawn\uffffF\uffffnil?\uffffN\uffff==\uffffN\uffffsize\uffffN\uffffsplit\uffffN\uffffstrip\uffffN\ufffflast\uffffN\uffffsplit\uffffN\uffffstrip\uffffN\ufffflast\uffffN\uffffsplit\uffffN\uffff==\uffffN\uffffsize\uffffN\uffffsplit\uffffN\uffffstrip\uffffN\ufffflast\uffffN\uffffsplit\uffffN\uffffstrip\uffffN\ufffflast\uffffN\uffffsplit\uffffN\uffffnew\uffffN\uffffnew\uffffN\uffffmask=\uffffN\uffffmask=\uffffV\uffffto_i\uffffN\uffff~\uffffN\uffffnew\uffffN\uffffwrite_ulong\uffffN\uffffGetIpNetTable\uffffN\uffffnew\uffffN\uffffread_ulong\uffffN\uffffwrite_ulong\uffffN\uffffsize\uffffN\uffffGetIpNetTable\uffffN\uffffnew\uffffN\uffffto_ptr\uffffN\uffff[]\uffffN\uffffmap\uffffN\ufffftimes\uffffN\uffff[]\uffffN\uffff+\uffffN\uffffaddress\uffffN\uffff*\uffffN\uffffsize\uffffN\uffffnew\uffffN\uffffnew\uffffN\uffffselect\uffffN\uffffarp_neighbours_rows\uffffV\uffffvalid?\uffffN\uffffmap\uffffN\uffffvalid_arp_neighbours_rows\uffffV\uffffmac_addr\uffffN\uffffip_addr_as_int\uffffN\ufffffirst\uffffN\uffffunpack\uffffN\uffffpack\uffffN\uffffnew\uffffN\uffffnew\uffffN\uffffnew\uffffN\uffffrun\uffffN\uffffsize\uffffN\uffffuniq\uffffN\uffffmap\uffffN\uffffservices\uffffN\uffffhost\uffffN\uffffextend\uffffF\uffffon_windows?\uffffN\uffffextend\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffextend\uffffF\uffffputs\uffffF\uffffon_mac?\uffffN\uffffextend\uffffF\uffffrequire\uffffF\uffffextend\uffffF\uffffputs\uffffF\uffffextend\uffffF\uffff\t\b\u0002\u0000;\u0005\u0000\u0001\u0002\u0013\u0000\u0000%\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(36, "no mDNSResponder.exe", this.getEncoding0());
        this.setByteList(16, "", this.getEncoding0());
        this.setByteList(35, "macOS/zeroconf", this.getEncoding0());
        this.setByteList(2, "lister/utils/platform", this.getEncoding0());
        this.setByteList(21, "incomplete", this.getEncoding0());
        this.setByteList(27, "ipconfig", this.getEncoding0());
        this.setByteList(6, "scanning with UDP (port:9) packets", this.getEncoding0());
        this.setByteList(20, "arp -an", this.getEncoding0());
        this.setByteList(33, "win32/mib/ipstats", this.getEncoding0());
        this.setByteList(11, "ip address show dev ", this.getEncoding0());
        this.setByteList(31, "N*", this.getEncoding0());
        this.setByteList(7, "discard-me", this.getEncoding0());
        this.setByteList(32, "api", this.getEncoding0());
        this.setByteList(3, "socket", this.getEncoding0());
        this.setByteList(19, "ifconfig ", this.getEncoding0());
        this.setByteList(15, "()", this.getEncoding0());
        this.setByteList(13, "ip neigh", this.getEncoding0());
        this.setByteList(1, "lister/utils/route", this.getEncoding0());
        this.setByteList(28, "IP", this.getEncoding0());
        this.setByteList(30, "l", this.getEncoding0());
        this.setByteList(25, "\\((([1-2]?\\d?\\d\\.){3}([1-2]?\\d?\\d))\\)", this.getEncoding0());
        this.setByteList(29, ".", this.getEncoding0());
        this.setByteList(22, "(\\w{1,2}:){5}\\w{1,2}", this.getEncoding0());
        this.setByteList(5, "ip", this.getEncoding0());
        this.setByteList(14, "lladdr", this.getEncoding0());
        this.setByteList(26, "not available", this.getEncoding0());
        this.setByteList(24, "0", this.getEncoding0());
        this.setByteList(0, "lister/util", this.getEncoding0());
        this.setByteList(12, "inet", this.getEncoding0());
        this.setByteList(34, "win32/iphelper", this.getEncoding0());
        this.setByteList(18, ";", this.getEncoding0());
        this.setByteList(23, ":", this.getEncoding0());
        this.setByteList(8, "stopped subnet scan (taking too much time)", this.getEncoding0());
        this.setByteList(9, "rescued error with the socket", this.getEncoding0());
        this.setByteList(4, "timeout", this.getEncoding0());
        this.setByteList(10, "instance-variable", this.getEncoding0());
        this.setByteList(17, "avahi-browse -atp", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite0().call(threadContext, rubyObject, rubyObject, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString0(threadContext.runtime, 32));
        file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite1().call(threadContext, rubyObject, rubyObject, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString1(threadContext.runtime, 32));
        file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite2().call(threadContext, rubyObject, rubyObject, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString2(threadContext.runtime, 32));
        file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite3().call(threadContext, rubyObject, rubyObject, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getSymbol0(threadContext.runtime, "UDPSocket"), file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString3(threadContext.runtime, 32));
        file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite4().call(threadContext, rubyObject, rubyObject, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getSymbol1(threadContext.runtime, "Timeout"), file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString4(threadContext.runtime, 32));
        file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite5().call(threadContext, rubyObject, rubyObject, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString5(threadContext.runtime, 32));
        class_0$RUBY$IP(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, threadContext, rubyObject, Block.NULL_BLOCK);
        return module__1$RUBY$Lister(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject class_0$RUBY$IP(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    10: ldc             "IP"
        //    12: swap           
        //    13: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    16: dup            
        //    17: astore_2       
        //    18: aload_1        
        //    19: swap           
        //    20: aload_0        
        //    21: aload_1        
        //    22: ldc             ",0,0,-1"
        //    24: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    27: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    30: aload_0        
        //    31: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite6:()Lorg/jruby/runtime/CallSite;
        //    34: aload_1        
        //    35: aload_2        
        //    36: aload_2        
        //    37: aload_0        
        //    38: aload_1        
        //    39: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    42: ldc             "mask"
        //    44: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    47: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: aload_1        
        //    51: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    54: goto            62
        //    57: aload_1        
        //    58: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    61: athrow         
        //    62: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  30     50     57     62     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_0$RUBY$IP(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_0$RUBY$IP(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Lister(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.module__2$RUBY$Utils:(Lruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__2$RUBY$Utils(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.module__3$RUBY$Scan:(Lruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__3$RUBY$Scan(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: dup            
        //     5: astore          4
        //     7: invokevirtual   org/jruby/runtime/DynamicScope.getValues:()[Lorg/jruby/runtime/builtin/IRubyObject;
        //    10: astore          5
        //    12: aload_1        
        //    13: aload_1        
        //    14: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    17: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    20: ldc             "Scan"
        //    22: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    25: dup            
        //    26: astore_2       
        //    27: aload_1        
        //    28: swap           
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc             ",0,0,-1"
        //    33: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getScope3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    36: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClass:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    39: aload_1        
        //    40: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //    43: astore          locals
        //    45: aload_0        
        //    46: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite7:()Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload_2        
        //    52: aload_0        
        //    53: aload_1        
        //    54: ldc             "Util"
        //    56: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    59: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    62: pop            
        //    63: aload_0        
        //    64: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite8:()Lorg/jruby/runtime/CallSite;
        //    67: aload_1        
        //    68: aload_2        
        //    69: aload_0        
        //    70: aload_1        
        //    71: ldc             "Struct"
        //    73: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: aload_0        
        //    77: aload_1        
        //    78: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    81: ldc             "mac"
        //    83: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    86: aload_0        
        //    87: aload_1        
        //    88: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    91: ldc             "ip"
        //    93: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    96: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    99: aload_1        
        //   100: ldc             "ARPEntry"
        //   102: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: pop            
        //   106: aload_0        
        //   107: aload_1        
        //   108: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   111: bipush          10
        //   113: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getFixnum0:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   116: aload_1        
        //   117: ldc             "MAX_UDP_SCAN"
        //   119: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   122: pop            
        //   123: aload_1        
        //   124: aload_2        
        //   125: aload_0        
        //   126: ldc_w           "arp_scan"
        //   129: ldc_w           "method__4$RUBY$arp_scan"
        //   132: ldc_w           "cnt;err,0,0,-1"
        //   135: iconst_0       
        //   136: ldc             "./lib//lister/utils/scan.rb"
        //   138: ldc_w           21
        //   141: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   144: ldc_w           "NONE"
        //   147: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   150: pop            
        //   151: aload_1        
        //   152: aload_2        
        //   153: aload_0        
        //   154: ldc_w           "each_neighbouring_address"
        //   157: ldc_w           "method__8$RUBY$each_neighbouring_address"
        //   160: ldc_w           "ip,0,0,-1"
        //   163: iconst_0       
        //   164: ldc             "./lib//lister/utils/scan.rb"
        //   166: ldc_w           41
        //   169: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   172: ldc_w           "NONE"
        //   175: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   178: pop            
        //   179: aload_1        
        //   180: aload_2        
        //   181: aload_0        
        //   182: ldc_w           "list_neighbours_arp"
        //   185: ldc_w           "method__9$RUBY$list_neighbours_arp"
        //   188: ldc             ",0,0,-1"
        //   190: iconst_0       
        //   191: ldc             "./lib//lister/utils/scan.rb"
        //   193: ldc_w           49
        //   196: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   199: ldc_w           "NONE"
        //   202: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   205: pop            
        //   206: aload_1        
        //   207: aload_2        
        //   208: aload_0        
        //   209: ldc_w           "count_neighbours_arp"
        //   212: ldc_w           "method__10$RUBY$count_neighbours_arp"
        //   215: ldc             ",0,0,-1"
        //   217: iconst_0       
        //   218: ldc             "./lib//lister/utils/scan.rb"
        //   220: ldc_w           54
        //   223: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   226: ldc_w           "NONE"
        //   229: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   232: pop            
        //   233: aload_1        
        //   234: aload_2        
        //   235: aload_0        
        //   236: ldc_w           "address"
        //   239: ldc_w           "method__11$RUBY$address"
        //   242: ldc             ",0,0,-1"
        //   244: iconst_0       
        //   245: ldc             "./lib//lister/utils/scan.rb"
        //   247: ldc_w           58
        //   250: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   253: ldc_w           "NONE"
        //   256: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   259: pop            
        //   260: aload_0        
        //   261: aload_1        
        //   262: aload_2        
        //   263: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //   266: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   269: invokestatic    ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.module__12$RUBY$Linux:(Lruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   272: pop            
        //   273: aload_0        
        //   274: aload_1        
        //   275: aload_2        
        //   276: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //   279: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   282: invokestatic    ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.module__21$RUBY$MacOs:(Lruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   285: pop            
        //   286: aload_0        
        //   287: aload_1        
        //   288: aload_2        
        //   289: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //   292: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   295: invokestatic    ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.module__30$RUBY$Windows:(Lruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   298: pop            
        //   299: aload_0        
        //   300: sipush          185
        //   303: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   306: aload_1        
        //   307: aload_2        
        //   308: aload_2        
        //   309: aload_2        
        //   310: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   313: pop            
        //   314: aload_0        
        //   315: sipush          186
        //   318: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   321: aload_1        
        //   322: aload_2        
        //   323: aload_0        
        //   324: aload_1        
        //   325: ldc_w           "Platform"
        //   328: bipush          47
        //   330: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   333: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   336: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   341: ifeq            430
        //   344: aload_0        
        //   345: sipush          187
        //   348: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   351: aload_1        
        //   352: aload_2        
        //   353: aload_2        
        //   354: aload_0        
        //   355: aload_1        
        //   356: ldc_w           "Windows"
        //   359: bipush          48
        //   361: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   364: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   367: pop            
        //   368: aload_0        
        //   369: sipush          188
        //   372: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   375: aload_1        
        //   376: aload_2        
        //   377: aload_2        
        //   378: aload_0        
        //   379: aload_1        
        //   380: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   383: bipush          33
        //   385: bipush          32
        //   387: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   390: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   393: pop            
        //   394: aload_0        
        //   395: sipush          189
        //   398: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   401: aload_1        
        //   402: aload_2        
        //   403: aload_2        
        //   404: aload_0        
        //   405: aload_1        
        //   406: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   409: bipush          34
        //   411: bipush          32
        //   413: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   416: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   419: pop            
        //   420: aload_0        
        //   421: aload_1        
        //   422: aload_2        
        //   423: aload_3        
        //   424: invokestatic    ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.chained_39_rescue_2$RUBY$SYNTHETICScan:(Lruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   427: goto            517
        //   430: aload_0        
        //   431: sipush          193
        //   434: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   437: aload_1        
        //   438: aload_2        
        //   439: aload_0        
        //   440: aload_1        
        //   441: ldc_w           "Platform"
        //   444: bipush          52
        //   446: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   449: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   452: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   457: ifeq            494
        //   460: aload_0        
        //   461: sipush          194
        //   464: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   467: aload_1        
        //   468: aload_2        
        //   469: aload_2        
        //   470: aload_0        
        //   471: aload_1        
        //   472: ldc_w           "MacOs"
        //   475: bipush          53
        //   477: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   480: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   483: pop            
        //   484: aload_0        
        //   485: aload_1        
        //   486: aload_2        
        //   487: aload_3        
        //   488: invokestatic    ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.chained_41_rescue_3$RUBY$SYNTHETICScan:(Lruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   491: goto            517
        //   494: aload_0        
        //   495: sipush          198
        //   498: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   501: aload_1        
        //   502: aload_2        
        //   503: aload_2        
        //   504: aload_0        
        //   505: aload_1        
        //   506: ldc_w           "Linux"
        //   509: bipush          58
        //   511: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   514: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   517: aload_1        
        //   518: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   521: goto            529
        //   524: aload_1        
        //   525: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   528: athrow         
        //   529: aload_1        
        //   530: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   533: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  45     489     4     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  45     521    524    529    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "arp_scan", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$arp_scan(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(RubyFixnum.zero(threadContext.runtime));
        if (file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite9().call(threadContext, rubyObject, rubyObject).isTrue()) {
            file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(10).call(threadContext, rubyObject, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(11).call(threadContext, rubyObject, rubyObject), file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString6(threadContext.runtime, 32));
        }
        return chained_5_rescue_1$RUBY$SYNTHETICarp_scan(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, threadContext, rubyObject, block);
    }
    
    public static IRubyObject chained_5_rescue_1$RUBY$SYNTHETICarp_scan(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext context, final IRubyObject self, final Block block) {
        context.getCurrentScope();
        final IRubyObject errorInfo = context.getErrorInfo();
        IRubyObject rubyObject;
        try {
            try {
                rubyObject = file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(12).callIter(context, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant2(context, "Timeout"), file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant3(context, "MAX_UDP_SCAN"), RuntimeHelpers.createBlock(context, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getBlockBody1(context, "block_0$RUBY$arp_scan,-1,skt,false,0,./lib//lister/utils/scan.rb,25,false")));
            }
            catch (JumpException.FlowControlException ex) {
                throw ex;
            }
            catch (Throwable t) {
                if (RuntimeHelpers.isJavaExceptionHandled(t, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstantFrom6(RuntimeHelpers.checkIsModule(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant5(context, "Timeout")), context, "Error"), context).isTrue()) {
                    RuntimeHelpers.storeExceptionInErrorInfo(t, context);
                    rubyObject = chained_6_rescue_line_34(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, context, self, block);
                    RuntimeHelpers.clearErrorInfo(context);
                }
                else {
                    if (!RuntimeHelpers.isJavaExceptionHandled(t, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant7(context, "SocketError"), context).isTrue()) {
                        throw t;
                    }
                    RuntimeHelpers.storeExceptionInErrorInfo(t, context);
                    rubyObject = chained_7_rescue_line_36(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, context, self, block);
                    RuntimeHelpers.clearErrorInfo(context);
                }
            }
        }
        catch (JumpException.FlowControlException ex2) {
            context.setErrorInfo(errorInfo);
            throw ex2;
        }
        context.setErrorInfo(errorInfo);
        return rubyObject;
    }
    
    public static IRubyObject block_0$RUBY$arp_scan(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    27: pop            
        //    28: pop            
        //    29: aload           locals
        //    31: aload_0        
        //    32: bipush          13
        //    34: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    37: aload_1        
        //    38: aload_2        
        //    39: aload_0        
        //    40: aload_1        
        //    41: ldc             "UDPSocket"
        //    43: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    46: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: pop            
        //    53: aload_0        
        //    54: bipush          14
        //    56: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    59: aload_1        
        //    60: aload_2        
        //    61: aload_2        
        //    62: aload_1        
        //    63: aload_2        
        //    64: aload_0        
        //    65: aload_1        
        //    66: ldc_w           "block_1$RUBY$arp_scan,1,addr,false,2,./lib//lister/utils/scan.rb,27,true"
        //    69: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    72: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    75: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    78: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  29     50      5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_1$RUBY$arp_scan(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          15
        //    28: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_1        
        //    34: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    37: aload_0        
        //    38: bipush          16
        //    40: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    43: aload_1        
        //    44: aload_2        
        //    45: aload_0        
        //    46: bipush          17
        //    48: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    51: aload_1        
        //    52: aload_2        
        //    53: aload_0        
        //    54: bipush          18
        //    56: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    59: aload_1        
        //    60: aload_2        
        //    61: aload_2        
        //    62: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    68: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    71: aload_0        
        //    72: bipush          19
        //    74: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    77: aload_1        
        //    78: aload_2        
        //    79: aload_0        
        //    80: bipush          20
        //    82: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    85: aload_1        
        //    86: aload_2        
        //    87: aload_0        
        //    88: bipush          21
        //    90: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    93: aload_1        
        //    94: aload_2        
        //    95: aload_2        
        //    96: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    99: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   102: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   108: aload_0        
        //   109: bipush          22
        //   111: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   114: aload_1        
        //   115: aload_2        
        //   116: aload           addr
        //   118: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   121: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   124: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   129: ifeq            139
        //   132: aload_1        
        //   133: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   136: goto            246
        //   139: aload_0        
        //   140: bipush          23
        //   142: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   145: aload_1        
        //   146: aload_2        
        //   147: aload           5
        //   149: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   152: aload_1        
        //   153: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   156: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   159: aload_0        
        //   160: aload_1        
        //   161: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   164: bipush          32
        //   166: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   169: aload_1        
        //   170: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   173: invokestatic    org/jruby/RubyFixnum.zero:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   176: aload_0        
        //   177: bipush          24
        //   179: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   182: aload_1        
        //   183: aload_2        
        //   184: aload           addr
        //   186: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   189: aload_0        
        //   190: aload_1        
        //   191: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   194: bipush          9
        //   196: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getFixnum1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   199: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   202: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   205: pop            
        //   206: aload           5
        //   208: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   211: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   214: aload_0        
        //   215: bipush          25
        //   217: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   220: aload_1        
        //   221: aload_2        
        //   222: aload           5
        //   224: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   227: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   230: aload_1        
        //   231: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   234: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   237: ldc2_w          1
        //   240: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;J)Lorg/jruby/runtime/builtin/IRubyObject;
        //   243: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   246: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     222     9     addr  Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject chained_6_rescue_line_34(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        threadContext.getCurrentScope().setValueOneDepthZero(RuntimeHelpers.getGlobalVariable(threadContext.runtime, "$!"));
        return file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(26).call(threadContext, rubyObject, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(27).call(threadContext, rubyObject, rubyObject), file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString8(threadContext.runtime, 32));
    }
    
    public static IRubyObject chained_7_rescue_line_36(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        threadContext.getCurrentScope().setValueOneDepthZero(RuntimeHelpers.getGlobalVariable(threadContext.runtime, "$!"));
        return file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(28).call(threadContext, rubyObject, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(29).call(threadContext, rubyObject, rubyObject), file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString9(threadContext.runtime, 32));
    }
    
    @JRubyMethod(name = "each_neighbouring_address", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$each_neighbouring_address(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(30).call(threadContext, self, self));
        if (locals.getValueZeroDepthZeroOrNil(threadContext.nil).isTrue()) {
            return file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(31).callIter(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(32).call(threadContext, self, locals.getValueZeroDepthZeroOrNil(threadContext.nil)), RuntimeHelpers.createBlock(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getBlockBody2(threadContext, "block_2$RUBY$each_neighbouring_address,2,int;idx,true,1,./lib//lister/utils/scan.rb,44,true")));
        }
        return threadContext.nil;
    }
    
    public static IRubyObject block_2$RUBY$each_neighbouring_address(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    56: aload_1        
        //    57: invokevirtual   org/jruby/runtime/ThreadContext.getFrameBlock:()Lorg/jruby/runtime/Block;
        //    60: aload_1        
        //    61: aload_0        
        //    62: bipush          33
        //    64: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    67: aload_1        
        //    68: aload_2        
        //    69: aload_0        
        //    70: aload_1        
        //    71: ldc             "IP"
        //    73: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant8:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    79: aload_0        
        //    80: swap           
        //    81: aload_1        
        //    82: ldc_w           "V4"
        //    85: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstantFrom9:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: aload           int
        //    90: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    93: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    96: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  56     41      9     int   Lorg/jruby/runtime/builtin/IRubyObject;
        //  56     41      10    idx   Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "list_neighbours_arp", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$list_neighbours_arp(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(34).call(threadContext, rubyObject, rubyObject);
        return file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(35).call(threadContext, rubyObject, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(36).call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "count_neighbours_arp", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__10$RUBY$count_neighbours_arp(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(37).call(threadContext, rubyObject, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(38).call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "address", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__11$RUBY$address(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@address") ? file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getByteList(10) : null) == null) {
            rubyObject = file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.setVariable0(threadContext.runtime, "@address", object, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(39).call(threadContext, object, object));
        }
        else if (!(rubyObject = file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getVariable0(threadContext.runtime, "@address", object)).isTrue()) {
            rubyObject = file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.setVariable1(threadContext.runtime, "@address", object, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(40).call(threadContext, object, object));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    public static IRubyObject module__12$RUBY$Linux(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    22: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getScope4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    25: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    28: aload_1        
        //    29: aload_2        
        //    30: aload_0        
        //    31: ldc_w           "address_cmd"
        //    34: ldc_w           "method__13$RUBY$address_cmd"
        //    37: ldc             ",0,0,-1"
        //    39: iconst_0       
        //    40: ldc             "./lib//lister/utils/scan.rb"
        //    42: ldc_w           63
        //    45: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    48: ldc_w           "NONE"
        //    51: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: pop            
        //    55: aload_1        
        //    56: aload_2        
        //    57: aload_0        
        //    58: ldc_w           "get_address"
        //    61: ldc_w           "method__14$RUBY$get_address"
        //    64: ldc_w           "line,0,0,-1"
        //    67: iconst_0       
        //    68: ldc             "./lib//lister/utils/scan.rb"
        //    70: ldc_w           67
        //    73: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    76: ldc_w           "NONE"
        //    79: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: pop            
        //    83: aload_1        
        //    84: aload_2        
        //    85: aload_0        
        //    86: ldc_w           "arp_neighbours_cmd"
        //    89: ldc_w           "method__15$RUBY$arp_neighbours_cmd"
        //    92: ldc             ",0,0,-1"
        //    94: iconst_0       
        //    95: ldc             "./lib//lister/utils/scan.rb"
        //    97: ldc_w           76
        //   100: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   103: ldc_w           "NONE"
        //   106: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   109: pop            
        //   110: aload_1        
        //   111: aload_2        
        //   112: aload_0        
        //   113: ldc_w           "lladdr_arp_neighbours_lines"
        //   116: ldc_w           "method__16$RUBY$lladdr_arp_neighbours_lines"
        //   119: ldc             ",0,0,-1"
        //   121: iconst_0       
        //   122: ldc             "./lib//lister/utils/scan.rb"
        //   124: ldc_w           80
        //   127: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   130: ldc_w           "NONE"
        //   133: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   136: pop            
        //   137: aload_1        
        //   138: aload_2        
        //   139: aload_0        
        //   140: ldc_w           "arp_neighbours"
        //   143: ldc_w           "method__17$RUBY$arp_neighbours"
        //   146: ldc             ",0,0,-1"
        //   148: iconst_0       
        //   149: ldc             "./lib//lister/utils/scan.rb"
        //   151: ldc_w           86
        //   154: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   157: ldc_w           "NONE"
        //   160: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   163: pop            
        //   164: aload_1        
        //   165: aload_2        
        //   166: aload_0        
        //   167: ldc_w           "avahi_scan_cmd"
        //   170: ldc_w           "method__18$RUBY$avahi_scan_cmd"
        //   173: ldc             ",0,0,-1"
        //   175: iconst_0       
        //   176: ldc             "./lib//lister/utils/scan.rb"
        //   178: ldc_w           94
        //   181: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   184: ldc_w           "NONE"
        //   187: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   190: pop            
        //   191: aload_1        
        //   192: aload_2        
        //   193: aload_0        
        //   194: ldc_w           "avahi_neighbours"
        //   197: ldc_w           "method__19$RUBY$avahi_neighbours"
        //   200: ldc             ",0,0,-1"
        //   202: iconst_0       
        //   203: ldc             "./lib//lister/utils/scan.rb"
        //   205: ldc_w           98
        //   208: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   211: ldc_w           "NONE"
        //   214: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   217: pop            
        //   218: aload_1        
        //   219: aload_2        
        //   220: aload_0        
        //   221: ldc_w           "count_neighbours_avahi"
        //   224: ldc_w           "method__20$RUBY$count_neighbours_avahi"
        //   227: ldc             ",0,0,-1"
        //   229: iconst_0       
        //   230: ldc             "./lib//lister/utils/scan.rb"
        //   232: ldc_w           104
        //   235: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   238: ldc_w           "NONE"
        //   241: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   244: pop            
        //   245: aload_1        
        //   246: aload_2        
        //   247: ldc_w           "count_neighbours_zeroconf"
        //   250: ldc_w           "count_neighbours_avahi"
        //   253: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defineAlias:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/Object;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   256: aload_1        
        //   257: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   260: goto            268
        //   263: aload_1        
        //   264: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   267: athrow         
        //   268: aload_1        
        //   269: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   272: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  28     260    263    268    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "address_cmd", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__13$RUBY$address_cmd(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        return RubyString.newStringLight(context.runtime, 20).append(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString(context.runtime, 11, 32)).append(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(41).call(context, rubyObject, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant(context, "Route", 10)).asString());
    }
    
    @JRubyMethod(name = "get_address", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__14$RUBY$get_address(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext context, final IRubyObject self, final Block block) {
        final DynamicScope locals = context.getCurrentScope();
        locals.setValueZeroDepthZero(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(42).callIter(context, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(43).call(context, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(44).call(context, self, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(45).call(context, self, self))), RuntimeHelpers.createBlock(context, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getBlockBody3(context, "block_3$RUBY$get_address,1,l,false,2,./lib//lister/utils/scan.rb,68,false"))));
        return locals.getValueZeroDepthZeroOrNil(context.nil).isTrue() ? file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(46).call(context, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant(context, "IP", 11), file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(47).call(context, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(48).call(context, self, locals.getValueZeroDepthZeroOrNil(context.nil)), RubyFixnum.one(context.runtime))) : context.nil;
    }
    
    public static IRubyObject block_3$RUBY$get_address(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    41: bipush          12
        //    43: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getByteList:(I)Lorg/jruby/util/ByteList;
        //    46: ldc_w           512
        //    49: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getRegexp0:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //    52: aload           locals
        //    54: aload_1        
        //    55: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    61: aload_1        
        //    62: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.match3:(Lorg/jruby/RubyRegexp;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: areturn        
        //    66: pop            
        //    67: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     31      5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     66     66     70     Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    @JRubyMethod(name = "arp_neighbours_cmd", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__15$RUBY$arp_neighbours_cmd(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString(threadContext.runtime, 13, 32);
    }
    
    @JRubyMethod(name = "lladdr_arp_neighbours_lines", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__16$RUBY$lladdr_arp_neighbours_lines(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(49).callIter(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(50).call(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(51).call(threadContext, self, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(52).call(threadContext, self, self))), RuntimeHelpers.createBlock(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getBlockBody4(threadContext, "block_4$RUBY$lladdr_arp_neighbours_lines,1,l,false,2,./lib//lister/utils/scan.rb,81,false")));
    }
    
    public static IRubyObject block_4$RUBY$lladdr_arp_neighbours_lines(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: bipush          53
        //    38: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_0        
        //    44: bipush          54
        //    46: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload_0        
        //    52: bipush          55
        //    54: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    57: aload_1        
        //    58: aload_2        
        //    59: aload           locals
        //    61: aload_1        
        //    62: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    68: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    71: aload_1        
        //    72: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    75: invokestatic    org/jruby/RubyFixnum.three:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    78: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    81: aload_0        
        //    82: aload_1        
        //    83: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    86: bipush          14
        //    88: bipush          32
        //    90: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    93: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    96: areturn        
        //    97: pop            
        //    98: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     62      5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     97     97     101    Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    @JRubyMethod(name = "arp_neighbours", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__17$RUBY$arp_neighbours(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(56).callIter(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(57).call(threadContext, self, self), RuntimeHelpers.createBlock(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getBlockBody5(threadContext, "block_5$RUBY$arp_neighbours,1,l;mac;ip,false,2,./lib//lister/utils/scan.rb,87,false")));
    }
    
    public static IRubyObject block_5$RUBY$arp_neighbours(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    49: aload_0        
        //    50: bipush          58
        //    52: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    55: aload_1        
        //    56: aload_2        
        //    57: aload_0        
        //    58: bipush          59
        //    60: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    63: aload_1        
        //    64: aload_2        
        //    65: aload           locals
        //    67: aload_1        
        //    68: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    71: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: aload_1        
        //    78: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    81: invokestatic    org/jruby/RubyFixnum.four:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    84: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    87: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    90: pop            
        //    91: aload           locals
        //    93: aload_0        
        //    94: bipush          60
        //    96: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    99: aload_1        
        //   100: aload_2        
        //   101: aload_0        
        //   102: bipush          61
        //   104: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   107: aload_1        
        //   108: aload_2        
        //   109: aload_0        
        //   110: bipush          62
        //   112: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   115: aload_1        
        //   116: aload_2        
        //   117: aload           locals
        //   119: aload_1        
        //   120: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   123: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   126: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   129: aload_1        
        //   130: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   133: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   136: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   139: aload_0        
        //   140: aload_1        
        //   141: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   144: bipush          15
        //   146: bipush          32
        //   148: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   151: aload_0        
        //   152: aload_1        
        //   153: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   156: bipush          16
        //   158: bipush          32
        //   160: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   163: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   166: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   169: pop            
        //   170: aload_0        
        //   171: bipush          63
        //   173: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   176: aload_1        
        //   177: aload_2        
        //   178: aload_0        
        //   179: aload_1        
        //   180: ldc             "ARPEntry"
        //   182: bipush          12
        //   184: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   187: aload           locals
        //   189: aload_1        
        //   190: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   193: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   196: aload           locals
        //   198: aload_1        
        //   199: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   202: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   205: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   208: areturn        
        //   209: pop            
        //   210: goto            47
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  47     162     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  47     209    209    213    Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    @JRubyMethod(name = "avahi_scan_cmd", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__18$RUBY$avahi_scan_cmd(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString(threadContext.runtime, 17, 32);
    }
    
    @JRubyMethod(name = "avahi_neighbours", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__19$RUBY$avahi_neighbours(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(64).call(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(65).callIter(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(66).call(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(67).call(threadContext, self, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(68).call(threadContext, self, self))), RuntimeHelpers.createBlock(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getBlockBody6(threadContext, "block_6$RUBY$avahi_neighbours,1,l,false,2,./lib//lister/utils/scan.rb,99,false"))));
    }
    
    public static IRubyObject block_6$RUBY$avahi_neighbours(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: bipush          69
        //    38: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_0        
        //    44: bipush          70
        //    46: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload           locals
        //    53: aload_1        
        //    54: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: aload_0        
        //    61: aload_1        
        //    62: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    65: bipush          18
        //    67: bipush          32
        //    69: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    72: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: aload_1        
        //    76: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    79: invokestatic    org/jruby/RubyFixnum.three:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    82: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    85: areturn        
        //    86: pop            
        //    87: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     51      5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     86     86     90     Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    @JRubyMethod(name = "count_neighbours_avahi", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__20$RUBY$count_neighbours_avahi(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(71).call(threadContext, rubyObject, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(72).call(threadContext, rubyObject, rubyObject));
    }
    
    public static IRubyObject module__12$RUBY$Linux(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__12$RUBY$Linux(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__21$RUBY$MacOs(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc_w           "MacOs"
        //    11: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    14: dup            
        //    15: astore_2       
        //    16: aload_1        
        //    17: swap           
        //    18: aload_0        
        //    19: aload_1        
        //    20: ldc             ",0,0,-1"
        //    22: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getScope5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    25: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    28: aload_1        
        //    29: aload_2        
        //    30: aload_0        
        //    31: ldc_w           "address_cmd"
        //    34: ldc_w           "method__22$RUBY$address_cmd"
        //    37: ldc             ",0,0,-1"
        //    39: iconst_0       
        //    40: ldc             "./lib//lister/utils/scan.rb"
        //    42: ldc_w           112
        //    45: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    48: ldc_w           "NONE"
        //    51: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: pop            
        //    55: aload_1        
        //    56: aload_2        
        //    57: aload_0        
        //    58: ldc_w           "get_address"
        //    61: ldc_w           "method__23$RUBY$get_address"
        //    64: ldc_w           "line;ary;ip;wmask,0,0,-1"
        //    67: iconst_0       
        //    68: ldc             "./lib//lister/utils/scan.rb"
        //    70: ldc_w           116
        //    73: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    76: ldc_w           "NONE"
        //    79: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: pop            
        //    83: aload_1        
        //    84: aload_2        
        //    85: aload_0        
        //    86: ldc_w           "arp_neighbours_cmd"
        //    89: ldc_w           "method__24$RUBY$arp_neighbours_cmd"
        //    92: ldc             ",0,0,-1"
        //    94: iconst_0       
        //    95: ldc             "./lib//lister/utils/scan.rb"
        //    97: ldc_w           130
        //   100: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   103: ldc_w           "NONE"
        //   106: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   109: pop            
        //   110: aload_1        
        //   111: aload_2        
        //   112: aload_0        
        //   113: ldc_w           "non_incomplete_arp_neighbours_lines"
        //   116: ldc_w           "method__25$RUBY$non_incomplete_arp_neighbours_lines"
        //   119: ldc             ",0,0,-1"
        //   121: iconst_0       
        //   122: ldc             "./lib//lister/utils/scan.rb"
        //   124: ldc_w           134
        //   127: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   130: ldc_w           "NONE"
        //   133: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   136: pop            
        //   137: aload_1        
        //   138: aload_2        
        //   139: aload_0        
        //   140: ldc_w           "arp_neighbours"
        //   143: ldc_w           "method__26$RUBY$arp_neighbours"
        //   146: ldc             ",0,0,-1"
        //   148: iconst_0       
        //   149: ldc             "./lib//lister/utils/scan.rb"
        //   151: ldc_w           140
        //   154: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   157: ldc_w           "NONE"
        //   160: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   163: pop            
        //   164: aload_1        
        //   165: aload_2        
        //   166: aload_0        
        //   167: ldc_w           "count_neighbours_zeroconf"
        //   170: ldc_w           "method__27$RUBY$count_neighbours_zeroconf"
        //   173: ldc             ",0,0,-1"
        //   175: iconst_0       
        //   176: ldc             "./lib//lister/utils/scan.rb"
        //   178: ldc_w           156
        //   181: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   184: ldc_w           "NONE"
        //   187: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   190: pop            
        //   191: aload_0        
        //   192: aload_1        
        //   193: aload_2        
        //   194: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //   197: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   200: invokestatic    ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.module__28$RUBY$AppleSoft:(Lruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "address_cmd", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__22$RUBY$address_cmd(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        return RubyString.newStringLight(context.runtime, 20).append(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString(context.runtime, 19, 32)).append(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(73).call(context, rubyObject, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant(context, "Route", 13)).asString());
    }
    
    @JRubyMethod(name = "get_address", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__23$RUBY$get_address(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(74).callIter(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(75).call(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(76).call(threadContext, self, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(77).call(threadContext, self, self))), RuntimeHelpers.createBlock(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getBlockBody7(threadContext, "block_7$RUBY$get_address,1,l;ary,false,2,./lib//lister/utils/scan.rb,117,false"))));
        IRubyObject rubyObject;
        if (locals.getValueZeroDepthZeroOrNil(threadContext.nil).isTrue()) {
            locals.setValueOneDepthZero(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(82).call(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(83).call(threadContext, self, locals.getValueZeroDepthZeroOrNil(threadContext.nil))));
            locals.setValueTwoDepthZero(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(84).call(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant(threadContext, "IP", 14), file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(85).call(threadContext, self, locals.getValueOneDepthZeroOrNil(threadContext.nil), RubyFixnum.one(threadContext.runtime))));
            locals.setValueThreeDepthZero(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(86).call(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstantFrom(RuntimeHelpers.checkIsModule(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant(threadContext, "IP", 15)), threadContext, "V4", 16), file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(87).call(threadContext, self, locals.getValueOneDepthZeroOrNil(threadContext.nil), RubyFixnum.three(threadContext.runtime))));
            final IRubyObject valueTwoDepthZeroOrNil = locals.getValueTwoDepthZeroOrNil(threadContext.nil);
            RuntimeHelpers.doAttrAsgn(valueTwoDepthZeroOrNil, RuntimeHelpers.selectAttrAsgnCallSite(valueTwoDepthZeroOrNil, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(88), file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(89)), file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(90).call(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(91).call(threadContext, self, locals.getValueThreeDepthZeroOrNil(threadContext.nil))), threadContext, self);
            rubyObject = locals.getValueTwoDepthZeroOrNil(threadContext.nil);
        }
        else {
            rubyObject = threadContext.nil;
        }
        return rubyObject;
    }
    
    public static IRubyObject block_7$RUBY$get_address(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    44: bipush          78
        //    46: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload_0        
        //    52: bipush          79
        //    54: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    57: aload_1        
        //    58: aload_2        
        //    59: aload           locals
        //    61: aload_1        
        //    62: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    68: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    71: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: pop            
        //    78: aload_0        
        //    79: bipush          80
        //    81: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    84: aload_1        
        //    85: aload_2        
        //    86: aload_0        
        //    87: bipush          81
        //    89: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    92: aload_1        
        //    93: aload_2        
        //    94: aload           locals
        //    96: aload_1        
        //    97: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   100: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   103: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   106: aload_0        
        //   107: aload_1        
        //   108: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   111: bipush          12
        //   113: bipush          32
        //   115: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   118: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   121: areturn        
        //   122: pop            
        //   123: goto            41
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  41     81      5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  41     122    122    126    Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    @JRubyMethod(name = "arp_neighbours_cmd", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__24$RUBY$arp_neighbours_cmd(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString(threadContext.runtime, 20, 32);
    }
    
    @JRubyMethod(name = "non_incomplete_arp_neighbours_lines", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__25$RUBY$non_incomplete_arp_neighbours_lines(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(92).callIter(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(93).call(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(94).call(threadContext, self, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(95).call(threadContext, self, self))), RuntimeHelpers.createBlock(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getBlockBody8(threadContext, "block_8$RUBY$non_incomplete_arp_neighbours_lines,1,line,false,2,./lib//lister/utils/scan.rb,135,false")));
    }
    
    public static IRubyObject block_8$RUBY$non_incomplete_arp_neighbours_lines(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    41: bipush          21
        //    43: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getByteList:(I)Lorg/jruby/util/ByteList;
        //    46: ldc_w           512
        //    49: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getRegexp1:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //    52: aload           locals
        //    54: aload_1        
        //    55: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    61: aload_1        
        //    62: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.match3:(Lorg/jruby/RubyRegexp;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: areturn        
        //    66: pop            
        //    67: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     31      5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     66     66     70     Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    @JRubyMethod(name = "arp_neighbours", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__26$RUBY$arp_neighbours(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(96).callIter(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(97).call(threadContext, self, self), RuntimeHelpers.createBlock(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getBlockBody(threadContext, 12, "block_9$RUBY$arp_neighbours,1,str;mac;fixed_mac;ip,false,2,./lib//lister/utils/scan.rb,141,false")));
    }
    
    public static IRubyObject block_9$RUBY$arp_neighbours(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    53: aload           locals
        //    55: aload_0        
        //    56: bipush          98
        //    58: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    61: aload_1        
        //    62: aload_2        
        //    63: aload_0        
        //    64: bipush          99
        //    66: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    69: aload_1        
        //    70: aload_2        
        //    71: aload           locals
        //    73: aload_1        
        //    74: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: aload_1        
        //    84: aload_2        
        //    85: aload_0        
        //    86: aload_1        
        //    87: ldc_w           "block_10$RUBY$arp_neighbours,1,i,false,2,./lib//lister/utils/scan.rb,142,false"
        //    90: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getBlockBody9:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    93: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    96: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    99: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   102: pop            
        //   103: aload           locals
        //   105: aload_0        
        //   106: bipush          100
        //   108: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   111: aload_1        
        //   112: aload_2        
        //   113: aload_0        
        //   114: bipush          101
        //   116: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   119: aload_1        
        //   120: aload_2        
        //   121: aload_0        
        //   122: bipush          102
        //   124: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   127: aload_1        
        //   128: aload_2        
        //   129: aload           locals
        //   131: aload_1        
        //   132: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   135: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   138: aload_0        
        //   139: aload_1        
        //   140: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   143: bipush          23
        //   145: bipush          32
        //   147: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   150: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   153: aload_1        
        //   154: aload_2        
        //   155: aload_0        
        //   156: aload_1        
        //   157: bipush          10
        //   159: ldc_w           "block_11$RUBY$arp_neighbours,1,hex,false,2,./lib//lister/utils/scan.rb,143,true"
        //   162: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getBlockBody:(Lorg/jruby/runtime/ThreadContext;ILjava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   165: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   168: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   171: aload_0        
        //   172: aload_1        
        //   173: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   176: bipush          23
        //   178: bipush          32
        //   180: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   183: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   186: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   189: pop            
        //   190: aload           locals
        //   192: aload_1        
        //   193: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   196: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   199: pop            
        //   200: aload_0        
        //   201: bipush          106
        //   203: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   206: aload_1        
        //   207: aload_2        
        //   208: aload_0        
        //   209: bipush          107
        //   211: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   214: aload_1        
        //   215: aload_2        
        //   216: aload           locals
        //   218: aload_1        
        //   219: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   222: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   225: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   228: aload_1        
        //   229: aload_2        
        //   230: aload_0        
        //   231: aload_1        
        //   232: bipush          11
        //   234: ldc_w           "block_12$RUBY$arp_neighbours,1,i,false,2,./lib//lister/utils/scan.rb,148,false"
        //   237: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getBlockBody:(Lorg/jruby/runtime/ThreadContext;ILjava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   240: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   243: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   246: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   251: ifeq            302
        //   254: aload           locals
        //   256: aload_0        
        //   257: bipush          108
        //   259: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   262: aload_1        
        //   263: aload_2        
        //   264: aload_0        
        //   265: bipush          109
        //   267: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   270: aload_1        
        //   271: aload_2        
        //   272: aload_0        
        //   273: aload_1        
        //   274: ldc_w           "Regexp"
        //   277: bipush          17
        //   279: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   282: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   285: aload_1        
        //   286: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   289: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   292: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   295: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   298: pop            
        //   299: goto            302
        //   302: aload_0        
        //   303: bipush          110
        //   305: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   308: aload_1        
        //   309: aload_2        
        //   310: aload_0        
        //   311: aload_1        
        //   312: ldc             "ARPEntry"
        //   314: bipush          18
        //   316: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   319: aload           locals
        //   321: aload_1        
        //   322: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   325: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   328: aload           locals
        //   330: aload_1        
        //   331: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   334: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   337: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   340: areturn        
        //   341: pop            
        //   342: goto            53
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  53     288     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  53     341    341    345    Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    public static IRubyObject block_10$RUBY$arp_neighbours(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    43: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getByteList:(I)Lorg/jruby/util/ByteList;
        //    46: ldc_w           512
        //    49: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getRegexp2:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //    52: aload           locals
        //    54: aload_1        
        //    55: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    61: aload_1        
        //    62: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.match3:(Lorg/jruby/RubyRegexp;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: areturn        
        //    66: pop            
        //    67: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     31      5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     66     66     70     Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    public static IRubyObject block_11$RUBY$arp_neighbours(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          103
        //    28: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_0        
        //    34: bipush          104
        //    36: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    39: aload_1        
        //    40: aload_2        
        //    41: aload           hex
        //    43: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    46: ldc2_w          2
        //    49: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;J)Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    57: ifeq            65
        //    60: aload           hex
        //    62: goto            90
        //    65: aload_0        
        //    66: bipush          105
        //    68: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    71: aload_1        
        //    72: aload_2        
        //    73: aload_0        
        //    74: aload_1        
        //    75: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    78: bipush          24
        //    80: bipush          32
        //    82: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    85: aload           hex
        //    87: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    90: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     66      9     hex   Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject block_12$RUBY$arp_neighbours(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    41: bipush          25
        //    43: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getByteList:(I)Lorg/jruby/util/ByteList;
        //    46: ldc_w           512
        //    49: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getRegexp3:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //    52: aload           locals
        //    54: aload_1        
        //    55: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    61: aload_1        
        //    62: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.match3:(Lorg/jruby/RubyRegexp;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: areturn        
        //    66: pop            
        //    67: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     31      5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     66     66     70     Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    @JRubyMethod(name = "count_neighbours_zeroconf", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__27$RUBY$count_neighbours_zeroconf(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString(threadContext.runtime, 26, 32);
    }
    
    public static IRubyObject module__28$RUBY$AppleSoft(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc_w           "AppleSoft"
        //    11: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    14: dup            
        //    15: astore_2       
        //    16: aload_1        
        //    17: swap           
        //    18: aload_0        
        //    19: aload_1        
        //    20: ldc             ",0,0,-1"
        //    22: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getScope6:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    25: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    28: aload_1        
        //    29: aload_2        
        //    30: aload_0        
        //    31: ldc_w           "count_neighbours_zeroconf"
        //    34: ldc_w           "method__29$RUBY$count_neighbours_zeroconf"
        //    37: ldc_w           "counter,0,0,-1"
        //    40: iconst_0       
        //    41: ldc             "./lib//lister/utils/scan.rb"
        //    43: ldc_w           163
        //    46: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    49: ldc_w           "NONE"
        //    52: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: aload_1        
        //    56: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    59: goto            67
        //    62: aload_1        
        //    63: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    66: athrow         
        //    67: aload_1        
        //    68: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    71: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  28     59     62     67     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "count_neighbours_zeroconf", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__29$RUBY$count_neighbours_zeroconf(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(111).call(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstantFrom(RuntimeHelpers.checkIsModule(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant(threadContext, "Darwin", 19)), threadContext, "ZeroconfCounter", 20)));
        file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(112).call(threadContext, self, locals.getValueZeroDepthZeroOrNil(threadContext.nil), file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getFixnum0(threadContext.runtime, 10));
        return file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(113).call(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(114).call(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(115).callIter(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(116).call(threadContext, self, locals.getValueZeroDepthZeroOrNil(threadContext.nil)), RuntimeHelpers.createBlock(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getBlockBody(threadContext, 13, "block_13$RUBY$count_neighbours_zeroconf,1,s,false,2,./lib//lister/utils/scan.rb,166,true")))));
    }
    
    public static IRubyObject block_13$RUBY$count_neighbours_zeroconf(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          117
        //    28: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           s
        //    35: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    38: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     14      9     s     Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__28$RUBY$AppleSoft(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__28$RUBY$AppleSoft(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__21$RUBY$MacOs(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__21$RUBY$MacOs(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__30$RUBY$Windows(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    22: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getScope7:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    25: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    28: aload_1        
        //    29: aload_2        
        //    30: aload_0        
        //    31: ldc_w           "get_address"
        //    34: ldc_w           "method__31$RUBY$get_address"
        //    37: ldc_w           "addr;mask;ip;wmask,0,0,-1"
        //    40: iconst_0       
        //    41: ldc             "./lib//lister/utils/scan.rb"
        //    43: ldc_w           173
        //    46: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    49: ldc_w           "NONE"
        //    52: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: pop            
        //    56: aload_1        
        //    57: aload_2        
        //    58: aload_0        
        //    59: ldc_w           "arp_neighbours_rows"
        //    62: ldc_w           "method__32$RUBY$arp_neighbours_rows"
        //    65: ldc_w           "p_len;ptr;table;p_tables,0,0,-1"
        //    68: iconst_0       
        //    69: ldc             "./lib//lister/utils/scan.rb"
        //    71: ldc_w           195
        //    74: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    77: ldc_w           "NONE"
        //    80: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: pop            
        //    84: aload_1        
        //    85: aload_2        
        //    86: aload_0        
        //    87: ldc_w           "valid_arp_neighbours_rows"
        //    90: ldc_w           "method__33$RUBY$valid_arp_neighbours_rows"
        //    93: ldc             ",0,0,-1"
        //    95: iconst_0       
        //    96: ldc             "./lib//lister/utils/scan.rb"
        //    98: ldc_w           211
        //   101: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   104: ldc_w           "NONE"
        //   107: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   110: pop            
        //   111: aload_1        
        //   112: aload_2        
        //   113: aload_0        
        //   114: ldc_w           "arp_neighbours"
        //   117: ldc_w           "method__34$RUBY$arp_neighbours"
        //   120: ldc             ",0,0,-1"
        //   122: iconst_0       
        //   123: ldc             "./lib//lister/utils/scan.rb"
        //   125: ldc_w           217
        //   128: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   131: ldc_w           "NONE"
        //   134: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   137: pop            
        //   138: aload_1        
        //   139: aload_2        
        //   140: aload_0        
        //   141: ldc_w           "arp_neighbours_cmd"
        //   144: ldc_w           "method__35$RUBY$arp_neighbours_cmd"
        //   147: ldc             ",0,0,-1"
        //   149: iconst_0       
        //   150: ldc             "./lib//lister/utils/scan.rb"
        //   152: ldc_w           227
        //   155: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   158: ldc_w           "NONE"
        //   161: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   164: pop            
        //   165: aload_1        
        //   166: aload_2        
        //   167: aload_0        
        //   168: ldc_w           "count_neighbours_zeroconf"
        //   171: ldc_w           "method__36$RUBY$count_neighbours_zeroconf"
        //   174: ldc             ",0,0,-1"
        //   176: iconst_0       
        //   177: ldc             "./lib//lister/utils/scan.rb"
        //   179: ldc_w           231
        //   182: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   185: ldc_w           "NONE"
        //   188: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   191: pop            
        //   192: aload_0        
        //   193: aload_1        
        //   194: aload_2        
        //   195: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //   198: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   201: invokestatic    ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.module__37$RUBY$AppleSoft:(Lruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   204: aload_1        
        //   205: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   208: goto            216
        //   211: aload_1        
        //   212: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   215: athrow         
        //   216: aload_1        
        //   217: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   220: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  28     208    211    216    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "get_address", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__31$RUBY$get_address(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext context, final IRubyObject self, final Block block) {
        final DynamicScope locals = context.getCurrentScope();
        locals.setValueZeroDepthZero(context.nil);
        locals.setValueOneDepthZero(context.nil);
        file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(118).callIter(context, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(119).call(context, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(120).call(context, self, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString(context.runtime, 27, 32))), RuntimeHelpers.createBlock(context, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getBlockBody(context, 14, "block_14$RUBY$get_address,1,l,false,2,./lib//lister/utils/scan.rb,176,false")));
        if (locals.getValueZeroDepthZeroOrNil(context.nil).isTrue()) {
            locals.setValueTwoDepthZero(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(140).call(context, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant(context, "IP", 21), locals.getValueZeroDepthZeroOrNil(context.nil)));
            locals.setValueThreeDepthZero(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(141).call(context, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant(context, "IP", 22), locals.getValueOneDepthZeroOrNil(context.nil)));
            final IRubyObject valueTwoDepthZeroOrNil = locals.getValueTwoDepthZeroOrNil(context.nil);
            RuntimeHelpers.doAttrAsgn(valueTwoDepthZeroOrNil, RuntimeHelpers.selectAttrAsgnCallSite(valueTwoDepthZeroOrNil, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(142), file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(143)), file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(144).call(context, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(145).call(context, self, locals.getValueThreeDepthZeroOrNil(context.nil))), context, self);
        }
        return locals.getValueTwoDepthZeroOrNil(context.nil);
    }
    
    public static IRubyObject block_14$RUBY$get_address(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: bipush          121
        //    38: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload           locals
        //    45: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    48: aload_1        
        //    49: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    63: ifeq            291
        //    66: aload_0        
        //    67: aload_1        
        //    68: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    71: aload_0        
        //    72: bipush          28
        //    74: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getByteList:(I)Lorg/jruby/util/ByteList;
        //    77: ldc_w           512
        //    80: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getRegexp4:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //    83: aload           locals
        //    85: aload_1        
        //    86: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    92: aload_1        
        //    93: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.match3:(Lorg/jruby/RubyRegexp;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    96: dup            
        //    97: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   102: ifeq            208
        //   105: pop            
        //   106: aload_0        
        //   107: bipush          122
        //   109: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   112: aload_1        
        //   113: aload_2        
        //   114: aload_0        
        //   115: bipush          123
        //   117: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   120: aload_1        
        //   121: aload_2        
        //   122: aload_0        
        //   123: bipush          124
        //   125: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   128: aload_1        
        //   129: aload_2        
        //   130: aload_0        
        //   131: bipush          125
        //   133: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   136: aload_1        
        //   137: aload_2        
        //   138: aload_0        
        //   139: bipush          126
        //   141: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   144: aload_1        
        //   145: aload_2        
        //   146: aload_0        
        //   147: bipush          127
        //   149: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   152: aload_1        
        //   153: aload_2        
        //   154: aload           locals
        //   156: aload_1        
        //   157: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   160: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   163: aload_0        
        //   164: aload_1        
        //   165: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   168: bipush          23
        //   170: bipush          32
        //   172: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   175: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   178: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   181: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   184: aload_0        
        //   185: aload_1        
        //   186: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   189: bipush          29
        //   191: bipush          32
        //   193: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   196: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   199: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   202: ldc2_w          4
        //   205: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;J)Lorg/jruby/runtime/builtin/IRubyObject;
        //   208: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   213: ifeq            284
        //   216: aload           locals
        //   218: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   221: aload_0        
        //   222: sipush          128
        //   225: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   228: aload_1        
        //   229: aload_2        
        //   230: aload_0        
        //   231: sipush          129
        //   234: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   237: aload_1        
        //   238: aload_2        
        //   239: aload_0        
        //   240: sipush          130
        //   243: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   246: aload_1        
        //   247: aload_2        
        //   248: aload           locals
        //   250: aload_1        
        //   251: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   254: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   257: aload_0        
        //   258: aload_1        
        //   259: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   262: bipush          23
        //   264: bipush          32
        //   266: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   269: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   272: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   275: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   278: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   281: goto            288
        //   284: aload_1        
        //   285: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   288: goto            488
        //   291: aload_0        
        //   292: sipush          131
        //   295: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   298: aload_1        
        //   299: aload_2        
        //   300: aload_0        
        //   301: sipush          132
        //   304: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   307: aload_1        
        //   308: aload_2        
        //   309: aload_0        
        //   310: sipush          133
        //   313: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   316: aload_1        
        //   317: aload_2        
        //   318: aload_0        
        //   319: sipush          134
        //   322: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   325: aload_1        
        //   326: aload_2        
        //   327: aload_0        
        //   328: sipush          135
        //   331: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   334: aload_1        
        //   335: aload_2        
        //   336: aload_0        
        //   337: sipush          136
        //   340: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   343: aload_1        
        //   344: aload_2        
        //   345: aload           locals
        //   347: aload_1        
        //   348: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   351: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   354: aload_0        
        //   355: aload_1        
        //   356: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   359: bipush          23
        //   361: bipush          32
        //   363: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   366: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   369: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   372: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   375: aload_0        
        //   376: aload_1        
        //   377: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   380: bipush          29
        //   382: bipush          32
        //   384: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   387: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   390: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   393: ldc2_w          4
        //   396: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;J)Lorg/jruby/runtime/builtin/IRubyObject;
        //   399: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   404: ifeq            484
        //   407: aload           locals
        //   409: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   412: aload_0        
        //   413: sipush          137
        //   416: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   419: aload_1        
        //   420: aload_2        
        //   421: aload_0        
        //   422: sipush          138
        //   425: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   428: aload_1        
        //   429: aload_2        
        //   430: aload_0        
        //   431: sipush          139
        //   434: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   437: aload_1        
        //   438: aload_2        
        //   439: aload           locals
        //   441: aload_1        
        //   442: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   445: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   448: aload_0        
        //   449: aload_1        
        //   450: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   453: bipush          23
        //   455: bipush          32
        //   457: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   460: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   463: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   466: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   469: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   472: pop            
        //   473: aload_1        
        //   474: aload_1        
        //   475: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   478: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.breakJump:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   481: goto            488
        //   484: aload_1        
        //   485: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   488: areturn        
        //   489: pop            
        //   490: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     454     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     489    489    493    Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    @JRubyMethod(name = "arp_neighbours_rows", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__32$RUBY$arp_neighbours_rows(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext context, final IRubyObject self, final Block block) {
        final DynamicScope locals = context.getCurrentScope();
        locals.setValueZeroDepthZero(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(146).call(context, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstantFrom(RuntimeHelpers.checkIsModule(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant(context, "FFI", 23)), context, "MemoryPointer", 24), file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getSymbol5(context.runtime, "ulong")));
        file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(147).call(context, self, locals.getValueZeroDepthZeroOrNil(context.nil), RubyFixnum.zero(context.runtime));
        file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(148).call(context, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstantFrom(RuntimeHelpers.checkIsModule(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant(context, "Win32", 25)), context, "IPHelper", 26), context.nil, locals.getValueZeroDepthZeroOrNil(context.nil), RubyFixnum.zero(context.runtime));
        locals.setValueOneDepthZero(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(149).call(context, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstantFrom(RuntimeHelpers.checkIsModule(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant(context, "FFI", 27)), context, "MemoryPointer", 28), file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(150).call(context, self, locals.getValueZeroDepthZeroOrNil(context.nil))));
        file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(151).call(context, self, locals.getValueZeroDepthZeroOrNil(context.nil), file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(152).call(context, self, locals.getValueOneDepthZeroOrNil(context.nil)));
        file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(153).call(context, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstantFrom(RuntimeHelpers.checkIsModule(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant(context, "Win32", 29)), context, "IPHelper", 30), locals.getValueOneDepthZeroOrNil(context.nil), locals.getValueZeroDepthZeroOrNil(context.nil), RubyFixnum.zero(context.runtime));
        locals.setValueTwoDepthZero(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(154).call(context, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstantFrom(RuntimeHelpers.checkIsModule(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstantFrom(RuntimeHelpers.checkIsModule(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant(context, "Win32", 31)), context, "MIB", 32)), context, "IpNetTable", 33), locals.getValueOneDepthZeroOrNil(context.nil)));
        locals.setValueThreeDepthZero(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(155).call(context, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(156).call(context, self, locals.getValueTwoDepthZeroOrNil(context.nil), file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getSymbol6(context.runtime, "table"))));
        return file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(157).callIter(context, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(158).call(context, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(159).call(context, self, locals.getValueTwoDepthZeroOrNil(context.nil), file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getSymbol7(context.runtime, "dwNumEntries"))), RuntimeHelpers.createBlock(context, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getBlockBody(context, 15, "block_15$RUBY$arp_neighbours_rows,1,i;addr,false,2,./lib//lister/utils/scan.rb,205,true")));
    }
    
    public static IRubyObject block_15$RUBY$arp_neighbours_rows(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    32: sipush          160
        //    35: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    38: aload_1        
        //    39: aload_2        
        //    40: aload_0        
        //    41: sipush          161
        //    44: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    47: aload_1        
        //    48: aload_2        
        //    49: aload           5
        //    51: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    54: aload_1        
        //    55: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    61: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    64: aload_0        
        //    65: sipush          162
        //    68: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    71: aload_1        
        //    72: aload_2        
        //    73: aload           i
        //    75: aload_0        
        //    76: sipush          163
        //    79: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    82: aload_1        
        //    83: aload_2        
        //    84: aload_0        
        //    85: aload_1        
        //    86: ldc_w           "Win32"
        //    89: bipush          34
        //    91: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    94: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    97: aload_0        
        //    98: swap           
        //    99: aload_1        
        //   100: ldc_w           "MIB"
        //   103: bipush          35
        //   105: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   108: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   111: aload_0        
        //   112: swap           
        //   113: aload_1        
        //   114: ldc_w           "IpNetRow"
        //   117: bipush          36
        //   119: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   122: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   125: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   128: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   131: astore          addr
        //   133: aload_0        
        //   134: sipush          164
        //   137: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   140: aload_1        
        //   141: aload_2        
        //   142: aload_0        
        //   143: aload_1        
        //   144: ldc_w           "Win32"
        //   147: bipush          37
        //   149: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   152: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   155: aload_0        
        //   156: swap           
        //   157: aload_1        
        //   158: ldc_w           "MIB"
        //   161: bipush          38
        //   163: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   166: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   169: aload_0        
        //   170: swap           
        //   171: aload_1        
        //   172: ldc_w           "IpNetRow"
        //   175: bipush          39
        //   177: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   180: aload_0        
        //   181: sipush          165
        //   184: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   187: aload_1        
        //   188: aload_2        
        //   189: aload_0        
        //   190: aload_1        
        //   191: ldc_w           "FFI"
        //   194: bipush          40
        //   196: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   199: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   202: aload_0        
        //   203: swap           
        //   204: aload_1        
        //   205: ldc_w           "Pointer"
        //   208: bipush          41
        //   210: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "valid_arp_neighbours_rows", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__33$RUBY$valid_arp_neighbours_rows(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(166).callIter(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(167).call(threadContext, self, self), RuntimeHelpers.createBlock(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getBlockBody(threadContext, 16, "block_16$RUBY$valid_arp_neighbours_rows,1,row,false,2,./lib//lister/utils/scan.rb,212,true")));
    }
    
    public static IRubyObject block_16$RUBY$valid_arp_neighbours_rows(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: sipush          168
        //    29: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    32: aload_1        
        //    33: aload_2        
        //    34: aload           row
        //    36: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     15      9     row   Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "arp_neighbours", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__34$RUBY$arp_neighbours(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(169).callIter(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(170).call(threadContext, self, self), RuntimeHelpers.createBlock(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getBlockBody(threadContext, 17, "block_17$RUBY$arp_neighbours,1,row;mac;host_int;net_int;ip,false,2,./lib//lister/utils/scan.rb,218,true")));
    }
    
    public static IRubyObject block_17$RUBY$arp_neighbours(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    46: astore          9
        //    48: pop            
        //    49: aload_0        
        //    50: sipush          171
        //    53: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    56: aload_1        
        //    57: aload_2        
        //    58: aload           row
        //    60: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: astore          mac
        //    65: aload_0        
        //    66: sipush          172
        //    69: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    72: aload_1        
        //    73: aload_2        
        //    74: aload           row
        //    76: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: astore          host_int
        //    81: aload_0        
        //    82: sipush          173
        //    85: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    88: aload_1        
        //    89: aload_2        
        //    90: aload_0        
        //    91: sipush          174
        //    94: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    97: aload_1        
        //    98: aload_2        
        //    99: aload_0        
        //   100: sipush          175
        //   103: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   106: aload_1        
        //   107: aload_2        
        //   108: aload_1        
        //   109: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   112: aload           host_int
        //   114: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   117: aload_0        
        //   118: aload_1        
        //   119: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   122: bipush          30
        //   124: bipush          32
        //   126: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   129: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   132: aload_0        
        //   133: aload_1        
        //   134: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   137: bipush          31
        //   139: bipush          32
        //   141: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   144: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   147: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   150: astore          net_int
        //   152: aload_0        
        //   153: sipush          176
        //   156: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   159: aload_1        
        //   160: aload_2        
        //   161: aload_0        
        //   162: aload_1        
        //   163: ldc             "IP"
        //   165: bipush          42
        //   167: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   170: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   173: aload_0        
        //   174: swap           
        //   175: aload_1        
        //   176: ldc_w           "V4"
        //   179: bipush          43
        //   181: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   184: aload           net_int
        //   186: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   189: astore          ip
        //   191: aload_0        
        //   192: sipush          177
        //   195: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   198: aload_1        
        //   199: aload_2        
        //   200: aload_0        
        //   201: aload_1        
        //   202: ldc             "ARPEntry"
        //   204: bipush          44
        //   206: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   209: aload           mac
        //   211: aload           ip
        //   213: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   216: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name      Signature
        //  -----  ------  ----  --------  ---------------------------------------
        //  49     168     9     row       Lorg/jruby/runtime/builtin/IRubyObject;
        //  49     168     10    mac       Lorg/jruby/runtime/builtin/IRubyObject;
        //  49     168     11    host_int  Lorg/jruby/runtime/builtin/IRubyObject;
        //  49     168     12    net_int   Lorg/jruby/runtime/builtin/IRubyObject;
        //  49     168     13    ip        Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "arp_neighbours_cmd", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__35$RUBY$arp_neighbours_cmd(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString(threadContext.runtime, 32, 32);
    }
    
    @JRubyMethod(name = "count_neighbours_zeroconf", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__36$RUBY$count_neighbours_zeroconf(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString(threadContext.runtime, 26, 32);
    }
    
    public static IRubyObject module__37$RUBY$AppleSoft(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc_w           "AppleSoft"
        //    11: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    14: dup            
        //    15: astore_2       
        //    16: aload_1        
        //    17: swap           
        //    18: aload_0        
        //    19: aload_1        
        //    20: ldc             ",0,0,-1"
        //    22: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getScope8:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    25: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    28: aload_1        
        //    29: aload_2        
        //    30: aload_0        
        //    31: ldc_w           "count_neighbours_zeroconf"
        //    34: ldc_w           "method__38$RUBY$count_neighbours_zeroconf"
        //    37: ldc_w           "counter,0,0,-1"
        //    40: iconst_0       
        //    41: ldc             "./lib//lister/utils/scan.rb"
        //    43: ldc_w           236
        //    46: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    49: ldc_w           "NONE"
        //    52: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: aload_1        
        //    56: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    59: goto            67
        //    62: aload_1        
        //    63: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    66: athrow         
        //    67: aload_1        
        //    68: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    71: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  28     59     62     67     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "count_neighbours_zeroconf", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__38$RUBY$count_neighbours_zeroconf(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(178).call(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstantFrom(RuntimeHelpers.checkIsModule(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant(threadContext, "Darwin", 45)), threadContext, "ZeroconfCounter", 46)));
        file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(179).call(threadContext, self, locals.getValueZeroDepthZeroOrNil(threadContext.nil), file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getFixnum0(threadContext.runtime, 10));
        return file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(180).call(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(181).call(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(182).callIter(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(183).call(threadContext, self, locals.getValueZeroDepthZeroOrNil(threadContext.nil)), RuntimeHelpers.createBlock(threadContext, self, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getBlockBody(threadContext, 18, "block_18$RUBY$count_neighbours_zeroconf,1,s,false,2,./lib//lister/utils/scan.rb,239,true")))));
    }
    
    public static IRubyObject block_18$RUBY$count_neighbours_zeroconf(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: sipush          184
        //    29: invokevirtual   ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    32: aload_1        
        //    33: aload_2        
        //    34: aload           s
        //    36: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     15      9     s     Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__37$RUBY$AppleSoft(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__37$RUBY$AppleSoft(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__30$RUBY$Windows(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__30$RUBY$Windows(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, threadContext, rubyObject, block);
    }
    
    public static IRubyObject chained_39_rescue_2$RUBY$SYNTHETICScan(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        threadContext.getCurrentScope();
        final IRubyObject errorInfo = threadContext.getErrorInfo();
        IRubyObject rubyObject2 = null;
        Label_0139: {
            try {
                try {
                    file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(190).call(threadContext, rubyObject, rubyObject, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString(threadContext.runtime, 35, 32));
                    rubyObject2 = file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(191).call(threadContext, rubyObject, rubyObject, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstantFrom(RuntimeHelpers.checkIsModule(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant(threadContext, "Windows", 49)), threadContext, "AppleSoft", 50));
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable t) {
                    if (RuntimeHelpers.isJavaExceptionHandled(t, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant(threadContext, "Exception", 51), threadContext).isTrue()) {
                        RuntimeHelpers.storeExceptionInErrorInfo(t, threadContext);
                        rubyObject2 = chained_40_rescue_line_254(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, threadContext, rubyObject, block);
                        RuntimeHelpers.clearErrorInfo(threadContext);
                        break Label_0139;
                    }
                    throw t;
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
    
    public static IRubyObject chained_40_rescue_line_254(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        threadContext.getCurrentScope();
        return file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(192).call(threadContext, rubyObject, rubyObject, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString(threadContext.runtime, 36, 32));
    }
    
    public static IRubyObject chained_41_rescue_3$RUBY$SYNTHETICScan(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        context.getCurrentScope();
        final IRubyObject errorInfo = context.getErrorInfo();
        IRubyObject rubyObject2 = null;
        Label_0149: {
            try {
                try {
                    file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(195).call(context, rubyObject, rubyObject, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString(context.runtime, 35, 32));
                    rubyObject2 = file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(196).call(context, rubyObject, rubyObject, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstantFrom(RuntimeHelpers.checkIsModule(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant(context, "MacOs", 54)), context, "AppleSoft", 55));
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable t) {
                    if (RuntimeHelpers.isJavaExceptionHandled(t, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant(context, "NameError", 56), file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getConstant(context, "LoadError", 57), context).isTrue()) {
                        RuntimeHelpers.storeExceptionInErrorInfo(t, context);
                        rubyObject2 = chained_42_rescue_line_262(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, context, rubyObject, block);
                        RuntimeHelpers.clearErrorInfo(context);
                        break Label_0149;
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
    
    public static IRubyObject chained_42_rescue_line_262(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        threadContext.getCurrentScope();
        return file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getCallSite(197).call(threadContext, rubyObject, rubyObject, file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.getString(threadContext.runtime, 36, 32));
    }
    
    public static IRubyObject module__3$RUBY$Scan(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__3$RUBY$Scan(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__2$RUBY$Utils(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__2$RUBY$Utils(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Lister(final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Lister(file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289, threadContext, rubyObject, block);
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
        final FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289 = new FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289();
        final String string = FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.class.getClassLoader().getResource("ruby/jit/FILE_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.class").toString();
        file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_EB0DDC820E0C5AB33F0FBD00A775B3C1DB049289.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
