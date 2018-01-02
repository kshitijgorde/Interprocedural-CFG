// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

import java.util.Hashtable;
import java.util.Stack;
import org.apache.bcel.classfile.Utility;
import java.util.Iterator;
import org.apache.bcel.classfile.LocalVariable;
import org.apache.bcel.classfile.LineNumber;
import org.apache.bcel.classfile.CodeException;
import org.apache.bcel.classfile.Attribute;
import org.apache.bcel.classfile.ExceptionTable;
import org.apache.bcel.classfile.LocalVariableTable;
import org.apache.bcel.classfile.LineNumberTable;
import org.apache.bcel.classfile.Code;
import org.apache.bcel.classfile.Method;
import java.util.ArrayList;

public class MethodGen extends FieldGenOrMethodGen
{
    private String class_name;
    private Type[] arg_types;
    private String[] arg_names;
    private int max_locals;
    private int max_stack;
    private InstructionList il;
    private boolean strip_attributes;
    private ArrayList variable_vec;
    private ArrayList line_number_vec;
    private ArrayList exception_vec;
    private ArrayList throws_vec;
    private ArrayList code_attrs_vec;
    private ArrayList observers;
    
    public MethodGen(final int access_flags, final Type return_type, final Type[] arg_types, String[] arg_names, final String method_name, final String class_name, final InstructionList il, final ConstantPoolGen cp) {
        this.variable_vec = new ArrayList();
        this.line_number_vec = new ArrayList();
        this.exception_vec = new ArrayList();
        this.throws_vec = new ArrayList();
        this.code_attrs_vec = new ArrayList();
        this.setAccessFlags(access_flags);
        this.setType(return_type);
        this.setArgumentTypes(arg_types);
        this.setArgumentNames(arg_names);
        this.setName(method_name);
        this.setClassName(class_name);
        this.setInstructionList(il);
        this.setConstantPool(cp);
        if ((access_flags & 0x500) == 0x0) {
            final InstructionHandle start = il.getStart();
            final InstructionHandle end = il.getEnd();
            if (!this.isStatic() && class_name != null) {
                this.addLocalVariable("this", new ObjectType(class_name), start, end);
            }
            if (arg_types != null) {
                final int size = arg_types.length;
                if (arg_names != null) {
                    if (size != arg_names.length) {
                        throw new ClassGenException("Mismatch in argument array lengths: " + size + " vs. " + arg_names.length);
                    }
                }
                else {
                    arg_names = new String[size];
                    for (int i = 0; i < size; ++i) {
                        arg_names[i] = "arg" + i;
                    }
                    this.setArgumentNames(arg_names);
                }
                for (int i = 0; i < size; ++i) {
                    this.addLocalVariable(arg_names[i], arg_types[i], start, end);
                }
            }
        }
    }
    
    public MethodGen(final Method m, final String class_name, final ConstantPoolGen cp) {
        this(m.getAccessFlags(), Type.getReturnType(m.getSignature()), Type.getArgumentTypes(m.getSignature()), null, m.getName(), class_name, ((m.getAccessFlags() & 0x500) == 0x0) ? new InstructionList(m.getCode().getCode()) : null, cp);
        final Attribute[] attributes = m.getAttributes();
        for (int i = 0; i < attributes.length; ++i) {
            Attribute a = attributes[i];
            if (a instanceof Code) {
                final Code c = (Code)a;
                this.setMaxStack(c.getMaxStack());
                this.setMaxLocals(c.getMaxLocals());
                final CodeException[] ces = c.getExceptionTable();
                if (ces != null) {
                    for (int j = 0; j < ces.length; ++j) {
                        final CodeException ce = ces[j];
                        final int type = ce.getCatchType();
                        ObjectType c_type = null;
                        if (type > 0) {
                            final String cen = m.getConstantPool().getConstantString(type, (byte)7);
                            c_type = new ObjectType(cen);
                        }
                        final int end_pc = ce.getEndPC();
                        final int length = m.getCode().getCode().length;
                        InstructionHandle end;
                        if (length == end_pc) {
                            end = this.il.getEnd();
                        }
                        else {
                            end = this.il.findHandle(end_pc);
                            end = end.getPrev();
                        }
                        this.addExceptionHandler(this.il.findHandle(ce.getStartPC()), end, this.il.findHandle(ce.getHandlerPC()), c_type);
                    }
                }
                final Attribute[] c_attributes = c.getAttributes();
                for (int k = 0; k < c_attributes.length; ++k) {
                    a = c_attributes[k];
                    if (a instanceof LineNumberTable) {
                        final LineNumber[] ln = ((LineNumberTable)a).getLineNumberTable();
                        for (int l = 0; l < ln.length; ++l) {
                            final LineNumber l2 = ln[l];
                            this.addLineNumber(this.il.findHandle(l2.getStartPC()), l2.getLineNumber());
                        }
                    }
                    else if (a instanceof LocalVariableTable) {
                        final LocalVariable[] lv = ((LocalVariableTable)a).getLocalVariableTable();
                        for (int l = 0; l < lv.length; ++l) {
                            final LocalVariable l3 = lv[l];
                            InstructionHandle start = this.il.findHandle(l3.getStartPC());
                            InstructionHandle end = this.il.findHandle(l3.getStartPC() + l3.getLength());
                            if (start == null) {
                                start = this.il.getStart();
                            }
                            if (end == null) {
                                end = this.il.getEnd();
                            }
                            this.addLocalVariable(l3.getName(), Type.getType(l3.getSignature()), l3.getIndex(), start, end);
                        }
                    }
                    else {
                        this.addCodeAttribute(a);
                    }
                }
            }
            else if (a instanceof ExceptionTable) {
                final String[] names = ((ExceptionTable)a).getExceptionNames();
                for (int j2 = 0; j2 < names.length; ++j2) {
                    this.addException(names[j2]);
                }
            }
            else {
                this.addAttribute(a);
            }
        }
    }
    
    public LocalVariableGen addLocalVariable(final String name, final Type type, final int slot, final InstructionHandle start, final InstructionHandle end) {
        final byte t = type.getType();
        final int add = type.getSize();
        if (slot + add > this.max_locals) {
            this.max_locals = slot + add;
        }
        final LocalVariableGen l = new LocalVariableGen(slot, name, type, start, end);
        final int i;
        if ((i = this.variable_vec.indexOf(l)) >= 0) {
            this.variable_vec.set(i, l);
        }
        else {
            this.variable_vec.add(l);
        }
        return l;
    }
    
    public LocalVariableGen addLocalVariable(final String name, final Type type, final InstructionHandle start, final InstructionHandle end) {
        return this.addLocalVariable(name, type, this.max_locals, start, end);
    }
    
    public void removeLocalVariable(final LocalVariableGen l) {
        this.variable_vec.remove(l);
    }
    
    public void removeLocalVariables() {
        this.variable_vec.clear();
    }
    
    private static final void sort(final LocalVariableGen[] vars, final int l, final int r) {
        int i = l;
        int j = r;
        final int m = vars[(l + r) / 2].getIndex();
        while (true) {
            if (vars[i].getIndex() >= m) {
                while (m < vars[j].getIndex()) {
                    --j;
                }
                if (i <= j) {
                    final LocalVariableGen h = vars[i];
                    vars[i] = vars[j];
                    vars[j] = h;
                    ++i;
                    --j;
                }
                if (i > j) {
                    break;
                }
                continue;
            }
            else {
                ++i;
            }
        }
        if (l < j) {
            sort(vars, l, j);
        }
        if (i < r) {
            sort(vars, i, r);
        }
    }
    
    public LocalVariableGen[] getLocalVariables() {
        final int size = this.variable_vec.size();
        final LocalVariableGen[] lg = new LocalVariableGen[size];
        this.variable_vec.toArray(lg);
        for (int i = 0; i < size; ++i) {
            if (lg[i].getStart() == null) {
                lg[i].setStart(this.il.getStart());
            }
            if (lg[i].getEnd() == null) {
                lg[i].setEnd(this.il.getEnd());
            }
        }
        if (size > 1) {
            sort(lg, 0, size - 1);
        }
        return lg;
    }
    
    public LocalVariableTable getLocalVariableTable(final ConstantPoolGen cp) {
        final LocalVariableGen[] lg = this.getLocalVariables();
        final int size = lg.length;
        final LocalVariable[] lv = new LocalVariable[size];
        for (int i = 0; i < size; ++i) {
            lv[i] = lg[i].getLocalVariable(cp);
        }
        return new LocalVariableTable(cp.addUtf8("LocalVariableTable"), 2 + lv.length * 10, lv, cp.getConstantPool());
    }
    
    public LineNumberGen addLineNumber(final InstructionHandle ih, final int src_line) {
        final LineNumberGen l = new LineNumberGen(ih, src_line);
        this.line_number_vec.add(l);
        return l;
    }
    
    public void removeLineNumber(final LineNumberGen l) {
        this.line_number_vec.remove(l);
    }
    
    public void removeLineNumbers() {
        this.line_number_vec.clear();
    }
    
    public LineNumberGen[] getLineNumbers() {
        final LineNumberGen[] lg = new LineNumberGen[this.line_number_vec.size()];
        this.line_number_vec.toArray(lg);
        return lg;
    }
    
    public LineNumberTable getLineNumberTable(final ConstantPoolGen cp) {
        final int size = this.line_number_vec.size();
        final LineNumber[] ln = new LineNumber[size];
        try {
            for (int i = 0; i < size; ++i) {
                ln[i] = this.line_number_vec.get(i).getLineNumber();
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {}
        return new LineNumberTable(cp.addUtf8("LineNumberTable"), 2 + ln.length * 4, ln, cp.getConstantPool());
    }
    
    public CodeExceptionGen addExceptionHandler(final InstructionHandle start_pc, final InstructionHandle end_pc, final InstructionHandle handler_pc, final ObjectType catch_type) {
        if (start_pc == null || end_pc == null || handler_pc == null) {
            throw new ClassGenException("Exception handler target is null instruction");
        }
        final CodeExceptionGen c = new CodeExceptionGen(start_pc, end_pc, handler_pc, catch_type);
        this.exception_vec.add(c);
        return c;
    }
    
    public void removeExceptionHandler(final CodeExceptionGen c) {
        this.exception_vec.remove(c);
    }
    
    public void removeExceptionHandlers() {
        this.exception_vec.clear();
    }
    
    public CodeExceptionGen[] getExceptionHandlers() {
        final CodeExceptionGen[] cg = new CodeExceptionGen[this.exception_vec.size()];
        this.exception_vec.toArray(cg);
        return cg;
    }
    
    private CodeException[] getCodeExceptions() {
        final int size = this.exception_vec.size();
        final CodeException[] c_exc = new CodeException[size];
        try {
            for (int i = 0; i < size; ++i) {
                final CodeExceptionGen c = this.exception_vec.get(i);
                c_exc[i] = c.getCodeException(super.cp);
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {}
        return c_exc;
    }
    
    public void addException(final String class_name) {
        this.throws_vec.add(class_name);
    }
    
    public void removeException(final String c) {
        this.throws_vec.remove(c);
    }
    
    public void removeExceptions() {
        this.throws_vec.clear();
    }
    
    public String[] getExceptions() {
        final String[] e = new String[this.throws_vec.size()];
        this.throws_vec.toArray(e);
        return e;
    }
    
    private ExceptionTable getExceptionTable(final ConstantPoolGen cp) {
        final int size = this.throws_vec.size();
        final int[] ex = new int[size];
        try {
            for (int i = 0; i < size; ++i) {
                ex[i] = cp.addClass(this.throws_vec.get(i));
            }
        }
        catch (ArrayIndexOutOfBoundsException ex2) {}
        return new ExceptionTable(cp.addUtf8("Exceptions"), 2 + 2 * size, ex, cp.getConstantPool());
    }
    
    public void addCodeAttribute(final Attribute a) {
        this.code_attrs_vec.add(a);
    }
    
    public void removeCodeAttribute(final Attribute a) {
        this.code_attrs_vec.remove(a);
    }
    
    public void removeCodeAttributes() {
        this.code_attrs_vec.clear();
    }
    
    public Attribute[] getCodeAttributes() {
        final Attribute[] attributes = new Attribute[this.code_attrs_vec.size()];
        this.code_attrs_vec.toArray(attributes);
        return attributes;
    }
    
    public Method getMethod() {
        final String signature = this.getSignature();
        final int name_index = super.cp.addUtf8(super.name);
        final int signature_index = super.cp.addUtf8(signature);
        byte[] byte_code = null;
        if (this.il != null) {
            byte_code = this.il.getByteCode();
        }
        LineNumberTable lnt = null;
        LocalVariableTable lvt = null;
        if (this.variable_vec.size() > 0 && !this.strip_attributes) {
            this.addCodeAttribute(lvt = this.getLocalVariableTable(super.cp));
        }
        if (this.line_number_vec.size() > 0 && !this.strip_attributes) {
            this.addCodeAttribute(lnt = this.getLineNumberTable(super.cp));
        }
        final Attribute[] code_attrs = this.getCodeAttributes();
        int attrs_len = 0;
        for (int i = 0; i < code_attrs.length; ++i) {
            attrs_len += code_attrs[i].getLength() + 6;
        }
        final CodeException[] c_exc = this.getCodeExceptions();
        final int exc_len = c_exc.length * 8;
        Code code = null;
        if (this.il != null && !this.isAbstract()) {
            code = new Code(super.cp.addUtf8("Code"), 8 + byte_code.length + 2 + exc_len + 2 + attrs_len, this.max_stack, this.max_locals, byte_code, c_exc, code_attrs, super.cp.getConstantPool());
            this.addAttribute(code);
        }
        ExceptionTable et = null;
        if (this.throws_vec.size() > 0) {
            this.addAttribute(et = this.getExceptionTable(super.cp));
        }
        final Method m = new Method(super.access_flags, name_index, signature_index, this.getAttributes(), super.cp.getConstantPool());
        if (lvt != null) {
            this.removeCodeAttribute(lvt);
        }
        if (lnt != null) {
            this.removeCodeAttribute(lnt);
        }
        if (code != null) {
            this.removeAttribute(code);
        }
        if (et != null) {
            this.removeAttribute(et);
        }
        return m;
    }
    
    public void removeNOPs() {
        if (this.il != null) {
            InstructionHandle next;
            for (InstructionHandle ih = this.il.getStart(); ih != null; ih = next) {
                next = ih.next;
                if (next != null && ih.getInstruction() instanceof NOP) {
                    try {
                        this.il.delete(ih);
                    }
                    catch (TargetLostException e) {
                        final InstructionHandle[] targets = e.getTargets();
                        for (int i = 0; i < targets.length; ++i) {
                            final InstructionTargeter[] targeters = targets[i].getTargeters();
                            for (int j = 0; j < targeters.length; ++j) {
                                targeters[j].updateTarget(targets[i], next);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void setMaxLocals(final int m) {
        this.max_locals = m;
    }
    
    public int getMaxLocals() {
        return this.max_locals;
    }
    
    public void setMaxStack(final int m) {
        this.max_stack = m;
    }
    
    public int getMaxStack() {
        return this.max_stack;
    }
    
    public String getClassName() {
        return this.class_name;
    }
    
    public void setClassName(final String class_name) {
        this.class_name = class_name;
    }
    
    public void setReturnType(final Type return_type) {
        this.setType(return_type);
    }
    
    public Type getReturnType() {
        return this.getType();
    }
    
    public void setArgumentTypes(final Type[] arg_types) {
        this.arg_types = arg_types;
    }
    
    public Type[] getArgumentTypes() {
        return this.arg_types.clone();
    }
    
    public void setArgumentType(final int i, final Type type) {
        this.arg_types[i] = type;
    }
    
    public Type getArgumentType(final int i) {
        return this.arg_types[i];
    }
    
    public void setArgumentNames(final String[] arg_names) {
        this.arg_names = arg_names;
    }
    
    public String[] getArgumentNames() {
        return this.arg_names.clone();
    }
    
    public void setArgumentName(final int i, final String name) {
        this.arg_names[i] = name;
    }
    
    public String getArgumentName(final int i) {
        return this.arg_names[i];
    }
    
    public InstructionList getInstructionList() {
        return this.il;
    }
    
    public void setInstructionList(final InstructionList il) {
        this.il = il;
    }
    
    public String getSignature() {
        return Type.getMethodSignature(super.type, this.arg_types);
    }
    
    public void setMaxStack() {
        if (this.il != null) {
            this.max_stack = getMaxStack(super.cp, this.il, this.getExceptionHandlers());
        }
        else {
            this.max_stack = 0;
        }
    }
    
    public void setMaxLocals() {
        if (this.il != null) {
            int max = this.isStatic() ? 0 : 1;
            if (this.arg_types != null) {
                for (int i = 0; i < this.arg_types.length; ++i) {
                    max += this.arg_types[i].getSize();
                }
            }
            for (InstructionHandle ih = this.il.getStart(); ih != null; ih = ih.getNext()) {
                final Instruction ins = ih.getInstruction();
                if (ins instanceof LocalVariableInstruction || ins instanceof RET || ins instanceof IINC) {
                    final int index = ((IndexedInstruction)ins).getIndex() + ((TypedInstruction)ins).getType(super.cp).getSize();
                    if (index > max) {
                        max = index;
                    }
                }
            }
            this.max_locals = max;
        }
        else {
            this.max_locals = 0;
        }
    }
    
    public void stripAttributes(final boolean flag) {
        this.strip_attributes = flag;
    }
    
    public static int getMaxStack(final ConstantPoolGen cp, final InstructionList il, final CodeExceptionGen[] et) {
        final BranchStack branchTargets = new BranchStack();
        for (int i = 0; i < et.length; ++i) {
            final InstructionHandle handler_pc = et[i].getHandlerPC();
            if (handler_pc != null) {
                branchTargets.push(handler_pc, 1);
            }
        }
        int stackDepth = 0;
        int maxStackDepth = 0;
        InstructionHandle ih = il.getStart();
        while (ih != null) {
            final Instruction instruction = ih.getInstruction();
            final short opcode = instruction.getOpcode();
            final int delta = instruction.produceStack(cp) - instruction.consumeStack(cp);
            stackDepth += delta;
            if (stackDepth > maxStackDepth) {
                maxStackDepth = stackDepth;
            }
            if (instruction instanceof BranchInstruction) {
                final BranchInstruction branch = (BranchInstruction)instruction;
                if (instruction instanceof Select) {
                    final Select select = (Select)branch;
                    final InstructionHandle[] targets = select.getTargets();
                    for (int j = 0; j < targets.length; ++j) {
                        branchTargets.push(targets[j], stackDepth);
                    }
                    ih = null;
                }
                else if (!(branch instanceof IfInstruction)) {
                    if (opcode == 168 || opcode == 201) {
                        branchTargets.push(ih.getNext(), stackDepth - 1);
                    }
                    ih = null;
                }
                branchTargets.push(branch.getTarget(), stackDepth);
            }
            else if (opcode == 191 || opcode == 169 || (opcode >= 172 && opcode <= 177)) {
                ih = null;
            }
            if (ih != null) {
                ih = ih.getNext();
            }
            if (ih == null) {
                final BranchTarget bt = branchTargets.pop();
                if (bt == null) {
                    continue;
                }
                ih = bt.target;
                stackDepth = bt.stackDepth;
            }
        }
        return maxStackDepth;
    }
    
    public void addObserver(final MethodObserver o) {
        if (this.observers == null) {
            this.observers = new ArrayList();
        }
        this.observers.add(o);
    }
    
    public void removeObserver(final MethodObserver o) {
        if (this.observers != null) {
            this.observers.remove(o);
        }
    }
    
    public void update() {
        if (this.observers != null) {
            final Iterator e = this.observers.iterator();
            while (e.hasNext()) {
                e.next().notify(this);
            }
        }
    }
    
    public final String toString() {
        final String access = Utility.accessToString(super.access_flags);
        String signature = Type.getMethodSignature(super.type, this.arg_types);
        signature = Utility.methodSignatureToString(signature, super.name, access, true, this.getLocalVariableTable(super.cp));
        final StringBuffer buf = new StringBuffer(signature);
        if (this.throws_vec.size() > 0) {
            final Iterator e = this.throws_vec.iterator();
            while (e.hasNext()) {
                buf.append("\n\t\tthrows " + e.next());
            }
        }
        return buf.toString();
    }
    
    public MethodGen copy(final String class_name, final ConstantPoolGen cp) {
        final Method m = ((MethodGen)this.clone()).getMethod();
        final MethodGen mg = new MethodGen(m, class_name, super.cp);
        if (super.cp != cp) {
            mg.setConstantPool(cp);
            mg.getInstructionList().replaceConstantPool(super.cp, cp);
        }
        return mg;
    }
    
    static final class BranchTarget
    {
        InstructionHandle target;
        int stackDepth;
        
        BranchTarget(final InstructionHandle target, final int stackDepth) {
            this.target = target;
            this.stackDepth = stackDepth;
        }
    }
    
    static final class BranchStack
    {
        Stack branchTargets;
        Hashtable visitedTargets;
        
        BranchStack() {
            this.branchTargets = new Stack();
            this.visitedTargets = new Hashtable();
        }
        
        public void push(final InstructionHandle target, final int stackDepth) {
            if (this.visited(target)) {
                return;
            }
            this.branchTargets.push(this.visit(target, stackDepth));
        }
        
        public BranchTarget pop() {
            if (!this.branchTargets.empty()) {
                final BranchTarget bt = this.branchTargets.pop();
                return bt;
            }
            return null;
        }
        
        private final BranchTarget visit(final InstructionHandle target, final int stackDepth) {
            final BranchTarget bt = new BranchTarget(target, stackDepth);
            this.visitedTargets.put(target, bt);
            return bt;
        }
        
        private final boolean visited(final InstructionHandle target) {
            return this.visitedTargets.get(target) != null;
        }
    }
}
