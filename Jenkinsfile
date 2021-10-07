pipeline {
	agent any

	stages {

	stage('clean'){
			steps{
				sh '/home/ec2-user/maven/maven3.8/bin/mvn clean'
			}
		}


	stage('compile'){
			steps{
				sh '/home/ec2-user/maven/maven3.8/bin/mvn compile'
			}
		}


	stage('package'){
			steps{
				sh '/home/ec2-user/maven/maven3.8/bin/mvn package'
			}
		}


	}
}