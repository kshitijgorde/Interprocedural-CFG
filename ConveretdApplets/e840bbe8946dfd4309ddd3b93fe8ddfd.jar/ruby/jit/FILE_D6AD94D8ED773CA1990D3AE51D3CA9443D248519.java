// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Arity;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519 extends AbstractScript
{
    public FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519() {
        this.filename = "./lib//lister.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffnew\uffffN\uffffnew\uffffN\uffffnew\uffffN\uffffnew\uffffN\uffffnew\uffffN\uffffnew\uffffN\uffffnew\uffffN\uffffnew\uffffN\uffff\u0001\u0005\u0000\u0000\n\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u001f\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(29, "", this.getEncoding0());
        this.setByteList(2, "dev", this.getEncoding0());
        this.setByteList(0, "lister/measurement", this.getEncoding0());
        this.setByteList(28, "dns_sd", this.getEncoding0());
        this.setByteList(22, "0.9.0", this.getEncoding0());
        this.setByteList(11, "Apache", this.getEncoding0());
        this.setByteList(30, "http://developer.apple.com/opensource/", this.getEncoding0());
        this.setByteList(27, "http://json-jruby.rubyforge.org/", this.getEncoding0());
        this.setByteList(19, "commons-logging", this.getEncoding0());
        this.setByteList(15, "http://www.sbbi.net/site/upnp/index.html", this.getEncoding0());
        this.setByteList(9, "JRuby", this.getEncoding0());
        this.setByteList(3, "lucas dicioccio", this.getEncoding0());
        this.setByteList(16, "commons-jxpath", this.getEncoding0());
        this.setByteList(20, "1.1", this.getEncoding0());
        this.setByteList(5, "http://cmon.lip6.fr/hnp", this.getEncoding0());
        this.setByteList(6, "/api/", this.getEncoding0());
        this.setByteList(24, "http://github.com/deploy2/ruby-ip", this.getEncoding0());
        this.setByteList(10, "1.6.0", this.getEncoding0());
        this.setByteList(23, "Ruby", this.getEncoding0());
        this.setByteList(21, "ruby-ip", this.getEncoding0());
        this.setByteList(14, "1.0.4", this.getEncoding0());
        this.setByteList(17, "1.0.3", this.getEncoding0());
        this.setByteList(7, "English", this.getEncoding0());
        this.setByteList(1, "0.1.3", this.getEncoding0());
        this.setByteList(25, "json-jruby", this.getEncoding0());
        this.setByteList(8, "Fran\uffc3\uffa7ais", this.getEncoding0());
        this.setByteList(13, "sbbi-upnplib", this.getEncoding0());
        this.setByteList(26, "1.4.3.1", this.getEncoding0());
        this.setByteList(4, "hnp@cmon.lip6.fr", this.getEncoding0());
        this.setByteList(12, "http://jruby.org/", this.getEncoding0());
        this.setByteList(18, "http://commons.apache.org/", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519 file_D6AD94D8ED773CA1990D3AE51D3CA9443D248519, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getCallSite0().call(threadContext, rubyObject, rubyObject, file_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString0(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_D6AD94D8ED773CA1990D3AE51D3CA9443D248519, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    32: bipush          32
        //    34: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString1:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    37: aload_1        
        //    38: ldc             "VERSION"
        //    40: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: pop            
        //    44: aload_0        
        //    45: aload_1        
        //    46: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    49: bipush          32
        //    51: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString2:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    54: aload_1        
        //    55: ldc             "API_VERSION"
        //    57: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    60: pop            
        //    61: aload_1        
        //    62: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    65: aload_0        
        //    66: aload_1        
        //    67: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    70: bipush          32
        //    72: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString3:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    75: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //    78: aload_1        
        //    79: ldc             "AUTHORS"
        //    81: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    84: pop            
        //    85: aload_0        
        //    86: aload_1        
        //    87: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    90: bipush          32
        //    92: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString4:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //    95: aload_1        
        //    96: ldc             "EMAIL"
        //    98: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   101: pop            
        //   102: aload_0        
        //   103: aload_1        
        //   104: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   107: bipush          32
        //   109: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString5:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   112: aload_1        
        //   113: ldc             "WEBSITE"
        //   115: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   118: pop            
        //   119: aload_1        
        //   120: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   123: ldc             20
        //   125: invokestatic    org/jruby/RubyString.newStringLight:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   128: aload_0        
        //   129: aload_1        
        //   130: ldc             "WEBSITE"
        //   132: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   135: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   140: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   143: aload_0        
        //   144: aload_1        
        //   145: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   148: bipush          32
        //   150: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString6:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   153: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   156: aload_0        
        //   157: aload_1        
        //   158: ldc             "API_VERSION"
        //   160: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getConstant1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   163: invokeinterface org/jruby/runtime/builtin/IRubyObject.asString:()Lorg/jruby/RubyString;
        //   168: invokevirtual   org/jruby/RubyString.append:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyString;
        //   171: aload_1        
        //   172: ldc             "API_BASE"
        //   174: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   177: pop            
        //   178: aload_1        
        //   179: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   182: aload_0        
        //   183: aload_1        
        //   184: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   187: bipush          32
        //   189: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString7:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   192: aload_0        
        //   193: aload_1        
        //   194: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   197: bipush          64
        //   199: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString8:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   202: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   205: aload_1        
        //   206: ldc             "LANGUAGES"
        //   208: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   211: pop            
        //   212: aload_0        
        //   213: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getCallSite1:()Lorg/jruby/runtime/CallSite;
        //   216: aload_1        
        //   217: aload_2        
        //   218: aload_0        
        //   219: aload_1        
        //   220: ldc             "Struct"
        //   222: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getConstant2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   225: aload_0        
        //   226: aload_1        
        //   227: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   230: ldc             "name"
        //   232: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   235: aload_0        
        //   236: aload_1        
        //   237: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   240: ldc             "version"
        //   242: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   245: aload_0        
        //   246: aload_1        
        //   247: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   250: ldc             "authors"
        //   252: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   255: aload_0        
        //   256: aload_1        
        //   257: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   260: ldc             "licence"
        //   262: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   265: aload_0        
        //   266: aload_1        
        //   267: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   270: ldc             "link"
        //   272: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   275: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   278: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   281: aload_1        
        //   282: ldc             "Dependency"
        //   284: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   287: pop            
        //   288: aload_1        
        //   289: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   292: bipush          7
        //   294: anewarray       Lorg/jruby/runtime/builtin/IRubyObject;
        //   297: dup            
        //   298: iconst_0       
        //   299: aload_0        
        //   300: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getCallSite2:()Lorg/jruby/runtime/CallSite;
        //   303: aload_1        
        //   304: aload_2        
        //   305: aload_0        
        //   306: aload_1        
        //   307: ldc             "Dependency"
        //   309: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getConstant3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   312: aload_0        
        //   313: aload_1        
        //   314: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   317: bipush          32
        //   319: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString9:(Lorg/jruby/Ruby;I)Lorg/jruby/RubyString;
        //   322: aload_0        
        //   323: aload_1        
        //   324: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   327: bipush          10
        //   329: bipush          32
        //   331: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   334: aload_1        
        //   335: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   338: aload_0        
        //   339: aload_1        
        //   340: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   343: bipush          11
        //   345: bipush          32
        //   347: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   350: aload_0        
        //   351: aload_1        
        //   352: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   355: bipush          12
        //   357: bipush          32
        //   359: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   362: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   365: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   368: aastore        
        //   369: dup            
        //   370: iconst_1       
        //   371: aload_0        
        //   372: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getCallSite3:()Lorg/jruby/runtime/CallSite;
        //   375: aload_1        
        //   376: aload_2        
        //   377: aload_0        
        //   378: aload_1        
        //   379: ldc             "Dependency"
        //   381: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getConstant4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   384: aload_0        
        //   385: aload_1        
        //   386: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   389: bipush          13
        //   391: bipush          32
        //   393: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   396: aload_0        
        //   397: aload_1        
        //   398: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   401: bipush          14
        //   403: bipush          32
        //   405: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   408: aload_1        
        //   409: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   412: aload_0        
        //   413: aload_1        
        //   414: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   417: bipush          11
        //   419: bipush          32
        //   421: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   424: aload_0        
        //   425: aload_1        
        //   426: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   429: bipush          15
        //   431: bipush          32
        //   433: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   436: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   439: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   442: aastore        
        //   443: dup            
        //   444: iconst_2       
        //   445: aload_0        
        //   446: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getCallSite4:()Lorg/jruby/runtime/CallSite;
        //   449: aload_1        
        //   450: aload_2        
        //   451: aload_0        
        //   452: aload_1        
        //   453: ldc             "Dependency"
        //   455: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getConstant5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   458: aload_0        
        //   459: aload_1        
        //   460: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   463: bipush          16
        //   465: bipush          32
        //   467: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   470: aload_0        
        //   471: aload_1        
        //   472: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   475: bipush          17
        //   477: bipush          32
        //   479: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   482: aload_1        
        //   483: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   486: aload_0        
        //   487: aload_1        
        //   488: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   491: bipush          11
        //   493: bipush          32
        //   495: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   498: aload_0        
        //   499: aload_1        
        //   500: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   503: bipush          18
        //   505: bipush          32
        //   507: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   510: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   513: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   516: aastore        
        //   517: dup            
        //   518: iconst_3       
        //   519: aload_0        
        //   520: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getCallSite5:()Lorg/jruby/runtime/CallSite;
        //   523: aload_1        
        //   524: aload_2        
        //   525: aload_0        
        //   526: aload_1        
        //   527: ldc             "Dependency"
        //   529: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getConstant6:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   532: aload_0        
        //   533: aload_1        
        //   534: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   537: bipush          19
        //   539: bipush          32
        //   541: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   544: aload_0        
        //   545: aload_1        
        //   546: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   549: bipush          20
        //   551: bipush          32
        //   553: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   556: aload_1        
        //   557: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   560: aload_0        
        //   561: aload_1        
        //   562: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   565: bipush          11
        //   567: bipush          32
        //   569: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   572: aload_0        
        //   573: aload_1        
        //   574: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   577: bipush          18
        //   579: bipush          32
        //   581: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   584: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   587: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   590: aastore        
        //   591: dup            
        //   592: iconst_4       
        //   593: aload_0        
        //   594: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getCallSite6:()Lorg/jruby/runtime/CallSite;
        //   597: aload_1        
        //   598: aload_2        
        //   599: aload_0        
        //   600: aload_1        
        //   601: ldc             "Dependency"
        //   603: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getConstant7:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   606: aload_0        
        //   607: aload_1        
        //   608: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   611: bipush          21
        //   613: bipush          32
        //   615: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   618: aload_0        
        //   619: aload_1        
        //   620: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   623: bipush          22
        //   625: bipush          32
        //   627: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   630: aload_1        
        //   631: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   634: aload_0        
        //   635: aload_1        
        //   636: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   639: bipush          23
        //   641: bipush          32
        //   643: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   646: aload_0        
        //   647: aload_1        
        //   648: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   651: bipush          24
        //   653: bipush          32
        //   655: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   658: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   661: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   664: aastore        
        //   665: dup            
        //   666: iconst_5       
        //   667: aload_0        
        //   668: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getCallSite7:()Lorg/jruby/runtime/CallSite;
        //   671: aload_1        
        //   672: aload_2        
        //   673: aload_0        
        //   674: aload_1        
        //   675: ldc             "Dependency"
        //   677: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getConstant8:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   680: aload_0        
        //   681: aload_1        
        //   682: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   685: bipush          25
        //   687: bipush          32
        //   689: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   692: aload_0        
        //   693: aload_1        
        //   694: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   697: bipush          26
        //   699: bipush          32
        //   701: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   704: aload_1        
        //   705: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   708: aload_0        
        //   709: aload_1        
        //   710: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   713: bipush          23
        //   715: bipush          32
        //   717: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   720: aload_0        
        //   721: aload_1        
        //   722: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   725: bipush          27
        //   727: bipush          32
        //   729: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   732: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   735: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   738: aastore        
        //   739: dup            
        //   740: bipush          6
        //   742: aload_0        
        //   743: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getCallSite8:()Lorg/jruby/runtime/CallSite;
        //   746: aload_1        
        //   747: aload_2        
        //   748: aload_0        
        //   749: aload_1        
        //   750: ldc             "Dependency"
        //   752: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getConstant9:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   755: aload_0        
        //   756: aload_1        
        //   757: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   760: bipush          28
        //   762: bipush          32
        //   764: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   767: aload_0        
        //   768: aload_1        
        //   769: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   772: bipush          29
        //   774: bipush          32
        //   776: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   779: aload_1        
        //   780: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   783: aload_0        
        //   784: aload_1        
        //   785: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   788: bipush          11
        //   790: bipush          32
        //   792: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   795: aload_0        
        //   796: aload_1        
        //   797: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   800: bipush          30
        //   802: bipush          32
        //   804: invokevirtual   ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   807: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   810: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   813: aastore        
        //   814: invokestatic    org/jruby/RubyArray.newArrayNoCopy:(Lorg/jruby/Ruby;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   817: aload_1        
        //   818: ldc             "DEPENDENCIES"
        //   820: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   823: aload_1        
        //   824: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   827: goto            835
        //   830: aload_1        
        //   831: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   834: athrow         
        //   835: aload_1        
        //   836: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   839: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     827    830    835    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519 file_D6AD94D8ED773CA1990D3AE51D3CA9443D248519, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_D6AD94D8ED773CA1990D3AE51D3CA9443D248519, threadContext, rubyObject, block);
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
        final FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519 file_D6AD94D8ED773CA1990D3AE51D3CA9443D248519 = new FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519();
        final String string = FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.class.getClassLoader().getResource("ruby/jit/FILE_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.class").toString();
        file_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_D6AD94D8ED773CA1990D3AE51D3CA9443D248519.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
