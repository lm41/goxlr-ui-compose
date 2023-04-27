package de.rmrf.common.data


enum class SamplePlayOrder {
    Sequential,
    Random
}


enum class DisplayMode {
    Simple,
    Advanced
}
enum class SamplePlaybackMode {
    PlayNext,
    PlayStop,
    PlayFade,
    StopOnRelease,
    FadeOnRelease,
    Loop
}
enum class EchoStyle {
    Quarter,
    Eighth,
    Triplet,
    PingPong,
    ClassicSlap,
    MultiTap
}


enum class RobotStyle {
    Robot1,
    Robot2,
    Robot3
}
enum class RobotRange {
    Low,
    Medium,
    High
}


enum class SampleButtons {
    TopLeft,
    TopRight,
    BottomLeft,
    BottomRight
}


enum class HardTuneSource {
    All,
    Music,
    Game,
    LineIn,
    System
}


enum class HardTuneStyle {
    Natural,
    Medium,
    Hard
}
enum class MegaphoneStyle {
    Megaphone,
    Radio,
    OnThePhone,
    Overdrive,
    BuzzCutt,
    Tweed
}
enum class GenderStyle {
    Narrow,
    Medium,
    Wide
}
enum class PitchStyle {
    Narrow,
    Wide
}
enum class ReverbStyle {
    Library,
    DarkBloom,
    MusicClub,
    RealPlate,
    Chapel,
    HockeyArena
}
enum class EffectBankPresets {
    Preset1,
    Preset2,
    Preset3,
    Preset4,
    Preset5,
    Preset6
}
enum class EncoderColourTargets {
    Reverb,
    Pitch,
    Echo,
    Gender
}
enum class SamplerColourTargets {
    SamplerSelectA,
    SamplerSelectB,
    SamplerSelectC
}


enum class SimpleColourTargets {
    Global,
    Accent,
    Scribble1,
    Scribble2,
    Scribble3,
    Scribble4
}
enum class Button {
    // These are all the buttons from the GoXLR Mini.
    Fader1Mute,
    Fader2Mute,
    Fader3Mute,
    Fader4Mute,
    Bleep,
    Cough,

    // The rest are GoXLR Full Buttons. On the mini, they will simply be ignored.
    EffectSelect1,
    EffectSelect2,
    EffectSelect3,
    EffectSelect4,
    EffectSelect5,
    EffectSelect6,

    // FX Button labelled as 'fxClear' in config?
    EffectFx,
    EffectMegaphone,
    EffectRobot,
    EffectHardTune,
    SamplerSelectA,
    SamplerSelectB,
    SamplerSelectC,
    SamplerTopLeft,
    SamplerTopRight,
    SamplerBottomLeft,
    SamplerBottomRight,
    SamplerClear
}


enum class GateTimes {
    Gate10ms,
    Gate20ms,
    Gate30ms,
    Gate40ms,
    Gate50ms,
    Gate60ms,
    Gate70ms,
    Gate80ms,
    Gate90ms,
    Gate100ms,
    Gate110ms,
    Gate120ms,
    Gate130ms,
    Gate140ms,
    Gate150ms,
    Gate160ms,
    Gate170ms,
    Gate180ms,
    Gate190ms,
    Gate200ms,
    Gate250ms,
    Gate300ms,
    Gate350ms,
    Gate400ms,
    Gate450ms,
    Gate500ms,
    Gate550ms,
    Gate600ms,
    Gate650ms,
    Gate700ms,
    Gate750ms,
    Gate800ms,
    Gate850ms,
    Gate900ms,
    Gate950ms,
    Gate1000ms,
    Gate1100ms,
    Gate1200ms,
    Gate1300ms,
    Gate1400ms,
    Gate1500ms,
    Gate1600ms,
    Gate1700ms,
    Gate1800ms,
    Gate1900ms,
    Gate2000ms
}


enum class CompressorAttackTime {
    // Note: 0ms is technically 0.001ms
    Comp0ms,
    Comp2ms,
    Comp3ms,
    Comp4ms,
    Comp5ms,
    Comp6ms,
    Comp7ms,
    Comp8ms,
    Comp9ms,
    Comp10ms,
    Comp12ms,
    Comp14ms,
    Comp16ms,
    Comp18ms,
    Comp20ms,
    Comp23ms,
    Comp26ms,
    Comp30ms,
    Comp35ms,
    Comp40ms
}


enum class CompressorReleaseTime {
    // Note: 0 is technically 15 :)
    Comp0ms,
    Comp15ms,
    Comp25ms,
    Comp35ms,
    Comp45ms,
    Comp55ms,
    Comp65ms,
    Comp75ms,
    Comp85ms,
    Comp100ms,
    Comp115ms,
    Comp140ms,
    Comp170ms,
    Comp230ms,
    Comp340ms,
    Comp680ms,
    Comp1000ms,
    Comp1500ms,
    Comp2000ms,
    Comp3000ms
}


enum class EqFrequencies {
    Equalizer31Hz,
    Equalizer63Hz,
    Equalizer125Hz,
    Equalizer250Hz,
    Equalizer500Hz,
    Equalizer1KHz,
    Equalizer2KHz,
    Equalizer4KHz,
    Equalizer8KHz,
    Equalizer16KHz
}


enum class SampleBank {
    A,
    B,
    C
}


enum class CompressorRatio {
    Ratio1_0,
    Ratio1_1,
    Ratio1_2,
    Ratio1_4,
    Ratio1_6,
    Ratio1_8,
    Ratio2_0,
    Ratio2_5,
    Ratio3_2,
    Ratio4_0,
    Ratio5_6,
    Ratio8_0,
    Ratio16_0,
    Ratio32_0,
    Ratio64_0
}


enum class MiniEqFrequencies {
    Equalizer90Hz,
    Equalizer250Hz,
    Equalizer500Hz,
    Equalizer1KHz,
    Equalizer3KHz,
    Equalizer8KHz
}



enum class DeviceType {
    Unknown,
    Full,
    Mini
}

enum class FaderName {
    A,
    B,
    C,
    D,
}


enum class ChannelName {
    Mic,
    LineIn,
    Console,
    System,
    Game,
    Chat,
    Sample,
    Music,
    Headphones,
    MicMonitor,
    LineOut
}


enum class MuteFunction {
    All,
    ToStream,
    ToVoiceChat,
    ToPhones,
    ToLineOut
}


enum class MuteState {
    Unmuted,
    MutedToX,
    MutedToAll
}


enum class InputDevice {
    Microphone,
    Chat,
    Music,
    Game,
    Console,
    LineIn,
    System,
    Samples
}


enum class OutputDevice {
    Headphones,
    BroadcastMix,
    LineOut,
    ChatMic,
    Sampler
}


enum class MicrophoneType {
    Dynamic,
    Condenser,
    Jack;

    fun getGainParam() : MicrophoneParamKey {
        return when(this){
            Dynamic -> MicrophoneParamKey.DynamicGain
            Condenser -> MicrophoneParamKey.CondenserGain
            Jack -> MicrophoneParamKey.JackGain
        }
    }
}

sealed class MicrophoneParamKey(val paramKey: Int) {
    object MicType: MicrophoneParamKey(0x000)
    object DynamicGain: MicrophoneParamKey(0x001)
    object CondenserGain: MicrophoneParamKey(0x002)
    object JackGain: MicrophoneParamKey(0x003)
    object GateThreshold: MicrophoneParamKey(0x30200)
    object GateAttack: MicrophoneParamKey(0x30400)
    object GateRelease: MicrophoneParamKey(0x30600)
    object GateAttenuation: MicrophoneParamKey(0x30900)
    object CompressorThreshold: MicrophoneParamKey(0x60200)
    object CompressorRatio: MicrophoneParamKey(0x60300)
    object CompressorAttack: MicrophoneParamKey(0x60400)
    object CompressorRelease: MicrophoneParamKey(0x60600)
    object CompressorMakeUpGain: MicrophoneParamKey(0x60700)
    object BleepLevel: MicrophoneParamKey(0x70100)

    /*
      These are the values for the GoXLR mini, it seems there's a difference in how the two
      are setup, The Mini does EQ via mic parameters, where as the full does it via effects.
     */
    object Equalizer90HzFrequency: MicrophoneParamKey(0x40000)
    object Equalizer90HzGain: MicrophoneParamKey(0x40001)
    object Equalizer250HzFrequency: MicrophoneParamKey(0x40003)
    object Equalizer250HzGain: MicrophoneParamKey(0x40004)
    object Equalizer500HzFrequency: MicrophoneParamKey(0x40006)
    object Equalizer500HzGain: MicrophoneParamKey(0x40007)
    object Equalizer1KHzFrequency: MicrophoneParamKey(0x50000)
    object Equalizer1KHzGain: MicrophoneParamKey(0x50001)
    object Equalizer3KHzFrequency: MicrophoneParamKey(0x50003)
    object Equalizer3KHzGain: MicrophoneParamKey(0x50004)
    object Equalizer8KHzFrequency: MicrophoneParamKey(0x50006)
    object Equalizer8KHzGain: MicrophoneParamKey(0x50007)
}


enum class FaderDisplayStyle {
    TwoColour,
    Gradient,
    Meter,
    GradientMeter
}


enum class ButtonColourOffStyle {
    Dimmed,
    Colour2,
    DimmedColour2
}
