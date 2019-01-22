package com.example.webtrekk.androidsdk

import webtrekk.android.sdk.model.Param
import webtrekk.android.sdk.model.ParamType
import webtrekk.android.sdk.model.customParam

val Param.BACKGROUND_COLOR
    get() = customParam(ParamType.PAGE_PARAM, 100)

val Param.TRACKING_LOCATION
    get() = customParam(ParamType.SESSION_PARAM, 10)
