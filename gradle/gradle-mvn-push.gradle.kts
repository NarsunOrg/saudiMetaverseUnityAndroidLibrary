import org.gradle.api.publish.PublishingExtension

apply(plugin = "maven-publish")


afterEvaluate {
    configure<PublishingExtension> {
        publications {
            create<MavenPublication>("release") {
                from(components.getByName("release"))
                pom {
                    description = 'third release'
                }
            }
        }
    }
}