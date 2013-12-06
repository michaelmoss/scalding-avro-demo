package com.mmoss.scalding

import org.apache.hadoop

// Scalding

import com.twitter.scalding.Tool


object JobRunner {
  def main(args: Array[String]) {
    hadoop.util.ToolRunner.run(new hadoop.conf.Configuration, new Tool, args);
  }
}