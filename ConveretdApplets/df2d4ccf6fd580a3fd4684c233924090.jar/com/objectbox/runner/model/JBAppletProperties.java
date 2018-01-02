// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.model;

import java.util.Hashtable;
import com.objectbox.runner.gui.AppletPropertiesEditorFrame;

public class JBAppletProperties extends JBProperties
{
    static final long serialVersionUID = -123456789L;
    public static transient AppletPropertiesEditorFrame editorFrame;
    
    static {
        JBAppletProperties.editorFrame = new AppletPropertiesEditorFrame();
    }
    
    public JBAppletProperties() {
        ((Hashtable<String, String>)super.properties).put("width", "");
        ((Hashtable<String, String>)super.properties).put("height", "");
        ((Hashtable<String, String>)super.properties).put("code", "");
        ((Hashtable<String, String>)super.properties).put("codebase", "");
        ((Hashtable<String, String>)super.properties).put("documentbase", "");
        ((Hashtable<String, String>)super.properties).put("archive", "");
    }
    
    public JBAppletProperties(final JBProperties jbProperties) {
        super(jbProperties);
    }
    
    public JBPropertyEditor getPropertyEditor() {
        return JBAppletProperties.editorFrame;
    }
}
