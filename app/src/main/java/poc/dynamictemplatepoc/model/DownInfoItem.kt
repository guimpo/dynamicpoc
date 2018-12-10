package poc.dynamictemplatepoc.model

import com.google.gson.annotations.SerializedName


data class DownInfoItem(

	@field:SerializedName("data")
	val data: Any? = null,

	@field:SerializedName("name")
	val name: String? = null
)