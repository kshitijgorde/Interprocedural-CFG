// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.model;

import java.util.Hashtable;
import com.objectbox.runner.util.JBLogger;
import com.objectbox.loader.JBURLClassloader;
import com.objectbox.runner.gui.JBee;
import com.objectbox.runner.gui.BeanRunner;

public class JBAppletHolder extends JBHolder
{
    static final long serialVersionUID = -123456789L;
    
    public JBAppletHolder() {
        super.props = new JBAppletProperties();
    }
    
    public BeanRunner run(final ThreadGroup threadGroup) {
        BeanRunner beanRunner = null;
        try {
            final JBProperties properties = this.getProperties();
            final String s = ((Hashtable<K, String>)properties.getProps()).get("code");
            beanRunner = new BeanRunner(new ThreadGroup(threadGroup, s), s);
            String s2 = JBee.getPreference("javabee_home");
            if (s2 == null) {
                s2 = "." + System.getProperty("file.separator");
            }
            else if (!s2.endsWith(System.getProperty("file.separator"))) {
                s2 = String.valueOf(s2) + System.getProperty("file.separator");
            }
            final JBURLClassloader jburlClassloader = new JBURLClassloader(String.valueOf(s2) + "cache");
            final String preference = JBee.getPreference("usebytecodecache");
            if (preference == null || preference.compareTo("true") == 0) {
                jburlClassloader.setCacheOn(true);
            }
            else {
                jburlClassloader.setCacheOn(false);
            }
            final String preference2 = JBee.getPreference("checkversion");
            if (preference2 == null || preference2.compareTo("true") == 0) {
                jburlClassloader.setChecklastmodified(true);
            }
            else {
                jburlClassloader.setChecklastmodified(false);
            }
            beanRunner.set(jburlClassloader, properties);
            beanRunner.setDelay(3000L);
            beanRunner.start();
        }
        catch (Throwable t) {
            JBLogger.log(t.getMessage());
        }
        return beanRunner;
    }
}
