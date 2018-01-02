// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.tree;

import java.util.Locale;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Document;
import org.w3c.dom.DOMException;
import java.io.IOException;
import org.w3c.dom.Attr;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

abstract class NodeBase implements Node, NodeEx, NodeList, XmlWritable
{
    private ParentNode parent;
    private int parentIndex;
    XmlDocument ownerDocument;
    boolean readonly;
    
    NodeBase() {
        this.parentIndex = -1;
    }
    
    ParentNode getParentImpl() {
        return this.parent;
    }
    
    public boolean isReadonly() {
        return this.readonly;
    }
    
    public void setReadonly(final boolean deep) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* this */
        //     1: iconst_1       
        //     2: putfield        org/apache/crimson/tree/NodeBase.readonly:Z
        //     5: iload_1         /* deep */
        //     6: ifeq            38
        //     9: new             Lorg/apache/crimson/tree/TreeWalker;
        //    12: dup            
        //    13: aload_0         /* this */
        //    14: invokespecial   org/apache/crimson/tree/TreeWalker.<init>:(Lorg/w3c/dom/Node;)V
        //    17: astore_2        /* walker */
        //    18: goto            29
        //    21: aload_3        
        //    22: checkcast       Lorg/apache/crimson/tree/NodeBase;
        //    25: iconst_0       
        //    26: invokevirtual   org/apache/crimson/tree/NodeBase.setReadonly:(Z)V
        //    29: aload_2         /* walker */
        //    30: invokevirtual   org/apache/crimson/tree/TreeWalker.getNext:()Lorg/w3c/dom/Node;
        //    33: dup            
        //    34: astore_3        /* next */
        //    35: ifnonnull       21
        //    38: return         
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  ------------------------------------
        //  0      39      0     this    Lorg/apache/crimson/tree/NodeBase;
        //  0      39      1     deep    Z
        //  18     20      2     walker  Lorg/apache/crimson/tree/TreeWalker;
        //  35     3       3     next    Lorg/w3c/dom/Node;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public String getLanguage() {
        return this.getInheritedAttribute("xml:lang");
    }
    
    public String getInheritedAttribute(final String name) {
        NodeBase current = this;
        Attr value = null;
        do {
            if (current instanceof ElementNode) {
                final ElementNode e = (ElementNode)current;
                if ((value = e.getAttributeNode(name)) != null) {
                    break;
                }
            }
            current = current.getParentImpl();
        } while (current != null);
        if (value != null) {
            return value.getValue();
        }
        return null;
    }
    
    public void writeChildrenXml(final XmlWriteContext context) throws IOException {
    }
    
    public Node getParentNode() {
        return this.parent;
    }
    
    void setParentNode(final ParentNode arg, final int index) throws DOMException {
        if (this.parent != null && arg != null) {
            this.parent.removeChild(this);
        }
        this.parent = arg;
        this.parentIndex = index;
    }
    
    void setOwnerDocument(final XmlDocument doc) {
        this.ownerDocument = doc;
    }
    
    public Document getOwnerDocument() {
        return this.ownerDocument;
    }
    
    public boolean hasChildNodes() {
        return false;
    }
    
    public void setNodeValue(final String value) {
        if (this.readonly) {
            throw new DomEx((short)7);
        }
    }
    
    public String getNodeValue() {
        return null;
    }
    
    public Node getFirstChild() {
        return null;
    }
    
    public int getLength() {
        return 0;
    }
    
    public Node item(final int i) {
        return null;
    }
    
    public NodeList getChildNodes() {
        return this;
    }
    
    public Node getLastChild() {
        return null;
    }
    
    public Node appendChild(final Node newChild) throws DOMException {
        throw new DomEx((short)3);
    }
    
    public Node insertBefore(final Node newChild, final Node refChild) throws DOMException {
        throw new DomEx((short)3);
    }
    
    public Node replaceChild(final Node newChild, final Node refChild) throws DOMException {
        throw new DomEx((short)3);
    }
    
    public Node removeChild(final Node oldChild) throws DOMException {
        throw new DomEx((short)3);
    }
    
    public Node getNextSibling() {
        if (this.parent == null) {
            return null;
        }
        if (this.parentIndex < 0 || this.parent.item(this.parentIndex) != this) {
            this.parentIndex = this.parent.getIndexOf(this);
        }
        return this.parent.item(this.parentIndex + 1);
    }
    
    public Node getPreviousSibling() {
        if (this.parent == null) {
            return null;
        }
        if (this.parentIndex < 0 || this.parent.item(this.parentIndex) != this) {
            this.parentIndex = this.parent.getIndexOf(this);
        }
        return this.parent.item(this.parentIndex - 1);
    }
    
    public NamedNodeMap getAttributes() {
        return null;
    }
    
    public void normalize() {
    }
    
    public boolean isSupported(final String feature, final String version) {
        return DOMImplementationImpl.hasFeature0(feature, version);
    }
    
    public String getNamespaceURI() {
        return null;
    }
    
    public String getPrefix() {
        return null;
    }
    
    public void setPrefix(final String prefix) throws DOMException {
        throw new DomEx((short)14);
    }
    
    public String getLocalName() {
        return null;
    }
    
    public boolean hasAttributes() {
        return false;
    }
    
    public int getIndexOf(final Node maybeChild) {
        return -1;
    }
    
    String getMessage(final String messageId) {
        return this.getMessage(messageId, null);
    }
    
    String getMessage(final String messageId, final Object[] parameters) {
        Locale locale;
        if (this instanceof XmlDocument) {
            locale = ((XmlDocument)this).getLocale();
        }
        else if (this.ownerDocument == null) {
            locale = Locale.getDefault();
        }
        else {
            locale = this.ownerDocument.getLocale();
        }
        return XmlDocument.catalog.getMessage(locale, messageId, parameters);
    }
    
    public abstract Node cloneNode(final boolean p0);
    
    public abstract short getNodeType();
    
    public abstract String getNodeName();
    
    public abstract void writeXml(final XmlWriteContext p0) throws IOException;
}
