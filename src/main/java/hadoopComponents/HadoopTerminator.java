package hadoopComponents;

public class HadoopTerminator {
	Integer maxIteration = 1;
	Integer iteration = 0; 
	void setMaxIteration(Integer maxIteration)
	{
		this.maxIteration = maxIteration;
	}
	void iterationInc()
	{
		if(iteration < maxIteration)
			iteration++;
	}
	void iterationDec()
	{
		if(iteration > 0)
			iteration--;
	}
	
	/*
	 * Override this function for different defination of termination condition
	 */
	protected boolean keepGoing()
	{
		if(iteration < maxIteration)
		{
			return true;
		}
		return false;
	}
}
