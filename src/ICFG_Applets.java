import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import heros.InterproceduralCFG;
import java.util.Map;
import soot.G;
import soot.PackManager;
import soot.Scene;
import soot.SceneTransformer;
import soot.SootClass;
import soot.SootMethod;
import soot.Transform;
import soot.Unit;
import soot.jimple.toolkits.ide.icfg.JimpleBasedInterproceduralCFG;
import soot.options.Options;
import soot.util.dot.DotGraph;

public class ICFG_Applets {

	public ArrayList<Unit> visited;
	public InterproceduralCFG<Unit, SootMethod> icfg;
	public ArrayList<String> argsList = new ArrayList<String>();
	public DotGraph dotIcfg;

	public static void main(String[] args) {
		String class_name_having_init = args[0];
		String icfgGraphName = args[1];	
		String jarFileName = args[2];
		
		ArrayList<String> argsList = new ArrayList<String>();
		argsList.clear();
		argsList.addAll(Arrays.asList(new String[] {

		"-w",

		"-pp",

		"-allow-phantom-refs",

		"-process-dir",

		"/home/kshitijgorde/workspace/ICFG/testers/"+"jarFileName",

		}));
		String[] args2 = argsList.toArray(new String[0]);

		Options.v().parse(args2);

		SootClass c = Scene.v().forceResolve(class_name_having_init, SootClass.BODIES);
		c.setApplicationClass();

		Scene.v().loadNecessaryClasses();

		SootMethod main_method = c.getMethodByName("init");
		List entryPoints = new ArrayList();
		entryPoints.add(main_method);
		Scene.v().setEntryPoints(entryPoints);
		PackManager.v().runPacks();
		ICFG_Applets icfgObject = new ICFG_Applets();
		icfgObject.constructICFG(main_method, icfgGraphName);

	}

	public void constructICFG(SootMethod main_method, String icfgGraphName) {

		try {

			Scene.v()
					.setSootClassPath(
							"/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/rt.jar:/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/jce.jar:"
									+ "/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/jsse.jar:/home/kshitijgorde/workspace/ICFG/testers/");

			dotIcfg = new DotGraph("");

			icfg = new JimpleBasedInterproceduralCFG();
			System.out.println("Custom Entry Points are: ");
			System.out.println(Scene.v().getEntryPoints());
			System.out.println("Constructing the ICFG");

			icfg = new JimpleBasedInterproceduralCFG();

			Unit startPoint = null;
			for (Unit temp : icfg.getStartPointsOf(main_method)) {
				startPoint = temp;
				System.out.println("Start Point Set!");
				break;
			}
			visited = new ArrayList<Unit>();
			graphTraverse(startPoint, icfg);
			G.v().out.println(icfgGraphName.toString() + dotIcfg.DOT_EXTENSION);
			dotIcfg.plot(icfgGraphName.toString());
		} catch (Exception e) {
			System.out.println("Exception occured!");
			e.printStackTrace();
		}

	}

	public void graphTraverse(Unit startPoint,
			InterproceduralCFG<Unit, SootMethod> icfg) {
		List<Unit> currentSuccessors = this.icfg.getSuccsOf(startPoint);
		System.out.println("Visited Size: "+this.visited.size());
		if (currentSuccessors.size() == 0) {
			System.out.println("Traversal complete");
			return;
		} else {
			for (Unit succ : currentSuccessors) {
				System.out.println("Succesor: " + succ.toString());
				if (!this.visited.contains(succ)) {
					this.dotIcfg.drawEdge(startPoint.toString(),
							succ.toString());
					this.visited.add(succ);
					graphTraverse(succ, this.icfg);

				}

			}
		}
	}
}