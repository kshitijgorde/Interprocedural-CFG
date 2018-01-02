// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.runtime.DynamicScope;
import org.jruby.exceptions.JumpException;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_D836B75EB52C6484B796B105F0BAADE84E649298 extends AbstractScript
{
    public FILE_D836B75EB52C6484B796B105F0BAADE84E649298() {
        this.filename = "./lib//lister/utils/transcode.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("autoload\uffffF\uffffeach\uffffN\uffffconv\uffffN\uffffjoin\uffffN\uffffeach\uffffN\uffffsplit\uffffN\ufffftranscode\uffffF\uffff\u0003\u0001\u0000\u0000\u0006\u0000\u0000\u0000\u0000\u0002\u0000\u0000\u0007\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(2, "UTF-8", this.getEncoding0());
        this.setByteList(6, " ", this.getEncoding0());
        this.setByteList(1, "ASCII", this.getEncoding0());
        this.setByteList(3, "CP1252", this.getEncoding0());
        this.setByteList(5, "CP1251", this.getEncoding0());
        this.setByteList(4, "CP1250", this.getEncoding0());
        this.setByteList(0, "iconv", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_D836B75EB52C6484B796B105F0BAADE84E649298 file_D836B75EB52C6484B796B105F0BAADE84E649298, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_D836B75EB52C6484B796B105F0BAADE84E649298.getCallSite0().call(threadContext, rubyObject, rubyObject, file_D836B75EB52C6484B796B105F0BAADE84E649298.getSymbol0(threadContext.runtime, "Iconv"), file_D836B75EB52C6484B796B105F0BAADE84E649298.getString0(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_D836B75EB52C6484B796B105F0BAADE84E649298, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_D836B75EB52C6484B796B105F0BAADE84E649298 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_D836B75EB52C6484B796B105F0BAADE84E649298.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_D836B75EB52C6484B796B105F0BAADE84E649298.module__1$RUBY$Utils:(Lruby/jit/FILE_D836B75EB52C6484B796B105F0BAADE84E649298;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Utils(final FILE_D836B75EB52C6484B796B105F0BAADE84E649298 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_D836B75EB52C6484B796B105F0BAADE84E649298.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_D836B75EB52C6484B796B105F0BAADE84E649298.module__2$RUBY$Transcode:(Lruby/jit/FILE_D836B75EB52C6484B796B105F0BAADE84E649298;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__2$RUBY$Transcode(final FILE_D836B75EB52C6484B796B105F0BAADE84E649298 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Transcode"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_D836B75EB52C6484B796B105F0BAADE84E649298.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_1        
        //    28: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    31: aload_0        
        //    32: aload_1        
        //    33: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    36: bipush          32
        //    38: invokevirtual   ruby/jit/FILE_D836B75EB52C6484B796B105F0BAADE84E649298.getString1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    41: aload_0        
        //    42: aload_1        
        //    43: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    46: bipush          32
        //    48: invokevirtual   ruby/jit/FILE_D836B75EB52C6484B796B105F0BAADE84E649298.getString2:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    51: aload_0        
        //    52: aload_1        
        //    53: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    56: bipush          32
        //    58: invokevirtual   ruby/jit/FILE_D836B75EB52C6484B796B105F0BAADE84E649298.getString3:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    61: aload_0        
        //    62: aload_1        
        //    63: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    66: bipush          32
        //    68: invokevirtual   ruby/jit/FILE_D836B75EB52C6484B796B105F0BAADE84E649298.getString4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    71: aload_0        
        //    72: aload_1        
        //    73: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    76: bipush          32
        //    78: invokevirtual   ruby/jit/FILE_D836B75EB52C6484B796B105F0BAADE84E649298.getString5:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    81: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //    84: aload_1        
        //    85: ldc             "ENCODINGS"
        //    87: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    90: pop            
        //    91: aload_1        
        //    92: aload_2        
        //    93: aload_0        
        //    94: ldc_w           "transcode"
        //    97: ldc_w           "method__3$RUBY$transcode"
        //   100: ldc_w           "str;recurse,1,1,-1"
        //   103: bipush          -2
        //   105: ldc             "./lib//lister/utils/transcode.rb"
        //   107: ldc_w           6
        //   110: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   113: ldc_w           "qstr;orecurse"
        //   116: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   119: aload_1        
        //   120: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   123: goto            131
        //   126: aload_1        
        //   127: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   130: athrow         
        //   131: aload_1        
        //   132: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   135: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     123    126    131    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "transcode", frame = true, required = 1, optional = 1, rest = -1)
    public static IRubyObject method__3$RUBY$transcode(final FILE_D836B75EB52C6484B796B105F0BAADE84E649298 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
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
        //    42: goto            63
        //    45: aload           5
        //    47: aload_1        
        //    48: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    51: invokevirtual   org/jruby/Ruby.getTrue:()Lorg/jruby/RubyBoolean;
        //    54: aload_1        
        //    55: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //    58: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    61: pop            
        //    62: pop            
        //    63: aload_0        
        //    64: invokevirtual   ruby/jit/FILE_D836B75EB52C6484B796B105F0BAADE84E649298.getCallSite1:()Lorg/jruby/runtime/CallSite;
        //    67: aload_1        
        //    68: aload_2        
        //    69: aload_0        
        //    70: aload_1        
        //    71: ldc             "ENCODINGS"
        //    73: invokevirtual   ruby/jit/FILE_D836B75EB52C6484B796B105F0BAADE84E649298.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: aload_1        
        //    77: aload_2        
        //    78: aload_0        
        //    79: aload_1        
        //    80: ldc             "block_0$RUBY$transcode,1,encoding,false,2,./lib//lister/utils/transcode.rb,7,false"
        //    82: invokevirtual   ruby/jit/FILE_D836B75EB52C6484B796B105F0BAADE84E649298.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    85: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    88: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    91: pop            
        //    92: aload           locals
        //    94: aload_1        
        //    95: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    98: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   101: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   106: ifeq            173
        //   109: aload_0        
        //   110: invokevirtual   ruby/jit/FILE_D836B75EB52C6484B796B105F0BAADE84E649298.getCallSite3:()Lorg/jruby/runtime/CallSite;
        //   113: aload_1        
        //   114: aload_2        
        //   115: aload_0        
        //   116: invokevirtual   ruby/jit/FILE_D836B75EB52C6484B796B105F0BAADE84E649298.getCallSite4:()Lorg/jruby/runtime/CallSite;
        //   119: aload_1        
        //   120: aload_2        
        //   121: aload_0        
        //   122: invokevirtual   ruby/jit/FILE_D836B75EB52C6484B796B105F0BAADE84E649298.getCallSite5:()Lorg/jruby/runtime/CallSite;
        //   125: aload_1        
        //   126: aload_2        
        //   127: aload           locals
        //   129: aload_1        
        //   130: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   133: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   136: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   139: aload_1        
        //   140: aload_2        
        //   141: aload_0        
        //   142: aload_1        
        //   143: ldc_w           "block_1$RUBY$transcode,1,atom,false,2,./lib//lister/utils/transcode.rb,18,true"
        //   146: invokevirtual   ruby/jit/FILE_D836B75EB52C6484B796B105F0BAADE84E649298.getBlockBody1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   149: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   152: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   155: aload_0        
        //   156: aload_1        
        //   157: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   160: bipush          32
        //   162: invokevirtual   ruby/jit/FILE_D836B75EB52C6484B796B105F0BAADE84E649298.getString6:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   165: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   168: areturn        
        //   169: nop            
        //   170: nop            
        //   171: nop            
        //   172: athrow         
        //   173: aload           locals
        //   175: aload_1        
        //   176: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   179: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   182: areturn        
        //   183: athrow         
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  63     121     5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_0$RUBY$transcode(final FILE_D836B75EB52C6484B796B105F0BAADE84E649298 file_D836B75EB52C6484B796B105F0BAADE84E649298, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject valueZeroDepthZero, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        currentScope.setValueZeroDepthZero(threadContext.nil);
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        currentScope.setValueZeroDepthZero(valueZeroDepthZero);
        try {
            return chained_4_rescue_1$RUBY$SYNTHETICtranscode(file_D836B75EB52C6484B796B105F0BAADE84E649298, threadContext, rubyObject, valueZeroDepthZero, block);
        }
        catch (JumpException.RedoJump redoJump) {
            return chained_4_rescue_1$RUBY$SYNTHETICtranscode(file_D836B75EB52C6484B796B105F0BAADE84E649298, threadContext, rubyObject, valueZeroDepthZero, block);
        }
    }
    
    public static IRubyObject chained_4_rescue_1$RUBY$SYNTHETICtranscode(final FILE_D836B75EB52C6484B796B105F0BAADE84E649298 file_D836B75EB52C6484B796B105F0BAADE84E649298, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        final IRubyObject errorInfo = threadContext.getErrorInfo();
        IRubyObject rubyObject3;
        try {
            try {
                throw RuntimeHelpers.returnJump(file_D836B75EB52C6484B796B105F0BAADE84E649298.getCallSite2().call(threadContext, rubyObject, file_D836B75EB52C6484B796B105F0BAADE84E649298.getConstant1(threadContext, "Iconv"), file_D836B75EB52C6484B796B105F0BAADE84E649298.getString2(threadContext.runtime, 32), currentScope.getValueZeroDepthZeroOrNil(threadContext.nil), currentScope.getNextCapturedScope().getValueZeroDepthZeroOrNil(threadContext.nil)), threadContext);
            }
            catch (JumpException.FlowControlException ex) {
                throw ex;
            }
            catch (Throwable t) {
                if (RuntimeHelpers.isJavaExceptionHandled(t, file_D836B75EB52C6484B796B105F0BAADE84E649298.getConstantFrom3(RuntimeHelpers.checkIsModule(file_D836B75EB52C6484B796B105F0BAADE84E649298.getConstant2(threadContext, "Iconv")), threadContext, "IllegalSequence"), threadContext).isTrue()) {
                    rubyObject3 = threadContext.nil;
                    RuntimeHelpers.clearErrorInfo(threadContext);
                }
                else {
                    if (!RuntimeHelpers.isJavaExceptionHandled(t, file_D836B75EB52C6484B796B105F0BAADE84E649298.getConstantFrom5(RuntimeHelpers.checkIsModule(file_D836B75EB52C6484B796B105F0BAADE84E649298.getConstant4(threadContext, "Iconv")), threadContext, "InvalidEncoding"), threadContext).isTrue()) {
                        throw t;
                    }
                    rubyObject3 = threadContext.nil;
                    RuntimeHelpers.clearErrorInfo(threadContext);
                }
            }
        }
        catch (JumpException.FlowControlException ex2) {
            threadContext.setErrorInfo(errorInfo);
            throw ex2;
        }
        threadContext.setErrorInfo(errorInfo);
        return rubyObject3;
    }
    
    public static IRubyObject block_1$RUBY$transcode(final FILE_D836B75EB52C6484B796B105F0BAADE84E649298 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: invokevirtual   ruby/jit/FILE_D836B75EB52C6484B796B105F0BAADE84E649298.getCallSite6:()Lorg/jruby/runtime/CallSite;
        //    29: aload_1        
        //    30: aload_2        
        //    31: aload_2        
        //    32: aload           atom
        //    34: aload_1        
        //    35: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    38: invokevirtual   org/jruby/Ruby.getFalse:()Lorg/jruby/RubyBoolean;
        //    41: aload_1        
        //    42: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //    45: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    48: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     24      9     atom  Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__2$RUBY$Transcode(final FILE_D836B75EB52C6484B796B105F0BAADE84E649298 file_D836B75EB52C6484B796B105F0BAADE84E649298, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__2$RUBY$Transcode(file_D836B75EB52C6484B796B105F0BAADE84E649298, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Utils(final FILE_D836B75EB52C6484B796B105F0BAADE84E649298 file_D836B75EB52C6484B796B105F0BAADE84E649298, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Utils(file_D836B75EB52C6484B796B105F0BAADE84E649298, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_D836B75EB52C6484B796B105F0BAADE84E649298 file_D836B75EB52C6484B796B105F0BAADE84E649298, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_D836B75EB52C6484B796B105F0BAADE84E649298, threadContext, rubyObject, block);
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
        final FILE_D836B75EB52C6484B796B105F0BAADE84E649298 file_D836B75EB52C6484B796B105F0BAADE84E649298 = new FILE_D836B75EB52C6484B796B105F0BAADE84E649298();
        final String string = FILE_D836B75EB52C6484B796B105F0BAADE84E649298.class.getClassLoader().getResource("ruby/jit/FILE_D836B75EB52C6484B796B105F0BAADE84E649298.class").toString();
        file_D836B75EB52C6484B796B105F0BAADE84E649298.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_D836B75EB52C6484B796B105F0BAADE84E649298.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
