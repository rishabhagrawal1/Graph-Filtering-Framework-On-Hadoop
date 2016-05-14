package graphComponents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class node {
	Integer id;
	ArrayList<Integer> edgeList;
	ArrayList<Float> weightList;
	
	public node()
	{
		
	}
	public node(String nodeStr)
	{
		edgeList = new ArrayList();
		weightList = new ArrayList();
		parseinputRow(nodeStr);
	}

	/*It will process edge in following string representation 
	Id\tdstNode1,dstNode2|weight1,weight2
	User need to extend this class and override this method in case of a different input format
	*/
	protected void parseinputRow(String nodeStr)
	{
		String[] tokens = nodeStr.split("\t");
		try{
			setId(Integer.parseInt(tokens[0]));
			String[] intermediate = tokens[1].split("|");
			String[] dstNodes = intermediate[0].split(",");
			String[] weights = intermediate[1].split(",");
			for(String node : dstNodes)
			{
				getEdgeList().add(Integer.parseInt(node));
			}
			for(String weight : weights)
			{
				getWeightList().add(Float.parseFloat(weight));
			}
		}
		catch(Exception e)
		{
			System.out.println("Not proper Input, override process method");
		}
	}
	
	public String toString()
	{
		String output = "";
		Iterator<Integer> itrEdge =  edgeList.iterator();
		while(itrEdge.hasNext())
		{
			output += Integer.toString(itrEdge.next());
			if(itrEdge.hasNext())
				output += ",";
		}
		output += "|";
		Iterator<Float> itrWeight =  weightList.iterator();
		while(itrWeight.hasNext())
		{
			output += Float.toString(itrWeight.next());
			if(itrWeight.hasNext())
				output += ",";
		}
		return output;
	}
	
	protected String createOutputRow(ArrayList<Integer> edgeList, ArrayList<Float> weightList)
	{
		String output = "";
		Iterator<Integer> itrEdge =  edgeList.iterator();
		while(itrEdge.hasNext())
		{
			output += Integer.toString(itrEdge.next());
			if(itrEdge.hasNext())
				output += ",";
		}
		output += "|";
		Iterator<Float> itrWeight =  weightList.iterator();
		while(itrWeight.hasNext())
		{
			output += Float.toString(itrWeight.next());
			if(itrWeight.hasNext())
				output += ",";
		}
		return output;
	}
	
	protected void setId(Integer id)
	{
		this.id = id;
	}
	
	protected Integer getId()
	{
		return id;
	}
	
	protected void setEdgeList(ArrayList<Integer> edgeList)
	{
		this.edgeList = edgeList;
	}
	
	protected ArrayList<Integer> getEdgeList()
	{
		return edgeList;
	}
	
	protected void setWeightList(ArrayList<Float> weightList)
	{
		this.weightList = weightList;
	}
	
	protected ArrayList<Float> getWeightList()
	{
		return weightList;
	}
}
