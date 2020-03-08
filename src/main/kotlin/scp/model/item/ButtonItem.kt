package scp.model.item

import tornadofx.*
import javafx.beans.property.SimpleStringProperty
import tornadofx.JsonBuilder
import tornadofx.JsonModel
import tornadofx.string
import javax.json.JsonObject

class ButtonItem constructor(): JsonModel {

    constructor(name: String, value: String) : this() {
        this.name = name
        this.value = value
    }

    val nameProperty = SimpleStringProperty()
    var name by nameProperty

    val valueProperty = SimpleStringProperty()
    var value by valueProperty

    override fun updateModel(json: JsonObject) {
        with(json) {
            name = string("name")
            value = string("value")
        }
    }

    override fun toJSON(json: JsonBuilder) {
        with(json) {
            add("name", name)
            add("value", value)
        }
    }
}