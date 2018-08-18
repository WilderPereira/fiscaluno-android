package com.fiscaluno.view.adapter

import android.content.Context
import com.fiscaluno.model.Course
import java.util.ArrayList

class TopCoursesAdapter(override val rateableEntities: ArrayList<Course>, override var context: Context) : TopRateableEntitiesAdapter(rateableEntities, context) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rateableEntity = rateableEntities[position]

        rateableEntity.let {
            holder.rateableEntityName.text = it.name
            holder.subRareableEntityName.text = it.institution?.name
            holder.averageRating.text = it.averageRating.toString()
            holder.rating.rating = it.averageRating
            holder.ratedByCount.text = it.reviewdBy.toString()
        }

        //TODO: Set imagedrawable
        //holder.rateableEntityImage.setImageDrawable()
        holder.institutionCard.setOnClickListener {
            // TODO: Go to courses tab on rateableEntity screen
        }
    }

}