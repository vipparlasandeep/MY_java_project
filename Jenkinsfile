pipeline{
    agent any 
        stages {
            stage ('code checkout'){
                steps{
                    git branch: 'main', url: ' <url>'
                }
            }

            stage ('build'){
                steps{
                    sh 'maven clean package'
                }
            }
            stage ('test'){
                steps{
                    sh 'maven test'
                }
            }


            stage ('Sonarqube analysis'){
                steps{
                    script {
                        def scannerHome = tool 'SonarQube Scanner'
                        withSonarQubeEnv('SonarQube') {
                            sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=project-key -Dsonar.sources=. -Dsonar.host.url=http://<ip>:9000 -Dsonar.login=<token>"
                        }
                    }
                }
            }

            stage ('build docker image'){
                steps{
                    sh 'docker build -t my-java-project'
                    }
                }

            stage ('Trivy scan'){
                steps{
                    sh 'trivy image <image-name>'
                }
            }   

            stage ('OWASP Dependency check'){

                steps{
                    sh 'dependency-check.sh --project <project-name> --scan <path-to-scan>'
                }
            }   

            stage ("Docker push"){
                steps{
                    script {
                        sh 'docker push <image-name>'
                    }
                }
            }
        }
}