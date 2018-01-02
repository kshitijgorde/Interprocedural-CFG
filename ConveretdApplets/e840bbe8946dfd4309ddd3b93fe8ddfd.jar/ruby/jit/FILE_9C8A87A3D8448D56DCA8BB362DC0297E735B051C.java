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

public class FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C extends AbstractScript
{
    public FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C() {
        this.filename = "./lib//lister/measurements/performance.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffversion\uffffF\uffffold_versions\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffreport\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffreport\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffreport\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffreport\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffreport\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffff\u0003\u0005\u0000\u0000\u0001\u0000\u0000\u0000\u0000\u0005\u0000\u0000\u001e\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(9, "500k", this.getEncoding0());
        this.setByteList(2, "dev", this.getEncoding0());
        this.setByteList(26, "The underlying Traceroute command executed (if applicable)", this.getEncoding0());
        this.setByteList(0, "lister/measurement", this.getEncoding0());
        this.setByteList(16, "\n        <p>This module does two things: HTTP download measurements, and\nTraceroute.</p>\n\n        <p>HTTP download is basically the speed at which you can download to\nour server. We increase the file size iteratively and we limit ourselves to\ndownloads not longer than 10 seconds. We would like to compare what bandwidth\nwe measure to what your ISP service plan.</p>\n\n        <p>Traceroute is a measurement tool that maps the Internet path between\nyour computer and another computer on the Internet. Traceroute collects the IPs\nand the RTTs of the individual routers along this path. We are interested in\nhow you are connected to our server, as well as how you are connected to your\nDNS (which may explain long delays when connections start).  </p>\n    ", this.getEncoding0());
        this.setByteList(21, "La liste des mesures de t\uffc3\uffa9l\uffc3\uffa9chargement HTTP (statut, taille et temps pris)", this.getEncoding0());
        this.setByteList(28, "The routes to the DNS servers and per-hop RTT (as a list of arrays of RTTs)", this.getEncoding0());
        this.setByteList(24, "The version of Traceroute", this.getEncoding0());
        this.setByteList(10, "1M", this.getEncoding0());
        this.setByteList(27, "La commande traceroute effectivement utilis\uffc3\uffa9e (si applicable)", this.getEncoding0());
        this.setByteList(25, "La version de traceroute install\uffc3\uffa9e", this.getEncoding0());
        this.setByteList(12, "10M", this.getEncoding0());
        this.setByteList(13, "Path performance to our server and your DNS servers", this.getEncoding0());
        this.setByteList(11, "5M", this.getEncoding0());
        this.setByteList(18, "\n        <p>Ce module fait deux choses: des t\uffc3\uffa9l\uffc3\uffa9chargements HTTP et Traceroute</p>\n\n        <p>Le t\uffc3\uffa9l\uffc3\uffa9chargement HTTP correspond simplement \uffc3\uffa0 la vitesse \uffc3\uffa0 laquelle\nvous pouvez t\uffc3\uffa9l\uffc3\uffa9charger sur notre serveur. Nous essayons des tailles  de\nfichier croissantes. Si le t\uffc3\uffa9l\uffc3\uffa9chargement prend plus de 10 secondes, alors nous\narr\uffc3\uffaatons d'augmenter la taille du fichier. Nous aimerions comparer ces vitesses\n\uffc3\uffa0 celles vendues par les fournisseurs d'acc\uffc3\uffa8s, c'est pourquoi nous demandons la\n        vitesse dans le questionnaire.</p>\n\n        <p>Traceroute est un outil de mesure qui d\uffc3\uffa9couvre les liens Internet\nentre votre ordinateur et une autre machine sur Internet. Traceroute collecte\nles IPs et les RTTs de chaque routeur sur le chemin Internet. Nous aimerions\n        savoir comment vous \uffc3\uffaates connect\uffc3\uffa9s \uffc3\uffa0 notre serveur ainsi que vos\n        serveurs DNS (ce qui pourrait expliquer des d\uffc3\uffa9lais au d\uffc3\uffa9but des\n                      connexions).  </p>\n    ", this.getEncoding0());
        this.setByteList(29, "Les routes vers les serveurs DNS", this.getEncoding0());
        this.setByteList(1, "0.1.0", this.getEncoding0());
        this.setByteList(20, "The list of HTTP downloads measurements (status, size, and time taken).", this.getEncoding0());
        this.setByteList(17, "English-HTML", this.getEncoding0());
        this.setByteList(22, "The route to our server and per-hop RTT.", this.getEncoding0());
        this.setByteList(14, "Performance vers notre serveur et vos serveurs DNS", this.getEncoding0());
        this.setByteList(4, "1k", this.getEncoding0());
        this.setByteList(15, "Fran\uffc3\uffa7ais", this.getEncoding0());
        this.setByteList(3, "0.0.1", this.getEncoding0());
        this.setByteList(7, "50k", this.getEncoding0());
        this.setByteList(8, "100k", this.getEncoding0());
        this.setByteList(5, "5k", this.getEncoding0());
        this.setByteList(23, "La route vers notre serveur et la RTT par saut", this.getEncoding0());
        this.setByteList(6, "10k", this.getEncoding0());
        this.setByteList(19, "Fran\uffc3\uffa7ais-HTML", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getCallSite0().call(threadContext, rubyObject, rubyObject, file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString0(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.module__1$RUBY$Measurements:(Lruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc_w           "Measurement"
        //    34: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    37: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    40: invokestatic    ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.class_2$RUBY$Performance:(Lruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject class_2$RUBY$Performance(final FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    29: ldc             "Performance"
        //    31: swap           
        //    32: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    35: dup            
        //    36: astore_2       
        //    37: aload_1        
        //    38: swap           
        //    39: aload_0        
        //    40: aload_1        
        //    41: ldc             ",0,0,-1"
        //    43: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    46: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClass:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    49: aload_1        
        //    50: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //    53: astore          locals
        //    55: aload_0        
        //    56: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getCallSite1:()Lorg/jruby/runtime/CallSite;
        //    59: aload_1        
        //    60: aload_2        
        //    61: aload_2        
        //    62: aload_0        
        //    63: aload_1        
        //    64: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    67: bipush          32
        //    69: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    72: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: pop            
        //    76: aload_0        
        //    77: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getCallSite2:()Lorg/jruby/runtime/CallSite;
        //    80: aload_1        
        //    81: aload_2        
        //    82: aload_2        
        //    83: aload_0        
        //    84: aload_1        
        //    85: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    88: bipush          32
        //    90: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString2:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    93: aload_0        
        //    94: aload_1        
        //    95: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    98: bipush          32
        //   100: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString3:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   103: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   106: pop            
        //   107: aload_1        
        //   108: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   111: bipush          9
        //   113: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //   116: dup            
        //   117: iconst_0       
        //   118: aload_0        
        //   119: aload_1        
        //   120: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   123: bipush          32
        //   125: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   128: aastore        
        //   129: dup            
        //   130: iconst_1       
        //   131: aload_0        
        //   132: aload_1        
        //   133: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   136: bipush          32
        //   138: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString5:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   141: aastore        
        //   142: dup            
        //   143: iconst_2       
        //   144: aload_0        
        //   145: aload_1        
        //   146: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   149: bipush          32
        //   151: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString6:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   154: aastore        
        //   155: dup            
        //   156: iconst_3       
        //   157: aload_0        
        //   158: aload_1        
        //   159: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   162: bipush          32
        //   164: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   167: aastore        
        //   168: dup            
        //   169: iconst_4       
        //   170: aload_0        
        //   171: aload_1        
        //   172: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   175: bipush          32
        //   177: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   180: aastore        
        //   181: dup            
        //   182: iconst_5       
        //   183: aload_0        
        //   184: aload_1        
        //   185: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   188: bipush          32
        //   190: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString9:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   193: aastore        
        //   194: dup            
        //   195: bipush          6
        //   197: aload_0        
        //   198: aload_1        
        //   199: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   202: bipush          10
        //   204: bipush          32
        //   206: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   209: aastore        
        //   210: dup            
        //   211: bipush          7
        //   213: aload_0        
        //   214: aload_1        
        //   215: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   218: bipush          11
        //   220: bipush          32
        //   222: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   225: aastore        
        //   226: dup            
        //   227: bipush          8
        //   229: aload_0        
        //   230: aload_1        
        //   231: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   234: bipush          12
        //   236: bipush          32
        //   238: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   241: aastore        
        //   242: invokestatic    org/jruby/RubyArray.newArrayNoCopy:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   245: aload_1        
        //   246: ldc             "SIZES"
        //   248: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   251: pop            
        //   252: aload_0        
        //   253: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getCallSite3:()Lorg/jruby/runtime/CallSite;
        //   256: aload_1        
        //   257: aload_2        
        //   258: aload_2        
        //   259: aload_0        
        //   260: aload_1        
        //   261: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   264: bipush          13
        //   266: bipush          32
        //   268: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   271: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   274: pop            
        //   275: aload_0        
        //   276: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getCallSite4:()Lorg/jruby/runtime/CallSite;
        //   279: aload_1        
        //   280: aload_2        
        //   281: aload_2        
        //   282: aload_0        
        //   283: aload_1        
        //   284: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   287: bipush          14
        //   289: bipush          32
        //   291: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   294: aload_0        
        //   295: aload_1        
        //   296: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   299: bipush          15
        //   301: bipush          64
        //   303: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   306: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   309: pop            
        //   310: aload_0        
        //   311: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getCallSite5:()Lorg/jruby/runtime/CallSite;
        //   314: aload_1        
        //   315: aload_2        
        //   316: aload_2        
        //   317: aload_0        
        //   318: aload_1        
        //   319: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   322: bipush          16
        //   324: bipush          32
        //   326: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   329: aload_0        
        //   330: aload_1        
        //   331: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   334: bipush          17
        //   336: bipush          32
        //   338: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   341: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   344: pop            
        //   345: aload_0        
        //   346: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getCallSite6:()Lorg/jruby/runtime/CallSite;
        //   349: aload_1        
        //   350: aload_2        
        //   351: aload_2        
        //   352: aload_0        
        //   353: aload_1        
        //   354: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   357: bipush          18
        //   359: bipush          64
        //   361: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   364: aload_0        
        //   365: aload_1        
        //   366: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   369: bipush          19
        //   371: bipush          64
        //   373: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   376: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   379: pop            
        //   380: aload_0        
        //   381: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getCallSite7:()Lorg/jruby/runtime/CallSite;
        //   384: aload_1        
        //   385: aload_2        
        //   386: aload_2        
        //   387: aload_0        
        //   388: aload_1        
        //   389: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   392: ldc             "http_values"
        //   394: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   397: aload_1        
        //   398: aload_2        
        //   399: aload_0        
        //   400: aload_1        
        //   401: ldc             "block_0$RUBY$Performance,-1,,false,0,./lib//lister/measurements/performance.rb,50,true"
        //   403: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   406: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   409: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   412: pop            
        //   413: aload_0        
        //   414: bipush          10
        //   416: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   419: aload_1        
        //   420: aload_2        
        //   421: aload_2        
        //   422: aload_0        
        //   423: aload_1        
        //   424: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   427: ldc             "route"
        //   429: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   432: aload_1        
        //   433: aload_2        
        //   434: aload_0        
        //   435: aload_1        
        //   436: ldc             "block_1$RUBY$Performance,-1,,false,0,./lib//lister/measurements/performance.rb,56,true"
        //   438: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getBlockBody1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   441: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   444: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   447: pop            
        //   448: aload_0        
        //   449: bipush          13
        //   451: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   454: aload_1        
        //   455: aload_2        
        //   456: aload_2        
        //   457: aload_0        
        //   458: aload_1        
        //   459: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   462: ldc             "traceroute_ver"
        //   464: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   467: aload_1        
        //   468: aload_2        
        //   469: aload_0        
        //   470: aload_1        
        //   471: ldc             "block_2$RUBY$Performance,-1,,false,0,./lib//lister/measurements/performance.rb,62,true"
        //   473: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getBlockBody2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   476: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   479: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   482: pop            
        //   483: aload_0        
        //   484: bipush          16
        //   486: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   489: aload_1        
        //   490: aload_2        
        //   491: aload_2        
        //   492: aload_0        
        //   493: aload_1        
        //   494: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   497: ldc             "traceroute_cmd"
        //   499: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   502: aload_1        
        //   503: aload_2        
        //   504: aload_0        
        //   505: aload_1        
        //   506: ldc             "block_3$RUBY$Performance,-1,,false,0,./lib//lister/measurements/performance.rb,68,true"
        //   508: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getBlockBody3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   511: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   514: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   517: pop            
        //   518: aload_0        
        //   519: bipush          19
        //   521: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   524: aload_1        
        //   525: aload_2        
        //   526: aload_2        
        //   527: aload_0        
        //   528: aload_1        
        //   529: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   532: ldc             "dns_routes"
        //   534: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   537: aload_1        
        //   538: aload_2        
        //   539: aload_0        
        //   540: aload_1        
        //   541: ldc             "block_4$RUBY$Performance,-1,,false,0,./lib//lister/measurements/performance.rb,74,true"
        //   543: invokevirtual   ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getBlockBody4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   546: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   549: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   552: aload_1        
        //   553: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   556: goto            564
        //   559: aload_1        
        //   560: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   563: athrow         
        //   564: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  55     510     4     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  55     552    559    564    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_0$RUBY$Performance(final FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        threadContext.getCurrentScope();
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getCallSite8().call(threadContext, rubyObject, rubyObject, file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString(threadContext.runtime, 20, 32));
        return file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getCallSite9().call(threadContext, rubyObject, rubyObject, file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString(threadContext.runtime, 21, 64), file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString(threadContext.runtime, 15, 64));
    }
    
    public static IRubyObject block_1$RUBY$Performance(final FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        threadContext.getCurrentScope();
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getCallSite(11).call(threadContext, rubyObject, rubyObject, file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString(threadContext.runtime, 22, 32));
        return file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getCallSite(12).call(threadContext, rubyObject, rubyObject, file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString(threadContext.runtime, 23, 32), file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString(threadContext.runtime, 15, 64));
    }
    
    public static IRubyObject block_2$RUBY$Performance(final FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        threadContext.getCurrentScope();
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getCallSite(14).call(threadContext, rubyObject, rubyObject, file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString(threadContext.runtime, 24, 32));
        return file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getCallSite(15).call(threadContext, rubyObject, rubyObject, file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString(threadContext.runtime, 25, 64), file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString(threadContext.runtime, 15, 64));
    }
    
    public static IRubyObject block_3$RUBY$Performance(final FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        threadContext.getCurrentScope();
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getCallSite(17).call(threadContext, rubyObject, rubyObject, file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString(threadContext.runtime, 26, 32));
        return file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getCallSite(18).call(threadContext, rubyObject, rubyObject, file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString(threadContext.runtime, 27, 64), file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString(threadContext.runtime, 15, 64));
    }
    
    public static IRubyObject block_4$RUBY$Performance(final FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        threadContext.getCurrentScope();
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getCallSite(20).call(threadContext, rubyObject, rubyObject, file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString(threadContext.runtime, 28, 32));
        return file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getCallSite(21).call(threadContext, rubyObject, rubyObject, file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString(threadContext.runtime, 29, 32), file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.getString(threadContext.runtime, 15, 64));
    }
    
    public static IRubyObject class_2$RUBY$Performance(final FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_2$RUBY$Performance(file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Measurements(file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C, threadContext, rubyObject, block);
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
        final FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C = new FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C();
        final String string = FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.class.getClassLoader().getResource("ruby/jit/FILE_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.class").toString();
        file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_9C8A87A3D8448D56DCA8BB362DC0297E735B051C.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
