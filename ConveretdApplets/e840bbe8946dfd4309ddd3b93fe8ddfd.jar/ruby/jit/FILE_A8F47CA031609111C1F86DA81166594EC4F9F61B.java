// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.exceptions.JumpException;
import org.jruby.RubyArray;
import org.jruby.runtime.DynamicScope;
import org.jruby.RubyString;
import org.jruby.runtime.Arity;
import org.jruby.ast.util.ArgsUtil;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B extends AbstractScript
{
    public FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B() {
        this.filename = "./lib//lister/runner/report.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("attr_reader\uffffF\uffffnew\uffffN\uffffperform\uffffN\ufffflog\uffffN\uffffrunner\uffffV\ufffffull_url\uffffV\uffffopen_connection\uffffN\uffffnew\uffffN\ufffffull_url\uffffV\uffffrequest_method=\uffffN\uffffrequest_method=\uffffV\uffffeach_pair\uffffN\uffffset_request_property\uffffN\uffffdo_output=\uffffN\uffffdo_output=\uffffV\uffffover_http\uffffF\uffffprivate\uffffV\uffffjava_to_ruby\uffffN\uffffjava_object\uffffN\uffffnew\uffffN\uffffRubyIO\uffffN\uffffjruby\uffffN\ufffforg\uffffV\uffffruntime\uffffN\uffffinput_stream\uffffN\uffffattr_reader\uffffF\uffffsuper\uffffS\uffffjoin\uffffN\uffffos\uffffV\uffffname\uffffN\uffffclass\uffffN\uffffexception\uffffV\uffffgsub\uffffN\uffffget_property\uffffN\uffffSystem\uffffN\ufffflang\uffffN\uffffjava\uffffV\uffffmap\uffffN\uffffbacktrace\uffffN\uffffexception\uffffV\uffffjoin\uffffN\uffffmap\uffffN\ufffflines\uffffN\uffffsub\uffffN\uffffjoin\uffffN\uffff+\uffffN\uffffmessage\uffffN\uffffexception\uffffV\uffffanonymized_backtrace\uffffV\uffffread\uffffN\uffffdebug_logfile\uffffN\uffffrunner\uffffV\uffffjoin\uffffN\ufffferror_message\uffffV\uffffdebug_logfile_content\uffffV\uffffover_http_post\uffffF\ufffflog\uffffN\uffffrunner\uffffV\uffffwrite\uffffN\uffffoutput_stream\uffffN\uffffto_java_bytes\uffffN\uffffreport_message\uffffV\uffffflush\uffffN\uffffoutput_stream\uffffN\uffffclose\uffffN\uffffoutput_stream\uffffN\uffffconnect\uffffN\uffffio_for_conn\uffffF\uffffresponse_message\uffffN\ufffflog\uffffN\uffffrunner\uffffV\uffffread\uffffN\uffffclose\uffffN\uffffinput_stream\uffffN\uffffclose\uffffN\uffffinput_stream\uffffN\uffffattr_reader\uffffF\uffffsuper\uffffS\uffffjoin\uffffN\uffffuid\uffffN\uffffrunner\uffffV\uffffrun_id\uffffN\uffffrunner\uffffV\uffffurl_chunck_name\uffffN\uffffmeasurement\uffffV\uffffcatch\uffffF\uffffeach\uffffN\uffffsleep\uffffF\uffffrand\uffffF\ufffflog\uffffN\uffffrunner\uffffV\uffffover_http_post\uffffF\ufffflog\uffffN\uffffrunner\uffffV\ufffflog\uffffN\uffffrunner\uffffV\uffffinspect\uffffN\uffffresult\uffffV\uffffJSON\uffffF\uffffresult\uffffV\ufffflog\uffffN\uffffrunner\uffffV\uffffwrite\uffffN\uffffoutput_stream\uffffN\uffffto_java_bytes\uffffN\uffffflush\uffffN\uffffoutput_stream\uffffN\uffffclose\uffffN\uffffoutput_stream\uffffN\uffffconnect\uffffN\uffffio_for_conn\uffffF\uffffresponse_message\uffffN\ufffflog\uffffN\uffffrunner\uffffV\uffffread\uffffN\uffffclose\uffffN\uffffinput_stream\uffffN\uffffthrow\uffffF\ufffflog\uffffN\uffffrunner\uffffV\ufffflog\uffffN\uffffrunner\uffffV\uffff\u0005\u0007\u0000\u0001\f\u0002\u0000\u0000\u0004\b\u0000\u0000\u0019\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(0, "Connecting to ", this.getEncoding0());
        this.setByteList(9, "\n", this.getEncoding0());
        this.setByteList(16, "run", this.getEncoding0());
        this.setByteList(20, "writing result", this.getEncoding0());
        this.setByteList(22, "JSON result is: \n ", this.getEncoding0());
        this.setByteList(17, "report", this.getEncoding0());
        this.setByteList(21, "ruby result is: \n ", this.getEncoding0());
        this.setByteList(23, "connection error rescued: ", this.getEncoding0());
        this.setByteList(6, "_", this.getEncoding0());
        this.setByteList(5, "\\W", this.getEncoding0());
        this.setByteList(13, "writing debug trace", this.getEncoding0());
        this.setByteList(8, "userdir", this.getEncoding0());
        this.setByteList(24, "aborting", this.getEncoding0());
        this.setByteList(18, "upload trial ", this.getEncoding0());
        this.setByteList(2, "bugreport", this.getEncoding0());
        this.setByteList(4, "os.name", this.getEncoding0());
        this.setByteList(3, "/", this.getEncoding0());
        this.setByteList(15, "agent", this.getEncoding0());
        this.setByteList(10, "\n###\n", this.getEncoding0());
        this.setByteList(1, "POST", this.getEncoding0());
        this.setByteList(12, "text/plain", this.getEncoding0());
        this.setByteList(14, "result code from server ", this.getEncoding0());
        this.setByteList(7, "^.*?\\.jar!", this.getEncoding0());
        this.setByteList(19, "application/json", this.getEncoding0());
        this.setByteList(11, "Content-Type", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B file_A8F47CA031609111C1F86DA81166594EC4F9F61B, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return module__0$RUBY$Lister(file_A8F47CA031609111C1F86DA81166594EC4F9F61B, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    33: invokestatic    ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.class_1$RUBY$Runner:(Lruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    36: aload_1        
        //    37: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    40: goto            48
        //    43: aload_1        
        //    44: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    47: athrow         
        //    48: aload_1        
        //    49: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    52: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     40     43     48     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_1$RUBY$Runner(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    10: ldc             "Runner"
        //    12: swap           
        //    13: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    16: dup            
        //    17: astore_2       
        //    18: aload_1        
        //    19: swap           
        //    20: aload_0        
        //    21: aload_1        
        //    22: ldc             ",0,0,-1"
        //    24: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    27: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    30: aload_0        
        //    31: aload_1        
        //    32: aload_2        
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.class_2$RUBY$Report:(Lruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: pop            
        //    40: aload_0        
        //    41: aload_1        
        //    42: aload_0        
        //    43: aload_1        
        //    44: ldc             "Report"
        //    46: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getConstant8:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    52: invokestatic    ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.class_9$RUBY$BugReport:(Lruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: pop            
        //    56: aload_0        
        //    57: aload_1        
        //    58: aload_0        
        //    59: aload_1        
        //    60: ldc             "Report"
        //    62: bipush          11
        //    64: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    70: invokestatic    ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.class_19$RUBY$MeasurementReport:(Lruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: aload_1        
        //    74: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    77: goto            85
        //    80: aload_1        
        //    81: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    84: athrow         
        //    85: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  30     73     80     85     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_2$RUBY$Report(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    10: ldc             "Report"
        //    12: swap           
        //    13: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    16: dup            
        //    17: astore_2       
        //    18: aload_1        
        //    19: swap           
        //    20: aload_0        
        //    21: aload_1        
        //    22: ldc             ",0,0,-1"
        //    24: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    27: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    30: aload_0        
        //    31: aload_1        
        //    32: ldc             "API_BASE"
        //    34: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    37: aload_1        
        //    38: ldc             "BASE_URL"
        //    40: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: pop            
        //    44: aload_0        
        //    45: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite0:()Lorg/jruby/runtime/CallSite;
        //    48: aload_1        
        //    49: aload_2        
        //    50: aload_2        
        //    51: aload_0        
        //    52: aload_1        
        //    53: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    56: ldc             "runner"
        //    58: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    61: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    64: pop            
        //    65: aload_1        
        //    66: aload_2        
        //    67: aload_2        
        //    68: aload_0        
        //    69: ldc             "report"
        //    71: ldc             "method__3$RUBY$report"
        //    73: ldc             "args;obj,0,0,0"
        //    75: iconst_m1      
        //    76: ldc             "./lib//lister/runner/report.rb"
        //    78: ldc             7
        //    80: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    83: ldc             "rargs"
        //    85: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: pop            
        //    89: aload_1        
        //    90: aload_2        
        //    91: aload_0        
        //    92: ldc             "initialize"
        //    94: ldc             "method__4$RUBY$initialize"
        //    96: ldc             "runner;args,1,0,1"
        //    98: bipush          -2
        //   100: ldc             "./lib//lister/runner/report.rb"
        //   102: ldc             12
        //   104: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   107: ldc             "qrunner;rargs"
        //   109: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   112: pop            
        //   113: aload_1        
        //   114: aload_2        
        //   115: aload_0        
        //   116: ldc             "full_url"
        //   118: ldc             "method__5$RUBY$full_url"
        //   120: ldc             ",0,0,-1"
        //   122: iconst_0       
        //   123: ldc             "./lib//lister/runner/report.rb"
        //   125: ldc             16
        //   127: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   130: ldc             "NONE"
        //   132: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   135: pop            
        //   136: aload_1        
        //   137: aload_2        
        //   138: aload_0        
        //   139: ldc_w           "over_http"
        //   142: ldc_w           "method__6$RUBY$over_http"
        //   145: ldc_w           "req_type;properties;conn,1,1,-1"
        //   148: bipush          -2
        //   150: ldc             "./lib//lister/runner/report.rb"
        //   152: ldc             20
        //   154: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   157: ldc_w           "qreq_type;oproperties"
        //   160: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   163: pop            
        //   164: aload_1        
        //   165: aload_2        
        //   166: aload_0        
        //   167: ldc_w           "over_http_post"
        //   170: ldc_w           "method__7$RUBY$over_http_post"
        //   173: ldc_w           "properties,0,1,-1"
        //   176: iconst_m1      
        //   177: ldc             "./lib//lister/runner/report.rb"
        //   179: ldc_w           31
        //   182: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   185: ldc_w           "oproperties"
        //   188: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   191: pop            
        //   192: aload_0        
        //   193: bipush          16
        //   195: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   198: aload_1        
        //   199: aload_2        
        //   200: aload_2        
        //   201: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   204: pop            
        //   205: aload_1        
        //   206: aload_2        
        //   207: aload_0        
        //   208: ldc_w           "io_for_conn"
        //   211: ldc_w           "method__8$RUBY$io_for_conn"
        //   214: ldc_w           "conn,1,0,-1"
        //   217: iconst_1       
        //   218: ldc             "./lib//lister/runner/report.rb"
        //   220: ldc_w           39
        //   223: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   226: ldc_w           "qconn"
        //   229: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   232: aload_1        
        //   233: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   236: goto            244
        //   239: aload_1        
        //   240: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   243: athrow         
        //   244: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  30     232    239    244    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "report", frame = true, required = 0, optional = 0, rest = 0)
    public static IRubyObject method__3$RUBY$report(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B file_A8F47CA031609111C1F86DA81166594EC4F9F61B, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] input, final Block block) {
        final IRubyObject nil = threadContext.nil;
        final IRubyObject args = RuntimeHelpers.createSubarray(input, threadContext.runtime, 0);
        final IRubyObject obj = file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite1().call(threadContext, rubyObject, rubyObject, ArgsUtil.convertToJavaArray(RuntimeHelpers.splatValue(args)));
        return file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite2().call(threadContext, rubyObject, obj);
    }
    
    @JRubyMethod(name = "initialize", frame = true, required = 1, optional = 0, rest = 1)
    public static IRubyObject method__4$RUBY$initialize(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     4: astore          10
        //     6: aload_1        
        //     7: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    10: aload_3        
        //    11: iconst_1       
        //    12: iconst_m1      
        //    13: invokestatic    org/jruby/runtime/Arity.raiseArgumentError:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;II)V
        //    16: aload_3        
        //    17: iconst_0       
        //    18: aaload         
        //    19: astore          9
        //    21: aload_3        
        //    22: aload_1        
        //    23: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    26: iconst_1       
        //    27: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createSubarray:([Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;I)Lorg/jruby/RubyArray;
        //    30: astore          args
        //    32: aload_0        
        //    33: aload_1        
        //    34: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    37: ldc             "@runner"
        //    39: aload_2        
        //    40: aload           runner
        //    42: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.setVariable0:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  ---------------------------------------
        //  32     14      9     runner  Lorg/jruby/runtime/builtin/IRubyObject;
        //  32     14      10    args    Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "full_url", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$full_url(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B file_A8F47CA031609111C1F86DA81166594EC4F9F61B, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        return file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getConstant1(context, "BASE_URL");
    }
    
    @JRubyMethod(name = "over_http", frame = true, required = 1, optional = 1, rest = -1)
    public static IRubyObject method__6$RUBY$over_http(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
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
        //    11: iconst_1       
        //    12: iconst_2       
        //    13: invokestatic    org/jruby/runtime/Arity.raiseArgumentError:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;II)V
        //    16: aload_3        
        //    17: iconst_0       
        //    18: aaload         
        //    19: aload           5
        //    21: swap           
        //    22: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    25: pop            
        //    26: aload_3        
        //    27: iconst_1       
        //    28: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.elementOrNull:([Lorg/jruby/runtime/builtin/IRubyObject;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    31: dup            
        //    32: ifnull          45
        //    35: aload           5
        //    37: swap           
        //    38: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: pop            
        //    42: goto            59
        //    45: aload           5
        //    47: aload_1        
        //    48: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    51: invokestatic    org/jruby/RubyHash.newHash:(Lorg/jruby/Ruby;)Lorg/jruby/RubyHash;
        //    54: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: pop            
        //    58: pop            
        //    59: aload_0        
        //    60: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite3:()Lorg/jruby/runtime/CallSite;
        //    63: aload_1        
        //    64: aload_2        
        //    65: aload_0        
        //    66: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite4:()Lorg/jruby/runtime/CallSite;
        //    69: aload_1        
        //    70: aload_2        
        //    71: aload_2        
        //    72: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: aload_1        
        //    76: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    79: ldc             20
        //    81: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    84: aload_0        
        //    85: aload_1        
        //    86: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    89: bipush          32
        //    91: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString0:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    94: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    97: aload_0        
        //    98: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite5:()Lorg/jruby/runtime/CallSite;
        //   101: aload_1        
        //   102: aload_2        
        //   103: aload_2        
        //   104: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   107: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   112: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   115: aload_0        
        //   116: aload_1        
        //   117: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   120: ldc_w           "debug"
        //   123: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   126: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   129: pop            
        //   130: aload           locals
        //   132: aload_0        
        //   133: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite6:()Lorg/jruby/runtime/CallSite;
        //   136: aload_1        
        //   137: aload_2        
        //   138: aload_0        
        //   139: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite7:()Lorg/jruby/runtime/CallSite;
        //   142: aload_1        
        //   143: aload_2        
        //   144: aload_0        
        //   145: aload_1        
        //   146: ldc_w           "URL"
        //   149: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getConstant2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   152: aload_0        
        //   153: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite8:()Lorg/jruby/runtime/CallSite;
        //   156: aload_1        
        //   157: aload_2        
        //   158: aload_2        
        //   159: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   162: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   165: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   168: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   171: pop            
        //   172: aload           locals
        //   174: aload_1        
        //   175: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   178: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   181: dup            
        //   182: aload_2        
        //   183: aload_0        
        //   184: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite9:()Lorg/jruby/runtime/CallSite;
        //   187: aload_0        
        //   188: bipush          10
        //   190: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   193: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   196: aload           locals
        //   198: aload_1        
        //   199: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   202: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   205: aload_1        
        //   206: aload_2        
        //   207: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   210: pop            
        //   211: aload_0        
        //   212: bipush          11
        //   214: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   217: aload_1        
        //   218: aload_2        
        //   219: aload           locals
        //   221: aload_1        
        //   222: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   225: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   228: aload_1        
        //   229: aload_2        
        //   230: aload_0        
        //   231: aload_1        
        //   232: ldc_w           "block_0$RUBY$over_http,2,k;v,true,1,./lib//lister/runner/report.rb,24,true"
        //   235: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   238: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   241: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   244: pop            
        //   245: aload           locals
        //   247: aload_1        
        //   248: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   251: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   254: dup            
        //   255: aload_2        
        //   256: aload_0        
        //   257: bipush          13
        //   259: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   262: aload_0        
        //   263: bipush          14
        //   265: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   268: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   271: aload_1        
        //   272: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   275: invokevirtual   org/jruby/Ruby.getTrue:()Lorg/jruby/RubyBoolean;
        //   278: aload_1        
        //   279: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //   282: aload_1        
        //   283: aload_2        
        //   284: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   287: pop            
        //   288: aload           4
        //   290: aload_1        
        //   291: aload           locals
        //   293: aload_1        
        //   294: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   297: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   300: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   303: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  59     245     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_0$RUBY$over_http(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    57: bipush          12
        //    59: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    62: aload_1        
        //    63: aload_2        
        //    64: aload           5
        //    66: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    69: aload_1        
        //    70: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: aload           k
        //    78: aload           v
        //    80: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  56     28      9     k     Lorg/jruby/runtime/builtin/IRubyObject;
        //  56     28      10    v     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "over_http_post", frame = true, required = 0, optional = 1, rest = -1)
    public static IRubyObject method__7$RUBY$over_http_post(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
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
        //    49: aload_0        
        //    50: bipush          15
        //    52: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    55: aload_1        
        //    56: aload_2        
        //    57: aload_2        
        //    58: aload_0        
        //    59: aload_1        
        //    60: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    63: bipush          32
        //    65: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    68: aload           locals
        //    70: aload_1        
        //    71: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: aload_1        
        //    78: aload_2        
        //    79: aload_0        
        //    80: aload_1        
        //    81: ldc_w           "block_1$RUBY$over_http_post,1,conn,false,2,./lib//lister/runner/report.rb,32,true"
        //    84: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getBlockBody1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    87: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    90: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    93: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  49     45      5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_1$RUBY$over_http_post(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    30: aload           conn
        //    32: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    35: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     11      9     conn  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "io_for_conn", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$io_for_conn(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B file_A8F47CA031609111C1F86DA81166594EC4F9F61B, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(17).call(threadContext, rubyObject, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getConstant3(threadContext, "Java"), file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(18).call(threadContext, rubyObject, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(19).call(threadContext, rubyObject, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(20).call(threadContext, rubyObject, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(21).call(threadContext, rubyObject, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(22).call(threadContext, rubyObject, rubyObject))), file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(23).call(threadContext, rubyObject, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getConstant4(threadContext, "JRuby")), file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(24).call(threadContext, rubyObject, rubyObject2))));
    }
    
    public static IRubyObject class_2$RUBY$Report(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B file_A8F47CA031609111C1F86DA81166594EC4F9F61B, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_2$RUBY$Report(file_A8F47CA031609111C1F86DA81166594EC4F9F61B, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_9$RUBY$BugReport(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc_w           "BugReport"
        //    20: swap           
        //    21: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    24: dup            
        //    25: astore_2       
        //    26: aload_1        
        //    27: swap           
        //    28: aload_0        
        //    29: aload_1        
        //    30: ldc             ",0,0,-1"
        //    32: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getScope3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    35: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    38: aload_0        
        //    39: bipush          25
        //    41: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    44: aload_1        
        //    45: aload_2        
        //    46: aload_2        
        //    47: aload_0        
        //    48: aload_1        
        //    49: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    52: ldc_w           "exception"
        //    55: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    58: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    61: pop            
        //    62: aload_1        
        //    63: aload_2        
        //    64: aload_0        
        //    65: ldc             "initialize"
        //    67: ldc_w           "method__10$RUBY$initialize"
        //    70: ldc_w           "runner;exc,2,0,-1"
        //    73: iconst_2       
        //    74: ldc             "./lib//lister/runner/report.rb"
        //    76: ldc_w           48
        //    79: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    82: ldc_w           "qrunner;qexc"
        //    85: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: pop            
        //    89: aload_1        
        //    90: aload_2        
        //    91: aload_0        
        //    92: ldc             "full_url"
        //    94: ldc_w           "method__11$RUBY$full_url"
        //    97: ldc             ",0,0,-1"
        //    99: iconst_0       
        //   100: ldc             "./lib//lister/runner/report.rb"
        //   102: ldc_w           53
        //   105: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   108: ldc             "NONE"
        //   110: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   113: pop            
        //   114: aload_1        
        //   115: aload_2        
        //   116: aload_0        
        //   117: ldc_w           "os"
        //   120: ldc_w           "method__12$RUBY$os"
        //   123: ldc             ",0,0,-1"
        //   125: iconst_0       
        //   126: ldc             "./lib//lister/runner/report.rb"
        //   128: ldc_w           61
        //   131: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   134: ldc             "NONE"
        //   136: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   139: pop            
        //   140: aload_1        
        //   141: aload_2        
        //   142: aload_0        
        //   143: ldc_w           "anonymized_backtrace"
        //   146: ldc_w           "method__13$RUBY$anonymized_backtrace"
        //   149: ldc             ",0,0,-1"
        //   151: iconst_0       
        //   152: ldc             "./lib//lister/runner/report.rb"
        //   154: ldc_w           65
        //   157: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   160: ldc             "NONE"
        //   162: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   165: pop            
        //   166: aload_1        
        //   167: aload_2        
        //   168: aload_0        
        //   169: ldc_w           "error_message"
        //   172: ldc_w           "method__14$RUBY$error_message"
        //   175: ldc             ",0,0,-1"
        //   177: iconst_0       
        //   178: ldc             "./lib//lister/runner/report.rb"
        //   180: ldc_w           73
        //   183: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   186: ldc             "NONE"
        //   188: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   191: pop            
        //   192: aload_1        
        //   193: aload_2        
        //   194: aload_0        
        //   195: ldc_w           "debug_logfile_content"
        //   198: ldc_w           "method__15$RUBY$debug_logfile_content"
        //   201: ldc             ",0,0,-1"
        //   203: iconst_0       
        //   204: ldc             "./lib//lister/runner/report.rb"
        //   206: ldc_w           77
        //   209: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   212: ldc             "NONE"
        //   214: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   217: pop            
        //   218: aload_1        
        //   219: aload_2        
        //   220: aload_0        
        //   221: ldc_w           "report_message"
        //   224: ldc_w           "method__16$RUBY$report_message"
        //   227: ldc             ",0,0,-1"
        //   229: iconst_0       
        //   230: ldc             "./lib//lister/runner/report.rb"
        //   232: ldc_w           81
        //   235: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   238: ldc             "NONE"
        //   240: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   243: pop            
        //   244: aload_1        
        //   245: aload_2        
        //   246: aload_0        
        //   247: ldc_w           "perform"
        //   250: ldc_w           "method__17$RUBY$perform"
        //   253: ldc             ",0,0,-1"
        //   255: iconst_0       
        //   256: ldc             "./lib//lister/runner/report.rb"
        //   258: ldc_w           85
        //   261: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   264: ldc             "NONE"
        //   266: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   269: aload_1        
        //   270: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   273: goto            281
        //   276: aload_1        
        //   277: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   280: athrow         
        //   281: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  38     269    276    281    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "initialize", frame = true, required = 2, optional = 0, rest = -1)
    public static IRubyObject method__10$RUBY$initialize(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final Block p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          6
        //     6: aload_3        
        //     7: aload           6
        //     9: swap           
        //    10: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    13: pop            
        //    14: aload           4
        //    16: aload           6
        //    18: swap           
        //    19: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    22: pop            
        //    23: aload_0        
        //    24: bipush          26
        //    26: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    29: aload_1        
        //    30: aload_2        
        //    31: aload_2        
        //    32: aload_1        
        //    33: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.getArgValues:(Lorg/jruby/runtime/ThreadContext;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //    36: aload           5
        //    38: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: pop            
        //    42: aload_0        
        //    43: aload_1        
        //    44: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    47: ldc_w           "@exception"
        //    50: aload_2        
        //    51: aload           locals
        //    53: aload_1        
        //    54: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.setVariable1:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  23     41      6     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "full_url", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__11$RUBY$full_url(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B file_A8F47CA031609111C1F86DA81166594EC4F9F61B, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(27).call(threadContext, rubyObject, RuntimeHelpers.constructRubyArray(threadContext.runtime, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getConstant5(threadContext, "BASE_URL"), file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString2(threadContext.runtime, 32), file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(28).call(threadContext, rubyObject, rubyObject), file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getConstant6(threadContext, "VERSION"), file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(29).call(threadContext, rubyObject, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(30).call(threadContext, rubyObject, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(31).call(threadContext, rubyObject, rubyObject)))), file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString3(threadContext.runtime, 32));
    }
    
    @JRubyMethod(name = "os", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__12$RUBY$os(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B file_A8F47CA031609111C1F86DA81166594EC4F9F61B, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(32).call(threadContext, rubyObject, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(33).call(threadContext, rubyObject, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(34).call(threadContext, rubyObject, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(35).call(threadContext, rubyObject, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(36).call(threadContext, rubyObject, rubyObject))), file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString4(threadContext.runtime, 32)), file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getRegexp0(threadContext.runtime, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getByteList5(), 512), file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString6(threadContext.runtime, 32));
    }
    
    @JRubyMethod(name = "anonymized_backtrace", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__13$RUBY$anonymized_backtrace(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B file_A8F47CA031609111C1F86DA81166594EC4F9F61B, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(37).callIter(threadContext, self, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(38).call(threadContext, self, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(39).call(threadContext, self, self)), RuntimeHelpers.createBlock(threadContext, self, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getBlockBody3(threadContext, "block_2$RUBY$anonymized_backtrace,1,str,false,2,./lib//lister/runner/report.rb,66,false")));
    }
    
    public static IRubyObject block_2$RUBY$anonymized_backtrace(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: bipush          40
        //    38: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_0        
        //    44: bipush          41
        //    46: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload_0        
        //    52: bipush          42
        //    54: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    57: aload_1        
        //    58: aload_2        
        //    59: aload           locals
        //    61: aload_1        
        //    62: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    68: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    71: aload_1        
        //    72: aload_2        
        //    73: aload_0        
        //    74: aload_1        
        //    75: ldc_w           "block_3$RUBY$anonymized_backtrace,1,l,false,2,./lib//lister/runner/report.rb,67,false"
        //    78: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getBlockBody2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    81: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    84: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    87: aload_0        
        //    88: aload_1        
        //    89: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    92: bipush          32
        //    94: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString9:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    97: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   100: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     66      5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_3$RUBY$anonymized_backtrace(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: bipush          43
        //    38: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload           locals
        //    45: aload_1        
        //    46: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: aload_0        
        //    53: aload_1        
        //    54: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    57: aload_0        
        //    58: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getByteList7:()Lorg/jruby/util/ByteList;
        //    61: ldc_w           512
        //    64: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getRegexp1:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //    67: aload_0        
        //    68: aload_1        
        //    69: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    72: bipush          32
        //    74: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    77: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: areturn        
        //    81: pop            
        //    82: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     46      5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     81     81     85     Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "error_message", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__14$RUBY$error_message(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B file_A8F47CA031609111C1F86DA81166594EC4F9F61B, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(44).call(threadContext, rubyObject, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(45).call(threadContext, rubyObject, RuntimeHelpers.constructRubyArray(threadContext.runtime, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(46).call(threadContext, rubyObject, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(47).call(threadContext, rubyObject, rubyObject))), file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(48).call(threadContext, rubyObject, rubyObject)), file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString9(threadContext.runtime, 32));
    }
    
    @JRubyMethod(name = "debug_logfile_content", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__15$RUBY$debug_logfile_content(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B file_A8F47CA031609111C1F86DA81166594EC4F9F61B, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        return file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(49).call(context, rubyObject, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getConstant7(context, "File"), file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(50).call(context, rubyObject, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(51).call(context, rubyObject, rubyObject)));
    }
    
    @JRubyMethod(name = "report_message", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__16$RUBY$report_message(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B file_A8F47CA031609111C1F86DA81166594EC4F9F61B, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(52).call(threadContext, rubyObject, RuntimeHelpers.constructRubyArray(threadContext.runtime, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(53).call(threadContext, rubyObject, rubyObject), file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(54).call(threadContext, rubyObject, rubyObject)), file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString(threadContext.runtime, 10, 32));
    }
    
    @JRubyMethod(name = "perform", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__17$RUBY$perform(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B file_A8F47CA031609111C1F86DA81166594EC4F9F61B, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(55).callIter(threadContext, self, self, RuntimeHelpers.constructHash(threadContext.runtime, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString(threadContext.runtime, 11, 32), file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString(threadContext.runtime, 12, 32)), RuntimeHelpers.createBlock(threadContext, self, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getBlockBody4(threadContext, "block_4$RUBY$perform,1,conn;io;resp;data,false,2,./lib//lister/runner/report.rb,86,false")));
    }
    
    public static IRubyObject block_4$RUBY$perform(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    54: bipush          56
        //    56: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    59: aload_1        
        //    60: aload_2        
        //    61: aload_0        
        //    62: bipush          57
        //    64: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    67: aload_1        
        //    68: aload_2        
        //    69: aload_2        
        //    70: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: aload_0        
        //    74: aload_1        
        //    75: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    78: bipush          13
        //    80: bipush          32
        //    82: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    85: aload_0        
        //    86: aload_1        
        //    87: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    90: ldc_w           "debug"
        //    93: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    96: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    99: pop            
        //   100: aload_0        
        //   101: bipush          58
        //   103: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   106: aload_1        
        //   107: aload_2        
        //   108: aload_0        
        //   109: bipush          59
        //   111: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   114: aload_1        
        //   115: aload_2        
        //   116: aload           locals
        //   118: aload_1        
        //   119: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   122: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   125: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   128: aload_0        
        //   129: bipush          60
        //   131: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   134: aload_1        
        //   135: aload_2        
        //   136: aload_0        
        //   137: bipush          61
        //   139: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   142: aload_1        
        //   143: aload_2        
        //   144: aload_2        
        //   145: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   148: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   151: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   154: pop            
        //   155: aload_0        
        //   156: bipush          62
        //   158: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   161: aload_1        
        //   162: aload_2        
        //   163: aload_0        
        //   164: bipush          63
        //   166: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   169: aload_1        
        //   170: aload_2        
        //   171: aload           locals
        //   173: aload_1        
        //   174: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   177: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   180: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   183: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   186: pop            
        //   187: aload_0        
        //   188: bipush          64
        //   190: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   193: aload_1        
        //   194: aload_2        
        //   195: aload_0        
        //   196: bipush          65
        //   198: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   201: aload_1        
        //   202: aload_2        
        //   203: aload           locals
        //   205: aload_1        
        //   206: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   209: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   212: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   215: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   218: pop            
        //   219: aload_0        
        //   220: aload_1        
        //   221: aload_2        
        //   222: aload_3        
        //   223: aload           4
        //   225: invokestatic    ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.chained_18_ensure_1$RUBY$__ensure__:(Lruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   228: areturn        
        //   229: pop            
        //   230: goto            53
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  53     176     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  53     229    229    233    Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject chained_18_ensure_1$RUBY$__ensure__(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B file_A8F47CA031609111C1F86DA81166594EC4F9F61B, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        try {
            file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(66).call(threadContext, rubyObject, currentScope.getValueZeroDepthZeroOrNil(threadContext.nil));
            currentScope.setValueOneDepthZero(file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(67).call(threadContext, rubyObject, rubyObject, currentScope.getValueZeroDepthZeroOrNil(threadContext.nil)));
            currentScope.setValueTwoDepthZero(file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(68).call(threadContext, rubyObject, currentScope.getValueZeroDepthZeroOrNil(threadContext.nil)));
            file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(69).call(threadContext, rubyObject, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(70).call(threadContext, rubyObject, rubyObject), RubyString.newStringLight(threadContext.runtime, 20).append(file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString(threadContext.runtime, 14, 32)).append(currentScope.getValueTwoDepthZeroOrNil(threadContext.nil).asString()), file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getSymbol1(threadContext.runtime, "debug"));
            final IRubyObject setValueThreeDepthZero = currentScope.setValueThreeDepthZero(file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(71).call(threadContext, rubyObject, currentScope.getValueOneDepthZeroOrNil(threadContext.nil)));
            file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(72).call(threadContext, rubyObject, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(73).call(threadContext, rubyObject, currentScope.getValueZeroDepthZeroOrNil(threadContext.nil)));
            return setValueThreeDepthZero;
        }
        finally {
            file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(74).call(threadContext, rubyObject, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(75).call(threadContext, rubyObject, currentScope.getValueZeroDepthZeroOrNil(threadContext.nil)));
        }
    }
    
    public static IRubyObject class_9$RUBY$BugReport(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B file_A8F47CA031609111C1F86DA81166594EC4F9F61B, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_9$RUBY$BugReport(file_A8F47CA031609111C1F86DA81166594EC4F9F61B, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_19$RUBY$MeasurementReport(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc_w           "MeasurementReport"
        //    20: swap           
        //    21: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    24: dup            
        //    25: astore_2       
        //    26: aload_1        
        //    27: swap           
        //    28: aload_0        
        //    29: aload_1        
        //    30: ldc             ",0,0,-1"
        //    32: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getScope4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    35: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    38: aload_0        
        //    39: bipush          76
        //    41: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    44: aload_1        
        //    45: aload_2        
        //    46: aload_2        
        //    47: aload_0        
        //    48: aload_1        
        //    49: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    52: ldc_w           "measurement"
        //    55: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    58: aload_0        
        //    59: aload_1        
        //    60: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    63: ldc_w           "result"
        //    66: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    69: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    72: pop            
        //    73: aload_1        
        //    74: aload_2        
        //    75: aload_0        
        //    76: ldc             "initialize"
        //    78: ldc_w           "method__20$RUBY$initialize"
        //    81: ldc_w           "runner;meas;res,3,0,-1"
        //    84: iconst_3       
        //    85: ldc             "./lib//lister/runner/report.rb"
        //    87: ldc_w           107
        //    90: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    93: ldc_w           "qrunner;qmeas;qres"
        //    96: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    99: pop            
        //   100: aload_1        
        //   101: aload_2        
        //   102: aload_0        
        //   103: ldc             "full_url"
        //   105: ldc_w           "method__21$RUBY$full_url"
        //   108: ldc             ",0,0,-1"
        //   110: iconst_0       
        //   111: ldc             "./lib//lister/runner/report.rb"
        //   113: ldc_w           113
        //   116: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   119: ldc             "NONE"
        //   121: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   124: pop            
        //   125: aload_1        
        //   126: aload_2        
        //   127: aload_0        
        //   128: ldc_w           "perform"
        //   131: ldc_w           "method__22$RUBY$perform"
        //   134: ldc             ",0,0,-1"
        //   136: iconst_0       
        //   137: ldc             "./lib//lister/runner/report.rb"
        //   139: ldc_w           123
        //   142: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   145: ldc             "NONE"
        //   147: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   150: aload_1        
        //   151: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   154: goto            162
        //   157: aload_1        
        //   158: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   161: athrow         
        //   162: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  38     150    157    162    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "initialize", frame = true, required = 3, optional = 0, rest = -1)
    public static IRubyObject method__20$RUBY$initialize(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final IRubyObject p5, final Block p6) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          7
        //     6: aload_3        
        //     7: aload           7
        //     9: swap           
        //    10: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    13: pop            
        //    14: aload           4
        //    16: aload           7
        //    18: swap           
        //    19: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    22: pop            
        //    23: aload           5
        //    25: aload           7
        //    27: swap           
        //    28: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    31: pop            
        //    32: aload_0        
        //    33: bipush          77
        //    35: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    38: aload_1        
        //    39: aload_2        
        //    40: aload_2        
        //    41: aload_1        
        //    42: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.getArgValues:(Lorg/jruby/runtime/ThreadContext;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: aload           6
        //    47: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: pop            
        //    51: aload_0        
        //    52: aload_1        
        //    53: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    56: ldc_w           "@measurement"
        //    59: aload_2        
        //    60: aload           locals
        //    62: aload_1        
        //    63: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    69: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.setVariable2:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    72: pop            
        //    73: aload_0        
        //    74: aload_1        
        //    75: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    78: ldc_w           "@result"
        //    81: aload_2        
        //    82: aload           locals
        //    84: aload_1        
        //    85: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    91: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.setVariable3:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    94: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  32     63      7     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "full_url", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__21$RUBY$full_url(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B file_A8F47CA031609111C1F86DA81166594EC4F9F61B, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        return file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(78).call(context, rubyObject, RubyArray.newArrayNoCopy(context.runtime, new IRubyObject[] { file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getConstant9(context, "BASE_URL"), file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString(context.runtime, 15, 32), file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(79).call(context, rubyObject, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(80).call(context, rubyObject, rubyObject)), file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString(context.runtime, 16, 32), file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(81).call(context, rubyObject, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(82).call(context, rubyObject, rubyObject)), file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString(context.runtime, 17, 32), file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(83).call(context, rubyObject, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(84).call(context, rubyObject, rubyObject)) }), file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString3(context.runtime, 32));
    }
    
    @JRubyMethod(name = "perform", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__22$RUBY$perform(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B file_A8F47CA031609111C1F86DA81166594EC4F9F61B, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(85).callIter(threadContext, self, self, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getSymbol5(threadContext.runtime, "stop"), RuntimeHelpers.createBlock(threadContext, self, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getBlockBody7(threadContext, "block_5$RUBY$perform,-1,count,false,0,./lib//lister/runner/report.rb,124,false")));
    }
    
    public static IRubyObject block_5$RUBY$perform(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    31: aload_1        
        //    32: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    35: invokestatic    org/jruby/RubyFixnum.three:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    38: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: pop            
        //    42: aload_0        
        //    43: bipush          86
        //    45: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    48: aload_1        
        //    49: aload_2        
        //    50: aload_1        
        //    51: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    54: aload_1        
        //    55: aload_1        
        //    56: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    59: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    62: aload           locals
        //    64: aload_1        
        //    65: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    68: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    71: invokestatic    org/jruby/RubyRange.newInclusiveRange:(Lorg/jruby/Ruby;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyRange;
        //    74: aload_1        
        //    75: aload_2        
        //    76: aload_0        
        //    77: aload_1        
        //    78: ldc_w           "block_6$RUBY$perform,1,trial,false,2,./lib//lister/runner/report.rb,126,false"
        //    81: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getBlockBody6:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    84: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    87: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    90: pop            
        //    91: aload_0        
        //    92: bipush          120
        //    94: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    97: aload_1        
        //    98: aload_2        
        //    99: aload_0        
        //   100: bipush          121
        //   102: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   105: aload_1        
        //   106: aload_2        
        //   107: aload_2        
        //   108: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   111: aload_0        
        //   112: aload_1        
        //   113: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   116: bipush          24
        //   118: bipush          32
        //   120: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   123: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   126: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  29     98      5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_6$RUBY$perform(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: bipush          87
        //    38: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_2        
        //    44: aload_0        
        //    45: bipush          88
        //    47: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    50: aload_1        
        //    51: aload_2        
        //    52: aload_2        
        //    53: aload_0        
        //    54: aload_1        
        //    55: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    58: ldc2_w          0.5
        //    61: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getFloat0:(Lorg/jruby/Ruby;D)Lorg/jruby/RubyFloat;
        //    64: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: pop            
        //    71: aload_0        
        //    72: bipush          89
        //    74: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    77: aload_1        
        //    78: aload_2        
        //    79: aload_0        
        //    80: bipush          90
        //    82: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    85: aload_1        
        //    86: aload_2        
        //    87: aload_2        
        //    88: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    91: aload_1        
        //    92: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    95: ldc             20
        //    97: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   100: aload_0        
        //   101: aload_1        
        //   102: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   105: bipush          18
        //   107: bipush          32
        //   109: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   112: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   115: aload           locals
        //   117: aload_1        
        //   118: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   121: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   124: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   129: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   132: aload_0        
        //   133: aload_1        
        //   134: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   137: bipush          32
        //   139: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString3:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   142: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   145: aload           locals
        //   147: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   150: aload_1        
        //   151: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   154: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   157: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   162: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   165: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   168: pop            
        //   169: aload_0        
        //   170: bipush          91
        //   172: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   175: aload_1        
        //   176: aload_2        
        //   177: aload_2        
        //   178: aload_1        
        //   179: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   182: aload_0        
        //   183: aload_1        
        //   184: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   187: bipush          11
        //   189: bipush          32
        //   191: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   194: aload_0        
        //   195: aload_1        
        //   196: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   199: bipush          19
        //   201: bipush          32
        //   203: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   206: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructHash:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyHash;
        //   209: aload_1        
        //   210: aload_2        
        //   211: aload_0        
        //   212: aload_1        
        //   213: ldc_w           "block_7$RUBY$perform,1,conn;json;io;resp;data;err,false,2,./lib//lister/runner/report.rb,129,false"
        //   216: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getBlockBody5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   219: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   222: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   225: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     191     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_7$RUBY$perform(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    69: aload_0        
        //    70: bipush          92
        //    72: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    75: aload_1        
        //    76: aload_2        
        //    77: aload_0        
        //    78: bipush          93
        //    80: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    83: aload_1        
        //    84: aload_2        
        //    85: aload_2        
        //    86: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: aload_0        
        //    90: aload_1        
        //    91: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    94: bipush          20
        //    96: bipush          32
        //    98: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   101: aload_0        
        //   102: aload_1        
        //   103: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   106: ldc_w           "debug"
        //   109: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   112: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   115: pop            
        //   116: aload_0        
        //   117: bipush          94
        //   119: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   122: aload_1        
        //   123: aload_2        
        //   124: aload_0        
        //   125: bipush          95
        //   127: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   130: aload_1        
        //   131: aload_2        
        //   132: aload_2        
        //   133: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   136: aload_1        
        //   137: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   140: ldc             20
        //   142: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   145: aload_0        
        //   146: aload_1        
        //   147: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   150: bipush          21
        //   152: bipush          32
        //   154: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   157: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   160: aload_0        
        //   161: bipush          96
        //   163: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   166: aload_1        
        //   167: aload_2        
        //   168: aload_0        
        //   169: bipush          97
        //   171: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   174: aload_1        
        //   175: aload_2        
        //   176: aload_2        
        //   177: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   180: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   183: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   188: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   191: aload_0        
        //   192: aload_1        
        //   193: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   196: ldc_w           "debug"
        //   199: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   202: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   205: pop            
        //   206: aload           locals
        //   208: aload_0        
        //   209: bipush          98
        //   211: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   214: aload_1        
        //   215: aload_2        
        //   216: aload_2        
        //   217: aload_0        
        //   218: bipush          99
        //   220: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   223: aload_1        
        //   224: aload_2        
        //   225: aload_2        
        //   226: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   229: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   232: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   235: pop            
        //   236: aload_0        
        //   237: bipush          100
        //   239: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   242: aload_1        
        //   243: aload_2        
        //   244: aload_0        
        //   245: bipush          101
        //   247: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   250: aload_1        
        //   251: aload_2        
        //   252: aload_2        
        //   253: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   256: aload_1        
        //   257: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   260: ldc             20
        //   262: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   265: aload_0        
        //   266: aload_1        
        //   267: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   270: bipush          22
        //   272: bipush          32
        //   274: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   277: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   280: aload           locals
        //   282: aload_1        
        //   283: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   286: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   289: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   294: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   297: aload_0        
        //   298: aload_1        
        //   299: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   302: ldc_w           "debug"
        //   305: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   308: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   311: pop            
        //   312: aload_0        
        //   313: bipush          102
        //   315: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   318: aload_1        
        //   319: aload_2        
        //   320: aload_0        
        //   321: bipush          103
        //   323: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   326: aload_1        
        //   327: aload_2        
        //   328: aload           locals
        //   330: aload_1        
        //   331: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   334: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   337: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   340: aload_0        
        //   341: bipush          104
        //   343: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   346: aload_1        
        //   347: aload_2        
        //   348: aload           locals
        //   350: aload_1        
        //   351: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   354: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   357: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   360: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   363: pop            
        //   364: aload_0        
        //   365: bipush          105
        //   367: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   370: aload_1        
        //   371: aload_2        
        //   372: aload_0        
        //   373: bipush          106
        //   375: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   378: aload_1        
        //   379: aload_2        
        //   380: aload           locals
        //   382: aload_1        
        //   383: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   386: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   389: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   392: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   395: pop            
        //   396: aload_0        
        //   397: bipush          107
        //   399: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   402: aload_1        
        //   403: aload_2        
        //   404: aload_0        
        //   405: bipush          108
        //   407: invokevirtual   ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   410: aload_1        
        //   411: aload_2        
        //   412: aload           locals
        //   414: aload_1        
        //   415: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   418: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   421: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   424: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   427: pop            
        //   428: aload_0        
        //   429: aload_1        
        //   430: aload_2        
        //   431: aload_3        
        //   432: aload           4
        //   434: invokestatic    ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.chained_23_rescue_1$RUBY$SYNTHETICperform:(Lruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   437: areturn        
        //   438: pop            
        //   439: goto            69
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  69     369     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  69     438    438    442    Lorg/jruby/exceptions/JumpException$RedoJump;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject chained_23_rescue_1$RUBY$SYNTHETICperform(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B file_A8F47CA031609111C1F86DA81166594EC4F9F61B, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        final IRubyObject[] values = currentScope.getValues();
        final IRubyObject errorInfo = threadContext.getErrorInfo();
        IRubyObject rubyObject3 = null;
        Label_0314: {
            try {
                try {
                    file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(109).call(threadContext, rubyObject, currentScope.getValueZeroDepthZeroOrNil(threadContext.nil));
                    currentScope.setValueTwoDepthZero(file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(110).call(threadContext, rubyObject, rubyObject, currentScope.getValueZeroDepthZeroOrNil(threadContext.nil)));
                    currentScope.setValueThreeDepthZero(file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(111).call(threadContext, rubyObject, currentScope.getValueZeroDepthZeroOrNil(threadContext.nil)));
                    file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(112).call(threadContext, rubyObject, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(113).call(threadContext, rubyObject, rubyObject), RubyString.newStringLight(threadContext.runtime, 20).append(file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString(threadContext.runtime, 14, 32)).append(currentScope.getValueThreeDepthZeroOrNil(threadContext.nil).asString()), file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getSymbol1(threadContext.runtime, "debug"));
                    values[4] = file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(114).call(threadContext, rubyObject, currentScope.getValueTwoDepthZeroOrNil(threadContext.nil));
                    file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(115).call(threadContext, rubyObject, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(116).call(threadContext, rubyObject, currentScope.getValueZeroDepthZeroOrNil(threadContext.nil)));
                    rubyObject3 = file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(117).call(threadContext, rubyObject, rubyObject, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getSymbol5(threadContext.runtime, "stop"));
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable t) {
                    if (RuntimeHelpers.isJavaExceptionHandled(t, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getConstant(threadContext, "NativeException", 10), threadContext).isTrue()) {
                        RuntimeHelpers.storeExceptionInErrorInfo(t, threadContext);
                        rubyObject3 = chained_24_rescue_line_145(file_A8F47CA031609111C1F86DA81166594EC4F9F61B, threadContext, rubyObject, rubyObject2, block);
                        RuntimeHelpers.clearErrorInfo(threadContext);
                        break Label_0314;
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
        return rubyObject3;
    }
    
    public static IRubyObject chained_24_rescue_line_145(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B file_A8F47CA031609111C1F86DA81166594EC4F9F61B, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final IRubyObject[] values = threadContext.getCurrentScope().getValues();
        values[5] = RuntimeHelpers.getGlobalVariable(threadContext.runtime, "$!");
        return file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(118).call(threadContext, rubyObject, file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getCallSite(119).call(threadContext, rubyObject, rubyObject), RubyString.newStringLight(threadContext.runtime, 20).append(file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getString(threadContext.runtime, 23, 32)).append(values[5].asString()), file_A8F47CA031609111C1F86DA81166594EC4F9F61B.getSymbol6(threadContext.runtime, "error"));
    }
    
    public static IRubyObject class_19$RUBY$MeasurementReport(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B file_A8F47CA031609111C1F86DA81166594EC4F9F61B, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_19$RUBY$MeasurementReport(file_A8F47CA031609111C1F86DA81166594EC4F9F61B, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_1$RUBY$Runner(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B file_A8F47CA031609111C1F86DA81166594EC4F9F61B, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_1$RUBY$Runner(file_A8F47CA031609111C1F86DA81166594EC4F9F61B, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B file_A8F47CA031609111C1F86DA81166594EC4F9F61B, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_A8F47CA031609111C1F86DA81166594EC4F9F61B, threadContext, rubyObject, block);
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
        final FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B file_A8F47CA031609111C1F86DA81166594EC4F9F61B = new FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B();
        final String string = FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.class.getClassLoader().getResource("ruby/jit/FILE_A8F47CA031609111C1F86DA81166594EC4F9F61B.class").toString();
        file_A8F47CA031609111C1F86DA81166594EC4F9F61B.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_A8F47CA031609111C1F86DA81166594EC4F9F61B.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
