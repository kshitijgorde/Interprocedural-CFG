import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class javahead extends Applet implements Runnable
{
    private Thread m_javahead;
    private int m_nMainImageWidth;
    private int m_nMainImageHeight;
    private int m_nMainImageMemorySize;
    public Image m_MainImage;
    public MemoryImageSource m_MemoryImage;
    public byte[] m_DrawPixels;
    public cheadconsumer chc;
    private float m_f1;
    private float m_f2;
    private float m_f1s;
    private float m_f2s;
    private float m_rvBlinkPos;
    private float m_rvBlinkStep;
    private int m_dwLastBlinkTime;
    private float m_rvThetaXFinal;
    private float m_rvThetaYFinal;
    private float m_rvThetaXCurrent;
    private float m_rvThetaYCurrent;
    private float m_rvThetaXStep;
    private float m_rvThetaYStep;
    private int m_nCountStep;
    private boolean m_bBlink;
    private boolean m_bDrawing;
    private int m_nSleepTime;
    private cheadcasedata m_headcasedata;
    private String m_HeadcaseFaceFile;
    private String m_AudioFile;
    private boolean m_AutomaticTick;
    private int m_BackgroundColorIndex;
    private boolean m_HeadMovement;
    private int m_HeadRotationLeftRight;
    private int m_HeadRotationUpDown;
    private int m_MouthOpen;
    private int m_TalkingLevel;
    private boolean m_TalkingMode;
    private int m_FramesPerSecond;
    private String m_Padding2;
    private String m_Padding3;
    private final String PARAM_HeadcaseFaceFile = "HeadcaseFaceFile";
    private final String PARAM_AudioFile = "AudioFile";
    private final String PARAM_AutomaticTick = "AutomaticTick";
    private final String PARAM_BackgroundColorIndex = "BackgroundColorIndex";
    private final String PARAM_HeadMovement = "HeadMovement";
    private final String PARAM_HeadRotationLeftRight = "HeadRotationLeftRight";
    private final String PARAM_HeadRotationUpDown = "HeadRotationUpDown";
    private final String PARAM_MouthOpen = "MouthOpen";
    private final String PARAM_TalkingLevel = "TalkingLevel";
    private final String PARAM_TalkingMode = "TalkingMode";
    private final String PARAM_FramesPerSecond = "FramesPerSecond";
    private final String PARAM_Padding2 = "Padding2";
    private final String PARAM_Padding3 = "Padding3";
    
    public void stop() {
        if (this.m_javahead != null) {
            this.m_javahead.stop();
            this.m_javahead = null;
        }
    }
    
    public javahead() {
        this.m_rvBlinkStep = 0.5f;
        this.m_HeadcaseFaceFile = "";
        this.m_AudioFile = "";
        this.m_AutomaticTick = true;
        this.m_HeadMovement = true;
        this.m_TalkingLevel = 22000;
        this.m_TalkingMode = true;
        this.m_FramesPerSecond = 25;
        this.m_Padding2 = "";
        this.m_Padding3 = "";
        this.m_f1 = 0.0f;
        this.m_f2 = 0.0f;
        this.m_f1s = 0.1f;
        this.m_f2s = 0.1f;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.m_MainImage, 0, 0, this.m_nMainImageWidth, this.m_nMainImageHeight, null);
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "HeadcaseFaceFile", "String", "Headcase Face File To Load" }, { "AudioFile", "String", "Audio File To Play" }, { "AutomaticTick", "boolean", "True if Face should redraw automatically" }, { "BackgroundColorIndex", "int", "Background Color Index (0-255)" }, { "HeadMovement", "boolean", "True if Head Movement is automatic" }, { "HeadRotationLeftRight", "int", "Head Rotation Left Right" }, { "HeadRotationUpDown", "int", "Head Rotation Up Down" }, { "MouthOpen", "int", "Percentage Mouth Open" }, { "TalkingLevel", "int", "Talking Level Threshold" }, { "TalkingMode", "boolean", "True if auto volume detection" }, { "FramesPerSecond", "int", "Frames Per Second" }, { "Padding2", "String", "Padding 2" }, { "Padding3", "String", "Padding 3" } };
    }
    
    public void destroy() {
    }
    
    void SetFaceExpression(final int n, float n2) {
        if (n2 > 1.0f) {
            n2 = 1.0f;
        }
        for (int i = 0; i < this.m_headcasedata.m_pArrayExpression[n].m_nNumberOfPoints; ++i) {
            this.m_headcasedata.m_LiveExpression.m_p3DPoints[this.m_headcasedata.m_pArrayExpression[n].m_p3DPoints[i].index].x += (int)(this.m_headcasedata.m_pArrayExpression[n].m_p3DPoints[i].x * n2);
            this.m_headcasedata.m_LiveExpression.m_p3DPoints[this.m_headcasedata.m_pArrayExpression[n].m_p3DPoints[i].index].y += (int)(this.m_headcasedata.m_pArrayExpression[n].m_p3DPoints[i].y * n2);
            this.m_headcasedata.m_LiveExpression.m_p3DPoints[this.m_headcasedata.m_pArrayExpression[n].m_p3DPoints[i].index].z += (int)(this.m_headcasedata.m_pArrayExpression[n].m_p3DPoints[i].z * n2);
        }
    }
    
    public void xxx() {
        this.m_bDrawing = false;
        this.m_MemoryImage.removeConsumer(this.chc);
        this.repaint();
    }
    
    void Tick() {
        if (this.m_HeadMovement) {
            if (this.m_nCountStep == 0) {
                this.m_rvThetaXFinal = (float)Math.random() - 0.5f;
                this.m_rvThetaYFinal = (float)(Math.random() * 0.6000000238418579) - 0.3f;
                this.m_rvThetaXStep = (this.m_rvThetaXFinal - this.m_rvThetaXCurrent) / 20.0f;
                this.m_rvThetaYStep = (this.m_rvThetaYFinal - this.m_rvThetaYCurrent) / 20.0f;
                this.m_nCountStep = 20;
            }
            this.m_rvThetaXCurrent += this.m_rvThetaXStep;
            this.m_rvThetaYCurrent += this.m_rvThetaYStep;
            --this.m_nCountStep;
        }
        for (int i = this.m_nMainImageMemorySize; i > 0; this.m_headcasedata.m_MainPixels[--i] = (byte)this.m_BackgroundColorIndex) {}
        this.m_headcasedata.ResetLiveExpression();
        if (this.m_dwLastBlinkTime < 0) {
            this.m_bBlink = true;
        }
        if (this.m_bBlink) {
            this.SetFaceExpression(3, this.m_rvBlinkPos);
            this.m_rvBlinkPos += this.m_rvBlinkStep;
            if (this.m_rvBlinkPos > 1.0) {
                this.m_rvBlinkStep = -0.2f;
            }
            if (this.m_rvBlinkPos < 0.0) {
                this.m_bBlink = false;
                this.m_rvBlinkPos = 0.0f;
                this.m_rvBlinkStep = 0.5f;
                this.m_dwLastBlinkTime = 150;
            }
        }
        --this.m_dwLastBlinkTime;
        for (int j = 0; j < this.m_headcasedata.m_nNumberOfPointsForBase; ++j) {
            final float n = this.m_headcasedata.m_LiveExpression.m_p3DPoints[j].x;
            final float n2 = this.m_headcasedata.m_LiveExpression.m_p3DPoints[j].y;
            final float n3 = this.m_headcasedata.m_LiveExpression.m_p3DPoints[j].z;
            this.m_headcasedata.m_LiveExpression.m_p3DPoints[j].x = (int)(n * Math.cos(this.m_rvThetaXCurrent) - n3 * Math.sin(this.m_rvThetaXCurrent));
            final float n4 = (float)(n * Math.sin(this.m_rvThetaXCurrent) + n3 * Math.cos(this.m_rvThetaXCurrent));
            this.m_headcasedata.m_LiveExpression.m_p3DPoints[j].y = (int)(n2 * Math.cos(this.m_rvThetaYCurrent) - n4 * Math.sin(this.m_rvThetaYCurrent));
            this.m_headcasedata.m_LiveExpression.m_p3DPoints[j].z = (int)(n2 * Math.sin(this.m_rvThetaYCurrent) + n4 * Math.cos(this.m_rvThetaYCurrent));
        }
        for (int k = 0; k < this.m_headcasedata.m_nNumberOfTrianglesForBase; ++k) {
            this.m_headcasedata.m_vTriangles[k].v[0].sx = this.m_headcasedata.m_LiveExpression.m_p3DPoints[this.m_headcasedata.pTriangles[k].x].x;
            this.m_headcasedata.m_vTriangles[k].v[0].sy = this.m_headcasedata.m_LiveExpression.m_p3DPoints[this.m_headcasedata.pTriangles[k].x].y;
            this.m_headcasedata.m_vTriangles[k].v[1].sx = this.m_headcasedata.m_LiveExpression.m_p3DPoints[this.m_headcasedata.pTriangles[k].y].x;
            this.m_headcasedata.m_vTriangles[k].v[1].sy = this.m_headcasedata.m_LiveExpression.m_p3DPoints[this.m_headcasedata.pTriangles[k].y].y;
            this.m_headcasedata.m_vTriangles[k].v[2].sx = this.m_headcasedata.m_LiveExpression.m_p3DPoints[this.m_headcasedata.pTriangles[k].z].x;
            this.m_headcasedata.m_vTriangles[k].v[2].sy = this.m_headcasedata.m_LiveExpression.m_p3DPoints[this.m_headcasedata.pTriangles[k].z].y;
        }
        this.m_headcasedata.DrawTriangles();
        System.arraycopy(this.m_headcasedata.m_MainPixels, 0, this.m_DrawPixels, 0, this.m_nMainImageMemorySize);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void start() {
        if (this.m_javahead == null) {
            (this.m_javahead = new Thread(this)).start();
        }
    }
    
    public String getAppletInfo() {
        return "Name: javahead\r\n" + "Author: RED TED\r\n" + "Created with Microsoft Visual J++ Version 1.1";
    }
    
    public void run() {
        this.repaint();
        (this.chc = new cheadconsumer()).setOwner(this);
    Label_0023_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        if (this.m_AutomaticTick) {
                            this.m_bDrawing = true;
                            this.Tick();
                            this.m_MemoryImage = new MemoryImageSource(this.m_nMainImageWidth, this.m_nMainImageHeight, this.m_headcasedata.m_MainIndexColorModel, this.m_DrawPixels, 0, this.m_nMainImageWidth);
                            this.m_MainImage = this.createImage(this.m_MemoryImage);
                        }
                        this.repaint();
                        Thread.sleep(this.m_nSleepTime);
                    }
                }
                catch (InterruptedException ex) {
                    this.stop();
                    continue Label_0023_Outer;
                }
                continue;
            }
        }
    }
    
    public void init() {
        final String parameter = this.getParameter("HeadcaseFaceFile");
        if (parameter != null) {
            this.m_HeadcaseFaceFile = parameter;
        }
        final String parameter2 = this.getParameter("AudioFile");
        if (parameter2 != null) {
            this.m_AudioFile = parameter2;
        }
        final String parameter3 = this.getParameter("AutomaticTick");
        if (parameter3 != null) {
            this.m_AutomaticTick = Boolean.valueOf(parameter3);
        }
        final String parameter4 = this.getParameter("BackgroundColorIndex");
        if (parameter4 != null) {
            this.m_BackgroundColorIndex = Integer.parseInt(parameter4);
        }
        final String parameter5 = this.getParameter("HeadMovement");
        if (parameter5 != null) {
            this.m_HeadMovement = Boolean.valueOf(parameter5);
        }
        final String parameter6 = this.getParameter("HeadRotationLeftRight");
        if (parameter6 != null) {
            this.m_HeadRotationLeftRight = Integer.parseInt(parameter6);
        }
        final String parameter7 = this.getParameter("HeadRotationUpDown");
        if (parameter7 != null) {
            this.m_HeadRotationUpDown = Integer.parseInt(parameter7);
        }
        final String parameter8 = this.getParameter("MouthOpen");
        if (parameter8 != null) {
            this.m_MouthOpen = Integer.parseInt(parameter8);
        }
        final String parameter9 = this.getParameter("TalkingLevel");
        if (parameter9 != null) {
            this.m_TalkingLevel = Integer.parseInt(parameter9);
        }
        final String parameter10 = this.getParameter("TalkingMode");
        if (parameter10 != null) {
            this.m_TalkingMode = Boolean.valueOf(parameter10);
        }
        final String parameter11 = this.getParameter("FramesPerSecond");
        if (parameter11 != null) {
            this.m_FramesPerSecond = Integer.parseInt(parameter11);
        }
        final String parameter12 = this.getParameter("Padding2");
        if (parameter12 != null) {
            this.m_Padding2 = parameter12;
        }
        final String parameter13 = this.getParameter("Padding3");
        if (parameter13 != null) {
            this.m_Padding3 = parameter13;
        }
        this.m_nMainImageWidth = this.size().width;
        this.m_nMainImageHeight = this.size().height;
        this.m_nMainImageMemorySize = this.m_nMainImageWidth * this.m_nMainImageHeight;
        (this.m_headcasedata = new cheadcasedata()).Load(this.m_HeadcaseFaceFile, this.m_nMainImageWidth, this.m_nMainImageHeight);
        this.m_DrawPixels = new byte[this.m_nMainImageMemorySize];
        if (this.m_FramesPerSecond < 0) {
            this.m_FramesPerSecond = 1;
        }
        if (this.m_FramesPerSecond > 1000) {
            this.m_FramesPerSecond = 1000;
        }
        this.m_nSleepTime = 1000 / this.m_FramesPerSecond;
    }
}
