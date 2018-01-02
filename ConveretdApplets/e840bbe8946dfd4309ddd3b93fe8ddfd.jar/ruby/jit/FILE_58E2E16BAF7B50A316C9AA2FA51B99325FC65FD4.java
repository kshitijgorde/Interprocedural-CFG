// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.RubyHash;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4 extends AbstractScript
{
    public FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4() {
        this.filename = "./lib//lister/runner/questionnaire/has_listener.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("extend\uffffN\uffffnew\uffffN\ufffflistener\uffffN\uffffclass\uffffN\uffff[]\uffffN\ufffflisteners\uffffV\ufffflisteners\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\uffffnew\uffffN\uffff[]\uffffN\ufffflisteners\uffffV\uffffattr_accessor\uffffF\uffff\u0005\u0002\u0000\u0000\u0003\u0000\u0000\u0001\u0003\u0000\u0000\u0000\u0001\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(0, "instance-variable", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4 file_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return module__0$RUBY$Lister(file_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.module__1$RUBY$Questionnaire:(Lruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Questionnaire(final FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Questionnaire"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.module__2$RUBY$HasListeners:(Lruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__2$RUBY$HasListeners(final FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "HasListeners"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_1        
        //    28: aload_2        
        //    29: aload_2        
        //    30: aload_0        
        //    31: ldc             "included"
        //    33: ldc             "method__3$RUBY$included"
        //    35: ldc             "mod,1,0,-1"
        //    37: iconst_1       
        //    38: ldc             "./lib//lister/runner/questionnaire/has_listener.rb"
        //    40: ldc             4
        //    42: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    45: ldc             "qmod"
        //    47: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: pop            
        //    51: aload_1        
        //    52: aload_2        
        //    53: aload_0        
        //    54: ldc             "listener"
        //    56: ldc             "method__4$RUBY$listener"
        //    58: ldc             "sym,0,1,-1"
        //    60: iconst_m1      
        //    61: ldc             "./lib//lister/runner/questionnaire/has_listener.rb"
        //    63: ldc             8
        //    65: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    68: ldc             "osym"
        //    70: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: pop            
        //    74: aload_0        
        //    75: aload_1        
        //    76: aload_2        
        //    77: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    83: invokestatic    ruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.module__5$RUBY$ClassMethods:(Lruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    86: pop            
        //    87: aload_0        
        //    88: aload_1        
        //    89: aload_2        
        //    90: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    93: invokestatic    ruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.class_8$RUBY$Listener:(Lruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    96: aload_1        
        //    97: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   100: goto            108
        //   103: aload_1        
        //   104: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   107: athrow         
        //   108: aload_1        
        //   109: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   112: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     100    103    108    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "included", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$included(final FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4 file_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.getCallSite0().call(context, rubyObject, rubyObject2, file_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.getConstant0(context, "ClassMethods"));
    }
    
    @JRubyMethod(name = "listener", frame = true, required = 0, optional = 1, rest = -1)
    public static IRubyObject method__4$RUBY$listener(final FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
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
        //    27: goto            43
        //    30: aload_0        
        //    31: aload_1        
        //    32: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    35: ldc             "default"
        //    37: invokevirtual   ruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    40: astore          9
        //    42: pop            
        //    43: aload_0        
        //    44: invokevirtual   ruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.getCallSite1:()Lorg/jruby/runtime/CallSite;
        //    47: aload_1        
        //    48: aload_2        
        //    49: aload_0        
        //    50: invokevirtual   ruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.getCallSite2:()Lorg/jruby/runtime/CallSite;
        //    53: aload_1        
        //    54: aload_2        
        //    55: aload_0        
        //    56: invokevirtual   ruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.getCallSite3:()Lorg/jruby/runtime/CallSite;
        //    59: aload_1        
        //    60: aload_2        
        //    61: aload_2        
        //    62: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: aload           sym
        //    67: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: aload_2        
        //    71: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  43     32      9     sym   Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__5$RUBY$ClassMethods(final FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "ClassMethods"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.getScope3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_1        
        //    28: aload_2        
        //    29: aload_0        
        //    30: ldc             "listeners"
        //    32: ldc             "method__6$RUBY$listeners"
        //    34: ldc             ",0,0,-1"
        //    36: iconst_0       
        //    37: ldc             "./lib//lister/runner/questionnaire/has_listener.rb"
        //    39: ldc             13
        //    41: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    44: ldc             "NONE"
        //    46: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: pop            
        //    50: aload_1        
        //    51: aload_2        
        //    52: aload_0        
        //    53: ldc             "listener"
        //    55: ldc_w           "method__7$RUBY$listener"
        //    58: ldc_w           "sym;blk,0,1,-1"
        //    61: iconst_m1      
        //    62: ldc             "./lib//lister/runner/questionnaire/has_listener.rb"
        //    64: ldc_w           16
        //    67: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    70: ldc_w           "osym;bblk"
        //    73: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: aload_1        
        //    77: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: goto            88
        //    83: aload_1        
        //    84: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    87: athrow         
        //    88: aload_1        
        //    89: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    92: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     80     83     88     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "listeners", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$listeners(final FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4 file_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@listener_classes") ? file_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.getByteList0() : null) == null) {
            rubyObject = file_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.setVariable0(threadContext.runtime, "@listener_classes", object, RubyHash.newHash(threadContext.runtime));
        }
        else if (!(rubyObject = file_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.getVariable0(threadContext.runtime, "@listener_classes", object)).isTrue()) {
            rubyObject = file_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.setVariable1(threadContext.runtime, "@listener_classes", object, RubyHash.newHash(threadContext.runtime));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "listener", frame = true, required = 0, optional = 1, rest = -1)
    public static IRubyObject method__7$RUBY$listener(final FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
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
        //    32: goto            52
        //    35: aload           5
        //    37: aload_0        
        //    38: aload_1        
        //    39: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    42: ldc             "default"
        //    44: invokevirtual   ruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    47: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: pop            
        //    51: pop            
        //    52: aload_1        
        //    53: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    56: aload           4
        //    58: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    61: aload           5
        //    63: swap           
        //    64: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: pop            
        //    68: aload_0        
        //    69: invokevirtual   ruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.getCallSite4:()Lorg/jruby/runtime/CallSite;
        //    72: aload_1        
        //    73: aload_2        
        //    74: aload_0        
        //    75: invokevirtual   ruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.getCallSite5:()Lorg/jruby/runtime/CallSite;
        //    78: aload_1        
        //    79: aload_2        
        //    80: aload_2        
        //    81: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    84: aload           locals
        //    86: aload_1        
        //    87: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    90: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    93: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    96: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   101: ifeq            107
        //   104: goto            184
        //   107: aload_0        
        //   108: invokevirtual   ruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.getCallSite6:()Lorg/jruby/runtime/CallSite;
        //   111: aload_1        
        //   112: aload_2        
        //   113: aload_2        
        //   114: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   117: dup            
        //   118: aload_2        
        //   119: aload_0        
        //   120: invokevirtual   ruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.getCallSite7:()Lorg/jruby/runtime/CallSite;
        //   123: aload_0        
        //   124: invokevirtual   ruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.getCallSite8:()Lorg/jruby/runtime/CallSite;
        //   127: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   130: aload           locals
        //   132: aload_1        
        //   133: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   136: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   139: aload_0        
        //   140: invokevirtual   ruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.getCallSite9:()Lorg/jruby/runtime/CallSite;
        //   143: aload_1        
        //   144: aload_2        
        //   145: aload_0        
        //   146: aload_1        
        //   147: ldc_w           "Class"
        //   150: invokevirtual   ruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.getConstant1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   153: aload_0        
        //   154: aload_1        
        //   155: ldc_w           "Listener"
        //   158: invokevirtual   ruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.getConstant2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   161: aload           locals
        //   163: aload_1        
        //   164: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   167: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   170: aload           4
        //   172: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.getBlockFromBlockPassBody:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/Block;
        //   175: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   178: aload_1        
        //   179: aload_2        
        //   180: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   183: pop            
        //   184: aload_0        
        //   185: bipush          10
        //   187: invokevirtual   ruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   190: aload_1        
        //   191: aload_2        
        //   192: aload_0        
        //   193: bipush          11
        //   195: invokevirtual   ruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   198: aload_1        
        //   199: aload_2        
        //   200: aload_2        
        //   201: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   204: aload           locals
        //   206: aload_1        
        //   207: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   210: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   213: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   216: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  68     149     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject module__5$RUBY$ClassMethods(final FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4 file_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__5$RUBY$ClassMethods(file_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_8$RUBY$Listener(final FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    10: ldc_w           "Listener"
        //    13: swap           
        //    14: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    17: dup            
        //    18: astore_2       
        //    19: aload_1        
        //    20: swap           
        //    21: aload_0        
        //    22: aload_1        
        //    23: ldc             ",0,0,-1"
        //    25: invokevirtual   ruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.getScope4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    28: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    31: aload_0        
        //    32: bipush          12
        //    34: invokevirtual   ruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    37: aload_1        
        //    38: aload_2        
        //    39: aload_2        
        //    40: aload_0        
        //    41: aload_1        
        //    42: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    45: ldc_w           "source"
        //    48: invokevirtual   ruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    51: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: pop            
        //    55: aload_1        
        //    56: aload_2        
        //    57: aload_0        
        //    58: ldc_w           "initialize"
        //    61: ldc_w           "method__9$RUBY$initialize"
        //    64: ldc_w           "src,1,0,-1"
        //    67: iconst_1       
        //    68: ldc             "./lib//lister/runner/questionnaire/has_listener.rb"
        //    70: ldc_w           26
        //    73: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    76: ldc_w           "qsrc"
        //    79: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: aload_1        
        //    83: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    86: goto            94
        //    89: aload_1        
        //    90: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    93: athrow         
        //    94: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  31     82     89     94     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "initialize", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$initialize(final FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4 file_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4, final ThreadContext threadContext, final IRubyObject object, final IRubyObject value, final Block block) {
        return file_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.setVariable2(threadContext.runtime, "@source", object, value);
    }
    
    public static IRubyObject class_8$RUBY$Listener(final FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4 file_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_8$RUBY$Listener(file_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__2$RUBY$HasListeners(final FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4 file_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__2$RUBY$HasListeners(file_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Questionnaire(final FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4 file_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Questionnaire(file_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4 file_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4, threadContext, rubyObject, block);
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
        final FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4 file_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4 = new FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4();
        final String string = FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.class.getClassLoader().getResource("ruby/jit/FILE_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.class").toString();
        file_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_58E2E16BAF7B50A316C9AA2FA51B99325FC65FD4.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
