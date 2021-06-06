def remote = [:]
remote.host = "192.168.160.87"
remote.name = "runtime"

pipeline {
	agent any
	
	tools{
	    jdk 'jdk11'
	    maven 'maven36'
	}
	
    
	stages {
	    stage('Cloning repository') {
            steps {
                git(
                    branch: 'development',
                    url: 'https://github.com/FilipeMiguelVale/ES-OnTrack.git'
                    
                )
                sh "chmod +x -R ${env.WORKSPACE}"
            }
        }

		stage ('Build') {
			steps {
			    dir("backend"){
				    sh 'mvn clean install'
			    }
			}
		}
		
		stage ('Testing') {
		    steps{
			    dir("backend"){
				    sh 'mvn test'
			    }
		    }
		}
		
		stage ('Deploying Artifact') {
            steps{
                dir("backend"){
				    sh 'mvn deploy -f pom.xml -s settings.xml' 
			    }
            }
        }
        
        stage("Build Backend Image"){
            steps{
                script{
                        docker.withRegistry('http://192.168.160.48:5000') {
                            backend = docker.build("esp23/backend", "./backend")
                   }
                }
            }
        }
        
        stage("Build React Image"){
            steps{
                script{
                        docker.withRegistry('http://192.168.160.48:5000') {
                            react = docker.build("esp23/react", "./react")
                   }
                }
            }
        }
        
        stage("Publish images"){
            steps{
                script{
                        docker.withRegistry('http://192.168.160.48:5000') {
                            react.push()
                            backend.push()
                   }
                sh "docker images"
                }
            }
        }
        
        stage('Deploy Backend') { 
            steps {
                 withCredentials([usernamePassword(credentialsId: 'Esp23_playground_vm', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    
                    script {
                      remote.user = USERNAME
                      remote.password = PASSWORD
                      remote.allowAnyHosts = true
                        
                    }
                    sshCommand remote: remote, command: "docker stop esp23-backend"
                    sshCommand remote: remote, command: "docker rm esp23-backend"
                    sshCommand remote: remote, command: "docker rmi 192.168.160.48:5000/esp23/backend"
                    sshCommand remote: remote, command: "docker pull 192.168.160.48:5000/esp23/backend"
                    sshCommand remote: remote, command: "docker create -p 23001:8080 --name esp23-backend 192.168.160.48:5000/esp23/backend"
                    sshCommand remote: remote, command: "docker start esp23-backend"
                    
                  }
            }
        }
        
        stage('Deploy React') { 
            steps {
                 withCredentials([usernamePassword(credentialsId: 'Esp23_playground_vm', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    
                    script {
                      remote.user = USERNAME
                      remote.password = PASSWORD
                      remote.allowAnyHosts = true
                        
                    }
                    
                    sshCommand remote: remote, command: "docker stop esp23-react"
                    sshCommand remote: remote, command: "docker rm esp23-react"
                    sshCommand remote: remote, command: "docker rmi 192.168.160.48:5000/esp23/react"
                    sshCommand remote: remote, command: "docker pull 192.168.160.48:5000/esp23/react"
                    sshCommand remote: remote, command: "docker create -p 23000:3000 --name esp23-react 192.168.160.48:5000/esp23/react"
                    sshCommand remote: remote, command: "docker start esp23-react"
                    
                  }
            }
        }
	}
} 
