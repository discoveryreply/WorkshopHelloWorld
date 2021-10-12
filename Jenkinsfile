pipeline {
	agent any
	
	environment
    {
    	//DOCKER_BUILD_NAME = sh(script: '/maven/maven3.8/bin/mvn help:evaluate -Dexpression=project.artifactId -q -DforceStdout', , returnStdout: true).trim()
		DOCKER_BUILD_NAME = 'wrkhelloworld'
		DOCKER_RUN_PARAMS = "-v ${WORKSPACE}:/usr/src/project -u jenkins -w /usr/src/project"
		DOCKER_BUILD_NAME_MAVEN = 'wrkhelloworld-maven'
		//DOCKER_BUILD_VERSION = sh(script: '/maven/maven3.8/bin/mvn help:evaluate -Dexpression=project.version -q -DforceStdout', , returnStdout: true).trim()
		DOCKER_BUILD_VERSION = sh(script: 'echo $BUILD_ID', , returnStdout: true).trim()
	}
	
	
	stages {
	
		stage ('Init')
    	{
    		steps
    		{
				sh("docker build -t ${DOCKER_BUILD_NAME_MAVEN} ./script/build")
			}
		}

	stage('clean'){
			script
			{
				docker.image("${DOCKER_BUILD_NAME_MAVEN}").inside(DOCKER_RUN_PARAMS) 
				{
    				sh 'mvn clean'
    			}
    		}     
		}

	stage('compile'){

			script
			{
				docker.image("${DOCKER_BUILD_NAME_MAVEN}").inside(DOCKER_RUN_PARAMS) 
				{
    				sh 'mvn compile'
    			}
    		}   
		}


	stage('package'){
			script
			{
				docker.image("${DOCKER_BUILD_NAME_MAVEN}").inside(DOCKER_RUN_PARAMS) 
				{
    				sh 'mvn package'
    			}
    		}  
		}
		
	stage('build docker image'){

			steps{

				//sh 'echo imageVersion: $DOCKER_BUILD_VERSION'
				//sh 'docker build -t $DOCKER_BUILD_NAME:$DOCKER_BUILD_VERSION .'
			}
		}
		
	stage('push image to ecr'){
			steps{
				//sh 'aws ecr get-login-password --region eu-west-1 | docker login --username AWS --password-stdin 753680302459.dkr.ecr.eu-west-1.amazonaws.com'
				//sh 'docker tag $DOCKER_BUILD_NAME:$DOCKER_BUILD_VERSION 753680302459.dkr.ecr.eu-west-1.amazonaws.com/$DOCKER_BUILD_NAME:$DOCKER_BUILD_VERSION'
				//sh 'docker push 753680302459.dkr.ecr.eu-west-1.amazonaws.com/$DOCKER_BUILD_NAME:$DOCKER_BUILD_VERSION'
			}
		}


	}
}