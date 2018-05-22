import java.nio.file.{Path, Paths}

object Helper {

  val projectRootDir: Path = Paths.get("").toAbsolutePath

  val mavenResourcesDirectory: String = projectRootDir + "/src/test/resources"
  val mavenTargetDirectory: String = projectRootDir + "/target"
  val mavenBinariesDirectory: String = mavenTargetDirectory + "/test-classes"

  val dataDirectory: String = mavenResourcesDirectory + "/data"
  val bodiesDirectory: String = mavenResourcesDirectory + "/bodies"

  val recorderOutputDirectory: String = projectRootDir + "src/test/scala"
  val resultsDirectory: String = mavenTargetDirectory + "/results"

  val recorderConfigFile: Path = Paths.get(mavenResourcesDirectory, "/recorder.conf")
}