// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.verifier.structurals;

import org.apache.bcel.generic.Select;
import org.apache.bcel.generic.BranchInstruction;
import org.apache.bcel.generic.GotoInstruction;
import org.apache.bcel.generic.ATHROW;
import org.apache.bcel.generic.ReturnInstruction;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.JsrInstruction;
import org.apache.bcel.generic.RET;
import org.apache.bcel.verifier.exc.StructuralCodeConstraintException;
import org.apache.bcel.generic.Visitor;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.bcel.verifier.exc.AssertionViolatedException;
import org.apache.bcel.generic.InstructionHandle;
import java.util.Hashtable;
import org.apache.bcel.generic.MethodGen;

public class ControlFlowGraph
{
    private final MethodGen method_gen;
    private final Subroutines subroutines;
    private final ExceptionHandlers exceptionhandlers;
    private Hashtable instructionContexts;
    
    public ControlFlowGraph(final MethodGen method_gen) {
        this.instructionContexts = new Hashtable();
        this.subroutines = new Subroutines(method_gen);
        this.exceptionhandlers = new ExceptionHandlers(method_gen);
        final InstructionHandle[] instructionhandles = method_gen.getInstructionList().getInstructionHandles();
        for (int i = 0; i < instructionhandles.length; ++i) {
            this.instructionContexts.put(instructionhandles[i], new InstructionContextImpl(instructionhandles[i]));
        }
        this.method_gen = method_gen;
    }
    
    public InstructionContext contextOf(final InstructionHandle inst) {
        final InstructionContext ic = this.instructionContexts.get(inst);
        if (ic == null) {
            throw new AssertionViolatedException("InstructionContext requested for an InstructionHandle that's not known!");
        }
        return ic;
    }
    
    public InstructionContext[] contextsOf(final InstructionHandle[] insts) {
        final InstructionContext[] ret = new InstructionContext[insts.length];
        for (int i = 0; i < insts.length; ++i) {
            ret[i] = this.contextOf(insts[i]);
        }
        return ret;
    }
    
    public InstructionContext[] getInstructionContexts() {
        final InstructionContext[] ret = new InstructionContext[this.instructionContexts.values().size()];
        return (InstructionContext[])this.instructionContexts.values().toArray(ret);
    }
    
    public boolean isDead(final InstructionHandle i) {
        return this.instructionContexts.containsKey(i);
    }
    
    private class InstructionContextImpl implements InstructionContext
    {
        private int TAG;
        private InstructionHandle instruction;
        private HashMap inFrames;
        private HashMap outFrames;
        private ArrayList executionPredecessors;
        
        public InstructionContextImpl(final InstructionHandle inst) {
            this.executionPredecessors = null;
            if (inst == null) {
                throw new AssertionViolatedException("Cannot instantiate InstructionContextImpl from NULL.");
            }
            this.instruction = inst;
            this.inFrames = new HashMap();
            this.outFrames = new HashMap();
        }
        
        public int getTag() {
            return this.TAG;
        }
        
        public void setTag(final int tag) {
            this.TAG = tag;
        }
        
        public ExceptionHandler[] getExceptionHandlers() {
            return ControlFlowGraph.this.exceptionhandlers.getExceptionHandlers(this.getInstruction());
        }
        
        public Frame getOutFrame(final ArrayList execChain) {
            this.executionPredecessors = execChain;
            final InstructionContext jsr = this.lastExecutionJSR();
            final Frame org = this.outFrames.get(jsr);
            if (org == null) {
                throw new AssertionViolatedException("outFrame not set! This:\n" + this + "\nExecutionChain: " + this.getExecutionChain() + "\nOutFrames: '" + this.outFrames + "'.");
            }
            return org.getClone();
        }
        
        public boolean execute(final Frame inFrame, final ArrayList execPreds, final InstConstraintVisitor icv, final ExecutionVisitor ev) {
            this.executionPredecessors = (ArrayList)execPreds.clone();
            if (this.lastExecutionJSR() == null && ControlFlowGraph.this.subroutines.subroutineOf(this.getInstruction()) != ControlFlowGraph.this.subroutines.getTopLevel()) {
                throw new AssertionViolatedException("Huh?! Am I '" + this + "' part of a subroutine or not?");
            }
            if (this.lastExecutionJSR() != null && ControlFlowGraph.this.subroutines.subroutineOf(this.getInstruction()) == ControlFlowGraph.this.subroutines.getTopLevel()) {
                throw new AssertionViolatedException("Huh?! Am I '" + this + "' part of a subroutine or not?");
            }
            Frame inF = this.inFrames.get(this.lastExecutionJSR());
            if (inF == null) {
                this.inFrames.put(this.lastExecutionJSR(), inFrame);
                inF = inFrame;
            }
            else {
                if (inF.equals(inFrame)) {
                    return false;
                }
                if (!this.mergeInFrames(inFrame)) {
                    return false;
                }
            }
            final Frame workingFrame = inF.getClone();
            try {
                icv.setFrame(workingFrame);
                this.getInstruction().accept(icv);
            }
            catch (StructuralCodeConstraintException ce) {
                ce.extendMessage("", "\nInstructionHandle: " + this.getInstruction() + "\n");
                ce.extendMessage("", "\nExecution Frame:\n" + workingFrame);
                this.extendMessageWithFlow(ce);
                throw ce;
            }
            ev.setFrame(workingFrame);
            this.getInstruction().accept(ev);
            this.outFrames.put(this.lastExecutionJSR(), workingFrame);
            return true;
        }
        
        public String toString() {
            final String ret = this.getInstruction().toString(false) + "\t[InstructionContext]";
            return ret;
        }
        
        private boolean mergeInFrames(final Frame inFrame) {
            final Frame inF = this.inFrames.get(this.lastExecutionJSR());
            final OperandStack oldstack = inF.getStack().getClone();
            final LocalVariables oldlocals = inF.getLocals().getClone();
            try {
                inF.getStack().merge(inFrame.getStack());
                inF.getLocals().merge(inFrame.getLocals());
            }
            catch (StructuralCodeConstraintException sce) {
                this.extendMessageWithFlow(sce);
                throw sce;
            }
            return !oldstack.equals(inF.getStack()) || !oldlocals.equals(inF.getLocals());
        }
        
        private String getExecutionChain() {
            String s = this.toString();
            for (int i = this.executionPredecessors.size() - 1; i >= 0; --i) {
                s = this.executionPredecessors.get(i) + "\n" + s;
            }
            return s;
        }
        
        private void extendMessageWithFlow(final StructuralCodeConstraintException e) {
            final String s = "Execution flow:\n";
            e.extendMessage("", s + this.getExecutionChain());
        }
        
        public InstructionHandle getInstruction() {
            return this.instruction;
        }
        
        private InstructionContextImpl lastExecutionJSR() {
            final int size = this.executionPredecessors.size();
            int retcount = 0;
            for (int i = size - 1; i >= 0; --i) {
                final InstructionContextImpl current = this.executionPredecessors.get(i);
                final Instruction currentlast = current.getInstruction().getInstruction();
                if (currentlast instanceof RET) {
                    ++retcount;
                }
                if (currentlast instanceof JsrInstruction && --retcount == -1) {
                    return current;
                }
            }
            return null;
        }
        
        public InstructionContext[] getSuccessors() {
            return ControlFlowGraph.this.contextsOf(this._getSuccessors());
        }
        
        private InstructionHandle[] _getSuccessors() {
            final InstructionHandle[] empty = new InstructionHandle[0];
            final InstructionHandle[] single = { null };
            final InstructionHandle[] pair = new InstructionHandle[2];
            final Instruction inst = this.getInstruction().getInstruction();
            if (inst instanceof RET) {
                final Subroutine s = ControlFlowGraph.this.subroutines.subroutineOf(this.getInstruction());
                if (s == null) {
                    throw new AssertionViolatedException("Asking for successors of a RET in dead code?!");
                }
                throw new AssertionViolatedException("DID YOU REALLY WANT TO ASK FOR RET'S SUCCS?");
            }
            else {
                if (inst instanceof ReturnInstruction) {
                    return empty;
                }
                if (inst instanceof ATHROW) {
                    return empty;
                }
                if (inst instanceof JsrInstruction) {
                    single[0] = ((JsrInstruction)inst).getTarget();
                    return single;
                }
                if (inst instanceof GotoInstruction) {
                    single[0] = ((GotoInstruction)inst).getTarget();
                    return single;
                }
                if (!(inst instanceof BranchInstruction)) {
                    single[0] = this.getInstruction().getNext();
                    return single;
                }
                if (inst instanceof Select) {
                    final InstructionHandle[] matchTargets = ((Select)inst).getTargets();
                    final InstructionHandle[] ret = new InstructionHandle[matchTargets.length + 1];
                    ret[0] = ((Select)inst).getTarget();
                    System.arraycopy(matchTargets, 0, ret, 1, matchTargets.length);
                    return ret;
                }
                pair[0] = this.getInstruction().getNext();
                pair[1] = ((BranchInstruction)inst).getTarget();
                return pair;
            }
        }
    }
}
