// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.run;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;
import java.awt.Component;
import com.bullionvault.chart.resources.Resources;
import javax.swing.JFrame;

public class ChartMainLauncher
{
    public static void main(final String[] array) {
        final ChartApp chartApp;
        (chartApp = new ChartApp(array)).init();
        final JFrame frame;
        (frame = new JFrame("ChartApp")).setSize(600, 400);
        frame.setIconImage(Resources.d().getImage());
        frame.setTitle(Resources.b("desktop.title"));
        frame.add("Center", chartApp);
        chartApp.start();
        try {
            SwingUtilities.invokeAndWait(new a(frame));
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        catch (InvocationTargetException ex2) {
            ex2.printStackTrace();
        }
    }
}
