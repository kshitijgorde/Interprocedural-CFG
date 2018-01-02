import java.util.Enumeration;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class Timer
{
    int count;
    long starttime;
    String name;
    long cumtime;
    static Vector timers;
    
    public Timer(final String name) {
        this.count = 0;
        this.cumtime = 0L;
        this.name = name;
        Timer.timers.addElement(this);
    }
    
    public static void printall() {
        final Enumeration<Object> elements = Timer.timers.elements();
        while (elements.hasMoreElements()) {
            System.out.println(elements.nextElement());
        }
    }
    
    public void start() {
        this.starttime = System.currentTimeMillis();
    }
    
    public void stop() {
        this.cumtime += System.currentTimeMillis() - this.starttime;
        ++this.count;
    }
    
    static {
        Timer.timers = new Vector();
    }
}
