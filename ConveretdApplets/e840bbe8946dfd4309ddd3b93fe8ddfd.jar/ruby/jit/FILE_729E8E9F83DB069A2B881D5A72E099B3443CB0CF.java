// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.DynamicScope;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Arity;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF extends AbstractScript
{
    public FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF() {
        this.filename = "./lib//win32/mib/ipstats.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\ufffflayout\uffffF\ufffflayout\uffffF\uffff[]\uffffF\uffff[]\uffffN\uffffalgo_int\uffffV\uffffalgo_int\uffffV\ufffflayout\uffffF\uffffread_string\uffffN\uffffto_ptr\uffffN\uffff[]\uffffF\uffffmin\uffffN\uffff[]\uffffF\uffff>\uffffN\uffff-\uffffN\uffffread_string\uffffN\uffffto_ptr\uffffN\uffff[]\uffffF\uffffjoin\uffffN\uffffunpack\uffffN\uffffread_string\uffffN\uffffto_ptr\uffffN\uffff[]\uffffF\uffff*\uffffN\ufffflayout\uffffF\ufffflayout\uffffF\uffffinclude?\uffffN\uffff[]\uffffF\uffff[]\uffffF\uffffjoin\uffffN\uffffunpack\uffffN\uffffread_string\uffffN\uffffto_ptr\uffffN\uffff[]\uffffF\uffff*\uffffN\ufffflayout\uffffF\ufffflayout\uffffF\ufffflayout\uffffF\ufffflayout\uffffF\ufffflayout\uffffF\ufffflayout\uffffF\uffff+\uffffN\uffff+\uffffN\uffff+\uffffN\uffff[]\uffffN\uffff[]\uffffF\uffff==\uffffN\uffff[]\uffffF\uffff==\uffffN\uffff[]\uffffF\uffff==\uffffN\uffff[]\uffffF\uffff\rm\f\u0000*\u0000\u0000\u0000\u0000\u0000\u0000\u0000\f\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(6, "atm", this.getEncoding0());
        this.setByteList(7, "ieee80211", this.getEncoding0());
        this.setByteList(1, "other", this.getEncoding0());
        this.setByteList(5, "software_loopback", this.getEncoding0());
        this.setByteList(10, "H2", this.getEncoding0());
        this.setByteList(2, "ethernet_csmacd", this.getEncoding0());
        this.setByteList(11, ":", this.getEncoding0());
        this.setByteList(9, "ieee1394", this.getEncoding0());
        this.setByteList(4, "ppp", this.getEncoding0());
        this.setByteList(3, "iso88025_tokenring", this.getEncoding0());
        this.setByteList(0, "ffi", this.getEncoding0());
        this.setByteList(8, "tunnel", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite0().call(threadContext, rubyObject, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getString0(threadContext.runtime, 32));
        return module__0$RUBY$Win32(file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Win32(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.module__1$RUBY$MIB:(Lruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$MIB(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "MIB"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_1        
        //    28: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    31: aload_1        
        //    32: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    35: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    38: aload_0        
        //    39: aload_1        
        //    40: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    43: bipush          32
        //    45: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getString1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    48: aload_0        
        //    49: aload_1        
        //    50: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    53: bipush          6
        //    55: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getFixnum0:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //    58: aload_0        
        //    59: aload_1        
        //    60: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    63: bipush          32
        //    65: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getString2:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    68: aload_0        
        //    69: aload_1        
        //    70: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    73: bipush          9
        //    75: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getFixnum1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //    78: aload_0        
        //    79: aload_1        
        //    80: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    83: bipush          32
        //    85: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getString3:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    88: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructHash:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyHash;
        //    91: dup            
        //    92: aload_1        
        //    93: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    96: aload_0        
        //    97: aload_1        
        //    98: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   101: bipush          23
        //   103: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getFixnum2:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   106: aload_0        
        //   107: aload_1        
        //   108: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   111: bipush          32
        //   113: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getString4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   116: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   119: dup            
        //   120: aload_1        
        //   121: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   124: aload_0        
        //   125: aload_1        
        //   126: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   129: bipush          24
        //   131: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getFixnum3:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   134: aload_0        
        //   135: aload_1        
        //   136: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   139: bipush          32
        //   141: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getString5:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   144: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   147: dup            
        //   148: aload_1        
        //   149: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   152: aload_0        
        //   153: aload_1        
        //   154: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   157: bipush          37
        //   159: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getFixnum4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   162: aload_0        
        //   163: aload_1        
        //   164: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   167: bipush          32
        //   169: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getString6:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   172: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   175: dup            
        //   176: aload_1        
        //   177: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   180: aload_0        
        //   181: aload_1        
        //   182: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   185: bipush          71
        //   187: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getFixnum5:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   190: aload_0        
        //   191: aload_1        
        //   192: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   195: bipush          32
        //   197: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   200: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   203: dup            
        //   204: aload_1        
        //   205: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   208: aload_0        
        //   209: aload_1        
        //   210: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   213: sipush          131
        //   216: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getFixnum6:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   219: aload_0        
        //   220: aload_1        
        //   221: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   224: bipush          32
        //   226: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getString8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   229: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   232: dup            
        //   233: aload_1        
        //   234: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   237: aload_0        
        //   238: aload_1        
        //   239: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   242: sipush          144
        //   245: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getFixnum7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   248: aload_0        
        //   249: aload_1        
        //   250: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   253: bipush          32
        //   255: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getString9:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   258: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   261: aload_1        
        //   262: ldc             "IF_TYPES"
        //   264: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   267: pop            
        //   268: aload_0        
        //   269: aload_1        
        //   270: aload_0        
        //   271: aload_1        
        //   272: ldc_w           "FFI"
        //   275: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   278: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   281: aload_0        
        //   282: swap           
        //   283: aload_1        
        //   284: ldc_w           "Struct"
        //   287: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstantFrom1:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   290: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   293: invokestatic    ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.class_2$RUBY$IPStats:(Lruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   296: pop            
        //   297: aload_0        
        //   298: aload_1        
        //   299: aload_0        
        //   300: aload_1        
        //   301: ldc_w           "FFI"
        //   304: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   307: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   310: aload_0        
        //   311: swap           
        //   312: aload_1        
        //   313: ldc_w           "Struct"
        //   316: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstantFrom4:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   319: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   322: invokestatic    ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.class_3$RUBY$TCPStats:(Lruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   325: pop            
        //   326: aload_0        
        //   327: aload_1        
        //   328: aload_0        
        //   329: aload_1        
        //   330: ldc_w           "FFI"
        //   333: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   336: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   339: aload_0        
        //   340: swap           
        //   341: aload_1        
        //   342: ldc_w           "Struct"
        //   345: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstantFrom6:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   348: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   351: invokestatic    ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.class_6$RUBY$IPForwardRow:(Lruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   354: pop            
        //   355: aload_0        
        //   356: aload_1        
        //   357: aload_0        
        //   358: aload_1        
        //   359: ldc_w           "FFI"
        //   362: bipush          12
        //   364: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   367: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   370: aload_0        
        //   371: swap           
        //   372: aload_1        
        //   373: ldc_w           "Struct"
        //   376: bipush          13
        //   378: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   381: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   384: invokestatic    ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.class_7$RUBY$IfRow:(Lruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   387: pop            
        //   388: aload_0        
        //   389: aload_1        
        //   390: aload_0        
        //   391: aload_1        
        //   392: ldc_w           "FFI"
        //   395: bipush          17
        //   397: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   400: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   403: aload_0        
        //   404: swap           
        //   405: aload_1        
        //   406: ldc_w           "Struct"
        //   409: bipush          18
        //   411: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   414: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   417: invokestatic    ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.class_11$RUBY$IpNetRow:(Lruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   420: pop            
        //   421: aload_0        
        //   422: aload_1        
        //   423: aload_0        
        //   424: aload_1        
        //   425: ldc_w           "FFI"
        //   428: bipush          19
        //   430: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   433: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   436: aload_0        
        //   437: swap           
        //   438: aload_1        
        //   439: ldc_w           "Struct"
        //   442: bipush          20
        //   444: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   447: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   450: invokestatic    ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.class_15$RUBY$IpAddrRow:(Lruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   453: pop            
        //   454: aload_0        
        //   455: aload_1        
        //   456: aload_0        
        //   457: aload_1        
        //   458: ldc_w           "FFI"
        //   461: bipush          23
        //   463: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   466: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   469: aload_0        
        //   470: swap           
        //   471: aload_1        
        //   472: ldc_w           "Struct"
        //   475: bipush          24
        //   477: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   480: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   483: invokestatic    ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.class_16$RUBY$IpAddrTable:(Lruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   486: pop            
        //   487: aload_0        
        //   488: aload_1        
        //   489: aload_0        
        //   490: aload_1        
        //   491: ldc_w           "FFI"
        //   494: bipush          27
        //   496: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   499: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   502: aload_0        
        //   503: swap           
        //   504: aload_1        
        //   505: ldc_w           "Struct"
        //   508: bipush          28
        //   510: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   513: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   516: invokestatic    ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.class_17$RUBY$IpNetTable:(Lruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   519: pop            
        //   520: aload_0        
        //   521: aload_1        
        //   522: aload_0        
        //   523: aload_1        
        //   524: ldc_w           "FFI"
        //   527: bipush          31
        //   529: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   532: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   535: aload_0        
        //   536: swap           
        //   537: aload_1        
        //   538: ldc_w           "Struct"
        //   541: bipush          32
        //   543: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   546: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   549: invokestatic    ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.class_18$RUBY$IfTable:(Lruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   552: pop            
        //   553: aload_0        
        //   554: aload_1        
        //   555: aload_0        
        //   556: aload_1        
        //   557: ldc_w           "FFI"
        //   560: bipush          33
        //   562: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   565: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   568: aload_0        
        //   569: swap           
        //   570: aload_1        
        //   571: ldc_w           "Struct"
        //   574: bipush          34
        //   576: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   579: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   582: invokestatic    ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.class_19$RUBY$IpAddrString:(Lruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   585: pop            
        //   586: aload_0        
        //   587: aload_1        
        //   588: aload_0        
        //   589: aload_1        
        //   590: ldc_w           "FFI"
        //   593: bipush          40
        //   595: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   598: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   601: aload_0        
        //   602: swap           
        //   603: aload_1        
        //   604: ldc_w           "Struct"
        //   607: bipush          41
        //   609: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   612: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   615: invokestatic    ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.class_20$RUBY$FixedInfo:(Lruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   618: aload_1        
        //   619: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   622: goto            630
        //   625: aload_1        
        //   626: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   629: athrow         
        //   630: aload_1        
        //   631: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   634: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     622    625    630    Any
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
    
    public static IRubyObject class_2$RUBY$IPStats(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //     4: aload_2        
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareSuperClass:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyClass;
        //     8: aload_1        
        //     9: aload_1        
        //    10: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    13: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    16: swap           
        //    17: ldc             "IPStats"
        //    19: swap           
        //    20: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    23: dup            
        //    24: astore_2       
        //    25: aload_1        
        //    26: swap           
        //    27: aload_0        
        //    28: aload_1        
        //    29: ldc             ",0,0,-1"
        //    31: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    34: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    37: aload_0        
        //    38: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite1:()Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_2        
        //    44: bipush          46
        //    46: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: dup            
        //    50: iconst_0       
        //    51: aload_0        
        //    52: aload_1        
        //    53: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    56: ldc             "dwForwarding"
        //    58: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    61: aastore        
        //    62: dup            
        //    63: iconst_1       
        //    64: aload_0        
        //    65: aload_1        
        //    66: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    69: ldc             "int"
        //    71: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    74: aastore        
        //    75: dup            
        //    76: iconst_2       
        //    77: aload_0        
        //    78: aload_1        
        //    79: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    82: ldc             "dwDefaultTTL"
        //    84: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    87: aastore        
        //    88: dup            
        //    89: iconst_3       
        //    90: aload_0        
        //    91: aload_1        
        //    92: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    95: ldc             "int"
        //    97: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   100: aastore        
        //   101: dup            
        //   102: iconst_4       
        //   103: aload_0        
        //   104: aload_1        
        //   105: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   108: ldc             "dwInReceives"
        //   110: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   113: aastore        
        //   114: dup            
        //   115: iconst_5       
        //   116: aload_0        
        //   117: aload_1        
        //   118: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   121: ldc             "int"
        //   123: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   126: aastore        
        //   127: dup            
        //   128: bipush          6
        //   130: aload_0        
        //   131: aload_1        
        //   132: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   135: ldc             "dwInHdrErrors"
        //   137: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   140: aastore        
        //   141: dup            
        //   142: bipush          7
        //   144: aload_0        
        //   145: aload_1        
        //   146: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   149: ldc             "int"
        //   151: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   154: aastore        
        //   155: dup            
        //   156: bipush          8
        //   158: aload_0        
        //   159: aload_1        
        //   160: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   163: ldc             "dwInAddrErrors"
        //   165: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   168: aastore        
        //   169: dup            
        //   170: bipush          9
        //   172: aload_0        
        //   173: aload_1        
        //   174: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   177: ldc             "int"
        //   179: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   182: aastore        
        //   183: dup            
        //   184: bipush          10
        //   186: aload_0        
        //   187: aload_1        
        //   188: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   191: ldc             "dwForwDatagrams"
        //   193: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol6:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   196: aastore        
        //   197: dup            
        //   198: bipush          11
        //   200: aload_0        
        //   201: aload_1        
        //   202: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   205: ldc             "int"
        //   207: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   210: aastore        
        //   211: dup            
        //   212: bipush          12
        //   214: aload_0        
        //   215: aload_1        
        //   216: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   219: ldc             "dwInUnknownProtos"
        //   221: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol7:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   224: aastore        
        //   225: dup            
        //   226: bipush          13
        //   228: aload_0        
        //   229: aload_1        
        //   230: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   233: ldc             "int"
        //   235: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   238: aastore        
        //   239: dup            
        //   240: bipush          14
        //   242: aload_0        
        //   243: aload_1        
        //   244: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   247: ldc             "dwInDiscards"
        //   249: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol8:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   252: aastore        
        //   253: dup            
        //   254: bipush          15
        //   256: aload_0        
        //   257: aload_1        
        //   258: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   261: ldc             "int"
        //   263: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   266: aastore        
        //   267: dup            
        //   268: bipush          16
        //   270: aload_0        
        //   271: aload_1        
        //   272: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   275: ldc             "dwInDelivers"
        //   277: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol9:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   280: aastore        
        //   281: dup            
        //   282: bipush          17
        //   284: aload_0        
        //   285: aload_1        
        //   286: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   289: ldc             "int"
        //   291: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   294: aastore        
        //   295: dup            
        //   296: bipush          18
        //   298: aload_0        
        //   299: aload_1        
        //   300: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   303: ldc             10
        //   305: ldc             "dwOutRequests"
        //   307: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   310: aastore        
        //   311: dup            
        //   312: bipush          19
        //   314: aload_0        
        //   315: aload_1        
        //   316: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   319: ldc             "int"
        //   321: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   324: aastore        
        //   325: dup            
        //   326: bipush          20
        //   328: aload_0        
        //   329: aload_1        
        //   330: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   333: ldc             11
        //   335: ldc             "dwRoutingDiscards"
        //   337: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   340: aastore        
        //   341: dup            
        //   342: bipush          21
        //   344: aload_0        
        //   345: aload_1        
        //   346: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   349: ldc             "int"
        //   351: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   354: aastore        
        //   355: dup            
        //   356: bipush          22
        //   358: aload_0        
        //   359: aload_1        
        //   360: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   363: ldc             12
        //   365: ldc             "dwOutDiscards"
        //   367: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   370: aastore        
        //   371: dup            
        //   372: bipush          23
        //   374: aload_0        
        //   375: aload_1        
        //   376: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   379: ldc             "int"
        //   381: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   384: aastore        
        //   385: dup            
        //   386: bipush          24
        //   388: aload_0        
        //   389: aload_1        
        //   390: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   393: ldc             13
        //   395: ldc             "dwOutNoRoutes"
        //   397: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   400: aastore        
        //   401: dup            
        //   402: bipush          25
        //   404: aload_0        
        //   405: aload_1        
        //   406: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   409: ldc             "int"
        //   411: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   414: aastore        
        //   415: dup            
        //   416: bipush          26
        //   418: aload_0        
        //   419: aload_1        
        //   420: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   423: ldc             14
        //   425: ldc             "dwReasmTimeout"
        //   427: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   430: aastore        
        //   431: dup            
        //   432: bipush          27
        //   434: aload_0        
        //   435: aload_1        
        //   436: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   439: ldc             "int"
        //   441: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   444: aastore        
        //   445: dup            
        //   446: bipush          28
        //   448: aload_0        
        //   449: aload_1        
        //   450: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   453: ldc             15
        //   455: ldc             "dwReasmReqds"
        //   457: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   460: aastore        
        //   461: dup            
        //   462: bipush          29
        //   464: aload_0        
        //   465: aload_1        
        //   466: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   469: ldc             "int"
        //   471: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   474: aastore        
        //   475: dup            
        //   476: bipush          30
        //   478: aload_0        
        //   479: aload_1        
        //   480: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   483: ldc             16
        //   485: ldc             "dwReasmOks"
        //   487: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   490: aastore        
        //   491: dup            
        //   492: bipush          31
        //   494: aload_0        
        //   495: aload_1        
        //   496: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   499: ldc             "int"
        //   501: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   504: aastore        
        //   505: dup            
        //   506: bipush          32
        //   508: aload_0        
        //   509: aload_1        
        //   510: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   513: ldc             17
        //   515: ldc             "dwReasmFails"
        //   517: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   520: aastore        
        //   521: dup            
        //   522: bipush          33
        //   524: aload_0        
        //   525: aload_1        
        //   526: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   529: ldc             "int"
        //   531: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   534: aastore        
        //   535: dup            
        //   536: bipush          34
        //   538: aload_0        
        //   539: aload_1        
        //   540: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   543: ldc             18
        //   545: ldc_w           "dwFragOks"
        //   548: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   551: aastore        
        //   552: dup            
        //   553: bipush          35
        //   555: aload_0        
        //   556: aload_1        
        //   557: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   560: ldc             "int"
        //   562: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   565: aastore        
        //   566: dup            
        //   567: bipush          36
        //   569: aload_0        
        //   570: aload_1        
        //   571: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   574: ldc_w           19
        //   577: ldc_w           "dwFragFails"
        //   580: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   583: aastore        
        //   584: dup            
        //   585: bipush          37
        //   587: aload_0        
        //   588: aload_1        
        //   589: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   592: ldc             "int"
        //   594: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   597: aastore        
        //   598: dup            
        //   599: bipush          38
        //   601: aload_0        
        //   602: aload_1        
        //   603: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   606: ldc_w           20
        //   609: ldc_w           "dwFragCreates"
        //   612: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   615: aastore        
        //   616: dup            
        //   617: bipush          39
        //   619: aload_0        
        //   620: aload_1        
        //   621: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   624: ldc             "int"
        //   626: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   629: aastore        
        //   630: dup            
        //   631: bipush          40
        //   633: aload_0        
        //   634: aload_1        
        //   635: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   638: ldc_w           21
        //   641: ldc_w           "dwNumIf"
        //   644: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   647: aastore        
        //   648: dup            
        //   649: bipush          41
        //   651: aload_0        
        //   652: aload_1        
        //   653: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   656: ldc             "int"
        //   658: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   661: aastore        
        //   662: dup            
        //   663: bipush          42
        //   665: aload_0        
        //   666: aload_1        
        //   667: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   670: ldc_w           22
        //   673: ldc_w           "dwNumAddr"
        //   676: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   679: aastore        
        //   680: dup            
        //   681: bipush          43
        //   683: aload_0        
        //   684: aload_1        
        //   685: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   688: ldc             "int"
        //   690: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   693: aastore        
        //   694: dup            
        //   695: bipush          44
        //   697: aload_0        
        //   698: aload_1        
        //   699: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   702: ldc_w           23
        //   705: ldc_w           "dwNumRoutes"
        //   708: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   711: aastore        
        //   712: dup            
        //   713: bipush          45
        //   715: aload_0        
        //   716: aload_1        
        //   717: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   720: ldc             "int"
        //   722: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   725: aastore        
        //   726: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   729: aload_1        
        //   730: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   733: goto            741
        //   736: aload_1        
        //   737: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   740: athrow         
        //   741: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  37     729    736    741    Any
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
    
    public static IRubyObject class_2$RUBY$IPStats(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_2$RUBY$IPStats(file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_3$RUBY$TCPStats(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //     4: aload_2        
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareSuperClass:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyClass;
        //     8: aload_1        
        //     9: aload_1        
        //    10: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    13: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    16: swap           
        //    17: ldc_w           "TCPStats"
        //    20: swap           
        //    21: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    24: dup            
        //    25: astore_2       
        //    26: aload_1        
        //    27: swap           
        //    28: aload_0        
        //    29: aload_1        
        //    30: ldc             ",0,0,-1"
        //    32: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getScope3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    35: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    38: aload_1        
        //    39: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    42: aload_1        
        //    43: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    46: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    49: aload_0        
        //    50: aload_1        
        //    51: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    54: ldc_w           24
        //    57: ldc_w           "other"
        //    60: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    63: aload_1        
        //    64: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    67: invokestatic    org/jruby/RubyFixnum.two:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    70: aload_0        
        //    71: aload_1        
        //    72: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    75: ldc_w           25
        //    78: ldc_w           "constant"
        //    81: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    84: aload_1        
        //    85: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    88: invokestatic    org/jruby/RubyFixnum.three:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    91: aload_0        
        //    92: aload_1        
        //    93: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    96: ldc_w           26
        //    99: ldc_w           "mil_std_1778"
        //   102: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   105: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructHash:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyHash;
        //   108: dup            
        //   109: aload_1        
        //   110: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   113: aload_1        
        //   114: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   117: invokestatic    org/jruby/RubyFixnum.four:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   120: aload_0        
        //   121: aload_1        
        //   122: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   125: ldc_w           27
        //   128: ldc_w           "van_jacobson"
        //   131: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   134: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   137: aload_1        
        //   138: ldc_w           "ALGOS"
        //   141: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   144: pop            
        //   145: aload_0        
        //   146: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite2:()Lorg/jruby/runtime/CallSite;
        //   149: aload_1        
        //   150: aload_2        
        //   151: aload_2        
        //   152: bipush          30
        //   154: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //   157: dup            
        //   158: iconst_0       
        //   159: aload_0        
        //   160: aload_1        
        //   161: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   164: ldc_w           28
        //   167: ldc_w           "dwRtoAlgorithm"
        //   170: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   173: aastore        
        //   174: dup            
        //   175: iconst_1       
        //   176: aload_0        
        //   177: aload_1        
        //   178: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   181: ldc_w           29
        //   184: ldc_w           "uint"
        //   187: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   190: aastore        
        //   191: dup            
        //   192: iconst_2       
        //   193: aload_0        
        //   194: aload_1        
        //   195: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   198: ldc_w           30
        //   201: ldc_w           "dwRtoMin"
        //   204: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   207: aastore        
        //   208: dup            
        //   209: iconst_3       
        //   210: aload_0        
        //   211: aload_1        
        //   212: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   215: ldc_w           29
        //   218: ldc_w           "uint"
        //   221: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   224: aastore        
        //   225: dup            
        //   226: iconst_4       
        //   227: aload_0        
        //   228: aload_1        
        //   229: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   232: ldc_w           31
        //   235: ldc_w           "dwRtoMax"
        //   238: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   241: aastore        
        //   242: dup            
        //   243: iconst_5       
        //   244: aload_0        
        //   245: aload_1        
        //   246: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   249: ldc_w           29
        //   252: ldc_w           "uint"
        //   255: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   258: aastore        
        //   259: dup            
        //   260: bipush          6
        //   262: aload_0        
        //   263: aload_1        
        //   264: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   267: ldc_w           32
        //   270: ldc_w           "dwMaxConn"
        //   273: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   276: aastore        
        //   277: dup            
        //   278: bipush          7
        //   280: aload_0        
        //   281: aload_1        
        //   282: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   285: ldc_w           29
        //   288: ldc_w           "uint"
        //   291: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   294: aastore        
        //   295: dup            
        //   296: bipush          8
        //   298: aload_0        
        //   299: aload_1        
        //   300: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   303: ldc_w           33
        //   306: ldc_w           "dwActiveOpens"
        //   309: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   312: aastore        
        //   313: dup            
        //   314: bipush          9
        //   316: aload_0        
        //   317: aload_1        
        //   318: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   321: ldc_w           29
        //   324: ldc_w           "uint"
        //   327: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   330: aastore        
        //   331: dup            
        //   332: bipush          10
        //   334: aload_0        
        //   335: aload_1        
        //   336: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   339: ldc_w           34
        //   342: ldc_w           "dwPassiveOpens"
        //   345: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   348: aastore        
        //   349: dup            
        //   350: bipush          11
        //   352: aload_0        
        //   353: aload_1        
        //   354: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   357: ldc_w           29
        //   360: ldc_w           "uint"
        //   363: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   366: aastore        
        //   367: dup            
        //   368: bipush          12
        //   370: aload_0        
        //   371: aload_1        
        //   372: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   375: ldc_w           35
        //   378: ldc_w           "dwAttemptFails"
        //   381: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   384: aastore        
        //   385: dup            
        //   386: bipush          13
        //   388: aload_0        
        //   389: aload_1        
        //   390: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   393: ldc_w           29
        //   396: ldc_w           "uint"
        //   399: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   402: aastore        
        //   403: dup            
        //   404: bipush          14
        //   406: aload_0        
        //   407: aload_1        
        //   408: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   411: ldc_w           36
        //   414: ldc_w           "dwEstabResets"
        //   417: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   420: aastore        
        //   421: dup            
        //   422: bipush          15
        //   424: aload_0        
        //   425: aload_1        
        //   426: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   429: ldc_w           29
        //   432: ldc_w           "uint"
        //   435: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   438: aastore        
        //   439: dup            
        //   440: bipush          16
        //   442: aload_0        
        //   443: aload_1        
        //   444: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   447: ldc_w           37
        //   450: ldc_w           "dwCurrEstab"
        //   453: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   456: aastore        
        //   457: dup            
        //   458: bipush          17
        //   460: aload_0        
        //   461: aload_1        
        //   462: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   465: ldc_w           29
        //   468: ldc_w           "uint"
        //   471: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   474: aastore        
        //   475: dup            
        //   476: bipush          18
        //   478: aload_0        
        //   479: aload_1        
        //   480: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   483: ldc_w           38
        //   486: ldc_w           "dwInSegs"
        //   489: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   492: aastore        
        //   493: dup            
        //   494: bipush          19
        //   496: aload_0        
        //   497: aload_1        
        //   498: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   501: ldc_w           29
        //   504: ldc_w           "uint"
        //   507: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   510: aastore        
        //   511: dup            
        //   512: bipush          20
        //   514: aload_0        
        //   515: aload_1        
        //   516: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   519: ldc_w           39
        //   522: ldc_w           "dwOutSegs"
        //   525: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   528: aastore        
        //   529: dup            
        //   530: bipush          21
        //   532: aload_0        
        //   533: aload_1        
        //   534: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   537: ldc_w           29
        //   540: ldc_w           "uint"
        //   543: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   546: aastore        
        //   547: dup            
        //   548: bipush          22
        //   550: aload_0        
        //   551: aload_1        
        //   552: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   555: ldc_w           40
        //   558: ldc_w           "dwRetransSegs"
        //   561: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   564: aastore        
        //   565: dup            
        //   566: bipush          23
        //   568: aload_0        
        //   569: aload_1        
        //   570: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   573: ldc_w           29
        //   576: ldc_w           "uint"
        //   579: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   582: aastore        
        //   583: dup            
        //   584: bipush          24
        //   586: aload_0        
        //   587: aload_1        
        //   588: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   591: ldc_w           41
        //   594: ldc_w           "dwInErrs"
        //   597: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   600: aastore        
        //   601: dup            
        //   602: bipush          25
        //   604: aload_0        
        //   605: aload_1        
        //   606: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   609: ldc_w           29
        //   612: ldc_w           "uint"
        //   615: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   618: aastore        
        //   619: dup            
        //   620: bipush          26
        //   622: aload_0        
        //   623: aload_1        
        //   624: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   627: ldc_w           42
        //   630: ldc_w           "dwOutRsts"
        //   633: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   636: aastore        
        //   637: dup            
        //   638: bipush          27
        //   640: aload_0        
        //   641: aload_1        
        //   642: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   645: ldc_w           29
        //   648: ldc_w           "uint"
        //   651: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   654: aastore        
        //   655: dup            
        //   656: bipush          28
        //   658: aload_0        
        //   659: aload_1        
        //   660: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   663: ldc_w           43
        //   666: ldc_w           "dwNumConns"
        //   669: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   672: aastore        
        //   673: dup            
        //   674: bipush          29
        //   676: aload_0        
        //   677: aload_1        
        //   678: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   681: ldc_w           29
        //   684: ldc_w           "uint"
        //   687: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   690: aastore        
        //   691: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   694: pop            
        //   695: aload_1        
        //   696: aload_2        
        //   697: aload_0        
        //   698: ldc_w           "algo_int"
        //   701: ldc_w           "method__4$RUBY$algo_int"
        //   704: ldc             ",0,0,-1"
        //   706: iconst_0       
        //   707: ldc             "./lib//win32/mib/ipstats.rb"
        //   709: ldc_w           64
        //   712: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   715: ldc_w           "NONE"
        //   718: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   721: pop            
        //   722: aload_1        
        //   723: aload_2        
        //   724: aload_0        
        //   725: ldc_w           "algo"
        //   728: ldc_w           "method__5$RUBY$algo"
        //   731: ldc             ",0,0,-1"
        //   733: iconst_0       
        //   734: ldc             "./lib//win32/mib/ipstats.rb"
        //   736: ldc_w           68
        //   739: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   742: ldc_w           "NONE"
        //   745: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   748: aload_1        
        //   749: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   752: goto            760
        //   755: aload_1        
        //   756: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   759: athrow         
        //   760: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  38     748    755    760    Any
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
    
    @JRubyMethod(name = "algo_int", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$algo_int(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite3().call(threadContext, rubyObject, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol(threadContext.runtime, 28, "dwRtoAlgorithm"));
    }
    
    @JRubyMethod(name = "algo", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$algo(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        IRubyObject rubyObject2;
        if (!(rubyObject2 = file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite4().call(context, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant2(context, "ALGOS"), file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite5().call(context, rubyObject, rubyObject))).isTrue()) {
            rubyObject2 = file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite6().call(context, rubyObject, rubyObject);
        }
        return rubyObject2;
    }
    
    public static IRubyObject class_3$RUBY$TCPStats(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_3$RUBY$TCPStats(file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_6$RUBY$IPForwardRow(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //     4: aload_2        
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareSuperClass:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyClass;
        //     8: aload_1        
        //     9: aload_1        
        //    10: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    13: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    16: swap           
        //    17: ldc_w           "IPForwardRow"
        //    20: swap           
        //    21: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    24: dup            
        //    25: astore_2       
        //    26: aload_1        
        //    27: swap           
        //    28: aload_0        
        //    29: aload_1        
        //    30: ldc             ",0,0,-1"
        //    32: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getScope4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    35: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    38: aload_0        
        //    39: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite7:()Lorg/jruby/runtime/CallSite;
        //    42: aload_1        
        //    43: aload_2        
        //    44: aload_2        
        //    45: bipush          28
        //    47: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: dup            
        //    51: iconst_0       
        //    52: aload_0        
        //    53: aload_1        
        //    54: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    57: ldc_w           44
        //    60: ldc_w           "dwForwardDest"
        //    63: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    66: aastore        
        //    67: dup            
        //    68: iconst_1       
        //    69: aload_0        
        //    70: aload_1        
        //    71: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    74: ldc_w           29
        //    77: ldc_w           "uint"
        //    80: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    83: aastore        
        //    84: dup            
        //    85: iconst_2       
        //    86: aload_0        
        //    87: aload_1        
        //    88: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    91: ldc_w           45
        //    94: ldc_w           "dwForwardMask"
        //    97: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   100: aastore        
        //   101: dup            
        //   102: iconst_3       
        //   103: aload_0        
        //   104: aload_1        
        //   105: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   108: ldc_w           29
        //   111: ldc_w           "uint"
        //   114: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   117: aastore        
        //   118: dup            
        //   119: iconst_4       
        //   120: aload_0        
        //   121: aload_1        
        //   122: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   125: ldc_w           46
        //   128: ldc_w           "dwForwardPolicy"
        //   131: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   134: aastore        
        //   135: dup            
        //   136: iconst_5       
        //   137: aload_0        
        //   138: aload_1        
        //   139: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   142: ldc_w           29
        //   145: ldc_w           "uint"
        //   148: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   151: aastore        
        //   152: dup            
        //   153: bipush          6
        //   155: aload_0        
        //   156: aload_1        
        //   157: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   160: ldc_w           47
        //   163: ldc_w           "dwForwardNextHop"
        //   166: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   169: aastore        
        //   170: dup            
        //   171: bipush          7
        //   173: aload_0        
        //   174: aload_1        
        //   175: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   178: ldc_w           29
        //   181: ldc_w           "uint"
        //   184: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   187: aastore        
        //   188: dup            
        //   189: bipush          8
        //   191: aload_0        
        //   192: aload_1        
        //   193: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   196: ldc_w           48
        //   199: ldc_w           "dwForwardIfIndex"
        //   202: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   205: aastore        
        //   206: dup            
        //   207: bipush          9
        //   209: aload_0        
        //   210: aload_1        
        //   211: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   214: ldc_w           29
        //   217: ldc_w           "uint"
        //   220: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   223: aastore        
        //   224: dup            
        //   225: bipush          10
        //   227: aload_0        
        //   228: aload_1        
        //   229: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   232: ldc_w           49
        //   235: ldc_w           "dwForwardType"
        //   238: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   241: aastore        
        //   242: dup            
        //   243: bipush          11
        //   245: aload_0        
        //   246: aload_1        
        //   247: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   250: ldc_w           29
        //   253: ldc_w           "uint"
        //   256: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   259: aastore        
        //   260: dup            
        //   261: bipush          12
        //   263: aload_0        
        //   264: aload_1        
        //   265: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   268: ldc_w           50
        //   271: ldc_w           "dwForwardProto"
        //   274: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   277: aastore        
        //   278: dup            
        //   279: bipush          13
        //   281: aload_0        
        //   282: aload_1        
        //   283: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   286: ldc_w           29
        //   289: ldc_w           "uint"
        //   292: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   295: aastore        
        //   296: dup            
        //   297: bipush          14
        //   299: aload_0        
        //   300: aload_1        
        //   301: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   304: ldc_w           51
        //   307: ldc_w           "dwForwardAge"
        //   310: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   313: aastore        
        //   314: dup            
        //   315: bipush          15
        //   317: aload_0        
        //   318: aload_1        
        //   319: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   322: ldc_w           29
        //   325: ldc_w           "uint"
        //   328: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   331: aastore        
        //   332: dup            
        //   333: bipush          16
        //   335: aload_0        
        //   336: aload_1        
        //   337: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   340: ldc_w           52
        //   343: ldc_w           "dwForwardNextHopAS"
        //   346: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   349: aastore        
        //   350: dup            
        //   351: bipush          17
        //   353: aload_0        
        //   354: aload_1        
        //   355: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   358: ldc_w           29
        //   361: ldc_w           "uint"
        //   364: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   367: aastore        
        //   368: dup            
        //   369: bipush          18
        //   371: aload_0        
        //   372: aload_1        
        //   373: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   376: ldc_w           53
        //   379: ldc_w           "dwForwardMetric1"
        //   382: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   385: aastore        
        //   386: dup            
        //   387: bipush          19
        //   389: aload_0        
        //   390: aload_1        
        //   391: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   394: ldc_w           29
        //   397: ldc_w           "uint"
        //   400: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   403: aastore        
        //   404: dup            
        //   405: bipush          20
        //   407: aload_0        
        //   408: aload_1        
        //   409: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   412: ldc_w           54
        //   415: ldc_w           "dwForwardMetric2"
        //   418: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   421: aastore        
        //   422: dup            
        //   423: bipush          21
        //   425: aload_0        
        //   426: aload_1        
        //   427: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   430: ldc_w           29
        //   433: ldc_w           "uint"
        //   436: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   439: aastore        
        //   440: dup            
        //   441: bipush          22
        //   443: aload_0        
        //   444: aload_1        
        //   445: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   448: ldc_w           55
        //   451: ldc_w           "dwForwardMetric3"
        //   454: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   457: aastore        
        //   458: dup            
        //   459: bipush          23
        //   461: aload_0        
        //   462: aload_1        
        //   463: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   466: ldc_w           29
        //   469: ldc_w           "uint"
        //   472: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   475: aastore        
        //   476: dup            
        //   477: bipush          24
        //   479: aload_0        
        //   480: aload_1        
        //   481: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   484: ldc_w           56
        //   487: ldc_w           "dwForwardMetric4"
        //   490: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   493: aastore        
        //   494: dup            
        //   495: bipush          25
        //   497: aload_0        
        //   498: aload_1        
        //   499: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   502: ldc_w           29
        //   505: ldc_w           "uint"
        //   508: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   511: aastore        
        //   512: dup            
        //   513: bipush          26
        //   515: aload_0        
        //   516: aload_1        
        //   517: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   520: ldc_w           57
        //   523: ldc_w           "dwForwardMetric5"
        //   526: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   529: aastore        
        //   530: dup            
        //   531: bipush          27
        //   533: aload_0        
        //   534: aload_1        
        //   535: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   538: ldc_w           29
        //   541: ldc_w           "uint"
        //   544: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   547: aastore        
        //   548: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   551: aload_1        
        //   552: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   555: goto            563
        //   558: aload_1        
        //   559: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   562: athrow         
        //   563: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  38     551    558    563    Any
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
    
    public static IRubyObject class_6$RUBY$IPForwardRow(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_6$RUBY$IPForwardRow(file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_7$RUBY$IfRow(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //     4: aload_2        
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareSuperClass:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyClass;
        //     8: aload_1        
        //     9: aload_1        
        //    10: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    13: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    16: swap           
        //    17: ldc_w           "IfRow"
        //    20: swap           
        //    21: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    24: dup            
        //    25: astore_2       
        //    26: aload_1        
        //    27: swap           
        //    28: aload_0        
        //    29: aload_1        
        //    30: ldc             ",0,0,-1"
        //    32: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getScope5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    35: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    38: aload_0        
        //    39: aload_1        
        //    40: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    43: sipush          256
        //    46: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getFixnum8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //    49: aload_1        
        //    50: ldc_w           "MAX_INTERFACE_NAME_LEN"
        //    53: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    56: pop            
        //    57: aload_0        
        //    58: aload_1        
        //    59: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    62: bipush          8
        //    64: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getFixnum9:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //    67: aload_1        
        //    68: ldc_w           "MAXLEN_PHYSADDR"
        //    71: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: pop            
        //    75: aload_0        
        //    76: aload_1        
        //    77: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    80: sipush          256
        //    83: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getFixnum8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //    86: aload_1        
        //    87: ldc_w           "MAXLEN_IFDESCR"
        //    90: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    93: pop            
        //    94: aload_1        
        //    95: aload_2        
        //    96: aload_0        
        //    97: ldc_w           "name"
        //   100: ldc_w           "method__8$RUBY$name"
        //   103: ldc             ",0,0,-1"
        //   105: iconst_0       
        //   106: ldc             "./lib//win32/mib/ipstats.rb"
        //   108: ldc_w           96
        //   111: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   114: ldc_w           "NONE"
        //   117: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   120: pop            
        //   121: aload_1        
        //   122: aload_2        
        //   123: aload_0        
        //   124: ldc_w           "description"
        //   127: ldc_w           "method__9$RUBY$description"
        //   130: ldc_w           "len,0,0,-1"
        //   133: iconst_0       
        //   134: ldc             "./lib//win32/mib/ipstats.rb"
        //   136: ldc_w           101
        //   139: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   142: ldc_w           "NONE"
        //   145: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   148: pop            
        //   149: aload_1        
        //   150: aload_2        
        //   151: aload_0        
        //   152: ldc_w           "mac_addr"
        //   155: ldc_w           "method__10$RUBY$mac_addr"
        //   158: ldc             ",0,0,-1"
        //   160: iconst_0       
        //   161: ldc             "./lib//win32/mib/ipstats.rb"
        //   163: ldc_w           107
        //   166: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   169: ldc_w           "NONE"
        //   172: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   175: pop            
        //   176: aload_0        
        //   177: bipush          24
        //   179: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   182: aload_1        
        //   183: aload_2        
        //   184: aload_2        
        //   185: bipush          48
        //   187: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //   190: dup            
        //   191: iconst_0       
        //   192: aload_0        
        //   193: aload_1        
        //   194: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   197: ldc_w           58
        //   200: ldc_w           "wszName"
        //   203: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   206: aastore        
        //   207: dup            
        //   208: iconst_1       
        //   209: aload_1        
        //   210: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   213: aload_0        
        //   214: aload_1        
        //   215: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   218: ldc_w           62
        //   221: ldc_w           "uint16"
        //   224: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   227: aload_0        
        //   228: aload_1        
        //   229: ldc_w           "MAX_INTERFACE_NAME_LEN"
        //   232: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant9:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   235: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   238: aastore        
        //   239: dup            
        //   240: iconst_2       
        //   241: aload_0        
        //   242: aload_1        
        //   243: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   246: ldc_w           63
        //   249: ldc_w           "dwIndex"
        //   252: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   255: aastore        
        //   256: dup            
        //   257: iconst_3       
        //   258: aload_0        
        //   259: aload_1        
        //   260: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   263: ldc_w           29
        //   266: ldc_w           "uint"
        //   269: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   272: aastore        
        //   273: dup            
        //   274: iconst_4       
        //   275: aload_0        
        //   276: aload_1        
        //   277: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   280: ldc_w           64
        //   283: ldc_w           "dwType"
        //   286: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   289: aastore        
        //   290: dup            
        //   291: iconst_5       
        //   292: aload_0        
        //   293: aload_1        
        //   294: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   297: ldc_w           29
        //   300: ldc_w           "uint"
        //   303: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   306: aastore        
        //   307: dup            
        //   308: bipush          6
        //   310: aload_0        
        //   311: aload_1        
        //   312: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   315: ldc_w           65
        //   318: ldc_w           "dwMtu"
        //   321: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   324: aastore        
        //   325: dup            
        //   326: bipush          7
        //   328: aload_0        
        //   329: aload_1        
        //   330: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   333: ldc_w           29
        //   336: ldc_w           "uint"
        //   339: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   342: aastore        
        //   343: dup            
        //   344: bipush          8
        //   346: aload_0        
        //   347: aload_1        
        //   348: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   351: ldc_w           66
        //   354: ldc_w           "dwSpeed"
        //   357: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   360: aastore        
        //   361: dup            
        //   362: bipush          9
        //   364: aload_0        
        //   365: aload_1        
        //   366: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   369: ldc_w           29
        //   372: ldc_w           "uint"
        //   375: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   378: aastore        
        //   379: dup            
        //   380: bipush          10
        //   382: aload_0        
        //   383: aload_1        
        //   384: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   387: ldc_w           67
        //   390: ldc_w           "dwPhysAddrLen"
        //   393: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   396: aastore        
        //   397: dup            
        //   398: bipush          11
        //   400: aload_0        
        //   401: aload_1        
        //   402: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   405: ldc_w           29
        //   408: ldc_w           "uint"
        //   411: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   414: aastore        
        //   415: dup            
        //   416: bipush          12
        //   418: aload_0        
        //   419: aload_1        
        //   420: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   423: ldc_w           61
        //   426: ldc_w           "bPhysAddr"
        //   429: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   432: aastore        
        //   433: dup            
        //   434: bipush          13
        //   436: aload_1        
        //   437: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   440: aload_0        
        //   441: aload_1        
        //   442: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   445: ldc_w           68
        //   448: ldc_w           "int8"
        //   451: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   454: aload_0        
        //   455: aload_1        
        //   456: ldc_w           "MAXLEN_PHYSADDR"
        //   459: bipush          10
        //   461: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   464: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   467: aastore        
        //   468: dup            
        //   469: bipush          14
        //   471: aload_0        
        //   472: aload_1        
        //   473: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   476: ldc_w           69
        //   479: ldc_w           "dwAdminStatus"
        //   482: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   485: aastore        
        //   486: dup            
        //   487: bipush          15
        //   489: aload_0        
        //   490: aload_1        
        //   491: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   494: ldc_w           29
        //   497: ldc_w           "uint"
        //   500: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   503: aastore        
        //   504: dup            
        //   505: bipush          16
        //   507: aload_0        
        //   508: aload_1        
        //   509: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   512: ldc_w           70
        //   515: ldc_w           "dwOperStatus"
        //   518: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   521: aastore        
        //   522: dup            
        //   523: bipush          17
        //   525: aload_0        
        //   526: aload_1        
        //   527: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   530: ldc_w           29
        //   533: ldc_w           "uint"
        //   536: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   539: aastore        
        //   540: dup            
        //   541: bipush          18
        //   543: aload_0        
        //   544: aload_1        
        //   545: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   548: ldc_w           71
        //   551: ldc_w           "dwLastChange"
        //   554: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   557: aastore        
        //   558: dup            
        //   559: bipush          19
        //   561: aload_0        
        //   562: aload_1        
        //   563: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   566: ldc_w           29
        //   569: ldc_w           "uint"
        //   572: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   575: aastore        
        //   576: dup            
        //   577: bipush          20
        //   579: aload_0        
        //   580: aload_1        
        //   581: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   584: ldc_w           72
        //   587: ldc_w           "dwInOctets"
        //   590: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   593: aastore        
        //   594: dup            
        //   595: bipush          21
        //   597: aload_0        
        //   598: aload_1        
        //   599: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   602: ldc_w           29
        //   605: ldc_w           "uint"
        //   608: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   611: aastore        
        //   612: dup            
        //   613: bipush          22
        //   615: aload_0        
        //   616: aload_1        
        //   617: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   620: ldc_w           73
        //   623: ldc_w           "dwInUcastPkts"
        //   626: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   629: aastore        
        //   630: dup            
        //   631: bipush          23
        //   633: aload_0        
        //   634: aload_1        
        //   635: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   638: ldc_w           29
        //   641: ldc_w           "uint"
        //   644: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   647: aastore        
        //   648: dup            
        //   649: bipush          24
        //   651: aload_0        
        //   652: aload_1        
        //   653: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   656: ldc_w           74
        //   659: ldc_w           "dwInNUcastPkts"
        //   662: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   665: aastore        
        //   666: dup            
        //   667: bipush          25
        //   669: aload_0        
        //   670: aload_1        
        //   671: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   674: ldc_w           29
        //   677: ldc_w           "uint"
        //   680: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   683: aastore        
        //   684: dup            
        //   685: bipush          26
        //   687: aload_0        
        //   688: aload_1        
        //   689: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   692: ldc             "dwInDiscards"
        //   694: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol8:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   697: aastore        
        //   698: dup            
        //   699: bipush          27
        //   701: aload_0        
        //   702: aload_1        
        //   703: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   706: ldc_w           29
        //   709: ldc_w           "uint"
        //   712: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   715: aastore        
        //   716: dup            
        //   717: bipush          28
        //   719: aload_0        
        //   720: aload_1        
        //   721: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   724: ldc_w           75
        //   727: ldc_w           "dwInErrors"
        //   730: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   733: aastore        
        //   734: dup            
        //   735: bipush          29
        //   737: aload_0        
        //   738: aload_1        
        //   739: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   742: ldc_w           29
        //   745: ldc_w           "uint"
        //   748: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   751: aastore        
        //   752: dup            
        //   753: bipush          30
        //   755: aload_0        
        //   756: aload_1        
        //   757: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   760: ldc             "dwInUnknownProtos"
        //   762: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol7:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   765: aastore        
        //   766: dup            
        //   767: bipush          31
        //   769: aload_0        
        //   770: aload_1        
        //   771: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   774: ldc_w           29
        //   777: ldc_w           "uint"
        //   780: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   783: aastore        
        //   784: dup            
        //   785: bipush          32
        //   787: aload_0        
        //   788: aload_1        
        //   789: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   792: ldc_w           76
        //   795: ldc_w           "dwOutOctets"
        //   798: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   801: aastore        
        //   802: dup            
        //   803: bipush          33
        //   805: aload_0        
        //   806: aload_1        
        //   807: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   810: ldc_w           29
        //   813: ldc_w           "uint"
        //   816: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   819: aastore        
        //   820: dup            
        //   821: bipush          34
        //   823: aload_0        
        //   824: aload_1        
        //   825: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   828: ldc_w           77
        //   831: ldc_w           "dwOutUcastPkts"
        //   834: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   837: aastore        
        //   838: dup            
        //   839: bipush          35
        //   841: aload_0        
        //   842: aload_1        
        //   843: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   846: ldc_w           29
        //   849: ldc_w           "uint"
        //   852: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   855: aastore        
        //   856: dup            
        //   857: bipush          36
        //   859: aload_0        
        //   860: aload_1        
        //   861: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   864: ldc_w           78
        //   867: ldc_w           "dwOutNUcastPkts"
        //   870: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   873: aastore        
        //   874: dup            
        //   875: bipush          37
        //   877: aload_0        
        //   878: aload_1        
        //   879: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   882: ldc_w           29
        //   885: ldc_w           "uint"
        //   888: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   891: aastore        
        //   892: dup            
        //   893: bipush          38
        //   895: aload_0        
        //   896: aload_1        
        //   897: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   900: ldc             12
        //   902: ldc             "dwOutDiscards"
        //   904: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   907: aastore        
        //   908: dup            
        //   909: bipush          39
        //   911: aload_0        
        //   912: aload_1        
        //   913: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   916: ldc_w           29
        //   919: ldc_w           "uint"
        //   922: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   925: aastore        
        //   926: dup            
        //   927: bipush          40
        //   929: aload_0        
        //   930: aload_1        
        //   931: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   934: ldc_w           79
        //   937: ldc_w           "dwOutErrors"
        //   940: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   943: aastore        
        //   944: dup            
        //   945: bipush          41
        //   947: aload_0        
        //   948: aload_1        
        //   949: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   952: ldc_w           29
        //   955: ldc_w           "uint"
        //   958: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   961: aastore        
        //   962: dup            
        //   963: bipush          42
        //   965: aload_0        
        //   966: aload_1        
        //   967: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   970: ldc_w           80
        //   973: ldc_w           "dwOutQLen"
        //   976: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   979: aastore        
        //   980: dup            
        //   981: bipush          43
        //   983: aload_0        
        //   984: aload_1        
        //   985: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   988: ldc_w           29
        //   991: ldc_w           "uint"
        //   994: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   997: aastore        
        //   998: dup            
        //   999: bipush          44
        //  1001: aload_0        
        //  1002: aload_1        
        //  1003: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1006: ldc_w           59
        //  1009: ldc_w           "dwDescrLen"
        //  1012: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1015: aastore        
        //  1016: dup            
        //  1017: bipush          45
        //  1019: aload_0        
        //  1020: aload_1        
        //  1021: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1024: ldc_w           29
        //  1027: ldc_w           "uint"
        //  1030: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1033: aastore        
        //  1034: dup            
        //  1035: bipush          46
        //  1037: aload_0        
        //  1038: aload_1        
        //  1039: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1042: ldc_w           60
        //  1045: ldc_w           "bDescr"
        //  1048: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1051: aastore        
        //  1052: dup            
        //  1053: bipush          47
        //  1055: aload_1        
        //  1056: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1059: aload_0        
        //  1060: aload_1        
        //  1061: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1064: ldc_w           68
        //  1067: ldc_w           "int8"
        //  1070: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1073: aload_0        
        //  1074: aload_1        
        //  1075: ldc_w           "MAXLEN_IFDESCR"
        //  1078: bipush          11
        //  1080: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1083: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //  1086: aastore        
        //  1087: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1090: aload_1        
        //  1091: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //  1094: goto            1102
        //  1097: aload_1        
        //  1098: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //  1101: athrow         
        //  1102: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  38     1090   1097   1102   Any
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
    
    @JRubyMethod(name = "name", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$name(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        return file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite8().call(context, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite9().call(context, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(10).call(context, rubyObject, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol(context.runtime, 58, "wszName"))), file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant7(context, "MAX_INTERFACE_NAME_LEN"));
    }
    
    @JRubyMethod(name = "description", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$description(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        final DynamicScope locals = context.getCurrentScope();
        locals.setValueZeroDepthZero(file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(11).call(context, rubyObject, RuntimeHelpers.constructRubyArray(context.runtime, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(12).call(context, rubyObject, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol(context.runtime, 59, "dwDescrLen")), file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant8(context, "MAXLEN_IFDESCR"))));
        if (file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(13).call(context, rubyObject, locals.getValueZeroDepthZeroOrNil(context.nil), 1L).isTrue()) {
            locals.setValueZeroDepthZero(file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(14).call(context, rubyObject, locals.getValueZeroDepthZeroOrNil(context.nil), 1L));
        }
        return file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(15).call(context, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(16).call(context, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(17).call(context, rubyObject, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol(context.runtime, 60, "bDescr"))), locals.getValueZeroDepthZeroOrNil(context.nil));
    }
    
    @JRubyMethod(name = "mac_addr", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__10$RUBY$mac_addr(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(18).call(threadContext, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(19).call(threadContext, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(20).call(threadContext, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(21).call(threadContext, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(22).call(threadContext, rubyObject, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol(threadContext.runtime, 61, "bPhysAddr"))), file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getFixnum0(threadContext.runtime, 6)), file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(23).call(threadContext, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getString(threadContext.runtime, 10, 32), 6L)), file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getString(threadContext.runtime, 11, 32));
    }
    
    public static IRubyObject class_7$RUBY$IfRow(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_7$RUBY$IfRow(file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_11$RUBY$IpNetRow(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //     4: aload_2        
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareSuperClass:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyClass;
        //     8: aload_1        
        //     9: aload_1        
        //    10: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    13: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    16: swap           
        //    17: ldc_w           "IpNetRow"
        //    20: swap           
        //    21: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    24: dup            
        //    25: astore_2       
        //    26: aload_1        
        //    27: swap           
        //    28: aload_0        
        //    29: aload_1        
        //    30: ldc             ",0,0,-1"
        //    32: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getScope6:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    35: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    38: aload_0        
        //    39: aload_1        
        //    40: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    43: bipush          8
        //    45: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getFixnum9:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //    48: aload_1        
        //    49: ldc_w           "MAXLEN_PHYSADDR"
        //    52: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: pop            
        //    56: aload_0        
        //    57: bipush          25
        //    59: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    62: aload_1        
        //    63: aload_2        
        //    64: aload_2        
        //    65: bipush          10
        //    67: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: dup            
        //    71: iconst_0       
        //    72: aload_0        
        //    73: aload_1        
        //    74: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    77: ldc_w           63
        //    80: ldc_w           "dwIndex"
        //    83: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    86: aastore        
        //    87: dup            
        //    88: iconst_1       
        //    89: aload_0        
        //    90: aload_1        
        //    91: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    94: ldc_w           29
        //    97: ldc_w           "uint"
        //   100: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   103: aastore        
        //   104: dup            
        //   105: iconst_2       
        //   106: aload_0        
        //   107: aload_1        
        //   108: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   111: ldc_w           67
        //   114: ldc_w           "dwPhysAddrLen"
        //   117: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   120: aastore        
        //   121: dup            
        //   122: iconst_3       
        //   123: aload_0        
        //   124: aload_1        
        //   125: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   128: ldc_w           29
        //   131: ldc_w           "uint"
        //   134: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   137: aastore        
        //   138: dup            
        //   139: iconst_4       
        //   140: aload_0        
        //   141: aload_1        
        //   142: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   145: ldc_w           61
        //   148: ldc_w           "bPhysAddr"
        //   151: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   154: aastore        
        //   155: dup            
        //   156: iconst_5       
        //   157: aload_1        
        //   158: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   161: aload_0        
        //   162: aload_1        
        //   163: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   166: ldc_w           68
        //   169: ldc_w           "int8"
        //   172: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   175: aload_0        
        //   176: aload_1        
        //   177: ldc_w           "MAXLEN_PHYSADDR"
        //   180: bipush          14
        //   182: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   185: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   188: aastore        
        //   189: dup            
        //   190: bipush          6
        //   192: aload_0        
        //   193: aload_1        
        //   194: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   197: ldc_w           81
        //   200: ldc_w           "dwAddr"
        //   203: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   206: aastore        
        //   207: dup            
        //   208: bipush          7
        //   210: aload_0        
        //   211: aload_1        
        //   212: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   215: ldc_w           29
        //   218: ldc_w           "uint"
        //   221: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   224: aastore        
        //   225: dup            
        //   226: bipush          8
        //   228: aload_0        
        //   229: aload_1        
        //   230: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   233: ldc_w           64
        //   236: ldc_w           "dwType"
        //   239: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   242: aastore        
        //   243: dup            
        //   244: bipush          9
        //   246: aload_0        
        //   247: aload_1        
        //   248: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   251: ldc_w           29
        //   254: ldc_w           "uint"
        //   257: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   260: aastore        
        //   261: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   264: pop            
        //   265: aload_1        
        //   266: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   269: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   272: aload_1        
        //   273: ldc_w           "TYPE_OTHER"
        //   276: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   279: pop            
        //   280: aload_1        
        //   281: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   284: invokestatic    org/jruby/RubyFixnum.two:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   287: aload_1        
        //   288: ldc_w           "TYPE_INVALID"
        //   291: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   294: pop            
        //   295: aload_1        
        //   296: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   299: invokestatic    org/jruby/RubyFixnum.three:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   302: aload_1        
        //   303: ldc_w           "TYPE_DYNAMIC"
        //   306: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   309: pop            
        //   310: aload_1        
        //   311: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   314: invokestatic    org/jruby/RubyFixnum.four:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   317: aload_1        
        //   318: ldc_w           "TYPE_STATIC"
        //   321: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   324: pop            
        //   325: aload_1        
        //   326: aload_2        
        //   327: aload_0        
        //   328: ldc_w           "valid?"
        //   331: ldc_w           "method__12$RUBY$valid_p_"
        //   334: ldc             ",0,0,-1"
        //   336: iconst_0       
        //   337: ldc             "./lib//win32/mib/ipstats.rb"
        //   339: ldc_w           150
        //   342: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   345: ldc_w           "NONE"
        //   348: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   351: pop            
        //   352: aload_1        
        //   353: aload_2        
        //   354: aload_0        
        //   355: ldc_w           "ip_addr_as_int"
        //   358: ldc_w           "method__13$RUBY$ip_addr_as_int"
        //   361: ldc             ",0,0,-1"
        //   363: iconst_0       
        //   364: ldc             "./lib//win32/mib/ipstats.rb"
        //   366: ldc_w           154
        //   369: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   372: ldc_w           "NONE"
        //   375: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   378: pop            
        //   379: aload_1        
        //   380: aload_2        
        //   381: aload_0        
        //   382: ldc_w           "mac_addr"
        //   385: ldc_w           "method__14$RUBY$mac_addr"
        //   388: ldc             ",0,0,-1"
        //   390: iconst_0       
        //   391: ldc             "./lib//win32/mib/ipstats.rb"
        //   393: ldc_w           158
        //   396: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   399: ldc_w           "NONE"
        //   402: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   405: aload_1        
        //   406: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   409: goto            417
        //   412: aload_1        
        //   413: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   416: athrow         
        //   417: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  38     405    412    417    Any
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
    
    @JRubyMethod(name = "valid?", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__12$RUBY$valid_p_(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(26).call(threadContext, rubyObject, RuntimeHelpers.constructRubyArray(threadContext.runtime, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant(threadContext, "TYPE_DYNAMIC", 15), file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant(threadContext, "TYPE_STATIC", 16)), file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(27).call(threadContext, rubyObject, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol(threadContext.runtime, 64, "dwType")));
    }
    
    @JRubyMethod(name = "ip_addr_as_int", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__13$RUBY$ip_addr_as_int(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(28).call(threadContext, rubyObject, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol(threadContext.runtime, 81, "dwAddr"));
    }
    
    @JRubyMethod(name = "mac_addr", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__14$RUBY$mac_addr(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(29).call(threadContext, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(30).call(threadContext, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(31).call(threadContext, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(32).call(threadContext, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(33).call(threadContext, rubyObject, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol(threadContext.runtime, 61, "bPhysAddr"))), file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getFixnum0(threadContext.runtime, 6)), file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(34).call(threadContext, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getString(threadContext.runtime, 10, 32), 6L)), file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getString(threadContext.runtime, 11, 32));
    }
    
    public static IRubyObject class_11$RUBY$IpNetRow(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_11$RUBY$IpNetRow(file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_15$RUBY$IpAddrRow(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //     4: aload_2        
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareSuperClass:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyClass;
        //     8: aload_1        
        //     9: aload_1        
        //    10: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    13: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    16: swap           
        //    17: ldc_w           "IpAddrRow"
        //    20: swap           
        //    21: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    24: dup            
        //    25: astore_2       
        //    26: aload_1        
        //    27: swap           
        //    28: aload_0        
        //    29: aload_1        
        //    30: ldc             ",0,0,-1"
        //    32: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getScope7:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    35: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    38: aload_0        
        //    39: bipush          35
        //    41: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    44: aload_1        
        //    45: aload_2        
        //    46: aload_2        
        //    47: bipush          14
        //    49: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: dup            
        //    53: iconst_0       
        //    54: aload_0        
        //    55: aload_1        
        //    56: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    59: ldc_w           81
        //    62: ldc_w           "dwAddr"
        //    65: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    68: aastore        
        //    69: dup            
        //    70: iconst_1       
        //    71: aload_0        
        //    72: aload_1        
        //    73: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    76: ldc_w           29
        //    79: ldc_w           "uint"
        //    82: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    85: aastore        
        //    86: dup            
        //    87: iconst_2       
        //    88: aload_0        
        //    89: aload_1        
        //    90: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    93: ldc_w           63
        //    96: ldc_w           "dwIndex"
        //    99: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   102: aastore        
        //   103: dup            
        //   104: iconst_3       
        //   105: aload_0        
        //   106: aload_1        
        //   107: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   110: ldc_w           29
        //   113: ldc_w           "uint"
        //   116: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   119: aastore        
        //   120: dup            
        //   121: iconst_4       
        //   122: aload_0        
        //   123: aload_1        
        //   124: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   127: ldc_w           82
        //   130: ldc_w           "dwMask"
        //   133: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   136: aastore        
        //   137: dup            
        //   138: iconst_5       
        //   139: aload_0        
        //   140: aload_1        
        //   141: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   144: ldc_w           29
        //   147: ldc_w           "uint"
        //   150: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   153: aastore        
        //   154: dup            
        //   155: bipush          6
        //   157: aload_0        
        //   158: aload_1        
        //   159: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   162: ldc_w           83
        //   165: ldc_w           "dwBCastAddr"
        //   168: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   171: aastore        
        //   172: dup            
        //   173: bipush          7
        //   175: aload_0        
        //   176: aload_1        
        //   177: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   180: ldc_w           29
        //   183: ldc_w           "uint"
        //   186: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   189: aastore        
        //   190: dup            
        //   191: bipush          8
        //   193: aload_0        
        //   194: aload_1        
        //   195: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   198: ldc_w           84
        //   201: ldc_w           "dwReasmSize"
        //   204: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   207: aastore        
        //   208: dup            
        //   209: bipush          9
        //   211: aload_0        
        //   212: aload_1        
        //   213: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   216: ldc_w           29
        //   219: ldc_w           "uint"
        //   222: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   225: aastore        
        //   226: dup            
        //   227: bipush          10
        //   229: aload_0        
        //   230: aload_1        
        //   231: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   234: ldc_w           85
        //   237: ldc_w           "unused1"
        //   240: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   243: aastore        
        //   244: dup            
        //   245: bipush          11
        //   247: aload_0        
        //   248: aload_1        
        //   249: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   252: ldc_w           86
        //   255: ldc_w           "ushort"
        //   258: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   261: aastore        
        //   262: dup            
        //   263: bipush          12
        //   265: aload_0        
        //   266: aload_1        
        //   267: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   270: ldc_w           87
        //   273: ldc_w           "wType"
        //   276: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   279: aastore        
        //   280: dup            
        //   281: bipush          13
        //   283: aload_0        
        //   284: aload_1        
        //   285: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   288: ldc_w           86
        //   291: ldc_w           "ushort"
        //   294: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   297: aastore        
        //   298: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   301: aload_1        
        //   302: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   305: goto            313
        //   308: aload_1        
        //   309: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   312: athrow         
        //   313: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  38     301    308    313    Any
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
    
    public static IRubyObject class_15$RUBY$IpAddrRow(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_15$RUBY$IpAddrRow(file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_16$RUBY$IpAddrTable(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //     4: aload_2        
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareSuperClass:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyClass;
        //     8: aload_1        
        //     9: aload_1        
        //    10: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    13: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    16: swap           
        //    17: ldc_w           "IpAddrTable"
        //    20: swap           
        //    21: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    24: dup            
        //    25: astore_2       
        //    26: aload_1        
        //    27: swap           
        //    28: aload_0        
        //    29: aload_1        
        //    30: ldc             ",0,0,-1"
        //    32: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getScope8:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    35: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    38: aload_1        
        //    39: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    42: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    45: aload_1        
        //    46: ldc_w           "ANY_SIZE"
        //    49: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: pop            
        //    53: aload_0        
        //    54: bipush          36
        //    56: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    59: aload_1        
        //    60: aload_2        
        //    61: aload_2        
        //    62: aload_0        
        //    63: aload_1        
        //    64: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    67: ldc_w           88
        //    70: ldc_w           "dwNumEntries"
        //    73: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    76: aload_0        
        //    77: aload_1        
        //    78: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    81: ldc_w           29
        //    84: ldc_w           "uint"
        //    87: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    90: aload_0        
        //    91: aload_1        
        //    92: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    95: ldc_w           89
        //    98: ldc_w           "table"
        //   101: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   104: aload_1        
        //   105: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   108: aload_0        
        //   109: aload_1        
        //   110: ldc_w           "IpAddrRow"
        //   113: bipush          21
        //   115: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   118: aload_0        
        //   119: aload_1        
        //   120: ldc_w           "ANY_SIZE"
        //   123: bipush          22
        //   125: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   128: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   131: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   134: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   137: aload_1        
        //   138: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   141: goto            149
        //   144: aload_1        
        //   145: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   148: athrow         
        //   149: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  38     137    144    149    Any
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
    
    public static IRubyObject class_16$RUBY$IpAddrTable(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_16$RUBY$IpAddrTable(file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_17$RUBY$IpNetTable(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //     4: aload_2        
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareSuperClass:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyClass;
        //     8: aload_1        
        //     9: aload_1        
        //    10: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    13: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    16: swap           
        //    17: ldc_w           "IpNetTable"
        //    20: swap           
        //    21: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    24: dup            
        //    25: astore_2       
        //    26: aload_1        
        //    27: swap           
        //    28: aload_0        
        //    29: aload_1        
        //    30: ldc             ",0,0,-1"
        //    32: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getScope9:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    35: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    38: aload_1        
        //    39: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    42: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    45: aload_1        
        //    46: ldc_w           "ANY_SIZE"
        //    49: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: pop            
        //    53: aload_0        
        //    54: bipush          37
        //    56: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    59: aload_1        
        //    60: aload_2        
        //    61: aload_2        
        //    62: aload_0        
        //    63: aload_1        
        //    64: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    67: ldc_w           88
        //    70: ldc_w           "dwNumEntries"
        //    73: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    76: aload_0        
        //    77: aload_1        
        //    78: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    81: ldc_w           29
        //    84: ldc_w           "uint"
        //    87: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    90: aload_0        
        //    91: aload_1        
        //    92: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    95: ldc_w           89
        //    98: ldc_w           "table"
        //   101: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   104: aload_1        
        //   105: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   108: aload_0        
        //   109: aload_1        
        //   110: ldc_w           "IpNetRow"
        //   113: bipush          25
        //   115: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   118: aload_0        
        //   119: aload_1        
        //   120: ldc_w           "ANY_SIZE"
        //   123: bipush          26
        //   125: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   128: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   131: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   134: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   137: aload_1        
        //   138: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   141: goto            149
        //   144: aload_1        
        //   145: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   148: athrow         
        //   149: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  38     137    144    149    Any
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
    
    public static IRubyObject class_17$RUBY$IpNetTable(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_17$RUBY$IpNetTable(file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_18$RUBY$IfTable(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //     4: aload_2        
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareSuperClass:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyClass;
        //     8: aload_1        
        //     9: aload_1        
        //    10: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    13: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    16: swap           
        //    17: ldc_w           "IfTable"
        //    20: swap           
        //    21: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    24: dup            
        //    25: astore_2       
        //    26: aload_1        
        //    27: swap           
        //    28: aload_0        
        //    29: aload_1        
        //    30: ldc             ",0,0,-1"
        //    32: bipush          10
        //    34: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getScope:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/parser/StaticScope;
        //    37: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    40: aload_1        
        //    41: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    44: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    47: aload_1        
        //    48: ldc_w           "ANY_SIZE"
        //    51: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: pop            
        //    55: aload_0        
        //    56: bipush          38
        //    58: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    61: aload_1        
        //    62: aload_2        
        //    63: aload_2        
        //    64: aload_0        
        //    65: aload_1        
        //    66: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    69: ldc_w           88
        //    72: ldc_w           "dwNumEntries"
        //    75: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    78: aload_0        
        //    79: aload_1        
        //    80: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    83: ldc_w           29
        //    86: ldc_w           "uint"
        //    89: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    92: aload_0        
        //    93: aload_1        
        //    94: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    97: ldc_w           89
        //   100: ldc_w           "table"
        //   103: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   106: aload_1        
        //   107: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   110: aload_0        
        //   111: aload_1        
        //   112: ldc_w           "IfRow"
        //   115: bipush          29
        //   117: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   120: aload_0        
        //   121: aload_1        
        //   122: ldc_w           "ANY_SIZE"
        //   125: bipush          30
        //   127: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   130: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   133: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   136: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   139: aload_1        
        //   140: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   143: goto            151
        //   146: aload_1        
        //   147: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   150: athrow         
        //   151: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  40     139    146    151    Any
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
    
    public static IRubyObject class_18$RUBY$IfTable(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_18$RUBY$IfTable(file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_19$RUBY$IpAddrString(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //     4: aload_2        
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareSuperClass:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyClass;
        //     8: aload_1        
        //     9: aload_1        
        //    10: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    13: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    16: swap           
        //    17: ldc_w           "IpAddrString"
        //    20: swap           
        //    21: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    24: dup            
        //    25: astore_2       
        //    26: aload_1        
        //    27: swap           
        //    28: aload_0        
        //    29: aload_1        
        //    30: ldc_w           "ipAddressString;ipMaskString,0,0,-1"
        //    33: bipush          11
        //    35: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getScope:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/parser/StaticScope;
        //    38: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    41: aload_1        
        //    42: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: astore          8
        //    47: aload_1        
        //    48: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: astore          ipMaskString
        //    53: aload_1        
        //    54: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    57: aload_0        
        //    58: aload_1        
        //    59: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    62: ldc_w           90
        //    65: ldc_w           "char"
        //    68: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    71: aload_0        
        //    72: aload_1        
        //    73: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    76: bipush          10
        //    78: bipush          16
        //    80: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getFixnum:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyFixnum;
        //    83: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //    86: astore          ipAddressString
        //    88: aload_1        
        //    89: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    92: aload_0        
        //    93: aload_1        
        //    94: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    97: ldc_w           90
        //   100: ldc_w           "char"
        //   103: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   106: aload_0        
        //   107: aload_1        
        //   108: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   111: bipush          10
        //   113: bipush          16
        //   115: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getFixnum:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyFixnum;
        //   118: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   121: astore          ipMaskString
        //   123: aload_0        
        //   124: bipush          39
        //   126: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   129: aload_1        
        //   130: aload_2        
        //   131: aload_2        
        //   132: bipush          8
        //   134: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //   137: dup            
        //   138: iconst_0       
        //   139: aload_0        
        //   140: aload_1        
        //   141: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   144: ldc_w           91
        //   147: ldc_w           "Next"
        //   150: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   153: aastore        
        //   154: dup            
        //   155: iconst_1       
        //   156: aload_0        
        //   157: aload_1        
        //   158: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   161: ldc_w           92
        //   164: ldc_w           "pointer"
        //   167: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   170: aastore        
        //   171: dup            
        //   172: iconst_2       
        //   173: aload_0        
        //   174: aload_1        
        //   175: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   178: ldc_w           93
        //   181: ldc_w           "IpAddress"
        //   184: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   187: aastore        
        //   188: dup            
        //   189: iconst_3       
        //   190: aload           ipAddressString
        //   192: aastore        
        //   193: dup            
        //   194: iconst_4       
        //   195: aload_0        
        //   196: aload_1        
        //   197: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   200: ldc_w           94
        //   203: ldc_w           "IpMask"
        //   206: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   209: aastore        
        //   210: dup            
        //   211: iconst_5       
        //   212: aload           ipMaskString
        //   214: aastore        
        //   215: dup            
        //   216: bipush          6
        //   218: aload_0        
        //   219: aload_1        
        //   220: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   223: ldc_w           95
        //   226: ldc_w           "Context"
        //   229: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   232: aastore        
        //   233: dup            
        //   234: bipush          7
        //   236: aload_0        
        //   237: aload_1        
        //   238: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   241: ldc_w           29
        //   244: ldc_w           "uint"
        //   247: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   250: aastore        
        //   251: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   254: aload_1        
        //   255: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   258: goto            266
        //   261: aload_1        
        //   262: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   265: athrow         
        //   266: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name             Signature
        //  -----  ------  ----  ---------------  ---------------------------------------
        //  53     214     8     ipAddressString  Lorg/jruby/runtime/builtin/IRubyObject;
        //  53     214     9     ipMaskString     Lorg/jruby/runtime/builtin/IRubyObject;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  53     254    261    266    Any
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
    
    public static IRubyObject class_19$RUBY$IpAddrString(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_19$RUBY$IpAddrString(file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_20$RUBY$FixedInfo(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //     4: aload_2        
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareSuperClass:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyClass;
        //     8: aload_1        
        //     9: aload_1        
        //    10: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    13: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    16: swap           
        //    17: ldc_w           "FixedInfo"
        //    20: swap           
        //    21: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    24: dup            
        //    25: astore_2       
        //    26: aload_1        
        //    27: swap           
        //    28: aload_0        
        //    29: aload_1        
        //    30: ldc             ",0,0,-1"
        //    32: bipush          12
        //    34: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getScope:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/parser/StaticScope;
        //    37: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    40: aload_0        
        //    41: aload_1        
        //    42: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    45: bipush          11
        //    47: sipush          128
        //    50: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getFixnum:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyFixnum;
        //    53: aload_1        
        //    54: ldc_w           "MAX_HOSTNAME_LEN"
        //    57: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: pop            
        //    61: aload_0        
        //    62: aload_1        
        //    63: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    66: bipush          11
        //    68: sipush          128
        //    71: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getFixnum:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyFixnum;
        //    74: aload_1        
        //    75: ldc_w           "MAX_DOMAIN_NAME_LEN"
        //    78: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    81: pop            
        //    82: aload_0        
        //    83: aload_1        
        //    84: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    87: sipush          256
        //    90: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getFixnum8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //    93: aload_1        
        //    94: ldc_w           "MAX_SCOPE_ID_LEN"
        //    97: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   100: pop            
        //   101: aload_1        
        //   102: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   105: aload_1        
        //   106: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   109: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   112: aload_0        
        //   113: aload_1        
        //   114: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   117: ldc_w           96
        //   120: ldc_w           "broadcast"
        //   123: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   126: aload_1        
        //   127: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   130: invokestatic    org/jruby/RubyFixnum.two:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   133: aload_0        
        //   134: aload_1        
        //   135: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   138: ldc_w           97
        //   141: ldc_w           "p2p"
        //   144: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   147: aload_1        
        //   148: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   151: invokestatic    org/jruby/RubyFixnum.four:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   154: aload_0        
        //   155: aload_1        
        //   156: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   159: ldc_w           98
        //   162: ldc_w           "mixed"
        //   165: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   168: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructHash:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyHash;
        //   171: dup            
        //   172: aload_1        
        //   173: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   176: aload_0        
        //   177: aload_1        
        //   178: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   181: bipush          8
        //   183: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getFixnum9:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   186: aload_0        
        //   187: aload_1        
        //   188: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   191: ldc_w           99
        //   194: ldc_w           "hybrid"
        //   197: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   200: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   203: aload_1        
        //   204: ldc_w           "NODE_TYPES"
        //   207: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   210: pop            
        //   211: aload_0        
        //   212: bipush          40
        //   214: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   217: aload_1        
        //   218: aload_2        
        //   219: aload_2        
        //   220: bipush          18
        //   222: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //   225: dup            
        //   226: iconst_0       
        //   227: aload_0        
        //   228: aload_1        
        //   229: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   232: ldc_w           100
        //   235: ldc_w           "HostName"
        //   238: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   241: aastore        
        //   242: dup            
        //   243: iconst_1       
        //   244: aload_1        
        //   245: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   248: aload_0        
        //   249: aload_1        
        //   250: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   253: ldc_w           90
        //   256: ldc_w           "char"
        //   259: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   262: aload_0        
        //   263: bipush          41
        //   265: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   268: aload_1        
        //   269: aload_2        
        //   270: aload_0        
        //   271: aload_1        
        //   272: ldc_w           "MAX_HOSTNAME_LEN"
        //   275: bipush          35
        //   277: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   280: ldc2_w          4
        //   283: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;J)Lorg/jruby/runtime/builtin/IRubyObject;
        //   286: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   289: aastore        
        //   290: dup            
        //   291: iconst_2       
        //   292: aload_0        
        //   293: aload_1        
        //   294: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   297: ldc_w           101
        //   300: ldc_w           "DomainName"
        //   303: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   306: aastore        
        //   307: dup            
        //   308: iconst_3       
        //   309: aload_1        
        //   310: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   313: aload_0        
        //   314: aload_1        
        //   315: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   318: ldc_w           90
        //   321: ldc_w           "char"
        //   324: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   327: aload_0        
        //   328: bipush          42
        //   330: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   333: aload_1        
        //   334: aload_2        
        //   335: aload_0        
        //   336: aload_1        
        //   337: ldc_w           "MAX_DOMAIN_NAME_LEN"
        //   340: bipush          36
        //   342: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   345: ldc2_w          4
        //   348: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;J)Lorg/jruby/runtime/builtin/IRubyObject;
        //   351: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   354: aastore        
        //   355: dup            
        //   356: iconst_4       
        //   357: aload_0        
        //   358: aload_1        
        //   359: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   362: ldc_w           102
        //   365: ldc_w           "CurrentDnsServer"
        //   368: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   371: aastore        
        //   372: dup            
        //   373: iconst_5       
        //   374: aload_0        
        //   375: aload_1        
        //   376: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   379: ldc_w           92
        //   382: ldc_w           "pointer"
        //   385: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   388: aastore        
        //   389: dup            
        //   390: bipush          6
        //   392: aload_0        
        //   393: aload_1        
        //   394: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   397: ldc_w           103
        //   400: ldc_w           "DnsServerList"
        //   403: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   406: aastore        
        //   407: dup            
        //   408: bipush          7
        //   410: aload_0        
        //   411: aload_1        
        //   412: ldc_w           "IpAddrString"
        //   415: bipush          37
        //   417: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   420: aastore        
        //   421: dup            
        //   422: bipush          8
        //   424: aload_0        
        //   425: aload_1        
        //   426: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   429: ldc_w           104
        //   432: ldc_w           "NodeType"
        //   435: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   438: aastore        
        //   439: dup            
        //   440: bipush          9
        //   442: aload_0        
        //   443: aload_1        
        //   444: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   447: ldc_w           29
        //   450: ldc_w           "uint"
        //   453: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   456: aastore        
        //   457: dup            
        //   458: bipush          10
        //   460: aload_0        
        //   461: aload_1        
        //   462: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   465: ldc_w           105
        //   468: ldc_w           "ScopeId"
        //   471: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   474: aastore        
        //   475: dup            
        //   476: bipush          11
        //   478: aload_1        
        //   479: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   482: aload_0        
        //   483: aload_1        
        //   484: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   487: ldc_w           90
        //   490: ldc_w           "char"
        //   493: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   496: aload_0        
        //   497: bipush          43
        //   499: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   502: aload_1        
        //   503: aload_2        
        //   504: aload_0        
        //   505: aload_1        
        //   506: ldc_w           "MAX_SCOPE_ID_LEN"
        //   509: bipush          38
        //   511: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   514: ldc2_w          4
        //   517: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;J)Lorg/jruby/runtime/builtin/IRubyObject;
        //   520: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   523: aastore        
        //   524: dup            
        //   525: bipush          12
        //   527: aload_0        
        //   528: aload_1        
        //   529: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   532: ldc_w           106
        //   535: ldc_w           "EnableRouting"
        //   538: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   541: aastore        
        //   542: dup            
        //   543: bipush          13
        //   545: aload_0        
        //   546: aload_1        
        //   547: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   550: ldc_w           29
        //   553: ldc_w           "uint"
        //   556: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   559: aastore        
        //   560: dup            
        //   561: bipush          14
        //   563: aload_0        
        //   564: aload_1        
        //   565: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   568: ldc_w           107
        //   571: ldc_w           "EnableProxy"
        //   574: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   577: aastore        
        //   578: dup            
        //   579: bipush          15
        //   581: aload_0        
        //   582: aload_1        
        //   583: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   586: ldc_w           29
        //   589: ldc_w           "uint"
        //   592: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   595: aastore        
        //   596: dup            
        //   597: bipush          16
        //   599: aload_0        
        //   600: aload_1        
        //   601: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   604: ldc_w           108
        //   607: ldc_w           "EnableDns"
        //   610: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   613: aastore        
        //   614: dup            
        //   615: bipush          17
        //   617: aload_0        
        //   618: aload_1        
        //   619: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   622: ldc_w           29
        //   625: ldc_w           "uint"
        //   628: invokevirtual   ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   631: aastore        
        //   632: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   635: pop            
        //   636: aload_1        
        //   637: aload_2        
        //   638: aload_0        
        //   639: ldc_w           "node_type"
        //   642: ldc_w           "method__21$RUBY$node_type"
        //   645: ldc             ",0,0,-1"
        //   647: iconst_0       
        //   648: ldc             "./lib//win32/mib/ipstats.rb"
        //   650: ldc_w           219
        //   653: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   656: ldc_w           "NONE"
        //   659: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   662: pop            
        //   663: aload_1        
        //   664: aload_2        
        //   665: aload_0        
        //   666: ldc_w           "routing_enabled?"
        //   669: ldc_w           "method__22$RUBY$routing_enabled_p_"
        //   672: ldc             ",0,0,-1"
        //   674: iconst_0       
        //   675: ldc             "./lib//win32/mib/ipstats.rb"
        //   677: ldc_w           223
        //   680: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   683: ldc_w           "NONE"
        //   686: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   689: pop            
        //   690: aload_1        
        //   691: aload_2        
        //   692: aload_0        
        //   693: ldc_w           "proxy_enabled?"
        //   696: ldc_w           "method__23$RUBY$proxy_enabled_p_"
        //   699: ldc             ",0,0,-1"
        //   701: iconst_0       
        //   702: ldc             "./lib//win32/mib/ipstats.rb"
        //   704: ldc_w           227
        //   707: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   710: ldc_w           "NONE"
        //   713: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   716: pop            
        //   717: aload_1        
        //   718: aload_2        
        //   719: aload_0        
        //   720: ldc_w           "dns_server_enabled?"
        //   723: ldc_w           "method__24$RUBY$dns_server_enabled_p_"
        //   726: ldc             ",0,0,-1"
        //   728: iconst_0       
        //   729: ldc             "./lib//win32/mib/ipstats.rb"
        //   731: ldc_w           231
        //   734: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   737: ldc_w           "NONE"
        //   740: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   743: aload_1        
        //   744: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   747: goto            755
        //   750: aload_1        
        //   751: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   754: athrow         
        //   755: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  40     743    750    755    Any
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
    
    @JRubyMethod(name = "node_type", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__21$RUBY$node_type(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        return file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(44).call(context, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getConstant(context, "NODE_TYPES", 39), file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(45).call(context, rubyObject, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol(context.runtime, 104, "NodeType")));
    }
    
    @JRubyMethod(name = "routing_enabled?", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__22$RUBY$routing_enabled_p_(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return RuntimeHelpers.negate(file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(46).call(threadContext, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(47).call(threadContext, rubyObject, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol(threadContext.runtime, 106, "EnableRouting")), 0L), threadContext.runtime);
    }
    
    @JRubyMethod(name = "proxy_enabled?", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__23$RUBY$proxy_enabled_p_(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return RuntimeHelpers.negate(file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(48).call(threadContext, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(49).call(threadContext, rubyObject, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol(threadContext.runtime, 107, "EnableProxy")), 0L), threadContext.runtime);
    }
    
    @JRubyMethod(name = "dns_server_enabled?", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__24$RUBY$dns_server_enabled_p_(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return RuntimeHelpers.negate(file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(50).call(threadContext, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getCallSite(51).call(threadContext, rubyObject, rubyObject, file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.getSymbol(threadContext.runtime, 108, "EnableDns")), 0L), threadContext.runtime);
    }
    
    public static IRubyObject class_20$RUBY$FixedInfo(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_20$RUBY$FixedInfo(file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$MIB(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$MIB(file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Win32(final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Win32(file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF, threadContext, rubyObject, block);
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
        final FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF = new FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF();
        final String string = FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.class.getClassLoader().getResource("ruby/jit/FILE_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.class").toString();
        file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_729E8E9F83DB069A2B881D5A72E099B3443CB0CF.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
