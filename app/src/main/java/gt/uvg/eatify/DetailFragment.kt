package gt.uvg.eatify

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import gt.uvg.eatify.databinding.FragmentDetailBinding
import gt.uvg.eatify.model.Recipe
import gt.uvg.eatify.repository.RecipeAPI
import retrofit2.Call
import retrofit2.Response


class DetailFragment : Fragment() {

    companion object{
        val foodId = "foodId"
    }

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var recipeId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{recipeId = it.getInt(foodId)}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(requireContext(), "ID: $recipeId", Toast.LENGTH_LONG).show()
        val client = RecipeAPI.retrofitService.getRecipeInformation(recipeId, "88ee5f7c9c874c8ba76eabced84e38ec")
        client.enqueue(object : retrofit2.Callback<Recipe> {
            override fun onResponse(
                call: Call<Recipe>,
                response: Response<Recipe>
            ){
                if (response.isSuccessful){

                    val title = response.body()?.title
                    val image = response.body()?.image
                    val readyInMinutes = response.body()?.readyInMinutes
                    val servings = response.body()?.servings
                    val sourceUrl = response.body()?.sourceUrl
                    val summary = response.body()?.summary
                    val instructions = response.body()?.instructions
                    val regex = "<(\\S*?)[^>]*>.?<\\1>|<.*?>"

                    Picasso.get().load(image)
                                .placeholder(R.drawable.placeholder)
                                .error(R.drawable.placeholder)
                                .into(binding.img)

                    binding.title.text = title
                    binding.summary.text = summary?.replace(regex.toRegex(), " ")
                    binding.servings.text = servings.toString()
                    binding.readyInMinutes.text = readyInMinutes.toString()
                    binding.instructions.text = instructions?.replace("\n", " ")
                    binding.sourceUrl.text = sourceUrl

                    binding.sourceUrl.setOnClickListener{
                        val browserIntent =
                            Intent(Intent.ACTION_VIEW, Uri.parse(sourceUrl))
                            startActivity(browserIntent)
                    }

                }
            }

            override fun onFailure(call: Call<Recipe>, t: Throwable) {
                Toast.makeText(requireContext(), "ERROR", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}