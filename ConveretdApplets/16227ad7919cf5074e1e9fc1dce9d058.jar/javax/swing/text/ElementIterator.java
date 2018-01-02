// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import java.util.Enumeration;
import java.util.Stack;

public class ElementIterator implements Cloneable
{
    private Element root;
    private Stack elementStack;
    
    public ElementIterator(final Document document) {
        this.elementStack = null;
        this.root = document.getDefaultRootElement();
    }
    
    public ElementIterator(final Element root) {
        this.elementStack = null;
        this.root = root;
    }
    
    public synchronized Object clone() {
        try {
            final ElementIterator elementIterator = new ElementIterator(this.root);
            elementIterator.elementStack = new Stack();
            for (int i = 0; i < this.elementStack.size(); ++i) {
                elementIterator.elementStack.push(((StackItem)this.elementStack.elementAt(i)).clone());
            }
            return elementIterator;
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
    
    public Element current() {
        if (this.elementStack == null) {
            return this.first();
        }
        if (this.elementStack.empty()) {
            return null;
        }
        final StackItem stackItem = this.elementStack.peek();
        final Element access$0 = stackItem.getElement();
        final int access$2 = stackItem.getIndex();
        if (access$2 == -1) {
            return access$0;
        }
        return access$0.getElement(access$2);
    }
    
    public int depth() {
        return this.elementStack.size();
    }
    
    private void dumpTree() {
        Element next;
        while ((next = this.next()) != null) {
            System.out.println("elem: " + next.getName());
            final AttributeSet attributes = next.getAttributes();
            String s = "";
            final Enumeration attributeNames = attributes.getAttributeNames();
            while (attributeNames.hasMoreElements()) {
                final Object nextElement = attributeNames.nextElement();
                final Object attribute = attributes.getAttribute(nextElement);
                if (attribute instanceof AttributeSet) {
                    s = String.valueOf(s) + nextElement + "=**AttributeSet** ";
                }
                else {
                    s = String.valueOf(s) + nextElement + "=" + attribute + " ";
                }
            }
            System.out.println("attributes: " + s);
        }
    }
    
    public Element first() {
        if (this.root == null) {
            return null;
        }
        this.elementStack = new Stack();
        if (this.root.getElementCount() != 0) {
            this.elementStack.push(new StackItem((1)null, this.root));
        }
        return this.root;
    }
    
    public Element next() {
        if (this.elementStack == null) {
            return this.first();
        }
        if (this.elementStack.isEmpty()) {
            return null;
        }
        final StackItem stackItem = this.elementStack.peek();
        final Element access$0 = stackItem.getElement();
        final int access$2 = stackItem.getIndex();
        if (access$2 + 1 < access$0.getElementCount()) {
            final Element element = access$0.getElement(access$2 + 1);
            if (element.isLeaf()) {
                stackItem.incrementIndex();
            }
            else {
                this.elementStack.push(new StackItem((1)null, element));
            }
            return element;
        }
        this.elementStack.pop();
        if (!this.elementStack.isEmpty()) {
            this.elementStack.peek().incrementIndex();
            return this.next();
        }
        return null;
    }
    
    public Element previous() {
        if (this.elementStack == null | this.elementStack.size() == 1) {
            return null;
        }
        final StackItem stackItem = this.elementStack.peek();
        final Element access$0 = stackItem.getElement();
        int access$2 = stackItem.getIndex();
        if (access$2 > 0) {
            return access$0.getElement(--access$2);
        }
        if (access$2 == 0) {
            return access$0;
        }
        if (access$2 == -1) {
            final Object pop = this.elementStack.pop();
            final StackItem stackItem2 = this.elementStack.peek();
            this.elementStack.push(pop);
            final Element access$3 = stackItem2.getElement();
            final int access$4 = stackItem2.getIndex();
            return (access$4 == -1) ? access$3 : access$3.getElement(access$4);
        }
        return null;
    }
    
    private class StackItem implements Cloneable
    {
        Element item;
        int childIndex;
        
        private StackItem(final Element item) {
            this.item = item;
            this.childIndex = -1;
        }
        
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
        
        private Element getElement() {
            return this.item;
        }
        
        private int getIndex() {
            return this.childIndex;
        }
        
        private void incrementIndex() {
            ++this.childIndex;
        }
    }
    
    static class 1
    {
    }
}
