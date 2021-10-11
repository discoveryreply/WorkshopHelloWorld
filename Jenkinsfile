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
		
	stage('push image to ecr'){
			steps{
				sh 'aws ecr get-login-password --region eu-west-1 | docker login --username AWS --password-stdin 753680302459.dkr.ecr.eu-west-1.amazonaws.com'
				sh 'docker tag wrkhelloworld:v1 753680302459.dkr.ecr.eu-west-1.amazonaws.com/wrkhelloworld:v1'
				sh 'docker push 753680302459.dkr.ecr.eu-west-1.amazonaws.com/wrkhelloworld:v1'
			}
		}


	}
}