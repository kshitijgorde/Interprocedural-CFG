// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.INVOKEVIRTUAL;
import org.apache.bcel.generic.INVOKESPECIAL;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.NEW;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;
import java.util.Enumeration;
import org.apache.xalan.xsltc.compiler.util.Util;

final class ApplyImports extends Instruction
{
    private QName _modeName;
    private String _functionName;
    private int _precedence;
    
    public void display(final int indent) {
        this.indent(indent);
        Util.println("ApplyTemplates");
        this.indent(indent + 4);
        if (this._modeName != null) {
            this.indent(indent + 4);
            Util.println("mode " + this._modeName);
        }
    }
    
    public boolean hasWithParams() {
        return this.hasContents();
    }
    
    private int getMinPrecedence(final int max) {
        final Stylesheet stylesheet = this.getStylesheet();
        final Stylesheet root = this.getParser().getTopLevelStylesheet();
        int min = max;
        final Enumeration templates = root.getContents().elements();
        while (templates.hasMoreElements()) {
            final SyntaxTreeNode child = templates.nextElement();
            if (child instanceof Template) {
                Stylesheet curr = child.getStylesheet();
                while (curr != null && curr != stylesheet) {
                    if (curr._importedFrom != null) {
                        curr = curr._importedFrom;
                    }
                    else if (curr._includedFrom != null) {
                        curr = curr._includedFrom;
                    }
                    else {
                        curr = null;
                    }
                }
                if (curr != stylesheet) {
                    continue;
                }
                final int prec = child.getStylesheet().getImportPrecedence();
                if (prec >= min) {
                    continue;
                }
                min = prec;
            }
        }
        return min;
    }
    
    public void parseContents(final Parser parser) {
        Stylesheet stylesheet = this.getStylesheet();
        stylesheet.setTemplateInlining(false);
        final Template template = this.getTemplate();
        this._modeName = template.getModeName();
        this._precedence = template.getImportPrecedence();
        stylesheet = parser.getTopLevelStylesheet();
        final int maxPrecedence = this._precedence;
        final int minPrecedence = this.getMinPrecedence(maxPrecedence);
        final Mode mode = stylesheet.getMode(this._modeName);
        this._functionName = mode.functionName(minPrecedence, maxPrecedence);
        this.parseChildren(parser);
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        this.typeCheckContents(stable);
        return Type.Void;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final Stylesheet stylesheet = classGen.getStylesheet();
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final int current = methodGen.getLocalIndex("current");
        il.append(classGen.loadTranslet());
        il.append(methodGen.loadDOM());
        final int init = cpg.addMethodref("org.apache.xalan.xsltc.dom.SingletonIterator", "<init>", "(I)V");
        il.append(new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.SingletonIterator")));
        il.append(InstructionConstants.DUP);
        il.append(methodGen.loadCurrentNode());
        il.append(new INVOKESPECIAL(init));
        il.append(methodGen.loadHandler());
        final String className = classGen.getStylesheet().getClassName();
        final String signature = classGen.getApplyTemplatesSig();
        final int applyTemplates = cpg.addMethodref(className, this._functionName, signature);
        il.append(new INVOKEVIRTUAL(applyTemplates));
    }
}
