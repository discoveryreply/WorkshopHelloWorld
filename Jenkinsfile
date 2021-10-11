pipeline {
	agent any
	
	environment
    {
    	DOCKER_BUILD_NAME = 'wrkhelloworld'
		DOCKER_BUILD_VERSION = ''
	}
	
	
	stages {

	stage('clean'){
			steps{
				sh '/maven/maven3.8/bin/mvn clean'
			}
		}

	stage('compile'){
			steps{
				sh '/maven/maven3.8/bin/mvn compile'
				DOCKER_BUILD_VERSION = sh '/maven/maven3.8/bin/mvn help:evaluate -Dexpression=project.version -q -DforceStdout'
				echo 'buildname: ' +$DOCKER_BUILD_NAME + ':' + $DOCKER_BUILD_VERSION
			}
		}


	stage('package'){
			steps{
				sh '/maven/maven3.8/bin/mvn package'
			}
		}
		
	stage('build docker image'){
			steps{
				sh 'docker build -t ' + $DOCKER_BUILD_NAME + ':' + $DOCKER_BUILD_VERSION  + ' .'
			}
		}
		
	stage('push image to ecr'){
			steps{
				sh 'aws ecr get-login-password --region eu-west-1 | docker login --username AWS --password-stdin 753680302459.dkr.ecr.eu-west-1.amazonaws.com'
				sh 'docker tag ' + $DOCKER_BUILD_NAME + ':' + $DOCKER_BUILD_VERSION + ' 753680302459.dkr.ecr.eu-west-1.amazonaws.com/' + $DOCKER_BUILD_NAME + ':' + $DOCKER_BUILD_VERSION
				sh 'docker push 753680302459.dkr.ecr.eu-west-1.amazonaws.com/' + $DOCKER_BUILD_NAME + ':' + $DOCKER_BUILD_VERSION
			}
		}


	}
}