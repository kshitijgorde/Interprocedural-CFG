// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.util;

import org.w3c.dom.Text;
import java.awt.Color;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import com.itt.J2KViewer.controller.ViewCentral;

public class SecurityUtil
{
    public static final String IMAGE_SEGMENT = "IMAGE";
    public static final String FILE_SEGMENT = "FILE";
    public static final String BOTH_SEGMENTS = "BOTH";
    
    public static String getSecurityClassification(final ViewCentral viewCentral, final String s) {
        String s2;
        if (s.equals("IMAGE")) {
            s2 = "ISCLAS";
        }
        else {
            s2 = "FSCLAS";
        }
        String simpleElementText = "";
        final List xmlBoxes = viewCentral.getXmlPropertiesParser().getXMLBoxes();
        boolean b = false;
        Node item = null;
        for (int i = 0; i < xmlBoxes.size(); ++i) {
            final Document document = xmlBoxes.get(i);
            if (checkXMLBox(document)) {
                final NodeList elementsByTagName = document.getElementsByTagName(s2);
                if (elementsByTagName != null) {
                    item = elementsByTagName.item(0);
                    if (item != null) {
                        b = true;
                        break;
                    }
                }
            }
        }
        if (b) {
            simpleElementText = DOMUtil.getSimpleElementText((Element)item);
        }
        return simpleElementText;
    }
    
    public static String getSecurityReleasability(final ViewCentral viewCentral, final String s) {
        String s2;
        if (s.equals("IMAGE")) {
            s2 = "ISREL";
        }
        else {
            s2 = "FSREL";
        }
        String simpleElementText = "";
        final List xmlBoxes = viewCentral.getXmlPropertiesParser().getXMLBoxes();
        boolean b = false;
        Node item = null;
        for (int i = 0; i < xmlBoxes.size(); ++i) {
            final Document document = xmlBoxes.get(i);
            if (checkXMLBox(document)) {
                final NodeList elementsByTagName = document.getElementsByTagName(s2);
                if (elementsByTagName != null) {
                    item = elementsByTagName.item(0);
                    if (item != null) {
                        b = true;
                        break;
                    }
                }
            }
        }
        if (b) {
            simpleElementText = DOMUtil.getSimpleElementText((Element)item);
        }
        return simpleElementText;
    }
    
    public static String getCollectionDate(final ViewCentral viewCentral) {
        String formatDateString = "";
        final List xmlBoxes = viewCentral.getXmlPropertiesParser().getXMLBoxes();
        boolean b = false;
        Node item = null;
        for (int i = 0; i < xmlBoxes.size(); ++i) {
            final Document document = xmlBoxes.get(i);
            if (checkXMLBox(document)) {
                final NodeList elementsByTagName = document.getElementsByTagName("IDATIM");
                if (elementsByTagName != null) {
                    item = elementsByTagName.item(0);
                    if (item != null) {
                        b = true;
                        break;
                    }
                }
            }
        }
        if (b) {
            formatDateString = formatDateString(DOMUtil.getSimpleElementText((Element)item));
        }
        return formatDateString;
    }
    
    public static Color getClassificationColor(final String s) {
        if (s.equalsIgnoreCase("T")) {
            return new Color(251, 138, 27);
        }
        if (s.equalsIgnoreCase("S")) {
            return Color.red;
        }
        if (s.equalsIgnoreCase("C")) {
            return new Color(116, 194, 251);
        }
        if (s.equalsIgnoreCase("R")) {
            return new Color(235, 77, 254);
        }
        if (s.equalsIgnoreCase("U")) {
            return Color.green;
        }
        return Color.white;
    }
    
    public static String getClassificationString(final String s) {
        if (s.equalsIgnoreCase("T")) {
            return new String("Top Secret");
        }
        if (s.equalsIgnoreCase("S")) {
            return new String("Secret");
        }
        if (s.equalsIgnoreCase("C")) {
            return new String("Confidential");
        }
        if (s.equalsIgnoreCase("R")) {
            return new String("Restricted");
        }
        if (s.equalsIgnoreCase("U")) {
            return new String("Unclassified");
        }
        return new String("No security information is available.");
    }
    
    public static String formatDateString(final String s) {
        final String s2 = new String();
        String s3;
        if (s.indexOf("Z") > 0) {
            s3 = s.substring(9, 12) + " " + s.substring(0, 2) + " " + s.substring(12) + "  " + s.substring(2, 4) + ":" + s.substring(4, 6) + ":" + s.substring(6, 8) + "Z";
        }
        else {
            s3 = getMonthString(s.substring(4, 6)) + " " + s.substring(6, 8) + " " + s.substring(0, 4) + "  " + s.substring(8, 10) + ":" + s.substring(10, 12) + ":" + s.substring(12) + "Z";
        }
        return s3;
    }
    
    public static String getMonthString(final String s) {
        int int1 = 0;
        try {
            int1 = Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {}
        switch (int1) {
            case 1: {
                return "JAN";
            }
            case 2: {
                return "FEB";
            }
            case 3: {
                return "MAR";
            }
            case 4: {
                return "APR";
            }
            case 5: {
                return "MAY";
            }
            case 6: {
                return "JUN";
            }
            case 7: {
                return "JUL";
            }
            case 8: {
                return "AUG";
            }
            case 9: {
                return "SEP";
            }
            case 10: {
                return "OCT";
            }
            case 11: {
                return "NOV";
            }
            case 12: {
                return "DEC";
            }
            default: {
                return "---";
            }
        }
    }
    
    public static boolean checkXMLBox(final Document document) {
        return document.getDocumentElement().getNodeName().equals("OriginalMetadata") || document.getDocumentElement().getNodeName().equals("parsedFile");
    }
    
    public static class DOMUtil
    {
        public static Element getFirstElement(final Element element, final String s) {
            final NodeList elementsByTagName = element.getElementsByTagName(s);
            if (elementsByTagName.getLength() < 1) {
                throw new RuntimeException("Element: " + element + " does not contain: " + s);
            }
            return (Element)elementsByTagName.item(0);
        }
        
        public static String getSimpleElementText(final Element element, final String s) {
            return getSimpleElementText(getFirstElement(element, s));
        }
        
        public static String getSimpleElementText(final Element element) {
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
    }
}
