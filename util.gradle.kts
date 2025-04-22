ext.getArtifactName = {
        defaultConfig ->
    def date = new Date().format("yyyyMMdd")
    return defaultConfig.applicationId + "-" + project.name + "-" + defaultConfig.versionName + "-" + defaultConfig.versionCode + "-" + date
}
