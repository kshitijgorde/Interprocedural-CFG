// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.verifier.statics;

import com.ibm.xslt4j.bcel.generic.INVOKEINTERFACE;
import com.ibm.xslt4j.bcel.generic.GETSTATIC;
import com.ibm.xslt4j.bcel.classfile.Field;
import com.ibm.xslt4j.bcel.generic.PUTSTATIC;
import com.ibm.xslt4j.bcel.generic.TABLESWITCH;
import com.ibm.xslt4j.bcel.generic.LOOKUPSWITCH;
import com.ibm.xslt4j.bcel.generic.DSTORE;
import com.ibm.xslt4j.bcel.generic.LSTORE;
import com.ibm.xslt4j.bcel.generic.DLOAD;
import com.ibm.xslt4j.bcel.generic.LLOAD;
import com.ibm.xslt4j.bcel.generic.IINC;
import com.ibm.xslt4j.bcel.generic.FSTORE;
import com.ibm.xslt4j.bcel.generic.ISTORE;
import com.ibm.xslt4j.bcel.generic.ALOAD;
import com.ibm.xslt4j.bcel.generic.FLOAD;
import com.ibm.xslt4j.bcel.generic.ILOAD;
import com.ibm.xslt4j.bcel.generic.NEWARRAY;
import com.ibm.xslt4j.bcel.generic.ANEWARRAY;
import com.ibm.xslt4j.bcel.generic.MULTIANEWARRAY;
import com.ibm.xslt4j.bcel.generic.NEW;
import com.ibm.xslt4j.bcel.generic.CHECKCAST;
import com.ibm.xslt4j.bcel.classfile.ConstantClass;
import com.ibm.xslt4j.bcel.generic.INSTANCEOF;
import com.ibm.xslt4j.bcel.generic.Type;
import com.ibm.xslt4j.bcel.generic.ArrayType;
import com.ibm.xslt4j.bcel.classfile.ConstantInterfaceMethodref;
import com.ibm.xslt4j.bcel.classfile.ConstantUtf8;
import com.ibm.xslt4j.bcel.classfile.ConstantNameAndType;
import com.ibm.xslt4j.bcel.classfile.ConstantMethodref;
import com.ibm.xslt4j.bcel.generic.INVOKESTATIC;
import com.ibm.xslt4j.bcel.generic.INVOKESPECIAL;
import com.ibm.xslt4j.bcel.generic.INVOKEVIRTUAL;
import com.ibm.xslt4j.bcel.generic.InvokeInstruction;
import com.ibm.xslt4j.bcel.classfile.ConstantFieldref;
import com.ibm.xslt4j.bcel.generic.FieldInstruction;
import com.ibm.xslt4j.bcel.verifier.exc.AssertionViolatedException;
import com.ibm.xslt4j.bcel.classfile.ConstantDouble;
import com.ibm.xslt4j.bcel.classfile.ConstantLong;
import com.ibm.xslt4j.bcel.generic.LDC2_W;
import com.ibm.xslt4j.bcel.classfile.Constant;
import com.ibm.xslt4j.bcel.classfile.ConstantString;
import com.ibm.xslt4j.bcel.classfile.ConstantFloat;
import com.ibm.xslt4j.bcel.classfile.ConstantInteger;
import com.ibm.xslt4j.bcel.generic.LDC;
import com.ibm.xslt4j.bcel.generic.ObjectType;
import com.ibm.xslt4j.bcel.verifier.VerifierFactory;
import com.ibm.xslt4j.bcel.generic.LoadClass;
import com.ibm.xslt4j.bcel.generic.EmptyVisitor;
import com.ibm.xslt4j.bcel.generic.Visitor;
import com.ibm.xslt4j.bcel.generic.ASTORE;
import com.ibm.xslt4j.bcel.verifier.exc.StaticCodeInstructionOperandConstraintException;
import com.ibm.xslt4j.bcel.generic.JsrInstruction;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.Instruction;
import com.ibm.xslt4j.bcel.generic.InstructionHandle;
import com.ibm.xslt4j.bcel.generic.ATHROW;
import com.ibm.xslt4j.bcel.generic.GotoInstruction;
import com.ibm.xslt4j.bcel.generic.RET;
import com.ibm.xslt4j.bcel.generic.ReturnInstruction;
import com.ibm.xslt4j.bcel.generic.BREAKPOINT;
import com.ibm.xslt4j.bcel.generic.IMPDEP2;
import com.ibm.xslt4j.bcel.generic.IMPDEP1;
import com.ibm.xslt4j.bcel.verifier.exc.StaticCodeInstructionConstraintException;
import com.ibm.xslt4j.bcel.classfile.CodeException;
import com.ibm.xslt4j.bcel.classfile.LocalVariable;
import com.ibm.xslt4j.bcel.classfile.Attribute;
import com.ibm.xslt4j.bcel.classfile.LineNumber;
import com.ibm.xslt4j.bcel.classfile.LineNumberTable;
import com.ibm.xslt4j.bcel.classfile.LocalVariableTable;
import com.ibm.xslt4j.bcel.classfile.Method;
import com.ibm.xslt4j.bcel.classfile.JavaClass;
import com.ibm.xslt4j.bcel.verifier.exc.StaticCodeConstraintException;
import com.ibm.xslt4j.bcel.verifier.exc.ClassConstraintException;
import com.ibm.xslt4j.bcel.verifier.exc.InvalidMethodException;
import com.ibm.xslt4j.bcel.Repository;
import com.ibm.xslt4j.bcel.verifier.VerificationResult;
import com.ibm.xslt4j.bcel.classfile.Code;
import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.verifier.Verifier;
import com.ibm.xslt4j.bcel.verifier.PassVerifier;

public final class Pass3aVerifier extends PassVerifier
{
    private Verifier myOwner;
    private int method_no;
    InstructionList instructionList;
    Code code;
    
    public Pass3aVerifier(final Verifier owner, final int method_no) {
        this.myOwner = owner;
        this.method_no = method_no;
    }
    
    public VerificationResult do_verify() {
        if (!this.myOwner.doPass2().equals(VerificationResult.VR_OK)) {
            return VerificationResult.VR_NOTYET;
        }
        final JavaClass jc = Repository.lookupClass(this.myOwner.getClassName());
        final Method[] methods = jc.getMethods();
        if (this.method_no >= methods.length) {
            throw new InvalidMethodException("METHOD DOES NOT EXIST!");
        }
        final Method method = methods[this.method_no];
        this.code = method.getCode();
        if (method.isAbstract() || method.isNative()) {
            return VerificationResult.VR_OK;
        }
        try {
            this.instructionList = new InstructionList(method.getCode().getCode());
        }
        catch (RuntimeException re) {
            return new VerificationResult(2, "Bad bytecode in the code array of the Code attribute of method '" + method + "'.");
        }
        this.instructionList.setPositions(true);
        VerificationResult vr = VerificationResult.VR_OK;
        try {
            this.delayedPass2Checks();
        }
        catch (ClassConstraintException cce) {
            vr = new VerificationResult(2, cce.getMessage());
            return vr;
        }
        try {
            this.pass3StaticInstructionChecks();
            this.pass3StaticInstructionOperandsChecks();
        }
        catch (StaticCodeConstraintException scce) {
            vr = new VerificationResult(2, scce.getMessage());
        }
        return vr;
    }
    
    private void delayedPass2Checks() {
        final int[] instructionPositions = this.instructionList.getInstructionPositions();
        final int codeLength = this.code.getCode().length;
        final LineNumberTable lnt = this.code.getLineNumberTable();
        if (lnt != null) {
            final LineNumber[] lineNumbers = lnt.getLineNumberTable();
            final IntList offsets = new IntList();
            int i = 0;
        Label_0215:
            while (i < lineNumbers.length) {
                for (int j = 0; j < instructionPositions.length; ++j) {
                    final int offset = lineNumbers[i].getStartPC();
                    if (instructionPositions[j] == offset) {
                        if (offsets.contains(offset)) {
                            this.addMessage("LineNumberTable attribute '" + this.code.getLineNumberTable() + "' refers to the same code offset ('" + offset + "') more than once which is violating the semantics [but is sometimes produced by IBM's 'jikes' compiler].");
                        }
                        else {
                            offsets.add(offset);
                        }
                        ++i;
                        continue Label_0215;
                    }
                }
                throw new ClassConstraintException("Code attribute '" + this.code + "' has a LineNumberTable attribute '" + this.code.getLineNumberTable() + "' referring to a code offset ('" + lineNumbers[i].getStartPC() + "') that does not exist.");
            }
        }
        final Attribute[] atts = this.code.getAttributes();
        for (int a = 0; a < atts.length; ++a) {
            if (atts[a] instanceof LocalVariableTable) {
                final LocalVariableTable lvt = (LocalVariableTable)atts[a];
                if (lvt != null) {
                    final LocalVariable[] localVariables = lvt.getLocalVariableTable();
                    for (int k = 0; k < localVariables.length; ++k) {
                        final int startpc = localVariables[k].getStartPC();
                        final int length = localVariables[k].getLength();
                        if (!contains(instructionPositions, startpc)) {
                            throw new ClassConstraintException("Code attribute '" + this.code + "' has a LocalVariableTable attribute '" + this.code.getLocalVariableTable() + "' referring to a code offset ('" + startpc + "') that does not exist.");
                        }
                        if (!contains(instructionPositions, startpc + length) && startpc + length != codeLength) {
                            throw new ClassConstraintException("Code attribute '" + this.code + "' has a LocalVariableTable attribute '" + this.code.getLocalVariableTable() + "' referring to a code offset start_pc+length ('" + (startpc + length) + "') that does not exist.");
                        }
                    }
                }
            }
        }
        int i;
        CodeException[] exceptionTable;
        int startpc2;
        int endpc;
        int handlerpc;
        for (exceptionTable = this.code.getExceptionTable(), i = 0; i < exceptionTable.length; ++i) {
            startpc2 = exceptionTable[i].getStartPC();
            endpc = exceptionTable[i].getEndPC();
            handlerpc = exceptionTable[i].getHandlerPC();
            if (startpc2 >= endpc) {
                throw new ClassConstraintException("Code attribute '" + this.code + "' has an exception_table entry '" + exceptionTable[i] + "' that has its start_pc ('" + startpc2 + "') not smaller than its end_pc ('" + endpc + "').");
            }
            if (!contains(instructionPositions, startpc2)) {
                throw new ClassConstraintException("Code attribute '" + this.code + "' has an exception_table entry '" + exceptionTable[i] + "' that has a non-existant bytecode offset as its start_pc ('" + startpc2 + "').");
            }
            if (!contains(instructionPositions, endpc) && endpc != codeLength) {
                throw new ClassConstraintException("Code attribute '" + this.code + "' has an exception_table entry '" + exceptionTable[i] + "' that has a non-existant bytecode offset as its end_pc ('" + startpc2 + "') [that is also not equal to code_length ('" + codeLength + "')].");
            }
            if (!contains(instructionPositions, handlerpc)) {
                throw new ClassConstraintException("Code attribute '" + this.code + "' has an exception_table entry '" + exceptionTable[i] + "' that has a non-existant bytecode offset as its handler_pc ('" + handlerpc + "').");
            }
        }
    }
    
    private void pass3StaticInstructionChecks() {
        if (this.code.getCode().length >= 65536) {
            throw new StaticCodeInstructionConstraintException("Code array in code attribute '" + this.code + "' too big: must be smaller than 65536 bytes.");
        }
        for (InstructionHandle ih = this.instructionList.getStart(); ih != null; ih = ih.getNext()) {
            final Instruction i = ih.getInstruction();
            if (i instanceof IMPDEP1) {
                throw new StaticCodeInstructionConstraintException("IMPDEP1 must not be in the code, it is an illegal instruction for _internal_ JVM use!");
            }
            if (i instanceof IMPDEP2) {
                throw new StaticCodeInstructionConstraintException("IMPDEP2 must not be in the code, it is an illegal instruction for _internal_ JVM use!");
            }
            if (i instanceof BREAKPOINT) {
                throw new StaticCodeInstructionConstraintException("BREAKPOINT must not be in the code, it is an illegal instruction for _internal_ JVM use!");
            }
        }
        final Instruction last = this.instructionList.getEnd().getInstruction();
        if (!(last instanceof ReturnInstruction) && !(last instanceof RET) && !(last instanceof GotoInstruction) && !(last instanceof ATHROW)) {
            throw new StaticCodeInstructionConstraintException("Execution must not fall off the bottom of the code array. This constraint is enforced statically as some existing verifiers do - so it may be a false alarm if the last instruction is not reachable.");
        }
    }
    
    private void pass3StaticInstructionOperandsChecks() {
        final ConstantPoolGen cpg = new ConstantPoolGen(Repository.lookupClass(this.myOwner.getClassName()).getConstantPool());
        final InstOperandConstraintVisitor v = new InstOperandConstraintVisitor(cpg);
        for (InstructionHandle ih = this.instructionList.getStart(); ih != null; ih = ih.getNext()) {
            final Instruction i = ih.getInstruction();
            if (i instanceof JsrInstruction) {
                final InstructionHandle target = ((JsrInstruction)i).getTarget();
                if (target == this.instructionList.getStart()) {
                    throw new StaticCodeInstructionOperandConstraintException("Due to JustIce's clear definition of subroutines, no JSR or JSR_W may have a top-level instruction (such as the very first instruction, which is targeted by instruction '" + ih + "' as its target.");
                }
                if (!(target.getInstruction() instanceof ASTORE)) {
                    throw new StaticCodeInstructionOperandConstraintException("Due to JustIce's clear definition of subroutines, no JSR or JSR_W may target anything else than an ASTORE instruction. Instruction '" + ih + "' targets '" + target + "'.");
                }
            }
            ih.accept(v);
        }
    }
    
    private static boolean contains(final int[] ints, final int i) {
        for (int j = 0; j < ints.length; ++j) {
            if (ints[j] == i) {
                return true;
            }
        }
        return false;
    }
    
    public int getMethodNo() {
        return this.method_no;
    }
    
    private class InstOperandConstraintVisitor extends EmptyVisitor
    {
        private ConstantPoolGen cpg;
        
        InstOperandConstraintVisitor(final ConstantPoolGen cpg) {
            this.cpg = cpg;
        }
        
        private int max_locals() {
            return Repository.lookupClass(Pass3aVerifier.this.myOwner.getClassName()).getMethods()[Pass3aVerifier.this.method_no].getCode().getMaxLocals();
        }
        
        private void constraintViolated(final Instruction i, final String message) {
            throw new StaticCodeInstructionOperandConstraintException("Instruction " + i + " constraint violated: " + message);
        }
        
        private void indexValid(final Instruction i, final int idx) {
            if (idx < 0 || idx >= this.cpg.getSize()) {
                this.constraintViolated(i, "Illegal constant pool index '" + idx + "'.");
            }
        }
        
        public void visitLoadClass(final LoadClass o) {
            final ObjectType t = o.getLoadClassType(this.cpg);
            if (t != null) {
                final Verifier v = VerifierFactory.getVerifier(t.getClassName());
                final VerificationResult vr = v.doPass1();
                if (vr.getStatus() != 1) {
                    this.constraintViolated((Instruction)o, "Class '" + o.getLoadClassType(this.cpg).getClassName() + "' is referenced, but cannot be loaded: '" + vr + "'.");
                }
            }
        }
        
        public void visitLDC(final LDC o) {
            this.indexValid(o, o.getIndex());
            final Constant c = this.cpg.getConstant(o.getIndex());
            if (!(c instanceof ConstantInteger) && !(c instanceof ConstantFloat) && !(c instanceof ConstantString)) {
                this.constraintViolated(o, "Operand of LDC or LDC_W must be one of CONSTANT_Integer, CONSTANT_Float or CONSTANT_String, but is '" + c + "'.");
            }
        }
        
        public void visitLDC2_W(final LDC2_W o) {
            this.indexValid(o, o.getIndex());
            final Constant c = this.cpg.getConstant(o.getIndex());
            if (!(c instanceof ConstantLong) && !(c instanceof ConstantDouble)) {
                this.constraintViolated(o, "Operand of LDC2_W must be CONSTANT_Long or CONSTANT_Double, but is '" + c + "'.");
            }
            try {
                this.indexValid(o, o.getIndex() + 1);
            }
            catch (StaticCodeInstructionOperandConstraintException e) {
                throw new AssertionViolatedException("OOPS: Does not BCEL handle that? LDC2_W operand has a problem.");
            }
        }
        
        public void visitFieldInstruction(final FieldInstruction o) {
            this.indexValid(o, o.getIndex());
            final Constant c = this.cpg.getConstant(o.getIndex());
            if (!(c instanceof ConstantFieldref)) {
                this.constraintViolated(o, "Indexing a constant that's not a CONSTANT_Fieldref but a '" + c + "'.");
            }
        }
        
        public void visitInvokeInstruction(final InvokeInstruction o) {
            this.indexValid(o, o.getIndex());
            if (o instanceof INVOKEVIRTUAL || o instanceof INVOKESPECIAL || o instanceof INVOKESTATIC) {
                final Constant c = this.cpg.getConstant(o.getIndex());
                if (!(c instanceof ConstantMethodref)) {
                    this.constraintViolated(o, "Indexing a constant that's not a CONSTANT_Methodref but a '" + c + "'.");
                }
                else {
                    final ConstantNameAndType cnat = (ConstantNameAndType)this.cpg.getConstant(((ConstantMethodref)c).getNameAndTypeIndex());
                    final ConstantUtf8 cutf8 = (ConstantUtf8)this.cpg.getConstant(cnat.getNameIndex());
                    if (cutf8.getBytes().equals("<init>") && !(o instanceof INVOKESPECIAL)) {
                        this.constraintViolated(o, "Only INVOKESPECIAL is allowed to invoke instance initialization methods.");
                    }
                    if (!cutf8.getBytes().equals("<init>") && cutf8.getBytes().startsWith("<")) {
                        this.constraintViolated(o, "No method with a name beginning with '<' other than the instance initialization methods may be called by the method invocation instructions.");
                    }
                }
            }
            else {
                final Constant c = this.cpg.getConstant(o.getIndex());
                if (!(c instanceof ConstantInterfaceMethodref)) {
                    this.constraintViolated(o, "Indexing a constant that's not a CONSTANT_InterfaceMethodref but a '" + c + "'.");
                }
                final ConstantNameAndType cnat = (ConstantNameAndType)this.cpg.getConstant(((ConstantInterfaceMethodref)c).getNameAndTypeIndex());
                final String name = ((ConstantUtf8)this.cpg.getConstant(cnat.getNameIndex())).getBytes();
                if (name.equals("<init>")) {
                    this.constraintViolated(o, "Method to invoke must not be '<init>'.");
                }
                if (name.equals("<clinit>")) {
                    this.constraintViolated(o, "Method to invoke must not be '<clinit>'.");
                }
            }
            Type t = o.getReturnType(this.cpg);
            if (t instanceof ArrayType) {
                t = ((ArrayType)t).getBasicType();
            }
            if (t instanceof ObjectType) {
                final Verifier v = VerifierFactory.getVerifier(((ObjectType)t).getClassName());
                final VerificationResult vr = v.doPass2();
                if (vr.getStatus() != 1) {
                    this.constraintViolated(o, "Return type class/interface could not be verified successfully: '" + vr.getMessage() + "'.");
                }
            }
            final Type[] ts = o.getArgumentTypes(this.cpg);
            for (int i = 0; i < ts.length; ++i) {
                t = ts[i];
                if (t instanceof ArrayType) {
                    t = ((ArrayType)t).getBasicType();
                }
                if (t instanceof ObjectType) {
                    final Verifier v2 = VerifierFactory.getVerifier(((ObjectType)t).getClassName());
                    final VerificationResult vr2 = v2.doPass2();
                    if (vr2.getStatus() != 1) {
                        this.constraintViolated(o, "Argument type class/interface could not be verified successfully: '" + vr2.getMessage() + "'.");
                    }
                }
            }
        }
        
        public void visitINSTANCEOF(final INSTANCEOF o) {
            this.indexValid(o, o.getIndex());
            final Constant c = this.cpg.getConstant(o.getIndex());
            if (!(c instanceof ConstantClass)) {
                this.constraintViolated(o, "Expecting a CONSTANT_Class operand, but found a '" + c + "'.");
            }
        }
        
        public void visitCHECKCAST(final CHECKCAST o) {
            this.indexValid(o, o.getIndex());
            final Constant c = this.cpg.getConstant(o.getIndex());
            if (!(c instanceof ConstantClass)) {
                this.constraintViolated(o, "Expecting a CONSTANT_Class operand, but found a '" + c + "'.");
            }
        }
        
        public void visitNEW(final NEW o) {
            this.indexValid(o, o.getIndex());
            final Constant c = this.cpg.getConstant(o.getIndex());
            if (!(c instanceof ConstantClass)) {
                this.constraintViolated(o, "Expecting a CONSTANT_Class operand, but found a '" + c + "'.");
            }
            else {
                final ConstantUtf8 cutf8 = (ConstantUtf8)this.cpg.getConstant(((ConstantClass)c).getNameIndex());
                final Type t = Type.getType("L" + cutf8.getBytes() + ";");
                if (t instanceof ArrayType) {
                    this.constraintViolated(o, "NEW must not be used to create an array.");
                }
            }
        }
        
        public void visitMULTIANEWARRAY(final MULTIANEWARRAY o) {
            this.indexValid(o, o.getIndex());
            final Constant c = this.cpg.getConstant(o.getIndex());
            if (!(c instanceof ConstantClass)) {
                this.constraintViolated(o, "Expecting a CONSTANT_Class operand, but found a '" + c + "'.");
            }
            final int dimensions2create = o.getDimensions();
            if (dimensions2create < 1) {
                this.constraintViolated(o, "Number of dimensions to create must be greater than zero.");
            }
            final Type t = o.getType(this.cpg);
            if (t instanceof ArrayType) {
                final int dimensions = ((ArrayType)t).getDimensions();
                if (dimensions < dimensions2create) {
                    this.constraintViolated(o, "Not allowed to create array with more dimensions ('+dimensions2create+') than the one referenced by the CONSTANT_Class '" + t + "'.");
                }
            }
            else {
                this.constraintViolated(o, "Expecting a CONSTANT_Class referencing an array type. [Constraint not found in The Java Virtual Machine Specification, Second Edition, 4.8.1]");
            }
        }
        
        public void visitANEWARRAY(final ANEWARRAY o) {
            this.indexValid(o, o.getIndex());
            final Constant c = this.cpg.getConstant(o.getIndex());
            if (!(c instanceof ConstantClass)) {
                this.constraintViolated(o, "Expecting a CONSTANT_Class operand, but found a '" + c + "'.");
            }
            final Type t = o.getType(this.cpg);
            if (t instanceof ArrayType) {
                final int dimensions = ((ArrayType)t).getDimensions();
                if (dimensions >= 255) {
                    this.constraintViolated(o, "Not allowed to create an array with more than 255 dimensions.");
                }
            }
        }
        
        public void visitNEWARRAY(final NEWARRAY o) {
            final byte t = o.getTypecode();
            if (t != 4 && t != 5 && t != 6 && t != 7 && t != 8 && t != 9 && t != 10 && t != 11) {
                this.constraintViolated(o, "Illegal type code '+t+' for 'atype' operand.");
            }
        }
        
        public void visitILOAD(final ILOAD o) {
            final int idx = o.getIndex();
            if (idx < 0) {
                this.constraintViolated(o, "Index '" + idx + "' must be non-negative.");
            }
            else {
                final int maxminus1 = this.max_locals() - 1;
                if (idx > maxminus1) {
                    this.constraintViolated(o, "Index '" + idx + "' must not be greater than max_locals-1 '" + maxminus1 + "'.");
                }
            }
        }
        
        public void visitFLOAD(final FLOAD o) {
            final int idx = o.getIndex();
            if (idx < 0) {
                this.constraintViolated(o, "Index '" + idx + "' must be non-negative.");
            }
            else {
                final int maxminus1 = this.max_locals() - 1;
                if (idx > maxminus1) {
                    this.constraintViolated(o, "Index '" + idx + "' must not be greater than max_locals-1 '" + maxminus1 + "'.");
                }
            }
        }
        
        public void visitALOAD(final ALOAD o) {
            final int idx = o.getIndex();
            if (idx < 0) {
                this.constraintViolated(o, "Index '" + idx + "' must be non-negative.");
            }
            else {
                final int maxminus1 = this.max_locals() - 1;
                if (idx > maxminus1) {
                    this.constraintViolated(o, "Index '" + idx + "' must not be greater than max_locals-1 '" + maxminus1 + "'.");
                }
            }
        }
        
        public void visitISTORE(final ISTORE o) {
            final int idx = o.getIndex();
            if (idx < 0) {
                this.constraintViolated(o, "Index '" + idx + "' must be non-negative.");
            }
            else {
                final int maxminus1 = this.max_locals() - 1;
                if (idx > maxminus1) {
                    this.constraintViolated(o, "Index '" + idx + "' must not be greater than max_locals-1 '" + maxminus1 + "'.");
                }
            }
        }
        
        public void visitFSTORE(final FSTORE o) {
            final int idx = o.getIndex();
            if (idx < 0) {
                this.constraintViolated(o, "Index '" + idx + "' must be non-negative.");
            }
            else {
                final int maxminus1 = this.max_locals() - 1;
                if (idx > maxminus1) {
                    this.constraintViolated(o, "Index '" + idx + "' must not be greater than max_locals-1 '" + maxminus1 + "'.");
                }
            }
        }
        
        public void visitASTORE(final ASTORE o) {
            final int idx = o.getIndex();
            if (idx < 0) {
                this.constraintViolated(o, "Index '" + idx + "' must be non-negative.");
            }
            else {
                final int maxminus1 = this.max_locals() - 1;
                if (idx > maxminus1) {
                    this.constraintViolated(o, "Index '" + idx + "' must not be greater than max_locals-1 '" + maxminus1 + "'.");
                }
            }
        }
        
        public void visitIINC(final IINC o) {
            final int idx = o.getIndex();
            if (idx < 0) {
                this.constraintViolated(o, "Index '" + idx + "' must be non-negative.");
            }
            else {
                final int maxminus1 = this.max_locals() - 1;
                if (idx > maxminus1) {
                    this.constraintViolated(o, "Index '" + idx + "' must not be greater than max_locals-1 '" + maxminus1 + "'.");
                }
            }
        }
        
        public void visitRET(final RET o) {
            final int idx = o.getIndex();
            if (idx < 0) {
                this.constraintViolated(o, "Index '" + idx + "' must be non-negative.");
            }
            else {
                final int maxminus1 = this.max_locals() - 1;
                if (idx > maxminus1) {
                    this.constraintViolated(o, "Index '" + idx + "' must not be greater than max_locals-1 '" + maxminus1 + "'.");
                }
            }
        }
        
        public void visitLLOAD(final LLOAD o) {
            final int idx = o.getIndex();
            if (idx < 0) {
                this.constraintViolated(o, "Index '" + idx + "' must be non-negative. [Constraint by JustIce as an analogon to the single-slot xLOAD/xSTORE instructions; may not happen anyway.]");
            }
            else {
                final int maxminus2 = this.max_locals() - 2;
                if (idx > maxminus2) {
                    this.constraintViolated(o, "Index '" + idx + "' must not be greater than max_locals-2 '" + maxminus2 + "'.");
                }
            }
        }
        
        public void visitDLOAD(final DLOAD o) {
            final int idx = o.getIndex();
            if (idx < 0) {
                this.constraintViolated(o, "Index '" + idx + "' must be non-negative. [Constraint by JustIce as an analogon to the single-slot xLOAD/xSTORE instructions; may not happen anyway.]");
            }
            else {
                final int maxminus2 = this.max_locals() - 2;
                if (idx > maxminus2) {
                    this.constraintViolated(o, "Index '" + idx + "' must not be greater than max_locals-2 '" + maxminus2 + "'.");
                }
            }
        }
        
        public void visitLSTORE(final LSTORE o) {
            final int idx = o.getIndex();
            if (idx < 0) {
                this.constraintViolated(o, "Index '" + idx + "' must be non-negative. [Constraint by JustIce as an analogon to the single-slot xLOAD/xSTORE instructions; may not happen anyway.]");
            }
            else {
                final int maxminus2 = this.max_locals() - 2;
                if (idx > maxminus2) {
                    this.constraintViolated(o, "Index '" + idx + "' must not be greater than max_locals-2 '" + maxminus2 + "'.");
                }
            }
        }
        
        public void visitDSTORE(final DSTORE o) {
            final int idx = o.getIndex();
            if (idx < 0) {
                this.constraintViolated(o, "Index '" + idx + "' must be non-negative. [Constraint by JustIce as an analogon to the single-slot xLOAD/xSTORE instructions; may not happen anyway.]");
            }
            else {
                final int maxminus2 = this.max_locals() - 2;
                if (idx > maxminus2) {
                    this.constraintViolated(o, "Index '" + idx + "' must not be greater than max_locals-2 '" + maxminus2 + "'.");
                }
            }
        }
        
        public void visitLOOKUPSWITCH(final LOOKUPSWITCH o) {
            final int[] matchs = o.getMatchs();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < matchs.length; ++i) {
                if (matchs[i] == max && i != 0) {
                    this.constraintViolated(o, "Match '" + matchs[i] + "' occurs more than once.");
                }
                if (matchs[i] < max) {
                    this.constraintViolated(o, "Lookup table must be sorted but isn't.");
                }
                else {
                    max = matchs[i];
                }
            }
        }
        
        public void visitTABLESWITCH(final TABLESWITCH o) {
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
            if (f.isFinal() && !Pass3aVerifier.this.myOwner.getClassName().equals(o.getClassType(this.cpg).getClassName())) {
                this.constraintViolated(o, "Referenced field '" + f + "' is final and must therefore be declared in the current class '" + Pass3aVerifier.this.myOwner.getClassName() + "' which is not the case: it is declared in '" + o.getClassType(this.cpg).getClassName() + "'.");
            }
            if (!f.isStatic()) {
                this.constraintViolated(o, "Referenced field '" + f + "' is not static which it should be.");
            }
            final String meth_name = Repository.lookupClass(Pass3aVerifier.this.myOwner.getClassName()).getMethods()[Pass3aVerifier.this.method_no].getName();
            if (!jc.isClass() && !meth_name.equals("<clinit>")) {
                this.constraintViolated(o, "Interface field '" + f + "' must be set in a '" + "<clinit>" + "' method.");
            }
        }
        
        public void visitGETSTATIC(final GETSTATIC o) {
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
            if (!f.isStatic()) {
                this.constraintViolated(o, "Referenced field '" + f + "' is not static which it should be.");
            }
        }
        
        public void visitINVOKEINTERFACE(final INVOKEINTERFACE o) {
            final String classname = o.getClassName(this.cpg);
            final JavaClass jc = Repository.lookupClass(classname);
            final Method[] ms = jc.getMethods();
            Method m = null;
            for (int i = 0; i < ms.length; ++i) {
                if (ms[i].getName().equals(o.getMethodName(this.cpg)) && Type.getReturnType(ms[i].getSignature()).equals(o.getReturnType(this.cpg)) && this.objarrayequals(Type.getArgumentTypes(ms[i].getSignature()), o.getArgumentTypes(this.cpg))) {
                    m = ms[i];
                    break;
                }
            }
            if (m == null) {
                this.constraintViolated(o, "Referenced method '" + o.getMethodName(this.cpg) + "' with expected signature not found in class '" + jc.getClassName() + "'. The native verfier does allow the method to be declared in some superinterface, which the Java Virtual Machine Specification, Second Edition does not.");
            }
            if (jc.isClass()) {
                this.constraintViolated(o, "Referenced class '" + jc.getClassName() + "' is a class, but not an interface as expected.");
            }
        }
        
        public void visitINVOKESPECIAL(final INVOKESPECIAL o) {
            final String classname = o.getClassName(this.cpg);
            final JavaClass jc = Repository.lookupClass(classname);
            final Method[] ms = jc.getMethods();
            Method m = null;
            for (int i = 0; i < ms.length; ++i) {
                if (ms[i].getName().equals(o.getMethodName(this.cpg)) && Type.getReturnType(ms[i].getSignature()).equals(o.getReturnType(this.cpg)) && this.objarrayequals(Type.getArgumentTypes(ms[i].getSignature()), o.getArgumentTypes(this.cpg))) {
                    m = ms[i];
                    break;
                }
            }
            if (m == null) {
                this.constraintViolated(o, "Referenced method '" + o.getMethodName(this.cpg) + "' with expected signature not found in class '" + jc.getClassName() + "'. The native verfier does allow the method to be declared in some superclass or implemented interface, which the Java Virtual Machine Specification, Second Edition does not.");
            }
            JavaClass current = Repository.lookupClass(Pass3aVerifier.this.myOwner.getClassName());
            if (current.isSuper() && Repository.instanceOf(current, jc) && !current.equals(jc) && !o.getMethodName(this.cpg).equals("<init>")) {
                int supidx = -1;
                Method meth = null;
                while (supidx != 0) {
                    supidx = current.getSuperclassNameIndex();
                    current = Repository.lookupClass(current.getSuperclassName());
                    final Method[] meths = current.getMethods();
                    for (int j = 0; j < meths.length; ++j) {
                        if (meths[j].getName().equals(o.getMethodName(this.cpg)) && Type.getReturnType(meths[j].getSignature()).equals(o.getReturnType(this.cpg)) && this.objarrayequals(Type.getArgumentTypes(meths[j].getSignature()), o.getArgumentTypes(this.cpg))) {
                            meth = meths[j];
                            break;
                        }
                    }
                    if (meth != null) {
                        break;
                    }
                }
                if (meth == null) {
                    this.constraintViolated(o, "ACC_SUPER special lookup procedure not successful: method '" + o.getMethodName(this.cpg) + "' with proper signature not declared in superclass hierarchy.");
                }
            }
        }
        
        public void visitINVOKESTATIC(final INVOKESTATIC o) {
            final String classname = o.getClassName(this.cpg);
            final JavaClass jc = Repository.lookupClass(classname);
            final Method[] ms = jc.getMethods();
            Method m = null;
            for (int i = 0; i < ms.length; ++i) {
                if (ms[i].getName().equals(o.getMethodName(this.cpg)) && Type.getReturnType(ms[i].getSignature()).equals(o.getReturnType(this.cpg)) && this.objarrayequals(Type.getArgumentTypes(ms[i].getSignature()), o.getArgumentTypes(this.cpg))) {
                    m = ms[i];
                    break;
                }
            }
            if (m == null) {
                this.constraintViolated(o, "Referenced method '" + o.getMethodName(this.cpg) + "' with expected signature not found in class '" + jc.getClassName() + "'. The native verifier possibly allows the method to be declared in some superclass or implemented interface, which the Java Virtual Machine Specification, Second Edition does not.");
            }
            if (!m.isStatic()) {
                this.constraintViolated(o, "Referenced method '" + o.getMethodName(this.cpg) + "' has ACC_STATIC unset.");
            }
        }
        
        public void visitINVOKEVIRTUAL(final INVOKEVIRTUAL o) {
            final String classname = o.getClassName(this.cpg);
            final JavaClass jc = Repository.lookupClass(classname);
            final Method[] ms = jc.getMethods();
            Method m = null;
            for (int i = 0; i < ms.length; ++i) {
                if (ms[i].getName().equals(o.getMethodName(this.cpg)) && Type.getReturnType(ms[i].getSignature()).equals(o.getReturnType(this.cpg)) && this.objarrayequals(Type.getArgumentTypes(ms[i].getSignature()), o.getArgumentTypes(this.cpg))) {
                    m = ms[i];
                    break;
                }
            }
            if (m == null) {
                this.constraintViolated(o, "Referenced method '" + o.getMethodName(this.cpg) + "' with expected signature not found in class '" + jc.getClassName() + "'. The native verfier does allow the method to be declared in some superclass or implemented interface, which the Java Virtual Machine Specification, Second Edition does not.");
            }
            if (!jc.isClass()) {
                this.constraintViolated(o, "Referenced class '" + jc.getClassName() + "' is an interface, but not a class as expected.");
            }
        }
        
        private boolean objarrayequals(final Object[] o, final Object[] p) {
            if (o.length != p.length) {
                return false;
            }
            for (int i = 0; i < o.length; ++i) {
                if (!o[i].equals(p[i])) {
                    return false;
                }
            }
            return true;
        }
    }
}
