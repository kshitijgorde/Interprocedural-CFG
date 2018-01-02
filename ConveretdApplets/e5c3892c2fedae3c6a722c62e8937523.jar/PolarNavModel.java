import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class PolarNavModel extends NavigationModel
{
    private String modelName;
    private String rowName;
    private String colName;
    private int min_col;
    private int max_col;
    private int min_row;
    private int max_row;
    private int columns;
    private int rows;
    private final int CELL_SIZE = 120;
    private final int MIN_X = -2880;
    private final int MAX_X = 2880;
    private final int MIN_Y = -2880;
    private final int MAX_Y = 2880;
    private ProjectionTransformation proj;
    
    public PolarNavModel() {
        this.modelName = "Polar";
        this.rowName = "Row";
        this.colName = "Col";
        this.min_col = -24;
        this.max_col = 24;
        this.min_row = -24;
        this.max_row = 24;
        this.columns = this.max_col - this.min_col + 1;
        this.rows = this.max_row - this.min_row + 1;
        this.proj = CreateProjection.fromProjectionNumber(1012);
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
        return this.max_col;
    }
    
    @Override
    public int getMinimumColumn() {
        return this.min_col;
    }
    
    @Override
    public int getMaximumRow() {
        return this.max_row;
    }
    
    @Override
    public int getMinimumRow() {
        return this.min_row;
    }
    
    @Override
    public String getColumnString(int n) {
        String s;
        if (n < 0) {
            s = "W";
            n = -n;
        }
        else {
            s = "E";
        }
        return s + n;
    }
    
    @Override
    public String getRowString(int n) {
        String s;
        if (n < 0) {
            s = "S";
            n = -n;
        }
        else {
            s = "N";
        }
        return s + n;
    }
    
    @Override
    public int getColumnNumberFromString(final String s) {
        final int n = this.getMinimumColumn() - 1;
        if (s.length() < 2) {
            return n;
        }
        final String substring = s.substring(0, 1);
        final String substring2 = s.substring(1);
        int int1;
        try {
            int1 = Integer.parseInt(substring2);
        }
        catch (NumberFormatException ex) {
            return n;
        }
        if (substring.equals("W")) {
            int1 = -int1;
        }
        return int1;
    }
    
    @Override
    public int getRowNumberFromString(final String s) {
        final int n = this.getMinimumRow() - 1;
        if (s.length() < 2) {
            return n;
        }
        final String substring = s.substring(0, 1);
        final String substring2 = s.substring(1);
        int int1;
        try {
            int1 = Integer.parseInt(substring2);
        }
        catch (NumberFormatException ex) {
            return n;
        }
        if (substring.equals("S")) {
            int1 = -int1;
        }
        return int1;
    }
    
    @Override
    public int getColumnDigits() {
        return 3;
    }
    
    @Override
    public Point latLongToGrid(final double n, final double n2) {
        final Point latLongToProj = this.proj.latLongToProj(new LatLong(n, n2));
        return new Point(Math.round(latLongToProj.x / 120000), Math.round(latLongToProj.y / 120000));
    }
    
    @Override
    public DoublePoint projToDoubleGrid(final Point point, final ProjectionTransformation projectionTransformation) {
        return new DoublePoint(point.x / 120000, point.y / 120000);
    }
    
    @Override
    public LatLong gridToLatLong(final int n, final int n2) {
        return this.proj.projToLatLong(n * 120 * 1000, n2 * 120 * 1000);
    }
    
    @Override
    public Point gridToProjCoords(final int n, final int n2, final ProjectionTransformation projectionTransformation) {
        return new Point(n * 120 * 1000, n2 * 120 * 1000);
    }
    
    @Override
    public int checkColumnBounds(int n) {
        final int maximumColumn = this.getMaximumColumn();
        final int minimumColumn = this.getMinimumColumn();
        if (n > maximumColumn) {
            n = maximumColumn;
        }
        else if (n < minimumColumn) {
            n = minimumColumn;
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
}
