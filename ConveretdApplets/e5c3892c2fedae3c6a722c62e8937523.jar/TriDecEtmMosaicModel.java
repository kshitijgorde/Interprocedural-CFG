import java.awt.Point;
import java.text.DecimalFormat;

// 
// Decompiled by Procyon v0.5.30
// 

public class TriDecEtmMosaicModel extends NavigationModel
{
    private String modelName;
    private String rowName;
    private String colName;
    private DecimalFormat twoDigitFormat;
    
    public TriDecEtmMosaicModel() {
        this.modelName = "Grid";
        this.rowName = "Row";
        this.colName = "Col";
        this.twoDigitFormat = new DecimalFormat("00");
    }
    
    @Override
    public String getModelName() {
        return this.modelName;
    }
    
    @Override
    public String getColName() {
        return this.colName;
    }
    
    @Override
    public String getRowName() {
        return this.rowName;
    }
    
    @Override
    public int getMaximumColumn() {
        return 119;
    }
    
    @Override
    public int getMinimumColumn() {
        return 0;
    }
    
    @Override
    public int getMaximumRow() {
        return 71;
    }
    
    @Override
    public int getMinimumRow() {
        return 0;
    }
    
    @Override
    public String getColumnString(final int n) {
        final String format = this.twoDigitFormat.format(n / 2 + 1);
        String s;
        if ((n & 0x1) == 0x1) {
            s = format + "R";
        }
        else {
            s = format + "L";
        }
        return s;
    }
    
    @Override
    public String getRowString(final int n) {
        int n2 = n - 36;
        String s;
        if (n2 < 0) {
            s = "S";
            n2 = -n2 - 1;
        }
        else {
            s = "N";
        }
        final String string = s + this.twoDigitFormat.format(n2 / 2 * 5);
        String s2;
        if ((n & 0x1) == 0x1) {
            s2 = string + "U";
        }
        else {
            s2 = string + "L";
        }
        return s2;
    }
    
    @Override
    public int getColumnNumberFromString(final String s) {
        final int n = this.getMinimumColumn() - 1;
        if (s.length() != 3) {
            return n;
        }
        final String substring = s.substring(0, 2);
        final String substring2 = s.substring(2, 3);
        int n2 = 0;
        if (substring2.equalsIgnoreCase("R")) {
            n2 = 1;
        }
        else if (!substring2.equalsIgnoreCase("L")) {
            return n;
        }
        int int1;
        try {
            int1 = Integer.parseInt(substring);
        }
        catch (NumberFormatException ex) {
            return n;
        }
        return (int1 - 1) * 2 + n2;
    }
    
    @Override
    public int getRowNumberFromString(final String s) {
        final int n = this.getMinimumRow() - 1;
        if (s.length() != 4) {
            return n;
        }
        final String substring = s.substring(0, 1);
        final String substring2 = s.substring(1, 3);
        final String substring3 = s.substring(3, 4);
        boolean b = false;
        if (substring.equalsIgnoreCase("N")) {
            b = true;
        }
        else if (!substring.equalsIgnoreCase("S")) {
            return n;
        }
        int int1;
        try {
            int1 = Integer.parseInt(substring2);
        }
        catch (NumberFormatException ex) {
            return n;
        }
        int n2 = 0;
        if (substring3.equalsIgnoreCase("U")) {
            n2 = 1;
        }
        else if (!substring3.equalsIgnoreCase("L")) {
            return n;
        }
        if (!b) {
            int1 = -int1;
            if (n2 == 0) {
                n2 = -2;
            }
            else {
                n2 = -1;
            }
        }
        return (int1 + 90) / 5 * 2 + n2;
    }
    
    @Override
    public int getColumnDigits() {
        return 3;
    }
    
    @Override
    public int getRowDigits() {
        return 4;
    }
    
    private int getUtmZoneFromLongitude(final double n) {
        return (int)(Math.abs(-180.0 - n) / 6.0) + 1;
    }
    
    private int getLongitudeFromUtmZone(final int n) {
        return (n - 1) * 6 - 180 + 3;
    }
    
    @Override
    public Point latLongToGrid(double n, final double n2) {
        final int utmZoneFromLongitude = this.getUtmZoneFromLongitude(n2);
        final int longitudeFromUtmZone = this.getLongitudeFromUtmZone(utmZoneFromLongitude);
        int n3 = (utmZoneFromLongitude - 1) * 2;
        if (n2 >= longitudeFromUtmZone) {
            ++n3;
        }
        n += 90.0;
        final int n4 = (int)(n / 5.0);
        final int n5 = n4 * 5;
        int n6 = n4 * 2;
        if (n >= n5 + 2.5) {
            ++n6;
        }
        return new Point(n3, n6);
    }
    
    @Override
    public DoublePoint projToDoubleGrid(final Point point, final ProjectionTransformation projectionTransformation) {
        final LatLong projToLatLong = projectionTransformation.projToLatLong(point.x, point.y);
        final int utmZoneFromLongitude = this.getUtmZoneFromLongitude(projToLatLong.longitude);
        final int longitudeFromUtmZone = this.getLongitudeFromUtmZone(utmZoneFromLongitude);
        final double n = (utmZoneFromLongitude - 1) * 2;
        double n2;
        if (projToLatLong.longitude >= longitudeFromUtmZone) {
            n2 = n + 1.0 + (projToLatLong.longitude - longitudeFromUtmZone) / 3.0;
        }
        else {
            n2 = n + (projToLatLong.longitude - (longitudeFromUtmZone - 3.0)) / 3.0;
        }
        final LatLong latLong = projToLatLong;
        latLong.latitude += 90.0;
        final double n3 = (int)(projToLatLong.latitude / 5.0);
        final int n4 = (int)n3 * 5;
        final double n5 = n3 * 2.0;
        double n6;
        if (projToLatLong.latitude >= n4 + 2.5) {
            n6 = n5 + 1.0 + (projToLatLong.latitude - (n4 + 2.5)) / 2.5;
        }
        else {
            n6 = n5 + (projToLatLong.latitude - n4) / 2.5;
        }
        return new DoublePoint(n2, n6);
    }
    
    @Override
    public LatLong gridToLatLong(final int n, final int n2) {
        return new LatLong(n2 * 2.5 - 90.0 + 1.25, n * 3.0 + 1.5 - 180.0);
    }
    
    @Override
    public Point gridToProjCoords(final int n, final int n2, final ProjectionTransformation projectionTransformation) {
        return projectionTransformation.latLongToProj(this.gridToLatLong(n, n2));
    }
    
    @Override
    public int checkColumnBounds(int n) {
        final int maximumColumn = this.getMaximumColumn();
        final int minimumColumn = this.getMinimumColumn();
        if (n > maximumColumn) {
            n -= maximumColumn + 1;
        }
        else if (n < minimumColumn) {
            n += maximumColumn + 1;
        }
        return n;
    }
    
    @Override
    public int checkRowBounds(int n) {
        final int maximumRow = this.getMaximumRow();
        final int minimumRow = this.getMinimumRow();
        if (n > maximumRow) {
            n = maximumRow;
        }
        else if (n < minimumRow) {
            n = minimumRow;
        }
        return n;
    }
    
    @Override
    public int getRowDownDirection() {
        return -1;
    }
    
    @Override
    public int getColumnRightDirection() {
        return 1;
    }
    
    @Override
    public boolean allowColumnWrapAround() {
        return true;
    }
}
