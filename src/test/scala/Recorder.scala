import io.gatling.recorder.GatlingRecorder
import io.gatling.recorder.config.RecorderPropertiesBuilder

object Recorder extends App {

  val props = new RecorderPropertiesBuilder
  props.simulationOutputFolder(Helper.recorderOutputDirectory.toString)
  props.simulationPackage("com.developers.feesRegister.scenarios")
  props.bodiesFolder(Helper.bodiesDirectory.toString)

  GatlingRecorder.fromMap(props.build, Some(Helper.recorderConfigFile))
}