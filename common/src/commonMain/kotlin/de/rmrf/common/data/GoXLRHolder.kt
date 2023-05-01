package de.rmrf.common.data

import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.databind.*
import java.nio.file.Path
import java.util.*


data class WebsocketResponse(
    val id: Long,
    val data: Data,

    ) {
    companion object {
        fun getEmpty(): WebsocketResponse {
            return WebsocketResponse(id = -1, data = Data.Ok)
        }
    }
}


@JsonSubTypes(
    value = [
        JsonSubTypes.Type(Data.Error::class),
        JsonSubTypes.Type(Data.Ok::class),
        JsonSubTypes.Type(Data.Status::class),
        JsonSubTypes.Type(Data.Patch::class)
    ]
)
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
sealed interface Data {

    @JsonTypeName("Error")
    @JvmInline
    value class Error(val error: String) : Data {
        companion object {
            @JvmStatic
            @JsonCreator
            fun fromJson(@JsonProperty("Error") error: String): Error = Error(error)
        }
    }

    @JsonTypeName("Ok")
    @JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, use = JsonTypeInfo.Id.NAME)
    object Ok : Data {
        @JvmStatic
        @JsonCreator
        fun fromJson(): Ok = Ok
    }

    @JsonTypeName("Status")
    @JvmInline
    value class Status(val status: DaemonStatus) : Data {
        companion object {
            @JvmStatic
            @JsonCreator
            fun fromJson(@JsonProperty("Status") status: DaemonStatus): Status = Status(status)
        }
    }

    @JsonTypeName("Patch")
    @JvmInline
    value class Patch(val patch: JsonNode) : Data {
        companion object {
            @JvmStatic
            @JsonCreator
            fun fromJson(@JsonProperty("Patch") patch: JsonNode): Patch = Patch(patch)
        }
    }
}


data class OldData(
    @JsonProperty("Status") val status: DaemonStatus? = null,
    @JsonProperty("Patch") val patch: Array<JsonNode>? = null
)

data class DaemonStatus(
    var config: DaemonConfig,
    var mixers: HashMap<String, MixerStatus>,
    var paths: Paths,
    var files: Files
) {
    fun getMixerStatus(serialNumber: String): MixerStatus? {
        return this.mixers[serialNumber]
    }
}

data class DaemonConfig(
    @JsonProperty("daemon_version")
    var daemonVersion: String,
    @JsonProperty("autostart_enabled")
    var autostartEnabled: Boolean,
    @JsonProperty("show_tray_icon")
    var showTrayIcon: Boolean
)


data class Files(
    var profiles: Vector<String>,
    var micProfiles: Vector<String>,
    var presets: Vector<String>,
    var samples: TreeMap<String, String>,
    var icons: Vector<String>
)

data class Paths(
    var profileDirectory: Path,
    var micProfileDirectory: Path,
    var samplesDirectory: Path,
    var presetsDirectory: Path,
    var iconsDirectory: Path
)

data class MixerStatus(
    var hardware: HardwareStatus,
    var faderStatus: EnumMap<FaderName, FaderStatus>,
    var micStatus: MicSettings,
    var levels: Levels,
    var router: EnumMap<InputDevice, EnumMap<OutputDevice, Boolean>>,
    var coughButton: CoughButton,
    var lighting: Lighting,
    var effects: Effects?,
    var sampler: Sampler?,
    var settings: Settings,
    var buttonDown: EnumMap<Button, Boolean>,
    var profileName: String,
    var micProfileName: String

)

data class Settings(
    var display: Display,
    var muteHoldDuration: UShort,
    var vcMuteAlsoMuteCm: Boolean
)

data class Display(
    var gate: DisplayMode,
    var compressor: DisplayMode,
    var equaliser: DisplayMode,
    var equaliserFine: DisplayMode
)

data class Sampler(
    var recordBuffer: UShort?,
    var banks: HashMap<SampleBank, HashMap<SampleButtons, SamplerButton>>
)

data class SamplerButton(
    var function: SamplePlaybackMode,
    var order: SamplePlayOrder,
    var samples: Vector<Sample>,
    var isPlaying: Boolean
)

data class Sample(
    var name: String,
    var startPct: Float,
    var stopPct: Float
)

data class Effects(
    var isEnabled: Boolean,
    var activePreset: EffectBankPresets,
    var presetNames: HashMap<EffectBankPresets, String>,
    var current: ActiveEffects,
)

data class ActiveEffects(
    var reverb: Reverb,
    var echo: Echo,
    var pitch: Pitch,
    var gender: Gender,
    var megaphone: Megaphone,
    var robot: Robot,
    var hardTune: HardTune,
)

data class Pitch(
    var style: PitchStyle,
    var amount: Byte,
    var character: UByte
)

data class Gender(
    var style: GenderStyle,
    var amount: Byte
)

data class Megaphone(
    var isEnabled: Boolean,
    var style: MegaphoneStyle,
    var amount: UByte,
    var postGain: Byte
)

data class Robot(
    var isEnabled: Boolean,
    var style: RobotStyle,
    var lowGain: Byte,
    var lowFreq: UByte,
    var lowWidth: UByte,
    var midGain: Byte,
    var midFreq: UByte,
    var midWidth: UByte,
    var highGain: Byte,
    var highFreq: UByte,
    var highWidth: UByte,
    var waveform: UByte,
    var pulseWidth: UByte,
    var threshold: Byte,
    var dryMix: Byte
)

data class HardTune(
    var isEnabled: Boolean,
    var style: HardTuneStyle,
    var amount: UByte,
    var rate: UByte,
    var window: UShort,
    var source: HardTuneSource
)

data class Echo(
    var style: EchoStyle,
    var amount: UByte,
    var feedback: UByte,
    var tempo: UShort,
    var delayLeft: UShort,
    var delayRight: UShort,
    var feedbackLeft: UByte,
    var feedbackRight: UByte,
    @JsonProperty("feedback_xfb_l_to_r")
    var feedbackXfbLToR: UByte,
    @JsonProperty("feedback_xfb_r_to_l")
    var feedbackXfbRToL: UByte
)

data class Reverb(
    var style: ReverbStyle,
    var amount: UByte,
    var decay: UShort,
    var earlyLevel: Byte,
    var tailLevel: Byte,
    var preDelay: UByte,
    var loColour: Byte,
    var hiColour: Byte,
    var hiFactor: Byte,
    var diffuse: Byte,
    var modSpeed: Byte,
    var modDepth: Byte,
)

data class Lighting(
    var faders: HashMap<FaderName, FaderLighting>,
    var buttons: HashMap<Button, ButtonLighting>,
    var simple: HashMap<SimpleColourTargets, OneColour>,
    var sampler: HashMap<SamplerColourTargets, SamplerLighting>,
    var encoders: HashMap<EncoderColourTargets, ThreeColours>
)

data class SamplerLighting(
    var offStyle: ButtonColourOffStyle,
    var colours: ThreeColours
)

data class ButtonLighting(
    var offStyle: ButtonColourOffStyle,
    var colours: TwoColours
)

data class FaderLighting(
    var style: FaderDisplayStyle,
    var colours: TwoColours
)

data class OneColour(
    var colourOne: String
)

data class TwoColours(
    var colourOne: String,
    var colourTwo: String
) {
    companion object {
        fun default() = TwoColours("#FFFFFF", "#FFFFFF")
    }
}

data class ThreeColours(
    var colourOne: String,
    var colourTwo: String,
    var colourThree: String
)

data class CoughButton(
    var isToggle: Boolean,
    var muteType: MuteFunction,
    var state: MuteState
)

data class Levels(
    var volumes: EnumMap<ChannelName, UByte>,
    var bleep: Byte,
    var deess: UByte
)

data class HardwareStatus(
    var versions: FirmwareVersions,
    var serialNumber: String,
    var manufacturedDate: String,
    var deviceType: DeviceType,
    var usbDevice: UsbProductInformation
)

data class FirmwareVersions(
    var firmware: Array<UInt>,
    var fpgaCount: UInt,
    var dice: Array<UInt>
)

data class UsbProductInformation(
    var manufacturerName: String,
    var productName: String,
    var version: Array<UByte>,
    var busNumber: UByte,
    var address: UByte,
    var identifier: String?
)

data class FaderStatus(
    var channel: ChannelName,
    var muteType: MuteFunction,
    var scribble: Scribble?,
    var muteState: MuteState
)

data class Scribble(
    var fileName: String?,
    var bottomText: String?,
    var leftText: String?,
    var inverted: Boolean
)

data class MicSettings(
    var micType: MicrophoneType,
    var micGains: EnumMap<MicrophoneType, UShort>?,
    var equaliser: Equaliser,
    var equaliserMini: EqualiserMini,
    var noiseGate: NoiseGate,
    var compressor: Compressor
)

data class Equaliser(
    var gain: HashMap<EqFrequencies, Byte>,
    var frequency: HashMap<EqFrequencies, Float>
)

data class EqualiserMini(
    var gain: HashMap<MiniEqFrequencies, Byte>,
    var frequency: HashMap<MiniEqFrequencies, Float>
)

data class NoiseGate(
    var threshold: Byte,
    var enabled: Boolean,
    var attack: GateTimes,
    var release: GateTimes,
    var attenuation: UByte
)

data class Compressor(
    var threshold: Byte,
    var ratio: CompressorRatio,
    var attack: CompressorAttackTime,
    var release: CompressorReleaseTime,
    var makeupGain: Byte
)





