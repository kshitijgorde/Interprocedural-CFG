// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.BranchHandle;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.INVOKESTATIC;
import org.apache.bcel.generic.CompoundInstruction;
import org.apache.bcel.generic.PUSH;
import org.apache.bcel.generic.GOTO;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.INVOKEVIRTUAL;
import org.apache.xalan.xsltc.compiler.util.StringType;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.IFEQ;
import org.apache.bcel.generic.IFNE;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.xalan.xsltc.compiler.util.RealType;
import org.apache.bcel.generic.BranchInstruction;
import org.apache.bcel.generic.IF_ICMPEQ;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.IF_ICMPNE;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.IntType;
import org.apache.xalan.xsltc.compiler.util.ResultTreeType;
import org.apache.xalan.xsltc.compiler.util.NodeSetType;
import org.apache.xalan.xsltc.compiler.util.NodeType;
import org.apache.xalan.xsltc.compiler.util.ReferenceType;
import org.apache.xalan.xsltc.compiler.util.NumberType;
import org.apache.xalan.xsltc.compiler.util.BooleanType;
import org.apache.xalan.xsltc.compiler.util.Type;
import org.apache.xalan.xsltc.runtime.Operators;

final class EqualityExpr extends Expression implements Operators
{
    private final int _op;
    private Expression _left;
    private Expression _right;
    
    public EqualityExpr(final int op, final Expression left, final Expression right) {
        this._op = op;
        (this._left = left).setParent(this);
        (this._right = right).setParent(this);
    }
    
    public void setParser(final Parser parser) {
        super.setParser(parser);
        this._left.setParser(parser);
        this._right.setParser(parser);
    }
    
    public String toString() {
        return Operators.names[this._op] + '(' + this._left + ", " + this._right + ')';
    }
    
    public Expression getLeft() {
        return this._left;
    }
    
    public Expression getRight() {
        return this._right;
    }
    
    public boolean getOp() {
        return this._op != 1;
    }
    
    public boolean hasPositionCall() {
        return this._left.hasPositionCall() || this._right.hasPositionCall();
    }
    
    public boolean hasLastCall() {
        return this._left.hasLastCall() || this._right.hasLastCall();
    }
    
    private void swapArguments() {
        final Expression temp = this._left;
        this._left = this._right;
        this._right = temp;
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        final Type tleft = this._left.typeCheck(stable);
        final Type tright = this._right.typeCheck(stable);
        if (tleft.isSimple() && tright.isSimple()) {
            if (tleft != tright) {
                if (tleft instanceof BooleanType) {
                    this._right = new CastExpr(this._right, Type.Boolean);
                }
                else if (tright instanceof BooleanType) {
                    this._left = new CastExpr(this._left, Type.Boolean);
                }
                else if (tleft instanceof NumberType || tright instanceof NumberType) {
                    this._left = new CastExpr(this._left, Type.Real);
                    this._right = new CastExpr(this._right, Type.Real);
                }
                else {
                    this._left = new CastExpr(this._left, Type.String);
                    this._right = new CastExpr(this._right, Type.String);
                }
            }
        }
        else if (tleft instanceof ReferenceType) {
            this._right = new CastExpr(this._right, Type.Reference);
        }
        else if (tright instanceof ReferenceType) {
            this._left = new CastExpr(this._left, Type.Reference);
        }
        else if (tleft instanceof NodeType && tright == Type.String) {
            this._left = new CastExpr(this._left, Type.String);
        }
        else if (tleft == Type.String && tright instanceof NodeType) {
            this._right = new CastExpr(this._right, Type.String);
        }
        else if (tleft instanceof NodeType && tright instanceof NodeType) {
            this._left = new CastExpr(this._left, Type.String);
            this._right = new CastExpr(this._right, Type.String);
        }
        else if (!(tleft instanceof NodeType) || !(tright instanceof NodeSetType)) {
            if (tleft instanceof NodeSetType && tright instanceof NodeType) {
                this.swapArguments();
            }
            else {
                if (tleft instanceof NodeType) {
                    this._left = new CastExpr(this._left, Type.NodeSet);
                }
                if (tright instanceof NodeType) {
                    this._right = new CastExpr(this._right, Type.NodeSet);
                }
                if (tleft.isSimple() || (tleft instanceof ResultTreeType && tright instanceof NodeSetType)) {
                    this.swapArguments();
                }
                if (this._right.getType() instanceof IntType) {
                    this._right = new CastExpr(this._right, Type.Real);
                }
            }
        }
        return super._type = Type.Boolean;
    }
    
    public void translateDesynthesized(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final Type tleft = this._left.getType();
        final InstructionList il = methodGen.getInstructionList();
        if (tleft instanceof BooleanType) {
            this._left.translate(classGen, methodGen);
            this._right.translate(classGen, methodGen);
            super._falseList.add(il.append((this._op == 0) ? new IF_ICMPNE(null) : new IF_ICMPEQ(null)));
        }
        else if (tleft instanceof NumberType) {
            this._left.translate(classGen, methodGen);
            this._right.translate(classGen, methodGen);
            if (tleft instanceof RealType) {
                il.append(InstructionConstants.DCMPG);
                super._falseList.add(il.append((this._op == 0) ? new IFNE(null) : new IFEQ(null)));
            }
            else {
                super._falseList.add(il.append((this._op == 0) ? new IF_ICMPNE(null) : new IF_ICMPEQ(null)));
            }
        }
        else {
            this.translate(classGen, methodGen);
            this.desynthesize(classGen, methodGen);
        }
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final Type tleft = this._left.getType();
        Type tright = this._right.getType();
        if (tleft instanceof BooleanType || tleft instanceof NumberType) {
            this.translateDesynthesized(classGen, methodGen);
            this.synthesize(classGen, methodGen);
            return;
        }
        if (tleft instanceof StringType) {
            final int equals = cpg.addMethodref("java.lang.String", "equals", "(Ljava/lang/Object;)Z");
            this._left.translate(classGen, methodGen);
            this._right.translate(classGen, methodGen);
            il.append(new INVOKEVIRTUAL(equals));
            if (this._op == 1) {
                il.append(InstructionConstants.ICONST_1);
                il.append(InstructionConstants.IXOR);
            }
            return;
        }
        if (tleft instanceof ResultTreeType) {
            if (tright instanceof BooleanType) {
                this._right.translate(classGen, methodGen);
                if (this._op == 1) {
                    il.append(InstructionConstants.ICONST_1);
                    il.append(InstructionConstants.IXOR);
                }
                return;
            }
            if (tright instanceof RealType) {
                this._left.translate(classGen, methodGen);
                tleft.translateTo(classGen, methodGen, Type.Real);
                this._right.translate(classGen, methodGen);
                il.append(InstructionConstants.DCMPG);
                final BranchHandle falsec = il.append((this._op == 0) ? new IFNE(null) : new IFEQ(null));
                il.append(InstructionConstants.ICONST_1);
                final BranchHandle truec = il.append(new GOTO(null));
                falsec.setTarget(il.append(InstructionConstants.ICONST_0));
                truec.setTarget(il.append(InstructionConstants.NOP));
                return;
            }
            this._left.translate(classGen, methodGen);
            tleft.translateTo(classGen, methodGen, Type.String);
            this._right.translate(classGen, methodGen);
            if (tright instanceof ResultTreeType) {
                tright.translateTo(classGen, methodGen, Type.String);
            }
            final int equals2 = cpg.addMethodref("java.lang.String", "equals", "(Ljava/lang/Object;)Z");
            il.append(new INVOKEVIRTUAL(equals2));
            if (this._op == 1) {
                il.append(InstructionConstants.ICONST_1);
                il.append(InstructionConstants.IXOR);
            }
        }
        else {
            if (tleft instanceof NodeSetType && tright instanceof BooleanType) {
                this._left.translate(classGen, methodGen);
                this._left.startIterator(classGen, methodGen);
                Type.NodeSet.translateTo(classGen, methodGen, Type.Boolean);
                this._right.translate(classGen, methodGen);
                il.append(InstructionConstants.IXOR);
                if (this._op == 0) {
                    il.append(InstructionConstants.ICONST_1);
                    il.append(InstructionConstants.IXOR);
                }
                return;
            }
            if (tleft instanceof NodeSetType && tright instanceof StringType) {
                this._left.translate(classGen, methodGen);
                this._left.startIterator(classGen, methodGen);
                this._right.translate(classGen, methodGen);
                il.append(new PUSH(cpg, this._op));
                il.append(methodGen.loadDOM());
                final int cmp = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "compare", "(" + tleft.toSignature() + tright.toSignature() + "I" + "Lorg/apache/xalan/xsltc/DOM;" + ")Z");
                il.append(new INVOKESTATIC(cmp));
                return;
            }
            this._left.translate(classGen, methodGen);
            this._left.startIterator(classGen, methodGen);
            this._right.translate(classGen, methodGen);
            this._right.startIterator(classGen, methodGen);
            if (tright instanceof ResultTreeType) {
                tright.translateTo(classGen, methodGen, Type.String);
                tright = Type.String;
            }
            il.append(new PUSH(cpg, this._op));
            il.append(methodGen.loadDOM());
            final int compare = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "compare", "(" + tleft.toSignature() + tright.toSignature() + "I" + "Lorg/apache/xalan/xsltc/DOM;" + ")Z");
            il.append(new INVOKESTATIC(compare));
        }
    }
}
