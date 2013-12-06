package com.mmoss.scalding.jobs

import com.twitter.scalding._
import com.miguno.avro._
import com.twitter.scalding.avro._

class AvroWordCountJob(args: Args) extends Job(args) {


  val output = args.getOrElse("output", null)

  val results = PackedAvroSource[twitter_schema](args("input"))
    .flatMap('twitter_schema -> 'word) {
    tweet: twitter_schema => tokenize(tweet.getTweet)
  }
    .groupBy('word) {
    _.size
  }


  if (output != null) {
    results.write(Tsv(output))
  }
  else {
    results.debug.write(NullSource)
  }

  def tokenize(text: String): Array[String] = {
    text.toLowerCase.replaceAll("[^a-zA-Z0-9\\s]", "").split("\\s+")
  }
}
