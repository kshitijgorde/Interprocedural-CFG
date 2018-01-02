import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class m_banner
{
    private Image CurrentImage;
    private Image[] ImageS;
    private int w;
    private int h;
    private int NbImages;
    private int x;
    private int y;
    private int Initial;
    private int Final;
    private int Transition;
    private int NbInter;
    private int CurrentInter;
    static int NONE;
    static int DESCENT;
    static int ASCENT;
    static int MORPHING;
    private String TargetPage;
    private String TargetFrame;
    
    m_banner(final Image[] array, final int w, final int h, final String s, final String s2, final String s3, final String s4, final String targetPage, final String targetFrame) {
        this.w = w;
        this.h = h;
        if (s == null) {
            this.x = 0;
        }
        else {
            this.x = Math.abs(Integer.parseInt(s));
        }
        if (s2 == null) {
            this.y = 0;
        }
        else {
            this.y = Math.abs(Integer.parseInt(s2));
        }
        if (s3 == null) {
            this.Transition = m_banner.MORPHING;
        }
        else if (s3.toUpperCase().equals("MORPHING")) {
            this.Transition = m_banner.MORPHING;
        }
        else if (s3.toUpperCase().equals("DESCENT")) {
            this.Transition = m_banner.DESCENT;
        }
        else if (s3.toUpperCase().equals("ASCENT")) {
            this.Transition = m_banner.ASCENT;
        }
        else {
            this.Transition = m_banner.NONE;
        }
        if (s4 == null) {
            this.NbInter = 0;
        }
        else {
            this.NbInter = Math.abs(Integer.parseInt(s4));
        }
        this.TargetPage = targetPage;
        this.TargetFrame = targetFrame;
        this.CurrentInter = 0;
        this.NbImages = array.length;
        this.ImageS = new Image[this.NbImages];
        for (int i = 0; i < this.NbImages; ++i) {
            this.ImageS[i] = array[i];
        }
        this.CurrentImage = this.ImageS[0];
        if (this.NbImages > 1) {
            this.Final = 1;
            this.Initial = 0;
            return;
        }
        this.Final = 0;
        this.Initial = 0;
    }
    
    public void Progress() {
        if (this.Transition != m_banner.DESCENT) {
            this.CurrentImage = this.ImageS[this.Final];
        }
        if (this.CurrentInter < this.NbInter + 1) {
            ++this.CurrentInter;
            return;
        }
        this.CurrentInter = 0;
        if (this.Final < this.NbImages - 1) {
            ++this.Final;
        }
        else {
            this.Final = 0;
        }
        if (this.Initial < this.NbImages - 1) {
            ++this.Initial;
            return;
        }
        this.Initial = 0;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getW() {
        return this.w;
    }
    
    public int getH() {
        return this.h;
    }
    
    public int getFinal() {
        return this.Final;
    }
    
    public Image getCurrentImage() {
        return this.CurrentImage;
    }
    
    public String getTargetPage() {
        return this.TargetPage;
    }
    
    public String getTargetFrame() {
        return this.TargetFrame;
    }
    
    static {
        m_banner.DESCENT = 1;
        m_banner.ASCENT = 2;
        m_banner.MORPHING = 3;
    }
}
