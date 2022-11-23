package gt.uvg.eatify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gt.uvg.eatify.databinding.FragmentMainBinding
import gt.uvg.eatify.model.RecipeResponse
import gt.uvg.eatify.repository.RecipeAPI
import retrofit2.Call
import retrofit2.Response

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var  recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val client = RecipeAPI.retrofitService.getFirst10Recipes()
        client.enqueue(object:retrofit2.Callback<RecipeResponse>{
            override fun onResponse(
                call: Call<RecipeResponse>,
                response: Response<RecipeResponse>
            ) {
                if(response.isSuccessful){
                    val recipes = response.body()?.recipes
                    recyclerView = binding.recyclerView
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    recyclerView.adapter = RecipeListAdapter(recipes!!)
                    recyclerView.setHasFixedSize(true)

                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    Toast.makeText(requireContext(), "FETCHED: " + recipes.size, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "ERROR", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}