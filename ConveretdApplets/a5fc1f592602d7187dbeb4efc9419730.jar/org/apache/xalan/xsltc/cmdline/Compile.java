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
    private static boolean _allowExit;
    
    public static void printUsage() {
        final StringBuffer vers = new StringBuffer("XSLTC version " + Compile.VERSION_MAJOR + "." + Compile.VERSION_MINOR + ((Compile.VERSION_DELTA > 0) ? ("." + Compile.VERSION_DELTA) : ""));
        System.err.println((Object)vers + "\n" + new ErrorMsg("COMPILE_USAGE_STR"));
        if (Compile._allowExit) {
            System.exit(-1);
        }
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
        //    41: goto            237
        //    44: iload           6
        //    46: tableswitch {
        //              200: 164
        //              201: 234
        //              202: 234
        //              203: 234
        //              204: 234
        //              205: 144
        //              206: 191
        //              207: 234
        //              208: 234
        //              209: 234
        //              210: 225
        //              211: 149
        //              212: 178
        //              213: 234
        //              214: 234
        //              215: 218
        //              216: 234
        //              217: 213
        //              218: 234
        //              219: 234
        //              220: 204
        //          default: 234
        //        }
        //   144: iconst_1       
        //   145: istore_2        /* useStdIn */
        //   146: goto            237
        //   149: aload           xsltc
        //   151: aload           getopt
        //   153: invokevirtual   org/apache/xalan/xsltc/cmdline/getopt/GetOpt.getOptionArg:()Ljava/lang/String;
        //   156: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.setClassName:(Ljava/lang/String;)V
        //   159: iconst_1       
        //   160: istore_3        /* classNameSet */
        //   161: goto            237
        //   164: aload           xsltc
        //   166: aload           getopt
        //   168: invokevirtual   org/apache/xalan/xsltc/cmdline/getopt/GetOpt.getOptionArg:()Ljava/lang/String;
        //   171: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.setDestDirectory:(Ljava/lang/String;)Z
        //   174: pop            
        //   175: goto            237
        //   178: aload           xsltc
        //   180: aload           getopt
        //   182: invokevirtual   org/apache/xalan/xsltc/cmdline/getopt/GetOpt.getOptionArg:()Ljava/lang/String;
        //   185: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.setPackageName:(Ljava/lang/String;)V
        //   188: goto            237
        //   191: aload           xsltc
        //   193: aload           getopt
        //   195: invokevirtual   org/apache/xalan/xsltc/cmdline/getopt/GetOpt.getOptionArg:()Ljava/lang/String;
        //   198: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.setJarFileName:(Ljava/lang/String;)V
        //   201: goto            237
        //   204: aload           xsltc
        //   206: iconst_1       
        //   207: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.setDebug:(Z)V
        //   210: goto            237
        //   213: iconst_1       
        //   214: istore_1        /* inputIsURL */
        //   215: goto            237
        //   218: iconst_0       
        //   219: putstatic       org/apache/xalan/xsltc/cmdline/Compile._allowExit:Z
        //   222: goto            237
        //   225: aload           xsltc
        //   227: iconst_1       
        //   228: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.setTemplateInlining:(Z)V
        //   231: goto            237
        //   234: invokestatic    org/apache/xalan/xsltc/cmdline/Compile.printUsage:()V
        //   237: aload           getopt
        //   239: invokevirtual   org/apache/xalan/xsltc/cmdline/getopt/GetOpt.getNextOption:()I
        //   242: dup            
        //   243: istore          c
        //   245: iconst_m1      
        //   246: if_icmpne       44
        //   249: iload_2         /* useStdIn */
        //   250: ifeq            300
        //   253: iload_3         /* classNameSet */
        //   254: ifne            282
        //   257: getstatic       java/lang/System.err:Ljava/io/PrintStream;
        //   260: new             Lorg/apache/xalan/xsltc/compiler/util/ErrorMsg;
        //   263: dup            
        //   264: ldc             "COMPILE_STDIN_ERR"
        //   266: invokespecial   org/apache/xalan/xsltc/compiler/util/ErrorMsg.<init>:(Ljava/lang/String;)V
        //   269: invokevirtual   java/io/PrintStream.println:(Ljava/lang/Object;)V
        //   272: getstatic       org/apache/xalan/xsltc/cmdline/Compile._allowExit:Z
        //   275: ifeq            282
        //   278: iconst_m1      
        //   279: invokestatic    java/lang/System.exit:(I)V
        //   282: aload           xsltc
        //   284: getstatic       java/lang/System.in:Ljava/io/InputStream;
        //   287: aload           xsltc
        //   289: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.getClassName:()Ljava/lang/String;
        //   292: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.compile:(Ljava/io/InputStream;Ljava/lang/String;)Z
        //   295: istore          compileOK
        //   297: goto            388
        //   300: aload           getopt
        //   302: invokevirtual   org/apache/xalan/xsltc/cmdline/getopt/GetOpt.getCmdArgs:()[Ljava/lang/String;
        //   305: astore          stylesheetNames
        //   307: new             Ljava/util/Vector;
        //   310: dup            
        //   311: invokespecial   java/util/Vector.<init>:()V
        //   314: astore          stylesheetVector
        //   316: iconst_0       
        //   317: istore          i
        //   319: goto            371
        //   322: aload           stylesheetNames
        //   324: iload           i
        //   326: aaload         
        //   327: astore          name
        //   329: iload_1         /* inputIsURL */
        //   330: ifeq            347
        //   333: new             Ljava/net/URL;
        //   336: dup            
        //   337: aload           name
        //   339: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //   342: astore          url
        //   344: goto            361
        //   347: new             Ljava/io/File;
        //   350: dup            
        //   351: aload           name
        //   353: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //   356: invokevirtual   java/io/File.toURL:()Ljava/net/URL;
        //   359: astore          url
        //   361: aload           stylesheetVector
        //   363: aload           url
        //   365: invokevirtual   java/util/Vector.addElement:(Ljava/lang/Object;)V
        //   368: iinc            i, 1
        //   371: iload           i
        //   373: aload           stylesheetNames
        //   375: arraylength    
        //   376: if_icmplt       322
        //   379: aload           xsltc
        //   381: aload           stylesheetVector
        //   383: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.compile:(Ljava/util/Vector;)Z
        //   386: istore          compileOK
        //   388: iload           compileOK
        //   390: ifeq            424
        //   393: aload           xsltc
        //   395: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.printWarnings:()V
        //   398: aload           xsltc
        //   400: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.getJarFileName:()Ljava/lang/String;
        //   403: ifnull          411
        //   406: aload           xsltc
        //   408: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.outputToJar:()V
        //   411: getstatic       org/apache/xalan/xsltc/cmdline/Compile._allowExit:Z
        //   414: ifeq            444
        //   417: iconst_0       
        //   418: invokestatic    java/lang/System.exit:(I)V
        //   421: goto            444
        //   424: aload           xsltc
        //   426: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.printWarnings:()V
        //   429: aload           xsltc
        //   431: invokevirtual   org/apache/xalan/xsltc/compiler/XSLTC.printErrors:()V
        //   434: getstatic       org/apache/xalan/xsltc/cmdline/Compile._allowExit:Z
        //   437: ifeq            444
        //   440: iconst_m1      
        //   441: invokestatic    java/lang/System.exit:(I)V
        //   444: goto            476
        //   447: astore_1        /* ex */
        //   448: getstatic       java/lang/System.err:Ljava/io/PrintStream;
        //   451: aload_1         /* ex */
        //   452: invokevirtual   java/io/PrintStream.println:(Ljava/lang/Object;)V
        //   455: invokestatic    org/apache/xalan/xsltc/cmdline/Compile.printUsage:()V
        //   458: goto            476
        //   461: astore_2        /* e */
        //   462: aload_2         /* e */
        //   463: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //   466: getstatic       org/apache/xalan/xsltc/cmdline/Compile._allowExit:Z
        //   469: ifeq            476
        //   472: iconst_m1      
        //   473: invokestatic    java/lang/System.exit:(I)V
        //   476: return         
        //    LocalVariableTable:
        //  Start  Length  Slot  Name              Signature
        //  -----  ------  ----  ----------------  --------------------------------------------------------
        //  0      477     0     args              [Ljava/lang/String;
        //  2      442     1     inputIsURL        Z
        //  4      440     2     useStdIn          Z
        //  6      438     3     classNameSet      Z
        //  18     426     4     getopt            Lorg/apache/xalan/xsltc/cmdline/getopt/GetOpt;
        //  36     408     5     xsltc             Lorg/apache/xalan/xsltc/compiler/XSLTC;
        //  245    199     6     c                 I
        //  297    147     7     compileOK         Z
        //  307    81      8     stylesheetNames   [Ljava/lang/String;
        //  316    72      9     stylesheetVector  Ljava/util/Vector;
        //  319    69      10    i                 I
        //  329    39      11    name              Ljava/lang/String;
        //  344    24      12    url               Ljava/net/URL;
        //  448    28      1     ex                Lorg/apache/xalan/xsltc/cmdline/getopt/GetOptsException;
        //  462    14      2     e                 Ljava/lang/Exception;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                    
        //  -----  -----  -----  -----  --------------------------------------------------------
        //  0      444    447    461    Lorg/apache/xalan/xsltc/cmdline/getopt/GetOptsException;
        //  0      444    461    476    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static {
        Compile.VERSION_MAJOR = 1;
        Compile.VERSION_MINOR = 4;
        Compile.VERSION_DELTA = 0;
        Compile._allowExit = true;
    }
}
