pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        echo 'Build'
        sh './gradlew build'
      }
    }
    stage('test') {
      steps {
        echo 'Test'
        sh './gradlew check'
      }
    }
  }
  post {
    always {
      archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
      junit 'build/reports/**/*.xml'
    }
  }  
}
