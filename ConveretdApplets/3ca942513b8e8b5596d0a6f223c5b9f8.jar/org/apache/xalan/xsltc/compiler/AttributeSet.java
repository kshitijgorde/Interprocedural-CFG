// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import java.util.Enumeration;
import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.InstructionConstants;
import com.ibm.xslt4j.bcel.generic.Instruction;
import com.ibm.xslt4j.bcel.generic.INVOKESPECIAL;
import com.ibm.xslt4j.bcel.generic.ClassGen;
import org.apache.xalan.xsltc.compiler.util.AttributeSetMethodGenerator;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;
import java.util.Vector;
import org.apache.xalan.xsltc.compiler.util.Util;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import org.apache.xml.utils.XML11Char;

final class AttributeSet extends TopLevelElement
{
    private static final String AttributeSetPrefix = "$as$";
    private QName _name;
    private UseAttributeSets _useSets;
    private AttributeSet _mergeSet;
    private String _method;
    private boolean _ignore;
    
    AttributeSet() {
        this._ignore = false;
    }
    
    public QName getName() {
        return this._name;
    }
    
    public String getMethodName() {
        return this._method;
    }
    
    public void ignore() {
        this._ignore = true;
    }
    
    public void parseContents(final Parser parser) {
        final String name = this.getAttribute("name");
        if (!XML11Char.isXML11ValidQName(name)) {
            final ErrorMsg err = new ErrorMsg("INVALID_QNAME_ERR", name, this);
            parser.reportError(3, err);
        }
        this._name = parser.getQNameIgnoreDefaultNs(name);
        if (this._name == null || this._name.equals("")) {
            final ErrorMsg msg = new ErrorMsg("UNNAMED_ATTRIBSET_ERR", this);
            parser.reportError(3, msg);
        }
        final String useSets = this.getAttribute("use-attribute-sets");
        if (useSets.length() > 0) {
            if (!Util.isValidQNames(useSets)) {
                final ErrorMsg err2 = new ErrorMsg("INVALID_QNAME_ERR", useSets, this);
                parser.reportError(3, err2);
            }
            this._useSets = new UseAttributeSets(useSets, parser);
        }
        final Vector contents = this.getContents();
        for (int count = contents.size(), i = 0; i < count; ++i) {
            final SyntaxTreeNode child = contents.elementAt(i);
            if (child instanceof XslAttribute) {
                parser.getSymbolTable().setCurrentNode(child);
                child.parseContents(parser);
            }
            else if (!(child instanceof Text)) {
                final ErrorMsg msg2 = new ErrorMsg("ILLEGAL_CHILD_ERR", this);
                parser.reportError(3, msg2);
            }
        }
        parser.getSymbolTable().setCurrentNode(this);
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        if (this._ignore) {
            return Type.Void;
        }
        this._mergeSet = stable.addAttributeSet(this);
        this._method = "$as$" + this.getXSLTC().nextAttributeSetSerial();
        if (this._useSets != null) {
            this._useSets.typeCheck(stable);
        }
        this.typeCheckContents(stable);
        return Type.Void;
    }
    
    public void translate(final ClassGenerator classGen, MethodGenerator methodGen) {
        if (this._ignore) {
            return;
        }
        methodGen = new AttributeSetMethodGenerator(this._method, classGen);
        if (this._mergeSet != null) {
            final ConstantPoolGen cpg = classGen.getConstantPool();
            final InstructionList il = methodGen.getInstructionList();
            final String methodName = this._mergeSet.getMethodName();
            il.append(classGen.loadTranslet());
            il.append(methodGen.loadDOM());
            il.append(methodGen.loadIterator());
            il.append(methodGen.loadHandler());
            final int method = cpg.addMethodref(classGen.getClassName(), methodName, "(Lorg/apache/xalan/xsltc/DOM;Lorg/apache/xml/dtm/DTMAxisIterator;Lorg/apache/xml/serializer/SerializationHandler;)V");
            il.append(new INVOKESPECIAL(method));
        }
        if (this._useSets != null) {
            this._useSets.translate(classGen, methodGen);
        }
        final Enumeration attributes = this.elements();
        while (attributes.hasMoreElements()) {
            final SyntaxTreeNode element = attributes.nextElement();
            if (element instanceof XslAttribute) {
                final XslAttribute attribute = (XslAttribute)element;
                attribute.translate(classGen, methodGen);
            }
        }
        final InstructionList il = methodGen.getInstructionList();
        il.append(InstructionConstants.RETURN);
        methodGen.stripAttributes(true);
        methodGen.setMaxLocals();
        methodGen.setMaxStack();
        methodGen.removeNOPs();
        classGen.addMethod(methodGen.getMethod());
    }
    
    public String toString() {
        final StringBuffer buf = new StringBuffer("attribute-set: ");
        final Enumeration attributes = this.elements();
        while (attributes.hasMoreElements()) {
            final XslAttribute attribute = attributes.nextElement();
            buf.append(attribute);
        }
        return buf.toString();
    }
}
