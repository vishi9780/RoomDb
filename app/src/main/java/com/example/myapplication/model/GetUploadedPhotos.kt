package com.example.myapplication.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class GetUploadedPhotos(
        @SerializedName("id") val id: String,
        @SerializedName("username") val username: String,
        @SerializedName("picture_name") val picture_name: String,
        @SerializedName("picture_medium_name") val picture_medium_name: String,
        @SerializedName("user_id") val user_id: String
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(username)
        writeString(picture_name)
        writeString(picture_medium_name)
        writeString(user_id)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<GetUploadedPhotos> = object : Parcelable.Creator<GetUploadedPhotos> {
            override fun createFromParcel(source: Parcel): GetUploadedPhotos = GetUploadedPhotos(source)
            override fun newArray(size: Int): Array<GetUploadedPhotos?> = arrayOfNulls(size)
        }
    }
}