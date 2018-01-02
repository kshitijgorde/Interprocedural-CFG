import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.AWTEventMulticaster;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class SubMenu extends Panel
{
    protected String actType;
    protected ActionListener Listener;
    protected boolean BoolParam5;
    protected boolean BoolParam4;
    private Image ImgParam1;
    private Image ImgParam2;
    private Image ImgParam3;
    private Image ImgParam4;
    private Image ImgParam5;
    private Image ImgParam6;
    private Image ImgParam7;
    private Image ImgParam8;
    private Image ImgParam9;
    private Image ImgParam10;
    private Image ImgParam11;
    private Image ImgParam12;
    private Image ImgParam13;
    private Image ImgParam14;
    private float FlParam1;
    private AudioClip SndParam2;
    private AudioClip SndParam1;
    private boolean BoolParam3;
    private boolean BoolParam2;
    private boolean BoolParam1;
    private int IntParam20;
    private int IntParam19;
    private int IntParam18;
    private int IntParam17;
    private int IntParam16;
    private int IntParam15;
    private int IntParam14;
    private int IntParam13;
    private int IntParam12;
    private int IntParam11;
    private int IntParam10;
    private int IntParam9;
    private int IntParam8;
    private int IntParam7;
    private int IntParam6;
    private int IntParam5;
    private int IntParam4;
    private int IntParam3;
    private int IntParam2;
    private int IntParam1;
    private Color crParam10;
    private Color crParam9;
    private Color crParam8;
    private Color crParam7;
    private Color crParam6;
    private Color crParam5;
    private Color crParam4;
    private Color crParam3;
    private Color crParam2;
    private Color crParam1;
    private String strParam3;
    private String strParam1;
    private String strParam4;
    private String strParam2;
    public static final int LEFT = 0;
    public static final int TOP = 1;
    public static final int RIGHT = 2;
    public static final int BOTTOM = 3;
    public static final int CENTER = 4;
    
    public SubMenu(final Image image) {
        this(image, null);
    }
    
    public SubMenu(final String s) {
        this(null, s);
    }
    
    public SubMenu(final Image imgParam1, final String strParam4) {
        this.Listener = null;
        this.BoolParam5 = false;
        this.BoolParam4 = false;
        this.IntParam18 = 2;
        this.FlParam1 = 1.0f;
        this.BoolParam3 = true;
        this.BoolParam2 = true;
        this.IntParam20 = 2;
        this.BoolParam1 = false;
        this.IntParam17 = 2;
        this.IntParam16 = 2;
        this.IntParam15 = 0;
        this.IntParam14 = 0;
        this.IntParam13 = 0;
        this.IntParam12 = 0;
        this.IntParam11 = 0;
        this.IntParam10 = 0;
        this.IntParam9 = 0;
        this.IntParam8 = 0;
        this.IntParam7 = 0;
        this.IntParam6 = 0;
        this.IntParam5 = 0;
        this.IntParam4 = 0;
        this.IntParam3 = 0;
        this.IntParam2 = 0;
        this.IntParam1 = 0;
        this.addMouseListener(new Trace(this));
        this.actType = "void";
        this.strParam4 = strParam4;
        this.setLayout(null);
        this.ImgParam1 = imgParam1;
        this.ImgParam2 = this.ImgParam1;
        this.resize();
    }
    
    public synchronized void addNotify() {
        this.resize();
        super.addNotify();
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.resize();
    }
    
    public void SetActiveCr(final Color crParam10, final Color crParam11, final Color crParam12, final Color crParam13, final Color crParam14, final Color crParam15, final Color crParam16, final Color crParam17, final Color crParam18) {
        this.crParam10 = crParam10;
        this.crParam9 = crParam11;
        this.crParam8 = crParam12;
        this.crParam7 = crParam13;
        this.crParam6 = crParam14;
        this.crParam5 = crParam15;
        this.crParam4 = crParam16;
        this.crParam3 = crParam17;
        this.crParam2 = crParam18;
        this.crParam1 = this.crParam4;
        this.setForeground(this.crParam7);
        this.setBackground(this.crParam10);
    }
    
    public void SetShadow(final int intParam14, final int intParam15) {
        this.IntParam14 = intParam14;
        this.IntParam13 = intParam15;
    }
    
    public void SetItemID(final int intParam19) {
        this.IntParam19 = intParam19;
    }
    
    public int GetItemID() {
        return this.IntParam19;
    }
    
    public void SetBordParam(final int intParam18) {
        if (intParam18 > 0) {
            this.BoolParam3 = true;
        }
        else {
            this.BoolParam3 = false;
        }
        this.IntParam18 = intParam18;
        this.resize();
    }
    
    public void SetItemStatusNote(final boolean boolParam2) {
        this.BoolParam2 = boolParam2;
    }
    
    public int GetTxtPos() {
        return this.IntParam20;
    }
    
    public void SetTxtPos(final int intParam20) {
        if (intParam20 != 0 && intParam20 != 1 && intParam20 != 2 && intParam20 != 3) {
            throw new IllegalArgumentException();
        }
        this.IntParam20 = intParam20;
        this.resize();
    }
    
    public void SetTitleCentered(final boolean boolParam1) {
        this.BoolParam1 = boolParam1;
        this.resize();
    }
    
    public String GetItemData() {
        return this.strParam4;
    }
    
    public void SetItemData(final String strParam4) {
        this.strParam4 = strParam4;
        this.resize();
        this.repaint();
    }
    
    public void SetSounds(final AudioClip sndParam2, final AudioClip sndParam3) {
        this.SndParam2 = sndParam2;
        this.SndParam1 = sndParam3;
    }
    
    public void SetPDAs(final int intParam16) {
        this.IntParam16 = intParam16;
        this.resize();
        this.repaint();
    }
    
    public int GetPDAs() {
        return this.IntParam16;
    }
    
    public void SetImgGP(final int intParam15) {
        this.IntParam15 = intParam15;
        this.resize();
        this.repaint();
    }
    
    public int GetImgGP() {
        return this.IntParam15;
    }
    
    public void SetImgActives(final Image imgParam1, final Image imgParam2, final Image imgParam3) {
        this.ImgParam1 = imgParam1;
        this.ImgParam3 = imgParam2;
        this.ImgParam4 = imgParam3;
        this.SetImgs(this.ImgParam2 = this.ImgParam1);
    }
    
    public void SetImgBK(final Image imgParam6, final Image imgParam7, final Image imgParam8) {
        this.ImgParam6 = imgParam6;
        this.ImgParam7 = imgParam7;
        this.ImgParam8 = imgParam8;
        this.SetImgsBKTo(this.ImgParam5 = this.ImgParam6);
    }
    
    public void SetImgs(final Image imgParam2) {
        final Image imgParam3 = this.ImgParam2;
        this.ImgParam2 = imgParam2;
        if (imgParam2 == null || imgParam3 == null || imgParam2.getWidth(this) != this.ImgParam2.getWidth(this) || imgParam2.getHeight(this) != this.ImgParam2.getHeight(this)) {
            this.resize();
        }
        this.ReDrawAll();
    }
    
    public void SetImgsBKTo(final Image imgParam5) {
        final Image imgParam6 = this.ImgParam5;
        this.ImgParam5 = imgParam5;
        if (imgParam5 == null || imgParam6 == null || imgParam5.getWidth(this) != this.ImgParam5.getWidth(this) || imgParam5.getHeight(this) != this.ImgParam5.getHeight(this)) {
            this.resize();
        }
        this.ReDrawAll();
    }
    
    public void Fun33(final Image imgParam11) {
        final Image imgParam12 = this.ImgParam11;
        this.ImgParam11 = imgParam11;
        if (imgParam11 == null || imgParam12 == null || imgParam11.getWidth(this) != this.ImgParam11.getWidth(this) || imgParam11.getHeight(this) != this.ImgParam11.getHeight(this)) {
            this.resize();
        }
        this.ReDrawAll();
    }
    
    public void Fun32(final Image imgParam12, final Image imgParam13, final Image imgParam14, final int intParam17) {
        this.ImgParam12 = imgParam12;
        this.ImgParam13 = imgParam13;
        this.ImgParam14 = imgParam14;
        this.ImgParam11 = this.ImgParam12;
        if (intParam17 != 0 && intParam17 != 2) {
            throw new IllegalArgumentException();
        }
        this.IntParam17 = intParam17;
        this.resize();
    }
    
    public void Fun31(final double n) {
        this.Fun31((float)n);
    }
    
    public void Fun31(float flParam1) {
        if (flParam1 <= 0.0f) {
            flParam1 = 1.0f;
        }
        this.FlParam1 = flParam1;
        this.resize();
    }
    
    public void ActivateIt() {
        if (!this.isEnabled()) {
            this.BoolParam5 = false;
            super.setEnabled(true);
            this.repaint();
        }
    }
    
    public void DeActivateIt() {
        if (this.isEnabled()) {
            this.BoolParam5 = false;
            this.repaint();
        }
    }
    
    public void resize() {
        final Font font = this.getFont();
        FontMetrics fontMetrics = null;
        int n = 0;
        int n2 = 0;
        final int n3 = 1;
        final String[] array = new String[n3];
        final int[] array2 = new int[n3];
        int n4 = 0;
        if (font != null && this.strParam4 != null) {
            fontMetrics = this.getFontMetrics(font);
            final int maxDescent = fontMetrics.getMaxDescent();
            final int maxAscent = fontMetrics.getMaxAscent();
            final int leading = fontMetrics.getLeading();
            final int n5 = maxAscent + maxDescent + leading;
            final StringTokenizer stringTokenizer = new StringTokenizer(this.strParam4, "\\");
            final int countTokens = stringTokenizer.countTokens();
            final String[] array3 = new String[countTokens];
            final int[] array4 = new int[countTokens];
            for (int i = 0; i < countTokens; ++i) {
                array3[i] = stringTokenizer.nextToken();
                array4[i] = fontMetrics.stringWidth(array3[i]);
                if (array4[i] > n4) {
                    n4 = array4[i];
                }
            }
            n = n4 + Math.abs(this.IntParam14);
            n2 = n5 * countTokens - leading + Math.abs(this.IntParam13);
        }
        int n6 = 0;
        int n7 = 0;
        int intParam15 = 0;
        if (this.ImgParam2 != null) {
            n6 = (int)(this.ImgParam2.getWidth(this) * this.FlParam1);
            n7 = (int)(this.ImgParam2.getHeight(this) * this.FlParam1);
            intParam15 = this.IntParam15;
        }
        int width = 0;
        int height = 0;
        int intParam16 = 0;
        if (this.ImgParam11 != null) {
            width = this.ImgParam11.getWidth(this);
            height = this.ImgParam11.getHeight(this);
            intParam16 = this.IntParam15;
        }
        int n8 = n6 + width + 2 * (this.IntParam16 + this.IntParam18);
        int n9 = Math.max(n7, height) + 2 * (this.IntParam16 + this.IntParam18);
        if (fontMetrics != null) {
            if (this.IntParam20 == 0 || this.IntParam20 == 2) {
                n8 += intParam15 + intParam16 + n;
                n9 = Math.max(n9, n2 + 2 * (this.IntParam16 + this.IntParam18));
            }
            else {
                n9 += intParam15 + n2;
                n8 = Math.max(n8, n + 2 * (this.IntParam16 + this.IntParam18));
            }
        }
        if (this.ImgParam5 != null) {
            final int n10 = (int)(this.ImgParam5.getWidth(this) * this.FlParam1);
            n9 = Math.max(n9, (int)(this.ImgParam5.getHeight(this) * this.FlParam1));
            n8 = Math.max(n8, n10);
        }
        this.setSize(n8, n9);
    }
    
    protected void ReDrawItems() {
        this.paint(this.getGraphics());
    }
    
    protected void ReDrawAll() {
        if (this.ImgParam5 != null) {
            final Graphics graphics = this.getGraphics();
            if (graphics == null) {
                return;
            }
            graphics.drawImage(this.ImgParam5, this.IntParam4, this.IntParam3, this);
        }
        if (this.ImgParam2 != null) {
            final Graphics graphics2 = this.getGraphics();
            if (graphics2 == null) {
                return;
            }
            if (this.FlParam1 == 1.0f) {
                graphics2.drawImage(this.isEnabled() ? this.ImgParam2 : this.ImgParam9, this.IntParam12, this.IntParam11, this);
            }
            else {
                graphics2.drawImage(this.isEnabled() ? this.ImgParam2 : this.ImgParam9, this.IntParam12, this.IntParam11, this.IntParam10, this.IntParam9, this);
            }
        }
        if (this.ImgParam11 != null) {
            final Graphics graphics3 = this.getGraphics();
            if (graphics3 == null) {
                return;
            }
            graphics3.drawImage(this.ImgParam11, this.IntParam8, this.IntParam7, this);
        }
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (!this.isEnabled() && this.ImgParam9 == null) {
            this.ImgParam9 = this.ImgParam2;
        }
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        graphics.clearRect(0, 0, width, height);
        if (this.BoolParam3) {
            final SubMenuII subMenuII = new SubMenuII(this, 0, 0, width, height, this.IntParam18);
            if (this.BoolParam5 && this.BoolParam2) {
                subMenuII.SetType(0);
            }
            else {
                subMenuII.SetType(1);
            }
            subMenuII.paint(graphics);
        }
        this.IntParam4 = 0;
        this.IntParam3 = 0;
        this.IntParam2 = 0;
        this.IntParam1 = 0;
        if (this.ImgParam5 != null) {
            this.IntParam2 = (int)(this.ImgParam5.getWidth(this) * this.FlParam1);
            this.IntParam1 = (int)(this.ImgParam5.getHeight(this) * this.FlParam1);
            this.IntParam4 = (width - this.IntParam2) / 2;
            this.IntParam3 = (height - this.IntParam1) / 2;
        }
        this.IntParam10 = 0;
        this.IntParam9 = 0;
        int intParam15 = 0;
        if (this.ImgParam2 != null) {
            this.IntParam10 = (int)(this.ImgParam2.getWidth(this) * this.FlParam1);
            this.IntParam9 = (int)(this.ImgParam2.getHeight(this) * this.FlParam1);
            if (this.strParam4 != null) {
                intParam15 = this.IntParam15;
            }
        }
        this.IntParam6 = 0;
        this.IntParam5 = 0;
        int intParam16 = 0;
        if (this.ImgParam11 != null) {
            this.IntParam6 = this.ImgParam11.getWidth(this);
            this.IntParam5 = this.ImgParam11.getHeight(this);
            intParam16 = this.IntParam15;
        }
        int maxAscent = 0;
        int n = 0;
        int n2 = 0;
        int countTokens = 1;
        String[] array = new String[countTokens];
        int[] array2 = new int[countTokens];
        int n3 = 0;
        int n4 = 0;
        if (this.strParam4 != null) {
            final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
            final int maxDescent = fontMetrics.getMaxDescent();
            maxAscent = fontMetrics.getMaxAscent();
            final int leading = fontMetrics.getLeading();
            n4 = maxAscent + maxDescent + leading;
            final StringTokenizer stringTokenizer = new StringTokenizer(this.strParam4, "\\");
            countTokens = stringTokenizer.countTokens();
            array = new String[countTokens];
            array2 = new int[countTokens];
            for (int i = 0; i < countTokens; ++i) {
                array[i] = stringTokenizer.nextToken();
                array2[i] = fontMetrics.stringWidth(array[i]);
                if (array2[i] > n3) {
                    n3 = array2[i];
                }
            }
            n = n3 + Math.abs(this.IntParam14);
            n2 = n4 * countTokens - leading + Math.abs(this.IntParam13);
        }
        final int n5 = this.IntParam16 + this.IntParam18 + ((this.BoolParam5 && this.BoolParam2) ? 1 : 0);
        final int n6 = this.IntParam16 + this.IntParam18 - ((this.BoolParam5 && this.BoolParam2) ? 1 : 0);
        int n7;
        if (this.IntParam17 == 2) {
            final int intParam17 = (width + n5 - n6 - this.IntParam6 - intParam16 - this.IntParam10 - intParam15 - n) / 2;
            if (this.IntParam20 == 2) {
                if (this.BoolParam1) {
                    this.IntParam12 = intParam17;
                }
                else {
                    this.IntParam12 = n5;
                }
                n7 = this.IntParam12 + this.IntParam10 + intParam15;
            }
            else if (this.IntParam20 == 0) {
                if (this.BoolParam1) {
                    n7 = intParam17;
                }
                else {
                    n7 = width - n5 - this.IntParam6 - intParam16 - this.IntParam10 - intParam15 - n;
                }
                this.IntParam12 = n7 + n + intParam15;
            }
            else {
                this.IntParam12 = (width + n5 - n6 - this.IntParam6 - intParam16 - this.IntParam10) / 2;
                n7 = (width + n5 - n6 - this.IntParam6 - intParam16 - n) / 2;
            }
        }
        else {
            final int intParam18 = (width + n5 - n6 + this.IntParam6 + intParam16 - this.IntParam10 - intParam15 - n) / 2;
            if (this.IntParam20 == 2) {
                if (this.BoolParam1) {
                    this.IntParam12 = intParam18;
                }
                else {
                    this.IntParam12 = n5 + this.IntParam6 + intParam16;
                }
                n7 = this.IntParam12 + this.IntParam10 + intParam15;
            }
            else if (this.IntParam20 == 0) {
                if (this.BoolParam1) {
                    n7 = intParam18;
                }
                else {
                    n7 = width - n6 - this.IntParam10 - intParam15 - n;
                }
                this.IntParam12 = n7 + n + intParam15;
            }
            else {
                this.IntParam12 = (width + n5 - n6 + this.IntParam6 + intParam16 - this.IntParam10) / 2;
                n7 = (width + n5 - n6 + this.IntParam6 + intParam16 - n) / 2;
            }
        }
        int n8;
        if (this.IntParam20 == 1) {
            n8 = (height + n5 - n6 - n2 - intParam15 - this.IntParam9) / 2 + maxAscent;
            this.IntParam11 = n8 - maxAscent + n2 + intParam15;
        }
        else if (this.IntParam20 == 3) {
            this.IntParam11 = (height + n5 - n6 - n2 - intParam15 - this.IntParam9) / 2;
            n8 = this.IntParam11 + this.IntParam9 + intParam15 + maxAscent;
        }
        else {
            this.IntParam11 = (height + n5 - n6 - this.IntParam9) / 2;
            n8 = (height + n5 - n6 - n2) / 2 + maxAscent;
        }
        this.IntParam7 = (height + n5 - n6 - this.IntParam5) / 2;
        if (this.IntParam17 == 2) {
            this.IntParam8 = width - n6 - this.IntParam6;
        }
        else {
            this.IntParam8 = n5;
        }
        this.ReDrawAll();
        if (this.strParam4 != null) {
            int n9;
            int n10;
            if (this.IntParam14 < 0) {
                n9 = n7 - this.IntParam14;
                n10 = n7;
            }
            else {
                n9 = n7;
                n10 = n7 + this.IntParam14;
            }
            int n11;
            int n12;
            if (this.IntParam13 < 0) {
                n11 = n8 - this.IntParam13;
                n12 = n8;
            }
            else {
                n11 = n8;
                n12 = n8 + this.IntParam13;
            }
            for (int j = 0; j < countTokens; ++j) {
                final int n13 = n12 + n4 * j;
                final int n14 = n11 + n4 * j;
                int n15;
                int n16;
                if (this.IntParam20 == 2) {
                    n15 = n10;
                    n16 = n9;
                }
                else if (this.IntParam20 == 0) {
                    n15 = n10 + n - array2[j];
                    n16 = n9 + n - array2[j];
                }
                else {
                    n15 = n10 + (n - array2[j]) / 2;
                    n16 = n9 + (n - array2[j]) / 2;
                }
                graphics.setColor(this.crParam1);
                graphics.drawString(array[j], n15, n13);
                graphics.setColor(this.getForeground());
                graphics.drawString(array[j], n16, n14);
            }
        }
    }
    
    public void PutActType(final String actType) {
        this.actType = actType;
    }
    
    public String RetActType() {
        return this.actType;
    }
    
    public void addActionListener(final ActionListener actionListener) {
        this.Listener = AWTEventMulticaster.add(this.Listener, actionListener);
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        this.Listener = AWTEventMulticaster.remove(this.Listener, actionListener);
    }
    
    protected void fireActionEvent(final int n) {
        if (this.Listener != null) {
            this.Listener.actionPerformed(new ActionEvent(this, n, this.actType));
        }
    }
    
    protected void SubMenu_MousePressed(final MouseEvent mouseEvent) {
        this.BoolParam5 = true;
        if (this.SndParam2 != null) {
            this.SndParam2.play();
        }
        this.setForeground(this.crParam5);
        this.setBackground(this.crParam8);
        this.crParam1 = this.crParam2;
        if (this.ImgParam7 != null) {
            this.SetImgsBKTo(this.ImgParam7);
        }
        if (this.ImgParam3 != null) {
            this.SetImgs(this.ImgParam3);
        }
        if (this.ImgParam14 != null) {
            this.Fun33(this.ImgParam14);
        }
        this.ReDrawItems();
        this.fireActionEvent(501);
    }
    
    protected void SubMenu_MouseReleased(final MouseEvent mouseEvent) {
        this.BoolParam5 = false;
        if (this.BoolParam4) {
            this.setForeground(this.crParam6);
            this.setBackground(this.crParam9);
            this.crParam1 = this.crParam3;
            if (this.ImgParam8 != null) {
                this.SetImgsBKTo(this.ImgParam8);
            }
            if (this.ImgParam4 != null) {
                this.SetImgs(this.ImgParam4);
            }
            if (this.ImgParam13 != null) {
                this.Fun33(this.ImgParam13);
            }
            this.ReDrawItems();
            this.fireActionEvent(500);
        }
        else {
            this.setForeground(this.crParam7);
            this.setBackground(this.crParam10);
            this.crParam1 = this.crParam4;
            if (this.ImgParam6 != null) {
                this.SetImgsBKTo(this.ImgParam6);
            }
            if (this.ImgParam1 != null) {
                this.SetImgs(this.ImgParam1);
            }
            if (this.ImgParam12 != null) {
                this.Fun33(this.ImgParam12);
            }
            this.ReDrawItems();
            this.fireActionEvent(502);
        }
    }
    
    protected void SubMenu_MouseEntered(final MouseEvent mouseEvent) {
        this.BoolParam4 = true;
        if (this.BoolParam5) {
            this.setForeground(this.crParam5);
            this.setBackground(this.crParam8);
            this.crParam1 = this.crParam2;
            if (this.ImgParam7 != null) {
                this.SetImgsBKTo(this.ImgParam7);
            }
            if (this.ImgParam3 != null) {
                this.SetImgs(this.ImgParam3);
            }
            if (this.ImgParam14 != null) {
                this.Fun33(this.ImgParam14);
            }
        }
        else {
            if (this.SndParam1 != null) {
                this.SndParam1.play();
            }
            this.setForeground(this.crParam6);
            this.setBackground(this.crParam9);
            this.crParam1 = this.crParam3;
            if (this.ImgParam8 != null) {
                this.SetImgsBKTo(this.ImgParam8);
            }
            if (this.ImgParam4 != null) {
                this.SetImgs(this.ImgParam4);
            }
            if (this.ImgParam13 != null) {
                this.Fun33(this.ImgParam13);
            }
        }
        this.ReDrawItems();
        this.fireActionEvent(504);
    }
    
    protected void SubMenu_MouseExited(final MouseEvent mouseEvent) {
        this.BoolParam4 = false;
        this.BoolParam5 = false;
        if (this.BoolParam5) {
            this.setForeground(this.crParam5);
            this.setBackground(this.crParam8);
            this.crParam1 = this.crParam2;
            if (this.ImgParam7 != null) {
                this.SetImgsBKTo(this.ImgParam7);
            }
            if (this.ImgParam3 != null) {
                this.SetImgs(this.ImgParam3);
            }
            if (this.ImgParam14 != null) {
                this.Fun33(this.ImgParam14);
            }
        }
        else {
            this.setForeground(this.crParam7);
            this.setBackground(this.crParam10);
            this.crParam1 = this.crParam4;
            if (this.ImgParam6 != null) {
                this.SetImgsBKTo(this.ImgParam6);
            }
            if (this.ImgParam1 != null) {
                this.SetImgs(this.ImgParam1);
            }
            if (this.ImgParam12 != null) {
                this.Fun33(this.ImgParam12);
            }
        }
        this.ReDrawItems();
        this.fireActionEvent(505);
    }
    
    class Trace extends MouseAdapter
    {
        private final SubMenu ParentParam;
        
        public void mouseExited(final MouseEvent mouseEvent) {
            this.ParentParam.SubMenu_MouseExited(mouseEvent);
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
            this.ParentParam.SubMenu_MouseEntered(mouseEvent);
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            this.ParentParam.SubMenu_MouseReleased(mouseEvent);
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            this.ParentParam.SubMenu_MousePressed(mouseEvent);
        }
        
        Trace(final SubMenu parentParam) {
            this.ParentParam = parentParam;
        }
    }
}
