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
			steps {
				script
				{
					docker.image("${DOCKER_BUILD_NAME_MAVEN}").inside(DOCKER_RUN_PARAMS) 
					{
	    				sh 'mvn clean'
	    			}
	    		}  
	    	}   
		}
	
		stage('compile'){
			steps {
				script
				{
					docker.image("${DOCKER_BUILD_NAME_MAVEN}").inside(DOCKER_RUN_PARAMS) 
					{
	    				sh 'mvn compile'
	    			}
	    		}
	    	}   
		}
	
	
		stage('package'){
			steps {
				script
				{
					docker.image("${DOCKER_BUILD_NAME_MAVEN}").inside(DOCKER_RUN_PARAMS) 
					{
	    				sh 'mvn package'
	    			}
	    		}
	    	}  
		}
		
		/* versione senza utilizzo del plugin docker
		stage('build docker image (microservizio)'){
			steps{
				sh 'docker build -t $DOCKER_BUILD_NAME:$DOCKER_BUILD_VERSION .'
			}
		}*/
		
		stage('push image to ecr (microservizio)'){
        	environment
        	{
        		AWS_REGION = 'eu-west-1'
        		REGISTRY_URL = 'http://753680302459.dkr.ecr.eu-west-1.amazonaws.com'
    			REGISTRY_NAME = sh(script: 'echo $DOCKER_BUILD_NAME', , returnStdout: true).trim()
    			imageName = "${DOCKER_BUILD_NAME}:${DOCKER_BUILD_VERSION}"
        	}
		
			steps{
				script
    			{
					sh 'aws ecr get-login-password --region eu-west-1 | docker login --username AWS --password-stdin 753680302459.dkr.ecr.eu-west-1.amazonaws.com'
					//sh 'docker tag $DOCKER_BUILD_NAME:$DOCKER_BUILD_VERSION 753680302459.dkr.ecr.eu-west-1.amazonaws.com/$DOCKER_BUILD_NAME:$DOCKER_BUILD_VERSION'
					//sh 'docker push 753680302459.dkr.ecr.eu-west-1.amazonaws.com/$DOCKER_BUILD_NAME:$DOCKER_BUILD_VERSION'
					docker.withRegistry(REGISTRY_URL)
					{	
						//build e push dell'immagine su ECR:
						docker.build(imageName, ".").push()					
					}
				}
				
			}
		}
	
	} //stages
}