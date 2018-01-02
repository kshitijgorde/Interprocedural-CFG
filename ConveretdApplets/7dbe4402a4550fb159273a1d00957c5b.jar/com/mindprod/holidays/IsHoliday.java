// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.holidays;

import com.mindprod.common11.BigDate;
import java.util.BitSet;

public final class IsHoliday
{
    static final boolean DEBUGGING = false;
    protected int firstOrd;
    protected int firstYear;
    protected int lastOrd;
    protected int lastYear;
    protected BitSet holidayBits;
    
    public IsHoliday(final int firstYear, final int lastYear) {
        if (firstYear < 1) {
            throw new IllegalArgumentException("firstYear=" + firstYear + " must be > 0.");
        }
        if (lastYear > 999999) {
            throw new IllegalArgumentException("lastYear=" + lastYear + " must be <= " + 999999 + ".");
        }
        if (lastYear < firstYear) {
            throw new IllegalArgumentException("firstYear=" + firstYear + " must be <= lastYear=" + lastYear + ".");
        }
        this.firstYear = firstYear;
        this.lastYear = lastYear;
        this.firstOrd = BigDate.toOrdinal(firstYear, 1, 1);
        this.lastOrd = BigDate.toOrdinal(lastYear, 12, 31);
        this.holidayBits = new BitSet(this.lastOrd - this.firstOrd + 1);
    }
    
    public void addAmericanFederalHolidays(final boolean shift) {
        for (int year = this.firstYear; year <= this.lastYear; ++year) {
            this.addHoliday(new NewYearsDay().when(year, shift, 0));
            this.addHoliday(new MartinLutherKingDay().when(year, shift, 0));
            this.addHoliday(new LincolnsBirthday().when(year, shift, 0));
            this.addHoliday(new PresidentsDay().when(year, shift, 0));
            this.addHoliday(new WashingtonsBirthday().when(year, shift, 0));
            this.addHoliday(new MemorialDay().when(year, shift, 0));
            this.addHoliday(new IndependenceDay().when(year, shift, 0));
            this.addHoliday(new LaborDay().when(year, shift, 0));
            this.addHoliday(new ColumbusDay().when(year, shift, 0));
            this.addHoliday(new VeteransDay().when(year, shift, 0));
            this.addHoliday(new AmericanThanksgiving().when(year, shift, 0));
            this.addHoliday(new Christmas().when(year, shift, 0));
        }
    }
    
    public void addCanadianFederalHolidays(final boolean shift) {
        for (int year = this.firstYear; year <= this.lastYear; ++year) {
            this.addHoliday(new NewYearsDay().when(year, shift, 0));
            this.addHoliday(new GoodFriday().when(year, shift, 0));
            this.addHoliday(new EasterMonday().when(year, shift, 0));
            this.addHoliday(new VictoriaDay().when(year, shift));
            this.addHoliday(new CanadaDay().when(year, shift, 0));
            this.addHoliday(new LabourDay().when(year, shift, 0));
            this.addHoliday(new CanadianThanksgiving().when(year, shift, 0));
            this.addHoliday(new RemembranceDay().when(year, shift, 0));
            this.addHoliday(new Christmas().when(year, shift, 0));
            this.addHoliday(new BoxingDay().when(year, shift, 0));
        }
    }
    
    public void addHoliday(final int ordinal) {
        if (ordinal < this.firstOrd || ordinal > this.lastOrd) {
            return;
        }
        this.holidayBits.set(ordinal - this.firstOrd);
    }
    
    public void addWeekendsAsHolidays() {
        int i;
        for (int ordFirstSunday = i = BigDate.ordinalOfnthXXXDay(1, 0, this.firstYear, 1); i <= this.lastOrd; i += 7) {
            this.addHoliday(i);
        }
        int j;
        for (int ordFirstSaturday = j = BigDate.ordinalOfnthXXXDay(1, 6, this.firstYear, 1); j <= this.lastOrd; j += 7) {
            this.addHoliday(j);
        }
    }
    
    public int getFirstYear() {
        return this.firstYear;
    }
    
    public int getLastYear() {
        return this.lastYear;
    }
    
    public boolean isHoliday(final BigDate bigDate) {
        return this.isHoliday(bigDate.getOrdinal());
    }
    
    public boolean isHoliday(final int ordinal) {
        if (ordinal < this.firstOrd || ordinal > this.lastOrd) {
            throw new IllegalArgumentException("out of range ordinal date: " + ordinal);
        }
        return this.holidayBits.get(ordinal - this.firstOrd);
    }
    
    public static void main(final String[] args) {
    }
}
