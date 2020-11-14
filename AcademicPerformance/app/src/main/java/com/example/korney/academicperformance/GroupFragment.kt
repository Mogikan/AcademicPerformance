package com.example.korney.academicperformance

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.example.korney.academicperformance.model.BusinessLogic
import com.example.korney.academicperformance.model.StudyGroup
import io.realm.Realm

/**
 * A fragment representing a list of Items.
 *
 *
 * Activities containing this fragment MUST implement the [OnListFragmentInteractionListener]
 * interface.
 */
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
class GroupFragment : Fragment() {
    // TODO: Customize parameters
    private var mColumnCount = 1
    private var mListener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            mColumnCount = arguments.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater!!.inflate(R.layout.fragment_group_list, container, false)

        val newGroupTextBox = root.findViewById<EditText>(R.id.newGroupId)


        val groupsRecycler = root.findViewById<RecyclerView>(R.id.groupList)

        val context = groupsRecycler.getContext()
        if (mColumnCount <= 1) {
            groupsRecycler.layoutManager = LinearLayoutManager(context)
        } else {
            groupsRecycler.layoutManager = GridLayoutManager(context, mColumnCount)
        }
        val adapter = GroupRecyclerViewAdapter(BusinessLogic.getGroups(), mListener)
        groupsRecycler.adapter = adapter
        val button = root.findViewById<Button>(R.id.addNewButton)
        button.setOnClickListener {
            BusinessLogic.addGroup(newGroupTextBox.text.toString())
            adapter.updateItems()
            newGroupTextBox.text.clear()
        }


        return root
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: StudyGroup)
    }

    companion object {

        // TODO: Customize parameter argument names
        private val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        fun newInstance(columnCount: Int): GroupFragment {
            val fragment = GroupFragment()
            val args = Bundle()
            args.putInt(ARG_COLUMN_COUNT, columnCount)
            fragment.arguments = args
            return fragment
        }
    }
}
