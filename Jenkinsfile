pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'Build'
        sh './gradlew clean build -x test'
      }
    }
    stage('Test') {
      steps {
        echo 'Test'
        sh './gradlew test'
        junit 'build/test-results/**/*.xml'
      }
    }
    stage('Deploy') {
      when {
          branch 'master'
      }
      steps {
        echo 'Deploy'
      }
    }
  }
}
