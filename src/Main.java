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

	public ArrayList<Unit> visited;
	public InterproceduralCFG<Unit, SootMethod> icfg;
	public ArrayList<String> argsList = new ArrayList<String>();
	public DotGraph dotIcfg;

	public static void main(String[] args) {
		
		Integer i;
		for (i=63;i<64;i++){
			
			Main object = new Main();
			creation:object.constructICFG(args,i);
			
		}
		
		
	}

	public void constructICFG(String[] args,Integer i) {
		//for (Integer i = 0; i < 2; i++) {
			try{
			dotIcfg = new DotGraph("");
			ArrayList<String> argsList = new ArrayList<String>();
			argsList.clear();
			argsList.addAll(Arrays.asList(new String[] {

					"-w",

					"-pp",

					"-allow-phantom-refs",

					"-process-dir",

					"/home/kshitijgorde/workspace/ICFG/testers/" + i.toString()
							+ ".jar",

			}));

			System.out.println("PRINTING THE VALUE OF I");
			System.out.println(i.toString());

			Scene.v()
					.setSootClassPath(
							"/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/rt.jar:/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/jce.jar:"
									+ "/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/jsse.jar:/home/kshitijgorde/workspace/ICFG/testers/"
									+ i.toString() + ".jar");
			
	
			
			
			
			if (PackManager.v().hasPhase("wjtp.ifds")) {
				if (Scene.v().hasMainClass()) {

					System.out.println(Scene.v().hasMainClass());

					// Scene.v().loadNecessaryClasses();
					// Scene.v().loadBasicClasses();
					//

					SootClass mainClass = null;
					SootMethod main_method = null;
					mainClass = Scene.v().getMainClass();
					System.out.println("ALL CLAAAASSSSSSSSSSS");
					System.out.println(Scene.v().getClasses());

					int mainMethodFlag = 0;
					for (SootClass sc : Scene.v().getClasses()) {
						// System.out.println(sc.getName());
						if (sc.getName().equals(mainClass.toString())) {
							mainClass = sc;

							System.out.println("All methods inside are: ");
							System.out.println(sc.getMethods().toString());

							for (SootMethod methods : sc.getMethods()) {
								System.out.println(methods.getName());
								if (methods.getName().equals("main")) {
									main_method = methods;
									System.out
											.println("Main method found!. Terminating Search!");
									System.out.println(main_method.getName());
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

					G.v().out.println(mainClass.toString()
							+ dotIcfg.DOT_EXTENSION);
					graphTraverse(startPoint,icfg);

					dotIcfg.plot(mainClass.toString());
				}
				else{
					System.out.println("Not a malware with Main method...!");
					
				}

			}

			else {
				PackManager.v().getPack("wjtp")
						.add(new Transform("wjtp.ifds", new SceneTransformer() {
							protected void internalTransform(String phaseName,
									@SuppressWarnings("rawtypes") Map options) {

								// System.out.println(b.toString());

								// System.out.println(Scene.v().getClasses());

								// Scene.v().loadClassAndSupport("Main");

								if (Scene.v().hasMainClass()) {

									System.out
											.println(Scene.v().hasMainClass());

									// Scene.v().loadNecessaryClasses();
									// Scene.v().loadBasicClasses();
									//

									SootClass mainClass = null;
									SootMethod main_method = null;
									mainClass = Scene.v().getMainClass();

									int mainMethodFlag = 0;
									for (SootClass sc : Scene.v().getClasses()) {
										// System.out.println(sc.getName());
										if (sc.getName().equals(
												mainClass.toString())) {
											mainClass = sc;

											System.out
													.println("All methods inside are: ");
											System.out.println(sc.getMethods()
													.toString());

											for (SootMethod methods : sc
													.getMethods()) {
												System.out.println(methods
														.getName());
												if (methods.getName().equals(
														"main")) {
													main_method = methods;
													System.out
															.println("Main method found!. Terminating Search!");
													System.out
															.println(main_method
																	.getName());
													mainMethodFlag = 1;
													break;
												}

											}
											if (mainMethodFlag == 1) {
												// indicates already found main
												// method
												System.out
														.println("Exiting Main Class search!");
												break;
											}
											// break the loop

										}
									}
									System.out
											.println("Setting the Entry Point!");
									ArrayList<SootMethod> entry_points = new ArrayList<SootMethod>();
									entry_points.add(main_method);
									Scene.v().setEntryPoints(entry_points);

									System.out.println("Entry Points are: ");
									System.out.println(Scene.v()
											.getEntryPoints());

									icfg = new JimpleBasedInterproceduralCFG();
									Unit startPoint = null;
									System.out.println(icfg
											.getStartPointsOf(main_method));
									for (Unit temp : icfg
											.getStartPointsOf(main_method)) {
										startPoint = temp;
										System.out.println("START POINT SET");
										System.out.println(icfg
												.getSuccsOf(temp));
										break;
									}

									// System.out.println(icfg.getSuccsOf(startPoint));
									visited = new ArrayList<Unit>();

									G.v().out.println(mainClass.toString()
											+ dotIcfg.DOT_EXTENSION);
									graphTraverse(startPoint, icfg);

									dotIcfg.plot(mainClass.toString());
								}
								else{
									System.out.println("Not a malware with main method");
									//G.reset();
									
								}
							

							}
						}));

			}
			
				args = argsList.toArray(new String[0]);
				soot.Main.main(args);
				G.reset();
			

			

			System.out.println("Processing Next Class!");
			}catch(Exception e){
				System.out.println("IN Ecetpsion");
				e.printStackTrace();
				return;
			}
	//	}

	}

	public void graphTraverse(Unit startPoint, InterproceduralCFG<Unit, SootMethod> icfg) {
		List<Unit> currentSuccessors = this.icfg.getSuccsOf(startPoint);

		if (currentSuccessors.size() == 0) {
			System.out.println("Traversal complete");
			return;
		} else {
			for (Unit succ : currentSuccessors) {
				System.out.println("Succesor: " + succ.toString());
				if (!this.visited.contains(succ)) {
					this.dotIcfg.drawEdge(startPoint.toString(), succ.toString());
					this.visited.add(succ);
					graphTraverse(succ, this.icfg);

				} else {
					this.dotIcfg.drawEdge(startPoint.toString(), succ.toString());
				}

			}
		}
	}
}