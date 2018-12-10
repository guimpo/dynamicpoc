package poc.dynamictemplatepoc.model

import com.google.gson.annotations.SerializedName


data class Screen(

	@field:SerializedName("riskTitle")
	val riskTitle: String? = null,

	@field:SerializedName("infoTitle")
	val infoTitle: String? = null,

	@field:SerializedName("whatIs")
	val whatIs: String? = null,

	@field:SerializedName("definition")
	val definition: String? = null,

	@field:SerializedName("risk")
	val risk: Int? = null,

	@field:SerializedName("downInfo")
	val downInfo: List<DownInfoItem?>? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("fundName")
	val fundName: String? = null,

	@field:SerializedName("moreInfo")
	val moreInfo: MoreInfo? = null,

	@field:SerializedName("info")
	val info: List<InfoItem?>? = null
)