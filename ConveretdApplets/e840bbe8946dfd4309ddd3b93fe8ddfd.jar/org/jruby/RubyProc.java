// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.BlockBody;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.MethodBlock;
import org.jruby.runtime.Binding;
import org.jruby.exceptions.JumpException;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import org.jruby.parser.StaticScope;
import org.jruby.parser.BlockStaticScope;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.runtime.Block;
import org.jruby.anno.JRubyClass;
import org.jruby.runtime.marshal.DataType;

@JRubyClass(name = { "Proc" })
public class RubyProc extends RubyObject implements DataType
{
    private Block block;
    private Block.Type type;
    private ISourcePosition sourcePosition;
    private static ObjectAllocator PROC_ALLOCATOR;
    
    protected RubyProc(final Ruby runtime, final RubyClass rubyClass, final Block.Type type) {
        super(runtime, rubyClass);
        this.block = Block.NULL_BLOCK;
        this.type = type;
    }
    
    protected RubyProc(final Ruby runtime, final RubyClass rubyClass, final Block.Type type, final ISourcePosition sourcePosition) {
        this(runtime, rubyClass, type);
        this.sourcePosition = sourcePosition;
    }
    
    public static RubyClass createProcClass(final Ruby runtime) {
        final RubyClass procClass = runtime.defineClass("Proc", runtime.getObject(), RubyProc.PROC_ALLOCATOR);
        runtime.setProc(procClass);
        procClass.index = 33;
        procClass.setReifiedClass(RubyProc.class);
        procClass.defineAnnotatedMethods(RubyProc.class);
        return procClass;
    }
    
    public Block getBlock() {
        return this.block;
    }
    
    @Deprecated
    public static RubyProc newProc(final Ruby runtime, final Block.Type type) {
        throw runtime.newRuntimeError("deprecated RubyProc.newProc with no block; do not use");
    }
    
    public static RubyProc newProc(final Ruby runtime, final Block block, final Block.Type type) {
        return newProc(runtime, block, type, null);
    }
    
    public static RubyProc newProc(final Ruby runtime, final Block block, final Block.Type type, final ISourcePosition sourcePosition) {
        final RubyProc proc = new RubyProc(runtime, runtime.getProc(), type, sourcePosition);
        proc.setup(block);
        return proc;
    }
    
    @JRubyMethod(name = { "new" }, rest = true, meta = true)
    public static IRubyObject newInstance(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, Block block) {
        if (!block.isGiven()) {
            block = context.getCurrentFrame().getBlock();
        }
        if (block.isGiven() && block.getProcObject() != null && block.getProcObject().getMetaClass() == recv) {
            return block.getProcObject();
        }
        final RubyProc obj = (RubyProc)((RubyClass)recv).allocate();
        obj.setup(block);
        obj.callMethod(context, "initialize", args, block);
        return obj;
    }
    
    private void setup(final Block procBlock) {
        if (!procBlock.isGiven()) {
            throw this.getRuntime().newArgumentError("tried to create Proc object without a block");
        }
        if (!this.isLambda() || procBlock == null) {}
        this.block = procBlock.cloneBlock();
        if (this.isThread()) {
            final StaticScope oldScope = this.block.getBody().getStaticScope();
            final StaticScope newScope = new BlockStaticScope(oldScope.getEnclosingScope(), oldScope.getVariables());
            newScope.setBackrefLastlineScope(true);
            newScope.setPreviousCRefScope(oldScope.getPreviousCRefScope());
            newScope.setModule(oldScope.getModule());
            this.block.getBody().setStaticScope(newScope);
        }
        this.block.getBinding().setFile(this.block.getBody().getFile());
        this.block.getBinding().setLine(this.block.getBody().getLine());
        this.block.type = this.type;
        this.block.setProcObject(this);
    }
    
    @JRubyMethod(name = { "clone" })
    public IRubyObject rbClone() {
        final RubyProc newProc = newProc(this.getRuntime(), this.block, this.type, this.sourcePosition);
        return newProc;
    }
    
    @JRubyMethod(name = { "dup" })
    public IRubyObject dup() {
        final RubyProc newProc = newProc(this.getRuntime(), this.block, this.type, this.sourcePosition);
        return newProc;
    }
    
    @JRubyMethod(name = { "==" }, required = 1)
    public IRubyObject op_equal(final IRubyObject other) {
        if (!(other instanceof RubyProc)) {
            return this.getRuntime().getFalse();
        }
        if (this == other || this.block.equals(((RubyProc)other).block)) {
            return this.getRuntime().getTrue();
        }
        return this.getRuntime().getFalse();
    }
    
    @JRubyMethod(name = { "to_s" }, compat = CompatVersion.RUBY1_8)
    public IRubyObject to_s() {
        return RubyString.newString(this.getRuntime(), "#<Proc:0x" + Integer.toString(this.block.hashCode(), 16) + "@" + this.block.getBody().getFile() + ":" + (this.block.getBody().getLine() + 1) + ">");
    }
    
    @JRubyMethod(name = { "to_s" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject to_s19() {
        final StringBuilder sb = new StringBuilder("#<Proc:0x" + Integer.toString(this.block.hashCode(), 16) + "@" + this.block.getBody().getFile() + ":" + (this.block.getBody().getLine() + 1));
        if (this.isLambda()) {
            sb.append(" (lambda)");
        }
        sb.append(">");
        return RubyString.newString(this.getRuntime(), sb.toString());
    }
    
    @JRubyMethod(name = { "binding" })
    public IRubyObject binding() {
        return this.getRuntime().newBinding(this.block.getBinding());
    }
    
    @JRubyMethod(name = { "call", "[]" }, rest = true, compat = CompatVersion.RUBY1_8)
    public IRubyObject call(final ThreadContext context, final IRubyObject[] args, final Block block) {
        return this.call(context, args, null, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject[] args) {
        return this.call(context, args, null, Block.NULL_BLOCK);
    }
    
    @JRubyMethod(name = { "call", "[]", "yield", "===" }, rest = true, compat = CompatVersion.RUBY1_9)
    public IRubyObject call19(final ThreadContext context, IRubyObject[] args, final Block block) {
        if (this.isLambda()) {
            this.block.arity().checkArity(context.getRuntime(), args.length);
        }
        if (this.isProc()) {
            final List<IRubyObject> list = new ArrayList<IRubyObject>(Arrays.asList(args));
            final int required = this.block.arity().required();
            if (this.block.arity().isFixed()) {
                if (required > args.length) {
                    for (int i = args.length; i < required; ++i) {
                        list.add(context.getRuntime().getNil());
                    }
                    args = list.toArray(args);
                }
                else if (required < args.length) {
                    args = list.subList(0, required).toArray(args);
                }
            }
        }
        return this.call(context, args, null, block);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject[] args, final IRubyObject self, final Block passedBlock) {
        assert args != null;
        final Block newBlock = this.block.cloneBlock();
        final int jumpTarget = newBlock.getBinding().getFrame().getJumpTarget();
        try {
            if (self != null) {
                newBlock.getBinding().setSelf(self);
            }
            return newBlock.call(context, args, passedBlock);
        }
        catch (JumpException.BreakJump bj) {
            return this.handleBreakJump(this.getRuntime(), newBlock, bj, jumpTarget);
        }
        catch (JumpException.ReturnJump rj) {
            return this.handleReturnJump(context, rj, jumpTarget);
        }
        catch (JumpException.RetryJump rj2) {
            return this.handleRetryJump(this.getRuntime(), rj2);
        }
    }
    
    private IRubyObject handleBreakJump(final Ruby runtime, final Block newBlock, final JumpException.BreakJump bj, final int jumpTarget) {
        switch (newBlock.type) {
            case LAMBDA: {
                if (bj.getTarget() == jumpTarget) {
                    return (IRubyObject)bj.getValue();
                }
                throw runtime.newLocalJumpError(RubyLocalJumpError.Reason.BREAK, (IRubyObject)bj.getValue(), "unexpected break");
            }
            case PROC: {
                if (newBlock.isEscaped()) {
                    throw runtime.newLocalJumpError(RubyLocalJumpError.Reason.BREAK, (IRubyObject)bj.getValue(), "break from proc-closure");
                }
                throw bj;
            }
            default: {
                throw bj;
            }
        }
    }
    
    private IRubyObject handleReturnJump(final ThreadContext context, final JumpException.ReturnJump rj, final int jumpTarget) {
        final int target = rj.getTarget();
        final Ruby runtime = context.getRuntime();
        if (target == jumpTarget && this.isLambda()) {
            return (IRubyObject)rj.getValue();
        }
        if (this.isThread()) {
            throw rj;
        }
        if (target == jumpTarget && !context.isJumpTargetAlive(target, 0)) {
            throw runtime.newLocalJumpError(RubyLocalJumpError.Reason.RETURN, (IRubyObject)rj.getValue(), "unexpected return");
        }
        throw rj;
    }
    
    private IRubyObject handleRetryJump(final Ruby runtime, final JumpException.RetryJump rj) {
        throw runtime.newLocalJumpError(RubyLocalJumpError.Reason.RETRY, (IRubyObject)rj.getValue(), "retry not supported outside rescue");
    }
    
    @JRubyMethod(name = { "arity" })
    public RubyFixnum arity() {
        return this.getRuntime().newFixnum(this.block.arity().getValue());
    }
    
    @JRubyMethod(name = { "to_proc" })
    public RubyProc to_proc() {
        return this;
    }
    
    @JRubyMethod(name = { "source_location" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject source_location(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        if (this.sourcePosition != null) {
            return runtime.newArray(runtime.newString(this.sourcePosition.getFile()), runtime.newFixnum(this.sourcePosition.getLine() + 1));
        }
        if (this.block != null) {
            final Binding binding = this.block.getBinding();
            return runtime.newArray(runtime.newString(binding.getFile()), runtime.newFixnum(binding.getLine() + 1));
        }
        return runtime.getNil();
    }
    
    @JRubyMethod(name = { "parameters" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject parameters(final ThreadContext context) {
        final Ruby runtime = context.getRuntime();
        final BlockBody body = this.getBlock().getBody();
        if (body instanceof MethodBlock) {
            final MethodBlock methodBlock = (MethodBlock)body;
            return methodBlock.getMethod().parameters(context);
        }
        return RuntimeHelpers.parameterListToParameters(runtime, body.getParameterList(), this.isLambda());
    }
    
    @JRubyMethod(name = { "lambda?" }, compat = CompatVersion.RUBY1_9)
    public IRubyObject lambda_p(final ThreadContext context) {
        return context.getRuntime().newBoolean(this.isLambda());
    }
    
    private boolean isLambda() {
        return this.type.equals(Block.Type.LAMBDA);
    }
    
    private boolean isProc() {
        return this.type.equals(Block.Type.PROC);
    }
    
    private boolean isThread() {
        return this.type.equals(Block.Type.THREAD);
    }
    
    static {
        RubyProc.PROC_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                final RubyProc instance = new RubyProc(runtime, runtime.getProc(), Block.Type.PROC);
                instance.setMetaClass(klass);
                return instance;
            }
        };
    }
}
