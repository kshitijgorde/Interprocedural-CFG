// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.OutputStream;
import java.io.Writer;

public final class WriterToUTF8Buffered extends Writer
{
    private static final int BYTES_MAX = 16384;
    private static final int CHARS_MAX = 5461;
    private final OutputStream m_os;
    private final byte[] m_outputBytes;
    private final char[] m_inputChars;
    private int count;
    
    public WriterToUTF8Buffered(final OutputStream out) throws UnsupportedEncodingException {
        this.m_os = out;
        this.m_outputBytes = new byte[16387];
        this.m_inputChars = new char[5462];
        this.count = 0;
    }
    
    public void write(final int c) throws IOException {
        if (this.count >= 16384) {
            this.flushBuffer();
        }
        if (c < 128) {
            this.m_outputBytes[this.count++] = (byte)c;
        }
        else if (c < 2048) {
            this.m_outputBytes[this.count++] = (byte)(192 + (c >> 6));
            this.m_outputBytes[this.count++] = (byte)(128 + (c & 0x3F));
        }
        else {
            this.m_outputBytes[this.count++] = (byte)(224 + (c >> 12));
            this.m_outputBytes[this.count++] = (byte)(128 + (c >> 6 & 0x3F));
            this.m_outputBytes[this.count++] = (byte)(128 + (c & 0x3F));
        }
    }
    
    public void write(final char[] chars, final int start, final int length) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_3       
        //     1: iload_3         /* length */
        //     2: imul           
        //     3: istore          lengthx3
        //     5: iload           lengthx3
        //     7: sipush          16384
        //    10: aload_0         /* this */
        //    11: getfield        org/apache/xml/serializer/WriterToUTF8Buffered.count:I
        //    14: isub           
        //    15: if_icmplt       96
        //    18: aload_0         /* this */
        //    19: invokevirtual   org/apache/xml/serializer/WriterToUTF8Buffered.flushBuffer:()V
        //    22: iload           lengthx3
        //    24: sipush          16384
        //    27: if_icmplt       96
        //    30: iconst_1       
        //    31: iload_3         /* length */
        //    32: sipush          5461
        //    35: idiv           
        //    36: iadd           
        //    37: istore          chunks
        //    39: iconst_0       
        //    40: istore          chunk
        //    42: goto            88
        //    45: iload_2         /* start */
        //    46: iload_3         /* length */
        //    47: iload           chunk
        //    49: imul           
        //    50: iload           chunks
        //    52: idiv           
        //    53: iadd           
        //    54: istore          start_chunk
        //    56: iload_2         /* start */
        //    57: iload_3         /* length */
        //    58: iload           chunk
        //    60: iconst_1       
        //    61: iadd           
        //    62: imul           
        //    63: iload           chunks
        //    65: idiv           
        //    66: iadd           
        //    67: istore          end_chunk
        //    69: iload           end_chunk
        //    71: iload           start_chunk
        //    73: isub           
        //    74: istore          len_chunk
        //    76: aload_0         /* this */
        //    77: aload_1         /* chars */
        //    78: iload           start_chunk
        //    80: iload           len_chunk
        //    82: invokevirtual   org/apache/xml/serializer/WriterToUTF8Buffered.write:([CII)V
        //    85: iinc            chunk, 1
        //    88: iload           chunk
        //    90: iload           chunks
        //    92: if_icmplt       45
        //    95: return         
        //    96: iload_3         /* length */
        //    97: iload_2         /* start */
        //    98: iadd           
        //    99: istore          n
        //   101: aload_0         /* this */
        //   102: getfield        org/apache/xml/serializer/WriterToUTF8Buffered.m_outputBytes:[B
        //   105: astore          buf_loc
        //   107: aload_0         /* this */
        //   108: getfield        org/apache/xml/serializer/WriterToUTF8Buffered.count:I
        //   111: istore          count_loc
        //   113: iload_2         /* start */
        //   114: istore          i
        //   116: goto            133
        //   119: aload           buf_loc
        //   121: iload           count_loc
        //   123: iinc            count_loc, 1
        //   126: iload           9
        //   128: i2b            
        //   129: bastore        
        //   130: iinc            i, 1
        //   133: iload           i
        //   135: iload           n
        //   137: if_icmpge       153
        //   140: aload_1         /* chars */
        //   141: iload           i
        //   143: caload         
        //   144: dup            
        //   145: istore          c
        //   147: sipush          128
        //   150: if_icmplt       119
        //   153: goto            291
        //   156: aload_1         /* chars */
        //   157: iload           i
        //   159: caload         
        //   160: istore          c
        //   162: iload           c
        //   164: sipush          128
        //   167: if_icmpge       184
        //   170: aload           buf_loc
        //   172: iload           count_loc
        //   174: iinc            count_loc, 1
        //   177: iload           c
        //   179: i2b            
        //   180: bastore        
        //   181: goto            288
        //   184: iload           c
        //   186: sipush          2048
        //   189: if_icmpge       231
        //   192: aload           buf_loc
        //   194: iload           count_loc
        //   196: iinc            count_loc, 1
        //   199: sipush          192
        //   202: iload           c
        //   204: bipush          6
        //   206: ishr           
        //   207: iadd           
        //   208: i2b            
        //   209: bastore        
        //   210: aload           buf_loc
        //   212: iload           count_loc
        //   214: iinc            count_loc, 1
        //   217: sipush          128
        //   220: iload           c
        //   222: bipush          63
        //   224: iand           
        //   225: iadd           
        //   226: i2b            
        //   227: bastore        
        //   228: goto            288
        //   231: aload           buf_loc
        //   233: iload           count_loc
        //   235: iinc            count_loc, 1
        //   238: sipush          224
        //   241: iload           c
        //   243: bipush          12
        //   245: ishr           
        //   246: iadd           
        //   247: i2b            
        //   248: bastore        
        //   249: aload           buf_loc
        //   251: iload           count_loc
        //   253: iinc            count_loc, 1
        //   256: sipush          128
        //   259: iload           c
        //   261: bipush          6
        //   263: ishr           
        //   264: bipush          63
        //   266: iand           
        //   267: iadd           
        //   268: i2b            
        //   269: bastore        
        //   270: aload           buf_loc
        //   272: iload           count_loc
        //   274: iinc            count_loc, 1
        //   277: sipush          128
        //   280: iload           c
        //   282: bipush          63
        //   284: iand           
        //   285: iadd           
        //   286: i2b            
        //   287: bastore        
        //   288: iinc            i, 1
        //   291: iload           i
        //   293: iload           n
        //   295: if_icmplt       156
        //   298: aload_0         /* this */
        //   299: iload           count_loc
        //   301: putfield        org/apache/xml/serializer/WriterToUTF8Buffered.count:I
        //   304: return         
        //    Exceptions:
        //  throws java.io.IOException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name         Signature
        //  -----  ------  ----  -----------  ------------------------------------------------
        //  0      305     0     this         Lorg/apache/xml/serializer/WriterToUTF8Buffered;
        //  0      305     1     chars        [C
        //  0      305     2     start        I
        //  0      305     3     length       I
        //  5      299     4     lengthx3     I
        //  39     57      5     chunks       I
        //  42     54      6     chunk        I
        //  56     29      7     start_chunk  I
        //  69     16      8     end_chunk    I
        //  76     9       9     len_chunk    I
        //  101    203     5     n            I
        //  107    197     6     buf_loc      [B
        //  113    191     7     count_loc    I
        //  116    188     8     i            I
        //  147    6       9     c            C
        //  162    126     9     c            C
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void directWrite(final char[] chars, final int start, final int length) throws IOException {
        if (length >= 16384 - this.count) {
            this.flushBuffer();
            if (length >= 16384) {
                for (int chunks = 1 + length / 5461, chunk = 0; chunk < chunks; ++chunk) {
                    final int start_chunk = start + length * chunk / chunks;
                    final int end_chunk = start + length * (chunk + 1) / chunks;
                    final int len_chunk = end_chunk - start_chunk;
                    this.directWrite(chars, start_chunk, len_chunk);
                }
                return;
            }
        }
        final int n = length + start;
        final byte[] buf_loc = this.m_outputBytes;
        int count_loc = this.count;
        for (int i = start; i < n; ++i) {
            buf_loc[count_loc++] = buf_loc[i];
        }
        this.count = count_loc;
    }
    
    public void write(final String s) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1         /* s */
        //     1: invokevirtual   java/lang/String.length:()I
        //     4: istore_2        /* length */
        //     5: iconst_3       
        //     6: iload_2         /* length */
        //     7: imul           
        //     8: istore_3        /* lengthx3 */
        //     9: iload_3         /* lengthx3 */
        //    10: sipush          16384
        //    13: aload_0         /* this */
        //    14: getfield        org/apache/xml/serializer/WriterToUTF8Buffered.count:I
        //    17: isub           
        //    18: if_icmplt       116
        //    21: aload_0         /* this */
        //    22: invokevirtual   org/apache/xml/serializer/WriterToUTF8Buffered.flushBuffer:()V
        //    25: iload_3         /* lengthx3 */
        //    26: sipush          16384
        //    29: if_icmplt       116
        //    32: iconst_0       
        //    33: istore          start
        //    35: iconst_1       
        //    36: iload_2         /* length */
        //    37: sipush          5461
        //    40: idiv           
        //    41: iadd           
        //    42: istore          chunks
        //    44: iconst_0       
        //    45: istore          chunk
        //    47: goto            108
        //    50: iconst_0       
        //    51: iload_2         /* length */
        //    52: iload           chunk
        //    54: imul           
        //    55: iload           chunks
        //    57: idiv           
        //    58: iadd           
        //    59: istore          start_chunk
        //    61: iconst_0       
        //    62: iload_2         /* length */
        //    63: iload           chunk
        //    65: iconst_1       
        //    66: iadd           
        //    67: imul           
        //    68: iload           chunks
        //    70: idiv           
        //    71: iadd           
        //    72: istore          end_chunk
        //    74: iload           end_chunk
        //    76: iload           start_chunk
        //    78: isub           
        //    79: istore          len_chunk
        //    81: aload_1         /* s */
        //    82: iload           start_chunk
        //    84: iload           end_chunk
        //    86: aload_0         /* this */
        //    87: getfield        org/apache/xml/serializer/WriterToUTF8Buffered.m_inputChars:[C
        //    90: iconst_0       
        //    91: invokevirtual   java/lang/String.getChars:(II[CI)V
        //    94: aload_0         /* this */
        //    95: aload_0         /* this */
        //    96: getfield        org/apache/xml/serializer/WriterToUTF8Buffered.m_inputChars:[C
        //    99: iconst_0       
        //   100: iload           len_chunk
        //   102: invokevirtual   org/apache/xml/serializer/WriterToUTF8Buffered.write:([CII)V
        //   105: iinc            chunk, 1
        //   108: iload           chunk
        //   110: iload           chunks
        //   112: if_icmplt       50
        //   115: return         
        //   116: aload_1         /* s */
        //   117: iconst_0       
        //   118: iload_2         /* length */
        //   119: aload_0         /* this */
        //   120: getfield        org/apache/xml/serializer/WriterToUTF8Buffered.m_inputChars:[C
        //   123: iconst_0       
        //   124: invokevirtual   java/lang/String.getChars:(II[CI)V
        //   127: aload_0         /* this */
        //   128: getfield        org/apache/xml/serializer/WriterToUTF8Buffered.m_inputChars:[C
        //   131: astore          chars
        //   133: iload_2         /* length */
        //   134: istore          n
        //   136: aload_0         /* this */
        //   137: getfield        org/apache/xml/serializer/WriterToUTF8Buffered.m_outputBytes:[B
        //   140: astore          buf_loc
        //   142: aload_0         /* this */
        //   143: getfield        org/apache/xml/serializer/WriterToUTF8Buffered.count:I
        //   146: istore          count_loc
        //   148: iconst_0       
        //   149: istore          i
        //   151: goto            168
        //   154: aload           buf_loc
        //   156: iload           count_loc
        //   158: iinc            count_loc, 1
        //   161: iload           9
        //   163: i2b            
        //   164: bastore        
        //   165: iinc            i, 1
        //   168: iload           i
        //   170: iload           n
        //   172: if_icmpge       189
        //   175: aload           chars
        //   177: iload           i
        //   179: caload         
        //   180: dup            
        //   181: istore          c
        //   183: sipush          128
        //   186: if_icmplt       154
        //   189: goto            328
        //   192: aload           chars
        //   194: iload           i
        //   196: caload         
        //   197: istore          c
        //   199: iload           c
        //   201: sipush          128
        //   204: if_icmpge       221
        //   207: aload           buf_loc
        //   209: iload           count_loc
        //   211: iinc            count_loc, 1
        //   214: iload           c
        //   216: i2b            
        //   217: bastore        
        //   218: goto            325
        //   221: iload           c
        //   223: sipush          2048
        //   226: if_icmpge       268
        //   229: aload           buf_loc
        //   231: iload           count_loc
        //   233: iinc            count_loc, 1
        //   236: sipush          192
        //   239: iload           c
        //   241: bipush          6
        //   243: ishr           
        //   244: iadd           
        //   245: i2b            
        //   246: bastore        
        //   247: aload           buf_loc
        //   249: iload           count_loc
        //   251: iinc            count_loc, 1
        //   254: sipush          128
        //   257: iload           c
        //   259: bipush          63
        //   261: iand           
        //   262: iadd           
        //   263: i2b            
        //   264: bastore        
        //   265: goto            325
        //   268: aload           buf_loc
        //   270: iload           count_loc
        //   272: iinc            count_loc, 1
        //   275: sipush          224
        //   278: iload           c
        //   280: bipush          12
        //   282: ishr           
        //   283: iadd           
        //   284: i2b            
        //   285: bastore        
        //   286: aload           buf_loc
        //   288: iload           count_loc
        //   290: iinc            count_loc, 1
        //   293: sipush          128
        //   296: iload           c
        //   298: bipush          6
        //   300: ishr           
        //   301: bipush          63
        //   303: iand           
        //   304: iadd           
        //   305: i2b            
        //   306: bastore        
        //   307: aload           buf_loc
        //   309: iload           count_loc
        //   311: iinc            count_loc, 1
        //   314: sipush          128
        //   317: iload           c
        //   319: bipush          63
        //   321: iand           
        //   322: iadd           
        //   323: i2b            
        //   324: bastore        
        //   325: iinc            i, 1
        //   328: iload           i
        //   330: iload           n
        //   332: if_icmplt       192
        //   335: aload_0         /* this */
        //   336: iload           count_loc
        //   338: putfield        org/apache/xml/serializer/WriterToUTF8Buffered.count:I
        //   341: return         
        //    Exceptions:
        //  throws java.io.IOException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name         Signature
        //  -----  ------  ----  -----------  ------------------------------------------------
        //  0      342     0     this         Lorg/apache/xml/serializer/WriterToUTF8Buffered;
        //  0      342     1     s            Ljava/lang/String;
        //  5      336     2     length       I
        //  9      332     3     lengthx3     I
        //  35     81      4     start        I
        //  44     72      5     chunks       I
        //  47     69      6     chunk        I
        //  61     44      7     start_chunk  I
        //  74     31      8     end_chunk    I
        //  81     24      9     len_chunk    I
        //  133    208     4     chars        [C
        //  136    205     5     n            I
        //  142    199     6     buf_loc      [B
        //  148    193     7     count_loc    I
        //  151    190     8     i            I
        //  183    6       9     c            C
        //  199    126     9     c            C
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void flushBuffer() throws IOException {
        if (this.count > 0) {
            this.m_os.write(this.m_outputBytes, 0, this.count);
            this.count = 0;
        }
    }
    
    public void flush() throws IOException {
        this.flushBuffer();
        this.m_os.flush();
    }
    
    public void close() throws IOException {
        this.flushBuffer();
        this.m_os.close();
    }
    
    public OutputStream getOutputStream() {
        return this.m_os;
    }
    
    public void directWrite(final String s) throws IOException {
        final int length = s.length();
        if (length >= 16384 - this.count) {
            this.flushBuffer();
            if (length >= 16384) {
                final int start = 0;
                for (int chunks = 1 + length / 5461, chunk = 0; chunk < chunks; ++chunk) {
                    final int start_chunk = 0 + length * chunk / chunks;
                    final int end_chunk = 0 + length * (chunk + 1) / chunks;
                    final int len_chunk = end_chunk - start_chunk;
                    s.getChars(start_chunk, end_chunk, this.m_inputChars, 0);
                    this.directWrite(this.m_inputChars, 0, len_chunk);
                }
                return;
            }
        }
        s.getChars(0, length, this.m_inputChars, 0);
        final char[] chars = this.m_inputChars;
        final byte[] buf_loc = this.m_outputBytes;
        int count_loc = this.count;
        for (int i = 0; i < length; buf_loc[count_loc++] = (byte)chars[i++]) {}
        this.count = count_loc;
    }
}
