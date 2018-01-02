// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.exceptions.JumpException;
import org.jruby.RubyFixnum;
import org.jruby.runtime.DynamicScope;
import org.jruby.runtime.Arity;
import org.jruby.RubyString;
import org.jruby.anno.JRubyMethod;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A extends AbstractScript
{
    public FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A() {
        this.filename = "./lib//win32/wlan.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffto_sym\uffffN\uffffsend\uffffF\ufffflayout\uffffF\ufffflayout\uffffF\uffffinclude\uffffF\uffff[]\uffffN\uffffinvert\uffffN\uffff[]\uffffF\uffff[]\uffffF\uffffto_a\uffffN\uffff[]\uffffF\uffffreject\uffffN\uffff==\uffffN\uffffpack\uffffN\ufffflayout\uffffF\ufffflayout\uffffF\uffffread_string\uffffN\uffffto_ptr\uffffN\uffff[]\uffffF\ufffflayout\uffffF\uffffto_a\uffffN\uffff[]\uffffF\uffffreject\uffffN\uffff==\uffffN\uffffpack\uffffN\uffff[]\uffffF\uffffstring\uffffN\ufffflayout\uffffF\ufffflayout\uffffF\ufffflayout\uffffF\ufffflayout\uffffF\uffff==\uffffN\uffff[]\uffffF\uffff==\uffffN\uffff[]\uffffF\uffffjoin\uffffN\uffffunpack\uffffN\uffffread_string\uffffN\uffffto_ptr\uffffN\uffff[]\uffffF\uffff*\uffffN\uffff[]\uffffF\uffffstring\uffffN\ufffflayout\uffffF\ufffflayout\uffffF\uffffread_string\uffffN\uffffto_ptr\uffffN\uffff[]\uffffF\uffffextend\uffffF\ufffftypedef\uffffF\uffffffi_lib\uffffF\uffffffi_convention\uffffF\uffffattach_function\uffffF\uffffattach_function\uffffF\uffffattach_function\uffffF\uffffattach_function\uffffF\uffffattach_function\uffffF\uffffattach_function\uffffF\uffffattach_function\uffffF\uffffattach_function\uffffF\uffffnew\uffffN\uffffnew\uffffN\uffffWlanOpenHandle\uffffN\uffff==\uffffN\uffffget_code_for_int\uffffN\uffffraise\uffffF\uffffget_code_for_int\uffffN\uffffread_handle\uffffN\uffffWlanCloseHandle\uffffN\uffffWlanCloseHandle\uffffN\uffffnew\uffffN\uffffWlanEnumInterfaces\uffffN\uffff==\uffffN\uffffget_code_for_int\uffffN\uffffread_pointer\uffffN\uffffnew\uffffN\ufffftimes\uffffN\uffff[]\uffffN\uffffnew\uffffN\uffff+\uffffN\uffffaddress\uffffN\uffffto_ptr\uffffN\uffff[]\uffffN\uffff*\uffffN\uffffsize\uffffN\uffffnew\uffffN\uffffto_ptr\uffffN\uffff[]\uffffN\uffffnew\uffffN\uffffWlanGetAvailableNetworkList\uffffN\uffff==\uffffN\uffffget_code_for_int\uffffN\uffffread_pointer\uffffN\uffffnew\uffffN\uffff[]\uffffN\ufffftimes\uffffN\uffffnew\uffffN\uffff+\uffffN\uffffaddress\uffffN\uffffto_ptr\uffffN\uffff[]\uffffN\uffff*\uffffN\uffffsize\uffffN\uffffnew\uffffN\uffffeach_bssid_on_interface_and_essid\uffffF\uffffto_ptr\uffffN\uffff[]\uffffN\uffffnew\uffffN\uffffWlanGetNetworkBssList\uffffN\uffff==\uffffN\uffffget_code_for_int\uffffN\uffffread_pointer\uffffN\uffffnew\uffffN\uffff[]\uffffN\ufffftimes\uffffN\uffffnew\uffffN\uffff+\uffffN\uffffaddress\uffffN\uffffto_ptr\uffffN\uffff[]\uffffN\uffff*\uffffN\uffffsize\uffffN\uffffnew\uffffN\uffffeach_interface\uffffF\uffffeach_network_on_interface\uffffF\uffffeach_interface\uffffF\uffffeach_bssid_on_interface\uffffF\uffffnew\uffffN\uffffcontext\uffffF\uffffrescan_all\uffffF\uffffsleep\uffffF\uffffeach_network\uffffF\uffffnew\uffffN\uffffssid\uffffN\uffff[]\uffffN\uffff[]\uffffN\uffff<<\uffffN\uffffeach_interface\uffffF\uffffrescan\uffffF\uffffto_ptr\uffffN\uffff[]\uffffN\uffffWlanScan\uffffN\uffffnew\uffffN\uffffnew\uffffN\uffffnew\uffffN\uffffWlanQueryInterface\uffffN\uffff==\uffffN\uffffget_code_for_int\uffffN\uffffblock_given?\uffffF\uffff[]\uffffN\uffffdo_query_iface\uffffF\uffffread_ulong\uffffN\uffffread_pointer\uffffN\uffff[]\uffffN\uffffdo_query_iface\uffffF\uffffread_long\uffffN\uffffread_pointer\uffffN\uffff[]\uffffN\uffffdo_query_iface\uffffF\uffffread_pointer\uffffN\uffffnew\uffffN\uffffcurrent_profile_as_xml_crap\uffffF\uffffpack\uffffN\uffffreject\uffffN\uffffunpack\uffffN\uffff==\uffffN\uffffcurrent_connection\uffffF\uffff[]\uffffN\uffffnew\uffffN\uffffWlanGetProfile\uffffN\uffff==\uffffN\uffffget_code_for_int\uffffN\uffffloop\uffffF\uffff+\uffffN\uffffread_string\uffffN\uffffread_pointer\uffffN\uffffreject\uffffN\uffffunpack\uffffN\uffff==\uffffN\uffffpack\uffffN\uffffend_with?\uffffN\uffffread_string\uffffN\uffffread_pointer\uffffN\uffffraise\uffffF\uffffnew\uffffN\uffffWlanHostedNetworkQueryStatus\uffffN\uffff==\uffffN\uffffget_code_for_int\uffffN\uffffread_pointer\uffffN\uffffnew\uffffN\uffffcontext\uffffF\uffffnew\uffffN\uffffeach_interface\uffffF\uffffchannel_number\uffffF\uffffchannel=\uffffN\uffffchannel=\uffffV\uffffcurrent_rssi\uffffF\uffffrssi=\uffffN\uffffrssi=\uffffV\uffffcurrent_profile\uffffF\uffffeach\uffffN\ufffflines\uffffN\uffff[]\uffffN\ufffflast_match\uffffN\uffffessid=\uffffN\uffffessid=\uffffV\uffffnew\uffffN\uffffcontext\uffffF\uffffrescan_all\uffffF\uffffsleep\uffffF\uffffeach_bssid\uffffF\uffffnew\uffffN\uffffssid\uffffN\uffffmac_addr\uffffN\uffff[]\uffffN\uffff[]\uffffN\uffffextend\uffffF\uffff\u0014o\u0015\u0000\u009d\u0001\u0000\u0000\u0000\u001d\u0000\u0000\u000e\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(9, " (", this.getEncoding0());
        this.setByteList(3, "read_", this.getEncoding0());
        this.setByteList(7, "Wlanapi", this.getEncoding0());
        this.setByteList(13, "<name>([^<]*)<\\/name>", this.getEncoding0());
        this.setByteList(2, "win32/errors", this.getEncoding0());
        this.setByteList(10, ")", this.getEncoding0());
        this.setByteList(11, "</WLANProfile>", this.getEncoding0());
        this.setByteList(12, "i can't develop cuz i can't attach the necessary function", this.getEncoding0());
        this.setByteList(4, "C*", this.getEncoding0());
        this.setByteList(8, "could not open wlan ", this.getEncoding0());
        this.setByteList(5, "H2", this.getEncoding0());
        this.setByteList(6, ":", this.getEncoding0());
        this.setByteList(0, "java", this.getEncoding0());
        this.setByteList(1, "ffi", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite0().call(threadContext, rubyObject, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getString0(threadContext.runtime, 32));
        file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite1().call(threadContext, rubyObject, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getString1(threadContext.runtime, 32));
        file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite2().call(threadContext, rubyObject, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getString2(threadContext.runtime, 32));
        RuntimeHelpers.setGlobalVariable(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0(threadContext.runtime, "uint"), threadContext.runtime, "$HANDLE_TYPE");
        class_0$RUBY$MemoryPointer(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, threadContext, rubyObject, Block.NULL_BLOCK);
        module__2$RUBY$Win32(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
        class_27$RUBY$CouldNotOpenWlanError(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, threadContext, rubyObject, Block.NULL_BLOCK);
        return module__28$RUBY$Win32(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject class_0$RUBY$MemoryPointer(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: aload_1        
        //     2: aload_0        
        //     3: aload_1        
        //     4: ldc             "FFI"
        //     6: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //     9: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    12: swap           
        //    13: ldc             "MemoryPointer"
        //    15: swap           
        //    16: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    19: dup            
        //    20: astore_2       
        //    21: aload_1        
        //    22: swap           
        //    23: aload_0        
        //    24: aload_1        
        //    25: ldc             ",0,0,-1"
        //    27: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    30: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    33: aload_1        
        //    34: aload_2        
        //    35: aload_0        
        //    36: ldc             "read_handle"
        //    38: ldc             "method__1$RUBY$read_handle"
        //    40: ldc             "sym,0,0,-1"
        //    42: iconst_0       
        //    43: ldc             "./lib//win32/wlan.rb"
        //    45: ldc             8
        //    47: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    50: ldc             "NONE"
        //    52: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: aload_1        
        //    56: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    59: goto            67
        //    62: aload_1        
        //    63: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    66: athrow         
        //    67: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  33     55     62     67     Any
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
    
    @JRubyMethod(name = "read_handle", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__1$RUBY$read_handle(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        IRubyObject sym = threadContext.nil;
        sym = file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite3().call(threadContext, rubyObject, RubyString.newStringLight(threadContext.runtime, 20).append(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getString3(threadContext.runtime, 32)).append(RuntimeHelpers.getGlobalVariable(threadContext.runtime, "$HANDLE_TYPE").asString()));
        return file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite4().call(threadContext, rubyObject, rubyObject, sym);
    }
    
    public static IRubyObject class_0$RUBY$MemoryPointer(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_0$RUBY$MemoryPointer(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__2$RUBY$Win32(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.module__3$RUBY$Wlan:(Lruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__3$RUBY$Wlan(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Wlan"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc             "FFI"
        //    33: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    36: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    39: aload_0        
        //    40: swap           
        //    41: aload_1        
        //    42: ldc_w           "Struct"
        //    45: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom2:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    48: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    51: invokestatic    ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.class_4$RUBY$GUID:(Lruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: pop            
        //    55: aload_0        
        //    56: aload_1        
        //    57: aload_2        
        //    58: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    61: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    64: invokestatic    ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.module__5$RUBY$InterfaceOPCode:(Lruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: pop            
        //    68: aload_0        
        //    69: aload_1        
        //    70: aload_2        
        //    71: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    77: invokestatic    ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.module__6$RUBY$InterfaceState:(Lruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: pop            
        //    81: aload_0        
        //    82: aload_1        
        //    83: aload_0        
        //    84: aload_1        
        //    85: ldc             "FFI"
        //    87: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant6:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    90: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    93: aload_0        
        //    94: swap           
        //    95: aload_1        
        //    96: ldc_w           "Struct"
        //    99: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom7:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   102: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   105: invokestatic    ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.class_7$RUBY$InterfaceInfo:(Lruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   108: pop            
        //   109: aload_1        
        //   110: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   113: invokestatic    org/jruby/RubyFixnum.zero:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   116: aload_1        
        //   117: ldc_w           "ANY_SIZE"
        //   120: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   123: pop            
        //   124: aload_0        
        //   125: aload_1        
        //   126: aload_0        
        //   127: aload_1        
        //   128: ldc             "FFI"
        //   130: bipush          10
        //   132: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   135: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   138: aload_0        
        //   139: swap           
        //   140: aload_1        
        //   141: ldc_w           "Struct"
        //   144: bipush          11
        //   146: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   149: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   152: invokestatic    ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.class_10$RUBY$InterfaceInfoList:(Lruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   155: pop            
        //   156: aload_0        
        //   157: aload_1        
        //   158: aload_0        
        //   159: aload_1        
        //   160: ldc             "FFI"
        //   162: bipush          13
        //   164: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   167: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   170: aload_0        
        //   171: swap           
        //   172: aload_1        
        //   173: ldc_w           "Struct"
        //   176: bipush          14
        //   178: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   181: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   184: invokestatic    ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.class_11$RUBY$Dot11SSID:(Lruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   187: pop            
        //   188: aload_0        
        //   189: aload_1        
        //   190: aload_0        
        //   191: aload_1        
        //   192: ldc             "FFI"
        //   194: bipush          17
        //   196: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   199: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   202: aload_0        
        //   203: swap           
        //   204: aload_1        
        //   205: ldc_w           "Struct"
        //   208: bipush          18
        //   210: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   213: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   216: invokestatic    ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.class_13$RUBY$AvailableNetwork:(Lruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   219: pop            
        //   220: aload_0        
        //   221: aload_1        
        //   222: aload_0        
        //   223: aload_1        
        //   224: ldc             "FFI"
        //   226: bipush          21
        //   228: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   231: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   234: aload_0        
        //   235: swap           
        //   236: aload_1        
        //   237: ldc_w           "Struct"
        //   240: bipush          22
        //   242: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   245: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   248: invokestatic    ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.class_16$RUBY$AvailableNetworkList:(Lruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   251: pop            
        //   252: aload_0        
        //   253: aload_1        
        //   254: aload_0        
        //   255: aload_1        
        //   256: ldc             "FFI"
        //   258: bipush          23
        //   260: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   263: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   266: aload_0        
        //   267: swap           
        //   268: aload_1        
        //   269: ldc_w           "Struct"
        //   272: bipush          24
        //   274: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   277: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   280: invokestatic    ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.class_17$RUBY$Dot11MacAddress:(Lruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   283: pop            
        //   284: aload_0        
        //   285: aload_1        
        //   286: aload_0        
        //   287: aload_1        
        //   288: ldc             "FFI"
        //   290: bipush          26
        //   292: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   295: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   298: aload_0        
        //   299: swap           
        //   300: aload_1        
        //   301: ldc_w           "Struct"
        //   304: bipush          27
        //   306: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   309: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   312: invokestatic    ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.class_18$RUBY$WlanRateSet:(Lruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   315: pop            
        //   316: aload_0        
        //   317: aload_1        
        //   318: aload_0        
        //   319: aload_1        
        //   320: ldc             "FFI"
        //   322: bipush          31
        //   324: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   327: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   330: aload_0        
        //   331: swap           
        //   332: aload_1        
        //   333: ldc_w           "Struct"
        //   336: bipush          32
        //   338: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   341: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   344: invokestatic    ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.class_19$RUBY$BSSEntry:(Lruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   347: pop            
        //   348: aload_0        
        //   349: aload_1        
        //   350: aload_0        
        //   351: aload_1        
        //   352: ldc             "FFI"
        //   354: bipush          34
        //   356: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   359: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   362: aload_0        
        //   363: swap           
        //   364: aload_1        
        //   365: ldc_w           "Struct"
        //   368: bipush          35
        //   370: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   373: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   376: invokestatic    ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.class_24$RUBY$BSSList:(Lruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   379: pop            
        //   380: aload_0        
        //   381: aload_1        
        //   382: aload_0        
        //   383: aload_1        
        //   384: ldc             "FFI"
        //   386: bipush          36
        //   388: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   391: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   394: aload_0        
        //   395: swap           
        //   396: aload_1        
        //   397: ldc_w           "Struct"
        //   400: bipush          37
        //   402: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   405: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   408: invokestatic    ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.class_25$RUBY$ConnectionAttributes:(Lruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   411: pop            
        //   412: aload_0        
        //   413: bipush          51
        //   415: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   418: aload_1        
        //   419: aload_2        
        //   420: aload_2        
        //   421: aload_0        
        //   422: aload_1        
        //   423: ldc             "FFI"
        //   425: bipush          38
        //   427: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   430: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   433: aload_0        
        //   434: swap           
        //   435: aload_1        
        //   436: ldc_w           "Library"
        //   439: bipush          39
        //   441: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   444: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   447: pop            
        //   448: aload_0        
        //   449: bipush          52
        //   451: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   454: aload_1        
        //   455: aload_2        
        //   456: aload_2        
        //   457: aload_1        
        //   458: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   461: ldc             "$HANDLE_TYPE"
        //   463: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.getGlobalVariable:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   466: aload_0        
        //   467: aload_1        
        //   468: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   471: ldc_w           91
        //   474: ldc_w           "handle"
        //   477: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   480: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   483: pop            
        //   484: aload_0        
        //   485: bipush          53
        //   487: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   490: aload_1        
        //   491: aload_2        
        //   492: aload_2        
        //   493: aload_0        
        //   494: aload_1        
        //   495: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   498: bipush          32
        //   500: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   503: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   506: pop            
        //   507: aload_0        
        //   508: bipush          54
        //   510: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   513: aload_1        
        //   514: aload_2        
        //   515: aload_2        
        //   516: aload_0        
        //   517: aload_1        
        //   518: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   521: ldc_w           92
        //   524: ldc_w           "stdcall"
        //   527: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   530: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   533: pop            
        //   534: aload_0        
        //   535: bipush          55
        //   537: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   540: aload_1        
        //   541: aload_2        
        //   542: aload_2        
        //   543: aload_0        
        //   544: aload_1        
        //   545: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   548: ldc_w           93
        //   551: ldc_w           "WlanOpenHandle"
        //   554: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   557: aload_1        
        //   558: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   561: aload_0        
        //   562: aload_1        
        //   563: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   566: ldc             "uint"
        //   568: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   571: aload_0        
        //   572: aload_1        
        //   573: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   576: ldc_w           94
        //   579: ldc_w           "pointer"
        //   582: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   585: aload_0        
        //   586: aload_1        
        //   587: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   590: ldc_w           94
        //   593: ldc_w           "pointer"
        //   596: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   599: aload_0        
        //   600: aload_1        
        //   601: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   604: ldc_w           94
        //   607: ldc_w           "pointer"
        //   610: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   613: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   616: aload_0        
        //   617: aload_1        
        //   618: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   621: ldc             "uint"
        //   623: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   626: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   629: pop            
        //   630: aload_0        
        //   631: bipush          56
        //   633: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   636: aload_1        
        //   637: aload_2        
        //   638: aload_2        
        //   639: aload_0        
        //   640: aload_1        
        //   641: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   644: ldc_w           95
        //   647: ldc_w           "WlanCloseHandle"
        //   650: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   653: aload_1        
        //   654: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   657: aload_0        
        //   658: aload_1        
        //   659: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   662: ldc_w           91
        //   665: ldc_w           "handle"
        //   668: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   671: aload_0        
        //   672: aload_1        
        //   673: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   676: ldc_w           94
        //   679: ldc_w           "pointer"
        //   682: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   685: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   688: aload_0        
        //   689: aload_1        
        //   690: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   693: ldc             "uint"
        //   695: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   698: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   701: pop            
        //   702: aload_0        
        //   703: bipush          57
        //   705: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   708: aload_1        
        //   709: aload_2        
        //   710: aload_2        
        //   711: aload_0        
        //   712: aload_1        
        //   713: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   716: ldc_w           96
        //   719: ldc_w           "WlanEnumInterfaces"
        //   722: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   725: aload_1        
        //   726: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   729: aload_0        
        //   730: aload_1        
        //   731: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   734: ldc_w           91
        //   737: ldc_w           "handle"
        //   740: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   743: aload_0        
        //   744: aload_1        
        //   745: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   748: ldc_w           94
        //   751: ldc_w           "pointer"
        //   754: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   757: aload_0        
        //   758: aload_1        
        //   759: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   762: ldc_w           94
        //   765: ldc_w           "pointer"
        //   768: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   771: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   774: aload_0        
        //   775: aload_1        
        //   776: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   779: ldc             "uint"
        //   781: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   784: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   787: pop            
        //   788: aload_0        
        //   789: bipush          58
        //   791: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   794: aload_1        
        //   795: aload_2        
        //   796: aload_2        
        //   797: aload_0        
        //   798: aload_1        
        //   799: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   802: ldc_w           97
        //   805: ldc_w           "WlanScan"
        //   808: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   811: aload_1        
        //   812: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   815: aload_0        
        //   816: aload_1        
        //   817: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   820: ldc_w           91
        //   823: ldc_w           "handle"
        //   826: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   829: aload_0        
        //   830: aload_1        
        //   831: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   834: ldc_w           94
        //   837: ldc_w           "pointer"
        //   840: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   843: aload_0        
        //   844: aload_1        
        //   845: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   848: ldc_w           94
        //   851: ldc_w           "pointer"
        //   854: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   857: aload_0        
        //   858: aload_1        
        //   859: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   862: ldc_w           94
        //   865: ldc_w           "pointer"
        //   868: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   871: aload_0        
        //   872: aload_1        
        //   873: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   876: ldc_w           94
        //   879: ldc_w           "pointer"
        //   882: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   885: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   888: aload_0        
        //   889: aload_1        
        //   890: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   893: ldc             "uint"
        //   895: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   898: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   901: pop            
        //   902: aload_0        
        //   903: bipush          59
        //   905: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   908: aload_1        
        //   909: aload_2        
        //   910: aload_2        
        //   911: aload_0        
        //   912: aload_1        
        //   913: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   916: ldc_w           98
        //   919: ldc_w           "WlanGetAvailableNetworkList"
        //   922: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   925: aload_1        
        //   926: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   929: aload_0        
        //   930: aload_1        
        //   931: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   934: ldc_w           91
        //   937: ldc_w           "handle"
        //   940: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   943: aload_0        
        //   944: aload_1        
        //   945: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   948: ldc_w           94
        //   951: ldc_w           "pointer"
        //   954: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   957: aload_0        
        //   958: aload_1        
        //   959: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   962: ldc             "uint"
        //   964: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   967: aload_0        
        //   968: aload_1        
        //   969: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   972: ldc_w           94
        //   975: ldc_w           "pointer"
        //   978: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   981: aload_0        
        //   982: aload_1        
        //   983: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   986: ldc_w           94
        //   989: ldc_w           "pointer"
        //   992: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   995: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   998: aload_0        
        //   999: aload_1        
        //  1000: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1003: ldc             "uint"
        //  1005: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //  1008: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1011: pop            
        //  1012: aload_0        
        //  1013: bipush          60
        //  1015: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1018: aload_1        
        //  1019: aload_2        
        //  1020: aload_2        
        //  1021: aload_0        
        //  1022: aload_1        
        //  1023: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1026: ldc_w           99
        //  1029: ldc_w           "WlanQueryInterface"
        //  1032: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1035: aload_1        
        //  1036: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1039: bipush          7
        //  1041: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //  1044: dup            
        //  1045: iconst_0       
        //  1046: aload_0        
        //  1047: aload_1        
        //  1048: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1051: ldc_w           91
        //  1054: ldc_w           "handle"
        //  1057: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1060: aastore        
        //  1061: dup            
        //  1062: iconst_1       
        //  1063: aload_0        
        //  1064: aload_1        
        //  1065: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1068: ldc_w           94
        //  1071: ldc_w           "pointer"
        //  1074: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1077: aastore        
        //  1078: dup            
        //  1079: iconst_2       
        //  1080: aload_0        
        //  1081: aload_1        
        //  1082: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1085: ldc             "uint"
        //  1087: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //  1090: aastore        
        //  1091: dup            
        //  1092: iconst_3       
        //  1093: aload_0        
        //  1094: aload_1        
        //  1095: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1098: ldc_w           94
        //  1101: ldc_w           "pointer"
        //  1104: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1107: aastore        
        //  1108: dup            
        //  1109: iconst_4       
        //  1110: aload_0        
        //  1111: aload_1        
        //  1112: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1115: ldc_w           94
        //  1118: ldc_w           "pointer"
        //  1121: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1124: aastore        
        //  1125: dup            
        //  1126: iconst_5       
        //  1127: aload_0        
        //  1128: aload_1        
        //  1129: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1132: ldc_w           94
        //  1135: ldc_w           "pointer"
        //  1138: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1141: aastore        
        //  1142: dup            
        //  1143: bipush          6
        //  1145: aload_0        
        //  1146: aload_1        
        //  1147: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1150: ldc_w           94
        //  1153: ldc_w           "pointer"
        //  1156: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1159: aastore        
        //  1160: invokestatic    org/jruby/RubyArray.newArrayNoCopy:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //  1163: aload_0        
        //  1164: aload_1        
        //  1165: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1168: ldc             "uint"
        //  1170: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //  1173: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1176: pop            
        //  1177: aload_0        
        //  1178: bipush          61
        //  1180: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1183: aload_1        
        //  1184: aload_2        
        //  1185: aload_2        
        //  1186: aload_0        
        //  1187: aload_1        
        //  1188: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1191: ldc_w           100
        //  1194: ldc_w           "WlanGetNetworkBssList"
        //  1197: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1200: aload_1        
        //  1201: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1204: bipush          7
        //  1206: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //  1209: dup            
        //  1210: iconst_0       
        //  1211: aload_0        
        //  1212: aload_1        
        //  1213: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1216: ldc_w           91
        //  1219: ldc_w           "handle"
        //  1222: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1225: aastore        
        //  1226: dup            
        //  1227: iconst_1       
        //  1228: aload_0        
        //  1229: aload_1        
        //  1230: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1233: ldc_w           94
        //  1236: ldc_w           "pointer"
        //  1239: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1242: aastore        
        //  1243: dup            
        //  1244: iconst_2       
        //  1245: aload_0        
        //  1246: aload_1        
        //  1247: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1250: ldc_w           94
        //  1253: ldc_w           "pointer"
        //  1256: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1259: aastore        
        //  1260: dup            
        //  1261: iconst_3       
        //  1262: aload_0        
        //  1263: aload_1        
        //  1264: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1267: ldc             "uint"
        //  1269: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //  1272: aastore        
        //  1273: dup            
        //  1274: iconst_4       
        //  1275: aload_0        
        //  1276: aload_1        
        //  1277: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1280: ldc_w           101
        //  1283: ldc_w           "bool"
        //  1286: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1289: aastore        
        //  1290: dup            
        //  1291: iconst_5       
        //  1292: aload_0        
        //  1293: aload_1        
        //  1294: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1297: ldc_w           94
        //  1300: ldc_w           "pointer"
        //  1303: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1306: aastore        
        //  1307: dup            
        //  1308: bipush          6
        //  1310: aload_0        
        //  1311: aload_1        
        //  1312: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1315: ldc_w           94
        //  1318: ldc_w           "pointer"
        //  1321: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1324: aastore        
        //  1325: invokestatic    org/jruby/RubyArray.newArrayNoCopy:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //  1328: aload_0        
        //  1329: aload_1        
        //  1330: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1333: ldc             "uint"
        //  1335: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //  1338: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1341: pop            
        //  1342: aload_0        
        //  1343: bipush          62
        //  1345: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1348: aload_1        
        //  1349: aload_2        
        //  1350: aload_2        
        //  1351: aload_0        
        //  1352: aload_1        
        //  1353: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1356: ldc_w           102
        //  1359: ldc_w           "WlanGetProfile"
        //  1362: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1365: aload_1        
        //  1366: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1369: bipush          7
        //  1371: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //  1374: dup            
        //  1375: iconst_0       
        //  1376: aload_0        
        //  1377: aload_1        
        //  1378: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1381: ldc_w           91
        //  1384: ldc_w           "handle"
        //  1387: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1390: aastore        
        //  1391: dup            
        //  1392: iconst_1       
        //  1393: aload_0        
        //  1394: aload_1        
        //  1395: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1398: ldc_w           94
        //  1401: ldc_w           "pointer"
        //  1404: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1407: aastore        
        //  1408: dup            
        //  1409: iconst_2       
        //  1410: aload_0        
        //  1411: aload_1        
        //  1412: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1415: ldc_w           94
        //  1418: ldc_w           "pointer"
        //  1421: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1424: aastore        
        //  1425: dup            
        //  1426: iconst_3       
        //  1427: aload_0        
        //  1428: aload_1        
        //  1429: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1432: ldc_w           94
        //  1435: ldc_w           "pointer"
        //  1438: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1441: aastore        
        //  1442: dup            
        //  1443: iconst_4       
        //  1444: aload_0        
        //  1445: aload_1        
        //  1446: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1449: ldc_w           94
        //  1452: ldc_w           "pointer"
        //  1455: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1458: aastore        
        //  1459: dup            
        //  1460: iconst_5       
        //  1461: aload_0        
        //  1462: aload_1        
        //  1463: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1466: ldc             "uint"
        //  1468: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //  1471: aastore        
        //  1472: dup            
        //  1473: bipush          6
        //  1475: aload_0        
        //  1476: aload_1        
        //  1477: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1480: ldc_w           94
        //  1483: ldc_w           "pointer"
        //  1486: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //  1489: aastore        
        //  1490: invokestatic    org/jruby/RubyArray.newArrayNoCopy:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //  1493: aload_0        
        //  1494: aload_1        
        //  1495: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1498: ldc             "uint"
        //  1500: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //  1503: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1506: aload_1        
        //  1507: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //  1510: goto            1518
        //  1513: aload_1        
        //  1514: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //  1517: athrow         
        //  1518: aload_1        
        //  1519: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //  1522: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     1510   1513   1518   Any
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
    
    public static IRubyObject class_4$RUBY$GUID(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc             "GUID"
        //    19: swap           
        //    20: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    23: dup            
        //    24: astore_2       
        //    25: aload_1        
        //    26: swap           
        //    27: aload_0        
        //    28: aload_1        
        //    29: ldc             ",0,0,-1"
        //    31: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getScope3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    34: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    37: aload_0        
        //    38: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite5:()Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_2        
        //    44: bipush          8
        //    46: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: dup            
        //    50: iconst_0       
        //    51: aload_0        
        //    52: aload_1        
        //    53: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    56: ldc             "Data1"
        //    58: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    61: aastore        
        //    62: dup            
        //    63: iconst_1       
        //    64: aload_0        
        //    65: aload_1        
        //    66: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    69: ldc             "uint"
        //    71: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    74: aastore        
        //    75: dup            
        //    76: iconst_2       
        //    77: aload_0        
        //    78: aload_1        
        //    79: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    82: ldc             "Data2"
        //    84: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    87: aastore        
        //    88: dup            
        //    89: iconst_3       
        //    90: aload_0        
        //    91: aload_1        
        //    92: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    95: ldc             "ushort"
        //    97: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   100: aastore        
        //   101: dup            
        //   102: iconst_4       
        //   103: aload_0        
        //   104: aload_1        
        //   105: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   108: ldc             "Data3"
        //   110: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   113: aastore        
        //   114: dup            
        //   115: iconst_5       
        //   116: aload_0        
        //   117: aload_1        
        //   118: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   121: ldc             "ushort"
        //   123: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   126: aastore        
        //   127: dup            
        //   128: bipush          6
        //   130: aload_0        
        //   131: aload_1        
        //   132: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   135: ldc             "Data4"
        //   137: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   140: aastore        
        //   141: dup            
        //   142: bipush          7
        //   144: aload_1        
        //   145: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   148: aload_0        
        //   149: aload_1        
        //   150: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   153: ldc             "char"
        //   155: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol6:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   158: aload_0        
        //   159: aload_1        
        //   160: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   163: bipush          8
        //   165: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum0:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   168: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   171: aastore        
        //   172: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   175: aload_1        
        //   176: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   179: goto            187
        //   182: aload_1        
        //   183: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   186: athrow         
        //   187: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  37     175    182    187    Any
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
    
    public static IRubyObject class_4$RUBY$GUID(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_4$RUBY$GUID(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__5$RUBY$InterfaceOPCode(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc_w           "InterfaceOPCode"
        //    11: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    14: dup            
        //    15: astore_2       
        //    16: aload_1        
        //    17: swap           
        //    18: aload_0        
        //    19: aload_1        
        //    20: ldc             ",0,0,-1"
        //    22: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getScope4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    25: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    28: aload_1        
        //    29: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    32: aload_0        
        //    33: aload_1        
        //    34: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    37: ldc_w           "autoconf_start"
        //    40: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol7:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    43: aload_1        
        //    44: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    47: invokestatic    org/jruby/RubyFixnum.zero:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    50: aload_0        
        //    51: aload_1        
        //    52: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    55: ldc_w           "autoconf_enabled"
        //    58: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol8:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    61: aload_1        
        //    62: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    65: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    68: aload_0        
        //    69: aload_1        
        //    70: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    73: ldc_w           "background_scan_enabled"
        //    76: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol9:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    79: aload_1        
        //    80: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    83: invokestatic    org/jruby/RubyFixnum.two:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    86: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructHash:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyHash;
        //    89: dup            
        //    90: aload_1        
        //    91: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    94: aload_0        
        //    95: aload_1        
        //    96: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    99: ldc_w           10
        //   102: ldc_w           "media_streaming_mode"
        //   105: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   108: aload_1        
        //   109: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   112: invokestatic    org/jruby/RubyFixnum.three:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   115: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   118: dup            
        //   119: aload_1        
        //   120: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   123: aload_0        
        //   124: aload_1        
        //   125: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   128: ldc_w           11
        //   131: ldc_w           "radio_state"
        //   134: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   137: aload_1        
        //   138: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   141: invokestatic    org/jruby/RubyFixnum.four:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   144: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   147: dup            
        //   148: aload_1        
        //   149: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   152: aload_0        
        //   153: aload_1        
        //   154: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   157: ldc_w           12
        //   160: ldc_w           "bss_type"
        //   163: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   166: aload_1        
        //   167: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   170: invokestatic    org/jruby/RubyFixnum.five:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   173: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   176: dup            
        //   177: aload_1        
        //   178: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   181: aload_0        
        //   182: aload_1        
        //   183: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   186: ldc_w           13
        //   189: ldc_w           "interface_state"
        //   192: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   195: aload_0        
        //   196: aload_1        
        //   197: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   200: bipush          6
        //   202: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   205: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   208: dup            
        //   209: aload_1        
        //   210: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   213: aload_0        
        //   214: aload_1        
        //   215: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   218: ldc_w           14
        //   221: ldc_w           "current_connection"
        //   224: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   227: aload_0        
        //   228: aload_1        
        //   229: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   232: bipush          7
        //   234: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum2:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   237: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   240: dup            
        //   241: aload_1        
        //   242: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   245: aload_0        
        //   246: aload_1        
        //   247: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   250: ldc_w           15
        //   253: ldc_w           "channel_number"
        //   256: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   259: aload_0        
        //   260: aload_1        
        //   261: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   264: bipush          8
        //   266: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum0:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   269: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   272: dup            
        //   273: aload_1        
        //   274: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   277: aload_0        
        //   278: aload_1        
        //   279: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   282: ldc_w           16
        //   285: ldc_w           "supported_infrastructure_auth_cipher_pairs"
        //   288: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   291: aload_0        
        //   292: aload_1        
        //   293: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   296: bipush          9
        //   298: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum3:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   301: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   304: dup            
        //   305: aload_1        
        //   306: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   309: aload_0        
        //   310: aload_1        
        //   311: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   314: ldc_w           17
        //   317: ldc_w           "supported_adhoc_auth_cipher_pairs"
        //   320: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   323: aload_0        
        //   324: aload_1        
        //   325: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   328: bipush          10
        //   330: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   333: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   336: dup            
        //   337: aload_1        
        //   338: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   341: aload_0        
        //   342: aload_1        
        //   343: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   346: ldc_w           18
        //   349: ldc_w           "supported_country_or_region_string_list"
        //   352: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   355: aload_0        
        //   356: aload_1        
        //   357: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   360: bipush          11
        //   362: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum5:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   365: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   368: dup            
        //   369: aload_1        
        //   370: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   373: aload_0        
        //   374: aload_1        
        //   375: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   378: ldc_w           19
        //   381: ldc_w           "current_operation_mode"
        //   384: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   387: aload_0        
        //   388: aload_1        
        //   389: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   392: bipush          12
        //   394: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum6:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   397: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   400: dup            
        //   401: aload_1        
        //   402: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   405: aload_0        
        //   406: aload_1        
        //   407: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   410: ldc             20
        //   412: ldc_w           "supported_safe_mode"
        //   415: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   418: aload_0        
        //   419: aload_1        
        //   420: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   423: bipush          13
        //   425: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   428: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   431: dup            
        //   432: aload_1        
        //   433: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   436: aload_0        
        //   437: aload_1        
        //   438: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   441: ldc_w           21
        //   444: ldc_w           "certified_safe_mode"
        //   447: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   450: aload_0        
        //   451: aload_1        
        //   452: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   455: bipush          14
        //   457: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   460: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   463: dup            
        //   464: aload_1        
        //   465: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   468: aload_0        
        //   469: aload_1        
        //   470: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   473: ldc_w           22
        //   476: ldc_w           "hosted_network_capable"
        //   479: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   482: aload_0        
        //   483: aload_1        
        //   484: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   487: ldc_w           268435455
        //   490: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum9:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   493: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   496: dup            
        //   497: aload_1        
        //   498: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   501: aload_0        
        //   502: aload_1        
        //   503: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   506: ldc_w           23
        //   509: ldc_w           "opcode_msm_start"
        //   512: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   515: aload_0        
        //   516: aload_1        
        //   517: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   520: bipush          10
        //   522: ldc_w           268435712
        //   525: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyFixnum;
        //   528: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   531: dup            
        //   532: aload_1        
        //   533: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   536: aload_0        
        //   537: aload_1        
        //   538: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   541: ldc_w           24
        //   544: ldc_w           "statistics"
        //   547: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   550: aload_0        
        //   551: aload_1        
        //   552: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   555: bipush          11
        //   557: ldc_w           268435713
        //   560: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyFixnum;
        //   563: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   566: dup            
        //   567: aload_1        
        //   568: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   571: aload_0        
        //   572: aload_1        
        //   573: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   576: ldc_w           25
        //   579: ldc_w           "rssi"
        //   582: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   585: aload_0        
        //   586: aload_1        
        //   587: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   590: bipush          12
        //   592: ldc_w           268435714
        //   595: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyFixnum;
        //   598: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   601: dup            
        //   602: aload_1        
        //   603: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   606: aload_0        
        //   607: aload_1        
        //   608: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   611: ldc_w           26
        //   614: ldc_w           "msm_end"
        //   617: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   620: aload_0        
        //   621: aload_1        
        //   622: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   625: bipush          13
        //   627: ldc_w           33554431
        //   630: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyFixnum;
        //   633: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   636: dup            
        //   637: aload_1        
        //   638: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   641: aload_0        
        //   642: aload_1        
        //   643: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   646: ldc_w           27
        //   649: ldc_w           "security_start"
        //   652: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   655: aload_0        
        //   656: aload_1        
        //   657: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   660: bipush          14
        //   662: ldc_w           536936448
        //   665: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyFixnum;
        //   668: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   671: dup            
        //   672: aload_1        
        //   673: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   676: aload_0        
        //   677: aload_1        
        //   678: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   681: ldc_w           28
        //   684: ldc_w           "security_end"
        //   687: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   690: aload_0        
        //   691: aload_1        
        //   692: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   695: bipush          15
        //   697: ldc_w           805306367
        //   700: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyFixnum;
        //   703: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   706: dup            
        //   707: aload_1        
        //   708: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   711: aload_0        
        //   712: aload_1        
        //   713: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   716: ldc_w           29
        //   719: ldc_w           "ihv_start"
        //   722: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   725: aload_0        
        //   726: aload_1        
        //   727: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   730: bipush          16
        //   732: ldc_w           805306368
        //   735: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyFixnum;
        //   738: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   741: dup            
        //   742: aload_1        
        //   743: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   746: aload_0        
        //   747: aload_1        
        //   748: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   751: ldc_w           30
        //   754: ldc_w           "ihv_end"
        //   757: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   760: aload_0        
        //   761: aload_1        
        //   762: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   765: bipush          17
        //   767: ldc_w           1073741823
        //   770: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyFixnum;
        //   773: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   776: aload_1        
        //   777: ldc_w           "MAP"
        //   780: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   783: aload_1        
        //   784: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   787: goto            795
        //   790: aload_1        
        //   791: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   794: athrow         
        //   795: aload_1        
        //   796: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   799: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  28     787    790    795    Any
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
    
    public static IRubyObject module__5$RUBY$InterfaceOPCode(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__5$RUBY$InterfaceOPCode(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__6$RUBY$InterfaceState(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc_w           "InterfaceState"
        //    11: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    14: dup            
        //    15: astore_2       
        //    16: aload_1        
        //    17: swap           
        //    18: aload_0        
        //    19: aload_1        
        //    20: ldc             ",0,0,-1"
        //    22: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getScope5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    25: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    28: aload_1        
        //    29: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    32: aload_0        
        //    33: aload_1        
        //    34: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    37: ldc_w           31
        //    40: ldc_w           "not_ready"
        //    43: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    46: aload_1        
        //    47: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    50: invokestatic    org/jruby/RubyFixnum.zero:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    53: aload_0        
        //    54: aload_1        
        //    55: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    58: ldc_w           32
        //    61: ldc_w           "connected"
        //    64: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    67: aload_1        
        //    68: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    71: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    74: aload_0        
        //    75: aload_1        
        //    76: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    79: ldc_w           33
        //    82: ldc_w           "ad_hoc"
        //    85: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    88: aload_1        
        //    89: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    92: invokestatic    org/jruby/RubyFixnum.two:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    95: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructHash:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyHash;
        //    98: dup            
        //    99: aload_1        
        //   100: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   103: aload_0        
        //   104: aload_1        
        //   105: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   108: ldc_w           34
        //   111: ldc_w           "disconnecting"
        //   114: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   117: aload_1        
        //   118: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   121: invokestatic    org/jruby/RubyFixnum.three:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   124: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   127: dup            
        //   128: aload_1        
        //   129: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   132: aload_0        
        //   133: aload_1        
        //   134: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   137: ldc_w           35
        //   140: ldc_w           "disconnected"
        //   143: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   146: aload_1        
        //   147: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   150: invokestatic    org/jruby/RubyFixnum.four:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   153: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   156: dup            
        //   157: aload_1        
        //   158: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   161: aload_0        
        //   162: aload_1        
        //   163: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   166: ldc_w           36
        //   169: ldc_w           "associating"
        //   172: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   175: aload_1        
        //   176: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   179: invokestatic    org/jruby/RubyFixnum.five:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   182: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   185: dup            
        //   186: aload_1        
        //   187: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   190: aload_0        
        //   191: aload_1        
        //   192: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   195: ldc_w           37
        //   198: ldc_w           "discovering"
        //   201: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   204: aload_0        
        //   205: aload_1        
        //   206: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   209: bipush          6
        //   211: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   214: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   217: dup            
        //   218: aload_1        
        //   219: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   222: aload_0        
        //   223: aload_1        
        //   224: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   227: ldc_w           38
        //   230: ldc_w           "authenticating"
        //   233: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   236: aload_0        
        //   237: aload_1        
        //   238: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   241: bipush          7
        //   243: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum2:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //   246: invokevirtual   org/jruby/RubyHash.fastASetCheckString:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)V
        //   249: aload_1        
        //   250: ldc_w           "MAP"
        //   253: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   256: aload_1        
        //   257: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   260: goto            268
        //   263: aload_1        
        //   264: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   267: athrow         
        //   268: aload_1        
        //   269: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   272: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  28     260    263    268    Any
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
    
    public static IRubyObject module__6$RUBY$InterfaceState(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__6$RUBY$InterfaceState(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_7$RUBY$InterfaceInfo(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc_w           "InterfaceInfo"
        //    20: swap           
        //    21: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    24: dup            
        //    25: astore_2       
        //    26: aload_1        
        //    27: swap           
        //    28: aload_0        
        //    29: aload_1        
        //    30: ldc             ",0,0,-1"
        //    32: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getScope6:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    35: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    38: aload_0        
        //    39: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite6:()Lorg/jruby/runtime/CallSite;
        //    42: aload_1        
        //    43: aload_2        
        //    44: aload_2        
        //    45: bipush          6
        //    47: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: dup            
        //    51: iconst_0       
        //    52: aload_0        
        //    53: aload_1        
        //    54: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    57: ldc_w           39
        //    60: ldc_w           "InterfaceGuid"
        //    63: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    66: aastore        
        //    67: dup            
        //    68: iconst_1       
        //    69: aload_0        
        //    70: aload_1        
        //    71: ldc             "GUID"
        //    73: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: aastore        
        //    77: dup            
        //    78: iconst_2       
        //    79: aload_0        
        //    80: aload_1        
        //    81: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    84: ldc_w           40
        //    87: ldc_w           "strInterfaceDescription"
        //    90: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    93: aastore        
        //    94: dup            
        //    95: iconst_3       
        //    96: aload_1        
        //    97: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   100: aload_0        
        //   101: aload_1        
        //   102: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   105: ldc_w           41
        //   108: ldc_w           "uint16"
        //   111: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   114: aload_0        
        //   115: aload_1        
        //   116: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   119: bipush          18
        //   121: sipush          256
        //   124: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyFixnum;
        //   127: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   130: aastore        
        //   131: dup            
        //   132: iconst_4       
        //   133: aload_0        
        //   134: aload_1        
        //   135: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   138: ldc_w           42
        //   141: ldc_w           "isState"
        //   144: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   147: aastore        
        //   148: dup            
        //   149: iconst_5       
        //   150: aload_0        
        //   151: aload_1        
        //   152: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   155: ldc             "uint"
        //   157: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   160: aastore        
        //   161: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   164: pop            
        //   165: aload_0        
        //   166: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite7:()Lorg/jruby/runtime/CallSite;
        //   169: aload_1        
        //   170: aload_2        
        //   171: aload_2        
        //   172: aload_0        
        //   173: aload_1        
        //   174: ldc_w           "InterfaceState"
        //   177: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   180: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   183: pop            
        //   184: aload_1        
        //   185: aload_2        
        //   186: aload_0        
        //   187: ldc_w           "state"
        //   190: ldc_w           "method__8$RUBY$state"
        //   193: ldc             ",0,0,-1"
        //   195: iconst_0       
        //   196: ldc             "./lib//win32/wlan.rb"
        //   198: ldc_w           71
        //   201: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   204: ldc             "NONE"
        //   206: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   209: pop            
        //   210: aload_1        
        //   211: aload_2        
        //   212: aload_0        
        //   213: ldc_w           "description"
        //   216: ldc_w           "method__9$RUBY$description"
        //   219: ldc_w           "ws_str;bytes,0,0,-1"
        //   222: iconst_0       
        //   223: ldc             "./lib//win32/wlan.rb"
        //   225: ldc_w           75
        //   228: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   231: ldc             "NONE"
        //   233: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   236: aload_1        
        //   237: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   240: goto            248
        //   243: aload_1        
        //   244: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   247: athrow         
        //   248: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  38     236    243    248    Any
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
    
    @JRubyMethod(name = "state", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$state(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        IRubyObject rubyObject2;
        if (!(rubyObject2 = file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite8().call(context, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite9().call(context, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant5(context, "MAP")), file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(10).call(context, rubyObject, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol(context.runtime, 42, "isState")))).isTrue()) {
            rubyObject2 = file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(11).call(context, rubyObject, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol(context.runtime, 42, "isState"));
        }
        return rubyObject2;
    }
    
    @JRubyMethod(name = "description", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$description(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(12).call(threadContext, self, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(13).call(threadContext, self, self, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol(threadContext.runtime, 40, "strInterfaceDescription"))));
        locals.setValueOneDepthZero(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(14).callIter(threadContext, self, locals.getValueZeroDepthZeroOrNil(threadContext.nil), RuntimeHelpers.createBlock(threadContext, self, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody0(threadContext, "block_0$RUBY$description,1,i,false,2,./lib//win32/wlan.rb,77,true"))));
        return file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(16).call(threadContext, self, locals.getValueOneDepthZeroOrNil(threadContext.nil), file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getString4(threadContext.runtime, 32));
    }
    
    public static IRubyObject block_0$RUBY$description(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          15
        //    28: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           i
        //    35: ldc2_w          0
        //    38: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;J)Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     17      9     i     Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject class_7$RUBY$InterfaceInfo(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_7$RUBY$InterfaceInfo(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_10$RUBY$InterfaceInfoList(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc_w           "InterfaceInfoList"
        //    20: swap           
        //    21: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    24: dup            
        //    25: astore_2       
        //    26: aload_1        
        //    27: swap           
        //    28: aload_0        
        //    29: aload_1        
        //    30: ldc             ",0,0,-1"
        //    32: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getScope7:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    35: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    38: aload_0        
        //    39: bipush          17
        //    41: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    44: aload_1        
        //    45: aload_2        
        //    46: aload_2        
        //    47: bipush          6
        //    49: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: dup            
        //    53: iconst_0       
        //    54: aload_0        
        //    55: aload_1        
        //    56: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    59: ldc_w           43
        //    62: ldc_w           "dwNumberOfItems"
        //    65: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    68: aastore        
        //    69: dup            
        //    70: iconst_1       
        //    71: aload_0        
        //    72: aload_1        
        //    73: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    76: ldc             "uint"
        //    78: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    81: aastore        
        //    82: dup            
        //    83: iconst_2       
        //    84: aload_0        
        //    85: aload_1        
        //    86: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    89: ldc_w           44
        //    92: ldc_w           "dwIndex"
        //    95: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    98: aastore        
        //    99: dup            
        //   100: iconst_3       
        //   101: aload_0        
        //   102: aload_1        
        //   103: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   106: ldc             "uint"
        //   108: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   111: aastore        
        //   112: dup            
        //   113: iconst_4       
        //   114: aload_0        
        //   115: aload_1        
        //   116: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   119: ldc_w           45
        //   122: ldc_w           "InterfaceInfo"
        //   125: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   128: aastore        
        //   129: dup            
        //   130: iconst_5       
        //   131: aload_1        
        //   132: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   135: aload_0        
        //   136: aload_1        
        //   137: ldc_w           "InterfaceInfo"
        //   140: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant8:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   143: aload_0        
        //   144: aload_1        
        //   145: ldc_w           "ANY_SIZE"
        //   148: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant9:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   151: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   154: aastore        
        //   155: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   158: aload_1        
        //   159: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   162: goto            170
        //   165: aload_1        
        //   166: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   169: athrow         
        //   170: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  38     158    165    170    Any
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
    
    public static IRubyObject class_10$RUBY$InterfaceInfoList(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_10$RUBY$InterfaceInfoList(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_11$RUBY$Dot11SSID(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc_w           "Dot11SSID"
        //    20: swap           
        //    21: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    24: dup            
        //    25: astore_2       
        //    26: aload_1        
        //    27: swap           
        //    28: aload_0        
        //    29: aload_1        
        //    30: ldc             ",0,0,-1"
        //    32: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getScope8:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    35: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    38: aload_0        
        //    39: aload_1        
        //    40: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    43: bipush          19
        //    45: bipush          32
        //    47: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyFixnum;
        //    50: aload_1        
        //    51: ldc_w           "DOT11_SSID_MAX_LENGTH"
        //    54: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: pop            
        //    58: aload_0        
        //    59: bipush          18
        //    61: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    64: aload_1        
        //    65: aload_2        
        //    66: aload_2        
        //    67: aload_0        
        //    68: aload_1        
        //    69: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    72: ldc_w           46
        //    75: ldc_w           "uSSIDLength"
        //    78: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    81: aload_0        
        //    82: aload_1        
        //    83: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    86: ldc_w           47
        //    89: ldc_w           "ulong"
        //    92: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    95: aload_0        
        //    96: aload_1        
        //    97: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   100: ldc_w           48
        //   103: ldc_w           "ucSSID"
        //   106: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   109: aload_1        
        //   110: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   113: aload_0        
        //   114: aload_1        
        //   115: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   118: ldc_w           49
        //   121: ldc_w           "uchar"
        //   124: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   127: aload_0        
        //   128: aload_1        
        //   129: ldc_w           "DOT11_SSID_MAX_LENGTH"
        //   132: bipush          12
        //   134: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   137: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   140: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   143: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   146: pop            
        //   147: aload_1        
        //   148: aload_2        
        //   149: aload_0        
        //   150: ldc_w           "string"
        //   153: ldc_w           "method__12$RUBY$string"
        //   156: ldc             ",0,0,-1"
        //   158: iconst_0       
        //   159: ldc             "./lib//win32/wlan.rb"
        //   161: ldc_w           95
        //   164: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   167: ldc             "NONE"
        //   169: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   172: aload_1        
        //   173: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   176: goto            184
        //   179: aload_1        
        //   180: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   183: athrow         
        //   184: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  38     172    179    184    Any
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
    
    @JRubyMethod(name = "string", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__12$RUBY$string(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(19).call(threadContext, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(20).call(threadContext, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(21).call(threadContext, rubyObject, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol(threadContext.runtime, 48, "ucSSID"))));
    }
    
    public static IRubyObject class_11$RUBY$Dot11SSID(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_11$RUBY$Dot11SSID(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_13$RUBY$AvailableNetwork(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc_w           "AvailableNetwork"
        //    20: swap           
        //    21: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    24: dup            
        //    25: astore_2       
        //    26: aload_1        
        //    27: swap           
        //    28: aload_0        
        //    29: aload_1        
        //    30: ldc             ",0,0,-1"
        //    32: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getScope9:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    35: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    38: aload_0        
        //    39: aload_1        
        //    40: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    43: bipush          8
        //    45: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum0:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //    48: aload_1        
        //    49: ldc_w           "MAX_PHY_TYPE_NUMBER"
        //    52: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: pop            
        //    56: aload_0        
        //    57: bipush          22
        //    59: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    62: aload_1        
        //    63: aload_2        
        //    64: aload_2        
        //    65: bipush          30
        //    67: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: dup            
        //    71: iconst_0       
        //    72: aload_0        
        //    73: aload_1        
        //    74: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    77: ldc_w           50
        //    80: ldc_w           "strProfileName"
        //    83: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    86: aastore        
        //    87: dup            
        //    88: iconst_1       
        //    89: aload_1        
        //    90: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    93: aload_0        
        //    94: aload_1        
        //    95: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    98: ldc_w           41
        //   101: ldc_w           "uint16"
        //   104: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   107: aload_0        
        //   108: aload_1        
        //   109: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   112: bipush          18
        //   114: sipush          256
        //   117: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyFixnum;
        //   120: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   123: aastore        
        //   124: dup            
        //   125: iconst_2       
        //   126: aload_0        
        //   127: aload_1        
        //   128: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   131: ldc_w           51
        //   134: ldc_w           "dot11Ssid"
        //   137: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   140: aastore        
        //   141: dup            
        //   142: iconst_3       
        //   143: aload_0        
        //   144: aload_1        
        //   145: ldc_w           "Dot11SSID"
        //   148: bipush          15
        //   150: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   153: aastore        
        //   154: dup            
        //   155: iconst_4       
        //   156: aload_0        
        //   157: aload_1        
        //   158: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   161: ldc_w           52
        //   164: ldc_w           "dot11BssType"
        //   167: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   170: aastore        
        //   171: dup            
        //   172: iconst_5       
        //   173: aload_0        
        //   174: aload_1        
        //   175: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   178: ldc             "uint"
        //   180: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   183: aastore        
        //   184: dup            
        //   185: bipush          6
        //   187: aload_0        
        //   188: aload_1        
        //   189: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   192: ldc_w           53
        //   195: ldc_w           "uNumberOfBssids"
        //   198: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   201: aastore        
        //   202: dup            
        //   203: bipush          7
        //   205: aload_0        
        //   206: aload_1        
        //   207: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   210: ldc_w           47
        //   213: ldc_w           "ulong"
        //   216: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   219: aastore        
        //   220: dup            
        //   221: bipush          8
        //   223: aload_0        
        //   224: aload_1        
        //   225: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   228: ldc_w           54
        //   231: ldc_w           "bNetworkConnectable"
        //   234: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   237: aastore        
        //   238: dup            
        //   239: bipush          9
        //   241: aload_0        
        //   242: aload_1        
        //   243: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   246: ldc             "uint"
        //   248: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   251: aastore        
        //   252: dup            
        //   253: bipush          10
        //   255: aload_0        
        //   256: aload_1        
        //   257: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   260: ldc_w           55
        //   263: ldc_w           "wlanNotConnectableReason"
        //   266: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   269: aastore        
        //   270: dup            
        //   271: bipush          11
        //   273: aload_0        
        //   274: aload_1        
        //   275: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   278: ldc             "uint"
        //   280: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   283: aastore        
        //   284: dup            
        //   285: bipush          12
        //   287: aload_0        
        //   288: aload_1        
        //   289: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   292: ldc_w           56
        //   295: ldc_w           "uNumberOfPhyTypes"
        //   298: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   301: aastore        
        //   302: dup            
        //   303: bipush          13
        //   305: aload_0        
        //   306: aload_1        
        //   307: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   310: ldc_w           47
        //   313: ldc_w           "ulong"
        //   316: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   319: aastore        
        //   320: dup            
        //   321: bipush          14
        //   323: aload_0        
        //   324: aload_1        
        //   325: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   328: ldc_w           57
        //   331: ldc_w           "dot11PhyTypes"
        //   334: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   337: aastore        
        //   338: dup            
        //   339: bipush          15
        //   341: aload_1        
        //   342: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   345: aload_0        
        //   346: aload_1        
        //   347: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   350: ldc             "uint"
        //   352: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   355: aload_0        
        //   356: aload_1        
        //   357: ldc_w           "MAX_PHY_TYPE_NUMBER"
        //   360: bipush          16
        //   362: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   365: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   368: aastore        
        //   369: dup            
        //   370: bipush          16
        //   372: aload_0        
        //   373: aload_1        
        //   374: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   377: ldc_w           58
        //   380: ldc_w           "bMorePhyTypes"
        //   383: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   386: aastore        
        //   387: dup            
        //   388: bipush          17
        //   390: aload_0        
        //   391: aload_1        
        //   392: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   395: ldc             "uint"
        //   397: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   400: aastore        
        //   401: dup            
        //   402: bipush          18
        //   404: aload_0        
        //   405: aload_1        
        //   406: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   409: ldc_w           59
        //   412: ldc_w           "wlanSignalQuality"
        //   415: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   418: aastore        
        //   419: dup            
        //   420: bipush          19
        //   422: aload_0        
        //   423: aload_1        
        //   424: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   427: ldc_w           47
        //   430: ldc_w           "ulong"
        //   433: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   436: aastore        
        //   437: dup            
        //   438: bipush          20
        //   440: aload_0        
        //   441: aload_1        
        //   442: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   445: ldc_w           60
        //   448: ldc_w           "bSecurityEnabled"
        //   451: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   454: aastore        
        //   455: dup            
        //   456: bipush          21
        //   458: aload_0        
        //   459: aload_1        
        //   460: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   463: ldc             "uint"
        //   465: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   468: aastore        
        //   469: dup            
        //   470: bipush          22
        //   472: aload_0        
        //   473: aload_1        
        //   474: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   477: ldc_w           61
        //   480: ldc_w           "dot11DefaultAuthAlgorithm"
        //   483: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   486: aastore        
        //   487: dup            
        //   488: bipush          23
        //   490: aload_0        
        //   491: aload_1        
        //   492: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   495: ldc             "uint"
        //   497: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   500: aastore        
        //   501: dup            
        //   502: bipush          24
        //   504: aload_0        
        //   505: aload_1        
        //   506: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   509: ldc_w           62
        //   512: ldc_w           "dot11DefaultCipherAlgorithm"
        //   515: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   518: aastore        
        //   519: dup            
        //   520: bipush          25
        //   522: aload_0        
        //   523: aload_1        
        //   524: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   527: ldc             "uint"
        //   529: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   532: aastore        
        //   533: dup            
        //   534: bipush          26
        //   536: aload_0        
        //   537: aload_1        
        //   538: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   541: ldc_w           63
        //   544: ldc_w           "dwFlags"
        //   547: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   550: aastore        
        //   551: dup            
        //   552: bipush          27
        //   554: aload_0        
        //   555: aload_1        
        //   556: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   559: ldc             "uint"
        //   561: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   564: aastore        
        //   565: dup            
        //   566: bipush          28
        //   568: aload_0        
        //   569: aload_1        
        //   570: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   573: ldc_w           64
        //   576: ldc_w           "dwReserved"
        //   579: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   582: aastore        
        //   583: dup            
        //   584: bipush          29
        //   586: aload_0        
        //   587: aload_1        
        //   588: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   591: ldc             "uint"
        //   593: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   596: aastore        
        //   597: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   600: pop            
        //   601: aload_1        
        //   602: aload_2        
        //   603: aload_0        
        //   604: ldc_w           "profile_name"
        //   607: ldc_w           "method__14$RUBY$profile_name"
        //   610: ldc_w           "ws_str;bytes,0,0,-1"
        //   613: iconst_0       
        //   614: ldc             "./lib//win32/wlan.rb"
        //   616: ldc_w           119
        //   619: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   622: ldc             "NONE"
        //   624: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   627: pop            
        //   628: aload_1        
        //   629: aload_2        
        //   630: aload_0        
        //   631: ldc_w           "ssid"
        //   634: ldc_w           "method__15$RUBY$ssid"
        //   637: ldc_w           "dot11ssid,0,0,-1"
        //   640: iconst_0       
        //   641: ldc             "./lib//win32/wlan.rb"
        //   643: ldc_w           125
        //   646: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   649: ldc             "NONE"
        //   651: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   654: aload_1        
        //   655: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   658: goto            666
        //   661: aload_1        
        //   662: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   665: athrow         
        //   666: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  38     654    661    666    Any
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
    
    @JRubyMethod(name = "profile_name", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__14$RUBY$profile_name(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(23).call(threadContext, self, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(24).call(threadContext, self, self, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol(threadContext.runtime, 50, "strProfileName"))));
        locals.setValueOneDepthZero(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(25).callIter(threadContext, self, locals.getValueZeroDepthZeroOrNil(threadContext.nil), RuntimeHelpers.createBlock(threadContext, self, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody1(threadContext, "block_1$RUBY$profile_name,1,i,false,2,./lib//win32/wlan.rb,121,true"))));
        return file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(27).call(threadContext, self, locals.getValueOneDepthZeroOrNil(threadContext.nil), file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getString4(threadContext.runtime, 32));
    }
    
    public static IRubyObject block_1$RUBY$profile_name(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          26
        //    28: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           i
        //    35: ldc2_w          0
        //    38: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;J)Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     17      9     i     Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "ssid", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__15$RUBY$ssid(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(28).call(threadContext, rubyObject, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol(threadContext.runtime, 51, "dot11Ssid")));
        return file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(29).call(threadContext, rubyObject, locals.getValueZeroDepthZeroOrNil(threadContext.nil));
    }
    
    public static IRubyObject class_13$RUBY$AvailableNetwork(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_13$RUBY$AvailableNetwork(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_16$RUBY$AvailableNetworkList(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc_w           "AvailableNetworkList"
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
        //    34: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getScope:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/parser/StaticScope;
        //    37: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    40: aload_0        
        //    41: bipush          30
        //    43: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    46: aload_1        
        //    47: aload_2        
        //    48: aload_2        
        //    49: bipush          6
        //    51: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: dup            
        //    55: iconst_0       
        //    56: aload_0        
        //    57: aload_1        
        //    58: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    61: ldc_w           43
        //    64: ldc_w           "dwNumberOfItems"
        //    67: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    70: aastore        
        //    71: dup            
        //    72: iconst_1       
        //    73: aload_0        
        //    74: aload_1        
        //    75: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    78: ldc             "uint"
        //    80: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    83: aastore        
        //    84: dup            
        //    85: iconst_2       
        //    86: aload_0        
        //    87: aload_1        
        //    88: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    91: ldc_w           44
        //    94: ldc_w           "dwIndex"
        //    97: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   100: aastore        
        //   101: dup            
        //   102: iconst_3       
        //   103: aload_0        
        //   104: aload_1        
        //   105: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   108: ldc             "uint"
        //   110: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   113: aastore        
        //   114: dup            
        //   115: iconst_4       
        //   116: aload_0        
        //   117: aload_1        
        //   118: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   121: ldc_w           65
        //   124: ldc_w           "Network"
        //   127: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   130: aastore        
        //   131: dup            
        //   132: iconst_5       
        //   133: aload_1        
        //   134: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   137: aload_0        
        //   138: aload_1        
        //   139: ldc_w           "AvailableNetwork"
        //   142: bipush          19
        //   144: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   147: aload_0        
        //   148: aload_1        
        //   149: ldc_w           "ANY_SIZE"
        //   152: bipush          20
        //   154: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   157: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   160: aastore        
        //   161: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   164: aload_1        
        //   165: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   168: goto            176
        //   171: aload_1        
        //   172: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   175: athrow         
        //   176: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  40     164    171    176    Any
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
    
    public static IRubyObject class_16$RUBY$AvailableNetworkList(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_16$RUBY$AvailableNetworkList(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_17$RUBY$Dot11MacAddress(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc_w           "Dot11MacAddress"
        //    20: swap           
        //    21: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    24: dup            
        //    25: astore_2       
        //    26: aload_1        
        //    27: swap           
        //    28: aload_0        
        //    29: aload_1        
        //    30: ldc             ",0,0,-1"
        //    32: bipush          11
        //    34: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getScope:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/parser/StaticScope;
        //    37: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    40: aload_0        
        //    41: bipush          31
        //    43: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    46: aload_1        
        //    47: aload_2        
        //    48: aload_2        
        //    49: aload_0        
        //    50: aload_1        
        //    51: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    54: ldc_w           66
        //    57: ldc_w           "value"
        //    60: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    63: aload_1        
        //    64: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    67: aload_0        
        //    68: aload_1        
        //    69: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    72: ldc_w           49
        //    75: ldc_w           "uchar"
        //    78: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    81: aload_0        
        //    82: aload_1        
        //    83: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    86: bipush          6
        //    88: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //    91: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //    94: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    97: aload_1        
        //    98: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   101: goto            109
        //   104: aload_1        
        //   105: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   108: athrow         
        //   109: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  40     97     104    109    Any
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
    
    public static IRubyObject class_17$RUBY$Dot11MacAddress(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_17$RUBY$Dot11MacAddress(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_18$RUBY$WlanRateSet(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc_w           "WlanRateSet"
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
        //    34: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getScope:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/parser/StaticScope;
        //    37: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    40: aload_0        
        //    41: aload_1        
        //    42: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    45: bipush          20
        //    47: bipush          126
        //    49: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyFixnum;
        //    52: aload_1        
        //    53: ldc_w           "DOT11_RATE_SET_MAX_LENGTH"
        //    56: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    59: pop            
        //    60: aload_0        
        //    61: bipush          32
        //    63: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    66: aload_1        
        //    67: aload_2        
        //    68: aload_2        
        //    69: aload_0        
        //    70: aload_1        
        //    71: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    74: ldc_w           67
        //    77: ldc_w           "uRateSetLength"
        //    80: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    83: aload_0        
        //    84: aload_1        
        //    85: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    88: ldc             "ushort"
        //    90: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    93: aload_0        
        //    94: aload_1        
        //    95: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    98: ldc_w           68
        //   101: ldc_w           "usRateSet"
        //   104: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   107: aload_1        
        //   108: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   111: aload_0        
        //   112: aload_1        
        //   113: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   116: ldc             "ushort"
        //   118: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   121: aload_0        
        //   122: aload_1        
        //   123: ldc_w           "DOT11_RATE_SET_MAX_LENGTH"
        //   126: bipush          25
        //   128: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   131: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   134: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   137: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   140: aload_1        
        //   141: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   144: goto            152
        //   147: aload_1        
        //   148: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   151: athrow         
        //   152: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  40     140    147    152    Any
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
    
    public static IRubyObject class_18$RUBY$WlanRateSet(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_18$RUBY$WlanRateSet(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_19$RUBY$BSSEntry(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc_w           "BSSEntry"
        //    20: swap           
        //    21: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    24: dup            
        //    25: astore_2       
        //    26: aload_1        
        //    27: swap           
        //    28: aload_0        
        //    29: aload_1        
        //    30: ldc             ",0,0,-1"
        //    32: bipush          13
        //    34: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getScope:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/parser/StaticScope;
        //    37: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    40: aload_0        
        //    41: bipush          33
        //    43: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    46: aload_1        
        //    47: aload_2        
        //    48: aload_2        
        //    49: bipush          32
        //    51: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: dup            
        //    55: iconst_0       
        //    56: aload_0        
        //    57: aload_1        
        //    58: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    61: ldc_w           51
        //    64: ldc_w           "dot11Ssid"
        //    67: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    70: aastore        
        //    71: dup            
        //    72: iconst_1       
        //    73: aload_0        
        //    74: aload_1        
        //    75: ldc_w           "Dot11SSID"
        //    78: bipush          28
        //    80: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: aastore        
        //    84: dup            
        //    85: iconst_2       
        //    86: aload_0        
        //    87: aload_1        
        //    88: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    91: ldc_w           69
        //    94: ldc_w           "uPhyId"
        //    97: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   100: aastore        
        //   101: dup            
        //   102: iconst_3       
        //   103: aload_0        
        //   104: aload_1        
        //   105: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   108: ldc_w           47
        //   111: ldc_w           "ulong"
        //   114: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   117: aastore        
        //   118: dup            
        //   119: iconst_4       
        //   120: aload_0        
        //   121: aload_1        
        //   122: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   125: ldc_w           70
        //   128: ldc_w           "dot11Bss"
        //   131: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   134: aastore        
        //   135: dup            
        //   136: iconst_5       
        //   137: aload_0        
        //   138: aload_1        
        //   139: ldc_w           "Dot11MacAddress"
        //   142: bipush          29
        //   144: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   147: aastore        
        //   148: dup            
        //   149: bipush          6
        //   151: aload_0        
        //   152: aload_1        
        //   153: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   156: ldc_w           52
        //   159: ldc_w           "dot11BssType"
        //   162: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   165: aastore        
        //   166: dup            
        //   167: bipush          7
        //   169: aload_0        
        //   170: aload_1        
        //   171: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   174: ldc             "uint"
        //   176: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   179: aastore        
        //   180: dup            
        //   181: bipush          8
        //   183: aload_0        
        //   184: aload_1        
        //   185: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   188: ldc_w           71
        //   191: ldc_w           "dot11PhyType"
        //   194: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   197: aastore        
        //   198: dup            
        //   199: bipush          9
        //   201: aload_0        
        //   202: aload_1        
        //   203: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   206: ldc             "uint"
        //   208: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   211: aastore        
        //   212: dup            
        //   213: bipush          10
        //   215: aload_0        
        //   216: aload_1        
        //   217: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   220: ldc_w           72
        //   223: ldc_w           "lRssi"
        //   226: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   229: aastore        
        //   230: dup            
        //   231: bipush          11
        //   233: aload_0        
        //   234: aload_1        
        //   235: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   238: ldc_w           73
        //   241: ldc_w           "long"
        //   244: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   247: aastore        
        //   248: dup            
        //   249: bipush          12
        //   251: aload_0        
        //   252: aload_1        
        //   253: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   256: ldc_w           74
        //   259: ldc_w           "uLinkQuality"
        //   262: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   265: aastore        
        //   266: dup            
        //   267: bipush          13
        //   269: aload_0        
        //   270: aload_1        
        //   271: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   274: ldc_w           47
        //   277: ldc_w           "ulong"
        //   280: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   283: aastore        
        //   284: dup            
        //   285: bipush          14
        //   287: aload_0        
        //   288: aload_1        
        //   289: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   292: ldc_w           75
        //   295: ldc_w           "bInRegDomain"
        //   298: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   301: aastore        
        //   302: dup            
        //   303: bipush          15
        //   305: aload_0        
        //   306: aload_1        
        //   307: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   310: ldc_w           76
        //   313: ldc_w           "int"
        //   316: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   319: aastore        
        //   320: dup            
        //   321: bipush          16
        //   323: aload_0        
        //   324: aload_1        
        //   325: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   328: ldc_w           77
        //   331: ldc_w           "usBeaconPeriod"
        //   334: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   337: aastore        
        //   338: dup            
        //   339: bipush          17
        //   341: aload_0        
        //   342: aload_1        
        //   343: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   346: ldc             "ushort"
        //   348: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   351: aastore        
        //   352: dup            
        //   353: bipush          18
        //   355: aload_0        
        //   356: aload_1        
        //   357: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   360: ldc_w           78
        //   363: ldc_w           "ullTimestamp"
        //   366: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   369: aastore        
        //   370: dup            
        //   371: bipush          19
        //   373: aload_0        
        //   374: aload_1        
        //   375: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   378: ldc_w           79
        //   381: ldc_w           "ulong_long"
        //   384: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   387: aastore        
        //   388: dup            
        //   389: bipush          20
        //   391: aload_0        
        //   392: aload_1        
        //   393: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   396: ldc_w           80
        //   399: ldc_w           "ullHostTimestamp"
        //   402: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   405: aastore        
        //   406: dup            
        //   407: bipush          21
        //   409: aload_0        
        //   410: aload_1        
        //   411: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   414: ldc_w           79
        //   417: ldc_w           "ulong_long"
        //   420: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   423: aastore        
        //   424: dup            
        //   425: bipush          22
        //   427: aload_0        
        //   428: aload_1        
        //   429: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   432: ldc_w           81
        //   435: ldc_w           "usCapabilityInformation"
        //   438: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   441: aastore        
        //   442: dup            
        //   443: bipush          23
        //   445: aload_0        
        //   446: aload_1        
        //   447: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   450: ldc             "ushort"
        //   452: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   455: aastore        
        //   456: dup            
        //   457: bipush          24
        //   459: aload_0        
        //   460: aload_1        
        //   461: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   464: ldc_w           82
        //   467: ldc_w           "ulChCenterFrequency"
        //   470: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   473: aastore        
        //   474: dup            
        //   475: bipush          25
        //   477: aload_0        
        //   478: aload_1        
        //   479: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   482: ldc_w           47
        //   485: ldc_w           "ulong"
        //   488: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   491: aastore        
        //   492: dup            
        //   493: bipush          26
        //   495: aload_0        
        //   496: aload_1        
        //   497: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   500: ldc_w           83
        //   503: ldc_w           "wlanRateSet"
        //   506: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   509: aastore        
        //   510: dup            
        //   511: bipush          27
        //   513: aload_0        
        //   514: aload_1        
        //   515: ldc_w           "WlanRateSet"
        //   518: bipush          30
        //   520: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   523: aastore        
        //   524: dup            
        //   525: bipush          28
        //   527: aload_0        
        //   528: aload_1        
        //   529: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   532: ldc_w           84
        //   535: ldc_w           "ulIeOffset"
        //   538: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   541: aastore        
        //   542: dup            
        //   543: bipush          29
        //   545: aload_0        
        //   546: aload_1        
        //   547: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   550: ldc_w           47
        //   553: ldc_w           "ulong"
        //   556: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   559: aastore        
        //   560: dup            
        //   561: bipush          30
        //   563: aload_0        
        //   564: aload_1        
        //   565: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   568: ldc_w           85
        //   571: ldc_w           "ulIeSize"
        //   574: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   577: aastore        
        //   578: dup            
        //   579: bipush          31
        //   581: aload_0        
        //   582: aload_1        
        //   583: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   586: ldc_w           47
        //   589: ldc_w           "ulong"
        //   592: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   595: aastore        
        //   596: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   599: pop            
        //   600: aload_1        
        //   601: aload_2        
        //   602: aload_0        
        //   603: ldc_w           "infrastructure?"
        //   606: ldc_w           "method__20$RUBY$infrastructure_p_"
        //   609: ldc             ",0,0,-1"
        //   611: iconst_0       
        //   612: ldc             "./lib//win32/wlan.rb"
        //   614: ldc_w           165
        //   617: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   620: ldc             "NONE"
        //   622: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   625: pop            
        //   626: aload_1        
        //   627: aload_2        
        //   628: aload_0        
        //   629: ldc_w           "ad_hoc?"
        //   632: ldc_w           "method__21$RUBY$ad_hoc_p_"
        //   635: ldc             ",0,0,-1"
        //   637: iconst_0       
        //   638: ldc             "./lib//win32/wlan.rb"
        //   640: ldc_w           169
        //   643: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   646: ldc             "NONE"
        //   648: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   651: pop            
        //   652: aload_1        
        //   653: aload_2        
        //   654: aload_0        
        //   655: ldc_w           "mac_addr"
        //   658: ldc_w           "method__22$RUBY$mac_addr"
        //   661: ldc             ",0,0,-1"
        //   663: iconst_0       
        //   664: ldc             "./lib//win32/wlan.rb"
        //   666: ldc_w           173
        //   669: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   672: ldc             "NONE"
        //   674: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   677: pop            
        //   678: aload_1        
        //   679: aload_2        
        //   680: aload_0        
        //   681: ldc_w           "ssid"
        //   684: ldc_w           "method__23$RUBY$ssid"
        //   687: ldc_w           "dot11ssid,0,0,-1"
        //   690: iconst_0       
        //   691: ldc             "./lib//win32/wlan.rb"
        //   693: ldc_w           177
        //   696: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   699: ldc             "NONE"
        //   701: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   704: aload_1        
        //   705: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   708: goto            716
        //   711: aload_1        
        //   712: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   715: athrow         
        //   716: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  40     704    711    716    Any
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
    
    @JRubyMethod(name = "infrastructure?", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__20$RUBY$infrastructure_p_(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(34).call(threadContext, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(35).call(threadContext, rubyObject, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol(threadContext.runtime, 52, "dot11BssType")), 1L);
    }
    
    @JRubyMethod(name = "ad_hoc?", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__21$RUBY$ad_hoc_p_(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(36).call(threadContext, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(37).call(threadContext, rubyObject, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol(threadContext.runtime, 52, "dot11BssType")), 2L);
    }
    
    @JRubyMethod(name = "mac_addr", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__22$RUBY$mac_addr(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(38).call(threadContext, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(39).call(threadContext, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(40).call(threadContext, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(41).call(threadContext, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(42).call(threadContext, rubyObject, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol(threadContext.runtime, 70, "dot11Bss"))), file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum1(threadContext.runtime, 6)), file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(43).call(threadContext, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getString5(threadContext.runtime, 32), 6L)), file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getString6(threadContext.runtime, 32));
    }
    
    @JRubyMethod(name = "ssid", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__23$RUBY$ssid(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(44).call(threadContext, rubyObject, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol(threadContext.runtime, 51, "dot11Ssid")));
        return file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(45).call(threadContext, rubyObject, locals.getValueZeroDepthZeroOrNil(threadContext.nil));
    }
    
    public static IRubyObject class_19$RUBY$BSSEntry(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_19$RUBY$BSSEntry(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_24$RUBY$BSSList(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc_w           "BSSList"
        //    20: swap           
        //    21: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    24: dup            
        //    25: astore_2       
        //    26: aload_1        
        //    27: swap           
        //    28: aload_0        
        //    29: aload_1        
        //    30: ldc             ",0,0,-1"
        //    32: bipush          14
        //    34: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getScope:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/parser/StaticScope;
        //    37: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    40: aload_0        
        //    41: bipush          46
        //    43: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    46: aload_1        
        //    47: aload_2        
        //    48: aload_2        
        //    49: bipush          6
        //    51: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: dup            
        //    55: iconst_0       
        //    56: aload_0        
        //    57: aload_1        
        //    58: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    61: ldc_w           86
        //    64: ldc_w           "dwTotalSize"
        //    67: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    70: aastore        
        //    71: dup            
        //    72: iconst_1       
        //    73: aload_0        
        //    74: aload_1        
        //    75: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    78: ldc             "uint"
        //    80: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    83: aastore        
        //    84: dup            
        //    85: iconst_2       
        //    86: aload_0        
        //    87: aload_1        
        //    88: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    91: ldc_w           43
        //    94: ldc_w           "dwNumberOfItems"
        //    97: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   100: aastore        
        //   101: dup            
        //   102: iconst_3       
        //   103: aload_0        
        //   104: aload_1        
        //   105: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   108: ldc             "uint"
        //   110: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   113: aastore        
        //   114: dup            
        //   115: iconst_4       
        //   116: aload_0        
        //   117: aload_1        
        //   118: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   121: ldc_w           87
        //   124: ldc_w           "wlanBssEntries"
        //   127: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   130: aastore        
        //   131: dup            
        //   132: iconst_5       
        //   133: aload_1        
        //   134: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   137: aload_0        
        //   138: aload_1        
        //   139: ldc_w           "BSSEntry"
        //   142: bipush          33
        //   144: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   147: aload_1        
        //   148: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   151: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   154: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   157: aastore        
        //   158: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   161: aload_1        
        //   162: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   165: goto            173
        //   168: aload_1        
        //   169: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   172: athrow         
        //   173: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  40     161    168    173    Any
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
    
    public static IRubyObject class_24$RUBY$BSSList(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_24$RUBY$BSSList(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_25$RUBY$ConnectionAttributes(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc_w           "ConnectionAttributes"
        //    20: swap           
        //    21: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    24: dup            
        //    25: astore_2       
        //    26: aload_1        
        //    27: swap           
        //    28: aload_0        
        //    29: aload_1        
        //    30: ldc             ",0,0,-1"
        //    32: bipush          15
        //    34: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getScope:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/parser/StaticScope;
        //    37: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    40: aload_0        
        //    41: bipush          47
        //    43: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    46: aload_1        
        //    47: aload_2        
        //    48: aload_2        
        //    49: bipush          10
        //    51: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: dup            
        //    55: iconst_0       
        //    56: aload_0        
        //    57: aload_1        
        //    58: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    61: ldc_w           42
        //    64: ldc_w           "isState"
        //    67: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    70: aastore        
        //    71: dup            
        //    72: iconst_1       
        //    73: aload_0        
        //    74: aload_1        
        //    75: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    78: ldc             "uint"
        //    80: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    83: aastore        
        //    84: dup            
        //    85: iconst_2       
        //    86: aload_0        
        //    87: aload_1        
        //    88: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    91: ldc_w           88
        //    94: ldc_w           "wlanConnectionMode"
        //    97: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   100: aastore        
        //   101: dup            
        //   102: iconst_3       
        //   103: aload_0        
        //   104: aload_1        
        //   105: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   108: ldc             "uint"
        //   110: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   113: aastore        
        //   114: dup            
        //   115: iconst_4       
        //   116: aload_0        
        //   117: aload_1        
        //   118: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   121: ldc_w           50
        //   124: ldc_w           "strProfileName"
        //   127: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   130: aastore        
        //   131: dup            
        //   132: iconst_5       
        //   133: aload_1        
        //   134: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   137: aload_0        
        //   138: aload_1        
        //   139: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   142: ldc_w           49
        //   145: ldc_w           "uchar"
        //   148: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   151: aload_0        
        //   152: aload_1        
        //   153: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   156: bipush          18
        //   158: sipush          256
        //   161: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyFixnum;
        //   164: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   167: aastore        
        //   168: dup            
        //   169: bipush          6
        //   171: aload_0        
        //   172: aload_1        
        //   173: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   176: ldc_w           89
        //   179: ldc_w           "wlanAssociationAttributes"
        //   182: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   185: aastore        
        //   186: dup            
        //   187: bipush          7
        //   189: aload_0        
        //   190: aload_1        
        //   191: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   194: ldc             "uint"
        //   196: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   199: aastore        
        //   200: dup            
        //   201: bipush          8
        //   203: aload_0        
        //   204: aload_1        
        //   205: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   208: ldc_w           90
        //   211: ldc_w           "wlanSecurityAttributes"
        //   214: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   217: aastore        
        //   218: dup            
        //   219: bipush          9
        //   221: aload_0        
        //   222: aload_1        
        //   223: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   226: ldc             "uint"
        //   228: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   231: aastore        
        //   232: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   235: pop            
        //   236: aload_1        
        //   237: aload_2        
        //   238: aload_0        
        //   239: ldc_w           "profile_name"
        //   242: ldc_w           "method__26$RUBY$profile_name"
        //   245: ldc             ",0,0,-1"
        //   247: iconst_0       
        //   248: ldc             "./lib//win32/wlan.rb"
        //   250: ldc_w           196
        //   253: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   256: ldc             "NONE"
        //   258: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   261: aload_1        
        //   262: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   265: goto            273
        //   268: aload_1        
        //   269: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   272: athrow         
        //   273: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  40     261    268    273    Any
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
    
    @JRubyMethod(name = "profile_name", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__26$RUBY$profile_name(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(48).call(threadContext, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(49).call(threadContext, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(50).call(threadContext, rubyObject, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol(threadContext.runtime, 50, "strProfileName"))), file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum(threadContext.runtime, 18, 256));
    }
    
    public static IRubyObject class_25$RUBY$ConnectionAttributes(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_25$RUBY$ConnectionAttributes(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__3$RUBY$Wlan(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__3$RUBY$Wlan(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__2$RUBY$Win32(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__2$RUBY$Win32(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_27$RUBY$CouldNotOpenWlanError(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    10: ldc_w           "CouldNotOpenWlanError"
        //    13: swap           
        //    14: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    17: dup            
        //    18: astore_2       
        //    19: aload_1        
        //    20: swap           
        //    21: aload_0        
        //    22: aload_1        
        //    23: ldc             ",0,0,-1"
        //    25: bipush          16
        //    27: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getScope:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/parser/StaticScope;
        //    30: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    33: aload_1        
        //    34: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    37: aload_1        
        //    38: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    41: goto            49
        //    44: aload_1        
        //    45: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    48: athrow         
        //    49: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  33     37     44     49     Any
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
    
    public static IRubyObject class_27$RUBY$CouldNotOpenWlanError(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_27$RUBY$CouldNotOpenWlanError(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__28$RUBY$Win32(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: bipush          17
        //    23: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getScope:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/parser/StaticScope;
        //    26: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    29: aload_0        
        //    30: aload_1        
        //    31: aload_2        
        //    32: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    35: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    38: invokestatic    ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.module__29$RUBY$Wlan:(Lruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: aload_1        
        //    42: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: goto            53
        //    48: aload_1        
        //    49: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    52: athrow         
        //    53: aload_1        
        //    54: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    57: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  29     45     48     53     Any
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
    
    public static IRubyObject module__29$RUBY$Wlan(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Wlan"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: bipush          18
        //    23: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getScope:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/parser/StaticScope;
        //    26: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    29: aload_0        
        //    30: aload_1        
        //    31: aload_0        
        //    32: aload_1        
        //    33: ldc_w           "RuntimeError"
        //    36: bipush          40
        //    38: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    44: invokestatic    ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.class_30$RUBY$CouldNotOpenWlanError:(Lruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: pop            
        //    48: aload_1        
        //    49: aload_2        
        //    50: aload_0        
        //    51: ldc_w           "context"
        //    54: ldc_w           "method__31$RUBY$context"
        //    57: ldc_w           "p_negotiated_version;p_handle;err;handle,0,0,-1"
        //    60: iconst_0       
        //    61: ldc             "./lib//win32/wlan.rb"
        //    63: ldc_w           227
        //    66: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    69: ldc             "NONE"
        //    71: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: pop            
        //    75: aload_1        
        //    76: aload_2        
        //    77: aload_0        
        //    78: ldc_w           "each_interface"
        //    81: ldc_w           "method__33$RUBY$each_interface"
        //    84: ldc_w           "handle;pp_info_list;err;p_info_list;info_list,1,0,-1"
        //    87: iconst_1       
        //    88: ldc             "./lib//win32/wlan.rb"
        //    90: ldc_w           243
        //    93: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    96: ldc_w           "qhandle"
        //    99: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   102: pop            
        //   103: aload_1        
        //   104: aload_2        
        //   105: aload_0        
        //   106: ldc_w           "each_network_on_interface"
        //   109: ldc_w           "method__34$RUBY$each_network_on_interface"
        //   112: ldc_w           "handle;iface;p_guid;flags;pp_network_list;err;p_network_list;network_list;cnt,2,0,-1"
        //   115: iconst_2       
        //   116: ldc             "./lib//win32/wlan.rb"
        //   118: ldc_w           258
        //   121: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   124: ldc_w           "qhandle;qiface"
        //   127: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   130: pop            
        //   131: aload_1        
        //   132: aload_2        
        //   133: aload_0        
        //   134: ldc_w           "each_bssid_on_interface"
        //   137: ldc_w           "method__35$RUBY$each_bssid_on_interface"
        //   140: ldc_w           "handle;iface,2,0,-1"
        //   143: iconst_2       
        //   144: ldc             "./lib//win32/wlan.rb"
        //   146: ldc_w           278
        //   149: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   152: ldc_w           "qhandle;qiface"
        //   155: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   158: pop            
        //   159: aload_1        
        //   160: aload_2        
        //   161: aload_0        
        //   162: ldc_w           "each_bssid_on_interface_and_essid"
        //   165: ldc_w           "method__36$RUBY$each_bssid_on_interface_and_essid"
        //   168: ldc_w           "handle;iface;essid;essid_sec;p_guid;pp_wlan_bss_list;type;err;p_wlan_bss_list;wlan_bss_list;cnt,2,2,-1"
        //   171: bipush          -3
        //   173: ldc             "./lib//win32/wlan.rb"
        //   175: ldc_w           284
        //   178: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   181: ldc_w           "qhandle;qiface;oessid;oessid_sec"
        //   184: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   187: pop            
        //   188: aload_1        
        //   189: aload_2        
        //   190: aload_0        
        //   191: ldc_w           "each_network"
        //   194: ldc_w           "method__37$RUBY$each_network"
        //   197: ldc_w           "handle,1,0,-1"
        //   200: iconst_1       
        //   201: ldc             "./lib//win32/wlan.rb"
        //   203: ldc_w           307
        //   206: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   209: ldc_w           "qhandle"
        //   212: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   215: pop            
        //   216: aload_1        
        //   217: aload_2        
        //   218: aload_0        
        //   219: ldc_w           "each_bssid"
        //   222: ldc_w           "method__38$RUBY$each_bssid"
        //   225: ldc_w           "handle,1,0,-1"
        //   228: iconst_1       
        //   229: ldc             "./lib//win32/wlan.rb"
        //   231: ldc_w           315
        //   234: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   237: ldc_w           "qhandle"
        //   240: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   243: pop            
        //   244: aload_0        
        //   245: sipush          130
        //   248: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   251: aload_1        
        //   252: aload_2        
        //   253: aload_0        
        //   254: aload_1        
        //   255: ldc_w           "Struct"
        //   258: bipush          107
        //   260: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   263: aload_0        
        //   264: aload_1        
        //   265: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   268: ldc_w           104
        //   271: ldc_w           "ssid"
        //   274: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   277: aload_0        
        //   278: aload_1        
        //   279: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   282: ldc_w           105
        //   285: ldc_w           "bssids_count"
        //   288: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   291: aload_0        
        //   292: aload_1        
        //   293: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   296: ldc_w           106
        //   299: ldc_w           "quality"
        //   302: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   305: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   308: aload_1        
        //   309: ldc_w           "FoundNetwork"
        //   312: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   315: pop            
        //   316: aload_1        
        //   317: aload_2        
        //   318: aload_0        
        //   319: ldc_w           "scan_all"
        //   322: ldc_w           "method__39$RUBY$scan_all"
        //   325: ldc_w           "nets,0,0,-1"
        //   328: iconst_0       
        //   329: ldc             "./lib//win32/wlan.rb"
        //   331: ldc_w           325
        //   334: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   337: ldc             "NONE"
        //   339: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   342: pop            
        //   343: aload_1        
        //   344: aload_2        
        //   345: aload_0        
        //   346: ldc_w           "rescan_all"
        //   349: ldc_w           "method__41$RUBY$rescan_all"
        //   352: ldc_w           "handle,1,0,-1"
        //   355: iconst_1       
        //   356: ldc             "./lib//win32/wlan.rb"
        //   358: ldc_w           343
        //   361: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   364: ldc_w           "qhandle"
        //   367: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   370: pop            
        //   371: aload_1        
        //   372: aload_2        
        //   373: aload_0        
        //   374: ldc_w           "rescan"
        //   377: ldc_w           "method__42$RUBY$rescan"
        //   380: ldc_w           "handle;iface;p_guid,2,0,-1"
        //   383: iconst_2       
        //   384: ldc             "./lib//win32/wlan.rb"
        //   386: ldc_w           349
        //   389: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   392: ldc_w           "qhandle;qiface"
        //   395: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   398: pop            
        //   399: aload_1        
        //   400: aload_2        
        //   401: aload_0        
        //   402: ldc_w           "do_query_iface"
        //   405: ldc_w           "method__43$RUBY$do_query_iface"
        //   408: ldc_w           "handle;iface;code;p_size;pp_data;p_opcode;err,3,0,-1"
        //   411: iconst_3       
        //   412: ldc             "./lib//win32/wlan.rb"
        //   414: ldc_w           354
        //   417: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   420: ldc_w           "qhandle;qiface;qcode"
        //   423: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   426: pop            
        //   427: aload_1        
        //   428: aload_2        
        //   429: aload_0        
        //   430: ldc_w           "channel_number"
        //   433: ldc_w           "method__44$RUBY$channel_number"
        //   436: ldc_w           "handle;iface;code,2,0,-1"
        //   439: iconst_2       
        //   440: ldc             "./lib//win32/wlan.rb"
        //   442: ldc_w           370
        //   445: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   448: ldc_w           "qhandle;qiface"
        //   451: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   454: pop            
        //   455: aload_1        
        //   456: aload_2        
        //   457: aload_0        
        //   458: ldc_w           "current_rssi"
        //   461: ldc_w           "method__45$RUBY$current_rssi"
        //   464: ldc_w           "handle;iface;code,2,0,-1"
        //   467: iconst_2       
        //   468: ldc             "./lib//win32/wlan.rb"
        //   470: ldc_w           377
        //   473: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   476: ldc_w           "qhandle;qiface"
        //   479: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   482: pop            
        //   483: aload_1        
        //   484: aload_2        
        //   485: aload_0        
        //   486: ldc_w           "current_connection"
        //   489: ldc_w           "method__46$RUBY$current_connection"
        //   492: ldc_w           "handle;iface;code,2,0,-1"
        //   495: iconst_2       
        //   496: ldc             "./lib//win32/wlan.rb"
        //   498: ldc_w           384
        //   501: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   504: ldc_w           "qhandle;qiface"
        //   507: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   510: pop            
        //   511: aload_1        
        //   512: aload_2        
        //   513: aload_0        
        //   514: ldc_w           "current_profile"
        //   517: ldc_w           "method__47$RUBY$current_profile"
        //   520: ldc_w           "handle;iface,2,0,-1"
        //   523: iconst_2       
        //   524: ldc             "./lib//win32/wlan.rb"
        //   526: ldc_w           394
        //   529: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   532: ldc_w           "qhandle;qiface"
        //   535: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   538: pop            
        //   539: aload_1        
        //   540: aload_2        
        //   541: aload_0        
        //   542: ldc_w           "current_profile_as_xml_crap"
        //   545: ldc_w           "method__48$RUBY$current_profile_as_xml_crap"
        //   548: ldc_w           "handle;iface,2,0,-1"
        //   551: iconst_2       
        //   552: ldc             "./lib//win32/wlan.rb"
        //   554: ldc_w           401
        //   557: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   560: ldc_w           "qhandle;qiface"
        //   563: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   566: pop            
        //   567: aload_1        
        //   568: aload_2        
        //   569: aload_0        
        //   570: ldc_w           "get_status"
        //   573: ldc_w           "method__49$RUBY$get_status"
        //   576: ldc_w           "handle;pp_hostednetwork_status;err,1,0,-1"
        //   579: iconst_1       
        //   580: ldc             "./lib//win32/wlan.rb"
        //   582: ldc_w           427
        //   585: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   588: ldc_w           "qhandle"
        //   591: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   594: pop            
        //   595: aload_0        
        //   596: sipush          192
        //   599: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   602: aload_1        
        //   603: aload_2        
        //   604: aload_0        
        //   605: aload_1        
        //   606: ldc_w           "Struct"
        //   609: sipush          150
        //   612: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   615: aload_0        
        //   616: aload_1        
        //   617: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   620: ldc_w           107
        //   623: ldc_w           "essid"
        //   626: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   629: aload_0        
        //   630: aload_1        
        //   631: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   634: ldc_w           108
        //   637: ldc_w           "bssid"
        //   640: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   643: aload_0        
        //   644: aload_1        
        //   645: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   648: ldc_w           25
        //   651: ldc_w           "rssi"
        //   654: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   657: aload_0        
        //   658: aload_1        
        //   659: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   662: ldc_w           109
        //   665: ldc_w           "channel"
        //   668: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   671: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   674: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   677: aload_1        
        //   678: ldc_w           "Status"
        //   681: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   684: pop            
        //   685: aload_1        
        //   686: aload_2        
        //   687: aload_0        
        //   688: ldc_w           "current_infos"
        //   691: ldc_w           "method__50$RUBY$current_infos"
        //   694: ldc             ",0,0,-1"
        //   696: iconst_0       
        //   697: ldc             "./lib//win32/wlan.rb"
        //   699: ldc_w           439
        //   702: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   705: ldc             "NONE"
        //   707: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   710: pop            
        //   711: aload_0        
        //   712: sipush          209
        //   715: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   718: aload_1        
        //   719: aload_2        
        //   720: aload_0        
        //   721: aload_1        
        //   722: ldc_w           "Struct"
        //   725: sipush          154
        //   728: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   731: aload_0        
        //   732: aload_1        
        //   733: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   736: ldc_w           104
        //   739: ldc_w           "ssid"
        //   742: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   745: aload_0        
        //   746: aload_1        
        //   747: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   750: ldc_w           108
        //   753: ldc_w           "bssid"
        //   756: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   759: aload_0        
        //   760: aload_1        
        //   761: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   764: ldc_w           25
        //   767: ldc_w           "rssi"
        //   770: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   773: aload_0        
        //   774: aload_1        
        //   775: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   778: ldc_w           110
        //   781: ldc_w           "freq"
        //   784: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   787: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   790: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   793: aload_1        
        //   794: ldc_w           "Signal"
        //   797: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   800: pop            
        //   801: aload_1        
        //   802: aload_2        
        //   803: aload_0        
        //   804: ldc_w           "deep_scan_all"
        //   807: ldc_w           "method__52$RUBY$deep_scan_all"
        //   810: ldc             ",0,0,-1"
        //   812: iconst_0       
        //   813: ldc             "./lib//win32/wlan.rb"
        //   815: ldc_w           468
        //   818: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   821: ldc             "NONE"
        //   823: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   826: pop            
        //   827: aload_0        
        //   828: sipush          219
        //   831: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   834: aload_1        
        //   835: aload_2        
        //   836: aload_2        
        //   837: aload_2        
        //   838: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   841: aload_1        
        //   842: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   845: goto            853
        //   848: aload_1        
        //   849: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   852: athrow         
        //   853: aload_1        
        //   854: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   857: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  29     845    848    853    Any
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
    
    public static IRubyObject class_30$RUBY$CouldNotOpenWlanError(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc_w           "CouldNotOpenWlanError"
        //    20: swap           
        //    21: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    24: dup            
        //    25: astore_2       
        //    26: aload_1        
        //    27: swap           
        //    28: aload_0        
        //    29: aload_1        
        //    30: ldc             ",0,0,-1"
        //    32: bipush          19
        //    34: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getScope:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/parser/StaticScope;
        //    37: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    40: aload_1        
        //    41: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    44: aload_1        
        //    45: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    48: goto            56
        //    51: aload_1        
        //    52: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    55: athrow         
        //    56: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  40     44     51     56     Any
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
    
    public static IRubyObject class_30$RUBY$CouldNotOpenWlanError(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_30$RUBY$CouldNotOpenWlanError(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, threadContext, rubyObject, block);
    }
    
    @JRubyMethod(name = "context", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__31$RUBY$context(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        final DynamicScope locals = context.getCurrentScope();
        locals.setValueZeroDepthZero(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(63).call(context, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom(RuntimeHelpers.checkIsModule(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant(context, "FFI", 41)), context, "MemoryPointer", 42), file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0(context.runtime, "uint")));
        locals.setValueOneDepthZero(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(64).call(context, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom(RuntimeHelpers.checkIsModule(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant(context, "FFI", 43)), context, "MemoryPointer", 44), RuntimeHelpers.getGlobalVariable(context.runtime, "$HANDLE_TYPE")));
        locals.setValueTwoDepthZero(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(65).call(context, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom(RuntimeHelpers.checkIsModule(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant(context, "Win32", 45)), context, "Wlan", 46), RuntimeHelpers.constructObjectArray(RubyFixnum.two(context.runtime), context.nil, locals.getValueZeroDepthZeroOrNil(context.nil), locals.getValueOneDepthZeroOrNil(context.nil))));
        if (!file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(66).call(context, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol(context.runtime, 103, "SUCCESS"), file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(67).call(context, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom(RuntimeHelpers.checkIsModule(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant(context, "Win32", 47)), context, "Errors", 48), locals.getValueTwoDepthZeroOrNil(context.nil))).isTrue()) {
            file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(68).call(context, rubyObject, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant(context, "CouldNotOpenWlanError", 49), RubyString.newStringLight(context.runtime, 20).append(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getString8(context.runtime, 32)).append(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(69).call(context, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom(RuntimeHelpers.checkIsModule(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant(context, "Win32", 50)), context, "Errors", 51), locals.getValueTwoDepthZeroOrNil(context.nil)).asString()).append(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getString9(context.runtime, 32)).append(locals.getValueTwoDepthZeroOrNil(context.nil).asString()).append(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getString(context.runtime, 10, 32)));
        }
        locals.setValueThreeDepthZero(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(70).call(context, rubyObject, locals.getValueOneDepthZeroOrNil(context.nil)));
        return chained_32_ensure_1$RUBY$__ensure__(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, context, rubyObject, block);
    }
    
    public static IRubyObject chained_32_ensure_1$RUBY$__ensure__(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        final DynamicScope currentScope = context.getCurrentScope();
        try {
            final IRubyObject yield = block.yield(context, currentScope.getValueThreeDepthZeroOrNil(context.nil));
            file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(71).call(context, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom(RuntimeHelpers.checkIsModule(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant(context, "Win32", 52)), context, "Wlan", 53), currentScope.getValueThreeDepthZeroOrNil(context.nil), context.nil);
            return yield;
        }
        finally {
            file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(72).call(context, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom(RuntimeHelpers.checkIsModule(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant(context, "Win32", 54)), context, "Wlan", 55), currentScope.getValueThreeDepthZeroOrNil(context.nil), context.nil);
        }
    }
    
    @JRubyMethod(name = "each_interface", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__33$RUBY$each_interface(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    16: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    19: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.fillNil:([Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;)V
        //    22: aload_3        
        //    23: aload           5
        //    25: swap           
        //    26: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    29: pop            
        //    30: aload           locals
        //    32: aload_0        
        //    33: bipush          73
        //    35: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    38: aload_1        
        //    39: aload_2        
        //    40: aload_0        
        //    41: aload_1        
        //    42: ldc             "FFI"
        //    44: bipush          56
        //    46: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    52: aload_0        
        //    53: swap           
        //    54: aload_1        
        //    55: ldc             "MemoryPointer"
        //    57: bipush          57
        //    59: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    62: aload_0        
        //    63: aload_1        
        //    64: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    67: ldc_w           94
        //    70: ldc_w           "pointer"
        //    73: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    76: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: pop            
        //    83: aload           locals
        //    85: aload_0        
        //    86: bipush          74
        //    88: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    91: aload_1        
        //    92: aload_2        
        //    93: aload_0        
        //    94: aload_1        
        //    95: ldc             "Win32"
        //    97: bipush          58
        //    99: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   102: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   105: aload_0        
        //   106: swap           
        //   107: aload_1        
        //   108: ldc             "Wlan"
        //   110: bipush          59
        //   112: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   115: aload           locals
        //   117: aload_1        
        //   118: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   121: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   124: aload_1        
        //   125: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   128: aload           locals
        //   130: aload_1        
        //   131: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   134: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   137: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   140: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   143: pop            
        //   144: aload_0        
        //   145: bipush          75
        //   147: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   150: aload_1        
        //   151: aload_2        
        //   152: aload_0        
        //   153: aload_1        
        //   154: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   157: ldc_w           103
        //   160: ldc_w           "SUCCESS"
        //   163: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   166: aload_0        
        //   167: bipush          76
        //   169: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   172: aload_1        
        //   173: aload_2        
        //   174: aload_0        
        //   175: aload_1        
        //   176: ldc             "Win32"
        //   178: bipush          60
        //   180: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   183: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   186: aload_0        
        //   187: swap           
        //   188: aload_1        
        //   189: ldc_w           "Errors"
        //   192: bipush          61
        //   194: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   197: aload           locals
        //   199: aload_1        
        //   200: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   203: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   206: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   209: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   212: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   217: ifeq            362
        //   220: aload           locals
        //   222: aload_0        
        //   223: bipush          77
        //   225: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   228: aload_1        
        //   229: aload_2        
        //   230: aload           locals
        //   232: aload_1        
        //   233: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   236: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   239: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   242: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   245: pop            
        //   246: aload           6
        //   248: iconst_4       
        //   249: aload_0        
        //   250: bipush          78
        //   252: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   255: aload_1        
        //   256: aload_2        
        //   257: aload_0        
        //   258: aload_1        
        //   259: ldc             "Win32"
        //   261: bipush          62
        //   263: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   266: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   269: aload_0        
        //   270: swap           
        //   271: aload_1        
        //   272: ldc             "Wlan"
        //   274: bipush          63
        //   276: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   279: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   282: aload_0        
        //   283: swap           
        //   284: aload_1        
        //   285: ldc_w           "InterfaceInfoList"
        //   288: bipush          64
        //   290: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   293: aload           locals
        //   295: aload_1        
        //   296: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   299: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   302: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   305: aastore        
        //   306: aload_0        
        //   307: bipush          79
        //   309: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   312: aload_1        
        //   313: aload_2        
        //   314: aload_0        
        //   315: bipush          80
        //   317: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   320: aload_1        
        //   321: aload_2        
        //   322: aload           6
        //   324: iconst_4       
        //   325: aaload         
        //   326: aload_0        
        //   327: aload_1        
        //   328: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   331: ldc_w           43
        //   334: ldc_w           "dwNumberOfItems"
        //   337: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   340: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   343: aload_1        
        //   344: aload_2        
        //   345: aload_0        
        //   346: aload_1        
        //   347: ldc_w           "block_2$RUBY$each_interface,1,idx;p_info;info,false,2,./lib//win32/wlan.rb,249,false"
        //   350: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   353: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   356: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   359: goto            366
        //   362: aload_1        
        //   363: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   366: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  30     337     5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_2$RUBY$each_interface(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    22: aload           5
        //    24: swap           
        //    25: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    28: pop            
        //    29: aload_1        
        //    30: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    33: aload           4
        //    35: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    38: aload_3        
        //    39: aload           5
        //    41: swap           
        //    42: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: pop            
        //    46: pop            
        //    47: aload           locals
        //    49: aload_0        
        //    50: bipush          81
        //    52: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    55: aload_1        
        //    56: aload_2        
        //    57: aload_0        
        //    58: aload_1        
        //    59: ldc             "FFI"
        //    61: bipush          65
        //    63: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    69: aload_0        
        //    70: swap           
        //    71: aload_1        
        //    72: ldc_w           "Pointer"
        //    75: bipush          66
        //    77: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: aload_0        
        //    81: bipush          82
        //    83: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    86: aload_1        
        //    87: aload_2        
        //    88: aload_0        
        //    89: bipush          83
        //    91: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    94: aload_1        
        //    95: aload_2        
        //    96: aload_0        
        //    97: bipush          84
        //    99: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   102: aload_1        
        //   103: aload_2        
        //   104: aload_0        
        //   105: bipush          85
        //   107: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   110: aload_1        
        //   111: aload_2        
        //   112: aload           locals
        //   114: iconst_4       
        //   115: iconst_1       
        //   116: aload_1        
        //   117: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   120: invokevirtual   org/jruby/runtime/DynamicScope.getValueOrNil:(IILorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   123: aload_0        
        //   124: aload_1        
        //   125: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   128: ldc_w           45
        //   131: ldc_w           "InterfaceInfo"
        //   134: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   137: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   140: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   143: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   146: aload_0        
        //   147: bipush          86
        //   149: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   152: aload_1        
        //   153: aload_2        
        //   154: aload_0        
        //   155: bipush          87
        //   157: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   160: aload_1        
        //   161: aload_2        
        //   162: aload_0        
        //   163: aload_1        
        //   164: ldc             "Win32"
        //   166: bipush          67
        //   168: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   171: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   174: aload_0        
        //   175: swap           
        //   176: aload_1        
        //   177: ldc             "Wlan"
        //   179: bipush          68
        //   181: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   184: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   187: aload_0        
        //   188: swap           
        //   189: aload_1        
        //   190: ldc_w           "InterfaceInfo"
        //   193: bipush          69
        //   195: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   198: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   201: aload           locals
        //   203: aload_1        
        //   204: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   207: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   210: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   213: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   216: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   219: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   222: pop            
        //   223: aload           locals
        //   225: aload_0        
        //   226: bipush          88
        //   228: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   231: aload_1        
        //   232: aload_2        
        //   233: aload_0        
        //   234: aload_1        
        //   235: ldc             "Win32"
        //   237: bipush          70
        //   239: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   242: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   245: aload_0        
        //   246: swap           
        //   247: aload_1        
        //   248: ldc             "Wlan"
        //   250: bipush          71
        //   252: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   255: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   258: aload_0        
        //   259: swap           
        //   260: aload_1        
        //   261: ldc_w           "InterfaceInfo"
        //   264: bipush          72
        //   266: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   269: aload           locals
        //   271: aload_1        
        //   272: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   275: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   278: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   281: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   284: pop            
        //   285: aload_1        
        //   286: invokevirtual   org/jruby/runtime/ThreadContext.getFrameBlock:()Lorg/jruby/runtime/Block;
        //   289: aload_1        
        //   290: aload           locals
        //   292: aload_1        
        //   293: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   296: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   299: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   302: areturn        
        //   303: pop            
        //   304: goto            47
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  47     256     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  47     303    303    307    Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    @JRubyMethod(name = "each_network_on_interface", frame = true, required = 2, optional = 0, rest = -1)
    public static IRubyObject method__34$RUBY$each_network_on_interface(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final Block p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: astore          6
        //     6: aload           6
        //     8: invokevirtual   org/jruby/runtime/DynamicScope.getValues:()[Lorg/jruby/runtime/builtin/IRubyObject;
        //    11: astore          7
        //    13: aload           7
        //    15: aload_1        
        //    16: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    19: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.fillNil:([Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;)V
        //    22: aload_3        
        //    23: aload           6
        //    25: swap           
        //    26: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    29: pop            
        //    30: aload           4
        //    32: aload           6
        //    34: swap           
        //    35: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    38: pop            
        //    39: aload           locals
        //    41: aload_0        
        //    42: bipush          89
        //    44: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    47: aload_1        
        //    48: aload_2        
        //    49: aload_0        
        //    50: bipush          90
        //    52: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    55: aload_1        
        //    56: aload_2        
        //    57: aload           locals
        //    59: aload_1        
        //    60: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: aload_0        
        //    67: aload_1        
        //    68: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    71: ldc_w           39
        //    74: ldc_w           "InterfaceGuid"
        //    77: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    80: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    86: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: pop            
        //    90: aload           locals
        //    92: aload_1        
        //    93: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    96: invokestatic    org/jruby/RubyFixnum.three:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    99: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   102: pop            
        //   103: aload           7
        //   105: iconst_4       
        //   106: aload_0        
        //   107: bipush          91
        //   109: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   112: aload_1        
        //   113: aload_2        
        //   114: aload_0        
        //   115: aload_1        
        //   116: ldc             "FFI"
        //   118: bipush          73
        //   120: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   123: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   126: aload_0        
        //   127: swap           
        //   128: aload_1        
        //   129: ldc             "MemoryPointer"
        //   131: bipush          74
        //   133: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   136: aload_0        
        //   137: aload_1        
        //   138: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   141: ldc_w           94
        //   144: ldc_w           "pointer"
        //   147: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   150: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   153: aastore        
        //   154: aload           7
        //   156: iconst_5       
        //   157: aload_0        
        //   158: bipush          92
        //   160: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   163: aload_1        
        //   164: aload_2        
        //   165: aload_0        
        //   166: aload_1        
        //   167: ldc             "Win32"
        //   169: bipush          75
        //   171: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   174: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   177: aload_0        
        //   178: swap           
        //   179: aload_1        
        //   180: ldc             "Wlan"
        //   182: bipush          76
        //   184: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   187: aload           locals
        //   189: aload_1        
        //   190: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   193: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   196: aload           locals
        //   198: aload_1        
        //   199: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   202: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   205: aload           locals
        //   207: aload_1        
        //   208: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   211: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   214: aload_1        
        //   215: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   218: aload           7
        //   220: iconst_4       
        //   221: aaload         
        //   222: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   225: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   228: aastore        
        //   229: aload_0        
        //   230: bipush          93
        //   232: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   235: aload_1        
        //   236: aload_2        
        //   237: aload_0        
        //   238: aload_1        
        //   239: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   242: ldc_w           103
        //   245: ldc_w           "SUCCESS"
        //   248: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   251: aload_0        
        //   252: bipush          94
        //   254: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   257: aload_1        
        //   258: aload_2        
        //   259: aload_0        
        //   260: aload_1        
        //   261: ldc             "Win32"
        //   263: bipush          77
        //   265: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   268: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   271: aload_0        
        //   272: swap           
        //   273: aload_1        
        //   274: ldc_w           "Errors"
        //   277: bipush          78
        //   279: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   282: aload           7
        //   284: iconst_5       
        //   285: aaload         
        //   286: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   289: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   292: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   297: ifeq            444
        //   300: aload           7
        //   302: bipush          6
        //   304: aload_0        
        //   305: bipush          95
        //   307: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   310: aload_1        
        //   311: aload_2        
        //   312: aload           7
        //   314: iconst_4       
        //   315: aaload         
        //   316: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   319: aastore        
        //   320: aload           7
        //   322: bipush          7
        //   324: aload_0        
        //   325: bipush          96
        //   327: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   330: aload_1        
        //   331: aload_2        
        //   332: aload_0        
        //   333: aload_1        
        //   334: ldc             "Win32"
        //   336: bipush          79
        //   338: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   341: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   344: aload_0        
        //   345: swap           
        //   346: aload_1        
        //   347: ldc             "Wlan"
        //   349: bipush          80
        //   351: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   354: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   357: aload_0        
        //   358: swap           
        //   359: aload_1        
        //   360: ldc_w           "AvailableNetworkList"
        //   363: bipush          81
        //   365: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   368: aload           7
        //   370: bipush          6
        //   372: aaload         
        //   373: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   376: aastore        
        //   377: aload           7
        //   379: bipush          8
        //   381: aload_0        
        //   382: bipush          97
        //   384: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   387: aload_1        
        //   388: aload_2        
        //   389: aload           7
        //   391: bipush          7
        //   393: aaload         
        //   394: aload_0        
        //   395: aload_1        
        //   396: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   399: ldc_w           43
        //   402: ldc_w           "dwNumberOfItems"
        //   405: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   408: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   411: aastore        
        //   412: aload_0        
        //   413: bipush          98
        //   415: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   418: aload_1        
        //   419: aload_2        
        //   420: aload           7
        //   422: bipush          8
        //   424: aaload         
        //   425: aload_1        
        //   426: aload_2        
        //   427: aload_0        
        //   428: aload_1        
        //   429: ldc_w           "block_3$RUBY$each_network_on_interface,1,jdx;p_network;network,false,2,./lib//win32/wlan.rb,269,false"
        //   432: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   435: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   438: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   441: goto            448
        //   444: aload_1        
        //   445: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   448: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  39     410     6     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_3$RUBY$each_network_on_interface(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    22: aload           5
        //    24: swap           
        //    25: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    28: pop            
        //    29: aload_1        
        //    30: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    33: aload           4
        //    35: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    38: aload_3        
        //    39: aload           5
        //    41: swap           
        //    42: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: pop            
        //    46: pop            
        //    47: aload           locals
        //    49: aload_0        
        //    50: bipush          99
        //    52: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    55: aload_1        
        //    56: aload_2        
        //    57: aload_0        
        //    58: aload_1        
        //    59: ldc             "FFI"
        //    61: bipush          82
        //    63: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    69: aload_0        
        //    70: swap           
        //    71: aload_1        
        //    72: ldc_w           "Pointer"
        //    75: bipush          83
        //    77: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: aload_0        
        //    81: bipush          100
        //    83: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    86: aload_1        
        //    87: aload_2        
        //    88: aload_0        
        //    89: bipush          101
        //    91: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    94: aload_1        
        //    95: aload_2        
        //    96: aload_0        
        //    97: bipush          102
        //    99: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   102: aload_1        
        //   103: aload_2        
        //   104: aload_0        
        //   105: bipush          103
        //   107: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   110: aload_1        
        //   111: aload_2        
        //   112: aload           locals
        //   114: bipush          7
        //   116: iconst_1       
        //   117: aload_1        
        //   118: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   121: invokevirtual   org/jruby/runtime/DynamicScope.getValueOrNil:(IILorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   124: aload_0        
        //   125: aload_1        
        //   126: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   129: ldc_w           65
        //   132: ldc_w           "Network"
        //   135: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   138: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   141: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   144: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   147: aload_0        
        //   148: bipush          104
        //   150: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   153: aload_1        
        //   154: aload_2        
        //   155: aload_0        
        //   156: bipush          105
        //   158: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   161: aload_1        
        //   162: aload_2        
        //   163: aload_0        
        //   164: aload_1        
        //   165: ldc             "Win32"
        //   167: bipush          84
        //   169: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   172: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   175: aload_0        
        //   176: swap           
        //   177: aload_1        
        //   178: ldc             "Wlan"
        //   180: bipush          85
        //   182: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   185: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   188: aload_0        
        //   189: swap           
        //   190: aload_1        
        //   191: ldc_w           "AvailableNetwork"
        //   194: bipush          86
        //   196: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   199: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   202: aload           locals
        //   204: aload_1        
        //   205: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   208: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   211: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   214: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   217: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   220: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   223: pop            
        //   224: aload           locals
        //   226: aload_0        
        //   227: bipush          106
        //   229: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   232: aload_1        
        //   233: aload_2        
        //   234: aload_0        
        //   235: aload_1        
        //   236: ldc             "Win32"
        //   238: bipush          87
        //   240: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   243: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   246: aload_0        
        //   247: swap           
        //   248: aload_1        
        //   249: ldc             "Wlan"
        //   251: bipush          88
        //   253: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   256: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   259: aload_0        
        //   260: swap           
        //   261: aload_1        
        //   262: ldc_w           "AvailableNetwork"
        //   265: bipush          89
        //   267: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   270: aload           locals
        //   272: aload_1        
        //   273: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   276: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   279: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   282: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   285: pop            
        //   286: aload_1        
        //   287: invokevirtual   org/jruby/runtime/ThreadContext.getFrameBlock:()Lorg/jruby/runtime/Block;
        //   290: aload_1        
        //   291: aload           locals
        //   293: aload_1        
        //   294: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   297: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   300: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   303: areturn        
        //   304: pop            
        //   305: goto            47
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  47     257     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  47     304    304    308    Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    @JRubyMethod(name = "each_bssid_on_interface", frame = true, required = 2, optional = 0, rest = -1)
    public static IRubyObject method__35$RUBY$each_bssid_on_interface(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final Block p5) {
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
        //    24: bipush          107
        //    26: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    29: aload_1        
        //    30: aload_2        
        //    31: aload_2        
        //    32: aload           locals
        //    34: aload_1        
        //    35: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    38: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: aload           locals
        //    43: aload_1        
        //    44: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: aload_1        
        //    51: aload_2        
        //    52: aload_0        
        //    53: aload_1        
        //    54: ldc_w           "block_4$RUBY$each_bssid_on_interface,1,n,false,2,./lib//win32/wlan.rb,279,true"
        //    57: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    60: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    63: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  23     44      6     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_4$RUBY$each_bssid_on_interface(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    25: aload_1        
        //    26: invokevirtual   org/jruby/runtime/ThreadContext.getFrameBlock:()Lorg/jruby/runtime/Block;
        //    29: aload_1        
        //    30: aload           n
        //    32: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    35: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     11      9     n     Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "each_bssid_on_interface_and_essid", frame = true, required = 2, optional = 2, rest = -1)
    public static IRubyObject method__36$RUBY$each_bssid_on_interface_and_essid(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
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
        //    16: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    19: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.fillNil:([Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;)V
        //    22: aload_1        
        //    23: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    26: aload_3        
        //    27: iconst_2       
        //    28: iconst_4       
        //    29: invokestatic    org/jruby/runtime/Arity.raiseArgumentError:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;II)V
        //    32: aload_3        
        //    33: iconst_0       
        //    34: aaload         
        //    35: aload           5
        //    37: swap           
        //    38: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: pop            
        //    42: aload_3        
        //    43: iconst_1       
        //    44: aaload         
        //    45: aload           5
        //    47: swap           
        //    48: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: pop            
        //    52: aload_3        
        //    53: iconst_2       
        //    54: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.elementOrNull:([Lorg/jruby/runtime/builtin/IRubyObject;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: dup            
        //    58: ifnull          87
        //    61: aload           5
        //    63: swap           
        //    64: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: pop            
        //    68: aload_3        
        //    69: iconst_3       
        //    70: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.elementOrNull:([Lorg/jruby/runtime/builtin/IRubyObject;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: dup            
        //    74: ifnull          97
        //    77: aload           5
        //    79: swap           
        //    80: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: pop            
        //    84: goto            115
        //    87: aload           5
        //    89: aload_1        
        //    90: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    93: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    96: pop            
        //    97: aload           5
        //    99: aload_1        
        //   100: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   103: invokevirtual   org/jruby/Ruby.getFalse:()Lorg/jruby/RubyBoolean;
        //   106: aload_1        
        //   107: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //   110: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   113: pop            
        //   114: pop            
        //   115: aload           6
        //   117: iconst_4       
        //   118: aload_0        
        //   119: bipush          108
        //   121: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   124: aload_1        
        //   125: aload_2        
        //   126: aload_0        
        //   127: bipush          109
        //   129: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   132: aload_1        
        //   133: aload_2        
        //   134: aload           locals
        //   136: aload_1        
        //   137: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   140: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   143: aload_0        
        //   144: aload_1        
        //   145: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   148: ldc_w           39
        //   151: ldc_w           "InterfaceGuid"
        //   154: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   157: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   160: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   163: aastore        
        //   164: aload           6
        //   166: iconst_5       
        //   167: aload_0        
        //   168: bipush          110
        //   170: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   173: aload_1        
        //   174: aload_2        
        //   175: aload_0        
        //   176: aload_1        
        //   177: ldc             "FFI"
        //   179: bipush          90
        //   181: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   184: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   187: aload_0        
        //   188: swap           
        //   189: aload_1        
        //   190: ldc             "MemoryPointer"
        //   192: bipush          91
        //   194: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   197: aload_0        
        //   198: aload_1        
        //   199: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   202: ldc_w           94
        //   205: ldc_w           "pointer"
        //   208: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   211: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   214: aastore        
        //   215: aload           6
        //   217: bipush          6
        //   219: aload_1        
        //   220: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   223: invokestatic    org/jruby/RubyFixnum.three:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   226: aastore        
        //   227: aload           6
        //   229: bipush          7
        //   231: aload_0        
        //   232: bipush          111
        //   234: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   237: aload_1        
        //   238: aload_2        
        //   239: aload_0        
        //   240: aload_1        
        //   241: ldc             "Win32"
        //   243: bipush          92
        //   245: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   248: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   251: aload_0        
        //   252: swap           
        //   253: aload_1        
        //   254: ldc             "Wlan"
        //   256: bipush          93
        //   258: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   261: bipush          7
        //   263: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //   266: dup            
        //   267: iconst_0       
        //   268: aload           locals
        //   270: aload_1        
        //   271: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   274: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   277: aastore        
        //   278: dup            
        //   279: iconst_1       
        //   280: aload           6
        //   282: iconst_4       
        //   283: aaload         
        //   284: aastore        
        //   285: dup            
        //   286: iconst_2       
        //   287: aload           locals
        //   289: aload_1        
        //   290: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   293: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   296: aastore        
        //   297: dup            
        //   298: iconst_3       
        //   299: aload           6
        //   301: bipush          6
        //   303: aaload         
        //   304: aastore        
        //   305: dup            
        //   306: iconst_4       
        //   307: aload           locals
        //   309: aload_1        
        //   310: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   313: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   316: aastore        
        //   317: dup            
        //   318: iconst_5       
        //   319: aload_1        
        //   320: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   323: aastore        
        //   324: dup            
        //   325: bipush          6
        //   327: aload           6
        //   329: iconst_5       
        //   330: aaload         
        //   331: aastore        
        //   332: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   335: aastore        
        //   336: aload_0        
        //   337: bipush          112
        //   339: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   342: aload_1        
        //   343: aload_2        
        //   344: aload_0        
        //   345: aload_1        
        //   346: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   349: ldc_w           103
        //   352: ldc_w           "SUCCESS"
        //   355: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   358: aload_0        
        //   359: bipush          113
        //   361: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   364: aload_1        
        //   365: aload_2        
        //   366: aload_0        
        //   367: aload_1        
        //   368: ldc             "Win32"
        //   370: bipush          94
        //   372: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   375: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   378: aload_0        
        //   379: swap           
        //   380: aload_1        
        //   381: ldc_w           "Errors"
        //   384: bipush          95
        //   386: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   389: aload           6
        //   391: bipush          7
        //   393: aaload         
        //   394: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   397: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   400: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   405: ifeq            552
        //   408: aload           6
        //   410: bipush          8
        //   412: aload_0        
        //   413: bipush          114
        //   415: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   418: aload_1        
        //   419: aload_2        
        //   420: aload           6
        //   422: iconst_5       
        //   423: aaload         
        //   424: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   427: aastore        
        //   428: aload           6
        //   430: bipush          9
        //   432: aload_0        
        //   433: bipush          115
        //   435: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   438: aload_1        
        //   439: aload_2        
        //   440: aload_0        
        //   441: aload_1        
        //   442: ldc             "Win32"
        //   444: bipush          96
        //   446: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   449: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   452: aload_0        
        //   453: swap           
        //   454: aload_1        
        //   455: ldc             "Wlan"
        //   457: bipush          97
        //   459: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   462: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   465: aload_0        
        //   466: swap           
        //   467: aload_1        
        //   468: ldc_w           "BSSList"
        //   471: bipush          98
        //   473: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   476: aload           6
        //   478: bipush          8
        //   480: aaload         
        //   481: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   484: aastore        
        //   485: aload           6
        //   487: bipush          10
        //   489: aload_0        
        //   490: bipush          116
        //   492: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   495: aload_1        
        //   496: aload_2        
        //   497: aload           6
        //   499: bipush          9
        //   501: aaload         
        //   502: aload_0        
        //   503: aload_1        
        //   504: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   507: ldc_w           43
        //   510: ldc_w           "dwNumberOfItems"
        //   513: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   516: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   519: aastore        
        //   520: aload_0        
        //   521: bipush          117
        //   523: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   526: aload_1        
        //   527: aload_2        
        //   528: aload           6
        //   530: bipush          10
        //   532: aaload         
        //   533: aload_1        
        //   534: aload_2        
        //   535: aload_0        
        //   536: aload_1        
        //   537: ldc_w           "block_5$RUBY$each_bssid_on_interface_and_essid,1,jdx;p_network;entry,false,2,./lib//win32/wlan.rb,297,false"
        //   540: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   543: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   546: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   549: goto            556
        //   552: aload_1        
        //   553: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   556: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  115    442     5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_5$RUBY$each_bssid_on_interface_and_essid(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    22: aload           5
        //    24: swap           
        //    25: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    28: pop            
        //    29: aload_1        
        //    30: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    33: aload           4
        //    35: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    38: aload_3        
        //    39: aload           5
        //    41: swap           
        //    42: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: pop            
        //    46: pop            
        //    47: aload           locals
        //    49: aload_0        
        //    50: bipush          118
        //    52: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    55: aload_1        
        //    56: aload_2        
        //    57: aload_0        
        //    58: aload_1        
        //    59: ldc             "FFI"
        //    61: bipush          99
        //    63: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    69: aload_0        
        //    70: swap           
        //    71: aload_1        
        //    72: ldc_w           "Pointer"
        //    75: bipush          100
        //    77: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: aload_0        
        //    81: bipush          119
        //    83: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    86: aload_1        
        //    87: aload_2        
        //    88: aload_0        
        //    89: bipush          120
        //    91: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    94: aload_1        
        //    95: aload_2        
        //    96: aload_0        
        //    97: bipush          121
        //    99: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   102: aload_1        
        //   103: aload_2        
        //   104: aload_0        
        //   105: bipush          122
        //   107: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   110: aload_1        
        //   111: aload_2        
        //   112: aload           locals
        //   114: bipush          9
        //   116: iconst_1       
        //   117: aload_1        
        //   118: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   121: invokevirtual   org/jruby/runtime/DynamicScope.getValueOrNil:(IILorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   124: aload_0        
        //   125: aload_1        
        //   126: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   129: ldc_w           87
        //   132: ldc_w           "wlanBssEntries"
        //   135: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   138: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   141: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   144: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   147: aload_0        
        //   148: bipush          123
        //   150: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   153: aload_1        
        //   154: aload_2        
        //   155: aload_0        
        //   156: bipush          124
        //   158: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   161: aload_1        
        //   162: aload_2        
        //   163: aload_0        
        //   164: aload_1        
        //   165: ldc             "Win32"
        //   167: bipush          101
        //   169: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   172: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   175: aload_0        
        //   176: swap           
        //   177: aload_1        
        //   178: ldc             "Wlan"
        //   180: bipush          102
        //   182: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   185: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   188: aload_0        
        //   189: swap           
        //   190: aload_1        
        //   191: ldc_w           "BSSEntry"
        //   194: bipush          103
        //   196: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   199: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   202: aload           locals
        //   204: aload_1        
        //   205: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   208: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   211: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   214: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   217: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   220: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   223: pop            
        //   224: aload           locals
        //   226: aload_0        
        //   227: bipush          125
        //   229: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   232: aload_1        
        //   233: aload_2        
        //   234: aload_0        
        //   235: aload_1        
        //   236: ldc             "Win32"
        //   238: bipush          104
        //   240: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   243: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   246: aload_0        
        //   247: swap           
        //   248: aload_1        
        //   249: ldc             "Wlan"
        //   251: bipush          105
        //   253: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   256: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   259: aload_0        
        //   260: swap           
        //   261: aload_1        
        //   262: ldc_w           "BSSEntry"
        //   265: bipush          106
        //   267: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   270: aload           locals
        //   272: aload_1        
        //   273: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   276: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   279: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   282: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   285: pop            
        //   286: aload_1        
        //   287: invokevirtual   org/jruby/runtime/ThreadContext.getFrameBlock:()Lorg/jruby/runtime/Block;
        //   290: aload_1        
        //   291: aload           locals
        //   293: aload_1        
        //   294: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   297: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   300: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   303: areturn        
        //   304: pop            
        //   305: goto            47
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  47     257     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  47     304    304    308    Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    @JRubyMethod(name = "each_network", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__37$RUBY$each_network(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    15: bipush          126
        //    17: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    20: aload_1        
        //    21: aload_2        
        //    22: aload_2        
        //    23: aload           locals
        //    25: aload_1        
        //    26: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    29: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    32: aload_1        
        //    33: aload_2        
        //    34: aload_0        
        //    35: aload_1        
        //    36: ldc_w           "block_6$RUBY$each_network,1,iface,false,2,./lib//win32/wlan.rb,308,false"
        //    39: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody7:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    42: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    45: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    48: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     35      5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_6$RUBY$each_network(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: bipush          127
        //    38: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_2        
        //    44: aload           locals
        //    46: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    49: aload_1        
        //    50: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    53: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    56: aload           locals
        //    58: aload_1        
        //    59: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    62: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: aload_1        
        //    66: aload_2        
        //    67: aload_0        
        //    68: aload_1        
        //    69: ldc_w           "block_7$RUBY$each_network,1,net,false,2,./lib//win32/wlan.rb,309,true"
        //    72: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody6:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    75: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    78: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    81: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     47      5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_7$RUBY$each_network(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    25: aload_1        
        //    26: invokevirtual   org/jruby/runtime/ThreadContext.getFrameBlock:()Lorg/jruby/runtime/Block;
        //    29: aload_1        
        //    30: aload           net
        //    32: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    35: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     11      9     net   Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "each_bssid", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__38$RUBY$each_bssid(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    15: sipush          128
        //    18: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    21: aload_1        
        //    22: aload_2        
        //    23: aload_2        
        //    24: aload           locals
        //    26: aload_1        
        //    27: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    30: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: aload_1        
        //    34: aload_2        
        //    35: aload_0        
        //    36: aload_1        
        //    37: ldc_w           "block_8$RUBY$each_bssid,1,iface,false,2,./lib//win32/wlan.rb,316,false"
        //    40: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody9:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    43: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    46: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     36      5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_8$RUBY$each_bssid(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: sipush          129
        //    39: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    42: aload_1        
        //    43: aload_2        
        //    44: aload_2        
        //    45: aload           locals
        //    47: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    50: aload_1        
        //    51: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: aload           locals
        //    59: aload_1        
        //    60: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: aload_1        
        //    67: aload_2        
        //    68: aload_0        
        //    69: aload_1        
        //    70: ldc_w           "block_9$RUBY$each_bssid,1,bssid,false,2,./lib//win32/wlan.rb,317,true"
        //    73: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody8:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    76: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    79: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     48      5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_9$RUBY$each_bssid(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    25: aload_1        
        //    26: invokevirtual   org/jruby/runtime/ThreadContext.getFrameBlock:()Lorg/jruby/runtime/Block;
        //    29: aload_1        
        //    30: aload           bssid
        //    32: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    35: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name   Signature
        //  -----  ------  ----  -----  ---------------------------------------
        //  25     11      9     bssid  Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "scan_all", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__39$RUBY$scan_all(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(threadContext.runtime.newArray());
        chained_40_rescue_1$RUBY$SYNTHETICscan_all(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, threadContext, rubyObject, block);
        return locals.getValueZeroDepthZeroOrNil(threadContext.nil);
    }
    
    public static IRubyObject chained_40_rescue_1$RUBY$SYNTHETICscan_all(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext context, final IRubyObject self, final Block block) {
        context.getCurrentScope();
        final IRubyObject errorInfo = context.getErrorInfo();
        IRubyObject rubyObject = null;
        Label_0095: {
            try {
                try {
                    rubyObject = file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(131).callIter(context, self, self, RuntimeHelpers.createBlock(context, self, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody(context, 11, "block_10$RUBY$scan_all,1,handle,false,2,./lib//win32/wlan.rb,328,false")));
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable currentThrowable) {
                    if (RuntimeHelpers.isJavaExceptionHandled(currentThrowable, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant(context, "CouldNotOpenWlanError", 109), context).isTrue()) {
                        rubyObject = context.nil;
                        RuntimeHelpers.clearErrorInfo(context);
                        break Label_0095;
                    }
                    throw currentThrowable;
                }
            }
            catch (JumpException.FlowControlException ex2) {
                context.setErrorInfo(errorInfo);
                throw ex2;
            }
        }
        context.setErrorInfo(errorInfo);
        return rubyObject;
    }
    
    public static IRubyObject block_10$RUBY$scan_all(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: sipush          132
        //    39: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    42: aload_1        
        //    43: aload_2        
        //    44: aload_2        
        //    45: aload           locals
        //    47: aload_1        
        //    48: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: pop            
        //    58: aload_0        
        //    59: sipush          133
        //    62: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    65: aload_1        
        //    66: aload_2        
        //    67: aload_2        
        //    68: aload_0        
        //    69: aload_1        
        //    70: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    73: bipush          10
        //    75: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //    78: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    81: pop            
        //    82: aload_0        
        //    83: sipush          134
        //    86: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    89: aload_1        
        //    90: aload_2        
        //    91: aload_2        
        //    92: aload           locals
        //    94: aload_1        
        //    95: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    98: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   101: aload_1        
        //   102: aload_2        
        //   103: aload_0        
        //   104: aload_1        
        //   105: bipush          10
        //   107: ldc_w           "block_11$RUBY$scan_all,1,net;n,false,2,./lib//win32/wlan.rb,331,false"
        //   110: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody:(Lorg/jruby/runtime/ThreadContext;ILjava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   113: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   116: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   119: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     85      5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_11$RUBY$scan_all(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    33: aload           5
        //    35: swap           
        //    36: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: pop            
        //    40: pop            
        //    41: aload           locals
        //    43: aload_0        
        //    44: sipush          135
        //    47: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    50: aload_1        
        //    51: aload_2        
        //    52: aload_0        
        //    53: aload_1        
        //    54: ldc_w           "FoundNetwork"
        //    57: bipush          108
        //    59: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    62: aload_0        
        //    63: sipush          136
        //    66: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    69: aload_1        
        //    70: aload_2        
        //    71: aload           locals
        //    73: aload_1        
        //    74: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: aload_0        
        //    84: sipush          137
        //    87: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    90: aload_1        
        //    91: aload_2        
        //    92: aload           locals
        //    94: aload_1        
        //    95: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    98: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   101: aload_0        
        //   102: aload_1        
        //   103: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   106: ldc_w           53
        //   109: ldc_w           "uNumberOfBssids"
        //   112: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   115: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   118: aload_0        
        //   119: sipush          138
        //   122: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   125: aload_1        
        //   126: aload_2        
        //   127: aload           locals
        //   129: aload_1        
        //   130: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   133: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   136: aload_0        
        //   137: aload_1        
        //   138: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   141: ldc_w           59
        //   144: ldc_w           "wlanSignalQuality"
        //   147: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   150: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   153: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   156: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   159: pop            
        //   160: aload_0        
        //   161: sipush          139
        //   164: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   167: aload_1        
        //   168: aload_2        
        //   169: aload           locals
        //   171: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   174: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   177: aload_1        
        //   178: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   181: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   184: aload           locals
        //   186: aload_1        
        //   187: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   190: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   193: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   196: pop            
        //   197: aload_1        
        //   198: invokevirtual   org/jruby/runtime/ThreadContext.getFrameBlock:()Lorg/jruby/runtime/Block;
        //   201: aload_1        
        //   202: aload           locals
        //   204: aload_1        
        //   205: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   208: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   211: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   214: areturn        
        //   215: pop            
        //   216: goto            41
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  41     174     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  41     215    215    219    Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    @JRubyMethod(name = "rescan_all", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__41$RUBY$rescan_all(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    15: sipush          140
        //    18: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    21: aload_1        
        //    22: aload_2        
        //    23: aload_2        
        //    24: aload           locals
        //    26: aload_1        
        //    27: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    30: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: aload_1        
        //    34: aload_2        
        //    35: aload_0        
        //    36: aload_1        
        //    37: bipush          12
        //    39: ldc_w           "block_12$RUBY$rescan_all,1,iface,false,2,./lib//win32/wlan.rb,344,true"
        //    42: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody:(Lorg/jruby/runtime/ThreadContext;ILjava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    45: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    48: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     38      5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_12$RUBY$rescan_all(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: sipush          141
        //    29: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    32: aload_1        
        //    33: aload_2        
        //    34: aload_2        
        //    35: aload           5
        //    37: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    40: aload_1        
        //    41: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    44: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: aload           iface
        //    49: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name   Signature
        //  -----  ------  ----  -----  ---------------------------------------
        //  25     28      9     iface  Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "rescan", frame = true, required = 2, optional = 0, rest = -1)
    public static IRubyObject method__42$RUBY$rescan(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final Block p5) {
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
        //    25: aload_0        
        //    26: sipush          142
        //    29: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    32: aload_1        
        //    33: aload_2        
        //    34: aload_0        
        //    35: sipush          143
        //    38: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload           locals
        //    45: aload_1        
        //    46: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: aload_0        
        //    53: aload_1        
        //    54: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    57: ldc_w           39
        //    60: ldc_w           "InterfaceGuid"
        //    63: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    66: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    69: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    72: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: pop            
        //    76: aload_0        
        //    77: sipush          144
        //    80: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    83: aload_1        
        //    84: aload_2        
        //    85: aload_0        
        //    86: aload_1        
        //    87: ldc             "Win32"
        //    89: bipush          110
        //    91: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    94: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    97: aload_0        
        //    98: swap           
        //    99: aload_1        
        //   100: ldc             "Wlan"
        //   102: bipush          111
        //   104: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   107: aload           locals
        //   109: aload_1        
        //   110: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   113: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   116: aload           locals
        //   118: aload_1        
        //   119: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   122: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   125: aload_1        
        //   126: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   129: aload_1        
        //   130: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   133: aload_1        
        //   134: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   137: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   140: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   143: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  23     121     6     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    @JRubyMethod(name = "do_query_iface", frame = true, required = 3, optional = 0, rest = -1)
    public static IRubyObject method__43$RUBY$do_query_iface(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final IRubyObject p5, final Block p6) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     4: dup            
        //     5: astore          14
        //     7: dup            
        //     8: astore          15
        //    10: dup            
        //    11: astore          16
        //    13: astore          17
        //    15: aload_3        
        //    16: astore          11
        //    18: aload           4
        //    20: astore          12
        //    22: aload           5
        //    24: astore          code
        //    26: aload_0        
        //    27: sipush          145
        //    30: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    33: aload_1        
        //    34: aload_2        
        //    35: aload_0        
        //    36: aload_1        
        //    37: ldc             "FFI"
        //    39: bipush          112
        //    41: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    44: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    47: aload_0        
        //    48: swap           
        //    49: aload_1        
        //    50: ldc             "MemoryPointer"
        //    52: bipush          113
        //    54: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: aload_0        
        //    58: aload_1        
        //    59: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    62: ldc             "uint"
        //    64: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    67: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: astore          p_size
        //    72: aload_0        
        //    73: sipush          146
        //    76: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    79: aload_1        
        //    80: aload_2        
        //    81: aload_0        
        //    82: aload_1        
        //    83: ldc             "FFI"
        //    85: bipush          114
        //    87: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    90: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    93: aload_0        
        //    94: swap           
        //    95: aload_1        
        //    96: ldc             "MemoryPointer"
        //    98: bipush          115
        //   100: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   103: aload_0        
        //   104: aload_1        
        //   105: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   108: ldc_w           94
        //   111: ldc_w           "pointer"
        //   114: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   117: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   120: astore          pp_data
        //   122: aload_0        
        //   123: sipush          147
        //   126: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   129: aload_1        
        //   130: aload_2        
        //   131: aload_0        
        //   132: aload_1        
        //   133: ldc             "FFI"
        //   135: bipush          116
        //   137: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   140: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   143: aload_0        
        //   144: swap           
        //   145: aload_1        
        //   146: ldc             "MemoryPointer"
        //   148: bipush          117
        //   150: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   153: aload_0        
        //   154: aload_1        
        //   155: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   158: ldc             "uint"
        //   160: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   163: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   166: astore          p_opcode
        //   168: aload_0        
        //   169: sipush          148
        //   172: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   175: aload_1        
        //   176: aload_2        
        //   177: aload_0        
        //   178: aload_1        
        //   179: ldc             "Win32"
        //   181: bipush          118
        //   183: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   186: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   189: aload_0        
        //   190: swap           
        //   191: aload_1        
        //   192: ldc             "Wlan"
        //   194: bipush          119
        //   196: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   199: bipush          7
        //   201: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //   204: dup            
        //   205: iconst_0       
        //   206: aload           handle
        //   208: aastore        
        //   209: dup            
        //   210: iconst_1       
        //   211: aload           iface
        //   213: aastore        
        //   214: dup            
        //   215: iconst_2       
        //   216: aload           code
        //   218: aastore        
        //   219: dup            
        //   220: iconst_3       
        //   221: aload_1        
        //   222: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   225: aastore        
        //   226: dup            
        //   227: iconst_4       
        //   228: aload           p_size
        //   230: aastore        
        //   231: dup            
        //   232: iconst_5       
        //   233: aload           pp_data
        //   235: aastore        
        //   236: dup            
        //   237: bipush          6
        //   239: aload           p_opcode
        //   241: aastore        
        //   242: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   245: astore          err
        //   247: aload_0        
        //   248: sipush          149
        //   251: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   254: aload_1        
        //   255: aload_2        
        //   256: aload_0        
        //   257: aload_1        
        //   258: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   261: ldc_w           103
        //   264: ldc_w           "SUCCESS"
        //   267: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   270: aload_0        
        //   271: sipush          150
        //   274: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   277: aload_1        
        //   278: aload_2        
        //   279: aload_0        
        //   280: aload_1        
        //   281: ldc             "Win32"
        //   283: bipush          120
        //   285: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   288: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   291: aload_0        
        //   292: swap           
        //   293: aload_1        
        //   294: ldc_w           "Errors"
        //   297: bipush          121
        //   299: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   302: aload           err
        //   304: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   307: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   310: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   315: ifeq            356
        //   318: aload_0        
        //   319: sipush          151
        //   322: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   325: aload_1        
        //   326: aload_2        
        //   327: aload_2        
        //   328: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   331: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   336: ifeq            351
        //   339: aload           6
        //   341: aload_1        
        //   342: aload           pp_data
        //   344: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   347: pop            
        //   348: goto            351
        //   351: aload           pp_data
        //   353: goto            360
        //   356: aload_1        
        //   357: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   360: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name      Signature
        //  -----  ------  ----  --------  ---------------------------------------
        //  26     335     11    handle    Lorg/jruby/runtime/builtin/IRubyObject;
        //  26     335     12    iface     Lorg/jruby/runtime/builtin/IRubyObject;
        //  26     335     13    code      Lorg/jruby/runtime/builtin/IRubyObject;
        //  26     335     14    p_size    Lorg/jruby/runtime/builtin/IRubyObject;
        //  26     335     15    pp_data   Lorg/jruby/runtime/builtin/IRubyObject;
        //  26     335     16    p_opcode  Lorg/jruby/runtime/builtin/IRubyObject;
        //  26     335     17    err       Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "channel_number", frame = true, required = 2, optional = 0, rest = -1)
    public static IRubyObject method__44$RUBY$channel_number(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final Block p5) {
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
        //    25: aload_0        
        //    26: sipush          152
        //    29: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    32: aload_1        
        //    33: aload_2        
        //    34: aload_0        
        //    35: aload_1        
        //    36: ldc             "Win32"
        //    38: bipush          122
        //    40: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    46: aload_0        
        //    47: swap           
        //    48: aload_1        
        //    49: ldc             "Wlan"
        //    51: bipush          123
        //    53: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    56: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    59: aload_0        
        //    60: swap           
        //    61: aload_1        
        //    62: ldc_w           "InterfaceOPCode"
        //    65: bipush          124
        //    67: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    73: aload_0        
        //    74: swap           
        //    75: aload_1        
        //    76: ldc_w           "MAP"
        //    79: bipush          125
        //    81: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    84: aload_0        
        //    85: aload_1        
        //    86: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    89: ldc_w           15
        //    92: ldc_w           "channel_number"
        //    95: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //    98: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   101: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   104: pop            
        //   105: aload_0        
        //   106: sipush          153
        //   109: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   112: aload_1        
        //   113: aload_2        
        //   114: aload_2        
        //   115: aload           locals
        //   117: aload_1        
        //   118: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   121: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   124: aload           locals
        //   126: aload_1        
        //   127: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   130: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   133: aload           locals
        //   135: aload_1        
        //   136: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   139: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   142: aload_1        
        //   143: aload_2        
        //   144: aload_0        
        //   145: aload_1        
        //   146: bipush          13
        //   148: ldc_w           "block_13$RUBY$channel_number,1,pp_data,false,2,./lib//win32/wlan.rb,372,true"
        //   151: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody:(Lorg/jruby/runtime/ThreadContext;ILjava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   154: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   157: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   160: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  23     138     6     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_13$RUBY$channel_number(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    25: aload_1        
        //    26: invokevirtual   org/jruby/runtime/ThreadContext.getFrameBlock:()Lorg/jruby/runtime/Block;
        //    29: aload_1        
        //    30: aload_0        
        //    31: sipush          154
        //    34: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    37: aload_1        
        //    38: aload_2        
        //    39: aload_0        
        //    40: sipush          155
        //    43: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    46: aload_1        
        //    47: aload_2        
        //    48: aload           pp_data
        //    50: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    53: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    56: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    59: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name     Signature
        //  -----  ------  ----  -------  ---------------------------------------
        //  25     35      9     pp_data  Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "current_rssi", frame = true, required = 2, optional = 0, rest = -1)
    public static IRubyObject method__45$RUBY$current_rssi(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final Block p5) {
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
        //    25: aload_0        
        //    26: sipush          156
        //    29: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    32: aload_1        
        //    33: aload_2        
        //    34: aload_0        
        //    35: aload_1        
        //    36: ldc             "Win32"
        //    38: bipush          126
        //    40: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    46: aload_0        
        //    47: swap           
        //    48: aload_1        
        //    49: ldc             "Wlan"
        //    51: bipush          127
        //    53: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    56: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    59: aload_0        
        //    60: swap           
        //    61: aload_1        
        //    62: ldc_w           "InterfaceOPCode"
        //    65: sipush          128
        //    68: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    71: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    74: aload_0        
        //    75: swap           
        //    76: aload_1        
        //    77: ldc_w           "MAP"
        //    80: sipush          129
        //    83: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    86: aload_0        
        //    87: aload_1        
        //    88: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    91: ldc_w           25
        //    94: ldc_w           "rssi"
        //    97: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   100: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   103: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   106: pop            
        //   107: aload_0        
        //   108: sipush          157
        //   111: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   114: aload_1        
        //   115: aload_2        
        //   116: aload_2        
        //   117: aload           locals
        //   119: aload_1        
        //   120: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   123: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   126: aload           locals
        //   128: aload_1        
        //   129: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   132: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   135: aload           locals
        //   137: aload_1        
        //   138: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   141: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   144: aload_1        
        //   145: aload_2        
        //   146: aload_0        
        //   147: aload_1        
        //   148: bipush          14
        //   150: ldc_w           "block_14$RUBY$current_rssi,1,pp_data,false,2,./lib//win32/wlan.rb,379,true"
        //   153: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody:(Lorg/jruby/runtime/ThreadContext;ILjava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   156: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   159: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   162: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  23     140     6     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_14$RUBY$current_rssi(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    25: aload_1        
        //    26: invokevirtual   org/jruby/runtime/ThreadContext.getFrameBlock:()Lorg/jruby/runtime/Block;
        //    29: aload_1        
        //    30: aload_0        
        //    31: sipush          158
        //    34: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    37: aload_1        
        //    38: aload_2        
        //    39: aload_0        
        //    40: sipush          159
        //    43: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    46: aload_1        
        //    47: aload_2        
        //    48: aload           pp_data
        //    50: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    53: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    56: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    59: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name     Signature
        //  -----  ------  ----  -------  ---------------------------------------
        //  25     35      9     pp_data  Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "current_connection", frame = true, required = 2, optional = 0, rest = -1)
    public static IRubyObject method__46$RUBY$current_connection(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final Block p5) {
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
        //    25: aload_0        
        //    26: sipush          160
        //    29: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    32: aload_1        
        //    33: aload_2        
        //    34: aload_0        
        //    35: aload_1        
        //    36: ldc             "Win32"
        //    38: sipush          130
        //    41: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    44: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    47: aload_0        
        //    48: swap           
        //    49: aload_1        
        //    50: ldc             "Wlan"
        //    52: sipush          131
        //    55: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    61: aload_0        
        //    62: swap           
        //    63: aload_1        
        //    64: ldc_w           "InterfaceOPCode"
        //    67: sipush          132
        //    70: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    76: aload_0        
        //    77: swap           
        //    78: aload_1        
        //    79: ldc_w           "MAP"
        //    82: sipush          133
        //    85: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: aload_0        
        //    89: aload_1        
        //    90: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    93: ldc_w           14
        //    96: ldc_w           "current_connection"
        //    99: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   102: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   108: pop            
        //   109: aload_0        
        //   110: sipush          161
        //   113: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   116: aload_1        
        //   117: aload_2        
        //   118: aload_2        
        //   119: aload           locals
        //   121: aload_1        
        //   122: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   125: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   128: aload           locals
        //   130: aload_1        
        //   131: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   134: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   137: aload           locals
        //   139: aload_1        
        //   140: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   143: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   146: aload_1        
        //   147: aload_2        
        //   148: aload_0        
        //   149: aload_1        
        //   150: bipush          15
        //   152: ldc_w           "block_15$RUBY$current_connection,1,pp_data;p_connection_attributes;attrs,false,2,./lib//win32/wlan.rb,387,true"
        //   155: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody:(Lorg/jruby/runtime/ThreadContext;ILjava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   158: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   161: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   164: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  23     142     6     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_15$RUBY$current_connection(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    19: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    22: astore          11
        //    24: aload_1        
        //    25: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    28: aload           4
        //    30: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: aload_3        
        //    34: astore          9
        //    36: pop            
        //    37: aload_0        
        //    38: sipush          162
        //    41: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    44: aload_1        
        //    45: aload_2        
        //    46: aload           pp_data
        //    48: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: astore          p_connection_attributes
        //    53: aload_0        
        //    54: sipush          163
        //    57: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    60: aload_1        
        //    61: aload_2        
        //    62: aload_0        
        //    63: aload_1        
        //    64: ldc             "Win32"
        //    66: sipush          134
        //    69: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    72: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    75: aload_0        
        //    76: swap           
        //    77: aload_1        
        //    78: ldc             "Wlan"
        //    80: sipush          135
        //    83: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    86: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    89: aload_0        
        //    90: swap           
        //    91: aload_1        
        //    92: ldc_w           "ConnectionAttributes"
        //    95: sipush          136
        //    98: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   101: aload           p_connection_attributes
        //   103: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   106: astore          attrs
        //   108: aload_1        
        //   109: invokevirtual   org/jruby/runtime/ThreadContext.getFrameBlock:()Lorg/jruby/runtime/Block;
        //   112: aload_1        
        //   113: aload           attrs
        //   115: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   118: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name                     Signature
        //  -----  ------  ----  -----------------------  ---------------------------------------
        //  37     82      9     pp_data                  Lorg/jruby/runtime/builtin/IRubyObject;
        //  37     82      10    p_connection_attributes  Lorg/jruby/runtime/builtin/IRubyObject;
        //  37     82      11    attrs                    Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "current_profile", frame = true, required = 2, optional = 0, rest = -1)
    public static IRubyObject method__47$RUBY$current_profile(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final Block p5) {
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
        //    24: sipush          164
        //    27: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    30: aload_1        
        //    31: aload_2        
        //    32: aload_2        
        //    33: aload           locals
        //    35: aload_1        
        //    36: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    42: aload           locals
        //    44: aload_1        
        //    45: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    48: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: aload_1        
        //    52: aload_2        
        //    53: aload_0        
        //    54: aload_1        
        //    55: bipush          17
        //    57: ldc_w           "block_16$RUBY$current_profile,1,crap;nice,false,2,./lib//win32/wlan.rb,395,false"
        //    60: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody:(Lorg/jruby/runtime/ThreadContext;ILjava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    63: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    66: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    69: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  23     47      6     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_16$RUBY$current_profile(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    33: aload           5
        //    35: swap           
        //    36: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: pop            
        //    40: pop            
        //    41: aload           locals
        //    43: aload_0        
        //    44: sipush          165
        //    47: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    50: aload_1        
        //    51: aload_2        
        //    52: aload_0        
        //    53: sipush          166
        //    56: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    59: aload_1        
        //    60: aload_2        
        //    61: aload_0        
        //    62: sipush          167
        //    65: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    68: aload_1        
        //    69: aload_2        
        //    70: aload           locals
        //    72: aload_1        
        //    73: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: aload_0        
        //    80: aload_1        
        //    81: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    84: bipush          32
        //    86: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getString4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    89: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    92: aload_1        
        //    93: aload_2        
        //    94: aload_0        
        //    95: aload_1        
        //    96: bipush          16
        //    98: ldc_w           "block_17$RUBY$current_profile,1,c,false,2,./lib//win32/wlan.rb,396,true"
        //   101: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody:(Lorg/jruby/runtime/ThreadContext;ILjava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   104: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   107: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   110: aload_0        
        //   111: aload_1        
        //   112: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   115: bipush          32
        //   117: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getString4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   120: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   123: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   126: pop            
        //   127: aload_1        
        //   128: invokevirtual   org/jruby/runtime/ThreadContext.getFrameBlock:()Lorg/jruby/runtime/Block;
        //   131: aload_1        
        //   132: aload           locals
        //   134: aload_1        
        //   135: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   138: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   141: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   144: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  41     104     5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_17$RUBY$current_profile(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: sipush          168
        //    29: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    32: aload_1        
        //    33: aload_2        
        //    34: aload           c
        //    36: ldc2_w          0
        //    39: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;J)Lorg/jruby/runtime/builtin/IRubyObject;
        //    42: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     18      9     c     Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "current_profile_as_xml_crap", frame = true, required = 2, optional = 0, rest = -1)
    public static IRubyObject method__48$RUBY$current_profile_as_xml_crap(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final Block p5) {
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
        //    24: sipush          169
        //    27: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    30: aload_1        
        //    31: aload_2        
        //    32: aload_2        
        //    33: aload           locals
        //    35: aload_1        
        //    36: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    42: aload           locals
        //    44: aload_1        
        //    45: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    48: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: aload_1        
        //    52: aload_2        
        //    53: aload_0        
        //    54: aload_1        
        //    55: bipush          20
        //    57: ldc_w           "block_18$RUBY$current_profile_as_xml_crap,1,attr;name;p_xml;p_flags;p_granted_access;err;i;final_str,false,2,./lib//win32/wlan.rb,402,false"
        //    60: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody:(Lorg/jruby/runtime/ThreadContext;ILjava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    63: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    66: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    69: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  23     47      6     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_18$RUBY$current_profile_as_xml_crap(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    71: aload_0        
        //    72: sipush          170
        //    75: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    78: aload_1        
        //    79: aload_2        
        //    80: aload           locals
        //    82: aload_1        
        //    83: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    86: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: aload_0        
        //    90: aload_1        
        //    91: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    94: ldc_w           50
        //    97: ldc_w           "strProfileName"
        //   100: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   103: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   106: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   109: pop            
        //   110: aload           locals
        //   112: aload_0        
        //   113: sipush          171
        //   116: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   119: aload_1        
        //   120: aload_2        
        //   121: aload_0        
        //   122: aload_1        
        //   123: ldc             "FFI"
        //   125: sipush          137
        //   128: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   131: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   134: aload_0        
        //   135: swap           
        //   136: aload_1        
        //   137: ldc             "MemoryPointer"
        //   139: sipush          138
        //   142: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   145: aload_0        
        //   146: aload_1        
        //   147: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   150: ldc_w           94
        //   153: ldc_w           "pointer"
        //   156: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   159: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   162: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   165: pop            
        //   166: aload           locals
        //   168: aload_1        
        //   169: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   172: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   175: pop            
        //   176: aload           6
        //   178: iconst_4       
        //   179: aload_1        
        //   180: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   183: aastore        
        //   184: aload           6
        //   186: iconst_5       
        //   187: aload_0        
        //   188: sipush          172
        //   191: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   194: aload_1        
        //   195: aload_2        
        //   196: aload_0        
        //   197: aload_1        
        //   198: ldc             "Win32"
        //   200: sipush          139
        //   203: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   206: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   209: aload_0        
        //   210: swap           
        //   211: aload_1        
        //   212: ldc             "Wlan"
        //   214: sipush          140
        //   217: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   220: bipush          7
        //   222: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //   225: dup            
        //   226: iconst_0       
        //   227: aload           locals
        //   229: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   232: aload_1        
        //   233: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   236: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   239: aastore        
        //   240: dup            
        //   241: iconst_1       
        //   242: aload           locals
        //   244: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   247: aload_1        
        //   248: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   251: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   254: aastore        
        //   255: dup            
        //   256: iconst_2       
        //   257: aload           locals
        //   259: aload_1        
        //   260: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   263: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   266: aastore        
        //   267: dup            
        //   268: iconst_3       
        //   269: aload_1        
        //   270: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   273: aastore        
        //   274: dup            
        //   275: iconst_4       
        //   276: aload           locals
        //   278: aload_1        
        //   279: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   282: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   285: aastore        
        //   286: dup            
        //   287: iconst_5       
        //   288: aload           locals
        //   290: aload_1        
        //   291: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   294: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   297: aastore        
        //   298: dup            
        //   299: bipush          6
        //   301: aload           6
        //   303: iconst_4       
        //   304: aaload         
        //   305: aastore        
        //   306: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   309: aastore        
        //   310: aload_0        
        //   311: sipush          173
        //   314: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   317: aload_1        
        //   318: aload_2        
        //   319: aload_0        
        //   320: aload_1        
        //   321: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   324: ldc_w           103
        //   327: ldc_w           "SUCCESS"
        //   330: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   333: aload_0        
        //   334: sipush          174
        //   337: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   340: aload_1        
        //   341: aload_2        
        //   342: aload_0        
        //   343: aload_1        
        //   344: ldc             "Win32"
        //   346: sipush          141
        //   349: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   352: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.checkIsModule:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //   355: aload_0        
        //   356: swap           
        //   357: aload_1        
        //   358: ldc_w           "Errors"
        //   361: sipush          142
        //   364: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom:(Lorg/jruby/RubyModule;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   367: aload           6
        //   369: iconst_5       
        //   370: aaload         
        //   371: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   374: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   377: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   382: ifeq            484
        //   385: aload           6
        //   387: bipush          6
        //   389: aload_1        
        //   390: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   393: invokestatic    org/jruby/RubyFixnum.zero:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   396: aastore        
        //   397: aload           6
        //   399: bipush          7
        //   401: aload_1        
        //   402: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   405: aastore        
        //   406: aload_0        
        //   407: sipush          175
        //   410: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   413: aload_1        
        //   414: aload_2        
        //   415: aload_2        
        //   416: aload_1        
        //   417: aload_2        
        //   418: aload_0        
        //   419: aload_1        
        //   420: bipush          19
        //   422: ldc_w           "block_19$RUBY$current_profile_as_xml_crap,-1,str;chars,false,0,./lib//win32/wlan.rb,415,false"
        //   425: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody:(Lorg/jruby/runtime/ThreadContext;ILjava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   428: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   431: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   434: pop            
        //   435: aload_1        
        //   436: invokevirtual   org/jruby/runtime/ThreadContext.getFrameBlock:()Lorg/jruby/runtime/Block;
        //   439: aload_1        
        //   440: aload_0        
        //   441: sipush          184
        //   444: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   447: aload_1        
        //   448: aload_2        
        //   449: aload_0        
        //   450: sipush          185
        //   453: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   456: aload_1        
        //   457: aload_2        
        //   458: aload           locals
        //   460: aload_1        
        //   461: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   464: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   467: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   470: aload           6
        //   472: bipush          6
        //   474: aaload         
        //   475: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   478: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   481: goto            488
        //   484: aload_1        
        //   485: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   488: areturn        
        //   489: pop            
        //   490: goto            69
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  69     420     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  69     489    489    493    Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    public static IRubyObject block_19$RUBY$current_profile_as_xml_crap(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    33: pop            
        //    34: pop            
        //    35: aload           locals
        //    37: bipush          6
        //    39: aload_0        
        //    40: sipush          176
        //    43: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    46: aload_1        
        //    47: aload_2        
        //    48: aload           locals
        //    50: bipush          6
        //    52: iconst_1       
        //    53: aload_1        
        //    54: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: invokevirtual   org/jruby/runtime/DynamicScope.getValueOrNil:(IILorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: ldc2_w          1
        //    63: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;J)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: iconst_1       
        //    67: invokevirtual   org/jruby/runtime/DynamicScope.setValue:(ILorg/jruby/runtime/builtin/IRubyObject;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: pop            
        //    71: aload           locals
        //    73: aload_0        
        //    74: sipush          177
        //    77: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    80: aload_1        
        //    81: aload_2        
        //    82: aload_0        
        //    83: sipush          178
        //    86: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    89: aload_1        
        //    90: aload_2        
        //    91: aload           locals
        //    93: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    96: aload_1        
        //    97: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   100: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   103: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   106: aload           locals
        //   108: bipush          6
        //   110: iconst_1       
        //   111: aload_1        
        //   112: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   115: invokevirtual   org/jruby/runtime/DynamicScope.getValueOrNil:(IILorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   118: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   121: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   124: pop            
        //   125: aload           locals
        //   127: aload_0        
        //   128: sipush          179
        //   131: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   134: aload_1        
        //   135: aload_2        
        //   136: aload_0        
        //   137: sipush          180
        //   140: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   143: aload_1        
        //   144: aload_2        
        //   145: aload           locals
        //   147: aload_1        
        //   148: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   151: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   154: aload_0        
        //   155: aload_1        
        //   156: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   159: bipush          32
        //   161: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getString4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   164: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   167: aload_1        
        //   168: aload_2        
        //   169: aload_0        
        //   170: aload_1        
        //   171: bipush          18
        //   173: ldc_w           "block_20$RUBY$current_profile_as_xml_crap,1,v,false,2,./lib//win32/wlan.rb,418,true"
        //   176: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody:(Lorg/jruby/runtime/ThreadContext;ILjava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   179: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   182: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   185: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   188: pop            
        //   189: aload           locals
        //   191: bipush          7
        //   193: aload_0        
        //   194: sipush          182
        //   197: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   200: aload_1        
        //   201: aload_2        
        //   202: aload           locals
        //   204: aload_1        
        //   205: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   208: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   211: aload_0        
        //   212: aload_1        
        //   213: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   216: bipush          32
        //   218: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getString4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   221: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   224: iconst_1       
        //   225: invokevirtual   org/jruby/runtime/DynamicScope.setValue:(ILorg/jruby/runtime/builtin/IRubyObject;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   228: pop            
        //   229: aload_0        
        //   230: sipush          183
        //   233: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   236: aload_1        
        //   237: aload_2        
        //   238: aload           locals
        //   240: bipush          7
        //   242: iconst_1       
        //   243: aload_1        
        //   244: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   247: invokevirtual   org/jruby/runtime/DynamicScope.getValueOrNil:(IILorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   250: aload_0        
        //   251: aload_1        
        //   252: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   255: bipush          11
        //   257: bipush          32
        //   259: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   262: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   265: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   270: ifeq            284
        //   273: aload_1        
        //   274: aload_1        
        //   275: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   278: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.breakJump:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   281: goto            288
        //   284: aload_1        
        //   285: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   288: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     254     5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_20$RUBY$current_profile_as_xml_crap(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: sipush          181
        //    29: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    32: aload_1        
        //    33: aload_2        
        //    34: aload           v
        //    36: ldc2_w          0
        //    39: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;J)Lorg/jruby/runtime/builtin/IRubyObject;
        //    42: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     18      9     v     Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "get_status", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__49$RUBY$get_status(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final IRubyObject nil = context.nil;
        file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(186).call(context, rubyObject, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant(context, "NotImplemented", 143), file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getString(context.runtime, 12, 32));
        final IRubyObject pp_hostednetwork_status = file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(187).call(context, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom(RuntimeHelpers.checkIsModule(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant(context, "FFI", 144)), context, "MemoryPointer", 145), file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol(context.runtime, 94, "pointer"));
        final IRubyObject err = file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(188).call(context, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom(RuntimeHelpers.checkIsModule(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant(context, "Win32", 146)), context, "Wlan", 147), rubyObject2, pp_hostednetwork_status, context.nil);
        return file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(189).call(context, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol(context.runtime, 103, "SUCCESS"), file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(190).call(context, rubyObject, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstantFrom(RuntimeHelpers.checkIsModule(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant(context, "Win32", 148)), context, "Errors", 149), err)).isTrue() ? file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(191).call(context, rubyObject, pp_hostednetwork_status) : context.nil;
    }
    
    @JRubyMethod(name = "current_infos", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__50$RUBY$current_infos(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return chained_51_rescue_2$RUBY$SYNTHETICcurrent_infos(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, threadContext, rubyObject, block);
    }
    
    public static IRubyObject chained_51_rescue_2$RUBY$SYNTHETICcurrent_infos(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext context, final IRubyObject self, final Block block) {
        context.getCurrentScope();
        final IRubyObject errorInfo = context.getErrorInfo();
        IRubyObject rubyObject = null;
        Label_0096: {
            try {
                try {
                    rubyObject = file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(193).callIter(context, self, self, RuntimeHelpers.createBlock(context, self, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody(context, 26, "block_21$RUBY$current_infos,1,handle;status,false,2,./lib//win32/wlan.rb,441,false")));
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable currentThrowable) {
                    if (RuntimeHelpers.isJavaExceptionHandled(currentThrowable, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant(context, "CouldNotOpenWlanError", 153), context).isTrue()) {
                        rubyObject = context.nil;
                        RuntimeHelpers.clearErrorInfo(context);
                        break Label_0096;
                    }
                    throw currentThrowable;
                }
            }
            catch (JumpException.FlowControlException ex2) {
                context.setErrorInfo(errorInfo);
                throw ex2;
            }
        }
        context.setErrorInfo(errorInfo);
        return rubyObject;
    }
    
    public static IRubyObject block_21$RUBY$current_infos(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    33: aload           5
        //    35: swap           
        //    36: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: pop            
        //    40: pop            
        //    41: aload           locals
        //    43: aload_0        
        //    44: sipush          194
        //    47: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    50: aload_1        
        //    51: aload_2        
        //    52: aload_0        
        //    53: aload_1        
        //    54: ldc_w           "Status"
        //    57: sipush          151
        //    60: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    69: pop            
        //    70: aload_0        
        //    71: sipush          195
        //    74: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    77: aload_1        
        //    78: aload_2        
        //    79: aload_2        
        //    80: aload           locals
        //    82: aload_1        
        //    83: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    86: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: aload_1        
        //    90: aload_2        
        //    91: aload_0        
        //    92: aload_1        
        //    93: bipush          25
        //    95: ldc_w           "block_22$RUBY$current_infos,1,iface,false,2,./lib//win32/wlan.rb,445,false"
        //    98: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody:(Lorg/jruby/runtime/ThreadContext;ILjava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   101: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   104: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   107: pop            
        //   108: aload_1        
        //   109: invokevirtual   org/jruby/runtime/ThreadContext.getFrameBlock:()Lorg/jruby/runtime/Block;
        //   112: aload_1        
        //   113: aload           locals
        //   115: aload_1        
        //   116: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   119: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   122: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   125: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  41     85      5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_22$RUBY$current_infos(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: sipush          196
        //    39: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    42: aload_1        
        //    43: aload_2        
        //    44: aload_2        
        //    45: aload           locals
        //    47: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    50: aload_1        
        //    51: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: aload           locals
        //    59: aload_1        
        //    60: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: aload_1        
        //    67: aload_2        
        //    68: aload_0        
        //    69: aload_1        
        //    70: bipush          21
        //    72: ldc_w           "block_23$RUBY$current_infos,1,attr,false,2,./lib//win32/wlan.rb,446,true"
        //    75: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody:(Lorg/jruby/runtime/ThreadContext;ILjava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    78: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    81: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    84: pop            
        //    85: aload_0        
        //    86: sipush          199
        //    89: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    92: aload_1        
        //    93: aload_2        
        //    94: aload_2        
        //    95: aload           locals
        //    97: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   100: aload_1        
        //   101: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   104: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   107: aload           locals
        //   109: aload_1        
        //   110: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   113: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   116: aload_1        
        //   117: aload_2        
        //   118: aload_0        
        //   119: aload_1        
        //   120: bipush          22
        //   122: ldc_w           "block_24$RUBY$current_infos,1,attr,false,2,./lib//win32/wlan.rb,449,true"
        //   125: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody:(Lorg/jruby/runtime/ThreadContext;ILjava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   128: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   131: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   134: pop            
        //   135: aload_0        
        //   136: sipush          202
        //   139: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   142: aload_1        
        //   143: aload_2        
        //   144: aload_2        
        //   145: aload           locals
        //   147: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   150: aload_1        
        //   151: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   154: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   157: aload           locals
        //   159: aload_1        
        //   160: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   163: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   166: aload_1        
        //   167: aload_2        
        //   168: aload_0        
        //   169: aload_1        
        //   170: bipush          24
        //   172: ldc_w           "block_25$RUBY$current_infos,1,xml,false,2,./lib//win32/wlan.rb,452,false"
        //   175: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody:(Lorg/jruby/runtime/ThreadContext;ILjava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   178: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   181: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   184: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     150     5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_23$RUBY$current_infos(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    25: aload           5
        //    27: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    30: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    33: aload_1        
        //    34: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    37: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    40: dup            
        //    41: aload_2        
        //    42: aload_0        
        //    43: sipush          197
        //    46: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_0        
        //    50: sipush          198
        //    53: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    56: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //    59: aload           attr
        //    61: aload_1        
        //    62: aload_2        
        //    63: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     42      9     attr  Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject block_24$RUBY$current_infos(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    25: aload           5
        //    27: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    30: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    33: aload_1        
        //    34: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    37: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    40: dup            
        //    41: aload_2        
        //    42: aload_0        
        //    43: sipush          200
        //    46: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_0        
        //    50: sipush          201
        //    53: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    56: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //    59: aload           attr
        //    61: aload_1        
        //    62: aload_2        
        //    63: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     42      9     attr  Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject block_25$RUBY$current_infos(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: sipush          203
        //    39: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    42: aload_1        
        //    43: aload_2        
        //    44: aload_0        
        //    45: sipush          204
        //    48: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    51: aload_1        
        //    52: aload_2        
        //    53: aload           locals
        //    55: aload_1        
        //    56: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    59: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    62: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: aload_1        
        //    66: aload_2        
        //    67: aload_0        
        //    68: aload_1        
        //    69: bipush          23
        //    71: ldc_w           "block_26$RUBY$current_infos,1,line;essid,false,2,./lib//win32/wlan.rb,453,false"
        //    74: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody:(Lorg/jruby/runtime/ThreadContext;ILjava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    77: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    80: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     49      5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_26$RUBY$current_infos(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    33: aload           5
        //    35: swap           
        //    36: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: pop            
        //    40: pop            
        //    41: aload_0        
        //    42: aload_1        
        //    43: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    46: aload_0        
        //    47: bipush          13
        //    49: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getByteList:(I)Lorg/jruby/util/ByteList;
        //    52: ldc_w           512
        //    55: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getRegexp0:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //    58: aload           locals
        //    60: aload_1        
        //    61: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    64: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: aload_1        
        //    68: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.match3:(Lorg/jruby/RubyRegexp;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    71: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    76: ifeq            181
        //    79: aload           locals
        //    81: aload_0        
        //    82: sipush          205
        //    85: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    88: aload_1        
        //    89: aload_2        
        //    90: aload_0        
        //    91: sipush          206
        //    94: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    97: aload_1        
        //    98: aload_2        
        //    99: aload_0        
        //   100: aload_1        
        //   101: ldc_w           "Regexp"
        //   104: sipush          152
        //   107: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   110: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   113: aload_1        
        //   114: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   117: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   120: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   123: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   126: pop            
        //   127: aload           locals
        //   129: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   132: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   135: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   138: aload_1        
        //   139: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   142: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   145: dup            
        //   146: aload_2        
        //   147: aload_0        
        //   148: sipush          207
        //   151: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   154: aload_0        
        //   155: sipush          208
        //   158: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   161: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   164: aload           locals
        //   166: aload_1        
        //   167: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   170: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   173: aload_1        
        //   174: aload_2        
        //   175: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   178: goto            185
        //   181: aload_1        
        //   182: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   185: areturn        
        //   186: pop            
        //   187: goto            41
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  41     145     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  41     186    186    190    Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    @JRubyMethod(name = "deep_scan_all", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__52$RUBY$deep_scan_all(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return chained_53_rescue_3$RUBY$SYNTHETICdeep_scan_all(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, threadContext, rubyObject, block);
    }
    
    public static IRubyObject chained_53_rescue_3$RUBY$SYNTHETICdeep_scan_all(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext context, final IRubyObject self, final Block block) {
        context.getCurrentScope();
        final IRubyObject errorInfo = context.getErrorInfo();
        IRubyObject rubyObject = null;
        Label_0096: {
            try {
                try {
                    rubyObject = file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite(210).callIter(context, self, self, RuntimeHelpers.createBlock(context, self, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody(context, 28, "block_27$RUBY$deep_scan_all,1,handle,false,2,./lib//win32/wlan.rb,470,false")));
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable currentThrowable) {
                    if (RuntimeHelpers.isJavaExceptionHandled(currentThrowable, file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant(context, "CouldNotOpenWlanError", 156), context).isTrue()) {
                        rubyObject = context.nil;
                        RuntimeHelpers.clearErrorInfo(context);
                        break Label_0096;
                    }
                    throw currentThrowable;
                }
            }
            catch (JumpException.FlowControlException ex2) {
                context.setErrorInfo(errorInfo);
                throw ex2;
            }
        }
        context.setErrorInfo(errorInfo);
        return rubyObject;
    }
    
    public static IRubyObject block_27$RUBY$deep_scan_all(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: sipush          211
        //    39: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    42: aload_1        
        //    43: aload_2        
        //    44: aload_2        
        //    45: aload           locals
        //    47: aload_1        
        //    48: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: pop            
        //    58: aload_0        
        //    59: sipush          212
        //    62: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    65: aload_1        
        //    66: aload_2        
        //    67: aload_2        
        //    68: aload_0        
        //    69: aload_1        
        //    70: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    73: bipush          10
        //    75: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getFixnum4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //    78: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    81: pop            
        //    82: aload_0        
        //    83: sipush          213
        //    86: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    89: aload_1        
        //    90: aload_2        
        //    91: aload_2        
        //    92: aload           locals
        //    94: aload_1        
        //    95: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    98: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   101: aload_1        
        //   102: aload_2        
        //   103: aload_0        
        //   104: aload_1        
        //   105: bipush          27
        //   107: ldc_w           "block_28$RUBY$deep_scan_all,1,net,false,2,./lib//win32/wlan.rb,473,false"
        //   110: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getBlockBody:(Lorg/jruby/runtime/ThreadContext;ILjava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   113: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   116: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   119: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     85      5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_28$RUBY$deep_scan_all(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    35: aload_1        
        //    36: invokevirtual   org/jruby/runtime/ThreadContext.getFrameBlock:()Lorg/jruby/runtime/Block;
        //    39: aload_1        
        //    40: aload_0        
        //    41: sipush          214
        //    44: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    47: aload_1        
        //    48: aload_2        
        //    49: aload_0        
        //    50: aload_1        
        //    51: ldc_w           "Signal"
        //    54: sipush          155
        //    57: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: aload_0        
        //    61: sipush          215
        //    64: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    67: aload_1        
        //    68: aload_2        
        //    69: aload           locals
        //    71: aload_1        
        //    72: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    78: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    81: aload_0        
        //    82: sipush          216
        //    85: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    88: aload_1        
        //    89: aload_2        
        //    90: aload           locals
        //    92: aload_1        
        //    93: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    96: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    99: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   102: aload_0        
        //   103: sipush          217
        //   106: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   109: aload_1        
        //   110: aload_2        
        //   111: aload           locals
        //   113: aload_1        
        //   114: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   117: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   120: aload_0        
        //   121: aload_1        
        //   122: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   125: ldc_w           72
        //   128: ldc_w           "lRssi"
        //   131: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   134: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   137: aload_0        
        //   138: sipush          218
        //   141: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   144: aload_1        
        //   145: aload_2        
        //   146: aload           locals
        //   148: aload_1        
        //   149: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   152: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   155: aload_0        
        //   156: aload_1        
        //   157: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   160: ldc_w           82
        //   163: ldc_w           "ulChCenterFrequency"
        //   166: invokevirtual   ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.getSymbol:(Lorg/jruby/Ruby;ILjava/lang/String;)Lorg/jruby/RubySymbol;
        //   169: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   172: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   175: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   178: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   181: areturn        
        //   182: pop            
        //   183: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     147     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     182    182    186    Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    public static IRubyObject module__29$RUBY$Wlan(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__29$RUBY$Wlan(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__28$RUBY$Win32(final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__28$RUBY$Win32(file_FCD3200A79545F8938D6263A33FAE80E72A81C4A, threadContext, rubyObject, block);
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
        final FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A file_FCD3200A79545F8938D6263A33FAE80E72A81C4A = new FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A();
        final String string = FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.class.getClassLoader().getResource("ruby/jit/FILE_FCD3200A79545F8938D6263A33FAE80E72A81C4A.class").toString();
        file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_FCD3200A79545F8938D6263A33FAE80E72A81C4A.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
