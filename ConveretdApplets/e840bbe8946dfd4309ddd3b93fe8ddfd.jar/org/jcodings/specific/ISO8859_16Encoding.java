// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings.specific;

import org.jcodings.ISOEncoding;

public final class ISO8859_16Encoding extends ISOEncoding
{
    static final short[] ISO8859_16CtypeTable;
    static final byte[] ISO8859_16ToLowerCaseTable;
    static final int[][] ISO8859_16CaseFoldMap;
    public static final ISO8859_16Encoding INSTANCE;
    
    protected ISO8859_16Encoding() {
        super("ISO-8859-16", ISO8859_16Encoding.ISO8859_16CtypeTable, ISO8859_16Encoding.ISO8859_16ToLowerCaseTable, ISO8859_16Encoding.ISO8859_16CaseFoldMap);
    }
    
    static {
        ISO8859_16CtypeTable = new short[] { 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16908, 16905, 16904, 16904, 16904, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 16392, 17028, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 30896, 30896, 30896, 30896, 30896, 30896, 30896, 30896, 30896, 30896, 16800, 16800, 16800, 16800, 16800, 16800, 16800, 31906, 31906, 31906, 31906, 31906, 31906, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 29858, 16800, 16800, 16800, 16800, 20896, 16800, 30946, 30946, 30946, 30946, 30946, 30946, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 28898, 16800, 16800, 16800, 16800, 16392, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 644, 13474, 12514, 13474, 160, 416, 13474, 160, 12514, 160, 13474, 416, 13474, 416, 12514, 13474, 160, 160, 13474, 12514, 13474, 416, 160, 416, 12514, 12514, 12514, 416, 13474, 12514, 13474, 12514, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 13474, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514, 12514 };
        ISO8859_16ToLowerCaseTable = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, -128, -127, -126, -125, -124, -123, -122, -121, -120, -119, -118, -117, -116, -115, -114, -113, -112, -111, -110, -109, -108, -107, -106, -105, -104, -103, -102, -101, -100, -99, -98, -97, -96, -94, -94, -77, -91, -91, -88, -89, -88, -87, -70, -85, -82, -83, -82, -65, -80, -79, -71, -77, -72, -75, -74, -73, -72, -71, -70, -69, -67, -67, -1, -65, -32, -31, -30, -29, -28, -27, -26, -25, -24, -23, -22, -21, -20, -19, -18, -17, -16, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6, -5, -4, -3, -2, -33, -32, -31, -30, -29, -28, -27, -26, -25, -24, -23, -22, -21, -20, -19, -18, -17, -16, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1 };
        ISO8859_16CaseFoldMap = new int[][] { { 161, 162 }, { 163, 179 }, { 166, 168 }, { 170, 186 }, { 172, 174 }, { 175, 191 }, { 178, 185 }, { 180, 184 }, { 188, 189 }, { 190, 255 }, { 192, 224 }, { 193, 225 }, { 194, 226 }, { 195, 227 }, { 196, 228 }, { 197, 229 }, { 198, 230 }, { 199, 231 }, { 200, 232 }, { 201, 233 }, { 202, 234 }, { 203, 235 }, { 204, 236 }, { 205, 237 }, { 206, 238 }, { 207, 239 }, { 208, 240 }, { 209, 241 }, { 210, 242 }, { 211, 243 }, { 212, 244 }, { 213, 245 }, { 214, 246 }, { 215, 247 }, { 216, 248 }, { 217, 249 }, { 218, 250 }, { 219, 251 }, { 220, 252 }, { 221, 253 }, { 222, 254 } };
        INSTANCE = new ISO8859_16Encoding();
    }
}
