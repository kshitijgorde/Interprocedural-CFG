import java.util.ArrayList;

// 
// Decompiled by Procyon v0.5.30
// 

public class TestPolygonRunTestList
{
    public static void main(final String[] args) {
        final ArrayList<Integer> failures = TestPolygonCode.runTestList();
        Util.debug(1, "******************");
        if (failures.isEmpty()) {
            Util.debug(1, "**** all pass ****");
        }
        else {
            Util.debug(1, "**** failures ****" + failures);
        }
        Util.debug(1, "******************");
    }
}
