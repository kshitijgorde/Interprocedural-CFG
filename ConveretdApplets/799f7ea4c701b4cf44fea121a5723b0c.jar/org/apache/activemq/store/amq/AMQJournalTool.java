// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.amq;

import org.apache.velocity.Template;
import java.io.Writer;
import org.apache.velocity.context.Context;
import java.io.OutputStream;
import java.io.PrintWriter;
import org.apache.activemq.command.ActiveMQMessage;
import java.util.Collections;
import org.apache.activemq.command.JournalTransaction;
import java.io.InputStream;
import java.util.Scanner;
import org.apache.activemq.util.ByteSequence;
import org.apache.activemq.kaha.impl.async.Location;
import java.util.List;
import java.util.Iterator;
import org.apache.activemq.command.DataStructure;
import org.apache.activemq.kaha.impl.async.ReadOnlyAsyncDataManager;
import org.josql.Query;
import java.util.Map;
import java.util.Arrays;
import org.apache.activemq.openwire.OpenWireFormat;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.VelocityContext;
import java.util.HashMap;
import org.apache.activemq.wireformat.WireFormat;
import java.io.File;
import java.util.ArrayList;

public class AMQJournalTool
{
    private final ArrayList<File> dirs;
    private final WireFormat wireFormat;
    private final HashMap<String, String> resources;
    private String messageFormat;
    private String topicAckFormat;
    private String queueAckFormat;
    private String transactionFormat;
    private String traceFormat;
    private String unknownFormat;
    private String where;
    private VelocityContext context;
    private VelocityEngine velocity;
    private boolean help;
    
    public AMQJournalTool() {
        this.dirs = new ArrayList<File>();
        this.wireFormat = new OpenWireFormat();
        this.resources = new HashMap<String, String>();
        this.messageFormat = "${location.dataFileId},${location.offset}|${type}|${record.destination}|${record.messageId}|${record.properties}|${body}";
        this.topicAckFormat = "${location.dataFileId},${location.offset}|${type}|${record.destination}|${record.clientId}|${record.subscritionName}|${record.messageId}";
        this.queueAckFormat = "${location.dataFileId},${location.offset}|${type}|${record.destination}|${record.messageAck.lastMessageId}";
        this.transactionFormat = "${location.dataFileId},${location.offset}|${type}|${record.transactionId}";
        this.traceFormat = "${location.dataFileId},${location.offset}|${type}|${record.message}";
        this.unknownFormat = "${location.dataFileId},${location.offset}|${type}|${record.class.name}";
    }
    
    public static void main(final String[] args) throws Exception {
        final AMQJournalTool consumerTool = new AMQJournalTool();
        final String[] directories = CommandLineSupport.setOptions(consumerTool, args);
        if (directories.length < 1) {
            System.out.println("Please specify the directories with journal data to scan");
            return;
        }
        for (int i = 0; i < directories.length; ++i) {
            consumerTool.getDirs().add(new File(directories[i]));
        }
        consumerTool.execute();
    }
    
    public void execute() throws Exception {
        if (this.help) {
            this.showHelp();
            return;
        }
        if (this.getDirs().size() < 1) {
            System.out.println("");
            System.out.println("Invalid Usage: Please specify the directories with journal data to scan");
            System.out.println("");
            this.showHelp();
            return;
        }
        for (final File dir : this.getDirs()) {
            if (!dir.exists()) {
                System.out.println("");
                System.out.println("Invalid Usage: the directory '" + dir.getPath() + "' does not exist");
                System.out.println("");
                this.showHelp();
                return;
            }
            if (!dir.isDirectory()) {
                System.out.println("");
                System.out.println("Invalid Usage: the argument '" + dir.getPath() + "' is not a directory");
                System.out.println("");
                this.showHelp();
                return;
            }
        }
        this.context = new VelocityContext();
        final List keys = Arrays.asList(this.context.getKeys());
        for (final Map.Entry kv : System.getProperties().entrySet()) {
            final String name = kv.getKey();
            final String value = kv.getValue();
            if (!keys.contains(name)) {
                this.context.put(name, (Object)value);
            }
        }
        (this.velocity = new VelocityEngine()).setProperty("resource.loader", (Object)"all");
        this.velocity.setProperty("all.resource.loader.class", (Object)CustomResourceLoader.class.getName());
        this.velocity.init();
        this.resources.put("message", this.messageFormat);
        this.resources.put("topicAck", this.topicAckFormat);
        this.resources.put("queueAck", this.queueAckFormat);
        this.resources.put("transaction", this.transactionFormat);
        this.resources.put("trace", this.traceFormat);
        this.resources.put("unknown", this.unknownFormat);
        Query query = null;
        if (this.where != null) {
            query = new Query();
            query.parse("select * from " + Entry.class.getName() + " where " + this.where);
        }
        final ReadOnlyAsyncDataManager manager = new ReadOnlyAsyncDataManager(this.getDirs());
        manager.start();
        try {
            for (Location curr = manager.getFirstLocation(); curr != null; curr = manager.getNextLocation(curr)) {
                final ByteSequence data = manager.read(curr);
                final DataStructure c = (DataStructure)this.wireFormat.unmarshal(data);
                final Entry entry = new Entry();
                entry.setLocation(curr);
                entry.setRecord(c);
                entry.setData(data);
                entry.setQuery(query);
                this.process(entry);
            }
        }
        finally {
            manager.close();
        }
    }
    
    private void showHelp() {
        final InputStream is = AMQJournalTool.class.getResourceAsStream("help.txt");
        final Scanner scanner = new Scanner(is);
        while (scanner.hasNextLine()) {
            final String line = scanner.nextLine();
            System.out.println(line);
        }
        scanner.close();
    }
    
    private void process(final Entry entry) throws Exception {
        final Location location = entry.getLocation();
        final DataStructure record = entry.getRecord();
        switch (record.getDataStructureType()) {
            case 23: {
                entry.setType("ActiveMQMessage");
                entry.setFormater("message");
                this.display(entry);
                break;
            }
            case 24: {
                entry.setType("ActiveMQBytesMessage");
                entry.setFormater("message");
                this.display(entry);
                break;
            }
            case 29: {
                entry.setType("ActiveMQBlobMessage");
                entry.setFormater("message");
                this.display(entry);
                break;
            }
            case 25: {
                entry.setType("ActiveMQMapMessage");
                entry.setFormater("message");
                this.display(entry);
                break;
            }
            case 26: {
                entry.setType("ActiveMQObjectMessage");
                entry.setFormater("message");
                this.display(entry);
                break;
            }
            case 27: {
                entry.setType("ActiveMQStreamMessage");
                entry.setFormater("message");
                this.display(entry);
                break;
            }
            case 28: {
                entry.setType("ActiveMQTextMessage");
                entry.setFormater("message");
                this.display(entry);
                break;
            }
            case 52: {
                entry.setType("Queue Ack");
                entry.setFormater("queueAck");
                this.display(entry);
                break;
            }
            case 50: {
                entry.setType("Topic Ack");
                entry.setFormater("topicAck");
                this.display(entry);
                break;
            }
            case 54: {
                entry.setType(this.getType((JournalTransaction)record));
                entry.setFormater("transaction");
                this.display(entry);
                break;
            }
            case 53: {
                entry.setType("Trace");
                entry.setFormater("trace");
                this.display(entry);
                break;
            }
            default: {
                entry.setType("Unknown");
                entry.setFormater("unknown");
                this.display(entry);
                break;
            }
        }
    }
    
    private String getType(final JournalTransaction record) {
        switch (record.getType()) {
            case 1: {
                return "XA Prepare";
            }
            case 2: {
                return "XA Commit";
            }
            case 3: {
                return "XA Rollback";
            }
            case 4: {
                return "Commit";
            }
            case 5: {
                return "Rollback";
            }
            default: {
                return "Unknown Transaction";
            }
        }
    }
    
    private void display(final Entry entry) throws Exception {
        if (entry.getQuery() != null) {
            final List list = Collections.singletonList(entry);
            final List results = entry.getQuery().execute(list).getResults();
            if (results.isEmpty()) {
                return;
            }
        }
        CustomResourceLoader.setResources(this.resources);
        try {
            this.context.put("location", (Object)entry.getLocation());
            this.context.put("record", (Object)entry.getRecord());
            this.context.put("type", (Object)entry.getType());
            if (entry.getRecord() instanceof ActiveMQMessage) {
                this.context.put("body", (Object)new MessageBodyFormatter((ActiveMQMessage)entry.getRecord()));
            }
            final Template template = this.velocity.getTemplate(entry.getFormater());
            final PrintWriter writer = new PrintWriter(System.out);
            template.merge((Context)this.context, (Writer)writer);
            writer.println();
            writer.flush();
        }
        finally {
            CustomResourceLoader.setResources(null);
        }
    }
    
    public void setMessageFormat(final String messageFormat) {
        this.messageFormat = messageFormat;
    }
    
    public void setTopicAckFormat(final String ackFormat) {
        this.topicAckFormat = ackFormat;
    }
    
    public void setTransactionFormat(final String transactionFormat) {
        this.transactionFormat = transactionFormat;
    }
    
    public void setTraceFormat(final String traceFormat) {
        this.traceFormat = traceFormat;
    }
    
    public void setUnknownFormat(final String unknownFormat) {
        this.unknownFormat = unknownFormat;
    }
    
    public void setQueueAckFormat(final String queueAckFormat) {
        this.queueAckFormat = queueAckFormat;
    }
    
    public String getQuery() {
        return this.where;
    }
    
    public void setWhere(final String query) {
        this.where = query;
    }
    
    public boolean isHelp() {
        return this.help;
    }
    
    public void setHelp(final boolean help) {
        this.help = help;
    }
    
    public ArrayList<File> getDirs() {
        return this.dirs;
    }
}
