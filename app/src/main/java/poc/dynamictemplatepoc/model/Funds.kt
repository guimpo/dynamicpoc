package poc.dynamictemplatepoc.model

import com.google.gson.annotations.SerializedName


data class Funds(

	@field:SerializedName("screen")
	val screen: Screen? = null
)