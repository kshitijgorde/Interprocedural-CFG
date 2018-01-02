// 
// Decompiled by Procyon v0.5.30
// 

public class List extends Collection implements Cloneable
{
    private static final String CLASS_NAME = "List";
    private static String CANT_DO;
    private static String ITEM_NUM;
    private static String ON_AN_EMPTY_LIST;
    private static String NOT_IN;
    private static String ME;
    private Object value;
    private List next;
    
    public List() {
        this.value = null;
        this.next = null;
    }
    
    public List(final Object value) {
        this.value = value;
        this.next = null;
    }
    
    public List(final Object value, final Object o) {
        this.value = value;
        this.next = new List(o);
    }
    
    public void append(final Object o) {
        if (this.value == null) {
            this.value = o;
        }
        else {
            List next;
            for (next = this; next.next != null; next = next.next) {}
            if (next.value == null) {
                next.value = o;
            }
            else {
                next.next = new List(o);
            }
        }
    }
    
    public Object butFirst() throws TTRuntimeError {
        if (this.value == null) {
            throw new TTRuntimeError(List.CANT_DO + "butfirst" + List.ON_AN_EMPTY_LIST);
        }
        if (this.next == null) {
            return new List();
        }
        return this.next.clone();
    }
    
    public Object butLast() throws TTRuntimeError {
        if (this.value == null) {
            throw new TTRuntimeError(List.CANT_DO + "butlast" + List.ON_AN_EMPTY_LIST);
        }
        List next;
        final List list = next = new List();
        for (List next2 = this; next2.next != null; next2 = next2.next) {
            if (next.value == null) {
                next.value = next2.value;
            }
            else {
                next.next = new List(next2.value);
                next = next.next;
            }
        }
        return list;
    }
    
    public Object clone() {
        List next;
        final List list = next = new List();
        for (List next2 = this; next2 != null; next2 = next2.next) {
            if (next2.value != null) {
                if (next.value == null) {
                    next.value = next2.value;
                }
                else {
                    next.next = new List(next2.value);
                    next = next.next;
                }
            }
        }
        return list;
    }
    
    public Integer count() {
        if (this.value == null) {
            return new Integer(0);
        }
        int n = 1;
        for (List next = this; next.next != null; next = next.next) {
            ++n;
        }
        return new Integer(n);
    }
    
    public Boolean emptyp() {
        if (this.value == null) {
            return new Boolean(true);
        }
        return new Boolean(false);
    }
    
    public Boolean equalp(final List list) {
        return new Boolean(this.equals(list));
    }
    
    public boolean equals(final List list) {
        List next;
        List next2;
        for (next = this, next2 = list; next != null && next2 != null; next = next.next, next2 = next2.next) {
            if (!next.value.equals(next2.value)) {
                return false;
            }
        }
        return next == null && next2 == null;
    }
    
    public Object first() throws TTRuntimeError {
        if (this.value == null) {
            throw new TTRuntimeError(List.CANT_DO + "first" + List.ON_AN_EMPTY_LIST);
        }
        return this.value;
    }
    
    public Boolean isArray() {
        return new Boolean(false);
    }
    
    public Boolean isList() {
        return new Boolean(true);
    }
    
    public Boolean isWord() {
        return new Boolean(false);
    }
    
    public Object item(final int n) throws TTRuntimeError {
        if (n < 1 || this.value == null) {
            final StringBuffer sb = new StringBuffer(List.ITEM_NUM);
            sb.append(n);
            sb.append(List.NOT_IN);
            sb.append(this.toString());
            throw new TTRuntimeError(sb.toString());
        }
        int i = 1;
        List next = this;
        while (i < n) {
            if (next.next == null) {
                final StringBuffer sb2 = new StringBuffer(List.ITEM_NUM);
                sb2.append(n);
                sb2.append(List.NOT_IN);
                sb2.append(this.toString());
                throw new TTRuntimeError(sb2.toString());
            }
            next = next.next;
            ++i;
        }
        return next.value;
    }
    
    public Object last() throws TTRuntimeError {
        if (this.value == null) {
            throw new TTRuntimeError(List.CANT_DO + "last" + List.ON_AN_EMPTY_LIST);
        }
        List next;
        for (next = this; next.next != null; next = next.next) {}
        return next.value;
    }
    
    public int length() {
        int n = 0;
        if (this.value != null) {
            n = 1;
            for (List next = this; next.next != null; next = next.next) {
                ++n;
            }
        }
        return n;
    }
    
    public Boolean memberp(final Object o) {
        for (List next = this; next != null; next = next.next) {
            if (next.value == null) {
                return new Boolean(false);
            }
            if (next.value.equals(o)) {
                return new Boolean(true);
            }
        }
        return new Boolean(false);
    }
    
    public static List sentence(final Object[] array) throws TTRuntimeError {
        final List list = new List();
        for (int i = 0; i < array.length; ++i) {
            Object next = array[i];
            if (next instanceof Array) {
                throw new TTRuntimeError("SENTENCE doesn't like '" + next + "' as an input");
            }
            if (next instanceof Word) {
                list.append(next);
            }
            else if (next instanceof List) {
                while (((List)next).value != null) {
                    list.append(((List)next).value);
                    next = ((List)next).next;
                    if (next == null) {
                        break;
                    }
                }
            }
        }
        return list;
    }
    
    public Array toArray() throws TTRuntimeError {
        if (this.value == null) {
            throw new TTRuntimeError(List.CANT_DO + "listtoarray" + List.ON_AN_EMPTY_LIST);
        }
        return new Array(this);
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        if (this.value != null) {
            sb.append(this.value.toString());
            for (List list = this.next; list != null; list = list.next) {
                if (this.value == null) {
                    System.err.print(List.ME + "toString: unexpected null value at \"");
                    System.err.println(sb.toString() + "\"");
                    break;
                }
                sb.append(" ");
                sb.append(list.value.toString());
            }
        }
        return sb.toString();
    }
    
    static {
        List.CANT_DO = "Can't do ";
        List.ITEM_NUM = "Item number ";
        List.ON_AN_EMPTY_LIST = " on an empty list";
        List.NOT_IN = " not in ";
        List.ME = "List.";
    }
}
