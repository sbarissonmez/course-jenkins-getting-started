pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/sbarissonmez/jgsu-spring-petclinic.git'
            }
        }    
        stage('Build') {
            steps {
                sh './mvnw clean compile'
            }
        
            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
