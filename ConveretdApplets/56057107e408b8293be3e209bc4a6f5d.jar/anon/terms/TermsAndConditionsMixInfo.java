// 
// Decompiled by Procyon v0.5.30
// 

package anon.terms;

import java.util.Enumeration;
import java.util.Locale;
import anon.util.XMLUtil;
import anon.util.XMLParseException;
import org.w3c.dom.Node;
import java.util.Hashtable;

public class TermsAndConditionsMixInfo
{
    public static final String TNC_MIX_INFO_ROOT = "TermsAndConditionsInfos";
    public static final String TNC_MIX_INFO = "TermsAndConditionsInfo";
    public static final String TNC_MIX_INFO_ID = "id";
    public static final String TNC_MIX_INFO_DATE = "date";
    public static final String TNC_MIX_INFO_LOCALE = "locale";
    public static final String TNC_MIX_INFO_DEFAULT_LANG = "defaultLang";
    public static final String TNC_MIX_INFO_TEMPLATE_REFID = "referenceId";
    private String id;
    private String date;
    private String defaultLang;
    private Hashtable templates;
    
    public TermsAndConditionsMixInfo(final Node node) throws XMLParseException {
        this.id = "";
        this.date = "";
        this.defaultLang = "";
        this.templates = new Hashtable();
        if (node == null) {
            throw new XMLParseException("T&C Info Node is null");
        }
        this.id = XMLUtil.parseAttribute(node, "id", "");
        if (this.id.equals("")) {
            throw new XMLParseException("T&C Info Node does not contain an ID");
        }
        this.date = XMLUtil.parseAttribute(node, "date", "");
        if (this.date.equals("")) {
            throw new XMLParseException("T&C Info Node " + this.id + " does not contain a valid date");
        }
        this.defaultLang = XMLUtil.parseAttribute(node, "defaultLang", "").trim().toLowerCase();
        if (this.defaultLang.equals("")) {
            throw new XMLParseException("T&C Info Node " + this.id + " does not define a default language");
        }
        for (Node node2 = XMLUtil.getFirstChildByName(node, "TermsAndConditionsInfo"); node2 != null; node2 = XMLUtil.getNextSiblingByName(node2, "TermsAndConditionsInfo")) {
            final String attribute = XMLUtil.parseAttribute(node2, "locale", "");
            final String attribute2 = XMLUtil.parseAttribute(node2, "referenceId", "");
            if (!attribute.equals("") && !attribute2.equals("")) {
                this.templates.put(attribute.trim().toLowerCase(), attribute2);
            }
        }
        this.getLanguages();
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getDate() {
        return this.date;
    }
    
    public String getTemplateRefId(final Locale locale) {
        return this.getTemplateRefId(locale.getLanguage());
    }
    
    public String getTemplateRefId(final String s) {
        return this.templates.get(s.trim().toLowerCase());
    }
    
    public String getDefaultTemplateRefId() {
        return this.templates.get(this.getDefaultLanguage());
    }
    
    public boolean hasTranslation(final String s) {
        return this.templates.get(s.trim().toLowerCase()) != null;
    }
    
    public boolean hasTranslation(final Locale locale) {
        return this.hasTranslation(locale.getLanguage());
    }
    
    public String getDefaultLanguage() {
        return this.defaultLang;
    }
    
    public Enumeration getLanguages() {
        return this.templates.keys();
    }
}
