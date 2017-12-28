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
import soot.util.dot.DotGraph;

public class Main {
	public static DotGraph dotIcfg = new DotGraph("");
	public static ArrayList<Unit> visited;
	private static InterproceduralCFG<Unit, SootMethod> icfg;

	public static void main(String[] args) {
		ArrayList<String> argsList = new ArrayList<String>();
		argsList.addAll(Arrays.asList(new String[] {
		"Main",
		"-w",

		"-cp",

		"/home/kshitijgorde/workspace/ICFG/testers/",

		"-pp",

		"-allow-phantom-refs",

		"-process-dir",

		"/home/kshitijgorde/workspace/ICFG/testers/",

		}));
		
		
		
		ArrayList<String> uniqueSystemCalls = new ArrayList<String>();
		
		uniqueSystemCalls.add("com.");
		uniqueSystemCalls.add("java.");
		uniqueSystemCalls.add("%java.");
		uniqueSystemCalls.add("javax.");
		uniqueSystemCalls.add("org.");
		uniqueSystemCalls.add("sun.");
		
		

		Scene.v()
				.setSootClassPath(
						"/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/rt.jar:/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/jce.jar:"
								+ "/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/jsse.jar:/home/kshitijgorde/workspace/ICFG/testers/");
		PackManager.v().getPack("wjtp")
				.add(new Transform("wjtp.ifds", new SceneTransformer() {
					protected void internalTransform(String phaseName,
							@SuppressWarnings("rawtypes") Map options) {

						// System.out.println(b.toString());

						//System.out.println(Scene.v().getClasses());
						System.out.println(Scene.v().hasMainClass());

						Scene.v().loadNecessaryClasses();
						Scene.v().loadBasicClasses();
						// System.out.println(Scene.v().getClasses());
						SootClass mainClass = null;
						SootMethod main_method = null;
						int mainMethodFlag = 0;
						for (SootClass sc : Scene.v().getClasses()) {
							// System.out.println(sc.getName());
							if (sc.getName().equals("Main")) {
								mainClass = sc;
								System.out.println("All methods inside are: ");
								System.out.println(sc.getMethods().toString());

								for (SootMethod methods : sc.getMethods()) {
									System.out.println(methods.getName());
									if (methods.getName().equals("init")) {
										main_method = methods;
										System.out
												.println("Main method found!. Terminating Search!");
										System.out.println(main_method
												.getName());
										mainMethodFlag = 1;
										break;
									}

								}
								if (mainMethodFlag == 1) {
									// indicates already found main method
									System.out
											.println("Exiting Main Class search!");
									break;
								}
								// break the loop

							}
						}
						System.out.println("Setting the Entry Point!");
						ArrayList<SootMethod> entry_points = new ArrayList<SootMethod>();
						entry_points.add(main_method);
						Scene.v().setEntryPoints(entry_points);
						System.out.println("Entry Points are: ");
						System.out.println(Scene.v().getEntryPoints());

						icfg = new JimpleBasedInterproceduralCFG();
						Unit startPoint = null;
						System.out.println(icfg.getStartPointsOf(main_method));
						for (Unit temp : icfg.getStartPointsOf(main_method)) {
							startPoint = temp;
							System.out.println("START POINT SET");
							System.out.println(icfg.getSuccsOf(temp));
							break;
						}

						// System.out.println(icfg.getSuccsOf(startPoint));
						visited = new ArrayList<Unit>();
						
						G.v().out.println("FistICFG" + dotIcfg.DOT_EXTENSION);
						graphTraverse(startPoint);
						
						dotIcfg.plot("FirstICFG");

						// SootMethod temp = main_method;
						//
						// System.out.println("Successors Printing");
						// System.out.println(icfg.getSuccsOf(startPoint)
						// .toString());

						// while (true) {
						// try {
						// System.out
						// .println("Traversing Graph and adding Successors");
						// if (startPoint.branches()) {
						// System.out.println("Branching Detected!");
						// // for (SootMethod sc : )
						// }
						// System.out.println("Successors are: ");
						// System.out.println(icfg.getSuccsOf(startPoint)
						// .toString());
						// Unit succ = icfg.getSuccsOf(startPoint).get(0); //
						// get
						// // first
						// // successor
						//
						// dotIcfg.drawEdge(startPoint.toString(),
						// succ.toString());
						// startPoint = succ;
						// succ = null;
						// } catch (IndexOutOfBoundsException e) {
						// System.out
						// .println("Traversal Completed!...Creating DOT output");
						// break;
						// }
						// }

						
					}
				}));

		args = argsList.toArray(new String[0]);

		soot.Main.main(args);
	}

	public static void graphTraverse(Unit startPoint) {
		List<Unit> currentSuccessors = icfg.getSuccsOf(startPoint);
		
		if (currentSuccessors.size() == 0) {
			System.out.println("Traversal complete");
			return;
		} else {
			for (Unit succ : currentSuccessors) {
				System.out.println("Succesor: " + succ.toString());
				if(!visited.contains(succ)){
					dotIcfg.drawEdge(startPoint.toString(), succ.toString());
					visited.add(succ);
					graphTraverse(succ);
					
				}
				else{
					dotIcfg.drawEdge(startPoint.toString(), succ.toString());
				}
				
				
			}
		}
	}
}