pipeline {
    agent any
    triggers { pollSCM('* * * * *') }
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/sbarissonmez/jgsu-spring-petclinic.git', branch: 'main'
            }
        }    
        stage('Build') {
            steps {
                sh './mvnw clean package'
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
