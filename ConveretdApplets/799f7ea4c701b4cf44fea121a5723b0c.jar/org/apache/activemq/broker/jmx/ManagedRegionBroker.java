// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.jmx;

import org.slf4j.LoggerFactory;
import org.apache.activemq.broker.region.policy.AbortSlowConsumerStrategy;
import javax.management.MalformedObjectNameException;
import org.apache.activemq.store.TopicMessageStore;
import org.apache.activemq.store.PersistenceAdapter;
import org.apache.activemq.command.MessageId;
import org.apache.activemq.store.MessageRecoveryListener;
import java.util.ArrayList;
import org.apache.activemq.broker.region.DestinationFactoryImpl;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.TabularDataSupport;
import javax.management.openmbean.TabularType;
import org.apache.activemq.command.ActiveMQMessage;
import javax.management.openmbean.TabularData;
import javax.management.openmbean.OpenDataException;
import org.apache.activemq.command.Message;
import java.util.List;
import javax.management.openmbean.CompositeData;
import org.apache.activemq.broker.region.TopicRegion;
import java.util.HashMap;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.command.ConsumerInfo;
import java.util.Hashtable;
import org.apache.activemq.util.JMXSupport;
import org.apache.activemq.broker.region.TopicSubscription;
import org.apache.activemq.command.SubscriptionInfo;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.broker.region.Topic;
import org.apache.activemq.broker.region.Queue;
import org.apache.activemq.broker.region.Destination;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.broker.region.Region;
import java.util.Iterator;
import javax.management.InstanceNotFoundException;
import org.apache.activemq.util.ServiceStopper;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import org.apache.activemq.thread.Scheduler;
import org.apache.activemq.broker.region.DestinationInterceptor;
import org.apache.activemq.broker.region.DestinationFactory;
import org.apache.activemq.usage.SystemUsage;
import org.apache.activemq.thread.TaskRunnerFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.Broker;
import java.util.Set;
import org.apache.activemq.broker.region.Subscription;
import org.apache.activemq.util.SubscriptionKey;
import java.util.Map;
import javax.management.ObjectName;
import org.slf4j.Logger;
import org.apache.activemq.broker.region.RegionBroker;

public class ManagedRegionBroker extends RegionBroker
{
    private static final Logger LOG;
    private final ManagementContext managementContext;
    private final ObjectName brokerObjectName;
    private final Map<ObjectName, DestinationView> topics;
    private final Map<ObjectName, DestinationView> queues;
    private final Map<ObjectName, DestinationView> temporaryQueues;
    private final Map<ObjectName, DestinationView> temporaryTopics;
    private final Map<ObjectName, SubscriptionView> queueSubscribers;
    private final Map<ObjectName, SubscriptionView> topicSubscribers;
    private final Map<ObjectName, SubscriptionView> durableTopicSubscribers;
    private final Map<ObjectName, SubscriptionView> inactiveDurableTopicSubscribers;
    private final Map<ObjectName, SubscriptionView> temporaryQueueSubscribers;
    private final Map<ObjectName, SubscriptionView> temporaryTopicSubscribers;
    private final Map<SubscriptionKey, ObjectName> subscriptionKeys;
    private final Map<Subscription, ObjectName> subscriptionMap;
    private final Set<ObjectName> registeredMBeans;
    private Broker contextBroker;
    
    public ManagedRegionBroker(final BrokerService brokerService, final ManagementContext context, final ObjectName brokerObjectName, final TaskRunnerFactory taskRunnerFactory, final SystemUsage memoryManager, final DestinationFactory destinationFactory, final DestinationInterceptor destinationInterceptor, final Scheduler scheduler, final ThreadPoolExecutor executor) throws IOException {
        super(brokerService, taskRunnerFactory, memoryManager, destinationFactory, destinationInterceptor, scheduler, executor);
        this.topics = new ConcurrentHashMap<ObjectName, DestinationView>();
        this.queues = new ConcurrentHashMap<ObjectName, DestinationView>();
        this.temporaryQueues = new ConcurrentHashMap<ObjectName, DestinationView>();
        this.temporaryTopics = new ConcurrentHashMap<ObjectName, DestinationView>();
        this.queueSubscribers = new ConcurrentHashMap<ObjectName, SubscriptionView>();
        this.topicSubscribers = new ConcurrentHashMap<ObjectName, SubscriptionView>();
        this.durableTopicSubscribers = new ConcurrentHashMap<ObjectName, SubscriptionView>();
        this.inactiveDurableTopicSubscribers = new ConcurrentHashMap<ObjectName, SubscriptionView>();
        this.temporaryQueueSubscribers = new ConcurrentHashMap<ObjectName, SubscriptionView>();
        this.temporaryTopicSubscribers = new ConcurrentHashMap<ObjectName, SubscriptionView>();
        this.subscriptionKeys = new ConcurrentHashMap<SubscriptionKey, ObjectName>();
        this.subscriptionMap = new ConcurrentHashMap<Subscription, ObjectName>();
        this.registeredMBeans = new CopyOnWriteArraySet<ObjectName>();
        this.managementContext = context;
        this.brokerObjectName = brokerObjectName;
    }
    
    @Override
    public void start() throws Exception {
        super.start();
        this.buildExistingSubscriptions();
    }
    
    @Override
    protected void doStop(final ServiceStopper stopper) {
        super.doStop(stopper);
        for (final ObjectName name : this.registeredMBeans) {
            try {
                this.managementContext.unregisterMBean(name);
            }
            catch (InstanceNotFoundException e2) {
                ManagedRegionBroker.LOG.warn("The MBean: " + name + " is no longer registered with JMX");
            }
            catch (Exception e) {
                stopper.onException(this, e);
            }
        }
        this.registeredMBeans.clear();
    }
    
    @Override
    protected Region createQueueRegion(final SystemUsage memoryManager, final TaskRunnerFactory taskRunnerFactory, final DestinationFactory destinationFactory) {
        return new ManagedQueueRegion(this, this.destinationStatistics, memoryManager, taskRunnerFactory, destinationFactory);
    }
    
    @Override
    protected Region createTempQueueRegion(final SystemUsage memoryManager, final TaskRunnerFactory taskRunnerFactory, final DestinationFactory destinationFactory) {
        return new ManagedTempQueueRegion(this, this.brokerService, this.destinationStatistics, memoryManager, taskRunnerFactory, destinationFactory);
    }
    
    @Override
    protected Region createTempTopicRegion(final SystemUsage memoryManager, final TaskRunnerFactory taskRunnerFactory, final DestinationFactory destinationFactory) {
        return new ManagedTempTopicRegion(this, this.destinationStatistics, memoryManager, taskRunnerFactory, destinationFactory);
    }
    
    @Override
    protected Region createTopicRegion(final SystemUsage memoryManager, final TaskRunnerFactory taskRunnerFactory, final DestinationFactory destinationFactory) {
        return new ManagedTopicRegion(this, this.destinationStatistics, memoryManager, taskRunnerFactory, destinationFactory);
    }
    
    public void register(final ActiveMQDestination destName, final Destination destination) {
        try {
            final ObjectName objectName = this.createObjectName(destName);
            DestinationView view;
            if (destination instanceof Queue) {
                view = new QueueView(this, (Queue)destination);
            }
            else if (destination instanceof Topic) {
                view = new TopicView(this, (Topic)destination);
            }
            else {
                view = null;
                ManagedRegionBroker.LOG.warn("JMX View is not supported for custom destination: " + destination);
            }
            if (view != null) {
                this.registerDestination(objectName, destName, view);
            }
        }
        catch (Exception e) {
            ManagedRegionBroker.LOG.error("Failed to register destination " + destName, e);
        }
    }
    
    public void unregister(final ActiveMQDestination destName) {
        try {
            final ObjectName objectName = this.createObjectName(destName);
            this.unregisterDestination(objectName);
        }
        catch (Exception e) {
            ManagedRegionBroker.LOG.error("Failed to unregister " + destName, e);
        }
    }
    
    public ObjectName registerSubscription(final ConnectionContext context, final Subscription sub) {
        final String connectionClientId = context.getClientId();
        final ObjectName brokerJmxObjectName = this.brokerObjectName;
        final String objectNameStr = getSubscriptionObjectName(sub, connectionClientId, brokerJmxObjectName);
        final SubscriptionKey key = new SubscriptionKey(context.getClientId(), sub.getConsumerInfo().getSubscriptionName());
        try {
            final ObjectName objectName = new ObjectName(objectNameStr);
            if (sub.getConsumerInfo().getConsumerId().getConnectionId().equals("OFFLINE")) {
                final SubscriptionInfo info = new SubscriptionInfo();
                info.setClientId(context.getClientId());
                info.setSubscriptionName(sub.getConsumerInfo().getSubscriptionName());
                info.setDestination(sub.getConsumerInfo().getDestination());
                this.addInactiveSubscription(key, info);
            }
            else {
                SubscriptionView view;
                if (sub.getConsumerInfo().isDurable()) {
                    view = new DurableSubscriptionView(this, context.getClientId(), sub);
                }
                else if (sub instanceof TopicSubscription) {
                    view = new TopicSubscriptionView(context.getClientId(), (TopicSubscription)sub);
                }
                else {
                    view = new SubscriptionView(context.getClientId(), sub);
                }
                this.registerSubscription(objectName, sub.getConsumerInfo(), key, view);
            }
            this.subscriptionMap.put(sub, objectName);
            return objectName;
        }
        catch (Exception e) {
            ManagedRegionBroker.LOG.error("Failed to register subscription " + sub, e);
            return null;
        }
    }
    
    public static String getSubscriptionObjectName(final Subscription sub, final String connectionClientId, final ObjectName brokerJmxObjectName) {
        final Hashtable map = brokerJmxObjectName.getKeyPropertyList();
        final String brokerDomain = brokerJmxObjectName.getDomain();
        String objectNameStr = brokerDomain + ":" + "BrokerName=" + map.get("BrokerName") + ",Type=Subscription,";
        final String destinationType = "destinationType=" + sub.getConsumerInfo().getDestination().getDestinationTypeAsString();
        final String destinationName = "destinationName=" + JMXSupport.encodeObjectNamePart(sub.getConsumerInfo().getDestination().getPhysicalName());
        final String clientId = "clientId=" + JMXSupport.encodeObjectNamePart(connectionClientId);
        String persistentMode = "persistentMode=";
        String consumerId = "";
        if (sub.getConsumerInfo().isDurable()) {
            persistentMode = persistentMode + "Durable,subscriptionID=" + JMXSupport.encodeObjectNamePart(sub.getConsumerInfo().getSubscriptionName());
        }
        else {
            persistentMode += "Non-Durable";
            if (sub.getConsumerInfo() != null && sub.getConsumerInfo().getConsumerId() != null) {
                consumerId = ",consumerId=" + JMXSupport.encodeObjectNamePart(sub.getConsumerInfo().getConsumerId().toString());
            }
        }
        objectNameStr = objectNameStr + persistentMode + ",";
        objectNameStr = objectNameStr + destinationType + ",";
        objectNameStr = objectNameStr + destinationName + ",";
        objectNameStr += clientId;
        objectNameStr += consumerId;
        return objectNameStr;
    }
    
    @Override
    public Subscription addConsumer(final ConnectionContext context, final ConsumerInfo info) throws Exception {
        final Subscription sub = super.addConsumer(context, info);
        final SubscriptionKey subscriptionKey = new SubscriptionKey(sub.getContext().getClientId(), sub.getConsumerInfo().getSubscriptionName());
        final ObjectName inactiveName = this.subscriptionKeys.get(subscriptionKey);
        if (inactiveName != null) {
            this.registerSubscription(context, sub);
        }
        return sub;
    }
    
    @Override
    public void removeConsumer(final ConnectionContext context, final ConsumerInfo info) throws Exception {
        for (final Subscription sub : this.subscriptionMap.keySet()) {
            if (sub.getConsumerInfo().equals(info)) {
                this.unregisterSubscription(this.subscriptionMap.get(sub), true);
            }
        }
        super.removeConsumer(context, info);
    }
    
    public void unregisterSubscription(final Subscription sub) {
        final ObjectName name = this.subscriptionMap.remove(sub);
        if (name != null) {
            try {
                final SubscriptionKey subscriptionKey = new SubscriptionKey(sub.getContext().getClientId(), sub.getConsumerInfo().getSubscriptionName());
                final ObjectName inactiveName = this.subscriptionKeys.get(subscriptionKey);
                if (inactiveName != null) {
                    this.inactiveDurableTopicSubscribers.remove(inactiveName);
                    this.managementContext.unregisterMBean(inactiveName);
                }
            }
            catch (Exception e) {
                ManagedRegionBroker.LOG.error("Failed to unregister subscription " + sub, e);
            }
        }
    }
    
    protected void registerDestination(final ObjectName key, final ActiveMQDestination dest, final DestinationView view) throws Exception {
        if (dest.isQueue()) {
            if (dest.isTemporary()) {
                this.temporaryQueues.put(key, view);
            }
            else {
                this.queues.put(key, view);
            }
        }
        else if (dest.isTemporary()) {
            this.temporaryTopics.put(key, view);
        }
        else {
            this.topics.put(key, view);
        }
        try {
            AnnotatedMBean.registerMBean(this.managementContext, view, key);
            this.registeredMBeans.add(key);
        }
        catch (Throwable e) {
            ManagedRegionBroker.LOG.warn("Failed to register MBean: " + key);
            ManagedRegionBroker.LOG.debug("Failure reason: " + e, e);
        }
    }
    
    protected void unregisterDestination(ObjectName key) throws Exception {
        final DestinationView view = null;
        this.removeAndRemember(this.topics, key, view);
        this.removeAndRemember(this.queues, key, view);
        this.removeAndRemember(this.temporaryQueues, key, view);
        this.removeAndRemember(this.temporaryTopics, key, view);
        if (this.registeredMBeans.remove(key)) {
            try {
                this.managementContext.unregisterMBean(key);
            }
            catch (Throwable e) {
                ManagedRegionBroker.LOG.warn("Failed to unregister MBean: " + key);
                ManagedRegionBroker.LOG.debug("Failure reason: " + e, e);
            }
        }
        if (view != null) {
            key = view.getSlowConsumerStrategy();
            if (key != null && this.registeredMBeans.remove(key)) {
                try {
                    this.managementContext.unregisterMBean(key);
                }
                catch (Throwable e) {
                    ManagedRegionBroker.LOG.warn("Failed to unregister slow consumer strategy MBean: " + key);
                    ManagedRegionBroker.LOG.debug("Failure reason: " + e, e);
                }
            }
        }
    }
    
    private void removeAndRemember(final Map<ObjectName, DestinationView> map, final ObjectName key, DestinationView view) {
        final DestinationView candidate = map.remove(key);
        if (candidate != null && view == null) {
            view = candidate;
        }
    }
    
    protected void registerSubscription(final ObjectName key, final ConsumerInfo info, final SubscriptionKey subscriptionKey, final SubscriptionView view) throws Exception {
        final ActiveMQDestination dest = info.getDestination();
        if (dest.isQueue()) {
            if (dest.isTemporary()) {
                this.temporaryQueueSubscribers.put(key, view);
            }
            else {
                this.queueSubscribers.put(key, view);
            }
        }
        else if (dest.isTemporary()) {
            this.temporaryTopicSubscribers.put(key, view);
        }
        else if (info.isDurable()) {
            this.durableTopicSubscribers.put(key, view);
            try {
                final ObjectName inactiveName = this.subscriptionKeys.get(subscriptionKey);
                if (inactiveName != null) {
                    this.inactiveDurableTopicSubscribers.remove(inactiveName);
                    this.registeredMBeans.remove(inactiveName);
                    this.managementContext.unregisterMBean(inactiveName);
                }
            }
            catch (Throwable e) {
                ManagedRegionBroker.LOG.error("Unable to unregister inactive durable subscriber: " + subscriptionKey, e);
            }
        }
        else {
            this.topicSubscribers.put(key, view);
        }
        try {
            AnnotatedMBean.registerMBean(this.managementContext, view, key);
            this.registeredMBeans.add(key);
        }
        catch (Throwable e) {
            ManagedRegionBroker.LOG.warn("Failed to register MBean: " + key);
            ManagedRegionBroker.LOG.debug("Failure reason: " + e, e);
        }
    }
    
    protected void unregisterSubscription(final ObjectName key, final boolean addToInactive) throws Exception {
        this.queueSubscribers.remove(key);
        this.topicSubscribers.remove(key);
        this.temporaryQueueSubscribers.remove(key);
        this.temporaryTopicSubscribers.remove(key);
        if (this.registeredMBeans.remove(key)) {
            try {
                this.managementContext.unregisterMBean(key);
            }
            catch (Throwable e) {
                ManagedRegionBroker.LOG.warn("Failed to unregister MBean: " + key);
                ManagedRegionBroker.LOG.debug("Failure reason: " + e, e);
            }
        }
        final DurableSubscriptionView view = this.durableTopicSubscribers.remove(key);
        if (view != null) {
            final SubscriptionKey subscriptionKey = new SubscriptionKey(view.getClientId(), view.getSubscriptionName());
            if (addToInactive) {
                final SubscriptionInfo info = new SubscriptionInfo();
                info.setClientId(subscriptionKey.getClientId());
                info.setSubscriptionName(subscriptionKey.getSubscriptionName());
                info.setDestination(new ActiveMQTopic(view.getDestinationName()));
                this.addInactiveSubscription(subscriptionKey, info);
            }
        }
    }
    
    protected void buildExistingSubscriptions() throws Exception {
        final Map<SubscriptionKey, SubscriptionInfo> subscriptions = new HashMap<SubscriptionKey, SubscriptionInfo>();
        final Set destinations = this.destinationFactory.getDestinations();
        if (destinations != null) {
            for (final ActiveMQDestination dest : destinations) {
                if (dest.isTopic()) {
                    final SubscriptionInfo[] infos = this.destinationFactory.getAllDurableSubscriptions((ActiveMQTopic)dest);
                    if (infos == null) {
                        continue;
                    }
                    for (int i = 0; i < infos.length; ++i) {
                        final SubscriptionInfo info = infos[i];
                        final SubscriptionKey key = new SubscriptionKey(info);
                        if (!this.alreadyKnown(key)) {
                            ManagedRegionBroker.LOG.debug("Restoring durable subscription mbean: " + info);
                            subscriptions.put(key, info);
                        }
                    }
                }
            }
        }
        for (final Map.Entry entry : subscriptions.entrySet()) {
            final SubscriptionKey key2 = entry.getKey();
            final SubscriptionInfo info2 = entry.getValue();
            this.addInactiveSubscription(key2, info2);
        }
    }
    
    private boolean alreadyKnown(final SubscriptionKey key) {
        boolean known = false;
        known = ((TopicRegion)this.getTopicRegion()).durableSubscriptionExists(key);
        if (ManagedRegionBroker.LOG.isTraceEnabled()) {
            ManagedRegionBroker.LOG.trace("Sub with key: " + key + ", " + (known ? "" : "not") + " already registered");
        }
        return known;
    }
    
    protected void addInactiveSubscription(final SubscriptionKey key, final SubscriptionInfo info) {
        final Hashtable map = this.brokerObjectName.getKeyPropertyList();
        try {
            final ObjectName objectName = new ObjectName(this.brokerObjectName.getDomain() + ":" + "BrokerName=" + map.get("BrokerName") + "," + "Type=Subscription," + "active=false," + "name=" + JMXSupport.encodeObjectNamePart(key.toString()) + "");
            final SubscriptionView view = new InactiveDurableSubscriptionView(this, key.getClientId(), info);
            try {
                AnnotatedMBean.registerMBean(this.managementContext, view, objectName);
                this.registeredMBeans.add(objectName);
            }
            catch (Throwable e) {
                ManagedRegionBroker.LOG.warn("Failed to register MBean: " + key);
                ManagedRegionBroker.LOG.debug("Failure reason: " + e, e);
            }
            this.inactiveDurableTopicSubscribers.put(objectName, view);
            this.subscriptionKeys.put(key, objectName);
        }
        catch (Exception e2) {
            ManagedRegionBroker.LOG.error("Failed to register subscription " + info, e2);
        }
    }
    
    public CompositeData[] browse(final SubscriptionView view) throws OpenDataException {
        final List<Message> messages = this.getSubscriberMessages(view);
        final CompositeData[] c = new CompositeData[messages.size()];
        for (int i = 0; i < c.length; ++i) {
            try {
                c[i] = OpenTypeSupport.convert(messages.get(i));
            }
            catch (Throwable e) {
                ManagedRegionBroker.LOG.error("failed to browse : " + view, e);
            }
        }
        return c;
    }
    
    public TabularData browseAsTable(final SubscriptionView view) throws OpenDataException {
        final OpenTypeSupport.OpenTypeFactory factory = OpenTypeSupport.getFactory(ActiveMQMessage.class);
        final List<Message> messages = this.getSubscriberMessages(view);
        final CompositeType ct = factory.getCompositeType();
        final TabularType tt = new TabularType("MessageList", "MessageList", ct, new String[] { "JMSMessageID" });
        final TabularDataSupport rc = new TabularDataSupport(tt);
        for (int i = 0; i < messages.size(); ++i) {
            rc.put(new CompositeDataSupport(ct, factory.getFields(messages.get(i))));
        }
        return rc;
    }
    
    protected List<Message> getSubscriberMessages(final SubscriptionView view) {
        if (!(this.destinationFactory instanceof DestinationFactoryImpl)) {
            throw new RuntimeException("unsupported by " + this.destinationFactory);
        }
        final PersistenceAdapter adapter = ((DestinationFactoryImpl)this.destinationFactory).getPersistenceAdapter();
        final List<Message> result = new ArrayList<Message>();
        try {
            final ActiveMQTopic topic = new ActiveMQTopic(view.getDestinationName());
            final TopicMessageStore store = adapter.createTopicMessageStore(topic);
            store.recover(new MessageRecoveryListener() {
                @Override
                public boolean recoverMessage(final Message message) throws Exception {
                    result.add(message);
                    return true;
                }
                
                @Override
                public boolean recoverMessageReference(final MessageId messageReference) throws Exception {
                    throw new RuntimeException("Should not be called.");
                }
                
                @Override
                public boolean hasSpace() {
                    return true;
                }
                
                @Override
                public boolean isDuplicate(final MessageId id) {
                    return false;
                }
            });
        }
        catch (Throwable e) {
            ManagedRegionBroker.LOG.error("Failed to browse messages for Subscription " + view, e);
        }
        return result;
    }
    
    protected ObjectName[] getTopics() {
        final Set<ObjectName> set = this.topics.keySet();
        return set.toArray(new ObjectName[set.size()]);
    }
    
    protected ObjectName[] getQueues() {
        final Set<ObjectName> set = this.queues.keySet();
        return set.toArray(new ObjectName[set.size()]);
    }
    
    protected ObjectName[] getTemporaryTopics() {
        final Set<ObjectName> set = this.temporaryTopics.keySet();
        return set.toArray(new ObjectName[set.size()]);
    }
    
    protected ObjectName[] getTemporaryQueues() {
        final Set<ObjectName> set = this.temporaryQueues.keySet();
        return set.toArray(new ObjectName[set.size()]);
    }
    
    protected ObjectName[] getTopicSubscribers() {
        final Set<ObjectName> set = this.topicSubscribers.keySet();
        return set.toArray(new ObjectName[set.size()]);
    }
    
    protected ObjectName[] getDurableTopicSubscribers() {
        final Set<ObjectName> set = this.durableTopicSubscribers.keySet();
        return set.toArray(new ObjectName[set.size()]);
    }
    
    protected ObjectName[] getQueueSubscribers() {
        final Set<ObjectName> set = this.queueSubscribers.keySet();
        return set.toArray(new ObjectName[set.size()]);
    }
    
    protected ObjectName[] getTemporaryTopicSubscribers() {
        final Set<ObjectName> set = this.temporaryTopicSubscribers.keySet();
        return set.toArray(new ObjectName[set.size()]);
    }
    
    protected ObjectName[] getTemporaryQueueSubscribers() {
        final Set<ObjectName> set = this.temporaryQueueSubscribers.keySet();
        return set.toArray(new ObjectName[set.size()]);
    }
    
    protected ObjectName[] getInactiveDurableTopicSubscribers() {
        final Set<ObjectName> set = this.inactiveDurableTopicSubscribers.keySet();
        return set.toArray(new ObjectName[set.size()]);
    }
    
    public Broker getContextBroker() {
        return this.contextBroker;
    }
    
    public void setContextBroker(final Broker contextBroker) {
        this.contextBroker = contextBroker;
    }
    
    protected ObjectName createObjectName(final ActiveMQDestination destName) throws MalformedObjectNameException {
        final Hashtable map = this.brokerObjectName.getKeyPropertyList();
        final ObjectName objectName = new ObjectName(this.brokerObjectName.getDomain() + ":" + "BrokerName=" + map.get("BrokerName") + "," + "Type=" + JMXSupport.encodeObjectNamePart(destName.getDestinationTypeAsString()) + "," + "Destination=" + JMXSupport.encodeObjectNamePart(destName.getPhysicalName()));
        return objectName;
    }
    
    public ObjectName registerSlowConsumerStrategy(final AbortSlowConsumerStrategy strategy) throws MalformedObjectNameException {
        ObjectName objectName = null;
        try {
            objectName = this.createObjectName(strategy);
            if (!this.registeredMBeans.contains(objectName)) {
                final AbortSlowConsumerStrategyView view = new AbortSlowConsumerStrategyView(this, strategy);
                AnnotatedMBean.registerMBean(this.managementContext, view, objectName);
                this.registeredMBeans.add(objectName);
            }
        }
        catch (Exception e) {
            ManagedRegionBroker.LOG.warn("Failed to register MBean: " + strategy);
            ManagedRegionBroker.LOG.debug("Failure reason: " + e, e);
        }
        return objectName;
    }
    
    private ObjectName createObjectName(final AbortSlowConsumerStrategy strategy) throws MalformedObjectNameException {
        final Hashtable map = this.brokerObjectName.getKeyPropertyList();
        final ObjectName objectName = new ObjectName(this.brokerObjectName.getDomain() + ":" + "BrokerName=" + map.get("BrokerName") + "," + "Type=SlowConsumerStrategy," + "InstanceName=" + JMXSupport.encodeObjectNamePart(strategy.getName()));
        return objectName;
    }
    
    public ObjectName getSubscriberObjectName(final Subscription key) {
        return this.subscriptionMap.get(key);
    }
    
    public Subscription getSubscriber(final ObjectName key) {
        Subscription sub = null;
        for (final Map.Entry<Subscription, ObjectName> entry : this.subscriptionMap.entrySet()) {
            if (entry.getValue().equals(key)) {
                sub = entry.getKey();
                break;
            }
        }
        return sub;
    }
    
    static {
        LOG = LoggerFactory.getLogger(ManagedRegionBroker.class);
    }
}
