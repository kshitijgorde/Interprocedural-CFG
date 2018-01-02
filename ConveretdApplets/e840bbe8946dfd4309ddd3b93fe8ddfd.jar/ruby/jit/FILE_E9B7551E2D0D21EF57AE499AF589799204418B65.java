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

public class FILE_E9B7551E2D0D21EF57AE499AF589799204418B65 extends AbstractScript
{
    public FILE_E9B7551E2D0D21EF57AE499AF589799204418B65() {
        this.filename = "./proprietary//undersimple.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffff\u0001\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\t\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(8, "UnderSimple is a library to define measurements in a flexible and pretty way.", this.getEncoding0());
        this.setByteList(0, "undersimple/core/measurement", this.getEncoding0());
        this.setByteList(4, "0.0.3", this.getEncoding0());
        this.setByteList(6, "proprietary", this.getEncoding0());
        this.setByteList(5, "Lucas DiCioccio", this.getEncoding0());
        this.setByteList(2, "undersimple/core/report", this.getEncoding0());
        this.setByteList(3, "undersimple/core/loose_type", this.getEncoding0());
        this.setByteList(7, "http://dicioccio.fr/", this.getEncoding0());
        this.setByteList(1, "undersimple/core/parameter", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_E9B7551E2D0D21EF57AE499AF589799204418B65 file_E9B7551E2D0D21EF57AE499AF589799204418B65, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_E9B7551E2D0D21EF57AE499AF589799204418B65.getCallSite0().call(threadContext, rubyObject, rubyObject, file_E9B7551E2D0D21EF57AE499AF589799204418B65.getString0(threadContext.runtime, 32));
        file_E9B7551E2D0D21EF57AE499AF589799204418B65.getCallSite1().call(threadContext, rubyObject, rubyObject, file_E9B7551E2D0D21EF57AE499AF589799204418B65.getString1(threadContext.runtime, 32));
        file_E9B7551E2D0D21EF57AE499AF589799204418B65.getCallSite2().call(threadContext, rubyObject, rubyObject, file_E9B7551E2D0D21EF57AE499AF589799204418B65.getString2(threadContext.runtime, 32));
        file_E9B7551E2D0D21EF57AE499AF589799204418B65.getCallSite3().call(threadContext, rubyObject, rubyObject, file_E9B7551E2D0D21EF57AE499AF589799204418B65.getString3(threadContext.runtime, 32));
        return module__0$RUBY$UnderSimple(file_E9B7551E2D0D21EF57AE499AF589799204418B65, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$UnderSimple(final FILE_E9B7551E2D0D21EF57AE499AF589799204418B65 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_E9B7551E2D0D21EF57AE499AF589799204418B65.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    32: bipush          32
        //    34: invokevirtual   ruby/jit/FILE_E9B7551E2D0D21EF57AE499AF589799204418B65.getString4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    37: aload_1        
        //    38: ldc             "VERSION"
        //    40: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: pop            
        //    44: aload_1        
        //    45: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    48: aload_0        
        //    49: aload_1        
        //    50: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    53: bipush          32
        //    55: invokevirtual   ruby/jit/FILE_E9B7551E2D0D21EF57AE499AF589799204418B65.getString5:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    58: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //    61: aload_1        
        //    62: ldc             "AUTHORS"
        //    64: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: pop            
        //    68: aload_1        
        //    69: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    72: aload_0        
        //    73: aload_1        
        //    74: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    77: bipush          32
        //    79: invokevirtual   ruby/jit/FILE_E9B7551E2D0D21EF57AE499AF589799204418B65.getString6:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    82: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //    85: aload_1        
        //    86: ldc             "LICENCE"
        //    88: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    91: pop            
        //    92: aload_0        
        //    93: aload_1        
        //    94: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    97: bipush          32
        //    99: invokevirtual   ruby/jit/FILE_E9B7551E2D0D21EF57AE499AF589799204418B65.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   102: aload_1        
        //   103: ldc             "WEBSITE"
        //   105: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   108: pop            
        //   109: aload_0        
        //   110: aload_1        
        //   111: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   114: bipush          32
        //   116: invokevirtual   ruby/jit/FILE_E9B7551E2D0D21EF57AE499AF589799204418B65.getString8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   119: aload_1        
        //   120: ldc             "ABOUT"
        //   122: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   125: aload_1        
        //   126: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   129: goto            137
        //   132: aload_1        
        //   133: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   136: athrow         
        //   137: aload_1        
        //   138: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   141: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     129    132    137    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject module__0$RUBY$UnderSimple(final FILE_E9B7551E2D0D21EF57AE499AF589799204418B65 file_E9B7551E2D0D21EF57AE499AF589799204418B65, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$UnderSimple(file_E9B7551E2D0D21EF57AE499AF589799204418B65, threadContext, rubyObject, block);
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
        final FILE_E9B7551E2D0D21EF57AE499AF589799204418B65 file_E9B7551E2D0D21EF57AE499AF589799204418B65 = new FILE_E9B7551E2D0D21EF57AE499AF589799204418B65();
        final String string = FILE_E9B7551E2D0D21EF57AE499AF589799204418B65.class.getClassLoader().getResource("ruby/jit/FILE_E9B7551E2D0D21EF57AE499AF589799204418B65.class").toString();
        file_E9B7551E2D0D21EF57AE499AF589799204418B65.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_E9B7551E2D0D21EF57AE499AF589799204418B65.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
