// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer.saalplan;

import java.io.Serializable;

public class SaalplanPromotionCodeDetails extends SaalplanBaseDetails implements Serializable
{
    private Integer id;
    private int prId;
    private String sessionId;
    private String tdlCode;
    private int tdlPromotionId;
    private int tdlState;
    private int tdlTicketsLeft;
    private int tdlVorstellungId;
    private int ticketsLeft;
    
    public SaalplanPromotionCodeDetails(final int ret, final String retText) {
        super(ret, retText);
    }
    
    public SaalplanPromotionCodeDetails() {
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public int getPrId() {
        return this.prId;
    }
    
    public String getSessionId() {
        return this.sessionId;
    }
    
    public String getTdlCode() {
        return this.tdlCode;
    }
    
    public int getTdlPromotionId() {
        return this.tdlPromotionId;
    }
    
    public int getTdlState() {
        return this.tdlState;
    }
    
    public int getTdlTicketsLeft() {
        return this.tdlTicketsLeft;
    }
    
    public int getTdlVorstellungId() {
        return this.tdlVorstellungId;
    }
    
    public int getTicketsLeft() {
        return this.ticketsLeft;
    }
    
    public void setId(final Integer id) {
        this.id = id;
    }
    
    public void setPrId(final int prId) {
        this.prId = prId;
    }
    
    public void setSessionId(final String sessionId) {
        this.sessionId = sessionId;
    }
    
    public void setTdlCode(final String tdlCode) {
        this.tdlCode = tdlCode;
    }
    
    public void setTdlPromotionId(final int tdlPromotionId) {
        this.tdlPromotionId = tdlPromotionId;
    }
    
    public void setTdlState(final int tdlState) {
        this.tdlState = tdlState;
    }
    
    public void setTdlTicketsLeft(final int tdlTicketsLeft) {
        this.tdlTicketsLeft = tdlTicketsLeft;
    }
    
    public void setTdlVorstellungId(final int tdlVorstellungId) {
        this.tdlVorstellungId = tdlVorstellungId;
    }
    
    public void setTicketsLeft(final int ticketsLeft) {
        this.ticketsLeft = ticketsLeft;
    }
}
