def call() {

  pipeline {
    agent any
    parameters {
      booleanParam defaultValue: true, description: 'if you need deploy docker appliaction', name: 'deploy'
      choice choices: ['docker', 'application', 'file'], description: 'type of deployment', name: 'Type'
      string defaultValue: 'hello-world', description: 'enter the application name', name: 'applicationName', trim: true
    }
      stages {
          // stage('Checkout') {
          //     steps {
          //         script {
          //             // Checkout the current branch
          //             checkout scm
          //             // Print the current branch
          //         }
          //     }
          // }
        stage('print'){
          steps {
            script {
                echo "this type docker application"
                echo "applicationName is ${applicationName} and Type is ${Type}"
            }
          }
        }
      }
  }
}
