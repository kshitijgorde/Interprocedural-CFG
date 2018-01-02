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

public class FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6 extends AbstractScript
{
    public FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6() {
        this.filename = "./lib//lister/utils/platform.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffextend\uffffF\uffffget_property\uffffN\uffffSystem\uffffN\ufffflang\uffffN\uffffjava\uffffV\uffffon_linux?\uffffF\uffffon_mac?\uffffF\uffff==\uffffN\uffffos\uffffV\uffffos\uffffV\uffffos\uffffV\uffffos\uffffV\uffffextend\uffffF\uffffon_posix_like?\uffffF\uffffchomp\uffffN\uffffspawn\uffffF\uffffon_windows?\uffffF\uffffextend\uffffF\ufffftranscode\uffffF\uffffgsub\uffffN\uffffspawn\uffffF\uffff\u0003\u0000\u0000\u0000\u0002\u0003\u0000\u0000\u0000\u0000\u0000\u0000\r\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(11, "", this.getEncoding0());
        this.setByteList(3, "os.name", this.getEncoding0());
        this.setByteList(10, "\n", this.getEncoding0());
        this.setByteList(9, "ver", this.getEncoding0());
        this.setByteList(12, "unknown", this.getEncoding0());
        this.setByteList(2, "lister/utils/transcode", this.getEncoding0());
        this.setByteList(4, "Linux", this.getEncoding0());
        this.setByteList(6, "^Mac", this.getEncoding0());
        this.setByteList(1, "lister/util", this.getEncoding0());
        this.setByteList(8, "uname -rsv", this.getEncoding0());
        this.setByteList(5, "^Windows", this.getEncoding0());
        this.setByteList(7, "^Darwin", this.getEncoding0());
        this.setByteList(0, "java", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6 file_C8926F0C0861DC88B40B8BAF354CA688716986D6, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getCallSite0().call(threadContext, rubyObject, rubyObject, file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getString0(threadContext.runtime, 32));
        file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getCallSite1().call(threadContext, rubyObject, rubyObject, file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getString1(threadContext.runtime, 32));
        file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getCallSite2().call(threadContext, rubyObject, rubyObject, file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getString2(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_C8926F0C0861DC88B40B8BAF354CA688716986D6, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6.module__1$RUBY$Utils:(Lruby/jit/FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Utils(final FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6.module__2$RUBY$Platform:(Lruby/jit/FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__2$RUBY$Platform(final FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Platform"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: invokevirtual   ruby/jit/FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6.getCallSite3:()Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_2        
        //    34: aload_0        
        //    35: aload_1        
        //    36: ldc             "Util"
        //    38: invokevirtual   ruby/jit/FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    44: pop            
        //    45: aload_1        
        //    46: aload_2        
        //    47: aload_0        
        //    48: ldc             "os"
        //    50: ldc             "method__3$RUBY$os"
        //    52: ldc             ",0,0,-1"
        //    54: iconst_0       
        //    55: ldc             "./lib//lister/utils/platform.rb"
        //    57: ldc             10
        //    59: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    62: ldc             "NONE"
        //    64: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: pop            
        //    68: aload_1        
        //    69: aload_2        
        //    70: aload_0        
        //    71: ldc             "on_posix_like?"
        //    73: ldc             "method__4$RUBY$on_posix_like_p_"
        //    75: ldc             ",0,0,-1"
        //    77: iconst_0       
        //    78: ldc             "./lib//lister/utils/platform.rb"
        //    80: ldc             14
        //    82: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    85: ldc             "NONE"
        //    87: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    90: pop            
        //    91: aload_1        
        //    92: aload_2        
        //    93: aload_0        
        //    94: ldc             "on_linux?"
        //    96: ldc             "method__5$RUBY$on_linux_p_"
        //    98: ldc             ",0,0,-1"
        //   100: iconst_0       
        //   101: ldc             "./lib//lister/utils/platform.rb"
        //   103: ldc             18
        //   105: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   108: ldc             "NONE"
        //   110: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   113: pop            
        //   114: aload_1        
        //   115: aload_2        
        //   116: aload_0        
        //   117: ldc             "on_windows?"
        //   119: ldc             "method__6$RUBY$on_windows_p_"
        //   121: ldc             ",0,0,-1"
        //   123: iconst_0       
        //   124: ldc             "./lib//lister/utils/platform.rb"
        //   126: ldc             22
        //   128: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   131: ldc             "NONE"
        //   133: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   136: pop            
        //   137: aload_1        
        //   138: aload_2        
        //   139: aload_0        
        //   140: ldc             "on_mac?"
        //   142: ldc             "method__7$RUBY$on_mac_p_"
        //   144: ldc             ",0,0,-1"
        //   146: iconst_0       
        //   147: ldc             "./lib//lister/utils/platform.rb"
        //   149: ldc             26
        //   151: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   154: ldc             "NONE"
        //   156: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   159: pop            
        //   160: aload_0        
        //   161: bipush          15
        //   163: invokevirtual   ruby/jit/FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   166: aload_1        
        //   167: aload_2        
        //   168: aload_2        
        //   169: aload_2        
        //   170: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   173: pop            
        //   174: aload_0        
        //   175: bipush          16
        //   177: invokevirtual   ruby/jit/FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   180: aload_1        
        //   181: aload_2        
        //   182: aload_2        
        //   183: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   186: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   191: ifeq            219
        //   194: aload_1        
        //   195: aload_2        
        //   196: aload_0        
        //   197: ldc             "true_os"
        //   199: ldc             "method__8$RUBY$true_os"
        //   201: ldc             ",0,0,-1"
        //   203: iconst_0       
        //   204: ldc             "./lib//lister/utils/platform.rb"
        //   206: ldc             33
        //   208: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   211: ldc             "NONE"
        //   213: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   216: goto            310
        //   219: aload_0        
        //   220: bipush          19
        //   222: invokevirtual   ruby/jit/FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   225: aload_1        
        //   226: aload_2        
        //   227: aload_2        
        //   228: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   231: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   236: ifeq            286
        //   239: aload_0        
        //   240: bipush          20
        //   242: invokevirtual   ruby/jit/FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   245: aload_1        
        //   246: aload_2        
        //   247: aload_2        
        //   248: aload_0        
        //   249: aload_1        
        //   250: ldc             "Transcode"
        //   252: invokevirtual   ruby/jit/FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6.getConstant1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   255: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   258: pop            
        //   259: aload_1        
        //   260: aload_2        
        //   261: aload_0        
        //   262: ldc             "true_os"
        //   264: ldc_w           "method__9$RUBY$true_os"
        //   267: ldc             ",0,0,-1"
        //   269: iconst_0       
        //   270: ldc             "./lib//lister/utils/platform.rb"
        //   272: ldc_w           39
        //   275: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   278: ldc             "NONE"
        //   280: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   283: goto            310
        //   286: aload_1        
        //   287: aload_2        
        //   288: aload_0        
        //   289: ldc             "true_os"
        //   291: ldc_w           "method__10$RUBY$true_os"
        //   294: ldc             ",0,0,-1"
        //   296: iconst_0       
        //   297: ldc             "./lib//lister/utils/platform.rb"
        //   299: ldc_w           43
        //   302: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   305: ldc             "NONE"
        //   307: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   310: aload_1        
        //   311: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   314: goto            322
        //   317: aload_1        
        //   318: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   321: athrow         
        //   322: aload_1        
        //   323: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   326: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     314    317    322    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "os", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$os(final FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6 file_C8926F0C0861DC88B40B8BAF354CA688716986D6, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getCallSite4().call(threadContext, rubyObject, file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getCallSite5().call(threadContext, rubyObject, file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getCallSite6().call(threadContext, rubyObject, file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getCallSite7().call(threadContext, rubyObject, rubyObject))), file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getString3(threadContext.runtime, 32));
    }
    
    @JRubyMethod(name = "on_posix_like?", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$on_posix_like_p_(final FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6 file_C8926F0C0861DC88B40B8BAF354CA688716986D6, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        IRubyObject rubyObject2;
        if (!(rubyObject2 = file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getCallSite8().call(threadContext, rubyObject, rubyObject)).isTrue()) {
            rubyObject2 = file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getCallSite9().call(threadContext, rubyObject, rubyObject);
        }
        return rubyObject2;
    }
    
    @JRubyMethod(name = "on_linux?", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$on_linux_p_(final FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6 file_C8926F0C0861DC88B40B8BAF354CA688716986D6, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getCallSite(10).call(threadContext, rubyObject, file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getCallSite(11).call(threadContext, rubyObject, rubyObject), file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getString4(threadContext.runtime, 32));
    }
    
    @JRubyMethod(name = "on_windows?", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$on_windows_p_(final FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6 file_C8926F0C0861DC88B40B8BAF354CA688716986D6, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        return RuntimeHelpers.match3(file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getRegexp0(context.runtime, file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getByteList5(), 512), file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getCallSite(12).call(context, rubyObject, rubyObject), context);
    }
    
    @JRubyMethod(name = "on_mac?", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$on_mac_p_(final FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6 file_C8926F0C0861DC88B40B8BAF354CA688716986D6, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        IRubyObject rubyObject2;
        if (!(rubyObject2 = RuntimeHelpers.match3(file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getRegexp1(threadContext.runtime, file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getByteList6(), 512), file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getCallSite(13).call(threadContext, rubyObject, rubyObject), threadContext)).isTrue()) {
            rubyObject2 = RuntimeHelpers.match3(file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getRegexp2(threadContext.runtime, file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getByteList7(), 512), file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getCallSite(14).call(threadContext, rubyObject, rubyObject), threadContext);
        }
        return rubyObject2;
    }
    
    @JRubyMethod(name = "true_os", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$true_os(final FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6 file_C8926F0C0861DC88B40B8BAF354CA688716986D6, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getCallSite(17).call(threadContext, rubyObject, file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getCallSite(18).call(threadContext, rubyObject, rubyObject, file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getString8(threadContext.runtime, 32)));
    }
    
    @JRubyMethod(name = "true_os", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$true_os(final FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6 file_C8926F0C0861DC88B40B8BAF354CA688716986D6, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getCallSite(21).call(threadContext, rubyObject, rubyObject, file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getCallSite(22).call(threadContext, rubyObject, file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getCallSite(23).call(threadContext, rubyObject, rubyObject, file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getString9(threadContext.runtime, 32)), file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getString(threadContext.runtime, 10, 32), file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getString(threadContext.runtime, 11, 32)));
    }
    
    @JRubyMethod(name = "true_os", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__10$RUBY$true_os(final FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6 file_C8926F0C0861DC88B40B8BAF354CA688716986D6, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_C8926F0C0861DC88B40B8BAF354CA688716986D6.getString(threadContext.runtime, 12, 32);
    }
    
    public static IRubyObject module__2$RUBY$Platform(final FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6 file_C8926F0C0861DC88B40B8BAF354CA688716986D6, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__2$RUBY$Platform(file_C8926F0C0861DC88B40B8BAF354CA688716986D6, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Utils(final FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6 file_C8926F0C0861DC88B40B8BAF354CA688716986D6, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Utils(file_C8926F0C0861DC88B40B8BAF354CA688716986D6, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6 file_C8926F0C0861DC88B40B8BAF354CA688716986D6, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_C8926F0C0861DC88B40B8BAF354CA688716986D6, threadContext, rubyObject, block);
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
        final FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6 file_C8926F0C0861DC88B40B8BAF354CA688716986D6 = new FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6();
        final String string = FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6.class.getClassLoader().getResource("ruby/jit/FILE_C8926F0C0861DC88B40B8BAF354CA688716986D6.class").toString();
        file_C8926F0C0861DC88B40B8BAF354CA688716986D6.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_C8926F0C0861DC88B40B8BAF354CA688716986D6.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
