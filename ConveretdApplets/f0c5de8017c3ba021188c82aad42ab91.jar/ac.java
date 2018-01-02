import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Hashtable;
import java.util.TimeZone;
import java.util.Locale;

// 
// Decompiled by Procyon v0.5.30
// 

public class ac
{
    private String a;
    private Locale b;
    private TimeZone c;
    public Hashtable d;
    
    public ac(final String a) {
        this.d = new Hashtable();
        this.a = a;
    }
    
    public final String a(final Date date) {
        return this.b().format(date);
    }
    
    public final void a(final TimeZone c) {
        this.c = c;
    }
    
    public final Date a(final String s) throws ParseException {
        return this.b().parse(s);
    }
    
    private final SimpleDateFormat b() {
        SimpleDateFormat simpleDateFormat = this.d.get(Thread.currentThread().hashCode() + "");
        if (simpleDateFormat == null) {
            if (this.b != null) {
                simpleDateFormat = new SimpleDateFormat(this.a, this.b);
            }
            else {
                simpleDateFormat = new SimpleDateFormat(this.a);
            }
            this.d.put(Thread.currentThread().hashCode() + "", simpleDateFormat);
        }
        if (this.c != null) {
            simpleDateFormat.setTimeZone(this.c);
        }
        return simpleDateFormat;
    }
    
    public void a() {
        this.d.clear();
    }
    
    public String toString() {
        final SimpleDateFormat b = this.b();
        return "SynchronizedSimpleDateFormat[" + this.hashCode() + "]@Formatter[" + b.hashCode() + "] pattern=" + b.toPattern() + ", timezone=" + b.getTimeZone().getID();
    }
}
