import java.util.StringTokenizer;
import java.awt.FontMetrics;

// 
// Decompiled by Procyon v0.5.30
// 

class jsTA_MsgParser
{
    FontMetrics m_FontM;
    jsTA_ImageReder m_ImageBuffer;
    String m_packMsg;
    int m_itemNum;
    int m_width;
    int m_height;
    int[] m_widths;
    Object[] m_ImageID;
    boolean m_debug;
    
    private void Debug(final String s) {
        if (this.m_debug) {
            System.out.println(s);
        }
    }
    
    public jsTA_MsgParser(final FontMetrics fontM, final jsTA_ImageReder imageBuffer) {
        this.m_debug = false;
        this.m_FontM = fontM;
        this.m_ImageBuffer = imageBuffer;
        this.m_itemNum = 0;
        this.m_width = 0;
        this.m_height = 0;
    }
    
    public void setMessage(final String s) {
        final StringBuffer sb = new StringBuffer();
        this.Debug("> setMessage:Begin()");
        this.m_itemNum = 0;
        this.m_width = 0;
        this.m_height = 0;
        final int length = s.length();
        this.m_widths = new int[length];
        this.m_ImageID = new Object[length];
        this.m_packMsg = "";
        this.m_height = this.m_FontM.getHeight();
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '$') {
                if (i < length - 1) {
                    char c = s.charAt(i + 1);
                    if (c == '$') {
                        sb.append(char1);
                        final int charWidth = this.m_FontM.charWidth(c);
                        this.m_width += charWidth;
                        this.m_widths[this.m_itemNum] = charWidth;
                        ++this.m_itemNum;
                        ++i;
                    }
                    else {
                        final StringBuffer sb2 = new StringBuffer();
                        int n = 1;
                        if (c >= '0' && c <= '9') {
                            do {
                                sb2.append(c);
                                ++n;
                                try {
                                    c = s.charAt(i + n);
                                }
                                catch (Exception ex) {
                                    c = ' ';
                                }
                            } while (c >= '0' && c <= '9');
                            final int intValue = Integer.valueOf(sb2.toString());
                            sb.append(' ');
                            final int imageWidth = this.m_ImageBuffer.getImageWidth(intValue);
                            this.m_width += imageWidth;
                            this.m_widths[this.m_itemNum] = imageWidth;
                            this.m_ImageID[this.m_itemNum] = new Integer(intValue);
                            ++this.m_itemNum;
                            i = i + n - 1;
                        }
                        else if (c == '(') {
                            this.Debug("OK ( \u0082ª\u0097\u0088\u0082½");
                            final StringBuffer sb3 = new StringBuffer();
                            int n2 = 2;
                            int n3 = 1;
                            try {
                                for (char c2 = s.charAt(i + n2); c2 != ')'; c2 = s.charAt(i + n2)) {
                                    sb3.append(c2);
                                    ++n2;
                                }
                            }
                            catch (Exception ex2) {
                                System.out.println(" ')' is messing.");
                            }
                            this.Debug("()=" + sb3.toString());
                            final StringTokenizer stringTokenizer = new StringTokenizer(sb3.toString(), ",");
                            this.Debug("countToken=" + stringTokenizer.countTokens());
                            final int[] array = new int[stringTokenizer.countTokens() + 1];
                            array[0] = 1;
                            while (stringTokenizer.hasMoreTokens()) {
                                array[n3] = Integer.valueOf(stringTokenizer.nextToken().trim());
                                ++n3;
                            }
                            sb.append(' ');
                            final int imageWidth2 = this.m_ImageBuffer.getImageWidth(array[1]);
                            this.m_width += imageWidth2;
                            this.m_widths[this.m_itemNum] = imageWidth2;
                            this.m_ImageID[this.m_itemNum] = array;
                            ++this.m_itemNum;
                            i += n2;
                        }
                        else {
                            sb.append(char1);
                            final int charWidth2 = this.m_FontM.charWidth(char1);
                            this.m_width += charWidth2;
                            this.m_widths[this.m_itemNum] = charWidth2;
                            ++this.m_itemNum;
                            sb.append(c);
                            final int charWidth3 = this.m_FontM.charWidth(c);
                            this.m_width += charWidth3;
                            this.m_widths[this.m_itemNum] = charWidth3;
                            ++this.m_itemNum;
                            ++i;
                        }
                    }
                }
            }
            else {
                sb.append(char1);
                final int charWidth4 = this.m_FontM.charWidth(char1);
                this.m_width += charWidth4;
                this.m_widths[this.m_itemNum] = charWidth4;
                ++this.m_itemNum;
            }
        }
        this.m_packMsg = sb.toString();
        this.Debug("PackMsg=[" + this.m_packMsg + "]");
        this.Debug("<setMessage:End()");
    }
    
    public int getWidth() {
        return this.m_width;
    }
    
    public int getHeight() {
        return this.m_height;
    }
    
    public int getItemWidth(final int n) {
        return this.m_widths[n];
    }
    
    public int getItemNum() {
        return this.m_itemNum;
    }
    
    public String getItemOneCharString(final int n) {
        return "" + this.m_packMsg.charAt(n);
    }
    
    public boolean isImage(final int n) {
        return this.m_ImageID[n] != null;
    }
    
    public int getImageID(final int n) {
        final Object o = this.m_ImageID[n];
        if (o instanceof Integer) {
            return (int)o;
        }
        if (!(o instanceof int[])) {
            return 0;
        }
        this.Debug("Int[]");
        final int[] array = (int[])o;
        final int n2 = array[0];
        if (n2 < array.length) {
            final int[] array2 = array;
            final int n3 = 0;
            ++array2[n3];
            return array[n2];
        }
        array[0] = 1;
        return array[1];
    }
}
