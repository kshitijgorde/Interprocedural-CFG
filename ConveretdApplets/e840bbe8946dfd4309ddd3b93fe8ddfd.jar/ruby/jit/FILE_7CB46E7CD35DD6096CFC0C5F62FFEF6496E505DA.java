// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.RubyFixnum;
import org.jruby.runtime.Arity;
import org.jruby.RubyString;
import org.jruby.runtime.DynamicScope;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA extends AbstractScript
{
    public FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA() {
        this.filename = "./lib//lister/utils/traceroute.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffextend\uffffF\uffffnew\uffffN\ufffffind\uffffN\ufffflines\uffffN\uffffspawn_err\uffffF\uffffversion_cmd\uffffV\ufffflast\uffffN\uffffsplit\uffffN\uffffinclude\uffffF\uffffeach\uffffN\ufffflines\uffffN\uffffspawn\uffffF\uffffcmd\uffffF\uffffparse_traceroute_line\uffffF\uffff+\uffffN\uffffsplit\uffffN\uffffto_i\uffffN\uffffshift\uffffN\uffff==\uffffN\uffffsize\uffffN\uffffsplit\uffffN\uffffnew\uffffN\uffff<<\uffffN\uffffinclude?\uffffN\uffff<<\uffffN\uffffrtts\uffffN\uffffnew\uffffN\uffff<<\uffffN\uffffshift\uffffN\uffffextend\uffffF\uffffeach\uffffN\ufffflines\uffffN\uffffspawn\uffffF\uffffcmd\uffffF\uffffparse_traceroute_line\uffffF\uffff<<\uffffN\uffffsplit\uffffN\uffff[]\uffffN\uffff[]\uffffN\uffff[]\uffffN\ufffffirst\uffffN\uffff==\uffffN\uffffsize\uffffN\uffffsplit\uffffN\uffffcurrent_ttl\uffffF\uffff+\uffffF\uffffcurrent_ttl=\uffffN\ufffffirst\uffffN\uffff[]\uffffN\uffffreject\uffffN\uffffinclude?\uffffN\uffffnew\uffffN\uffffcurrent_ttl\uffffV\uffffeach\uffffN\ufffflines\uffffN\uffffspawn\uffffF\uffffcmd\uffffF\uffffparse_traceroute_line\uffffF\uffff<<\uffffN\uffffsplit\uffffN\uffffgsub\uffffN\uffffgsub\uffffN\ufffffirst\uffffN\uffffhop_line?\uffffF\uffffsplit\uffffN\uffffgsub\uffffN\uffffgsub\uffffN\uffffto_i\uffffN\uffff==\uffffN\uffffnew\uffffN\uffffnew\uffffN\uffffreject\uffffN\uffff==\uffffN\uffffon_mac?\uffffN\uffffextend\uffffF\uffffextend\uffffF\uffffon_linux?\uffffN\uffffextend\uffffF\uffffextend\uffffF\uffffon_windows?\uffffN\uffffextend\uffffF\uffff\u0007\u0003\u0001\u0000\u0012\u0004\u0000\u0001\u0001\u0006\u0000\u0000\u0011\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(12, "", this.getEncoding0());
        this.setByteList(5, " ", this.getEncoding0());
        this.setByteList(9, "traceroute", this.getEncoding0());
        this.setByteList(2, "traceroute -v", this.getEncoding0());
        this.setByteList(7, "ms", this.getEncoding0());
        this.setByteList(8, "*", this.getEncoding0());
        this.setByteList(13, "<1", this.getEncoding0());
        this.setByteList(1, "lister/utils/platform", this.getEncoding0());
        this.setByteList(6, ".", this.getEncoding0());
        this.setByteList(15, "^\\d+$", this.getEncoding0());
        this.setByteList(3, "version", this.getEncoding0());
        this.setByteList(4, "traceroute -n ", this.getEncoding0());
        this.setByteList(10, "tracert -d ", this.getEncoding0());
        this.setByteList(16, "^([12]?\\d?\\d\\.){3}([12]?\\d?\\d)$", this.getEncoding0());
        this.setByteList(14, "0", this.getEncoding0());
        this.setByteList(0, "lister/util", this.getEncoding0());
        this.setByteList(11, "not-available", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite0().call(threadContext, rubyObject, rubyObject, file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getString0(threadContext.runtime, 32));
        file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite1().call(threadContext, rubyObject, rubyObject, file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getString1(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.module__1$RUBY$Utils:(Lruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Utils(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.module__2$RUBY$Traceroute:(Lruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__2$RUBY$Traceroute(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Traceroute"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    32: bipush          92
        //    34: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getFixnum0:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyFixnum;
        //    37: aload_1        
        //    38: ldc             "WINDOWS_PACKET_LEN"
        //    40: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: pop            
        //    44: aload_0        
        //    45: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite2:()Lorg/jruby/runtime/CallSite;
        //    48: aload_1        
        //    49: aload_2        
        //    50: aload_2        
        //    51: aload_0        
        //    52: aload_1        
        //    53: ldc             "Util"
        //    55: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    58: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    61: pop            
        //    62: aload_0        
        //    63: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite3:()Lorg/jruby/runtime/CallSite;
        //    66: aload_1        
        //    67: aload_2        
        //    68: aload_0        
        //    69: aload_1        
        //    70: ldc             "Struct"
        //    72: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getConstant1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: aload_0        
        //    76: aload_1        
        //    77: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    80: ldc             "ttl"
        //    82: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    85: aload_0        
        //    86: aload_1        
        //    87: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    90: ldc             "ip"
        //    92: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    95: aload_0        
        //    96: aload_1        
        //    97: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   100: ldc             "rtts"
        //   102: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   105: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   108: aload_1        
        //   109: ldc             "Hop"
        //   111: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   114: pop            
        //   115: aload_0        
        //   116: aload_1        
        //   117: aload_2        
        //   118: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //   121: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   124: invokestatic    ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.module__3$RUBY$Unix:(Lruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   127: pop            
        //   128: aload_0        
        //   129: aload_1        
        //   130: aload_2        
        //   131: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //   134: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   137: invokestatic    ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.module__7$RUBY$Linux:(Lruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   140: pop            
        //   141: aload_0        
        //   142: aload_1        
        //   143: aload_2        
        //   144: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //   147: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   150: invokestatic    ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.module__10$RUBY$MacOS:(Lruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   153: pop            
        //   154: aload_0        
        //   155: aload_1        
        //   156: aload_2        
        //   157: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //   160: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   163: invokestatic    ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.module__15$RUBY$Windows:(Lruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   166: pop            
        //   167: aload_0        
        //   168: bipush          75
        //   170: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   173: aload_1        
        //   174: aload_2        
        //   175: aload_0        
        //   176: aload_1        
        //   177: ldc_w           "Platform"
        //   180: bipush          10
        //   182: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   185: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   188: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   193: ifeq            243
        //   196: aload_0        
        //   197: bipush          76
        //   199: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   202: aload_1        
        //   203: aload_2        
        //   204: aload_2        
        //   205: aload_0        
        //   206: aload_1        
        //   207: ldc             "Unix"
        //   209: bipush          11
        //   211: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   214: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   217: pop            
        //   218: aload_0        
        //   219: bipush          77
        //   221: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   224: aload_1        
        //   225: aload_2        
        //   226: aload_2        
        //   227: aload_0        
        //   228: aload_1        
        //   229: ldc_w           "MacOS"
        //   232: bipush          12
        //   234: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   237: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   240: goto            377
        //   243: aload_0        
        //   244: bipush          78
        //   246: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   249: aload_1        
        //   250: aload_2        
        //   251: aload_0        
        //   252: aload_1        
        //   253: ldc_w           "Platform"
        //   256: bipush          13
        //   258: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   261: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   264: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   269: ifeq            319
        //   272: aload_0        
        //   273: bipush          79
        //   275: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   278: aload_1        
        //   279: aload_2        
        //   280: aload_2        
        //   281: aload_0        
        //   282: aload_1        
        //   283: ldc             "Unix"
        //   285: bipush          14
        //   287: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   290: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   293: pop            
        //   294: aload_0        
        //   295: bipush          80
        //   297: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   300: aload_1        
        //   301: aload_2        
        //   302: aload_2        
        //   303: aload_0        
        //   304: aload_1        
        //   305: ldc_w           "Linux"
        //   308: bipush          15
        //   310: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   313: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   316: goto            377
        //   319: aload_0        
        //   320: bipush          81
        //   322: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   325: aload_1        
        //   326: aload_2        
        //   327: aload_0        
        //   328: aload_1        
        //   329: ldc_w           "Platform"
        //   332: bipush          16
        //   334: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   337: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   340: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   345: ifeq            373
        //   348: aload_0        
        //   349: bipush          82
        //   351: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   354: aload_1        
        //   355: aload_2        
        //   356: aload_2        
        //   357: aload_0        
        //   358: aload_1        
        //   359: ldc_w           "Windows"
        //   362: bipush          17
        //   364: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   367: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   370: goto            377
        //   373: aload_1        
        //   374: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   377: aload_1        
        //   378: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   381: goto            389
        //   384: aload_1        
        //   385: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   388: athrow         
        //   389: aload_1        
        //   390: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   393: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     381    384    389    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject module__3$RUBY$Unix(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Unix"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getScope3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_1        
        //    28: aload_2        
        //    29: aload_0        
        //    30: ldc             "version_cmd"
        //    32: ldc             "method__4$RUBY$version_cmd"
        //    34: ldc             ",0,0,-1"
        //    36: iconst_0       
        //    37: ldc             "./lib//lister/utils/traceroute.rb"
        //    39: ldc             14
        //    41: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    44: ldc             "NONE"
        //    46: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: pop            
        //    50: aload_1        
        //    51: aload_2        
        //    52: aload_0        
        //    53: ldc_w           "version"
        //    56: ldc_w           "method__5$RUBY$version"
        //    59: ldc_w           "ret;line,0,0,-1"
        //    62: iconst_0       
        //    63: ldc             "./lib//lister/utils/traceroute.rb"
        //    65: ldc_w           18
        //    68: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    71: ldc             "NONE"
        //    73: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: pop            
        //    77: aload_1        
        //    78: aload_2        
        //    79: aload_0        
        //    80: ldc_w           "cmd"
        //    83: ldc_w           "method__6$RUBY$cmd"
        //    86: ldc_w           "ip,1,0,-1"
        //    89: iconst_1       
        //    90: ldc             "./lib//lister/utils/traceroute.rb"
        //    92: ldc_w           27
        //    95: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeDummy:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    98: ldc_w           "qip"
        //   101: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   104: aload_1        
        //   105: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   108: goto            116
        //   111: aload_1        
        //   112: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   115: athrow         
        //   116: aload_1        
        //   117: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   120: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     108    111    116    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "version_cmd", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__4$RUBY$version_cmd(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getString2(threadContext.runtime, 32);
    }
    
    @JRubyMethod(name = "version", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$version(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(threadContext.nil);
        locals.setValueOneDepthZero(file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite4().callIter(threadContext, self, file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite5().call(threadContext, self, file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite6().call(threadContext, self, self, file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite7().call(threadContext, self, self))), RuntimeHelpers.createBlock(threadContext, self, file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getBlockBody0(threadContext, "block_0$RUBY$version,1,l,false,2,./lib//lister/utils/traceroute.rb,20,false"))));
        if (locals.getValueOneDepthZeroOrNil(threadContext.nil).isTrue()) {
            locals.setValueZeroDepthZero(file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite8().call(threadContext, self, file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite9().call(threadContext, self, locals.getValueOneDepthZeroOrNil(threadContext.nil))));
        }
        return locals.getValueZeroDepthZeroOrNil(threadContext.nil);
    }
    
    public static IRubyObject block_0$RUBY$version(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    41: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getByteList3:()Lorg/jruby/util/ByteList;
        //    44: ldc             513
        //    46: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getRegexp0:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
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
    
    @JRubyMethod(name = "cmd", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$cmd(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA, final ThreadContext context, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return RubyString.newStringLight(context.runtime, 20).append(file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getString4(context.runtime, 32)).append(rubyObject2.asString()).append(file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getString5(context.runtime, 32)).append(file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getConstant2(context, "WINDOWS_PACKET_LEN").asString());
    }
    
    public static IRubyObject module__3$RUBY$Unix(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__3$RUBY$Unix(file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__7$RUBY$Linux(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    22: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getScope4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    25: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    28: aload_0        
        //    29: bipush          10
        //    31: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    34: aload_1        
        //    35: aload_2        
        //    36: aload_2        
        //    37: aload_0        
        //    38: aload_1        
        //    39: ldc             "Unix"
        //    41: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getConstant3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    44: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: pop            
        //    48: aload_1        
        //    49: aload_2        
        //    50: aload_0        
        //    51: ldc_w           "to"
        //    54: ldc_w           "method__8$RUBY$to"
        //    57: ldc_w           "ip;route,1,0,-1"
        //    60: iconst_1       
        //    61: ldc             "./lib//lister/utils/traceroute.rb"
        //    63: ldc_w           35
        //    66: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    69: ldc_w           "qip"
        //    72: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: pop            
        //    76: aload_1        
        //    77: aload_2        
        //    78: aload_0        
        //    79: ldc_w           "parse_traceroute_line"
        //    82: ldc_w           "method__9$RUBY$parse_traceroute_line"
        //    85: ldc_w           "l;hops;ary;ttl;token;hop;rtts;ip,1,0,-1"
        //    88: iconst_1       
        //    89: ldc             "./lib//lister/utils/traceroute.rb"
        //    91: ldc_w           44
        //    94: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    97: ldc_w           "ql"
        //   100: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   103: aload_1        
        //   104: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   107: goto            115
        //   110: aload_1        
        //   111: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   114: athrow         
        //   115: aload_1        
        //   116: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   119: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  28     107    110    115    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "to", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$to(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    20: invokevirtual   org/jruby/Ruby.newArray:()Lorg/jruby/RubyArray;
        //    23: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    26: pop            
        //    27: aload_0        
        //    28: bipush          11
        //    30: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    33: aload_1        
        //    34: aload_2        
        //    35: aload_0        
        //    36: bipush          12
        //    38: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_0        
        //    44: bipush          13
        //    46: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload_2        
        //    52: aload_0        
        //    53: bipush          14
        //    55: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    58: aload_1        
        //    59: aload_2        
        //    60: aload_2        
        //    61: aload           locals
        //    63: aload_1        
        //    64: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: aload_1        
        //    80: aload_2        
        //    81: aload_0        
        //    82: aload_1        
        //    83: ldc_w           "block_1$RUBY$to,1,l;hop,false,2,./lib//lister/utils/traceroute.rb,37,true"
        //    86: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getBlockBody1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    89: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    92: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    95: pop            
        //    96: aload           locals
        //    98: aload_1        
        //    99: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   102: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     92      5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_1$RUBY$to(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    32: bipush          15
        //    34: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    37: aload_1        
        //    38: aload_2        
        //    39: aload_2        
        //    40: aload           l
        //    42: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: astore          hop
        //    47: aload           hop
        //    49: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    54: ifeq            93
        //    57: aload           5
        //    59: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    62: aload_0        
        //    63: bipush          16
        //    65: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    68: aload_1        
        //    69: aload_2        
        //    70: aload           5
        //    72: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    75: aload_1        
        //    76: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: aload           hop
        //    84: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    87: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    90: goto            97
        //    93: aload_1        
        //    94: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    97: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  31     67      9     l     Lorg/jruby/runtime/builtin/IRubyObject;
        //  31     67      10    hop   Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "parse_traceroute_line", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$parse_traceroute_line(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    32: aload_1        
        //    33: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    36: invokevirtual   org/jruby/Ruby.newArray:()Lorg/jruby/RubyArray;
        //    39: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    42: pop            
        //    43: aload           locals
        //    45: aload_0        
        //    46: bipush          17
        //    48: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    51: aload_1        
        //    52: aload_2        
        //    53: aload           locals
        //    55: aload_1        
        //    56: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    59: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    62: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    68: pop            
        //    69: aload           locals
        //    71: aload_0        
        //    72: bipush          18
        //    74: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    77: aload_1        
        //    78: aload_2        
        //    79: aload_0        
        //    80: bipush          19
        //    82: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    85: aload_1        
        //    86: aload_2        
        //    87: aload           locals
        //    89: aload_1        
        //    90: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    93: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    96: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    99: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   102: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: pop            
        //   106: aload           6
        //   108: iconst_4       
        //   109: aload_1        
        //   110: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   113: aastore        
        //   114: aload           6
        //   116: iconst_5       
        //   117: aload_1        
        //   118: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   121: aastore        
        //   122: aload           6
        //   124: bipush          6
        //   126: aload_1        
        //   127: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   130: invokevirtual   org/jruby/Ruby.newArray:()Lorg/jruby/RubyArray;
        //   133: aastore        
        //   134: goto            451
        //   137: aload           6
        //   139: iconst_5       
        //   140: aaload         
        //   141: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   146: ifeq            375
        //   149: aload_0        
        //   150: bipush          20
        //   152: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   155: aload_1        
        //   156: aload_2        
        //   157: aload_0        
        //   158: bipush          21
        //   160: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   163: aload_1        
        //   164: aload_2        
        //   165: aload_0        
        //   166: bipush          22
        //   168: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   171: aload_1        
        //   172: aload_2        
        //   173: aload           6
        //   175: iconst_4       
        //   176: aaload         
        //   177: aload_0        
        //   178: aload_1        
        //   179: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   182: bipush          32
        //   184: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getString6:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   187: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   190: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   193: ldc2_w          4
        //   196: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;J)Lorg/jruby/runtime/builtin/IRubyObject;
        //   199: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   204: ifeq            285
        //   207: aload           6
        //   209: bipush          7
        //   211: aload           6
        //   213: iconst_4       
        //   214: aaload         
        //   215: aastore        
        //   216: aload           6
        //   218: iconst_5       
        //   219: aload_0        
        //   220: bipush          23
        //   222: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   225: aload_1        
        //   226: aload_2        
        //   227: aload_0        
        //   228: aload_1        
        //   229: ldc             "Hop"
        //   231: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getConstant4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   234: aload           locals
        //   236: aload_1        
        //   237: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   240: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   243: aload           6
        //   245: iconst_4       
        //   246: aaload         
        //   247: aload_1        
        //   248: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   251: invokevirtual   org/jruby/Ruby.newArray:()Lorg/jruby/RubyArray;
        //   254: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   257: aastore        
        //   258: aload_0        
        //   259: bipush          24
        //   261: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   264: aload_1        
        //   265: aload_2        
        //   266: aload           locals
        //   268: aload_1        
        //   269: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   272: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   275: aload           6
        //   277: iconst_5       
        //   278: aaload         
        //   279: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   282: goto            372
        //   285: aload_0        
        //   286: bipush          25
        //   288: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   291: aload_1        
        //   292: aload_2        
        //   293: aload_1        
        //   294: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   297: aload_0        
        //   298: aload_1        
        //   299: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   302: bipush          32
        //   304: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   307: aload_0        
        //   308: aload_1        
        //   309: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   312: bipush          32
        //   314: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getString8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   317: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   320: aload           6
        //   322: iconst_4       
        //   323: aaload         
        //   324: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   327: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   332: ifeq            342
        //   335: aload_1        
        //   336: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   339: goto            372
        //   342: aload_0        
        //   343: bipush          26
        //   345: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   348: aload_1        
        //   349: aload_2        
        //   350: aload_0        
        //   351: bipush          27
        //   353: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   356: aload_1        
        //   357: aload_2        
        //   358: aload           6
        //   360: iconst_5       
        //   361: aaload         
        //   362: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   365: aload           6
        //   367: iconst_4       
        //   368: aaload         
        //   369: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   372: goto            450
        //   375: aload           6
        //   377: bipush          7
        //   379: aload           6
        //   381: iconst_4       
        //   382: aaload         
        //   383: aastore        
        //   384: aload           6
        //   386: iconst_5       
        //   387: aload_0        
        //   388: bipush          28
        //   390: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   393: aload_1        
        //   394: aload_2        
        //   395: aload_0        
        //   396: aload_1        
        //   397: ldc             "Hop"
        //   399: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getConstant5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   402: aload           locals
        //   404: aload_1        
        //   405: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   408: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   411: aload           6
        //   413: iconst_4       
        //   414: aaload         
        //   415: aload_1        
        //   416: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   419: invokevirtual   org/jruby/Ruby.newArray:()Lorg/jruby/RubyArray;
        //   422: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   425: aastore        
        //   426: aload_0        
        //   427: bipush          29
        //   429: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   432: aload_1        
        //   433: aload_2        
        //   434: aload           locals
        //   436: aload_1        
        //   437: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   440: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   443: aload           6
        //   445: iconst_5       
        //   446: aaload         
        //   447: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   450: pop            
        //   451: aload           6
        //   453: iconst_4       
        //   454: aload_0        
        //   455: bipush          30
        //   457: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   460: aload_1        
        //   461: aload_2        
        //   462: aload           locals
        //   464: aload_1        
        //   465: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   468: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   471: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   474: dup_x2         
        //   475: aastore        
        //   476: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   481: ifne            137
        //   484: aload_1        
        //   485: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   488: aload_1        
        //   489: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //   492: pop            
        //   493: aload           locals
        //   495: aload_1        
        //   496: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   499: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   502: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  30     473     5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject module__7$RUBY$Linux(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__7$RUBY$Linux(file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__10$RUBY$MacOS(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc_w           "MacOS"
        //    11: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    14: dup            
        //    15: astore_2       
        //    16: aload_1        
        //    17: swap           
        //    18: aload_0        
        //    19: aload_1        
        //    20: ldc             ",0,0,-1"
        //    22: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getScope5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    25: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    28: aload_0        
        //    29: bipush          31
        //    31: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    34: aload_1        
        //    35: aload_2        
        //    36: aload_2        
        //    37: aload_0        
        //    38: aload_1        
        //    39: ldc             "Unix"
        //    41: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getConstant6:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    44: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: pop            
        //    48: aload_1        
        //    49: aload_2        
        //    50: aload_0        
        //    51: ldc_w           "to"
        //    54: ldc_w           "method__11$RUBY$to"
        //    57: ldc_w           "ip;route,1,0,-1"
        //    60: iconst_1       
        //    61: ldc             "./lib//lister/utils/traceroute.rb"
        //    63: ldc_w           73
        //    66: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    69: ldc_w           "qip"
        //    72: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: pop            
        //    76: aload_1        
        //    77: aload_2        
        //    78: aload_0        
        //    79: ldc_w           "current_ttl"
        //    82: ldc_w           "method__12$RUBY$current_ttl"
        //    85: ldc             ",0,0,-1"
        //    87: iconst_0       
        //    88: ldc             "./lib//lister/utils/traceroute.rb"
        //    90: ldc_w           82
        //    93: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    96: ldc             "NONE"
        //    98: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   101: pop            
        //   102: aload_1        
        //   103: aload_2        
        //   104: aload_0        
        //   105: ldc_w           "current_ttl="
        //   108: ldc_w           "method__13$RUBY$current_ttl_equal_"
        //   111: ldc_w           "val,1,0,-1"
        //   114: iconst_1       
        //   115: ldc             "./lib//lister/utils/traceroute.rb"
        //   117: ldc_w           86
        //   120: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   123: ldc_w           "qval"
        //   126: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   129: pop            
        //   130: aload_1        
        //   131: aload_2        
        //   132: aload_0        
        //   133: ldc_w           "parse_traceroute_line"
        //   136: ldc_w           "method__14$RUBY$parse_traceroute_line"
        //   139: ldc_w           "l;hop;ary;ttl;ip;rest;rtts,1,0,-1"
        //   142: iconst_1       
        //   143: ldc             "./lib//lister/utils/traceroute.rb"
        //   145: ldc_w           90
        //   148: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   151: ldc_w           "ql"
        //   154: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   157: aload_1        
        //   158: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   161: goto            169
        //   164: aload_1        
        //   165: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   168: athrow         
        //   169: aload_1        
        //   170: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   173: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  28     161    164    169    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "to", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__11$RUBY$to(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    20: invokevirtual   org/jruby/Ruby.newArray:()Lorg/jruby/RubyArray;
        //    23: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    26: pop            
        //    27: aload_0        
        //    28: bipush          32
        //    30: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    33: aload_1        
        //    34: aload_2        
        //    35: aload_0        
        //    36: bipush          33
        //    38: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_0        
        //    44: bipush          34
        //    46: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload_2        
        //    52: aload_0        
        //    53: bipush          35
        //    55: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    58: aload_1        
        //    59: aload_2        
        //    60: aload_2        
        //    61: aload           locals
        //    63: aload_1        
        //    64: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: aload_1        
        //    80: aload_2        
        //    81: aload_0        
        //    82: aload_1        
        //    83: ldc_w           "block_2$RUBY$to,1,l;hop,false,2,./lib//lister/utils/traceroute.rb,75,true"
        //    86: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getBlockBody2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    89: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    92: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    95: pop            
        //    96: aload           locals
        //    98: aload_1        
        //    99: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   102: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     92      5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_2$RUBY$to(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    32: bipush          36
        //    34: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    37: aload_1        
        //    38: aload_2        
        //    39: aload_2        
        //    40: aload           l
        //    42: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: astore          hop
        //    47: aload           hop
        //    49: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    54: ifeq            85
        //    57: aload_0        
        //    58: bipush          37
        //    60: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    63: aload_1        
        //    64: aload_2        
        //    65: aload           5
        //    67: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    70: aload_1        
        //    71: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: aload           hop
        //    79: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: goto            89
        //    85: aload_1        
        //    86: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  31     59      9     l     Lorg/jruby/runtime/builtin/IRubyObject;
        //  31     59      10    hop   Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "current_ttl", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__12$RUBY$current_ttl(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA, final ThreadContext threadContext, final IRubyObject object, final Block block) {
        IRubyObject rubyObject;
        if (!(rubyObject = file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getVariable0(threadContext.runtime, "@ttl", object)).isTrue()) {
            rubyObject = RubyFixnum.zero(threadContext.runtime);
        }
        return rubyObject;
    }
    
    @JRubyMethod(name = "current_ttl=", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__13$RUBY$current_ttl_equal_(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA, final ThreadContext threadContext, final IRubyObject object, final IRubyObject value, final Block block) {
        return file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.setVariable0(threadContext.runtime, "@ttl", object, value);
    }
    
    @JRubyMethod(name = "parse_traceroute_line", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__14$RUBY$parse_traceroute_line(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    32: aload_1        
        //    33: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    36: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: pop            
        //    40: aload           locals
        //    42: aload_0        
        //    43: bipush          38
        //    45: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    48: aload_1        
        //    49: aload_2        
        //    50: aload           locals
        //    52: aload_1        
        //    53: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    56: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    59: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    62: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: pop            
        //    66: aload_0        
        //    67: bipush          39
        //    69: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    72: aload_1        
        //    73: aload_2        
        //    74: aload           locals
        //    76: aload_1        
        //    77: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: aload_1        
        //    84: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    87: invokestatic    org/jruby/RubyFixnum.zero:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    90: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    93: aload_0        
        //    94: bipush          40
        //    96: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    99: aload_1        
        //   100: aload_2        
        //   101: aload           locals
        //   103: aload_1        
        //   104: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   107: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   110: aload_1        
        //   111: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   114: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   117: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   120: swap           
        //   121: aload           locals
        //   123: swap           
        //   124: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   127: pop            
        //   128: aload           6
        //   130: swap           
        //   131: iconst_4       
        //   132: swap           
        //   133: aastore        
        //   134: aload           6
        //   136: iconst_5       
        //   137: aload_0        
        //   138: bipush          41
        //   140: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   143: aload_1        
        //   144: aload_2        
        //   145: aload           locals
        //   147: aload_1        
        //   148: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   151: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   154: aload_1        
        //   155: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   158: aload_1        
        //   159: aload_1        
        //   160: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   163: invokestatic    org/jruby/RubyFixnum.two:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   166: aload_1        
        //   167: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   170: invokestatic    org/jruby/RubyFixnum.minus_one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   173: invokestatic    org/jruby/RubyRange.newInclusiveRange:(Lorg/jruby/Ruby;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyRange;
        //   176: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   179: dup            
        //   180: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   185: ifne            196
        //   188: pop            
        //   189: aload_1        
        //   190: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   193: invokevirtual   org/jruby/Ruby.newArray:()Lorg/jruby/RubyArray;
        //   196: aastore        
        //   197: aload_0        
        //   198: aload_1        
        //   199: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   202: aload_0        
        //   203: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getByteList9:()Lorg/jruby/util/ByteList;
        //   206: ldc_w           512
        //   209: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getRegexp1:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   212: aload_0        
        //   213: bipush          42
        //   215: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   218: aload_1        
        //   219: aload_2        
        //   220: aload           locals
        //   222: aload_1        
        //   223: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   226: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   229: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   232: aload_1        
        //   233: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.match3:(Lorg/jruby/RubyRegexp;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   236: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   241: ifeq            253
        //   244: aload_1        
        //   245: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   248: areturn        
        //   249: nop            
        //   250: nop            
        //   251: nop            
        //   252: athrow         
        //   253: aload_0        
        //   254: bipush          43
        //   256: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   259: aload_1        
        //   260: aload_2        
        //   261: aload_0        
        //   262: bipush          44
        //   264: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   267: aload_1        
        //   268: aload_2        
        //   269: aload_0        
        //   270: bipush          45
        //   272: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   275: aload_1        
        //   276: aload_2        
        //   277: aload           locals
        //   279: aload_1        
        //   280: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   283: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   286: aload_0        
        //   287: aload_1        
        //   288: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   291: bipush          32
        //   293: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getString6:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   296: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   299: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   302: ldc2_w          1
        //   305: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;J)Lorg/jruby/runtime/builtin/IRubyObject;
        //   308: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   313: ifeq            355
        //   316: aload_1        
        //   317: aload_2        
        //   318: aload_2        
        //   319: aload_1        
        //   320: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   323: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   326: aload_0        
        //   327: bipush          46
        //   329: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   332: aload_0        
        //   333: bipush          47
        //   335: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   338: aload_0        
        //   339: bipush          48
        //   341: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   344: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.opAsgnWithMethod:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   347: aload_1        
        //   348: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //   351: pop            
        //   352: goto            442
        //   355: aload           6
        //   357: iconst_4       
        //   358: aload_0        
        //   359: bipush          49
        //   361: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   364: aload_1        
        //   365: aload_2        
        //   366: aload           locals
        //   368: aload_1        
        //   369: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   372: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   375: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   378: aastore        
        //   379: aload           6
        //   381: iconst_5       
        //   382: aload_0        
        //   383: bipush          50
        //   385: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   388: aload_1        
        //   389: aload_2        
        //   390: aload           locals
        //   392: aload_1        
        //   393: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   396: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   399: aload_1        
        //   400: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   403: aload_1        
        //   404: aload_1        
        //   405: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   408: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   411: aload_1        
        //   412: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   415: invokestatic    org/jruby/RubyFixnum.minus_one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   418: invokestatic    org/jruby/RubyRange.newInclusiveRange:(Lorg/jruby/Ruby;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyRange;
        //   421: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   424: dup            
        //   425: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   430: ifne            441
        //   433: pop            
        //   434: aload_1        
        //   435: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   438: invokevirtual   org/jruby/Ruby.newArray:()Lorg/jruby/RubyArray;
        //   441: aastore        
        //   442: aload           6
        //   444: bipush          6
        //   446: aload_0        
        //   447: bipush          51
        //   449: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   452: aload_1        
        //   453: aload_2        
        //   454: aload           6
        //   456: iconst_5       
        //   457: aaload         
        //   458: aload_1        
        //   459: aload_2        
        //   460: aload_0        
        //   461: aload_1        
        //   462: ldc_w           "block_3$RUBY$parse_traceroute_line,1,str,false,2,./lib//lister/utils/traceroute.rb,102,true"
        //   465: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getBlockBody3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   468: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   471: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   474: aastore        
        //   475: aload           locals
        //   477: aload_0        
        //   478: bipush          53
        //   480: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   483: aload_1        
        //   484: aload_2        
        //   485: aload_0        
        //   486: aload_1        
        //   487: ldc             "Hop"
        //   489: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getConstant7:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   492: aload_0        
        //   493: bipush          54
        //   495: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   498: aload_1        
        //   499: aload_2        
        //   500: aload_2        
        //   501: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   504: aload           6
        //   506: iconst_4       
        //   507: aaload         
        //   508: aload           6
        //   510: bipush          6
        //   512: aaload         
        //   513: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   516: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   519: pop            
        //   520: aload           locals
        //   522: aload_1        
        //   523: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   526: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   529: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  30     500     5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_3$RUBY$parse_traceroute_line(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          52
        //    28: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload_1        
        //    34: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    37: aload_0        
        //    38: aload_1        
        //    39: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    42: bipush          32
        //    44: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    47: aload_0        
        //    48: aload_1        
        //    49: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    52: bipush          32
        //    54: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getString8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    57: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //    60: aload           str
        //    62: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    65: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     41      9     str   Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__10$RUBY$MacOS(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__10$RUBY$MacOS(file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__15$RUBY$Windows(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    22: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getScope6:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    25: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    28: aload_1        
        //    29: aload_2        
        //    30: aload_0        
        //    31: ldc_w           "cmd"
        //    34: ldc_w           "method__16$RUBY$cmd"
        //    37: ldc_w           "ip,1,0,-1"
        //    40: iconst_1       
        //    41: ldc             "./lib//lister/utils/traceroute.rb"
        //    43: ldc_w           109
        //    46: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    49: ldc_w           "qip"
        //    52: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: pop            
        //    56: aload_1        
        //    57: aload_2        
        //    58: aload_0        
        //    59: ldc_w           "version"
        //    62: ldc_w           "method__17$RUBY$version"
        //    65: ldc             ",0,0,-1"
        //    67: iconst_0       
        //    68: ldc             "./lib//lister/utils/traceroute.rb"
        //    70: ldc_w           113
        //    73: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    76: ldc             "NONE"
        //    78: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    81: pop            
        //    82: aload_1        
        //    83: aload_2        
        //    84: aload_0        
        //    85: ldc_w           "to"
        //    88: ldc_w           "method__18$RUBY$to"
        //    91: ldc_w           "ip;route,1,0,-1"
        //    94: iconst_1       
        //    95: ldc             "./lib//lister/utils/traceroute.rb"
        //    97: ldc_w           117
        //   100: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   103: ldc_w           "qip"
        //   106: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   109: pop            
        //   110: aload_1        
        //   111: aload_2        
        //   112: aload_0        
        //   113: ldc_w           "hop_line?"
        //   116: ldc_w           "method__19$RUBY$hop_line_p_"
        //   119: ldc_w           "l;ary,1,0,-1"
        //   122: iconst_1       
        //   123: ldc             "./lib//lister/utils/traceroute.rb"
        //   125: ldc_w           126
        //   128: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   131: ldc_w           "ql"
        //   134: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   137: pop            
        //   138: aload_1        
        //   139: aload_2        
        //   140: aload_0        
        //   141: ldc_w           "parse_traceroute_line"
        //   144: ldc_w           "method__20$RUBY$parse_traceroute_line"
        //   147: ldc_w           "l;ary;ttl;t1;t2;t3;ip;rest;rtts;hop,1,0,-1"
        //   150: iconst_1       
        //   151: ldc             "./lib//lister/utils/traceroute.rb"
        //   153: ldc_w           131
        //   156: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   159: ldc_w           "ql"
        //   162: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   165: aload_1        
        //   166: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   169: goto            177
        //   172: aload_1        
        //   173: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   176: athrow         
        //   177: aload_1        
        //   178: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   181: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  28     169    172    177    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "cmd", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__16$RUBY$cmd(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return RubyString.newStringLight(threadContext.runtime, 20).append(file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getString(threadContext.runtime, 10, 32)).append(rubyObject2.asString());
    }
    
    @JRubyMethod(name = "version", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__17$RUBY$version(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getString(threadContext.runtime, 11, 32);
    }
    
    @JRubyMethod(name = "to", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__18$RUBY$to(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    20: invokevirtual   org/jruby/Ruby.newArray:()Lorg/jruby/RubyArray;
        //    23: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    26: pop            
        //    27: aload_0        
        //    28: bipush          55
        //    30: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    33: aload_1        
        //    34: aload_2        
        //    35: aload_0        
        //    36: bipush          56
        //    38: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    41: aload_1        
        //    42: aload_2        
        //    43: aload_0        
        //    44: bipush          57
        //    46: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    49: aload_1        
        //    50: aload_2        
        //    51: aload_2        
        //    52: aload_0        
        //    53: bipush          58
        //    55: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    58: aload_1        
        //    59: aload_2        
        //    60: aload_2        
        //    61: aload           locals
        //    63: aload_1        
        //    64: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    67: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: aload_1        
        //    80: aload_2        
        //    81: aload_0        
        //    82: aload_1        
        //    83: ldc_w           "block_4$RUBY$to,1,l;hop,false,2,./lib//lister/utils/traceroute.rb,119,true"
        //    86: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getBlockBody4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    89: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    92: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    95: pop            
        //    96: aload           locals
        //    98: aload_1        
        //    99: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   102: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     92      5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_4$RUBY$to(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    32: bipush          59
        //    34: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    37: aload_1        
        //    38: aload_2        
        //    39: aload_2        
        //    40: aload           l
        //    42: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: astore          hop
        //    47: aload           hop
        //    49: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    54: ifeq            85
        //    57: aload_0        
        //    58: bipush          60
        //    60: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    63: aload_1        
        //    64: aload_2        
        //    65: aload           5
        //    67: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    70: aload_1        
        //    71: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: aload           hop
        //    79: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: goto            89
        //    85: aload_1        
        //    86: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  31     59      9     l     Lorg/jruby/runtime/builtin/IRubyObject;
        //  31     59      10    hop   Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "hop_line?", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__19$RUBY$hop_line_p_(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    17: bipush          61
        //    19: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    22: aload_1        
        //    23: aload_2        
        //    24: aload_0        
        //    25: bipush          62
        //    27: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    30: aload_1        
        //    31: aload_2        
        //    32: aload_0        
        //    33: bipush          63
        //    35: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    38: aload_1        
        //    39: aload_2        
        //    40: aload           locals
        //    42: aload_1        
        //    43: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    46: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: aload_0        
        //    50: aload_1        
        //    51: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    54: bipush          32
        //    56: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    59: aload_0        
        //    60: aload_1        
        //    61: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    64: bipush          12
        //    66: bipush          32
        //    68: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    71: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    74: aload_0        
        //    75: aload_1        
        //    76: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    79: bipush          13
        //    81: bipush          32
        //    83: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    86: aload_0        
        //    87: aload_1        
        //    88: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    91: bipush          14
        //    93: bipush          32
        //    95: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    98: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   101: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   104: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   107: pop            
        //   108: aload_0        
        //   109: aload_1        
        //   110: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   113: aload_0        
        //   114: bipush          15
        //   116: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getByteList:(I)Lorg/jruby/util/ByteList;
        //   119: ldc_w           512
        //   122: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getRegexp2:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   125: aload_0        
        //   126: bipush          64
        //   128: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   131: aload_1        
        //   132: aload_2        
        //   133: aload           locals
        //   135: aload_1        
        //   136: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   139: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   142: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   145: aload_1        
        //   146: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.match3:(Lorg/jruby/RubyRegexp;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   149: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     136     5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    @JRubyMethod(name = "parse_traceroute_line", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__20$RUBY$parse_traceroute_line(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    30: aload_0        
        //    31: bipush          65
        //    33: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    36: aload_1        
        //    37: aload_2        
        //    38: aload_2        
        //    39: aload           locals
        //    41: aload_1        
        //    42: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    45: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    48: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    51: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    56: ifeq            62
        //    59: goto            68
        //    62: aload_1        
        //    63: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: areturn        
        //    67: athrow         
        //    68: aload           locals
        //    70: aload_0        
        //    71: bipush          66
        //    73: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    76: aload_1        
        //    77: aload_2        
        //    78: aload_0        
        //    79: bipush          67
        //    81: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    84: aload_1        
        //    85: aload_2        
        //    86: aload_0        
        //    87: bipush          68
        //    89: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    92: aload_1        
        //    93: aload_2        
        //    94: aload           locals
        //    96: aload_1        
        //    97: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   100: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   103: aload_0        
        //   104: aload_1        
        //   105: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   108: bipush          32
        //   110: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   113: aload_0        
        //   114: aload_1        
        //   115: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   118: bipush          12
        //   120: bipush          32
        //   122: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   125: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   128: aload_0        
        //   129: aload_1        
        //   130: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   133: bipush          13
        //   135: bipush          32
        //   137: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   140: aload_0        
        //   141: aload_1        
        //   142: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   145: bipush          14
        //   147: bipush          32
        //   149: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   152: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   155: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   158: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   161: pop            
        //   162: aload           locals
        //   164: aload_1        
        //   165: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   168: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   171: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.splatValue:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   174: aload_1        
        //   175: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   178: iconst_1       
        //   179: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.ensureMultipleAssignableRubyArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;Z)Lorg/jruby/RubyArray;
        //   182: astore          9
        //   184: aload           9
        //   186: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilZero:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   189: aload           locals
        //   191: swap           
        //   192: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   195: pop            
        //   196: aload           9
        //   198: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilOne:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   201: aload           locals
        //   203: swap           
        //   204: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   207: pop            
        //   208: aload           9
        //   210: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilTwo:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   213: aload           6
        //   215: swap           
        //   216: iconst_4       
        //   217: swap           
        //   218: aastore        
        //   219: aload           9
        //   221: iconst_3       
        //   222: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNil:(Lorg/jruby/RubyArray;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   225: aload           6
        //   227: swap           
        //   228: iconst_5       
        //   229: swap           
        //   230: aastore        
        //   231: aload           9
        //   233: iconst_4       
        //   234: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNil:(Lorg/jruby/RubyArray;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   237: aload           6
        //   239: swap           
        //   240: bipush          6
        //   242: swap           
        //   243: aastore        
        //   244: aload           9
        //   246: iconst_5       
        //   247: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNil:(Lorg/jruby/RubyArray;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   250: aload           6
        //   252: swap           
        //   253: bipush          7
        //   255: swap           
        //   256: aastore        
        //   257: aload           9
        //   259: pop            
        //   260: aload           6
        //   262: bipush          8
        //   264: aload_1        
        //   265: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   268: aload           locals
        //   270: aload_1        
        //   271: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   274: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   277: aload           6
        //   279: iconst_4       
        //   280: aaload         
        //   281: aload           6
        //   283: iconst_5       
        //   284: aaload         
        //   285: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   288: aastore        
        //   289: aload           locals
        //   291: aload_0        
        //   292: bipush          69
        //   294: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   297: aload_1        
        //   298: aload_2        
        //   299: aload           locals
        //   301: aload_1        
        //   302: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   305: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   308: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   311: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   314: pop            
        //   315: aload           6
        //   317: bipush          9
        //   319: aload_0        
        //   320: bipush          70
        //   322: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   325: aload_1        
        //   326: aload_2        
        //   327: aload           6
        //   329: bipush          8
        //   331: aaload         
        //   332: aload_1        
        //   333: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   336: aload_0        
        //   337: aload_1        
        //   338: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   341: bipush          32
        //   343: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getString8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   346: aload_0        
        //   347: aload_1        
        //   348: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   351: bipush          32
        //   353: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getString8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   356: aload_0        
        //   357: aload_1        
        //   358: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   361: bipush          32
        //   363: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getString8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   366: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   369: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   372: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   377: ifeq            427
        //   380: aload_0        
        //   381: bipush          71
        //   383: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   386: aload_1        
        //   387: aload_2        
        //   388: aload_0        
        //   389: aload_1        
        //   390: ldc             "Hop"
        //   392: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getConstant8:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   395: aload           locals
        //   397: aload_1        
        //   398: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   401: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   404: aload_0        
        //   405: aload_1        
        //   406: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   409: bipush          32
        //   411: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getString8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   414: aload_1        
        //   415: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   418: invokevirtual   org/jruby/Ruby.newArray:()Lorg/jruby/RubyArray;
        //   421: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   424: goto            544
        //   427: aload           6
        //   429: bipush          6
        //   431: aaload         
        //   432: dup            
        //   433: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   438: ifeq            468
        //   441: pop            
        //   442: aload_0        
        //   443: aload_1        
        //   444: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   447: aload_0        
        //   448: bipush          16
        //   450: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getByteList:(I)Lorg/jruby/util/ByteList;
        //   453: ldc_w           512
        //   456: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getRegexp3:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   459: aload           6
        //   461: bipush          6
        //   463: aaload         
        //   464: aload_1        
        //   465: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.match3:(Lorg/jruby/RubyRegexp;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   468: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   473: ifeq            540
        //   476: aload_0        
        //   477: bipush          72
        //   479: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   482: aload_1        
        //   483: aload_2        
        //   484: aload_0        
        //   485: aload_1        
        //   486: ldc             "Hop"
        //   488: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getConstant9:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   491: aload           locals
        //   493: aload_1        
        //   494: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   497: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   500: aload           6
        //   502: bipush          6
        //   504: aaload         
        //   505: aload_0        
        //   506: bipush          73
        //   508: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   511: aload_1        
        //   512: aload_2        
        //   513: aload           6
        //   515: bipush          8
        //   517: aaload         
        //   518: aload_1        
        //   519: aload_2        
        //   520: aload_0        
        //   521: aload_1        
        //   522: ldc_w           "block_5$RUBY$parse_traceroute_line,1,i,false,2,./lib//lister/utils/traceroute.rb,140,true"
        //   525: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getBlockBody5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   528: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   531: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   534: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   537: goto            544
        //   540: aload_1        
        //   541: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   544: aastore        
        //   545: aload           6
        //   547: bipush          9
        //   549: aaload         
        //   550: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  30     521     5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_5$RUBY$parse_traceroute_line(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          74
        //    28: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           i
        //    35: aload_0        
        //    36: aload_1        
        //    37: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    40: bipush          32
        //    42: invokevirtual   ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.getString8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    45: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    48: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     24      9     i     Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__15$RUBY$Windows(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__15$RUBY$Windows(file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__2$RUBY$Traceroute(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__2$RUBY$Traceroute(file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Utils(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Utils(file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA, threadContext, rubyObject, block);
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
        final FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA = new FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA();
        final String string = FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.class.getClassLoader().getResource("ruby/jit/FILE_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.class").toString();
        file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_7CB46E7CD35DD6096CFC0C5F62FFEF6496E505DA.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
