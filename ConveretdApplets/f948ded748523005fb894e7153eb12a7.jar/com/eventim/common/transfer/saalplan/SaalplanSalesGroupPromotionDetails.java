// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer.saalplan;

import java.io.Serializable;

public class SaalplanSalesGroupPromotionDetails extends SaalplanBaseDetails implements Serializable
{
    private int evId;
    private Integer id;
    private int prId;
    private int state;
    private int tdlNumber;
    private int tdlPromotionId;
    private int tdlVorstellungId;
    
    public SaalplanSalesGroupPromotionDetails(final int ret, final String retText) {
        super(ret, retText);
    }
    
    public SaalplanSalesGroupPromotionDetails() {
    }
    
    public int getEvId() {
        return this.evId;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public int getPrId() {
        return this.prId;
    }
    
    public int getState() {
        return this.state;
    }
    
    public int getTdlNumber() {
        return this.tdlNumber;
    }
    
    public int getTdlPromotionId() {
        return this.tdlPromotionId;
    }
    
    public int getTdlVorstellungId() {
        return this.tdlVorstellungId;
    }
    
    public void setEvId(final int evId) {
        this.evId = evId;
    }
    
    public void setId(final Integer id) {
        this.id = id;
    }
    
    public void setPrId(final int prId) {
        this.prId = prId;
    }
    
    public void setState(final int state) {
        this.state = state;
    }
    
    public void setTdlNumber(final int tdlNumber) {
        this.tdlNumber = tdlNumber;
    }
    
    public void setTdlPromotionId(final int tdlPromotionId) {
        this.tdlPromotionId = tdlPromotionId;
    }
    
    public void setTdlVorstellungId(final int tdlVorstellungId) {
        this.tdlVorstellungId = tdlVorstellungId;
    }
}
