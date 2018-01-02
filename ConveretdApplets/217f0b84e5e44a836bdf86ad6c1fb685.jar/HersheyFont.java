import java.io.BufferedInputStream;
import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.net.URL;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class HersheyFont
{
    public static final int HORIZONTAL_CENTER = 0;
    public static final int HORIZONTAL_LEFT = 1;
    public static final int HORIZONTAL_RIGHT = 2;
    public static final int HORIZONTAL_NORMAL = 1;
    public static final int VERTICAL_TOP = 0;
    public static final int VERTICAL_HALF = 1;
    public static final int VERTICAL_CAP = 2;
    public static final int VERTICAL_BOTTOM = 3;
    public static final int VERTICAL_NORMAL = 3;
    private static final int MAX_CHARACTERS = 256;
    protected static final int X = 0;
    protected static final int Y = 1;
    private float hersheyWidth;
    private float hersheyHeight;
    private int hersheyLineWidth;
    private int hersheyHorizontalAlignment;
    private int herhseyVerticalAlignment;
    private double hersheyTheta;
    private boolean hersheyItalics;
    private float hersheyItalicSlant;
    private String copyright;
    private float H5;
    protected String name;
    protected char[][][] characterVectors;
    protected int[] characterMinX;
    protected int[] characterMaxX;
    protected int characterSetMinY;
    protected int characterSetMaxY;
    protected int charactersInSet;
    
    public HersheyFont(final InputStream inputStream, final String name) {
        this.hersheyWidth = 1.0f;
        this.hersheyHeight = 1.0f;
        this.hersheyLineWidth = 1;
        this.hersheyHorizontalAlignment = 1;
        this.herhseyVerticalAlignment = 3;
        this.hersheyTheta = 0.0;
        this.hersheyItalics = false;
        this.hersheyItalicSlant = 0.75f;
        this.copyright = "Copyright (c) James P. Buzbee Mar 30, 1996";
        this.H5 = 0.5f;
        this.characterVectors = new char[256][][];
        this.LoadHersheyFont(this.name = name, inputStream);
    }
    
    public HersheyFont(final String name) {
        this.hersheyWidth = 1.0f;
        this.hersheyHeight = 1.0f;
        this.hersheyLineWidth = 1;
        this.hersheyHorizontalAlignment = 1;
        this.herhseyVerticalAlignment = 3;
        this.hersheyTheta = 0.0;
        this.hersheyItalics = false;
        this.hersheyItalicSlant = 0.75f;
        this.copyright = "Copyright (c) James P. Buzbee Mar 30, 1996";
        this.H5 = 0.5f;
        this.characterVectors = new char[256][][];
        this.name = name;
        try {
            final FileInputStream fileInputStream = new FileInputStream(name);
            this.LoadHersheyFont(name, fileInputStream);
            fileInputStream.close();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public HersheyFont(final URL url) {
        this.hersheyWidth = 1.0f;
        this.hersheyHeight = 1.0f;
        this.hersheyLineWidth = 1;
        this.hersheyHorizontalAlignment = 1;
        this.herhseyVerticalAlignment = 3;
        this.hersheyTheta = 0.0;
        this.hersheyItalics = false;
        this.hersheyItalicSlant = 0.75f;
        this.copyright = "Copyright (c) James P. Buzbee Mar 30, 1996";
        this.H5 = 0.5f;
        this.characterVectors = new char[256][][];
        this.name = url.toString();
        try {
            final InputStream openStream = url.openStream();
            this.LoadHersheyFont(this.name, openStream);
            openStream.close();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public HersheyFont(final URL url, final String name) {
        this.hersheyWidth = 1.0f;
        this.hersheyHeight = 1.0f;
        this.hersheyLineWidth = 1;
        this.hersheyHorizontalAlignment = 1;
        this.herhseyVerticalAlignment = 3;
        this.hersheyTheta = 0.0;
        this.hersheyItalics = false;
        this.hersheyItalicSlant = 0.75f;
        this.copyright = "Copyright (c) James P. Buzbee Mar 30, 1996";
        this.H5 = 0.5f;
        this.characterVectors = new char[256][][];
        this.name = name;
        try {
            final InputStream openStream = new URL(url, name).openStream();
            this.LoadHersheyFont(name, openStream);
            openStream.close();
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    protected void calculateCharacterSize(final int n, final int n2) {
        this.characterMinX[n] = 1000;
        this.characterMaxX[n] = -1000;
        for (int i = 1; i < this.characterVectors[n][0].length; ++i) {
            if (this.characterVectors[n][0][i] != ' ') {
                final char c = this.characterVectors[n][0][i];
                final char c2 = this.characterVectors[n][1][i];
                if (c < this.characterMinX[n]) {
                    this.characterMinX[n] = c;
                }
                if (c > this.characterMaxX[n]) {
                    this.characterMaxX[n] = c;
                }
                if (c2 < this.characterSetMinY) {
                    this.characterSetMinY = c2;
                }
                if (c2 > this.characterSetMaxY) {
                    this.characterSetMaxY = c2;
                }
            }
        }
        final int[] characterMinX = this.characterMinX;
        characterMinX[n] -= n2;
        final int[] characterMaxX = this.characterMaxX;
        characterMaxX[n] += n2;
    }
    
    protected void drawCharacter(final int n, final int n2, final int n3, final int n4, final float n5, final float n6, final boolean b, final float n7, final float n8, final boolean b2, final Rectangle rectangle, final char[][] array, final int n9, final int n10, final boolean b3, final float n11, final Graphics graphics) {
        int n12 = 0;
        int n13 = 0;
        int n14 = 1;
        final float n15 = n6 * -n11;
        for (int i = 1; i < array[0].length; ++i) {
            if (array[0][i] == ' ') {
                n14 = 1;
            }
            else {
                int n16 = (int)(b3 ? ((array[1][i] - n10) * n15) : 0.0f) + (int)(n + (array[0][i] - n9) * n5);
                int n17 = (int)(n2 + (array[1][i] - n10) * n6);
                if (b) {
                    final float n18 = n16 - n3;
                    final float n19 = n17 - n4;
                    final float n20 = n18 * n8 - n19 * n7;
                    final float n21 = n18 * n7 + n19 * n8;
                    n16 = (int)(n20 + this.H5) + n3;
                    n17 = (int)(n21 + this.H5) + n4;
                }
                if (!b2) {
                    if (n16 < rectangle.x) {
                        rectangle.x = n16;
                    }
                    if (n17 < rectangle.y) {
                        rectangle.y = n17;
                    }
                    if (n16 > rectangle.width) {
                        rectangle.width = n16;
                    }
                    if (n17 > rectangle.height) {
                        rectangle.height = n17;
                    }
                }
                if (n14 == 0 && b2) {
                    this.drawFontLine(n12, n13, n16, n17, this.hersheyLineWidth, graphics);
                }
                n14 = 0;
                n12 = n16;
                n13 = n17;
            }
        }
    }
    
    protected void drawFontLine(final int n, final int n2, final int n3, final int n4, final int n5, final Graphics graphics) {
        if (n5 > 1) {
            final Polygon polygon = new Polygon();
            final int n6 = n5 >> 1;
            polygon.addPoint(n - n6, n2 + n6);
            polygon.addPoint(n + n6, n2 - n6);
            polygon.addPoint(n3 + n6, n4 - n6);
            polygon.addPoint(n3 - n6, n4 + n6);
            graphics.fillPolygon(polygon);
        }
        else {
            graphics.drawLine(n, n2, n3, n4);
        }
    }
    
    public void drawString(final String s, final int n, final int n2, final Graphics graphics) {
        this.drawText(s, n, n2, this.hersheyWidth, this.hersheyHeight, this.hersheyHorizontalAlignment, this.herhseyVerticalAlignment, this.hersheyTheta, true, new Rectangle(), graphics);
    }
    
    protected int drawText(final String s, final int n, final int n2, final float n3, final float n4, final int n5, final int n6, double n7, final boolean b, final Rectangle rectangle, final Graphics graphics) {
        int n8 = 0;
        int n9 = 0;
        float n10 = 0.0f;
        float n11 = 0.0f;
        float n12 = 0.0f;
        final boolean b2 = n7 != 0.0;
        if (b2) {
            n7 *= -0.017453292519943295;
            n10 = (float)Math.cos(n7);
            n11 = (float)Math.sin(n7);
            n8 = n;
            n9 = n2;
        }
        int n13 = n;
        if (!b) {
            rectangle.x = n13;
            rectangle.y = n2;
            rectangle.width = n13;
            rectangle.height = n2;
        }
        switch (n6) {
            case 0: {
                n12 = 0.0f;
                break;
            }
            case 1: {
                n12 = 0.5f;
                break;
            }
            case 3: {
                n12 = 1.0f;
                break;
            }
            case 2: {
                n12 = 0.25f;
                break;
            }
        }
        final int n14 = n2 - (int)(n12 * (n4 * (this.characterSetMaxY - this.characterSetMinY)));
        if (n5 != 1 && n5 != 1) {
            final int textLength = this.getTextLength(n3, s);
            if (n5 == 0) {
                n13 -= textLength >> 1;
            }
            else {
                n13 -= textLength;
            }
        }
        for (int i = 0; i < s.length(); ++i) {
            int n15 = s.charAt(i) - ' ';
            if (n15 >= this.charactersInSet) {
                if (n15 < 256) {
                    n15 -= 128;
                }
                if (n15 >= this.charactersInSet) {
                    n15 = 0;
                }
            }
            if (n15 < 0) {
                n15 = 0;
            }
            this.drawCharacter(n13, n14, n8, n9, n3, n4, b2, n11, n10, b, rectangle, this.characterVectors[n15], this.characterMinX[n15], this.characterSetMinY, this.hersheyItalics, this.hersheyItalicSlant, graphics);
            n13 += (int)((this.characterMaxX[n15] - this.characterMinX[n15]) * n3);
        }
        return 0;
    }
    
    protected int fontAdjustment(final String s) {
        int n = 0;
        if (s.toLowerCase().indexOf("scri") < 0) {
            if (s.toLowerCase().indexOf("goth") >= 0) {
                n = 2;
            }
            else {
                n = 3;
            }
        }
        return n;
    }
    
    private int getInt(final InputStream inputStream, final int n) throws IOException {
        int n2 = 0;
        final char[] array = new char[n];
        for (int i = 0; i < n; ++i) {
            int n3;
            for (n3 = inputStream.read(); n3 == 10 || n3 == 13; n3 = inputStream.read()) {}
            if (n3 == -1) {
                return n3;
            }
            if ((char)n3 != ' ') {
                array[n2++] = (char)n3;
            }
        }
        return Integer.parseInt(String.copyValueOf(array, 0, n2));
    }
    
    public String getName() {
        return this.name;
    }
    
    private int getTextLength(final float n, final String s) {
        float n2 = 0.0f;
        for (int i = 0; i < s.length(); ++i) {
            int n3 = s.charAt(i) - ' ';
            if (n3 >= this.charactersInSet) {
                if (n3 < 256) {
                    n3 -= 128;
                }
                if (n3 >= this.charactersInSet) {
                    n3 = 0;
                }
            }
            if (n3 < 0) {
                n3 = 0;
            }
            n2 += (this.characterMaxX[n3] - this.characterMinX[n3]) * n;
        }
        return (int)n2;
    }
    
    public int getTextLength(final String s) {
        return this.getTextLength(this.hersheyWidth, s);
    }
    
    private void LoadHersheyFont(final String s, final InputStream inputStream) {
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        int charactersInSet = 0;
        final int fontAdjustment = this.fontAdjustment(s);
        try {
            while (this.getInt(bufferedInputStream, 5) >= 1) {
                if (charactersInSet == this.characterVectors.length) {
                    final char[][][] characterVectors = new char[charactersInSet * 2][][];
                    System.arraycopy(this.characterVectors, 0, characterVectors, 0, charactersInSet);
                    this.characterVectors = characterVectors;
                }
                final int int1 = this.getInt(bufferedInputStream, 3);
                this.characterVectors[charactersInSet] = new char[2][int1];
                for (int i = 0; i < int1; ++i) {
                    if (i == 32 || i == 68 || i == 104 || i == 140) {
                        bufferedInputStream.read();
                    }
                    int n = bufferedInputStream.read();
                    if (n == 10) {
                        n = bufferedInputStream.read();
                    }
                    this.characterVectors[charactersInSet][0][i] = (char)n;
                    this.characterVectors[charactersInSet][1][i] = (char)bufferedInputStream.read();
                }
                bufferedInputStream.read();
                ++charactersInSet;
            }
            this.charactersInSet = charactersInSet;
            this.characterMinX = new int[this.charactersInSet];
            this.characterMaxX = new int[this.charactersInSet];
            this.characterSetMinY = 1000;
            this.characterSetMaxY = -1000;
            for (int j = 1; j < this.charactersInSet; ++j) {
                this.calculateCharacterSize(j, fontAdjustment);
            }
            if (65 <= this.charactersInSet) {
                this.characterMinX[0] = this.characterMinX[65];
                this.characterMaxX[0] = this.characterMaxX[65];
            }
            else {
                this.characterMinX[0] = this.characterMinX[this.charactersInSet - 1];
                this.characterMaxX[0] = this.characterMaxX[this.charactersInSet - 1];
            }
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public void setHeight(final float hersheyHeight) {
        this.hersheyHeight = hersheyHeight;
    }
    
    public void setHorizontalAlignment(final int hersheyHorizontalAlignment) {
        this.hersheyHorizontalAlignment = hersheyHorizontalAlignment;
    }
    
    public void setItalics(final boolean hersheyItalics) {
        this.hersheyItalics = hersheyItalics;
    }
    
    public void setItalicsSlant(final float hersheyItalicSlant) {
        this.hersheyItalicSlant = hersheyItalicSlant;
    }
    
    public void setLineWidth(final int hersheyLineWidth) {
        this.hersheyLineWidth = hersheyLineWidth;
    }
    
    public void setRotation(final double hersheyTheta) {
        this.hersheyTheta = hersheyTheta;
    }
    
    public void setVerticalAlignment(final int herhseyVerticalAlignment) {
        this.herhseyVerticalAlignment = herhseyVerticalAlignment;
    }
    
    public void setWidth(final float hersheyWidth) {
        this.hersheyWidth = hersheyWidth;
    }
    
    public Rectangle stringLimit(final String s, final int n, final int n2, final Graphics graphics) {
        final Rectangle rectangle = new Rectangle();
        this.drawText(s, n, n2, this.hersheyWidth, this.hersheyHeight, this.hersheyHorizontalAlignment, this.herhseyVerticalAlignment, this.hersheyTheta, false, rectangle, graphics);
        rectangle.width = rectangle.width - rectangle.x + 1;
        rectangle.height = rectangle.height - rectangle.y + 1;
        return rectangle;
    }
    
    public String toString() {
        return this.name;
    }
}
