// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Arity;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99 extends AbstractScript
{
    public FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99() {
        this.filename = "./proprietary//undersimple/core/loosely_typable.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffinclude\uffffF\ufffffor\uffffN\uffffverify\uffffF\uffffvalid_value?\uffffN\uffff\u0002\u0001\u0000\u0000\u0002\u0000\u0000\u0002\u0001\u0001\u0000\u0000\u0001\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(0, "undersimple/core/loose_type", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99 file_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99.getCallSite0().call(threadContext, rubyObject, rubyObject, file_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99.getString0(threadContext.runtime, 32));
        return module__0$RUBY$UnderSimple(file_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$UnderSimple(final FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "UnderSimple"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99.module__1$RUBY$LooselyTypable:(Lruby/jit/FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$LooselyTypable(final FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "LooselyTypable"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: invokevirtual   ruby/jit/FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99.getCallSite1:()Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_2        
        //    34: aload_0        
        //    35: aload_1        
        //    36: ldc             "Verifiable"
        //    38: invokevirtual   ruby/jit/FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    44: pop            
        //    45: aload_1        
        //    46: aload_2        
        //    47: aload_0        
        //    48: ldc             "type"
        //    50: ldc             "method__2$RUBY$type"
        //    52: ldc             "name,0,1,-1"
        //    54: iconst_m1      
        //    55: ldc             "./proprietary//undersimple/core/loosely_typable.rb"
        //    57: ldc             6
        //    59: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    62: ldc             "oname"
        //    64: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: aload_1        
        //    68: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    71: goto            79
        //    74: aload_1        
        //    75: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    78: athrow         
        //    79: aload_1        
        //    80: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    83: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     71     74     79     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "type", frame = true, required = 0, optional = 1, rest = -1)
    public static IRubyObject method__2$RUBY$type(final FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
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
        //    32: goto            46
        //    35: aload           5
        //    37: aload_1        
        //    38: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    44: pop            
        //    45: pop            
        //    46: aload           locals
        //    48: aload_1        
        //    49: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    60: ifeq            136
        //    63: aload_0        
        //    64: aload_1        
        //    65: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    68: ldc             "@type"
        //    70: aload_2        
        //    71: aload_0        
        //    72: invokevirtual   ruby/jit/FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99.getCallSite2:()Lorg/jruby/runtime/CallSite;
        //    75: aload_1        
        //    76: aload_2        
        //    77: aload_0        
        //    78: aload_1        
        //    79: ldc             "LooseType"
        //    81: invokevirtual   ruby/jit/FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99.getConstant1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    84: aload           locals
        //    86: aload_1        
        //    87: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    90: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    93: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    96: invokevirtual   ruby/jit/FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99.setVariable0:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    99: pop            
        //   100: aload_0        
        //   101: invokevirtual   ruby/jit/FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99.getCallSite3:()Lorg/jruby/runtime/CallSite;
        //   104: aload_1        
        //   105: aload_2        
        //   106: aload_2        
        //   107: aload_0        
        //   108: aload_1        
        //   109: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   112: ldc             "correct_type"
        //   114: invokevirtual   ruby/jit/FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   117: aload_1        
        //   118: aload_2        
        //   119: aload_0        
        //   120: aload_1        
        //   121: ldc             "block_0$RUBY$type,1,x,false,2,./proprietary//undersimple/core/loosely_typable.rb,9,true"
        //   123: invokevirtual   ruby/jit/FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   126: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   129: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   132: pop            
        //   133: goto            136
        //   136: aload_0        
        //   137: aload_1        
        //   138: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   141: ldc             "@type"
        //   143: aload_2        
        //   144: invokevirtual   ruby/jit/FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99.getVariable1:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   147: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  46     102     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_0$RUBY$type(final FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: invokevirtual   ruby/jit/FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99.getCallSite4:()Lorg/jruby/runtime/CallSite;
        //    29: aload_1        
        //    30: aload_2        
        //    31: aload_0        
        //    32: aload_1        
        //    33: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    36: ldc             "@type"
        //    38: aload_2        
        //    39: invokevirtual   ruby/jit/FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99.getVariable0:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    42: aload           x
        //    44: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     23      9     x     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject module__1$RUBY$LooselyTypable(final FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99 file_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$LooselyTypable(file_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$UnderSimple(final FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99 file_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$UnderSimple(file_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99, threadContext, rubyObject, block);
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
        final FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99 file_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99 = new FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99();
        final String string = FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99.class.getClassLoader().getResource("ruby/jit/FILE_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99.class").toString();
        file_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_8E85B2E0595A6F22A818FEF3813FB9EC2AF82F99.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
