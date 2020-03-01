package scp.view

import javafx.beans.property.SimpleStringProperty
import javafx.scene.paint.Color
import javafx.stage.StageStyle
import tornadofx.*

class MainView: View() {
    val controller: MyController by inject()
    val input = SimpleStringProperty()

    override val root = form {
        fieldset {
            field("Input") {
                textfield(input)
            }

            button("Commit") {
                textFill = Color.RED
                action {
                    find<MyFragment>().openModal(stageStyle = StageStyle.UTILITY)
                    runAsync {
                        controller.writeToDb(input.value)
                    } ui {
                        input.value = ""
                    }
                }
            }
        }
    }
}

class MyFragment: Fragment() {
    override val root = label("This is a popup")
}

class MyController: Controller() {
    fun writeToDb(inputValue: String) {
        println("Write string $inputValue to DB")
        Thread.sleep(2000)
    }
}