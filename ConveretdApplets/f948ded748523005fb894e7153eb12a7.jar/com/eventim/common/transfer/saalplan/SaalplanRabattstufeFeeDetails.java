// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer.saalplan;

import java.util.Date;

public class SaalplanRabattstufeFeeDetails extends SaalplanBaseDetails
{
    private Integer id;
    private Date insertDate;
    private int insertUserId;
    private Date lastUpdate;
    private int lastUpdateUserId;
    private int rsId;
    private int tdlCrc;
    private double tdlEndPreis;
    private int tdlGebKonto;
    private int tdlGebStSchl;
    private int tdlLfdNr;
    private String tdlName;
    private double tdlNettoPreis;
    private int tdlPreisRabattGebuehrenId;
    private int tdlPreisRabattLinkId;
    private double tdlProzent;
    private int tdlTemplateNr;
    private int tdlTyp;
    private int tdlVorstellungId;
    private int version;
    
    public SaalplanRabattstufeFeeDetails(final int ret, final String retText) {
        super(ret, retText);
    }
    
    public SaalplanRabattstufeFeeDetails() {
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public Date getInsertDate() {
        return this.insertDate;
    }
    
    public int getInsertUserId() {
        return this.insertUserId;
    }
    
    public Date getLastUpdate() {
        return this.lastUpdate;
    }
    
    public int getLastUpdateUserId() {
        return this.lastUpdateUserId;
    }
    
    public int getRsId() {
        return this.rsId;
    }
    
    public int getTdlCrc() {
        return this.tdlCrc;
    }
    
    public double getTdlEndPreis() {
        return this.tdlEndPreis;
    }
    
    public int getTdlGebKonto() {
        return this.tdlGebKonto;
    }
    
    public int getTdlGebStSchl() {
        return this.tdlGebStSchl;
    }
    
    public int getTdlLfdNr() {
        return this.tdlLfdNr;
    }
    
    public String getTdlName() {
        return this.tdlName;
    }
    
    public double getTdlNettoPreis() {
        return this.tdlNettoPreis;
    }
    
    public int getTdlPreisRabattGebuehrenId() {
        return this.tdlPreisRabattGebuehrenId;
    }
    
    public int getTdlPreisRabattLinkId() {
        return this.tdlPreisRabattLinkId;
    }
    
    public double getTdlProzent() {
        return this.tdlProzent;
    }
    
    public int getTdlTemplateNr() {
        return this.tdlTemplateNr;
    }
    
    public int getTdlTyp() {
        return this.tdlTyp;
    }
    
    public int getTdlVorstellungId() {
        return this.tdlVorstellungId;
    }
    
    public int getVersion() {
        return this.version;
    }
    
    public void setId(final Integer id) {
        this.id = id;
    }
    
    public void setInsertDate(final Date insertDate) {
        this.insertDate = insertDate;
    }
    
    public void setInsertUserId(final int insertUserId) {
        this.insertUserId = insertUserId;
    }
    
    public void setLastUpdate(final Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
    public void setLastUpdateUserId(final int lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }
    
    public void setRsId(final int rsId) {
        this.rsId = rsId;
    }
    
    public void setTdlCrc(final int tdlCrc) {
        this.tdlCrc = tdlCrc;
    }
    
    public void setTdlEndPreis(final double tdlEndPreis) {
        this.tdlEndPreis = tdlEndPreis;
    }
    
    public void setTdlGebKonto(final int tdlGebKonto) {
        this.tdlGebKonto = tdlGebKonto;
    }
    
    public void setTdlGebStSchl(final int tdlGebStSchl) {
        this.tdlGebStSchl = tdlGebStSchl;
    }
    
    public void setTdlLfdNr(final int tdlLfdNr) {
        this.tdlLfdNr = tdlLfdNr;
    }
    
    public void setTdlName(final String tdlName) {
        this.tdlName = tdlName;
    }
    
    public void setTdlNettoPreis(final double tdlNettoPreis) {
        this.tdlNettoPreis = tdlNettoPreis;
    }
    
    public void setTdlPreisRabattGebuehrenId(final int tdlPreisRabattGebuehrenId) {
        this.tdlPreisRabattGebuehrenId = tdlPreisRabattGebuehrenId;
    }
    
    public void setTdlPreisRabattLinkId(final int tdlPreisRabattLinkId) {
        this.tdlPreisRabattLinkId = tdlPreisRabattLinkId;
    }
    
    public void setTdlProzent(final double tdlProzent) {
        this.tdlProzent = tdlProzent;
    }
    
    public void setTdlTemplateNr(final int tdlTemplateNr) {
        this.tdlTemplateNr = tdlTemplateNr;
    }
    
    public void setTdlTyp(final int tdlTyp) {
        this.tdlTyp = tdlTyp;
    }
    
    public void setTdlVorstellungId(final int tdlVorstellungId) {
        this.tdlVorstellungId = tdlVorstellungId;
    }
    
    public void setVersion(final int version) {
        this.version = version;
    }
}
