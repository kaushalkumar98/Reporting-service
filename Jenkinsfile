pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and the JDK version configured as "jdk11"
        maven 'M3'
        jdk 'jdk17'
    }

    environment {
        // Define SonarQube environment variables
        SONARQUBE_URL = 'https://localhost/9000'
        SONARQUBE_TOKEN = credentials('sqp_77a1596f0f95373bae2a4689928d661436d0326b')
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
                    sh 'mvn sonar:sonar -Dsonar.host.url=$SONARQUBE_URL -Dsonar.login=$SONARQUBE_TOKEN'
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
