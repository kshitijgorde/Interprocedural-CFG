// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms.msg;

import javax.jms.Message;
import javax.jms.Session;
import javax.jms.JMSException;
import org.xmodel.compress.TabularCompressor;
import javax.jms.BytesMessage;
import org.xmodel.IModelObject;
import org.xmodel.ElementFactory;
import org.xmodel.log.Log;
import org.xmodel.IModelObjectFactory;

public class MessageBuilder
{
    private IModelObjectFactory factory;
    static final Log log;
    
    static {
        log = Log.getLog(MessageBuilder.class);
    }
    
    public MessageBuilder() {
        this.factory = new ElementFactory();
    }
    
    public IModelObject buildModel(final JmsMessage message) throws JMSException {
        try {
            final BytesMessage bmsg = (BytesMessage)message.getMessage();
            final int length = (int)bmsg.getBodyLength();
            final byte[] bytes = new byte[length];
            bmsg.readBytes(bytes);
            final TabularCompressor c = new TabularCompressor();
            c.setFactory(this.factory);
            final IModelObject e = c.decompress(bytes, 0);
            return e;
        }
        catch (Exception ex) {
            MessageBuilder.log.error(this, ex);
            throw new JMSException("Cannot create the Document");
        }
    }
    
    public JmsMessage buildMessage(final IModelObject root, final Session session) throws JMSException {
        try {
            final TabularCompressor c = new TabularCompressor();
            final byte[] bytes = c.compress(root);
            final BytesMessage bmsg = session.createBytesMessage();
            bmsg.writeBytes(bytes);
            return new JmsMessage(bmsg);
        }
        catch (Exception e) {
            MessageBuilder.log.error(this, e);
            throw new JMSException("Cannot create the ByteMessage");
        }
    }
    
    public void setFactory(final IModelObjectFactory factory) {
        this.factory = factory;
    }
}
