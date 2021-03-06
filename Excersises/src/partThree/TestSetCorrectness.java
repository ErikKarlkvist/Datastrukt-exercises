package partThree;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class TestSetCorrectness {

	public Set<Integer> testSet;
	public SimpleSet<Integer> linkedSet; // change

	public boolean isCorrect;
	private int test, nbrOfIntegers, chosenAction, testedInt;

	public TestSetCorrectness(int test, int nbrOfIntegers) {
		this.test = test;
		this.nbrOfIntegers = nbrOfIntegers;
		testSet = new TreeSet<Integer>();
		if (test == 1) {
			linkedSet = new SortedLinkedListSet<Integer>();
		} else {
			linkedSet = new SplayTreeSet<Integer>();
		}
	}

	private void doAction() {
		isCorrect = true;
		Random rand = new Random();
		chosenAction = rand.nextInt(4);
		switch (chosenAction) {
		case 0:
			checkSize();
			break;
		case 1:
			checkAdd();
			break;
		case 2:
			checkRemove();
			break;
		case 3:
			checkContains();
			break;
		}
	}

	private void checkSize() {
		isCorrect = (testSet.size() == linkedSet.size());
	}

	private void checkAdd() {
		Random rand = new Random();
		testedInt = rand.nextInt(nbrOfIntegers);
		isCorrect = (testSet.add(testedInt) == linkedSet.add(testedInt));
	}

	private void checkRemove() {
		Random rand = new Random();
		testedInt = rand.nextInt(nbrOfIntegers);
		isCorrect = (testSet.remove(testedInt) == linkedSet.remove(testedInt));
	}

	private void checkContains() {
		Random rand = new Random();
		testedInt = rand.nextInt(nbrOfIntegers);
		isCorrect = (testSet.contains(testedInt) == linkedSet
				.contains(testedInt));
	}

	public static void main(String args[]) {
		int implementationNbr = Integer.parseInt(args[0]);
		int restarts = Integer.parseInt(args[1]);
		int numberOfActions = Integer.parseInt(args[2]);
		int nbrOfIntegers = Integer.parseInt(args[3]);
		
		int successfulRestarts = 0;
		int finishedActions = 0;
		int restartsCopy = restarts;
		for (; restarts > 0; restarts--, successfulRestarts++) {
			TestSetCorrectness myTest = new TestSetCorrectness(implementationNbr, nbrOfIntegers);
			for (; finishedActions < numberOfActions; finishedActions++) {
				myTest.doAction();
				if (!myTest.isCorrect) {
					switch (myTest.chosenAction) {
					case 0:
						System.out.println("test : " + myTest.testSet.size());
						System.out.println("linked : " + myTest.linkedSet.size());
						System.out.println("Sizes does not match");
						break;
					case 1:
						System.out.println("Adding did not match at "
								+ myTest.testedInt);
						break;
					case 2:
						System.out.println("Remove did not match at "
								+ myTest.testedInt);
						break;
					case 3:
						System.out.println("Contains did not match at "
								+ myTest.testedInt);
						break;
					}
					break;
				}
			}
		}
		String implementation = implementationNbr == 1 ? "SortedLinkedSet" : "SplayTreeSet";
		System.out.println(implementation + " finished with " + successfulRestarts + " restarts and "
				+ finishedActions + " actions at last restart (goal = "
				+ numberOfActions + " actions, " + restartsCopy + " restarts)");
	}
}
