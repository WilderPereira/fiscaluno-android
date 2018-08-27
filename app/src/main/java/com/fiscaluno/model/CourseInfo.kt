package com.fiscaluno.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CourseInfo (
        @SerializedName("course_type") val courseType: String? = null,
        @SerializedName("period") val period: String? = null,
        @SerializedName("semester") val semester: Int? = null,
        @SerializedName("course_name") val courseName: String,
        @SerializedName("monthly_payment_value") val monthlyPaymentValue: Float? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readValue(Float::class.java.classLoader) as? Float) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(courseType)
        parcel.writeString(period)
        parcel.writeValue(semester)
        parcel.writeString(courseName)
        parcel.writeValue(monthlyPaymentValue)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CourseInfo> {
        override fun createFromParcel(parcel: Parcel): CourseInfo {
            return CourseInfo(parcel)
        }

        override fun newArray(size: Int): Array<CourseInfo?> {
            return arrayOfNulls(size)
        }
    }
}