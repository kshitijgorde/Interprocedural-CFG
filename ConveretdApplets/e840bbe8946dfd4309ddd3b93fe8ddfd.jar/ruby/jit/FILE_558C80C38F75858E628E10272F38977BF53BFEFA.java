// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.DynamicScope;
import org.jruby.RubyFixnum;
import org.jruby.RubyString;
import org.jruby.runtime.Arity;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_558C80C38F75858E628E10272F38977BF53BFEFA extends AbstractScript
{
    public FILE_558C80C38F75858E628E10272F38977BF53BFEFA() {
        this.filename = "./lib//lister/utils/traffic.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("autoload\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffextend\uffffF\uffffdefault\uffffN\ufffftraffic\uffffN\uffff[]\uffffN\uffffspawn\uffffF\uffffdefault_iface\uffffN\uffffnew\uffffN\uffffeach_line\uffffN\uffffspawn\uffffF\uffffmerge!\uffffN\uffffparse_ifconfig_line\uffffF\uffff[]\uffffN\uffffdefault_iface\uffffN\uffff[]\uffffN\uffffspawn\uffffF\uffff===\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffto_i\uffffN\ufffflast_match\uffffN\uffff===\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffto_i\uffffN\ufffflast_match\uffffN\uffff===\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffto_i\uffffN\ufffflast_match\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffto_i\uffffN\ufffflast_match\uffffN\uffff[]\uffffN\uffffdefault_iface\uffffN\uffff[]\uffffN\uffffspawn\uffffF\uffffdefault_iface\uffffN\uffffnew\uffffN\uffffeach_line\uffffN\uffffspawn\uffffF\uffffmerge!\uffffN\uffffparse_netstat_line\uffffF\uffffsplit\uffffN\uffff==\uffffN\ufffffirst\uffffN\uffffto_i\uffffN\uffff[]\uffffN\uffffto_i\uffffN\uffff[]\uffffN\uffffto_i\uffffN\uffff[]\uffffN\uffffto_i\uffffN\uffff[]\uffffN\uffffon_windows?\uffffN\uffffextend\uffffF\uffffrequire\uffffF\uffffon_mac?\uffffN\uffffextend\uffffF\uffffextend\uffffF\uffff\u0006\u0007\u0003\u0000\u0014\u0003\u0000\u0000\u0000\u0002\u0000\u0000\u0012\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(1, "lister/utils/route", this.getEncoding0());
        this.setByteList(5, "ping -n 20 -w 200 ", this.getEncoding0());
        this.setByteList(10, "[^R]*RX packets:(\\d+).*", this.getEncoding0());
        this.setByteList(15, "netstat -i -b -I ", this.getEncoding0());
        this.setByteList(12, "[^R]*RX bytes:(\\d+)[^T]*TX bytes:(\\d+).*", this.getEncoding0());
        this.setByteList(2, "lister/utils/platform", this.getEncoding0());
        this.setByteList(9, " -c 20 -i 0.2 ", this.getEncoding0());
        this.setByteList(4, "127.0.0.1", this.getEncoding0());
        this.setByteList(16, "Name", this.getEncoding0());
        this.setByteList(11, "[^T]*TX packets:(\\d+).*", this.getEncoding0());
        this.setByteList(17, "win32/interface", this.getEncoding0());
        this.setByteList(14, " -c 20 ", this.getEncoding0());
        this.setByteList(8, "ping -I ", this.getEncoding0());
        this.setByteList(3, "lister/util", this.getEncoding0());
        this.setByteList(13, "ping -b ", this.getEncoding0());
        this.setByteList(6, "ifconfig ", this.getEncoding0());
        this.setByteList(7, "0.0.0.0", this.getEncoding0());
        this.setByteList(0, "win32", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_558C80C38F75858E628E10272F38977BF53BFEFA file_558C80C38F75858E628E10272F38977BF53BFEFA, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite0().call(threadContext, rubyObject, rubyObject, file_558C80C38F75858E628E10272F38977BF53BFEFA.getSymbol0(threadContext.runtime, "Win32"), file_558C80C38F75858E628E10272F38977BF53BFEFA.getString0(threadContext.runtime, 32));
        file_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite1().call(threadContext, rubyObject, rubyObject, file_558C80C38F75858E628E10272F38977BF53BFEFA.getString1(threadContext.runtime, 32));
        file_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite2().call(threadContext, rubyObject, rubyObject, file_558C80C38F75858E628E10272F38977BF53BFEFA.getString2(threadContext.runtime, 32));
        file_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite3().call(threadContext, rubyObject, rubyObject, file_558C80C38F75858E628E10272F38977BF53BFEFA.getString3(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_558C80C38F75858E628E10272F38977BF53BFEFA, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_558C80C38F75858E628E10272F38977BF53BFEFA p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.module__1$RUBY$Utils:(Lruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Utils(final FILE_558C80C38F75858E628E10272F38977BF53BFEFA p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.module__2$RUBY$Traffic:(Lruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__2$RUBY$Traffic(final FILE_558C80C38F75858E628E10272F38977BF53BFEFA p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Traffic"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite4:()Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_2        
        //    34: aload_0        
        //    35: aload_1        
        //    36: ldc             "Util"
        //    38: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    44: pop            
        //    45: aload_0        
        //    46: aload_1        
        //    47: aload_2        
        //    48: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    54: invokestatic    ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.module__3$RUBY$Windows:(Lruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: pop            
        //    58: aload_0        
        //    59: aload_1        
        //    60: aload_2        
        //    61: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    64: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    67: invokestatic    ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.module__6$RUBY$Linux:(Lruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: pop            
        //    71: aload_0        
        //    72: aload_1        
        //    73: aload_2        
        //    74: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    80: invokestatic    ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.module__10$RUBY$MacOs:(Lruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: pop            
        //    84: aload_0        
        //    85: bipush          59
        //    87: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    90: aload_1        
        //    91: aload_2        
        //    92: aload_0        
        //    93: aload_1        
        //    94: ldc_w           "Platform"
        //    97: bipush          15
        //    99: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   102: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   110: ifeq            162
        //   113: aload_0        
        //   114: bipush          60
        //   116: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   119: aload_1        
        //   120: aload_2        
        //   121: aload_2        
        //   122: aload_0        
        //   123: aload_1        
        //   124: ldc             "Windows"
        //   126: bipush          16
        //   128: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   131: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   134: pop            
        //   135: aload_0        
        //   136: bipush          61
        //   138: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   141: aload_1        
        //   142: aload_2        
        //   143: aload_2        
        //   144: aload_0        
        //   145: aload_1        
        //   146: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   149: bipush          17
        //   151: bipush          32
        //   153: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   156: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   159: goto            238
        //   162: aload_0        
        //   163: bipush          62
        //   165: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   168: aload_1        
        //   169: aload_2        
        //   170: aload_0        
        //   171: aload_1        
        //   172: ldc_w           "Platform"
        //   175: bipush          17
        //   177: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   180: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   183: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   188: ifeq            216
        //   191: aload_0        
        //   192: bipush          63
        //   194: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   197: aload_1        
        //   198: aload_2        
        //   199: aload_2        
        //   200: aload_0        
        //   201: aload_1        
        //   202: ldc_w           "MacOs"
        //   205: bipush          18
        //   207: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   210: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   213: goto            238
        //   216: aload_0        
        //   217: bipush          64
        //   219: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   222: aload_1        
        //   223: aload_2        
        //   224: aload_2        
        //   225: aload_0        
        //   226: aload_1        
        //   227: ldc_w           "Linux"
        //   230: bipush          19
        //   232: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   235: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   238: aload_1        
        //   239: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   242: goto            250
        //   245: aload_1        
        //   246: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   249: athrow         
        //   250: aload_1        
        //   251: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   254: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     242    245    250    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject module__3$RUBY$Windows(final FILE_558C80C38F75858E628E10272F38977BF53BFEFA p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Windows"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getScope3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_1        
        //    28: aload_2        
        //    29: aload_0        
        //    30: ldc             "now"
        //    32: ldc             "method__4$RUBY$now"
        //    34: ldc             "iface,0,0,-1"
        //    36: iconst_0       
        //    37: ldc             "./lib//lister/utils/traffic.rb"
        //    39: ldc             12
        //    41: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    44: ldc             "NONE"
        //    46: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: pop            
        //    50: aload_1        
        //    51: aload_2        
        //    52: aload_0        
        //    53: ldc_w           "inject_some_traffic"
        //    56: ldc_w           "method__5$RUBY$inject_some_traffic"
        //    59: ldc_w           "params;ip,0,1,-1"
        //    62: iconst_m1      
        //    63: ldc             "./lib//lister/utils/traffic.rb"
        //    65: ldc_w           17
        //    68: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    71: ldc_w           "oparams"
        //    74: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: aload_1        
        //    78: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    81: goto            89
        //    84: aload_1        
        //    85: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    88: athrow         
        //    89: aload_1        
        //    90: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    93: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     81     84     89     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "now", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$now(final FILE_558C80C38F75858E628E10272F38977BF53BFEFA file_558C80C38F75858E628E10272F38977BF53BFEFA, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        IRubyObject iface = threadContext.nil;
        iface = file_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite5().call(threadContext, rubyObject, file_558C80C38F75858E628E10272F38977BF53BFEFA.getConstantFrom2(RuntimeHelpers.checkIsModule(file_558C80C38F75858E628E10272F38977BF53BFEFA.getConstant1(threadContext, "Win32")), threadContext, "Interface"));
        return iface.isTrue() ? file_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite6().call(threadContext, rubyObject, iface) : threadContext.nil;
    }
    
    @JRubyMethod(name = "inject_some_traffic", frame = true, required = 0, optional = 1, rest = -1)
    public static IRubyObject method__5$RUBY$inject_some_traffic(final FILE_558C80C38F75858E628E10272F38977BF53BFEFA p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    10: aload_3        
        //    11: iconst_0       
        //    12: iconst_1       
        //    13: invokestatic    org/jruby/runtime/Arity.raiseArgumentError:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;II)V
        //    16: aload_3        
        //    17: iconst_0       
        //    18: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.elementOrNull:([Lorg/jruby/runtime/builtin/IRubyObject;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    21: dup            
        //    22: ifnull          35
        //    25: aload           5
        //    27: swap           
        //    28: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    31: pop            
        //    32: goto            49
        //    35: aload           5
        //    37: aload_1        
        //    38: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    41: invokestatic    org/jruby/RubyHash.newHash:(Lorg/jruby/Ruby;)Lorg/jruby/RubyHash;
        //    44: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: pop            
        //    48: pop            
        //    49: aload           locals
        //    51: aload_0        
        //    52: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite7:()Lorg/jruby/runtime/CallSite;
        //    55: aload_1        
        //    56: aload_2        
        //    57: aload           locals
        //    59: aload_1        
        //    60: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: aload_0        
        //    67: aload_1        
        //    68: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    71: ldc             "ip"
        //    73: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    76: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: dup            
        //    80: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    85: ifne            99
        //    88: pop            
        //    89: aload_0        
        //    90: aload_1        
        //    91: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    94: bipush          32
        //    96: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getString4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    99: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   102: pop            
        //   103: aload_0        
        //   104: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite8:()Lorg/jruby/runtime/CallSite;
        //   107: aload_1        
        //   108: aload_2        
        //   109: aload_2        
        //   110: aload_1        
        //   111: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   114: ldc             20
        //   116: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   119: aload_0        
        //   120: aload_1        
        //   121: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   124: bipush          32
        //   126: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getString5:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   129: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   132: aload           locals
        //   134: aload_1        
        //   135: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   138: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   141: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   146: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   149: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   152: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  49     104     5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject module__3$RUBY$Windows(final FILE_558C80C38F75858E628E10272F38977BF53BFEFA file_558C80C38F75858E628E10272F38977BF53BFEFA, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__3$RUBY$Windows(file_558C80C38F75858E628E10272F38977BF53BFEFA, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__6$RUBY$Linux(final FILE_558C80C38F75858E628E10272F38977BF53BFEFA p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    22: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getScope4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    25: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    28: aload_1        
        //    29: aload_2        
        //    30: aload_0        
        //    31: ldc             "now"
        //    33: ldc_w           "method__7$RUBY$now"
        //    36: ldc_w           "iface;cmd;h,0,0,-1"
        //    39: iconst_0       
        //    40: ldc             "./lib//lister/utils/traffic.rb"
        //    42: ldc_w           25
        //    45: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    48: ldc             "NONE"
        //    50: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    53: pop            
        //    54: aload_1        
        //    55: aload_2        
        //    56: aload_0        
        //    57: ldc_w           "inject_some_traffic"
        //    60: ldc_w           "method__8$RUBY$inject_some_traffic"
        //    63: ldc_w           "params;iface;ip,0,1,-1"
        //    66: iconst_m1      
        //    67: ldc             "./lib//lister/utils/traffic.rb"
        //    69: ldc_w           35
        //    72: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    75: ldc_w           "oparams"
        //    78: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    81: pop            
        //    82: aload_1        
        //    83: aload_2        
        //    84: aload_0        
        //    85: ldc_w           "parse_ifconfig_line"
        //    88: ldc_w           "method__9$RUBY$parse_ifconfig_line"
        //    91: ldc_w           "l;h,1,0,-1"
        //    94: iconst_1       
        //    95: ldc             "./lib//lister/utils/traffic.rb"
        //    97: ldc_w           41
        //   100: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   103: ldc_w           "ql"
        //   106: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   109: aload_1        
        //   110: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   113: goto            121
        //   116: aload_1        
        //   117: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   120: athrow         
        //   121: aload_1        
        //   122: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   125: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  28     113    116    121    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "now", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$now(final FILE_558C80C38F75858E628E10272F38977BF53BFEFA file_558C80C38F75858E628E10272F38977BF53BFEFA, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(file_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite9().call(threadContext, self, file_558C80C38F75858E628E10272F38977BF53BFEFA.getConstant3(threadContext, "Route")));
        locals.setValueOneDepthZero(RubyString.newStringLight(threadContext.runtime, 20).append(file_558C80C38F75858E628E10272F38977BF53BFEFA.getString6(threadContext.runtime, 32)).append(locals.getValueZeroDepthZeroOrNil(threadContext.nil).asString()));
        locals.setValueTwoDepthZero(file_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite(10).call(threadContext, self, file_558C80C38F75858E628E10272F38977BF53BFEFA.getConstant4(threadContext, "Hash"), RubyFixnum.zero(threadContext.runtime)));
        file_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite(11).callIter(threadContext, self, file_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite(12).call(threadContext, self, self, locals.getValueOneDepthZeroOrNil(threadContext.nil)), RuntimeHelpers.createBlock(threadContext, self, file_558C80C38F75858E628E10272F38977BF53BFEFA.getBlockBody0(threadContext, "block_0$RUBY$now,1,l,false,2,./lib//lister/utils/traffic.rb,29,true")));
        return locals.getValueTwoDepthZeroOrNil(threadContext.nil);
    }
    
    public static IRubyObject block_0$RUBY$now(final FILE_558C80C38F75858E628E10272F38977BF53BFEFA p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          13
        //    28: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           5
        //    35: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    38: aload_1        
        //    39: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    42: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: aload_0        
        //    46: bipush          14
        //    48: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    51: aload_1        
        //    52: aload_2        
        //    53: aload_2        
        //    54: aload           l
        //    56: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    59: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    62: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     38      9     l     Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "inject_some_traffic", frame = true, required = 0, optional = 1, rest = -1)
    public static IRubyObject method__8$RUBY$inject_some_traffic(final FILE_558C80C38F75858E628E10272F38977BF53BFEFA p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    10: aload_3        
        //    11: iconst_0       
        //    12: iconst_1       
        //    13: invokestatic    org/jruby/runtime/Arity.raiseArgumentError:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;II)V
        //    16: aload_3        
        //    17: iconst_0       
        //    18: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.elementOrNull:([Lorg/jruby/runtime/builtin/IRubyObject;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    21: dup            
        //    22: ifnull          35
        //    25: aload           5
        //    27: swap           
        //    28: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    31: pop            
        //    32: goto            49
        //    35: aload           5
        //    37: aload_1        
        //    38: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    41: invokestatic    org/jruby/RubyHash.newHash:(Lorg/jruby/Ruby;)Lorg/jruby/RubyHash;
        //    44: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: pop            
        //    48: pop            
        //    49: aload           locals
        //    51: aload_0        
        //    52: bipush          15
        //    54: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    57: aload_1        
        //    58: aload_2        
        //    59: aload           locals
        //    61: aload_1        
        //    62: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    68: aload_0        
        //    69: aload_1        
        //    70: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    73: ldc_w           "iface"
        //    76: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    79: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: dup            
        //    83: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    88: ifne            122
        //    91: pop            
        //    92: aload_0        
        //    93: bipush          16
        //    95: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    98: aload_1        
        //    99: aload_2        
        //   100: aload_0        
        //   101: aload_1        
        //   102: ldc             "Utils"
        //   104: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getConstant5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   107: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   110: aload_0        
        //   111: swap           
        //   112: aload_1        
        //   113: ldc_w           "Route"
        //   116: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getConstantFrom6:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   119: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   122: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   125: pop            
        //   126: aload           locals
        //   128: aload_0        
        //   129: bipush          17
        //   131: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   134: aload_1        
        //   135: aload_2        
        //   136: aload           locals
        //   138: aload_1        
        //   139: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   142: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   145: aload_0        
        //   146: aload_1        
        //   147: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   150: ldc             "ip"
        //   152: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   155: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   158: dup            
        //   159: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   164: ifne            178
        //   167: pop            
        //   168: aload_0        
        //   169: aload_1        
        //   170: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   173: bipush          32
        //   175: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   178: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   181: pop            
        //   182: aload_0        
        //   183: bipush          18
        //   185: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   188: aload_1        
        //   189: aload_2        
        //   190: aload_2        
        //   191: aload_1        
        //   192: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   195: ldc             20
        //   197: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   200: aload_0        
        //   201: aload_1        
        //   202: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   205: bipush          32
        //   207: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getString8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   210: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   213: aload           locals
        //   215: aload_1        
        //   216: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   219: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   222: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   227: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   230: aload_0        
        //   231: aload_1        
        //   232: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   235: bipush          32
        //   237: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getString9:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   240: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   243: aload           locals
        //   245: aload_1        
        //   246: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   249: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   252: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   257: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   260: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   263: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  49     215     5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    @JRubyMethod(name = "parse_ifconfig_line", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$parse_ifconfig_line(final FILE_558C80C38F75858E628E10272F38977BF53BFEFA p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    16: aload_1        
        //    17: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    20: invokestatic    org/jruby/RubyHash.newHash:(Lorg/jruby/Ruby;)Lorg/jruby/RubyHash;
        //    23: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    26: pop            
        //    27: aload           locals
        //    29: aload_1        
        //    30: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    36: aload_1        
        //    37: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //    40: astore          9
        //    42: aload_0        
        //    43: bipush          19
        //    45: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    48: aload_1        
        //    49: aload_2        
        //    50: aload           9
        //    52: aload_0        
        //    53: aload_1        
        //    54: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    57: aload_0        
        //    58: bipush          10
        //    60: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getByteList:(I)Lorg/jruby/util/ByteList;
        //    63: ldc_w           512
        //    66: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getRegexp0:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //    69: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //    72: ifeq            158
        //    75: aload           locals
        //    77: aload_1        
        //    78: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    81: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    84: dup            
        //    85: aload_2        
        //    86: aload_0        
        //    87: bipush          20
        //    89: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    92: aload_0        
        //    93: bipush          21
        //    95: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    98: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   101: aload_0        
        //   102: aload_1        
        //   103: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   106: ldc_w           "packets_received"
        //   109: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   112: aload_0        
        //   113: bipush          22
        //   115: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   118: aload_1        
        //   119: aload_2        
        //   120: aload_0        
        //   121: bipush          23
        //   123: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   126: aload_1        
        //   127: aload_2        
        //   128: aload_0        
        //   129: aload_1        
        //   130: ldc_w           "Regexp"
        //   133: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getConstant7:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   136: aload_1        
        //   137: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   140: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   143: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   146: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   149: aload_1        
        //   150: aload_2        
        //   151: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   154: pop            
        //   155: goto            472
        //   158: aload_0        
        //   159: bipush          24
        //   161: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   164: aload_1        
        //   165: aload_2        
        //   166: aload           9
        //   168: aload_0        
        //   169: aload_1        
        //   170: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   173: aload_0        
        //   174: bipush          11
        //   176: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getByteList:(I)Lorg/jruby/util/ByteList;
        //   179: ldc_w           512
        //   182: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getRegexp1:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   185: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   188: ifeq            274
        //   191: aload           locals
        //   193: aload_1        
        //   194: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   197: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   200: dup            
        //   201: aload_2        
        //   202: aload_0        
        //   203: bipush          25
        //   205: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   208: aload_0        
        //   209: bipush          26
        //   211: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   214: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   217: aload_0        
        //   218: aload_1        
        //   219: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   222: ldc_w           "packets_sent"
        //   225: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   228: aload_0        
        //   229: bipush          27
        //   231: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   234: aload_1        
        //   235: aload_2        
        //   236: aload_0        
        //   237: bipush          28
        //   239: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   242: aload_1        
        //   243: aload_2        
        //   244: aload_0        
        //   245: aload_1        
        //   246: ldc_w           "Regexp"
        //   249: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getConstant8:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   252: aload_1        
        //   253: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   256: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   259: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   262: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   265: aload_1        
        //   266: aload_2        
        //   267: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   270: pop            
        //   271: goto            472
        //   274: aload_0        
        //   275: bipush          29
        //   277: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   280: aload_1        
        //   281: aload_2        
        //   282: aload           9
        //   284: aload_0        
        //   285: aload_1        
        //   286: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   289: aload_0        
        //   290: bipush          12
        //   292: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getByteList:(I)Lorg/jruby/util/ByteList;
        //   295: ldc_w           512
        //   298: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getRegexp2:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   301: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   304: ifeq            472
        //   307: aload           locals
        //   309: aload_1        
        //   310: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   313: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   316: dup            
        //   317: aload_2        
        //   318: aload_0        
        //   319: bipush          30
        //   321: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   324: aload_0        
        //   325: bipush          31
        //   327: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   330: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   333: aload_0        
        //   334: aload_1        
        //   335: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   338: ldc_w           "bytes_received"
        //   341: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   344: aload_0        
        //   345: bipush          32
        //   347: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   350: aload_1        
        //   351: aload_2        
        //   352: aload_0        
        //   353: bipush          33
        //   355: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   358: aload_1        
        //   359: aload_2        
        //   360: aload_0        
        //   361: aload_1        
        //   362: ldc_w           "Regexp"
        //   365: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getConstant9:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   368: aload_1        
        //   369: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   372: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   375: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   378: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   381: aload_1        
        //   382: aload_2        
        //   383: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   386: pop            
        //   387: aload           locals
        //   389: aload_1        
        //   390: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   393: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   396: dup            
        //   397: aload_2        
        //   398: aload_0        
        //   399: bipush          34
        //   401: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   404: aload_0        
        //   405: bipush          35
        //   407: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   410: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   413: aload_0        
        //   414: aload_1        
        //   415: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   418: ldc_w           "bytes_sent"
        //   421: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getSymbol6:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   424: aload_0        
        //   425: bipush          36
        //   427: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   430: aload_1        
        //   431: aload_2        
        //   432: aload_0        
        //   433: bipush          37
        //   435: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   438: aload_1        
        //   439: aload_2        
        //   440: aload_0        
        //   441: aload_1        
        //   442: ldc_w           "Regexp"
        //   445: bipush          10
        //   447: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   450: aload_1        
        //   451: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   454: invokestatic    org/jruby/RubyFixnum.two:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   457: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   460: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   463: aload_1        
        //   464: aload_2        
        //   465: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   468: pop            
        //   469: goto            472
        //   472: aload           locals
        //   474: aload_1        
        //   475: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   478: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   481: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     468     5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject module__6$RUBY$Linux(final FILE_558C80C38F75858E628E10272F38977BF53BFEFA file_558C80C38F75858E628E10272F38977BF53BFEFA, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__6$RUBY$Linux(file_558C80C38F75858E628E10272F38977BF53BFEFA, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__10$RUBY$MacOs(final FILE_558C80C38F75858E628E10272F38977BF53BFEFA p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    22: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getScope5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    25: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    28: aload_1        
        //    29: aload_2        
        //    30: aload_0        
        //    31: ldc_w           "inject_some_traffic"
        //    34: ldc_w           "method__11$RUBY$inject_some_traffic"
        //    37: ldc_w           "params;iface;ip,0,1,-1"
        //    40: iconst_m1      
        //    41: ldc             "./lib//lister/utils/traffic.rb"
        //    43: ldc_w           57
        //    46: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    49: ldc_w           "oparams"
        //    52: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: pop            
        //    56: aload_1        
        //    57: aload_2        
        //    58: aload_0        
        //    59: ldc             "now"
        //    61: ldc_w           "method__12$RUBY$now"
        //    64: ldc_w           "iface;cmd;h,0,0,-1"
        //    67: iconst_0       
        //    68: ldc             "./lib//lister/utils/traffic.rb"
        //    70: ldc_w           63
        //    73: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    76: ldc             "NONE"
        //    78: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    81: pop            
        //    82: aload_1        
        //    83: aload_2        
        //    84: aload_0        
        //    85: ldc_w           "parse_netstat_line"
        //    88: ldc_w           "method__13$RUBY$parse_netstat_line"
        //    91: ldc_w           "l;ary;ret,1,0,-1"
        //    94: iconst_1       
        //    95: ldc             "./lib//lister/utils/traffic.rb"
        //    97: ldc_w           73
        //   100: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   103: ldc_w           "ql"
        //   106: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   109: aload_1        
        //   110: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   113: goto            121
        //   116: aload_1        
        //   117: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   120: athrow         
        //   121: aload_1        
        //   122: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   125: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  28     113    116    121    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "inject_some_traffic", frame = true, required = 0, optional = 1, rest = -1)
    public static IRubyObject method__11$RUBY$inject_some_traffic(final FILE_558C80C38F75858E628E10272F38977BF53BFEFA p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    10: aload_3        
        //    11: iconst_0       
        //    12: iconst_1       
        //    13: invokestatic    org/jruby/runtime/Arity.raiseArgumentError:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;II)V
        //    16: aload_3        
        //    17: iconst_0       
        //    18: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.elementOrNull:([Lorg/jruby/runtime/builtin/IRubyObject;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    21: dup            
        //    22: ifnull          35
        //    25: aload           5
        //    27: swap           
        //    28: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    31: pop            
        //    32: goto            49
        //    35: aload           5
        //    37: aload_1        
        //    38: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    41: invokestatic    org/jruby/RubyHash.newHash:(Lorg/jruby/Ruby;)Lorg/jruby/RubyHash;
        //    44: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: pop            
        //    48: pop            
        //    49: aload           locals
        //    51: aload_0        
        //    52: bipush          38
        //    54: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    57: aload_1        
        //    58: aload_2        
        //    59: aload           locals
        //    61: aload_1        
        //    62: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    68: aload_0        
        //    69: aload_1        
        //    70: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    73: ldc_w           "iface"
        //    76: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    79: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: dup            
        //    83: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    88: ifne            126
        //    91: pop            
        //    92: aload_0        
        //    93: bipush          39
        //    95: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    98: aload_1        
        //    99: aload_2        
        //   100: aload_0        
        //   101: aload_1        
        //   102: ldc             "Utils"
        //   104: bipush          11
        //   106: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   109: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   112: aload_0        
        //   113: swap           
        //   114: aload_1        
        //   115: ldc_w           "Route"
        //   118: bipush          12
        //   120: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   123: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   126: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   129: pop            
        //   130: aload           locals
        //   132: aload_0        
        //   133: bipush          40
        //   135: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   138: aload_1        
        //   139: aload_2        
        //   140: aload           locals
        //   142: aload_1        
        //   143: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   146: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   149: aload_0        
        //   150: aload_1        
        //   151: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   154: ldc             "ip"
        //   156: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   159: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   162: dup            
        //   163: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   168: ifne            182
        //   171: pop            
        //   172: aload_0        
        //   173: aload_1        
        //   174: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   177: bipush          32
        //   179: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   182: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   185: pop            
        //   186: aload_0        
        //   187: bipush          41
        //   189: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   192: aload_1        
        //   193: aload_2        
        //   194: aload_2        
        //   195: aload_1        
        //   196: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   199: ldc             20
        //   201: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   204: aload_0        
        //   205: aload_1        
        //   206: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   209: bipush          13
        //   211: bipush          32
        //   213: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   216: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   219: aload           locals
        //   221: aload_1        
        //   222: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   225: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   228: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   233: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   236: aload_0        
        //   237: aload_1        
        //   238: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   241: bipush          14
        //   243: bipush          32
        //   245: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   248: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   251: aload           locals
        //   253: aload_1        
        //   254: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   257: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   260: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   265: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   268: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   271: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  49     223     5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    @JRubyMethod(name = "now", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__12$RUBY$now(final FILE_558C80C38F75858E628E10272F38977BF53BFEFA file_558C80C38F75858E628E10272F38977BF53BFEFA, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(file_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite(42).call(threadContext, self, file_558C80C38F75858E628E10272F38977BF53BFEFA.getConstant(threadContext, "Route", 13)));
        locals.setValueOneDepthZero(RubyString.newStringLight(threadContext.runtime, 20).append(file_558C80C38F75858E628E10272F38977BF53BFEFA.getString(threadContext.runtime, 15, 32)).append(locals.getValueZeroDepthZeroOrNil(threadContext.nil).asString()));
        locals.setValueTwoDepthZero(file_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite(43).call(threadContext, self, file_558C80C38F75858E628E10272F38977BF53BFEFA.getConstant(threadContext, "Hash", 14), RubyFixnum.zero(threadContext.runtime)));
        file_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite(44).callIter(threadContext, self, file_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite(45).call(threadContext, self, self, locals.getValueOneDepthZeroOrNil(threadContext.nil)), RuntimeHelpers.createBlock(threadContext, self, file_558C80C38F75858E628E10272F38977BF53BFEFA.getBlockBody1(threadContext, "block_1$RUBY$now,1,l,false,2,./lib//lister/utils/traffic.rb,67,true")));
        return locals.getValueTwoDepthZeroOrNil(threadContext.nil);
    }
    
    public static IRubyObject block_1$RUBY$now(final FILE_558C80C38F75858E628E10272F38977BF53BFEFA p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          46
        //    28: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           5
        //    35: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    38: aload_1        
        //    39: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    42: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: aload_0        
        //    46: bipush          47
        //    48: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    51: aload_1        
        //    52: aload_2        
        //    53: aload_2        
        //    54: aload           l
        //    56: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    59: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    62: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     38      9     l     Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "parse_netstat_line", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__13$RUBY$parse_netstat_line(final FILE_558C80C38F75858E628E10272F38977BF53BFEFA p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    17: bipush          48
        //    19: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    22: aload_1        
        //    23: aload_2        
        //    24: aload           locals
        //    26: aload_1        
        //    27: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    30: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    36: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: pop            
        //    40: aload           locals
        //    42: aload_0        
        //    43: bipush          49
        //    45: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    48: aload_1        
        //    49: aload_2        
        //    50: aload_0        
        //    51: bipush          50
        //    53: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    56: aload_1        
        //    57: aload_2        
        //    58: aload           locals
        //    60: aload_1        
        //    61: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    64: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: aload_0        
        //    71: aload_1        
        //    72: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    75: bipush          16
        //    77: bipush          32
        //    79: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    82: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    85: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    90: ifeq            103
        //    93: aload_1        
        //    94: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    97: invokestatic    org/jruby/RubyHash.newHash:(Lorg/jruby/Ruby;)Lorg/jruby/RubyHash;
        //   100: goto            323
        //   103: aload_1        
        //   104: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   107: aload_0        
        //   108: aload_1        
        //   109: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   112: ldc_w           "packets_received"
        //   115: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   118: aload_0        
        //   119: bipush          51
        //   121: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   124: aload_1        
        //   125: aload_2        
        //   126: aload_0        
        //   127: bipush          52
        //   129: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   132: aload_1        
        //   133: aload_2        
        //   134: aload           locals
        //   136: aload_1        
        //   137: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   140: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   143: aload_1        
        //   144: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   147: invokestatic    org/jruby/RubyFixnum.four:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   150: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   153: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   156: aload_0        
        //   157: aload_1        
        //   158: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   161: ldc_w           "bytes_received"
        //   164: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   167: aload_0        
        //   168: bipush          53
        //   170: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   173: aload_1        
        //   174: aload_2        
        //   175: aload_0        
        //   176: bipush          54
        //   178: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   181: aload_1        
        //   182: aload_2        
        //   183: aload           locals
        //   185: aload_1        
        //   186: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   189: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   192: aload_0        
        //   193: aload_1        
        //   194: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   197: bipush          6
        //   199: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getFixnum0:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   202: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   205: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   208: aload_0        
        //   209: aload_1        
        //   210: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   213: ldc_w           "packets_sent"
        //   216: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   219: aload_0        
        //   220: bipush          55
        //   222: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   225: aload_1        
        //   226: aload_2        
        //   227: aload_0        
        //   228: bipush          56
        //   230: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   233: aload_1        
        //   234: aload_2        
        //   235: aload           locals
        //   237: aload_1        
        //   238: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   241: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   244: aload_0        
        //   245: aload_1        
        //   246: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   249: bipush          7
        //   251: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getFixnum1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   254: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   257: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   260: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructHash:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyHash;
        //   263: dup            
        //   264: aload_1        
        //   265: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   268: aload_0        
        //   269: aload_1        
        //   270: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   273: ldc_w           "bytes_sent"
        //   276: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getSymbol6:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   279: aload_0        
        //   280: bipush          57
        //   282: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   285: aload_1        
        //   286: aload_2        
        //   287: aload_0        
        //   288: bipush          58
        //   290: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   293: aload_1        
        //   294: aload_2        
        //   295: aload           locals
        //   297: aload_1        
        //   298: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   301: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   304: aload_0        
        //   305: aload_1        
        //   306: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   309: bipush          9
        //   311: invokevirtual   ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.getFixnum2:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   314: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   317: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   320: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   323: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   326: pop            
        //   327: aload           locals
        //   329: aload_1        
        //   330: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   333: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   336: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     323     5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject module__10$RUBY$MacOs(final FILE_558C80C38F75858E628E10272F38977BF53BFEFA file_558C80C38F75858E628E10272F38977BF53BFEFA, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__10$RUBY$MacOs(file_558C80C38F75858E628E10272F38977BF53BFEFA, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__2$RUBY$Traffic(final FILE_558C80C38F75858E628E10272F38977BF53BFEFA file_558C80C38F75858E628E10272F38977BF53BFEFA, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__2$RUBY$Traffic(file_558C80C38F75858E628E10272F38977BF53BFEFA, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Utils(final FILE_558C80C38F75858E628E10272F38977BF53BFEFA file_558C80C38F75858E628E10272F38977BF53BFEFA, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Utils(file_558C80C38F75858E628E10272F38977BF53BFEFA, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_558C80C38F75858E628E10272F38977BF53BFEFA file_558C80C38F75858E628E10272F38977BF53BFEFA, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_558C80C38F75858E628E10272F38977BF53BFEFA, threadContext, rubyObject, block);
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
        final FILE_558C80C38F75858E628E10272F38977BF53BFEFA file_558C80C38F75858E628E10272F38977BF53BFEFA = new FILE_558C80C38F75858E628E10272F38977BF53BFEFA();
        final String string = FILE_558C80C38F75858E628E10272F38977BF53BFEFA.class.getClassLoader().getResource("ruby/jit/FILE_558C80C38F75858E628E10272F38977BF53BFEFA.class").toString();
        file_558C80C38F75858E628E10272F38977BF53BFEFA.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_558C80C38F75858E628E10272F38977BF53BFEFA.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
