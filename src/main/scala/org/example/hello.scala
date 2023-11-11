package org.example

import org.apache.spark.sql.SparkSession

object hello{
  def main(args: Array[String]): Unit = {

 println("hello scala is ready")
    val spark = SparkSession.builder()
      .master("yarn")
      .appName("My name is ravi")
      .getOrCreate()

    println(spark.sparkContext.appName)
  }
}