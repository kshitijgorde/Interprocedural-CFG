// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.xml;

import java.util.Iterator;
import com.pluraprocessing.common.domain.Executable;
import com.pluraprocessing.common.domain.NodeType;
import java.util.UUID;
import com.pluraprocessing.common.domain.WorkVersion;
import java.util.ArrayList;

public class WorkVersionParser
{
    public static ArrayList<WorkVersion> getVersionContent(final String xml) throws Exception {
        final ArrayList<WorkVersion> versionList = new ArrayList<WorkVersion>();
        final UUID ownerId = UUID.fromString(XmlStringParser.getAttribute("vs", "ownerId", xml));
        final ArrayList<String> versionNodes = XmlStringParser.getAllNodes("v", xml);
        versionList.ensureCapacity(versionNodes.size());
        for (final String versionNode : versionNodes) {
            final WorkVersion version = new WorkVersion();
            final String extId = XmlStringParser.getAttribute("v", "extId", versionNode);
            if (extId != null && !extId.equals("")) {
                version.setExternalId(extId);
            }
            final String parentId = XmlStringParser.getAttribute("v", "parentId", versionNode);
            if (parentId != null && !parentId.equals("")) {
                version.setParentId(parentId);
            }
            final String timeSensitive = XmlStringParser.getAttribute("v", "timeSensitive", versionNode);
            if (timeSensitive != null && !timeSensitive.equals("")) {
                version.setTimeSensitive(timeSensitive.equals("1"));
            }
            final String minRetry = XmlStringParser.getAttribute("v", "minRetry", versionNode);
            if (minRetry != null && !minRetry.equals("")) {
                version.setMinimumRetryTimeSeconds(Integer.parseInt(minRetry));
            }
            final String heap = XmlStringParser.getAttribute("v", "heapMB", versionNode);
            if (heap != null && !heap.equals("")) {
                version.setMaxHeapSpaceNeededMB(Integer.parseInt(heap));
            }
            final String priority = XmlStringParser.getAttribute("v", "priority", versionNode);
            if (priority != null && !priority.equals("")) {
                version.setPriority(Integer.parseInt(priority));
            }
            final String nodeType = XmlStringParser.getAttribute("v", "nodeType", versionNode);
            version.setNodeType(Enum.valueOf(NodeType.class, nodeType));
            version.setWorkOwnerId(ownerId);
            final ArrayList<String> executableNodes = XmlStringParser.getAllNodesWithoutChildren("e", versionNode);
            for (final String executableNode : executableNodes) {
                final Executable e = new Executable();
                e.setId(UUID.fromString(XmlStringParser.getAttribute("e", "id", executableNode)));
                version.getExecutables().add(e);
            }
            versionList.add(version);
        }
        return versionList;
    }
    
    public static ArrayList<String> getVersionIdsContent(final String xml) throws Exception {
        final ArrayList<String> versionList = new ArrayList<String>();
        final ArrayList<String> versionNodes = XmlStringParser.getAllNodesWithoutChildren("v", xml);
        versionList.ensureCapacity(versionNodes.size());
        for (final String versionNode : versionNodes) {
            versionList.add(XmlStringParser.getAttribute("v", "id", versionNode));
        }
        return versionList;
    }
    
    public static String createNewVersionXml(final ArrayList<WorkVersion> versions) {
        final XmlWriter writer = new XmlWriter();
        writer.completeOpenTag("vs");
        for (final WorkVersion v : versions) {
            writer.completeEmptyTagSetWithId("v", v.getId().toString());
        }
        writer.completeCloseTag("vs");
        return writer.GetXml();
    }
    
    public static String createNewVersionXmlWithIds(final ArrayList<String> versionIds) {
        final XmlWriter writer = new XmlWriter();
        writer.completeOpenTag("vs");
        for (final String v : versionIds) {
            writer.completeEmptyTagSetWithId("v", v);
        }
        writer.completeCloseTag("vs");
        return writer.GetXml();
    }
    
    public static String getFullVersionXML(final ArrayList<WorkVersion> versions) {
        final XmlWriter writer = new XmlWriter();
        writer.completeOpenTag("vs");
        for (final WorkVersion v : versions) {
            writer.partialOpenTag("v").property("id", v.getId().toString()).property("extId", v.getExternalId()).property("percentage", String.valueOf(v.getPercentage())).property("activeWorkCount", String.valueOf(v.getUncompletedCount())).property("nodeType", String.valueOf(v.getNodeType().name().toString())).property("percentageNode", String.valueOf(v.getPercentageNode()));
            writer.partialCloseTag();
        }
        writer.completeCloseTag("vs");
        return writer.GetXml();
    }
}
