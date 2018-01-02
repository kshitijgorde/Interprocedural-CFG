// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.util;

import org.w3c.dom.Text;
import java.net.URL;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.List;
import java.io.IOException;
import java.util.Properties;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import com.itt.J2KViewer.controller.ViewCentral;

public class LicenseUtil
{
    private Log log;
    private String vendorName;
    private String vendorLicense;
    static /* synthetic */ Class class$com$itt$J2KViewer$util$LicenseUtil;
    
    public LicenseUtil(final ViewCentral viewCentral) {
        this.log = new Log((LicenseUtil.class$com$itt$J2KViewer$util$LicenseUtil == null) ? (LicenseUtil.class$com$itt$J2KViewer$util$LicenseUtil = class$("com.itt.J2KViewer.util.LicenseUtil")) : LicenseUtil.class$com$itt$J2KViewer$util$LicenseUtil);
        this.vendorName = null;
        this.vendorLicense = null;
        final List xmlBoxes = viewCentral.getXmlPropertiesParser().getXMLBoxes();
        for (int i = 0; i < xmlBoxes.size(); ++i) {
            final Document document = xmlBoxes.get(i);
            if (this.checkXMLBox(document)) {
                if (this.vendorName == null) {
                    final NodeList elementsByTagName = document.getElementsByTagName("IID2");
                    if (elementsByTagName != null) {
                        final Node item = elementsByTagName.item(0);
                        if (item != null) {
                            final String trim = this.getSimpleElementText((Element)item).trim();
                            if (trim.length() > 8) {
                                final String substring = trim.substring(7, 9);
                                if (substring != null) {
                                    final URL urlAsResource = Helper.getURLAsResource("vendormapping.properties");
                                    final Properties properties = new Properties();
                                    if (urlAsResource != null) {
                                        try {
                                            properties.load(urlAsResource.openStream());
                                            if (properties.size() > 0) {
                                                final String property = properties.getProperty(substring);
                                                if (property != null) {
                                                    this.vendorName = property;
                                                }
                                            }
                                        }
                                        catch (IOException ex) {
                                            this.log.error("Unable to read vendor platform mapping properties file: " + ex.getMessage());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (this.vendorLicense == null) {
                    final NodeList elementsByTagName2 = document.getElementsByTagName("Text_Segment");
                    if (elementsByTagName2 != null) {
                        for (int j = 0; j < elementsByTagName2.getLength(); ++j) {
                            final NodeList elementsByTagName3 = ((Element)elementsByTagName2.item(j)).getElementsByTagName("TEXTID");
                            if (elementsByTagName3 != null) {
                                final Node item2 = elementsByTagName3.item(0);
                                if (item2 != null && this.getSimpleElementText((Element)item2).toLowerCase().indexOf("license") >= 0) {
                                    final NodeList elementsByTagName4 = ((Element)elementsByTagName2.item(j)).getElementsByTagName("TEXT");
                                    if (elementsByTagName4 != null) {
                                        final Node item3 = elementsByTagName4.item(0);
                                        if (item3 != null) {
                                            final String trim2 = this.getSimpleElementText((Element)item3).trim();
                                            if (trim2 != null) {
                                                this.vendorLicense = trim2;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (this.vendorName != null && this.vendorLicense != null) {
                break;
            }
        }
    }
    
    public String getLicenseVendor() {
        return this.vendorName;
    }
    
    public String getLicenseContent() {
        return this.vendorLicense;
    }
    
    public boolean checkXMLBox(final Document document) {
        return document.getDocumentElement().getNodeName().equals("OriginalMetadata") || document.getDocumentElement().getNodeName().equals("parsedFile");
    }
    
    private String getSimpleElementText(final Element element) {
        final StringBuffer sb = new StringBuffer();
        final NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); ++i) {
            final Node item = childNodes.item(i);
            if (item instanceof Text) {
                sb.append(item.getNodeValue());
            }
        }
        return sb.toString();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
