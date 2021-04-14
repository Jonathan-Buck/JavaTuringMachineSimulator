package turingmachine.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

import turingmachine.automata.TuringMachine;
import turingmachine.automata.parts.Direction;
import turingmachine.automata.parts.MachineState;
import turingmachine.automata.parts.StateTransition;

public class TuringMachineUI {
	public static void main(String args[]) {
		Boolean stop = false;
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a Turing Machine file: ");
		String input = in.next();
		File fileIn = new File(input);
		int lineCount = 0;
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(fileIn);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (!stop) {
			String startState = null;
			HashMap<String, String> acceptStates = new HashMap<String, String>();
			HashMap<String, MachineState> states = new HashMap<String, MachineState>();
			while(fileScanner.hasNextLine()) {
				lineCount++;
				StringTokenizer stateTokens = new StringTokenizer(fileScanner.nextLine());
				if (stateTokens.hasMoreTokens()) {
					String firstTok = stateTokens.nextToken();
					if (!firstTok.equals("#")) {
						if (firstTok.equals("init")) {
							startState = stateTokens.nextToken();
						}
						else if (firstTok.equals("accept")) {
							while (stateTokens.hasMoreTokens()) {
								String key = stateTokens.nextToken();
								states.put(key, new MachineState(key, true));
							}
						}
						else {
							String state = firstTok;
							String nextState = stateTokens.nextToken();
							String charIn = stateTokens.nextToken();
							String charOut = stateTokens.nextToken();
							String tapeDirection = stateTokens.nextToken();
							Direction direction;
							if (tapeDirection.equals("R")) {
								direction = Direction.RIGHT;
							}	
							else if (tapeDirection.equals("L")) {
								direction = Direction.LEFT;
							}
							else {
								direction = Direction.STAY;
							}
							StateTransition transition = new StateTransition(charIn.charAt(0), charOut.charAt(0), nextState, direction);
							if (states.get(state) == null) {
								MachineState tempState = new MachineState(state, false);
								states.put(state, tempState);
							}
							states.get(state).addTransition(transition);
						}
					}
				}
			}
			TuringMachine turingMachine = new TuringMachine(states.get(startState), states);
			System.out.print("enter a string or q for quit: ");
			input = in.next();
			if (input.equals("q")) {
				stop = true;
			}
			else {
				Boolean accept = turingMachine.run(input);
				String tape = turingMachine.getTapeString();
				if (accept) {
					System.out.println("accept " + tape);
				}
				else {
					System.out.println("reject " + tape);
				}
			}
		}
	}
}
