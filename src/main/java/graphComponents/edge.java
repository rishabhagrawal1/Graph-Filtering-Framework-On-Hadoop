package graphComponents;

public class edge {
	Integer id;
	float weight;
	Integer source;
	Integer target;
	
	public edge(String edgeStr)
	{
		parseinputRow(edgeStr);
	}

	/*It will process edge in following string representation 
	Id\tweight\tsource\tdestination
	User need to extend this class and override this method in case of a different input format
	*/
	protected void parseinputRow(String edgeStr)
	{
		String[] tokens = edgeStr.split("\t");
		try{
			setId(Integer.parseInt(tokens[0]));
			setWeight(Float.parseFloat(tokens[1]));
			setSource(Integer.parseInt(tokens[2]));
			setTarget(Integer.parseInt(tokens[3]));
		}
		catch(Exception e)
		{
			System.out.println("Not proper Input, override process method");
		}
	}
	
	public String toString()
	{
		return weight+ "\t" + source + "\t" + target;
	}
	
	protected String createOutputRow(Integer weight, Integer soucre, Integer target)
	{
		return Float.toString(weight) + Integer.toString(source) + Integer.toString(target);
	}
	
	private void setId(Integer id)
	{
		this.id = id;
	}
	
	private Integer getId()
	{
		return id;
	}
	
	private void setWeight(float weight)
	{
		this.weight = weight;
	}
	
	private float getWeight()
	{
		return weight;
	}
	
	private void setSource(Integer source)
	{
		this.source = source;
	}
	
	private Integer getSource()
	{
		return source;
	}
	
	private void setTarget(Integer target)
	{
		this.target = target;
	}
	
	private Integer getTarget()
	{
		return target;
	}
}
