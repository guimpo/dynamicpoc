package poc.dynamictemplatepoc.model

import com.google.gson.annotations.SerializedName


data class InfoItem(

	@field:SerializedName("data")
	val data: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)