package de.rmrf.common.io

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonSubTypes
import de.rmrf.common.data.*
import de.rmrf.common.io.GoXLRCommand.*
import kotlinx.serialization.Serializable


@Serializable
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@JsonSubTypes(
    JsonSubTypes.Type(value = SetShutdownCommands::class, name = "SetShutdownCommands"),
    JsonSubTypes.Type(value = SetSamplerPreBufferDuration::class, name = "SetSamplerPreBufferDuration"),
    JsonSubTypes.Type(value = SetFader::class, name = "SetFader"),
    JsonSubTypes.Type(value = SetFaderMuteFunction::class, name = "SetFaderMuteFunction"),
    JsonSubTypes.Type(value = SetVolume::class, name = "SetVolume"),
    JsonSubTypes.Type(value = SetMicrophoneType::class, name = "SetMicrophoneType"),
    JsonSubTypes.Type(value = SetMicrophoneGain::class, name = "SetMicrophoneGain"),
    JsonSubTypes.Type(value = SetRouter::class, name = "SetRouter"),
    JsonSubTypes.Type(value = SetCoughMuteFunction::class, name = "SetCoughMuteFunction"),
    JsonSubTypes.Type(value = SetCoughIsHold::class, name = "SetCoughIsHold"),
    JsonSubTypes.Type(value = SetSwearButtonVolume::class, name = "SetSwearButtonVolume"),
    JsonSubTypes.Type(value = SetEqMiniGain::class, name = "SetEqMiniGain"),
    JsonSubTypes.Type(value = SetEqMiniFreq::class, name = "SetEqMiniFreq"),
    JsonSubTypes.Type(value = SetEqGain::class, name = "SetEqGain"),
    JsonSubTypes.Type(value = SetEqFreq::class, name = "SetEqFreq"),
    JsonSubTypes.Type(value = SetGateThreshold::class, name = "SetGateThreshold"),
    JsonSubTypes.Type(value = SetGateAttenuation::class, name = "SetGateAttenuation"),
    JsonSubTypes.Type(value = SetGateAttack::class, name = "SetGateAttack"),
    JsonSubTypes.Type(value = SetGateRelease::class, name = "SetGateRelease"),
    JsonSubTypes.Type(value = SetGateActive::class, name = "SetGateActive"),
    JsonSubTypes.Type(value = SetCompressorThreshold::class, name = "SetCompressorThreshold"),
    JsonSubTypes.Type(value = SetCompressorRatio::class, name = "SetCompressorRatio"),
    JsonSubTypes.Type(value = SetCompressorAttack::class, name = "SetCompressorAttack"),
    JsonSubTypes.Type(value = SetCompressorReleaseTime::class, name = "SetCompressorReleaseTime"),
    JsonSubTypes.Type(value = SetCompressorMakeupGain::class, name = "SetCompressorMakeupGain"),
    JsonSubTypes.Type(value = SetElementDisplayMode::class, name = "SetElementDisplayMode"),
    JsonSubTypes.Type(value = SetDeeser::class, name = "SetDeeser"),
    JsonSubTypes.Type(value = SetFaderDisplayStyle::class, name = "SetFaderDisplayStyle"),
    JsonSubTypes.Type(value = SetFaderColours::class, name = "SetFaderColours"),
    JsonSubTypes.Type(value = SetAllFaderColours::class, name = "SetAllFaderColours"),
    JsonSubTypes.Type(value = SetAllFaderDisplayStyle::class, name = "SetAllFaderDisplayStyle"),
    JsonSubTypes.Type(value = SetButtonColours::class, name = "SetButtonColours")
)
sealed class GoXLRCommand {

    /*private companion object {
        @JsonCreator
        @JvmStatic
        fun findBySimpleClassName(simpleName: String) : GoXLRCommand? {
            return GoXLRCommand::class.sealedSubclasses.first {
                it.simpleName == simpleName
            }.objectInstance
        }
    }*/

    @Serializable
    data class SetShutdownCommands(val commands: List<GoXLRCommand>) : GoXLRCommand()

    @Serializable
    data class SetSamplerPreBufferDuration(val duration: UShort) : GoXLRCommand()

    //@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
    data class SetFader(

        val faderName: FaderName, val channelName: ChannelName
    ) : GoXLRCommand()

    @Serializable
    data class SetFaderMuteFunction(val faderName: FaderName, val muteFunction: MuteFunction) : GoXLRCommand()

    @Serializable

    data class SetVolume(val channelName: ChannelName, val volume: UByte) : GoXLRCommand()
    data class SetMicrophoneType(val microphoneType: MicrophoneType) : GoXLRCommand()
    data class SetMicrophoneGain(val microphoneType: MicrophoneType, val gain: UShort) : GoXLRCommand()
    data class SetRouter(val inputDevice: InputDevice, val outputDevice: OutputDevice, val enabled: Boolean) :
        GoXLRCommand()

    // Cough Button
    data class SetCoughMuteFunction(val muteFunction: MuteFunction) : GoXLRCommand()
    data class SetCoughIsHold(val isHold: Boolean) : GoXLRCommand()

    // Bleep Button
    data class SetSwearButtonVolume(val volume: Byte) : GoXLRCommand()

    // EQ Settings
    data class SetEqMiniGain(val frequency: MiniEqFrequencies, val gain: Byte) : GoXLRCommand()
    data class SetEqMiniFreq(val frequency: MiniEqFrequencies, val freq: Float) : GoXLRCommand()
    data class SetEqGain(val frequency: EqFrequencies, val gain: Byte) : GoXLRCommand()
    data class SetEqFreq(val frequency: EqFrequencies, val freq: Float) : GoXLRCommand()

    // Gate Settings
    data class SetGateThreshold(val threshold: Byte) : GoXLRCommand()
    data class SetGateAttenuation(val attenuation: UByte) : GoXLRCommand()
    data class SetGateAttack(val attack: GateTimes) : GoXLRCommand()
    data class SetGateRelease(val release: GateTimes) : GoXLRCommand()
    data class SetGateActive(val active: Boolean) : GoXLRCommand()

    // Compressor..
    data class SetCompressorThreshold(val threshold: Byte) : GoXLRCommand()
    data class SetCompressorRatio(val ratio: CompressorRatio) : GoXLRCommand()
    data class SetCompressorAttack(val attack: CompressorAttackTime) : GoXLRCommand()
    data class SetCompressorReleaseTime(val releaseTime: CompressorReleaseTime) : GoXLRCommand()
    data class SetCompressorMakeupGain(val gain: Byte) : GoXLRCommand()

    // Used to switch between display modes.
    data class SetElementDisplayMode(val component: DisplayModeComponents, val displayMode: DisplayMode) :
        GoXLRCommand()

    // DeEss
    data class SetDeeser(val value: UByte) : GoXLRCommand()

    // Colour Related Settings..

    data class SetFaderDisplayStyle(val faderName: FaderName, val faderDisplayStyle: FaderDisplayStyle) : GoXLRCommand()
    data class SetFaderColours(val faderName: FaderName, val color1: String, val color2: String) : GoXLRCommand()
    data class SetAllFaderColours(val color1: String, val color2: String) : GoXLRCommand()
    data class SetAllFaderDisplayStyle(val faderDisplayStyle: FaderDisplayStyle) : GoXLRCommand()

    data class SetButtonColours(val button: Button, val color1: String, val color2: String?) : GoXLRCommand()
    data class SetButtonOffStyle(val button: Button, val buttonColourOffStyle: ButtonColourOffStyle) : GoXLRCommand()
    data class SetButtonGroupColours(
        val buttonColourGroups: ButtonColourGroups,
        val color1: String,
        val color2: String?
    ) : GoXLRCommand()

    data class SetButtonGroupOffStyle(
        val buttonColourGroups: ButtonColourGroups,
        val buttonColourOffStyle: ButtonColourOffStyle
    ) : GoXLRCommand()

    data class SetSimpleColour(val simpleColourTargets: SimpleColourTargets, val color: String) : GoXLRCommand()
    data class SetEncoderColour(
        val encoderColourTargets: EncoderColourTargets,
        val color1: String,
        val color2: String,
        val color3: String
    ) : GoXLRCommand()

    data class SetSampleColour(
        val samplerColourTargets: SamplerColourTargets,
        val color1: String,
        val color2: String,
        val color3: String
    ) : GoXLRCommand()

    data class SetSampleOffStyle(
        val samplerColourTargets: SamplerColourTargets,
        val buttonColourOffStyle: ButtonColourOffStyle
    ) : GoXLRCommand()

    // Effect Related Settings..
    data class LoadEffectPreset(val presetName: String) : GoXLRCommand()
    data class RenameActivePreset(val presetName: String) : GoXLRCommand()
    object SaveActivePreset : GoXLRCommand()

    // Reverb
    data class SetReverbStyle(val reverbStyle: ReverbStyle) : GoXLRCommand()
    data class SetReverbAmount(val amount: UByte) : GoXLRCommand()
    data class SetReverbDecay(val decay: UShort) : GoXLRCommand()
    data class SetReverbEarlyLevel(val earlyLevel: Byte) : GoXLRCommand()
    data class SetReverbTailLevel(val tailLevel: Byte) : GoXLRCommand()
    data class SetReverbPreDelay(val preDelay: UByte) : GoXLRCommand()
    data class SetReverbLowColour(val lowColour: Byte) : GoXLRCommand()
    data class SetReverbHighColour(val highColour: Byte) : GoXLRCommand()
    data class SetReverbHighFactor(val highFactor: Byte) : GoXLRCommand()
    data class SetReverbDiffuse(val diffuse: Byte) : GoXLRCommand()
    data class SetReverbModSpeed(val modSpeed: Byte) : GoXLRCommand()
    data class SetReverbModDepth(val modDepth: Byte) : GoXLRCommand()

    // Echo..
    data class SetEchoStyle(val style: EchoStyle) : GoXLRCommand()
    data class SetEchoAmount(val amount: UByte) : GoXLRCommand()
    data class SetEchoFeedback(val feedback: UByte) : GoXLRCommand()
    data class SetEchoTempo(val tempo: UShort) : GoXLRCommand()
    data class SetEchoDelayLeft(val delay: UShort) : GoXLRCommand()
    data class SetEchoDelayRight(val delay: UShort) : GoXLRCommand()
    data class SetEchoFeedbackLeft(val feedback: UByte) : GoXLRCommand()
    data class SetEchoFeedbackRight(val feedback: UByte) : GoXLRCommand()
    data class SetEchoFeedbackXFBLtoR(val feedback: UByte) : GoXLRCommand()
    data class SetEchoFeedbackXFBRtoL(val feedback: UByte) : GoXLRCommand()

    // Pitch
    data class SetPitchStyle(val style: PitchStyle) : GoXLRCommand()
    data class SetPitchAmount(val amount: Byte) : GoXLRCommand()
    data class SetPitchCharacter(val character: UByte) : GoXLRCommand()

    // Gender
    data class SetGenderStyle(val style: GenderStyle) : GoXLRCommand()
    data class SetGenderAmount(val amount: Byte) : GoXLRCommand()

    // Megaphone
    data class SetMegaphoneStyle(val style: MegaphoneStyle) : GoXLRCommand()
    data class SetMegaphoneAmount(val amount: UByte) : GoXLRCommand()
    data class SetMegaphonePostGain(val gain: Byte) : GoXLRCommand()

    // Robot
    data class SetRobotStyle(val style: RobotStyle) : GoXLRCommand()
    data class SetRobotGain(val range: RobotRange, val gain: Byte) : GoXLRCommand()
    data class SetRobotFreq(val range: RobotRange, val freq: UByte) : GoXLRCommand()
    data class SetRobotWidth(val range: RobotRange, val width: UByte) : GoXLRCommand()
    data class SetRobotWaveform(val waveform: UByte) : GoXLRCommand()
    data class SetRobotPulseWidth(val pulseWidth: UByte) : GoXLRCommand()
    data class SetRobotThreshold(val threshold: Byte) : GoXLRCommand()
    data class SetRobotDryMix(val dryMix: Byte) : GoXLRCommand()

    // Hardtune
    data class SetHardTuneStyle(val style: HardTuneStyle) : GoXLRCommand()
    data class SetHardTuneAmount(val amount: UByte) : GoXLRCommand()
    data class SetHardTuneRate(val rate: UByte) : GoXLRCommand()
    data class SetHardTuneWindow(val window: UShort) : GoXLRCommand()
    data class SetHardTuneSource(val source: HardTuneSource) : GoXLRCommand()

    data class SetSamplerFunction(
        val sampleBank: SampleBank,
        val sampleButtons: SampleButtons,
        val samplePlaybackMode: SamplePlaybackMode
    ) : GoXLRCommand()

    data class SetSamplerOrder(
        val sampleBank: SampleBank,
        val sampleButtons: SampleButtons,
        val samplePlayOrder: SamplePlayOrder
    ) : GoXLRCommand()

    data class AddSample(
        val sampleBank: SampleBank,
        val sampleButtons: SampleButtons,
        val string: String
    ) : GoXLRCommand()

    data class SetSampleStartPercent(
        val sampleBank: SampleBank,
        val sampleButtons: SampleButtons,
        val index: Int,
        val percentage: Float
    ) : GoXLRCommand()

    data class SetSampleStopPercent(
        val sampleBank: SampleBank,
        val sampleButtons: SampleButtons,
        val index: Int,
        val percentage: Float
    ) : GoXLRCommand()

    data class RemoveSampleByIndex(
        val sampleBank: SampleBank,
        val sampleButtons: SampleButtons,
        val index: Int
    ) : GoXLRCommand()

    data class PlaySampleByIndex(
        val sampleBank: SampleBank,
        val sampleButtons: SampleButtons,
        val index: Int
    ) : GoXLRCommand()

    data class PlayNextSample(
        val sampleBank: SampleBank,
        val sampleButtons: SampleButtons
    ) : GoXLRCommand()

    data class StopSamplePlayback(
        val sampleBank: SampleBank,
        val sampleButtons: SampleButtons
    ) : GoXLRCommand()

    data class SetScribbleIcon(
        val faderName: FaderName,
        val string: String
    ) : GoXLRCommand()

    data class SetScribbleText(
        val faderName: FaderName,
        val string: String
    ) : GoXLRCommand()

    data class SetScribbleNumber(
        val faderName: FaderName,
        val string: String
    ) : GoXLRCommand()

    data class SetScribbleInvert(
        val faderName: FaderName,
        val boolean: Boolean
    ) : GoXLRCommand()

    data class NewProfile(val string: String) : GoXLRCommand()
    data class LoadProfile(val string: String) : GoXLRCommand()
    data class LoadProfileColours(val string: String) : GoXLRCommand()
    object SaveProfile : GoXLRCommand()
    data class SaveProfileAs(val string: String) : GoXLRCommand()
    data class DeleteProfile(val string: String) : GoXLRCommand()

    data class NewMicProfile(val string: String) : GoXLRCommand()
    data class LoadMicProfile(val string: String) : GoXLRCommand()
    object SaveMicProfile : GoXLRCommand()
    data class SaveMicProfileAs(val string: String) : GoXLRCommand()
    data class DeleteMicProfile(val string: String) : GoXLRCommand()

    // General Settings
    data class SetMuteHoldDuration(val duration: UShort) : GoXLRCommand()
    data class SetVCMuteAlsoMuteCM(val enabled: Boolean) : GoXLRCommand()

    // These control the current GoXLR 'State'..
    data class SetActiveEffectPreset(val preset: EffectBankPresets) : GoXLRCommand()
    data class SetActiveSamplerBank(val bank: SampleBank) : GoXLRCommand()
    data class SetMegaphoneEnabled(val enabled: Boolean) : GoXLRCommand()
    data class SetRobotEnabled(val enabled: Boolean) : GoXLRCommand()
    data class SetHardTuneEnabled(val enabled: Boolean) : GoXLRCommand()
    data class SetFXEnabled(val enabled: Boolean) : GoXLRCommand()
    data class SetFaderMuteState(val faderName: FaderName, val muteState: MuteState) : GoXLRCommand()
    data class SetCoughMuteState(val muteState: MuteState) : GoXLRCommand()

    // Submix Commands
    data class SetSubMixEnabled(val enabled: Boolean) : GoXLRCommand()
    data class SetSubMixVolume(val channelName: ChannelName, val volume: UByte) : GoXLRCommand()
    data class SetSubMixLinked(val channelName: ChannelName, val linked: Boolean) : GoXLRCommand()
    data class SetSubMixOutputMix(val outputDevice: OutputDevice, val mix: Mix) : GoXLRCommand()
}
