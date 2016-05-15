package graph_filter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.util.StringTokenizer;
import java.net.URI;
import java.util.Calendar;

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

import hadoopComponents.HadoopJob;
import hadoopComponents.hadoopMapper;
import hadoopComponents.hadoopReducer;
import hadoopComponents.HadoopTerminator;

public class FrameworkMain extends Configured implements Tool{
	HadoopJob hJob;
	
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
		Configuration conf = new Configuration();
		String path="logs/"+hJob.getJobName()+ "-" +hJob.getJobName();
		FileSystem fs1 = FileSystem.get(URI.create(path),conf);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fs1.create(new Path(path),true)));
		Calendar startTime = Calendar.getInstance();
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
		Calendar finishTime = Calendar.getInstance();
		bw.write("Total Iterations: "+iterationCount+"\n");
		bw.write("Time Taken: "+(finishTime.getTimeInMillis()-startTime.getTimeInMillis())/1000.0+" seconds\n");
		bw.close();
		return 0;
	}

	public void runMain(String[] args, HadoopJob hJob)
	{
		int res = 0;
		this.hJob = hJob;
		try {
			res = ToolRunner.run(new Configuration(), new FrameworkMain(), args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(res);
	}


}
