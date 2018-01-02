// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.store.amq.reader;

import org.apache.activemq.broker.region.MessageReference;
import org.apache.activemq.filter.MessageEvaluationContext;
import org.apache.activemq.filter.BooleanExpression;
import javax.jms.Message;
import java.util.Iterator;

class AMQIterator implements Iterator<Message>
{
    private AMQReader reader;
    private BooleanExpression expression;
    private MessageLocation currentLocation;
    private MessageLocation nextLocation;
    private boolean valid;
    
    AMQIterator(final AMQReader reader, final BooleanExpression expression) {
        this.valid = true;
        this.reader = reader;
        this.expression = expression;
    }
    
    @Override
    public boolean hasNext() {
        try {
            this.nextLocation = this.reader.getNextMessage(this.currentLocation);
            Message next = (this.nextLocation != null) ? this.nextLocation.getMessage() : null;
            if (this.expression == null) {
                return next != null;
            }
            while (next != null) {
                final MessageEvaluationContext context = new MessageEvaluationContext();
                context.setMessageReference((MessageReference)next);
                if (this.expression.matches(context)) {
                    return true;
                }
                this.nextLocation = this.reader.getNextMessage(this.currentLocation);
                next = ((this.nextLocation != null) ? this.nextLocation.getMessage() : null);
            }
            return this.valid = false;
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to get next message from reader ", e);
        }
    }
    
    @Override
    public Message next() {
        if (this.valid && (this.nextLocation != null || this.hasNext())) {
            this.currentLocation = this.nextLocation;
            return this.nextLocation.getMessage();
        }
        return null;
    }
    
    @Override
    public void remove() {
        throw new IllegalStateException("Not supported");
    }
}
