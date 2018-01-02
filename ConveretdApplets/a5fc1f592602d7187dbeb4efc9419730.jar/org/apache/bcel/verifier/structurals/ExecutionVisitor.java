// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.verifier.structurals;

import org.apache.bcel.generic.TABLESWITCH;
import org.apache.bcel.generic.SWAP;
import org.apache.bcel.generic.SIPUSH;
import org.apache.bcel.generic.SASTORE;
import org.apache.bcel.generic.SALOAD;
import org.apache.bcel.generic.RETURN;
import org.apache.bcel.generic.RET;
import org.apache.bcel.generic.PUTSTATIC;
import org.apache.bcel.generic.PUTFIELD;
import org.apache.bcel.generic.POP2;
import org.apache.bcel.generic.POP;
import org.apache.bcel.generic.NOP;
import org.apache.bcel.generic.NEWARRAY;
import org.apache.bcel.generic.ObjectType;
import org.apache.bcel.generic.NEW;
import org.apache.bcel.generic.MULTIANEWARRAY;
import org.apache.bcel.generic.MONITOREXIT;
import org.apache.bcel.generic.MONITORENTER;
import org.apache.bcel.generic.LXOR;
import org.apache.bcel.generic.LUSHR;
import org.apache.bcel.generic.LSUB;
import org.apache.bcel.generic.LSTORE;
import org.apache.bcel.generic.LSHR;
import org.apache.bcel.generic.LSHL;
import org.apache.bcel.generic.LRETURN;
import org.apache.bcel.generic.LREM;
import org.apache.bcel.generic.LOR;
import org.apache.bcel.generic.LOOKUPSWITCH;
import org.apache.bcel.generic.LNEG;
import org.apache.bcel.generic.LMUL;
import org.apache.bcel.generic.LLOAD;
import org.apache.bcel.generic.LDIV;
import org.apache.bcel.classfile.ConstantDouble;
import org.apache.bcel.classfile.ConstantLong;
import org.apache.bcel.generic.LDC2_W;
import org.apache.bcel.generic.LDC_W;
import org.apache.bcel.classfile.Constant;
import org.apache.bcel.classfile.ConstantString;
import org.apache.bcel.classfile.ConstantFloat;
import org.apache.bcel.classfile.ConstantInteger;
import org.apache.bcel.generic.LDC;
import org.apache.bcel.generic.LCONST;
import org.apache.bcel.generic.LCMP;
import org.apache.bcel.generic.LASTORE;
import org.apache.bcel.generic.LAND;
import org.apache.bcel.generic.LALOAD;
import org.apache.bcel.generic.LADD;
import org.apache.bcel.generic.L2I;
import org.apache.bcel.generic.L2F;
import org.apache.bcel.generic.L2D;
import org.apache.bcel.generic.JSR_W;
import org.apache.bcel.generic.ReturnaddressType;
import org.apache.bcel.generic.JSR;
import org.apache.bcel.generic.IXOR;
import org.apache.bcel.generic.IUSHR;
import org.apache.bcel.generic.ISUB;
import org.apache.bcel.generic.ISTORE;
import org.apache.bcel.generic.ISHR;
import org.apache.bcel.generic.ISHL;
import org.apache.bcel.generic.IRETURN;
import org.apache.bcel.generic.IREM;
import org.apache.bcel.generic.IOR;
import org.apache.bcel.generic.INVOKEVIRTUAL;
import org.apache.bcel.generic.INVOKESTATIC;
import org.apache.bcel.generic.INVOKESPECIAL;
import org.apache.bcel.generic.INVOKEINTERFACE;
import org.apache.bcel.generic.INSTANCEOF;
import org.apache.bcel.generic.INEG;
import org.apache.bcel.generic.IMUL;
import org.apache.bcel.generic.ILOAD;
import org.apache.bcel.generic.IINC;
import org.apache.bcel.generic.IFNULL;
import org.apache.bcel.generic.IFNONNULL;
import org.apache.bcel.generic.IFNE;
import org.apache.bcel.generic.IFLT;
import org.apache.bcel.generic.IFLE;
import org.apache.bcel.generic.IFGT;
import org.apache.bcel.generic.IFGE;
import org.apache.bcel.generic.IFEQ;
import org.apache.bcel.generic.IF_ICMPNE;
import org.apache.bcel.generic.IF_ICMPLT;
import org.apache.bcel.generic.IF_ICMPLE;
import org.apache.bcel.generic.IF_ICMPGT;
import org.apache.bcel.generic.IF_ICMPGE;
import org.apache.bcel.generic.IF_ICMPEQ;
import org.apache.bcel.generic.IF_ACMPNE;
import org.apache.bcel.generic.IF_ACMPEQ;
import org.apache.bcel.generic.IDIV;
import org.apache.bcel.generic.ICONST;
import org.apache.bcel.generic.IASTORE;
import org.apache.bcel.generic.IAND;
import org.apache.bcel.generic.IALOAD;
import org.apache.bcel.generic.IADD;
import org.apache.bcel.generic.I2S;
import org.apache.bcel.generic.I2L;
import org.apache.bcel.generic.I2F;
import org.apache.bcel.generic.I2D;
import org.apache.bcel.generic.I2C;
import org.apache.bcel.generic.I2B;
import org.apache.bcel.generic.GOTO_W;
import org.apache.bcel.generic.GOTO;
import org.apache.bcel.generic.GETSTATIC;
import org.apache.bcel.generic.GETFIELD;
import org.apache.bcel.generic.FSUB;
import org.apache.bcel.generic.FSTORE;
import org.apache.bcel.generic.FRETURN;
import org.apache.bcel.generic.FREM;
import org.apache.bcel.generic.FNEG;
import org.apache.bcel.generic.FMUL;
import org.apache.bcel.generic.FLOAD;
import org.apache.bcel.generic.FDIV;
import org.apache.bcel.generic.FCONST;
import org.apache.bcel.generic.FCMPL;
import org.apache.bcel.generic.FCMPG;
import org.apache.bcel.generic.FASTORE;
import org.apache.bcel.generic.FALOAD;
import org.apache.bcel.generic.FADD;
import org.apache.bcel.generic.F2L;
import org.apache.bcel.generic.F2I;
import org.apache.bcel.generic.F2D;
import org.apache.bcel.generic.DUP2_X2;
import org.apache.bcel.generic.DUP2_X1;
import org.apache.bcel.generic.DUP2;
import org.apache.bcel.generic.DUP_X2;
import org.apache.bcel.generic.DUP_X1;
import org.apache.bcel.generic.DUP;
import org.apache.bcel.generic.DSUB;
import org.apache.bcel.generic.DSTORE;
import org.apache.bcel.generic.DRETURN;
import org.apache.bcel.generic.DREM;
import org.apache.bcel.generic.DNEG;
import org.apache.bcel.generic.DMUL;
import org.apache.bcel.generic.DLOAD;
import org.apache.bcel.generic.DDIV;
import org.apache.bcel.generic.DCONST;
import org.apache.bcel.generic.DCMPL;
import org.apache.bcel.generic.DCMPG;
import org.apache.bcel.generic.DASTORE;
import org.apache.bcel.generic.DALOAD;
import org.apache.bcel.generic.DADD;
import org.apache.bcel.generic.D2L;
import org.apache.bcel.generic.D2I;
import org.apache.bcel.generic.D2F;
import org.apache.bcel.generic.CHECKCAST;
import org.apache.bcel.generic.CASTORE;
import org.apache.bcel.generic.CALOAD;
import org.apache.bcel.generic.BIPUSH;
import org.apache.bcel.generic.BASTORE;
import org.apache.bcel.generic.BALOAD;
import org.apache.bcel.generic.ATHROW;
import org.apache.bcel.generic.ASTORE;
import org.apache.bcel.generic.ARRAYLENGTH;
import org.apache.bcel.generic.ARETURN;
import org.apache.bcel.generic.ANEWARRAY;
import org.apache.bcel.generic.ALOAD;
import org.apache.bcel.generic.ACONST_NULL;
import org.apache.bcel.generic.AASTORE;
import org.apache.bcel.generic.ArrayType;
import org.apache.bcel.generic.Type;
import org.apache.bcel.generic.AALOAD;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.Visitor;
import org.apache.bcel.generic.EmptyVisitor;

public class ExecutionVisitor extends EmptyVisitor implements Visitor
{
    private Frame frame;
    private ConstantPoolGen cpg;
    
    public ExecutionVisitor() {
        this.frame = null;
        this.cpg = null;
    }
    
    private OperandStack stack() {
        return this.frame.getStack();
    }
    
    private LocalVariables locals() {
        return this.frame.getLocals();
    }
    
    public void setConstantPoolGen(final ConstantPoolGen cpg) {
        this.cpg = cpg;
    }
    
    public void setFrame(final Frame f) {
        this.frame = f;
    }
    
    public void visitAALOAD(final AALOAD o) {
        this.stack().pop();
        final Type t = this.stack().pop();
        if (t == Type.NULL) {
            this.stack().push(Type.NULL);
        }
        else {
            final ArrayType at = (ArrayType)t;
            this.stack().push(at.getElementType());
        }
    }
    
    public void visitAASTORE(final AASTORE o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().pop();
    }
    
    public void visitACONST_NULL(final ACONST_NULL o) {
        this.stack().push(Type.NULL);
    }
    
    public void visitALOAD(final ALOAD o) {
        this.stack().push(this.locals().get(o.getIndex()));
    }
    
    public void visitANEWARRAY(final ANEWARRAY o) {
        this.stack().pop();
        this.stack().push(new ArrayType(o.getType(this.cpg), 1));
    }
    
    public void visitARETURN(final ARETURN o) {
        this.stack().pop();
    }
    
    public void visitARRAYLENGTH(final ARRAYLENGTH o) {
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitASTORE(final ASTORE o) {
        this.locals().set(o.getIndex(), this.stack().pop());
    }
    
    public void visitATHROW(final ATHROW o) {
        final Type t = this.stack().pop();
        this.stack().clear();
        if (t.equals(Type.NULL)) {
            this.stack().push(Type.getType("Ljava/lang/NullPointerException;"));
        }
        else {
            this.stack().push(t);
        }
    }
    
    public void visitBALOAD(final BALOAD o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitBASTORE(final BASTORE o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().pop();
    }
    
    public void visitBIPUSH(final BIPUSH o) {
        this.stack().push(Type.INT);
    }
    
    public void visitCALOAD(final CALOAD o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitCASTORE(final CASTORE o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().pop();
    }
    
    public void visitCHECKCAST(final CHECKCAST o) {
        this.stack().pop();
        this.stack().push(o.getType(this.cpg));
    }
    
    public void visitD2F(final D2F o) {
        this.stack().pop();
        this.stack().push(Type.FLOAT);
    }
    
    public void visitD2I(final D2I o) {
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitD2L(final D2L o) {
        this.stack().pop();
        this.stack().push(Type.LONG);
    }
    
    public void visitDADD(final DADD o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.DOUBLE);
    }
    
    public void visitDALOAD(final DALOAD o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.DOUBLE);
    }
    
    public void visitDASTORE(final DASTORE o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().pop();
    }
    
    public void visitDCMPG(final DCMPG o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitDCMPL(final DCMPL o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitDCONST(final DCONST o) {
        this.stack().push(Type.DOUBLE);
    }
    
    public void visitDDIV(final DDIV o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.DOUBLE);
    }
    
    public void visitDLOAD(final DLOAD o) {
        this.stack().push(Type.DOUBLE);
    }
    
    public void visitDMUL(final DMUL o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.DOUBLE);
    }
    
    public void visitDNEG(final DNEG o) {
        this.stack().pop();
        this.stack().push(Type.DOUBLE);
    }
    
    public void visitDREM(final DREM o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.DOUBLE);
    }
    
    public void visitDRETURN(final DRETURN o) {
        this.stack().pop();
    }
    
    public void visitDSTORE(final DSTORE o) {
        this.locals().set(o.getIndex(), this.stack().pop());
        this.locals().set(o.getIndex() + 1, Type.UNKNOWN);
    }
    
    public void visitDSUB(final DSUB o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.DOUBLE);
    }
    
    public void visitDUP(final DUP o) {
        final Type t = this.stack().pop();
        this.stack().push(t);
        this.stack().push(t);
    }
    
    public void visitDUP_X1(final DUP_X1 o) {
        final Type w1 = this.stack().pop();
        final Type w2 = this.stack().pop();
        this.stack().push(w1);
        this.stack().push(w2);
        this.stack().push(w1);
    }
    
    public void visitDUP_X2(final DUP_X2 o) {
        final Type w1 = this.stack().pop();
        final Type w2 = this.stack().pop();
        if (w2.getSize() == 2) {
            this.stack().push(w1);
            this.stack().push(w2);
            this.stack().push(w1);
        }
        else {
            final Type w3 = this.stack().pop();
            this.stack().push(w1);
            this.stack().push(w3);
            this.stack().push(w2);
            this.stack().push(w1);
        }
    }
    
    public void visitDUP2(final DUP2 o) {
        final Type t = this.stack().pop();
        if (t.getSize() == 2) {
            this.stack().push(t);
            this.stack().push(t);
        }
        else {
            final Type u = this.stack().pop();
            this.stack().push(u);
            this.stack().push(t);
            this.stack().push(u);
            this.stack().push(t);
        }
    }
    
    public void visitDUP2_X1(final DUP2_X1 o) {
        final Type t = this.stack().pop();
        if (t.getSize() == 2) {
            final Type u = this.stack().pop();
            this.stack().push(t);
            this.stack().push(u);
            this.stack().push(t);
        }
        else {
            final Type u = this.stack().pop();
            final Type v = this.stack().pop();
            this.stack().push(u);
            this.stack().push(t);
            this.stack().push(v);
            this.stack().push(u);
            this.stack().push(t);
        }
    }
    
    public void visitDUP2_X2(final DUP2_X2 o) {
        final Type t = this.stack().pop();
        if (t.getSize() == 2) {
            final Type u = this.stack().pop();
            if (u.getSize() == 2) {
                this.stack().push(t);
                this.stack().push(u);
                this.stack().push(t);
            }
            else {
                final Type v = this.stack().pop();
                this.stack().push(t);
                this.stack().push(v);
                this.stack().push(u);
                this.stack().push(t);
            }
        }
        else {
            final Type u = this.stack().pop();
            final Type v = this.stack().pop();
            if (v.getSize() == 2) {
                this.stack().push(u);
                this.stack().push(t);
                this.stack().push(v);
                this.stack().push(u);
                this.stack().push(t);
            }
            else {
                final Type w = this.stack().pop();
                this.stack().push(u);
                this.stack().push(t);
                this.stack().push(w);
                this.stack().push(v);
                this.stack().push(u);
                this.stack().push(t);
            }
        }
    }
    
    public void visitF2D(final F2D o) {
        this.stack().pop();
        this.stack().push(Type.DOUBLE);
    }
    
    public void visitF2I(final F2I o) {
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitF2L(final F2L o) {
        this.stack().pop();
        this.stack().push(Type.LONG);
    }
    
    public void visitFADD(final FADD o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.FLOAT);
    }
    
    public void visitFALOAD(final FALOAD o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.FLOAT);
    }
    
    public void visitFASTORE(final FASTORE o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().pop();
    }
    
    public void visitFCMPG(final FCMPG o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitFCMPL(final FCMPL o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitFCONST(final FCONST o) {
        this.stack().push(Type.FLOAT);
    }
    
    public void visitFDIV(final FDIV o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.FLOAT);
    }
    
    public void visitFLOAD(final FLOAD o) {
        this.stack().push(Type.FLOAT);
    }
    
    public void visitFMUL(final FMUL o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.FLOAT);
    }
    
    public void visitFNEG(final FNEG o) {
        this.stack().pop();
        this.stack().push(Type.FLOAT);
    }
    
    public void visitFREM(final FREM o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.FLOAT);
    }
    
    public void visitFRETURN(final FRETURN o) {
        this.stack().pop();
    }
    
    public void visitFSTORE(final FSTORE o) {
        this.locals().set(o.getIndex(), this.stack().pop());
    }
    
    public void visitFSUB(final FSUB o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.FLOAT);
    }
    
    public void visitGETFIELD(final GETFIELD o) {
        this.stack().pop();
        Type t = o.getFieldType(this.cpg);
        if (t.equals(Type.BOOLEAN) || t.equals(Type.CHAR) || t.equals(Type.BYTE) || t.equals(Type.SHORT)) {
            t = Type.INT;
        }
        this.stack().push(t);
    }
    
    public void visitGETSTATIC(final GETSTATIC o) {
        Type t = o.getFieldType(this.cpg);
        if (t.equals(Type.BOOLEAN) || t.equals(Type.CHAR) || t.equals(Type.BYTE) || t.equals(Type.SHORT)) {
            t = Type.INT;
        }
        this.stack().push(t);
    }
    
    public void visitGOTO(final GOTO o) {
    }
    
    public void visitGOTO_W(final GOTO_W o) {
    }
    
    public void visitI2B(final I2B o) {
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitI2C(final I2C o) {
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitI2D(final I2D o) {
        this.stack().pop();
        this.stack().push(Type.DOUBLE);
    }
    
    public void visitI2F(final I2F o) {
        this.stack().pop();
        this.stack().push(Type.FLOAT);
    }
    
    public void visitI2L(final I2L o) {
        this.stack().pop();
        this.stack().push(Type.LONG);
    }
    
    public void visitI2S(final I2S o) {
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitIADD(final IADD o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitIALOAD(final IALOAD o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitIAND(final IAND o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitIASTORE(final IASTORE o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().pop();
    }
    
    public void visitICONST(final ICONST o) {
        this.stack().push(Type.INT);
    }
    
    public void visitIDIV(final IDIV o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitIF_ACMPEQ(final IF_ACMPEQ o) {
        this.stack().pop();
        this.stack().pop();
    }
    
    public void visitIF_ACMPNE(final IF_ACMPNE o) {
        this.stack().pop();
        this.stack().pop();
    }
    
    public void visitIF_ICMPEQ(final IF_ICMPEQ o) {
        this.stack().pop();
        this.stack().pop();
    }
    
    public void visitIF_ICMPGE(final IF_ICMPGE o) {
        this.stack().pop();
        this.stack().pop();
    }
    
    public void visitIF_ICMPGT(final IF_ICMPGT o) {
        this.stack().pop();
        this.stack().pop();
    }
    
    public void visitIF_ICMPLE(final IF_ICMPLE o) {
        this.stack().pop();
        this.stack().pop();
    }
    
    public void visitIF_ICMPLT(final IF_ICMPLT o) {
        this.stack().pop();
        this.stack().pop();
    }
    
    public void visitIF_ICMPNE(final IF_ICMPNE o) {
        this.stack().pop();
        this.stack().pop();
    }
    
    public void visitIFEQ(final IFEQ o) {
        this.stack().pop();
    }
    
    public void visitIFGE(final IFGE o) {
        this.stack().pop();
    }
    
    public void visitIFGT(final IFGT o) {
        this.stack().pop();
    }
    
    public void visitIFLE(final IFLE o) {
        this.stack().pop();
    }
    
    public void visitIFLT(final IFLT o) {
        this.stack().pop();
    }
    
    public void visitIFNE(final IFNE o) {
        this.stack().pop();
    }
    
    public void visitIFNONNULL(final IFNONNULL o) {
        this.stack().pop();
    }
    
    public void visitIFNULL(final IFNULL o) {
        this.stack().pop();
    }
    
    public void visitIINC(final IINC o) {
    }
    
    public void visitILOAD(final ILOAD o) {
        this.stack().push(Type.INT);
    }
    
    public void visitIMUL(final IMUL o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitINEG(final INEG o) {
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitINSTANCEOF(final INSTANCEOF o) {
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitINVOKEINTERFACE(final INVOKEINTERFACE o) {
        this.stack().pop();
        for (int i = 0; i < o.getArgumentTypes(this.cpg).length; ++i) {
            this.stack().pop();
        }
        if (o.getReturnType(this.cpg) != Type.VOID) {
            Type t = o.getReturnType(this.cpg);
            if (t.equals(Type.BOOLEAN) || t.equals(Type.CHAR) || t.equals(Type.BYTE) || t.equals(Type.SHORT)) {
                t = Type.INT;
            }
            this.stack().push(t);
        }
    }
    
    public void visitINVOKESPECIAL(final INVOKESPECIAL o) {
        if (o.getMethodName(this.cpg).equals("<init>")) {
            final UninitializedObjectType t = (UninitializedObjectType)this.stack().peek(o.getArgumentTypes(this.cpg).length);
            if (t == Frame._this) {
                Frame._this = null;
            }
            this.stack().initializeObject(t);
            this.locals().initializeObject(t);
        }
        this.stack().pop();
        for (int i = 0; i < o.getArgumentTypes(this.cpg).length; ++i) {
            this.stack().pop();
        }
        if (o.getReturnType(this.cpg) != Type.VOID) {
            Type t2 = o.getReturnType(this.cpg);
            if (t2.equals(Type.BOOLEAN) || t2.equals(Type.CHAR) || t2.equals(Type.BYTE) || t2.equals(Type.SHORT)) {
                t2 = Type.INT;
            }
            this.stack().push(t2);
        }
    }
    
    public void visitINVOKESTATIC(final INVOKESTATIC o) {
        for (int i = 0; i < o.getArgumentTypes(this.cpg).length; ++i) {
            this.stack().pop();
        }
        if (o.getReturnType(this.cpg) != Type.VOID) {
            Type t = o.getReturnType(this.cpg);
            if (t.equals(Type.BOOLEAN) || t.equals(Type.CHAR) || t.equals(Type.BYTE) || t.equals(Type.SHORT)) {
                t = Type.INT;
            }
            this.stack().push(t);
        }
    }
    
    public void visitINVOKEVIRTUAL(final INVOKEVIRTUAL o) {
        this.stack().pop();
        for (int i = 0; i < o.getArgumentTypes(this.cpg).length; ++i) {
            this.stack().pop();
        }
        if (o.getReturnType(this.cpg) != Type.VOID) {
            Type t = o.getReturnType(this.cpg);
            if (t.equals(Type.BOOLEAN) || t.equals(Type.CHAR) || t.equals(Type.BYTE) || t.equals(Type.SHORT)) {
                t = Type.INT;
            }
            this.stack().push(t);
        }
    }
    
    public void visitIOR(final IOR o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitIREM(final IREM o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitIRETURN(final IRETURN o) {
        this.stack().pop();
    }
    
    public void visitISHL(final ISHL o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitISHR(final ISHR o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitISTORE(final ISTORE o) {
        this.locals().set(o.getIndex(), this.stack().pop());
    }
    
    public void visitISUB(final ISUB o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitIUSHR(final IUSHR o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitIXOR(final IXOR o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitJSR(final JSR o) {
        this.stack().push(new ReturnaddressType(o.physicalSuccessor()));
    }
    
    public void visitJSR_W(final JSR_W o) {
        this.stack().push(new ReturnaddressType(o.physicalSuccessor()));
    }
    
    public void visitL2D(final L2D o) {
        this.stack().pop();
        this.stack().push(Type.DOUBLE);
    }
    
    public void visitL2F(final L2F o) {
        this.stack().pop();
        this.stack().push(Type.FLOAT);
    }
    
    public void visitL2I(final L2I o) {
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitLADD(final LADD o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.LONG);
    }
    
    public void visitLALOAD(final LALOAD o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.LONG);
    }
    
    public void visitLAND(final LAND o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.LONG);
    }
    
    public void visitLASTORE(final LASTORE o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().pop();
    }
    
    public void visitLCMP(final LCMP o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitLCONST(final LCONST o) {
        this.stack().push(Type.LONG);
    }
    
    public void visitLDC(final LDC o) {
        final Constant c = this.cpg.getConstant(o.getIndex());
        if (c instanceof ConstantInteger) {
            this.stack().push(Type.INT);
        }
        if (c instanceof ConstantFloat) {
            this.stack().push(Type.FLOAT);
        }
        if (c instanceof ConstantString) {
            this.stack().push(Type.STRING);
        }
    }
    
    public void visitLDC_W(final LDC_W o) {
        final Constant c = this.cpg.getConstant(o.getIndex());
        if (c instanceof ConstantInteger) {
            this.stack().push(Type.INT);
        }
        if (c instanceof ConstantFloat) {
            this.stack().push(Type.FLOAT);
        }
        if (c instanceof ConstantString) {
            this.stack().push(Type.STRING);
        }
    }
    
    public void visitLDC2_W(final LDC2_W o) {
        final Constant c = this.cpg.getConstant(o.getIndex());
        if (c instanceof ConstantLong) {
            this.stack().push(Type.LONG);
        }
        if (c instanceof ConstantDouble) {
            this.stack().push(Type.DOUBLE);
        }
    }
    
    public void visitLDIV(final LDIV o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.LONG);
    }
    
    public void visitLLOAD(final LLOAD o) {
        this.stack().push(this.locals().get(o.getIndex()));
    }
    
    public void visitLMUL(final LMUL o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.LONG);
    }
    
    public void visitLNEG(final LNEG o) {
        this.stack().pop();
        this.stack().push(Type.LONG);
    }
    
    public void visitLOOKUPSWITCH(final LOOKUPSWITCH o) {
        this.stack().pop();
    }
    
    public void visitLOR(final LOR o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.LONG);
    }
    
    public void visitLREM(final LREM o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.LONG);
    }
    
    public void visitLRETURN(final LRETURN o) {
        this.stack().pop();
    }
    
    public void visitLSHL(final LSHL o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.LONG);
    }
    
    public void visitLSHR(final LSHR o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.LONG);
    }
    
    public void visitLSTORE(final LSTORE o) {
        this.locals().set(o.getIndex(), this.stack().pop());
        this.locals().set(o.getIndex() + 1, Type.UNKNOWN);
    }
    
    public void visitLSUB(final LSUB o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.LONG);
    }
    
    public void visitLUSHR(final LUSHR o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.LONG);
    }
    
    public void visitLXOR(final LXOR o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.LONG);
    }
    
    public void visitMONITORENTER(final MONITORENTER o) {
        this.stack().pop();
    }
    
    public void visitMONITOREXIT(final MONITOREXIT o) {
        this.stack().pop();
    }
    
    public void visitMULTIANEWARRAY(final MULTIANEWARRAY o) {
        for (int i = 0; i < o.getDimensions(); ++i) {
            this.stack().pop();
        }
        this.stack().push(o.getType(this.cpg));
    }
    
    public void visitNEW(final NEW o) {
        this.stack().push(new UninitializedObjectType((ObjectType)o.getType(this.cpg)));
    }
    
    public void visitNEWARRAY(final NEWARRAY o) {
        this.stack().pop();
        this.stack().push(o.getType());
    }
    
    public void visitNOP(final NOP o) {
    }
    
    public void visitPOP(final POP o) {
        this.stack().pop();
    }
    
    public void visitPOP2(final POP2 o) {
        final Type t = this.stack().pop();
        if (t.getSize() == 1) {
            this.stack().pop();
        }
    }
    
    public void visitPUTFIELD(final PUTFIELD o) {
        this.stack().pop();
        this.stack().pop();
    }
    
    public void visitPUTSTATIC(final PUTSTATIC o) {
        this.stack().pop();
    }
    
    public void visitRET(final RET o) {
    }
    
    public void visitRETURN(final RETURN o) {
    }
    
    public void visitSALOAD(final SALOAD o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().push(Type.INT);
    }
    
    public void visitSASTORE(final SASTORE o) {
        this.stack().pop();
        this.stack().pop();
        this.stack().pop();
    }
    
    public void visitSIPUSH(final SIPUSH o) {
        this.stack().push(Type.INT);
    }
    
    public void visitSWAP(final SWAP o) {
        final Type t = this.stack().pop();
        final Type u = this.stack().pop();
        this.stack().push(t);
        this.stack().push(u);
    }
    
    public void visitTABLESWITCH(final TABLESWITCH o) {
        this.stack().pop();
    }
}
