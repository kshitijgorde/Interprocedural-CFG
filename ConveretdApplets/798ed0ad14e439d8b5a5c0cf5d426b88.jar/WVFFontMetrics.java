import java.io.InputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.awt.Font;
import java.util.Hashtable;
import java.awt.FontMetrics;

// 
// Decompiled by Procyon v0.5.30
// 

public class WVFFontMetrics extends FontMetrics
{
    static Hashtable append;
    WVFFontMetricsByStyle ascent;
    int descent;
    private ResourceProvider resourceProvider;
    
    public WVFFontMetrics(final Font font, final String s, final float n, final ResourceProvider resourceProvider) {
        super(font);
        this.descent = (int)(font.getSize() * n);
        this.resourceProvider = resourceProvider;
        this.ascent = request_font_metrics(fontToFileName(s, font), s, resourceProvider);
    }
    
    private static WVFFontMetricsByStyle request_font_metrics(final String s, final String s2, final ResourceProvider resourceProvider) {
        WVFFontMetricsByStyle new_font_metrics = WVFFontMetrics.append.get(s);
        if (new_font_metrics == null) {
            new_font_metrics = new_font_metrics(s, s2, resourceProvider);
            WVFFontMetrics.append.put(s, new_font_metrics);
        }
        return new_font_metrics;
    }
    
    private static WVFFontMetricsByStyle new_font_metrics(final String s, final String mode, final ResourceProvider resourceProvider) {
        final WVFFontMetricsByStyle wvfFontMetricsByStyle = new WVFFontMetricsByStyle();
        wvfFontMetricsByStyle.mode = mode;
        try {
            final InputStream internalResourceStream = resourceProvider.getInternalResourceStream("Fonts/" + s);
            if (internalResourceStream == null) {
                throw new IOException("wiris: File not foundFonts/" + s);
            }
            final DataInputStream dataInputStream = new DataInputStream(internalResourceStream);
            dataInputStream.readByte();
            dataInputStream.readByte();
            dataInputStream.readByte();
            dataInputStream.readInt();
            dataInputStream.readInt();
            int i;
            do {
                i = dataInputStream.readInt();
                final int int1 = dataInputStream.readInt();
                switch (i) {
                    case 1: {
                        wvfFontMetricsByStyle.descent = dataInputStream.readInt();
                        continue;
                    }
                    case 2: {
                        wvfFontMetricsByStyle.ascent = dataInputStream.readInt();
                        continue;
                    }
                    case 256: {
                        for (int j = 0; j < 256; ++j) {
                            wvfFontMetricsByStyle.width[j] = dataInputStream.readShort();
                        }
                        continue;
                    }
                    default: {
                        for (int k = 0; k < int1; ++k) {
                            dataInputStream.readByte();
                        }
                        continue;
                    }
                }
            } while (i != 512);
        }
        catch (IOException ex) {
            MessageBox.showMessageBox("Error", "Cannot obtain the resource:" + s);
            ex.printStackTrace();
        }
        return wvfFontMetricsByStyle;
    }
    
    public final int getAscent() {
        return this.ascent.ascent * this.descent / 1000;
    }
    
    public final int getDescent() {
        return this.ascent.descent * this.descent / 1000;
    }
    
    private final int append(WVFFontMetricsByStyle request_font_metrics, char ascent) {
        if (ascent >= '\u0391' && ascent <= '\u03c9') {
            request_font_metrics = request_font_metrics(request_font_metrics.mode + "-sy-0", request_font_metrics.mode, this.resourceProvider);
            ascent = ascent(ascent);
        }
        if (ascent >= ' ' && ascent < '\u0100' && request_font_metrics.width[ascent] != ascent) {
            return request_font_metrics.width[ascent];
        }
        return 0;
    }
    
    public final int charWidth(final char c) {
        return this.append(this.ascent, c) * this.descent / 1000;
    }
    
    public final int charsWidth(final char[] array, final int n, final int n2) {
        int n3 = 0;
        for (int i = 0; i < n2; ++i) {
            n3 += this.append(this.ascent, array[i + n]);
        }
        return n3 * this.descent / 1000;
    }
    
    private static final char ascent(final char c) {
        switch (c) {
            case '\u0393': {
                return 'G';
            }
            case '\u0394': {
                return 'D';
            }
            case '\u0398': {
                return 'Q';
            }
            case '\u039b': {
                return 'L';
            }
            case '\u039e': {
                return 'X';
            }
            case '\u03a0': {
                return 'P';
            }
            case '\u03a3': {
                return 'S';
            }
            case '\u03a6': {
                return 'F';
            }
            case '\u03a8': {
                return 'Y';
            }
            case '\u03a9': {
                return 'W';
            }
            case '\u03b1': {
                return 'a';
            }
            case '\u03b2': {
                return 'b';
            }
            case '\u03b3': {
                return 'g';
            }
            case '\u03b4': {
                return 'd';
            }
            case '\u03b5': {
                return 'e';
            }
            case '\u03b6': {
                return 'z';
            }
            case '\u03b7': {
                return 'h';
            }
            case '\u03b8': {
                return 'q';
            }
            case '\u03b9': {
                return 'i';
            }
            case '\u03ba': {
                return 'k';
            }
            case '\u03bb': {
                return 'c';
            }
            case '\u03bc': {
                return 'm';
            }
            case '\u03bd': {
                return 'n';
            }
            case '\u03be': {
                return 'x';
            }
            case '\u03c0': {
                return 'p';
            }
            case '\u03c1': {
                return 'r';
            }
            case '\u03c2': {
                return 's';
            }
            case '\u03c3': {
                return 's';
            }
            case '\u03c4': {
                return 't';
            }
            case '\u03c5': {
                return 'u';
            }
            case '\u03c6': {
                return 'j';
            }
            case '\u03c7': {
                return 'c';
            }
            case '\u03c8': {
                return 'y';
            }
            case '\u03c9': {
                return 'w';
            }
            default: {
                return ' ';
            }
        }
    }
    
    public static final String fontToFileName(final String s, final Font font) {
        final String name = font.getName();
        String s2;
        if (name.equalsIgnoreCase("Monospaced")) {
            s2 = "m";
        }
        else if (name.equalsIgnoreCase("Serif")) {
            s2 = "s";
        }
        else {
            s2 = "ss";
        }
        return s + "-" + s2 + "-" + font.getStyle();
    }
    
    static {
        WVFFontMetrics.append = new Hashtable();
    }
}
