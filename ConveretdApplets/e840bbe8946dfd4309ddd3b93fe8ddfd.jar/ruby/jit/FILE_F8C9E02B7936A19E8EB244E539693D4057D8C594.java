// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.runtime.DynamicScope;
import org.jruby.RubyFixnum;
import org.jruby.runtime.Arity;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594 extends AbstractScript
{
    public FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594() {
        this.filename = "./lib//lister/utils/route.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffrequire\uffffF\uffffextend\uffffF\ufffffind\uffffN\ufffflines\uffffN\uffffspawn\uffffF\uffff[]\uffffN\uffffsplit\uffffN\uffffiface_for_addr\uffffF\ufffffind\uffffN\ufffflines\uffffN\uffffspawn\uffffF\uffff[]\uffffN\uffffsplit\uffffN\ufffffind\uffffN\ufffflines\uffffN\uffffspawn\uffffF\uffff==\uffffN\ufffffirst\uffffN\uffffsplit\uffffN\uffff[]\uffffN\uffffsplit\uffffN\ufffffor_int_addresses\uffffN\uffffto_i\uffffN\uffffnew\uffffN\uffffdefault\uffffN\uffffon_windows?\uffffN\uffffextend\uffffF\uffffrequire\uffffF\uffffon_linux?\uffffN\uffffextend\uffffF\uffffon_mac?\uffffN\uffffextend\uffffF\uffff\u0006\u0000\u0000\u0000\f\u0002\u0000\u0000\u0000\u0003\u0000\u0000\u000b\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(10, "win32/interface", this.getEncoding0());
        this.setByteList(9, "default", this.getEncoding0());
        this.setByteList(0, "lister/util", this.getEncoding0());
        this.setByteList(7, "dev", this.getEncoding0());
        this.setByteList(8, "ip route list", this.getEncoding0());
        this.setByteList(3, "route get ", this.getEncoding0());
        this.setByteList(5, "0.0.0.0", this.getEncoding0());
        this.setByteList(4, "interface", this.getEncoding0());
        this.setByteList(1, "lister/utils/platform", this.getEncoding0());
        this.setByteList(6, "ip route get ", this.getEncoding0());
        this.setByteList(2, "ip", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594 file_F8C9E02B7936A19E8EB244E539693D4057D8C594, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite0().call(threadContext, rubyObject, rubyObject, file_F8C9E02B7936A19E8EB244E539693D4057D8C594.getString0(threadContext.runtime, 32));
        file_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite1().call(threadContext, rubyObject, rubyObject, file_F8C9E02B7936A19E8EB244E539693D4057D8C594.getString1(threadContext.runtime, 32));
        file_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite2().call(threadContext, rubyObject, rubyObject, file_F8C9E02B7936A19E8EB244E539693D4057D8C594.getString2(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_F8C9E02B7936A19E8EB244E539693D4057D8C594, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.module__1$RUBY$Utils:(Lruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Utils(final FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Utils"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.module__2$RUBY$Route:(Lruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__2$RUBY$Route(final FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Route"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite3:()Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_2        
        //    34: aload_0        
        //    35: aload_1        
        //    36: ldc             "Util"
        //    38: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    41: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    44: pop            
        //    45: aload_0        
        //    46: aload_1        
        //    47: aload_2        
        //    48: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    54: invokestatic    ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.module__3$RUBY$MacOs:(Lruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    57: pop            
        //    58: aload_0        
        //    59: aload_1        
        //    60: aload_2        
        //    61: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    64: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    67: invokestatic    ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.module__6$RUBY$Linux:(Lruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: pop            
        //    71: aload_0        
        //    72: aload_1        
        //    73: aload_2        
        //    74: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    80: invokestatic    ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.module__9$RUBY$Windows:(Lruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: pop            
        //    84: aload_0        
        //    85: bipush          27
        //    87: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    90: aload_1        
        //    91: aload_2        
        //    92: aload_0        
        //    93: aload_1        
        //    94: ldc_w           "Platform"
        //    97: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getConstant6:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   100: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   103: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   108: ifeq            159
        //   111: aload_0        
        //   112: bipush          28
        //   114: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   117: aload_1        
        //   118: aload_2        
        //   119: aload_2        
        //   120: aload_0        
        //   121: aload_1        
        //   122: ldc_w           "Windows"
        //   125: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getConstant7:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   128: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   131: pop            
        //   132: aload_0        
        //   133: bipush          29
        //   135: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   138: aload_1        
        //   139: aload_2        
        //   140: aload_2        
        //   141: aload_0        
        //   142: aload_1        
        //   143: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   146: bipush          10
        //   148: bipush          32
        //   150: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   153: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   156: goto            266
        //   159: aload_0        
        //   160: bipush          30
        //   162: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   165: aload_1        
        //   166: aload_2        
        //   167: aload_0        
        //   168: aload_1        
        //   169: ldc_w           "Platform"
        //   172: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getConstant8:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   175: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   178: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   183: ifeq            209
        //   186: aload_0        
        //   187: bipush          31
        //   189: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   192: aload_1        
        //   193: aload_2        
        //   194: aload_2        
        //   195: aload_0        
        //   196: aload_1        
        //   197: ldc_w           "Linux"
        //   200: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getConstant9:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   203: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   206: goto            266
        //   209: aload_0        
        //   210: bipush          32
        //   212: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   215: aload_1        
        //   216: aload_2        
        //   217: aload_0        
        //   218: aload_1        
        //   219: ldc_w           "Platform"
        //   222: bipush          10
        //   224: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   227: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   230: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   235: ifeq            262
        //   238: aload_0        
        //   239: bipush          33
        //   241: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   244: aload_1        
        //   245: aload_2        
        //   246: aload_2        
        //   247: aload_0        
        //   248: aload_1        
        //   249: ldc             "MacOs"
        //   251: bipush          11
        //   253: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   256: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   259: goto            266
        //   262: aload_1        
        //   263: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   266: aload_1        
        //   267: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   270: goto            278
        //   273: aload_1        
        //   274: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   277: athrow         
        //   278: aload_1        
        //   279: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   282: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     270    273    278    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject module__3$RUBY$MacOs(final FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getScope3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_1        
        //    28: aload_2        
        //    29: aload_0        
        //    30: ldc             "iface_for_addr"
        //    32: ldc             "method__4$RUBY$iface_for_addr"
        //    34: ldc             "addr;line,1,0,-1"
        //    36: iconst_1       
        //    37: ldc             "./lib//lister/utils/route.rb"
        //    39: ldc             11
        //    41: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    44: ldc             "qaddr"
        //    46: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: pop            
        //    50: aload_1        
        //    51: aload_2        
        //    52: aload_0        
        //    53: ldc_w           "default_iface"
        //    56: ldc_w           "method__5$RUBY$default_iface"
        //    59: ldc             ",0,0,-1"
        //    61: iconst_0       
        //    62: ldc             "./lib//lister/utils/route.rb"
        //    64: ldc             20
        //    66: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    69: ldc_w           "NONE"
        //    72: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
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
        //  27     79     82     87     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "iface_for_addr", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$iface_for_addr(final FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    16: aload_0        
        //    17: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite4:()Lorg/jruby/runtime/CallSite;
        //    20: aload_1        
        //    21: aload_2        
        //    22: aload_0        
        //    23: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite5:()Lorg/jruby/runtime/CallSite;
        //    26: aload_1        
        //    27: aload_2        
        //    28: aload_0        
        //    29: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite6:()Lorg/jruby/runtime/CallSite;
        //    32: aload_1        
        //    33: aload_2        
        //    34: aload_2        
        //    35: aload_1        
        //    36: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    39: ldc             20
        //    41: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    44: aload_0        
        //    45: aload_1        
        //    46: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    49: bipush          32
        //    51: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getString3:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    54: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    57: aload           locals
        //    59: aload_1        
        //    60: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //    71: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    74: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: aload_1        
        //    81: aload_2        
        //    82: aload_0        
        //    83: aload_1        
        //    84: ldc             "block_0$RUBY$iface_for_addr,1,l,false,2,./lib//lister/utils/route.rb,12,false"
        //    86: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getBlockBody0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    89: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    92: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    95: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    98: pop            
        //    99: aload           locals
        //   101: aload_1        
        //   102: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   108: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   113: ifeq            153
        //   116: aload_0        
        //   117: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite7:()Lorg/jruby/runtime/CallSite;
        //   120: aload_1        
        //   121: aload_2        
        //   122: aload_0        
        //   123: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite8:()Lorg/jruby/runtime/CallSite;
        //   126: aload_1        
        //   127: aload_2        
        //   128: aload           locals
        //   130: aload_1        
        //   131: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   134: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   137: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   140: aload_1        
        //   141: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   144: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   147: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   150: goto            157
        //   153: aload_1        
        //   154: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   157: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     144     5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_0$RUBY$iface_for_addr(final FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: aload_1        
        //    37: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    40: aload_0        
        //    41: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getByteList4:()Lorg/jruby/util/ByteList;
        //    44: ldc             512
        //    46: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getRegexp0:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //    49: aload           locals
        //    51: aload_1        
        //    52: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: aload_1        
        //    59: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.match3:(Lorg/jruby/RubyRegexp;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    62: areturn        
        //    63: pop            
        //    64: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     28      5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     63     63     67     Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    @JRubyMethod(name = "default_iface", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$default_iface(final FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594 file_F8C9E02B7936A19E8EB244E539693D4057D8C594, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite9().call(threadContext, rubyObject, rubyObject, file_F8C9E02B7936A19E8EB244E539693D4057D8C594.getString5(threadContext.runtime, 32));
    }
    
    public static IRubyObject module__3$RUBY$MacOs(final FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594 file_F8C9E02B7936A19E8EB244E539693D4057D8C594, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__3$RUBY$MacOs(file_F8C9E02B7936A19E8EB244E539693D4057D8C594, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__6$RUBY$Linux(final FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc_w           "Linux"
        //    11: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    14: dup            
        //    15: astore_2       
        //    16: aload_1        
        //    17: swap           
        //    18: aload_0        
        //    19: aload_1        
        //    20: ldc             ",0,0,-1"
        //    22: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getScope4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    25: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    28: aload_1        
        //    29: aload_2        
        //    30: aload_0        
        //    31: ldc             "iface_for_addr"
        //    33: ldc_w           "method__7$RUBY$iface_for_addr"
        //    36: ldc             "addr;line,1,0,-1"
        //    38: iconst_1       
        //    39: ldc             "./lib//lister/utils/route.rb"
        //    41: ldc_w           26
        //    44: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    47: ldc             "qaddr"
        //    49: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: pop            
        //    53: aload_1        
        //    54: aload_2        
        //    55: aload_0        
        //    56: ldc_w           "default_iface"
        //    59: ldc_w           "method__8$RUBY$default_iface"
        //    62: ldc_w           "line,0,0,-1"
        //    65: iconst_0       
        //    66: ldc             "./lib//lister/utils/route.rb"
        //    68: ldc_w           35
        //    71: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    74: ldc_w           "NONE"
        //    77: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: aload_1        
        //    81: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    84: goto            92
        //    87: aload_1        
        //    88: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    91: athrow         
        //    92: aload_1        
        //    93: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    96: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  28     84     87     92     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "iface_for_addr", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__7$RUBY$iface_for_addr(final FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    16: aload_0        
        //    17: bipush          10
        //    19: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    22: aload_1        
        //    23: aload_2        
        //    24: aload_0        
        //    25: bipush          11
        //    27: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    30: aload_1        
        //    31: aload_2        
        //    32: aload_0        
        //    33: bipush          12
        //    35: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    38: aload_1        
        //    39: aload_2        
        //    40: aload_2        
        //    41: aload_1        
        //    42: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    45: ldc             20
        //    47: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    50: aload_0        
        //    51: aload_1        
        //    52: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    55: bipush          32
        //    57: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getString6:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    60: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    63: aload           locals
        //    65: aload_1        
        //    66: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    69: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    72: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //    77: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //    80: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    86: aload_1        
        //    87: aload_2        
        //    88: aload_0        
        //    89: aload_1        
        //    90: ldc_w           "block_1$RUBY$iface_for_addr,1,l,false,2,./lib//lister/utils/route.rb,27,false"
        //    93: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getBlockBody1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    96: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    99: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   102: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: pop            
        //   106: aload           locals
        //   108: aload_1        
        //   109: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   112: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   115: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   120: ifeq            164
        //   123: aload_0        
        //   124: bipush          13
        //   126: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   129: aload_1        
        //   130: aload_2        
        //   131: aload_0        
        //   132: bipush          14
        //   134: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   137: aload_1        
        //   138: aload_2        
        //   139: aload           locals
        //   141: aload_1        
        //   142: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   145: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   148: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   151: aload_1        
        //   152: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   155: invokestatic    org/jruby/RubyFixnum.four:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   158: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   161: goto            168
        //   164: aload_1        
        //   165: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   168: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     155     5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_1$RUBY$iface_for_addr(final FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: aload_1        
        //    37: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    40: aload_0        
        //    41: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getByteList7:()Lorg/jruby/util/ByteList;
        //    44: ldc             512
        //    46: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getRegexp1:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //    49: aload           locals
        //    51: aload_1        
        //    52: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: aload_1        
        //    59: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.match3:(Lorg/jruby/RubyRegexp;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    62: areturn        
        //    63: pop            
        //    64: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     28      5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     63     63     67     Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    @JRubyMethod(name = "default_iface", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$default_iface(final FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594 file_F8C9E02B7936A19E8EB244E539693D4057D8C594, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(file_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite(15).callIter(threadContext, self, file_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite(16).call(threadContext, self, file_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite(17).call(threadContext, self, self, file_F8C9E02B7936A19E8EB244E539693D4057D8C594.getString8(threadContext.runtime, 32))), RuntimeHelpers.createBlock(threadContext, self, file_F8C9E02B7936A19E8EB244E539693D4057D8C594.getBlockBody2(threadContext, "block_2$RUBY$default_iface,1,l,false,2,./lib//lister/utils/route.rb,36,false"))));
        return locals.getValueZeroDepthZeroOrNil(threadContext.nil).isTrue() ? file_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite(21).call(threadContext, self, file_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite(22).call(threadContext, self, locals.getValueZeroDepthZeroOrNil(threadContext.nil)), RubyFixnum.four(threadContext.runtime)) : threadContext.nil;
    }
    
    public static IRubyObject block_2$RUBY$default_iface(final FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    36: bipush          18
        //    38: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_0        
        //    44: bipush          19
        //    46: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload_0        
        //    52: bipush          20
        //    54: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    57: aload_1        
        //    58: aload_2        
        //    59: aload           locals
        //    61: aload_1        
        //    62: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    68: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    71: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: aload_0        
        //    75: aload_1        
        //    76: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    79: bipush          32
        //    81: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getString9:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    84: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    87: areturn        
        //    88: pop            
        //    89: goto            35
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  35     53      5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  35     88     88     92     Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    public static IRubyObject module__6$RUBY$Linux(final FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594 file_F8C9E02B7936A19E8EB244E539693D4057D8C594, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__6$RUBY$Linux(file_F8C9E02B7936A19E8EB244E539693D4057D8C594, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__9$RUBY$Windows(final FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc_w           "Windows"
        //    11: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    14: dup            
        //    15: astore_2       
        //    16: aload_1        
        //    17: swap           
        //    18: aload_0        
        //    19: aload_1        
        //    20: ldc             ",0,0,-1"
        //    22: invokevirtual   ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.getScope5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    25: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    28: aload_1        
        //    29: aload_2        
        //    30: aload_0        
        //    31: ldc             "iface_for_addr"
        //    33: ldc_w           "method__10$RUBY$iface_for_addr"
        //    36: ldc_w           "addr,1,0,-1"
        //    39: iconst_1       
        //    40: ldc             "./lib//lister/utils/route.rb"
        //    42: ldc_w           46
        //    45: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    48: ldc             "qaddr"
        //    50: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    53: pop            
        //    54: aload_1        
        //    55: aload_2        
        //    56: aload_0        
        //    57: ldc_w           "default_iface"
        //    60: ldc_w           "method__11$RUBY$default_iface"
        //    63: ldc             ",0,0,-1"
        //    65: iconst_0       
        //    66: ldc             "./lib//lister/utils/route.rb"
        //    68: ldc_w           50
        //    71: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    74: ldc_w           "NONE"
        //    77: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: aload_1        
        //    81: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    84: goto            92
        //    87: aload_1        
        //    88: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    91: athrow         
        //    92: aload_1        
        //    93: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //    96: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  28     84     87     92     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "iface_for_addr", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__10$RUBY$iface_for_addr(final FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594 file_F8C9E02B7936A19E8EB244E539693D4057D8C594, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite(23).call(context, rubyObject, file_F8C9E02B7936A19E8EB244E539693D4057D8C594.getConstantFrom2(RuntimeHelpers.checkIsModule(file_F8C9E02B7936A19E8EB244E539693D4057D8C594.getConstant1(context, "Win32")), context, "Interface"), file_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite(24).call(context, rubyObject, file_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite(25).call(context, rubyObject, file_F8C9E02B7936A19E8EB244E539693D4057D8C594.getConstant3(context, "IP"), rubyObject2)));
    }
    
    @JRubyMethod(name = "default_iface", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__11$RUBY$default_iface(final FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594 file_F8C9E02B7936A19E8EB244E539693D4057D8C594, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_F8C9E02B7936A19E8EB244E539693D4057D8C594.getCallSite(26).call(threadContext, rubyObject, file_F8C9E02B7936A19E8EB244E539693D4057D8C594.getConstantFrom5(RuntimeHelpers.checkIsModule(file_F8C9E02B7936A19E8EB244E539693D4057D8C594.getConstant4(threadContext, "Win32")), threadContext, "Interface"));
    }
    
    public static IRubyObject module__9$RUBY$Windows(final FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594 file_F8C9E02B7936A19E8EB244E539693D4057D8C594, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__9$RUBY$Windows(file_F8C9E02B7936A19E8EB244E539693D4057D8C594, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__2$RUBY$Route(final FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594 file_F8C9E02B7936A19E8EB244E539693D4057D8C594, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__2$RUBY$Route(file_F8C9E02B7936A19E8EB244E539693D4057D8C594, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Utils(final FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594 file_F8C9E02B7936A19E8EB244E539693D4057D8C594, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Utils(file_F8C9E02B7936A19E8EB244E539693D4057D8C594, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594 file_F8C9E02B7936A19E8EB244E539693D4057D8C594, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_F8C9E02B7936A19E8EB244E539693D4057D8C594, threadContext, rubyObject, block);
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
        final FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594 file_F8C9E02B7936A19E8EB244E539693D4057D8C594 = new FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594();
        final String string = FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.class.getClassLoader().getResource("ruby/jit/FILE_F8C9E02B7936A19E8EB244E539693D4057D8C594.class").toString();
        file_F8C9E02B7936A19E8EB244E539693D4057D8C594.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_F8C9E02B7936A19E8EB244E539693D4057D8C594.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
