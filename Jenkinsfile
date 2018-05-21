pipeline {
  agent any
  stages {
    stage('init') {
      parallel {
        stage('init') {
          steps {
            echo 'hello'
          }
        }
        stage('p1') {
          steps {
            echo '11'
          }
        }
        stage('p2') {
          steps {
            echo 'p2'
          }
        }
      }
    }
    stage('build') {
      steps {
        echo '11'
      }
    }
    stage('test') {
      steps {
        echo '22'
      }
    }
  }
}