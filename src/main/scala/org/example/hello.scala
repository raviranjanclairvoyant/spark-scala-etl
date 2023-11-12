package org.example

import org.apache.spark.rdd
import org.apache.spark.sql.SparkSession

object hello{
  def main(args: Array[String]): Unit = {

 println("hello scala is ready")
    val spark = SparkSession.builder()
      .master("yarn")
      .appName("My name is ravi")
      .getOrCreate()
    import spark.implicits._

    val someDF = Seq(
      (8, "bat"),
      (64, "mouse"),
      (-27, "horse")
    ).toDF("number", "word")

    someDF.show()

    someDF.write.mode("overwrite")
      .format("bigquery")
      .option("temporaryGcsBucket","dataproc_ravi_poc")
      .save("playground-375318.test.ravi_test")

    println(spark.sparkContext.appName)
  }
}