import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class jsTA_BGAction
{
    final int NO_ACTION = 0;
    final int ACTION_YUKI = 1;
    final int ACTION_AME = 2;
    final int ACTION_HOSHI = 3;
    final int ACTION_MAX = 4;
    final int IMAGE_LEFT_TOP = 0;
    final int IMAGE_LEFT_CENTER = 1;
    final int IMAGE_LEFT_BOTTOM = 2;
    final int IMAGE_CENTER_TOP = 3;
    final int IMAGE_CENTER_CENTER = 4;
    final int IMAGE_CENTER_BOTTOM = 5;
    final int IMAGE_RIGHT_TOP = 6;
    final int IMAGE_RIGHT_CENTER = 7;
    final int IMAGE_RIGHT_BOTTOM = 8;
    final int IMAGE_FIT = 9;
    final int IMAGE_TILE = 10;
    final int IMAGE_XY = 11;
    final int IMAGE_XYXLYL = 12;
    Applet m_Applet;
    Image m_Image;
    int m_nImageX;
    int m_nImageY;
    int m_nImageXl;
    int m_nImageYl;
    int m_DrawImageType;
    int m_nBackGroundAction;
    int[][] m_BackGroundAction;
    int m_nBackGroundDotNum;
    int m_nBackGroundSpeed;
    Color m_BackGroundDotColor;
    boolean m_debug;
    
    private void Debug(final String s) {
        if (this.m_debug) {
            System.out.println(s);
        }
    }
    
    public jsTA_BGAction(final Applet applet) {
        this.m_DrawImageType = -1;
        this.m_nBackGroundDotNum = 50;
        this.m_nBackGroundSpeed = 3;
        this.m_debug = false;
        this.m_Applet = applet;
    }
    
    Color parseRGB(final String s) {
        return new Color(Integer.parseInt(s.substring(0, 2), 16), Integer.parseInt(s.substring(2, 4), 16), Integer.parseInt(s.substring(4, 6), 16));
    }
    
    public void checkParam() {
        final String parameter = this.m_Applet.getParameter("BackGroundDotColor");
        if (parameter != null) {
            this.m_BackGroundDotColor = this.parseRGB(parameter);
        }
        else {
            this.m_BackGroundDotColor = Color.white;
        }
        final String parameter2 = this.m_Applet.getParameter("BackGroundAction");
        if (parameter2 != null) {
            this.m_nBackGroundAction = Integer.valueOf(parameter2);
            if (this.m_nBackGroundAction > 4) {
                this.m_nBackGroundAction = (int)(Math.random() * 4.0);
            }
            this.initBackGroundAction();
        }
        final String parameter3 = this.m_Applet.getParameter("BackGroundActionSpeed");
        if (parameter3 != null) {
            this.m_nBackGroundSpeed = Integer.valueOf(parameter3);
        }
        else {
            switch (this.m_nBackGroundAction) {
                case 1: {
                    this.m_nBackGroundSpeed = 3;
                    break;
                }
                case 2: {
                    this.m_nBackGroundSpeed = 15;
                    break;
                }
                case 3: {
                    this.m_nBackGroundSpeed = 3;
                    break;
                }
            }
        }
        final String parameter4 = this.m_Applet.getParameter("BackGroundDotNum");
        if (parameter4 != null) {
            this.m_nBackGroundDotNum = Integer.valueOf(parameter4);
        }
        final String parameter5 = this.m_Applet.getParameter("BackGroundSpeed");
        if (parameter5 != null) {
            this.m_nBackGroundSpeed = Integer.valueOf(parameter5);
        }
        final String parameter6 = this.m_Applet.getParameter("BackGroundImage");
        if (parameter6 != null) {
            String s = null;
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer(parameter6, ",");
                switch (stringTokenizer.countTokens()) {
                    case 1: {
                        s = stringTokenizer.nextToken();
                        this.Debug("Back Ground Image=[" + s + "]");
                        this.m_DrawImageType = 4;
                        break;
                    }
                    case 2: {
                        s = stringTokenizer.nextToken();
                        this.m_DrawImageType = Integer.valueOf(stringTokenizer.nextToken());
                        break;
                    }
                    case 3: {
                        s = stringTokenizer.nextToken();
                        final String nextToken = stringTokenizer.nextToken();
                        final String nextToken2 = stringTokenizer.nextToken();
                        this.m_nImageX = Integer.valueOf(nextToken);
                        this.m_nImageY = Integer.valueOf(nextToken2);
                        this.m_DrawImageType = 11;
                        break;
                    }
                    case 5: {
                        s = stringTokenizer.nextToken();
                        final String nextToken3 = stringTokenizer.nextToken();
                        final String nextToken4 = stringTokenizer.nextToken();
                        final String nextToken5 = stringTokenizer.nextToken();
                        final String nextToken6 = stringTokenizer.nextToken();
                        this.m_nImageX = Integer.valueOf(nextToken3);
                        this.m_nImageY = Integer.valueOf(nextToken4);
                        this.m_nImageXl = Integer.valueOf(nextToken5);
                        this.m_nImageYl = Integer.valueOf(nextToken6);
                        this.m_DrawImageType = 12;
                        break;
                    }
                    default: {
                        System.out.println("BackGroundImage Error");
                        break;
                    }
                }
            }
            catch (NoSuchElementException ex) {
                System.out.println("BackGroundImage Error");
                ex.printStackTrace();
                return;
            }
            if (s != null) {
                URL url;
                try {
                    url = new URL(this.m_Applet.getCodeBase() + s.trim());
                }
                catch (MalformedURLException ex2) {
                    ex2.printStackTrace();
                    return;
                }
                final MediaTracker mediaTracker = new MediaTracker(this.m_Applet);
                mediaTracker.addImage(this.m_Image = this.m_Applet.getImage(url), 0);
                mediaTracker.checkID(0);
            }
        }
    }
    
    void initBackGroundAction() {
        final Dimension size = this.m_Applet.size();
        this.m_BackGroundAction = new int[2][this.m_nBackGroundDotNum];
        for (int i = 0; i < this.m_nBackGroundDotNum; ++i) {
            this.m_BackGroundAction[0][i] = (int)(Math.random() * size.width);
            this.m_BackGroundAction[1][i] = (int)(Math.random() * size.height);
        }
    }
    
    void DrawBackGround_Yiki(final Graphics graphics) {
        final Dimension size = this.m_Applet.size();
        graphics.setColor(this.m_BackGroundDotColor);
        for (int i = 0; i < this.m_nBackGroundDotNum; ++i) {
            graphics.drawLine(this.m_BackGroundAction[0][i], this.m_BackGroundAction[1][i], this.m_BackGroundAction[0][i] + (int)(Math.random() * 2.0), this.m_BackGroundAction[1][i] + (int)(Math.random() * 2.0));
            final int[] array = this.m_BackGroundAction[0];
            final int n = i;
            array[n] += (int)(Math.random() * this.m_nBackGroundSpeed);
            final int[] array2 = this.m_BackGroundAction[1];
            final int n2 = i;
            array2[n2] += (int)(Math.random() * this.m_nBackGroundSpeed);
            final int[] array3 = this.m_BackGroundAction[0];
            final int n3 = i;
            array3[n3] -= this.m_nBackGroundSpeed;
            if (this.m_BackGroundAction[1][i] > size.height || this.m_BackGroundAction[1][i] < 0) {
                if (this.m_nBackGroundSpeed > 0) {
                    this.m_BackGroundAction[1][i] = 0;
                }
                else {
                    this.m_BackGroundAction[1][i] = size.height;
                }
                this.m_BackGroundAction[0][i] = (int)(Math.random() * size.width);
            }
        }
    }
    
    void DrawBackGround_Ame(final Graphics graphics) {
        final Dimension size = this.m_Applet.size();
        graphics.setColor(this.m_BackGroundDotColor);
        for (int i = 0; i < this.m_nBackGroundDotNum; ++i) {
            graphics.drawLine(this.m_BackGroundAction[0][i], this.m_BackGroundAction[1][i], this.m_BackGroundAction[0][i], this.m_BackGroundAction[1][i] + 2);
            final int[] array = this.m_BackGroundAction[1];
            final int n = i;
            array[n] += (int)(Math.random() * this.m_nBackGroundSpeed);
            if (this.m_BackGroundAction[1][i] > size.height || this.m_BackGroundAction[1][i] < 0) {
                if (this.m_nBackGroundSpeed > 0) {
                    this.m_BackGroundAction[1][i] = 0;
                }
                else {
                    this.m_BackGroundAction[1][i] = size.height;
                }
                this.m_BackGroundAction[0][i] = (int)(Math.random() * size.width);
            }
        }
    }
    
    void DrawBackGround_Hoshi(final Graphics graphics) {
        final Dimension size = this.m_Applet.size();
        graphics.setColor(this.m_BackGroundDotColor);
        for (int i = 0; i < this.m_nBackGroundDotNum; ++i) {
            graphics.drawLine(this.m_BackGroundAction[0][i], this.m_BackGroundAction[1][i], this.m_BackGroundAction[0][i], this.m_BackGroundAction[1][i]);
            if (i > (int)(this.m_nBackGroundDotNum * 0.9)) {
                final int[] array = this.m_BackGroundAction[0];
                final int n = i;
                array[n] += this.m_nBackGroundSpeed * 6;
            }
            else if (i > (int)(this.m_nBackGroundDotNum * 0.8)) {
                final int[] array2 = this.m_BackGroundAction[0];
                final int n2 = i;
                array2[n2] += this.m_nBackGroundSpeed * 4;
            }
            else if (i > (int)(this.m_nBackGroundDotNum * 0.7)) {
                final int[] array3 = this.m_BackGroundAction[0];
                final int n3 = i;
                array3[n3] += this.m_nBackGroundSpeed * 3;
            }
            else if (i > (int)(this.m_nBackGroundDotNum * 0.6)) {
                final int[] array4 = this.m_BackGroundAction[0];
                final int n4 = i;
                array4[n4] += this.m_nBackGroundSpeed * 2;
            }
            else {
                final int[] array5 = this.m_BackGroundAction[0];
                final int n5 = i;
                array5[n5] += this.m_nBackGroundSpeed;
            }
            if (this.m_BackGroundAction[0][i] > size.width || this.m_BackGroundAction[0][i] < 0) {
                if (this.m_nBackGroundSpeed > 0) {
                    this.m_BackGroundAction[0][i] = 1;
                }
                else {
                    this.m_BackGroundAction[0][i] = size.width;
                }
            }
        }
    }
    
    void backGrundImage(final Graphics graphics) {
        if (this.m_Image == null) {
            return;
        }
        int n = 0;
        int n2 = 0;
        int n3 = this.m_Image.getHeight(null);
        int n4 = this.m_Image.getWidth(null);
        if (n3 <= 0 || n4 <= 0) {
            return;
        }
        final Dimension size = this.m_Applet.size();
        if (this.m_DrawImageType == 10) {
            for (int i = 0; i < size.width; i += n3) {
                for (int j = 0; j < size.height; j += n4) {
                    graphics.drawImage(this.m_Image, i, j, n3, n4, null);
                }
            }
            return;
        }
        switch (this.m_DrawImageType) {
            case 0: {
                n = 0;
                n2 = 0;
                break;
            }
            case 1: {
                n = 0;
                n2 = (size.height - n4) / 2;
                break;
            }
            case 2: {
                n = 0;
                n2 = size.height - n4;
                break;
            }
            case 3: {
                n = (size.width - n3) / 2;
                n2 = 0;
                break;
            }
            case 4: {
                n = (size.width - n3) / 2;
                n2 = (size.height - n4) / 2;
                break;
            }
            case 5: {
                n = (size.width - n3) / 2;
                n2 = size.height - n4;
                break;
            }
            case 6: {
                n = size.width - n3;
                n2 = 0;
                break;
            }
            case 7: {
                n = size.width - n3;
                n2 = (size.height - n4) / 2;
                break;
            }
            case 8: {
                n = size.width - n3;
                n2 = size.height - n4;
                break;
            }
            case 9:
            case 10: {
                n3 = size.width;
                n4 = size.height;
            }
            case 11: {
                n = this.m_nImageX;
                n2 = this.m_nImageY;
                break;
            }
            case 12: {
                n = this.m_nImageX;
                n2 = this.m_nImageY;
                n3 = this.m_nImageXl;
                n4 = this.m_nImageYl;
                break;
            }
        }
        graphics.drawImage(this.m_Image, n, n2, n3, n4, null);
    }
    
    void action(final Graphics graphics) {
        this.backGrundImage(graphics);
        switch (this.m_nBackGroundAction) {
            case 1: {
                this.DrawBackGround_Yiki(graphics);
            }
            case 2: {
                this.DrawBackGround_Ame(graphics);
            }
            case 3: {
                this.DrawBackGround_Hoshi(graphics);
            }
            default: {}
        }
    }
}
