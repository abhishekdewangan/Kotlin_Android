package barqexp.mersattech.firstkotlinproject.utils

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

abstract class RecyclerOnScrollListener(private val mLinearLayoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    private var previousTotalItemCount = 0 // The total number of items in the dataset after the last load
    private var loading = true
    // True if we are still waiting for the last set of data to load.
    private var visibleThreshold = 10 // The minimum amount of items to have below your current scroll position before loading more.
    internal var firstVisibleItemPos: Int = 0
    internal var visibleItemCount: Int = 0
    internal var totalItemCount: Int = 0
    // Sets the starting page index
    private val startingPageIndex = 0
    // The current offset index of data you have loaded
    private var currentPage = 0

    constructor(linearLayoutManager: LinearLayoutManager,
                minScrollItems: Int) : this(linearLayoutManager) {
        this.visibleThreshold = minScrollItems
    }

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        visibleItemCount = recyclerView!!.childCount
        // Total item count (Minus the other items: Trending, Recent and Recommended)
        totalItemCount = mLinearLayoutManager.itemCount
        firstVisibleItemPos = mLinearLayoutManager.findFirstVisibleItemPosition()

        if (!(visibleItemCount > 0 && totalItemCount > 0 && firstVisibleItemPos >= 0)) {
            Log.d(TAG, " NO ITEMS IN RECYCLERVIEW, RETURN! visibleItemCount = " + visibleItemCount
                    + " , totalItemCount = " + totalItemCount + " , firstVisibleItemPos = " + firstVisibleItemPos)
            return
        }

        // If the total item count is zero and the previous isn't, assume the
        // list is invalidated and should be reset back to initial state
        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex
            this.previousTotalItemCount = 0
            if (totalItemCount == 0) {
                this.loading = true
            }
        }
        // If it's still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
            currentPage++
        }

        // If it isn't currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        if (!loading && firstVisibleItemPos + visibleItemCount >= totalItemCount
                && totalItemCount >= visibleThreshold) {
            Log.d(TAG, "LOADING MORE: firstVisibleItemPos: " + firstVisibleItemPos
                    + " visibleItemCount: " + visibleItemCount + " visibleThreshold: "
                    + visibleThreshold + " >? totalItemCount: " + totalItemCount)
            loading = onLoadMore(currentPage + 1, totalItemCount)
        }
    }

    // Defines the process for actually loading more data based on page
    // Returns true if more data is being loaded; returns false if there is no more data to load.
    abstract fun onLoadMore(page: Int, totalItemsCount: Int): Boolean

    companion object {
        var TAG = RecyclerOnScrollListener::class.java.simpleName
    }
}
