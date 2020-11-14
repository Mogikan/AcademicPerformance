package com.example.korney.academicperformance

import android.app.AlertDialog
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

import com.example.korney.academicperformance.GroupFragment.OnListFragmentInteractionListener
import com.example.korney.academicperformance.model.BusinessLogic
import com.example.korney.academicperformance.model.StudyGroup
import io.realm.RealmList
import kotlinx.android.synthetic.main.student_edit.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class GroupRecyclerViewAdapter(private var mValues: List<StudyGroup>, private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<GroupRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_group, parent, false)
        val viewHolder = ViewHolder(view)
        val delete = view.findViewById<ImageButton>(R.id.delete)
        delete.setOnClickListener {
            BusinessLogic.deleteGroup(viewHolder.mItem!!)
            updateItems()
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        //holder.mIdView.text = mValues[position]?.id
        holder.mContentView.text = mValues[position].name

        holder.mView.setOnClickListener {
            mListener?.onListFragmentInteraction(holder.mItem!!)
        }
    }

    fun updateItems(){
        mValues = BusinessLogic.getGroups()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        //val mId: String
        val mContentView: TextView
        var mItem: StudyGroup? = null

        init {
            //mId = mView.findViewById<View>(R.id.id) as TextView
            mContentView = mView.findViewById<View>(R.id.content) as TextView
        }

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
