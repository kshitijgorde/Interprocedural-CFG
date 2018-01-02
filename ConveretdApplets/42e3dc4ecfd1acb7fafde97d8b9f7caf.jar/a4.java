import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class a4
{
    private Vector a;
    
    public a4() {
        this.a = new Vector();
    }
    
    public void a(final Object o) {
        synchronized (this.a) {
            this.a.addElement(o);
            this.a.notify();
        }
    }
    
    public Object a() {
        if (this.a.size() == 0) {
            synchronized (this.a) {
                try {
                    this.a.wait();
                }
                catch (Exception ex) {}
            }
        }
        Object firstElement;
        try {
            firstElement = this.a.firstElement();
            this.a.removeElementAt(0);
        }
        catch (ArrayIndexOutOfBoundsException ex2) {
            throw new InternalError("Race hazard in Queue object.");
        }
        return firstElement;
    }
}
