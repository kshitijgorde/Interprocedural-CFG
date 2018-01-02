// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.runtime.DynamicScope;
import org.jruby.exceptions.JumpException;
import org.jruby.RubyString;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.RubyHash;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 extends AbstractScript
{
    public FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31() {
        this.filename = "./lib//lister/runner/measurements/performance.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffautoload\uffffF\uffffautoload\uffffF\uffffdo_http_downloads\uffffV\uffffdo_traceroute\uffffV\uffffdo_dns_traceroutes\uffffV\uffffread\uffffN\uffffopen\uffffF\uffffhexdigest\uffffN\ufffflog\uffffF\uffffurl_for_file\uffffF\uffffread\uffffN\uffffopen\uffffF\uffffurl_for_file\uffffF\uffffget_http_sha1s\uffffV\uffffget_http_sha1s\uffffV\ufffffind\uffffN\ufffflines\uffffN\uffffhttp_sha1s\uffffV\uffffinclude?\uffffN\uffffdowncase\uffffN\uffffdowncase\uffffN\ufffffirst\uffffN\uffffsplit\uffffN\uffffeach\uffffN\uffffhttp_values\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffsha1_for_size\uffffF\ufffflog\uffffF\uffffurl_for_dl_file\uffffF\uffffdelta_t\uffffF\uffffdownload_http\uffffF\uffffurl_for_dl_file\uffffF\ufffflog\uffffF\uffff[]=\uffffN\uffff[]=\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffdo_sha1\uffffV\uffff>\uffffN\ufffflog\uffffF\ufffflog\uffffF\ufffflog\uffffF\uffff[]=\uffffN\uffff[]=\uffffV\uffffsleep\uffffF\uffffjoin\uffffN\uffffurl_for_file\uffffF\uffffnow\uffffN\uffffnow\uffffN\uffff-\uffffN\uffffattr_accessor\uffffF\uffffutil_result\uffffV\uffffmap\uffffN\uffffutil_result\uffffV\uffffttl\uffffN\uffffip\uffffN\uffffrtts\uffffN\uffffcmd\uffffN\uffffdestination\uffffV\uffffversion\uffffN\ufffflog\uffffF\uffffgetaddress\uffffN\uffffnew\uffffN\uffffdestination_host\uffffV\uffff>\uffffN\uffffraise\uffffF\uffffdo_resolution\uffffF\uffff+\uffffN\uffffdo_resolution\uffffV\uffffdo_resolution\uffffV\uffffutil_result=\uffffN\uffffutil_result=\uffffV\uffffto\uffffN\uffffdestination\uffffV\uffffnew\uffffN\uffffnew\uffffN\uffffeach_with_index\uffffN\uffffservers\uffffN\uffffto\uffffN\uffffdns_routes\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffmap\uffffN\uffffttl\uffffN\uffffip\uffffN\uffffrtts\uffffN\uffff>\uffffN\uffff\u0003\r\u0001\u0000\u001c\u0000\u0000\u0006\t\u0006\u0000\u0000\u0016\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(19, "doing resolution (", this.getEncoding0());
        this.setByteList(16, "dl_file_", this.getEncoding0());
        this.setByteList(12, "Took more than TRESHOLD (", this.getEncoding0());
        this.setByteList(10, "Download completed in ", this.getEncoding0());
        this.setByteList(8, "getting sha1s from ", this.getEncoding0());
        this.setByteList(14, "Error during the download", this.getEncoding0());
        this.setByteList(0, "lister/measurements/performance", this.getEncoding0());
        this.setByteList(2, "lister/utils/dns", this.getEncoding0());
        this.setByteList(4, "resolv", this.getEncoding0());
        this.setByteList(3, "open-uri", this.getEncoding0());
        this.setByteList(6, "sha1sums.txt", this.getEncoding0());
        this.setByteList(20, ")", this.getEncoding0());
        this.setByteList(17, ".rnd", this.getEncoding0());
        this.setByteList(21, "cmon.lip6.fr", this.getEncoding0());
        this.setByteList(9, "Starting the download of ", this.getEncoding0());
        this.setByteList(15, "/", this.getEncoding0());
        this.setByteList(1, "lister/utils/traceroute", this.getEncoding0());
        this.setByteList(13, "), stopping downloads test", this.getEncoding0());
        this.setByteList(5, "digest/sha1", this.getEncoding0());
        this.setByteList(11, " seconds", this.getEncoding0());
        this.setByteList(18, "not-available", this.getEncoding0());
        this.setByteList(7, "instance-variable", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite0().call(threadContext, rubyObject, rubyObject, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getString0(threadContext.runtime, 32));
        file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite1().call(threadContext, rubyObject, rubyObject, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getString1(threadContext.runtime, 32));
        file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite2().call(threadContext, rubyObject, rubyObject, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getString2(threadContext.runtime, 32));
        file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite3().call(threadContext, rubyObject, rubyObject, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getString3(threadContext.runtime, 32));
        file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite4().call(threadContext, rubyObject, rubyObject, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getSymbol0(threadContext.runtime, "Resolv"), file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getString4(threadContext.runtime, 32));
        file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite5().call(threadContext, rubyObject, rubyObject, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getSymbol1(threadContext.runtime, "Digest"), file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getString5(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.module__1$RUBY$Measurements:(Lruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc_w           "Measurement"
        //    34: bipush          27
        //    36: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    42: invokestatic    ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.class_2$RUBY$Performance:(Lruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject class_2$RUBY$Performance(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc             "Performance"
        //    19: swap           
        //    20: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    23: dup            
        //    24: astore_2       
        //    25: aload_1        
        //    26: swap           
        //    27: aload_0        
        //    28: aload_1        
        //    29: ldc             ",0,0,-1"
        //    31: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    34: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    37: aload_0        
        //    38: aload_1        
        //    39: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    42: bipush          32
        //    44: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getString6:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    47: aload_1        
        //    48: ldc             "SHA1_FILE"
        //    50: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    53: pop            
        //    54: aload_1        
        //    55: aload_2        
        //    56: aload_0        
        //    57: ldc             "http_values"
        //    59: ldc             "method__3$RUBY$http_values"
        //    61: ldc             ",0,0,-1"
        //    63: iconst_0       
        //    64: ldc             "./lib//lister/runner/measurements/performance.rb"
        //    66: ldc             14
        //    68: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    71: ldc             "NONE"
        //    73: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: pop            
        //    77: aload_1        
        //    78: aload_2        
        //    79: aload_0        
        //    80: ldc             "execute"
        //    82: ldc             "method__4$RUBY$execute"
        //    84: ldc             ",0,0,-1"
        //    86: iconst_0       
        //    87: ldc             "./lib//lister/runner/measurements/performance.rb"
        //    89: ldc             18
        //    91: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    94: ldc             "NONE"
        //    96: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    99: pop            
        //   100: aload_1        
        //   101: aload_2        
        //   102: aload_0        
        //   103: ldc_w           "download_http"
        //   106: ldc_w           "method__5$RUBY$download_http"
        //   109: ldc_w           "url,1,0,-1"
        //   112: iconst_1       
        //   113: ldc             "./lib//lister/runner/measurements/performance.rb"
        //   115: ldc_w           24
        //   118: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   121: ldc_w           "qurl"
        //   124: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   127: pop            
        //   128: aload_1        
        //   129: aload_2        
        //   130: aload_0        
        //   131: ldc_w           "do_sha1"
        //   134: ldc_w           "method__6$RUBY$do_sha1"
        //   137: ldc             ",0,0,-1"
        //   139: iconst_0       
        //   140: ldc             "./lib//lister/runner/measurements/performance.rb"
        //   142: ldc_w           28
        //   145: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   148: ldc             "NONE"
        //   150: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   153: pop            
        //   154: aload_1        
        //   155: aload_2        
        //   156: aload_0        
        //   157: ldc_w           "get_http_sha1s"
        //   160: ldc_w           "method__7$RUBY$get_http_sha1s"
        //   163: ldc             ",0,0,-1"
        //   165: iconst_0       
        //   166: ldc             "./lib//lister/runner/measurements/performance.rb"
        //   168: ldc_w           32
        //   171: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   174: ldc             "NONE"
        //   176: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   179: pop            
        //   180: aload_1        
        //   181: aload_2        
        //   182: aload_0        
        //   183: ldc_w           "http_sha1s"
        //   186: ldc_w           "method__8$RUBY$http_sha1s"
        //   189: ldc             ",0,0,-1"
        //   191: iconst_0       
        //   192: ldc             "./lib//lister/runner/measurements/performance.rb"
        //   194: ldc_w           37
        //   197: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   200: ldc             "NONE"
        //   202: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   205: pop            
        //   206: aload_1        
        //   207: aload_2        
        //   208: aload_0        
        //   209: ldc_w           "sha1_for_size"
        //   212: ldc_w           "method__9$RUBY$sha1_for_size"
        //   215: ldc_w           "size;l,1,0,-1"
        //   218: iconst_1       
        //   219: ldc             "./lib//lister/runner/measurements/performance.rb"
        //   221: ldc_w           41
        //   224: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   227: ldc_w           "qsize"
        //   230: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   233: pop            
        //   234: aload_0        
        //   235: aload_1        
        //   236: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   239: bipush          10
        //   241: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getFixnum0:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   244: aload_1        
        //   245: ldc_w           "STOP_TRESHOLD"
        //   248: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   251: pop            
        //   252: aload_1        
        //   253: aload_2        
        //   254: aload_0        
        //   255: ldc_w           "do_http_downloads"
        //   258: ldc_w           "method__10$RUBY$do_http_downloads"
        //   261: ldc             ",0,0,-1"
        //   263: iconst_0       
        //   264: ldc             "./lib//lister/runner/measurements/performance.rb"
        //   266: ldc_w           51
        //   269: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   272: ldc             "NONE"
        //   274: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   277: pop            
        //   278: aload_1        
        //   279: aload_2        
        //   280: aload_0        
        //   281: ldc_w           "url_for_file"
        //   284: ldc_w           "method__13$RUBY$url_for_file"
        //   287: ldc_w           "file,1,0,-1"
        //   290: iconst_1       
        //   291: ldc             "./lib//lister/runner/measurements/performance.rb"
        //   293: ldc_w           78
        //   296: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   299: ldc_w           "qfile"
        //   302: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   305: pop            
        //   306: aload_1        
        //   307: aload_2        
        //   308: aload_0        
        //   309: ldc_w           "url_for_dl_file"
        //   312: ldc_w           "method__14$RUBY$url_for_dl_file"
        //   315: ldc_w           "size,1,0,-1"
        //   318: iconst_1       
        //   319: ldc             "./lib//lister/runner/measurements/performance.rb"
        //   321: ldc_w           82
        //   324: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   327: ldc_w           "qsize"
        //   330: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   333: pop            
        //   334: aload_1        
        //   335: aload_2        
        //   336: aload_0        
        //   337: ldc_w           "delta_t"
        //   340: ldc_w           "method__15$RUBY$delta_t"
        //   343: ldc_w           "t0;t1,0,0,-1"
        //   346: iconst_0       
        //   347: ldc             "./lib//lister/runner/measurements/performance.rb"
        //   349: ldc_w           86
        //   352: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   355: ldc             "NONE"
        //   357: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   360: pop            
        //   361: aload_0        
        //   362: bipush          59
        //   364: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   367: aload_1        
        //   368: aload_2        
        //   369: aload_2        
        //   370: aload_0        
        //   371: aload_1        
        //   372: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   375: ldc_w           "util_result"
        //   378: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getSymbol9:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   381: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   384: pop            
        //   385: aload_1        
        //   386: aload_2        
        //   387: aload_0        
        //   388: ldc_w           "route"
        //   391: ldc_w           "method__16$RUBY$route"
        //   394: ldc             ",0,0,-1"
        //   396: iconst_0       
        //   397: ldc             "./lib//lister/runner/measurements/performance.rb"
        //   399: ldc_w           95
        //   402: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   405: ldc             "NONE"
        //   407: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   410: pop            
        //   411: aload_1        
        //   412: aload_2        
        //   413: aload_0        
        //   414: ldc_w           "traceroute_cmd"
        //   417: ldc_w           "method__17$RUBY$traceroute_cmd"
        //   420: ldc             ",0,0,-1"
        //   422: iconst_0       
        //   423: ldc             "./lib//lister/runner/measurements/performance.rb"
        //   425: ldc_w           107
        //   428: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   431: ldc             "NONE"
        //   433: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   436: pop            
        //   437: aload_1        
        //   438: aload_2        
        //   439: aload_0        
        //   440: ldc_w           "traceroute_version"
        //   443: ldc_w           "method__18$RUBY$traceroute_version"
        //   446: ldc             ",0,0,-1"
        //   448: iconst_0       
        //   449: ldc             "./lib//lister/runner/measurements/performance.rb"
        //   451: ldc_w           111
        //   454: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   457: ldc             "NONE"
        //   459: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   462: pop            
        //   463: aload_1        
        //   464: aload_2        
        //   465: aload_0        
        //   466: ldc_w           "do_resolution"
        //   469: ldc_w           "method__19$RUBY$do_resolution"
        //   472: ldc_w           "retry_cnt;ret;err,0,1,-1"
        //   475: iconst_m1      
        //   476: ldc             "./lib//lister/runner/measurements/performance.rb"
        //   478: ldc_w           115
        //   481: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   484: ldc_w           "oretry_cnt"
        //   487: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   490: pop            
        //   491: aload_1        
        //   492: aload_2        
        //   493: aload_0        
        //   494: ldc_w           "destination_host"
        //   497: ldc_w           "method__22$RUBY$destination_host"
        //   500: ldc             ",0,0,-1"
        //   502: iconst_0       
        //   503: ldc             "./lib//lister/runner/measurements/performance.rb"
        //   505: ldc_w           130
        //   508: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   511: ldc             "NONE"
        //   513: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   516: pop            
        //   517: aload_1        
        //   518: aload_2        
        //   519: aload_0        
        //   520: ldc_w           "destination"
        //   523: ldc_w           "method__23$RUBY$destination"
        //   526: ldc             ",0,0,-1"
        //   528: iconst_0       
        //   529: ldc             "./lib//lister/runner/measurements/performance.rb"
        //   531: ldc_w           134
        //   534: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   537: ldc             "NONE"
        //   539: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   542: pop            
        //   543: aload_1        
        //   544: aload_2        
        //   545: aload_0        
        //   546: ldc_w           "do_traceroute"
        //   549: ldc_w           "method__24$RUBY$do_traceroute"
        //   552: ldc             ",0,0,-1"
        //   554: iconst_0       
        //   555: ldc             "./lib//lister/runner/measurements/performance.rb"
        //   557: ldc_w           138
        //   560: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   563: ldc             "NONE"
        //   565: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   568: pop            
        //   569: aload_1        
        //   570: aload_2        
        //   571: aload_0        
        //   572: ldc_w           "dns_routes"
        //   575: ldc_w           "method__25$RUBY$dns_routes"
        //   578: ldc             ",0,0,-1"
        //   580: iconst_0       
        //   581: ldc             "./lib//lister/runner/measurements/performance.rb"
        //   583: ldc_w           142
        //   586: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   589: ldc             "NONE"
        //   591: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   594: pop            
        //   595: aload_1        
        //   596: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   599: invokestatic    org/jruby/RubyFixnum.three:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   602: aload_1        
        //   603: ldc_w           "UNACCEPTABLE_COUNT_OF_DNS_SERVERS"
        //   606: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   609: pop            
        //   610: aload_1        
        //   611: aload_2        
        //   612: aload_0        
        //   613: ldc_w           "do_dns_traceroutes"
        //   616: ldc_w           "method__26$RUBY$do_dns_traceroutes"
        //   619: ldc             ",0,0,-1"
        //   621: iconst_0       
        //   622: ldc             "./lib//lister/runner/measurements/performance.rb"
        //   624: ldc_w           148
        //   627: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   630: ldc             "NONE"
        //   632: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   635: aload_1        
        //   636: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   639: goto            647
        //   642: aload_1        
        //   643: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   646: athrow         
        //   647: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  37     635    642    647    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "http_values", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$http_values(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@http_values") ? file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getByteList7() : null) == null) {
            rubyObject = file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.setVariable0(threadContext.runtime, "@http_values", object, RubyHash.newHash(threadContext.runtime));
        }
        else if (!(rubyObject = file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getVariable0(threadContext.runtime, "@http_values", object)).isTrue()) {
            rubyObject = file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.setVariable1(threadContext.runtime, "@http_values", object, RubyHash.newHash(threadContext.runtime));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "execute", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$execute(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite6().call(threadContext, rubyObject, rubyObject);
        file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite7().call(threadContext, rubyObject, rubyObject);
        return file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite8().call(threadContext, rubyObject, rubyObject);
    }
    
    @JRubyMethod(name = "download_http", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$download_http(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, final ThreadContext threadContext, final IRubyObject object, final IRubyObject rubyObject, final Block block) {
        return file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.setVariable2(threadContext.runtime, "@buffer", object, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite9().call(threadContext, object, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(10).call(threadContext, object, object, rubyObject)));
    }
    
    @JRubyMethod(name = "do_sha1", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$do_sha1(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getVariable1(threadContext.runtime, "@buffer", rubyObject).isTrue() ? file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(11).call(threadContext, rubyObject, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstantFrom1(RuntimeHelpers.checkIsModule(file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstant0(threadContext, "Digest")), threadContext, "SHA1"), file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getVariable2(threadContext.runtime, "@buffer", rubyObject)) : threadContext.nil;
    }
    
    @JRubyMethod(name = "get_http_sha1s", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$get_http_sha1s(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(12).call(threadContext, rubyObject, rubyObject, RubyString.newStringLight(threadContext.runtime, 20).append(file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getString8(threadContext.runtime, 32)).append(file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(13).call(threadContext, rubyObject, rubyObject, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstant2(threadContext, "SHA1_FILE")).asString()));
        return file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(14).call(threadContext, rubyObject, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(15).call(threadContext, rubyObject, rubyObject, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(16).call(threadContext, rubyObject, rubyObject, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstant3(threadContext, "SHA1_FILE"))));
    }
    
    @JRubyMethod(name = "http_sha1s", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$http_sha1s(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@http_sha1s") ? file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getByteList7() : null) == null) {
            rubyObject = file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.setVariable3(threadContext.runtime, "@http_sha1s", object, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(17).call(threadContext, object, object));
        }
        else if (!(rubyObject = file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getVariable3(threadContext.runtime, "@http_sha1s", object)).isTrue()) {
            rubyObject = file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.setVariable4(threadContext.runtime, "@http_sha1s", object, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(18).call(threadContext, object, object));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "sha1_for_size", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$sha1_for_size(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    17: bipush          19
        //    19: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    22: aload_1        
        //    23: aload_2        
        //    24: aload_0        
        //    25: bipush          20
        //    27: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    30: aload_1        
        //    31: aload_2        
        //    32: aload_0        
        //    33: bipush          21
        //    35: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    38: aload_1        
        //    39: aload_2        
        //    40: aload_2        
        //    41: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    44: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: aload_1        
        //    48: aload_2        
        //    49: aload_0        
        //    50: aload_1        
        //    51: ldc_w           "block_0$RUBY$sha1_for_size,1,line,false,2,./lib//lister/runner/measurements/performance.rb,42,true"
        //    54: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    57: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    60: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: pop            
        //    67: aload           locals
        //    69: aload_1        
        //    70: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    81: ifeq            118
        //    84: aload_0        
        //    85: bipush          25
        //    87: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    90: aload_1        
        //    91: aload_2        
        //    92: aload_0        
        //    93: bipush          26
        //    95: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    98: aload_1        
        //    99: aload_2        
        //   100: aload           locals
        //   102: aload_1        
        //   103: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   106: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   109: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   112: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   115: goto            122
        //   118: aload_1        
        //   119: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   122: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     109     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_0$RUBY$sha1_for_size(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          22
        //    28: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_0        
        //    34: bipush          23
        //    36: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    39: aload_1        
        //    40: aload_2        
        //    41: aload           line
        //    43: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    46: aload_0        
        //    47: bipush          24
        //    49: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    52: aload_1        
        //    53: aload_2        
        //    54: aload           5
        //    56: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    59: aload_1        
        //    60: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    69: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    72: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     48      9     line  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "do_http_downloads", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__10$RUBY$do_http_downloads(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, final ThreadContext context, final IRubyObject self, final Block block) {
        return file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(27).callIter(context, self, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstant4(context, "SIZES"), RuntimeHelpers.createBlock(context, self, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getBlockBody2(context, "block_1$RUBY$do_http_downloads,1,size;h;delay;err,false,2,./lib//lister/runner/measurements/performance.rb,52,false")));
    }
    
    public static IRubyObject block_1$RUBY$do_http_downloads(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    55: aload_1        
        //    56: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    59: invokestatic    org/jruby/RubyHash.newHash:(Lorg/jruby/Ruby;)Lorg/jruby/RubyHash;
        //    62: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: pop            
        //    66: aload_0        
        //    67: bipush          28
        //    69: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    72: aload_1        
        //    73: aload_2        
        //    74: aload_2        
        //    75: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    78: dup            
        //    79: aload_2        
        //    80: aload_0        
        //    81: bipush          29
        //    83: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    86: aload_0        
        //    87: bipush          30
        //    89: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    92: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //    95: aload           locals
        //    97: aload_1        
        //    98: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   101: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   104: aload           locals
        //   106: aload_1        
        //   107: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   110: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   113: aload_1        
        //   114: aload_2        
        //   115: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   118: pop            
        //   119: aload           locals
        //   121: aload_1        
        //   122: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   125: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   128: dup            
        //   129: aload_2        
        //   130: aload_0        
        //   131: bipush          31
        //   133: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   136: aload_0        
        //   137: bipush          32
        //   139: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   142: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   145: aload_0        
        //   146: aload_1        
        //   147: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   150: ldc_w           "expected_sha1"
        //   153: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   156: aload_0        
        //   157: bipush          33
        //   159: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   162: aload_1        
        //   163: aload_2        
        //   164: aload_2        
        //   165: aload           locals
        //   167: aload_1        
        //   168: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   171: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   174: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   177: aload_1        
        //   178: aload_2        
        //   179: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   182: pop            
        //   183: aload_0        
        //   184: bipush          34
        //   186: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   189: aload_1        
        //   190: aload_2        
        //   191: aload_2        
        //   192: aload_1        
        //   193: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   196: ldc_w           20
        //   199: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   202: aload_0        
        //   203: aload_1        
        //   204: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   207: bipush          32
        //   209: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getString9:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   212: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   215: aload_0        
        //   216: bipush          35
        //   218: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   221: aload_1        
        //   222: aload_2        
        //   223: aload_2        
        //   224: aload           locals
        //   226: aload_1        
        //   227: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   230: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   233: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   236: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   241: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   244: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   247: pop            
        //   248: aload_0        
        //   249: aload_1        
        //   250: aload_2        
        //   251: aload_3        
        //   252: aload           4
        //   254: invokestatic    ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.chained_11_rescue_1$RUBY$SYNTHETICdo_http_downloads:(Lruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   257: pop            
        //   258: aload_0        
        //   259: bipush          53
        //   261: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   264: aload_1        
        //   265: aload_2        
        //   266: aload_2        
        //   267: aload_1        
        //   268: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   271: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   274: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   277: areturn        
        //   278: pop            
        //   279: goto            53
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  53     225     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  53     278    278    282    Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject chained_11_rescue_1$RUBY$SYNTHETICdo_http_downloads(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, final ThreadContext threadContext, final IRubyObject self, final IRubyObject rubyObject, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        final IRubyObject errorInfo = threadContext.getErrorInfo();
        IRubyObject rubyObject2 = null;
        Label_0458: {
            try {
                try {
                    currentScope.setValueTwoDepthZero(file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(36).callIter(threadContext, self, self, RuntimeHelpers.createBlock(threadContext, self, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getBlockBody1(threadContext, "block_2$RUBY$do_http_downloads,-1,,false,0,./lib//lister/runner/measurements/performance.rb,58,true"))));
                    file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(39).call(threadContext, self, self, RubyString.newStringLight(threadContext.runtime, 20).append(file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getString(threadContext.runtime, 10, 32)).append(currentScope.getValueTwoDepthZeroOrNil(threadContext.nil).asString()).append(file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getString(threadContext.runtime, 11, 32)));
                    final IRubyObject valueOneDepthZeroOrNil = currentScope.getValueOneDepthZeroOrNil(threadContext.nil);
                    RuntimeHelpers.doAttrAsgn(valueOneDepthZeroOrNil, RuntimeHelpers.selectAttrAsgnCallSite(valueOneDepthZeroOrNil, self, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(40), file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(41)), file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getSymbol3(threadContext.runtime, "delay_s"), currentScope.getValueTwoDepthZeroOrNil(threadContext.nil), threadContext, self);
                    final IRubyObject valueOneDepthZeroOrNil2 = currentScope.getValueOneDepthZeroOrNil(threadContext.nil);
                    RuntimeHelpers.doAttrAsgn(valueOneDepthZeroOrNil2, RuntimeHelpers.selectAttrAsgnCallSite(valueOneDepthZeroOrNil2, self, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(42), file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(43)), file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getSymbol4(threadContext.runtime, "status"), file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getSymbol5(threadContext.runtime, "ok"), threadContext, self);
                    final IRubyObject valueOneDepthZeroOrNil3 = currentScope.getValueOneDepthZeroOrNil(threadContext.nil);
                    RuntimeHelpers.doAttrAsgn(valueOneDepthZeroOrNil3, RuntimeHelpers.selectAttrAsgnCallSite(valueOneDepthZeroOrNil3, self, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(44), file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(45)), file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getSymbol6(threadContext.runtime, "got_sha1"), file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(46).call(threadContext, self, self), threadContext, self);
                    if (file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(47).call(threadContext, self, currentScope.getValueTwoDepthZeroOrNil(threadContext.nil), file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstant5(threadContext, "STOP_TRESHOLD")).isTrue()) {
                        file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(48).call(threadContext, self, self, RubyString.newStringLight(threadContext.runtime, 20).append(file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getString(threadContext.runtime, 12, 32)).append(file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstant6(threadContext, "STOP_TRESHOLD").asString()).append(file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getString(threadContext.runtime, 13, 32)));
                        rubyObject2 = RuntimeHelpers.breakJump(threadContext, threadContext.nil);
                    }
                    else {
                        rubyObject2 = threadContext.nil;
                    }
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable t) {
                    if (RuntimeHelpers.isJavaExceptionHandled(t, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstant7(threadContext, "IOError"), threadContext).isTrue()) {
                        RuntimeHelpers.storeExceptionInErrorInfo(t, threadContext);
                        rubyObject2 = chained_12_rescue_line_69(file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, threadContext, self, rubyObject, block);
                        RuntimeHelpers.clearErrorInfo(threadContext);
                        break Label_0458;
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
    
    public static IRubyObject block_2$RUBY$do_http_downloads(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        return file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(37).call(threadContext, rubyObject, rubyObject, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(38).call(threadContext, rubyObject, rubyObject, currentScope.getNextCapturedScope().getValueZeroDepthZeroOrNil(threadContext.nil)));
    }
    
    public static IRubyObject chained_12_rescue_line_69(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final DynamicScope currentScope = context.getCurrentScope();
        currentScope.setValueThreeDepthZero(RuntimeHelpers.getGlobalVariable(context.runtime, "$!"));
        file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(49).call(context, rubyObject, rubyObject, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getString(context.runtime, 14, 32), file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getSymbol7(context.runtime, "error"));
        file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(50).call(context, rubyObject, rubyObject, currentScope.getValueThreeDepthZeroOrNil(context.nil));
        final IRubyObject valueOneDepthZeroOrNil = currentScope.getValueOneDepthZeroOrNil(context.nil);
        return RuntimeHelpers.doAttrAsgn(valueOneDepthZeroOrNil, RuntimeHelpers.selectAttrAsgnCallSite(valueOneDepthZeroOrNil, rubyObject, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(51), file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(52)), file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getSymbol4(context.runtime, "status"), file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getSymbol8(context.runtime, "ko"), context, rubyObject);
    }
    
    @JRubyMethod(name = "url_for_file", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__13$RUBY$url_for_file(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject two, final Block block) {
        return file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(54).call(context, rubyObject, RuntimeHelpers.constructRubyArray(context.runtime, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstant8(context, "WEBSITE"), two), file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getString(context.runtime, 15, 32));
    }
    
    @JRubyMethod(name = "url_for_dl_file", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__14$RUBY$url_for_dl_file(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(55).call(threadContext, rubyObject, rubyObject, RubyString.newStringLight(threadContext.runtime, 20).append(file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getString(threadContext.runtime, 16, 32)).append(rubyObject2.asString()).append(file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getString(threadContext.runtime, 17, 32)));
    }
    
    @JRubyMethod(name = "delta_t", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__15$RUBY$delta_t(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        IRubyObject t1 = context.nil;
        final IRubyObject t2 = file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(56).call(context, rubyObject, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstant9(context, "Time"));
        block.yieldSpecific(context);
        t1 = file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(57).call(context, rubyObject, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstant(context, "Time", 10));
        return file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(58).call(context, rubyObject, t1, t2);
    }
    
    @JRubyMethod(name = "route", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__16$RUBY$route(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(60).call(threadContext, self, self).isTrue() ? file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(61).callIter(threadContext, self, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(62).call(threadContext, self, self), RuntimeHelpers.createBlock(threadContext, self, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getBlockBody3(threadContext, "block_3$RUBY$route,1,res,false,2,./lib//lister/runner/measurements/performance.rb,97,true"))) : file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getString(threadContext.runtime, 18, 32);
    }
    
    public static IRubyObject block_3$RUBY$route(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    29: aload_0        
        //    30: aload_1        
        //    31: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    34: ldc_w           10
        //    37: ldc_w           "ttl"
        //    40: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    43: aload_0        
        //    44: bipush          63
        //    46: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload           res
        //    53: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    56: aload_0        
        //    57: aload_1        
        //    58: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    61: ldc_w           11
        //    64: ldc_w           "ip"
        //    67: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    70: aload_0        
        //    71: bipush          64
        //    73: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    76: aload_1        
        //    77: aload_2        
        //    78: aload           res
        //    80: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: aload_0        
        //    84: aload_1        
        //    85: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    88: ldc_w           12
        //    91: ldc_w           "rtts"
        //    94: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    97: aload_0        
        //    98: bipush          65
        //   100: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   103: aload_1        
        //   104: aload_2        
        //   105: aload           res
        //   107: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   110: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructHash:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyHash;
        //   113: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     89      9     res   Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "traceroute_cmd", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__17$RUBY$traceroute_cmd(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(66).call(threadContext, rubyObject, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstantFrom(RuntimeHelpers.checkIsModule(file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstant(threadContext, "Utils", 11)), threadContext, "Traceroute", 12), file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(67).call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "traceroute_version", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__18$RUBY$traceroute_version(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(68).call(threadContext, rubyObject, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstantFrom(RuntimeHelpers.checkIsModule(file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstant(threadContext, "Utils", 13)), threadContext, "Traceroute", 14));
    }
    
    @JRubyMethod(name = "do_resolution", frame = true, required = 0, optional = 1, rest = -1)
    public static IRubyObject method__19$RUBY$do_resolution(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
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
        //    41: invokestatic    org/jruby/RubyFixnum.zero:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    44: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: pop            
        //    48: pop            
        //    49: aload_0        
        //    50: bipush          69
        //    52: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    55: aload_1        
        //    56: aload_2        
        //    57: aload_2        
        //    58: aload_1        
        //    59: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    62: ldc_w           20
        //    65: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    68: aload_0        
        //    69: aload_1        
        //    70: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    73: bipush          19
        //    75: bipush          32
        //    77: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    80: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    83: aload           locals
        //    85: aload_1        
        //    86: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    92: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //    97: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   100: aload_0        
        //   101: aload_1        
        //   102: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   105: bipush          20
        //   107: bipush          32
        //   109: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   112: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   115: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   118: pop            
        //   119: aload           locals
        //   121: aload_1        
        //   122: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   125: invokestatic    org/jruby/RubyFixnum.zero:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   128: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   131: pop            
        //   132: aload_0        
        //   133: aload_1        
        //   134: aload_2        
        //   135: aload_3        
        //   136: aload           4
        //   138: invokestatic    ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.chained_20_rescue_2$RUBY$SYNTHETICdo_resolution:(Lruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   141: pop            
        //   142: aload           locals
        //   144: aload_1        
        //   145: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   148: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   151: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  49     103     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject chained_20_rescue_2$RUBY$SYNTHETICdo_resolution(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        final IRubyObject errorInfo = threadContext.getErrorInfo();
        IRubyObject rubyObject2 = null;
        Label_0139: {
            try {
                try {
                    rubyObject2 = currentScope.setValueOneDepthZero(file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(70).call(threadContext, rubyObject, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(71).call(threadContext, rubyObject, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstant(threadContext, "Resolv", 15)), file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(72).call(threadContext, rubyObject, rubyObject)));
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable t) {
                    if (RuntimeHelpers.isJavaExceptionHandled(t, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstantFrom(RuntimeHelpers.checkIsModule(file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstant(threadContext, "Resolv", 16)), threadContext, "ResolvError", 17), threadContext).isTrue()) {
                        RuntimeHelpers.storeExceptionInErrorInfo(t, threadContext);
                        rubyObject2 = chained_21_rescue_line_120(file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, threadContext, rubyObject, array, block);
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
    
    public static IRubyObject chained_21_rescue_line_120(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        currentScope.setValueTwoDepthZero(RuntimeHelpers.getGlobalVariable(threadContext.runtime, "$!"));
        return file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(73).call(threadContext, rubyObject, currentScope.getValueZeroDepthZeroOrNil(threadContext.nil), 2L).isTrue() ? file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(74).call(threadContext, rubyObject, rubyObject, currentScope.getValueTwoDepthZeroOrNil(threadContext.nil)) : currentScope.setValueOneDepthZero(file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(75).call(threadContext, rubyObject, rubyObject, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(76).call(threadContext, rubyObject, currentScope.getValueZeroDepthZeroOrNil(threadContext.nil), 1L)));
    }
    
    @JRubyMethod(name = "destination_host", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__22$RUBY$destination_host(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getString(threadContext.runtime, 21, 32);
    }
    
    @JRubyMethod(name = "destination", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__23$RUBY$destination(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@destination") ? file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getByteList7() : null) == null) {
            rubyObject = file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.setVariable5(threadContext.runtime, "@destination", object, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(77).call(threadContext, object, object));
        }
        else if (!(rubyObject = file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getVariable4(threadContext.runtime, "@destination", object)).isTrue()) {
            rubyObject = file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.setVariable6(threadContext.runtime, "@destination", object, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(78).call(threadContext, object, object));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "do_traceroute", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__24$RUBY$do_traceroute(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        return RuntimeHelpers.doAttrAsgn(rubyObject, RuntimeHelpers.selectAttrAsgnCallSite(rubyObject, rubyObject, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(79), file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(80)), file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(81).call(context, rubyObject, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstantFrom(RuntimeHelpers.checkIsModule(file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstant(context, "Utils", 18)), context, "Traceroute", 19), file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(82).call(context, rubyObject, rubyObject)), context, rubyObject);
    }
    
    @JRubyMethod(name = "dns_routes", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__25$RUBY$dns_routes(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@dns_routes") ? file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getByteList7() : null) == null) {
            rubyObject = file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.setVariable7(threadContext.runtime, "@dns_routes", object, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(83).call(threadContext, object, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstant(threadContext, "Hash", 20)));
        }
        else if (!(rubyObject = file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getVariable5(threadContext.runtime, "@dns_routes", object)).isTrue()) {
            rubyObject = file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.setVariable8(threadContext.runtime, "@dns_routes", object, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(84).call(threadContext, object, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstant(threadContext, "Hash", 21)));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "do_dns_traceroutes", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__26$RUBY$do_dns_traceroutes(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(85).callIter(threadContext, self, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite(86).call(threadContext, self, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstantFrom(RuntimeHelpers.checkIsModule(file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstant(threadContext, "Utils", 22)), threadContext, "DNS", 23)), RuntimeHelpers.createBlock(threadContext, self, file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getBlockBody5(threadContext, "block_4$RUBY$do_dns_traceroutes,2,ip;index;route,true,1,./lib//lister/runner/measurements/performance.rb,149,false")));
    }
    
    public static IRubyObject block_4$RUBY$do_dns_traceroutes(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    79: aload_0        
        //    80: bipush          87
        //    82: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    85: aload_1        
        //    86: aload_2        
        //    87: aload_0        
        //    88: aload_1        
        //    89: ldc_w           "Utils"
        //    92: bipush          24
        //    94: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    97: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   100: aload_0        
        //   101: swap           
        //   102: aload_1        
        //   103: ldc_w           "Traceroute"
        //   106: bipush          25
        //   108: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   111: aload           locals
        //   113: aload_1        
        //   114: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   117: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   120: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   123: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   126: pop            
        //   127: aload_0        
        //   128: bipush          88
        //   130: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   133: aload_1        
        //   134: aload_2        
        //   135: aload_2        
        //   136: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   139: dup            
        //   140: aload_2        
        //   141: aload_0        
        //   142: bipush          89
        //   144: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   147: aload_0        
        //   148: bipush          90
        //   150: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   153: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   156: aload           locals
        //   158: aload_1        
        //   159: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   162: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   165: aload_0        
        //   166: bipush          91
        //   168: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   171: aload_1        
        //   172: aload_2        
        //   173: aload           locals
        //   175: aload_1        
        //   176: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   179: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   182: aload_1        
        //   183: aload_2        
        //   184: aload_0        
        //   185: aload_1        
        //   186: ldc_w           "block_5$RUBY$do_dns_traceroutes,1,res,false,2,./lib//lister/runner/measurements/performance.rb,151,true"
        //   189: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getBlockBody4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   192: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   195: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   198: aload_1        
        //   199: aload_2        
        //   200: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   203: pop            
        //   204: aload_0        
        //   205: bipush          95
        //   207: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   210: aload_1        
        //   211: aload_2        
        //   212: aload           locals
        //   214: aload_1        
        //   215: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   218: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   221: aload_0        
        //   222: aload_1        
        //   223: ldc_w           "UNACCEPTABLE_COUNT_OF_DNS_SERVERS"
        //   226: bipush          26
        //   228: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   231: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   234: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   239: ifeq            253
        //   242: aload_1        
        //   243: aload_1        
        //   244: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   247: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.breakJump:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   250: goto            257
        //   253: aload_1        
        //   254: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   257: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  77     181     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_5$RUBY$do_dns_traceroutes(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    29: aload_0        
        //    30: aload_1        
        //    31: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    34: ldc_w           10
        //    37: ldc_w           "ttl"
        //    40: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    43: aload_0        
        //    44: bipush          92
        //    46: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload           res
        //    53: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    56: aload_0        
        //    57: aload_1        
        //    58: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    61: ldc_w           11
        //    64: ldc_w           "ip"
        //    67: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    70: aload_0        
        //    71: bipush          93
        //    73: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    76: aload_1        
        //    77: aload_2        
        //    78: aload           res
        //    80: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: aload_0        
        //    84: aload_1        
        //    85: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    88: ldc_w           12
        //    91: ldc_w           "rtts"
        //    94: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    97: aload_0        
        //    98: bipush          94
        //   100: invokevirtual   ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   103: aload_1        
        //   104: aload_2        
        //   105: aload           res
        //   107: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   110: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructHash:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyHash;
        //   113: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     89      9     res   Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_2$RUBY$Performance(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_2$RUBY$Performance(file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Measurements(file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31, threadContext, rubyObject, block);
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
        final FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31 = new FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31();
        final String string = FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.class.getClassLoader().getResource("ruby/jit/FILE_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.class").toString();
        file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_39355EC2B5DD1C76D0425BB9F9B85C8D1D81BE31.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
