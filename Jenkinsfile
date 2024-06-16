pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and the JDK version configured as "jdk11"
        maven 'M3'
        jdk 'jdk17'
    }

    environment {
        // Define SonarQube environment variables
        SONARQUBE_URL = 'http://localhost:9000'
        SONARQUBE_TOKEN = credentials('Krasv_bank')
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout code from GitHub
                git branch: 'master', url: 'https://github.com/kaushalkumar98/Reporting-service.git'
            }
        }

        stage('Build') {
            steps {
                // Run Maven build
                sh 'mvn clean install'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                // Run SonarQube analysis
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar -Dsonar.projectKey=Krasv_bank -Dsonar.projectName=Krasv_bank -Dsonar.host.url=http://localhost:9000 -Dsonar.token=sqp_77a1596f0f95373bae2a4689928d661436d0326b'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                // Wait for SonarQube analysis to finish and get the Quality Gate status
                waitForQualityGate abortPipeline: true
            }
        }
    }

    post {
        always {
            // Clean up workspace
            cleanWs()
        }
    }
}
