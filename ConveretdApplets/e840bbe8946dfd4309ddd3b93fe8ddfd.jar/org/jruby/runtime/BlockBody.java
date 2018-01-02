// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime;

import org.jruby.ast.MultipleAsgnNode;
import org.jruby.ast.IterNode;
import org.jruby.common.IRubyWarnings;
import org.jruby.ast.NodeType;
import org.jruby.parser.StaticScope;
import org.jruby.RubyModule;
import org.jruby.RubyArray;
import org.jruby.runtime.builtin.IRubyObject;

public abstract class BlockBody
{
    public static final int ZERO_ARGS = 0;
    public static final int MULTIPLE_ASSIGNMENT = 1;
    public static final int ARRAY = 2;
    public static final int SINGLE_RESTARG = 3;
    public static final String[] EMPTY_PARAMETER_LIST;
    protected final int argumentType;
    public static final BlockBody NULL_BODY;
    
    public BlockBody(final int argumentType) {
        this.argumentType = argumentType;
    }
    
    public IRubyObject call(final ThreadContext context, IRubyObject[] args, final Binding binding, final Block.Type type) {
        args = this.prepareArgumentsForCall(context, args, type);
        return this.yield(context, RubyArray.newArrayNoCopy(context.getRuntime(), args), null, null, true, binding, type);
    }
    
    public IRubyObject call(final ThreadContext context, IRubyObject[] args, final Binding binding, final Block.Type type, final Block block) {
        args = this.prepareArgumentsForCall(context, args, type);
        return this.yield(context, RubyArray.newArrayNoCopy(context.getRuntime(), args), null, null, true, binding, type, block);
    }
    
    public abstract IRubyObject yield(final ThreadContext p0, final IRubyObject p1, final Binding p2, final Block.Type p3);
    
    public abstract IRubyObject yield(final ThreadContext p0, final IRubyObject p1, final IRubyObject p2, final RubyModule p3, final boolean p4, final Binding p5, final Block.Type p6);
    
    public IRubyObject yield(final ThreadContext context, final IRubyObject value, final IRubyObject self, final RubyModule klass, final boolean aValue, final Binding binding, final Block.Type type, final Block block) {
        return this.yield(context, value, self, klass, aValue, binding, type);
    }
    
    public IRubyObject yield(final ThreadContext context, final IRubyObject value, final Binding binding, final Block.Type type, final Block block) {
        return this.yield(context, value, binding, type);
    }
    
    public int getArgumentType() {
        return this.argumentType;
    }
    
    public IRubyObject call(final ThreadContext context, final Binding binding, final Block.Type type) {
        IRubyObject[] args = IRubyObject.NULL_ARRAY;
        args = this.prepareArgumentsForCall(context, args, type);
        return this.yield(context, RubyArray.newArrayNoCopy(context.getRuntime(), args), null, null, true, binding, type);
    }
    
    public IRubyObject call(final ThreadContext context, final Binding binding, final Block.Type type, final Block unusedBlock) {
        return this.call(context, binding, type);
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final Binding binding, final Block.Type type) {
        return this.yield(context, null, null, null, true, binding, type);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject arg0, final Binding binding, final Block.Type type) {
        IRubyObject[] args = { arg0 };
        args = this.prepareArgumentsForCall(context, args, type);
        return this.yield(context, RubyArray.newArrayNoCopy(context.getRuntime(), args), null, null, true, binding, type);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject arg0, final Binding binding, final Block.Type type, final Block unusedBlock) {
        return this.call(context, arg0, binding, type);
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final IRubyObject arg0, final Binding binding, final Block.Type type) {
        return this.yield(context, arg0, null, null, true, binding, type);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Binding binding, final Block.Type type) {
        IRubyObject[] args = { arg0, arg1 };
        args = this.prepareArgumentsForCall(context, args, type);
        return this.yield(context, RubyArray.newArrayNoCopy(context.getRuntime(), args), null, null, true, binding, type);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Binding binding, final Block.Type type, final Block unusedBlock) {
        return this.call(context, arg0, arg1, binding, type);
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final Binding binding, final Block.Type type) {
        return this.yield(context, context.getRuntime().newArrayNoCopyLight(arg0, arg1), null, null, true, binding, type);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Binding binding, final Block.Type type) {
        IRubyObject[] args = { arg0, arg1, arg2 };
        args = this.prepareArgumentsForCall(context, args, type);
        return this.yield(context, RubyArray.newArrayNoCopy(context.getRuntime(), args), null, null, true, binding, type);
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Binding binding, final Block.Type type, final Block unusedBlock) {
        return this.call(context, arg0, arg1, arg2, binding, type);
    }
    
    public IRubyObject yieldSpecific(final ThreadContext context, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Binding binding, final Block.Type type) {
        return this.yield(context, context.getRuntime().newArrayNoCopyLight(arg0, arg1, arg2), null, null, true, binding, type);
    }
    
    public abstract StaticScope getStaticScope();
    
    public abstract void setStaticScope(final StaticScope p0);
    
    public abstract Block cloneBlock(final Binding p0);
    
    public abstract Arity arity();
    
    public boolean isGiven() {
        return true;
    }
    
    public abstract String getFile();
    
    public abstract int getLine();
    
    public static int asArgumentType(final NodeType nodeId) {
        if (nodeId == null) {
            return 0;
        }
        switch (nodeId) {
            case ZEROARGNODE: {
                return 0;
            }
            case MULTIPLEASGNNODE: {
                return 1;
            }
            case SVALUENODE: {
                return 3;
            }
            default: {
                return 2;
            }
        }
    }
    
    public IRubyObject[] prepareArgumentsForCall(final ThreadContext context, IRubyObject[] args, final Block.Type type) {
        Label_0080: {
            switch (type) {
                case NORMAL: {
                    if (args.length != 1 || !(args[0] instanceof RubyArray)) {
                        break Label_0080;
                    }
                    if (this.argumentType == 1 || this.argumentType == 3) {
                        args = ((RubyArray)args[0]).toJavaArray();
                        break;
                    }
                    break;
                }
                case PROC: {
                    if (args.length == 1 && args[0] instanceof RubyArray && this.argumentType == 1 && this.argumentType != 3) {
                        args = ((RubyArray)args[0]).toJavaArray();
                        break;
                    }
                    break;
                }
                case LAMBDA: {
                    if (this.argumentType != 2 || args.length == 1) {
                        this.arity().checkArity(context.getRuntime(), args);
                        break;
                    }
                    context.getRuntime().getWarnings().warn(IRubyWarnings.ID.MULTIPLE_VALUES_FOR_BLOCK, "multiple values for a block parameter (" + args.length + " for " + this.arity().getValue() + ")", new Object[0]);
                    if (args.length == 0) {
                        args = context.getRuntime().getSingleNilArray();
                        break;
                    }
                    args = new IRubyObject[] { context.getRuntime().newArrayNoCopy(args) };
                    break;
                }
            }
        }
        return args;
    }
    
    public String[] getParameterList() {
        return BlockBody.EMPTY_PARAMETER_LIST;
    }
    
    public static NodeType getArgumentTypeWackyHack(final IterNode iterNode) {
        NodeType argsNodeId = null;
        if (iterNode.getVarNode() != null && iterNode.getVarNode().getNodeType() != NodeType.ZEROARGNODE) {
            argsNodeId = iterNode.getVarNode().getNodeType();
            if (argsNodeId == NodeType.MULTIPLEASGNNODE) {
                final MultipleAsgnNode multipleAsgnNode = (MultipleAsgnNode)iterNode.getVarNode();
                if (multipleAsgnNode.getHeadNode() == null && multipleAsgnNode.getArgsNode() != null) {
                    argsNodeId = NodeType.SVALUENODE;
                }
            }
        }
        return argsNodeId;
    }
    
    static {
        EMPTY_PARAMETER_LIST = new String[0];
        NULL_BODY = new NullBlockBody();
    }
}
