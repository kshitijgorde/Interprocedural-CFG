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

public class FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3 extends AbstractScript
{
    public FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3() {
        this.filename = "./lib//lister/measurements/ask_email.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffversion\uffffF\uffffold_versions\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffffreport\uffffF\uffffdescribe\uffffF\uffffdescribe\uffffF\uffff\u0003\u0001\u0000\u0000\u0001\u0000\u0000\u0000\u0000\u0001\u0000\u0000\r\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(12, "L'addresse e-mail de l'utilisateur (non enregistr\uffc3\uffa9e)", this.getEncoding0());
        this.setByteList(1, "0.1.0", this.getEncoding0());
        this.setByteList(9, "<p>\n      Ce module s\uffc3\uffa9par\uffc3\uffa9 vous demande votre adresse e-mail.\n      Nous ne stockons pas votre adresse mais nous l'utilisons pour vous envoyer un lien vers votre page de rapports de mesure \uffc3\uffa0 la vol\uffc3\uffa9e.\n      </p>", this.getEncoding0());
        this.setByteList(2, "dev", this.getEncoding0());
        this.setByteList(0, "lister/measurement", this.getEncoding0());
        this.setByteList(7, "<p>\n        This separate module asks you your e-mail address.\n        We do not store the e-mail address but just use it to send you a link with your report page.\n      </p>", this.getEncoding0());
        this.setByteList(5, "Demande votre e-mail", this.getEncoding0());
        this.setByteList(8, "English-HTML", this.getEncoding0());
        this.setByteList(4, "Ask for your e-mail", this.getEncoding0());
        this.setByteList(6, "Fran\uffc3\uffa7ais", this.getEncoding0());
        this.setByteList(3, "0.0.1", this.getEncoding0());
        this.setByteList(11, "The user's email address (not saved)", this.getEncoding0());
        this.setByteList(10, "Fran\uffc3\uffa7ais-HTML", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3 file_62A9BF64B3F740F853D318CD095209DD08A4E1D3, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getCallSite0().call(threadContext, rubyObject, rubyObject, file_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getString0(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_62A9BF64B3F740F853D318CD095209DD08A4E1D3, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3.module__1$RUBY$Measurements:(Lruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc             "Measurement"
        //    33: invokevirtual   ruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    36: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    39: invokestatic    ruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3.class_2$RUBY$AskEmail:(Lruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    42: aload_1        
        //    43: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    46: goto            54
        //    49: aload_1        
        //    50: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    53: athrow         
        //    54: aload_1        
        //    55: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    58: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     46     49     54     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_2$RUBY$AskEmail(final FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    29: ldc             "AskEmail"
        //    31: swap           
        //    32: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    35: dup            
        //    36: astore_2       
        //    37: aload_1        
        //    38: swap           
        //    39: aload_0        
        //    40: aload_1        
        //    41: ldc             ",0,0,-1"
        //    43: invokevirtual   ruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    46: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClass:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    49: aload_1        
        //    50: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //    53: astore          locals
        //    55: aload_0        
        //    56: invokevirtual   ruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getCallSite1:()Lorg/jruby/runtime/CallSite;
        //    59: aload_1        
        //    60: aload_2        
        //    61: aload_2        
        //    62: aload_0        
        //    63: aload_1        
        //    64: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    67: bipush          32
        //    69: invokevirtual   ruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getString1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    72: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: pop            
        //    76: aload_0        
        //    77: invokevirtual   ruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getCallSite2:()Lorg/jruby/runtime/CallSite;
        //    80: aload_1        
        //    81: aload_2        
        //    82: aload_2        
        //    83: aload_0        
        //    84: aload_1        
        //    85: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    88: bipush          32
        //    90: invokevirtual   ruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getString2:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    93: aload_0        
        //    94: aload_1        
        //    95: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    98: bipush          32
        //   100: invokevirtual   ruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getString3:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   103: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   106: pop            
        //   107: aload_0        
        //   108: invokevirtual   ruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getCallSite3:()Lorg/jruby/runtime/CallSite;
        //   111: aload_1        
        //   112: aload_2        
        //   113: aload_2        
        //   114: aload_0        
        //   115: aload_1        
        //   116: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   119: bipush          32
        //   121: invokevirtual   ruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getString4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   124: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   127: pop            
        //   128: aload_0        
        //   129: invokevirtual   ruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getCallSite4:()Lorg/jruby/runtime/CallSite;
        //   132: aload_1        
        //   133: aload_2        
        //   134: aload_2        
        //   135: aload_0        
        //   136: aload_1        
        //   137: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   140: bipush          32
        //   142: invokevirtual   ruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getString5:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   145: aload_0        
        //   146: aload_1        
        //   147: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   150: bipush          64
        //   152: invokevirtual   ruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getString6:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   155: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   158: pop            
        //   159: aload_0        
        //   160: invokevirtual   ruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getCallSite5:()Lorg/jruby/runtime/CallSite;
        //   163: aload_1        
        //   164: aload_2        
        //   165: aload_2        
        //   166: aload_0        
        //   167: aload_1        
        //   168: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   171: bipush          32
        //   173: invokevirtual   ruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   176: aload_0        
        //   177: aload_1        
        //   178: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   181: bipush          32
        //   183: invokevirtual   ruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getString8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   186: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   189: pop            
        //   190: aload_0        
        //   191: invokevirtual   ruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getCallSite6:()Lorg/jruby/runtime/CallSite;
        //   194: aload_1        
        //   195: aload_2        
        //   196: aload_2        
        //   197: aload_0        
        //   198: aload_1        
        //   199: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   202: bipush          64
        //   204: invokevirtual   ruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getString9:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   207: aload_0        
        //   208: aload_1        
        //   209: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   212: bipush          10
        //   214: bipush          64
        //   216: invokevirtual   ruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   219: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   222: pop            
        //   223: aload_0        
        //   224: invokevirtual   ruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getCallSite7:()Lorg/jruby/runtime/CallSite;
        //   227: aload_1        
        //   228: aload_2        
        //   229: aload_2        
        //   230: aload_0        
        //   231: aload_1        
        //   232: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   235: ldc             "email"
        //   237: invokevirtual   ruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   240: aload_1        
        //   241: aload_2        
        //   242: aload_0        
        //   243: aload_1        
        //   244: ldc             "block_0$RUBY$AskEmail,-1,,false,0,./lib//lister/measurements/ask_email.rb,23,true"
        //   246: invokevirtual   ruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   249: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   252: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   255: aload_1        
        //   256: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   259: goto            267
        //   262: aload_1        
        //   263: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   266: athrow         
        //   267: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  55     213     4     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  55     255    262    267    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_0$RUBY$AskEmail(final FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3 file_62A9BF64B3F740F853D318CD095209DD08A4E1D3, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        threadContext.getCurrentScope();
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        file_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getCallSite8().call(threadContext, rubyObject, rubyObject, file_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getString(threadContext.runtime, 11, 32));
        return file_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getCallSite9().call(threadContext, rubyObject, rubyObject, file_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getString(threadContext.runtime, 12, 64), file_62A9BF64B3F740F853D318CD095209DD08A4E1D3.getString6(threadContext.runtime, 64));
    }
    
    public static IRubyObject class_2$RUBY$AskEmail(final FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3 file_62A9BF64B3F740F853D318CD095209DD08A4E1D3, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_2$RUBY$AskEmail(file_62A9BF64B3F740F853D318CD095209DD08A4E1D3, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3 file_62A9BF64B3F740F853D318CD095209DD08A4E1D3, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Measurements(file_62A9BF64B3F740F853D318CD095209DD08A4E1D3, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3 file_62A9BF64B3F740F853D318CD095209DD08A4E1D3, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_62A9BF64B3F740F853D318CD095209DD08A4E1D3, threadContext, rubyObject, block);
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
        final FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3 file_62A9BF64B3F740F853D318CD095209DD08A4E1D3 = new FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3();
        final String string = FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3.class.getClassLoader().getResource("ruby/jit/FILE_62A9BF64B3F740F853D318CD095209DD08A4E1D3.class").toString();
        file_62A9BF64B3F740F853D318CD095209DD08A4E1D3.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_62A9BF64B3F740F853D318CD095209DD08A4E1D3.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
