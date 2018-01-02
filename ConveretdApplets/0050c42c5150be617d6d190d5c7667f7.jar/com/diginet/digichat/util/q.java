// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.util;

import java.awt.Color;
import com.diginet.digichat.awt.FontsChoice;
import com.diginet.digichat.awt.ColorChoice;
import java.awt.Checkbox;
import java.awt.event.ItemListener;
import java.awt.Choice;
import java.lang.reflect.Method;
import java.awt.Label;
import com.diginet.digichat.client.DigiChatAppletAbstract;
import java.awt.Component;

public class q
{
    public static Component a(final String s) {
        if (DigiChatAppletAbstract.langName == null || !DigiChatAppletAbstract.langName.equalsIgnoreCase("arabic.lang")) {
            return new Label(s);
        }
        final Component a = a();
        a(a, s);
        return a;
    }
    
    public static void a(final Component component, final boolean b) {
        final Method[] methods = component.getClass().getMethods();
        Method method = null;
        for (int i = 0; i < methods.length; ++i) {
            if ("setOpaque".equals(methods[i].getName())) {
                method = methods[i];
                break;
            }
        }
        try {
            method.invoke(component, new Boolean(b));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void b(final Component component, final boolean b) {
        final Method[] methods = component.getClass().getMethods();
        Method method = null;
        for (int i = 0; i < methods.length; ++i) {
            if ("setLightWeightPopupEnabled".equals(methods[i].getName())) {
                method = methods[i];
                break;
            }
        }
        try {
            method.invoke(component, new Boolean(b));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        for (int j = 0; j < methods.length; ++j) {
            if ("setDefaultLightWeightPopupEnabled()".equals(methods[j].getName())) {
                method = methods[j];
                break;
            }
        }
        try {
            method.invoke(component, new Boolean(b));
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
    
    public static void a(final Component component, final String s) {
        final Method[] methods = component.getClass().getMethods();
        Method method = null;
        for (int i = 0; i < methods.length; ++i) {
            if ("setText".equals(methods[i].getName())) {
                method = methods[i];
                break;
            }
        }
        try {
            method.invoke(component, s);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static Component a() {
        if (DigiChatAppletAbstract.langName == null || !DigiChatAppletAbstract.langName.equalsIgnoreCase("arabic.lang")) {
            return new Label();
        }
        Component component = null;
        try {
            component = (Component)Class.forName("javax.swing.JLabel").newInstance();
            a(component, false);
        }
        catch (ClassNotFoundException ex3) {
            try {
                component = (Component)Class.forName("java.awt.Label").newInstance();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        return component;
    }
    
    public static Component b() {
        if (DigiChatAppletAbstract.langName == null || !DigiChatAppletAbstract.langName.equalsIgnoreCase("arabic.lang")) {
            return new Choice();
        }
        Component component = null;
        try {
            component = (Component)Class.forName("javax.swing.JComboBox").newInstance();
            b(component, false);
            a(component, false);
        }
        catch (ClassNotFoundException ex3) {
            try {
                component = (Component)Class.forName("java.awt.Choice").newInstance();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        return component;
    }
    
    public static void a(final Component component, final Object o) {
        final Method[] methods = component.getClass().getMethods();
        Method method = null;
        for (int i = 0; i < methods.length; ++i) {
            if ("removeItemListener".equals(methods[i].getName())) {
                method = methods[i];
                break;
            }
        }
        try {
            method.invoke(component, o);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void a(final Component component, final ItemListener itemListener) {
        final Method[] methods = component.getClass().getMethods();
        Method method = null;
        if ("javax.swing.JComboBox".equals(component.getClass())) {
            for (int i = 0; i < methods.length; ++i) {
                if ("addItemListener".equals(methods[i].getName())) {
                    method = methods[i];
                    break;
                }
            }
        }
        else {
            for (int j = 0; j < methods.length; ++j) {
                if ("addItemListener".equals(methods[j].getName())) {
                    method = methods[j];
                    break;
                }
            }
        }
        try {
            method.invoke(component, itemListener);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void b(final Component component, final String s) {
        final Method[] methods = component.getClass().getMethods();
        Method method = null;
        for (int i = 0; i < methods.length; ++i) {
            if ("addItem".equals(methods[i].getName())) {
                method = methods[i];
                break;
            }
        }
        try {
            method.invoke(component, s);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void a(final Component component, final int n) {
        final Method[] methods = component.getClass().getMethods();
        Method method = null;
        for (int i = 0; i < methods.length; ++i) {
            if (("select".equals(methods[i].getName()) && "public synchronized void java.awt.Choice.select(int)".equals(methods[i].toString())) || "setSelectedIndex".equals(methods[i].getName())) {
                method = methods[i];
                break;
            }
        }
        try {
            method.invoke(component, new Integer(n));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void c(final Component component, final String s) {
        final Method[] methods = component.getClass().getMethods();
        Method method = null;
        for (int i = 0; i < methods.length; ++i) {
            if (("select".equals(methods[i].getName()) && "public synchronized void java.awt.Choice.select(java.lang.String)".equals(methods[i].toString())) || "setSelectedItem".equals(methods[i].getName())) {
                method = methods[i];
                break;
            }
        }
        try {
            method.invoke(component, s);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static String a(final Component p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //     4: invokevirtual   java/lang/Class.getMethods:()[Ljava/lang/reflect/Method;
        //     7: astore_1       
        //     8: aconst_null    
        //     9: astore_2       
        //    10: iconst_0       
        //    11: istore_3       
        //    12: goto            39
        //    15: ldc             "getSelectedItem"
        //    17: aload_1        
        //    18: iload_3        
        //    19: aaload         
        //    20: invokevirtual   java/lang/reflect/Method.getName:()Ljava/lang/String;
        //    23: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    26: ifeq            36
        //    29: aload_1        
        //    30: iload_3        
        //    31: aaload         
        //    32: astore_2       
        //    33: goto            45
        //    36: iinc            3, 1
        //    39: iload_3        
        //    40: aload_1        
        //    41: arraylength    
        //    42: if_icmplt       15
        //    45: new             Ljava/lang/String;
        //    48: dup            
        //    49: invokespecial   java/lang/String.<init>:()V
        //    52: astore_3       
        //    53: aconst_null    
        //    54: astore_3       
        //    55: aload_2        
        //    56: aload_0        
        //    57: iconst_0       
        //    58: anewarray       Ljava/lang/Object;
        //    61: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //    64: checkcast       Ljava/lang/String;
        //    67: astore_3       
        //    68: jsr             95
        //    71: goto            98
        //    74: astore          7
        //    76: aload           7
        //    78: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //    81: jsr             95
        //    84: goto            98
        //    87: astore          4
        //    89: jsr             95
        //    92: aload           4
        //    94: athrow         
        //    95: pop            
        //    96: aload_3        
        //    97: areturn        
        //    98: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  55     68     74     87     Ljava/lang/Exception;
        //  55     81     87     95     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2162)
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
    
    public static int b(final Component p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //     4: invokevirtual   java/lang/Class.getMethods:()[Ljava/lang/reflect/Method;
        //     7: astore_1       
        //     8: aconst_null    
        //     9: astore_2       
        //    10: iconst_0       
        //    11: istore_3       
        //    12: goto            39
        //    15: ldc             "getSelectedIndex"
        //    17: aload_1        
        //    18: iload_3        
        //    19: aaload         
        //    20: invokevirtual   java/lang/reflect/Method.getName:()Ljava/lang/String;
        //    23: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    26: ifeq            36
        //    29: aload_1        
        //    30: iload_3        
        //    31: aaload         
        //    32: astore_2       
        //    33: goto            45
        //    36: iinc            3, 1
        //    39: iload_3        
        //    40: aload_1        
        //    41: arraylength    
        //    42: if_icmplt       15
        //    45: iconst_m1      
        //    46: istore_3       
        //    47: aload_2        
        //    48: aload_0        
        //    49: iconst_0       
        //    50: anewarray       Ljava/lang/Object;
        //    53: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //    56: checkcast       Ljava/lang/Integer;
        //    59: astore          7
        //    61: aload           7
        //    63: invokevirtual   java/lang/Integer.intValue:()I
        //    66: istore_3       
        //    67: jsr             94
        //    70: goto            97
        //    73: astore          7
        //    75: aload           7
        //    77: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //    80: jsr             94
        //    83: goto            97
        //    86: astore          4
        //    88: jsr             94
        //    91: aload           4
        //    93: athrow         
        //    94: pop            
        //    95: iload_3        
        //    96: ireturn        
        //    97: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  47     67     73     86     Ljava/lang/Exception;
        //  47     80     86     94     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2162)
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
    
    public static Component a(final String s, final boolean b) {
        final Component c = c();
        c(c, b);
        d(c, s);
        return c;
    }
    
    public static Component b(final String s) {
        final Component c = c();
        d(c, s);
        return c;
    }
    
    public static Component c() {
        if (DigiChatAppletAbstract.langName == null || !DigiChatAppletAbstract.langName.equalsIgnoreCase("arabic.lang")) {
            return new Checkbox();
        }
        Component component = null;
        try {
            component = (Component)Class.forName("javax.swing.JCheckBox").newInstance();
            a(component, false);
        }
        catch (ClassNotFoundException ex3) {
            try {
                component = (Component)Class.forName("java.awt.Checkbox").newInstance();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        return component;
    }
    
    public static void d(final Component component, final String s) {
        final Method[] methods = component.getClass().getMethods();
        Method method = null;
        for (int i = 0; i < methods.length; ++i) {
            if ("setLabel".equals(methods[i].getName())) {
                method = methods[i];
                break;
            }
        }
        try {
            method.invoke(component, s);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static boolean c(final Component p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //     4: invokevirtual   java/lang/Class.getMethods:()[Ljava/lang/reflect/Method;
        //     7: astore_1       
        //     8: aconst_null    
        //     9: astore_2       
        //    10: iconst_0       
        //    11: istore          4
        //    13: iconst_0       
        //    14: istore          5
        //    16: goto            60
        //    19: ldc             "getState"
        //    21: aload_1        
        //    22: iload           5
        //    24: aaload         
        //    25: invokevirtual   java/lang/reflect/Method.getName:()Ljava/lang/String;
        //    28: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    31: ifne            49
        //    34: ldc             "isSelected"
        //    36: aload_1        
        //    37: iload           5
        //    39: aaload         
        //    40: invokevirtual   java/lang/reflect/Method.getName:()Ljava/lang/String;
        //    43: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    46: ifeq            57
        //    49: aload_1        
        //    50: iload           5
        //    52: aaload         
        //    53: astore_2       
        //    54: goto            67
        //    57: iinc            5, 1
        //    60: iload           5
        //    62: aload_1        
        //    63: arraylength    
        //    64: if_icmplt       19
        //    67: aload_2        
        //    68: aload_0        
        //    69: iconst_0       
        //    70: anewarray       Ljava/lang/Object;
        //    73: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //    76: checkcast       Ljava/lang/Boolean;
        //    79: astore_3       
        //    80: aload_3        
        //    81: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    84: istore          4
        //    86: jsr             113
        //    89: goto            117
        //    92: astore          8
        //    94: aload           8
        //    96: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //    99: jsr             113
        //   102: goto            117
        //   105: astore          5
        //   107: jsr             113
        //   110: aload           5
        //   112: athrow         
        //   113: pop            
        //   114: iload           4
        //   116: ireturn        
        //   117: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  67     86     92     105    Ljava/lang/Exception;
        //  67     99     105    113    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2162)
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
    
    public static void c(final Component component, final boolean b) {
        final Method[] methods = component.getClass().getMethods();
        Method method = null;
        for (int i = 0; i < methods.length; ++i) {
            if ("setState".equals(methods[i].getName()) || "setSelected".equals(methods[i].getName())) {
                method = methods[i];
                break;
            }
        }
        try {
            method.invoke(component, new Boolean(b));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static Component d() {
        if (DigiChatAppletAbstract.langName == null || !DigiChatAppletAbstract.langName.equalsIgnoreCase("arabic.lang")) {
            return new ColorChoice();
        }
        Component component = null;
        try {
            Class.forName("javax.swing.JComboBox");
            component = (Component)Class.forName("com.diginet.digichat.awt.JColorChoice").newInstance();
            b(component, false);
            a(component, false);
        }
        catch (ClassNotFoundException ex4) {
            try {
                component = (Component)Class.forName("com.diginet.digichat.awt.ColorChoice").newInstance();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        catch (NoClassDefFoundError noClassDefFoundError) {
            try {
                noClassDefFoundError.printStackTrace();
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
        return component;
    }
    
    public static Component e() {
        if (DigiChatAppletAbstract.langName == null || !DigiChatAppletAbstract.langName.equalsIgnoreCase("arabic.lang")) {
            return new FontsChoice();
        }
        Component component = null;
        try {
            Class.forName("javax.swing.JComboBox");
            component = (Component)Class.forName("com.diginet.digichat.awt.JFontsChoice").newInstance();
            b(component, false);
            a(component, false);
        }
        catch (ClassNotFoundException ex3) {
            try {
                component = (Component)Class.forName("com.diginet.digichat.awt.FontsChoice").newInstance();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        return component;
    }
    
    public static Color d(final Component component) {
        final Method[] methods = component.getClass().getMethods();
        Method method = null;
        Color color = null;
        for (int i = 0; i < methods.length; ++i) {
            if ("getSelectedColor".equals(methods[i].getName())) {
                method = methods[i];
                break;
            }
        }
        try {
            color = (Color)method.invoke(component, new Object[0]);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return color;
    }
    
    public static void a(final Component component, final Color color) {
        final Method[] methods = component.getClass().getMethods();
        Method method = null;
        for (int i = 0; i < methods.length; ++i) {
            if (("select".equals(methods[i].getName()) && methods[i].toString().endsWith("select(java.awt.Color)")) || ("setSelectedIndex".equals(methods[i].getName()) && methods[i].toString().endsWith("setSelectedIndex(java.awt.Color)"))) {
                method = methods[i];
                break;
            }
        }
        try {
            method.invoke(component, color);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
