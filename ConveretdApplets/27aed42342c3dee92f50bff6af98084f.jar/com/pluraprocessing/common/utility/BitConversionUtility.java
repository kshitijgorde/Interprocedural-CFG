// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.utility;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class BitConversionUtility
{
    public static String convert8bitTo6bit(final byte[] inData) {
        int curByte = 0;
        int curBit = 0;
        int tempByte = 0;
        final ByteArrayOutputStream process = new ByteArrayOutputStream();
        final int length = inData.length;
        while (curByte < length) {
            if (curBit == 0) {
                tempByte = (inData[curByte] & 0xFC) >> 2;
                curBit = 6;
            }
            else if (curBit == 2) {
                tempByte = (inData[curByte] & 0x3F);
                curBit = 0;
                ++curByte;
            }
            else if (curBit == 4) {
                tempByte = (inData[curByte] & 0xF) << 2;
                if (curByte + 1 != length) {
                    tempByte += (inData[curByte + 1] & 0xC0) >> 6;
                }
                curBit = 2;
                ++curByte;
            }
            else if (curBit == 6) {
                tempByte = (inData[curByte] & 0x3) << 4;
                if (curByte + 1 != length) {
                    tempByte += (inData[curByte + 1] & 0xF0) >> 4;
                }
                curBit = 4;
                ++curByte;
            }
            if (tempByte < 26) {
                tempByte += 97;
            }
            else if (tempByte < 52) {
                tempByte = tempByte - 26 + 65;
            }
            else if (tempByte < 62) {
                tempByte = tempByte - 52 + 48;
            }
            else if (tempByte == 62) {
                tempByte = 45;
            }
            else if (tempByte == 63) {
                tempByte = 95;
            }
            process.write(tempByte);
        }
        return process.toString();
    }
    
    public static String convert6bitTo8bitString(final String inData) {
        final ByteArrayInputStream in = new ByteArrayInputStream(inData.getBytes());
        return convert6bitTo8bit(in).toString();
    }
    
    public static byte[] convert6bitTo8bit(final String inData) {
        final ByteArrayInputStream in = new ByteArrayInputStream(inData.getBytes());
        return convert6bitTo8bit(in).toByteArray();
    }
    
    public static byte[] convert6bitTo8bit(final byte[] inData) {
        final ByteArrayInputStream in = new ByteArrayInputStream(inData);
        return convert6bitTo8bit(in).toByteArray();
    }
    
    public static ByteArrayOutputStream convert6bitTo8bit(final InputStream in) {
        int curByte = 0;
        int curBit = 0;
        int tempByte = 0;
        int tempByte2 = 0;
        int curChar = 0;
        final InputStreamReader isr = new InputStreamReader(in);
        final ByteArrayOutputStream process = new ByteArrayOutputStream();
        try {
            while ((curChar = isr.read()) != -1) {
                if (curChar >= 97 && curChar <= 122) {
                    curChar -= 97;
                }
                else if (curChar >= 65 && curChar <= 90) {
                    curChar = curChar - 65 + 26;
                }
                else if (curChar >= 48 && curChar <= 57) {
                    curChar = curChar - 48 + 52;
                }
                else if (curChar == 45) {
                    curChar = 62;
                }
                else if (curChar == 95) {
                    curChar = 63;
                }
                if (curBit == 0) {
                    tempByte = curChar << 2;
                    curBit = 6;
                }
                else if (curBit == 2) {
                    tempByte += curChar;
                    process.write(tempByte);
                    ++curByte;
                    curBit = 0;
                }
                else if (curBit == 4) {
                    tempByte += (curChar & 0xFC) >> 2;
                    process.write(tempByte);
                    tempByte2 = (tempByte = (curChar & 0x3) << 6);
                    ++curByte;
                    curBit = 2;
                }
                else {
                    if (curBit != 6) {
                        continue;
                    }
                    tempByte += (curChar & 0xF0) >> 4;
                    process.write(tempByte);
                    tempByte2 = (tempByte = (curChar & 0xF) << 4);
                    ++curByte;
                    curBit = 4;
                }
            }
        }
        catch (IOException ex) {}
        return process;
    }
}
