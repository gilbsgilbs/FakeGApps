// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.6.0-rc01" apply false
}

tasks.withType(JavaCompile::class.java) {
    options.compilerArgs.add("-Xlint:all")
}