// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.graphics;

import org.eclipse.swt.internal.Callback;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.win32.LOGFONTA;
import org.eclipse.swt.internal.win32.LOGFONTW;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.LOGFONT;

public final class FontData
{
    public LOGFONT data;
    public float height;
    String lang;
    String country;
    String variant;
    
    public FontData() {
        this.data = (OS.IsUnicode ? new LOGFONTW() : new LOGFONTA());
        this.data.lfCharSet = 1;
        this.height = 12.0f;
    }
    
    FontData(final LOGFONT data, final float height) {
        this.data = data;
        this.height = height;
    }
    
    public FontData(final String s) {
        if (s == null) {
            SWT.error(4);
        }
        final int n = 0;
        final int index = s.indexOf(124);
        if (index == -1) {
            SWT.error(5);
        }
        final String substring = s.substring(n, index);
        try {
            if (Integer.parseInt(substring) != 1) {
                SWT.error(5);
            }
        }
        catch (NumberFormatException ex) {
            SWT.error(5);
        }
        final int n2 = index + 1;
        final int index2 = s.indexOf(124, n2);
        if (index2 == -1) {
            SWT.error(5);
        }
        final String substring2 = s.substring(n2, index2);
        final int n3 = index2 + 1;
        final int index3 = s.indexOf(124, n3);
        if (index3 == -1) {
            SWT.error(5);
        }
        float float1 = 0.0f;
        try {
            float1 = Float.parseFloat(s.substring(n3, index3));
        }
        catch (NumberFormatException ex2) {
            SWT.error(5);
        }
        final int n4 = index3 + 1;
        final int index4 = s.indexOf(124, n4);
        if (index4 == -1) {
            SWT.error(5);
        }
        int int1 = 0;
        try {
            int1 = Integer.parseInt(s.substring(n4, index4));
        }
        catch (NumberFormatException ex3) {
            SWT.error(5);
        }
        final int n5 = index4 + 1;
        final int index5 = s.indexOf(124, n5);
        this.data = (OS.IsUnicode ? new LOGFONTW() : new LOGFONTA());
        this.data.lfCharSet = 1;
        this.setName(substring2);
        this.setHeight(float1);
        this.setStyle(int1);
        if (index5 == -1) {
            return;
        }
        final String substring3 = s.substring(n5, index5);
        final int n6 = index5 + 1;
        final int index6 = s.indexOf(124, n6);
        if (index6 == -1) {
            return;
        }
        final String substring4 = s.substring(n6, index6);
        if (substring3.equals("WINDOWS") && substring4.equals("1")) {
            final LOGFONT data = OS.IsUnicode ? new LOGFONTW() : new LOGFONTA();
            int n20;
            try {
                final int n7 = index6 + 1;
                final int index7 = s.indexOf(124, n7);
                if (index7 == -1) {
                    return;
                }
                data.lfHeight = Integer.parseInt(s.substring(n7, index7));
                final int n8 = index7 + 1;
                final int index8 = s.indexOf(124, n8);
                if (index8 == -1) {
                    return;
                }
                data.lfWidth = Integer.parseInt(s.substring(n8, index8));
                final int n9 = index8 + 1;
                final int index9 = s.indexOf(124, n9);
                if (index9 == -1) {
                    return;
                }
                data.lfEscapement = Integer.parseInt(s.substring(n9, index9));
                final int n10 = index9 + 1;
                final int index10 = s.indexOf(124, n10);
                if (index10 == -1) {
                    return;
                }
                data.lfOrientation = Integer.parseInt(s.substring(n10, index10));
                final int n11 = index10 + 1;
                final int index11 = s.indexOf(124, n11);
                if (index11 == -1) {
                    return;
                }
                data.lfWeight = Integer.parseInt(s.substring(n11, index11));
                final int n12 = index11 + 1;
                final int index12 = s.indexOf(124, n12);
                if (index12 == -1) {
                    return;
                }
                data.lfItalic = Byte.parseByte(s.substring(n12, index12));
                final int n13 = index12 + 1;
                final int index13 = s.indexOf(124, n13);
                if (index13 == -1) {
                    return;
                }
                data.lfUnderline = Byte.parseByte(s.substring(n13, index13));
                final int n14 = index13 + 1;
                final int index14 = s.indexOf(124, n14);
                if (index14 == -1) {
                    return;
                }
                data.lfStrikeOut = Byte.parseByte(s.substring(n14, index14));
                final int n15 = index14 + 1;
                final int index15 = s.indexOf(124, n15);
                if (index15 == -1) {
                    return;
                }
                data.lfCharSet = Byte.parseByte(s.substring(n15, index15));
                final int n16 = index15 + 1;
                final int index16 = s.indexOf(124, n16);
                if (index16 == -1) {
                    return;
                }
                data.lfOutPrecision = Byte.parseByte(s.substring(n16, index16));
                final int n17 = index16 + 1;
                final int index17 = s.indexOf(124, n17);
                if (index17 == -1) {
                    return;
                }
                data.lfClipPrecision = Byte.parseByte(s.substring(n17, index17));
                final int n18 = index17 + 1;
                final int index18 = s.indexOf(124, n18);
                if (index18 == -1) {
                    return;
                }
                data.lfQuality = Byte.parseByte(s.substring(n18, index18));
                final int n19 = index18 + 1;
                final int index19 = s.indexOf(124, n19);
                if (index19 == -1) {
                    return;
                }
                data.lfPitchAndFamily = Byte.parseByte(s.substring(n19, index19));
                n20 = index19 + 1;
            }
            catch (NumberFormatException ex4) {
                this.setName(substring2);
                this.setHeight(float1);
                this.setStyle(int1);
                return;
            }
            final TCHAR tchar = new TCHAR(0, s.substring(n20), false);
            final int min = Math.min(31, tchar.length());
            if (OS.IsUnicode) {
                System.arraycopy(tchar.chars, 0, ((LOGFONTW)data).lfFaceName, 0, min);
            }
            else {
                System.arraycopy(tchar.bytes, 0, ((LOGFONTA)data).lfFaceName, 0, min);
            }
            this.data = data;
        }
    }
    
    public FontData(final String name, final int height, final int style) {
        if (name == null) {
            SWT.error(4);
        }
        this.data = (OS.IsUnicode ? new LOGFONTW() : new LOGFONTA());
        this.setName(name);
        this.setHeight(height);
        this.setStyle(style);
        this.data.lfCharSet = 1;
    }
    
    FontData(final String name, final float height, final int style) {
        if (name == null) {
            SWT.error(4);
        }
        this.data = (OS.IsUnicode ? new LOGFONTW() : new LOGFONTA());
        this.setName(name);
        this.setHeight(height);
        this.setStyle(style);
        this.data.lfCharSet = 1;
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FontData)) {
            return false;
        }
        final FontData fontData = (FontData)o;
        final LOGFONT data = fontData.data;
        return this.data.lfCharSet == data.lfCharSet && this.height == fontData.height && this.data.lfWidth == data.lfWidth && this.data.lfEscapement == data.lfEscapement && this.data.lfOrientation == data.lfOrientation && this.data.lfWeight == data.lfWeight && this.data.lfItalic == data.lfItalic && this.data.lfUnderline == data.lfUnderline && this.data.lfStrikeOut == data.lfStrikeOut && this.data.lfCharSet == data.lfCharSet && this.data.lfOutPrecision == data.lfOutPrecision && this.data.lfClipPrecision == data.lfClipPrecision && this.data.lfQuality == data.lfQuality && this.data.lfPitchAndFamily == data.lfPitchAndFamily && this.getName().equals(fontData.getName());
    }
    
    int EnumLocalesProc(final int n) {
        final int n2 = 8;
        final TCHAR tchar = new TCHAR(0, n2);
        OS.MoveMemory(tchar, n, n2 * TCHAR.sizeof);
        final int int1 = Integer.parseInt(tchar.toString(0, tchar.strlen()), 16);
        final int getLocaleInfo = OS.GetLocaleInfo(int1, 89, tchar, n2);
        if (getLocaleInfo <= 0 || !this.lang.equals(tchar.toString(0, getLocaleInfo - 1))) {
            return 1;
        }
        if (this.country != null) {
            final int getLocaleInfo2 = OS.GetLocaleInfo(int1, 90, tchar, n2);
            if (getLocaleInfo2 <= 0 || !this.country.equals(tchar.toString(0, getLocaleInfo2 - 1))) {
                return 1;
            }
        }
        final int getLocaleInfo3 = OS.GetLocaleInfo(int1, 4100, tchar, n2);
        if (getLocaleInfo3 <= 0) {
            return 1;
        }
        final int int2 = Integer.parseInt(tchar.toString(0, getLocaleInfo3 - 1));
        final int[] array = new int[8];
        OS.TranslateCharsetInfo(int2, array, 2);
        this.data.lfCharSet = (byte)array[0];
        return 0;
    }
    
    public int getHeight() {
        return (int)(0.5f + this.height);
    }
    
    float getHeightF() {
        return this.height;
    }
    
    public String getLocale() {
        final StringBuffer sb = new StringBuffer();
        final char c = '_';
        if (this.lang != null) {
            sb.append(this.lang);
            sb.append(c);
        }
        if (this.country != null) {
            sb.append(this.country);
            sb.append(c);
        }
        if (this.variant != null) {
            sb.append(this.variant);
        }
        String s = sb.toString();
        final int length = s.length();
        if (length > 0 && s.charAt(length - 1) == c) {
            s = s.substring(0, length - 1);
        }
        return s;
    }
    
    public String getName() {
        char[] lfFaceName;
        if (OS.IsUnicode) {
            lfFaceName = ((LOGFONTW)this.data).lfFaceName;
        }
        else {
            lfFaceName = new char[32];
            final byte[] lfFaceName2 = ((LOGFONTA)this.data).lfFaceName;
            OS.MultiByteToWideChar(0, 1, lfFaceName2, lfFaceName2.length, lfFaceName, lfFaceName.length);
        }
        int n;
        for (n = 0; n < lfFaceName.length && lfFaceName[n] != '\0'; ++n) {}
        return new String(lfFaceName, 0, n);
    }
    
    public int getStyle() {
        int n = 0;
        if (this.data.lfWeight == 700) {
            n |= 0x1;
        }
        if (this.data.lfItalic != 0) {
            n |= 0x2;
        }
        return n;
    }
    
    public int hashCode() {
        return this.data.lfCharSet ^ this.getHeight() << 8 ^ this.data.lfWidth ^ this.data.lfEscapement ^ this.data.lfOrientation ^ this.data.lfWeight ^ this.data.lfItalic ^ this.data.lfUnderline ^ this.data.lfStrikeOut ^ this.data.lfCharSet ^ this.data.lfOutPrecision ^ this.data.lfClipPrecision ^ this.data.lfQuality ^ this.data.lfPitchAndFamily ^ this.getName().hashCode();
    }
    
    public void setHeight(final int n) {
        if (n < 0) {
            SWT.error(5);
        }
        this.height = n;
        this.data.lfWidth = 0;
    }
    
    void setHeight(final float height) {
        if (height < 0.0f) {
            SWT.error(5);
        }
        this.height = height;
    }
    
    public void setLocale(final String s) {
        final String lang = null;
        this.variant = lang;
        this.country = lang;
        this.lang = lang;
        if (s != null) {
            final int n = 95;
            final int length = s.length();
            int index = s.indexOf(n);
            int index2;
            if (index == -1) {
                index2 = (index = length);
            }
            else {
                index2 = s.indexOf(n, index + 1);
                if (index2 == -1) {
                    index2 = length;
                }
            }
            if (index > 0) {
                this.lang = s.substring(0, index);
            }
            if (index2 > index + 1) {
                this.country = s.substring(index + 1, index2);
            }
            if (length > index2 + 1) {
                this.variant = s.substring(index2 + 1);
            }
        }
        if (this.lang == null) {
            this.data.lfCharSet = 1;
        }
        else {
            final Callback callback = new Callback(this, "EnumLocalesProc", 1);
            final int address = callback.getAddress();
            if (address == 0) {
                SWT.error(3);
            }
            OS.EnumSystemLocales(address, 2);
            callback.dispose();
        }
    }
    
    public void setName(final String s) {
        if (s == null) {
            SWT.error(4);
        }
        final TCHAR tchar = new TCHAR(0, s, true);
        final int min = Math.min(31, tchar.length());
        if (OS.IsUnicode) {
            final char[] lfFaceName = ((LOGFONTW)this.data).lfFaceName;
            for (int i = 0; i < lfFaceName.length; ++i) {
                lfFaceName[i] = '\0';
            }
            System.arraycopy(tchar.chars, 0, lfFaceName, 0, min);
        }
        else {
            final byte[] lfFaceName2 = ((LOGFONTA)this.data).lfFaceName;
            for (int j = 0; j < lfFaceName2.length; ++j) {
                lfFaceName2[j] = 0;
            }
            System.arraycopy(tchar.bytes, 0, lfFaceName2, 0, min);
        }
    }
    
    public void setStyle(final int n) {
        if ((n & 0x1) == 0x1) {
            this.data.lfWeight = 700;
        }
        else {
            this.data.lfWeight = 0;
        }
        if ((n & 0x2) == 0x2) {
            this.data.lfItalic = 1;
        }
        else {
            this.data.lfItalic = 0;
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(128);
        sb.append("1|");
        final String name = this.getName();
        sb.append(name);
        sb.append("|");
        sb.append(this.getHeightF());
        sb.append("|");
        sb.append(this.getStyle());
        sb.append("|");
        sb.append("WINDOWS|1|");
        sb.append(this.data.lfHeight);
        sb.append("|");
        sb.append(this.data.lfWidth);
        sb.append("|");
        sb.append(this.data.lfEscapement);
        sb.append("|");
        sb.append(this.data.lfOrientation);
        sb.append("|");
        sb.append(this.data.lfWeight);
        sb.append("|");
        sb.append(this.data.lfItalic);
        sb.append("|");
        sb.append(this.data.lfUnderline);
        sb.append("|");
        sb.append(this.data.lfStrikeOut);
        sb.append("|");
        sb.append(this.data.lfCharSet);
        sb.append("|");
        sb.append(this.data.lfOutPrecision);
        sb.append("|");
        sb.append(this.data.lfClipPrecision);
        sb.append("|");
        sb.append(this.data.lfQuality);
        sb.append("|");
        sb.append(this.data.lfPitchAndFamily);
        sb.append("|");
        sb.append(name);
        return sb.toString();
    }
    
    public static FontData win32_new(final LOGFONT logfont, final float n) {
        return new FontData(logfont, n);
    }
}
