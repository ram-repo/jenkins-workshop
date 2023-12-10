def call() {
  pipeline {
    agent any
    triggers { pollSCM '* * * * *'}
    environment {
        REPO_NAME = sh(script: 'basename -s .git `git config --get remote.origin.url`', returnStdout: true).trim()
        BUILD_NUMBER = "${env.BUILD_NUMBER}"
        BRANCH_NAME = "${env.BRANCH_NAME}"
    }
      stages {
          stage('Checkout') {
              steps {
                  script {
                      // Checkout the current branch
                      checkout scm
                      // Print the current branch
                  }
              }
          }
        stage('docker build'){
          steps {
            script {
              withCredentials([usernamePassword(credentialsId: 'jfro', passwordVariable: 'jfrogpassword', usernameVariable: 'jfroguser')]) {
                sh "docker image build -t spring:1.5.0 ."
                sh "docker tag spring:1.5.0 ramakrishna7899.jfrog.io/dev-docker-docker/spring:1.5.0"
                sh "docker login kfc2024.jfrog.io -u ${jfroguser} -p ${jfrogpasswd}"
                sh "docker push ramakrishna7899.jfrog.io/dev-docker-docke/spring:1.5.0" 
              }
            }
          }
        }
      }
  }

}
