// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.Arity;
import org.jruby.RubyString;
import org.jruby.RubyArray;
import org.jruby.runtime.DynamicScope;
import org.jruby.exceptions.JumpException;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753 extends AbstractScript
{
    public FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753() {
        this.filename = "./lib//lister/runner/measurements/binaries.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffon_posix_like?\uffffN\uffffrunner\uffffV\uffffsplit\uffffN\uffff[]\uffffN\uffffsplit\uffffN\uffff[]\uffffN\ufffffile?\uffffN\uffffexecutable?\uffffN\uffff==\uffffN\uffffextname\uffffN\uffff[]\uffffN\uffff<<\uffffN\uffffbinaries\uffffV\uffffbasename\uffffN\uffffpaths\uffffV\uffffeach_with_index\uffffN\ufffflog\uffffF\uffffprogress\uffffF\uffff/\uffffN\uffff*\uffffN\uffffsize\uffffN\uffffdirectory?\uffffN\uffffeach\uffffN\uffffentries\uffffN\uffffjoin\uffffN\uffffrunnable?\uffffF\ufffftag_binary\uffffF\ufffflog\uffffF\uffff\u0003\u0000\u0001\u0000\u000f\u0000\u0000\u0001\u0002\u0002\u0000\u0000\t\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(5, "PATHEXT", this.getEncoding0());
        this.setByteList(7, "Searching in ", this.getEncoding0());
        this.setByteList(4, ";", this.getEncoding0());
        this.setByteList(2, ":", this.getEncoding0());
        this.setByteList(1, "PATH", this.getEncoding0());
        this.setByteList(0, "lister/measurements/binaries", this.getEncoding0());
        this.setByteList(8, "Rescued error in this dir", this.getEncoding0());
        this.setByteList(6, "instance-variable", this.getEncoding0());
        this.setByteList(3, "Path", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753 file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite0().call(threadContext, rubyObject, rubyObject, file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getString0(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.module__1$RUBY$Measurements:(Lruby/jit/FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Measurements"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc_w           "Measurement"
        //    34: bipush          14
        //    36: invokevirtual   ruby/jit/FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    42: invokestatic    ruby/jit/FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.class_2$RUBY$Binaries:(Lruby/jit/FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject class_2$RUBY$Binaries(final FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    17: ldc             "Binaries"
        //    19: swap           
        //    20: invokevirtual   org/jruby/RubyModule.defineOrGetClassUnder:(Ljava/lang/String;Lorg/jruby/RubyClass;)Lorg/jruby/RubyClass;
        //    23: dup            
        //    24: astore_2       
        //    25: aload_1        
        //    26: swap           
        //    27: aload_0        
        //    28: aload_1        
        //    29: ldc             ",0,0,-1"
        //    31: invokevirtual   ruby/jit/FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    34: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    37: aload_1        
        //    38: aload_2        
        //    39: aload_0        
        //    40: ldc             "paths"
        //    42: ldc             "method__3$RUBY$paths"
        //    44: ldc             ",0,0,-1"
        //    46: iconst_0       
        //    47: ldc             "./lib//lister/runner/measurements/binaries.rb"
        //    49: ldc             7
        //    51: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    54: ldc             "NONE"
        //    56: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    59: pop            
        //    60: aload_1        
        //    61: aload_2        
        //    62: aload_0        
        //    63: ldc_w           "runnable?"
        //    66: ldc_w           "method__4$RUBY$runnable_p_"
        //    69: ldc_w           "path,1,0,-1"
        //    72: iconst_1       
        //    73: ldc             "./lib//lister/runner/measurements/binaries.rb"
        //    75: ldc_w           15
        //    78: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    81: ldc_w           "qpath"
        //    84: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    87: pop            
        //    88: aload_1        
        //    89: aload_2        
        //    90: aload_0        
        //    91: ldc_w           "binaries"
        //    94: ldc_w           "method__6$RUBY$binaries"
        //    97: ldc             ",0,0,-1"
        //    99: iconst_0       
        //   100: ldc             "./lib//lister/runner/measurements/binaries.rb"
        //   102: ldc_w           24
        //   105: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   108: ldc             "NONE"
        //   110: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   113: pop            
        //   114: aload_1        
        //   115: aload_2        
        //   116: aload_0        
        //   117: ldc_w           "tag_binary"
        //   120: ldc_w           "method__7$RUBY$tag_binary"
        //   123: ldc_w           "path,1,0,-1"
        //   126: iconst_1       
        //   127: ldc             "./lib//lister/runner/measurements/binaries.rb"
        //   129: ldc_w           28
        //   132: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   135: ldc_w           "qpath"
        //   138: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   141: pop            
        //   142: aload_1        
        //   143: aload_2        
        //   144: aload_0        
        //   145: ldc_w           "execute"
        //   148: ldc_w           "method__8$RUBY$execute"
        //   151: ldc_w           "all_paths,0,0,-1"
        //   154: iconst_0       
        //   155: ldc             "./lib//lister/runner/measurements/binaries.rb"
        //   157: ldc_w           32
        //   160: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   163: ldc             "NONE"
        //   165: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   168: aload_1        
        //   169: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   172: goto            180
        //   175: aload_1        
        //   176: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   179: athrow         
        //   180: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  37     168    175    180    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "paths", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$paths(final FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753 file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite1().call(threadContext, rubyObject, file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite2().call(threadContext, rubyObject, rubyObject)).isTrue() ? file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite3().call(threadContext, rubyObject, file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite4().call(threadContext, rubyObject, file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getConstant0(threadContext, "ENV"), file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getString1(threadContext.runtime, 32)), file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getString2(threadContext.runtime, 32)) : file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite5().call(threadContext, rubyObject, file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite6().call(threadContext, rubyObject, file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getConstant1(threadContext, "ENV"), file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getString3(threadContext.runtime, 32)), file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getString4(threadContext.runtime, 32));
    }
    
    @JRubyMethod(name = "runnable?", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$runnable_p_(final FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753 file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject valueZeroDepthZero, final Block block) {
        threadContext.getCurrentScope().setValueZeroDepthZero(valueZeroDepthZero);
        return chained_5_rescue_1$RUBY$SYNTHETICrunnable_p_(file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753, threadContext, rubyObject, valueZeroDepthZero, block);
    }
    
    public static IRubyObject chained_5_rescue_1$RUBY$SYNTHETICrunnable_p_(final FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753 file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        final IRubyObject errorInfo = threadContext.getErrorInfo();
        IRubyObject rubyObject3 = null;
        Label_0216: {
            try {
                try {
                    IRubyObject call;
                    if ((call = (rubyObject3 = file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite7().call(threadContext, rubyObject, file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getConstant2(threadContext, "File"), currentScope.getValueZeroDepthZeroOrNil(threadContext.nil)))).isTrue()) {
                        rubyObject3 = (call = file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite8().call(threadContext, rubyObject, file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getConstant3(threadContext, "File"), currentScope.getValueZeroDepthZeroOrNil(threadContext.nil)));
                    }
                    if (!call.isTrue()) {
                        rubyObject3 = file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite9().call(threadContext, rubyObject, file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite(10).call(threadContext, rubyObject, file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getConstant4(threadContext, "File"), currentScope.getValueZeroDepthZeroOrNil(threadContext.nil)), file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite(11).call(threadContext, rubyObject, file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getConstant5(threadContext, "ENV"), file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getString5(threadContext.runtime, 32)));
                    }
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable currentThrowable) {
                    if (RuntimeHelpers.isJavaExceptionHandled(currentThrowable, file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getConstantFrom7(RuntimeHelpers.checkIsModule(file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getConstant6(threadContext, "Errno")), threadContext, "ENOENT"), threadContext).isTrue()) {
                        rubyObject3 = threadContext.runtime.getFalse();
                        threadContext.pollThreadEvents();
                        RuntimeHelpers.clearErrorInfo(threadContext);
                        break Label_0216;
                    }
                    throw currentThrowable;
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
    
    @JRubyMethod(name = "binaries", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$binaries(final FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753 file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if ((object.getInstanceVariables().fastHasInstanceVariable("@binaries") ? file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getByteList6() : null) == null) {
            rubyObject = file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.setVariable0(threadContext.runtime, "@binaries", object, threadContext.runtime.newArray());
        }
        else if (!(rubyObject = file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getVariable0(threadContext.runtime, "@binaries", object)).isTrue()) {
            rubyObject = file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.setVariable1(threadContext.runtime, "@binaries", object, threadContext.runtime.newArray());
        }
        threadContext.pollThreadEvents();
        return rubyObject;
    }
    
    @JRubyMethod(name = "tag_binary", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$tag_binary(final FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753 file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite(12).call(context, rubyObject, file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite(13).call(context, rubyObject, rubyObject), file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite(14).call(context, rubyObject, file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getConstant8(context, "File"), rubyObject2));
    }
    
    @JRubyMethod(name = "execute", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$execute(final FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753 file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite(15).call(threadContext, self, self));
        return file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite(16).callIter(threadContext, self, locals.getValueZeroDepthZeroOrNil(threadContext.nil), RuntimeHelpers.createBlock(threadContext, self, file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getBlockBody1(threadContext, "block_0$RUBY$execute,2,dir;idx,true,1,./lib//lister/runner/measurements/binaries.rb,34,false")));
    }
    
    public static IRubyObject block_0$RUBY$execute(final FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753 file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject value, final Block block) {
        final DynamicScope currentScope = threadContext.getCurrentScope();
        currentScope.setValueOneDepthZero(currentScope.setValueZeroDepthZero(threadContext.nil));
        RuntimeHelpers.processBlockArgument(threadContext.runtime, block);
        final RubyArray ensureMultipleAssignableRubyArray = RuntimeHelpers.ensureMultipleAssignableRubyArray(value, threadContext.runtime, true);
        currentScope.setValueZeroDepthZero(RuntimeHelpers.arrayEntryOrNilZero(ensureMultipleAssignableRubyArray));
        currentScope.setValueOneDepthZero(RuntimeHelpers.arrayEntryOrNilOne(ensureMultipleAssignableRubyArray));
        try {
            return chained_9_rescue_2$RUBY$SYNTHETICexecute(file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753, threadContext, rubyObject, value, block);
        }
        catch (JumpException.RedoJump redoJump) {
            return chained_9_rescue_2$RUBY$SYNTHETICexecute(file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753, threadContext, rubyObject, value, block);
        }
    }
    
    public static IRubyObject chained_9_rescue_2$RUBY$SYNTHETICexecute(final FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753 file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753, final ThreadContext context, final IRubyObject self, final IRubyObject rubyObject, final Block block) {
        final DynamicScope currentScope = context.getCurrentScope();
        final IRubyObject errorInfo = context.getErrorInfo();
        IRubyObject rubyObject2 = null;
        Label_0324: {
            try {
                try {
                    file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite(17).call(context, self, self, RubyString.newStringLight(context.runtime, 20).append(file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getString7(context.runtime, 32)).append(currentScope.getValueZeroDepthZeroOrNil(context.nil).asString()));
                    file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite(18).call(context, self, self, file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite(19).call(context, self, file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite(20).call(context, self, file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getFixnum0(context.runtime, 100), currentScope.getValueOneDepthZeroOrNil(context.nil)), file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite(21).call(context, self, currentScope.getNextCapturedScope().getValueZeroDepthZeroOrNil(context.nil))));
                    if (!file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite(22).call(context, self, file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getConstant9(context, "File"), currentScope.getValueZeroDepthZeroOrNil(context.nil)).isTrue()) {
                        context.pollThreadEvents();
                        RuntimeHelpers.nextJump(context.nil);
                    }
                    rubyObject2 = file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite(23).callIter(context, self, file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite(24).call(context, self, file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getConstant(context, "Dir", 10), currentScope.getValueZeroDepthZeroOrNil(context.nil)), RuntimeHelpers.createBlock(context, self, file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getBlockBody0(context, "block_1$RUBY$execute,1,bin;path,false,2,./lib//lister/runner/measurements/binaries.rb,39,true")));
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable t) {
                    if (RuntimeHelpers.isJavaExceptionHandled(t, file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getConstantFrom(RuntimeHelpers.checkIsModule(file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getConstant(context, "Errno", 12)), context, "ENOENT", 13), context).isTrue()) {
                        RuntimeHelpers.storeExceptionInErrorInfo(t, context);
                        rubyObject2 = chained_10_rescue_line_43(file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753, context, self, rubyObject, block);
                        RuntimeHelpers.clearErrorInfo(context);
                        break Label_0324;
                    }
                    throw t;
                }
            }
            catch (JumpException.FlowControlException ex2) {
                context.setErrorInfo(errorInfo);
                throw ex2;
            }
        }
        context.setErrorInfo(errorInfo);
        return rubyObject2;
    }
    
    public static IRubyObject block_1$RUBY$execute(final FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    32: bipush          25
        //    34: invokevirtual   ruby/jit/FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    37: aload_1        
        //    38: aload_2        
        //    39: aload_0        
        //    40: aload_1        
        //    41: ldc             "File"
        //    43: bipush          11
        //    45: invokevirtual   ruby/jit/FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    48: aload           5
        //    50: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    53: aload_1        
        //    54: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: aload           bin
        //    62: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: astore          path
        //    67: aload_0        
        //    68: bipush          26
        //    70: invokevirtual   ruby/jit/FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    73: aload_1        
        //    74: aload_2        
        //    75: aload_2        
        //    76: aload           path
        //    78: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    81: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    86: ifeq            106
        //    89: aload_0        
        //    90: bipush          27
        //    92: invokevirtual   ruby/jit/FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    95: aload_1        
        //    96: aload_2        
        //    97: aload_2        
        //    98: aload           path
        //   100: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   103: goto            110
        //   106: aload_1        
        //   107: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   110: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  31     80      9     bin   Lorg/jruby/runtime/builtin/IRubyObject;
        //  31     80      10    path  Lorg/jruby/runtime/builtin/IRubyObject;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject chained_10_rescue_line_43(final FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753 file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        threadContext.getCurrentScope();
        return file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getCallSite(28).call(threadContext, rubyObject, rubyObject, file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.getString8(threadContext.runtime, 32));
    }
    
    public static IRubyObject class_2$RUBY$Binaries(final FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753 file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return class_2$RUBY$Binaries(file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Measurements(final FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753 file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Measurements(file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753 file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753, threadContext, rubyObject, block);
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
        final FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753 file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753 = new FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753();
        final String string = FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.class.getClassLoader().getResource("ruby/jit/FILE_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.class").toString();
        file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_DA6500F8D84C844EDDC5218A47022A7E7AD7E753.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
