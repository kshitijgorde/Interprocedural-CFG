// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing;

import java.net.URL;
import java.awt.Component;
import javax.swing.JTextArea;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.external.ICachingPolicy;
import org.xmodel.external.ExternalReference;
import org.xmodel.caching.IFileAssociation;
import org.xmodel.external.ICache;
import org.xmodel.caching.URLCachingPolicy;
import org.xmodel.external.UnboundedCache;
import org.xidget.swing.applet.JAppletXidget;
import org.xidget.IToolkit;
import javax.swing.UIManager;
import java.awt.Color;
import org.xidget.Creator;
import javax.swing.SwingUtilities;
import org.xmodel.log.Log;
import org.xidget.swing.feature.SwingWidgetFeature;
import org.xidget.IXidget;
import javax.swing.JApplet;

public class Applet extends JApplet
{
    private IXidget xidget;
    
    public Applet() {
        Log.getLog(SwingWidgetFeature.class.getCanonicalName()).setLevel(255);
    }
    
    @Override
    public void init() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    Applet.this.createContent();
                }
            });
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public void start() {
    }
    
    @Override
    public void stop() {
    }
    
    @Override
    public void destroy() {
        if (this.xidget != null) {
            Creator.getInstance().destroy(this.xidget);
            this.xidget = null;
        }
    }
    
    private void createContent() {
        this.setBackground(Color.white);
        this.getContentPane().setBackground(Color.white);
        try {
            UIManager.LookAndFeelInfo[] installedLookAndFeels;
            for (int length = (installedLookAndFeels = UIManager.getInstalledLookAndFeels()).length, i = 0; i < length; ++i) {
                final UIManager.LookAndFeelInfo lookAndFeelInfo = installedLookAndFeels[i];
                if ("Nimbus".equals(lookAndFeelInfo.getName())) {
                    UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
                    break;
                }
            }
        }
        catch (Exception ex) {}
        Creator.setToolkitClass(Toolkit.class);
        final JAppletXidget appletXidget = new JAppletXidget(this);
        Creator.getInstance().register(this, appletXidget);
        try {
            final URL resource = this.getClass().getResource("/xapp.xip");
            final URLCachingPolicy cachingPolicy = new URLCachingPolicy(new UnboundedCache());
            cachingPolicy.addAssociation(new SwingXipAssociation());
            final ExternalReference externalReference = new ExternalReference("root");
            externalReference.setAttribute("url", resource);
            externalReference.setCachingPolicy(cachingPolicy);
            externalReference.setDirty(true);
            final IModelObject firstChild = externalReference.getFirstChild("xapp").getFirstChild("main.xml");
            if (firstChild == null) {
                throw new RuntimeException("Unable to locate startup script: main.xml.");
            }
            final StatefulContext statefulContext = new StatefulContext(externalReference);
            statefulContext.set("applet", appletXidget.getConfig());
            final XActionDocument xActionDocument = new XActionDocument(Applet.class.getClassLoader());
            xActionDocument.setRoot(firstChild);
            xActionDocument.addPackage("org.xidget.xaction");
            xActionDocument.createScript(new String[0]).run(statefulContext);
        }
        catch (Throwable t) {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            t.printStackTrace(new PrintStream(byteArrayOutputStream));
            final JTextArea textArea = new JTextArea();
            textArea.setText(byteArrayOutputStream.toString());
            this.getContentPane().add(textArea);
        }
    }
}
