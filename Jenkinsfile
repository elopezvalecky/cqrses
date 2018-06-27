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
        sh 'sh \'./gradlew check\''
      }
    }
  }
}