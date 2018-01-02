// 
// Decompiled by Procyon v0.5.30
// 

package complabel;

import java.util.Enumeration;
import java.util.Vector;

public class CompParser
{
    String s;
    int pos;
    int startpos;
    public ParserNode tree;
    public ParserNode currentNode;
    ParserElement defaultElement;
    
    public CompParser(final String font, final String size, final String[] array, final String fgString, final String bgString) {
        this.defaultElement = new ParserElement();
        this.defaultElement.font = font;
        this.defaultElement.size = size;
        this.defaultElement.fgString = fgString;
        this.defaultElement.bgString = bgString;
        for (int i = 0; i < array.length; ++i) {
            this.defaultElement.flags.addElement(array[i]);
        }
    }
    
    public CompParser(final ParserNode tree) {
        this.tree = tree;
        this.currentNode = this.tree;
        while (this.currentNode.children != null && this.currentNode.children.size() > 0) {
            this.currentNode = this.currentNode.children.lastElement();
        }
    }
    
    ParserNode makeTagTree(final ParserNode currentNode) {
        final ParserNode parserNode = new ParserNode();
        this.currentNode = currentNode;
        ParserNode parserNode2 = parserNode;
        while (true) {
            final ParserNode parserNode3 = new ParserNode(this.currentNode);
            parserNode2.addChild(parserNode3);
            parserNode2 = parserNode3;
            if (this.currentNode.children == null || this.currentNode.children.size() <= 0) {
                break;
            }
            this.currentNode = this.currentNode.children.lastElement();
        }
        return parserNode;
    }
    
    ParserNode parseString(final String s) {
        this.s = s;
        if (this.tree == null) {
            this.tree = new ParserNode();
            this.currentNode = this.tree;
            this.currentNode.element = this.defaultElement;
        }
        this.pos = 0;
        this.startpos = 0;
        while (this.pos < this.s.length()) {
            if (this.s.charAt(this.pos) == '<') {
                this.putText();
                this.parseTag();
                this.startpos = this.pos;
            }
            else {
                ++this.pos;
            }
        }
        this.putText();
        if (this.currentNode == null) {
            System.out.println("whoa! nelly!\n" + new String(this.s));
            this.dump();
            System.out.println("eom.");
            return null;
        }
        return this.makeTagTree(this.tree);
    }
    
    void putText() {
        if (this.pos > this.startpos) {
            final ParserNode parserNode = new ParserNode(this.currentNode);
            parserNode.element.text = this.s.substring(this.startpos, this.pos);
            this.currentNode.addChild(parserNode);
        }
    }
    
    void parseTag() {
        final int pos = this.pos;
        final int index = this.s.indexOf(62, pos + 1);
        if (index < 0) {
            throw new RuntimeException("Ouch! tag without end!");
        }
        final char char1 = this.s.charAt(pos + 1);
        int n = pos + 1;
        boolean b = false;
        if (char1 == '/') {
            ++n;
            b = true;
        }
        final String string = "" + this.s.charAt(n);
        String substring = null;
        if (this.s.charAt(n + 1) == ':') {
            substring = this.s.substring(n + 2, index);
        }
        this.pos = index + 1;
        if (!b) {
            this.openTag(string, substring);
        }
        else {
            this.closeTag(string, substring);
        }
    }
    
    void openTag(final String s, final String s2) {
        final ParserNode currentNode = new ParserNode(this.currentNode);
        this.currentNode.addChild(currentNode);
        this.currentNode = currentNode;
        if (s2 == null) {
            this.toggleFlag(s);
        }
        else if (s.equals("f")) {
            this.currentNode.element.font = s2;
        }
        else if (s.equals("s")) {
            this.currentNode.element.size = s2;
        }
        else if (s.equals("c")) {
            this.currentNode.element.fgString = s2;
        }
        else if (s.equals("d")) {
            this.currentNode.element.bgString = s2;
        }
    }
    
    void closeTag(final String s, final String s2) {
        if (this.currentNode != null) {
            this.currentNode = this.currentNode.parent;
            final ParserNode parserNode = new ParserNode(this.currentNode);
            if (this.currentNode != null) {
                this.currentNode.addChild(parserNode);
            }
        }
    }
    
    void toggleFlag(final String s) {
        if (this.currentNode.element.flags.contains(s)) {
            this.currentNode.element.flags.removeElement(s);
        }
        else {
            this.currentNode.element.flags.addElement(s);
        }
    }
    
    public void dump() {
        System.out.println("dump:");
        this.tree.dump(0);
    }
    
    public Vector makeComps() {
        final Vector<TextComp> vector = new Vector<TextComp>();
        this.makeCompTraverser(vector, this.tree);
        final Enumeration<TextComp> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final TextComp textComp = elements.nextElement();
        }
        return vector;
    }
    
    void makeCompTraverser(final Vector vector, final ParserNode parserNode) {
        final TextComp textComp = parserNode.element.toTextComp();
        if (textComp != null) {
            vector.addElement(textComp);
        }
        if (parserNode.children != null) {
            final Enumeration<ParserNode> elements = (Enumeration<ParserNode>)parserNode.children.elements();
            while (elements.hasMoreElements()) {
                this.makeCompTraverser(vector, elements.nextElement());
            }
        }
    }
}
