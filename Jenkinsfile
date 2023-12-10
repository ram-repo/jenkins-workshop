@Library('common-dev') _

pipeline {
    agent any

    stages {
        stage('Example Stage') {
            steps {
                script {
                    // Using the shared library step
                    basic("Hello World!")
                    // Using the shared library class
                    def myLib = new com.example.sampleclass()
                    myLib.echo("Hello World!")
                }
            }
        }
    }
}
