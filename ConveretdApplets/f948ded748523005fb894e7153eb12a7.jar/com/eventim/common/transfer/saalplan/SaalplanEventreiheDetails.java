// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer.saalplan;

import java.util.Date;
import java.io.Serializable;

public class SaalplanEventreiheDetails extends SaalplanBaseDetails implements Serializable
{
    private Date aufnahmedatum;
    private int autoupdate;
    private Date beginn;
    private String bildPfad;
    private String descLong;
    private String descLongSearchText;
    private String descShort;
    private String descShortSearchText;
    private Date ende;
    private int[] eventreiheKategorienIds;
    private int exclusiveAffiliatePoolId;
    private int gesperrteAffiliatePoolId;
    private Integer id;
    private String info;
    private String infoEventimGetgo;
    private Date lastUpdate;
    private int lastUpdateUserId;
    private int maxmenge;
    private String memo;
    private String name;
    private String nameSearchText;
    private int rankReal;
    private int rankTopten;
    private int rankToptenRel;
    private int status;
    private int tdlTourneeId;
    private int tdlVeranstalterId;
    private int tdlVeranstaltungId;
    
    public SaalplanEventreiheDetails(final int ret, final String retText) {
        super(ret, retText);
    }
    
    public SaalplanEventreiheDetails() {
    }
    
    public Date getAufnahmedatum() {
        return this.aufnahmedatum;
    }
    
    public int getAutoupdate() {
        return this.autoupdate;
    }
    
    public Date getBeginn() {
        return this.beginn;
    }
    
    public String getBildPfad() {
        return this.bildPfad;
    }
    
    public String getDescLong() {
        return this.descLong;
    }
    
    public String getDescLongSearchText() {
        return this.descLongSearchText;
    }
    
    public String getDescShort() {
        return this.descShort;
    }
    
    public String getDescShortSearchText() {
        return this.descShortSearchText;
    }
    
    public Date getEnde() {
        return this.ende;
    }
    
    public int[] getEventreiheKategorienIds() {
        return this.eventreiheKategorienIds;
    }
    
    public int getExclusiveAffiliatePoolId() {
        return this.exclusiveAffiliatePoolId;
    }
    
    public int getGesperrteAffiliatePoolId() {
        return this.gesperrteAffiliatePoolId;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public String getInfo() {
        return this.info;
    }
    
    public String getInfoEventimGetgo() {
        return this.infoEventimGetgo;
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
    
    public String getMemo() {
        return this.memo;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getNameSearchText() {
        return this.nameSearchText;
    }
    
    public Object getPrimaryKey() {
        return this.id;
    }
    
    public int getRankReal() {
        return this.rankReal;
    }
    
    public int getRankTopten() {
        return this.rankTopten;
    }
    
    public int getRankToptenRel() {
        return this.rankToptenRel;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public int getTdlTourneeId() {
        return this.tdlTourneeId;
    }
    
    public int getTdlVeranstalterId() {
        return this.tdlVeranstalterId;
    }
    
    public int getTdlVeranstaltungId() {
        return this.tdlVeranstaltungId;
    }
    
    public void setAufnahmedatum(final Date arg0) {
        this.aufnahmedatum = arg0;
    }
    
    public void setAutoupdate(final int arg0) {
        this.autoupdate = arg0;
    }
    
    public void setBeginn(final Date arg0) {
        this.beginn = arg0;
    }
    
    public void setBildPfad(final String arg0) {
        this.bildPfad = arg0;
    }
    
    public void setDescLong(final String arg0) {
        this.descLong = arg0;
    }
    
    public void setDescLongSearchText(final String arg0) {
        this.descLongSearchText = arg0;
    }
    
    public void setDescShort(final String arg0) {
        this.descShort = arg0;
    }
    
    public void setDescShortSearchText(final String arg0) {
        this.descShortSearchText = arg0;
    }
    
    public void setEnde(final Date arg0) {
        this.ende = arg0;
    }
    
    public void setEventreiheKategorienIds(final int[] kategorieIds) {
        this.eventreiheKategorienIds = kategorieIds;
    }
    
    public void setExclusiveAffiliatePoolId(final int exclusiveAffiliatePoolId) {
        this.exclusiveAffiliatePoolId = exclusiveAffiliatePoolId;
    }
    
    public void setGesperrteAffiliatePoolId(final int gesperrteAffiliatePoolId) {
        this.gesperrteAffiliatePoolId = gesperrteAffiliatePoolId;
    }
    
    public void setId(final Integer arg0) {
        this.id = arg0;
    }
    
    public void setInfo(final String arg0) {
        this.info = arg0;
    }
    
    public void setInfoEventimGetgo(final String arg0) {
        this.infoEventimGetgo = arg0;
    }
    
    public void setLastUpdate(final Date arg0) {
        this.lastUpdate = arg0;
    }
    
    public void setLastUpdateUserId(final int arg0) {
        this.lastUpdateUserId = arg0;
    }
    
    public void setMaxmenge(final int maxmenge) {
        this.maxmenge = maxmenge;
    }
    
    public void setMemo(final String arg0) {
        this.memo = arg0;
    }
    
    public void setName(final String arg0) {
        this.name = arg0;
    }
    
    public void setNameSearchText(final String arg0) {
        this.nameSearchText = arg0;
    }
    
    public void setRankReal(final int arg0) {
        this.rankReal = arg0;
    }
    
    public void setRankTopten(final int arg0) {
        this.rankTopten = arg0;
    }
    
    public void setRankToptenRel(final int arg0) {
        this.rankToptenRel = arg0;
    }
    
    public void setStatus(final int arg0) {
        this.status = arg0;
    }
    
    public void setTdlTourneeId(final int arg0) {
        this.tdlTourneeId = arg0;
    }
    
    public void setTdlVeranstalterId(final int arg0) {
        this.tdlVeranstalterId = arg0;
    }
    
    public void setTdlVeranstaltungId(final int arg0) {
        this.tdlVeranstaltungId = arg0;
    }
    
    public String toString() {
        String returnString = "";
        returnString += this.id;
        returnString = returnString + ", " + this.lastUpdate;
        returnString = returnString + ", " + this.lastUpdateUserId;
        returnString = returnString + ", " + this.name;
        returnString = returnString + ", " + this.nameSearchText;
        returnString = returnString + ", " + this.descShort;
        returnString = returnString + ", " + this.descShortSearchText;
        returnString = returnString + ", " + this.descLong;
        returnString = returnString + ", " + this.descLongSearchText;
        returnString = returnString + ", " + this.memo;
        returnString = returnString + ", " + this.bildPfad;
        returnString = returnString + ", " + this.beginn;
        returnString = returnString + ", " + this.ende;
        returnString = returnString + ", " + this.rankReal;
        returnString = returnString + ", " + this.status;
        returnString = returnString + ", " + this.aufnahmedatum;
        returnString = returnString + ", " + this.rankTopten;
        returnString = returnString + ", " + this.rankToptenRel;
        returnString = returnString + ", " + this.infoEventimGetgo;
        returnString = returnString + ", " + this.info;
        returnString = returnString + ", " + this.tdlVeranstaltungId;
        returnString = returnString + ", " + this.tdlTourneeId;
        returnString = returnString + ", " + this.tdlVeranstalterId;
        returnString = returnString + ", " + this.autoupdate;
        returnString = returnString + ", " + this.exclusiveAffiliatePoolId;
        returnString = returnString + ", " + this.gesperrteAffiliatePoolId;
        returnString = returnString + ", " + this.maxmenge;
        returnString = returnString + ", " + this.eventreiheKategorienIds;
        return returnString;
    }
}
