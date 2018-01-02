import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class Mh
{
    private static final String bka = "~";
    private static Color cka;
    public final int jb;
    public final UserDrawTool dka;
    
    public Mh(final int jb, final UserDrawTool dka) {
        this.jb = jb;
        this.dka = dka;
    }
    
    public Mh(final String s) {
        final u u = new u("~");
        u.m(s);
        if (u.a() < 5) {
            throw new IllegalArgumentException("toolString: " + s);
        }
        this.jb = Integer.parseInt(u.b(0));
        (this.dka = (UserDrawTool)Class.forName(u.b(1)).newInstance()).a(this.a(u.b(2)), this.a(u.b(3)));
        this.dka.h(Integer.parseInt(u.b(4)));
        if (this.dka instanceof TextTool) {
            if (u.a() > 5) {
                ((TextTool)this.dka).setText(u.b(5));
            }
            if (u.a() > 6) {
                this.dka.setColor(Color.decode(u.b(6)));
            }
            else {
                this.dka.setColor(Mh.cka);
            }
        }
        else if (u.a() > 5) {
            this.dka.setColor(Color.decode(u.b(5)));
        }
        else {
            this.dka.setColor(Mh.cka);
        }
    }
    
    private double[] a(final String s) {
        if (s == null || s.length() == 0) {
            return new double[0];
        }
        final u u = new u(",");
        u.m(s);
        final double[] array = new double[u.a()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = Double.valueOf(u.b(i));
        }
        return array;
    }
    
    private String b(final double[] array) {
        if (array == null || array.length == 0) {
            return "";
        }
        final StringBuffer sb = new StringBuffer(32);
        for (int i = 0; i < array.length; ++i) {
            sb.append(array[i]);
            sb.append(',');
        }
        return sb.toString();
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(128);
        sb.append(this.jb);
        sb.append("~");
        sb.append(this.dka.getClass().getName());
        sb.append("~");
        sb.append(this.b(this.dka.V()));
        sb.append("~");
        sb.append(this.b(this.dka.W()));
        sb.append("~");
        sb.append(this.dka.eka);
        if (this.dka instanceof TextTool) {
            sb.append("~");
            sb.append(((TextTool)this.dka).getText());
        }
        sb.append("~");
        sb.append("0x" + Integer.toHexString(this.dka.getColor().getRGB() & 0xFFFFFF).toUpperCase());
        return sb.toString();
    }
    
    public static void _(final Color cka) {
        Mh.cka = cka;
    }
    
    static {
        Mh.cka = Color.red;
    }
}
