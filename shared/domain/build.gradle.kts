plugins {
    id("com.fern.feature")
    id("com.fern.integration.testing")
    id("com.fern.room")
}

android {
    namespace = "com.fern.domain"
}

dependencies {
    implementation(projects.shared.base)
    implementation(projects.shared.data.core)

    implementation(libs.datastore)
    implementation(libs.bundles.ktor)
    implementation(libs.bundles.opencsv)

    testImplementation(projects.shared.data.modelTesting)
    testImplementation(projects.shared.data.coreTesting)

    androidTestImplementation(libs.bundles.integration.testing)
    androidTestImplementation(libs.mockk.android)
}