// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.viewapplet;

import java.util.Hashtable;
import java.util.Properties;

public class DefaultProperties extends Properties
{
    public DefaultProperties() {
        ((Hashtable<String, String>)this).put("reading.entries", "Reading View Data...");
        ((Hashtable<String, String>)this).put("reading.design", "Reading View Design Data...");
        ((Hashtable<String, String>)this).put("error.action", "The action was not completed for one or more documents");
        ((Hashtable<String, String>)this).put("folderdialog.move", "Move");
        ((Hashtable<String, String>)this).put("folderdialog.add", "Add");
        ((Hashtable<String, String>)this).put("folderdialog.cancel", "Cancel");
        ((Hashtable<String, String>)this).put("folderdialog.select", "Select a Folder:");
        ((Hashtable<String, String>)this).put("folderdialog.titlemove", "Move To Folder");
        ((Hashtable<String, String>)this).put("folderdialog.titlecopy", "Copy To Folder");
        ((Hashtable<String, String>)this).put("not.categorized", "(Not Categorized)");
    }
}
