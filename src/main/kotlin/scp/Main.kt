package scp

import scp.view.MainView
import scp.view.add.AddItemView
import tornadofx.App
import tornadofx.launch

class Main: App(AddItemView::class)

fun main() {
    launch<Main>()
}