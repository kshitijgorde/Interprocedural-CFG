// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.filter;

import javax.jms.JMSException;

public interface BooleanExpression extends Expression
{
    boolean matches(final MessageEvaluationContext p0) throws JMSException;
}
