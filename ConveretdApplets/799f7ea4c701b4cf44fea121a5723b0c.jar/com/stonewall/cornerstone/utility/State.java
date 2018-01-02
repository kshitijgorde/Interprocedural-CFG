// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.util.Iterator;
import java.util.ArrayList;
import org.jdom.Element;
import org.jdom.Namespace;
import java.util.List;

class State
{
    final MMLEmulation emulation;
    final String name;
    final String prompt;
    final List<Expect> expectList;
    static Namespace cnns;
    
    static {
        State.cnns = Namespace.getNamespace("cn", "http://www.stonewallnetworks.com/ns/common");
    }
    
    State(final Element root, final MMLEmulation emulation) {
        this.expectList = new ArrayList<Expect>();
        this.emulation = emulation;
        this.name = root.getAttributeValue("name");
        this.prompt = root.getChildText("prompt", State.cnns);
        for (final Object o : root.getChildren("expect", State.cnns)) {
            this.expectList.add(new Expect((Element)o, this));
        }
    }
    
    void validate() {
        for (final Expect exp : this.expectList) {
            final String ns = exp.nextState();
            if (ns == null) {
                continue;
            }
            if (!this.emulation.validateState(ns)) {
                final String reason = String.valueOf(this.emulation.name) + "/" + this.name + "/" + exp.expr + "has invalid nextState:" + exp.nextState;
                throw new IllegalStateException(reason);
            }
        }
    }
    
    Expect match(final String s) {
        for (final Expect exp : this.expectList) {
            if (exp.match(s)) {
                return exp;
            }
        }
        return null;
    }
    
    String getPrompt() {
        return this.prompt;
    }
}
