// 
// Decompiled by Procyon v0.5.30
// 

package java_cup.runtime;

import java.util.Stack;

public class virtual_parse_stack
{
    protected Stack real_stack;
    protected int real_next;
    protected Stack vstack;
    
    public virtual_parse_stack(final Stack real_stack) throws Exception {
        if (real_stack == null) {
            throw new Exception("Internal parser error: attempt to create null virtual stack");
        }
        this.real_stack = real_stack;
        this.vstack = new Stack();
        this.real_next = 0;
        this.get_from_real();
    }
    
    public boolean empty() {
        return this.vstack.empty();
    }
    
    protected void get_from_real() {
        if (this.real_next >= this.real_stack.size()) {
            return;
        }
        final Symbol symbol = (Symbol)this.real_stack.elementAt(this.real_stack.size() - 1 - this.real_next);
        ++this.real_next;
        this.vstack.push(new Integer(symbol.parse_state));
    }
    
    public void pop() throws Exception {
        if (this.vstack.empty()) {
            throw new Exception("Internal parser error: pop from empty virtual stack");
        }
        this.vstack.pop();
        if (this.vstack.empty()) {
            this.get_from_real();
        }
    }
    
    public void push(final int n) {
        this.vstack.push(new Integer(n));
    }
    
    public int top() throws Exception {
        if (this.vstack.empty()) {
            throw new Exception("Internal parser error: top() called on empty virtual stack");
        }
        return this.vstack.peek();
    }
}
