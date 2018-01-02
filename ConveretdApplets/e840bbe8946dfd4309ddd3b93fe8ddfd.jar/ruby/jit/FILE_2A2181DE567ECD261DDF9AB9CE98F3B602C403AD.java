// 
// Decompiled by Procyon v0.5.30
// 

package ruby.jit;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.exceptions.JumpException;
import org.jruby.util.ByteList;
import org.jruby.anno.JRubyMethod;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.ast.executable.RuntimeCache;
import org.jruby.runtime.ThreadContext;
import org.jruby.ast.executable.AbstractScript;

public class FILE_2A2181DE567ECD261DDF9AB9CE98F3B602C403AD extends AbstractScript
{
    public FILE_2A2181DE567ECD261DDF9AB9CE98F3B602C403AD() {
        this.filename = "./lib//lister/portability_tricks.rb";
        super.runtimeCache = new RuntimeCache();
        this.initFromDescriptor("\uffff\u0000\u0000\u0000\u0000\t\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000");
    }
    
    @JRubyMethod(name = "__file__", frame = true, required = 0, optional = 0, rest = -2)
    public static IRubyObject __file__(final FILE_2A2181DE567ECD261DDF9AB9CE98F3B602C403AD file_2A2181DE567ECD261DDF9AB9CE98F3B602C403AD, final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return RuntimeHelpers.stringOrNil(rescue_1$RUBY$SYNTHETIC__file__(file_2A2181DE567ECD261DDF9AB9CE98F3B602C403AD, threadContext, rubyObject, block), threadContext).isTrue() ? threadContext.nil : (RuntimeHelpers.stringOrNil(rescue_2$RUBY$SYNTHETIC__file__(file_2A2181DE567ECD261DDF9AB9CE98F3B602C403AD, threadContext, rubyObject, block), threadContext).isTrue() ? RuntimeHelpers.setConstantInModule(file_2A2181DE567ECD261DDF9AB9CE98F3B602C403AD.getConstant3(threadContext, "Errno"), file_2A2181DE567ECD261DDF9AB9CE98F3B602C403AD.getConstantFrom6(RuntimeHelpers.checkIsModule(file_2A2181DE567ECD261DDF9AB9CE98F3B602C403AD.getConstantFrom5(RuntimeHelpers.checkIsModule(file_2A2181DE567ECD261DDF9AB9CE98F3B602C403AD.getConstant4(threadContext, "Errno")), threadContext, "Errno")), threadContext, "EACCESS"), "EACCESS", threadContext) : RuntimeHelpers.setConstantInModule(file_2A2181DE567ECD261DDF9AB9CE98F3B602C403AD.getConstant7(threadContext, "Errno"), file_2A2181DE567ECD261DDF9AB9CE98F3B602C403AD.getConstant8(threadContext, "Exception"), "EACCESS", threadContext));
    }
    
    @Override
    public IRubyObject __file__(final ThreadContext threadContext, final IRubyObject rubyObject, final IRubyObject[] array, final Block block) {
        return __file__(this, threadContext, rubyObject, array, block);
    }
    
    public static /* synthetic */ ByteList rescue_1$RUBY$SYNTHETIC__file__(final FILE_2A2181DE567ECD261DDF9AB9CE98F3B602C403AD file_2A2181DE567ECD261DDF9AB9CE98F3B602C403AD, final ThreadContext context, final IRubyObject rubyObject, final Block block) {
        final IRubyObject errorInfo = context.getErrorInfo();
        context.getCurrentScope();
        ByteList definedConstantOrBoundMethod;
        try {
            definedConstantOrBoundMethod = RuntimeHelpers.getDefinedConstantOrBoundMethod(file_2A2181DE567ECD261DDF9AB9CE98F3B602C403AD.getConstant0(context, "Errno"), "EACCESS");
        }
        catch (JumpException ex) {
            context.setErrorInfo(errorInfo);
            definedConstantOrBoundMethod = null;
        }
        return definedConstantOrBoundMethod;
    }
    
    public static /* synthetic */ ByteList rescue_2$RUBY$SYNTHETIC__file__(final FILE_2A2181DE567ECD261DDF9AB9CE98F3B602C403AD file_2A2181DE567ECD261DDF9AB9CE98F3B602C403AD, final ThreadContext threadContext, final IRubyObject rubyObject, final Block block) {
        final IRubyObject errorInfo = threadContext.getErrorInfo();
        threadContext.getCurrentScope();
        ByteList definedConstantOrBoundMethod;
        try {
            definedConstantOrBoundMethod = RuntimeHelpers.getDefinedConstantOrBoundMethod(file_2A2181DE567ECD261DDF9AB9CE98F3B602C403AD.getConstantFrom2(RuntimeHelpers.checkIsModule(file_2A2181DE567ECD261DDF9AB9CE98F3B602C403AD.getConstant1(threadContext, "Errno")), threadContext, "Errno"), "EACCESS");
        }
        catch (JumpException ex) {
            threadContext.setErrorInfo(errorInfo);
            definedConstantOrBoundMethod = null;
        }
        return definedConstantOrBoundMethod;
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
        final FILE_2A2181DE567ECD261DDF9AB9CE98F3B602C403AD file_2A2181DE567ECD261DDF9AB9CE98F3B602C403AD = new FILE_2A2181DE567ECD261DDF9AB9CE98F3B602C403AD();
        final String string = FILE_2A2181DE567ECD261DDF9AB9CE98F3B602C403AD.class.getClassLoader().getResource("ruby/jit/FILE_2A2181DE567ECD261DDF9AB9CE98F3B602C403AD.class").toString();
        file_2A2181DE567ECD261DDF9AB9CE98F3B602C403AD.setFilename(string);
        final RubyInstanceConfig config = new RubyInstanceConfig();
        config.setArgv(argv);
        config.setScriptFileName(string);
        final Ruby instance = Ruby.newInstance(config);
        file_2A2181DE567ECD261DDF9AB9CE98F3B602C403AD.load(instance.getCurrentContext(), instance.getTopSelf(), IRubyObject.NULL_ARRAY, Block.NULL_BLOCK);
    }
}
