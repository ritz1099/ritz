 import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    kotlin("kapt") version "1.7.20"
    kotlin("plugin.spring") version "1.7.20"
    kotlin("plugin.jpa") version "1.7.20"
    id("org.springframework.boot") version "2.7.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("net.linguica.maven-settings") version "0.5"
    id("org.sonarqube") version "3.3"
//    id("org.sonarqube") version "3.0.0.2784"
    java
    jacoco
}

group = "com.pharmeasy"
version = ""
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	maven {
		name = "nexus"
		url = uri("https://nexus.mercuryonline.co/nexus/content/groups/public")
	}
	mavenCentral()
}

extra["springBootVersion"] = "2.7.4"
extra["springCloudVersion"] = "2021.0.3"
extra["springCloudStreamVersion"] = "3.2.4"
extra["springRetryVersion"] = "1.3.1"
extra["utilsVersion"] = "2022.0.25"
extra["jwtVersion"] = "3.14.0"
extra["jwksVersion"] = "0.17.1"
extra["mysqlVersion"] = "8.0.16"
extra["h2Version"] = "1.4.200"
extra["jupiterVersion"] = "5.8.2"
extra["awsSdkVersion"] = "2.16.48"
extra["feignVersion"] = "11.8"
extra["keycloakVersion"] = "12.0.3"
extra["sentryVersion"] = "6.4.0"
extra["outboxVersion"] = "0.1.55"

dependencies {
    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    // spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.integration:spring-integration-redis")
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
    implementation("org.springframework.cloud:spring-cloud-starter-stream-kafka")
    implementation("org.springframework.retry:spring-retry:${property("springRetryVersion")}")
    // util
    implementation("com.pharmeasy:mercury-model:${property("utilsVersion")}")
    implementation("com.pharmeasy:data-model:${property("utilsVersion")}")
    implementation("com.pharmeasy:jwt-auth-util:${property("utilsVersion")}")
    implementation("com.pharmeasy:header-context-util:${property("utilsVersion")}")
    implementation("com.pharmeasy:micro-service-util:${property("utilsVersion")}")
    implementation("com.pharmeasy:notification-util:${property("utilsVersion")}")
    implementation("com.pharmeasy:s3-util:${property("utilsVersion")}")
    implementation("com.pharmeasy:keycloak-feign:${property("utilsVersion")}")
    implementation("com.pharmeasy.transactional-outbox:jpa:${property("outboxVersion")}")
    implementation("com.pharmeasy.transactional-outbox:core:${property("outboxVersion")}")


    // keycloak
    implementation("org.keycloak:keycloak-spring-boot-starter:${property("keycloakVersion")}")
    // 3rd party
    implementation("org.apache.commons:commons-csv:1.6")
    implementation("io.github.openfeign:feign-httpclient:${property("feignVersion")}")
    implementation("com.auth0:java-jwt:${property("jwtVersion")}")
    implementation("com.auth0:jwks-rsa:${property("jwksVersion")}")
    implementation("mysql:mysql-connector-java:${property("mysqlVersion")}")
    implementation("software.amazon.awssdk:sts")
    implementation("software.amazon.awssdk:s3")
    implementation("org.flywaydb:flyway-core:7.15.0")
    implementation("io.sentry:sentry-spring-boot-starter:${property("sentryVersion")}")
    implementation("io.sentry:sentry-logback:${property("sentryVersion")}")
    implementation("org.apache.tika:tika-core:2.8.0")
    implementation("org.springdoc:springdoc-openapi-ui:1.6.11")
    // extras
    implementation("com.amazonaws:aws-java-sdk:1.11.234")

    // MapStruct
    kapt("org.mapstruct:mapstruct-processor:1.3.1.Final")
    implementation("org.mapstruct:mapstruct-jdk8:1.3.1.Final")
    kapt("org.mapstruct:mapstruct-processor:1.3.1.Final")
    kapt("org.mapstruct:mapstruct-jdk8:1.3.1.Final")

    implementation("com.univocity:univocity-parsers:2.8.2")
    implementation("com.vladmihalcea:hibernate-types-52:2.7.0")
    implementation("org.apache.commons:commons-lang3:3.11")
    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testRuntimeOnly("com.h2database:h2:${property("h2Version")}")
    // dev
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // Cache
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("com.github.ben-manes.caffeine:caffeine:3.0.4")

    // audited tables
    implementation("org.hibernate:hibernate-envers:5.6.11.Final")

    //fuzzysearch
    implementation("me.xdrop:fuzzywuzzy:1.4.0")

    //javers
    implementation("org.javers:javers-core:6.8.2")

}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:${property("springBootVersion")}")
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        mavenBom("org.springframework.cloud:spring-cloud-stream-dependencies:${property("springCloudStreamVersion")}")
        mavenBom("software.amazon.awssdk:bom:${property("awsSdkVersion")}")
        mavenBom("org.junit:junit-bom:${property("jupiterVersion")}")
    }
    dependencies {
        dependency("org.springframework.boot:spring-boot-starter-web:${property("springBootVersion")}")
    }
}
tasks.check {
    dependsOn(tasks.jacocoTestCoverageVerification)
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}