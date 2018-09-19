package com.fiscaluno.contracts

import com.fiscaluno.model.DetailedReview
import com.fiscaluno.model.GeneralReview

/**
 * Created by Wilder on 16/07/17.
 */

interface ReviewsContract {
    interface View {
        fun setupGeneralReviews(generalReviews: List<GeneralReview>?)
        fun setupDetailedReviews(detailedReviews: List<DetailedReview>?)
    }

    interface Presenter {
        fun bindView(view: ReviewsContract.View)
        fun loadGeneralReviews(institutionId: String, courseId: String? = null)
        fun loadDetailedReviews(institutionId: String, courseId: String? = null)
    }
}
