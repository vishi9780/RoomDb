package com.example.myapplication.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Data(
        @SerializedName("username") val username: String,
        @SerializedName("userEmail") val userEmail: String,
        @SerializedName("userRegDate") val userRegDate: String,
        @SerializedName("userFirstName") val userFirstName: String,
        @SerializedName("userLastname") val userLastname: String,
        @SerializedName("userState") val userState: String,
        @SerializedName("getUploadedPhotos") val getUploadedPhotos: List<GetUploadedPhotos>) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.createTypedArrayList(GetUploadedPhotos.CREATOR)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(username)
        writeString(userEmail)
        writeString(userRegDate)
        writeString(userFirstName)
        writeString(userLastname)
        writeString(userState)
        writeTypedList(getUploadedPhotos)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Data> = object : Parcelable.Creator<Data> {
            override fun createFromParcel(source: Parcel): Data = Data(source)
            override fun newArray(size: Int): Array<Data?> = arrayOfNulls(size)
        }
    }
}