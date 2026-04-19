plugins {
  id("com.fern.feature")
}

android {
  namespace = "com.fern.poll"
}

dependencies {
  implementation(projects.shared.domain)
}