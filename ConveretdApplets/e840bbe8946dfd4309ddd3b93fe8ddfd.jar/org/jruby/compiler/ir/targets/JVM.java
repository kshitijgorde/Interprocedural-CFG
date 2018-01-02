// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.targets;

import org.jruby.compiler.ir.Operation;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.compiler.ir.operands.FieldRef;
import org.jruby.compiler.ir.instructions.DefineClassMethodInstr;
import org.jruby.org.objectweb.asm.commons.Method;
import org.jruby.org.objectweb.asm.Type;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.Fixnum;
import com.kenai.constantine.Constant;
import org.jruby.compiler.ir.instructions.ReturnInstr;
import org.jruby.compiler.ir.instructions.ReceiveArgumentInstruction;
import org.jruby.compiler.ir.instructions.GetFieldInstr;
import org.jruby.compiler.ir.instructions.PutFieldInstr;
import org.jruby.compiler.ir.instructions.LABEL_Instr;
import org.jruby.compiler.ir.instructions.JumpInstr;
import org.jruby.compiler.ir.instructions.DefineInstanceMethodInstr;
import org.jruby.compiler.ir.instructions.CopyInstr;
import org.jruby.compiler.ir.instructions.CallInstr;
import org.jruby.compiler.ir.instructions.BEQInstr;
import java.util.Iterator;
import org.jruby.compiler.ir.IRMethod;
import org.jruby.compiler.ir.instructions.Instr;
import org.jruby.util.CodegenUtils;
import org.jruby.RubyObject;
import org.jruby.RubyInstanceConfig;
import org.jruby.compiler.ir.IRClass;
import org.jruby.compiler.ir.IRScope;
import org.jruby.org.objectweb.asm.commons.GeneratorAdapter;
import org.jruby.org.objectweb.asm.util.TraceClassVisitor;
import org.jruby.org.objectweb.asm.ClassWriter;
import java.io.OutputStream;
import java.io.PrintWriter;
import org.jruby.org.objectweb.asm.ClassVisitor;
import java.util.ArrayList;
import org.jruby.compiler.ir.IRScript;
import java.util.List;
import java.util.Stack;
import org.jruby.compiler.ir.CompilerTarget;

public class JVM implements CompilerTarget
{
    private static final boolean DEBUG = true;
    Stack<ClassData> clsStack;
    List<ClassData> clsAccum;
    IRScript script;
    
    public JVM() {
        this.clsStack = new Stack<ClassData>();
        this.clsAccum = new ArrayList<ClassData>();
    }
    
    public ClassVisitor cls() {
        return this.clsData().cls;
    }
    
    public ClassData clsData() {
        return this.clsStack.peek();
    }
    
    public void pushclass() {
        final PrintWriter pw = new PrintWriter(System.out);
        this.clsStack.push(new ClassData(new TraceClassVisitor(new ClassWriter(3), pw)));
        pw.flush();
    }
    
    public void popclass() {
        this.clsStack.pop();
    }
    
    public GeneratorAdapter method() {
        return this.clsData().method();
    }
    
    public void pushmethod(final String name) {
        this.clsData().pushmethod(name);
    }
    
    public void popmethod() {
        this.clsData().popmethod();
    }
    
    public void codegen(final IRScope scope) {
        if (scope instanceof IRScript) {
            this.codegen((IRScript)scope);
        }
    }
    
    public void codegen(final IRScript script) {
        this.script = script;
        this.emit(script.getRootClass());
    }
    
    public void emit(final IRClass cls) {
        this.pushclass();
        this.cls().visit(RubyInstanceConfig.JAVA_VERSION, 33, cls.getName(), null, CodegenUtils.p(RubyObject.class), null);
        this.cls().visitSource(this.script.getFileName().toString(), null);
        this.pushmethod("__class__");
        for (final Instr instr : cls.getInstrs()) {
            this.emit(instr);
        }
        this.popmethod();
        for (final IRMethod method : cls.getMethods()) {
            this.emit(method);
        }
        for (final IRClass cls2 : cls.getClasses()) {
            this.emit(cls2);
        }
        this.cls().visitEnd();
        this.popclass();
    }
    
    public void emit(final IRMethod method) {
        this.pushmethod(method.getName());
        for (final Instr instr : method.getInstrs()) {
            this.emit(instr);
        }
        this.popmethod();
    }
    
    public void emit(final Instr instr) {
        switch (instr.operation) {
            case BEQ: {
                this.emitBEQ((BEQInstr)instr);
                break;
            }
            case CALL: {
                this.emitCALL((CallInstr)instr);
                break;
            }
            case COPY: {
                this.emitCOPY((CopyInstr)instr);
                break;
            }
            case DEF_INST_METH: {
                this.emitDEF_INST_METH((DefineInstanceMethodInstr)instr);
                break;
            }
            case JUMP: {
                this.emitJUMP((JumpInstr)instr);
                break;
            }
            case LABEL: {
                this.emitLABEL((LABEL_Instr)instr);
                break;
            }
            case PUT_FIELD: {
                this.emitPUT_FIELD((PutFieldInstr)instr);
                break;
            }
            case GET_FIELD: {
                this.emitGET_FIELD((GetFieldInstr)instr);
                break;
            }
            case RECV_ARG: {
                this.emitRECV_ARG((ReceiveArgumentInstruction)instr);
                break;
            }
            case RETURN: {
                this.emitRETURN((ReturnInstr)instr);
                break;
            }
            default: {
                System.err.println("unsupported: " + instr.operation);
                break;
            }
        }
    }
    
    public void emit(final Constant constant) {
        if (constant instanceof Fixnum) {
            this.method().push(((Fixnum)constant).value);
        }
    }
    
    public void emit(final Operand operand) {
        if (operand.isConstant()) {
            this.emit((Constant)operand);
        }
        else if (operand instanceof Variable) {
            this.emit((Variable)operand);
        }
    }
    
    public void emit(final Variable variable) {
        final int index = this.getVariableIndex(variable);
        this.method().loadLocal(index);
    }
    
    public void emitBEQ(final BEQInstr beq) {
        final Operand[] args = beq.getOperands();
        this.emit(args[0]);
        this.emit(args[1]);
        this.method().ifCmp(Type.getType(Object.class), 153, this.getLabel(beq.getJumpTarget()));
    }
    
    public void emitCOPY(final CopyInstr copy) {
        final int index = this.getVariableIndex(copy.result);
        this.emit(copy.getOperands()[0]);
        this.method().storeLocal(index);
    }
    
    public void emitCALL(final CallInstr call) {
        this.emit(call.getReceiver());
        for (final Operand operand : call.getCallArgs()) {
            this.emit(operand);
        }
        this.method().invokeVirtual(Type.getType(Object.class), Method.getMethod("Object " + call.getMethodAddr() + " ()"));
    }
    
    public void emitDEF_INST_METH(final DefineInstanceMethodInstr instr) {
        final IRMethod irMethod = instr.method;
        final GeneratorAdapter adapter = new GeneratorAdapter(1, Method.getMethod("void " + irMethod.getName() + " ()"), null, null, this.cls());
        adapter.loadThis();
        adapter.loadArgs();
        adapter.invokeStatic(Type.getType(Object.class), Method.getMethod("Object __ruby__" + irMethod.getName() + " (Object)"));
        adapter.returnValue();
        adapter.endMethod();
    }
    
    public void emitDEF_CLS_METH(final DefineClassMethodInstr instr) {
        final IRMethod irMethod = instr.method;
        final GeneratorAdapter adapter = new GeneratorAdapter(9, Method.getMethod("void " + irMethod.getName() + " ()"), null, null, this.cls());
        adapter.returnValue();
        adapter.endMethod();
    }
    
    public void emitJUMP(final JumpInstr jump) {
        this.method().goTo(this.getLabel(jump.target));
    }
    
    public void emitLABEL(final LABEL_Instr lbl) {
        this.method().mark(this.getLabel(lbl._lbl));
    }
    
    public void emitPUT_FIELD(final PutFieldInstr putField) {
        final String field = ((FieldRef)putField.getOperands()[1]).getName();
        this.declareField(field);
        this.emit(putField.getOperands()[0]);
        this.emit(putField.getOperands()[2]);
        this.method().putField(Type.getType(Object.class), field, Type.getType(Object.class));
    }
    
    public void emitGET_FIELD(final GetFieldInstr putField) {
        final String field = ((FieldRef)putField.getOperands()[1]).getName();
        this.declareField(field);
        this.emit(putField.getOperands()[0]);
        this.method().getField(Type.getType(Object.class), field, Type.getType(Object.class));
    }
    
    public void emitRETURN(final ReturnInstr ret) {
        this.emit(ret.getOperands()[0]);
        this.method().returnValue();
    }
    
    public void emitRECV_ARG(final ReceiveArgumentInstruction recvArg) {
        final int index = this.getVariableIndex(recvArg.result);
    }
    
    private int getVariableIndex(final Variable variable) {
        Integer index = this.clsStack.peek().methodStack.peek().varMap.get(variable);
        if (index == null) {
            index = this.method().newLocal(Type.getType(Object.class));
            this.clsStack.peek().methodStack.peek().varMap.put(variable, index);
        }
        return index;
    }
    
    private org.jruby.org.objectweb.asm.Label getLabel(final Label label) {
        org.jruby.org.objectweb.asm.Label asmLabel = this.clsData().methodData().labelMap.get(label);
        if (asmLabel == null) {
            asmLabel = this.method().newLabel();
            this.clsData().methodData().labelMap.put(label, asmLabel);
        }
        return asmLabel;
    }
    
    private void declareField(final String field) {
        if (!this.clsData().fieldSet.contains(field)) {
            this.cls().visitField(4, field, CodegenUtils.ci(Object.class), null, null);
            this.clsData().fieldSet.add(field);
        }
    }
    
    private static class ClassData
    {
        public ClassVisitor cls;
        Stack<MethodData> methodStack;
        public Set<String> fieldSet;
        
        public ClassData(final ClassVisitor cls) {
            this.methodStack = new Stack<MethodData>();
            this.fieldSet = new HashSet<String>();
            this.cls = cls;
        }
        
        public GeneratorAdapter method() {
            return this.methodData().method;
        }
        
        public MethodData methodData() {
            return this.methodStack.peek();
        }
        
        public void pushmethod(final String name) {
            this.methodStack.push(new MethodData(new GeneratorAdapter(9, Method.getMethod("org.jruby.runtime.builtin.IRubyObject " + name + " (org.jruby.runtime.ThreadContext, org.jruby.runtime.builtin.IRubyObject)"), null, null, this.cls)));
        }
        
        public void popmethod() {
            this.method().endMethod();
            this.methodStack.pop();
        }
    }
    
    private static class MethodData
    {
        public GeneratorAdapter method;
        public Map<Variable, Integer> varMap;
        public Map<Label, org.jruby.org.objectweb.asm.Label> labelMap;
        
        public MethodData(final GeneratorAdapter method) {
            this.varMap = new HashMap<Variable, Integer>();
            this.labelMap = new HashMap<Label, org.jruby.org.objectweb.asm.Label>();
            this.method = method;
        }
    }
}
