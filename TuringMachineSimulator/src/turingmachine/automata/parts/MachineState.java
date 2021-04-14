package turingmachine.automata.parts;

import java.util.ArrayList;
import java.util.List;

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
	private ArrayList<StateTransition> transitions;
	private Boolean accept;
	
	/**
	 * basic machine state constructor
	 * @param name state name
	 * @param transitions state transitions
	 * @param accept true if state is an accept state
	 */
	public MachineState(String name, Boolean accept) {
		this.name = name;
		this.transitions = new ArrayList<StateTransition>();
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
	 * adds a state transition
	 * @param t
	 */
	public void addTransition(StateTransition t) {
		transitions.add(t);
	}
	
	/**
	 * returns true if accept
	 * @return true if accept
	 */
	public Boolean isAccept() {
		return accept;
	}
}
