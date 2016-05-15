package applications;

import java.util.ArrayList;
import java.util.Iterator;


import graphComponents.Node;

/*Example of extended node class*/

public class NodeExtendedBFS extends Node{
	public static enum Color {
	    WHITE, GRAY, BLACK
	};

	private Color color;
	private int distance;
	
	public NodeExtendedBFS(String nodeStr) {
		
		parseinputRow(nodeStr);
	}
	
    public NodeExtendedBFS(Integer id) {
	    super.id = id;
    }
	
	public void parseinputRow(String nodeStr)
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
			setColor(Color.valueOf(intermediate[2]));
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
	
	public String createOutputRow(ArrayList<Integer> edgeList, ArrayList<Float> weightList, String color)
	{
		String output = "";
		output = super.createOutputRow(edgeList, weightList);
		return (output += "|" + color);
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public void setDistance(int distance)
	{
		this.distance = distance;
	}
	
	public int getDistance()
	{
		return distance;
	}
}
