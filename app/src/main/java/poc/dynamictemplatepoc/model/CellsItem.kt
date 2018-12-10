package poc.dynamictemplatepoc.model

import com.google.gson.annotations.SerializedName


data class CellsItem(

	@field:SerializedName("typefield")
	val typefield: Any? = null,

	@field:SerializedName("hidden")
	val hidden: Boolean? = null,

	@field:SerializedName("show")
	val show: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("type")
	val type: Int? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("topSpacing")
	val topSpacing: Double? = null,

	@field:SerializedName("required")
	val required: Boolean? = null
)