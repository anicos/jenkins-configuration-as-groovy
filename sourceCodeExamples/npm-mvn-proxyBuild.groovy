job('npm-mvn-proxy'){
  scm{
    github 'anicos/npm-mvn-proxy'
  }
  steps{
    shell './gradlew clean build'
  }
}
