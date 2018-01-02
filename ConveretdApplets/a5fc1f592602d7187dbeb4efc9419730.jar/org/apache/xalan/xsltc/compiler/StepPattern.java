// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.IF_ICMPNE;
import org.apache.bcel.generic.IF_ICMPLT;
import org.apache.bcel.generic.IFLT;
import org.apache.bcel.generic.PUTFIELD;
import org.apache.bcel.generic.ALOAD;
import org.apache.bcel.generic.IFNONNULL;
import org.apache.bcel.generic.ASTORE;
import org.apache.bcel.generic.GETFIELD;
import org.apache.bcel.classfile.Attribute;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.generic.LocalVariableGen;
import org.apache.bcel.generic.INVOKESPECIAL;
import org.apache.bcel.generic.NEW;
import org.apache.bcel.generic.ILOAD;
import org.apache.bcel.generic.ISTORE;
import org.apache.xalan.xsltc.compiler.util.Util;
import org.apache.bcel.generic.GOTO;
import org.apache.bcel.generic.BranchHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.IF_ICMPEQ;
import org.apache.bcel.generic.CompoundInstruction;
import org.apache.bcel.generic.PUSH;
import org.apache.bcel.generic.GOTO_W;
import org.apache.bcel.generic.BranchInstruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.IFNE;
import org.apache.bcel.generic.INVOKEINTERFACE;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;
import org.apache.xml.dtm.Axis;
import java.util.Vector;

class StepPattern extends RelativePathPattern
{
    private static final int NO_CONTEXT = 0;
    private static final int SIMPLE_CONTEXT = 1;
    private static final int GENERAL_CONTEXT = 2;
    protected final int _axis;
    protected final int _nodeType;
    protected Vector _predicates;
    private Step _step;
    private boolean _isEpsilon;
    private int _contextCase;
    private double _priority;
    
    public StepPattern(final int axis, final int nodeType, final Vector predicates) {
        this._step = null;
        this._isEpsilon = false;
        this._priority = Double.MAX_VALUE;
        this._axis = axis;
        this._nodeType = nodeType;
        this._predicates = predicates;
    }
    
    public void setParser(final Parser parser) {
        super.setParser(parser);
        if (this._predicates != null) {
            for (int n = this._predicates.size(), i = 0; i < n; ++i) {
                final Predicate exp = this._predicates.elementAt(i);
                exp.setParser(parser);
                exp.setParent(this);
            }
        }
    }
    
    public int getNodeType() {
        return this._nodeType;
    }
    
    public void setPriority(final double priority) {
        this._priority = priority;
    }
    
    public StepPattern getKernelPattern() {
        return this;
    }
    
    public boolean isWildcard() {
        return this._isEpsilon && !this.hasPredicates();
    }
    
    public StepPattern setPredicates(final Vector predicates) {
        this._predicates = predicates;
        return this;
    }
    
    protected boolean hasPredicates() {
        return this._predicates != null && this._predicates.size() > 0;
    }
    
    public double getDefaultPriority() {
        if (this._priority != Double.MAX_VALUE) {
            return this._priority;
        }
        if (this.hasPredicates()) {
            return 0.5;
        }
        switch (this._nodeType) {
            case -1: {
                return -0.5;
            }
            case 0: {
                return 0.0;
            }
            default: {
                return (this._nodeType >= 14) ? 0.0 : -0.5;
            }
        }
    }
    
    public int getAxis() {
        return this._axis;
    }
    
    public void reduceKernelPattern() {
        this._isEpsilon = true;
    }
    
    public String toString() {
        final StringBuffer buffer = new StringBuffer("stepPattern(\"");
        buffer.append(Axis.names[this._axis]).append("\", ").append(this._isEpsilon ? ("epsilon{" + Integer.toString(this._nodeType) + "}") : Integer.toString(this._nodeType));
        if (this._predicates != null) {
            buffer.append(", ").append(this._predicates.toString());
        }
        return buffer.append(')').toString();
    }
    
    private int analyzeCases() {
        boolean noContext = true;
        final int n = this._predicates.size();
        for (int i = 0; i < n && noContext; ++i) {
            final Predicate pred = this._predicates.elementAt(i);
            if (pred.isNthPositionFilter() || pred.hasPositionCall() || pred.hasLastCall()) {
                noContext = false;
            }
        }
        if (noContext) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return 2;
    }
    
    private String getNextFieldName() {
        return "__step_pattern_iter_" + this.getXSLTC().nextStepPatternSerial();
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        if (this.hasPredicates()) {
            for (int n = this._predicates.size(), i = 0; i < n; ++i) {
                final Predicate pred = this._predicates.elementAt(i);
                pred.typeCheck(stable);
            }
            this._contextCase = this.analyzeCases();
            Step step = null;
            if (this._contextCase == 1) {
                final Predicate pred2 = this._predicates.elementAt(0);
                if (pred2.isNthPositionFilter()) {
                    this._contextCase = 2;
                    step = new Step(this._axis, this._nodeType, this._predicates);
                }
                else {
                    step = new Step(this._axis, this._nodeType, null);
                }
            }
            else if (this._contextCase == 2) {
                for (int len = this._predicates.size(), j = 0; j < len; ++j) {
                    this._predicates.elementAt(j).dontOptimize();
                }
                step = new Step(this._axis, this._nodeType, this._predicates);
            }
            if (step != null) {
                step.setParser(this.getParser());
                step.typeCheck(stable);
                this._step = step;
            }
        }
        return (this._axis == 3) ? Type.Element : Type.Attribute;
    }
    
    private void translateKernel(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        if (this._nodeType == 1) {
            final int check = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "isElement", "(I)Z");
            il.append(methodGen.loadDOM());
            il.append(InstructionConstants.SWAP);
            il.append(new INVOKEINTERFACE(check, 2));
            final BranchHandle icmp = il.append(new IFNE(null));
            super._falseList.add(il.append(new GOTO_W(null)));
            icmp.setTarget(il.append(InstructionConstants.NOP));
        }
        else if (this._nodeType == 2) {
            final int check = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "isAttribute", "(I)Z");
            il.append(methodGen.loadDOM());
            il.append(InstructionConstants.SWAP);
            il.append(new INVOKEINTERFACE(check, 2));
            final BranchHandle icmp = il.append(new IFNE(null));
            super._falseList.add(il.append(new GOTO_W(null)));
            icmp.setTarget(il.append(InstructionConstants.NOP));
        }
        else {
            final int getEType = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getExpandedTypeID", "(I)I");
            il.append(methodGen.loadDOM());
            il.append(InstructionConstants.SWAP);
            il.append(new INVOKEINTERFACE(getEType, 2));
            il.append(new PUSH(cpg, this._nodeType));
            final BranchHandle icmp = il.append(new IF_ICMPEQ(null));
            super._falseList.add(il.append(new GOTO_W(null)));
            icmp.setTarget(il.append(InstructionConstants.NOP));
        }
    }
    
    private void translateNoContext(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(methodGen.loadCurrentNode());
        il.append(InstructionConstants.SWAP);
        il.append(methodGen.storeCurrentNode());
        if (!this._isEpsilon) {
            il.append(methodGen.loadCurrentNode());
            this.translateKernel(classGen, methodGen);
        }
        for (int n = this._predicates.size(), i = 0; i < n; ++i) {
            final Predicate pred = this._predicates.elementAt(i);
            final Expression exp = pred.getExpr();
            exp.translateDesynthesized(classGen, methodGen);
            super._trueList.append(exp._trueList);
            super._falseList.append(exp._falseList);
        }
        InstructionHandle restore = il.append(methodGen.storeCurrentNode());
        this.backPatchTrueList(restore);
        final BranchHandle skipFalse = il.append(new GOTO(null));
        restore = il.append(methodGen.storeCurrentNode());
        this.backPatchFalseList(restore);
        super._falseList.add(il.append(new GOTO(null)));
        skipFalse.setTarget(il.append(InstructionConstants.NOP));
    }
    
    private void translateSimpleContext(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final LocalVariableGen match = methodGen.addLocalVariable("step_pattern_tmp1", Util.getJCRefType("I"), il.getEnd(), null);
        il.append(new ISTORE(match.getIndex()));
        if (!this._isEpsilon) {
            il.append(new ILOAD(match.getIndex()));
            this.translateKernel(classGen, methodGen);
        }
        il.append(methodGen.loadCurrentNode());
        il.append(methodGen.loadIterator());
        int index = cpg.addMethodref("org.apache.xalan.xsltc.dom.MatchingIterator", "<init>", "(ILorg/apache/xml/dtm/DTMAxisIterator;)V");
        il.append(new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.MatchingIterator")));
        il.append(InstructionConstants.DUP);
        il.append(new ILOAD(match.getIndex()));
        this._step.translate(classGen, methodGen);
        il.append(new INVOKESPECIAL(index));
        il.append(methodGen.loadDOM());
        il.append(new ILOAD(match.getIndex()));
        index = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getParent", "(I)I");
        il.append(new INVOKEINTERFACE(index, 2));
        il.append(methodGen.setStartNode());
        il.append(methodGen.storeIterator());
        il.append(new ILOAD(match.getIndex()));
        il.append(methodGen.storeCurrentNode());
        final Predicate pred = this._predicates.elementAt(0);
        final Expression exp = pred.getExpr();
        exp.translateDesynthesized(classGen, methodGen);
        InstructionHandle restore = il.append(methodGen.storeIterator());
        il.append(methodGen.storeCurrentNode());
        exp.backPatchTrueList(restore);
        final BranchHandle skipFalse = il.append(new GOTO(null));
        restore = il.append(methodGen.storeIterator());
        il.append(methodGen.storeCurrentNode());
        exp.backPatchFalseList(restore);
        super._falseList.add(il.append(new GOTO(null)));
        skipFalse.setTarget(il.append(InstructionConstants.NOP));
    }
    
    private void translateGeneralContext(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        int iteratorIndex = 0;
        BranchHandle ifBlock = null;
        final String iteratorName = this.getNextFieldName();
        final LocalVariableGen node = methodGen.addLocalVariable("step_pattern_tmp1", Util.getJCRefType("I"), il.getEnd(), null);
        il.append(new ISTORE(node.getIndex()));
        final LocalVariableGen iter = methodGen.addLocalVariable("step_pattern_tmp2", Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;"), il.getEnd(), null);
        if (!classGen.isExternal()) {
            final Field iterator = new Field(2, cpg.addUtf8(iteratorName), cpg.addUtf8("Lorg/apache/xml/dtm/DTMAxisIterator;"), null, cpg.getConstantPool());
            classGen.addField(iterator);
            iteratorIndex = cpg.addFieldref(classGen.getClassName(), iteratorName, "Lorg/apache/xml/dtm/DTMAxisIterator;");
            il.append(classGen.loadTranslet());
            il.append(new GETFIELD(iteratorIndex));
            il.append(InstructionConstants.DUP);
            il.append(new ASTORE(iter.getIndex()));
            ifBlock = il.append(new IFNONNULL(null));
            il.append(classGen.loadTranslet());
        }
        this._step.translate(classGen, methodGen);
        il.append(new ASTORE(iter.getIndex()));
        if (!classGen.isExternal()) {
            il.append(new ALOAD(iter.getIndex()));
            il.append(new PUTFIELD(iteratorIndex));
            ifBlock.setTarget(il.append(InstructionConstants.NOP));
        }
        il.append(methodGen.loadDOM());
        il.append(new ILOAD(node.getIndex()));
        final int index = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getParent", "(I)I");
        il.append(new INVOKEINTERFACE(index, 2));
        il.append(new ALOAD(iter.getIndex()));
        il.append(InstructionConstants.SWAP);
        il.append(methodGen.setStartNode());
        final LocalVariableGen node2 = methodGen.addLocalVariable("step_pattern_tmp3", Util.getJCRefType("I"), il.getEnd(), null);
        final BranchHandle skipNext = il.append(new GOTO(null));
        final InstructionHandle next = il.append(new ALOAD(iter.getIndex()));
        final InstructionHandle begin = il.append(methodGen.nextNode());
        il.append(InstructionConstants.DUP);
        il.append(new ISTORE(node2.getIndex()));
        super._falseList.add(il.append(new IFLT(null)));
        il.append(new ILOAD(node2.getIndex()));
        il.append(new ILOAD(node.getIndex()));
        il.append(new IF_ICMPLT(next));
        il.append(new ILOAD(node2.getIndex()));
        il.append(new ILOAD(node.getIndex()));
        super._falseList.add(il.append(new IF_ICMPNE(null)));
        skipNext.setTarget(begin);
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        if (this.hasPredicates()) {
            switch (this._contextCase) {
                case 0: {
                    this.translateNoContext(classGen, methodGen);
                    break;
                }
                case 1: {
                    this.translateSimpleContext(classGen, methodGen);
                    break;
                }
                default: {
                    this.translateGeneralContext(classGen, methodGen);
                    break;
                }
            }
        }
        else if (this.isWildcard()) {
            il.append(InstructionConstants.POP);
        }
        else {
            this.translateKernel(classGen, methodGen);
        }
    }
}
