import java.awt.Event;
import java.net.MalformedURLException;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.Dimension;
import java.awt.Graphics;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class EasyButton_v2_3 extends Applet implements Runnable
{
    private Thread thread;
    private final int NIMAGES = 12;
    private int NSTATES;
    private String[] Text;
    private int[] TextX;
    private int[] TextY;
    private int[] ImageX;
    private int[] ImageY;
    private Font[] TextFont;
    private int[][] TextColor;
    private Color[] ShadowColor;
    private int[] ShadowSize;
    private int[] Outline;
    Image BackgroundImage;
    Image[] hImage;
    private int[] BGColor;
    private AudioClip[] Clip;
    private boolean NeedRedraw;
    private String[] Align;
    private String Status;
    private final int EffectsNumber = 10;
    Image[] EffectImage;
    private final int EffectAnimColorsNumber = 4;
    int[][] EffectAnimColors;
    private int UpdateTime;
    private boolean AnimatedColorsExist;
    private Graphics offGraphics;
    private Image offImage;
    private Dimension offDimension;
    private boolean displayRollover;
    private boolean showCounters;
    private long NextWakeUp;
    private Image UserFontImage;
    private int UserFontImageWidth;
    private int UserFontImageHeight;
    private byte[] UserFontData;
    private int UserFontHeight;
    private int UserFontRealHeight;
    private int UserFontOffsetY;
    private int UserFontOffsetX;
    private int UserFontDensity;
    private int[] UserFontOffsets;
    static Applet[] ActiveApplet;
    static int[] UpdateKey;
    int LocalUpdateKey;
    private int AppletGroup;
    URL hrefURL;
    String hrefTarget;
    private int div_width;
    
    private String GetField(final String s, final String s2, final int n) {
        if (s == null) {
            return null;
        }
        int n2 = -1;
        int n3 = 0;
        while (true) {
            final int n4 = n2 + 1;
            n2 = s.indexOf(s2, n4);
            if (n3 == n) {
                if (n2 == -1) {
                    n2 = s.length();
                }
                return s.substring(n4, n2) + "";
            }
            if (n2 == -1) {
                return null;
            }
            ++n3;
        }
    }
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
        this.offGraphics = null;
        this.offImage = null;
    }
    
    boolean shouldUpdate() {
        if (this.LocalUpdateKey == EasyButton_v2_3.UpdateKey[this.AppletGroup]) {
            return false;
        }
        this.LocalUpdateKey = EasyButton_v2_3.UpdateKey[this.AppletGroup];
        return true;
    }
    
    public void DrawLine(final Color color, final int n, final int n2, final int n3, final int n4) {
        this.offGraphics.setColor(color);
        this.offGraphics.drawLine(n, n2, n3, n4);
    }
    
    public void DrawEffect_Tab(final int n, final String s) {
        final int getIntParam = this.GetIntParam(s, 1, 1);
        final Color getColorParam = this.GetColorParam(s, 2, Color.gray, false);
        this.GetColorParam(s, 3, Color.black, false);
        final Color getColorParam2 = this.GetColorParam(s, 4, Color.black, false);
        final Color getColorParam3 = this.GetColorParam(s, 5, Color.gray, false);
        final Color getColorParam4 = this.GetColorParam(s, 6, Color.black, false);
        final Color getColorParam5 = this.GetColorParam(s, 7, Color.black, false);
        final Dimension size = this.size();
        int n2 = getIntParam;
        if (this.isActiveApplet()) {
            n2 = 0;
        }
        if (this.isActiveApplet()) {
            this.DrawEffect_FillRect(n, s, n2);
            this.DrawEffect_Draw3DRect(n, s, true, n2);
        }
        else {
            this.offGraphics.setColor(getColorParam5);
            this.offGraphics.fillRect(0, n2, size.width, size.height);
            for (int i = 0; i < getIntParam; ++i) {
                this.DrawTopLine(getColorParam3, size, 0, i + n2, 0);
                this.DrawBottomLine(getColorParam, size, 0, i + getIntParam, 0);
            }
            this.DrawRightLine(getColorParam4, size, 0, n2, getIntParam * 2);
        }
        for (int j = 0; j < getIntParam; ++j) {
            this.DrawBottomLine(getColorParam2, size, 0, j, 0);
        }
    }
    
    public void DrawLeftLine(final Color color, final Dimension dimension, final int n, final int n2, final int n3) {
        this.offGraphics.setColor(color);
        this.offGraphics.drawLine(n, n2, n, dimension.height - 1 - n3);
    }
    
    public void DrawRightLine(final Color color, final Dimension dimension, final int n, final int n2, final int n3) {
        this.offGraphics.setColor(color);
        this.offGraphics.drawLine(dimension.width - 1 - n, n2, dimension.width - 1 - n, dimension.height - 1 - n3);
    }
    
    public void DrawEffect_Gradient(final int n, final String s) {
        if (this.EffectAnimColors[n] == null) {
            this.EffectAnimColors[n] = this.PrepareAnimColorParam(s, 1, Color.black, null);
        }
        final Color getAnimColor = this.GetAnimColor(this.EffectAnimColors[n]);
        if (this.EffectAnimColors[n + 10] == null) {
            this.EffectAnimColors[n + 10] = this.PrepareAnimColorParam(s, 2, Color.black, null);
        }
        final Color getAnimColor2 = this.GetAnimColor(this.EffectAnimColors[n + 10]);
        final Dimension size = this.size();
        boolean b = false;
        if (this.GetStrParam(s, 3, "horizontal").equalsIgnoreCase("vertical")) {
            b = true;
        }
        boolean b2 = false;
        if (this.GetStrParam(s, 4, "").equalsIgnoreCase("centered")) {
            b2 = true;
        }
        int n2 = b ? size.height : size.width;
        int n3 = 0;
        int n4 = 0;
        if (b2) {
            n2 = n2 / 2 + 1;
            if (b) {
                n4 = size.height / 2;
            }
            else {
                n3 = size.width / 2;
            }
        }
        for (int i = 0; i < n2; ++i) {
            this.offGraphics.setColor(this.BlendColors(getAnimColor, getAnimColor2, (n2 - i) / n2));
            if (!b) {
                this.offGraphics.drawLine(n3 + i, 0, n3 + i, size.height);
            }
            else {
                this.offGraphics.drawLine(0, n4 + i, size.width, n4 + i);
            }
            if (b2) {
                if (!b) {
                    this.offGraphics.drawLine(n3 - i, 0, n3 - i, size.height);
                }
                else {
                    this.offGraphics.drawLine(0, n4 - i, size.width, n4 - i);
                }
            }
        }
    }
    
    int GetCharOffset(int n) {
        n -= 32;
        return this.UserFontOffsets[n / 16 * 17 + n % 16];
    }
    
    void LoadCounters(final boolean b) {
        int n = 0;
        do {
            this.LoadCounter(n, b, false);
        } while (++n < 4);
    }
    
    int GetIntParam(final String s, final int n, final int n2) {
        final String getStrParam = this.GetStrParam(s, n, null);
        if (getStrParam == null) {
            return n2;
        }
        return Integer.parseInt(getStrParam);
    }
    
    double GetDblParam(final String s, final int n, final double n2) {
        final String getStrParam = this.GetStrParam(s, n, null);
        if (getStrParam == null) {
            return n2;
        }
        return Double.valueOf(getStrParam);
    }
    
    public void DrawEffect_Draw3DRect(final int n, final String s, final boolean b, final int n2) {
        final int getIntParam = this.GetIntParam(s, 1, 1);
        final Color getColorParam = this.GetColorParam(s, 2, Color.gray, false);
        final Color getColorParam2 = this.GetColorParam(s, 3, Color.black, false);
        final Dimension size = this.size();
        if (b) {
            final Dimension dimension = size;
            dimension.height += getIntParam;
        }
        for (int i = 0; i < getIntParam; ++i) {
            this.DrawTopLine(getColorParam, size, i, n2 + i, i);
            this.DrawLeftLine(getColorParam, size, i, n2 + i, i);
            this.DrawBottomLine(getColorParam2, size, i, i, i);
            this.DrawRightLine(getColorParam2, size, i, n2 + i, i);
        }
    }
    
    Image LoadImage(final String s) {
        Image image = null;
        try {
            if (s != null && s != "") {
                image = this.getImage(this.getDocumentBase(), s);
                this.prepareImage(image, this);
            }
        }
        catch (Exception ex) {
            System.out.println("run - Exception loading image " + s);
        }
        return image;
    }
    
    public void update(final Graphics graphics) {
        this.AnimatedColorsExist = false;
        final Dimension size = this.size();
        if (this.UserFontData == null) {
            this.LoadUserFont();
        }
        this.NeedRedraw = false;
        if (this.showCounters) {
            graphics.setColor(Color.lightGray);
            graphics.fillRect(0, 0, size.width, size.height);
            this.DrawCounters(graphics);
            return;
        }
        if (this.offGraphics == null) {
            this.offDimension = size;
            this.offImage = this.createImage(size.width, size.height);
            this.offGraphics = this.offImage.getGraphics();
        }
        try {
            if (this.BGColor != null) {
                this.offGraphics.setColor(this.GetAnimColor(this.BGColor));
            }
            this.offGraphics.fillRect(0, 0, size.width, size.height);
            if (this.IsLoaded(this.BackgroundImage)) {
                final int width = this.BackgroundImage.getWidth(this);
                for (int n = (size.width + 1) / width + 1, i = 0; i < n; ++i) {
                    this.offGraphics.drawImage(this.BackgroundImage, width * i, 0, this);
                }
            }
        }
        catch (Exception ex) {}
        int n2 = 0;
        if (this.displayRollover) {
            n2 = 1;
        }
        this.DrawEffects(n2);
        for (int j = n2; j < 12; j += this.NSTATES) {
            try {
                if (this.CheckState("Image" + (j + 1), 3) && this.IsLoaded(this.hImage[j])) {
                    this.offGraphics.drawImage(this.hImage[j], this.ImageX[j], this.ImageY[j], this);
                }
            }
            catch (Exception ex2) {}
        }
        this.offGraphics.setFont(this.TextFont[n2]);
        this.offGraphics.setColor(this.ShadowColor[n2]);
        int n3 = this.TextX[n2];
        int n4 = this.TextY[n2];
        final int stringLength = this.StringLength(this.Text[n2]);
        if (n4 == -1) {
            n4 = (size.height + this.offGraphics.getFontMetrics().getMaxAscent()) / 2 - 2;
        }
        if (this.Align[n2].equalsIgnoreCase("left")) {
            if (n3 == -1) {
                n3 = (size.width - stringLength) / 2;
            }
        }
        else {
            if (n3 == -1) {
                n3 = 0;
            }
            n3 = size.width - n3 - stringLength;
        }
        if (this.UserFontData != null) {
            this.PrintString(n2, n3, n4);
        }
        else {
            this.offGraphics.drawString(this.Text[n2], n3, n4);
            if (this.Outline[n2] > 0) {
                for (int k = 0; k <= this.Outline[n2]; ++k) {
                    for (int l = 0; l <= this.Outline[n2]; ++l) {
                        this.offGraphics.drawString(this.Text[n2], n3 + k, n4 + l);
                        this.offGraphics.drawString(this.Text[n2], n3 - k, n4 - l);
                        this.offGraphics.drawString(this.Text[n2], n3 - k, n4 + l);
                        this.offGraphics.drawString(this.Text[n2], n3 + k, n4 - l);
                    }
                }
            }
            this.offGraphics.setColor(this.GetAnimColor(this.TextColor[n2]));
            this.offGraphics.drawString(this.Text[n2], n3 - this.ShadowSize[n2], n4 - this.ShadowSize[n2]);
        }
        graphics.drawImage(this.offImage, 0, 0, this);
    }
    
    public void DrawTopLine(final Color color, final Dimension dimension, final int n, final int n2, final int n3) {
        this.offGraphics.setColor(color);
        this.offGraphics.drawLine(n, n2, dimension.width - 1 - n3, n2);
    }
    
    int GetCharSum(final String s) {
        if (s == null) {
            return 0;
        }
        char c = '\0';
        for (int i = 0; i < s.length(); ++i) {
            c += s.charAt(i);
        }
        return c;
    }
    
    Color GetAnimColor(final int[] array) {
        final int n = array[0];
        if (n == 1) {
            return new Color(array[3]);
        }
        this.AnimatedColorsExist = true;
        final int n2 = (int)((System.currentTimeMillis() - array[(n + 1) * 2]) % array[1]);
        int n3 = 0;
        int i = 0;
        while (i < n) {
            final int n4 = array[(i + 1) * 2];
            if (n2 >= n3 && n2 < n3 + Math.abs(n4)) {
                final Color color = new Color(array[(i + 1) * 2 + 1]);
                final Color color2 = new Color(array[((i + 1) % n + 1) * 2 + 1]);
                if (color.getRGB() == color2.getRGB() || n4 < 0) {
                    final long nextWakeUp = System.currentTimeMillis() + (n3 + Math.abs(n4) - n2);
                    if (this.NextWakeUp > nextWakeUp) {
                        this.NextWakeUp = nextWakeUp;
                    }
                }
                else {
                    this.NextWakeUp = 0L;
                }
                if (n4 < 0) {
                    return color;
                }
                return this.BlendColors(color, color2, 1.0f - (n2 - n3) / n4);
            }
            else {
                n3 += Math.abs(n4);
                ++i;
            }
        }
        return Color.black;
    }
    
    void showParseError(final Exception ex) {
        final String string = "Parse error: " + ex;
        this.showStatus(string);
        System.err.println(string);
        this.repaint();
    }
    
    public void DrawEffect_FillOval(final int n, final String s) {
        final Color getColorParam = this.GetColorParam(s, 1, Color.black, false);
        final Dimension size = this.size();
        this.offGraphics.setColor(getColorParam);
        this.offGraphics.fillOval(0, 0, size.width, size.height);
    }
    
    public void start() {
        if (this.thread == null) {
            (this.thread = new Thread(this)).start();
        }
    }
    
    public void DrawEffect_Sphere(final int n, final String s) {
        final Dimension size = this.size();
        int n2 = 1;
        final Color getColorParam = this.GetColorParam(s, n2++, Color.black, false);
        final Color getColorParam2 = this.GetColorParam(s, n2++, Color.white, false);
        final Color getColorParam3 = this.GetColorParam(s, n2++, Color.white, false);
        final Color getColorParam4 = this.GetColorParam(s, n2++, Color.white, false);
        final int getIntParam = this.GetIntParam(s, n2++, 1);
        final int getIntParam2 = this.GetIntParam(s, n2++, size.height / 2);
        final int getIntParam3 = this.GetIntParam(s, n2++, 1);
        final double n3 = getIntParam3 / 2 * Math.sqrt(11.0);
        for (int i = getIntParam2 - getIntParam3 / 2; i < getIntParam2 + getIntParam3 / 2; ++i) {
            for (int j = getIntParam - getIntParam3 / 2; j < getIntParam + getIntParam3 / 2; ++j) {
                final double n4 = j - getIntParam;
                final double n5 = i - getIntParam2;
                final double n6 = getIntParam3 * getIntParam3 / 4.0 - n4 * n4 - n5 * n5;
                if (n6 >= 0.0) {
                    final double n7 = ((-1.0 * n4 - 1.0 * n5 * 1.0 + 3.0 * Math.sqrt(n6)) / n3 + 1.0) / 2.0;
                    Color color = this.Blend3Colors(getColorParam, getColorParam2, getColorParam3, (float)(n7 * n7 * n7 * n7));
                    final double n8 = getIntParam3 / 2 - Math.sqrt(n4 * n4 + n5 * n5);
                    if (n8 <= 1.0) {
                        color = this.BlendColors(color, getColorParam4, (float)n8);
                    }
                    this.offGraphics.setColor(color);
                    this.offGraphics.drawLine(j, i, j, i);
                }
            }
        }
    }
    
    private int GetFieldsNumber(final String s, final String s2) {
        if (s == null) {
            return 0;
        }
        int i;
        int n;
        for (i = 0, n = 0; i != -1; i = s.indexOf(s2, i + 1), ++n) {}
        return n;
    }
    
    boolean DrawCounter(final int n, final Graphics graphics) {
        final Image loadCounter = this.LoadCounter(n, false, true);
        if (loadCounter == null) {
            return true;
        }
        final String string = "Counter" + Integer.toString(n + 1);
        graphics.drawImage(loadCounter, this.GetIntParam(string, 3, 0), this.GetIntParam(string, 4, 0), this);
        return true;
    }
    
    int StringLength(final String s) {
        if (this.UserFontData == null) {
            return this.offGraphics.getFontMetrics().stringWidth(s);
        }
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            n += this.GetCharWidth(s.charAt(i)) * this.UserFontDensity / 100;
        }
        return n;
    }
    
    public void DrawEffects(final int n) {
        for (int i = n; i < 10; i += 2) {
            final String string = "Effect" + Integer.toString(i + 1);
            final String getStrParam = this.GetStrParam(string, 0, "none");
            if (getStrParam.equalsIgnoreCase("fillrect")) {
                this.DrawEffect_FillRect(i, string, 0);
            }
            if (getStrParam.equalsIgnoreCase("fillround")) {
                this.DrawEffect_FillRound(i, string);
            }
            if (getStrParam.equalsIgnoreCase("fill3drect")) {
                this.DrawEffect_Fill3DRect(i, string);
            }
            if (getStrParam.equalsIgnoreCase("draw3drect")) {
                this.DrawEffect_Draw3DRect(i, string, false, 0);
            }
            if (getStrParam.equalsIgnoreCase("filloval")) {
                this.DrawEffect_FillOval(i, string);
            }
            if (getStrParam.equalsIgnoreCase("Gradient")) {
                this.DrawEffect_Gradient(i, string);
            }
            if (getStrParam.equalsIgnoreCase("light")) {
                this.DrawEffect_Light(i, string, true);
            }
            if (getStrParam.equalsIgnoreCase("lightline")) {
                this.DrawEffect_Light(i, string, false);
            }
            if (getStrParam.equalsIgnoreCase("rect")) {
                this.DrawEffect_Rect(i, string, false);
            }
            if (getStrParam.equalsIgnoreCase("underline")) {
                this.DrawEffect_Rect(i, string, true);
            }
            if (getStrParam.equalsIgnoreCase("drawoval")) {
                this.DrawEffect_DrawOval(i, string);
            }
            if (getStrParam.equalsIgnoreCase("sphere")) {
                this.DrawEffect_Sphere(i, string);
            }
            if (getStrParam.equalsIgnoreCase("Tab")) {
                this.DrawEffect_Tab(i, string);
            }
        }
    }
    
    public void DrawEffect_FillRect(final int n, final String s, final int n2) {
        Color color = this.GetColorParam(s, 4, null, false);
        if (color == null) {
            color = this.GetColorParam(s, 1, Color.black, false);
        }
        final Dimension size = this.size();
        this.offGraphics.setColor(color);
        this.offGraphics.fillRect(0, n2, size.width, size.height);
    }
    
    public void DrawEffect_Light(final int n, final String s, final boolean b) {
        if (this.EffectImage[n] != null) {
            this.offGraphics.drawImage(this.EffectImage[n], 0, 0, this);
            return;
        }
        final Dimension size = this.size();
        this.EffectImage[n] = this.createImage(size.width, size.height);
        final Graphics graphics = this.EffectImage[n].getGraphics();
        final int n2 = size.height / 2;
        int n4;
        int n3 = n4 = size.width / 2;
        if (!b) {
            n3 = n2;
            n4 = size.width - n2;
        }
        final Color getColorParam = this.GetColorParam(s, 1, Color.black, false);
        final Color getColorParam2 = this.GetColorParam(s, 2, Color.white, false);
        final float n5 = n3 / n2;
        final double n6 = n3;
        for (int i = 0; i < size.height; ++i) {
            for (int j = 0; j < size.width; ++j) {
                int n7;
                if ((n7 = j) < n3) {
                    n7 = n3;
                }
                if (j > n4) {
                    n7 = n4;
                }
                float n8 = (float)((n6 - Math.sqrt(Math.pow(n7 - j, 2.0) + Math.pow((n2 - i) * n5, 2.0))) / n6);
                if (n8 < 0.0f) {
                    n8 = 0.0f;
                }
                graphics.setColor(this.BlendColors(getColorParam, getColorParam2, n8));
                graphics.drawLine(j, i, j, i);
            }
        }
        this.offGraphics.drawImage(this.EffectImage[n], 0, 0, this);
    }
    
    String GetStrParam(final String s, final int n, final String s2) {
        try {
            final String getField = this.GetField(this.getParameter(s), "|", n);
            if (getField == null || getField.equalsIgnoreCase("")) {
                return s2;
            }
            return getField;
        }
        catch (Exception ex) {
            return s2;
        }
    }
    
    int[] PrepareAnimColorParam(final String s, final int n, final Color color, final int[] array) {
        final String getField = this.GetField(this.getParameter(s), "|", n);
        if (getField == null || getField.equalsIgnoreCase("")) {
            if (array != null) {
                return array;
            }
            return new int[] { 1, 0, 0, color.getRGB() };
        }
        else {
            final int getFieldsNumber = this.GetFieldsNumber(getField, ">");
            final int[] array2 = new int[(getFieldsNumber + 2) * 2];
            if ((array2[0] = getFieldsNumber) == 1) {
                array2[3] = this.GetColorParam(s, n, color, false).getRGB();
                return array2;
            }
            int n2 = 0;
            array2[(getFieldsNumber + 1) * 2] = this.GetIntField(this.GetField(getField, ">", 0), ",", 2, 0);
            for (int i = 0; i < getFieldsNumber; ++i) {
                final String getField2 = this.GetField(getField, ">", i);
                if (getField2 == null || getField2 == null) {
                    break;
                }
                final int getIntField = this.GetIntField(getField2, ",", 1, 100);
                final Color getColorParam = this.GetColorParam(this.GetField(getField2, ",", 0), 0, color, true);
                array2[(i + 1) * 2] = getIntField;
                array2[(i + 1) * 2 + 1] = getColorParam.getRGB();
                n2 += Math.abs(getIntField);
            }
            array2[1] = n2;
            return array2;
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public boolean CheckState(final String s, final int n) {
        final String getStrParam = this.GetStrParam(s, n, "");
        return (!getStrParam.equals("passive") || !this.isActiveApplet()) && (!getStrParam.equals("active") || this.isActiveApplet());
    }
    
    public void DrawBottomLine(final Color color, final Dimension dimension, final int n, final int n2, final int n3) {
        this.offGraphics.setColor(color);
        this.offGraphics.drawLine(n, dimension.height - 1 - n2, dimension.width - n3, dimension.height - 1 - n2);
    }
    
    public void DrawEffect_DrawOval(final int n, final String s) {
        final int getIntParam = this.GetIntParam(s, 1, 1);
        Color color = this.GetColorParam(s, 2, Color.black, false);
        final Dimension size = this.size();
        for (int i = 0; i < getIntParam; ++i) {
            color = this.GetColorParam(s, 2 + i, color, false);
            this.offGraphics.setColor(color);
            this.offGraphics.drawOval(i, i, size.width - i * 2 - 1, size.height - i * 2 - 1);
        }
    }
    
    Color Blend3Colors(final Color color, final Color color2, final Color color3, final float n) {
        if (n > 0.5) {
            return this.BlendColors(color, color2, (float)(n - 0.5) * 2.0f);
        }
        return this.BlendColors(color2, color3, n * 2.0f);
    }
    
    boolean LoadUserFont() {
        if (this.UserFontImageWidth != 0) {
            return false;
        }
        if (!this.IsLoaded(this.UserFontImage)) {
            return false;
        }
        this.UserFontImageWidth = this.UserFontImage.getWidth(this);
        this.UserFontImageHeight = this.UserFontImage.getHeight(this);
        this.UserFontHeight = (this.UserFontImageHeight + 7) / 14;
        this.UserFontDensity = this.GetIntParam("UserFont", 1, 100);
        this.UserFontRealHeight = this.GetIntParam("UserFont", 2, this.UserFontHeight * 7 / 10);
        this.UserFontOffsetY = this.GetIntParam("UserFont", 3, 0);
        this.UserFontOffsetX = this.GetIntParam("UserFont", 4, 0);
        final int[] array = new int[this.UserFontImageWidth * this.UserFontImageHeight];
        final PixelGrabber pixelGrabber = new PixelGrabber(this.UserFontImage, 0, 0, this.UserFontImageWidth, this.UserFontImageHeight, array, 0, this.UserFontImageWidth);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            this.UserFontData = null;
            return false;
        }
        if ((pixelGrabber.status() & 0x80) != 0x0) {
            this.UserFontData = null;
            return false;
        }
        this.UserFontOffsets = new int[238];
        int n = 0;
        for (int i = 0; i < this.UserFontImageWidth * this.UserFontImageHeight; ++i) {
            if (this.IsBlue(array[i])) {
                this.UserFontOffsets[n] = i;
                if (++n >= 238) {
                    break;
                }
                if (n % 17 == 0) {
                    i += this.UserFontImageWidth * (this.UserFontHeight - 3) - 2;
                }
                else {
                    i += 3;
                }
            }
        }
        if (n != 238) {
            System.out.println("Error when trying to load user font ");
            return false;
        }
        this.UserFontData = new byte[this.UserFontImageWidth * this.UserFontImageHeight];
        for (int j = 0; j < this.UserFontImageWidth * this.UserFontImageHeight; ++j) {
            this.UserFontData[j] = (byte)((array[j] & 0xFF0000) >> 16);
        }
        this.UserFontImage = null;
        return true;
    }
    
    public EasyButton_v2_3() {
        this.thread = null;
        this.NSTATES = 2;
        this.Text = new String[3];
        this.TextX = new int[3];
        this.TextY = new int[3];
        this.ImageX = new int[12];
        this.ImageY = new int[12];
        this.TextFont = new Font[3];
        this.TextColor = null;
        this.ShadowColor = new Color[3];
        this.ShadowSize = new int[3];
        this.Outline = new int[3];
        this.BackgroundImage = null;
        this.hImage = new Image[12];
        this.Clip = new AudioClip[3];
        this.NeedRedraw = true;
        this.Align = new String[3];
        this.EffectImage = new Image[10];
        this.EffectAnimColors = new int[40][];
        this.UpdateTime = 50;
        this.AnimatedColorsExist = false;
        this.displayRollover = false;
        this.showCounters = false;
        this.NextWakeUp = 0L;
        this.UserFontImage = null;
        this.UserFontImageWidth = 0;
        this.UserFontImageHeight = 0;
        this.UserFontData = null;
        this.UserFontHeight = 0;
        this.UserFontRealHeight = 0;
        this.UserFontOffsetY = 0;
        this.UserFontOffsetX = 0;
        this.UserFontDensity = 0;
        this.UserFontOffsets = null;
        this.LocalUpdateKey = 0;
        this.AppletGroup = 0;
        this.hrefURL = null;
        this.hrefTarget = null;
        this.div_width = 0;
    }
    
    int GetIntField(final String s, final String s2, final int n, final int n2) {
        if (s == "" || s == null) {
            return n2;
        }
        int int1 = n2;
        try {
            int1 = Integer.parseInt(this.GetField(s, s2, n));
        }
        catch (Exception ex) {}
        return int1;
    }
    
    int PrintChar(final char c, int n, int n2, final Color color, final Color color2) {
        final int getCharOffset = this.GetCharOffset(c);
        final int getCharWidth = this.GetCharWidth(c);
        final int n3 = getCharOffset + this.div_width / 6;
        Color color3 = Color.black;
        int n4 = -1;
        n2 -= this.UserFontRealHeight;
        ++n;
        try {
            for (int i = 0; i < getCharWidth; ++i) {
                for (int j = 0; j < this.UserFontHeight - 2; ++j) {
                    int n5 = this.UserFontData[n3 + i + (j - 1 + this.UserFontOffsetY) * this.UserFontImageWidth + this.UserFontOffsetX];
                    if (n5 < 0) {
                        n5 += 256;
                    }
                    if (n5 <= 245) {
                        if (n4 != n5) {
                            color3 = this.BlendColors(color2, color, n5 / 255.0f);
                        }
                        n4 = n5;
                        this.offGraphics.setColor(color3);
                        this.offGraphics.drawLine(n + i, n2 + j, n + i, n2 + j);
                    }
                }
            }
        }
        catch (Exception ex) {}
        return getCharWidth;
    }
    
    Image LoadCounter(final int n, final boolean b, final boolean b2) {
        final String string = "Counter" + Integer.toString(n + 1);
        final String getStrParam = this.GetStrParam(string, 0, null);
        if (getStrParam == null || getStrParam == "") {
            return null;
        }
        final boolean equalsIgnoreCase = this.GetStrParam(string, 1, "always").equalsIgnoreCase("click");
        if (!b2 && b == equalsIgnoreCase) {
            return null;
        }
        final double getDblParam = this.GetDblParam(string, 2, 1.0);
        if (!b2 && Math.random() > getDblParam) {
            return null;
        }
        return this.LoadImage(getStrParam);
    }
    
    boolean IsLoaded(final Image image) {
        return image != null && (this.checkImage(image, this) & 0x23) != 0x0;
    }
    
    int GetCharWidth(final int n) {
        if (this.div_width == 0 && n != 124) {
            this.div_width = this.GetCharWidth(124);
        }
        return this.UserFontOffsets[(n - 32) / 16 * 17 + (n - 32) % 16 + 1] - this.GetCharOffset(n) - this.div_width / 3;
    }
    
    boolean PrintString(final int n, int n2, final int n3) {
        if (this.UserFontData == null) {
            return false;
        }
        final String s = this.Text[n];
        final Color getAnimColor = this.GetAnimColor(this.TextColor[n]);
        final Color color = this.ShadowColor[n];
        for (int i = 0; i < s.length(); ++i) {
            n2 += this.PrintChar(s.charAt(i), n2, n3, getAnimColor, color) * this.UserFontDensity / 100;
        }
        return false;
    }
    
    static {
        EasyButton_v2_3.ActiveApplet = new Applet[10];
        EasyButton_v2_3.UpdateKey = new int[10];
    }
    
    public void DrawEffect_Rect(final int n, final String s, final boolean b) {
        final int getIntParam = this.GetIntParam(s, 1, 1);
        final Dimension size = this.size();
        Color color = Color.gray;
        int[] array = null;
        for (int i = 0; i < getIntParam; ++i) {
            if (i < 3) {
                final int n2 = n + 10 * i;
                if (this.EffectAnimColors[n2] == null) {
                    this.EffectAnimColors[n2] = this.PrepareAnimColorParam(s, 2 + i, color, array);
                }
                color = this.GetAnimColor(this.EffectAnimColors[n2]);
                array = this.EffectAnimColors[n2];
            }
            else {
                color = this.GetColorParam(s, 2 + i, color, false);
            }
            this.DrawEffect_Rect(i, size, color, b);
        }
    }
    
    public void DrawEffect_Rect(final int n, final Dimension dimension, final Color color, final boolean b) {
        this.offGraphics.setColor(color);
        if (b) {
            this.offGraphics.drawLine(0, dimension.height - n - 1, dimension.width, dimension.height - n - 1);
            return;
        }
        this.offGraphics.drawRect(n, n, dimension.width - n * 2 - 1, dimension.height - n * 2 - 1);
    }
    
    public void DrawEffect_Fill3DRect(final int n, final String s) {
        this.DrawEffect_FillRect(n, s, 0);
        this.DrawEffect_Draw3DRect(n, s, false, 0);
    }
    
    Color BlendColors(final Color color, final Color color2, final float n) {
        final float n2 = color.getRed();
        final float n3 = color.getGreen();
        final float n4 = color.getBlue();
        final float n5 = color2.getRed();
        final float n6 = color2.getGreen();
        final float n7 = color2.getBlue();
        final float n8 = n / 255.0f;
        final float n9 = (1.0f - n) / 255.0f;
        return new Color(n8 * n2 + n9 * n5, n8 * n3 + n9 * n6, n8 * n4 + n9 * n7);
    }
    
    public void run() {
        int n = 0;
        do {
            this.hImage[n] = null;
        } while (++n < 12);
        Thread.currentThread().setPriority(1);
        this.repaint();
        int n2 = 0;
        do {
            this.hImage[n2] = this.LoadImage(this.GetStrParam("Image" + Integer.toString(n2 + 1), 0, null));
        } while (++n2 < 12);
        this.BackgroundImage = this.LoadImage(this.GetStrParam("Background", 0, null));
        this.UserFontImage = this.LoadImage(this.GetStrParam("UserFont", 0, null));
        if (this.UserFontData == null) {
            this.LoadUserFont();
        }
        this.repaint();
        if (this.UpdateTime == 0) {
            return;
        }
        while (true) {
            if (this.shouldUpdate()) {
                this.repaint();
            }
            if (this.AnimatedColorsExist) {
                long n3 = this.UpdateTime;
                if (this.NextWakeUp != 1000000000000L) {
                    n3 = this.NextWakeUp - System.currentTimeMillis();
                    if (n3 < this.UpdateTime) {
                        n3 = this.UpdateTime;
                    }
                }
                this.NextWakeUp = 1000000000000L;
                try {
                    Thread.currentThread();
                    Thread.sleep(n3);
                }
                catch (Exception ex) {
                    return;
                }
                this.repaint();
            }
            else {
                try {
                    Thread.currentThread();
                    Thread.sleep(this.UpdateTime);
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    boolean isActiveApplet() {
        return this == EasyButton_v2_3.ActiveApplet[this.AppletGroup];
    }
    
    public void init() {
        try {
            final String parameter = this.getParameter("HREF");
            if (parameter != null && !parameter.equals("")) {
                try {
                    this.hrefURL = new URL(this.getDocumentBase(), parameter);
                }
                catch (MalformedURLException ex) {
                    this.showParseError(ex);
                }
            }
        }
        catch (NumberFormatException ex2) {
            this.showParseError(ex2);
        }
        this.hrefTarget = this.GetStrParam("TARGET", 0, "_self");
        this.UpdateTime = this.GetIntParam("UpdateTime", 0, this.UpdateTime);
        final int getCharSum = this.GetCharSum(this.getParameter("Copyright"));
        if (getCharSum != 6208 && getCharSum != 6438) {
            System.out.println("Wrong parameter 'Copyright'");
            return;
        }
        this.Status = this.getParameter("Status");
        String getStrParam = "";
        int getIntParam = 12;
        this.TextColor = new int[3][];
        int n = 0;
        do {
            String string = "Text";
            if (n > 0) {
                string += n + 1;
            }
            this.Text[n] = this.GetStrParam(string, 0, (n > 0) ? this.Text[n - 1] : "");
            this.Align[n] = this.GetStrParam(string, 1, (n > 0) ? this.Align[n - 1] : "left");
            this.TextX[n] = this.GetIntParam(string, 2, (n > 0) ? this.TextX[n - 1] : -1);
            this.TextY[n] = this.GetIntParam(string, 3, (n > 0) ? this.TextY[n - 1] : -1);
            int n2 = 0;
            final String getStrParam2 = this.GetStrParam(string, 4, "Arial");
            getIntParam = this.GetIntParam(string, 5, getIntParam);
            getStrParam = this.GetStrParam(string, 6, getStrParam);
            if (getStrParam.indexOf("bold") != -1) {
                n2 |= 0x1;
            }
            if (getStrParam.indexOf("italic") != -1) {
                n2 |= 0x2;
            }
            this.TextFont[n] = new Font(getStrParam2, n2, getIntParam);
            this.TextColor[n] = this.PrepareAnimColorParam(string, 7, Color.black, (int[])((n > 0) ? this.TextColor[n - 1] : null));
            this.ShadowColor[n] = this.GetColorParam(string, 8, (n > 0) ? this.ShadowColor[n - 1] : Color.gray, false);
            this.ShadowSize[n] = this.GetIntParam(string, 9, (n > 0) ? this.ShadowSize[n - 1] : 1);
            this.Outline[n] = this.GetIntParam(string, 10, (n > 0) ? this.Outline[n - 1] : 0);
        } while (++n < 3);
        int n3 = 0;
        do {
            this.ImageX[n3] = this.GetIntParam("Image" + Integer.toString(n3 + 1), 1, this.ImageX[0]);
            this.ImageY[n3] = this.GetIntParam("Image" + Integer.toString(n3 + 1), 2, this.ImageY[0]);
        } while (++n3 < 12);
        this.BGColor = this.PrepareAnimColorParam("Background", 1, Color.gray, null);
        try {
            this.Clip[0] = this.getAudioClip(this.getDocumentBase(), this.getParameter("Sound"));
            this.Clip[1] = this.getAudioClip(this.getDocumentBase(), this.getParameter("Sound2"));
        }
        catch (Exception ex3) {}
        this.LoadCounters(false);
        this.AppletGroup = this.GetIntParam("Group", 0, 9);
        if (this.GetStrParam("Group", 1, "passive").equals("active")) {
            EasyButton_v2_3.ActiveApplet[this.AppletGroup] = this;
        }
    }
    
    Color GetColorParam(final String s, final int n, final Color color, final boolean b) {
        String s2;
        if (b) {
            s2 = s;
        }
        else {
            s2 = this.GetField(this.getParameter(s), "|", n);
        }
        if (s2 == null || s2.equalsIgnoreCase("")) {
            return color;
        }
        if (s2.charAt(0) == '#') {
            return new Color(Integer.parseInt(s2.substring(1), 16));
        }
        final int index = s2.indexOf("+");
        final int index2 = s2.indexOf("-");
        final int n2 = index + index2;
        int int1 = 0;
        if (n2 > 0) {
            try {
                int1 = Integer.parseInt(s2.substring(n2 + 2));
            }
            catch (Exception ex) {
                int1 = 50;
            }
            s2 = s2.substring(0, n2);
        }
        final String s3 = "red     green   blue    pink    orange  magenta cyan    white   yellow  gray    grey    black   darkGraylightGray";
        Color color2 = color;
        try {
            switch (s3.indexOf(s2)) {
                case 0: {
                    color2 = Color.red;
                    break;
                }
                case 8: {
                    color2 = Color.green;
                    break;
                }
                case 16: {
                    color2 = Color.blue;
                    break;
                }
                case 24: {
                    color2 = Color.pink;
                    break;
                }
                case 32: {
                    color2 = Color.orange;
                    break;
                }
                case 40: {
                    color2 = Color.magenta;
                    break;
                }
                case 48: {
                    color2 = Color.cyan;
                    break;
                }
                case 56: {
                    color2 = Color.white;
                    break;
                }
                case 64: {
                    color2 = Color.yellow;
                    break;
                }
                case 72: {
                    color2 = Color.gray;
                    break;
                }
                case 80: {
                    color2 = Color.gray;
                    break;
                }
                case 88: {
                    color2 = Color.black;
                    break;
                }
                case 96: {
                    color2 = Color.darkGray;
                    break;
                }
                case 104: {
                    color2 = Color.lightGray;
                    break;
                }
            }
        }
        catch (Exception ex2) {}
        if (index > 0) {
            color2 = this.BlendColors(Color.white, color2, (float)(int1 / 100.0));
        }
        if (index2 > 0) {
            color2 = this.BlendColors(Color.black, color2, (float)(int1 / 100.0));
        }
        return color2;
    }
    
    public void DrawEffect_FillRound(final int n, final String s) {
        final Color getColorParam = this.GetColorParam(s, 1, Color.black, false);
        final int getIntParam = this.GetIntParam(s, 3, 5);
        final Dimension size = this.size();
        this.offGraphics.setColor(getColorParam);
        this.offGraphics.fillRoundRect(0, 0, size.width - 1, size.height - 1, getIntParam, getIntParam);
    }
    
    String ModifyPath(final String s) {
        return s;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 501: {
                this.showCounters = false;
                if (event.shiftDown() && event.controlDown()) {
                    this.showCounters = true;
                }
                this.repaint();
                return true;
            }
            case 502: {
                EasyButton_v2_3.ActiveApplet[this.AppletGroup] = this;
                final int[] updateKey = EasyButton_v2_3.UpdateKey;
                final int appletGroup = this.AppletGroup;
                ++updateKey[appletGroup];
                if (this.Clip[1] != null) {
                    this.Clip[1].play();
                }
                if (this.hrefURL != null && (event.modifiers & 0x1) == 0x0) {
                    this.LoadCounters(true);
                    this.getAppletContext().showDocument(this.hrefURL, this.hrefTarget);
                }
                return true;
            }
            case 504: {
                if (this.Status != null) {
                    this.showStatus(this.Status);
                }
                else {
                    this.showStatus(this.hrefURL + "");
                }
                this.displayRollover = true;
                this.repaint();
                if (this.Clip[0] != null) {
                    this.Clip[0].play();
                }
                return true;
            }
            case 505: {
                this.displayRollover = false;
                this.showCounters = false;
                this.repaint();
                return true;
            }
            case 1002: {
                this.repaint();
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    boolean IsBlue(final int n) {
        return (n & 0xFF) - ((n & 0xFF0000) >> 16) > 100;
    }
    
    void DrawCounters(final Graphics graphics) {
        int n = 0;
        do {
            this.DrawCounter(n, graphics);
        } while (++n < 4);
    }
}
