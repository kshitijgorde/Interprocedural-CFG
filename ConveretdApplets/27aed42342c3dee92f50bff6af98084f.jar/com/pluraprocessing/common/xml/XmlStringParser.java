// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.xml;

import java.util.ArrayList;

public class XmlStringParser
{
    public static ArrayList<String> getAllNodes(final String tagName, final String xml) throws Exception {
        XmlWriter writer = new XmlWriter();
        final String openTag = writer.completeOpenTag(tagName).GetXml();
        writer = new XmlWriter();
        final String openTag2 = writer.partialOpenTag(tagName).appendString(" ").GetXml();
        writer = new XmlWriter();
        final String closeTag = writer.completeCloseTag(tagName).GetXml();
        final ArrayList<String> nodes = new ArrayList<String>();
        int startLocation = 0;
        int stopLocation = 0;
        int originalStartLocation = 0;
        while (startLocation != -1) {
            startLocation = (originalStartLocation = stopLocation);
            startLocation = xml.indexOf(openTag, startLocation);
            if (startLocation == -1) {
                startLocation = xml.indexOf(openTag2, originalStartLocation);
            }
            if (startLocation != -1) {
                stopLocation = xml.indexOf(closeTag, startLocation) + closeTag.length();
                nodes.add(xml.substring(startLocation, stopLocation));
            }
        }
        return nodes;
    }
    
    public static ArrayList<String> getAllNodesWithoutChildren(final String tagName, final String xml) {
        XmlWriter writer = new XmlWriter();
        final String openTag = writer.partialOpenTag(tagName).appendString(" ").GetXml();
        writer = new XmlWriter();
        final String closeTag = writer.partialCloseTag().GetXml();
        final ArrayList<String> nodes = new ArrayList<String>();
        int startLocation = 0;
        int stopLocation = 0;
        while (startLocation != -1) {
            startLocation = stopLocation;
            startLocation = xml.indexOf(openTag, startLocation);
            if (startLocation != -1) {
                stopLocation = xml.indexOf(closeTag, startLocation) + closeTag.length();
                nodes.add(xml.substring(startLocation, stopLocation));
            }
        }
        return nodes;
    }
    
    public static String getAttribute(final String tagName, final String attribute, final String xml) {
        XmlWriter writer = new XmlWriter();
        final String openTag = writer.partialOpenTag(tagName).appendString(" ").GetXml();
        writer = new XmlWriter();
        final String attributeTagOpen = writer.openProperty(attribute).GetXml();
        writer = new XmlWriter();
        final String attributeTagClose = writer.closeProperty().GetXml();
        int startLocation = 0;
        int stopLocation = 0;
        startLocation = xml.indexOf(openTag, startLocation);
        if (startLocation != -1) {
            startLocation = xml.indexOf(attributeTagOpen, startLocation);
            if (startLocation != -1) {
                startLocation += attributeTagOpen.length();
                stopLocation = xml.indexOf(attributeTagClose, startLocation);
                if (stopLocation != -1) {
                    return xml.substring(startLocation, stopLocation);
                }
            }
        }
        return null;
    }
    
    public static String getNodeValue(final String tagName, final String xml) {
        XmlWriter writer = new XmlWriter();
        final String openTag = writer.partialOpenTag(tagName).appendString(" ").GetXml();
        writer = new XmlWriter();
        final String openTag2 = writer.completeOpenTag(tagName).GetXml();
        writer = new XmlWriter();
        final String openTagClose = writer.closeBracket().GetXml();
        writer = new XmlWriter();
        final String closeTag = writer.completeCloseTag(tagName).GetXml();
        int startLocation = 0;
        int stopLocation = 0;
        startLocation = xml.indexOf(openTag, startLocation);
        if (startLocation == -1) {
            startLocation = xml.indexOf(openTag2, startLocation);
        }
        if (startLocation != -1) {
            startLocation = xml.indexOf(openTagClose, startLocation);
            if (startLocation != -1) {
                startLocation += openTagClose.length();
                stopLocation = xml.indexOf(closeTag, startLocation);
                if (stopLocation != -1) {
                    return xml.substring(startLocation, stopLocation);
                }
            }
        }
        return null;
    }
}
