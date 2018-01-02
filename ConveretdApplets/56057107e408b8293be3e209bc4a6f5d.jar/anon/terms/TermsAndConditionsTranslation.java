// 
// Decompiled by Procyon v0.5.30
// 

package anon.terms;

import anon.infoservice.OperatorAddress;
import java.util.Date;
import anon.infoservice.ServiceOperator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface TermsAndConditionsTranslation
{
    public static final String XML_ELEMENT_NAME = "TCTranslation";
    public static final String XML_ELEMENT_CONTAINER_NAME = "TermsAndConditions";
    public static final String XML_ELEMENT_PRIVACY_POLICY = "PrivacyPolicyUrl";
    public static final String XML_ELEMENT_LEGAL_OPINIONS = "LegalOpinionsUrl";
    public static final String XML_ELEMENT_OPERATIONAL_AGREEMENT = "OperationalAgreementUrl";
    public static final String XML_ATTR_LOCALE = "locale";
    public static final String XML_ATTR_DEFAULT_LOCALE = "default";
    public static final String XML_ATTR_REFERENCE_ID = "referenceId";
    public static final String PROPERTY_NAME_PRIVACY_POLICY = "privacyPolicyUrl";
    public static final String PROPERTY_NAME_LEGAL_OPINIONS = "legalOpinionsUrl";
    public static final String PROPERTY_NAME_OPERATIONAL_AGREEMENT = "operationalAgreementUrl";
    public static final String PROPERTY_NAME_TEMPLATE_REFERENCE_ID = "templateReferenceId";
    
    void setTemplateReferenceId(final String p0);
    
    String getTemplateReferenceId();
    
    String getLocale();
    
    void setDefaultTranslation(final boolean p0);
    
    boolean isDefaultTranslation();
    
    Element getTranslationElement();
    
    Element createXMLOutput(final Document p0);
    
    ServiceOperator getOperator();
    
    Date getDate();
    
    void setOperatorAddress(final OperatorAddress p0);
    
    OperatorAddress getOperatorAddress();
    
    String getPrivacyPolicyUrl();
    
    void setPrivacyPolicyUrl(final String p0);
    
    String getLegalOpinionsUrl();
    
    void setLegalOpinionsUrl(final String p0);
    
    String getOperationalAgreementUrl();
    
    void setOperationalAgreementUrl(final String p0);
    
    void setSections(final TCComposite p0);
    
    TCComposite getSections();
    
    boolean hasContent();
    
    TermsAndConditionsTranslation duplicateWithImports(final Element p0);
}
