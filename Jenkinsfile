pipeline {
    agent any

    tools {
        maven 'Maven3'   // Manage Jenkins > Tools mein jo naam hai wahi likho
        jdk 'JDK8'       // project JavaSE-1.8 hai
    }

    parameters {
        string(name: 'SUITE', defaultValue: 'testng.xml', description: 'TestNG suite file')
    }

    options {
        timestamps()
        timeout(time: 30, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile -DskipTests'
            }
        }

        stage('Run API Tests') {
            steps {
                bat "mvn test -DsuiteXmlFile=${params.SUITE}"
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
            archiveArtifacts artifacts: 'test-output/**, target/surefire-reports/**', allowEmptyArchive: true
        }
        success {
            echo 'API tests passed.'
        }
        failure {
            echo 'API tests failed. Check console output.'
        }
    }
}