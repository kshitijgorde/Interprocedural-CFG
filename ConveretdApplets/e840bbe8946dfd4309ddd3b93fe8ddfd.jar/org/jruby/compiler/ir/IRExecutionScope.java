// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir;

import java.util.Set;
import org.jruby.compiler.ir.operands.LocalVariable;
import java.util.SortedSet;
import java.util.Map;
import java.util.TreeSet;
import org.jruby.compiler.ir.operands.Variable;
import java.util.HashMap;
import java.util.Iterator;
import org.jruby.compiler.ir.operands.MethAddr;
import org.jruby.compiler.ir.instructions.CallInstr;
import org.jruby.compiler.ir.instructions.RubyInternalCallInstr;
import org.jruby.compiler.ir.instructions.ReceiveClosureInstr;
import org.jruby.compiler.ir.compiler_pass.CompilerPass;
import org.jruby.parser.StaticScope;
import org.jruby.compiler.ir.operands.Operand;
import java.util.ArrayList;
import java.util.Stack;
import org.jruby.compiler.ir.representations.CFG;
import org.jruby.compiler.ir.instructions.Instr;
import java.util.List;

public abstract class IRExecutionScope extends IRScopeImpl
{
    private List<Instr> instructions;
    private CFG cfg;
    private List<IRClosure> closures;
    private boolean canCaptureCallersBinding;
    private boolean canModifyCode;
    private boolean requiresBinding;
    private Stack<IRLoop> loopStack;
    protected int requiredArgs;
    protected int optionalArgs;
    protected int restArg;
    
    private void init() {
        this.instructions = new ArrayList<Instr>();
        this.closures = new ArrayList<IRClosure>();
        this.loopStack = new Stack<IRLoop>();
        this.canModifyCode = true;
        this.canCaptureCallersBinding = true;
        this.requiresBinding = true;
    }
    
    public IRExecutionScope(final IRScope lexicalParent, final Operand container, final String name, final StaticScope staticScope) {
        super(lexicalParent, container, name, staticScope);
        this.requiredArgs = 0;
        this.optionalArgs = 0;
        this.restArg = -1;
        this.init();
    }
    
    public void addClosure(final IRClosure c) {
        this.closures.add(c);
    }
    
    public void addInstr(final Instr i) {
        this.instructions.add(i);
    }
    
    public void startLoop(final IRLoop l) {
        this.loopStack.push(l);
    }
    
    public void endLoop(final IRLoop l) {
        this.loopStack.pop();
    }
    
    public IRLoop getCurrentLoop() {
        return this.loopStack.isEmpty() ? null : this.loopStack.peek();
    }
    
    public List<IRClosure> getClosures() {
        return this.closures;
    }
    
    public List<Instr> getInstrs() {
        return this.instructions;
    }
    
    public IRMethod getClosestMethodAncestor() {
        IRExecutionScope s;
        for (s = this; !(s instanceof IRMethod); s = (IRExecutionScope)s.getLexicalParent()) {}
        return (IRMethod)s;
    }
    
    public void setCodeModificationFlag(final boolean f) {
        this.canModifyCode = f;
    }
    
    public boolean modifiesCode() {
        return this.canModifyCode;
    }
    
    public boolean requiresBinding() {
        return this.requiresBinding;
    }
    
    public boolean canCaptureCallersBinding() {
        return this.canCaptureCallersBinding;
    }
    
    public CFG buildCFG() {
        (this.cfg = new CFG(this)).build(this.instructions);
        return this.cfg;
    }
    
    public CFG getCFG() {
        return this.cfg;
    }
    
    public void runCompilerPassOnNestedScopes(final CompilerPass p) {
    }
    
    public void computeExecutionScopeFlags() {
        this.canModifyCode = true;
        this.canCaptureCallersBinding = false;
        this.requiresBinding = false;
        boolean receivesClosureArg = false;
        for (final Instr i : this.getInstrs()) {
            if (i instanceof ReceiveClosureInstr) {
                receivesClosureArg = true;
            }
            if (i instanceof RubyInternalCallInstr && ((CallInstr)i).getMethodAddr() == MethAddr.ZSUPER) {
                this.canCaptureCallersBinding = true;
            }
            if (i instanceof CallInstr) {
                final CallInstr call = (CallInstr)i;
                if (call.requiresBinding()) {
                    this.requiresBinding = true;
                }
                if (!receivesClosureArg || !call.canBeEval() || call.getCallArgs().length <= 1) {
                    continue;
                }
                this.canCaptureCallersBinding = true;
            }
        }
    }
    
    public String toStringInstrs() {
        final StringBuilder b = new StringBuilder();
        int i = 0;
        for (final Instr instr : this.instructions) {
            if (i > 0) {
                b.append("\n");
            }
            b.append("  ").append(i).append('\t').append(instr);
            ++i;
        }
        if (!this.closures.isEmpty()) {
            b.append("\n\n------ Closures encountered in this scope ------\n");
            for (final IRClosure c : this.closures) {
                b.append(c.toStringBody());
            }
            b.append("------------------------------------------------\n");
        }
        return b.toString();
    }
    
    public String toStringVariables() {
        final Map<Variable, Integer> ends = new HashMap<Variable, Integer>();
        final Map<Variable, Integer> starts = new HashMap<Variable, Integer>();
        final SortedSet<Variable> variables = new TreeSet<Variable>();
        for (int i = this.instructions.size() - 1; i >= 0; --i) {
            final Instr instr = this.instructions.get(i);
            final Variable var = instr.result;
            if (var != null) {
                variables.add(var);
                starts.put(var, i);
            }
            for (final Operand operand : instr.getOperands()) {
                if (operand != null && operand instanceof Variable && ends.get(operand) == null) {
                    ends.put((Variable)operand, i);
                    variables.add((Variable)operand);
                }
            }
        }
        final StringBuilder sb = new StringBuilder();
        int j = 0;
        for (final Variable var2 : variables) {
            final Integer end = ends.get(var2);
            if (end != null) {
                if (j > 0) {
                    sb.append("\n");
                }
                ++j;
                sb.append("    " + var2 + ": " + starts.get(var2) + "-" + end);
            }
        }
        return sb.toString();
    }
    
    @Interp
    public Iterator<LocalVariable> getLiveLocalVariables() {
        final Map<LocalVariable, Integer> ends = new HashMap<LocalVariable, Integer>();
        final Map<LocalVariable, Integer> starts = new HashMap<LocalVariable, Integer>();
        final Set<LocalVariable> variables = new TreeSet<LocalVariable>();
        for (int i = this.instructions.size() - 1; i >= 0; --i) {
            final Instr instr = this.instructions.get(i);
            Variable variable = instr.result;
            if (variable != null && variable instanceof LocalVariable) {
                variables.add((LocalVariable)variable);
                starts.put((LocalVariable)variable, i);
            }
            for (final Operand operand : instr.getOperands()) {
                if (operand instanceof LocalVariable) {
                    variable = (LocalVariable)operand;
                    if (ends.get(variable) == null) {
                        ends.put((LocalVariable)variable, i);
                        variables.add((LocalVariable)variable);
                    }
                }
            }
        }
        return variables.iterator();
    }
    
    @Interp
    public StaticScope allocateStaticScope(final StaticScope parent) {
        final Iterator<LocalVariable> variables = this.getLiveLocalVariables();
        final StaticScope scope = this.constructStaticScope(parent);
        while (variables.hasNext()) {
            final LocalVariable variable = variables.next();
            final int destination = scope.addVariable(variable.getName());
            System.out.println("Allocating " + variable + " to " + destination);
            variable.setLocation(destination);
        }
        return scope;
    }
    
    @Interp
    public void calculateParameterCounts() {
        for (int i = this.instructions.size() - 1; i >= 0; --i) {
            final Instr instr = this.instructions.get(i);
        }
    }
    
    @Interp
    protected abstract StaticScope constructStaticScope(final StaticScope p0);
    
    public Variable getSelf() {
        return this.getLocalVariable("%self");
    }
    
    public LocalVariable getLocalVariable(final String name) {
        return this.getClosestMethodAncestor().getLocalVariable(name);
    }
    
    public int getLocalVariablesCount() {
        return this.getClosestMethodAncestor().getLocalVariablesCount();
    }
}
