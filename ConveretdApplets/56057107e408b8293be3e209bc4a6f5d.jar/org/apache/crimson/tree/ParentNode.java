// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.tree;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.DOMException;
import java.io.IOException;

abstract class ParentNode extends NodeBase
{
    private NodeBase[] children;
    private int length;
    
    public void trimToSize() {
        if (this.length == 0) {
            this.children = null;
        }
        else if (this.children.length != this.length) {
            final NodeBase[] temp = new NodeBase[this.length];
            System.arraycopy(this.children, 0, temp, 0, this.length);
            this.children = temp;
        }
    }
    
    void reduceWaste() {
        if (this.children == null) {
            return;
        }
        if (this.children.length - this.length > 6) {
            this.trimToSize();
        }
    }
    
    public void writeChildrenXml(final XmlWriteContext context) throws IOException {
        if (this.children == null) {
            return;
        }
        int oldIndent = 0;
        boolean preserve = true;
        boolean pureText = true;
        if (this.getNodeType() == 1) {
            preserve = "preserve".equals(this.getInheritedAttribute("xml:space"));
            oldIndent = context.getIndentLevel();
        }
        try {
            if (!preserve) {
                context.setIndentLevel(oldIndent + 2);
            }
            for (int i = 0; i < this.length; ++i) {
                if (!preserve && this.children[i].getNodeType() != 3) {
                    context.printIndent();
                    pureText = false;
                }
                this.children[i].writeXml(context);
            }
        }
        finally {
            if (!preserve) {
                context.setIndentLevel(oldIndent);
                if (!pureText) {
                    context.printIndent();
                }
            }
        }
    }
    
    abstract void checkChildType(final int p0) throws DOMException;
    
    public final boolean hasChildNodes() {
        return this.length > 0;
    }
    
    public final Node getFirstChild() {
        if (this.length == 0) {
            return null;
        }
        return this.children[0];
    }
    
    public final Node getLastChild() {
        if (this.length == 0) {
            return null;
        }
        return this.children[this.length - 1];
    }
    
    public final int getLength() {
        return this.length;
    }
    
    public final Node item(final int i) {
        if (this.length == 0 || i >= this.length) {
            return null;
        }
        try {
            return this.children[i];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
    
    private NodeBase checkDocument(final Node newChild) throws DOMException {
        if (newChild == null) {
            throw new DomEx((short)3);
        }
        if (!(newChild instanceof NodeBase)) {
            throw new DomEx((short)4);
        }
        final Document owner = newChild.getOwnerDocument();
        XmlDocument myOwner = super.ownerDocument;
        final NodeBase child = (NodeBase)newChild;
        if (myOwner == null && this instanceof XmlDocument) {
            myOwner = (XmlDocument)this;
        }
        if (owner != null && owner != myOwner) {
            throw new DomEx((short)4);
        }
        if (owner == null) {
            child.setOwnerDocument(myOwner);
        }
        if (child.hasChildNodes()) {
            int i = 0;
            while (true) {
                final Node node = child.item(i);
                if (node == null) {
                    break;
                }
                if (node.getOwnerDocument() == null) {
                    ((NodeBase)node).setOwnerDocument(myOwner);
                }
                else if (node.getOwnerDocument() != myOwner) {
                    throw new DomEx((short)4);
                }
                ++i;
            }
        }
        return child;
    }
    
    private void checkNotAncestor(final Node newChild) throws DOMException {
        if (!newChild.hasChildNodes()) {
            return;
        }
        for (Node ancestor = this; ancestor != null; ancestor = ancestor.getParentNode()) {
            if (newChild == ancestor) {
                throw new DomEx((short)3);
            }
        }
    }
    
    private void mutated() {
        XmlDocument doc = super.ownerDocument;
        if (doc == null && this instanceof XmlDocument) {
            doc = (XmlDocument)this;
        }
        if (doc != null) {
            final XmlDocument xmlDocument = doc;
            ++xmlDocument.mutationCount;
        }
    }
    
    private void consumeFragment(final Node fragment, final Node before) throws DOMException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1         /* fragment */
        //     1: checkcast       Lorg/apache/crimson/tree/ParentNode;
        //     4: astore_3        /* frag */
        //     5: iconst_0       
        //     6: istore          i
        //     8: goto            31
        //    11: aload_0         /* this */
        //    12: aload           4
        //    14: invokespecial   org/apache/crimson/tree/ParentNode.checkNotAncestor:(Lorg/w3c/dom/Node;)V
        //    17: aload_0         /* this */
        //    18: aload           4
        //    20: invokeinterface org/w3c/dom/Node.getNodeType:()S
        //    25: invokevirtual   org/apache/crimson/tree/ParentNode.checkChildType:(I)V
        //    28: iinc            i, 1
        //    31: aload_3         /* frag */
        //    32: iload           i
        //    34: invokevirtual   org/apache/crimson/tree/ParentNode.item:(I)Lorg/w3c/dom/Node;
        //    37: dup            
        //    38: astore          temp
        //    40: ifnonnull       11
        //    43: goto            54
        //    46: aload_0         /* this */
        //    47: aload           temp
        //    49: aload_2         /* before */
        //    50: invokevirtual   org/apache/crimson/tree/ParentNode.insertBefore:(Lorg/w3c/dom/Node;Lorg/w3c/dom/Node;)Lorg/w3c/dom/Node;
        //    53: pop            
        //    54: aload_3         /* frag */
        //    55: iconst_0       
        //    56: invokevirtual   org/apache/crimson/tree/ParentNode.item:(I)Lorg/w3c/dom/Node;
        //    59: dup            
        //    60: astore          temp
        //    62: ifnonnull       46
        //    65: return         
        //    Exceptions:
        //  throws org.w3c.dom.DOMException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name      Signature
        //  -----  ------  ----  --------  ------------------------------------
        //  0      66      0     this      Lorg/apache/crimson/tree/ParentNode;
        //  0      66      1     fragment  Lorg/w3c/dom/Node;
        //  0      66      2     before    Lorg/w3c/dom/Node;
        //  5      60      3     frag      Lorg/apache/crimson/tree/ParentNode;
        //  40     25      4     temp      Lorg/w3c/dom/Node;
        //  8      57      5     i         I
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
    
    public Node appendChild(final Node newChild) throws DOMException {
        if (super.readonly) {
            throw new DomEx((short)7);
        }
        final NodeBase child = this.checkDocument(newChild);
        if (newChild.getNodeType() == 11) {
            this.consumeFragment(newChild, null);
            return newChild;
        }
        this.checkNotAncestor(newChild);
        this.checkChildType(child.getNodeType());
        if (this.children == null) {
            this.children = new NodeBase[3];
        }
        else if (this.children.length == this.length) {
            final NodeBase[] temp = new NodeBase[this.length * 2];
            System.arraycopy(this.children, 0, temp, 0, this.length);
            this.children = temp;
        }
        child.setParentNode(this, this.length);
        this.children[this.length++] = child;
        this.mutated();
        return child;
    }
    
    public Node insertBefore(final Node newChild, final Node refChild) throws DOMException {
        if (super.readonly) {
            throw new DomEx((short)7);
        }
        if (refChild == null) {
            return this.appendChild(newChild);
        }
        if (this.length == 0) {
            throw new DomEx((short)8);
        }
        final NodeBase child = this.checkDocument(newChild);
        if (newChild.getNodeType() == 11) {
            this.consumeFragment(newChild, refChild);
            return newChild;
        }
        this.checkNotAncestor(newChild);
        this.checkChildType(newChild.getNodeType());
        if (this.children.length == this.length) {
            final NodeBase[] temp = new NodeBase[this.length * 2];
            System.arraycopy(this.children, 0, temp, 0, this.length);
            this.children = temp;
        }
        for (int i = 0; i < this.length; ++i) {
            if (this.children[i] == refChild) {
                child.setParentNode(this, i);
                System.arraycopy(this.children, i, this.children, i + 1, this.length - i);
                this.children[i] = child;
                ++this.length;
                this.mutated();
                return newChild;
            }
        }
        throw new DomEx((short)8);
    }
    
    public Node replaceChild(final Node newChild, final Node refChild) throws DOMException {
        if (super.readonly) {
            throw new DomEx((short)7);
        }
        if (newChild == null || refChild == null) {
            throw new DomEx((short)3);
        }
        if (this.children == null) {
            throw new DomEx((short)8);
        }
        final NodeBase child = this.checkDocument(newChild);
        if (newChild.getNodeType() == 11) {
            this.consumeFragment(newChild, refChild);
            return this.removeChild(refChild);
        }
        this.checkNotAncestor(newChild);
        this.checkChildType(newChild.getNodeType());
        for (int i = 0; i < this.length; ++i) {
            if (this.children[i] == refChild) {
                child.setParentNode(this, i);
                this.children[i] = child;
                ((NodeBase)refChild).setParentNode(null, -1);
                this.mutated();
                return refChild;
            }
        }
        throw new DomEx((short)8);
    }
    
    public Node removeChild(final Node oldChild) throws DOMException {
        if (super.readonly) {
            throw new DomEx((short)7);
        }
        if (!(oldChild instanceof NodeBase)) {
            throw new DomEx((short)8);
        }
        final NodeBase child = (NodeBase)oldChild;
        for (int i = 0; i < this.length; ++i) {
            if (this.children[i] == child) {
                if (i + 1 != this.length) {
                    System.arraycopy(this.children, i + 1, this.children, i, this.length - 1 - i);
                }
                --this.length;
                this.children[this.length] = null;
                child.setParentNode(null, -1);
                this.mutated();
                return oldChild;
            }
        }
        throw new DomEx((short)8);
    }
    
    public NodeList getElementsByTagName(String tagname) {
        if ("*".equals(tagname)) {
            tagname = null;
        }
        return new TagList(tagname);
    }
    
    public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
        if ("*".equals(namespaceURI)) {
            namespaceURI = null;
        }
        if ("*".equals(localName)) {
            localName = null;
        }
        return new TagListNS(namespaceURI, localName);
    }
    
    public final int getIndexOf(final Node maybeChild) {
        for (int i = 0; i < this.length; ++i) {
            if (this.children[i] == maybeChild) {
                return i;
            }
        }
        return -1;
    }
    
    public void normalize() {
        boolean preserve = false;
        boolean knowPreserve = false;
        if (super.readonly) {
            throw new DomEx((short)7);
        }
        int i = 0;
        while (true) {
            final Node node = this.item(i);
            if (node == null) {
                break;
            }
            switch (node.getNodeType()) {
                case 1: {
                    ((Element)node).normalize();
                    break;
                }
                case 3: {
                    final Node node2 = this.item(i + 1);
                    if (node2 != null && node2.getNodeType() == 3) {
                        ((TextNode)node).joinNextText();
                        --i;
                        break;
                    }
                    if (!knowPreserve) {
                        preserve = "preserve".equals(this.getInheritedAttribute("xml:space"));
                        knowPreserve = true;
                    }
                    if (preserve) {
                        break;
                    }
                    final char[] buf = ((TextNode)node).data;
                    if (buf == null || buf.length == 0) {
                        this.removeChild(node);
                        --i;
                        break;
                    }
                    final int current = this.removeWhiteSpaces(buf);
                    if (current != buf.length) {
                        final char[] tmp = new char[current];
                        System.arraycopy(buf, 0, tmp, 0, current);
                        ((TextNode)node).data = tmp;
                    }
                    break;
                }
            }
            ++i;
        }
    }
    
    public int removeWhiteSpaces(final char[] buf) {
        int current = 0;
        int j = 0;
        while (j < buf.length) {
            boolean sawSpace = false;
            char c = buf[j++];
            if (c == ' ' || c == '\t' || c == '\n' || c == '\r') {
                c = ' ';
                sawSpace = true;
            }
            buf[current++] = c;
            if (sawSpace) {
                while (j < buf.length) {
                    c = buf[j];
                    if (c != ' ' && c != '\t' && c != '\n' && c != '\r') {
                        break;
                    }
                    ++j;
                }
            }
        }
        return current;
    }
    
    class TagList implements NodeList
    {
        protected String tag;
        protected int lastMutationCount;
        protected int lastIndex;
        protected TreeWalker lastWalker;
        
        protected int getLastMutationCount() {
            final XmlDocument doc = (XmlDocument)ParentNode.this.getOwnerDocument();
            return (doc == null) ? 0 : doc.mutationCount;
        }
        
        TagList(final String tag) {
            this.tag = tag;
        }
        
        public Node item(final int i) {
            if (i < 0) {
                return null;
            }
            final int temp = this.getLastMutationCount();
            if (this.lastWalker != null && (i < this.lastIndex || temp != this.lastMutationCount)) {
                this.lastWalker = null;
            }
            if (this.lastWalker == null) {
                this.lastWalker = new TreeWalker(ParentNode.this);
                this.lastIndex = -1;
                this.lastMutationCount = temp;
            }
            if (i == this.lastIndex) {
                return this.lastWalker.getCurrent();
            }
            Node node = null;
            while (i > this.lastIndex && (node = this.lastWalker.getNextElement(this.tag)) != null) {
                ++this.lastIndex;
            }
            return node;
        }
        
        public int getLength() {
            final TreeWalker walker = new TreeWalker(ParentNode.this);
            Node node = null;
            int retval = 0;
            while ((node = walker.getNextElement(this.tag)) != null) {
                ++retval;
            }
            return retval;
        }
    }
    
    class TagListNS extends TagList
    {
        private String namespaceURI;
        
        TagListNS(final String namespaceURI, final String localName) {
            super(localName);
            this.namespaceURI = namespaceURI;
        }
        
        public Node item(final int i) {
            if (i < 0) {
                return null;
            }
            final int temp = this.getLastMutationCount();
            if (super.lastWalker != null && (i < super.lastIndex || temp != super.lastMutationCount)) {
                super.lastWalker = null;
            }
            if (super.lastWalker == null) {
                super.lastWalker = new TreeWalker(ParentNode.this);
                super.lastIndex = -1;
                super.lastMutationCount = temp;
            }
            if (i == super.lastIndex) {
                return super.lastWalker.getCurrent();
            }
            Node node = null;
            while (i > super.lastIndex && (node = super.lastWalker.getNextElement(this.namespaceURI, super.tag)) != null) {
                ++super.lastIndex;
            }
            return node;
        }
        
        public int getLength() {
            final TreeWalker walker = new TreeWalker(ParentNode.this);
            int count = 0;
            while (walker.getNextElement(this.namespaceURI, super.tag) != null) {
                ++count;
            }
            return count;
        }
    }
}
