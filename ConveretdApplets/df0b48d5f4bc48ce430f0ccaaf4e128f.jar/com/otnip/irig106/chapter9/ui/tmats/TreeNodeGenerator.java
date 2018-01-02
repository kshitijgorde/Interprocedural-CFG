// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter9.ui.tmats;

import com.otnip.irig106.chapter9.r.PCMDataTypeAttributes;
import com.otnip.irig106.chapter9.r.AnalogDataTypeAttributes;
import com.otnip.irig106.chapter9.r.Data;
import com.otnip.irig106.chapter9.r.RecordingEvent;
import java.util.Iterator;
import com.otnip.irig106.chapter9.r.R;
import com.otnip.irig106.chapter9.common.PointOfContact;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.DefaultMutableTreeNode;
import com.otnip.irig106.chapter9.TMATS;
import com.otnip.irig106.chapter9.g.G;

public class TreeNodeGenerator
{
    public static DefaultMutableTreeNode generateGNode(final G g, final TMATS tmats) {
        final DefaultMutableTreeNode gNode = new DefaultMutableTreeNode(new TMATSTreeField("General Information Group (G)", null, g, "/IconExperience/icons/24/plain/text.png"));
        gNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Program Name", "programName", g)));
        gNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Test Item", "testItem", g)));
        gNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Comments", "comments", g)));
        final DefaultMutableTreeNode informationNode = new DefaultMutableTreeNode(new TMATSTreeField("Information", null, g.information));
        gNode.add(informationNode);
        informationNode.add(new DefaultMutableTreeNode(new TMATSTreeField("IRIG 106 Revision Level", "irig106RevisionLevel", g.information)));
        informationNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Origination Date", "originationDate", g.information)));
        informationNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Revision Number", "revisionNumber", g.information)));
        informationNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Revision Date", "revisionDate", g.information)));
        informationNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Update Number", "updateNumber", g.information)));
        informationNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Update Date", "updateDate", g.information)));
        informationNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Test Number", "testNumber", g.information)));
        final DefaultMutableTreeNode contactsNode = new DefaultMutableTreeNode(new TMATSTreeField("Point Of Contact(s)", null, g.information, "/IconExperience/icons/24/plain/id_cards.png"));
        informationNode.add(contactsNode);
        for (final PointOfContact contact : g.information.pointOfContacts) {
            contactsNode.add(createPointOfContactNode("Point Of Contact", contact));
        }
        final DefaultMutableTreeNode testInformationNode = new DefaultMutableTreeNode(new TMATSTreeField("Test Information", null, g));
        gNode.add(testInformationNode);
        testInformationNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Update Number", "testDuration", g.testInformation)));
        testInformationNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Pre-Test Requirement", "preTestRequirement", g.testInformation)));
        testInformationNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Post-Test Requirement", "postTestRequirement", g.testInformation)));
        testInformationNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Security Classification", "securityClassification", g.testInformation)));
        final DefaultMutableTreeNode dataSourceIdentificationsNode = new DefaultMutableTreeNode(new TMATSTreeField("Data Source(s)", null, g));
        gNode.add(dataSourceIdentificationsNode);
        for (final R r : tmats.rs) {
            dataSourceIdentificationsNode.add(generateRNode(r));
        }
        return gNode;
    }
    
    public static DefaultMutableTreeNode generateRNode(final R r) {
        final DefaultMutableTreeNode rNode = new DefaultMutableTreeNode(new TMATSTreeField("Tape/Storage (R)", null, r, "/IconExperience/icons/24/plain/movie.png"));
        rNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Data Source ID", "dataSourceID", r)));
        rNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Tape/Storage ID", "storageID", r)));
        rNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Tape/Storage Description", "storageDescription", r)));
        final DefaultMutableTreeNode storageCharacteristicsNode = new DefaultMutableTreeNode(new TMATSTreeField("Tape/Storage Characteristics", null, r.storageCharacteristics));
        rNode.add(storageCharacteristicsNode);
        storageCharacteristicsNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Tape/Storage Type", "storageType", r.storageCharacteristics)));
        storageCharacteristicsNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Tape/Storage Manufacturer", "storageManufacturer", r.storageCharacteristics)));
        storageCharacteristicsNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Tape/Storage Code", "storageCode", r.storageCharacteristics)));
        storageCharacteristicsNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Tape Width", "tapeWidth", r.storageCharacteristics)));
        storageCharacteristicsNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Tape Housing", "tapeHousing", r.storageCharacteristics)));
        storageCharacteristicsNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Type Of Tracks", "typeOfTracks", r.storageCharacteristics)));
        storageCharacteristicsNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Number Of Tracks/Channels", "numberOfTracks", r.storageCharacteristics)));
        storageCharacteristicsNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Record Speed", "recordSpeed", r.storageCharacteristics)));
        storageCharacteristicsNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Data Packing Density", "dataPackingDensity", r.storageCharacteristics)));
        storageCharacteristicsNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Tape Rewound", "tapeRewound", r.storageCharacteristics)));
        storageCharacteristicsNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Number Of Source Bits", "numberOfSourceBits", r.storageCharacteristics)));
        final DefaultMutableTreeNode recorderInformationNode = new DefaultMutableTreeNode(new TMATSTreeField("Recorder Infomation", null, r.recorderInformation));
        rNode.add(recorderInformationNode);
        recorderInformationNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Tape Drive/Storage Manufacturer", "storageManufacturer", r.recorderInformation)));
        recorderInformationNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Tape Drive/Storage Model", "storageModel", r.recorderInformation)));
        recorderInformationNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Original Tape/Storage", "originalStorage", r.recorderInformation)));
        recorderInformationNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Date And Time Created", "dateAndTimeCreated", r.recorderInformation)));
        recorderInformationNode.add(createPointOfContactNode("Creating Organization Contact", r.recorderInformation.creatingOrganization));
        recorderInformationNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Date Of Dub", "dateOfDub", r.recorderInformation)));
        recorderInformationNode.add(createPointOfContactNode("Dubbing Organization Contact", r.recorderInformation.creatingOrganization));
        final DefaultMutableTreeNode recordingEventDefinitionsNode = new DefaultMutableTreeNode(new TMATSTreeField("Recording Event Definitions", null, r.recordingEventDefinitions));
        rNode.add(recordingEventDefinitionsNode);
        recordingEventDefinitionsNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Recording Events Enabled", "recordingEventsEnabled", r.recordingEventDefinitions)));
        for (final RecordingEvent recordingEvent : r.recordingEventDefinitions.recordingEvents) {
            final DefaultMutableTreeNode recordingEventNode = new DefaultMutableTreeNode(new TMATSTreeField("Recording Event", null, recordingEvent));
            recorderInformationNode.add(recordingEventNode);
            recordingEventNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Event ID", "eventID", recordingEvent)));
            recordingEventNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Event Description", "eventDescription", recordingEvent)));
            recordingEventNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Event Type", "eventType", recordingEvent)));
            recordingEventNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Event Priority", "eventPriority", recordingEvent)));
            recordingEventNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Recording Event Limit Count", "recordingEventLimitCount", recordingEvent)));
            recordingEventNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Event Measurement Source", "eventMeasurementSource", recordingEvent)));
            recordingEventNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Event Measurement Name", "eventMeasurementName", recordingEvent)));
        }
        final DefaultMutableTreeNode recordingIndexNode = new DefaultMutableTreeNode(new TMATSTreeField("Recording Index", null, r.recordingIndex));
        rNode.add(recordingIndexNode);
        recordingIndexNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Recording Index Enabled", "recordingIndexEnabled", r.recordingIndex)));
        recordingIndexNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Recording Index Type", "recordingIndexType", r.recordingIndex)));
        final DefaultMutableTreeNode datasNode = new DefaultMutableTreeNode(new TMATSTreeField("Data Channels", null, r));
        rNode.add(datasNode);
        for (final Data data : r.datas) {
            final DefaultMutableTreeNode dataNode = new DefaultMutableTreeNode(new TMATSTreeField("Data", null, data));
            datasNode.add(dataNode);
            dataNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Track Number/Channel ID", "trackNumber", data)));
            dataNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Recording Technique", "recordingTechnique", data)));
            dataNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Data Source ID", "dataSourceID", data)));
            dataNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Data Direction", "dataDirection", data)));
            dataNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Channel Enable", "channelEnable", data)));
            dataNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Channel Data Type", "channelDataType", data)));
            if (data.channelDataType != null) {
                switch (data.channelDataType) {
                    case ANALOG: {
                        final AnalogDataTypeAttributes analogDataTypeAttributes = (AnalogDataTypeAttributes)data.channelDataTypeAttributes;
                        final DefaultMutableTreeNode analogNode = new DefaultMutableTreeNode(new TMATSTreeField("Analog Data Type Attributes", null, data));
                        dataNode.add(analogNode);
                        analogNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Number Of Analog Channels Per Packet", "numberOfAnalogChannelsPerPacket", analogDataTypeAttributes)));
                        analogNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Data Packing Option", "dataPackingOptions", analogDataTypeAttributes)));
                        analogNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Sample Rate", "sampleRate", analogDataTypeAttributes)));
                        final DefaultMutableTreeNode measurementsNode = new DefaultMutableTreeNode(new TMATSTreeField("Measurements", null, analogDataTypeAttributes));
                        analogNode.add(measurementsNode);
                        for (final AnalogDataTypeAttributes.Measurement measurement : analogDataTypeAttributes.measurements) {
                            final DefaultMutableTreeNode measurementNode = new DefaultMutableTreeNode(new TMATSTreeField("Measurement", null, measurement));
                            measurementsNode.add(measurementNode);
                            measurementNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Measurement Name", "measurementName", measurement)));
                            measurementNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Data Length", "dataLength", measurement)));
                            measurementNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Bit Mask", "bitMask", measurement)));
                            measurementNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Measurement Transfer Order", "measurementTransferOrder", measurement)));
                            measurementNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Sample Factor", "sampleFactor", measurement)));
                        }
                        continue;
                    }
                    case PCM: {
                        final PCMDataTypeAttributes pcmDataTypeAttributes = (PCMDataTypeAttributes)data.channelDataTypeAttributes;
                        final DefaultMutableTreeNode pcmNode = new DefaultMutableTreeNode(new TMATSTreeField("PCM Data Type Attributes", null, data));
                        dataNode.add(pcmNode);
                        pcmNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Data Link Name", "dataLinkName", pcmDataTypeAttributes)));
                        pcmNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Data Packing Option", "dataPackingOption", pcmDataTypeAttributes)));
                        pcmNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Type Format", "typeFormat", pcmDataTypeAttributes)));
                        continue;
                    }
                    default: {
                        dataNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Type Not Supported Yet", null, data, "/IconExperience/icons/24/plain/error.png")));
                        continue;
                    }
                }
            }
            else {
                dataNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Type Not Supported Yet", null, data, "/IconExperience/icons/24/plain/error.png")));
            }
        }
        return rNode;
    }
    
    public static DefaultMutableTreeNode createPointOfContactNode(final String nodeName, final PointOfContact pointOfContact) {
        final DefaultMutableTreeNode pointOfContactNode = new DefaultMutableTreeNode(new TMATSTreeField(nodeName, null, pointOfContact, "/IconExperience/icons/24/plain/id_card.png"));
        pointOfContactNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Name", "name", pointOfContact)));
        pointOfContactNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Agency", "agency", pointOfContact)));
        pointOfContactNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Address", "address", pointOfContact)));
        pointOfContactNode.add(new DefaultMutableTreeNode(new TMATSTreeField("Telephone", "telephone", pointOfContact)));
        return pointOfContactNode;
    }
}
