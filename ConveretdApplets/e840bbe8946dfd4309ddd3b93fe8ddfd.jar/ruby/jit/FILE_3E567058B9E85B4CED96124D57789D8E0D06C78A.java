// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A extends AbstractScript
{
    public FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A() {
        this.filename = "./lib//lister/interpret.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("[]\uffffN\ufffflanguages\uffffV\ufffflanguage\uffffN\uffffclass\uffffN\uffffattr_reader\uffffF\uffffspeak!\uffffF\uffffspeak!\uffffF\uffffextend\uffffN\uffffget_language\uffffF\uffffadd_device\uffffV\uffffdelete_device\uffffV\uffff\u0004\u0001\u0000\u0000\u0003\u0000\u0000\u0000\u0001\u0000\u0000\u0000$\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(28, "TERMINER", this.getEncoding0());
        this.setByteList(26, "T\uffc3\uffa2ches de fond", this.getEncoding0());
        this.setByteList(30, "Ajouter", this.getEncoding0());
        this.setByteList(7, "Start", this.getEncoding0());
        this.setByteList(22, "Invalide", this.getEncoding0());
        this.setByteList(27, "Envoi des r\uffc3\uffa9sultats", this.getEncoding0());
        this.setByteList(32, "Pr\uffc3\uffa9c\uffc3\uffa9dent", this.getEncoding0());
        this.setByteList(2, "no such word: ", this.getEncoding0());
        this.setByteList(4, "Warning", this.getEncoding0());
        this.setByteList(35, "Pas d'aide", this.getEncoding0());
        this.setByteList(14, "Copy report URL in clipboard", this.getEncoding0());
        this.setByteList(25, "Aide", this.getEncoding0());
        this.setByteList(10, "Help", this.getEncoding0());
        this.setByteList(8, "OK", this.getEncoding0());
        this.setByteList(12, "Uploading measurements:", this.getEncoding0());
        this.setByteList(13, "Background measurements:", this.getEncoding0());
        this.setByteList(15, "Add device", this.getEncoding0());
        this.setByteList(31, "Enlever", this.getEncoding0());
        this.setByteList(23, "D\uffc3\uffa9marrer", this.getEncoding0());
        this.setByteList(5, "Invalid", this.getEncoding0());
        this.setByteList(29, "Copier l'URL de rapports de mesures dans le presse papier", this.getEncoding0());
        this.setByteList(34, "Commentaire", this.getEncoding0());
        this.setByteList(16, "Delete device", this.getEncoding0());
        this.setByteList(18, "Double-click here to add comment", this.getEncoding0());
        this.setByteList(6, "DONE", this.getEncoding0());
        this.setByteList(0, "English", this.getEncoding0());
        this.setByteList(24, "Suivant", this.getEncoding0());
        this.setByteList(9, "Next", this.getEncoding0());
        this.setByteList(17, "Select", this.getEncoding0());
        this.setByteList(19, "Missing help", this.getEncoding0());
        this.setByteList(1, "Fran\uffc3\uffa7ais", this.getEncoding0());
        this.setByteList(20, "<HTML><p>Cette fen\uffc3\uffaatre se fermera automatiquement quand les mesures\n         en t\uffc3\uffa2che de fond termineront et seront envoy\uffc3\uffa9es sur notre serveur.<br>\n         <br>\n         Votre rapport de mesure sera disponible d\uffc3\uffa8s que cette fen\uffc3\uffaatre se fermera.</p></HTML>", this.getEncoding0());
        this.setByteList(11, "Previous", this.getEncoding0());
        this.setByteList(3, "<HTML><p>This window will close automatically once measurements are uploaded. <br>\n        <br>\n        Your reports will be available as soon as the window closes.</p></HTML>", this.getEncoding0());
        this.setByteList(33, "S\uffc3\uffa9lectionner", this.getEncoding0());
        this.setByteList(21, "Attention", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return module__0$RUBY$Lister(file_3E567058B9E85B4CED96124D57789D8E0D06C78A, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    33: invokestatic    ruby/jit/FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A.class_1$RUBY$Interpret:(Lruby/jit/FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_1$RUBY$Interpret(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    10: ldc             "Interpret"
        //    12: swap           
        //    13: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    16: dup            
        //    17: astore_2       
        //    18: aload_1        
        //    19: swap           
        //    20: aload_0        
        //    21: aload_1        
        //    22: ldc             ",0,0,-1"
        //    24: invokevirtual   ruby/jit/FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    27: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    30: aload_1        
        //    31: aload_2        
        //    32: aload_2        
        //    33: aload_0        
        //    34: ldc             "languages"
        //    36: ldc             "method__2$RUBY$languages"
        //    38: ldc             ",0,0,-1"
        //    40: iconst_0       
        //    41: ldc             "./lib//lister/interpret.rb"
        //    43: ldc             3
        //    45: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    48: ldc             "NONE"
        //    50: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    53: pop            
        //    54: aload_1        
        //    55: aload_2        
        //    56: aload_2        
        //    57: aload_0        
        //    58: ldc             "language"
        //    60: ldc             "method__3$RUBY$language"
        //    62: ldc             "lang,1,0,-1"
        //    64: iconst_1       
        //    65: ldc             "./lib//lister/interpret.rb"
        //    67: ldc             8
        //    69: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    72: ldc             "qlang"
        //    74: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: pop            
        //    78: aload_1        
        //    79: aload_2        
        //    80: aload_0        
        //    81: ldc             "get_language"
        //    83: ldc             "method__4$RUBY$get_language"
        //    85: ldc             "lang,1,0,-1"
        //    87: iconst_1       
        //    88: ldc             "./lib//lister/interpret.rb"
        //    90: ldc             12
        //    92: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    95: ldc             "qlang"
        //    97: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   100: pop            
        //   101: aload_0        
        //   102: invokevirtual   ruby/jit/FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A.getCallSite4:()Lorg/jruby/runtime/CallSite;
        //   105: aload_1        
        //   106: aload_2        
        //   107: aload_2        
        //   108: aload_0        
        //   109: aload_1        
        //   110: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   113: ldc             "current_language"
        //   115: invokevirtual   ruby/jit/FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   118: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   121: pop            
        //   122: aload_1        
        //   123: aload_2        
        //   124: aload_0        
        //   125: ldc             "initialize"
        //   127: ldc             "method__5$RUBY$initialize"
        //   129: ldc             "lang,1,0,-1"
        //   131: iconst_1       
        //   132: ldc             "./lib//lister/interpret.rb"
        //   134: ldc             18
        //   136: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   139: ldc             "qlang"
        //   141: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   144: pop            
        //   145: aload_1        
        //   146: aload_2        
        //   147: aload_0        
        //   148: ldc_w           "method_missing"
        //   151: ldc_w           "method__6$RUBY$method_missing"
        //   154: ldc_w           "word;args;blk,1,0,1"
        //   157: bipush          -2
        //   159: ldc             "./lib//lister/interpret.rb"
        //   161: ldc_w           23
        //   164: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   167: ldc_w           "qword;rargs;bblk"
        //   170: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   173: pop            
        //   174: aload_1        
        //   175: aload_2        
        //   176: aload_0        
        //   177: ldc_w           "speak!"
        //   180: ldc_w           "method__7$RUBY$speak_b_"
        //   183: ldc             "lang,1,0,-1"
        //   185: iconst_1       
        //   186: ldc             "./lib//lister/interpret.rb"
        //   188: ldc_w           27
        //   191: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   194: ldc             "qlang"
        //   196: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   199: pop            
        //   200: aload_0        
        //   201: aload_1        
        //   202: aload_2        
        //   203: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //   206: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   209: invokestatic    ruby/jit/FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A.module__8$RUBY$English:(Lruby/jit/FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   212: pop            
        //   213: aload_0        
        //   214: aload_1        
        //   215: aload_2        
        //   216: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //   219: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   222: invokestatic    ruby/jit/FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A.module__28$RUBY$Francais:(Lruby/jit/FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   225: aload_1        
        //   226: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   229: goto            237
        //   232: aload_1        
        //   233: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   236: athrow         
        //   237: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  30     225    232    237    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "languages", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__2$RUBY$languages(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return RuntimeHelpers.constructHash(threadContext.runtime, file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString0(threadContext.runtime, 32), file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getConstant0(threadContext, "English"), file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString1(threadContext.runtime, 64), file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getConstant1(threadContext, "Francais"));
    }
    
    @JRubyMethod(name = "language", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$language(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    15: invokevirtual   ruby/jit/FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A.getCallSite0:()Lorg/jruby/runtime/CallSite;
        //    18: aload_1        
        //    19: aload_2        
        //    20: aload_0        
        //    21: invokevirtual   ruby/jit/FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A.getCallSite1:()Lorg/jruby/runtime/CallSite;
        //    24: aload_1        
        //    25: aload_2        
        //    26: aload_2        
        //    27: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    30: aload           locals
        //    32: aload_1        
        //    33: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    36: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    42: dup            
        //    43: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    48: ifne            59
        //    51: pop            
        //    52: aload_0        
        //    53: aload_1        
        //    54: ldc             "English"
        //    56: invokevirtual   ruby/jit/FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A.getConstant2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    59: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     46      5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    @JRubyMethod(name = "get_language", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$get_language(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getCallSite2().call(threadContext, rubyObject, file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getCallSite3().call(threadContext, rubyObject, rubyObject), rubyObject2);
    }
    
    @JRubyMethod(name = "initialize", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$initialize(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getCallSite5().call(threadContext, rubyObject, rubyObject, file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString0(threadContext.runtime, 32));
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getCallSite6().call(threadContext, rubyObject, rubyObject, rubyObject2);
    }
    
    @JRubyMethod(name = "method_missing", frame = true, required = 1, optional = 0, rest = 1)
    public static IRubyObject method__6$RUBY$method_missing(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject[] p3, final Block p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     4: dup            
        //     5: astore          10
        //     7: astore          11
        //     9: aload_1        
        //    10: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    13: aload_3        
        //    14: iconst_1       
        //    15: iconst_m1      
        //    16: invokestatic    org/jruby/runtime/Arity.raiseArgumentError:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;II)V
        //    19: aload_3        
        //    20: iconst_0       
        //    21: aaload         
        //    22: astore          9
        //    24: aload_3        
        //    25: aload_1        
        //    26: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    29: iconst_1       
        //    30: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createSubarray:([Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;I)Lorg/jruby/RubyArray;
        //    33: astore          10
        //    35: aload_1        
        //    36: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    39: aload           4
        //    41: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.processBlockArgument:(Lorg/jruby/Ruby;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    44: astore          blk
        //    46: aload_1        
        //    47: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    50: ldc             20
        //    52: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    55: aload_0        
        //    56: aload_1        
        //    57: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    60: bipush          32
        //    62: invokevirtual   ruby/jit/FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString2:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    65: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    68: aload           word
        //    70: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //    75: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    78: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  46     33      9     word  Lorg/jruby/runtime/builtin/IRubyObject;
        //  46     33      10    args  Lorg/jruby/runtime/builtin/IRubyObject;
        //  46     33      11    blk   Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "speak!", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$speak_b_(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject object, final IRubyObject value, final Block block) {
        file_3E567058B9E85B4CED96124D57789D8E0D06C78A.setVariable0(threadContext.runtime, "@current_language", object, value);
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getCallSite7().call(threadContext, object, object, file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getCallSite8().call(threadContext, object, object, value));
    }
    
    public static IRubyObject module__8$RUBY$English(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "English"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_1        
        //    28: aload_2        
        //    29: aload_0        
        //    30: ldc_w           "wait"
        //    33: ldc_w           "method__9$RUBY$wait"
        //    36: ldc             ",0,0,-1"
        //    38: iconst_0       
        //    39: ldc             "./lib//lister/interpret.rb"
        //    41: ldc_w           33
        //    44: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    47: ldc             "NONE"
        //    49: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: pop            
        //    53: aload_1        
        //    54: aload_2        
        //    55: aload_0        
        //    56: ldc_w           "warning"
        //    59: ldc_w           "method__10$RUBY$warning"
        //    62: ldc             ",0,0,-1"
        //    64: iconst_0       
        //    65: ldc             "./lib//lister/interpret.rb"
        //    67: ldc_w           39
        //    70: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    73: ldc             "NONE"
        //    75: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    78: pop            
        //    79: aload_1        
        //    80: aload_2        
        //    81: aload_0        
        //    82: ldc_w           "invalid"
        //    85: ldc_w           "method__11$RUBY$invalid"
        //    88: ldc             ",0,0,-1"
        //    90: iconst_0       
        //    91: ldc             "./lib//lister/interpret.rb"
        //    93: ldc_w           43
        //    96: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    99: ldc             "NONE"
        //   101: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   104: pop            
        //   105: aload_1        
        //   106: aload_2        
        //   107: aload_0        
        //   108: ldc_w           "done"
        //   111: ldc_w           "method__12$RUBY$done"
        //   114: ldc             ",0,0,-1"
        //   116: iconst_0       
        //   117: ldc             "./lib//lister/interpret.rb"
        //   119: ldc_w           47
        //   122: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   125: ldc             "NONE"
        //   127: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   130: pop            
        //   131: aload_1        
        //   132: aload_2        
        //   133: aload_0        
        //   134: ldc_w           "start"
        //   137: ldc_w           "method__13$RUBY$start"
        //   140: ldc             ",0,0,-1"
        //   142: iconst_0       
        //   143: ldc             "./lib//lister/interpret.rb"
        //   145: ldc_w           51
        //   148: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   151: ldc             "NONE"
        //   153: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   156: pop            
        //   157: aload_1        
        //   158: aload_2        
        //   159: aload_0        
        //   160: ldc_w           "ok"
        //   163: ldc_w           "method__14$RUBY$ok"
        //   166: ldc             ",0,0,-1"
        //   168: iconst_0       
        //   169: ldc             "./lib//lister/interpret.rb"
        //   171: ldc_w           55
        //   174: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   177: ldc             "NONE"
        //   179: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   182: pop            
        //   183: aload_1        
        //   184: aload_2        
        //   185: aload_0        
        //   186: ldc_w           "next"
        //   189: ldc_w           "method__15$RUBY$next"
        //   192: ldc             ",0,0,-1"
        //   194: iconst_0       
        //   195: ldc             "./lib//lister/interpret.rb"
        //   197: ldc_w           59
        //   200: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   203: ldc             "NONE"
        //   205: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   208: pop            
        //   209: aload_1        
        //   210: aload_2        
        //   211: aload_0        
        //   212: ldc_w           "help"
        //   215: ldc_w           "method__16$RUBY$help"
        //   218: ldc             ",0,0,-1"
        //   220: iconst_0       
        //   221: ldc             "./lib//lister/interpret.rb"
        //   223: ldc_w           63
        //   226: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   229: ldc             "NONE"
        //   231: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   234: pop            
        //   235: aload_1        
        //   236: aload_2        
        //   237: aload_0        
        //   238: ldc_w           "previous"
        //   241: ldc_w           "method__17$RUBY$previous"
        //   244: ldc             ",0,0,-1"
        //   246: iconst_0       
        //   247: ldc             "./lib//lister/interpret.rb"
        //   249: ldc_w           67
        //   252: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   255: ldc             "NONE"
        //   257: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   260: pop            
        //   261: aload_1        
        //   262: aload_2        
        //   263: aload_0        
        //   264: ldc_w           "bkg_upload"
        //   267: ldc_w           "method__18$RUBY$bkg_upload"
        //   270: ldc             ",0,0,-1"
        //   272: iconst_0       
        //   273: ldc             "./lib//lister/interpret.rb"
        //   275: ldc_w           71
        //   278: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   281: ldc             "NONE"
        //   283: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   286: pop            
        //   287: aload_1        
        //   288: aload_2        
        //   289: aload_0        
        //   290: ldc_w           "bkg_meas"
        //   293: ldc_w           "method__19$RUBY$bkg_meas"
        //   296: ldc             ",0,0,-1"
        //   298: iconst_0       
        //   299: ldc             "./lib//lister/interpret.rb"
        //   301: ldc_w           75
        //   304: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   307: ldc             "NONE"
        //   309: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   312: pop            
        //   313: aload_1        
        //   314: aload_2        
        //   315: aload_0        
        //   316: ldc_w           "paste_url"
        //   319: ldc_w           "method__20$RUBY$paste_url"
        //   322: ldc             ",0,0,-1"
        //   324: iconst_0       
        //   325: ldc             "./lib//lister/interpret.rb"
        //   327: ldc_w           79
        //   330: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   333: ldc             "NONE"
        //   335: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   338: pop            
        //   339: aload_1        
        //   340: aload_2        
        //   341: aload_0        
        //   342: ldc_w           "add"
        //   345: ldc_w           "method__21$RUBY$add"
        //   348: ldc             ",0,0,-1"
        //   350: iconst_0       
        //   351: ldc             "./lib//lister/interpret.rb"
        //   353: ldc_w           83
        //   356: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   359: ldc             "NONE"
        //   361: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   364: pop            
        //   365: aload_1        
        //   366: aload_2        
        //   367: aload_0        
        //   368: ldc_w           "add_device"
        //   371: ldc_w           "method__22$RUBY$add_device"
        //   374: ldc             ",0,0,-1"
        //   376: iconst_0       
        //   377: ldc             "./lib//lister/interpret.rb"
        //   379: ldc_w           87
        //   382: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   385: ldc             "NONE"
        //   387: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   390: pop            
        //   391: aload_1        
        //   392: aload_2        
        //   393: aload_0        
        //   394: ldc_w           "delete"
        //   397: ldc_w           "method__23$RUBY$delete"
        //   400: ldc             ",0,0,-1"
        //   402: iconst_0       
        //   403: ldc             "./lib//lister/interpret.rb"
        //   405: ldc_w           91
        //   408: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   411: ldc             "NONE"
        //   413: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   416: pop            
        //   417: aload_1        
        //   418: aload_2        
        //   419: aload_0        
        //   420: ldc_w           "delete_device"
        //   423: ldc_w           "method__24$RUBY$delete_device"
        //   426: ldc             ",0,0,-1"
        //   428: iconst_0       
        //   429: ldc             "./lib//lister/interpret.rb"
        //   431: ldc_w           95
        //   434: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   437: ldc             "NONE"
        //   439: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   442: pop            
        //   443: aload_1        
        //   444: aload_2        
        //   445: aload_0        
        //   446: ldc_w           "please_select_item"
        //   449: ldc_w           "method__25$RUBY$please_select_item"
        //   452: ldc             ",0,0,-1"
        //   454: iconst_0       
        //   455: ldc             "./lib//lister/interpret.rb"
        //   457: ldc_w           99
        //   460: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   463: ldc             "NONE"
        //   465: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   468: pop            
        //   469: aload_1        
        //   470: aload_2        
        //   471: aload_0        
        //   472: ldc_w           "please_add_comment"
        //   475: ldc_w           "method__26$RUBY$please_add_comment"
        //   478: ldc             ",0,0,-1"
        //   480: iconst_0       
        //   481: ldc             "./lib//lister/interpret.rb"
        //   483: ldc_w           103
        //   486: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   489: ldc             "NONE"
        //   491: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   494: pop            
        //   495: aload_1        
        //   496: aload_2        
        //   497: aload_0        
        //   498: ldc_w           "no_help"
        //   501: ldc_w           "method__27$RUBY$no_help"
        //   504: ldc             ",0,0,-1"
        //   506: iconst_0       
        //   507: ldc             "./lib//lister/interpret.rb"
        //   509: ldc_w           107
        //   512: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   515: ldc             "NONE"
        //   517: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   520: aload_1        
        //   521: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   524: goto            532
        //   527: aload_1        
        //   528: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   531: athrow         
        //   532: aload_1        
        //   533: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   536: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     524    527    532    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "wait", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$wait(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString3(threadContext.runtime, 32);
    }
    
    @JRubyMethod(name = "warning", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__10$RUBY$warning(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString4(threadContext.runtime, 32);
    }
    
    @JRubyMethod(name = "invalid", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__11$RUBY$invalid(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString5(threadContext.runtime, 32);
    }
    
    @JRubyMethod(name = "done", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__12$RUBY$done(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString6(threadContext.runtime, 32);
    }
    
    @JRubyMethod(name = "start", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__13$RUBY$start(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString7(threadContext.runtime, 32);
    }
    
    @JRubyMethod(name = "ok", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__14$RUBY$ok(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString8(threadContext.runtime, 32);
    }
    
    @JRubyMethod(name = "next", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__15$RUBY$next(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString9(threadContext.runtime, 32);
    }
    
    @JRubyMethod(name = "help", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__16$RUBY$help(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString(threadContext.runtime, 10, 32);
    }
    
    @JRubyMethod(name = "previous", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__17$RUBY$previous(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString(threadContext.runtime, 11, 32);
    }
    
    @JRubyMethod(name = "bkg_upload", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__18$RUBY$bkg_upload(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString(threadContext.runtime, 12, 32);
    }
    
    @JRubyMethod(name = "bkg_meas", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__19$RUBY$bkg_meas(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString(threadContext.runtime, 13, 32);
    }
    
    @JRubyMethod(name = "paste_url", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__20$RUBY$paste_url(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString(threadContext.runtime, 14, 32);
    }
    
    @JRubyMethod(name = "add", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__21$RUBY$add(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getCallSite9().call(threadContext, rubyObject, rubyObject);
    }
    
    @JRubyMethod(name = "add_device", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__22$RUBY$add_device(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString(threadContext.runtime, 15, 32);
    }
    
    @JRubyMethod(name = "delete", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__23$RUBY$delete(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getCallSite(10).call(threadContext, rubyObject, rubyObject);
    }
    
    @JRubyMethod(name = "delete_device", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__24$RUBY$delete_device(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString(threadContext.runtime, 16, 32);
    }
    
    @JRubyMethod(name = "please_select_item", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__25$RUBY$please_select_item(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString(threadContext.runtime, 17, 32);
    }
    
    @JRubyMethod(name = "please_add_comment", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__26$RUBY$please_add_comment(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString(threadContext.runtime, 18, 32);
    }
    
    @JRubyMethod(name = "no_help", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__27$RUBY$no_help(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString(threadContext.runtime, 19, 32);
    }
    
    public static IRubyObject module__8$RUBY$English(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__8$RUBY$English(file_3E567058B9E85B4CED96124D57789D8E0D06C78A, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__28$RUBY$Francais(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Francais"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A.getScope3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_1        
        //    28: aload_2        
        //    29: aload_0        
        //    30: ldc_w           "wait"
        //    33: ldc_w           "method__29$RUBY$wait"
        //    36: ldc             ",0,0,-1"
        //    38: iconst_0       
        //    39: ldc             "./lib//lister/interpret.rb"
        //    41: ldc_w           113
        //    44: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    47: ldc             "NONE"
        //    49: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: pop            
        //    53: aload_1        
        //    54: aload_2        
        //    55: aload_0        
        //    56: ldc_w           "warning"
        //    59: ldc_w           "method__30$RUBY$warning"
        //    62: ldc             ",0,0,-1"
        //    64: iconst_0       
        //    65: ldc             "./lib//lister/interpret.rb"
        //    67: ldc_w           120
        //    70: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    73: ldc             "NONE"
        //    75: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    78: pop            
        //    79: aload_1        
        //    80: aload_2        
        //    81: aload_0        
        //    82: ldc_w           "invalid"
        //    85: ldc_w           "method__31$RUBY$invalid"
        //    88: ldc             ",0,0,-1"
        //    90: iconst_0       
        //    91: ldc             "./lib//lister/interpret.rb"
        //    93: ldc_w           124
        //    96: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    99: ldc             "NONE"
        //   101: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   104: pop            
        //   105: aload_1        
        //   106: aload_2        
        //   107: aload_0        
        //   108: ldc_w           "start"
        //   111: ldc_w           "method__32$RUBY$start"
        //   114: ldc             ",0,0,-1"
        //   116: iconst_0       
        //   117: ldc             "./lib//lister/interpret.rb"
        //   119: ldc_w           128
        //   122: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   125: ldc             "NONE"
        //   127: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   130: pop            
        //   131: aload_1        
        //   132: aload_2        
        //   133: aload_0        
        //   134: ldc_w           "ok"
        //   137: ldc_w           "method__33$RUBY$ok"
        //   140: ldc             ",0,0,-1"
        //   142: iconst_0       
        //   143: ldc             "./lib//lister/interpret.rb"
        //   145: ldc_w           132
        //   148: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   151: ldc             "NONE"
        //   153: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   156: pop            
        //   157: aload_1        
        //   158: aload_2        
        //   159: aload_0        
        //   160: ldc_w           "next"
        //   163: ldc_w           "method__34$RUBY$next"
        //   166: ldc             ",0,0,-1"
        //   168: iconst_0       
        //   169: ldc             "./lib//lister/interpret.rb"
        //   171: ldc_w           136
        //   174: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   177: ldc             "NONE"
        //   179: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   182: pop            
        //   183: aload_1        
        //   184: aload_2        
        //   185: aload_0        
        //   186: ldc_w           "help"
        //   189: ldc_w           "method__35$RUBY$help"
        //   192: ldc             ",0,0,-1"
        //   194: iconst_0       
        //   195: ldc             "./lib//lister/interpret.rb"
        //   197: ldc_w           140
        //   200: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   203: ldc             "NONE"
        //   205: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   208: pop            
        //   209: aload_1        
        //   210: aload_2        
        //   211: aload_0        
        //   212: ldc_w           "bkg_meas"
        //   215: ldc_w           "method__36$RUBY$bkg_meas"
        //   218: ldc             ",0,0,-1"
        //   220: iconst_0       
        //   221: ldc             "./lib//lister/interpret.rb"
        //   223: ldc_w           144
        //   226: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   229: ldc             "NONE"
        //   231: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   234: pop            
        //   235: aload_1        
        //   236: aload_2        
        //   237: aload_0        
        //   238: ldc_w           "bkg_upload"
        //   241: ldc_w           "method__37$RUBY$bkg_upload"
        //   244: ldc             ",0,0,-1"
        //   246: iconst_0       
        //   247: ldc             "./lib//lister/interpret.rb"
        //   249: ldc_w           148
        //   252: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   255: ldc             "NONE"
        //   257: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   260: pop            
        //   261: aload_1        
        //   262: aload_2        
        //   263: aload_0        
        //   264: ldc_w           "done"
        //   267: ldc_w           "method__38$RUBY$done"
        //   270: ldc             ",0,0,-1"
        //   272: iconst_0       
        //   273: ldc             "./lib//lister/interpret.rb"
        //   275: ldc_w           152
        //   278: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   281: ldc             "NONE"
        //   283: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   286: pop            
        //   287: aload_1        
        //   288: aload_2        
        //   289: aload_0        
        //   290: ldc_w           "paste_url"
        //   293: ldc_w           "method__39$RUBY$paste_url"
        //   296: ldc             ",0,0,-1"
        //   298: iconst_0       
        //   299: ldc             "./lib//lister/interpret.rb"
        //   301: ldc_w           156
        //   304: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   307: ldc             "NONE"
        //   309: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   312: pop            
        //   313: aload_1        
        //   314: aload_2        
        //   315: aload_0        
        //   316: ldc_w           "add"
        //   319: ldc_w           "method__40$RUBY$add"
        //   322: ldc             ",0,0,-1"
        //   324: iconst_0       
        //   325: ldc             "./lib//lister/interpret.rb"
        //   327: ldc_w           160
        //   330: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   333: ldc             "NONE"
        //   335: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   338: pop            
        //   339: aload_1        
        //   340: aload_2        
        //   341: aload_0        
        //   342: ldc_w           "delete"
        //   345: ldc_w           "method__41$RUBY$delete"
        //   348: ldc             ",0,0,-1"
        //   350: iconst_0       
        //   351: ldc             "./lib//lister/interpret.rb"
        //   353: ldc_w           164
        //   356: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   359: ldc             "NONE"
        //   361: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   364: pop            
        //   365: aload_1        
        //   366: aload_2        
        //   367: aload_0        
        //   368: ldc_w           "previous"
        //   371: ldc_w           "method__42$RUBY$previous"
        //   374: ldc             ",0,0,-1"
        //   376: iconst_0       
        //   377: ldc             "./lib//lister/interpret.rb"
        //   379: ldc_w           168
        //   382: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   385: ldc             "NONE"
        //   387: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   390: pop            
        //   391: aload_1        
        //   392: aload_2        
        //   393: aload_0        
        //   394: ldc_w           "please_select_item"
        //   397: ldc_w           "method__43$RUBY$please_select_item"
        //   400: ldc             ",0,0,-1"
        //   402: iconst_0       
        //   403: ldc             "./lib//lister/interpret.rb"
        //   405: ldc_w           172
        //   408: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   411: ldc             "NONE"
        //   413: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   416: pop            
        //   417: aload_1        
        //   418: aload_2        
        //   419: aload_0        
        //   420: ldc_w           "please_add_comment"
        //   423: ldc_w           "method__44$RUBY$please_add_comment"
        //   426: ldc             ",0,0,-1"
        //   428: iconst_0       
        //   429: ldc             "./lib//lister/interpret.rb"
        //   431: ldc_w           176
        //   434: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   437: ldc             "NONE"
        //   439: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   442: pop            
        //   443: aload_1        
        //   444: aload_2        
        //   445: aload_0        
        //   446: ldc_w           "no_help"
        //   449: ldc_w           "method__45$RUBY$no_help"
        //   452: ldc             ",0,0,-1"
        //   454: iconst_0       
        //   455: ldc             "./lib//lister/interpret.rb"
        //   457: ldc_w           180
        //   460: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   463: ldc             "NONE"
        //   465: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   468: aload_1        
        //   469: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   472: goto            480
        //   475: aload_1        
        //   476: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   479: athrow         
        //   480: aload_1        
        //   481: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   484: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     472    475    480    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "wait", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__29$RUBY$wait(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString(threadContext.runtime, 20, 64);
    }
    
    @JRubyMethod(name = "warning", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__30$RUBY$warning(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString(threadContext.runtime, 21, 32);
    }
    
    @JRubyMethod(name = "invalid", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__31$RUBY$invalid(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString(threadContext.runtime, 22, 32);
    }
    
    @JRubyMethod(name = "start", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__32$RUBY$start(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString(threadContext.runtime, 23, 64);
    }
    
    @JRubyMethod(name = "ok", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__33$RUBY$ok(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString8(threadContext.runtime, 32);
    }
    
    @JRubyMethod(name = "next", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__34$RUBY$next(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString(threadContext.runtime, 24, 32);
    }
    
    @JRubyMethod(name = "help", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__35$RUBY$help(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString(threadContext.runtime, 25, 32);
    }
    
    @JRubyMethod(name = "bkg_meas", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__36$RUBY$bkg_meas(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString(threadContext.runtime, 26, 64);
    }
    
    @JRubyMethod(name = "bkg_upload", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__37$RUBY$bkg_upload(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString(threadContext.runtime, 27, 64);
    }
    
    @JRubyMethod(name = "done", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__38$RUBY$done(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString(threadContext.runtime, 28, 32);
    }
    
    @JRubyMethod(name = "paste_url", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__39$RUBY$paste_url(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString(threadContext.runtime, 29, 32);
    }
    
    @JRubyMethod(name = "add", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__40$RUBY$add(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString(threadContext.runtime, 30, 32);
    }
    
    @JRubyMethod(name = "delete", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__41$RUBY$delete(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString(threadContext.runtime, 31, 32);
    }
    
    @JRubyMethod(name = "previous", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__42$RUBY$previous(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString(threadContext.runtime, 32, 64);
    }
    
    @JRubyMethod(name = "please_select_item", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__43$RUBY$please_select_item(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString(threadContext.runtime, 33, 64);
    }
    
    @JRubyMethod(name = "please_add_comment", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__44$RUBY$please_add_comment(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString(threadContext.runtime, 34, 32);
    }
    
    @JRubyMethod(name = "no_help", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__45$RUBY$no_help(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3E567058B9E85B4CED96124D57789D8E0D06C78A.getString(threadContext.runtime, 35, 32);
    }
    
    public static IRubyObject module__28$RUBY$Francais(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__28$RUBY$Francais(file_3E567058B9E85B4CED96124D57789D8E0D06C78A, threadContext, rubyObject, block);
    }
    
    public static IRubyObject class_1$RUBY$Interpret(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_1$RUBY$Interpret(file_3E567058B9E85B4CED96124D57789D8E0D06C78A, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_3E567058B9E85B4CED96124D57789D8E0D06C78A, threadContext, rubyObject, block);
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
        final FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A file_3E567058B9E85B4CED96124D57789D8E0D06C78A = new FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A();
        final String string = FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A.class.getClassLoader().getResource("ruby/jit/FILE_3E567058B9E85B4CED96124D57789D8E0D06C78A.class").toString();
        file_3E567058B9E85B4CED96124D57789D8E0D06C78A.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_3E567058B9E85B4CED96124D57789D8E0D06C78A.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
