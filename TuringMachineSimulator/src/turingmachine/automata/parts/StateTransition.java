package turingmachine.automata.parts;

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
