package gt.uvg.eatify.model

data class Recipe(
    val title: String,
    val image: String,
    val id: Int
) {

}

data class RecipeResponse(
    val recipes: List<Recipe>
)