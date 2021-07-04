plugins {
    `kotlin-dsl`
    idea
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation("org.codehaus.groovy:groovy-all:3.0.7")

    // https://github.com/jenkinsci/java-client-api
    implementation("com.offbytwo.jenkins:jenkins-client:0.3.8")
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:2.3.2")
    implementation("org.glassfish.jaxb:jaxb-runtime:2.3.2")
}

gradlePlugin {
    plugins {
        create("testGenerator") {
            id = "ci.build.simulator.generateTests"
            implementationClass = "ci.build.simulator.generatetests.GenerateTestsPlugin"
        }

        create("jenkins") {
            id = "ci.build.simulator.jenkins"
            implementationClass = "ci.build.simulator.jenkins.JenkinsPlugin"
        }
    }
}