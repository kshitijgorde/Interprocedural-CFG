// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.RubyString;
import org.jruby.internal.runtime.methods.CallConfiguration;
import org.jruby.anno.JRubyMethod;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278 extends AbstractScript
{
    public FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278() {
        this.filename = "./lib//macOS/netstat.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("new\uffffN\uffff==\uffffN\uffffforeign_addr\uffffV\uffff>\uffffN\ufffflocal_port\uffffV\uffff`\uffffF\uffffcmd_for_protocol\uffffF\uffffblock_given?\uffffF\uffffeach_line\uffffN\uffffoutput_for_protocol\uffffF\uffff===\uffffN\uffffsplit\uffffN\uffffchomp\uffffN\uffffto_i\uffffN\uffffto_i\uffffN\uffffjoin\uffffN\uffff[]\uffffN\uffffsplit\uffffN\uffffto_i\uffffN\ufffflast\uffffN\uffffsplit\uffffN\uffffjoin\uffffN\uffff[]\uffffN\uffffsplit\uffffN\uffffto_i\uffffN\ufffflast\uffffN\uffffsplit\uffffN\uffffnew\uffffN\uffffnew\uffffN\uffff\u0002\t\u0001\u0000\u0005\u0001\u0000\u0000\u0000\u0002\u0000\u0000\u0005\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(3, "^(tcp|udp)", this.getEncoding0());
        this.setByteList(0, "constant", this.getEncoding0());
        this.setByteList(1, "*", this.getEncoding0());
        this.setByteList(4, ".", this.getEncoding0());
        this.setByteList(2, "netstat -n -a -l -p ", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278 file_F25942E4E97A72601A44C76801BF6E0AF9E9E278, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        if (!RuntimeHelpers.stringOrNil(threadContext.getConstantDefined("Enumerator") ? file_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getByteList0() : null, threadContext).isTrue()) {
            RuntimeHelpers.setConstantInCurrent(file_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getConstantFrom1(RuntimeHelpers.checkIsModule(file_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getConstant0(threadContext, "Enumerable")), threadContext, "Enumerator"), threadContext, "Enumerator");
        }
        return module__0$RUBY$MacOs(file_F25942E4E97A72601A44C76801BF6E0AF9E9E278, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$MacOs(final FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.module__1$RUBY$Netstat:(Lruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Netstat(final FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    13: aload_1        
        //    14: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    17: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    20: ldc             "Netstat"
        //    22: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    25: dup            
        //    26: astore_2       
        //    27: aload_1        
        //    28: swap           
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc             ",0,0,-1"
        //    33: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    36: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClass:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    39: aload_1        
        //    40: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //    43: astore          locals
        //    45: aload_0        
        //    46: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite0:()Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload_0        
        //    52: aload_1        
        //    53: ldc             "Struct"
        //    55: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getConstant2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: bipush          8
        //    60: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: dup            
        //    64: iconst_0       
        //    65: aload_0        
        //    66: aload_1        
        //    67: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    70: ldc             "proto"
        //    72: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    75: aastore        
        //    76: dup            
        //    77: iconst_1       
        //    78: aload_0        
        //    79: aload_1        
        //    80: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    83: ldc             "rq"
        //    85: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    88: aastore        
        //    89: dup            
        //    90: iconst_2       
        //    91: aload_0        
        //    92: aload_1        
        //    93: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    96: ldc             "sq"
        //    98: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   101: aastore        
        //   102: dup            
        //   103: iconst_3       
        //   104: aload_0        
        //   105: aload_1        
        //   106: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   109: ldc             "local_addr"
        //   111: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   114: aastore        
        //   115: dup            
        //   116: iconst_4       
        //   117: aload_0        
        //   118: aload_1        
        //   119: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   122: ldc             "local_port"
        //   124: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   127: aastore        
        //   128: dup            
        //   129: iconst_5       
        //   130: aload_0        
        //   131: aload_1        
        //   132: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   135: ldc             "foreign_addr"
        //   137: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   140: aastore        
        //   141: dup            
        //   142: bipush          6
        //   144: aload_0        
        //   145: aload_1        
        //   146: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   149: ldc             "foreign_port"
        //   151: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getSymbol6:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   154: aastore        
        //   155: dup            
        //   156: bipush          7
        //   158: aload_0        
        //   159: aload_1        
        //   160: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   163: ldc             "state"
        //   165: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getSymbol7:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   168: aastore        
        //   169: aload_1        
        //   170: aload_2        
        //   171: aload_0        
        //   172: aload_1        
        //   173: ldc             "block_0$RUBY$Netstat,-1,,false,0,./lib//macOS/netstat.rb,6,true"
        //   175: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   178: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   181: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   184: aload_1        
        //   185: ldc             "Connection"
        //   187: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   190: pop            
        //   191: aload_1        
        //   192: aload_2        
        //   193: aload_2        
        //   194: aload_0        
        //   195: ldc_w           "cmd_for_protocol"
        //   198: ldc_w           "method__3$RUBY$cmd_for_protocol"
        //   201: ldc_w           "proto,1,0,-1"
        //   204: iconst_1       
        //   205: ldc             "./lib//macOS/netstat.rb"
        //   207: ldc_w           12
        //   210: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   213: ldc_w           "qproto"
        //   216: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   219: pop            
        //   220: aload_1        
        //   221: aload_2        
        //   222: aload_2        
        //   223: aload_0        
        //   224: ldc_w           "output_for_protocol"
        //   227: ldc_w           "method__4$RUBY$output_for_protocol"
        //   230: ldc_w           "proto,1,0,-1"
        //   233: iconst_1       
        //   234: ldc             "./lib//macOS/netstat.rb"
        //   236: ldc_w           16
        //   239: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   242: ldc_w           "qproto"
        //   245: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   248: pop            
        //   249: aload_1        
        //   250: aload_2        
        //   251: aload_2        
        //   252: aload_0        
        //   253: ldc_w           "each_connection"
        //   256: ldc_w           "method__5$RUBY$each_connection"
        //   259: ldc_w           "proto,1,0,-1"
        //   262: iconst_1       
        //   263: ldc             "./lib//macOS/netstat.rb"
        //   265: ldc             20
        //   267: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   270: ldc_w           "qproto"
        //   273: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   276: aload_1        
        //   277: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   280: goto            288
        //   283: aload_1        
        //   284: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   287: athrow         
        //   288: aload_1        
        //   289: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   292: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  45     248     4     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  45     280    283    288    Any
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
    
    public static IRubyObject block_0$RUBY$Netstat(final FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278 scriptObject, final ThreadContext context, final IRubyObject self, final IRubyObject rubyObject, final Block block) {
        context.getCurrentScope();
        RuntimeHelpers.processBlockArgument(context.runtime, block);
        return RuntimeHelpers.def(context, self, scriptObject, "listening?", "method__2$RUBY$listening_p_", ",0,0,-1", 0, "./lib//macOS/netstat.rb", 7, CallConfiguration.FrameNoneScopeNone, "NONE");
    }
    
    @JRubyMethod(name = "listening?", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__2$RUBY$listening_p_(final FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278 file_F25942E4E97A72601A44C76801BF6E0AF9E9E278, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        IRubyObject rubyObject2;
        if ((rubyObject2 = file_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite1().call(threadContext, rubyObject, file_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite2().call(threadContext, rubyObject, rubyObject), file_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getString1(threadContext.runtime, 32))).isTrue()) {
            rubyObject2 = file_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite3().call(threadContext, rubyObject, file_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite4().call(threadContext, rubyObject, rubyObject), 0L);
        }
        return rubyObject2;
    }
    
    @JRubyMethod(name = "cmd_for_protocol", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$cmd_for_protocol(final FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278 file_F25942E4E97A72601A44C76801BF6E0AF9E9E278, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return RubyString.newStringLight(threadContext.runtime, 20).append(file_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getString2(threadContext.runtime, 32)).append(rubyObject2.asString());
    }
    
    @JRubyMethod(name = "output_for_protocol", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$output_for_protocol(final FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278 file_F25942E4E97A72601A44C76801BF6E0AF9E9E278, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite5().call(threadContext, rubyObject, rubyObject, RubyString.newStringLight(threadContext.runtime, 20).append(file_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite6().call(threadContext, rubyObject, rubyObject, rubyObject2).asString()));
    }
    
    @JRubyMethod(name = "each_connection", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$each_connection(final FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    14: aload_0        
        //    15: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite7:()Lorg/jruby/runtime/CallSite;
        //    18: aload_1        
        //    19: aload_2        
        //    20: aload_2        
        //    21: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    24: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    29: ifeq            76
        //    32: aload_0        
        //    33: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite8:()Lorg/jruby/runtime/CallSite;
        //    36: aload_1        
        //    37: aload_2        
        //    38: aload_0        
        //    39: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite9:()Lorg/jruby/runtime/CallSite;
        //    42: aload_1        
        //    43: aload_2        
        //    44: aload_2        
        //    45: aload           locals
        //    47: aload_1        
        //    48: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: aload_1        
        //    58: aload_2        
        //    59: aload_0        
        //    60: aload_1        
        //    61: ldc_w           "block_1$RUBY$each_connection,1,line;rq_s;sq_s;la_p;fa_p;st;rq;sq;la;lp;fa;fp,false,2,./lib//macOS/netstat.rb,22,false"
        //    64: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getBlockBody1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    67: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    70: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: goto            115
        //    76: aload_0        
        //    77: bipush          28
        //    79: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    82: aload_1        
        //    83: aload_2        
        //    84: aload_0        
        //    85: aload_1        
        //    86: ldc             "Enumerator"
        //    88: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getConstant4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    91: aload_2        
        //    92: aload_0        
        //    93: aload_1        
        //    94: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    97: ldc_w           "each_connection"
        //   100: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getSymbol8:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   103: aload           locals
        //   105: aload_1        
        //   106: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   109: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   112: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   115: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     102     5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_1$RUBY$each_connection(final FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          5
        //     6: aload           5
        //     8: invokevirtual   org/jruby/runtime/DynamicScope.getValues:()[Lorg/jruby/runtime/builtin/IRubyObject;
        //    11: astore          6
        //    13: aload           6
        //    15: aload_1        
        //    16: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    19: aload           5
        //    21: swap           
        //    22: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    25: aload           5
        //    27: swap           
        //    28: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    31: aload           5
        //    33: swap           
        //    34: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    37: aload           5
        //    39: swap           
        //    40: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: pop            
        //    44: aload_1        
        //    45: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    48: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.fillNil:([Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;)V
        //    51: aload_1        
        //    52: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    55: aload           4
        //    57: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: aload_3        
        //    61: aload           5
        //    63: swap           
        //    64: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: pop            
        //    68: pop            
        //    69: aload           locals
        //    71: aload_1        
        //    72: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    78: aload_1        
        //    79: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //    82: astore          9
        //    84: aload_0        
        //    85: bipush          10
        //    87: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    90: aload_1        
        //    91: aload_2        
        //    92: aload           9
        //    94: aload_0        
        //    95: aload_1        
        //    96: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    99: aload_0        
        //   100: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getByteList3:()Lorg/jruby/util/ByteList;
        //   103: ldc_w           512
        //   106: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getRegexp0:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   109: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   112: ifeq            682
        //   115: aload_0        
        //   116: bipush          11
        //   118: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   121: aload_1        
        //   122: aload_2        
        //   123: aload_0        
        //   124: bipush          12
        //   126: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   129: aload_1        
        //   130: aload_2        
        //   131: aload           locals
        //   133: aload_1        
        //   134: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   137: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   140: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   143: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   146: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.splatValue:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   149: aload_1        
        //   150: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   153: iconst_1       
        //   154: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.ensureMultipleAssignableRubyArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;Z)Lorg/jruby/RubyArray;
        //   157: astore          10
        //   159: aload           10
        //   161: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilZero:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   164: aload           locals
        //   166: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   169: swap           
        //   170: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   173: pop            
        //   174: aload           10
        //   176: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilOne:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   179: aload           locals
        //   181: swap           
        //   182: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   185: pop            
        //   186: aload           10
        //   188: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilTwo:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   191: aload           locals
        //   193: swap           
        //   194: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   197: pop            
        //   198: aload           10
        //   200: iconst_3       
        //   201: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNil:(Lorg/jruby/RubyArray;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   204: aload           locals
        //   206: swap           
        //   207: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   210: pop            
        //   211: aload           10
        //   213: iconst_4       
        //   214: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNil:(Lorg/jruby/RubyArray;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   217: aload           6
        //   219: swap           
        //   220: iconst_4       
        //   221: swap           
        //   222: aastore        
        //   223: aload           10
        //   225: iconst_5       
        //   226: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNil:(Lorg/jruby/RubyArray;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   229: aload           6
        //   231: swap           
        //   232: iconst_5       
        //   233: swap           
        //   234: aastore        
        //   235: aload           10
        //   237: pop            
        //   238: aload           6
        //   240: bipush          6
        //   242: aload_0        
        //   243: bipush          13
        //   245: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   248: aload_1        
        //   249: aload_2        
        //   250: aload           locals
        //   252: aload_1        
        //   253: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   256: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   259: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   262: aastore        
        //   263: aload           6
        //   265: bipush          7
        //   267: aload_0        
        //   268: bipush          14
        //   270: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   273: aload_1        
        //   274: aload_2        
        //   275: aload           locals
        //   277: aload_1        
        //   278: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   281: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   284: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   287: aastore        
        //   288: aload           6
        //   290: bipush          8
        //   292: aload_0        
        //   293: bipush          15
        //   295: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   298: aload_1        
        //   299: aload_2        
        //   300: aload_0        
        //   301: bipush          16
        //   303: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   306: aload_1        
        //   307: aload_2        
        //   308: aload_0        
        //   309: bipush          17
        //   311: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   314: aload_1        
        //   315: aload_2        
        //   316: aload           locals
        //   318: aload_1        
        //   319: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   322: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   325: aload_0        
        //   326: aload_1        
        //   327: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   330: bipush          32
        //   332: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getString4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   335: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   338: aload_1        
        //   339: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   342: aload_1        
        //   343: aload_1        
        //   344: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   347: invokestatic    org/jruby/RubyFixnum.zero:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   350: aload_0        
        //   351: aload_1        
        //   352: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   355: bipush          -2
        //   357: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getFixnum0:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   360: invokestatic    org/jruby/RubyRange.newInclusiveRange:(Lorg/jruby/Ruby;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyRange;
        //   363: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   366: aload_0        
        //   367: aload_1        
        //   368: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   371: bipush          32
        //   373: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getString4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   376: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   379: aastore        
        //   380: aload           6
        //   382: bipush          9
        //   384: aload_0        
        //   385: bipush          18
        //   387: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   390: aload_1        
        //   391: aload_2        
        //   392: aload_0        
        //   393: bipush          19
        //   395: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   398: aload_1        
        //   399: aload_2        
        //   400: aload_0        
        //   401: bipush          20
        //   403: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   406: aload_1        
        //   407: aload_2        
        //   408: aload           locals
        //   410: aload_1        
        //   411: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   414: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   417: aload_0        
        //   418: aload_1        
        //   419: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   422: bipush          32
        //   424: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getString4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   427: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   430: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   433: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   436: aastore        
        //   437: aload           6
        //   439: bipush          10
        //   441: aload_0        
        //   442: bipush          21
        //   444: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   447: aload_1        
        //   448: aload_2        
        //   449: aload_0        
        //   450: bipush          22
        //   452: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   455: aload_1        
        //   456: aload_2        
        //   457: aload_0        
        //   458: bipush          23
        //   460: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   463: aload_1        
        //   464: aload_2        
        //   465: aload           6
        //   467: iconst_4       
        //   468: aaload         
        //   469: aload_0        
        //   470: aload_1        
        //   471: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   474: bipush          32
        //   476: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getString4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   479: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   482: aload_1        
        //   483: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   486: aload_1        
        //   487: aload_1        
        //   488: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   491: invokestatic    org/jruby/RubyFixnum.zero:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   494: aload_0        
        //   495: aload_1        
        //   496: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   499: bipush          -2
        //   501: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getFixnum0:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   504: invokestatic    org/jruby/RubyRange.newInclusiveRange:(Lorg/jruby/Ruby;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyRange;
        //   507: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   510: aload_0        
        //   511: aload_1        
        //   512: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   515: bipush          32
        //   517: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getString4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   520: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   523: aastore        
        //   524: aload           6
        //   526: bipush          11
        //   528: aload_0        
        //   529: bipush          24
        //   531: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   534: aload_1        
        //   535: aload_2        
        //   536: aload_0        
        //   537: bipush          25
        //   539: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   542: aload_1        
        //   543: aload_2        
        //   544: aload_0        
        //   545: bipush          26
        //   547: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   550: aload_1        
        //   551: aload_2        
        //   552: aload           6
        //   554: iconst_4       
        //   555: aaload         
        //   556: aload_0        
        //   557: aload_1        
        //   558: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   561: bipush          32
        //   563: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getString4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   566: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   569: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   572: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   575: aastore        
        //   576: aload_1        
        //   577: invokevirtual   org/jruby/runtime/ThreadContext.getFrameBlock:()Lorg/jruby/runtime/Block;
        //   580: aload_1        
        //   581: aload_0        
        //   582: bipush          27
        //   584: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   587: aload_1        
        //   588: aload_2        
        //   589: aload_0        
        //   590: aload_1        
        //   591: ldc             "Connection"
        //   593: invokevirtual   ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.getConstant3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   596: bipush          8
        //   598: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //   601: dup            
        //   602: iconst_0       
        //   603: aload           locals
        //   605: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   608: aload_1        
        //   609: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   612: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   615: aastore        
        //   616: dup            
        //   617: iconst_1       
        //   618: aload           6
        //   620: bipush          6
        //   622: aaload         
        //   623: aastore        
        //   624: dup            
        //   625: iconst_2       
        //   626: aload           6
        //   628: bipush          7
        //   630: aaload         
        //   631: aastore        
        //   632: dup            
        //   633: iconst_3       
        //   634: aload           6
        //   636: bipush          8
        //   638: aaload         
        //   639: aastore        
        //   640: dup            
        //   641: iconst_4       
        //   642: aload           6
        //   644: bipush          9
        //   646: aaload         
        //   647: aastore        
        //   648: dup            
        //   649: iconst_5       
        //   650: aload           6
        //   652: bipush          10
        //   654: aaload         
        //   655: aastore        
        //   656: dup            
        //   657: bipush          6
        //   659: aload           6
        //   661: bipush          11
        //   663: aaload         
        //   664: aastore        
        //   665: dup            
        //   666: bipush          7
        //   668: aload           6
        //   670: iconst_5       
        //   671: aaload         
        //   672: aastore        
        //   673: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   676: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   679: goto            686
        //   682: aload_1        
        //   683: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   686: areturn        
        //   687: pop            
        //   688: goto            69
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  69     618     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  69     687    687    691    Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    public static IRubyObject module__1$RUBY$Netstat(final FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278 file_F25942E4E97A72601A44C76801BF6E0AF9E9E278, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Netstat(file_F25942E4E97A72601A44C76801BF6E0AF9E9E278, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$MacOs(final FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278 file_F25942E4E97A72601A44C76801BF6E0AF9E9E278, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$MacOs(file_F25942E4E97A72601A44C76801BF6E0AF9E9E278, threadContext, rubyObject, block);
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
        final FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278 file_F25942E4E97A72601A44C76801BF6E0AF9E9E278 = new FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278();
        final String string = FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.class.getClassLoader().getResource("ruby/jit/FILE_F25942E4E97A72601A44C76801BF6E0AF9E9E278.class").toString();
        file_F25942E4E97A72601A44C76801BF6E0AF9E9E278.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_F25942E4E97A72601A44C76801BF6E0AF9E9E278.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
