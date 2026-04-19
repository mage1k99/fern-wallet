plugins {
    id("com.fern.feature")
    id("com.fern.room")
    id("com.fern.integration.testing")
}

android {
    namespace = "com.fern.data"
}

dependencies {
    implementation(projects.shared.base)
    api(projects.shared.data.model)

    api(libs.datastore)
    implementation(libs.bundles.ktor)

    testImplementation(projects.shared.data.modelTesting)
    androidTestImplementation(libs.bundles.integration.testing)
}
