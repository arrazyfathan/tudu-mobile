package com.arrazyfathan.tudu

import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIApplication
import kotlin.experimental.ExperimentalObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName(swiftName = "AppDelegateAdapter")
class AppDelegateAdapter {
    @OptIn(ExperimentalForeignApi::class)
    fun application(
        application: UIApplication,
        didFinishLaunchingWithOptions: Map<Any?, *>?,
    ): Boolean {
        initNapier()
        return true
    }
}
