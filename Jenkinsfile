#!groovy
@Library("Reform")

def triggers = []
if (env.BRANCH_NAME == "master") {
    triggers << cron('H H(0-6) * * *') //build to trigger sometime between midnight and 4am every day
}

properties(
        [
                [$class: 'GithubProjectProperty', projectUrlStr: 'https://github.com/hmcts/probate-sol-ccd-services-performance-tests.git'],
                pipelineTriggers(triggers),
                parameters([
                        string(description: 'Sol ccd url', defaultValue: 'http://betaDevbprobateapp01.reform.hmcts.net:4104', name: 'SOL_CCD_SERVICE_BASE_URL'),
                        string(description: 'Service auth url', defaultValue: 'http://betadevbccidams2slb.reform.hmcts.net', name: 'SERVICE_AUTH_PROVIDER_BASE_URL'),
                        string(description: 'Idam user auth url', defaultValue: 'http://betadevbccidamapplb.reform.hmcts.net', name: 'USER_AUTH_PROVIDER_OAUTH2_URL'),
                        string(description: 'Service auth service name', defaultValue: 'PROBATE_BACKEND', name: 'AUTHORISED_SERVICES'),
                        string(description: 'At once users', defaultValue: '1', name: 'AT_ONCE_USERS'),
                        string(description: 'Min pass percent', defaultValue: '99', name: 'MIN_PASS_PERCENT'),
                        string(description: 'Idam User Id', defaultValue: '22603', name: 'IDAM_USER_ID')
                ])
        ]
)

lock('Sol CCD Service API performance tests') {
    node {
        try {
            stage('Run performance tests') {
                deleteDir()
                checkout scm
                sh "./gradlew clean loadTest"

                sh "mv target/gatling-results/solccdservicessimulation*/* target/gatling-results/"

                publishHTML([
                        allowMissing         : false,
                        alwaysLinkToLastBuild: false,
                        keepAll              : true,
                        reportDir            : "target/gatling-results/",
                        reportFiles          : 'index.html',
                        reportName           : 'Performance Test Report'
                ])
            }
        } catch (err) {
            notifyBuildFailure channel: '#cc-tech'
            throw err
        }
    }
}
