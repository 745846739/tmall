pipeline {
    agent any

    tools {
        maven "M3"
        dockerTool "docker"
    }

    stages {
        stage('Build') {
            steps {
                # xxx
                git 'https://github.com/745846739/tmall.git'
                sh "mvn clean package"
                sh "docker build . -t lzy/tmall:0.0.1"
            }

            post {
                success {
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
