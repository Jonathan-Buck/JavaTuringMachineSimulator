package turingmachine.automata;

import java.util.HashMap;

import turingmachine.automata.parts.Direction;
import turingmachine.automata.parts.MachineState;
import turingmachine.automata.parts.StateTransition;
import turingmachine.positionallist.PositionalList;

/**
 * Implementation for a simulated turing machine
 * @author Jonathan Buck
 *
 */
public class TuringMachine {

	
	
	private MachineState initialState;
	private PositionalList tape;
	private HashMap<String, MachineState> states;
	
	public TuringMachine(MachineState initialState, HashMap<String, MachineState> states) {
		setInitialState(initialState);
		setStates(states);
		tape = null;
	}

	public Boolean run(String in) {
		
		char[] chars = new char[in.length()];
		for (int i = 0; i < in.length(); i++) {
			chars[i] = in.charAt(i);
		}
		tape = new PositionalList(chars);
		PositionalList.PositionalNode currentChar = tape.front().getNext();
		MachineState currentState = initialState;
		Boolean stop = false;
		while(!stop) {
			stop = true;
			for (StateTransition t : currentState.getTransitions()) {
				Boolean endFor = false;
				
				if (t.getIn() == currentChar.getElement()) {
					endFor = true;
					stop = false;
					currentChar.setElement(t.getOut());
					currentState = states.get(t.getNextState());
					if (t.getDirection() == Direction.LEFT) {
						currentChar = currentChar.getPrev();
					}
					else if (t.getDirection() == Direction.RIGHT) {
						currentChar = currentChar.getNext();
					}
					else {
						//Stay in the same position
					}
				}
				if (endFor) {
					break;
				}
			}
		}
		return currentState.isAccept();
	}

	/**
	 * @return the tape
	 */
	public String getTapeString() {
		StringBuilder tapeOut = new StringBuilder();
		PositionalList.PositionalNode node = tape.front();
		node = node.getNext();
		while (!node.equals(tape.tail)) {
			tapeOut.append(node.getElement());
			node = node.getNext();
		}
		return tapeOut.toString();
	}

	/**
	 * @param initialState the initialstate to set
	 */
	private void setInitialState(MachineState initialState) {
		this.initialState = initialState;
	}

	/**
	 * @param states the states to set
	 */
	public void setStates(HashMap<String, MachineState> states) {
		this.states = states;
	}
	
}
