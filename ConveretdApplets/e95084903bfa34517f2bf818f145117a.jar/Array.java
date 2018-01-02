// 
// Decompiled by Procyon v0.5.30
// 

public class Array extends Collection
{
    private static final String CANT_DO = "Can't do ";
    private static final String ELEM = " elements";
    private static final String ITEM_NUM = "Item number ";
    private static final String MORE_THAN = "An array must have more than ";
    private static final String NOT_IN = " not in ";
    private static final String ON_AN_ARRAY = " on an array";
    private Object[] stuff;
    
    public Array(final Object[] stuff) {
        this.stuff = stuff;
    }
    
    public Array(final int n) throws TTRuntimeError {
        if (n > 0) {
            this.stuff = new Object[n];
        }
        else {
            if (n != 0) {
                final StringBuffer sb = new StringBuffer("An array must have more than ");
                sb.append(n);
                sb.append(" elements");
                throw new TTRuntimeError(sb.toString());
            }
            this.stuff = null;
        }
    }
    
    public Array(final List list) throws TTRuntimeError {
        final int length = list.length();
        this.stuff = new Object[length];
        for (int i = 1; i <= length; ++i) {
            this.stuff[i - 1] = list.item(i);
        }
    }
    
    public Object butFirst() throws TTRuntimeError {
        throw new TTRuntimeError("Can't do butfirst on an array");
    }
    
    public Object butLast() throws TTRuntimeError {
        throw new TTRuntimeError("Can't do butlast on an array");
    }
    
    public Integer count() {
        return new Integer((this.stuff == null) ? 0 : this.stuff.length);
    }
    
    public Boolean emptyp() {
        return new Boolean(false);
    }
    
    public Object first() throws TTRuntimeError {
        throw new TTRuntimeError("Can't do first on an array");
    }
    
    public Boolean isArray() {
        return new Boolean(true);
    }
    
    public Boolean isList() {
        return new Boolean(false);
    }
    
    public Boolean isWord() {
        return new Boolean(false);
    }
    
    public Object item(final int n) throws TTRuntimeError {
        if (this.stuff == null || n < 1 || n > this.stuff.length) {
            final StringBuffer sb = new StringBuffer("Item number ");
            sb.append(n);
            sb.append(" not in ");
            sb.append(this.toString());
            throw new TTRuntimeError(sb.toString());
        }
        final Object o = this.stuff[n - 1];
        if (o == null) {
            return new List();
        }
        return o;
    }
    
    public Object last() throws TTRuntimeError {
        throw new TTRuntimeError("Can't do last on an array");
    }
    
    public Boolean memberp(final Object o) {
        if (this.stuff == null) {
            return new Boolean(false);
        }
        for (int i = 0; i < this.stuff.length; ++i) {
            if (this.stuff[i].equals(o)) {
                return new Boolean(true);
            }
        }
        return new Boolean(false);
    }
    
    public boolean setitem(final int n, final Object o) {
        if (this.stuff == null || n < 1 || n > this.stuff.length) {
            return false;
        }
        this.stuff[n - 1] = o;
        return true;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer("{");
        if (this.stuff != null) {
            for (int i = 0; i < this.stuff.length; ++i) {
                if (i > 0) {
                    sb.append(" ");
                }
                final Object o = this.stuff[i];
                if (o != null) {
                    if (o instanceof List) {
                        sb.append("[");
                    }
                    sb.append(o.toString());
                    if (o instanceof List) {
                        sb.append("]");
                    }
                }
                else {
                    sb.append("[]");
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
