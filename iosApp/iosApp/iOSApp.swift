import SwiftUI
import ComposeApp

@main
struct iOSApp: App {

    init() {
        InitKoinKt.initKoin()

    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}