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

public class FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423 extends AbstractScript
{
    public FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423() {
        this.filename = "./lib//lister/measurements/upnp.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffversion\uffffF\uffffold_versions\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffreport\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffreport\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffreport\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffreport\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffreport\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffreport\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffattr_accessor\uffffF\uffff\u0003\u0006\u0000\u0000\u0001\u0000\u0000\u0000\u0000\u0006\u0000\u0000\u0018\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(1, "0.1.1", this.getEncoding0());
        this.setByteList(18, "Gets the access link rate", this.getEncoding0());
        this.setByteList(4, "0.1.0", this.getEncoding0());
        this.setByteList(13, "Une valeur pour dire si oui ou non il y a une gateway (ou FAI-box)", this.getEncoding0());
        this.setByteList(2, "dev", this.getEncoding0());
        this.setByteList(0, "lister/measurement", this.getEncoding0());
        this.setByteList(8, "<p>\n      This module profiles your home gateway. The home gateway is the box that\nconnects your home to the Internet (your DSL or cable modem). We detect the\ngateway using <a href=\"http://www.upnp.org/\">UPnP</a>. If UPnP is implemented and enabled, we collect the gateway\ntype as well as its manufacturer, the connection type, the names of services it\nprovides. Sometimes home gateways do not implement UPnP correctly. This module\nwill generate traffic to detect whether UPnP is implemented correctly by\nreporting this traffic.\n</p>", this.getEncoding0());
        this.setByteList(21, "La pr\uffc3\uffa9cision du compteur r\uffc3\uffa9seau de la gateway: le nombre de paquets/octets re\uffc3\uffa7us/envoy\uffc3\uffa9s, par la machine et vus par la gateway", this.getEncoding0());
        this.setByteList(14, "Gets the gateway model", this.getEncoding0());
        this.setByteList(6, "Configuration de votre gateway", this.getEncoding0());
        this.setByteList(9, "English-HTML", this.getEncoding0());
        this.setByteList(12, "A flag to tell if there is a gateway", this.getEncoding0());
        this.setByteList(20, "Gives the accuracy of the gateway: the number of sent/received bytes/packets, on the local machine and as seen from the gateway", this.getEncoding0());
        this.setByteList(17, "Le type de r\uffc3\uffa9seau d'acc\uffc3\uffa8s vu par la gateway", this.getEncoding0());
        this.setByteList(7, "Fran\uffc3\uffa7ais", this.getEncoding0());
        this.setByteList(15, "Le mod\uffc3\uffa8le de la gateway", this.getEncoding0());
        this.setByteList(3, "0.0.1", this.getEncoding0());
        this.setByteList(23, "Le fournisseur d'acc\uffc3\uffa8s vu par la gateway", this.getEncoding0());
        this.setByteList(22, "The ISP as told by the end gateway", this.getEncoding0());
        this.setByteList(16, "Gets the access link type", this.getEncoding0());
        this.setByteList(11, "Fran\uffc3\uffa7ais-HTML", this.getEncoding0());
        this.setByteList(19, "La vitesse du lien d'acc\uffc3\uffa8s", this.getEncoding0());
        this.setByteList(5, "Configuration of your home gateway", this.getEncoding0());
        this.setByteList(10, "<p>Ce module d\uffc3\uffa9tecte si vous avez une gateway et son type. \nNous utilisons <a href=\"http://www.upnp.org/\">UPnP</a> pour d\uffc3\uffa9tecter la gateway.  Ce module g\uffc3\uffa9n\uffc3\uffa8re un peu de traffic et v\uffc3\uffa9rifie que les requ\uffc3\uffaates UPnP\n      rapportent ce traffic correctement.  </p>", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423 file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getCallSite0().call(threadContext, rubyObject, rubyObject, file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString0(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.module__1$RUBY$Measurements:(Lruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc_w           "Measurement"
        //    34: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    37: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    40: invokestatic    ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.class_2$RUBY$UPnP:(Lruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject class_2$RUBY$UPnP(final FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    29: ldc             "UPnP"
        //    31: swap           
        //    32: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    35: dup            
        //    36: astore_2       
        //    37: aload_1        
        //    38: swap           
        //    39: aload_0        
        //    40: aload_1        
        //    41: ldc             ",0,0,-1"
        //    43: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    46: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClass:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    49: aload_1        
        //    50: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //    53: astore          locals
        //    55: aload_0        
        //    56: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getCallSite1:()Lorg/jruby/runtime/CallSite;
        //    59: aload_1        
        //    60: aload_2        
        //    61: aload_2        
        //    62: aload_0        
        //    63: aload_1        
        //    64: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    67: bipush          32
        //    69: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    72: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: pop            
        //    76: aload_0        
        //    77: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getCallSite2:()Lorg/jruby/runtime/CallSite;
        //    80: aload_1        
        //    81: aload_2        
        //    82: aload_2        
        //    83: aload_0        
        //    84: aload_1        
        //    85: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    88: bipush          32
        //    90: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString2:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    93: aload_0        
        //    94: aload_1        
        //    95: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    98: bipush          32
        //   100: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString3:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   103: aload_0        
        //   104: aload_1        
        //   105: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   108: bipush          32
        //   110: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   113: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   116: pop            
        //   117: aload_0        
        //   118: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getCallSite3:()Lorg/jruby/runtime/CallSite;
        //   121: aload_1        
        //   122: aload_2        
        //   123: aload_2        
        //   124: aload_0        
        //   125: aload_1        
        //   126: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   129: bipush          32
        //   131: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString5:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   134: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   137: pop            
        //   138: aload_0        
        //   139: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getCallSite4:()Lorg/jruby/runtime/CallSite;
        //   142: aload_1        
        //   143: aload_2        
        //   144: aload_2        
        //   145: aload_0        
        //   146: aload_1        
        //   147: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   150: bipush          32
        //   152: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString6:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   155: aload_0        
        //   156: aload_1        
        //   157: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   160: bipush          64
        //   162: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   165: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   168: pop            
        //   169: aload_0        
        //   170: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getCallSite5:()Lorg/jruby/runtime/CallSite;
        //   173: aload_1        
        //   174: aload_2        
        //   175: aload_2        
        //   176: aload_0        
        //   177: aload_1        
        //   178: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   181: bipush          32
        //   183: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   186: aload_0        
        //   187: aload_1        
        //   188: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   191: bipush          32
        //   193: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString9:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   196: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   199: pop            
        //   200: aload_0        
        //   201: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getCallSite6:()Lorg/jruby/runtime/CallSite;
        //   204: aload_1        
        //   205: aload_2        
        //   206: aload_2        
        //   207: aload_0        
        //   208: aload_1        
        //   209: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   212: bipush          10
        //   214: bipush          64
        //   216: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   219: aload_0        
        //   220: aload_1        
        //   221: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   224: bipush          11
        //   226: bipush          64
        //   228: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   231: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   234: pop            
        //   235: aload_0        
        //   236: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getCallSite7:()Lorg/jruby/runtime/CallSite;
        //   239: aload_1        
        //   240: aload_2        
        //   241: aload_2        
        //   242: aload_0        
        //   243: aload_1        
        //   244: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   247: ldc             "has_gateway"
        //   249: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   252: aload_1        
        //   253: aload_2        
        //   254: aload_0        
        //   255: aload_1        
        //   256: ldc             "block_0$RUBY$UPnP,-1,,false,0,./lib//lister/measurements/upnp.rb,28,true"
        //   258: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   261: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   264: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   267: pop            
        //   268: aload_0        
        //   269: bipush          10
        //   271: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   274: aload_1        
        //   275: aload_2        
        //   276: aload_2        
        //   277: aload_0        
        //   278: aload_1        
        //   279: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   282: ldc             "gateway_model"
        //   284: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   287: aload_1        
        //   288: aload_2        
        //   289: aload_0        
        //   290: aload_1        
        //   291: ldc             "block_1$RUBY$UPnP,-1,,false,0,./lib//lister/measurements/upnp.rb,34,true"
        //   293: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getBlockBody1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   296: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   299: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   302: pop            
        //   303: aload_0        
        //   304: bipush          13
        //   306: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   309: aload_1        
        //   310: aload_2        
        //   311: aload_2        
        //   312: aload_0        
        //   313: aload_1        
        //   314: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   317: ldc             "line_type"
        //   319: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   322: aload_1        
        //   323: aload_2        
        //   324: aload_0        
        //   325: aload_1        
        //   326: ldc             "block_2$RUBY$UPnP,-1,,false,0,./lib//lister/measurements/upnp.rb,40,true"
        //   328: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getBlockBody2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   331: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   334: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   337: pop            
        //   338: aload_0        
        //   339: bipush          16
        //   341: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   344: aload_1        
        //   345: aload_2        
        //   346: aload_2        
        //   347: aload_0        
        //   348: aload_1        
        //   349: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   352: ldc             "sync_rates"
        //   354: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   357: aload_1        
        //   358: aload_2        
        //   359: aload_0        
        //   360: aload_1        
        //   361: ldc             "block_3$RUBY$UPnP,-1,,false,0,./lib//lister/measurements/upnp.rb,46,true"
        //   363: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getBlockBody3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   366: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   369: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   372: pop            
        //   373: aload_0        
        //   374: bipush          19
        //   376: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   379: aload_1        
        //   380: aload_2        
        //   381: aload_2        
        //   382: aload_0        
        //   383: aload_1        
        //   384: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   387: ldc             "counter_accuracy"
        //   389: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   392: aload_1        
        //   393: aload_2        
        //   394: aload_0        
        //   395: aload_1        
        //   396: ldc             "block_4$RUBY$UPnP,-1,,false,0,./lib//lister/measurements/upnp.rb,52,true"
        //   398: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getBlockBody4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   401: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   404: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   407: pop            
        //   408: aload_0        
        //   409: bipush          22
        //   411: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   414: aload_1        
        //   415: aload_2        
        //   416: aload_2        
        //   417: aload_0        
        //   418: aload_1        
        //   419: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   422: ldc             "isp"
        //   424: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   427: aload_1        
        //   428: aload_2        
        //   429: aload_0        
        //   430: aload_1        
        //   431: ldc             "block_5$RUBY$UPnP,-1,,false,0,./lib//lister/measurements/upnp.rb,58,true"
        //   433: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getBlockBody5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   436: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   439: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   442: pop            
        //   443: aload_0        
        //   444: bipush          25
        //   446: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   449: aload_1        
        //   450: aload_2        
        //   451: aload_2        
        //   452: bipush          6
        //   454: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //   457: dup            
        //   458: iconst_0       
        //   459: aload_0        
        //   460: aload_1        
        //   461: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   464: ldc             "has_gateway"
        //   466: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   469: aastore        
        //   470: dup            
        //   471: iconst_1       
        //   472: aload_0        
        //   473: aload_1        
        //   474: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   477: ldc             "gateway_model"
        //   479: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   482: aastore        
        //   483: dup            
        //   484: iconst_2       
        //   485: aload_0        
        //   486: aload_1        
        //   487: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   490: ldc             "line_type"
        //   492: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   495: aastore        
        //   496: dup            
        //   497: iconst_3       
        //   498: aload_0        
        //   499: aload_1        
        //   500: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   503: ldc             "sync_rates"
        //   505: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   508: aastore        
        //   509: dup            
        //   510: iconst_4       
        //   511: aload_0        
        //   512: aload_1        
        //   513: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   516: ldc             "counter_accuracy"
        //   518: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   521: aastore        
        //   522: dup            
        //   523: iconst_5       
        //   524: aload_0        
        //   525: aload_1        
        //   526: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   529: ldc             "isp"
        //   531: invokevirtual   ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   534: aastore        
        //   535: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   538: aload_1        
        //   539: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   542: goto            550
        //   545: aload_1        
        //   546: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   549: athrow         
        //   550: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  55     496     4     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  55     538    545    550    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_0$RUBY$UPnP(final FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423 file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        threadContext.getCurrentScope();
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getCallSite8().call(threadContext, rubyObject, rubyObject, file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString(threadContext.runtime, 12, 32));
        return file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getCallSite9().call(threadContext, rubyObject, rubyObject, file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString(threadContext.runtime, 13, 32), file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString7(threadContext.runtime, 64));
    }
    
    public static IRubyObject block_1$RUBY$UPnP(final FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423 file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        threadContext.getCurrentScope();
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getCallSite(11).call(threadContext, rubyObject, rubyObject, file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString(threadContext.runtime, 14, 32));
        return file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getCallSite(12).call(threadContext, rubyObject, rubyObject, file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString(threadContext.runtime, 15, 64), file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString7(threadContext.runtime, 64));
    }
    
    public static IRubyObject block_2$RUBY$UPnP(final FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423 file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        threadContext.getCurrentScope();
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getCallSite(14).call(threadContext, rubyObject, rubyObject, file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString(threadContext.runtime, 16, 32));
        return file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getCallSite(15).call(threadContext, rubyObject, rubyObject, file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString(threadContext.runtime, 17, 64), file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString7(threadContext.runtime, 64));
    }
    
    public static IRubyObject block_3$RUBY$UPnP(final FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423 file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        threadContext.getCurrentScope();
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getCallSite(17).call(threadContext, rubyObject, rubyObject, file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString(threadContext.runtime, 18, 32));
        return file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getCallSite(18).call(threadContext, rubyObject, rubyObject, file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString(threadContext.runtime, 19, 64), file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString7(threadContext.runtime, 64));
    }
    
    public static IRubyObject block_4$RUBY$UPnP(final FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423 file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        threadContext.getCurrentScope();
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getCallSite(20).call(threadContext, rubyObject, rubyObject, file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString(threadContext.runtime, 20, 32));
        return file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getCallSite(21).call(threadContext, rubyObject, rubyObject, file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString(threadContext.runtime, 21, 64), file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString7(threadContext.runtime, 64));
    }
    
    public static IRubyObject block_5$RUBY$UPnP(final FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423 file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        threadContext.getCurrentScope();
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getCallSite(23).call(threadContext, rubyObject, rubyObject, file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString(threadContext.runtime, 22, 32));
        return file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getCallSite(24).call(threadContext, rubyObject, rubyObject, file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString(threadContext.runtime, 23, 64), file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.getString7(threadContext.runtime, 64));
    }
    
    public static IRubyObject class_2$RUBY$UPnP(final FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423 file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_2$RUBY$UPnP(file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423 file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Measurements(file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423 file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423, threadContext, rubyObject, block);
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
        final FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423 file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423 = new FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423();
        final String string = FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.class.getClassLoader().getResource("ruby/jit/FILE_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.class").toString();
        file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_A852A925CA223A5A9E7E4F7908C10A69FC6E2423.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
