def remote = [:]


pipeline {
	agent any
	
	tools{
	    jdk 'jdk11'
	    maven 'maven36'
	}

	stages {

        stage('Setup parameters') {
            steps {
                script { 
                    properties([
                        parameters([
                            choice(
                                choices: ['DEPLOY AND TEST', 'DEPLOY', 'TEST'], 
                                name: 'PARAMETER'
                            )

                        ])
                    ])
                }
            }
        }

	    stage('Cloning repository') {
            steps {
                git(
                    branch: 'development',
                    url: 'https://github.com/FilipeMiguelVale/ES-OnTrack.git'
                    
                )
                sh "chmod +x -R ${env.WORKSPACE}"
            }
        }

		stage ('Build Backend') {
			when {
                expression { (PARAMETER == 'DEPLOY AND TEST') || (PARAMETER == 'DEPLOY')}
            }
            steps {
			    dir("backend"){
				    sh 'mvn clean install -DskipTests'
			    }
			}
		}

        stage ('Build Producer') {
			when {
                expression { (PARAMETER == 'DEPLOY AND TEST') || (PARAMETER == 'DEPLOY')}
            }
            steps {
			    dir("bus_producer"){
				    sh 'mvn clean install -DskipTests'
			    }
			}
		}

        stage ('Build Selenium') {
			when {
                expression { (PARAMETER == 'DEPLOY AND TEST') || (PARAMETER == 'DEPLOY')}
            }
            steps {
			    dir("selenium_dev"){
				    sh 'mvn clean install -DskipTests'
			    }
			}
		}
		
		stage ('Testing Backend') {
			when {
                expression { (PARAMETER == 'DEPLOY AND TEST') || (PARAMETER == 'TEST')}
            }		    
            steps{
			    dir("backend"){
				    sh 'mvn test -Dtest=SrcApplicationTests'
			    }
		    }
		}
		stage ('Testing React') {
			when {
                expression { (PARAMETER == 'DEPLOY AND TEST') || (PARAMETER == 'TEST')}
            }
		    steps{
			    dir("backend"){
				    sh 'mvn test -Dtest=ReactTest'
			    }
		    }
		}
		
		stage ('Deploying Artifact') {
			when {
                expression { (PARAMETER == 'DEPLOY AND TEST') || (PARAMETER == 'DEPLOY')}
            }            
            steps{
                dir("backend"){
				    sh 'mvn deploy -DskipTests -f pom.xml -s settings.xml' 
			    }
            }
        }
        
        stage("Build Backend Image"){
			when {
                expression { (PARAMETER == 'DEPLOY AND TEST') || (PARAMETER == 'DEPLOY')}
            }
            steps{
                script{
                        docker.withRegistry('http://192.168.160.48:5000') {
                            backend = docker.build("esp23/backend", "./backend")
                   }
                }
            }
        }
        
        stage("Build React Image"){
			when {
                expression { (PARAMETER == 'DEPLOY AND TEST') || (PARAMETER == 'DEPLOY')}
            }
            steps{
                script{
                        docker.withRegistry('http://192.168.160.48:5000') {
                            react = docker.build("esp23/react", "./react")
                   }
                }
            }
        }

        stage("Build Producer Image"){
			when {
                expression { (PARAMETER == 'DEPLOY AND TEST') || (PARAMETER == 'DEPLOY')}
            }
            steps{
                script{
                        docker.withRegistry('http://192.168.160.48:5000') {
                            bus_producer = docker.build("esp23/bus_producer", "./bus_producer")
                   }
                }
            }
        }
        
        stage("Publish images"){
			when {
                expression { (PARAMETER == 'DEPLOY AND TEST') || (PARAMETER == 'DEPLOY')}
            }
            steps{
                script{
                        docker.withRegistry('http://192.168.160.48:5000') {
                            react.push()
                            backend.push()
                            bus_producer.push()

                   }
                sh "docker images"
                }
            }
        }

            stage('Deploy React') {
			when {
                expression { (PARAMETER == 'DEPLOY AND TEST') || (PARAMETER == 'DEPLOY')}
            } 
            steps {
                 withCredentials([usernamePassword(credentialsId: 'Esp23_playground_vm', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    
                    script {
                      remote.host = "192.168.160.87"
                      remote.name = "playground"         
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

        stage('Deploy Backend') {
			when {
                expression { (PARAMETER == 'DEPLOY AND TEST') || (PARAMETER == 'DEPLOY')}
            } 
            steps {
                 withCredentials([usernamePassword(credentialsId: 'Esp23_playground_vm', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    
                    script {
                      remote.host = "192.168.160.87"
                      remote.name = "playground"                          
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

        /**
        stage('Deploy Producer') {
			when {
                expression { (PARAMETER == 'DEPLOY AND TEST') || (PARAMETER == 'DEPLOY')}
            } 
            steps {
                 withCredentials([usernamePassword(credentialsId: 'Esp23_playground_vm', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    
                    script {
                      remote.host = "192.168.160.18"
                      remote.name = "runtime"  
                      remote.user = USERNAME
                      remote.password = PASSWORD
                      remote.allowAnyHosts = true
                        
                    }
                    //sshCommand remote: remote, command: "docker stop esp23-bus_producer"
                    //sshCommand remote: remote, command: "docker rm esp23-bus_producer"
                    //sshCommand remote: remote, command: "docker rmi 192.168.160.48:5000/esp23/bus_producer"
                    sshCommand remote: remote, command: "docker pull 192.168.160.48:5000/esp23/bus_producer"
                    sshCommand remote: remote, command: "docker create -p 23002:9001 --name esp23-bus_producer 192.168.160.48:5000/esp23/bus_producer"
                    sshCommand remote: remote, command: "docker start esp23-bus_producer"
                    
                  }
            }
        }

        */


        stage ("Wait before React Testing") {
			when {
                expression { (PARAMETER == 'DEPLOY AND TEST') || (PARAMETER == 'TEST')}
            }
            steps{
                echo 'Waiting 1 minute before react testing'
                sleep 60 // seconds
            }
        }

        
        stage ('Selenium Testing') {
			when {
                expression { (PARAMETER == 'DEPLOY AND TEST') || (PARAMETER == 'TEST')}
            }
		    steps{
			    dir("selenium_dev"){
				    sh 'mvn test'
			    }
		    }
		}

        
	}
} 
