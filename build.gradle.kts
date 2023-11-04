plugins {
    java
    eclipse
    id("org.springframework.boot") version "3.1.5" apply false
}

allprojects {
    group = "it.discovery"
}

subprojects {
    apply(plugin = "java")

    java.sourceCompatibility = JavaVersion.VERSION_21
    java.targetCompatibility = JavaVersion.VERSION_21

    val cloudStarterVersion = "2022.0.4"

    repositories {
        jcenter()
    }

    dependencies {
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok:1.18.30")
        implementation("org.modelmapper:modelmapper:3.2.0")

        implementation(platform("org.springframework.cloud:spring-cloud-starter-parent:$cloudStarterVersion"))
        testImplementation("org.springframework.boot:spring-boot-starter-test")

    }

    tasks.withType<JavaCompile> {
        options.compilerArgs.add("--enable-preview")
    }

    tasks.test {
        jvmArgs("--enable-preview")
    }

}

