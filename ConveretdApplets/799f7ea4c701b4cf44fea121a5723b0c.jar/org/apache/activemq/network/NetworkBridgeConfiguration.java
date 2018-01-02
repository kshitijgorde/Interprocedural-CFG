// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.network;

import org.apache.activemq.command.ActiveMQDestination;
import java.util.List;

public class NetworkBridgeConfiguration
{
    private boolean conduitSubscriptions;
    private boolean dynamicOnly;
    private boolean dispatchAsync;
    private boolean decreaseNetworkConsumerPriority;
    private boolean duplex;
    private boolean bridgeTempDestinations;
    private int prefetchSize;
    private int networkTTL;
    private String brokerName;
    private String brokerURL;
    private String userName;
    private String password;
    private String destinationFilter;
    private String name;
    private List<ActiveMQDestination> excludedDestinations;
    private List<ActiveMQDestination> dynamicallyIncludedDestinations;
    private List<ActiveMQDestination> staticallyIncludedDestinations;
    private boolean suppressDuplicateQueueSubscriptions;
    private boolean suppressDuplicateTopicSubscriptions;
    
    public NetworkBridgeConfiguration() {
        this.conduitSubscriptions = true;
        this.dispatchAsync = true;
        this.bridgeTempDestinations = true;
        this.prefetchSize = 1000;
        this.networkTTL = 1;
        this.brokerName = "localhost";
        this.brokerURL = "";
        this.destinationFilter = ">";
        this.name = "NC";
        this.suppressDuplicateQueueSubscriptions = false;
        this.suppressDuplicateTopicSubscriptions = true;
    }
    
    public boolean isConduitSubscriptions() {
        return this.conduitSubscriptions;
    }
    
    public void setConduitSubscriptions(final boolean conduitSubscriptions) {
        this.conduitSubscriptions = conduitSubscriptions;
    }
    
    public boolean isDynamicOnly() {
        return this.dynamicOnly;
    }
    
    public void setDynamicOnly(final boolean dynamicOnly) {
        this.dynamicOnly = dynamicOnly;
    }
    
    public boolean isBridgeTempDestinations() {
        return this.bridgeTempDestinations;
    }
    
    public void setBridgeTempDestinations(final boolean bridgeTempDestinations) {
        this.bridgeTempDestinations = bridgeTempDestinations;
    }
    
    public boolean isDecreaseNetworkConsumerPriority() {
        return this.decreaseNetworkConsumerPriority;
    }
    
    public void setDecreaseNetworkConsumerPriority(final boolean decreaseNetworkConsumerPriority) {
        this.decreaseNetworkConsumerPriority = decreaseNetworkConsumerPriority;
    }
    
    public boolean isDispatchAsync() {
        return this.dispatchAsync;
    }
    
    public void setDispatchAsync(final boolean dispatchAsync) {
        this.dispatchAsync = dispatchAsync;
    }
    
    public boolean isDuplex() {
        return this.duplex;
    }
    
    public void setDuplex(final boolean duplex) {
        this.duplex = duplex;
    }
    
    public String getBrokerName() {
        return this.brokerName;
    }
    
    public void setBrokerName(final String brokerName) {
        this.brokerName = brokerName;
    }
    
    public int getNetworkTTL() {
        return this.networkTTL;
    }
    
    public void setNetworkTTL(final int networkTTL) {
        this.networkTTL = networkTTL;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public int getPrefetchSize() {
        return this.prefetchSize;
    }
    
    public void setPrefetchSize(final int prefetchSize) {
        this.prefetchSize = prefetchSize;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(final String userName) {
        this.userName = userName;
    }
    
    public String getDestinationFilter() {
        return this.destinationFilter;
    }
    
    public void setDestinationFilter(final String destinationFilter) {
        this.destinationFilter = destinationFilter;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public List<ActiveMQDestination> getExcludedDestinations() {
        return this.excludedDestinations;
    }
    
    public void setExcludedDestinations(final List<ActiveMQDestination> excludedDestinations) {
        this.excludedDestinations = excludedDestinations;
    }
    
    public List<ActiveMQDestination> getDynamicallyIncludedDestinations() {
        return this.dynamicallyIncludedDestinations;
    }
    
    public void setDynamicallyIncludedDestinations(final List<ActiveMQDestination> dynamicallyIncludedDestinations) {
        this.dynamicallyIncludedDestinations = dynamicallyIncludedDestinations;
    }
    
    public List<ActiveMQDestination> getStaticallyIncludedDestinations() {
        return this.staticallyIncludedDestinations;
    }
    
    public void setStaticallyIncludedDestinations(final List<ActiveMQDestination> staticallyIncludedDestinations) {
        this.staticallyIncludedDestinations = staticallyIncludedDestinations;
    }
    
    public boolean isSuppressDuplicateQueueSubscriptions() {
        return this.suppressDuplicateQueueSubscriptions;
    }
    
    public void setSuppressDuplicateQueueSubscriptions(final boolean val) {
        this.suppressDuplicateQueueSubscriptions = val;
    }
    
    public boolean isSuppressDuplicateTopicSubscriptions() {
        return this.suppressDuplicateTopicSubscriptions;
    }
    
    public void setSuppressDuplicateTopicSubscriptions(final boolean val) {
        this.suppressDuplicateTopicSubscriptions = val;
    }
    
    public String getBrokerURL() {
        return this.brokerURL;
    }
    
    public void setBrokerURL(final String brokerURL) {
        this.brokerURL = brokerURL;
    }
}
