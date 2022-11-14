package com.github.jing332.tts_server_android.data

import com.github.jing332.tts_server_android.constant.TtsApiType
import com.github.jing332.tts_server_android.service.systts.help.TtsAudioFormat
import java.io.Serializable

@kotlinx.serialization.Serializable
data class VoiceProperty(
    @TtsApiType var api: Int = TtsApiType.EDGE,
    var format: String = "",
    var locale: String,
    var voiceName: String,
    var voiceId: String? = null,
    var prosody: Prosody,
    var expressAs: ExpressAs? = null
) : Serializable, Cloneable {
    constructor() : this(DEFAULT_VOICE)
    constructor(voiceName: String) : this(voiceName, Prosody())
    constructor(voiceName: String, voiceId: String) : this(
        TtsApiType.CREATION, TtsAudioFormat.DEFAULT,
        DEFAULT_LOCALE,
        voiceName,
        voiceId,
        Prosody(),
        null
    )

    constructor(voiceName: String, prosody: Prosody) : this(
        TtsApiType.EDGE, TtsAudioFormat.DEFAULT,
        DEFAULT_LOCALE,
        voiceName,
        null,
        prosody,
        null
    )

    companion object {
        const val DEFAULT_LOCALE = "zh-CN"
        const val DEFAULT_VOICE = "zh-CN-XiaoxiaoNeural"
        const val DEFAULT_VOICE_ID = "5f55541d-c844-4e04-a7f8-1723ffbea4a9"
    }

    public override fun clone(): VoiceProperty {
        val obj = super.clone() as VoiceProperty
        obj.api = api
        obj.voiceName = voiceName
        obj.voiceId = voiceId
        obj.expressAs = expressAs
        obj.prosody = prosody.clone()

        return obj
    }
}

@kotlinx.serialization.Serializable
data class ExpressAs(
    var style: String? = null,
    var styleDegree: Float = 1F,
    var role: String? = null
) :
    Serializable {
    constructor() : this("", 1F, "")
}

/* Prosody 基本数值参数 单位: %百分比 */
@kotlinx.serialization.Serializable
data class Prosody(
    var rate: Int = RATE_FOLLOW_SYSTEM_VALUE,
    var volume: Int = 0,
    var pitch: Int = 0
) : Serializable, Cloneable {
    companion object {
        const val RATE_FOLLOW_SYSTEM_VALUE = -100
    }

    public override fun clone(): Prosody {
        return super.clone() as Prosody
    }

    fun isRateFollowSystem(): Boolean {
        return rate <= RATE_FOLLOW_SYSTEM_VALUE
    }

    fun setRateIfFollowSystem(sysRate: Int): Prosody {
        if (isRateFollowSystem()) rate = sysRate
        return this
    }

}