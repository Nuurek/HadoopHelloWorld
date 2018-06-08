package hadoop;

import java.util.*;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;

public class Sort {

  public static class E_EMapper extends MapReduceBase implements
    Mapper<LongWritable, Text, Text, Text> {

      public void map(LongWritable key,
                      Text value,
                      OutputCollector<Text, Text> output,
                      Reporter reporter) throws IOException {
        // Do something.
        
        output.collect(k, v);
      }

    }

  public static class E_EReduce extends MapReduceBase implements
    Reducer<Text, Text, Text, Text> {

      public void reduce(Text key,
                         Iterator <Text> values,
                         OutputCollector<Text, Text> output,
                         Reporter reporter) throws IOException {

				// Do something.
				
        output.collect(k, v);
      }

    }

  public static void main(String args[]) throws Exception {
    JobConf conf = new JobConf(Sort.class);

    conf.setJobName("Sort_Numbers");
    conf.setOutputKeyClass(Text.class);
    conf.setOutputValueClass(Text.class);
    conf.setMapperClass(E_EMapper.class);
    conf.setCombinerClass(E_EReduce.class);
    conf.setReducerClass(E_EReduce.class);
    conf.setInputFormat(TextInputFormat.class);
    conf.setOutputFormat(TextOutputFormat.class);

    FileInputFormat.setInputPaths(conf, new Path(args[0]));
    FileOutputFormat.setOutputPath(conf, new Path(args[1]));

    JobClient.runJob(conf);
  }
}
