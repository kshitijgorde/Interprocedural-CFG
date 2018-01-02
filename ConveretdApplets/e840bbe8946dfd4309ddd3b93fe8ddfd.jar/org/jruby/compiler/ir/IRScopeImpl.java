// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir;

import org.jruby.compiler.ir.compiler_pass.AddBindingInstructions;
import org.jruby.compiler.ir.compiler_pass.opts.DeadCodeElimination;
import org.jruby.compiler.ir.compiler_pass.LiveVariableAnalysis;
import org.jruby.compiler.ir.compiler_pass.CFG_Builder;
import org.jruby.compiler.ir.compiler_pass.opts.LocalOptimizationPass;
import org.jruby.compiler.ir.compiler_pass.CompilerPass;
import java.util.List;
import org.jruby.compiler.ir.instructions.Instr;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.compiler.ir.operands.RenamedVariable;
import org.jruby.compiler.ir.operands.TemporaryVariable;
import org.jruby.compiler.ir.operands.TemporaryClosureVariable;
import org.jruby.compiler.ir.operands.Variable;
import java.util.HashMap;
import org.jruby.parser.StaticScope;
import java.util.Map;
import org.jruby.RubyModule;
import org.jruby.compiler.ir.operands.Operand;

public abstract class IRScopeImpl implements IRScope
{
    Operand container;
    RubyModule containerModule;
    IRScope lexicalParent;
    private String name;
    private Map<String, String> aliases;
    private int nextClosureIndex;
    private Map<String, Integer> nextVarIndex;
    private StaticScope staticScope;
    
    public IRScopeImpl(final IRScope lexicalParent, final Operand container, final String name, final StaticScope staticScope) {
        this.aliases = new HashMap<String, String>();
        this.nextClosureIndex = 0;
        this.nextVarIndex = new HashMap<String, Integer>();
        this.lexicalParent = lexicalParent;
        this.container = container;
        this.name = name;
        this.staticScope = staticScope;
    }
    
    public void setContainer(final Operand o) {
        this.container = o;
    }
    
    public Operand getContainer() {
        return this.container;
    }
    
    public RubyModule getContainerModule() {
        return this.containerModule;
    }
    
    public IRScope getLexicalParent() {
        return this.lexicalParent;
    }
    
    public IRModule getNearestModule() {
        IRScope current;
        for (current = this.lexicalParent; current != null && !(current instanceof IRModule) && !(current instanceof IRScript); current = current.getLexicalParent()) {}
        if (current instanceof IRScript) {
            current = ((IRScript)current).getRootClass();
        }
        return (IRModule)current;
    }
    
    public int getNextClosureId() {
        return ++this.nextClosureIndex;
    }
    
    public Variable getNewTemporaryClosureVariable(final int closureId) {
        return new TemporaryClosureVariable(closureId, this.allocateNextPrefixedName("%cl_" + closureId));
    }
    
    public Variable getNewTemporaryVariable() {
        return new TemporaryVariable(this.allocateNextPrefixedName("%v"));
    }
    
    public Variable getNewInlineVariable() {
        return new RenamedVariable("%i", this.allocateNextPrefixedName("%i"));
    }
    
    public int getTemporaryVariableSize() {
        return this.getPrefixCountSize("%v");
    }
    
    public int getRenamedVariableSize() {
        return this.getPrefixCountSize("%i");
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public abstract String getScopeName();
    
    public Label getNewLabel(final String prefix) {
        return new Label(prefix + "_" + this.allocateNextPrefixedName(prefix));
    }
    
    public Label getNewLabel() {
        return this.getNewLabel("LBL");
    }
    
    private int allocateNextPrefixedName(final String prefix) {
        final int index = this.getPrefixCountSize(prefix);
        this.nextVarIndex.put(prefix, index + 1);
        return index;
    }
    
    protected int getPrefixCountSize(final String prefix) {
        final Integer index = this.nextVarIndex.get(prefix);
        if (index == null) {
            return 0;
        }
        return index;
    }
    
    public StaticScope getStaticScope() {
        return this.staticScope;
    }
    
    public void addInstr(final Instr i) {
        throw new RuntimeException("Encountered instruction add in a non-execution scope!");
    }
    
    public void recordMethodAlias(final String newName, final String oldName) {
        this.aliases.put(oldName, newName);
    }
    
    public String unaliasMethodName(final String name) {
        String n = name;
        String a = null;
        do {
            a = this.aliases.get(n);
            if (a != null) {
                n = a;
            }
        } while (a != null);
        return n;
    }
    
    public List<Instr> getInstrs() {
        return null;
    }
    
    public String toString() {
        return this.getScopeName() + " " + this.getName();
    }
    
    public void runCompilerPassOnNestedScopes(final CompilerPass p) {
    }
    
    public void runCompilerPass(final CompilerPass p) {
        final boolean isPreOrder = p.isPreOrder();
        if (isPreOrder) {
            p.run(this);
        }
        this.runCompilerPassOnNestedScopes(p);
        if (!isPreOrder) {
            p.run(this);
        }
    }
    
    public void prepareForInterpretation() {
        this.runCompilerPass(new LocalOptimizationPass());
        this.runCompilerPass(new CFG_Builder());
        this.runCompilerPass(new LiveVariableAnalysis());
        this.runCompilerPass(new DeadCodeElimination());
        this.runCompilerPass(new AddBindingInstructions());
    }
    
    public String toStringInstrs() {
        return "";
    }
    
    public String toStringVariables() {
        return "";
    }
}
