package gt.uvg.eatify.model

data class Recipe(
    val title: String,
    val image: String,
    val id: Int,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceUrl: String,
    val summary: String,
    val instructions: String
) {

}

data class RecipeResponse(
    val recipes: List<Recipe>
)