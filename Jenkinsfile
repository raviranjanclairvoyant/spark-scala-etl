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

        stage('Run gcloud') {
            steps {
                  withEnv(['GCLOUD_PATH=/usr/lib/google-cloud-sdk/bin']) {
                    sh '$GCLOUD_PATH/gcloud --version'
                    }
                 }
              }
           }
}