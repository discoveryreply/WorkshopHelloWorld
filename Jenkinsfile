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
			steps 
            {
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
		steps 
            {
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
		steps 
            {
			script
			{
				docker.image("${DOCKER_BUILD_NAME_MAVEN}").inside(DOCKER_RUN_PARAMS) 
				{
    				sh 'mvn package'
    			}
    		}
    		}  
		}
	}
}