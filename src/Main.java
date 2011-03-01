import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class Main 
{
	private static void copyXXMtoXML(String xxmFile, String xmlFile) throws IOException
	{			
		File f1= new File(xxmFile);
		File f2 = new File(xmlFile);					
		InputStream in = new FileInputStream(f1);
		OutputStream out = new FileOutputStream(f2); 		
		byte[] buf = new byte[1024];
		int len = 0;		
		while ((len = in.read(buf)) > 0)
		{
		    out.write(buf, 0, len);			 
		}
		in.close();
		out.close();
	}//end of copyXXMtoXML
	
	public static void displayGeneralInfo(List<State> s)
	{
		System.out.println("********************General Info********************");
		for (int x=0; x<s.size(); x++)
		{
    		if (!s.get(x).getStateName().equalsIgnoreCase("end") || s.get(x).getVariable()!=null)
    		{
    			System.out.println("state: "+s.get(x).getStateName()+" is the starting point of: "+
    					s.get(x).getStartTransitions().toString()+" and the ending point of: "+
    						s.get(x).getEndTransitions().toString());   			
    		}
    		else
    		{
    			System.out.println("state: "+s.get(x).getStateName()+" is the final state");
    		}  		
		}
		System.out.println();
	}
	
	public static void displaySpecification(List<State> s)
	{
		
		for (int x=0; x<s.size(); x++)
		{		
			if (!s.get(x).getStateName().equalsIgnoreCase("end") && !s.get(x).getStateName().equalsIgnoreCase("Error"))
			{
				System.out.println("********************Textual Description for state: "+s.get(x).getStateName()+"******************** ");
		    	System.out.println();
	    		s.get(x).variableCheck();
	    		s.get(x).testCase();
	    		System.out.println();
			}
			if (s.get(x).getStateName().equalsIgnoreCase("end"))
			{
				System.out.println("********************Textual Description for state: "+s.get(x).getStateName()+"******************** ");
				System.out.println("This is the final state ");
				System.out.println();
			}			
		}		
	}
	
	public static void displayParameters(List<State> s)
	{
		System.out.println("********************Parameters********************");		
		for (int x=0; x<s.size(); x++)
		{
			if (s.get(x).getVariable()!=null)
			{
				System.out.print(s.get(x).getVariable()+"? : "+s.get(x).getVariableType()+" ; ");
			}
		}
		System.out.println("\n");
	}
	
	public static void displayTransitions(List<Transition> tr)
	{
		System.out.println("********************Transitions********************");
		for (int x=0; x<tr.size(); x++)
		{
			for (int k=0;k<tr.get(x).getStartStates().size();k++)
			{
				if (tr.get(x).getStartStates().get(k).getGuard()!=null)
				{
					System.out.println("transition: "+tr.get(x).getTransitionName()+" accepts "+tr.get(x).getStartStates().get(k).getVariable()+"?, " +
							"has guard "+tr.get(x).getStartStates().get(k).getGuard()+"("+tr.get(x).getStartStates().get(k).condition()+")");
				}
			}
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws  JDOMException, IOException 
	{
		
		String f = "C:/SHEFFIELD-COM/COM6780-Darwin/workspace/fggf/new_file.xxm";	
		//String f = "C:/SHEFFIELD-COM/COM6780-Darwin/workspace/Darwin/src/new_file.xxm";	
		String ff = "C:/SHEFFIELD-COM/COM6780-Darwin/workspace/sadsdsda/src/new_file.xml";
		copyXXMtoXML( f,  ff);
		
		// lists that will hold state/transition objects
		List<State> states = new LinkedList<State>();
		List<Transition> transitions = new LinkedList<Transition>();		
		
		SAXBuilder builder = new SAXBuilder();
	    Document document = builder.build(new File(ff));	    
	    
	    // root element: <xxm>	    
	    Element root = document.getRootElement();
	    
	    //list that will hold the <state> elements of the XML
	    List childrenState = root.getChildren("state");
	    Iterator i = childrenState.iterator();
	    
	    //list that will hold the <function> elements of the XML
	    List childrenFunction = root.getChildren("function");
	    Iterator k = childrenFunction.iterator();
	   
	    //populate the states list
	    while (i.hasNext()) 	   
	    {	
	    	for (int x=0; x<childrenState.size(); x++)
		    {
			    Element childState = (Element) i.next();
			    states.add(x,new State());
			    states.get(x).setStateName(childState.getAttribute("name").getValue());
			    states.get(x).setStateID(childState.getAttribute("id").getValue());		      
		    }
	    }
	    
	    //populate the transitions list
	    while (k.hasNext()) 	   
	    {	
	    	for (int x=0; x<childrenFunction.size(); x++)
		    {	
	    		Element childFunction = (Element) k.next();
	    		transitions.add(x, new Transition());
	    		transitions.get(x).setTransitionName(childFunction.getAttribute("name").getValue());
	 		    transitions.get(x).setTransitionID(childFunction.getAttribute("id").getValue()); 
	 		    
	 		    // search the states list to find the matching ID's of the start and end states 
	 		    // of each transition
	 		    for (int j=0; j<childrenState.size(); j++)
			     {
		 		    if (states.get(j).getStateID().equals(childFunction.getAttribute("start").getValue()))
		 		    	{	
		 		    		transitions.get(x).setStartStates(states.get(j));		 		    		
		 		    	}
		 		    if (states.get(j).getStateID().equals(childFunction.getAttribute("end").getValue()))
		    		   {	
		 		    		transitions.get(x).setEndStates(states.get(j));
		    		   }    
			    }
		    }
	    }
	    
	    
	    // search through the transitions and populate the starting and  
	    // ending transitions of each state
    	for (int j=0; j<childrenFunction.size(); j++)
    	{
    		for (int x=0; x<childrenState.size(); x++)
    		{
    			for (int g=0;g<transitions.get(j).getStartStates().size();g++)
    			{
	    			if (transitions.get(j).getStartStates().get(g).equals(states.get(x)))	    				
	    			{
	    				states.get(x).setStartTransitions(transitions.get(j));
	    			}
	    			if (transitions.get(j).getEndStates().get(g).equals(states.get(x)))	    				
	    			{
	    				states.get(x).setEndTransitions(transitions.get(j));
	    			}
    			}
    		}
    	}
    	
    	/*
	    states.get(0).setVariable("number1");
	    states.get(0).setVariableType("int");
	    states.get(0).setMaximum(Integer.toString(50));
	    states.get(0).setMinimum(Integer.toString(0));
	    states.get(0).setGuard("ValidNumber");
	    */
	    states.get(0).setVariable("copyID");
	    states.get(0).setVariableType("int");
	    states.get(0).setMaximum(Integer.toString(50));
	    states.get(0).setMinimum(Integer.toString(0));
	    states.get(0).setGuard("ValidCopy");
	    
	    states.get(1).setVariable("ISBN");
	    states.get(1).setVariableType("int");
	    //states.get(0).setMaximum(Integer.toString(50));
	    states.get(1).setMinimum(Integer.toString(0));
	    states.get(1).setGuard("ValidISBN");
	    
	    states.get(2).setVariable("publisher");
	    states.get(2).setVariableType("String");
	    //states.get(0).setMaximum(Integer.toString(50));
	    //states.get(0).setMinimum(Integer.toString(0));
	    //states.get(0).setGuard("ValidISBN");
	    
	    // print
	    displayGeneralInfo(states);	    
	    displayParameters(states);
	    displayTransitions(transitions);
	    displaySpecification(states);
	    
    		 

	}//end of main

}//end of class
