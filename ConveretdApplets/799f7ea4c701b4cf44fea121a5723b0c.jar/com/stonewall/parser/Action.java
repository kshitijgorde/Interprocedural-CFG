// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser;

import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import org.jdom.Attribute;
import java.util.List;
import org.jdom.output.XMLOutputter;
import org.jdom.output.Format;
import com.stonewall.parser.jdom.SAXHandler;
import org.xmodel.log.Log;
import org.jdom.Element;

public class Action implements Cloneable
{
    private Element root;
    private Context context;
    protected Condition condition;
    static Log log;
    
    static {
        Action.log = Log.getLog(Action.class);
    }
    
    Action(final Element root, final Context context) {
        this.condition = new Condition();
        this.root = root;
        this.context = context;
    }
    
    public String id() {
        return this.location();
    }
    
    public Action clone() {
        try {
            final Action clone = (Action)super.clone();
            clone.root = (Element)this.root.clone();
            return clone;
        }
        catch (Exception e) {
            Action.log.error(this.location(), e);
            return null;
        }
    }
    
    String location() {
        return SAXHandler.location(this.root);
    }
    
    @Override
    public String toString() {
        final Format format = Format.getPrettyFormat();
        final XMLOutputter p = new XMLOutputter(format);
        return p.outputString(this.root);
    }
    
    boolean apply() {
        boolean result = true;
        this.processAttributes();
        if (this.condition()) {
            this.processContent();
            result = this.applyChildren();
        }
        Action.log.debug("result (" + result + ") for: " + this.id());
        return result;
    }
    
    void processAttributes() {
        try {
            this.context.scriptHandler().process(this.root.getAttributes());
        }
        catch (Exception e) {
            Action.log.error(this.id(), e);
        }
    }
    
    void processContent() {
        try {
            final String[] never = { "dictionary", "model", "import" };
            final Set<String> filter = new HashSet<String>();
            String[] array;
            for (int length = (array = never).length, i = 0; i < length; ++i) {
                final String s = array[i];
                filter.add(s);
            }
            String[] tags;
            for (int length2 = (tags = actions.tags()).length, j = 0; j < length2; ++j) {
                final String s = tags[j];
                filter.add(s);
            }
            this.context.scriptHandler().process(this.root, filter);
        }
        catch (Exception e) {
            Action.log.error(this.id(), e);
        }
    }
    
    boolean applyChildren() {
        for (Action a : this.context.actions(this.root, this)) {
            final Action child = a;
            for (int i = -1; i < a.repeat(); ++i) {
                if (a.repeat() > 0) {
                    a = child.clone();
                }
                Action.log.debug("apply:\n" + a.id());
                final boolean abort = !a.apply();
                if (abort) {
                    return false;
                }
            }
            if (this.lastChild(child)) {
                break;
            }
        }
        return true;
    }
    
    int repeat() {
        return 0;
    }
    
    boolean lastChild(final Action a) {
        return false;
    }
    
    boolean condition() {
        return this.condition.evaluate(this);
    }
    
    Element root() {
        return this.root;
    }
    
    Context context() {
        return this.context;
    }
    
    enum actions
    {
        action("action", 0), 
        foreach("foreach", 1), 
        abort("abort", 2), 
        end("end", 3), 
        push("push", 4), 
        pop("pop", 5), 
        tokens("tokens", 6), 
        pswitch("pswitch", 7), 
        pcase("pcase", 8), 
        pwhile("pwhile", 9), 
        call("call", 10), 
        append("append", 11), 
        insert("insert", 12), 
        insertAfter("insertAfter", 13), 
        set("set", 14), 
        delete("delete", 15), 
        move("move", 16), 
        assign("assign", 17), 
        log("log", 18);
        
        private actions(final String s, final int n) {
        }
        
        static String[] tags() {
            final actions[] all = values();
            final String[] result = new String[all.length];
            for (int i = 0; i < all.length; ++i) {
                result[i] = all[i].name();
            }
            return result;
        }
    }
}
