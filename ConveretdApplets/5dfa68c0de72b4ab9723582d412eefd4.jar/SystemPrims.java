import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class SystemPrims extends Primitives
{
    static String[] primlist;
    
    public String[] primlist() {
        return SystemPrims.primlist;
    }
    
    public Object dispatch(final int n, final Object[] array, final LContext lContext) {
        switch (n) {
            case 0: {
                return this.prim_resett(lContext);
            }
            case 1: {
                return this.prim_timer(lContext);
            }
            case 2: {
                return this.prim_wait(array[0], lContext);
            }
            case 3: {
                return this.prim_mwait(array[0], lContext);
            }
            case 4: {
                return this.prim_eq(array[0], array[1], lContext);
            }
            case 5: {
                return this.prim_parleft(lContext);
            }
            case 6: {
                return this.prim_parright(lContext);
            }
            case 7: {
                return new Boolean(true);
            }
            case 8: {
                return new Boolean(false);
            }
            case 9: {
                return this.prim_hexw(array[0], array[1], lContext);
            }
            case 10: {
                return "\t";
            }
            case 11: {
                return array[0].getClass();
            }
            case 12: {
                return this.prim_class(array[0], lContext);
            }
            case 13: {
                return this.prstring(array[0]);
            }
            case 14: {
                lContext.tyo.println(Logo.prs(array[0]));
                return null;
            }
            case 15: {
                lContext.tyo.print(Logo.prs(array[0]));
                return null;
            }
            case 16: {
                return null;
            }
            default: {
                return null;
            }
        }
    }
    
    Object prim_resett(final LContext lContext) {
        Logo.starttime = System.currentTimeMillis();
        return null;
    }
    
    Object prim_timer(final LContext lContext) {
        return new Double(System.currentTimeMillis() - Logo.starttime);
    }
    
    Object prim_wait(final Object o, final LContext lContext) {
        this.sleepForMSecs((int)(100.0 * Logo.aDouble(o, lContext)), lContext);
        return null;
    }
    
    Object prim_mwait(final Object o, final LContext lContext) {
        this.sleepForMSecs(Logo.anInt(o, lContext), lContext);
        return null;
    }
    
    void sleepForMSecs(final int n, final LContext lContext) {
        if (n <= 0) {
            return;
        }
        for (long currentTimeMillis = System.currentTimeMillis(), n2 = System.currentTimeMillis() - currentTimeMillis; n2 >= 0L && n2 < n; n2 = System.currentTimeMillis() - currentTimeMillis) {
            if (lContext.timeToStop) {
                Logo.error("Stopped!!!", lContext);
            }
            try {
                Thread.sleep(Math.min(n - n2, 10L));
            }
            catch (InterruptedException ex) {}
        }
    }
    
    Object prim_eq(final Object o, final Object o2, final LContext lContext) {
        return new Boolean(o.equals(o2));
    }
    
    Object prim_parright(final LContext lContext) {
        Logo.error("Missing \"(\"", lContext);
        return null;
    }
    
    Object prim_parleft(final LContext lContext) {
        if (this.ipmnext(lContext.iline)) {
            return this.ipmcall(lContext);
        }
        final Object eval = Logo.eval(lContext);
        final Object next = lContext.iline.next();
        if (next instanceof Symbol && ((Symbol)next).pname.equals(")")) {
            return eval;
        }
        Logo.error("Missing \")\"", lContext);
        return null;
    }
    
    boolean ipmnext(final MapList list) {
        try {
            return ((Symbol)list.peek()).fcn.ipm;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    Object ipmcall(final LContext lContext) {
        final Vector vector = new Vector<Object>();
        lContext.cfun = (Symbol)lContext.iline.next();
        while (!this.finIpm(lContext.iline)) {
            vector.addElement(Logo.evalOneArg(lContext.iline, lContext));
        }
        final Object[] array = new Object[vector.size()];
        vector.copyInto(array);
        return Logo.evalSym(lContext.cfun, array, lContext);
    }
    
    boolean finIpm(final MapList list) {
        if (list.eof()) {
            return true;
        }
        final Object peek = list.peek();
        if (peek instanceof Symbol && ((Symbol)peek).pname.equals(")")) {
            list.next();
            return true;
        }
        return false;
    }
    
    Object prim_hexw(final Object o, final Object o2, final LContext lContext) {
        Logo.anInt(o, lContext);
        final String prs = Logo.prs(o, 16);
        return "00000000".substring(8 - Logo.anInt(o2, lContext) + prs.length()) + prs;
    }
    
    Object prim_class(final Object o, final LContext lContext) {
        try {
            return Class.forName(Logo.prs(o));
        }
        catch (Exception ex) {}
        catch (Error error) {}
        return "";
    }
    
    String prstring(final Object o) {
        if (o instanceof Number && Logo.isInt((Number)o)) {
            return Long.toString(((Number)o).longValue(), 10);
        }
        if (o instanceof String) {
            return "|" + (String)o + "|";
        }
        if (o instanceof Object[]) {
            String s = "";
            final Object[] array = (Object[])o;
            for (int i = 0; i < array.length; ++i) {
                if (array[i] instanceof Object[]) {
                    s += "[";
                }
                s += this.prstring(array[i]);
                if (array[i] instanceof Object[]) {
                    s += "]";
                }
                if (i != array.length - 1) {
                    s += " ";
                }
            }
            return s;
        }
        return o.toString();
    }
    
    static {
        SystemPrims.primlist = new String[] { "resett", "0", "%timer", "0", "wait", "1", "mwait", "1", "eq", "2", "(", "0", ")", "0", "true", "0", "false", "0", "hexw", "2", "tab", "0", "classof", "1", "class", "1", "string", "1", "print", "1", "prs", "1", "ignore", "1" };
    }
}
