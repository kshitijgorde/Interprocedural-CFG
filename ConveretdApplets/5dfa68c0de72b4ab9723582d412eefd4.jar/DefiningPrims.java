import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class DefiningPrims extends Primitives
{
    static String[] primlist;
    
    public String[] primlist() {
        return DefiningPrims.primlist;
    }
    
    public Object dispatch(final int n, final Object[] array, final LContext lContext) {
        switch (n) {
            case 0: {
                return this.prim_make(array[0], array[1], lContext);
            }
            case 1: {
                return this.prim_define(array[0], array[1], array[2], lContext);
            }
            case 2: {
                return this.prim_let(array[0], lContext);
            }
            case 3: {
                return this.prim_thing(array[0], lContext);
            }
            case 4: {
                return this.prim_put(array[0], array[1], array[2], lContext);
            }
            case 5: {
                return this.prim_get(array[0], array[1], lContext);
            }
            case 6: {
                return this.prim_get(array[0], array[1], lContext);
            }
            case 7: {
                return this.prim_plist(array[0], lContext);
            }
            case 8: {
                return this.prim_erplist(array[0], lContext);
            }
            case 9: {
                return this.prim_namep(array[0], lContext);
            }
            case 10: {
                return this.prim_definedp(array[0], lContext);
            }
            case 11: {
                return this.prim_clearname(array[0], lContext);
            }
            case 12: {
                return this.prim_quote(array[0], lContext);
            }
            case 13: {
                return this.prim_intern(array[0], lContext);
            }
            default: {
                return null;
            }
        }
    }
    
    Object prim_make(final Object o, final Object o2, final LContext lContext) {
        Logo.setValue(Logo.aSymbol(o, lContext), o2, lContext);
        return null;
    }
    
    Object prim_clearname(final Object o, final LContext lContext) {
        Logo.setValue(Logo.aSymbol(o, lContext), null, lContext);
        return null;
    }
    
    Object prim_define(final Object o, final Object o2, final Object o3, final LContext lContext) {
        final Symbol aSymbol = Logo.aSymbol(o, lContext);
        final Object[] aList = Logo.aList(o2, lContext);
        aSymbol.fcn = new Function(new Ufun(aList, Logo.aList(o3, lContext)), aList.length, 0);
        return null;
    }
    
    Object prim_let(final Object o, final LContext lContext) {
        final Vector vector = new Vector<Symbol>();
        if (lContext.locals != null) {
            for (int i = 0; i < lContext.locals.length; ++i) {
                vector.addElement(lContext.locals[i]);
            }
        }
        final MapList list = new MapList(Logo.aList(o, lContext));
        while (!list.eof()) {
            final Symbol aSymbol = Logo.aSymbol(list.next(), lContext);
            vector.addElement(aSymbol);
            vector.addElement(aSymbol.value);
            Logo.setValue(aSymbol, Logo.evalOneArg(list, lContext), lContext);
        }
        vector.copyInto(lContext.locals = new Object[vector.size()]);
        return null;
    }
    
    Object prim_thing(final Object o, final LContext lContext) {
        return Logo.getValue(Logo.aSymbol(o, lContext), lContext);
    }
    
    Object prim_put(final Object o, final Object o2, final Object o3, final LContext lContext) {
        Hashtable<Object, Object> hashtable = lContext.props.get(o);
        if (hashtable == null) {
            hashtable = new Hashtable<Object, Object>();
            lContext.props.put(o, hashtable);
        }
        hashtable.put(o2, o3);
        return null;
    }
    
    Object prim_get(final Object o, final Object o2, final LContext lContext) {
        final Hashtable<Object, Object> hashtable = lContext.props.get(o);
        if (hashtable == null) {
            return new Object[0];
        }
        final Object value = hashtable.get(o2);
        if (value == null) {
            return new Object[0];
        }
        return value;
    }
    
    Object prim_plist(final Object o, final LContext lContext) {
        final Hashtable<Object, Hashtable<Object, Hashtable<Object, Hashtable>>> hashtable = lContext.props.get(o);
        if (hashtable == null) {
            return new Object[0];
        }
        final Vector vector = new Vector<Object>();
        final Enumeration<Object> keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            final Object nextElement = keys.nextElement();
            vector.add(nextElement);
            vector.add(hashtable.get(nextElement));
        }
        final Object[] array = new Object[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    Object prim_erplist(final Object o, final LContext lContext) {
        lContext.props.remove(o);
        return null;
    }
    
    Object prim_namep(final Object o, final LContext lContext) {
        return new Boolean(Logo.aSymbol(o, lContext).value != null);
    }
    
    Object prim_definedp(final Object o, final LContext lContext) {
        return new Boolean(Logo.aSymbol(o, lContext).fcn != null);
    }
    
    Object prim_quote(final Object o, final LContext lContext) {
        if (o instanceof Object[]) {
            return o;
        }
        return new QuotedSymbol(Logo.aSymbol(o, lContext));
    }
    
    Object prim_intern(final Object o, final LContext lContext) {
        return Logo.aSymbol(o, lContext);
    }
    
    static {
        DefiningPrims.primlist = new String[] { "make", "2", "define", "3", "let", "1", "thing", "1", "put", "3", "get", "2", "getp", "2", "plist", "1", "erplist", "1", "name?", "1", "defined?", "1", "clearname", "1", "quote", "1", "intern", "1" };
    }
}
