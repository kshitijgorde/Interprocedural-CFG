// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.filter;

import org.apache.activemq.command.TransactionId;
import org.apache.activemq.command.ActiveMQDestination;
import java.util.HashMap;
import org.apache.activemq.command.Message;
import javax.jms.JMSException;
import java.io.IOException;
import org.apache.activemq.util.JMSExceptionSupport;
import java.util.Map;

public class PropertyExpression implements Expression
{
    private static final Map<String, SubExpression> JMS_PROPERTY_EXPRESSIONS;
    private final String name;
    private final SubExpression jmsPropertyExpression;
    
    public PropertyExpression(final String name) {
        this.name = name;
        this.jmsPropertyExpression = PropertyExpression.JMS_PROPERTY_EXPRESSIONS.get(name);
    }
    
    @Override
    public Object evaluate(final MessageEvaluationContext message) throws JMSException {
        try {
            if (message.isDropped()) {
                return null;
            }
            if (this.jmsPropertyExpression != null) {
                return this.jmsPropertyExpression.evaluate(message.getMessage());
            }
            try {
                return message.getMessage().getProperty(this.name);
            }
            catch (IOException ioe) {
                throw JMSExceptionSupport.create("Could not get property: " + this.name + " reason: " + ioe.getMessage(), ioe);
            }
        }
        catch (IOException e) {
            throw JMSExceptionSupport.create(e);
        }
    }
    
    public Object evaluate(final Message message) throws JMSException {
        if (this.jmsPropertyExpression != null) {
            return this.jmsPropertyExpression.evaluate(message);
        }
        try {
            return message.getProperty(this.name);
        }
        catch (IOException ioe) {
            throw JMSExceptionSupport.create(ioe);
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
    
    @Override
    public boolean equals(final Object o) {
        return o != null && this.getClass().equals(o.getClass()) && this.name.equals(((PropertyExpression)o).name);
    }
    
    static {
        (JMS_PROPERTY_EXPRESSIONS = new HashMap<String, SubExpression>()).put("JMSDestination", new SubExpression() {
            @Override
            public Object evaluate(final Message message) {
                ActiveMQDestination dest = message.getOriginalDestination();
                if (dest == null) {
                    dest = message.getDestination();
                }
                if (dest == null) {
                    return null;
                }
                return dest.toString();
            }
        });
        PropertyExpression.JMS_PROPERTY_EXPRESSIONS.put("JMSReplyTo", new SubExpression() {
            @Override
            public Object evaluate(final Message message) {
                if (message.getReplyTo() == null) {
                    return null;
                }
                return message.getReplyTo().toString();
            }
        });
        PropertyExpression.JMS_PROPERTY_EXPRESSIONS.put("JMSType", new SubExpression() {
            @Override
            public Object evaluate(final Message message) {
                return message.getType();
            }
        });
        PropertyExpression.JMS_PROPERTY_EXPRESSIONS.put("JMSDeliveryMode", new SubExpression() {
            @Override
            public Object evaluate(final Message message) {
                return message.isPersistent() ? 2 : 1;
            }
        });
        PropertyExpression.JMS_PROPERTY_EXPRESSIONS.put("JMSPriority", new SubExpression() {
            @Override
            public Object evaluate(final Message message) {
                return message.getPriority();
            }
        });
        PropertyExpression.JMS_PROPERTY_EXPRESSIONS.put("JMSMessageID", new SubExpression() {
            @Override
            public Object evaluate(final Message message) {
                if (message.getMessageId() == null) {
                    return null;
                }
                return message.getMessageId().toString();
            }
        });
        PropertyExpression.JMS_PROPERTY_EXPRESSIONS.put("JMSTimestamp", new SubExpression() {
            @Override
            public Object evaluate(final Message message) {
                return message.getTimestamp();
            }
        });
        PropertyExpression.JMS_PROPERTY_EXPRESSIONS.put("JMSCorrelationID", new SubExpression() {
            @Override
            public Object evaluate(final Message message) {
                return message.getCorrelationId();
            }
        });
        PropertyExpression.JMS_PROPERTY_EXPRESSIONS.put("JMSExpiration", new SubExpression() {
            @Override
            public Object evaluate(final Message message) {
                return message.getExpiration();
            }
        });
        PropertyExpression.JMS_PROPERTY_EXPRESSIONS.put("JMSRedelivered", new SubExpression() {
            @Override
            public Object evaluate(final Message message) {
                return message.isRedelivered();
            }
        });
        PropertyExpression.JMS_PROPERTY_EXPRESSIONS.put("JMSXDeliveryCount", new SubExpression() {
            @Override
            public Object evaluate(final Message message) {
                return message.getRedeliveryCounter() + 1;
            }
        });
        PropertyExpression.JMS_PROPERTY_EXPRESSIONS.put("JMSXGroupID", new SubExpression() {
            @Override
            public Object evaluate(final Message message) {
                return message.getGroupID();
            }
        });
        PropertyExpression.JMS_PROPERTY_EXPRESSIONS.put("JMSXGroupSeq", new SubExpression() {
            @Override
            public Object evaluate(final Message message) {
                return new Integer(message.getGroupSequence());
            }
        });
        PropertyExpression.JMS_PROPERTY_EXPRESSIONS.put("JMSXProducerTXID", new SubExpression() {
            @Override
            public Object evaluate(final Message message) {
                TransactionId txId = message.getOriginalTransactionId();
                if (txId == null) {
                    txId = message.getTransactionId();
                }
                if (txId == null) {
                    return null;
                }
                return new Integer(txId.toString());
            }
        });
        PropertyExpression.JMS_PROPERTY_EXPRESSIONS.put("JMSActiveMQBrokerInTime", new SubExpression() {
            @Override
            public Object evaluate(final Message message) {
                return message.getBrokerInTime();
            }
        });
        PropertyExpression.JMS_PROPERTY_EXPRESSIONS.put("JMSActiveMQBrokerOutTime", new SubExpression() {
            @Override
            public Object evaluate(final Message message) {
                return message.getBrokerOutTime();
            }
        });
    }
    
    interface SubExpression
    {
        Object evaluate(final Message p0);
    }
}
