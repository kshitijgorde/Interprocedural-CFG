// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.exceptions.JumpException;
import org.jruby.RubyArray;
import org.jruby.runtime.Arity;
import org.jruby.RubyFixnum;
import org.jruby.RubyHash;
import org.jruby.runtime.DynamicScope;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 extends AbstractScript
{
    public FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5() {
        this.filename = "./lib//lister/utils/wifiscan.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("require\uffffF\uffffrequire\uffffF\uffffextend\uffffF\uffffnew\uffffN\uffffeach_AP\uffffF\uffff<<\uffffN\uffff-\uffffN\uffff*\uffffN\uffffmerge\uffffN\uffffnew\uffffN\uffff[]\uffffN\uffffdeep_scan_all\uffffN\uffffchannel_for_freq\uffffF\ufffffreq\uffffN\uffffnew\uffffN\uffffssid\uffffN\uffffbssid\uffffN\uffffrssi\uffffN\ufffffreq\uffffN\uffffcurrent_infos\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffchannel\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffessid\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffrssi\uffffN\uffffmerge\uffffN\uffffnew\uffffN\uffffspawn\uffffF\uffffeach_AP_cmd\uffffV\uffffparse_each_AP_data\uffffF\uffffeach\uffffN\ufffflines\uffffN\uffffempty?\uffffN\uffffchomp\uffffN\uffff==\uffffN\ufffffirst\uffffN\uffffsplit\uffffN\uffffnew\uffffN\uffffbssid=\uffffN\uffffbssid=\uffffV\uffffdowncase\uffffN\ufffflast\uffffN\uffffsplit\uffffN\uffffchomp\uffffN\uffff===\uffffN\uffffchannel=\uffffN\uffffchannel=\uffffV\uffffto_i\uffffN\uffffstrip\uffffN\uffff[]\uffffN\uffffsplit\uffffN\uffffchomp\uffffN\uffff===\uffffN\uffffrssi=\uffffN\uffffrssi=\uffffV\uffffto_i\uffffN\ufffflast\uffffN\uffffsplit\uffffN\uffff[]\uffffN\uffffsplit\uffffN\uffff===\uffffN\ufffflast\uffffN\uffffsplit\uffffN\uffffchomp\uffffN\uffffessid=\uffffN\uffffessid=\uffffV\uffffsanitize_quoted_ssid_str\uffffF\ufffftr\uffffN\uffffsub\uffffN\uffffsub\uffffN\uffffspawn\uffffF\uffffcurrent_AP_cmd\uffffV\uffffparse_current_AP_data\uffffF\uffff[]\uffffN\uffffto_s\uffffN\uffffeach\uffffN\ufffflines\uffffN\uffffchomp\uffffN\uffffempty?\uffffN\uffffany?\uffffN\uffff===\uffffN\uffff===\uffffN\uffffsplit\uffffN\uffffstrip\uffffN\uffffchomp\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffchomp\uffffN\ufffflast\uffffN\uffffsplit\uffffN\uffff==\uffffN\uffffsanitize_quoted_ssid_str\uffffF\uffff[]=\uffffN\uffff[]=\uffffV\uffff===\uffffN\ufffffind\uffffN\uffffsplit\uffffN\uffffstart_with?\uffffN\ufffflast\uffffN\uffffsplit\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffff[]=\uffffN\uffff[]=\uffffV\ufffffreq_2_channel\uffffF\uffffdowncase\uffffN\ufffflast\uffffN\uffffsplit\uffffN\uffffstrip\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffff===\uffffN\ufffffind\uffffN\uffffsplit\uffffN\uffffstart_with?\uffffN\ufffflast\uffffN\uffffsplit\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffto_i\uffffN\uffffdowncase\uffffN\uffffjoin\uffffN\uffffmap\uffffN\uffffsplit\uffffN\uffff%\uffffN\uffffto_i\uffffN\uffffeach\uffffN\ufffflines\uffffN\uffffspawn\uffffF\uffffeach_AP_cmd\uffffV\uffffparse_line\uffffF\ufffffirst\uffffN\uffff==\uffffN\uffffsize\uffffN\uffffsplit\uffffN\ufffffirst\uffffN\uffffsplit\uffffN\uffffempty?\uffffN\uffff<<\uffffN\uffffshift\uffffN\uffffempty?\uffffN\uffffary_not_aligned?\uffffF\uffffnew\uffffN\uffffclean_bssid\uffffF\uffffto_i\uffffN\uffffto_i\uffffN\uffffeach\uffffN\ufffflines\uffffN\uffffspawn\uffffF\uffffcurrent_AP_cmd\uffffV\uffffsplit\uffffN\uffffstrip\uffffN\uffffchomp\uffffN\uffff[]=\uffffN\uffff[]=\uffffV\uffffinclude?\uffffN\uffffinteger_keys\uffffV\uffffto_i\uffffN\uffff==\uffffN\uffffclean_bssid\uffffF\uffffon_mac?\uffffN\uffffextend\uffffF\uffffon_linux?\uffffN\uffffextend\uffffF\uffffon_windows?\uffffN\uffffrequire\uffffF\uffffextend\uffffF\uffffputs\uffffF\uffffextend\uffffF\uffff\u0007\bz\u0001\u0015\n\u0000\u0000\u0000\u000b\u0000\u0000x\u0001");
        this.setEncoding(0, "ASCII-8BIT");
        this.setByteList(90, "", this.getEncoding0());
        this.setByteList(86, "Quality", this.getEncoding0());
        this.setByteList(33, "3.662500", this.getEncoding0());
        this.setByteList(68, "5.540", this.getEncoding0());
        this.setByteList(37, "3.682500", this.getEncoding0());
        this.setByteList(31, "3.690", this.getEncoding0());
        this.setByteList(91, "\"$", this.getEncoding0());
        this.setByteList(60, "5.230", this.getEncoding0());
        this.setByteList(48, "5.035", this.getEncoding0());
        this.setByteList(30, "3.689_500", this.getEncoding0());
        this.setByteList(9, "2.437", this.getEncoding0());
        this.setByteList(117, "BSSID", this.getEncoding0());
        this.setByteList(109, "agrCtlNoise", this.getEncoding0());
        this.setByteList(8, "2.432", this.getEncoding0());
        this.setByteList(113, "lastAssocStatus", this.getEncoding0());
        this.setByteList(82, "iwlist scan", this.getEncoding0());
        this.setByteList(93, "\"", this.getEncoding0());
        this.setByteList(34, "3.667500", this.getEncoding0());
        this.setByteList(89, "^\"", this.getEncoding0());
        this.setByteList(78, "5.765", this.getEncoding0());
        this.setByteList(11, "2.447", this.getEncoding0());
        this.setByteList(61, "5.240", this.getEncoding0());
        this.setByteList(94, "iwconfig", this.getEncoding0());
        this.setByteList(0, "lister/util", this.getEncoding0());
        this.setByteList(95, "no wireless", this.getEncoding0());
        this.setByteList(50, "5.045", this.getEncoding0());
        this.setByteList(21, "3.667_500", this.getEncoding0());
        this.setByteList(85, ":", this.getEncoding0());
        this.setByteList(10, "2.442", this.getEncoding0());
        this.setByteList(49, "5.040", this.getEncoding0());
        this.setByteList(87, "=", this.getEncoding0());
        this.setByteList(3, "legacy", this.getEncoding0());
        this.setByteList(69, "5.560", this.getEncoding0());
        this.setByteList(83, "Cell", this.getEncoding0());
        this.setByteList(47, "4.980", this.getEncoding0());
        this.setByteList(103, "level=", this.getEncoding0());
        this.setByteList(5, "2.417", this.getEncoding0());
        this.setByteList(96, "^\\w+", this.getEncoding0());
        this.setByteList(4, "2.412", this.getEncoding0());
        this.setByteList(116, ": ", this.getEncoding0());
        this.setByteList(58, "5.210", this.getEncoding0());
        this.setByteList(38, "3.687500", this.getEncoding0());
        this.setByteList(28, "3.687_500", this.getEncoding0());
        this.setByteList(19, "3.662_500", this.getEncoding0());
        this.setByteList(77, "5.745", this.getEncoding0());
        this.setByteList(40, "4.915", this.getEncoding0());
        this.setByteList(98, "no-name-found", this.getEncoding0());
        this.setByteList(7, "2.427", this.getEncoding0());
        this.setByteList(41, "4.920", this.getEncoding0());
        this.setByteList(59, "5.220", this.getEncoding0());
        this.setByteList(6, "2.422", this.getEncoding0());
        this.setByteList(2, "library", this.getEncoding0());
        this.setByteList(99, "off/any", this.getEncoding0());
        this.setByteList(71, "5.600", this.getEncoding0());
        this.setByteList(17, "2.484", this.getEncoding0());
        this.setByteList(42, "4.925", this.getEncoding0());
        this.setByteList(25, "3.677_500", this.getEncoding0());
        this.setByteList(36, "3.677500", this.getEncoding0());
        this.setByteList(1, "lister/utils/platform", this.getEncoding0());
        this.setByteList(70, "5.580", this.getEncoding0());
        this.setByteList(26, "3.682_500", this.getEncoding0());
        this.setByteList(80, "5.805", this.getEncoding0());
        this.setByteList(54, "5.170", this.getEncoding0());
        this.setByteList(75, "5.680", this.getEncoding0());
        this.setByteList(110, "agrExtNoise", this.getEncoding0());
        this.setByteList(79, "5.785", this.getEncoding0());
        this.setByteList(104, "%02x", this.getEncoding0());
        this.setByteList(43, "4.935", this.getEncoding0());
        this.setByteList(16, "2.472", this.getEncoding0());
        this.setByteList(20, "3.660", this.getEncoding0());
        this.setByteList(22, "3.665", this.getEncoding0());
        this.setByteList(53, "5.080", this.getEncoding0());
        this.setByteList(114, "MCS", this.getEncoding0());
        this.setByteList(35, "3.672500", this.getEncoding0());
        this.setByteList(63, "5.280", this.getEncoding0());
        this.setByteList(119, "rescued loading wifi error", this.getEncoding0());
        this.setByteList(57, "5.200", this.getEncoding0());
        this.setByteList(72, "5.620", this.getEncoding0());
        this.setByteList(32, "3.657500", this.getEncoding0());
        this.setByteList(67, "5.520", this.getEncoding0());
        this.setByteList(64, "5.300", this.getEncoding0());
        this.setByteList(55, "5.180", this.getEncoding0());
        this.setByteList(108, "agrExtRSSI", this.getEncoding0());
        this.setByteList(44, "4.940", this.getEncoding0());
        this.setByteList(88, "ESSID:", this.getEncoding0());
        this.setByteList(102, "^\\s+Link Quality", this.getEncoding0());
        this.setByteList(24, "3.670", this.getEncoding0());
        this.setByteList(45, "4.945", this.getEncoding0());
        this.setByteList(56, "5.190", this.getEncoding0());
        this.setByteList(14, "2.462", this.getEncoding0());
        this.setByteList(15, "2.467", this.getEncoding0());
        this.setByteList(106, "/System/Library/PrivateFrameworks/Apple80211.framework/Versions/Current/Resources/airport -I", this.getEncoding0());
        this.setByteList(39, "3.689500", this.getEncoding0());
        this.setByteList(100, "^\\s+Mode:", this.getEncoding0());
        this.setByteList(101, "Frequency:", this.getEncoding0());
        this.setByteList(81, "5.825", this.getEncoding0());
        this.setByteList(51, "5.055", this.getEncoding0());
        this.setByteList(97, "\\s+", this.getEncoding0());
        this.setByteList(74, "5.660", this.getEncoding0());
        this.setByteList(76, "5.700", this.getEncoding0());
        this.setByteList(107, "agrCtlRSSI", this.getEncoding0());
        this.setByteList(27, "3.680", this.getEncoding0());
        this.setByteList(92, "\\\"", this.getEncoding0());
        this.setByteList(105, "/System/Library/PrivateFrameworks/Apple80211.framework/Versions/Current/Resources/airport -s", this.getEncoding0());
        this.setByteList(12, "2.452", this.getEncoding0());
        this.setByteList(18, "3.657_500", this.getEncoding0());
        this.setByteList(13, "2.457", this.getEncoding0());
        this.setByteList(62, "5.260", this.getEncoding0());
        this.setByteList(112, "maxRate", this.getEncoding0());
        this.setByteList(29, "3.685", this.getEncoding0());
        this.setByteList(52, "5.060", this.getEncoding0());
        this.setByteList(111, "lastTxRate", this.getEncoding0());
        this.setByteList(23, "3.672_500", this.getEncoding0());
        this.setByteList(73, "5.640", this.getEncoding0());
        this.setByteList(84, "Channel:", this.getEncoding0());
        this.setByteList(46, "4.960", this.getEncoding0());
        this.setByteList(66, "5.500", this.getEncoding0());
        this.setByteList(118, "win32/wlan", this.getEncoding0());
        this.setByteList(65, "5.320", this.getEncoding0());
        this.setByteList(115, "channel", this.getEncoding0());
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite0().call(threadContext, rubyObject, rubyObject, file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString0(threadContext.runtime, 32));
        file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite1().call(threadContext, rubyObject, rubyObject, file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString1(threadContext.runtime, 32));
        return module__0$RUBY$Lister(file_4B197E63B21397B2B752F01B316CEB14EA4363B5, threadContext, rubyObject, IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getScope0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.module__1$RUBY$Utils:(Lruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__1$RUBY$Utils(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    21: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getScope1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_0        
        //    28: aload_1        
        //    29: aload_2        
        //    30: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //    33: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //    36: invokestatic    ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.module__2$RUBY$WifiScan:(Lruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__2$RUBY$WifiScan(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //     4: dup            
        //     5: astore          4
        //     7: invokevirtual   org/jruby/runtime/DynamicScope.getValues:()[Lorg/jruby/runtime/builtin/IRubyObject;
        //    10: astore          5
        //    12: aload_1        
        //    13: aload_1        
        //    14: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    17: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //    20: ldc             "WifiScan"
        //    22: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    25: dup            
        //    26: astore_2       
        //    27: aload_1        
        //    28: swap           
        //    29: aload_0        
        //    30: aload_1        
        //    31: ldc             "err,0,0,-1"
        //    33: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getScope2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    36: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClass:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    39: aload_1        
        //    40: invokevirtual   org/jruby/runtime/ThreadContext.getCurrentScope:()Lorg/jruby/runtime/DynamicScope;
        //    43: astore          4
        //    45: aload_1        
        //    46: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: aload           4
        //    51: swap           
        //    52: invokevirtual   org/jruby/runtime/DynamicScope.setValueZeroDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: pop            
        //    56: aload_0        
        //    57: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite2:()Lorg/jruby/runtime/CallSite;
        //    60: aload_1        
        //    61: aload_2        
        //    62: aload_2        
        //    63: aload_0        
        //    64: aload_1        
        //    65: ldc             "Util"
        //    67: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getConstant0:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    73: pop            
        //    74: aload_0        
        //    75: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite3:()Lorg/jruby/runtime/CallSite;
        //    78: aload_1        
        //    79: aload_2        
        //    80: aload_0        
        //    81: aload_1        
        //    82: ldc             "Struct"
        //    84: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getConstant1:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    87: aload_0        
        //    88: aload_1        
        //    89: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    92: ldc             "essid"
        //    94: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getSymbol0:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    97: aload_0        
        //    98: aload_1        
        //    99: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   102: ldc             "bssid"
        //   104: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   107: aload_0        
        //   108: aload_1        
        //   109: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   112: ldc             "channel"
        //   114: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   117: aload_0        
        //   118: aload_1        
        //   119: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   122: ldc             "rssi"
        //   124: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   127: aload_0        
        //   128: aload_1        
        //   129: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   132: ldc             "frequency"
        //   134: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getSymbol4:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   137: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   140: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   143: aload_1        
        //   144: ldc             "AccessPoint"
        //   146: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   149: pop            
        //   150: aload_1        
        //   151: aload_2        
        //   152: aload_2        
        //   153: aload_0        
        //   154: ldc             "all_APs"
        //   156: ldc             "method__3$RUBY$all_APs"
        //   158: ldc             "ary,0,0,-1"
        //   160: iconst_0       
        //   161: ldc             "./lib//lister/utils/wifiscan.rb"
        //   163: ldc             11
        //   165: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   168: ldc             "NONE"
        //   170: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.defs:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   173: pop            
        //   174: aload_0        
        //   175: aload_1        
        //   176: aload_2        
        //   177: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //   180: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   183: invokestatic    ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.module__4$RUBY$Windows:(Lruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   186: pop            
        //   187: aload_0        
        //   188: aload_1        
        //   189: aload_2        
        //   190: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //   193: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   196: invokestatic    ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.module__11$RUBY$WindowsLegacy:(Lruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   199: pop            
        //   200: aload_0        
        //   201: aload_1        
        //   202: aload_2        
        //   203: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //   206: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   209: invokestatic    ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.module__15$RUBY$Linux:(Lruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   212: pop            
        //   213: aload_0        
        //   214: aload_1        
        //   215: aload_2        
        //   216: getstatic       org/jruby/runtime/builtin/IRubyObject.NULL_ARRAY:[Lorg/jruby/runtime/builtin/IRubyObject;
        //   219: getstatic       org/jruby/runtime/Block.NULL_BLOCK:Lorg/jruby/runtime/Block;
        //   222: invokestatic    ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.module__25$RUBY$MacOs:(Lruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   225: pop            
        //   226: aload_0        
        //   227: sipush          164
        //   230: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   233: aload_1        
        //   234: aload_2        
        //   235: aload_0        
        //   236: aload_1        
        //   237: ldc_w           "Platform"
        //   240: bipush          13
        //   242: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   245: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   248: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   253: ifeq            282
        //   256: aload_0        
        //   257: sipush          165
        //   260: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   263: aload_1        
        //   264: aload_2        
        //   265: aload_2        
        //   266: aload_0        
        //   267: aload_1        
        //   268: ldc_w           "MacOs"
        //   271: bipush          14
        //   273: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   276: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   279: goto            382
        //   282: aload_0        
        //   283: sipush          166
        //   286: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   289: aload_1        
        //   290: aload_2        
        //   291: aload_0        
        //   292: aload_1        
        //   293: ldc_w           "Platform"
        //   296: bipush          15
        //   298: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   301: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   304: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   309: ifeq            338
        //   312: aload_0        
        //   313: sipush          167
        //   316: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   319: aload_1        
        //   320: aload_2        
        //   321: aload_2        
        //   322: aload_0        
        //   323: aload_1        
        //   324: ldc_w           "Linux"
        //   327: bipush          16
        //   329: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   332: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   335: goto            382
        //   338: aload_0        
        //   339: sipush          168
        //   342: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   345: aload_1        
        //   346: aload_2        
        //   347: aload_0        
        //   348: aload_1        
        //   349: ldc_w           "Platform"
        //   352: bipush          17
        //   354: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   357: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   360: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   365: ifeq            378
        //   368: aload_0        
        //   369: aload_1        
        //   370: aload_2        
        //   371: aload_3        
        //   372: invokestatic    ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.chained_34_rescue_1$RUBY$SYNTHETICWifiScan:(Lruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   375: goto            382
        //   378: aload_1        
        //   379: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   382: aload_1        
        //   383: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   386: goto            394
        //   389: aload_1        
        //   390: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   393: athrow         
        //   394: aload_1        
        //   395: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   398: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  56     343     4     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  56     386    389    394    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "all_APs", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__3$RUBY$all_APs(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(threadContext.runtime.newArray());
        file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite4().callIter(threadContext, self, self, RuntimeHelpers.createBlock(threadContext, self, file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getBlockBody0(threadContext, "block_0$RUBY$all_APs,1,ap,false,2,./lib//lister/utils/wifiscan.rb,13,true")));
        return locals.getValueZeroDepthZeroOrNil(threadContext.nil);
    }
    
    public static IRubyObject block_0$RUBY$all_APs(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite5:()Lorg/jruby/runtime/CallSite;
        //    29: aload_1        
        //    30: aload_2        
        //    31: aload           5
        //    33: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    36: aload_1        
        //    37: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    40: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: aload           ap
        //    45: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    48: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     24      9     ap    Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__4$RUBY$Windows(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc             "Windows"
        //    10: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    13: dup            
        //    14: astore_2       
        //    15: aload_1        
        //    16: swap           
        //    17: aload_0        
        //    18: aload_1        
        //    19: ldc             ",0,0,-1"
        //    21: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getScope3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    24: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    27: aload_1        
        //    28: aload_2        
        //    29: aload_0        
        //    30: ldc_w           "each_AP_cmd"
        //    33: ldc_w           "method__5$RUBY$each_AP_cmd"
        //    36: ldc             ",0,0,-1"
        //    38: iconst_0       
        //    39: ldc             "./lib//lister/utils/wifiscan.rb"
        //    41: ldc_w           20
        //    44: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    47: ldc             "NONE"
        //    49: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: pop            
        //    53: aload_1        
        //    54: aload_2        
        //    55: aload_0        
        //    56: ldc_w           "rssi_for_quality"
        //    59: ldc_w           "method__6$RUBY$rssi_for_quality"
        //    62: ldc_w           "x,1,0,-1"
        //    65: iconst_1       
        //    66: ldc             "./lib//lister/utils/wifiscan.rb"
        //    68: ldc_w           28
        //    71: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    74: ldc_w           "qx"
        //    77: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: pop            
        //    81: aload_0        
        //    82: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite8:()Lorg/jruby/runtime/CallSite;
        //    85: aload_1        
        //    86: aload_2        
        //    87: aload_0        
        //    88: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite9:()Lorg/jruby/runtime/CallSite;
        //    91: aload_1        
        //    92: aload_2        
        //    93: aload_0        
        //    94: aload_1        
        //    95: ldc_w           "Hash"
        //    98: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getConstant2:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   101: aload_1        
        //   102: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   105: invokestatic    org/jruby/RubyFixnum.minus_one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   108: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   111: aload_0        
        //   112: aload_1        
        //   113: aload_1        
        //   114: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   117: invokestatic    org/jruby/RubyHash.newHash:(Lorg/jruby/Ruby;)Lorg/jruby/RubyHash;
        //   120: invokestatic    ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.hash_builder_7:(Lruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/RubyHash;)Lorg/jruby/RubyHash;
        //   123: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   126: aload_1        
        //   127: ldc_w           "FREQ_MAP"
        //   130: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   133: pop            
        //   134: aload_1        
        //   135: aload_2        
        //   136: aload_0        
        //   137: ldc_w           "channel_for_freq"
        //   140: ldc_w           "method__8$RUBY$channel_for_freq"
        //   143: ldc_w           "f,1,0,-1"
        //   146: iconst_1       
        //   147: ldc             "./lib//lister/utils/wifiscan.rb"
        //   149: ldc_w           108
        //   152: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   155: ldc_w           "qf"
        //   158: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   161: pop            
        //   162: aload_1        
        //   163: aload_2        
        //   164: aload_0        
        //   165: ldc_w           "each_AP"
        //   168: ldc_w           "method__9$RUBY$each_AP"
        //   171: ldc             ",0,0,-1"
        //   173: iconst_0       
        //   174: ldc             "./lib//lister/utils/wifiscan.rb"
        //   176: ldc_w           112
        //   179: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   182: ldc             "NONE"
        //   184: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   187: pop            
        //   188: aload_1        
        //   189: aload_2        
        //   190: aload_0        
        //   191: ldc_w           "current_AP"
        //   194: ldc_w           "method__10$RUBY$current_AP"
        //   197: ldc_w           "hash,0,0,-1"
        //   200: iconst_0       
        //   201: ldc             "./lib//lister/utils/wifiscan.rb"
        //   203: ldc_w           124
        //   206: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   209: ldc             "NONE"
        //   211: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   214: aload_1        
        //   215: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   218: goto            226
        //   221: aload_1        
        //   222: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   225: athrow         
        //   226: aload_1        
        //   227: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   230: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  27     218    221    226    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "each_AP_cmd", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__5$RUBY$each_AP_cmd(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString2(threadContext.runtime, 32);
    }
    
    @JRubyMethod(name = "rssi_for_quality", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__6$RUBY$rssi_for_quality(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject rubyObject2, final Block block) {
        return file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite6().call(threadContext, rubyObject, file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite7().call(threadContext, rubyObject, file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getFloat0(threadContext.runtime, 0.5), rubyObject2), 100L);
    }
    
    @JRubyMethod(name = "channel_for_freq", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__8$RUBY$channel_for_freq(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    15: bipush          10
        //    17: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    20: aload_1        
        //    21: aload_2        
        //    22: aload_0        
        //    23: aload_1        
        //    24: ldc_w           "FREQ_MAP"
        //    27: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getConstant3:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    30: aload           locals
        //    32: aload_1        
        //    33: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    36: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    39: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    42: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     29      5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    @JRubyMethod(name = "each_AP", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__9$RUBY$each_AP(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite(11).callIter(threadContext, self, file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getConstantFrom5(RuntimeHelpers.checkIsModule(file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getConstant4(threadContext, "Win32")), threadContext, "Wlan"), RuntimeHelpers.createBlock(threadContext, self, file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getBlockBody1(threadContext, "block_1$RUBY$each_AP,1,signal;channel;ap,false,2,./lib//lister/utils/wifiscan.rb,113,true")));
    }
    
    public static IRubyObject block_1$RUBY$each_AP(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    38: bipush          12
        //    40: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    43: aload_1        
        //    44: aload_2        
        //    45: aload_2        
        //    46: aload_0        
        //    47: bipush          13
        //    49: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    52: aload_1        
        //    53: aload_2        
        //    54: aload           signal
        //    56: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    59: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    62: astore          channel
        //    64: aload_0        
        //    65: bipush          14
        //    67: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    70: aload_1        
        //    71: aload_2        
        //    72: aload_0        
        //    73: aload_1        
        //    74: ldc             "AccessPoint"
        //    76: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getConstant6:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: aload_0        
        //    80: bipush          15
        //    82: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    85: aload_1        
        //    86: aload_2        
        //    87: aload           signal
        //    89: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    92: aload_0        
        //    93: bipush          16
        //    95: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    98: aload_1        
        //    99: aload_2        
        //   100: aload           signal
        //   102: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: aload           channel
        //   107: aload_0        
        //   108: bipush          17
        //   110: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   113: aload_1        
        //   114: aload_2        
        //   115: aload           signal
        //   117: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   120: aload_0        
        //   121: bipush          18
        //   123: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   126: aload_1        
        //   127: aload_2        
        //   128: aload           signal
        //   130: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   133: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   136: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   139: astore          ap
        //   141: aload_1        
        //   142: invokevirtual   org/jruby/runtime/ThreadContext.getFrameBlock:()Lorg/jruby/runtime/Block;
        //   145: aload_1        
        //   146: aload           ap
        //   148: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   151: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name     Signature
        //  -----  ------  ----  -------  ---------------------------------------
        //  37     115     9     signal   Lorg/jruby/runtime/builtin/IRubyObject;
        //  37     115     10    channel  Lorg/jruby/runtime/builtin/IRubyObject;
        //  37     115     11    ap       Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "current_AP", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__10$RUBY$current_AP(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(RubyHash.newHash(threadContext.runtime));
        file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite(19).callIter(threadContext, self, file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getConstantFrom8(RuntimeHelpers.checkIsModule(file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getConstant7(threadContext, "Win32")), threadContext, "Wlan"), RuntimeHelpers.createBlock(threadContext, self, file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getBlockBody2(threadContext, "block_2$RUBY$current_AP,1,status,false,2,./lib//lister/utils/wifiscan.rb,126,true")));
        return locals.getValueZeroDepthZeroOrNil(threadContext.nil);
    }
    
    public static IRubyObject block_2$RUBY$current_AP(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    40: bipush          20
        //    42: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    45: aload_0        
        //    46: bipush          21
        //    48: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    51: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //    54: aload_0        
        //    55: aload_1        
        //    56: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    59: ldc             "channel"
        //    61: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //    64: aload_0        
        //    65: bipush          22
        //    67: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    70: aload_1        
        //    71: aload_2        
        //    72: aload           status
        //    74: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: aload_1        
        //    78: aload_2        
        //    79: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    82: pop            
        //    83: aload           5
        //    85: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    88: aload_1        
        //    89: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    92: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    95: dup            
        //    96: aload_2        
        //    97: aload_0        
        //    98: bipush          23
        //   100: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   103: aload_0        
        //   104: bipush          24
        //   106: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   109: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   112: aload_0        
        //   113: aload_1        
        //   114: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   117: ldc_w           "ssid"
        //   120: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   123: aload_0        
        //   124: bipush          25
        //   126: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   129: aload_1        
        //   130: aload_2        
        //   131: aload           status
        //   133: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   136: aload_1        
        //   137: aload_2        
        //   138: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   141: pop            
        //   142: aload           5
        //   144: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   147: aload_1        
        //   148: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   151: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   154: dup            
        //   155: aload_2        
        //   156: aload_0        
        //   157: bipush          26
        //   159: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   162: aload_0        
        //   163: bipush          27
        //   165: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   168: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   171: aload_0        
        //   172: aload_1        
        //   173: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   176: ldc             "rssi"
        //   178: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   181: aload_0        
        //   182: bipush          28
        //   184: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   187: aload_1        
        //   188: aload_2        
        //   189: aload           status
        //   191: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   194: aload_1        
        //   195: aload_2        
        //   196: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   199: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  ---------------------------------------
        //  25     175     9     status  Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__4$RUBY$Windows(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__4$RUBY$Windows(file_4B197E63B21397B2B752F01B316CEB14EA4363B5, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__11$RUBY$WindowsLegacy(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc_w           "WindowsLegacy"
        //    11: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    14: dup            
        //    15: astore_2       
        //    16: aload_1        
        //    17: swap           
        //    18: aload_0        
        //    19: aload_1        
        //    20: ldc             ",0,0,-1"
        //    22: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getScope4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    25: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    28: aload_1        
        //    29: aload_2        
        //    30: aload_0        
        //    31: ldc_w           "each_AP_cmd"
        //    34: ldc_w           "method__12$RUBY$each_AP_cmd"
        //    37: ldc             ",0,0,-1"
        //    39: iconst_0       
        //    40: ldc             "./lib//lister/utils/wifiscan.rb"
        //    42: ldc_w           137
        //    45: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    48: ldc             "NONE"
        //    50: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    53: pop            
        //    54: aload_1        
        //    55: aload_2        
        //    56: aload_0        
        //    57: ldc_w           "each_AP"
        //    60: ldc_w           "method__13$RUBY$each_AP"
        //    63: ldc             ",0,0,-1"
        //    65: iconst_0       
        //    66: ldc             "./lib//lister/utils/wifiscan.rb"
        //    68: ldc_w           141
        //    71: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    74: ldc             "NONE"
        //    76: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: pop            
        //    80: aload_1        
        //    81: aload_2        
        //    82: aload_0        
        //    83: ldc_w           "current_AP"
        //    86: ldc_w           "method__14$RUBY$current_AP"
        //    89: ldc             ",0,0,-1"
        //    91: iconst_0       
        //    92: ldc             "./lib//lister/utils/wifiscan.rb"
        //    94: ldc_w           145
        //    97: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   100: ldc             "NONE"
        //   102: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   105: aload_1        
        //   106: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   109: goto            117
        //   112: aload_1        
        //   113: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   116: athrow         
        //   117: aload_1        
        //   118: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   121: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  28     109    112    117    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "each_AP_cmd", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__12$RUBY$each_AP_cmd(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString3(threadContext.runtime, 32);
    }
    
    @JRubyMethod(name = "each_AP", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__13$RUBY$each_AP(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return threadContext.nil;
    }
    
    @JRubyMethod(name = "current_AP", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__14$RUBY$current_AP(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return threadContext.nil;
    }
    
    public static IRubyObject module__11$RUBY$WindowsLegacy(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__11$RUBY$WindowsLegacy(file_4B197E63B21397B2B752F01B316CEB14EA4363B5, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__15$RUBY$Linux(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
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
        //    22: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getScope5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    25: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    28: aload_0        
        //    29: bipush          29
        //    31: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    34: aload_1        
        //    35: aload_2        
        //    36: aload_0        
        //    37: bipush          30
        //    39: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    42: aload_1        
        //    43: aload_2        
        //    44: aload_0        
        //    45: aload_1        
        //    46: ldc_w           "Hash"
        //    49: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getConstant9:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: aload_1        
        //    53: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    56: invokestatic    org/jruby/RubyFixnum.minus_one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //    59: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    62: aload_0        
        //    63: aload_1        
        //    64: aload_1        
        //    65: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    68: invokestatic    org/jruby/RubyHash.newHash:(Lorg/jruby/Ruby;)Lorg/jruby/RubyHash;
        //    71: invokestatic    ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.hash_builder_16:(Lruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/RubyHash;)Lorg/jruby/RubyHash;
        //    74: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    77: aload_1        
        //    78: ldc_w           "FREQ_MAP"
        //    81: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.setConstantInCurrent:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    84: pop            
        //    85: aload_1        
        //    86: aload_2        
        //    87: aload_0        
        //    88: ldc_w           "each_AP_cmd"
        //    91: ldc_w           "method__17$RUBY$each_AP_cmd"
        //    94: ldc             ",0,0,-1"
        //    96: iconst_0       
        //    97: ldc             "./lib//lister/utils/wifiscan.rb"
        //    99: ldc_w           237
        //   102: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   105: ldc             "NONE"
        //   107: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   110: pop            
        //   111: aload_1        
        //   112: aload_2        
        //   113: aload_0        
        //   114: ldc_w           "each_AP"
        //   117: ldc_w           "method__18$RUBY$each_AP"
        //   120: ldc_w           "data,0,0,-1"
        //   123: iconst_0       
        //   124: ldc             "./lib//lister/utils/wifiscan.rb"
        //   126: ldc_w           241
        //   129: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   132: ldc             "NONE"
        //   134: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   137: pop            
        //   138: aload_1        
        //   139: aload_2        
        //   140: aload_0        
        //   141: ldc_w           "parse_each_AP_data"
        //   144: ldc_w           "method__19$RUBY$parse_each_AP_data"
        //   147: ldc_w           "data;current_ap,1,0,-1"
        //   150: iconst_1       
        //   151: ldc             "./lib//lister/utils/wifiscan.rb"
        //   153: ldc_w           248
        //   156: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   159: ldc_w           "qdata"
        //   162: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   165: pop            
        //   166: aload_1        
        //   167: aload_2        
        //   168: aload_0        
        //   169: ldc_w           "sanitize_quoted_ssid_str"
        //   172: ldc_w           "method__20$RUBY$sanitize_quoted_ssid_str"
        //   175: ldc_w           "ssid_str,1,0,-1"
        //   178: iconst_1       
        //   179: ldc             "./lib//lister/utils/wifiscan.rb"
        //   181: ldc_w           273
        //   184: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   187: ldc_w           "qssid_str"
        //   190: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   193: pop            
        //   194: aload_1        
        //   195: aload_2        
        //   196: aload_0        
        //   197: ldc_w           "current_AP_cmd"
        //   200: ldc_w           "method__21$RUBY$current_AP_cmd"
        //   203: ldc             ",0,0,-1"
        //   205: iconst_0       
        //   206: ldc             "./lib//lister/utils/wifiscan.rb"
        //   208: ldc_w           277
        //   211: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   214: ldc             "NONE"
        //   216: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   219: pop            
        //   220: aload_1        
        //   221: aload_2        
        //   222: aload_0        
        //   223: ldc_w           "current_AP"
        //   226: ldc_w           "method__22$RUBY$current_AP"
        //   229: ldc_w           "current;data,0,0,-1"
        //   232: iconst_0       
        //   233: ldc             "./lib//lister/utils/wifiscan.rb"
        //   235: ldc_w           281
        //   238: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   241: ldc             "NONE"
        //   243: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   246: pop            
        //   247: aload_1        
        //   248: aload_2        
        //   249: aload_0        
        //   250: ldc_w           "freq_2_channel"
        //   253: ldc_w           "method__23$RUBY$freq_2_channel"
        //   256: ldc_w           "ghz,1,0,-1"
        //   259: iconst_1       
        //   260: ldc             "./lib//lister/utils/wifiscan.rb"
        //   262: ldc_w           287
        //   265: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   268: ldc_w           "qghz"
        //   271: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   274: pop            
        //   275: aload_1        
        //   276: aload_2        
        //   277: aload_0        
        //   278: ldc_w           "parse_current_AP_data"
        //   281: ldc_w           "method__24$RUBY$parse_current_AP_data"
        //   284: ldc_w           "data;infos;found_out_not_connected,1,0,-1"
        //   287: iconst_1       
        //   288: ldc             "./lib//lister/utils/wifiscan.rb"
        //   290: ldc_w           291
        //   293: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   296: ldc_w           "qdata"
        //   299: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   302: aload_1        
        //   303: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   306: goto            314
        //   309: aload_1        
        //   310: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   313: athrow         
        //   314: aload_1        
        //   315: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   318: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  28     306    309    314    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "each_AP_cmd", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__17$RUBY$each_AP_cmd(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString(threadContext.runtime, 82, 32);
    }
    
    @JRubyMethod(name = "each_AP", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__18$RUBY$each_AP(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite(31).call(threadContext, self, self, file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite(32).call(threadContext, self, self)));
        return file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite(33).callIter(threadContext, self, self, locals.getValueZeroDepthZeroOrNil(threadContext.nil), RuntimeHelpers.createBlock(threadContext, self, file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getBlockBody3(threadContext, "block_3$RUBY$each_AP,1,ap,false,2,./lib//lister/utils/wifiscan.rb,243,true")));
    }
    
    public static IRubyObject block_3$RUBY$each_AP(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    30: aload           ap
        //    32: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    35: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     11      9     ap    Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "parse_each_AP_data", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__19$RUBY$parse_each_AP_data(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    17: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    20: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    23: pop            
        //    24: aload_0        
        //    25: bipush          34
        //    27: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    30: aload_1        
        //    31: aload_2        
        //    32: aload_0        
        //    33: bipush          35
        //    35: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    38: aload_1        
        //    39: aload_2        
        //    40: aload           locals
        //    42: aload_1        
        //    43: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    46: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: aload_1        
        //    53: aload_2        
        //    54: aload_0        
        //    55: aload_1        
        //    56: ldc_w           "block_4$RUBY$parse_each_AP_data,1,l;ssid_str,false,2,./lib//lister/utils/wifiscan.rb,250,false"
        //    59: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getBlockBody4:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    62: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    65: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    68: pop            
        //    69: aload           locals
        //    71: aload_1        
        //    72: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    78: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    83: ifeq            104
        //    86: aload           4
        //    88: aload_1        
        //    89: aload           locals
        //    91: aload_1        
        //    92: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    95: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    98: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   101: goto            108
        //   104: aload_1        
        //   105: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   108: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     95      5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_4$RUBY$parse_each_AP_data(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    42: bipush          36
        //    44: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    47: aload_1        
        //    48: aload_2        
        //    49: aload_0        
        //    50: bipush          37
        //    52: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    55: aload_1        
        //    56: aload_2        
        //    57: aload           locals
        //    59: aload_1        
        //    60: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    63: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    69: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    72: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    77: ifeq            139
        //    80: aload           locals
        //    82: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //    85: aload_1        
        //    86: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    92: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    97: ifeq            124
        //   100: aload_1        
        //   101: invokevirtual   org/jruby/runtime/ThreadContext.getFrameBlock:()Lorg/jruby/runtime/Block;
        //   104: aload_1        
        //   105: aload           locals
        //   107: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   110: aload_1        
        //   111: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   114: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   117: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   120: pop            
        //   121: goto            124
        //   124: aload           locals
        //   126: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   129: aload_1        
        //   130: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   133: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   136: goto            840
        //   139: aload_0        
        //   140: bipush          38
        //   142: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   145: aload_1        
        //   146: aload_2        
        //   147: aload_0        
        //   148: bipush          39
        //   150: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   153: aload_1        
        //   154: aload_2        
        //   155: aload_0        
        //   156: bipush          40
        //   158: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   161: aload_1        
        //   162: aload_2        
        //   163: aload           locals
        //   165: aload_1        
        //   166: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   169: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   172: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   175: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   178: aload_0        
        //   179: aload_1        
        //   180: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   183: bipush          83
        //   185: bipush          32
        //   187: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   190: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   193: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   198: ifeq            364
        //   201: aload           locals
        //   203: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   206: aload_1        
        //   207: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   210: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   213: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   218: ifeq            245
        //   221: aload_1        
        //   222: invokevirtual   org/jruby/runtime/ThreadContext.getFrameBlock:()Lorg/jruby/runtime/Block;
        //   225: aload_1        
        //   226: aload           locals
        //   228: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   231: aload_1        
        //   232: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   235: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   238: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   241: pop            
        //   242: goto            245
        //   245: aload           locals
        //   247: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   250: aload_0        
        //   251: bipush          41
        //   253: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   256: aload_1        
        //   257: aload_2        
        //   258: aload_0        
        //   259: aload_1        
        //   260: ldc             "AccessPoint"
        //   262: bipush          10
        //   264: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   267: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   270: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   273: pop            
        //   274: aload           locals
        //   276: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   279: aload_1        
        //   280: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   283: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   286: dup            
        //   287: aload_2        
        //   288: aload_0        
        //   289: bipush          42
        //   291: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   294: aload_0        
        //   295: bipush          43
        //   297: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   300: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   303: aload_0        
        //   304: bipush          44
        //   306: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   309: aload_1        
        //   310: aload_2        
        //   311: aload_0        
        //   312: bipush          45
        //   314: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   317: aload_1        
        //   318: aload_2        
        //   319: aload_0        
        //   320: bipush          46
        //   322: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   325: aload_1        
        //   326: aload_2        
        //   327: aload_0        
        //   328: bipush          47
        //   330: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   333: aload_1        
        //   334: aload_2        
        //   335: aload           locals
        //   337: aload_1        
        //   338: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   341: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   344: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   347: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   350: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   353: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   356: aload_1        
        //   357: aload_2        
        //   358: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   361: goto            840
        //   364: aload           locals
        //   366: aload_1        
        //   367: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   370: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   373: aload_1        
        //   374: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //   377: astore          9
        //   379: aload_0        
        //   380: bipush          48
        //   382: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   385: aload_1        
        //   386: aload_2        
        //   387: aload           9
        //   389: aload_0        
        //   390: aload_1        
        //   391: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   394: aload_0        
        //   395: bipush          84
        //   397: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getByteList:(I)Lorg/jruby/util/ByteList;
        //   400: ldc_w           512
        //   403: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getRegexp0:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   406: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   409: ifeq            532
        //   412: aload           locals
        //   414: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   417: aload_1        
        //   418: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   421: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   424: dup            
        //   425: aload_2        
        //   426: aload_0        
        //   427: bipush          49
        //   429: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   432: aload_0        
        //   433: bipush          50
        //   435: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   438: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   441: aload_0        
        //   442: bipush          51
        //   444: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   447: aload_1        
        //   448: aload_2        
        //   449: aload_0        
        //   450: bipush          52
        //   452: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   455: aload_1        
        //   456: aload_2        
        //   457: aload_0        
        //   458: bipush          53
        //   460: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   463: aload_1        
        //   464: aload_2        
        //   465: aload_0        
        //   466: bipush          54
        //   468: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   471: aload_1        
        //   472: aload_2        
        //   473: aload_0        
        //   474: bipush          55
        //   476: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   479: aload_1        
        //   480: aload_2        
        //   481: aload           locals
        //   483: aload_1        
        //   484: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   487: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   490: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   493: aload_0        
        //   494: aload_1        
        //   495: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   498: bipush          85
        //   500: bipush          32
        //   502: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   505: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   508: aload_1        
        //   509: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   512: invokestatic    org/jruby/RubyFixnum.one:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   515: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   518: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   521: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   524: aload_1        
        //   525: aload_2        
        //   526: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   529: goto            840
        //   532: aload_0        
        //   533: bipush          56
        //   535: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   538: aload_1        
        //   539: aload_2        
        //   540: aload           9
        //   542: aload_0        
        //   543: aload_1        
        //   544: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   547: aload_0        
        //   548: bipush          86
        //   550: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getByteList:(I)Lorg/jruby/util/ByteList;
        //   553: ldc_w           512
        //   556: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getRegexp1:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   559: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   562: ifeq            685
        //   565: aload           locals
        //   567: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   570: aload_1        
        //   571: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   574: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   577: dup            
        //   578: aload_2        
        //   579: aload_0        
        //   580: bipush          57
        //   582: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   585: aload_0        
        //   586: bipush          58
        //   588: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   591: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   594: aload_0        
        //   595: bipush          59
        //   597: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   600: aload_1        
        //   601: aload_2        
        //   602: aload_0        
        //   603: bipush          60
        //   605: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   608: aload_1        
        //   609: aload_2        
        //   610: aload_0        
        //   611: bipush          61
        //   613: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   616: aload_1        
        //   617: aload_2        
        //   618: aload_0        
        //   619: bipush          62
        //   621: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   624: aload_1        
        //   625: aload_2        
        //   626: aload_0        
        //   627: bipush          63
        //   629: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   632: aload_1        
        //   633: aload_2        
        //   634: aload           locals
        //   636: aload_1        
        //   637: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   640: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   643: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   646: aload_1        
        //   647: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   650: invokestatic    org/jruby/RubyFixnum.two:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   653: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   656: aload_0        
        //   657: aload_1        
        //   658: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   661: bipush          87
        //   663: bipush          32
        //   665: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   668: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   671: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   674: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   677: aload_1        
        //   678: aload_2        
        //   679: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   682: goto            840
        //   685: aload_0        
        //   686: bipush          64
        //   688: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   691: aload_1        
        //   692: aload_2        
        //   693: aload           9
        //   695: aload_0        
        //   696: aload_1        
        //   697: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   700: aload_0        
        //   701: bipush          88
        //   703: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getByteList:(I)Lorg/jruby/util/ByteList;
        //   706: ldc_w           512
        //   709: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getRegexp2:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   712: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   715: ifeq            836
        //   718: aload           locals
        //   720: aload_0        
        //   721: bipush          65
        //   723: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   726: aload_1        
        //   727: aload_2        
        //   728: aload_0        
        //   729: bipush          66
        //   731: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   734: aload_1        
        //   735: aload_2        
        //   736: aload_0        
        //   737: bipush          67
        //   739: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   742: aload_1        
        //   743: aload_2        
        //   744: aload           locals
        //   746: aload_1        
        //   747: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   750: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   753: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   756: aload_0        
        //   757: aload_1        
        //   758: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   761: bipush          85
        //   763: bipush          32
        //   765: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   768: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   771: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   774: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   777: pop            
        //   778: aload           locals
        //   780: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   783: aload_1        
        //   784: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   787: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   790: dup            
        //   791: aload_2        
        //   792: aload_0        
        //   793: bipush          68
        //   795: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   798: aload_0        
        //   799: bipush          69
        //   801: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   804: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   807: aload_0        
        //   808: bipush          70
        //   810: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   813: aload_1        
        //   814: aload_2        
        //   815: aload_2        
        //   816: aload           locals
        //   818: aload_1        
        //   819: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   822: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   825: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   828: aload_1        
        //   829: aload_2        
        //   830: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   833: goto            840
        //   836: aload_1        
        //   837: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   840: areturn        
        //   841: pop            
        //   842: goto            41
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  41     800     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  41     841    841    845    Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    @JRubyMethod(name = "sanitize_quoted_ssid_str", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__20$RUBY$sanitize_quoted_ssid_str(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    15: bipush          71
        //    17: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    20: aload_1        
        //    21: aload_2        
        //    22: aload_0        
        //    23: bipush          72
        //    25: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    28: aload_1        
        //    29: aload_2        
        //    30: aload_0        
        //    31: bipush          73
        //    33: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    36: aload_1        
        //    37: aload_2        
        //    38: aload           locals
        //    40: aload_1        
        //    41: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    44: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: aload_0        
        //    48: aload_1        
        //    49: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    52: aload_0        
        //    53: bipush          89
        //    55: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getByteList:(I)Lorg/jruby/util/ByteList;
        //    58: ldc_w           512
        //    61: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getRegexp3:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //    64: aload_0        
        //    65: aload_1        
        //    66: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    69: bipush          90
        //    71: bipush          32
        //    73: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    76: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: aload_0        
        //    80: aload_1        
        //    81: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    84: aload_0        
        //    85: bipush          91
        //    87: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getByteList:(I)Lorg/jruby/util/ByteList;
        //    90: ldc_w           512
        //    93: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getRegexp4:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //    96: aload_0        
        //    97: aload_1        
        //    98: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   101: bipush          90
        //   103: bipush          32
        //   105: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   108: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   111: aload_0        
        //   112: aload_1        
        //   113: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   116: bipush          92
        //   118: bipush          32
        //   120: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   123: aload_0        
        //   124: aload_1        
        //   125: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   128: bipush          93
        //   130: bipush          32
        //   132: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   135: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   138: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     125     5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    @JRubyMethod(name = "current_AP_cmd", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__21$RUBY$current_AP_cmd(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString(threadContext.runtime, 94, 32);
    }
    
    @JRubyMethod(name = "current_AP", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__22$RUBY$current_AP(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        IRubyObject data = threadContext.nil;
        final IRubyObject current = threadContext.nil;
        data = file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite(74).call(threadContext, rubyObject, rubyObject, file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite(75).call(threadContext, rubyObject, rubyObject));
        return file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite(76).call(threadContext, rubyObject, rubyObject, data);
    }
    
    @JRubyMethod(name = "freq_2_channel", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__23$RUBY$freq_2_channel(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    15: bipush          77
        //    17: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    20: aload_1        
        //    21: aload_2        
        //    22: aload_0        
        //    23: aload_1        
        //    24: ldc_w           "FREQ_MAP"
        //    27: bipush          11
        //    29: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //    32: aload_0        
        //    33: bipush          78
        //    35: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    38: aload_1        
        //    39: aload_2        
        //    40: aload           locals
        //    42: aload_1        
        //    43: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    46: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    49: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     42      5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    @JRubyMethod(name = "parse_current_AP_data", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__24$RUBY$parse_current_AP_data(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    20: invokestatic    org/jruby/RubyHash.newHash:(Lorg/jruby/Ruby;)Lorg/jruby/RubyHash;
        //    23: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    26: pop            
        //    27: aload           locals
        //    29: aload_1        
        //    30: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    33: invokevirtual   org/jruby/Ruby.getFalse:()Lorg/jruby/RubyBoolean;
        //    36: aload_1        
        //    37: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //    40: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    43: pop            
        //    44: aload_0        
        //    45: bipush          79
        //    47: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    50: aload_1        
        //    51: aload_2        
        //    52: aload_0        
        //    53: bipush          80
        //    55: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    58: aload_1        
        //    59: aload_2        
        //    60: aload           locals
        //    62: aload_1        
        //    63: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    66: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    69: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    72: aload_1        
        //    73: aload_2        
        //    74: aload_0        
        //    75: aload_1        
        //    76: ldc_w           "block_5$RUBY$parse_current_AP_data,1,line;l;name;rest;essid_str;essid;f_pair;ghz;bssid;dBm_pair;dBm_str,false,2,./lib//lister/utils/wifiscan.rb,294,false"
        //    79: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getBlockBody7:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    82: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    85: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: pop            
        //    89: aload           locals
        //    91: aload_1        
        //    92: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    95: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    98: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     85      5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_5$RUBY$parse_current_AP_data(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    72: bipush          81
        //    74: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    77: aload_1        
        //    78: aload_2        
        //    79: aload           locals
        //    81: aload_1        
        //    82: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    85: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    88: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    91: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    94: pop            
        //    95: aload_0        
        //    96: bipush          82
        //    98: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   101: aload_1        
        //   102: aload_2        
        //   103: aload           locals
        //   105: aload_1        
        //   106: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   109: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   112: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   115: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   120: ifeq            212
        //   123: aload_0        
        //   124: bipush          83
        //   126: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   129: aload_1        
        //   130: aload_2        
        //   131: aload           locals
        //   133: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   136: aload_1        
        //   137: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   140: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   143: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   146: dup            
        //   147: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   152: ifeq            175
        //   155: pop            
        //   156: aload           locals
        //   158: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   161: aload_1        
        //   162: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   165: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   168: aload_1        
        //   169: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   172: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.negate:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   175: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   180: ifeq            194
        //   183: aload_1        
        //   184: aload_1        
        //   185: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   188: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.breakJump:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   191: goto            209
        //   194: aload           locals
        //   196: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   199: aload_1        
        //   200: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   203: invokestatic    org/jruby/RubyHash.newHash:(Lorg/jruby/Ruby;)Lorg/jruby/RubyHash;
        //   206: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   209: goto            1339
        //   212: aload           locals
        //   214: aload_1        
        //   215: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   218: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   221: aload_1        
        //   222: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //   225: astore          9
        //   227: aload_0        
        //   228: bipush          84
        //   230: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   233: aload_1        
        //   234: aload_2        
        //   235: aload           9
        //   237: aload_0        
        //   238: aload_1        
        //   239: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   242: aload_0        
        //   243: bipush          95
        //   245: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getByteList:(I)Lorg/jruby/util/ByteList;
        //   248: ldc_w           512
        //   251: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getRegexp5:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   254: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   257: ifeq            274
        //   260: aload_1        
        //   261: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //   264: aload_1        
        //   265: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   268: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.nextJump:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   271: goto            1339
        //   274: aload           locals
        //   276: aload_1        
        //   277: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   280: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   283: aload_1        
        //   284: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //   287: astore          10
        //   289: aload_0        
        //   290: bipush          85
        //   292: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   295: aload_1        
        //   296: aload_2        
        //   297: aload           10
        //   299: aload_0        
        //   300: aload_1        
        //   301: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   304: aload_0        
        //   305: bipush          96
        //   307: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getByteList:(I)Lorg/jruby/util/ByteList;
        //   310: ldc_w           512
        //   313: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getRegexp6:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   316: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   319: ifeq            739
        //   322: aload_0        
        //   323: bipush          86
        //   325: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   328: aload_1        
        //   329: aload_2        
        //   330: aload_0        
        //   331: bipush          87
        //   333: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   336: aload_1        
        //   337: aload_2        
        //   338: aload_0        
        //   339: bipush          88
        //   341: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   344: aload_1        
        //   345: aload_2        
        //   346: aload           locals
        //   348: aload_1        
        //   349: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   352: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   355: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   358: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   361: aload_0        
        //   362: aload_1        
        //   363: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   366: aload_0        
        //   367: bipush          97
        //   369: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getByteList:(I)Lorg/jruby/util/ByteList;
        //   372: ldc_w           512
        //   375: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getRegexp7:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   378: aload_1        
        //   379: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   382: invokestatic    org/jruby/RubyFixnum.two:(Lorg/jruby/Ruby;)Lorg/jruby/RubyFixnum;
        //   385: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   388: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.aryToAry:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   391: aload_1        
        //   392: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   395: iconst_1       
        //   396: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.ensureMultipleAssignableRubyArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;Z)Lorg/jruby/RubyArray;
        //   399: astore          11
        //   401: aload           11
        //   403: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilZero:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   406: aload           locals
        //   408: swap           
        //   409: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   412: pop            
        //   413: aload           11
        //   415: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilOne:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   418: aload           locals
        //   420: swap           
        //   421: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   424: pop            
        //   425: aload           11
        //   427: pop            
        //   428: aload           locals
        //   430: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   433: aload_1        
        //   434: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   437: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   440: dup            
        //   441: aload_2        
        //   442: aload_0        
        //   443: bipush          89
        //   445: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   448: aload_0        
        //   449: bipush          90
        //   451: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   454: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   457: aload_0        
        //   458: aload_1        
        //   459: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   462: ldc_w           "name"
        //   465: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getSymbol6:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   468: aload           locals
        //   470: aload_1        
        //   471: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   474: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   477: dup            
        //   478: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   483: ifne            499
        //   486: pop            
        //   487: aload_0        
        //   488: aload_1        
        //   489: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   492: bipush          98
        //   494: bipush          32
        //   496: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   499: aload_1        
        //   500: aload_2        
        //   501: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   504: pop            
        //   505: aload           locals
        //   507: aload_1        
        //   508: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   511: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   514: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   519: ifeq            732
        //   522: aload           6
        //   524: iconst_4       
        //   525: aload_0        
        //   526: bipush          91
        //   528: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   531: aload_1        
        //   532: aload_2        
        //   533: aload_0        
        //   534: bipush          92
        //   536: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   539: aload_1        
        //   540: aload_2        
        //   541: aload_0        
        //   542: bipush          93
        //   544: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   547: aload_1        
        //   548: aload_2        
        //   549: aload           locals
        //   551: aload_1        
        //   552: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   555: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   558: aload_0        
        //   559: aload_1        
        //   560: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   563: bipush          88
        //   565: bipush          32
        //   567: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   570: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   573: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   576: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   579: aastore        
        //   580: aload_0        
        //   581: bipush          94
        //   583: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   586: aload_1        
        //   587: aload_2        
        //   588: aload           6
        //   590: iconst_4       
        //   591: aaload         
        //   592: aload_0        
        //   593: aload_1        
        //   594: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   597: bipush          99
        //   599: bipush          32
        //   601: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   604: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   607: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   612: ifeq            638
        //   615: aload           locals
        //   617: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   620: aload_1        
        //   621: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   624: invokevirtual   org/jruby/Ruby.getTrue:()Lorg/jruby/RubyBoolean;
        //   627: aload_1        
        //   628: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //   631: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   634: pop            
        //   635: goto            638
        //   638: aload           6
        //   640: iconst_5       
        //   641: aload_0        
        //   642: bipush          95
        //   644: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   647: aload_1        
        //   648: aload_2        
        //   649: aload_2        
        //   650: aload           6
        //   652: iconst_4       
        //   653: aaload         
        //   654: dup            
        //   655: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   660: ifne            676
        //   663: pop            
        //   664: aload_0        
        //   665: aload_1        
        //   666: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   669: bipush          90
        //   671: bipush          32
        //   673: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   676: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   679: aastore        
        //   680: aload           locals
        //   682: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   685: aload_1        
        //   686: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   689: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   692: dup            
        //   693: aload_2        
        //   694: aload_0        
        //   695: bipush          96
        //   697: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   700: aload_0        
        //   701: bipush          97
        //   703: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   706: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   709: aload_0        
        //   710: aload_1        
        //   711: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   714: ldc_w           "ssid"
        //   717: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getSymbol5:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   720: aload           6
        //   722: iconst_5       
        //   723: aaload         
        //   724: aload_1        
        //   725: aload_2        
        //   726: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   729: goto            736
        //   732: aload_1        
        //   733: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   736: goto            1339
        //   739: aload_0        
        //   740: bipush          98
        //   742: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   745: aload_1        
        //   746: aload_2        
        //   747: aload           10
        //   749: aload_0        
        //   750: aload_1        
        //   751: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   754: aload_0        
        //   755: bipush          100
        //   757: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getByteList:(I)Lorg/jruby/util/ByteList;
        //   760: ldc_w           512
        //   763: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getRegexp8:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //   766: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //   769: ifeq            1126
        //   772: aload           6
        //   774: bipush          6
        //   776: aload_0        
        //   777: bipush          99
        //   779: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   782: aload_1        
        //   783: aload_2        
        //   784: aload_0        
        //   785: bipush          100
        //   787: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   790: aload_1        
        //   791: aload_2        
        //   792: aload           locals
        //   794: aload_1        
        //   795: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   798: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   801: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   804: aload_1        
        //   805: aload_2        
        //   806: aload_0        
        //   807: aload_1        
        //   808: ldc_w           "block_6$RUBY$parse_current_AP_data,1,a,false,2,./lib//lister/utils/wifiscan.rb,325,true"
        //   811: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getBlockBody5:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //   814: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //   817: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   820: aastore        
        //   821: aload           6
        //   823: bipush          6
        //   825: aaload         
        //   826: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   831: ifeq            994
        //   834: aload           6
        //   836: bipush          7
        //   838: aload_0        
        //   839: bipush          102
        //   841: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   844: aload_1        
        //   845: aload_2        
        //   846: aload_0        
        //   847: bipush          103
        //   849: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   852: aload_1        
        //   853: aload_2        
        //   854: aload           6
        //   856: bipush          6
        //   858: aaload         
        //   859: aload_0        
        //   860: aload_1        
        //   861: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   864: bipush          85
        //   866: bipush          32
        //   868: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   871: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   874: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   877: aastore        
        //   878: aload           locals
        //   880: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   883: aload_1        
        //   884: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   887: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   890: dup            
        //   891: aload_2        
        //   892: aload_0        
        //   893: bipush          104
        //   895: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   898: aload_0        
        //   899: bipush          105
        //   901: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   904: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   907: aload_0        
        //   908: aload_1        
        //   909: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   912: ldc_w           "GhZ"
        //   915: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getSymbol7:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   918: aload           6
        //   920: bipush          7
        //   922: aaload         
        //   923: aload_1        
        //   924: aload_2        
        //   925: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   928: pop            
        //   929: aload           locals
        //   931: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   934: aload_1        
        //   935: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   938: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   941: dup            
        //   942: aload_2        
        //   943: aload_0        
        //   944: bipush          106
        //   946: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   949: aload_0        
        //   950: bipush          107
        //   952: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   955: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   958: aload_0        
        //   959: aload_1        
        //   960: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   963: ldc             "channel"
        //   965: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getSymbol2:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //   968: aload_0        
        //   969: bipush          108
        //   971: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   974: aload_1        
        //   975: aload_2        
        //   976: aload_2        
        //   977: aload           6
        //   979: bipush          7
        //   981: aaload         
        //   982: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   985: aload_1        
        //   986: aload_2        
        //   987: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   990: pop            
        //   991: goto            994
        //   994: aload           6
        //   996: bipush          8
        //   998: aload_0        
        //   999: bipush          109
        //  1001: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1004: aload_1        
        //  1005: aload_2        
        //  1006: aload_0        
        //  1007: bipush          110
        //  1009: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1012: aload_1        
        //  1013: aload_2        
        //  1014: aload_0        
        //  1015: bipush          111
        //  1017: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1020: aload_1        
        //  1021: aload_2        
        //  1022: aload_0        
        //  1023: bipush          112
        //  1025: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1028: aload_1        
        //  1029: aload_2        
        //  1030: aload           locals
        //  1032: aload_1        
        //  1033: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //  1036: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1039: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1042: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1045: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1048: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1051: dup            
        //  1052: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //  1057: ifne            1073
        //  1060: pop            
        //  1061: aload_0        
        //  1062: aload_1        
        //  1063: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1066: bipush          90
        //  1068: bipush          32
        //  1070: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //  1073: aastore        
        //  1074: aload           locals
        //  1076: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //  1079: aload_1        
        //  1080: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //  1083: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1086: dup            
        //  1087: aload_2        
        //  1088: aload_0        
        //  1089: bipush          113
        //  1091: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1094: aload_0        
        //  1095: bipush          114
        //  1097: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1100: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //  1103: aload_0        
        //  1104: aload_1        
        //  1105: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1108: ldc             "bssid"
        //  1110: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getSymbol1:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //  1113: aload           6
        //  1115: bipush          8
        //  1117: aaload         
        //  1118: aload_1        
        //  1119: aload_2        
        //  1120: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1123: goto            1339
        //  1126: aload_0        
        //  1127: bipush          115
        //  1129: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1132: aload_1        
        //  1133: aload_2        
        //  1134: aload           10
        //  1136: aload_0        
        //  1137: aload_1        
        //  1138: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1141: aload_0        
        //  1142: bipush          102
        //  1144: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getByteList:(I)Lorg/jruby/util/ByteList;
        //  1147: ldc_w           512
        //  1150: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getRegexp9:(Lorg/jruby/Ruby;Lorg/jruby/util/ByteList;I)Lorg/jruby/RubyRegexp;
        //  1153: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.invokeEqqForCaseWhen:(Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Z
        //  1156: ifeq            1335
        //  1159: aload           6
        //  1161: bipush          9
        //  1163: aload_0        
        //  1164: bipush          116
        //  1166: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1169: aload_1        
        //  1170: aload_2        
        //  1171: aload_0        
        //  1172: bipush          117
        //  1174: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1177: aload_1        
        //  1178: aload_2        
        //  1179: aload           locals
        //  1181: aload_1        
        //  1182: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //  1185: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1188: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1191: aload_1        
        //  1192: aload_2        
        //  1193: aload_0        
        //  1194: aload_1        
        //  1195: ldc_w           "block_7$RUBY$parse_current_AP_data,1,a,false,2,./lib//lister/utils/wifiscan.rb,334,true"
        //  1198: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getBlockBody6:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //  1201: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //  1204: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1207: aastore        
        //  1208: aload           6
        //  1210: bipush          9
        //  1212: aaload         
        //  1213: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //  1218: ifeq            1328
        //  1221: aload           6
        //  1223: bipush          10
        //  1225: aload_0        
        //  1226: bipush          119
        //  1228: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1231: aload_1        
        //  1232: aload_2        
        //  1233: aload_0        
        //  1234: bipush          120
        //  1236: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1239: aload_1        
        //  1240: aload_2        
        //  1241: aload           6
        //  1243: bipush          9
        //  1245: aaload         
        //  1246: aload_0        
        //  1247: aload_1        
        //  1248: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1251: bipush          87
        //  1253: bipush          32
        //  1255: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //  1258: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1261: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1264: aastore        
        //  1265: aload           locals
        //  1267: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //  1270: aload_1        
        //  1271: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //  1274: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1277: dup            
        //  1278: aload_2        
        //  1279: aload_0        
        //  1280: bipush          121
        //  1282: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1285: aload_0        
        //  1286: bipush          122
        //  1288: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1291: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //  1294: aload_0        
        //  1295: aload_1        
        //  1296: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //  1299: ldc             "rssi"
        //  1301: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getSymbol3:(Lorg/jruby/Ruby;Ljava/lang/String;)Lorg/jruby/RubySymbol;
        //  1304: aload_0        
        //  1305: bipush          123
        //  1307: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //  1310: aload_1        
        //  1311: aload_2        
        //  1312: aload           6
        //  1314: bipush          10
        //  1316: aaload         
        //  1317: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1320: aload_1        
        //  1321: aload_2        
        //  1322: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //  1325: goto            1332
        //  1328: aload_1        
        //  1329: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //  1332: goto            1339
        //  1335: aload_1        
        //  1336: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //  1339: areturn        
        //  1340: pop            
        //  1341: goto            69
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  69     1271    5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  69     1340   1340   1344   Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    public static IRubyObject block_6$RUBY$parse_current_AP_data(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          101
        //    28: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           a
        //    35: aload_0        
        //    36: aload_1        
        //    37: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    40: bipush          101
        //    42: bipush          32
        //    44: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    47: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     26      9     a     Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject block_7$RUBY$parse_current_AP_data(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    26: bipush          118
        //    28: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    31: aload_1        
        //    32: aload_2        
        //    33: aload           a
        //    35: aload_0        
        //    36: aload_1        
        //    37: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    40: bipush          103
        //    42: bipush          32
        //    44: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    47: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     26      9     a     Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    public static IRubyObject module__15$RUBY$Linux(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__15$RUBY$Linux(file_4B197E63B21397B2B752F01B316CEB14EA4363B5, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__25$RUBY$MacOs(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 p0, final ThreadContext p1, final IRubyObject p2, final Block p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_1        
        //     2: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //     5: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.prepareClassNamespace:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyModule;
        //     8: ldc_w           "MacOs"
        //    11: invokevirtual   org/jruby/RubyModule.defineOrGetModuleUnder:(Ljava/lang/String;)Lorg/jruby/RubyModule;
        //    14: dup            
        //    15: astore_2       
        //    16: aload_1        
        //    17: swap           
        //    18: aload_0        
        //    19: aload_1        
        //    20: ldc             ",0,0,-1"
        //    22: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getScope6:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/parser/StaticScope;
        //    25: invokevirtual   org/jruby/runtime/ThreadContext.preCompiledClassDummyScope:(Lorg/jruby/RubyModule;Lorg/jruby/parser/StaticScope;)V
        //    28: aload_1        
        //    29: aload_2        
        //    30: aload_0        
        //    31: ldc_w           "clean_bssid"
        //    34: ldc_w           "method__26$RUBY$clean_bssid"
        //    37: ldc_w           "bssid,1,0,-1"
        //    40: iconst_1       
        //    41: ldc             "./lib//lister/utils/wifiscan.rb"
        //    43: ldc_w           348
        //    46: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    49: ldc_w           "qbssid"
        //    52: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: pop            
        //    56: aload_1        
        //    57: aload_2        
        //    58: aload_0        
        //    59: ldc_w           "each_AP_cmd"
        //    62: ldc_w           "method__27$RUBY$each_AP_cmd"
        //    65: ldc             ",0,0,-1"
        //    67: iconst_0       
        //    68: ldc             "./lib//lister/utils/wifiscan.rb"
        //    70: ldc_w           352
        //    73: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //    76: ldc             "NONE"
        //    78: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    81: pop            
        //    82: aload_1        
        //    83: aload_2        
        //    84: aload_0        
        //    85: ldc_w           "each_AP"
        //    88: ldc_w           "method__28$RUBY$each_AP"
        //    91: ldc             ",0,0,-1"
        //    93: iconst_0       
        //    94: ldc             "./lib//lister/utils/wifiscan.rb"
        //    96: ldc_w           356
        //    99: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   102: ldc             "NONE"
        //   104: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   107: pop            
        //   108: aload_1        
        //   109: aload_2        
        //   110: aload_0        
        //   111: ldc_w           "ary_not_aligned?"
        //   114: ldc_w           "method__29$RUBY$ary_not_aligned_p_"
        //   117: ldc_w           "ary,1,0,-1"
        //   120: iconst_1       
        //   121: ldc             "./lib//lister/utils/wifiscan.rb"
        //   123: ldc_w           363
        //   126: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   129: ldc_w           "qary"
        //   132: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   135: pop            
        //   136: aload_1        
        //   137: aload_2        
        //   138: aload_0        
        //   139: ldc_w           "parse_line"
        //   142: ldc_w           "method__30$RUBY$parse_line"
        //   145: ldc_w           "l;ary;e;b;r;c;rest;ap,1,0,-1"
        //   148: iconst_1       
        //   149: ldc             "./lib//lister/utils/wifiscan.rb"
        //   151: ldc_w           368
        //   154: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   157: ldc_w           "ql"
        //   160: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   163: pop            
        //   164: aload_1        
        //   165: aload_2        
        //   166: aload_0        
        //   167: ldc_w           "current_AP_cmd"
        //   170: ldc_w           "method__31$RUBY$current_AP_cmd"
        //   173: ldc             ",0,0,-1"
        //   175: iconst_0       
        //   176: ldc             "./lib//lister/utils/wifiscan.rb"
        //   178: ldc_w           381
        //   181: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   184: ldc             "NONE"
        //   186: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   189: pop            
        //   190: aload_1        
        //   191: aload_2        
        //   192: aload_0        
        //   193: ldc_w           "integer_keys"
        //   196: ldc_w           "method__32$RUBY$integer_keys"
        //   199: ldc             ",0,0,-1"
        //   201: iconst_0       
        //   202: ldc             "./lib//lister/utils/wifiscan.rb"
        //   204: ldc_w           385
        //   207: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameNoneScopeNone:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   210: ldc             "NONE"
        //   212: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   215: pop            
        //   216: aload_1        
        //   217: aload_2        
        //   218: aload_0        
        //   219: ldc_w           "current_AP"
        //   222: ldc_w           "method__33$RUBY$current_AP"
        //   225: ldc_w           "hash,0,0,-1"
        //   228: iconst_0       
        //   229: ldc             "./lib//lister/utils/wifiscan.rb"
        //   231: ldc_w           389
        //   234: getstatic       org/jruby/internal/runtime/methods/CallConfiguration.FrameFullScopeFull:Lorg/jruby/internal/runtime/methods/CallConfiguration;
        //   237: ldc             "NONE"
        //   239: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.def:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILorg/jruby/internal/runtime/methods/CallConfiguration;Ljava/lang/String;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   242: aload_1        
        //   243: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   246: goto            254
        //   249: aload_1        
        //   250: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   253: athrow         
        //   254: aload_1        
        //   255: invokevirtual   org/jruby/runtime/ThreadContext.postCompiledClass:()V
        //   258: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  28     246    249    254    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @JRubyMethod(name = "clean_bssid", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__26$RUBY$clean_bssid(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    15: bipush          124
        //    17: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    20: aload_1        
        //    21: aload_2        
        //    22: aload_0        
        //    23: bipush          125
        //    25: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    28: aload_1        
        //    29: aload_2        
        //    30: aload_0        
        //    31: bipush          126
        //    33: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    36: aload_1        
        //    37: aload_2        
        //    38: aload_0        
        //    39: bipush          127
        //    41: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    44: aload_1        
        //    45: aload_2        
        //    46: aload           locals
        //    48: aload_1        
        //    49: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    52: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    55: aload_0        
        //    56: aload_1        
        //    57: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    60: bipush          85
        //    62: bipush          32
        //    64: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    67: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    70: aload_1        
        //    71: aload_2        
        //    72: aload_0        
        //    73: aload_1        
        //    74: ldc_w           "block_8$RUBY$clean_bssid,1,c,false,2,./lib//lister/utils/wifiscan.rb,349,true"
        //    77: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getBlockBody8:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;)Lorg/jruby/runtime/BlockBody;
        //    80: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.createBlock:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/BlockBody;)Lorg/jruby/runtime/Block;
        //    83: invokevirtual   org/jruby/runtime/CallSite.callIter:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/Block;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    86: aload_0        
        //    87: aload_1        
        //    88: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    91: bipush          85
        //    93: bipush          32
        //    95: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    98: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   101: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   104: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     91      5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    public static IRubyObject block_8$RUBY$clean_bssid(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    30: sipush          128
        //    33: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    36: aload_1        
        //    37: aload_2        
        //    38: aload_0        
        //    39: aload_1        
        //    40: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    43: bipush          104
        //    45: bipush          32
        //    47: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //    50: aload_0        
        //    51: sipush          129
        //    54: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    57: aload_1        
        //    58: aload_2        
        //    59: aload           c
        //    61: aload_0        
        //    62: aload_1        
        //    63: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    66: bipush          67
        //    68: bipush          16
        //    70: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getFixnum:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyFixnum;
        //    73: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    76: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    79: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructRubyArray:(Lorg/jruby/Ruby;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //    82: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  25     58      9     c     Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "each_AP_cmd", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__27$RUBY$each_AP_cmd(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString(threadContext.runtime, 105, 32);
    }
    
    @JRubyMethod(name = "each_AP", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__28$RUBY$each_AP(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        return file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite(130).callIter(threadContext, self, file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite(131).call(threadContext, self, file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite(132).call(threadContext, self, self, file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite(133).call(threadContext, self, self))), RuntimeHelpers.createBlock(threadContext, self, file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getBlockBody9(threadContext, "block_9$RUBY$each_AP,1,l;ap,false,2,./lib//lister/utils/wifiscan.rb,357,true")));
    }
    
    public static IRubyObject block_9$RUBY$each_AP(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    32: sipush          134
        //    35: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    38: aload_1        
        //    39: aload_2        
        //    40: aload_2        
        //    41: aload           l
        //    43: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    46: astore          ap
        //    48: aload           ap
        //    50: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    55: ifeq            71
        //    58: aload_1        
        //    59: invokevirtual   org/jruby/runtime/ThreadContext.getFrameBlock:()Lorg/jruby/runtime/Block;
        //    62: aload_1        
        //    63: aload           ap
        //    65: invokevirtual   org/jruby/runtime/Block.yield:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    68: goto            75
        //    71: aload_1        
        //    72: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  31     45      9     l     Lorg/jruby/runtime/builtin/IRubyObject;
        //  31     45      10    ap    Lorg/jruby/runtime/builtin/IRubyObject;
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
    
    @JRubyMethod(name = "ary_not_aligned?", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__29$RUBY$ary_not_aligned_p_(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    15: sipush          135
        //    18: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    21: aload_1        
        //    22: aload_2        
        //    23: aload           locals
        //    25: aload_1        
        //    26: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    29: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    32: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    35: dup            
        //    36: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    41: ifeq            124
        //    44: pop            
        //    45: aload_0        
        //    46: sipush          136
        //    49: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    52: aload_1        
        //    53: aload_2        
        //    54: aload_0        
        //    55: sipush          137
        //    58: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    61: aload_1        
        //    62: aload_2        
        //    63: aload_0        
        //    64: sipush          138
        //    67: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    70: aload_1        
        //    71: aload_2        
        //    72: aload_0        
        //    73: sipush          139
        //    76: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    79: aload_1        
        //    80: aload_2        
        //    81: aload           locals
        //    83: aload_1        
        //    84: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    87: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    90: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    93: aload_0        
        //    94: aload_1        
        //    95: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    98: bipush          85
        //   100: bipush          32
        //   102: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   105: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   108: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   111: ldc2_w          6
        //   114: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;J)Lorg/jruby/runtime/builtin/IRubyObject;
        //   117: aload_1        
        //   118: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   121: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.negate:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   124: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  14     111     5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    @JRubyMethod(name = "parse_line", frame = true, required = 1, optional = 0, rest = -1)
    public static IRubyObject method__30$RUBY$parse_line(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    33: sipush          140
        //    36: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    39: aload_1        
        //    40: aload_2        
        //    41: aload           locals
        //    43: aload_1        
        //    44: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    47: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    50: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    53: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    56: pop            
        //    57: aload_0        
        //    58: sipush          141
        //    61: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    64: aload_1        
        //    65: aload_2        
        //    66: aload           locals
        //    68: aload_1        
        //    69: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    72: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    75: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    78: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //    83: ifeq            95
        //    86: aload_1        
        //    87: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    90: areturn        
        //    91: nop            
        //    92: nop            
        //    93: nop            
        //    94: athrow         
        //    95: aload           locals
        //    97: aload_0        
        //    98: aload_1        
        //    99: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   102: bipush          90
        //   104: bipush          32
        //   106: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   109: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   112: pop            
        //   113: goto            201
        //   116: aload_0        
        //   117: sipush          142
        //   120: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   123: aload_1        
        //   124: aload_2        
        //   125: aload           locals
        //   127: aload_1        
        //   128: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   131: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   134: aload_0        
        //   135: sipush          143
        //   138: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   141: aload_1        
        //   142: aload_2        
        //   143: aload           locals
        //   145: aload_1        
        //   146: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   149: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   152: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   155: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   158: pop            
        //   159: aload_0        
        //   160: sipush          144
        //   163: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   166: aload_1        
        //   167: aload_2        
        //   168: aload           locals
        //   170: aload_1        
        //   171: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   174: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   177: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   180: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   185: ifeq            196
        //   188: aload_1        
        //   189: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   192: areturn        
        //   193: nop            
        //   194: nop            
        //   195: athrow         
        //   196: aload_1        
        //   197: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   200: pop            
        //   201: aload_0        
        //   202: sipush          145
        //   205: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   208: aload_1        
        //   209: aload_2        
        //   210: aload_2        
        //   211: aload           locals
        //   213: aload_1        
        //   214: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   217: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   220: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   223: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   228: ifne            116
        //   231: aload_1        
        //   232: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   235: aload_1        
        //   236: invokevirtual   org/jruby/runtime/ThreadContext.pollThreadEvents:()V
        //   239: pop            
        //   240: aload           locals
        //   242: aload_1        
        //   243: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   246: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   249: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.splatValue:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/RubyArray;
        //   252: aload_1        
        //   253: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   256: iconst_1       
        //   257: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.ensureMultipleAssignableRubyArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;Z)Lorg/jruby/RubyArray;
        //   260: astore          9
        //   262: aload           9
        //   264: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilZero:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   267: aload           locals
        //   269: swap           
        //   270: invokevirtual   org/jruby/runtime/DynamicScope.setValueThreeDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   273: pop            
        //   274: aload           9
        //   276: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilOne:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   279: aload           6
        //   281: swap           
        //   282: iconst_4       
        //   283: swap           
        //   284: aastore        
        //   285: aload           9
        //   287: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilTwo:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   290: aload           6
        //   292: swap           
        //   293: iconst_5       
        //   294: swap           
        //   295: aastore        
        //   296: aload           9
        //   298: iconst_3       
        //   299: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNil:(Lorg/jruby/RubyArray;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   302: aload           6
        //   304: swap           
        //   305: bipush          6
        //   307: swap           
        //   308: aastore        
        //   309: aload           9
        //   311: pop            
        //   312: aload           6
        //   314: bipush          7
        //   316: aload_0        
        //   317: sipush          146
        //   320: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   323: aload_1        
        //   324: aload_2        
        //   325: aload_0        
        //   326: aload_1        
        //   327: ldc             "AccessPoint"
        //   329: bipush          12
        //   331: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getConstant:(Lorg/jruby/runtime/ThreadContext;Ljava/lang/String;I)Lorg/jruby/runtime/builtin/IRubyObject;
        //   334: aload           locals
        //   336: aload_1        
        //   337: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   340: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   343: aload_0        
        //   344: sipush          147
        //   347: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   350: aload_1        
        //   351: aload_2        
        //   352: aload_2        
        //   353: aload           locals
        //   355: aload_1        
        //   356: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   359: invokevirtual   org/jruby/runtime/DynamicScope.getValueThreeDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   362: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   365: aload_0        
        //   366: sipush          148
        //   369: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   372: aload_1        
        //   373: aload_2        
        //   374: aload           6
        //   376: iconst_5       
        //   377: aaload         
        //   378: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   381: aload_0        
        //   382: sipush          149
        //   385: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   388: aload_1        
        //   389: aload_2        
        //   390: aload           6
        //   392: iconst_4       
        //   393: aaload         
        //   394: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   397: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.constructObjectArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)[Lorg/jruby/runtime/builtin/IRubyObject;
        //   400: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;[Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   403: dup_x2         
        //   404: aastore        
        //   405: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  30     376     5     locals  Lorg/jruby/runtime/DynamicScope;
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
    
    @JRubyMethod(name = "current_AP_cmd", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__31$RUBY$current_AP_cmd(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString(threadContext.runtime, 106, 32);
    }
    
    @JRubyMethod(name = "integer_keys", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__32$RUBY$integer_keys(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        return RubyArray.newArrayNoCopy(threadContext.runtime, new IRubyObject[] { file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString(threadContext.runtime, 107, 32), file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString(threadContext.runtime, 108, 32), file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString(threadContext.runtime, 109, 32), file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString(threadContext.runtime, 110, 32), file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString(threadContext.runtime, 111, 32), file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString(threadContext.runtime, 112, 32), file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString(threadContext.runtime, 113, 32), file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString(threadContext.runtime, 114, 32), file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString(threadContext.runtime, 115, 32) });
    }
    
    @JRubyMethod(name = "current_AP", frame = true, required = 0, optional = 0, rest = -1)
    public static IRubyObject method__33$RUBY$current_AP(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5, final ThreadContext threadContext, final IRubyObject self, final Block block) {
        final DynamicScope locals = threadContext.getCurrentScope();
        locals.setValueZeroDepthZero(RubyHash.newHash(threadContext.runtime));
        file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite(150).callIter(threadContext, self, file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite(151).call(threadContext, self, file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite(152).call(threadContext, self, self, file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite(153).call(threadContext, self, self))), RuntimeHelpers.createBlock(threadContext, self, file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getBlockBody(threadContext, 10, "block_10$RUBY$current_AP,1,l;k;val,false,2,./lib//lister/utils/wifiscan.rb,391,false")));
        return locals.getValueZeroDepthZeroOrNil(threadContext.nil);
    }
    
    public static IRubyObject block_10$RUBY$current_AP(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 p0, final ThreadContext p1, final IRubyObject p2, final IRubyObject p3, final Block p4) {
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
        //    47: aload_0        
        //    48: sipush          154
        //    51: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    54: aload_1        
        //    55: aload_2        
        //    56: aload_0        
        //    57: sipush          155
        //    60: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    63: aload_1        
        //    64: aload_2        
        //    65: aload_0        
        //    66: sipush          156
        //    69: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //    72: aload_1        
        //    73: aload_2        
        //    74: aload           locals
        //    76: aload_1        
        //    77: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //    80: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    83: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    86: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //    89: aload_0        
        //    90: aload_1        
        //    91: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //    94: bipush          116
        //    96: bipush          32
        //    98: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   101: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   104: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.aryToAry:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   107: aload_1        
        //   108: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   111: iconst_1       
        //   112: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.ensureMultipleAssignableRubyArray:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/Ruby;Z)Lorg/jruby/RubyArray;
        //   115: astore          9
        //   117: aload           9
        //   119: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilZero:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   122: aload           locals
        //   124: swap           
        //   125: invokevirtual   org/jruby/runtime/DynamicScope.setValueOneDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   128: pop            
        //   129: aload           9
        //   131: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.arrayEntryOrNilOne:(Lorg/jruby/RubyArray;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   134: aload           locals
        //   136: swap           
        //   137: invokevirtual   org/jruby/runtime/DynamicScope.setValueTwoDepthZero:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   140: pop            
        //   141: aload           9
        //   143: pop            
        //   144: aload           locals
        //   146: invokevirtual   org/jruby/runtime/DynamicScope.getNextCapturedScope:()Lorg/jruby/runtime/DynamicScope;
        //   149: aload_1        
        //   150: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   153: invokevirtual   org/jruby/runtime/DynamicScope.getValueZeroDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   156: dup            
        //   157: aload_2        
        //   158: aload_0        
        //   159: sipush          157
        //   162: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   165: aload_0        
        //   166: sipush          158
        //   169: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   172: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.selectAttrAsgnCallSite:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/CallSite;)Lorg/jruby/runtime/CallSite;
        //   175: aload           locals
        //   177: aload_1        
        //   178: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   181: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   184: aload_0        
        //   185: sipush          159
        //   188: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   191: aload_1        
        //   192: aload_2        
        //   193: aload_0        
        //   194: sipush          160
        //   197: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   200: aload_1        
        //   201: aload_2        
        //   202: aload_2        
        //   203: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   206: aload           locals
        //   208: aload_1        
        //   209: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   212: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   215: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   218: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   223: ifeq            250
        //   226: aload_0        
        //   227: sipush          161
        //   230: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   233: aload_1        
        //   234: aload_2        
        //   235: aload           locals
        //   237: aload_1        
        //   238: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   241: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   244: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   247: goto            325
        //   250: aload_0        
        //   251: sipush          162
        //   254: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   257: aload_1        
        //   258: aload_2        
        //   259: aload           locals
        //   261: aload_1        
        //   262: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   265: invokevirtual   org/jruby/runtime/DynamicScope.getValueOneDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   268: aload_0        
        //   269: aload_1        
        //   270: getfield        org/jruby/runtime/ThreadContext.runtime:Lorg/jruby/Ruby;
        //   273: bipush          117
        //   275: bipush          32
        //   277: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString:(Lorg/jruby/Ruby;II)Lorg/jruby/RubyString;
        //   280: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   283: invokeinterface org/jruby/runtime/builtin/IRubyObject.isTrue:()Z
        //   288: ifeq            316
        //   291: aload_0        
        //   292: sipush          163
        //   295: invokevirtual   ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite:(I)Lorg/jruby/runtime/CallSite;
        //   298: aload_1        
        //   299: aload_2        
        //   300: aload_2        
        //   301: aload           locals
        //   303: aload_1        
        //   304: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   307: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   310: invokevirtual   org/jruby/runtime/CallSite.call:(Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   313: goto            325
        //   316: aload           locals
        //   318: aload_1        
        //   319: getfield        org/jruby/runtime/ThreadContext.nil:Lorg/jruby/runtime/builtin/IRubyObject;
        //   322: invokevirtual   org/jruby/runtime/DynamicScope.getValueTwoDepthZeroOrNil:(Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   325: aload_1        
        //   326: aload_2        
        //   327: invokestatic    org/jruby/javasupport/util/RuntimeHelpers.doAttrAsgn:(Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/CallSite;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/builtin/IRubyObject;Lorg/jruby/runtime/ThreadContext;Lorg/jruby/runtime/builtin/IRubyObject;)Lorg/jruby/runtime/builtin/IRubyObject;
        //   330: areturn        
        //   331: pop            
        //   332: goto            47
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------
        //  47     284     5     locals  Lorg/jruby/runtime/DynamicScope;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  47     331    331    335    Lorg/jruby/exceptions/JumpException$RedoJump;
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
    
    public static IRubyObject module__25$RUBY$MacOs(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__25$RUBY$MacOs(file_4B197E63B21397B2B752F01B316CEB14EA4363B5, threadContext, rubyObject, block);
    }
    
    public static IRubyObject chained_34_rescue_1$RUBY$SYNTHETICWifiScan(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        context.getCurrentScope();
        final IRubyObject errorInfo = context.getErrorInfo();
        IRubyObject rubyObject2 = null;
        Label_0124: {
            try {
                try {
                    file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite(169).call(context, rubyObject, rubyObject, file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString(context.runtime, 118, 32));
                    rubyObject2 = file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite(170).call(context, rubyObject, rubyObject, file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getConstant(context, "Windows", 18));
                }
                catch (JumpException.FlowControlException ex) {
                    throw ex;
                }
                catch (Throwable t) {
                    if (RuntimeHelpers.isJavaExceptionHandled(t, file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getConstant(context, "LoadError", 19), context).isTrue()) {
                        RuntimeHelpers.storeExceptionInErrorInfo(t, context);
                        rubyObject2 = chained_35_rescue_line_413(file_4B197E63B21397B2B752F01B316CEB14EA4363B5, context, rubyObject, block);
                        RuntimeHelpers.clearErrorInfo(context);
                        break Label_0124;
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
    
    public static IRubyObject chained_35_rescue_line_413(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        context.getCurrentScope().setValueZeroDepthZero(RuntimeHelpers.getGlobalVariable(context.runtime, "$!"));
        file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite(171).call(context, rubyObject, rubyObject, file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getString(context.runtime, 119, 32));
        return file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getCallSite(172).call(context, rubyObject, rubyObject, file_4B197E63B21397B2B752F01B316CEB14EA4363B5.getConstant(context, "WindowsLegacy", 20));
    }
    
    public static IRubyObject module__2$RUBY$WifiScan(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__2$RUBY$WifiScan(file_4B197E63B21397B2B752F01B316CEB14EA4363B5, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__1$RUBY$Utils(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__1$RUBY$Utils(file_4B197E63B21397B2B752F01B316CEB14EA4363B5, threadContext, rubyObject, block);
    }
    
    public static IRubyObject module__0$RUBY$Lister(final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] args, final Block block) {
        Arity.checkArgumentCount(threadContext.getRuntime(), args, 0, 0);
        return module__0$RUBY$Lister(file_4B197E63B21397B2B752F01B316CEB14EA4363B5, threadContext, rubyObject, block);
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
        final FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5 file_4B197E63B21397B2B752F01B316CEB14EA4363B5 = new FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5();
        final String string = FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.class.getClassLoader().getResource("ruby/jit/FILE_4B197E63B21397B2B752F01B316CEB14EA4363B5.class").toString();
        file_4B197E63B21397B2B752F01B316CEB14EA4363B5.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_4B197E63B21397B2B752F01B316CEB14EA4363B5.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
