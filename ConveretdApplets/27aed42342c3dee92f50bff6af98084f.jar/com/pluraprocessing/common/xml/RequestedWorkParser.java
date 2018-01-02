// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.xml;

import java.util.ArrayList;
import java.util.Calendar;
import com.pluraprocessing.common.domain.RequestedWork;
import com.pluraprocessing.common.domain.BandwidthResult;
import java.util.Date;
import java.util.UUID;
import com.pluraprocessing.common.domain.CompletedWork;
import java.util.Iterator;
import com.pluraprocessing.common.domain.Data;
import com.pluraprocessing.common.domain.Work;
import com.pluraprocessing.common.domain.WorkVersion;

public class RequestedWorkParser
{
    public static String getReloadVersionFromXml(final String xml) {
        final String str = XmlStringParser.getAttribute("reload", "v", xml);
        return WorkVersion.getVersionFileString(str);
    }
    
    public static String getRequestedWorkXML(final Work work, final Long dateCreated) {
        final XmlWriter writer = new XmlWriter();
        writer.partialOpenTag("rw");
        writer.property("workId", work.getId().toString());
        writer.property("date", dateCreated.toString());
        writer.closeBracket();
        if (!work.getData().isEmpty()) {
            writer.completeOpenTag("ds");
            for (final Data data : work.getData()) {
                writer.completeEmptyTagSetWithId("d", data.getId().toString());
            }
            writer.completeCloseTag("ds");
        }
        if (work.getContent() != null && !work.getContent().equals("")) {
            writer.completeTagSet("c", work.getContent());
        }
        writer.completeCloseTag("rw");
        return writer.GetXml();
    }
    
    public static String getReloadXML(final String versionId) {
        final XmlWriter writer = new XmlWriter();
        writer.reload(versionId);
        return writer.GetXml();
    }
    
    public static CompletedWork getCompletedObject(final String xml) {
        final CompletedWork requestedWork = new CompletedWork();
        requestedWork.getWork().setId(UUID.fromString(XmlStringParser.getAttribute("rw", "workId", xml)));
        requestedWork.setCpuUsage(Double.valueOf(XmlStringParser.getAttribute("rw", "cpu", xml)));
        requestedWork.getWork().getVersion().setId(UUID.fromString(XmlStringParser.getAttribute("rw", "v", xml)));
        requestedWork.setDateRequested(new Date(Long.parseLong(XmlStringParser.getAttribute("rw", "date", xml))));
        final String bandwidth = XmlStringParser.getAttribute("rw", "bw", xml);
        if (bandwidth != null) {
            final BandwidthResult bandwidthResult = new BandwidthResult();
            bandwidthResult.setBandwidthUsedKB(Integer.valueOf(bandwidth));
            requestedWork.setBandwidthResult(bandwidthResult);
        }
        final String client = XmlStringParser.getAttribute("rw", "client", xml);
        if (client != null && !client.equals("")) {
            requestedWork.setClientIdentifier(client);
        }
        final String affiliate = XmlStringParser.getAttribute("rw", "affiliate", xml);
        if (affiliate != null && !affiliate.equals("")) {
            requestedWork.setAffiliateId(affiliate);
        }
        requestedWork.setResult(XmlStringParser.getNodeValue("r", xml));
        return requestedWork;
    }
    
    public static RequestedWork getRequestedObject(final String xml) {
        final RequestedWork requestedWork = new RequestedWork();
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(XmlStringParser.getAttribute("rw", "date", xml)));
        requestedWork.setDateRequested(calendar);
        requestedWork.getWork().setContent(XmlStringParser.getNodeValue("c", xml));
        requestedWork.getWork().setId(UUID.fromString(XmlStringParser.getAttribute("rw", "workId", xml)));
        final ArrayList<String> dataNodes = XmlStringParser.getAllNodesWithoutChildren("d", xml);
        if (dataNodes.size() > 0) {
            requestedWork.getWork().getData().ensureCapacity(dataNodes.size());
            for (final String node : dataNodes) {
                final Data data = new Data();
                data.setId(UUID.fromString(XmlStringParser.getAttribute("d", "id", node)));
                requestedWork.getWork().getData().add(data);
            }
        }
        return requestedWork;
    }
    
    public static String getCompletedWorkXML(final String result, final String workId, final Double cpuUsage, final Long bandwidthUsed, final String versionId, final Long dateCreated, final String affiliateId, final String clientId) {
        final XmlWriter writer = new XmlWriter();
        writer.partialOpenTag("rw");
        writer.property("v", versionId);
        writer.property("date", dateCreated.toString());
        writer.property("workId", workId);
        writer.property("cpu", cpuUsage.toString());
        if (clientId != null && !clientId.equals("")) {
            writer.property("client", clientId);
        }
        if (bandwidthUsed != null) {
            writer.property("bw", bandwidthUsed.toString());
        }
        if (affiliateId != null && !affiliateId.equals("")) {
            writer.property("affiliate", affiliateId);
        }
        writer.closeBracket();
        writer.completeTagSet("r", result);
        writer.completeCloseTag("rw");
        return writer.GetXml();
    }
}
