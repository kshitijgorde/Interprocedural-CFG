// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.LocalVariableGen;
import org.apache.bcel.generic.INVOKESTATIC;
import org.apache.bcel.generic.ALOAD;
import org.apache.bcel.generic.ASTORE;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.CompoundInstruction;
import org.apache.bcel.generic.PUSH;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;
import org.apache.xml.utils.XMLChar;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import org.apache.xalan.xsltc.compiler.util.Util;

final class XslElement extends Instruction
{
    private String _prefix;
    private boolean _ignore;
    private boolean _isLiteralName;
    private AttributeValueTemplate _name;
    private AttributeValueTemplate _namespace;
    
    XslElement() {
        this._ignore = false;
        this._isLiteralName = true;
    }
    
    public void display(final int indent) {
        this.indent(indent);
        Util.println("Element " + this._name);
        this.displayContents(indent + 4);
    }
    
    public boolean declaresDefaultNS() {
        return false;
    }
    
    public void parseContents(final Parser parser) {
        final SymbolTable stable = parser.getSymbolTable();
        String name = this.getAttribute("name");
        if (name == "") {
            final ErrorMsg msg = new ErrorMsg("ILLEGAL_ELEM_NAME_ERR", name, this);
            parser.reportError(4, msg);
            this.parseChildren(parser);
            this._ignore = true;
            return;
        }
        String namespace = this.getAttribute("namespace");
        this._isLiteralName = Util.isLiteral(name);
        if (this._isLiteralName) {
            if (!XMLChar.isValidQName(name)) {
                final ErrorMsg msg2 = new ErrorMsg("ILLEGAL_ELEM_NAME_ERR", name, this);
                parser.reportError(4, msg2);
                this.parseChildren(parser);
                this._ignore = true;
                return;
            }
            final QName qname = parser.getQNameSafe(name);
            String prefix = qname.getPrefix();
            final String local = qname.getLocalPart();
            if (prefix == null) {
                prefix = "";
            }
            if (!this.hasAttribute("namespace")) {
                namespace = this.lookupNamespace(prefix);
                if (namespace == null) {
                    final ErrorMsg err = new ErrorMsg("NAMESPACE_UNDEF_ERR", prefix, this);
                    parser.reportError(4, err);
                    this.parseChildren(parser);
                    this._ignore = true;
                    return;
                }
                this._prefix = prefix;
                this._namespace = new AttributeValueTemplate(namespace, parser, this);
            }
            else {
                if (prefix == "") {
                    if (Util.isLiteral(namespace)) {
                        prefix = this.lookupPrefix(namespace);
                        if (prefix == null) {
                            prefix = stable.generateNamespacePrefix();
                        }
                    }
                    final StringBuffer newName = new StringBuffer(prefix);
                    if (prefix != "") {
                        newName.append(':');
                    }
                    name = newName.append(local).toString();
                }
                this._prefix = prefix;
                this._namespace = new AttributeValueTemplate(namespace, parser, this);
            }
        }
        else {
            this._namespace = ((namespace == "") ? null : new AttributeValueTemplate(namespace, parser, this));
        }
        this._name = new AttributeValueTemplate(name, parser, this);
        final String useSets = this.getAttribute("use-attribute-sets");
        if (useSets.length() > 0) {
            if (!Util.isValidQNames(useSets)) {
                final ErrorMsg err2 = new ErrorMsg("INVALID_QNAME_ERR", useSets, this);
                parser.reportError(3, err2);
            }
            this.setFirstElement(new UseAttributeSets(useSets, parser));
        }
        this.parseChildren(parser);
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        if (!this._ignore) {
            this._name.typeCheck(stable);
            if (this._namespace != null) {
                this._namespace.typeCheck(stable);
            }
        }
        this.typeCheckContents(stable);
        return Type.Void;
    }
    
    public void translateLiteral(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        if (!this._ignore) {
            il.append(methodGen.loadHandler());
            this._name.translate(classGen, methodGen);
            il.append(InstructionConstants.DUP2);
            il.append(methodGen.startElement());
            if (this._namespace != null) {
                il.append(methodGen.loadHandler());
                il.append(new PUSH(cpg, this._prefix));
                this._namespace.translate(classGen, methodGen);
                il.append(methodGen.namespace());
            }
        }
        this.translateContents(classGen, methodGen);
        if (!this._ignore) {
            il.append(methodGen.endElement());
        }
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final LocalVariableGen local = null;
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        if (this._isLiteralName) {
            this.translateLiteral(classGen, methodGen);
            return;
        }
        if (!this._ignore) {
            final LocalVariableGen nameValue = methodGen.addLocalVariable2("nameValue", Util.getJCRefType("Ljava/lang/String;"), il.getEnd());
            this._name.translate(classGen, methodGen);
            il.append(new ASTORE(nameValue.getIndex()));
            il.append(new ALOAD(nameValue.getIndex()));
            final int check = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "checkQName", "(Ljava/lang/String;)V");
            il.append(new INVOKESTATIC(check));
            il.append(methodGen.loadHandler());
            il.append(new ALOAD(nameValue.getIndex()));
            if (this._namespace != null) {
                this._namespace.translate(classGen, methodGen);
            }
            else {
                il.append(InstructionConstants.ACONST_NULL);
            }
            il.append(methodGen.loadHandler());
            il.append(methodGen.loadDOM());
            il.append(methodGen.loadCurrentNode());
            il.append(new INVOKESTATIC(cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "startXslElement", "(Ljava/lang/String;Ljava/lang/String;Lorg/apache/xml/serializer/SerializationHandler;Lorg/apache/xalan/xsltc/DOM;I)Ljava/lang/String;")));
        }
        this.translateContents(classGen, methodGen);
        if (!this._ignore) {
            il.append(methodGen.endElement());
        }
    }
    
    public void translateContents(final ClassGenerator classGen, final MethodGenerator methodGen) {
        for (int n = this.elementCount(), i = 0; i < n; ++i) {
            final SyntaxTreeNode item = this.getContents().elementAt(i);
            if (!this._ignore || !(item instanceof XslAttribute)) {
                item.translate(classGen, methodGen);
            }
        }
    }
}
