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

public class FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD extends AbstractScript
{
    public FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD() {
        this.filename = "./lib//lister/measurements/services.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffversion\uffffF\uffffold_versions\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffreport\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffreport\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffreport\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffreport\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffff\u0003\u0004\u0000\u0000\u0001\u0000\u0000\u0000\u0000\u0004\u0000\u0000\u0014\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(17, "La liste des ports UDP en \uffc3\uffa9coute sur la machine", this.getEncoding0());
        this.setByteList(14, "The list of programs running", this.getEncoding0());
        this.setByteList(1, "0.1.0", this.getEncoding0());
        this.setByteList(2, "dev", this.getEncoding0());
        this.setByteList(0, "lister/measurement", this.getEncoding0());
        this.setByteList(9, "English-HTML", this.getEncoding0());
        this.setByteList(10, "<p>Ce module obtient le nom des applications qui tournent dans l'ordinateur\npendant les mesures.  Si le syst\uffc3\uffa8me d'exploitation fait une diff\uffc3\uffa9rence entre les services et les\nprocessus, nous mesurons les deux.  Nous obtenons \uffc3\uffa9galement la liste des\nports TCP et UDP en \uffc3\uffa9coute (ex., les serveurs).  </p>", this.getEncoding0());
        this.setByteList(13, "La liste des services syst\uffc3\uffa8mes en marche", this.getEncoding0());
        this.setByteList(19, "La liste des ports TCP en \uffc3\uffa9coute sur la machine", this.getEncoding0());
        this.setByteList(16, "The list of UDP listening sockets' ports", this.getEncoding0());
        this.setByteList(6, "Liste des applications en cours d'ex\uffc3\uffa9cution", this.getEncoding0());
        this.setByteList(8, "<p>This module collects the set of applications (i.e., process names) that are running in the computer during the experiment.\n      In the case where the OS differentiates processes from services (e.g., two services may run under the same process name) we collect both.\n      We also collect the ports of UDP and TCP listening sockets (e.g., servers).\n      ", this.getEncoding0());
        this.setByteList(7, "Fran\uffc3\uffa7ais", this.getEncoding0());
        this.setByteList(15, "La liste des programmes en marche", this.getEncoding0());
        this.setByteList(3, "0.0.1", this.getEncoding0());
        this.setByteList(5, "List of running applications", this.getEncoding0());
        this.setByteList(18, "The list of TCP listening sockets' ports", this.getEncoding0());
        this.setByteList(4, "0.0.2", this.getEncoding0());
        this.setByteList(12, "The list of System services running", this.getEncoding0());
        this.setByteList(11, "Fran\uffc3\uffa7ais-HTML", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getCallSite0().call(threadContext, rubyObject, rubyObject, file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getString0(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.module__1$RUBY$Measurements:(Lruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc_w           "Measurement"
        //    34: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    37: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    40: invokestatic    ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.class_2$RUBY$Services:(Lruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject class_2$RUBY$Services(final FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    13: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    16: aload_2        
        //    17: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareSuperClass:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyClass;
        //    20: aload_1        
        //    21: aload_1        
        //    22: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    25: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    28: swap           
        //    29: ldc             "Services"
        //    31: swap           
        //    32: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    35: dup            
        //    36: astore_2       
        //    37: aload_1        
        //    38: swap           
        //    39: aload_0        
        //    40: aload_1        
        //    41: ldc             ",0,0,-1"
        //    43: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    46: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClass:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    49: aload_1        
        //    50: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //    53: astore          locals
        //    55: aload_0        
        //    56: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getCallSite1:()Lorg/jruby/runtime/CallSite;
        //    59: aload_1        
        //    60: aload_2        
        //    61: aload_2        
        //    62: aload_0        
        //    63: aload_1        
        //    64: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    67: bipush          32
        //    69: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getString1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    72: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: pop            
        //    76: aload_0        
        //    77: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getCallSite2:()Lorg/jruby/runtime/CallSite;
        //    80: aload_1        
        //    81: aload_2        
        //    82: aload_2        
        //    83: aload_0        
        //    84: aload_1        
        //    85: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    88: bipush          32
        //    90: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getString2:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    93: aload_0        
        //    94: aload_1        
        //    95: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    98: bipush          32
        //   100: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getString3:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   103: aload_0        
        //   104: aload_1        
        //   105: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   108: bipush          32
        //   110: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getString4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   113: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   116: pop            
        //   117: aload_0        
        //   118: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getCallSite3:()Lorg/jruby/runtime/CallSite;
        //   121: aload_1        
        //   122: aload_2        
        //   123: aload_2        
        //   124: aload_0        
        //   125: aload_1        
        //   126: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   129: bipush          32
        //   131: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getString5:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   134: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   137: pop            
        //   138: aload_0        
        //   139: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getCallSite4:()Lorg/jruby/runtime/CallSite;
        //   142: aload_1        
        //   143: aload_2        
        //   144: aload_2        
        //   145: aload_0        
        //   146: aload_1        
        //   147: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   150: bipush          64
        //   152: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getString6:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   155: aload_0        
        //   156: aload_1        
        //   157: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   160: bipush          64
        //   162: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   165: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   168: pop            
        //   169: aload_0        
        //   170: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getCallSite5:()Lorg/jruby/runtime/CallSite;
        //   173: aload_1        
        //   174: aload_2        
        //   175: aload_2        
        //   176: aload_0        
        //   177: aload_1        
        //   178: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   181: bipush          32
        //   183: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getString8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   186: aload_0        
        //   187: aload_1        
        //   188: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   191: bipush          32
        //   193: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getString9:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   196: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   199: pop            
        //   200: aload_0        
        //   201: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getCallSite6:()Lorg/jruby/runtime/CallSite;
        //   204: aload_1        
        //   205: aload_2        
        //   206: aload_2        
        //   207: aload_0        
        //   208: aload_1        
        //   209: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   212: bipush          10
        //   214: bipush          64
        //   216: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   219: aload_0        
        //   220: aload_1        
        //   221: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   224: bipush          11
        //   226: bipush          64
        //   228: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   231: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   234: pop            
        //   235: aload_0        
        //   236: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getCallSite7:()Lorg/jruby/runtime/CallSite;
        //   239: aload_1        
        //   240: aload_2        
        //   241: aload_2        
        //   242: aload_0        
        //   243: aload_1        
        //   244: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   247: ldc             "system"
        //   249: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   252: aload_1        
        //   253: aload_2        
        //   254: aload_0        
        //   255: aload_1        
        //   256: ldc             "block_0$RUBY$Services,-1,,false,0,./lib//lister/measurements/services.rb,24,true"
        //   258: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   261: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   264: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   267: pop            
        //   268: aload_0        
        //   269: bipush          10
        //   271: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   274: aload_1        
        //   275: aload_2        
        //   276: aload_2        
        //   277: aload_0        
        //   278: aload_1        
        //   279: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   282: ldc             "programs"
        //   284: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   287: aload_1        
        //   288: aload_2        
        //   289: aload_0        
        //   290: aload_1        
        //   291: ldc             "block_1$RUBY$Services,-1,,false,0,./lib//lister/measurements/services.rb,29,true"
        //   293: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getBlockBody1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   296: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   299: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   302: pop            
        //   303: aload_0        
        //   304: bipush          13
        //   306: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   309: aload_1        
        //   310: aload_2        
        //   311: aload_2        
        //   312: aload_0        
        //   313: aload_1        
        //   314: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   317: ldc             "udp"
        //   319: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   322: aload_1        
        //   323: aload_2        
        //   324: aload_0        
        //   325: aload_1        
        //   326: ldc             "block_2$RUBY$Services,-1,,false,0,./lib//lister/measurements/services.rb,34,true"
        //   328: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getBlockBody2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   331: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   334: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   337: pop            
        //   338: aload_0        
        //   339: bipush          16
        //   341: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   344: aload_1        
        //   345: aload_2        
        //   346: aload_2        
        //   347: aload_0        
        //   348: aload_1        
        //   349: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   352: ldc             "tcp"
        //   354: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   357: aload_1        
        //   358: aload_2        
        //   359: aload_0        
        //   360: aload_1        
        //   361: ldc             "block_3$RUBY$Services,-1,,false,0,./lib//lister/measurements/services.rb,39,true"
        //   363: invokevirtual   ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getBlockBody3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   366: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   369: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   372: aload_1        
        //   373: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   376: goto            384
        //   379: aload_1        
        //   380: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   383: athrow         
        //   384: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  55     330     4     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  55     372    379    384    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_0$RUBY$Services(final FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        threadContext.getCurrentScope();
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getCallSite8().call(threadContext, rubyObject, rubyObject, file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getString(threadContext.runtime, 12, 32));
        return file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getCallSite9().call(threadContext, rubyObject, rubyObject, file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getString(threadContext.runtime, 13, 64), file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getString7(threadContext.runtime, 64));
    }
    
    public static IRubyObject block_1$RUBY$Services(final FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        threadContext.getCurrentScope();
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getCallSite(11).call(threadContext, rubyObject, rubyObject, file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getString(threadContext.runtime, 14, 32));
        return file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getCallSite(12).call(threadContext, rubyObject, rubyObject, file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getString(threadContext.runtime, 15, 32), file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getString7(threadContext.runtime, 64));
    }
    
    public static IRubyObject block_2$RUBY$Services(final FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        threadContext.getCurrentScope();
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getCallSite(14).call(threadContext, rubyObject, rubyObject, file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getString(threadContext.runtime, 16, 32));
        return file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getCallSite(15).call(threadContext, rubyObject, rubyObject, file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getString(threadContext.runtime, 17, 64), file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getString7(threadContext.runtime, 64));
    }
    
    public static IRubyObject block_3$RUBY$Services(final FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        threadContext.getCurrentScope();
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getCallSite(17).call(threadContext, rubyObject, rubyObject, file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getString(threadContext.runtime, 18, 32));
        return file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getCallSite(18).call(threadContext, rubyObject, rubyObject, file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getString(threadContext.runtime, 19, 64), file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.getString7(threadContext.runtime, 64));
    }
    
    public static IRubyObject class_2$RUBY$Services(final FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_2$RUBY$Services(file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Measurements(file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD, threadContext, rubyObject, block);
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
        final FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD = new FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD();
        final String string = FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.class.getClassLoader().getResource("ruby/jit/FILE_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.class").toString();
        file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_95C8C8012388B1D5C7EF45CD54B8B5873B61F5CD.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
