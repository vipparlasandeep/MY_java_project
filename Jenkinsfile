pipeline {
    agent any
    environment {
        
        DOCKER_CREDENTIALS = credentials('docker_hub_cred') // Docker Hub credentials
        SCANNER_HOME=tool 'sonar-qube-scanner'
    }
    stages {
        stage('Checkout Code') {
            steps {
                git url: 'https://github.com/vipparlasandeep/MY_java_project.git', branch: 'main'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh '$SCANNER_HOME/bin/sonar-scanner -Dsonar.host.url=http://98.85.117.213:9000 -Dsonar.login=squ_d9418b17908484c8b514f5e2c648958fa70fc903' \
                    -Dsonar.projectKey=My_Java_Project \
                            -Dsonar.projectName="My_Java_Project" \
                            -Dsonar.projectVersion=1.0 \
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t my-app:latest .'
            }
        }
        stage('Push to Docker Hub') {
            steps {
                withDockerRegistry([credentialsId: 'docker-hub', url: 'https://index.docker.io/v1/']) {
                    sh 'docker tag my-app:latest my-docker-user/my-app:latest'
                    sh 'docker push my-docker-user/my-app:latest'
                }
            }
        }
    }
}
