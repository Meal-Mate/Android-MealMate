package com.example.mealmate.ui.dashboard.matching

// SwipeCardAdapter.kt
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mealmate.R

class SwipeCardAdapter(private val cardItems: List<CardItem>) :
    RecyclerView.Adapter<SwipeCardAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(cardItems[position])
    }

    override fun getItemCount(): Int {
        return cardItems.size
    }

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardContainer: ViewGroup = itemView.findViewById(R.id.cardContainer)
        private val image: ImageView = itemView.findViewById(R.id.image)

        fun bind(cardItem: CardItem) {
            // Set your card item data here (e.g., image, text)

            // Example:
            // image.setImageResource(cardItem.imageResId)

            // Set up swipe gesture listener
            cardContainer.setOnTouchListener(object : View.OnTouchListener {
                private var startX = 0f
                private var startY = 0f

                override fun onTouch(view: View, event: MotionEvent): Boolean {
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            startX = event.x
                            startY = event.y
                        }
                        MotionEvent.ACTION_UP -> {
                            val endX = event.x
                            val endY = event.y
                            val deltaX = endX - startX
                            val deltaY = endY - startY

                            // Determine swipe direction based on deltaX and deltaY
                            if (deltaX > 0 && Math.abs(deltaX) > SWIPE_THRESHOLD) {
                                // Swiped right
                                // Implement your logic for a right swipe action
                            } else if (deltaX < 0 && Math.abs(deltaX) > SWIPE_THRESHOLD) {
                                // Swiped left
                                // Implement your logic for a left swipe action
                            } else if (deltaY < 0 && Math.abs(deltaY) > SWIPE_THRESHOLD) {
                                // Swiped up
                                // Implement your logic for an upward swipe action
                            }

                            // Reset card position
                            view.translationX = 0f
                            view.translationY = 0f
                        }
                        MotionEvent.ACTION_MOVE -> {
                            val dx = event.x - startX
                            val dy = event.y - startY
                            view.translationX = dx
                            view.translationY = dy
                        }
                    }
                    return true
                }
            })
        }
    }

    companion object {
        private const val SWIPE_THRESHOLD = 150f
    }
}

data class CardItem(val imageResId: Int)
