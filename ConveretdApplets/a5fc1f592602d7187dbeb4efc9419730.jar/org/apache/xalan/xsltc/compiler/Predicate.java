// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.CompoundInstruction;
import org.apache.bcel.generic.PUSH;
import org.apache.bcel.generic.PUTFIELD;
import org.apache.bcel.generic.INVOKESPECIAL;
import org.apache.bcel.generic.NEW;
import org.apache.bcel.generic.LocalVariableGen;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.ASTORE;
import org.apache.bcel.generic.GETFIELD;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.CHECKCAST;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.xalan.xsltc.compiler.util.TestGenerator;
import org.apache.xalan.xsltc.compiler.util.Util;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.classfile.Attribute;
import org.apache.bcel.classfile.Field;
import org.apache.xalan.xsltc.compiler.util.FilterGenerator;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.BooleanType;
import org.apache.xalan.xsltc.compiler.util.IntType;
import org.apache.xalan.xsltc.compiler.util.NumberType;
import org.apache.xalan.xsltc.compiler.util.ResultTreeType;
import org.apache.xalan.xsltc.compiler.util.ReferenceType;
import org.apache.xalan.xsltc.compiler.util.Type;
import java.util.ArrayList;

final class Predicate extends Expression implements Closure
{
    private Expression _exp;
    private boolean _canOptimize;
    private boolean _nthPositionFilter;
    private boolean _nthDescendant;
    int _ptype;
    private String _className;
    private ArrayList _closureVars;
    private Closure _parentClosure;
    private Expression _value;
    private Step _step;
    
    public Predicate(final Expression exp) {
        this._exp = null;
        this._canOptimize = true;
        this._nthPositionFilter = false;
        this._nthDescendant = false;
        this._ptype = -1;
        this._className = null;
        this._closureVars = null;
        this._parentClosure = null;
        this._value = null;
        this._step = null;
        (this._exp = exp).setParent(this);
    }
    
    public void setParser(final Parser parser) {
        super.setParser(parser);
        this._exp.setParser(parser);
    }
    
    public boolean isNthPositionFilter() {
        return this._nthPositionFilter;
    }
    
    public boolean isNthDescendant() {
        return this._nthDescendant;
    }
    
    public void dontOptimize() {
        this._canOptimize = false;
    }
    
    public boolean hasPositionCall() {
        return this._exp.hasPositionCall();
    }
    
    public boolean hasLastCall() {
        return this._exp.hasLastCall();
    }
    
    public boolean inInnerClass() {
        return this._className != null;
    }
    
    public Closure getParentClosure() {
        if (this._parentClosure == null) {
            SyntaxTreeNode node = this.getParent();
            while (!(node instanceof Closure)) {
                if (node instanceof TopLevelElement) {
                    return this._parentClosure;
                }
                node = node.getParent();
                if (node == null) {
                    return this._parentClosure;
                }
            }
            this._parentClosure = (Closure)node;
        }
        return this._parentClosure;
    }
    
    public String getInnerClassName() {
        return this._className;
    }
    
    public void addVariable(final VariableRefBase variableRef) {
        if (this._closureVars == null) {
            this._closureVars = new ArrayList();
        }
        if (!this._closureVars.contains(variableRef)) {
            this._closureVars.add(variableRef);
            final Closure parentClosure = this.getParentClosure();
            if (parentClosure != null) {
                parentClosure.addVariable(variableRef);
            }
        }
    }
    
    public int getPosType() {
        if (this._ptype == -1) {
            final SyntaxTreeNode parent = this.getParent();
            if (parent instanceof StepPattern) {
                this._ptype = ((StepPattern)parent).getNodeType();
            }
            else if (parent instanceof AbsoluteLocationPath) {
                final AbsoluteLocationPath path = (AbsoluteLocationPath)parent;
                final Expression exp = path.getPath();
                if (exp instanceof Step) {
                    this._ptype = ((Step)exp).getNodeType();
                }
            }
            else if (parent instanceof VariableRefBase) {
                final VariableRefBase ref = (VariableRefBase)parent;
                final VariableBase var = ref.getVariable();
                final Expression exp2 = var.getExpression();
                if (exp2 instanceof Step) {
                    this._ptype = ((Step)exp2).getNodeType();
                }
            }
            else if (parent instanceof Step) {
                this._ptype = ((Step)parent).getNodeType();
            }
        }
        return this._ptype;
    }
    
    public boolean parentIsPattern() {
        return this.getParent() instanceof Pattern;
    }
    
    public Expression getExpr() {
        return this._exp;
    }
    
    public String toString() {
        return "pred(" + this._exp + ')';
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        Type texp = this._exp.typeCheck(stable);
        if (texp instanceof ReferenceType) {
            this._exp = new CastExpr(this._exp, texp = Type.Real);
        }
        if (texp instanceof ResultTreeType) {
            this._exp = new CastExpr(this._exp, Type.Boolean);
            this._exp = new CastExpr(this._exp, Type.Real);
            texp = this._exp.typeCheck(stable);
        }
        if (texp instanceof NumberType) {
            if (!(texp instanceof IntType)) {
                this._exp = new CastExpr(this._exp, Type.Int);
            }
            if (this._canOptimize) {
                this._nthPositionFilter = (!this._exp.hasLastCall() && !this._exp.hasPositionCall());
                if (this._nthPositionFilter) {
                    final SyntaxTreeNode parent = this.getParent();
                    this._nthDescendant = (parent instanceof Step && parent.getParent() instanceof AbsoluteLocationPath);
                    return super._type = Type.NodeSet;
                }
            }
            final boolean b = false;
            this._nthDescendant = b;
            this._nthPositionFilter = b;
            final QName position = this.getParser().getQNameIgnoreDefaultNs("position");
            final PositionCall positionCall = new PositionCall(position);
            positionCall.setParser(this.getParser());
            positionCall.setParent(this);
            this._exp = new EqualityExpr(0, positionCall, this._exp);
            if (this._exp.typeCheck(stable) != Type.Boolean) {
                this._exp = new CastExpr(this._exp, Type.Boolean);
            }
            return super._type = Type.Boolean;
        }
        if (!(texp instanceof BooleanType)) {
            this._exp = new CastExpr(this._exp, Type.Boolean);
        }
        return super._type = Type.Boolean;
    }
    
    private void compileFilter(final ClassGenerator classGen, final MethodGenerator methodGen) {
        this._className = this.getXSLTC().getHelperClassName();
        final FilterGenerator filterGen = new FilterGenerator(this._className, "java.lang.Object", this.toString(), 33, new String[] { "org.apache.xalan.xsltc.dom.CurrentNodeListFilter" }, classGen.getStylesheet());
        final ConstantPoolGen cpg = filterGen.getConstantPool();
        for (int length = (this._closureVars == null) ? 0 : this._closureVars.size(), i = 0; i < length; ++i) {
            final VariableBase var = this._closureVars.get(i).getVariable();
            filterGen.addField(new Field(1, cpg.addUtf8(var.getEscapedName()), cpg.addUtf8(var.getType().toSignature()), null, cpg.getConstantPool()));
        }
        final InstructionList il = new InstructionList();
        final TestGenerator testGen = new TestGenerator(17, org.apache.bcel.generic.Type.BOOLEAN, new org.apache.bcel.generic.Type[] { org.apache.bcel.generic.Type.INT, org.apache.bcel.generic.Type.INT, org.apache.bcel.generic.Type.INT, org.apache.bcel.generic.Type.INT, Util.getJCRefType("Lorg/apache/xalan/xsltc/runtime/AbstractTranslet;"), Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;") }, new String[] { "node", "position", "last", "current", "translet", "iterator" }, "test", this._className, il, cpg);
        final LocalVariableGen local = testGen.addLocalVariable("document", Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;"), null, null);
        final String className = classGen.getClassName();
        il.append(filterGen.loadTranslet());
        il.append(new CHECKCAST(cpg.addClass(className)));
        il.append(new GETFIELD(cpg.addFieldref(className, "_dom", "Lorg/apache/xalan/xsltc/DOM;")));
        il.append(new ASTORE(local.getIndex()));
        testGen.setDomIndex(local.getIndex());
        this._exp.translate(filterGen, testGen);
        il.append(InstructionConstants.IRETURN);
        testGen.stripAttributes(true);
        testGen.setMaxLocals();
        testGen.setMaxStack();
        testGen.removeNOPs();
        filterGen.addEmptyConstructor(1);
        filterGen.addMethod(testGen.getMethod());
        this.getXSLTC().dumpClass(filterGen.getJavaClass());
    }
    
    public boolean isBooleanTest() {
        return this._exp instanceof BooleanExpr;
    }
    
    public boolean isNodeValueTest() {
        return this._canOptimize && this.getStep() != null && this.getCompareValue() != null;
    }
    
    public Step getStep() {
        if (this._step != null) {
            return this._step;
        }
        if (this._exp == null) {
            return null;
        }
        if (this._exp instanceof EqualityExpr) {
            final EqualityExpr exp = (EqualityExpr)this._exp;
            Expression left = exp.getLeft();
            Expression right = exp.getRight();
            if (left instanceof CastExpr) {
                left = ((CastExpr)left).getExpr();
            }
            if (left instanceof Step) {
                this._step = (Step)left;
            }
            if (right instanceof CastExpr) {
                right = ((CastExpr)right).getExpr();
            }
            if (right instanceof Step) {
                this._step = (Step)right;
            }
        }
        return this._step;
    }
    
    public Expression getCompareValue() {
        if (this._value != null) {
            return this._value;
        }
        if (this._exp == null) {
            return null;
        }
        if (this._exp instanceof EqualityExpr) {
            final EqualityExpr exp = (EqualityExpr)this._exp;
            final Expression left = exp.getLeft();
            final Expression right = exp.getRight();
            if (left instanceof LiteralExpr) {
                return this._value = left;
            }
            if (left instanceof VariableRefBase && left.getType() == Type.String) {
                return this._value = left;
            }
            if (right instanceof LiteralExpr) {
                return this._value = right;
            }
            if (right instanceof VariableRefBase && right.getType() == Type.String) {
                return this._value = right;
            }
        }
        return null;
    }
    
    public void translateFilter(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        this.compileFilter(classGen, methodGen);
        il.append(new NEW(cpg.addClass(this._className)));
        il.append(InstructionConstants.DUP);
        il.append(new INVOKESPECIAL(cpg.addMethodref(this._className, "<init>", "()V")));
        for (int length = (this._closureVars == null) ? 0 : this._closureVars.size(), i = 0; i < length; ++i) {
            final VariableRefBase varRef = this._closureVars.get(i);
            final VariableBase var = varRef.getVariable();
            final Type varType = var.getType();
            il.append(InstructionConstants.DUP);
            Closure variableClosure;
            for (variableClosure = this._parentClosure; variableClosure != null && !variableClosure.inInnerClass(); variableClosure = variableClosure.getParentClosure()) {}
            if (variableClosure != null) {
                il.append(InstructionConstants.ALOAD_0);
                il.append(new GETFIELD(cpg.addFieldref(variableClosure.getInnerClassName(), var.getEscapedName(), varType.toSignature())));
            }
            else {
                il.append(var.loadInstruction());
            }
            il.append(new PUTFIELD(cpg.addFieldref(this._className, var.getEscapedName(), varType.toSignature())));
        }
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        if (this._nthPositionFilter || this._nthDescendant) {
            this._exp.translate(classGen, methodGen);
        }
        else if (this.isNodeValueTest() && this.getParent() instanceof Step) {
            this._value.translate(classGen, methodGen);
            il.append(new CHECKCAST(cpg.addClass("java.lang.String")));
            il.append(new PUSH(cpg, ((EqualityExpr)this._exp).getOp()));
        }
        else {
            this.translateFilter(classGen, methodGen);
        }
    }
}
