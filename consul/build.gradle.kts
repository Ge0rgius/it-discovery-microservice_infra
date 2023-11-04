val cloudConsulVersion = "4.0.3"

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-consul-all")

    implementation(platform("org.springframework.cloud:spring-cloud-consul-dependencies:$cloudConsulVersion"))
}
