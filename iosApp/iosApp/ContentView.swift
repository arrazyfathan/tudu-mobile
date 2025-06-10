import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}

struct ContentView: View {
    @State var scaleAmount = 0.0
    @State var opacityAmount = 0.0
    @State var offsetAmount = 20.0
  @State var isHomeRootScreen = false

  var body: some View {
    ZStack {
      if isHomeRootScreen {
        ComposeView()
      } else {
        Image(.tuduLogo)
        .resizable()
        .aspectRatio(contentMode: .fit)
        .opacity(opacityAmount)
        .offset(y: offsetAmount)
        .frame(width:100)
        .onAppear() {
            withAnimation(.easeOut(duration: 0.3)) {
                opacityAmount = 1.0
                offsetAmount = 0.0
            }

            DispatchQueue.main.asyncAfter(deadline: .now() + 0.8, execute: {
                isHomeRootScreen = true
            })
        }
      }
    }.ignoresSafeArea(( isHomeRootScreen ? .keyboard : .all))
  }
}



