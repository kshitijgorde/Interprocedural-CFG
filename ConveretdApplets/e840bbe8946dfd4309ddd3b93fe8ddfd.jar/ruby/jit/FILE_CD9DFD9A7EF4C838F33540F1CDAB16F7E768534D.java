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

public class FILE_CD9DFD9A7EF4C838F33540F1CDAB16F7E768534D extends AbstractScript
{
    public FILE_CD9DFD9A7EF4C838F33540F1CDAB16F7E768534D() {
        this.filename = "./lib//win32.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffff\u0001\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0003\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(2, "win32/mib/ipstats", this.getEncoding0());
        this.setByteList(1, "win32/iphelper", this.getEncoding0());
        this.setByteList(0, "win32/errors", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_CD9DFD9A7EF4C838F33540F1CDAB16F7E768534D file_CD9DFD9A7EF4C838F33540F1CDAB16F7E768534D, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return module__0$RUBY$Win32(file_CD9DFD9A7EF4C838F33540F1CDAB16F7E768534D, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Win32(final FILE_CD9DFD9A7EF4C838F33540F1CDAB16F7E768534D p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Win32"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_CD9DFD9A7EF4C838F33540F1CDAB16F7E768534D.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: invokevirtual   ruby/jit/FILE_CD9DFD9A7EF4C838F33540F1CDAB16F7E768534D.getCallSite0:()Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_2        
        //    34: aload_0        
        //    35: aload_1        
        //    36: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    39: bipush          32
        //    41: invokevirtual   ruby/jit/FILE_CD9DFD9A7EF4C838F33540F1CDAB16F7E768534D.getString0:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    44: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: pop            
        //    48: aload_0        
        //    49: invokevirtual   ruby/jit/FILE_CD9DFD9A7EF4C838F33540F1CDAB16F7E768534D.getCallSite1:()Lorg/jruby/runtime/CallSite;
        //    52: aload_1        
        //    53: aload_2        
        //    54: aload_2        
        //    55: aload_0        
        //    56: aload_1        
        //    57: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    60: bipush          32
        //    62: invokevirtual   ruby/jit/FILE_CD9DFD9A7EF4C838F33540F1CDAB16F7E768534D.getString1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    65: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    68: pop            
        //    69: aload_0        
        //    70: invokevirtual   ruby/jit/FILE_CD9DFD9A7EF4C838F33540F1CDAB16F7E768534D.getCallSite2:()Lorg/jruby/runtime/CallSite;
        //    73: aload_1        
        //    74: aload_2        
        //    75: aload_2        
        //    76: aload_0        
        //    77: aload_1        
        //    78: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    81: bipush          32
        //    83: invokevirtual   ruby/jit/FILE_CD9DFD9A7EF4C838F33540F1CDAB16F7E768534D.getString2:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    86: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: aload_1        
        //    90: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    93: goto            101
        //    96: aload_1        
        //    97: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   100: athrow         
        //   101: aload_1        
        //   102: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   105: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     93     96     101    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException: -1
        //     at java.util.ArrayList.elementData(ArrayList.java:422)
        //     at java.util.ArrayList.remove(ArrayList.java:499)
        //     at com.strobel.assembler.ir.StackMappingVisitor.pop(StackMappingVisitor.java:267)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:595)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
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
    
    public static IRubyObject module__0$RUBY$Win32(final FILE_CD9DFD9A7EF4C838F33540F1CDAB16F7E768534D file_CD9DFD9A7EF4C838F33540F1CDAB16F7E768534D, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Win32(file_CD9DFD9A7EF4C838F33540F1CDAB16F7E768534D, threadContext, rubyObject, block);
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
        final FILE_CD9DFD9A7EF4C838F33540F1CDAB16F7E768534D file_CD9DFD9A7EF4C838F33540F1CDAB16F7E768534D = new FILE_CD9DFD9A7EF4C838F33540F1CDAB16F7E768534D();
        final String string = FILE_CD9DFD9A7EF4C838F33540F1CDAB16F7E768534D.class.getClassLoader().getResource("ruby/jit/FILE_CD9DFD9A7EF4C838F33540F1CDAB16F7E768534D.class").toString();
        file_CD9DFD9A7EF4C838F33540F1CDAB16F7E768534D.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_CD9DFD9A7EF4C838F33540F1CDAB16F7E768534D.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
