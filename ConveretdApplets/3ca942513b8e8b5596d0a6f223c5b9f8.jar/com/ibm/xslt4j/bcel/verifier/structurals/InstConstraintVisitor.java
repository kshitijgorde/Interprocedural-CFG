// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.verifier.structurals;

import com.ibm.xslt4j.bcel.generic.TABLESWITCH;
import com.ibm.xslt4j.bcel.generic.SWAP;
import com.ibm.xslt4j.bcel.generic.SIPUSH;
import com.ibm.xslt4j.bcel.generic.SASTORE;
import com.ibm.xslt4j.bcel.generic.SALOAD;
import com.ibm.xslt4j.bcel.generic.RET;
import com.ibm.xslt4j.bcel.generic.PUTSTATIC;
import com.ibm.xslt4j.bcel.generic.PUTFIELD;
import com.ibm.xslt4j.bcel.generic.POP2;
import com.ibm.xslt4j.bcel.generic.POP;
import com.ibm.xslt4j.bcel.generic.NOP;
import com.ibm.xslt4j.bcel.generic.NEWARRAY;
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
import com.ibm.xslt4j.bcel.classfile.ConstantInterfaceMethodref;
import com.ibm.xslt4j.bcel.generic.INVOKEINTERFACE;
import com.ibm.xslt4j.bcel.generic.INSTANCEOF;
import com.ibm.xslt4j.bcel.generic.INEG;
import com.ibm.xslt4j.bcel.generic.IMUL;
import com.ibm.xslt4j.bcel.generic.IMPDEP2;
import com.ibm.xslt4j.bcel.generic.IMPDEP1;
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
import com.ibm.xslt4j.bcel.classfile.Field;
import com.ibm.xslt4j.bcel.classfile.JavaClass;
import com.ibm.xslt4j.bcel.Repository;
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
import com.ibm.xslt4j.bcel.classfile.ConstantClass;
import com.ibm.xslt4j.bcel.generic.CHECKCAST;
import com.ibm.xslt4j.bcel.generic.CASTORE;
import com.ibm.xslt4j.bcel.generic.CALOAD;
import com.ibm.xslt4j.bcel.generic.BREAKPOINT;
import com.ibm.xslt4j.bcel.generic.BIPUSH;
import com.ibm.xslt4j.bcel.generic.BASTORE;
import com.ibm.xslt4j.bcel.generic.BALOAD;
import com.ibm.xslt4j.bcel.generic.ATHROW;
import com.ibm.xslt4j.bcel.generic.ARRAYLENGTH;
import com.ibm.xslt4j.bcel.generic.ANEWARRAY;
import com.ibm.xslt4j.bcel.generic.ACONST_NULL;
import com.ibm.xslt4j.bcel.generic.AASTORE;
import com.ibm.xslt4j.bcel.generic.AALOAD;
import com.ibm.xslt4j.bcel.generic.ARETURN;
import com.ibm.xslt4j.bcel.generic.RETURN;
import com.ibm.xslt4j.bcel.generic.ReturnInstruction;
import com.ibm.xslt4j.bcel.generic.ReturnaddressType;
import com.ibm.xslt4j.bcel.generic.ASTORE;
import com.ibm.xslt4j.bcel.generic.StoreInstruction;
import com.ibm.xslt4j.bcel.generic.ALOAD;
import com.ibm.xslt4j.bcel.generic.LoadInstruction;
import com.ibm.xslt4j.bcel.generic.LocalVariableInstruction;
import com.ibm.xslt4j.bcel.generic.StackInstruction;
import com.ibm.xslt4j.bcel.generic.InvokeInstruction;
import com.ibm.xslt4j.bcel.classfile.Constant;
import com.ibm.xslt4j.bcel.classfile.ConstantFieldref;
import com.ibm.xslt4j.bcel.generic.FieldInstruction;
import com.ibm.xslt4j.bcel.verifier.exc.AssertionViolatedException;
import com.ibm.xslt4j.bcel.generic.CPInstruction;
import com.ibm.xslt4j.bcel.generic.StackProducer;
import com.ibm.xslt4j.bcel.generic.StackConsumer;
import com.ibm.xslt4j.bcel.verifier.VerificationResult;
import com.ibm.xslt4j.bcel.verifier.Verifier;
import com.ibm.xslt4j.bcel.verifier.VerifierFactory;
import com.ibm.xslt4j.bcel.generic.LoadClass;
import com.ibm.xslt4j.bcel.generic.ArrayType;
import com.ibm.xslt4j.bcel.generic.ReferenceType;
import com.ibm.xslt4j.bcel.generic.Type;
import com.ibm.xslt4j.bcel.verifier.exc.StructuralCodeConstraintException;
import com.ibm.xslt4j.bcel.generic.Instruction;
import com.ibm.xslt4j.bcel.generic.MethodGen;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.ObjectType;
import com.ibm.xslt4j.bcel.generic.Visitor;
import com.ibm.xslt4j.bcel.generic.EmptyVisitor;

public class InstConstraintVisitor extends EmptyVisitor implements Visitor
{
    private static ObjectType GENERIC_ARRAY;
    private Frame frame;
    private ConstantPoolGen cpg;
    private MethodGen mg;
    
    static {
        InstConstraintVisitor.GENERIC_ARRAY = new ObjectType("com.ibm.xslt4j.bcel.verifier.structurals.GenericArray");
    }
    
    public InstConstraintVisitor() {
        this.frame = null;
        this.cpg = null;
        this.mg = null;
    }
    
    private OperandStack stack() {
        return this.frame.getStack();
    }
    
    private LocalVariables locals() {
        return this.frame.getLocals();
    }
    
    private void constraintViolated(final Instruction violator, final String description) {
        final String fq_classname = violator.getClass().getName();
        throw new StructuralCodeConstraintException("Instruction " + fq_classname.substring(fq_classname.lastIndexOf(46) + 1) + " constraint violated: " + description);
    }
    
    public void setFrame(final Frame f) {
        this.frame = f;
    }
    
    public void setConstantPoolGen(final ConstantPoolGen cpg) {
        this.cpg = cpg;
    }
    
    public void setMethodGen(final MethodGen mg) {
        this.mg = mg;
    }
    
    private void indexOfInt(final Instruction o, final Type index) {
        if (!index.equals(Type.INT)) {
            this.constraintViolated(o, "The 'index' is not of type int but of type " + index + ".");
        }
    }
    
    private void referenceTypeIsInitialized(final Instruction o, final ReferenceType r) {
        if (r instanceof UninitializedObjectType) {
            this.constraintViolated(o, "Working on an uninitialized object '" + r + "'.");
        }
    }
    
    private void valueOfInt(final Instruction o, final Type value) {
        if (!value.equals(Type.INT)) {
            this.constraintViolated(o, "The 'value' is not of type int but of type " + value + ".");
        }
    }
    
    private boolean arrayrefOfArrayType(final Instruction o, final Type arrayref) {
        if (!(arrayref instanceof ArrayType) && !arrayref.equals(Type.NULL)) {
            this.constraintViolated(o, "The 'arrayref' does not refer to an array but is of type " + arrayref + ".");
        }
        return arrayref instanceof ArrayType;
    }
    
    private void _visitStackAccessor(final Instruction o) {
        final int consume = o.consumeStack(this.cpg);
        if (consume > this.stack().slotsUsed()) {
            this.constraintViolated(o, "Cannot consume " + consume + " stack slots: only " + this.stack().slotsUsed() + " slot(s) left on stack!\nStack:\n" + this.stack());
        }
        final int produce = o.produceStack(this.cpg) - o.consumeStack(this.cpg);
        if (produce + this.stack().slotsUsed() > this.stack().maxStack()) {
            this.constraintViolated(o, "Cannot produce " + produce + " stack slots: only " + (this.stack().maxStack() - this.stack().slotsUsed()) + " free stack slot(s) left.\nStack:\n" + this.stack());
        }
    }
    
    public void visitLoadClass(final LoadClass o) {
        final ObjectType t = o.getLoadClassType(this.cpg);
        if (t != null) {
            final Verifier v = VerifierFactory.getVerifier(t.getClassName());
            final VerificationResult vr = v.doPass2();
            if (vr.getStatus() != 1) {
                this.constraintViolated((Instruction)o, "Class '" + o.getLoadClassType(this.cpg).getClassName() + "' is referenced, but cannot be loaded and resolved: '" + vr + "'.");
            }
        }
    }
    
    public void visitStackConsumer(final StackConsumer o) {
        this._visitStackAccessor((Instruction)o);
    }
    
    public void visitStackProducer(final StackProducer o) {
        this._visitStackAccessor((Instruction)o);
    }
    
    public void visitCPInstruction(final CPInstruction o) {
        final int idx = o.getIndex();
        if (idx < 0 || idx >= this.cpg.getSize()) {
            throw new AssertionViolatedException("Huh?! Constant pool index of instruction '" + o + "' illegal? Pass 3a should have checked this!");
        }
    }
    
    public void visitFieldInstruction(final FieldInstruction o) {
        final Constant c = this.cpg.getConstant(o.getIndex());
        if (!(c instanceof ConstantFieldref)) {
            this.constraintViolated(o, "Index '" + o.getIndex() + "' should refer to a CONSTANT_Fieldref_info structure, but refers to '" + c + "'.");
        }
        final Type t = o.getType(this.cpg);
        if (t instanceof ObjectType) {
            final String name = ((ObjectType)t).getClassName();
            final Verifier v = VerifierFactory.getVerifier(name);
            final VerificationResult vr = v.doPass2();
            if (vr.getStatus() != 1) {
                this.constraintViolated(o, "Class '" + name + "' is referenced, but cannot be loaded and resolved: '" + vr + "'.");
            }
        }
    }
    
    public void visitInvokeInstruction(final InvokeInstruction o) {
    }
    
    public void visitStackInstruction(final StackInstruction o) {
        this._visitStackAccessor(o);
    }
    
    public void visitLocalVariableInstruction(final LocalVariableInstruction o) {
        if (this.locals().maxLocals() <= ((o.getType(this.cpg).getSize() == 1) ? o.getIndex() : (o.getIndex() + 1))) {
            this.constraintViolated(o, "The 'index' is not a valid index into the local variable array.");
        }
    }
    
    public void visitLoadInstruction(final LoadInstruction o) {
        if (this.locals().get(o.getIndex()) == Type.UNKNOWN) {
            this.constraintViolated(o, "Read-Access on local variable " + o.getIndex() + " with unknown content.");
        }
        if (o.getType(this.cpg).getSize() == 2 && this.locals().get(o.getIndex() + 1) != Type.UNKNOWN) {
            this.constraintViolated(o, "Reading a two-locals value from local variables " + o.getIndex() + " and " + (o.getIndex() + 1) + " where the latter one is destroyed.");
        }
        if (!(o instanceof ALOAD)) {
            if (this.locals().get(o.getIndex()) != o.getType(this.cpg)) {
                this.constraintViolated(o, "Local Variable type and LOADing Instruction type mismatch: Local Variable: '" + this.locals().get(o.getIndex()) + "'; Instruction type: '" + o.getType(this.cpg) + "'.");
            }
        }
        else if (!(this.locals().get(o.getIndex()) instanceof ReferenceType)) {
            this.constraintViolated(o, "Local Variable type and LOADing Instruction type mismatch: Local Variable: '" + this.locals().get(o.getIndex()) + "'; Instruction expects a ReferenceType.");
        }
        if (this.stack().maxStack() - this.stack().slotsUsed() < o.getType(this.cpg).getSize()) {
            this.constraintViolated(o, "Not enough free stack slots to load a '" + o.getType(this.cpg) + "' onto the OperandStack.");
        }
    }
    
    public void visitStoreInstruction(final StoreInstruction o) {
        if (this.stack().isEmpty()) {
            this.constraintViolated(o, "Cannot STORE: Stack to read from is empty.");
        }
        if (!(o instanceof ASTORE)) {
            if (this.stack().peek() != o.getType(this.cpg)) {
                this.constraintViolated(o, "Stack top type and STOREing Instruction type mismatch: Stack top: '" + this.stack().peek() + "'; Instruction type: '" + o.getType(this.cpg) + "'.");
            }
        }
        else {
            final Type stacktop = this.stack().peek();
            if (!(stacktop instanceof ReferenceType) && !(stacktop instanceof ReturnaddressType)) {
                this.constraintViolated(o, "Stack top type and STOREing Instruction type mismatch: Stack top: '" + this.stack().peek() + "'; Instruction expects a ReferenceType or a ReturnadressType.");
            }
            if (stacktop instanceof ReferenceType) {
                this.referenceTypeIsInitialized(o, (ReferenceType)stacktop);
            }
        }
    }
    
    public void visitReturnInstruction(final ReturnInstruction o) {
        if (o instanceof RETURN) {
            return;
        }
        if (o instanceof ARETURN) {
            if (this.stack().peek() == Type.NULL) {
                return;
            }
            if (!(this.stack().peek() instanceof ReferenceType)) {
                this.constraintViolated(o, "Reference type expected on top of stack, but is: '" + this.stack().peek() + "'.");
            }
            this.referenceTypeIsInitialized(o, (ReferenceType)this.stack().peek());
            final ReferenceType objectref = (ReferenceType)this.stack().peek();
        }
        else {
            Type method_type = this.mg.getType();
            if (method_type == Type.BOOLEAN || method_type == Type.BYTE || method_type == Type.SHORT || method_type == Type.CHAR) {
                method_type = Type.INT;
            }
            if (!method_type.equals(this.stack().peek())) {
                this.constraintViolated(o, "Current method has return type of '" + this.mg.getType() + "' expecting a '" + method_type + "' on top of the stack. But stack top is a '" + this.stack().peek() + "'.");
            }
        }
    }
    
    public void visitAALOAD(final AALOAD o) {
        final Type arrayref = this.stack().peek(1);
        final Type index = this.stack().peek(0);
        this.indexOfInt(o, index);
        if (this.arrayrefOfArrayType(o, arrayref)) {
            if (!(((ArrayType)arrayref).getElementType() instanceof ReferenceType)) {
                this.constraintViolated(o, "The 'arrayref' does not refer to an array with elements of a ReferenceType but to an array of " + ((ArrayType)arrayref).getElementType() + ".");
            }
            this.referenceTypeIsInitialized(o, (ReferenceType)((ArrayType)arrayref).getElementType());
        }
    }
    
    public void visitAASTORE(final AASTORE o) {
        final Type arrayref = this.stack().peek(2);
        final Type index = this.stack().peek(1);
        final Type value = this.stack().peek(0);
        this.indexOfInt(o, index);
        if (!(value instanceof ReferenceType)) {
            this.constraintViolated(o, "The 'value' is not of a ReferenceType but of type " + value + ".");
        }
        else {
            this.referenceTypeIsInitialized(o, (ReferenceType)value);
        }
        if (this.arrayrefOfArrayType(o, arrayref)) {
            if (!(((ArrayType)arrayref).getElementType() instanceof ReferenceType)) {
                this.constraintViolated(o, "The 'arrayref' does not refer to an array with elements of a ReferenceType but to an array of " + ((ArrayType)arrayref).getElementType() + ".");
            }
            if (!((ReferenceType)value).isAssignmentCompatibleWith(((ArrayType)arrayref).getElementType())) {
                this.constraintViolated(o, "The type of 'value' ('" + value + "') is not assignment compatible to the components of the array 'arrayref' refers to. ('" + ((ArrayType)arrayref).getElementType() + "')");
            }
        }
    }
    
    public void visitACONST_NULL(final ACONST_NULL o) {
    }
    
    public void visitALOAD(final ALOAD o) {
    }
    
    public void visitANEWARRAY(final ANEWARRAY o) {
        if (!this.stack().peek().equals(Type.INT)) {
            this.constraintViolated(o, "The 'count' at the stack top is not of type '" + Type.INT + "' but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitARETURN(final ARETURN o) {
        if (!(this.stack().peek() instanceof ReferenceType)) {
            this.constraintViolated(o, "The 'objectref' at the stack top is not of a ReferenceType but of type '" + this.stack().peek() + "'.");
        }
        final ReferenceType objectref = (ReferenceType)this.stack().peek();
        this.referenceTypeIsInitialized(o, objectref);
    }
    
    public void visitARRAYLENGTH(final ARRAYLENGTH o) {
        final Type arrayref = this.stack().peek(0);
        this.arrayrefOfArrayType(o, arrayref);
    }
    
    public void visitASTORE(final ASTORE o) {
        if (!(this.stack().peek() instanceof ReferenceType) && !(this.stack().peek() instanceof ReturnaddressType)) {
            this.constraintViolated(o, "The 'objectref' is not of a ReferenceType or of ReturnaddressType but of " + this.stack().peek() + ".");
        }
        if (this.stack().peek() instanceof ReferenceType) {
            this.referenceTypeIsInitialized(o, (ReferenceType)this.stack().peek());
        }
    }
    
    public void visitATHROW(final ATHROW o) {
        if (!(this.stack().peek() instanceof ObjectType) && !this.stack().peek().equals(Type.NULL)) {
            this.constraintViolated(o, "The 'objectref' is not of an (initialized) ObjectType but of type " + this.stack().peek() + ".");
        }
        if (this.stack().peek().equals(Type.NULL)) {
            return;
        }
        final ObjectType exc = (ObjectType)this.stack().peek();
        final ObjectType throwable = (ObjectType)Type.getType("Ljava/lang/Throwable;");
        if (!exc.subclassOf(throwable) && !exc.equals(throwable)) {
            this.constraintViolated(o, "The 'objectref' is not of class Throwable or of a subclass of Throwable, but of '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitBALOAD(final BALOAD o) {
        final Type arrayref = this.stack().peek(1);
        final Type index = this.stack().peek(0);
        this.indexOfInt(o, index);
        if (this.arrayrefOfArrayType(o, arrayref) && !((ArrayType)arrayref).getElementType().equals(Type.BOOLEAN) && !((ArrayType)arrayref).getElementType().equals(Type.BYTE)) {
            this.constraintViolated(o, "The 'arrayref' does not refer to an array with elements of a Type.BYTE or Type.BOOLEAN but to an array of '" + ((ArrayType)arrayref).getElementType() + "'.");
        }
    }
    
    public void visitBASTORE(final BASTORE o) {
        final Type arrayref = this.stack().peek(2);
        final Type index = this.stack().peek(1);
        final Type value = this.stack().peek(0);
        this.indexOfInt(o, index);
        this.valueOfInt(o, index);
        if (this.arrayrefOfArrayType(o, arrayref) && !((ArrayType)arrayref).getElementType().equals(Type.BOOLEAN) && !((ArrayType)arrayref).getElementType().equals(Type.BYTE)) {
            this.constraintViolated(o, "The 'arrayref' does not refer to an array with elements of a Type.BYTE or Type.BOOLEAN but to an array of '" + ((ArrayType)arrayref).getElementType() + "'.");
        }
    }
    
    public void visitBIPUSH(final BIPUSH o) {
    }
    
    public void visitBREAKPOINT(final BREAKPOINT o) {
        throw new AssertionViolatedException("In this JustIce verification pass there should not occur an illegal instruction such as BREAKPOINT.");
    }
    
    public void visitCALOAD(final CALOAD o) {
        final Type arrayref = this.stack().peek(1);
        final Type index = this.stack().peek(0);
        this.indexOfInt(o, index);
        this.arrayrefOfArrayType(o, arrayref);
    }
    
    public void visitCASTORE(final CASTORE o) {
        final Type arrayref = this.stack().peek(2);
        final Type index = this.stack().peek(1);
        final Type value = this.stack().peek(0);
        this.indexOfInt(o, index);
        this.valueOfInt(o, index);
        if (this.arrayrefOfArrayType(o, arrayref) && !((ArrayType)arrayref).getElementType().equals(Type.CHAR)) {
            this.constraintViolated(o, "The 'arrayref' does not refer to an array with elements of type char but to an array of type " + ((ArrayType)arrayref).getElementType() + ".");
        }
    }
    
    public void visitCHECKCAST(final CHECKCAST o) {
        final Type objectref = this.stack().peek(0);
        if (!(objectref instanceof ReferenceType)) {
            this.constraintViolated(o, "The 'objectref' is not of a ReferenceType but of type " + objectref + ".");
        }
        else {
            this.referenceTypeIsInitialized(o, (ReferenceType)objectref);
        }
        final Constant c = this.cpg.getConstant(o.getIndex());
        if (!(c instanceof ConstantClass)) {
            this.constraintViolated(o, "The Constant at 'index' is not a ConstantClass, but '" + c + "'.");
        }
    }
    
    public void visitD2F(final D2F o) {
        if (this.stack().peek() != Type.DOUBLE) {
            this.constraintViolated(o, "The value at the stack top is not of type 'double', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitD2I(final D2I o) {
        if (this.stack().peek() != Type.DOUBLE) {
            this.constraintViolated(o, "The value at the stack top is not of type 'double', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitD2L(final D2L o) {
        if (this.stack().peek() != Type.DOUBLE) {
            this.constraintViolated(o, "The value at the stack top is not of type 'double', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitDADD(final DADD o) {
        if (this.stack().peek() != Type.DOUBLE) {
            this.constraintViolated(o, "The value at the stack top is not of type 'double', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.DOUBLE) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'double', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitDALOAD(final DALOAD o) {
        this.indexOfInt(o, this.stack().peek());
        if (this.stack().peek(1) == Type.NULL) {
            return;
        }
        if (!(this.stack().peek(1) instanceof ArrayType)) {
            this.constraintViolated(o, "Stack next-to-top must be of type double[] but is '" + this.stack().peek(1) + "'.");
        }
        final Type t = ((ArrayType)this.stack().peek(1)).getBasicType();
        if (t != Type.DOUBLE) {
            this.constraintViolated(o, "Stack next-to-top must be of type double[] but is '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitDASTORE(final DASTORE o) {
        if (this.stack().peek() != Type.DOUBLE) {
            this.constraintViolated(o, "The value at the stack top is not of type 'double', but of type '" + this.stack().peek() + "'.");
        }
        this.indexOfInt(o, this.stack().peek(1));
        if (this.stack().peek(2) == Type.NULL) {
            return;
        }
        if (!(this.stack().peek(2) instanceof ArrayType)) {
            this.constraintViolated(o, "Stack next-to-next-to-top must be of type double[] but is '" + this.stack().peek(2) + "'.");
        }
        final Type t = ((ArrayType)this.stack().peek(2)).getBasicType();
        if (t != Type.DOUBLE) {
            this.constraintViolated(o, "Stack next-to-next-to-top must be of type double[] but is '" + this.stack().peek(2) + "'.");
        }
    }
    
    public void visitDCMPG(final DCMPG o) {
        if (this.stack().peek() != Type.DOUBLE) {
            this.constraintViolated(o, "The value at the stack top is not of type 'double', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.DOUBLE) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'double', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitDCMPL(final DCMPL o) {
        if (this.stack().peek() != Type.DOUBLE) {
            this.constraintViolated(o, "The value at the stack top is not of type 'double', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.DOUBLE) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'double', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitDCONST(final DCONST o) {
    }
    
    public void visitDDIV(final DDIV o) {
        if (this.stack().peek() != Type.DOUBLE) {
            this.constraintViolated(o, "The value at the stack top is not of type 'double', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.DOUBLE) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'double', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitDLOAD(final DLOAD o) {
    }
    
    public void visitDMUL(final DMUL o) {
        if (this.stack().peek() != Type.DOUBLE) {
            this.constraintViolated(o, "The value at the stack top is not of type 'double', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.DOUBLE) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'double', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitDNEG(final DNEG o) {
        if (this.stack().peek() != Type.DOUBLE) {
            this.constraintViolated(o, "The value at the stack top is not of type 'double', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitDREM(final DREM o) {
        if (this.stack().peek() != Type.DOUBLE) {
            this.constraintViolated(o, "The value at the stack top is not of type 'double', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.DOUBLE) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'double', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitDRETURN(final DRETURN o) {
        if (this.stack().peek() != Type.DOUBLE) {
            this.constraintViolated(o, "The value at the stack top is not of type 'double', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitDSTORE(final DSTORE o) {
    }
    
    public void visitDSUB(final DSUB o) {
        if (this.stack().peek() != Type.DOUBLE) {
            this.constraintViolated(o, "The value at the stack top is not of type 'double', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.DOUBLE) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'double', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitDUP(final DUP o) {
        if (this.stack().peek().getSize() != 1) {
            this.constraintViolated(o, "Won't DUP type on stack top '" + this.stack().peek() + "' because it must occupy exactly one slot, not '" + this.stack().peek().getSize() + "'.");
        }
    }
    
    public void visitDUP_X1(final DUP_X1 o) {
        if (this.stack().peek().getSize() != 1) {
            this.constraintViolated(o, "Type on stack top '" + this.stack().peek() + "' should occupy exactly one slot, not '" + this.stack().peek().getSize() + "'.");
        }
        if (this.stack().peek(1).getSize() != 1) {
            this.constraintViolated(o, "Type on stack next-to-top '" + this.stack().peek(1) + "' should occupy exactly one slot, not '" + this.stack().peek(1).getSize() + "'.");
        }
    }
    
    public void visitDUP_X2(final DUP_X2 o) {
        if (this.stack().peek().getSize() != 1) {
            this.constraintViolated(o, "Stack top type must be of size 1, but is '" + this.stack().peek() + "' of size '" + this.stack().peek().getSize() + "'.");
        }
        if (this.stack().peek(1).getSize() == 2) {
            return;
        }
        if (this.stack().peek(2).getSize() != 1) {
            this.constraintViolated(o, "If stack top's size is 1 and stack next-to-top's size is 1, stack next-to-next-to-top's size must also be 1, but is: '" + this.stack().peek(2) + "' of size '" + this.stack().peek(2).getSize() + "'.");
        }
    }
    
    public void visitDUP2(final DUP2 o) {
        if (this.stack().peek().getSize() == 2) {
            return;
        }
        if (this.stack().peek(1).getSize() != 1) {
            this.constraintViolated(o, "If stack top's size is 1, then stack next-to-top's size must also be 1. But it is '" + this.stack().peek(1) + "' of size '" + this.stack().peek(1).getSize() + "'.");
        }
    }
    
    public void visitDUP2_X1(final DUP2_X1 o) {
        if (this.stack().peek().getSize() == 2) {
            if (this.stack().peek(1).getSize() == 1) {
                return;
            }
            this.constraintViolated(o, "If stack top's size is 2, then stack next-to-top's size must be 1. But it is '" + this.stack().peek(1) + "' of size '" + this.stack().peek(1).getSize() + "'.");
        }
        else {
            if (this.stack().peek(1).getSize() != 1) {
                this.constraintViolated(o, "If stack top's size is 1, then stack next-to-top's size must also be 1. But it is '" + this.stack().peek(1) + "' of size '" + this.stack().peek(1).getSize() + "'.");
            }
            if (this.stack().peek(2).getSize() != 1) {
                this.constraintViolated(o, "If stack top's size is 1, then stack next-to-next-to-top's size must also be 1. But it is '" + this.stack().peek(2) + "' of size '" + this.stack().peek(2).getSize() + "'.");
            }
        }
    }
    
    public void visitDUP2_X2(final DUP2_X2 o) {
        if (this.stack().peek(0).getSize() == 2) {
            if (this.stack().peek(1).getSize() == 2) {
                return;
            }
            if (this.stack().peek(2).getSize() == 1) {
                return;
            }
            this.constraintViolated(o, "If stack top's size is 2 and stack-next-to-top's size is 1, then stack next-to-next-to-top's size must also be 1. But it is '" + this.stack().peek(2) + "' of size '" + this.stack().peek(2).getSize() + "'.");
        }
        else if (this.stack().peek(1).getSize() == 1) {
            if (this.stack().peek(2).getSize() == 2) {
                return;
            }
            if (this.stack().peek(3).getSize() == 1) {
                return;
            }
        }
        this.constraintViolated(o, "The operand sizes on the stack do not match any of the four forms of usage of this instruction.");
    }
    
    public void visitF2D(final F2D o) {
        if (this.stack().peek() != Type.FLOAT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'float', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitF2I(final F2I o) {
        if (this.stack().peek() != Type.FLOAT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'float', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitF2L(final F2L o) {
        if (this.stack().peek() != Type.FLOAT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'float', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitFADD(final FADD o) {
        if (this.stack().peek() != Type.FLOAT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'float', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.FLOAT) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'float', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitFALOAD(final FALOAD o) {
        this.indexOfInt(o, this.stack().peek());
        if (this.stack().peek(1) == Type.NULL) {
            return;
        }
        if (!(this.stack().peek(1) instanceof ArrayType)) {
            this.constraintViolated(o, "Stack next-to-top must be of type float[] but is '" + this.stack().peek(1) + "'.");
        }
        final Type t = ((ArrayType)this.stack().peek(1)).getBasicType();
        if (t != Type.FLOAT) {
            this.constraintViolated(o, "Stack next-to-top must be of type float[] but is '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitFASTORE(final FASTORE o) {
        if (this.stack().peek() != Type.FLOAT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'float', but of type '" + this.stack().peek() + "'.");
        }
        this.indexOfInt(o, this.stack().peek(1));
        if (this.stack().peek(2) == Type.NULL) {
            return;
        }
        if (!(this.stack().peek(2) instanceof ArrayType)) {
            this.constraintViolated(o, "Stack next-to-next-to-top must be of type float[] but is '" + this.stack().peek(2) + "'.");
        }
        final Type t = ((ArrayType)this.stack().peek(2)).getBasicType();
        if (t != Type.FLOAT) {
            this.constraintViolated(o, "Stack next-to-next-to-top must be of type float[] but is '" + this.stack().peek(2) + "'.");
        }
    }
    
    public void visitFCMPG(final FCMPG o) {
        if (this.stack().peek() != Type.FLOAT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'float', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.FLOAT) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'float', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitFCMPL(final FCMPL o) {
        if (this.stack().peek() != Type.FLOAT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'float', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.FLOAT) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'float', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitFCONST(final FCONST o) {
    }
    
    public void visitFDIV(final FDIV o) {
        if (this.stack().peek() != Type.FLOAT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'float', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.FLOAT) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'float', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitFLOAD(final FLOAD o) {
    }
    
    public void visitFMUL(final FMUL o) {
        if (this.stack().peek() != Type.FLOAT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'float', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.FLOAT) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'float', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitFNEG(final FNEG o) {
        if (this.stack().peek() != Type.FLOAT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'float', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitFREM(final FREM o) {
        if (this.stack().peek() != Type.FLOAT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'float', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.FLOAT) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'float', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitFRETURN(final FRETURN o) {
        if (this.stack().peek() != Type.FLOAT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'float', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitFSTORE(final FSTORE o) {
    }
    
    public void visitFSUB(final FSUB o) {
        if (this.stack().peek() != Type.FLOAT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'float', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.FLOAT) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'float', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitGETFIELD(final GETFIELD o) {
        final Type objectref = this.stack().peek();
        if (!(objectref instanceof ObjectType) && objectref != Type.NULL) {
            this.constraintViolated(o, "Stack top should be an object reference that's not an array reference, but is '" + objectref + "'.");
        }
        final String field_name = o.getFieldName(this.cpg);
        final JavaClass jc = Repository.lookupClass(o.getClassType(this.cpg).getClassName());
        final Field[] fields = jc.getFields();
        Field f = null;
        for (int i = 0; i < fields.length; ++i) {
            if (fields[i].getName().equals(field_name)) {
                f = fields[i];
                break;
            }
        }
        if (f == null) {
            throw new AssertionViolatedException("Field not found?!?");
        }
        if (f.isProtected()) {
            final ObjectType classtype = o.getClassType(this.cpg);
            final ObjectType curr = new ObjectType(this.mg.getClassName());
            if (classtype.equals(curr) || curr.subclassOf(classtype)) {
                final Type t = this.stack().peek();
                if (t == Type.NULL) {
                    return;
                }
                if (!(t instanceof ObjectType)) {
                    this.constraintViolated(o, "The 'objectref' must refer to an object that's not an array. Found instead: '" + t + "'.");
                }
                final ObjectType objreftype = (ObjectType)t;
                if (!objreftype.equals(curr)) {
                    objreftype.subclassOf(curr);
                }
            }
        }
        if (f.isStatic()) {
            this.constraintViolated(o, "Referenced field '" + f + "' is static which it shouldn't be.");
        }
    }
    
    public void visitGETSTATIC(final GETSTATIC o) {
    }
    
    public void visitGOTO(final GOTO o) {
    }
    
    public void visitGOTO_W(final GOTO_W o) {
    }
    
    public void visitI2B(final I2B o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitI2C(final I2C o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitI2D(final I2D o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitI2F(final I2F o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitI2L(final I2L o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitI2S(final I2S o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitIADD(final IADD o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.INT) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'int', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitIALOAD(final IALOAD o) {
        this.indexOfInt(o, this.stack().peek());
        if (this.stack().peek(1) == Type.NULL) {
            return;
        }
        if (!(this.stack().peek(1) instanceof ArrayType)) {
            this.constraintViolated(o, "Stack next-to-top must be of type int[] but is '" + this.stack().peek(1) + "'.");
        }
        final Type t = ((ArrayType)this.stack().peek(1)).getBasicType();
        if (t != Type.INT) {
            this.constraintViolated(o, "Stack next-to-top must be of type int[] but is '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitIAND(final IAND o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.INT) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'int', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitIASTORE(final IASTORE o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
        this.indexOfInt(o, this.stack().peek(1));
        if (this.stack().peek(2) == Type.NULL) {
            return;
        }
        if (!(this.stack().peek(2) instanceof ArrayType)) {
            this.constraintViolated(o, "Stack next-to-next-to-top must be of type int[] but is '" + this.stack().peek(2) + "'.");
        }
        final Type t = ((ArrayType)this.stack().peek(2)).getBasicType();
        if (t != Type.INT) {
            this.constraintViolated(o, "Stack next-to-next-to-top must be of type int[] but is '" + this.stack().peek(2) + "'.");
        }
    }
    
    public void visitICONST(final ICONST o) {
    }
    
    public void visitIDIV(final IDIV o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.INT) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'int', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitIF_ACMPEQ(final IF_ACMPEQ o) {
        if (!(this.stack().peek() instanceof ReferenceType)) {
            this.constraintViolated(o, "The value at the stack top is not of a ReferenceType, but of type '" + this.stack().peek() + "'.");
        }
        this.referenceTypeIsInitialized(o, (ReferenceType)this.stack().peek());
        if (!(this.stack().peek(1) instanceof ReferenceType)) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of a ReferenceType, but of type '" + this.stack().peek(1) + "'.");
        }
        this.referenceTypeIsInitialized(o, (ReferenceType)this.stack().peek(1));
    }
    
    public void visitIF_ACMPNE(final IF_ACMPNE o) {
        if (!(this.stack().peek() instanceof ReferenceType)) {
            this.constraintViolated(o, "The value at the stack top is not of a ReferenceType, but of type '" + this.stack().peek() + "'.");
            this.referenceTypeIsInitialized(o, (ReferenceType)this.stack().peek());
        }
        if (!(this.stack().peek(1) instanceof ReferenceType)) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of a ReferenceType, but of type '" + this.stack().peek(1) + "'.");
            this.referenceTypeIsInitialized(o, (ReferenceType)this.stack().peek(1));
        }
    }
    
    public void visitIF_ICMPEQ(final IF_ICMPEQ o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.INT) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'int', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitIF_ICMPGE(final IF_ICMPGE o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.INT) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'int', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitIF_ICMPGT(final IF_ICMPGT o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.INT) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'int', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitIF_ICMPLE(final IF_ICMPLE o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.INT) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'int', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitIF_ICMPLT(final IF_ICMPLT o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.INT) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'int', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitIF_ICMPNE(final IF_ICMPNE o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.INT) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'int', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitIFEQ(final IFEQ o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitIFGE(final IFGE o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitIFGT(final IFGT o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitIFLE(final IFLE o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitIFLT(final IFLT o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitIFNE(final IFNE o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitIFNONNULL(final IFNONNULL o) {
        if (!(this.stack().peek() instanceof ReferenceType)) {
            this.constraintViolated(o, "The value at the stack top is not of a ReferenceType, but of type '" + this.stack().peek() + "'.");
        }
        this.referenceTypeIsInitialized(o, (ReferenceType)this.stack().peek());
    }
    
    public void visitIFNULL(final IFNULL o) {
        if (!(this.stack().peek() instanceof ReferenceType)) {
            this.constraintViolated(o, "The value at the stack top is not of a ReferenceType, but of type '" + this.stack().peek() + "'.");
        }
        this.referenceTypeIsInitialized(o, (ReferenceType)this.stack().peek());
    }
    
    public void visitIINC(final IINC o) {
        if (this.locals().maxLocals() <= ((o.getType(this.cpg).getSize() == 1) ? o.getIndex() : (o.getIndex() + 1))) {
            this.constraintViolated(o, "The 'index' is not a valid index into the local variable array.");
        }
        this.indexOfInt(o, this.locals().get(o.getIndex()));
    }
    
    public void visitILOAD(final ILOAD o) {
    }
    
    public void visitIMPDEP1(final IMPDEP1 o) {
        throw new AssertionViolatedException("In this JustIce verification pass there should not occur an illegal instruction such as IMPDEP1.");
    }
    
    public void visitIMPDEP2(final IMPDEP2 o) {
        throw new AssertionViolatedException("In this JustIce verification pass there should not occur an illegal instruction such as IMPDEP2.");
    }
    
    public void visitIMUL(final IMUL o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.INT) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'int', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitINEG(final INEG o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitINSTANCEOF(final INSTANCEOF o) {
        final Type objectref = this.stack().peek(0);
        if (!(objectref instanceof ReferenceType)) {
            this.constraintViolated(o, "The 'objectref' is not of a ReferenceType but of type " + objectref + ".");
        }
        else {
            this.referenceTypeIsInitialized(o, (ReferenceType)objectref);
        }
        final Constant c = this.cpg.getConstant(o.getIndex());
        if (!(c instanceof ConstantClass)) {
            this.constraintViolated(o, "The Constant at 'index' is not a ConstantClass, but '" + c + "'.");
        }
    }
    
    public void visitINVOKEINTERFACE(final INVOKEINTERFACE o) {
        final int count = o.getCount();
        if (count == 0) {
            this.constraintViolated(o, "The 'count' argument must not be 0.");
        }
        final ConstantInterfaceMethodref cimr = (ConstantInterfaceMethodref)this.cpg.getConstant(o.getIndex());
        final Type t = o.getType(this.cpg);
        if (t instanceof ObjectType) {
            final String name = ((ObjectType)t).getClassName();
            final Verifier v = VerifierFactory.getVerifier(name);
            final VerificationResult vr = v.doPass2();
            if (vr.getStatus() != 1) {
                this.constraintViolated(o, "Class '" + name + "' is referenced, but cannot be loaded and resolved: '" + vr + "'.");
            }
        }
        final Type[] argtypes = o.getArgumentTypes(this.cpg);
        final int nargs = argtypes.length;
        for (int i = nargs - 1; i >= 0; --i) {
            final Type fromStack = this.stack().peek(nargs - 1 - i);
            Type fromDesc = argtypes[i];
            if (fromDesc == Type.BOOLEAN || fromDesc == Type.BYTE || fromDesc == Type.CHAR || fromDesc == Type.SHORT) {
                fromDesc = Type.INT;
            }
            if (!fromStack.equals(fromDesc)) {
                if (fromStack instanceof ReferenceType && fromDesc instanceof ReferenceType) {
                    final ReferenceType rFromStack = (ReferenceType)fromStack;
                    final ReferenceType rFromDesc = (ReferenceType)fromDesc;
                }
                else {
                    this.constraintViolated(o, "Expecting a '" + fromDesc + "' but found a '" + fromStack + "' on the stack.");
                }
            }
        }
        Type objref = this.stack().peek(nargs);
        if (objref == Type.NULL) {
            return;
        }
        if (!(objref instanceof ReferenceType)) {
            this.constraintViolated(o, "Expecting a reference type as 'objectref' on the stack, not a '" + objref + "'.");
        }
        this.referenceTypeIsInitialized(o, (ReferenceType)objref);
        if (!(objref instanceof ObjectType)) {
            if (!(objref instanceof ArrayType)) {
                this.constraintViolated(o, "Expecting an ObjectType as 'objectref' on the stack, not a '" + objref + "'.");
            }
            else {
                objref = InstConstraintVisitor.GENERIC_ARRAY;
            }
        }
        final String objref_classname = ((ObjectType)objref).getClassName();
        final String theInterface = o.getClassName(this.cpg);
        int counted_count = 1;
        for (int j = 0; j < nargs; ++j) {
            counted_count += argtypes[j].getSize();
        }
        if (count != counted_count) {
            this.constraintViolated(o, "The 'count' argument should probably read '" + counted_count + "' but is '" + count + "'.");
        }
    }
    
    public void visitINVOKESPECIAL(final INVOKESPECIAL o) {
        if (o.getMethodName(this.cpg).equals("<init>") && !(this.stack().peek(o.getArgumentTypes(this.cpg).length) instanceof UninitializedObjectType)) {
            this.constraintViolated(o, "Possibly initializing object twice. A valid instruction sequence must not have an uninitialized object on the operand stack or in a local variable during a backwards branch, or in a local variable in code protected by an exception handler. Please see The Java Virtual Machine Specification, Second Edition, 4.9.4 (pages 147 and 148) for details.");
        }
        final Type t = o.getType(this.cpg);
        if (t instanceof ObjectType) {
            final String name = ((ObjectType)t).getClassName();
            final Verifier v = VerifierFactory.getVerifier(name);
            final VerificationResult vr = v.doPass2();
            if (vr.getStatus() != 1) {
                this.constraintViolated(o, "Class '" + name + "' is referenced, but cannot be loaded and resolved: '" + vr + "'.");
            }
        }
        final Type[] argtypes = o.getArgumentTypes(this.cpg);
        final int nargs = argtypes.length;
        for (int i = nargs - 1; i >= 0; --i) {
            final Type fromStack = this.stack().peek(nargs - 1 - i);
            Type fromDesc = argtypes[i];
            if (fromDesc == Type.BOOLEAN || fromDesc == Type.BYTE || fromDesc == Type.CHAR || fromDesc == Type.SHORT) {
                fromDesc = Type.INT;
            }
            if (!fromStack.equals(fromDesc)) {
                if (fromStack instanceof ReferenceType && fromDesc instanceof ReferenceType) {
                    final ReferenceType rFromStack = (ReferenceType)fromStack;
                    final ReferenceType rFromDesc = (ReferenceType)fromDesc;
                }
                else {
                    this.constraintViolated(o, "Expecting a '" + fromDesc + "' but found a '" + fromStack + "' on the stack.");
                }
            }
        }
        Type objref = this.stack().peek(nargs);
        if (objref == Type.NULL) {
            return;
        }
        if (!(objref instanceof ReferenceType)) {
            this.constraintViolated(o, "Expecting a reference type as 'objectref' on the stack, not a '" + objref + "'.");
        }
        String objref_classname = null;
        if (!o.getMethodName(this.cpg).equals("<init>")) {
            this.referenceTypeIsInitialized(o, (ReferenceType)objref);
            if (!(objref instanceof ObjectType)) {
                if (!(objref instanceof ArrayType)) {
                    this.constraintViolated(o, "Expecting an ObjectType as 'objectref' on the stack, not a '" + objref + "'.");
                }
                else {
                    objref = InstConstraintVisitor.GENERIC_ARRAY;
                }
            }
            objref_classname = ((ObjectType)objref).getClassName();
        }
        else {
            if (!(objref instanceof UninitializedObjectType)) {
                this.constraintViolated(o, "Expecting an UninitializedObjectType as 'objectref' on the stack, not a '" + objref + "'. Otherwise, you couldn't invoke a method since an array has no methods (not to speak of a return address).");
            }
            objref_classname = ((UninitializedObjectType)objref).getInitialized().getClassName();
        }
        final String theClass = o.getClassName(this.cpg);
        if (!Repository.instanceOf(objref_classname, theClass)) {
            this.constraintViolated(o, "The 'objref' item '" + objref + "' does not implement '" + theClass + "' as expected.");
        }
    }
    
    public void visitINVOKESTATIC(final INVOKESTATIC o) {
        final Type t = o.getType(this.cpg);
        if (t instanceof ObjectType) {
            final String name = ((ObjectType)t).getClassName();
            final Verifier v = VerifierFactory.getVerifier(name);
            final VerificationResult vr = v.doPass2();
            if (vr.getStatus() != 1) {
                this.constraintViolated(o, "Class '" + name + "' is referenced, but cannot be loaded and resolved: '" + vr + "'.");
            }
        }
        final Type[] argtypes = o.getArgumentTypes(this.cpg);
        final int nargs = argtypes.length;
        for (int i = nargs - 1; i >= 0; --i) {
            final Type fromStack = this.stack().peek(nargs - 1 - i);
            Type fromDesc = argtypes[i];
            if (fromDesc == Type.BOOLEAN || fromDesc == Type.BYTE || fromDesc == Type.CHAR || fromDesc == Type.SHORT) {
                fromDesc = Type.INT;
            }
            if (!fromStack.equals(fromDesc)) {
                if (fromStack instanceof ReferenceType && fromDesc instanceof ReferenceType) {
                    final ReferenceType rFromStack = (ReferenceType)fromStack;
                    final ReferenceType rFromDesc = (ReferenceType)fromDesc;
                }
                else {
                    this.constraintViolated(o, "Expecting a '" + fromDesc + "' but found a '" + fromStack + "' on the stack.");
                }
            }
        }
    }
    
    public void visitINVOKEVIRTUAL(final INVOKEVIRTUAL o) {
        final Type t = o.getType(this.cpg);
        if (t instanceof ObjectType) {
            final String name = ((ObjectType)t).getClassName();
            final Verifier v = VerifierFactory.getVerifier(name);
            final VerificationResult vr = v.doPass2();
            if (vr.getStatus() != 1) {
                this.constraintViolated(o, "Class '" + name + "' is referenced, but cannot be loaded and resolved: '" + vr + "'.");
            }
        }
        final Type[] argtypes = o.getArgumentTypes(this.cpg);
        final int nargs = argtypes.length;
        for (int i = nargs - 1; i >= 0; --i) {
            final Type fromStack = this.stack().peek(nargs - 1 - i);
            Type fromDesc = argtypes[i];
            if (fromDesc == Type.BOOLEAN || fromDesc == Type.BYTE || fromDesc == Type.CHAR || fromDesc == Type.SHORT) {
                fromDesc = Type.INT;
            }
            if (!fromStack.equals(fromDesc)) {
                if (fromStack instanceof ReferenceType && fromDesc instanceof ReferenceType) {
                    final ReferenceType rFromStack = (ReferenceType)fromStack;
                    final ReferenceType rFromDesc = (ReferenceType)fromDesc;
                }
                else {
                    this.constraintViolated(o, "Expecting a '" + fromDesc + "' but found a '" + fromStack + "' on the stack.");
                }
            }
        }
        Type objref = this.stack().peek(nargs);
        if (objref == Type.NULL) {
            return;
        }
        if (!(objref instanceof ReferenceType)) {
            this.constraintViolated(o, "Expecting a reference type as 'objectref' on the stack, not a '" + objref + "'.");
        }
        this.referenceTypeIsInitialized(o, (ReferenceType)objref);
        if (!(objref instanceof ObjectType)) {
            if (!(objref instanceof ArrayType)) {
                this.constraintViolated(o, "Expecting an ObjectType as 'objectref' on the stack, not a '" + objref + "'.");
            }
            else {
                objref = InstConstraintVisitor.GENERIC_ARRAY;
            }
        }
        final String objref_classname = ((ObjectType)objref).getClassName();
        final String theClass = o.getClassName(this.cpg);
        if (!Repository.instanceOf(objref_classname, theClass)) {
            this.constraintViolated(o, "The 'objref' item '" + objref + "' does not implement '" + theClass + "' as expected.");
        }
    }
    
    public void visitIOR(final IOR o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.INT) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'int', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitIREM(final IREM o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.INT) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'int', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitIRETURN(final IRETURN o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitISHL(final ISHL o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.INT) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'int', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitISHR(final ISHR o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.INT) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'int', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitISTORE(final ISTORE o) {
    }
    
    public void visitISUB(final ISUB o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.INT) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'int', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitIUSHR(final IUSHR o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.INT) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'int', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitIXOR(final IXOR o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.INT) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'int', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitJSR(final JSR o) {
    }
    
    public void visitJSR_W(final JSR_W o) {
    }
    
    public void visitL2D(final L2D o) {
        if (this.stack().peek() != Type.LONG) {
            this.constraintViolated(o, "The value at the stack top is not of type 'long', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitL2F(final L2F o) {
        if (this.stack().peek() != Type.LONG) {
            this.constraintViolated(o, "The value at the stack top is not of type 'long', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitL2I(final L2I o) {
        if (this.stack().peek() != Type.LONG) {
            this.constraintViolated(o, "The value at the stack top is not of type 'long', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitLADD(final LADD o) {
        if (this.stack().peek() != Type.LONG) {
            this.constraintViolated(o, "The value at the stack top is not of type 'long', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.LONG) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'long', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitLALOAD(final LALOAD o) {
        this.indexOfInt(o, this.stack().peek());
        if (this.stack().peek(1) == Type.NULL) {
            return;
        }
        if (!(this.stack().peek(1) instanceof ArrayType)) {
            this.constraintViolated(o, "Stack next-to-top must be of type long[] but is '" + this.stack().peek(1) + "'.");
        }
        final Type t = ((ArrayType)this.stack().peek(1)).getBasicType();
        if (t != Type.LONG) {
            this.constraintViolated(o, "Stack next-to-top must be of type long[] but is '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitLAND(final LAND o) {
        if (this.stack().peek() != Type.LONG) {
            this.constraintViolated(o, "The value at the stack top is not of type 'long', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.LONG) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'long', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitLASTORE(final LASTORE o) {
        if (this.stack().peek() != Type.LONG) {
            this.constraintViolated(o, "The value at the stack top is not of type 'long', but of type '" + this.stack().peek() + "'.");
        }
        this.indexOfInt(o, this.stack().peek(1));
        if (this.stack().peek(2) == Type.NULL) {
            return;
        }
        if (!(this.stack().peek(2) instanceof ArrayType)) {
            this.constraintViolated(o, "Stack next-to-next-to-top must be of type long[] but is '" + this.stack().peek(2) + "'.");
        }
        final Type t = ((ArrayType)this.stack().peek(2)).getBasicType();
        if (t != Type.LONG) {
            this.constraintViolated(o, "Stack next-to-next-to-top must be of type long[] but is '" + this.stack().peek(2) + "'.");
        }
    }
    
    public void visitLCMP(final LCMP o) {
        if (this.stack().peek() != Type.LONG) {
            this.constraintViolated(o, "The value at the stack top is not of type 'long', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.LONG) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'long', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitLCONST(final LCONST o) {
    }
    
    public void visitLDC(final LDC o) {
        final Constant c = this.cpg.getConstant(o.getIndex());
        if (!(c instanceof ConstantInteger) && !(c instanceof ConstantFloat) && !(c instanceof ConstantString)) {
            this.constraintViolated(o, "Referenced constant should be a CONSTANT_Integer, a CONSTANT_Float or a CONSTANT_String, but is '" + c + "'.");
        }
    }
    
    public void visitLDC_W(final LDC_W o) {
        final Constant c = this.cpg.getConstant(o.getIndex());
        if (!(c instanceof ConstantInteger) && !(c instanceof ConstantFloat) && !(c instanceof ConstantString)) {
            this.constraintViolated(o, "Referenced constant should be a CONSTANT_Integer, a CONSTANT_Float or a CONSTANT_String, but is '" + c + "'.");
        }
    }
    
    public void visitLDC2_W(final LDC2_W o) {
        final Constant c = this.cpg.getConstant(o.getIndex());
        if (!(c instanceof ConstantLong) && !(c instanceof ConstantDouble)) {
            this.constraintViolated(o, "Referenced constant should be a CONSTANT_Integer, a CONSTANT_Float or a CONSTANT_String, but is '" + c + "'.");
        }
    }
    
    public void visitLDIV(final LDIV o) {
        if (this.stack().peek() != Type.LONG) {
            this.constraintViolated(o, "The value at the stack top is not of type 'long', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.LONG) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'long', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitLLOAD(final LLOAD o) {
    }
    
    public void visitLMUL(final LMUL o) {
        if (this.stack().peek() != Type.LONG) {
            this.constraintViolated(o, "The value at the stack top is not of type 'long', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.LONG) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'long', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitLNEG(final LNEG o) {
        if (this.stack().peek() != Type.LONG) {
            this.constraintViolated(o, "The value at the stack top is not of type 'long', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitLOOKUPSWITCH(final LOOKUPSWITCH o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitLOR(final LOR o) {
        if (this.stack().peek() != Type.LONG) {
            this.constraintViolated(o, "The value at the stack top is not of type 'long', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.LONG) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'long', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitLREM(final LREM o) {
        if (this.stack().peek() != Type.LONG) {
            this.constraintViolated(o, "The value at the stack top is not of type 'long', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.LONG) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'long', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitLRETURN(final LRETURN o) {
        if (this.stack().peek() != Type.LONG) {
            this.constraintViolated(o, "The value at the stack top is not of type 'long', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitLSHL(final LSHL o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.LONG) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'long', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitLSHR(final LSHR o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.LONG) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'long', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitLSTORE(final LSTORE o) {
    }
    
    public void visitLSUB(final LSUB o) {
        if (this.stack().peek() != Type.LONG) {
            this.constraintViolated(o, "The value at the stack top is not of type 'long', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.LONG) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'long', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitLUSHR(final LUSHR o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.LONG) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'long', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitLXOR(final LXOR o) {
        if (this.stack().peek() != Type.LONG) {
            this.constraintViolated(o, "The value at the stack top is not of type 'long', but of type '" + this.stack().peek() + "'.");
        }
        if (this.stack().peek(1) != Type.LONG) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of type 'long', but of type '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitMONITORENTER(final MONITORENTER o) {
        if (!(this.stack().peek() instanceof ReferenceType)) {
            this.constraintViolated(o, "The stack top should be of a ReferenceType, but is '" + this.stack().peek() + "'.");
        }
        this.referenceTypeIsInitialized(o, (ReferenceType)this.stack().peek());
    }
    
    public void visitMONITOREXIT(final MONITOREXIT o) {
        if (!(this.stack().peek() instanceof ReferenceType)) {
            this.constraintViolated(o, "The stack top should be of a ReferenceType, but is '" + this.stack().peek() + "'.");
        }
        this.referenceTypeIsInitialized(o, (ReferenceType)this.stack().peek());
    }
    
    public void visitMULTIANEWARRAY(final MULTIANEWARRAY o) {
        for (int dimensions = o.getDimensions(), i = 0; i < dimensions; ++i) {
            if (this.stack().peek(i) != Type.INT) {
                this.constraintViolated(o, "The '" + dimensions + "' upper stack types should be 'int' but aren't.");
            }
        }
    }
    
    public void visitNEW(final NEW o) {
        final Type t = o.getType(this.cpg);
        if (!(t instanceof ReferenceType)) {
            throw new AssertionViolatedException("NEW.getType() returning a non-reference type?!");
        }
        if (!(t instanceof ObjectType)) {
            this.constraintViolated(o, "Expecting a class type (ObjectType) to work on. Found: '" + t + "'.");
        }
        final ObjectType obj = (ObjectType)t;
        if (!obj.referencesClass()) {
            this.constraintViolated(o, "Expecting a class type (ObjectType) to work on. Found: '" + obj + "'.");
        }
    }
    
    public void visitNEWARRAY(final NEWARRAY o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
    }
    
    public void visitNOP(final NOP o) {
    }
    
    public void visitPOP(final POP o) {
        if (this.stack().peek().getSize() != 1) {
            this.constraintViolated(o, "Stack top size should be 1 but stack top is '" + this.stack().peek() + "' of size '" + this.stack().peek().getSize() + "'.");
        }
    }
    
    public void visitPOP2(final POP2 o) {
        if (this.stack().peek().getSize() != 2) {
            this.constraintViolated(o, "Stack top size should be 2 but stack top is '" + this.stack().peek() + "' of size '" + this.stack().peek().getSize() + "'.");
        }
    }
    
    public void visitPUTFIELD(final PUTFIELD o) {
        final Type objectref = this.stack().peek(1);
        if (!(objectref instanceof ObjectType) && objectref != Type.NULL) {
            this.constraintViolated(o, "Stack next-to-top should be an object reference that's not an array reference, but is '" + objectref + "'.");
        }
        final String field_name = o.getFieldName(this.cpg);
        final JavaClass jc = Repository.lookupClass(o.getClassType(this.cpg).getClassName());
        final Field[] fields = jc.getFields();
        Field f = null;
        for (int i = 0; i < fields.length; ++i) {
            if (fields[i].getName().equals(field_name)) {
                f = fields[i];
                break;
            }
        }
        if (f == null) {
            throw new AssertionViolatedException("Field not found?!?");
        }
        final Type value = this.stack().peek();
        Type shouldbe;
        final Type t = shouldbe = Type.getType(f.getSignature());
        if (shouldbe == Type.BOOLEAN || shouldbe == Type.BYTE || shouldbe == Type.CHAR || shouldbe == Type.SHORT) {
            shouldbe = Type.INT;
        }
        if (t instanceof ReferenceType) {
            ReferenceType rvalue = null;
            if (value instanceof ReferenceType) {
                rvalue = (ReferenceType)value;
                this.referenceTypeIsInitialized(o, rvalue);
            }
            else {
                this.constraintViolated(o, "The stack top type '" + value + "' is not of a reference type as expected.");
            }
        }
        else if (shouldbe != value) {
            this.constraintViolated(o, "The stack top type '" + value + "' is not of type '" + shouldbe + "' as expected.");
        }
        if (f.isProtected()) {
            final ObjectType classtype = o.getClassType(this.cpg);
            final ObjectType curr = new ObjectType(this.mg.getClassName());
            if (classtype.equals(curr) || curr.subclassOf(classtype)) {
                final Type tp = this.stack().peek(1);
                if (tp == Type.NULL) {
                    return;
                }
                if (!(tp instanceof ObjectType)) {
                    this.constraintViolated(o, "The 'objectref' must refer to an object that's not an array. Found instead: '" + tp + "'.");
                }
                final ObjectType objreftype = (ObjectType)tp;
                if (!objreftype.equals(curr) && !objreftype.subclassOf(curr)) {
                    this.constraintViolated(o, "The referenced field has the ACC_PROTECTED modifier, and it's a member of the current class or a superclass of the current class. However, the referenced object type '" + this.stack().peek() + "' is not the current class or a subclass of the current class.");
                }
            }
        }
        if (f.isStatic()) {
            this.constraintViolated(o, "Referenced field '" + f + "' is static which it shouldn't be.");
        }
    }
    
    public void visitPUTSTATIC(final PUTSTATIC o) {
        final String field_name = o.getFieldName(this.cpg);
        final JavaClass jc = Repository.lookupClass(o.getClassType(this.cpg).getClassName());
        final Field[] fields = jc.getFields();
        Field f = null;
        for (int i = 0; i < fields.length; ++i) {
            if (fields[i].getName().equals(field_name)) {
                f = fields[i];
                break;
            }
        }
        if (f == null) {
            throw new AssertionViolatedException("Field not found?!?");
        }
        final Type value = this.stack().peek();
        Type shouldbe;
        final Type t = shouldbe = Type.getType(f.getSignature());
        if (shouldbe == Type.BOOLEAN || shouldbe == Type.BYTE || shouldbe == Type.CHAR || shouldbe == Type.SHORT) {
            shouldbe = Type.INT;
        }
        if (t instanceof ReferenceType) {
            ReferenceType rvalue = null;
            if (value instanceof ReferenceType) {
                rvalue = (ReferenceType)value;
                this.referenceTypeIsInitialized(o, rvalue);
            }
            else {
                this.constraintViolated(o, "The stack top type '" + value + "' is not of a reference type as expected.");
            }
            if (!rvalue.isAssignmentCompatibleWith(shouldbe)) {
                this.constraintViolated(o, "The stack top type '" + value + "' is not assignment compatible with '" + shouldbe + "'.");
            }
        }
        else if (shouldbe != value) {
            this.constraintViolated(o, "The stack top type '" + value + "' is not of type '" + shouldbe + "' as expected.");
        }
    }
    
    public void visitRET(final RET o) {
        if (!(this.locals().get(o.getIndex()) instanceof ReturnaddressType)) {
            this.constraintViolated(o, "Expecting a ReturnaddressType in local variable " + o.getIndex() + ".");
        }
        if (this.locals().get(o.getIndex()) == ReturnaddressType.NO_TARGET) {
            throw new AssertionViolatedException("Oops: RET expecting a target!");
        }
    }
    
    public void visitRETURN(final RETURN o) {
        if (this.mg.getName().equals("<init>") && Frame._this != null && !this.mg.getClassName().equals(Type.OBJECT.getClassName())) {
            this.constraintViolated(o, "Leaving a constructor that itself did not call a constructor.");
        }
    }
    
    public void visitSALOAD(final SALOAD o) {
        this.indexOfInt(o, this.stack().peek());
        if (this.stack().peek(1) == Type.NULL) {
            return;
        }
        if (!(this.stack().peek(1) instanceof ArrayType)) {
            this.constraintViolated(o, "Stack next-to-top must be of type short[] but is '" + this.stack().peek(1) + "'.");
        }
        final Type t = ((ArrayType)this.stack().peek(1)).getBasicType();
        if (t != Type.SHORT) {
            this.constraintViolated(o, "Stack next-to-top must be of type short[] but is '" + this.stack().peek(1) + "'.");
        }
    }
    
    public void visitSASTORE(final SASTORE o) {
        if (this.stack().peek() != Type.INT) {
            this.constraintViolated(o, "The value at the stack top is not of type 'int', but of type '" + this.stack().peek() + "'.");
        }
        this.indexOfInt(o, this.stack().peek(1));
        if (this.stack().peek(2) == Type.NULL) {
            return;
        }
        if (!(this.stack().peek(2) instanceof ArrayType)) {
            this.constraintViolated(o, "Stack next-to-next-to-top must be of type short[] but is '" + this.stack().peek(2) + "'.");
        }
        final Type t = ((ArrayType)this.stack().peek(2)).getBasicType();
        if (t != Type.SHORT) {
            this.constraintViolated(o, "Stack next-to-next-to-top must be of type short[] but is '" + this.stack().peek(2) + "'.");
        }
    }
    
    public void visitSIPUSH(final SIPUSH o) {
    }
    
    public void visitSWAP(final SWAP o) {
        if (this.stack().peek().getSize() != 1) {
            this.constraintViolated(o, "The value at the stack top is not of size '1', but of size '" + this.stack().peek().getSize() + "'.");
        }
        if (this.stack().peek(1).getSize() != 1) {
            this.constraintViolated(o, "The value at the stack next-to-top is not of size '1', but of size '" + this.stack().peek(1).getSize() + "'.");
        }
    }
    
    public void visitTABLESWITCH(final TABLESWITCH o) {
        this.indexOfInt(o, this.stack().peek());
    }
}
