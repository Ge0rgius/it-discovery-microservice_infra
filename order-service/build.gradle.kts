apply(plugin = "org.springframework.boot")

dependencies {
    implementation(project(":platform"))
    implementation(project(":order-client"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.kafka:spring-kafka")
}