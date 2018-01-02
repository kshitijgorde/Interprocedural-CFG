// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.verifier.structurals;

import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.IndexedInstruction;
import org.apache.bcel.generic.LocalVariableInstruction;
import org.apache.bcel.generic.Select;
import org.apache.bcel.generic.BranchInstruction;
import org.apache.bcel.generic.GotoInstruction;
import org.apache.bcel.generic.ATHROW;
import org.apache.bcel.generic.ReturnInstruction;
import org.apache.bcel.verifier.exc.AssertionViolatedException;
import org.apache.bcel.generic.RET;
import java.util.Enumeration;
import java.util.Iterator;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.CodeExceptionGen;
import org.apache.bcel.verifier.exc.StructuralCodeConstraintException;
import java.util.ArrayList;
import java.awt.Color;
import org.apache.bcel.generic.ASTORE;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.JsrInstruction;
import java.util.HashSet;
import org.apache.bcel.generic.MethodGen;
import java.util.Hashtable;

public class Subroutines
{
    private Hashtable subroutines;
    public final Subroutine TOPLEVEL;
    
    public Subroutines(final MethodGen mg) {
        this.subroutines = new Hashtable();
        final InstructionHandle[] all = mg.getInstructionList().getInstructionHandles();
        final CodeExceptionGen[] handlers = mg.getExceptionHandlers();
        this.TOPLEVEL = new SubroutineImpl();
        final HashSet sub_leaders = new HashSet();
        final InstructionHandle ih = all[0];
        for (int i = 0; i < all.length; ++i) {
            final Instruction inst = all[i].getInstruction();
            if (inst instanceof JsrInstruction) {
                sub_leaders.add(((JsrInstruction)inst).getTarget());
            }
        }
        Iterator iter = sub_leaders.iterator();
        while (iter.hasNext()) {
            final SubroutineImpl sr = new SubroutineImpl();
            final InstructionHandle astore = iter.next();
            sr.setLocalVariable(((ASTORE)astore.getInstruction()).getIndex());
            this.subroutines.put(astore, sr);
        }
        this.subroutines.put(all[0], this.TOPLEVEL);
        sub_leaders.add(all[0]);
        for (int j = 0; j < all.length; ++j) {
            final Instruction inst2 = all[j].getInstruction();
            if (inst2 instanceof JsrInstruction) {
                final InstructionHandle leader = ((JsrInstruction)inst2).getTarget();
                ((SubroutineImpl)this.getSubroutine(leader)).addEnteringJsrInstruction(all[j]);
            }
        }
        final HashSet instructions_assigned = new HashSet();
        final Hashtable colors = new Hashtable();
        iter = sub_leaders.iterator();
        while (iter.hasNext()) {
            final InstructionHandle actual = iter.next();
            for (int k = 0; k < all.length; ++k) {
                colors.put(all[k], Color.white);
            }
            colors.put(actual, Color.gray);
            final ArrayList Q = new ArrayList();
            Q.add(actual);
            if (actual == all[0]) {
                for (int l = 0; l < handlers.length; ++l) {
                    colors.put(handlers[l].getHandlerPC(), Color.gray);
                    Q.add(handlers[l].getHandlerPC());
                }
            }
            while (Q.size() != 0) {
                final InstructionHandle u = Q.remove(0);
                final InstructionHandle[] successors = getSuccessors(u);
                for (int m = 0; m < successors.length; ++m) {
                    if (colors.get(successors[m]) == Color.white) {
                        colors.put(successors[m], Color.gray);
                        Q.add(successors[m]);
                    }
                }
                colors.put(u, Color.black);
            }
            for (int i2 = 0; i2 < all.length; ++i2) {
                if (colors.get(all[i2]) == Color.black) {
                    ((SubroutineImpl)((actual == all[0]) ? this.getTopLevel() : this.getSubroutine(actual))).addInstruction(all[i2]);
                    if (instructions_assigned.contains(all[i2])) {
                        throw new StructuralCodeConstraintException("Instruction '" + all[i2] + "' is part of more than one subroutine (or of the top level and a subroutine).");
                    }
                    instructions_assigned.add(all[i2]);
                }
            }
            if (actual != all[0]) {
                ((SubroutineImpl)this.getSubroutine(actual)).setLeavingRET();
            }
        }
        for (int i3 = 0; i3 < handlers.length; ++i3) {
            for (InstructionHandle _protected = handlers[i3].getStartPC(); _protected != handlers[i3].getEndPC().getNext(); _protected = _protected.getNext()) {
                final Enumeration subs = this.subroutines.elements();
                while (subs.hasMoreElements()) {
                    final Subroutine sub = subs.nextElement();
                    if (sub != this.subroutines.get(all[0]) && sub.contains(_protected)) {
                        throw new StructuralCodeConstraintException("Subroutine instruction '" + _protected + "' is protected by an exception handler, '" + handlers[i3] + "'. This is forbidden by the JustIce verifier due to its clear definition of subroutines.");
                    }
                }
            }
        }
        this.noRecursiveCalls(this.getTopLevel(), new HashSet());
    }
    
    private void noRecursiveCalls(final Subroutine sub, final HashSet set) {
        final Subroutine[] subs = sub.subSubs();
        for (int i = 0; i < subs.length; ++i) {
            final int index = ((RET)subs[i].getLeavingRET().getInstruction()).getIndex();
            if (!set.add(new Integer(index))) {
                final SubroutineImpl si = (SubroutineImpl)subs[i];
                throw new StructuralCodeConstraintException("Subroutine with local variable '" + si.localVariable + "', JSRs '" + si.theJSRs + "', RET '" + si.theRET + "' is called by a subroutine which uses the same local variable index as itself; maybe even a recursive call? JustIce's clean definition of a subroutine forbids both.");
            }
            this.noRecursiveCalls(subs[i], set);
            set.remove(new Integer(index));
        }
    }
    
    public Subroutine getSubroutine(final InstructionHandle leader) {
        final Subroutine ret = this.subroutines.get(leader);
        if (ret == null) {
            throw new AssertionViolatedException("Subroutine requested for an InstructionHandle that is not a leader of a subroutine.");
        }
        if (ret == this.TOPLEVEL) {
            throw new AssertionViolatedException("TOPLEVEL special subroutine requested; use getTopLevel().");
        }
        return ret;
    }
    
    public Subroutine subroutineOf(final InstructionHandle any) {
        for (final Subroutine s : this.subroutines.values()) {
            if (s.contains(any)) {
                return s;
            }
        }
        System.err.println("DEBUG: Please verify '" + any + "' lies in dead code.");
        return null;
    }
    
    public Subroutine getTopLevel() {
        return this.TOPLEVEL;
    }
    
    private static InstructionHandle[] getSuccessors(final InstructionHandle instruction) {
        final InstructionHandle[] empty = new InstructionHandle[0];
        final InstructionHandle[] single = { null };
        final InstructionHandle[] pair = new InstructionHandle[2];
        final Instruction inst = instruction.getInstruction();
        if (inst instanceof RET) {
            return empty;
        }
        if (inst instanceof ReturnInstruction) {
            return empty;
        }
        if (inst instanceof ATHROW) {
            return empty;
        }
        if (inst instanceof JsrInstruction) {
            single[0] = instruction.getNext();
            return single;
        }
        if (inst instanceof GotoInstruction) {
            single[0] = ((GotoInstruction)inst).getTarget();
            return single;
        }
        if (!(inst instanceof BranchInstruction)) {
            single[0] = instruction.getNext();
            return single;
        }
        if (inst instanceof Select) {
            final InstructionHandle[] matchTargets = ((Select)inst).getTargets();
            final InstructionHandle[] ret = new InstructionHandle[matchTargets.length + 1];
            ret[0] = ((Select)inst).getTarget();
            System.arraycopy(matchTargets, 0, ret, 1, matchTargets.length);
            return ret;
        }
        pair[0] = instruction.getNext();
        pair[1] = ((BranchInstruction)inst).getTarget();
        return pair;
    }
    
    public String toString() {
        return "---\n" + this.subroutines.toString() + "\n---\n";
    }
    
    private class SubroutineImpl implements Subroutine
    {
        private final int UNSET = -1;
        private int localVariable;
        private HashSet instructions;
        private HashSet theJSRs;
        private InstructionHandle theRET;
        
        public boolean contains(final InstructionHandle inst) {
            return this.instructions.contains(inst);
        }
        
        public String toString() {
            String ret = "Subroutine: Local variable is '" + this.localVariable + "', JSRs are '" + this.theJSRs + "', RET is '" + this.theRET + "', Instructions: '" + this.instructions.toString() + "'.";
            ret += " Accessed local variable slots: '";
            int[] alv = this.getAccessedLocalsIndices();
            for (int i = 0; i < alv.length; ++i) {
                ret = ret + alv[i] + " ";
            }
            ret += "'.";
            ret += " Recursively (via subsub...routines) accessed local variable slots: '";
            alv = this.getRecursivelyAccessedLocalsIndices();
            for (int j = 0; j < alv.length; ++j) {
                ret = ret + alv[j] + " ";
            }
            ret += "'.";
            return ret;
        }
        
        void setLeavingRET() {
            if (this.localVariable == -1) {
                throw new AssertionViolatedException("setLeavingRET() called for top-level 'subroutine' or forgot to set local variable first.");
            }
            final Iterator iter = this.instructions.iterator();
            InstructionHandle ret = null;
            while (iter.hasNext()) {
                final InstructionHandle actual = iter.next();
                if (actual.getInstruction() instanceof RET) {
                    if (ret != null) {
                        throw new StructuralCodeConstraintException("Subroutine with more then one RET detected: '" + ret + "' and '" + actual + "'.");
                    }
                    ret = actual;
                }
            }
            if (ret == null) {
                throw new StructuralCodeConstraintException("Subroutine without a RET detected.");
            }
            if (((RET)ret.getInstruction()).getIndex() != this.localVariable) {
                throw new StructuralCodeConstraintException("Subroutine uses '" + ret + "' which does not match the correct local variable '" + this.localVariable + "'.");
            }
            this.theRET = ret;
        }
        
        public InstructionHandle[] getEnteringJsrInstructions() {
            if (this == Subroutines.this.TOPLEVEL) {
                throw new AssertionViolatedException("getLeavingRET() called on top level pseudo-subroutine.");
            }
            final InstructionHandle[] jsrs = new InstructionHandle[this.theJSRs.size()];
            return (InstructionHandle[])this.theJSRs.toArray(jsrs);
        }
        
        public void addEnteringJsrInstruction(final InstructionHandle jsrInst) {
            if (jsrInst == null || !(jsrInst.getInstruction() instanceof JsrInstruction)) {
                throw new AssertionViolatedException("Expecting JsrInstruction InstructionHandle.");
            }
            if (this.localVariable == -1) {
                throw new AssertionViolatedException("Set the localVariable first!");
            }
            if (this.localVariable != ((ASTORE)((JsrInstruction)jsrInst.getInstruction()).getTarget().getInstruction()).getIndex()) {
                throw new AssertionViolatedException("Setting a wrong JsrInstruction.");
            }
            this.theJSRs.add(jsrInst);
        }
        
        public InstructionHandle getLeavingRET() {
            if (this == Subroutines.this.TOPLEVEL) {
                throw new AssertionViolatedException("getLeavingRET() called on top level pseudo-subroutine.");
            }
            return this.theRET;
        }
        
        public InstructionHandle[] getInstructions() {
            final InstructionHandle[] ret = new InstructionHandle[this.instructions.size()];
            return (InstructionHandle[])this.instructions.toArray(ret);
        }
        
        void addInstruction(final InstructionHandle ih) {
            if (this.theRET != null) {
                throw new AssertionViolatedException("All instructions must have been added before invoking setLeavingRET().");
            }
            this.instructions.add(ih);
        }
        
        public int[] getRecursivelyAccessedLocalsIndices() {
            final HashSet s = new HashSet();
            final int[] lvs = this.getAccessedLocalsIndices();
            for (int j = 0; j < lvs.length; ++j) {
                s.add(new Integer(lvs[j]));
            }
            this._getRecursivelyAccessedLocalsIndicesHelper(s, this.subSubs());
            final int[] ret = new int[s.size()];
            final Iterator i = s.iterator();
            int k = -1;
            while (i.hasNext()) {
                ++k;
                ret[k] = i.next();
            }
            return ret;
        }
        
        private void _getRecursivelyAccessedLocalsIndicesHelper(final HashSet s, final Subroutine[] subs) {
            for (int i = 0; i < subs.length; ++i) {
                final int[] lvs = subs[i].getAccessedLocalsIndices();
                for (int j = 0; j < lvs.length; ++j) {
                    s.add(new Integer(lvs[j]));
                }
                if (subs[i].subSubs().length != 0) {
                    this._getRecursivelyAccessedLocalsIndicesHelper(s, subs[i].subSubs());
                }
            }
        }
        
        public int[] getAccessedLocalsIndices() {
            final HashSet acc = new HashSet();
            if (this.theRET == null && this != Subroutines.this.TOPLEVEL) {
                throw new AssertionViolatedException("This subroutine object must be built up completely before calculating accessed locals.");
            }
            for (final InstructionHandle ih : this.instructions) {
                if (ih.getInstruction() instanceof LocalVariableInstruction || ih.getInstruction() instanceof RET) {
                    final int idx = ((IndexedInstruction)ih.getInstruction()).getIndex();
                    acc.add(new Integer(idx));
                    try {
                        if (!(ih.getInstruction() instanceof LocalVariableInstruction)) {
                            continue;
                        }
                        final int s = ((LocalVariableInstruction)ih.getInstruction()).getType(null).getSize();
                        if (s != 2) {
                            continue;
                        }
                        acc.add(new Integer(idx + 1));
                    }
                    catch (RuntimeException re) {
                        throw new AssertionViolatedException("Oops. BCEL did not like NULL as a ConstantPoolGen object.");
                    }
                }
            }
            final int[] ret = new int[acc.size()];
            final Iterator i = acc.iterator();
            int j = -1;
            while (i.hasNext()) {
                ++j;
                ret[j] = i.next();
            }
            return ret;
        }
        
        public Subroutine[] subSubs() {
            final HashSet h = new HashSet();
            final Iterator i = this.instructions.iterator();
            while (i.hasNext()) {
                final Instruction inst = i.next().getInstruction();
                if (inst instanceof JsrInstruction) {
                    final InstructionHandle targ = ((JsrInstruction)inst).getTarget();
                    h.add(Subroutines.this.getSubroutine(targ));
                }
            }
            final Subroutine[] ret = new Subroutine[h.size()];
            return (Subroutine[])h.toArray(ret);
        }
        
        void setLocalVariable(final int i) {
            if (this.localVariable != -1) {
                throw new AssertionViolatedException("localVariable set twice.");
            }
            this.localVariable = i;
        }
        
        public SubroutineImpl() {
            this.localVariable = -1;
            this.instructions = new HashSet();
            this.theJSRs = new HashSet();
        }
    }
}
