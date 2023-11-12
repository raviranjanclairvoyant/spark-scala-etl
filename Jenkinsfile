pipeline {
    agent any
      tools {
        maven 'maven-3.6.3'
      }

    stages {
        stage('New Build') {
            steps {
             echo "The build number is ${env.BUILD_NUMBER}"
             slackSend message: '$BUILD_NUMBER'
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
                     sh '$GCLOUD_PATH/gsutil cp /bitnami/jenkins/home/workspace/spark-scala-etl/target/spark-scala-etl-1.0-SNAPSHOT-jar-with-dependencies.jar gs://dataproc_ravi_poc/spark_jar/'
                    }
                 }
              }
        stage('Running the jar with gcloud') {
             steps {
                    withEnv(['GCLOUD_PATH=/usr/lib/google-cloud-sdk/bin']) {
                       sh '$GCLOUD_PATH/gcloud dataproc jobs submit spark --cluster=cluster-e7b7 --region=us-central1 --class=org.example.hello --jars=gs://dataproc_ravi_poc/spark_jar/spark-scala-etl-1.0-SNAPSHOT-jar-with-dependencies.jar'
                    }
                 }
              }
           }
           post {
                  success {
                      slackSend message: 'success'
                  }
                  failure {
                     slackSend message: 'test failure'
                  }
                  always {
                      slackSend message: 'test done'
                  }
               }
}