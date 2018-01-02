// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.verifier.structurals;

import java.util.Vector;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.classfile.JavaClass;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.bcel.verifier.exc.VerifierConstraintViolatedException;
import org.apache.bcel.generic.ObjectType;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.Repository;
import org.apache.bcel.verifier.VerificationResult;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.ReturnInstruction;
import org.apache.bcel.generic.Type;
import org.apache.bcel.generic.JsrInstruction;
import org.apache.bcel.verifier.exc.AssertionViolatedException;
import org.apache.bcel.generic.ReturnaddressType;
import org.apache.bcel.generic.RET;
import java.util.ArrayList;
import java.util.Random;
import org.apache.bcel.verifier.Verifier;
import org.apache.bcel.verifier.PassVerifier;

public final class Pass3bVerifier extends PassVerifier
{
    private static final boolean DEBUG = true;
    private Verifier myOwner;
    private int method_no;
    
    public Pass3bVerifier(final Verifier owner, final int method_no) {
        this.myOwner = owner;
        this.method_no = method_no;
    }
    
    private void circulationPump(final ControlFlowGraph cfg, final InstructionContext start, final Frame vanillaFrame, final InstConstraintVisitor icv, final ExecutionVisitor ev) {
        final Random random = new Random();
        final InstructionContextQueue icq = new InstructionContextQueue();
        start.execute(vanillaFrame, new ArrayList(), icv, ev);
        icq.add(start, new ArrayList());
        while (!icq.isEmpty()) {
            final InstructionContext u = icq.getIC(0);
            final ArrayList ec = icq.getEC(0);
            icq.remove(0);
            final ArrayList oldchain = (ArrayList)ec.clone();
            final ArrayList newchain = (ArrayList)ec.clone();
            newchain.add(u);
            if (u.getInstruction().getInstruction() instanceof RET) {
                final RET ret = (RET)u.getInstruction().getInstruction();
                final ReturnaddressType t = (ReturnaddressType)u.getOutFrame(oldchain).getLocals().get(ret.getIndex());
                final InstructionContext theSuccessor = cfg.contextOf(t.getTarget());
                InstructionContext lastJSR = null;
                int skip_jsr = 0;
                for (int ss = oldchain.size() - 1; ss >= 0; --ss) {
                    if (skip_jsr < 0) {
                        throw new AssertionViolatedException("More RET than JSR in execution chain?!");
                    }
                    if (oldchain.get(ss).getInstruction().getInstruction() instanceof JsrInstruction) {
                        if (skip_jsr == 0) {
                            lastJSR = oldchain.get(ss);
                            break;
                        }
                        --skip_jsr;
                    }
                    if (oldchain.get(ss).getInstruction().getInstruction() instanceof RET) {
                        ++skip_jsr;
                    }
                }
                if (lastJSR == null) {
                    throw new AssertionViolatedException("RET without a JSR before in ExecutionChain?! EC: '" + oldchain + "'.");
                }
                final JsrInstruction jsr = (JsrInstruction)lastJSR.getInstruction().getInstruction();
                if (theSuccessor != cfg.contextOf(jsr.physicalSuccessor())) {
                    throw new AssertionViolatedException("RET '" + u.getInstruction() + "' info inconsistent: jump back to '" + theSuccessor + "' or '" + cfg.contextOf(jsr.physicalSuccessor()) + "'?");
                }
                if (theSuccessor.execute(u.getOutFrame(oldchain), newchain, icv, ev)) {
                    icq.add(theSuccessor, (ArrayList)newchain.clone());
                }
            }
            else {
                final InstructionContext[] succs = u.getSuccessors();
                for (int s = 0; s < succs.length; ++s) {
                    final InstructionContext v = succs[s];
                    if (v.execute(u.getOutFrame(oldchain), newchain, icv, ev)) {
                        icq.add(v, (ArrayList)newchain.clone());
                    }
                }
            }
            final ExceptionHandler[] exc_hds = u.getExceptionHandlers();
            for (int s = 0; s < exc_hds.length; ++s) {
                final InstructionContext v = cfg.contextOf(exc_hds[s].getHandlerStart());
                if (v.execute(new Frame(u.getOutFrame(oldchain).getLocals(), new OperandStack(u.getOutFrame(oldchain).getStack().maxStack(), (exc_hds[s].getExceptionType() == null) ? Type.THROWABLE : exc_hds[s].getExceptionType())), new ArrayList(), icv, ev)) {
                    icq.add(v, new ArrayList());
                }
            }
        }
        InstructionHandle ih = start.getInstruction();
        do {
            if (ih.getInstruction() instanceof ReturnInstruction && !cfg.isDead(ih)) {
                final InstructionContext ic = cfg.contextOf(ih);
                final Frame f = ic.getOutFrame(new ArrayList());
                final LocalVariables lvs = f.getLocals();
                for (int i = 0; i < lvs.maxLocals(); ++i) {
                    if (lvs.get(i) instanceof UninitializedObjectType) {
                        this.addMessage("Warning: ReturnInstruction '" + ic + "' may leave method with an uninitialized object in the local variables array '" + lvs + "'.");
                    }
                }
                final OperandStack os = f.getStack();
                for (int j = 0; j < os.size(); ++j) {
                    if (os.peek(j) instanceof UninitializedObjectType) {
                        this.addMessage("Warning: ReturnInstruction '" + ic + "' may leave method with an uninitialized object on the operand stack '" + os + "'.");
                    }
                }
            }
        } while ((ih = ih.getNext()) != null);
    }
    
    public VerificationResult do_verify() {
        if (!this.myOwner.doPass3a(this.method_no).equals(VerificationResult.VR_OK)) {
            return VerificationResult.VR_NOTYET;
        }
        final JavaClass jc = Repository.lookupClass(this.myOwner.getClassName());
        final ConstantPoolGen constantPoolGen = new ConstantPoolGen(jc.getConstantPool());
        final InstConstraintVisitor icv = new InstConstraintVisitor();
        icv.setConstantPoolGen(constantPoolGen);
        final ExecutionVisitor ev = new ExecutionVisitor();
        ev.setConstantPoolGen(constantPoolGen);
        final Method[] methods = jc.getMethods();
        try {
            final MethodGen mg = new MethodGen(methods[this.method_no], this.myOwner.getClassName(), constantPoolGen);
            icv.setMethodGen(mg);
            if (!mg.isAbstract() && !mg.isNative()) {
                final ControlFlowGraph cfg = new ControlFlowGraph(mg);
                final Frame f = new Frame(mg.getMaxLocals(), mg.getMaxStack());
                if (!mg.isStatic()) {
                    if (mg.getName().equals("<init>")) {
                        Frame._this = new UninitializedObjectType(new ObjectType(jc.getClassName()));
                        f.getLocals().set(0, Frame._this);
                    }
                    else {
                        Frame._this = null;
                        f.getLocals().set(0, new ObjectType(jc.getClassName()));
                    }
                }
                final Type[] argtypes = mg.getArgumentTypes();
                int twoslotoffset = 0;
                for (int j = 0; j < argtypes.length; ++j) {
                    if (argtypes[j] == Type.SHORT || argtypes[j] == Type.BYTE || argtypes[j] == Type.CHAR || argtypes[j] == Type.BOOLEAN) {
                        argtypes[j] = Type.INT;
                    }
                    f.getLocals().set(twoslotoffset + j + (mg.isStatic() ? 0 : 1), argtypes[j]);
                    if (argtypes[j].getSize() == 2) {
                        ++twoslotoffset;
                        f.getLocals().set(twoslotoffset + j + (mg.isStatic() ? 0 : 1), Type.UNKNOWN);
                    }
                }
                this.circulationPump(cfg, cfg.contextOf(mg.getInstructionList().getStart()), f, icv, ev);
            }
        }
        catch (VerifierConstraintViolatedException ce) {
            ce.extendMessage("Constraint violated in method '" + methods[this.method_no] + "':\n", "");
            return new VerificationResult(2, ce.getMessage());
        }
        catch (RuntimeException re) {
            final StringWriter sw = new StringWriter();
            final PrintWriter pw = new PrintWriter(sw);
            re.printStackTrace(pw);
            throw new AssertionViolatedException("Some RuntimeException occured while verify()ing class '" + jc.getClassName() + "', method '" + methods[this.method_no] + "'. Original RuntimeException's stack trace:\n---\n" + sw + "---\n");
        }
        return VerificationResult.VR_OK;
    }
    
    public int getMethodNo() {
        return this.method_no;
    }
    
    private static final class InstructionContextQueue
    {
        private Vector ics;
        private Vector ecs;
        
        private InstructionContextQueue() {
            this.ics = new Vector();
            this.ecs = new Vector();
        }
        
        public void add(final InstructionContext ic, final ArrayList executionChain) {
            this.ics.add(ic);
            this.ecs.add(executionChain);
        }
        
        public boolean isEmpty() {
            return this.ics.isEmpty();
        }
        
        public void remove() {
            this.remove(0);
        }
        
        public void remove(final int i) {
            this.ics.remove(i);
            this.ecs.remove(i);
        }
        
        public InstructionContext getIC(final int i) {
            return this.ics.get(i);
        }
        
        public ArrayList getEC(final int i) {
            return this.ecs.get(i);
        }
        
        public int size() {
            return this.ics.size();
        }
    }
}
