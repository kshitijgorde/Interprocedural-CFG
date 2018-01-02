// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir;

import org.jruby.parser.BlockStaticScope;
import org.jruby.compiler.ir.representations.CFG;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.runtime.InterpretedIRBlockBody;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.MetaObject;
import org.jruby.runtime.Arity;
import org.jruby.parser.StaticScope;
import org.jruby.runtime.BlockBody;
import org.jruby.compiler.ir.operands.Label;

public class IRClosure extends IRExecutionScope
{
    public final Label startLabel;
    public final Label endLabel;
    public final int closureId;
    private final BlockBody body;
    
    public IRClosure(final IRScope lexicalParent, final StaticScope staticScope, final Arity arity, final int argumentType) {
        super(lexicalParent, MetaObject.create(lexicalParent), null, staticScope);
        this.startLabel = this.getNewLabel("_CLOSURE_START");
        this.endLabel = this.getNewLabel("_CLOSURE_END");
        this.closureId = lexicalParent.getNextClosureId();
        this.setName("_CLOSURE_" + this.closureId);
        this.body = new InterpretedIRBlockBody(this, arity, argumentType);
    }
    
    public int getNextClosureId() {
        return this.lexicalParent.getNextClosureId();
    }
    
    public int getTemporaryVariableSize() {
        return this.getPrefixCountSize("%cl_" + this.closureId);
    }
    
    public Variable getNewTemporaryVariable() {
        return this.getNewTemporaryClosureVariable(this.closureId);
    }
    
    public Label getNewLabel() {
        return this.getNewLabel("CL" + this.closureId + "_LBL");
    }
    
    public String getScopeName() {
        return "Closure";
    }
    
    public String toStringBody() {
        final StringBuilder buf = new StringBuilder();
        buf.append(this.getName()).append(" = { \n");
        final CFG c = this.getCFG();
        if (c != null) {
            buf.append("\nCFG:\n").append(c.getGraph().toString());
            buf.append("\nInstructions:\n").append(c.toStringInstrs());
        }
        else {
            buf.append(this.toStringInstrs());
        }
        buf.append("\n}\n\n");
        return buf.toString();
    }
    
    protected StaticScope constructStaticScope(final StaticScope parent) {
        return new BlockStaticScope(parent);
    }
    
    public BlockBody getBlockBody() {
        return this.body;
    }
}
