// 
// Decompiled by Procyon v0.5.30
// 

package dk.scanmaps.ditkort;

import java.util.MissingResourceException;
import java.util.Locale;
import java.util.ResourceBundle;

public class CResourceManager
{
    String locale;
    final String bundle = "ditkort";
    int count;
    private static CResourceManager instance;
    ResourceBundle myResources;
    
    private CResourceManager() {
        this.locale = "dk";
        this.count = 0;
        this.myResources = null;
        this.myResources = ResourceBundle.getBundle("ditkort", new Locale(this.locale));
    }
    
    public static CResourceManager instance() {
        if (CResourceManager.instance == null) {
            return CResourceManager.instance = new CResourceManager();
        }
        return CResourceManager.instance;
    }
    
    public void setLocale(final String locale) {
        this.locale = locale;
        this.myResources = ResourceBundle.getBundle("ditkort", new Locale(locale));
    }
    
    public void setBundle(final String bundle) {
        this.myResources = ResourceBundle.getBundle(bundle, new Locale(this.locale));
    }
    
    public String getResource(final String alias) throws MissingResourceException {
        try {
            return this.myResources.getString(alias);
        }
        catch (MissingResourceException e) {
            System.out.println("The resource " + alias + " couldn't be found!");
            return null;
        }
    }
    
    public String getLocale() {
        return this.locale;
    }
}
