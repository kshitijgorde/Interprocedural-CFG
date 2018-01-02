// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import com.ibm.xslt4j.bcel.generic.LocalVariableGen;
import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.INVOKEINTERFACE;
import com.ibm.xslt4j.bcel.generic.INVOKEVIRTUAL;
import com.ibm.xslt4j.bcel.generic.INVOKESPECIAL;
import com.ibm.xslt4j.bcel.generic.ALOAD;
import com.ibm.xslt4j.bcel.generic.InstructionConstants;
import com.ibm.xslt4j.bcel.generic.NEW;
import com.ibm.xslt4j.bcel.generic.Instruction;
import com.ibm.xslt4j.bcel.generic.ASTORE;
import com.ibm.xslt4j.bcel.generic.InstructionHandle;
import org.apache.xalan.xsltc.compiler.util.Util;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;

final class ParentLocationPath extends RelativeLocationPath
{
    private Expression _step;
    private final RelativeLocationPath _path;
    private Type stype;
    private boolean _orderNodes;
    private boolean _axisMismatch;
    
    public ParentLocationPath(final RelativeLocationPath path, final Expression step) {
        this._orderNodes = false;
        this._axisMismatch = false;
        this._path = path;
        this._step = step;
        this._path.setParent(this);
        this._step.setParent(this);
        if (this._step instanceof Step) {
            this._axisMismatch = this.checkAxisMismatch();
        }
    }
    
    public void setAxis(final int axis) {
        this._path.setAxis(axis);
    }
    
    public int getAxis() {
        return this._path.getAxis();
    }
    
    public RelativeLocationPath getPath() {
        return this._path;
    }
    
    public Expression getStep() {
        return this._step;
    }
    
    public void setParser(final Parser parser) {
        super.setParser(parser);
        this._step.setParser(parser);
        this._path.setParser(parser);
    }
    
    public String toString() {
        return "ParentLocationPath(" + this._path + ", " + this._step + ')';
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        this.stype = this._step.typeCheck(stable);
        this._path.typeCheck(stable);
        if (this._axisMismatch) {
            this.enableNodeOrdering();
        }
        return super._type = Type.NodeSet;
    }
    
    public void enableNodeOrdering() {
        final SyntaxTreeNode parent = this.getParent();
        if (parent instanceof ParentLocationPath) {
            ((ParentLocationPath)parent).enableNodeOrdering();
        }
        else {
            this._orderNodes = true;
        }
    }
    
    public boolean checkAxisMismatch() {
        final int left = this._path.getAxis();
        final int right = ((Step)this._step).getAxis();
        if ((left == 0 || left == 1) && (right == 3 || right == 4 || right == 5 || right == 10 || right == 11 || right == 12)) {
            return true;
        }
        if ((left == 3 && right == 0) || right == 1 || right == 10 || right == 11) {
            return true;
        }
        if (left == 4 || left == 5) {
            return true;
        }
        if ((left == 6 || left == 7) && (right == 6 || right == 10 || right == 11 || right == 12)) {
            return true;
        }
        if ((left == 11 || left == 12) && (right == 4 || right == 5 || right == 6 || right == 7 || right == 10 || right == 11 || right == 12)) {
            return true;
        }
        if (right == 6 && left == 3 && this._path instanceof Step) {
            final int type = ((Step)this._path).getNodeType();
            if (type == 2) {
                return true;
            }
        }
        return false;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        this._path.translate(classGen, methodGen);
        final LocalVariableGen pathTemp = methodGen.addLocalVariable("parent_location_path_tmp1", Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;"), il.getEnd(), null);
        il.append(new ASTORE(pathTemp.getIndex()));
        this._step.translate(classGen, methodGen);
        final LocalVariableGen stepTemp = methodGen.addLocalVariable("parent_location_path_tmp2", Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;"), il.getEnd(), null);
        il.append(new ASTORE(stepTemp.getIndex()));
        final int initSI = cpg.addMethodref("org.apache.xalan.xsltc.dom.StepIterator", "<init>", "(Lorg/apache/xml/dtm/DTMAxisIterator;Lorg/apache/xml/dtm/DTMAxisIterator;)V");
        il.append(new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.StepIterator")));
        il.append(InstructionConstants.DUP);
        il.append(new ALOAD(pathTemp.getIndex()));
        il.append(new ALOAD(stepTemp.getIndex()));
        il.append(new INVOKESPECIAL(initSI));
        Expression stp = this._step;
        if (stp instanceof ParentLocationPath) {
            stp = ((ParentLocationPath)stp).getStep();
        }
        if (this._path instanceof Step && stp instanceof Step) {
            final int path = ((Step)this._path).getAxis();
            final int step = ((Step)stp).getAxis();
            if ((path == 5 && step == 3) || (path == 11 && step == 10)) {
                final int incl = cpg.addMethodref("org.apache.xml.dtm.ref.DTMAxisIteratorBase", "includeSelf", "()Lorg/apache/xml/dtm/DTMAxisIterator;");
                il.append(new INVOKEVIRTUAL(incl));
            }
        }
        if (this._orderNodes) {
            final int order = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "orderNodes", "(Lorg/apache/xml/dtm/DTMAxisIterator;I)Lorg/apache/xml/dtm/DTMAxisIterator;");
            il.append(methodGen.loadDOM());
            il.append(InstructionConstants.SWAP);
            il.append(methodGen.loadContextNode());
            il.append(new INVOKEINTERFACE(order, 3));
        }
    }
}
