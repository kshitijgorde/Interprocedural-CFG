// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta.plugin;

import java.awt.Menu;
import java.awt.Component;
import de.mud.jta.PluginMessage;
import de.mud.jta.event.SocketRequest;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.awt.GridBagConstraints;
import de.mud.jta.PluginListener;
import de.mud.jta.PluginConfig;
import de.mud.jta.event.ConfigurationListener;
import de.mud.jta.PluginBus;
import java.util.Hashtable;
import java.awt.Panel;
import java.awt.event.ActionListener;
import de.mud.jta.VisualPlugin;
import de.mud.jta.FilterPlugin;
import de.mud.jta.Plugin;

public class ButtonBar extends Plugin implements FilterPlugin, VisualPlugin, ActionListener
{
    protected Panel panel;
    private Hashtable buttons;
    private Hashtable fields;
    private boolean clearFields;
    FilterPlugin source;
    
    public ButtonBar(final PluginBus bus, final String id) {
        super(bus, id);
        this.panel = new Panel();
        this.buttons = null;
        this.fields = null;
        this.clearFields = true;
        bus.registerPluginListener(new ConfigurationListener() {
            public void setConfiguration(final PluginConfig cfg) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_1         /* cfg */
                //     1: ldc             "ButtonBar"
                //     3: aload_0         /* this */
                //     4: getfield        de/mud/jta/plugin/ButtonBar$1.val$id:Ljava/lang/String;
                //     7: ldc             "setup"
                //     9: invokevirtual   de/mud/jta/PluginConfig.getProperty:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
                //    12: astore_2        /* file */
                //    13: aload_0         /* this */
                //    14: getfield        de/mud/jta/plugin/ButtonBar$1.this$0:Lde/mud/jta/plugin/ButtonBar;
                //    17: new             Ljava/lang/Boolean;
                //    20: dup            
                //    21: aload_1         /* cfg */
                //    22: ldc             "ButtonBar"
                //    24: aload_0         /* this */
                //    25: getfield        de/mud/jta/plugin/ButtonBar$1.val$id:Ljava/lang/String;
                //    28: ldc             "clearFields"
                //    30: invokevirtual   de/mud/jta/PluginConfig.getProperty:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
                //    33: invokespecial   java/lang/Boolean.<init>:(Ljava/lang/String;)V
                //    36: invokevirtual   java/lang/Boolean.booleanValue:()Z
                //    39: invokestatic    de/mud/jta/plugin/ButtonBar.access$002:(Lde/mud/jta/plugin/ButtonBar;Z)Z
                //    42: pop            
                //    43: aload_2         /* file */
                //    44: ifnonnull       57
                //    47: aload_0         /* this */
                //    48: getfield        de/mud/jta/plugin/ButtonBar$1.this$0:Lde/mud/jta/plugin/ButtonBar;
                //    51: ldc             "no setup file"
                //    53: invokevirtual   de/mud/jta/Plugin.error:(Ljava/lang/String;)V
                //    56: return         
                //    57: aconst_null    
                //    58: astore_3        /* setup */
                //    59: aconst_null    
                //    60: astore          is
                //    62: aload_0         /* this */
                //    63: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
                //    66: aload_2         /* file */
                //    67: invokevirtual   java/lang/Class.getResourceAsStream:(Ljava/lang/String;)Ljava/io/InputStream;
                //    70: astore          is
                //    72: goto            77
                //    75: astore          e
                //    77: aload           is
                //    79: ifnonnull       127
                //    82: new             Ljava/net/URL;
                //    85: dup            
                //    86: aload_2         /* file */
                //    87: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
                //    90: invokevirtual   java/net/URL.openStream:()Ljava/io/InputStream;
                //    93: astore          is
                //    95: goto            127
                //    98: astore          ue
                //   100: aload_0         /* this */
                //   101: getfield        de/mud/jta/plugin/ButtonBar$1.this$0:Lde/mud/jta/plugin/ButtonBar;
                //   104: new             Ljava/lang/StringBuffer;
                //   107: dup            
                //   108: invokespecial   java/lang/StringBuffer.<init>:()V
                //   111: ldc             "could not find: "
                //   113: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
                //   116: aload_2         /* file */
                //   117: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
                //   120: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
                //   123: invokevirtual   de/mud/jta/Plugin.error:(Ljava/lang/String;)V
                //   126: return         
                //   127: new             Ljava/io/InputStreamReader;
                //   130: dup            
                //   131: aload           is
                //   133: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
                //   136: astore          ir
                //   138: new             Ljava/io/StreamTokenizer;
                //   141: dup            
                //   142: new             Ljava/io/BufferedReader;
                //   145: dup            
                //   146: aload           ir
                //   148: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
                //   151: invokespecial   java/io/StreamTokenizer.<init>:(Ljava/io/Reader;)V
                //   154: astore_3        /* setup */
                //   155: goto            197
                //   158: astore          e
                //   160: aload_0         /* this */
                //   161: getfield        de/mud/jta/plugin/ButtonBar$1.this$0:Lde/mud/jta/plugin/ButtonBar;
                //   164: new             Ljava/lang/StringBuffer;
                //   167: dup            
                //   168: invokespecial   java/lang/StringBuffer.<init>:()V
                //   171: ldc             "cannot load "
                //   173: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
                //   176: aload_2         /* file */
                //   177: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
                //   180: ldc             ": "
                //   182: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
                //   185: aload           e
                //   187: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/Object;)Ljava/lang/StringBuffer;
                //   190: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
                //   193: invokevirtual   de/mud/jta/Plugin.error:(Ljava/lang/String;)V
                //   196: return         
                //   197: aload_3         /* setup */
                //   198: bipush          35
                //   200: invokevirtual   java/io/StreamTokenizer.commentChar:(I)V
                //   203: aload_3         /* setup */
                //   204: bipush          34
                //   206: invokevirtual   java/io/StreamTokenizer.quoteChar:(I)V
                //   209: aload_0         /* this */
                //   210: getfield        de/mud/jta/plugin/ButtonBar$1.this$0:Lde/mud/jta/plugin/ButtonBar;
                //   213: new             Ljava/util/Hashtable;
                //   216: dup            
                //   217: invokespecial   java/util/Hashtable.<init>:()V
                //   220: invokestatic    de/mud/jta/plugin/ButtonBar.access$102:(Lde/mud/jta/plugin/ButtonBar;Ljava/util/Hashtable;)Ljava/util/Hashtable;
                //   223: pop            
                //   224: aload_0         /* this */
                //   225: getfield        de/mud/jta/plugin/ButtonBar$1.this$0:Lde/mud/jta/plugin/ButtonBar;
                //   228: new             Ljava/util/Hashtable;
                //   231: dup            
                //   232: invokespecial   java/util/Hashtable.<init>:()V
                //   235: invokestatic    de/mud/jta/plugin/ButtonBar.access$202:(Lde/mud/jta/plugin/ButtonBar;Ljava/util/Hashtable;)Ljava/util/Hashtable;
                //   238: pop            
                //   239: new             Ljava/awt/GridBagLayout;
                //   242: dup            
                //   243: invokespecial   java/awt/GridBagLayout.<init>:()V
                //   246: astore          l
                //   248: new             Ljava/awt/GridBagConstraints;
                //   251: dup            
                //   252: invokespecial   java/awt/GridBagConstraints.<init>:()V
                //   255: astore          c
                //   257: aload_0         /* this */
                //   258: getfield        de/mud/jta/plugin/ButtonBar$1.this$0:Lde/mud/jta/plugin/ButtonBar;
                //   261: getfield        de/mud/jta/plugin/ButtonBar.panel:Ljava/awt/Panel;
                //   264: aload           l
                //   266: invokevirtual   java/awt/Container.setLayout:(Ljava/awt/LayoutManager;)V
                //   269: aload           c
                //   271: iconst_1       
                //   272: putfield        java/awt/GridBagConstraints.fill:I
                //   275: goto            830
                //   278: iload           8
                //   280: lookupswitch {
                //               -3: 300
                //          default: 801
                //        }
                //   300: aload           c
                //   302: iconst_1       
                //   303: putfield        java/awt/GridBagConstraints.gridwidth:I
                //   306: aload           c
                //   308: dconst_0       
                //   309: putfield        java/awt/GridBagConstraints.weightx:D
                //   312: aload           c
                //   314: dconst_0       
                //   315: putfield        java/awt/GridBagConstraints.weighty:D
                //   318: aload_3         /* setup */
                //   319: getfield        java/io/StreamTokenizer.sval:Ljava/lang/String;
                //   322: ldc             "button"
                //   324: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                //   327: ifeq            470
                //   330: aload_3         /* setup */
                //   331: invokevirtual   java/io/StreamTokenizer.nextToken:()I
                //   334: dup            
                //   335: istore          token
                //   337: iconst_m1      
                //   338: if_icmpeq       458
                //   341: aload_3         /* setup */
                //   342: getfield        java/io/StreamTokenizer.sval:Ljava/lang/String;
                //   345: astore          descr
                //   347: aload_3         /* setup */
                //   348: invokevirtual   java/io/StreamTokenizer.nextToken:()I
                //   351: dup            
                //   352: istore          token
                //   354: iconst_m1      
                //   355: if_icmpeq       428
                //   358: new             Ljava/awt/Button;
                //   361: dup            
                //   362: aload           descr
                //   364: invokespecial   java/awt/Button.<init>:(Ljava/lang/String;)V
                //   367: astore          b
                //   369: aload_0         /* this */
                //   370: getfield        de/mud/jta/plugin/ButtonBar$1.this$0:Lde/mud/jta/plugin/ButtonBar;
                //   373: invokestatic    de/mud/jta/plugin/ButtonBar.access$200:(Lde/mud/jta/plugin/ButtonBar;)Ljava/util/Hashtable;
                //   376: aload           b
                //   378: aload_3         /* setup */
                //   379: getfield        java/io/StreamTokenizer.sval:Ljava/lang/String;
                //   382: invokevirtual   java/util/Hashtable.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
                //   385: pop            
                //   386: aload           b
                //   388: aload_0         /* this */
                //   389: getfield        de/mud/jta/plugin/ButtonBar$1.this$0:Lde/mud/jta/plugin/ButtonBar;
                //   392: invokevirtual   java/awt/Button.addActionListener:(Ljava/awt/event/ActionListener;)V
                //   395: aload           l
                //   397: aload           b
                //   399: aload_0         /* this */
                //   400: getfield        de/mud/jta/plugin/ButtonBar$1.this$0:Lde/mud/jta/plugin/ButtonBar;
                //   403: aload           c
                //   405: aload_3         /* setup */
                //   406: invokestatic    de/mud/jta/plugin/ButtonBar.access$300:(Lde/mud/jta/plugin/ButtonBar;Ljava/awt/GridBagConstraints;Ljava/io/StreamTokenizer;)Ljava/awt/GridBagConstraints;
                //   409: invokevirtual   java/awt/GridBagLayout.setConstraints:(Ljava/awt/Component;Ljava/awt/GridBagConstraints;)V
                //   412: aload_0         /* this */
                //   413: getfield        de/mud/jta/plugin/ButtonBar$1.this$0:Lde/mud/jta/plugin/ButtonBar;
                //   416: getfield        de/mud/jta/plugin/ButtonBar.panel:Ljava/awt/Panel;
                //   419: aload           b
                //   421: invokevirtual   java/awt/Container.add:(Ljava/awt/Component;)Ljava/awt/Component;
                //   424: pop            
                //   425: goto            455
                //   428: aload_0         /* this */
                //   429: getfield        de/mud/jta/plugin/ButtonBar$1.this$0:Lde/mud/jta/plugin/ButtonBar;
                //   432: new             Ljava/lang/StringBuffer;
                //   435: dup            
                //   436: invokespecial   java/lang/StringBuffer.<init>:()V
                //   439: aload           descr
                //   441: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
                //   444: ldc             ": missing button command"
                //   446: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
                //   449: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
                //   452: invokevirtual   de/mud/jta/Plugin.error:(Ljava/lang/String;)V
                //   455: goto            830
                //   458: aload_0         /* this */
                //   459: getfield        de/mud/jta/plugin/ButtonBar$1.this$0:Lde/mud/jta/plugin/ButtonBar;
                //   462: ldc             "unexpected end of file"
                //   464: invokevirtual   de/mud/jta/Plugin.error:(Ljava/lang/String;)V
                //   467: goto            830
                //   470: aload_3         /* setup */
                //   471: getfield        java/io/StreamTokenizer.sval:Ljava/lang/String;
                //   474: ldc             "input"
                //   476: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                //   479: ifeq            830
                //   482: aload_3         /* setup */
                //   483: invokevirtual   java/io/StreamTokenizer.nextToken:()I
                //   486: dup            
                //   487: istore          token
                //   489: iconst_m1      
                //   490: if_icmpeq       789
                //   493: aload_3         /* setup */
                //   494: getfield        java/io/StreamTokenizer.sval:Ljava/lang/String;
                //   497: astore          descr
                //   499: aload_3         /* setup */
                //   500: invokevirtual   java/io/StreamTokenizer.nextToken:()I
                //   503: dup            
                //   504: istore          token
                //   506: bipush          -2
                //   508: if_icmpne       759
                //   511: aload_3         /* setup */
                //   512: getfield        java/io/StreamTokenizer.nval:D
                //   515: d2i            
                //   516: istore          size
                //   518: ldc             ""
                //   520: astore          init
                //   522: ldc             ""
                //   524: astore          command
                //   526: aload_3         /* setup */
                //   527: invokevirtual   java/io/StreamTokenizer.nextToken:()I
                //   530: istore          token
                //   532: aload_3         /* setup */
                //   533: getfield        java/io/StreamTokenizer.sval:Ljava/lang/String;
                //   536: ldc             "button"
                //   538: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                //   541: ifne            580
                //   544: aload_3         /* setup */
                //   545: getfield        java/io/StreamTokenizer.sval:Ljava/lang/String;
                //   548: ldc             "input"
                //   550: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                //   553: ifne            580
                //   556: aload_3         /* setup */
                //   557: getfield        java/io/StreamTokenizer.sval:Ljava/lang/String;
                //   560: ldc             "stretch"
                //   562: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                //   565: ifne            580
                //   568: aload_3         /* setup */
                //   569: getfield        java/io/StreamTokenizer.sval:Ljava/lang/String;
                //   572: ldc             "break"
                //   574: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                //   577: ifeq            587
                //   580: aload_3         /* setup */
                //   581: invokevirtual   java/io/StreamTokenizer.pushBack:()V
                //   584: goto            593
                //   587: aload_3         /* setup */
                //   588: getfield        java/io/StreamTokenizer.sval:Ljava/lang/String;
                //   591: astore          command
                //   593: aload_3         /* setup */
                //   594: invokevirtual   java/io/StreamTokenizer.nextToken:()I
                //   597: istore          token
                //   599: aload_3         /* setup */
                //   600: getfield        java/io/StreamTokenizer.sval:Ljava/lang/String;
                //   603: ldc             "button"
                //   605: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                //   608: ifne            647
                //   611: aload_3         /* setup */
                //   612: getfield        java/io/StreamTokenizer.sval:Ljava/lang/String;
                //   615: ldc             "input"
                //   617: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                //   620: ifne            647
                //   623: aload_3         /* setup */
                //   624: getfield        java/io/StreamTokenizer.sval:Ljava/lang/String;
                //   627: ldc             "stretch"
                //   629: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                //   632: ifne            647
                //   635: aload_3         /* setup */
                //   636: getfield        java/io/StreamTokenizer.sval:Ljava/lang/String;
                //   639: ldc             "break"
                //   641: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                //   644: ifeq            658
                //   647: aload_3         /* setup */
                //   648: invokevirtual   java/io/StreamTokenizer.pushBack:()V
                //   651: aload           command
                //   653: astore          init
                //   655: goto            664
                //   658: aload_3         /* setup */
                //   659: getfield        java/io/StreamTokenizer.sval:Ljava/lang/String;
                //   662: astore          init
                //   664: new             Ljava/awt/TextField;
                //   667: dup            
                //   668: aload           init
                //   670: iload           size
                //   672: invokespecial   java/awt/TextField.<init>:(Ljava/lang/String;I)V
                //   675: astore          t
                //   677: aload           init
                //   679: aload           command
                //   681: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                //   684: ifne            711
                //   687: aload_0         /* this */
                //   688: getfield        de/mud/jta/plugin/ButtonBar$1.this$0:Lde/mud/jta/plugin/ButtonBar;
                //   691: invokestatic    de/mud/jta/plugin/ButtonBar.access$200:(Lde/mud/jta/plugin/ButtonBar;)Ljava/util/Hashtable;
                //   694: aload           t
                //   696: aload           command
                //   698: invokevirtual   java/util/Hashtable.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
                //   701: pop            
                //   702: aload           t
                //   704: aload_0         /* this */
                //   705: getfield        de/mud/jta/plugin/ButtonBar$1.this$0:Lde/mud/jta/plugin/ButtonBar;
                //   708: invokevirtual   java/awt/TextField.addActionListener:(Ljava/awt/event/ActionListener;)V
                //   711: aload_0         /* this */
                //   712: getfield        de/mud/jta/plugin/ButtonBar$1.this$0:Lde/mud/jta/plugin/ButtonBar;
                //   715: invokestatic    de/mud/jta/plugin/ButtonBar.access$100:(Lde/mud/jta/plugin/ButtonBar;)Ljava/util/Hashtable;
                //   718: aload           descr
                //   720: aload           t
                //   722: invokevirtual   java/util/Hashtable.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
                //   725: pop            
                //   726: aload           l
                //   728: aload           t
                //   730: aload_0         /* this */
                //   731: getfield        de/mud/jta/plugin/ButtonBar$1.this$0:Lde/mud/jta/plugin/ButtonBar;
                //   734: aload           c
                //   736: aload_3         /* setup */
                //   737: invokestatic    de/mud/jta/plugin/ButtonBar.access$300:(Lde/mud/jta/plugin/ButtonBar;Ljava/awt/GridBagConstraints;Ljava/io/StreamTokenizer;)Ljava/awt/GridBagConstraints;
                //   740: invokevirtual   java/awt/GridBagLayout.setConstraints:(Ljava/awt/Component;Ljava/awt/GridBagConstraints;)V
                //   743: aload_0         /* this */
                //   744: getfield        de/mud/jta/plugin/ButtonBar$1.this$0:Lde/mud/jta/plugin/ButtonBar;
                //   747: getfield        de/mud/jta/plugin/ButtonBar.panel:Ljava/awt/Panel;
                //   750: aload           t
                //   752: invokevirtual   java/awt/Container.add:(Ljava/awt/Component;)Ljava/awt/Component;
                //   755: pop            
                //   756: goto            786
                //   759: aload_0         /* this */
                //   760: getfield        de/mud/jta/plugin/ButtonBar$1.this$0:Lde/mud/jta/plugin/ButtonBar;
                //   763: new             Ljava/lang/StringBuffer;
                //   766: dup            
                //   767: invokespecial   java/lang/StringBuffer.<init>:()V
                //   770: aload           descr
                //   772: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
                //   775: ldc             ": missing field size"
                //   777: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
                //   780: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
                //   783: invokevirtual   de/mud/jta/Plugin.error:(Ljava/lang/String;)V
                //   786: goto            830
                //   789: aload_0         /* this */
                //   790: getfield        de/mud/jta/plugin/ButtonBar$1.this$0:Lde/mud/jta/plugin/ButtonBar;
                //   793: ldc             "unexpected end of file"
                //   795: invokevirtual   de/mud/jta/Plugin.error:(Ljava/lang/String;)V
                //   798: goto            830
                //   801: aload_0         /* this */
                //   802: getfield        de/mud/jta/plugin/ButtonBar$1.this$0:Lde/mud/jta/plugin/ButtonBar;
                //   805: new             Ljava/lang/StringBuffer;
                //   808: dup            
                //   809: invokespecial   java/lang/StringBuffer.<init>:()V
                //   812: ldc             "syntax error at line "
                //   814: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
                //   817: aload_3         /* setup */
                //   818: invokevirtual   java/io/StreamTokenizer.lineno:()I
                //   821: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
                //   824: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
                //   827: invokevirtual   de/mud/jta/Plugin.error:(Ljava/lang/String;)V
                //   830: aload_3         /* setup */
                //   831: invokevirtual   java/io/StreamTokenizer.nextToken:()I
                //   834: dup            
                //   835: istore          token
                //   837: iconst_m1      
                //   838: if_icmpne       278
                //   841: goto            873
                //   844: astore          e
                //   846: aload_0         /* this */
                //   847: getfield        de/mud/jta/plugin/ButtonBar$1.this$0:Lde/mud/jta/plugin/ButtonBar;
                //   850: new             Ljava/lang/StringBuffer;
                //   853: dup            
                //   854: invokespecial   java/lang/StringBuffer.<init>:()V
                //   857: ldc             "unexpected error while reading setup: "
                //   859: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
                //   862: aload           e
                //   864: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/Object;)Ljava/lang/StringBuffer;
                //   867: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
                //   870: invokevirtual   de/mud/jta/Plugin.error:(Ljava/lang/String;)V
                //   873: aload_0         /* this */
                //   874: getfield        de/mud/jta/plugin/ButtonBar$1.this$0:Lde/mud/jta/plugin/ButtonBar;
                //   877: getfield        de/mud/jta/plugin/ButtonBar.panel:Ljava/awt/Panel;
                //   880: invokevirtual   java/awt/Container.validate:()V
                //   883: return         
                //    LocalVariableTable:
                //  Start  Length  Slot  Name     Signature
                //  -----  ------  ----  -------  -------------------------------
                //  0      884     0     this     Lde/mud/jta/plugin/ButtonBar$1;
                //  0      884     1     cfg      Lde/mud/jta/PluginConfig;
                //  13     870     2     file     Ljava/lang/String;
                //  59     824     3     setup    Ljava/io/StreamTokenizer;
                //  62     821     4     is       Ljava/io/InputStream;
                //  75     2       5     e        Ljava/lang/Exception;
                //  98     29      5     ue       Ljava/lang/Exception;
                //  138    17      5     ir       Ljava/io/InputStreamReader;
                //  158    39      5     e        Ljava/lang/Exception;
                //  248    635     6     l        Ljava/awt/GridBagLayout;
                //  257    626     7     c        Ljava/awt/GridBagConstraints;
                //  337    546     8     token    I
                //  347    108     9     descr    Ljava/lang/String;
                //  369    56      10    b        Ljava/awt/Button;
                //  499    287     9     descr    Ljava/lang/String;
                //  518    238     10    size     I
                //  522    234     11    init     Ljava/lang/String;
                //  526    230     12    command  Ljava/lang/String;
                //  677    79      13    t        Ljava/awt/TextField;
                //  844    29      9     e        Ljava/io/IOException;
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                 
                //  -----  -----  -----  -----  ---------------------
                //  62     72     75     77     Ljava/lang/Exception;
                //  82     95     98     127    Ljava/lang/Exception;
                //  127    155    158    197    Ljava/lang/Exception;
                //  275    841    844    873    Ljava/io/IOException;
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
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
        });
    }
    
    private GridBagConstraints constraints(final GridBagConstraints c, final StreamTokenizer setup) throws IOException {
        if (setup.nextToken() == -3) {
            if (setup.sval.equals("break")) {
                c.gridwidth = 0;
            }
            else if (setup.sval.equals("stretch")) {
                c.weightx = 1.0;
            }
            else {
                setup.pushBack();
            }
        }
        else {
            setup.pushBack();
        }
        return c;
    }
    
    public void actionPerformed(final ActionEvent evt) {
        String tmp;
        if ((tmp = this.buttons.get(evt.getSource())) != null) {
            String cmd = "";
            String function = null;
            int idx = 0;
            int oldidx = 0;
            while ((idx = tmp.indexOf(92, oldidx)) >= 0 && ++idx <= tmp.length()) {
                cmd += tmp.substring(oldidx, idx - 1);
                switch (tmp.charAt(idx)) {
                    case 'b': {
                        cmd += "\b";
                        break;
                    }
                    case 'e': {
                        cmd += "\u001b";
                        break;
                    }
                    case 'n': {
                        cmd += "\n";
                        break;
                    }
                    case 'r': {
                        cmd += "\r";
                        break;
                    }
                    case '$': {
                        int ni = tmp.indexOf(40, idx + 1);
                        if (ni < idx) {
                            this.error("ERROR: Function: missing '('");
                            break;
                        }
                        if (ni == ++idx) {
                            this.error("ERROR: Function: missing name");
                            break;
                        }
                        function = tmp.substring(idx, ni);
                        idx = ni + 1;
                        ni = tmp.indexOf(41, idx);
                        if (ni < idx) {
                            this.error("ERROR: Function: missing ')'");
                            break;
                        }
                        tmp = tmp.substring(idx, ni);
                        oldidx = (idx = 0);
                        continue;
                    }
                    case '@': {
                        final int ni = tmp.indexOf(64, idx + 1);
                        if (ni < idx) {
                            this.error("ERROR: Input Field: '@'-End Marker not found");
                            break;
                        }
                        if (ni == ++idx) {
                            this.error("ERROR: Input Field: no name specified");
                            break;
                        }
                        final String name = tmp.substring(idx, ni);
                        idx = ni;
                        final TextField t;
                        if (this.fields == null || (t = this.fields.get(name)) == null) {
                            this.error("ERROR: Input Field: requested input \"" + name + "\" does not exist");
                            break;
                        }
                        cmd += t.getText();
                        if (this.clearFields) {
                            t.setText("");
                            break;
                        }
                        break;
                    }
                    default: {
                        cmd += tmp.substring(idx, ++idx);
                        break;
                    }
                }
                oldidx = ++idx;
            }
            if (oldidx <= tmp.length()) {
                cmd += tmp.substring(oldidx, tmp.length());
            }
            if (function != null) {
                if (function.equals("exit")) {
                    try {
                        System.exit(0);
                    }
                    catch (Exception e) {
                        this.error("cannot exit: " + e);
                    }
                }
                if (function.equals("connect")) {
                    String address = null;
                    int port = -1;
                    try {
                        if ((idx = cmd.indexOf(",")) >= 0) {
                            try {
                                port = Integer.parseInt(cmd.substring(idx + 1, cmd.length()));
                            }
                            catch (Exception e2) {
                                port = -1;
                            }
                            cmd = cmd.substring(0, idx);
                        }
                        if (cmd.length() > 0) {
                            address = cmd;
                        }
                        if (address != null) {
                            if (port != -1) {
                                super.bus.broadcast(new SocketRequest(address, port));
                            }
                            else {
                                super.bus.broadcast(new SocketRequest(address, 23));
                            }
                        }
                        else {
                            this.error("connect: no address");
                        }
                    }
                    catch (Exception e2) {
                        this.error("connect(): failed");
                        e2.printStackTrace();
                    }
                }
                else if (function.equals("disconnect")) {
                    super.bus.broadcast(new SocketRequest());
                }
                else if (function.equals("detach")) {
                    this.error("detach not implemented yet");
                }
                else {
                    this.error("ERROR: function not implemented: \"" + function + "\"");
                }
                return;
            }
            if (cmd.length() > 0) {
                try {
                    this.write(cmd.getBytes());
                }
                catch (IOException e3) {
                    this.error("send: " + e3);
                }
            }
        }
    }
    
    public Component getPluginVisual() {
        return this.panel;
    }
    
    public Menu getPluginMenu() {
        return null;
    }
    
    public void setFilterSource(final FilterPlugin source) {
        this.source = source;
    }
    
    public int read(final byte[] b) throws IOException {
        return this.source.read(b);
    }
    
    public void write(final byte[] b) throws IOException {
        this.source.write(b);
    }
}
