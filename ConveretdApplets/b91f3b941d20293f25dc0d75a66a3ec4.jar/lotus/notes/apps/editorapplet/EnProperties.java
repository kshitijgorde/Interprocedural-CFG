// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editorapplet;

import java.util.Hashtable;
import java.util.Properties;

public class EnProperties extends Properties
{
    public EnProperties() {
        ((Hashtable<String, String>)this).put("BOLD", "bold");
        ((Hashtable<String, String>)this).put("ITALIC", "italic");
        ((Hashtable<String, String>)this).put("UNDERLINE", "underline");
        ((Hashtable<String, String>)this).put("COLORSELECT", "text color");
        ((Hashtable<String, String>)this).put("ALIGN_LEFT", "left align");
        ((Hashtable<String, String>)this).put("ALIGN_CENTER", "center align");
        ((Hashtable<String, String>)this).put("ALIGN_RIGHT", "right align");
        ((Hashtable<String, String>)this).put("BULLET", "plain paragraph");
        ((Hashtable<String, String>)this).put("UNORDERED", "bulleted list");
        ((Hashtable<String, String>)this).put("ORDERED", "numbered list");
        ((Hashtable<String, String>)this).put("INDENT", "indent paragraph");
        ((Hashtable<String, String>)this).put("OUTDENT", "outdent paragraph");
        ((Hashtable<String, String>)this).put("H1", "headline");
        ((Hashtable<String, String>)this).put("HEADLINE", "insert graphic text");
        ((Hashtable<String, String>)this).put("IMAGE", "insert image");
        ((Hashtable<String, String>)this).put("SPELL", "spell check");
        ((Hashtable<String, String>)this).put("LINK", "create link");
        ((Hashtable<String, String>)this).put("ACCEPT", "accept");
        ((Hashtable<String, String>)this).put("CANCEL", "cancel");
        ((Hashtable<String, String>)this).put("INTL", "enter internationl characters");
        ((Hashtable<String, String>)this).put("LINK_PROMPT", "Enter Link Address: ");
        ((Hashtable<String, String>)this).put("UNICODE_PROMPT", "Enter Unicode value (decimal or 0xhex): ");
    }
}
