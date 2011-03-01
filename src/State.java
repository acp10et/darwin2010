import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;


public class State 
{
	
	private String stateName;
	private String stateID;
	private String variable;
	private String variableType;
	private String minimum;
	private String maximum;
	private String equals;
	private String guard;
	//private boolean unique;
	private String collection;
	//private boolean errorState;
	//private boolean finalState;
	private List<Transition> startTransitions; 
	private List<Transition> endTransitions; 
	
	
	
	
	public State()
	{
		this.stateName = null;
		//errorState = false;
		//finalState = false;
		this.stateID = null;
		this.variable = null;
		this.variableType = null;
		this.minimum = null;
		this.maximum = null;
		this.equals = null;
		this.startTransitions = new LinkedList<Transition>();
		this.endTransitions = new LinkedList<Transition>();
		
		this.collection = null;
		
		
	}

	
	public void testCase()
	{
		if (this.minimum!=null)
		{
			System.out.println("*Boundary checking for minimum value*");
			System.out.println("test case: "+this.variable+" = "+(Integer.parseInt(this.minimum)-1));
			System.out.println("test case: "+this.variable+" = "+(Integer.parseInt(this.minimum)));
			System.out.println("test case: "+this.variable+" = "+(Integer.parseInt(this.minimum)+1));
			System.out.println();
		}
		if (this.maximum!=null)
		{
			System.out.println("*Boundary checking for maximum value*");
			System.out.println("test case: "+this.variable+" = "+(Integer.parseInt(this.maximum)+1));
			System.out.println("test case: "+this.variable+" = "+(Integer.parseInt(this.maximum)));	
			System.out.println("test case: "+this.variable+" = "+(Integer.parseInt(this.maximum)-1));
			System.out.println();
		}		
	}
	
	public String condition()
	{
		if (this.minimum!=null)
		{
			if (this.maximum!=null)
			{				
				return this.minimum+"<"+this.variable+"<"+this.maximum;
			}else
			{			
				return this.variable+">"+this.minimum;
			}		
		}else
		{
			if (this.maximum!=null)
			{
				return this.variable+"<"+this.maximum;
			}
		}
		return "";					
	}
	
	public void variableCheck()
	{
		char isMemberOf = '\u2208';
		char z = '\u0291';
		
		if (this.variable!=null && this.variableType!=null)
		{
			System.out.println("*Variable type checking*");	
			System.out.println("check if variable: "+this.variable+" is of type: "+this.variableType+" *** "+this.variable+" "+isMemberOf+" "+this.variableType);
			//JOptionPane.showMessageDialog(null, this.variable+" "+isMemberOf+" "+this.variableType);
			System.out.println("You should attempt to input invalid variable types to check system behavior ");
			System.out.println();
			
			if (this.minimum!=null)
			{
				if (this.maximum!=null)
				{
					System.out.println("condition: "+this.minimum+"<"+this.variable+"<"+this.maximum+" must be met " +
							"*** {"+this.variable+":"+this.variableType+"|"+this.variable+" "+isMemberOf+" "+(Integer.parseInt(this.minimum)+1)+"..."+(Integer.parseInt(this.maximum)-1)+"}");
					System.out.println();
				}else
				{
					System.out.println("condition: "+this.variable+">"+this.minimum+" must be met *** {"+this.variable+":"+this.variableType+"|"+this.variable+">"+this.minimum+"}");
					System.out.println();
				}		
			}else
			{
				if (this.maximum!=null)
				{
					System.out.println("condition: "+this.variable+"<"+this.maximum+" must be met *** {"+this.variable+":"+this.variableType+"|"+this.variable+"<"+this.maximum+"}");
					System.out.println();
				}
			}			
		}
	}
	
	public String toString()
	{
		return stateName;
	}
	
	
	public String getGuard() {
		return guard;
	}


	public void setGuard(String guard) {
		this.guard = guard;
	}


	public List<Transition> getEndTransitions()
	{ 
		return endTransitions; 
	}
	public void setEndTransitions(Transition tr)
	{ 
		this.endTransitions.add(tr); 
	}
	
	public List<Transition> getStartTransitions() 
	{		
		return startTransitions;
	}
	public void setStartTransitions(Transition tr) {
		this.startTransitions.add(tr);
	}

	public String getStateName() 
	{
		return stateName;
	}
	public void setStateName(String stateName) 
	{
		this.stateName = stateName;
	}
	
	public String getStateID() 
	{
		return stateID;
	}
	public void setStateID(String stateID) 
	{
		this.stateID = stateID;
	}
	
	public String getVariable() 
	{
		return variable;
	}
	public void setVariable(String variable) 
	{
		this.variable = variable;
	}
	
	public String getVariableType() 
	{
		return variableType;
	}
	public void setVariableType(String variableType) 
	{
		this.variableType = variableType;
	}
	
	public String getMinimum() 
	{
		return minimum;
	}
	public void setMinimum(String minimum) 
	{
		this.minimum = minimum;
	}
	
	public String getMaximum() 
	{
		return maximum;
	}
	public void setMaximum(String maximum) 
	{
		this.maximum = maximum;
	}
	
	public String getEquals() 
	{
		return equals;
	}
	public void setEquals(String equals) 
	{
		this.equals = equals;
	}
	
}//end of class
