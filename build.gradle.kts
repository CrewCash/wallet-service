plugins {
	java
	id("org.springframework.boot") version "3.5.7"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.crewcash"
version = "0.0.1-SNAPSHOT"
description = "wallet-service"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

extra["otelVersion"] = "1.33.3"
extra["springCloudVersion"] = "2025.0.0"
extra["testcontainersVersion"] = "1.19.8"
extra["testKeycloakVersion"] = "3.9.1"

val otelVersion: String by project
val springCloudVersion: String by project
val testcontainersVersion: String by project
val testKeycloakVersion: String by project

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.cloud:spring-cloud-starter-config")
	implementation("org.springframework.retry:spring-retry")
	implementation("org.flywaydb:flyway-core")

	runtimeOnly("io.micrometer:micrometer-registry-prometheus")
	runtimeOnly("io.opentelemetry.javaagent:opentelemetry-javaagent:$otelVersion")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-starter-webflux")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:postgresql")
	testImplementation("com.github.dasniko:testcontainers-keycloak:$testKeycloakVersion")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
