//
//  AppDelegate.swift
//  iosApp
//
//  Created by Ar Razy Fathan Rabbani on 14/06/25.
//
import Foundation
import SwiftUI
import ComposeApp
import Firebase

class AppDelegate: NSObject, UIApplicationDelegate {
    private let appDelegateAdapter = AppDelegateAdapter()
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        FirebaseApp.configure()
        return appDelegateAdapter.application(application: application, didFinishLaunchingWithOptions: launchOptions)
    }
}
