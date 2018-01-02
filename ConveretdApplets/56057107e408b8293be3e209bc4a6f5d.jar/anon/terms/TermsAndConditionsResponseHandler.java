// 
// Decompiled by Procyon v0.5.30
// 

package anon.terms;

import anon.client.IllegalTCRequestPostConditionException;
import logging.LogHolder;
import logging.LogType;
import anon.infoservice.ServiceOperator;
import anon.infoservice.AbstractDatabaseEntry;
import anon.infoservice.Database;
import java.security.SignatureException;
import anon.terms.template.TermsAndConditionsTemplate;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import anon.util.XMLUtil;
import anon.util.XMLParseException;
import java.io.IOException;
import org.w3c.dom.Document;
import java.util.Observable;

public class TermsAndConditionsResponseHandler extends Observable
{
    public static final String XML_ELEMENT_INVALID_REQUEST_NAME = "InvalidTermsAndConditionsRequest";
    public static final String XML_ELEMENT_RESPONSE_NAME = "TermsAndConditionsResponse";
    private static final TermsAndConditionsResponseHandler SINGLETON;
    static /* synthetic */ Class class$anon$terms$template$TermsAndConditionsTemplate;
    static /* synthetic */ Class class$anon$infoservice$ServiceOperator;
    
    public void handleXMLResourceResponse(final Document document, final TermsAndConditionsRequest termsAndConditionsRequest) throws XMLParseException, IOException, IllegalTCRequestPostConditionException, SignatureException {
        if (document.getDocumentElement().getTagName().equals("InvalidTermsAndConditionsRequest")) {
            throw new IOException("Error: Mix reported invalid TC request");
        }
        if (!document.getDocumentElement().getTagName().equals("TermsAndConditionsResponse")) {
            throw new XMLParseException("No TC response.");
        }
        for (Node node = XMLUtil.getFirstChildByName(document.getDocumentElement(), "Resources"); node != null; node = XMLUtil.getNextSiblingByName(node, "Resources")) {
            final String attribute = XMLUtil.parseAttribute(node, "id", "");
            if (attribute.equals("")) {
                throw new XMLParseException("invalid attributes: id not set");
            }
            for (Node firstChildByName = XMLUtil.getFirstChildByName(node, "Template"); firstChildByName != null; firstChildByName = XMLUtil.getNextSiblingByName(firstChildByName, "Template")) {
                final TermsAndConditionsTemplate termsAndConditionsTemplate = new TermsAndConditionsTemplate(firstChildByName.getFirstChild());
                if (!termsAndConditionsTemplate.isVerified()) {
                    throw new SignatureException("TermsAndConditionsTemplate cannot be verified!");
                }
                Database.getInstance((TermsAndConditionsResponseHandler.class$anon$terms$template$TermsAndConditionsTemplate == null) ? (TermsAndConditionsResponseHandler.class$anon$terms$template$TermsAndConditionsTemplate = class$("anon.terms.template.TermsAndConditionsTemplate")) : TermsAndConditionsResponseHandler.class$anon$terms$template$TermsAndConditionsTemplate).update(termsAndConditionsTemplate);
            }
            for (Node firstChildByName2 = XMLUtil.getFirstChildByName(node, "CustomizedSections"); firstChildByName2 != null; firstChildByName2 = XMLUtil.getNextSiblingByName(firstChildByName2, "CustomizedSections")) {
                final ServiceOperator serviceOperator = (ServiceOperator)Database.getInstance((TermsAndConditionsResponseHandler.class$anon$infoservice$ServiceOperator == null) ? (TermsAndConditionsResponseHandler.class$anon$infoservice$ServiceOperator = class$("anon.infoservice.ServiceOperator")) : TermsAndConditionsResponseHandler.class$anon$infoservice$ServiceOperator).getEntryById(attribute.toUpperCase());
                if (serviceOperator == null) {
                    throw new XMLParseException("invalid id " + attribute + ": no operator found with this subject key identifier");
                }
                final TermsAndConditions termsAndConditions = TermsAndConditions.getTermsAndConditions(serviceOperator);
                if (termsAndConditions == null) {
                    throw new IllegalStateException("a tc container for operator " + serviceOperator.getOrganization() + " must exist but does not!");
                }
                try {
                    termsAndConditions.addTranslation((Element)XMLUtil.getFirstChildByName(firstChildByName2, "TCTranslation"));
                }
                catch (SignatureException ex) {
                    LogHolder.log(3, LogType.MISC, "Signature validition error while receiving mix tc answer: ", ex);
                }
            }
        }
        termsAndConditionsRequest.checkRequestPostCondition();
    }
    
    public void notifyAboutChanges() {
        this.setChanged();
        this.notifyObservers();
    }
    
    public static TermsAndConditionsResponseHandler get() {
        return TermsAndConditionsResponseHandler.SINGLETON;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        SINGLETON = new TermsAndConditionsResponseHandler();
    }
}
