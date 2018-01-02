// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast;

import org.jruby.evaluator.ASTInterpreter;
import org.jruby.javasupport.JavaUtil;
import org.jruby.RubyException;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.common.IRubyWarnings;
import org.jruby.util.unsafe.UnsafeFactory;
import org.jruby.exceptions.Unrescuable;
import org.jruby.exceptions.RaiseException;
import org.jruby.exceptions.JumpException;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;
import java.util.List;
import org.jruby.ast.visitor.NodeVisitor;
import org.jruby.lexer.yacc.ISourcePosition;

public class RescueNode extends Node
{
    private final Node bodyNode;
    private final RescueBodyNode rescueNode;
    private final Node elseNode;
    
    public RescueNode(final ISourcePosition position, final Node bodyNode, final RescueBodyNode rescueNode, final Node elseNode) {
        super(position);
        this.bodyNode = bodyNode;
        this.rescueNode = rescueNode;
        this.elseNode = elseNode;
    }
    
    public NodeType getNodeType() {
        return NodeType.RESCUENODE;
    }
    
    public Object accept(final NodeVisitor iVisitor) {
        return iVisitor.visitRescueNode(this);
    }
    
    public Node getBodyNode() {
        return this.bodyNode;
    }
    
    public Node getElseNode() {
        return this.elseNode;
    }
    
    public RescueBodyNode getRescueNode() {
        return this.rescueNode;
    }
    
    public List<Node> childNodes() {
        return Node.createList(this.rescueNode, this.bodyNode, this.elseNode);
    }
    
    public IRubyObject interpret(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        return this.interpretWithJavaExceptions(runtime, context, self, aBlock);
    }
    
    private IRubyObject interpretWithJavaExceptions(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        IRubyObject result = null;
        boolean exceptionRaised = false;
        while (true) {
            final IRubyObject globalExceptionState = runtime.getGlobalVariables().get("$!");
            final boolean anotherExceptionRaised = false;
            try {
                result = this.executeBody(runtime, context, self, aBlock);
            }
            catch (RaiseException raiseJump) {
                try {
                    result = this.handleException(runtime, context, self, aBlock, raiseJump);
                    exceptionRaised = true;
                }
                catch (JumpException.RetryJump rj) {}
                catch (RaiseException ex) {}
            }
            catch (JumpException.FlowControlException flow) {
                throw flow;
            }
            catch (Throwable t) {
                if (t instanceof Unrescuable) {
                    UnsafeFactory.getUnsafe().throwException(t);
                }
                try {
                    result = this.handleJavaException(runtime, context, self, aBlock, t);
                    exceptionRaised = true;
                }
                catch (JumpException.RetryJump rj) {}
                catch (RaiseException ex2) {}
            }
            finally {
                if (!anotherExceptionRaised) {
                    runtime.getGlobalVariables().set("$!", globalExceptionState);
                }
            }
        }
        if (this.elseNode != null && !exceptionRaised) {
            if (this.rescueNode == null) {
                runtime.getWarnings().warn(IRubyWarnings.ID.ELSE_WITHOUT_RESCUE, this.elseNode.getPosition(), "else without rescue is useless", new Object[0]);
            }
            result = this.elseNode.interpret(runtime, context, self, aBlock);
        }
        return result;
    }
    
    private IRubyObject handleException(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock, final RaiseException raiseJump) {
        final RubyException raisedException = raiseJump.getException();
        runtime.getGlobalVariables().set("$!", raisedException);
        for (RescueBodyNode cRescueNode = this.rescueNode; cRescueNode != null; cRescueNode = cRescueNode.getOptRescueNode()) {
            final IRubyObject[] exceptions = this.getExceptions(cRescueNode, runtime, context, self, aBlock);
            if (RuntimeHelpers.isExceptionHandled(raisedException, exceptions, context).isTrue()) {
                return cRescueNode.interpret(runtime, context, self, aBlock);
            }
        }
        throw raiseJump;
    }
    
    private IRubyObject handleJavaException(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock, final Throwable throwable) {
        for (RescueBodyNode cRescueNode = this.rescueNode; cRescueNode != null; cRescueNode = cRescueNode.getOptRescueNode()) {
            final IRubyObject[] exceptions = this.getExceptions(cRescueNode, runtime, context, self, aBlock);
            if (RuntimeHelpers.isJavaExceptionHandled(throwable, exceptions, context).isTrue()) {
                runtime.getGlobalVariables().set("$!", JavaUtil.convertJavaToUsableRubyObject(runtime, throwable));
                return cRescueNode.interpret(runtime, context, self, aBlock);
            }
        }
        UnsafeFactory.getUnsafe().throwException(throwable);
        throw new RuntimeException("Unsafe.throwException failed");
    }
    
    private IRubyObject executeBody(final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        if (this.bodyNode == null) {
            return runtime.getNil();
        }
        return this.bodyNode.interpret(runtime, context, self, aBlock);
    }
    
    private IRubyObject[] getExceptions(final RescueBodyNode cRescueNode, final Ruby runtime, final ThreadContext context, final IRubyObject self, final Block aBlock) {
        final Node exceptionNodes = cRescueNode.getExceptionNodes();
        IRubyObject[] exceptions;
        if (exceptionNodes == null) {
            exceptions = new IRubyObject[] { runtime.getStandardError() };
        }
        else {
            exceptions = ASTInterpreter.setupArgs(runtime, context, exceptionNodes, self, aBlock);
        }
        return exceptions;
    }
}
