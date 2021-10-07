pipeline {
	agent any

	stages {

	stage('clean'){
			steps{
				sh 'mvn clean'
			}
		}


	stage('compile'){
			steps{
				sh 'mvn compile'
			}
		}


	stage('package'){
			steps{
				sh 'mvn package'
			}
		}


	}
}