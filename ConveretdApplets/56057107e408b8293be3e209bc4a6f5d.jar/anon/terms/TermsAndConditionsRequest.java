// 
// Decompiled by Procyon v0.5.30
// 

package anon.terms;

import anon.terms.template.TermsAndConditionsTemplate;
import anon.client.IllegalTCRequestPostConditionException;
import java.util.Enumeration;
import anon.util.XMLUtil;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import anon.infoservice.ServiceOperator;
import java.util.Hashtable;
import java.util.Vector;
import anon.util.IXMLEncodable;

public class TermsAndConditionsRequest implements IXMLEncodable
{
    public static final String XML_ELEMENT_NAME = "Resources";
    public static final String XML_ELEMENT_CONTAINER_NAME = "TermsAndConditionsRequest";
    public static final String XML_ATTR_LOCALE = "locale";
    public static final String XML_ELEMENT_REQ_TRANSLATION = "Translation";
    public static final String XML_ELEMENT_RESOURCE_TEMPLATE = "Template";
    public static final String XML_ELEMENT_RESOURCE_CUSTOMIZED_SECT = "CustomizedSections";
    public static final String XML_MSG_TC_INTERRUPT = "TermsAndConditionsInterrupt";
    public static final String XML_MSG_TC_CONFIRM = "TermsAndConditionsConfirm";
    private Vector requestedTemplates;
    private Hashtable requestedItems;
    private Hashtable resourceRootElements;
    
    public TermsAndConditionsRequest() {
        this.requestedTemplates = null;
        this.requestedItems = null;
        this.resourceRootElements = null;
        this.requestedTemplates = new Vector();
        this.requestedItems = new Hashtable();
        this.resourceRootElements = new Hashtable();
    }
    
    public void addTemplateRequest(final ServiceOperator serviceOperator, final String s, final String s2) {
        if (!this.requestedTemplates.contains(s2)) {
            this.requestedTemplates.addElement(s2);
            this.addResourceRequest("Template", serviceOperator, s);
        }
    }
    
    public void addCustomizedSectionsRequest(final ServiceOperator serviceOperator, final String s) {
        this.addResourceRequest("CustomizedSections", serviceOperator, s);
    }
    
    private void addResourceRequest(final String s, final ServiceOperator serviceOperator, final String s2) {
        final TCRequestKey tcRequestKey = new TCRequestKey(serviceOperator, s2);
        TCRequestValue tcRequestValue = this.requestedItems.get(tcRequestKey);
        if (tcRequestValue == null) {
            tcRequestValue = new TCRequestValue();
            this.requestedItems.put(tcRequestKey, tcRequestValue);
        }
        tcRequestValue.addResourceRequest(s);
    }
    
    public boolean hasResourceRequests() {
        return !this.requestedItems.isEmpty();
    }
    
    public Element toXmlElement(final Document document) {
        final Enumeration keys = this.requestedItems.keys();
        if (!keys.hasMoreElements()) {
            return null;
        }
        final Element element = document.createElement("TermsAndConditionsRequest");
        document.appendChild(element);
        Element element2 = null;
        while (keys.hasMoreElements()) {
            final TCRequestKey tcRequestKey = keys.nextElement();
            Element element3 = this.resourceRootElements.get(tcRequestKey.getOperator());
            if (element3 == null) {
                element3 = document.createElement("Resources");
                XMLUtil.setAttribute(element3, "id", tcRequestKey.getOperator().getId());
                this.resourceRootElements.put(tcRequestKey.getOperator(), element3);
            }
            final Enumeration access$300 = this.requestedItems.get(tcRequestKey).getAllResourceRequests();
            if (access$300.hasMoreElements()) {
                element2 = document.createElement("Translation");
                XMLUtil.setAttribute(element2, "locale", tcRequestKey.getLangCode());
                element3.appendChild(element2);
            }
            while (access$300.hasMoreElements()) {
                element2.appendChild(document.createElement(access$300.nextElement()));
            }
            element.appendChild(element3);
        }
        return element;
    }
    
    public void checkRequestPostCondition() throws IllegalTCRequestPostConditionException {
        final IllegalTCRequestPostConditionException ex = new IllegalTCRequestPostConditionException();
        final Enumeration<TCRequestKey> keys = this.requestedItems.keys();
        while (keys.hasMoreElements()) {
            final TCRequestKey tcRequestKey = keys.nextElement();
            final TermsAndConditions termsAndConditions = TermsAndConditions.getTermsAndConditions(tcRequestKey.getOperator());
            if (termsAndConditions != null) {
                if (!termsAndConditions.hasTranslation(tcRequestKey.getLangCode())) {
                    ex.addErrorMessage("Requested Translation [" + tcRequestKey.getLangCode() + "] was not loaded for terms and conditions of operator " + tcRequestKey.getOperator().getOrganization());
                }
                else {
                    final String templateReferenceId = termsAndConditions.getTemplateReferenceId(tcRequestKey.getLangCode());
                    if (templateReferenceId == null || TermsAndConditionsTemplate.getById(templateReferenceId, false) == null) {
                        ex.addErrorMessage("Template '" + templateReferenceId + "' for translation [" + tcRequestKey.getLangCode() + "] of terms and conditions of operator " + tcRequestKey.getOperator().getOrganization() + " was not loaded.");
                    }
                }
                if (termsAndConditions.hasDefaultTranslation()) {
                    continue;
                }
                ex.addErrorMessage("No default translation for terms and conditions of operator " + tcRequestKey.getOperator().getOrganization() + " were loaded.");
                TermsAndConditions.removeTermsAndConditions(tcRequestKey.getOperator());
            }
            else {
                ex.addErrorMessage("Translation for " + tcRequestKey + " not loaded.");
            }
        }
        if (ex.hasErrorMessages()) {
            throw ex;
        }
    }
    
    private static class TCRequestKey
    {
        ServiceOperator operator;
        String langCode;
        
        private TCRequestKey(final ServiceOperator operator, final String langCode) {
            this.operator = null;
            this.langCode = null;
            this.operator = operator;
            this.langCode = langCode;
        }
        
        public String toString() {
            return this.operator.getId() + this.langCode;
        }
        
        public int hashCode() {
            return this.toString().hashCode();
        }
        
        public boolean equals(final Object o) {
            return ((TCRequestKey)o).toString().equals(this.toString());
        }
        
        public String getLangCode() {
            return this.langCode;
        }
        
        public ServiceOperator getOperator() {
            return this.operator;
        }
    }
    
    private static class TCRequestValue
    {
        Vector requestEntries;
        
        private TCRequestValue() {
            this.requestEntries = new Vector();
        }
        
        private void addResourceRequest(final String s) {
            if (!this.requestEntries.contains(s)) {
                this.requestEntries.addElement(s);
            }
        }
        
        private Enumeration getAllResourceRequests() {
            return this.requestEntries.elements();
        }
    }
}
