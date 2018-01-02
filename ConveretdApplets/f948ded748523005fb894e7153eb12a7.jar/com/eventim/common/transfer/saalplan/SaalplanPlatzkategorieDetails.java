// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer.saalplan;

import java.util.Date;
import java.io.Serializable;

public class SaalplanPlatzkategorieDetails extends SaalplanBaseDetails implements Serializable
{
    private int autoupdate;
    private int erId;
    private int evId;
    private int exclusiveAffiliatePoolId;
    private short flagobsolet;
    private int gemischteRabattstufen;
    private int gesperrteAffiliatePoolId;
    private double handlingfee;
    private Integer id;
    private Date lastUpdate;
    private int lastUpdateUserId;
    private int maxmenge;
    private String name;
    private int nummer;
    private int priorisierungZh;
    private int produktart;
    private SaalplanRabattstufeDetails[] rabattstufen;
    private int saalplanMaxmenge;
    private int status;
    private int tdlPreisklasseId;
    
    public SaalplanPlatzkategorieDetails(final int ret, final String retText) {
        super(ret, retText);
    }
    
    public SaalplanPlatzkategorieDetails() {
    }
    
    public int getAutoupdate() {
        return this.autoupdate;
    }
    
    public int getErId() {
        return this.erId;
    }
    
    public int getEvId() {
        return this.evId;
    }
    
    public int getExclusiveAffiliatePoolId() {
        return this.exclusiveAffiliatePoolId;
    }
    
    public short getFlagobsolet() {
        return this.flagobsolet;
    }
    
    public int getGemischteRabattstufen() {
        return this.gemischteRabattstufen;
    }
    
    public int getGesperrteAffiliatePoolId() {
        return this.gesperrteAffiliatePoolId;
    }
    
    public double getHandlingfee() {
        return this.handlingfee;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public Date getLastUpdate() {
        return this.lastUpdate;
    }
    
    public int getLastUpdateUserId() {
        return this.lastUpdateUserId;
    }
    
    public int getMaxmenge() {
        return this.maxmenge;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getNummer() {
        return this.nummer;
    }
    
    public Object getPrimaryKey() {
        return this.id;
    }
    
    public int getPriorisierungZh() {
        return this.priorisierungZh;
    }
    
    public int getProduktart() {
        return this.produktart;
    }
    
    public SaalplanRabattstufeDetails[] getRabattstufen() {
        return this.rabattstufen;
    }
    
    public int getSaalplanMaxmenge() {
        return this.saalplanMaxmenge;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public int getTdlPreisklasseId() {
        return this.tdlPreisklasseId;
    }
    
    public void setAutoupdate(final int arg0) {
        this.autoupdate = arg0;
    }
    
    public void setErId(final int arg0) {
        this.erId = arg0;
    }
    
    public void setEvId(final int arg0) {
        this.evId = arg0;
    }
    
    public void setExclusiveAffiliatePoolId(final int exclusiveAffiliatePoolId) {
        this.exclusiveAffiliatePoolId = exclusiveAffiliatePoolId;
    }
    
    public void setFlagobsolet(final short arg0) {
        this.flagobsolet = arg0;
    }
    
    public void setGemischteRabattstufen(final int arg0) {
        this.gemischteRabattstufen = arg0;
    }
    
    public void setGesperrteAffiliatePoolId(final int gesperrteAffiliatePoolId) {
        this.gesperrteAffiliatePoolId = gesperrteAffiliatePoolId;
    }
    
    public void setHandlingfee(final double arg0) {
        this.handlingfee = arg0;
    }
    
    public void setId(final Integer arg0) {
        this.id = arg0;
    }
    
    public void setLastUpdate(final Date arg0) {
        this.lastUpdate = arg0;
    }
    
    public void setLastUpdateUserId(final int arg0) {
        this.lastUpdateUserId = arg0;
    }
    
    public void setMaxmenge(final int arg0) {
        this.maxmenge = arg0;
    }
    
    public void setName(final String arg0) {
        this.name = arg0;
    }
    
    public void setNummer(final int arg0) {
        this.nummer = arg0;
    }
    
    public void setPriorisierungZh(final int arg0) {
        this.priorisierungZh = arg0;
    }
    
    public void setProduktart(final int produktart) {
        this.produktart = produktart;
    }
    
    public void setRabattstufen(final SaalplanRabattstufeDetails[] arg0) {
        this.rabattstufen = arg0;
    }
    
    public void setSaalplanMaxmenge(final int arg0) {
        this.saalplanMaxmenge = arg0;
    }
    
    public void setStatus(final int arg0) {
        this.status = arg0;
    }
    
    public void setTdlPreisklasseId(final int arg0) {
        this.tdlPreisklasseId = arg0;
    }
    
    public String toString() {
        String returnString = "";
        returnString = returnString + this.id + ", ";
        returnString = returnString + this.lastUpdate + ", ";
        returnString = returnString + this.lastUpdateUserId + ", ";
        returnString = returnString + this.erId + ", ";
        returnString = returnString + this.evId + ", ";
        returnString = returnString + this.name + ", ";
        returnString = returnString + this.nummer + ", ";
        returnString = returnString + this.flagobsolet + ", ";
        returnString = returnString + this.maxmenge + ", ";
        returnString = returnString + this.saalplanMaxmenge + ", ";
        returnString = returnString + this.handlingfee + ", ";
        returnString = returnString + this.tdlPreisklasseId + ", ";
        returnString = returnString + this.priorisierungZh + ", ";
        returnString = returnString + this.status + ", ";
        returnString = returnString + this.autoupdate + ", ";
        returnString = returnString + this.exclusiveAffiliatePoolId + ", ";
        returnString = returnString + this.gesperrteAffiliatePoolId + ", ";
        returnString = returnString + this.gemischteRabattstufen + ", ";
        returnString += this.produktart;
        return returnString;
    }
}
