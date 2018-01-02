// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.util.Vector;

public class RuleList
{
    private Vector _list;
    private Object _def;
    
    public RuleList() {
        this._list = new Vector();
        this._def = null;
    }
    
    public void setDefaultValue(final Object def) {
        this._def = def;
    }
    
    public Object getDefaultValue() {
        return this._def;
    }
    
    public void addRule(final String[] array, final Object value) {
        final ListHandler[] handlers = new ListHandler[array.length];
        for (int i = 0; i < array.length; ++i) {
            handlers[i] = new ListHandler(array[i]);
        }
        final RuleItem ruleItem = new RuleItem();
        ruleItem.handlers = handlers;
        ruleItem.value = value;
        this._list.insertElementAt(ruleItem, this._list.size());
    }
    
    private boolean match(final RuleItem ruleItem, final String[] array) {
        final ListHandler[] handlers = ruleItem.handlers;
        if (array.length != handlers.length) {
            return false;
        }
        for (int i = 0; i < handlers.length; ++i) {
            if (!handlers[i].inList(array[i])) {
                return false;
            }
        }
        return true;
    }
    
    public Object findValue(final String[] array) {
        for (int size = this._list.size(), i = 0; i < size; ++i) {
            final RuleItem ruleItem = this._list.elementAt(i);
            if (this.match(ruleItem, array)) {
                return ruleItem.value;
            }
        }
        return this._def;
    }
}
