job('example') {
    configure {
        it / node/ child << 'hudson.plugins.xshell.XellBuilder' {
            commandLine('123')
        }
    }
}