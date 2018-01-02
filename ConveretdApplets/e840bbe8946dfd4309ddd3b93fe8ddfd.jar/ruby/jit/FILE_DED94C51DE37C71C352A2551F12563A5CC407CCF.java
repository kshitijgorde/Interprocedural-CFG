// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.RubyHash;
import org.jruby.runtime.DynamicScope;
import org.jruby.RubyFixnum;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF extends AbstractScript
{
    public FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF() {
        this.filename = "./lib//win32/interface.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffattr_accessor\uffffF\uffffnew\uffffN\uffffwrite_ulong\uffffN\uffffGetIfTable\uffffN\uffffnew\uffffN\uffffread_ulong\uffffN\uffffwrite_ulong\uffffN\uffffsize\uffffN\uffffGetIfTable\uffffN\uffffnew\uffffN\uffffto_ptr\uffffN\uffff[]\uffffN\uffffmap\uffffN\ufffftimes\uffffN\uffff[]\uffffN\uffff+\uffffN\uffffaddress\uffffN\uffff*\uffffN\uffffsize\uffffN\uffffnew\uffffN\uffffnew\uffffN\uffffmap\uffffN\uffffall_rows\uffffV\uffffnew\uffffN\uffff[]\uffffN\ufffffind\uffffN\uffffall_rows\uffffN\uffffclass\uffffN\uffff==\uffffN\uffff[]\uffffN\uffffindex\uffffV\uffffget_row\uffffV\uffffupdate_row!\uffffF\uffffupdate_row!\uffffF\uffff[]\uffffN\uffffrow\uffffV\uffff[]\uffffN\uffffrow\uffffV\uffff[]\uffffN\uffffrow\uffffV\uffff[]\uffffN\uffffrow\uffffV\uffff[]\uffffN\uffffrow\uffffV\uffff+\uffffN\uffffsent_unicast_packets\uffffV\uffffsent_non_unicast_packets\uffffV\uffff[]\uffffN\uffffrow\uffffV\uffff[]\uffffN\uffffrow\uffffV\uffff+\uffffN\uffffreceived_unicast_packets\uffffV\uffffreceived_non_unicast_packets\uffffV\uffffrow\uffffV\uffffsent_bytes\uffffV\uffffreceived_bytes\uffffV\uffffsent_packets\uffffV\uffffreceived_packets\uffffV\uffff[]\uffffN\uffffrow\uffffV\uffffmac_addr\uffffN\uffffrow\uffffV\uffff==\uffffN\uffff[]\uffffN\uffffrow\uffffV\uffff[]\uffffN\uffffrow\uffffV\uffff[]\uffffN\uffffname\uffffN\uffffrow\uffffV\uffffdescription\uffffN\uffffrow\uffffV\uffff[]\uffffN\ufffftype\uffffV\uffffto_s\uffffN\ufffftype\uffffV\uffffnew\uffffN\uffffGetBestRoute\uffffN\uffff[]\uffffN\uffffnew\uffffN\ufffffor_int_addresses\uffffF\uffff\u0002\u0014\b\u0000\u001a\u0000\u0000\u0001\u0004\u0003\u0000\u0000\u0013\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(5, "disconnected", this.getEncoding0());
        this.setByteList(6, "connecting", this.getEncoding0());
        this.setByteList(10, "other", this.getEncoding0());
        this.setByteList(7, "connected", this.getEncoding0());
        this.setByteList(13, "ppp", this.getEncoding0());
        this.setByteList(3, "non-operational", this.getEncoding0());
        this.setByteList(8, "operational", this.getEncoding0());
        this.setByteList(16, "ieee802.11", this.getEncoding0());
        this.setByteList(15, "atm", this.getEncoding0());
        this.setByteList(12, "token-ring", this.getEncoding0());
        this.setByteList(11, "ethernet", this.getEncoding0());
        this.setByteList(14, "loopback", this.getEncoding0());
        this.setByteList(1, "win32/mib/ipstats", this.getEncoding0());
        this.setByteList(4, "unreachable", this.getEncoding0());
        this.setByteList(0, "win32/iphelper", this.getEncoding0());
        this.setByteList(18, "ieee1394", this.getEncoding0());
        this.setByteList(9, "disabled", this.getEncoding0());
        this.setByteList(17, "tunnel", this.getEncoding0());
        this.setByteList(2, "instance-variable", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF file_DED94C51DE37C71C352A2551F12563A5CC407CCF, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite0().call(threadContext, rubyObject, rubyObject, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getString0(threadContext.runtime, 32));
        file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite1().call(threadContext, rubyObject, rubyObject, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getString1(threadContext.runtime, 32));
        return module__0$RUBY$Win32(file_DED94C51DE37C71C352A2551F12563A5CC407CCF, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Win32(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    33: invokestatic    ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.class_1$RUBY$Interface:(Lruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    36: aload_1        
        //    37: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    40: goto            48
        //    43: aload_1        
        //    44: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    47: athrow         
        //    48: aload_1        
        //    49: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    52: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     40     43     48     Any
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
    
    public static IRubyObject class_1$RUBY$Interface(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    10: ldc             "Interface"
        //    12: swap           
        //    13: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    16: dup            
        //    17: astore_2       
        //    18: aload_1        
        //    19: swap           
        //    20: aload_0        
        //    21: aload_1        
        //    22: ldc             ",0,0,-1"
        //    24: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    27: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    30: aload_0        
        //    31: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite2:()Lorg/jruby/runtime/CallSite;
        //    34: aload_1        
        //    35: aload_2        
        //    36: aload_2        
        //    37: aload_0        
        //    38: aload_1        
        //    39: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    42: ldc             "index"
        //    44: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    47: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: pop            
        //    51: aload_1        
        //    52: aload_2        
        //    53: aload_0        
        //    54: ldc             "initialize"
        //    56: ldc             "method__2$RUBY$initialize"
        //    58: ldc             "idx,0,1,-1"
        //    60: iconst_m1      
        //    61: ldc             "./lib//win32/interface.rb"
        //    63: ldc             7
        //    65: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    68: ldc             "oidx"
        //    70: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: pop            
        //    74: aload_1        
        //    75: aload_2        
        //    76: aload_2        
        //    77: aload_0        
        //    78: ldc_w           "all_rows"
        //    81: ldc_w           "method__3$RUBY$all_rows"
        //    84: ldc_w           "p_len;ptr;table;p_tables,0,0,-1"
        //    87: iconst_0       
        //    88: ldc             "./lib//win32/interface.rb"
        //    90: ldc_w           11
        //    93: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    96: ldc_w           "NONE"
        //    99: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   102: pop            
        //   103: aload_1        
        //   104: aload_2        
        //   105: aload_2        
        //   106: aload_0        
        //   107: ldc_w           "all"
        //   110: ldc_w           "method__4$RUBY$all"
        //   113: ldc             ",0,0,-1"
        //   115: iconst_0       
        //   116: ldc             "./lib//win32/interface.rb"
        //   118: ldc_w           29
        //   121: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   124: ldc_w           "NONE"
        //   127: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   130: pop            
        //   131: aload_1        
        //   132: aload_2        
        //   133: aload_0        
        //   134: ldc_w           "get_row"
        //   137: ldc_w           "method__5$RUBY$get_row"
        //   140: ldc             ",0,0,-1"
        //   142: iconst_0       
        //   143: ldc             "./lib//win32/interface.rb"
        //   145: ldc_w           35
        //   148: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   151: ldc_w           "NONE"
        //   154: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   157: pop            
        //   158: aload_1        
        //   159: aload_2        
        //   160: aload_0        
        //   161: ldc_w           "update_row!"
        //   164: ldc_w           "method__6$RUBY$update_row_b_"
        //   167: ldc             ",0,0,-1"
        //   169: iconst_0       
        //   170: ldc             "./lib//win32/interface.rb"
        //   172: ldc_w           41
        //   175: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   178: ldc_w           "NONE"
        //   181: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   184: pop            
        //   185: aload_1        
        //   186: aload_2        
        //   187: aload_0        
        //   188: ldc_w           "row"
        //   191: ldc_w           "method__7$RUBY$row"
        //   194: ldc             ",0,0,-1"
        //   196: iconst_0       
        //   197: ldc             "./lib//win32/interface.rb"
        //   199: ldc_w           45
        //   202: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   205: ldc_w           "NONE"
        //   208: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   211: pop            
        //   212: aload_1        
        //   213: aload_2        
        //   214: aload_0        
        //   215: ldc_w           "mtu"
        //   218: ldc_w           "method__8$RUBY$mtu"
        //   221: ldc             ",0,0,-1"
        //   223: iconst_0       
        //   224: ldc             "./lib//win32/interface.rb"
        //   226: ldc_w           49
        //   229: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   232: ldc_w           "NONE"
        //   235: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   238: pop            
        //   239: aload_1        
        //   240: aload_2        
        //   241: aload_0        
        //   242: ldc_w           "sent_bytes"
        //   245: ldc_w           "method__9$RUBY$sent_bytes"
        //   248: ldc             ",0,0,-1"
        //   250: iconst_0       
        //   251: ldc             "./lib//win32/interface.rb"
        //   253: ldc_w           53
        //   256: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   259: ldc_w           "NONE"
        //   262: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   265: pop            
        //   266: aload_1        
        //   267: aload_2        
        //   268: aload_0        
        //   269: ldc_w           "received_bytes"
        //   272: ldc_w           "method__10$RUBY$received_bytes"
        //   275: ldc             ",0,0,-1"
        //   277: iconst_0       
        //   278: ldc             "./lib//win32/interface.rb"
        //   280: ldc_w           57
        //   283: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   286: ldc_w           "NONE"
        //   289: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   292: pop            
        //   293: aload_1        
        //   294: aload_2        
        //   295: aload_0        
        //   296: ldc_w           "sent_unicast_packets"
        //   299: ldc_w           "method__11$RUBY$sent_unicast_packets"
        //   302: ldc             ",0,0,-1"
        //   304: iconst_0       
        //   305: ldc             "./lib//win32/interface.rb"
        //   307: ldc_w           61
        //   310: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   313: ldc_w           "NONE"
        //   316: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   319: pop            
        //   320: aload_1        
        //   321: aload_2        
        //   322: aload_0        
        //   323: ldc_w           "sent_non_unicast_packets"
        //   326: ldc_w           "method__12$RUBY$sent_non_unicast_packets"
        //   329: ldc             ",0,0,-1"
        //   331: iconst_0       
        //   332: ldc             "./lib//win32/interface.rb"
        //   334: ldc_w           65
        //   337: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   340: ldc_w           "NONE"
        //   343: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   346: pop            
        //   347: aload_1        
        //   348: aload_2        
        //   349: aload_0        
        //   350: ldc_w           "sent_packets"
        //   353: ldc_w           "method__13$RUBY$sent_packets"
        //   356: ldc             ",0,0,-1"
        //   358: iconst_0       
        //   359: ldc             "./lib//win32/interface.rb"
        //   361: ldc_w           69
        //   364: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   367: ldc_w           "NONE"
        //   370: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   373: pop            
        //   374: aload_1        
        //   375: aload_2        
        //   376: aload_0        
        //   377: ldc_w           "received_unicast_packets"
        //   380: ldc_w           "method__14$RUBY$received_unicast_packets"
        //   383: ldc             ",0,0,-1"
        //   385: iconst_0       
        //   386: ldc             "./lib//win32/interface.rb"
        //   388: ldc_w           73
        //   391: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   394: ldc_w           "NONE"
        //   397: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   400: pop            
        //   401: aload_1        
        //   402: aload_2        
        //   403: aload_0        
        //   404: ldc_w           "received_non_unicast_packets"
        //   407: ldc_w           "method__15$RUBY$received_non_unicast_packets"
        //   410: ldc             ",0,0,-1"
        //   412: iconst_0       
        //   413: ldc             "./lib//win32/interface.rb"
        //   415: ldc_w           77
        //   418: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   421: ldc_w           "NONE"
        //   424: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   427: pop            
        //   428: aload_1        
        //   429: aload_2        
        //   430: aload_0        
        //   431: ldc_w           "received_packets"
        //   434: ldc_w           "method__16$RUBY$received_packets"
        //   437: ldc             ",0,0,-1"
        //   439: iconst_0       
        //   440: ldc             "./lib//win32/interface.rb"
        //   442: ldc_w           81
        //   445: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   448: ldc_w           "NONE"
        //   451: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   454: pop            
        //   455: aload_1        
        //   456: aload_2        
        //   457: aload_0        
        //   458: ldc_w           "traffic"
        //   461: ldc_w           "method__17$RUBY$traffic"
        //   464: ldc_w           "ret,0,0,-1"
        //   467: iconst_0       
        //   468: ldc             "./lib//win32/interface.rb"
        //   470: ldc_w           85
        //   473: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   476: ldc_w           "NONE"
        //   479: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   482: pop            
        //   483: aload_1        
        //   484: aload_2        
        //   485: aload_0        
        //   486: ldc_w           "type"
        //   489: ldc_w           "method__18$RUBY$type"
        //   492: ldc             ",0,0,-1"
        //   494: iconst_0       
        //   495: ldc             "./lib//win32/interface.rb"
        //   497: ldc_w           97
        //   500: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   503: ldc_w           "NONE"
        //   506: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   509: pop            
        //   510: aload_1        
        //   511: aload_2        
        //   512: aload_0        
        //   513: ldc_w           "mac_addr"
        //   516: ldc_w           "method__19$RUBY$mac_addr"
        //   519: ldc             ",0,0,-1"
        //   521: iconst_0       
        //   522: ldc             "./lib//win32/interface.rb"
        //   524: ldc_w           101
        //   527: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   530: ldc_w           "NONE"
        //   533: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   536: pop            
        //   537: aload_1        
        //   538: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   541: aload_1        
        //   542: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   545: invokestatic    org/jruby/RubyFixnum.zero:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   548: aload_0        
        //   549: aload_1        
        //   550: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   553: bipush          32
        //   555: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getString3:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   558: aload_1        
        //   559: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   562: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   565: aload_0        
        //   566: aload_1        
        //   567: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   570: bipush          32
        //   572: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getString4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   575: aload_1        
        //   576: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   579: invokestatic    org/jruby/RubyFixnum.two:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   582: aload_0        
        //   583: aload_1        
        //   584: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   587: bipush          32
        //   589: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getString5:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   592: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructHash:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyHash;
        //   595: dup            
        //   596: aload_1        
        //   597: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   600: aload_1        
        //   601: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   604: invokestatic    org/jruby/RubyFixnum.three:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   607: aload_0        
        //   608: aload_1        
        //   609: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   612: bipush          32
        //   614: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getString6:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   617: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   620: dup            
        //   621: aload_1        
        //   622: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   625: aload_1        
        //   626: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   629: invokestatic    org/jruby/RubyFixnum.four:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   632: aload_0        
        //   633: aload_1        
        //   634: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   637: bipush          32
        //   639: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   642: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   645: dup            
        //   646: aload_1        
        //   647: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   650: aload_1        
        //   651: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   654: invokestatic    org/jruby/RubyFixnum.five:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   657: aload_0        
        //   658: aload_1        
        //   659: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   662: bipush          32
        //   664: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getString8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   667: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   670: aload_1        
        //   671: ldc_w           "STATUS_TYPES"
        //   674: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   677: pop            
        //   678: aload_1        
        //   679: aload_2        
        //   680: aload_0        
        //   681: ldc_w           "status"
        //   684: ldc_w           "method__20$RUBY$status"
        //   687: ldc_w           "int,0,0,-1"
        //   690: iconst_0       
        //   691: ldc             "./lib//win32/interface.rb"
        //   693: ldc_w           115
        //   696: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   699: ldc_w           "NONE"
        //   702: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   705: pop            
        //   706: aload_1        
        //   707: aload_2        
        //   708: aload_0        
        //   709: ldc_w           "name"
        //   712: ldc_w           "method__21$RUBY$name"
        //   715: ldc             ",0,0,-1"
        //   717: iconst_0       
        //   718: ldc             "./lib//win32/interface.rb"
        //   720: ldc_w           124
        //   723: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   726: ldc_w           "NONE"
        //   729: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   732: pop            
        //   733: aload_1        
        //   734: aload_2        
        //   735: aload_0        
        //   736: ldc_w           "description"
        //   739: ldc_w           "method__22$RUBY$description"
        //   742: ldc             ",0,0,-1"
        //   744: iconst_0       
        //   745: ldc             "./lib//win32/interface.rb"
        //   747: ldc_w           128
        //   750: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   753: ldc_w           "NONE"
        //   756: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   759: pop            
        //   760: aload_1        
        //   761: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   764: aload_1        
        //   765: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   768: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   771: aload_0        
        //   772: aload_1        
        //   773: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   776: bipush          10
        //   778: bipush          32
        //   780: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   783: aload_0        
        //   784: aload_1        
        //   785: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   788: bipush          6
        //   790: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getFixnum0:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   793: aload_0        
        //   794: aload_1        
        //   795: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   798: bipush          11
        //   800: bipush          32
        //   802: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   805: aload_0        
        //   806: aload_1        
        //   807: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   810: bipush          9
        //   812: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getFixnum1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   815: aload_0        
        //   816: aload_1        
        //   817: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   820: bipush          12
        //   822: bipush          32
        //   824: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   827: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructHash:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyHash;
        //   830: dup            
        //   831: aload_1        
        //   832: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   835: aload_0        
        //   836: aload_1        
        //   837: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   840: bipush          23
        //   842: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getFixnum2:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   845: aload_0        
        //   846: aload_1        
        //   847: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   850: bipush          13
        //   852: bipush          32
        //   854: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   857: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   860: dup            
        //   861: aload_1        
        //   862: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   865: aload_0        
        //   866: aload_1        
        //   867: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   870: bipush          24
        //   872: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getFixnum3:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   875: aload_0        
        //   876: aload_1        
        //   877: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   880: bipush          14
        //   882: bipush          32
        //   884: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   887: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   890: dup            
        //   891: aload_1        
        //   892: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   895: aload_0        
        //   896: aload_1        
        //   897: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   900: bipush          37
        //   902: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getFixnum4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   905: aload_0        
        //   906: aload_1        
        //   907: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   910: bipush          15
        //   912: bipush          32
        //   914: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   917: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   920: dup            
        //   921: aload_1        
        //   922: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   925: aload_0        
        //   926: aload_1        
        //   927: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   930: bipush          71
        //   932: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getFixnum5:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   935: aload_0        
        //   936: aload_1        
        //   937: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   940: bipush          16
        //   942: bipush          32
        //   944: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   947: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   950: dup            
        //   951: aload_1        
        //   952: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   955: aload_0        
        //   956: aload_1        
        //   957: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   960: sipush          131
        //   963: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getFixnum6:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   966: aload_0        
        //   967: aload_1        
        //   968: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   971: bipush          17
        //   973: bipush          32
        //   975: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   978: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   981: dup            
        //   982: aload_1        
        //   983: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   986: aload_0        
        //   987: aload_1        
        //   988: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   991: sipush          144
        //   994: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getFixnum7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   997: aload_0        
        //   998: aload_1        
        //   999: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1002: bipush          18
        //  1004: bipush          32
        //  1006: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //  1009: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //  1012: aload_1        
        //  1013: ldc_w           "IANA_IFACE_TYPES"
        //  1016: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1019: pop            
        //  1020: aload_1        
        //  1021: aload_2        
        //  1022: aload_0        
        //  1023: ldc_w           "type_str"
        //  1026: ldc_w           "method__23$RUBY$type_str"
        //  1029: ldc             ",0,0,-1"
        //  1031: iconst_0       
        //  1032: ldc             "./lib//win32/interface.rb"
        //  1034: ldc_w           145
        //  1037: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1040: ldc_w           "NONE"
        //  1043: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1046: pop            
        //  1047: aload_1        
        //  1048: aload_2        
        //  1049: aload_2        
        //  1050: aload_0        
        //  1051: ldc_w           "for_int_addresses"
        //  1054: ldc_w           "method__24$RUBY$for_int_addresses"
        //  1057: ldc_w           "dst;src;ptr;idx,1,1,-1"
        //  1060: bipush          -2
        //  1062: ldc             "./lib//win32/interface.rb"
        //  1064: ldc_w           149
        //  1067: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1070: ldc_w           "qdst;osrc"
        //  1073: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1076: pop            
        //  1077: aload_1        
        //  1078: aload_2        
        //  1079: aload_2        
        //  1080: aload_0        
        //  1081: ldc_w           "default"
        //  1084: ldc_w           "method__25$RUBY$default"
        //  1087: ldc             ",0,0,-1"
        //  1089: iconst_0       
        //  1090: ldc             "./lib//win32/interface.rb"
        //  1092: ldc_w           156
        //  1095: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //  1098: ldc_w           "NONE"
        //  1101: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1104: aload_1        
        //  1105: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //  1108: goto            1116
        //  1111: aload_1        
        //  1112: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //  1115: athrow         
        //  1116: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  30     1104   1111   1116   Any
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
    
    @JRubyMethod(name = "initialize", frame = true, required = 0, optional = 1, rest = -1)
    public static IRubyObject method__2$RUBY$initialize(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     4: astore          9
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
        //    22: ifnull          30
        //    25: astore          9
        //    27: goto            37
        //    30: aload_1        
        //    31: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    34: astore          9
        //    36: pop            
        //    37: aload_0        
        //    38: aload_1        
        //    39: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    42: ldc             "@index"
        //    44: aload_2        
        //    45: aload           idx
        //    47: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.setVariable0:(Lorg/jruby/Ruby;Ljava/lang/String;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  37     14      9     idx   Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
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
    
    @JRubyMethod(name = "all_rows", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$all_rows(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF file_DED94C51DE37C71C352A2551F12563A5CC407CCF, final ThreadContext context, final IRubyObject self, final Block block) {
        final DynamicScope locals = context.getCurrentScope();
        locals.setValueZeroDepthZero(file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite3().call(context, self, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getConstantFrom1(RuntimeHelpers.checkIsModule(file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getConstant0(context, "FFI")), context, "MemoryPointer"), file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getSymbol1(context.runtime, "ulong")));
        file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite4().call(context, self, locals.getValueZeroDepthZeroOrNil(context.nil), RubyFixnum.zero(context.runtime));
        file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite5().call(context, self, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getConstantFrom3(RuntimeHelpers.checkIsModule(file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getConstant2(context, "Win32")), context, "IPHelper"), context.nil, locals.getValueZeroDepthZeroOrNil(context.nil), RubyFixnum.zero(context.runtime));
        locals.setValueOneDepthZero(file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite6().call(context, self, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getConstantFrom5(RuntimeHelpers.checkIsModule(file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getConstant4(context, "FFI")), context, "MemoryPointer"), file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite7().call(context, self, locals.getValueZeroDepthZeroOrNil(context.nil))));
        file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite8().call(context, self, locals.getValueZeroDepthZeroOrNil(context.nil), file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite9().call(context, self, locals.getValueOneDepthZeroOrNil(context.nil)));
        file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(10).call(context, self, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getConstantFrom7(RuntimeHelpers.checkIsModule(file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getConstant6(context, "Win32")), context, "IPHelper"), locals.getValueOneDepthZeroOrNil(context.nil), locals.getValueZeroDepthZeroOrNil(context.nil), RubyFixnum.zero(context.runtime));
        locals.setValueTwoDepthZero(file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(11).call(context, self, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getConstantFrom(RuntimeHelpers.checkIsModule(file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getConstantFrom9(RuntimeHelpers.checkIsModule(file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getConstant8(context, "Win32")), context, "MIB")), context, "IfTable", 10), locals.getValueOneDepthZeroOrNil(context.nil)));
        locals.setValueThreeDepthZero(file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(12).call(context, self, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(13).call(context, self, locals.getValueTwoDepthZeroOrNil(context.nil), file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getSymbol2(context.runtime, "table"))));
        return file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(14).callIter(context, self, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(15).call(context, self, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(16).call(context, self, locals.getValueTwoDepthZeroOrNil(context.nil), file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getSymbol3(context.runtime, "dwNumEntries"))), RuntimeHelpers.createBlock(context, self, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getBlockBody0(context, "block_0$RUBY$all_rows,1,i;addr,false,2,./lib//win32/interface.rb,23,true")));
    }
    
    public static IRubyObject block_0$RUBY$all_rows(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    32: bipush          17
        //    34: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    37: aload_1        
        //    38: aload_2        
        //    39: aload_0        
        //    40: bipush          18
        //    42: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    45: aload_1        
        //    46: aload_2        
        //    47: aload           5
        //    49: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    52: aload_1        
        //    53: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    56: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    59: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    62: aload_0        
        //    63: bipush          19
        //    65: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    68: aload_1        
        //    69: aload_2        
        //    70: aload           i
        //    72: aload_0        
        //    73: bipush          20
        //    75: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    78: aload_1        
        //    79: aload_2        
        //    80: aload_0        
        //    81: aload_1        
        //    82: ldc             "Win32"
        //    84: bipush          11
        //    86: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    92: aload_0        
        //    93: swap           
        //    94: aload_1        
        //    95: ldc             "MIB"
        //    97: bipush          12
        //    99: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   102: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   105: aload_0        
        //   106: swap           
        //   107: aload_1        
        //   108: ldc_w           "IfRow"
        //   111: bipush          13
        //   113: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   116: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   119: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   122: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   125: astore          addr
        //   127: aload_0        
        //   128: bipush          21
        //   130: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   133: aload_1        
        //   134: aload_2        
        //   135: aload_0        
        //   136: aload_1        
        //   137: ldc             "Win32"
        //   139: bipush          14
        //   141: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   144: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   147: aload_0        
        //   148: swap           
        //   149: aload_1        
        //   150: ldc             "MIB"
        //   152: bipush          15
        //   154: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   157: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   160: aload_0        
        //   161: swap           
        //   162: aload_1        
        //   163: ldc_w           "IfRow"
        //   166: bipush          16
        //   168: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   171: aload_0        
        //   172: bipush          22
        //   174: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   177: aload_1        
        //   178: aload_2        
        //   179: aload_0        
        //   180: aload_1        
        //   181: ldc             "FFI"
        //   183: bipush          17
        //   185: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   188: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   191: aload_0        
        //   192: swap           
        //   193: aload_1        
        //   194: ldc_w           "Pointer"
        //   197: bipush          18
        //   199: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   202: aload           addr
        //   204: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   207: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   210: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  31     180     9     i     Lorg/jruby/runtime/builtin/IRubyObject;
        //  31     180     10    addr  Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "all", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$all(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF file_DED94C51DE37C71C352A2551F12563A5CC407CCF, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(23).callIter(threadContext, self, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(24).call(threadContext, self, self), RuntimeHelpers.createBlock(threadContext, self, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getBlockBody1(threadContext, "block_1$RUBY$all,1,r,false,2,./lib//win32/interface.rb,30,false")));
    }
    
    public static IRubyObject block_1$RUBY$all(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: bipush          25
        //    38: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_2        
        //    44: aload_0        
        //    45: bipush          26
        //    47: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    50: aload_1        
        //    51: aload_2        
        //    52: aload           locals
        //    54: aload_1        
        //    55: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    61: aload_0        
        //    62: aload_1        
        //    63: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    66: ldc_w           "dwIndex"
        //    69: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    72: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    78: areturn        
        //    79: pop            
        //    80: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     44      5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     79     79     83     Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    @JRubyMethod(name = "get_row", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$get_row(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF file_DED94C51DE37C71C352A2551F12563A5CC407CCF, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(27).callIter(threadContext, self, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(28).call(threadContext, self, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(29).call(threadContext, self, self)), RuntimeHelpers.createBlock(threadContext, self, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getBlockBody2(threadContext, "block_2$RUBY$get_row,1,r,false,2,./lib//win32/interface.rb,36,false")));
    }
    
    public static IRubyObject block_2$RUBY$get_row(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    38: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_0        
        //    44: bipush          31
        //    46: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload           locals
        //    53: aload_1        
        //    54: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: aload_0        
        //    61: aload_1        
        //    62: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    65: ldc_w           "dwIndex"
        //    68: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    71: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: aload_0        
        //    75: bipush          32
        //    77: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    80: aload_1        
        //    81: aload_2        
        //    82: aload_2        
        //    83: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    86: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: areturn        
        //    90: pop            
        //    91: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     55      5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     90     90     94     Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    @JRubyMethod(name = "update_row!", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$update_row_b_(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF file_DED94C51DE37C71C352A2551F12563A5CC407CCF, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        return file_DED94C51DE37C71C352A2551F12563A5CC407CCF.setVariable1(threadContext.runtime, "@row", object, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(33).call(threadContext, object, object));
    }
    
    @JRubyMethod(name = "row", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$row(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF file_DED94C51DE37C71C352A2551F12563A5CC407CCF, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@row") ? file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getByteList2() : null) == null) {
            rubyObject = file_DED94C51DE37C71C352A2551F12563A5CC407CCF.setVariable2(threadContext.runtime, "@row", object, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(34).call(threadContext, object, object));
        }
        else if (!(rubyObject = file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getVariable0(threadContext.runtime, "@row", object)).isTrue()) {
            rubyObject = file_DED94C51DE37C71C352A2551F12563A5CC407CCF.setVariable3(threadContext.runtime, "@row", object, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(35).call(threadContext, object, object));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "mtu", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$mtu(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF file_DED94C51DE37C71C352A2551F12563A5CC407CCF, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(36).call(threadContext, rubyObject, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(37).call(threadContext, rubyObject, rubyObject), file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getSymbol5(threadContext.runtime, "dwMtu"));
    }
    
    @JRubyMethod(name = "sent_bytes", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$sent_bytes(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF file_DED94C51DE37C71C352A2551F12563A5CC407CCF, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(38).call(threadContext, rubyObject, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(39).call(threadContext, rubyObject, rubyObject), file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getSymbol6(threadContext.runtime, "dwOutOctets"));
    }
    
    @JRubyMethod(name = "received_bytes", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__10$RUBY$received_bytes(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF file_DED94C51DE37C71C352A2551F12563A5CC407CCF, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(40).call(threadContext, rubyObject, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(41).call(threadContext, rubyObject, rubyObject), file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getSymbol7(threadContext.runtime, "dwInOctets"));
    }
    
    @JRubyMethod(name = "sent_unicast_packets", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__11$RUBY$sent_unicast_packets(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF file_DED94C51DE37C71C352A2551F12563A5CC407CCF, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(42).call(threadContext, rubyObject, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(43).call(threadContext, rubyObject, rubyObject), file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getSymbol8(threadContext.runtime, "dwOutUcastPkts"));
    }
    
    @JRubyMethod(name = "sent_non_unicast_packets", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__12$RUBY$sent_non_unicast_packets(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF file_DED94C51DE37C71C352A2551F12563A5CC407CCF, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(44).call(threadContext, rubyObject, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(45).call(threadContext, rubyObject, rubyObject), file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getSymbol9(threadContext.runtime, "dwOutNUcastPkts"));
    }
    
    @JRubyMethod(name = "sent_packets", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__13$RUBY$sent_packets(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF file_DED94C51DE37C71C352A2551F12563A5CC407CCF, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(46).call(threadContext, rubyObject, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(47).call(threadContext, rubyObject, rubyObject), file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(48).call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "received_unicast_packets", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__14$RUBY$received_unicast_packets(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF file_DED94C51DE37C71C352A2551F12563A5CC407CCF, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(49).call(threadContext, rubyObject, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(50).call(threadContext, rubyObject, rubyObject), file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getSymbol(threadContext.runtime, 10, "dwInUcastPkts"));
    }
    
    @JRubyMethod(name = "received_non_unicast_packets", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__15$RUBY$received_non_unicast_packets(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF file_DED94C51DE37C71C352A2551F12563A5CC407CCF, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(51).call(threadContext, rubyObject, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(52).call(threadContext, rubyObject, rubyObject), file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getSymbol(threadContext.runtime, 11, "dwInNUcastPkts"));
    }
    
    @JRubyMethod(name = "received_packets", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__16$RUBY$received_packets(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF file_DED94C51DE37C71C352A2551F12563A5CC407CCF, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(53).call(threadContext, rubyObject, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(54).call(threadContext, rubyObject, rubyObject), file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(55).call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "traffic", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__17$RUBY$traffic(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF file_DED94C51DE37C71C352A2551F12563A5CC407CCF, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        IRubyObject ret = threadContext.nil;
        ret = threadContext.nil;
        if (file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(56).call(threadContext, rubyObject, rubyObject).isTrue()) {
            final RubyHash constructHash = RuntimeHelpers.constructHash(threadContext.runtime, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getSymbol(threadContext.runtime, 12, "bytes_sent"), file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(57).call(threadContext, rubyObject, rubyObject), file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getSymbol(threadContext.runtime, 13, "bytes_received"), file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(58).call(threadContext, rubyObject, rubyObject), file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getSymbol(threadContext.runtime, 14, "packets_sent"), file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(59).call(threadContext, rubyObject, rubyObject));
            constructHash.fastASetCheckString(threadContext.runtime, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getSymbol(threadContext.runtime, 15, "packets_received"), file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(60).call(threadContext, rubyObject, rubyObject));
            ret = constructHash;
        }
        return ret;
    }
    
    @JRubyMethod(name = "type", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__18$RUBY$type(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF file_DED94C51DE37C71C352A2551F12563A5CC407CCF, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(61).call(threadContext, rubyObject, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(62).call(threadContext, rubyObject, rubyObject), file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getSymbol(threadContext.runtime, 16, "dwType"));
    }
    
    @JRubyMethod(name = "mac_addr", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__19$RUBY$mac_addr(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF file_DED94C51DE37C71C352A2551F12563A5CC407CCF, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(63).call(threadContext, rubyObject, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(64).call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "status", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__20$RUBY$status(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF file_DED94C51DE37C71C352A2551F12563A5CC407CCF, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        final DynamicScope locals = context.getCurrentScope();
        IRubyObject rubyObject2;
        if (file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(65).call(context, rubyObject, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(66).call(context, rubyObject, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(67).call(context, rubyObject, rubyObject), file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getSymbol(context.runtime, 17, "dwAdminStatus")), 1L).isTrue()) {
            locals.setValueZeroDepthZero(file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(68).call(context, rubyObject, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(69).call(context, rubyObject, rubyObject), file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getSymbol(context.runtime, 18, "dwOperStatus")));
            if (!(rubyObject2 = file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(70).call(context, rubyObject, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getConstant(context, "STATUS_TYPES", 19), locals.getValueZeroDepthZeroOrNil(context.nil))).isTrue()) {
                rubyObject2 = locals.getValueZeroDepthZeroOrNil(context.nil);
            }
        }
        else {
            rubyObject2 = file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getString9(context.runtime, 32);
        }
        return rubyObject2;
    }
    
    @JRubyMethod(name = "name", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__21$RUBY$name(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF file_DED94C51DE37C71C352A2551F12563A5CC407CCF, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(71).call(threadContext, rubyObject, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(72).call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "description", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__22$RUBY$description(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF file_DED94C51DE37C71C352A2551F12563A5CC407CCF, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(73).call(threadContext, rubyObject, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(74).call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "type_str", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__23$RUBY$type_str(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF file_DED94C51DE37C71C352A2551F12563A5CC407CCF, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        IRubyObject rubyObject2;
        if (!(rubyObject2 = file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(75).call(context, rubyObject, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getConstant(context, "IANA_IFACE_TYPES", 20), file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(76).call(context, rubyObject, rubyObject))).isTrue()) {
            rubyObject2 = file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(77).call(context, rubyObject, file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(78).call(context, rubyObject, rubyObject));
        }
        return rubyObject2;
    }
    
    @JRubyMethod(name = "for_int_addresses", frame = true, required = 1, optional = 1, rest = -1)
    public static IRubyObject method__24$RUBY$for_int_addresses(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
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
        //    42: goto            59
        //    45: aload           5
        //    47: aload_1        
        //    48: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    51: invokestatic    org/jruby/RubyFixnum.zero:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    54: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: pop            
        //    58: pop            
        //    59: aload           locals
        //    61: aload_0        
        //    62: bipush          79
        //    64: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    67: aload_1        
        //    68: aload_2        
        //    69: aload_0        
        //    70: aload_1        
        //    71: ldc             "Win32"
        //    73: bipush          21
        //    75: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    78: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    81: aload_0        
        //    82: swap           
        //    83: aload_1        
        //    84: ldc             "MIB"
        //    86: bipush          22
        //    88: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    91: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    94: aload_0        
        //    95: swap           
        //    96: aload_1        
        //    97: ldc_w           "IPForwardRow"
        //   100: bipush          23
        //   102: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   108: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   111: pop            
        //   112: aload_0        
        //   113: bipush          80
        //   115: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   118: aload_1        
        //   119: aload_2        
        //   120: aload_0        
        //   121: aload_1        
        //   122: ldc             "Win32"
        //   124: bipush          24
        //   126: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   129: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   132: aload_0        
        //   133: swap           
        //   134: aload_1        
        //   135: ldc             "IPHelper"
        //   137: bipush          25
        //   139: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   142: aload           locals
        //   144: aload_1        
        //   145: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   148: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   151: aload_1        
        //   152: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   155: invokestatic    org/jruby/RubyFixnum.zero:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   158: aload           locals
        //   160: aload_1        
        //   161: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   164: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   167: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   170: pop            
        //   171: aload           locals
        //   173: aload_0        
        //   174: bipush          81
        //   176: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   179: aload_1        
        //   180: aload_2        
        //   181: aload           locals
        //   183: aload_1        
        //   184: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   187: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   190: aload_0        
        //   191: aload_1        
        //   192: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   195: ldc_w           19
        //   198: ldc_w           "dwForwardIfIndex"
        //   201: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   204: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   207: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   210: pop            
        //   211: aload_0        
        //   212: bipush          82
        //   214: invokevirtual   ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   217: aload_1        
        //   218: aload_2        
        //   219: aload_2        
        //   220: aload           locals
        //   222: aload_1        
        //   223: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   226: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   229: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   232: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  59     174     5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    @JRubyMethod(name = "default", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__25$RUBY$default(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF file_DED94C51DE37C71C352A2551F12563A5CC407CCF, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_DED94C51DE37C71C352A2551F12563A5CC407CCF.getCallSite(83).call(threadContext, rubyObject, rubyObject, RubyFixnum.zero(threadContext.runtime));
    }
    
    public static IRubyObject class_1$RUBY$Interface(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF file_DED94C51DE37C71C352A2551F12563A5CC407CCF, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_1$RUBY$Interface(file_DED94C51DE37C71C352A2551F12563A5CC407CCF, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Win32(final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF file_DED94C51DE37C71C352A2551F12563A5CC407CCF, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Win32(file_DED94C51DE37C71C352A2551F12563A5CC407CCF, threadContext, rubyObject, block);
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
        final FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF file_DED94C51DE37C71C352A2551F12563A5CC407CCF = new FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF();
        final String string = FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.class.getClassLoader().getResource("ruby/jit/FILE_DED94C51DE37C71C352A2551F12563A5CC407CCF.class").toString();
        file_DED94C51DE37C71C352A2551F12563A5CC407CCF.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_DED94C51DE37C71C352A2551F12563A5CC407CCF.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
