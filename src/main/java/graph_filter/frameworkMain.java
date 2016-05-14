package graph_filter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.conf.Configured;

import hadoopComponents.hadoopJob;
import hadoopComponents.hadoopMapper;
import hadoopComponents.hadoopReducer;
import hadoopComponents.hadoopTerminator;

public class frameworkMain extends Configured implements Tool{
	hadoopJob hJob;

	public int run(String[] args) throws Exception {
		String jobname = args[0];
		String input_path = args[1];
		String output_path = args[2];
		String final_output_path;

		int iterationCount = 0;
		int numMappers = 1;
		int numReducers = 1;

		Job job;
		FileSystem fs = FileSystem.get(new Configuration());
		try{
			/*
			 * Reflection to get if termination condition met 
			 */
			Object termination = hJob.getTerminatorValueClass().newInstance(); // invoke empty constructor
			String methodName = "keepGoing";
			Method getNameMethod = termination.getClass().getMethod(methodName);
			Boolean b = (Boolean)getNameMethod.invoke(termination);
			while (b.booleanValue()) {
				//DeleteAllFiles.deleteFile(user,"tmp", jobname + "-" + iterationCount);	
				output_path = "/tmp/" + jobname + "-" + (iterationCount + 1);
				//If need to set mappers and reducers here
				hJob.setNumReducers(numReducers);
				hJob.setNumMappers(numMappers);
				//get Job
				job = hJob.getJob(jobname);
				//set input and output path
				FileInputFormat.addInputPath(job, new Path(input_path));
				FileOutputFormat.setOutputPath(job, new Path(output_path));
				
				job.waitForCompletion(true);
	
				input_path = output_path;
				iterationCount++;
			}
		}catch(Exception e){
			e.printStackTrace();
		} 
		return 0;
	}

	void runMain(String[] args)
	{
		int res = 0;
		try {
			res = ToolRunner.run(new Configuration(), new frameworkMain(), args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(res);
	}


}
