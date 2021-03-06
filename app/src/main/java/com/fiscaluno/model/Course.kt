package com.fiscaluno.model

import android.os.Parcel
import android.os.Parcelable
import com.fiscaluno.contracts.SearchContract
import com.google.gson.annotations.SerializedName

data class Course  (
    @SerializedName("course_name") override var name: String? = "",
    @SerializedName("course_type") var courseType: String = "",
    @SerializedName("course_average_rating") override var averageRating: Float = 0f,
    @SerializedName("course_rated_by_count") override var ratedByCount: Int = 0,
    @SerializedName("course_periods") var coursePeriods: List<String> = emptyList(),
    @SerializedName("course_monthly_value_range") var monthlyValueRange: List<Float> = mutableListOf(),
    @SerializedName("course_time_to_graduate_range") var timeToGraduateRange: List<Int> = mutableListOf(),
    @SerializedName("course_duration") var duration: Int = 0, // duration in startYear
    var id: Int = 0,
    @SerializedName("institution") var institution: Institution? = null
) : RateableEntity() {

    constructor(parcel: Parcel) : this(
            name = parcel.readString(),
            courseType = parcel.readString(),
            averageRating = parcel.readFloat(),
            ratedByCount = parcel.readInt(),
            coursePeriods = parcel.createStringArrayList(),
            duration = parcel.readInt(),
            id = parcel.readInt(),
            institution = parcel.readParcelable(Institution::class.java.classLoader)) {
        parcel.readList(monthlyValueRange, Float::class.java.classLoader)
        parcel.readList(timeToGraduateRange, Int::class.java.classLoader)
    }

    override fun search(searchPresenter: SearchContract.Presenter, searchFilter: SearchFilter) {
        searchPresenter.searchCourse(searchFilter)
    }

    override fun setValue(name: String) {
        this.name = name
    }

    override fun getValue() = this.name ?: ""


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(courseType)
        parcel.writeFloat(averageRating)
        parcel.writeInt(ratedByCount)
        parcel.writeStringList(coursePeriods)
        parcel.writeInt(duration)
        parcel.writeInt(id)
        parcel.writeParcelable(institution, flags)
        parcel.writeList(monthlyValueRange)
        parcel.writeList(timeToGraduateRange)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Course> {
        override fun createFromParcel(parcel: Parcel): Course {
            return Course(parcel)
        }

        override fun newArray(size: Int): Array<Course?> {
            return arrayOfNulls(size)
        }
    }

}
