pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        echo 'Build'
        sh './gradlew clean build -x test'
      }
    }
    stage('test') {
      steps {
        echo 'Test'
        sh './gradlew test'
        junit 'build/test-results/**/*.xml'
      }
    }
  }
}
