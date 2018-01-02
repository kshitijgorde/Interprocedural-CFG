// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.xml.serializer.ToHTMLStream;
import org.apache.xml.serializer.ElemDesc;
import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.InstructionConstants;
import com.ibm.xslt4j.bcel.generic.CompoundInstruction;
import com.ibm.xslt4j.bcel.generic.PUSH;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import java.util.Enumeration;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;
import org.apache.xalan.xsltc.compiler.util.Util;
import java.util.Hashtable;
import java.util.Vector;

final class LiteralElement extends Instruction
{
    private String _name;
    private LiteralElement _literalElemParent;
    private Vector _attributeElements;
    private Hashtable _accessedPrefixes;
    private boolean _allAttributesUnique;
    private static final String XMLNS_STRING = "xmlns";
    
    LiteralElement() {
        this._literalElemParent = null;
        this._attributeElements = null;
        this._accessedPrefixes = null;
        this._allAttributesUnique = false;
    }
    
    public QName getName() {
        return super._qname;
    }
    
    public void display(final int indent) {
        this.indent(indent);
        Util.println("LiteralElement name = " + this._name);
        this.displayContents(indent + 4);
    }
    
    private String accessedNamespace(final String prefix) {
        if (this._literalElemParent != null) {
            final String result = this._literalElemParent.accessedNamespace(prefix);
            if (result != null) {
                return result;
            }
        }
        return (this._accessedPrefixes != null) ? this._accessedPrefixes.get(prefix) : null;
    }
    
    public void registerNamespace(String prefix, final String uri, final SymbolTable stable, final boolean declared) {
        if (this._literalElemParent != null) {
            final String parentUri = this._literalElemParent.accessedNamespace(prefix);
            if (parentUri != null && parentUri.equals(uri)) {
                return;
            }
        }
        if (this._accessedPrefixes == null) {
            this._accessedPrefixes = new Hashtable();
        }
        else if (!declared) {
            final String old = this._accessedPrefixes.get(prefix);
            if (old != null) {
                if (old.equals(uri)) {
                    return;
                }
                prefix = stable.generateNamespacePrefix();
            }
        }
        if (!prefix.equals("xml")) {
            this._accessedPrefixes.put(prefix, uri);
        }
    }
    
    private String translateQName(final QName qname, final SymbolTable stable) {
        final String localname = qname.getLocalPart();
        String prefix = qname.getPrefix();
        if (prefix == null) {
            prefix = "";
        }
        else if (prefix.equals("xmlns")) {
            return "xmlns";
        }
        final String alternative = stable.lookupPrefixAlias(prefix);
        if (alternative != null) {
            stable.excludeNamespaces(prefix);
            prefix = alternative;
        }
        final String uri = this.lookupNamespace(prefix);
        if (uri == null) {
            return localname;
        }
        this.registerNamespace(prefix, uri, stable, false);
        if (prefix != "") {
            return prefix + ":" + localname;
        }
        return localname;
    }
    
    public void addAttribute(final SyntaxTreeNode attribute) {
        if (this._attributeElements == null) {
            this._attributeElements = new Vector(2);
        }
        this._attributeElements.add(attribute);
    }
    
    public void setFirstAttribute(final SyntaxTreeNode attribute) {
        if (this._attributeElements == null) {
            this._attributeElements = new Vector(2);
        }
        this._attributeElements.insertElementAt(attribute, 0);
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        if (this._attributeElements != null) {
            for (int count = this._attributeElements.size(), i = 0; i < count; ++i) {
                final SyntaxTreeNode node = this._attributeElements.elementAt(i);
                node.typeCheck(stable);
            }
        }
        this.typeCheckContents(stable);
        return Type.Void;
    }
    
    public Enumeration getNamespaceScope(SyntaxTreeNode node) {
        final Hashtable all = new Hashtable();
        while (node != null) {
            final Hashtable mapping = node.getPrefixMapping();
            if (mapping != null) {
                final Enumeration prefixes = mapping.keys();
                while (prefixes.hasMoreElements()) {
                    final String prefix = prefixes.nextElement();
                    if (!all.containsKey(prefix)) {
                        all.put(prefix, mapping.get(prefix));
                    }
                }
            }
            node = node.getParent();
        }
        return all.keys();
    }
    
    public void parseContents(final Parser parser) {
        final SymbolTable stable = parser.getSymbolTable();
        stable.setCurrentNode(this);
        final SyntaxTreeNode parent = this.getParent();
        if (parent != null && parent instanceof LiteralElement) {
            this._literalElemParent = (LiteralElement)parent;
        }
        this._name = this.translateQName(super._qname, stable);
        final int count = super._attributes.getLength();
        for (int i = 0; i < count; ++i) {
            final QName qname = parser.getQName(super._attributes.getQName(i));
            final String uri = qname.getNamespace();
            final String val = super._attributes.getValue(i);
            if (qname == parser.getUseAttributeSets()) {
                if (!Util.isValidQNames(val)) {
                    final ErrorMsg err = new ErrorMsg("INVALID_QNAME_ERR", val, this);
                    parser.reportError(3, err);
                }
                this.setFirstAttribute(new UseAttributeSets(val, parser));
            }
            else if (qname == parser.getExtensionElementPrefixes()) {
                stable.excludeNamespaces(val);
            }
            else if (qname == parser.getExcludeResultPrefixes()) {
                stable.excludeNamespaces(val);
            }
            else {
                final String prefix = qname.getPrefix();
                if ((prefix == null || !prefix.equals("xmlns")) && (prefix != null || !qname.getLocalPart().equals("xmlns"))) {
                    if (uri == null || !uri.equals("http://www.w3.org/1999/XSL/Transform")) {
                        final String name = this.translateQName(qname, stable);
                        final LiteralAttribute attr = new LiteralAttribute(name, val, parser, this);
                        this.addAttribute(attr);
                        attr.setParent(this);
                        attr.parseContents(parser);
                    }
                }
            }
        }
        final Enumeration include = this.getNamespaceScope(this);
        while (include.hasMoreElements()) {
            final String prefix2 = include.nextElement();
            if (!prefix2.equals("xml")) {
                final String uri2 = this.lookupNamespace(prefix2);
                if (uri2 == null || stable.isExcludedNamespace(uri2)) {
                    continue;
                }
                this.registerNamespace(prefix2, uri2, stable, true);
            }
        }
        this.parseChildren(parser);
        for (int j = 0; j < count; ++j) {
            final QName qname2 = parser.getQName(super._attributes.getQName(j));
            final String val2 = super._attributes.getValue(j);
            if (qname2 == parser.getExtensionElementPrefixes()) {
                stable.unExcludeNamespaces(val2);
            }
            else if (qname2 == parser.getExcludeResultPrefixes()) {
                stable.unExcludeNamespaces(val2);
            }
        }
    }
    
    protected boolean contextDependent() {
        return this.dependentContents();
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        this._allAttributesUnique = this.checkAttributesUnique();
        il.append(methodGen.loadHandler());
        il.append(new PUSH(cpg, this._name));
        il.append(InstructionConstants.DUP2);
        il.append(methodGen.startElement());
        int j = 0;
        while (j < this.elementCount()) {
            final SyntaxTreeNode item = (SyntaxTreeNode)this.elementAt(j);
            if (item instanceof Variable) {
                item.translate(classGen, methodGen);
                this.removeElement(item);
            }
            else {
                ++j;
            }
        }
        if (this._accessedPrefixes != null) {
            boolean declaresDefaultNS = false;
            final Enumeration e = this._accessedPrefixes.keys();
            while (e.hasMoreElements()) {
                final String prefix = e.nextElement();
                final String uri = this._accessedPrefixes.get(prefix);
                if (uri != "" || prefix != "") {
                    if (prefix == "") {
                        declaresDefaultNS = true;
                    }
                    il.append(methodGen.loadHandler());
                    il.append(new PUSH(cpg, prefix));
                    il.append(new PUSH(cpg, uri));
                    il.append(methodGen.namespace());
                }
            }
            if (!declaresDefaultNS && super._parent instanceof XslElement && ((XslElement)super._parent).declaresDefaultNS()) {
                il.append(methodGen.loadHandler());
                il.append(new PUSH(cpg, ""));
                il.append(new PUSH(cpg, ""));
                il.append(methodGen.namespace());
            }
        }
        if (this._attributeElements != null) {
            for (int count = this._attributeElements.size(), i = 0; i < count; ++i) {
                final SyntaxTreeNode node = this._attributeElements.elementAt(i);
                if (!(node instanceof XslAttribute)) {
                    node.translate(classGen, methodGen);
                }
            }
        }
        this.translateContents(classGen, methodGen);
        il.append(methodGen.endElement());
    }
    
    private boolean isHTMLOutput() {
        return this.getStylesheet().getOutputMethod() == 2;
    }
    
    public ElemDesc getElemDesc() {
        if (this.isHTMLOutput()) {
            return ToHTMLStream.getElemDesc(this._name);
        }
        return null;
    }
    
    public boolean allAttributesUnique() {
        return this._allAttributesUnique;
    }
    
    private boolean checkAttributesUnique() {
        final boolean hasHiddenXslAttribute = this.canProduceAttributeNodes(this, true);
        if (hasHiddenXslAttribute) {
            return false;
        }
        if (this._attributeElements != null) {
            final int numAttrs = this._attributeElements.size();
            Hashtable attrsTable = null;
            for (int i = 0; i < numAttrs; ++i) {
                final SyntaxTreeNode node = this._attributeElements.elementAt(i);
                if (node instanceof UseAttributeSets) {
                    return false;
                }
                if (node instanceof XslAttribute) {
                    if (attrsTable == null) {
                        attrsTable = new Hashtable();
                        for (int k = 0; k < i; ++k) {
                            final SyntaxTreeNode n = this._attributeElements.elementAt(k);
                            if (n instanceof LiteralAttribute) {
                                final LiteralAttribute literalAttr = (LiteralAttribute)n;
                                attrsTable.put(literalAttr.getName(), literalAttr);
                            }
                        }
                    }
                    final XslAttribute xslAttr = (XslAttribute)node;
                    final AttributeValue attrName = xslAttr.getName();
                    if (attrName instanceof AttributeValueTemplate) {
                        return false;
                    }
                    if (attrName instanceof SimpleAttributeValue) {
                        final SimpleAttributeValue simpleAttr = (SimpleAttributeValue)attrName;
                        final String name = simpleAttr.toString();
                        if (name != null && attrsTable.get(name) != null) {
                            return false;
                        }
                        if (name != null) {
                            attrsTable.put(name, xslAttr);
                        }
                    }
                }
            }
        }
        return true;
    }
    
    private boolean canProduceAttributeNodes(final SyntaxTreeNode node, final boolean ignoreXslAttribute) {
        final Vector contents = node.getContents();
        for (int size = contents.size(), i = 0; i < size; ++i) {
            final SyntaxTreeNode child = contents.elementAt(i);
            if (child instanceof Text) {
                final Text text = (Text)child;
                if (!text.isIgnore()) {
                    return false;
                }
            }
            else {
                if (child instanceof LiteralElement || child instanceof ValueOf || child instanceof XslElement || child instanceof Comment || child instanceof Number || child instanceof ProcessingInstruction) {
                    return false;
                }
                if (child instanceof XslAttribute) {
                    if (!ignoreXslAttribute) {
                        return true;
                    }
                }
                else {
                    if (child instanceof CallTemplate || child instanceof ApplyTemplates || child instanceof Copy || child instanceof CopyOf) {
                        return true;
                    }
                    if ((child instanceof If || child instanceof ForEach) && this.canProduceAttributeNodes(child, false)) {
                        return true;
                    }
                    if (child instanceof Choose) {
                        final Vector chooseContents = child.getContents();
                        for (int num = chooseContents.size(), k = 0; k < num; ++k) {
                            final SyntaxTreeNode chooseChild = chooseContents.elementAt(k);
                            if ((chooseChild instanceof When || chooseChild instanceof Otherwise) && this.canProduceAttributeNodes(chooseChild, false)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
