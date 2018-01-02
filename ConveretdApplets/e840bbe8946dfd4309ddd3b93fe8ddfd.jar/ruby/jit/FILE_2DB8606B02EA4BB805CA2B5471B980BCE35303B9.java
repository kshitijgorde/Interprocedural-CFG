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

public class FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9 extends AbstractScript
{
    public FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9() {
        this.filename = "./lib//win32/iphelper.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffextend\uffffF\uffffffi_lib\uffffF\uffffffi_convention\uffffF\uffffattach_function\uffffF\uffffattach_function\uffffF\uffffattach_function\uffffF\uffffattach_function\uffffF\uffffattach_function\uffffF\uffffattach_function\uffffF\uffffattach_function\uffffF\uffff\u0002\f\u0000\u0000\u0002\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0002\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(1, "Iphlpapi", this.getEncoding0());
        this.setByteList(0, "ffi", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9 file_2DB8606B02EA4BB805CA2B5471B980BCE35303B9, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getCallSite0().call(threadContext, rubyObject, rubyObject, file_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getString0(threadContext.runtime, 32));
        return module__0$RUBY$Win32(file_2DB8606B02EA4BB805CA2B5471B980BCE35303B9, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Win32(final FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.module__1$RUBY$IPHelper:(Lruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$IPHelper(final FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "IPHelper"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getCallSite1:()Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_2        
        //    34: aload_0        
        //    35: aload_1        
        //    36: ldc             "FFI"
        //    38: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    44: aload_0        
        //    45: swap           
        //    46: aload_1        
        //    47: ldc             "Library"
        //    49: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getConstantFrom1:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: pop            
        //    56: aload_0        
        //    57: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getCallSite2:()Lorg/jruby/runtime/CallSite;
        //    60: aload_1        
        //    61: aload_2        
        //    62: aload_2        
        //    63: aload_0        
        //    64: aload_1        
        //    65: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    68: bipush          32
        //    70: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getString1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    73: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: pop            
        //    77: aload_0        
        //    78: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getCallSite3:()Lorg/jruby/runtime/CallSite;
        //    81: aload_1        
        //    82: aload_2        
        //    83: aload_2        
        //    84: aload_0        
        //    85: aload_1        
        //    86: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    89: ldc             "stdcall"
        //    91: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    94: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    97: pop            
        //    98: aload_0        
        //    99: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getCallSite4:()Lorg/jruby/runtime/CallSite;
        //   102: aload_1        
        //   103: aload_2        
        //   104: aload_2        
        //   105: aload_0        
        //   106: aload_1        
        //   107: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   110: ldc             "GetIpStatistics"
        //   112: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   115: aload_1        
        //   116: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   119: aload_0        
        //   120: aload_1        
        //   121: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   124: ldc             "pointer"
        //   126: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   129: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   132: aload_0        
        //   133: aload_1        
        //   134: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   137: ldc             "void"
        //   139: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   142: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   145: pop            
        //   146: aload_0        
        //   147: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getCallSite5:()Lorg/jruby/runtime/CallSite;
        //   150: aload_1        
        //   151: aload_2        
        //   152: aload_2        
        //   153: aload_0        
        //   154: aload_1        
        //   155: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   158: ldc             "GetBestRoute"
        //   160: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   163: aload_1        
        //   164: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   167: aload_0        
        //   168: aload_1        
        //   169: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   172: ldc             "uint"
        //   174: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   177: aload_0        
        //   178: aload_1        
        //   179: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   182: ldc             "uint"
        //   184: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   187: aload_0        
        //   188: aload_1        
        //   189: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   192: ldc             "pointer"
        //   194: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   197: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   200: aload_0        
        //   201: aload_1        
        //   202: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   205: ldc             "uint"
        //   207: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   210: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   213: pop            
        //   214: aload_0        
        //   215: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getCallSite6:()Lorg/jruby/runtime/CallSite;
        //   218: aload_1        
        //   219: aload_2        
        //   220: aload_2        
        //   221: aload_0        
        //   222: aload_1        
        //   223: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   226: ldc             "GetIfTable"
        //   228: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol6:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   231: aload_1        
        //   232: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   235: aload_0        
        //   236: aload_1        
        //   237: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   240: ldc             "pointer"
        //   242: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   245: aload_0        
        //   246: aload_1        
        //   247: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   250: ldc             "pointer"
        //   252: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   255: aload_0        
        //   256: aload_1        
        //   257: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   260: ldc             "bool"
        //   262: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol7:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   265: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   268: aload_0        
        //   269: aload_1        
        //   270: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   273: ldc             "uint"
        //   275: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   278: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   281: pop            
        //   282: aload_0        
        //   283: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getCallSite7:()Lorg/jruby/runtime/CallSite;
        //   286: aload_1        
        //   287: aload_2        
        //   288: aload_2        
        //   289: aload_0        
        //   290: aload_1        
        //   291: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   294: ldc             "GetTcpStatistics"
        //   296: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol8:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   299: aload_1        
        //   300: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   303: aload_0        
        //   304: aload_1        
        //   305: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   308: ldc             "pointer"
        //   310: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   313: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   316: aload_0        
        //   317: aload_1        
        //   318: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   321: ldc             "uint"
        //   323: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   326: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   329: pop            
        //   330: aload_0        
        //   331: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getCallSite8:()Lorg/jruby/runtime/CallSite;
        //   334: aload_1        
        //   335: aload_2        
        //   336: aload_2        
        //   337: aload_0        
        //   338: aload_1        
        //   339: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   342: ldc             "GetNetworkParams"
        //   344: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol9:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   347: aload_1        
        //   348: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   351: aload_0        
        //   352: aload_1        
        //   353: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   356: ldc             "pointer"
        //   358: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   361: aload_0        
        //   362: aload_1        
        //   363: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   366: ldc             "pointer"
        //   368: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   371: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   374: aload_0        
        //   375: aload_1        
        //   376: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   379: ldc             "uint"
        //   381: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   384: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   387: pop            
        //   388: aload_0        
        //   389: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getCallSite9:()Lorg/jruby/runtime/CallSite;
        //   392: aload_1        
        //   393: aload_2        
        //   394: aload_2        
        //   395: aload_0        
        //   396: aload_1        
        //   397: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   400: ldc             10
        //   402: ldc             "GetIpAddrTable"
        //   404: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   407: aload_1        
        //   408: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   411: aload_0        
        //   412: aload_1        
        //   413: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   416: ldc             "pointer"
        //   418: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   421: aload_0        
        //   422: aload_1        
        //   423: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   426: ldc             "pointer"
        //   428: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   431: aload_0        
        //   432: aload_1        
        //   433: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   436: ldc             "bool"
        //   438: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol7:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   441: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   444: aload_0        
        //   445: aload_1        
        //   446: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   449: ldc             "uint"
        //   451: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   454: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   457: pop            
        //   458: aload_0        
        //   459: bipush          10
        //   461: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   464: aload_1        
        //   465: aload_2        
        //   466: aload_2        
        //   467: aload_0        
        //   468: aload_1        
        //   469: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   472: ldc             11
        //   474: ldc             "GetIpNetTable"
        //   476: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   479: aload_1        
        //   480: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   483: aload_0        
        //   484: aload_1        
        //   485: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   488: ldc             "pointer"
        //   490: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   493: aload_0        
        //   494: aload_1        
        //   495: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   498: ldc             "pointer"
        //   500: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   503: aload_0        
        //   504: aload_1        
        //   505: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   508: ldc             "uint"
        //   510: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   513: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   516: aload_0        
        //   517: aload_1        
        //   518: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   521: ldc             "uint"
        //   523: invokevirtual   ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   526: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   529: aload_1        
        //   530: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   533: goto            541
        //   536: aload_1        
        //   537: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   540: athrow         
        //   541: aload_1        
        //   542: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   545: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     533    536    541    Any
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
    
    public static IRubyObject module__1$RUBY$IPHelper(final FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9 file_2DB8606B02EA4BB805CA2B5471B980BCE35303B9, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$IPHelper(file_2DB8606B02EA4BB805CA2B5471B980BCE35303B9, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Win32(final FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9 file_2DB8606B02EA4BB805CA2B5471B980BCE35303B9, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Win32(file_2DB8606B02EA4BB805CA2B5471B980BCE35303B9, threadContext, rubyObject, block);
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
        final FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9 file_2DB8606B02EA4BB805CA2B5471B980BCE35303B9 = new FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9();
        final String string = FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.class.getClassLoader().getResource("ruby/jit/FILE_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.class").toString();
        file_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_2DB8606B02EA4BB805CA2B5471B980BCE35303B9.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
