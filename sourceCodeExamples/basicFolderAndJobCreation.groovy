String basePath = 'example1'
String repo = 'sheehan/grails-example'

folder(basePath) {
    description 'Basic folder/job creation.'
}

job("$basePath/grails example build") {
    scm {
        github repo
    }
    triggers {
        scm 'H/5 * * * *'
    }
    steps {
        grails {
            useWrapper true
            targets(['test-app', 'war'])
        }
    }
}