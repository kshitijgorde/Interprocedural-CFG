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

public class FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 extends AbstractScript
{
    public FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9() {
        this.filename = "./lib//lister/utils/programs.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffextend\uffffF\uffffselect\uffffN\ufffflines\uffffN\uffffspawn\uffffF\uffffcmd\uffffV\ufffffirst\uffffN\uffffsplit\uffffN\uffffmap\uffffN\uffff[]\uffffN\uffffsplit\uffffN\uffffselect\uffffN\ufffflines\uffffN\uffffspawn\uffffF\uffffcmd\uffffV\ufffffirst\uffffN\uffffsplit\uffffN\uffffmap\uffffN\uffff[]\uffffN\uffffsplit\uffffN\uffffmap\uffffN\uffffselect\uffffN\ufffflines\uffffN\uffffspawn\uffffF\uffffcmd\uffffV\uffff>\uffffN\uffffto_i\uffffN\uffff[]\uffffN\uffffsplit\uffffN\ufffffirst\uffffN\uffffsplit\uffffN\uffffmap\uffffN\uffffconv\uffffN\uffffon_windows?\uffffN\uffffextend\uffffF\uffffon_mac?\uffffN\uffffextend\uffffF\uffffon_linux?\uffffN\uffffextend\uffffF\uffff\u0006\u0000\u0000\u0000\b\u0002\u0000\u0000\u0000\u0007\u0000\u0000\b\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(3, "ps -A", this.getEncoding0());
        this.setByteList(5, "UTF-8", this.getEncoding0());
        this.setByteList(6, "CP1252", this.getEncoding0());
        this.setByteList(0, "lister/util", this.getEncoding0());
        this.setByteList(2, "\\d+", this.getEncoding0());
        this.setByteList(4, "ps -e", this.getEncoding0());
        this.setByteList(1, "lister/utils/platform", this.getEncoding0());
        this.setByteList(7, "tasklist", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 file_3E69F969AEFB267EE375DFEB03874909E640F9E9, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite0().call(threadContext, rubyObject, rubyObject, file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getString0(threadContext.runtime, 32));
        file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite1().call(threadContext, rubyObject, rubyObject, file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getString1(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_3E69F969AEFB267EE375DFEB03874909E640F9E9, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.module__1$RUBY$Utils:(Lruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Utils(final FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.module__2$RUBY$Programs:(Lruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__2$RUBY$Programs(final FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Programs"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite2:()Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_2        
        //    34: aload_0        
        //    35: aload_1        
        //    36: ldc             "Util"
        //    38: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    44: pop            
        //    45: aload_0        
        //    46: aload_1        
        //    47: aload_2        
        //    48: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    54: invokestatic    ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.module__3$RUBY$MacOs:(Lruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: pop            
        //    58: aload_0        
        //    59: aload_1        
        //    60: aload_2        
        //    61: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    64: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    67: invokestatic    ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.module__6$RUBY$Linux:(Lruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: pop            
        //    71: aload_0        
        //    72: aload_1        
        //    73: aload_2        
        //    74: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    80: invokestatic    ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.module__9$RUBY$Windows:(Lruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: pop            
        //    84: aload_0        
        //    85: bipush          34
        //    87: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    90: aload_1        
        //    91: aload_2        
        //    92: aload_0        
        //    93: aload_1        
        //    94: ldc_w           "Platform"
        //    97: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getConstant2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   100: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   103: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   108: ifeq            134
        //   111: aload_0        
        //   112: bipush          35
        //   114: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   117: aload_1        
        //   118: aload_2        
        //   119: aload_2        
        //   120: aload_0        
        //   121: aload_1        
        //   122: ldc_w           "Windows"
        //   125: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getConstant3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   128: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   131: goto            237
        //   134: aload_0        
        //   135: bipush          36
        //   137: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   140: aload_1        
        //   141: aload_2        
        //   142: aload_0        
        //   143: aload_1        
        //   144: ldc_w           "Platform"
        //   147: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getConstant4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   150: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   153: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   158: ifeq            183
        //   161: aload_0        
        //   162: bipush          37
        //   164: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   167: aload_1        
        //   168: aload_2        
        //   169: aload_2        
        //   170: aload_0        
        //   171: aload_1        
        //   172: ldc             "MacOs"
        //   174: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getConstant5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   177: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   180: goto            237
        //   183: aload_0        
        //   184: bipush          38
        //   186: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   189: aload_1        
        //   190: aload_2        
        //   191: aload_0        
        //   192: aload_1        
        //   193: ldc_w           "Platform"
        //   196: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getConstant6:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   199: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   202: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   207: ifeq            233
        //   210: aload_0        
        //   211: bipush          39
        //   213: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   216: aload_1        
        //   217: aload_2        
        //   218: aload_2        
        //   219: aload_0        
        //   220: aload_1        
        //   221: ldc_w           "Linux"
        //   224: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getConstant7:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   227: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   230: goto            237
        //   233: aload_1        
        //   234: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   237: aload_1        
        //   238: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   241: goto            249
        //   244: aload_1        
        //   245: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   248: athrow         
        //   249: aload_1        
        //   250: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   253: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     241    244    249    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject module__3$RUBY$MacOs(final FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "MacOs"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getScope3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_1        
        //    28: aload_2        
        //    29: aload_0        
        //    30: ldc             "all"
        //    32: ldc             "method__4$RUBY$all"
        //    34: ldc             "pid_lines,0,0,-1"
        //    36: iconst_0       
        //    37: ldc             "./lib//lister/utils/programs.rb"
        //    39: ldc             10
        //    41: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    44: ldc             "NONE"
        //    46: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: pop            
        //    50: aload_1        
        //    51: aload_2        
        //    52: aload_0        
        //    53: ldc             "cmd"
        //    55: ldc             "method__5$RUBY$cmd"
        //    57: ldc             ",0,0,-1"
        //    59: iconst_0       
        //    60: ldc             "./lib//lister/utils/programs.rb"
        //    62: ldc             19
        //    64: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    67: ldc             "NONE"
        //    69: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    72: aload_1        
        //    73: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: goto            84
        //    79: aload_1        
        //    80: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    83: athrow         
        //    84: aload_1        
        //    85: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    88: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     76     79     84     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "all", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$all(final FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 file_3E69F969AEFB267EE375DFEB03874909E640F9E9, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite3().callIter(threadContext, rubyObject, file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite4().call(threadContext, rubyObject, file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite5().call(threadContext, rubyObject, rubyObject, file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite6().call(threadContext, rubyObject, rubyObject))), RuntimeHelpers.createBlock(threadContext, rubyObject, file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getBlockBody0(threadContext, "block_0$RUBY$all,1,l,false,2,./lib//lister/utils/programs.rb,11,false"))));
        return file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite9().callIter(threadContext, rubyObject, locals.getValueZeroDepthZeroOrNil(threadContext.nil), RuntimeHelpers.createBlock(threadContext, rubyObject, file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getBlockBody1(threadContext, "block_1$RUBY$all,1,l,false,2,./lib//lister/utils/programs.rb,14,false")));
    }
    
    public static IRubyObject block_0$RUBY$all(final FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: aload_1        
        //    37: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    40: aload_0        
        //    41: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getByteList2:()Lorg/jruby/util/ByteList;
        //    44: ldc             512
        //    46: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getRegexp0:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //    49: aload_0        
        //    50: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite7:()Lorg/jruby/runtime/CallSite;
        //    53: aload_1        
        //    54: aload_2        
        //    55: aload_0        
        //    56: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite8:()Lorg/jruby/runtime/CallSite;
        //    59: aload_1        
        //    60: aload_2        
        //    61: aload           locals
        //    63: aload_1        
        //    64: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: aload_1        
        //    77: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.match3:(Lorg/jruby/RubyRegexp;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject block_1$RUBY$all(final FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    38: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_0        
        //    44: bipush          11
        //    46: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload           locals
        //    53: aload_1        
        //    54: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: aload_1        
        //    64: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    67: invokestatic    org/jruby/RubyFixnum.three:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    70: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: areturn        
        //    74: pop            
        //    75: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     39      5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     74     74     78     Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    @JRubyMethod(name = "cmd", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$cmd(final FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 file_3E69F969AEFB267EE375DFEB03874909E640F9E9, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getString3(threadContext.runtime, 32);
    }
    
    public static IRubyObject module__3$RUBY$MacOs(final FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 file_3E69F969AEFB267EE375DFEB03874909E640F9E9, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__3$RUBY$MacOs(file_3E69F969AEFB267EE375DFEB03874909E640F9E9, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__6$RUBY$Linux(final FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc_w           "Linux"
        //    11: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    14: dup            
        //    15: astore_2       
        //    16: aload_1        
        //    17: swap           
        //    18: aload_0        
        //    19: aload_1        
        //    20: ldc             ",0,0,-1"
        //    22: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getScope4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    25: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    28: aload_1        
        //    29: aload_2        
        //    30: aload_0        
        //    31: ldc             "all"
        //    33: ldc_w           "method__7$RUBY$all"
        //    36: ldc             "pid_lines,0,0,-1"
        //    38: iconst_0       
        //    39: ldc             "./lib//lister/utils/programs.rb"
        //    41: ldc_w           25
        //    44: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    47: ldc             "NONE"
        //    49: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: pop            
        //    53: aload_1        
        //    54: aload_2        
        //    55: aload_0        
        //    56: ldc             "cmd"
        //    58: ldc_w           "method__8$RUBY$cmd"
        //    61: ldc             ",0,0,-1"
        //    63: iconst_0       
        //    64: ldc             "./lib//lister/utils/programs.rb"
        //    66: ldc_w           34
        //    69: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    72: ldc             "NONE"
        //    74: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: aload_1        
        //    78: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    81: goto            89
        //    84: aload_1        
        //    85: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    88: athrow         
        //    89: aload_1        
        //    90: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    93: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  28     81     84     89     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "all", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$all(final FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 file_3E69F969AEFB267EE375DFEB03874909E640F9E9, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite(12).callIter(threadContext, rubyObject, file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite(13).call(threadContext, rubyObject, file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite(14).call(threadContext, rubyObject, rubyObject, file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite(15).call(threadContext, rubyObject, rubyObject))), RuntimeHelpers.createBlock(threadContext, rubyObject, file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getBlockBody2(threadContext, "block_2$RUBY$all,1,l,false,2,./lib//lister/utils/programs.rb,26,false"))));
        return file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite(18).callIter(threadContext, rubyObject, locals.getValueZeroDepthZeroOrNil(threadContext.nil), RuntimeHelpers.createBlock(threadContext, rubyObject, file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getBlockBody3(threadContext, "block_3$RUBY$all,1,l,false,2,./lib//lister/utils/programs.rb,29,false")));
    }
    
    public static IRubyObject block_2$RUBY$all(final FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: aload_1        
        //    37: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    40: aload_0        
        //    41: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getByteList2:()Lorg/jruby/util/ByteList;
        //    44: ldc             512
        //    46: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getRegexp1:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //    49: aload_0        
        //    50: bipush          16
        //    52: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    55: aload_1        
        //    56: aload_2        
        //    57: aload_0        
        //    58: bipush          17
        //    60: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    63: aload_1        
        //    64: aload_2        
        //    65: aload           locals
        //    67: aload_1        
        //    68: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    71: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: aload_1        
        //    81: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.match3:(Lorg/jruby/RubyRegexp;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    84: areturn        
        //    85: pop            
        //    86: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     50      5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     85     85     89     Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    public static IRubyObject block_3$RUBY$all(final FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: bipush          19
        //    38: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_0        
        //    44: bipush          20
        //    46: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload           locals
        //    53: aload_1        
        //    54: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: aload_1        
        //    64: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    67: invokestatic    org/jruby/RubyFixnum.three:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    70: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: areturn        
        //    74: pop            
        //    75: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     39      5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     74     74     78     Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    @JRubyMethod(name = "cmd", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$cmd(final FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 file_3E69F969AEFB267EE375DFEB03874909E640F9E9, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getString4(threadContext.runtime, 32);
    }
    
    public static IRubyObject module__6$RUBY$Linux(final FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 file_3E69F969AEFB267EE375DFEB03874909E640F9E9, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__6$RUBY$Linux(file_3E69F969AEFB267EE375DFEB03874909E640F9E9, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__9$RUBY$Windows(final FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    22: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getScope5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    25: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    28: aload_1        
        //    29: aload_2        
        //    30: aload_0        
        //    31: ldc             "all"
        //    33: ldc_w           "method__10$RUBY$all"
        //    36: ldc_w           "strs,0,0,-1"
        //    39: iconst_0       
        //    40: ldc             "./lib//lister/utils/programs.rb"
        //    42: ldc_w           40
        //    45: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    48: ldc             "NONE"
        //    50: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    53: pop            
        //    54: aload_1        
        //    55: aload_2        
        //    56: aload_0        
        //    57: ldc             "cmd"
        //    59: ldc_w           "method__11$RUBY$cmd"
        //    62: ldc             ",0,0,-1"
        //    64: iconst_0       
        //    65: ldc             "./lib//lister/utils/programs.rb"
        //    67: ldc_w           48
        //    70: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    73: ldc             "NONE"
        //    75: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    78: aload_1        
        //    79: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: goto            90
        //    85: aload_1        
        //    86: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    89: athrow         
        //    90: aload_1        
        //    91: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    94: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  28     82     85     90     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "all", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__10$RUBY$all(final FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 file_3E69F969AEFB267EE375DFEB03874909E640F9E9, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite(21).callIter(threadContext, self, file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite(22).callIter(threadContext, self, file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite(23).call(threadContext, self, file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite(24).call(threadContext, self, self, file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite(25).call(threadContext, self, self))), RuntimeHelpers.createBlock(threadContext, self, file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getBlockBody4(threadContext, "block_4$RUBY$all,1,l,false,2,./lib//lister/utils/programs.rb,41,false"))), RuntimeHelpers.createBlock(threadContext, self, file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getBlockBody5(threadContext, "block_5$RUBY$all,1,l,false,2,./lib//lister/utils/programs.rb,43,false"))));
        return file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite(32).callIter(threadContext, self, locals.getValueZeroDepthZeroOrNil(threadContext.nil), RuntimeHelpers.createBlock(threadContext, self, file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getBlockBody6(threadContext, "block_6$RUBY$all,1,str,false,2,./lib//lister/utils/programs.rb,44,true")));
    }
    
    public static IRubyObject block_4$RUBY$all(final FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: bipush          26
        //    38: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_0        
        //    44: bipush          27
        //    46: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload_0        
        //    52: bipush          28
        //    54: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    57: aload_1        
        //    58: aload_2        
        //    59: aload_0        
        //    60: bipush          29
        //    62: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    65: aload_1        
        //    66: aload_2        
        //    67: aload           locals
        //    69: aload_1        
        //    70: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: aload_1        
        //    80: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    83: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    86: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    92: ldc2_w          0
        //    95: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;J)Lorg/jruby/runtime/builtin/IRubyObject;
        //    98: areturn        
        //    99: pop            
        //   100: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     64      5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     99     99     103    Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    public static IRubyObject block_5$RUBY$all(final FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: bipush          30
        //    38: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_0        
        //    44: bipush          31
        //    46: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload           locals
        //    53: aload_1        
        //    54: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: areturn        
        //    67: pop            
        //    68: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     32      5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     67     67     71     Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    public static IRubyObject block_6$RUBY$all(final FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          33
        //    28: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_0        
        //    34: aload_1        
        //    35: ldc_w           "Iconv"
        //    38: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getConstant1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: aload_0        
        //    42: aload_1        
        //    43: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    46: bipush          32
        //    48: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getString5:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    51: aload_0        
        //    52: aload_1        
        //    53: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    56: bipush          32
        //    58: invokevirtual   ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.getString6:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    61: aload           str
        //    63: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     42      9     str   Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "cmd", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__11$RUBY$cmd(final FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 file_3E69F969AEFB267EE375DFEB03874909E640F9E9, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E69F969AEFB267EE375DFEB03874909E640F9E9.getString7(threadContext.runtime, 32);
    }
    
    public static IRubyObject module__9$RUBY$Windows(final FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 file_3E69F969AEFB267EE375DFEB03874909E640F9E9, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__9$RUBY$Windows(file_3E69F969AEFB267EE375DFEB03874909E640F9E9, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__2$RUBY$Programs(final FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 file_3E69F969AEFB267EE375DFEB03874909E640F9E9, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__2$RUBY$Programs(file_3E69F969AEFB267EE375DFEB03874909E640F9E9, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Utils(final FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 file_3E69F969AEFB267EE375DFEB03874909E640F9E9, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Utils(file_3E69F969AEFB267EE375DFEB03874909E640F9E9, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 file_3E69F969AEFB267EE375DFEB03874909E640F9E9, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_3E69F969AEFB267EE375DFEB03874909E640F9E9, threadContext, rubyObject, block);
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
        final FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9 file_3E69F969AEFB267EE375DFEB03874909E640F9E9 = new FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9();
        final String string = FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.class.getClassLoader().getResource("ruby/jit/FILE_3E69F969AEFB267EE375DFEB03874909E640F9E9.class").toString();
        file_3E69F969AEFB267EE375DFEB03874909E640F9E9.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_3E69F969AEFB267EE375DFEB03874909E640F9E9.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
