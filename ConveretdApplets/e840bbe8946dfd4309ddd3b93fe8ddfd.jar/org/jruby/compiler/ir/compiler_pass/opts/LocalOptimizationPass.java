// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.compiler_pass.opts;

import org.jruby.compiler.ir.instructions.METHOD_VERSION_GUARD_Instr;
import org.jruby.compiler.ir.IRModule;
import org.jruby.compiler.ir.IRMethod;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.compiler.ir.operands.Array;
import org.jruby.compiler.ir.operands.Float;
import org.jruby.compiler.ir.operands.Constant;
import org.jruby.compiler.ir.operands.Fixnum;
import java.util.ListIterator;
import org.jruby.compiler.ir.instructions.CallInstr;
import org.jruby.compiler.ir.instructions.JumpInstr;
import org.jruby.compiler.ir.instructions.CopyInstr;
import org.jruby.compiler.ir.operands.BreakResult;
import org.jruby.compiler.ir.instructions.Instr;
import org.jruby.compiler.ir.CodeVersion;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.Variable;
import java.util.Iterator;
import java.util.List;
import org.jruby.compiler.ir.IRClosure;
import org.jruby.compiler.ir.IRExecutionScope;
import org.jruby.compiler.ir.IRScope;
import org.jruby.compiler.ir.compiler_pass.CompilerPass;

public class LocalOptimizationPass implements CompilerPass
{
    public boolean isPreOrder() {
        return false;
    }
    
    public void run(final IRScope s) {
        if (s instanceof IRExecutionScope) {
            final IRExecutionScope es = (IRExecutionScope)s;
            final List<IRClosure> closures = es.getClosures();
            for (final IRClosure c : closures) {
                this.run(c);
            }
            runLocalOpts(es);
            es.computeExecutionScopeFlags();
        }
    }
    
    private static void recordSimplification(final Variable res, final Operand val, final Map<Operand, Operand> valueMap, final Map<Variable, List<Variable>> simplificationMap) {
        valueMap.put(res, val);
        if (val instanceof Variable) {
            final Variable v = (Variable)val;
            List<Variable> x = simplificationMap.get(val);
            if (x == null) {
                x = new ArrayList<Variable>();
                simplificationMap.put(v, x);
            }
            x.add(res);
        }
    }
    
    private static void runLocalOpts(final IRExecutionScope s) {
        final Label deoptLabel = s.getNewLabel();
        Map<Operand, Operand> valueMap = new HashMap<Operand, Operand>();
        Map<Variable, List<Variable>> simplificationMap = new HashMap<Variable, List<Variable>>();
        Map<String, CodeVersion> versionMap = new HashMap<String, CodeVersion>();
        final ListIterator<Instr> instrs = s.getInstrs().listIterator();
        while (instrs.hasNext()) {
            final Instr i = instrs.next();
            final Operation iop = i.operation;
            if (iop.startsBasicBlock()) {
                valueMap = new HashMap<Operand, Operand>();
                simplificationMap = new HashMap<Variable, List<Variable>>();
                versionMap = new HashMap<String, CodeVersion>();
            }
            Operand val = i.simplifyAndGetResult(valueMap);
            final Variable res = i.getResult();
            if (val != null && res != null && res != val) {
                recordSimplification(res, val, valueMap, simplificationMap);
                if (val instanceof BreakResult) {
                    final BreakResult br = (BreakResult)val;
                    i.markDead();
                    instrs.add(new CopyInstr(res, br._result));
                    instrs.add(new JumpInstr(br._jumpTarget));
                }
            }
            else if (iop.isCall()) {
                val = null;
                final CallInstr call = (CallInstr)i;
                Operand r = call.getReceiver();
                if (r != null) {
                    if (!r.isConstant()) {
                        final Operand v = valueMap.get(r);
                        if (v != null) {
                            r = v;
                        }
                    }
                    final IRMethod rm = call.getTargetMethodWithReceiver(r);
                    if (rm != null) {
                        final IRModule rc = rm.getDefiningIRModule();
                        if (rc != null) {
                            if (rc.isCoreClass("Fixnum")) {
                                final Operand[] args = call.getOperands();
                                if (args[2].isConstant()) {
                                    addMethodGuard(rm, deoptLabel, versionMap, instrs);
                                    val = ((Fixnum)r).computeValue(rm.getName(), (Constant)args[2]);
                                }
                            }
                            else if (rc.isCoreClass("Float")) {
                                final Operand[] args = call.getOperands();
                                if (args[2].isConstant()) {
                                    addMethodGuard(rm, deoptLabel, versionMap, instrs);
                                    val = ((Float)r).computeValue(rm.getName(), (Constant)args[2]);
                                }
                            }
                            else if (rc.isCoreClass("Array")) {
                                final Operand[] args = call.getOperands();
                                if (args[2] instanceof Fixnum && rm.getName() == "[]") {
                                    addMethodGuard(rm, deoptLabel, versionMap, instrs);
                                    val = ((Array)r).fetchCompileTimeArrayElement((int)(Object)((Fixnum)args[2]).value, false);
                                }
                            }
                        }
                        if (val != null) {
                            i.markDead();
                            instrs.add(new CopyInstr(res, val));
                            recordSimplification(res, val, valueMap, simplificationMap);
                        }
                    }
                }
            }
            if (res != null) {
                final List<Variable> simplifiedVars = simplificationMap.get(res);
                if (simplifiedVars != null) {
                    for (final Variable v2 : simplifiedVars) {
                        valueMap.remove(v2);
                    }
                    simplificationMap.remove(res);
                }
            }
            if (iop.endsBasicBlock() || (iop.isCall() && !i.isDead())) {
                valueMap = new HashMap<Operand, Operand>();
                simplificationMap = new HashMap<Variable, List<Variable>>();
                versionMap = new HashMap<String, CodeVersion>();
            }
        }
    }
    
    private static void addMethodGuard(final IRMethod m, final Label deoptLabel, final Map<String, CodeVersion> versionMap, final ListIterator instrs) {
        final String fullName = m.getFullyQualifiedName();
        final CodeVersion knownVersion = versionMap.get(fullName);
        final CodeVersion mVersion = m.getVersion();
        if (knownVersion == null || knownVersion._version != mVersion._version) {
            instrs.add(new METHOD_VERSION_GUARD_Instr(m, m.getVersion(), deoptLabel));
            versionMap.put(fullName, mVersion);
        }
    }
}
