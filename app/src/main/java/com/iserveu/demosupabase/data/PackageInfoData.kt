package com.iserveu.demosupabase.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PackageInfoData(
    @SerialName("AdminName")
    val adminName: String? = "",
    @SerialName("AepsDriverActivityUpdate")
    val aepsDriverActivityUpdate: String? = "",
    @SerialName("AppCode")
    val appCode: String? = "",
    @SerialName("AppName")
    val appName: String? = "",
    @SerialName("BannerList")
    val bannerList: List<Banner>? = listOf(),
    @SerialName("BetaTesting")
    val betaTesting: BetaTesting? = BetaTesting(),
    @SerialName("CreatedBy")
    val createdBy: String? = "",
    @SerialName("CustomTheme")
    val customTheme: String? = "",
    @SerialName("DeviceSetup")
    val deviceSetup: DeviceSetup? = DeviceSetup(),
    @SerialName("MainApp")
    val mainApp: String? = "",
    @SerialName("Onboard")
    val onboard: Onboard? = Onboard(),
    @SerialName("Products")
    val products: Products? = Products(),
    @SerialName("Training")
    val training: Training? = Training(),
    @SerialName("UnifiedAePSEnable")
    val unifiedAePSEnable: UnifiedAePSEnable? = UnifiedAePSEnable(),
    @SerialName("UniqueId")
    val uniqueId: String? = "",
    @SerialName("VersionName")
    val versionName: String? = "",
    @SerialName("WalletTopup")
    val walletTopup: WalletTopup? = WalletTopup(),
    @SerialName("zendeskForm")
    val zendeskForm: ZendeskForm? = ZendeskForm()
)