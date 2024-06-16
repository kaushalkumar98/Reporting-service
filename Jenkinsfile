pipeline {
    agent any

    tools {
        maven 'M3' // Ensure this matches the name configured in Jenkins
        jdk 'jdk17' // Ensure this matches the name configured in Jenkins
    }

    environment {
        SONARQUBE_URL = 'http://localhost:9000/'
        SONARQUBE_TOKEN = credentials('krasv_bank')
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    node {
                        // Checkout code from GitHub
                        git branch: 'master', url: 'https://github.com/kaushalkumar98/Reporting-service.g
                    }
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    node {
                        // Run Maven build
                        sh 'mvn clean install'
                    }
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    node {
                        // Run SonarQube analysis
                        withSonarQubeEnv('SonarQube') {
                            sh 'mvn sonar:sonar -Dsonar.projectKey=com.axis.team2.krasv_bank -Dsonar.host.url=$SONARQUBE_URL -Dsonar.login=$SONARQUBE_TOKEN'
                        }
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                script {
                    node {
                        // Wait for SonarQube analysis to finish and get the Quality Gate status
                        waitForQualityGate abortPipeline: true
                    }
                }
            }
        }
    }

    post {
        always {
            script {
                node {
                    // Clean up workspace
                    cleanWs()
                }
            }
        }
    }
}
