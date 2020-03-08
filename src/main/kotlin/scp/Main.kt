package scp

import scp.io.FileSystemService
import scp.view.MainView
import tornadofx.App
import tornadofx.launch

class Main: App(MainView::class)

fun main() {
    FileSystemService.instance.createFileStructureOnStartup()
    launch<Main>()
}