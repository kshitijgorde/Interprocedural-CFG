import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Stack;
import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public final class t implements Serializable, Cloneable
{
    public static final long serialVersionUID = -5484027033443045840L;
    public Stack a;
    public String b;
    public Stack c;
    
    public t() {
        this.i();
    }
    
    public void a(final String b) {
        if (this.b != null) {
            this.a.push(this.b);
        }
        this.b = b;
        this.c.removeAllElements();
        this.a(true);
    }
    
    public String a() {
        return this.b;
    }
    
    public boolean b() {
        return this.c() || this.d();
    }
    
    public boolean c() {
        return this.e() != null;
    }
    
    public boolean d() {
        return this.f() != null;
    }
    
    public String e() {
        String s = null;
        if (!this.a.isEmpty()) {
            s = this.a.peek();
        }
        return s;
    }
    
    public String f() {
        String s = null;
        if (!this.c.isEmpty()) {
            s = this.c.peek();
        }
        return s;
    }
    
    public boolean g() {
        String b = null;
        if (!this.a.isEmpty()) {
            b = this.a.pop();
            if (this.b != null) {
                this.c.push(this.b);
            }
            this.b = b;
            this.a(false);
        }
        return b != null;
    }
    
    public boolean h() {
        String b = null;
        if (!this.c.isEmpty()) {
            b = this.c.pop();
            if (this.b != null) {
                this.a.push(this.b);
            }
            this.b = b;
            this.a(true);
        }
        return b != null;
    }
    
    public String toString() {
        final String property = System.getProperty("line.separator");
        final StringBuffer sb = new StringBuffer();
        final int n = this.a.size() + 1 + this.c.size();
        sb.append("WhHistory {" + property);
        for (int i = 0; i < this.a.size(); ++i) {
            sb.append("   [" + (i + 1) + "/" + n + "] " + this.a.elementAt(i) + property);
        }
        sb.append(" > [" + (this.a.size() + 1) + "/" + n + "] " + this.b + property);
        for (int j = this.c.size() - 1; j >= 0; --j) {
            sb.append("   [" + (this.a.size() + 1 + j + 1) + "/" + n + "] " + this.c.elementAt(j) + property);
        }
        sb.append("}");
        return sb.toString();
    }
    
    public Object clone() {
        final t t = new t();
        for (int i = 0; i < this.a.size(); ++i) {
            t.a.push(this.a.elementAt(i));
        }
        t.b = this.b;
        for (int j = 0; j < this.c.size(); ++j) {
            t.c.push(this.c.elementAt(j));
        }
        return t;
    }
    
    public void i() {
        this.a = new Stack();
        this.b = null;
        this.c = new Stack();
    }
    
    public void a(final boolean b) {
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(1);
        objectOutputStream.writeObject(this.a);
        objectOutputStream.writeObject(this.b);
        objectOutputStream.writeObject(this.c);
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException {
        this.i();
        if (objectInputStream.readInt() >= 1) {
            try {
                this.a = (Stack)objectInputStream.readObject();
            }
            catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            try {
                this.b = (String)objectInputStream.readObject();
            }
            catch (ClassNotFoundException ex2) {
                ex2.printStackTrace();
            }
            try {
                this.c = (Stack)objectInputStream.readObject();
            }
            catch (ClassNotFoundException ex3) {
                ex3.printStackTrace();
            }
        }
    }
}
