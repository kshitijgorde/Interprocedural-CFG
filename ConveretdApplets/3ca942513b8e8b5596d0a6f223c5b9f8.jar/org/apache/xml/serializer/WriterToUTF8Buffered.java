// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.OutputStream;
import java.io.Writer;

final class WriterToUTF8Buffered extends Writer implements WriterChain
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
        this.m_inputChars = new char[5463];
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
        else if (c < 65536) {
            this.m_outputBytes[this.count++] = (byte)(224 + (c >> 12));
            this.m_outputBytes[this.count++] = (byte)(128 + (c >> 6 & 0x3F));
            this.m_outputBytes[this.count++] = (byte)(128 + (c & 0x3F));
        }
        else {
            this.m_outputBytes[this.count++] = (byte)(240 + (c >> 18));
            this.m_outputBytes[this.count++] = (byte)(128 + (c >> 12 & 0x3F));
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
        //    15: if_icmplt       155
        //    18: aload_0         /* this */
        //    19: invokevirtual   org/apache/xml/serializer/WriterToUTF8Buffered.flushBuffer:()V
        //    22: iload           lengthx3
        //    24: sipush          16384
        //    27: if_icmple       155
        //    30: iload_3         /* length */
        //    31: sipush          5461
        //    34: idiv           
        //    35: istore          split
        //    37: iload           split
        //    39: iconst_1       
        //    40: if_icmple       50
        //    43: iload           split
        //    45: istore          chunks
        //    47: goto            53
        //    50: iconst_2       
        //    51: istore          chunks
        //    53: iload_2         /* start */
        //    54: istore          end_chunk
        //    56: iconst_1       
        //    57: istore          chunk
        //    59: goto            147
        //    62: iload           end_chunk
        //    64: istore          start_chunk
        //    66: iload_2         /* start */
        //    67: iload_3         /* length */
        //    68: i2l            
        //    69: iload           chunk
        //    71: i2l            
        //    72: lmul           
        //    73: iload           chunks
        //    75: i2l            
        //    76: ldiv           
        //    77: l2i            
        //    78: iadd           
        //    79: istore          end_chunk
        //    81: aload_1         /* chars */
        //    82: iload           end_chunk
        //    84: iconst_1       
        //    85: isub           
        //    86: caload         
        //    87: istore          c
        //    89: aload_1         /* chars */
        //    90: iload           end_chunk
        //    92: iconst_1       
        //    93: isub           
        //    94: caload         
        //    95: istore          ic
        //    97: iload           c
        //    99: ldc             55296
        //   101: if_icmplt       128
        //   104: iload           c
        //   106: ldc             56319
        //   108: if_icmpgt       128
        //   111: iload           end_chunk
        //   113: iload_2         /* start */
        //   114: iload_3         /* length */
        //   115: iadd           
        //   116: if_icmpge       125
        //   119: iinc            end_chunk, 1
        //   122: goto            128
        //   125: iinc            end_chunk, -1
        //   128: iload           end_chunk
        //   130: iload           start_chunk
        //   132: isub           
        //   133: istore          len_chunk
        //   135: aload_0         /* this */
        //   136: aload_1         /* chars */
        //   137: iload           start_chunk
        //   139: iload           len_chunk
        //   141: invokevirtual   org/apache/xml/serializer/WriterToUTF8Buffered.write:([CII)V
        //   144: iinc            chunk, 1
        //   147: iload           chunk
        //   149: iload           chunks
        //   151: if_icmple       62
        //   154: return         
        //   155: iload_3         /* length */
        //   156: iload_2         /* start */
        //   157: iadd           
        //   158: istore          n
        //   160: aload_0         /* this */
        //   161: getfield        org/apache/xml/serializer/WriterToUTF8Buffered.m_outputBytes:[B
        //   164: astore          buf_loc
        //   166: aload_0         /* this */
        //   167: getfield        org/apache/xml/serializer/WriterToUTF8Buffered.count:I
        //   170: istore          count_loc
        //   172: iload_2         /* start */
        //   173: istore          i
        //   175: goto            192
        //   178: aload           buf_loc
        //   180: iload           count_loc
        //   182: iinc            count_loc, 1
        //   185: iload           9
        //   187: i2b            
        //   188: bastore        
        //   189: iinc            i, 1
        //   192: iload           i
        //   194: iload           n
        //   196: if_icmpge       212
        //   199: aload_1         /* chars */
        //   200: iload           i
        //   202: caload         
        //   203: dup            
        //   204: istore          c
        //   206: sipush          128
        //   209: if_icmplt       178
        //   212: goto            475
        //   215: aload_1         /* chars */
        //   216: iload           i
        //   218: caload         
        //   219: istore          c
        //   221: iload           c
        //   223: sipush          128
        //   226: if_icmpge       243
        //   229: aload           buf_loc
        //   231: iload           count_loc
        //   233: iinc            count_loc, 1
        //   236: iload           c
        //   238: i2b            
        //   239: bastore        
        //   240: goto            472
        //   243: iload           c
        //   245: sipush          2048
        //   248: if_icmpge       290
        //   251: aload           buf_loc
        //   253: iload           count_loc
        //   255: iinc            count_loc, 1
        //   258: sipush          192
        //   261: iload           c
        //   263: bipush          6
        //   265: ishr           
        //   266: iadd           
        //   267: i2b            
        //   268: bastore        
        //   269: aload           buf_loc
        //   271: iload           count_loc
        //   273: iinc            count_loc, 1
        //   276: sipush          128
        //   279: iload           c
        //   281: bipush          63
        //   283: iand           
        //   284: iadd           
        //   285: i2b            
        //   286: bastore        
        //   287: goto            472
        //   290: iload           c
        //   292: ldc             55296
        //   294: if_icmplt       415
        //   297: iload           c
        //   299: ldc             56319
        //   301: if_icmpgt       415
        //   304: iload           c
        //   306: istore          high
        //   308: iinc            i, 1
        //   311: aload_1         /* chars */
        //   312: iload           i
        //   314: caload         
        //   315: istore          low
        //   317: aload           buf_loc
        //   319: iload           count_loc
        //   321: iinc            count_loc, 1
        //   324: sipush          240
        //   327: iload           high
        //   329: bipush          64
        //   331: iadd           
        //   332: bipush          8
        //   334: ishr           
        //   335: sipush          240
        //   338: iand           
        //   339: ior            
        //   340: i2b            
        //   341: bastore        
        //   342: aload           buf_loc
        //   344: iload           count_loc
        //   346: iinc            count_loc, 1
        //   349: sipush          128
        //   352: iload           high
        //   354: bipush          64
        //   356: iadd           
        //   357: iconst_2       
        //   358: ishr           
        //   359: bipush          63
        //   361: iand           
        //   362: ior            
        //   363: i2b            
        //   364: bastore        
        //   365: aload           buf_loc
        //   367: iload           count_loc
        //   369: iinc            count_loc, 1
        //   372: sipush          128
        //   375: iload           low
        //   377: bipush          6
        //   379: ishr           
        //   380: bipush          15
        //   382: iand           
        //   383: iload           high
        //   385: iconst_4       
        //   386: ishl           
        //   387: bipush          48
        //   389: iand           
        //   390: iadd           
        //   391: ior            
        //   392: i2b            
        //   393: bastore        
        //   394: aload           buf_loc
        //   396: iload           count_loc
        //   398: iinc            count_loc, 1
        //   401: sipush          128
        //   404: iload           low
        //   406: bipush          63
        //   408: iand           
        //   409: ior            
        //   410: i2b            
        //   411: bastore        
        //   412: goto            472
        //   415: aload           buf_loc
        //   417: iload           count_loc
        //   419: iinc            count_loc, 1
        //   422: sipush          224
        //   425: iload           c
        //   427: bipush          12
        //   429: ishr           
        //   430: iadd           
        //   431: i2b            
        //   432: bastore        
        //   433: aload           buf_loc
        //   435: iload           count_loc
        //   437: iinc            count_loc, 1
        //   440: sipush          128
        //   443: iload           c
        //   445: bipush          6
        //   447: ishr           
        //   448: bipush          63
        //   450: iand           
        //   451: iadd           
        //   452: i2b            
        //   453: bastore        
        //   454: aload           buf_loc
        //   456: iload           count_loc
        //   458: iinc            count_loc, 1
        //   461: sipush          128
        //   464: iload           c
        //   466: bipush          63
        //   468: iand           
        //   469: iadd           
        //   470: i2b            
        //   471: bastore        
        //   472: iinc            i, 1
        //   475: iload           i
        //   477: iload           n
        //   479: if_icmplt       215
        //   482: aload_0         /* this */
        //   483: iload           count_loc
        //   485: putfield        org/apache/xml/serializer/WriterToUTF8Buffered.count:I
        //   488: return         
        //    Exceptions:
        //  throws java.io.IOException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name         Signature
        //  -----  ------  ----  -----------  ------------------------------------------------
        //  0      489     0     this         Lorg/apache/xml/serializer/WriterToUTF8Buffered;
        //  0      489     1     chars        [C
        //  0      489     2     start        I
        //  0      489     3     length       I
        //  5      483     4     lengthx3     I
        //  37     118     5     split        I
        //  47     108     6     chunks       I
        //  56     99      7     end_chunk    I
        //  59     96      8     chunk        I
        //  66     78      9     start_chunk  I
        //  89     55      10    c            C
        //  97     47      11    ic           I
        //  135    9       12    len_chunk    I
        //  160    328     5     n            I
        //  166    322     6     buf_loc      [B
        //  172    316     7     count_loc    I
        //  175    313     8     i            I
        //  206    6       9     c            C
        //  221    251     9     c            C
        //  308    104     10    high         C
        //  317    95      11    low          C
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
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
        //    18: if_icmplt       166
        //    21: aload_0         /* this */
        //    22: invokevirtual   org/apache/xml/serializer/WriterToUTF8Buffered.flushBuffer:()V
        //    25: iload_3         /* lengthx3 */
        //    26: sipush          16384
        //    29: if_icmple       166
        //    32: iconst_0       
        //    33: istore          start
        //    35: iload_2         /* length */
        //    36: sipush          5461
        //    39: idiv           
        //    40: istore          split
        //    42: iload           split
        //    44: iconst_1       
        //    45: if_icmple       55
        //    48: iload           split
        //    50: istore          chunks
        //    52: goto            58
        //    55: iconst_2       
        //    56: istore          chunks
        //    58: iconst_0       
        //    59: istore          end_chunk
        //    61: iconst_1       
        //    62: istore          chunk
        //    64: goto            158
        //    67: iload           end_chunk
        //    69: istore          start_chunk
        //    71: iconst_0       
        //    72: iload_2         /* length */
        //    73: i2l            
        //    74: iload           chunk
        //    76: i2l            
        //    77: lmul           
        //    78: iload           chunks
        //    80: i2l            
        //    81: ldiv           
        //    82: l2i            
        //    83: iadd           
        //    84: istore          end_chunk
        //    86: aload_1         /* s */
        //    87: iload           start_chunk
        //    89: iload           end_chunk
        //    91: aload_0         /* this */
        //    92: getfield        org/apache/xml/serializer/WriterToUTF8Buffered.m_inputChars:[C
        //    95: iconst_0       
        //    96: invokevirtual   java/lang/String.getChars:(II[CI)V
        //    99: iload           end_chunk
        //   101: iload           start_chunk
        //   103: isub           
        //   104: istore          len_chunk
        //   106: aload_0         /* this */
        //   107: getfield        org/apache/xml/serializer/WriterToUTF8Buffered.m_inputChars:[C
        //   110: iload           len_chunk
        //   112: iconst_1       
        //   113: isub           
        //   114: caload         
        //   115: istore          c
        //   117: iload           c
        //   119: ldc             55296
        //   121: if_icmplt       144
        //   124: iload           c
        //   126: ldc             56319
        //   128: if_icmpgt       144
        //   131: iinc            end_chunk, -1
        //   134: iinc            len_chunk, -1
        //   137: iload           chunk
        //   139: iload           chunks
        //   141: if_icmpne       144
        //   144: aload_0         /* this */
        //   145: aload_0         /* this */
        //   146: getfield        org/apache/xml/serializer/WriterToUTF8Buffered.m_inputChars:[C
        //   149: iconst_0       
        //   150: iload           len_chunk
        //   152: invokevirtual   org/apache/xml/serializer/WriterToUTF8Buffered.write:([CII)V
        //   155: iinc            chunk, 1
        //   158: iload           chunk
        //   160: iload           chunks
        //   162: if_icmple       67
        //   165: return         
        //   166: aload_1         /* s */
        //   167: iconst_0       
        //   168: iload_2         /* length */
        //   169: aload_0         /* this */
        //   170: getfield        org/apache/xml/serializer/WriterToUTF8Buffered.m_inputChars:[C
        //   173: iconst_0       
        //   174: invokevirtual   java/lang/String.getChars:(II[CI)V
        //   177: aload_0         /* this */
        //   178: getfield        org/apache/xml/serializer/WriterToUTF8Buffered.m_inputChars:[C
        //   181: astore          chars
        //   183: iload_2         /* length */
        //   184: istore          n
        //   186: aload_0         /* this */
        //   187: getfield        org/apache/xml/serializer/WriterToUTF8Buffered.m_outputBytes:[B
        //   190: astore          buf_loc
        //   192: aload_0         /* this */
        //   193: getfield        org/apache/xml/serializer/WriterToUTF8Buffered.count:I
        //   196: istore          count_loc
        //   198: iconst_0       
        //   199: istore          i
        //   201: goto            218
        //   204: aload           buf_loc
        //   206: iload           count_loc
        //   208: iinc            count_loc, 1
        //   211: iload           9
        //   213: i2b            
        //   214: bastore        
        //   215: iinc            i, 1
        //   218: iload           i
        //   220: iload           n
        //   222: if_icmpge       239
        //   225: aload           chars
        //   227: iload           i
        //   229: caload         
        //   230: dup            
        //   231: istore          c
        //   233: sipush          128
        //   236: if_icmplt       204
        //   239: goto            504
        //   242: aload           chars
        //   244: iload           i
        //   246: caload         
        //   247: istore          c
        //   249: iload           c
        //   251: sipush          128
        //   254: if_icmpge       271
        //   257: aload           buf_loc
        //   259: iload           count_loc
        //   261: iinc            count_loc, 1
        //   264: iload           c
        //   266: i2b            
        //   267: bastore        
        //   268: goto            501
        //   271: iload           c
        //   273: sipush          2048
        //   276: if_icmpge       318
        //   279: aload           buf_loc
        //   281: iload           count_loc
        //   283: iinc            count_loc, 1
        //   286: sipush          192
        //   289: iload           c
        //   291: bipush          6
        //   293: ishr           
        //   294: iadd           
        //   295: i2b            
        //   296: bastore        
        //   297: aload           buf_loc
        //   299: iload           count_loc
        //   301: iinc            count_loc, 1
        //   304: sipush          128
        //   307: iload           c
        //   309: bipush          63
        //   311: iand           
        //   312: iadd           
        //   313: i2b            
        //   314: bastore        
        //   315: goto            501
        //   318: iload           c
        //   320: ldc             55296
        //   322: if_icmplt       444
        //   325: iload           c
        //   327: ldc             56319
        //   329: if_icmpgt       444
        //   332: iload           c
        //   334: istore          high
        //   336: iinc            i, 1
        //   339: aload           chars
        //   341: iload           i
        //   343: caload         
        //   344: istore          low
        //   346: aload           buf_loc
        //   348: iload           count_loc
        //   350: iinc            count_loc, 1
        //   353: sipush          240
        //   356: iload           high
        //   358: bipush          64
        //   360: iadd           
        //   361: bipush          8
        //   363: ishr           
        //   364: sipush          240
        //   367: iand           
        //   368: ior            
        //   369: i2b            
        //   370: bastore        
        //   371: aload           buf_loc
        //   373: iload           count_loc
        //   375: iinc            count_loc, 1
        //   378: sipush          128
        //   381: iload           high
        //   383: bipush          64
        //   385: iadd           
        //   386: iconst_2       
        //   387: ishr           
        //   388: bipush          63
        //   390: iand           
        //   391: ior            
        //   392: i2b            
        //   393: bastore        
        //   394: aload           buf_loc
        //   396: iload           count_loc
        //   398: iinc            count_loc, 1
        //   401: sipush          128
        //   404: iload           low
        //   406: bipush          6
        //   408: ishr           
        //   409: bipush          15
        //   411: iand           
        //   412: iload           high
        //   414: iconst_4       
        //   415: ishl           
        //   416: bipush          48
        //   418: iand           
        //   419: iadd           
        //   420: ior            
        //   421: i2b            
        //   422: bastore        
        //   423: aload           buf_loc
        //   425: iload           count_loc
        //   427: iinc            count_loc, 1
        //   430: sipush          128
        //   433: iload           low
        //   435: bipush          63
        //   437: iand           
        //   438: ior            
        //   439: i2b            
        //   440: bastore        
        //   441: goto            501
        //   444: aload           buf_loc
        //   446: iload           count_loc
        //   448: iinc            count_loc, 1
        //   451: sipush          224
        //   454: iload           c
        //   456: bipush          12
        //   458: ishr           
        //   459: iadd           
        //   460: i2b            
        //   461: bastore        
        //   462: aload           buf_loc
        //   464: iload           count_loc
        //   466: iinc            count_loc, 1
        //   469: sipush          128
        //   472: iload           c
        //   474: bipush          6
        //   476: ishr           
        //   477: bipush          63
        //   479: iand           
        //   480: iadd           
        //   481: i2b            
        //   482: bastore        
        //   483: aload           buf_loc
        //   485: iload           count_loc
        //   487: iinc            count_loc, 1
        //   490: sipush          128
        //   493: iload           c
        //   495: bipush          63
        //   497: iand           
        //   498: iadd           
        //   499: i2b            
        //   500: bastore        
        //   501: iinc            i, 1
        //   504: iload           i
        //   506: iload           n
        //   508: if_icmplt       242
        //   511: aload_0         /* this */
        //   512: iload           count_loc
        //   514: putfield        org/apache/xml/serializer/WriterToUTF8Buffered.count:I
        //   517: return         
        //    Exceptions:
        //  throws java.io.IOException
        //    LocalVariableTable:
        //  Start  Length  Slot  Name         Signature
        //  -----  ------  ----  -----------  ------------------------------------------------
        //  0      518     0     this         Lorg/apache/xml/serializer/WriterToUTF8Buffered;
        //  0      518     1     s            Ljava/lang/String;
        //  5      512     2     length       I
        //  9      508     3     lengthx3     I
        //  35     131     4     start        I
        //  42     124     5     split        I
        //  52     114     6     chunks       I
        //  61     105     7     end_chunk    I
        //  64     102     8     chunk        I
        //  71     84      9     start_chunk  I
        //  106    49      10    len_chunk    I
        //  117    38      11    c            C
        //  183    334     4     chars        [C
        //  186    331     5     n            I
        //  192    325     6     buf_loc      [B
        //  198    319     7     count_loc    I
        //  201    316     8     i            I
        //  233    6       9     c            C
        //  249    252     9     c            C
        //  336    105     10    high         C
        //  346    95      11    low          C
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
    
    public Writer getWriter() {
        return null;
    }
}
