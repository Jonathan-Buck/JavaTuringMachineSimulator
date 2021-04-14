package turingmachine.automata;

import java.util.HashMap;
import java.util.List;

import turingmachine.positionallist.PositionalList;

/**
 * Implementation for a simulated turing machine
 * @author Jonathan Buck
 *
 */
public class TuringMachine {

	enum Direction {
		LEFT,
		RIGHT,
		STAY
	}
	
	private MachineState initialState;
	private PositionalList<Character> tape;
	private HashMap<String, MachineState> states;
	
	public TuringMachine(MachineState initialState, HashMap<String, MachineState> states) {
		setInitialState(initialState);
		setStates(states);
		tape = null;
	}

	public Boolean run(String in) {
		Character[] chars = new Character[in.length()];
		for (int i = 0; i < in.length(); i++) {
			chars[i] = in.charAt(i);
		}
		tape = new PositionalList<Character>(chars);
		PositionalList<Character>.PositionalNode<Character> currentChar = tape.front();
		MachineState currentState = initialState;
		Boolean stop = false;
		while(!stop) {
			stop = true;
			for (StateTransition t : currentState.getTransitions()) {
				if (t.getIn().equals(currentChar.getElement())) {
					stop = false;
					currentChar.setElement(t.getOut());
					currentState = states.get(t.nextState);
					if (t.direction == Direction.LEFT) {
						currentChar = currentChar.getPrev();
					}
					else if (t.direction == Direction.RIGHT) {
						currentChar = currentChar.getNext();
					}
					else {
						//Stay in the same position
					}
				}
			}
		}
		return currentState.isAccept();
	}

	/**
	 * @return the tape
	 */
	public PositionalList<Character> getTape() {
		return tape;
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



	/**
	 * Private class that contains turing machine state information:
	 * -State Name
	 * -State transitions
	 * -If state is an accept state
	 * @author Jonathan Buck
	 *
	 */
	public class MachineState {
		private String name;
		private List<StateTransition> transitions;
		private Boolean accept;
		
		/**
		 * basic machine state constructor
		 * @param name state name
		 * @param transitions state transitions
		 * @param accept true if state is an accept state
		 */
		public MachineState(String name, List<StateTransition> transitions, Boolean accept) {
			this.name = name;
			this.transitions = transitions;
			this.accept = accept;
		}
		
		/**
		 * getter for name
		 * @return name
		 */
		public String getName() {
			return name;
		}
		
		/**
		 * getter for transitions
		 * @return transitions
		 */
		public List<StateTransition> getTransitions() {
			return transitions;
		}
		
		/**
		 * returns true if accept
		 * @return true if accept
		 */
		public Boolean isAccept() {
			return accept;
		}
	}
	
	/**
	 * Private class that contains turing machine transition information:
	 * -State transitioned to
	 * -input character
	 * -output character
	 * -right or left on tape
	 * @author Jonathan Buck
	 *
	 */
	public class StateTransition {
		private Character in;
		private Character out;
		private String nextState;
		private Direction direction;
		
		/**
		 * State transition constructor
		 * @param in input character to take transition
		 * @param out character outputted if transition is taken
		 * @param next next state
		 * @param direction direction to move on tape
		 */
		public StateTransition(Character in, Character out, String nextState, Direction direction) {
			this.in = in;
			this.out = out;
			this.nextState = nextState;
			this.direction = direction;
		}

		/**
		 * @return the in
		 */
		public Character getIn() {
			return in;
		}

		/**
		 * @return the out
		 */
		public Character getOut() {
			return out;
		}

		/**
		 * @return the nextState
		 */
		public String getNextState() {
			return nextState;
		}

		/**
		 * @return the direction
		 */
		public Direction getDirection() {
			return direction;
		}
	}
}
