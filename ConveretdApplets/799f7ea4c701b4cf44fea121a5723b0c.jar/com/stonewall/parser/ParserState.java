// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import java.util.HashSet;
import org.xmodel.log.Log;
import java.util.Set;
import java.util.Stack;

public class ParserState
{
    static final String Default = "";
    private Stack<String> stack;
    private Set<String> domain;
    public static Log log;
    
    static {
        ParserState.log = Log.getLog(Parser.class);
    }
    
    public ParserState() {
        this.stack = new Stack<String>();
        this.domain = new HashSet<String>();
    }
    
    String current() {
        return this.stack.isEmpty() ? "" : this.stack.peek();
    }
    
    boolean match(final String state) {
        return this.current().equals(state);
    }
    
    void push(final String state) {
        if (state != null) {
            this.stack.push(state);
            ParserState.log.debug("state {" + state + "} pushed");
        }
    }
    
    String pop() {
        if (this.stack.isEmpty()) {
            ParserState.log.debug("stack is empty!");
            return "";
        }
        final String state = this.stack.pop();
        ParserState.log.debug("state popped, current is {" + state + "}");
        return state;
    }
    
    void reset() {
        this.stack.clear();
    }
    
    void add(final String state) {
        this.domain.add(state);
    }
    
    boolean domainContains(final String state) {
        return this.domain.contains(state);
    }
}
