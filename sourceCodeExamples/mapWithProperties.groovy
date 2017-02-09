String basePath = 'example4'

folder(basePath) {
    description 'Jobs that differ only with a few properties'
}

[
        [repo: 'repo1', email: 'me@example.com'],
        [repo: 'repo2', email: 'you@example.com'],
        [repo: 'repo3'],
].each { Map config ->

    job("$basePath/ci-${config.repo}") {
        description "Main job for ${config.repo}"

        triggers {
            scm 'H/5 * * * *'
        }
        steps {
            grails 'test war'
        }
        publishers {
            if (config.email) {
                extendedEmail {
                    recipientList config.email
                }
            }
        }
    }
}