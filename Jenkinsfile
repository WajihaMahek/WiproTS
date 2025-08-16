pipeline {
    agent any

    tools {
        maven "Maven3"
    }

    stages {
        stage('Compile') {
            steps {
                echo "Compiling code..."
                bat 'mvn -B -DskipTests clean package'
            }
        }

        stage('Code Review') {
            steps {
                echo "Running code review checks..."
                bat 'mvn checkstyle:check || exit 0'
            }
        }

        stage('Unit Test') {
            steps {
                echo "Running unit tests..."
                bat 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Metric Check') {
            steps {
                echo "Generating code coverage..."
                bat 'mvn org.jacoco:jacoco-maven-plugin:prepare-agent test jacoco:report'
            }
        }

        stage('Package Check') {
            steps {
                echo "Archiving artifacts..."
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Deploy') {
            steps {
                echo "Deploying application..."
                // Example: Copy the built JAR to a deployment folder and run it
                bat '''
                echo Copying JAR to deployment folder...
                if not exist C:\\deploy mkdir C:\\deploy
                copy target\\*.jar C:\\deploy\\app.jar /Y

                echo Starting application...
                start cmd /c "java -jar C:\\deploy\\app.jar"
                '''
            }
        }
    }
}
