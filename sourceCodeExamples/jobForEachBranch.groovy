import groovy.json.JsonSlurper

String basePath = 'example2'
String repo = 'sheehan/grails-example'

folder(basePath) {
    description 'Create a set of jobs for each branch'
}

URL branchUrl = "https://api.github.com/repos/$repo/branches".toURL()
List branches = new JsonSlurper().parse(branchUrl.newReader())
branches.each { branch ->

    String safeBranchName = branch.name.replaceAll('/', '-')

    folder "$basePath/$safeBranchName"

    job("$basePath/$safeBranchName/grails-example-build") {
        scm {
            github repo, branch.name
        }
        triggers {
            scm 'H/30 * * * *'
        }
        steps {
            grails 'test-app war', true
        }
    }
}