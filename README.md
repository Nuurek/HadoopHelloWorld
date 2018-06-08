# MBP - Hadoop

Install docker, pull image and run it binding `/home` directory.
```
zypper install docker
docker -v
docker pull sequenceiq/hadoop-docker:2.7.1
docker run -v /home:/home -it sequenceiq/hadoop-docker:2.7.1 /etc/bootstrap.sh -bash
```

Move into directory with Hadoop binaries.
```
cd $HADOOP_PREFIX
```

Copy file from to HDFS.
```
bin/hdfs dfs -mkdir wordcount
bin/hdfs dfs -put /home/book.txt wordcount/book.txt
bin/hdfs dfs -cat wordcount.txt
```

Start Hadoop job.
```
bin/hadoop jar share/hadoop/tools/lib/hadoop-streaming-2.7.1.jar -mapper /home/mapper.py -reducer /home/reducer.py -input wordcount/book.txt -output output
```

Check output.
```
bin/hdfs dfs -ls output
bin/hdfs dfs -cat output/part-00000
```
