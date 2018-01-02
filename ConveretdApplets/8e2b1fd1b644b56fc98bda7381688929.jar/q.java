// 
// Decompiled by Procyon v0.5.30
// 

public class q
{
    private static final String vra = "~";
    public final int A;
    public final UserDrawTool wra;
    
    public q(final int a, final UserDrawTool wra) {
        this.A = a;
        this.wra = wra;
    }
    
    public q(final String s) {
        final try try1 = new try("~");
        try1.l(s);
        if (try1.g() < 5) {
            throw new IllegalArgumentException("toolString: " + s);
        }
        this.A = Integer.parseInt(try1.a(0));
        (this.wra = (UserDrawTool)Class.forName(try1.a(1)).newInstance()).b(this.a(try1.a(2)), this.a(try1.a(3)));
        this.wra.I(Integer.parseInt(try1.a(4)));
        if (try1.g() > 5 && this.wra instanceof TextTool) {
            ((TextTool)this.wra).setText(try1.a(5));
        }
    }
    
    private double[] a(final String s) {
        if (s == null || s.length() == 0) {
            return new double[0];
        }
        final try try1 = new try(",");
        try1.l(s);
        final double[] array = new double[try1.g()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = Double.valueOf(try1.a(i));
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
        sb.append(this.A);
        sb.append("~");
        sb.append(this.wra.getClass().getName());
        sb.append("~");
        sb.append(this.b(this.wra.Z()));
        sb.append("~");
        sb.append(this.b(this.wra._a()));
        sb.append("~");
        sb.append(this.wra.tra);
        if (this.wra instanceof TextTool) {
            sb.append("~");
            sb.append(((TextTool)this.wra).getText());
        }
        return sb.toString();
    }
}
