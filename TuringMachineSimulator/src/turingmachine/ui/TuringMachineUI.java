package turingmachine.ui;

import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

import turingmachine.automata.TuringMachine.MachineState;

public class TuringMachineUI {
	public static void main() {
		Boolean stop = false;
		while (!stop) {
			Scanner in = new Scanner(System.in);
			System.out.println("Enter a Turing Machine file:");
			String input = in.next();
			int lineCount = 0;
			Scanner fileScanner = new Scanner(input);
			String startState;
			HashMap<String, String> acceptStates = new HashMap<String, String>();
			HashMap<String, MachineState> states = new HashMap<String, MachineState>();
			while(fileScanner.hasNextLine()) {
				lineCount++;
				StringTokenizer stateTokens = new StringTokenizer(fileScanner.nextLine());
				if ()
			}
		}
	}
}
