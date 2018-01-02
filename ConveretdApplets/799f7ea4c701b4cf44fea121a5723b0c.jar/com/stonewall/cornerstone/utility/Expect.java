// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import org.jdom.Element;
import org.jdom.Namespace;

class Expect
{
    Reply reply;
    final State state;
    final String expr;
    final String nextState;
    final Action action;
    static Namespace cnns;
    
    static {
        Expect.cnns = Namespace.getNamespace("cn", "http://www.stonewallnetworks.com/ns/common");
    }
    
    Expect(final Element root, final State state) {
        this.reply = null;
        this.state = state;
        this.expr = root.getChildText("expr", Expect.cnns);
        this.reply = Reply.newInstance(root.getChild("reply", Expect.cnns));
        this.nextState = root.getChildText("nextState", Expect.cnns);
        this.action = Action.newInstance(root.getChild("action", Expect.cnns));
    }
    
    boolean match(final String s) {
        return s.matches(this.expr);
    }
    
    Reply getReply() {
        return this.reply;
    }
    
    Action getAction() {
        return this.action;
    }
    
    String nextState() {
        return this.nextState;
    }
}
