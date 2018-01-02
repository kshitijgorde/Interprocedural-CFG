// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

import java.io.Serializable;
import java.util.Vector;

public abstract class Field implements UsageTypes
{
    private Node a;
    private int b;
    protected Field[] c;
    protected Vector d;
    protected Vector e;
    private int f;
    private boolean g;
    
    public Field getRoutedField(final int n) {
        if (n < 0 || n >= this.getNumRoutes()) {
            throw new Shout3DException("Error in getRoutedField, requested route out of range");
        }
        return this.c[n];
    }
    
    public Node getOwner() {
        return this.a;
    }
    
    public boolean addRoute(final Field field) throws Shout3DException {
        if (!field.isOfType(this.getTypeName())) {
            throw new Shout3DException("Field can not create route  field of different type");
        }
        if (this.isRouted(field)) {
            return false;
        }
        if (this.c == null) {
            this.c = new Field[1];
        }
        else {
            final Field[] c = new Field[this.c.length + 1];
            System.arraycopy(this.c, 0, c, 0, this.c.length);
            this.c = c;
        }
        this.c[this.c.length - 1] = field;
        return true;
    }
    
    public Field(final Node a, final String s, final int b) {
        this.c = null;
        this.d = null;
        this.e = null;
        this.g = false;
        this.b = b;
        (this.a = a).a(this, s);
    }
    
    public boolean deleteRoute(final Field field) {
        if (!this.isRouted(field)) {
            return false;
        }
        final Field[] c = new Field[this.c.length - 1];
        int n = 0;
        for (int i = 0; i < this.c.length; ++i) {
            if (this.c[i] == field) {
                ++n;
            }
            else {
                c[i + n] = this.c[i];
            }
        }
        if (n != 1) {
            throw new Shout3DException("Error -- field had more than one route to the same node");
        }
        this.c = c;
        return true;
    }
    
    public void setValueByString(final String s) {
    }
    
    public String getValueByString() {
        return null;
    }
    
    protected void a(final Field field) {
    }
    
    public boolean isRouted(final Field field) {
        if (this.c == null) {
            return false;
        }
        for (int i = 0; i < this.c.length; ++i) {
            if (this.c[i] == field) {
                return true;
            }
        }
        return false;
    }
    
    protected void a() {
        if (this.g) {
            return;
        }
        this.g = true;
        if (this.c != null) {
            this.f = 0;
            while (this.f < this.c.length) {
                this.a(this.c[this.f]);
                ++this.f;
            }
        }
        if (this.d != null) {
            for (int i = 0; i < this.d.size(); ++i) {
                ((FieldObserver)this.d.elementAt(i)).onFieldChange(this, this.e.elementAt(i));
            }
        }
        if (this.a != null && this.a.d.d != null) {
            for (int j = 0; j < this.a.d.d.size(); ++j) {
                ((FieldObserver)this.a.d.d.elementAt(j)).onFieldChange(this, this.a.d.e.elementAt(j));
            }
        }
        this.g = false;
    }
    
    public void removeFieldObserver(final FieldObserver fieldObserver) {
        if (this.d != null) {
            final int index = this.d.indexOf(fieldObserver);
            this.d.removeElement(fieldObserver);
            this.e.removeElementAt(index);
            if (this.d.size() == 0) {
                this.d = null;
                this.e = null;
            }
        }
    }
    
    public int getNumRoutes() {
        if (this.c == null) {
            return 0;
        }
        return this.c.length;
    }
    
    public String getName() {
        return this.a.getFieldName(this);
    }
    
    public int getUsage() {
        return this.b;
    }
    
    public boolean isOfType(final String s) {
        Serializable s2 = this.getClass();
        while (true) {
            final String name = ((Class)s2).getName();
            final String substring = name.substring(name.lastIndexOf(".") + 1, name.length());
            if (substring.equals(s)) {
                return true;
            }
            if (substring.equals("Field")) {
                return false;
            }
            s2 = ((Class<? extends Field>)s2).getSuperclass();
        }
    }
    
    public void addFieldObserver(final FieldObserver fieldObserver, final Object o) {
        if (this.d == null) {
            this.d = new Vector(1);
            this.e = new Vector(1);
        }
        this.d.addElement(fieldObserver);
        this.e.addElement(o);
    }
    
    protected void finalize() throws Throwable {
        for (int i = this.getNumRoutes() - 1; i >= 0; --i) {
            this.deleteRoute(this.getRoutedField(i));
        }
        this.a = null;
        super.finalize();
    }
    
    public String getTypeName() {
        final String name = this.getClass().getName();
        return name.substring(name.lastIndexOf(".") + 1, name.length());
    }
}
