// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public class InstructionFactory implements InstructionConstants
{
    protected ClassGen cg;
    protected ConstantPoolGen cp;
    private static MethodObject[] append_mos;
    
    static {
        InstructionFactory.append_mos = new MethodObject[] { new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.STRING }, 1), new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.OBJECT }, 1), null, null, new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.BOOLEAN }, 1), new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.CHAR }, 1), new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.FLOAT }, 1), new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.DOUBLE }, 1), new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.INT }, 1), new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.INT }, 1), new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.INT }, 1), new MethodObject("java.lang.StringBuffer", "append", Type.STRINGBUFFER, new Type[] { Type.LONG }, 1) };
    }
    
    public InstructionFactory(final ClassGen cg, final ConstantPoolGen cp) {
        this.cg = cg;
        this.cp = cp;
    }
    
    public InstructionFactory(final ClassGen cg) {
        this(cg, cg.getConstantPool());
    }
    
    public InstructionFactory(final ConstantPoolGen cp) {
        this(null, cp);
    }
    
    public InvokeInstruction createInvoke(final String class_name, final String name, final Type ret_type, final Type[] arg_types, final short kind) {
        int nargs = 0;
        final String signature = Type.getMethodSignature(ret_type, arg_types);
        for (int i = 0; i < arg_types.length; ++i) {
            nargs += arg_types[i].getSize();
        }
        int index;
        if (kind == 185) {
            index = this.cp.addInterfaceMethodref(class_name, name, signature);
        }
        else {
            index = this.cp.addMethodref(class_name, name, signature);
        }
        switch (kind) {
            case 183: {
                return new INVOKESPECIAL(index);
            }
            case 182: {
                return new INVOKEVIRTUAL(index);
            }
            case 184: {
                return new INVOKESTATIC(index);
            }
            case 185: {
                return new INVOKEINTERFACE(index, nargs + 1);
            }
            default: {
                throw new RuntimeException("Oops: Unknown invoke kind:" + kind);
            }
        }
    }
    
    public InstructionList createPrintln(final String s) {
        final InstructionList il = new InstructionList();
        final int out = this.cp.addFieldref("java.lang.System", "out", "Ljava/io/PrintStream;");
        final int println = this.cp.addMethodref("java.io.PrintStream", "println", "(Ljava/lang/String;)V");
        il.append(new GETSTATIC(out));
        il.append(new PUSH(this.cp, s));
        il.append(new INVOKEVIRTUAL(println));
        return il;
    }
    
    private InvokeInstruction createInvoke(final MethodObject m, final short kind) {
        return this.createInvoke(m.class_name, m.name, m.result_type, m.arg_types, kind);
    }
    
    private static final boolean isString(final Type type) {
        return type instanceof ObjectType && ((ObjectType)type).getClassName().equals("java.lang.String");
    }
    
    public Instruction createAppend(final Type type) {
        final byte t = type.getType();
        if (isString(type)) {
            return this.createInvoke(InstructionFactory.append_mos[0], (short)182);
        }
        switch (t) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11: {
                return this.createInvoke(InstructionFactory.append_mos[t], (short)182);
            }
            case 13:
            case 14: {
                return this.createInvoke(InstructionFactory.append_mos[1], (short)182);
            }
            default: {
                throw new RuntimeException("Oops: No append for this type? " + type);
            }
        }
    }
    
    public FieldInstruction createFieldAccess(final String class_name, final String name, final Type type, final short kind) {
        final String signature = type.getSignature();
        final int index = this.cp.addFieldref(class_name, name, signature);
        switch (kind) {
            case 180: {
                return new GETFIELD(index);
            }
            case 181: {
                return new PUTFIELD(index);
            }
            case 178: {
                return new GETSTATIC(index);
            }
            case 179: {
                return new PUTSTATIC(index);
            }
            default: {
                throw new RuntimeException("Oops: Unknown getfield kind:" + kind);
            }
        }
    }
    
    public static Instruction createThis() {
        return new ALOAD(0);
    }
    
    public static ReturnInstruction createReturn(final Type type) {
        switch (type.getType()) {
            case 13:
            case 14: {
                return InstructionConstants.ARETURN;
            }
            case 4:
            case 5:
            case 8:
            case 9:
            case 10: {
                return InstructionConstants.IRETURN;
            }
            case 6: {
                return InstructionConstants.FRETURN;
            }
            case 7: {
                return InstructionConstants.DRETURN;
            }
            case 11: {
                return InstructionConstants.LRETURN;
            }
            case 12: {
                return InstructionConstants.RETURN;
            }
            default: {
                throw new RuntimeException("Invalid type: " + type);
            }
        }
    }
    
    private static final ArithmeticInstruction createBinaryIntOp(final char first, final String op) {
        switch (first) {
            case '-': {
                return InstructionConstants.ISUB;
            }
            case '+': {
                return InstructionConstants.IADD;
            }
            case '%': {
                return InstructionConstants.IREM;
            }
            case '*': {
                return InstructionConstants.IMUL;
            }
            case '/': {
                return InstructionConstants.IDIV;
            }
            case '&': {
                return InstructionConstants.IAND;
            }
            case '|': {
                return InstructionConstants.IOR;
            }
            case '^': {
                return InstructionConstants.IXOR;
            }
            case '<': {
                return InstructionConstants.ISHL;
            }
            case '>': {
                return op.equals(">>>") ? InstructionConstants.IUSHR : InstructionConstants.ISHR;
            }
            default: {
                throw new RuntimeException("Invalid operand " + op);
            }
        }
    }
    
    private static final ArithmeticInstruction createBinaryLongOp(final char first, final String op) {
        switch (first) {
            case '-': {
                return InstructionConstants.LSUB;
            }
            case '+': {
                return InstructionConstants.LADD;
            }
            case '%': {
                return InstructionConstants.LREM;
            }
            case '*': {
                return InstructionConstants.LMUL;
            }
            case '/': {
                return InstructionConstants.LDIV;
            }
            case '&': {
                return InstructionConstants.LAND;
            }
            case '|': {
                return InstructionConstants.LOR;
            }
            case '^': {
                return InstructionConstants.LXOR;
            }
            case '<': {
                return InstructionConstants.LSHL;
            }
            case '>': {
                return op.equals(">>>") ? InstructionConstants.LUSHR : InstructionConstants.LSHR;
            }
            default: {
                throw new RuntimeException("Invalid operand " + op);
            }
        }
    }
    
    private static final ArithmeticInstruction createBinaryFloatOp(final char op) {
        switch (op) {
            case '-': {
                return InstructionConstants.FSUB;
            }
            case '+': {
                return InstructionConstants.FADD;
            }
            case '*': {
                return InstructionConstants.FMUL;
            }
            case '/': {
                return InstructionConstants.FDIV;
            }
            default: {
                throw new RuntimeException("Invalid operand " + op);
            }
        }
    }
    
    private static final ArithmeticInstruction createBinaryDoubleOp(final char op) {
        switch (op) {
            case '-': {
                return InstructionConstants.DSUB;
            }
            case '+': {
                return InstructionConstants.DADD;
            }
            case '*': {
                return InstructionConstants.DMUL;
            }
            case '/': {
                return InstructionConstants.DDIV;
            }
            default: {
                throw new RuntimeException("Invalid operand " + op);
            }
        }
    }
    
    public static ArithmeticInstruction createBinaryOperation(final String op, final Type type) {
        final char first = op.toCharArray()[0];
        switch (type.getType()) {
            case 5:
            case 8:
            case 9:
            case 10: {
                return createBinaryIntOp(first, op);
            }
            case 11: {
                return createBinaryLongOp(first, op);
            }
            case 6: {
                return createBinaryFloatOp(first);
            }
            case 7: {
                return createBinaryDoubleOp(first);
            }
            default: {
                throw new RuntimeException("Invalid type " + type);
            }
        }
    }
    
    public static StackInstruction createPop(final int size) {
        return (size == 2) ? InstructionConstants.POP2 : InstructionConstants.POP;
    }
    
    public static StackInstruction createDup(final int size) {
        return (size == 2) ? InstructionConstants.DUP2 : InstructionConstants.DUP;
    }
    
    public static StackInstruction createDup_2(final int size) {
        return (size == 2) ? InstructionConstants.DUP2_X2 : InstructionConstants.DUP_X2;
    }
    
    public static StackInstruction createDup_1(final int size) {
        return (size == 2) ? InstructionConstants.DUP2_X1 : InstructionConstants.DUP_X1;
    }
    
    public static LocalVariableInstruction createStore(final Type type, final int index) {
        switch (type.getType()) {
            case 4:
            case 5:
            case 8:
            case 9:
            case 10: {
                return new ISTORE(index);
            }
            case 6: {
                return new FSTORE(index);
            }
            case 7: {
                return new DSTORE(index);
            }
            case 11: {
                return new LSTORE(index);
            }
            case 13:
            case 14: {
                return new ASTORE(index);
            }
            default: {
                throw new RuntimeException("Invalid type " + type);
            }
        }
    }
    
    public static LocalVariableInstruction createLoad(final Type type, final int index) {
        switch (type.getType()) {
            case 4:
            case 5:
            case 8:
            case 9:
            case 10: {
                return new ILOAD(index);
            }
            case 6: {
                return new FLOAD(index);
            }
            case 7: {
                return new DLOAD(index);
            }
            case 11: {
                return new LLOAD(index);
            }
            case 13:
            case 14: {
                return new ALOAD(index);
            }
            default: {
                throw new RuntimeException("Invalid type " + type);
            }
        }
    }
    
    public static ArrayInstruction createArrayLoad(final Type type) {
        switch (type.getType()) {
            case 4:
            case 8: {
                return InstructionConstants.BALOAD;
            }
            case 5: {
                return InstructionConstants.CALOAD;
            }
            case 9: {
                return InstructionConstants.SALOAD;
            }
            case 10: {
                return InstructionConstants.IALOAD;
            }
            case 6: {
                return InstructionConstants.FALOAD;
            }
            case 7: {
                return InstructionConstants.DALOAD;
            }
            case 11: {
                return InstructionConstants.LALOAD;
            }
            case 13:
            case 14: {
                return InstructionConstants.AALOAD;
            }
            default: {
                throw new RuntimeException("Invalid type " + type);
            }
        }
    }
    
    public static ArrayInstruction createArrayStore(final Type type) {
        switch (type.getType()) {
            case 4:
            case 8: {
                return InstructionConstants.BASTORE;
            }
            case 5: {
                return InstructionConstants.CASTORE;
            }
            case 9: {
                return InstructionConstants.SASTORE;
            }
            case 10: {
                return InstructionConstants.IASTORE;
            }
            case 6: {
                return InstructionConstants.FASTORE;
            }
            case 7: {
                return InstructionConstants.DASTORE;
            }
            case 11: {
                return InstructionConstants.LASTORE;
            }
            case 13:
            case 14: {
                return InstructionConstants.AASTORE;
            }
            default: {
                throw new RuntimeException("Invalid type " + type);
            }
        }
    }
    
    public Instruction createCast(final Type src_type, final Type dest_type) {
        if (src_type instanceof BasicType && dest_type instanceof BasicType) {
            final byte dest = dest_type.getType();
            byte src = src_type.getType();
            if (dest == 11 && (src == 5 || src == 8 || src == 9)) {
                src = 10;
            }
            final String[] short_names = { "C", "F", "D", "B", "S", "I", "L" };
            final String name = "com.ibm.xslt4j.bcel.generic." + short_names[src - 5] + "2" + short_names[dest - 5];
            Instruction i = null;
            try {
                i = (Instruction)Class.forName(name).newInstance();
            }
            catch (Exception e) {
                throw new RuntimeException("Could not find instruction: " + name);
            }
            return i;
        }
        if (!(src_type instanceof ReferenceType) || !(dest_type instanceof ReferenceType)) {
            throw new RuntimeException("Can not cast " + src_type + " to " + dest_type);
        }
        if (dest_type instanceof ArrayType) {
            return new CHECKCAST(this.cp.addArrayClass((ArrayType)dest_type));
        }
        return new CHECKCAST(this.cp.addClass(((ObjectType)dest_type).getClassName()));
    }
    
    public GETFIELD createGetField(final String class_name, final String name, final Type t) {
        return new GETFIELD(this.cp.addFieldref(class_name, name, t.getSignature()));
    }
    
    public GETSTATIC createGetStatic(final String class_name, final String name, final Type t) {
        return new GETSTATIC(this.cp.addFieldref(class_name, name, t.getSignature()));
    }
    
    public PUTFIELD createPutField(final String class_name, final String name, final Type t) {
        return new PUTFIELD(this.cp.addFieldref(class_name, name, t.getSignature()));
    }
    
    public PUTSTATIC createPutStatic(final String class_name, final String name, final Type t) {
        return new PUTSTATIC(this.cp.addFieldref(class_name, name, t.getSignature()));
    }
    
    public CHECKCAST createCheckCast(final ReferenceType t) {
        if (t instanceof ArrayType) {
            return new CHECKCAST(this.cp.addArrayClass((ArrayType)t));
        }
        return new CHECKCAST(this.cp.addClass((ObjectType)t));
    }
    
    public NEW createNew(final ObjectType t) {
        return new NEW(this.cp.addClass(t));
    }
    
    public NEW createNew(final String s) {
        return this.createNew(new ObjectType(s));
    }
    
    public AllocationInstruction createNewArray(final Type t, final short dim) {
        if (dim != 1) {
            ArrayType at;
            if (t instanceof ArrayType) {
                at = (ArrayType)t;
            }
            else {
                at = new ArrayType(t, dim);
            }
            return new MULTIANEWARRAY(this.cp.addArrayClass(at), dim);
        }
        if (t instanceof ObjectType) {
            return new ANEWARRAY(this.cp.addClass((ObjectType)t));
        }
        if (t instanceof ArrayType) {
            return new ANEWARRAY(this.cp.addArrayClass((ArrayType)t));
        }
        return new NEWARRAY(((BasicType)t).getType());
    }
    
    public static Instruction createNull(final Type type) {
        switch (type.getType()) {
            case 13:
            case 14: {
                return InstructionConstants.ACONST_NULL;
            }
            case 4:
            case 5:
            case 8:
            case 9:
            case 10: {
                return InstructionConstants.ICONST_0;
            }
            case 6: {
                return InstructionConstants.FCONST_0;
            }
            case 7: {
                return InstructionConstants.DCONST_0;
            }
            case 11: {
                return InstructionConstants.LCONST_0;
            }
            case 12: {
                return InstructionConstants.NOP;
            }
            default: {
                throw new RuntimeException("Invalid type: " + type);
            }
        }
    }
    
    public static BranchInstruction createBranchInstruction(final short opcode, final InstructionHandle target) {
        switch (opcode) {
            case 153: {
                return new IFEQ(target);
            }
            case 154: {
                return new IFNE(target);
            }
            case 155: {
                return new IFLT(target);
            }
            case 156: {
                return new IFGE(target);
            }
            case 157: {
                return new IFGT(target);
            }
            case 158: {
                return new IFLE(target);
            }
            case 159: {
                return new IF_ICMPEQ(target);
            }
            case 160: {
                return new IF_ICMPNE(target);
            }
            case 161: {
                return new IF_ICMPLT(target);
            }
            case 162: {
                return new IF_ICMPGE(target);
            }
            case 163: {
                return new IF_ICMPGT(target);
            }
            case 164: {
                return new IF_ICMPLE(target);
            }
            case 165: {
                return new IF_ACMPEQ(target);
            }
            case 166: {
                return new IF_ACMPNE(target);
            }
            case 167: {
                return new GOTO(target);
            }
            case 168: {
                return new JSR(target);
            }
            case 198: {
                return new IFNULL(target);
            }
            case 199: {
                return new IFNONNULL(target);
            }
            case 200: {
                return new GOTO_W(target);
            }
            case 201: {
                return new JSR_W(target);
            }
            default: {
                throw new RuntimeException("Invalid opcode: " + opcode);
            }
        }
    }
    
    public void setClassGen(final ClassGen c) {
        this.cg = c;
    }
    
    public ClassGen getClassGen() {
        return this.cg;
    }
    
    public void setConstantPool(final ConstantPoolGen c) {
        this.cp = c;
    }
    
    public ConstantPoolGen getConstantPool() {
        return this.cp;
    }
    
    private static class MethodObject
    {
        Type[] arg_types;
        Type result_type;
        String[] arg_names;
        String class_name;
        String name;
        int access;
        
        MethodObject(final String c, final String n, final Type r, final Type[] a, final int acc) {
            this.class_name = c;
            this.name = n;
            this.result_type = r;
            this.arg_types = a;
            this.access = acc;
        }
    }
}
