package gt.uvg.eatify

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import gt.uvg.eatify.MainFragmentDirections
import gt.uvg.eatify.R
import gt.uvg.eatify.databinding.ItemRecipeViewBinding
import gt.uvg.eatify.model.Recipe

class RecipeListAdapter(private val recipeList: List<Recipe>) : RecyclerView.Adapter<RecipeListAdapter.RecipeListHolder>() {

    inner class RecipeListHolder(val binding: ItemRecipeViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListHolder {
        val binding = ItemRecipeViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeListHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeListHolder, position: Int) {
        val recipe = recipeList.get(position)
        holder.binding.recipeName.text = recipe.title
        Picasso.get()
            .load(recipe.image)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(holder.binding.recipePhoto)

        holder.binding.cardView.setOnClickListener{
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(recipe.id)
            holder.binding.root.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }
}