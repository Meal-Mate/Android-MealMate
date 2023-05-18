import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealmate.R
import com.example.mealmate.ui.dashboard.matching.CardItem
import com.example.mealmate.ui.dashboard.matching.SwipeCardAdapter

class MatchingFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SwipeCardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.matching_fragment, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)

        // Initialize your list of card items
        val cardItems = listOf(
            CardItem(R.drawable.image1),
            CardItem(R.drawable.image2),
            CardItem(R.drawable.image3),
            // Add more card items as needed
        )

        // Set up RecyclerView and adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = SwipeCardAdapter(cardItems)
        recyclerView.adapter = adapter

        return view
    }

    override fun onResume() {
        super.onResume()
        // Notify the adapter that the data set has changed
        adapter.notifyDataSetChanged()
    }
}
