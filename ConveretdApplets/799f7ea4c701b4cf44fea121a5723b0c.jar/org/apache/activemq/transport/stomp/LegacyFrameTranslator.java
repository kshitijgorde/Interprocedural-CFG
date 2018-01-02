// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport.stomp;

import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import org.apache.activemq.command.DataStructure;
import org.apache.activemq.command.ActiveMQDestination;
import javax.jms.Destination;
import java.io.IOException;
import java.util.HashMap;
import javax.jms.JMSException;
import java.util.Map;
import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.activemq.command.ActiveMQMessage;

public class LegacyFrameTranslator implements FrameTranslator
{
    @Override
    public ActiveMQMessage convertFrame(final ProtocolConverter converter, final StompFrame command) throws JMSException, ProtocolException {
        final Map headers = command.getHeaders();
        ActiveMQMessage msg;
        if (headers.containsKey("amq-msg-type")) {
            final String intendedType = headers.get("amq-msg-type");
            if (intendedType.equalsIgnoreCase("text")) {
                final ActiveMQTextMessage text = new ActiveMQTextMessage();
                try {
                    text.setText(new String(command.getContent(), "UTF-8"));
                }
                catch (Throwable e) {
                    throw new ProtocolException("Text could not bet set: " + e, false, e);
                }
                msg = text;
            }
            else {
                if (!intendedType.equalsIgnoreCase("bytes")) {
                    throw new ProtocolException("Unsupported message type '" + intendedType + "'", false);
                }
                final ActiveMQBytesMessage byteMessage = new ActiveMQBytesMessage();
                byteMessage.writeBytes(command.getContent());
                msg = byteMessage;
            }
        }
        else if (headers.containsKey("content-length")) {
            headers.remove("content-length");
            final ActiveMQBytesMessage bm = new ActiveMQBytesMessage();
            bm.writeBytes(command.getContent());
            msg = bm;
        }
        else {
            final ActiveMQTextMessage text2 = new ActiveMQTextMessage();
            try {
                text2.setText(new String(command.getContent(), "UTF-8"));
            }
            catch (Throwable e2) {
                throw new ProtocolException("Text could not bet set: " + e2, false, e2);
            }
            msg = text2;
        }
        Helper.copyStandardHeadersFromFrameToMessage(converter, command, msg, this);
        return msg;
    }
    
    @Override
    public StompFrame convertMessage(final ProtocolConverter converter, final ActiveMQMessage message) throws IOException, JMSException {
        final StompFrame command = new StompFrame();
        command.setAction("MESSAGE");
        final Map<String, String> headers = new HashMap<String, String>(25);
        command.setHeaders(headers);
        Helper.copyStandardHeadersFromMessageToFrame(converter, message, command, this);
        if (message.getDataStructureType() == 28) {
            final ActiveMQTextMessage msg = (ActiveMQTextMessage)message.copy();
            command.setContent(msg.getText().getBytes("UTF-8"));
        }
        else if (message.getDataStructureType() == 24) {
            final ActiveMQBytesMessage msg2 = (ActiveMQBytesMessage)message.copy();
            msg2.setReadOnlyBody(true);
            final byte[] data = new byte[(int)msg2.getBodyLength()];
            msg2.readBytes(data);
            headers.put("content-length", "" + data.length);
            command.setContent(data);
        }
        else if (message.getDataStructureType() == 23 && "Advisory".equals(message.getType())) {
            Helper.copyStandardHeadersFromMessageToFrame(converter, message, command, this);
            final String body = this.marshallAdvisory(message.getDataStructure());
            command.setContent(body.getBytes("UTF-8"));
        }
        return command;
    }
    
    @Override
    public String convertDestination(final ProtocolConverter converter, final Destination d) {
        if (d == null) {
            return null;
        }
        final ActiveMQDestination activeMQDestination = (ActiveMQDestination)d;
        final String physicalName = activeMQDestination.getPhysicalName();
        final String rc = converter.getCreatedTempDestinationName(activeMQDestination);
        if (rc != null) {
            return rc;
        }
        final StringBuffer buffer = new StringBuffer();
        if (activeMQDestination.isQueue()) {
            if (activeMQDestination.isTemporary()) {
                buffer.append("/remote-temp-queue/");
            }
            else {
                buffer.append("/queue/");
            }
        }
        else if (activeMQDestination.isTemporary()) {
            buffer.append("/remote-temp-topic/");
        }
        else {
            buffer.append("/topic/");
        }
        buffer.append(physicalName);
        return buffer.toString();
    }
    
    @Override
    public ActiveMQDestination convertDestination(final ProtocolConverter converter, final String name) throws ProtocolException {
        if (name == null) {
            return null;
        }
        if (name.startsWith("/queue/")) {
            final String qName = name.substring("/queue/".length(), name.length());
            return ActiveMQDestination.createDestination(qName, (byte)1);
        }
        if (name.startsWith("/topic/")) {
            final String tName = name.substring("/topic/".length(), name.length());
            return ActiveMQDestination.createDestination(tName, (byte)2);
        }
        if (name.startsWith("/remote-temp-queue/")) {
            final String tName = name.substring("/remote-temp-queue/".length(), name.length());
            return ActiveMQDestination.createDestination(tName, (byte)5);
        }
        if (name.startsWith("/remote-temp-topic/")) {
            final String tName = name.substring("/remote-temp-topic/".length(), name.length());
            return ActiveMQDestination.createDestination(tName, (byte)6);
        }
        if (name.startsWith("/temp-queue/")) {
            return converter.createTempQueue(name);
        }
        if (name.startsWith("/temp-topic/")) {
            return converter.createTempTopic(name);
        }
        throw new ProtocolException("Illegal destination name: [" + name + "] -- ActiveMQ STOMP destinations " + "must begin with one of: /queue/ /topic/ /temp-queue/ /temp-topic/");
    }
    
    protected String marshallAdvisory(final DataStructure ds) {
        final XStream xstream = new XStream((HierarchicalStreamDriver)new JsonHierarchicalStreamDriver());
        xstream.setMode(1001);
        xstream.aliasPackage("", "org.apache.activemq.command");
        return xstream.toXML((Object)ds);
    }
}
