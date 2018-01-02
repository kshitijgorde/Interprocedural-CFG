// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler;

import org.jruby.ast.SValue19Node;
import org.jruby.ast.Match2CaptureNode;
import org.jruby.ast.HashNode;
import org.jruby.ast.Hash19Node;
import org.jruby.ast.StarNode;
import java.util.Iterator;
import org.jruby.ast.MultipleAsgn19Node;
import org.jruby.ast.NodeType;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.Arity;
import org.jruby.runtime.BlockBody;
import org.jruby.ast.MultipleAsgnNode;
import org.jruby.ast.LambdaNode;
import org.jruby.ast.IterNode;
import org.jruby.ast.EncodingNode;
import org.jruby.ast.ArgsPushNode;
import org.jruby.ast.ListNode;
import org.jruby.ast.OptArgNode;
import org.jruby.ast.ArgumentNode;
import org.jruby.ast.ArrayNode;
import org.jruby.ast.NthRefNode;
import org.jruby.RubyMatchData;
import org.jruby.ast.ArgsNode;
import org.jruby.ast.Node;

public class ASTCompiler19 extends ASTCompiler
{
    public void compile(final Node node, final BodyCompiler context, final boolean expr) {
        if (node == null) {
            if (expr) {
                context.loadNil();
            }
            return;
        }
        switch (node.getNodeType()) {
            case ENCODINGNODE: {
                this.compileEncoding(node, context, expr);
                break;
            }
            case LAMBDANODE: {
                this.compileLambda(node, context, expr);
                break;
            }
            case MULTIPLEASGN19NODE: {
                this.compileMultipleAsgn19(node, context, expr);
                break;
            }
            default: {
                super.compile(node, context, expr);
                break;
            }
        }
    }
    
    public void compileArgs(final Node node, final BodyCompiler context, final boolean expr) {
        final ArgsNode argsNode = (ArgsNode)node;
        final int required = argsNode.getRequiredArgsCount();
        final int opt = argsNode.getOptionalArgsCount();
        final int rest = argsNode.getRestArg();
        context.getVariableCompiler().checkMethodArity(required, opt, rest);
        this.compileMethodArgs(node, context, expr);
    }
    
    public void compileAssignment(final Node node, final BodyCompiler context, final boolean expr) {
        switch (node.getNodeType()) {
            case MULTIPLEASGN19NODE: {
                this.compileMultipleAsgn19Assignment(node, context, expr);
                break;
            }
            default: {
                super.compileAssignment(node, context, expr);
                break;
            }
        }
    }
    
    protected void compileDefinedAndOrDStrDRegexp(final Node node, final BodyCompiler context) {
        context.pushByteList(Node.EXPRESSION_BYTELIST);
    }
    
    protected void compileDefinedBackref(final Node node, final BodyCompiler context) {
        context.backref();
        context.isInstanceOf(RubyMatchData.class, new BranchCallback() {
            public void branch(final BodyCompiler context) {
                context.pushByteList(Node.GLOBAL_VARIABLE_BYTELIST);
            }
        }, new BranchCallback() {
            public void branch(final BodyCompiler context) {
                context.pushNull();
            }
        });
    }
    
    protected void compileDefinedDVar(final Node node, final BodyCompiler context) {
        context.pushByteList(Node.LOCAL_VARIABLE_BYTELIST);
    }
    
    protected void compileDefinedNthref(final Node node, final BodyCompiler context) {
        context.isCaptured(((NthRefNode)node).getMatchNumber(), new BranchCallback() {
            public void branch(final BodyCompiler context) {
                context.pushByteList(Node.GLOBAL_VARIABLE_BYTELIST);
            }
        }, new BranchCallback() {
            public void branch(final BodyCompiler context) {
                context.pushNull();
            }
        });
    }
    
    public void compileMethodArgs(final Node node, final BodyCompiler context, final boolean expr) {
        final ArgsNode argsNode = (ArgsNode)node;
        final int required = argsNode.getRequiredArgsCount();
        final int opt = argsNode.getOptionalArgsCount();
        final int rest = argsNode.getRestArg();
        ArrayCallback requiredAssignment = null;
        ArrayCallback optionalGiven = null;
        ArrayCallback optionalNotGiven = null;
        CompilerCallback restAssignment = null;
        CompilerCallback blockAssignment = null;
        if (required > 0) {
            requiredAssignment = new ArrayCallback() {
                public void nextValue(final BodyCompiler context, final Object object, final int index) {
                    final ArrayNode arguments = (ArrayNode)object;
                    final Node argNode = arguments.get(index);
                    switch (argNode.getNodeType()) {
                        case ARGUMENTNODE: {
                            final int varIndex = ((ArgumentNode)argNode).getIndex();
                            context.getVariableCompiler().assignLocalVariable(varIndex, false);
                            break;
                        }
                        case MULTIPLEASGN19NODE: {
                            ASTCompiler19.this.compileMultipleAsgn19Assignment(argNode, context, false);
                            break;
                        }
                        default: {
                            throw new NotCompilableException("unknown argument type: " + argNode);
                        }
                    }
                }
            };
        }
        if (opt > 0) {
            optionalGiven = new ArrayCallback() {
                public void nextValue(final BodyCompiler context, final Object object, final int index) {
                    final OptArgNode optArg = (OptArgNode)((ListNode)object).get(index);
                    ASTCompiler19.this.compileAssignment(optArg.getValue(), context, false);
                }
            };
            optionalNotGiven = new ArrayCallback() {
                public void nextValue(final BodyCompiler context, final Object object, final int index) {
                    final OptArgNode optArg = (OptArgNode)((ListNode)object).get(index);
                    ASTCompiler19.this.compile(optArg.getValue(), context, false);
                }
            };
        }
        if (rest > -1) {
            restAssignment = new CompilerCallback() {
                public void call(final BodyCompiler context) {
                    context.getVariableCompiler().assignLocalVariable(argsNode.getRestArg(), false);
                }
            };
        }
        if (argsNode.getBlock() != null) {
            blockAssignment = new CompilerCallback() {
                public void call(final BodyCompiler context) {
                    context.getVariableCompiler().assignLocalVariable(argsNode.getBlock().getCount(), false);
                }
            };
        }
        context.getVariableCompiler().assignMethodArguments19(argsNode.getPre(), argsNode.getPreCount(), argsNode.getPost(), argsNode.getPostCount(), argsNode.getPostIndex(), argsNode.getOptArgs(), argsNode.getOptionalArgsCount(), requiredAssignment, optionalGiven, optionalNotGiven, restAssignment, blockAssignment);
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileArgsPush(final Node node, final BodyCompiler context, final boolean expr) {
        final ArgsPushNode argsPush = (ArgsPushNode)node;
        this.compile(argsPush.getFirstNode(), context, true);
        this.compile(argsPush.getSecondNode(), context, true);
        context.argsPush();
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileEncoding(final Node node, final BodyCompiler context, final boolean expr) {
        final EncodingNode encodingNode = (EncodingNode)node;
        if (expr) {
            context.loadEncoding(encodingNode.getEncoding());
        }
    }
    
    public void compileIter(final Node node, final BodyCompiler context) {
        final IterNode iterNode = (IterNode)node;
        final ArgsNode argsNode = (ArgsNode)iterNode.getVarNode();
        final CompilerCallback closureBody = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                if (iterNode.getBodyNode() != null) {
                    ASTCompiler19.this.compile(iterNode.getBodyNode(), context, true);
                }
                else {
                    context.loadNil();
                }
            }
        };
        final CompilerCallback closureArgs = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                context.consumeCurrentValue();
                context.consumeCurrentValue();
                if (iterNode.getVarNode() != null) {
                    if (iterNode instanceof LambdaNode) {
                        final int required = argsNode.getRequiredArgsCount();
                        final int opt = argsNode.getOptionalArgsCount();
                        final int rest = argsNode.getRestArg();
                        context.getVariableCompiler().checkMethodArity(required, opt, rest);
                        ASTCompiler19.this.compileMethodArgs(argsNode, context, true);
                    }
                    else {
                        ASTCompiler19.this.compileMethodArgs(argsNode, context, true);
                    }
                }
            }
        };
        boolean hasMultipleArgsHead = false;
        if (iterNode.getVarNode() instanceof MultipleAsgnNode) {
            hasMultipleArgsHead = (((MultipleAsgnNode)iterNode.getVarNode()).getHeadNode() != null);
        }
        final NodeType argsNodeId = BlockBody.getArgumentTypeWackyHack(iterNode);
        final ASTInspector inspector = new ASTInspector();
        inspector.inspect(iterNode.getBodyNode());
        inspector.inspect(iterNode.getVarNode());
        if (argsNodeId == null) {
            context.createNewClosure19(iterNode.getPosition().getFile(), iterNode.getPosition().getStartLine(), iterNode.getScope(), Arity.procArityOf(iterNode.getVarNode()).getValue(), closureBody, null, hasMultipleArgsHead, argsNodeId, RuntimeHelpers.encodeParameterList(argsNode), inspector);
        }
        else {
            context.createNewClosure19(iterNode.getPosition().getFile(), iterNode.getPosition().getStartLine(), iterNode.getScope(), Arity.procArityOf(iterNode.getVarNode()).getValue(), closureBody, closureArgs, hasMultipleArgsHead, argsNodeId, RuntimeHelpers.encodeParameterList(argsNode), inspector);
        }
    }
    
    public void compileLambda(final Node node, final BodyCompiler context, final boolean expr) {
        final LambdaNode lambdaNode = (LambdaNode)node;
        if (expr) {
            context.createNewLambda(new CompilerCallback() {
                public void call(final BodyCompiler context) {
                    ASTCompiler19.this.compileIter(lambdaNode, context);
                }
            });
        }
    }
    
    public void compileMultipleAsgn19(final Node node, final BodyCompiler context, final boolean expr) {
        final MultipleAsgn19Node multipleAsgn19Node = (MultipleAsgn19Node)node;
        if (expr) {
            this.compileUnoptimizedMultipleAsgn19(multipleAsgn19Node, context, expr);
        }
        else {
            this.compileOptimizedMultipleAsgn19(multipleAsgn19Node, context, expr);
        }
    }
    
    private void compileOptimizedMultipleAsgn19(final MultipleAsgn19Node multipleAsgn19Node, final BodyCompiler context, final boolean expr) {
        if (multipleAsgn19Node.getValueNode() instanceof ArrayNode && multipleAsgn19Node.getPreCount() > 0 && multipleAsgn19Node.getPostCount() == 0 && multipleAsgn19Node.getRest() == null && multipleAsgn19Node.getPreCount() == ((ArrayNode)multipleAsgn19Node.getValueNode()).size()) {
            boolean normalAssigns = true;
            for (final Node asgn : multipleAsgn19Node.getPre().childNodes()) {
                if (asgn instanceof ListNode) {
                    normalAssigns = false;
                    break;
                }
            }
            if (normalAssigns) {
                final int size = multipleAsgn19Node.getPreCount();
                if (size >= 2 && size <= 10) {
                    final ArrayNode values = (ArrayNode)multipleAsgn19Node.getValueNode();
                    for (final Node value : values.childNodes()) {
                        this.compile(value, context, true);
                    }
                    context.reverseValues(size);
                    for (final Node asgn2 : multipleAsgn19Node.getPre().childNodes()) {
                        this.compileAssignment(asgn2, context, false);
                    }
                    return;
                }
            }
        }
        this.compileUnoptimizedMultipleAsgn19(multipleAsgn19Node, context, expr);
    }
    
    private void compileUnoptimizedMultipleAsgn19(final MultipleAsgn19Node multipleAsgn19Node, final BodyCompiler context, final boolean expr) {
        this.compile(multipleAsgn19Node.getValueNode(), context, true);
        this.compileMultipleAsgn19Assignment(multipleAsgn19Node, context, expr);
    }
    
    public void compileMultipleAsgn19Assignment(final Node node, final BodyCompiler context, final boolean expr) {
        final MultipleAsgn19Node multipleAsgn19Node = (MultipleAsgn19Node)node;
        final ArrayCallback prePostAssignCallback = new ArrayCallback() {
            public void nextValue(final BodyCompiler context, final Object sourceArray, final int index) {
                final ListNode nodes = (ListNode)sourceArray;
                final Node assignNode = nodes.get(index);
                ASTCompiler19.this.compileAssignment(assignNode, context, false);
            }
        };
        final CompilerCallback restCallback = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                final Node argsNode = multipleAsgn19Node.getRest();
                if (argsNode instanceof StarNode) {
                    context.consumeCurrentValue();
                }
                else {
                    ASTCompiler19.this.compileAssignment(argsNode, context, false);
                }
            }
        };
        if (multipleAsgn19Node.getPreCount() == 0 && multipleAsgn19Node.getPostCount() == 0) {
            if (multipleAsgn19Node.getRest() == null) {
                throw new NotCompilableException("Something's wrong, multiple assignment with no head or args at: " + multipleAsgn19Node.getPosition());
            }
            if (!(multipleAsgn19Node.getRest() instanceof StarNode)) {
                context.ensureMultipleAssignableRubyArray(multipleAsgn19Node.getPreCount() != 0 || multipleAsgn19Node.getPostCount() != 0);
                context.forEachInValueArray(0, 0, null, null, restCallback);
            }
        }
        else {
            context.ensureMultipleAssignableRubyArray(multipleAsgn19Node.getPreCount() != 0 || multipleAsgn19Node.getPostCount() != 0);
            if (multipleAsgn19Node.getRest() == null) {
                context.forEachInValueArray(0, multipleAsgn19Node.getPreCount(), multipleAsgn19Node.getPre(), multipleAsgn19Node.getPostCount(), multipleAsgn19Node.getPost(), prePostAssignCallback, null);
            }
            else {
                context.forEachInValueArray(0, multipleAsgn19Node.getPreCount(), multipleAsgn19Node.getPre(), multipleAsgn19Node.getPostCount(), multipleAsgn19Node.getPost(), prePostAssignCallback, restCallback);
            }
        }
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileHash(final Node node, final BodyCompiler context, final boolean expr) {
        this.compileHashCommon((HashNode)node, context, expr);
    }
    
    protected void createNewHash(final BodyCompiler context, final HashNode hashNode, final ArrayCallback hashCallback) {
        context.createNewHash19(hashNode.getListNode(), hashCallback, hashNode.getListNode().size() / 2);
    }
    
    public void compileMatch2(final Node node, final BodyCompiler context, final boolean expr) {
        if (!(node instanceof Match2CaptureNode)) {
            super.compileMatch2(node, context, expr);
            return;
        }
        final Match2CaptureNode matchNode = (Match2CaptureNode)node;
        this.compile(matchNode.getReceiverNode(), context, true);
        final CompilerCallback value = new CompilerCallback() {
            public void call(final BodyCompiler context) {
                ASTCompiler19.this.compile(matchNode.getValueNode(), context, true);
            }
        };
        context.match2Capture(value, matchNode.getScopeOffsets());
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    public void compileSValue(final Node node, final BodyCompiler context, final boolean expr) {
        final SValue19Node svalueNode = (SValue19Node)node;
        this.compile(svalueNode.getValue(), context, true);
        context.singlifySplattedValue19();
        if (!expr) {
            context.consumeCurrentValue();
        }
    }
    
    protected void splatCurrentValue(final BodyCompiler context) {
        context.splatCurrentValue("splatValue19");
    }
}
