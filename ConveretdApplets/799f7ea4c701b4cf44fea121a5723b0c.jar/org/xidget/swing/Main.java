// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing;

import org.xmodel.external.ConfiguredCachingPolicy;
import java.util.Collections;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.Context;
import org.xmodel.xpath.XPath;
import org.xmodel.external.ICachingPolicy;
import org.xmodel.external.ExternalReference;
import org.xidget.caching.FileSystemCachingPolicy;
import org.xidget.caching.ZipCachingPolicy;
import java.io.File;
import org.xidget.IToolkit;
import org.xidget.Creator;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import java.awt.Component;
import javax.swing.JOptionPane;
import org.xmodel.log.Log;

public class Main
{
    private static Log log;
    
    static {
        Main.log = Log.getLog("org.xidget.swing");
    }
    
    public static void main(final String[] array) {
        Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(final Thread thread, final Throwable t) {
                t.printStackTrace(System.err);
                JOptionPane.showMessageDialog(null, String.format("Thread: %s\n%s: %s", thread.getName(), t.getClass().getName(), t.getMessage()));
            }
        });
        try {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Main.start(array);
                }
            });
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void start(final String[] array) {
        final String s = (array.length > 0) ? array[0] : "xapp/main.xml";
        try {
            UIManager.LookAndFeelInfo[] installedLookAndFeels;
            for (int length = (installedLookAndFeels = UIManager.getInstalledLookAndFeels()).length, i = 0; i < length; ++i) {
                final UIManager.LookAndFeelInfo lookAndFeelInfo = installedLookAndFeels[i];
                Main.log.info(lookAndFeelInfo.getName());
                if ("Nimbus".equals(lookAndFeelInfo.getName())) {
                    UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
                    break;
                }
            }
        }
        catch (Exception ex2) {}
        Creator.setToolkitClass(Toolkit.class);
        final String property = System.getProperty("java.class.path");
        final boolean b = property.indexOf(File.pathSeparator) < 0 && property.endsWith(".jar");
        final ConfiguredCachingPolicy cachingPolicy = b ? new ZipCachingPolicy() : new FileSystemCachingPolicy();
        try {
            final String s2 = b ? System.getProperty("java.class.path") : ".";
            final ExternalReference externalReference = new ExternalReference("resources");
            externalReference.setCachingPolicy(cachingPolicy);
            externalReference.setAttribute("path", s2);
            externalReference.setDirty(true);
            final IModelObject queryFirst = XPath.createExpression(s).queryFirst(new Context(externalReference));
            if (queryFirst == null) {
                throw new IllegalArgumentException("Unable to locate startup script: " + s);
            }
            final XActionDocument xActionDocument = new XActionDocument(Main.class.getClassLoader());
            xActionDocument.setRoot(queryFirst);
            xActionDocument.addPackage("org.xidget.xaction");
            final StatefulContext statefulContext = new StatefulContext(externalReference);
            statefulContext.set("applet", Collections.emptyList());
            xActionDocument.createScript(new String[0]).run(statefulContext);
        }
        catch (Exception ex) {
            Main.log.exception(ex);
        }
    }
}
