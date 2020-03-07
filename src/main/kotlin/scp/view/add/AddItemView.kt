package scp.view.add

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import scp.event.AddItemEvent
import scp.model.item.ButtonItem
import tornadofx.*

class AddItemView : View() {
    val name = SimpleStringProperty()
    val value = SimpleStringProperty()
    val isCloseOnSave = SimpleBooleanProperty()

    override val root = form {

        fieldset {
            field("Name:") {
                textfield(name)
            }

            field("Value:") {
                textfield(value) {
                    addEventFilter(KeyEvent.KEY_PRESSED) { event ->
                        if(event.code == KeyCode.ENTER) {
                            log.info {"Enter was pressed" }

                            if (isCloseOnSave.value) {
                                closeWindow()
                            }
                        }
                    }
                }
            }

            hbox {
                checkbox("Close on save?", isCloseOnSave) {
                    action {
                        // save property to json program data
                    }
                }

                button("Save") {
                    action {
                        //fire(AddItemEvent())
                    }
                }

                button("Close") {
                    action {
                        closeWindow()
                    }
                }
            }
        }
    }

    fun closeWindow() {

    }

}