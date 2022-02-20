//class for Adapter of recyclerView
package com.ayushunleashed.animeQuotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

// created Adapter , work of adapter is to handle data and view holders

//passing items arraylist as our data to be shown
// adapter needs view holders so we created a class for view holders named NewViewHolder
//RecyclerView.Adapter is a interface , we derive it in our class and override functions


class NewsListAdapter(private val listener:NewsItemClicked): RecyclerView.Adapter<NewViewHolder>() {

    val items:ArrayList<Quote> = ArrayList()
    // oncCreate called when viewholder is created, return a viewholder ,every view holder has argument in which a view is send
    // (which was converted from xml) || this view is what we will see repeated , eg: one instagram post has (photo,interaction)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {
        //creating view holder
        //converting xml to view to pass it to NewsViewHolder class
        //onCreate => inflate xml code to view , in turn passed to create ViewHolder
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder = NewViewHolder(view)

        view.setOnClickListener {
            //using listener object of main activity which was in turn derieved from interface to access function of interface
          listener.onItemClicked(items[viewHolder.adapterPosition])
        }

        return viewHolder     //returning instance of NewsViewHolder //it require view that's why we created
    }

    //bind given data(currently in string from ) to our view holders
    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {
        //taking string data from our list and fitting it onto viewholder to set ot on recyclerview
        val currItem = items[position]
        // holder is  a view holder
        holder.animeName.text = items[position].animeName;
        holder.animeCharacter.text = items[position].animeCharacter;
        holder.animeQuote.text = items[position].animeQuote;
    }

    override fun getItemCount(): Int {
        return items.size
    }

    public fun updateNews(updatedNews:ArrayList<Quote>)
    {
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged() //reloads all three functions
    }

}

// what will individual item contain => everything in the view holder
class NewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{   //a text view from our repeating view.
    val animeName:TextView = itemView.findViewById(R.id.animeName)
    val animeCharacter:TextView = itemView.findViewById(R.id.animeCharacter)
    val animeQuote:TextView = itemView.findViewById(R.id.animeQuote)
}

interface NewsItemClicked
{
    fun onItemClicked(item:Quote)
}