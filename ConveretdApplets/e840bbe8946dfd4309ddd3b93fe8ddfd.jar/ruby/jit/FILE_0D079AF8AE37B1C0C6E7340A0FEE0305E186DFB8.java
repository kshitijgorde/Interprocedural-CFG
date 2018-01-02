// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.RubyArray;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8 extends AbstractScript
{
    public FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8() {
        this.filename = "./lib//lister/measurements/poll.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffversion\uffffF\uffffold_versions\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffinclude\uffffF\uffffmap\uffffN\uffffquestions_symbols\uffffV\uffffsend\uffffN\uffffeach\uffffN\uffffquestions\uffffN\uffffreport\uffffF\uffffidentifier\uffffN\uffffeach_pair\uffffN\uffffdescriptions\uffffN\uffffdescribe\uffffF\uffffdefault_description_tag=\uffffN\uffffdefault_description_tag=\uffffV\uffffdefault_description_tag\uffffN\uffff\u0003\n\u0000\u0000\u0003\u0000\u0000\u0000\u0000\u0004\u0000\u0000\u0016\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(13, "0.1.1", this.getEncoding0());
        this.setByteList(2, "0.1.2", this.getEncoding0());
        this.setByteList(15, "Questionnaire (prend cinq minutes en moyenne)", this.getEncoding0());
        this.setByteList(1, "lister/questions_list", this.getEncoding0());
        this.setByteList(12, "0.1.0", this.getEncoding0());
        this.setByteList(3, "dev", this.getEncoding0());
        this.setByteList(0, "lister/measurement", this.getEncoding0());
        this.setByteList(18, "English-HTML", this.getEncoding0());
        this.setByteList(16, "Fran\uffc3\uffa7ais", this.getEncoding0());
        this.setByteList(14, "User survey (takes five minutes on average)", this.getEncoding0());
        this.setByteList(4, "0.0.1", this.getEncoding0());
        this.setByteList(6, "0.0.3", this.getEncoding0());
        this.setByteList(5, "0.0.2", this.getEncoding0());
        this.setByteList(8, "0.0.5", this.getEncoding0());
        this.setByteList(7, "0.0.4", this.getEncoding0());
        this.setByteList(10, "0.0.7", this.getEncoding0());
        this.setByteList(9, "0.0.6", this.getEncoding0());
        this.setByteList(20, "Fran\uffc3\uffa7ais-HTML", this.getEncoding0());
        this.setByteList(17, "<p>\n      This module asks a series of questions about your home Internet access and\n      your home network. We will use your answers to this survey for two purposes.\n      First, we want to compare your answers to what we infer with our\n      measurements. Second, your answers will complement our measurements,  for\n      properties we cannot measure directly.\n      http://cmon.lip6.fr/hnp/pages/guide</a>\n      </p>", this.getEncoding0());
        this.setByteList(11, "0.0.8", this.getEncoding0());
        this.setByteList(21, "no-description", this.getEncoding0());
        this.setByteList(19, "<p>Ce module a deux objects: \n      primo, comparer le contenu aux valeurs mesur\uffc3\uffa9es dans les\n      autres modules (par exemple, la vitesse de connexion). Secondo, il nous\n      permet d'obtenir des informations autrement difficiles \uffc3\uffa0 mesurer\n      directement.</p>", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8 file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getCallSite0().call(threadContext, rubyObject, rubyObject, file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getString0(threadContext.runtime, 32));
        file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getCallSite1().call(threadContext, rubyObject, rubyObject, file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getString1(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.module__1$RUBY$Measurements:(Lruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc_w           "Measurement"
        //    34: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getConstant2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    37: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    40: invokestatic    ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.class_2$RUBY$Poll:(Lruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject class_2$RUBY$Poll(final FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    29: ldc             "Poll"
        //    31: swap           
        //    32: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    35: dup            
        //    36: astore_2       
        //    37: aload_1        
        //    38: swap           
        //    39: aload_0        
        //    40: aload_1        
        //    41: ldc             ",0,0,-1"
        //    43: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    46: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClass:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    49: aload_1        
        //    50: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //    53: astore          locals
        //    55: aload_0        
        //    56: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getCallSite2:()Lorg/jruby/runtime/CallSite;
        //    59: aload_1        
        //    60: aload_2        
        //    61: aload_2        
        //    62: aload_0        
        //    63: aload_1        
        //    64: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    67: bipush          32
        //    69: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getString2:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    72: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: pop            
        //    76: aload_0        
        //    77: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getCallSite3:()Lorg/jruby/runtime/CallSite;
        //    80: aload_1        
        //    81: aload_2        
        //    82: aload_2        
        //    83: bipush          11
        //    85: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: dup            
        //    89: iconst_0       
        //    90: aload_0        
        //    91: aload_1        
        //    92: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    95: bipush          32
        //    97: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getString3:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   100: aastore        
        //   101: dup            
        //   102: iconst_1       
        //   103: aload_0        
        //   104: aload_1        
        //   105: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   108: bipush          32
        //   110: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getString4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   113: aastore        
        //   114: dup            
        //   115: iconst_2       
        //   116: aload_0        
        //   117: aload_1        
        //   118: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   121: bipush          32
        //   123: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getString5:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   126: aastore        
        //   127: dup            
        //   128: iconst_3       
        //   129: aload_0        
        //   130: aload_1        
        //   131: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   134: bipush          32
        //   136: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getString6:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   139: aastore        
        //   140: dup            
        //   141: iconst_4       
        //   142: aload_0        
        //   143: aload_1        
        //   144: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   147: bipush          32
        //   149: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   152: aastore        
        //   153: dup            
        //   154: iconst_5       
        //   155: aload_0        
        //   156: aload_1        
        //   157: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   160: bipush          32
        //   162: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getString8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   165: aastore        
        //   166: dup            
        //   167: bipush          6
        //   169: aload_0        
        //   170: aload_1        
        //   171: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   174: bipush          32
        //   176: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getString9:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   179: aastore        
        //   180: dup            
        //   181: bipush          7
        //   183: aload_0        
        //   184: aload_1        
        //   185: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   188: bipush          10
        //   190: bipush          32
        //   192: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   195: aastore        
        //   196: dup            
        //   197: bipush          8
        //   199: aload_0        
        //   200: aload_1        
        //   201: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   204: bipush          11
        //   206: bipush          32
        //   208: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   211: aastore        
        //   212: dup            
        //   213: bipush          9
        //   215: aload_0        
        //   216: aload_1        
        //   217: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   220: bipush          12
        //   222: bipush          32
        //   224: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   227: aastore        
        //   228: dup            
        //   229: bipush          10
        //   231: aload_0        
        //   232: aload_1        
        //   233: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   236: bipush          13
        //   238: bipush          32
        //   240: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   243: aastore        
        //   244: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   247: pop            
        //   248: aload_0        
        //   249: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getCallSite4:()Lorg/jruby/runtime/CallSite;
        //   252: aload_1        
        //   253: aload_2        
        //   254: aload_2        
        //   255: aload_0        
        //   256: aload_1        
        //   257: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   260: bipush          14
        //   262: bipush          32
        //   264: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   267: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   270: pop            
        //   271: aload_0        
        //   272: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getCallSite5:()Lorg/jruby/runtime/CallSite;
        //   275: aload_1        
        //   276: aload_2        
        //   277: aload_2        
        //   278: aload_0        
        //   279: aload_1        
        //   280: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   283: bipush          15
        //   285: bipush          32
        //   287: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   290: aload_0        
        //   291: aload_1        
        //   292: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   295: bipush          16
        //   297: bipush          64
        //   299: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   302: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   305: pop            
        //   306: aload_0        
        //   307: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getCallSite6:()Lorg/jruby/runtime/CallSite;
        //   310: aload_1        
        //   311: aload_2        
        //   312: aload_2        
        //   313: aload_0        
        //   314: aload_1        
        //   315: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   318: bipush          17
        //   320: bipush          32
        //   322: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   325: aload_0        
        //   326: aload_1        
        //   327: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   330: bipush          18
        //   332: bipush          32
        //   334: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   337: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   340: pop            
        //   341: aload_0        
        //   342: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getCallSite7:()Lorg/jruby/runtime/CallSite;
        //   345: aload_1        
        //   346: aload_2        
        //   347: aload_2        
        //   348: aload_0        
        //   349: aload_1        
        //   350: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   353: bipush          19
        //   355: bipush          64
        //   357: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   360: aload_0        
        //   361: aload_1        
        //   362: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   365: bipush          20
        //   367: bipush          64
        //   369: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   372: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   375: pop            
        //   376: aload_0        
        //   377: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getCallSite8:()Lorg/jruby/runtime/CallSite;
        //   380: aload_1        
        //   381: aload_2        
        //   382: aload_2        
        //   383: aload_0        
        //   384: aload_1        
        //   385: ldc             "QuestionsList"
        //   387: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   390: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   393: pop            
        //   394: aload_1        
        //   395: aload_2        
        //   396: aload_2        
        //   397: aload_0        
        //   398: ldc             "questions_symbols"
        //   400: ldc             "method__3$RUBY$questions_symbols"
        //   402: ldc             ",0,0,-1"
        //   404: iconst_0       
        //   405: ldc             "./lib//lister/measurements/poll.rb"
        //   407: ldc             32
        //   409: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   412: ldc_w           "NONE"
        //   415: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   418: pop            
        //   419: aload_1        
        //   420: aload_2        
        //   421: aload_2        
        //   422: aload_0        
        //   423: ldc_w           "questions"
        //   426: ldc_w           "method__4$RUBY$questions"
        //   429: ldc             ",0,0,-1"
        //   431: iconst_0       
        //   432: ldc             "./lib//lister/measurements/poll.rb"
        //   434: ldc_w           48
        //   437: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   440: ldc_w           "NONE"
        //   443: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   446: pop            
        //   447: aload_0        
        //   448: bipush          12
        //   450: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   453: aload_1        
        //   454: aload_2        
        //   455: aload_0        
        //   456: bipush          13
        //   458: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   461: aload_1        
        //   462: aload_2        
        //   463: aload_2        
        //   464: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   467: aload_1        
        //   468: aload_2        
        //   469: aload_0        
        //   470: aload_1        
        //   471: ldc_w           "block_1$RUBY$Poll,1,question,false,2,./lib//lister/measurements/poll.rb,52,false"
        //   474: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getBlockBody3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   477: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   480: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   483: aload_1        
        //   484: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   487: goto            495
        //   490: aload_1        
        //   491: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   494: athrow         
        //   495: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  55     441     4     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  55     483    490    495    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "questions_symbols", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$questions_symbols(final FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8 file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return RubyArray.newArrayNoCopy(threadContext.runtime, new IRubyObject[] { file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getSymbol0(threadContext.runtime, "user_level_question"), file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getSymbol1(threadContext.runtime, "at_home_question"), file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getSymbol2(threadContext.runtime, "connection_question"), file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getSymbol3(threadContext.runtime, "community_wifi_question"), file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getSymbol4(threadContext.runtime, "internet_question"), file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getSymbol5(threadContext.runtime, "tv_question"), file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getSymbol6(threadContext.runtime, "vod_question"), file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getSymbol7(threadContext.runtime, "phone_question"), file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getSymbol8(threadContext.runtime, "home_devices_question"), file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getSymbol9(threadContext.runtime, "tweak_question") });
    }
    
    @JRubyMethod(name = "questions", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$questions(final FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8 file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getCallSite9().callIter(threadContext, self, file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getCallSite(10).call(threadContext, self, self), RuntimeHelpers.createBlock(threadContext, self, file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getBlockBody0(threadContext, "block_0$RUBY$questions,1,sym,false,2,./lib//lister/measurements/poll.rb,49,true")));
    }
    
    public static IRubyObject block_0$RUBY$questions(final FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          11
        //    28: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_0        
        //    34: aload_1        
        //    35: ldc             "QuestionsList"
        //    37: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getConstant1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    40: aload           sym
        //    42: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     21      9     sym   Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject block_1$RUBY$Poll(final FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: bipush          14
        //    38: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_2        
        //    44: aload_0        
        //    45: bipush          15
        //    47: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    50: aload_1        
        //    51: aload_2        
        //    52: aload           locals
        //    54: aload_1        
        //    55: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    61: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    64: aload_1        
        //    65: aload_2        
        //    66: aload_0        
        //    67: aload_1        
        //    68: ldc_w           "block_2$RUBY$Poll,-1,,false,0,./lib//lister/measurements/poll.rb,53,false"
        //    71: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getBlockBody2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    74: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    77: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     46      5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_2$RUBY$Poll(final FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    10: aload           4
        //    12: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    15: aload_3        
        //    16: pop            
        //    17: pop            
        //    18: aload_0        
        //    19: bipush          16
        //    21: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    24: aload_1        
        //    25: aload_2        
        //    26: aload_0        
        //    27: bipush          17
        //    29: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    32: aload_1        
        //    33: aload_2        
        //    34: aload           locals
        //    36: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    39: aload_1        
        //    40: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    46: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload_0        
        //    52: aload_1        
        //    53: ldc_w           "block_3$RUBY$Poll,2,tag;txt,true,1,./lib//lister/measurements/poll.rb,54,true"
        //    56: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getBlockBody1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    59: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    62: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  18     48      5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_3$RUBY$Poll(final FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    57: bipush          18
        //    59: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    62: aload_1        
        //    63: aload_2        
        //    64: aload_2        
        //    65: aload           txt
        //    67: dup            
        //    68: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    73: ifne            89
        //    76: pop            
        //    77: aload_0        
        //    78: aload_1        
        //    79: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    82: bipush          21
        //    84: bipush          32
        //    86: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    89: aload           tag
        //    91: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    94: pop            
        //    95: aload_2        
        //    96: dup            
        //    97: aload_2        
        //    98: aload_0        
        //    99: bipush          19
        //   101: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   104: aload_0        
        //   105: bipush          20
        //   107: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   110: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   113: aload_0        
        //   114: bipush          21
        //   116: invokevirtual   ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   119: aload_1        
        //   120: aload_2        
        //   121: aload           5
        //   123: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   126: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   129: aload_1        
        //   130: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   133: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   136: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   139: aload_1        
        //   140: aload_2        
        //   141: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   144: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  56     89      9     tag   Lorg/jruby/runtime/builtin/IRubyObject;
        //  56     89      10    txt   Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject class_2$RUBY$Poll(final FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8 file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_2$RUBY$Poll(file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8 file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Measurements(file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8 file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8, threadContext, rubyObject, block);
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
        final FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8 file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8 = new FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8();
        final String string = FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.class.getClassLoader().getResource("ruby/jit/FILE_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.class").toString();
        file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_0D079AF8AE37B1C0C6E7340A0FEE0305E186DFB8.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
