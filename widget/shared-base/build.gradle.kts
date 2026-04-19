plugins {
    id("com.fern.widget")
}

android {
    namespace = "com.fern.widget"
}

dependencies {
    implementation(projects.shared.base)
    implementation(projects.shared.domain)
}
