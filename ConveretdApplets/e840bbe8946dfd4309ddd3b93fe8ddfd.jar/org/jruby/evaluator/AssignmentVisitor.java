// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.evaluator;

import org.jruby.ast.ListNode;
import org.jruby.ast.NodeType;
import org.jruby.ast.MultipleAsgnNode;
import org.jruby.RubyArray;
import org.jruby.ast.MultipleAsgn19Node;
import org.jruby.runtime.Block;
import org.jruby.ast.Node;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;

public class AssignmentVisitor
{
    @Deprecated
    public static IRubyObject assign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Node node, final IRubyObject value, final Block block, final boolean checkArity) {
        return node.assign(runtime, context, self, value, block, checkArity);
    }
    
    public static IRubyObject multiAssign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final MultipleAsgn19Node node, final RubyArray value, final boolean checkArity) {
        final int valueLen = value.getLength();
        final int varLen = node.getPreCount() + node.getPostCount();
        if (checkArity && varLen < valueLen) {
            throw runtime.newArgumentError("Wrong # of arguments (" + valueLen + " for " + varLen + ")");
        }
        int offset;
        for (offset = 0, offset = 0; offset < node.getPreCount(); ++offset) {
            if (offset >= valueLen) {
                node.getPre().get(offset).assign(runtime, context, self, runtime.getNil(), Block.NULL_BLOCK, false);
            }
            else {
                node.getPre().get(offset).assign(runtime, context, self, value.eltInternal(offset), Block.NULL_BLOCK, false);
            }
        }
        final Node restArgument = node.getRest();
        if (restArgument != null) {
            final int restLen = valueLen - varLen;
            if (restLen > 0) {
                restArgument.assign(runtime, context, self, value.subseqLight(offset, restLen), Block.NULL_BLOCK, false);
                offset += restLen;
            }
            else {
                restArgument.assign(runtime, context, self, RubyArray.newEmptyArray(runtime), Block.NULL_BLOCK, false);
            }
        }
        for (int i = 0; i < node.getPostCount(); ++i) {
            offset += i;
            if (offset >= valueLen) {
                node.getPost().get(i).assign(runtime, context, self, runtime.getNil(), Block.NULL_BLOCK, false);
            }
            else {
                node.getPost().get(i).assign(runtime, context, self, value.eltInternal(offset), Block.NULL_BLOCK, false);
            }
        }
        return value;
    }
    
    public static IRubyObject multiAssign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final MultipleAsgnNode node, final RubyArray value, final boolean checkArity) {
        int valueLen;
        int varLen;
        int j;
        for (valueLen = value.getLength(), varLen = ((node.getHeadNode() == null) ? 0 : node.getHeadNode().size()), j = 0; j < valueLen && j < varLen; ++j) {
            node.getHeadNode().get(j).assign(runtime, context, self, value.eltInternal(j), Block.NULL_BLOCK, checkArity);
        }
        if (checkArity && j < varLen) {
            throw runtime.newArgumentError("Wrong # of arguments (" + valueLen + " for " + varLen + ")");
        }
        final Node argsNode = node.getArgsNode();
        if (argsNode != null) {
            if (argsNode.getNodeType() != NodeType.STARNODE) {
                if (varLen < valueLen) {
                    argsNode.assign(runtime, context, self, value.subseqLight(varLen, valueLen), Block.NULL_BLOCK, checkArity);
                }
                else {
                    argsNode.assign(runtime, context, self, RubyArray.newEmptyArray(runtime), Block.NULL_BLOCK, checkArity);
                }
            }
        }
        else if (checkArity && valueLen < varLen) {
            throw runtime.newArgumentError("Wrong # of arguments (" + valueLen + " for " + varLen + ")");
        }
        while (j < varLen) {
            node.getHeadNode().get(j++).assign(runtime, context, self, runtime.getNil(), Block.NULL_BLOCK, checkArity);
        }
        return value;
    }
    
    public static IRubyObject multiAssign(final Ruby runtime, final ThreadContext context, final IRubyObject self, final MultipleAsgn19Node node, final RubyArray value) {
        final int valueLen = value.getLength();
        final int postCount = node.getPostCount();
        final int preCount = node.getPreCount();
        final ListNode pre = node.getPre();
        final ListNode post = node.getPost();
        int j;
        for (j = 0; j < valueLen && j < preCount; ++j) {
            pre.get(j).assign(runtime, context, self, value.eltInternal(j), Block.NULL_BLOCK, false);
        }
        final Node rest = node.getRest();
        if (rest != null) {
            if (rest.getNodeType() != NodeType.STARNODE) {
                if (preCount + postCount < valueLen) {
                    rest.assign(runtime, context, self, value.subseqLight(preCount, valueLen - preCount - postCount), Block.NULL_BLOCK, false);
                }
                else {
                    rest.assign(runtime, context, self, RubyArray.newEmptyArray(runtime), Block.NULL_BLOCK, false);
                }
            }
            final int postIndexBase = valueLen - postCount;
            for (int i = 0; i < valueLen && i < postCount; ++i) {
                post.get(i).assign(runtime, context, self, value.eltInternal(i + postIndexBase), Block.NULL_BLOCK, false);
            }
        }
        while (j < preCount) {
            pre.get(j++).assign(runtime, context, self, runtime.getNil(), Block.NULL_BLOCK, false);
        }
        return value;
    }
}
