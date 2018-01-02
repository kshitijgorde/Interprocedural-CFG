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

public class FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A extends AbstractScript
{
    public FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A() {
        this.filename = "./lib//lister/utils/hide_ip.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("===\uffffN\uffff===\uffffN\uffff===\uffffN\uffff===\uffffN\uffff===\uffffN\uffff===\uffffN\uffff\u0002\u0000\u0000\u0000\u0016\u0014\u0000\u0000\u0000\u0000\u0000\u0000\u001a\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(1, "^127\\.0\\.0\\.", this.getEncoding0());
        this.setByteList(18, "^235\\.", this.getEncoding0());
        this.setByteList(22, "multicast", this.getEncoding0());
        this.setByteList(9, "^192\\.18\\.", this.getEncoding0());
        this.setByteList(12, "^203\\.0\\.113\\.", this.getEncoding0());
        this.setByteList(4, "^172\\.", this.getEncoding0());
        this.setByteList(20, "6to4", this.getEncoding0());
        this.setByteList(7, "^192\\.88\\.99\\.", this.getEncoding0());
        this.setByteList(11, "^198\\.51\\.100", this.getEncoding0());
        this.setByteList(17, "^239\\.", this.getEncoding0());
        this.setByteList(0, "^0\\.0\\.0\\.", this.getEncoding0());
        this.setByteList(3, "^172\\.16\\.", this.getEncoding0());
        this.setByteList(19, "n/a", this.getEncoding0());
        this.setByteList(2, "^169\\.254\\.", this.getEncoding0());
        this.setByteList(16, "^233\\.", this.getEncoding0());
        this.setByteList(6, "^10\\.", this.getEncoding0());
        this.setByteList(14, "^224\\.", this.getEncoding0());
        this.setByteList(13, "^255\\.255\\.255\\.255", this.getEncoding0());
        this.setByteList(8, "^192\\.0\\.0\\.", this.getEncoding0());
        this.setByteList(24, "v6", this.getEncoding0());
        this.setByteList(15, "^232\\.", this.getEncoding0());
        this.setByteList(23, ":", this.getEncoding0());
        this.setByteList(21, "special", this.getEncoding0());
        this.setByteList(5, "^192\\.168\\.", this.getEncoding0());
        this.setByteList(25, "public", this.getEncoding0());
        this.setByteList(10, "^192\\.0\\.2\\.", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A file_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return module__0$RUBY$Lister(file_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.module__1$RUBY$HideIP:(Lruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$HideIP(final FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "HideIP"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    32: aload_0        
        //    33: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getByteList0:()Lorg/jruby/util/ByteList;
        //    36: ldc             512
        //    38: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getRegexp0:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //    41: aload_1        
        //    42: ldc             "SELF_ID"
        //    44: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: pop            
        //    48: aload_0        
        //    49: aload_1        
        //    50: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    53: aload_0        
        //    54: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getByteList1:()Lorg/jruby/util/ByteList;
        //    57: ldc             512
        //    59: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getRegexp1:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //    62: aload_1        
        //    63: ldc             "LOOPBACK"
        //    65: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    68: pop            
        //    69: aload_0        
        //    70: aload_1        
        //    71: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    74: aload_0        
        //    75: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getByteList2:()Lorg/jruby/util/ByteList;
        //    78: ldc             512
        //    80: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getRegexp2:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //    83: aload_1        
        //    84: ldc             "LINK_LOCAL"
        //    86: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: pop            
        //    90: aload_0        
        //    91: aload_1        
        //    92: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    95: aload_0        
        //    96: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getByteList3:()Lorg/jruby/util/ByteList;
        //    99: ldc             512
        //   101: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getRegexp3:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   104: aload_1        
        //   105: ldc             "PRIVATE_USE"
        //   107: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   110: pop            
        //   111: aload_0        
        //   112: aload_1        
        //   113: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   116: aload_0        
        //   117: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getByteList4:()Lorg/jruby/util/ByteList;
        //   120: ldc             512
        //   122: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getRegexp4:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   125: aload_1        
        //   126: ldc             "PRIVATE_USE_BROAD"
        //   128: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   131: pop            
        //   132: aload_0        
        //   133: aload_1        
        //   134: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   137: aload_0        
        //   138: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getByteList5:()Lorg/jruby/util/ByteList;
        //   141: ldc             512
        //   143: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getRegexp5:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   146: aload_1        
        //   147: ldc             "PRIVATE_USE_2"
        //   149: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   152: pop            
        //   153: aload_0        
        //   154: aload_1        
        //   155: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   158: aload_0        
        //   159: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getByteList6:()Lorg/jruby/util/ByteList;
        //   162: ldc             512
        //   164: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getRegexp6:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   167: aload_1        
        //   168: ldc             "PRIVATE_USE_3"
        //   170: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   173: pop            
        //   174: aload_0        
        //   175: aload_1        
        //   176: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   179: aload_0        
        //   180: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getByteList7:()Lorg/jruby/util/ByteList;
        //   183: ldc             512
        //   185: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getRegexp7:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   188: aload_1        
        //   189: ldc             "SIX_TO_FOUR"
        //   191: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   194: pop            
        //   195: aload_0        
        //   196: aload_1        
        //   197: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   200: aload_0        
        //   201: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getByteList8:()Lorg/jruby/util/ByteList;
        //   204: ldc             512
        //   206: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getRegexp8:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   209: aload_1        
        //   210: ldc             "IANA_IPV4_SPECIAL"
        //   212: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   215: pop            
        //   216: aload_0        
        //   217: aload_1        
        //   218: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   221: aload_0        
        //   222: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getByteList9:()Lorg/jruby/util/ByteList;
        //   225: ldc             512
        //   227: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getRegexp9:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   230: aload_1        
        //   231: ldc             "DEVICE_BENCHMARK"
        //   233: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   236: pop            
        //   237: aload_0        
        //   238: aload_1        
        //   239: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   242: bipush          10
        //   244: aload_0        
        //   245: bipush          10
        //   247: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getByteList:(I)Lorg/jruby/util/ByteList;
        //   250: ldc             512
        //   252: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getRegexp:(Lorg/jruby/Ruby;ILorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   255: aload_1        
        //   256: ldc             "TEST_NET"
        //   258: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   261: pop            
        //   262: aload_0        
        //   263: aload_1        
        //   264: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   267: bipush          11
        //   269: aload_0        
        //   270: bipush          11
        //   272: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getByteList:(I)Lorg/jruby/util/ByteList;
        //   275: ldc             512
        //   277: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getRegexp:(Lorg/jruby/Ruby;ILorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   280: aload_1        
        //   281: ldc             "TEST_NET_2"
        //   283: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   286: pop            
        //   287: aload_0        
        //   288: aload_1        
        //   289: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   292: bipush          12
        //   294: aload_0        
        //   295: bipush          12
        //   297: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getByteList:(I)Lorg/jruby/util/ByteList;
        //   300: ldc             512
        //   302: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getRegexp:(Lorg/jruby/Ruby;ILorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   305: aload_1        
        //   306: ldc             "TEST_NET_3"
        //   308: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   311: pop            
        //   312: aload_0        
        //   313: aload_1        
        //   314: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   317: bipush          13
        //   319: aload_0        
        //   320: bipush          13
        //   322: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getByteList:(I)Lorg/jruby/util/ByteList;
        //   325: ldc             512
        //   327: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getRegexp:(Lorg/jruby/Ruby;ILorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   330: aload_1        
        //   331: ldc             "BROADCAST"
        //   333: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   336: pop            
        //   337: aload_0        
        //   338: aload_1        
        //   339: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   342: bipush          14
        //   344: aload_0        
        //   345: bipush          14
        //   347: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getByteList:(I)Lorg/jruby/util/ByteList;
        //   350: ldc             512
        //   352: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getRegexp:(Lorg/jruby/Ruby;ILorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   355: aload_1        
        //   356: ldc             "MULTICAST_1"
        //   358: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   361: pop            
        //   362: aload_0        
        //   363: aload_1        
        //   364: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   367: bipush          15
        //   369: aload_0        
        //   370: bipush          15
        //   372: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getByteList:(I)Lorg/jruby/util/ByteList;
        //   375: ldc             512
        //   377: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getRegexp:(Lorg/jruby/Ruby;ILorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   380: aload_1        
        //   381: ldc             "MULTICAST_2"
        //   383: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   386: pop            
        //   387: aload_0        
        //   388: aload_1        
        //   389: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   392: bipush          16
        //   394: aload_0        
        //   395: bipush          16
        //   397: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getByteList:(I)Lorg/jruby/util/ByteList;
        //   400: ldc             512
        //   402: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getRegexp:(Lorg/jruby/Ruby;ILorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   405: aload_1        
        //   406: ldc             "MULTICAST_3"
        //   408: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   411: pop            
        //   412: aload_0        
        //   413: aload_1        
        //   414: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   417: bipush          17
        //   419: aload_0        
        //   420: bipush          17
        //   422: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getByteList:(I)Lorg/jruby/util/ByteList;
        //   425: ldc             512
        //   427: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getRegexp:(Lorg/jruby/Ruby;ILorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   430: aload_1        
        //   431: ldc             "MULTICAST_4"
        //   433: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   436: pop            
        //   437: aload_0        
        //   438: aload_1        
        //   439: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   442: bipush          18
        //   444: aload_0        
        //   445: bipush          18
        //   447: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getByteList:(I)Lorg/jruby/util/ByteList;
        //   450: ldc             512
        //   452: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getRegexp:(Lorg/jruby/Ruby;ILorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   455: aload_1        
        //   456: ldc             "MULTICAST_5"
        //   458: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   461: pop            
        //   462: aload_1        
        //   463: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   466: bipush          8
        //   468: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //   471: dup            
        //   472: iconst_0       
        //   473: aload_0        
        //   474: aload_1        
        //   475: ldc             "SELF_ID"
        //   477: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   480: aastore        
        //   481: dup            
        //   482: iconst_1       
        //   483: aload_0        
        //   484: aload_1        
        //   485: ldc             "LOOPBACK"
        //   487: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getConstant1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   490: aastore        
        //   491: dup            
        //   492: iconst_2       
        //   493: aload_0        
        //   494: aload_1        
        //   495: ldc             "LINK_LOCAL"
        //   497: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getConstant2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   500: aastore        
        //   501: dup            
        //   502: iconst_3       
        //   503: aload_0        
        //   504: aload_1        
        //   505: ldc             "PRIVATE_USE"
        //   507: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getConstant3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   510: aastore        
        //   511: dup            
        //   512: iconst_4       
        //   513: aload_0        
        //   514: aload_1        
        //   515: ldc             "PRIVATE_USE_BROAD"
        //   517: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getConstant4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   520: aastore        
        //   521: dup            
        //   522: iconst_5       
        //   523: aload_0        
        //   524: aload_1        
        //   525: ldc             "PRIVATE_USE_2"
        //   527: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getConstant5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   530: aastore        
        //   531: dup            
        //   532: bipush          6
        //   534: aload_0        
        //   535: aload_1        
        //   536: ldc             "PRIVATE_USE_3"
        //   538: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getConstant6:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   541: aastore        
        //   542: dup            
        //   543: bipush          7
        //   545: aload_0        
        //   546: aload_1        
        //   547: ldc             "BROADCAST"
        //   549: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getConstant7:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   552: aastore        
        //   553: invokestatic    org/jruby/RubyArray.newArrayNoCopy:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   556: aload_1        
        //   557: ldc             "NON_PERSONAL"
        //   559: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   562: pop            
        //   563: aload_1        
        //   564: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   567: aload_0        
        //   568: aload_1        
        //   569: ldc             "IANA_IPV4_SPECIAL"
        //   571: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getConstant8:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   574: aload_0        
        //   575: aload_1        
        //   576: ldc             "DEVICE_BENCHMARK"
        //   578: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getConstant9:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   581: aload_0        
        //   582: aload_1        
        //   583: ldc             "TEST_NET"
        //   585: bipush          10
        //   587: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   590: aload_0        
        //   591: aload_1        
        //   592: ldc             "TEST_NET_2"
        //   594: bipush          11
        //   596: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   599: aload_0        
        //   600: aload_1        
        //   601: ldc             "TEST_NET_3"
        //   603: bipush          12
        //   605: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   608: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   611: aload_1        
        //   612: ldc             "SPECIAL"
        //   614: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   617: pop            
        //   618: aload_1        
        //   619: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   622: aload_0        
        //   623: aload_1        
        //   624: ldc             "MULTICAST_1"
        //   626: bipush          13
        //   628: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   631: aload_0        
        //   632: aload_1        
        //   633: ldc             "MULTICAST_2"
        //   635: bipush          14
        //   637: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   640: aload_0        
        //   641: aload_1        
        //   642: ldc             "MULTICAST_3"
        //   644: bipush          15
        //   646: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   649: aload_0        
        //   650: aload_1        
        //   651: ldc             "MULTICAST_4"
        //   653: bipush          16
        //   655: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   658: aload_0        
        //   659: aload_1        
        //   660: ldc             "MULTICAST_5"
        //   662: bipush          17
        //   664: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   667: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   670: aload_1        
        //   671: ldc             "MULTICAST"
        //   673: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   676: pop            
        //   677: aload_1        
        //   678: aload_2        
        //   679: aload_0        
        //   680: ldc_w           "hide_ip"
        //   683: ldc_w           "method__2$RUBY$hide_ip"
        //   686: ldc_w           "ip,1,0,-1"
        //   689: iconst_1       
        //   690: ldc             "./lib//lister/utils/hide_ip.rb"
        //   692: ldc_w           41
        //   695: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   698: ldc_w           "qip"
        //   701: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   704: aload_1        
        //   705: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   708: goto            716
        //   711: aload_1        
        //   712: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   715: athrow         
        //   716: aload_1        
        //   717: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   720: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     708    711    716    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "hide_ip", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__2$RUBY$hide_ip(final FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    17: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    20: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    23: aload_1        
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //    27: astore          9
        //    29: aload_0        
        //    30: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getCallSite0:()Lorg/jruby/runtime/CallSite;
        //    33: aload_1        
        //    34: aload_2        
        //    35: aload           9
        //    37: aload_1        
        //    38: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //    44: ifeq            62
        //    47: aload_0        
        //    48: aload_1        
        //    49: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    52: bipush          19
        //    54: bipush          32
        //    56: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    59: goto            288
        //    62: aload_0        
        //    63: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getCallSite1:()Lorg/jruby/runtime/CallSite;
        //    66: aload_1        
        //    67: aload_2        
        //    68: aload           9
        //    70: aload_0        
        //    71: aload_1        
        //    72: ldc             "NON_PERSONAL"
        //    74: bipush          18
        //    76: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.splatValue:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //    82: invokestatic    org/jruby/ast/util/ArgsUtil.convertToJavaArray:(Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //    85: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //    88: ifeq            103
        //    91: aload           locals
        //    93: aload_1        
        //    94: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    97: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   100: goto            288
        //   103: aload_0        
        //   104: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getCallSite2:()Lorg/jruby/runtime/CallSite;
        //   107: aload_1        
        //   108: aload_2        
        //   109: aload           9
        //   111: aload_0        
        //   112: aload_1        
        //   113: ldc             "SIX_TO_FOUR"
        //   115: bipush          19
        //   117: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   120: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   123: ifeq            141
        //   126: aload_0        
        //   127: aload_1        
        //   128: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   131: bipush          20
        //   133: bipush          32
        //   135: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   138: goto            288
        //   141: aload_0        
        //   142: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getCallSite3:()Lorg/jruby/runtime/CallSite;
        //   145: aload_1        
        //   146: aload_2        
        //   147: aload           9
        //   149: aload_0        
        //   150: aload_1        
        //   151: ldc             "SPECIAL"
        //   153: bipush          20
        //   155: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   158: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.splatValue:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   161: invokestatic    org/jruby/ast/util/ArgsUtil.convertToJavaArray:(Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   164: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   167: ifeq            185
        //   170: aload_0        
        //   171: aload_1        
        //   172: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   175: bipush          21
        //   177: bipush          32
        //   179: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   182: goto            288
        //   185: aload_0        
        //   186: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getCallSite4:()Lorg/jruby/runtime/CallSite;
        //   189: aload_1        
        //   190: aload_2        
        //   191: aload           9
        //   193: aload_0        
        //   194: aload_1        
        //   195: ldc             "MULTICAST"
        //   197: bipush          21
        //   199: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   202: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.splatValue:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   205: invokestatic    org/jruby/ast/util/ArgsUtil.convertToJavaArray:(Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   208: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   211: ifeq            229
        //   214: aload_0        
        //   215: aload_1        
        //   216: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   219: bipush          22
        //   221: bipush          32
        //   223: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   226: goto            288
        //   229: aload_0        
        //   230: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getCallSite5:()Lorg/jruby/runtime/CallSite;
        //   233: aload_1        
        //   234: aload_2        
        //   235: aload           9
        //   237: aload_0        
        //   238: aload_1        
        //   239: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   242: bipush          19
        //   244: aload_0        
        //   245: bipush          23
        //   247: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getByteList:(I)Lorg/jruby/util/ByteList;
        //   250: ldc             512
        //   252: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getRegexp:(Lorg/jruby/Ruby;ILorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   255: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   258: ifeq            276
        //   261: aload_0        
        //   262: aload_1        
        //   263: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   266: bipush          24
        //   268: bipush          32
        //   270: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   273: goto            288
        //   276: aload_0        
        //   277: aload_1        
        //   278: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   281: bipush          25
        //   283: bipush          32
        //   285: invokevirtual   ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   288: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     275     5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject module__1$RUBY$HideIP(final FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A file_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$HideIP(file_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A file_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A, threadContext, rubyObject, block);
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
        final FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A file_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A = new FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A();
        final String string = FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.class.getClassLoader().getResource("ruby/jit/FILE_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.class").toString();
        file_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_8E82CFB21EF78B6EC13D2318A54ED88BDCDECB5A.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
