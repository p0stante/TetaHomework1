package ru.avtoropova.tetahomework1.model.dto

import android.os.Parcel
import android.os.Parcelable

data class ActorDto(
    val name: String?,
    val imageUrl: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(imageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ActorDto> {
        override fun createFromParcel(parcel: Parcel): ActorDto {
            return ActorDto(parcel)
        }

        override fun newArray(size: Int): Array<ActorDto?> {
            return arrayOfNulls(size)
        }
    }
}