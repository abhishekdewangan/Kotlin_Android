package barqexp.mersattech.firstkotlinproject.utils

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import barqexp.mersattech.firstkotlinproject.R

class RecyclerItemDecorator(private val orientationType: String) : RecyclerView.ItemDecoration() {
    companion object {
        val ORIENTATION_TYPE_HORIZONTAL: String = "Horizontal"
        val ORIENTATION_TYPE_VERTICAL: String = "Vertical"
    }

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        when (orientationType) {
            ORIENTATION_TYPE_HORIZONTAL -> {
                if (parent?.getChildAdapterPosition(view) == 0)
                    outRect?.left = view?.getResources()?.getDimensionPixelSize(R.dimen.margin8dp)
                outRect?.right = view?.getResources()?.getDimensionPixelSize(R.dimen.margin8dp)
            }

            ORIENTATION_TYPE_VERTICAL -> {
                if (parent?.getChildAdapterPosition(view) == 0)
                    outRect?.top = view?.getResources()?.getDimensionPixelSize(R.dimen.margin16dp)
                outRect?.bottom = view?.getResources()?.getDimensionPixelSize(R.dimen.margin16dp)
            }
        }
    }
}