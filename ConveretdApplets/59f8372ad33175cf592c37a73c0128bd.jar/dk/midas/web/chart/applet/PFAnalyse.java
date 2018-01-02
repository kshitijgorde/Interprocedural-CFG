// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Graphics;
import java.awt.Color;

public class PFAnalyse extends Analyse
{
    public static final float cQ = 0.001f;
    public static final int cU = 3;
    public static final int cF = 0;
    public static final int cI = 1;
    public static final int cS = 2;
    public static final int cK = 3;
    public static final int cO = 4;
    static Color cL;
    static Color cN;
    static final int cM = 3;
    static final int cT = 8;
    private float cR;
    private u cV;
    private int[] cG;
    private int[] cJ;
    private int[] cH;
    private int cP;
    private int cW;
    
    public PFAnalyse(final ChartManager chartManager, final DataSource dataSource) {
        this(chartManager, o.if(43), dataSource);
    }
    
    public PFAnalyse(final ChartManager chartManager, final Study study, final DataSource dataSource) {
        super(chartManager, study, dataSource, 1);
        super.cu = Integer.MAX_VALUE;
        this.cV = new u(this.null(), this.null().u(), this.null().l(), this.for(this.null()), 3);
    }
    
    public void if(final DataSource dataSource) {
        super.if(dataSource);
        this.cV = new u(dataSource, dataSource.u(), dataSource.l(), this.do(dataSource), this.cV.do());
        this.m();
    }
    
    private float for(final DataSource dataSource) {
        return (dataSource.if(dataSource.bL) + dataSource.a(dataSource.b5)) / 2000.0f;
    }
    
    private float do(final DataSource dataSource) {
        return dataSource.if(dataSource.bL) * this.cR;
    }
    
    public static String createNameSuffix(final Object[] array) {
        return "" + Study.a(array, 0, 0.001f) + ";" + Study.a(array, 1, 3);
    }
    
    public void a(final Object[] array) {
        this.cR = Study.a(array, 0, 0.001f);
        this.cV.a(this.do(this.null()));
        this.cV.a(Study.a(array, 1, 3));
        this.int(createNameSuffix(array));
    }
    
    public void m() {
        this.cV.try();
        final int size = this.cV.if().size();
        if (super.cz[0] == null || super.cz[0].length < size) {
            for (int i = 0; i < super.cz.length; ++i) {
                super.cz[i] = new float[size];
            }
        }
        for (int j = 0; j < size; ++j) {
            super.cz[0][j] = ((r)this.cV.if().elementAt(j)).if();
        }
    }
    
    protected void v() {
        final int size = this.cV.if().size();
        if (super.cv == null || super.cv.length < size) {
            super.cv = new int[size];
            this.cG = new int[size];
            this.cJ = new int[size];
            this.cH = new int[size];
        }
        super.bJ = this.b() / size;
        super.bC = Float.MAX_VALUE;
        super.bF = Float.MIN_VALUE;
        for (int i = 0; i < size; ++i) {
            final r r = this.cV.if().elementAt(i);
            super.bC = Math.min(super.bC, r.for());
            super.bF = Math.max(super.bF, r.if());
        }
        super.bI = (this.char() - this.byte().bottom - this.byte().top) / (super.bF - super.bC);
        int n = Math.max(3, Math.round(this.cV.new() * super.bI));
        for (int j = 0; j < size; ++j) {
            final r r2 = this.cV.if().elementAt(j);
            this.cG[j] = Math.round(this.char() - this.byte().bottom - (r2.for() - super.bC) * super.bI);
            this.cJ[j] = Math.round(this.char() - this.byte().bottom - (r2.if() - super.bC) * super.bI);
            n = Math.max(3, Math.min(n, this.cG[j] - this.cJ[j]));
            this.cH[j] = r2.a();
        }
        this.cW = Math.round(1.0f * super.bJ);
        this.cP = Math.min(this.cW - 3, n);
        if (this.cP < 3) {
            this.cP = 3;
        }
        else if (this.cP > 8) {
            this.cP = 8;
        }
        this.cW = this.cP + 3;
        int n2 = 0;
        for (int k = 0; k < size; ++k, n2 += this.cW) {
            super.cv[k] = n2;
        }
        final int n3 = n2 + this.cW - this.b();
        if (n3 > 0) {
            for (int l = 0; l < size; ++l) {
                final int[] cv = super.cv;
                final int n4 = l;
                cv[n4] -= n3;
            }
        }
        else {
            final int n5 = n3 / 2;
            for (int n6 = 0; n6 < size; ++n6) {
                final int[] cv2 = super.cv;
                final int n7 = n6;
                cv2[n7] -= n5;
            }
        }
    }
    
    protected void k() {
        for (int size = this.cV.if().size(), i = 0; i < size; ++i) {
            if (this.cH[i] == 1) {
                this.a(super.cv[i], this.cG[i], this.cJ[i], this.cH[i], PFAnalyse.cN);
            }
            else if (this.cH[i] == 0) {
                this.a(super.cv[i], this.cG[i], this.cJ[i], this.cH[i], PFAnalyse.cL);
            }
        }
    }
    
    private void a(final int n, final int n2, final int n3, final int n4, final Color color) {
        final int abs = Math.abs(n3 - n2);
        this.else().setColor(color);
        final int n5 = n + this.cP - 1;
        if (n4 == 1) {
            for (int i = 0; i < abs - this.cP; i += this.cP + 1) {
                final int n6 = n3 + i;
                final int n7 = n3 + i + this.cP - 1;
                final int[] array = { n, n, n + 1, n5 - 1, n5, n5, n5 - 1, n + 1 };
                this.else().drawPolygon(array, new int[] { n6 + 1, n7 - 1, n7, n7, n7 - 1, n6 + 1, n6, n6 }, array.length);
            }
        }
        else if (n4 == 0) {
            for (int j = 0; j < abs - this.cP; j += this.cP + 1) {
                final int n8 = n3 + j;
                final int n9 = n3 + j + this.cP - 1;
                this.else().drawLine(n, n8, n5, n9);
                this.else().drawLine(n, n9, n5, n8);
            }
        }
    }
    
    protected void a(final Graphics graphics, final int n, final int n2) {
        if (n > this.case().left && n < this.case().left + this.b() && (n2 < this.case().top || n2 > this.case().top + this.char()) && super.parent.eS.d8 == 8) {
            final int di = super.parent.eS.dI;
            if (this.cV.for().u() <= di && di <= this.cV.for().l()) {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                for (int i = 0; i < this.cV.if().size(); ++i) {
                    if (((r)this.cV.if().elementAt(i)).a(di)) {
                        min = Math.min(min, this.case().left + super.cv[i] - 2);
                        max = Math.max(max, this.case().left + super.cv[i] + this.cP + 2);
                    }
                }
                if (min < max && min > this.case().left) {
                    final int n3 = this.case().top + 1;
                    final int n4 = this.case().top + this.char() - 1;
                    graphics.drawLine(min, n3, min, n4);
                    graphics.drawLine(max, n3, max, n4);
                }
            }
        }
    }
    
    public int e(final int n) {
        int n2 = 0;
        if (this.cV.for().u() <= n && n <= this.cV.for().l()) {
            for (int i = 0; i < this.cV.if().size(); ++i) {
                final r r = this.cV.if().elementAt(i);
                if (r.a(n)) {
                    switch (n2) {
                        case 0: {
                            n2 = ((r.a() == 1) ? 1 : 2);
                            break;
                        }
                        case 1: {
                            return (r.a() == 1) ? 1 : 3;
                        }
                        case 2: {
                            return (r.a() == 1) ? 4 : 2;
                        }
                    }
                }
            }
        }
        return n2;
    }
    
    public synchronized float void() {
        return super.cz[0][this.cV.if().size() - 1];
    }
    
    protected void t() {
        int n = 0;
        boolean b = true;
        for (float n2 = this.char() / 1, n3 = 0.0f; n3 < this.char(); n3 += n2) {
            if (b) {
                this.else().setColor(super.bK.gG);
            }
            else {
                this.else().setColor(super.bK.gE);
            }
            b = !b;
            final int round = Math.round(n3 + n2);
            this.else().fillRect(0, n, this.b(), round - n + 1);
            n = round;
        }
    }
    
    static {
        PFAnalyse.cL = Color.green;
        PFAnalyse.cN = Color.red;
    }
}
