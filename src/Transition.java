import java.util.LinkedList;
import java.util.List;


public class Transition 
{
	private String transitionName;
	private String transitionID;
	private List<State> startStates;
	private List<State> endStates;
	
	
	//constructor
	public Transition()
	{
		transitionName = null;
		transitionID = null;
		startStates = new LinkedList<State>();
		endStates = new LinkedList<State>();
	}//end of constructor

	public String toString()
	{
		return this.transitionName;
	}
	
	public List<State> getStartStates() 
	{
		return startStates;
	}
	public void setStartStates(State s) 
	{	
		this.startStates.add(s);
	}
	
	public List<State> getEndStates() 
	{
		return endStates;
	}
	public void setEndStates(State e) 
	{
		this.endStates.add(e);
	}
	
	public String getTransitionName() 
	{
		return transitionName;
	}
	public void setTransitionName(String transitionName) 
	{
		this.transitionName = transitionName;
	}
	
	public String getTransitionID() 
	{
		return transitionID;
	}
	public void setTransitionID(String transitionID) 
	{
		this.transitionID = transitionID;
	}

}//end of class
