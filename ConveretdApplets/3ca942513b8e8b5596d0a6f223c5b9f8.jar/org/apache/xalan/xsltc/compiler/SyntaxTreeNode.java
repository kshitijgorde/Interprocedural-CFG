// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.INVOKEVIRTUAL;
import com.ibm.xslt4j.bcel.generic.CHECKCAST;
import com.ibm.xslt4j.bcel.generic.GETFIELD;
import com.ibm.xslt4j.bcel.generic.INVOKESPECIAL;
import com.ibm.xslt4j.bcel.generic.NEWARRAY;
import com.ibm.xslt4j.bcel.generic.ANEWARRAY;
import com.ibm.xslt4j.bcel.generic.ICONST;
import com.ibm.xslt4j.bcel.generic.DUP_X1;
import com.ibm.xslt4j.bcel.generic.NEW;
import com.ibm.xslt4j.bcel.generic.InstructionConstants;
import com.ibm.xslt4j.bcel.generic.Instruction;
import com.ibm.xslt4j.bcel.generic.INVOKEINTERFACE;
import com.ibm.xslt4j.bcel.generic.CompoundInstruction;
import com.ibm.xslt4j.bcel.generic.PUSH;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;
import java.util.Enumeration;
import org.xml.sax.Attributes;
import java.util.Hashtable;
import org.apache.xalan.xsltc.runtime.AttributeList;
import java.util.Vector;

public abstract class SyntaxTreeNode implements Constants
{
    private Parser _parser;
    protected SyntaxTreeNode _parent;
    private Stylesheet _stylesheet;
    private Template _template;
    private final Vector _contents;
    protected QName _qname;
    private int _line;
    protected AttributeList _attributes;
    private Hashtable _prefixMapping;
    static final SyntaxTreeNode Dummy;
    protected static final int IndentIncrement = 4;
    private static final char[] _spaces;
    
    public SyntaxTreeNode() {
        this._contents = new Vector(2);
        this._attributes = null;
        this._prefixMapping = null;
        this._line = 0;
        this._qname = null;
    }
    
    public SyntaxTreeNode(final int line) {
        this._contents = new Vector(2);
        this._attributes = null;
        this._prefixMapping = null;
        this._line = line;
        this._qname = null;
    }
    
    public SyntaxTreeNode(final String uri, final String prefix, final String local) {
        this._contents = new Vector(2);
        this._attributes = null;
        this._prefixMapping = null;
        this._line = 0;
        this.setQName(uri, prefix, local);
    }
    
    protected final void setLineNumber(final int line) {
        this._line = line;
    }
    
    public final int getLineNumber() {
        if (this._line > 0) {
            return this._line;
        }
        final SyntaxTreeNode parent = this.getParent();
        return (parent != null) ? parent.getLineNumber() : 0;
    }
    
    protected void setQName(final QName qname) {
        this._qname = qname;
    }
    
    protected void setQName(final String uri, final String prefix, final String localname) {
        this._qname = new QName(uri, prefix, localname);
    }
    
    protected QName getQName() {
        return this._qname;
    }
    
    protected void setAttributes(final AttributeList attributes) {
        this._attributes = attributes;
    }
    
    protected String getAttribute(final String qname) {
        if (this._attributes == null) {
            return "";
        }
        final String value = this._attributes.getValue(qname);
        return (value == null || value.equals("")) ? "" : value;
    }
    
    protected String getAttribute(final String prefix, final String localName) {
        return this.getAttribute(prefix + ':' + localName);
    }
    
    protected boolean hasAttribute(final String qname) {
        return this._attributes != null && this._attributes.getValue(qname) != null;
    }
    
    protected void addAttribute(final String qname, final String value) {
        this._attributes.add(qname, value);
    }
    
    protected Attributes getAttributes() {
        return this._attributes;
    }
    
    protected void setPrefixMapping(final Hashtable mapping) {
        this._prefixMapping = mapping;
    }
    
    protected Hashtable getPrefixMapping() {
        return this._prefixMapping;
    }
    
    protected void addPrefixMapping(final String prefix, final String uri) {
        if (this._prefixMapping == null) {
            this._prefixMapping = new Hashtable();
        }
        this._prefixMapping.put(prefix, uri);
    }
    
    protected String lookupNamespace(final String prefix) {
        String uri = null;
        if (this._prefixMapping != null) {
            uri = this._prefixMapping.get(prefix);
        }
        if (uri == null && this._parent != null) {
            uri = this._parent.lookupNamespace(prefix);
            if (prefix == "" && uri == null) {
                uri = "";
            }
        }
        return uri;
    }
    
    protected String lookupPrefix(final String uri) {
        String prefix = null;
        if (this._prefixMapping != null && this._prefixMapping.contains(uri)) {
            final Enumeration prefixes = this._prefixMapping.keys();
            while (prefixes.hasMoreElements()) {
                prefix = prefixes.nextElement();
                final String mapsTo = this._prefixMapping.get(prefix);
                if (mapsTo.equals(uri)) {
                    return prefix;
                }
            }
        }
        else if (this._parent != null) {
            prefix = this._parent.lookupPrefix(uri);
            if (uri == "" && prefix == null) {
                prefix = "";
            }
        }
        return prefix;
    }
    
    protected void setParser(final Parser parser) {
        this._parser = parser;
    }
    
    public final Parser getParser() {
        return this._parser;
    }
    
    protected void setParent(final SyntaxTreeNode parent) {
        if (this._parent == null) {
            this._parent = parent;
        }
    }
    
    protected final SyntaxTreeNode getParent() {
        return this._parent;
    }
    
    protected final boolean isDummy() {
        return this == SyntaxTreeNode.Dummy;
    }
    
    protected int getImportPrecedence() {
        final Stylesheet stylesheet = this.getStylesheet();
        if (stylesheet == null) {
            return Integer.MIN_VALUE;
        }
        return stylesheet.getImportPrecedence();
    }
    
    public Stylesheet getStylesheet() {
        if (this._stylesheet == null) {
            SyntaxTreeNode parent;
            for (parent = this; parent != null; parent = parent.getParent()) {
                if (parent instanceof Stylesheet) {
                    return (Stylesheet)parent;
                }
            }
            this._stylesheet = (Stylesheet)parent;
        }
        return this._stylesheet;
    }
    
    protected Template getTemplate() {
        if (this._template == null) {
            SyntaxTreeNode parent;
            for (parent = this; parent != null && !(parent instanceof Template); parent = parent.getParent()) {}
            this._template = (Template)parent;
        }
        return this._template;
    }
    
    protected final XSLTC getXSLTC() {
        return this._parser.getXSLTC();
    }
    
    protected final SymbolTable getSymbolTable() {
        return (this._parser == null) ? null : this._parser.getSymbolTable();
    }
    
    public void parseContents(final Parser parser) {
        this.parseChildren(parser);
    }
    
    protected final void parseChildren(final Parser parser) {
        Vector locals = null;
        for (int count = this._contents.size(), i = 0; i < count; ++i) {
            final SyntaxTreeNode child = this._contents.elementAt(i);
            parser.getSymbolTable().setCurrentNode(child);
            child.parseContents(parser);
            final QName varOrParamName = this.updateScope(parser, child);
            if (varOrParamName != null) {
                if (locals == null) {
                    locals = new Vector(2);
                }
                locals.addElement(varOrParamName);
            }
        }
        parser.getSymbolTable().setCurrentNode(this);
        if (locals != null) {
            for (int nLocals = locals.size(), j = 0; j < nLocals; ++j) {
                parser.removeVariable(locals.elementAt(j));
            }
        }
    }
    
    protected QName updateScope(final Parser parser, final SyntaxTreeNode node) {
        if (node instanceof Variable) {
            final Variable var = (Variable)node;
            parser.addVariable(var);
            return var.getName();
        }
        if (node instanceof Param) {
            final Param param = (Param)node;
            parser.addParameter(param);
            return param.getName();
        }
        return null;
    }
    
    public abstract Type typeCheck(final SymbolTable p0) throws TypeCheckError;
    
    protected Type typeCheckContents(final SymbolTable stable) throws TypeCheckError {
        for (int n = this.elementCount(), i = 0; i < n; ++i) {
            final SyntaxTreeNode item = this._contents.elementAt(i);
            item.typeCheck(stable);
        }
        return Type.Void;
    }
    
    public abstract void translate(final ClassGenerator p0, final MethodGenerator p1);
    
    protected void translateContents(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final int n = this.elementCount();
        for (int i = 0; i < n; ++i) {
            final SyntaxTreeNode item = this._contents.elementAt(i);
            item.translate(classGen, methodGen);
        }
        for (int j = 0; j < n; ++j) {
            if (this._contents.elementAt(j) instanceof VariableBase) {
                final VariableBase var = this._contents.elementAt(j);
                var.unmapRegister(methodGen);
            }
        }
    }
    
    private boolean isSimpleRTF(final SyntaxTreeNode node) {
        final Vector contents = node.getContents();
        for (int i = 0; i < contents.size(); ++i) {
            final SyntaxTreeNode item = contents.elementAt(i);
            if (!this.isTextElement(item, false)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isAdaptiveRTF(final SyntaxTreeNode node) {
        final Vector contents = node.getContents();
        for (int i = 0; i < contents.size(); ++i) {
            final SyntaxTreeNode item = contents.elementAt(i);
            if (!this.isTextElement(item, true)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isTextElement(final SyntaxTreeNode node, final boolean doExtendedCheck) {
        if (node instanceof ValueOf || node instanceof Number || node instanceof Text) {
            return true;
        }
        if (node instanceof If) {
            return doExtendedCheck ? this.isAdaptiveRTF(node) : this.isSimpleRTF(node);
        }
        if (node instanceof Choose) {
            final Vector contents = node.getContents();
            for (int i = 0; i < contents.size(); ++i) {
                final SyntaxTreeNode item = contents.elementAt(i);
                if (!(item instanceof Text)) {
                    if (item instanceof When || item instanceof Otherwise) {
                        if (doExtendedCheck && this.isAdaptiveRTF(item)) {
                            continue;
                        }
                        if (!doExtendedCheck && this.isSimpleRTF(item)) {
                            continue;
                        }
                    }
                    return false;
                }
            }
            return true;
        }
        return doExtendedCheck && (node instanceof CallTemplate || node instanceof ApplyTemplates);
    }
    
    protected void compileResultTree(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final Stylesheet stylesheet = classGen.getStylesheet();
        final boolean isSimple = this.isSimpleRTF(this);
        boolean isAdaptive = false;
        if (!isSimple) {
            isAdaptive = this.isAdaptiveRTF(this);
        }
        final int rtfType = isSimple ? 0 : (isAdaptive ? 1 : 2);
        il.append(methodGen.loadHandler());
        final String DOM_CLASS = classGen.getDOMClass();
        il.append(methodGen.loadDOM());
        int index = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getResultTreeFrag", "(IIZ)Lorg/apache/xalan/xsltc/DOM;");
        il.append(new PUSH(cpg, 32));
        il.append(new PUSH(cpg, rtfType));
        il.append(new PUSH(cpg, stylesheet.callsNodeset()));
        il.append(new INVOKEINTERFACE(index, 4));
        il.append(InstructionConstants.DUP);
        index = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getOutputDomBuilder", "()Lorg/apache/xml/serializer/SerializationHandler;");
        il.append(new INVOKEINTERFACE(index, 1));
        il.append(InstructionConstants.DUP);
        il.append(methodGen.storeHandler());
        il.append(methodGen.startDocument());
        this.translateContents(classGen, methodGen);
        il.append(methodGen.loadHandler());
        il.append(methodGen.endDocument());
        if (stylesheet.callsNodeset() && !DOM_CLASS.equals("org/apache/xalan/xsltc/DOM")) {
            index = cpg.addMethodref("org/apache/xalan/xsltc/dom/DOMAdapter", "<init>", "(Lorg/apache/xalan/xsltc/DOM;[Ljava/lang/String;[Ljava/lang/String;[I[Ljava/lang/String;)V");
            il.append(new NEW(cpg.addClass("org/apache/xalan/xsltc/dom/DOMAdapter")));
            il.append(new DUP_X1());
            il.append(InstructionConstants.SWAP);
            if (!stylesheet.callsNodeset()) {
                il.append(new ICONST(0));
                il.append(new ANEWARRAY(cpg.addClass("java.lang.String")));
                il.append(InstructionConstants.DUP);
                il.append(InstructionConstants.DUP);
                il.append(new ICONST(0));
                il.append(new NEWARRAY(com.ibm.xslt4j.bcel.generic.Type.INT));
                il.append(InstructionConstants.SWAP);
                il.append(new INVOKESPECIAL(index));
            }
            else {
                il.append(InstructionConstants.ALOAD_0);
                il.append(new GETFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "namesArray", "[Ljava/lang/String;")));
                il.append(InstructionConstants.ALOAD_0);
                il.append(new GETFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "urisArray", "[Ljava/lang/String;")));
                il.append(InstructionConstants.ALOAD_0);
                il.append(new GETFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "typesArray", "[I")));
                il.append(InstructionConstants.ALOAD_0);
                il.append(new GETFIELD(cpg.addFieldref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "namespaceArray", "[Ljava/lang/String;")));
                il.append(new INVOKESPECIAL(index));
                il.append(InstructionConstants.DUP);
                il.append(methodGen.loadDOM());
                il.append(new CHECKCAST(cpg.addClass(classGen.getDOMClass())));
                il.append(InstructionConstants.SWAP);
                index = cpg.addMethodref("org.apache.xalan.xsltc.dom.MultiDOM", "addDOMAdapter", "(Lorg/apache/xalan/xsltc/dom/DOMAdapter;)I");
                il.append(new INVOKEVIRTUAL(index));
                il.append(InstructionConstants.POP);
            }
        }
        il.append(InstructionConstants.SWAP);
        il.append(methodGen.storeHandler());
    }
    
    protected boolean contextDependent() {
        return true;
    }
    
    protected boolean dependentContents() {
        for (int n = this.elementCount(), i = 0; i < n; ++i) {
            final SyntaxTreeNode item = this._contents.elementAt(i);
            if (item.contextDependent()) {
                return true;
            }
        }
        return false;
    }
    
    protected final void addElement(final SyntaxTreeNode element) {
        this._contents.addElement(element);
        element.setParent(this);
    }
    
    protected final void setFirstElement(final SyntaxTreeNode element) {
        this._contents.insertElementAt(element, 0);
        element.setParent(this);
    }
    
    protected final void removeElement(final SyntaxTreeNode element) {
        this._contents.remove(element);
        element.setParent(null);
    }
    
    protected final Vector getContents() {
        return this._contents;
    }
    
    protected final boolean hasContents() {
        return this.elementCount() > 0;
    }
    
    protected final int elementCount() {
        return this._contents.size();
    }
    
    protected final Enumeration elements() {
        return this._contents.elements();
    }
    
    protected final Object elementAt(final int pos) {
        return this._contents.elementAt(pos);
    }
    
    protected final SyntaxTreeNode lastChild() {
        if (this._contents.size() == 0) {
            return null;
        }
        return this._contents.lastElement();
    }
    
    public void display(final int indent) {
        this.displayContents(indent);
    }
    
    protected void displayContents(final int indent) {
        for (int n = this.elementCount(), i = 0; i < n; ++i) {
            final SyntaxTreeNode item = this._contents.elementAt(i);
            item.display(indent);
        }
    }
    
    protected final void indent(final int indent) {
        System.out.print(new String(SyntaxTreeNode._spaces, 0, indent));
    }
    
    protected void reportError(final SyntaxTreeNode element, final Parser parser, final String errorCode, final String message) {
        final ErrorMsg error = new ErrorMsg(errorCode, message, element);
        parser.reportError(3, error);
    }
    
    protected void reportWarning(final SyntaxTreeNode element, final Parser parser, final String errorCode, final String message) {
        final ErrorMsg error = new ErrorMsg(errorCode, message, element);
        parser.reportError(4, error);
    }
    
    static {
        Dummy = new AbsolutePathPattern(null);
        _spaces = "                                                       ".toCharArray();
    }
}
