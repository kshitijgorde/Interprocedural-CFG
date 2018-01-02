// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.tree;

import java.io.IOException;
import java.io.Writer;
import org.w3c.dom.Node;
import org.w3c.dom.DOMException;
import org.apache.crimson.util.XmlNames;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;

final class AttributeNode extends NamespacedNode implements Attr
{
    private String value;
    private boolean specified;
    private String defaultValue;
    private Element ownerElement;
    
    public AttributeNode(final String name, final String value, final boolean specified, final String defaultValue) throws DOMException {
        if (!XmlNames.isName(name)) {
            throw new DomEx((short)5);
        }
        super.name = name;
        this.value = value;
        this.specified = specified;
        this.defaultValue = defaultValue;
    }
    
    public AttributeNode(final String namespaceURI, final String qName, final String value, final boolean specified, final String defaultValue) throws DOMException {
        super.name = qName;
        super.namespaceURI = namespaceURI;
        this.value = value;
        this.specified = specified;
        this.defaultValue = defaultValue;
    }
    
    static void checkArguments(final String namespaceURI, final String qualifiedName) throws DomEx {
        if (qualifiedName == null) {
            throw new DomEx((short)14);
        }
        final int first = qualifiedName.indexOf(58);
        if (first <= 0) {
            if (!XmlNames.isUnqualifiedName(qualifiedName)) {
                throw new DomEx((short)5);
            }
            if ("xmlns".equals(qualifiedName) && !"http://www.w3.org/2000/xmlns/".equals(namespaceURI)) {
                throw new DomEx((short)14);
            }
        }
        else {
            final int last = qualifiedName.lastIndexOf(58);
            if (last != first) {
                throw new DomEx((short)14);
            }
            final String prefix = qualifiedName.substring(0, first);
            final String localName = qualifiedName.substring(first + 1);
            if (!XmlNames.isUnqualifiedName(prefix) || !XmlNames.isUnqualifiedName(localName)) {
                throw new DomEx((short)5);
            }
            if (namespaceURI == null || ("xml".equals(prefix) && !"http://www.w3.org/XML/1998/namespace".equals(namespaceURI))) {
                throw new DomEx((short)14);
            }
        }
    }
    
    String getDefaultValue() {
        return this.defaultValue;
    }
    
    public Element getOwnerElement() {
        return this.ownerElement;
    }
    
    void setOwnerElement(final ElementNode element) {
        if (element != null && this.ownerElement != null) {
            throw new IllegalStateException(this.getMessage("A-000", new Object[] { element.getTagName() }));
        }
        this.ownerElement = element;
    }
    
    public short getNodeType() {
        return 2;
    }
    
    public String getName() {
        return super.name;
    }
    
    public String getNodeName() {
        return super.name;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(final String value) {
        this.setNodeValue(value);
    }
    
    public String getNodeValue() {
        return this.value;
    }
    
    public boolean getSpecified() {
        return this.specified;
    }
    
    public void setNodeValue(final String value) {
        if (this.isReadonly()) {
            throw new DomEx((short)7);
        }
        this.value = value;
        this.specified = true;
    }
    
    void setSpecified(final boolean specified) {
        this.specified = specified;
    }
    
    public Node getParentNode() {
        return null;
    }
    
    public Node getNextSibling() {
        return null;
    }
    
    public Node getPreviousSibling() {
        return null;
    }
    
    public void writeXml(final XmlWriteContext context) throws IOException {
        final Writer out = context.getWriter();
        out.write(super.name);
        out.write("=\"");
        this.writeChildrenXml(context);
        out.write(34);
    }
    
    public void writeChildrenXml(final XmlWriteContext context) throws IOException {
        final Writer out = context.getWriter();
        for (int i = 0; i < this.value.length(); ++i) {
            final int c = this.value.charAt(i);
            switch (c) {
                case 60: {
                    out.write("&lt;");
                    break;
                }
                case 62: {
                    out.write("&gt;");
                    break;
                }
                case 38: {
                    out.write("&amp;");
                    break;
                }
                case 39: {
                    out.write("&apos;");
                    break;
                }
                case 34: {
                    out.write("&quot;");
                    break;
                }
                default: {
                    out.write(c);
                    break;
                }
            }
        }
    }
    
    public Node cloneNode(final boolean deep) {
        final AttributeNode attr = this.cloneAttributeNode(deep);
        attr.specified = true;
        return attr;
    }
    
    AttributeNode cloneAttributeNode(final boolean deep) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_0         /* this */
        //     5: getfield        org/apache/crimson/tree/NamespacedNode.namespaceURI:Ljava/lang/String;
        //     8: aload_0         /* this */
        //     9: getfield        org/apache/crimson/tree/NamespacedNode.name:Ljava/lang/String;
        //    12: aload_0         /* this */
        //    13: getfield        org/apache/crimson/tree/AttributeNode.value:Ljava/lang/String;
        //    16: aload_0         /* this */
        //    17: getfield        org/apache/crimson/tree/AttributeNode.specified:Z
        //    20: aload_0         /* this */
        //    21: getfield        org/apache/crimson/tree/AttributeNode.defaultValue:Ljava/lang/String;
        //    24: invokespecial   org/apache/crimson/tree/AttributeNode.<init>:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V
        //    27: astore_2       
        //    28: aload_2        
        //    29: aload_0         /* this */
        //    30: invokevirtual   org/apache/crimson/tree/NodeBase.getOwnerDocument:()Lorg/w3c/dom/Document;
        //    33: checkcast       Lorg/apache/crimson/tree/XmlDocument;
        //    36: invokevirtual   org/apache/crimson/tree/NodeBase.setOwnerDocument:(Lorg/apache/crimson/tree/XmlDocument;)V
        //    39: iload_1         /* deep */
        //    40: ifeq            77
        //    43: iconst_0       
        //    44: istore          i
        //    46: goto            66
        //    49: aload_3        
        //    50: iconst_1       
        //    51: invokeinterface org/w3c/dom/Node.cloneNode:(Z)Lorg/w3c/dom/Node;
        //    56: astore_3        /* node */
        //    57: aload_2        
        //    58: aload_3         /* node */
        //    59: invokevirtual   org/apache/crimson/tree/ParentNode.appendChild:(Lorg/w3c/dom/Node;)Lorg/w3c/dom/Node;
        //    62: pop            
        //    63: iinc            i, 1
        //    66: aload_0         /* this */
        //    67: iload           i
        //    69: invokevirtual   org/apache/crimson/tree/ParentNode.item:(I)Lorg/w3c/dom/Node;
        //    72: dup            
        //    73: astore_3        /* node */
        //    74: ifnonnull       49
        //    77: aload_2        
        //    78: areturn        
        //    79: astore_2        /* e */
        //    80: new             Ljava/lang/RuntimeException;
        //    83: dup            
        //    84: aload_0         /* this */
        //    85: ldc             "A-002"
        //    87: invokevirtual   org/apache/crimson/tree/NodeBase.getMessage:(Ljava/lang/String;)Ljava/lang/String;
        //    90: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;)V
        //    93: athrow         
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ---------------------------------------
        //  0      94      0     this  Lorg/apache/crimson/tree/AttributeNode;
        //  0      94      1     deep  Z
        //  28     51      2     attr  Lorg/apache/crimson/tree/AttributeNode;
        //  57     20      3     node  Lorg/w3c/dom/Node;
        //  46     31      4     i     I
        //  79     15      2     e     Lorg/w3c/dom/DOMException;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                      
        //  -----  -----  -----  -----  --------------------------
        //  0      79     79     94     Lorg/w3c/dom/DOMException;
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
    
    void checkChildType(final int type) throws DOMException {
        switch (type) {
            case 3:
            case 5: {}
            default: {
                throw new DomEx((short)3);
            }
        }
    }
}
