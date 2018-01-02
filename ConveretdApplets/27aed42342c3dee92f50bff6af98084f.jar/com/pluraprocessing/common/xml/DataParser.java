// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.xml;

import java.util.Iterator;
import java.util.UUID;
import com.pluraprocessing.common.domain.Data;
import java.util.ArrayList;

public class DataParser
{
    public static ArrayList<Data> GetDataContent(final String xml) throws Exception {
        final ArrayList<Data> dataList = new ArrayList<Data>();
        final UUID ownerId = UUID.fromString(XmlStringParser.getAttribute("ds", "ownerId", xml));
        final ArrayList<String> dataNodes = XmlStringParser.getAllNodes("d", xml);
        dataList.ensureCapacity(dataNodes.size());
        for (final String dataNode : dataNodes) {
            final Data data = new Data();
            data.setExternalId(XmlStringParser.getAttribute("d", "extId", dataNode));
            data.setContent(XmlStringParser.getNodeValue("d", dataNode));
            data.setOwnerId(ownerId);
            dataList.add(data);
        }
        return dataList;
    }
    
    public static ArrayList<Data> GetDataIds(final String xml) {
        final ArrayList<Data> dataList = new ArrayList<Data>();
        final ArrayList<String> dataNodes = XmlStringParser.getAllNodesWithoutChildren("d", xml);
        dataList.ensureCapacity(dataNodes.size());
        for (final String dataNode : dataNodes) {
            final Data data = new Data();
            data.setId(UUID.fromString(XmlStringParser.getAttribute("d", "id", dataNode)));
            dataList.add(data);
        }
        return dataList;
    }
    
    public static String GetDataIdXML(final ArrayList<Data> data) {
        final XmlWriter writer = new XmlWriter();
        writer.completeOpenTag("ds");
        for (final Data d : data) {
            writer.completeEmptyTagSetWithId("d", d.getId().toString());
        }
        writer.completeCloseTag("ds");
        return writer.GetXml();
    }
    
    public static String GetFullDataXML(final ArrayList<Data> data) {
        final XmlWriter writer = new XmlWriter();
        writer.completeOpenTag("ds");
        for (final Data d : data) {
            if (d.getExternalId() == null || d.getExternalId().equals("")) {
                if (d.getContent() == null) {
                    writer.completeEmptyTagSetWithId("d", d.getId().toString());
                }
                else {
                    writer.completeOpenWithId("d", d.getId().toString()).appendString(d.getContent()).completeCloseTag("d");
                }
            }
            else {
                writer.partialOpenTag("d").property("id", d.getId().toString()).property("extId", d.getExternalId());
                if (d.getContent() == null) {
                    writer.partialCloseTag();
                }
                else {
                    writer.closeBracket().appendString(d.getContent()).completeCloseTag("d");
                }
            }
        }
        writer.completeCloseTag("ds");
        return writer.GetXml();
    }
}
