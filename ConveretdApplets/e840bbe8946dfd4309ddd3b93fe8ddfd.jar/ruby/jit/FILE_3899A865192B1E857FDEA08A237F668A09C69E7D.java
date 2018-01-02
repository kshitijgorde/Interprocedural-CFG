// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.runtime.DynamicScope;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.exceptions.JumpException;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_3899A865192B1E857FDEA08A237F668A09C69E7D extends AbstractScript
{
    public FILE_3899A865192B1E857FDEA08A237F668A09C69E7D() {
        this.filename = "./lib//lister/toolbar.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude_class\uffffF\uffffinclude\uffffF\uffffinclude\uffffF\uffffattr_reader\uffffF\uffffaction_command\uffffN\uffffrespond_to?\uffffF\uffffsend\uffffF\uffffon_error\uffffF\uffffaction_command\uffffN\uffffdisplay\uffffN\uffffrunner\uffffV\uffffmessage\uffffN\uffffsystem_clipboard\uffffN\uffffdefault_toolkit\uffffN\uffffnew\uffffN\uffffurl\uffffN\uffffrunner\uffffV\uffffset_contents\uffffN\uffffraise\uffffF\uffffattr_accessor\uffffF\uffffsuper\uffffS\uffffadd\uffffF\uffffhnp\uffffV\ufffflanguage\uffffN\uffffrunner\uffffV\uffffnew\uffffN\ufffflanguage\uffffV\uffffnew\uffffN\uffffadd\uffffN\uffffpaste\uffffV\uffffinit_hnp\uffffV\uffffinit_hnp\uffffV\uffffpaste_url\uffffN\uffffinterpret\uffffV\uffffnew\uffffN\uffffpaste_url_text\uffffV\uffffadd_action_listener\uffffN\ufffflistener\uffffV\uffffaction_command=\uffffN\uffffaction_command=\uffffV\uffffinit_paste\uffffV\uffffinit_paste\uffffV\uffffnew\uffffN\uffffrunner\uffffV\uffffinit_listener\uffffV\uffffinit_listener\uffffV\uffff\u0003\u0001\u0000\u0000\u000b\u0000\u0000\u0003\b\u0000\u0000\u0000\u000f\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(6, "java.awt.datatransfer.ClipboardOwner", this.getEncoding0());
        this.setByteList(9, "on_", this.getEncoding0());
        this.setByteList(2, "java.awt.Menu", this.getEncoding0());
        this.setByteList(3, "java.awt.MenuItem", this.getEncoding0());
        this.setByteList(5, "java.awt.event.ActionListener", this.getEncoding0());
        this.setByteList(7, "java.awt.datatransfer.DataFlavor", this.getEncoding0());
        this.setByteList(12, "HNP", this.getEncoding0());
        this.setByteList(1, "java.awt.MenuBar", this.getEncoding0());
        this.setByteList(11, "No clipboard visible on this machine", this.getEncoding0());
        this.setByteList(0, "lister/interpret", this.getEncoding0());
        this.setByteList(4, "java.awt.Toolkit", this.getEncoding0());
        this.setByteList(10, "impossible action: ", this.getEncoding0());
        this.setByteList(8, "java.awt.datatransfer.StringSelection", this.getEncoding0());
        this.setByteList(14, "copy", this.getEncoding0());
        this.setByteList(13, "instance-variable", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_3899A865192B1E857FDEA08A237F668A09C69E7D file_3899A865192B1E857FDEA08A237F668A09C69E7D, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite0().call(threadContext, rubyObject, rubyObject, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getString0(threadContext.runtime, 32));
        file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite1().call(threadContext, rubyObject, rubyObject, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getString1(threadContext.runtime, 32));
        file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite2().call(threadContext, rubyObject, rubyObject, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getString2(threadContext.runtime, 32));
        file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite3().call(threadContext, rubyObject, rubyObject, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getString3(threadContext.runtime, 32));
        file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite4().call(threadContext, rubyObject, rubyObject, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getString4(threadContext.runtime, 32));
        file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite5().call(threadContext, rubyObject, rubyObject, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getString5(threadContext.runtime, 32));
        file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite6().call(threadContext, rubyObject, rubyObject, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getString6(threadContext.runtime, 32));
        file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite7().call(threadContext, rubyObject, rubyObject, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getString7(threadContext.runtime, 32));
        file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite8().call(threadContext, rubyObject, rubyObject, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getString8(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_3899A865192B1E857FDEA08A237F668A09C69E7D, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_3899A865192B1E857FDEA08A237F668A09C69E7D p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_3899A865192B1E857FDEA08A237F668A09C69E7D.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc_w           "MenuBar"
        //    34: bipush          10
        //    36: invokevirtual   ruby/jit/FILE_3899A865192B1E857FDEA08A237F668A09C69E7D.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    42: invokestatic    ruby/jit/FILE_3899A865192B1E857FDEA08A237F668A09C69E7D.class_1$RUBY$Toolbar:(Lruby/jit/FILE_3899A865192B1E857FDEA08A237F668A09C69E7D;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: aload_1        
        //    46: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: goto            57
        //    52: aload_1        
        //    53: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    56: athrow         
        //    57: aload_1        
        //    58: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    61: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     49     52     57     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_1$RUBY$Toolbar(final FILE_3899A865192B1E857FDEA08A237F668A09C69E7D p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc             "Toolbar"
        //    19: swap           
        //    20: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    23: dup            
        //    24: astore_2       
        //    25: aload_1        
        //    26: swap           
        //    27: aload_0        
        //    28: aload_1        
        //    29: ldc             ",0,0,-1"
        //    31: invokevirtual   ruby/jit/FILE_3899A865192B1E857FDEA08A237F668A09C69E7D.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    34: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    37: aload_0        
        //    38: aload_1        
        //    39: aload_2        
        //    40: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    43: invokestatic    ruby/jit/FILE_3899A865192B1E857FDEA08A237F668A09C69E7D.class_2$RUBY$Listener:(Lruby/jit/FILE_3899A865192B1E857FDEA08A237F668A09C69E7D;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    46: pop            
        //    47: aload_0        
        //    48: bipush          27
        //    50: invokevirtual   ruby/jit/FILE_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    53: aload_1        
        //    54: aload_2        
        //    55: aload_2        
        //    56: aload_0        
        //    57: aload_1        
        //    58: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    61: ldc             "runner"
        //    63: invokevirtual   ruby/jit/FILE_3899A865192B1E857FDEA08A237F668A09C69E7D.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    66: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    69: pop            
        //    70: aload_1        
        //    71: aload_2        
        //    72: aload_0        
        //    73: ldc_w           "initialize"
        //    76: ldc_w           "method__9$RUBY$initialize"
        //    79: ldc_w           "runner,1,0,-1"
        //    82: iconst_1       
        //    83: ldc             "./lib//lister/toolbar.rb"
        //    85: ldc_w           53
        //    88: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    91: ldc_w           "qrunner"
        //    94: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    97: pop            
        //    98: aload_1        
        //    99: aload_2        
        //   100: aload_0        
        //   101: ldc_w           "language"
        //   104: ldc_w           "method__10$RUBY$language"
        //   107: ldc             ",0,0,-1"
        //   109: iconst_0       
        //   110: ldc             "./lib//lister/toolbar.rb"
        //   112: ldc_w           59
        //   115: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   118: ldc_w           "NONE"
        //   121: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   124: pop            
        //   125: aload_1        
        //   126: aload_2        
        //   127: aload_0        
        //   128: ldc_w           "interpret"
        //   131: ldc_w           "method__11$RUBY$interpret"
        //   134: ldc             ",0,0,-1"
        //   136: iconst_0       
        //   137: ldc             "./lib//lister/toolbar.rb"
        //   139: ldc_w           63
        //   142: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   145: ldc_w           "NONE"
        //   148: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   151: pop            
        //   152: aload_1        
        //   153: aload_2        
        //   154: aload_0        
        //   155: ldc_w           "init_hnp"
        //   158: ldc_w           "method__12$RUBY$init_hnp"
        //   161: ldc_w           "m,0,0,-1"
        //   164: iconst_0       
        //   165: ldc             "./lib//lister/toolbar.rb"
        //   167: ldc_w           67
        //   170: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   173: ldc_w           "NONE"
        //   176: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   179: pop            
        //   180: aload_1        
        //   181: aload_2        
        //   182: aload_0        
        //   183: ldc_w           "hnp"
        //   186: ldc_w           "method__13$RUBY$hnp"
        //   189: ldc             ",0,0,-1"
        //   191: iconst_0       
        //   192: ldc             "./lib//lister/toolbar.rb"
        //   194: ldc_w           73
        //   197: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   200: ldc_w           "NONE"
        //   203: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   206: pop            
        //   207: aload_1        
        //   208: aload_2        
        //   209: aload_0        
        //   210: ldc_w           "paste_url_text"
        //   213: ldc_w           "method__14$RUBY$paste_url_text"
        //   216: ldc             ",0,0,-1"
        //   218: iconst_0       
        //   219: ldc             "./lib//lister/toolbar.rb"
        //   221: ldc_w           77
        //   224: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   227: ldc_w           "NONE"
        //   230: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   233: pop            
        //   234: aload_1        
        //   235: aload_2        
        //   236: aload_0        
        //   237: ldc_w           "init_paste"
        //   240: ldc_w           "method__15$RUBY$init_paste"
        //   243: ldc_w           "m,0,0,-1"
        //   246: iconst_0       
        //   247: ldc             "./lib//lister/toolbar.rb"
        //   249: ldc_w           81
        //   252: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   255: ldc_w           "NONE"
        //   258: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   261: pop            
        //   262: aload_1        
        //   263: aload_2        
        //   264: aload_0        
        //   265: ldc_w           "paste"
        //   268: ldc_w           "method__16$RUBY$paste"
        //   271: ldc             ",0,0,-1"
        //   273: iconst_0       
        //   274: ldc             "./lib//lister/toolbar.rb"
        //   276: ldc_w           88
        //   279: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   282: ldc_w           "NONE"
        //   285: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   288: pop            
        //   289: aload_1        
        //   290: aload_2        
        //   291: aload_0        
        //   292: ldc_w           "init_listener"
        //   295: ldc_w           "method__17$RUBY$init_listener"
        //   298: ldc             ",0,0,-1"
        //   300: iconst_0       
        //   301: ldc             "./lib//lister/toolbar.rb"
        //   303: ldc_w           92
        //   306: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   309: ldc_w           "NONE"
        //   312: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   315: pop            
        //   316: aload_1        
        //   317: aload_2        
        //   318: aload_0        
        //   319: ldc_w           "listener"
        //   322: ldc_w           "method__18$RUBY$listener"
        //   325: ldc             ",0,0,-1"
        //   327: iconst_0       
        //   328: ldc             "./lib//lister/toolbar.rb"
        //   330: ldc_w           96
        //   333: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   336: ldc_w           "NONE"
        //   339: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   342: aload_1        
        //   343: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   346: goto            354
        //   349: aload_1        
        //   350: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   353: athrow         
        //   354: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  37     342    349    354    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject class_2$RUBY$Listener(final FILE_3899A865192B1E857FDEA08A237F668A09C69E7D p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    10: ldc             "Listener"
        //    12: swap           
        //    13: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    16: dup            
        //    17: astore_2       
        //    18: aload_1        
        //    19: swap           
        //    20: aload_0        
        //    21: aload_1        
        //    22: ldc             ",0,0,-1"
        //    24: invokevirtual   ruby/jit/FILE_3899A865192B1E857FDEA08A237F668A09C69E7D.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    27: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    30: aload_0        
        //    31: invokevirtual   ruby/jit/FILE_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite9:()Lorg/jruby/runtime/CallSite;
        //    34: aload_1        
        //    35: aload_2        
        //    36: aload_2        
        //    37: aload_0        
        //    38: aload_1        
        //    39: ldc             "ActionListener"
        //    41: invokevirtual   ruby/jit/FILE_3899A865192B1E857FDEA08A237F668A09C69E7D.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    44: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: pop            
        //    48: aload_0        
        //    49: bipush          10
        //    51: invokevirtual   ruby/jit/FILE_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    54: aload_1        
        //    55: aload_2        
        //    56: aload_2        
        //    57: aload_0        
        //    58: aload_1        
        //    59: ldc             "ClipboardOwner"
        //    61: invokevirtual   ruby/jit/FILE_3899A865192B1E857FDEA08A237F668A09C69E7D.getConstant1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    64: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: pop            
        //    68: aload_0        
        //    69: bipush          11
        //    71: invokevirtual   ruby/jit/FILE_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    74: aload_1        
        //    75: aload_2        
        //    76: aload_2        
        //    77: aload_0        
        //    78: aload_1        
        //    79: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    82: ldc             "runner"
        //    84: invokevirtual   ruby/jit/FILE_3899A865192B1E857FDEA08A237F668A09C69E7D.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    87: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    90: pop            
        //    91: aload_1        
        //    92: aload_2        
        //    93: aload_0        
        //    94: ldc_w           "action_performed"
        //    97: ldc_w           "method__3$RUBY$action_performed"
        //   100: ldc_w           "act;meth;err,1,0,-1"
        //   103: iconst_1       
        //   104: ldc             "./lib//lister/toolbar.rb"
        //   106: ldc             20
        //   108: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   111: ldc_w           "qact"
        //   114: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   117: pop            
        //   118: aload_1        
        //   119: aload_2        
        //   120: aload_0        
        //   121: ldc_w           "initialize"
        //   124: ldc_w           "method__6$RUBY$initialize"
        //   127: ldc_w           "runner,1,0,-1"
        //   130: iconst_1       
        //   131: ldc             "./lib//lister/toolbar.rb"
        //   133: ldc_w           31
        //   136: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   139: ldc_w           "qrunner"
        //   142: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   145: pop            
        //   146: aload_1        
        //   147: aload_2        
        //   148: aload_0        
        //   149: ldc_w           "on_error"
        //   152: ldc_w           "method__7$RUBY$on_error"
        //   155: ldc_w           "act;err,2,0,-1"
        //   158: iconst_2       
        //   159: ldc             "./lib//lister/toolbar.rb"
        //   161: ldc_w           35
        //   164: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   167: ldc_w           "qact;qerr"
        //   170: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   173: pop            
        //   174: aload_1        
        //   175: aload_2        
        //   176: aload_0        
        //   177: ldc_w           "on_copy"
        //   180: ldc_w           "method__8$RUBY$on_copy"
        //   183: ldc_w           "clip;str,0,0,-1"
        //   186: iconst_0       
        //   187: ldc             "./lib//lister/toolbar.rb"
        //   189: ldc_w           40
        //   192: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   195: ldc_w           "NONE"
        //   198: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   201: aload_1        
        //   202: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   205: goto            213
        //   208: aload_1        
        //   209: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   212: athrow         
        //   213: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  30     201    208    213    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "action_performed", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$action_performed(final FILE_3899A865192B1E857FDEA08A237F668A09C69E7D p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    14: aload           locals
        //    16: aload_1        
        //    17: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    20: ldc             20
        //    22: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    25: aload_0        
        //    26: aload_1        
        //    27: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    30: bipush          32
        //    32: invokevirtual   ruby/jit/FILE_3899A865192B1E857FDEA08A237F668A09C69E7D.getString9:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    35: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    38: aload_0        
        //    39: bipush          12
        //    41: invokevirtual   ruby/jit/FILE_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    44: aload_1        
        //    45: aload_2        
        //    46: aload           locals
        //    48: aload_1        
        //    49: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //    63: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    66: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    69: pop            
        //    70: aload_0        
        //    71: bipush          13
        //    73: invokevirtual   ruby/jit/FILE_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    76: aload_1        
        //    77: aload_2        
        //    78: aload_2        
        //    79: aload           locals
        //    81: aload_1        
        //    82: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    85: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    91: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    96: ifeq            111
        //    99: aload_0        
        //   100: aload_1        
        //   101: aload_2        
        //   102: aload_3        
        //   103: aload           4
        //   105: invokestatic    ruby/jit/FILE_3899A865192B1E857FDEA08A237F668A09C69E7D.chained_4_rescue_1$RUBY$SYNTHETICaction_performed:(Lruby/jit/FILE_3899A865192B1E857FDEA08A237F668A09C69E7D;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   108: goto            115
        //   111: aload_1        
        //   112: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject chained_4_rescue_1$RUBY$SYNTHETICaction_performed(final FILE_3899A865192B1E857FDEA08A237F668A09C69E7D file_3899A865192B1E857FDEA08A237F668A09C69E7D, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        final IRubyObject errorInfo = threadContext.getErrorInfo();
        IRubyObject rubyObject3 = null;
        Label_0096: {
            try {
                try {
                    rubyObject3 = file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(14).call(threadContext, rubyObject, rubyObject, currentScope.getValueOneDepthZeroOrNil(threadContext.nil));
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable t) {
                    if (RuntimeHelpers.isJavaExceptionHandled(t, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getConstant2(threadContext, "Exception"), threadContext).isTrue()) {
                        RuntimeHelpers.storeExceptionInErrorInfo(t, threadContext);
                        rubyObject3 = chained_5_rescue_line_25(file_3899A865192B1E857FDEA08A237F668A09C69E7D, threadContext, rubyObject, rubyObject2, block);
                        RuntimeHelpers.clearErrorInfo(threadContext);
                        break Label_0096;
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
    
    public static IRubyObject chained_5_rescue_line_25(final FILE_3899A865192B1E857FDEA08A237F668A09C69E7D file_3899A865192B1E857FDEA08A237F668A09C69E7D, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        currentScope.setValueTwoDepthZero(RuntimeHelpers.getGlobalVariable(threadContext.runtime, "$!"));
        return file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(15).call(threadContext, rubyObject, rubyObject, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(16).call(threadContext, rubyObject, currentScope.getValueZeroDepthZeroOrNil(threadContext.nil)), currentScope.getValueTwoDepthZeroOrNil(threadContext.nil));
    }
    
    @JRubyMethod(name = "initialize", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$initialize(final FILE_3899A865192B1E857FDEA08A237F668A09C69E7D file_3899A865192B1E857FDEA08A237F668A09C69E7D, final ThreadContext threadContext, final IRubyObject object, final IRubyObject value, final Block block) {
        return file_3899A865192B1E857FDEA08A237F668A09C69E7D.setVariable0(threadContext.runtime, "@runner", object, value);
    }
    
    @JRubyMethod(name = "on_error", frame = true, required = 2, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$on_error(final FILE_3899A865192B1E857FDEA08A237F668A09C69E7D p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final Block p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_3        
        //     1: astore          10
        //     3: aload           4
        //     5: astore          err
        //     7: aload_0        
        //     8: bipush          17
        //    10: invokevirtual   ruby/jit/FILE_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    13: aload_1        
        //    14: aload_2        
        //    15: aload_0        
        //    16: bipush          18
        //    18: invokevirtual   ruby/jit/FILE_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    21: aload_1        
        //    22: aload_2        
        //    23: aload_2        
        //    24: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    27: aload_1        
        //    28: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    31: ldc             20
        //    33: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    36: aload_0        
        //    37: aload_1        
        //    38: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    41: bipush          10
        //    43: bipush          32
        //    45: invokevirtual   ruby/jit/FILE_3899A865192B1E857FDEA08A237F668A09C69E7D.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    48: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    51: aload           act
        //    53: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //    58: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    61: aload_0        
        //    62: bipush          19
        //    64: invokevirtual   ruby/jit/FILE_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    67: aload_1        
        //    68: aload_2        
        //    69: aload           err
        //    71: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  7      71      10    act   Lorg/jruby/runtime/builtin/IRubyObject;
        //  7      71      11    err   Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "on_copy", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$on_copy(final FILE_3899A865192B1E857FDEA08A237F668A09C69E7D file_3899A865192B1E857FDEA08A237F668A09C69E7D, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        IRubyObject str = context.nil;
        final IRubyObject clip = file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(20).call(context, rubyObject, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(21).call(context, rubyObject, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getConstant3(context, "Toolkit")));
        IRubyObject rubyObject2;
        if (clip.isTrue()) {
            str = file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(22).call(context, rubyObject, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getConstant4(context, "StringSelection"), file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(23).call(context, rubyObject, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(24).call(context, rubyObject, rubyObject)));
            rubyObject2 = file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(25).call(context, rubyObject, clip, str, context.nil);
        }
        else {
            rubyObject2 = file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(26).call(context, rubyObject, rubyObject, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getConstant5(context, "RuntimeError"), file_3899A865192B1E857FDEA08A237F668A09C69E7D.getString(context.runtime, 11, 32));
        }
        return rubyObject2;
    }
    
    public static IRubyObject class_2$RUBY$Listener(final FILE_3899A865192B1E857FDEA08A237F668A09C69E7D file_3899A865192B1E857FDEA08A237F668A09C69E7D, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_2$RUBY$Listener(file_3899A865192B1E857FDEA08A237F668A09C69E7D, threadContext, rubyObject, block);
    }
    
    @JRubyMethod(name = "initialize", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$initialize(final FILE_3899A865192B1E857FDEA08A237F668A09C69E7D file_3899A865192B1E857FDEA08A237F668A09C69E7D, final ThreadContext threadContext, final IRubyObject object, final IRubyObject value, final Block block) {
        file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(28).call(threadContext, object, object, block);
        file_3899A865192B1E857FDEA08A237F668A09C69E7D.setVariable1(threadContext.runtime, "@runner", object, value);
        return file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(29).call(threadContext, object, object, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(30).call(threadContext, object, object));
    }
    
    @JRubyMethod(name = "language", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__10$RUBY$language(final FILE_3899A865192B1E857FDEA08A237F668A09C69E7D file_3899A865192B1E857FDEA08A237F668A09C69E7D, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(31).call(threadContext, rubyObject, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(32).call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "interpret", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__11$RUBY$interpret(final FILE_3899A865192B1E857FDEA08A237F668A09C69E7D file_3899A865192B1E857FDEA08A237F668A09C69E7D, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        return file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(33).call(context, rubyObject, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getConstant6(context, "Interpret"), file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(34).call(context, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "init_hnp", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__12$RUBY$init_hnp(final FILE_3899A865192B1E857FDEA08A237F668A09C69E7D file_3899A865192B1E857FDEA08A237F668A09C69E7D, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        IRubyObject m = context.nil;
        m = file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(35).call(context, rubyObject, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getConstant7(context, "Menu"), file_3899A865192B1E857FDEA08A237F668A09C69E7D.getString(context.runtime, 12, 32));
        file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(36).call(context, rubyObject, m, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(37).call(context, rubyObject, rubyObject));
        return m;
    }
    
    @JRubyMethod(name = "hnp", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__13$RUBY$hnp(final FILE_3899A865192B1E857FDEA08A237F668A09C69E7D file_3899A865192B1E857FDEA08A237F668A09C69E7D, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@hnp") ? file_3899A865192B1E857FDEA08A237F668A09C69E7D.getByteList(13) : null) == null) {
            rubyObject = file_3899A865192B1E857FDEA08A237F668A09C69E7D.setVariable2(threadContext.runtime, "@hnp", object, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(38).call(threadContext, object, object));
        }
        else if (!(rubyObject = file_3899A865192B1E857FDEA08A237F668A09C69E7D.getVariable0(threadContext.runtime, "@hnp", object)).isTrue()) {
            rubyObject = file_3899A865192B1E857FDEA08A237F668A09C69E7D.setVariable3(threadContext.runtime, "@hnp", object, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(39).call(threadContext, object, object));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "paste_url_text", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__14$RUBY$paste_url_text(final FILE_3899A865192B1E857FDEA08A237F668A09C69E7D file_3899A865192B1E857FDEA08A237F668A09C69E7D, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(40).call(threadContext, rubyObject, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(41).call(threadContext, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "init_paste", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__15$RUBY$init_paste(final FILE_3899A865192B1E857FDEA08A237F668A09C69E7D file_3899A865192B1E857FDEA08A237F668A09C69E7D, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        IRubyObject m = threadContext.nil;
        m = file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(42).call(threadContext, rubyObject, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getConstant8(threadContext, "MenuItem"), file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(43).call(threadContext, rubyObject, rubyObject));
        file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(44).call(threadContext, rubyObject, m, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(45).call(threadContext, rubyObject, rubyObject));
        final IRubyObject rubyObject2 = m;
        RuntimeHelpers.doAttrAsgn(rubyObject2, RuntimeHelpers.selectAttrAsgnCallSite(rubyObject2, rubyObject, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(46), file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(47)), file_3899A865192B1E857FDEA08A237F668A09C69E7D.getString(threadContext.runtime, 14, 32), threadContext, rubyObject);
        return m;
    }
    
    @JRubyMethod(name = "paste", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__16$RUBY$paste(final FILE_3899A865192B1E857FDEA08A237F668A09C69E7D file_3899A865192B1E857FDEA08A237F668A09C69E7D, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@paste") ? file_3899A865192B1E857FDEA08A237F668A09C69E7D.getByteList(13) : null) == null) {
            rubyObject = file_3899A865192B1E857FDEA08A237F668A09C69E7D.setVariable4(threadContext.runtime, "@paste", object, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(48).call(threadContext, object, object));
        }
        else if (!(rubyObject = file_3899A865192B1E857FDEA08A237F668A09C69E7D.getVariable1(threadContext.runtime, "@paste", object)).isTrue()) {
            rubyObject = file_3899A865192B1E857FDEA08A237F668A09C69E7D.setVariable5(threadContext.runtime, "@paste", object, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(49).call(threadContext, object, object));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "init_listener", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__17$RUBY$init_listener(final FILE_3899A865192B1E857FDEA08A237F668A09C69E7D file_3899A865192B1E857FDEA08A237F668A09C69E7D, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        return file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(50).call(context, rubyObject, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getConstant9(context, "Listener"), file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(51).call(context, rubyObject, rubyObject));
    }
    
    @JRubyMethod(name = "listener", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__18$RUBY$listener(final FILE_3899A865192B1E857FDEA08A237F668A09C69E7D file_3899A865192B1E857FDEA08A237F668A09C69E7D, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@listener") ? file_3899A865192B1E857FDEA08A237F668A09C69E7D.getByteList(13) : null) == null) {
            rubyObject = file_3899A865192B1E857FDEA08A237F668A09C69E7D.setVariable6(threadContext.runtime, "@listener", object, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(52).call(threadContext, object, object));
        }
        else if (!(rubyObject = file_3899A865192B1E857FDEA08A237F668A09C69E7D.getVariable2(threadContext.runtime, "@listener", object)).isTrue()) {
            rubyObject = file_3899A865192B1E857FDEA08A237F668A09C69E7D.setVariable7(threadContext.runtime, "@listener", object, file_3899A865192B1E857FDEA08A237F668A09C69E7D.getCallSite(53).call(threadContext, object, object));
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    public static IRubyObject class_1$RUBY$Toolbar(final FILE_3899A865192B1E857FDEA08A237F668A09C69E7D file_3899A865192B1E857FDEA08A237F668A09C69E7D, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_1$RUBY$Toolbar(file_3899A865192B1E857FDEA08A237F668A09C69E7D, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_3899A865192B1E857FDEA08A237F668A09C69E7D file_3899A865192B1E857FDEA08A237F668A09C69E7D, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_3899A865192B1E857FDEA08A237F668A09C69E7D, threadContext, rubyObject, block);
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
        final FILE_3899A865192B1E857FDEA08A237F668A09C69E7D file_3899A865192B1E857FDEA08A237F668A09C69E7D = new FILE_3899A865192B1E857FDEA08A237F668A09C69E7D();
        final String string = FILE_3899A865192B1E857FDEA08A237F668A09C69E7D.class.getClassLoader().getResource("ruby/jit/FILE_3899A865192B1E857FDEA08A237F668A09C69E7D.class").toString();
        file_3899A865192B1E857FDEA08A237F668A09C69E7D.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_3899A865192B1E857FDEA08A237F668A09C69E7D.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
