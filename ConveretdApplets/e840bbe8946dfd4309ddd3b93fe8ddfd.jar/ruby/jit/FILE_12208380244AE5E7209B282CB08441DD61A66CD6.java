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

public class FILE_12208380244AE5E7209B282CB08441DD61A66CD6 extends AbstractScript
{
    public FILE_12208380244AE5E7209B282CB08441DD61A66CD6() {
        this.filename = "./lib//lister/utils/dns.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffextend\uffffF\ufffffile?\uffffN\uffffselect\uffffN\uffffread\uffffN\uffff==\uffffN\ufffffirst\uffffN\uffffsplit\uffffN\uffffmap\uffffN\ufffflast\uffffN\uffffsplit\uffffN\uffffchomp\uffffN\uffffnew\uffffN\uffffwrite_ulong\uffffN\uffffGetNetworkParams\uffffN\ufffflogger\uffffV\uffffdebug\uffffN\ufffflogger\uffffV\uffffread_ulong\uffffN\uffff>\uffffN\uffffread_ulong\uffffN\uffffnew\uffffN\uffffread_ulong\uffffN\uffffGetNetworkParams\uffffN\uffffnew\uffffN\uffffto_ptr\uffffN\uffff[]\uffffN\uffffnew\uffffN\uffff<<\uffffN\uffffread_string\uffffN\uffffto_ptr\uffffN\uffff[]\uffffN\uffff[]\uffffN\uffffnull?\uffffN\ufffffree\uffffN\ufffffree\uffffN\uffffon_windows?\uffffN\uffffextend\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffon_posix_like?\uffffN\uffffextend\uffffF\uffff\u0005\u0004\u0000\u0000\u0015\u0000\u0000\u0000\u0000\u0002\u0000\u0000\t\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(5, " (", this.getEncoding0());
        this.setByteList(4, "GetNetworkParams needs ", this.getEncoding0());
        this.setByteList(3, "nameserver", this.getEncoding0());
        this.setByteList(7, "win32/mib/ipstats", this.getEncoding0());
        this.setByteList(0, "lister/util", this.getEncoding0());
        this.setByteList(2, "/etc/resolv.conf", this.getEncoding0());
        this.setByteList(8, "win32/iphelper", this.getEncoding0());
        this.setByteList(6, ")", this.getEncoding0());
        this.setByteList(1, "lister/utils/platform", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_12208380244AE5E7209B282CB08441DD61A66CD6 file_12208380244AE5E7209B282CB08441DD61A66CD6, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite0().call(threadContext, rubyObject, rubyObject, file_12208380244AE5E7209B282CB08441DD61A66CD6.getString0(threadContext.runtime, 32));
        file_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite1().call(threadContext, rubyObject, rubyObject, file_12208380244AE5E7209B282CB08441DD61A66CD6.getString1(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_12208380244AE5E7209B282CB08441DD61A66CD6, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_12208380244AE5E7209B282CB08441DD61A66CD6 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.module__1$RUBY$Utils:(Lruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Utils(final FILE_12208380244AE5E7209B282CB08441DD61A66CD6 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.module__2$RUBY$DNS:(Lruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__2$RUBY$DNS(final FILE_12208380244AE5E7209B282CB08441DD61A66CD6 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "DNS"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite2:()Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_2        
        //    34: aload_0        
        //    35: aload_1        
        //    36: ldc             "Util"
        //    38: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    44: pop            
        //    45: aload_0        
        //    46: aload_1        
        //    47: aload_2        
        //    48: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    54: invokestatic    ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.module__3$RUBY$Posix:(Lruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: pop            
        //    58: aload_0        
        //    59: aload_1        
        //    60: aload_2        
        //    61: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    64: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    67: invokestatic    ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.module__5$RUBY$Windows:(Lruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: pop            
        //    71: aload_0        
        //    72: bipush          37
        //    74: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    77: aload_1        
        //    78: aload_2        
        //    79: aload_0        
        //    80: aload_1        
        //    81: ldc_w           "Platform"
        //    84: bipush          17
        //    86: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    92: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    97: ifeq            171
        //   100: aload_0        
        //   101: bipush          38
        //   103: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   106: aload_1        
        //   107: aload_2        
        //   108: aload_2        
        //   109: aload_0        
        //   110: aload_1        
        //   111: ldc_w           "Windows"
        //   114: bipush          18
        //   116: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   119: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   122: pop            
        //   123: aload_0        
        //   124: bipush          39
        //   126: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   129: aload_1        
        //   130: aload_2        
        //   131: aload_2        
        //   132: aload_0        
        //   133: aload_1        
        //   134: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   137: bipush          32
        //   139: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   142: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   145: pop            
        //   146: aload_0        
        //   147: bipush          40
        //   149: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   152: aload_1        
        //   153: aload_2        
        //   154: aload_2        
        //   155: aload_0        
        //   156: aload_1        
        //   157: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   160: bipush          32
        //   162: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getString8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   165: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   168: goto            228
        //   171: aload_0        
        //   172: bipush          41
        //   174: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   177: aload_1        
        //   178: aload_2        
        //   179: aload_0        
        //   180: aload_1        
        //   181: ldc_w           "Platform"
        //   184: bipush          19
        //   186: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   189: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   192: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   197: ifeq            224
        //   200: aload_0        
        //   201: bipush          42
        //   203: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   206: aload_1        
        //   207: aload_2        
        //   208: aload_2        
        //   209: aload_0        
        //   210: aload_1        
        //   211: ldc             "Posix"
        //   213: bipush          20
        //   215: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   218: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   221: goto            228
        //   224: aload_1        
        //   225: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   228: aload_1        
        //   229: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   232: goto            240
        //   235: aload_1        
        //   236: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   239: athrow         
        //   240: aload_1        
        //   241: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   244: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     232    235    240    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject module__3$RUBY$Posix(final FILE_12208380244AE5E7209B282CB08441DD61A66CD6 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Posix"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getScope3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_1        
        //    28: aload_2        
        //    29: aload_0        
        //    30: ldc             "servers"
        //    32: ldc             "method__4$RUBY$servers"
        //    34: ldc             "ret;lines,0,0,-1"
        //    36: iconst_0       
        //    37: ldc             "./lib//lister/utils/dns.rb"
        //    39: ldc             10
        //    41: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    44: ldc             "NONE"
        //    46: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: aload_1        
        //    50: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    53: goto            61
        //    56: aload_1        
        //    57: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    60: athrow         
        //    61: aload_1        
        //    62: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    65: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     53     56     61     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "servers", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$servers(final FILE_12208380244AE5E7209B282CB08441DD61A66CD6 file_12208380244AE5E7209B282CB08441DD61A66CD6, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(threadContext.runtime.newArray());
        if (file_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite3().call(threadContext, rubyObject, file_12208380244AE5E7209B282CB08441DD61A66CD6.getConstant1(threadContext, "File"), file_12208380244AE5E7209B282CB08441DD61A66CD6.getString2(threadContext.runtime, 32)).isTrue()) {
            locals.setValueOneDepthZero(file_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite4().callIter(threadContext, rubyObject, file_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite5().call(threadContext, rubyObject, file_12208380244AE5E7209B282CB08441DD61A66CD6.getConstant2(threadContext, "File"), file_12208380244AE5E7209B282CB08441DD61A66CD6.getString2(threadContext.runtime, 32)), RuntimeHelpers.createBlock(threadContext, rubyObject, file_12208380244AE5E7209B282CB08441DD61A66CD6.getBlockBody0(threadContext, "block_0$RUBY$servers,1,line,false,2,./lib//lister/utils/dns.rb,13,false"))));
            locals.setValueZeroDepthZero(file_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite9().callIter(threadContext, rubyObject, locals.getValueOneDepthZeroOrNil(threadContext.nil), RuntimeHelpers.createBlock(threadContext, rubyObject, file_12208380244AE5E7209B282CB08441DD61A66CD6.getBlockBody1(threadContext, "block_1$RUBY$servers,1,line,false,2,./lib//lister/utils/dns.rb,16,false"))));
        }
        return locals.getValueZeroDepthZeroOrNil(threadContext.nil);
    }
    
    public static IRubyObject block_0$RUBY$servers(final FILE_12208380244AE5E7209B282CB08441DD61A66CD6 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite6:()Lorg/jruby/runtime/CallSite;
        //    39: aload_1        
        //    40: aload_2        
        //    41: aload_0        
        //    42: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite7:()Lorg/jruby/runtime/CallSite;
        //    45: aload_1        
        //    46: aload_2        
        //    47: aload_0        
        //    48: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite8:()Lorg/jruby/runtime/CallSite;
        //    51: aload_1        
        //    52: aload_2        
        //    53: aload           locals
        //    55: aload_1        
        //    56: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    59: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    62: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    68: aload_0        
        //    69: aload_1        
        //    70: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    73: bipush          32
        //    75: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getString3:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    78: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    81: areturn        
        //    82: pop            
        //    83: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     47      5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     82     82     86     Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    public static IRubyObject block_1$RUBY$servers(final FILE_12208380244AE5E7209B282CB08441DD61A66CD6 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: bipush          10
        //    38: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_0        
        //    44: bipush          11
        //    46: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload_0        
        //    52: bipush          12
        //    54: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    57: aload_1        
        //    58: aload_2        
        //    59: aload           locals
        //    61: aload_1        
        //    62: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    68: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    71: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: areturn        
        //    78: pop            
        //    79: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     43      5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     78     78     82     Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    public static IRubyObject module__3$RUBY$Posix(final FILE_12208380244AE5E7209B282CB08441DD61A66CD6 file_12208380244AE5E7209B282CB08441DD61A66CD6, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__3$RUBY$Posix(file_12208380244AE5E7209B282CB08441DD61A66CD6, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__5$RUBY$Windows(final FILE_12208380244AE5E7209B282CB08441DD61A66CD6 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    22: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getScope4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    25: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    28: aload_1        
        //    29: aload_2        
        //    30: aload_0        
        //    31: ldc             "servers"
        //    33: ldc_w           "method__6$RUBY$servers"
        //    36: ldc_w           "ret;p_long;err_code;p_info;info;p_ipaddr;ipaddr,0,0,-1"
        //    39: iconst_0       
        //    40: ldc             "./lib//lister/utils/dns.rb"
        //    42: ldc_w           23
        //    45: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    48: ldc             "NONE"
        //    50: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    53: aload_1        
        //    54: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: goto            65
        //    60: aload_1        
        //    61: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    64: athrow         
        //    65: aload_1        
        //    66: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    69: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  28     57     60     65     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "servers", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$servers(final FILE_12208380244AE5E7209B282CB08441DD61A66CD6 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    24: aload_1        
        //    25: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    28: invokevirtual   org/jruby/Ruby.newArray:()Lorg/jruby/RubyArray;
        //    31: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    34: pop            
        //    35: aload           locals
        //    37: aload_0        
        //    38: bipush          13
        //    40: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    43: aload_1        
        //    44: aload_2        
        //    45: aload_0        
        //    46: aload_1        
        //    47: ldc_w           "FFI"
        //    50: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getConstant3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    53: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    56: aload_0        
        //    57: swap           
        //    58: aload_1        
        //    59: ldc_w           "MemoryPointer"
        //    62: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getConstantFrom4:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: aload_0        
        //    66: aload_1        
        //    67: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    70: ldc_w           "ulong"
        //    73: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    76: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: pop            
        //    83: aload_0        
        //    84: bipush          14
        //    86: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    89: aload_1        
        //    90: aload_2        
        //    91: aload           locals
        //    93: aload_1        
        //    94: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    97: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   100: aload_1        
        //   101: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   104: invokestatic    org/jruby/RubyFixnum.zero:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   107: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   110: pop            
        //   111: aload           locals
        //   113: aload_0        
        //   114: bipush          15
        //   116: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   119: aload_1        
        //   120: aload_2        
        //   121: aload_0        
        //   122: aload_1        
        //   123: ldc_w           "Win32"
        //   126: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getConstant5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   129: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   132: aload_0        
        //   133: swap           
        //   134: aload_1        
        //   135: ldc_w           "IPHelper"
        //   138: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getConstantFrom6:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   141: aload_1        
        //   142: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   145: aload           locals
        //   147: aload_1        
        //   148: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   151: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   154: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   157: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   160: pop            
        //   161: aload_0        
        //   162: bipush          16
        //   164: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   167: aload_1        
        //   168: aload_2        
        //   169: aload_2        
        //   170: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   173: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   178: ifeq            302
        //   181: aload_0        
        //   182: bipush          17
        //   184: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   187: aload_1        
        //   188: aload_2        
        //   189: aload_0        
        //   190: bipush          18
        //   192: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   195: aload_1        
        //   196: aload_2        
        //   197: aload_2        
        //   198: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   201: aload_1        
        //   202: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   205: ldc_w           20
        //   208: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   211: aload_0        
        //   212: aload_1        
        //   213: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   216: bipush          32
        //   218: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getString4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   221: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   224: aload_0        
        //   225: bipush          19
        //   227: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   230: aload_1        
        //   231: aload_2        
        //   232: aload           locals
        //   234: aload_1        
        //   235: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   238: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   241: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   244: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   249: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   252: aload_0        
        //   253: aload_1        
        //   254: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   257: bipush          32
        //   259: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getString5:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   262: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   265: aload           locals
        //   267: aload_1        
        //   268: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   271: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   274: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   279: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   282: aload_0        
        //   283: aload_1        
        //   284: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   287: bipush          32
        //   289: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getString6:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   292: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   295: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   298: pop            
        //   299: goto            302
        //   302: aload_0        
        //   303: bipush          20
        //   305: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   308: aload_1        
        //   309: aload_2        
        //   310: aload_0        
        //   311: bipush          21
        //   313: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   316: aload_1        
        //   317: aload_2        
        //   318: aload           locals
        //   320: aload_1        
        //   321: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   324: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   327: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   330: ldc2_w          0
        //   333: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;J)Lorg/jruby/runtime/builtin/IRubyObject;
        //   336: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   341: ifeq            783
        //   344: aload           locals
        //   346: aload_0        
        //   347: bipush          22
        //   349: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   352: aload_1        
        //   353: aload_2        
        //   354: aload_0        
        //   355: aload_1        
        //   356: ldc_w           "FFI"
        //   359: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getConstant7:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   362: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   365: aload_0        
        //   366: swap           
        //   367: aload_1        
        //   368: ldc_w           "MemoryPointer"
        //   371: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getConstantFrom8:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   374: aload_0        
        //   375: bipush          23
        //   377: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   380: aload_1        
        //   381: aload_2        
        //   382: aload           locals
        //   384: aload_1        
        //   385: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   388: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   391: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   394: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   397: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   400: pop            
        //   401: aload_0        
        //   402: bipush          24
        //   404: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   407: aload_1        
        //   408: aload_2        
        //   409: aload_0        
        //   410: aload_1        
        //   411: ldc_w           "Win32"
        //   414: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getConstant9:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   417: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   420: aload_0        
        //   421: swap           
        //   422: aload_1        
        //   423: ldc_w           "IPHelper"
        //   426: bipush          10
        //   428: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   431: aload           locals
        //   433: aload_1        
        //   434: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   437: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   440: aload           locals
        //   442: aload_1        
        //   443: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   446: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   449: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   452: pop            
        //   453: aload           5
        //   455: iconst_4       
        //   456: aload_0        
        //   457: bipush          25
        //   459: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   462: aload_1        
        //   463: aload_2        
        //   464: aload_0        
        //   465: aload_1        
        //   466: ldc_w           "Win32"
        //   469: bipush          11
        //   471: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   474: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   477: aload_0        
        //   478: swap           
        //   479: aload_1        
        //   480: ldc_w           "MIB"
        //   483: bipush          12
        //   485: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   488: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   491: aload_0        
        //   492: swap           
        //   493: aload_1        
        //   494: ldc_w           "FixedInfo"
        //   497: bipush          13
        //   499: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   502: aload           locals
        //   504: aload_1        
        //   505: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   508: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   511: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   514: aastore        
        //   515: aload           5
        //   517: iconst_5       
        //   518: aload_0        
        //   519: bipush          26
        //   521: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   524: aload_1        
        //   525: aload_2        
        //   526: aload_0        
        //   527: bipush          27
        //   529: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   532: aload_1        
        //   533: aload_2        
        //   534: aload           5
        //   536: iconst_4       
        //   537: aaload         
        //   538: aload_0        
        //   539: aload_1        
        //   540: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   543: ldc_w           "DnsServerList"
        //   546: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   549: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   552: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   555: aastore        
        //   556: goto            720
        //   559: aload           5
        //   561: bipush          6
        //   563: aload_0        
        //   564: bipush          28
        //   566: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   569: aload_1        
        //   570: aload_2        
        //   571: aload_0        
        //   572: aload_1        
        //   573: ldc_w           "Win32"
        //   576: bipush          14
        //   578: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   581: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   584: aload_0        
        //   585: swap           
        //   586: aload_1        
        //   587: ldc_w           "MIB"
        //   590: bipush          15
        //   592: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   595: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   598: aload_0        
        //   599: swap           
        //   600: aload_1        
        //   601: ldc_w           "IpAddrString"
        //   604: bipush          16
        //   606: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   609: aload           5
        //   611: iconst_5       
        //   612: aaload         
        //   613: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   616: aastore        
        //   617: aload_0        
        //   618: bipush          29
        //   620: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   623: aload_1        
        //   624: aload_2        
        //   625: aload           locals
        //   627: aload_1        
        //   628: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   631: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   634: aload_0        
        //   635: bipush          30
        //   637: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   640: aload_1        
        //   641: aload_2        
        //   642: aload_0        
        //   643: bipush          31
        //   645: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   648: aload_1        
        //   649: aload_2        
        //   650: aload_0        
        //   651: bipush          32
        //   653: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   656: aload_1        
        //   657: aload_2        
        //   658: aload           5
        //   660: bipush          6
        //   662: aaload         
        //   663: aload_0        
        //   664: aload_1        
        //   665: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   668: ldc_w           "IpAddress"
        //   671: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   674: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   677: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   680: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   683: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   686: pop            
        //   687: aload           5
        //   689: iconst_5       
        //   690: aload_0        
        //   691: bipush          33
        //   693: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   696: aload_1        
        //   697: aload_2        
        //   698: aload           5
        //   700: bipush          6
        //   702: aaload         
        //   703: aload_0        
        //   704: aload_1        
        //   705: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   708: ldc_w           "Next"
        //   711: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   714: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   717: dup_x2         
        //   718: aastore        
        //   719: pop            
        //   720: aload_0        
        //   721: bipush          34
        //   723: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   726: aload_1        
        //   727: aload_2        
        //   728: aload           5
        //   730: iconst_5       
        //   731: aaload         
        //   732: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   735: aload_1        
        //   736: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   739: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.negate:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   742: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   747: ifne            559
        //   750: aload_1        
        //   751: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   754: aload_1        
        //   755: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //   758: pop            
        //   759: aload_0        
        //   760: bipush          35
        //   762: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   765: aload_1        
        //   766: aload_2        
        //   767: aload           locals
        //   769: aload_1        
        //   770: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   773: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   776: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   779: pop            
        //   780: goto            783
        //   783: aload_0        
        //   784: bipush          36
        //   786: invokevirtual   ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   789: aload_1        
        //   790: aload_2        
        //   791: aload           locals
        //   793: aload_1        
        //   794: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   797: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   800: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   803: pop            
        //   804: aload           locals
        //   806: aload_1        
        //   807: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   810: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   813: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  22     792     4     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject module__5$RUBY$Windows(final FILE_12208380244AE5E7209B282CB08441DD61A66CD6 file_12208380244AE5E7209B282CB08441DD61A66CD6, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__5$RUBY$Windows(file_12208380244AE5E7209B282CB08441DD61A66CD6, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__2$RUBY$DNS(final FILE_12208380244AE5E7209B282CB08441DD61A66CD6 file_12208380244AE5E7209B282CB08441DD61A66CD6, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__2$RUBY$DNS(file_12208380244AE5E7209B282CB08441DD61A66CD6, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Utils(final FILE_12208380244AE5E7209B282CB08441DD61A66CD6 file_12208380244AE5E7209B282CB08441DD61A66CD6, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Utils(file_12208380244AE5E7209B282CB08441DD61A66CD6, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_12208380244AE5E7209B282CB08441DD61A66CD6 file_12208380244AE5E7209B282CB08441DD61A66CD6, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_12208380244AE5E7209B282CB08441DD61A66CD6, threadContext, rubyObject, block);
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
        final FILE_12208380244AE5E7209B282CB08441DD61A66CD6 file_12208380244AE5E7209B282CB08441DD61A66CD6 = new FILE_12208380244AE5E7209B282CB08441DD61A66CD6();
        final String string = FILE_12208380244AE5E7209B282CB08441DD61A66CD6.class.getClassLoader().getResource("ruby/jit/FILE_12208380244AE5E7209B282CB08441DD61A66CD6.class").toString();
        file_12208380244AE5E7209B282CB08441DD61A66CD6.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_12208380244AE5E7209B282CB08441DD61A66CD6.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
