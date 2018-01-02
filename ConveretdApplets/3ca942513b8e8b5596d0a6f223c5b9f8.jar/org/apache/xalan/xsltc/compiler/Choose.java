// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import com.ibm.xslt4j.bcel.generic.InstructionList;
import java.util.Enumeration;
import com.ibm.xslt4j.bcel.generic.BranchHandle;
import com.ibm.xslt4j.bcel.generic.GOTO;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import com.ibm.xslt4j.bcel.generic.BranchInstruction;
import com.ibm.xslt4j.bcel.generic.InstructionHandle;
import com.ibm.xslt4j.bcel.generic.IFEQ;
import org.apache.xalan.xsltc.compiler.util.Type;
import com.ibm.xslt4j.bcel.generic.InstructionConstants;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import java.util.Vector;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.Util;

final class Choose extends Instruction
{
    public void display(final int indent) {
        this.indent(indent);
        Util.println("Choose");
        this.indent(indent + 4);
        this.displayContents(indent + 4);
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final Vector whenElements = new Vector();
        Otherwise otherwise = null;
        final Enumeration elements = this.elements();
        ErrorMsg error = null;
        final int line = this.getLineNumber();
        while (elements.hasMoreElements()) {
            final Object element = elements.nextElement();
            if (element instanceof When) {
                whenElements.addElement(element);
            }
            else if (element instanceof Otherwise) {
                if (otherwise == null) {
                    otherwise = (Otherwise)element;
                }
                else {
                    error = new ErrorMsg("MULTIPLE_OTHERWISE_ERR", this);
                    this.getParser().reportError(3, error);
                }
            }
            else if (element instanceof Text) {
                ((Text)element).ignore();
            }
            else {
                error = new ErrorMsg("WHEN_ELEMENT_ERR", this);
                this.getParser().reportError(3, error);
            }
        }
        if (whenElements.size() == 0) {
            error = new ErrorMsg("MISSING_WHEN_ERR", this);
            this.getParser().reportError(3, error);
            return;
        }
        final InstructionList il = methodGen.getInstructionList();
        BranchHandle nextElement = null;
        final Vector exitHandles = new Vector();
        InstructionHandle exit = null;
        final Enumeration whens = whenElements.elements();
        while (whens.hasMoreElements()) {
            final When when = whens.nextElement();
            final Expression test = when.getTest();
            InstructionHandle truec = il.getEnd();
            if (nextElement != null) {
                nextElement.setTarget(il.append(InstructionConstants.NOP));
            }
            test.translateDesynthesized(classGen, methodGen);
            if (test instanceof FunctionCall) {
                final FunctionCall call = (FunctionCall)test;
                try {
                    final Type type = call.typeCheck(this.getParser().getSymbolTable());
                    if (type != Type.Boolean) {
                        test._falseList.add(il.append(new IFEQ(null)));
                    }
                }
                catch (TypeCheckError typeCheckError) {}
            }
            truec = il.getEnd();
            if (!when.ignore()) {
                when.translateContents(classGen, methodGen);
            }
            exitHandles.addElement(il.append(new GOTO(null)));
            if (whens.hasMoreElements() || otherwise != null) {
                nextElement = il.append(new GOTO(null));
                test.backPatchFalseList(nextElement);
            }
            else {
                test.backPatchFalseList(exit = il.append(InstructionConstants.NOP));
            }
            test.backPatchTrueList(truec.getNext());
        }
        if (otherwise != null) {
            nextElement.setTarget(il.append(InstructionConstants.NOP));
            otherwise.translateContents(classGen, methodGen);
            exit = il.append(InstructionConstants.NOP);
        }
        final Enumeration exitGotos = exitHandles.elements();
        while (exitGotos.hasMoreElements()) {
            final BranchHandle gotoExit = exitGotos.nextElement();
            gotoExit.setTarget(exit);
        }
    }
}
