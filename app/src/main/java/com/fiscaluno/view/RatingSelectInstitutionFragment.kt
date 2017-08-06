package com.fiscaluno.view

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fiscaluno.R
import com.fiscaluno.contracts.DataManager
import com.fiscaluno.contracts.SelectInstitutionContract
import com.fiscaluno.model.Institution
import com.fiscaluno.presenter.SelectInstitutionPresenter
import com.fiscaluno.view.adapter.InstitutionListAdapter
import com.stepstone.stepper.BlockingStep
import com.stepstone.stepper.Step
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.VerificationError
import java.util.ArrayList

class RatingSelectInstitutionFragment : Fragment(), SelectInstitutionContract.View, Step {

    var presenter : SelectInstitutionContract.Presenter? = null
    var institutionList: RecyclerView? = null
    var searchEt: TextInputEditText? = null
    var adapter: InstitutionListAdapter? = null

    lateinit var dataManager: DataManager

    companion object {
        fun newInstance(): RatingSelectInstitutionFragment {
            val fragment = RatingSelectInstitutionFragment()
            return fragment
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is DataManager) {
            dataManager = context
        } else {
            throw IllegalStateException("Activity must implement DataManager interface!")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = SelectInstitutionPresenter()
        presenter?.bindView(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater!!.inflate(R.layout.fragment_rating_select_institution, container, false)
        institutionList = view.findViewById(R.id.instituitons_rv_rating) as RecyclerView
        searchEt = view.findViewById(R.id.searchInstitution_et_rating) as TextInputEditText
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.loadMainInstitutions()
    }

    private fun setupList(institutions: ArrayList<Institution>){
        adapter = InstitutionListAdapter(institutions, (activity as RatingActivity))
        institutionList?.adapter = adapter
        institutionList?.layoutManager = LinearLayoutManager(context)
    }

    override fun updateInstitutionList(institutions: ArrayList<Institution>) {
        setupList(institutions)
    }

    override fun onSelected() {
    }

    override fun verifyStep(): VerificationError? {
        return null
    }

    override fun onError(error: VerificationError) {
    }

}
