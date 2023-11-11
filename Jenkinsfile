pipeline {
    agent any
      tools {
        maven 'maven-3.6.3'
      }

    stages {
        stage('New Build') {
            steps {
              sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }

        stage('Moving data to GCS BUCKET') {
            steps {
                  withEnv(['GCLOUD_PATH=/usr/lib/google-cloud-sdk/bin']) {
                    sh '$GCLOUD_PATH/gsutil cp /bitnami/jenkins/home/workspace/spark-scala-etl/target/spark-scala-etl-1.0-SNAPSHOT.jar gs://dataproc_ravi_poc/spark_jar/'
                    }
                 }
              }
           }
}