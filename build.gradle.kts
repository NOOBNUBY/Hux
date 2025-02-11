plugins {
    kotlin("jvm") version "1.9.10"
}

group = "kr.vanilage"
version = "1.0" // 여기에 버전을 작성하세요.

var buildPath = "D:\\Programming\\Mcserver\\Hux\\plugins" // 빌드 주소를 설정하세요.

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT"); // 여기 버전을 바꿀수 있습니다.
    implementation("io.github.monun:kommand-api:3.1.7")
    implementation("io.github.monun:invfx-api:3.3.2")
}

task("copy") {
    doLast {
        val directory = File(buildPath)

        if (directory.exists() && directory.isDirectory) {
            if (directory.listFiles().any {it.name == project.name + "-" + version + ".jar"}) {
                copy {
                    from("build/libs") // 파일이 나오는 주소를 설정하세요.
                    include("*.jar")
                    into(buildPath.plus("\\update"))
                }

                delete(buildPath.plus("\\update\\RELOAD"))
            }

            else {
                copy {
                    from("build/libs") // 파일이 나오는 주소를 설정하세요.
                    include("*.jar")
                    into(buildPath)
                }
            }
        }
    }
}

tasks {
    named("jar") {
        finalizedBy("copy")
    }
}
