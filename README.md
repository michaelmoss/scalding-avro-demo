# Scalding Avro Demo

## Introduction

This project demonstrates how to get started with Avro on Scalding.

Portions of this project was derived from: https://github.com/snowplow/scalding-example-project

## Building

Assuming you already have SBT installed:

    $ git clone git://github.com/michaelmoss/scalding-avro-demo.git
    $ cd scalding-avro-demo
    $ sbt assembly

The 'fat jar' is now available as:

    target/scalding-avro-demo-1.0.jar

## Running on Amazon EMR

### Prepare

Assuming you have already assembled the jarfile (see above), now upload the jar to Amazon S3.

Next, upload the data file [`resources/twitter.avro`] [twitter-avro] to S3.

### Run

Finally, you are ready to run this job using the [Amazon Ruby EMR client] [emr-client]:

    $ elastic-mapreduce --create --name "scalding-avro-demo" \
      --jar s3n://{{JAR_BUCKET}}/scalding-avro-demo-1.0.jar \
      --arg com.mmoss.scalding.jobs.AvroWordCountJob \
      --arg --hdfs \
      --arg --input --arg s3n://{{IN_BUCKET}}/twitter.avro \
      --arg --output --arg s3n://{{OUT_BUCKET}}/results

Replace `{{JAR_BUCKET}}`, `{{IN_BUCKET}}` and `{{OUT_BUCKET}}` with the appropriate paths.

### Inspect

Once the output has completed, you should see a folder structure like this in your output bucket:

     results
     |
     +- _SUCCESS
     +- part-00000

Download the `part-00000` file and check that it contains:

	goodbye	1
	hello	1
	world	2

## Running on your own Hadoop cluster

If you are trying to run this on a non-Amazon EMR environment, you may need to edit:

    project/BuildSettings.scala

And comment out the Hadoop jar exclusions:

    // "hadoop-core-0.20.2.jar", // Provided by Amazon EMR. Delete this line if you're not on EMR
    // "hadoop-tools-0.20.2.jar" // "

## Next steps

Fork this project and adapt it into your own custom Scalding job.

To invoke/schedule your Scalding job on EMR, check out:

* [Spark Plug] [spark-plug] for Scala
* [Elasticity] [elasticity] for Ruby
* [Boto] [boto] for Python