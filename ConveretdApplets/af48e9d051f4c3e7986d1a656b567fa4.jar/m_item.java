import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class m_item
{
    private Image Im0;
    private Image Im1;
    private int w0;
    private int h0;
    private int w1;
    private int h1;
    private boolean active;
    private int state;
    static int RETRACTED;
    static int FPROGRESS;
    static int ADVANCED;
    static int BPROGRESS;
    private int x;
    private int y;
    private int xi;
    private int yi;
    private int xf;
    private int yf;
    private int align;
    static int LEFT;
    static int RIGHT;
    private int[] TrajectoryX;
    private int[] TrajectoryY;
    private int InternalCnt;
    private int NbPts;
    static int LINEAR;
    static int SQRT;
    static int PARABOLIC;
    static int EXPONENTIAL;
    static int LOG;
    static int PERIODIC;
    private String TargetPage;
    private String TargetFrame;
    
    m_item(final int xi, final int xf, final int yi, final int yf, final int align, final int n, final int nbPts, final Image im0, final Image im2, final String targetPage, final String targetFrame) {
        this.active = false;
        this.xi = xi;
        this.xf = xf;
        this.yi = yi;
        this.yf = yf;
        this.x = this.xi;
        this.y = this.yi;
        this.align = align;
        this.Im0 = im0;
        this.Im1 = im2;
        this.state = m_item.RETRACTED;
        this.TargetPage = targetPage;
        this.TargetFrame = targetFrame;
        this.NbPts = nbPts;
        this.InternalCnt = 0;
        this.TrajectoryX = new int[this.NbPts];
        this.TrajectoryY = new int[this.NbPts];
        final float n2 = (float)Math.sqrt((this.xf - this.xi) * (this.xf - this.xi) + (this.yf - this.yi) * (this.yf - this.yi));
        float n3;
        float n4;
        if (n2 != 0.0f) {
            n3 = (this.xf - this.xi) / n2;
            n4 = (this.yf - this.yi) / n2;
        }
        else {
            n3 = 0.0f;
            n4 = 0.0f;
        }
        if (n == m_item.SQRT) {
            final float n5 = n2 / (float)Math.sqrt(this.NbPts - 1);
            for (int i = 0; i < this.NbPts; ++i) {
                this.TrajectoryX[i] = (int)Math.round(this.xi + Math.sqrt(i) * n3 * n5);
                this.TrajectoryY[i] = (int)Math.round(this.yi + Math.sqrt(i) * n4 * n5);
            }
            return;
        }
        if (n == m_item.PARABOLIC) {
            final float n6 = n2 / ((this.NbPts - 1) * (this.NbPts - 1));
            for (int j = 0; j < this.NbPts; ++j) {
                this.TrajectoryX[j] = Math.round(this.xi + j * j * n3 * n6);
                this.TrajectoryY[j] = Math.round(this.yi + j * j * n4 * n6);
            }
            return;
        }
        if (n == m_item.EXPONENTIAL) {
            final float n7 = n2 / (float)Math.exp(this.NbPts - 1);
            for (int k = 0; k < this.NbPts; ++k) {
                this.TrajectoryX[k] = (int)Math.round(this.xi + Math.exp(k) * n3 * n7);
                this.TrajectoryY[k] = (int)Math.round(this.yi + Math.exp(k) * n4 * n7);
            }
            return;
        }
        if (n == m_item.LOG) {
            final float n8 = n2 / (float)Math.log(this.NbPts);
            for (int l = 0; l < this.NbPts; ++l) {
                this.TrajectoryX[l] = (int)Math.round(this.xi + Math.log(1 + l) * n3 * n8);
                this.TrajectoryY[l] = (int)Math.round(this.yi + Math.log(1 + l) * n4 * n8);
            }
            return;
        }
        if (n != m_item.PERIODIC) {
            final float n9 = n2 / (this.NbPts - 1);
            for (int n10 = 0; n10 < this.NbPts; ++n10) {
                this.TrajectoryX[n10] = Math.round(this.xi + n10 * n3 * n9);
                this.TrajectoryY[n10] = Math.round(this.yi + n10 * n4 * n9);
            }
            return;
        }
        if (this.xf != this.xi) {
            final float n11 = (this.yf - this.yi) / (this.xf - this.xi);
            final float n12 = this.xf - this.xi;
            for (int n13 = 0; n13 < this.NbPts; ++n13) {
                this.TrajectoryX[n13] = (int)Math.round(this.xi + Math.sin(6.283185307179586 * n13 / (this.NbPts - 1)) * n12);
                this.TrajectoryY[n13] = Math.round(this.yi + n11 * (this.TrajectoryX[n13] - this.xi));
            }
            return;
        }
        final float n14 = this.yf - this.yi;
        for (int n15 = 0; n15 < this.NbPts; ++n15) {
            this.TrajectoryX[n15] = Math.round(this.xi);
            this.TrajectoryY[n15] = (int)Math.round(this.yi + Math.sin(6.283185307179586 * n15 / (this.NbPts - 1)) * n14);
        }
    }
    
    public void automat() {
        if (this.state == m_item.RETRACTED) {
            if (this.active) {
                this.state = m_item.FPROGRESS;
            }
        }
        else if (this.state == m_item.FPROGRESS) {
            if (!this.active) {
                this.state = m_item.BPROGRESS;
                return;
            }
            if (this.InternalCnt < this.NbPts) {
                this.x = this.TrajectoryX[this.InternalCnt];
                this.y = this.TrajectoryY[this.InternalCnt];
                ++this.InternalCnt;
                return;
            }
            this.state = m_item.ADVANCED;
        }
        else if (this.state == m_item.ADVANCED) {
            if (!this.active) {
                this.state = m_item.BPROGRESS;
            }
        }
        else if (this.state == m_item.BPROGRESS) {
            if (!this.active) {
                if (this.InternalCnt > 0) {
                    --this.InternalCnt;
                    this.x = this.TrajectoryX[this.InternalCnt];
                    this.y = this.TrajectoryY[this.InternalCnt];
                    return;
                }
                this.state = m_item.RETRACTED;
            }
            else {
                this.state = m_item.FPROGRESS;
            }
        }
    }
    
    public int getX() {
        if (this.align == m_item.RIGHT) {
            return this.x - this.getW();
        }
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public Image getIm0() {
        return this.Im0;
    }
    
    public Image getIm1() {
        return this.Im1;
    }
    
    public Image getIm() {
        if (this.active) {
            return this.Im1;
        }
        return this.Im0;
    }
    
    public int getW() {
        if (this.active) {
            return this.w1;
        }
        return this.w0;
    }
    
    public int getH() {
        if (this.active) {
            return this.h1;
        }
        return this.h0;
    }
    
    public boolean getActivity() {
        return this.active;
    }
    
    public String getTargetPage() {
        return this.TargetPage;
    }
    
    public String getTargetFrame() {
        return this.TargetFrame;
    }
    
    public void setActive() {
        this.active = true;
    }
    
    public void setInActive() {
        this.active = false;
    }
    
    public void setSize(final int w0, final int h0, final int w2, final int h2) {
        this.w0 = w0;
        this.w1 = w2;
        this.h0 = h0;
        this.h1 = h2;
    }
    
    static {
        m_item.FPROGRESS = 1;
        m_item.ADVANCED = 2;
        m_item.BPROGRESS = 3;
        m_item.RIGHT = 1;
        m_item.SQRT = 1;
        m_item.PARABOLIC = 2;
        m_item.EXPONENTIAL = 3;
        m_item.LOG = 4;
        m_item.PERIODIC = 5;
    }
}
