// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.diff;

import java.util.Iterator;
import org.xmodel.Element;
import org.xmodel.IModelObject;
import java.util.HashMap;
import java.util.Map;

public class Annotation
{
    Action action;
    StringBuilder sb;
    Map<String, String> map;
    static final long serialVersionUID = 0L;
    
    Annotation(final String... content) {
        this.action = Action.text;
        this.sb = new StringBuilder();
        this.map = new HashMap<String, String>();
    }
    
    void setAction(final Action action) {
        this.action = action;
    }
    
    void add(final Key key, final String value) {
        this.map.put(key.toString(), value);
    }
    
    void apply(final IModelObject object) {
        final IModelObject pi = new Element("?chg-" + this.action);
        for (final Map.Entry<String, String> entry : this.map.entrySet()) {
            pi.setAttribute(entry.getKey(), entry.getValue());
        }
        object.addChild(pi, 0);
    }
    
    enum Action
    {
        added("added", 0), 
        text("text", 1), 
        attr("attr", 2), 
        removed("removed", 3);
        
        private Action(final String s, final int n) {
        }
    }
    
    enum Key
    {
        attribute("attribute", 0), 
        to("to", 1);
        
        private Key(final String s, final int n) {
        }
    }
}
