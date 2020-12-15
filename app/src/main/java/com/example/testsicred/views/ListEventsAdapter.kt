package com.example.testsicred.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.model.EventItem
import com.example.testsicred.databinding.RecyclerViewEventItemBinding

class ListEventsAdapter(
    private val listEventsListener: ListEventsListener
): RecyclerView.Adapter<ListEventsAdapter.ListEventViewHolder>() {

    var listOfEvents: List<EventItem> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListEventViewHolder(
           RecyclerViewEventItemBinding.inflate(
               LayoutInflater.from(parent.context),
               parent,
               false
           )
        )

    override fun onBindViewHolder(holder: ListEventViewHolder, position: Int) {
        getItemId(position).let { holder.bind(listOfEvents[position]) }
    }

    override fun getItemCount() = listOfEvents.size

    inner class ListEventViewHolder(private val viewEventItemBinding: RecyclerViewEventItemBinding):
        RecyclerView.ViewHolder(viewEventItemBinding.root){

        fun bind(eventResponseBodyItem: EventItem){
            viewEventItemBinding.eventItem = eventResponseBodyItem
            viewEventItemBinding.listEventsListener = View.OnClickListener {
                listEventsListener.seeDetail(eventResponseBodyItem.id)
            }
        }
    }

    interface ListEventsListener{
        fun seeDetail(id: String)
    }
}
