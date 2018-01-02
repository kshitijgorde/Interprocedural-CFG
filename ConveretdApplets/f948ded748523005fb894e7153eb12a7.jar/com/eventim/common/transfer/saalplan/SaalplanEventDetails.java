// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.transfer.saalplan;

import java.util.Date;
import java.io.Serializable;

public class SaalplanEventDetails extends SaalplanBaseDetails implements Serializable
{
    private Date aufnahmedatum;
    private Date austrittsdatum;
    private int autoupdate;
    private Date datum;
    private String desc1;
    private String desc2;
    private int dienstleisterId;
    private int erId;
    private int exclusiveAffiliatePoolId;
    private int expressTicket;
    private int gesperrteAffiliatePoolId;
    private Integer id;
    private Date lastUpdate;
    private int lastUpdateUserId;
    private int maxmenge;
    private String name1;
    private String name1SearchText;
    private String name2;
    private String name2SearchText;
    private int produktArt;
    private int reverseAbwicklung;
    private int saalplanJackenplatz;
    private int saalplanMinimumfrei;
    private int saalplanModus;
    private int saalplanProzentfrei;
    private int saalplanSchablone;
    private int status;
    private String tdlBeschreibung;
    private Date tdlFreigabedatum;
    private int tdlMaxTickets;
    public SeatplanDetails tdlSeatplanDetails;
    private int tdlStatus;
    private String tdlSuchbegriff;
    private int tdlVeranstalterId;
    private int tdlVeranstaltungId;
    private int tdlVorstellungId;
    private int tdlVorstellungNr;
    private int veId;
    
    public SaalplanEventDetails(final int ret, final String retText) {
        super(ret, retText);
    }
    
    public SaalplanEventDetails() {
    }
    
    public Date getAufnahmedatum() {
        return this.aufnahmedatum;
    }
    
    public Date getAustrittsdatum() {
        return this.austrittsdatum;
    }
    
    public int getAutoupdate() {
        return this.autoupdate;
    }
    
    public Date getDatum() {
        return this.datum;
    }
    
    public String getDesc1() {
        return this.desc1;
    }
    
    public String getDesc2() {
        return this.desc2;
    }
    
    public int getDienstleisterId() {
        return this.dienstleisterId;
    }
    
    public int getErId() {
        return this.erId;
    }
    
    public int getExclusiveAffiliatePoolId() {
        return this.exclusiveAffiliatePoolId;
    }
    
    public int getExpressTicket() {
        return this.expressTicket;
    }
    
    public int getGesperrteAffiliatePoolId() {
        return this.gesperrteAffiliatePoolId;
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
    
    public String getName1() {
        return this.name1;
    }
    
    public String getName1SearchText() {
        return this.name1SearchText;
    }
    
    public String getName2() {
        return this.name2;
    }
    
    public String getName2SearchText() {
        return this.name2SearchText;
    }
    
    public Object getPrimaryKey() {
        return this.id;
    }
    
    public int getReverseAbwicklung() {
        return this.reverseAbwicklung;
    }
    
    public int getSaalplanJackenplatz() {
        return this.saalplanJackenplatz;
    }
    
    public int getSaalplanMinimumfrei() {
        return this.saalplanMinimumfrei;
    }
    
    public int getSaalplanModus() {
        return this.saalplanModus;
    }
    
    public int getSaalplanProzentfrei() {
        return this.saalplanProzentfrei;
    }
    
    public int getSaalplanSchablone() {
        return this.saalplanSchablone;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public String getTdlBeschreibung() {
        return this.tdlBeschreibung;
    }
    
    public Date getTdlFreigabedatum() {
        return this.tdlFreigabedatum;
    }
    
    public int getTdlMaxTickets() {
        return this.tdlMaxTickets;
    }
    
    public int getTdlStatus() {
        return this.tdlStatus;
    }
    
    public String getTdlSuchbegriff() {
        return this.tdlSuchbegriff;
    }
    
    public int getTdlVeranstalterId() {
        return this.tdlVeranstalterId;
    }
    
    public int getTdlVeranstaltungId() {
        return this.tdlVeranstaltungId;
    }
    
    public int getTdlVorstellungId() {
        return this.tdlVorstellungId;
    }
    
    public int getTdlVorstellungNr() {
        return this.tdlVorstellungNr;
    }
    
    public int getVeId() {
        return this.veId;
    }
    
    public void setAufnahmedatum(final Date arg0) {
        this.aufnahmedatum = arg0;
    }
    
    public void setAustrittsdatum(final Date arg0) {
        this.austrittsdatum = arg0;
    }
    
    public void setAutoupdate(final int arg0) {
        this.autoupdate = arg0;
    }
    
    public void setDatum(final Date arg0) {
        this.datum = arg0;
    }
    
    public void setDesc1(final String arg0) {
        this.desc1 = arg0;
    }
    
    public void setDesc2(final String arg0) {
        this.desc2 = arg0;
    }
    
    public void setDienstleisterId(final int dienstleisterId) {
        this.dienstleisterId = dienstleisterId;
    }
    
    public void setErId(final int arg0) {
        this.erId = arg0;
    }
    
    public void setExclusiveAffiliatePoolId(final int exclusiveAffiliatePoolId) {
        this.exclusiveAffiliatePoolId = exclusiveAffiliatePoolId;
    }
    
    public void setExpressTicket(final int expressTicket) {
        this.expressTicket = expressTicket;
    }
    
    public void setGesperrteAffiliatePoolId(final int gesperrteAffiliatePoolId) {
        this.gesperrteAffiliatePoolId = gesperrteAffiliatePoolId;
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
    
    public void setMaxmenge(final int maxmenge) {
        this.maxmenge = maxmenge;
    }
    
    public void setName1(final String arg0) {
        this.name1 = arg0;
    }
    
    public void setName1SearchText(final String arg0) {
        this.name1SearchText = arg0;
    }
    
    public void setName2(final String arg0) {
        this.name2 = arg0;
    }
    
    public void setName2SearchText(final String arg0) {
        this.name2SearchText = arg0;
    }
    
    public void setReverseAbwicklung(final int reverseAbwicklung) {
        this.reverseAbwicklung = reverseAbwicklung;
    }
    
    public void setSaalplanJackenplatz(final int arg0) {
        this.saalplanJackenplatz = arg0;
    }
    
    public void setSaalplanMinimumfrei(final int arg0) {
        this.saalplanMinimumfrei = arg0;
    }
    
    public void setSaalplanModus(final int arg0) {
        this.saalplanModus = arg0;
    }
    
    public void setSaalplanProzentfrei(final int arg0) {
        this.saalplanProzentfrei = arg0;
    }
    
    public void setSaalplanSchablone(final int arg0) {
        this.saalplanSchablone = arg0;
    }
    
    public void setStatus(final int arg0) {
        this.status = arg0;
    }
    
    public void setTdlBeschreibung(final String tdlBeschreibung) {
        this.tdlBeschreibung = tdlBeschreibung;
    }
    
    public void setTdlFreigabedatum(final Date arg0) {
        this.tdlFreigabedatum = arg0;
    }
    
    public void setTdlMaxTickets(final int tdlMaxTickets) {
        this.tdlMaxTickets = tdlMaxTickets;
    }
    
    public void setTdlStatus(final int arg0) {
        this.tdlStatus = arg0;
    }
    
    public void setTdlSuchbegriff(final String arg0) {
        this.tdlSuchbegriff = arg0;
    }
    
    public void setTdlVeranstalterId(final int arg0) {
        this.tdlVeranstalterId = arg0;
    }
    
    public void setTdlVeranstaltungId(final int tdlVeranstaltungId) {
        this.tdlVeranstaltungId = tdlVeranstaltungId;
    }
    
    public void setTdlVorstellungId(final int arg0) {
        this.tdlVorstellungId = arg0;
    }
    
    public void setTdlVorstellungNr(final int tdlVorstellungNr) {
        this.tdlVorstellungNr = tdlVorstellungNr;
    }
    
    public void setVeId(final int arg0) {
        this.veId = arg0;
    }
    
    public String toString() {
        String returnString = "";
        returnString += this.id;
        returnString = returnString + ", " + this.lastUpdate;
        returnString = returnString + ", " + this.lastUpdateUserId;
        returnString = returnString + ", " + this.erId;
        returnString = returnString + ", " + this.veId;
        returnString = returnString + ", " + this.name1;
        returnString = returnString + ", " + this.name1SearchText;
        returnString = returnString + ", " + this.name2;
        returnString = returnString + ", " + this.name2SearchText;
        returnString = returnString + ", " + this.datum;
        returnString = returnString + ", " + this.aufnahmedatum;
        returnString = returnString + ", " + this.austrittsdatum;
        returnString = returnString + ", " + this.status;
        returnString = returnString + ", " + this.desc1;
        returnString = returnString + ", " + this.desc2;
        returnString = returnString + ", " + this.saalplanModus;
        returnString = returnString + ", " + this.saalplanSchablone;
        returnString = returnString + ", " + this.saalplanProzentfrei;
        returnString = returnString + ", " + this.saalplanMinimumfrei;
        returnString = returnString + ", " + this.saalplanJackenplatz;
        returnString = returnString + ", " + this.tdlVorstellungId;
        returnString = returnString + ", " + this.tdlVeranstalterId;
        returnString = returnString + ", " + this.tdlVeranstaltungId;
        returnString = returnString + ", " + this.tdlMaxTickets;
        returnString = returnString + ", " + this.autoupdate;
        returnString = returnString + ", " + this.tdlSuchbegriff;
        returnString = returnString + ", " + this.tdlStatus;
        returnString = returnString + ", " + this.produktArt;
        returnString = returnString + ", " + this.tdlFreigabedatum;
        returnString = returnString + ", " + this.exclusiveAffiliatePoolId;
        returnString = returnString + ", " + this.gesperrteAffiliatePoolId;
        returnString = returnString + ", " + this.expressTicket;
        returnString = returnString + ", " + this.maxmenge;
        returnString = returnString + ", " + this.reverseAbwicklung;
        returnString = returnString + ", " + this.tdlBeschreibung;
        returnString = returnString + ", " + this.tdlVorstellungNr;
        returnString = returnString + ", " + this.dienstleisterId;
        return returnString;
    }
}
