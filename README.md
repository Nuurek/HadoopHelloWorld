# MBP - Hadoop

Install docker, pull image and run it binding `/home` directory.
```
zypper install docker
docker -v
docker pull sequenceiq/hadoop-docker:2.7.1
docker run -v /home:/home -it sequenceiq/hadoop-docker:2.7.1 /etc/bootstrap.sh -bash
```

Compile Java class
```
cd /home/wordcount
export JAVA_HOME=/usr/java/default
export PATH=${JAVA_HOME}/bin:${PATH}
export HADOOP_CLASSPATH=${JAVA_HOME}/lib/tools.jar
$HADOOP_PREFIX/bin/hadoop com.sun.tools.javac.Main WordCount.java
jar cf WordCount.jar WordCount*.class
```

Copy file to HDFS.
```
bin/hdfs dfs -mkdir wordcount
bin/hdfs dfs -mkdir wordcount/input
bin/hdfs dfs -put /home/wordcount/input/book.txt wordcount/input/book.txt
bin/hdfs dfs -cat wordcount/input/book.txt
bin/hdfs dfs -mkdir wordcount/java-input
```

Move into directory with Hadoop binaries.
```
cd $HADOOP_PREFIX
```

Start Java Hadoop job.
```
bin/hadoop jar /home/wordcount/WordCount.jar WordCount wordcount/input wordcount/java-output
```

Check Java Hadoop job output.
```
bin/hdfs dfs -ls wordcount/java-output
bin/hdfs dfs -cat wordcount/java-output/part-r-00000
```

Start Python Hadoop job.
```
bin/hadoop jar share/hadoop/tools/lib/hadoop-streaming-2.7.1.jar -mapper /home/wordcount/mapper.py -reducer /home/wordcount/reducer.py -input wordcount/input/book.txt -output wordcount/python-output
```

Check Java Hadoop job output.
```
bin/hdfs dfs -ls wordcount/python-output
bin/hdfs dfs -cat wordcount/python-output/part-00000
```

Check configuration files.
```
cat etc/hadoop/core-site.xml
cat etc/hadoop/hdfs-site.xml
cat etc/hadoop/slaves
```
