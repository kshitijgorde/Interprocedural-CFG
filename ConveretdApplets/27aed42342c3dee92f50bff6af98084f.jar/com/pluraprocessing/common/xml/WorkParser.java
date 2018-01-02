// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.xml;

import com.pluraprocessing.common.domain.Data;
import java.util.LinkedHashMap;
import com.pluraprocessing.common.domain.CompletedWork;
import java.util.UUID;
import java.util.Iterator;
import com.pluraprocessing.common.domain.Work;
import java.util.ArrayList;

public class WorkParser
{
    public static String getRequestedWorkXML(final ArrayList<Work> work) {
        final XmlWriter writer = new XmlWriter();
        writer.completeOpenTag("ws");
        for (final Work w : work) {
            writer.completeEmptyTagSetWithId("w", w.getId().toString());
        }
        writer.completeCloseTag("ws");
        return writer.GetXml();
    }
    
    public static String getRequestedWorkXML(final String versionId, final ArrayList<Work> work) {
        final XmlWriter writer = new XmlWriter();
        writer.completeOpenWithId("ws", versionId);
        for (final Work w : work) {
            writer.completeEmptyTagSetWithId("w", w.getId().toString());
        }
        writer.completeCloseTag("ws");
        return writer.GetXml();
    }
    
    public static String getEmptyWorkTagSetXML(final ArrayList<UUID> work) {
        final XmlWriter writer = new XmlWriter();
        writer.completeOpenTag("ws");
        for (final UUID w : work) {
            writer.completeEmptyTagSetWithId("w", w.toString());
        }
        writer.completeCloseTag("ws");
        return writer.GetXml();
    }
    
    public static String getCompletedWorkXML(final ArrayList<CompletedWork> reqWork) {
        final XmlWriter writer = new XmlWriter();
        writer.completeOpenTag("ws");
        for (final CompletedWork rw : reqWork) {
            writer.completeTagSetWithId("w", rw.getWork().getId().toString(), rw.getResult());
        }
        writer.completeCloseTag("ws");
        return writer.GetXml();
    }
    
    public static LinkedHashMap<String, ArrayList<Work>> getWorkObjectsCompactMultiple(final String xml) throws Exception {
        final LinkedHashMap<String, ArrayList<Work>> workListByVersionId = new LinkedHashMap<String, ArrayList<Work>>();
        final ArrayList<String> versionNodes = XmlStringParser.getAllNodes("ws", xml);
        for (final String versionXML : versionNodes) {
            final ArrayList<Work> workList = getWorkObjectsCompact(versionXML);
            if (workList != null && !workList.isEmpty()) {
                workListByVersionId.put(workList.get(0).getVersion().getId().toString(), workList);
            }
        }
        return workListByVersionId;
    }
    
    public static ArrayList<Work> getWorkObjectsCompact(final String xml) throws Exception {
        final ArrayList<Work> workList = new ArrayList<Work>();
        final UUID versionId = UUID.fromString(XmlStringParser.getAttribute("ws", "v", xml));
        String domain = XmlStringParser.getAttribute("ws", "domain", xml);
        if (domain != null && domain.equals("")) {
            domain = null;
        }
        final ArrayList<Data> dataList = getDataObjects(xml);
        final ArrayList<String> workNodes = XmlStringParser.getAllNodes("w", xml);
        dataList.ensureCapacity(workNodes.size());
        for (final String workNode : workNodes) {
            final Work work = new Work();
            work.getVersion().setId(versionId);
            work.setTimeSensitiveDomainId(domain);
            work.setContent(XmlStringParser.getNodeValue("c", workNode));
            work.setData(dataList);
            workList.add(work);
        }
        return workList;
    }
    
    private static ArrayList<Data> getDataObjects(final String xml) {
        final ArrayList<String> dataNodes = XmlStringParser.getAllNodesWithoutChildren("d", xml);
        final ArrayList<Data> dataList = new ArrayList<Data>();
        dataList.ensureCapacity(dataNodes.size());
        for (final String dataNode : dataNodes) {
            final Data data = new Data();
            data.setId(UUID.fromString(XmlStringParser.getAttribute("d", "id", dataNode)));
            dataList.add(data);
        }
        return dataList;
    }
    
    public static ArrayList<Work> getWorkObjects(final String xml) throws Exception {
        final ArrayList<Work> dataList = new ArrayList<Work>();
        final UUID versionId = UUID.fromString(XmlStringParser.getAttribute("ws", "v", xml));
        String domain = XmlStringParser.getAttribute("ws", "domain", xml);
        if (domain != null && domain.equals("")) {
            domain = null;
        }
        final ArrayList<String> workNodes = XmlStringParser.getAllNodes("w", xml);
        dataList.ensureCapacity(workNodes.size());
        for (final String workNode : workNodes) {
            final Work work = new Work();
            work.getVersion().setId(versionId);
            work.setTimeSensitiveDomainId(domain);
            work.setContent(XmlStringParser.getNodeValue("c", workNode));
            final ArrayList<String> dataNodes = XmlStringParser.getAllNodesWithoutChildren("d", workNode);
            work.getData().ensureCapacity(dataNodes.size());
            for (final String dataNode : dataNodes) {
                final Data data = new Data();
                data.setId(UUID.fromString(XmlStringParser.getAttribute("d", "id", dataNode)));
                work.getData().add(data);
            }
            dataList.add(work);
        }
        return dataList;
    }
    
    public static ArrayList<UUID> getWorkIds(final String xml) {
        final ArrayList<UUID> idList = new ArrayList<UUID>();
        final ArrayList<String> dataNodes = XmlStringParser.getAllNodesWithoutChildren("w", xml);
        idList.ensureCapacity(dataNodes.size());
        for (final String dataNode : dataNodes) {
            final String id = XmlStringParser.getAttribute("w", "id", dataNode);
            idList.add(UUID.fromString(id));
        }
        return idList;
    }
}
