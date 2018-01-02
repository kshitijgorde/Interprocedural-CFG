// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serialize;

public class Encodings
{
    static final int DefaultLastPrintable = 127;
    static final String JIS_DANGER_CHARS = "\\~\u007f¢£¥¬\u2014\u2015\u2016\u2026\u203e\u203e\u2225\u222f\u301c\uff3c\uff5e\uffe0\uffe1\uffe2\uffe3";
    private static final EncodingInfo[] _encodings;
    
    static EncodingInfo getEncodingInfo(final String encoding) {
        if (encoding == null) {
            return new EncodingInfo(null, 127);
        }
        for (int i = 0; i < Encodings._encodings.length; ++i) {
            if (Encodings._encodings[i].name.equalsIgnoreCase(encoding)) {
                return Encodings._encodings[i];
            }
        }
        return new SieveEncodingInfo(encoding, 127);
    }
    
    static {
        _encodings = new EncodingInfo[] { new EncodingInfo("ASCII", 127), new EncodingInfo("US-ASCII", 127), new EncodingInfo("ISO-8859-1", 255), new EncodingInfo("ISO-8859-2", 255), new EncodingInfo("ISO-8859-3", 255), new EncodingInfo("ISO-8859-4", 255), new EncodingInfo("ISO-8859-5", 255), new EncodingInfo("ISO-8859-6", 255), new EncodingInfo("ISO-8859-7", 255), new EncodingInfo("ISO-8859-8", 255), new EncodingInfo("ISO-8859-9", 255), new EncodingInfo("UTF-8", "UTF8", 1114111), new SieveEncodingInfo("Shift_JIS", "SJIS", 127, "\\~\u007f¢£¥¬\u2014\u2015\u2016\u2026\u203e\u203e\u2225\u222f\u301c\uff3c\uff5e\uffe0\uffe1\uffe2\uffe3"), new SieveEncodingInfo("Windows-31J", "MS932", 127, "\\~\u007f¢£¥¬\u2014\u2015\u2016\u2026\u203e\u203e\u2225\u222f\u301c\uff3c\uff5e\uffe0\uffe1\uffe2\uffe3"), new SieveEncodingInfo("EUC-JP", null, 127, "\\~\u007f¢£¥¬\u2014\u2015\u2016\u2026\u203e\u203e\u2225\u222f\u301c\uff3c\uff5e\uffe0\uffe1\uffe2\uffe3"), new SieveEncodingInfo("ISO-2022-JP", null, 127, "\\~\u007f¢£¥¬\u2014\u2015\u2016\u2026\u203e\u203e\u2225\u222f\u301c\uff3c\uff5e\uffe0\uffe1\uffe2\uffe3") };
    }
}
