// 
// Decompiled by Procyon v0.5.30
// 

public class TextDrawingArea2 extends DrawingArea
{
    public int baseCharacterHeight;
    public int anInt4142;
    public int anInt4144;
    public int[] characterDrawYOffsets;
    public int[] characterHeights;
    public int[] characterDrawXOffsets;
    public int[] characterWidths;
    public int[] iconWidths;
    public byte[] aByteArray4151;
    public byte[][] fontPixels;
    public int[] characterScreenWidths;
    public Sprite[] chatImages;
    public static String aRSString_4135;
    public static String startTransparency;
    public static String startDefaultShadow;
    public static String endShadow;
    public static String endEffect;
    public static String aRSString_4143;
    public static String endStrikethrough;
    public static String aRSString_4147;
    public static String startColor;
    public static String lineBreak;
    public static String startStrikethrough;
    public static String endColor;
    public static String startImage;
    public static String endUnderline;
    public static String defaultStrikethrough;
    public static String startShadow;
    public static String startEffect;
    public static String aRSString_4162;
    public static String aRSString_4163;
    public static String endTransparency;
    public static String aRSString_4165;
    public static String startUnderline;
    public static String startDefaultUnderline;
    public static String aRSString_4169;
    public static String[] splitTextStrings;
    public static int defaultColor;
    public static int textShadowColor;
    public static int strikethroughColor;
    public static int defaultTransparency;
    public static int anInt4175;
    public static int underlineColor;
    public static int defaultShadow;
    public static int anInt4178;
    public static int transparency;
    public static int textColor;
    
    public TextDrawingArea2(final boolean b, final String s, final StreamLoader streamLoader) {
        this.baseCharacterHeight = 0;
        this.fontPixels = new byte[256][];
        this.characterWidths = new int[256];
        this.characterHeights = new int[256];
        this.characterDrawXOffsets = new int[256];
        this.characterDrawYOffsets = new int[256];
        this.characterScreenWidths = new int[256];
        final Stream stream = new Stream(streamLoader.getDataForName(s + ".dat"));
        final Stream stream2 = new Stream(streamLoader.getDataForName("index.dat"));
        stream2.currentOffset = stream.readSignedWord() + 4;
        final int unsignedByte = stream2.readUnsignedByte();
        if (unsignedByte > 0) {
            final Stream stream3 = stream2;
            stream3.currentOffset += 3 * (unsignedByte - 1);
        }
        for (int i = 0; i < 256; ++i) {
            this.characterDrawXOffsets[i] = stream2.readUnsignedByte();
            this.characterDrawYOffsets[i] = stream2.readUnsignedByte();
            final int[] characterWidths = this.characterWidths;
            final int n = i;
            final int signedWord = stream2.readSignedWord();
            characterWidths[n] = signedWord;
            final int n2 = signedWord;
            final int[] characterHeights = this.characterHeights;
            final int n3 = i;
            final int signedWord2 = stream2.readSignedWord();
            characterHeights[n3] = signedWord2;
            final int baseCharacterHeight = signedWord2;
            final int unsignedByte2 = stream2.readUnsignedByte();
            final int n4 = n2 * baseCharacterHeight;
            this.fontPixels[i] = new byte[n4];
            if (unsignedByte2 == 0) {
                for (int j = 0; j < n4; ++j) {
                    this.fontPixels[i][j] = stream.readSignedByte();
                }
            }
            else if (unsignedByte2 == 1) {
                for (int k = 0; k < n2; ++k) {
                    for (int l = 0; l < baseCharacterHeight; ++l) {
                        this.fontPixels[i][k + l * n2] = stream.readSignedByte();
                    }
                }
            }
            if (baseCharacterHeight > this.baseCharacterHeight && i < 128) {
                this.baseCharacterHeight = baseCharacterHeight;
            }
            this.characterDrawXOffsets[i] = 1;
            this.characterScreenWidths[i] = n2 + 2;
            byte b2 = 0;
            for (int n5 = baseCharacterHeight / 7; n5 < baseCharacterHeight; ++n5) {
                b2 += this.fontPixels[i][n5 * n2];
            }
            if (b2 <= baseCharacterHeight / 7) {
                final int[] characterScreenWidths = this.characterScreenWidths;
                final int n6 = i;
                --characterScreenWidths[n6];
                this.characterDrawXOffsets[i] = 0;
            }
            byte b3 = 0;
            for (int n7 = baseCharacterHeight / 7; n7 < baseCharacterHeight; ++n7) {
                b3 += this.fontPixels[i][n2 - 1 + n7 * n2];
            }
            if (b3 <= baseCharacterHeight / 7) {
                final int[] characterScreenWidths2 = this.characterScreenWidths;
                final int n8 = i;
                --characterScreenWidths2[n8];
            }
        }
        if (b) {
            this.characterScreenWidths[32] = this.characterScreenWidths[73];
        }
        else {
            this.characterScreenWidths[32] = this.characterScreenWidths[105];
        }
    }
    
    public void drawStringMoveY(final String s, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        if (s != null) {
            this.setColorAndShadow(n3, n4);
            double n7 = 7.0 - n6 / 8.0;
            if (n7 < 0.0) {
                n7 = 0.0;
            }
            final int[] array = new int[s.length()];
            for (int i = 0; i < s.length(); ++i) {
                array[i] = (int)(Math.sin(i / 1.5 + n5) * n7);
            }
            this.drawBaseStringMoveXY(s, n - this.getTextWidth(s) / 2, n2, null, array);
        }
    }
    
    public int getCharacterWidth(final int n) {
        return this.characterScreenWidths[n & 0xFF];
    }
    
    public void setTrans(final int n, final int n2, final int n3) {
        TextDrawingArea2.defaultShadow = n;
        TextDrawingArea2.textShadowColor = n;
        TextDrawingArea2.defaultColor = n2;
        TextDrawingArea2.textColor = n2;
        TextDrawingArea2.defaultTransparency = n3;
        TextDrawingArea2.transparency = n3;
    }
    
    public void setDefaultTextEffectValues(final int n, final int n2, final int n3) {
        TextDrawingArea2.strikethroughColor = -1;
        TextDrawingArea2.underlineColor = -1;
        TextDrawingArea2.defaultShadow = n2;
        TextDrawingArea2.textShadowColor = n2;
        TextDrawingArea2.defaultColor = n;
        TextDrawingArea2.textColor = n;
        TextDrawingArea2.defaultTransparency = n3;
        TextDrawingArea2.transparency = n3;
        TextDrawingArea2.anInt4178 = 0;
        TextDrawingArea2.anInt4175 = 0;
    }
    
    public static int method1014(final byte[][] array, final byte[][] array2, final int[] array3, final int[] array4, final int[] array5, final int n, final int n2) {
        final int n3 = array3[n];
        final int n4 = n3 + array5[n];
        final int n5 = array3[n2];
        final int n6 = n5 + array5[n2];
        int n7 = n3;
        if (n5 > n3) {
            n7 = n5;
        }
        int n8;
        if (n6 < (n8 = n4)) {
            n8 = n6;
        }
        int n9 = array4[n];
        if (array4[n2] < n9) {
            n9 = array4[n2];
        }
        final byte[] array6 = array2[n];
        final byte[] array7 = array[n2];
        int n10 = n7 - n3;
        int n11 = n7 - n5;
        for (int i = n7; i < n8; ++i) {
            final byte b = (byte)(array6[n10++] + array7[n11++]);
            if (b < n9) {
                n9 = b;
            }
        }
        return -n9;
    }
    
    public void drawCenteredStringMoveXY(final String s, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (s != null) {
            this.setColorAndShadow(n3, n4);
            final int[] array = new int[s.length()];
            final int[] array2 = new int[s.length()];
            for (int i = 0; i < s.length(); ++i) {
                array[i] = (int)(Math.sin(i / 5.0 + n5 / 5.0) * 5.0);
                array2[i] = (int)(Math.sin(i / 3.0 + n5 / 5.0) * 5.0);
            }
            this.drawBaseStringMoveXY(s, n - this.getTextWidth(s) / 2, n2, array, array2);
        }
    }
    
    public void drawCenteredStringMoveY(final String s, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (s != null) {
            this.setColorAndShadow(n3, n4);
            final int[] array = new int[s.length()];
            for (int i = 0; i < s.length(); ++i) {
                array[i] = (int)(Math.sin(i / 2.0 + n5 / 5.0) * 5.0);
            }
            this.drawBaseStringMoveXY(s, n - this.getTextWidth(s) / 2, n2, null, array);
        }
    }
    
    public void unpackChatImages(final Sprite[] chatImages) {
        this.chatImages = chatImages;
    }
    
    public void drawBasicString(final String s, int n, int n2) {
        n2 -= this.baseCharacterHeight;
        int n3 = -1;
        for (int i = 0; i < s.length(); ++i) {
            int char1 = s.charAt(i);
            if (char1 > 255) {
                char1 = 32;
            }
            if (char1 == 60) {
                n3 = i;
            }
            else {
                if (char1 == 62 && n3 != -1) {
                    final String substring = s.substring(n3 + 1, i);
                    n3 = -1;
                    if (substring.equals(TextDrawingArea2.startEffect)) {
                        char1 = 60;
                    }
                    else if (substring.equals(TextDrawingArea2.endEffect)) {
                        char1 = 62;
                    }
                    else if (substring.equals(TextDrawingArea2.aRSString_4135)) {
                        char1 = 160;
                    }
                    else if (substring.equals(TextDrawingArea2.aRSString_4162)) {
                        char1 = 173;
                    }
                    else if (substring.equals(TextDrawingArea2.aRSString_4165)) {
                        char1 = 215;
                    }
                    else if (substring.equals(TextDrawingArea2.aRSString_4147)) {
                        char1 = 128;
                    }
                    else if (substring.equals(TextDrawingArea2.aRSString_4163)) {
                        char1 = 169;
                    }
                    else if (substring.equals(TextDrawingArea2.aRSString_4169)) {
                        char1 = 174;
                    }
                    else {
                        if (substring.startsWith(TextDrawingArea2.startImage)) {
                            try {
                                final Sprite sprite = this.chatImages[Integer.valueOf(substring.substring(4))];
                                final int anInt1445 = sprite.anInt1445;
                                if (TextDrawingArea2.transparency == 256) {
                                    sprite.drawSprite(n, n2 + this.baseCharacterHeight - anInt1445);
                                }
                                else {
                                    sprite.drawSpriteOpacity(n, n2 + this.baseCharacterHeight - anInt1445, TextDrawingArea2.transparency);
                                }
                                n += sprite.anInt1444;
                            }
                            catch (Exception ex) {}
                            continue;
                        }
                        this.setTextEffects(substring);
                        continue;
                    }
                }
                if (n3 == -1) {
                    final int n4 = this.characterWidths[char1];
                    final int n5 = this.characterHeights[char1];
                    if (char1 != 32) {
                        if (TextDrawingArea2.transparency == 256) {
                            if (TextDrawingArea2.textShadowColor != -1) {
                                this.drawCharacter(char1, n + this.characterDrawXOffsets[char1] + 1, n2 + this.characterDrawYOffsets[char1] + 1, n4, n5, TextDrawingArea2.textShadowColor, true);
                            }
                            this.drawCharacter(char1, n + this.characterDrawXOffsets[char1], n2 + this.characterDrawYOffsets[char1], n4, n5, TextDrawingArea2.textColor, false);
                        }
                        else {
                            if (TextDrawingArea2.textShadowColor != -1) {
                                this.drawTransparentCharacter(char1, n + this.characterDrawXOffsets[char1] + 1, n2 + this.characterDrawYOffsets[char1] + 1, n4, n5, TextDrawingArea2.textShadowColor, TextDrawingArea2.transparency, true);
                            }
                            this.drawTransparentCharacter(char1, n + this.characterDrawXOffsets[char1], n2 + this.characterDrawYOffsets[char1], n4, n5, TextDrawingArea2.textColor, TextDrawingArea2.transparency, false);
                        }
                    }
                    else if (TextDrawingArea2.anInt4178 > 0) {
                        TextDrawingArea2.anInt4175 += TextDrawingArea2.anInt4178;
                        n += TextDrawingArea2.anInt4175 >> 8;
                        TextDrawingArea2.anInt4175 &= 0xFF;
                    }
                    final int n6 = this.characterScreenWidths[char1];
                    if (TextDrawingArea2.strikethroughColor != -1) {
                        rsDrawingArea.drawHorizontalLine(n, n2 + (int)(this.baseCharacterHeight * 0.7), n6, TextDrawingArea2.strikethroughColor);
                    }
                    if (TextDrawingArea2.underlineColor != -1) {
                        rsDrawingArea.drawHorizontalLine(n, n2 + this.baseCharacterHeight, n6, TextDrawingArea2.underlineColor);
                    }
                    n += n6;
                }
            }
        }
    }
    
    public void drawBasicString(final boolean b, final boolean b2, final String s, int n, int n2) {
        n2 -= this.baseCharacterHeight;
        int n3 = -1;
        if (b2) {
            n -= (b ? 9 : 6);
        }
        for (int i = 0; i < s.length(); ++i) {
            int char1 = s.charAt(i);
            if (char1 > 255) {
                char1 = 32;
            }
            if (char1 == 60) {
                n3 = i;
            }
            else {
                if (char1 == 62 && n3 != -1) {
                    final String substring = s.substring(n3 + 1, i);
                    n3 = -1;
                    if (substring.equals(TextDrawingArea2.startEffect)) {
                        char1 = 60;
                    }
                    else if (substring.equals(TextDrawingArea2.endEffect)) {
                        char1 = 62;
                    }
                    else if (substring.equals(TextDrawingArea2.aRSString_4135)) {
                        char1 = 160;
                    }
                    else if (substring.equals(TextDrawingArea2.aRSString_4162)) {
                        char1 = 173;
                    }
                    else if (substring.equals(TextDrawingArea2.aRSString_4165)) {
                        char1 = 215;
                    }
                    else if (substring.equals(TextDrawingArea2.aRSString_4147)) {
                        char1 = 128;
                    }
                    else if (substring.equals(TextDrawingArea2.aRSString_4163)) {
                        char1 = 169;
                    }
                    else if (substring.equals(TextDrawingArea2.aRSString_4169)) {
                        char1 = 174;
                    }
                    else {
                        if (substring.startsWith(TextDrawingArea2.startImage)) {
                            try {
                                final Sprite sprite = this.chatImages[Integer.valueOf(substring.substring(4))];
                                final int anInt1445 = sprite.anInt1445;
                                if (TextDrawingArea2.transparency == 256) {
                                    sprite.drawSprite(n, n2 + this.baseCharacterHeight - anInt1445);
                                }
                                else {
                                    sprite.drawSpriteOpacity(n, n2 + this.baseCharacterHeight - anInt1445, TextDrawingArea2.transparency);
                                }
                                n += sprite.anInt1444;
                            }
                            catch (Exception ex) {}
                            continue;
                        }
                        this.setTextEffects(substring);
                        continue;
                    }
                }
                if (n3 == -1) {
                    final int n4 = this.characterWidths[char1];
                    final int n5 = this.characterHeights[char1];
                    if (char1 != 32) {
                        if (TextDrawingArea2.transparency == 256) {
                            if (TextDrawingArea2.textShadowColor != -1) {
                                this.drawCharacter(char1, n + this.characterDrawXOffsets[char1] + 1, n2 + this.characterDrawYOffsets[char1] + 1, n4, n5, TextDrawingArea2.textShadowColor, true);
                            }
                            this.drawCharacter(char1, n + this.characterDrawXOffsets[char1], n2 + this.characterDrawYOffsets[char1], n4, n5, TextDrawingArea2.textColor, false);
                        }
                        else {
                            if (TextDrawingArea2.textShadowColor != -1) {
                                this.drawTransparentCharacter(char1, n + this.characterDrawXOffsets[char1] + 1, n2 + this.characterDrawYOffsets[char1] + 1, n4, n5, TextDrawingArea2.textShadowColor, TextDrawingArea2.transparency, true);
                            }
                            this.drawTransparentCharacter(char1, n + this.characterDrawXOffsets[char1], n2 + this.characterDrawYOffsets[char1], n4, n5, TextDrawingArea2.textColor, TextDrawingArea2.transparency, false);
                        }
                    }
                    else if (TextDrawingArea2.anInt4178 > 0) {
                        TextDrawingArea2.anInt4175 += TextDrawingArea2.anInt4178;
                        n += TextDrawingArea2.anInt4175 >> 8;
                        TextDrawingArea2.anInt4175 &= 0xFF;
                    }
                    final int n6 = this.characterScreenWidths[char1];
                    if (TextDrawingArea2.strikethroughColor != -1) {
                        rsDrawingArea.drawHorizontalLine(n, n2 + (int)(this.baseCharacterHeight * 0.7), n6, TextDrawingArea2.strikethroughColor);
                    }
                    if (TextDrawingArea2.underlineColor != -1) {
                        rsDrawingArea.drawHorizontalLine(n, n2 + this.baseCharacterHeight, n6, TextDrawingArea2.underlineColor);
                    }
                    n += n6;
                }
            }
        }
    }
    
    public void drawRAString(final String s, final int n, final int n2, final int n3, final int n4) {
        if (s != null) {
            this.setColorAndShadow(n3, n4);
            this.drawBasicString(s, n - this.getTextWidth(s), n2);
        }
    }
    
    public void drawBaseStringMoveXY(final String s, int n, int n2, final int[] array, final int[] array2) {
        n2 -= this.baseCharacterHeight;
        int n3 = -1;
        int n4 = 0;
        for (int i = 0; i < s.length(); ++i) {
            int char1 = s.charAt(i);
            if (char1 == 60) {
                n3 = i;
            }
            else {
                if (char1 == 62 && n3 != -1) {
                    final String substring = s.substring(n3 + 1, i);
                    n3 = -1;
                    if (substring.equals(TextDrawingArea2.startEffect)) {
                        char1 = 60;
                    }
                    else if (substring.equals(TextDrawingArea2.endEffect)) {
                        char1 = 62;
                    }
                    else if (substring.equals(TextDrawingArea2.aRSString_4135)) {
                        char1 = 160;
                    }
                    else if (substring.equals(TextDrawingArea2.aRSString_4162)) {
                        char1 = 173;
                    }
                    else if (substring.equals(TextDrawingArea2.aRSString_4165)) {
                        char1 = 215;
                    }
                    else if (substring.equals(TextDrawingArea2.aRSString_4147)) {
                        char1 = 128;
                    }
                    else if (substring.equals(TextDrawingArea2.aRSString_4163)) {
                        char1 = 169;
                    }
                    else if (substring.equals(TextDrawingArea2.aRSString_4169)) {
                        char1 = 174;
                    }
                    else {
                        if (substring.startsWith(TextDrawingArea2.startImage)) {
                            try {
                                int n5;
                                if (array != null) {
                                    n5 = array[n4];
                                }
                                else {
                                    n5 = 0;
                                }
                                int n6;
                                if (array2 != null) {
                                    n6 = array2[n4];
                                }
                                else {
                                    n6 = 0;
                                }
                                ++n4;
                                final Sprite sprite = this.chatImages[Integer.valueOf(substring.substring(4))];
                                final int anInt1445 = sprite.anInt1445;
                                if (TextDrawingArea2.transparency == 256) {
                                    sprite.drawSprite(n + n5, n2 + this.baseCharacterHeight - anInt1445 + n6);
                                }
                                else {
                                    sprite.drawSpriteOpacity(n + n5, n2 + this.baseCharacterHeight - anInt1445 + n6, TextDrawingArea2.transparency);
                                }
                                n += sprite.anInt1444;
                            }
                            catch (Exception ex) {}
                            continue;
                        }
                        this.setTextEffects(substring);
                        continue;
                    }
                }
                if (n3 == -1) {
                    final int n7 = this.characterWidths[char1];
                    final int n8 = this.characterHeights[char1];
                    int n9;
                    if (array != null) {
                        n9 = array[n4];
                    }
                    else {
                        n9 = 0;
                    }
                    int n10;
                    if (array2 != null) {
                        n10 = array2[n4];
                    }
                    else {
                        n10 = 0;
                    }
                    ++n4;
                    if (char1 != 32) {
                        if (TextDrawingArea2.transparency == 256) {
                            if (TextDrawingArea2.textShadowColor != -1) {
                                this.drawCharacter(char1, n + this.characterDrawXOffsets[char1] + 1 + n9, n2 + this.characterDrawYOffsets[char1] + 1 + n10, n7, n8, TextDrawingArea2.textShadowColor, true);
                            }
                            this.drawCharacter(char1, n + this.characterDrawXOffsets[char1] + n9, n2 + this.characterDrawYOffsets[char1] + n10, n7, n8, TextDrawingArea2.textColor, false);
                        }
                        else {
                            if (TextDrawingArea2.textShadowColor != -1) {
                                this.drawTransparentCharacter(char1, n + this.characterDrawXOffsets[char1] + 1 + n9, n2 + this.characterDrawYOffsets[char1] + 1 + n10, n7, n8, TextDrawingArea2.textShadowColor, TextDrawingArea2.transparency, true);
                            }
                            this.drawTransparentCharacter(char1, n + this.characterDrawXOffsets[char1] + n9, n2 + this.characterDrawYOffsets[char1] + n10, n7, n8, TextDrawingArea2.textColor, TextDrawingArea2.transparency, false);
                        }
                    }
                    else if (TextDrawingArea2.anInt4178 > 0) {
                        TextDrawingArea2.anInt4175 += TextDrawingArea2.anInt4178;
                        n += TextDrawingArea2.anInt4175 >> 8;
                        TextDrawingArea2.anInt4175 &= 0xFF;
                    }
                    final int n11 = this.characterScreenWidths[char1];
                    if (TextDrawingArea2.strikethroughColor != -1) {
                        rsDrawingArea.drawHorizontalLine(n, n2 + (int)(this.baseCharacterHeight * 0.7), n11, TextDrawingArea2.strikethroughColor);
                    }
                    if (TextDrawingArea2.underlineColor != -1) {
                        rsDrawingArea.drawHorizontalLine(n, n2 + this.baseCharacterHeight, n11, TextDrawingArea2.underlineColor);
                    }
                    n += n11;
                }
            }
        }
    }
    
    public void setTextEffects(final String s) {
        try {
            if (s.startsWith(TextDrawingArea2.startColor)) {
                TextDrawingArea2.textColor = Integer.valueOf(s.substring(4));
            }
            else if (s.equals(TextDrawingArea2.endColor)) {
                TextDrawingArea2.textColor = TextDrawingArea2.defaultColor;
            }
            else if (s.startsWith(TextDrawingArea2.startTransparency)) {
                TextDrawingArea2.transparency = Integer.valueOf(s.substring(6));
            }
            else if (s.equals(TextDrawingArea2.endTransparency)) {
                TextDrawingArea2.transparency = TextDrawingArea2.defaultTransparency;
            }
            else if (s.startsWith(TextDrawingArea2.startStrikethrough)) {
                TextDrawingArea2.strikethroughColor = Integer.valueOf(s.substring(4));
            }
            else if (s.equals(TextDrawingArea2.defaultStrikethrough)) {
                TextDrawingArea2.strikethroughColor = 8388608;
            }
            else if (s.equals(TextDrawingArea2.endStrikethrough)) {
                TextDrawingArea2.strikethroughColor = -1;
            }
            else if (s.startsWith(TextDrawingArea2.startUnderline)) {
                TextDrawingArea2.underlineColor = Integer.valueOf(s.substring(2));
            }
            else if (s.equals(TextDrawingArea2.startDefaultUnderline)) {
                TextDrawingArea2.underlineColor = 0;
            }
            else if (s.equals(TextDrawingArea2.endUnderline)) {
                TextDrawingArea2.underlineColor = -1;
            }
            else if (s.startsWith(TextDrawingArea2.startShadow)) {
                TextDrawingArea2.textShadowColor = Integer.valueOf(s.substring(5));
            }
            else if (s.equals(TextDrawingArea2.startDefaultShadow)) {
                TextDrawingArea2.textShadowColor = 0;
            }
            else if (s.equals(TextDrawingArea2.endShadow)) {
                TextDrawingArea2.textShadowColor = TextDrawingArea2.defaultShadow;
            }
            else if (s.equals(TextDrawingArea2.lineBreak)) {
                this.setDefaultTextEffectValues(TextDrawingArea2.defaultColor, TextDrawingArea2.defaultShadow, TextDrawingArea2.defaultTransparency);
            }
        }
        catch (Exception ex) {}
    }
    
    public void setColorAndShadow(final int n, final int n2) {
        TextDrawingArea2.strikethroughColor = -1;
        TextDrawingArea2.underlineColor = -1;
        TextDrawingArea2.defaultShadow = n2;
        TextDrawingArea2.textShadowColor = n2;
        TextDrawingArea2.defaultColor = n;
        TextDrawingArea2.textColor = n;
        TextDrawingArea2.transparency = (TextDrawingArea2.defaultTransparency = 256);
        TextDrawingArea2.anInt4178 = 0;
        TextDrawingArea2.anInt4175 = 0;
    }
    
    public int getTextWidth(final String s) {
        if (s == null) {
            return 0;
        }
        int n = -1;
        int n2 = 0;
        for (int i = 0; i < s.length(); ++i) {
            int char1 = s.charAt(i);
            if (char1 > 255) {
                char1 = 32;
            }
            if (char1 == 60) {
                n = i;
            }
            else {
                if (char1 == 62 && n != -1) {
                    final String substring = s.substring(n + 1, i);
                    n = -1;
                    if (substring.equals(TextDrawingArea2.startEffect)) {
                        char1 = 60;
                    }
                    else if (substring.equals(TextDrawingArea2.endEffect)) {
                        char1 = 62;
                    }
                    else if (substring.equals(TextDrawingArea2.aRSString_4135)) {
                        char1 = 160;
                    }
                    else if (substring.equals(TextDrawingArea2.aRSString_4162)) {
                        char1 = 173;
                    }
                    else if (substring.equals(TextDrawingArea2.aRSString_4165)) {
                        char1 = 215;
                    }
                    else if (substring.equals(TextDrawingArea2.aRSString_4147)) {
                        char1 = 128;
                    }
                    else if (substring.equals(TextDrawingArea2.aRSString_4163)) {
                        char1 = 169;
                    }
                    else if (substring.equals(TextDrawingArea2.aRSString_4169)) {
                        char1 = 174;
                    }
                    else {
                        if (substring.startsWith(TextDrawingArea2.startImage)) {
                            try {
                                n2 += this.chatImages[Integer.valueOf(substring.substring(4))].anInt1444;
                            }
                            catch (Exception ex) {}
                        }
                        continue;
                    }
                }
                if (n == -1) {
                    n2 += this.characterScreenWidths[char1];
                }
            }
        }
        return n2;
    }
    
    public void drawBasicString(final String s, final int n, final int n2, final int n3, final int n4) {
        if (s != null) {
            this.setColorAndShadow(n3, n4);
            this.drawBasicString(s, n, n2);
        }
    }
    
    public void drawCenteredString(final String s, final int n, final int n2) {
        if (s != null) {
            this.drawBasicString(s, n - this.getTextWidth(s) / 2, n2);
        }
    }
    
    public void drawCenteredString(final String s, final int n, final int n2, final int n3, final int n4) {
        if (s != null) {
            this.setColorAndShadow(n3, n4);
            this.drawBasicString(s, n - this.getTextWidth(s) / 2, n2);
        }
    }
    
    public static void nullLoader() {
        TextDrawingArea2.startEffect = null;
        TextDrawingArea2.endEffect = null;
        TextDrawingArea2.aRSString_4135 = null;
        TextDrawingArea2.aRSString_4162 = null;
        TextDrawingArea2.aRSString_4165 = null;
        TextDrawingArea2.aRSString_4147 = null;
        TextDrawingArea2.aRSString_4163 = null;
        TextDrawingArea2.aRSString_4169 = null;
        TextDrawingArea2.startImage = null;
        TextDrawingArea2.lineBreak = null;
        TextDrawingArea2.startColor = null;
        TextDrawingArea2.endColor = null;
        TextDrawingArea2.startTransparency = null;
        TextDrawingArea2.endTransparency = null;
        TextDrawingArea2.startUnderline = null;
        TextDrawingArea2.startDefaultUnderline = null;
        TextDrawingArea2.endUnderline = null;
        TextDrawingArea2.startShadow = null;
        TextDrawingArea2.startDefaultShadow = null;
        TextDrawingArea2.endShadow = null;
        TextDrawingArea2.startStrikethrough = null;
        TextDrawingArea2.defaultStrikethrough = null;
        TextDrawingArea2.endStrikethrough = null;
        TextDrawingArea2.aRSString_4143 = null;
        TextDrawingArea2.splitTextStrings = null;
    }
    
    public static void createTransparentCharacterPixels(final int[] array, final byte[] array2, int n, int n2, int n3, final int n4, final int n5, final int n6, final int n7, int n8) {
        n = ((n & 0xFF00FF) * n8 & 0xFF00FF00) + ((n & 0xFF00) * n8 & 0xFF0000) >> 8;
        n8 = 256 - n8;
        for (int i = -n5; i < 0; ++i) {
            for (int j = -n4; j < 0; ++j) {
                if (array2[n2++] != 0) {
                    final int n9 = array[n3];
                    array[n3++] = (((n9 & 0xFF00FF) * n8 & 0xFF00FF00) + ((n9 & 0xFF00) * n8 & 0xFF0000) >> 8) + n;
                }
                else {
                    ++n3;
                }
            }
            n3 += n6;
            n2 += n7;
        }
    }
    
    public void drawTransparentCharacter(final int n, int topX, int topY, int n2, int n3, final int n4, final int n5, final boolean b) {
        int n6 = topX + topY * DrawingArea.width;
        int n7 = DrawingArea.width - n2;
        int n8 = 0;
        int n9 = 0;
        if (topY < DrawingArea.topY) {
            final int n10 = DrawingArea.topY - topY;
            n3 -= n10;
            topY = DrawingArea.topY;
            n9 += n10 * n2;
            n6 += n10 * DrawingArea.width;
        }
        if (topY + n3 > DrawingArea.bottomY) {
            n3 -= topY + n3 - DrawingArea.bottomY;
        }
        if (topX < DrawingArea.topX) {
            final int n11 = DrawingArea.topX - topX;
            n2 -= n11;
            topX = DrawingArea.topX;
            n9 += n11;
            n6 += n11;
            n8 += n11;
            n7 += n11;
        }
        if (topX + n2 > DrawingArea.bottomX) {
            final int n12 = topX + n2 - DrawingArea.bottomX;
            n2 -= n12;
            n8 += n12;
            n7 += n12;
        }
        if (n2 > 0 && n3 > 0) {
            createTransparentCharacterPixels(DrawingArea.pixels, this.fontPixels[n], n4, n9, n6, n2, n3, n7, n8, n5);
        }
    }
    
    public static void createCharacterPixels(final int[] array, final byte[] array2, final int n, int n2, int n3, int n4, final int n5, final int n6, final int n7) {
        final int n8 = -(n4 >> 2);
        n4 = -(n4 & 0x3);
        for (int i = -n5; i < 0; ++i) {
            for (int j = n8; j < 0; ++j) {
                if (array2[n2++] != 0) {
                    array[n3++] = n;
                }
                else {
                    ++n3;
                }
                if (array2[n2++] != 0) {
                    array[n3++] = n;
                }
                else {
                    ++n3;
                }
                if (array2[n2++] != 0) {
                    array[n3++] = n;
                }
                else {
                    ++n3;
                }
                if (array2[n2++] != 0) {
                    array[n3++] = n;
                }
                else {
                    ++n3;
                }
            }
            for (int k = n4; k < 0; ++k) {
                if (array2[n2++] != 0) {
                    array[n3++] = n;
                }
                else {
                    ++n3;
                }
            }
            n3 += n6;
            n2 += n7;
        }
    }
    
    public void drawCharacter(final int n, int topX, int topY, int n2, int n3, final int n4, final boolean b) {
        int n5 = topX + topY * DrawingArea.width;
        int n6 = DrawingArea.width - n2;
        int n7 = 0;
        int n8 = 0;
        if (topY < DrawingArea.topY) {
            final int n9 = DrawingArea.topY - topY;
            n3 -= n9;
            topY = DrawingArea.topY;
            n8 += n9 * n2;
            n5 += n9 * DrawingArea.width;
        }
        if (topY + n3 > DrawingArea.bottomY) {
            n3 -= topY + n3 - DrawingArea.bottomY;
        }
        if (topX < DrawingArea.topX) {
            final int n10 = DrawingArea.topX - topX;
            n2 -= n10;
            topX = DrawingArea.topX;
            n8 += n10;
            n5 += n10;
            n7 += n10;
            n6 += n10;
        }
        if (topX + n2 > DrawingArea.bottomX) {
            final int n11 = topX + n2 - DrawingArea.bottomX;
            n2 -= n11;
            n7 += n11;
            n6 += n11;
        }
        if (n2 > 0 && n3 > 0) {
            createCharacterPixels(DrawingArea.pixels, this.fontPixels[n], n4, n8, n5, n2, n3, n6, n7);
        }
    }
    
    static {
        TextDrawingArea2.endShadow = "/shad";
        TextDrawingArea2.endStrikethrough = "/str";
        TextDrawingArea2.startTransparency = "trans=";
        TextDrawingArea2.startStrikethrough = "str=";
        TextDrawingArea2.startDefaultShadow = "shad";
        TextDrawingArea2.startColor = "col=";
        TextDrawingArea2.lineBreak = "br";
        TextDrawingArea2.defaultStrikethrough = "str";
        TextDrawingArea2.endUnderline = "/u";
        TextDrawingArea2.startImage = "img=";
        TextDrawingArea2.startShadow = "shad=";
        TextDrawingArea2.startUnderline = "u=";
        TextDrawingArea2.endColor = "/col";
        TextDrawingArea2.startDefaultUnderline = "u";
        TextDrawingArea2.endTransparency = "/trans";
        TextDrawingArea2.aRSString_4143 = Integer.toString(100);
        TextDrawingArea2.aRSString_4135 = "nbsp";
        TextDrawingArea2.aRSString_4169 = "reg";
        TextDrawingArea2.aRSString_4165 = "times";
        TextDrawingArea2.aRSString_4162 = "shy";
        TextDrawingArea2.aRSString_4163 = "copy";
        TextDrawingArea2.endEffect = "gt";
        TextDrawingArea2.aRSString_4147 = "euro";
        TextDrawingArea2.startEffect = "lt";
        TextDrawingArea2.defaultTransparency = 256;
        TextDrawingArea2.defaultShadow = -1;
        TextDrawingArea2.anInt4175 = 0;
        TextDrawingArea2.textShadowColor = -1;
        TextDrawingArea2.textColor = 0;
        TextDrawingArea2.defaultColor = 0;
        TextDrawingArea2.strikethroughColor = -1;
        TextDrawingArea2.splitTextStrings = new String[100];
        TextDrawingArea2.underlineColor = -1;
        TextDrawingArea2.anInt4178 = 0;
        TextDrawingArea2.transparency = 256;
    }
}
