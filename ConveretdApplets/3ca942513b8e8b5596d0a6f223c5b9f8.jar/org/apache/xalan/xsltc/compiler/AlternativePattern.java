// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.BranchInstruction;
import com.ibm.xslt4j.bcel.generic.InstructionHandle;
import com.ibm.xslt4j.bcel.generic.GOTO;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;

final class AlternativePattern extends Pattern
{
    private final Pattern _left;
    private final Pattern _right;
    
    public AlternativePattern(final Pattern left, final Pattern right) {
        this._left = left;
        this._right = right;
    }
    
    public void setParser(final Parser parser) {
        super.setParser(parser);
        this._left.setParser(parser);
        this._right.setParser(parser);
    }
    
    public Pattern getLeft() {
        return this._left;
    }
    
    public Pattern getRight() {
        return this._right;
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        this._left.typeCheck(stable);
        this._right.typeCheck(stable);
        return null;
    }
    
    public double getPriority() {
        final double left = this._left.getPriority();
        final double right = this._right.getPriority();
        if (left < right) {
            return left;
        }
        return right;
    }
    
    public String toString() {
        return "alternative(" + this._left + ", " + this._right + ')';
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final InstructionList il = methodGen.getInstructionList();
        this._left.translate(classGen, methodGen);
        final InstructionHandle gotot = il.append(new GOTO(null));
        il.append(methodGen.loadContextNode());
        this._right.translate(classGen, methodGen);
        this._left._trueList.backPatch(gotot);
        this._left._falseList.backPatch(gotot.getNext());
        super._trueList.append(this._right._trueList.add(gotot));
        super._falseList.append(this._right._falseList);
    }
}
