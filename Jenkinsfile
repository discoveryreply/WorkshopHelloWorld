pipeline {
	agent any

	stages {

	stage('clean'){
			steps{
				sh '/maven/maven3.8/bin/mvn clean'
			}
		}


	stage('compile'){
			steps{
				sh '/maven/maven3.8/bin/mvn compile'
			}
		}


	stage('package'){
			steps{
				sh '/maven/maven3.8/bin/mvn package'
			}
		}
		
	stage('build docker image'){
			steps{
				sh 'docker build -t wrkhelloworld:v1 .'
			}
		}


	}
}