// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.RubyHash;
import org.jruby.runtime.DynamicScope;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Arity;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80 extends AbstractScript
{
    public FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80() {
        this.filename = "./lib//lister/runner/questionnaire/question_data_model.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("autoload\uffffF\uffffautoload\uffffF\uffffnew\uffffN\uffffsend\uffffN\uffffset_field\uffffF\ufffffind\uffffN\ufffffields\uffffV\uffff==\uffffN\uffffname\uffffN\uffff<<\uffffN\ufffffields\uffffV\uffffnew\uffffN\uffffmap\uffffN\ufffffields\uffffV\uffffname\uffffN\uffffdefault\uffffN\uffff[]\uffffN\uffffdefault_pairs\uffffV\uffffmap\uffffN\ufffffields\uffffV\uffffname\uffffN\uffffrespond_to?\uffffF\uffffsend\uffffF\uffffcompact\uffffN\uffffnew\uffffN\uffffquestions_list\uffffV\uffffcreate\uffffN\uffffquestionnaire\uffffF\uffffeach_pair\uffffN\uffffsend\uffffF\uffffeach\uffffN\ufffffields\uffffN\uffffclass\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffto_s\uffffN\uffffname\uffffN\uffffsend\uffffF\uffffname\uffffN\uffff\u0004\t\u0000\u0000\t\u0000\u0000\u0001\u0002\u0005\u0000\u0000\u0005\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(3, "_question", this.getEncoding0());
        this.setByteList(1, "lister/runner/questionnaire/wizard", this.getEncoding0());
        this.setByteList(0, "lister/runner/questionnaire/questionnaire", this.getEncoding0());
        this.setByteList(4, "=", this.getEncoding0());
        this.setByteList(2, "instance-variable", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80 file_30906A1F5876CFCED79F2280C17B92FAB708FF80, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        module__0$RUBY$Lister(file_30906A1F5876CFCED79F2280C17B92FAB708FF80, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
        return class_2$RUBY$QuestionDataModel(file_30906A1F5876CFCED79F2280C17B92FAB708FF80, threadContext, rubyObject, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.module__1$RUBY$Questionnaire:(Lruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Questionnaire(final FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite0:()Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_2        
        //    34: aload_0        
        //    35: aload_1        
        //    36: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    39: ldc             "Questionnaire"
        //    41: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    44: aload_0        
        //    45: aload_1        
        //    46: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    49: bipush          32
        //    51: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getString0:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    54: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: pop            
        //    58: aload_0        
        //    59: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite1:()Lorg/jruby/runtime/CallSite;
        //    62: aload_1        
        //    63: aload_2        
        //    64: aload_2        
        //    65: aload_0        
        //    66: aload_1        
        //    67: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    70: ldc             "Wizard"
        //    72: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    75: aload_0        
        //    76: aload_1        
        //    77: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    80: bipush          32
        //    82: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getString1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    85: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: aload_1        
        //    89: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    92: goto            100
        //    95: aload_1        
        //    96: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    99: athrow         
        //   100: aload_1        
        //   101: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   104: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     92     95     100    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject module__1$RUBY$Questionnaire(final FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80 file_30906A1F5876CFCED79F2280C17B92FAB708FF80, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Questionnaire(file_30906A1F5876CFCED79F2280C17B92FAB708FF80, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80 file_30906A1F5876CFCED79F2280C17B92FAB708FF80, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_30906A1F5876CFCED79F2280C17B92FAB708FF80, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_2$RUBY$QuestionDataModel(final FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    10: ldc             "QuestionDataModel"
        //    12: swap           
        //    13: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    16: dup            
        //    17: astore_2       
        //    18: aload_1        
        //    19: swap           
        //    20: aload_0        
        //    21: aload_1        
        //    22: ldc             ",0,0,-1"
        //    24: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    27: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    30: aload_0        
        //    31: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite2:()Lorg/jruby/runtime/CallSite;
        //    34: aload_1        
        //    35: aload_2        
        //    36: aload_0        
        //    37: aload_1        
        //    38: ldc             "Struct"
        //    40: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: aload_0        
        //    44: aload_1        
        //    45: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    48: ldc             "name"
        //    50: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    53: aload_0        
        //    54: aload_1        
        //    55: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    58: ldc             "kind"
        //    60: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    63: aload_0        
        //    64: aload_1        
        //    65: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    68: ldc             "default"
        //    70: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    73: aload_0        
        //    74: aload_1        
        //    75: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    78: ldc             "accepted"
        //    80: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    83: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //    86: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: aload_1        
        //    90: ldc             "ColumnItem"
        //    92: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    95: pop            
        //    96: aload_0        
        //    97: aload_1        
        //    98: aload_2        
        //    99: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   102: invokestatic    ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.sclass_3$RUBY$__singleton__:(Lruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: pop            
        //   106: aload_1        
        //   107: aload_2        
        //   108: aload_0        
        //   109: ldc_w           "initialize"
        //   112: ldc_w           "method__12$RUBY$initialize"
        //   115: ldc_w           "hash,1,0,-1"
        //   118: iconst_1       
        //   119: ldc             "./lib//lister/runner/questionnaire/question_data_model.rb"
        //   121: ldc_w           58
        //   124: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   127: ldc_w           "qhash"
        //   130: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   133: pop            
        //   134: aload_1        
        //   135: aload_2        
        //   136: aload_0        
        //   137: ldc_w           "to_h"
        //   140: ldc_w           "method__13$RUBY$to_h"
        //   143: ldc_w           "h,0,0,-1"
        //   146: iconst_0       
        //   147: ldc             "./lib//lister/runner/questionnaire/question_data_model.rb"
        //   149: ldc_w           65
        //   152: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   155: ldc_w           "NONE"
        //   158: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
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
        //  30     161    168    173    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject sclass_3$RUBY$__singleton__(final FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //     4: aload_2        
        //     5: astore          8
        //     7: aload_2        
        //     8: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.getSingletonClass:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyClass;
        //    11: dup            
        //    12: astore_2       
        //    13: aload_1        
        //    14: swap           
        //    15: aload_0        
        //    16: aload_1        
        //    17: ldc             ",0,0,-1"
        //    19: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getScope3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    22: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    25: aload_1        
        //    26: aload_2        
        //    27: aload_0        
        //    28: ldc             "fields"
        //    30: ldc             "method__4$RUBY$fields"
        //    32: ldc             ",0,0,-1"
        //    34: iconst_0       
        //    35: ldc             "./lib//lister/runner/questionnaire/question_data_model.rb"
        //    37: ldc             12
        //    39: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    42: ldc_w           "NONE"
        //    45: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    48: pop            
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload_0        
        //    52: ldc_w           "field"
        //    55: ldc_w           "method__5$RUBY$field"
        //    58: ldc_w           "name;kind;default;accepted,1,3,-1"
        //    61: bipush          -2
        //    63: ldc             "./lib//lister/runner/questionnaire/question_data_model.rb"
        //    65: ldc_w           16
        //    68: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    71: ldc_w           "qname;okind;odefault;oaccepted"
        //    74: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: pop            
        //    78: aload_1        
        //    79: aload_2        
        //    80: aload_0        
        //    81: ldc_w           "set_field"
        //    84: ldc_w           "method__6$RUBY$set_field"
        //    87: ldc_w           "name;kind;default;accepted,4,0,-1"
        //    90: iconst_4       
        //    91: ldc             "./lib//lister/runner/questionnaire/question_data_model.rb"
        //    93: ldc_w           25
        //    96: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    99: ldc_w           "qname;qkind;qdefault;qaccepted"
        //   102: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: pop            
        //   106: aload_1        
        //   107: aload_2        
        //   108: aload_0        
        //   109: ldc_w           "default_pairs"
        //   112: ldc_w           "method__7$RUBY$default_pairs"
        //   115: ldc             ",0,0,-1"
        //   117: iconst_0       
        //   118: ldc             "./lib//lister/runner/questionnaire/question_data_model.rb"
        //   120: ldc_w           29
        //   123: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   126: ldc_w           "NONE"
        //   129: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   132: pop            
        //   133: aload_1        
        //   134: aload_2        
        //   135: aload_0        
        //   136: ldc_w           "default_values"
        //   139: ldc_w           "method__8$RUBY$default_values"
        //   142: ldc             ",0,0,-1"
        //   144: iconst_0       
        //   145: ldc             "./lib//lister/runner/questionnaire/question_data_model.rb"
        //   147: ldc_w           33
        //   150: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   153: ldc_w           "NONE"
        //   156: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   159: pop            
        //   160: aload_1        
        //   161: aload_2        
        //   162: aload_0        
        //   163: ldc_w           "questions_list"
        //   166: ldc_w           "method__9$RUBY$questions_list"
        //   169: ldc_w           "questions,0,0,-1"
        //   172: iconst_0       
        //   173: ldc             "./lib//lister/runner/questionnaire/question_data_model.rb"
        //   175: ldc_w           37
        //   178: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   181: ldc_w           "NONE"
        //   184: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   187: pop            
        //   188: aload_1        
        //   189: aload_2        
        //   190: aload_0        
        //   191: ldc_w           "questionnaire"
        //   194: ldc_w           "method__10$RUBY$questionnaire"
        //   197: ldc_w           "interpret,1,0,-1"
        //   200: iconst_1       
        //   201: ldc             "./lib//lister/runner/questionnaire/question_data_model.rb"
        //   203: ldc_w           49
        //   206: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   209: ldc_w           "qinterpret"
        //   212: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   215: pop            
        //   216: aload_1        
        //   217: aload_2        
        //   218: aload_0        
        //   219: ldc_w           "wizard"
        //   222: ldc_w           "method__11$RUBY$wizard"
        //   225: ldc_w           "interpret,1,0,-1"
        //   228: iconst_1       
        //   229: ldc             "./lib//lister/runner/questionnaire/question_data_model.rb"
        //   231: ldc_w           53
        //   234: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   237: ldc_w           "qinterpret"
        //   240: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   243: aload_1        
        //   244: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   247: goto            255
        //   250: aload_1        
        //   251: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   254: athrow         
        //   255: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  25     243    250    255    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "fields", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$fields(final FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80 file_30906A1F5876CFCED79F2280C17B92FAB708FF80, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@fields") ? file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getByteList2() : null) == null) {
            rubyObject = file_30906A1F5876CFCED79F2280C17B92FAB708FF80.setVariable0(threadContext.runtime, "@fields", object, threadContext.runtime.newArray());
        }
        else if (!(rubyObject = file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getVariable0(threadContext.runtime, "@fields", object)).isTrue()) {
            rubyObject = file_30906A1F5876CFCED79F2280C17B92FAB708FF80.setVariable1(threadContext.runtime, "@fields", object, threadContext.runtime.newArray());
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "field", frame = true, required = 1, optional = 3, rest = -1)
    public static IRubyObject method__5$RUBY$field(final FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
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
        //    12: iconst_4       
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
        //    32: ifnull          77
        //    35: aload           5
        //    37: swap           
        //    38: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: pop            
        //    42: aload_3        
        //    43: iconst_2       
        //    44: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.elementOrNull:([Lorg/jruby/runtime/builtin/IRubyObject;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: dup            
        //    48: ifnull          87
        //    51: aload           5
        //    53: swap           
        //    54: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: pop            
        //    58: aload_3        
        //    59: iconst_3       
        //    60: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.elementOrNull:([Lorg/jruby/runtime/builtin/IRubyObject;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: dup            
        //    64: ifnull          97
        //    67: aload           5
        //    69: swap           
        //    70: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: pop            
        //    74: goto            108
        //    77: aload           5
        //    79: aload_1        
        //    80: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    86: pop            
        //    87: aload           5
        //    89: aload_1        
        //    90: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    93: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    96: pop            
        //    97: aload           5
        //    99: aload_1        
        //   100: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   103: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   106: pop            
        //   107: pop            
        //   108: aload           locals
        //   110: aload_1        
        //   111: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   114: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   117: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   122: ifeq            208
        //   125: aload_0        
        //   126: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite3:()Lorg/jruby/runtime/CallSite;
        //   129: aload_1        
        //   130: aload_2        
        //   131: aload_2        
        //   132: aload_0        
        //   133: aload_1        
        //   134: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   137: ldc_w           "attr_accessor"
        //   140: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getSymbol6:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   143: aload           locals
        //   145: aload_1        
        //   146: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   149: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   152: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   155: pop            
        //   156: aload_0        
        //   157: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite4:()Lorg/jruby/runtime/CallSite;
        //   160: aload_1        
        //   161: aload_2        
        //   162: aload_2        
        //   163: aload           locals
        //   165: aload_1        
        //   166: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   169: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   172: aload           locals
        //   174: aload_1        
        //   175: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   178: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   181: aload           locals
        //   183: aload_1        
        //   184: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   187: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   190: aload           locals
        //   192: aload_1        
        //   193: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   196: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   199: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   202: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   205: goto            240
        //   208: aload_0        
        //   209: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite5:()Lorg/jruby/runtime/CallSite;
        //   212: aload_1        
        //   213: aload_2        
        //   214: aload_0        
        //   215: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite6:()Lorg/jruby/runtime/CallSite;
        //   218: aload_1        
        //   219: aload_2        
        //   220: aload_2        
        //   221: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   224: aload_1        
        //   225: aload_2        
        //   226: aload_0        
        //   227: aload_1        
        //   228: ldc_w           "block_0$RUBY$field,1,f,false,2,./lib//lister/runner/questionnaire/question_data_model.rb,21,true"
        //   231: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   234: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   237: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   240: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  108    133     5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_0$RUBY$field(final FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite7:()Lorg/jruby/runtime/CallSite;
        //    29: aload_1        
        //    30: aload_2        
        //    31: aload_0        
        //    32: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite8:()Lorg/jruby/runtime/CallSite;
        //    35: aload_1        
        //    36: aload_2        
        //    37: aload           f
        //    39: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    42: aload           5
        //    44: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    47: aload_1        
        //    48: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    54: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     33      9     f     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "set_field", frame = true, required = 4, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$set_field(final FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //     4: aload_3        
        //     5: iconst_4       
        //     6: iconst_4       
        //     7: invokestatic    org/jruby/runtime/Arity.raiseArgumentError:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;II)V
        //    10: aload_3        
        //    11: iconst_0       
        //    12: aaload         
        //    13: astore          9
        //    15: aload_3        
        //    16: iconst_1       
        //    17: aaload         
        //    18: astore          10
        //    20: aload_3        
        //    21: iconst_2       
        //    22: aaload         
        //    23: astore          11
        //    25: aload_3        
        //    26: iconst_3       
        //    27: aaload         
        //    28: astore          accepted
        //    30: aload_0        
        //    31: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite9:()Lorg/jruby/runtime/CallSite;
        //    34: aload_1        
        //    35: aload_2        
        //    36: aload_0        
        //    37: bipush          10
        //    39: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    42: aload_1        
        //    43: aload_2        
        //    44: aload_2        
        //    45: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    48: aload_0        
        //    49: bipush          11
        //    51: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    54: aload_1        
        //    55: aload_2        
        //    56: aload_0        
        //    57: aload_1        
        //    58: ldc             "ColumnItem"
        //    60: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getConstant1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: aload           name
        //    65: aload           kind
        //    67: aload           default
        //    69: aload           accepted
        //    71: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name      Signature
        //  -----  ------  ----  --------  ---------------------------------------
        //  30     51      9     name      Lorg/jruby/runtime/builtin/IRubyObject;
        //  30     51      10    kind      Lorg/jruby/runtime/builtin/IRubyObject;
        //  30     51      11    default   Lorg/jruby/runtime/builtin/IRubyObject;
        //  30     51      12    accepted  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "default_pairs", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$default_pairs(final FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80 file_30906A1F5876CFCED79F2280C17B92FAB708FF80, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite(12).callIter(threadContext, self, file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite(13).call(threadContext, self, self), RuntimeHelpers.createBlock(threadContext, self, file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getBlockBody1(threadContext, "block_1$RUBY$default_pairs,1,f,false,2,./lib//lister/runner/questionnaire/question_data_model.rb,30,true")));
    }
    
    public static IRubyObject block_1$RUBY$default_pairs(final FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    29: aload_0        
        //    30: bipush          14
        //    32: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    35: aload_1        
        //    36: aload_2        
        //    37: aload           f
        //    39: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    42: aload_0        
        //    43: bipush          15
        //    45: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    48: aload_1        
        //    49: aload_2        
        //    50: aload           f
        //    52: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //    58: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     34      9     f     Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "default_values", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$default_values(final FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80 file_30906A1F5876CFCED79F2280C17B92FAB708FF80, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        return file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite(16).call(context, rubyObject, file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getConstant2(context, "Hash"), file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite(17).call(context, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "questions_list", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$questions_list(final FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80 file_30906A1F5876CFCED79F2280C17B92FAB708FF80, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite(18).callIter(threadContext, self, file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite(19).call(threadContext, self, self), RuntimeHelpers.createBlock(threadContext, self, file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getBlockBody2(threadContext, "block_2$RUBY$questions_list,1,f;sym;question,false,2,./lib//lister/runner/questionnaire/question_data_model.rb,38,true"))));
        return file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite(23).call(threadContext, self, locals.getValueZeroDepthZeroOrNil(threadContext.nil));
    }
    
    public static IRubyObject block_2$RUBY$questions_list(final FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    37: aload_1        
        //    38: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    41: ldc_w           20
        //    44: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    47: aload_0        
        //    48: bipush          20
        //    50: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    53: aload_1        
        //    54: aload_2        
        //    55: aload           f
        //    57: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //    65: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    68: aload_0        
        //    69: aload_1        
        //    70: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    73: bipush          32
        //    75: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getString3:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    78: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    81: astore          sym
        //    83: aload_0        
        //    84: bipush          21
        //    86: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    89: aload_1        
        //    90: aload_2        
        //    91: aload_2        
        //    92: aload           sym
        //    94: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    97: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   102: ifeq            125
        //   105: aload_0        
        //   106: bipush          22
        //   108: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   111: aload_1        
        //   112: aload_2        
        //   113: aload_2        
        //   114: aload           sym
        //   116: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   119: dup            
        //   120: astore          question
        //   122: goto            129
        //   125: aload_1        
        //   126: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   129: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name      Signature
        //  -----  ------  ----  --------  ---------------------------------------
        //  37     93      9     f         Lorg/jruby/runtime/builtin/IRubyObject;
        //  37     93      10    sym       Lorg/jruby/runtime/builtin/IRubyObject;
        //  37     93      11    question  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "questionnaire", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__10$RUBY$questionnaire(final FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80 file_30906A1F5876CFCED79F2280C17B92FAB708FF80, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite(24).call(context, rubyObject, file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getConstantFrom5(RuntimeHelpers.checkIsModule(file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getConstantFrom4(RuntimeHelpers.checkIsModule(file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getConstant3(context, "Lister")), context, "Questionnaire")), context, "Questionnaire"), file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite(25).call(context, rubyObject, rubyObject), rubyObject2, RuntimeHelpers.constructHash(context.runtime, file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getSymbol7(context.runtime, "done"), file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getSymbol8(context.runtime, "add")));
    }
    
    @JRubyMethod(name = "wizard", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__11$RUBY$wizard(final FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80 file_30906A1F5876CFCED79F2280C17B92FAB708FF80, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite(26).call(context, rubyObject, file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getConstantFrom8(RuntimeHelpers.checkIsModule(file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getConstantFrom7(RuntimeHelpers.checkIsModule(file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getConstant6(context, "Lister")), context, "Questionnaire")), context, "Wizard"), file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite(27).call(context, rubyObject, rubyObject, rubyObject2));
    }
    
    public static IRubyObject sclass_3$RUBY$__singleton__(final FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80 file_30906A1F5876CFCED79F2280C17B92FAB708FF80, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return sclass_3$RUBY$__singleton__(file_30906A1F5876CFCED79F2280C17B92FAB708FF80, threadContext, rubyObject, block);
    }
    
    @JRubyMethod(name = "initialize", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__12$RUBY$initialize(final FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    15: bipush          28
        //    17: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    20: aload_1        
        //    21: aload_2        
        //    22: aload           locals
        //    24: aload_1        
        //    25: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    28: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_0        
        //    34: aload_1        
        //    35: ldc_w           "block_3$RUBY$initialize,2,k;v;setter,true,1,./lib//lister/runner/questionnaire/question_data_model.rb,59,true"
        //    38: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getBlockBody3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    41: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    44: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     34      5     locals  Lorg/jruby/runtime/DynamicScope;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject block_3$RUBY$initialize(final FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    34: aload_1        
        //    35: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    38: iconst_1       
        //    39: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.ensureMultipleAssignableRubyArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;Z)Lorg/jruby/RubyArray;
        //    42: astore          12
        //    44: aload           12
        //    46: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilZero:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: astore          9
        //    51: aload           12
        //    53: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilOne:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    56: astore          10
        //    58: aload           12
        //    60: pop            
        //    61: pop            
        //    62: aload_1        
        //    63: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    66: ldc_w           20
        //    69: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    72: aload           k
        //    74: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //    79: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    82: aload_0        
        //    83: aload_1        
        //    84: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    87: bipush          32
        //    89: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getString4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    92: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    95: astore          setter
        //    97: aload_0        
        //    98: bipush          29
        //   100: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   103: aload_1        
        //   104: aload_2        
        //   105: aload_2        
        //   106: aload           setter
        //   108: aload           v
        //   110: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   113: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  ---------------------------------------
        //  62     52      9     k       Lorg/jruby/runtime/builtin/IRubyObject;
        //  62     52      10    v       Lorg/jruby/runtime/builtin/IRubyObject;
        //  62     52      11    setter  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "to_h", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__13$RUBY$to_h(final FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80 file_30906A1F5876CFCED79F2280C17B92FAB708FF80, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(RubyHash.newHash(threadContext.runtime));
        file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite(30).callIter(threadContext, self, file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite(31).call(threadContext, self, file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite(32).call(threadContext, self, self)), RuntimeHelpers.createBlock(threadContext, self, file_30906A1F5876CFCED79F2280C17B92FAB708FF80.getBlockBody4(threadContext, "block_4$RUBY$to_h,1,field,false,2,./lib//lister/runner/questionnaire/question_data_model.rb,67,true")));
        return locals.getValueZeroDepthZeroOrNil(threadContext.nil);
    }
    
    public static IRubyObject block_4$RUBY$to_h(final FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    30: aload_1        
        //    31: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    34: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    37: dup            
        //    38: aload_2        
        //    39: aload_0        
        //    40: bipush          33
        //    42: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    45: aload_0        
        //    46: bipush          34
        //    48: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    51: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //    54: aload_0        
        //    55: bipush          35
        //    57: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    60: aload_1        
        //    61: aload_2        
        //    62: aload_0        
        //    63: bipush          36
        //    65: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    68: aload_1        
        //    69: aload_2        
        //    70: aload           field
        //    72: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    78: aload_0        
        //    79: bipush          37
        //    81: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    84: aload_1        
        //    85: aload_2        
        //    86: aload_2        
        //    87: aload_0        
        //    88: bipush          38
        //    90: invokevirtual   ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    93: aload_1        
        //    94: aload_2        
        //    95: aload           field
        //    97: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   100: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   103: aload_1        
        //   104: aload_2        
        //   105: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   108: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name   Signature
        //  -----  ------  ----  -----  ---------------------------------------
        //  25     84      9     field  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_2$RUBY$QuestionDataModel(final FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80 file_30906A1F5876CFCED79F2280C17B92FAB708FF80, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_2$RUBY$QuestionDataModel(file_30906A1F5876CFCED79F2280C17B92FAB708FF80, threadContext, rubyObject, block);
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
        final FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80 file_30906A1F5876CFCED79F2280C17B92FAB708FF80 = new FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80();
        final String string = FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.class.getClassLoader().getResource("ruby/jit/FILE_30906A1F5876CFCED79F2280C17B92FAB708FF80.class").toString();
        file_30906A1F5876CFCED79F2280C17B92FAB708FF80.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_30906A1F5876CFCED79F2280C17B92FAB708FF80.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
