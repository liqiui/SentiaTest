package com.example.sentiatest.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    val address: String,
    val state:String,
    val suburb: String,
    val postcode: Int?,
    val latitude: Double,
    val longitude: Double
): Parcelable

@Parcelize
data class Owner(
    @Json(name="first_name") val firstName:String,
    @Json(name="last_name")  val lastName: String,
    val dob: String,
    val avatar: Avatar
): Parcelable

@Parcelize
data class Avatar(
    val small: Small,
    val medium: Medium,
    val large : Large
): Parcelable

@Parcelize
data class Small(
    val url: String
): Parcelable

@Parcelize
data class Medium(
    val url: String
): Parcelable

@Parcelize
data class Large(
    val url: String
): Parcelable

@Parcelize
data class PropertyImages(
    val id: Int,
    val attachment: Attachment
): Parcelable

@Parcelize
data class Attachment(
    val url:String,
    val thumb: Thumb,
    val medium: Medium,
    val large: Large
): Parcelable

@Parcelize
data class Thumb(
    val url: String
): Parcelable

@Parcelize
data class Agent(
    @Json(name ="first_name")val firstName: String,
    @Json(name = "last_name") val lastName: String,
    @Json(name = "company_name") val companyName:String,
    val avatar: Avatar
): Parcelable

@Parcelize
data class Data (
    val id:String,
    @Json(name = "auction_date") val auctionDate: String,
    @Json(name = "available_from") val availableFrom:String,
    val bedrooms: Int,
    val bathrooms: Int,
    val carspaces: Int,
    @Json(name ="date_first_listed") val dateFirstListed :String,
    @Json(name="date_updated") val dateUpdated:String,
    val description:String,
    @Json(name = "display_price") val displayPrice:String,
    val currency:String,
    val location: Location,
    val owner: Owner,
    @Json(name = "property_images")val propertyImages: List<PropertyImages>,
    val agent: Agent,
    @Json(name= "property_type") val propertyType:String ,
    @Json(name="sale_type") val saleType:String
): Parcelable

@Parcelize
data class Result(
    val data: List<Data>
): Parcelable




