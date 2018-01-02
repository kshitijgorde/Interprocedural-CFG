// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.RubyFixnum;
import org.jruby.exceptions.JumpException;
import org.jruby.RubyBoolean;
import org.jruby.runtime.DynamicScope;
import org.jruby.runtime.Arity;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 extends AbstractScript
{
    public FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5() {
        this.filename = "./lib//macOS/zeroconf.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffinclude_class\uffffF\uffffDNSSD\uffffN\uffffdnssd\uffffN\uffffapple\uffffN\uffffcom\uffffV\uffffinclude_class\uffffF\uffffBrowseListener\uffffN\uffffdnssd\uffffN\uffffapple\uffffN\uffffcom\uffffV\uffffinclude_class\uffffF\uffffResolveListener\uffffN\uffffdnssd\uffffN\uffffapple\uffffN\uffffcom\uffffV\uffffinclude_class\uffffF\uffffQueryListener\uffffN\uffffdnssd\uffffN\uffffapple\uffffN\uffffcom\uffffV\uffffinclude\uffffF\uffffattr_reader\uffffF\uffffbrowse\uffffN\uffffadd_browser\uffffN\uffffparent\uffffV\uffffstop\uffffN\uffffstart_browsing_on\uffffN\uffffparent\uffffV\uffffnew\uffffN\uffffnew\uffffN\uffff[]\uffffF\uffffbrowsers\uffffV\uffffnew\uffffN\uffff[]=\uffffF\uffff<<\uffffN\uffff[]\uffffN\uffffbrowsers\uffffV\uffffsynchronize\uffffF\uffffappend_browser\uffffF\uffffappend_browser\uffffF\uffffnew\uffffN\uffffnew\uffffN\uffffresolvers\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffdelete\uffffN\uffffresolvers\uffffV\uffff[]\uffffN\uffffresolvers\uffffV\uffffnew\uffffN\uffffnew\uffffN\uffff<<\uffffN\uffffqueriers\uffffV\uffffdelete\uffffN\uffffqueriers\uffffV\uffff===\uffffN\uffff+\uffffN\uffff===\uffffN\uffff+\uffffN\uffffsynchronize\uffffF\uffffadd_browser\uffffF\uffffbrowse\uffffN\uffffinclude\uffffF\uffffinclude\uffffF\uffffinclude\uffffF\uffffattr_reader\uffffF\uffffnew\uffffN\uffffnew\uffffN\uffffnew\uffffN\uffffsynchronize\uffffN\uffffmutex\uffffV\uffffstart\uffffN\uffffnew\uffffN\uffffAppleDNSSDException\uffffN\uffffdnssd\uffffN\uffffapple\uffffN\uffffcom\uffffV\ufffftimeout\uffffN\uffffloop\uffffF\uffffsleep\uffffF\uffffsynchronize\uffffF\uffffeach_pair\uffffN\uffffbrowsers\uffffV\uffffeach\uffffN\uffffstop\uffffN\uffffeach\uffffN\uffffkeys\uffffN\uffffresolvers\uffffV\uffffstop\uffffN\uffffeach\uffffN\uffffqueriers\uffffV\uffffstop\uffffN\uffffsynchronize\uffffF\uffffresolve\uffffN\uffffadd_resolver\uffffF\uffffnew\uffffN\uffffsynchronize\uffffF\uffffnew\uffffN\uffffget_resolver_regtype\uffffF\uffff<<\uffffN\uffffservices\uffffV\uffffstop\uffffN\uffffremove_resolver\uffffF\uffffquery_record\uffffN\uffffadd_querier\uffffF\uffffeach\uffffN\uffffselect\uffffN\uffffservices\uffffV\uffff==\uffffN\uffffhost\uffffN\uffffjoin\uffffN\uffffunpack\uffffN\uffffpack\uffffN\uffffto_a\uffffN\uffffip=\uffffN\uffffip=\uffffV\uffffsynchronize\uffffF\uffffstop\uffffN\uffffremove_querier\uffffF\uffff\u0004\f\u0002\u0000\u001a\u0002\u0000\u0005\u000b\u000f\u0000\u0000\f\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(8, "_udp", this.getEncoding0());
        this.setByteList(10, "C*", this.getEncoding0());
        this.setByteList(4, "_services._dns-sd._udp", this.getEncoding0());
        this.setByteList(2, "thread", this.getEncoding0());
        this.setByteList(0, "java", this.getEncoding0());
        this.setByteList(7, "._tcp.", this.getEncoding0());
        this.setByteList(6, "_tcp", this.getEncoding0());
        this.setByteList(11, ".", this.getEncoding0());
        this.setByteList(3, "vendor/dns_sd.jar", this.getEncoding0());
        this.setByteList(1, "timeout", this.getEncoding0());
        this.setByteList(9, "._udp.", this.getEncoding0());
        this.setByteList(5, "instance-variable", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite0().call(threadContext, rubyObject, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getString0(threadContext.runtime, 32));
        file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite1().call(threadContext, rubyObject, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getString1(threadContext.runtime, 32));
        file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite2().call(threadContext, rubyObject, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getString2(threadContext.runtime, 32));
        file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite3().call(threadContext, rubyObject, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getString3(threadContext.runtime, 32));
        file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite4().call(threadContext, rubyObject, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite5().call(threadContext, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite6().call(threadContext, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite7().call(threadContext, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite8().call(threadContext, rubyObject, rubyObject)))));
        file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite9().call(threadContext, rubyObject, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(10).call(threadContext, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(11).call(threadContext, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(12).call(threadContext, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(13).call(threadContext, rubyObject, rubyObject)))));
        file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(14).call(threadContext, rubyObject, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(15).call(threadContext, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(16).call(threadContext, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(17).call(threadContext, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(18).call(threadContext, rubyObject, rubyObject)))));
        file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(19).call(threadContext, rubyObject, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(20).call(threadContext, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(21).call(threadContext, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(22).call(threadContext, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(23).call(threadContext, rubyObject, rubyObject)))));
        return module__0$RUBY$Darwin(file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Darwin(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Darwin"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    33: invokestatic    ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.class_1$RUBY$ServiceDiscoverer:(Lruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    36: pop            
        //    37: aload_0        
        //    38: aload_1        
        //    39: aload_2        
        //    40: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    43: invokestatic    ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.class_7$RUBY$ZeroconfCounter:(Lruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    46: aload_1        
        //    47: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: goto            58
        //    53: aload_1        
        //    54: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    57: athrow         
        //    58: aload_1        
        //    59: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    62: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     50     53     58     Any
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
    
    public static IRubyObject class_1$RUBY$ServiceDiscoverer(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: aload_1        
        //     2: aload_1        
        //     3: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     6: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     9: swap           
        //    10: ldc             "ServiceDiscoverer"
        //    12: swap           
        //    13: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    16: dup            
        //    17: astore_2       
        //    18: aload_1        
        //    19: swap           
        //    20: aload_0        
        //    21: aload_1        
        //    22: ldc             ",0,0,-1"
        //    24: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    27: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    30: aload_0        
        //    31: bipush          24
        //    33: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    36: aload_1        
        //    37: aload_2        
        //    38: aload_2        
        //    39: aload_0        
        //    40: aload_1        
        //    41: ldc             "BrowseListener"
        //    43: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    46: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: pop            
        //    50: aload_0        
        //    51: bipush          25
        //    53: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    56: aload_1        
        //    57: aload_2        
        //    58: aload_2        
        //    59: aload_0        
        //    60: aload_1        
        //    61: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    64: ldc             "parent"
        //    66: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    69: aload_0        
        //    70: aload_1        
        //    71: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    74: ldc             "browser"
        //    76: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    79: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: pop            
        //    83: aload_1        
        //    84: aload_2        
        //    85: aload_0        
        //    86: ldc             "initialize"
        //    88: ldc             "method__2$RUBY$initialize"
        //    90: ldc             "parent,1,0,-1"
        //    92: iconst_1       
        //    93: ldc             "./lib//macOS/zeroconf.rb"
        //    95: ldc             16
        //    97: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   100: ldc             "qparent"
        //   102: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: pop            
        //   106: aload_1        
        //   107: aload_2        
        //   108: aload_0        
        //   109: ldc             "start"
        //   111: ldc             "method__3$RUBY$start"
        //   113: ldc             "reg_type,0,0,-1"
        //   115: iconst_0       
        //   116: ldc             "./lib//macOS/zeroconf.rb"
        //   118: ldc             20
        //   120: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   123: ldc             "NONE"
        //   125: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   128: pop            
        //   129: aload_1        
        //   130: aload_2        
        //   131: aload_0        
        //   132: ldc             "stop"
        //   134: ldc             "method__4$RUBY$stop"
        //   136: ldc             ",0,0,-1"
        //   138: iconst_0       
        //   139: ldc             "./lib//macOS/zeroconf.rb"
        //   141: ldc             26
        //   143: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   146: ldc             "NONE"
        //   148: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   151: pop            
        //   152: aload_1        
        //   153: aload_2        
        //   154: aload_0        
        //   155: ldc             "serviceFound"
        //   157: ldc             "method__5$RUBY$serviceFound"
        //   159: ldc             "browser;flags;iface_idx;service_name;reg_type;domain,6,0,-1"
        //   161: bipush          6
        //   163: ldc             "./lib//macOS/zeroconf.rb"
        //   165: ldc             30
        //   167: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   170: ldc             "qbrowser;qflags;qiface_idx;qservice_name;qreg_type;qdomain"
        //   172: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   175: pop            
        //   176: aload_1        
        //   177: aload_2        
        //   178: aload_0        
        //   179: ldc_w           "serviceLost"
        //   182: ldc_w           "method__6$RUBY$serviceLost"
        //   185: ldc_w           "args,0,0,0"
        //   188: iconst_m1      
        //   189: ldc             "./lib//macOS/zeroconf.rb"
        //   191: ldc_w           35
        //   194: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   197: ldc_w           "rargs"
        //   200: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   203: aload_1        
        //   204: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   207: goto            215
        //   210: aload_1        
        //   211: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   214: athrow         
        //   215: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  30     203    210    215    Any
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
    
    @JRubyMethod(name = "initialize", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__2$RUBY$initialize(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject object, final IRubyObject value, final Block block) {
        return file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.setVariable0(threadContext.runtime, "@parent", object, value);
    }
    
    @JRubyMethod(name = "start", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$start(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext context, final IRubyObject object, final Block block) {
        IRubyObject reg_type = context.nil;
        reg_type = file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getString4(context.runtime, 32);
        file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.setVariable1(context.runtime, "@browser", object, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(26).call(context, object, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getConstant1(context, "DNSSD"), reg_type, object));
        return file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(27).call(context, object, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(28).call(context, object, object), reg_type, object);
    }
    
    @JRubyMethod(name = "stop", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$stop(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        return file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(29).call(threadContext, object, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getVariable0(threadContext.runtime, "@browser", object));
    }
    
    @JRubyMethod(name = "serviceFound", frame = true, required = 6, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$serviceFound(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //     4: aload_3        
        //     5: bipush          6
        //     7: bipush          6
        //     9: invokestatic    org/jruby/runtime/Arity.raiseArgumentError:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;II)V
        //    12: aload_3        
        //    13: iconst_0       
        //    14: aaload         
        //    15: astore          9
        //    17: aload_3        
        //    18: iconst_1       
        //    19: aaload         
        //    20: astore          10
        //    22: aload_3        
        //    23: iconst_2       
        //    24: aaload         
        //    25: astore          11
        //    27: aload_3        
        //    28: iconst_3       
        //    29: aaload         
        //    30: astore          12
        //    32: aload_3        
        //    33: iconst_4       
        //    34: aaload         
        //    35: astore          13
        //    37: aload_3        
        //    38: iconst_5       
        //    39: aaload         
        //    40: astore          domain
        //    42: aload_0        
        //    43: bipush          30
        //    45: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    48: aload_1        
        //    49: aload_2        
        //    50: aload_0        
        //    51: bipush          31
        //    53: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    56: aload_1        
        //    57: aload_2        
        //    58: aload_2        
        //    59: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    62: aload           service_name
        //    64: aload           reg_type
        //    66: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    69: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name          Signature
        //  -----  ------  ----  ------------  ---------------------------------------
        //  42     28      9     browser       Lorg/jruby/runtime/builtin/IRubyObject;
        //  42     28      10    flags         Lorg/jruby/runtime/builtin/IRubyObject;
        //  42     28      11    iface_idx     Lorg/jruby/runtime/builtin/IRubyObject;
        //  42     28      12    service_name  Lorg/jruby/runtime/builtin/IRubyObject;
        //  42     28      13    reg_type      Lorg/jruby/runtime/builtin/IRubyObject;
        //  42     28      14    domain        Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "serviceLost", frame = true, required = 0, optional = 0, rest = 0)
    public static IRubyObject method__6$RUBY$serviceLost(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] input, final Block block) {
        final IRubyObject nil = threadContext.nil;
        final IRubyObject args = RuntimeHelpers.createSubarray(input, threadContext.runtime, 0);
        return threadContext.nil;
    }
    
    public static IRubyObject class_1$RUBY$ServiceDiscoverer(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_1$RUBY$ServiceDiscoverer(file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_7$RUBY$ZeroconfCounter(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: aload_1        
        //     2: aload_1        
        //     3: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     6: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     9: swap           
        //    10: ldc_w           "ZeroconfCounter"
        //    13: swap           
        //    14: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    17: dup            
        //    18: astore_2       
        //    19: aload_1        
        //    20: swap           
        //    21: aload_0        
        //    22: aload_1        
        //    23: ldc             ",0,0,-1"
        //    25: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    28: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_0        
        //    34: ldc_w           "browsers"
        //    37: ldc_w           "method__8$RUBY$browsers"
        //    40: ldc             ",0,0,-1"
        //    42: iconst_0       
        //    43: ldc             "./lib//macOS/zeroconf.rb"
        //    45: ldc_w           42
        //    48: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    51: ldc             "NONE"
        //    53: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    56: pop            
        //    57: aload_1        
        //    58: aload_2        
        //    59: aload_0        
        //    60: ldc_w           "append_browser"
        //    63: ldc_w           "method__9$RUBY$append_browser"
        //    66: ldc_w           "reg_type;browser,2,0,-1"
        //    69: iconst_2       
        //    70: ldc             "./lib//macOS/zeroconf.rb"
        //    72: ldc_w           46
        //    75: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    78: ldc_w           "qreg_type;qbrowser"
        //    81: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    84: pop            
        //    85: aload_1        
        //    86: aload_2        
        //    87: aload_0        
        //    88: ldc_w           "add_browser"
        //    91: ldc_w           "method__10$RUBY$add_browser"
        //    94: ldc_w           "reg_type;browser;lock,2,1,-1"
        //    97: bipush          -3
        //    99: ldc             "./lib//macOS/zeroconf.rb"
        //   101: ldc_w           51
        //   104: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   107: ldc_w           "qreg_type;qbrowser;olock"
        //   110: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   113: pop            
        //   114: aload_1        
        //   115: aload_2        
        //   116: aload_0        
        //   117: ldc_w           "resolvers"
        //   120: ldc_w           "method__11$RUBY$resolvers"
        //   123: ldc             ",0,0,-1"
        //   125: iconst_0       
        //   126: ldc             "./lib//macOS/zeroconf.rb"
        //   128: ldc_w           61
        //   131: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   134: ldc             "NONE"
        //   136: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   139: pop            
        //   140: aload_1        
        //   141: aload_2        
        //   142: aload_0        
        //   143: ldc_w           "add_resolver"
        //   146: ldc_w           "method__12$RUBY$add_resolver"
        //   149: ldc_w           "resolver;reg_type,2,0,-1"
        //   152: iconst_2       
        //   153: ldc             "./lib//macOS/zeroconf.rb"
        //   155: ldc_w           65
        //   158: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   161: ldc_w           "qresolver;qreg_type"
        //   164: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   167: pop            
        //   168: aload_1        
        //   169: aload_2        
        //   170: aload_0        
        //   171: ldc_w           "remove_resolver"
        //   174: ldc_w           "method__13$RUBY$remove_resolver"
        //   177: ldc_w           "resolver,1,0,-1"
        //   180: iconst_1       
        //   181: ldc             "./lib//macOS/zeroconf.rb"
        //   183: ldc_w           69
        //   186: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   189: ldc_w           "qresolver"
        //   192: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   195: pop            
        //   196: aload_1        
        //   197: aload_2        
        //   198: aload_0        
        //   199: ldc_w           "get_resolver_regtype"
        //   202: ldc_w           "method__14$RUBY$get_resolver_regtype"
        //   205: ldc_w           "resolver,1,0,-1"
        //   208: iconst_1       
        //   209: ldc             "./lib//macOS/zeroconf.rb"
        //   211: ldc_w           73
        //   214: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   217: ldc_w           "qresolver"
        //   220: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   223: pop            
        //   224: aload_1        
        //   225: aload_2        
        //   226: aload_0        
        //   227: ldc_w           "queriers"
        //   230: ldc_w           "method__15$RUBY$queriers"
        //   233: ldc             ",0,0,-1"
        //   235: iconst_0       
        //   236: ldc             "./lib//macOS/zeroconf.rb"
        //   238: ldc_w           77
        //   241: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   244: ldc             "NONE"
        //   246: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   249: pop            
        //   250: aload_1        
        //   251: aload_2        
        //   252: aload_0        
        //   253: ldc_w           "add_querier"
        //   256: ldc_w           "method__16$RUBY$add_querier"
        //   259: ldc_w           "querier,1,0,-1"
        //   262: iconst_1       
        //   263: ldc             "./lib//macOS/zeroconf.rb"
        //   265: ldc_w           81
        //   268: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   271: ldc_w           "qquerier"
        //   274: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   277: pop            
        //   278: aload_1        
        //   279: aload_2        
        //   280: aload_0        
        //   281: ldc_w           "remove_querier"
        //   284: ldc_w           "method__17$RUBY$remove_querier"
        //   287: ldc_w           "querier,1,0,-1"
        //   290: iconst_1       
        //   291: ldc             "./lib//macOS/zeroconf.rb"
        //   293: ldc_w           85
        //   296: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   299: ldc_w           "qquerier"
        //   302: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   305: pop            
        //   306: aload_1        
        //   307: aload_2        
        //   308: aload_0        
        //   309: ldc_w           "start_browsing_on"
        //   312: ldc_w           "method__18$RUBY$start_browsing_on"
        //   315: ldc_w           "service_name;reg_type;registration,2,0,-1"
        //   318: iconst_2       
        //   319: ldc             "./lib//macOS/zeroconf.rb"
        //   321: ldc_w           89
        //   324: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   327: ldc_w           "qservice_name;qreg_type"
        //   330: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   333: pop            
        //   334: aload_0        
        //   335: bipush          66
        //   337: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   340: aload_1        
        //   341: aload_2        
        //   342: aload_2        
        //   343: aload_0        
        //   344: aload_1        
        //   345: ldc             "BrowseListener"
        //   347: bipush          10
        //   349: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   352: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   355: pop            
        //   356: aload_0        
        //   357: bipush          67
        //   359: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   362: aload_1        
        //   363: aload_2        
        //   364: aload_2        
        //   365: aload_0        
        //   366: aload_1        
        //   367: ldc_w           "ResolveListener"
        //   370: bipush          11
        //   372: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   375: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   378: pop            
        //   379: aload_0        
        //   380: bipush          68
        //   382: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   385: aload_1        
        //   386: aload_2        
        //   387: aload_2        
        //   388: aload_0        
        //   389: aload_1        
        //   390: ldc_w           "QueryListener"
        //   393: bipush          12
        //   395: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   398: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   401: pop            
        //   402: aload_0        
        //   403: bipush          69
        //   405: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   408: aload_1        
        //   409: aload_2        
        //   410: aload_2        
        //   411: aload_0        
        //   412: aload_1        
        //   413: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   416: ldc_w           "start_time"
        //   419: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   422: aload_0        
        //   423: aload_1        
        //   424: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   427: ldc_w           "operation"
        //   430: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   433: aload_0        
        //   434: aload_1        
        //   435: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   438: ldc_w           "services"
        //   441: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   444: aload_0        
        //   445: aload_1        
        //   446: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   449: ldc_w           "mutex"
        //   452: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   455: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   458: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   461: pop            
        //   462: aload_1        
        //   463: aload_2        
        //   464: aload_0        
        //   465: ldc             "initialize"
        //   467: ldc_w           "method__19$RUBY$initialize"
        //   470: ldc             ",0,0,-1"
        //   472: iconst_0       
        //   473: ldc             "./lib//macOS/zeroconf.rb"
        //   475: ldc_w           112
        //   478: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   481: ldc             "NONE"
        //   483: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   486: pop            
        //   487: aload_1        
        //   488: aload_2        
        //   489: aload_0        
        //   490: ldc_w           "services"
        //   493: ldc_w           "method__20$RUBY$services"
        //   496: ldc             ",0,0,-1"
        //   498: iconst_0       
        //   499: ldc             "./lib//macOS/zeroconf.rb"
        //   501: ldc_w           116
        //   504: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   507: ldc             "NONE"
        //   509: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   512: pop            
        //   513: aload_1        
        //   514: aload_2        
        //   515: aload_0        
        //   516: ldc_w           "synchronize"
        //   519: ldc_w           "method__21$RUBY$synchronize"
        //   522: ldc             ",0,0,-1"
        //   524: iconst_0       
        //   525: ldc             "./lib//macOS/zeroconf.rb"
        //   527: ldc_w           120
        //   530: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   533: ldc             "NONE"
        //   535: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   538: pop            
        //   539: aload_1        
        //   540: aload_2        
        //   541: aload_0        
        //   542: ldc_w           "run"
        //   545: ldc_w           "method__22$RUBY$run"
        //   548: ldc_w           "timeout,1,0,-1"
        //   551: iconst_1       
        //   552: ldc             "./lib//macOS/zeroconf.rb"
        //   554: ldc_w           126
        //   557: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   560: ldc_w           "qtimeout"
        //   563: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   566: pop            
        //   567: aload_1        
        //   568: aload_2        
        //   569: aload_0        
        //   570: ldc             "serviceFound"
        //   572: ldc_w           "method__27$RUBY$serviceFound"
        //   575: ldc             "browser;flags;iface_idx;service_name;reg_type;domain,6,0,-1"
        //   577: bipush          6
        //   579: ldc             "./lib//macOS/zeroconf.rb"
        //   581: ldc_w           157
        //   584: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   587: ldc             "qbrowser;qflags;qiface_idx;qservice_name;qreg_type;qdomain"
        //   589: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   592: pop            
        //   593: aload_1        
        //   594: aload_2        
        //   595: aload_0        
        //   596: ldc_w           "serviceLost"
        //   599: ldc_w           "method__28$RUBY$serviceLost"
        //   602: ldc_w           "args,0,0,0"
        //   605: iconst_m1      
        //   606: ldc             "./lib//macOS/zeroconf.rb"
        //   608: ldc_w           168
        //   611: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   614: ldc_w           "rargs"
        //   617: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   620: pop            
        //   621: aload_0        
        //   622: bipush          99
        //   624: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   627: aload_1        
        //   628: aload_2        
        //   629: aload_0        
        //   630: aload_1        
        //   631: ldc_w           "Struct"
        //   634: bipush          21
        //   636: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   639: bipush          6
        //   641: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //   644: dup            
        //   645: iconst_0       
        //   646: aload_0        
        //   647: aload_1        
        //   648: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   651: ldc_w           "name"
        //   654: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getSymbol6:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   657: aastore        
        //   658: dup            
        //   659: iconst_1       
        //   660: aload_0        
        //   661: aload_1        
        //   662: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   665: ldc_w           "host"
        //   668: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getSymbol7:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   671: aastore        
        //   672: dup            
        //   673: iconst_2       
        //   674: aload_0        
        //   675: aload_1        
        //   676: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   679: ldc_w           "port"
        //   682: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getSymbol8:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   685: aastore        
        //   686: dup            
        //   687: iconst_3       
        //   688: aload_0        
        //   689: aload_1        
        //   690: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   693: ldc_w           "data"
        //   696: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getSymbol9:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   699: aastore        
        //   700: dup            
        //   701: iconst_4       
        //   702: aload_0        
        //   703: aload_1        
        //   704: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   707: ldc_w           10
        //   710: ldc_w           "reg_type"
        //   713: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   716: aastore        
        //   717: dup            
        //   718: iconst_5       
        //   719: aload_0        
        //   720: aload_1        
        //   721: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   724: ldc_w           11
        //   727: ldc_w           "ip"
        //   730: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   733: aastore        
        //   734: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   737: aload_1        
        //   738: ldc_w           "Service"
        //   741: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   744: pop            
        //   745: aload_0        
        //   746: aload_1        
        //   747: aload_2        
        //   748: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //   751: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   754: invokestatic    ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.module__29$RUBY$Records:(Lruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   757: pop            
        //   758: aload_1        
        //   759: aload_2        
        //   760: aload_0        
        //   761: ldc_w           "service_resolved"
        //   764: ldc_w           "method__30$RUBY$service_resolved"
        //   767: ldc_w           "resolver;flags;iface_index;full_name;host_name;port;txt,7,0,-1"
        //   770: bipush          7
        //   772: ldc             "./lib//macOS/zeroconf.rb"
        //   774: ldc_w           180
        //   777: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   780: ldc_w           "qresolver;qflags;qiface_index;qfull_name;qhost_name;qport;qtxt"
        //   783: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   786: pop            
        //   787: aload_1        
        //   788: aload_2        
        //   789: aload_0        
        //   790: ldc_w           "queryAnswered"
        //   793: ldc_w           "method__31$RUBY$queryAnswered"
        //   796: ldc_w           "querier;flags;iface_index;full_name;rrtype;rrclass;rdata;ttl,8,0,-1"
        //   799: bipush          8
        //   801: ldc             "./lib//macOS/zeroconf.rb"
        //   803: ldc_w           194
        //   806: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   809: ldc_w           "qquerier;qflags;qiface_index;qfull_name;qrrtype;qrrclass;qrdata;qttl"
        //   812: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   815: aload_1        
        //   816: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   819: goto            827
        //   822: aload_1        
        //   823: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   826: athrow         
        //   827: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  31     815    822    827    Any
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
    
    @JRubyMethod(name = "browsers", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$browsers(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@browsers") ? file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getByteList5() : null) == null) {
            rubyObject = file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.setVariable2(threadContext.runtime, "@browsers", object, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(32).call(threadContext, object, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getConstant2(threadContext, "Hash")));
        }
        else if (!(rubyObject = file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getVariable1(threadContext.runtime, "@browsers", object)).isTrue()) {
            rubyObject = file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.setVariable3(threadContext.runtime, "@browsers", object, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(33).call(threadContext, object, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getConstant3(threadContext, "Hash")));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "append_browser", frame = true, required = 2, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$append_browser(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final Block p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          6
        //     6: aload_3        
        //     7: aload           6
        //     9: swap           
        //    10: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    13: pop            
        //    14: aload           4
        //    16: aload           6
        //    18: swap           
        //    19: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    22: pop            
        //    23: aload_0        
        //    24: bipush          34
        //    26: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    29: aload_1        
        //    30: aload_2        
        //    31: aload_0        
        //    32: bipush          35
        //    34: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    37: aload_1        
        //    38: aload_2        
        //    39: aload_2        
        //    40: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: aload           locals
        //    45: aload_1        
        //    46: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: dup2           
        //    53: astore          10
        //    55: astore          11
        //    57: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: dup            
        //    61: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    66: ifne            104
        //    69: pop            
        //    70: aload_1        
        //    71: aload_2        
        //    72: aload           11
        //    74: aload           10
        //    76: aload_0        
        //    77: bipush          36
        //    79: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    82: aload_1        
        //    83: aload_2        
        //    84: aload_0        
        //    85: aload_1        
        //    86: ldc_w           "Array"
        //    89: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getConstant4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    92: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    95: aload_0        
        //    96: bipush          37
        //    98: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   101: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.opElementAsgnWithOrPartTwoOneArg:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   104: pop            
        //   105: aload_0        
        //   106: bipush          38
        //   108: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   111: aload_1        
        //   112: aload_2        
        //   113: aload_0        
        //   114: bipush          39
        //   116: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   119: aload_1        
        //   120: aload_2        
        //   121: aload_0        
        //   122: bipush          40
        //   124: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   127: aload_1        
        //   128: aload_2        
        //   129: aload_2        
        //   130: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   133: aload           locals
        //   135: aload_1        
        //   136: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   139: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   142: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   145: aload           locals
        //   147: aload_1        
        //   148: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   151: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   154: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   157: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  23     135     6     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    @JRubyMethod(name = "add_browser", frame = true, required = 2, optional = 1, rest = -1)
    public static IRubyObject method__10$RUBY$add_browser(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
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
        //    11: iconst_2       
        //    12: iconst_3       
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
        //    28: aaload         
        //    29: aload           5
        //    31: swap           
        //    32: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    35: pop            
        //    36: aload_3        
        //    37: iconst_2       
        //    38: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.elementOrNull:([Lorg/jruby/runtime/builtin/IRubyObject;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: dup            
        //    42: ifnull          55
        //    45: aload           5
        //    47: swap           
        //    48: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: pop            
        //    52: goto            73
        //    55: aload           5
        //    57: aload_1        
        //    58: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    61: invokevirtual   org/jruby/Ruby.getFalse:()Lorg/jruby/RubyBoolean;
        //    64: aload_1        
        //    65: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //    68: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    71: pop            
        //    72: pop            
        //    73: aload           locals
        //    75: aload_1        
        //    76: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    87: ifeq            118
        //    90: aload_0        
        //    91: bipush          41
        //    93: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    96: aload_1        
        //    97: aload_2        
        //    98: aload_2        
        //    99: aload_1        
        //   100: aload_2        
        //   101: aload_0        
        //   102: aload_1        
        //   103: ldc_w           "block_0$RUBY$add_browser,-1,,false,0,./lib//macOS/zeroconf.rb,53,true"
        //   106: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   109: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   112: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   115: goto            148
        //   118: aload_0        
        //   119: bipush          43
        //   121: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   124: aload_1        
        //   125: aload_2        
        //   126: aload_2        
        //   127: aload           locals
        //   129: aload_1        
        //   130: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   133: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   136: aload           locals
        //   138: aload_1        
        //   139: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   142: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   145: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   148: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  73     76      5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_0$RUBY$add_browser(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        return file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(42).call(threadContext, rubyObject, rubyObject, currentScope.getNextCapturedScope().getValueZeroDepthZeroOrNil(threadContext.nil), currentScope.getNextCapturedScope().getValueOneDepthZeroOrNil(threadContext.nil));
    }
    
    @JRubyMethod(name = "resolvers", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__11$RUBY$resolvers(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@resolvers") ? file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getByteList5() : null) == null) {
            rubyObject = file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.setVariable4(threadContext.runtime, "@resolvers", object, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(44).call(threadContext, object, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getConstant5(threadContext, "Hash")));
        }
        else if (!(rubyObject = file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getVariable2(threadContext.runtime, "@resolvers", object)).isTrue()) {
            rubyObject = file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.setVariable5(threadContext.runtime, "@resolvers", object, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(45).call(threadContext, object, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getConstant6(threadContext, "Hash")));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "add_resolver", frame = true, required = 2, optional = 0, rest = -1)
    public static IRubyObject method__12$RUBY$add_resolver(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final Block p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_3        
        //     1: astore          10
        //     3: aload           4
        //     5: astore          reg_type
        //     7: aload_0        
        //     8: bipush          46
        //    10: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    13: aload_1        
        //    14: aload_2        
        //    15: aload_2        
        //    16: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    19: dup            
        //    20: aload_2        
        //    21: aload_0        
        //    22: bipush          47
        //    24: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    27: aload_0        
        //    28: bipush          48
        //    30: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    33: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //    36: aload           resolver
        //    38: aload           reg_type
        //    40: aload_1        
        //    41: aload_2        
        //    42: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name      Signature
        //  -----  ------  ----  --------  ---------------------------------------
        //  7      39      10    resolver  Lorg/jruby/runtime/builtin/IRubyObject;
        //  7      39      11    reg_type  Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "remove_resolver", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__13$RUBY$remove_resolver(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(49).call(threadContext, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(50).call(threadContext, rubyObject, rubyObject), rubyObject2);
    }
    
    @JRubyMethod(name = "get_resolver_regtype", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__14$RUBY$get_resolver_regtype(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    15: bipush          51
        //    17: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    20: aload_1        
        //    21: aload_2        
        //    22: aload_0        
        //    23: bipush          52
        //    25: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    28: aload_1        
        //    29: aload_2        
        //    30: aload_2        
        //    31: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    34: aload           locals
        //    36: aload_1        
        //    37: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    40: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    46: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     33      5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    @JRubyMethod(name = "queriers", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__15$RUBY$queriers(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@queriers") ? file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getByteList5() : null) == null) {
            rubyObject = file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.setVariable6(threadContext.runtime, "@queriers", object, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(53).call(threadContext, object, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getConstant7(threadContext, "Array")));
        }
        else if (!(rubyObject = file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getVariable3(threadContext.runtime, "@queriers", object)).isTrue()) {
            rubyObject = file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.setVariable7(threadContext.runtime, "@queriers", object, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(54).call(threadContext, object, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getConstant8(threadContext, "Array")));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "add_querier", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__16$RUBY$add_querier(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(55).call(threadContext, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(56).call(threadContext, rubyObject, rubyObject), rubyObject2);
    }
    
    @JRubyMethod(name = "remove_querier", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__17$RUBY$remove_querier(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(57).call(threadContext, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(58).call(threadContext, rubyObject, rubyObject), rubyObject2);
    }
    
    @JRubyMethod(name = "start_browsing_on", frame = true, required = 2, optional = 0, rest = -1)
    public static IRubyObject method__18$RUBY$start_browsing_on(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final Block p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          6
        //     6: aload_3        
        //     7: aload           6
        //     9: swap           
        //    10: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    13: pop            
        //    14: aload           4
        //    16: aload           6
        //    18: swap           
        //    19: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    22: pop            
        //    23: aload           locals
        //    25: aload           locals
        //    27: aload_1        
        //    28: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    31: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    34: aload_1        
        //    35: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //    38: astore          10
        //    40: aload_0        
        //    41: bipush          59
        //    43: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    46: aload_1        
        //    47: aload_2        
        //    48: aload           10
        //    50: aload_0        
        //    51: aload_1        
        //    52: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    55: aload_0        
        //    56: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getByteList6:()Lorg/jruby/util/ByteList;
        //    59: ldc_w           512
        //    62: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getRegexp0:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //    65: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //    68: ifeq            104
        //    71: aload_0        
        //    72: bipush          60
        //    74: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    77: aload_1        
        //    78: aload_2        
        //    79: aload           locals
        //    81: aload_1        
        //    82: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    85: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: aload_0        
        //    89: aload_1        
        //    90: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    93: bipush          32
        //    95: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    98: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   101: goto            172
        //   104: aload_0        
        //   105: bipush          61
        //   107: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   110: aload_1        
        //   111: aload_2        
        //   112: aload           10
        //   114: aload_0        
        //   115: aload_1        
        //   116: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   119: aload_0        
        //   120: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getByteList8:()Lorg/jruby/util/ByteList;
        //   123: ldc_w           512
        //   126: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getRegexp1:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   129: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   132: ifeq            168
        //   135: aload_0        
        //   136: bipush          62
        //   138: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   141: aload_1        
        //   142: aload_2        
        //   143: aload           locals
        //   145: aload_1        
        //   146: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   149: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   152: aload_0        
        //   153: aload_1        
        //   154: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   157: bipush          32
        //   159: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getString9:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   162: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   165: goto            172
        //   168: aload_1        
        //   169: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   172: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   175: pop            
        //   176: aload           locals
        //   178: aload_1        
        //   179: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   182: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   185: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   190: ifeq            221
        //   193: aload_0        
        //   194: bipush          63
        //   196: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   199: aload_1        
        //   200: aload_2        
        //   201: aload_2        
        //   202: aload_1        
        //   203: aload_2        
        //   204: aload_0        
        //   205: aload_1        
        //   206: ldc_w           "block_1$RUBY$start_browsing_on,-1,,false,0,./lib//macOS/zeroconf.rb,99,true"
        //   209: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getBlockBody1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   212: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   215: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   218: goto            225
        //   221: aload_1        
        //   222: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   225: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  23     203     6     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_1$RUBY$start_browsing_on(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final DynamicScope currentScope = context.getCurrentScope();
        RuntimeHelpers.processBlockArgument(context.runtime, block);
        return file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(64).call(context, rubyObject, rubyObject, currentScope.getNextCapturedScope().getValueTwoDepthZeroOrNil(context.nil), file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(65).call(context, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getConstant9(context, "DNSSD"), currentScope.getNextCapturedScope().getValueTwoDepthZeroOrNil(context.nil), rubyObject));
    }
    
    @JRubyMethod(name = "initialize", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__19$RUBY$initialize(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext context, final IRubyObject object, final Block block) {
        return file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.setVariable8(context.runtime, "@mutex", object, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(70).call(context, object, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getConstant(context, "Mutex", 13)));
    }
    
    @JRubyMethod(name = "services", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__20$RUBY$services(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@services") ? file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getByteList5() : null) == null) {
            rubyObject = file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.setVariable9(threadContext.runtime, "@services", object, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(71).call(threadContext, object, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getConstant(threadContext, "Array", 14)));
        }
        else if (!(rubyObject = file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getVariable4(threadContext.runtime, "@services", object)).isTrue()) {
            rubyObject = file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.setVariable(threadContext.runtime, 10, "@services", object, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(72).call(threadContext, object, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getConstant(threadContext, "Array", 15)));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "synchronize", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__21$RUBY$synchronize(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(73).callIter(threadContext, self, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(74).call(threadContext, self, self), RuntimeHelpers.createBlock(threadContext, self, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getBlockBody2(threadContext, "block_2$RUBY$synchronize,-1,,false,0,./lib//macOS/zeroconf.rb,121,true")));
    }
    
    public static IRubyObject block_2$RUBY$synchronize(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        context.getCurrentScope();
        RuntimeHelpers.processBlockArgument(context.runtime, block);
        return context.getFrameBlock().yieldSpecific(context);
    }
    
    @JRubyMethod(name = "run", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__22$RUBY$run(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject valueZeroDepthZero, final Block block) {
        threadContext.getCurrentScope().setValueZeroDepthZero(valueZeroDepthZero);
        chained_23_rescue_1$RUBY$SYNTHETICrun(file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, threadContext, rubyObject, valueZeroDepthZero, block);
        chained_25_rescue_2$RUBY$SYNTHETICrun(file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, threadContext, rubyObject, valueZeroDepthZero, block);
        final RubyBoolean true = threadContext.runtime.getTrue();
        threadContext.pollThreadEvents();
        return true;
    }
    
    public static IRubyObject chained_23_rescue_1$RUBY$SYNTHETICrun(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        threadContext.getCurrentScope();
        final IRubyObject errorInfo = threadContext.getErrorInfo();
        IRubyObject rubyObject3 = null;
        Label_0145: {
            try {
                try {
                    rubyObject3 = file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(75).call(threadContext, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(76).call(threadContext, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getConstant(threadContext, "ServiceDiscoverer", 16), rubyObject));
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable t) {
                    if (RuntimeHelpers.isJavaExceptionHandled(t, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(77).call(threadContext, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(78).call(threadContext, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(79).call(threadContext, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(80).call(threadContext, rubyObject, rubyObject)))), threadContext).isTrue()) {
                        RuntimeHelpers.storeExceptionInErrorInfo(t, threadContext);
                        rubyObject3 = chained_24_rescue_line_129(file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, threadContext, rubyObject, rubyObject2, block);
                        RuntimeHelpers.clearErrorInfo(threadContext);
                        break Label_0145;
                    }
                    throw t;
                }
            }
            catch (JumpException.FlowControlException ex2) {
                threadContext.setErrorInfo(errorInfo);
                throw ex2;
            }
        }
        threadContext.setErrorInfo(errorInfo);
        return rubyObject3;
    }
    
    public static IRubyObject chained_24_rescue_line_129(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        context.getCurrentScope();
        final RubyBoolean false = context.runtime.getFalse();
        context.pollThreadEvents();
        throw RuntimeHelpers.returnJump(false, context);
    }
    
    public static IRubyObject chained_25_rescue_2$RUBY$SYNTHETICrun(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject self, final IRubyObject rubyObject, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        final IRubyObject errorInfo = threadContext.getErrorInfo();
        IRubyObject rubyObject2 = null;
        Label_0135: {
            try {
                try {
                    rubyObject2 = file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(81).callIter(threadContext, self, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getConstant(threadContext, "Timeout", 17), currentScope.getValueZeroDepthZeroOrNil(threadContext.nil), RuntimeHelpers.createBlock(threadContext, self, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getBlockBody4(threadContext, "block_3$RUBY$run,-1,,false,0,./lib//macOS/zeroconf.rb,134,false")));
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable t) {
                    if (RuntimeHelpers.isJavaExceptionHandled(t, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getConstantFrom(RuntimeHelpers.checkIsModule(file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getConstant(threadContext, "Timeout", 18)), threadContext, "Error", 19), threadContext).isTrue()) {
                        RuntimeHelpers.storeExceptionInErrorInfo(t, threadContext);
                        rubyObject2 = chained_26_rescue_line_139(file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, threadContext, self, rubyObject, block);
                        RuntimeHelpers.clearErrorInfo(threadContext);
                        break Label_0135;
                    }
                    throw t;
                }
            }
            catch (JumpException.FlowControlException ex2) {
                threadContext.setErrorInfo(errorInfo);
                throw ex2;
            }
        }
        threadContext.setErrorInfo(errorInfo);
        return rubyObject2;
    }
    
    public static IRubyObject block_3$RUBY$run(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject self, final IRubyObject rubyObject, final Block block) {
        threadContext.getCurrentScope();
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        return file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(82).callIter(threadContext, self, self, RuntimeHelpers.createBlock(threadContext, self, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getBlockBody3(threadContext, "block_4$RUBY$run,-1,,false,0,./lib//macOS/zeroconf.rb,135,true")));
    }
    
    public static IRubyObject block_4$RUBY$run(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        threadContext.getCurrentScope();
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        return file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(83).call(threadContext, rubyObject, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getFixnum0(threadContext.runtime, 10));
    }
    
    public static IRubyObject chained_26_rescue_line_139(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject self, final IRubyObject rubyObject, final Block block) {
        threadContext.getCurrentScope();
        return file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(84).callIter(threadContext, self, self, RuntimeHelpers.createBlock(threadContext, self, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getBlockBody9(threadContext, "block_5$RUBY$run,-1,,false,0,./lib//macOS/zeroconf.rb,140,false")));
    }
    
    public static IRubyObject block_5$RUBY$run(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject self, final IRubyObject rubyObject, final Block block) {
        threadContext.getCurrentScope();
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(85).callIter(threadContext, self, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(86).call(threadContext, self, self), RuntimeHelpers.createBlock(threadContext, self, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getBlockBody6(threadContext, "block_6$RUBY$run,2,reg_type;ary,true,1,./lib//macOS/zeroconf.rb,141,false")));
        file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(89).callIter(threadContext, self, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(90).call(threadContext, self, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(91).call(threadContext, self, self)), RuntimeHelpers.createBlock(threadContext, self, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getBlockBody7(threadContext, "block_8$RUBY$run,1,resolver,false,2,./lib//macOS/zeroconf.rb,146,true")));
        return file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(93).callIter(threadContext, self, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(94).call(threadContext, self, self), RuntimeHelpers.createBlock(threadContext, self, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getBlockBody8(threadContext, "block_9$RUBY$run,1,querier,false,2,./lib//macOS/zeroconf.rb,149,true")));
    }
    
    public static IRubyObject block_6$RUBY$run(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    16: aload           5
        //    18: swap           
        //    19: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    22: pop            
        //    23: aload_1        
        //    24: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    27: aload           4
        //    29: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    32: aload_3        
        //    33: aload_1        
        //    34: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    37: iconst_1       
        //    38: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.ensureMultipleAssignableRubyArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;Z)Lorg/jruby/RubyArray;
        //    41: astore          9
        //    43: aload           9
        //    45: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilZero:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    48: aload           5
        //    50: swap           
        //    51: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: pop            
        //    55: aload           9
        //    57: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilOne:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: aload           5
        //    62: swap           
        //    63: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: pop            
        //    67: aload           9
        //    69: pop            
        //    70: pop            
        //    71: aload_0        
        //    72: bipush          87
        //    74: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    77: aload_1        
        //    78: aload_2        
        //    79: aload           locals
        //    81: aload_1        
        //    82: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    85: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: aload_1        
        //    89: aload_2        
        //    90: aload_0        
        //    91: aload_1        
        //    92: ldc_w           "block_7$RUBY$run,1,browser,false,2,./lib//macOS/zeroconf.rb,142,true"
        //    95: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getBlockBody5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    98: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   101: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   104: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  71     34      5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_7$RUBY$run(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          88
        //    28: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           browser
        //    35: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    38: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name     Signature
        //  -----  ------  ----  -------  ---------------------------------------
        //  25     14      9     browser  Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject block_8$RUBY$run(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          92
        //    28: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           resolver
        //    35: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    38: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name      Signature
        //  -----  ------  ----  --------  ---------------------------------------
        //  25     14      9     resolver  Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject block_9$RUBY$run(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          95
        //    28: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           querier
        //    35: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    38: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name     Signature
        //  -----  ------  ----  -------  ---------------------------------------
        //  25     14      9     querier  Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "serviceFound", frame = true, required = 6, optional = 0, rest = -1)
    public static IRubyObject method__27$RUBY$serviceFound(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject self, final IRubyObject[] args, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        final IRubyObject[] values = currentScope.getValues();
        RuntimeHelpers.fillNil(values, threadContext.runtime);
        Arity.raiseArgumentError(threadContext.runtime, args, 6, 6);
        currentScope.setValueZeroDepthZero(args[0]);
        currentScope.setValueOneDepthZero(args[1]);
        currentScope.setValueTwoDepthZero(args[2]);
        currentScope.setValueThreeDepthZero(args[3]);
        values[4] = args[4];
        values[5] = args[5];
        return file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(96).callIter(threadContext, self, self, RuntimeHelpers.createBlock(threadContext, self, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getBlockBody(threadContext, 10, "block_10$RUBY$serviceFound,-1,resolver,false,0,./lib//macOS/zeroconf.rb,159,true")));
    }
    
    public static IRubyObject block_10$RUBY$serviceFound(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final DynamicScope currentScope = context.getCurrentScope();
        final IRubyObject nil = context.nil;
        RuntimeHelpers.processBlockArgument(context.runtime, block);
        final IRubyObject resolver = file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(97).call(context, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getConstant(context, "DNSSD", 20), currentScope.getNextCapturedScope().getValueOneDepthZeroOrNil(context.nil), currentScope.getNextCapturedScope().getValueTwoDepthZeroOrNil(context.nil), currentScope.getNextCapturedScope().getValueThreeDepthZeroOrNil(context.nil), currentScope.getValueOrNil(4, 1, context.nil), currentScope.getValueOrNil(5, 1, context.nil), rubyObject);
        return file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(98).call(context, rubyObject, rubyObject, resolver, currentScope.getValueOrNil(4, 1, context.nil));
    }
    
    @JRubyMethod(name = "serviceLost", frame = true, required = 0, optional = 0, rest = 0)
    public static IRubyObject method__28$RUBY$serviceLost(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] input, final Block block) {
        final IRubyObject nil = threadContext.nil;
        final IRubyObject args = RuntimeHelpers.createSubarray(input, threadContext.runtime, 0);
        return threadContext.nil;
    }
    
    public static IRubyObject module__29$RUBY$Records(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc_w           "Records"
        //    11: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    14: dup            
        //    15: astore_2       
        //    16: aload_1        
        //    17: swap           
        //    18: aload_0        
        //    19: aload_1        
        //    20: ldc             ",0,0,-1"
        //    22: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getScope3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    25: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    28: aload_1        
        //    29: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    32: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    35: aload_1        
        //    36: ldc_w           "A"
        //    39: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    42: pop            
        //    43: aload_1        
        //    44: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    47: invokestatic    org/jruby/RubyFixnum.two:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    50: aload_1        
        //    51: ldc_w           "NS"
        //    54: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: pop            
        //    58: aload_0        
        //    59: aload_1        
        //    60: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    63: bipush          12
        //    65: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getFixnum1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //    68: aload_1        
        //    69: ldc_w           "PTR"
        //    72: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: aload_1        
        //    76: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: goto            87
        //    82: aload_1        
        //    83: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    86: athrow         
        //    87: aload_1        
        //    88: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    91: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  28     79     82     87     Any
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
    
    public static IRubyObject module__29$RUBY$Records(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__29$RUBY$Records(file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, threadContext, rubyObject, block);
    }
    
    @JRubyMethod(name = "service_resolved", frame = true, required = 7, optional = 0, rest = -1)
    public static IRubyObject method__30$RUBY$service_resolved(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject self, final IRubyObject[] args, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        final IRubyObject[] values = currentScope.getValues();
        RuntimeHelpers.fillNil(values, threadContext.runtime);
        Arity.raiseArgumentError(threadContext.runtime, args, 7, 7);
        currentScope.setValueZeroDepthZero(args[0]);
        currentScope.setValueOneDepthZero(args[1]);
        currentScope.setValueTwoDepthZero(args[2]);
        currentScope.setValueThreeDepthZero(args[3]);
        values[4] = args[4];
        values[5] = args[5];
        values[6] = args[6];
        return file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(100).callIter(threadContext, self, self, RuntimeHelpers.createBlock(threadContext, self, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getBlockBody(threadContext, 11, "block_11$RUBY$service_resolved,-1,service;querier,false,0,./lib//macOS/zeroconf.rb,182,true")));
    }
    
    public static IRubyObject block_11$RUBY$service_resolved(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        final IRubyObject nil = threadContext.nil;
        final IRubyObject nil2 = threadContext.nil;
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        final IRubyObject service = file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(101).call(threadContext, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getConstant(threadContext, "Service", 22), RuntimeHelpers.constructObjectArray(currentScope.getNextCapturedScope().getValueThreeDepthZeroOrNil(threadContext.nil), currentScope.getValueOrNil(4, 1, threadContext.nil), currentScope.getValueOrNil(5, 1, threadContext.nil), currentScope.getValueOrNil(6, 1, threadContext.nil), file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(102).call(threadContext, rubyObject, rubyObject, currentScope.getNextCapturedScope().getValueZeroDepthZeroOrNil(threadContext.nil))));
        file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(103).call(threadContext, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(104).call(threadContext, rubyObject, rubyObject), service);
        file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(105).call(threadContext, rubyObject, currentScope.getNextCapturedScope().getValueZeroDepthZeroOrNil(threadContext.nil));
        file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(106).call(threadContext, rubyObject, rubyObject, currentScope.getNextCapturedScope().getValueZeroDepthZeroOrNil(threadContext.nil));
        final IRubyObject querier = file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(107).call(threadContext, rubyObject, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getConstant(threadContext, "DNSSD", 23), currentScope.getNextCapturedScope().getValueOneDepthZeroOrNil(threadContext.nil), currentScope.getNextCapturedScope().getValueTwoDepthZeroOrNil(threadContext.nil), currentScope.getValueOrNil(4, 1, threadContext.nil), file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getConstantFrom(RuntimeHelpers.checkIsModule(file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getConstant(threadContext, "Records", 24)), threadContext, "A", 25), RubyFixnum.one(threadContext.runtime), rubyObject);
        return file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(108).call(threadContext, rubyObject, rubyObject, querier);
    }
    
    @JRubyMethod(name = "queryAnswered", frame = true, required = 8, optional = 0, rest = -1)
    public static IRubyObject method__31$RUBY$queryAnswered(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject self, final IRubyObject[] args, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        final IRubyObject[] values = currentScope.getValues();
        RuntimeHelpers.fillNil(values, threadContext.runtime);
        Arity.raiseArgumentError(threadContext.runtime, args, 8, 8);
        currentScope.setValueZeroDepthZero(args[0]);
        currentScope.setValueOneDepthZero(args[1]);
        currentScope.setValueTwoDepthZero(args[2]);
        currentScope.setValueThreeDepthZero(args[3]);
        values[4] = args[4];
        values[5] = args[5];
        values[6] = args[6];
        values[7] = args[7];
        file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(109).callIter(threadContext, self, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(110).callIter(threadContext, self, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(111).call(threadContext, self, self), RuntimeHelpers.createBlock(threadContext, self, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getBlockBody(threadContext, 12, "block_12$RUBY$queryAnswered,1,s,false,2,./lib//macOS/zeroconf.rb,197,true"))), RuntimeHelpers.createBlock(threadContext, self, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getBlockBody(threadContext, 13, "block_13$RUBY$queryAnswered,1,service;ip,false,2,./lib//macOS/zeroconf.rb,197,true")));
        return file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(120).callIter(threadContext, self, self, RuntimeHelpers.createBlock(threadContext, self, file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getBlockBody(threadContext, 14, "block_14$RUBY$queryAnswered,-1,,false,0,./lib//macOS/zeroconf.rb,201,true")));
    }
    
    public static IRubyObject block_12$RUBY$queryAnswered(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          112
        //    28: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_0        
        //    34: bipush          113
        //    36: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    39: aload_1        
        //    40: aload_2        
        //    41: aload           s
        //    43: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    46: aload           5
        //    48: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    51: aload_1        
        //    52: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    61: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     37      9     s     Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject block_13$RUBY$queryAnswered(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    13: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    16: astore          10
        //    18: aload_1        
        //    19: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    22: aload           4
        //    24: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    27: aload_3        
        //    28: astore          9
        //    30: pop            
        //    31: aload_0        
        //    32: bipush          114
        //    34: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    37: aload_1        
        //    38: aload_2        
        //    39: aload_0        
        //    40: bipush          115
        //    42: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    45: aload_1        
        //    46: aload_2        
        //    47: aload_0        
        //    48: bipush          116
        //    50: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    53: aload_1        
        //    54: aload_2        
        //    55: aload_0        
        //    56: bipush          117
        //    58: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    61: aload_1        
        //    62: aload_2        
        //    63: aload           5
        //    65: bipush          6
        //    67: iconst_1       
        //    68: aload_1        
        //    69: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    72: invokevirtual   org/jruby/runtime/DynamicScope.getValueOrNil:(IILorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    78: aload_0        
        //    79: aload_1        
        //    80: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    83: bipush          10
        //    85: bipush          32
        //    87: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    90: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    93: aload_0        
        //    94: aload_1        
        //    95: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    98: bipush          10
        //   100: bipush          32
        //   102: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   105: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   108: aload_0        
        //   109: aload_1        
        //   110: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   113: bipush          11
        //   115: bipush          32
        //   117: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   120: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   123: astore          ip
        //   125: aload           service
        //   127: dup            
        //   128: aload_2        
        //   129: aload_0        
        //   130: bipush          118
        //   132: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   135: aload_0        
        //   136: bipush          119
        //   138: invokevirtual   ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   141: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   144: aload           ip
        //   146: aload_1        
        //   147: aload_2        
        //   148: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   151: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name     Signature
        //  -----  ------  ----  -------  ---------------------------------------
        //  31     121     9     service  Lorg/jruby/runtime/builtin/IRubyObject;
        //  31     121     10    ip       Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject block_14$RUBY$queryAnswered(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(121).call(threadContext, rubyObject, currentScope.getNextCapturedScope().getValueZeroDepthZeroOrNil(threadContext.nil));
        return file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.getCallSite(122).call(threadContext, rubyObject, rubyObject, currentScope.getNextCapturedScope().getValueZeroDepthZeroOrNil(threadContext.nil));
    }
    
    public static IRubyObject class_7$RUBY$ZeroconfCounter(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_7$RUBY$ZeroconfCounter(file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Darwin(final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Darwin(file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5, threadContext, rubyObject, block);
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
        final FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5 = new FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5();
        final String string = FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.class.getClassLoader().getResource("ruby/jit/FILE_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.class").toString();
        file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_9A17B0AC938654C76E77A2B43C7D3249F1B684A5.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
