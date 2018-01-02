// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.security;

import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.Message;
import org.apache.activemq.broker.ProducerBrokerExchange;
import org.apache.activemq.command.ProducerInfo;
import org.apache.activemq.broker.region.Subscription;
import org.apache.activemq.command.ConsumerInfo;
import java.util.Set;
import org.apache.activemq.broker.region.Destination;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.DestinationInfo;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.BrokerFilter;

public class AuthorizationBroker extends BrokerFilter implements SecurityAdminMBean
{
    private final AuthorizationMap authorizationMap;
    
    public AuthorizationBroker(final Broker next, final AuthorizationMap authorizationMap) {
        super(next);
        this.authorizationMap = authorizationMap;
    }
    
    @Override
    public void addDestinationInfo(final ConnectionContext context, final DestinationInfo info) throws Exception {
        this.addDestination(context, info.getDestination(), true);
        super.addDestinationInfo(context, info);
    }
    
    @Override
    public Destination addDestination(final ConnectionContext context, final ActiveMQDestination destination, final boolean create) throws Exception {
        final SecurityContext securityContext = context.getSecurityContext();
        if (securityContext == null) {
            throw new SecurityException("User is not authenticated.");
        }
        final Destination existing = this.getDestinationMap().get(destination);
        if (existing != null) {
            return super.addDestination(context, destination, create);
        }
        if (!securityContext.isBrokerContext()) {
            Set<?> allowedACLs = null;
            if (!destination.isTemporary()) {
                allowedACLs = this.authorizationMap.getAdminACLs(destination);
            }
            else {
                allowedACLs = this.authorizationMap.getTempDestinationAdminACLs();
            }
            if (allowedACLs != null && !securityContext.isInOneOf(allowedACLs)) {
                throw new SecurityException("User " + securityContext.getUserName() + " is not authorized to create: " + destination);
            }
        }
        return super.addDestination(context, destination, create);
    }
    
    @Override
    public void removeDestination(final ConnectionContext context, final ActiveMQDestination destination, final long timeout) throws Exception {
        final SecurityContext securityContext = context.getSecurityContext();
        if (securityContext == null) {
            throw new SecurityException("User is not authenticated.");
        }
        Set<?> allowedACLs = null;
        if (!destination.isTemporary()) {
            allowedACLs = this.authorizationMap.getAdminACLs(destination);
        }
        else {
            allowedACLs = this.authorizationMap.getTempDestinationAdminACLs();
        }
        if (!securityContext.isBrokerContext() && allowedACLs != null && !securityContext.isInOneOf(allowedACLs)) {
            throw new SecurityException("User " + securityContext.getUserName() + " is not authorized to remove: " + destination);
        }
        super.removeDestination(context, destination, timeout);
    }
    
    @Override
    public Subscription addConsumer(final ConnectionContext context, final ConsumerInfo info) throws Exception {
        final SecurityContext subject = context.getSecurityContext();
        if (subject == null) {
            throw new SecurityException("User is not authenticated.");
        }
        Set<?> allowedACLs = null;
        if (!info.getDestination().isTemporary()) {
            allowedACLs = this.authorizationMap.getReadACLs(info.getDestination());
        }
        else {
            allowedACLs = this.authorizationMap.getTempDestinationReadACLs();
        }
        if (!subject.isBrokerContext() && allowedACLs != null && !subject.isInOneOf(allowedACLs)) {
            throw new SecurityException("User " + subject.getUserName() + " is not authorized to read from: " + info.getDestination());
        }
        subject.getAuthorizedReadDests().put(info.getDestination(), info.getDestination());
        return super.addConsumer(context, info);
    }
    
    @Override
    public void addProducer(final ConnectionContext context, final ProducerInfo info) throws Exception {
        final SecurityContext subject = context.getSecurityContext();
        if (subject == null) {
            throw new SecurityException("User is not authenticated.");
        }
        if (!subject.isBrokerContext() && info.getDestination() != null) {
            Set<?> allowedACLs = null;
            if (!info.getDestination().isTemporary()) {
                allowedACLs = this.authorizationMap.getWriteACLs(info.getDestination());
            }
            else {
                allowedACLs = this.authorizationMap.getTempDestinationWriteACLs();
            }
            if (allowedACLs != null && !subject.isInOneOf(allowedACLs)) {
                throw new SecurityException("User " + subject.getUserName() + " is not authorized to write to: " + info.getDestination());
            }
            subject.getAuthorizedWriteDests().put(info.getDestination(), info.getDestination());
        }
        super.addProducer(context, info);
    }
    
    @Override
    public void send(final ProducerBrokerExchange producerExchange, final Message messageSend) throws Exception {
        final SecurityContext subject = producerExchange.getConnectionContext().getSecurityContext();
        if (subject == null) {
            throw new SecurityException("User is not authenticated.");
        }
        if (!subject.isBrokerContext() && !subject.getAuthorizedWriteDests().contains(messageSend.getDestination())) {
            Set<?> allowedACLs = null;
            if (!messageSend.getDestination().isTemporary()) {
                allowedACLs = this.authorizationMap.getWriteACLs(messageSend.getDestination());
            }
            else {
                allowedACLs = this.authorizationMap.getTempDestinationWriteACLs();
            }
            if (allowedACLs != null && !subject.isInOneOf(allowedACLs)) {
                throw new SecurityException("User " + subject.getUserName() + " is not authorized to write to: " + messageSend.getDestination());
            }
            subject.getAuthorizedWriteDests().put(messageSend.getDestination(), messageSend.getDestination());
        }
        super.send(producerExchange, messageSend);
    }
    
    @Override
    public void addQueueRole(final String queue, final String operation, final String role) {
        this.addDestinationRole(new ActiveMQQueue(queue), operation, role);
    }
    
    @Override
    public void addTopicRole(final String topic, final String operation, final String role) {
        this.addDestinationRole(new ActiveMQTopic(topic), operation, role);
    }
    
    @Override
    public void removeQueueRole(final String queue, final String operation, final String role) {
        this.removeDestinationRole(new ActiveMQQueue(queue), operation, role);
    }
    
    @Override
    public void removeTopicRole(final String topic, final String operation, final String role) {
        this.removeDestinationRole(new ActiveMQTopic(topic), operation, role);
    }
    
    public void addDestinationRole(final javax.jms.Destination destination, final String operation, final String role) {
    }
    
    public void removeDestinationRole(final javax.jms.Destination destination, final String operation, final String role) {
    }
    
    @Override
    public void addRole(final String role) {
    }
    
    @Override
    public void addUserRole(final String user, final String role) {
    }
    
    @Override
    public void removeRole(final String role) {
    }
    
    @Override
    public void removeUserRole(final String user, final String role) {
    }
}
