import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.Iterator;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class CircleAreaRunner
{
    public static int NUMBER_OF_ZONES;
    public static int LOW_NUMBER_DIVIDER;
    public static final double NEARLY_EQUAL_FACTOR = 0.1;
    public static final double DUPLICATE_POINTS_LIMIT = 1.5;
    public static double[] zoneVariance;
    public static boolean[] structureData;
    public static double[] rangeData;
    public static boolean[] binaryAreaData;
    public static String[] zonePairString;
    public static CirclePanel circlePanel;
    public static String[] circleIndex;
    public static String[] zoneIndex;
    
    static {
        CircleAreaRunner.NUMBER_OF_ZONES = 8;
        CircleAreaRunner.LOW_NUMBER_DIVIDER = 1000000;
        CircleAreaRunner.zoneVariance = new double[CircleAreaRunner.NUMBER_OF_ZONES];
        CircleAreaRunner.structureData = new boolean[CircleAreaRunner.NUMBER_OF_ZONES];
        CircleAreaRunner.rangeData = new double[21];
        CircleAreaRunner.binaryAreaData = new boolean[21];
        CircleAreaRunner.zonePairString = new String[21];
        CircleAreaRunner.circlePanel = null;
        CircleAreaRunner.circleIndex = new String[] { "A", "B", "C" };
        CircleAreaRunner.zoneIndex = new String[] { "Not Used", "A", "B", "AB", "C", "AC", "BC", "ABC" };
    }
    
    public static void main(final String[] args) {
        compareAreas();
    }
    
    public static void compareAreas() {
        final Random r = new Random();
        final ArrayList circlesList = new ArrayList();
        final int numberOfTests = 1000;
        for (int test = 0; test < numberOfTests; ++test) {
            final CircleLayout[] circles = new CircleLayout[3];
            for (int i = 0; i < circles.length; ++i) {
                circles[i] = new CircleLayout();
                circles[i].center = new Point2D.Double();
                circles[i].center.x = r.nextDouble() * 100.0 + 100.0;
                circles[i].center.y = r.nextDouble() * 100.0 + 100.0;
                circles[i].radius = r.nextDouble() * 80.0 + 20.0;
            }
            circlesList.add(circles);
        }
        long start = System.currentTimeMillis();
        for (final CircleLayout[] circles2 : circlesList) {
            CircleGeometry.computeArea(circles2);
        }
        long end = System.currentTimeMillis();
        System.out.println("GEOM 1 Time taken for " + numberOfTests + " diagrams is " + (end - start) / 1000.0 + " seconds");
        start = System.currentTimeMillis();
        for (final CircleLayout[] circles2 : circlesList) {
            CircleGeometry.computeArea(circles2);
        }
        end = System.currentTimeMillis();
        System.out.println("GEOM 2 Time taken for " + numberOfTests + " diagrams is " + (end - start) / 1000.0 + " seconds");
        start = System.currentTimeMillis();
        for (final CircleLayout[] circles2 : circlesList) {
            computeAreaByPolygons(circles2);
        }
        end = System.currentTimeMillis();
        System.out.println("POLY 1 Time taken for " + numberOfTests + " diagrams is " + (end - start) / 1000.0 + " seconds");
        start = System.currentTimeMillis();
        for (final CircleLayout[] circles2 : circlesList) {
            computeAreaByPolygons(circles2);
        }
        end = System.currentTimeMillis();
        System.out.println("POLY 2 Time taken for " + numberOfTests + " diagrams is " + (end - start) / 1000.0 + " seconds");
    }
    
    public static void testAllowableRatios() {
        int low = 1;
        int high = 1;
        double[] result = allowableRatios(low, high);
        System.out.println("low " + low + " high " + high + " range " + result[0] + " " + result[1]);
        low = 1010;
        high = 1030;
        result = allowableRatios(low, high);
        System.out.println("low " + low + " high " + high + " range " + round(result[0], 2) + " " + round(result[1], 2));
        low = 10;
        high = 15;
        result = allowableRatios(low, high);
        System.out.println("low " + low + " high " + high + " range " + round(result[0], 2) + " " + round(result[1], 2));
        low = 10;
        high = 18;
        result = allowableRatios(low, high);
        System.out.println("low " + low + " high " + high + " range " + round(result[0], 2) + " " + round(result[1], 2));
        low = 1;
        high = 2;
        result = allowableRatios(low, high);
        System.out.println("low " + low + " high " + high + " range " + round(result[0], 2) + " " + round(result[1], 2));
        low = 1;
        high = 3;
        result = allowableRatios(low, high);
        System.out.println("low " + low + " high " + high + " range " + round(result[0], 2) + " " + round(result[1], 2));
        low = 1;
        high = 10;
        result = allowableRatios(low, high);
        System.out.println("low " + low + " high " + high + " range " + round(result[0], 2) + " " + round(result[1], 2));
        low = 6;
        high = 7;
        result = allowableRatios(low, high);
        System.out.println("low " + low + " high " + high + " range " + round(result[0], 2) + " " + round(result[1], 2));
        low = 6;
        high = 12;
        result = allowableRatios(low, high);
        System.out.println("low " + low + " high " + high + " range " + round(result[0], 2) + " " + round(result[1], 2));
    }
    
    public static double round(final double inAmount, final int decimalPlaces) {
        long divider = 1L;
        for (int i = 1; i <= decimalPlaces; ++i) {
            divider *= 10L;
        }
        final double largeAmount = Math.rint(inAmount * divider);
        return largeAmount / divider;
    }
    
    public static void testNearlyEqual() {
        System.out.println("START TESTING nearlyEqual");
        if (!nearlyEqual(10.0, 11.0, 0.05)) {
            System.out.println("Failed 1");
        }
        if (nearlyEqual(10.0, 9.0, 0.05)) {
            System.out.println("Failed 2");
        }
        if (nearlyEqual(10.0, 11.0, 0.005)) {
            System.out.println("Failed 3");
        }
        if (!nearlyEqual(10.0, 1000.0, 1.0)) {
            System.out.println("Failed 4");
        }
        if (!nearlyEqual(10.0, 10.0, 0.0)) {
            System.out.println("Failed 5");
        }
        System.out.println("END TESTING nearlyEqual");
    }
    
    public static void testHillClimber() {
        final CircleLayout c1 = new CircleLayout();
        c1.center = new Point2D.Double(110.0, 120.0);
        c1.radius = 15.0;
        final CircleLayout c2 = new CircleLayout();
        c2.center = new Point2D.Double(130.0, 120.0);
        c2.radius = 15.0;
        final CircleLayout c3 = new CircleLayout();
        c3.center = new Point2D.Double(120.0, 110.0);
        c3.radius = 15.0;
        final CircleLayout[] circles = { c1, c2, c3 };
        double[] areas = computeAreaByPolygons(circles);
        outputCircles(circles);
        outputAreas(areas);
        final double[] pops1 = { 0.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0 };
        System.out.println("1 Fitness before " + computeFitness(pops1, circles));
        hillClimber(pops1, circles, true);
        System.out.println("1 Fitness after " + computeFitness(pops1, circles));
        outputCircles(circles);
        areas = computeAreaByPolygons(circles);
        outputAreas(areas);
    }
    
    public static Ellipse2D.Double ellipseFromCircle(final CircleLayout circle) {
        final double x = circle.center.x - circle.radius;
        final double y = circle.center.y - circle.radius;
        final double diameter = circle.radius * 2.0;
        final Ellipse2D.Double ellipse = new Ellipse2D.Double(x, y, diameter, diameter);
        return ellipse;
    }
    
    public static double[] computeAreaByPolygons(final CircleLayout[] circles) {
        final int accuracy = 6;
        final double[] ret = new double[8];
        final Ellipse2D.Double ellipseA = ellipseFromCircle(circles[0]);
        final Ellipse2D.Double ellipseB = ellipseFromCircle(circles[1]);
        final Ellipse2D.Double ellipseC = ellipseFromCircle(circles[2]);
        final Area areaA = new Area(ellipseA);
        final Area areaB = new Area(ellipseB);
        final Area areaC = new Area(ellipseC);
        final Area area1 = new Area(areaA);
        area1.subtract(areaB);
        area1.subtract(areaC);
        final Polygon p1 = getPolygonFromArea(area1, accuracy);
        final double value1 = getPolygonArea(p1);
        ret[1] = value1;
        final Area area2 = new Area(areaB);
        area2.subtract(areaA);
        area2.subtract(areaC);
        final Polygon p2 = getPolygonFromArea(area2, accuracy);
        final double value2 = getPolygonArea(p2);
        ret[2] = value2;
        final Area area3 = new Area(areaA);
        area3.intersect(areaB);
        area3.subtract(areaC);
        final Polygon p3 = getPolygonFromArea(area3, accuracy);
        final double value3 = getPolygonArea(p3);
        ret[3] = value3;
        final Area area4 = new Area(areaC);
        area4.subtract(areaA);
        area4.subtract(areaB);
        final Polygon p4 = getPolygonFromArea(area4, accuracy);
        final double value4 = getPolygonArea(p4);
        ret[4] = value4;
        final Area area5 = new Area(areaA);
        area5.intersect(areaC);
        area5.subtract(areaB);
        final Polygon p5 = getPolygonFromArea(area5, accuracy);
        final double value5 = getPolygonArea(p5);
        ret[5] = value5;
        final Area area6 = new Area(areaB);
        area6.intersect(areaC);
        area6.subtract(areaA);
        final Polygon p6 = getPolygonFromArea(area6, accuracy);
        final double value6 = getPolygonArea(p6);
        ret[6] = value6;
        final Area area7 = new Area(areaA);
        area7.intersect(areaB);
        area7.intersect(areaC);
        final Polygon p7 = getPolygonFromArea(area7, accuracy);
        final double value7 = getPolygonArea(p7);
        ret[7] = value7;
        return ret;
    }
    
    public static void outputCircles(final CircleLayout[] circles) {
        for (int i = 0; i < circles.length; ++i) {
            System.out.println(String.valueOf(i) + " radius " + circles[i].radius + " center " + circles[i].center.x + "," + circles[i].center.x);
        }
    }
    
    public static void outputPops(final double[] pops) {
        for (int i = 1; i < CircleAreaRunner.NUMBER_OF_ZONES; ++i) {
            System.out.println(String.valueOf(i) + " " + CircleAreaRunner.zoneIndex[i] + " pops " + pops[i]);
        }
    }
    
    public static void outputAreas(final double[] areas) {
        for (int i = 1; i < CircleAreaRunner.NUMBER_OF_ZONES; ++i) {
            System.out.println(String.valueOf(i) + " " + CircleAreaRunner.zoneIndex[i] + " area " + areas[i]);
        }
    }
    
    public static void outputPopsAndAreas(final double[] pops, final CircleLayout[] circles) {
        double[] areas = computeArea(circles);
        areas = convertArea1to1Ratio(areas, pops);
        for (int i = 1; i < CircleAreaRunner.NUMBER_OF_ZONES; ++i) {
            System.out.println(String.valueOf(i) + " " + CircleAreaRunner.zoneIndex[i] + " population " + pops[i] + " area " + areas[i]);
        }
    }
    
    public static void outputStructureData(final boolean[] structureCheck) {
        for (int i = 1; i < CircleAreaRunner.NUMBER_OF_ZONES; ++i) {
            System.out.println(String.valueOf(i) + " " + CircleAreaRunner.zoneIndex[i] + " " + structureCheck[i]);
        }
    }
    
    public static Rectangle2D.Double getCircleBBox(final CircleLayout[] circles) {
        double bestBottom = Double.MAX_VALUE;
        double bestLeft = Double.MAX_VALUE;
        double bestTop = Double.MIN_VALUE;
        double bestRight = Double.MIN_VALUE;
        for (int i = 0; i < circles.length; ++i) {
            final CircleLayout circle = circles[i];
            final double left = circle.center.x - circle.radius;
            final double right = circle.center.x + circle.radius;
            final double top = circle.center.y + circle.radius;
            final double bottom = circle.center.y - circle.radius;
            if (left < bestLeft) {
                bestLeft = left;
            }
            if (bottom < bestBottom) {
                bestBottom = bottom;
            }
            if (top > bestTop) {
                bestTop = top;
            }
            if (right > bestRight) {
                bestRight = right;
            }
        }
        final Rectangle2D.Double ret = new Rectangle2D.Double(bestLeft, bestBottom, bestRight - bestLeft, bestTop - bestBottom);
        return ret;
    }
    
    public static void hillClimber(final double[] pops, final CircleLayout[] circles, final boolean update) {
        final Rectangle2D.Double bbox = getCircleBBox(circles);
        final double startMoveDistance = (int)((bbox.width + bbox.height) / 50.0);
        final int numberOfSteps = 20;
        double fitness = computeFitness(pops, circles);
        boolean[] structureCheck = computeStructureCheck(pops, circles);
        double moveDistance = startMoveDistance;
        for (int stepNumber = 1; stepNumber <= numberOfSteps; ++stepNumber) {
            for (int circlei = 0; circlei < circles.length; ++circlei) {
                final CircleLayout circle = circles[circlei];
                int direction = 0;
                while (direction < 8) {
                    final int directionNumber = direction + stepNumber * 5 + circlei * 3;
                    double moveX = 0.0;
                    double moveY = 0.0;
                    if (directionNumber % 8 == 0) {
                        moveX = moveDistance;
                    }
                    if (directionNumber % 8 == 1) {
                        moveX = -moveDistance;
                    }
                    if (directionNumber % 8 == 2) {
                        moveY = moveDistance;
                    }
                    if (directionNumber % 8 == 3) {
                        moveY = -moveDistance;
                    }
                    if (directionNumber % 8 == 4) {
                        moveX = moveDistance;
                        moveY = moveDistance;
                    }
                    if (directionNumber % 8 == 5) {
                        moveX = -moveDistance;
                        moveY = moveDistance;
                    }
                    if (directionNumber % 8 == 6) {
                        moveX = moveDistance;
                        moveY = -moveDistance;
                    }
                    if (directionNumber % 8 == 7) {
                        moveX = -moveDistance;
                        moveY = -moveDistance;
                    }
                    final double oldx = circle.center.x;
                    final double oldy = circle.center.y;
                    final Point2D.Double center = circle.center;
                    center.x += moveX;
                    final Point2D.Double center2 = circle.center;
                    center2.y += moveY;
                    final double newFitness = computeFitness(pops, circles);
                    final boolean[] newStructureCheck = computeStructureCheck(pops, circles);
                    if (isValidMove(structureCheck, newStructureCheck) && newFitness <= fitness) {
                        fitness = newFitness;
                        structureCheck = newStructureCheck;
                        if (update) {
                            updateDisplay();
                            break;
                        }
                        break;
                    }
                    else {
                        circle.center.x = oldx;
                        circle.center.y = oldy;
                        ++direction;
                    }
                }
            }
            for (int circlei = 0; circlei < circles.length; ++circlei) {
                final CircleLayout circle = circles[circlei];
                int direction = 0;
                while (direction < 2) {
                    final int directionNumber = direction + stepNumber + circlei;
                    double change = 0.0;
                    if (directionNumber % 2 == 0) {
                        change = moveDistance;
                    }
                    if (directionNumber % 2 == 1) {
                        change = -moveDistance;
                    }
                    final double oldRadius = circle.radius;
                    final CircleLayout circleLayout = circle;
                    circleLayout.radius += change;
                    final double newFitness2 = computeFitness(pops, circles);
                    final boolean[] newStructureCheck2 = computeStructureCheck(pops, circles);
                    if (isValidMove(structureCheck, newStructureCheck2) && newFitness2 <= fitness) {
                        fitness = newFitness2;
                        structureCheck = newStructureCheck2;
                        if (update) {
                            updateDisplay();
                            break;
                        }
                        break;
                    }
                    else {
                        circle.radius = oldRadius;
                        ++direction;
                    }
                }
            }
            moveDistance -= startMoveDistance / numberOfSteps;
        }
        updateDisplay();
    }
    
    public static void updateDisplay() {
        if (CircleAreaRunner.circlePanel != null) {
            CircleAreaRunner.circlePanel.updatePanel(100);
        }
    }
    
    public static boolean isValidMove(final boolean[] oldStructureCheck, final boolean[] newStructureCheck) {
        int oldCount = 0;
        int newCount = 0;
        for (int i = 1; i < CircleAreaRunner.NUMBER_OF_ZONES; ++i) {
            if (!oldStructureCheck[i]) {
                ++oldCount;
            }
            if (!newStructureCheck[i]) {
                ++newCount;
            }
        }
        return newCount <= oldCount;
    }
    
    public static double getAreaLowNumber(final CircleLayout[] circles) {
        final Rectangle2D.Double bbox = getCircleBBox(circles);
        final double areaLowNumber = bbox.width * bbox.height / CircleAreaRunner.LOW_NUMBER_DIVIDER;
        return areaLowNumber;
    }
    
    public static double getPopulationLowNumber(final double[] pops) {
        double popsTotal = 0.0;
        for (int j = 1; j < CircleAreaRunner.NUMBER_OF_ZONES; ++j) {
            popsTotal += pops[j];
        }
        final double popsLowNumber = popsTotal / CircleAreaRunner.LOW_NUMBER_DIVIDER;
        return popsLowNumber;
    }
    
    public static boolean[] computeStructureCheck(final double[] pops, final CircleLayout[] circles) {
        final double areaLowNumber = getAreaLowNumber(circles);
        final double popsLowNumber = getPopulationLowNumber(pops);
        final double[] area = computeArea(circles);
        final boolean[] ret = new boolean[CircleAreaRunner.NUMBER_OF_ZONES];
        for (int i = 1; i < CircleAreaRunner.NUMBER_OF_ZONES; ++i) {
            if (area[i] < areaLowNumber && pops[i] < popsLowNumber) {
                ret[i] = true;
            }
            else if (area[i] > areaLowNumber && pops[i] > popsLowNumber) {
                ret[i] = true;
            }
            else {
                ret[i] = false;
            }
        }
        return CircleAreaRunner.structureData = ret;
    }
    
    public static double computeFitness(final double[] pops, final CircleLayout[] circles) {
        final double[] area = computeArea(circles);
        final double rangeDifference = computeRangeRelativeZoneSizes(pops, circles, area);
        return rangeDifference;
    }
    
    public static int computeBinaryRelativeZoneSizes(final double[] pops, final CircleLayout[] circles, final double[] area) {
        int wrongSizeCount = 0;
        int count = -1;
        for (int i = 1; i < CircleAreaRunner.NUMBER_OF_ZONES; ++i) {
            for (int j = i + 1; j < CircleAreaRunner.NUMBER_OF_ZONES; ++j) {
                ++count;
                CircleAreaRunner.binaryAreaData[count] = true;
                if (nearlyEqual(pops[i], pops[j], 0.1)) {
                    if (!nearlyEqual(area[i], area[j], 0.1)) {
                        CircleAreaRunner.binaryAreaData[count] = false;
                        ++wrongSizeCount;
                    }
                }
                else if (pops[i] > pops[j]) {
                    if (area[i] < area[j] * 1.1) {
                        CircleAreaRunner.binaryAreaData[count] = false;
                        ++wrongSizeCount;
                    }
                }
                else if (pops[i] < pops[j] && area[i] > area[j] / 1.1) {
                    CircleAreaRunner.binaryAreaData[count] = false;
                    ++wrongSizeCount;
                }
            }
        }
        return wrongSizeCount;
    }
    
    public static double computeRangeRelativeZoneSizes(final double[] pops, final CircleLayout[] circles, final double[] areas) {
        final double areaLowNumber = getAreaLowNumber(circles);
        final double popsLowNumber = getPopulationLowNumber(pops);
        double fitness = 0.0;
        int count = 0;
        for (int i = 1; i < CircleAreaRunner.NUMBER_OF_ZONES; ++i) {
            for (int j = i + 1; j < CircleAreaRunner.NUMBER_OF_ZONES; ++j) {
                int largeIndex = i;
                int smallIndex = j;
                if (pops[i] < pops[j]) {
                    largeIndex = j;
                    smallIndex = i;
                }
                double diffSquared = 0.0;
                final double areaRatio = areas[largeIndex] / areas[smallIndex];
                final double[] ranges = allowableRatios(pops[smallIndex], pops[largeIndex]);
                final double lowRange = ranges[0];
                final double highRange = ranges[1];
                if (pops[i] < popsLowNumber || pops[j] < popsLowNumber) {
                    diffSquared = 0.0;
                }
                else if (areas[i] < areaLowNumber || areas[j] < areaLowNumber) {
                    diffSquared = 0.0;
                }
                else {
                    if (areaRatio < lowRange) {
                        final double diff = lowRange - areaRatio;
                        final double diffRatio = diff / areaRatio;
                        diffSquared = diffRatio * diffRatio;
                    }
                    if (areaRatio > highRange) {
                        final double diff = areaRatio - highRange;
                        final double diffRatio = diff / highRange;
                        diffSquared = diffRatio * diffRatio;
                    }
                }
                if (areaRatio < lowRange || nearlyEqual(pops[i], pops[j], 0.1)) {
                    diffSquared *= 100.0;
                }
                fitness += diffSquared;
                CircleAreaRunner.rangeData[count] = diffSquared;
                CircleAreaRunner.zonePairString[count] = String.valueOf(i) + "," + j + " " + CircleAreaRunner.zoneIndex[i] + "," + CircleAreaRunner.zoneIndex[j];
                ++count;
            }
        }
        return fitness;
    }
    
    public static double[] allowableRatios(final double smallZonePopulation, final double largeZonePopulation) {
        if (smallZonePopulation > largeZonePopulation) {
            System.out.println("ERROR called allowableRatios(" + smallZonePopulation + "," + largeZonePopulation + "). First must be smaller than second.");
        }
        final double lowDifferenceFactor = 0.3;
        final double highDifferenceFactor = 2.0;
        double low = 0.0;
        double high = 0.0;
        if (nearlyEqual(smallZonePopulation, largeZonePopulation, 0.1)) {
            low = 0.9;
            high = 1.1;
        }
        else {
            final double normalizedLarge = largeZonePopulation / smallZonePopulation;
            final double difference = normalizedLarge - 1.0;
            low = 1.0 + difference * lowDifferenceFactor;
            high = 1.0 + difference * highDifferenceFactor;
        }
        final double[] ret = { low, high };
        return ret;
    }
    
    public static boolean nearlyEqual(final double value1, final double value2, final double percent) {
        final double difference = Math.abs(value1 - value2);
        final double total = value1 + value2;
        final double allowedValue = total * percent;
        return difference <= allowedValue;
    }
    
    public static double computeVariance(final double[] pops, final CircleLayout[] circles, final double[] area) {
        double popsTotal = 0.0;
        for (int i = 1; i < CircleAreaRunner.NUMBER_OF_ZONES; ++i) {
            popsTotal += pops[i];
        }
        double areaTotal = 0.0;
        for (int j = 1; j < CircleAreaRunner.NUMBER_OF_ZONES; ++j) {
            areaTotal += area[j];
        }
        double varianceSum = 0.0;
        for (int k = 1; k < CircleAreaRunner.NUMBER_OF_ZONES; ++k) {
            final double difference = pops[k] / popsTotal - area[k] / areaTotal;
            final double variance = difference * difference;
            CircleAreaRunner.zoneVariance[k] = variance;
            varianceSum += variance;
        }
        return varianceSum;
    }
    
    public static double[] computeArea(final CircleLayout[] circles) {
        return CircleGeometry.computeArea(circles);
    }
    
    public static Polygon getPolygonFromArea(final Area a, final int divide) {
        final Polygon p = new Polygon();
        final double[] coords = new double[6];
        double lastX = 0.0;
        double lastY = 0.0;
        int type = -1;
        final PathIterator pi = a.getPathIterator(new AffineTransform());
        while (!pi.isDone()) {
            type = pi.currentSegment(coords);
            pi.next();
            if (type == 0) {
                p.addPoint((int)coords[0], (int)coords[1]);
                lastX = coords[0];
                lastY = coords[1];
            }
            if (type == 1) {
                p.addPoint((int)coords[0], (int)coords[1]);
            }
            if (type == 2) {
                p.addPoint((int)coords[0], (int)coords[1]);
                p.addPoint((int)coords[2], (int)coords[3]);
            }
            if (type == 3) {
                final CubicCurve2D.Double cubicCurve = new CubicCurve2D.Double(lastX, lastY, coords[0], coords[1], coords[2], coords[3], coords[4], coords[5]);
                lastX = coords[4];
                lastY = coords[5];
                final ArrayList curveList = splitCubicCurve(cubicCurve, divide);
                addCubicCurvesToPolygon(p, curveList);
            }
        }
        return p;
    }
    
    public static void addCubicCurvesToPolygon(final Polygon p, final ArrayList curveList) {
        for (final CubicCurve2D.Double c : curveList) {
            p.addPoint((int)c.getCtrlP1().getX(), (int)c.getCtrlP1().getY());
            p.addPoint((int)c.getCtrlP2().getX(), (int)c.getCtrlP2().getY());
            p.addPoint((int)c.getP2().getX(), (int)c.getP2().getY());
        }
    }
    
    public static ArrayList splitCubicCurve(final CubicCurve2D.Double cubicCurve, final int divide) {
        ArrayList splitCurves = new ArrayList();
        splitCurves.add(cubicCurve);
        for (int splitCount = 0; splitCount < divide; ++splitCount) {
            final ArrayList newCurves = new ArrayList();
            for (final CubicCurve2D.Double c : splitCurves) {
                final CubicCurve2D.Double c2 = new CubicCurve2D.Double();
                final CubicCurve2D.Double c3 = new CubicCurve2D.Double();
                c.subdivide(c2, c3);
                newCurves.add(c2);
                newCurves.add(c3);
            }
            splitCurves = newCurves;
        }
        return splitCurves;
    }
    
    public static double getPolygonArea(final Polygon p) {
        if (p.npoints < 2) {
            return 0.0;
        }
        final int[] xs = p.xpoints;
        final int[] ys = p.ypoints;
        int x1 = 0;
        int y1 = 0;
        int area = 0;
        for (int i = 0; i <= p.npoints; ++i) {
            final int x2 = xs[i % p.npoints];
            final int y2 = ys[i % p.npoints];
            area += x1 * y2 - x2 * y1;
            x1 = x2;
            y1 = y2;
        }
        area /= (int)2.0;
        return (area < 0) ? (-area) : area;
    }
    
    public static double[] convertArea1to1Ratio(final double[] areas, final double[] pops) {
        final double[] newAreas = new double[CircleAreaRunner.NUMBER_OF_ZONES];
        double popsTotal = 0.0;
        double areasTotal = 0.0;
        for (int i = 1; i < CircleAreaRunner.NUMBER_OF_ZONES; ++i) {
            popsTotal += pops[i];
            areasTotal += areas[i];
        }
        final double ratio = popsTotal / areasTotal;
        for (int j = 1; j < CircleAreaRunner.NUMBER_OF_ZONES; ++j) {
            newAreas[j] = areas[j] * ratio;
        }
        return newAreas;
    }
    
    public static String showPoints(final Area area) {
        final StringBuffer ret = new StringBuffer("");
        final double[] pathPoint = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
        final PathIterator pi = area.getPathIterator(null);
        while (!pi.isDone()) {
            final int segmentType = pi.currentSegment(pathPoint);
            if (segmentType == 0) {
                ret.append("MOVETO," + pathPoint[0] + "," + pathPoint[1] + " ");
            }
            if (segmentType == 1) {
                ret.append("LINETO," + pathPoint[0] + "," + pathPoint[1] + " ");
            }
            if (segmentType == 4) {
                ret.append("CLOSE," + pathPoint[0] + "," + pathPoint[1] + " ");
            }
            pi.next();
        }
        return ret.toString();
    }
    
    public static Area removeExtraPoints(final Area area) {
        final GeneralPath path = new GeneralPath();
        int type = 0;
        final float[] coords = new float[6];
        float x0 = 0.0f;
        float y0 = 0.0f;
        final PathIterator it = area.getPathIterator(null);
        while (!it.isDone()) {
            type = it.currentSegment(coords);
            final float x2 = coords[0];
            final float y2 = coords[1];
            final float x3 = coords[2];
            final float y3 = coords[3];
            final float x4 = coords[4];
            final float y4 = coords[5];
            if (type == 4) {
                path.closePath();
            }
            else if (type == 3) {
                if (!pointsAreClose(x0, y0, x4, y4)) {
                    path.curveTo(x2, y2, x3, y3, x4, y4);
                }
                x0 = x4;
                y0 = y4;
            }
            else if (type == 1) {
                if (!pointsAreClose(x0, y0, x2, y2)) {
                    path.lineTo(x2, y2);
                }
                x0 = x2;
                y0 = y2;
            }
            else if (type == 0) {
                path.moveTo(x2, y2);
            }
            else if (type == 2) {
                if (!pointsAreClose(x0, y0, x3, y3)) {
                    path.quadTo(x2, y2, x3, y3);
                }
                x0 = x3;
                y0 = y3;
            }
            else if (type == 0) {
                path.setWindingRule(type);
            }
            else if (type == 1) {
                path.setWindingRule(type);
            }
            it.next();
        }
        return new Area(path);
    }
    
    public static boolean pointsAreClose(final float x1, final float y1, final float x2, final float y2) {
        final float dx = x2 - x1;
        final float dy = y2 - y1;
        final float distSq = dx * dx + dy * dy;
        return distSq < 1.5;
    }
}
