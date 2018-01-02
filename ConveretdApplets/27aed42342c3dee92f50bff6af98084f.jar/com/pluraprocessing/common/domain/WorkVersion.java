// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WorkVersion
{
    private UUID id;
    private UUID workOwnerId;
    private String externalId;
    private float percentage;
    private int activeWorkCount;
    private NodeType nodeType;
    private float percentageNode;
    private boolean isAltered;
    private int uncompletedCount;
    private boolean isCharitable;
    private String parentId;
    private boolean isTimeSensitive;
    private int minimumRetryTimeSeconds;
    private int maxHeapSpaceNeededMB;
    private List<Executable> executables;
    private int priority;
    private int serverNumber;
    private String versionReloadString;
    
    public WorkVersion() {
        this.id = UUID.randomUUID();
        this.isTimeSensitive = false;
        this.minimumRetryTimeSeconds = 0;
        this.maxHeapSpaceNeededMB = -1;
        this.executables = new ArrayList<Executable>();
        this.priority = 1;
    }
    
    public boolean isTimeSensitive() {
        return this.isTimeSensitive;
    }
    
    public void setTimeSensitive(final boolean isTimeSensitive) {
        this.isTimeSensitive = isTimeSensitive;
    }
    
    public int getMinimumRetryTimeSeconds() {
        return this.minimumRetryTimeSeconds;
    }
    
    public void setMinimumRetryTimeSeconds(final int minimumRetryTimeSeconds) {
        this.minimumRetryTimeSeconds = minimumRetryTimeSeconds;
    }
    
    public String getParentId() {
        return this.parentId;
    }
    
    public void setParentId(final String parentId) {
        this.parentId = parentId;
    }
    
    public UUID getId() {
        return this.id;
    }
    
    public void setId(final UUID id) {
        this.id = id;
    }
    
    public String getExternalId() {
        return this.externalId;
    }
    
    public void setExternalId(final String externalId) {
        this.externalId = externalId;
    }
    
    public UUID getWorkOwnerId() {
        return this.workOwnerId;
    }
    
    public void setWorkOwnerId(final UUID workOwnerId) {
        this.workOwnerId = workOwnerId;
    }
    
    public float getPercentage() {
        return this.percentage;
    }
    
    public void setPercentage(final float percentage) {
        this.percentage = percentage;
    }
    
    public int getActiveWorkCount() {
        return this.activeWorkCount;
    }
    
    public void setActiveWorkCount(final int activeWorkCount) {
        this.activeWorkCount = activeWorkCount;
    }
    
    public static String getVersionFileString(final String versionId) {
        return versionId.replace("-", "");
    }
    
    public NodeType getNodeType() {
        return this.nodeType;
    }
    
    public void setNodeType(final NodeType nodeType) {
        this.nodeType = nodeType;
    }
    
    public float getPercentageNode() {
        return this.percentageNode;
    }
    
    public void setPercentageNode(final float percentageNode) {
        this.percentageNode = percentageNode;
    }
    
    public boolean isAltered() {
        return this.isAltered;
    }
    
    public void setAltered(final boolean isAltered) {
        this.isAltered = isAltered;
    }
    
    public int getUncompletedCount() {
        return this.uncompletedCount;
    }
    
    public void setUncompletedCount(final int uncompletedCount) {
        this.uncompletedCount = uncompletedCount;
    }
    
    public boolean isCharitable() {
        return this.isCharitable;
    }
    
    public void setCharitable(final boolean isCharitable) {
        this.isCharitable = isCharitable;
    }
    
    public String getVersionReloadString() {
        return this.versionReloadString;
    }
    
    public void setVersionReloadString(final String versionReloadString) {
        this.versionReloadString = versionReloadString;
    }
    
    public int getMaxHeapSpaceNeededMB() {
        return this.maxHeapSpaceNeededMB;
    }
    
    public void setMaxHeapSpaceNeededMB(final int maxHeapSpaceNeededMB) {
        this.maxHeapSpaceNeededMB = maxHeapSpaceNeededMB;
    }
    
    public List<Executable> getExecutables() {
        return this.executables;
    }
    
    public void setExecutables(final List<Executable> executables) {
        this.executables = executables;
    }
    
    public int getPriority() {
        return this.priority;
    }
    
    public void setPriority(final int priority) {
        this.priority = priority;
    }
    
    public int getServerNumber() {
        return this.serverNumber;
    }
    
    public void setServerNumber(final int serverNumber) {
        this.serverNumber = serverNumber;
    }
}
