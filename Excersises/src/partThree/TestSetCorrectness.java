package partThree;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TestSetCorrectness {
	
	private Set<Integer> testSet;
	private SimpleSet<Integer> linkedSet;
	
	public boolean isCorrect;
	private int test, nbrOfIntegers, chosenAction, testedInt;
	public TestSetCorrectness(int test, int nbrOfIntegers){
		this.test = test;
		this.nbrOfIntegers = nbrOfIntegers;
		linkedSet = new SortedLinkedListSet<Integer>();
		if(test == 1){
			testSet = new HashSet<Integer>();	
		} else {
			
		}
	}
	
	private void doAction(){
		isCorrect = true;
		Random rand = new Random();
		chosenAction = rand.nextInt(3);
		switch (chosenAction){
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
		isCorrect = (testSet.add(testedInt) && linkedSet.add(testedInt));
	}

	private void checkRemove() {
		Random rand = new Random();
		testedInt = rand.nextInt(nbrOfIntegers);
		isCorrect = (testSet.remove(testedInt) && linkedSet.remove(testedInt));
	}

	private void checkContains() {
		Random rand = new Random();
		testedInt = rand.nextInt(nbrOfIntegers);
		isCorrect = (testSet.contains(testedInt) && linkedSet.contains(testedInt));
	}
	
	public static void main(String args[]){
		int restarts = 10;
		int numberOfActions = 100;
		TestSetCorrectness myTest = new TestSetCorrectness(1,100);
		for(int i = 0; i < numberOfActions; i++){
			myTest.doAction();
			if(!myTest.isCorrect){
				switch(myTest.chosenAction){
				case 0:
					System.out.println("Sizes does not match");
					break;
				case 1:
					System.out.println("Adding did not match at " + myTest.testedInt);
					break;
				case 2:
					System.out.println("Remove did not match at " + myTest.testedInt);
					break;
				case 3:
					System.out.println("Contains did not match at " + myTest.testedInt);
					break;
				}
				//break;
			}
		}
	}
}
