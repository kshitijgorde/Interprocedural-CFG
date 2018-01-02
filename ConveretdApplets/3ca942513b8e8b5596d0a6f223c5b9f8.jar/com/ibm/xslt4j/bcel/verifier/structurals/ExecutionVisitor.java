// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.verifier.structurals;

import com.ibm.xslt4j.bcel.generic.TABLESWITCH;
import com.ibm.xslt4j.bcel.generic.SWAP;
import com.ibm.xslt4j.bcel.generic.SIPUSH;
import com.ibm.xslt4j.bcel.generic.SASTORE;
import com.ibm.xslt4j.bcel.generic.SALOAD;
import com.ibm.xslt4j.bcel.generic.RETURN;
import com.ibm.xslt4j.bcel.generic.RET;
import com.ibm.xslt4j.bcel.generic.PUTSTATIC;
import com.ibm.xslt4j.bcel.generic.PUTFIELD;
import com.ibm.xslt4j.bcel.generic.POP2;
import com.ibm.xslt4j.bcel.generic.POP;
import com.ibm.xslt4j.bcel.generic.NOP;
import com.ibm.xslt4j.bcel.generic.NEWARRAY;
import com.ibm.xslt4j.bcel.generic.ObjectType;
import com.ibm.xslt4j.bcel.generic.NEW;
import com.ibm.xslt4j.bcel.generic.MULTIANEWARRAY;
import com.ibm.xslt4j.bcel.generic.MONITOREXIT;
import com.ibm.xslt4j.bcel.generic.MONITORENTER;
import com.ibm.xslt4j.bcel.generic.LXOR;
import com.ibm.xslt4j.bcel.generic.LUSHR;
import com.ibm.xslt4j.bcel.generic.LSUB;
import com.ibm.xslt4j.bcel.generic.LSTORE;
import com.ibm.xslt4j.bcel.generic.LSHR;
import com.ibm.xslt4j.bcel.generic.LSHL;
import com.ibm.xslt4j.bcel.generic.LRETURN;
import com.ibm.xslt4j.bcel.generic.LREM;
import com.ibm.xslt4j.bcel.generic.LOR;
import com.ibm.xslt4j.bcel.generic.LOOKUPSWITCH;
import com.ibm.xslt4j.bcel.generic.LNEG;
import com.ibm.xslt4j.bcel.generic.LMUL;
import com.ibm.xslt4j.bcel.generic.LLOAD;
import com.ibm.xslt4j.bcel.generic.LDIV;
import com.ibm.xslt4j.bcel.classfile.ConstantDouble;
import com.ibm.xslt4j.bcel.classfile.ConstantLong;
import com.ibm.xslt4j.bcel.generic.LDC2_W;
import com.ibm.xslt4j.bcel.generic.LDC_W;
import com.ibm.xslt4j.bcel.classfile.Constant;
import com.ibm.xslt4j.bcel.classfile.ConstantString;
import com.ibm.xslt4j.bcel.classfile.ConstantFloat;
import com.ibm.xslt4j.bcel.classfile.ConstantInteger;
import com.ibm.xslt4j.bcel.generic.LDC;
import com.ibm.xslt4j.bcel.generic.LCONST;
import com.ibm.xslt4j.bcel.generic.LCMP;
import com.ibm.xslt4j.bcel.generic.LASTORE;
import com.ibm.xslt4j.bcel.generic.LAND;
import com.ibm.xslt4j.bcel.generic.LALOAD;
import com.ibm.xslt4j.bcel.generic.LADD;
import com.ibm.xslt4j.bcel.generic.L2I;
import com.ibm.xslt4j.bcel.generic.L2F;
import com.ibm.xslt4j.bcel.generic.L2D;
import com.ibm.xslt4j.bcel.generic.JSR_W;
import com.ibm.xslt4j.bcel.generic.ReturnaddressType;
import com.ibm.xslt4j.bcel.generic.JSR;
import com.ibm.xslt4j.bcel.generic.IXOR;
import com.ibm.xslt4j.bcel.generic.IUSHR;
import com.ibm.xslt4j.bcel.generic.ISUB;
import com.ibm.xslt4j.bcel.generic.ISTORE;
import com.ibm.xslt4j.bcel.generic.ISHR;
import com.ibm.xslt4j.bcel.generic.ISHL;
import com.ibm.xslt4j.bcel.generic.IRETURN;
import com.ibm.xslt4j.bcel.generic.IREM;
import com.ibm.xslt4j.bcel.generic.IOR;
import com.ibm.xslt4j.bcel.generic.INVOKEVIRTUAL;
import com.ibm.xslt4j.bcel.generic.INVOKESTATIC;
import com.ibm.xslt4j.bcel.generic.INVOKESPECIAL;
import com.ibm.xslt4j.bcel.generic.INVOKEINTERFACE;
import com.ibm.xslt4j.bcel.generic.INSTANCEOF;
import com.ibm.xslt4j.bcel.generic.INEG;
import com.ibm.xslt4j.bcel.generic.IMUL;
import com.ibm.xslt4j.bcel.generic.ILOAD;
import com.ibm.xslt4j.bcel.generic.IINC;
import com.ibm.xslt4j.bcel.generic.IFNULL;
import com.ibm.xslt4j.bcel.generic.IFNONNULL;
import com.ibm.xslt4j.bcel.generic.IFNE;
import com.ibm.xslt4j.bcel.generic.IFLT;
import com.ibm.xslt4j.bcel.generic.IFLE;
import com.ibm.xslt4j.bcel.generic.IFGT;
import com.ibm.xslt4j.bcel.generic.IFGE;
import com.ibm.xslt4j.bcel.generic.IFEQ;
import com.ibm.xslt4j.bcel.generic.IF_ICMPNE;
import com.ibm.xslt4j.bcel.generic.IF_ICMPLT;
import com.ibm.xslt4j.bcel.generic.IF_ICMPLE;
import com.ibm.xslt4j.bcel.generic.IF_ICMPGT;
import com.ibm.xslt4j.bcel.generic.IF_ICMPGE;
import com.ibm.xslt4j.bcel.generic.IF_ICMPEQ;
import com.ibm.xslt4j.bcel.generic.IF_ACMPNE;
import com.ibm.xslt4j.bcel.generic.IF_ACMPEQ;
import com.ibm.xslt4j.bcel.generic.IDIV;
import com.ibm.xslt4j.bcel.generic.ICONST;
import com.ibm.xslt4j.bcel.generic.IASTORE;
import com.ibm.xslt4j.bcel.generic.IAND;
import com.ibm.xslt4j.bcel.generic.IALOAD;
import com.ibm.xslt4j.bcel.generic.IADD;
import com.ibm.xslt4j.bcel.generic.I2S;
import com.ibm.xslt4j.bcel.generic.I2L;
import com.ibm.xslt4j.bcel.generic.I2F;
import com.ibm.xslt4j.bcel.generic.I2D;
import com.ibm.xslt4j.bcel.generic.I2C;
import com.ibm.xslt4j.bcel.generic.I2B;
import com.ibm.xslt4j.bcel.generic.GOTO_W;
import com.ibm.xslt4j.bcel.generic.GOTO;
import com.ibm.xslt4j.bcel.generic.GETSTATIC;
import com.ibm.xslt4j.bcel.generic.GETFIELD;
import com.ibm.xslt4j.bcel.generic.FSUB;
import com.ibm.xslt4j.bcel.generic.FSTORE;
import com.ibm.xslt4j.bcel.generic.FRETURN;
import com.ibm.xslt4j.bcel.generic.FREM;
import com.ibm.xslt4j.bcel.generic.FNEG;
import com.ibm.xslt4j.bcel.generic.FMUL;
import com.ibm.xslt4j.bcel.generic.FLOAD;
import com.ibm.xslt4j.bcel.generic.FDIV;
import com.ibm.xslt4j.bcel.generic.FCONST;
import com.ibm.xslt4j.bcel.generic.FCMPL;
import com.ibm.xslt4j.bcel.generic.FCMPG;
import com.ibm.xslt4j.bcel.generic.FASTORE;
import com.ibm.xslt4j.bcel.generic.FALOAD;
import com.ibm.xslt4j.bcel.generic.FADD;
import com.ibm.xslt4j.bcel.generic.F2L;
import com.ibm.xslt4j.bcel.generic.F2I;
import com.ibm.xslt4j.bcel.generic.F2D;
import com.ibm.xslt4j.bcel.generic.DUP2_X2;
import com.ibm.xslt4j.bcel.generic.DUP2_X1;
import com.ibm.xslt4j.bcel.generic.DUP2;
import com.ibm.xslt4j.bcel.generic.DUP_X2;
import com.ibm.xslt4j.bcel.generic.DUP_X1;
import com.ibm.xslt4j.bcel.generic.DUP;
import com.ibm.xslt4j.bcel.generic.DSUB;
import com.ibm.xslt4j.bcel.generic.DSTORE;
import com.ibm.xslt4j.bcel.generic.DRETURN;
import com.ibm.xslt4j.bcel.generic.DREM;
import com.ibm.xslt4j.bcel.generic.DNEG;
import com.ibm.xslt4j.bcel.generic.DMUL;
import com.ibm.xslt4j.bcel.generic.DLOAD;
import com.ibm.xslt4j.bcel.generic.DDIV;
import com.ibm.xslt4j.bcel.generic.DCONST;
import com.ibm.xslt4j.bcel.generic.DCMPL;
import com.ibm.xslt4j.bcel.generic.DCMPG;
import com.ibm.xslt4j.bcel.generic.DASTORE;
import com.ibm.xslt4j.bcel.generic.DALOAD;
import com.ibm.xslt4j.bcel.generic.DADD;
import com.ibm.xslt4j.bcel.generic.D2L;
import com.ibm.xslt4j.bcel.generic.D2I;
import com.ibm.xslt4j.bcel.generic.D2F;
import com.ibm.xslt4j.bcel.generic.CHECKCAST;
import com.ibm.xslt4j.bcel.generic.CASTORE;
import com.ibm.xslt4j.bcel.generic.CALOAD;
import com.ibm.xslt4j.bcel.generic.BIPUSH;
import com.ibm.xslt4j.bcel.generic.BASTORE;
import com.ibm.xslt4j.bcel.generic.BALOAD;
import com.ibm.xslt4j.bcel.generic.ATHROW;
import com.ibm.xslt4j.bcel.generic.ASTORE;
import com.ibm.xslt4j.bcel.generic.ARRAYLENGTH;
import com.ibm.xslt4j.bcel.generic.ARETURN;
import com.ibm.xslt4j.bcel.generic.ANEWARRAY;
import com.ibm.xslt4j.bcel.generic.ALOAD;
import com.ibm.xslt4j.bcel.generic.ACONST_NULL;
import com.ibm.xslt4j.bcel.generic.AASTORE;
import com.ibm.xslt4j.bcel.generic.ArrayType;
import com.ibm.xslt4j.bcel.generic.Type;
import com.ibm.xslt4j.bcel.generic.AALOAD;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.Visitor;
import com.ibm.xslt4j.bcel.generic.EmptyVisitor;

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
