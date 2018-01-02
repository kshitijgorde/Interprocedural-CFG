// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.cmdline;

import org.apache.xalan.xsltc.compiler.util.ErrorMsg;

public final class Compile
{
    private static int VERSION_MAJOR;
    private static int VERSION_MINOR;
    private static int VERSION_DELTA;
    
    public static void printUsage() {
        final StringBuffer vers = new StringBuffer("XSLTC version " + Compile.VERSION_MAJOR + "." + Compile.VERSION_MINOR + ((Compile.VERSION_DELTA > 0) ? ("." + Compile.VERSION_DELTA) : ""));
        System.err.println((Object)vers + "\n" + new ErrorMsg("COMPILE_USAGE_STR"));
    }
    
    public static void main(final String[] args) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_1        /* inputIsURL */
        //     2: iconst_0       
        //     3: istore_2        /* useStdIn */
        //     4: iconst_0       
        //     5: istore_3        /* classNameSet */
        //     6: new             Lorg/apache/xalan/xsltc/cmdline/getopt/GetOpt;
        //     9: dup            
        //    10: aload_0         /* args */
        //    11: ldc             "o:d:j:p:uxhsinv"
        //    13: invokespecial   org/apache/xalan/xsltc/cmdline/getopt/GetOpt.<init>:([Ljava/lang/String;Ljava/lang/String;)V
        //    16: astore          getopt
        //    18: aload_0         /* args */
        //    19: arraylength    
        //    20: iconst_1       
        //    21: if_icmpge       27
        //    24: invokestatic    org/apache/xalan/xsltc/cmdline/Compile.printUsage:()V
        //    27: new             Lorg/apache/xalan/xsltc/compiler/XSLTC;
        //    30: dup            
        //    31: invokespecial   org/apache/xalan/xsltc/compiler/XSLTC.<init>:()V
        //    34: astore          xsltc
        //    36: aload           xsltc
        //    38: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.init:()V
        //    41: goto            230
        //    44: iload           6
        //    46: tableswitch {
        //              200: 164
        //              201: 227
        //              202: 227
        //              203: 227
        //              204: 227
        //              205: 144
        //              206: 191
        //              207: 227
        //              208: 227
        //              209: 227
        //              210: 218
        //              211: 149
        //              212: 178
        //              213: 227
        //              214: 227
        //              215: 227
        //              216: 227
        //              217: 213
        //              218: 227
        //              219: 227
        //              220: 204
        //          default: 227
        //        }
        //   144: iconst_1       
        //   145: istore_2        /* useStdIn */
        //   146: goto            230
        //   149: aload           xsltc
        //   151: aload           getopt
        //   153: invokevirtual   org/apache/xalan/xsltc/cmdline/getopt/GetOpt.getOptionArg:()Ljava/lang/String;
        //   156: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.setClassName:(Ljava/lang/String;)V
        //   159: iconst_1       
        //   160: istore_3        /* classNameSet */
        //   161: goto            230
        //   164: aload           xsltc
        //   166: aload           getopt
        //   168: invokevirtual   org/apache/xalan/xsltc/cmdline/getopt/GetOpt.getOptionArg:()Ljava/lang/String;
        //   171: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.setDestDirectory:(Ljava/lang/String;)Z
        //   174: pop            
        //   175: goto            230
        //   178: aload           xsltc
        //   180: aload           getopt
        //   182: invokevirtual   org/apache/xalan/xsltc/cmdline/getopt/GetOpt.getOptionArg:()Ljava/lang/String;
        //   185: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.setPackageName:(Ljava/lang/String;)V
        //   188: goto            230
        //   191: aload           xsltc
        //   193: aload           getopt
        //   195: invokevirtual   org/apache/xalan/xsltc/cmdline/getopt/GetOpt.getOptionArg:()Ljava/lang/String;
        //   198: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.setJarFileName:(Ljava/lang/String;)V
        //   201: goto            230
        //   204: aload           xsltc
        //   206: iconst_1       
        //   207: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.setDebug:(Z)V
        //   210: goto            230
        //   213: iconst_1       
        //   214: istore_1        /* inputIsURL */
        //   215: goto            230
        //   218: aload           xsltc
        //   220: iconst_1       
        //   221: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.setTemplateInlining:(Z)V
        //   224: goto            230
        //   227: invokestatic    org/apache/xalan/xsltc/cmdline/Compile.printUsage:()V
        //   230: aload           getopt
        //   232: invokevirtual   org/apache/xalan/xsltc/cmdline/getopt/GetOpt.getNextOption:()I
        //   235: dup            
        //   236: istore          c
        //   238: iconst_m1      
        //   239: if_icmpne       44
        //   242: iload_2         /* useStdIn */
        //   243: ifeq            283
        //   246: iload_3         /* classNameSet */
        //   247: ifne            265
        //   250: getstatic       java/lang/System.err:Ljava/io/PrintStream;
        //   253: new             Lorg/apache/xalan/xsltc/compiler/util/ErrorMsg;
        //   256: dup            
        //   257: ldc             "COMPILE_STDIN_ERR"
        //   259: invokespecial   org/apache/xalan/xsltc/compiler/util/ErrorMsg.<init>:(Ljava/lang/String;)V
        //   262: invokevirtual   java/io/PrintStream.println:(Ljava/lang/Object;)V
        //   265: aload           xsltc
        //   267: getstatic       java/lang/System.in:Ljava/io/InputStream;
        //   270: aload           xsltc
        //   272: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.getClassName:()Ljava/lang/String;
        //   275: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.compile:(Ljava/io/InputStream;Ljava/lang/String;)Z
        //   278: istore          compileOK
        //   280: goto            371
        //   283: aload           getopt
        //   285: invokevirtual   org/apache/xalan/xsltc/cmdline/getopt/GetOpt.getCmdArgs:()[Ljava/lang/String;
        //   288: astore          stylesheetNames
        //   290: new             Ljava/util/Vector;
        //   293: dup            
        //   294: invokespecial   java/util/Vector.<init>:()V
        //   297: astore          stylesheetVector
        //   299: iconst_0       
        //   300: istore          i
        //   302: goto            354
        //   305: aload           stylesheetNames
        //   307: iload           i
        //   309: aaload         
        //   310: astore          name
        //   312: iload_1         /* inputIsURL */
        //   313: ifeq            330
        //   316: new             Ljava/net/URL;
        //   319: dup            
        //   320: aload           name
        //   322: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //   325: astore          url
        //   327: goto            344
        //   330: new             Ljava/io/File;
        //   333: dup            
        //   334: aload           name
        //   336: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //   339: invokevirtual   java/io/File.toURL:()Ljava/net/URL;
        //   342: astore          url
        //   344: aload           stylesheetVector
        //   346: aload           url
        //   348: invokevirtual   java/util/Vector.addElement:(Ljava/lang/Object;)V
        //   351: iinc            i, 1
        //   354: iload           i
        //   356: aload           stylesheetNames
        //   358: arraylength    
        //   359: if_icmplt       305
        //   362: aload           xsltc
        //   364: aload           stylesheetVector
        //   366: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.compile:(Ljava/util/Vector;)Z
        //   369: istore          compileOK
        //   371: iload           compileOK
        //   373: ifeq            397
        //   376: aload           xsltc
        //   378: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.printWarnings:()V
        //   381: aload           xsltc
        //   383: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.getJarFileName:()Ljava/lang/String;
        //   386: ifnull          407
        //   389: aload           xsltc
        //   391: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.outputToJar:()V
        //   394: goto            407
        //   397: aload           xsltc
        //   399: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.printWarnings:()V
        //   402: aload           xsltc
        //   404: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.printErrors:()V
        //   407: goto            429
        //   410: astore_1        /* ex */
        //   411: getstatic       java/lang/System.err:Ljava/io/PrintStream;
        //   414: aload_1         /* ex */
        //   415: invokevirtual   java/io/PrintStream.println:(Ljava/lang/Object;)V
        //   418: invokestatic    org/apache/xalan/xsltc/cmdline/Compile.printUsage:()V
        //   421: goto            429
        //   424: astore_2        /* e */
        //   425: aload_2         /* e */
        //   426: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //   429: return         
        //    LocalVariableTable:
        //  Start  Length  Slot  Name              Signature
        //  -----  ------  ----  ----------------  --------------------------------------------------------
        //  0      430     0     args              [Ljava/lang/String;
        //  2      405     1     inputIsURL        Z
        //  4      403     2     useStdIn          Z
        //  6      401     3     classNameSet      Z
        //  18     389     4     getopt            Lorg/apache/xalan/xsltc/cmdline/getopt/GetOpt;
        //  36     371     5     xsltc             Lorg/apache/xalan/xsltc/compiler/XSLTC;
        //  238    169     6     c                 I
        //  280    127     7     compileOK         Z
        //  290    81      8     stylesheetNames   [Ljava/lang/String;
        //  299    72      9     stylesheetVector  Ljava/util/Vector;
        //  302    69      10    i                 I
        //  312    39      11    name              Ljava/lang/String;
        //  327    24      12    url               Ljava/net/URL;
        //  411    18      1     ex                Lorg/apache/xalan/xsltc/cmdline/getopt/GetOptsException;
        //  425    4       2     e                 Ljava/lang/Exception;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                    
        //  -----  -----  -----  -----  --------------------------------------------------------
        //  0      407    410    424    Lorg/apache/xalan/xsltc/cmdline/getopt/GetOptsException;
        //  0      407    424    429    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static {
        Compile.VERSION_MAJOR = 1;
        Compile.VERSION_MINOR = 4;
        Compile.VERSION_DELTA = 0;
    }
}
