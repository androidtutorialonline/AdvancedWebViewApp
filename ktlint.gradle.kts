plugins {
    id("org.jlleitschuh.gradle.ktlint") version "11.6.0"
}

ktlint {
    version.set("0.49.1")
    android.set(true)
    outputColorName.set("RED")
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
    }
}
