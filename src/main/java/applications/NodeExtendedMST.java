package applications;

import java.util.ArrayList;

import graphComponents.Node;

public class NodeExtendedMST extends Node{
	String color; 
	public NodeExtendedMST(String nodeStr) {
		
		parseinputRow(nodeStr);
	}
	
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
			setColor(intermediate[2]);
		}
		catch(Exception e)
		{
			System.out.println("Not proper Input, override process method");
		}
	}
	
	public String toString()
	{
		String output = "";
		output = super.toString();
		return (output += "|" + color);
	}
	
	protected String createOutputRow(ArrayList<Integer> edgeList, ArrayList<Float> weightList, String color)
	{
		String output = "";
		output = super.createOutputRow(edgeList, weightList);
		return (output += "|" + color);
	}
	
	private void setColor(String color)
	{
		this.color = color;
	}
	
	private String getColor()
	{
		return color;
	}

}
