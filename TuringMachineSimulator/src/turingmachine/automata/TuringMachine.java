package turingmachine.automata;

import java.util.HashMap;
import java.util.List;

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
			//System.out.println(stateToString(currentState));
			//System.out.println(currentState.getName() + " " + currentState.getTransitions().size());
			for (StateTransition t : currentState.getTransitions()) {
				Boolean endFor = false;
				//System.out.println(t.getIn() + " " + t.getOut() + " " + t.getNextState());
				//System.out.println(currentState.getName() + " " + currentChar.getElement() + " " + t.getIn() + " " + (t.getIn() == currentChar.getElement()));
				
				if (t.getIn() == currentChar.getElement()) {
					endFor = true;
					//System.out.print(currentState.getName());
					//System.out.print(" " + t.getIn() + " " + currentChar.getElement());
					//System.out.println(" " + t.getNextState());
					stop = false;
					currentChar.setElement(t.getOut());
					//System.out.println(currentState.getName());
					//System.out.println(t.getNextState());
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
		//System.out.println(currentState.getName());
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
	
	private String stateToString(MachineState state) {
		String out = state.getName();
		for (StateTransition t : state.getTransitions()) {
			out = out + " " + t.getNextState() + " " + t.getIn() + " "+ t.getOut() + " | ";
		}
		return  out;
	}
}
