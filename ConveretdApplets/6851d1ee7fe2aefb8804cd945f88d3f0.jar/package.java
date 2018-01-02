import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class package extends implements
{
    public static final int Hla = 0;
    public static final int Ila = 1;
    public static final int Jla = 2;
    public static final int Kla = 3;
    public static final int Lla = 4;
    public static final int Mla = 5;
    public static final int Nla = 0;
    public static final int Ola = 1;
    public static final int Pla = 2;
    public static final int Qla = 3;
    private int Rla;
    private int w;
    private boolean Sla;
    private boolean Tla;
    private boolean Ula;
    private boolean Vla;
    
    public package(final String s, final int[] array, final class class1) {
        super(s, 6, null, null, class1);
        this.Rla = 3;
        this.w = 22;
        this.Sla = true;
        this.Tla = true;
        this.Ula = false;
        this.Vla = false;
        this.W();
    }
    
    protected void W() {
        super.r[0] = 0;
        super.r[1] = 0;
        super.r[2] = 0;
        super.r[3] = 0;
        super.r[4] = 0;
        super.r[5] = 0;
    }
    
    protected void X() {
        final double[] y = super.s.Y();
        final double[] a = super.s.a();
        final double[] b = super.s.b();
        final double[] _ = super.s._();
        if (y == null || a == null || b == null || _ == null) {
            super.t = null;
            return;
        }
        final int y2 = super.s.y();
        if (y2 == 100003 && (this.Rla == 1 || this.Rla == 2)) {
            super.t = null;
            return;
        }
        if (y2 == 100002 && this.Rla == 1) {
            super.t = null;
            return;
        }
        super.t = new double[super.u][_.length];
        final double[] array = new double[a.length];
        final double[] array2 = new double[b.length];
        final double[] array3 = new double[_.length];
        switch (this.Rla) {
            case 3: {
                this.a(y, a, b, _, array, array2, array3, super.s.b());
                break;
            }
            case 2: {
                this.b(y, a, b, _, array, array2, array3, super.s.b());
                break;
            }
            case 1: {
                this._(y, a, b, _, array, array2, array3, super.s.b());
                break;
            }
            case 0: {
                this.a(a, b, _, array, array2, array3, this.w);
                break;
            }
        }
        for (int i = 0; i < _.length; ++i) {
            if (array[i] > 0.0 && array2[i] > 0.0 && array3[i] > 0.0) {
                super.t[0][i] = 1.0;
                super.t[1][i] = (array[i] + array2[i] + array3[i]) / 3.0;
            }
        }
        for (int j = 0; j < super.t[0].length; ++j) {
            if (super.t[0][j] > 0.0) {
                for (int k = j; k < super.t[0].length; ++k) {
                    super.t[2][k] = Math.max(0.0, 2.0 * super.t[1][j] - array2[j]);
                    super.t[3][k] = Math.max(0.0, 2.0 * super.t[1][j] - array[j]);
                    super.t[4][k] = Math.max(0.0, super.t[1][j] - super.t[3][k] + super.t[2][k]);
                    super.t[5][k] = Math.max(0.0, super.t[1][j] - (super.t[2][k] - super.t[3][k]));
                    if (k > j && super.t[1][k] > 0.0) {
                        break;
                    }
                    super.t[1][k] = super.t[1][j];
                }
            }
        }
        if (this.Vla) {
            int n = 0;
            for (int l = super.t[1].length - 1; l >= 0; --l) {
                if (n != 0) {
                    final double[] array4 = super.t[0];
                    final int n2 = l;
                    final double[] array5 = super.t[1];
                    final int n3 = l;
                    final double[] array6 = super.t[2];
                    final int n4 = l;
                    final double[] array7 = super.t[3];
                    final int n5 = l;
                    final double[] array8 = super.t[4];
                    final int n6 = l;
                    final double[] array9 = super.t[5];
                    final int n7 = l;
                    final double n8 = 0.0;
                    array8[n6] = (array9[n7] = n8);
                    array6[n4] = (array7[n5] = n8);
                    array4[n2] = (array5[n3] = n8);
                }
                if (super.t[0][l] > 0.0) {
                    n = 1;
                }
            }
        }
        if (!this.Sla) {
            for (int n9 = 0; n9 < super.t[1].length; ++n9) {
                super.t[1][n9] = 0.0;
            }
        }
        if (!this.Tla) {
            for (int n10 = 0; n10 < super.t[1].length; ++n10) {
                super.t[2][n10] = (super.t[3][n10] = 0.0);
            }
        }
        if (!this.Ula) {
            for (int n11 = 0; n11 < super.t[1].length; ++n11) {
                super.t[4][n11] = (super.t[5][n11] = 0.0);
            }
        }
        boolean b2 = false;
        for (int n12 = 0; n12 < super.t[0].length; ++n12) {
            if (super.t[0][n12] > 0.0) {
                b2 = true;
                break;
            }
        }
        if (!b2) {
            super.t = null;
        }
    }
    
    private void a(final double[] array, final double[] array2, final double[] array3, final double[] array4, final double[] array5, final double[] array6, final int n) {
        for (int i = array3.length - 1; i >= 0; i -= this.w) {
            double max = array[i];
            double min = array2[i];
            for (int n2 = i - 1; n2 >= 0 && n2 > i - this.w; --n2) {
                max = Math.max(array[n2], max);
                min = Math.min(array2[n2], min);
            }
            array4[i] = max;
            array5[i] = min;
            array6[i] = array3[i];
        }
    }
    
    private void a(final double[] array, final double[] array2, final double[] array3, final double[] array4, final double[] array5, final double[] array6, final double[] array7, final boolean b) {
        if (array.length < 1) {
            return;
        }
        final r r = new r((int)array[0]);
        int p8 = r.p();
        double max = array2[0];
        double min = array3[0];
        for (int i = 0; i < array4.length; ++i) {
            r._((int)array[i]);
            final int p9 = r.p();
            if (p9 != p8) {
                if (i > 0) {
                    array5[i - 1] = max;
                    array6[i - 1] = min;
                    array7[i - 1] = array4[i - 1];
                }
                max = array2[i];
                min = array3[i];
                if (!b && i == array4.length - 1 && r.e()) {
                    array5[i] = max;
                    array6[i] = min;
                    array7[i] = array4[i];
                }
            }
            else {
                max = Math.max(array2[i], max);
                min = Math.min(array3[i], min);
                if (!b && i == array4.length - 1 && r.e()) {
                    array5[i] = max;
                    array6[i] = min;
                    array7[i] = array4[i];
                }
            }
            p8 = p9;
        }
    }
    
    private void b(final double[] array, final double[] array2, final double[] array3, final double[] array4, final double[] array5, final double[] array6, final double[] array7, final boolean b) {
        if (array.length < 1) {
            return;
        }
        final r r = new r((int)array[0]);
        int z = r.z();
        double max = array2[0];
        double min = array3[0];
        for (int i = 0; i < array4.length; ++i) {
            r._((int)array[i]);
            final int z2 = r.z();
            if (z2 != z) {
                if (i > 0) {
                    array5[i - 1] = max;
                    array6[i - 1] = min;
                    array7[i - 1] = array4[i - 1];
                }
                max = array2[i];
                min = array3[i];
                if (!b && i == array4.length - 1 && r.f()) {
                    array5[i] = max;
                    array6[i] = min;
                    array7[i] = array4[i];
                }
            }
            else {
                max = Math.max(array2[i], max);
                min = Math.min(array3[i], min);
                if (!b && i == array4.length - 1 && r.f()) {
                    array5[i] = max;
                    array6[i] = min;
                    array7[i] = array4[i];
                }
            }
            z = z2;
        }
    }
    
    private void _(final double[] array, final double[] array2, final double[] array3, final double[] array4, final double[] array5, final double[] array6, final double[] array7, final boolean b) {
        if (array.length < 1) {
            return;
        }
        int n = (int)array[0];
        double max = array2[0];
        double min = array3[0];
        for (int i = 0; i < array4.length; ++i) {
            final int n2 = (int)array[i];
            if (n2 != n) {
                if (i > 0) {
                    array5[i - 1] = max;
                    array6[i - 1] = min;
                    array7[i - 1] = array4[i - 1];
                }
                max = array2[i];
                min = array3[i];
                if (!b && i == array4.length - 1) {
                    array5[i] = max;
                    array6[i] = min;
                    array7[i] = array4[i];
                }
            }
            else {
                max = Math.max(array2[i], max);
                min = Math.min(array3[i], min);
                if (!b && i == array4.length - 1) {
                    array5[i] = max;
                    array6[i] = min;
                    array7[i] = array4[i];
                }
            }
            n = n2;
        }
    }
    
    public boolean b(final Frame frame, final o o) {
        final volatile volatile1 = new volatile(this, frame, o);
        final while while1 = new while(frame, super.name, o, volatile1);
        while1.show();
        if (while1.a()) {
            if (volatile1.hla.getState()) {
                this.Rla = 3;
            }
            else if (volatile1.gla.getState()) {
                this.Rla = 2;
            }
            else if (volatile1.fla.getState()) {
                this.Rla = 1;
            }
            else if (volatile1.ila.getState()) {
                this.Rla = 0;
            }
            try {
                this.w = Integer.parseInt(volatile1.k.getText());
            }
            catch (NumberFormatException ex) {}
            this.Sla = volatile1.jla.getState();
            this.Tla = volatile1.kla.getState();
            this.Ula = volatile1.lla.getState();
            this.Vla = volatile1.mla.getState();
            super.y = true;
        }
        return while1.a();
    }
    
    public boolean i() {
        return true;
    }
    
    public void k(final String s) {
        final u u = new u(",");
        u.m(s);
        if (u.a() != 6) {
            return;
        }
        final String b = u.b(0);
        if (b != null) {
            try {
                this.Rla = Integer.parseInt(b);
            }
            catch (NumberFormatException ex) {}
        }
        if (this.Rla < 0 || this.Rla > 3) {
            this.Rla = 3;
        }
        final String b2 = u.b(1);
        if (b2 != null) {
            try {
                this.w = Integer.parseInt(b2);
            }
            catch (NumberFormatException ex2) {}
        }
        if (this.w < 1 || this.w > 999) {
            this.w = 22;
        }
        if ("1".equals(u.b(2))) {
            this.Sla = true;
        }
        else {
            this.Sla = false;
        }
        if ("1".equals(u.b(3))) {
            this.Tla = true;
        }
        else {
            this.Tla = false;
        }
        if ("1".equals(u.b(4))) {
            this.Ula = true;
        }
        else {
            this.Ula = false;
        }
        if ("1".equals(u.b(5))) {
            this.Vla = true;
        }
        else {
            this.Vla = false;
        }
        super.y = true;
    }
    
    public String k() {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.Rla);
        sb.append(",");
        sb.append(this.w);
        sb.append(",");
        sb.append(this.Sla ? 1 : 0);
        sb.append(",");
        sb.append(this.Tla ? 1 : 0);
        sb.append(",");
        sb.append(this.Ula ? 1 : 0);
        sb.append(",");
        sb.append(this.Vla ? 1 : 0);
        return sb.toString();
    }
    
    static int _(final package package1) {
        return package1.Rla;
    }
    
    static int a(final package package1) {
        return package1.w;
    }
    
    static boolean a(final package package1) {
        return package1.Sla;
    }
    
    static boolean b(final package package1) {
        return package1.Tla;
    }
    
    static boolean _(final package package1) {
        return package1.Ula;
    }
    
    static boolean k(final package package1) {
        return package1.Vla;
    }
}
